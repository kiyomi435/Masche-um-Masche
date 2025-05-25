package com.example.masche_um_masche.ui.projects;

import android.content.Intent;
import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.ProjectPart;
import com.example.masche_um_masche.ui.BaseActivity;
import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.entity.Project;

import androidx.core.content.ContextCompat;
import java.util.ArrayList;
import androidx.room.Room;

public class ProjectsActivity extends BaseActivity {
    private AppDatabase db;
    LinearLayout projectListContainer;
    List<ProjectPart> exampleParts = new ArrayList<>();
    private List<Project> allProjects = new ArrayList<>();
    Button btnProgress;
    Button btnFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projects);
        createBottomNavigation(R.id.nav_projects);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "masche_db").build();
        projectListContainer = findViewById(R.id.project_list_container);

        ImageView addPatternButton = findViewById(R.id.add_project);
        addPatternButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectsActivity.this, NewProjectActivity.class);
                startActivity(intent);            }
        });

        selectFinishedProjects(false);


        btnProgress = findViewById(R.id.btn_progress);
        btnFinished = findViewById(R.id.btn_finished);

        // Startzustand: btnActive ist ausgewählt
        selectButton(btnProgress, btnFinished);

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(btnProgress, btnFinished);
                selectFinishedProjects(false);
            }
        });

        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(btnFinished, btnProgress);
                selectFinishedProjects(true);
            }
        });

        loadProjectsFromDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProjectsFromDatabase(); // neu laden nach Rückkehr von NewProjectActivity
    }

    private void addProjectView(LinearLayout container, Project project) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.project_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_project_name);
        TextView teileText = projectView.findViewById(R.id.text_material_details);
        ProgressBar progressBar = projectView.findViewById(R.id.progress_project);
        ImageButton deleteButton = projectView.findViewById(R.id.button_delete);

        nameText.setText(project.getName());
        teileText.setText(project.getParts().size() + " Teile");
        progressBar.setProgress(project.getProgress());

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(ProjectsActivity.this, ProjectActivity.class);
            intent.putExtra("projectId", project.id);  // <<< DAS ist wichtig
            startActivity(intent);
        });

        deleteButton.setOnClickListener(v -> {
            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(this);
                db.projectDao().delete(project);
                runOnUiThread(() -> {
                    allProjects.remove(project);
                    selectFinishedProjects(false); // oder true, je nach Zustand
                });
            }).start();
        });

        container.addView(projectView);
    }

    private void selectButton(Button selected, Button deselected) {
        // Aktiven Button hervorheben
        selected.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.system_on_primary_container_dark));
        selected.setTextColor(Color.WHITE);

        // Nicht aktiven Button neutral darstellen
        deselected.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray));
        deselected.setTextColor(Color.WHITE);
    }

    // zeigt Projekte basierend auf Fortschritt
    private void selectFinishedProjects(boolean showFinished) {
        projectListContainer.removeAllViews(); // vorherigen Inhalt löschen

        for (Project project : allProjects) {
            if (!showFinished && project.getProgress() < 100) { //draw all in progress projects
                addProjectView(projectListContainer, project);
            } else if (showFinished && project.getProgress() >= 100) { //draw all finsihed projects
                addProjectView(projectListContainer, project);
            }
        }
    }

    private void loadProjectsFromDatabase() {
        new Thread(() -> {
            allProjects = db.projectDao().getAll();
            // Für jedes Projekt: zugehörige Teile laden und zuordnen
            for (Project project : allProjects) {
                List<ProjectPart> parts = db.projectPartDao().getAllByProjectId(project.getId());
                project.setParts(parts); // das berechnet allRows & currentRows automatisch
            }

            runOnUiThread(() -> {

                selectFinishedProjects(false); // z. B. Standardanzeige
            });
        }).start();
    }
}
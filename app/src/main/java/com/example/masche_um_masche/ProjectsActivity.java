package com.example.masche_um_masche;

import android.content.Intent;
import java.util.List;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class ProjectsActivity extends BaseActivity {
    LinearLayout projectListContainer;
    List<ProjectPart> exampleParts = new ArrayList<>();
    private final List<Project> allProjects = new ArrayList<>();
    Button btnProgress;
    Button btnFinished;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        exampleParts.add(new ProjectPart("Teil1", 20, 80));
        exampleParts.add(new ProjectPart("Teil2", 30, 20));

        //create Projects
        allProjects.add(new Project("Sommerpulli", exampleParts, 60));
        allProjects.add(new Project("Mütze", exampleParts, 30));
        allProjects.add(new Project("Decke", exampleParts, 80));
        allProjects.add(new Project("Tasche", exampleParts, 10));
        allProjects.add(new Project("Teddy", exampleParts, 100));

        setContentView(R.layout.activity_projects);
        createBottomNavigation(R.id.nav_projects);

        projectListContainer = findViewById(R.id.project_list_container);

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
    }

    private void addProjectView(LinearLayout container, Project project) {
            View projectView = LayoutInflater.from(this).inflate(R.layout.project_item, container, false);

            TextView nameText = projectView.findViewById(R.id.text_project_name);
            TextView teileText = projectView.findViewById(R.id.text_material_details);
            ProgressBar progressBar = projectView.findViewById(R.id.progress_project);

            nameText.setText(project.name);
            teileText.setText(project.parts.size() + " Teile");
            progressBar.setProgress(project.progress);

            projectView.setOnClickListener(v -> {
                Intent intent = new Intent(ProjectsActivity.this, ProjectActivity.class);
                startActivity(intent);
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
            if (!showFinished && project.progress < 100) { //draw all in progress projects
                addProjectView(projectListContainer, project);
            } else if (showFinished && project.progress >= 100) { //draw all finsihed projects
                addProjectView(projectListContainer, project);
            }
        }
    }
}
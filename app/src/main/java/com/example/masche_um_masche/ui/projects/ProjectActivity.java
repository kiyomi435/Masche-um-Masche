package com.example.masche_um_masche.ui.projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.ProjectPart;
import com.example.masche_um_masche.ui.projectparts.NewProjectPartActivity;
import com.example.masche_um_masche.ui.projectparts.ProjectPartActivity;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends Activity {
    LinearLayout projectPartsListContainer;
    private final List<ProjectPart> allParts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        int projectId = getIntent().getIntExtra("projectId", -1);
        projectPartsListContainer = findViewById(R.id.project_parts_list_container);

        findViewById(R.id.back_button).setOnClickListener(v -> finish());

        findViewById(R.id.button_edit).setOnClickListener(v-> {
            Intent intent = new Intent(ProjectActivity.this, EditProjectActivity.class);
            intent.putExtra("project_id", projectId);
            startActivity(intent);
        });

        findViewById(R.id.add_project_part).setOnClickListener(v -> {
            Intent intent = new Intent(ProjectActivity.this, NewProjectPartActivity.class);
            intent.putExtra("projectId", projectId); // mitgeben!
            startActivity(intent);
        });


    }

    private void addProjectPartView(LinearLayout container, ProjectPart part) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.project_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_project_name);
        TextView teileText = projectView.findViewById(R.id.text_material_details);
        ProgressBar progressBar = projectView.findViewById(R.id.progress_project);
        ImageButton deleteButton = projectView.findViewById(R.id.button_delete);

        nameText.setText(part.getName());
        String row_info = part.getCurrentRows() + "/" + part.getAllRows() + " Reihen";
        teileText.setText(row_info);
        int progress = (int) ((double) part.getCurrentRows() / part.getAllRows() * 100);
        progressBar.setProgress(progress);

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(ProjectActivity.this, ProjectPartActivity.class);
            intent.putExtra("projectPartId", part.id);  // <<< DAS ist wichtig
            startActivity(intent);
        });

        deleteButton.setOnClickListener(v -> {
            new Thread(() -> {
                AppDatabase db = AppDatabase.getInstance(this);
                db.projectPartDao().delete(part);
                runOnUiThread(() -> {
                    container.removeView(projectView);
                    //selectFinishedProjects(false); // oder true, je nach Zustand
                });
            }).start();
        });

        container.addView(projectView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadParts();  // neu laden, wenn die Seite wieder sichtbar ist
    }

    private void loadParts() {
        projectPartsListContainer.removeAllViews();  // wichtig: erst leeren
        int projectId = getIntent().getIntExtra("projectId", -1);


        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            List<ProjectPart> parts = db.projectPartDao().getAllByProjectId(projectId);

            runOnUiThread(() -> {
                for (ProjectPart part : parts) {
                    addProjectPartView(projectPartsListContainer, part);
                }
            });
        }).start();
    }

}
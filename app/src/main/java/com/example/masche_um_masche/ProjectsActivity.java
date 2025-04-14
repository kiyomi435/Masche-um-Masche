package com.example.masche_um_masche;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProjectsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        createBottomNavigation(R.id.nav_projects);

        LinearLayout container = findViewById(R.id.project_list_container);

        // Einfach ein paar Projekte hinzufügen
        addProjectView(container, "Sommerpulli", 5, 60);
        addProjectView(container, "Mütze", 2, 30);
        addProjectView(container, "Decke", 8, 80);
    }

    private void addProjectView(LinearLayout container, String name, int teile, int progress) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.project_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_project_name);
        TextView teileText = projectView.findViewById(R.id.text_project_parts);
        ProgressBar progressBar = projectView.findViewById(R.id.progress_project);

        nameText.setText(name);
        teileText.setText(teile + " Teile");
        progressBar.setProgress(progress);

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(ProjectsActivity.this, ProjectActivity.class);
            startActivity(intent);
        });

        container.addView(projectView);
    }
}
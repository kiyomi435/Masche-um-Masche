package com.example.masche_um_masche;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

public class ProjectsActivity extends BaseActivity {

    Button btnProgress;
    Button btnFinished;

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
        addProjectView(container, "Tasche", 0, 10);
        addProjectView(container, "Teddy", 10, 100);


        btnProgress = findViewById(R.id.btn_progress);
        btnFinished = findViewById(R.id.btn_finished);

        // Startzustand: btnActive ist ausgewählt
        selectButton(btnProgress, btnFinished);

        btnProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(btnProgress, btnFinished);
            }
        });

        btnFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectButton(btnFinished, btnProgress);
            }
        });
    }

    private void addProjectView(LinearLayout container, String name, int teile, int progress) {
        if(progress < 100) {
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

    private void selectButton(Button selected, Button deselected) {
        // Aktiven Button hervorheben
        selected.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.system_on_primary_container_dark));
        selected.setTextColor(Color.WHITE);

        // Nicht aktiven Button neutral darstellen
        deselected.setBackgroundTintList(ContextCompat.getColorStateList(this, android.R.color.darker_gray));
        deselected.setTextColor(Color.WHITE);
    }
}
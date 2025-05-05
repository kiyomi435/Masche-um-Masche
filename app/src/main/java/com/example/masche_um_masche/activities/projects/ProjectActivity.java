package com.example.masche_um_masche.activities.projects;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.objects.ProjectPart;

import java.util.ArrayList;
import java.util.List;

public class ProjectActivity extends Activity {
    LinearLayout projectPartsListContainer;
    private final List<ProjectPart> allParts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        allParts.add(new ProjectPart("Teil1", 20, 80));
        allParts.add(new ProjectPart("Teil2", 30, 20));

        projectPartsListContainer = findViewById(R.id.project_parts_list_container);

        for (ProjectPart part : allParts) {
                addProjectPartView(projectPartsListContainer, part);
        }

        ImageView backButton = findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Beendet die aktuelle Activity und geht zur vorherigen zurück
            }
        });

        ImageView addProjectButton = findViewById(R.id.add_project_part);
        addProjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, NewProjectPartActivity.class);
                startActivity(intent);            }
        });


    }

    private void addProjectPartView(LinearLayout container, ProjectPart part) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.project_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_project_name);
        TextView teileText = projectView.findViewById(R.id.text_material_details);
        ProgressBar progressBar = projectView.findViewById(R.id.progress_project);

        nameText.setText(part.getName());
        teileText.setText(part.getRows() + " Reihen");
        progressBar.setProgress(part.getProgress());

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(ProjectActivity.this, ProjectPartActivity.class);
            startActivity(intent);
        });

        container.addView(projectView);
    }
}
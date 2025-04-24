package com.example.masche_um_masche;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MaterialsActivity extends BaseActivity {
    LinearLayout materialListContainer;
    private final List<ProjectPart> allParts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        createBottomNavigation(R.id.nav_materials);

        allParts.add(new ProjectPart("Teil1", 20, 80));
        allParts.add(new ProjectPart("Teil2", 30, 20));

        materialListContainer = findViewById(R.id.material_list_container);

        for (ProjectPart part : allParts) {
            addProjectPartView(materialListContainer, part);
        }
    }

    private void addProjectPartView(LinearLayout container, ProjectPart part) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.material_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_material_name);
        TextView detailsText = projectView.findViewById(R.id.text_material_details);

        nameText.setText(part.name);
        detailsText.setText(part.rows + " Reihen");

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(MaterialsActivity.this, ProjectPartActivity.class);
            startActivity(intent);
        });

        container.addView(projectView);
    }
}
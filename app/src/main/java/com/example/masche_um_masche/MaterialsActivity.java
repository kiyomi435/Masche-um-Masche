package com.example.masche_um_masche;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masche_um_masche.objects.CrochetHook;
import com.example.masche_um_masche.objects.IMaterial;
import com.example.masche_um_masche.objects.KnittingNeedle;
import com.example.masche_um_masche.objects.OtherUtensil;
import com.example.masche_um_masche.objects.ProjectPart;
import com.example.masche_um_masche.objects.Wool;

import java.util.ArrayList;
import java.util.List;

public class MaterialsActivity extends BaseActivity {
    LinearLayout materialListContainer;
    private List<IMaterial> allMaterials;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        createBottomNavigation(R.id.nav_materials);

        initializeMaterials();

        materialListContainer = findViewById(R.id.material_list_container);

        for (IMaterial material : allMaterials) {
            //addProjectPartView(materialListContainer, part);
            displayMaterials(materialListContainer, material);
        }
    }

    private void addProjectPartView(LinearLayout container, ProjectPart part) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.material_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_material_name);
        TextView detailsText = projectView.findViewById(R.id.text_material_details);

        nameText.setText(part.getName());
        detailsText.setText(part.getRows() + " Reihen");

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(MaterialsActivity.this, ProjectPartActivity.class);
            startActivity(intent);
        });

        container.addView(projectView);
    }

    private void displayMaterials(LinearLayout container, IMaterial mat) {
        View projectView = LayoutInflater.from(this).inflate(R.layout.material_item, container, false);

        TextView nameText = projectView.findViewById(R.id.text_material_name);
        TextView detailsText = projectView.findViewById(R.id.text_material_details);

        nameText.setText(mat.getName());
        detailsText.setText(mat.getSummaryInfo());

        projectView.setOnClickListener(v -> {
            Intent intent = new Intent(MaterialsActivity.this, ProjectPartActivity.class);
            startActivity(intent);
        });

        container.addView(projectView);
    }


    private void initializeMaterials() {
        allMaterials = new ArrayList<>();

        // Beispiel 1: Wolle
        Wool merinoWool = new Wool(
                "Merino 120",
                "Bordeaux",
                "Lana Grossa",
                "120m / 50g",
                3,
                "100% Merino Wool",
                "Box A1",
                ""
        );

        // Beispiel 2: Stricknadel
        KnittingNeedle needle = new KnittingNeedle(
                "Double Pointed",
                "3.5 mm",
                "20 cm",
                "Bamboo",
                "Drawer 3",
                ""
        );

        // Beispiel 3: Häkelnadel
        CrochetHook hook = new CrochetHook(
                "3.0 mm",
                "Ergonomic",
                "Plastic",
                "Drawer 2",
                ""
        );

        // Beispiel 4: Sonstiges
        OtherUtensil stitchMarker = new OtherUtensil(
                "Stitch Marker",
                "Heart-shaped",
                "Plastic",
                10,
                "Jar 1",
                ""
        );

        // Alle zur Liste hinzufügen
        allMaterials.add(merinoWool);
        allMaterials.add(needle);
        allMaterials.add(hook);
        allMaterials.add(stitchMarker);
    }
}
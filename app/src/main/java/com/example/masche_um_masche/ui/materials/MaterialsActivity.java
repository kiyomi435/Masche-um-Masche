package com.example.masche_um_masche.ui.materials;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.masche_um_masche.ui.BaseActivity;
import com.example.masche_um_masche.R;
import com.example.masche_um_masche.ui.projectparts.ProjectPartActivity;
import com.example.masche_um_masche.data.entity.CrochetHook;
import com.example.masche_um_masche.data.entity.Material;
import com.example.masche_um_masche.data.entity.KnittingNeedle;
import com.example.masche_um_masche.data.entity.OtherUtensil;
import com.example.masche_um_masche.data.entity.Wool;

import java.util.ArrayList;
import java.util.List;

public class MaterialsActivity extends BaseActivity {
    LinearLayout materialListContainer;
    private List<Material> allMaterials;
    private String currentFilter = "All";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        createBottomNavigation(R.id.nav_materials);

        setupFilterButtons();
        initializeMaterials();

        materialListContainer = findViewById(R.id.material_list_container);

        displayMaterials(materialListContainer, allMaterials);

        ImageView addPatternButton = findViewById(R.id.add_material);
        addPatternButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaterialsActivity.this, NewMaterialActivity.class);
                startActivity(intent);            }
        });
    }

    private void displayMaterials(LinearLayout container, List<Material> materialsToDisplay) {
        container.removeAllViews(); // vorherige Views löschen

        for (Material mat : materialsToDisplay) {
            View materialView = LayoutInflater.from(this).inflate(R.layout.material_item, container, false);

            TextView nameText = materialView.findViewById(R.id.text_material_name);
            TextView detailsText = materialView.findViewById(R.id.text_material_details);

            nameText.setText(mat.getName());
            detailsText.setText(mat.getSummaryInfo());

            materialView.setOnClickListener(v -> {
                Intent intent = new Intent(MaterialsActivity.this, ProjectPartActivity.class);
                startActivity(intent);
            });

            container.addView(materialView);
        }
    }

    private void setupFilterButtons() {
        LinearLayout filterBar = findViewById(R.id.filter_bar);
        String[] filters = {"All", "Wool", "KnittingNeedle", "CrochetHook", "OtherUtensil"};

        for (String filter : filters) {
            Button btn = new Button(this);
            btn.setText(filter);
            btn.setAllCaps(false);
            btn.setTextSize(14);
            btn.setPadding(30, 10, 30, 10);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(16, 0, 16, 0);
            btn.setLayoutParams(params);

            btn.setBackgroundResource(R.drawable.filter_button_background); // siehe unten
            btn.setTextColor(Color.BLACK);

            btn.setOnClickListener(v -> {
                currentFilter = filter;

                List<Material> filteredList = new ArrayList<>();
                for (Material m : allMaterials) {
                    if (filter.equals("All") || m.getClass().getSimpleName().equalsIgnoreCase(filter.replace(" ", ""))) {
                        filteredList.add(m);
                    }
                }

                displayMaterials(materialListContainer, filteredList);
            });

            filterBar.addView(btn);
        }
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
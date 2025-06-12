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

import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.ui.BaseActivity;
import com.example.masche_um_masche.R;
import com.example.masche_um_masche.ui.projectparts.ProjectPartActivity;
import com.example.masche_um_masche.data.entity.Material;

import java.util.ArrayList;
import java.util.List;

public class MaterialsActivity extends BaseActivity {
    LinearLayout materialListContainer;
    private List<Material> allMaterials;
    private String currentFilter = "All";
    private List<Button> filterButtons = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        materialListContainer = findViewById(R.id.material_list_container);
        createBottomNavigation(R.id.nav_materials);

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);

            List<Material> loadedMaterials = new ArrayList<>();
            loadedMaterials.addAll(db.woolDao().getAll());
            loadedMaterials.addAll(db.knittingNeedleDao().getAll());
            loadedMaterials.addAll(db.crochetHookDao().getAll());
            loadedMaterials.addAll(db.otherUtensilDao().getAll());

            runOnUiThread(() -> {
                allMaterials = loadedMaterials;
                setupFilterButtons();
                displayMaterials(materialListContainer, allMaterials);
            });
        }).start();

        ImageView addMaterialButton = findViewById(R.id.add_material);
        addMaterialButton.setOnClickListener(new View.OnClickListener() {
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
        filterButtons.clear();

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

            btn.setBackgroundResource(filter.equals("All")
                    ? R.drawable.filter_button_selected
                    : R.drawable.filter_button_background);
            btn.setTextColor(Color.BLACK);

            btn.setOnClickListener(v -> {
                currentFilter = filter;

                // Button-Stile aktualisieren
                for (Button b : filterButtons) {
                    if (b == btn) {
                        b.setBackgroundResource(R.drawable.filter_button_selected);
                    } else {
                        b.setBackgroundResource(R.drawable.filter_button_background);
                    }
                }

                List<Material> filteredList = new ArrayList<>();
                for (Material m : allMaterials) {
                    if (filter.equals("All") || m.getClass().getSimpleName().equalsIgnoreCase(filter.replace(" ", ""))) {
                        filteredList.add(m);
                    }
                }

                displayMaterials(materialListContainer, filteredList);
            });

            filterButtons.add(btn);
            filterBar.addView(btn);
        }
    }
}
package com.example.masche_um_masche.activities.materials;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.masche_um_masche.R;

import java.util.HashMap;
import java.util.Map;

public class NewMaterialActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_material);
        setupCategorySelection();

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Beendet die aktuelle Activity und geht zur vorherigen zurück
            }
        });


    }

    private void setupCategorySelection() {
        LinearLayout categoryButtons = findViewById(R.id.category_buttons);

        // Layout-Referenzen
        LinearLayout formWool = findViewById(R.id.form_wool);
        LinearLayout formKnittingNeedle = findViewById(R.id.form_knitting_needle);
        LinearLayout formCrochetHook = findViewById(R.id.form_crochet_hook);
        LinearLayout formOther = findViewById(R.id.form_other);

        Map<String, LinearLayout> formMap = new HashMap<>();
        formMap.put("Wool", formWool);
        formMap.put("KnittingNeedle", formKnittingNeedle);
        formMap.put("CrochetHook", formCrochetHook);
        formMap.put("Others", formOther);

        String[] categories = {"Wool", "KnittingNeedle", "CrochetHook", "Others"};

        createButtons(categories, formMap, categoryButtons);
    }

    private void createButtons(String[] categories, Map<String, LinearLayout> formMap, LinearLayout categoryButtons) {
        for (String category : categories) {
            Button btn = new Button(this);
            btn.setText(category);

            //layout of buttons
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
                // Alle Formulare ausblenden
                for (LinearLayout layout : formMap.values()) {
                    layout.setVisibility(View.GONE);
                }
                // Das ausgewählte sichtbar machen
                LinearLayout selectedForm = formMap.get(category);
                if (selectedForm != null) selectedForm.setVisibility(View.VISIBLE);
            });
            categoryButtons.addView(btn);
        }
    }

}
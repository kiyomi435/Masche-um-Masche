package com.example.masche_um_masche;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.masche_um_masche.objects.CrochetHook;
import com.example.masche_um_masche.objects.KnittingNeedle;
import com.example.masche_um_masche.objects.Material;
import com.example.masche_um_masche.objects.OtherUtensil;
import com.example.masche_um_masche.objects.Pattern;
import com.example.masche_um_masche.objects.Wool;

import java.util.ArrayList;
import java.util.List;

public class PatternsActivity extends BaseActivity {

    private List<Pattern> allPatterns = new ArrayList<>();
    private LinearLayout patternListContainer;
    private String currentFilter = "All";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patterns);
        createBottomNavigation(R.id.nav_patterns);

        patternListContainer = findViewById(R.id.pattern_list_container);

        initializePatterns();         // 1. Erstelle Beispiel-Pattern
        setupFilterButtons();
        displayPatterns(patternListContainer, allPatterns);
    }

    private void displayPatterns(LinearLayout container, List<Pattern> patternsToDisplay) {
        container.removeAllViews(); // vorherige Views löschen

        for (Pattern pat : patternsToDisplay) {
            View patternView = LayoutInflater.from(this).inflate(R.layout.pattern_item, container, false);

            TextView nameText = patternView.findViewById(R.id.text_pattern_name);
            TextView detailsText = patternView.findViewById(R.id.text_pattern_details);

            nameText.setText(pat.getName());
            detailsText.setText(pat.getSummaryInfo());

            patternView.setOnClickListener(v -> {
                Intent intent = new Intent(PatternsActivity.this, ProjectPartActivity.class);
                startActivity(intent);
            });

            container.addView(patternView);
        }
    }

    private void setupFilterButtons() {
        LinearLayout filterBar = findViewById(R.id.filter_bar);
        String[] filters = {"All", "Kleidung", "Haushalt", "Accessoire"};

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

                List<Pattern> filteredList = new ArrayList<>();
                for (Pattern p : allPatterns) {
                    if (filter.equals("All") || p.getCategory().equalsIgnoreCase(filter.replace(" ", ""))) {
                        filteredList.add(p);
                    }
                }

                displayPatterns(patternListContainer, filteredList);
            });

            filterBar.addView(btn);
        }
    }


    private void initializePatterns() {
        allPatterns.add(new Pattern(
                "Sommerpulli", "Kleidung",
                "path/to/sommerpulli.pdf", "KnittingNeedle", "3mm"));

        allPatterns.add(new Pattern(
                "Topflappen Herz", "Haushalt",
                "path/to/topflappen.pdf", "CrochetHook", "4mm"));

        allPatterns.add(new Pattern(
                "Schal Easy", "Accessoire",
                "path/to/schal.pdf", "KnittingNeedle", "6mm"));
    }
}
package com.example.masche_um_masche.ui.patterns;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.masche_um_masche.R;

public class NewPatternActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pattern);
        setCategoryButtons();
        setNeedletypeButtons();

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Beendet die aktuelle Activity und geht zur vorherigen zurück
            }
        });
    }

    private void setCategoryButtons() {
        LinearLayout categoryButtons = findViewById(R.id.category_buttons);
        String[] catergories = {"Deko", "Kleidung", "Haushalt", "Sonstiges"};

        for (String category : catergories) {
            Button btn = new Button(this);
            btn.setText(category);
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
            categoryButtons.addView(btn);
        }
    }

    private void setNeedletypeButtons() {
        LinearLayout needleButtons = findViewById(R.id.needletype_buttons);
        String[] needles = {"Stricknadel", "Häkelnadel"};

        for (String needle : needles) {
            Button btn = new Button(this);
            btn.setText(needle);
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
            needleButtons.addView(btn);
        }
    }
}
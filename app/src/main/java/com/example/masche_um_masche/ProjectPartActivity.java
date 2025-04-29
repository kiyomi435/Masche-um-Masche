package com.example.masche_um_masche;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProjectPartActivity extends AppCompatActivity {
    private int currentRows = 0;
    private TextView currentRowText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_part);

        ImageView backButton = findViewById(R.id.back_button);
        setButtonListeners();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Beendet die aktuelle Activity und geht zur vorherigen zurück
            }
        });
    }

    protected void setButtonListeners() {
        ImageButton btnAdd = findViewById(R.id.button_add);
        ImageButton btnReset = findViewById(R.id.button_reset);
        ImageButton btnSubtract = findViewById(R.id.button_substract);
        currentRowText = findViewById(R.id.current_row);

        updateCurrentRowDisplay();

        btnAdd.setOnClickListener(v -> {
            currentRows++;
            updateCurrentRowDisplay();
        });

        btnSubtract.setOnClickListener(v -> {
            if (currentRows > 0) {
                currentRows--;
                updateCurrentRowDisplay();
            }
        });

        btnReset.setOnClickListener(v -> {
            currentRows = 0;
            updateCurrentRowDisplay();
        });
    }

    private void updateCurrentRowDisplay() {
        currentRowText.setText(String.valueOf(currentRows));
    }
}
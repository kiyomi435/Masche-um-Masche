package com.example.masche_um_masche.ui.projectparts;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.Project;
import com.example.masche_um_masche.data.entity.ProjectPart;
import com.example.masche_um_masche.ui.projects.ProjectActivity;

public class EditProjectPartActivity extends Activity {
    private EditText nameInput, rowsInput;
    private AppDatabase db;
    private ProjectPart projectPart;
    private int partId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_part); // 👈 gleiches Layout wie bei Neuerstellung

        nameInput = findViewById(R.id.edit_name);
        rowsInput = findViewById(R.id.edit_max_rows);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "masche_db").build();

        // ID des zu bearbeitenden Teils aus dem Intent holen
        partId = getIntent().getIntExtra("partId", -1);
        if (partId == -1) {
            Toast.makeText(this, "Teil nicht gefunden", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Teil laden und Felder ausfüllen
        new Thread(() -> {
            projectPart = db.projectPartDao().getById(partId);
            runOnUiThread(() -> {
                if (projectPart != null) {
                    nameInput.setText(projectPart.getName());
                    rowsInput.setText(String.valueOf(projectPart.getAllRows()));
                }
            });
        }).start();

        // Save-Button
        Button saveButton = findViewById(R.id.button_save_project_part);
        saveButton.setOnClickListener(v -> {
            String newName = nameInput.getText().toString();
            String rowsText = rowsInput.getText().toString();

            if (newName.isEmpty() || rowsText.isEmpty()) {
                Toast.makeText(this, "Alle Felder ausfüllen!", Toast.LENGTH_SHORT).show();
                return;
            }

            int newMaxRows = Integer.parseInt(rowsText);

            new Thread(() -> {
                projectPart.setName(newName);
                projectPart.setAllRows(newMaxRows);
                db.projectPartDao().update(projectPart);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Teil aktualisiert", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();
        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
    }
}
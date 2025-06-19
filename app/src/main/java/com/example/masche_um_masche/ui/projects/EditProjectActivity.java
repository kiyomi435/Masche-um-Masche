package com.example.masche_um_masche.ui.projects;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.Project;

public class EditProjectActivity extends Activity {
    private EditText nameInput;
    private AppDatabase db;
    private int projectId; // Wird per Intent übergeben

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project); // Gleiches Layout

        nameInput = findViewById(R.id.edit_project_name);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "masche_db").build();

        TextView title = findViewById(R.id.text_neues_projekt_title);
        title.setText("Projekt bearbeiten"); // Titel anpassen

        // Projekt-ID empfangen
        projectId = getIntent().getIntExtra("project_id", -1);

        if (projectId != -1) {
            new Thread(() -> {
                Project project = db.projectDao().getById(projectId);
                runOnUiThread(() -> {
                    nameInput.setText(project.getName());
                    // Optional: weitere Felder (falls mehr existieren)
                });
            }).start();
        }

        Button saveButton = findViewById(R.id.button_save_project);
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            new Thread(() -> {
                Project updated = new Project(name);
                updated.setId(projectId); // ID behalten
                db.projectDao().update(updated);
                runOnUiThread(this::finish);
            }).start();
        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(v -> finish());
    }
}
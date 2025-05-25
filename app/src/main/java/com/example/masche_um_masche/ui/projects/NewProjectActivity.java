package com.example.masche_um_masche.ui.projects;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.room.Room;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.Project;

public class NewProjectActivity extends Activity {
    private EditText nameInput, rowsInput;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        nameInput = findViewById(R.id.edit_project_name);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "masche_db").build();

        Button saveButton = findViewById(R.id.button_save_project);
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();

            new Thread(() -> {
                db.projectDao().insert(new Project(name));
                runOnUiThread(this::finish); // Zurück zur vorherigen Activity
            }).start();
        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Beendet die aktuelle Activity und geht zur vorherigen zurück
            }
        });
    }
}
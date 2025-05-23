package com.example.masche_um_masche.ui.projectparts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.room.Room;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.Project;
import com.example.masche_um_masche.data.entity.ProjectPart;

public class NewProjectPartActivity extends Activity {
    private EditText nameInput, rowsInput;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project_part);

        nameInput = findViewById(R.id.edit_name);
        rowsInput = findViewById(R.id.edit_max_rows);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "masche_db").build();

        Button saveButton = findViewById(R.id.button_save_project_part);
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            int maxRows = Integer.parseInt(rowsInput.getText().toString());
            int projectId = getIntent().getIntExtra("projectId", -1);
            if (projectId == -1) {
                Toast.makeText(this, "Projekt nicht gefunden!", Toast.LENGTH_SHORT).show();
                return;
            }
            ProjectPart newPart = new ProjectPart(name, maxRows, 0, projectId);

            new Thread(() -> {
                //AppDatabase db = AppDatabase.getInstance(getApplicationContext());
                db.projectPartDao().insert(newPart);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Teil gespeichert", Toast.LENGTH_SHORT).show();
                    finish();
                });
            }).start();


        });

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
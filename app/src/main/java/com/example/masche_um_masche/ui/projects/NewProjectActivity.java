package com.example.masche_um_masche.ui.projects;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.room.Room;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.Project;

public class NewProjectActivity extends Activity {
    private EditText nameInput, rowsInput;
    private AppDatabase db;

    private static final int PICK_FILE_REQUEST_CODE = 101;
    private Uri selectedFileUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_project);

        nameInput = findViewById(R.id.edit_project_name);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "masche_db").build();

        Button saveButton = findViewById(R.id.button_save_project);
        saveButton.setOnClickListener(v -> {
            String name = nameInput.getText().toString();
            String filePath = selectedFileUri != null ? selectedFileUri.toString() : null;

            new Thread(() -> {
                db.projectDao().insert(new Project(name, filePath));
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

        TextView selectedFileName = findViewById(R.id.selected_file_name);
        Button chooseFileButton = findViewById(R.id.button_choose_file);

        chooseFileButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf"); // Nur PDFs
            startActivityForResult(intent, PICK_FILE_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_FILE_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            selectedFileUri = data.getData();

            TextView selectedFileName = findViewById(R.id.selected_file_name);
            String fileName = getFileNameFromUri(selectedFileUri);
            selectedFileName.setText(fileName != null ? fileName : "Datei ausgewählt");
        }
    }

    private String getFileNameFromUri(Uri uri) {
        String result = null;
        if ("content".equals(uri.getScheme())) {
            try (Cursor cursor = getContentResolver().query(uri, null, null, null, null)) {
                if (cursor != null && cursor.moveToFirst()) {
                    int nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                    if (nameIndex != -1) {
                        result = cursor.getString(nameIndex);
                    }
                }
            }
        }
        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }
}
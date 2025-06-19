package com.example.masche_um_masche.ui.projectparts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.Project;
import com.example.masche_um_masche.data.entity.ProjectPart;

import java.io.IOException;

public class ProjectPartActivity extends Activity {
    private int currentRows = 0;
    private TextView currentRowText;
    private ProjectPart part;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_part);

        int projectId = getIntent().getIntExtra("projectId", -1);
        int partId = getIntent().getIntExtra("projectPartId", -1);
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());


        ImageView backButton = findViewById(R.id.back_button);
        setButtonListeners();

        new Thread(() -> {
            part = db.projectPartDao().getById(partId);
            Project project = db.projectDao().getById(part.getProjectId()); // oder wie deine DAO-Methode heißt
            runOnUiThread(() -> {
                // Fülle UI mit Daten
                ((TextView) findViewById(R.id.current_row)).setText(String.valueOf(part.getCurrentRows()));
                ((TextView) findViewById(R.id.text_part_name)).setText(part.getName());

                if (project != null && project.pdfUri != null) {
                    Uri pdfUri = Uri.parse(project.pdfUri);
                    runOnUiThread(() -> renderPdf(pdfUri)); // Methode unten
                }

                ((ProgressBar) findViewById(R.id.progress_project)).setProgress(
                        (int) ((part.getCurrentRows() / (float) part.getAllRows()) * 100));

                setupButtons(part); // hier gleich die Logik fürs Hochzählen einbauen
            });
        }).start();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Beendet die aktuelle Activity und geht zur vorherigen zurück
            }
        });

        ImageView editButton = findViewById(R.id.button_edit);
        editButton.setOnClickListener( v -> {
            Intent intent = new Intent(ProjectPartActivity.this, EditProjectPartActivity.class);
            intent.putExtra("partId", part.getId());
            startActivity(intent);
        });
    }

    protected void setButtonListeners() {
        ImageButton btnAdd = findViewById(R.id.button_add);
        ImageButton btnReset = findViewById(R.id.button_reset);
        ImageButton btnSubtract = findViewById(R.id.button_substract);
        currentRowText = findViewById(R.id.current_row);

        updateCurrentRowDisplay();

        btnAdd.setOnClickListener(v -> {
            if (currentRows < part.getAllRows()) {
                currentRows++;
                updateCurrentRowDisplay();
            }
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

    private void setupButtons(ProjectPart part) {
        TextView currentRowText = findViewById(R.id.current_row);
        ProgressBar progressBar = findViewById(R.id.progress_project);
        EditText inputField = findViewById(R.id.input_field);

        findViewById(R.id.button_add).setOnClickListener(v -> {
            int step = getStep(inputField);
            part.setCurrentRows(part.getCurrentRows() + step);
            updateUIAndSave(part, currentRowText, progressBar);
        });

        findViewById(R.id.button_substract).setOnClickListener(v -> {
            int step = getStep(inputField);
            part.setCurrentRows(Math.max(0, part.getCurrentRows() - step));
            updateUIAndSave(part, currentRowText, progressBar);
        });

        findViewById(R.id.button_reset).setOnClickListener(v -> {
            part.setCurrentRows(0);
            updateUIAndSave(part, currentRowText, progressBar);
        });
    }

    private int getStep(EditText inputField) {
        try {
            return Integer.parseInt(inputField.getText().toString());
        } catch (NumberFormatException e) {
            return 1;
        }
    }

    private void updateUIAndSave(ProjectPart part, TextView currentRowText, ProgressBar progressBar) {
        int current = part.getCurrentRows();
        int max = part.getAllRows();

        currentRowText.setText(String.valueOf(current));
        progressBar.setProgress((int) ((current / (float) max) * 100));

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(getApplicationContext());
            db.projectPartDao().update(part);
        }).start();
    }
    public void renderPdf(Uri pdfUri) {
        try {
            ParcelFileDescriptor fileDescriptor = getContentResolver().openFileDescriptor(pdfUri, "r");
            if (fileDescriptor != null) {
                PdfRenderer pdfRenderer = new PdfRenderer(fileDescriptor);
                PdfRenderer.Page page = pdfRenderer.openPage(0); // erste Seite

                Bitmap bitmap = Bitmap.createBitmap(
                        page.getWidth() * 2, // erhöhe Auflösung (optional)
                        page.getHeight() * 2,
                        Bitmap.Config.ARGB_8888
                );
                page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
                page.close();
                pdfRenderer.close();
                fileDescriptor.close();

                ImageView imageView = findViewById(R.id.pdf_image_view);
                imageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
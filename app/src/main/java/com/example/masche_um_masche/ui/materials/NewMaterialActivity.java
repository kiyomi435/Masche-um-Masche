package com.example.masche_um_masche.ui.materials;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.data.AppDatabase;
import com.example.masche_um_masche.data.entity.CrochetHook;
import com.example.masche_um_masche.data.entity.KnittingNeedle;
import com.example.masche_um_masche.data.entity.Material;
import com.example.masche_um_masche.data.entity.OtherUtensil;
import com.example.masche_um_masche.data.entity.Wool;

import java.util.HashMap;
import java.util.Map;

public class NewMaterialActivity extends Activity {
    private String selectedMaterialType = null;


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
        initiateSaveButton();

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

    private void initiateSaveButton() {
        Button saveButton = findViewById(R.id.button_save_material);
        saveButton.setOnClickListener(v -> {
            if (selectedMaterialType == null) {
                Toast.makeText(this, "Bitte wähle eine Kategorie.", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(getApplicationContext());

            new Thread(() -> {
                Material material = null;

                switch (selectedMaterialType) {
                    case "Wool":
                        String nameWool = ((EditText) findViewById(R.id.edit_name)).getText().toString();
                        String farbe = ((EditText) findViewById(R.id.edit_farbe)).getText().toString();
                        String größe = ((EditText) findViewById(R.id.edit_Größe)).getText().toString();
                        String länge = ((EditText) findViewById(R.id.edit_länge)).getText().toString();
                        String menge = ((EditText) findViewById(R.id.edit_menge)).getText().toString();
                        String zusammensetzung = ((EditText) findViewById(R.id.edit_zusammensetzung)).getText().toString();
                        String lagerort = ((EditText) findViewById(R.id.edit_lagerort_stricken)).getText().toString();
                        String notizenWool = ((EditText) findViewById(R.id.notes_wool)).getText().toString();

                        material = new Wool(nameWool, farbe, größe, länge, menge, zusammensetzung, "", notizenWool);
                        db.woolDao().insert((Wool) material);
                        break;

                    case "KnittingNeedle":
                        String nadeltyp = ((EditText) findViewById(R.id.edit_nadeltyp)).getText().toString();
                        String nadelgröße = ((EditText) findViewById(R.id.edit_nadelgröße)).getText().toString();
                        String nadellänge = ((EditText) findViewById(R.id.edit_nadellänge)).getText().toString();
                        String nadelmaterial = ((EditText) findViewById(R.id.edit_nadelmaterial)).getText().toString();
                        String lagerortStricken = ((EditText) findViewById(R.id.edit_lagerort_stricken)).getText().toString();
                        String notizenStrick = ((EditText) findViewById(R.id.notes_knitting_needle)).getText().toString();

                        material = new KnittingNeedle(nadeltyp, nadelgröße, nadellänge, nadelmaterial, lagerortStricken, notizenStrick);
                        db.knittingNeedleDao().insert((KnittingNeedle) material);
                        break;

                    case "CrochetHook":
                        String grifftyp = ((EditText) findViewById(R.id.edit_grifftyp)).getText().toString();
                        String hakengröße = ((EditText) findViewById(R.id.edit_hakengröße)).getText().toString();
                        String hakenmaterial = ((EditText) findViewById(R.id.edit_hakenmaterial)).getText().toString();
                        String lagerortHook = ((EditText) findViewById(R.id.edit_lagerort)).getText().toString();
                        String notizenHook = ((EditText) findViewById(R.id.notes_crochet_hook)).getText().toString();

                        material = new CrochetHook(hakengröße, grifftyp, hakenmaterial, lagerortHook, notizenHook);
                        db.crochetHookDao().insert((CrochetHook) material);
                        break;

                    case "Others":
                        String gegenstandart = ((EditText) findViewById(R.id.edit_gegenstand_art)).getText().toString();
                        String beschreibung = ((EditText) findViewById(R.id.edit_beschreibung)).getText().toString();
                        String utensil_material = ((EditText) findViewById(R.id.edit_utensil_material)).getText().toString();
                        String utensil_menge = ((EditText) findViewById(R.id.edit_utensil_menge)).getText().toString();
                        String utensil_farbe = ((EditText) findViewById(R.id.edit_utensil_farbe)).getText().toString();
                        String utensil_gebrauch = ((EditText) findViewById(R.id.edit_utensil_gebrauch)).getText().toString();
                        String utensil_location = ((EditText) findViewById(R.id.edit_utensil_location)).getText().toString();
                        String notizenOther = ((EditText) findViewById(R.id.notes_other)).getText().toString();
                        material = new OtherUtensil(gegenstandart, beschreibung, utensil_material, utensil_menge, utensil_location, notizenOther);
                        db.otherUtensilDao().insert((OtherUtensil) material);
                        break;
                }

                runOnUiThread(this::finish);
            }).start();
        });
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
                selectedMaterialType = category;
            });
            categoryButtons.addView(btn);
        }
    }

}
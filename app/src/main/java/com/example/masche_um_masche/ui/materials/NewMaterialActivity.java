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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewMaterialActivity extends Activity {
    private String selectedMaterialType = null;
    private final List<Button> materialTypeButtons = new ArrayList<>();


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
                        String woolName = ((EditText) findViewById(R.id.edit_wool_name)).getText().toString();
                        String color = ((EditText) findViewById(R.id.edit_wool_color)).getText().toString();
                        String needleSize = ((EditText) findViewById(R.id.edit_wool_needle_size)).getText().toString();
                        String stock = ((EditText) findViewById(R.id.edit_wool_stock)).getText().toString();
                        String composition = ((EditText) findViewById(R.id.edit_wool_composition)).getText().toString();
                        String storageLocationWool = ((EditText) findViewById(R.id.edit_wool_storage)).getText().toString();
                        String notesWool = ((EditText) findViewById(R.id.edit_wool_notes)).getText().toString();

                        Wool wool = new Wool(woolName, color, needleSize, stock, composition, storageLocationWool, notesWool);
                        db.woolDao().insert(wool);
                        break;

                    case "KnittingNeedle":
                        String needleType = ((EditText) findViewById(R.id.edit_knitting_needle_type)).getText().toString();
                        String sizeNeedle = ((EditText) findViewById(R.id.edit_knitting_needle_size)).getText().toString();
                        String length = ((EditText) findViewById(R.id.edit_knitting_needle_length)).getText().toString();
                        String materialTypeNeedle = ((EditText) findViewById(R.id.edit_knitting_needle_material)).getText().toString();
                        String storage = ((EditText) findViewById(R.id.edit_knitting_needle_storage)).getText().toString();
                        String notes = ((EditText) findViewById(R.id.edit_knitting_needle_notes)).getText().toString();

                        KnittingNeedle knittingNeedle = new KnittingNeedle(needleType, sizeNeedle, length, materialTypeNeedle, storage, notes);
                        db.knittingNeedleDao().insert(knittingNeedle);
                        break;


                    case "CrochetHook":
                        String gripType = ((EditText) findViewById(R.id.edit_crochet_hook_grip_type)).getText().toString();
                        String sizeHook = ((EditText) findViewById(R.id.edit_crochet_hook_size)).getText().toString();
                        String materialTypeHook = ((EditText) findViewById(R.id.edit_crochet_hook_material)).getText().toString();
                        String storageLocationCrochet = ((EditText) findViewById(R.id.edit_crochet_hook_storage)).getText().toString();
                        String notesCrochet = ((EditText) findViewById(R.id.edit_crochet_hook_notes)).getText().toString();

                        CrochetHook crochetHook = new CrochetHook(sizeHook, gripType, materialTypeHook, storageLocationCrochet, notesCrochet);
                        db.crochetHookDao().insert(crochetHook);
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
            materialTypeButtons.add(btn);

            btn.setOnClickListener(v -> {
                // Alle Formulare ausblenden
                for (LinearLayout layout : formMap.values()) {
                    layout.setVisibility(View.GONE);
                }
                // Das ausgewählte sichtbar machen
                LinearLayout selectedForm = formMap.get(category);
                if (selectedForm != null) selectedForm.setVisibility(View.VISIBLE);
                selectedMaterialType = category;

                // Visuelles Feedback: Alle zurücksetzen
                for (Button b : materialTypeButtons) {
                    b.setBackgroundResource(R.drawable.filter_button_background); // normaler Hintergrund
                    b.setTextColor(Color.BLACK);
                }

                // Aktiven Button hervorheben
                btn.setBackgroundResource(R.drawable.filter_button_selected); // z.B. lila Hintergrund
                btn.setTextColor(Color.WHITE);
            });
            categoryButtons.addView(btn);

        }
    }

}
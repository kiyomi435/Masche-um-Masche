package com.example.masche_um_masche.ui;

import android.app.Activity;
import android.content.Intent;

import com.example.masche_um_masche.ui.profile.ProfileActivity;
import com.example.masche_um_masche.R;
import com.example.masche_um_masche.ui.materials.MaterialsActivity;
import com.example.masche_um_masche.ui.patterns.PatternsActivity;
import com.example.masche_um_masche.ui.projects.ProjectsActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends Activity {

    protected void createBottomNavigation(int selectedItemId) {
        BottomNavigationView nav = findViewById(R.id.bottom_navigation);
        nav.setSelectedItemId(selectedItemId); // markiert aktuellen Tab

        nav.setOnItemSelectedListener(item -> {
            //if (item.getItemId() == selectedItemId) return true; // nichts tun, schon da

            Intent intent = null;

            if(item.getItemId() == R.id.nav_projects) {
                startActivity(new Intent(this, ProjectsActivity.class));
            } else if (item.getItemId() == R.id.nav_materials) {
                startActivity(new Intent(this, MaterialsActivity.class));
            } else if (item.getItemId() == R.id.nav_patterns) {
                startActivity(new Intent(this, PatternsActivity.class));
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
            } else {
                return false;
            }
            overridePendingTransition(0, 0); // kein Übergangseffekt
            return true;
        });
    }
}

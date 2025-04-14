package com.example.masche_um_masche;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MaterialsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        createBottomNavigation(R.id.nav_materials);
    }
}
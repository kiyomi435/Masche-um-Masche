package com.example.masche_um_masche;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PatternsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patterns);
        createBottomNavigation(R.id.nav_patterns);
    }
}
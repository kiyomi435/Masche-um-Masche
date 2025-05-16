package com.example.masche_um_masche.ui.profile;

import android.os.Bundle;

import com.example.masche_um_masche.R;
import com.example.masche_um_masche.ui.BaseActivity;

public class ProfileActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        createBottomNavigation(R.id.nav_profile);
    }
}
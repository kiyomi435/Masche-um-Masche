package com.example.masche_um_masche;

import android.app.Activity;
import android.os.Bundle;

public class ProjectsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        createBottomNavigation(R.id.nav_projects);
    }
}
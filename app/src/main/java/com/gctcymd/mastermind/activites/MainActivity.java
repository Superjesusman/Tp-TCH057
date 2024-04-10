package com.gctcymd.mastermind.activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gctcymd.mastermind.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Construction du menu navigation
//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.settings);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //if not connected and item.getItemId() != R.id.home, pop up to connect!
        switch (item.getItemId()) {
            case R.id.home:
                return true;
            case R.id.game:
                startActivity(new Intent(getApplicationContext(),JeuActivity.class));
                return true;
            case R.id.history:
                //startActivity(new Intent(getApplicationContext(),HistoryActivity.class));
                return true;
            case R.id.settings:
                startActivity(new Intent(getApplicationContext(),ConfigurationActivity.class));
                return true;
        }
        return false;
    }
}
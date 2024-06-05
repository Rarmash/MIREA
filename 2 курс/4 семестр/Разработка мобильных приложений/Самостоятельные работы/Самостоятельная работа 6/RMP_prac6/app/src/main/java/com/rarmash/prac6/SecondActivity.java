package com.rarmash.prac6;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Главная");

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
            bottomNavigationView.setOnNavigationItemReselectedListener(item -> {
                if (item.getItemId() == R.id.home) {
                    actionBar.setTitle("Главная");
                } else if (item.getItemId() == R.id.settings) {
                    actionBar.setTitle("Настройки");
                } else if (item.getItemId() == R.id.notification) {
                    actionBar.setTitle("Уведомления");
                }
            });
        }
    }
}

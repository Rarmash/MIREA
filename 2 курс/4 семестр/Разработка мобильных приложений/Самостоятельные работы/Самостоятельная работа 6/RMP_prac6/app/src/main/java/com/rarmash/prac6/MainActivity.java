package com.rarmash.prac6;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public DrawerLayout drawer;
    public ActionBarDrawerToggle toggle;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(MainActivity.this, drawer, R.string.drawer_open, R.string.drawer_close);

        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }

        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int id = menuItem.getItemId();
            navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment_main);

            if (id == R.id.nav_master) {
                Toast.makeText(MainActivity.this, "1 clicked", Toast.LENGTH_SHORT).show();
                try {
                    navController.navigate(R.id.action_mainFragment1_mainFragment2);
                } catch (Exception e) {
                    navController.navigate(R.id.action_mainFragment1_self);
                }
            } else if (id == R.id.nav_next_activity) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                MainActivity.this.startActivity(intent);
            }
            drawer.closeDrawer(GravityCompat.START, false);
            return true;
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
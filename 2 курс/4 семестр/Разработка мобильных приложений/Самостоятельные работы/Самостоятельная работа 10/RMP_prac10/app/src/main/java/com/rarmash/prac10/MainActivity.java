package com.rarmash.prac10;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        EditText titleInput = findViewById(R.id.title_input);
        EditText yearInput = findViewById(R.id.year_input);
        Button saveButton = findViewById(R.id.save_button);
        Button deleteButton = findViewById(R.id.delete_button);
        Button findButton = findViewById(R.id.find_button);
        RecyclerView gamesList = findViewById(R.id.games_list);
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        List<Game> games = dbHelper.getAllGames();
        GameAdapter adapter = new GameAdapter((ArrayList<Game>) games);
        gamesList.setLayoutManager(new LinearLayoutManager(this));
        gamesList.setAdapter(adapter);
        saveButton.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String year = yearInput.getText().toString();
            if (dbHelper.addGame(new Game(0, title, year)))
            {
                games.add(new Game(0, title, year));
                adapter.notifyItemInserted(games.size() - 1);
                Toast.makeText(this, "Game saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to save game",
                        Toast.LENGTH_SHORT).show();
            }
        });
        deleteButton.setOnClickListener(v -> {
            String year = yearInput.getText().toString();
            if (dbHelper.deleteGame(year)) {
                int position = -1;
                for (int i = 0; i < games.size(); i++) {
                    if (games.get(i).getYearOfRelease().equals(year))
                    {
                        position = i;
                        games.remove(i);
                        break;
                    }
                }
                if (position != -1) {
                    adapter.notifyItemRemoved(position);
                    Toast.makeText(this, "Game deleted successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Game not found",
                            Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Failed to delete game",
                        Toast.LENGTH_SHORT).show();
            }
        });
        findButton.setOnClickListener(v -> {
            String year = yearInput.getText().toString();
            Game foundGame = dbHelper.findGame(year);
            if (foundGame != null) {
                titleInput.setText(foundGame.getTitle());
                yearInput.setText(foundGame.getYearOfRelease());
                Toast.makeText(this, "Game found: " +
                        foundGame.getTitle(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Game not found",
                        Toast.LENGTH_SHORT).show();
            }
        });
        Button updateButton = findViewById(R.id.update_button);
        updateButton.setOnClickListener(v -> {
            String oldYear = yearInput.getText().toString();
            String newTitle = titleInput.getText().toString();
            String newYear = yearInput.getText().toString();
            if (dbHelper.updateGame(oldYear, newTitle,
                    newYear)) {
                Toast.makeText(this, "Game updated successfully!", Toast.LENGTH_SHORT).show();
                // Обновляем список и адаптер
                refreshGamesList(dbHelper, games, adapter,
                        gamesList);
            } else {
                Toast.makeText(this, "Failed to update game",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void refreshGamesList(DatabaseHelper dbHelper,
                                  List<Game> games, GameAdapter adapter, RecyclerView
                                             gamesList) {
        games = dbHelper.getAllGames();
        adapter = new GameAdapter((ArrayList<Game>) games);
        gamesList.setAdapter(adapter);
    }
}

////Shared preferences
//public class MainActivity extends AppCompatActivity {
//
//    private TextView textView;
//    private SharedPreferences sharedPref;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        textView = findViewById(R.id.textView);
//        sharedPref = getPreferences(MODE_PRIVATE);
//
//        Button saveButton = findViewById(R.id.saveButton);
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString("user_name", "Имя_пользователя");
//                editor.apply();
//                Toast.makeText(MainActivity.this, "Имя сохранено", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Button getButton = findViewById(R.id.getButton);
//        getButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userName = sharedPref.getString("user_name", "Имя_по_умолчанию");
//                textView.setText("Имя пользователя: " + userName);
//                Toast.makeText(MainActivity.this, "Имя получено", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        Button changeButton = findViewById(R.id.changeButton);
//        changeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                builder.setTitle("Изменение имени");
//
//                final EditText input = new EditText(MainActivity.this);
//                input.setInputType(InputType.TYPE_CLASS_TEXT);
//                builder.setView(input);
//
//                builder.setPositiveButton("OK", (dialog, which) -> {
//                    String newName = input.getText().toString();
//                    SharedPreferences.Editor editor = sharedPref.edit();
//                    editor.putString("user_name", newName);
//                    editor.apply();
//                    Toast.makeText(MainActivity.this, "Имя изменено", Toast.LENGTH_SHORT).show();
//                });
//
//                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
//
//                builder.show();
//            }
//        });
//
//        Button deleteButton = findViewById(R.id.deleteButton);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.remove("user_name");
//                editor.apply();
//                Toast.makeText(MainActivity.this, "Имя удалено", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
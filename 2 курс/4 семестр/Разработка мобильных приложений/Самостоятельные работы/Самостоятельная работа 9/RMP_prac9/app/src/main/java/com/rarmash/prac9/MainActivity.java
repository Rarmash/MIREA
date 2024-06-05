package com.rarmash.prac9;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    //используется для сохранения и восстановления имени файла
    private static final String FILENAME_KEY = "filename";

    //используется для сохранения и восстановления содержимого файла
    private static final String FILECONTENT_KEY = "filecontent";

    //используется для сохранения и восстановления текста поля fileContentField
    private static final String FILECONTENTFIELD_KEY = "filecontentfield";

    private TextInputEditText fileName;
    private TextInputEditText fileContent;
    private TextView fileContentField;

    private String filename;
    private String fileContents;
    private String fileContentFieldText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fileName = findViewById(R.id.fileName);
        fileContent = findViewById(R.id.fileContent);
        fileContentField = findViewById(R.id.fileContentField);

        Button saveButton = findViewById(R.id.saveButton);
        Button readButton = findViewById(R.id.readButton);
        Button deleteButton = findViewById(R.id.deleteButton);
        Button putButton = findViewById(R.id.addButton);

        // Восстановление состояния приложения, если оно было сохранено
        if (savedInstanceState != null) {
            filename = savedInstanceState.getString(FILENAME_KEY);
            fileContents = savedInstanceState.getString(FILECONTENT_KEY);
            fileContentFieldText = savedInstanceState.getString(FILECONTENTFIELD_KEY);

            fileName.setText(filename);
            fileContent.setText(fileContents);
            fileContentField.setText(fileContentFieldText);
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileName.getText().toString().matches("") && fileContent.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Оба поля пустые", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fileName.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Введите название файла", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fileContent.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Введите содержимое файла", Toast.LENGTH_SHORT).show();
                    return;
                }


                String filename = fileName.getText().toString(); // Название файла
                String fileContents = fileContent.getText().toString(); // Текст внутри файла

                // Получение контекста активности
                Context context = getApplicationContext();

                // Открываем поток для записи. Если документ не создан, то он будет создан автоматически
                try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
                    // Записываем текст в файл, переведя его в массив байт
                    fos.write(fileContents.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Сохранено", Toast.LENGTH_SHORT).show();
            }
        });


        readButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = fileName.getText().toString(); // Получаем имя файла из текстового поля

                if (filename.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Имя файла пустое", Toast.LENGTH_SHORT).show();
                    return;
                }

                File file = new File(getFilesDir(), filename); // Используем getFilesDir() для получения каталога файлов приложения

                if (file.exists()) {
                    StringBuilder text = new StringBuilder();
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = br.readLine()) != null) {
                            text.append(line);
                            text.append('\n');
                        }
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String fileData = text.toString();

                    fileContentField.setText(fileData);

                    Toast.makeText(getApplicationContext(), "Прочитано", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Файла не существует", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = fileName.getText().toString(); // Получаем имя файла из текстового поля

                if (filename.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Имя файла пустое", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Вы уверены, что хотите удалить файл '" + filename + "'?");
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File file = new File(getFilesDir(), filename); // Получаем файл по имени

                        if (file.exists()) {
                            if (file.delete()) { // Удаляем файл
                                Toast.makeText(getApplicationContext(), "Файл '" + filename + "' удален", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Ошибка удаления файла", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Файла не существует", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("Нет", null);
                builder.show();
            }
        });

        putButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = fileName.getText().toString(); // Получаем имя файла из текстового поля
                String fileContents = fileContent.getText().toString(); // Получаем содержимое для добавления

                if (filename.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Имя файла пустое", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (fileContents.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Содержимое файла пустое", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Получение контекста активности
                Context context = getApplicationContext();

                // Открываем поток для записи. Если документ не создан, то он будет создан автоматически
                try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_APPEND)) {
                    // Записываем текст в файл, переведя его в массив байт
                    fos.write(fileContents.getBytes());
                    fos.write("\n".getBytes()); // Добавляем новую строку после добавляемого содержимого
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "Информация добавлена в файл", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Сохранение состояния
        filename = fileName.getText().toString();
        fileContents = fileContent.getText().toString();
        fileContentFieldText = fileContentField.getText().toString();
        outState.putString(FILENAME_KEY, filename);
        outState.putString(FILECONTENT_KEY, fileContents);
        outState.putString(FILECONTENTFIELD_KEY, fileContentFieldText);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Восстановление состояния
        filename = savedInstanceState.getString(FILENAME_KEY);
        fileContents = savedInstanceState.getString(FILECONTENT_KEY);
    }
}
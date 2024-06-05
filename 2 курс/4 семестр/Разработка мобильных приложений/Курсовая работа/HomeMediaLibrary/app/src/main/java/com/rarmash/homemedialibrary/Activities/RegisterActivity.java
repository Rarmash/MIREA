package com.rarmash.homemedialibrary.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rarmash.homemedialibrary.Constant;
import com.rarmash.homemedialibrary.R;
import com.rarmash.homemedialibrary.models.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword, editTextUsername;
    Button buttonReg;
    ProgressBar progressBar;
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextUsername = findViewById(R.id.username);
        buttonReg = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.loginNow);

        editTextUsername.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        editTextEmail.setFilters(new InputFilter[]{new InputFilter.LengthFilter(256)});
        editTextPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference(Constant.USERS);

                uploadUser();
            }
        });
    }


    private void uploadUser() {
        boolean superUser = false;
        String email = editTextEmail.getText().toString();
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (email.equals(Constant.ME)) {
            superUser = true;
        }
        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Поле не может быть пустым");
            editTextEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Поле не может быть пустым");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Поле не может быть пустым");
            editTextPassword.requestFocus();
            return;
        }

        if (username.length() < 6) {
            editTextUsername.setError("Длина имени должна быть не менее 6 символов");
            editTextUsername.requestFocus();
            return;
        }
        if (password.length() < 6) {
            editTextPassword.setError("Длина пароля должна быть не менее 6 символов");
            editTextPassword.requestFocus();
            return;
        }

        // Проверяем наличие пробела в пароле
        if (password.contains(" ")) {
            editTextPassword.setError("Пароль не может содержать пробелы");
            editTextPassword.requestFocus();
            return;
        }
        if (username.contains("\n") || username.contains(".") || username.contains("$") || username.contains("[")
                || username.contains("]") || username.contains("#") || username.contains("%") || username.contains("\\") || username.contains("/")) {
            editTextUsername.setError("Поле содержит запрещенные символы: '.', '$', '[', ']', '#', '%', '\\', '/', '\\n'");
            editTextUsername.requestFocus();
            return;
        }
        if (email.contains("\n") || email.contains(" ")) {
            editTextEmail.setError("Поле имеет запрещенный символ ('\\n', '␣')");
            editTextEmail.requestFocus();
            return;
        }
        if (!isValidEmail(email)) {
            editTextEmail.setError("Неверный формат адреса электронной почты");
            editTextEmail.requestFocus();
            return;
        }


        password = hashPassword(password);

        reference = FirebaseDatabase.getInstance().getReference(Constant.USERS);
        Query checkUserDB = reference.orderByChild(Constant.USERNAME).equalTo(username);

        User user = new User(email, username, password, superUser);
        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    editTextUsername.setError("Пользователь уже существует");
                } else {
                    Query checkUserEmail = reference.orderByChild(Constant.EMAIL).equalTo(email);

                    checkUserEmail.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                editTextEmail.setError("Пользователь с такой почтой уже существует");
                            } else {

                                reference.child(username).setValue(user);
                                Toast.makeText(RegisterActivity.this, "Пользователь добавлен", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email.matches(emailPattern);
    }
}
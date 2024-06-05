package com.rarmash.homemedialibrary.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginActivity extends AppCompatActivity {
    TextInputEditText editTextLogin, editTextPassword;
    Button buttonLogin;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        String savedName = sharedPreferences.getString("name", null);
        if (savedName != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }


        editTextLogin = findViewById(R.id.loginName);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        textView = findViewById(R.id.registerNow);

        editTextLogin.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        editTextPassword.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {

                } else {
                    checkUser();
                }
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public Boolean validateUsername() {
        String val = editTextLogin.getText().toString();
        if (val.isEmpty()) {
            editTextLogin.setError("Поле имени не может быть пустым");
            return false;
        } else {
            editTextLogin.setError(null);
            return true;
        }
    }

    public Boolean validatePassword() {
        String val = editTextPassword.getText().toString();
        if (val.isEmpty()) {
            editTextPassword.setError("Поле пароля не может быть пустым");
            return false;
        } else {
            editTextPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUserName = editTextLogin.getText().toString().trim();
        String userPassword = hashPassword(editTextPassword.getText().toString().trim());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Constant.USERS);
        Query checkUserDB = reference.orderByChild(Constant.USERNAME).equalTo(userUserName);

        checkUserDB.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    editTextLogin.setError(null);
                    String passwordFromDB = snapshot.child(userUserName).child(Constant.PASSWORD).getValue(String.class);
                    if (passwordFromDB.equals(userPassword)) {
                        editTextLogin.setError(null);

                        String nameFromDB = snapshot.child(userUserName).child(Constant.USERNAME).getValue(String.class);
                        String emailFromDB = snapshot.child(userUserName).child(Constant.EMAIL).getValue(String.class);
                        boolean isSuperUser = snapshot.child(userUserName).child(Constant.SUPERUSER).getValue(Boolean.class);

                        SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", nameFromDB);
                        editor.putString("email", emailFromDB);
                        editor.putBoolean("isSuperUser", isSuperUser);
                        editor.apply();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        intent.putExtra(Constant.USERNAME, nameFromDB);
                        intent.putExtra(Constant.EMAIL, emailFromDB);
                        intent.putExtra(Constant.SUPERUSER, isSuperUser);
                        intent.putExtra(Constant.PASSWORD, passwordFromDB);

                        startActivity(intent);
                        finish();
                    } else {
                        editTextPassword.setError("Указан неверный логин/пароль");
                        editTextPassword.requestFocus();
                    }
                } else {
                    editTextLogin.setError("Пользователь не найден");
                    editTextPassword.requestFocus();
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
}
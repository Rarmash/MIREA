package com.rarmash.homemedialibrary.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.rarmash.homemedialibrary.Activities.LoginActivity;
import com.rarmash.homemedialibrary.R;

public class ProfileFragment extends Fragment {
    TextView role, userName, userEmail;
    FloatingActionButton fab;
    SharedPreferences sharedPref;
    boolean isSuperUser;
    String name, email;
    Button exit;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        exit = rootView.findViewById(R.id.logout);
        fab = rootView.findViewById(R.id.fab);
        role = rootView.findViewById(R.id.role);
        userName = rootView.findViewById(R.id.userName);
        userEmail = rootView.findViewById(R.id.userEmail);

        sharedPref = getContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);

        name = sharedPref.getString("name", null);
        email = sharedPref.getString("email", null);
        isSuperUser = sharedPref.getBoolean("isSuperUser", false);

        userName.setText("Имя пользователя: "+ name);
        userEmail.setText("Электронная почта: " + email);

        if (isSuperUser) {
            role.setText("Профиль (администратор)");
            fab.setVisibility(View.VISIBLE);
        } else {
            role.setText("Профиль");
            fab.setVisibility(View.GONE);
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadFragment uploadFragment = new UploadFragment();
                ((AppCompatActivity) getContext()).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, uploadFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("LoginPrefs", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
}
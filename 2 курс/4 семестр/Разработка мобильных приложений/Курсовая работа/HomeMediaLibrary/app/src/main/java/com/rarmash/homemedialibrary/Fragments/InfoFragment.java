package com.rarmash.homemedialibrary.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.rarmash.homemedialibrary.R;

public class InfoFragment extends Fragment {
    TextView creatorText, usingDesc, personalDesc;
    ImageView githubBtn;
    SharedPreferences sharedPref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        creatorText = rootView.findViewById(R.id.creator);
        githubBtn = rootView.findViewById(R.id.github_btn);
        usingDesc = rootView.findViewById(R.id.usingDesc);
        personalDesc = rootView.findViewById(R.id.personalDesc);

        sharedPref = getContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE);
        boolean isSuperUser = sharedPref.getBoolean("isSuperUser", false);

        githubBtn.setOnClickListener(view -> {
            String url = "https://github.com/Rarmash/HomeMediaLibrary";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
        creatorText.setText("Разработчик:\nГришин Андрей (ИКБО-11-22)");
        usingDesc.setText("На главной странице отображается список медиа с соответствующими постерами, при нажатии на которые будет открыта страница с подробной информацией о данном медиа");

        if(isSuperUser){
            personalDesc.setText("Будучи администратором, вам доступно:\n1) Добавление медиа в БД на странице профиля\n2) Редактирование данных о медиа на его странице");
        } else {
            personalDesc.setText("Вы обычный пользователь.");
        }
        return rootView;
    }
}
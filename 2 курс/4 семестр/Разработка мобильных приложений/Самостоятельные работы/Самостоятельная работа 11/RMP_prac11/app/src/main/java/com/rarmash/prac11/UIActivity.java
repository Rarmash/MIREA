package com.rarmash.prac11;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class UIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uiactivity);

        ImageView imageView = findViewById(R.id.imageview); // Нахождение объекта ImageView внутри макета по его ID

        // Создание анимации вращения изображения с помощью ObjectAnimator
        ObjectAnimator rotateAnim = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotateAnim.setDuration(2000); // Установка длительности анимации в миллисекундах
        rotateAnim.setRepeatCount(ObjectAnimator.INFINITE); // Установка количества повторений анимации (бесконечно)
        rotateAnim.setRepeatMode(ObjectAnimator.RESTART); // Установка режима повтора анимации (с начала)
        rotateAnim.start(); // Запуск анимации
    }
}

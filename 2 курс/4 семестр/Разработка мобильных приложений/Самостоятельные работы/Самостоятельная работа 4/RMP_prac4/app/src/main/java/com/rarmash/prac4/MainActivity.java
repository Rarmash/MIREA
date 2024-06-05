package com.rarmash.prac4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        TextView textView = new TextView(this);
        textView.setText("Hello Android!");
        textView.setTextSize(30);
        textView.setBackgroundColor(0xffe8eaf6);
        // установка gravity
        textView.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        // установка высоты и ширины
        ConstraintLayout.LayoutParams layoutParams = new
                ConstraintLayout.LayoutParams
                (ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, 200);
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.rightToRight = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.bottomToBottom =
                ConstraintLayout.LayoutParams.PARENT_ID;
        textView.setLayoutParams(layoutParams);
        constraintLayout.addView(textView);
        setContentView(constraintLayout);
    }
}

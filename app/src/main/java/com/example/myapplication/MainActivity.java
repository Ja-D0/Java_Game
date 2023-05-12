package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    protected static int score = 0;
    protected static int scoreOnClick = 1;
    protected static boolean autoClick = false;

    protected static boolean autoClickOn = false;
    private TextView scoreTextView;
    private ImageButton clickButton;
    private Button shopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SaverAndLoader.init(this);
        SaverAndLoader.load();

        Toast.makeText(this, " " + autoClick + "", Toast.LENGTH_SHORT).show();

        Animation zoomAnimationOn = AnimationUtils.loadAnimation(this, R.anim.zoom_on);

        scoreTextView = findViewById(R.id.scoreTextView);
        clickButton = findViewById(R.id.clickButton);
        shopButton = findViewById(R.id.shopButton);

        scoreTextView.setText(getScoreString());

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.startAnimation(zoomAnimationOn);
                score += scoreOnClick;
                scoreTextView.setText(getScoreString());
            }
        });
        shopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShop();
            }
        });
        autoClickStatus();
    }

    private void openShop() {
        Intent intent = new Intent(this, ShopActivity.class);
        intent.putExtra("score", scoreTextView.getText().toString());
        intent.putExtra("autoClick", Boolean.toString(autoClick));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        score = data.getIntExtra("score", 0);
        scoreOnClick += data.getIntExtra("clickUpgradeValue", 0);
        autoClick = data.getBooleanExtra("autoClick", false);
        scoreTextView.setText(getScoreString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", score);
        outState.putInt("scoreOnClick", scoreOnClick);
        outState.putBoolean("autoClick", autoClick);
        outState.putBoolean("autoClickOn", autoClickOn);
    }


    protected void onPause() {
        super.onPause();
        SaverAndLoader.save();
    }

    protected void onResume() {
        super.onResume();
        if (autoClick && !autoClickOn) {
            autoClickStatus();
        }
        Toast.makeText(this, "" + autoClick + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            score = savedInstanceState.getInt("score");
            scoreOnClick = savedInstanceState.getInt("scoreOnClick");
            autoClick = savedInstanceState.getBoolean("autoClick");
            autoClickOn = savedInstanceState.getBoolean("autoClickOn");
            scoreTextView.setText(getScoreString());
        }
    }

    private String getScoreString() {
        return Integer.toString(score);
    }

    private void autoClickStatus() {
        if (autoClick) {
            Handler handler = new Handler();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    clickButton.performClick();
                    handler.postDelayed(this, 1000);
                }
            };
            autoClickOn = true;
            handler.postDelayed(runnable, 1000);
        }
    }
}
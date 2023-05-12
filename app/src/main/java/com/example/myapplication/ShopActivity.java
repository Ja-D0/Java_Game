package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;

public class ShopActivity extends AppCompatActivity implements OnItemClickListener {

    protected static int upgradeClickCost = 10;
    protected static int autoClickCost = 10;
    protected static int score;
    protected static boolean autoClick;
    protected static int clickUpgradeValue = 1;
    protected static Intent intent;
    protected ArrayList<ShopItem> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        SaverAndLoader.load();

        intent = getIntent();
        score = Integer.parseInt(intent.getStringExtra("score"));
        autoClick = Boolean.parseBoolean(intent.getStringExtra("autoClick"));

        item = new ArrayList<ShopItem>();
        item.add(new ShopItem(1, "Улучшить клик", upgradeClickCost, getClickUpgradeValue()));
        item.add(new ShopItem(2, "Автоклик", autoClickCost, "Автоклик"));
        ListView shop = findViewById(R.id.item_list);
        ShopAdapter adapter = new ShopAdapter(this, R.layout.shop_item, item, this);
        ;
        shop.setAdapter(adapter);

        Button button = findViewById(R.id.back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaverAndLoader.save();
                sendIntentToMainActivity();
                finish();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("upgradeCost", upgradeClickCost);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    protected void onPause() {
        super.onPause();
        SaverAndLoader.save();
        sendIntentToMainActivity();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }

    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    protected void sendIntentToMainActivity() {
        intent.putExtra("score", score);
        intent.putExtra("autoClick", autoClick);
        setResult(RESULT_OK, intent);
    }

    protected String getUpgradeClickCost() {
        return Integer.toString(upgradeClickCost);
    }

    protected String getClickUpgradeValue() {
        return Integer.toString(clickUpgradeValue);
    }

    @Override
    public void onItemClick(ShopItem item) {
        if (score >= upgradeClickCost) {
            score -= upgradeClickCost;
            upgradeClickCost *= 2;
            intent.putExtra("clickUpgradeValue", clickUpgradeValue);
            clickUpgradeValue++;
            item.setCost(upgradeClickCost);
            item.setUpgradeValue(getClickUpgradeValue());
        } else {
            Toast.makeText(this, "У вас недостаточно монет", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void autoClickOn(ShopItem item) {
        if ((score >= autoClickCost) && (autoClick == false)) {
            score -= autoClickCost;
            autoClickCost = 0;
            autoClick = true;
            item.setCost(autoClickCost);
        } else if ((score <= autoClickCost)){
            Toast.makeText(this, "У вас недостаточно монет", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Автоклик уже куплен", Toast.LENGTH_SHORT).show();
        }
    }
}

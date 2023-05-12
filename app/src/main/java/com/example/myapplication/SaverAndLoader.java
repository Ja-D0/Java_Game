package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SaverAndLoader extends AppCompatActivity {
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    protected static void init (Context context){
        if (preferences == null){
            preferences = context.getSharedPreferences(context.getPackageName(), MODE_PRIVATE);
            editor = preferences.edit();
        }
    }

    protected static void save(){
        editor.putInt("score", MainActivity.score);
        editor.putInt("scoreOnClick", MainActivity.scoreOnClick);
        editor.putBoolean("autoClick", MainActivity.autoClick);
        editor.putBoolean("autoClickOn", MainActivity.autoClickOn);
        editor.putInt("upgradeCost", ShopActivity.upgradeClickCost);
        editor.putInt("clickUpgradeValue", ShopActivity.clickUpgradeValue);
        editor.putInt("autoClickCost", ShopActivity.autoClickCost);
        editor.apply();
    }
    protected static void load(){
        MainActivity.score = preferences.getInt("score" , MainActivity.score);
        MainActivity.scoreOnClick = preferences.getInt("scoreOnClick" , MainActivity.scoreOnClick);
        MainActivity.autoClick = preferences.getBoolean("autoClick", MainActivity.autoClick);
        MainActivity.autoClickOn = preferences.getBoolean("autoClickOn", MainActivity.autoClickOn);
        ShopActivity.upgradeClickCost = preferences.getInt("upgradeCost" , ShopActivity.upgradeClickCost);
        ShopActivity.clickUpgradeValue = preferences.getInt("clickUpgradeValue" , ShopActivity.clickUpgradeValue);
        ShopActivity.autoClickCost = preferences.getInt("autoClickCost", ShopActivity.autoClickCost);
    }
    protected static void clearData(){
        editor.clear();
    }

}

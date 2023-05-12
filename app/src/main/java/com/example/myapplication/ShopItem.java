package com.example.myapplication;

import android.view.View;

public class ShopItem {
    private String description;
    private int cost;
    private String upgradeValue;
    private int id;

    ShopItem(int id,String description, int cost, String upgradeValue) {
        setId(id);
        setCost(cost);
        setDescription(description);
        setUpgradeValue(upgradeValue);


    }

    protected String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    protected int getCost() {
        return this.cost;
    }
    protected void setCost(int cost) {
        this.cost = cost;
    }

    protected int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getUpgradeValue() {
        return upgradeValue;
    }

    public void setUpgradeValue(String upgradeValue) {
        this.upgradeValue = upgradeValue;
    }
}

package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class ViewHolder {
    final Button buyButton;
    final TextView description, cost, upgradeValue;

    ViewHolder(View view) {
        upgradeValue = view.findViewById(R.id.upgradeValue);
        buyButton = view.findViewById(R.id.buyButton);
        description = view.findViewById(R.id.description);
        cost = view.findViewById(R.id.cost);
    }

}

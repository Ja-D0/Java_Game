package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ShopAdapter extends ArrayAdapter<ShopItem> {
    private LayoutInflater inflater;
    private int layout;
    private ViewHolder viewHolder;
    private ArrayList<ShopItem> itemList;
    private OnItemClickListener listener;

    ShopAdapter(Context context, int resource, ArrayList<ShopItem> items, OnItemClickListener listener) {
        super(context, resource, items);
        this.inflater = LayoutInflater.from(context);
        this.layout = resource;
        this.itemList = items;
        this.listener = listener;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
            this.viewHolder = new ViewHolder(convertView);
            convertView.setTag(this.viewHolder);
        } else {
            this.viewHolder = (ViewHolder) convertView.getTag();
        }
        final ShopItem item = itemList.get(position);
        this.viewHolder.description.setText(item.getDescription());
        this.viewHolder.upgradeValue.setText("+" + item.getUpgradeValue());
        this.viewHolder.cost.setText(Integer.toString(item.getCost()));
        this.viewHolder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (item.getId()){
                    case (1):{
                        listener.onItemClick(item);
                        notifyDataSetChanged();
                        break;
                        }
                    case (2):{
                        listener.autoClickOn(item);
                        notifyDataSetChanged();
                        break;
                    }
                }
            }
        });

        return convertView;
    }
}



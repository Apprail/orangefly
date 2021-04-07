package com.example.orangefly.ui.account;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.orangefly.R;

import java.util.ArrayList;

public class CustomAccountListView extends BaseAdapter implements ListAdapter {

    private final Context context;
    private final ArrayList<AccountListItems> itemsArrayList;

    public CustomAccountListView(Context context, ArrayList<AccountListItems> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;

    }

    @Override
    public int getCount() {
        return itemsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemsArrayList.indexOf(getItem(position));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ((Activity)context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.account_list,null);
            holder = new ViewHolder();

            holder.icon = (ImageView) view.findViewById(R.id.icon);
            holder.title = (TextView) view.findViewById(R.id.title);
            holder.subtitle = (TextView) view.findViewById(R.id.subtitle);

            view.setTag(holder);
        }else{
            holder=(ViewHolder)view.getTag();
        }
        holder.icon.setImageResource(itemsArrayList.get(position).getIcon());
        holder.title.setText(itemsArrayList.get(position).getParent_text());
        holder.subtitle.setText(itemsArrayList.get(position).getSub_text());

        return view;
    }

    public static class ViewHolder{

        public ImageView icon;
        public TextView title;
        public TextView subtitle;

    }
}  
package com.example.orangefly.ui.address;

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
import com.example.orangefly.ui.address.AddressListItems;
import com.example.orangefly.ui.address.CustomAddressListView;

import java.util.ArrayList;

public class CustomAddressListView extends BaseAdapter implements ListAdapter {

    private final Context context;
    private final ArrayList<AddressListItems> itemsArrayList;

    public CustomAddressListView(Context context, ArrayList<AddressListItems> itemsArrayList) {
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
        final CustomAddressListView.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ((Activity)context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.address_list,null);
            holder = new CustomAddressListView.ViewHolder();

            holder.title = (TextView) view.findViewById(R.id.address_type_title);

            view.setTag(holder);
        }else{
            holder=(CustomAddressListView.ViewHolder)view.getTag();
        }
        holder.title.setText(itemsArrayList.get(position).getParent_text());

        return view;
    }

    public static class ViewHolder{
        public TextView title;
    }
}  

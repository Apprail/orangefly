package com.example.orangefly.ui.cart;

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
import com.example.orangefly.ui.account.AccountListItems;
import com.example.orangefly.ui.account.CustomAccountListView;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter implements ListAdapter {

    private final Context context;
    private final ArrayList<CartItems> itemsArrayList;

    public CartAdapter(Context context, ArrayList<CartItems> itemsArrayList) {
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
        final CartAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ((Activity)context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_cart_list_items,null);
            holder = new CartAdapter.ViewHolder();

            holder.selected_image = (ImageView) view.findViewById(R.id.selected_image);
            holder.selectedProductTitle = (TextView) view.findViewById(R.id.selectedProductTitle);
            holder.selectedProductPrice = (TextView) view.findViewById(R.id.price);
            holder.selectedProductQty = (TextView) view.findViewById(R.id.qty);
            holder.selectedProductTotal = (TextView) view.findViewById(R.id.totalprice);

            view.setTag(holder);
        }else{
            holder=(CartAdapter.ViewHolder)view.getTag();
        }
        holder.selected_image.setImageBitmap(itemsArrayList.get(position).getIcon());
        holder.selectedProductTitle.setText(itemsArrayList.get(position).getTitle_text());
        holder.selectedProductPrice.setText(String.valueOf(itemsArrayList.get(position).getPrice()));
        holder.selectedProductQty.setText(String.valueOf(itemsArrayList.get(position).getQty()));
        holder.selectedProductTotal.setText(String.valueOf(itemsArrayList.get(position).getTotal()));

        return view;
    }
    public static class ViewHolder{

        public ImageView selected_image;
        public TextView selectedProductTitle;
        public TextView selectedProductPrice;
        public TextView selectedProductQty;
        public TextView selectedProductTotal;

    }
}

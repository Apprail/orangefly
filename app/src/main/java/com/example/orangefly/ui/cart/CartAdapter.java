package com.example.orangefly.ui.cart;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.orangefly.R;

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
            holder.cart_plus_img = (ImageView) view.findViewById(R.id.plus);
            holder.cart_minus_img = (ImageView) view.findViewById(R.id.minus);
            holder.delete_item = (ImageView) view.findViewById(R.id.deleteitem);
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


        int selected_price = Integer.parseInt(String.valueOf(holder.selectedProductPrice.getText()));
        int selected_qty = Integer.parseInt(String.valueOf(holder.selectedProductQty.getText()));

        holder.selectedProductTotal.setText(String.valueOf(priceCalculation(selected_price, selected_qty)));

        holder.cart_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(holder.selectedProductQty.getText()));
                if(count>0){
                    count++;
                    holder.selectedProductQty.setText(String.valueOf(count));
                    holder.selectedProductTotal.setText(String.valueOf(priceCalculation(selected_price, count)));
                }
            }
        });

        holder.cart_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(holder.selectedProductQty.getText()));
                if(count>1) {
                    count--;
                    holder.selectedProductQty.setText(String.valueOf(count));
                    holder.selectedProductTotal.setText(String.valueOf(priceCalculation(selected_price, count)));
                }
            }
        });

        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemsArrayList.remove(position);
                notifyDataSetChanged();
            }
        });
        return view;
    }

    private int priceCalculation(int price, int qty){
        return price * qty;
    }

    public static class ViewHolder{

        public ImageView selected_image, cart_plus_img, cart_minus_img, delete_item;
        public TextView selectedProductTitle;
        public TextView selectedProductPrice;
        public TextView selectedProductQty;
        public TextView selectedProductTotal;

    }
}

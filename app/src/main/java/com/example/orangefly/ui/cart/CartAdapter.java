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

import com.example.orangefly.DatabaseHelper;
import com.example.orangefly.R;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter implements ListAdapter {

    private final Context context;
    private final ArrayList<CartItems> itemsArrayList;
    public TextView cart_total;
    int selected_price = 0;
    int selected_qty = 0;
    int id = 0;
    int total = 0;

    public CartAdapter(Context context, ArrayList<CartItems> itemsArrayList, TextView cart_total) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
        this.cart_total = cart_total;
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
            LayoutInflater inflater = (LayoutInflater) ((Activity) context).getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_cart_list_items, null);
            holder = new CartAdapter.ViewHolder();

            holder.selected_image = (ImageView) view.findViewById(R.id.selected_image);
            holder.cart_plus_img = (ImageView) view.findViewById(R.id.plus);
            holder.cart_minus_img = (ImageView) view.findViewById(R.id.minus);
            holder.delete_item = (ImageView) view.findViewById(R.id.deleteitem);
            holder.selectedProductTitle = (TextView) view.findViewById(R.id.selectedProductTitle);
            holder.selectedProductPrice = (TextView) view.findViewById(R.id.price);
            holder.selectedProductQty = (TextView) view.findViewById(R.id.qty);
            holder.selectedProductTotal = (TextView) view.findViewById(R.id.totalprice);
            holder.cart_db_id = (TextView) view.findViewById(R.id.cart_db_id);

            view.setTag(holder);
        } else {
            holder = (CartAdapter.ViewHolder) view.getTag();
        }
        holder.selected_image.setImageBitmap(itemsArrayList.get(position).getImg());
        holder.selectedProductTitle.setText(itemsArrayList.get(position).getTitle_text());
        holder.selectedProductPrice.setText(String.valueOf(itemsArrayList.get(position).getPrice()));
        holder.selectedProductQty.setText(String.valueOf(itemsArrayList.get(position).getQty()));
        holder.cart_db_id.setText(String.valueOf(itemsArrayList.get(position).getId()));


        selected_price = Integer.parseInt(itemsArrayList.get(position).getPrice());
        selected_qty = Integer.parseInt(itemsArrayList.get(position).getQty());
        id = Integer.parseInt(itemsArrayList.get(position).getId());
        total = priceCalculation(selected_price, selected_qty);

        holder.selectedProductTotal.setText(String.valueOf(total));
        updateCartTotal(context);


        holder.cart_plus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_qty = Integer.parseInt(String.valueOf(holder.selectedProductQty.getText()));
                if (selected_qty > 0) {

                    id = Integer.parseInt(String.valueOf(holder.cart_db_id.getText()));
                    selected_price = Integer.parseInt(String.valueOf(holder.selectedProductPrice.getText()));

                    selected_qty++;
                    total = priceCalculation(selected_price, selected_qty);

                    holder.selectedProductQty.setText(String.valueOf(selected_qty));
                    holder.selectedProductTotal.setText(String.valueOf(priceCalculation(selected_price, selected_qty)));

                    updateDB(context, id, selected_qty, selected_price, total, false);
                    updateCartTotal(context);
                }

            }
        });

        holder.cart_minus_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_qty = Integer.parseInt(String.valueOf(holder.selectedProductQty.getText()));
                if (selected_qty > 1) {
                    id = Integer.parseInt(String.valueOf(holder.cart_db_id.getText()));
                    selected_price = Integer.parseInt(String.valueOf(holder.selectedProductPrice.getText()));

                    selected_qty--;
                    total = priceCalculation(selected_price, selected_qty);

                    holder.selectedProductQty.setText(String.valueOf(selected_qty));
                    holder.selectedProductTotal.setText(String.valueOf(priceCalculation(selected_price, selected_qty)));

                    updateDB(context, id, selected_qty, selected_price, total, false);
                    updateCartTotal(context);
                }

            }
        });

        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = Integer.parseInt(String.valueOf(holder.cart_db_id.getText()));
                selected_price = Integer.parseInt(String.valueOf(holder.selectedProductPrice.getText()));
                selected_qty = Integer.parseInt(String.valueOf(holder.selectedProductQty.getText()));
                updateDB(context, id, selected_qty, selected_price, total, true);
                updateCartTotal(context);
                itemsArrayList.remove(position);
                notifyDataSetChanged();

            }
        });
        return view;
    }

    public void updateDB(Context context, int id, int qty, int price, int total, Boolean delete) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        if (delete) {
            databaseHelper.deleteCartRow(id);
        } else {
            databaseHelper.updateEntry(id, qty, price, total);
        }
    }

    private int priceCalculation(int price, int qty) {
        return price * qty;
    }

    public void updateCartTotal(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        int getCartTotal = databaseHelper.getCartTotal();
        cart_total.setText(String.valueOf(getCartTotal));
    }

    public static class ViewHolder {

        public ImageView selected_image, cart_plus_img, cart_minus_img, delete_item;
        public TextView selectedProductTitle, cart_db_id;
        public TextView selectedProductPrice;
        public TextView selectedProductQty;
        public TextView selectedProductTotal;

    }
}

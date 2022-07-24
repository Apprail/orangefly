package com.example.orangefly.ui.cart;

import android.graphics.Bitmap;

public class CartItems {
    private Bitmap icon;
    private String title_text;
    private int price;
    private int qty;
    private int total;


    public CartItems(Bitmap icon, String title_text, int price, int qty, int total) {
        this.icon = icon;
        this.title_text = title_text;
        this.price = price;
        this.qty = qty;
        this.total = total;
    }

    public String getTitle_text() {
        return title_text;
    }

    public void setTitle_text(String title_text) {
        this.title_text = title_text;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

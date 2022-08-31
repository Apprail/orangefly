package com.example.orangefly.ui.cart;

import android.graphics.Bitmap;

public class CartItems {
    private String id;
    private Bitmap img;
    private String title_text;
    private String price;
    private String qty;
    private String total;


    public CartItems(String id, Bitmap img, String title_text, String price, String qty, String total) {
        this.id = id;
        this.img = img;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}

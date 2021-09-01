package com.example.orangefly.ui.address;

public class AddressListItems {
    private String address_type_text;

    public AddressListItems(String address_type_text) {
        this.address_type_text = address_type_text;
    }

    public String getParent_text(){
        return address_type_text;
    }

    public void setParent_text(){
        this.address_type_text = address_type_text;
    }

}

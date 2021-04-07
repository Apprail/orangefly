package com.example.orangefly.ui.account;

public class AccountListItems {
    private int icon;
    private String parent_text;
    private String sub_text;

    public AccountListItems(int icon, String parent_text, String sub_text) {
        this.icon = icon;
        this.parent_text = parent_text;
        this.sub_text = sub_text;
    }


    public int getIcon(){
        return icon;
    }

    public void setIcon(){
        this.icon = icon;
    }

    public String getParent_text(){
        return parent_text;
    }

    public void setParent_text(){
        this.parent_text = parent_text;
    }

    public String getSub_text(){
        return sub_text;
    }

    public void setSub_text(){
        this.sub_text = sub_text;
    }

}

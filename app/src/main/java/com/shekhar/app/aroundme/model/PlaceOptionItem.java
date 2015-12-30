package com.shekhar.app.aroundme.model;

public class PlaceOptionItem {

    private String title;
    private String value;
    private String item_new;
    private int icon;

    public PlaceOptionItem() {

    }

    public PlaceOptionItem(String title,String value, int icon) {
        this.title = title;
        this.icon = icon;
        this.value=value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getItem_new() {
        return item_new;
    }

    public void setItem_new(String item_new) {
        this.item_new = item_new;
    }
}
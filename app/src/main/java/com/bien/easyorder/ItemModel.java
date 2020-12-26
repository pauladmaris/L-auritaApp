package com.bien.easyorder;


public class ItemModel {
    private int image;
    private String name;

    public ItemModel() {
    }

    public ItemModel(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public String getName() {
        return name;
    }
}


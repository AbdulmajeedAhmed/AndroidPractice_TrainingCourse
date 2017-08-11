package com.alyafei.myapplication.others;

/**
 * Created by DELL on 3/18/2017.
 */

public class Item {
    private String frute;
    private int price;
    private int image;

    public Item(String frute, int price,int image) {
        this.frute=frute;
        this.price=price;
        this.image=image;
    }

    public String getFrute() {
        return frute;
    }

    public void setFrute(String frute) {
        this.frute = frute;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

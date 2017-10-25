package com.bwei.czx.czx1022study;

/**
 * Created by czx on 2017/10/23.
 */

public class UserBean {
    private String name;
    private boolean b = false;
    private float price;
    private long count;

    public UserBean(String name, boolean b, float price, long count) {
        this.name = name;
        this.b = b;
        this.price = price;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}

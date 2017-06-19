package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class Size {
    private int id;
    private String size;

    public Size() {
    }

    public Size(int id, String size) {
        this.id = id;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}

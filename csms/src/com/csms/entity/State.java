package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class State {
    private int id;
    private String state;//1 未确认 2 已确认

    public State() {
    }

    public State(int id, String state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

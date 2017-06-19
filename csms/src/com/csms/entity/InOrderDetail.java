package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class InOrderDetail {
    private Integer id;
    private Integer clothesNumberID;
    private Integer colorID;
    private Integer sizeID;
    private Integer num;
    private String inOrderNumber;
    private Integer del;//软删除 1正常 2删除

    public InOrderDetail() {
    }

    public InOrderDetail(Integer clothesNumberID, Integer colorID, Integer sizeID, Integer num, String inOrderNumber) {
        this.clothesNumberID = clothesNumberID;
        this.colorID = colorID;
        this.sizeID = sizeID;
        this.num = num;
        this.inOrderNumber = inOrderNumber;
    }

    public InOrderDetail(Integer id, Integer clothesNumberID, Integer colorID, Integer sizeID, Integer num, String inOrderNumber, Integer del) {
        this.id = id;
        this.clothesNumberID = clothesNumberID;
        this.colorID = colorID;
        this.sizeID = sizeID;
        this.num = num;
        this.inOrderNumber = inOrderNumber;
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getclothesNumberID() {
        return clothesNumberID;
    }

    public void setclothesNumberID(Integer clothesNumberID) {
        this.clothesNumberID = clothesNumberID;
    }

    public Integer getColorID() {
        return colorID;
    }

    public void setColorID(Integer colorID) {
        this.colorID = colorID;
    }

    public Integer getSizeID() {
        return sizeID;
    }

    public void setSizeID(Integer sizeID) {
        this.sizeID = sizeID;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getinOrderNumber() {
        return inOrderNumber;
    }

    public void setinOrderNumber(String inOrderNumber) {
        inOrderNumber = inOrderNumber;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}

package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class Clothes {
    private Integer id;
    private Integer clothesNumberID;//货号
    private Integer colorID;
    private Integer sizeID;
    private Integer stock;//库存量
    private Integer del;//软删除 1正常 2删除

    public Clothes() {
    }

    public Clothes(InOrderDetail inOrderDetail) {
        this.clothesNumberID = inOrderDetail.getclothesNumberID();
        this.colorID = inOrderDetail.getColorID();
        this.sizeID = inOrderDetail.getSizeID();
    }
    public Clothes(OutOrderDetail outOrderDetail){
        this.clothesNumberID = outOrderDetail.getClothesNumberID();
        this.colorID = outOrderDetail.getColorID();
        this.sizeID = outOrderDetail.getSizeID();
    }

    public Clothes(Integer id, Integer clothesNumberID, Integer colorID, Integer sizeID, Integer stock, Integer del) {
        this.id = id;
        this.clothesNumberID = clothesNumberID;
        this.colorID = colorID;
        this.sizeID = sizeID;
        this.stock = stock;
        this.del = del;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClothesNumberID() {
        return clothesNumberID;
    }

    public void setClothesNumberID(Integer clothesNumberID) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}


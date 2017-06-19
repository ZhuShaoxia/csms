package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class ClothesNumber {
    private Integer id;
    private String clothesNumber;
    private String brand;//品牌名
    private double inPrice;//入库价
    private double outPrice;//出库价
    private Integer stock;//库存
    private Integer del;//软删除 1正常 2删除

    public ClothesNumber() {
    }

    public ClothesNumber(String clothesNumber, String brand, double inPrice, double outPrice, Integer stock) {
        this.clothesNumber = clothesNumber;
        this.brand = brand;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
        this.stock = stock;
    }

    public ClothesNumber(Integer id, String clothesNumber, String brand, double inPrice, double outPrice, Integer stock, Integer del) {
        this.id = id;
        this.clothesNumber = clothesNumber;
        this.brand = brand;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
        this.stock = stock;
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClothesNumber() {
        return clothesNumber;
    }

    public void setClothesNumber(String clothesNumber) {
        this.clothesNumber = clothesNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClothesNumber that = (ClothesNumber) o;

        if (Double.compare(that.inPrice, inPrice) != 0) return false;
        if (Double.compare(that.outPrice, outPrice) != 0) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (clothesNumber != null ? !clothesNumber.equals(that.clothesNumber) : that.clothesNumber != null)
            return false;
        if (brand != null ? !brand.equals(that.brand) : that.brand != null) return false;
        if (stock != null ? !stock.equals(that.stock) : that.stock != null) return false;
        return del != null ? del.equals(that.del) : that.del == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (clothesNumber != null ? clothesNumber.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        temp = Double.doubleToLongBits(inPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(outPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (stock != null ? stock.hashCode() : 0);
        result = 31 * result + (del != null ? del.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClothesNumber{" +
                "id=" + id +
                ", clothesNumber='" + clothesNumber + '\'' +
                ", brand='" + brand + '\'' +
                ", inPrice=" + inPrice +
                ", outPrice=" + outPrice +
                ", stock=" + stock +
                ", del=" + del +
                '}';
    }
}

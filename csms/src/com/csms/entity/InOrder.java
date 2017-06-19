package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class InOrder {
    private Integer id;
    private String inOrderNumber;//入库单编号
    private String inOrderDate;//入库单日期
    private String maker;//制单人
    private String supplier;//供应厂商
    private Integer depotID;
    private Integer stateID;//1 未确认 2 已确认
    private String profile;//备注
    private Integer del;//软删除 1正常 2删除

    public InOrder() {
    }

    public InOrder(String inOrderNumber, String inOrderDate, String maker, String supplier, Integer depotID, Integer stateID, String profile) {
        this.inOrderNumber = inOrderNumber;
        this.inOrderDate = inOrderDate;
        this.maker = maker;
        this.supplier = supplier;
        this.depotID = depotID;
        this.stateID = stateID;
        this.profile = profile;
    }

    public InOrder(Integer id, String inOrderNumber, String inOrderDate, String maker, String supplier, Integer depotID, Integer stateID, String profile, Integer del) {
        this.id = id;
        this.inOrderNumber = inOrderNumber;
        this.inOrderDate = inOrderDate;
        this.maker = maker;
        this.supplier = supplier;
        this.depotID = depotID;
        this.stateID = stateID;
        this.profile = profile;
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInOrderNumber() {
        return inOrderNumber;
    }

    public void setInOrderNumber(String inOrderNumber) {
        this.inOrderNumber = inOrderNumber;
    }

    public String getInOrderDate() {
        return inOrderDate;
    }

    public void setInOrderDate(String inOrderDate) {
        this.inOrderDate = inOrderDate;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getDepotID() {
        return depotID;
    }

    public void setDepotID(Integer depotID) {
        this.depotID = depotID;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}

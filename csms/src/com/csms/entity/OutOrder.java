package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class OutOrder {
    private Integer id;
    private String outOrderNumber;
    private String outOrderDate;
    private String consignee;//收货人
    private String tel;
    private String address;//发货地址
    private Integer depotID;
    private Integer stateID;//1 未确认 2 已确认
    private String profile;//备注
    private Integer del;//软删除 1正常 2删除


    public OutOrder() {
    }

    public OutOrder(String outOrderNumber, String outOrderDate, String consignee, String tel, String address, Integer depotID, String profile) {
        this.outOrderNumber = outOrderNumber;
        this.outOrderDate = outOrderDate;
        this.consignee = consignee;
        this.tel = tel;
        this.address = address;
        this.depotID = depotID;
        this.profile = profile;
    }

    public OutOrder(String outOrderNumber, String outOrderDate, String consignee, String tel, String address, Integer depotID, Integer stateID, String profile, Integer del) {
        this.outOrderNumber = outOrderNumber;
        this.outOrderDate = outOrderDate;
        this.consignee = consignee;
        this.tel = tel;
        this.address = address;
        this.depotID = depotID;
        this.stateID = stateID;
        this.profile = profile;
        this.del = del;
    }

    public OutOrder(Integer id, String outOrderNumber, String outOrderDate, String consignee, String tel, String address, Integer depotID, Integer stateID, String profile, Integer del) {
        this.id = id;
        this.outOrderNumber = outOrderNumber;
        this.outOrderDate = outOrderDate;
        this.consignee = consignee;
        this.tel = tel;
        this.address = address;
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

    public String getOutOrderNumber() {
        return outOrderNumber;
    }

    public void setOutOrderNumber(String outOrderNumber) {
        this.outOrderNumber = outOrderNumber;
    }

    public String getOutOrderDate() {
        return outOrderDate;
    }

    public void setOutOrderDate(String outOrderDate) {
        this.outOrderDate = outOrderDate;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

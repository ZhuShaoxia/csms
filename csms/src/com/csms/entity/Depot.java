package com.csms.entity;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class Depot {
    private Integer id;
    private String depotNumber;
    private String depot;
    private String supervisor;//仓库管理员
    private String tel;//管理员电话
    private Integer capacity;//仓储量
    private Integer del;//软删除 1正常 2删除

    public Depot() {
    }

    public Depot(String depotNumber, String depot, String supervisor, String tel, Integer capacity) {
        this.depotNumber = depotNumber;
        this.depot = depot;
        this.supervisor = supervisor;
        this.tel = tel;
        this.capacity = capacity;
    }

    public Depot(Integer id, String depotNumber, String depot, String supervisor, String tel, Integer capacity, Integer del) {
        this.id = id;
        this.depotNumber = depotNumber;
        this.depot = depot;
        this.supervisor = supervisor;
        this.tel = tel;
        this.capacity = capacity;
        this.del = del;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }

    public String getDepotNumber() {
        return depotNumber;
    }

    public void setDepotNumber(String depotNumber) {
        this.depotNumber = depotNumber;
    }
}

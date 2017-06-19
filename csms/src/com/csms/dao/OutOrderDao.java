package com.csms.dao;

import com.csms.entity.OutOrder;
import com.csms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/7.
 */
public class OutOrderDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;



    /*
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
     */

    /**
     * 得到出库单集合
     *
     * @return
     */
    public List<OutOrder> getOutOrderList() {
        List<OutOrder> result = new ArrayList<OutOrder>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,OutOrderNumber,outOrderDate,consignee,tel,address,depotID,stateID,profile,del FROM T_OutOrder WHERE del=1;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String OutOrderNumber = rs.getString("OutOrderNumber");
                String OutOrderDate = rs.getString("outOrderDate");
                String consignee = rs.getString("consignee");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                int depotID = rs.getInt("depotID");
                int stateID = rs.getInt("stateID");
                String profile = rs.getString("profile");
                int del = rs.getInt("del");
                OutOrder OutOrder = new OutOrder(id, OutOrderNumber, OutOrderDate, consignee, tel, address, depotID, stateID, profile, del);
                result.add(OutOrder);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return result;
    }

    /**
     * 根据条件选择符合情况的出库单
     *
     * @param depotID
     * @param outOrderNumber
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<OutOrder> getOutOrderList(Integer depotID, String outOrderNumber, String beginDate, String endDate) {
        List<OutOrder> result = new ArrayList<OutOrder>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,OutOrderNumber,outOrderDate,consignee,tel,address,depotID,stateID,profile,del FROM T_OutOrder WHERE del=1 ";
            StringBuilder stringBuilder = new StringBuilder(sql);
            if (depotID != null) {
                stringBuilder.append(" AND depotID = " + depotID);
            }
            if (outOrderNumber != null) {
                stringBuilder.append(" AND outOrderNumber LIKE " + "\'%" + outOrderNumber + "%\'");
            }
            if (beginDate != null) {
                stringBuilder.append(" AND STR_TO_DATE(outOrderDate,'%Y-%m-%d') >= " + "\'" + beginDate + "\'");
            }
            if (endDate != null) {
                stringBuilder.append(" AND STR_TO_DATE(outOrderDate,'%Y-%m-%d') <= " + "\'" + endDate + "\'");
            }
            rs = stmt.executeQuery(stringBuilder.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("OutOrderNumber");
                String OutOrderDate = rs.getString("outOrderDate");
                String consignee = rs.getString("consignee");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                int depot = rs.getInt("depotID");
                int stateID = rs.getInt("stateID");
                String profile = rs.getString("profile");
                int del = rs.getInt("del");
                OutOrder OutOrder = new OutOrder(id, number, OutOrderDate, consignee, tel, address, depot, stateID, profile, del);
                result.add(OutOrder);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return result;
    }

    /**
     * 插入出库单
     *
     * @param outOrder
     * @return
     */
    public boolean insertOutOrder(OutOrder outOrder) {
        String OutOrderNumber = outOrder.getOutOrderNumber();
        String OutOrderDate = outOrder.getOutOrderDate();
        String consignee = outOrder.getConsignee();
        String tel = outOrder.getTel();
        String address = outOrder.getAddress();
        int depotID = outOrder.getDepotID();
        String profile = outOrder.getProfile();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO T_OutOrder(OutOrderNumber,outOrderDate,consignee,tel,address,depotID,stateID,profile,del) VALUES('" + OutOrderNumber + "','" + OutOrderDate + "','" + consignee + "','" + tel + "','" + address + "'," + depotID + ",1,'" + profile + "',1);";
            int n = stmt.executeUpdate(sql);
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, null);
        }
        return false;
    }


    /**
     * 删除入库单
     *
     * @param id
     * @return
     */
    public boolean removeOutOrderByID(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_OutOrder SET del = 2 WHERE id = " + id + "";
            int n = stmt.executeUpdate(sql);
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, null);
        }
        return false;
    }

    /**
     * 根据订单id得到入库单实体
     *
     * @param id
     * @return
     */
    public OutOrder getOutOrderByID(int id) {
        OutOrder OutOrder = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,OutOrderNumber,outOrderDate,consignee,tel,address,depotID,stateID,profile,del FROM T_OutOrder WHERE del=1 AND id = " + id + ";";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String number = rs.getString("OutOrderNumber");
                String OutOrderDate = rs.getString("outOrderDate");
                String consignee = rs.getString("consignee");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                int depot = rs.getInt("depotID");
                int stateID = rs.getInt("stateID");
                String profile = rs.getString("profile");
                int del = rs.getInt("del");
                OutOrder = new OutOrder(id, number, OutOrderDate, consignee, tel, address, depot, stateID, profile, del);
            }
            return OutOrder;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return OutOrder;
    }

    /**
     * 更新订单详情
     *
     * @param outOrder
     * @return
     */
    public boolean modifyOutOrderInfo(OutOrder outOrder) {
        int id = outOrder.getId();
//        String OutOrderNumber = outOrder.getOutOrderNumber();
        String OutOrderDate = outOrder.getOutOrderDate();
        String consignee = outOrder.getConsignee();
        String tel = outOrder.getTel();
        String address = outOrder.getAddress();
        int depotID = outOrder.getDepotID();
        String profile = outOrder.getProfile();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_OutOrder SET OutOrderDate = '" + OutOrderDate + "' ,tel = '" + tel + "',address = '" + address + "',depotID = " + depotID + ",consignee = '" + consignee + "',profile = '" + profile + "' WHERE id = " + id + ";";
            int n = stmt.executeUpdate(sql);
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, null);
        }
        return false;
    }


    /**
     * 更新订单状态
     *
     * @param id
     * @return
     */
    public boolean updateOutOrderState(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_OutOrder SET stateID = 2 WHERE id = " + id + ";";
            int n = stmt.executeUpdate(sql);
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, null);
        }
        return false;
    }

    /**
     * 根据出库单ID得到出库单编号
     * @param id
     * @return
     */
    public String getOutOrderNumberByID(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id , OutOrderNumber FROM T_OutOrder WHERE id = " + id + " AND del =1 ;";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String outOrderNumber = rs.getString("outOrderNumber");
                return outOrderNumber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.csms.dao;

import com.csms.entity.ClothesNumber;
import com.csms.entity.InOrder;
import com.csms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class InOrderDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
//
//    public InOrderDao() {
//        try {
//            conn = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 得到入库单集合
     *
     * @return
     */
    public List<InOrder> getInOrderList() {
        List<InOrder> result = new ArrayList<InOrder>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,inOrderNumber,inOrderDate,maker,supplier,depotID,stateID,profile,del FROM T_InOrder WHERE del=1;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String inOrderNumber = rs.getString("inOrderNumber");
                String inOrderDate = rs.getString("inOrderDate");
                String maker = rs.getString("maker");
                String supplier = rs.getString("supplier");
                int depotID = rs.getInt("depotID");
                int stateID = rs.getInt("stateID");
                String profile = rs.getString("profile");
                int del = rs.getInt("del");
                InOrder inOrder = new InOrder(id, inOrderNumber, inOrderDate, maker, supplier, depotID, stateID, profile, del);
                result.add(inOrder);
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
     * 根据条件选择符合情况的入库单
     * @param depotID
     * @param inOrderNumber
     * @param beginDate
     * @param endDate
     * @return
     */
    public List<InOrder> getInOrderList(Integer depotID, String inOrderNumber, String beginDate, String endDate) {
        List<InOrder> result = new ArrayList<InOrder>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,inOrderNumber,inOrderDate,maker,supplier,depotID,stateID,profile,del FROM T_InOrder WHERE del=1 ";
            StringBuilder stringBuilder = new StringBuilder(sql);
            if (depotID != null) {
                stringBuilder.append(" AND depotID = " + depotID);
            }
            if (inOrderNumber != null) {
                stringBuilder.append(" AND inOrderNumber LIKE " + "\'%"+inOrderNumber+"%\'");
            }
            if (beginDate != null) {
                stringBuilder.append(" AND STR_TO_DATE(inOrderDate,'%Y-%m-%d') >= " + "\'" + beginDate + "\'");
            }
            if (endDate != null) {
                stringBuilder.append(" AND STR_TO_DATE(inOrderDate,'%Y-%m-%d') <= " + "\'" + endDate + "\'");
            }
            rs = stmt.executeQuery(stringBuilder.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("inOrderNumber");
                String inOrderDate = rs.getString("inOrderDate");
                String maker = rs.getString("maker");
                String supplier = rs.getString("supplier");
                int depot = rs.getInt("depotID");
                int stateID = rs.getInt("stateID");
                String profile = rs.getString("profile");
                int del = rs.getInt("del");
                InOrder inOrder = new InOrder(id, number, inOrderDate, maker, supplier, depot, stateID, profile, del);
                result.add(inOrder);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,stmt,rs);
        }

        return result;
    }

    /**
     * 插入入库单
     *
     * @param inOrder
     * @return
     */
    public boolean insertInOrder(InOrder inOrder) {
        String inOrderNumber = inOrder.getInOrderNumber();
        String inOrderDate = inOrder.getInOrderDate();
        String maker = inOrder.getMaker();
        String supplier = inOrder.getSupplier();
        int depotID = inOrder.getDepotID();
        int stateID = inOrder.getStateID();
        String profile = inOrder.getProfile();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO T_InOrder(inOrderNumber,inOrderDate,maker,supplier,depotID,stateID,profile,del) VALUES('" + inOrderNumber + "','" + inOrderDate + "','" + maker + "','" + supplier + "'," + depotID + "," + stateID + ",'" + profile + "',1);";
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
    public boolean removeInOrderByID(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_InOrder SET del = 2 WHERE id = " + id + "";
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
    public InOrder getInOrderByID(int id) {
        InOrder inOrder = null;
        try {
//            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,inOrderNumber,inOrderDate,maker,supplier,depotID,stateID,profile,del FROM T_InOrder WHERE del=1 AND id = " + id + ";";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String inOrderNumber = rs.getString("inOrderNumber");
                String inOrderDate = rs.getString("inOrderDate");
                String maker = rs.getString("maker");
                String supplier = rs.getString("supplier");
                int depotID = rs.getInt("depotID");
                int stateID = rs.getInt("stateID");
                String profile = rs.getString("profile");
                int del = rs.getInt("del");
                inOrder = new InOrder(id, inOrderNumber, inOrderDate, maker, supplier, depotID, stateID, profile, del);
            }
            return inOrder;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return inOrder;
    }

    /**
     * 更新订单详情
     *
     * @param inOrder
     * @return
     */
    public boolean modifyInOrderInfo(InOrder inOrder) {
        int id = inOrder.getId();
        String date = inOrder.getInOrderDate();
        int depotID = inOrder.getDepotID();
        String supplier = inOrder.getSupplier();
        String profile = inOrder.getProfile();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_InOrder SET inOrderDate = '" + date + "' ,depotID = " + depotID + ",supplier = '" + supplier + "',profile = '" + profile + "' WHERE id = " + id + ";";
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
    public boolean updateInOrderState(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_InOrder SET stateID = 2 WHERE id = " + id + ";";
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

    public String getInOrderNumberByID(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id , inOrderNumber FROM T_InOrder WHERE id = " + id + " AND del =1 ;";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String inOrderNumber = rs.getString("inOrderNumber");
                return inOrderNumber;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return null;
    }
}

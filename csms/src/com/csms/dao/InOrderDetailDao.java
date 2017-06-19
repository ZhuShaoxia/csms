package com.csms.dao;

import com.csms.entity.InOrderDetail;
import com.csms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/6.
 */
public class InOrderDetailDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    /**
     * 根据入库单编号得到入库单详细集合
     *
     * @param inOrderNumber
     * @return
     */
    public List<InOrderDetail> getInOrderDetailList(String inOrderNumber) {
        List<InOrderDetail> result = new ArrayList<InOrderDetail>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,num,inOrderNumber,del FROM T_InOrderDetail WHERE inOrderNumber = '" + inOrderNumber + "' AND del = 1;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int clothesNumberID = rs.getInt("clothesNumberID");
                int colorID = rs.getInt("colorID");
                int sizeID = rs.getInt("sizeID");
                int num = rs.getInt("num");
                int del = rs.getInt("del");
                InOrderDetail inOrderDetail = new InOrderDetail(id, clothesNumberID, colorID, sizeID, num, inOrderNumber, del);
                result.add(inOrderDetail);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 增加订单详细数据
     *
     * @param inOrderDetail
     * @return
     */
    public boolean insertInOrderDetail(InOrderDetail inOrderDetail) {
        int clothesNumberID = inOrderDetail.getclothesNumberID();
        int colorID = inOrderDetail.getColorID();
        int sizeID = inOrderDetail.getSizeID();
        int num = inOrderDetail.getNum();
        String inOrderNumber = inOrderDetail.getinOrderNumber();
        Statement stmt1 = null;
        int n = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt1 = conn.createStatement();
            if (isExitInOrderDetail(inOrderDetail)) {
                int nums = getNum(inOrderDetail) + num;
                String sql = "UPDATE T_InOrderDetail SET num = " + nums + "  WHERE clothesNumberID = " + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND inOrderNumber = '" + inOrderNumber + "' AND del = 1";
                n = stmt1.executeUpdate(sql);

            } else {
                String sql = "INSERT INTO T_InOrderDetail(clothesNumberID,colorID,sizeID,num,InOrderNumber,del) VALUES(" + clothesNumberID + "," + colorID + "," + sizeID + "," + num + ",'" + inOrderNumber + "',1);";
                n = stmt1.executeUpdate(sql);
            }
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt1, null);
        }
        return false;
    }

    /**
     * 判断订单详情是否存在
     *
     * @param inOrderDetail
     * @return
     */
    public boolean isExitInOrderDetail(InOrderDetail inOrderDetail) {
        int clothesNumberID = inOrderDetail.getclothesNumberID();
        int colorID = inOrderDetail.getColorID();
        int sizeID = inOrderDetail.getSizeID();
        String inOrderNumber = inOrderDetail.getinOrderNumber();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,num,inOrderNumber,del FROM T_InOrderDetail WHERE clothesNumberID = " + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND inOrderNumber = '" + inOrderNumber + "' AND del = 1";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return false;
    }

    /**
     * 得到指定订单详情指定颜色尺寸的商品数量
     *
     * @param inOrderDetail
     * @return
     */
    public int getNum(InOrderDetail inOrderDetail) {
        int clothesNumberID = inOrderDetail.getclothesNumberID();
        int colorID = inOrderDetail.getColorID();
        int sizeID = inOrderDetail.getSizeID();
        String inOrderNumber = inOrderDetail.getinOrderNumber();
        Integer num = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,num,inOrderNumber,del FROM T_InOrderDetail WHERE clothesNumberID = " + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND inOrderNumber = '" + inOrderNumber + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                num = rs.getInt("num");
            }
            return num;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return num;
    }

    /**
     * 删除入单详细子项
     *
     * @param id
     * @return
     */
    public boolean removeInOrderDetail(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_InOrderDetail SET del = 2 WHERE id = " + id + "";
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


}

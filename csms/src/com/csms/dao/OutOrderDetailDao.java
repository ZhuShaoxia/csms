package com.csms.dao;

import com.csms.entity.OutOrderDetail;
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
public class OutOrderDetailDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    /**
     * 根据订单编号得到订单子项
     *
     * @param outOrderNumber
     * @return
     */
    public List<OutOrderDetail> getOutOrderDetailList(String outOrderNumber) {
        List<OutOrderDetail> result = new ArrayList<OutOrderDetail>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,num,outOrderNumber,del FROM T_OutOrderDetail WHERE outOrderNumber = '" + outOrderNumber + "' AND del = 1;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int clothesNumberID = rs.getInt("clothesNumberID");
                int colorID = rs.getInt("colorID");
                int sizeID = rs.getInt("sizeID");
                int num = rs.getInt("num");
                int del = rs.getInt("del");
                OutOrderDetail outOrderDetail = new OutOrderDetail(id, clothesNumberID, colorID, sizeID, num, outOrderNumber, del);
                result.add(outOrderDetail);
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
     * @param outOrderDetail
     * @return
     */
    public boolean insertOutOrderDetail(OutOrderDetail outOrderDetail) {
        int clothesNumberID = outOrderDetail.getClothesNumberID();
        int colorID = outOrderDetail.getColorID();
        int sizeID = outOrderDetail.getSizeID();
        int num = outOrderDetail.getNum();
        String outOrderNumber = outOrderDetail.getOutOrderNumber();
        Statement stmt1 = null;
        int n = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt1 = conn.createStatement();
            if (isExitOutOrderDetail(outOrderDetail)) {
                int nums = getNum(outOrderDetail) + num;
                String sql = "UPDATE T_OutOrderDetail SET num = " + nums + "  WHERE clothesNumberID = " + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND outOrderNumber = '" + outOrderNumber + "' AND del = 1";
                n = stmt1.executeUpdate(sql);

            } else {
                String sql = "INSERT INTO T_OutOrderDetail(clothesNumberID,colorID,sizeID,num,outOrderNumber,del) VALUES(" + clothesNumberID + "," + colorID + "," + sizeID + "," + num + ",'" + outOrderNumber + "',1);";
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
     * 判断出库单详情是否存在
     *
     * @param outOrderDetail
     * @return
     */
    public boolean isExitOutOrderDetail(OutOrderDetail outOrderDetail) {
        int clothesNumberID = outOrderDetail.getClothesNumberID();
        int colorID = outOrderDetail.getColorID();
        int sizeID = outOrderDetail.getSizeID();
        String outOrderNumber = outOrderDetail.getOutOrderNumber();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,num,outOrderNumber,del FROM T_OutOrderDetail WHERE clothesNumberID = " + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND outOrderNumber = '" + outOrderNumber + "' AND del = 1";
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
     * @param outOrderDetail
     * @return
     */
    public int getNum(OutOrderDetail outOrderDetail) {
        int clothesNumberID = outOrderDetail.getClothesNumberID();
        int colorID = outOrderDetail.getColorID();
        int sizeID = outOrderDetail.getSizeID();
        String outOrderNumber = outOrderDetail.getOutOrderNumber();
        Integer num = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,num,outOrderNumber,del FROM T_OutOrderDetail WHERE clothesNumberID = " + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND outOrderNumber = '" + outOrderNumber + "' AND del = 1";
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
     * 移除订单子项
     *
     * @param id
     * @return
     */
    public boolean removeOutOrderDetail(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_OutOrderDetail SET del = 2 WHERE id = " + id + "";
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

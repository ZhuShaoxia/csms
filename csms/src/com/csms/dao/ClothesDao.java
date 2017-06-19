package com.csms.dao;

import com.csms.entity.Clothes;
import com.csms.entity.InOrderDetail;
import com.csms.entity.OutOrderDetail;
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
public class ClothesDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

//    public ClothesDao() {
//        try {
//            conn = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 根据货号ID得到该货号商品
     *
     * @param clothesNumberID
     * @return
     */
        public List<Clothes> getClothesList(int clothesNumberID) {
        List<Clothes> result = new ArrayList<Clothes>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,stock,del FROM T_Clothes WHERE del = 1 AND clothesNumberID = " + clothesNumberID + ";";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                int colorID = rs.getInt("colorID");
                int sizeID = rs.getInt("sizeID");
                int stock = rs.getInt("stock");
                int del = rs.getInt("del");
                Clothes clothes = new Clothes(id, clothesNumberID, colorID, sizeID, stock, del);
                result.add(clothes);
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
     * 根据颜色和尺寸查询指定货号ID的衣服
     *
     * @param clothesNumberID
     * @param colorID
     * @param sizeID
     * @return
     */
    public List<Clothes> getClothesList(int clothesNumberID, Integer colorID, Integer sizeID) {
        List<Clothes> result = new ArrayList<Clothes>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,stock,del FROM T_Clothes WHERE del = 1 AND clothesNumberID = " + clothesNumberID + "";
            StringBuilder stringBuilder = new StringBuilder(sql);
            if (colorID != null) {
                stringBuilder.append(" AND colorID = " + colorID);
            }
            if (sizeID != null) {
                stringBuilder.append(" AND sizeID = " + sizeID);
            }
            rs = stmt.executeQuery(stringBuilder.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                int color = rs.getInt("colorID");
                int size = rs.getInt("sizeID");
                int stock = rs.getInt("stock");
                int del = rs.getInt("del");
                Clothes clothes = new Clothes(id, clothesNumberID, color, size, stock, del);
                result.add(clothes);
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
     * 插入
     *
     * @param inOrderDetails
     * @return
     */
    public boolean insertClothes(List<InOrderDetail> inOrderDetails) {
        Statement statement = null;
        try {
            conn = JDBCUtil.getConnection();
            statement = conn.createStatement();
            int n = 0;
            for (InOrderDetail i : inOrderDetails) {
                Clothes clothes = new Clothes(i);
                if (isExitClothes(clothes)) {
                    int num = getNum(clothes) + i.getNum();
                    String sql = "UPDATE T_Clothes SET stock = " + num + " WHERE clothesNumberID=" + clothes.getClothesNumberID() + " AND colorID = " + clothes.getColorID() + " AND sizeID = " + clothes.getSizeID() + " AND del = 1;";
                    n = statement.executeUpdate(sql);
                } else {
                    String sql = "INSERT INTO T_Clothes(clothesNumberID,colorID,sizeID,stock,del) VALUES(" + i.getclothesNumberID() + "," + i.getColorID() + "," + i.getSizeID() + "," + i.getNum() + ",1);";
                    n = statement.executeUpdate(sql);
                }
            }
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, statement, null);
        }


        return false;
    }

    /**
     * 确认出库单出库
     *
     * @param outOrderDetails
     * @return
     */
    public boolean outClothes(List<OutOrderDetail> outOrderDetails) {
        Statement statement = null;
        try {
            conn = JDBCUtil.getConnection();
            statement = conn.createStatement();
            for (OutOrderDetail outOrderDetail : outOrderDetails) {
                Clothes clothes = new Clothes(outOrderDetail);
                ClothesDao clothesDao = new ClothesDao();
                clothes = clothesDao.getClothes(clothes);
                int stock = clothes.getStock();
                int outOderDetailNum = outOrderDetail.getNum();
                int num = stock - outOderDetailNum;
                String sql = "UPDATE T_Clothes SET stock = " + num + " WHERE clothesNumberID=" + clothes.getClothesNumberID() + " AND colorID = " + clothes.getColorID() + " AND sizeID = " + clothes.getSizeID() + " AND del = 1;";
                statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, statement, rs);
        }
        return false;
    }


    /**
     * 根据条件得到衣服实体
     * @param clothes
     * @return
     */
    public Clothes getClothes(Clothes clothes) {
        int clothesNumberID = clothes.getClothesNumberID();
//        int colorID = clothes.getColorID();
//        int sizeID = clothes.getSizeID();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,stock,del FROM T_Clothes WHERE clothesNumberID=" + clothes.getClothesNumberID() + " AND colorID = " + clothes.getColorID() + " AND sizeID = " + clothes.getSizeID() + " AND del = 1;";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                int id = rs.getInt("id");
                int colorID = rs.getInt("colorID");
                int sizeID = rs.getInt("sizeID");
                int stock = rs.getInt("stock");
                int del = rs.getInt("del");
                clothes = new Clothes(id, clothesNumberID, colorID, sizeID, stock, del);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clothes;
    }


    /**
     * 判断是否存在当前衣服
     *
     * @param clothes
     * @return
     */
    public boolean isExitClothes(Clothes clothes) {
        int clothesNumberID = clothes.getClothesNumberID();
        int colorID = clothes.getColorID();
        int sizeID = clothes.getSizeID();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,stock,del FROM T_Clothes WHERE clothesNumberID=" + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND del = 1;";
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
     * 得到指定衣服的库存量
     *
     * @param clothes
     * @return
     */
    public int getNum(Clothes clothes) {
        int clothesNumberID = clothes.getClothesNumberID();
        int colorID = clothes.getColorID();
        int sizeID = clothes.getSizeID();
        Integer num = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumberID,colorID,sizeID,stock,del FROM T_Clothes WHERE clothesNumberID=" + clothesNumberID + " AND colorID = " + colorID + " AND sizeID = " + sizeID + " AND del = 1;";
            System.out.println(sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                num = rs.getInt("stock");
                return num;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return num;
    }
}

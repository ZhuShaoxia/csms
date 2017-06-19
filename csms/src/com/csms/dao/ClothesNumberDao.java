package com.csms.dao;

import com.csms.entity.ClothesNumber;
import com.csms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class ClothesNumberDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

//    public ClothesNumberDao() {
//        try {
//            conn = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 得到商品货号集合
     *
     * @return
     */
    public List<ClothesNumber> getClothesNumberList() {
        List<ClothesNumber> result = new ArrayList<ClothesNumber>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumber,brand,inPrice,outPrice,stock,del FROM T_ClothesNumber WHERE del =1 ;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("clothesNumber");
                String brand = rs.getString("brand");
                double inPrice = rs.getDouble("inPrice");
                double outPrice = rs.getDouble("outPrice");
                int stock = rs.getInt("stock");
                int del = rs.getInt("del");
                ClothesNumber clothesNumber = new ClothesNumber(id, number, brand, inPrice, outPrice, stock, del);
                result.add(clothesNumber);
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
     * 根据衣服货号或者衣服品名得到货号集合
     *
     * @param clothesNumber
     * @param brand
     * @return
     */
    public List<ClothesNumber> getClothesNumberList(String clothesNumber, String brand) {
        List<ClothesNumber> result = new ArrayList<ClothesNumber>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT id,clothesNumber,brand,inPrice,outPrice,stock,del FROM T_ClothesNumber WHERE del =1 ";
            StringBuilder strb = new StringBuilder(sql);
            if (clothesNumber != null) {
                strb.append(" AND clothesNumber LIKE \'%" + clothesNumber + "%\' ");
            }
            if (brand != null) {
                strb.append(" AND brand LIKE \'%" + brand + "%\' ");
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strb.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("clothesNumber");
                String name = rs.getString("brand");
                double inPrice = rs.getDouble("inPrice");
                double outPrice = rs.getDouble("outPrice");
                int stock = rs.getInt("stock");
                int del = rs.getInt("del");
                ClothesNumber clothesNumber1 = new ClothesNumber(id, number, name, inPrice, outPrice, stock, del);
                result.add(clothesNumber1);
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
     * 插入衣服货号
     *
     * @param clothesNumber
     * @return
     */
    public boolean insertClothesNumber(ClothesNumber clothesNumber) {
        String number = clothesNumber.getClothesNumber();
        String name = clothesNumber.getBrand();
        double inPrice = clothesNumber.getInPrice();
        double outPrice = clothesNumber.getOutPrice();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO T_ClothesNumber(clothesNumber,brand,inPrice,outPrice,stock,del) VALUES('" + number + "','" + name + "'," + inPrice + "," + outPrice + ",0,1);";
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
     * 通过货号ID删除货号
     *
     * @param id
     * @return
     */
    public boolean removeClothesNumberByID(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_ClothesNumber SET del = 2 WHERE id = " + id + ";";
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
     * 根据货号id得到货号实体
     *
     * @param id
     * @return
     */
    public ClothesNumber getClothesNumberByID(int id) {
        ClothesNumber clothesNumber = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumber,brand,inPrice,outPrice,stock,del FROM T_ClothesNumber WHERE id = " + id + "; ";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String number = rs.getString("clothesNumber");
                String brand = rs.getString("brand");
                double inPrice = rs.getDouble("inPrice");
                double outPrice = rs.getDouble("outPrice");
                int stock = rs.getInt("stock");
                int del = rs.getInt("del");
                clothesNumber = new ClothesNumber(id, number, brand, inPrice, outPrice, stock, del);
            }
            return clothesNumber;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return clothesNumber;
    }

    /**
     * 更新货号信息
     *
     * @param clothesNumber
     * @return
     */
    public boolean modifyClothesInfo(ClothesNumber clothesNumber) {
        String brand = clothesNumber.getBrand();
        double inPrice = clothesNumber.getInPrice();
        double outPrice = clothesNumber.getOutPrice();
        int stock = clothesNumber.getStock();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_ClothesNumber SET brand = '" + brand + "',inPrice = " + inPrice + ",outPrice=" + outPrice + ",stock = " + stock + ";";
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
     * 判断货号是否存在
     *
     * @param clothesNumber
     * @return
     */
    public boolean isExitClothesNumber(String clothesNumber) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT clothesNumber FROM T_ClothesNumber WHERE clothesNumber = '" + clothesNumber + "';";
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
     * 根据衣服货号得到衣服货号ID
     *
     * @param clothesNumber
     * @return
     */
    public int getIDByClothesNumber(String clothesNumber) {
        int id = 0;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,clothesNumber FROM T_ClothesNumber WHERE clothesNumber = '" + clothesNumber + "';";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                id = rs.getInt("id");
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return id;
    }


    /**
     * 更新库存量
     *
     * @param id
     * @param stock
     * @return
     */
    public boolean updateClothesNumberStock(int id, int stock) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_ClothesNumber SET stock = " + stock + " WHERE id = " + id +";";
            int n = stmt.executeUpdate(sql);
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn,stmt,null);
        }
        return false;
    }
}

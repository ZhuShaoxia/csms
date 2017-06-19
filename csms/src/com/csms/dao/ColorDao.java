package com.csms.dao;

import com.csms.entity.Color;
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
public class ColorDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

//    public ColorDao() {
//        try {
//            conn = JDBCUtil.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 得到数据库中颜色的集合
     *
     * @return
     */
    public List<Color> getColorList() {
        List<Color> result = new ArrayList<Color>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,color FROM T_Color ;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String color = rs.getString("color");
                Color color1 = new Color(id, color);
                result.add(color1);
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
     * 根据ID得到颜色实体
     * @param id
     * @return
     */
    public Color getColorByID(int id) {
        Color color = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,color FROM T_Color WHERE id = " + id + ";";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String colorStr = rs.getString("color");
                color = new Color(id,colorStr);
            }
            return color;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return color;
    }
}

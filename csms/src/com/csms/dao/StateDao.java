package com.csms.dao;

import com.csms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class StateDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public String getStateByID(int id) {
        String state = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,state FROM T_State WHERE id = " + id + ";";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                state = rs.getString("state");
            }
            return state;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return state;
    }

}

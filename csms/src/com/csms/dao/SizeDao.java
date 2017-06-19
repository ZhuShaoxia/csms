package com.csms.dao;

import com.csms.entity.Size;
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
public class SizeDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    /**
     * 得到尺寸集合
     *
     * @return
     */
    public List<Size> getSizeList() {
        List<Size> result = new ArrayList<Size>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,size From T_Size ;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String size = rs.getString("size");
                Size size1 = new Size(id, size);
                result.add(size1);
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
     * 根据尺寸id得到尺寸实体
     * @param id
     * @return
     */
    public Size getSizeByID(int id) {
        Size size = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,size FROM T_Size WHERE id = " + id + ";";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String size1 = rs.getString("size");
                size = new Size(id, size1);
            }
            return size;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return size;
    }
}

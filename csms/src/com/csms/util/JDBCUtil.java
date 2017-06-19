package com.csms.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zhuxiaolei on 2017/4/20.
 */
public class JDBCUtil {
    private static String url = "jdbc:mysql://127.0.0.1:3306/csms?useUnicode=true&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "123456";
    private static Connection connection;
    private static PreparedStatement pstmt;
    private static ResultSet rs;
    /**
     * 静态代码块加载驱动
     * 驱动只需加载一次
     */
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }
//
//    /**
//     * 执行更新操作
//     * @param sql
//     * @param params
//     * @return
//     * @throws SQLException
//     */
//    public static boolean updateByPreparedStatement(String sql, List<?> params) throws SQLException {
//        boolean flag = false;
//        int result = -1;
//        PreparedStatement pstmt = getConnection().prepareStatement(sql);
//        int index = 1;
//        if (params != null && !params.isEmpty()) {
//            for (int i = 0; i < params.size(); i++) {
//                pstmt.setObject(index++, params.get(i));
//            }
//        }
//        result = pstmt.executeUpdate();
//        flag = result > 0 ? true : false;
//        return flag;
//    }
//
//    public static List<Map<String,Object>> findResult(String sql, List<?> params) throws SQLException {
//        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//        int index = 1;
//        pstmt = connection.prepareStatement(sql);
//        if (params != null && !params.isEmpty()) {
//            for (int i = 0; i < params.size(); i++) {
//                pstmt.setObject(index++, params.get(i));
//            }
//        }
//        rs = pstmt.executeQuery();
//        ResultSetMetaData metaData = rs.getMetaData();
//        int cols_len = metaData.getColumnCount();
//        while (rs.next()) {
//            Map<String, Object> map = new HashMap<String, Object>();
//            for (int i = 0; i < cols_len; i++) {
//                String cols_name = metaData.getColumnName(i + 1);
//                Object cols_value = rs.getObject(cols_name);
//                if (cols_value == null) {
//                    cols_value = "";
//                }
//                map.put(cols_name, cols_value);
//            }
//            list.add(map);
//        }
//        return list;
//    }


}

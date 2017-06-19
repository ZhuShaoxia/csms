package com.csms.dao;

import com.csms.entity.Depot;
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
public class DepotDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;


    public void sql() {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,depotNumber,depot,supervisor,tel,capacity,del FROM T_Depot WHERE del = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String depotNumber = rs.getString("depotNumber");
                String depot = rs.getString("depot");
                String supervisor = rs.getString("supervisor");
                String tel = rs.getString("tel");
                int capacity = rs.getInt("capacity");
                int del = rs.getInt("del");
                Depot depot1 = new Depot(id, depotNumber, depot, supervisor, tel, capacity, del);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
    }


    /**
     * 得到数据库中仓库集合
     *
     * @return
     */
    public List<Depot> getDepotList() {
        List<Depot> result = new ArrayList<Depot>();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,depotNumber,depot,supervisor,tel,capacity,del FROM T_Depot WHERE del = 1";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String depotNumber = rs.getString("depotNumber");
                String depot = rs.getString("depot");
                String supervisor = rs.getString("supervisor");
                String tel = rs.getString("tel");
                int capacity = rs.getInt("capacity");
                int del = rs.getInt("del");
                Depot depot1 = new Depot(id, depotNumber, depot, supervisor, tel, capacity, del);
                result.add(depot1);
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
     * 根据仓库编号和仓库名字搜索仓库
     *
     * @param depotNumber
     * @param depot
     * @return
     */
    public List<Depot> getDepotList(String depotNumber, String depot) {
        List<Depot> result = new ArrayList<Depot>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT id,depotNumber,depot,supervisor,tel,capacity,del FROM T_Depot WHERE del = 1";
            StringBuilder strb = new StringBuilder(sql);
            if (depotNumber != null) {
                strb.append(" AND depotNumber LIKE \'%" + depotNumber + "%\' ");
            }
            if (depot != null) {
                strb.append(" AND depot LIKE \'%" + depot + "%\' ");
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strb.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("depotNumber");
                String depot2 = rs.getString("depot");
                String supervisor = rs.getString("supervisor");
                String tel = rs.getString("tel");
                int capacity = rs.getInt("capacity");
                int del = rs.getInt("del");
                Depot depot1 = new Depot(id, number, depot2, supervisor, tel, capacity, del);
                result.add(depot1);
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
     * 增加仓库
     *
     * @param depot
     * @return
     */
    public boolean insertDepot(Depot depot) {
        String depotNumber = depot.getDepotNumber();
        String depot1 = depot.getDepot();
        String supervisor = depot.getSupervisor();
        String tel = depot.getTel();
        int capacity = depot.getCapacity();
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "INSERT INTO T_Depot(depotNumber,depot,supervisor,tel,capacity,del) VALUES ('" + depotNumber + "','" + depot1 + "','" + supervisor + "','" + tel + "'," + capacity + ",1)";
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
     * 更新仓库信息
     *
     * @param depot
     * @return
     */
    public boolean modifyDepotInfo(Depot depot) {
        int id = depot.getId();
        String depotNumber = depot.getDepotNumber();
        String depot1 = depot.getDepot();
        String supervisor = depot.getSupervisor();
        String tel = depot.getTel();
        int capacity = depot.getCapacity();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_Depot SET depotNumber = '" + depotNumber + "',depot = '" + depot1 + "',supervisor = '" + supervisor + "',tel = '" + tel + "',capacity = " + capacity + " WHERE id = " + id + ";";
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
     * 根据仓库ID得到仓库实体
     *
     * @param id
     * @return
     */
    public Depot getDepotByID(int id) {
        Depot depot1 = null;
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT id,depotNumber,depot,supervisor,tel,capacity,del FROM T_Depot WHERE id = " + id + "";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String depotNumber = rs.getString("depotNumber");
                String depot = rs.getString("depot");
                String supervisor = rs.getString("supervisor");
                String tel = rs.getString("tel");
                int capacity = rs.getInt("capacity");
                int del = rs.getInt("del");
                depot1 = new Depot(id, depotNumber, depot, supervisor, tel, capacity, del);
            }
            return depot1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return depot1;
    }

    /**
     * 删除仓库
     *
     * @param id
     * @return
     */
    public boolean removeDepotByID(int id) {
        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.createStatement();
            String sql = "UPDATE T_Depot SET del = 2 WHERE id = " + id + ";";
            int n = stmt.executeUpdate(sql);
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断仓库编号是否存在
     *
     * @param depotNumber
     * @return
     */
    public boolean isExitDepotNumber(String depotNumber) {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT depotNumber FROM T_Depot WHERE depotNumber = '" + depotNumber + "';";
            stmt = conn.createStatement();
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
}

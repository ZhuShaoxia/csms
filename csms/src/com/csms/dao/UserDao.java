package com.csms.dao;

import com.csms.entity.User;
import com.csms.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class UserDao {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    public boolean cc() {
        try {
            conn = JDBCUtil.getConnection();
            String sql = ";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }

        return false;
    }

    /**
     * 判断管理员登录账号是否正确
     *
     * @param userAccount
     * @param pwd
     * @return
     */
    public boolean isAdmin(String userAccount, String pwd) {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT userAccount , password ,gradeID FROM T_USER WHERE userAccount = '" + userAccount + "' AND password = '" + pwd + "' AND gradeID = 1;";
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

    /**
     * 修改管理员用户密码
     *
     * @param pwd1
     * @param pwd2
     * @return
     */
    public boolean updateAdminPwd(String pwd1, String pwd2) {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE T_USER SET password = '" + pwd2 + "' WHERE userAccount = 'admin' AND password = '" + pwd1 + "';";
            stmt = conn.createStatement();
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
     * 得到管理员用户密码
     *
     * @return
     */
    public String getAdminPwd() {
        String password = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT password FROM T_User WHERE userAccount = 'admin';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                password = rs.getString("password");
                return password;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return password;
    }


    /**
     * 得到用户集合
     *
     * @return
     */
    public List<User> getUserList() {
        List<User> result = new ArrayList<User>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT id,userAccount,password,userName,userProfile,gradeID,del FROM T_USER WHERE gradeID = 2 AND del = 1;";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String userAccount = rs.getString("userAccount");
                String password = rs.getString("password");
                String userName = rs.getString("userName");
                String userProfile = rs.getString("userProfile");
                int gradeID = rs.getInt("gradeID");
                int del = rs.getInt("del");
                User user = new User(id, userAccount, password, userName, userProfile, gradeID, del);
                result.add(user);
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
     * 根据用户名或用户账号查询用户
     *
     * @param userName
     * @param userAccount
     * @return
     */
    public List<User> getUserList(String userName, String userAccount) {
        List<User> result = new ArrayList<User>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT id,userAccount,password,userName,userProfile,gradeID,del FROM T_USER WHERE gradeID = 2 AND del = 1 ";
            StringBuilder strb = new StringBuilder(sql);
            if (userName != null) {
                strb.append(" AND userName LIKE \'%" + userName + "%\' ");
            }
            if (userAccount != null) {
                strb.append(" AND userAccount LIKE \'%" + userAccount + "%\' ");
            }
            stmt = conn.createStatement();
            rs = stmt.executeQuery(strb.toString());
            while (rs.next()) {
                int id = rs.getInt("id");
                String account = rs.getString("userAccount");
                String password = rs.getString("password");
                String name = rs.getString("userName");
                String userProfile = rs.getString("userProfile");
                int gradeID = rs.getInt("gradeID");
                int del = rs.getInt("del");
                User user = new User(id, account, password, name, userProfile, gradeID, del);
                result.add(user);
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
     * 根据用户id得到用户实体
     *
     * @param id
     * @return
     */
    public User getUserByID(int id) {
        User user = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT id,userAccount,password,userName,userProfile,gradeID,del FROM T_USER WHERE id = " + id + ";";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String userAccount = rs.getString("userAccount");
                String password = rs.getString("password");
                String userName = rs.getString("userName");
                String userProfile = rs.getString("userProfile");
                int gradeID = rs.getInt("gradeID");
                int del = rs.getInt("del");
                user = new User(id, userAccount, password, userName, userProfile, gradeID, del);
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, stmt, rs);
        }
        return user;
    }

    /**
     * 更新用户信息
     *
     * @param userAccount
     * @param userName
     * @param pwd
     * @param profile
     * @return
     */

    public boolean modifyUserInfo(int id, String userAccount, String userName, String pwd, String profile) {

        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE T_USER SET userAccount = '" + userAccount + "',userName ='" + userName + "',password = '" + pwd + "',userProfile = '" + profile + "' WHERE id = " + id + ";";
            stmt = conn.createStatement();
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

    public boolean regiterUser(User user) {
        String userAccount = user.getUserAccount();
        String password = user.getPassword();
        String userName = user.getUserName();
        String profile = user.getUserProfile();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO T_User(userAccount,password,userName,userProfile,gradeID,del) VALUES ('" + userAccount + "','" + password + "','" + userName + "','" + profile + "',2,1);";
            stmt = conn.createStatement();
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
     * 通过用户id移除用户
     *
     * @param id
     * @return
     */
    public boolean removeUserByid(int id) {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE T_USER SET del = 2 WHERE id = " + id + ";";
            stmt = conn.createStatement();
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
     * 判断用户账号是否存在
     * @param userAccount
     * @return
     */
    public boolean isExitUserAccount(String userAccount) {
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT userAccount FROM T_User WHERE userAccount = '" + userAccount + "';";
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

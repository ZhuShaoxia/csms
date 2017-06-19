package com.csms.servlet;

import com.csms.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/3.
 */
public class ModifyPasswordServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String password = req.getParameter("password");
        String pwd1 = req.getParameter("pwd1");
        String pwd2 = req.getParameter("pwd2");
        UserDao userDao = new UserDao();
        boolean isAdmin = userDao.isAdmin("admin", password);
        boolean bpwd1 = pwd1 != null && !"".equals(pwd1.trim());
        boolean bpwd2 = pwd2 != null && !"".equals(pwd2.trim());
        if (isAdmin) {
            if (bpwd1 && bpwd2) {
                if (pwd1.equals(pwd2)) {
                    userDao.updateAdminPwd(password, pwd1);
                }
            }
        }
        resp.sendRedirect("PASSWORD1001.jsp");
    }
}

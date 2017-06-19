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
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userAccount = req.getParameter("userAccount");
        String password = req.getParameter("password");
        boolean account = userAccount != null && !"".equals(userAccount.trim());
        boolean pwd = password != null && !"".equals(password.trim());
        if (account && pwd) {
            UserDao userDao = new UserDao();
            boolean isAdmin = userDao.isAdmin(userAccount, password);
            if (isAdmin) {
                resp.sendRedirect("Main.jsp");
            } else {
                resp.sendRedirect("login.jsp");
            }
        } else {
            resp.sendRedirect("login.jsp");
        }

    }
}

package com.csms.servlet;

import com.csms.dao.UserDao;
import com.csms.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/4.
 */
public class UserModifyInfoServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));
        UserDao userDao = new UserDao();
        User user = userDao.getUserByID(id);

        String userAccount = user.getUserAccount();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String profile = req.getParameter("profile");

        userDao.modifyUserInfo(id, userAccount, userName, password, profile);
        user = userDao.getUserByID(id);
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("/csms/system/USER1002.jsp");
    }
}

package com.csms.servlet;

import com.csms.dao.UserDao;
import com.csms.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/4.
 */
public class UserListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        UserDao userDao = new UserDao();
        List<User> users = userDao.getUserList();

        HttpSession session = req.getSession();
        session.setAttribute("users",users);
        session.removeAttribute("ERROR_USERACCOUNT");
        resp.sendRedirect("/csms/system/USER1001.jsp");
    }
}

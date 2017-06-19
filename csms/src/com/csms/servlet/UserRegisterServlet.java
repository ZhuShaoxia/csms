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
public class UserRegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        String userAccount = req.getParameter("userAccount");
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String profile = req.getParameter("profile");

        boolean isEmpty = isEmpty(userAccount, userName, password);
        UserDao userDao = new UserDao();
        HttpSession session = req.getSession();
        session.removeAttribute("ERROR_USERACCOUNT");
        if (userDao.isExitUserAccount(userAccount)) {
            session.setAttribute("ERROR_USERACCOUNT","该账号已存在,请重新输入");
            resp.sendRedirect("/csms/system/USER1003.jsp");
        } else {
            if (isEmpty) {
                User user = new User(userAccount, password, userName, profile);
                userDao.regiterUser(user);
                resp.sendRedirect("/csms/system/UserListServlet");
            }
        }
    }

    public boolean isEmpty(String userAccount, String userName, String password) {
        boolean isUserAccount = userAccount != null && !"".equals(userAccount.trim());
        boolean isUserName = userName != null;
        boolean isPassword = password != null && !"".equals(password.trim());

        if (isUserAccount && isUserName && isPassword) {
            return true;
        } else {
            return false;
        }
    }
}

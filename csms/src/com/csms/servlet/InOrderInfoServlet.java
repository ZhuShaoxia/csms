package com.csms.servlet;

import com.csms.dao.InOrderDao;
import com.csms.entity.InOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class InOrderInfoServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));

        InOrderDao inOrderDao = new InOrderDao();
        InOrder inOrder = inOrderDao.getInOrderByID(id);

        HttpSession session = req.getSession();
        session.setAttribute("inOrder",inOrder);
        resp.sendRedirect("order2002.jsp");
    }
}

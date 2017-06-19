package com.csms.servlet;

import com.csms.dao.InOrderDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class InOrderRemoveServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));

        InOrderDao inOrderDao = new InOrderDao();
        inOrderDao.removeInOrderByID(id);

        resp.sendRedirect("/csms/stock/InOrderListServlet");
    }
}

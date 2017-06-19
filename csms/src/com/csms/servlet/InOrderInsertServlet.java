package com.csms.servlet;

import com.csms.dao.InOrderDao;
import com.csms.entity.InOrder;
import com.csms.util.OrderNumberUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class InOrderInsertServlet extends HttpServlet{
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        OrderNumberUtil orderNumberUtil = new OrderNumberUtil();

        String supplier = req.getParameter("supplier");
        String date = req.getParameter("date");
        int depotID = Integer.parseInt(req.getParameter("depotID"));
        int stateID = 1;
        String inOrderNumber = orderNumberUtil.getInOrderNumber(date.substring(2,4));
        String maker = req.getParameter("maker");
        String profile = req.getParameter("profile");

        InOrder inOrder = new InOrder(inOrderNumber,date,maker,supplier,depotID,stateID,profile);
        InOrderDao inOrderDao = new InOrderDao();
        inOrderDao.insertInOrder(inOrder);
        resp.sendRedirect("/csms/stock/InOrderListServlet");
    }
}

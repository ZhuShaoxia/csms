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
public class InOrderSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String depot = req.getParameter("depotID");
        Integer depotID = null;
        if ("".equals(depot.trim())) {
            depotID = null;
        }else {
            depotID = Integer.valueOf(depot);
        }

        String inOrderNumber = req.getParameter("inOrderNumber");
        if ("".equals(inOrderNumber.trim())) {
            inOrderNumber = null;
        }
        String beginDate = req.getParameter("beginDate");
        if ("".equals(beginDate.trim())) {
            beginDate = null;
        }
        String endDate = req.getParameter("endDate");
        if ("".equals(endDate.trim())) {
            endDate = null;
        }
        InOrderDao inOrderDao = new InOrderDao();
        List<InOrder> inOrders = null;

        inOrders = inOrderDao.getInOrderList(depotID, inOrderNumber, beginDate, endDate);

        HttpSession session = req.getSession();
        session.setAttribute("inOrders", inOrders);
        resp.sendRedirect("/csms/stock/order2001.jsp");
    }
}

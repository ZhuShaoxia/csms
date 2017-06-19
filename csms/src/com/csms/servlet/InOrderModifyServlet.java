package com.csms.servlet;

import com.csms.dao.ClothesNumberDao;
import com.csms.dao.InOrderDao;
import com.csms.entity.ClothesNumber;
import com.csms.entity.InOrder;
import com.csms.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class InOrderModifyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));
        int depotID = Integer.parseInt(req.getParameter("depotID"));
        String supplier = req.getParameter("supplier");

        InOrderDao inOrderDao = new InOrderDao();
        InOrder inOrder = inOrderDao.getInOrderByID(id);
        inOrder.setDepotID(depotID);
        inOrder.setSupplier(supplier);
        inOrderDao.modifyInOrderInfo(inOrder);

        inOrder = inOrderDao.getInOrderByID(id);
        System.out.println(inOrder.getDepotID());
        HttpSession session = req.getSession();
        session.setAttribute("inOrder",inOrder);
        resp.sendRedirect("/csms/stock/order2002.jsp");

    }
}

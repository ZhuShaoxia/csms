package com.csms.servlet;

import com.csms.dao.ClothesDao;
import com.csms.dao.InOrderDao;
import com.csms.dao.InOrderDetailDao;
import com.csms.entity.Clothes;
import com.csms.entity.InOrderDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/6.
 */
public class InOrderDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        if (action.equals("add")) {
            inOrderInsert(req, resp);
            resp.sendRedirect("/csms/stock/order2002.jsp");
        }
        if (action.equals("remove")) {
            inOrderRemove(req, resp);
            resp.sendRedirect("/csms/stock/order2002.jsp");
        }
        if (action.equals("inorder")) {
            inorder(req, resp);
            resp.sendRedirect("/csms/stock/InOrderListServlet");
        }
    }

        public void inOrderInsert(HttpServletRequest req, HttpServletResponse resp) {
            int clothesNumberID = Integer.parseInt(req.getParameter("clothesNumberID"));
            int colorID = Integer.parseInt(req.getParameter("colorID"));
            int sizeID = Integer.parseInt(req.getParameter("sizeID"));
            int num = Integer.parseInt(req.getParameter("num"));
            String inOrderNumber = req.getParameter("inOrderNumber");
            InOrderDetailDao inOrderDetailDao = new InOrderDetailDao();
            InOrderDetail inOrderDetail = new InOrderDetail(clothesNumberID, colorID, sizeID, num, inOrderNumber);
            inOrderDetailDao.insertInOrderDetail(inOrderDetail);
        }

    public void inOrderRemove(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        InOrderDetailDao inOrderDetailDao = new InOrderDetailDao();
        inOrderDetailDao.removeInOrderDetail(id);
    }

    public void inorder(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        InOrderDao inOrderDao = new InOrderDao();
        inOrderDao.updateInOrderState(id);
        String inOrderNumber = inOrderDao.getInOrderNumberByID(id);

        InOrderDetailDao inOrderDetailDao = new InOrderDetailDao();
        List<InOrderDetail> inOrderDetails = inOrderDetailDao.getInOrderDetailList(inOrderNumber);

        ClothesDao clothesDao = new ClothesDao();

        clothesDao.insertClothes(inOrderDetails);
    }
}

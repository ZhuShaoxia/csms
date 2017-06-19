package com.csms.servlet;

import com.csms.dao.ClothesDao;
import com.csms.dao.OutOrderDao;
import com.csms.dao.OutOrderDetailDao;
import com.csms.entity.OutOrder;
import com.csms.entity.OutOrderDetail;
import com.csms.util.OrderNumberUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/8.
 */
public class OutOrderServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        if (action.equals("insert")) {
            insertOutOrder(req, resp);
            resp.sendRedirect("/csms/stock/OutOrderServlet?action=list");
        }
        if (action.equals("list")) {
            listOutOrder(req, resp);
            resp.sendRedirect("/csms/stock/order3001.jsp");
        }
        if (action.equals("remove")) {
            removeOutOrder(req, resp);
            resp.sendRedirect("/csms//stock/OutOrderServlet?action=list");
        }
        if (action.equals("info")) {
            infoOutOrder(req, resp);
            HttpSession session = req.getSession();
            session.removeAttribute("ERROR_NUM");
            resp.sendRedirect("/csms/stock/order3002.jsp");
        }
        if (action.equals("modify")) {
            modifyOutOrder(req, resp);
            resp.sendRedirect("/csms/stock/order3002.jsp");
        }
        if (action.equals("search")) {
            searchOutOrder(req, resp);
            resp.sendRedirect("/csms/stock/order3001.jsp");
        }
        if (action.equals("out")) {
            outOrder(req, resp);
            resp.sendRedirect("/csms//stock/OutOrderServlet?action=list");
        }
    }


    public void insertOutOrder(HttpServletRequest req, HttpServletResponse resp) {
        String outOrderNumber = OrderNumberUtil.getOutOrderNumber("001");
        String date = req.getParameter("date");
        int depotID = Integer.parseInt(req.getParameter("depotID"));
        String consignee = req.getParameter("consignee");
        String tel = req.getParameter("tel");
        String address = req.getParameter("address");
        String profile = req.getParameter("profile");

        OutOrder outOrder = new OutOrder(outOrderNumber, date, consignee, tel, address, depotID, 1, profile, 1);
        OutOrderDao outOrderDao = new OutOrderDao();
        outOrderDao.insertOutOrder(outOrder);
    }

    public void listOutOrder(HttpServletRequest req, HttpServletResponse resp) {
        OutOrderDao outOrderDao = new OutOrderDao();
        List<OutOrder> outOrders = outOrderDao.getOutOrderList();
        HttpSession session = req.getSession();
        session.removeAttribute("ERROR_NUM");
        session.setAttribute("outOrders", outOrders);
    }

    public void removeOutOrder(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        OutOrderDao outOrderDao = new OutOrderDao();
        outOrderDao.removeOutOrderByID(id);
    }

    public void infoOutOrder(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        OutOrderDao outOrderDao = new OutOrderDao();
        OutOrder outOrder = outOrderDao.getOutOrderByID(id);
        HttpSession session = req.getSession();
        session.setAttribute("outOrder", outOrder);

        String outOrderNumber = outOrderDao.getOutOrderNumberByID(id);
        OutOrderDetailDao outOrderDetailDao = new OutOrderDetailDao();
        List<OutOrderDetail> outOrderDetails = outOrderDetailDao.getOutOrderDetailList(outOrderNumber);
        session.setAttribute("outOrderDetails", outOrderDetails);
    }

    public void modifyOutOrder(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String address = req.getParameter("address");
        String date = req.getParameter("date");
        int depotID = Integer.parseInt(req.getParameter("depotID"));
        String consignee = req.getParameter("consignee");
        String tel = req.getParameter("tel");
        String profile = req.getParameter("profile");
        OutOrder outOrder = new OutOrder();
        outOrder.setId(id);
        outOrder.setAddress(address);
        outOrder.setOutOrderDate(date);
        outOrder.setDepotID(depotID);
        outOrder.setConsignee(consignee);
        outOrder.setTel(tel);
        outOrder.setProfile(profile);
        OutOrderDao outOrderDao = new OutOrderDao();
        outOrderDao.modifyOutOrderInfo(outOrder);
        outOrder = outOrderDao.getOutOrderByID(id);

        HttpSession session = req.getSession();
        session.setAttribute("outOrder", outOrder);
    }

    public void searchOutOrder(HttpServletRequest req, HttpServletResponse resp) {
        String depot = req.getParameter("depotID");
        Integer depotID = null;
        if ("".equals(depot.trim())) {
            depotID = null;
        } else {
            depotID = Integer.valueOf(depot);
        }

        String outOrderNumber = req.getParameter("outOrderNumber");
        if ("".equals(outOrderNumber.trim())) {
            outOrderNumber = null;
        }
        String beginDate = req.getParameter("beginDate");
        if ("".equals(beginDate.trim())) {
            beginDate = null;
        }
        String endDate = req.getParameter("endDate");
        if ("".equals(endDate.trim())) {
            endDate = null;
        }
        OutOrderDao outOrderDao = new OutOrderDao();
        List<OutOrder> outOrders = null;

        outOrders = outOrderDao.getOutOrderList(depotID, outOrderNumber, beginDate, endDate);
        HttpSession session = req.getSession();
        session.setAttribute("outOrders", outOrders);
    }

    public void outOrder(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));

        OutOrderDao outOrderDao = new OutOrderDao();
        outOrderDao.updateOutOrderState(id);
        OutOrder outOrder = outOrderDao.getOutOrderByID(id);
        String outOrderNumber = outOrder.getOutOrderNumber();
        OutOrderDetailDao outOrderDetailDao = new OutOrderDetailDao();
        List<OutOrderDetail> outOrderDetails = outOrderDetailDao.getOutOrderDetailList(outOrderNumber);
        ClothesDao clothesDao = new ClothesDao();
        clothesDao.outClothes(outOrderDetails);
    }


}

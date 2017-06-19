package com.csms.servlet;

import com.csms.dao.ClothesDao;
import com.csms.dao.OutOrderDetailDao;
import com.csms.entity.Clothes;
import com.csms.entity.InOrder;
import com.csms.entity.OutOrder;
import com.csms.entity.OutOrderDetail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/8.
 */
public class OutOrderDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        if (action.equals("add")) {
            session.removeAttribute("ERROR_NUM");
            int clothesNumberID = Integer.parseInt(req.getParameter("clothesNumberID"));
            int colorID = Integer.parseInt(req.getParameter("colorID"));
            int sizeID = Integer.parseInt(req.getParameter("sizeID"));
            int num = Integer.parseInt(req.getParameter("num"));
            String outOrderNumber = req.getParameter("outOrderNumber");

            OutOrderDetail outOrderDetail = new OutOrderDetail(clothesNumberID, colorID, sizeID, num, outOrderNumber);

            Clothes clothes = new Clothes();
            clothes.setClothesNumberID(clothesNumberID);
            clothes.setColorID(colorID);
            clothes.setSizeID(sizeID);
            ClothesDao clothesDao = new ClothesDao();
            int clothesDaoNum = clothesDao.getNum(clothes);
            OutOrderDetailDao outOrderDetailDao = new OutOrderDetailDao();
            int outOrderDetailNum = outOrderDetailDao.getNum(outOrderDetail);
            if (num + outOrderDetailNum <= clothesDaoNum) {

                outOrderDetailDao.insertOutOrderDetail(outOrderDetail);
                OutOrder outOrder = (OutOrder) session.getAttribute("outOrder");
                resp.sendRedirect("/csms/stock/OutOrderServlet?action=info&id=" + outOrder.getId());
            } else {
                session.setAttribute("ERROR_NUM", "当前库存量不足");
                resp.sendRedirect("/csms/stock/order3003.jsp");
            }

        }
        if (action.equals("remove")) {
            removeOutOrderDetail(req, resp);
            OutOrder outOrder = (OutOrder) session.getAttribute("outOrder");
            resp.sendRedirect("/csms/stock/OutOrderServlet?action=info&id=" + outOrder.getId());
        }

    }


    public void removeOutOrderDetail(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        OutOrderDetailDao outOrderDetailDao = new OutOrderDetailDao();
        outOrderDetailDao.removeOutOrderDetail(id);
    }
}

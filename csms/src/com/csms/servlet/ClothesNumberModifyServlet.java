package com.csms.servlet;

import com.csms.dao.ClothesNumberDao;
import com.csms.entity.ClothesNumber;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class ClothesNumberModifyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));
        double inPrice = Double.valueOf(req.getParameter("inPrice"));
        double outPrice = Double.valueOf(req.getParameter("outPrice"));

        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
        ClothesNumber clothesNumber = clothesNumberDao.getClothesNumberByID(id);
        clothesNumber.setInPrice(inPrice);
        clothesNumber.setOutPrice(outPrice);
        HttpSession session = req.getSession();
        session.setAttribute("clothesNumber",clothesNumber);
        resp.sendRedirect("CLOTHING1002.jsp");
    }
}

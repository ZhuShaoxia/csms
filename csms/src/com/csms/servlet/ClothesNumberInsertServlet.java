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
public class ClothesNumberInsertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String clothesNumber = req.getParameter("clothesNumber");
        String brand = req.getParameter("brand");
        double inPrice = Double.parseDouble(req.getParameter("inPrice"));
        double outPrice = Double.parseDouble(req.getParameter("outPrice"));

        ClothesNumber clothesNumber1 = new ClothesNumber(clothesNumber, brand, inPrice, outPrice, 0);
        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
        HttpSession session = req.getSession();
        session.removeAttribute("ERROR_CLOTHESNUMBER");
        if (clothesNumberDao.isExitClothesNumber(clothesNumber)) {
            session.setAttribute("ERROR_CLOTHESNUMBER","货号已存在，请重新输入");
            resp.sendRedirect("CLOTHING1003.jsp");
        } else {
            clothesNumberDao.insertClothesNumber(clothesNumber1);
            resp.sendRedirect("/csms/system/ClothesNumberListServlet");
        }
    }
}

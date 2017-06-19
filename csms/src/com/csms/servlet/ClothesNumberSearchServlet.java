package com.csms.servlet;

import com.csms.dao.ClothesNumberDao;
import com.csms.entity.ClothesNumber;

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
public class ClothesNumberSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String clothesNumber = req.getParameter("clothesNumber");
        String brand = req.getParameter("brand");

        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
        List<ClothesNumber> clothesNumbers = null;
        if (clothesNumber != null && brand != null) {
            clothesNumbers = clothesNumberDao.getClothesNumberList(clothesNumber,brand);
        }

        HttpSession session = req.getSession();
        session.setAttribute("clothesNumbers",clothesNumbers);
        resp.sendRedirect("/csms/system/CLOTHING1001.jsp");
    }
}

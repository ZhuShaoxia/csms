package com.csms.servlet;

import com.csms.dao.ClothesDao;
import com.csms.dao.ClothesNumberDao;
import com.csms.entity.Clothes;
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
public class ClothesNumberListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
        List<ClothesNumber> clothesNumbers = clothesNumberDao.getClothesNumberList();
        updateStock(req, resp);
        HttpSession session = req.getSession();
        session.removeAttribute("ERROR_CLOTHESNUMBER");
        session.setAttribute("clothesNumbers", clothesNumbers);

        resp.sendRedirect("/csms/system/CLOTHING1001.jsp");
    }

    public void updateStock(HttpServletRequest req, HttpServletResponse resp) {
        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
        List<ClothesNumber> clothesNumbers = clothesNumberDao.getClothesNumberList();

        ClothesDao clothesDao = new ClothesDao();
        for (ClothesNumber c : clothesNumbers) {
            int clothesNumberID = c.getId();
            List<Clothes> clothes = clothesDao.getClothesList(clothesNumberID);
            int stock = 0;
            for (Clothes clothes1 : clothes) {
                stock += clothes1.getStock();
            }
            clothesNumberDao.updateClothesNumberStock(clothesNumberID, stock);
        }

    }
}

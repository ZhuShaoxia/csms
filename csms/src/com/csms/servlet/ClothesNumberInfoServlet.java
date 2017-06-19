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
import java.security.interfaces.RSAKey;
import java.util.List;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class ClothesNumberInfoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        int id = Integer.parseInt(req.getParameter("id"));

        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
        ClothesNumber clothesNumber = clothesNumberDao.getClothesNumberByID(id);


        ClothesDao clothesDao = new ClothesDao();
        List<Clothes> clothes = clothesDao.getClothesList(id);

        String action = req.getParameter("action");
        if (action != null && action.equals("search")) {
            clothes = search(req, resp);
        }
        HttpSession session = req.getSession();

        session.setAttribute("clothes", clothes);
        session.setAttribute("clothesNumber", clothesNumber);
        resp.sendRedirect("CLOTHING1002.jsp");
    }

    public List<Clothes> search(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Integer sizeID = null;
        Integer colorID = null;

        String size = req.getParameter("sizeID");
        if (size != null && !"".equals(size.trim())) {
            sizeID = Integer.valueOf(size);
        }
        String color = req.getParameter("colorID");
        if (color != null && !"".equals(color.trim())) {
            colorID = Integer.valueOf(color);
        }
        ClothesDao clothesDao = new ClothesDao();
        List<Clothes> clothes = clothesDao.getClothesList(id, colorID, sizeID);
        return clothes;
    }
}

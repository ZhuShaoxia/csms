package com.csms.servlet;

import com.csms.dao.DepotDao;
import com.csms.entity.Depot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by zhuxiaolei on 2017/6/5.
 */
public class DepotInsertServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String depotNumber = req.getParameter("depotNumber");
        String depot = req.getParameter("depot");
        String supervisor = req.getParameter("supervisor");//仓库管理员
        String tel = req.getParameter("tel");//管理员电话
        int capacity = Integer.parseInt(req.getParameter("capacity"));//仓储量
        Depot depot1 = new Depot(depotNumber, depot, supervisor, tel, capacity);
        DepotDao depotDao = new DepotDao();
        HttpSession session = req.getSession();
        session.removeAttribute("ERROR_DEPOTNUMBER");
        if (depotDao.isExitDepotNumber(depotNumber)) {
            session.setAttribute("ERROR_DEPOTNUMBER", "仓库编号已存在");
            resp.sendRedirect("/csms/system/bsd_code_006_2.jsp");
        } else {
            depotDao.insertDepot(depot1);
            resp.sendRedirect("/csms/system/DepotListServlet");
        }

    }
}

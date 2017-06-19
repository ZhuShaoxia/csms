package com.csms.servlet;

import com.csms.dao.DepotDao;
import com.csms.entity.Depot;

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
public class DepotSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String depotNumber = req.getParameter("depotNumber");
        String depot = req.getParameter("depot");

        DepotDao depotDao = new DepotDao();
        List<Depot> depots = null;
        if (depotNumber != null && depot != null) {
            depots = depotDao.getDepotList(depotNumber, depot);
        }

        HttpSession session = req.getSession();
        session.setAttribute("depots",depots);
        resp.sendRedirect("/csms/system/bsd_code_006.jsp");
    }
}

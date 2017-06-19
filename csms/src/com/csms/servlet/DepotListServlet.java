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
public class DepotListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        DepotDao depotDao = new DepotDao();
        List<Depot> depots = depotDao.getDepotList();

        HttpSession session = req.getSession();
        session.setAttribute("depots",depots);
        session.removeAttribute("ERROR_DEPOTNUMBER");
        resp.sendRedirect("/csms/system/bsd_code_006.jsp");
    }
}

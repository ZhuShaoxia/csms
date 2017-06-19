<%@ page import="com.csms.dao.DepotDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.entity.Depot" %>
<%@ page import="com.csms.entity.OutOrder" %>
<%@ page import="com.csms.dao.StateDao" %><%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<head>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
    <META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
    <META HTTP-EQUIV="content-style-type" CONTENT="text/css">
    <title>出库单查询</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/cjcalendar.js"></script>
    <script type="text/javascript" src="../js/page.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/aaa.css"/>
    <script type="text/javascript" src="../js/aaa.js"></script>
</head>
<script language="javascript">
    var CalendarWebControl = new atCalendarControl();
</script>
<SCRIPT LANGUAGE="javaScript">
    //    <!--
    //
    //    function goSearch() {
    //        document.forms[0].action = "order3001.jsp";
    //        document.forms[0].submit();
    //    }
    //
    //    function delCom(id) {
    //        if (id == '1') {
    //            document.idFrmMain.gys.value = "";
    //        } else {
    //            document.idFrmMain.sccj.value = "";
    //        }
    //    }
    //
    //    function changesize(id) {
    //        document.forms[0].action = "order3001.jsp";
    //        document.forms[0].submit();
    //    }
    //
    //    function jump(id) {
    //        document.forms[0].action = "order3001.jsp";
    //        document.forms[0].submit();
    //    }
    //
    //    function locatePage(id) {
    //        document.forms[0].action = "order3001.jsp";
    //        document.forms[0].submit();
    //    }
    //
    //    function setValue() {
    //        document.all.sccj.value = "青岛雪中飞贸易有限公司";
    //    }
    //    -->
    function delInfo(id) {
        if (confirm("您确定删除该条记录？")) {
            alert("删除成功！");
            window.location.href = "/csms/stock/OutOrderServlet?action=remove&id=" + id;
        }
    }
    function out(id) {
        if (confirm("您确认出库？确认之后订单不可修改")) {
            alert("出库成功！");
            window.location.href = "/csms/stock/OutOrderServlet?action=out&id=" + id;
        }
    }
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="idFrmMain" ID="idmig0101" METHOD="POST" ACTION="/csms/stock/OutOrderServlet?action=search">
    <input type="hidden" id="slide_img">
    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">出库单查询</td>
            <td class="headerbar61"><p align="right">
                <input type=submit value=" 查 询 "></p></td>
        </tr>
    </table>
    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>

    <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="textbar81" width="15%">所出仓库</td>
            <td class="textbar01" width="35%">
                <select name="depotID" style="width:152px">
                    <option value="">------</option>
                    <%
                        DepotDao depotDao = new DepotDao();
                        List<Depot> depots = depotDao.getDepotList();
                        for (Depot d : depots) {
                    %>
                    <option value="<%=d.getId()%>"><%=d.getDepot()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
            <td class="textbar81" width="15%">单据编号</td>
            <td class="textbar01" width="35%">
                <input type="text" name="outOrderNumber" style="width:152px">
            </td>

        </tr>

        <tr>
            <td class="textbar81" width="15%">出库日期</td>
            <td class="textbar01" width="35%" colspan="3">
                <input type="text" name="beginDate" class="tcal" value="" placeholder="请选择日期"/>
                ~
                <input type="text" name="endDate" class="tcal" value="" placeholder="请选择日期"/>
            </td>

        </tr>

    </table>

    <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
        <tr>
            <td></td>
        </tr>
    </table>

    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="headerbar61" width="100%" colspan="1">出库单明细</td>
            <td class="headerbar61"><p align="right">
                <input type=button value=" 新 增 " onClick="JavaScript:goto('order3004.jsp');">
            </td>
    </table>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" bgcolor="gray">
        <tr>
            <td width="100%" colspan="1">
                <table border="0" cellspacing="1" cellpadding="2" width="100%">
                    <tr>
                        <td width="5%" class="headerbar82">序号</td>
                        <td width="15%" class="headerbar82">单据编号</td>
                        <td width="10%" class="headerbar82">所出仓库</td>
                        <td width="10%" class="headerbar82">出库日期</td>
                        <td width="10%" class="headerbar82">接收人</td>
                        <td width="20%" class="headerbar82">发往地址</td>
                        <td width="10%" class="headerbar82">出库单状态</td>
                        <td class="headerbar82">操作</td>
                    </tr>
                    <%
                        List<OutOrder> outOrders = (List<OutOrder>) session.getAttribute("outOrders");
                        for (int i = 0; i < outOrders.size(); i++) {
                            int n = i + 1;
                            OutOrder outOrder = outOrders.get(i);
                            int depotID = outOrder.getDepotID();
                            StateDao stateDao = new StateDao();
                            int stateID = outOrder.getStateID();
                            if (n % 2 == 0) {
                    %>
                    <tr>
                        <td class="gridbar11" align="center"><%=n%>
                        </td>
                        <td class="gridbar11" align="center"><a href="/csms/stock/OutOrderServlet?action=info&id=<%=outOrder.getId()%>"><%=outOrder.getOutOrderNumber()%>
                        </a></td>
                        <td class="gridbar11" align="center"><%=depotDao.getDepotByID(depotID).getDepot()%>
                        </td>
                        <td class="gridbar11" align="center"><%=outOrder.getOutOrderDate()%>
                        </td>
                        <td class="gridbar11" align="center"><%=outOrder.getConsignee()%>
                        </td>
                        <td class="gridbar11" align="left"><%=outOrder.getAddress()%>
                        </td>
                        <td class="gridbar11" align="center"><%=stateDao.getStateByID(stateID)%>
                        </td>
                        <td class="gridbar11" align="center">

                            <%
                                if (stateID == 1) {

                            %>
                            <a href="#" onclick="out('<%=outOrder.getId()%>')">确认出库</a> |
                            <%
                                }
                            %>

                            <a
                                    href="#"><img src="../image/del.gif"
                                                  align="bottom" border="0" alt="作废"
                                                  onClick="delInfo('<%=outOrder.getId()%>')"/></a>
                        </td>
                    </tr>
                    <%

                    } else {

                    %>
                    <tr>
                        <td class="gridbar01" align="center"><%=n%>
                        </td>
                        <td class="gridbar01" align="center"><a href="/csms/stock/OutOrderServlet?action=info&id=<%=outOrder.getId()%>"><%=outOrder.getOutOrderNumber()%>
                        </a></td>
                        <td class="gridbar01" align="center"><%=depotDao.getDepotByID(depotID).getDepot()%>
                        </td>
                        <td class="gridbar01" align="center"><%=outOrder.getOutOrderDate()%>
                        </td>
                        <td class="gridbar01" align="center"><%=outOrder.getConsignee()%>
                        </td>
                        <td class="gridbar01" align="left"><%=outOrder.getAddress()%>
                        </td>
                        <td class="gridbar01" align="center"><%=stateDao.getStateByID(stateID)%>
                        </td>
                        <td class="gridbar01" align="center">

                            <%
                                if (stateID == 1) {

                            %>
                            <a href="#" onclick="out('<%=outOrder.getId()%>')">确认出库</a> |
                            <%
                                }
                            %>

                            <a
                                    href="#"><img src="../image/del.gif"
                                                  align="bottom" border="0" alt="作废"
                                                  onClick="delInfo('<%=outOrder.getId()%>')"/></a>
                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </td>
        </tr>
    </table>


    <table width="100%" border="0" cellpadding="0" cellspacing="2">
        <tr>
            <td colspan="2" align="right" height="20" nowrap class="textbar3">
                &nbsp;&nbsp; 共4条 &nbsp;&nbsp; 第1/1页 &nbsp;&nbsp;
                <a href="#" style="cursor:hand">首页</a>&nbsp;&nbsp;
                <a style="cursor:hand" href="#">上一页</a>&nbsp;&nbsp;
                <a style="cursor:hand" href="#">下一页</a>&nbsp;&nbsp;
                <a style="cursor:hand" href="#">尾页</a>&nbsp;&nbsp;
                &nbsp;&nbsp;
            </td>
        </tr>
    </table>
</FORM>
</BODY>
</html>



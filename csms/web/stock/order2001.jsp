<%@ page import="com.csms.dao.DepotDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.entity.Depot" %>
<%@ page import="com.csms.entity.InOrder" %>
<%@ page import="com.csms.dao.InOrderDao" %>
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
    <title>采购入库管理</title>
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

    function goSearch() {
        document.forms[0].action = "order2001.jsp";
        document.forms[0].submit();
    }

    function delCom(id) {
        if (id == '1') {
            document.idFrmMain.gys.value = "";
        } else {
            document.idFrmMain.sccj.value = "";
        }
    }

    function changesize(id) {
        document.forms[0].action = "order2001.jsp";
        document.forms[0].submit();
    }

    function jump(id) {
        document.forms[0].action = "order2001.jsp";
        document.forms[0].submit();
    }

    function locatePage(id) {
        document.forms[0].action = "order2001.jsp";
        document.forms[0].submit();
    }
    function delInfo(id) {
        if (confirm("您确定删除该条记录？")) {
            alert("删除成功！");
            window.location.href = "/csms/stock/InOrderRemoveServlet?id=" + id;
        }
    }
    function inorder(id) {
        if (confirm("您确认入库？确认之后订单不可修改")) {
            alert("入库成功！");
            window.location.href = "/csms/stock/InOrderDetailServlet?action=inorder&id=" + id;
        }
    }
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="idFrmMain" ID="idmig0101" METHOD="POST" ACTION="/csms/stock/InOrderSearchServlet">

    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">入库单查询</td>
            <td class="headerbar61"><p align="right">
                <input type=submit value=" 查 询 ">
            </p>
            </td>
        </tr>
    </table>


    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>


    <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="textbar81" width="15%">所入仓库</td>
            <td class="textbar01" width="35%">
                <select name="depotID" style="width:152px">
                    <%
                        DepotDao depotDao = new DepotDao();
                        List<Depot> depots = depotDao.getDepotList();
                    %>
                    <option value="" selected="selected">------</option>
                    <%
                        for (Depot depot : depots) {
                    %>
                    <option value="<%=depot.getId()%>"><%=depot.getDepot()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
            <td class="textbar81" width="15%">单据编号</td>
            <td class="textbar01" width="35%">
                <input type="text" name="inOrderNumber" style="width:152px">
            </td>

        </tr>

        <tr>
            <td class="textbar81" width="15%">入库日期</td>
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
            <td class="headerbar61" width="100%" colspan="1">入库单</td>
            <td class="headerbar61">
                <p align="rigth">
                    <input type=submit value=" 新 增 " onClick="JavaScript:goto('order2004.jsp');">
                </p>
            </td>


    </table>
    <%
        List<InOrder> inOrders = (List<InOrder>) session.getAttribute("inOrders");
        if (inOrders == null) {
            InOrderDao inOrderDao = new InOrderDao();
            inOrders = inOrderDao.getInOrderList();
        }
    %>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" bgcolor="gray">
        <tr>
            <td width="100%" colspan="1">
                <table border="0" cellspacing="1" cellpadding="2" width="100%">
                    <tr>
                        <td width="5%" class="headerbar82">序号</td>
                        <td width="15%" class="headerbar82">单据编号</td>
                        <td width="20%" class="headerbar82">所入仓库</td>
                        <td width="15%" class="headerbar82">入库日期</td>
                        <td width="10%" class="headerbar82">制单人</td>
                        <td width="15%" class="headerbar82">供货商</td>
                        <td width="10%" class="headerbar82">入库单状态</td>
                        <td class="headerbar82">操作</td>
                    </tr>
                    <%
                        for (int i = 0; i < inOrders.size(); i++) {
                            int n = i + 1;
                            InOrder inOrder = inOrders.get(i);
                            Depot depot = depotDao.getDepotByID(inOrder.getDepotID());
                            StateDao stateDao = new StateDao();
                            int stateID = inOrder.getStateID();
                            String state = stateDao.getStateByID(inOrder.getStateID());
                            if (n % 2 == 0) {

                    %>
                    <tr>
                        <td class="gridbar11" align="center"><%=n%>
                        </td>
                        <td class="gridbar11" align="center"><a
                                href="/csms/stock/InOrderInfoServlet?id=<%=inOrder.getId()%>"><%=inOrder.getInOrderNumber()%>
                        </a></td>
                        <td class="gridbar11" align="center"><%=depot.getDepot()%>
                        </td>
                        <td class="gridbar11" align="center"><%=inOrder.getInOrderDate()%>
                        </td>
                        <td class="gridbar11" align="left"><%=inOrder.getMaker()%>
                        </td>
                        <td class="gridbar11" align="left"><%=inOrder.getSupplier()%>
                        </td>
                        <td class="gridbar11" align="center"><%=state%>
                        </td>
                        <td class="gridbar11" align="center">

                            <%
                                if (stateID == 1) {

                            %>
                            <a href="#" onclick="inorder('<%=inOrder.getId()%>')">确认入库</a> |
                            <%
                                }
                            %>
                            <a
                                href="#"><img src="../image/del.gif"
                                              align="bottom" border="0" alt="作废"
                                              onClick="delInfo('<%=inOrder.getId()%>')"/></a>
                        </td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td class="gridbar01" align="center"><%=n%>
                        </td>
                        <td class="gridbar01" align="center"><a
                                href="/csms/stock/InOrderInfoServlet?id=<%=inOrder.getId()%>"><%=inOrder.getInOrderNumber()%>
                        </a></td>
                        <td class="gridbar01" align="center"><%=depot.getDepot()%>
                        </td>
                        <td class="gridbar01" align="center"><%=inOrder.getInOrderDate()%>
                        </td>
                        <td class="gridbar01" align="left"><%=inOrder.getMaker()%>
                        </td>
                        <td class="gridbar01" align="left"><%=inOrder.getSupplier()%>
                        </td>
                        <td class="gridbar01" align="center"><%=state%>
                        </td>
                        <td class="gridbar01" align="center">

                            <%
                                if (stateID == 1) {
                            %>
                            <a href="#" onclick="inorder('<%=inOrder.getId()%>')">确认入库</a> |
                            <%
                                }
                            %>
                            <a
                                href="#"><img src="../image/del.gif"
                                              align="bottom" border="0" alt="作废"
                                              onClick="delInfo('<%=inOrder.getId()%>')"/></a>
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
                &nbsp; 共4条 &nbsp;&nbsp; 第1/1页 &nbsp;&nbsp;
                <a href="#" style="cursor:hand">首页</a>&nbsp;&nbsp;
                <a style="cursor:hand" href="#">上一页</a>&nbsp;&nbsp;
                <a style="cursor:hand" href="#">下一页</a>&nbsp;&nbsp;
                <a style="cursor:hand" href="#">尾页</a>&nbsp;&nbsp;
            </td>
        </tr>
    </table>
</FORM>
</BODY>
</html>



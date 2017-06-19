<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.csms.entity.Depot" %>
<%@ page import="com.csms.dao.DepotDao" %><%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<head>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
    <META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
    <META HTTP-EQUIV="content-style-type" CONTENT="text/css">
    <title>日志查询</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/page.js"></script>
    <script type="text/javascript" src="../js/cjcalendar.js"></script>
</head>
<script language="javascript">
    var CalendarWebControl = new atCalendarControl();
</script>
<SCRIPT LANGUAGE="javaScript">
    <!--
    function goto(strURL) {
        document.forms[0].action = strURL;
        document.forms[0].submit();
    }
    function del(id) {
        if (confirm("您确定删除该条记录？")) {
            alert("删除成功！");
            window.location.href="/csms/system/DepotRemoveServlet?id="+id;
        }
    }


    -->
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="idFrmMain" ID="idmig0101" METHOD="POST" ACTION="/csms/system/DepotSearchServlet">


    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">仓库查询</td>
            <td class="headerbar63" width="50%" colspan="1"><p align="right">
                <input type=submit value=" 查 询 "></p></td>
        </tr>
        </tr>
    </table>

    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>

    <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
        <tr>

            <td class="textbar81" width="15%">仓库编号</td>
            <td class="textbar01" width="35%">
                <input type="text" name="depotNumber" value="" style="width:210px "></td>
            <td class="textbar81" width="15%">仓库名称</td>
            <td class="textbar01" width="35%">
                <input type="text" name="depot" value="" style="width:210px "></td>
        </tr>

    </table>

    <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
        <tr>
            <td></td>
        </tr>
    </table>

    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="headerbar61" width="50%" colspan="1">仓库明细</td>
            <td class="headerbar63" width="50%" colspan="1"><p align="right">
                <input type=submit value=" 新 增 " onclick="JavaScript:goto('bsd_code_006_2.jsp');"></p></td>
        </tr>
    </table>
    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" bgcolor="gray">
        <tr>
            <td width="100%" colspan="1">
                <table border="0" cellspacing="1" cellpadding="2" width="100%">
                    <tr>
                        <td width="5%" class="headerbar82">序号</td>
                        <td width="10%" class="headerbar82">仓库编号</td>
                        <td width="20%" class="headerbar82">仓库名称</td>
                        <td width="10%" class="headerbar82">仓储量</td>
                        <td width="5%" class="headerbar82">操作</td>
                    </tr>

                    <%
                        List<Depot> depots = (List<Depot>) session.getAttribute("depots");
                        if (depots == null) {
                            DepotDao depotDao = new DepotDao();
                            depots = depotDao.getDepotList();
                        }
                        for (int i = 0; i < depots.size(); i++) {
                            int n = i + 1;
                            Depot depot = depots.get(i);
                            if (n % 2 == 0) {

                    %>
                    <tr>
                        <td width="5%" class="gridbar11"><%=n%>
                        </td>
                        <td width="10%" class="gridbar11"><a
                                href="/csms/system/DepotInfoServlet?id=<%=depot.getId()%>"><%=depot.getDepotNumber()%>
                        </a>
                        </td>
                        <td width="20%" class="gridbar11"><%=depot.getDepot()%>
                        </td>
                        <td width="10%" class="gridbar11"><%=depot.getCapacity()%>
                        </td>
                        <td width="5%" class="gridbar11">
                            <img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onclick="javascript:del('<%=depot.getId()%>')" style="cursor:hand"/></td>

                        </td>
                    </tr>
                    <%

                    } else {

                    %>
                    <tr>
                        <td width="5%" class="gridbar01"><%=n%>
                        </td>
                        <td width="10%" class="gridbar01"><a
                                href="/csms/system/DepotInfoServlet?id=<%=depot.getId()%>"><%=depot.getDepotNumber()%>
                        </a>
                        </td>
                        <td width="20%" class="gridbar01"><%=depot.getDepot()%>
                        </td>
                        <td width="10%" class="gridbar01"><%=depot.getCapacity()%>
                        </td>
                        <td width="5%" class="gridbar01">
                            <img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onclick="javascript:del('<%=depot.getId()%>')" style="cursor:hand"/></td>

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

    <table width="100%" border="0" cellpadding="1" cellspacing="2">
        <tr>
            <td colspan="2" align="right" height="20" nowrap class="textbar3">
                &nbsp; 共4条 &nbsp; 第1/1页 &nbsp; <a href="#" style="cursor:hand">首页</a>&nbsp;
                <a style="cursor:hand" href="#">上一页</a>&nbsp;
                <a style="cursor:hand" href="#">下一页</a>&nbsp;
                <a style="cursor:hand" href="#">尾页</a>&nbsp;
                &nbsp;
            </td>
        </tr>
    </table>

    </td>
    </tr>
    </table>
</FORM>
</BODY>
</html>


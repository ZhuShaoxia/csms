<%@ page import="com.csms.dao.DepotDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.entity.Depot" %><%--
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
    <title>出库单</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/cjcalendar.js"></script>
    <script type="text/javascript" src="../js/addFunction.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/aaa.css"/>
    <script type="text/javascript" src="../js/aaa.js"></script>
</head>
<script language="javascript">
    var CalendarWebControl = new atCalendarControl();
</script>
<SCRIPT LANGUAGE="javaScript">
    function saveInfo(obj) {
        if (obj.date.value == "") {
            alert("请选择日期");
            return false;
        }
        if (obj.depotID.value == "") {
            alert("请选择仓库");
            return false;
        }
        if (obj.consignee.value == "") {
            alert("请输入接收人");
            return false;
        }
        if (obj.tel.value == "") {
            alert("请输入接收人电话");
            return false;
        }
        if (obj.address.value == "") {
            alert("请输入发往地址");
            return false;
        }
    }
    function back() {
        window.location.href = "/csms/stock/OutOrderServlet?action=list"
    }
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="mig0101" ID="idmig0101" METHOD="POST" ACTION="/csms/stock/OutOrderServlet?action=insert"
      ONSUBMIT="return saveInfo(this)">

    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">出库单</td>

            <td class="headerbar61"><p align="right">
                <input type=submit value=" 保 存 ">
                <input type=button value=" 返 回 " onClick="back()">
            </p></td>
        </tr>
    </table>
    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>
    <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="textbar81" width="15%">发往地址</td>
            <td class="textbar01" width="35%">
                <input type="text" name="address" value="" style="width:200px"></td>

            <td class="textbar81" width="15%">单据日期</td>
            <td class="textbar01" width="35%">
                <input type="text" name="date" class="tcal" value="" placeholder="请选择日期"/>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">所出仓库</td>
            <td class="textbar01" width="35%">
                <select name="depotID" style="width:200px">
                    <option value="" selected>------</option>
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
                </select></td>
            <td class="textbar81" width="15%">接收人</td>

            <td class="textbar01" width="35%"><input type="text" name="consignee" style="width:200px"></td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">接收人电话</td>
            <td class="textbar01" width="35%">
                <input type="text" value="" name="tel" style="width:200px"></td>

            <td class="textbar81" width="15%"></td>
            <td class="textbar01" width="35%"></td>

        </tr>
        <tr>
            <td class="textbar81" width="15%">备注</td>
            <td class="textbar01" colspan="3" width="85%">
                <textarea cols="80" rows="4" name="profile"></textarea></td>
        </tr>
    </table>

    <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
        <tr>
            <td></td>
        </tr>
    </table>


</FORM>
</BODY>
</html>



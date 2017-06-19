<%@ page import="com.csms.entity.Depot" %>
<%@ page import="com.csms.dao.DepotDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.util.DATEUtil" %>
<%--
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
    <title>采购入库单详细</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/page.js"></script>
    <script type="text/javascript" src="../js/cjcalendar.js"></script>
    <script type="text/javascript" src="../js/addFunction.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/aaa.css"/>
    <script type="text/javascript" src="../js/aaa.js"></script>
</head>
<script language="javascript">
    var CalendarWebControl = new atCalendarControl();
</script>
<SCRIPT LANGUAGE="javaScript">
//    <!--
//    var trFlag = 0;
//    function tabMove0(objId, position) {
//        if (event.keyCode == 13) {
//            document.getElementById(objId).childNodes[2].innerHTML = '07长面包新款';
//            document.getElementById(objId).childNodes[3].innerHTML = '045';
//            tabMove(objId, position);
//        }
//    }
//    function goto(strURL) {
//        document.forms[0].action = strURL;
//        document.forms[0].submit();
//    }
//
//    function del(id) {
//        if (confirm("您确定删除该条明细？")) {
//            alert("删除成功！");
//        }
//    }
//
//    function save() {
//        alert('保存成功');
//    }
//    function setValue() {
//        document.forms[0].gys.value = "610";
//    }
//    function delCom(id) {
//        if (id == "1") {
//            document.forms[0].gys.value = "";
//        } else {
//            document.forms[0].sccj.value = "";
//        }
//    }
//    function setValue1() {
//        document.forms[0].sccj.value = "";
//    }
//    -->
    function saveInfo(obj) {
        if (obj.supplier.value==""){
            alert("供货商不能为空");
            return false;
        }
        if (obj.date.value==""){
            alert("日期不能为空");
            return false;
        }
        if (obj.depotID.value==""){
            alert("所入仓库不能为空");
            return false;
        }
        if (obj.maker.value==""){
            alert("制单人也不能为空");
            return false;
        }

    }
    function back() {
        window.location.href="/csms/stock/InOrderListServlet"
    }
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<%--<%--%>
<%--InOrder inOrder = (InOrder) session.getAttribute("inOrder");--%>
<%--%>--%>
<FORM NAME="mig0101" ID="idmig0101" METHOD="POST" ACTION="/csms/stock/InOrderInsertServlet" ONSUBMIT="return saveInfo(this)">

    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">入库单</td>
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
            <td class="textbar81" width="15%">供货商</td>
            <td class="textbar01" width="35%">
                <input type="text" value="" size="20" name="supplier">
            </td>

            <td class="textbar81" width="15%">入库日期</td>
            <td class="textbar01" width="35%">
                <input type="text" name="date" class="tcal" value="<%=DATEUtil.getDate()%>"/>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">所入仓库</td>
            <td class="textbar01" width="35%">
                <select name="depotID" style="width:152px">
                    <%
                        DepotDao depotDao = new DepotDao();
                        List<Depot> depots = depotDao.getDepotList();
                    %>
                    <option value="" selected="true">------</option>
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

            <td class="textbar81" width="15%">制单人</td>
            <td class="textbar01" width="35%">
                <input type="text" name="maker" value="" size="20">
            </td>
        </tr>


        <tr>
            <td class="textbar81" width="15%">备注</td>
            <td class="textbar01" width="85%" colspan="3">
                <textarea name="profile" cols="80" rows="4"></textarea>
            </td>
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



<%@ page import="java.util.List" %>
<%@ page import="com.csms.entity.ClothesNumber" %>
<%@ page import="com.csms.dao.ClothesNumberDao" %><%--
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
    function delInfo(id) {
        if (confirm("您确定删除该条记录？")) {
            alert("删除成功！");
            window.location.href = "/csms/system/ClothesNumberRemoveServlet?id=" + id
        }
    }

    function doAdd() {
        document.forms[0].action = "CLOTHING1003.jsp";
        document.forms[0].submit();
    }

    -->
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="idFrmMain" ID="idmig0101" METHOD="POST" ACTION="/csms/system/ClothesNumberSearchServlet">

    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">货号查询</td>
            <td class="headerbar63" width="50%" colspan="1"><p align="right">
                <input type=submit value=" 查 询 " onClick=""></p></td>
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

            <td class="textbar81" width="15%">货号</td>
            <td class="textbar01" width="35%">
                <input type="text" name="clothesNumber" value="" style="width:210px "></td>
            <td class="textbar81" width="15%">品名</td>
            <td class="textbar01" width="35%">
                <input type="text" name="brand" value="" style="width:210px "></td>
        </tr>

    </table>

    <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
        <tr>
            <td></td>
        </tr>
    </table>

    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="headerbar61" width="50%" colspan="1">货号明细</td>
            <td class="headerbar63" width="50%" colspan="1"><p align="right">
                <input type=submit value=" 新 增 " onClick="JavaScript:doAdd();"></p></td>
        </tr>
    </table>
    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>
    <%
        List<ClothesNumber> clothesNumbers = (List<ClothesNumber>) session.getAttribute("clothesNumbers");
        if (clothesNumbers == null) {
            ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
            clothesNumbers = clothesNumberDao.getClothesNumberList();
        }
    %>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="0" bgcolor="gray">
        <tr>
            <td width="100%" colspan="1">
                <table border="0" cellspacing="1" cellpadding="2" width="100%">
                    <tr>
                        <td width="5%" class="headerbar82">序号</td>
                        <td width="18%" class="headerbar82">货号</td>
                        <td width="20%" class="headerbar82">品名</td>
                        <td width="15%" class="headerbar82">出厂价</td>
                        <td width="15%" class="headerbar82">零售价</td>
                        <td width="15%" class="headerbar82">库存</td>
                        <td class="headerbar82">操作</td>
                    </tr>

                    <%
                        for (int i = 0; i < clothesNumbers.size(); i++) {
                            int n = i + 1;
                            ClothesNumber clothesNumber = clothesNumbers.get(i);
                            if (n % 2 == 0) {
                    %>
                    <tr>
                        <td class="gridbar11" align="center"><%=n%>
                        </td>
                        <td class="gridbar11" align="center"><a href="#"
                                                                onclick="javascript:goto('/csms/system/ClothesNumberInfoServlet?id=<%=clothesNumber.getId()%>');"><%=clothesNumber.getClothesNumber()%>
                        </a>
                        </td>
                        <!--<td class="gridbar11" align="center"> 大红色</td>-->
                        <!--<td class="gridbar11" align="center"> 160</td>-->
                        <td class="gridbar11" align="center"><%=clothesNumber.getBrand()%>
                        </td>
                        <td class="gridbar11" align="center"><%=clothesNumber.getInPrice()%>
                        </td>
                        <td class="gridbar11" align="center"><%=clothesNumber.getOutPrice()%>
                        </td>
                        <td class="gridbar11" align="center"><%=clothesNumber.getStock()%>
                        </td>

                        <td class="gridbar11" align="center">
                            <img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onClick="javascript:delInfo('<%=clothesNumber.getId()%>')" style="cursor:hand"/></td>
                    </tr>

                    <%

                    } else {

                    %>
                    <tr>
                        <td class="gridbar01" align="center"><%=n%>
                        </td>
                        <td class="gridbar01" align="center"><a href="#"
                                                                onclick="javascript:goto('/csms/system/ClothesNumberInfoServlet?id=<%=clothesNumber.getId()%>');"><%=clothesNumber.getClothesNumber()%>
                        </a>
                        </td>
                        <!--<td class="gridbar11" align="center"> 大红色</td>-->
                        <!--<td class="gridbar11" align="center"> 160</td>-->
                        <td class="gridbar01" align="center"><%=clothesNumber.getBrand()%>
                        </td>
                        <td class="gridbar01" align="center"><%=clothesNumber.getInPrice()%>
                        </td>
                        <td class="gridbar01" align="center"><%=clothesNumber.getOutPrice()%>
                        </td>
                        <td class="gridbar01" align="center"><%=clothesNumber.getStock()%>
                        </td>

                        <td class="gridbar01" align="center">
                            <img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onClick="javascript:delInfo('<%=clothesNumber.getId()%>')" style="cursor:hand"/></td>
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


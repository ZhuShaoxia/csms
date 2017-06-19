<%@ page import="com.csms.dao.ClothesNumberDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.dao.ColorDao" %>
<%@ page import="com.csms.dao.SizeDao" %>
<%@ page import="com.csms.entity.*" %><%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<head>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=gb2312">
    <META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
    <META HTTP-EQUIV="content-style-type" CONTENT="text/css">
    <title>入库单详细</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/page.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/aaa.css"/>
    <script type="text/javascript" src="../js/aaa.js"></script>
</head>

<SCRIPT LANGUAGE="javaScript">
    <!--
    function saveInfo(obj) {
        if (obj.clothesNumberID.value == "") {
            alert("请选择货号");
            return false;
        }
        if (obj.colorID.value == "") {
            alert("请选择商品颜色");
            return false;
        }
        if (obj.sizeID.value == "") {
            alert("请选择商品尺码");
            return false;
        }
        if (obj.num.value == "") {
            alert("请输入商品数量");
            return false;
        }
        alert("保存成功");
    }

    function backs(id) {
        window.location.href = "/csms/stock/OutOrderServlet?action=info&id=" + id;
    }


    -->
</SCRIPT>
<%
    OutOrder outOrder = (OutOrder) session.getAttribute("outOrder");
    int stateID = outOrder.getStateID();
%>
<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="idFrmMain" ID="idmig0102" METHOD="POST" ACTION="/csms/stock/OutOrderDetailServlet?action=add"
      ONSUBMIT="return saveInfo(this)">
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="headerbar61" width="15%" colspan="1">出库单详细</td>
            <td class="headerbar63" width="85%" colspan="1">
                <input type="submit" name="save70302002" value=" 保 存 ">
                &nbsp;
                <input type="button" name="back70302003" onClick="backs('<%=outOrder.getId()%>')" value=" 返 回 ">
            </td>
        </tr>
    </table>

    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>

    <input type="hidden" name="outOrderNumber" value="<%=outOrder.getOutOrderNumber()%>">
    <input type="hidden" name="id" value="<%=outOrder.getId()%>">
    <table border="0" width="100%" id="table1" cellspacing="1" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="textbar81" width="15%" colspan="1">货号</td>
            <td class="textbar01" width="85%" colspan="1">
                <select name="clothesNumberID" style="width:152px">
                    <option value="">------</option>
                    <%
                        ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
                        List<ClothesNumber> clothesNumbers = clothesNumberDao.getClothesNumberList();
                        for (ClothesNumber clothesNumber : clothesNumbers) {
                    %>
                    <option value="<%=clothesNumber.getId()%>"><%=clothesNumber.getClothesNumber()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%" colspan="1">色号</td>
            <td class="textbar01" width="85%" colspan="1">
                <select name="colorID" style="width:210px ">
                    <option value="" selected="selected">请选择</option>
                    <%
                        ColorDao colorDao = new ColorDao();
                        List<Color> colors = colorDao.getColorList();
                        for (Color c : colors) {
                    %>
                    <option value="<%=c.getId()%>"><%=c.getColor()%>
                    </option>
                    <%
                        }
                    %>
                </select></td>
        </tr>
        <tr>
            <td class="textbar81" width="15%" colspan="1">尺码</td>
            <td class="textbar01" width="85%" colspan="1"><select name="sizeID" style="width:210px ">
                <option value="" selected="selected">请选择</option>
                <%
                    SizeDao sizeDao = new SizeDao();
                    List<Size> sizes = sizeDao.getSizeList();
                    for (Size s : sizes) {

                %>
                <option value="<%=s.getId()%>"><%=s.getSize()%>
                </option>
                <%
                    }
                %>
            </select></td>
        </tr>
        <tr>
            <td class="textbar81" width="15%" colspan="1">数量</td>
            <%
                String msg = String.valueOf(session.getAttribute("ERROR_NUM"));
                if (msg.equals("null")){
                    msg = "请输入整数";
                }
            %>
            <td class="textbar01" width="85%" colspan="1"><input name="num" value="" style="width:210px;">
                <%=msg%>
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





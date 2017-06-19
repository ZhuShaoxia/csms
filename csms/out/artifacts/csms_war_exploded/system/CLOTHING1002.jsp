<%@ page import="com.csms.entity.ClothesNumber" %>
<%@ page import="com.csms.dao.ColorDao" %>
<%@ page import="com.csms.entity.Color" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.dao.SizeDao" %>
<%@ page import="com.csms.entity.Size" %>
<%@ page import="com.csms.dao.ClothesNumberDao" %>
<%@ page import="com.csms.entity.Clothes" %>
<%@ page import="com.csms.dao.ClothesDao" %>
<%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<head>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=gb2312">
    <META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
    <META HTTP-EQUIV="content-style-type" CONTENT="text/css">
    <title>品牌修改</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/cjcalendar.js"></script>
    <script language="javascript" src="../js/page.js"></script>
</head>
<script language="javascript">
    var CalendarWebControl = new atCalendarControl();
</script>
<SCRIPT LANGUAGE="javaScript">
    function delCom(id) {
        if (id == '1') {
            document.idFrmMain.gys.value = "";
        } else {
            document.idFrmMain.sccj.value = "";
        }
    }

    function saveInfo(obj) {
        if (obj.inPrice.value == "") {
            alert("出厂价不能为空");
            return false;
        }
        if (obj.outPrice.value == "") {
            alert("零售价不能为空");
            return false;
        }
        alert("保存成功！");
    }

    function back() {
        window.location.href = "/csms/system/ClothesNumberListServlet";
    }

</SCRIPT>
<BODY BACKGROUND="../image/bg.gif">
<%
    ClothesNumber clothesNumber = (ClothesNumber) session.getAttribute("clothesNumber");
%>
<FORM NAME="idFrmMain" ID="idFrmMain" METHOD="POST"
      ACTION="/csms/system/ClothesNumberModifyServlet?id=<%=clothesNumber.getId()%>"
      ONSUBMIT="return saveInfo(this)">
    <table border="0" width="100%">
        <tr>
            <td width="100%" colspan="0" rowspan="0" align="center" valign="center">
                <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
                    <tr>
                        <td class="headerbar61" width="50%">货号详细</td>
                        <td class="headerbar63" width="50%"><input type="submit" name="save70302002"
                                                                   value=" 保 存 ">
                            <input type="button" name="save70302002" onClick="javascript:back()" value=" 返 回 ">
                            &nbsp; </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td width="100%" colspan="0" rowspan="0" align="center" valign="center">
                <table border="0" width="100%" id="table1" cellspacing="1" cellpadding="2" bgcolor="gray">
                    <tr>
                        <td class="textbar81" width="15%">货号</td>
                        <td class="textbar01" width="35%"><input type="text"
                                                                 value="<%=clothesNumber.getClothesNumber()%>" size="15"
                                                                 style="width:210px " disabled="false"></td>
                        <td class="textbar81" width="15%">品名</td>
                        <td class="textbar01" width="35%"><input type="text" value="<%=clothesNumber.getBrand()%>"
                                                                 size="15"
                                                                 style="width:210px " disabled="false"></td>
                    </tr>
                    <tr>
                        <td width="15%" class="textbar81">出厂价</td>
                        <td class="textbar01" width="35%"><input type="text" value="<%=clothesNumber.getInPrice()%>"
                                                                 size="15"
                                                                 style="width:210px " name="inPrice"></td>
                        <td class="textbar81" width="15%">零售价</td>
                        <td class="textbar01" width="35%"><input type="text" value="<%=clothesNumber.getOutPrice()%>"
                                                                 size="15"
                                                                 style="width:210px " name="outPrice"></td>
                    </tr>


                </table>
                <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
                    <tr>
                        <td></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</FORM>
<form method="post" action="/csms/system/ClothesNumberInfoServlet?action=search&id=<%=clothesNumber.getId()%>">

    <table border="0" width="100%">
        <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
            <tr>
                <td class="headerbar61">具体商品查询</td>
                <td class="headerbar63" width="50%" colspan="1"><p align="right">
                    <input type="submit" value=" 查 询 " onClick=""></p></td>
            </tr>
        </table>

        <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
            <tr>
                <td></td>
            </tr>
        </table>

        <table border="0" width="100%" id="table1" cellspacing="1" cellpadding="2" bgcolor="gray">
            <tr>
                <td width="15%" class="textbar81">色号</td>
                <td class="textbar01" width="35%"><select name="colorID" style="width:210px ">
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

                <td width="15%" class="textbar81">尺码</td>
                <td class="textbar01" width="35%"><select name="sizeID" style="width:210px ">
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
                            <td width="15%" class="headerbar82">货号</td>
                            <td width="20%" class="headerbar82">品名</td>
                            <td width="15%" class="headerbar82">颜色</td>
                            <td width="15%" class="headerbar82">尺寸</td>
                            <td width="15%" class="headerbar82">库存</td>
                            <td class="headerbar82">操作</td>
                        </tr>
                        <%
                            ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
//                            int clothesNumberID = clothesNumberDao.getIDByClothesNumber(clothesNumber.getClothesNumber());
//                            ClothesDao clothesDao = new ClothesDao();
//                            List<Clothes> clothes = clothesDao.getClothesList(clothesNumberID);
                            List<Clothes> clothes = (List<Clothes>) session.getAttribute("clothes");
                            for (int i = 0; i < clothes.size(); i++) {
                                Clothes c = clothes.get(i);
                                ClothesNumber cn = clothesNumberDao.getClothesNumberByID(c.getClothesNumberID());
                                Size size = sizeDao.getSizeByID(c.getSizeID());
                                Color color = colorDao.getColorByID(c.getColorID());
                                int n = i + 1;
                                if (n % 2 == 0) {

                        %>

                        <tr>
                            <td class="gridbar11" align="center"><%=n%>
                            </td>
                            <td class="gridbar11" align="center"><%=cn.getClothesNumber()%>
                            </td>
                            <td class="gridbar11" align="center"><%=cn.getBrand()%>
                            </td>
                            <td class="gridbar11" align="center"><%=color.getColor()%>
                            </td>
                            <td class="gridbar11" align="center"><%=size.getSize()%>
                            </td>
                            <td class="gridbar11" align="center"><%=c.getStock()%>
                            </td>

                            <td class="gridbar11" align="center">
                                <img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                     onClick="javascript:del('<%=c.getId()%>')" style="cursor:hand"/></td>
                        </tr>
                        <%

                        } else {

                        %>
                        <tr>
                            <td class="gridbar01" align="center"><%=n%>
                            </td>
                            <td class="gridbar01" align="center"><%=cn.getClothesNumber()%>
                            </td>
                            <td class="gridbar01" align="center"><%=cn.getBrand()%>
                            </td>
                            <td class="gridbar01" align="center"><%=color.getColor()%>
                            </td>
                            <td class="gridbar01" align="center"><%=size.getSize()%>
                            </td>
                            <td class="gridbar01" align="center"><%=c.getStock()%>
                            </td>

                            <td class="gridbar01" align="center">
                                <img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                     onClick="javascript:del('<%=c.getId()%>')" style="cursor:hand"/></td>
                        </tr>
                        <%

                                }
                            }
                        %>
                    </table>
        </table>
    </table>
</form>
</BODY>
</html>



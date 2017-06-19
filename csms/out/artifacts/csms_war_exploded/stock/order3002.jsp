<%@ page import="com.csms.dao.DepotDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.csms.dao.ClothesNumberDao" %>
<%@ page import="com.csms.dao.ColorDao" %>
<%@ page import="com.csms.entity.*" %>
<%@ page import="com.csms.dao.SizeDao" %><%--
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

    function goto(strURL) {
        window.location.href = "/csms/stock/" + strURL;
    }
    //
    //    function del(id) {
    //        if (confirm("您确定删除该条明细？")) {
    //            alert("删除成功！");
    //        }
    //    }
    //
    //    function goSearch() {
    //        document.forms[0].action = "order3002.jsp";
    //        document.forms[0].submit();
    //    }
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
        alert("保存成功");
    }
    function back() {
        window.location.href = "/csms/stock/order3001.jsp";
    }
    function delInfo(id) {
        if (confirm("您确定删除该条数据？")) {
            alert("删除成功！");
            window.location.href = "/csms/stock/OutOrderDetailServlet?action=remove&id=" + id;
        }
    }
    function confirm() {
        alert("订单已确认 不能修改");
    }
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<%
    OutOrder outOrder = (OutOrder) session.getAttribute("outOrder");
    int stateID = outOrder.getStateID();
%>
<FORM NAME="mig0101" ID="idmig0101" METHOD="POST"
      ACTION="/csms/stock/OutOrderServlet?action=modify&id=<%=outOrder.getId()%>" ONSUBMIT="return saveInfo(this)">

    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">出库单详细</td>

            <td class="headerbar61"><p align="right">
                <%
                    if (stateID == 1) {

                %>
                <input type=submit value=" 保 存 ">
                <%

                } else {

                %>
                <input type=button value=" 保 存 " onclick="confirm()">

                <%

                    }
                %>
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
            <td class="textbar81" width="15%">单据号</td>
            <td class="textbar01" width="35%">
                <input type="text" value="<%=outOrder.getOutOrderNumber()%>" readonly style="width:200px"></td>
            <td class="textbar81" width="15%">单据日期</td>
            <td class="textbar01" width="35%">
                <input type="text" name="date" class="tcal" value="<%=outOrder.getOutOrderDate()%>"
                       placeholder="请选择日期"/>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">所出仓库</td>
            <%
                if (stateID == 1) {

            %>
            <td class="textbar01" width="35%">
                <select name="depotID" style="width:152px">
                    <%
                        DepotDao depotDao = new DepotDao();
                        List<Depot> depots = depotDao.getDepotList();
                    %>
                    <option value="">------</option>
                    <%
                        for (Depot depot : depots) {
                            if (depot.getId() == outOrder.getDepotID()) {
                    %>
                    <option value="<%=depot.getId()%>" selected="selected"><%=depot.getDepot()%>
                    </option>
                    <%
                    } else {
                    %>
                    <option value="<%=depot.getId()%>"><%=depot.getDepot()%>
                    </option>

                    <%
                            }
                        }
                    %>
                </select>
            </td>
            <%
            } else {

            %>
            <td class="textbar01" width="35%">
                <select name="depotID" style="width:152px" readonly>
                    <%
                        DepotDao depotDao = new DepotDao();
                        List<Depot> depots = depotDao.getDepotList();
                    %>
                    <option value="">------</option>
                    <%
                        for (Depot depot : depots) {
                            if (depot.getId() == outOrder.getDepotID()) {
                    %>
                    <option value="<%=depot.getId()%>" selected="selected"><%=depot.getDepot()%>
                    </option>
                    <%
                    } else {
                    %>
                    <option value="<%=depot.getId()%>"><%=depot.getDepot()%>
                    </option>

                    <%
                            }
                        }
                    %>
                </select>
            </td>
            <%
                }
            %>
            <td class="textbar81" width="15%">接收人</td>
            <td class="textbar01" width="35%">
                <%
                    if (stateID == 2) {
                %>
                <input type="text" name="consignee" style="width:200px" value="<%=outOrder.getConsignee()%>" readonly>
                <%
                } else {

                %>
                <input type="text" name="consignee" style="width:200px" value="<%=outOrder.getConsignee()%>">
                <%
                    }
                %>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">接收人电话</td>
            <td class="textbar01" width="35%">
                <%
                    if (stateID == 2) {
                %>
                <input type="text" value="<%=outOrder.getTel()%>" name="tel" style="width:200px" readonly>
                <%
                } else {

                %>
                <input type="text" value="<%=outOrder.getTel()%>" name="tel" style="width:200px">
                <%
                    }
                %>

            </td>

            <td class="textbar81" width="15%">发往地址</td>
            <td class="textbar01" width="35%">
                <%
                    if (stateID == 2) {
                %>
                <input type="text" name="address" value="<%=outOrder.getAddress()%>" style="width:200px" readonly>
                <%
                } else {

                %>
                <input type="text" name="address" value="<%=outOrder.getAddress()%>" style="width:200px" >
                <%
                    }
                %>
            </td>

        </tr>
        <tr>
            <td class="textbar81" width="15%">备注</td>
            <td class="textbar01" colspan="3" width="85%">
                <%
                    if (stateID == 2) {
                %>
                <textarea cols="80" rows="4" name="profile" readonly><%=outOrder.getProfile()%></textarea>
                <%
                } else {

                %>
                <textarea cols="80" rows="4" name="profile" ><%=outOrder.getProfile()%></textarea>
                <%
                    }
                %>
            </td>
        </tr>
    </table>
</FORM>
<form action="" method="post">
    <table border=0 cellspacing=0 cellpadding=0 width="100%" height=5>
        <tr>
            <td></td>
        </tr>
    </table>

    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="headerbar61" colspan="1">出库单明细</td>
            <td class="headerbar63" colspan="1">
                <p align="right">
                    <%
                        if (stateID == 1) {
                    %>
                    <input type=button value=" 新增明细 " onClick="goto('order3003.jsp')"></p>
                <%

                } else {

                %>
                <input type="button" value=" 新增明细" onclick="confirm()">
                <%
                    }

                %>
            </td>
        </tr>
    </table>
    <%
        List<OutOrderDetail> outOrderDetails = (List<OutOrderDetail>) session.getAttribute("outOrderDetails");
    %>

    <table id="tab0" border="0" cellspacing="1" cellpadding="2" width="100%" bgcolor="gray">
        <tr>
            <td width="5%" class="headerbar82">序号</td>
            <td width="20%" class="headerbar82">货号</td>
            <td width="20%" class="headerbar82">品名</td>
            <td width="15%" class="headerbar82">色号</td>
            <td width="15%" class="headerbar82">尺码</td>
            <td width="15%" class="headerbar82">数量</td>
            <td class="headerbar82">操作</td>
        </tr>
        <%
            for (int i = 0; i < outOrderDetails.size(); i++) {
                int n = i + 1;
                OutOrderDetail outOrderDetail = outOrderDetails.get(i);

                ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
                ClothesNumber clothesNumber = clothesNumberDao.getClothesNumberByID(outOrderDetail.getClothesNumberID());

                ColorDao colorDao = new ColorDao();
                Color color = colorDao.getColorByID(outOrderDetail.getColorID());

                SizeDao sizeDao = new SizeDao();
                Size size = sizeDao.getSizeByID(outOrderDetail.getSizeID());
                if (n % 2 == 0) {
        %>
        <tr>
            <td class="gridbar11" align="center"><%=n%>
            </td>
            <td class="gridbar11" align="center"><%=clothesNumber.getClothesNumber()%>
            </td>
            <td class="gridbar11"><%=clothesNumber.getBrand()%>
            </td>
            <td class="gridbar11"><%=color.getColor()%>
            </td>
            <td class="gridbar11"><%=size.getSize()%>
            </td>
            <td class="gridbar11" align="center"><%=outOrderDetail.getNum()%>
            </td>
            <td class="gridbar11" align="center">
                <%
                    if (stateID == 1) {
                %>
                <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onClick="delInfo('<%=outOrderDetail.getId()%>')"/></a>
                <%
                } else {
                %>
                <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"/></a>
                <%
                    }
                %>
            </td>
        </tr>
        <%

        } else {

        %>
        <tr>
            <td class="gridbar01" align="center"><%=n%>
            </td>
            <td class="gridbar01" align="center"><%=clothesNumber.getClothesNumber()%>
            </td>
            <td class="gridbar01"><%=clothesNumber.getBrand()%>
            </td>
            <td class="gridbar01"><%=color.getColor()%>
            </td>
            <td class="gridbar01"><%=size.getSize()%>
            </td>
            <td class="gridbar01" align="center"><%=outOrderDetail.getNum()%>
            </td>
            <td class="gridbar01" align="center">
                <%
                    if (stateID == 1) {
                %>
                <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onClick="delInfo('<%=outOrderDetail.getId()%>')"/></a>
                <%
                } else {
                %>
                <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"/></a>
                <%
                    }
                %>
            </td>
        </tr>
        <%
                }
            }

        %>
    </table>

</form>
</BODY>
</html>



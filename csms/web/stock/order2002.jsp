<%@ page import="java.util.List" %>
<%@ page import="com.csms.dao.*" %>
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
        if (obj.depotID.value == "") {
            alert("请选择所入仓库");
            return false;
        }
        if (obj.supplier.value == "") {
            alert("请输入来源");
            return false;
        }
        alert("保存成功");

    }
    function delInfo(id) {
        if (confirm("您确定删除该条数据？")) {
            alert("删除成功！");
            window.location.href = "/csms/stock/InOrderDetailServlet?action=remove&id=" + id;
        }
    }
    function backs() {
        window.location.href="/csms/stock/order2001.jsp";
    }
    function confirm() {
        alert("订单已确认 不能修改");
    }
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<%
    InOrder inOrder = (InOrder) session.getAttribute("inOrder");
    int stateID = inOrder.getStateID();
%>
<FORM NAME="mig0101" ID="idmig0101" METHOD="POST" ACTION="/csms/stock/InOrderModifyServlet?id=<%=inOrder.getId()%>"
      ONSUBMIT="return saveInfo(this)">

    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">入库单详细</td>
            <td class="headerbar61"><p align="right">
                <%
                    if (stateID==1){

                %>
                <input type=submit value=" 保 存 ">
                <%

                    }else {

                %>
                <input type=button value=" 保 存 " onclick="confirm()">

                <%

                    }
                %>
                <input type=button value=" 返 回 " onClick=backs()>
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
                <input type="text" value="<%=inOrder.getInOrderNumber()%>" readonly size="20">
            </td>
            <td class="textbar81" width="15%">入库日期</td>
            <td class="textbar01" width="35%">
                <input type="text" name="date" class="tcal" value="<%=inOrder.getInOrderDate()%>" disabled="true"/>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">所入仓库</td>
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
                            if (depot.getId() == inOrder.getDepotID()) {
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
                            if (depot.getId() == inOrder.getDepotID()) {
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
            <td class="textbar81" width="15%">来源</td>
            <td class="textbar01" width="35%">
                <%
                    if (stateID == 1) {

                %>
                <input type="text" name="supplier" value="<%=inOrder.getSupplier()%>" size="20">

                <%

                } else {

                %>
                <input type="text" name="supplier" value="<%=inOrder.getSupplier()%>" size="20" readonly>
                <%

                    }
                %>
            </td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">备注</td>
            <td class="textbar01" width="85%" colspan="3">
                <%
                    if (stateID == 1) {

                %>
                <textarea name="profile" cols="80" rows="4"><%=inOrder.getProfile()%></textarea>

                <%

                } else {

                %>
                <textarea name="profile" cols="80" rows="4" readonly><%=inOrder.getProfile()%></textarea>
                <%

                    }
                %>

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
            <td class="headerbar61" colspan="1">入库单明细</td>
            <td class="headerbar63" colspan="1">
                <p align="right">
                    <%
                        if (stateID == 1) {

                    %>
                    <input type=button value=" 新增明细 " onClick="goto('order2003.jsp');"></p>

                <%

                } else {

                %>
                <input type=button value=" 新增明细 " onclick="confirm()"></p>
                <%

                    }
                %>

            </td>
        </tr>
    </table>

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
            InOrderDetailDao inOrderDetailDao = new InOrderDetailDao();
            List<InOrderDetail> inOrderDetails = inOrderDetailDao.getInOrderDetailList(inOrder.getInOrderNumber());
            ClothesNumberDao clothesNumberDao = new ClothesNumberDao();
            ColorDao colorDao = new ColorDao();
            SizeDao sizeDao = new SizeDao();
            for (int i = 0; i < inOrderDetails.size(); i++) {
                int n = i + 1;
                InOrderDetail detail = inOrderDetails.get(i);
                ClothesNumber clothesNumber = clothesNumberDao.getClothesNumberByID(detail.getclothesNumberID());
                Color color = colorDao.getColorByID(detail.getColorID());
                Size size = sizeDao.getSizeByID(detail.getSizeID());
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
            <td class="gridbar11" align="center"><%=detail.getNum()%>
            </td>
            <td class="gridbar11" align="center">

                <%
                    if (stateID == 1) {

                %>
                <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onClick="delInfo('<%=detail.getId()%>')"/></a>


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
            <td class="gridbar01" align="center"><%=detail.getNum()%>
            </td>
            <td class="gridbar01" align="center">
                <%
                    if (stateID == 1) {

                %>
                <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                 onClick="delInfo('<%=detail.getId()%>')"/></a>


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


</FORM>
</BODY>
</html>



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
    //    function delCom(id) {
    //        if (id == '1') {
    //            document.idFrmMain.gys.value = "";
    //        } else {
    //            document.idFrmMain.sccj.value = "";
    //        }
    //    }

    function saveInfo(obj, error) {
        if (obj.clothesNumber.value == "") {
            alert("货号不能为空");
            return false;

        }
        if (obj.brand.value == "") {
            alert("品名不能为空");
            return false;

        }
        if (obj.inPrice.value == "") {
            alert("出厂价不能为空");
            return false;

        }
        if (obj.outPrice.value == "") {
            alert("零售价不能为空");
            return false;

        }
//        if (error != null && error != "") {
//            alert("货号已存在，请重新输入");
//            return false;
//        }
        alert("保存成功！");
    }

    function back() {
        window.location.href = "/csms/system/ClothesNumberListServlet";
    }

</SCRIPT>
<BODY BACKGROUND="../image/bg.gif">
<%
    String error = (String) session.getAttribute("ERROR_CLOTHESNUMBER");
%>
<FORM NAME="idFrmMain" ID="idFrmMain" METHOD="POST" ACTION="/csms/system/ClothesNumberInsertServlet" ONSUBMIT="return saveInfo(this,'<%=error%>')">
    <table border="0" width="100%">
        <tr>
            <td width="100%" colspan="0" rowspan="0" align="center" valign="center">
                <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
                    <tr>
                        <td class="headerbar61" width="50%">货号详细</td>
                        <td class="headerbar63" width="50%"><input type="submit" name="save70302002" value=" 保 存 ">
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
                        <td class="textbar01" width="35%"><input type="text" value="" size="15"
                                                                 style="width:210px " name="clothesNumber">
                            <%
                                if (error != null) {

                            %>
                            <%=error%>
                            <%
                                }
                            %>
                        </td>
                        <td class="textbar81" width="15%">品名</td>
                        <td class="textbar01" width="35%"><input type="text" value="" size="15"
                                                                 style="width:210px " name="brand"></td>
                    </tr>

                    <tr>
                        <td width="15%" class="textbar81">出厂价</td>
                        <td class="textbar01" width="35%"><input type="text" value="" size="15"
                                                                 style="width:210px " name="inPrice"></td>
                        <td class="textbar81" width="15%">零售价</td>
                        <td class="textbar01" width="35%"><input type="text" value="" size="15"
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
</BODY>
</html>




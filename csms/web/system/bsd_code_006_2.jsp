<%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<head>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=gb2312">
    <META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
    <META HTTP-EQUIV="content-style-type" CONTENT="text/css">
    <title></title>
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

    function saveInfo(obj, error) {
        if (obj.depotNumber.value == "") {
            alert("仓库编号不能为空");
            return false;
        }
        if (obj.depot.value == "") {
            alert("仓库名称不能为空");
            return false;
        }
        if (obj.supervisor.value == "") {
            alert("联系人不能为空");
            return false;
        }
        if (obj.tel.value == "") {
            alert("联系人电话不能为空");
            return false;
        }
        if (obj.capacity.value == "") {
            alert("仓储量不能为空");
            return false;
        }
//        if (error != null && error != "") {
//            alert("仓库编号已存在，请重新输入")
//            return false;
//        }

        alert("保存成功！");
    }

    function back() {
        window.location.href = "/csms/system/DepotListServlet";
    }

</SCRIPT>
<BODY BACKGROUND="../image/bg.gif">
<%
    String errorMsg = (String) session.getAttribute("ERROR_DEPOTNUMBER");
%>
<FORM NAME="idFrmMain" ID="idFrmMain" METHOD="POST" ACTION="/csms/system/DepotInsertServlet"
      onsubmit="return saveInfo(this,'<%=errorMsg%>')">
    <table border="0" width="100%">
        <tr>
            <td width="100%" colspan="0" rowspan="0" align="center" valign="center">
                <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
                    <tr>
                        <td class="headerbar61" width="50%" colspan="1">仓库详细</td>
                        <td class="headerbar63" width="50%" colspan="1">
                            <input type="submit" name="save70302002" value=" 保 存 ">
                            <input type="button" name="save70302002" onclick="javascript:back()" value=" 返 回 ">&nbsp;
                        </td>
                    </tr>
                </table>
            </td>
        </tr>

        <tr>
            <td width="100%" colspan="0" rowspan="0" align="center" valign="center">
                <table border="0" width="100%" id="table1" cellspacing="1" cellpadding="2" bgcolor="gray">
                    <tr>
                        <td class="textbar81" width="15%" colspan="1">仓库编号</td>
                        <td class="textbar01" width="85%" colspan="1">
                            <input type="text" value="" name="depotNumber" size="15">
                            <%
                                if (errorMsg != null) {
                            %>
                            <%=errorMsg%>
                            <%
                                }%>
                        </td>

                    </tr>

                    <tr>
                        <td class="textbar81" width="15%">仓库名称</td>
                        <td class="textbar01" width="85%">
                            <input type="text" value="" name="depot" size="15">
                        </td>
                    </tr>


                    <tr>
                        <td class="textbar81" width="15%">联系人</td>
                        <td class="textbar01" width="85%">
                            <input type="text" value="" name="supervisor" size="15">
                        </td>
                    </tr>

                    <tr>
                        <td class="textbar81" width="15%">联系电话</td>
                        <td class="textbar01" width="85%">
                            <input type="text" value="" name="tel" size="15">
                        </td>
                    </tr>

                    <tr>
                        <td class="textbar81" width="15%">仓储量</td>
                        <td class="textbar01" width="85%">
                            <input type="text" value="" name="capacity" size="15">
                        </td>
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





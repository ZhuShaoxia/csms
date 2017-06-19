<%@ page import="java.util.List" %>
<%@ page import="com.csms.entity.User" %>
<%@ page import="com.csms.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <META HTTP-EQUIV="content-type" CONTENT="text/html; charset=GB2312">
    <META HTTP-EQUIV="content-script-type" CONTENT="text/JavaScript">
    <META HTTP-EQUIV="content-style-type" CONTENT="text/css">
    <title>用户管理</title>
    <link rel="stylesheet" href="../css/cjpm.css">
</head>

<SCRIPT LANGUAGE="javaScript">
    <!--
    function goto() {
        document.forms[0].action = "USER1003.jsp";
        document.forms[0].submit();
    }

    function del(id) {
        if (confirm("您确定删除该条数据？")) {
            alert("删除成功！");
            window.location.href="/csms/system/UserRemoveServlet?id="+id;
        }
    }

    function goSearch() {
        document.forms[0].action = "/csms/system/UserSearchServlet";
        document.forms[0].submit();
    }

    -->
</SCRIPT>

<BODY BACKGROUND="../image/bg.gif">
<FORM NAME="mig0101" ID="idmig0101" METHOD="POST" ACTION="" ONSUBMIT="return false">
    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61">用户明细查询</td>
            <td class="headerbar61"><p align="right"><input type=submit value=" 查 询 " onClick="goSearch();"></p></td>
        </tr>
    </table>

    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>
    <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="textbar81" width="15%">用户姓名</td>
            <td class="textbar01" width="35%">
                <input type="text" name="userName" size="20">
            </td>
            <td class="textbar81" width="15%">用户登录号</td>
            <td class="textbar01" width="35%"><input type="text" name="userAccount" size="20"></td>
        </tr>

    </table>
    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>
    <table border="0" width="100%" id="table1" cellspacing="0" cellpadding="2" bgcolor="gray">
        <tr>
            <td class="headerbar61" width="50%" colspan="1">用户明细表</td>
            <td class="headerbar63" width="50%" colspan="1">
                <input type="button" name="add" tabindex="1" onClick="javascript:goto()" value=" 新 增 "></td>
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
                        <td width="35%" class="headerbar82">用户登录号</td>
                        <td width="35%" class="headerbar82">用户姓名</td>
                        <td class="headerbar82">操作</td>
                    </tr>
                    <%
                        List<User> users = (List<User>) session.getAttribute("users");
                        if (users == null) {
                            UserDao userDao = new UserDao();
                            users = userDao.getUserList();
                        }
                        for (int i = 0; i < users.size(); i++) {
                            User user = users.get(i);
                            int n = i + 1;
                            if (n % 2 == 1) {

                    %>
                    <tr>
                        <td class="gridbar11" align="center"><%=n%>
                        </td>
                        <td class="gridbar11" align="center"><a
                                href="/csms/system/UserInfoServlet?id=<%=user.getId()%>"><%=user.getUserAccount()%>
                        </a></td>
                        <td class="gridbar11" align="center"><a
                                href="/csms/system/UserInfoServlet?id=<%=user.getId()%>"><%=user.getUserName()%>
                        </a></td>
                        <td class="gridbar11" align="center">
                            <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                             onClick="javascript:del('<%=user.getId()%>')"/></a></td>
                    </tr>
                    <%
                    } else {
                    %>
                    <tr>
                        <td class="gridbar01" align="center"><%=n%>
                        </td>
                        <td class="gridbar01" align="center"><a href="/csms/system/UserInfoServlet?id=<%=user.getId()%>"><%=user.getUserAccount()%>
                        </a></td>
                        <td class="gridbar01" align="center"><a href="/csms/system/UserInfoServlet?id=<%=user.getId()%>"><%=user.getUserName()%>
                        </a></td>
                        <td class="gridbar01" align="center">
                            <a href="#"><img src="../image/del.gif" align="bottom" border="0" alt="删除"
                                             onClick="javascript:del('<%=user.getId()%>')"/></a></td>
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
                &nbsp; 共3条 &nbsp; 第1/1页 &nbsp; <a href="#" style="cursor:hand">首页</a>&nbsp;
                <a style="cursor:hand" href="#">上一页</a>&nbsp;
                <a style="cursor:hand" href="#">下一页</a>&nbsp;
                <a style="cursor:hand" href="#">尾页</a>&nbsp;
                &nbsp;
            </td>
        </tr>
    </table>


</FORM>
</BODY>
</html>


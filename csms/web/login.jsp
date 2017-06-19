<%@ page import="com.csms.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<HTML>
<HEAD>
    <TITLE>服装库存管理系统</TITLE>
    <META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
    <link rel="stylesheet" type="text/css" href="css/frame.css">
    <script>
        function go(obj, account, pwd) {

            if (obj.userAccount.value == "") {
                alert("用户账号不能为空");
                return false;
            }
            if (obj.password.value == "") {
                alert("用户密码不能为空");
                return false;
            }

            if (account.toString() != obj.userAccount.value && pwd.toString() != obj.password.value) {
                alert("账号密码输入错误");
                return false;
            }
        }
    </script>
</HEAD>
<BODY BACKGROUND="image/bg.gif">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="100%" height="32">
            <TABLE WIDTH=100% BORDER=0 CELLPADDING=0 CELLSPACING=0>
                <TR>
                    <TD width="376"><IMG SRC="image/top_1.jpg" WIDTH=376 HEIGHT=54 ALT=""></TD>
                    <TD width="90%" align="right" valign="bottom" background="image/top_2.jpg">
                        <table width="100%" border="0" align="right" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="5%" align="center">&nbsp;</td>
                                <td width="80%" align="right">&nbsp;</td>
                                <td width="27%" align="right">
                                    <table width="75%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="128" height="24" align="right"></td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </TD>
                </TR>
            </TABLE>
        </td>
    </tr>

</table>
<%
    UserDao userDao = new UserDao();
    String pwd = userDao.getAdminPwd();
    String userAccount = "admin";
%>

<FORM NAME="idFrmMain" METHOD="POST" ACTION="/csms/LoginServlet"
      onsubmit="return go(this,'<%=userAccount%>','<%=pwd%>')">
    <table width="100%" height="40%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td align="center" valign="middle">
                <table width="300" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td>用户名:<input name="userAccount" type="text" size="10">
                        </td>
                        <td>&nbsp;密码:<input name="password" type="password" size="10"></td>

                        <td width="42"><input name="imageField" type="submit" value="登录" alt="登录" width="42" height="32"
                                              border="0"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>

</BODY>
</HTML>



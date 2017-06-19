<%@ page import="com.csms.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">
    <title>在线通知</title>
    <link rel="stylesheet" href="../css/cjpm.css">
    <script type="text/javascript" src="../js/page.js"></script>
    <script type="text/javascript" src="../js/msn_message.js"></script>
    <style type="text/css">
        body {
            background-color: #f8f7f7;
        }


    </style>
    <script language="JavaScript">
        function save() {
            alert("保存成功！");
        }
        function checkPwd(obj, pwd) {

            if (obj.password.value == "") {
                alert("不能为空");
                return false;
            }
            if (obj.pwd1.value == "") {
                alert("不能为空");
                return false;
            }
            if (obj.pwd1.value == "") {
                alert("不能为空");
                return false;
            }
            if (pwd != obj.password.value) {
                alert("密码输入错误，请重新输入");
                return false;
            }

            if (obj.pwd1.value != obj.pwd2.value) {
                alert("两次输入密码不一致");
                return false;
            }
            alert("修改成功");
        }
    </script>
</head>

<BODY BACKGROUND="../image/bg.gif">
<%
    UserDao userDao = new UserDao();
    String pwd = userDao.getAdminPwd();
%>
<FORM NAME="BSDTERM001" ID="BSDTERM001" METHOD="POST" ACTION="/csms/common/ModifyPasswordServlet"
      onsubmit="checkPwd(this,'<%=pwd%>')">
    <table border=0 cellspacing=0 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="headerbar61"> 修改密码</td>
            <td class="headerbar61"><p align="right"><input type=submit value=" 保 存 "></p>
            </td>
        </tr>
    </table>
    <table border=0 cellspacing=0 cellpadding=2 width="100%" height="5">
        <tr>
            <td></td>
        </tr>
    </table>
    <table border=0 cellspacing=1 cellpadding=2 width="100%" bgcolor="gray">
        <tr>
            <td class="textbar81" width="15%">旧密码</td>
            <td class="textbar01" width="85%"><input type="password" name="password" size="30"></td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">新密码</td>
            <td class="textbar01" width="85%"><input type="password" name="pwd1" size="30"></td>
        </tr>
        <tr>
            <td class="textbar81" width="15%">确认密码</td>
            <td class="textbar01" width="85%"><input type="password" name="pwd2" size="30"></td>
        </tr>
    </table>

</FORM>
</body>
</html>




<%--
  Created by IntelliJ IDEA.
  User: zhuxiaolei
  Date: 2017/6/3
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<HTML>
<HEAD>
    <TITLE>button</TITLE>
    <META content="text/html; charset=gb2312" http-equiv=Content-Type>
    <SCRIPT>
        function oa_tool() {
//alert(window.parent.oa_frame.cols);
            if (window.parent.sss.cols == "180,10,*") {
                frameshow.src = "../image/m1.gif";
                oa_tree.title = "显示子栏目"
                window.parent.sss.cols = "0,10,*";
            }
            else {
                frameshow.src = "../image/m2.gif";
                oa_tree.title = "隐藏子栏目"
                window.parent.sss.cols = "180,10,*";
            }
        }
    </SCRIPT>
    <META content="MSHTML 5.00.2920.0" name=GENERATOR>
    <style type="text/css">
        <!--
        .hand {
            cursor: hand;
        }

        -->
    </style>
</HEAD>
<BODY leftMargin=0
      topMargin=0>
<TABLE width="9" height="100%" border=0 cellPadding=0 cellSpacing=0>
    <TBODY>
    <TR>
        <TD align="center" valign="middle" bgcolor="#B3B3B3">
            <DIV id=oa_tree onclick=oa_tool(); title=隐藏子栏目><IMG src="../image/m2.gif" width="10" height="67"
                                                                align=middle class="hand"
                                                                id=frameshow></DIV>
        </TD>
    </TR>
    </TBODY>
</TABLE>
</BODY>
</HTML>



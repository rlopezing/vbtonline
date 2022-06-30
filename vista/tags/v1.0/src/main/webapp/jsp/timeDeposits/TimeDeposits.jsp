<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: Rgodoy
  Date: 13/07/12
  Time: 11:33 AM
  To change this template use File | Settings | File Templates.
--%>

    <link href='../vbtonline/resources/images/favicon.png' rel='shortcut icon' type='image/png'>
    <script type="text/javascript" src="../vbtonline/resources/js/timeDeposits/timeDeposits.js?"<% Math.random(); %>></script>

<s:form action="Login">
    <table SUMMARY='tabla'align="center" border="0">
        <tr><td>Id</td><td><s:textfield id="id" key="id"/></td></tr>

        <tr><td colspan="2" align="center"><input type="button" id="enviar" value="entrar" /></td></tr>
        <tr><td colspan="2" align="center"><span id="mensaje"></span></td></tr>

    </table>
</s:form>

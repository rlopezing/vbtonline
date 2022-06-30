<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<%--
  Created by IntelliJ IDEA.
  User: Manuel Serrano
  Date: 21/05/2010
  Time: 01:46:26 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="es" class="no-js"> <!--<![endif]-->
<head>
    <title>::: VBT Bank &amp; Trust Online :::</title>
    <meta charset="UTF-8">
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='expires' content='0'>
    <meta http-equiv='pragma' content='no-cache'>

    <meta charset="UTF-8" />
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
    <meta name="description" content="Acceso a la aplicación web de Vencred" />
    <meta name="author" content="Rodolfo Rivas" />
    <link rel="shortcut icon" href="../favicon.ico">

    <link href='../vbtonline/resources/images/favicon.png' rel='shortcut icon' type='image/png'>


    <link rel="stylesheet" href="../vbtonline/resources/css/login/demo.css?_expirecache_=1" type="text/css" />
    <link rel="stylesheet" href="../vbtonline/resources/css/login/style.css?_expirecache_=1" type="text/css" />
    <link rel="stylesheet" href="../vbtonline/resources/css/teclado/keyboard.css?_expirecache_=1" type="text/css" />
    <link rel="stylesheet" href="../vbtonline/resources/css/teclado/smoothness.css?_expirecache_=1" type="text/css" />

    <script src="../vbtonline/resources/jquery/jquery-1.11.3.js?_expirecache_=1"></script>
    <script src="../vbtonline/resources/jquery/jquery-ui.js?_expirecache_=1"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/base/json2.js?_expirecache_=1"></script>
    <script src="../vbtonline/resources/js/base/jquery.keyboard.js?_expirecache_=1"></script>

    <script type="text/javascript" src="../vbtonline/resources/js/base/nucleo.js?_expirecache_=1"></script>
    <script src="../vbtonline/resources/js/login/login.js?_expirecache_=1"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/login/jquery-idleTimeout.js?_expirecache_=1" charset="utf-8"></script>



</head>
<body>
<div id="encabezado">
<%--    <div id="div_logo">
        <img id="logo" src="../vbtonline/resources/images/logo.gif" alt="logo">
        <div id="label_idiomas"><span id="idioma_es">Español </span>| <span id="idioma_en">English</span></div>
    </div>--%>

</div>
<div id="marco_trabajo" >
    <div id="cuerpo">
        <div id="div_login" style="">
            <jsp:include page="Login.jsp" />
        </div>
    </div>
</div>
<!-- PIE DE PAGINA -->
<div id="copyright">
    <hr style="color:#195266;width: 94%;">
    <b>VBT Bank &amp; Trust, Ltd.</b>
    <br>
    Copyright &copy;2004 Derechos Reservados
</div>
</body>
</html>
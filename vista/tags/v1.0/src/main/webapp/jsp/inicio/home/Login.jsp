<%@ page import="java.util.Random" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
    response.setDateHeader ("Expires", -1);

%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="es" class="no-js"> <!--<![endif]-->

<head><title>::: VBT Bank &amp; Trust Online :::</title>
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='expires' content='0'>
    <meta http-equiv='pragma' content='no-cache'>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <link type="text/css"  href='../vbtonline/resources/images/favicon.png' rel='shortcut icon'  >
    <link type="text/css"  media="print" rel="stylesheet" href="../vbtonline/resources/css/style_print.css?_expirecache_=1" />

    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/style.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/ui.notify.css?_expirecache_=1"/>
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/tabla/table_jui.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/tabla/jquery-ui-1.8.4.custom.css?_expirecache_=1"  />
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/teclado/keyboard.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/teclado/smoothness.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/menu.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel="stylesheet" href="../vbtonline/resources/css/poppup.css?_expirecache_=1"  />
    <link type="text/css"  media="screen" rel='stylesheet' href="../vbtonline/resources/css/calendar/calendar_theme.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel='stylesheet' href="../vbtonline/resources/css/calendar/fullcalendar.css?_expirecache_=1" />
    <link type="text/css"  media="screen" rel='stylesheet' href="../vbtonline/resources/css/calendar/fullcalendar.print.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/login/demo.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/login/style.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/login/animate-custom.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/style_jpaginate.css?_expirecache_=1"/>

    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery-1.7.2.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery-ui.1.8.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/json2.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery.ui.widget.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.notify.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.keyboard.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/printArea.js?_expirecache_=1"></script>
    <script type='text/javascript' language="javascript" src="../vbtonline/resources/js/base/jMonthCalendar.min.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/jquery-idleTimeout.js?_expirecache_=1" charset="utf-8"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/nucleo.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/funciones.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/login.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/home/home.js?_expirecache_=1"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/base/jquery.crypt.js?Math.random()"></script>
</head>
<body>
<div id="capa_carga"><img src="../vbtonline/resources/images/ajax-loader.gif" alt="" class="iagen_carga"/></div>
<div id="encabezado">
    <div id="div_logo">
        <img id="logo" src="../vbtonline/resources/images/logo.gif" alt="logo">
        <div id="label_idiomas">
            <img id="idioma_es" src="resources/images/flag_espanol.png" class="seleccionIdioma"/>
            <img id="idioma_en" src="resources/images/flag_ingles.png" class="seleccionIdioma current_seleccionIdioma"/></div>
    </div>

</div>

<div id="marco_trabajo">
<div id="cuerpo">
<div id="box_menu">
</div>


<div id="espacio_divs" >
<div id="div_carga_espera2" class="imagen_carga oculto">
    <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" />
</div>
<div id="div_mensajes_error" class="alerta oculto" >
    <div id="mensaje_error">
        error
    </div>
    <div id="cerrar_div_mensaje_error">[X]</div>
</div>
<div id="div_login" class="opcion_seleccionada div_menu2 ">
    <jsp:include page="../../login/Login.jsp" />
</div>
<div id="div_SALIR" class="oculto div_menu">
        <jsp:include page="../../login/salir.jsp" />
</div>
<!-- PIE DE PAGINA -->
<div >
    <table width="135" align="center" border="0" cellpadding="2" cellspacing="0" title="Click to Verify - This site chose VeriSign SSL for secure e-commerce and confidential communications.">
        <tr>
            <td width="135" align="center" valign="top"><script type="text/javascript" src="https://seal.verisign.com/getseal?host_name=secure.vbtbank.com&size=S&use_flash=YES&use_transparent=YES&lang=en"></script><br />
                <a href="http://www.verisign.com/ssl-certificate/" target="_blank" style="color:#000000; text-decoration:none; font:bold 7px verdana,sans-serif; letter-spacing:.5px; text-align:center; margin:0px; padding:0px;">ABOUT SSL CERTIFICATES</a></td>
        </tr>
    </table>
</div>
<div id="copyright">
    <hr style="color:#195266;width: 94%;">
    <b>VBT Bank &amp; Trust, Ltd.</b>
    <br>
    Copyright &copy;2004 Derechos Reservados
</div>
</div>
</div>
</div>

</body>
</html>

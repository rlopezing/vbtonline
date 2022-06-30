<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 18/07/12
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>::: VBT Bank &amp; Trust Online :::</title>
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='expires' content='0'>
    <meta http-equiv='pragma' content='no-cache'>
    <link type="text/css"  href='../vbtonline/resources/images/favicon.png' rel='shortcut icon'  >
    <link type="text/css"  media="all" rel="stylesheet" href="../vbtonline/resources/css/style.css?_expirecache_=1" />
    <script src="../vbtonline/resources/jquery/jquery-1.11.3.js?_expirecache_=1"></script>
    <script language="javascript">
        function detectarPlataforma(){
            var plataforma= navigator.platform;
            var passw= document.getElementById('pwdClave');
            if(plataforma == 'Win32' || plataforma == 'Win64' || plataforma == 'Linux' || plataforma == 'MacIntel')
            {
                var ref = window.open("/vbtonline/Login_abrir_popup","VBT_Bank_Trust_Online",'fullscreen=yes, scrollbars=yes,menubar=no,resizable=no,location=no,status=no,toolbar=no');
                $("#marco").attr("style",'display:""');
                plataforma="PC";
            }else{
                setTimeout("location.href='/vbtonline/Login_abrir_popup'", 100);
                plataforma="NO-PC";
            }
        }

    </script>
</head>
<body onload="detectarPlataforma();">
<div id="marco" style="display:none">
    <div id="encabezado">
        <div id="div_logo">
            <img id="logo" src="../vbtonline/resources/images/logo.gif" alt="logo">
        </div>
        <div id="marco_trabajo" >
            <div id="cuerpo">
                <div style="" id="espacio_divs">
                    <img width="300" height="300" onclick="detectarPlataforma();" style="cursor: pointer; border-radius: 20px 20px 20px 20px; border: 3px solid rgb(0, 0, 0); margin-left: 450px;  margin-top: 86px;" src="../vbtonline/resources/images/oficina.jpg" alt="">
                    <p style="margin-bottom: 160px;margin-left:440px"> para volver acceder a la aplicacion hacer click en la imagen</p>
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

    </div>
</div>

</body>
</html>
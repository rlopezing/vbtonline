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
    <meta charset="UTF-8">
    <meta http-equiv='cache-control' content='no-cache'>
    <meta http-equiv='expires' content='0'>
    <meta http-equiv='pragma' content='no-cache'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <!--FAVICON-->
    <link rel="apple-touch-icon" sizes="180x180" href="../vbtonline/resources/img/icons/favicon/apple-touch-icon.png" />
    <link rel="icon" type="image/png" sizes="32x32" href="../vbtonline/resources/img/icons/favicon/favicon-32x32.png" />
    <link rel="icon" type="image/png" sizes="16x16" href="../vbtonline/resources/img/icons/favicon/favicon-16x16.png" />
    <link rel="manifest" href="../vbtonline/resources/img/icons/favicon/site.webmanifest" />
    <link rel="mask-icon" href="../vbtonline/resources/img/icons/favicon/safari-pinned-tab.svg" color="#5bbad5" />
    <meta name="msapplication-TileColor" content="#ffffff" />
<%--    <meta name="theme-color" content="#ffffff" />    <link type="text/css"  media="print" rel="stylesheet" href="../vbtonline/resources/css/style_print.css?_expirecache_=1" />--%>
    <meta name="theme-color" content="#ffffff" />
    <link type="text/css"  media="print" rel="stylesheet" href="../vbtonline/resources/css/custom-styles-print.css?_expirecache_=1" />

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
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/intlTelInput.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/jquery-ui.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/jquery.fileupload.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/jquery.modal.min.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/custom-styles.css?_expirecache_=1"/>
    <link type="text/css"  media="screen"  rel="stylesheet" href="../vbtonline/resources/css/responsive.css?_expirecache_=1"/>

    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery-1.7.2.js?_expirecache_=1"></script>

    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery-ui.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/idle-timer.min.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/json2.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.notify.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.keyboard.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/printArea.js?_expirecache_=1"></script>
    <script type='text/javascript' language="javascript" src="../vbtonline/resources/js/base/jMonthCalendar.min.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/jquery-idleTimeout.js?_expirecache_=1" charset="utf-8"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/jquery.modal.min.js"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/nucleo.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/funciones.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/home/home.js?_expirecache_=1"></script>
    <%--<script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/loginHome.js?_expirecache_=1"></script>--%>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/login.js?_expirecache_=1"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/base/jquery.crypt.js?Math.random()"></script>

    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/vendor/jquery.ui.widget.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery.iframe-transport.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/jquery/jquery.fileupload.js?_expirecache_=1"></script>
    <!--FONTAWESOME-->
    <link rel="stylesheet" href="../vbtonline/resources/fontawesome/css/all.min.css" />
    <script src="../vbtonline/resources/fontawesome/js/all.min.js"></script>
</head>
<body onload="CargarHomeJSONData();">

    <script>
        var tempateCargado = false;
    </script>
    
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

    ga('create', 'UA-54103717-1', 'auto');
    ga('send', 'pageview');

</script>
<script>
    (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
    })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
    ga('create', 'UA-XXXXXX-XX', 'example.com');
    ga('require', 'displayfeatures');
    ga('send', 'pageview');
</script>

<script>
    /* $(document).ready(function () {
         $(document).idleTimer(240000); // 4 minutos
         $(document).on("idle.idleTimer", function () {
             // se hace un llamado a la función que emite la alerta cuando el usuario se mantiene inactivo 4 minutos.
             console.log("document ready");
             loadAlertTimeout();
         });
     }); */
</script>
<div id="dialog_act"  style="display:none"></div>
<div id="dialog"  style="display:none"></div>
<div id="capa_carga" class="loader loader--modifier">
<%--    <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" class="iagen_carga"/>--%>
    <div class="lds-roller">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div><span class="loader__text">Loading</span>
</div>
<%--<div id="encabezado">
    <div id="div_logo">
        <img id="logo" src="../vbtonline/resources/images/logo.gif" alt="logo">
        <div id="label_idiomas">
            <img id="idioma_es" src="resources/images/flag_espanol.png" class="seleccionIdioma"/>
            <img id="idioma_en" src="resources/images/flag_ingles.png" class="seleccionIdioma current_seleccionIdioma"/></div>
    </div>

</div>--%>

<header class="header">
    <div class="header__nav">
        <a class="header__logo header__logo--responsive"  onclick="seleccionarOpcionMenu('home')" style="cursor:pointer;"
        ><img
                src="../vbtonline/resources/img/logo.svg"
                alt="Logo VBT Bank &amp; Trust"/>
        </a>
        <div id="icon-menu" class="icon-menu">
            <i class="fas fa-bars header__burguer"></i>
        </div>
    </div>
    <div id="menu" class="header__content">
        <img class="header__mark" src="../vbtonline/resources/img/mark.svg" alt="mark icon svg" />
        <div class="header__general">
            <ul class="general-menu container" id="general-menu">
<%--                <li class="general-menu__item" class="general-menu__item">
                    <a class="general-menu__link" href="#">INFO</a>
                </li>
                <li class="general-menu__item">
                    <a class="general-menu__link" href="portfolio.html">ALL MY PORTFOLIO</a>
                </li>--%>
                <li class="header__languages">
                    <img id="idioma_es_home" src="resources/images/flag_espanol.png" class="seleccionIdioma" alt="">
                    <img id="idioma_en_home" src="resources/images/flag_ingles.png" class="seleccionIdioma current_seleccionIdioma" alt="">
                </li>
                <li class="general-menu__item general-menu__item--profile">
                    <div class="general-menu__profile">
                        <img
                                class="general-menu__avatar"
                                src="../vbtonline/resources/img/avatar.png"
                                alt="user photo"
                        />
                        <span class="general-menu__user" id="nameMyInformation_profile"></span>
                        <img class="general-menu__down" src="../vbtonline/resources/img/icons/ic_chevron_down_gray.svg" alt="icon down" />
                    </div>
                    <div class="main-menu__submenu submenu">
                        <span class="submenu__title" id="TitleMenuProfile">PROFILE</span>
                        <ul class="submenu__list submenu__list--col1">
                            <li class="submenu__item">
                                <img class="submenu__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_lock.png" alt="">
                                <a id="TAGTitleMisDatos" class="submenu__link">My Information</a>
                            </li>
                            <li class="submenu__item">
                                <img class="submenu__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_payments.png" alt="">
                                <span id="menu_TAGTitleSalir" class="submenu__link">Logout</span>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
        <div class="header__menu">
            <div class="header__menu--container container">
                <a class="header__logo" onclick="seleccionarOpcionMenu('home')"  style="cursor:pointer;" 
                ><img
                        src="../vbtonline/resources/img/logo-alternative.svg"
                        alt="Logo VBT Bank &amp; Trust"
                /></a>
                <ul class="main-menu" id="main-menu">
                </ul>
            </div>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            const btnMenu = document.getElementById("icon-menu");
            const menu = document.getElementById("menu");

            btnMenu.addEventListener("click", () => {
                menu.classList.toggle("header__content--active");
            });

        });
    </script>
</header>

<div id="marco_trabajo">
    <div id="cuerpo">
<%--        <div id="box_menu">--%>
<%--        </div>--%>


        <div id="espacio_divs" >
<%--            <div id="div_bloqueo" class="oculto"></div>--%>
            <div id="div_carga_espera" class="loader oculto">
<%--                <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" />--%>
                <div class="lds-roller">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div><span class="loader__text">Loading</span>
            </div>

            <div id="div_carga_espera2" class="loader oculto">
<%--                <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" />--%>
                <div class="lds-roller">
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                    <div></div>
                </div><span class="loader__text">Loading</span>
            </div>
            <div id="div_mensajes_error" class="alerta oculto" >
                <div class="alerta__top">
                    <div class="alerta__info">
                        <img class="alerta__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png">
                        <spam id="tag_spam_alert" class="alerta__title">Alert</spam>
                    </div>
                    <div id="cerrar_div_mensaje_error"><i class="far fa-times-circle"></i></div>
                </div>
                <div class="alerta__content" id="mensaje_error">
                    error
                </div>
            </div>

            <%--
                AUTOR:          RRANGEL
                FECHA:          09/10/2014
                NOMBRE:         popup_pendientes_notiFC
                DESCRIPCION:    POPUP DE NOTIFICACION DE SOLICITUDES PENDIENTES POR APROBAR/LIBERAR.
            --%>         <%--
            <div id="popup_pendientes_notiFC" class="alerta oculto">
                <div id="popupTagNotiFC">You've got {0} new request(s).</div>
                <div id="popupTagCerrarFC" class="btn_errmsg_close"> [X] </div>
            </div>   --%>
<%--            <div id='poppup_mensaje_generico' class='oculto'>
                <span id="mensaje_generico"></span>
                <span id='cerrar_div_mensaje_generico' class="oculto">Cerrar</span>
            </div>--%>
            <div id='poppup_mensaje_generico' class="alerta oculto" >
                <div class="alerta__top">
                    <div class="alerta__info">
                        <img class="alerta__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png">
                        <spam class="alerta__title">Message / Mensaje</spam>
                    </div>
                    <div id='cerrar_div_mensaje_generico' class="oculto"><i class="far fa-times-circle"></i></div>
                </div>
                <div class="alerta__content" id="mensaje_generico">
                </div>
            </div>
            <%-- FIN popup_pendientes_notiFC --%>

            <!-- Informacion General -->

            <div id="div_login" class="oculto div_menu2 ">
                <jsp:include page="../../login/Login.jsp" />
            </div>
            <div id="div_home" class="opcion_seleccionada div_menu 0000000061_0 div_opcion_menu">
                <jsp:include page="contenido_home.jsp" />
            </div>
<%--
            <div id="div_CALENDARIO_BANCARIO" class="oculto div_menu 0000000062_0 div_opcion_menu">
                <jsp:include page="../calendario_bancario/calendario_bancario.jsp" />
            </div>
--%>

            <div id="div_FORMATOS" class="oculto div_menu 0000000007_0 div_opcion_menu">
                <jsp:include page="../../inicio/formatos/formatos.jsp" />
            </div>


            <div id="div_INSTRUCCIONES" class="oculto div_menu 0000000063_0 div_opcion_menu">
                <jsp:include page="../../inicio/instrucciones_transferencias/instrucciones_transferencia.jsp" />
                <%--  &lt;%&ndash;<jsp:include page="../../backOffice/ConsultarContratos.jsp" />&ndash;%&gt; --%>
            </div>

            <div id="div_CAJA_DE_CORREOS" class="oculto div_menu 0000000104_0 div_opcion_menu">
                <%--                <jsp:include page="../../inicio/mail/mailBox.jsp" />--%>
            </div>

            <div id="div_BLOQUEO_EMERGENCIA_BO" class="oculto div_menu 0000000103_0 div_opcion_menu">
                <jsp:include page="../../backOffice/tarjetas/BloqueoEmergenciaBO.jsp"/>
            </div>


            <!-- Cuentas -->
            <div id="div_SALDOS_CUENTAS" class="oculto div_menu 0000000009_0 div_opcion_menu">
                <jsp:include page="../../accounts/balancesAndTransactions/BalancesAndTransactions.jsp"/>
            </div>
            <div id="div_ESTADO_CUENTA" class="oculto div_menu 0000000010_0 div_opcion_menu">
                <jsp:include page="../../accounts/accountsStatements/AccountsStatements.jsp" />
            </div>
            <div id="div_BLOQUEOS_CUENTAS" class="oculto div_menu 0000000011_0 div_opcion_menu">
                <jsp:include page="../../accounts/blockedAmounts/BlockedAmounts.jsp" />
            </div>
            <%--<div id="div_cuentas_estado_test" class="oculto div_menu">--%>
            <%--<jsp:include page="../../accounts/Accounts.jsp" />--%>
            <%--</div>--%>

            <!-- Transferencias -->
            <div id="div_TRANSFERENCIA_INTERNA_MIS_CTAS"  class="oculto div_menu 0000000037_0 div_opcion_menu">
                <jsp:include page="../../transfers/betweenMyLinkedAccounts/BetweenMyLinkedAccounts.jsp" />
            </div>
            <div id="div_TRANSFERENCIA_EXTERNA"  class="oculto div_menu 0000000040_0 div_opcion_menu">
                <%--<jsp:include page="../../transfers/toOtherBanks/validarClaveOpeEsp222.jsp" />--%>
                <jsp:include page="../../transfers/toOtherBanks/ToOtherBanks.jsp" />
            </div>
            <div id="div_TRANSFERENCIA_EXTERNA_FC"  class="oculto div_menu div_opcion_menu">
                <%--<jsp:include page="../../transfers/toOtherBanks/validarClaveOpeEsp222.jsp" />--%>
                <%--jsp:include page="../../transfers/toOtherBanksJointSignatures/ToOtherBanksJointSignatures.jsp" />--%>

            </div>
            <div id="div_validarOpeEsp"  class="oculto div_menu div_opcion_menu">
                <jsp:include page="../../transfers/toOtherBanks/validarClaveOpeEsp.jsp" />
            </div>

            <div id="div_TRANSFERENCIA_INTERNA_TERCEROS"  class="oculto div_menu 0000000038_0 div_opcion_menu">
                <jsp:include page="../../transfers/toOtherClientInVBT/ToOtherClientInVBT.jsp" />
            </div>
            <div id="div_CONSULTA_TRANSFERENCIAS"  class="oculto div_menu 0000000039_0 div_opcion_menu">
                <jsp:include page="../../transfers/transferHistory/TransferHistory.jsp" />
            </div>
            <div id="div_DIRECTORIO"  class="oculto div_menu 0000000070_0 div_opcion_menu">
                <jsp:include page="../../transfers/templates/Templates.jsp" />
            </div>
            <div id="div_APROBAR_TRANSFERENCIA"  class="oculto div_menu 0000000042_0 div_opcion_menu">
                <jsp:include page="../../transfers/toOtherBanksJointSignatures/ApproveTransferManager.jsp" />
            </div>
            <div id="div_LIBERAR_TRANSFERENCIA"  class="oculto div_menu 0000000043_0 div_opcion_menu">
                <jsp:include page="../../transfers/toOtherBanksJointSignatures/ReleaseTransferManager.jsp" />
            </div>

            <!-- Colocaciones -->
            <div id="div_SALDOS_COLOCACIONES" class="oculto div_menu 0000000013_0 div_opcion_menu">
                <jsp:include page="../../timeDeposits/balancesAndTransactions/BalancesAndTransactions.jsp" />
            </div>
            <div id="div_BLOQUEOS_COLOCACIONES" class="oculto div_menu 0000000015_0 div_opcion_menu">
                <jsp:include page="../../timeDeposits/blockedAmounts/BlockedAmounts.jsp" />
            </div>
            <div id="div_CERTIFICADOS" class="oculto div_menu 0000000014_0 div_opcion_menu">
                <jsp:include page="../../timeDeposits/certificates/Certificates.jsp" />
            </div>
            <div id="div_VENCIMIENTOS" class="oculto div_menu 0000000016_0 div_opcion_menu">
                <jsp:include page="../../timeDeposits/maturities/Maturities.jsp" />
            </div>
            <div id="div_TDC_TIME_DEPOSITS" class="oculto div_menu 0000000101_0 div_opcion_menu">
                <jsp:include page="../../timeDeposits/td/TD.jsp" />
            </div>


            <!-- Fondos Mutuales -->
            <div id="div_SALDOS_FONDOS" class="oculto div_menu 0000000018_0 div_opcion_menu">
                <jsp:include page="../../mutualFunds/balancesAndTransactions/BalancesAndTransactions.jsp" />
            </div>
            <div id="div_BLOQUEOS_FONDOS" class="oculto div_menu 0000000019_0 div_opcion_menu">
                <jsp:include page="../../mutualFunds/blockedAmounts/BlockedAmounts.jsp" />
            </div>
            <div id="div_ESTADO_CUENTA_FONDOS" class="oculto div_menu 0000000090_0 div_opcion_menu">
                <jsp:include page="../../mutualFunds/accountStatements/AccountStatements.jsp" />
            </div>
            <div id="div_INFORMES" class="oculto div_menu 0000000099_0 div_opcion_menu">
                <jsp:include page="../../mutualFunds/reports/Reports.jsp" />
            </div>

            <!-- Otras Inversiones -->
            <div id="div_SALDOS_FONDOS_OI" class="oculto div_menu 0000000048_0 div_opcion_menu">
                <jsp:include page="../../OthersInvestments/balancesAndTransactions/BalancesAndTransactions.jsp" />
            </div>
            <div id="div_BLOQUEOS_FONDOS_OI" class="oculto div_menu 0000000049_0 div_opcion_menu">
                <jsp:include page="../../OthersInvestments/blockedAmounts/BlockedAmounts.jsp" />
            </div>
            <div id="div_ESTADO_CUENTA_FONDOS_OI" class="oculto div_menu 0000000091_0 div_opcion_menu">
                <jsp:include page="../../OthersInvestments/accountStatements/AccountStatements.jsp" />
            </div>

            <!-- Tarjetas de Credito -->
            <div id="div_MOVIMIENTOS_FACTURAR" class="oculto div_menu 0000000033_0 div_opcion_menu">
                <jsp:include page="../../creditCards/inTransitTransactions/InTransitTransactions.jsp" />
            </div>
            <div id="div_ESTADO_CUENTA_TARJETA" class="oculto div_menu 0000000034_0 div_opcion_menu">
                <jsp:include page="../../creditCards/accountStatement/AccountStatement.jsp" />
            </div>
            <div id="div_GESTION_RECLAMO" class="oculto div_menu 0000000035_0 div_opcion_menu">
                <jsp:include page="../../creditCards/claimManagement/ClaimManagement.jsp" />
            </div>
            <div id="div_BLOQUEO_TARJETA" class="oculto div_menu 0000000056_0 div_opcion_menu">
                <jsp:include page="../../creditCards/cardLock/CardLock.jsp" />
            </div>
            <div id="div_APROBAR_PAGO_TARJETAS" class="oculto div_menu 0000000095_0 div_opcion_menu">
                <jsp:include page="../../creditCards/JointSignatures/ApprovePaymentsTdcManager.jsp" />
            </div>
            <div id="div_LIBERAR_PAGO_TARJETAS" class="oculto div_menu 0000000096_0 div_opcion_menu">
                <jsp:include page="../../creditCards/JointSignatures/ReleasePaymentsTdcManager.jsp" />
            </div>
            <div id="div_CONSULTA_PAGOS_TDC" class="oculto div_menu 0000000098_0 div_opcion_menu">
                <jsp:include page="../../creditCards/paymentCard/paymentHistory.jsp" />
            </div>
            <div id="div_BLOQUEO_EMERGENCIA_TDC" class="oculto div_menu 0000000100_0 div_opcion_menu">
                <jsp:include page="../../creditCards/cardLock/CardLockEmergency.jsp" />
            </div>



            <!-- diseñe su banco -->
            <div id="div_PARAMETROS_PERSONALES" class="oculto div_menu 0000000045_0 div_opcion_menu">
                <jsp:include page="../../desingBank/parametrosPersonales.jsp" />
            </div>

            <div id="div_PARAMETROS_PERSONALES_FC" class="oculto div_menu 0000000053_0 div_opcion_menu">
                <jsp:include page="../../firmasConjuntas/parametrosPersonalesFC.jsp" />
            </div>
            <div id="div_CAMBIAR_CLAVE" class="oculto div_menu 0000000046_0 div_opcion_menu">
                <jsp:include page="../../desingBank/cambiarClave.jsp" />
            </div>
            <div id="div_CAMBIAR_CLAVE_PRINCIPAL" class="oculto div_menu ">
                <jsp:include page="../../desingBank/cambiarClavePrincipal.jsp" />
            </div>
            <%--Administrador cliente--%>
            <div id="div_USUARIOS_FC" class="oculto div_menu 0000000052_0 div_opcion_menu">
                <jsp:include page="../../firmasConjuntas/ConsultarUsuariosFC.jsp" />
            </div>

            <!-- Mi portafolio-->
            <div id="div_CONSOLIDADO" class="oculto div_menu 0000000021_0 div_opcion_menu">
                <jsp:include page="../../allMyPortafolio/AllMyPortafolio.jsp" />
            </div>
            <div id="div_MIS_DATOS" class="oculto div_menu 0000000023_0 div_opcion_menu">
                <jsp:include page="../../myInformation/MyInformation.jsp" />
            </div>
            <div id="div_SALIR" class="oculto div_menu">
                <jsp:include page="../../login/salir.jsp" />
            </div>
            <%--backoffice--%>
            <div id="div_USUARIO_CLIENTE" class="oculto div_menu 0000000029_0 div_opcion_menu">
                <jsp:include page="../../backOffice/ConsultarUsuariosContratos.jsp" />
            </div>
            <div id="div_USUARIO_CLIENTE_FC" class="oculto div_menu 0000000081_0 div_opcion_menu">
                <jsp:include page="../../backOffice/ConsultarUsuariosContratosFC.jsp" />
            </div>
            <div id="div_USUARIOS" class="oculto div_menu 0000000003_0 div_opcion_menu">
                <jsp:include page="../../backOffice/ConsultarUsuarios.jsp" />
            </div>
            <div id="div_GRUPOS" class="oculto div_menu 0000000028_0 div_opcion_menu">
                <jsp:include page="../../backOffice/Grupos/ConsultarGrupos.jsp" />
            </div>
            <div id="div_CONTRATOS" class="oculto div_menu 0000000004_0 div_opcion_menu">
                <jsp:include page="../../backOffice/ConsultarContratos.jsp" />
            </div>
            <div id="div_BITACORA" class="oculto div_menu 0000000005_0 div_opcion_menu">
                <jsp:include page="../../backOffice/bitacora.jsp" />
            </div>
            <div id="div_EXCEPCIONES" class="oculto div_menu 0000000006_0 div_opcion_menu">
                <jsp:include page="../../backOffice/VisorDeSucesos.jsp" />
            </div>
            <div id="div_terminos_condiciones_transferencia" class="oculto div_menu">
                <jsp:include page="../../transfers/terminosCondiciones.jsp" />
            </div>
            <div id="div_PARAMETROS_GLOBALES_VBT" class="oculto div_menu 0000000071_0 div_opcion_menu">
                <jsp:include page="../../backOffice/ParametrosGlobales.jsp" />
            </div>


            <div id="div_PARAMETROS_PERSONALES_USUARIOS" class="oculto div_menu">
                <jsp:include page="../../backOffice/ParametrosPersonales.jsp" />
            </div>

            <div id="div_CARTA_INSTRUCCION" class="oculto div_menu">
                <jsp:include page="../../inicio/clientInstruction/ClientInstruction.jsp" />
            </div>
            <div id="div_TARJETA_COORDENADAS" class="oculto div_menu">
                <jsp:include page="../../securityCard/generateSecurityCard.jsp" />
            </div>
            <div id="div_TARJETA_COORDENADAS_ACTIVA" class="oculto div_menu">
                <jsp:include page="../../securityCard/activateSecurityCard.jsp" />
            </div>
            <div id="div_METODOS_VALIDACION" class="oculto div_menu">
                <jsp:include page="../../validationMethods/validationMethods.jsp" />
            </div>

            <div id="div_LOG_SMS" class="oculto div_menu div_opcion_menu">
                <jsp:include page="../../backOffice/consultarLogSms.jsp" />
            </div>

            <div id="div_PAISES_NO_PERMITIDOS" class="oculto div_menu">
                <jsp:include page="../../backOffice/PaisesNoPermitidos.jsp" />
            </div>

            <div id="div_ANULAR_CARTA_INSTRUCCION" class="oculto div_menu">
                <jsp:include page="../../inicio/clientInstruction/ClientInstructionVoid.jsp" />
            </div>

            <div id="div_ROLES_FC" class="oculto div_menu 0000000083_0 div_opcion_menu">
                <jsp:include page="../../backOffice/Grupos/ConsultarRolesFC.jsp" />
            </div>

            <div id="div_INACTIVAR_BANCOS" class="oculto div_menu 0000000085_0 div_opcion_menu">
                <jsp:include page="../../backOffice/InactivarBancos.jsp"/>
            </div>

            <div id="div_AVISOS" class="oculto div_menu 0000000086_0 div_opcion_menu">
                <jsp:include page="../../backOffice/Avisos.jsp"/>
            </div>

            <div id="div_BLOQUEO_TARJETAS_BO" class="oculto div_menu 0000000088_0 div_opcion_menu">
                <jsp:include page="../../backOffice/BloquearTarjetas.jsp" />
            </div>
            <div id="div_PAGO_TARJETA" class="oculto div_menu 0000000089_0 div_opcion_menu">
                <jsp:include page="../../creditCards/paymentCard/paymentCard.jsp" />
            </div>
            <div id="div_SESIONES_ACTIVAS" class="oculto div_menu 0000000092_0 div_opcion_menu">
                <jsp:include page="../../backOffice/sesiones.jsp" />
            </div>
            <div id="div_PAGOS_TDC" class="oculto div_menu 0000000093_0 div_opcion_menu">
                <jsp:include page="../../backOffice/PagosTarjetas.jsp" />
            </div>
            <div id="div_CUENTAS_NO_PERMITIDAS" class="oculto div_menu 0000000094_0 div_opcion_menu">
                <jsp:include page="../../backOffice/CuentasNoPermitidas.jsp"/>
            </div>
            <div id="div_TRANSACCIONES_ESPECIALES" class="oculto div_menu 0000000097_0 div_opcion_menu">
                <jsp:include page="../../backOffice/TransaccionesEspeciales.jsp"/>
            </div>
            <!-- PIE DE PAGINA -->
            <!-- <jsp:include page="../../transfer-instructions.jsp" /> -->
            <jsp:include page="../../footer.jsp" />
        </div>
    </div>
</div>

<div class="notification-modal" id="detalle_transferencia_fc">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="assets/img//icons/ic_login_security_tips.png" alt=""/>
    </div>
    <div class="notification-modal__content" id="detalle_transferencia_fc_content">
        <div class="table">
            <table class="table__content table__content--summary">
                <tr class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGCuentaDebito2" class="table__item table__item--summary"><span></span></td>
                    <td id="detalle_RAccounts" class="table__item table__item--last"><span></span></td>
                </tr>
            </table>
        </div>
        <div id="BeneficiaryBankDT" class="table">
            <div><span id="detalle_transferencias_TAGBancoBeneficiario2" class="table__title">TITLE TABLE</span></div>
            <table class="table__content table__content--summary">
                <tr id="BeneficiaryBankDT1" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGEmailCodigoBancoBeneficiario" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RBankCode" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="BeneficiaryBankDT2" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGEmailNombreBancoBeneficiario" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RNameBank" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div class="table">
            <div ><span id="detalle_transferencias_TAGNombreBeneficiario2" class="table__title">TITLE TABLE</span></div>
            <table class="table__content table__content--summary">
                <tr id="detalle_RnameDT" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGBeneficiaryName" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_Rname" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_RAccountNumberDT" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGBeneficiaryAccount" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RAccountNumber" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGEmailBeneficiario" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RCorreo" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_RTelephoneNumber" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGBeneficiaryTelephone" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RTelephoneNumber" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div id="detalle_div_TOB_resumen_intermediary" class="table">
            <div ><span id="detalle_transferencias_TAGIntermediaryBank" class="table__title">TITLE TABLE</span></div>
            <table class="table__content table__content--summary">
                <tr id="detalle_div_RBankCodeIB" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGEmailCodigoBancoIntermediario" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RBankCodeIB" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_RNameBankIB" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGEmailNombreBancoIntermediario" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RNameBankIB" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div id="detalle_div_TOB_resumen_furtherCredit" class="table">
            <div ><span id="detalle_transferencias_TAGParaFuturoCredito2" class="table__title">TITLE TABLE</span></div>
            <table class="table__content table__content--summary">
                <tr id="detalle_div_RAccountNumberFFC" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGFurtherCreditAccount" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RAccountNumberFFC" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_RNameFFC" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGFurtherCreditName" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RNameFFC" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div class="table">
            <div ><span id="detalle_transferencias_TAGMontoInstrucciones2" class="table__title">TITLE TABLE</span></div>
            <table class="table__content table__content--summary">
                <tr id="montoDetalleOcultar" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGMonto2" class="table__item table__item--summary"><span>Amount</span></td>
                    <td id="detalle_RAmmountAI" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="unitDetalleOcultar" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGUnits2" class="table__item table__item--summary"><span>Units</span></td>
                    <td id="detalle_RUnitsAI" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="cancleacionHistoricoOcultar" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGCanelacion2" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RCancelAI" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_RReceiverInformation" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGConcepto" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_RReceiverInformation" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_estatus_TOB" class="table__row table__row--summary">
                    <td id="detalle_transferencias_TAGEstatusTransferencia" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_status_TOB" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div id="detalle_div_record_TOB" class="table">
            <div ><span id="detalle_transferencias_TAGHistorial" class="table__title">TITLE TABLE</span></div>
            <table class="table__content table__content--summary">
                <tr id='detalle_div_record_TOB1' class="table__row table__row--summary">
                    <td id="detalle_transferencias_usr_carga" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_transferencias_usr_carga2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id='detalle_div_record_TOB2' class="table__row table__row--summary">
                    <td id="detalle_transferencias_fecha_carga" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_transferencias_fecha_carga2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_usr_apro" class="table__row table__row--summary">
                    <td id="detalle_transferencias_usr_aprueba" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_transferencias_usr_aprueba2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_usr_apro2" class="table__row table__row--summary">
                    <td id="detalle_transferencias_fecha_aprueba" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_transferencias_fecha_aprueba2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_usr_lib" class="table__row table__row--summary">
                    <td id="detalle_transferencias_usr_libera" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_transferencias_usr_libera2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_div_usr_lib2" class="table__row table__row--summary">
                    <td id="detalle_transferencias_fecha_libera" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_transferencias_fecha_libera2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div class="notification-modal__buttons">
            <input type="button" id="btn_cerrar_detalleTransferencia" value="Close" class="button button--white">
            <input type="button" id="btn_imprimir_detalleTransferencia" value="Print" class="button button--white">
        </div>
    </div>
</div>


<%--<div id="detalle_transferencia_fc">--%>
<%--    <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen2">--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGCuentaDebito2">From Product</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RAccounts" class="datos_resumen"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="BeneficiaryBankDT">--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_transferencias_TAGBancoBeneficiario2">Beneficiary Bank</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="BeneficiaryBankDT1">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGEmailCodigoBancoBeneficiario">Beneficiary Bank Code</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RBankCode" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="BeneficiaryBankDT2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGEmailNombreBancoBeneficiario">Beneficiary Bank Name</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RNameBank" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_transferencias_TAGNombreBeneficiario2">Beneficiary</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr  id="detalle_RnameDT">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGBeneficiaryName">Beneficiary Name</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_Rname" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr  id="detalle_RAccountNumberDT">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGBeneficiaryAccount">Beneficiary Account</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RAccountNumber" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGEmailBeneficiario">Beneficiary Correo</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RCorreo" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_RTelephoneNumber" >--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGBeneficiaryTelephone">Beneficiary Telephone</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RTelephoneNumber" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_TOB_resumen_intermediary" >--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_transferencias_TAGIntermediaryBank">Intermediary Bank</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_RBankCodeIB" >--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGEmailCodigoBancoIntermediario">Intermediary Bank Code</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RBankCodeIB" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_RNameBankIB" >--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGEmailNombreBancoIntermediario"> Intermediary Bank Name</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RNameBankIB" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_div_TOB_resumen_furtherCredit" >--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_transferencias_TAGParaFuturoCredito2">For Further Credit to</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_RAccountNumberFFC" >--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGFurtherCreditAccount">Further Credit Account</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RAccountNumberFFC" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_RNameFFC">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGFurtherCreditName">Further Credit Name</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RNameFFC" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_transferencias_TAGMontoInstrucciones2">Amount & Instructions</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="montoDetalleOcultar">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGMonto2">Amount</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RAmmountAI" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="unitDetalleOcultar">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGUnits2">Units</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RUnitsAI" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="cancleacionHistoricoOcultar">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGCanelacion2">Cancelation</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RCancelAI" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_RReceiverInformation">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGConcepto">Description</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_RReceiverInformation" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_estatus_TOB">--%>

<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_TAGEstatusTransferencia">Transfer Status</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_status_TOB" class="TOB_borrarData"></span>--%>
<%--            </td>--%>

<%--        </tr>--%>
<%--        <tr id="detalle_div_record_TOB">--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_transferencias_TAGHistorial">Record</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id='detalle_div_record_TOB1'>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_usr_carga">Input User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_transferencias_usr_carga2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id='detalle_div_record_TOB2'>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_fecha_carga">Input Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_transferencias_fecha_carga2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_usr_apro">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_usr_aprueba">Approver User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_transferencias_usr_aprueba2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_usr_apro2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_fecha_aprueba">Approval Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_transferencias_fecha_aprueba2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>


<%--        <tr id="detalle_div_usr_lib">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_usr_libera">Releaser User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_transferencias_usr_libera2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr id="detalle_div_usr_lib2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_transferencias_fecha_libera">Release Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_transferencias_fecha_libera2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>

<%--            <td class="datos_resumen" colspan="2">--%>
<%--                <input type="button" id="btn_cerrar_detalleTransferencia" value="Cerrar" class="boton">--%>
<%--                <input type="button" id="btn_imprimir_detalleTransferencia" value="Imprimir" class="boton">--%>
<%--            </td>--%>

<%--        </tr>--%>

<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>


<%--<div id="detalle_pagoTDC_fc" style="display: none">--%>
<%--    <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen2">--%>
<%--        <tbody>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_TAGCreditCardNumber">Credit Card Number</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_CreditCardNumber" class="datos_resumen"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_pagoTDC_TAGMontoInstrucciones2">Amount & Instructions</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_TAGCuentaDebito">Debit Account</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_DebitAccount" class="datos_resumen"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_TAGTipoPago">Payment Option</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_PaymentOpcion" class="datos_resumen"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_TAGMonto">Amount</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_Amount" class="datos_resumen"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_TAGStatus">Status</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_Status" class="datos_resumen"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_Record">--%>
<%--            <td class="titulo_resumen" colspan="2">--%>
<%--                <b><span id="detalle_pagoTDC_TAGRecord">Record</span></b>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_carga">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_carga">Input User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_carga2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_carga2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_carga">Load Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_carga2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_apro">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_aprueba">Approver User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_aprueba2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_apro2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_aprueba">Approval Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_aprueba2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_lib">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_libera">Releaser User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_libera2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_lib2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_libera">Release Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_libera2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_rechaza">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_rechaza">Rejector User</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_usr_rechaza2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr id="detalle_pagoTDC_div_usr_rechaza2">--%>
<%--            <td class="titulo_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_rechaza">Rejected Date</span>--%>
<%--            </td>--%>
<%--            <td class="datos_resumen">--%>
<%--                <span id="detalle_pagoTDC_fecha_rechaza2" class="TOB_borrarData"></span>--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        <tr>--%>
<%--            <td class="datos_resumen" colspan="2">--%>
<%--                <input type="button" id="btn_cerrar_detallePagoTdc" value="Cerrar" class="boton">--%>
<%--                <input type="button" id="btn_imprimir_detallePagoTdc" value="Imprimir" class="boton">--%>
<%--            </td>--%>
<%--        </tr>--%>

<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>

<div class="notification-modal" id="detalle_pagoTDC_fc">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="assets/img//icons/ic_login_security_tips.png" alt=""/>
    </div>
    <div class="notification-modal__content" id="detalle_pagoTDC_fc_content">
        <div class="table">
            <table class="table__content table__content--summary">
                <tr class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_TAGCreditCardNumber" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_CreditCardNumber" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div class="table">
            <span id="detalle_pagoTDC_TAGMontoInstrucciones2" class="table__title">TITLE</span>
            <table class="table__content table__content--summary">
                <tr class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_TAGCuentaDebito" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_DebitAccount" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_TAGTipoPago" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_PaymentOpcion" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_TAGMonto" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_Amount" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_TAGStatus" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_Status" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div id="detalle_pagoTDC_Record" class="table">
            <span id="detalle_pagoTDC_TAGRecord" class="table__title">TITLE</span>
            <table class="table__content table__content--summary">
                <tr id="detalle_pagoTDC_div_usr_carga" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_usr_carga" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_usr_carga2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_carga2" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_fecha_carga" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_fecha_carga2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_apro" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_usr_aprueba" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_usr_aprueba2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_apro2" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_fecha_aprueba" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_fecha_aprueba2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_lib" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_usr_libera" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_usr_libera2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_lib2" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_fecha_libera" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_fecha_libera2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_rechaza" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_usr_rechaza" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_usr_rechaza2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
                <tr id="detalle_pagoTDC_div_usr_rechaza2" class="table__row table__row--summary">
                    <td id="detalle_pagoTDC_fecha_rechaza" class="table__item table__item--summary"><span>LABEL ITEM</span></td>
                    <td id="detalle_pagoTDC_fecha_rechaza2" class="table__item table__item--last"><span>VALUE</span></td>
                </tr>
            </table>
        </div>
        <div class="notification-modal__buttons">
            <input type="button" id="btn_cerrar_detallePagoTdc" value="Close" class="button button--white">
            <input type="button" id="btn_imprimir_detallePagoTdc" value="Print" class="button button--white">
        </div>
    </div>
</div>



<div id="poppup_ayuda_transferencia" class="oculto">
    <div id="poppup_ayuda_transferencia_cerrar"> Cerrar </div>
    <div id="tabs_popup_ayuda">
        <ul>
            <li><a href="#tabs_popup_ayuda-1">Swif</a></li>
            <li><a href="#tabs_popup_ayuda-2">ABA</a></li>
            <li><a href="#tabs_popup_ayuda-3">IBAN</a></li>
        </ul>
        <div id="tabs_popup_ayuda-1">
            <h3 id="poppup_ayuda_label_swiff_code">Swift Code</h3>
            <p id="poppup_ayuda_label_swiff_code_1">The Swift Code is also known as BIC (Bank Identifier Code) and it allows the identification of a bank to facilitate international money transfers.</p>
            <span id="poppup_ayuda_label_swiff_code_2">Swift code consists of 11 characters:</span><br>
            <ul>
                <li id="poppup_ayuda_label_swiff_code_3">
                    The first four characters (alphabetic) identify the bank.
                </li>
                <li id="poppup_ayuda_label_swiff_code_4">
                    The next two characters (alphabetic) identify the country.
                </li>
                <li id="poppup_ayuda_label_swiff_code_5">
                    The next two characters (alphabetic or numeric) identify the locality.
                </li>
                <li id="poppup_ayuda_label_swiff_code_6">
                    The last three characters (alphabetic or numeric) identify the office of the Bank; you can use 'XXX' to refer to the main office (optional).
                </li>
            </ul>
            <p id="poppup_ayuda_label_swiff_code_7">
                You can use an abbreviated version of eight characters. In this case it is understood that the office is the main office.
            </p>
            <p id="poppup_ayuda_label_swiff_code_8">
                If you do not dispose of the Swift Code, we suggest to get this information from the beneficiary of the transfer or through the following site  swift.com.
            </p>
            <p class="resaltado" id="poppup_ayuda_label_swiff_code_9">
                <span class="negrita">Note:</span> If you only indicate Account Number, the transfer might be subject to an additional cost by the Beneficiary's Bank.
            </p>

        </div>
        <div id="tabs_popup_ayuda-2">
            <h3 id="poppup_ayuda_label_ABA">ABA Number - Routing Transit Number (RTN)</h3>
            <p id="poppup_ayuda_label_ABA_1">The ABA Number is a 9-digit code which identifies a Bank and is used only for domestic transactions in the United States of America. </p>

            <p class="resaltado" id="poppup_ayuda_label_ABA_2"><span class="negrita">Note:</span> If you only indicate Account Number, the transfer might be subject to an additional cost by the Beneficiary's Bank.</p>
        </div>
        <div id="tabs_popup_ayuda-3">
            <h3 id="poppup_ayuda_label_IBAN">IBAN - International Bank Account Number</h3>
            <p id="poppup_ayuda_label_IBAN1">The IBAN is the account number of a client in an international context.</p>
            <span id="poppup_ayuda_label_IBAN2">The IBAN consists of a maximum of 34 characters, and its size is fixed for each country. The structure of the IBAN is unique and is defined as follows:</span><br>
            <ul>
                <li id="poppup_ayuda_label_IBAN3">
                    The first two characters are alphabetic and correspond to the country code where the account is held;
                </li>
                <li id="poppup_ayuda_label_IBAN4">
                    The following two characters are numeric digits and are used as control to validate the complete IBAN;
                </li>
                <li id="poppup_ayuda_label_IBAN5">
                    The remaining characters are the BIN (Bank Identification Number), which identifies and validates the country, the bank and the beneficiary account. The IBAN improves the effectiveness of service provided by banks for international transfers.
                </li>
            </ul>
            <p id="poppup_ayuda_label_IBAN6">
                You can find the IBAN on the menu option called "WIRE TRANSFER INSTRUCTIONS".
            </p>
            <p id="poppup_ayuda_label_IBAN7">
                If you do not dispose of the Swift Code, we suggest to get this information from the beneficiary of the transfer or through the following site  swift.com.
            </p>
            <p class="resaltado" id="poppup_ayuda_label_IBAN8">
                <span class="negrita">Note:</span> If you only indicate Account Number, the transfer might be subject to an additional cost by the Beneficiary's Bank.
            </p>

        </div>
    </div>


</div>
<div id="poppup_parametrosPersonalesTOB" class="oculto parametros">
    <div class="parametros__titulos">
        <span id="popupTagHaciaOtrosBancos"></span>
    </div>
    <div class="parametros__grid">
        <div class="parametros__item">
            <span class="parametros__bold" id="popupTag_PP_numeroMaxTransDiarias_OB">Number of Transfers Maximum by Day Name</span>
            <span id="popupTag_PP_numeroMaxTransDiarias_OBValue" class=""></span>
        </div>
        <div class="parametros__item">
            <span class="parametros__bold" id="popupTag_PP_montoMaxTransDiarias_OB">Maximum Amount of Daily Transfers</span>
            <span id="popupTag_PP_montoMaxTransDiarias_OBValue" class=""></span>
        </div>
        <div class="parametros__item">
            <span class="parametros__bold" id="popupTag_PP_montoMinTransOpe_OB">Minimum Amount of Transfer by Operation</span>
            <span id="popupTag_PP_montoMinTransOpe_OBValue" class=""></span>
        </div>
        <div class="parametros__item">
            <span class="parametros__bold" id="popupTag_PP_montoMaxTransOpe_OB">Maximum Amount of Transfer by Operation</span>
            <span id="popupTag_PP_montoMaxTransOpe_OBValue" class=""></span>
        </div>
    </div>
</div>
<div id="poppup_parametrosPersonalesCID" class="oculto parametros">
<%--    <div>
        <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen3">
            <tbody>
            <tr>
                <td class="titulo_resumen" colspan="2">
                    <b><span id="tagDentroVBT">Transfer Parameters In VBT</span></b>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                    <span id="pupupTag_PP_numeroMaxTransDiarias">Number of Transfers Maximum by Day</span>
                </td>
                <td class="datos_resumen">
                    <span id="pupupTag_PP_numeroMaxTransDiariasValue" class="TOB_borrarData"></span>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                    <span id="pupupTag_PP_montoMaxTransDiarias">Maximum Amount of Daily Transfers</span>
                </td>
                <td class="datos_resumen">
                    <span id="pupupTag_PP_montoMaxTransDiariasValue" class="TOB_borrarData"></span>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                    <span id="pupupTag_PP_montoMinTransOpe">Minimum Amount of Transfer by Operation</span>
                </td>
                <td class="datos_resumen">
                    <span id="pupupTag_PP_montoMinTransOpeValue" class="TOB_borrarData"></span>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                    <span id="pupupTag_PP_montoMaxTransOpe">Maximum Amount of Transfer by Operation</span>
                </td>
                <td class="datos_resumen">
                    <span id="pupupTag_PP_montoMaxTransOpeValue" class="TOB_borrarData"></span>
                </td>
            </tr>
            </tbody>
        </table>
    </div>--%>
    <div class="parametros__titulos">
        <span id="tagDentroVBT">Transfer Parameters In VBT</span>
    </div>
    <div class="parametros__grid">
        <div class="parametros__item">
            <span class="parametros__bold" id="pupupTag_PP_numeroMaxTransDiarias">Number of Transfers Maximum by Day Name</span>
            <span id="pupupTag_PP_numeroMaxTransDiariasValue" class=""></span>
        </div>
        <div class="parametros__item">
            <span class="parametros__bold" id="pupupTag_PP_montoMaxTransDiarias">Maximum Amount of Daily Transfers</span>
            <span id="pupupTag_PP_montoMaxTransDiariasValue" class=""></span>
        </div>
        <div class="parametros__item">
            <span class="parametros__bold" id="pupupTag_PP_montoMinTransOpe">Minimum Amount of Transfer by Operation</span>
            <span id="pupupTag_PP_montoMinTransOpeValue" class=""></span>
        </div>
        <div class="parametros__item">
            <span class="parametros__bold" id="pupupTag_PP_montoMaxTransOpe">Maximum Amount of Transfer by Operation</span>
            <span id="pupupTag_PP_montoMaxTransOpeValue" class=""></span>
        </div>
    </div>

</div>
<div id="poppup_saveTemplate" class="oculto poppup">
<%--    <div>
        <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen3">
            <tbody>
            <tr>
                <td colspan="2" style="text-align:center;">
                    <b><span class="datos negrita2" id="tagCancelTransfersTOB">Usted ha cancelado la carga de la transferencia,<br> ¿desea guardar en el directorio los datos registrados para utilizarlos posteriormente?.</span></b>
                </td>
            </tr>
            <tr>
                <td   width="50%" align="center">
                    <input  type="button" id="btn_TemplateGuardar_TOB_inicio" value="Guardar Plantilla" class="botonGrande">
                </td>
                <td   width="50%" align="center">
                    <input  type="button" id="btn_TemplateGuardar_TOB_close" value="Cerrar" class="boton">
                </td>
            </tr>
            </tbody>
        </table>
    </div>--%>
    <p class="poppup_saveTemplate__text" id="tagCancelTransfersTOB"></p>
    <div class="poppup_saveTemplate__buttons">
        <input  type="button" id="btn_TemplateGuardar_TOB_close" value="Cerrar" class="button button--white">
        <input  type="button" id="btn_TemplateGuardar_TOB_inicio" value="Guardar Plantilla" class="button">
    </div>
</div>
<div id="dialog-confirm"  style="display:none">
    <p></p>
</div>
<div id="poppup_message" class="oculto poppup">
<%--
    <div>
        <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen3">
            <tbody>
            <tr>
                <td colspan="2" style="text-align:center;">
                    <br /><br />
                </td>
            </tr>
            <tr id="confirmButtons" class="oculto">
                <td   width="50%" align="center">
                    <input  type="button"  class="dinamicButton">
                </td>
                <td   width="50%" align="center">
                    <input  type="button"  class="dinamicButton">
                </td>
            </tr>
            <tr id="alertButton" class="oculto">
                <td colspan="2" width="50%" align="center">
                    <input type="button" id="btn_alert_ok" class="dinamicButton">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
--%>
    <p class="poppup_saveTemplate__text" id="msg_texto"></p>
    <div id="confirmButtons" class="poppup_saveTemplate__buttons oculto">
        <input  type="button" id="btn_confirm_no" value="Cerrar" class="button button--white">
        <input  type="button" id="btn_confirm_si" value="Guardar Plantilla" class="button">
    </div>
    <div id="alertButton" class="poppup_saveTemplate__buttons oculto">
        <input type="button" id="btn_alert_ok"  value="Guardar Plantilla" class="button">
    </div>
</div>
</div>
<div class="notification-modal" id="logout-modal">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png" alt=""/>
        <span id="tagSalir" class="notification-modal__title">Exite</span></div>
    <div class="notification-modal__content">
        <div class="notification-modal__option">
            <input type="button" id="btn__sir_cancelar" value="Cancel" class="button">
            <input type="button" id="btn__sir_aceptar" value="Accept" class="button button--white">
        </div>
<%--        <div class="notification-modal__options">
            <div class="notification-modal__option">
                <span id="span_aceptar">If you want to Log Out press Accept</span>
                <input type="button" id="btn__sir_aceptar" value="Accept" class="button button--white">
            </div>
            <div class="notification-modal__option">
                <span id="span_cancelar">If you you want to back press Cancel</span>
                <input type="button" id="btn__sir_cancelar" value="Cancel" class="button">
            </div>
        </div>--%>
    </div>
</div>

</body>
<HEAD>
    <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    <META HTTP-EQUIV="Expires" CONTENT="-1">

    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarUsuarios.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarContratos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/grupos/consultarGrupos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarUsuariosContratos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarUsuariosContratosFC.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/transfers.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/transfersJoinsSignatures.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/transfersToOtherClients.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/approveTransferManager.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/releaseTransferManager.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/transfersBetweenMyLinkedAccounts.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/allMyPortafolio/allMyPortafolio.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/accounts/accountsStatements/accountsStatements.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/accounts/balancesAndTransactions/balancesAndTransactions.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/accounts/blockedAmounts/blockedAmounts.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/timeDeposits/certificates/certificates.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/timeDeposits/maturities/maturities.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/timeDeposits/BalancesAndTransactions/balancesAndTransactions.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/timeDeposits/blockedAmounts/blockedAmounts.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/mutualFunds/balancesAndTransactions/balancesAndTransactions.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/mutualFunds/blockedAmounts/blockedAmounts.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/mutualFunds/accountStatements/accountStatement.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/othersInvestments/balancesAndTransactions/balancesAndTransactions.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/othersInvestments/blockedAmounts/blockedAmounts.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/othersInvestments/accountStatements/accountStatement.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/accountStatement/accountStatement.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/inTransitTransactions/inTransitTransactions.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/cardLock/cardLock.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/cardLock/cardLockEmergency.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/transferHistory.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/TemplatesTransfers.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/myInformation/myInformation.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/inicio/formatos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/creditCards.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/desingBank/parametrosPersonales.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/firmasConjuntas/parametrosPersonalesFC.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/desingBank/cambiarClave.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/transfers/buscarBanco.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/bitacora.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/visorDeSucesos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/OpcionesSistema.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/parametrosPersonalesBO.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/firmasConjuntas/consultarUsuariosFC.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.lightbox_me.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.paginate.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/inicio/clientInstruction/clientInstruction.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/securityCard/securityCard.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/mask_date.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/validationMethods/validationMethods.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/dataTables.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/jquery.maskedinput.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/intlTelInput.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarLogSms.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/PaisesNoPermitidos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/inicio/clientInstruction/clientinstructionvoid.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/grupos/ConsultarRolesFC.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/base/chosen.jquery.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/InactivarBancos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/avisos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/tinymce/tinymce.min.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/tinymce/jquery.tinymce.min.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/BloquearTarjetas.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/base64.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/paymentCard/paymentCard.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/paymentCard/approvePaymentsTdcManager.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/paymentCard/releasePaymentsTdcManager.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/CuentasNoPermitidas.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/transaccionesEspeciales.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/PagosTarjetas.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/paymentCard/paymentCardHistory.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/timeDeposits/td/TD.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/tarjetas/bloqueoEmergenciaBO.js?_expirecache_=1"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/printThis-master/printThis.js"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/custom-scripts.js"></script>
</HEAD>
</html>

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
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/home/home.js?_expirecache_=1"></script>
    <%--<script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/loginHome.js?_expirecache_=1"></script>--%>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/login/login.js?_expirecache_=1"></script>
    <script type="text/javascript" src="../vbtonline/resources/js/base/jquery.crypt.js?Math.random()"></script>
</head>
<body onload="CargarHomeJSONData();">
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
            <div id="div_carga_espera" class="imagen_carga oculto">
               <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" />
            </div>
            <div id="div_carga_espera2" class="imagen_carga oculto">
                <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" />
            </div>
            <div id="div_mensajes_error" class="alerta oculto" >
                <div id="mensaje_error">
                    error
                </div>
                <div id="cerrar_div_mensaje_error">[X]</div>
            </div>
            <div id="div_login" class="oculto div_menu2 ">
                <jsp:include page="../../login/Login.jsp" />
            </div>
            <div id="div_home" class="opcion_seleccionada div_menu 0000000061_0 div_opcion_menu">
                <h1 id="tituloHome">HOME</h1>
                <jsp:include page="contenido_home.jsp" />
            </div>
            <div id="div_CALENDARIO_BANCARIO" class="oculto div_menu 0000000062_0 div_opcion_menu">
                <jsp:include page="../calendario_bancario/calendario_bancario.jsp" />
            </div>

            <div id="div_FORMATOS" class="oculto div_menu 0000000007_0 div_opcion_menu">
                <jsp:include page="../../inicio/formatos/formatos.jsp" />
            </div>
            <div id="div_INSTRUCCIONES" class="oculto div_menu 0000000063_0 div_opcion_menu">
                <jsp:include page="../../inicio/instrucciones_transferencias/instrucciones_transferencia.jsp" />
                    <%--<jsp:include page="../../backOffice/ConsultarContratos.jsp" />--%>
            </div>
            <!-- Cuentas -->
            <div id="div_SALDOS_CUENTAS" class="oculto div_menu 0000000009_0 div_opcion_menu">
                <jsp:include page="../../accounts/balancesAndTransactions/BalancesAndTransactions.jsp" />
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

            <!-- Fondos Mutuales -->
            <div id="div_SALDOS_FONDOS" class="oculto div_menu 0000000018_0 div_opcion_menu">
                <jsp:include page="../../mutualFunds/balancesAndTransactions/BalancesAndTransactions.jsp" />
            </div>
            <div id="div_BLOQUEOS_FONDOS" class="oculto div_menu 0000000019_0 div_opcion_menu">
                <jsp:include page="../../mutualFunds/blockedAmounts/BlockedAmounts.jsp" />
            </div>

            <!-- Otras Inversiones -->
            <div id="div_SALDOS_FONDOS_OI" class="oculto div_menu 0000000048_0 div_opcion_menu">
                <jsp:include page="../../OthersInvestments/balancesAndTransactions/BalancesAndTransactions.jsp" />
            </div>
            <div id="div_BLOQUEOS_FONDOS_OI" class="oculto div_menu 0000000049_0 div_opcion_menu">
                <jsp:include page="../../OthersInvestments/blockedAmounts/BlockedAmounts.jsp" />
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

<div id="detalle_transferencia_fc">
    <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen2">
        <tbody>
        <tr>
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGCuentaDebito2">From Account</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RAccounts" class="datos_resumen"></span>
            </td>
        </tr>
        <tr id="BeneficiaryBankDT">
            <td class="titulo_resumen" colspan="2">
                <b><span id="detalle_transferencias_TAGBancoBeneficiario2">Beneficiary Bank</span></b>
            </td>
        </tr>

        <tr id="BeneficiaryBankDT1">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGEmailCodigoBancoBeneficiario">Beneficiary Bank Code</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RBankCode" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="BeneficiaryBankDT2">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGEmailNombreBancoBeneficiario">Beneficiary Bank Name</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RNameBank" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr>
            <td class="titulo_resumen" colspan="2">
                <b><span id="detalle_transferencias_TAGNombreBeneficiario2">Beneficiary</span></b>
            </td>
        </tr>
        <tr  id="detalle_RnameDT">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGBeneficiaryName">Beneficiary Name</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_Rname" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr  id="detalle_RAccountNumberDT">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGBeneficiaryAccount">Beneficiary Account</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RAccountNumber" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr>
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGEmailBeneficiario">Beneficiary Correo</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RCorreo" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_RTelephoneNumber" >
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGBeneficiaryTelephone">Beneficiary Telephone</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RTelephoneNumber" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_TOB_resumen_intermediary" >
            <td class="titulo_resumen" colspan="2">
                <b><span id="detalle_transferencias_TAGIntermediaryBank">Intermediary Bank</span></b>
            </td>
        </tr>
        <tr id="detalle_div_RBankCodeIB" >
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGEmailCodigoBancoIntermediario">Intermediary Bank Code</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RBankCodeIB" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_RNameBankIB" >
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGEmailNombreBancoIntermediario"> Intermediary Bank Name</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RNameBankIB" class="TOB_borrarData"></span>
            </td>
        </tr>

        <tr id="detalle_div_TOB_resumen_furtherCredit" >
            <td class="titulo_resumen" colspan="2">
                <b><span id="detalle_transferencias_TAGParaFuturoCredito2">For Further Credit to</span></b>
            </td>
        </tr>
        <tr id="detalle_div_RAccountNumberFFC" >
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGFurtherCreditAccount">Further Credit Account</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RAccountNumberFFC" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_RNameFFC">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGFurtherCreditName">Further Credit Name</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RNameFFC" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr>
            <td class="titulo_resumen" colspan="2">
                <b><span id="detalle_transferencias_TAGMontoInstrucciones2">Amount & Instructions</span></b>
            </td>
        </tr>
        <tr>
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGMonto2">Amount</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RAmmountAI" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_RReceiverInformation">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGConcepto">Description</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_RReceiverInformation" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_estatus_TOB">

            <td class="titulo_resumen">
                <span id="detalle_transferencias_TAGEstatusTransferencia">Transfer Status</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_status_TOB" class="TOB_borrarData"></span>
            </td>

        </tr>
        <tr>
            <td class="titulo_resumen" colspan="2">
                <b><span id="detalle_transferencias_TAGHistorial">Record</span></b>
            </td>
        </tr>

        <tr>
            <td class="titulo_resumen">
                <span id="detalle_transferencias_usr_carga">Input User</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_transferencias_usr_carga2" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr>
            <td class="titulo_resumen">
                <span id="detalle_transferencias_fecha_carga">Input Date</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_transferencias_fecha_carga2" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_usr_apro">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_usr_aprueba">Approver User</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_transferencias_usr_aprueba2" class="TOB_borrarData"></span>
            </td>
        </tr>
        <tr id="detalle_div_usr_apro2">
            <td class="titulo_resumen">
                <span id="detalle_transferencias_fecha_aprueba">Approval Date</span>
            </td>
            <td class="datos_resumen">
                <span id="detalle_transferencias_fecha_aprueba2" class="TOB_borrarData"></span>
            </td>
        </tr>

        <tr>

            <td class="datos_resumen" colspan="2">
                <input type="button" id="btn_cerrar_detalleTransferencia" value="Cerrar" class="boton">
                <input type="button" id="btn_imprimir_detalleTransferencia" value="Imprimir" class="boton">
            </td>

        </tr>

        </tbody>
    </table>
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
</body>
<HEAD>
    <META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
    <META HTTP-EQUIV="Expires" CONTENT="-1">

    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarUsuarios.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarContratos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/grupos/consultarGrupos.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/consultarUsuariosContratos.js?_expirecache_=1"></script>
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
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/othersInvestments/balancesAndTransactions/balancesAndTransactions.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/othersInvestments/blockedAmounts/blockedAmounts.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/accountStatement/accountStatement.js?_expirecache_=1"></script>
    <script type="text/javascript" language="javascript" src="../vbtonline/resources/js/creditCards/inTransitTransactions/inTransitTransactions.js?_expirecache_=1"></script>
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

</HEAD>
</html>

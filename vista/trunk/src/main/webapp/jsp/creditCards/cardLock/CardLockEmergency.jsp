<%--
  Created by IntelliJ IDEA.
  User: Rafael Nuñez
  Date: 19/07/21
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="TDC_titulo_bloquear_emergencia">Tarjetas de Cr&eacute;dito / Bloqueo de Tarjeta</h1>
</div>

<div id="div_creditCard_CLE_alertaSeguridad" >

    <div id="alertaSeguridadTDC">
        <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
            <tr height="10%">
                <td align="center">
                    <span id="comun_TAGSubTituloAlertaTDCV_6">AVISOS DE SEGURIDAD PARA EVITAR FRAUDES.<P>POR FAVOR LEA CON DETENIMIENTO LA SIGUIENTE INFORMACI&Oacute;N</P></span>
                </td>
            </tr>
            <tr>
                <td>
                    <TABLE border="0" width="85%" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
                                    <tr><td  height="16" align="center" colspan="2"></td></tr>
                                    <tr>
                                        <td  align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV1_5">Firme su tarjeta en tinta indeleble tan pronto la reciba y destruya las tarjetas viejas cuando caduquen.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV2_5">Nunca permita que otras personas utilicen su tarjeta. Es suya y solo suya.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV3_5">Al realizar una compra, aseg&uacute;rese que el vendedor procese la transacci&oacute;n en su presencia.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV4_5">Revise su tarjeta cuando le es devuelta por el cajero para asegurar que es la suya y que no ha sido adulterada en ning&uacute;n modo.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV5_5">Siempre conserve sus recibos para reconciliarlos con su estado de cuenta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV6_5">Notifique al emisor de la tarjeta de cualquier cambio de direcci&oacute;n, para evitar que nuevas tarjetas y/o estados de cuenta sean enviados a la vieja direcci&oacute;n.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV7_5">En caso de robo o p&eacute;rdida de la tarjeta, notif&iacute;quelo al emisor de inmediato. Siempre mantenga el n&uacute;mero de tel&eacute;fono del emisor (Bank & Trust, Ltd.) a la mano.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV8_5">No entregue ninguna informaci&oacute;n de la tarjeta a personas que lo soliciten por tel&eacute;fono y/o correo y/o correo electr&oacute;nico.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV9_5">De hacer compras en l&iacute;nea, aseg&uacute;rese de realizar su compra a trav&eacute;s de un sitio web Secure Server Transaction (mejor aun si ha sido certificado por VISA).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV10_5">No firme vouchers de pago en blanco, haga una l&iacute;nea sobre el monto total y destruya el carb&oacute;n y recibos anulados.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV11_5">Evite los sitios web de apuestas, casinos y desconocidos.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV12_5">Si recibe confirmaciones de compra desconocidas v&iacute;a SMS (tel&eacute;fono celular) y/o correo electr&oacute;acute;nicos del Banco, por favor cont&aacute;ute;ctenos de inmediato (Atenci&oacute;n al Cliente, Ejecutivo de Ventas).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV13_5">Est&eacute; atento a cualquier correo electr&oacute;nico que solicite informaci&oacute;n personal &minus;tales como claves, PINs, o su n&uacute;mero de identificaci&oacute;n- o lo dirija a sitios web que soliciten dicha informaci&oacute;n. Estos mensajes pueden tratarse de un enga&ntilde;o, falsificando una direcci&oacute;n de correo electr&oacute;nico para aparentar ser otra direcci&oacute;n y negocio leg&iacute;timos. Recuerde nunca enviar su informaci&oacute;n personal v&iacute;a correo electr&oacute;nico. Si recibe un correo electr&oacute;nico enga&ntilde;oso, por favor informe a VISA acerca de los detalles de este correo electr&oacute;nico ilegal.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV14_5">Visa no lo contactar&aacute; o enviar&aacute; correos electr&oacute;nicos solicitando la informaci&oacute;n personal de su cuenta. Los centros de llamadas de VISA no inician llamadas de tele-mercadeo. Los clientes no deben responder a correos electr&oacute;nicos ni llamadas telef&oacute;nicas solicitando informaci&oacute;n personal de su tarjeta y se les sugiere reportar inmediatamente tal situaci&oacute;n a las autoridades locales as&iacute; como a la instituci&oacute;n financiera emisora de la tarjeta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV15_5">El tarjetahabiente ser&aacute; responsable ante El Banco por no reportar consumos en la tarjeta no autorizados. Tan pronto como el tarjetahabiente notifique debidamente al Banco, la responsabilidad de los cargos subsiguientes no autorizados y avances de efectivo podr&aacute; cesar.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV16_5">El tarjetahabiente es el &uacute;nico responsable por el correcto uso de la tarjeta. El tarjetahabiente asumir&aacute; las responsabilidades por todos los cargos y comisiones derivados del uso correcto de la tarjeta. Cualquier uso incorrecto de la tarjeta ser&aacute; de la sola responsabilidad del tarjetahabiente.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV17_5">El uso de su tarjeta por terceras personas no autorizadas (e.g. debido a p&eacute;rdida, robo, falsificaci&oacute;n o uso de la data de la tarjeta mientras el tarjetahabiente utiliza la tarjeta en l&iacute;nea o alg&uacute;n medio digital) es el mayor riesgo que el tarjetahabiente enfrenta al utilizar la tarjeta. La apropiaci&oacute;n de la tarjeta por terceras personas  facilitando el uso incorrecto de la tarjeta podr&aacute; causar da&ntilde;o a una o mas partes participantes del sistema de tarjetas de cr&eacute;dito (e.g. usuarios, establecimientos participantes y el emisor). El reparo de dicho da&ntilde;o surgido del incumplimiento de su obligaci&oacute;n/responsabilidad como tarjetahabiente de prevenir dichos da&ntilde;os puede resultar en compromisos monetarios de usted como tarjetahabiente.</span>
                                        </td>
                                    </tr>
                                    <tr><td class="V8NNegroGris" height="16" align="center" colspan="2"></td></tr>
                                </TABLE>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
            <tr height="30">
                <td align="center" class="A7NVerde">
                    <input type="button" class="botonGrande" id="comun_TAGTitleContinuarTDC4" value="Click para Continuar">
                </td>
            </tr>
        </TABLE>
        <input type="hidden" id="pantalla" value="">
    </div>
</div>


<div id="div_creditCard_CLE" style="display: none">
    <fieldset class="invisible_print div_info" style="width:99%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left" style="text-align:justify;">
                    <span id="TAG_EMERGENCY_CREDIT_CARDS" class="datosInfo">Esta opción le permite bloquear la tarjeta en caso de emergencia.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td class="datos" width="15%"><span class="label_numero_cuenta_TDC_CLE" id="TAGNumeroTarjeta_Pago3">N&uacute;mero de Tarjeta</span><span>:</span></td>
                <td class="datos" width="15%">
                    <select  id="numero_cuenta_TDC_CLE" title="Numero de Tarjeta" style="width:320px;margin-right:4px" class="chosen-select requerido_TDC_CLE invisible_print" onchange="cargarEstatusCLE(this.value)">
                    </select>
                    <span id="numero_cuenta_TDC_CLE_select" class="visible_print"></span>
                    <span id="tag_cliente_bloqueo_cle" style="padding-left: 14px;"></span>
                </td>
                <td class="datos" align="left">
                    <div id="div_datos_TDC_CLE">
                        <table width="100%">
                            <td width="70%">
                                <div class="oculto" id="motivosCLE">
                                    <span class="label_motivosBloqueo" id="TAGMotivosBloqueo">Motivos Bloqueo</span><span>:</span>
                                    <select  id="motivosBloqueoCLE" title="Motivos Bloqueo" class="chosen-select requerido_TDC_CLE invisible_print" style="width:200px;margin-right:4px" >
                                    </select>
                                </div>
                            </td>
                            <td width="30%" align="right">
                                <div class="oculto" style="" id="btnBloqueo">
                                    <input type="button" id="crear_bloqueo" value="Bloqueo" class="botonGrandeDerecha">
                                </div>
                            </td>
                        </table>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</div>
<div id="div_anularBloqueo" class="oculto">
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td class="datos" width="70%">
                    <span class="label_titular" id="noInfo_TDC5_1"> La tarjeta fue bloqueada por: .</span>
                    <span id="noInfo_TDC5_motivo">  </span>
                    <span class="label_titular oculto" id="noInfo_TDC5_2"> En caso que haya sido recuperada y quiera solicitar su desbloqueo, presione el siguiente botón, y  espere ser contactado por su su Asesor/Ejecutivo. </span>
                </td>
                <td class="datos" width="30%">
                    <div class="oculto" id="btnActivacion" >
                        <input type="button" id="solicita_activar" value="Solicitar Activación" class="botonGrandeDerecha">
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
</div>

<div id="div_noRespuesta_datos_tdc" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_TDC4"> Su solicitud se encuentra en proceso </span>
    </fieldset>
</div>

<div id="div_noInfo_CLE_creditCard" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_TDC3">No posee tarjetas de cr&eacute;dito que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img
            class="banner__img banner__img--modifier"
            src="../vbtonline/resources/img/bg_portfolio.png"
            alt="Banner Home"
    />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TDC_titulo_bloquear_emergencia" class="banner__title banner__title--modifier">
                Credit Cards / Preventive Block - Unblock
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome36" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="PREVENTIVE_BLOCK_UNBLOCK_CREDIT_CARDS">CREDIT CARDS</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="PREVENTIVE_BLOCK_UNBLOCK">PREVENTIVE BLOCK - UNBLOCK</li>
            </ul>
            <p id="TAG_EMERGENCY_CREDIT_CARDS_3" class="banner__description banner__description--modifier">
                Security advises to avoid frauds. Please read carefully the
                following information.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TDC_titulo_bloquear_emergencia" class="banner__title banner__title--modifier">
                Credit Cards / Preventive Block - Unblock
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome16" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagTransferApro">CREDIT CARDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagTransferAproSub">PREVENTIVE BLOCK - UNBLOCK</li>
            </ul>
            <p id="TAG_EMERGENCY_CREDIT_CARDS_3" class="banner__description banner__description--modifier">
                Security advises to avoid frauds. Please read carefully the
                following information.
            </p>
            <div id="ver_mas_generate_security" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<div id="dialog-description-show-more"  style="display:none">
    <p></p>
</div>
<main class="main main--modifier">
    <div id="div_creditCard_CLE_alertaSeguridad" class="section">
        <div id="alertaSeguridadTDC" class="section__container container">
            <div class="section__top">
                <img
                        class="section__icon"
                        src="../vbtonline/resources/img/icons/ic_login_security_tips.png"
                        alt=""
                />
                <div class="section__header">
                    <span class="section__title">Tips</span>
                </div>
            </div>
            <div class="section__content">
                <ul class="section__list">
                    <li id="comun_TAGAlertaTDCV1_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV2_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV3_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV4_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV5_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV6_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV7_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV8_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV9_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV10_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV11_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV12_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV13_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV14_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV15_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV16_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV17_5">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                </ul>
                <input type="hidden" id="pantalla" value="div_creditCard_CLE">
                <div class="table__spacebetween table__spacebetween--margin">
                    <span class="table__mandaroty"></span>
                    <div class="table__buttons">
                        <button id="comun_TAGTitleContinuarTDC4" class="table__button button" >
                            Click to Continue
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_creditCard_CLE" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="">
                <div class="section__header">
                    <div class="section__field">
                        <label id="TAGNumeroTarjeta_Pago3" for="numero_cuenta_TDC_CLE">LABEL</label>
                        <div class="select-section select-section--form">
                            <select id="numero_cuenta_TDC_CLE"
                                    class="select-section__select select-section__select--form chosen-select requerido_TDC_CLE invisible_print"
                                    onchange="cargarEstatusCLE(this.value)">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                        </div>
                    </div>
                </div>
                <div id="motivosCLE" class="section__row section__row--spacebetween oculto">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="TAGMotivosBloqueo" for="motivosBloqueoCLE">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="motivosBloqueoCLE"
                                        class="select-section__select select-section__select--form chosen-select requerido_TDC_CLE invisible_print">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="section__buttons">
                        <button class="section__button button" id="crear_bloqueo">Block</button>
                    </div>
                </div>
           <!--      <div class="section__row section__row--spacebetween">
                    <span id="TAG_EMERGENCY_CREDIT_CARDS"></span>
                </div> -->
            </div>
            <div class="section__content">
                <div id="div_anularBloqueo" class="section__message oculto">
                    <div class="section__texts">
                        <span id="noInfo_TDC5_1"> La tarjeta fue bloqueada por: .</span>
                        <span id="noInfo_TDC5_motivo"> </span>
                        <span class="oculto" id="noInfo_TDC5_2"> En caso que haya sido recuperada y quiera solicitar
                            su desbloqueo, presione el siguiente botón, y espere ser contactado por su su Asesor/Ejecutivo. </span>
                    </div>
                    <input type="button" id="solicita_activar" value="Solicitar Activación" class="button">
                </div>
                <div id="div_noRespuesta_datos_tdc" class="section__message" style="display: none">
                    <div class="section__texts">
                        <span id="noInfo_TDC4"> Su solicitud se encuentra en proceso </span>
                    </div>
                </div>
                <div id="div_noInfo_CLE_creditCard" class="section__message" style="display: none">
                    <div class="section__texts">
                        <span id="noInfo_TDC3"> Su solicitud se encuentra en proceso </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>
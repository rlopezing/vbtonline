<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="TDC_titulo_BT">Tarjetas de Cr&eacute;dito / Estado de Cuenta</h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info oculto" id="TAG_INFO_ESTADOS_TARJETAS">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_ESTADOS">This option allows you to see and print your credit card statement.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_creditCard_AE_alertaSeguridad" >


    <div id="alertaSeguridadTDC">
        <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
            <tr height="10%">
                <td align="center">
                    <span id="comun_TAGSubTituloAlertaTDCV_1">AVISOS DE SEGURIDAD PARA EVITAR FRAUDES.<P>POR FAVOR LEA CON DETENIMIENTO LA SIGUIENTE INFORMACI&acute;N</P></span>
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
                                            <span id="comun_TAGAlertaTDCV1_1">Firme su tarjeta en tinta indeleble tan pronto la reciba y destruya las tarjetas viejas cuando caduquen.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV2_1">Nunca permita que otras personas utilicen su tarjeta. Es suya y solo suya.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV3_1">Al realizar una compra, aseg&uacute;rese que el vendedor procese la transacci&acute;n en su presencia.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV4_1">Revise su tarjeta cuando le es devuelta por el cajero para asegurar que es la suya y que no ha sido adulterada en ning&uacute;n modo.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV5_1">Siempre conserve sus recibos para reconciliarlos con su estado de cuenta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV6_1">Notifique al emisor de la tarjeta de cualquier cambio de direcci&oacute;n, para evitar que nuevas tarjetas y/o estados de cuenta sean enviados a la vieja direcci&oacute;n.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV7_1">En caso de robo o p&eacute;rdida de la tarjeta, notif&iacute;quelo al emisor de inmediato. Siempre mantenga el n&uacute;mero de tel&eacute;fono del emisor (Bank & Trust, Ltd.) a la mano.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV8_1">No entregue ninguna informaci&oacute;n de la tarjeta a personas que lo soliciten por tel&eacute;fono y/o correo y/o correo electr&oacute;nico.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV9_1">De hacer compras en l&iacute;nea, aseg&uacute;rese de realizar su compra a trav&eacute;s de un sitio web Secure Server Transaction (mejor aun si ha sido certificado por VISA).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV10_1">No firme vouchers de pago en blanco, haga una l&iacute;nea sobre el monto total y destruya el carb&oacute;n y recibos anulados.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV11_1">Evite los sitios web de apuestas, casinos y desconocidos.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV12_1">Si recibe confirmaciones de compra desconocidas v&iacute;a SMS (tel&eacute;fono celular) y/o correo electr&oacute;acute;nicos del Banco, por favor cont&aacute;ute;ctenos de inmediato (Atenci&oacute;n al Cliente, Ejecutivo de Ventas).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV13_1">Est&eacute; atento a cualquier correo electr&oacute;nico que solicite informaci&oacute;n personal &minus;tales como claves, PINs, o su n&uacute;mero de identificaci&oacute;n- o lo dirija a sitios web que soliciten dicha informaci&oacute;n. Estos mensajes pueden tratarse de un enga&ntilde;o, falsificando una direcci&oacute;n de correo electr&oacute;nico para aparentar ser otra direcci&oacute;n y negocio leg&iacute;timos. Recuerde nunca enviar su informaci&oacute;n personal v&iacute;a correo electr&oacute;nico. Si recibe un correo electr&oacute;nico enga&ntilde;oso, por favor informe a VISA acerca de los detalles de este correo electr&oacute;nico ilegal.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV14_1">Visa no lo contactar&aacute; o enviar&aacute; correos electr&oacute;nicos solicitando la informaci&oacute;n personal de su cuenta. Los centros de llamadas de VISA no inician llamadas de tele-mercadeo. Los clientes no deben responder a correos electr&oacute;nicos ni llamadas telef&oacute;nicas solicitando informaci&oacute;n personal de su tarjeta y se les sugiere reportar inmediatamente tal situaci&oacute;n a las autoridades locales as&iacute; como a la instituci&oacute;n financiera emisora de la tarjeta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV15_1">El tarjetahabiente ser&aacute; responsable ante El Banco por no reportar consumos en la tarjeta no autorizados. Tan pronto como el tarjetahabiente notifique debidamente al Banco, la responsabilidad de los cargos subsiguientes no autorizados y avances de efectivo podr&aacute; cesar.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV16_1">El tarjetahabiente es el &uacute;nico responsable por el correcto uso de la tarjeta. El tarjetahabiente asumir&aacute; las responsabilidades por todos los cargos y comisiones derivados del uso correcto de la tarjeta. Cualquier uso incorrecto de la tarjeta ser&aacute; de la sola responsabilidad del tarjetahabiente.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV17_1">El uso de su tarjeta por terceras personas no autorizadas (e.g. debido a p&eacute;rdida, robo, falsificaci&oacute;n o uso de la data de la tarjeta mientras el tarjetahabiente utiliza la tarjeta en l&iacute;nea o alg&uacute;n medio digital) es el mayor riesgo que el tarjetahabiente enfrenta al utilizar la tarjeta. La apropiaci&oacute;n de la tarjeta por terceras personas  facilitando el uso incorrecto de la tarjeta podr&aacute; causar da&ntilde;o a una o mas partes participantes del sistema de tarjetas de cr&eacute;dito (e.g. usuarios, establecimientos participantes y el emisor). El reparo de dicho da&ntilde;o surgido del incumplimiento de su obligaci&oacute;n/responsabilidad como tarjetahabiente de prevenir dichos da&ntilde;os puede resultar en compromisos monetarios de usted como tarjetahabiente.</span>
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
                    &lt;%&ndash;<A class="" HREF="" title="Click para continuar" target="" id="TDC_continuar">Click para continuar >></A>&ndash;%&gt;
                    <input type="button" class="botonGrande" id="comun_TAGTitleContinuarTDC1" value="Click para Continuar">
                </td>
            </tr>
        </TABLE>
        <input type="hidden" id="pantalla" value="">
    </div>
</div>
<div id="div_creditCard_AE" style="display: none">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="'100%'">
        <tbody>
        <tr>
            <td class="datos"><span class="label_numero_cuenta_TDC_AE" id="tdcconsultaedocta_TAGNumeroTarjeta">N&uacute;mero de Tarjeta </span><span>:</span></td>
            <td class="datos">
                <select  id="numero_cuenta_TDC_AE" title="Numero de Tarjeta" class="requerido_TDC_AE" onchange="limpiarTabla_TDC(this.value); consultarOnClickTDCEstadoCuenta(this.value);">

                </select>
            </td>
            <td class="datos" width="1%">
                <span id="tdcconsultaedocta_TAGFecha">Fecha</span><span>:</span>
            </td>
            <td class="datos">
                <select id="date_TDC_AE" title="Fecha" class="requerido_TDC_AE" onchange="limpiarTabla_TDC_fecha();">
                </select>
            <td class="botones_formulario" width="38%">
                <input type="button" id="consulta_TDC_AE" value="Consultar" class="botonEDOCuenta">
            </td>
            <td  width='8%'>
                <div id="botonVolverPortafolioCC"> <input type="button" id="btnVolverPortafolioCC" value="Volver" class="botonEDOCuenta oculto"></div>
            </td>
            <td  width="5%" class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a style="display: none" id="estado_cuenta_TDC_imprimir" onclick="javascript:crearPDF_accountStatement_TDC()" TITLE="imprimir"> <img class="imprimir"  src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
            </select></td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <legend id="tdcconsultaedocta_TAGResumenTDC"> Resumen de Estado de Cuenta </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" width="50%">
                <span class="label_saldo_TDC_AE" id="tdcconsultaedocta_TAGSaldoAnterior"> Saldo Anterior </span><span>:</span>
            </td>
            <td class="datos5" width="40%">
                <span id="saldo_TDC_AE" >  </span>
            </td>

            <td class="datos4" width="50%">
                <span class="label_limiteCredito_TDC_AE" id="tdcconsultaedocta_TAGLimiteCredito">L&iacute;mite de Cr&eacute;dito </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="limiteCredito_TDC_AE">  </span>
            </td>


        </tr>
        <tr>
            <td class="datos4" width="50%">
            </td>
            <td class="datos5" width="40%">
            </td>

            <td class="datos4" width="50%">
                <span class="label_creditoDisponible_TDC_AE" id="tdcconsultaedocta_TAGCreditoDisp">Credito Disponible </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="creditoDisponible_TDC_AE" > </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">

            </td>
            <td class="datos5" width="40%">

            </td>
            <td class="datos4" width="50%">
                <span class="label_fechaFacturacion_TDC_AE" id="tdcconsultaedocta_TAGFechaFact">Fecha de Facturacion </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="fechaFacturacion_TDC_AE" > </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">

            </td>
            <td class="datos5" width="40%">

            </td>
            <td class="datos4" width="50%">
                <span class="label_fechaLimitePago_TDC_AE" id="tdcconsultaedocta_TAGFechaPagAntes">Fecha Limite de Pago </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="fechaLimitePago_TDC_AE" > </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">

            </td>
            <td class="datos5" width="40%">

            </td>
            <td class="datos4" width="50%">
                <span class="label_saldoActual_TDC_AE" id="tdcconsultaedocta_TAGSaldoActual">Saldo Actual </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="saldoActual_TDC_AE" > </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">

            </td>
            <td class="datos5" width="40%">

            </td>
            <td class="datos4" width="50%">
                <span class="label_pagoTotal_TDC_AE" id="tdcconsultaedocta_TAGPagoTotal">Pago Total </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="pagoTotal_TDC_AE" class="spanFormulario_creditCards_AE"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">

            </td>
            <td class="datos5" width="40%">

            </td>
            <td class="datos4" width="50%">
                <span class="label_pagoMinimo_TDC_AE" id="tdcconsultaedocta_TAGPagoMin">Pago Minimo </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="pagoMinimo_TDC_AE"> </span>
            </td>
        </tr>

        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="div_tabla_consulta_TDC_AE" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_noInfo_accountStatement_creditCard" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_TDC">No posee tarjetas de cr&eacute;dito que consultar </span>
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
            <h2 id="TDC_titulo_BT" class="banner__title banner__title--modifier">
                CREDIT CARDS / ACCOUNT STATEMENT
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome11" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="TDC_titulo_credit_card">CREDIT CARDS</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="TDC_titulo_account_statement">ACCOUNT STATEMENT</li>
            </ul>
            <p id="TAG_INFO_ESTADOS_2" class="banner__description banner__description--modifier">
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
            <h2 id="TDC_titulo_BT" class="banner__title banner__title--modifier">
                CREDIT CARDS / ACCOUNT STATEMENT
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome11" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TDC_titulo_credit_card">CREDIT CARDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TDC_titulo_account_statement">ACCOUNT STATEMENT</li>
            </ul>
            <p id="TAG_INFO_ESTADOS_2" class="banner__description banner__description--modifier">
                Security advises to avoid frauds. Please read carefully the
                following information.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_creditCard_AE_alertaSeguridad" class="section">
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
        </div>
    </div>
    <div id="div_creditCard_AE" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <div class="section__field">
                        <label id="tdcconsultaedocta_TAGNumeroTarjeta" for="numero_cuenta_TDC_AE">LABEL</label>
                        <div class="select-section select-section--form">
                            <select id="numero_cuenta_TDC_AE"
                                    title="Credit Card Number"
                                    class="select-section__select select-section__select--form requerido_TDC_AE"
                                    onchange="limpiarTabla_TDC(this.value); consultarOnClickTDCEstadoCuenta(this.value);">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                        </div>
                    </div>
                    <div id="botonVolverPortafolioCC" class="section__buttons">
                        <input type="button" id="btnVolverPortafolioCC" value="Volver" class="section__button button button--white oculto">
                    </div>                    
                    <img id="transit_imprimir" onclick="javascript:crearPDF_accountStatement_TDC()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="tdcconsultaedocta_TAGFecha" for="numero_cuenta_TDC_AE">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="date_TDC_AE"
                                        title="Date"
                                        class="select-section__select select-section__select--form requerido_TDC_AE"
                                        onchange="limpiarTabla_TDC_fecha();">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                            </div>
                        </div>
                    </div>
                    <div class="section__buttons">
                        <button class="section__button button" id="consulta_TDC_AE">Consult</button>
                    </div>
                </div>
             <!--    <div class="section__row">
                    <span id="TAG_INFO_ESTADOS"></span>
                </div> -->
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="table__titles">
                        <span id="tdcconsultaedocta_TAGResumenTDC" class="table__title">Balances / Transactions</span>
                    
                        <div id="btn-notification-modal-cc-as" class="notice" style="justify-content: right;">
                            <img class="notice__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png" alt="" />
                            <span id="transferencias_TAGNoticeBLA" class="notice__text">TIPS</span>
                        </div>
                    </div>
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGSaldoAnterior" style="font-weight: bold;">>Previous Balance</span> <span id="saldo_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGLimiteCredito" style="font-weight: bold;">Credit Limit</span> <span id="limiteCredito_TDC_AE" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGCreditoDisp" style="font-weight: bold;">Available Credit Limit</span> <span id="creditoDisponible_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGFechaFact" style="font-weight: bold;">Statement Closing Date</span> <span id="fechaFacturacion_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGFechaPagAntes" style="font-weight: bold;">Payment Due Date</span> <span id="fechaLimitePago_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGSaldoActual" style="font-weight: bold;">New Balance</span> <span id="saldoActual_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGPagoTotal" style="font-weight: bold;">Total Payment</span> <span id="pagoTotal_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="tdcconsultaedocta_TAGPagoMin" style="font-weight: bold;">Minimum Payment Due</span> <span id="pagoMinimo_TDC_AE" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                                <tr class="table__row">
                                    <th id="tdcconsultaedocta_TAGSaldoAnterior" class="table__item table__item--head">Previous Balance:</th>
                                    <th id="tdcconsultaedocta_TAGLimiteCredito" class="table__item table__item--head">Credit Limit:</th>
                                    <th id="tdcconsultaedocta_TAGCreditoDisp" class="table__item table__item--head">Available Credit Limit:</th>
                                    <th id="tdcconsultaedocta_TAGFechaFact" class="table__item table__item--head">Statement Closing Date:</th>
                                    <th id="tdcconsultaedocta_TAGFechaPagAntes" class="table__item table__item--head">Payment Due Date:</th>
                                    <th id="tdcconsultaedocta_TAGSaldoActual" class="table__item table__item--head">New Balance:</th>
                                    <th id="tdcconsultaedocta_TAGPagoTotal" class="table__item table__item--head">Total Payment:</th>
                                    <th id="tdcconsultaedocta_TAGPagoMin" class="table__item table__item--head">Minimum Payment Due:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table__row">
                                    <td class="table__item right" data-label="Previous Balance:">
                                        <span id="saldo_TDC_AE"></span>
                                    </td>
                                    <td class="table__item right" data-label="Credit Limit:">
                                        <span id="limiteCredito_TDC_AE"></span>
                                    </td>
                                    <td class="table__item right" data-label="Available Credit Limit:">
                                        <span id="creditoDisponible_TDC_AE"></span>
                                    </td>
                                    <td class="table__item" data-label="Statement Closing Date:">
                                        <span id="fechaFacturacion_TDC_AE"></span>
                                    </td>
                                    <td class="table__item" data-label="Payment Due Date:">
                                        <span id="fechaLimitePago_TDC_AE"></span>
                                    </td>
                                    <td class="table__item right" data-label="New Balance:">
                                        <span id="saldoActual_TDC_AE"</span>
                                    </td>
                                    <td class="table__item right" data-label="Total Payment:">
                                        <span id="pagoTotal_TDC_AE"></span>
                                    </td>
                                   <td class="table__item right" data-label="Minimum Payment Due:">
                                        <span id="pagoMinimo_TDC_AE"></span>
                                    </td>
                                 </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <table id="tabla_consulta_TDC_AE" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="notification-modal" id="notification-modal-cc-as">
        <div class="notification-modal__top">
            <img class="notification-modal__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png"
                                                  alt=""/>
            <span id="tag_title_tips_credit_card" class="notification-modal__title">TIPS</span></div>
        <div class="notification-modal__content">
            <ul class="section__list">
                <li id="comun_TAGAlertaTDCV1_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV2_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV3_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV4_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV5_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV6_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV7_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV8_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV9_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV10_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV11_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV12_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV13_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV14_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV15_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV16_1">                    
                </li>
                <li id="comun_TAGAlertaTDCV17_1">                    
                </li>
            </ul>
                
        <div class="notification-modal__spacebetween notification-modal__spacebetween--modifier">
            <img class="notification-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
            <div>
                <a id="TagBtnCloseas" class="notification-modal__button button-alternative" href="#close" rel="modal:close">Cerrar</a>
            </div>
        </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>


<script>
    $("#btn-notification-modal-cc-as").click(function (){

        $("#notification-modal-cc-as").modal({
            showClose: !1,
            modalClass: "notification-modal",
            fadeDuration: 100,
            blockerClass: "notification-modal--blocker",
        });



    });    
</script>
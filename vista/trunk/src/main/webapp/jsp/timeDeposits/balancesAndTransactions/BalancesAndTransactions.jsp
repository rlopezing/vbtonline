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
    <h1 id="tagTitulo_Colocaciones_BT">Colocaciones / Saldos y Transacciones</h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_COLOCACIONES_SALDOS">This option allows you to check balances and transactions of your Time Deposit(s).</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_colocacionesSaldosTrans" style="display: none">
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%">
            <tbody>
            <tr>
                <td class="datos" width="15%"><span class="CBT_label_numero_cuenta_TDBT" id="colocacionessaldos_TAGNumeroColocacion">N&uacute;mero de Colocaci&oacute;n </span> <span>:</span></td>
                <td class="datos" width="70%">
                    <select  id="colocaciones_numero_cuenta_BT" title="Numero de cuenta" class="invisible_print" style="width:240px;"  >

                    </select>
                    <span id="colocaciones_numero_cuenta_BT_select" class="visible_print"></span>
                </td>
                <td  width='10%'>
                    <div id="botonVolverPortafolioColocaciones"> <input type="button" id="btnVolverPortafolioColocaciones" value="Volver" class="botonEDOCuenta oculto"></div>
                </td>
                <td width="5%"  class="botones_formulario">
                    <a id="between_imprimir_bloqueos" onclick="print_colocacionesSaldosTrans()" title="imprimir">
                        <img class="imprimir"  src="../vbtonline/resources/images/comun/impresora.gif" border="0" width="18" height="15"/>
                    </a>
                </td>

            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend>
            <span id="tag_saldo_colocaciones" style="display: block">Datos Cuenta</span>
            <span id="tag_saldo_colocaciones2" style="display: none"></span>
            <span id="tag_saldo_colocacionesFecha" style="display: none"></span>
        </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tbody>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_titular" id="colocacionessaldos_TAGTitularColocacion">Titular Colocaci&oacute;n </span><span>:</span>
                </td>
                <td class="datos5" >
                    <b><span id="titular_BT_Colocaciones">  </span>   </b>
                </td>

                <td class="datos4" >
                    <span class="CBT_label_montoApertura" id="colocacionessaldos_TAGMontoApertura">Monto Apertura </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="montoApertura_BT_Colocaciones">  </span>
                </td>


            </tr>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_moneda" id="colocacionessaldos_TAGMoneda">Moneda </span><span>:</span>
                </td>
                <td class="datos5" >
                   <b><span id="moneda_BT_Colocaciones">  </span></b>
                </td>

                <td class="datos4" >
                    <div id="linkBloqueo"></div>
                </td>
                <td class="datos6" >
                    <span id="montoBloqueado_BT_Colocaciones"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_fechaApertura" id="colocacionessaldos_TAGFechaApertura">Fecha Apertura </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="fechaApertura_BT_Colocaciones"> </span>
                </td>
                <td class="datos4" >
                    <span class="CBT_label_montoVencimiento" id="colocacionessaldos_TAGMontoVencimiento">Monto al Vencimiento </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="montoVencimiento_BT_Colocaciones"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_fechaVencimiento" id="colocacionessaldos_TAGFechaVencimiento">Fecha Vencimiento </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="fechaVencimiento_BT_Colocaciones"> </span>
                </td>
                <td class="datos4" >
                    <span class="CBT_label_tasa" id="colocacionessaldos_TAGTasa">Tasa </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="tasa_BT_Colocaciones"> </span>
                </td>
            </tr>

            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_BT_Colocaciones" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="noPoseeColocacionesSaldosTrans" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>
--%>

<%--<div id="div_mensajes_info_desc_tabla_TDBT" class="info_descp_tabla oculto">--%>
    <%--<div id="cerrar_div_mensajes_info_desc_tabla_TDBT"><img width="24px" height="24px" src="../vbtonline/resources/images/close.png"></div>--%>
    <%--<div id="mensajes_info_desc_tabla_TDBT">--%>
        <%--<table width="100%">--%>
            <%--<tr>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficiario">Beneficiary </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Beneficiary_TDBT"></span></td>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentassaldos_TAGCuentaNumero">Account </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Account_TDBT"></span></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBanco">Bank </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Bank_TDBT"></span></td>--%>
                <%--<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Currency </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Currency_TDBT"></span></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentasbloqueos_TAGObservacion">Observation </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Observation_TDBT"></span></td>--%>
                <%--<td width="25%"></td>--%>
                <%--<td width="25%"></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>

<%--</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tagTitulo_Colocaciones_BT" class="banner__title banner__title--modifier">
                MUTUAL FUNDS / BALANCES AND TRANSACTIONS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome17" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavColocaciones">MUTUAL FUND</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTitulo_Nav_Colocaciones_BT">BALANCES & TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_COLOCACIONES_SALDOS" class="banner__description banner__description--modifier">
                This option allows you to check the balances and transactions of your Mutual Funds.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tagTitulo_Colocaciones_BT" class="banner__title banner__title--modifier">
                MUTUAL FUNDS / BALANCES AND TRANSACTIONS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome17" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavColocaciones">MUTUAL FUND</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTitulo_Nav_Colocaciones_BT">BALANCES & TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_COLOCACIONES_SALDOS" class="banner__description banner__description--modifier">
                This option allows you to check the balances and transactions of your Mutual Funds.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_colocacionesSaldosTrans" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__headline">
                    <div class="section__header">
                        <div class="section__field">
                            <label id="colocacionessaldos_TAGNumeroColocacion" for="colocaciones_numero_cuenta_BT">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="colocaciones_numero_cuenta_BT"
                                        title="Time Deposit number"
                                        class="select-section__select select-section__select--form invisible_print">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <img id="between_imprimir_bloqueos" onclick="print_colocacionesSaldosTrans()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="table__titles">
                        <span class="table__title"></span>
                        <p class="balance-date balance-date--note">
                            <span id="tag_saldo_colocaciones" style="display: block">Datos Cuenta</span>
                            <span id="tag_saldo_colocaciones2" style="display: none"></span>
                            <span id="tag_saldo_colocacionesFecha" style="display: none"></span>
                        </p>
                    </div>
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGTitularColocacion" style="font-weight: bold;">Time Deposit holder</span>: <span id="titular_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGMoneda" style="font-weight: bold;">Currency</span>: <span id="moneda_BT_Colocaciones" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGFechaApertura" style="font-weight: bold;">Opening date</span>: <span id="fechaApertura_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGFechaVencimiento" style="font-weight: bold;">Maturity date</span>: <span id="fechaVencimiento_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGMontoApertura" style="font-weight: bold;">Opening amount</span>: <span id="montoApertura_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGBloqueado" style="font-weight: bold;">Blocked Amount</span>: <span id="montoBloqueado_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGMontoVencimiento" style="font-weight: bold;">Maturity amount</span>: <span id="montoVencimiento_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="colocacionessaldos_TAGTasa" style="font-weight: bold;">Rate</span>: <span id="tasa_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                                <tr class="table__row">
                                    <th id="colocacionessaldos_TAGTitularColocacion" class="table__item table__item--head">Time Deposit holder:</th>
                                    <th id="colocacionessaldos_TAGMoneda" class="table__item table__item--head">Currency:</th>
                                    <th id="colocacionessaldos_TAGFechaApertura" class="table__item table__item--head">Opening date:</th>
                                    <th id="colocacionessaldos_TAGFechaVencimiento" class="table__item table__item--head">Maturity date:</th>
                                    <th id="colocacionessaldos_TAGMontoApertura" class="table__item table__item--head">Opening amount:</th>
                                    <th id="colocacionessaldos_TAGBloqueado" class="table__item table__item--head">Blocked Amount:</th>
                                    <th id="colocacionessaldos_TAGMontoVencimiento" class="table__item table__item--head">Maturity amount:</th>
                                    <th id="colocacionessaldos_TAGTasa" class="table__item table__item--head">Rate:</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table__row">
                                    <td class="table__item" data-label="Time Deposit holder:">
                                        <span id="titular_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td id="moneda_BT_Colocaciones" class="table__item table__item--currency" data-label="Currency:">
                                        -
                                    </td>
                                    <td class="table__item" data-label="Opening date:">
                                        <span id="fechaApertura_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Maturity date:">
                                        <span id="fechaVencimiento_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Opening amount:">
                                        <span id="montoApertura_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Blocked Amount:">
                                        <span id="montoBloqueado_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Maturity amount:">
                                        <span id="montoVencimiento_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Rate:">
                                        <span id="tasa_BT_Colocaciones" class="spanFormulario_Account_BA"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <table id="tabla_consulta_BT_Colocaciones" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="noPoseeColocacionesSaldosTrans" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__content">
                <span id="mensaje_sinColocaciones"></span>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>

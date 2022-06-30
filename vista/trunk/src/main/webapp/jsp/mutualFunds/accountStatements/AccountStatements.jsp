<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div>
    <h1 id="FM_titulo_ACSFondos">Fondos Mutuales / Saldos y Transacciones </h1>
</div>

<div id="div_accountStatement_ACSFondos">
    <fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="3%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left">
                    <span class="datosInfo" id="TAG_INFO_ESTADO_CUENTA_FONDOS">This option allows you to see and print your account statement.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
                <tr>
                    <td class="datos" width="7%">
                        <span class="label_numero_cuenta" id="fondossaldosedocuenta_TAGFondoMutual">Mutual Fund </span><span>:</span>
                    </td>
                    <td class="datos" width="25%">
                        <!--  consultarEdoCuentaOnclick() -->
                        <select id="estado_cuenta_fondos_numero_cuenta" title="Fondo Mutual" class="selectFormulario_mutualFund_AE requerido_mutualFund_AE"  style="width:96%;" onchange="limpiarTabla_AEFM();"  >

                        </select>
                    </td>
                    <td class="datos" width="3%"><span class="label_fecha_mes" id="fondossaldosedocuenta_TAGMes">Mes </span><span>:</span></td>
                    <td class="datos" width="12%">
                        <select  id="estado_cuenta_fondos_fecha_mes" title="Mes" class="selectFormulario_mutualFund_AE requerido_mutualFund_AE" style="width:105px;" onchange="limpiarTabla_AEFM();" >

                        </select>
                    </td>
                    <td class="datos" width="3%"><span class="label_fecha_anio" id="fondossaldosedocuenta_TAGAnio">A&ntilde;o </span><span>:</span></td>
                    <td class="datos" width="12%">
                        <input type="text"  id="estado_cuenta_fondos_fecha_anio" title="Año" class="inputFormulario_mutualFund_AE requerido_mutualFund_AE" maxlength="4" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
                    </td>
                    <td  class="botones_formulario" width="12%">
                        <input type="button"  id="estado_cuenta_fondos_consultar" value="Consultar" class="botonEDOCuenta" onclick="limpiarTabla_AEFM(); consultarEdoCuentaFondoMutualOnclick();" >
                    </td>
                    <td  width="3%" class="botones_formulario">
                            &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                        <a id="estado_cuenta_fondos_imprimir" title="Imprimir" onclick="crearPDF_ESTADO_CUENTA_FONDOS()">
                            <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border='0' width='18' height='15'></a>
                    </td>
                </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset class="div_consulta" id="div_encabezadogralFM">
        <legend>
            <span id="saldo_BT_OtherInvestments" style="display: block"></span>
            <span id="saldo_BT_OtherInvestments2" style="display: none"></span>
            <span id="saldo_BT_OtherInvestmentsFecha" style="display: none"></span>
        </legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" style="width: 98%;">
            <tbody>
            <tr>
                <td class="datos4" >
                    <span class="label_titular_SalTrans_OI" id="ASFunds_TAGholder">Titular Inversi&oacute;n</span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="ASFunds_holder" class="spanFormulario_OI_BT">  </span>
                </td>

                <td class="datos4" >
                    <span class="label_VUI_SalTrans_OI" id="ASFunds_TAGtotalBalance">Unidades Totales</span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASFunds_totalBalance" class="spanFormulario_OI_BT"> </span>
                </td>

            </tr>
            <tr>
                <td class="datos4" >
                    <span class="label_moneda_SalTrans_OI" id="ASFunds_TAGcurrency">Moneda</span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="ASFunds_currency" class="spanFormulario_OI_BT">  </span>
                </td>

                <td class="datos4" >
                    <span class="label_unidadesTotales_SalTrans_OI" id="ASFunds_TAGguarantee">Unidades en Garantia</span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASFunds_guarantee" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>

                </td>
                <td class="datos4" >
                    <span class="label_unidadesTotales_SalTrans_OI" id="ASFunds_TAGavalaible">Unidades Disponibles</span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASFunds_avalaible" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >

                </td>
                <td class="datos5" >

                </td>
                <td class="datos4" >
                    <span class="label_VUI_SalTrans_OI" id="ASFunds_TAGNAV">VUI </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASFunds_NAV" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                </td>
                <td class="datos5" >
                </td>
                <td class="datos4" >
                    <span class="label_totalMoneda_SalTrans_OI" id="ASFunds_TAGcurrencybalance">Total en Moneda</span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASFunds_currencybalance" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta" >
        <div id="estado_cuenta_fondos_div_tabla_consulta" class="div_tabla_consulta">
        </div>
    </fieldset>

</div>
<div id="div_noInfo_accountStatement_ACSFondos" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account2">No posee cuentas que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="FM_titulo_ACSFondos" class="banner__title banner__title--modifier">
                Mutual Funds / Account statements
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome22" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="FM_EC_tag_link_mutual_funds">MUTUAL FUNDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="FM_EC_tag_link_balanc_transaction">ACCOUNT STATEMENTS</li>
            </ul>
            <p id="TAG_INFO_ESTADO_CUENTA_FONDOS" class="banner__description banner__description--modifier">
                This option allows you to check the account statement of your Mutual Funds.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="FM_titulo_ACSFondos" class="banner__title banner__title--modifier">
                Mutual Funds / Account statements
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome22" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="FM_EC_tag_link_mutual_funds">MUTUAL FUNDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="FM_EC_tag_link_balanc_transaction">ACCOUNT STATEMENTS</li>
            </ul>
            <p id="TAG_INFO_ESTADO_CUENTA_FONDOS" class="banner__description banner__description--modifier">
                This option allows you to check the account statement of your Mutual Funds.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_accountStatement_ACSFondos" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div class="section__field">
                        <label id="fondossaldosedocuenta_TAGFondoMutual" for="estado_cuenta_fondos_numero_cuenta">LABEL</label>
                        <div class="select-section select-section--form">
                            <select id="estado_cuenta_fondos_numero_cuenta"
                                    title="Mutual Fund"
                                    class="select-section__select select-section__select--form selectFormulario_mutualFund_AE requerido_mutualFund_AE"
                                    onchange="limpiarTabla_AEFM();">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                    <img id="estado_cuenta_fondos_imprimir" onclick="crearPDF_ESTADO_CUENTA_FONDOS()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="fondossaldosedocuenta_TAGMes" for="estado_cuenta_fondos_fecha_mes">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="estado_cuenta_fondos_fecha_mes"
                                        title="Month"
                                        class="select-section__select select-section__select--form selectFormulario_mutualFund_AE requerido_mutualFund_AE"
                                        onchange="limpiarTabla_AEFM();">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="section__field">
                            <label id="fondossaldosedocuenta_TAGAnio" for="estado_cuenta_fondos_fecha_anio">LABEL</label>
                            <input id="estado_cuenta_fondos_fecha_anio"
                                   title="Year"
                                   class="input input--form inputFormulario_mutualFund_AE requerido_mutualFund_AE"
                                   maxlength="4"
                                   type="text"
                                   onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);">
                        </div>
                    </div>
                    <div class="section__buttons">
                        <input type="button" id="estado_cuenta_fondos_consultar" value="Consult" class="section__button button" onclick="limpiarTabla_AEFM(); consultarEdoCuentaFondoMutualOnclick();">
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div id="div_encabezadogralFM" class="table">
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASFunds_TAGholder" style="font-weight: bold;">Unitholder(s)</span> <span id="ASFunds_holder" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="ASFunds_TAGcurrency" style="font-weight: bold;">Currency</span> <span id="ASFunds_currency" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASFunds_TAGtotalBalance" style="font-weight: bold;">Total Balance (in Units)</span> <span id="ASFunds_totalBalance" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="ASFunds_TAGguarantee" style="font-weight: bold;">Units Given in Guarantee</span> <span id="ASFunds_guarantee" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASFunds_TAGavalaible" style="font-weight: bold;">Units Avalaible</span> <span id="ASFunds_avalaible" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="ASFunds_TAGNAV" style="font-weight: bold;">N.A.V.</span> <span id="ASFunds_NAV" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASFunds_TAGcurrencybalance" style="font-weight: bold;">Balance (in Currency)</span> <span id="ASFunds_currencybalance" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <!-- <li class="table__item table__item--head"><span id="colocacionessaldos_TAGTasa" style="font-weight: bold;">Rate</span>: <span id="tasa_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li> -->
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                                <tr class="table__row">
                                    <th id="ASFunds_TAGholder" class="table__item table__item--head">Unitholder(s)</th>
                                    <th id="ASFunds_TAGcurrency" class="table__item table__item--head">Currency</th>
                                    <th id="ASFunds_TAGtotalBalance" class="table__item table__item--head">Total Balance (in Units)</th>
                                    <th id="ASFunds_TAGguarantee" class="table__item table__item--head">Units Given in Guarantee</th>
                                    <th id="ASFunds_TAGavalaible" class="table__item table__item--head">Units Avalaible</th>
                                    <th id="ASFunds_TAGNAV" class="table__item table__item--head">N.A.V.</th>
                                    <th id="ASFunds_TAGcurrencybalance" class="table__item table__item--head">Balance (in Currency)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table__row">
                                    <td class="table__item" data-label="Unitholder(s)">
                                        <span id="ASFunds_holder" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td id="ASFunds_currency" class="table__item table__item--currency" data-label="Currency">
                                        -
                                    </td>
                                    <td class="table__item" data-label="Total Balance (in Units)">
                                        <span id="ASFunds_totalBalance" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Units Given in Guarantee">
                                        <span id="ASFunds_guarantee" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Units Avalaible">
                                        <span id="ASFunds_avalaible" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="N.A.V.">
                                        <span id="ASFunds_NAV" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Balance (in Currency)">
                                        <span id="ASFunds_currencybalance" class="spanFormulario_Account_BA"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <table id="estado_cuenta_fondos_tabla_consulta" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="div_noInfo_accountStatement_ACSFondos" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__content">
                <span id="noInfo_account2">Usted no posee Fondos Mutuales que consultar </span>
            </div>
        </div>
    </div>
</main>

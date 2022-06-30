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
    <h1 id="TAGtitle_OtherInvestmentsAS">OTRAS INVERSIONES / Estados de Cuenta </h1>
</div>

<div id="div_otherInvestments_AS">
    <fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="3%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left">
                    <span class="datosInfo" id="TAG_INFO_ESTADO_CUENTA_OTRAS_INVERSIONES">Esta opción le permite consultar los estados de cuenta de sus otras inversiones.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
            <tr>
                <td class="datos" width="12%">
                    <span class="label_numero_cuenta" id="otrasinversionesedocuenta_TAGOtrasInversiones">Otras Inversiones </span><span>:</span>
                </td>
                <td class="datos" width="30%">
                    <!--  consultarEdoCuentaOnclick() -->
                    <select id="estado_cuenta_otrasinversiones_numero_cuenta" title="Numero de cuenta" class="selectFormulario_othersInvestments_AE requerido_othersInvestments_AE"  style="width:90%;" onchange="limpiarTabla_AE();"  >

                    </select>
                </td>
                <td class="datos" width="3%"><span class="label_fecha_mes" id="otrasinversionesedocuenta_TAGMes">Mes </span><span>:</span></td>
                <td class="datos" width="12%">
                    <select id="estado_cuenta_otrasinversiones_fecha_mes" title="Mes" class="selectFormulario_othersInvestments_AE requerido_othersInvestments_AE" style="width:105px;" onchange="limpiarTabla_AE();" >

                    </select>
                </td>
                <td class="datos" width="3%"><span class="label_fecha_anio" id="otrasinversionesedocuenta_TAGAnio">A&ntilde;o </span><span>:</span></td>
                <td class="datos" width="12%">
                    <input type="text"  id="estado_cuenta_otrasinversiones_fecha_anio" title="Año" class="inputFormulario_othersInvestments_AE requerido_othersInvestments_AE" maxlength="4" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
                </td>
                <td  class="botones_formulario" width="12%">
                    &lt;%&ndash;<input type="button"  id="estado_cuenta_otrasinversiones_consultar" value="Consultar" class="botonEDOCuenta" onclick="limpiarTabla_AE(); consultarEdoCuentaOtrasInversionesOnclick();" >&ndash;%&gt;
                    <input type="button"  id="estado_cuenta_otrasinversiones_consultar" value="Consultar" class="botonEDOCuenta" onclick="limpiarTabla_AE();" >
                </td>
                <td  width="3%" class="botones_formulario">
                    &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                    <a id="estado_cuenta_otrasinversiones_imprimir"  onclick="crearPDF_ESTADO_CUENTA_OI()">
                        <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border='0' width='18' height='15'></a>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset class="div_consulta" id="div_encabezadogralIO">
        <legend>
            <span id="saldo_BT_OtherInvestments" style="display: block"></span>
            <span id="saldo_BT_OtherInvestments2" style="display: none"></span>
            <span id="saldo_BT_OtherInvestmentsFecha" style="display: none"></span>
        </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tbody>
            <tr>
                <td class="datos4" >
                    <span class="label_titular_SalTrans_OI" id="ASIO_TAGholder">Titular Inversi&oacute;n</span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="ASIO_holder" class="spanFormulario_OI_BT">  </span>
                </td>

                <td class="datos4" >
                    <span class="label_VUI_SalTrans_OI" id="ASIO_TAGtotalBalance">Unidades Totales </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASIO_totalBalance" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                    <span class="label_moneda_SalTrans_OI" id="ASIO_TAGcurrency">Moneda </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="ASIO_currency" class="spanFormulario_OI_BT">  </span>
                </td>

                <td class="datos4" >
                    <span class="label_unidadesTotales_SalTrans_OI" id="ASIO_TAGguarantee">Unidades en Garantia</span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASIO_guarantee" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td>

                </td>
                <td>

                </td>
                <td class="datos4" >
                    <span class="label_unidadesTotales_SalTrans_OI" id="ASIO_TAGavalaible">Unidades Disponibles </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASIO_avalaible" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >

                </td>
                <td class="datos5" >

                </td>
                <td class="datos4" >
                    <span class="label_VUI_SalTrans_OI" id="ASIO_TAGNAV">VUI </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASIO_NAV" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                </td>
                <td class="datos5" >
                </td>
                <td class="datos4" >
                    <span class="label_totalMoneda_SalTrans_OI" id="ASIO_TAGcurrencybalance">Total en Moneda</span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="ASIO_currencybalance" class="spanFormulario_OI_BT"> </span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset class="div_consulta" >
        <div id="estado_cuenta_otrasinversiones_div_tabla_consulta" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>

<div id="div_noInfo_OtherInvestmentsAS" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account2">Usted no posee Fondos Mutuales que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TAGtitle_OtherInvestmentsAS" class="banner__title banner__title--modifier">
                OTHER INVESTMENTS / Account statements
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome24" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_other_investments">OTHER INVESTMENTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_account_statements">ACCOUNT STATEMENTS</li>
            </ul>
            <p id="TAG_INFO_ESTADO_CUENTA_OTRAS_INVERSIONES" class="banner__description banner__description--modifier">
                This option allows you to check the account statement of your Mutual Funds.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TAGtitle_OtherInvestmentsAS" class="banner__title banner__title--modifier">
                OTHER INVESTMENTS / Account statements
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome24" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_other_investments">OTHER INVESTMENTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_account_statements">ACCOUNT STATEMENTS</li>
            </ul>
            <p id="TAG_INFO_ESTADO_CUENTA_OTRAS_INVERSIONES" class="banner__description banner__description--modifier">
                This option allows you to check the account statement of your Mutual Funds.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_otherInvestments_AS" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div class="section__field">
                        <span id="otrasinversionesedocuenta_TAGOtrasInversiones">Other Investments</span>
                        <div class="select-section select-section--form">
                            <select id="estado_cuenta_otrasinversiones_numero_cuenta"
                                    title="From Account"
                                    onchange="limpiarTabla_AE();"
                                    class="select-section__select select-section__select--form selectFormulario_othersInvestments_AE requerido_othersInvestments_AE">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                    <img id="estado_cuenta_otrasinversiones_imprimir" onclick="crearPDF_ESTADO_CUENTA_OI()"
                         class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div class="section__field">
                            <span id="otrasinversionesedocuenta_TAGMes">Other Investments</span>
                            <div class="select-section select-section--form">
                                <select id="estado_cuenta_otrasinversiones_fecha_mes"
                                        title="From Account"
                                        onchange="limpiarTabla_AE();"
                                        class="select-section__select select-section__select--form selectFormulario_othersInvestments_AE requerido_othersInvestments_AE">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="section__field">
                            <span id="otrasinversionesedocuenta_TAGAnio">Other Investments</span>
                            <input type="text" id="estado_cuenta_otrasinversiones_fecha_anio" title="Year"
                                   class="ml-1_5 input input--form inputFormulario_othersInvestments_AE requerido_othersInvestments_AE" maxlength="4"
                                   onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);">
                        </div>
                    </div>
                    <button class="section__button button" id="estado_cuenta_otrasinversiones_consultar">CONSULT</button>
                </div>
            </div>
            <div class="section__content">
                <div id="div_encabezadogralIO" class="table">
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASIO_TAGholder" style="font-weight: bold;">Shareholder(s)</span>: <span id="ASIO_holder" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="ASIO_TAGcurrency" style="font-weight: bold;">Currency</span>: <span id="ASIO_currency" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASIO_TAGtotalBalance" style="font-weight: bold;">Total Balance (in Shares)</span>: <span id="ASIO_totalBalance" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="ASIO_TAGguarantee" style="font-weight: bold;">Shares Given in Guarantee</span>: <span id="ASIO_guarantee" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASIO_TAGavalaible" style="font-weight: bold;">Shares Avalaible</span>: <span id="ASIO_avalaible" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="ASIO_TAGNAV" style="font-weight: bold;">N.A.V.</span>: <span id="ASIO_NAV" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="ASIO_TAGcurrencybalance" style="font-weight: bold;">Balance (in Currency)</span>: <span id="ASIO_currencybalance" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <!-- <li class="table__item table__item--head"><span id="colocacionessaldos_TAGTasa" style="font-weight: bold;">Rate</span>: <span id="tasa_BT_Colocaciones" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li> -->
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                                <tr class="table__row">
                                    <th id="ASIO_TAGholder" class="table__item table__item--head">Shareholder(s)</th>
                                    <th id="ASIO_TAGcurrency" class="table__item table__item--head">Currency</th>
                                    <th id="ASIO_TAGtotalBalance" class="table__item table__item--head">Total Balance (in Shares)</th>
                                    <th id="ASIO_TAGguarantee" class="table__item table__item--head">Shares Given in Guarantee</th>
                                    <th id="ASIO_TAGavalaible" class="table__item table__item--head">Shares Avalaible</th>
                                    <th id="ASIO_TAGNAV" class="table__item table__item--head">N.A.V.</th>
                                    <th id="ASIO_TAGcurrencybalance" class="table__item table__item--head">Balance (in Currency)</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table__row">
                                    <td class="table__item" data-label="Shareholder(s)">
                                        <span id="ASIO_holder"></span>
                                    </td>
                                    <td id="ASIO_currency" class="table__item table__item--currency" data-label="Currency">
                                    </td>
                                    <td class="table__item" data-label="Total Balance (in Shares)">
                                        <span id="ASIO_totalBalance"></span>
                                    </td>
                                    <td class="table__item" data-label="Shares Given in Guarantee">
                                        <span id="ASIO_guarantee"></span>
                                    </td>
                                    <td class="table__item" data-label="Shares Avalaible">
                                        <span id="ASIO_avalaible"></span>
                                    </td>
                                    <td class="table__item" data-label="N.A.V.">
                                        <span id="ASIO_NAV"></span>
                                    </td>
                                    <td class="table__item" data-label="Balance (in Currency)">
                                        <span id="ASIO_currencybalance"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <table id="estado_cuenta_otrasinversiones_tabla_consulta" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>
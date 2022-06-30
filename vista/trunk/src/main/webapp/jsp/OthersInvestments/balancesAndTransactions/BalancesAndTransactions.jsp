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
    <h1 id="OI_titulo_BT">Otras Inversiones / Saldos y Transacciones </h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_OTRAS_INVERSIONES">This option allows you to check the balances and transactions of your other investments.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_OtherInvestments_BT">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td class="datos" width="11%"><span class="OtherInvestments_label_SalTrans_OI" id="otrasinversionessaldos_TAGOtrasInversiones">Otras Inversiones </span><span>:</span></td>
            <td class="datos" width="70%">
                <select  id="numero_cuenta_SalTrans_OI" title="Numero de cuenta" class="invisible_print requerido_SalTrans_OI" style="" onchange="cargarDetalleCuentaOtrasInversiones(this.value)">

                </select>
                <span id="numero_cuenta_SalTrans_OI_select" class="visible_print"></span>
            </td>
            <td  width='10%'>
                <div id="botonVolverPortafolioOI"> <input type="button" id="btnVolverPortafolioOI" value="Volver" class="botonEDOCuenta oculto"></div>
            </td>
            <td width="5%" class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a id="saldos_imprimir" title="Imprimir" onclick="print_SALDOS_FONDOS_OI()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <legend>
        <span id="saldo_BT_OtherInvestments" style="display: block"></span>
        <span id="saldo_BT_OtherInvestments2" style="display: none"></span>
        <span id="saldo_BT_OtherInvestmentsFecha" style="display: none"></span>
    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" >
                <span class="label_titular_SalTrans_OI" id="otrasinversionessaldos_TAGTitularOtrasInversiones">Titular Inversi&oacute;n</span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="titular_SalTrans_OI" class="spanFormulario_OI_BT">  </span>
            </td>

            <td class="datos4" >
                &lt;%&ndash;<span class="label_unidadesBloqueadas_SalTrans_OI" id="otrasinversionessaldos_TAGBloqueado">Unidades Bloqueadas </span><span>:</span>&ndash;%&gt;
                <div id="linkBloqueo_OI"></div>
            </td>
            <td class="datos6" >
                <span id="unidadesBloqueadas_SalTrans_OI" class="spanFormulario_OI_BT">  </span>
            </td>


        </tr>
        <tr>
            <td class="datos4" >
                <span class="label_moneda_SalTrans_OI" id="otrasinversionessaldos_TAGMoneda">Moneda </span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="moneda_SalTrans_OI" class="spanFormulario_OI_BT">  </span>
            </td>

            <td class="datos4" >
                <span class="label_unidadDisponible_SalTrans_OI" id="otrasinversionessaldos_TAGDisponible">Unidades Disponibles </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadDisponible_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >

            </td>
            <td class="datos5" >

            </td>
            <td class="datos4" >
                <span class="label_unidadesTotales_SalTrans_OI" id="otrasinversionessaldos_TAGValorActual">Unidades Totales </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadesTotales_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_VUI_SalTrans_OI" id="otrasinversionessaldos_TAGVUI">VUI </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="VUI_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_montoTransito_SalTrans_OI" id="otrasinversionessaldos_TAGEnTransito">Monto en Tr&aacute;nsito </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="montoTransito_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_totalMoneda_SalTrans_OI" id="otrasinversionessaldos_TAGTotalMoneda">Total en Moneda </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="totalMoneda_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>

        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    &lt;%&ndash;<span id="fechaBloqueado_SalTrans_FM"></span>&ndash;%&gt;
    <legend id="otrasinversionessaldos_TAGTransacciones"> Transacciones</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 100%;">
        <tr>
            <td class="datos" width="34%">
                <span id="otrasinversionessaldos_TAGTipoTransaccion">Tipo Transacci&oacute;n </span><span>:</span>
                <select  id="tipo_transaccion_SalTrans_OI" title="Tipo Transaccion" class="requerido_SalTrans_OI selectFormulario_OI_BT" style="width:220px;"  >

                </select>
            </td>
            <td class="datos"> </td>

            <td class="datos" width="30%">
                <span id="otrasinversionessaldos_TAGIntervaloTransaccion">Transacci&oacute;n por </span><span>:</span>
                <select  id="buscar_SalTrans_OI" title="Buscar" class="requerido_SalTrans_OI selectFormulario_OI_BT" style="width:100px;" onchange="validarActivarFechas_SalTrans_OI(this.value)" >

                </select>
            </td>

            <td  class="botones_formulario" width="20%">
                <input type="button" id="consulta_SalTrans_OI" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>

        <tr id="fechas_SalTrans_OI" style="display: none">
            &lt;%&ndash;<td class="datos"></td>&ndash;%&gt;
            <td class="datos" colspan="2">
                <span id="otrasinversionessaldos_TAGFechaDesde">Fecha Desde</span> <span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_SalTrans_OI" class="invisible_print inputFormulario_OI_BT requeridoFecha_SalTrans_OI" tabindex="2">
                <span id="fechaDesdeFiltro_SalTrans_OI_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos" colspan="2">
                <span id="otrasinversionessaldos_TAGFechaHasta">Fecha Hasta:</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_SalTrans_OI"  class="invisible_print inputFormulario_OI_BT requeridoFecha_SalTrans_OI" tabindex="2">
                <span id="fechaHastaFiltro_SalTrans_OI_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="div_tabla_consulta_SalTrans_OI" class="div_tabla_consulta"></div>
    <div id="paginacion_tabla_consulta_SalTrans_OI"></div>
</fieldset>
</div>
<div id="div_noInfo_BT_otherInvestment" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="noInfo_otherInvestment">Usted no posee Otras Inversiones que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="OI_titulo_BT" class="banner__title banner__title--modifier">
                Other Investments / Balances And Transactions
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome23" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="OI_BT_tag_link_other_investments">OTHER INVESTMENTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="OI_BT_tag_link_balance_transactions">BALANCES AND TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_OTRAS_INVERSIONES" class="banner__description banner__description--modifier">
                This option allows you to check the balances and transactions of your other investments.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="OI_titulo_BT" class="banner__title banner__title--modifier">
                Other Investments / Balances And Transactions
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome23" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="OI_BT_tag_link_other_investments">OTHER INVESTMENTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="OI_BT_tag_link_balance_transactions">BALANCES AND TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_OTRAS_INVERSIONES" class="banner__description banner__description--modifier">
                This option allows you to check the balances and transactions of your other investments.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_OtherInvestments_BT" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__headline">
                    <div class="section__header">
                        <div class="section__field">
                            <label id="otrasinversionessaldos_TAGOtrasInversiones" for="numero_cuenta_SalTrans_OI">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="numero_cuenta_SalTrans_OI"
                                        title="Other Investments"
                                        class="select-section__select select-section__select--form invisible_print requerido_SalTrans_OI"
                                        onchange="cargarDetalleCuentaOtrasInversiones(this.value)">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="botonVolverPortafolioOI" class="section__buttons" >
                            <input type="button" id="btnVolverPortafolioOI" value="Volver" class="section__button button button--white oculto">
                            <img id="saldos_imprimir" title="Imprimir" onclick="print_SALDOS_FONDOS_OI()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGTitularOtrasInversiones" style="font-weight: bold;">Investment Holder</span> <span id="titular_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGMoneda" style="font-weight: bold;">Currency</span> <span id="moneda_SalTrans_OI" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGBloqueado" style="font-weight: bold;">Units Blocked</span> <span id="unidadesBloqueadas_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGDisponible" style="font-weight: bold;">Units Available</span> <span id="unidadDisponible_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGValorActual" style="font-weight: bold;">Balance in Units</span> <span id="unidadesTotales_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGVUI" style="font-weight: bold;">NAV</span> <span id="VUI_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGEnTransito" style="font-weight: bold;">Amount in Transit</span> <span id="montoTransito_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="otrasinversionessaldos_TAGTotalMoneda" style="font-weight: bold;">Total in Currency</span> <span id="totalMoneda_SalTrans_OI" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                                <tr class="table__row">
                                    <th id="otrasinversionessaldos_TAGTitularOtrasInversiones" class="table__item table__item--head">Investment Holder</th>
                                    <th id="otrasinversionessaldos_TAGMoneda" class="table__item table__item--head">Currency</th>
                                    <th id="otrasinversionessaldos_TAGBloqueado" class="table__item table__item--head">Units Blocked</th>
                                    <th id="otrasinversionessaldos_TAGDisponible" class="table__item table__item--head">Units Available</th>
                                    <th id="otrasinversionessaldos_TAGValorActual" class="table__item table__item--head">Balance in Units</th>
                                    <th id="otrasinversionessaldos_TAGVUI" class="table__item table__item--head">NAV</th>
                                    <th id="otrasinversionessaldos_TAGEnTransito" class="table__item table__item--head">Amount in Transit</th>
                                    <th id="otrasinversionessaldos_TAGTotalMoneda" class="table__item table__item--head">Total in Currency</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table__row">
                                    <td class="table__item" data-label="Investment Holder">
                                        <span id="titular_SalTrans_OI"></span>
                                    </td>
                                    <td id="moneda_SalTrans_OI" class="table__item table__item--currency" data-label="Currency">
                                        -
                                    </td>
                                    <td class="table__item" data-label="Units Blocked">
                                        <span id="unidadesBloqueadas_SalTrans_OI"></span>
                                    </td>
                                    <td class="table__item" data-label="Units Available">
                                        <span id="unidadDisponible_SalTrans_OI"></span>
                                    </td>
                                    <td class="table__item" data-label="Balance in Units">
                                        <span id="unidadesTotales_SalTrans_OI"></span>
                                    </td>
                                    <td class="table__item" data-label="NAV">
                                        <span id="VUI_SalTrans_OI"></span>
                                    </td>
                                    <td class="table__item" data-label="Amount in Transit">
                                        <span id="montoTransito_SalTrans_OI"></span>
                                    </td>
                                    <td class="table__item" data-label="Total in Currency">
                                        <span id="totalMoneda_SalTrans_OI"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <span id="otrasinversionessaldos_TAGTransacciones" class="table__title">TRANSACTIONS</span>
                    <div class="filter">
                        <div class="filter__grid">
                            <div class="filter__item">
                                <label id="otrasinversionessaldos_TAGTipoTransaccion" for="tipo_transaccion_SalTrans_OI">Label</label>
                                <div class="select-section select-section--form">
                                    <select id="tipo_transaccion_SalTrans_OI"
                                            title="Type of Transaction"
                                            class="select-section__select select-section__select--form requerido_SalTrans_OI selectFormulario_OI_BT">
                                    </select>
                                    <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                </div>
                            </div>
                            <div class="filter__item">
                                <label id="otrasinversionessaldos_TAGIntervaloTransaccion" for="buscar_SalTrans_OI">Label</label>
                                <div class="select-section select-section--form">
                                    <select id="buscar_SalTrans_OI"
                                            title="Transaction From"
                                            class="select-section__select select-section__select--form requerido_SalTrans_OI selectFormulario_OI_BT"
                                            onchange="validarActivarFechas_SalTrans_OI(this.value)">
                                    </select>
                                    <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                </div>
                            </div>
                            <div id="fechas_SalTrans_OI" class="filter__item filter__item--dates">
                                <div class="filter__item">
                                    <label id="otrasinversionessaldos_TAGFechaDesde" for="fechaDesdeFiltro_SalTrans_OI">Label</label>
                                    <input id="fechaDesdeFiltro_SalTrans_OI"
                                           placeholder="dd/mm/yyyy"
                                           class="input input--form invisible_print inputFormulario_OI_BT requeridoFecha_SalTrans_OI"
                                           type="text"
                                           title="From">
                                </div>
                                <div class="filter__item">
                                    <label id="otrasinversionessaldos_TAGFechaHasta" for="fechaHastaFiltro_SalTrans_OI">Label</label>
                                    <input id="fechaHastaFiltro_SalTrans_OI"
                                           placeholder="dd/mm/yyyy"
                                           class="input input--form invisible_print inputFormulario_OI_BT requeridoFecha_SalTrans_OI"
                                           type="text"
                                           title="To">
                                </div>
                            </div>
                        </div>
                        <div class="filter__buttons">
                            <input type="button" id="consulta_SalTrans_OI" value="Consult" class="button">
                        </div>
                    </div>
                    <table id="tabla_consulta_SalTrans_OI" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>

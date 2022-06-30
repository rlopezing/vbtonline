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
    <h1 id="FM_titulo_BT">Fondos Mutuales / Saldos y Transacciones </h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_FONDOS_MUTUALES">This option allows you to check the balances and transactions of your Mutual Funds.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_mutualFunds_BT">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td class="datos"><span class="Fondo_label_SalTrans_FM" id="fondossaldos_TAGFondoMutual">Fondos Mutuales </span><span>:</span></td>
            <td class="datos">
                <select  id="numero_cuenta_SalTrans_FM" title="Numero de cuenta" class="requerido_SalTrans_FM invisible_print" style="" onchange="cargarDetalleCuentaFondosMutuales(this.value)">
                </select>
                <span id="numero_cuenta_SalTrans_FM_select" class="visible_print"></span>
            </td>

            <td  width='10%'>
                <div id="botonVolverPortafolioFM"> <input type="button" id="btnVolverPortafolioFM" value="Volver" class="botonEDOCuenta oculto"></div>
            </td>
            <td width="5%" class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a id="saldos_imprimir" title="Imprimir" onclick="print_SALDOS_FONDOS();" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <legend>
        <span id="saldo_BT_MutualFunds" style="display: block"></span>
        <span id="saldo_BT_MutualFunds2" style="display: none"></span>
        <span id="saldo_BT_fechaSaldo" style="display: none"></span>
    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" >
                <span class="label_titular_SalTrans_FM" id="fondossaldos_TAGTitularFondo">Titular del Fondo</span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="titular_SalTrans_FM" class="spanFormulario_MF_BT">  </span>
            </td>

            <td class="datos4" >
                <div id="linkBloqueo_mutual"></div>
                &lt;%&ndash;<span class="label_unidadesBloqueadas_SalTrans_FM" id="fondossaldos_TAGBloqueado">Unidades Bloqueadas </span><span>:</span>&ndash;%&gt;
            </td>
            <td class="datos6" >
                <span id="unidadesBloqueadas_SalTrans_FM" class="spanFormulario_MF_BT">  </span>
            </td>


        </tr>
        <tr>
            <td class="datos4" >
                <span class="label_moneda_SalTrans_FM" id="fondossaldos_TAGMoneda">Moneda </span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="moneda_SalTrans_FM" class="spanFormulario_MF_BT">  </span>
            </td>

            <td class="datos4" >
                <span class="label_unidadDisponible_SalTrans_FM" id="fondossaldos_TAGDisponible">Unidades Disponibles </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadDisponible_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >

            </td>
            <td class="datos5" >

            </td>
            <td class="datos4" >
                <span class="label_unidadesTotales_SalTrans_FM" id="fondossaldos_TAGValorActual">Unidades Totales </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadesTotales_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_VUI_SalTrans_FM" id="fondossaldos_TAGVUI">VUI </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="VUI_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_montoTransito_SalTrans_FM" id="fondossaldos_TAGEnTransito">Monto en Tr&aacute;nsito </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="montoTransito_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_totalMoneda_SalTrans_FM" id="fondossaldos_TAGTotalMoneda">Total en Moneda </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="totalMoneda_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>

        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    &lt;%&ndash;<span id="fechaBloqueado_SalTrans_FM"></span>&ndash;%&gt;
    <legend id="fondossaldos_TAGTransacciones"> Transacciones</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%;">
        <tr>
            <td class="datos" width="34%">
                <span id="fondossaldos_TAGTipoTransaccion">Tipo Transacci&oacute;n </span><span>:</span>
                <select  id="tipo_transaccion_SalTrans_FM" title="Tipo Transaccion" class="requerido_SalTrans_FM selectFormulario_MF_BT" style="width:220px;"  >

                </select>
            </td>
            <td class="datos"> </td>

            <td class="datos" width="30%">
                <span id="fondossaldos_TAGIntervaloTransaccion">Transacci&oacute;n por </span><span>:</span>
                <select  id="buscar_SalTrans_FM" title="Buscar" class="requerido_SalTrans_FM selectFormulario_MF_BT" style="width:100px;" onchange="validarActivarFechas_SalTrans_FM(this.value)" >

                </select>
            </td>
            <td  class="botones_formulario" width="20%">
                <input type="button" id="consulta_SalTrans_FM" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>

        <tr id="fechas_SalTrans_FM" style="display: none">
            &lt;%&ndash;<td class="datos"></td>&ndash;%&gt;
            <td class="datos" colspan="2">
                <span id="fondossaldos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_SalTrans_FM" class="invisible_print inputFormulario_MF_BT requeridoFecha_SalTrans_FM" tabindex="2">
                <span id="fechaDesdeFiltro_SalTrans_FM_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos" colspan="2">
                <span id="fondossaldos_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_SalTrans_FM"  class="invisible_print inputFormulario_MF_BT requeridoFecha_SalTrans_FM" tabindex="2">
                <span id="fechaHastaFiltro_SalTrans_FM_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="div_tabla_consulta_SalTrans_FM" class="div_tabla_consulta"></div>
    <div id="paginacion_tabla_consulta_SalTrans_FM" ></div>
</fieldset>
</div>
<div id="div_noInfo_BT_mutualFunds" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="noInfo_mutualFunds">Usted no posee Fondos Mutuales que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="FM_titulo_BT" class="banner__title banner__title--modifier">
                MUTUAL FUNDS / BALANCES AND TRANSACTIONS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome21" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_mutual_fung">MUTUAL FUND</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_balanc_transaction">BALANCES & TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_FONDOS_MUTUALES" class="banner__description banner__description--modifier">
                This option allows you to check the balances and transactions of your Mutual Funds.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="FM_titulo_BT" class="banner__title banner__title--modifier">
                MUTUAL FUNDS / BALANCES AND TRANSACTIONS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome21" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_mutual_fung">MUTUAL FUND</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_link_balanc_transaction">BALANCES & TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_FONDOS_MUTUALES" class="banner__description banner__description--modifier">
                This option allows you to check the balances and transactions of your Mutual Funds.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_mutualFunds_BT" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__headline">
                    <div class="section__header">
                        <div class="section__field">
                            <label id="fondossaldos_TAGFondoMutual" for="numero_cuenta_SalTrans_FM">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="numero_cuenta_SalTrans_FM"
                                        title="Mutual Fund"
                                        class="select-section__select select-section__select--form requerido_SalTrans_FM invisible_print"
                                        onchange="cargarDetalleCuentaFondosMutuales(this.value)">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="botonVolverPortafolioFM" class="section__buttons">
                            <input type="button" id="btnVolverPortafolioFM" value="Volver" class="section__button button button--white oculto">
                            <img id="saldos_imprimir" title="Print" onclick="print_SALDOS_FONDOS();" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="table__titles">
                        <span class="table__title"></span>
                        <p class="balance-date balance-date--note">
                            <span id="saldo_BT_MutualFunds" style="display: block"></span>
                            <span id="saldo_BT_MutualFunds2" style="display: none"></span>
                            <span id="saldo_BT_fechaSaldo" style="display: none"></span>
                        </p>
                    </div>
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGTitularFondo" style="font-weight: bold;">>Mutual Fund Holder</span> <span id="titular_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGMoneda" style="font-weight: bold;">Currency</span> <span id="moneda_SalTrans_FM" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGBloqueado" style="font-weight: bold;">Units Blocked</span> <span id="unidadesBloqueadas_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGDisponible" style="font-weight: bold;">Units Available</span> <span id="unidadDisponible_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGValorActual" style="font-weight: bold;">Balance in Units</span> <span id="unidadesTotales_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGVUI" style="font-weight: bold;">NAV</span> <span id="VUI_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGEnTransito" style="font-weight: bold;">Amount in Transit</span> <span id="montoTransito_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="fondossaldos_TAGTotalMoneda" style="font-weight: bold;">Total in Currency</span> <span id="totalMoneda_SalTrans_FM" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                                <tr class="table__row">
                                    <th id="fondossaldos_TAGTitularFondo" class="table__item table__item--head">Mutual Fund Holder</th>
                                    <th id="fondossaldos_TAGMoneda" class="table__item table__item--head">Currency</th>
                                    <th id="fondossaldos_TAGBloqueado" class="table__item table__item--head">Units Blocked</th>
                                    <th id="fondossaldos_TAGDisponible" class="table__item table__item--head">Units Available</th>
                                    <th id="fondossaldos_TAGValorActual" class="table__item table__item--head">Balance in Units</th>
                                    <th id="fondossaldos_TAGVUI" class="table__item table__item--head">NAV</th>
                                    <th id="fondossaldos_TAGEnTransito" class="table__item table__item--head">Amount in Transit</th>
                                    <th id="fondossaldos_TAGTotalMoneda" class="table__item table__item--head">Total in Currency</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr class="table__row">
                                    <td class="table__item" data-label="Mutual Fund Holder">
                                        <span id="titular_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td id="moneda_SalTrans_FM" class="table__item table__item--currency" data-label="Currency">
                                        -
                                    </td>
                                    <td class="table__item" data-label="Units Blocked">
                                        <span id="unidadesBloqueadas_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Units Available">
                                        <span id="unidadDisponible_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Balance in Units">
                                        <span id="unidadesTotales_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="NAV">
                                        <span id="VUI_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Amount in Transit">
                                        <span id="montoTransito_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                    <td class="table__item" data-label="Total in Currency">
                                        <span id="totalMoneda_SalTrans_FM" class="spanFormulario_Account_BA"></span>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <span id="fondossaldos_TAGTransacciones" class="table__title">TRANSACTIONS</span>
                    <div class="filter">
                        <div class="filter__grid">
                            <div class="filter__item">
                                <label id="fondossaldos_TAGTipoTransaccion" for="tipo_transaccion_SalTrans_FM">Label</label>
                                <div class="select-section select-section--form">
                                    <select id="tipo_transaccion_SalTrans_FM"
                                            title="Type of Transaction"
                                            class="select-section__select select-section__select--form requerido_SalTrans_FM selectFormulario_MF_BT">
                                    </select>
                                    <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                </div>
                            </div>
                            <div class="filter__item">
                                <label id="fondossaldos_TAGIntervaloTransaccion" for="buscar_SalTrans_FM">Label</label>
                                <div class="select-section select-section--form">
                                    <select id="buscar_SalTrans_FM"
                                            title="Transaction From"
                                            class="select-section__select select-section__select--form requerido_SalTrans_FM selectFormulario_MF_BT"
                                            onchange="validarActivarFechas_SalTrans_FM(this.value)">
                                    </select>
                                    <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                </div>
                            </div>
                            <div id="fechas_SalTrans_FM" class="filter__item filter__item--dates">
                                <div class="filter__item">
                                    <label id="fondossaldos_TAGFechaDesde" for="fechaDesdeFiltro_SalTrans_FM">Label</label>
                                    <input id="fechaDesdeFiltro_SalTrans_FM"
                                           placeholder="dd/mm/yyyy"
                                           class="input input--form invisible_print invisible_print inputFormulario_MF_BT requeridoFecha_SalTrans_FM"
                                           type="text"
                                           title="From">
                                </div>
                                <div class="filter__item">
                                    <label id="fondossaldos_TAGFechaHasta" for="fechaHastaFiltro_SalTrans_FM">Label</label>
                                    <input id="fechaHastaFiltro_SalTrans_FM"
                                           placeholder="dd/mm/yyyy"
                                           class="input input--form invisible_print invisible_print inputFormulario_MF_BT requeridoFecha_SalTrans_FM"
                                           type="text"
                                           title="To">
                                </div>
                            </div>
                        </div>
                        <div class="filter__buttons">
                            <input type="button" id="consulta_SalTrans_FM" value="Consult" class="button">
                        </div>
                    </div>
                    <table id="tabla_consulta_SalTrans_FM" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="div_noInfo_BT_mutualFunds" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__content">
                <span id="noInfo_mutualFunds">Usted no posee Fondos Mutuales que consultar </span>
            </div>
        </div>
    </div>
</main>
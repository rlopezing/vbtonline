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
    <h1 id="tagTitulo_Account_BT">Cuentas / Saldos y Transacciones</h1>
</div>
<div id="div_account_BalancesAndTransactions">
    <fieldset class="invisible_print div_info">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAG_INGO_CUENTA_SALDOS" class="datosInfo">This option allows you to check balances and transactions of your accounts.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tbody>
            <tr>
                <td class="datos"><span class="label_numero_cuenta" id="cuentassaldos_TAGNumeroCuenta">N&uacute;mero de cuenta </span><span>:</span></td>
                <td class="datos">
                    <select  id="estado_cuenta_numero_cuenta_BT" title="Numero de cuenta" class="invisible_print requeridoBT selectFormulario_Account_BA" style="width:266px;" onchange="cargarDetalleCuenta(this.value)" >

                    </select>
                    <span id="estado_cuenta_numero_cuenta_BT_select" class="visible_print"></span>
                </td>
                <td>
                    &lt;%&ndash;<span class="datos2" id="">Esta opci&oacute;n le permite consultar los saldos y transacciones de sus cuentas.</span>&ndash;%&gt;
                </td>
                <td  width='64%'>
                    <div id="botonVolverPortafolio"> <input type="button" id="btnVolverPortafolio" value="Volver" class="botonEDOCuenta oculto"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend>
            <span id="tagAccount_DatosCuenta_BT" style="display: block">Saldo</span>
            <span id="tagAccount_DatosCuenta_BT2" style="display: none">Saldo al: </span>
            <span id="tagAccount_fecha_BT"></span>
        </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tbody>
            <tr>
                <td class="datos4">
                    <span class="label_titular" id="cuentassaldos_TAGTitularCuenta">Titular </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="titular_BT" class="spanFormulario_Account_BA">  </span>
                </td>
                <td class="datos4">
                    &lt;%&ndash;<span class="label_bloqueado" id="cuentassaldos_TAGBloqueado">Bloqueado </span><span>:</span>&ndash;%&gt;
                    <div id="linkBloqueo_account"></div>
                </td>
                <td class="datos6" >
                    <span id="bloqueado_BT" class="spanFormulario_Account_BA"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4">
                    <span class="label_moneda" id="cuentassaldos_TAGMoneda">Moneda </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="moneda_BT" class="spanFormulario_Account_BA">  </span>
                </td>
                <td class="datos4">
                    <span class="label_diferido" id="cuentassaldos_TAGDiferido">Diferido </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="diferido_BT" class="spanFormulario_Account_BA">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos4">
                </td>
                <td class="datos5" >
                </td>
                <td class="datos4">
                    <span class="label_disponible" id="cuentassaldos_TAGDisponible">Disponible </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="disponible_BT" class="spanFormulario_Account_BA">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos4">
                    &lt;%&ndash;<input id="estado_cuenta_imprime" class="boton" value="imprimir">&ndash;%&gt;
                </td>
                <td class="datos5" >
                </td>
                <td class="datos4">
                    <span class="label_bloqueado" id="cuentassaldos_TAGSaldoActual">Saldo Actual </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="saldo_actual_BT" class=" spanFormulario_Account_BA"> </span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend id="cuentassaldos_TAGTransacciones"> Transacciones </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 100%;">
            <tr>
                <td class="datos"> <span id="cuentassaldos_TAGTipoTransaccion">Tipo de Transacci&oacute;n </span><span>:</span></td>
                <td class="datos"> <span id="cuentassaldos_TAGIntervaloTransaccion">Ver Transacciones de </span><span>:</span></td>
                <td class="datos"> <span id="cuentassaldos_TAGComentarioTransaccion">Comentario Transacci&oacute;n </span><span>:</span></td>
                <td class="datos"></td>
            </tr>
            <tr>
                <td class="datos" width="25%">
                    <select  id="tipo_transaccion_BT" title="Tipo Transaccion" class="" style="width:200px;"  >

                    </select>
                </td>
                <td class="datos" width="30%">
                    <select  id="transaccion_desde_BT" title="Transaction desde" class="requeridoBT selectFormulario_Account_BA" style="width:200px;" onchange="validarActivarFechas(this.value)" >

                    </select>
                </td>

                <td class="datos">
                    <input type="text"  id="comentario_transaccion_BT" title="Comentario Transaccion" class="" style="width:200px;"  />
                </td>

                <td  width="40%" class="botones_formulario">
                    &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                    <a style="display: none" id="balance_imprimir" onclick="crearPDF_balancesAndTransactions()" TITLE="imprimir"> <img class="imprimir"  src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
                </td>

                <td  class="botones_formulario" width="10%">
                    <input type="button" id="estado_cuenta_consultar_BT" value="Consultar" class="botonEDOCuenta">
                </td>
            </tr>
            <tr id="fechas_BT" style="display: none">
                <td class="datos" style="width: 31%;">
                    <span id="cuentassaldos_TAGFechaDesde">Desde</span><span>:</span>
                    &lt;%&ndash;esto debe ser un calendario que permita seleccionar la fecha&ndash;%&gt;
                    <input type="text" title="Fecha desde" id="fechaDesdeFiltroBT" class="invisible_print inputFormulario_Account_BA requeridoFechaBT" tabindex="2" maxlength="10">
                    <span id="fechaDesdeFiltroBT_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>
                <td class="datos" style="width: 31%;">
                    <span id="cuentassaldos_TAGFechaHasta">Hasta</span><span>:</span>
                    &lt;%&ndash;esto debe ser un calendario que permita seleccionar la fecha&ndash;%&gt;
                    <input type="text" title="Fecha hasta" id="fechaHastaFiltroBT"  class="invisible_print inputFormulario_Account_BA requeridoFechaBT" tabindex="2" maxlength="10">
                    <span id="fechaHastaFiltroBT_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <div id="estado_cuenta_div_tabla_consulta_BT" class="div_tabla_consulta">
        </div>
        <div id="paginacion_estado_cuenta_tabla_consulta_BT" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="noInfo_balancesAndTransactions_account" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account">No posee cuentas que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tagTitulo_Account_BT" class="banner__title banner__title--modifier">
                Balances & Transactionsss
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome3" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavCuentas">ACCOUNTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTitulo_Nav_Account_BT">BALANCES & TRANSACTIONS</li>
            </ul>
            <p id="TAG_INGO_CUENTA_SALDOS" class="banner__description banner__description--modifier">
                This option allows you to check balances and transactions of your
                accounts.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tagTitulo_Account_BT" class="banner__title banner__title--modifier">
                Balances & Transactionsss
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome3" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavCuentas">ACCOUNTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTitulo_Nav_Account_BT">BALANCES & TRANSACTIONS</li>
            </ul>
            <p id="TAG_INGO_CUENTA_SALDOS" class="banner__description banner__description--modifier">
                This option allows you to check balances and transactions of your
                accounts.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_account_BalancesAndTransactions" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__headline">
                    <div class="section__header">
                        <div class="section__field">
                            <label id="cuentassaldos_TAGNumeroCuenta" for="estado_cuenta_numero_cuenta_BT">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="estado_cuenta_numero_cuenta_BT"
                                        title="Account Number"
                                        class="select-section__select select-section__select--form invisible_print requeridoBT selectFormulario_Account_BA"
                                        onchange="cargarDetalleCuenta(this.value)">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="section__buttons" id="botonVolverPortafolio">
                            <input type="button" id="btnVolverPortafolio" value="Volver" class="section__button button button--white oculto">
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    
                <div class="table">
                    <div class="table__titles"><span class="table__title" id="tag_table_title_balance">Balance</span>
                        <div>
                            <span id="tagAccount_DatosCuenta_BT2"></span>
                            <span id="tagAccount_fecha_BT"></span>
                        </div>
                    </div>
                </div>

                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="cuentassaldos_TAGTitularCuenta" style="font-weight: bold;">ACCOUNT HOLDER</span> <span id="titular_BT" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="cuentassaldos_TAGMoneda" style="font-weight: bold;">CURRENCY</span> <span id="moneda_BT" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="cuentassaldos_TAGBloqueado" style="font-weight: bold;">BLOCKED</span> <span id="bloqueado_BT" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="cuentassaldos_TAGDiferido" style="font-weight: bold;">DEFERRED</span> <span id="diferido_BT" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="cuentassaldos_TAGDisponible" style="font-weight: bold;">AVAILABLE</span> <span id="disponible_BT" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="cuentassaldos_TAGSaldoActual" style="font-weight: bold;">CURRENT BALANCE</span> <span id="saldo_actual_BT" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                            <tr class="table__row">
                                <th id="cuentassaldos_TAGTitularCuenta" class="table__item table__item--head">ACCOUNT HOLDER</th>
                                <th id="cuentassaldos_TAGMoneda" class="table__item table__item--head">CURRENCY</th>
                                <th id="cuentassaldos_TAGBloqueado" class="table__item table__item--head">BLOCKED</th>
                                <th id="cuentassaldos_TAGDiferido" class="table__item table__item--head">DEFERRED</th>
                                <th id="cuentassaldos_TAGDisponible" class="table__item table__item--head">AVAILABLE</th>
                                <th id="cuentassaldos_TAGSaldoActual" class="table__item table__item--head">CURRENT BALANCE</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="table__row">
                                <td class="table__item">
                                    <span id="titular_BT" class="spanFormulario_Account_BA"></span>
                                </td>
                                <td id="moneda_BT" class="table__item table__item--currency">
                                    -
                                </td>
                                <td class="table__item">
                                    <span id="bloqueado_BT" class="spanFormulario_Account_BA"></span>
                                </td>
                                <td class="table__item">
                                    <span id="diferido_BT" class="spanFormulario_Account_BA"></span>
                                </td>
                                <td class="table__item">
                                    <span id="disponible_BT" class="spanFormulario_Account_BA"></span>
                                </td>
                                <td class="table__item">
                                    <span id="saldo_actual_BT" class="spanFormulario_Account_BA"></span>
                                </td>
                            </tr>
                            </tbody>

                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <span id="cuentassaldos_TAGTransacciones" class="table__title">TRANSACTIONS</span>
                    <div class="filter">
                        <div class="filter__grid">
                            <div class="filter__item">
                                <label id="cuentassaldos_TAGTipoTransaccion" for="tipo_transaccion_BT">Label</label>
                                <div class="select-section select-section--form">
                                    <select id="tipo_transaccion_BT"
                                            title="Type of Transaction"
                                            class="select-section__select select-section__select--form">
                                    </select>
                                    <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                </div>
                            </div>
                            <div class="filter__item">
                                <label id="cuentassaldos_TAGIntervaloTransaccion" for="transaccion_desde_BT">Label</label>
                                <div class="select-section select-section--form">
                                    <select id="transaccion_desde_BT"
                                            title="Transaction From"
                                            class="select-section__select select-section__select--form requeridoBT selectFormulario_Account_BA"
                                            onchange="validarActivarFechas(this.value)">
                                    </select>
                                    <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                </div>
                            </div>
                            <div class="filter__item">
                                <label id="cuentassaldos_TAGComentarioTransaccion" for="comentario_transaccion_BT">Label</label>
                                <input id="comentario_transaccion_BT" class="input input--form" type="text" title="Comentario Transaccion">
                            </div>
                            <div id="fechas_BT" class="filter__item filter__item--dates">
                                <div class="filter__item">
                                    <label id="cuentassaldos_TAGFechaDesde" for="fechaDesdeFiltroBT">Label</label>
                                    <input id="fechaDesdeFiltroBT"
                                           placeholder="dd/mm/yyyy"
                                           class="input input--form invisible_print inputFormulario_Account_BA requeridoFechaBT"
                                           type="text"
                                           maxlength="10"
                                           title="From">
                                </div>
                                <div class="filter__item">
                                    <label id="cuentassaldos_TAGFechaHasta" for="fechaHastaFiltroBT">Label</label>
                                    <input id="fechaHastaFiltroBT"
                                           placeholder="dd/mm/yyyy"
                                           class="input input--form invisible_print inputFormulario_Account_BA requeridoFechaBT"
                                           type="text"
                                           maxlength="10"
                                           title="To">
                                </div>
                            </div>
                        </div>
                        <div class="filter__buttons">
                            <img id="account_imprimir" onclick="crearPDF_balancesAndTransactions()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" title="Print">
                            <!-- <input type="button" id="balance_imprimir" onclick="crearPDF_balancesAndTransactions()" value="Print" style="display: none" class="button button--white"> -->
                            <input type="button" id="estado_cuenta_consultar_BT" value="Consult" class="button">
                        </div>
                    </div>
                    <table id="estado_cuenta_tabla_consulta_BT" class="table__content">
                     </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>

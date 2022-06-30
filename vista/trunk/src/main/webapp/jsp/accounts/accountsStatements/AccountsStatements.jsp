<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div class="clear"></div>
<div>
    <h1 id="tagTitulo_Account_AE">Cuentas / Estados de Cuenta</h1>
</div>
<div id="div_accountStatement_account">

    <fieldset class="invisible_print div_info" style="margin-left:auto;margin-right:auto;float:none;">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAG_INFO_ESTADOS_CUENTA" class="datosInfo">This option allows you to see and print your account statement.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
<fieldset class="div_consulta">
    <legend><span class="label_estado_cuenta" id="cuentasedocuenta_TAGEstadoCuenta">Estado de Cuenta</span></legend>
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%" align="center">
        <tbody>
        <tr>
            <td class="datos" width="12%"><span class="label_numero_cuenta" id="cuentasedocuenta_TAGNumeroCuenta">N&uacute;mero de cuenta </span><span>:</span></td>
            <td class="datos" width="25%">
                <select  id="estado_cuenta_numero_cuenta" title="Numero de cuenta" class="selectFormulario_Accounts_AE requerido_account_AE" style="width:250px;" onchange="limpiarTabla_AE(); consultarEdoCuentaOnclick();"  >

                </select>
            </td>
            <td class="datos" width="3%"><span class="label_fecha_mes" id="cuentasedocuenta_TAGMes">Mes </span><span>:</span></td>
            <td class="datos" width="12%">
                <select  id="estado_cuenta_fecha_mes" title="Mes de consulta" class="selectFormulario_Accounts_AE requerido_account_AE" style="width:105px;" onchange="limpiarTabla_AE(); consultarEdoCuentaOnclick();" >

                </select>
            </td>
            <td class="datos" width="3%"><span class="label_fecha_anio" id="cuentasedocuenta_TAGAnio">A&ntilde;o </span><span>:</span></td>
            <td class="datos" width="12%">
                <input type="text"  id="estado_cuenta_fecha_anio" title="Año de consulta" class="inputFormulario_Accounts_AE requerido_account_AE" maxlength="4" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
            </td>

            <td  class="botones_formulario" width="12%">
                <input type="button"  id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">
            </td>
            <td  width="3%" class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
               <a id="account_imprimir" onclick="crearPDF_accountStatement_account()" TITLE="Imprimir">
               <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    &lt;%&ndash;<legend id="tagAccount_DatosCuenta_AE"> Datos Cuenta </legend>&ndash;%&gt;
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" width="50%">
                <span class="label_titular" id="cuentasedocuenta_TAGTitularCuenta">Titular </span><span>:</span>
            </td>
            <td class="datos5" width="40%">
                <span id="titular_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
            <td class="datos4" width="50%">
                <span class="label_bloqueado" id="cuentasedocuenta_TAGBloqueado">Bloqueado </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="bloqueado_AS" class="spanFormulario_Accounts_AE"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">
                <span class="label_moneda" id="cuentasedocuenta_TAGMoneda">Moneda </span><span>:</span>
            </td>
            <td class="datos5" width="40%">
                <span id="moneda_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
            <td class="datos4" width="50%">
                <span class="label_diferido" id="cuentasedocuenta_TAGDiferido">Diferido </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="diferido_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">
            </td>
            <td class="datos5" width="40%">
            </td>
            <td class="datos4" width="50%">
                <span class="label_disponible" id="cuentasedocuenta_TAGDisponible">Disponible </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="disponible_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">
                &lt;%&ndash;<input id="estado_cuenta_imprime" class="boton" value="imprimir">&ndash;%&gt;
            </td>
            <td class="datos5" width="40%">
            </td>
            <td class="datos4" width="50%">
                <span class="label_bloqueado" id="cuentasedocuenta_TAGSaldoActual">Saldo Actual </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="saldo_actual_AS" class="spanFormulario_Accounts_AE"> </span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="estado_cuenta_div_tabla_consulta" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_noInfo_accountStatement_account" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account2">No posee cuentas que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tagTitulo_Account_AE" class="banner__title banner__title--modifier">
                ACCOUNT / ACCOUNT STATEMENT
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome4" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavCuentas2">ACCOUNTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTitulo_Nav_Account_AE">ACCOUNT STATEMENT</li>
            </ul>
            <p id="TAG_INFO_ESTADOS_CUENTA" class="banner__description banner__description--modifier">
                This option allows you to see and print your account statement.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tagTitulo_Account_AE" class="banner__title banner__title--modifier">
                ACCOUNT / ACCOUNT STATEMENT
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome4" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavCuentas2">ACCOUNTS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTitulo_Nav_Account_AE">ACCOUNT STATEMENT</li>
            </ul>
            <p id="TAG_INFO_ESTADOS_CUENTA" class="banner__description banner__description--modifier">
                This option allows you to see and print your account statement.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_accountStatement_account" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="cuentasedocuenta_TAGEstadoCuenta" class="section__title">Account Statement</span>
                    <div class="section__field">
                        <label id="cuentasedocuenta_TAGNumeroCuenta" for="estado_cuenta_numero_cuenta">LABEL</label>
                        <div class="select-section select-section--form">
                            <select id="estado_cuenta_numero_cuenta"
                                    title="Account Number"
                                    class="select-section__select select-section__select--form selectFormulario_Accounts_AE requerido_account_AE"
                                    onchange="limpiarTabla_AE(); consultarEdoCuentaOnclick();">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                        <img id="account_imprimir" onclick="crearPDF_accountStatement_account()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="cuentasedocuenta_TAGMes" for="estado_cuenta_fecha_mes">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="estado_cuenta_fecha_mes"
                                        title="Month"
                                        class="select-section__select select-section__select--form selectFormulario_Accounts_AE requerido_account_AE"
                                        onchange="limpiarTabla_AE(); consultarEdoCuentaOnclick();">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="section__field">
                            <label id="cuentasedocuenta_TAGAnio" for="estado_cuenta_fecha_anio">LABEL</label>
                            <input id="estado_cuenta_fecha_anio"
                                   title="Year"
                                   class="input input--form inputFormulario_Accounts_AE requerido_account_AE"
                                   maxlength="4"
                                   type="text"
                                   onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);">
                        </div>
                    </div>
                    <div class="section__buttons">
                        <button class="section__button button" id="estado_cuenta_consultar">CONSULT</button>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="dataTables_wrapper">
                        <div class="TableHeaderListContent">
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="cuentasedocuenta_TAGTitularCuenta" style="font-weight: bold;">ACCOUNT HOLDER</span> <span id="titular_AS" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="cuentasedocuenta_TAGMoneda" style="font-weight: bold;">CURRENCY</span> <span id="moneda_AS" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="cuentasedocuenta_TAGBloqueado" style="font-weight: bold;">BLOCKED</span> <span id="bloqueado_AS" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="cuentasedocuenta_TAGDiferido" style="font-weight: bold;">DEFERRED</span> <span id="diferido_AS" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                            <ul class="TableHeaderList">
                                <li class="table__item table__item--head"><span id="cuentasedocuenta_TAGDisponible" style="font-weight: bold;">AVAILABLE</span> <span id="disponible_AS" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                                <li class="table__item table__item--head"><span id="cuentasedocuenta_TAGSaldoActual" style="font-weight: bold;">CURRENT BALANCE</span> <span id="saldo_actual_AS" class="spanFormulario_Account_BA" style="margin-left: 1em;"></span></li>
                            </ul>
                        </div>
                        <%--<table class="table__content">
                            <thead>
                            <tr class="table__row">
                                <th id="cuentasedocuenta_TAGTitularCuenta" class="table__item table__item--head">Account Holder</th>
                                <th id="cuentasedocuenta_TAGMoneda" class="table__item table__item--head">Currency</th>
                                <th id="cuentasedocuenta_TAGBloqueado" class="table__item table__item--head">Blocked</th>
                                <th id="cuentasedocuenta_TAGDiferido" class="table__item table__item--head">Deferred</th>
                                <th id="cuentasedocuenta_TAGDisponible" class="table__item table__item--head">Available</th>
                                <th id="cuentasedocuenta_TAGSaldoActual" class="table__item table__item--head center">Current Balance</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="table__row">
                                <td class="table__item" data-label="Account Holder">
                                    <span id="titular_AS" class="spanFormulario_Accounts_AE"></span>
                                </td>
                                <td id="moneda_AS" class="table__item table__item--currency spanFormulario_Accounts_AE" data-label="Currency">
                                </td>
                                <td class="table__item" data-label="Blocked">
                                    <span id="bloqueado_AS" class="spanFormulario_Accounts_AE"></span>
                                </td>
                                <td class="table__item" data-label="Deferred">
                                    <span id="diferido_AS" class="spanFormulario_Accounts_AE"></span>
                                </td>
                                <td class="table__item" data-label="Available">
                                    <span id="disponible_AS" class="spanFormulario_Accounts_AE"></span>
                                </td>
                                <td class="table__item" data-label="Current Balance">
                                    <span id="saldo_actual_AS" class="spanFormulario_Accounts_AE"></span>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                        --%>
                    </div>
                </div>
                <div class="table">
                    <table id="estado_cuenta_tabla_consulta2" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>

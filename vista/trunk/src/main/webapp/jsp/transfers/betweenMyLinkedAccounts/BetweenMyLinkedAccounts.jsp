<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 23/07/2012
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div>
    <h1 id="transferencias_TAGEntreMisCuentas">Transferencias / Entre mis Productos </h1>
</div>
<div id="BetweenLinkedAccounts_form" >
    <fieldset id="DIV_INFO_INTERNAS_FI" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAG_INFO_INTERNAS_FI" class="datosInfo">This option enables you to make transfers between your accounts with VBT Bank & Trust.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset id="DIV_INFO_INTERNAS_FC" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAG_INFO_INTERNAS_FC" class="datosInfo">This option allows you to request transfers between VBT Bank & Trust customer accounts. Remember that such transfers must be subsequently approved and released by users with the appropriate permissions.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <div class="clear"></div>

    <fieldset id="BetweenLinkedAccounts_form_1" class="formulario_fieldset div_consulta">

        <legend id="transferencias_TAGNumeroCuentaDebitoLegend2" >N&uacute;mero de Cuenta Origen</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="600px">
                    <select id="BetweenLinkedAccounts_Accounts" title="Cuenta Origen" class="obligatorioBMLA selectFormulario_BMLA obligatorioCambioBMLA" style="width: 97%;"></select>
                    <label class="datos6">  * </label>
                </td>

                <td id="cancelacionProductorMostrar">
                    <label id="TAGproductoCancelacionBMLA" class="datos6"> Product Cancelation</label><span class="datos6">:</span>
                    <input type="checkbox" id="productCancelation" >
                </td>

                <td  class="botones_formulario invisible_print">
                    <a id="between_imprimir" onclick="printTransfers()" TITLE="imprimir" style="margin-left: 15px"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
                </td>

                <td class="datos2 invisible_print">
                    <label id="transferencias_TAGFechaCierre">Balance Disponible al:&nbsp; </label>
                    <b><label id="BetweenLinkedAccounts_tagAvailableBalanceDate"> </label></b>
                </td>
            </tr>

        </table>
    </fieldset>

    <fieldset id="BetweenLinkedAccounts_form_2" class="formulario_fieldset div_consulta" >
        <legend id="transferencias_TAGDatosTransferencia">Datos de la Transferencia</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td width="30%">
                    <label for="BetweenLinkedAccounts_NumCuentaDestino" id="transferencias_TAGNumeroCuentaCredito"  class="datos">Numero de Cuenta Destino</label><span class="datos">:</span>
                </td>
                <td width="70%">
                    <select id="BetweenLinkedAccounts_NumCuentaDestino" title="Número de Cuenta Destino" class="obligatorioBMLA selectFormulario_BMLA obligatorioCambioBMLA">

                    </select>
                    <label class="datos6">  * </label>
                </td>
            </tr>
            <tr>
                <td width="30%" >

                </td>
                <td width="70%">
                    <table>
                        <td>
                            <div id="unidadesMostrar">
                                <label id="TAGUnidades" class="datos6" > Unidades</label><span class="datos6">:</span>
                                <input type="checkbox" id="transferirUnidades" >
                            </div>
                        </td>
                        <td>
                            <div id="montoMostrarBMLA">
                                <label id="TAGmontoBMLA" class="datos6"> Monto</label><span class="datos6">:</span>
                                <input type="checkbox" id="transferirMontoBMLA" class="inputFormulario_BMLAVBT">
                            </div>
                        </td>
                    </table>
                </td>
            </tr>
            <tr id="montoInputMostrarBMLA">

                <td width="30%" >
                    <label for="BetweenLinkedAccounts_Monto" id="transferencias_TAGMonto"  class="datos negrita">Monto</label><span class="datos">:</span>
                </td>
                <td width="70%">
                    <span id="BetweenLinkedAccounts_Monto_print" class="datos visible_print"></span>
                    &lt;%&ndash;<input id="BetweenLinkedAccounts_Monto" type="text" maxlength="15" size="15" class="invisible_print obligatorioBMLA inputFormulario_BMLA" title="Monto" onblur="this.value=formatCurrency(this.value,true,2,',');"  onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/> <label class="datos" id="monedaBMLA">   </label><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>&ndash;%&gt;
                    <input id="BetweenLinkedAccounts_Monto" type="text" maxlength="15" size="15" class="invisible_print obligatorioBMLA inputFormulario_BMLA" title="Monto" value="" onblur="this.value=formatCurrency(this.value,true,2,'.');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'EM');"/> <label class="datos" id="monedaBMLA">   </label><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>

                </td>
            </tr>
            <tr  id="unidadesInputOcultoBMLA">
                <td width="30%">
                    <label for="BMLA_Unidades" id="TAGUnidadesProductoBMLA"  class="datos negrita">Units</label><span>:</span>
                </td>
                <td width="70%">
                    <input id="BMLA_Unidades" type="text" class="invisible_print inputFormulario_BMLAVBT obligatorioCambioBMLA" title="Unidades" maxlength="25" size="15" />
                    <label class="datos6 invisible_print">*</label>
                    <label id="cantidadUnidades" class="datos negrita">unidades mostradas</label>


                </td>
            </tr>
            <tr id="montoComisionMostrarBMLA" >
                <td width="30%">
                    <label id="TAGmontoComision" class="datos"> Comision</label><span class="datos6">: </span>
                </td>
                <td width="70%">
                    <label id="montoComisionBMLA" class="datos negrita"></label>
                </td>
            </tr>
            <tr id="descripcionTransferenciaBMLA">
                <td width="30%">
                    <label for="BetweenLinkedAccounts_Concepto" id="transferencias_TAGConcepto3"  class="datos">Concepto</label><span class="datos">:</span>
                </td>
                <td width="70%">
                    <span id="BetweenLinkedAccounts_Concepto_print" class="datos visible_print"></span>
                    <input id="BetweenLinkedAccounts_Concepto" style="width:250px" type="text" title="Concepto" class="invisible_print inputFormulario_BMLA" maxlength="40" size="40"/>
                </td>
            </tr>
            <tr id="motivoCancelacionProductoBMLA" >
                <td width="30%">
                    <label for="BetweenLinkedAccounts_Motivo" id="transferencias_TAGMotivoCancel" class="datos">Motivo</label><span>:</span>
                </td>
                <td width="70%">
                    <input id="BetweenLinkedAccounts_Motivo" style="width:250px" type="text" title="Motivo" class="invisible_print inputFormulario_TOCVBT obligatorioCancelacionBMLA" maxlength="40" size="40" />
                    <span id="BetweenLinkedAccounts_Motivo_print" class=" datos6 visible_print"></span><label class="datos6 invisible_print">* </label>
                </td>
            </tr>
            <tr>
                <td width="100%" colspan="2">
                    <label id="mandatoryField_BMLA" class="datosCampoObligatorio"> * Campos Obligatorios</label>
                </td>

            </tr>


        </table>
    </fieldset>

    <fieldset id="BetweenLinkedAccounts" class="formulario_fieldset div_consulta right">
        <input  type="button" id="BetweenLinkedAccounts_btn_cancelar" value="Cancelar" class="boton">
        <input  type="button" id="BetweenLinkedAccounts_btn_aceptar" value="Aceptar" class="boton">
    </fieldset>

    <fieldset id="BetweenLinkedAccountsAviso" class="formulario_fieldset div_consulta">
        &lt;%&ndash;<label class="datos" id="transferencias_TAGAvisosImportantes">&ndash;%&gt;
            &lt;%&ndash;AVISOS IMPORTANTES <br><br>&ndash;%&gt;

            &lt;%&ndash;Agradecemos lea antes de confirmar su operaci&oacute;n.<br><br> 1. La fecha valor de su operaci&oacute;n ser&aacute; el mismo d&iacute;a, de realizarse en fecha h&aacute;bil bancaria, dentro del horario estipulado. De efectuar la operaci&oacute;n en d&iacute;a feriado bancario o fin de semana, la fecha valor de su transferencia ser&aacute; el primer d&iacute;a h&aacute;bil bancario siguiente que corresponda.<br><br> 2. Las transacciones realizadas despu&eacute;s de la 3:00  pm (UTC/GMT -5) ser&aacute;n procesadas el d&iacute;a h&aacute;bil bancario siguiente.<br><br> 3. Para conocer el costo de sus operaciones en el VB&T Online, por favor referirse al Schedule of Fees ubicado en <A HREF="http://www.vbtbank.com">www.vbtbank.com</a><br><br> 4. Usted recibir&aacute; en su email registrado la notificaci&oacute;n de sus transacciones.&ndash;%&gt;
        &lt;%&ndash;</label>&ndash;%&gt;
            <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td height="50" >
                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="100%" height="35" class="V8NBNegroGris" align="center">
                                    <b><span id="transferencias_TAGAvisosImportantes_1" class="datos">IMPORTANT NOTICE</span></b>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%" class="V8NBNegroGris" align="left">
                            <span id="transferencias_TAGMsgDisclaimeEntrebancos_1" class="datos">
                                Please read carefully before entering any transaction.<br><br>
                                1. Provided that the transaction you request is placed on a business day and within the specified hours, your transaction will have 48 hours value date. If placed on holidays or weekend, value date will be 48 hours later started counting on next business day.<br><br>
                                2. Transactions placed after 3:00  pm (UTC/GMT -5) will be processed on the next business day.<br><br>
                                3. To learn about the fees applied to each transaction, please refer to the Schedule of Fees at <A HREF="http://www.vbtbank.com">www.vbtbank.com</a> <br><br>
                                4. You will receive a confirmation for each transaction on you registered email account.
                            </span>
                                </td>
                            </tr>
                        </TABLE>
                    </td>
                </tr>
            </TABLE>

    </fieldset>

</div>
<div id="noBetweenLinkedAccounts_form" style="display: none">
    <fieldset class="div_consulta">
        <span id="noInfo_BetweenLinkedAccounts_form" class="datos"></span>
    </fieldset>
</div>
<div id="summaryBetweenLinkedAccounts">
    <div class="titulo_resume" id="tituloResumen_BMLA">
        <span id="summary_TOB2">Resumen</span>
        <div class="descripcion_resume">
            <span id="sumaryConfirm_TOB2">Confirme los Datos de su Transferencia<br></span>
        </div>
    </div>


    <div class="botones_resume2">

        <table SUMMARY='tabla'cellpadding="4" cellspacing="1" class="tabla_resumen">
            <tbody>
            <tr id="div_numref_BMLA" style="display: none">
                <td class="titulo_resumen right" colspan="2">
                    <b><span id="transferencias_TAGNumeroRecibo">  Referencia Nro.&nbsp; </span> <span id="numRef_BMLA"></span> </b>
                </td>
            </tr>
            <tr>
                <td  class="titulo_resumen">
                   <span id="transferencias_TAGNumeroCuentaDebito2">Producto Origen</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_Accounts" class="datos_resumen"></span>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                   <span id="transferencias_TAGNumeroCuentaCredito2">Producto Destino</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_NumCuentaDestino"></span>
                </td>
            </tr>
            <tr id="montoResumenBMLAOcultar">
                <td class="titulo_resumen">
                    <span id="transferencias_TAGMonto3">Monto</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_Monto"></span>
                </td>
            </tr>
            <tr id="unidadesResumenBMLAOcultar">
                <td class="titulo_resumen">
                    <span id="transferencias_TAGUnidades3">Units</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_Unidades"></span>
                </td>
            </tr>
            <tr id="div_conceptp_BMLA" style="display: none">
                <td class="titulo_resumen">
                    <span id="transferencias_TAGConcepto2">Concepto</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_Concepto"></span>
                </td>
            </tr>
            <tr id="div_estatus_BMLA" style="display: none">

                <td class="titulo_resumen">
                    <span id="transferencias_TAGEstatusTransferencia">Estatus de la Transferencia</span>
                </td>
                <td class="datos_resumen">
                    <span id="status_BMLA" class="BMLA_borrarData"></span>
                </td>

            </tr>
            <tr id="div_cancelacion_BMLA" >

                <td class="titulo_resumen">
                    <span id="transferencias_TAGCancelacionProductoTransferencia">Cancelación de Producto</span>
                </td>
                <td class="datos_resumen">
                    <span id="cancelacion_BMLA" ></span>
                </td>

            </tr>

            <tr id="resumenBotones_BMLA" style="display: block">
                <td class="datos" colspan="2" >
                    <input type="button" id="btn_resumen_cancelar_BMLA" value="Back" class="boton">
                    <input type="button" id="btn_resumen_aceptar_BMLA" value="Aceptar" class="boton">
                </td>
            </tr>
            <tr id="resumenBotones_BMLA_Finales" style="display: none" >
                <td class="datos center" colspan="2">
                    <input type="button" id="btn_finalizar_BMLA_final" value="Hacer Otra" class="botonTransfer">
                    <input type="button" id="btn_home_BMLA_final" value="Aceptar" class="boton">
                    <input type="button" id="btn_imprimir_BMLA_final" value="Imprimir" class="boton">
                </td>
            </tr>
            </tbody>
        </table>
    </div>

</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_transfers.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="transferencias_TAGEntreMisCuentas" class="banner__title banner__title--modifier">Between My Linked Accounts </h2>
            <ul class="banner__nav">
                <li><a id="TagHome5" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="TAGNavTitleTransferencias">TRANSFERS</li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="transferencias_TAGNavEntreMisCuentas">BETWEEN MY LINKED ACCOUNTS</li>
            </ul>
            <p id="TAG_INFO_INTERNAS_FI" class="banner__description banner__description--modifier">This option enables you to make transfers between
                your accounts with VBT Bank & Trust.</p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="transferencias_TAGEntreMisCuentas" class="banner__title banner__title--modifier">Between My Linked Accounts </h2>
            <ul class="banner__nav">
                <li><a id="TagHome5" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGNavTitleTransferencias">TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="transferencias_TAGNavEntreMisCuentas">BETWEEN MY LINKED ACCOUNTS</li>
            </ul>
            <p id="TAG_INFO_INTERNAS_FI" class="banner__description banner__description--modifier">This option enables you to make transfers between
                your accounts with VBT Bank & Trust.</p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="BetweenLinkedAccounts_form" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                <div class="section__header">
                    <span id="transferencias_TAGNumeroCuentaDebitoLegend2" class="section__title">From Account</span>
                    <div class="section__info">
                        <p class="balance-date">
                            <span id="transferencias_TAGFechaCierre">Available Balance as at </span>
                            <span id="BetweenLinkedAccounts_tagAvailableBalanceDate"></span>
                        </p>
                        <img id="between_imprimir" onclick="printTransfers()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
                <div class="section__row">
                    <div class="section__form">
                        <div class="section__field">
                            <div class="select-section select-section--form">
                                <select id="BetweenLinkedAccounts_Accounts"
                                        title="From Account"
                                        class="select-section__select select-section__select--form obligatorioBMLA selectFormulario_BMLA obligatorioCambioBMLA">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="cancelacionProductorMostrar" class="section__checkbox checkbox-container">
                            <label id="TAGproductoCancelacionBMLA" class="checkbox-container__label" for="productCancelation">Product Cancelation</label>
                            <input id="productCancelation" class="checkbox-container__check" type="checkbox" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table__titles">
                    <span id="transferencias_TAGDatosTransferencia" class="table__title">TRANSFER DETAILS</span>
                    <div id="btn-notification-modal-bla" class="notice">
                        <img class="notice__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png"  alt=""/>
                        <span id="transferencias_TAGNoticeBLA" class="notice__text">NOTICE</span>
                    </div>
                </div>
                <div class="form-between">
                    <div class="form-between__grid" style="grid-template-columns: repeat(1, 12fr);">
                        <div class="form-between__item form-between__item--col_part1">
                            <label id="transferencias_TAGNumeroCuentaCredito" class="form-between__label field-obligatory" for="BetweenLinkedAccounts_NumCuentaDestino">Select Account*</label>
                            <div class="select-section select-section--form">
                                <select id="BetweenLinkedAccounts_NumCuentaDestino"
                                        title="To Account"
                                        class="select-section__select select-section__select--form obligatorioBMLA selectFormulario_BMLA obligatorioCambioBMLA">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>

                        <div class="form-between__item form-between__item--col2" style="grid-column-end: 2;grid-column-start: 1;">
                            <div style="display: flex;flex-direction: row;gap:1em;">
                                <div id="unidadesMostrar" class="checkbox-container">
                                    <label id="TAGUnidadesProd" class="checkbox-container__label" for="transferirUnidades">Unidades</label>
                                    <input id="transferirUnidades" class="checkbox-container__check" type="checkbox" />
                                </div>
                                <div id="montoMostrarBMLA" class="checkbox-container">
                                    <label id="TAGmontoBMLAProd" class="checkbox-container__label" for="transferirMontoBMLA">Monto</label>
                                    <input id="transferirMontoBMLA" class="checkbox-container__check inputFormulario_BMLAVBT" type="checkbox" />
                                </div>
                            </div>
                            <div style="display: flex;flex-direction: row;gap:1em;">
                                <div id="unitsBMLA" class="form-between__item" style="display: flex; flex-direction: column;">
                                    <label id="transferencias_TAGUnits" class="form-between__label">Units</label>
                                    <span id="cantidadUnidades"></span>
                                </div>
                                <div id="montoInputMostrarBMLA" class="form-between__item">
                                    <label id="transferencias_TAGMonto" class="form-between__label field-obligatory" for="BetweenLinkedAccounts_Monto">Amount</label>
                                    <input
                                            id="BetweenLinkedAccounts_Monto"
                                            type="text"
                                            maxlength="15"
                                            size="15"
                                            title="Amount"
                                            onblur="this.value=formatCurrency(this.value,true,2,'.');"
                                            onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'EM');"
                                            class="form-between__input input input--form invisible_print obligatorioBMLA inputFormulario_BMLA"
                                            />
                                </div>
                                <div id="unidadesInputOcultoBMLA" class="form-between__item">
                                    <label id="TAGUnidadesProductoBMLA" class="form-between__label field-obligatory" for="BMLA_Unidades">Units</label>
                                    <input
                                            id="BMLA_Unidades"
                                            type="text"
                                            maxlength="25"
                                            size="15"
                                            title="Unidades"
                                            onblur="this.value=formatCurrency(this.value,true,2,'.');"
                                            onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'EM');"
                                            class="form-between__input input input--form invisible_print inputFormulario_BMLAVBT obligatorioCambioBMLA"
                                            />
                                </div>
                                <div id="currencyBMLA" class="form-between__item">
                                    <label id="transferencias_TAGCurrency" class="form-between__label oculto">Currency</label>
                                    <div id="monedaBMLA">
                                        
                                    </div>
                                </div>
                                <div id="montoCuentaBMLA" style="display: flex;align-items: center;">
                                    <span></span>
                                </div>
                            </div>
                        </div>

                        <div id="descripcionTransferenciaBMLA" class="form-between__item form-between__item--col2" style="grid-column-start: 3;grid-column-end: 6;">
                            <label id="transferencias_TAGConcepto3" class="form-between__label" for="BetweenLinkedAccounts_Concepto">Description</label>
                            <input id="BetweenLinkedAccounts_Concepto"
                                   title="Description"
                                   type="text"
                                   maxlength="40"
                                   size="40"
                                   class="form-between__input input input--form invisible_print inputFormulario_BMLA" />
                        </div>
                        <div id="motivoCancelacionProductoBMLA" class="form-between__item form-between__item--col2" style="grid-column: 3 / 6;">
                            <label id="transferencias_TAGMotivoCancel" class="form-between__label field-obligatory" for="BetweenLinkedAccounts_Concepto">Porpouse</label>
                            <input id="BetweenLinkedAccounts_Motivo"
                                   title="Motivo"
                                   type="text"
                                   maxlength="40"
                                   size="40"
                                   class="form-between__input input input--form invisible_print inputFormulario_TOCVBT obligatorioCancelacionBMLA" />
                        </div>
                        <div id="montoComisionMostrarBMLA" class="form-between__item" >
                            <label id="TAGmontoComision" class="form-between__label">Commission</label>
                            <span class="form-between__value" id="montoComisionBMLA"></span>
                        </div>
                    </div>
                    <div class="form-between__between">
                        <span id="mandatoryField_BMLA" class="mandatory-fields">* Mandatory fields</span>
                        <div class="form-between__buttons">
                            <input id="BetweenLinkedAccounts_btn_cancelar" type="button" class="form-between__button button button--white" value="Cancel" />
                            <input id="BetweenLinkedAccounts_btn_aceptar" type="button" class="form-between__button button" value="Accept" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="summaryBetweenLinkedAccounts" class="section">
        <div class="section__container container">
            <div id="tituloResumen_BMLA" class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                <div class="section__header">
                    <span id="summary_TOB2" class="section__title">Summary</span>
                    <div class="section__info">
                        <span id="sumaryConfirm_TOB2">Confirme los Datos de su Transferencia </span>
                        <img class="section__icon" src="../vbtonline/resources/img/icons/ic_table_transfer_header_info.png" alt="" />
                    </div>
                </div>
            </div>
            <div id="summaryBetweenLinkedAccountsForm" class="section__content">
                <div class="table">
                    <table class="table__content table__content--summary">
                        <tr id="div_numref_BMLA" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroRecibo" class="table__item table__item--summary"><span>Referencia Nro.</span></td>
                            <td class="table__item table__item--summary" style="font-weight: bold"><span id="numRef_BMLA"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroCuentaDebito2" class="table__item table__item--summary"><span>Producto Origen</span></td>
                            <td class="table__item table__item--summary"><span id="RBetweenLinkedAccounts_Accounts"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroCuentaCredito2" class="table__item table__item--summary"><span>Producto Destino</span></td>
                            <td class="table__item table__item--summary"><span id="RBetweenLinkedAccounts_NumCuentaDestino"></span></td>
                        </tr>
                        <tr id="montoResumenBMLAOcultar" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span id="transferencias_TAGMonto3">Monto</span></td>
                            <td class="table__item table__item--summary"><span id="RBetweenLinkedAccounts_Monto"></span></td>
                        </tr>
                        <tr id="unidadesResumenBMLAOcultar" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span id="transferencias_TAGUnidades3">Units</span></td>
                            <td class="table__item table__item--summary"><span id="RBetweenLinkedAccounts_Unidades"></span></td>
                        </tr>
                        <tr id="div_conceptp_BMLA" style="display: none" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span id="transferencias_TAGConcepto2">Concepto</span></td>
                            <td class="table__item table__item--summary"><span id="RBetweenLinkedAccounts_Concepto"></span></td>
                        </tr>
                        <tr id="div_estatus_BMLA" style="display: none" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span id="transferencias_TAGEstatusTransferencia">Estatus de la Transferencia</span></td>
                            <td class="table__item table__item--summary"><span id="status_BMLA"></span></td>
                        </tr>
                        <tr id="div_cancelacion_BMLA" style="display: none" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span id="transferencias_TAGCancelacionProductoTransferencia">Cancelación de Producto</span></td>
                            <td class="table__item table__item--summary"><span id="cancelacion_BMLA"></span></td>
                        </tr>
                    </table>
                    <div id="resumenBotones_BMLA" style="display: block" class="table__spacebetween table__spacebetween--top">
                        <div></div>
                        <div class="table__buttons">
                            <input type="button" id="btn_resumen_cancelar_BMLA" value="Back" class="table__button button button--white">
                            <input type="button" id="btn_resumen_aceptar_BMLA" value="Aceptar" class="table__button button">
                        </div>
                    </div>
                    <div id="resumenBotones_BMLA_Finales" style="display: none" class="table__spacebetween table__spacebetween--top">
                        <div></div>
                        <div class="table__buttons">
                            <input type="button" id="btn_finalizar_BMLA_final" value="Hacer Otra" class="table__button button button--white">
                            <input type="button" id="btn_home_BMLA_final" value="Aceptar" class="table__button button">
                            <input type="button" id="btn_imprimir_BMLA_final" value="Imprimir" class="table__button button button--white">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="noBetweenLinkedAccounts_form" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__content">
                <span id="noInfo_BetweenLinkedAccounts_form"></span>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<div class="notification-modal" id="notification-modal-bta">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png"
                                              alt=""/>
        <span id="transferencias_TAGAvisosImportantes_1" class="notification-modal__title">IMPORTANT NOTICE</span></div>
    <div class="notification-modal__content">
        <div id="transferencias_TAGMsgDisclaimeEntrebancos_1" class="notification-modal__descriptions">
        </div>
        <div id="transferencias_TAGMsgDisclaimerExternas_1_Step_2" class="notification-modal__descriptions">
        </div>
        <div id="transferencias_TAGMsgDisclaimerExternas_1_Step_3" class="notification-modal__descriptions" style="display: none;">
        </div>
        <div class="notification-modal__greeting">
            <span>Sincerely Yours</span>
            <span class="notification-modal__vbt">VBT Bank & Trust, Ltd.</span>
        </div>
        <div class="notification-modal__spacebetween notification-modal__spacebetween--modifier">
            <img class="notification-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
            <div>
                <a id="btn_TOB_volver_bt_2" class="notification-modal__button button-alternative" onclick="backStep2()" style="display: none;">Volver</a>
                <a id="TagBtnCloseBMLA" class="notification-modal__button button-alternative" href="#close" rel="modal:close">Cerrar</a>
            </div>
        </div>
    </div>
</div>
<div class="notification-modal" id="disclaimer-modal-transfers-others-client_v2">

    <div class="notification-modal__content">
        <div id="transferencias_TAGMsgDisclaimeOtrosClientes_1_disclaimer_v2" class="notification-modal__descriptions">
        </div>
        <div class="notification-modal__greeting">
            <span>Sincerely Yours</span>
            <span class="notification-modal__vbt">VBT Bank & Trust, Ltd.</span>
        </div>
        <div class="notification-modal__spacebetween notification-modal__spacebetween--modifier">
            <img class="notification-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
            <div>
                <a id="btn_TOB_volver_bt_2_4_cancelar_v2" class="notification-modal__button button-alternative">Cancelar</a>
                <a id="btn_TOB_volver_bt_2_4_continuar_v2" class="notification-modal__button button-alternative">Continuar</a>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="../../footer.jsp" />--%>
<script>
    $("#btn-notification-modal-bla").click(function (){

        $("#notification-modal-bta").modal({
            showClose: !1,
            modalClass: "notification-modal",
            fadeDuration: 100,
            blockerClass: "notification-modal--blocker",
        });

        let tipoOption = $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3");
        console.log('tipoOption',tipoOption);
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_3').hide();

        if(tipoOption == 'Venecredit Balanced Opportunity Fund'){
            $('#transferencias_TAGMsgDisclaimeEntrebancos_1').hide();
            $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2').show();
        }else{
            $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2').hide();
            $('#transferencias_TAGMsgDisclaimeEntrebancos_1').show();
        }
    });

    function goLink(){
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2').hide();
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_3').show();
        $('#btn_TOB_volver_bt_2').show();
    }

    function backStep2(){
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_3').hide();
        $('#btn_TOB_volver_bt_2').hide();
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2').show();
    }

    
</script>
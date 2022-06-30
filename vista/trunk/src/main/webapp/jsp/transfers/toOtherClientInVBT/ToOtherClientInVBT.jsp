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
    <h1 id="transferencias_TAGTercerosVenecredit">Transferencias / A Terceros en VBT </h1>
</div>
<div id="ToOtherClient_form">
    <fieldset id="DIV_INFO_TERCEROS_FI" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAG_INFO_TERCEROS_FI" class="datosInfo">This option allows you to make transfers to another client(s) account(s) with VBT Bank & Trust.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset id="DIV_INFO_TERCEROS_FC" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAG_INFO_TERCEROS_FC" class="datosInfo">This option allows you to make transfers to another client(s) account(s) with VBT Bank & Trust.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <div class="clear"></div>
<fieldset id="ToOtherClient_form_1" class="formulario_fieldset div_consulta">
    <legend id="transferencias_TAGNumeroCuentaDebitoLegend" >N&uacute;mero de Cuenta Origen</legend>
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
            <td width="60%">
                <select id="ToOtherClient_Accounts" title="Cuenta Origen" class="obligatorioTOC selectFormulario_TOCVBT" style="width: 97%;"></select>
                <label class="datos6">  * </label>
            </td>
            <td id="cancelarProductoMostrar">
                <label id="TAGproductoCancelacionTOC" class="datos6"> Product Cancelation</label><span class="datos6">:</span>
                <input type="checkbox" id="productCancelationOtrosClientesVBT" class="checkboxFormulario_TOCVBT" >
            </td>
            <td  class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a id="otherClientinVBT_imprimir" HREF="javascript:printOtherClientinVBT()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
            <td class="datos2 ">
                <label id="transferencias_TAGFechaCierre3">Available balance as at </label>
                <b><label id="ToOtherClient_tagAvailableBalanceDate"> </label></b>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="ToOtherClient_form_2" class="formulario_fieldset div_consulta" >

    <legend id="transferencias_TAGDatosTransferencia2">Datos de la Transferencia</legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr id="productosMostrar">
            <td width="30%">
                <label for="ToOtherClient_Producto" id="TOCTAGNumeroCuentaCredito"  class="datos">To Product</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="ToOtherClient_Producto" title="Producto Destino" class="productoTOC selectFormulario_TOCVBT obligatorioCambioTOC obligatorioTOC"></select>
                <label class="invisible_print datos6">  * </label>
            </td>
        </tr>
        <tr  id="numCuentaDestinoMostrar">
            <td width="30%">
                <label for="ToOtherClient_NumCuentaDestino" id="transferencias_TAGNumeroCuentaCredito4"  class="datos">Numero de Cuenta Destino</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_NumCuentaDestino" title="Numero de Cuenta Destino" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);" onblur="this.value=lpad(this.value,'0',10);validarCuentaOrigen();" maxlength="10" size="11"/>
                <span id="ToOtherClient_NumCuentaDestino_print" class="datos6 visible_print"></span><label class="datos6 invisible_print">* </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ToOtherClient_NumCarteraDestino" id="transferencias_TAGCarteraDestino"  class="datos">Numero de Cartera Destino</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_NumCarteraDestino" title="Número de Cartera Destino" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT obligatorioCambioTOC" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);" onblur="/*this.value=lpad2(this.value,'0',10);*/;validarCartera();" maxlength="10" size="11" />
                <span id="ToOtherClient_NumCarteraDestino_print" class="datos6 visible_print"></span><label class="datos6 invisible_print">* </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ToOtherClient_NombreBeneficiario"  id="transferencias_TAGNombreBeneficiario" class="datos">Nombre del Beneficiario  </label> <span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_NombreBeneficiario" style="width:250px" title="Nombre Beneficiario" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT obligatorioCambioTOC" maxlength="60" size="35" />
                <span id="ToOtherClient_NombreBeneficiario_print" class=" datos6 visible_print"></span><label class="invisible_print datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ToOtherClient_beneficiaryEmail" id="transferencias_TAGEmailBeneficiario3" class="datos">Email del Beneficiario</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_beneficiaryEmail" style="width:250px" title="Email Beneficiario" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT obligatorioCambioTOC" maxlength="55" size="35" />
                <span id="ToOtherClient_beneficiaryEmail_print" class=" datos6 visible_print"></span><label class="invisible_print datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%" >

            </td>
            <td width="70%">
                <table>
                    <td>
                        <div id="unidadesMostrarOtrosClientes">
                            <label id="TAGUnidades" class="datos6 " > Unidades</label><span class="datos6">:</span>
                            <input type="checkbox" id="transferirUnidadesOtrosClientes" class="inputFormulario_TOCVBT " >
                        </div>
                    </td>
                    <td>
                        <div id="montoMostrarOtrosClientes">
                            <label id="TAGmontoTOC" class="datos6"> Monto</label><span class="datos6">:</span>
                            <input type="checkbox" id="transferirMontoOtrosClientes" class="inputFormulario_TOCVBT">
                        </div>
                    </td>
                </table>
        </tr>
        <tr  id="montoInputMostrar">
            <td width="30%">
                <label for="ToOtherClient_Monto" id="transferencias_TAGMonto_1"  class="datos negrita">Monto</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_Monto" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT" title="Monto" maxlength="15" size="15" onblur="this.value=formatCurrency(this.value,true,2,'.');" onkeydown="habilitarPanel_con_boton('form_6','form_7');" onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'OC');" />
                <span id="ToOtherClient_Monto_print" class=" datos6 visible_print"></span><label class="datos" id="monedaTOC">   </label><label class="datos6 invisible_print">*</label>
            </td>
        </tr>
        <tr  id="unidadesInputOculto">
            <td width="30%">
                <label for="ToOtherClient_Unidades" id="TAGUnidadesProductoTOC"  class="datos negrita">Units</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_Unidades" type="text" class="invisible_print inputFormulario_TOCVBT " title="Unidades" maxlength="25" size="15" />
                <label class="datos6 invisible_print">*</label>
                <label id="cantidadUnidadesOtrosClientes" class="datos negrita">unidades mostradas</label>

             </td>
        </tr>
        <tr id="montoComisionMostrarTOC" >
            <td width="30%">
                <label id="transferencias_TAGmontoComision" class="datos"> Comision</label><span class="datos6">: </span>
            </td>
            <td width="70%">
                <label id="montoComisionTOC" class="datos negrita"></label>
            </td>
        </tr>
        <tr id="descripcionTransferencia">
            <td width="30%">
                <label for="ToOtherClient_Concepto" id="transferencias_TAGConcepto4"  class="datos">Concepto</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_Concepto" style="width:250px" type="text" title="Concepto" class="invisible_print inputFormulario_TOCVBT" maxlength="40" size="40" />
                <span id="ToOtherClient_Concepto_print" class=" datos6 visible_print"></span>
            </td>
        </tr>
        <tr id="motivoCancelacionProducto" >
            <td width="30%">
                <label for="ToOtherClient_Motivo" id="transferencias_TAGMotivoCancel" class="datos">Motivo</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_Motivo" style="width:250px" type="text" title="Concepto" class="invisible_print inputFormulario_TOCVBT obligatorioCancelacion" maxlength="40" size="40" />
                <span id="ToOtherClient_Motivo_print" class=" datos6 visible_print"></span><label class="datos6 invisible_print">* </label>
            </td>
        </tr>

        <tr>
            <td width="100%" colspan="2">
                <label id="mandatoryField_TOCVBT" class="datosCampoObligatorio"> * Campos Obligatorios</label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="ToOtherClient" class="formulario_fieldset div_consulta right">
    <input  type="button" id="ToOtherClient_btn_cancelar" value="Cancelar" class="boton">
    <input  type="button" id="ToOtherClient_btn_aceptar" value="Aceptar" class="boton">
</fieldset>
<fieldset id="ToOtherClientAviso" class="formulario_fieldset div_consulta">
    <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
        <tr>
            <td height="50" >
                <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="100%" height="35" class="V8NBNegroGris" align="center">
                            <b><span id="transferencias_TAGAvisosImportantes_4" class="datos">IMPORTANT NOTICE</span></b>
                        </td>
                    </tr>
                    <tr>
                        <td width="100%" class="V8NBNegroGris" align="left">
                            <span id="transferencias_TAGMsgDisclaimerExternas_4" class="datos">
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
    <div id="TOCdisclaimer"  style="display:none">
        <p align="justify"></p>
    </div>
    <div id="TOCdisclaimerHora"  style="display:none">
        <p align="justify"></p>
    </div>
</div>
<div id="noToOtherClient_form" style="display: none">
    <fieldset class="div_consulta">
        <span id="noInfo_noToOtherClient_form" class="datos"></span>
    </fieldset>
</div>
<div id="summaryToOtherClient" style="display: none" >
<div id="tituloResumen_TOC" class="titulo_resume" >
    <span id="summary_TOB3">Resumen</span>
    <div class="descripcion_resume">
        <span id="sumaryConfirm_TOB3">Confirme los Datos de su Transferencia<br></span>
    </div>
</div>

<div class="botones_resume2">

<table SUMMARY='tabla'cellpadding="4" cellspacing="1" class="tabla_resumen">
<tbody>
<tr id="div_numref_TOC" style="display: none">
    <td class="titulo_resumen right" colspan="2">
        <b><span id="transferencias_TAGNumeroRecibo">  Referencia Nro.&nbsp; </span> <span id="numRef_TOC"></span> </b>
    </td>
</tr>
<!--<tr id="tipoProductoResumenMostraO" >
    <td class="titulo_resumen">
        <span id="transferencias_TAGproducto4">Product</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_Producto"></span>
        <input type="hidden" id="ProductoTransferTOC">
    </td>
</tr>-->
<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGNumeroCuentaDebitoToOtherClients">Producto Origen</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_Accounts" class="datos_resumen"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGNumeroCuentaCredito5">Producto Destino</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_NumCuentaDestino"></span>
    </td>
</tr>

<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGNombreBeneficiarioToOtherClients">Nombre del Beneficiario</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_NombreBeneficiario"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGEmailBeneficiario2">Email del Beneficiario</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_beneficiaryEmail"></span>
    </td>
</tr>
<tr id="montoResumenMostraO">
    <td class="titulo_resumen">
       <span id="transferencias_TAGMonto4">Monto</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_Monto"></span>
        <input type="hidden" id="monedaTransferTOC">
    </td>
</tr>

<tr id="unidadesResumenMostraO" >
    <td class="titulo_resumen">
        <span id="transferencias_TAGUnidades4">Units</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_Unidades"></span>
        <input type="hidden" id="UnidadesTransferTOC">
    </td>
</tr>

<tr id="cancelacionMostrarR" >
    <td class="titulo_resumen">
        <span id="transferencias_TAGcancelacionMostrarR">Cancelación de Producto</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_cancelacionMostrarR"></span>
        <input type="hidden" id="cancelacionMostrarRTOC">
    </td>
</tr>

<tr id="div_conceptp_TOC" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGConcepto5">Concepto</span>
        <span id="transferencias_TAGo">o</span>
        <span id="transferencias_TAGMotivo2">Motivo</span>
    </td>

    <td class="datos_resumen">
        <span id="RToOtherClient_Concepto"></span>
    </td>
</tr>
<tr id="div_estatus_TOC" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGEstatusTransferencia">Estatus de la Transferencia</span>
    </td>
    <td class="datos_resumen">
        <span id="status_TOC"></span>
    </td>
</tr>

<tr id="resumenBotones_TOC" style="display: block">
    <td class="datos" colspan="2">
        <input type="button" id="btn_resumen_cancelar_TOC" value="Volver" class="boton">
        <input type="button" id="btn_resumen_aceptar_TOC" value="Aceptar" class="boton">
    </td>
</tr>
<tr id="resumenBotones_TOC_Finales" style="display: none" >
    <td class="datos center" colspan="2">
        <input type="button" id="btn_finalizar_TOC_final" value="Hacer Otra" class="botonGrande">
        <input type="button" id="btn_home_TOC_final" value="Aceptar" class="boton">
        <input type="button" id="btn_imprimir_TOC_final" value="Imprimir" class="boton">
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
            <h2 id="transferencias_TAGTercerosVenecredit" class="banner__title banner__title--modifier">To other clients in VBT</h2>
            <ul class="banner__nav">
                <li><a id="TagHome6" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="TagTransfer6">TRANSFERS</li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="TOCBRAN">TO OTHER CLIENTS IN VBT</li>
            </ul>
            <p id="TAG_INFO_TERCEROS_FI" class="banner__description banner__description--modifier">This option allows you to make transfers to another
                client(s) account(s) with VBT Bank & Trust.</p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="transferencias_TAGTercerosVenecredit" class="banner__title banner__title--modifier">To other clients in VBT</h2>
            <ul class="banner__nav">
                <li><a id="TagHome6" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagTransfer6">TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TOCBRAN">TO OTHER CLIENTS IN VBT</li>
            </ul>
            <p id="TAG_INFO_TERCEROS_FI" class="banner__description banner__description--modifier">This option allows you to make transfers to another
                client(s) account(s) with VBT Bank & Trust.</p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="section" id="ToOtherClient_form">
        <div class="section__container container">
            <div id="ToOtherClient_form_1" class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                <div class="section__header">
                    <span id="transferencias_TAGNumeroCuentaDebitoLegend" class="section__title">From Account</span>
                    <div class="section__info">
                        <p class="balance-date">
                            <span id="transferencias_TAGFechaCierre3"></span>
                            <span id="ToOtherClient_tagAvailableBalanceDate"></span>
                        </p>
                        <img id="otherClientinVBT_imprimir" href="javascript:printOtherClientinVBT()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
                <div class="section__row">
                    <div class="section__form">
                        <div class="section__field">
                            <div class="select-section select-section--form">
                                <select id="ToOtherClient_Accounts"
                                        title="From Account"
                                        class="select-section__select select-section__select--form obligatorioTOC selectFormulario_TOCVBT field-obligatory">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="cancelarProductoMostrar" class="section__checkbox checkbox-container">
                            <label id="TAGproductoCancelacionTOC" class="checkbox-container__label" for="productCancelationOtrosClientesVBT">Product Cancelation</label>
                            <input id="productCancelationOtrosClientesVBT" class="checkbox-container__check checkboxFormulario_TOCVBT" type="checkbox" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table__titles">
                    <span id="transferencias_TAGDatosTransferencia2" class="table__title">TRANSFER DETAILS</span>
                    <div id="btn-notification-modal-TOC" class="notice">
                        <img class="notice__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png" />
                        <span id="transferencias_TAGNotice" class="notice__text">NOTICE</span>
                    </div>
                </div>
                <div id="ToOtherClient_form_2" class="form-filter mt_2">
                    <div class="form-filter__grid form-filter__grid--col2">
                        <div id="productosMostrar" class="form-filter__item">
                            <label id="TOCTAGNumeroCuentaCredito" class="form-filter__label field-obligatory" for="ToOtherClient_Producto">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="ToOtherClient_Producto"
                                        title="Producto Destino"
                                        class="select-section__select select-section__select--form productoTOC selectFormulario_TOCVBT obligatorioCambioTOC obligatorioTOC">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="numCuentaDestinoMostrar" class="form-filter__item">
                            <label id="transferencias_TAGCarteraDestino" class="form-filter__label field-obligatory" for="ToOtherClient_NumCuentaDestino">Label</label>
                            <input id="ToOtherClient_NumCuentaDestino"
                                   title="To Account"
                                   class="form-filter__input input input--form invisible_print obligatorioTOC inputFormulario_TOCVBT"
                                   onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                   onblur="this.value=lpad(this.value,'0',10);validarCuentaOrigen();"
                                   maxlength="10"
                                   size="11"
                                   type="text"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="transferencias_TAGCarteraDestino2" class="form-filter__label field-obligatory" for="ToOtherClient_NumCarteraDestino">Label</label>
                            <input id="ToOtherClient_NumCarteraDestino"
                                   title="Número de Cartera Destino"
                                   class="form-filter__input input input--form invisible_print obligatorioTOC inputFormulario_TOCVBT obligatorioCambioTOC"
                                   onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                   onblur="/*this.value=lpad2(this.value,'0',10);*/;validarCartera();"
                                   maxlength="10"
                                   size="11"
                                   type="text"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="transferencias_TAGNombreBeneficiario" class="form-filter__label" for="ToOtherClient_NombreBeneficiario">Label</label>
                            <input id="ToOtherClient_NombreBeneficiario"
                                   title="Beneficiary"
                                   class="form-filter__input input input--form invisible_print obligatorioTOC inputFormulario_TOCVBT obligatorioCambioTOC"
                                   maxlength="60"
                                   size="35"
                                   type="text"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="transferencias_TAGEmailBeneficiario3" class="form-filter__label field-obligatory" for="ToOtherClient_beneficiaryEmail">Label</label>
                            <input id="ToOtherClient_beneficiaryEmail"
                                   title="Beneficiary Email"
                                   class="form-filter__input input input--form invisible_print obligatorioTOC inputFormulario_TOCVBT obligatorioCambioTOC"
                                   maxlength="55"
                                   size="35"
                                   type="text"/>
                        </div>
                        <div id="checksMostrarOtrosClientes" class="form-between__item form-between__item--checks">
                            <div id="unidadesMostrarOtrosClientes" class="checkbox-container">
                                <label id="TAGUnidades" class="checkbox-container__label" for="transferirUnidadesOtrosClientes">Unidades</label>
                                <input type="checkbox" id="transferirUnidadesOtrosClientes" class="checkbox-container__check inputFormulario_TOCVBT" />
                            </div>
                            <div id="montoMostrarOtrosClientes" class="checkbox-container">
                                <label id="TAGmontoTOC" class="checkbox-container__label" for="transferirMontoOtrosClientes">Monto</label>
                                <input type="checkbox" id="transferirMontoOtrosClientes" class="checkbox-container__check inputFormulario_TOCVBT" />
                            </div>
                        </div>
                        <div id="montoInputMostrar" class="form-filter__item">
                            <label id="transferencias_TAGMonto_1" class="form-filter__label field-obligatory" for="ToOtherClient_Monto">Label</label>
                            <div class="form-filter__flex">
                                <input id="ToOtherClient_Monto"
                                       title="Amount"
                                       class="form-filter__input input input--form invisible_print obligatorioTOC inputFormulario_TOCVBT"
                                       maxlength="15"
                                       size="15"
                                       onblur="this.value=formatCurrency(this.value,true,2,'.');"
                                       onkeydown="habilitarPanel_con_boton('form_6','form_7');"
                                       onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'OC');"
                                       type="text"/>
                                <div id="monedaTOC"></div>
                            </div>
                        </div>
                        <div id="unidadesInputOculto" class="form-filter__item">
                            <label id="TAGUnidadesProductoTOC" class="form-filter__label" for="ToOtherClient_Unidades">Unidades</label>
                            <input id="ToOtherClient_Unidades"
                                   title="Unidades"
                                   class="form-filter__input input input--form invisible_print inputFormulario_TOCVBT"
                                   maxlength="25"
                                   size="15"
                                   type="text"/>
                                   <label id="cantidadUnidadesOtrosClientes" class="datos negrita">unidades mostradas</label>
                        </div>
                        <div id="montoComisionMostrarTOC" class="form-filter__item">
                            <label id="transferencias_TAGmontoComision" class="form-filter__label">Comision</label>
                            <span id="montoComisionTOC"></span>
                        </div>
                        <div id="descripcionTransferencia" class="form-filter__item">
                            <label id="transferencias_TAGConcepto4" class="form-filter__label" for="ToOtherClient_Concepto">Concepto</label>
                            <input id="ToOtherClient_Concepto"
                                   title="Description"
                                   class="form-filter__input input input--form invisible_print inputFormulario_TOCVBT"
                                   maxlength="40"
                                   size="40"
                                   type="text"/>
                        </div>
                        <div id="motivoCancelacionProducto" class="form-filter__item">
                            <label id="transferencias_TOC_TAGMotivoCancel" class="form-filter__label" for="ToOtherClient_Motivo">Motivo</label>
                            <input id="ToOtherClient_Motivo"
                                   title="Concepto"
                                   class="form-filter__input input input--form invisible_print inputFormulario_TOCVBT obligatorioCancelacion"
                                   maxlength="40"
                                   size="40"
                                   type="text"/>
                        </div>
                    </div>
                    <div class="form-filter__between">
                        <span id="mandatoryField_TOCVBT" class="mandatory-fields">* Mandatory fields</span>
                        <div class="form-filter__buttons">
                            <input type="button" id="ToOtherClient_btn_cancelar" value="Cancel" class="button button--white">
                            <input type="button" id="ToOtherClient_btn_aceptar" value="Accept" class="button">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="summaryToOtherClient" style="display: none" class="section">
        <div class="section__container container">
            <div id="tituloResumen_TOC" class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                <div class="section__header">
                    <span id="summary_TOB3" class="section__title">Summary</span>
                    <div class="section__info">
                        <span id="sumaryConfirm_TOB3">Confirme los Datos de su Transferencia </span>
                        <img class="section__icon" src="../vbtonline/resources/img/icons/ic_table_transfer_header_info.png" alt="" />
                    </div>
                </div>
            </div>
            <div id="summaryToOtherClientForm" class="section__content">
                <div class="table">
                    <table class="table__content table__content--summary">
                        <tr id="div_numref_TOC"  style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroRecibo" class="table__item table__item--summary"><span>Referencia Nro.</span></td>
                            <td class="table__item table__item--summary" style="font-weight: bold"><span id="numRef_TOC"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroCuentaDebitoToOtherClients" class="table__item table__item--summary"><span>Producto Origen</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_Accounts"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroCuentaCredito5"class="table__item table__item--summary"><span>Producto Destino</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_NumCuentaDestino"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGNombreBeneficiarioToOtherClients" class="table__item table__item--summary"><span>Nombre del Beneficiario</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_NombreBeneficiario"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailBeneficiario2" class="table__item table__item--summary"><span>Email del Beneficiario</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_beneficiaryEmail"></span></td>
                        </tr>
                        <tr id="montoResumenMostraO" class="table__row table__row--summary">
                            <td id="transferencias_TAGMonto4" class="table__item table__item--summary"><span>Monto</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_Monto"></span></td>
                            <input type="hidden" id="monedaTransferTOC" />
                        </tr>
                        <tr id="unidadesResumenMostraO" class="table__row table__row--summary">
                            <td id="transferencias_TAGUnidades4" class="table__item table__item--summary"><span>Units</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_Unidades"></span></td>
                            <input type="hidden" id="UnidadesTransferTOC" />
                        </tr>
                        <tr id="cancelacionMostrarR" class="table__row table__row--summary">
                            <td id="transferencias_TAGcancelacionMostrarR" class="table__item table__item--summary"><span>Cancelación de Producto</span></td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_cancelacionMostrarR"></span></td>
                            <input type="hidden" id="cancelacionMostrarRTOC" />
                        </tr>
                        <tr id="div_conceptp_TOC" style="display: none" class="table__row table__row--summary">
                            <td class="table__item table__item--summary">
                                <span id="transferencias_TAGConcepto5">Concepto</span>
                            </td>
                            <td class="table__item table__item--summary"><span id="RToOtherClient_Concepto"></span></td>
                        </tr>
                        <tr id="div_estatus_TOC" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEstatusTransferencia" class="table__item table__item--summary"><span>Estatus de la Transferencia</span></td>
                            <td class="table__item table__item--summary"><span id="status_TOC"></span></td>
                        </tr>
                    </table>
                    <div id="resumenBotones_TOC" style="display: block" class="table__spacebetween table__spacebetween--top">
                        <div></div>
                        <div class="table__buttons">
                            <input type="button" id="btn_resumen_cancelar_TOC" value="Back" class="table__button button button--white">
                            <input type="button" id="btn_resumen_aceptar_TOC" value="Aceptar" class="table__button button">
                        </div>
                    </div>
                    <div id="resumenBotones_TOC_Finales" style="display: none" class="table__spacebetween table__spacebetween--top">
                        <div></div>
                        <div class="table__buttons">
                            <input type="button" id="btn_finalizar_TOC_final" value="Hacer Otra" class="table__button button button--white">
                            <input type="button" id="btn_home_TOC_final" value="Aceptar" class="table__button button">
                            <input type="button" id="btn_imprimir_TOC_final" value="Imprimir" class="table__button button button--white">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div id="noToOtherClient_form" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__content">
                <span id="noInfo_noToOtherClient_form"></span>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>
<div class="notification-modal" id="notification-modal-transfers-others-client">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png"
             alt=""/>
        <span id="transferencias_TAGAvisosImportantes_4" class="notification-modal__title">IMPORTANT NOTICE</span></div>
    <div class="notification-modal__content">
        <div id="transferencias_TAGMsgDisclaimeOtrosClientes_1" class="notification-modal__descriptions">
        </div>
        <div id="transferencias_TAGMsgDisclaimerExternas_1_Step_2_4" class="notification-modal__descriptions">
        </div>
        <div id="transferencias_TAGMsgDisclaimerExternas_1_Step_3_4" class="notification-modal__descriptions" style="display: none;">
        </div>
        <div class="notification-modal__greeting">
            <span>Sincerely Yours</span>
            <span class="notification-modal__vbt">VBT Bank & Trust, Ltd.</span>
        </div>
        <div class="notification-modal__spacebetween notification-modal__spacebetween--modifier">
            <img class="notification-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
            <div>
                <a id="btn_TOB_volver_bt_2_4" class="notification-modal__button button-alternative" onclick="backStep24()" style="display: none;">Volver</a>
                <a id="TagBtnCloseTOCV" class="notification-modal__button button-alternative" href="#close" rel="modal:close">Cerrar</a>
            </div>
        </div>
    </div>
</div>
<div class="notification-modal" id="disclaimer-modal-transfers-others-client">

    <div class="notification-modal__content">
        <div id="transferencias_TAGMsgDisclaimeOtrosClientes_1_disclaimer" class="notification-modal__descriptions">
        </div>
        <div class="notification-modal__greeting">
            <span>Sincerely Yours</span>
            <span class="notification-modal__vbt">VBT Bank & Trust, Ltd.</span>
        </div>
        <div class="notification-modal__spacebetween notification-modal__spacebetween--modifier">
            <img class="notification-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
            <div>
                <a id="btn_TOB_volver_bt_2_4_cancelar" class="notification-modal__button button-alternative">Cancelar</a>
                <a id="btn_TOB_volver_bt_2_4_continuar" class="notification-modal__button button-alternative">Continuar</a>
            </div>
        </div>
    </div>
</div>
<div id="TOCdisclaimer"  style="display:none">
    <p align="justify"></p>
</div>
<div id="TOCdisclaimerHora"  style="display:none">
    <p align="justify"></p>
</div>
<script>

    $("#btn-notification-modal-TOC").click(function (){
        $("#notification-modal-transfers-others-client").modal({
        showClose: !1,
        modalClass: "notification-modal",
        fadeDuration: 100,
        blockerClass: "notification-modal--blocker",
    });

    let tipoOption = $("#ToOtherClient_Accounts option:selected").attr("extra3");
    console.log('tipoOption',tipoOption);
    $('#transferencias_TAGMsgDisclaimerExternas_1_Step_3_4').hide();

    if(tipoOption == 'Venecredit Balanced Opportunity Fund'){
        $('#transferencias_TAGMsgDisclaimeOtrosClientes_1').hide();
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2_4').show();
    }else{
        $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2_4').hide();
        $('#transferencias_TAGMsgDisclaimeOtrosClientes_1').show();
    }
    });

    function goLink4(){
    $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2_4').hide();
    $('#transferencias_TAGMsgDisclaimerExternas_1_Step_3_4').show();
    $('#btn_TOB_volver_bt_2_4').show();
    }

    function backStep24(){
    $('#transferencias_TAGMsgDisclaimerExternas_1_Step_3_4').hide();
    $('#btn_TOB_volver_bt_2_4').hide();
    $('#transferencias_TAGMsgDisclaimerExternas_1_Step_2_4').show();
    }

</script>


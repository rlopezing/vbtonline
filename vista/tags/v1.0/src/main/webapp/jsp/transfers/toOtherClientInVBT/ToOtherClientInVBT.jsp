<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 23/07/2012
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="transferencias_TAGTercerosVenecredit">Transferencias / A Terceros en VBT </h1>
</div>
<div id="ToOtherClient_form">
<fieldset id="ToOtherClient_form_1" class="formulario_fieldset div_consulta">

    <legend id="transferencias_TAGNumeroCuentaDebitoLegend" >N&uacute;mero de Cuenta Origen</legend>
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
            <td width="77%">
                <select id="ToOtherClient_Accounts" title="Cuenta Origen" class="obligatorioTOC selectFormulario_TOCVBT">

                </select>
                <label class="datos6">  * </label>
            </td>

            <td  class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
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
        <tr>
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
                <label for="ToOtherClient_NombreBeneficiario"  id="transferencias_TAGNombreBeneficiario" class="datos">Nombre del Beneficiario  </label> <span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_NombreBeneficiario" style="width:250px" title="Nombre Beneficiario" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT" maxlength="60" size="35"/>
                <span id="ToOtherClient_NombreBeneficiario_print" class=" datos6 visible_print"></span><label class="invisible_print datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ToOtherClient_beneficiaryEmail" id="transferencias_TAGEmailBeneficiario3" class="datos">Email del Beneficiario</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_beneficiaryEmail" style="width:250px" title="Email Beneficiario" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT" maxlength="55" size="35"/>
                <span id="ToOtherClient_beneficiaryEmail_print" class=" datos6 visible_print"></span><label class="invisible_print datos6">  * </label>
            </td>
        </tr>

        <tr>
            <td width="30%">
                <label for="ToOtherClient_Monto" id="transferencias_TAGMonto_1"  class="datos">Monto</label><span>:</span>
            </td>
            <td width="70%">
                <%--<input id="ToOtherClient_Monto" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT" title="Monto" maxlength="15" size="15" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeydown="habilitarPanel_con_boton('form_6','form_7');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>--%>
                    <input id="ToOtherClient_Monto" type="text" class="invisible_print obligatorioTOC inputFormulario_TOCVBT" title="Monto" maxlength="15" size="15" onblur="this.value=formatCurrency(this.value,true,2,'.');" onkeydown="habilitarPanel_con_boton('form_6','form_7');" onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'OC');"/>
                    <span id="ToOtherClient_Monto_print" class=" datos6 visible_print"></span><label class="datos" id="monedaTOC">   </label><label class="datos6 invisible_print">*</label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ToOtherClient_Concepto" id="transferencias_TAGConcepto4"  class="datos">Concepto</label><span>:</span>
            </td>
            <td width="70%">
                <input id="ToOtherClient_Concepto" style="width:250px" type="text" title="Concepto" class="invisible_print inputFormulario_TOCVBT" maxlength="40" size="40"/>
                <span id="ToOtherClient_Concepto_print" class=" datos6 visible_print"></span>

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
                                2. Transactions placed after 12:30 pm (UTC/GMT -5) will be processed on the next business day.<br><br>
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
    <%--<div id="tituloExitoso_TOC" class="titulo_resume" style="display: none">--%>
        <%--<span id="successfull_TOB">Transferencia Exitosa</span>--%>
    <%--</div>--%>

<div class="botones_resume2">

<table SUMMARY='tabla'cellpadding="4" cellspacing="1" class="tabla_resumen">
<tbody>
<tr id="div_numref_TOC" style="display: none">
    <td class="titulo_resumen right" colspan="2">
        <b><span id="transferencias_TAGNumeroRecibo">  Referencia Nro.&nbsp; </span> <span id="numRef_TOC"></span> </b>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGNumeroCuentaDebito2">N&uacute;mero Cuenta Origen</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_Accounts" class="datos_resumen"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGNumeroCuentaCredito5">N&uacute;mero Cuenta Destino</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_NumCuentaDestino"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGNombreBeneficiario2">Nombre del Beneficiario</span>
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
<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGMonto4">Monto</span>
    </td>
    <td class="datos_resumen">
        <span id="RToOtherClient_Monto"></span>
    </td>
</tr>
<tr id="div_conceptp_TOC" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGConcepto5">Concepto</span>
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

</div>


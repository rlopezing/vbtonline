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
    <h1 id="transferencias_TAGEntreMisCuentas">Transferencias / Entre mis Cuentas </h1>
</div>
<div id="BetweenLinkedAccounts_form" >
    <fieldset id="BetweenLinkedAccounts_form_1" class="formulario_fieldset div_consulta">

        <legend id="transferencias_TAGNumeroCuentaDebitoLegend2" >N&uacute;mero de Cuenta Origen</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="77%">
                    <select id="BetweenLinkedAccounts_Accounts" title="Cuenta Origen" class="obligatorioBMLA selectFormulario_BMLA">

                    </select>
                    <label class="datos6">  * </label>
                </td>


                <td  class="botones_formulario invisible_print">
                    <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                    <a id="between_imprimir" onclick="printTransfers()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
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
                    <select id="BetweenLinkedAccounts_NumCuentaDestino" title="Número de Cuenta Destino" class="obligatorioBMLA selectFormulario_BMLA">

                    </select>
                    <label class="datos6">  * </label>
                </td>
            </tr>
            <tr>
                <td width="30%">
                    <label for="BetweenLinkedAccounts_Monto" id="transferencias_TAGMonto"  class="datos">Monto</label><span class="datos">:</span>
                </td>
                <td width="70%">
                    <span id="BetweenLinkedAccounts_Monto_print" class="datos visible_print"></span>
                    <%--<input id="BetweenLinkedAccounts_Monto" type="text" maxlength="15" size="15" class="invisible_print obligatorioBMLA inputFormulario_BMLA" title="Monto" onblur="this.value=formatCurrency(this.value,true,2,',');"  onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/> <label class="datos" id="monedaBMLA">   </label><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>--%>
                    <input id="BetweenLinkedAccounts_Monto" type="text" maxlength="15" size="15" class="invisible_print obligatorioBMLA inputFormulario_BMLA" title="Monto" onblur="this.value=formatCurrency(this.value,true,2,'.');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'EM');"/> <label class="datos" id="monedaBMLA">   </label><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>


                </td>
            </tr>
            <tr>
                <td width="30%">
                    <label for="BetweenLinkedAccounts_Concepto" id="transferencias_TAGConcepto3"  class="datos">Concepto</label><span class="datos">:</span>
                </td>
                <td width="70%">
                    <span id="BetweenLinkedAccounts_Concepto_print" class="datos visible_print"></span>
                    <input id="BetweenLinkedAccounts_Concepto" style="width:250px" type="text" title="Concepto" class="invisible_print inputFormulario_BMLA" maxlength="40" size="40"/>
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
        <%--<label class="datos" id="transferencias_TAGAvisosImportantes">--%>
            <%--AVISOS IMPORTANTES <br><br>--%>

            <%--Agradecemos lea antes de confirmar su operaci&oacute;n.<br><br> 1. La fecha valor de su operaci&oacute;n ser&aacute; el mismo d&iacute;a, de realizarse en fecha h&aacute;bil bancaria, dentro del horario estipulado. De efectuar la operaci&oacute;n en d&iacute;a feriado bancario o fin de semana, la fecha valor de su transferencia ser&aacute; el primer d&iacute;a h&aacute;bil bancario siguiente que corresponda.<br><br> 2. Las transacciones realizadas despu&eacute;s de la 12:30 pm (UTC/GMT -5) ser&aacute;n procesadas el d&iacute;a h&aacute;bil bancario siguiente.<br><br> 3. Para conocer el costo de sus operaciones en el VB&T Online, por favor referirse al Schedule of Fees ubicado en <A HREF="http://www.vbtbank.com">www.vbtbank.com</a><br><br> 4. Usted recibir&aacute; en su email registrado la notificaci&oacute;n de sus transacciones.--%>
        <%--</label>--%>
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
                            <span id="transferencias_TAGMsgDisclaimerExternas_1" class="datos">
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
<div id="noBetweenLinkedAccounts_form" style="display: none">
    <fieldset class="div_consulta">
        <span id="noInfo_BetweenLinkedAccounts_form" class="datos"></span>
    </fieldset>
</div>
<div id="summaryBetweenLinkedAccounts" style="display: none" >
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
                   <span id="transferencias_TAGNumeroCuentaDebito2">N&uacute;mero Cuenta Origen</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_Accounts" class="datos_resumen"></span>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                   <span id="transferencias_TAGNumeroCuentaCredito2">N&uacute;mero Cuenta Destino</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_NumCuentaDestino"></span>
                </td>
            </tr>
            <tr>
                <td class="titulo_resumen">
                    <span id="transferencias_TAGMonto3">Monto</span>
                </td>
                <td class="datos_resumen">
                    <span id="RBetweenLinkedAccounts_Monto"></span>
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

</div>
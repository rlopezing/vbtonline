<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 23/07/2012
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->


<%--<script type="text/javascript" src="../vbtonline/resources/js/transfers/transfers.js?Math.random()"></script>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div>
    <h1 id="transferencias_TAGTituloOpe">Transfers / To Other Banks </h1>
</div>
<div id="createToOtherBank">

<fieldset id="infoTransfer" class="formulario_fieldsetTransfer div_consultaTransfers">


    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
            <td width="">
               <span id="transferencias_TAGDescripcionTransfExterna" class="datos">Using the option 'Transfer to other Banks' you can transfer funds to accounts held with other Financial Institutions.</span>
            </td>
            <td width="">
                <a id="imprimirCampos_TOB" title="Imprimir" style="cursor: pointer"><img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

        </tr>


    </table>

</fieldset>
<fieldset id="form_1" class="formulario_fieldsetTransfer div_consultaTransfers">

    <legend>1 - <span id="transferencias_TAGCuentaDebito" >From Account </span></legend>
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
            <td width="60%">
                <select id="Accounts" title="From Account: Account" class="obligatorioTOB selectFormulario_TOB" >

                </select>
                <label class="datos6">  * </label>
            </td>
            <td width="70%" class="datos2 ">
                <label id="transferencias_TAGFechaCierre2">Available balance as at </label>
                <b><label id="tagAvailableBalanceDate">  </label> </b>
            </td>
        </tr>
        <tr>
            <td width="30%">

            </td>
            <td  class="datos2" width="70%">
                <label  for="tagiTemplate" id="transferencias_TAGDirectorio" >template:</label>:
                <input  id="tagiTemplate" type="text"  class="inputFormulario_TOB">
                <%--<input type="Button" class="botonBuscar" id="btnBuscarTemplate" name="btnBuscarTemplate" value="..." tabindex="12">--%>
            </td>
        </tr>

    </table>

</fieldset>

<fieldset id="form_3" class=" div_consulta fieldset_BeneficiaryBank" >
    <legend> 2 - <span id="transferencias_TAGBancoBeneficiario">Beneficiary Bank</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="BankCode" id="transferencias_TAGCodigoBanco"  class="datos negrita">Bank Code</label>:
            </td>
            <td width="70%">
                <select id="BankCode" class="selectFormulario_TOB" title="Beneficiary Bank: Code Type" style="width: 97px" onchange="validarIntermediario();"/>

                <input id="BankCode2" type="text" class="invisible_print inputFormulario_TOB" style="width: 178px" title="Beneficiary Bank: Bank Code" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscar" name="btnCodBancoBuscar" value="..." tabindex="12"> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="BankCodeIB" id="transferencias_TAGCodigoBancoIBSwift"  class="datos negrita">Swift Code</label>:
            </td>
            <td width="70%">
                <input id="BankCodeSWIFTtext" type="text" title="Beneficiary Bank: Swift Code" class="invisible_print inputFormulario_TOB" style="width: 275px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscarSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="NameBank" id="transferencias_TAGBankName"  class="datos">Name Bank</label>:
            </td>
            <td width="70%">
                <span id="NameBank_print" class="datos visible_print"></span>
                <input id="NameBank" type="text" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Beneficiary Bank: Name Bank" style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBank1" id="transferencias_TAGDireccion1"  class="datos">Address Line 1</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBank1_print" class="datos visible_print"></span>
                <input id="AddressLineBank1" type="text" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Beneficiary Bank: Address Line 1" style="width: 300px" maxlength="50" size="55"/> <label class="datos6" >  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBank2" id="transferencias_TAGDireccion2"  class="datos">Address Line 2</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBank2_print" class="datos visible_print"></span>
                <input id="AddressLineBank2" type="text" title="Beneficiary Bank: Address Line 2" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBank3" id="transferencias_TAGDireccion3"  class="datos ">Address Line 3</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBank3_print" class="datos visible_print"></span>
                <input id="AddressLineBank3" type="text" title="Beneficiary Bank: Address Line 3" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="CountryBank" id="transferencias_TAGPais"  class="datos">Country</label>:
            </td>
            <td width="70%">
                <select id="CountryBank" class="obligatorioTOB selectFormulario_TOB" title="Beneficiary Bank: Country" style="width: 300px">

                </select>
                <label class="datos6" >  * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="form_2" class=" div_consulta fieldset_Beneficiary" >

    <legend>3 - <span id="transferencias_TAGBeneficiario">Beneficiary</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="101%" border="0">
        <tr>
            &nbsp;
        </tr>
        <tr>
            &nbsp;
        </tr>
        <tr>
            <td width="23%">
                <label for="name" id="transferencias_TAGNombre"  class="datos">Name</label>:
            </td>
            <td width="70%">
                <span id="name_print" class="datos visible_print"></span>
                <input id="name" title="Beneficiary: Name" type="text" class="invisible_print obligatorioTOB inputFormulario_TOB" style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AccountBank" id="transferencias_TAGNumeroCuenta" class="datos negrita">Account Number</label>:
            </td>
            <td width="70%">
                <select id="AccountBank" title="Beneficiary: Account Type" class="obligatorioTOB selectFormulario_TOB" style="width: 60px" >

                </select>
                <span id="AccountNumber_print" class="datos visible_print"></span>
                <input id="AccountNumber" title="Beneficiary: Account Number" type="text" class="invisible_print obligatorioTOB inputFormulario_TOB" style="width: 236px" maxlength="30" size="34"/><label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="beneficiaryEmail" id="transferencias_TAGEmail" class="datos">Email</label>:
            </td>
            <td width="70%">
                <span id="beneficiaryEmail_print" class="datos visible_print"></span>
                <input id="beneficiaryEmail" title="Beneficiary: Email" type="text" class="invisible_print obligatorioTOB inputFormulario_TOB"  style="width: 300px" maxlength="55" size="35"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AddressLine1" id="transferencias_TAGDireccion12"  class="datos">Address Line 1</label>:
            </td>
            <td width="70%">
                <span id="AddressLine1_print" class="datos visible_print"></span>
                <input id="AddressLine1" type="text" title="Beneficiary: Address Line 1" class="invisible_print obligatorioTOB inputFormulario_TOB"  style="width: 300px" maxlength="50" size="55"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AddressLine2" id="transferencias_TAGDireccion22"  class="datos">Address Line 2</label>:
            </td>
            <td width="70%">
                <span id="AddressLine2_print" class="datos visible_print"></span>
                <input id="AddressLine2" type="text" title="Beneficiary: Address Line 2" class="invisible_print inputFormulario_TOB"  style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AddressLine3" id="transferencias_TAGDireccion32"  class="datos">Address Line 3</label>:
            </td>
            <td width="70%">
                <span id="AddressLine3_print" class="datos visible_print"></span>
                <input id="AddressLine3" type="text" title="Beneficiary: Address Line 2" class="invisible_print inputFormulario_TOB"  style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="23%">

                <label for="TelephoneNumber" id="transferencias_TAGTelefono"  class="datos">Telephone Number</label>:
            </td>
            <td width="70%">
                <span id="TelephoneNumber_print" class="datos visible_print"></span>
                <input id="TelephoneNumber" type="text" title="Beneficiary: Telephone Number" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="20" size="25"/>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="Country" id="transferencias_TAGPais2"  class="datos">Country</label>:
            </td>
            <td width="70%">
                <select id="Country" title="Beneficiary: Country" class="obligatorioTOB selectFormulario_TOB" style="width: 300px">

                </select> <label class="datos6">  * </label>
            </td>

        </tr>
    </table>
</fieldset>

<fieldset id="form_4" class=" div_consulta fieldset_IntermediaryBank" >
    <legend>4 - <span id="transferencias_TAGBancoIntermediario">Intermediary Bank (if applicable)</span></legend>

    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="BankCodeIB" id="transferencias_TAGCodigoBanco2"  class="datos negrita">Bank Code</label>:
            </td>
            <td width="70%">
                <select id="BankCodeIB" title="Intermediary Bank: Bank Code Type" class="selectFormulario_TOB" style="width: 97px">

                </select>
                <span id="BankCodeIB2_print" class="datos visible_print"></span>
                <input id="BankCodeIB2" type="text" title="Intermediary Bank: Bank Code" class="invisible_print inputFormulario_TOB" style="width: 178px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscarIB" name="btnCodBancoBuscar" value="..." tabindex="12">
                <label id="BankCodeIB2OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="BankCodeIB" id="transferencias_TAGCodigoBancoSWIFT"  class="datos negrita">Swift Code</label>:
            </td>
            <td width="70%">
                <input id="BankCodeIBSWIFTtext" type="text" title="Intermediary Bank: Swift Code" class="invisible_print inputFormulario_TOB" style="width: 276px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscarIBSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="NameBankIB" id="transferencias_TAGBankName2"  class="datos">Name Bank</label>:
            </td>
            <td width="70%">
                <span id="NameBankIB_print" class="datos visible_print"></span>
                <input id="NameBankIB" type="text" title="Intermediary Bank: Name Bank" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="65" size="50"/>
                <label id="NameBankIBOB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBankIB1" id="transferencias_TAGDireccion13"  class="datos" >Address Line 1</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBankIB1_print" class="datos visible_print"></span>
                <input id="AddressLineBankIB1" type="text" title="Intermediary Bank: Address Line 1" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="50" size="55"/>
                <label id="AddressLineBankIB1OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBankIB2" id="transferencias_TAGDireccion23"  class="datos" >Address Line 2</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBankIB2_print" class="datos visible_print"></span>
                <input id="AddressLineBankIB2" type="text" title="Intermediary Bank: Address Line 2" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBankIB3" id="transferencias_TAGDireccion33"  class="datos">Address Line 3</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBankIB3_print" class="datos visible_print"></span>
                <input id="AddressLineBankIB3" type="text" title="Intermediary Bank: Address Line 3" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="CountryBankIB" id="transferencias_TAGPais3"  class="datos">Country</label>:
            </td>
            <td width="70%">
                <select id="CountryBankIB" title="Intermediary Bank: Country" class="selectFormulario_TOB" style="width: 300px">

                </select>
                <label id="CountryBankIBOB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="form_5" class=" div_consulta fieldset_ForFurtherCredit" >
    <legend>5 - <span id="transferencias_TAGParaFuturoCredito">For Further Credit to (if applicable)</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="35%">
                <label for="AccountNumberFFC" id="transferencias_TAGNumeroCuenta2"  class="datos">Account Number</label>:
            </td>
            <td width="70%">
                <span id="AccountNumberFFC_print" class="datos visible_print"></span>
                <input id="AccountNumberFFC" type="text" title="For Further Credit to: Account Number" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="25" size="26"/>
            </td>
        </tr>
        <tr>
            <td width="35%">
                <label for="NameFFC" id="transferencias_TAGNombre2"  class="datos">Name</label>:
            </td>
            <td width="70%">
                <span id="NameFFC_print" class="datos visible_print"></span>
                <input id="NameFFC" type="text" title="For Further Credit to: Name" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="65" size="50"/>
            </td>
        </tr>

    </table>
</fieldset>

<fieldset id="form_6" class=" div_consultaTransfers" >
    <legend>6 - <span id="transferencias_TAGMontoInstrucciones">Amount & Instructions</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="AmmountAI" id="transferencias_TAGMonto_2"  class="datos">Amount</label>:
            </td>
            <td width="70%">
                <span id="AmmountAI_print" class="datos visible_print"></span>
                <%--<input id="AmmountAI" type="text" style="width: 300px" maxlength="15" size="15" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Amount & Instructions: Amount" onblur="this.value=formatCurrency(this.value,false,2,',');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2);"/> <label class="datos6" >  * </label>--%>
                <input id="AmmountAI" type="text" style="width: 300px" maxlength="15" size="15" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Amount & Instructions: Amount" onblur="this.value=formatCurrency(this.value,true,2,'.');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'OB');"/> <label class="datos6" >  * </label>
                <%--<input id="AmmountAIAux" type="hidden" style="width: 300px" />--%>
                <%--<input id="AmmountAI" type="text" style="width: 300px" maxlength="15" size="15" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Amount & Instructions: Amount" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeydown="habilitarPanel_con_boton('form_6','form_7');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/> <label class="datos6" >  * </label>--%>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ReceiverInformation" id="transferencias_TAGDescripcion"  class="datos">Receiver Information</label>:
            </td>
            <td width="70%">
                <span id="ReceiverInformation_print" class="datos visible_print"></span>
                <input id="ReceiverInformation" type="text" title="Amount & Instructions: Receiver Information" class="invisible_print inputFormulario_TOB" style="width: 300px" maxlength="60" size="55"/>
            </td>

        </tr>

    </table>
</fieldset>

<fieldset id="form_13" class=" div_consultaTransfers" >
    <legend>7 - <span id="transferencias_TAGMotivo">Motivo de la Transferencia</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="MotivoAI" id="transferencias_TAGMotivo_2"  class="datos">Motivo</label>:
            </td>
            <td width="70%">
                <span id="Motivo_print" class="datos visible_print"></span>
                <input id="MotivoAI" type="text" style="width: 300px" maxlength="40" size="40" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Motivo de la Transferencia: Motivo" /> <label class="datos6" >  * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset class=" div_consultaTransfers">

    <label id="transferencias_TAGMsgCampoObligario" class="datosCampoObligatorio"> * Mandatory Fields</label>

</fieldset>

<fieldset id="form_7" class=" div_consultaTransfers center" >
    <input  type="button" id="btn_TOB_volver" value="Back" class="boton">
    <input  type="button" id="btn_TOB_cancelar" value="Clear" class="boton">
    <input  type="button" id="btn_TOB_aceptar" value="Acept" class="boton">
</fieldset>

<fieldset id="form_8" class=" div_consultaTransfers right" >
    <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
        <tr>
            <td height="50" >
                <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="100%" height="35" class="V8NBNegroGris" align="center">
                           <span id="transferencias_TAGAvisosImportantes_2" class="datos"><b>IMPORTANT NOTICE</b></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="100%" class="V8NBNegroGris" align="left">
                            <span id="transferencias_TAGMsgDisclaimerExternas_2" class="datos">
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
<div id="noCreateToOtherBank" style="display: none">
    <fieldset class="div_consulta">
        <span id="noInfo_noCreateToOtherBank" class="datos"></span>
    </fieldset>
</div>
<div id="summaryToOtherBank" style="display: none" >
<div id="tituloResumen_TOB" class="titulo_resume" style="display: block">
    <span id="summary_TOB">Summary</span>
    <div class="descripcion_resume">
        <span id="sumaryConfirm_TOB">Confirm the Data of the Transfer<br></span>
    </div>
</div>
<div id="tituloExitoso_TOB" class="titulo_resume" style="display: none">
    <span id="successfull_TOB">Successful transfer</span>

</div>
<div id="clave_TOB" style="display: none">
    <fieldset id="form_12" class=" div_consultaTransfers" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="1" align="center">
            <tr>
                <td width="3%">&nbsp;</td>
                <td width="97%">
                    <TABLE border="0" cellspacing="0" cellpadding="5" width="100%">
                        <tr>
                            <td class="V8NNegroGris">
                                <span id="transferencias_TAGDescripcionTransfExtClave1" class="datos">Please check on your e-mail account the message you received from VBT Bank & Trust indicating the 'Transaction Key' to confirm and complete this transfer operation.</span><br>
                                <span id="transferencias_TAGDescripcionTransfExtClave2" class="datos"><br><b>Remember that this Transaction Key is valid only to confirm the specific transfer operation that it originated.</b><br><br> It will become invalid:<br>  1. after used to confirm the specific transfer operation,<br>  2. when the transfer itself has been canceled or<br>  3. once you logoff from VBT Online.</span>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
            <tr><td height="12"></td></tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_11" class=" div_consultaTransfers" >
       <div id="metodosValidacionTransfer">

       </div>
    </fieldset>
    <fieldset id="form_9" class=" div_consultaTransfers" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" class="" align="center">
                                <b><span id="transferencias_TAGImportante" class="datos">IMPORTANT NOTICE:</span></b>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" class="V8NBNegroGris" align="center">
                                <b><span id="transferencias_TAGMsgAdvertencia" class="datos">Once you press Accept the transaction will be processed according<BR>to your instructions and no modification is permitted.</span> </b>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_10_botones" class="div_consultaTransfers center oculto" >
        <input  type="button" id="btn_cancelarClave" value="Cancel" class="boton">
        <input  type="button" id="btn_aceptarClave" value="Accept" class="boton">

    </fieldset>

</div>

<div class="botones_resumeTransfer">

<table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen">
<tbody>
<tr id="div_numref_TOB" style="display: none">
    <td class="titulo_resumen right" colspan="2">
      <b><span id="transferencias_TAGNumeroRecibo">  Reference No.&nbsp; </span> <span id="numRef_TOB"></span> </b>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGCuentaDebito2">From Account</span>
    </td>
    <td class="datos_resumen" width='300px'>
        <span id="RAccounts" class="datos_resumen"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="transferencias_TAGBancoBeneficiario2">Beneficiary Bank</span></b>
    </td>
</tr>

<tr id="div_RBankCode" style="display: none">
    <td class="titulo_resumen">
       <span id="transferencias_TAGEmailCodigoBancoBeneficiario">Beneficiary Bank Code</span>
    </td>
    <td class="datos_resumen">
        <span id="RBankCode" class="TOB_borrarData"></span>
    </td>
</tr>

<tr id="div_RBankCodeSwift" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGEmailCodigoBancoBeneficiarioSwift">Beneficiary Bank Code Swift</span>
    </td>
    <td class="datos_resumen">
        <span id="RBankCodeSwift" class="TOB_borrarData"></span>
    </td>
</tr>

<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryBankAddress1">Beneficiary Bank Address 1</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLineBank1" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RAddressLineBank2" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryBankAddress2">Beneficiary Bank Address 2</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLineBank2"></span>
    </td>
</tr>
<tr id="div_RAddressLineBank3" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryBankAddress3">Beneficiary Bank Address 3</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLineBank3" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryBankCountry">Beneficiary Bank Country</span>
    </td>
    <td class="datos_resumen">
        <span id="RCountryBank" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="transferencias_TAGNombreBeneficiario2">Beneficiary</span></b>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryName">Beneficiary Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Rname" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryAccount">Beneficiary Account</span>
    </td>
    <td class="datos_resumen">
        <span id="RAccountNumber" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGEmailBeneficiario"></span>
    </td>
    <td class="datos_resumen">
        <span id="RbeneficiaryEmail" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
       <span id="transferencias_TAGBeneficiaryAddress1">Beneficiary Address 1</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLine1" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RAddressLine2" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryAddress2">Beneficiary Address 2</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLine2" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RAddressLine3" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryAddress3">Beneficiary Address 3</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLine3" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RTelephoneNumber" style="display: none">
    <td class="titulo_resumen">
       <span id="transferencias_TAGBeneficiaryTelephone">Beneficiary Telephone</span>
    </td>
    <td class="datos_resumen">
        <span id="RTelephoneNumber" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGBeneficiaryCountry">Beneficiary Country</span>
    </td>
    <td class="datos_resumen">
        <span id="RCountry" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_TOB_resumen_intermediary" style="display: none">
    <td class="titulo_resumen" colspan="2">
        <b><span id="transferencias_TAGIntermediaryBank">Intermediary Bank</span></b>
    </td>
</tr>
<tr id="div_RBankCodeIB" style="display: none">
    <td class="titulo_resumen">
       <span id="transferencias_TAGEmailCodigoBancoIntermediario">Intermediary Bank Code</span>
    </td>
    <td class="datos_resumen">
        <span id="RBankCodeIB" class="TOB_borrarData"></span>
    </td>
</tr>

<tr id="div_RBankCodeIBSwift" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGEmailCodigoBancoIntermediarioSwift">Intermediary Bank Code Swift</span>
    </td>
    <td class="datos_resumen">
        <span id="RBankCodeIBSwift" class="TOB_borrarData"></span>
    </td>
</tr>

<tr id="div_RNameBankIB" style="display: none">
    <td class="titulo_resumen">
       <span id="transferencias_TAGEmailNombreBancoIntermediario"> Intermediary Bank Name</span>
    </td>
    <td class="datos_resumen">
        <span id="RNameBankIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RAddressLineBankIB1" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGIntermediaryBankAddress1">Intermediary Bank Address 1</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLineBankIB1" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RAddressLineBankIB2" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGIntermediaryBankAddress2">Intermediary Bank Address 2</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLineBankIB2" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RAddressLineBankIB3" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGIntermediaryBankAddress3">Intermediary Bank Address 3</span>
    </td>
    <td class="datos_resumen">
        <span id="RAddressLineBankIB3" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RCountryBankIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGIntermediaryBankCountry"> Intermediary Bank Country</span>
    </td>
    <td class="datos_resumen">
        <span id="RCountryBankIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_TOB_resumen_furtherCredit" style="display: none">
    <td class="titulo_resumen" colspan="2">
        <b><span id="transferencias_TAGParaFuturoCredito2">For Further Credit to</span></b>
    </td>
</tr>
<tr id="div_RAccountNumberFFC" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGFurtherCreditAccount">Further Credit Account</span>
    </td>
    <td class="datos_resumen">
        <span id="RAccountNumberFFC" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RNameFFC" style="display: none">
    <td class="titulo_resumen">
       <span id="transferencias_TAGFurtherCreditName">Further Credit Name</span>
    </td>
    <td class="datos_resumen">
        <span id="RNameFFC" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="transferencias_TAGMontoInstrucciones2">Amount & Instructions</span></b>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGMonto2">Amount</span>
    </td>
    <td class="datos_resumen">
        <span id="RAmmountAI" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RReceiverInformation" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGConcepto">Description</span>
    </td>
    <td class="datos_resumen">
        <span id="RReceiverInformation" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="transferencias_TAGMotivo_3">Motivo</span>
    </td>
    <td class="datos_resumen">
        <span id="RMotivoAI" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_estatus_TOB" style="display: none">

    <td class="titulo_resumen">
        <span id="transferencias_TAGEstatusTransferencia">Transfer Status</span>
    </td>
    <td class="datos_resumen">
        <span id="status_TOB" class="TOB_borrarData"></span>
    </td>

</tr>

<tr id="resumenBotones_TOB" style="display: block">
    <td class="datos" colspan="2">
        <input type="button" id="btn__resumen_cancelar" value="<< Back" class="boton">
        <input type="button" id="btn__resumen_aceptar" value="Accept" class="boton">
    </td>
</tr>
<tr id="resumenBotones_TOB_Finales" style="display: none" class="invisible_print" >
    <td class="datos center" colspan="2">
        <input type="button" id="btn_imprimir_TOB_final" value="Print" class="boton">
        <input type="button" id="btn_finalizar_TOB_final" value="Make Another Transfer" class="botonTransfer">
        <input type="button" id="btn_TemplateGuardar_TOB_final" value="Save as Template" class="botonTransfer">
        <input type="button" id="btn_ok_TOB_final" value="OK" class="botonTransfer">
    </td>
</tr>
</tbody>
</table>
</div>
</div>



<div id="sign_up" class="invisible_print">
    <h3 id="see_id" >Search Bank Code</h3>
    <div id="sign_up_form">
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
         <tr>
             <td><label id="tag_buscarBanco_codetype"><strong>Bank Code Type:</strong></label></td>
             <td><select id="BankCodeType_buscar" class="selectFormulario_buscar" title="Beneficiary Bank: Code Type" > </select></td>
             <td><label id="tag_buscarBanco_code"><strong>Bank Code:</strong></label></td>
             <td><input id="BankCode_buscar" type="text" class="obligatorioBuscar inputFormulario_buscar"  title="Beneficiary Bank: Bank Code"/></td>
         </tr>
         <tr>
             <td><label id="tag_buscarBanco_nombre"><strong>Bank Name:</strong></label></td>
             <td><input id="BankName_buscar" type="text" class="obligatorioBuscar inputFormulario_buscar"  title="Beneficiary Bank: Bank Name"/></td>
             <td><label id="tag_buscarBanco_Pais"><strong>Country:</strong></label></td>
             <td><select id="BankCountry_buscar" class="selectFormulario_buscar" title="Country" > </select></td>
         </tr>
        </table>

        <div id="actions">
            <input type='button' id="cancel_FC" href="#" value="Cancel" class="boton"/>
            <input type='button' id="buscar_FC" href="#" value="Search" class="boton"/>
        </div>
    </div>
    <div id="div_carga_espera_sign_up" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='30px' alt="" /></div>
    <div id="buscarBanco_div_tabla_consulta_bancos" class="result"></div>
    <div id="paginacion_buscarBanco_consulta_bancos" class="result"></div>
    <a id="close_x" class="close sprited" href="#">close</a>

    <div id="div_mensajes_info_desc" class="info_descp oculto">
        <div id="mensajes_info_desc">
            error
        </div>
        <div id="cerrar_div_mensajes_info_desc">[X]</div>
    </div>
</div>

<div id="sign_up_template_transfer">
    <h3 id="see_id_template_transfer" >Templates Cargados</h3>
    <div id="div_carga_espera_template_transfer" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='40px' alt="" /></div>
    <div id="div_tabla_template_consulta_transfer" class="result"></div>
    <div id="paginacion_buscarBanco_consulta_bancos_template_transfer" class="result"></div>
    <a id="close_x" class="close sprited" href="#">close</a>

    <div id="div_mensajes_info_desc" class="info_descp oculto">
        <div id="mensajes_info_desc">
            error
        </div>
        <div id="cerrar_div_mensajes_info_desc">[X]</div>
    </div>
</div>




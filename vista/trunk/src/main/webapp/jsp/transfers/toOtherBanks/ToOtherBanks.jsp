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
<%--

<div>
    <h1 id="transferencias_TAGTituloOpe">Transfers / To Other Banks </h1>
</div>
<div id="createToOtherBank">




<fieldset id="DIV_INFO_EXTERNA_FI" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
    <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
        <tbody>
        <tr>
            <td width="3%">
                <img  src="resources/images/iconInfo.png" >
            </td>
            <td align="left">
                <span id="TAG_INFO_EXTERNA_FI" class="datosInfo">This option enables you to make transfers to accounts at other financial institutions. Remember that such transfers must be subsequently approved and released by users with the appropriate permissions.</span>
            </td>
            <td align="right">
                <a id="imprimirCampos_TOBFI" title="Imprimir" style="cursor: pointer"><img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>

<fieldset id="DIV_INFO_EXTERNA_FC" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
    <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
        <tbody>
        <tr>
            <td width="3%">
                <img  src="resources/images/iconInfo.png" >
            </td>
            <td align="left">
                <span id="TAG_INFO_EXTERNA_FC" class="datosInfo">This option enables you to make transfers from your account(s) with VBT Bank & Trust to another account(s) at other financial institutions.</span>
            </td>
            <td align="right">
                <a id="imprimirCampos_TOBFC" title="Imprimir" style="cursor: pointer"><img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div class="clear"></div>




&lt;%&ndash;<fieldset id="infoTransfer" class="formulario_fieldsetTransfer div_consultaTransfers">&ndash;%&gt;


    &lt;%&ndash;<table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<td width="">&ndash;%&gt;
               &lt;%&ndash;<span id="transferencias_TAGDescripcionTransfExterna" class="datos">Using the option 'Transfer to other Banks' you can transfer funds to accounts held with other Financial Institutions.</span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;<td align="left">&ndash;%&gt;
                &lt;%&ndash;<a id="imprimirCampos_TOB" title="Imprimir" style="cursor: pointer"><img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;

        &lt;%&ndash;</tr>&ndash;%&gt;


    &lt;%&ndash;</table>&ndash;%&gt;

&lt;%&ndash;</fieldset>&ndash;%&gt;
<fieldset id="form_1" class="formulario_fieldsetTransfer div_consultaTransfers">

    <legend>1 - <span id="transferencias_TAGCuentaDebito" >From Account </span></legend>
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
            <td width="50%">
                <select id="Accounts" title="From Account: Account" class="obligatorioTOB selectFormulario_TOB obligatorioTOBTMP verificarCancelacion" style="width: 97%;">

                </select>
                <label class="datos6">  * </label>
            </td>

            <td style="width: 90%;" class="datos2 ">
                <label id="transferencias_TAGFechaCierre2" >Available balance as at </label>
                <b><label id="tagAvailableBalanceDate">  </label> </b>
            </td>
        </tr>
        <tr>
  <!--          <td align="right" colspan="2"> -->
                <table width="100%" align="right">
                    <tbody>
                    <tr>
                        <td width="95%" align="right">
                            <label class="datosInfo" id="TAG_INFO_TEMP_TRANSFERS" >Click on the menu if you want to select a saved template.</label>
                        </td>
                        <td class="datos2" style="align: right;">
                            <input type="button" aria-haspopup="true" aria-autocomplete="list" role="textbox" autocomplete="off" title="Template" value="Template" id="tagiTemplate" class="boton ui-autocomplete-input">
                        </td>
                    </tr>
                    </tbody></table>
         <!--   </td> -->
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

                <input id="BankCode2" type="text" class="invisible_print inputFormulario_TOB only_alpha_num" style="width: 178px" title="Beneficiary Bank: Bank Code" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscar" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="BankCodeIB" id="transferencias_TAGCodigoBancoIBSwift"  class="datos negrita">Swift Code</label>:
            </td>
            <td width="70%">
                <input id="BankCodeSWIFTtext" type="text" title="Beneficiary Bank: Swift Code" class="invisible_print inputFormulario_TOB only_alpha_num" onkeypress="return validarn(event)" style="width: 275px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscarSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="NameBank" id="transferencias_TAGBankName"  class="datos">Name Bank</label>:
            </td>
            <td width="70%">
                <span id="NameBank_print" class="datos visible_print"></span>
                <input id="NameBank" type="text" class="invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more" title="Beneficiary Bank: Name Bank" style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBank1" id="transferencias_TAGDireccion1"  class="datos">Address Line 1</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBank1_print" class="datos visible_print"></span>
                <input id="AddressLineBank1" type="text" class="invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more" title="Beneficiary Bank: Address Line 1" style="width: 300px" maxlength="50" size="55"/> <label class="datos6" >  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBank2" id="transferencias_TAGDireccion2"  class="datos">Address Line 2</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBank2_print" class="datos visible_print"></span>
                <input id="AddressLineBank2" type="text" title="Beneficiary Bank: Address Line 2" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBank3" id="transferencias_TAGDireccionCity"  class="datos ">City</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBank3_print" class="datos visible_print"></span>
                <input id="AddressLineBank3" type="text" title="City" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="CountryBank" id="transferencias_TAGPais"  class="datos">Country</label>:
            </td>
            <td width="70%">
                <select id="CountryBank" class="obligatorioTOBTMP obligatorioTOB selectFormulario_TOB verificarCancelacion" title="Beneficiary Bank: Country" style="width: 300px">

                </select>
                <label class="datos6" >  * </label>
            </td>
        </tr>
    </table>
</fieldset>
<div id="dialog-confirm"  style="display:none">
    <p></p>
</div>
<div id="dialog-disclaimer"  style="display:none">
    <p align="justify"></p>
</div>
<fieldset id="form_2" class=" div_consulta fieldset_Beneficiary" >

    <legend>3 - <span id="transferencias_TAGBeneficiario">Beneficiary</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="101%" border="0">

        <tr>
            <td width="23%">
                <label for="TipoPersona" id="transferencias_TAGTipoPersona"  class="datos">Name</label>:
            </td>
            <td width="70%">
                <span id="tipoPersona_print" class="datos visible_print"></span>
                <select id="TipoPersona" title="Beneficiary: Type" class="obligatorioTOB obligatorioTOBTMP selectFormulario_TOB"  >
                    <option value="-2" id="transferencias_tipoPersonaSelect" >Select</option>
                    <option value="NAT" id="transferencias_tipoPersonaNatural">Natural</option>
                    <option value="JUR" id="transferencias_tipoPersonaJuridica">Jur&iacute;dico</option>
                </select>

            </td>
        </tr>

        <tr >
            <td width="23%">
                <label for="name" id="transferencias_TAGNombre"  class="datos">Name</label>:
            </td>
            <td width="70%">
                <span id="name_print" class="datos visible_print"></span>
                <input id="name" title="Beneficiary: Name" type="text" class="invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr name="transferencias_apellidos" class="oculto">
            <td width="23%">
                <label for="lastname1" id="transferencias_TAGLastname1"  class="datos">First Last Name</label>:
            </td>
            <td width="70%">
                <span id="lastname1_print" class="datos visible_print"></span>
                <input id="lastname1" title="Beneficiary: First Last Name" type="text" class="only_alpha invisible_print inputFormulario_TOB" style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr name="transferencias_apellidos" class="oculto">
            <td width="23%">
                <label for="lastname2" id="transferencias_TAGLastname2"  class="datos">Second Last Name</label>:
            </td>
            <td width="70%">
                <span id="lastname2_print" class="datos visible_print"></span>
                <input id="lastname2" title="Beneficiary: Second Last Name" type="text" class="only_alpha invisible_print inputFormulario_TOB" style="width: 300px" maxlength="65" size="50"/>
            </td>
        </tr>

        <tr>
            <td width="70%">
                <label for="AccountBank" id="transferencias_TAGNumeroCuenta" class="datos negrita">Account Number</label>
                <select id="AccountBank" title="Beneficiary: Account Type" class="obligatorioTOB obligatorioTOBTMP selectFormulario_TOB" style="width: 60px" >

                </select>
                <span id="AccountNumber_print" class="datos visible_print"></span>
                <input id="AccountNumber" title="Beneficiary: Account Number" type="text" class="invisible_print obligatorioTOBTMP obligatorioTOB inputFormulario_TOB only_alpha_num" style="width: 236px" maxlength="30" size="34"/><label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="beneficiaryEmail" id="transferencias_TAGEmail" class="datos">Email</label>:
            </td>
            <td width="70%">
                <span id="beneficiaryEmail_print" class="datos visible_print"></span>
                <input id="beneficiaryEmail" title="Beneficiary: Email" type="text" class="invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB"  style="width: 300px" maxlength="55" size="35"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AddressLine1" id="transferencias_TAGDireccion12"  class="datos ">Address Line 1</label>:
            </td>
            <td width="70%">
                <span id="AddressLine1_print" class="datos visible_print"></span>
                <input id="AddressLine1" type="text" title="Beneficiary: Address Line 1" class="invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more"  style="width: 300px" maxlength="50" size="55"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AddressLine2" id="transferencias_TAGDireccion22"  class="datos">Address Line 2</label>:
            </td>
            <td width="70%">
                <span id="AddressLine2_print" class="datos visible_print"></span>
                <input id="AddressLine2" type="text" title="Beneficiary: Address Line 2" class="invisible_print inputFormulario_TOB only_alpha_num_more"  style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
        <td width="23%">
                <label  id="transferencias_TAGDireccion32"  class="datos">City</label>:
            </td>
            <td width="70%">
                <span id="AddressLine3_print" class="datos visible_print"></span>
                <input id="AddressLine3" type="text" title=City class="invisible_print obligatorioTOBTMP obligatorioTOB inputFormulario_TOB only_alpha_num_more"  style="width: 300px" maxlength="45" size="55"/>
                <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="AddressLine3" id="transferencias_TAGPostalCode"  class="datos">Postal Code</label>:
            </td>
            <td width="70%">
                <span id="BeneficiaryPostalCode_print" class="datos visible_print"></span>
                <input id="BeneficiaryPostalCode" type="text" title="Beneficiary: Postal Code" class="invisible_print inputFormulario_TOB"  style="width: 300px" maxlength="45" size="55" onkeypress=""/>
            </td>
        </tr>
        <tr>
            <td width="23%">

                <label for="TelephoneNumber" id="transferencias_TAGTelefono"  class="datos">Telephone Number</label>:
            </td>
            <td width="70%">
                <span id="TelephoneNumber_print" class="datos visible_print"></span>
                <input id="TelephoneNumber" type="text" title="Beneficiary: Telephone Number" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="20" size="25"/>
            </td>
        </tr>
        <tr>
            <td width="23%">
                <label for="Country" id="transferencias_TAGPais2"  class="datos">Country</label>:
            </td>
            <td width="70%">
                <select id="Country" title="Beneficiary: Country" class="obligatorioTOB obligatorioTOBTMP selectFormulario_TOB" style="width: 300px">

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
                <input id="BankCodeIB2" type="text" title="Intermediary Bank: Bank Code" class="invisible_print inputFormulario_TOB only_alpha_num" style="width: 178px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscarIB" name="btnCodBancoBuscar" value="..." tabindex="12">
                <label id="BankCodeIB2OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="BankCodeIB" id="transferencias_TAGCodigoBancoSWIFT"  class="datos negrita">Swift Code</label>:
            </td>
            <td width="70%">
                <input id="BankCodeIBSWIFTtext" type="text" title="Intermediary Bank: Swift Code" class="invisible_print inputFormulario_TOB only_alpha_num" onkeypress="return validarn(event)" style="width: 276px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="btnCodBancoBuscarIBSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="NameBankIB" id="transferencias_TAGBankName2"  class="datos">Name Bank</label>:
            </td>
            <td width="70%">
                <span id="NameBankIB_print" class="datos visible_print"></span>
                <input id="NameBankIB" type="text" title="Intermediary Bank: Name Bank" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="65" size="50"/>
                <label id="NameBankIBOB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBankIB1" id="transferencias_TAGDireccion13"  class="datos" >Address Line 1</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBankIB1_print" class="datos visible_print"></span>
                <input id="AddressLineBankIB1" type="text" title="Intermediary Bank: Address Line 1" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="50" size="55"/>
                <label id="AddressLineBankIB1OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBankIB2" id="transferencias_TAGDireccion23"  class="datos" >Address Line 2</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBankIB2_print" class="datos visible_print"></span>
                <input id="AddressLineBankIB2" type="text" title="Intermediary Bank: Address Line 2" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AddressLineBankIB3" id="transferencias_TAGDireccion33City"  class="datos">City</label>:
            </td>
            <td width="70%">
                <span id="AddressLineBankIB3_print" class="datos visible_print"></span>
                <input id="AddressLineBankIB3" type="text" title="Intermediary Bank: Address Line 3" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="45" size="55"/>
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
                <input id="AccountNumberFFC" type="text" title="For Further Credit to: Account Number" class="invisible_print inputFormulario_TOB only_alpha_num" style="width: 300px" maxlength="25" size="26"/>
            </td>
        </tr>
        <tr>
            <td width="35%">
                <label for="NameFFC" id="transferencias_TAGNombre2"  class="datos">Name</label>:
            </td>
            <td width="70%">
                <span id="NameFFC_print" class="datos visible_print"></span>
                <input id="NameFFC" type="text" title="For Further Credit to: Name" class="invisible_print inputFormulario_TOB only_alpha_num_more" style="width: 300px" maxlength="65" size="50"/>
            </td>
        </tr>

    </table>
</fieldset>

<fieldset id="form_6" class=" div_consultaTransfers" >
    <legend>6 - <span id="transferencias_TAGMontoInstrucciones">Amount & Instructions</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">

        <tr id="cancelarProductoMostrarTOB" >
            <td width="30%">
                <label id="TAGproductoCancelacionTOB" class="datos" > Product Cancelation</label><span class="datos6">:</span>
            </td>
            <td width="70%">
            <input type="checkbox" id="productCancelationTOB" class="checkboxFormulario_TOBVBT inputFormulario_TOB invisible_print" >
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="AmmountAI" id="transferencias_TAGMonto_2"  class="datos negrita">Amount</label>:
            </td>
            <td width="70%">
                <span id="AmmountAI_print" class="datos visible_print"></span>
                &lt;%&ndash;<input id="AmmountAI" type="text" style="width: 300px" maxlength="15" size="15" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Amount & Instructions: Amount" onblur="this.value=formatCurrency(this.value,false,2,',');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2);"/> <label class="datos6" >  * </label>&ndash;%&gt;
                <input id="AmmountAI" type="text" style="width: 300px" maxlength="15" size="15" class="invisible_print obligatorioTOB inputFormulario_TOB" title="Amount & Instructions: Amount" onblur="this.value=formatCurrency(this.value,true,2,'.');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'OB');"/> &nbsp; <span id="AmountCodMon" class="datos"></span> <label class="datos6" >  * </label>
            </td>
        </tr>
        <tr id="montoComisionMostrar" >
            <td width="30%">
                <label id="transferencias_TAGmontoComision" class="datos"> Comision</label><span class="datos6">: </span>
            </td>
            <td width="70%">
               <label id="montoComision" class="datos negrita"></label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="ReceiverInformation" id="transferencias_TAGDescripcion"  class="datos">Receiver Information</label>:
            </td>
            <td width="70%">
                <span id="ReceiverInformation_print" class="datos visible_print"></span>
                <input id="ReceiverInformation" type="text" title="Amount & Instructions: Receiver Information" class="only_alpha_num invisible_print inputFormulario_TOB" style="width: 300px" maxlength="60" size="55"/>
            </td>

        </tr>

    </table>
</fieldset>

<fieldset id="form_13" class=" div_consultaTransfers">
    <legend>7 - <span id="transferencias_TAGMotivo">Motivo de la Transferencia</span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="MotivoAI" id="transferencias_TAGMotivo_2"  class="datos">Motivo</label>:
            </td>
            <td width="70%">
                <span id="Motivo_print" class="datos visible_print"></span>
                    <select id="Motivos" title="Motivo de la Transferencia: Motivo" class="obligatorioTOB selectFormulario_TOB" style="width: 300px">
                </select>
                <input id="MotivoAI" type="text"  style="width: 300px;" maxlength="60" size="60" class="only_alpha invisible_print inputFormulario_TOB" title="Motivo de la Transferencia: Motivo" />
                <label id="RequiredMotivoAI" class="datos6" style="display: none;">  * </label>

            </td>
        </tr>
        <tr><td colspan="2" width="100%">&nbsp;</td></tr>
        <tr id="TABSoporte3" style="display:none;">
            <td width="30%" >
                <label for="fileupload" id="transferencias_TAGSoporteMotivo"  class="datos">Documents that supports the transaction</label>:
            </td>
            <td width="70%">
                <div class="upload">
                    <input id="fileupload" type="file" accept="application/pdf,image/x-png,image/jpeg,image/jpg"  multiple title="Motivo de la Transferencia: Soporte" data-url="Transfers_fileUpload.action"  enctype="multipart/form-data">
                </div>
            </td>
        </tr>
        <tr id="TABSoporte" style="display:none;">
            <td rowspan="2" valign="top">
                <p id="Transferencias_TAGSoporteMotivos3" class="datos">Allowed format: PDF, JPG, JPEG, PNG <br>Maximum file size for upload: 15Mb</p>
                <!--<p id="Transferencias_TAGSoporteMotivos4" class="datos">* To select multiple files press CTRL key and click on them</p>-->
            </td>
            <td width="70%">
                <div id="progress" style="width:310px" class="font">
                    <div class="bar" style="width: 0%;">
                    </div>
                </div>
                <div class="percent">0%</div >

            </td>
        </tr>
        <tr id="TABSoporte2" style="display:none;">
            <td width="70%">
                <div id="transferencias_TAGMsgSoporteMotivo">
                    <div class="Nofiles" id="transferencias_TAGMsgSoporteMotivo2" style="position:relative;height:17px;width:300px;">
                    </div>
                </div>
            </td>
        </tr>
        <tr id="TABSoporte4" style="display:none;">
            <td width="30%">&nbsp;</td>
            <td width="70%"><div id="loading">&nbsp;</div ></td>
        </tr>

    </table>
</fieldset>

<fieldset id="form_14" class="div_consulta  fieldset_BeneficiaryInformation" style="display:none">

    <legend>8 - <span id="transferencias_TAGInformacionBeneficiario">Beneficiary Information </span></legend>
    <table SUMMARY='tabla' cellpadding="0" id="TableIndividual" cellspacing="0" width="100%" border="0">

        <!--  For Individual -->
        <tr>
            <td width="15%">
                <label for="FullNameIndividualIB" id="transferencias_TAGNombreCompletoNatural"  class="datos">Full Name</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input id="FullNameIndividualIB" type="text" title="Individual Beneficiary: Full Name" class="inputFormulario_TOB only_alpha"  style="width: 300px;" maxlength="65" size="50"/>
                <label class="datos6">  * </label>
            </td>

            <td width="15%">
                <label for="DateBirthIB" id="transferencias_TAGFechaNacimiento"  class="datos">Date of Birth</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input type="text" id="DateBirthIB" title="Individual Beneficiary: Date of Birth" class="inputFormulario_TOB"  />
                <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="15%">
                <label for="NationalityIB" id="transferencias_TAGNacionalidad"  class="datos" >Nationality</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <select id="NationalityIB" title="Individual Beneficiary: Nationality" class="selectFormulario_TOB" style="width: 300px;" >

                </select>
                <label class="datos6">  * </label>
            </td>
            <td width="15%">
                <label for="IdPassportIB" id="transferencias_TAGDocumentoIdentidad"  class="datos" >I.D. / Passport Nr.</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input id="IdPassportIB" type="text" title="Individual Beneficiary: I.D. / Passport Nr." class="inputFormulario_TOB only_alpha_num_more"  style="width: 300px;" maxlength="20" size="26"/>
                <label class="datos6">  * </label>
            </td>
        </tr>
    </table>
    <table SUMMARY='tabla' cellpadding="0" id="TableCompanies" cellspacing="0" width="100%" border="0">
        <tr>
            <!--  For Companies -->
            <td width="15%">
                <label for="FullNameIB" id="transferencias_TAGNombreCompleto"  class="datos">Full Name</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input id="FullNameIB" type="text" title="Companies Beneficiary: Full Name" class="inputFormulario_TOB only_alpha_num_more"  style="width: 300px;" maxlength="65" size="50"/>
                <label class="datos6">  * </label>
            </td>
            <td width="15%">
                <label for="CountryIncorporationIB" id="transferencias_TAGPaisIncorporacion"  class="datos">Country of Incorporation</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <select id="CountryIncorporationIB" title="Companies Beneficiary: Country of Incorporation" class="selectFormulario_TOB" style="width: 300px;" >

                </select>
                <label class="datos6">  * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset class=" div_consultaTransfers">

    <div id="mismoDiaTOB">
        <label id="TAGTransferenciaMismoDia" class="datos">Transferencia Mismo Dia</label>
        <input type="checkbox" id="transMismoDia" value="" class="hidden verificarCancelacion">
    </div>




    <label id="transferencias_TAGMsgCampoObligario" class="datosCampoObligatorio"> * Mandatory Fields</label>

</fieldset>

<fieldset id="form_7" class=" div_consultaTransfers center invisible_print" >
    <input  type="button" id="btn_TOB_volver" value="Back" class="boton">
    <input  type="button" id="btn_TOB_cancelar" value="Clear" class="boton">
    <input  type="button" id="btn_TemplateGuardar_TOB_cancel" value="Guardar Plantilla" class="boton ">
    <input  type="button" id="btn_TOB_aceptar" value="Accept" class="boton">
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
    <div id="TOBdisclaimer"  style="display:none">
        <p align="justify"></p>
    </div>


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
        <span id="RAccounts" class="datos_resumen "></span>
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

<tr id="div_RBankName" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGEmailNombreBancoBeneficiario">Beneficiary Bank Name</span>
    </td>
    <td class="datos_resumen">
        <span id="RBankName" class="TOB_borrarData"></span>
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
<tr id="div_PostalCode" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGRPostalCode">Postal Code</span>
    </td>
    <td class="datos_resumen">
        <span id="RBeneficiaryPostalCode" class="TOB_borrarData"></span>
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
<tr id="div_Transferencias_productoCancelacionIB" >
    <td class="titulo_resumen">
        <span id="transferencias_TAGProductoCancelacion">Cancelacion Producto</span>
    </td>
    <td class="datos_resumen">
        <span id="RProductoCancelacionIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Transferencias_mismoDiaIB" >
    <td class="titulo_resumen">
        <span id="transferencias_TAGmismoDia">Mismo Día</span>
    </td>
    <td class="datos_resumen">
        <span id="mismoDiaIB" class="TOB_borrarData"></span>
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
<!-- Resumen -->
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="transferencias_TAGInformacionBeneficiario2">Beneficiary Information</span></b>
    </td>
</tr>
<tr id="div_Transferencias_RFullNameIndividualIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGNombreCompletoNatural2">Full Name</span>
    </td>
    <td class="datos_resumen">
        <span id="RFullNameIndividualIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Transferencias_RDateBirthIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGFechaNacimiento2">Date of Birth</span>
    </td>
    <td class="datos_resumen">
        <span id="RDateBirthIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Transferencias_RNationalityIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGNacionalidad2">Nationality</span>
    </td>
    <td class="datos_resumen">
        <span id="RNationalityIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Transferencias_RIdPassportIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGDocumentoIdentidad2">I.D. / Passport Nr.</span>
    </td>
    <td class="datos_resumen">
        <span id="RIdPassportIB" class="TOB_borrarData"></span>
    </td>
</tr>

<tr id="div_Transferencias_RFullNameIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGNombreCompleto2">Full Name</span>
    </td>
    <td class="datos_resumen">
        <span id="RFullNameIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Transferencias_RCountryIncorporationIB" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGPaisIncorporacion2">Country of Incorporation</span>
    </td>
    <td class="datos_resumen">
        <span id="RCountryIncorporationIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen" colspan="2" id="SupportTitle" style="display:none">
        <b><span id="transferencias_TAGSoporteMotivos">Purposes Support</span></b>
    </td>
</tr>
<tr id="div_Transferencias_RarchivoSoporte" style="display: none">
    <td class="titulo_resumen">
        <span id="transferencias_TAGSoporteMotivos2">Files</span>
    </td>
    <td class="datos_resumen">
        <span id="RSupportfiles" class="TOB_borrarData"></span>
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

<div id="sign_up_template_transfer"  style="left: 12%">
    <h3 id="see_id_template_transfer" >Templates Cargados</h3>
    <fieldset style="width:95%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="5%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left">
                    <span class="datosInfo" id="TAG_INFO_POPUP_TMP">You can make transfers through the status templates "Approved", so you will not need the confirmation key or the use of the code card.<br>If the template you would like to use is not "Approved", click on the respective "Approve" button.<br>If you make transfers to accounts not stored in this directory, you must validate the operation with a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
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
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_transfers.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="transferencias_TAGTituloOpe" class="banner__title banner__title--modifier">To other Banks</h2>
            <ul class="banner__nav">
                <li><a id="TagHome7" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" />
                </li>
                <li id="tag_banner_title_transfers">TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" />
                </li>
                <li id="tag_banner_title_to_other_banks">TO OTHER BANKS</li>
            </ul>
            <p id="TAG_INFO_EXTERNA_FI" class="banner__description banner__description--modifier">
                This option enables you to make transfers from your account(s) with
                VBT Bank & Trust to another account(s) at other financial
                institutions.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="transferencias_TAGTituloOpe" class="banner__title banner__title--modifier">To other Banks</h2>
            <ul class="banner__nav">
                <li><a id="TagHome7" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_banner_title_transfers">TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_banner_title_to_other_banks">TO OTHER BANKS</li>
            </ul>
            <p id="TAG_INFO_EXTERNA_FI" class="banner__description banner__description--modifier">
                This option enables you to make transfers from your account(s) with
                VBT Bank & Trust to another account(s) at other financial
                institutions.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="section" id="createToOtherBank">
        <div class="section__container section__container--modifier container">
            <div class="section__top">
                <div class="section__icon">
                    <img src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                </div>
                <div class="section__header">
                    <span id="transferencias_TAGCuentaDebito" class="section__title field-obligatory">From Account</span>
                    <div class="section__info">
                        <p class="balance-date">
                            <span id="transferencias_TAGFechaCierre2">Available Balance as at</span>
                            <span id="tagAvailableBalanceDate"></span>
                        </p>
                        <img id="imprimirCampos_TOBFI" class="section__icon"
                             src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__field">
                        <div class="select-section select-section--form">
                            <select id="Accounts"
                                    title="From Account"
                                    class="select-section__select select-section__select--form obligatorioTOB selectFormulario_TOB obligatorioTOBTMP verificarCancelacion">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                    <input type="button" autocomplete="off" title="Use Template" value="Template" id="tagiTemplate" class="section__button button">
                </div>
            </div>
            <div class="section__content">
                <div class="accordeon">
                    <div id="beneficiaryBankAccordeonBlock" class="accordeon__block">
                        <div class="accordeon__titles">
                            <img id="beneficiaryBankAccordeonStep"  class="accordeon__status img-step" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                                 alt="" />
                            <span id="transferencias_TAGBancoBeneficiario" class="accordeon__title">BENEFICIARY BANK</span>
                            <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                        <div id="beneficiaryBankAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid form-transfer__grid--banks-V2">
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <div class="form-transfer__question">
                                            <label class="label-cursor" id="transferencias_TAGCodigoBanco" for="BankCode2"> BANK CODE</label>
                                            <img src="../vbtonline/resources/img/icons/question-banks.png" alt="">
                                        </div>
                                        <div class="form-transfer__auto1fr">
                                            <div class="select-section select-section--form">
                                                <select id="BankCode"
                                                        title="Beneficiary Bank: Code Type"
                                                        class="select-section__select select-section__select--form selectFormulario_TOB"
                                                        name="account" onchange="validarIntermediario();">
                                                </select>
                                                <img class="select-section__icon select-section__icon--form"
                                                     src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                            <div class="input-container">
                                                <input id="BankCode2" class="input input--form invisible_print inputFormulario_TOB only_alpha_num" type="text"
                                                       onkeypress="return validarn(event)"
                                                       title="Beneficiary Bank: Bank Code" maxlength="20" size="23"
                                                />
                                                <button class="input-container__search"  id="btnCodBancoBuscar" name="btnCodBancoBuscar">
                                                    <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <div class="form-transfer__question">
                                            <label class="label-cursor" id="transferencias_TAGCodigoBancoIBSwift" for="BankCodeSWIFTtext">
                                                SWITF CODE*</label>
                                            <img src="../vbtonline/resources/img/icons/question-banks.png" alt="">
                                        </div>
                                        <div class="input-container">
                                            <input class="input input--form invisible_print inputFormulario_TOB only_alpha_num"
                                                   id="BankCodeSWIFTtext"
                                                   title="Beneficiary Bank: Switf Code" type="text" onkeypress="return validarn(event)" maxlength="20" size="23"/>
                                            <button id="btnCodBancoBuscarSWIFT" class="input-container__search">
                                                <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                            </button>
                                        </div>
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <label class="field-obligatory" id="transferencias_TAGBankName" for="NameBank">BANK NAME*</label>
                                        <input class="input input--form invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more" type="text"
                                               id="NameBank" maxlength="65" size="50"
                                               title="Beneficiary Bank: Bank Name" />
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column"></div>
                                </div>
                                <!-- <div id="tra_ob_tag_fiel_address" class="form-transfer__field form-transfer__fieldtitle2">ADDRESS</div> -->
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGDireccion1" for="AddressLineBank1">Address line 1*</label>
                                            <input id="AddressLineBank1"
                                                   class="input input--form invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more"
                                                   title="Beneficiary Bank: Address Line 1"
                                                   maxlength="50" size="55"
                                                   type="text"/>
                                        </div>
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGDireccion2" for="AddressLineBank2">Address line 2</label>
                                            <input id="AddressLineBank2"
                                                   title="Beneficiary Bank: Address Line 2"
                                                   class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                                   maxlength="50"
                                                   size="55"
                                                   type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <!-- <div class="form-transfer__field"></div> -->
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGDireccionCity" for="AddressLineBank3">City</label>
                                            <input id="AddressLineBank3" class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                                   type="text" maxlength="45"
                                                   size="55" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGPais" for="CountryBank">Select Country</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray obligatorioTOBTMP obligatorioTOB selectFormulario_TOB verificarCancelacion"
                                                        id="CountryBank" name="account" title="Beneficiary Bank: Country">

                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                      <!--       <div class="form-transfer__spacebetween-V2">
                                <span id="tra_ob_tag_span_mandatory_fields"  class="form-transfer__mandaroty">* Mandatory fields</span>
                                <div class="form-transfer__buttons">
                                    <button class="form-transfer__button button button--white"
                                            id="btnBeneficiaryBankAccordeonClear">
                                        CANCEL
                                    </button> 
                                    <button class="form-transfer__button form-transfer__button--next button"
                                            id="btnBeneficiaryBankAccordeonNext">
                                        NEXT
                                    </button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div id="beneficiaryAccordeonBlock" class="accordeon__block">
                        <div class="accordeon__titles">
                            <img id="beneficiaryAccordeonStep" class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                                 alt="" />
                            <span id="transferencias_TAGBeneficiario" class="accordeon__title">BENEFICIARY</span>
                            <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                        <div id="beneficiaryAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGTipoPersona" for="TipoPersona">LEGAL PERSON*</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--blue obligatorioTOB obligatorioTOBTMP selectFormulario_TOB"
                                                        id="TipoPersona" titel="Beneficiary : Legal Person">
                                                    <option value="-2" id="transferencias_tipoPersonaSelect" >Select</option>
                                                    <option value="NAT" id="transferencias_tipoPersonaNatural">Natural</option>
                                                    <option value="JUR" id="transferencias_tipoPersonaJuridica">Jur&iacute;dico</option>
                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_login_language_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                        <div class="form-transfer__field ocultoCampo" name="transferencias_apellidos2">
                                            <label id="transferencias_TAGLastname1" for="lastname1" class="field-obligatory">First surname</label>
                                            <input id="lastname1" class="input input--form only_alpha invisible_print inputFormulario_TOB"
                                                   title="Beneficiary: First Last Name"
                                                   maxlength="65" size="50"
                                                   type="text"/>
                                          </div>
                                    </div>
                                    <div class="form-transfer__item ">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGNombre" for="name">Beneficiary Name*</label>
                                            <input class="input input--form invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more"
                                                   type="text"
                                                   maxlength="65"
                                                   size="50"
                                                   id="name"
                                                   title="Beneficiary : Name" />
                                        </div>
                                        <div name="transferencias_apellidos" class="form-transfer__field ocultoCampo">
                                            <label id="transferencias_TAGLastname2" for="lastname2">Last surname</label>
                                            <input id="lastname2" class="input input--form only_alpha invisible_print inputFormulario_TOB"
                                                   title="Beneficiary: Second Last Name"
                                                   maxlength="65" size="50"
                                                   type="text"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-transfer__grid">
                                    <div class="form-transfer__field">
                                        <label class="label-cursor field-obligatory" id="transferencias_TAGNumeroCuenta" for="AccountBank">ACCOUNT NUMBER*</label>
                                        <div style="display: flex;flex-direction: row;">
                                            <div class="select-section select-section--form">
                                                <select id="AccountBank" class="select-section__select select-section__select--form obligatorioTOB obligatorioTOBTMP selectFormulario_TOB" name="account" style="width:8em">
    
                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                            <input class="input input--form invisible_print obligatorioTOBTMP obligatorioTOB inputFormulario_TOB only_alpha_num"
                                                   title="Beneficiary : Account Number"
                                                   id="AccountNumber" maxlength="30" size="34" type="text"  />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- <div id="tra_ob_tag_bene_fiel_address" class="form-transfer__field form-transfer__fieldtitle2">ADDRESS</div> -->
                            <div class="form-transfer__grid">
                                <div class="form-transfer__item">
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="transferencias_TAGDireccion12" for="AddressLine1">Address line 1*</label>
                                        <input id="AddressLine1"
                                               class="input input--form invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB only_alpha_num_more"
                                               type="text"
                                               title="Beneficiary : Address line 1"
                                               maxlength="50"
                                               size="55" />
                                    </div>
                                    <div class="form-transfer__field">
                                        <label id="transferencias_TAGDireccion22" for="AddressLine2">Address line 2</label>
                                        <input id="AddressLine2" title="Beneficiary: Address Line 2"
                                               class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                               maxlength="50"
                                               size="55"
                                               type="text" />
                                    </div>
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="transferencias_TAGDireccion32" for="AddressLine3">City*</label>
                                        <input id="AddressLine3"
                                               class="input input--form invisible_print obligatorioTOBTMP obligatorioTOB inputFormulario_TOB only_alpha_num_more"
                                               type="text"
                                               maxlength="45"
                                               size="55"
                                               title="Beneficiary: Address Line 3"/>
                                    </div>
                                    <div class="form-transfer__field">
                                        <label id="transferencias_TAGPostalCode" for="BeneficiaryPostalCode">Postal Code</label>
                                        <input id="BeneficiaryPostalCode" class="input input--form invisible_print inputFormulario_TOB" type="text"
                                               maxlength="45" size="55"
                                               title="Beneficiary: Postal Code"
                                               onkeypress=""
                                                />
                                    </div>
                                </div>
                                <div class="form-transfer__item">
                                    <!-- <div class="form-transfer__field"></div> -->
                                    <div class="form-transfer__field">
                                        <label id="transferencias_TAGTelefono" for="TelephoneNumber">Telephone</label>
                                        <input id="TelephoneNumber" class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                               title="Beneficiary: Telephone Number"
                                               maxlength="20" size="25"
                                               type="text" />
                                    </div>
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="transferencias_TAGPais2" for="Country">Select Country*</label>
                                        <div class="select-section select-section--form">
                                            <select
                                                    class="select-section__select select-section__select--form select-section__select--gray obligatorioTOB obligatorioTOBTMP selectFormulario_TOB"
                                                    id="Country"  title="Beneficiary : Select Country">

                                            </select>
                                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="transferencias_TAGEmail" for="beneficiaryEmail">Email*</label>
                                        <input id="beneficiaryEmail" class="input input--form invisible_print obligatorioTOB obligatorioTOBTMP inputFormulario_TOB"
                                               type="email"
                                               maxlength="55" size="35"
                                               title="Beneficiary : Email" />
                                    </div>
                                </div>
                            </div>
<!--                             <div class="form-transfer__spacebetween-V2">
                                <span id="tra_ob_tag_bene_span_mandatory_fields" class="form-transfer__mandaroty">* Mandatory fields</span>
                                <div class="form-transfer__buttons">
                                     <button id="btnBeneficiaryAccordeonClear"
                                            class="form-transfer__button button button--white">
                                        CANCEL
                                    </button> 
                                    <button id="btnBeneficiaryAccordeonNext"
                                            class="form-transfer__button form-transfer__button--next button">
                                        NEXT
                                    </button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div id="form_14" class="accordeon__block" style="display:none">
                        <div class="accordeon__titles">
                            <img id="beneficiaryInformationAccordeonStep" class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png" alt="" />
                            <span id="transferencias_TAGInformacionBeneficiario" class="accordeon__title">Beneficiary Information</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div id="beneficiaryInformationAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid" id="TableIndividual">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGNombreCompletoNatural" for="FullNameIndividualIB">Full Name</label>
                                            <input id="FullNameIndividualIB" class="input input--form inputFormulario_TOB only_alpha obligatorioTOB"
                                                   title="Individual Beneficiary: Full Name"
                                                   maxlength="65"
                                                   size="50"
                                                   type="text"/>
                                        </div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGNacionalidad" for="NationalityIB">Nationalityr</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray selectFormulario_TOB obligatorioTOB"
                                                        id="NationalityIB" title="Individual Beneficiary: Nationality">
                                                </select>
                                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field"></div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGFechaNacimiento" for="DateBirthIB">Date of Birth</label>
                                            <input id="DateBirthIB"
                                                   title="Individual Beneficiary: Date of Birth"
                                                   class="input input--form inputFormulario_TOB obligatorioTOB"
                                                   type="text" placeholder="dd/mm/yyyy" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGDocumentoIdentidad" for="IdPassportIB">I.D. / Passport Nr.</label>
                                            <input id="IdPassportIB" class="input input--form inputFormulario_TOB only_alpha_num_more obligatorioTOB"
                                                   maxlength="20"
                                                   title="Individual Beneficiary: I.D. / Passport Nr."
                                                   size="26"
                                                   type="text" />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-transfer__grid" id="TableCompanies">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">Beneficiary</div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGNombreCompleto" for="FullNameIB">Full Name:</label>
                                            <input id="FullNameIB" class="input input--form inputFormulario_TOB only_alpha_num_more"
                                                   title="Companies Beneficiary: Full Name"
                                                   maxlength="65" size="50"
                                                   type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field"></div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="transferencias_TAGPaisIncorporacion" for="CountryIncorporationIB">Nationalityr</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray selectFormulario_TOB"
                                                        title="Companies Beneficiary: Country of Incorporation"
                                                        id="CountryIncorporationIB">

                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
 <!--                            <div class="form-transfer__spacebetween-V2">
                                <span class="form-transfer__mandaroty"> </span>
                                <div class="form-transfer__buttons">
                                     <button id="btnBeneficiaryInformationAccordeonClear" class="form-transfer__button button button--white">
                                        CANCEL
                                    </button> 
                                    <button id="btnBeneficiaryInformationAccordeonNext" class="form-transfer__button form-transfer__button--next button">
                                        NEXT
                                    </button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div id="intermediaryBankAccordeonBlock" class="accordeon__block">
                        <div class="accordeon__titles">
                            <img id="intermediaryBankAccordeonStep" class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png" alt="" />
                            <span id="transferencias_TAGBancoIntermediario" class="accordeon__title">INTERMEDIARY BANK (IF APPLICABLE)</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div id="intermediaryBankAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid form-transfer__grid--banks-V2">
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <div class="form-transfer__question">
                                            <label class="label-cursor" id="transferencias_TAGCodigoBanco2" for="BankCodeIB2">BANK CODE</label>
                                            <img src="../vbtonline/resources/img/icons/question-banks.png" alt="">
                                        </div>
                                        <div class="form-transfer__auto1fr">
                                            <div class="select-section select-section--form">
                                                <select class="select-section__select select-section__select--form selectFormulario_TOB"
                                                        id="BankCodeIB">
                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                            <div class="input-container">
                                                <input id="BankCodeIB2" class="input input--form invisible_print inputFormulario_TOB only_alpha_num"
                                                       title="Intermediary Bank: Bank Code"
                                                       maxlength="20" size="23"
                                                       type="text" />
                                                <button id="btnCodBancoBuscarIB" name="btnCodBancoBuscar" class="input-container__search">
                                                    <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <div class="form-transfer__question">
                                            <label class="label-cursor" id="transferencias_TAGCodigoBancoSWIFT" for="BankCodeSWIFTtext">
                                                SWITF CODE</label>
                                            <img src="../vbtonline/resources/img/icons/question-banks.png" alt="">
                                        </div>
                                        <div class="input-container">
                                            <input class="input input--form invisible_print inputFormulario_TOB only_alpha_num"
                                                   title="Intermediary Bank: Swift Code"
                                                   id="BankCodeIBSWIFTtext" type="text" onkeypress="return validarn(event)"
                                                   maxlength="20" size="23"
                                            />
                                            <button id="btnCodBancoBuscarIBSWIFT" class="input-container__search">
                                                <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                            </button>
                                        </div>
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <label id="transferencias_TAGBankName2" for="NameBankIB">BANK NAME</label>
                                        <input id="NameBankIB"
                                               class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                               title="Intermediary Bank: Name Bank"
                                               maxlength="65"
                                               size="50"
                                               type="text" />
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column"></div>
                                </div>
                                <!-- <div id="tra_ob_tag_ban_fiel_address" class="form-transfer__field form-transfer__fieldtitle2">ADDRESS</div> -->
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGDireccion13" for="AddressLineBankIB1">Address line 1</label>
                                            <input id="AddressLineBankIB1" title="Intermediary Bank: Address Line 1"
                                                   class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                                   maxlength="50"
                                                   size="55"
                                                   type="text" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGDireccion23" for="AddressLineBankIB2">Address line 2</label>
                                            <input id="AddressLineBankIB2" class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                                   title="Intermediary Bank: Address Line 2"
                                                   maxlength="50"
                                                   size="55"
                                                   type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <!-- <div class="form-transfer__field"></div> -->
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGDireccion33City" for="AddressLineBankIB3">City</label>
                                            <input id="AddressLineBankIB3" class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                                   title="Intermediary Bank: Address Line 3"
                                                   maxlength="45"
                                                   size="55"
                                                   type="text"/>
                                        </div>
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGPais3" for="CountryBankIB">Select Country</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray selectFormulario_TOB"
                                                        id="CountryBankIB">

                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
<!--                             <div class="form-transfer__spacebetween-V2">
                                <span id="tra_ob_tag_ban_span_mandatory_fields" class="form-transfer__mandaroty">* Mandatory fields</span>
                                <div class="form-transfer__buttons">
                    -                 <button id="btnIntermediaryBankAccordeonSkip" class="form-transfer__button button button--white"
                                            data-accordeon-next="data-accordeon-next">
                                        SKIP
                                    </button> 
                                    <button id="btnIntermediaryBankAccordeonNext" class="form-transfer__button form-transfer__button--next button">
                                        NEXT
                                    </button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div id="futherCreditAccordeonBlock" class="accordeon__block">
                        <div class="accordeon__titles">
                            <img id="futherCreditAccordeonStep" class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png" alt="" />
                            <span id="transferencias_TAGParaFuturoCredito" class="accordeon__title">FOR FURTHER CREDIT TO (IF APPLICABLE)</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div id="futherCreditAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <!-- <div id="tra_ob_tag_fiel_nuemro_cuenta" class="form-transfer__field form-transfer__fieldtitle2">ACCOUNT NUMBER</div> -->
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGNumeroCuenta2" for="AccountNumberFFC">Number</label>
                                            <input id="AccountNumberFFC" class="input input--form invisible_print inputFormulario_TOB only_alpha_num"
                                                   title="For Further Credit to: Account Number"
                                                   maxlength="25" size="26"
                                                   type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <!-- <div class="form-transfer__field"></div> -->
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGNombre2" for="NameFFC">Name</label>
                                            <input id="NameFFC" class="input input--form invisible_print inputFormulario_TOB only_alpha_num_more"
                                                   maxlength="65"
                                                   title="For Further Credit to: Name"
                                                   size="50"
                                                   type="text"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
         <!--                    <div class="form-transfer__spacebetween-V2">
                                <span class="form-transfer__mandaroty"> </span>
                                <div class="form-transfer__buttons">
                                     <button id="btnFutherCreditAccordeonSkip" class="form-transfer__button button button--white">
                                        SKIP
                                    </button> 
                                    <button id="btnFutherCreditAccordeonNext" class="form-transfer__button form-transfer__button--next button">
                                        NEXT
                                    </button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div id="amountInstructionsAccordeonBlock" class="accordeon__block">
                        <div class="accordeon__titles">
                            <img id="amountInstructionsAccordeonStep" class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png" alt="" />
                            <span id="transferencias_TAGMontoInstrucciones" class="accordeon__title">AMOUNT & INSTRUCTIONS</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div id="amountInstructionsAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGMonto_2" for="ReceiverInformation" class="form-transfer__field--amount label-cursor field-obligatory">Label</label>
                                            <div style="display: block;">
                                                <input class="input input--form invisible_print obligatorioTOB inputFormulario_TOB" type="text"
                                                       id="AmmountAI" title="Amount & Instructions"
                                                       onblur="this.value=formatCurrency(this.value,true,2,'.');"
                                                       maxlength="15" size="15"
                                                       onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'OB');"/>
                                                <div id="AmountCodMon">
    
                                                </div>
                                            </div>
                                        </div>
                                        <div id="cancelarProductoMostrarTOB" class="form-transfer__field">
                                            <label id="TAGproductoCancelacionTOB" for="productCancelationTOB">Product Cancelation</label>
                                            <input id="productCancelationTOB" class="checkbox-container__check checkboxFormulario_TOBVBT inputFormulario_TOB invisible_print" type="checkbox" />
                                        </div>
                                        <div id="montoComisionMostrar" class="form-transfer__field">
                                            <label id="transferencias_TAGmontoComision">Comision</label>
                                            <span id="montoComision"></span>
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGDescripcion" for="ReceiverInformation">Description</label>
                                            <input id="ReceiverInformation"
                                                   class="input input--form only_alpha_num invisible_print inputFormulario_TOB"
                                                   title="Amount & Instructions: Receiver Information"
                                                   maxlength="60" size="55"
                                                   type="text"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
<!--                             <div class="form-transfer__spacebetween-V2">
                                <span class="form-transfer__mandaroty"> </span>
                                <div class="form-transfer__buttons">
                                     <button id="btnAmountInstructionsAccordeonClear" class="form-transfer__button button button--white">
                                        CANCEL
                                    </button> 
                                    <button id="btnAmountInstructionsAccordeonNext" class="form-transfer__button form-transfer__button--next button">
                                        NEXT
                                    </button>
                                </div>
                            </div> -->
                        </div>
                    </div>
                    <div id="purposePaymentAccordeonBlock" class="accordeon__block">
                        <div class="accordeon__titles">
                            <img id="purposePaymentAccordeonStep" class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png" alt="" />
                            <span id="transferencias_TAGMotivo" class="accordeon__title">PURPOSE OF PAYMENT</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div id="purposePaymentAccordeonContent" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <!-- <label for="Motivos" id="transferencias_TAGMotivo_2" class="form-transfer__field form-transfer__fieldtitle2">
                                    PURPOSE OF TRANSFER
                                </label> -->
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field form-transfer__field--1frauto">
                                            <div class="select-section select-section--form">
                                                <select id="Motivos" title="Motivo de la Transferencia: Motivo" class="select-section__select select-section__select--form obligatorioTOB selectFormulario_TOB"
                                                         name="account">

                                                </select>
                                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <!-- <div class="form-transfer__field"></div> -->
                                        <div class="form-transfer__field">
                                            <label id="transferencias_TAGNote" for="MotivoAI">Note</label>
                                            <input id="MotivoAI" maxlength="60" size="60" class="input input--form invisible_print inputFormulario_TOB" type="text"
                                                   title="Motivo de la Transferencia: Motivo"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div id="TABSoporte3" style="display:none;" class="bank-file">
                                            <label for="fileupload" id="transferencias_TAGSoporteMotivo" class="datos">Documents that supports the
                                                transaction</label>
                                            <div class="upload button">
                                                <span id="TAGFileUpload" class="upload__text">Add File</span>
                                                <input id="fileupload" type="file" accept="application/pdf,image/x-png,image/jpeg,image/jpg" multiple
                                                       title="Motivo de la Transferencia: Soporte" data-url="Transfers_fileUpload.action"
                                                       enctype="multipart/form-data" class="button">
                                            </div>
                                        </div>
                                        <div id="TABSoporte" style="display:none;">
                                            <p id="Transferencias_TAGSoporteMotivos3" class="datos">Allowed format: PDF, JPG, JPEG, PNG <br>Maximum file
                                                    size for upload: 15Mb</p>
                                            <div id="progress" style="width:310px" class="font">
                                                <div class="bar" style="width: 0%;"></div>
                                            </div>
                                            <div class="percent">0%</div>
                                        </div>
                                        <div id="TABSoporte2" style="display:none;">
                                            <div id="transferencias_TAGMsgSoporteMotivo">
                                                <div class="Nofiles" id="transferencias_TAGMsgSoporteMotivo2">
                                                </div>
                                            </div>
                                        </div>
                                        <div id="TABSoporte4" style="display:none;">
                                            <div id="loading">&nbsp;</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-transfer__spacebetween-V2">
                                <span class="form-transfer__mandaroty"> </span>
                                <div class="form-transfer__buttons">
                            <!--         <button id="btnPurposePaymentAccordeonClear" class="form-transfer__button button button--white">
                                        CANCEL
                                    </button> -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="mismoDiaTOB" class="section__checkbox checkbox-container">
                    <div>
                        <label id="TAGTransferenciaMismoDia" class="checkbox-container__label" for="transMismoDia">Product Cancelation</label>
                        <input id="transMismoDia" class="checkbox-container__check" type="checkbox" />
                    </div>
                    <label id="campoobligatorioMD" class="datosCampoObligatorio" style="float: none;">Commission charge starts at $350</label>
                </div>
                <div class="accordeon__spacebetween">
                    <div id="btn-notification-modal-TOB" class="notice">
                        <img class="notice__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png" alt="" />
                        <span id="transferencias_TAGNoticeTOB" class="notice__text">NOTICE</span>
                    </div>
                    <div class="accordeon__buttons">
                        <input type="button" id="btn_TOB_volver" value="Back" class="form-transfer__button button button--white">
                        <input type="button" id="btn_TOB_cancelar" value="CLEAR" class="form-transfer__button button button--white">
                        <input type="button" id="btn_TemplateGuardar_TOB_cancel" value="CANCEL" class="form-transfer__button button button--white">
                        <input type="button" id="btn_TOB_aceptar" value="SEND" class="form-transfer__button form-transfer__button--next button">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="dialog-disclaimer" style="display:none">
        <p align="justify"></p>
    </div>
    <div class="section" id="summaryToOtherBank" style="display: none" >
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                <div class="section__titles"><span id="summary_TOB2" class="section__title">Summary</span>
                    <div class="section__info"><span id="sumaryConfirm_TOB">Confirme sus datos</span><img class="section__icon" src="../vbtonline/resources/img/icons/ic_table_transfer_header_info.png" alt="" /></div>
                </div>
                <div class="section__row section__row--spacebetween"><span id="RAccounts">Savings Account - 0000003276 (Available: 20.400,18 USD) - (Client ID No: 000000165443)</span>
                    <div class="status status--created"> <span class="status__text">CREATED</span><img class="status__icon" src="assets/img//icons/ic_table_transfer_header_status_created.png" alt=""/></div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="table__titles"><span id="transferencias_TAGBancoBeneficiario2" class="table__title">BENEFICIARY BANK</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_RBankCode" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailCodigoBancoBeneficiario" class="table__item table__item--summary"><span>Beneficiary Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="RBankCode"></span></td>
                        </tr>
                        <tr id="div_RBankName" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailNombreBancoBeneficiario" class="table__item table__item--summary"><span>Beneficiary Bank Name</span></td>
                            <td class="table__item table__item--last"><span id="RBankName"></span></td>
                        </tr>
                        <tr id="div_RBankCodeSwift" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailCodigoBancoBeneficiarioSwift" class="table__item table__item--summary"><span>Beneficiary Swift Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="RBankCodeSwift"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryBankAddress1" class="table__item table__item--summary"><span>Beneficiary Bank Address 1</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLineBank1"></span></td>
                        </tr>
                        <tr id="div_RAddressLineBank2" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryBankAddress2" class="table__item table__item--summary"><span>Beneficiary Bank Address 2</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLineBank2"></span></td>
                        </tr>
                        <tr id="div_RAddressLineBank3" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryBankAddress3" class="table__item table__item--summary"><span>Beneficiary Bank Address 3</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLineBank3"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryBankCountry" class="table__item table__item--summary"><span>Beneficiary Bank Country</span></td>
                            <td class="table__item table__item--last"><span id="RCountryBank"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table">
                    <div class="table__titles"><span id="transferencias_TAGNombreBeneficiario2" class="table__title">Beneficiary</span></div>
                    <table class="table__content table__content--summary">
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryName" class="table__item table__item--summary"><span>Beneficiary Name</span></td>
                            <td class="table__item table__item--last"><span id="Rname"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryAccount" class="table__item table__item--summary"><span>Beneficiary Account</span></td>
                            <td class="table__item table__item--last"><span id="RAccountNumber"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailBeneficiario" class="table__item table__item--summary"><span></span></td>
                            <td class="table__item table__item--last"><span id="RbeneficiaryEmail" ></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryAddress1" class="table__item table__item--summary"><span>Beneficiary Address 1</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLine1" ></span></td>
                        </tr>
                        <tr id="div_RAddressLine2" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryAddress2" class="table__item table__item--summary"><span>Beneficiary Address 2</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLine2" ></span></td>
                        </tr>
                        <tr id="div_RAddressLine3" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryAddress3" class="table__item table__item--summary"><span>Beneficiary Address 3</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLine3" ></span></td>
                        </tr>
                        <tr id="div_PostalCode" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGRPostalCode" class="table__item table__item--summary"><span>Postal Code</span></td>
                            <td class="table__item table__item--last"><span id="RBeneficiaryPostalCode"></span></td>
                        </tr>
                        <tr id="div_RTelephoneNumber" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryTelephone" class="table__item table__item--summary"><span>Beneficiary Telephone</span></td>
                            <td class="table__item table__item--last"><span id="RTelephoneNumber"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGBeneficiaryCountry" class="table__item table__item--summary"><span>Beneficiary Country</span></td>
                            <td class="table__item table__item--last"><span id="RCountry"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table" id="div_TOB_resumen_intermediary" style="display: none">
                    <div class="table__titles"><span id="transferencias_TAGIntermediaryBank" class="table__title">Intermediary Bank</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_RBankCodeIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailCodigoBancoIntermediario" class="table__item table__item--summary"><span>Intermediary Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="RBankCodeIB"></span></td>
                        </tr>
                        <tr id="div_RBankCodeIBSwift" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailCodigoBancoIntermediarioSwift" class="table__item table__item--summary"><span>Intermediary Swift Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="RBankCodeIBSwift"></span></td>
                        </tr>
                        <tr id="div_RNameBankIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEmailNombreBancoIntermediario" class="table__item table__item--summary"><span>Intermediary Bank Name</span></td>
                            <td class="table__item table__item--last"><span id="RNameBankIB"></span></td>
                        </tr>
                        <tr id="div_RAddressLineBankIB1" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGIntermediaryBankAddress1" class="table__item table__item--summary"><span>Intermediary Bank Address 1</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLineBankIB1"></span></td>
                        </tr>
                        <tr id="div_RAddressLineBankIB2" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGIntermediaryBankAddress2" class="table__item table__item--summary"><span>Intermediary Bank Address 2</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLineBankIB2"></span></td>
                        </tr>
                        <tr id="div_RAddressLineBankIB3" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGIntermediaryBankAddress3" class="table__item table__item--summary"><span>Intermediary Bank Address 3</span></td>
                            <td class="table__item table__item--last"><span id="RAddressLineBankIB3"></span></td>
                        </tr>
                        <tr id="div_RCountryBankIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGIntermediaryBankCountry" class="table__item table__item--summary"><span>Intermediary Bank Country</span></td>
                            <td class="table__item table__item--last"><span id="RCountryBankIB"></span></td>
                        </tr>
                    </table>
                </div>
                <div id="div_TOB_resumen_furtherCredit" style="display: none" class="table">
                    <div class="table__titles"><span id="transferencias_TAGParaFuturoCredito2" class="table__title">For Further Credit to</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_RAccountNumberFFC" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGFurtherCreditAccount" class="table__item table__item--summary"><span>Further Credit Account</span></td>
                            <td class="table__item table__item--last"><span id="RAccountNumberFFC"></span></td>
                        </tr>
                        <tr id="div_RNameFFC" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGFurtherCreditName" class="table__item table__item--summary"><span>Further Credit Name</span></td>
                            <td class="table__item table__item--last"><span id="RNameFFC"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table">
                    <div class="table__titles"><span id="transferencias_TAGMontoInstrucciones2" class="table__title">Amount & Instructions</span></div>
                    <table class="table__content table__content--summary">
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGMonto2" class="table__item table__item--summary"><span>Amount</span></td>
                            <td class="table__item table__item--last"><span id="RAmmountAI"></span></td>
                        </tr>
                        <tr id="div_Transferencias_productoCancelacionIB" class="table__row table__row--summary">
                            <td id="transferencias_TAGProductoCancelacion" class="table__item table__item--summary"><span>Cancelacion Producto</span></td>
                            <td class="table__item table__item--last"><span id="RProductoCancelacionIB"></span></td>
                        </tr>
                        <tr id="div_Transferencias_mismoDiaIB" class="table__row table__row--summary">
                            <td id="transferencias_TAGmismoDia" class="table__item table__item--summary"><span>Mismo Día</span></td>
                            <td class="table__item table__item--last"><span id="mismoDiaIB" ></span></td>
                        </tr>
                        <tr id="div_RReceiverInformation" style="display: none"class="table__row table__row--summary">
                            <td id="transferencias_TAGConcepto" class="table__item table__item--summary"><span>Description</span></td>
                            <td class="table__item table__item--last"><span id="RReceiverInformation"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="transferencias_TAGMotivo_3" class="table__item table__item--summary"><span>Motivo</span></td>
                            <td class="table__item table__item--last"><span id="RMotivoAI"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table">
                    <div class="table__titles"><span id="transferencias_TAGInformacionBeneficiario2" class="table__title">Beneficiary Information</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_Transferencias_RFullNameIndividualIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGNombreCompletoNatural2" class="table__item table__item--summary"><span>Full Name</span></td>
                            <td class="table__item table__item--last"><span id="RFullNameIndividualIB"></span></td>
                        </tr>
                        <tr id="div_Transferencias_RDateBirthIB"  style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGFechaNacimiento2" class="table__item table__item--summary"><span>Date of Birth</span></td>
                            <td class="table__item table__item--last"><span id="RDateBirthIB"></span></td>
                        </tr>
                        <tr id="div_Transferencias_RNationalityIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGNacionalidad2" class="table__item table__item--summary"><span>Nationality</span></td>
                            <td class="table__item table__item--last"><span id="RNationalityIB"></span></td>
                        </tr>
                        <tr id="div_Transferencias_RIdPassportIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGDocumentoIdentidad2" class="table__item table__item--summary"><span>I.D. / Passport Nr.</span></td>
                            <td class="table__item table__item--last"><span id="RIdPassportIB"></span></td>
                        </tr>
                        <tr id="div_Transferencias_RFullNameIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGNombreCompleto2" class="table__item table__item--summary"><span>Full Name</span></td>
                            <td class="table__item table__item--last"><span id="RFullNameIB"></span></td>
                        </tr>
                        <tr id="div_Transferencias_RCountryIncorporationIB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGPaisIncorporacion2" class="table__item table__item--summary"><span>Country of Incorporation</span></td>
                            <td class="table__item table__item--last"><span id="RCountryIncorporationIB"></span></td>
                        </tr>
                    </table>
                </div>
                <div id="SupportTitle" style="display:none" class="table">
                    <div class="table__titles"><span id="transferencias_TAGSoporteMotivos" class="table__title">Purposes Support</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_Transferencias_RarchivoSoporte" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGSoporteMotivos2" class="table__item table__item--summary"><span>Files</span></td>
                            <td class="table__item table__item--last"><span id="RSupportfiles"></span></td>
                        </tr>
                        <tr id="div_estatus_TOB" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGEstatusTransferencia" class="table__item table__item--summary"><span>Files</span></td>
                            <td class="table__item table__item--last"><span id="status_TOB"></span></td>
                        </tr>
                    </table>
                 </div>
                <div id="resumenBotones_TOB" class="table__spacebetween table__spacebetween--top">
                    <div></div>
                    <div class="table__buttons">
                        <button id="btn__resumen_cancelar" class="table__button button button--white">BACK</button>
                        <button id="btn__resumen_aceptar"  class="table__button button">SEND</button>
                    </div>
                </div>
                <div id="resumenBotones_TOB_Finales" style="display: none" class="table__spacebetween table__spacebetween--top invisible_print">
                    <div></div>
                    <div class="table__buttons">
                        <button id="btn_imprimir_TOB_final" class="table__button button button--white">PRINT</button>
                        <button id="btn_finalizar_TOB_final"   class="table__button button">Make Another Transfer</button>
                        <button id="btn_TemplateGuardar_TOB_final"  class="table__button button">Save as Template</button>
                        <button id="btn_ok_TOB_final" class="table__button button">OK</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="section" id="noCreateToOtherBank" style="display: none" >
        <div class="section__container container">
            <div class="section__content">
                <div class="not-aviable">
                    <span id="noInfo_noCreateToOtherBank"></span>
                </div>
            </div>
        </div>
    </div>

<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<div class="form-modal invisible_print container" id="sign_up_search">
    <div class="form-modal__top">
        <img class="form-modal__icon" src="../vbtonline/resources/img/icons/ic_templates_saved_header_search.png" alt="" />
        <span id="see_id" class="form-modal__title">Bank Code</span>
    </div>
    <div id="sign_up_form" class="form-modal__content">
        <div class="form-transfer">
            <div class="form-transfer__grid form-transfer__grid--nomargin">
                <div class="form-transfer__item">
                    <div class="form-transfer__field"></div>
                    <div class="form-transfer__field">
                        <label id="tag_buscarBanco_codetype" for="BankCodeType_buscar">Bank Code Type</label>
                        <div class="select-section select-section--form">
                            <select id="BankCodeType_buscar" class="select-section__select select-section__select--form selectFormulario_buscar" name="account">
                            </select><img class="select-section__icon select-section__icon--form"
                                          src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                    <div class="form-transfer__field">
                        <label id="tag_buscarBanco_nombre" for="BankName_buscar">Bank Name</label>
                        <input id="BankName_buscar" class="input input--form obligatorioBuscar inputFormulario_buscar" type="text"/>
                    </div>
                </div>
                <div class="form-transfer__item">
                    <div class="form-transfer__field"></div>
                    <div class="form-transfer__field">
                        <label id="tag_buscarBanco_code" for="BankCode_buscar">Bank Code</label>
                        <input id="BankCode_buscar" class="input input--form obligatorioBuscar inputFormulario_buscar" type="text"/>
                    </div>
                    <div class="form-transfer__field">
                        <label id="tag_buscarBanco_Pais" for="BankCountry_buscar">Select Country</label>
                        <div class="select-section select-section--form">
                            <select  id="BankCountry_buscar"class="select-section__select select-section__select--form selectFormulario_buscar" name="account">
                            </select><img class="select-section__icon select-section__icon--form"
                                          src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-modal__spacebetween form-modal__spacebetween--border">
                <div></div>
                <div class="form-modal__buttons">
                    <input type='button' id="cancel_FC" href="#" value="Clear" class="form-modal__button button button--white"/>
                    <input type='button' id="buscar_FC" href="#" value="Search" class="form-modal__button button"/>
                </div>
            </div>
            <div class="table table--nomargin">
                <table id="tabla_consulta_bancos" class="table__content">
                </table>
            </div>
        </div>
    </div>
</div>
<div class="form-modal invisible_print container" id="sign_up_template_transfer">
    <div class="form-modal__top">
        <img class="form-modal__icon" src="../vbtonline/resources/img/icons/ic_templates_saved_header_search.png" alt="" />
        <span id="see_id_template_transfer" class="form-modal__title">Templates</span>
    </div>
    <div class="form-modal__content">
        <div class="form-transfer">
            <div class="table table--nomargin">
                <span id="TAG_INFO_POPUP_TMP"></span>
                <table id="tabla_Templates_consulta_transfer" class="table__content"></table>
            </div>
        </div>
    </div>
</div>

<div class="notification-modal" id="notification-modal-TOB">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png"
                                              alt=""/>
        <span id="transferencias_TAGAvisosImportantes_2" class="notification-modal__title">IMPORTANT NOTICE</span></div>
    <div class="notification-modal__content">
        <div id="transferencias_TAGMsgDisclaimerExternas_2" class="notification-modal__descriptions">
        </div>
<!--         <div id="transferencias_TAGMsgDisclaimerExternas_1_Step_2_5" class="notification-modal__descriptions">
        </div>
        <div id="transferencias_TAGMsgDisclaimerExternas_1_Step_3_5" class="notification-modal__descriptions" style="display: none;">
        </div> -->
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
<%--<jsp:include page="../../footer.jsp" />--%>
<script>
/*    const btnTransfersBank = document.getElementById("transfers-bank-");
    const btnTransfersBankCancel = document.getElementById("transfers-bank-cancel");
    const formTransferBank = document.getElementById("formTransferBank");

    if (btnTransfersBank && formTransferBank) {
        btnTransfersBank.addEventListener("click", (e) => {
            formTransferBank.reset();
        });
    }

    if (btnTransfersBankCancel) {
        btnTransfersBankCancel.addEventListener("click", (e) => {
            alert("Cancelando...");
        });
    }

    $("#btnValidacion").click(function () {
        var mensaje="";

        $(".obligatorioTOB").each(function () {
            if($.trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                mensaje = mensaje + "Campo requerido - " + this.title + "<br>";
                $("#" + this.id).addClass("input--error");
            } else {
                if ($("#" + this.id).hasClass("input--error"))
                    $("#" + this.id).removeClass("input--error");
            }
        });
    });*/
$("#btn-notification-modal-TOB").click(function (){
    $("#notification-modal-TOB").modal({
        showClose: !1,
        modalClass: "notification-modal",
        fadeDuration: 100,
        blockerClass: "notification-modal--blocker",
    });

    
});
</script>

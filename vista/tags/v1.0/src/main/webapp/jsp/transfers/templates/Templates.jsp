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
    <h1 id="directorio_TAGTituloOpe">Transferencias / Directorio Personal </h1>
</div>
<div id="Template_consulta">
    <fieldset class="div_consulta">
        <label id="directorio_TAGMsgDescDirectorio"  class="datos">This option allows you to dispose in the future of a personal transaction list (Templates) which includes your frequently used beneficiaries and its data for more convenience transferring amounts with VBT Online.<br><br>
        To add a new item to the list click <b>Add</b>. To <b>Change</b> or <b>Remove</b>, click on the respective template name.
        </label>
    </fieldset>
    <fieldset class="div_consulta 0000000070_1">
        <%--<input  type="button" id="Template_back" value="Back" class="boton">--%>
        <input  type="button" id="Template_add" value="Add" class="botonDerecha">
    </fieldset>
    <fieldset class="div_consulta">
        <div id="div_tabla_Templates_consulta" class="div_tabla_consulta">
        </div>
    </fieldset>

</div>
<div id="Template_createTemplate" style="display: none">
<fieldset id="Template_form_info" class="formulario_fieldsetTransfer div_consultaTransfers">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>

            <td>
                <label id="transferencias_TAGMsgDescAddTemplate"  class="datos">This data will be included in your personal template list, for you to dispose of this data next time you would like to transfer to the same Beneficiary.
                </label>

            </td>

        </tr>
        <tr>
            <td>
            <label id="comun_TAGPasosAddTemplate"  class="datos">
                <b>Steps to create a template item in your personal transaction list:</b><br>
                1. Indicate a name or alias to identify the record.<br>
                2. Complete the data.<br>
                3. Click "Save Template", and the template item will be created automatically.<br>
            </label>
            </td>
        </tr>

    </table>

</fieldset>

<fieldset id="Template_form_1" class="formulario_fieldsetTransfer div_consultaTransfers">


    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>

            <td>
                <label for="Template_nombreTemplate" id="comun_TAGTemplateName"  class="datos">Template Name</label><span class="datos">:</span>
                <input id="Template_nombreTemplate" type="text" class="obligatorio_Template inputFormulario_templateAdd" title="Name of Template"  style="width: 300px"/>
                <label class="datos6" >  * </label>
            </td>

        </tr>

    </table>

</fieldset>
<fieldset id="Template_form_3" class=" div_consulta fieldset_BeneficiaryBank" >
    <legend > 1 - <span id="Template_TAGBancoBeneficiario">Beneficiary Bank </span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="Template_BankCode" id="template_TAGCodigoBanco"  class="negrita datos">Bank Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_BankCode" class="selectFormulario_templateAdd" title="Beneficiary Bank: Code Type" style="width: 97px" onchange="validarIntermediarioTemplate();" >

                </select>
                <input id="Template_BankCode2" type="text" style="width: 178px" class="inputFormulario_templateAdd" title="Beneficiary Bank: Bank Code" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscar" name="btnCodBancoBuscar" value="..." tabindex="12"> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_SwiftBankCode2" id="template_SwiftTAGCodigoBanco" class="datos">Swift Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_SwiftBankCode2" type="text" style="width: 275px" class="inputFormulario_templateAdd" title="Beneficiary Swift Bank: Bank Code" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscarSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_NameBank" id="template_TAGBankName"  class="datos">Bank Name</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_NameBank" type="text"  style="width: 300px" class="obligatorio_Template inputFormulario_templateAdd" title="Beneficiary Bank: Name Bank" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBank1" id="template_TAGDireccion1"  class="datos">Address Line 1</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBank1" type="text"  style="width: 300px" class="obligatorio_Template inputFormulario_templateAdd" title="Beneficiary Bank: Address Line 1" maxlength="50" size="55"/> <label class="datos6" >  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBank2" id="template_TAGDireccion2"  class="datos">Address Line 2</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBank2" type="text"  style="width: 300px" title="Beneficiary Bank: Address Line 2" class="inputFormulario_templateAdd" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBank3" id="template_TAGDireccion3"  class="datos ">Address Line 3</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBank3" type="text"  style="width: 300px" title="Beneficiary Bank: Address Line 3" class="inputFormulario_templateAdd" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_CountryBank" id="template_TAGPais"  class="datos">Country</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_CountryBank" class="obligatorio_Template selectFormulario_templateAdd" title="Beneficiary Bank: Country"  style="width: 300px" >

                </select>
                <label class="datos6" >  * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="Template_form_2" class=" div_consulta fieldset_Beneficiary" >

    <legend>2 -  <span id="template_TAGBeneficiario">Beneficiary </span></legend>
    <table SUMMARY='tabla 'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            &nbsp;
        </tr>
        <tr>
            &nbsp;
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_name" id="template_TAGNombre"  class="datos">Name</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_name" title="Beneficiary:  " type="text" class="obligatorio_Template inputFormulario_templateAdd"  style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AccountBank"  id="template_TAGNumeroCuenta" class="datos negrita">Account Number</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_AccountBank" title="Beneficiary: Account Type" class="obligatorio_Template selectFormulario_templateAdd" style="width: 60px">

                </select>
                <input id="Template_AccountNumber" title="Beneficiary: Account Number" type="text" class="obligatorio_Template inputFormulario_templateAdd" style="width: 236px" maxlength="30" size="34"/><label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_beneficiaryEmail" id="template_TAGEmail" class="datos">Email</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_beneficiaryEmail" title="Beneficiary: Email" type="text" class="obligatorio_Template inputFormulario_templateAdd" style="width: 300px" maxlength="55" size="35"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AddressLine1" id="template_TAGDireccion12"  class="datos">Address Line 1</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLine1" type="text" title="Beneficiary: Address Line 1" class="obligatorio_Template inputFormulario_templateAdd" style="width: 300px" maxlength="50" size="55"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AddressLine2" id="template_TAGDireccion22"  class="datos">Address Line 2</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLine2" type="text" title="Beneficiary: Address Line 2" class="inputFormulario_templateAdd" style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AddressLine3" id="template_TAGDireccion32"  class="datos">Address Line 3</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLine3" type="text" title="Beneficiary: Address Line 2" class="inputFormulario_templateAdd" style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_TelephoneNumber" id="template_TAGTelefono"  class="datos">Telephone Number</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_TelephoneNumber" type="text" title="Beneficiary: Telephone Number" class="inputFormulario_templateAdd" style="width: 300px" maxlength="20" size="25"/>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_Country" id="template_TAGPais2"  class="datos">Country</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_Country" title="Beneficiary: Country" class="obligatorio_Template selectFormulario_templateAdd" style="width: 300px" >

                </select> <label class="datos6">  * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="Template_form_4" class=" div_consulta fieldset_IntermediaryBank" >
    <legend>3 -  <span id="template_TAGBancoIntermediario">Intermediary Bank (if applicable)</span></legend>

    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="30%">
                <label for="Template_BankCodeIB" id="template_TAGCodigoBanco2"  class="datos">Bank Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_BankCodeIB" title="Intermediary Bank: Bank Code Type" class="selectFormulario_templateAdd" style="width: 97px">

                </select>
                <input id="Template_BankCodeIB2" type="text" title="Intermediary Bank: Bank Code" class="inputFormulario_templateAdd" style="width: 178px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscarIB" name="btnCodBancoBuscar" value="..." tabindex="12">
                <label id="Template_BankCodeIB2OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_SwiftBankCodeIB2" id="template_SwiftTAGCodigoBanco2"  class="datos">Swift Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_SwiftBankCodeIB2" type="text" title="Intermediary Swift Bank: Bank Code" class="inputFormulario_templateAdd" style="width: 275px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscarIBSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_NameBankIB" id="template_TAGBankName2"  class="datos">Name Bank</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_NameBankIB" type="text" title="Intermediary Bank: Name Bank" class="inputFormulario_templateAdd"  style="width: 300px" maxlength="65" size="50"/>
                <label id="Template_NameBankIBOB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBankIB1" id="template_TAGDireccion13"  class="datos">Address Line 1</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBankIB1" type="text" title="Intermediary Bank: Address Line 1" class="inputFormulario_templateAdd"  style="width: 300px" maxlength="50" size="55"/>
                <label id="Template_AddressLineBankIB1OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBankIB2" id="template_TAGDireccion23"  class="datos" >Address Line 2</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBankIB2" type="text" title="Intermediary Bank: Address Line 2" class="inputFormulario_templateAdd"  style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBankIB3" id="template_TAGDireccion33"  class="datos">Address Line 3</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBankIB3" type="text" title="Intermediary Bank: Address Line 3" class="inputFormulario_templateAdd"  style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_CountryBankIB" id="template_TAGPais3"  class="datos">Country</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_CountryBankIB" title="Intermediary Bank: Country" class="selectFormulario_templateAdd"  style="width: 300px">

                </select>
                <label id="Template_CountryBankIBOB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
    </table>
</fieldset>

<fieldset id="Template_form_5" class=" div_consulta fieldset_ForFurtherCredit" >
    <legend>4 - <span id="template_TAGParaFuturoCredito">For Further Credit to (if applicable) </span></legend>
    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
        <tr>
            <td width="35%">
                <label for="Template_AccountNumberFFC" id="template_TAGNumeroCuenta2"  class="datos">Account Number</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AccountNumberFFC" type="text" title="For Further Credit to: Account Number" class="inputFormulario_templateAdd"  style="width: 300px" maxlength="20" size="26"/>
            </td>
        </tr>
        <tr>
            <td width="35%">
                <label for="Template_NameFFC" id="template_TAGNombre2"  class="datos">Name</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_NameFFC" type="text" title="For Further Credit to: Name" class="inputFormulario_templateAdd"  style="width: 300px" maxlength="65" size="50"/>
            </td>
        </tr>

    </table>
</fieldset>

<br>
<fieldset id="Templateform_7" class=" div_consultaTransfers right"  style="display:none">
    <table align="right" cellspacing="0">
        <tr>
            <td><input  type="button" id="Template_btn_back" value="Back" class="boton"></td>
            <td><input  type="button" id="Template_btn_TOB_borrar" value="Delete" class="boton 0000000070_3"> </td>
            <td><input  type="button" id="Template_btn_TOB_cancelar" value="Cancel" class="boton">   </td>
            <td><input  type="button" id="Template_btn_TOB_aceptar" value="Save Template" class="botonGrande 0000000070_2"> </td>
            <td> <div id="btnAproveTemplate"><input  type="button" id="Template_btn_TOB_aprobar" value="Approve" class="botonGrande 0000000070_6 oculto"></div></td>
           <td> <div id="btnMakeTransfers"><input  type="button" id="Template_btn_transferencia" value="Make a Transfer" class="botonGrande 0000000040_0"></div></td>
        </tr>
    </table>






</fieldset>
<input type="hidden" id="templateID" value="">
<input type="hidden" id="accionTemplate" value="">

</div>
<div id="summaryTemplate" style="display: none" >
<div class="titulo_resume">
    <span id="Template_summary_TOB">Summary</span>
    <div class="descripcion_resume">
        <span id="Template_sumaryConfirm_TOB">Confirm the Data of the Template<br></span>
    </div>
</div>

<div class="botones_resumeTransfer">

<table SUMMARY='tabla'cellpadding="4" cellspacing="1" class="tabla_resumen">
<tbody>
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="template_TAGBancoBeneficiario2">Beneficiary Bank</span></b>
    </td>
</tr>

<tr id="div_RBeneficiaryBankCode" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGEmailCodigoBancoBeneficiario">Beneficiary Bank Code</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RBankCode" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_RBeneficiarySwiftBankCode" style="display: none">
    <td class="titulo_resumen">
        <span >Beneficiary Swift Bank Code</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RSwiftBankCode" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGEmailNombreBancoBeneficiario">Beneficiary Bank Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RNameBank" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryBankAddress1">Beneficiary Bank Address 1</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLineBank1" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLineBank2" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryBankAddress2">Beneficiary Bank Address 2</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLineBank2"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLineBank3" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryBankAddress3">Beneficiary Bank Address 3</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLineBank3" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryBankCountry">Beneficiary Bank Country</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RCountryBank" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="template_TAGNombreBeneficiario2">Beneficiary</span></b>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryName">Beneficiary Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_Rname" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryAccount">Beneficiary Account</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAccountNumber" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGEmailBeneficiario"></span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RbeneficiaryEmail" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryAddress1">Beneficiary Address 1</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLine1" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLine2" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryAddress2">Beneficiary Address 2</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLine2" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLine3" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryAddress3">Beneficiary Address 3</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLine3" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RTelephoneNumber" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryTelephone">Beneficiary Telephone</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RTelephoneNumber" class="TOB_borrarData"></span>
    </td>
</tr>
<tr>
    <td class="titulo_resumen">
        <span id="template_TAGBeneficiaryCountry">Beneficiary Country</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RCountry" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_resumen_intermediary" style="display: none">
    <td class="titulo_resumen" colspan="2">
        <b><span id="template_TAGIntermediaryBank">Intermediary Bank</span></b>
    </td>
</tr>
<tr id="div_Template_RBankCodeIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGEmailCodigoBancoIntermediario">Intermediary Bank Code</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RBankCodeIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RSwiftBankCodeIB" style="display: none">
    <td class="titulo_resumen">
        <span >Intermediary Swift Bank Code</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RSwiftBankCodeIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RNameBankIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGEmailNombreBancoIntermediario"> Intermediary Bank Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RNameBankIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLineBankIB1" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGIntermediaryBankAddress1">Intermediary Bank Address 1</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLineBankIB1" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLineBankIB2" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGIntermediaryBankAddress2">Intermediary Bank Address 2</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLineBankIB2" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RAddressLineBankIB3" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGIntermediaryBankAddress3">Intermediary Bank Address 3</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAddressLineBankIB3" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RCountryBankIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGIntermediaryBankCountry"> Intermediary Bank Country</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RCountryBankIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_resumen_furtherCredit" style="display: none">
    <td class="titulo_resumen" colspan="2">
        <b><span id="template_TAGParaFuturoCredito2">For Further Credit to</span></b>
    </td>
</tr>
<tr id="div_Template_RAccountNumberFFC" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGFurtherCreditAccount">Further Credit Account</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RAccountNumberFFC" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RNameFFC" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGFurtherCreditName">Further Credit Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RNameFFC" class="TOB_borrarData"></span>
    </td>
</tr>

<tr>
    <td class="datos" colspan="2">
        <input type="button" id="btn_resumen_aceptar_template" value="Accept" class="boton">
        <input type="button" id="btn_resumen_cancelar_template" value="Cancel" class="boton">
    </td>
</tr>
</tbody>
</table>
</div>

</div>
<div id="sign_up_template">
    <h3 id="see_id_template" >Buscar Código de Banco</h3>
    <div id="sign_up_form_template">
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td>
                    <label id="buscar_bankcodetype"><strong>Bank Code Type:</strong>  </label>

                </td>
                <td>
                    <select id="Template_BankCodeType_buscar" class="selectFormulario_buscar" title="Beneficiary Bank: Code Type" > </select>
                </td>
                <td>
                    <label id="buscar_bankcode"><strong>Bank Code:</strong></label>
                </td>
                <td>
                    <input id="Template_BankCode_buscar" type="text" class="obligatorioBuscar inputFormulario_buscar"  title="Beneficiary Bank: Bank Code"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="buscar_bankname"><strong>Bank Name:</strong></label>

                </td>
                <td>
                    <input id="Template_BankName_buscar" type="text" class="obligatorioBuscar inputFormulario_buscar"  title="Beneficiary Bank: Bank Name"/>
                </td>

                <td>
                    <label id="buscar_bankcountry"><strong>Country:</strong> </label>

                </td>
                <td>
                    <select id="Template_BankCountry_buscar" class="selectFormulario_buscar" title="Country" > </select>
                </td>
            </tr>
        </table>

        <div id="actions">
            <input type='button' id="Template_cancel" href="#" value="Cancelar" class="boton"/>
            <input type='button' id="Template_buscar" href="#" value="Buscar" class="boton"/>
        </div>
    </div>
    <div id="div_carga_espera_template" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='40px' alt="" /></div>
    <div id="buscarBanco_div_tabla_consulta_bancos_template" class="result"></div>
    <div id="paginacion_buscarBanco_consulta_bancos_template" class="result"></div>
    <a id="close_x" class="close sprited" href="#">close</a>

    <div id="div_mensajes_info_desc" class="info_descp oculto">
        <div id="mensajes_info_desc">
            error
        </div>
        <div id="cerrar_div_mensajes_info_desc">[X]</div>
    </div>
</div>



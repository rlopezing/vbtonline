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
<%--<div>
    <h1 id="directorio_TAGTituloOpe">Transferencias / Directorio Personal </h1>
</div>
<div id="Template_consulta">
    &lt;%&ndash;<fieldset class="div_consulta">&ndash;%&gt;
        &lt;%&ndash;<label id="directorio_TAGMsgDescDirectorio"  class="datos">This option allows you to dispose in the future of a personal transaction list (Templates) which includes your frequently used beneficiaries and its data for more convenience transferring amounts with VBT Online.<br><br>&ndash;%&gt;
        &lt;%&ndash;To add a new item to the list click <b>Add</b>. To <b>Change</b> or <b>Remove</b>, click on the respective template name.&ndash;%&gt;
        &lt;%&ndash;</label>&ndash;%&gt;
    &lt;%&ndash;</fieldset>&ndash;%&gt;

    <fieldset style="width:100%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="3%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left">
                    <span class="datosInfo" id="TAG_INFO_TEMPLATE_PRINCIPAL">This option allows you to dispose in the future of a personal transaction list (Templates) which includes your frequently used beneficiaries and its data for more convenience transferring amounts with VBT Online.<br><br>
        To add a new item to the list click <b>Add</b>. To <b>Change</b> or <b>Remove</b>, click on the respective template name.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset class="div_consulta 0000000070_1">
        &lt;%&ndash;<input  type="button" id="Template_back" value="Back" class="boton">&ndash;%&gt;
        <input  type="button" id="Template_add" value="Add" class="botonDerecha">
    </fieldset>
    <fieldset class="div_consulta">
        <div id="div_tabla_Templates_consulta" class="div_tabla_consulta">
        </div>
    </fieldset>

</div>
<div id="Template_createTemplate" style="display: none">
&lt;%&ndash;<fieldset id="Template_form_info" class="formulario_fieldsetTransfer div_consultaTransfers">&ndash;%&gt;
    &lt;%&ndash;<table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;

            &lt;%&ndash;<td>&ndash;%&gt;
                &lt;%&ndash;<label id="transferencias_TAGMsgDescAddTemplate"  class="datos">This data will be included in your personal template list, for you to dispose of this data next time you would like to transfer to the same Beneficiary.&ndash;%&gt;
                &lt;%&ndash;</label>&ndash;%&gt;

            &lt;%&ndash;</td>&ndash;%&gt;

        &lt;%&ndash;</tr>&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<td>&ndash;%&gt;
            &lt;%&ndash;<label id="comun_TAGPasosAddTemplate"  class="datos">&ndash;%&gt;
                &lt;%&ndash;<b>Steps to create a template item in your personal transaction list:</b><br>&ndash;%&gt;
                &lt;%&ndash;1. Indicate a name or alias to identify the record.<br>&ndash;%&gt;
                &lt;%&ndash;2. Complete the data.<br>&ndash;%&gt;
                &lt;%&ndash;3. Click "Save Template", and the template item will be created automatically.<br>&ndash;%&gt;
            &lt;%&ndash;</label>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
        &lt;%&ndash;</tr>&ndash;%&gt;

    &lt;%&ndash;</table>&ndash;%&gt;

&lt;%&ndash;</fieldset>&ndash;%&gt;
<fieldset class="invisible_print div_info oculto" style="width:92%; margin-left:3%;margin-right:auto;float:none;" id="TAG_INFO_TEMPLATE_ADD">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="5%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <table>
                    <tr>
                        <td>
                            <span id="TAG_INFO_TMP_ADD" class="datosInfo">The following data will be saved in your Personal Transfers Directory for future use of the same transfers.</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span id="TAG_INFO_TMP_ADD2" class="datosInfo"><b>Steps to create an item in the list of your Personal Transfers Directory:</b></span>
                            <br>
                            <span id="TAG_INFO_TMP_ADD3" class="datosInfo">1.	Include a name or alias to identify the record.<br>2.	Enter template data.<br>3.	Click on 'Save Template'.<br>4.	Follow up the template approval process in order to use it in your transfers.</span>
                            <br>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>

<fieldset class="invisible_print div_info oculto" style="width:92%; margin-left:3%;margin-right:auto;float:none;" id="TAG_INFO_TEMPLATE_EDIT">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="5%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <table>
                    <tr>
                        <td>
                            <span id="TAG_INFO_TMP_EDIT" class="datosInfo">The following data will be saved in your Personal Transfers Directory for future use of the same transfers.</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span id="TAG_INFO_TMP_EDIT2" class="datosInfo"><b>Steps to modify  an item in the list of your Personal Transfers Directory:</b></span>
                            <br>
                            <span id="TAG_INFO_TMP_EDIT3" class="datosInfo">1.	Include a name or alias to identify the record.<br>2.	Enter template data.<br>3.	Click on 'Save Template'.<br>4.	Follow up the template approval process in order to use it in your transfers.</span>
                            <br>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        </tbody>
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
                <input id="Template_BankCode2" type="text" style="width: 178px" class="inputFormulario_templateAdd only_alpha_num" title="Beneficiary Bank: Bank Code" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscar" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
               <label for="Template_SwiftBankCode2" id="template_SwiftTAGCodigoBanco" class="datos">Swift Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
               <input id="Template_SwiftBankCode2" type="text" style="width: 275px" class="inputFormulario_templateAdd only_alpha_num" title="Beneficiary Swift Bank: Bank Code" onkeypress="return validarn(event)" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscarSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="100%">
                <input id="Template_NameBank" type="text"  style="width: 300px" class="obligatorio_Template inputFormulario_templateAdd only_alpha_num_more" title="Beneficiary Bank: Name Bank" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
            <td width="30%">
                <label for="Template_NameBank" id="template_TAGBankName"  class="datos">Bank Name</label><span class="datos">:</span>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBank1" id="template_TAGDireccion1"  class="datos">Address Line 1</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBank1" type="text"  style="width: 300px" class="obligatorio_Template inputFormulario_templateAdd  only_alpha_num_more" title="Beneficiary Bank: Address Line 1" maxlength="50" size="55"/> <label class="datos6" >  * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBank2" id="template_TAGDireccion2"  class="datos">Address Line 2</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBank2" type="text"  style="width: 300px" title="Beneficiary Bank: Address Line 2" class="inputFormulario_templateAdd only_alpha_num_more" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBank3" id="template_TAGDireccion3"  class="datos ">City</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBank3" type="text"  style="width: 300px" title="Beneficiary Bank: City" class=" inputFormulario_templateAdd only_alpha_num_more" maxlength="45" size="55"/>
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
            <td width="23%">
                <label for="Template_TipoPersona" id="Template_transferencias_TAGTipoPersona"  class="datos">Name</label>:
            </td>
            <td width="70%">
                <span id="Template_tipoPersona_print" class="datos visible_print"></span>
                <select id="Template_TipoPersona" title="Beneficiary: Type" class="inputFormulario_templateAdd obligatorio_Template selectFormulario_TOB"  >
                    <option value="-2" id="Template_transferencias_tipoPersonaSelect" >Select</option>
                    <option value="NAT" id="Template_transferencias_tipoPersonaNatural">Natural</option>
                    <option value="JUR" id="Template_transferencias_tipoPersonaJuridica">Jur&iacute;dico</option>
                </select>

            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_name" id="template_TAGNombre"  class="datos">Name</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_name" title="Beneficiary:  " type="text" class="obligatorio_Template inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>


        <tr name="Template_transferencias_apellidos" class="oculto">
            <td width="23%">
                <label for="Template_lastname1" id="Template_transferencias_TAGLastname1"  class="datos">First Last Name</label>:
            </td>
            <td width="70%">
                <span id="Template_lastname1_print" class="datos visible_print"></span>
                <input id="Template_lastname1" title="Beneficiary: First Last Name" type="text" class="inputFormulario_templateAdd only_alpha invisible_print inputFormulario_TOB " style="width: 300px" maxlength="65" size="50"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr name="Template_transferencias_apellidos" class="oculto">
            <td width="23%">
                <label for="Template_lastname2" id="Template_transferencias_TAGLastname2"  class="datos">Second Last Name</label>:
            </td>
            <td width="70%">
                <span id="Template_lastname2_print" class="datos visible_print"></span>
                <input id="Template_lastname2" title="Beneficiary: Second Last Name" type="text" class="inputFormulario_templateAdd only_alpha invisible_print inputFormulario_TOB" style="width: 300px" maxlength="65" size="50"/>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AccountBank"  id="template_TAGNumeroCuenta" class="datos negrita">Account Number</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <select id="Template_AccountBank" title="Beneficiary: Account Type" class="obligatorio_Template selectFormulario_templateAdd " style="width: 60px">

                </select>
                <input id="Template_AccountNumber" title="Beneficiary: Account Number" type="text" class="obligatorio_Template inputFormulario_templateAdd  only_alpha_num" style="width: 236px" maxlength="30" size="34"/><label class="datos6">  * </label>
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
                <input id="Template_AddressLine1" type="text" title="Beneficiary: Address Line 1" class="obligatorio_Template inputFormulario_templateAdd only_alpha_num_more" style="width: 300px" maxlength="50" size="55"/> <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AddressLine2" id="template_TAGDireccion22"  class="datos">Address Line 2</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLine2" type="text" title="Beneficiary: Address Line 2" class="inputFormulario_templateAdd only_alpha_num_more" style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_AddressLine3" id="template_TAGDireccion32"  class="datos">Address Line 3</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLine3" type="text" title="Beneficiary: City" class="inputFormulario_templateAdd obligatorio_Template only_alpha_num_more" style="width: 300px" maxlength="45" size="55"/><label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_BeneficiaryPostalCode" id="template_TAGPostalCode"  class="datos">Postal Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_BeneficiaryPostalCode" type="text" title="Postal Code" class="inputFormulario_templateAdd only_alpha_num_more" style="width: 300px" maxlength="45" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="25%">
                <label for="Template_TelephoneNumber" id="template_TAGTelefono"  class="datos">Telephone Number</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_TelephoneNumber" type="text" title="Beneficiary: Telephone Number" class="inputFormulario_templateAdd only_alpha_num_more" style="width: 300px" maxlength="20" size="25"/>
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
                <input id="Template_BankCodeIB2" type="text" title="Intermediary Bank: Bank Code" class="inputFormulario_templateAdd only_alpha_num" style="width: 178px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscarIB" name="btnCodBancoBuscar" value="..." tabindex="12">
                <label id="Template_BankCodeIB2OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_SwiftBankCodeIB2" id="template_SwiftTAGCodigoBanco2"  class="datos">Swift Code</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_SwiftBankCodeIB2" type="text" title="Intermediary Swift Bank: Bank Code" class="inputFormulario_templateAdd only_alpha_num" onkeypress="return validarn(event)" style="width: 275px" maxlength="20" size="23"/>
                <input type="Button" class="botonBuscar" id="Template_btnCodBancoBuscarIBSWIFT" name="btnCodBancoBuscar" value="..." tabindex="12">
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_NameBankIB" id="template_TAGBankName2"  class="datos">Name Bank</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_NameBankIB" type="text" title="Intermediary Bank: Name Bank" class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="65" size="50"/>
                <label id="Template_NameBankIBOB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBankIB1" id="template_TAGDireccion13"  class="datos">Address Line 1</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBankIB1" type="text" title="Intermediary Bank: Address Line 1" class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="50" size="55"/>
                <label id="Template_AddressLineBankIB1OB" class="datos6" style="display: none"> * </label>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBankIB2" id="template_TAGDireccion23"  class="datos" >Address Line 2</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBankIB2" type="text" title="Intermediary Bank: Address Line 2" class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="50" size="55"/>
            </td>
        </tr>
        <tr>
            <td width="30%">
                <label for="Template_AddressLineBankIB3" id="template_TAGDireccion33"  class="datos">Address Line 3</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AddressLineBankIB3" type="text" title="Intermediary Bank: Address Line 3" class="Template_AddressLineBankIB3 inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="45" size="55"/>
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
                <label for="Template_AccountNumberFFC" id="template_TAGNumeroCuenta2"  class="datos  only_alpha_num_more">Account Number</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_AccountNumberFFC" type="text" title="For Further Credit to: Account Number" class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="20" size="26"/>
            </td>
        </tr>
        <tr>
            <td width="35%">
                <label for="Template_NameFFC" id="template_TAGNombre2"  class="datos">Name</label><span class="datos">:</span>
            </td>
            <td width="70%">
                <input id="Template_NameFFC" type="text" title="For Further Credit to: Name" class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px" maxlength="65" size="50"/>
            </td>
        </tr>

    </table>
</fieldset>

<fieldset id="Templateform_8" class="div_consulta  fieldset_BeneficiaryInformation" style="display:none">
    <legend>5 - <span id="template_TAGInformacionBeneficiario">Beneficiary Information </span></legend>
    <table SUMMARY='tabla'cellpadding="0" id="Template_TableIndividual" cellspacing="0" width="100%" border="0">

        <!--  For Individual -->
        <tr>
            <td width="15%">
                <label for="Template_FullNameIndividualIB" id="template_TAG_NombreCompletoNatural"  class="datos">Full Name</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input id="Template_FullNameIndividualIB" type="text" title="Individual Beneficiary: Full Name" class="inputFormulario_templateAdd only_alpha"  style="width: 300px;" maxlength="65" size="50"/>
                <label class="datos6">  * </label>
            </td>

            <td width="15%">
                <label for="Template_DateBirthIB" id="template_TAGFechaNacimiento"  class="datos">Date of Birth</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input type="text" id="Template_DateBirthIB" class="inputFormulario_templateAdd" title="Individual Beneficiary: Date of Birth">
                <label class="datos6">  * </label>
            </td>
        </tr>
        <tr>
            <td width="15%">
                <label for="Template_NationalityIB" id="template_TAGNacionalidad"  class="datos" >Nationality</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <select id="Template_NationalityIB" title="Individual Beneficiary: Nationality" class="selectFormulario_templateAdd" style="width: 300px;" >

                </select>
                <label class="datos6">  * </label>
            </td>
            <td width="15%">
                <label for="Template_IdPassportIB" id="template_TAGDocumentoIdentidad"  class="datos" >I.D. / Passport Nr.</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input id="Template_IdPassportIB" type="text" title="Individual Beneficiary: I.D. / Passport Nr." class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px;" maxlength="20" size="26"/>
                <label class="datos6">  * </label>
            </td>
        </tr>
    </table>
    <table SUMMARY='tabla'cellpadding="0" id="Template_TableCompanies" cellspacing="0" width="100%" border="0">
        <tr>
            <!--  For Companies -->
            <td width="15%">
                <label for="Template_FullNameIB" id="template_TAGNombreCompleto"  class="datos">Full Name</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <input id="Template_FullNameIB" type="text" title="Companies Beneficiary: Full Name" class="inputFormulario_templateAdd only_alpha_num_more"  style="width: 300px;" maxlength="65" size="50"/>
                <label class="datos6">  * </label>
            </td>

            <td width="15%">
                <label for="Template_CountryIncorporationIB" id="template_TAGPaisIncorporacion"  class="datos">Country of Incorporation</label><span class="datos">:</span>
            </td>
            <td width="30%">
                <select id="Template_CountryIncorporationIB" title="Companies Beneficiary: Country of Incorporation" class="selectFormulario_templateAdd" style="width: 300px;" >

                </select>
                <label class="datos6">  * </label>
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
<tr id="div_Template_PostalCode" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGRPostalCode">Postal Code</span>
    </td>
    <td class="datos_resumen">
        <span id="template_RBeneficiaryPostalCode" class="TOB_borrarData"></span>
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

<!-- Resumen -->
<tr>
    <td class="titulo_resumen" colspan="2">
        <b><span id="template_TAGInformacionBeneficiario2">Beneficiary Information</span></b>
    </td>
</tr>
<tr id="div_Template_RFullNameIndividualIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGNombreCompletoNatural2">Full Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RFullNameIndividualIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RDateBirthIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGFechaNacimiento2">Date of Birth</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RDateBirthIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RNationalityIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGNacionalidad2">Nationality</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RNationalityIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RIdPassportIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGDocumentoIdentidad2">I.D. / Passport Nr.</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RIdPassportIB" class="TOB_borrarData"></span>
    </td>
</tr>

<tr id="div_Template_RFullNameIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGNombreCompleto2">Full Name</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RFullNameIB" class="TOB_borrarData"></span>
    </td>
</tr>
<tr id="div_Template_RCountryIncorporationIB" style="display: none">
    <td class="titulo_resumen">
        <span id="template_TAGPaisIncorporacion2">Country of Incorporation</span>
    </td>
    <td class="datos_resumen">
        <span id="Template_RCountryIncorporationIB" class="TOB_borrarData"></span>
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
</div>--%>
<%--
<div class="banner"><img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_transfers.png"
                         alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 class="banner__title banner__title--modifier" id="tag_title_banner_template">Balances & Transactions</h2>
            <ul class="banner__nav">
                <li><a id="TagHome8" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="tagTransfers">TRANSFERS</li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="tagTemplates">TEMPLATES</li>
            </ul>
            <p id="TAG_INFO_TEMPLATE_PRINCIPAL_2" class="banner__description banner__description--modifier">This option allows you to dispose in the future of
                a personal transaction list (Templates) which includes your frequently used beneficiaries and its data for
                more convenience transferring amounts with VBT Online.</p>
                <div id="ver_mas_template" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 class="banner__title banner__title--modifier" id="tag_title_banner_template">Balances & Transactions</h2>
            <ul class="banner__nav">
                <li><a id="TagHome8" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTransfers">TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagTemplates">TEMPLATES</li>
            </ul>
            <p id="TAG_INFO_TEMPLATE_PRINCIPAL_2" class="banner__description banner__description--modifier">This option allows you to dispose in the future of
                a personal transaction list (Templates) which includes your frequently used beneficiaries and its data for
                more convenience transferring amounts with VBT Online.</p>
                <div id="ver_mas_template" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<div id="dialog-description-show-more"  style="display:none">
    <p></p>
</div>
<main class="main main--modifier">
    <div class="section" id="Template_consulta">
        <div class="section__container container">
            <div class="section__top"><img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <span class="section__title" id="tag_section_title_template">Template</span>
                </div>
                <div class="section__row section__row--spacebetween">
                    <span class="section__note" id="TAG_INFO_TEMPLATE_PRINCIPAL">To add a new item to the list
              click add. To Change, Remove or Approve, click on the respective template name.</span>
                    <button class="section__button button" id="Template_add">ADD</button>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <table id="tabla_Templates_consulta" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="section" id="Template_createTemplate" style="display: none">
        <div class="section__container section__container--modifier container">
            <div class="section__top">
                <div class="section__icon">
                    <img src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                </div>
                <div class="section__titles">
                    <span id="tag_title_transfers_template" class="section__title">Transfers Template</span>
                </div>
                <div class="section__row section__row--grid">
                    <input id="Template_nombreTemplate" class="input input--form obligatorio_Template inputFormulario_templateAdd" type="text" placeholder="Template Name" />
                </div>
            </div>
            <div class="section__content">
                <div class="accordeon" id="accordeon-form">
                    <div class="accordeon__block">
                        <div class="accordeon__titles">
                            <img class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                                 alt="" />
                            <span id="Template_TAGBancoBeneficiario" class="accordeon__title" style="margin-top: 1em;">BENEFICIARY BANK</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__field form-transfer__field--column">
                          
                                        <div class="form-transfer__auto1fr">
                                            <div class="select-section select-section--form">
                                                <div class="form-transfer__question">
                                                    <label class="label-cursor" id="template_TAGCodigoBanco" for="Template_BankCode2" style="width: 7em;"> BANK CODE</label>
                                                    <img src="../vbtonline/resources/img/icons/question-banks.png" alt="" style="margin-right: 1em;">
                                                </div>
                                                <select id="Template_BankCode" class="select-section__select select-section__select--form selectFormulario_templateAdd"
                                                        onchange="validarIntermediarioTemplate();">
                                                </select>
                                                <img class="select-section__icon select-section__icon--form"
                                                     src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                            <div class="input-container">
                                                <input id="Template_BankCode2" class="input input--form inputFormulario_templateAdd only_alpha_num" type="text" onkeypress="return validarn(event)"/>
                                                <button class="input-container__search"  id="Template_btnCodBancoBuscar" name="btnCodBancoBuscar">
                                                    <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-transfer__field form-transfer__field--column">
                                        <div class="input-container">
                                            <div class="form-transfer__question">
                                                <label id="template_SwiftTAGCodigoBanco" for="Template_SwiftBankCode2" style="width: 7em;">SWITF CODE*</label>
                                                <img src="../vbtonline/resources/img/icons/question-banks.png" alt="" style="margin-right: 1em;">
                                            </div>
                                            <input class="input input--form inputFormulario_templateAdd only_alpha_num" id="Template_SwiftBankCode2"
                                                   title="Beneficiary Bank: Switf Code" type="text" onkeypress="return validarn(event)"/>
                                            <button id="Template_btnCodBancoBuscarSWIFT" class="input-container__search">
                                                <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGBankName" for="Template_NameBank">BANK NAME*</label>
                                            <input id="Template_NameBank" class="input input--form obligatorio_Template inputFormulario_templateAdd only_alpha_num_more" title="Beneficiary Bank: Bank Name"
                                                   type="text" />
                                        </div>
                                    </div>
                                </div>


                                <div class="form-transfer__grid" style="margin-top: 0.5em;">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGDireccion1" for="Template_AddressLineBank1">Address line 1*</label>
                                            <input id="Template_AddressLineBank1" class="input input--form obligatorio_Template inputFormulario_templateAdd  only_alpha_num_more" title="Beneficiary Bank: Address line 1"
                                                   type="text" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label id="template_TAGDireccion2" for="Template_AddressLineBank2">Address line 2</label>
                                            <input id="Template_AddressLineBank2"class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="template_TAGDireccion3" for="Template_AddressLineBank3">City</label>
                                            <input id="Template_AddressLineBank3" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGPais" for="Template_CountryBank">Select Country*</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray obligatorio_Template selectFormulario_templateAdd"
                                                        id="Template_CountryBank" title="Beneficiary Bank: Country">

                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                       
                        </div>
                    </div>
                    <div class="accordeon__block">
                        <div class="accordeon__titles">
                            <img class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                                 alt="" />
                            <span id="template_TAGBeneficiario" class="accordeon__title" style="margin-top: 1em;">BENEFICIARY</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div id="Template_form_2" class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="Template_transferencias_TAGTipoPersona" for="Template_TipoPersona">LEGAL PERSON*</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--blue inputFormulario_templateAdd obligatorio_Template selectFormulario_TOB"
                                                        id="Template_TipoPersona" title="Beneficiary : Legal Person">
                                                    <option value="-2" id="Template_transferencias_tipoPersonaSelect" >Select</option>
                                                    <option value="NAT" id="Template_transferencias_tipoPersonaNatural">Natural</option>
                                                    <option value="JUR" id="Template_transferencias_tipoPersonaJuridica">Jur&iacute;dico</option>
                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_login_language_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                        <div name="Template_transferencias_apellidos"  class="form-transfer__field ocultoCampo">
                                            <label id="Template_transferencias_TAGLastname1" for="Template_lastname1">First surname</label>
                                            <input id="Template_lastname1" class="input input--form inputFormulario_templateAdd only_alpha invisible_print inputFormulario_TOB obligatorio_Template" type="text"/>
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">

                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGNombre" for="Template_name">Beneficiary Name*</label>
                                            <input class="input input--form obligatorio_Template inputFormulario_templateAdd only_alpha_num_more" type="text"
                                                   id="Template_name" title="Beneficiary : Name" />
                                        </div>
                                        <div name="Template_transferencias_apellidos" class="form-transfer__field ocultoCampo">
                                            <label id="Template_transferencias_TAGLastname2" for="Template_lastname2">Last surname</label>
                                            <input id="Template_lastname2" class="input input--form inputFormulario_templateAdd only_alpha invisible_print inputFormulario_TOB" type="text"/>
                                        </div>
                                    </div>
                                </div>
<!--                                 <div class=" form-transfer__headers--margin">
                                    <label class="label-cursor field-obligatory" id="template_TAGNumeroCuenta" for="Template_AccountBank">ACCOUNT NUMBER*</label>
                                </div> -->
<!--                                 <div class="form-transfer__row form-transfer__row--col2">
                                    <div class="form-transfer__auto1fr">
                                        <div class="select-section select-section--form">
                                            <select id="Template_AccountBank" class="select-section__select select-section__select--form obligatorio_Template selectFormulario_templateAdd " name="account">

                                            </select><img class="select-section__icon select-section__icon--form"
                                                          src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                        </div>
                                        <input class="input input--form obligatorio_Template inputFormulario_templateAdd  only_alpha_num" tittle="Beneficiary : Account Number"
                                               id="Template_AccountNumber" type="text" />
                                    </div>
                                </div> -->

                                <div class="form-transfer__grid">
                                    <div class="form-transfer__field">
                                        <label class="label-cursor field-obligatory" id="template_TAGNumeroCuenta" for="Template_AccountBank">ACCOUNT NUMBER*</label>
                                        <div style="display: flex;flex-direction: row;">
                                            <div class="select-section select-section--form">
                                                <select id="Template_AccountBank" class="select-section__select select-section__select--form obligatorioTOB obligatorioTOBTMP selectFormulario_TOB" name="account" style="width:8em">
    
                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                            <input class="input input--form invisible_print obligatorioTOBTMP obligatorioTOB inputFormulario_TOB only_alpha_num"
                                                   title="Beneficiary : Account Number"
                                                   id="Template_AccountNumber" maxlength="30" size="34" type="text"  />
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="form-transfer__grid">
                                <div class="form-transfer__item">
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="template_TAGDireccion12" for="Template_AddressLine1">Address line 1*</label>
                                        <input id="Template_AddressLine1" class="input input--form obligatorio_Template inputFormulario_templateAdd only_alpha_num_more" type="text"
                                               title="Beneficiary : Address line 1" />
                                    </div>
                                    <div class="form-transfer__field">
                                        <label id="template_TAGDireccion22" for="Template_AddressLine2">Address line 2</label>
                                        <input id="Template_AddressLine2" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                    </div>
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="template_TAGDireccion32" for="Template_AddressLine3">City*</label>
                                        <input id="Template_AddressLine3" class="input input--form inputFormulario_templateAdd obligatorio_Template only_alpha_num_more" type="text"
                                               title="Beneficiary : City"/>
                                    </div>
                                    <div class="form-transfer__field">
                                        <label id="template_TAGPostalCode" for="Template_BeneficiaryPostalCode">Postal Code</label>
                                        <input id="Template_BeneficiaryPostalCode" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text"  />
                                    </div>
                                </div>
                                <div class="form-transfer__item">
                                    <div class="form-transfer__field">
                                        <label id="template_TAGTelefono" for="Template_TelephoneNumber">Telephone</label>
                                        <input id="Template_TelephoneNumber" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text"  />
                                    </div>
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="template_TAGPais2" for="Template_Country">Select Country*</label>
                                        <div class="select-section select-section--form">
                                            <select
                                                    class="select-section__select select-section__select--form select-section__select--gray obligatorio_Template selectFormulario_templateAdd"
                                                    id="Template_Country"  title="Beneficiary : Select Country">

                                            </select><img class="select-section__icon select-section__icon--form"
                                                          src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__field">
                                        <label class="field-obligatory" id="template_TAGEmail" for="Template_beneficiaryEmail">Email*</label>
                                        <input id="Template_beneficiaryEmail" class="input input--form obligatorio_Template inputFormulario_templateAdd" type="email"
                                                title="Beneficiary : Email" />
                                    </div>
                                </div>
                            </div>
                         
                        </div>
                    </div>
                    <div id="Templateform_8" class="accordeon__block" style="display:none">
                        <div class="accordeon__titles">
                            <img class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                                 alt="" /><span id="template_TAGInformacionBeneficiario" class="accordeon__title" style="margin-top: 1em;">Beneficiary Information</span>
                              <!--    <img
                                class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid" id="Template_TableIndividual">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">Beneficiary</div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGNombreCompletoNatural" for="Template_AccountNumberFFC">Number</label>
                                            <input id="Template_FullNameIndividualIB"
                                                   class="input input--form inputFormulario_templateAdd only_alpha obligatorio_Template"
                                                   title="Individual Beneficiary: Full Name"
                                                   maxlength="65"
                                                   size="50"
                                                   type="text" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGNacionalidad" for="Template_NationalityIB">Nationalityr</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray selectFormulario_templateAdd obligatorio_Template"
                                                        id="Template_NationalityIB"
                                                        title="Individual Beneficiary: Nationality"
                                                >

                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGFechaNacimiento" for="Template_DateBirthIB">Date of Birth</label>
                                            <input id="Template_DateBirthIB"
                                                   title="Individual Beneficiary: Date of Birth"
                                                   class="input input--form inputFormulario_templateAdd obligatorio_Template"
                                                   type="text" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGDocumentoIdentidad" for="Template_IdPassportIB">I.D. / Passport Nr.</label>
                                            <input id="Template_IdPassportIB"
                                                   title="Individual Beneficiary: I.D. / Passport Nr."
                                                   maxlength="20"
                                                   size="26"
                                                   class="input input--form inputFormulario_templateAdd only_alpha_num_more obligatorio_Template"
                                                   type="text"  />
                                        </div>
                                    </div>
                                </div>
                                <div class="form-transfer__grid" id="Template_TableCompanies">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">Beneficiary</div>
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGNombreCompleto" for="Template_FullNameIB">Full Name:</label>
                                            <input id="Template_FullNameIB"
                                                   class="input input--form inputFormulario_templateAdd only_alpha_num_more obligatorio_Template"
                                                   title="Companies Beneficiary: Full Name"
                                                   maxlength="65"
                                                   size="50"
                                                   type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label class="field-obligatory" id="template_TAGPaisIncorporacion" for="Template_CountryIncorporationIB">Nationalityr</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray selectFormulario_templateAdd obligatorio_Template"
                                                        id="Template_CountryIncorporationIB"
                                                        title="Companies Beneficiary: Country of Incorporation"
                                                >

                                                </select>
                                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          
                        </div>
                    </div>
                    <div class="accordeon__block">
                        <div class="accordeon__titles">
                            <img class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png" alt="" />
                            <span id="template_TAGBancoIntermediario" class="accordeon__title" style="margin-top: 1em;">INTERMEDIARY BANK (IF APPLICABLE)</span>
                            <!-- <img class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
          <!--                       <div class="form-transfer__container">
                                    <div class="">
                                        <label id="template_TAGBankName2" for="Template_NameBankIB">BANK NAME</label>
                                        <label id="template_TAGCodigoBanco2" for="Template_BankCodeIB2">BANK CODE</label>
                                        <label id="template_SwiftTAGCodigoBanco2" for="Template_SwiftBankCodeIB2">
                                            SWITF CODE</label>
                                    </div> -->
                                    <div class="form-transfer__row">
                                        <div class="form-transfer__auto1fr">
                                            <div class="select-section select-section--form">
                                                <div class="form-transfer__question">
                                                    <label class="label-cursor" id="template_TAGCodigoBanco2" for="Template_BankCode2" style="width: 7em;"> BANK CODE</label>
                                                    <img src="../vbtonline/resources/img/icons/question-banks.png" alt="" style="margin-right: 1em;">
                                                </div>
                                                <select class="select-section__select select-section__select--form selectFormulario_templateAdd"
                                                        id="Template_BankCodeIB">
                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                            <div class="input-container">
                                                <input id="Template_BankCodeIB2" class="input input--form inputFormulario_templateAdd only_alpha_num" type="text" />
                                                <button id="Template_btnCodBancoBuscarIB" name="btnCodBancoBuscar" class="input-container__search">
                                                    <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                                </button>
                                            </div>
                                        </div>
                                        <div class="input-container">
                                            <div class="form-transfer__question">
                                                <label id="template_SwiftTAGCodigoBanco3" for="Template_SwiftBankCode2" style="width: 7em;">SWITF CODE*</label>
                                                <img src="../vbtonline/resources/img/icons/question-banks.png" alt="" style="margin-right: 1em;">
                                            </div>
                                            <input class="input input--form inputFormulario_templateAdd only_alpha_num" id="Template_SwiftBankCodeIB2" type="text" onkeypress="return validarn(event)"/>
                                            <button id="Template_btnCodBancoBuscarIBSWIFT" class="input-container__search">
                                                <img src="../vbtonline/resources/img/icons/ic_table_template_search_code.png" alt="" />
                                            </button>
                                        </div>
                                    </div>
                                    <div class="form-transfer__grid">
                                        <div class="form-transfer__item">
                                            <div class="form-transfer__field">
                                                <label id="template_TAGBankName2" for="Template_NameBankIB">BANK NAME</label>
                                                <input id="Template_NameBankIB" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="template_TAGDireccion13" for="Template_AddressLineBankIB1">Address line 1</label>
                                            <input id="Template_AddressLineBankIB1" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label id="template_TAGDireccion23" for="Template_AddressLineBankIB2">Address line 2</label>
                                            <input id="Template_AddressLineBankIB2" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="template_TAGDireccion33" for="Template_AddressLineBankIB3">City</label>
                                            <input id="Template_AddressLineBankIB3" class="input input--form Template_AddressLineBankIB3 inputFormulario_templateAdd only_alpha_num_more" type="text"  />
                                        </div>
                                        <div class="form-transfer__field">
                                            <label id="template_TAGPais3" for="Template_CountryBankIB">Select Country</label>
                                            <div class="select-section select-section--form">
                                                <select
                                                        class="select-section__select select-section__select--form select-section__select--gray selectFormulario_templateAdd"
                                                        id="Template_CountryBankIB">

                                                </select><img class="select-section__icon select-section__icon--form"
                                                              src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
           
                        </div>
                    </div>
                    <div class="accordeon__block">
                        <div class="accordeon__titles">
                            <img class="accordeon__status" src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                                 alt="" /><span id="template_TAGParaFuturoCredito" class="accordeon__title" style="margin-top: 1em;">FOR FURTHER CREDIT TO (IF APPLICABLE)</span>
                            <!--      <img
                                class="accordeon__arrow" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" /> -->
                        </div>
                        <div class="accordeon__content accordeon__content--active">
                            <div class="form-transfer">
                                <div class="form-transfer__grid">
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="template_TAGNumeroCuenta2" for="Template_AccountNumberFFC">Number</label>
                                            <input id="Template_AccountNumberFFC" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                        </div>
                                    </div>
                                    <div class="form-transfer__item">
                                        <div class="form-transfer__field">
                                            <label id="template_TAGNombre2" for="Template_NameFFC">Name</label>
                                            <input id="Template_NameFFC" class="input input--form inputFormulario_templateAdd only_alpha_num_more" type="text" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-transfer__spacebetween">
                                <span class="form-transfer__mandaroty"> </span>
                                <div class="form-transfer__buttons">
<%--                                    <button class="form-transfer__button button button--white"
                                            data-accordeon-next="data-accordeon-next">
                                        SKIP
                                    </button>
                                    <button class="form-transfer__button form-transfer__button--next button"
                                            data-accordeon-next="data-accordeon-next">
                                        NEXT
                                    </button>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="accordeon__spacebetween" style="padding-bottom:1em;">
                    <div id="Templateform_7" class="accordeon__buttons">
                        <input type="button" id="Template_btn_back" value="BACK" class="form-transfer__button button button--white">
                        <input type="button" id="Template_btn_TOB_borrar" value="Delete" class="form-transfer__button button button--white">
                        <input type="button" id="Template_btn_TOB_cancelar" value="Clear" class="form-transfer__button button button--white">
                        <input type="button" id="Template_btn_TOB_aceptar" value="SAVE TEMPLATE" class="form-transfer__button form-transfer__button--next button">
                        <div id="btnAproveTemplate"><input type="button" id="Template_btn_TOB_aprobar" value="Approve" class="form-transfer__button form-transfer__button--next button"></div>
                        <div id="btnMakeTransfers"><input type="button" id="Template_btn_transferencia" value="Make a Transfer" class="form-transfer__button form-transfer__button--next button"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" id="templateID" value="" />
    <input type="hidden" id="accionTemplate" value="" />
    <div class="section" id="summaryTemplate" style="display: none" >
        <div class="section__container container">
            <div class="section__top"><img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png"
                                           alt="" />
                <div class="section__titles"><span id="summary_TOB2" class="section__title">Summary</span>
                    <div class="section__info"><span id="sumaryConfirm_TOB2">Confirme sus datos</span><img class="section__icon" src="../vbtonline/resources/img/icons/ic_table_transfer_header_info.png" alt="" /></div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <div class="table__titles"><span id="template_TAGBancoBeneficiario2" class="table__title">BENEFICIARY BANK</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_RBeneficiaryBankCode" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGEmailCodigoBancoBeneficiario" class="table__item table__item--summary"><span>Beneficiary Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="Template_RBankCode"></span></td>
                        </tr>
                        <tr id="div_RBeneficiarySwiftBankCode" style="display: none" class="table__row table__row--summary">
                            <td id="transferencias_TAGNumeroCuentaDebito2" class="table__item table__item--summary"><span>Beneficiary Swift Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="Template_RSwiftBankCode"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGEmailNombreBancoBeneficiario" class="table__item table__item--summary"><span>Beneficiary Bank Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_RNameBank"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryBankAddress1"class="table__item table__item--summary"><span>Beneficiary Bank Address 1</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBank1"></span></td>
                        </tr>
                        <tr id="div_Template_RAddressLineBank2" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryBankAddress2" class="table__item table__item--summary"><span>Beneficiary Bank Address 2</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBank2"></span></td>
                        </tr>
                        <tr id="div_Template_RAddressLineBank3" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryBankAddress3" class="table__item table__item--summary"><span>Beneficiary Bank Address 3</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBank3"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryBankCountry"class="table__item table__item--summary"><span>Beneficiary Bank Country</span></td>
                            <td class="table__item table__item--last"><span id="Template_RCountryBank"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table">
                    <div class="table__titles"><span id="template_TAGNombreBeneficiario2" class="table__title">Beneficiary</span></div>
                    <table class="table__content table__content--summary">
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryName" class="table__item table__item--summary"><span>Beneficiary Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_Rname"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryAccount" class="table__item table__item--summary"><span>Beneficiary Account</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAccountNumber"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGEmailBeneficiario" class="table__item table__item--summary"><span></span></td>
                            <td class="table__item table__item--last"><span id="Template_RbeneficiaryEmail" ></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryAddress1" class="table__item table__item--summary"><span>Beneficiary Address 1</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLine1" ></span></td>
                        </tr>
                        <tr id="div_Template_RAddressLine2" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryAddress2" class="table__item table__item--summary"><span>Beneficiary Address 2</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLine2" ></span></td>
                        </tr>
                        <tr id="div_Template_RAddressLine3" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryAddress3" class="table__item table__item--summary"><span>Beneficiary Address 2</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLine3" ></span></td>
                        </tr>
                        <tr id="div_Template_PostalCode" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGRPostalCode" class="table__item table__item--summary"><span>Postal Code</span></td>
                            <td class="table__item table__item--last"><span id="template_RBeneficiaryPostalCode"></span></td>
                        </tr>
                        <tr id="div_Template_RTelephoneNumber" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryTelephone" class="table__item table__item--summary"><span>Beneficiary Telephone</span></td>
                            <td class="table__item table__item--last"><span id="Template_RTelephoneNumber"></span></td>
                        </tr>
                        <tr class="table__row table__row--summary">
                            <td id="template_TAGBeneficiaryCountry" class="table__item table__item--summary"><span>Beneficiary Country</span></td>
                            <td class="table__item table__item--last"><span id="Template_RCountry"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table" id="div_Template_resumen_intermediary" style="display: none">
                    <div class="table__titles"><span id="template_TAGIntermediaryBank" class="table__title">Intermediary Bank</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_Template_RBankCodeIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGEmailCodigoBancoIntermediario" class="table__item table__item--summary"><span>Intermediary Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="Template_RBankCodeIB"></span></td>
                        </tr>
                       <tr id="div_Template_RSwiftBankCodeIB" style="display: none" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span>Intermediary Swift Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="Template_RSwiftBankCodeIB"></span></td>
                        </tr>
                       <tr id="div_Template_RNameBankIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGEmailNombreBancoIntermediario" class="table__item table__item--summary"><span>Intermediary Bank Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_RNameBankIB"></span></td>
                        </tr>
                       <tr id="div_Template_RAddressLineBankIB1" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankAddress1" class="table__item table__item--summary"><span>Intermediary Bank Address 1</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBankIB1"></span></td>
                        </tr>
                      <tr id="div_Template_RAddressLineBankIB2" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankAddress2" class="table__item table__item--summary"><span>Intermediary Bank Address 2</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBankIB2"></span></td>
                        </tr>
                      <tr id="div_Template_RAddressLineBankIB3" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankAddress3" class="table__item table__item--summary"><span>Intermediary Bank Address 3</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBankIB3"></span></td>
                        </tr>
                      <tr id="div_Template_RCountryBankIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankCountry" class="table__item table__item--summary"><span>Intermediary Bank Country</span></td>
                            <td class="table__item table__item--last"><span id="Template_RCountryBankIB"></span></td>
                        </tr>
                    </table>
                </div>
                <div id="div_Template_resumen_furtherCredit" style="display: none" class="table">
                    <div class="table__titles"><span id="template_TAGParaFuturoCredito2" class="table__title">Intermediary Bank</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_Template_RAccountNumberFFC" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGFurtherCreditAccount" class="table__item table__item--summary"><span>Further Credit Account</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAccountNumberFFC"></span></td>
                        </tr>
                        <tr id="div_Template_RNameFFC" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGFurtherCreditName" class="table__item table__item--summary"><span>Further Credit Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_RNameFFC"></span></td>
                        </tr>
                    </table>
                </div>
                <div class="table">
                    <div class="table__titles"><span id="template_TAGInformacionBeneficiario2"class="table__title">Beneficiary Information</span></div>
                    <table class="table__content table__content--summary">
                        <tr id="div_Template_RFullNameIndividualIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGNombreCompletoNatural2" class="table__item table__item--summary"><span>Full Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_RFullNameIndividualIB"></span></td>
                        </tr>
                        <tr id="div_Template_RDateBirthIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGFechaNacimiento2" class="table__item table__item--summary"><span>Date of Birth</span></td>
                            <td class="table__item table__item--last"><span id="Template_RDateBirthIB"></span></td>
                        </tr>
                        <tr id="div_Template_RNationalityIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGNacionalidad2" class="table__item table__item--summary"><span>Nationality</span></td>
                            <td class="table__item table__item--last"><span id="Template_RNationalityIB"></span></td>
                        </tr>
                       <tr id="div_Template_RIdPassportIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGDocumentoIdentidad2" class="table__item table__item--summary"><span>I.D. / Passport Nr.</span></td>
                            <td class="table__item table__item--last"><span id="Template_RIdPassportIB"></span></td>
                        </tr>
                       <tr id="div_Template_RFullNameIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGNombreCompleto2" class="table__item table__item--summary"><span>Full Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_RFullNameIB"></span></td>
                        </tr>
                       <tr id="div_Template_RCountryIncorporationIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGPaisIncorporacion2" class="table__item table__item--summary"><span>Country of Incorporation</span></td>
                            <td class="table__item table__item--last"><span id="Template_RCountryIncorporationIB"></span></td>
                        </tr>
                    </table>
                    <div class="table__spacebetween table__spacebetween--top">
                        <div></div>
                        <div class="table__buttons">
                            <button id="btn_resumen_cancelar_template" class="table__button button button--white">BACK</button>
                            <button id="btn_resumen_aceptar_template" class="table__button button">SEND</button>
                        </div>
                    </div>
                </div>
<%--
                <div class="table">
                    <div id="template_TAGParaFuturoCredito2" class="table__titles"><span id="template_TAGIntermediaryBank" class="table__title">Intermediary Bank</span></div>
                    <table class="table__content">
                        <tr id="div_Template_RBankCodeIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGEmailCodigoBancoIntermediario" class="table__item table__item--summary"><span>Intermediary Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="Template_RBankCodeIB"></span></td>
                        </tr>
                       <tr id="div_Template_RSwiftBankCodeIB" style="display: none" class="table__row table__row--summary">
                            <td class="table__item table__item--summary"><span>Intermediary Swift Bank Code</span></td>
                            <td class="table__item table__item--last"><span id="Template_RSwiftBankCodeIB"></span></td>
                        </tr>
                       <tr id="div_Template_RNameBankIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGEmailNombreBancoIntermediario" class="table__item table__item--summary"><span>Intermediary Bank Name</span></td>
                            <td class="table__item table__item--last"><span id="Template_RNameBankIB"></span></td>
                        </tr>
                       <tr id="div_Template_RAddressLineBankIB1" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankAddress1" class="table__item table__item--summary"><span>Intermediary Bank Address 1</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBankIB1"></span></td>
                        </tr>
                      <tr id="div_Template_RAddressLineBankIB2" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankAddress2" class="table__item table__item--summary"><span>Intermediary Bank Address 2</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBankIB2"></span></td>
                        </tr>
                      <tr id="div_Template_RAddressLineBankIB3" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankAddress3" class="table__item table__item--summary"><span>Intermediary Bank Address 3</span></td>
                            <td class="table__item table__item--last"><span id="Template_RAddressLineBankIB3"></span></td>
                        </tr>
                      <tr id="div_Template_RCountryBankIB" style="display: none" class="table__row table__row--summary">
                            <td id="template_TAGIntermediaryBankCountry" class="table__item table__item--summary"><span>Intermediary Bank Country</span></td>
                            <td class="table__item table__item--last"><span id="Template_RCountryBankIB"></span></td>
                        </tr>
                    </table>
                </div>
--%>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<div class="form-modal invisible_print container" id="sign_up_template_search">
    <div class="form-modal__top">
        <img class="form-modal__icon" src="../vbtonline/resources/img/icons/ic_templates_saved_header_search.png" alt="" />
        <span id="see_id_template" class="form-modal__title">Bank Code</span>
    </div>
    <div class="form-modal__content">
        <div class="form-transfer">
            <div class="form-transfer__grid form-transfer__grid--nomargin">
                <div class="form-transfer__item">
                    <div class="form-transfer__field">
                        <label id="buscar_bankcodetype" for="Template_BankCodeType_buscar">Bank Code Type</label>
                        <div class="select-section select-section--form">
                            <select id="Template_BankCodeType_buscar" class="select-section__select select-section__select--form selectFormulario_buscar" name="account">
                            </select><img class="select-section__icon select-section__icon--form"
                                          src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                    <div class="form-transfer__field">
                        <label id="buscar_bankname" for="Template_BankName_buscar">Bank Name</label>
                        <input id="Template_BankName_buscar" class="input input--form obligatorioBuscar inputFormulario_buscar" type="text"  />
                    </div>
                </div>
                <div class="form-transfer__item">
                    <div class="form-transfer__field">
                        <label id="buscar_bankcode" for="Template_BankCode_buscar">Bank Code</label>
                        <input id="Template_BankCode_buscar" class="input input--form obligatorioBuscar inputFormulario_buscar" type="text"  />
                    </div>
                    <div class="form-transfer__field">
                        <label id="buscar_bankcountry" for="Template_BankCountry_buscar">Select Country</label>
                        <div class="select-section select-section--form">
                            <select id="Template_BankCountry_buscar" class="select-section__select select-section__select--form selectFormulario_buscar" name="account">
                            </select><img class="select-section__icon select-section__icon--form"
                                          src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-modal__spacebetween form-modal__spacebetween--border">
                <div></div>
                <div class="form-modal__buttons">
                    <input type='button' id="Template_cancel" href="#" value="Clear" class="form-modal__button button button--white"/>
                    <input type='button' id="Template_buscar" href="#" value="Search" class="form-modal__button button"/>
                </div>
            </div>
            <div class="table table--nomargin">
                <table id="tabla_consulta_bancos_template" class="table__content">

                </table>
            </div>
        </div>
    </div>
</div>
<%--<jsp:include page="../../footer.jsp" />--%>



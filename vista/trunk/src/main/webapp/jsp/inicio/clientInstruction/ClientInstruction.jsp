<%--
  Created by IntelliJ IDEA.
  User: Mary Bernot
  Date: 19/09/13
  Time: 02:41 PM
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div class="clear"></div>
<div>
    <h1 id="ClientInstruction_TAGTitle">Generar carta de instrucción</h1>
</div>
<fieldset class="invisible_print div_info">
    <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
        <tbody>
        <tr>
            <td width="3%">
                <img  src="resources/images/iconInfo.png" >
            </td>
            <td align="left">
                <span id="TAG_INFO_CARTA" class="datosInfo">This option allows you to generate Instruction letters for manual transactions which you must process through your adviser or Account Executive.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_clientInstruction">
    <fieldset class="div_consulta">
        <legend><span class="label_client_instruction" id="ClientInstruction_TAGClientInstruction">Carta de Instrucción</span></legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
            <tr>
                <input  type="hidden"  id="ci_codperclicarta"  >
                <input  type="hidden"  id="ci_nm_transfer_type"  >
                <td class="datos" ><span class="label_ci_client" id="ClientInstruction_TAGClient">Cliente </span><span>:</span></td>
                <td class="datos" >

                    <select  id="ci_client" title="Cliente" class="selectFormulario_Client_Instruction requerido_Client_Instruction" style="width:250px;" onchange=""  >

                    </select>
                </td>
                <td class="datos"><span class="label_ci_id" id="ClientInstruction_TAGId">Id </span><span>:</span></td>
                <td class="datos">
                    <input  type="text"  id="ci_id" title="Id" class="inputFormulario_Client_Instruction requerido_Client_Instruction" readonly="readonly"  >


                </td>
                <td class="datos"></td>
                <td class="datos">

                </td>


                <td  width="3%" class="botones_formulario">
                    &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;

                </td>
            </tr>
            <tr>
                <td class="datos" ><span class="label_transfer_type" id="ClientInstruction_TAGTransferType">Tipo de Transferencia </span><span>:</span></td>
                <td class="datos" >
                    <select  id="ci_transfer_type" title="Tipo de Transferencia" class="selectFormulario_Client_Instruction requerido_Client_Instruction"  onkeypress="" />

                </td>

                <td class="datos" ><span class="label_ci_expiration" id="ClientInstruction_TAGExpiration">Vencimiento </span><span>:</span></td>
                <td class="datos" >
                    <select  id="ci_expiration" title="Vencimiento" class="selectFormulario_Client_Instruction requerido_Client_Instruction" onchange=""  >

                    </select>
                </td>
                <td class="datos" ><span class="label_ci_quantity" id="ClientInstruction_TAGQuantity">Cantidad </span><span>:</span></td>
                <td class="datos" >
                    <select  id="ci_quantity" title="Id" class="selectFormulario_Client_Instruction requerido_Client_Instruction" style="width:105px;" onchange="" >

                    </select>
                </td>

                <td  class="botones_formulario">
                    <input type="button" id="ClientInstruction_BTNConsultar" value="Consultar" class="botonEDOCuenta">

                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

</div>
--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="ClientInstruction_TAGTitle" class="banner__title banner__title--modifier">
                Generate Instruction Letter
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome1" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="ClientInstruction_TAGNavTitle">GENERATE INSTRUCTION LETTER</li>
            </ul>
            <p id="TAG_INFO_CARTA" class="banner__description banner__description--modifier">
                This option allows you to generate letter of instruction for manual transactions which you must process through your Adviser or Account Executive
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="ClientInstruction_TAGTitle" class="banner__title banner__title--modifier">
                Generate Instruction Letter
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome1" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="ClientInstruction_TAGNavTitle">GENERATE INSTRUCTION LETTER</li>
            </ul>
            <p id="TAG_INFO_CARTA" class="banner__description banner__description--modifier">
                This option allows you to generate letter of instruction for manual transactions which you must process through your Adviser or Account Executive
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_clientInstruction" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__headline">
                    <div class="section__header">
                        <span id="ClientInstruction_TAGClientInstruction" class="section__title">Instruction Letter</span>
                        <div class="section__field">
                            <label id="ClientInstruction_TAGClient" for="ci_client">Client</label>
                            <div class="select-section select-section--form">
                                <select id="ci_client"
                                        title="Client"
                                        class="select-section__select select-section__select--form selectFormulario_Client_Instruction requerido_Client_Instruction">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter" >
                    <input type="hidden" id="ci_codperclicarta" />
                    <input type="hidden" id="ci_nm_transfer_type" />
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="ClientInstruction_TAGId" class="form-filter__label" for="ci_id">Label</label>
                            <input class="form-filter__input input input--form inputFormulario_Client_Instruction requerido_Client_Instruction"
                                   type="text" id="ci_id" readonly="readonly" title="CI/RIF"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="ClientInstruction_TAGTransferType" class="form-filter__label" for="ci_transfer_type">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="ci_transfer_type"
                                        title="Transfer Type"
                                        class="select-section__select select-section__select--form selectFormulario_Client_Instruction requerido_Client_Instruction">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="ClientInstruction_TAGExpiration" class="form-filter__label" for="ci_expiration">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="ci_expiration"
                                        title="Expiration"
                                        class="select-section__select select-section__select--form selectFormulario_Client_Instruction requerido_Client_Instruction">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                       <div class="form-filter__item">
                            <label id="ClientInstruction_TAGQuantity" class="form-filter__label" for="ci_quantity">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="ci_quantity"
                                        title="Quantity"
                                        class="select-section__select select-section__select--form selectFormulario_Client_Instruction requerido_Client_Instruction">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input id="ClientInstruction_BTNConsultar" type="button" class="form-filter__button button" value="Search" />
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>

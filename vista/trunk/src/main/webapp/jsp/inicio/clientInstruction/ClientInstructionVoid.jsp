<%--
  Created by IntelliJ IDEA.
  User: bcalderon
  Date: 24/09/14
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--



<div>
    <h1 id="ClientInstructionVoid_TAGTitle">Anular carta de instrucción</h1>
</div>
<fieldset class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_ANULAR_CARTA">This option allows you to cancel a letter of instruction.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_clientInstructionVoid">
    <fieldset class="div_consulta">

        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tbody>
            <tr>
                <td class="datos" width="20%">
                    <span class="label_numero_control" id="ClientInstructionVoid_TAGNumeroControl">N&uacute;mero de Control </span>
                    <span>:</span>
                </td>
                <td class="datos" width ="15%">
                    <input type="text"  id="numero_de_control" title="Número de Control" class="inputFormulario requerido_ACI" maxlength="15" onkeypress="return onlyDigits(event,'noDec');" onchange="validarCamposAnularCarta();"/>
                </td>
                <td class="datos" width ="10%">
                    <span class="label_ci_client" id="ClientInstructionVoid_TAGClient">Cliente </span>
                    <span>:</span>
                </td>
                <td class="datos" width ="10%" >
                    <select  id="ci_client_cancel" title="Cliente" class="selectFormulario requerido_ACI" style="width:250px;" onchange="validarCamposAnularCarta();">
                    </select>
                </td>
                <td  class="botones_formulario" width ="55%">
                    <input type="hidden" id="instrucciones_cliente_consultar" value="Verificar" class="boton">
                </td>
            </tr>
            </tbody>
        </table>

      </fieldset>
    <br>
</div>
<div id="div_clientInstructionCancel2" class="oculto" >
    <fieldset  class="div_consulta">
        <table SUMMARY='tabla2' cellspacing="0" cellpadding="0" width="100%">
            <tbody>
            <tr>

                <td  class="botones_formulario2" width ="30%">
               <div id="div_cliente_anular"> </div>
                </td>

            </tr>
            </tbody>
        </table>
    </fieldset>
  <br>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_transfers.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="ClientInstructionVoid_TAGTitle" class="banner__title banner__title--modifier">CANCELLING A LETTER OF INSTRUCTION</h2>
            <ul class="banner__nav">
                <li><a id="TagHome2" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li>CANCELLING A LETTER OF INSTRUCTION</li>
            </ul>
            <p id="TAG_INFO_ANULAR_CARTA" class="banner__description banner__description--modifier">This option allows you to cancel a letter of instruction.</p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="ClientInstructionVoid_TAGTitle" class="banner__title banner__title--modifier">CANCELLING A LETTER OF INSTRUCTION</h2>

            <ul class="banner__nav">
                <li><a id="TagHome2" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="Cancelling_Letter_instruction">CANCELLING A LETTER OF INSTRUCTION</li>
            </ul>
            <p id="TAG_INFO_ANULAR_CARTA" class="banner__description banner__description--modifier">This option allows you to cancel a letter of instruction.</p>

        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="section" id="div_clientInstructionVoid">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="ClientInstructionVoid_TAGNumeroControl" for="numero_de_control">Client</label>
                            <input type="text" id="numero_de_control"
                                   title="Número de Control"
                                   class="input input--form inputFormulario requerido_ACI"
                                   maxlength="15"
                                   onkeypress="return onlyDigits(event,'noDec');"
                                   onchange="validarCamposAnularCarta();">
                        </div>
                        <div class="section__field">
                            <label id="ClientInstructionVoid_TAGClient" for="ci_client_cancel">Client</label>
                            <div class="select-section select-section--form">
                                <select id="ci_client_cancel"
                                        title="Cliente"
                                        class="select-section__select select-section__select--form selectFormulario requerido_ACI"
                                        onchange="validarCamposAnularCarta();">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <input type="hidden" id="instrucciones_cliente_consultar" value="Verificar" class="boton">
                    </div>
                </div>
                <div id="div_clientInstructionCancel2" class="section__row section__row--spacebetween oculto">
                    <div class="section__buttons">
                        <button class="section__button button button--full" id="instrucciones_cliente_anular">ADD</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>



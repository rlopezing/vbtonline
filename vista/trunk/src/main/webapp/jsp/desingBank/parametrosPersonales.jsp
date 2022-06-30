<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="DB_titulo_PP">Dise&ntilde;e su Banco / Par&aacute;metros Personales </h1>
</div>
<fieldset style="width:90%; margin-left:4%;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_PARAMETROS_PERSONALES">This option allows you to change the minimum and maximum limits for internal and external transfers. You must confirm such changes through a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_parametrosPersonales">

    <fieldset class=" div_consultaTransfers">
        <legend id="tagCuentaPredeterminada" >Cuenta Predeterminada para operaciones de Transferencia</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="60%">
                    <select id="Accounts_PP" title="Account" class="selectFormulario_PP requerido_PP" >

                    </select>

                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset  class=" div_consultaTransfers">
        <legend id="tagDentroVBT" >Par&aacute;metros de Transferencia dentro de VBT</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PP_numeroMaxTransDiarias" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                 </td>
                <td>
                    &lt;%&ndash;<select id="numeroMaxTransDiarias_PP" title="Número máximo de Transferencias Diarias" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="numeroMaximoTransfDiarias_PP" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>

            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransDiarias" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="montoMaxTransDiarias_PP" title="Monto máximo de Transferencias Diarias" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="montoMaxTransDiarias_PP" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px"  onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMinTransOpe" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="montoMinTransOpe_PP" title="Monto mínimo de Transferencias por Operación" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="montoMinTransOpe_PP" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransOpe" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="montoMinTransDiarias_PP" title="Monto maximo de Transferencia por Operación" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="montoMinTransDiarias_PP" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class=" div_consultaTransfers">
        <legend id="tagHaciaOtrosBancos" >Hacia Otros Bancos</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PP_numeroMaxTransDiarias_OB" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="PP_numeroMaxTransDiarias_OB" title="Número máximo de Transferencias Diarias" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="PP_numeroMaxTransDiarias_OB" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransDiarias_OB" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="PP_montoMaxTransDiarias_OB" title="Monto máximo de Transferencias Diarias" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="PP_montoMaxTransDiarias_OB" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMinTransOpe_OB" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="PP_montoMinTransOpe_OB" title="Monto mínimo de Transferencias por Operación" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="PP_montoMinTransOpe_OB" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransOpe_OB" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    &lt;%&ndash;<select id="PP_montoMinTransDiarias_OB" title="Monto maximo de Transferencia por Operación" class="selectFormulario_PP" >&ndash;%&gt;

                    &lt;%&ndash;</select>&ndash;%&gt;
                        <input id="PP_montoMaxTransOpe_OB" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">

        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td align="right">
                    <input  type="button" id="btn_PP_aceptar" value="Aceptar" class="boton 0000000045_2">
                    <input  type="button" id="btn_PP_cancelar" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>

</div>
<div id="metodosParametros" class=" oculto" >
    <fieldset  class=" div_consultaTransfers">
        <div id="metodosValidacionParametros">

        </div>
    </fieldset>
    <fieldset  class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <input  type="button" id="btn_PP_validar_clave" value="Aceptar" class="boton">
                    <input  type="button" id="btn_PP_cancelar_clave" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="DB_titulo_PP"  class="banner__title banner__title--modifier">
                DESIGN BANK / PERSONAL PARAMETERS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome25" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>DESIGN BANK</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>PERSONAL PARAMETERS</li>
            </ul>
            <p id="TAG_INFO_PARAMETROS_PERSONALES" class="banner__description banner__description--modifier">
                This option allows you to change the minimum and maximum limits for internal and external transfers. You must confirm such changes through a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="DB_titulo_PP"  class="banner__title banner__title--modifier">
                DESIGN BANK / PERSONAL PARAMETERS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome25" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="PERSONAL_PARAMETERS_DESIGN_BANK">DESIGN BANK</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="PERSONAL_PARAMETERS">PERSONAL PARAMETERS</li>
            </ul>
            <p id="TAG_INFO_PARAMETROS_PERSONALES" class="banner__description banner__description--modifier">
                This option allows you to change the minimum and maximum limits for internal and external transfers. You must confirm such changes through a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_clientInstruction" class="section">
        <div id="div_parametrosPersonales" class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span class="section__title" id="tagCuentaPredeterminada">Default Account for Transfer Operations</span>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__field">
                        <div class="select-section select-section--form">
                            <select id="Accounts_PP"
                                    title="Accounts"
                                    class="select-section__select select-section__select--form selectFormulario_PP requerido_PP">

                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="form-transfer">
                    <div class="form-transfer__container">
                        <div class="form-transfer__grid">
                            <div class="form-transfer__item">
                                <div id="tagDentroVBT" class="form-transfer__field bold">Transfer Parameters In VBT</div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_numeroMaxTransDiarias" for="numeroMaximoTransfDiarias_PP">Number of Transfers Maximum by Day</label>
                                    <input id="numeroMaximoTransfDiarias_PP" class="input input--form inputFormulario_PP requerido_PP"
                                           type="text" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_montoMaxTransDiarias" for="montoMaxTransDiarias_PP">Maximum Amount of Daily Transfers</label>
                                    <input id="montoMaxTransDiarias_PP" class="input input--form inputFormulario_PP requerido_PP" type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                            <div class="form-transfer__item">
                                <div class="form-transfer__field"></div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_montoMinTransOpe" for="montoMinTransOpe_PP">Minimum Amount of Transfer by Operation</label>
                                    <input id="montoMinTransOpe_PP" class="input input--form inputFormulario_PP requerido_PP" type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_montoMaxTransOpe" for="montoMinTransDiarias_PP">Maximum Amount of Transfer by Operation</label>
                                    <input id="montoMinTransDiarias_PP" class="input input--form inputFormulario_PP requerido_PP" type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="form-transfer__grid">
                            <div class="form-transfer__item">
                                <div id="tagHaciaOtrosBancos" class="form-transfer__field bold">Transfer Parameters To Other Banks</div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_numeroMaxTransDiarias_OB" for="PP_numeroMaxTransDiarias_OB">Number of Transfers Maximum by Day:</label>
                                    <input id="PP_numeroMaxTransDiarias_OB" class="input input--form inputFormulario_PP requerido_PP"
                                           type="text" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_montoMaxTransDiarias_OB" for="PP_montoMaxTransDiarias_OB">Maximum Amount of Daily Transfers</label>
                                    <input id="PP_montoMaxTransDiarias_OB"class="input input--form inputFormulario_PP requerido_PP" type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                            <div class="form-transfer__item">
                                <div class="form-transfer__field"></div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_montoMinTransOpe_OB" for="PP_montoMinTransOpe_OB">Minimum Amount of Transfer by Operation</label>
                                    <input id="PP_montoMinTransOpe_OB" class="input input--form inputFormulario_PP requerido_PP" type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PP_montoMaxTransOpe_OB" for="PP_montoMaxTransOpe_OB">Maximum Amount of Transfer by Operation</label>
                                    <input id="PP_montoMaxTransOpe_OB" class="input input--form inputFormulario_PP requerido_PP" type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="form-transfer__spacebetween">
                            <span class="form-transfer__mandaroty"></span>
                            <div class="form-transfer__buttons">
                                <button class="form-transfer__button form-transfer__button--next button button--white"
                                        id="btn_PP_cancelar">
                                    CANCEL
                                </button>
                                <button class="form-transfer__button form-transfer__button--next button"
                                        id="btn_PP_aceptar">
                                    ACCEPT
                                </button>
                            </div>
                        </div>
                        <div id="metodosParametros" class="form-transfer__spacebetween oculto">
                            <div id="metodosValidacionParametros">
                            </div>
                            <div class="form-transfer__buttons">
                                <button class="form-transfer__button form-transfer__button--next button button--white"
                                        id="btn_PP_cancelar_clave">
                                    CANCEL
                                </button>
                                <button class="form-transfer__button form-transfer__button--next button"
                                        id="btn_PP_validar_clave">
                                    ACCEPT
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../footer.jsp"/>--%>

<%--
<div>
    <h1 id="DB_titulo_PG">Par&aacute;metros Globales VBT </h1>
</div>

<div id="div_parametrosEmpresa">
    <fieldset  class="formulario_fieldset div_consulta">

        <legend id="tagTipoParametro" >Seleccione</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="77%">
                    <select id="tipoParametroGlobal" title="Tipo Parametro" class="obligatorioTOC selectFormulario_TOCVBT" onchange="cargarParametrosGlobalesBC();">
                        &lt;%&ndash;<option value="GC" selected="selected">Globales Clientes</option>&ndash;%&gt;
                        &lt;%&ndash;<option value="GFC">Globales Firmas Conjuntas</option>&ndash;%&gt;
                    </select>
                </td>
            </tr>
        </table>

    </fieldset>
    <fieldset class=" div_consultaTransfers">
        <legend id="tagDentroVBT" >Par&aacute;metros de Transferencia dentro de VBT</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PG_numeroMaxTransDiarias" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    <input id="numeroMaximoTransfDiarias_PG" type="text" title="Numero maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransDiarias" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    <input id="montoMaxTransDiarias_PG" type="text" title="Monto maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px"  onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMinTransOpe" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <input id="montoMinTransOpe_PG" type="text" title="Monto minimo de Transferencias por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransOpe" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <input id="montoMinTransDiarias_PG" type="text" title="Monto maximo de Transferencia por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class=" div_consultaTransfers">
        <legend id="tagHaciaOtrosBancos" >Hacia Otros Bancos</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PG_numeroMaxTransDiarias_OB" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_numeroMaxTransDiarias_OB" type="text" title="Numero maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransDiarias_OB" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_montoMaxTransDiarias_OB" type="text" title="Monto maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMinTransOpe_OB" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_montoMinTransOpe_OB" type="text" title="Monto minimo de Transferencias por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransOpe_OB" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_montoMaxTransOpe_OB" type="text" title="Monto maximo de Transferencia por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">
        <legend id="tagParametrosGenerales" >Par&aacute;metros Generales</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td style="width: 1140px">
                    <span id="tag_PG_saldoMinimoCuenta" class="datos">Saldo M&iacute;nimo en la Cuenta </span><span class="datos">:</span>
                </td>
                <td style="width: 400px">
                    <input id="PG_saldoMinimoCuenta" type="text" title="Saldo Mínimo en la Cuenta" class="inputFormulario_PG requerido_PG"  style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');"  onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <input  type="button" id="btn_PG_aceptar" value="Aceptar" class="boton 0000000071_2">
                    <input  type="button" id="btn_PG_cancelar" value="Cancelar" class="boton">
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
            <h2 id="DB_titulo_PG"  class="banner__title banner__title--modifier">
                PARÁMETROS GLOBALES
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome35" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>PARÁMETROS GLOBALES</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="DB_titulo_PG"  class="banner__title banner__title--modifier">
                PARÁMETROS GLOBALES
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome35" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>PARÁMETROS GLOBALES</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_clientInstruction" class="section">
        <div id="div_parametrosEmpresa" class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span class="section__title" id="tagTipoParametro">Parametros</span>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__field">
                        <div class="select-section select-section--form">
                            <select id="tipoParametroGlobal"
                                    title="Tipo Parametro"
                                    class="select-section__select select-section__select--form <%--obligatorioTOC selectFormulario_TOCVBT--%>"
                                    onchange="cargarParametrosGlobalesBC();"
                            >

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
                                <div id="tagDentroVBT" class="form-transfer__field bold">Parámetros de Transferencia dentro de VBT</div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_numeroMaxTransDiarias" for="numeroMaximoTransfDiarias_PG">Número Máximo de Transferencias Diarias </label>
                                    <input id="numeroMaximoTransfDiarias_PG"
                                           title="Numero maximo de Transferencias Diarias"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_montoMaxTransDiarias" for="montoMaxTransDiarias_PG">Monto Máximo de Transferencia Diarias </label>
                                    <input id="montoMaxTransDiarias_PG"
                                           title="Monto maximo de Transferencias Diarias"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                            <div class="form-transfer__item">
                                <div class="form-transfer__field"></div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_montoMinTransOpe" for="montoMinTransOpe_PG">Monto Mínimo de Transferencia por Operación </label>
                                    <input id="montoMinTransOpe_PG"
                                           title="Monto minimo de Transferencias por Operacion"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_montoMaxTransOpe" for="montoMinTransDiarias_PG">Monto Máximo de Transferencia por Operación </label>
                                    <input id="montoMinTransDiarias_PG"
                                           title="Monto maximo de Transferencia por Operacion"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="form-transfer__grid">
                            <div class="form-transfer__item">
                                <div id="tagHaciaOtrosBancos" class="form-transfer__field bold">Hacia Otros Bancos</div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_numeroMaxTransDiarias_OB" for="PG_numeroMaxTransDiarias_OB">Número Máximo de Transferencias Diarias</label>
                                    <input id="PG_numeroMaxTransDiarias_OB"
                                           title="Numero maximo de Transferencias Diarias"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_montoMaxTransDiarias_OB" for="PG_montoMaxTransDiarias_OB">Monto Máximo de Transferencia Diarias </label>
                                    <input id="PG_montoMaxTransDiarias_OB"
                                           title="Monto maximo de Transferencias Diarias"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                            <div class="form-transfer__item">
                                <div class="form-transfer__field"></div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_montoMinTransOpe_OB" for="PG_montoMinTransOpe_OB">Monto Mínimo de Transferencia por Operación </label>
                                    <input id="PG_montoMinTransOpe_OB"
                                           title="Monto minimo de Transferencias por Operacion"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_montoMaxTransOpe_OB" for="PG_montoMaxTransOpe_OB">Monto Máximo de Transferencia por Operación </label>
                                    <input id="PG_montoMaxTransOpe_OB"
                                           title="Monto maximo de Transferencia por Operacion"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                    />
                                </div>
                            </div>
                        </div>
                        <div class="form-transfer__grid">
                            <div class="form-transfer__item">
                                <div id="tagParametrosGenerales" class="form-transfer__field bold">Parámetros Generales</div>
                                <div class="form-transfer__field">
                                    <label id="tag_PG_saldoMinimoCuenta" for="PG_numeroMaxTransDiarias_OB">Saldo Mínimo en la Cuenta </label>
                                    <input id="PG_saldoMinimoCuenta"
                                           title="Saldo Mínimo en la Cuenta"
                                           class="input input--form inputFormulario_PG requerido_PG"
                                           type="text"
                                           onblur="this.value=formatCurrency(this.value,true,2,',');"
                                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);" />
                                </div>
                            </div>
                        </div>
                        <div class="form-transfer__spacebetween">
                            <span class="form-transfer__mandaroty"></span>
                            <div class="form-transfer__buttons">
                                <button class="form-transfer__button form-transfer__button--next button button--white"
                                        id="btn_PG_cancelar">
                                    Cancelar
                                </button>
                                <button class="form-transfer__button form-transfer__button--next button"
                                        id="btn_PG_aceptar">
                                    Aceptar
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>

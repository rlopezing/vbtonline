<%--

<div id="filtro_log_sms">

    <div>
        <h1 id="Titulo_Bitacora_SMS"> Log Sms </h1>
    </div>
    <fieldset id="log_sms"  class="div_consulta">
        <legend>Motor de b&uacute;squeda</legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%">
            <tr>

                <td class="datos2" id="tagSMS_ConFiltro">Contrato:</td>
                <td class="datos" >
                    <input type="TEXT" id="sms_contratoFiltro" title="Contrato" style="width: 200px;"  maxlength="16" class="inputFormulario" >
                </td>


                <td class="datos2" id="tagSMS_UsuFiltro">Usuario:</td>
                <td class="datos" >
                    <input type="TEXT" id="SMS_usuarioFiltro" title="Usuario" style="width: 200px;"  maxlength="16" class="inputFormulario" >
                </td>


                <td class="datos2" id="tagSMS_TelFiltro">Telefono:</td>
                <td class="datos" >
                    <input type="TEXT" id="SMS_telefonoFiltro" title="Telefono" style="width: 200px;"  maxlength="16" class="inputFormulario" >
                </td>

            </tr>

            <tr>
                <td class="datos2" id="tagEstatusFiltro">Estatus:</td>
                <td class="datos">
                    <select  id="estatusFiltroSMS" title="Estatus" class="selectFormulario" style="width: 200px">

                    </select>
                </td>



                <td class="datos2"><span id="SMS_TAGFechaDesde">Desde</span><span>:</span></td>
                <td class="datos">

                    <input type="text" title="Fecha desde" id="fechaDesdeFiltroSMS" class="inputFormulario_sms fechadias" tabindex="2">
                    <label class="datos">dd/mm/yyyy</label>
                </td>

                <td class="datos2">
                    <span id="sms_TAGFechaHasta">Hasta</span><span>:</span>
                </td>
                <td class="datos">


                    <input type="text" title="Fecha hasta" id="fechaHastaFiltroSMS"  class="inputFormulario_sms fechadias" tabindex="2">
                    <label class="datos">dd/mm/yyyy</label>
                </td>
            </tr>

            <tr>

                <td class="datos2" id="tagMotivoFiltro">Motivo</td>
                <td class="datos">
                    <select  id="motivoFiltroSMS" title="Motivo" class="selectFormulario" style="width: 200px">

                    </select>
                </td>
                <td colspan="6" class="botones_formulario">
                    <input type="button" id="sms_search" value="Buscar" class="botonEDOCuenta">
                    <input type="button" id="sms_reset" value="Limpiar" class="botonEDOCuenta">
                </td>
            </tr>
        </table>

    </fieldset>
    <fieldset id="div_tabla_consulta_sms"  class="div_consulta">
      <div   id="div_tabla_consulta_resultado">

      </div>
        <div id="paginacion_tabla_consulta_paginacion_sms" class="div_tabla_consulta">

        </div>
    </fieldset>





</div>
--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_Bitacora" class="banner__title banner__title--modifier">
                Logs SMS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome40" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="blog_sms_tag_bitacora">BITÁCORA</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="blog_sms_tag_log_sms">LOGS SMS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_Bitacora" class="banner__title banner__title--modifier">
                Logs SMS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome40" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="blog_sms_tag_bitacora">BITÁCORA</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="blog_sms_tag_log_sms">LOGS SMS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main id="filtro_log_sms" class="main main--modifier">
    <div class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                </div>
            </div>
            <div class="section__content">
                <div id="log_sms" class="form-filter">
                    <span id="blog_sms_tag_titulo_motor" class="form-filter__title">Motor de búsqueda</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagSMS_ConFiltro" class="form-filter__label" for="sms_contratoFiltro">Contrato:</label>
                            <input
                                    id="sms_contratoFiltro"
                                    title="Contrato"
                                    type="text"
                                    maxlength="16"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagSMS_UsuFiltro" class="form-filter__label" for="SMS_usuarioFiltro">Usuario:</label>
                            <input
                                    id="SMS_usuarioFiltro"
                                    title="Usuario"
                                    type="text"
                                    maxlength="16"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagSMS_TelFiltro" class="form-filter__label" for="SMS_telefonoFiltro">Telefono:</label>
                            <input
                                    id="SMS_telefonoFiltro"
                                    title="Telefono"
                                    type="text"
                                    maxlength="16"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="sms_tagEstatusFiltro" class="form-filter__label" for="estatusFiltroSMS">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="estatusFiltroSMS"
                                        title="Estatus"
                                        class="select-section__select select-section__select--form selectFormulario"
                                >
                                </select>
                                <img
                                        class="select-section__icon select-section__icon--form"
                                        src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                        alt=""
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="SMS_TAGFechaDesde" class="form-filter__label" for="fechaDesdeFiltroSMS">Desde</label>
                            <input
                                    id="fechaDesdeFiltroSMS"
                                    title="Fecha desde"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_sms fechadias"
                                    placeholder="dd/mm/yyyy"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="sms_TAGFechaHasta" class="form-filter__label" for="fechaHastaFiltroSMS">Hasta</label>
                            <input
                                    id="fechaHastaFiltroSMS"
                                    title="Fecha hasta"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_sms fechadias"
                                    placeholder="dd/mm/yyyy"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagMotivoFiltro" class="form-filter__label" for="motivoFiltroSMS">Motivo</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="motivoFiltroSMS"
                                        title="Motivo"
                                        class="select-section__select select-section__select--form selectFormulario"
                                >
                                </select>
                                <img
                                        class="select-section__icon select-section__icon--form"
                                        src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                        alt=""
                                />
                            </div>
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="sms_reset"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="sms_search"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <div id="div_tabla_consulta_resultado">

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
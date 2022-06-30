<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->

<%--
<div id="div_bitacora" class="div_tabla_consulta">

    <div>
        <h1 id="Titulo_Bitacora"> Bitacora </h1>
    </div>
<fieldset class="div_consulta">
    <legend>Motor de b&uacute;squeda</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="datos2" id="tagBitacora_UsuFiltro">Usuario:</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_usuarioFiltro" title="Usuario" style="width: 200px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>

            <td class="datos2" id="TagBitacora_accion" title="Acción">Acci&oacute;n:</td>
            <td class="datos">
                <select  id="bitacora_accion" title="Accion" class="selectFormulario_bitacora" style="width:200px;"  >

                </select>
            </td>
            <td class="datos2" id="TagBitacora_objeto" >Objeto Afectado:</td>
            <td class="datos">
                <select  id="bitacora_objeto" title="Objeto Afectado" class="selectFormulario_bitacora" style="width:200px;"  >

                </select>
            </td>
        </tr>
        <tr>
            <td class="datos2"><span id="bitacora_TAGFechaDesde">Desde</span><span>:</span></td>
            <td class="datos">

                &lt;%&ndash;esto debe ser un calendario que permita seleccionar la fecha&ndash;%&gt;
                <input type="text" title="Fecha desde" id="fechaDesdeFiltroBitacora" class="inputFormulario_bitacora fechadias" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos2">
                <span id="bitacora_TAGFechaHasta">Hasta</span><span>:</span>
            </td>
            <td class="datos">

                &lt;%&ndash;esto debe ser un calendario que permita seleccionar la fecha&ndash;%&gt;
                <input type="text" title="Fecha hasta" id="fechaHastaFiltroBitacora"  class="inputFormulario_bitacora fechadias" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos2" id="tagBitacora_Ultimos">&Uacute;ltimos:</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_dias" title="Ultimos" style="width: 100px;"  maxlength="16" class="inputFormulario_bitacora fechadias" >
            </td>
        </tr>
        <tr>

            <td class="datos2" id="tagBitacora_idobjeto">
                    Id del Objeto:
            </td>
            <td class="datos">
                <input type="TEXT" id="bitacora_idobjeto" title="Id del Objeto" style="width: 100px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>
            <td class="datos2">IP</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_ip" title="Ultimos" style="width: 100px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>

            <td class="datos2" id="tagBitacora_ComentarioFiltro">Comentario:</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_comentarioFiltro" title="Comentario" style="width: 200px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>


        </tr>

        <tr>
            <td colspan="6" class="botones_formulario">
                <input type="button" id="bitacora_search" value="Buscar" class="botonEDOCuenta">
                <input type="button" id="bitacora_reset" value="Limpiar" class="botonEDOCuenta">
            </td>
        </tr>
     </table>

</fieldset>
<fieldset class="div_consulta">

    <div id="div_tabla_bitacora" class="div_tabla_bitacora">

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
                Bitacora
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome39" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagBitacora1">BITÁCORA</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagBitacora2">BITÁCORA</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_Bitacora" class="banner__title banner__title--modifier">
                Bitacora
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome39" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagBitacora1">BITÁCORA</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tagBitacora2">BITÁCORA</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main id="div_bitacora" class="main main--modifier">
    <div class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span id="titulo_tag_motor_busqueda" class="form-filter__title">Motor de búsqueda</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagBitacora_UsuFiltro" class="form-filter__label" for="bitacora_usuarioFiltro">Usuario</label>
                            <input
                                    id="bitacora_usuarioFiltro"
                                    title="Usuario"
                                    type="text"
                                    maxlength="16"
                                    class="form-filter__input input input--form inputFormulario_bitacora"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="TagBitacora_accion" class="form-filter__label" for="bitacora_accion">Acción</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="bitacora_accion"
                                        title="Acción"
                                        class="select-section__select select-section__select--form selectFormulario_bitacora"
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
                            <label id="TagBitacora_objeto" class="form-filter__label" for="bitacora_objeto">Objeto Afectado</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="bitacora_objeto"
                                        title="Objeto Afectado"
                                        class="select-section__select select-section__select--form selectFormulario_bitacora"
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
                            <label id="bitacora_TAGFechaDesde" class="form-filter__label" for="fechaDesdeFiltroBitacora">Desde</label>
                            <input
                                    id="fechaDesdeFiltroBitacora"
                                    title="Desde"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_bitacora fechadias"
                                    placeholder="dd/mm/yyyy"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="bitacora_TAGFechaHasta" class="form-filter__label" for="fechaHastaFiltroBitacora">Hasta</label>
                            <input
                                    id="fechaHastaFiltroBitacora"
                                    title="Hasta"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_bitacora fechadias"
                                    placeholder="dd/mm/yyyy"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagBitacora_Ultimos" class="form-filter__label" for="bitacora_dias">Últimos</label>
                            <input
                                    id="bitacora_dias"
                                    title="Últimos"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_bitacora fechadias"
                                    maxlength="16"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagBitacora_idobjeto" class="form-filter__label" for="bitacora_idobjeto">Id del Objeto</label>
                            <input
                                    id="bitacora_idobjeto"
                                    title="Id del Objeto"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_bitacora"
                                    maxlength="16"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="bitacora_ip">IP</label>
                            <input
                                    id="bitacora_ip"
                                    title="IP"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_bitacora"
                                    maxlength="16"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagBitacora_ComentarioFiltro" class="form-filter__label" for="bitacora_comentarioFiltro">Comentarios</label>
                            <input
                                    id="bitacora_comentarioFiltro"
                                    title="Comentario"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_bitacora"
                                    maxlength="16"
                            />
                        </div>

                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="bitacora_reset"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="bitacora_search"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <div id="div_tabla_bitacora">

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>


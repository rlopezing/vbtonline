<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="tag_titulo_VisorDeSucesos"> Visor de Sucesos</h1>
</div>

<fieldset class="div_consulta">
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr id="fechas_sucesos">
            <td class="datos">
                <span id="sucesos_TAGDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_sucesos" class="inputFormulario_sucesos requeridoFecha_sucesos" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="sucesos_TAGHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_sucesos"  class="inputFormulario_sucesos requeridoFecha_sucesos" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>

            <td  class="botones_formulario">
                <input type="button" id="consultar_sucesos" value="Consultar" class="botonEDOCuenta">
                <input type="button" id="limpiar_sucesos" value="Limpiar" class="botonEDOCuenta">

            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="div_tabla_consulta_Sucesos" class="div_tabla_consulta">
    </div>
    <div id="paginacion_tabla_consulta_Sucesos" class="div_tabla_consulta">
    </div>
</fieldset>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tag_titulo_VisorDeSucesos" class="banner__title banner__title--modifier">
                Visor de Sucesos
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome38" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_visor_bitacora">BITÁCORA</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_visor_visor">VISOR DE SUCESOS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tag_titulo_VisorDeSucesos" class="banner__title banner__title--modifier">
                Visor de Sucesos
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome38" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_visor_bitacora">BITÁCORA</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_visor_visor">VISOR DE SUCESOS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                </div>
            </div>
            <div class="section__content">
            
                <div id="fechas_Transferencias" class="section__top" style="padding-left: 0rem; display: block;">
                    <div class="section__row section__row--spacebetween" style="padding-left: 2rem;border:none;">
                        <div class="section__form">
                            <div class="section__field">
                                <label id="sucesos_TAGDesde" class="form-filter__label" for="fechaDesdeFiltro_sucesos" style="color: white;">Fecha Desde</label>
                                <input
                                        id="fechaDesdeFiltro_sucesos"
                                        title="Fecha desde"
                                        type="text"
                                        class="form-filter__input input input--form inputFormulario_sucesos requeridoFecha_sucesos"
                                        placeholder="dd/mm/yyyy"
                                />
                            </div>
                           <div class="section__field">
                            <label id="sucesos_TAGHasta" class="form-filter__label" for="fechaHastaFiltro_sucesos" style="color: white;">Fecha Hasta</label>
                            <input
                                    id="fechaHastaFiltro_sucesos"
                                    title="Fecha hasta"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_sucesos requeridoFecha_sucesos"
                                    placeholder="dd/mm/yyyy"
                            />
                            </div>
                        </div>
                        <div class="section__buttons" style="margin-left: 1em;">
                            <input
                            id="limpiar_sucesos"
                            type="button"
                            class="form-filter__button button button--white"
                            value="Limpiar"
                            />
                            <input
                                    id="consultar_sucesos"
                                    type="button"
                                    class="form-filter__button button"
                                    value="Consultar"
                            />
                        </div>
                    </div>
                </div>
                <div class="table">
                    <div id="div_tabla_consulta_Sucesos">

                    </div>
                </div>
            </div>
        </div>
    </div>
</main>


<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div id="div_tabla_consultaContratos" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_PaisNoPermitido"> Administrar Lista de Paises</h1>
    </div>

    <fieldset class="div_consulta">

        <legend>Filtro de consulta</legend>
        <div id="filtroPaisesNoPermitidos">
            <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" border="0">
                <tbody><tr>
                    <td><label  class="datos2">Código del País:</label></td>
                    <td class="datos">
                        <input type="TEXT" id="CodPaisNoPermitido" title="Codigo del País:"  onKeyUp="this.value=this.value.toUpperCase();" class="inputFormulario">
                    </td>
                    <td><label  class="datos2">Nombre del País:</label></td>
                    <td class="datos">
                        <input type="TEXT" id="NomPaisNoPermitido" title="Nombre del País:"  class="inputFormulario">
                    </td>
                    <td class="datos2" id="OJO_tagPaisNoPermitidoFiltro">
                        Transferencia:
                    </td>
                    <td class="datos">
                        <select  id="estatusFiltroPaises" title="Estatus" class="selectFormulario" style="width: 84px">

                        </select>
                    </td>
                    <td class="datos2" id="OJO_tagRevisionPaisFiltro">
                        Revisión:
                    </td>
                    <td class="datos">
                        <select  id="estatusRevisionPais" title="Estatus" class="selectFormulario" style="width: 84px">

                        </select>
                    </td>
                    <td class="botones_formulario">
                        <input type="button" id="searchListaPaisesNoPermitidos" value="Buscar" class="botonDerecha" style="margin-right: 5px">
                    </td>
                        <td>
                        <input type="button" id="resetFiltroPaisesNoPermitidos" value="Limpiar" class="boton" style="margin-left: 5px">
                    </td>
                </tr>

                </tbody></table>
        </div>
    </fieldset>


    <fieldset class="div_consulta">
        <legend> Consulta de Países </legend>
        <div id="div_tabla_consulta_paises" class="div_tabla_consulta_contratosBO">

        </div>
        <div id="paginacion_tabla_consulta_paises" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_PaisNoPermitido" class="banner__title banner__title--modifier">
                Administrar Lista de Paises
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome36" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ADMINISTRAR LISTA DE PAISES</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_PaisNoPermitido" class="banner__title banner__title--modifier">
                Administrar Lista de Paises
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome36" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ADMINISTRAR LISTA DE PAISES</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main id="div_tabla_consultaContratos" class="main main--modifier">
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
                    <span class="form-filter__title">Filtro de consulta</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="CodPaisNoPermitido">Código del País:</label>
                            <input
                                    id="CodPaisNoPermitido"
                                    title="Codigo del País:"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    onkeyup="this.value=this.value.toUpperCase();"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="NomPaisNoPermitido">Nombre del País:</label>
                            <input
                                    id="NomPaisNoPermitido"
                                    title="Nombre del País:"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="OJO_tagPaisNoPermitidoFiltro" class="form-filter__label" for="estatusFiltroPaises">Transferencia</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="estatusFiltroPaises"
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
                            <label id="OJO_tagRevisionPaisFiltro" class="form-filter__label" for="estatusRevisionPais">Revisión</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="estatusRevisionPais"
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
                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="resetFiltroPaisesNoPermitidos"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="searchListaPaisesNoPermitidos"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <span class="table__title">Consulta de Países</span>
                    <div id="div_tabla_consulta_paises" class="div_tabla_consulta_contratosBO"></div>
                </div>
            </div>
        </div>
    </div>
</main>


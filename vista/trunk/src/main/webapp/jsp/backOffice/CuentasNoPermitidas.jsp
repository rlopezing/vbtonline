<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: fescobar
  Date: 06/04/16
  Time: 12:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div id="div_tabla_consultaCuentaNoPermitida" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarCuentasNoPermitidas">Cuentas No Permitidas</h1>
    </div>
    <fieldset class="div_consulta 0000000094_1">
        &lt;%&ndash;<legend>Usuarios</legend>&ndash;%&gt;
        &lt;%&ndash;<label class="datos2"> <a href="../../jsp/backOffice/AgregarUsuario.jsp"id="" >Agregar Usuario </a> </label>&ndash;%&gt;
        <input type="button" id="agregarCuentaNoPermitida" value="Agregar Cuenta" class="botonBackoffice">
    </fieldset>

    <fieldset class="div_consulta">
        <legend>Filtro de consulta</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td class="datos2" id="tagCCNP_BancoFiltro">Código Banco:</td>
                <td class="datos" >
                    <input type="TEXT" id="CCNP_BancoFiltro" title="Banco" style="width: 200px;"  maxlength="15" size="17" class="inputFormulario" >
                </td>

                <td class="datos2" id="tagCCNP_NroCuentaFiltro">Nro de Cuenta:</td>
                <td class="datos">
                    <input type="TEXT" id="CCNP_NroCuentaFiltro" title="Nro Cuenta" style="width: 200px;"  maxlength="55" size="35" class="inputFormulario">
                </td>
            </tr>
            <tr>
                <td colspan="4" class="botones_formulario">
                    <input type="button" id="CCNP_search" value="Buscar" class="boton">

                    <input type="button" id="CCNP_reset" value="Limpiar" class="boton">
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class="div_consulta 0000000094_1">
        &lt;%&ndash;<legend>Usuarios</legend>&ndash;%&gt;
        &lt;%&ndash;<label class="datos2"> <a href="../../jsp/backOffice/AgregarUsuario.jsp"id="" >Agregar Usuario </a> </label>&ndash;%&gt;
        <input type="button" id="CCNP_PermitirCuenta" value="Eliminar" class="botonBackoffice">
    </fieldset>

    <fieldset class="div_consulta">
        <legend > Consultar Cuentas No Permitidas </legend>
        <div id="div_tabla_consultarCuentasNoPermitidas" class="div_tabla_consultarUsuarios">

        </div>
        <div id="paginacion_tabla_consultarCuentasNoPermitidas" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>

<div id="div_tabla_agregarCuentaNoPermitida" style="display: none">
    <div>
        <h1 id="Titulo_agregarCuentaNoPermitida_backofficeACNP">Agregar Cuenta No Permitida</h1>
    </div>
    <fieldset id="ACNP_botones" class="formulario_fieldset div_consulta">
        <input type="button" id="CCNP_InsertarCuentaNoPermitida" value="Agregar" class="boton" >
        <input type="button" id="CCNP_Resetear" value="Limpiar" class="boton" >
        <input type="button" id="CCNP_Cancelar" value="<< Volver" class="botonDerecha" >
    </fieldset>
    <fieldset id="formularioAgregarCuentaNoPermitida" class="formulario_fieldset div_consulta">

        <legend>Datos de la Cuenta</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0"  border="0" width="80%" align="center">
            <tr>
                <td >
                    <label for="ACNP_tipoBanco" id="tagACNPtipoBanco"  class="datos">Tipo Codigo:</label>
                </td>
                <td >
                    <select id="ACNP_tipoBanco" class="selectFormulario obligatorio_ACNP" title="Banco">
                    </select>
                </td>
                <td >
                    <label for="ACNP_codigoBanco"  id="tagACNP_codigoBanco" class="datos">Código Banco:</label>
                </td>
                <td >
                    <input id="ACNP_codigoBanco" type="text" class="obligatorio_ACNP inputFormulario" title="Numero de cuenta" style="width:195px" maxlength="55" size="35"/> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="ACNP_numeroCuenta"  id="tagACNP_numeroCuenta" class="datos">Nro Cuenta:</label>
                </td>
                <td >
                    <input id="ACNP_numeroCuenta" type="text" class="obligatorio_ACNP inputFormulario" title="Numero de cuenta" style="width:195px" maxlength="55" size="35"/> <label class="datos">  * </label>
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
            <h2 id="Titulo_PaisNoPermitido" class="banner__title banner__title--modifier">
                Cuentas No Permitidas
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome37" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>CUENTAS NO PERMITIDAS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_PaisNoPermitido" class="banner__title banner__title--modifier">
                Cuentas No Permitidas
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome37" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>MANTENIMIENTO</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>CUENTAS NO PERMITIDAS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaCuentaNoPermitida" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                    <input type="button" id="agregarCuentaNoPermitida" value="Agregar Cuenta" class="section__button button">
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span class="form-filter__title">Filtro de consulta</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagCCNP_BancoFiltro" class="form-filter__label" for="CCNP_BancoFiltro">Código Banco:</label>
                            <input
                                    id="CCNP_BancoFiltro"
                                    title="Banco"
                                    type="text"
                                    maxlength="15"
                                    size="17"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCCNP_NroCuentaFiltro" class="form-filter__label" for="CCNP_NroCuentaFiltro">Nro de Cuenta:</label>
                            <input
                                    id="CCNP_NroCuentaFiltro"
                                    title="Nro Cuenta"
                                    type="text"
                                    maxlength="55"
                                    size="35"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="CCNP_reset"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="CCNP_search"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <span class="table__title">Consultar Cuentas No Permitidas</span>
                    <div id="div_tabla_consultarCuentasNoPermitidas"></div>
                    <div class="table__spacebetween">
                        <div></div>
                        <div class="table__buttons">
                            <input type="button" id="CCNP_PermitirCuenta" value="Eliminar" class="button button--white">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_agregarCuentaNoPermitida" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_agregarCuentaNoPermitida_backofficeACNP" class="section__title">Agregar Cuenta No Permitida</span>
                    <input type="button" id="CCNP_Cancelar" value="<< Volver" class="section__button button button--white">
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span class="form-filter__title">Filtro de consulta</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagACNPtipoBanco" class="form-filter__label" for="ACNP_tipoBanco">Tipo Codigo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="ACNP_tipoBanco"
                                        title="Banco"
                                        class="select-section__select select-section__select--form selectFormulario obligatorio_ACNP"
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
                            <label id="tagACNP_codigoBanco" class="form-filter__label field-obligatory" for="ACNP_codigoBanco">Código Banco:</label>
                            <input
                                    id="ACNP_codigoBanco"
                                    title="Numero de cuenta"
                                    type="text"
                                    maxlength="55"
                                    size="35"
                                    class="form-filter__input input input--form obligatorio_ACNP inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagACNP_numeroCuenta" class="form-filter__label field-obligatory" for="ACNP_numeroCuenta">Nro Cuenta:</label>
                            <input
                                    id="ACNP_numeroCuenta"
                                    title="Numero de cuenta"
                                    type="text"
                                    maxlength="55"
                                    size="35"
                                    class="form-filter__input input input--form obligatorio_ACNP inputFormulario"
                            />
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="CCNP_Resetear"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="CCNP_InsertarCuentaNoPermitida"
                                type="button"
                                class="form-filter__button button"
                                value="Agregar"
                        />
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
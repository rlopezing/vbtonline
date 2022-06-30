<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div id="div_tabla_consultaCUC_FC" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarUsuariosFC">Usuarios de Firmas Conjuntas en Contratos</h1>
    </div>
    <fieldset class="div_consulta">
        <legend id="tagFiltroConsulta_FC">Filtro de B&uacute;squeda</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td class="datos2" ><span id="tagCUC_UsuFiltro_FC">Usuario:</span></td>
                <td class="datos">
                    <input type="TEXT" id="CUC_usuarioFiltro_FC" title="usuarioFiltro" style="width: 200px;"
                           maxlength="15" size="17" class="inputFormulario">
                </td>

                <td class="datos2" ><span id="tagCUC_NomFiltro_FC">Nombre:</span></td>
                <td class="datos">
                    <input type="TEXT" id="CUC_nombreFiltro_FC" title="CUC_nombreFiltro_FC" style="width: 200px;" maxlength="55"
                           size="35" class="inputFormulario">
                </td>
            </tr>
            <tr>
                <td class="datos2"><span id="tagCUC_CIRIF_FC">C.I./R.I.F:</span></td>
                <td class="datos">
                    <input type="text" title="CIRIF_FC" id="CUC_CIRIF_FC" style="width:200px;" tabindex="2" maxlength="20"
                           size="12" class="inputFormulario">
                </td>
                <td class="datos2"><span id="tagCUC_CARTERA_FC">Cartera N°:</span></td>
                <td class="datos">
                    <input type="text" title="CARTERA_FC" id="CUC_CARTERA_FC" style="width:200px;" tabindex="2" maxlength="20"
                           size="12" class="inputFormulario">
                </td>
            </tr>
            <tr>
                <td class="datos2">
                    <span id="tagCUC_GrupoFiltro_FC">Grupo:</span>
                </td>
                <td class="datos">
                    <select id="CUC_grupoFiltro_FC" class="selectFormulario" title="grupoFiltro_FC" style="width:200px;">
                        <option value="-2" selected="selected">Seleccione</option>
                    </select>
                </td>
                <td class="datos2" >
                    <span id="tagEstatusFiltro_FC">Estatus:</span>
                </td>
                <td class="datos">
                    <select id="CUC_estatusFiltro_FC" title="estatusFiltro_FC" style="width:200px;" class="selectFormulario">
                        <option value="-2" selected="selected">Seleccione</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="4" class="botones_formulario">
                    <input type="button" id="CUC_reset_FC" value="Limpiar" class="boton">
                    <input type="button" id="CUC_search_FC" value="Buscar" class="boton">
                    &nbsp;
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend id="tagConsultarUsuarios_FC">Consultar Usuarios de Firmas Conjuntas en Contratos</legend>
        <div id="div_tabla_consultarUsuariosContratos_FC" class="div_tabla_consultarUsuariosContratos_FC">
        </div>
        <div id="paginacion_tabla_consultarUsuariosContratos_FC" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="div_tabla_detalleCUC_FC" style="display: none">
    <div>
        <h1 id="Titulo_detalleCUC_FC">Consulta Usuario de Firmas Conjuntas en Contratos</h1>
    </div>
    <fieldset id="barra_acciones" class="formulario_fieldset div_consulta">
        <input align="left" type="button" id="btnAccesosCUC_FC" value="Accesos" class="boton">
        <input align="left" type="button" id="btnResetearClaveCUC_FC" value="Reiniciar Contrase&ntilde;a" class="botonGrande 0000000052_5">
        <input align="left" type="button" id="btnEnviarUsuarioCUC_FC" value="Enviar Usuario" class="botonGrande 0000000052_5">
        <input align="left" type="button" id="btnResetearPasswordCUC_FC_Correo" value="Reiniciar Clave Correo" class="0000000073_0 botonGrandeMin ocultarStatus">
        <input align="left" type="button" id="btnEnviarUsuarioCUC_FC_Correo" value="Enviar Usuario Correo" class="botonGrandeMin 0000000073_0 ocultarStatus">
        <input align="right" type="button" id="btnRegresarCUC_FC" value="Regresar" class="botonDerecha">
    </fieldset>

    <fieldset id="formularioDetalleUsuario" class="formulario_fieldset div_consulta">
        <legend id="tagDatosUsuarioCUC_FC">Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="80%" border="0" align="center">
            <tr>
                <td>
                    <label for="usuarioCUC_FC" id="BackOffice_tagUsuarioCUC" class="datos  negrita">Usuario:</label>
                </td>
                <td >
                    <label id="usuarioCUC_FC" class="datos negrita"></label>
                </td>
                <td>
                    <label for="estatusCUC_FC" id="BackOffice_tagEstatusEU" class="datos negrita">Estatus:</label>
                </td>
                <td>
                    &lt;%&ndash;<label id="estatusCUC_FC" class="datos"></label>&ndash;%&gt;
                    <select id="estatusCUC_FC" disabled="disabled" style="width:200px;" class="selectFormulario_FC">
                        <option value="-2" selected="selected">Seleccione</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="nombreCUC_FC" id="BackOffice_tagNombreEU" class="datos negrita">Nombre:</label>
                </td>
                <td>
                    <label id="nombreCUC_FC" class="datos"></label>
                </td>
                <td>
                    <label for="ciRifCUC_FC" id="BackOffice_tagCiRifEU" class="datos negrita">C.I./R.I.F:</label>
                </td>
                <td>
                    <label id="ciRifCUC_FC" class="datos"></label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="telefonoCUC_FC" id="BackOffice_tagTelefonoEU" class="datos negrita">Tel&eacute;fono:</label>
                </td>
                <td>
                    <label id="telefonoCUC_FC" class="datos"></label>
                </td>
                <td>
                    <label for="telefonoCelCUC_FC" id="BackOffice_tagTelefonoCelEU" class="datos negrita">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td>
                    <label id="telefonoCelCUC_FC" class="datos"></label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="direccionCUC_FC" id="BackOffice_tagDireccionEU" class="datos negrita">Direcci&oacute;n:</label>
                </td>
                <td>
                    <label id="direccionCUC_FC" class="datos"></label>
                </td>
                <td>
                    <label for="emailCUC_FC" id="BackOffice_tagEmailEU" class="datos negrita">Email:</label>
                </td>
                <td>
                    <label id="emailCUC_FC" class="datos"></label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="grupoCUC_FC" id="BackOffice_tagGrupoEU" class="datos negrita">Grupo:</label>
                </td>
                <td>
                    &lt;%&ndash;<label id="grupoCUC_FC" class="datos"></label>&ndash;%&gt;
                    <select id="grupoCUC_FC" class="selectFormulario_FC" disabled="disabled" style="width:200px;">
                        <option value="-2" selected="selected">Seleccione</option>
                    </select>
                </td>
            </tr>

        </table>

    </fieldset>
    <fieldset id="RolesFCDetail" class="formulario_fieldset div_consulta oculto">

        <legend id="RolesMainDetail">Roles Asignados</legend>

        <table>
            <tr>
                <td width="40%" valign="top">
                    <div id="rolesDinamicosDetail" style="padding-left:12px;">
                    </div>
                </td>
                <td>
                    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
                        <tr>
                            <td width="100%" rowspan="4" valign="middle" w>
                                <div id="rolesDinamicosDetailLista" class="roles_descrip">
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </fieldset>
</div>

<div  id="div_tabla_consultaOpcionesSistema_CUC_FC">
    <div id="usuarioOpcionesSistema_CUC_FC">
        <h3 id="see_id_cu"> Accesos al Sistema </h3>
        <fieldset class="div_consulta">
            &lt;%&ndash;<legend id="Legend_OpcionesSistema">  </legend>&ndash;%&gt;
            &lt;%&ndash;<label>Para Editar haga click sobre la opcion </label>&ndash;%&gt;
            <div id="div_tabla_UsuarioOpcionesSistema_CUC_FC" class="div_tabla_UsuarioOpcionesSistema">
            </div>

        </fieldset>
    </div>
    <a id="close_x" class="close sprited" href="#">close</a>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuariosFC1" class="banner__title banner__title--modifier">
                Usuarios de Firmas Conjuntas en Contratos
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome42" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagAdmContra">ADMINISTRACIÓN DE CONTRATOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagUsrFirContr">USUARIOS DE FIRMAS CONJUNTAS EN CONTRATOS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuariosFC1" class="banner__title banner__title--modifier">
                Usuarios de Firmas Conjuntas en Contratos
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome42" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagAdmContra">ADMINISTRACIÓN DE CONTRATOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagUsrFirContr">USUARIOS DE FIRMAS CONJUNTAS EN CONTRATOS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaCUC_FC" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span id="tagFiltroConsulta_FC1" class="form-filter__title">Filtro de Búsqueda</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagCUC_UsuFiltro_FC" class="form-filter__label" for="CUC_usuarioFiltro_FC">Usuario:</label>
                            <input
                                    id="CUC_usuarioFiltro_FC"
                                    title="usuarioFiltro"
                                    type="text"
                                    maxlength="15"
                                    size="17"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCUC_CIRIF_FC" class="form-filter__label" for="CUC_CIRIF_FC">C.I./R.I.F:</label>
                            <input
                                    id="CUC_CIRIF_FC"
                                    title="CIRIF_FC"
                                    type="text"
                                    size="12"
                                    maxlength="20"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCUC_GrupoFiltro_FC" class="form-filter__label" for="CUC_grupoFiltro_FC">Grupo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="CUC_grupoFiltro_FC"
                                        title="grupoFiltro_FC"
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
                            <label id="tagCUC_NomFiltro_FC" class="form-filter__label" for="CUC_nombreFiltro_FC">Nombre:</label>
                            <input
                                    id="CUC_nombreFiltro_FC"
                                    title="CUC_nombreFiltro_FC"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCUC_CARTERA_FC" class="form-filter__label" for="CUC_CARTERA_FC">Cartera Nº</label>
                            <input
                                    id="CUC_CARTERA_FC"
                                    title="CARTERA_FC"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="20"
                                    size="12"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagEstatusFiltro_FC" class="form-filter__label" for="CUC_estatusFiltro_FC">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="CUC_estatusFiltro_FC"
                                        title="estatusFiltro_FC"
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
                                id="CUC_reset_FC"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="CUC_search_FC"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <span id="tagConsultarUsuarios_FC1" class="table__title">Consultar Usuarios de Firmas Conjuntas en Contratos</span>
                    <div id="div_tabla_consultarUsuariosContratos_FC">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_detalleCUC_FC" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_detalleCUC_FC" class="section__title">Consulta Usuario de Firmas Conjuntas en Contratos</span>
                    <input type="button" id="btnRegresarCUC_FC" value="Regresar" class="section__button button button--white">
                </div>
            </div>
            <div class="section__content">
                <div id="formularioDetalleUsuario" class="form-filter">
                    <span id="tagDatosUsuarioCUC_FC" class="form-filter__title">Datos del Usuario</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="BackOffice_tagUsuarioCUC" class="form-filter__label">Usuario:</label>
                            <span id="usuarioCUC_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="BackOffice_tagNombreEU" class="form-filter__label">Nombre:</label>
                            <span id="nombreCUC_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="BackOffice_tagTelefonoEU" class="form-filter__label">Teléfono:</label>
                            <span id="telefonoCUC_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="BackOffice_tagDireccionEU" class="form-filter__label">Dirección:</label>
                            <span id="direccionCUC_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="BackOffice_tagGrupoEU" class="form-filter__label" for="grupoCUC_FC">Grupo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="grupoCUC_FC"
                                        disabled="disabled"
                                        class="select-section__select select-section__select--form selectFormulario_FC"
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
                            <label id="BackOffice_tagEstatusEU" class="form-filter__label" for="estatusCUC_FC">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="estatusCUC_FC"
                                        disabled="disabled"
                                        class="select-section__select select-section__select--form selectFormulario_FC"
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
                            <label id="BackOffice_tagCiRifEU" class="form-filter__label">C.I./R.I.F:</label>
                            <span id="ciRifCUC_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="BackOffice_tagTelefonoCelEU" class="form-filter__label">Teléfono Célular:</label>
                            <span id="telefonoCelCUC_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="BackOffice_tagEmailEU" class="form-filter__label">Email:</label>
                            <span id="emailCUC_FC" class="form-filter__value"></span>
                        </div>
                    </div>
                    <div id="RolesFCDetail" class="form-filter__section oculto">
                        <span id="RolesMainDetail" class="form-filter__title">Roles Asignados</span>
                        <div class="form-filter__col2">
                            <div id="rolesDinamicosDetail">
                            </div>
                            <div id="rolesDinamicosDetailLista">
                            </div>
                        </div>
                    </div>
                    <div id="barra_acciones"class="form-filter__buttons">
                        <input type="button" id="btnResetearClaveCUC_FC" value="Reiniciar Contraseña" class="button button--white">
                        <input type="button" id="btnAccesosCUC_FC" value="Accesos" class="button">
                        <input type="button" id="btnEnviarUsuarioCUC_FC" value="Enviar Usuario" class="button">
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>







<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div id="div_tabla_consultaCUC" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_usuariosContrato">Usuarios en Contrato</h1>
    </div>
<fieldset class="div_consulta">
    <div id="filtroUsuariosClientes">
    <legend>Filtro de consulta</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" align="center">
        <tr>
            <td class="datos2" id="tagCUC_UsuFiltro">Usuario:</td>
            <td class="datos">
                <input type="TEXT" id="CUC_usuarioFiltro" title="Usuario" style="width:200px;"  maxlength="16" class="inputFormulario" >
            </td>

            <td class="datos2" id="tagCUC_NomFiltro" >Nombre:</td>
            <td class="datos">
                <input type="TEXT" id="CUC_nombreFiltro" title="Nombre" style="width:200px;"  maxlength="50" class="inputFormulario">
            </td>
        </tr>
        <tr>
            <td class="datos2">C.I./R.I.F:</td>
            <td class="datos">
                <input type="text" title="CI/RIF" id="CUC_CIRIF" style="width:200px;" tabindex="2" maxlength="15" class="inputFormulario">
            </td>
            <td class="datos2" id="tagCUC_AmbitoFiltro">
                Ambito:
            </td>
            <td class="datos">
                <select  id="CUC_ambitoFiltro" title="Ambito" class="selectFormulario" style="width:200px;"  >

                </select>
            </td>
        </tr>
        <tr>

            <td class="datos2" id="tagCUC_GrupoFiltro">
                Grupo:
            </td>
            <td class="datos">
                <select  id="CUC_grupoFiltro" title="Grupo" class="selectFormulario" style="width:200px;"  >

                </select>
            </td>
            <td class="datos2" id="tagEstatusFiltro">
                Estatus:
            </td>
            <td class="datos">
                <select  id="CUC_estatusFiltro" title="Estatus" style="width:200px;" class="selectFormulario"  >

                </select>
            </td>
        </tr>
        <tr>
            <td class="datos2" id="tagCUC_CarteraFiltro">Cartera Nº</td>
            <td class="datos">
                <input type="TEXT" id="CUC_carteraFiltro" title="Cartera" style="width:200px;"  maxlength="16" class="inputFormulario" >
            </td>
            <td colspan="2">
            </td>
        </tr>

        <tr>
            <td colspan="4" class="botones_formulario">
                <input type="button" id="CUC_search" value="Buscar" class="botonEDOCuenta">
                <input type="button" id="CUC_reset" value="Limpiar" class="botonEDOCuenta">
            </td>
        </tr>
    </table>
    </div>
</fieldset>

<fieldset class="div_consulta">
    <legend> Usuarios en Contratos </legend>
    <div id="div_tabla_consultarUsuariosContratos" class="div_tabla_consultarUsuariosContratos">

    </div>
    <div id="paginacion_tabla_consultarUsuariosContratos" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_tabla_editarUsuarioCUC" style="display: none">
    <div>
        <h1 id="Titulo_editarUsuarioContrato"> Usuarios en Contrato / Editar Usuario</h1>
    </div>
    <input type="hidden" value="" id="pantallaMostrar">
    <fieldset id="formulario_EU_CUC" class="formulario_fieldset div_consulta">
        <input align="left" type="button" id="btnSalvarEU_CUC" value="Guardar" class="botonMin 0000000029_2 0000000029_4">
        <input align="left" type="button" id="btnDeshacerEU_CUC" value="Deshacer" class="botonMin 0000000029_2">
        <input align="left" type="button" id="btnAccesosEspecialesEU_CUC" value="Accesos Especiales" class="0000000030_2 botonGrandeMin">
        <input align="left" type="button" id="btnResetearPasswordEU_CUC" value="Reiniciar Clave SMS" class="0000000029_5 botonGrandeMin ocultarStatus">
        <input align="left" type="button" id="btnEnviarUsuarioEU_CUC" value="Enviar Usuario SMS" class="botonGrandeMin 0000000029_5  ocultarStatus">
        <input align="left" type="button" id="btnResetearPasswordEU_CUC_Correo" value="Reiniciar Clave Correo" class="0000000073_0 botonGrandeMin ocultarStatus">
        <input align="left" type="button" id="btnEnviarUsuarioEU_CUC_Correo" value="Enviar Usuario Correo" class="botonGrandeMin 0000000073_0 ocultarStatus">
        <input align="right" type="button" id="btnBackEU_CUCbtnBackEU_CUC" value="Volver" class="botonDerechaMin">
    </fieldset>

    <fieldset id="formularioEditarUsuarioCUC" class="formulario_fieldset div_consulta">

        <legend>Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td width="10%">
                    <label for="usuarioEU_CUC" id="tagUsuarioEU_CUC"  class="datos2">Usuario:</label>
                </td>
                <td width="10%">
                    <b><label class="datos" id="usuarioEU_CUC" > </label></b>
                </td>
                <td width="10%">
                    <label for="estatusEU_CUC" id="tagEstatusEU_CUC"  class="datos2">Estatus:</label>
                </td>
                <td width="10%">
                    <select id="estatusEU_CUC" onchange="cambioEstatusEUC();" class="obligatorio_EUCBO" style="width: 200px" >

                    </select>
                    <label class="datos">*</label>
                </td>

            </tr>
            <tr>
                <td >
                    <label for="nombreEU_CUC" id="tagNombreEU_CUC"  class="datos2">Nombre:</label>
                </td>
                <td >
                   <b> <label id="nombreEU_CUC" class="datos"  /></b>
                </td>
                <td >
                    <label for="ciRifEU_CUC"  id="tagCiRifEU_CUC" class="datos2">C.I./R.I.F:</label>
                </td>
                <td >
                   <b> <label id="ciRifEU_CUC" class="datos"/></b>
                </td>

            </tr>
            <tr>
                <td >
                    <label for="emailEU_CUC" id="tagEmailEU_CUC" class="datos2">Email:</label>
                </td>
                <td >
                    <select id="emailEU_CUC" class="obligatorio_EUCBO cambioStatus" >

                    </select>
                </td>
                <td>
                    <label for="grupoEU_CUC"  id="tagGrupoEU_CUC" class="datos2">Grupo:</label>
                </td>
                <td>
                    <select id="grupoEU_CUC" class="obligatorio_EUCBO cambioStatus" style="width: 200px" title="Grupo" >

                    </select>
                    <label class="datos" >*</label>
                </td>
            </tr>
            <tr>
                <td >
                    <label for="telefonoEU_CUC" id="tagTelefonoEU_CUC"  class="datos2">Tel&eacute;fono:</label>
                </td>
                <td >
                    <select id="telefonoEU_CUC" class="cambioStatus" title="Telefono">

                    </select>
                </td>
                <td >
                    <label for="telefonoCelEU_CUC" id="tagTelefonoCelEU_CUC"  class="datos2">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td >
                    <input id="codigoPaisUC" type="tel" style="width:90px;text-align:center;"  disabled="true"  maxlength="4" size="4" class='codigoPais obligatorio_EUFC mascaraCodPais'/>
                    <select id="telefonoCelEU_CUC" class="obligatorio_EUCBO cambioStatus" title='Telefono Celular'>

                    </select>
                    <label class="datos" >*</label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="direccionEU_CUC" id="tagDireccionEU_CUC"  class="datos2">Direcci&oacute;n:</label>
                </td>
                <td  colspan="3">
                   <b><label id="direccionEU_CUC" class="datos"/> </b>

                </td>
            </tr>
            <tr>

                <td>
                    <label for="ambitoEU_CUC"  id="tagAmbitoEU_CUC" class="datos2">Ambito:</label>
                </td>
                <td>
                    <select id="ambitoEU_CUC" class="obligatorio_EUCBO cambioStatus" style="width: 200px" >

                    </select>
                    <label class="datos" >*</label>
                </td>
            </tr>

        </table>
    </fieldset>




</div>

<div  id="div_tabla_consultaOpcionesSistema_CUC">
    <div id="usuarioOpcionesSistema_CUC">
        <h3 id="see_id_cu"> Opciones Sistema </h3>

        <fieldset class="div_consulta">
            &lt;%&ndash;<legend id="Legend_OpcionesSistema">  </legend>&ndash;%&gt;
            <label>Para Editar haga click sobre la opcion </label>
            <div id="div_tabla_UsuarioOpcionesSistema_CUC" class="div_tabla_UsuarioOpcionesSistema_CUC">
            </div>

        </fieldset>
    </div>
    <div  id="div_tabla_consultaUsuarioOpcionesPermiso_CUC" style="display: none">
        <h3 id="see_id_cup"> Opciones Permisos </h3>
        <fieldset class="div_consulta">
            <input type="button" id="CUOP_back_CUC" value="Volver" class="boton" >
            <input type="button" id="CUOP_save_CUC" value="Guardar" class="boton" >
            <input type="button" id="CUOP_eliminar_CUC" value="Eliminar Permisologias" class="botonGrande" >
            <input type="hidden" id="codOpc_CUC" value="" >

        </fieldset>
        <fieldset class="div_consulta">
            <legend id="Legend_UsuarioOpcionesSistemaPermisos_CUC"></legend>
            <label>Para Editar haga click sobre la opcion </label>
            <div id="div_tabla_usuarioOpcionesPermisos_CUC" class="div_tabla_usuarioOpcionesPermisos_CUC">
            </div>

        </fieldset>
    </div>


    <a id="close_x" class="close sprited" href="#">close</a>

    <div id="div_mensajes_info_desc" class="info_descp oculto">
        <div id="mensajes_info_desc">
            error
        </div>
        <div id="cerrar_div_mensajes_info_desc">[X]</div>
    </div>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_usuariosContrato" class="banner__title banner__title--modifier">
                Usuarios en Contrato
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome41" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagAdmiContra">ADMINISTRACIÓN DE CONTRATOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagUsrCont">USUARIOS EN CONTRATO</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_usuariosContrato" class="banner__title banner__title--modifier">
                Usuarios en Contrato
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome41" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagAdmiContra">ADMINISTRACIÓN DE CONTRATOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagUsrCont">USUARIOS EN CONTRATO</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaCUC" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                </div>
            </div>
            <div class="section__content">
                <div id="filtroUsuariosClientes" class="form-filter">
                    <span id="title_filtroUsuariosClientes" class="form-filter__title">Filtro de consulta</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagCUC_UsuFiltro" class="form-filter__label" for="CUC_usuarioFiltro">Usuario:</label>
                            <input
                                    id="CUC_usuarioFiltro"
                                    title="Usuario"
                                    type="text"
                                    maxlength="16"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="CUC_CIRIF">C.I./R.I.F:</label>
                            <input
                                    id="CUC_CIRIF"
                                    title="CI/RIF"
                                    type="text"
                                    maxlength="15"
                                    class="form-filter__input input input--form inputFormulario"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCUC_GrupoFiltro" class="form-filter__label" for="CUC_grupoFiltro">Grupo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="CUC_grupoFiltro"
                                        title="Grupo"
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
                            <label id="tagCUC_CarteraFiltro" class="form-filter__label" for="CUC_carteraFiltro">Cartera Nº</label>
                            <input
                                    id="CUC_carteraFiltro"
                                    title="Cartera"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="16"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCUC_NomFiltro" class="form-filter__label" for="CUC_nombreFiltro">Nombre:</label>
                            <input
                                    id="CUC_nombreFiltro"
                                    title="Nombre"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="50"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCUC_AmbitoFiltro" class="form-filter__label" for="CUC_ambitoFiltro">Ambito:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="CUC_ambitoFiltro"
                                        title="Ambito"
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
                            <label id="tagEstatusFiltro" class="form-filter__label" for="CUC_estatusFiltro">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="CUC_estatusFiltro"
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
                                id="CUC_reset"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Limpiar"
                        />
                        <input
                                id="CUC_search"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <span id="table_title_usr_contract" class="table__title">Usuarios en Contratos</span>
                    <div id="div_tabla_consultarUsuariosContratos">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_editarUsuarioCUC" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_editarUsuarioContrato" class="section__title">Usuarios en Contrato / Editar Usuario</span>
                    <input type="button" id="btnBackEU_CUCbtnBackEU_CUC" value="Volver" class="section__button button button--white">
                </div>
            </div>
            <input type="hidden" value="" id="pantallaMostrar">
            <div class="section__content">
                <div id="formularioEditarUsuarioCUC" class="form-filter">
                    <span class="form-filter__title">Filtro de consulta</span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagUsuarioEU_CUC" class="form-filter__label">Usuario:</label>
                            <span id="usuarioEU_CUC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagNombreEU_CUC" class="form-filter__label">Nombre:</label>
                            <span id="nombreEU_CUC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagEmailEU_CUC" class="form-filter__label field-obligatory" for="emailEU_CUC">Email:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="emailEU_CUC"
                                        title="Email"
                                        class="select-section__select select-section__select--form obligatorio_EUCBO cambioStatus"
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
                            <label id="tagTelefonoEU_CUC" class="form-filter__label" for="telefonoEU_CUC">Teléfono:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="telefonoEU_CUC"
                                        title="Telefono"
                                        class="select-section__select select-section__select--form cambioStatus"
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
                            <label id="tagDireccionEU_CUC" class="form-filter__label">Dirección:</label>
                            <span id="direccionEU_CUC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagAmbitoEU_CUC" class="form-filter__label field-obligatory" for="ambitoEU_CUC">Ambito:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="ambitoEU_CUC"
                                        title="Ambito"
                                        class="select-section__select select-section__select--form obligatorio_EUCBO cambioStatus"
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
                            <label id="tagEstatusEU_CUC" class="form-filter__label field-obligatory" for="estatusEU_CUC">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="estatusEU_CUC"
                                        title="Ambito"
                                        class="select-section__select select-section__select--form obligatorio_EUCBO"
                                        onchange="cambioEstatusEUC();"
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
                            <label id="tagCiRifEU_CUC" class="form-filter__label">C.I./R.I.F:</label>
                            <span id="ciRifEU_CUC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagGrupoEU_CUC" class="form-filter__label field-obligatory" for="grupoEU_CUC">Grupo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="grupoEU_CUC"
                                        title="Grupo"
                                        class="select-section__select select-section__select--form obligatorio_EUCBO cambioStatus"
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
                            <label id="tagTelefonoCelEU_CUC" class="form-filter__label field-obligatory" for="estatusEU_CUC">Teléfono Célular:</label>
                            <div class="form-filter__choose">
                                <input
                                        id="codigoPaisUC"
                                        title="Country Code"
                                        type="tel"
                                        disabled="true"
                                        class="form-filter__input input input--form codigoPais obligatorio_EUFC mascaraCodPais right"
                                        maxlength="4"
                                        size="4"
                                />
                                <div class="form-filter__select select-section select-section--form">
                                    <select
                                            id="telefonoCelEU_CUC"
                                            title="Telefono Celular"
                                            class="select-section__select select-section__select--form obligatorio_EUCBO cambioStatus"
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

                    </div>
                    <div id="formulario_EU_CUC" class="form-filter__buttons">
                        <input type="button" id="btnDeshacerEU_CUC" value="Deshacer" class="button button--white">
                        <input type="button" id="btnResetearPasswordEU_CUC" value="Reiniciar Clave SMS" class="button button--white ocultarStatus">
<%--                        <input type="button" id="btnResetearPasswordEU_CUC_Correo" value="Reiniciar Clave Correo" class="button button--white ocultarStatus">--%>
                        <input type="button" id="btnAccesosEspecialesEU_CUC" value="Accesos Especiales" class="button">
                        <input type="button" id="btnSalvarEU_CUC" value="Guardar" class="button">
                        <input type="button" id="btnEnviarUsuarioEU_CUC" value="Enviar Usuario SMS" class="button ocultarStatus">
<%--                        <input type="button" id="btnEnviarUsuarioEU_CUC_Correo" value="Enviar Usuario Correo" class="button ocultarStatus">--%>
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
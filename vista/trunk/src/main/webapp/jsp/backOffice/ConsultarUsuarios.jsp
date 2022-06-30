s<%@ taglib prefix="s" uri="/struts-tags" %>
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
<div id="div_tabla_consultaCU" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarUsuarios">Usuarios</h1>
    </div>
<fieldset class="div_consulta 0000000003_1">
    &lt;%&ndash;<legend>Usuarios</legend>&ndash;%&gt;
    &lt;%&ndash;<label class="datos2"> <a href="../../jsp/backOffice/AgregarUsuario.jsp"id="" >Agregar Usuario </a> </label>&ndash;%&gt;
    <input type="button" id="agregarUsuarioCU" value="Agregar Usuario" class="botonBackoffice">

</fieldset>

<fieldset class="div_consulta">
    <legend>Filtro de consulta</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="datos2" id="tagCU_UsuFiltro">Usuario:</td>
            <td class="datos" >
                <input type="TEXT" id="OJO_CU_usuarioFiltro" title="Usuario" style="width: 200px;"  maxlength="15" size="17" class="inputFormulario" >
            </td>

            <td class="datos2" id="tagCU_NomFiltro" >Nombre:</td>
            <td class="datos">
                <input type="TEXT" id="CU_nombreFiltro" title="Nombre" style="width: 200px;"  maxlength="55" size="35" class="inputFormulario">
            </td>
        </tr>
        <tr>
            <td class="datos2">C.I./R.I.F:</td>
            <td class="datos">
                <input type="text" title="CI/RIF" id="CU_CIRIF" style="width:200px;" tabindex="2" maxlength="20" size="12" class="inputFormulario">
            </td>
            <td class="datos2" id="tagCU_AmbitoFiltro">
                Ámbito:
            </td>
            <td class="datos">
                <select  id="CU_ambitoFiltro" class="selectFormulario" title="Ambito" style="width:200px;"  >

                </select>
            </td>
        </tr>
        <tr>

            <td class="datos2" id="tagCU_GrupoFiltro">
                    Grupo:
            </td>
            <td class="datos">
                <select  id="CU_grupoFiltro" class="selectFormulario" title="Grupo" style="width:200px;"  >

                </select>
            </td>
            <td class="datos2" id="OJO_2tagEstatusFiltro">
                Estatus:
            </td>
            <td class="datos">
                <select  id="CU_estatusFiltro" title="Estatus" style="width:200px;" class="selectFormulario"  >

                </select>
            </td>
        </tr>

        <tr>
            <td colspan="4" class="botones_formulario">
                <input type="button" id="CU_search" value="Buscar" class="boton">
                <input type="button" id="CU_reset" value="Deshacer" class="boton">
            </td>
        </tr>
        </table>

</fieldset>
<fieldset class="div_consulta">
    <legend > Consultar Usuarios </legend>
    <div id="div_tabla_consultarUsuarios" class="div_tabla_consultarUsuarios">

    </div>
    <div id="paginacion_tabla_consultarUsuarios" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_tabla_agregarCU" style="display: none">
    <div>
        <h1 id="Titulo_agregarUsuarios_backoffice">Usuario / Agregar Usuario</h1>
    </div>
    <fieldset id="AU_botones" class="formulario_fieldset div_consulta">
        <input type="button" id="CU_AgregarUsuario_AU" value="Agregar" class="boton" >
        <input type="button" id="CU_Resetear_AU" value="Limpiar" class="boton" >
        <input type="button" id="CU_Cancelar_AU" value="<< Volver" class="botonDerecha" >
    </fieldset>
    <fieldset id="formularioAgregarUsuario" class="formulario_fieldset div_consulta">

        <legend id="BO_USUARIO_TAG_DATOS_USUARIOS">Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0"  border="0" width="80%" align="center">
            <tr>
                <td >
                    <label for="AU_usuario" id="tagUsuario"  class="datos">Usuario:</label>
                </td>
                <td >
                    <input id="AU_usuario" type="text" class="obligatorio_AUBO inputFormulario only_login" title="Usuario" style="width:195px" maxlength="15" size="17"/> <label class="datos">  * </label>
                </td>

            </tr>
            <tr>
                <td >
                    <label for="AU_nombre" id="tagNombre"  class="datos">Nombre:</label>
                </td>
                <td >
                    <input id="AU_nombre" type="text" class="obligatorio_AUBO  inputFormulario" title="Nombre" style="width:195px" maxlength="55" size="35"/> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="AU_aciRif"  id="tagACiRif" class="datos">C.I./R.I.F:</label>
                </td>
                <td >
                    <select id="AU_tipoPersona" class="selectFormulario obligatorio_AUBO" title="Tipo Persona">

                    </select>
                    <input id="AU_aciRif" type="text" class="obligatorio_AUBO inputFormulario" title="C.I./R.I.F" maxlength="20" size="12" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/><label class="datos">  * </label>
                </td>

            </tr>

            <tr>
                <td >
                    <label for="AU_telefono" id="tagAU_Telefono"  class="datos">Tel&eacute;fono:</label>
                </td>
                <td >
                    <input id="AU_telefono" type="text" class="obligatorio_AUBO  inputFormulario" title="Tel&eacute;fono" style="width:195px" maxlength="20" size="20"/> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="AU_telefonoCel" id="tagAU_TelefonoCel"  class="datos">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td >
                    <input id="AU_telefonoCel" type="text" class="inputFormulario obligatorio_AUBO" title="Tel&eacute;fono Celular" style="width:195px" maxlength="20" size="20"/>   <label class="datos">  * </label>
                </td>
            </tr>
            <tr>
                <td >
                    <label for="AU_direccion" id="tagDireccion"  class="datos">Direcci&oacute;n:</label>
                </td>
                <td>
                    <input id="AU_direccion" type="text" class="obligatorio_AUBO inputFormulario" style="width:300px" maxlength="120" size="86" title="Dirección"/>   <label class="datos">  * </label>
                </td>
                <td >
                    <label for="AU_email" id="tagEmail" class="datos">Email:</label>
                </td>
                <td >
                    <input id="AU_email" type="text" class="obligatorio_AUBO  inputFormulario" title="Email" style="width:195px" maxlength="55" size="20"/> <label class="datos">  * </label>
                </td>

            </tr>
            <tr>
                <td>
                    <label for="AU_grupo"  id="tagGrupo" class="datos">Grupo:</label>
                </td>
                <td>
                    <select id="AU_grupo" class="selectFormulario obligatorio_AUBO" title="Grupo" style="width:195px">

                    </select>                                        <label class="datos">  * </label>
                </td>
                <td >
                    <label for="AU_ambito" id="tagAU_ambito"  class="datos">&Aacute;mbito:</label>
                </td>
                <td >
                    <select id="AU_ambito" class="selectFormulario obligatorio_AUBO" title="&Aacute;mbito" style="width:195px">

                    </select>  <label class="datos">  * </label>
                </td>
            </tr>
        </table>
    </fieldset>




</div>
<div id="div_tabla_editarUsuario" style="display: none">
    <div>
        <h1 id="Titulo_editarUsuario">Usuario / Editar Usuario</h1>
    </div>
    <fieldset id="formulario2" class="formulario_fieldset div_consulta">
        <input align="left" type="button" id="btnSalvarEU" value="Guardar" class="boton 0000000003_2">
        <input align="left" type="button" id="btnDeshacerEU" value="Deshacer" class="boton 0000000003_2">
        <input align="left" type="button" id="btnAccesosEspecialesEU" value="Accesos Especiales" class="0000000031_2 botonGrande">
        <input align="left" type="button" id="btnResetearPasswordEU" value="Resetear Clave" class="0000000003_5 botonGrande">
        <input align="right" type="button" id="btnBackEU" value="Volver" class="botonDerecha">
    </fieldset>

    <fieldset id="formularioEditarUsuario" class="formulario_fieldset div_consulta">

        <legend>Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td width="25%">
                    <label for="usuarioEU" id="tagUsuarioEU"  class="datos2">Usuario:</label>
                </td>
                <td width="25%">
                    <b><label class="datos" id="usuarioEU" > </label></b>
                </td>
                <td width="25%">
                    <label for="estatusEU" id="tagEstatusEU"  class="datos2">Estatus:</label>
                </td>
                <td width="25%">
                    <select id="estatusEU" onchange="cambioEstatus();" class="obligatorio_EUBO" style="width: 220px;" title='Estatus' >

                    </select>
                    <label class="datos">*</label>
                </td>

            </tr>
            <tr>
                <td >
                    <label for="nombreEU" id="tagNombreEU"  class="datos2">Nombre:</label>
                </td>
                <td >
                    <input id="nombreEU" type="text" class="obligatorio_EUBO ediitarUsuarioBack"  title="Nombre" size="35" maxlength="55" style="width:220px;"/> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="ciRifEU"  id="tagCiRifEU" class="datos2">C.I./R.I.F:</label>
                </td>
                <td >
                    <select id="tipoPersonaEU" class="obligatorio_EUBO ediitarUsuarioBack" style="width:90px;" title="Tipo Persona">

                    </select>
                    <input id="ciRifEU" type="text" class="obligatorio_EUBO ediitarUsuarioBack" style="width:125px;" title="C.I./R.I.F" maxlength="20" size="12"/><label class="datos">  * </label>
                </td>

            </tr>

            <tr>
                <td >
                    <label for="telefonoEU" id="tagTelefonoEU"  class="datos2">Tel&eacute;fono:</label>
                </td>
                <td >
                    <input id="telefonoEU" type="text" class="obligatorio_EUBO ediitarUsuarioBack" style="width:220px;" maxlength="20" size="20" title="Telefono" /> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="telefonoCelEU" id="tagTelefonoCelEU"  class="datos2">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td >
                    <input id="telefonoCelEU" type="text"  class="obligatorio_EUBO ediitarUsuarioBack" style="width:220px;" maxlength="20" size="20" title='Telefono Celular'/> <label class="datos">  * </label>
                </td>
            </tr>
            <tr>
                <td >
                    <label for="direccionEU" id="tagDireccionEU"  class="datos2">Direcci&oacute;n:</label>
                </td>
                <td >
                    <input id="direccionEU" type="text" class="obligatorio_EUBO ediitarUsuarioBack" maxlength="120" size="86" style="width:220px;" title="Direccion"/>
                    <label class="datos">*</label>
                </td>
                <td >
                    <label for="emailEU" id="tagEmailEU" class="datos2">Email:</label>
                </td>
                <td >
                    <input id="emailEU" type="text" class="obligatorio_EUBO ediitarUsuarioBack" size="35" maxlength="55" style="width:220px;" title='Email'/> <label class="datos">  * </label>
                </td>

            </tr>
            <tr>

                <td>
                    <label for="grupoEU"  id="tagGrupoEU" class="datos2">Grupo:</label>
                </td>
                <td>
                    <select id="grupoEU" class="obligatorio_EUBO ediitarUsuarioBack" style="width:220px;" title="Grupo">

                    </select>
                    <label class="datos" >*</label>
                </td>
                <td >
                    <label for="ambitoEU" id="tagAmbitoEU"  class="datos2">Ambito:</label>
                </td>
                <td >
                    <select id="ambitoEU" class="selectFormulario obligatorio_EUBO ediitarUsuarioBack" title="&Aacute;mbito" style="width:195px">

                    </select>  <label class="datos">  * </label>
                </td>
            </tr>

        </table>
    </fieldset>
</div>


<div  id="div_tabla_consultaOpcionesSistema">
   <div id="usuarioOpcionesSistema">
    <h3 id="see_id_cu"> Opciones Sistema </h3>

    <fieldset class="div_consulta">
        &lt;%&ndash;<legend id="Legend_OpcionesSistema">  </legend>&ndash;%&gt;
        <label>Para Editar haga click sobre la opcion </label>
        <div id="div_tabla_UsuarioOpcionesSistema" class="div_tabla_UsuarioOpcionesSistema">
        </div>

    </fieldset>
   </div>
    <div  id="div_tabla_consultaUsuarioOpcionesPermiso" style="display: none">
        <h3 id="see_id_cup"> Opciones Permisos </h3>
        <fieldset class="div_consulta">
            <input type="button" id="CUOP_back_CU" value="Volver" class="boton" >
            <input type="button" id="CUOP_save_CU" value="Guardar" class="boton" >
            <input type="button" id="CUOP_eliminar_CU" value="Eliminar Permisologias" class="botonGrande" >
            <input type="hidden" id="codOpc" value="" >

        </fieldset>
        <fieldset class="div_consulta">
            <legend id="Legend_UsuarioOpcionesSistemaPermisos"></legend>
            <label>Para Editar haga click sobre la opcion </label>
            <div id="div_tabla_usuarioOpcionesPermisos" class="div_tabla_usuarioOpcionesPermisos">
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
</div>
--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuarios"  class="banner__title banner__title--modifier">
                Usuarios
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome32" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="BO_USUARIO_TAG_USUARIOS_2">USUARIOS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuarios"  class="banner__title banner__title--modifier">
                Usuarios
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome32" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="BO_USUARIO_TAG_USUARIOS_2">USUARIOS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaCU" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                    <input id="agregarUsuarioCU" type="button" class="section__button button" value="Agregar Usuario">
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span id="tagFiltroConsulta_FC" class="form-filter__title"></span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagCU_UsuFiltro" class="form-filter__label" for="OJO_CU_usuarioFiltro">Usuario:</label>
                            <input
                                    id="OJO_CU_usuarioFiltro"
                                    title="Usuario"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="15"
                                    size="17"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="CU_CIRIF">C.I./R.I.F:</label>
                            <input
                                    id="CU_CIRIF"
                                    title="C.I./R.I.F"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="20"
                                    size="12"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCU_GrupoFiltro" class="form-filter__label" for="CU_grupoFiltro">Grupo:</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="CU_grupoFiltro"
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
                            <label id="tagCU_NomFiltro" class="form-filter__label" for="CU_nombreFiltro">Nombre:</label>
                            <input
                                    id="CU_nombreFiltro"
                                    title="Nombre"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCU_AmbitoFiltro" class="form-filter__label" for="CU_ambitoFiltro">Ámbito:</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="CU_ambitoFiltro"
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
                            <label id="OJO_2tagEstatusFiltro" class="form-filter__label" for="CU_estatusFiltro">Estatus::</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="CU_estatusFiltro"
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
                                id="CU_reset"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Deshacer"
                        />
                        <input
                                id="CU_search"
                                type="button"
                                class="form-filter__button button"
                                value="Buscar"
                        />
                    </div>
                </div>
                <div class="table">
                    <div class="table__titles">
                        <span id="BO_USUARIO_TAG_CONSULTAR_USUARIOS" class="table__title">Consultar Usuarios</span>
                    </div>
                    <div id="div_tabla_consultarUsuarios" class="div_tabla_consultarUsuarios">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_agregarCU" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_agregarUsuarios_backoffice" class="section__title">Usuario / Agregar Usuario</span>
                    <input type="button" id="CU_Cancelar_AU" value="<< Volver" class="section__button button button--white">
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span class="form-filter__title">Datos del Usuario</span>
                    <div id="formularioAgregarUsuario" class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagUsuario" class="form-filter__label field-obligatory" for="AU_usuario">Usuario:</label>
                            <input
                                    id="AU_usuario"
                                    title="Usuario"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUBO inputFormulario only_login"
                                    maxlength="15"
                                    size="17"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagNombre" class="form-filter__label field-obligatory" for="AU_nombre">Nombre:</label>
                            <input
                                    id="AU_nombre"
                                    title="Nombre"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUBO  inputFormulario"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagAU_Telefono" class="form-filter__label field-obligatory" for="AU_telefono">Teléfono:</label>
                            <input
                                    id="AU_telefono"
                                    title="Teléfono"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUBO inputFormulario"
                                    maxlength="20"
                                    size="20"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagDireccion" class="form-filter__label field-obligatory" for="AU_direccion">Dirección:</label>
                            <input
                                    id="AU_direccion"
                                    title="Dirección"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUBO inputFormulario"
                                    maxlength="120"
                                    size="86"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagGrupo" class="form-filter__label field-obligatory" for="AU_grupo">Grupo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="AU_grupo"
                                        title="Grupo"
                                        class="select-section__select select-section__select--form selectFormulario obligatorio_AUBO"
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
                            <label id="tagACiRif" class="form-filter__label field-obligatory" for="AU_aciRif">C.I./R.I.F:</label>
                            <div class="form-filter__choose">
                                <div class="form-filter__select select-section select-section--form">
                                    <select
                                            id="AU_tipoPersona"
                                            title="Tipo Persona"
                                            class="select-section__select select-section__select--form selectFormulario obligatorio_AUBO"
                                    >
                                    </select>
                                    <img
                                            class="select-section__icon select-section__icon--form"
                                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                            alt=""
                                    />
                                </div>
                                <input
                                        id="AU_aciRif"
                                        title="C.I./R.I.F"
                                        type="text"
                                        class="form-filter__input input input--form obligatorio_AUBO inputFormulario"
                                        maxlength="20"
                                        size="12"
                                        onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagAU_TelefonoCel" class="form-filter__label field-obligatory" for="AU_telefonoCel">Teléfono Célular:</label>
                            <input
                                    id="AU_telefonoCel"
                                    title="Teléfono Celular"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario obligatorio_AUBO"
                                    maxlength="20"
                                    size="20"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagEmail" class="form-filter__label field-obligatory" for="AU_email">Email:</label>
                            <input
                                    id="AU_email"
                                    title="Email"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUBO  inputFormulario"
                                    maxlength="55"
                                    size="20"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagAU_ambito" class="form-filter__label field-obligatory" for="AU_ambito">Ámbito:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="AU_ambito"
                                        title="Ámbito"
                                        class="select-section__select select-section__select--form selectFormulario obligatorio_AUBO"
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
                        <input type="button" id="CU_Resetear_AU" value="Limpiar" class="button button--white">
                        <input type="button" id="CU_AgregarUsuario_AU" value="Agregar" class="button">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_editarUsuario" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_editarUsuario" class="section__title">Usuario / Editar Usuario</span>
                    <input type="button" id="btnBackEU" value="Volver" class="section__button button button--white">
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span class="form-filter__title">Datos del Usuario</span>
                    <div id="formularioEditarUsuario" class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagUsuarioEU" class="form-filter__label" for="usuarioEU">Usuario:</label>
                            <span id="usuarioEU" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagNombreEU" class="form-filter__label field-obligatory" for="nombreEU">Nombre:</label>
                            <input
                                    id="nombreEU"
                                    title="Nombre"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUBO ediitarUsuarioBack"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagTelefonoEU" class="form-filter__label field-obligatory" for="telefonoEU">Teléfono:</label>
                            <input
                                    id="telefonoEU"
                                    title="Telefono"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUBO ediitarUsuarioBack"
                                    maxlength="20"
                                    size="20"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagDireccionEU" class="form-filter__label field-obligatory" for="direccionEU">Dirección:</label>
                            <input
                                    id="direccionEU"
                                    title="Direccion"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUBO ediitarUsuarioBack"
                                    maxlength="120"
                                    size="86"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagGrupoEU" class="form-filter__label field-obligatory" for="grupoEU">Grupo:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="grupoEU"
                                        title="Grupo"
                                        class="select-section__select select-section__select--form obligatorio_EUBO ediitarUsuarioBack"
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
                            <label id="tagEstatusEU" class="form-filter__label field-obligatory" for="estatusEU">Estatus:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="estatusEU"
                                        title="Estatus"
                                        class="select-section__select select-section__select--form obligatorio_EUBO"
                                        onchange="cambioEstatus();"
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
                            <label id="tagCiRifEU" class="form-filter__label field-obligatory" for="ciRifEU">C.I./R.I.F:</label>
                            <div class="form-filter__choose">
                                <div class="form-filter__select select-section select-section--form">
                                    <select
                                            id="tipoPersonaEU"
                                            title="Tipo Persona"
                                            class="select-section__select select-section__select--form obligatorio_EUBO ediitarUsuarioBack"
                                    >
                                    </select>
                                    <img
                                            class="select-section__icon select-section__icon--form"
                                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                            alt=""
                                    />
                                </div>
                                <input
                                        id="ciRifEU"
                                        title="C.I./R.I.F"
                                        type="text"
                                        class="form-filter__input input input--form obligatorio_EUBO ediitarUsuarioBack"
                                        maxlength="20"
                                        size="12"
                                        onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="tagTelefonoCelEU" class="form-filter__label field-obligatory" for="telefonoCelEU">Teléfono Célular:</label>
                            <input
                                    id="telefonoCelEU"
                                    title="Telefono Celular"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUBO ediitarUsuarioBack"
                                    maxlength="20"
                                    size="20"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagEmailEU" class="form-filter__label field-obligatory" for="emailEU">Email:</label>
                            <input
                                    id="emailEU"
                                    title="Email"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUBO ediitarUsuarioBack"
                                    maxlength="55"
                                    size="20"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagAmbitoEU" class="form-filter__label field-obligatory" for="ambitoEU">Ambito:</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="ambitoEU"
                                        title="Ámbito"
                                        class="select-section__select select-section__select--form selectFormulario obligatorio_EUBO ediitarUsuarioBack"
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
                        <input type="button" id="btnResetearPasswordEU" value="Resetear Clave" class="button button--white">
                        <input type="button" id="btnAccesosEspecialesEU" value="Accesos Especiales" class="button button--white">
                        <input type="button" id="btnDeshacerEU" value="Deshacer" class="button button--white">
                        <input type="button" id="btnSalvarEU" value="Guardar" class="button">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-modal container" id="div_tabla_consultaOpcionesSistema">
        <div class="form-modal__top">
            <img class="form-modal__icon" src="../vbtonline/resources/img/icons/ic_templates_saved_header_search.png" alt="" />
            <span class="form-modal__title">Opciones Sistema</span>
        </div>
        <div class="form-modal__content">
            <div class="form-transfer">
                <div id="div_tabla_UsuarioOpcionesSistema" class="div_tabla_UsuarioOpcionesSistema"></div>
            </div>
        </div>
    </div>
</main>






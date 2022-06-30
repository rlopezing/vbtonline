<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div id="div_tabla_consultaCU_FC" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarUsuariosFC">Administrador / Usuarios Firmas Conjuntas</h1>
    </div>
    <fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left">
                    <span class="datosInfo" id="TAG_INFO_USERS_FC">This option allows you to create, modify, block and inactivate users.<br>You can create 5 types of users:<br><li>Charger: user responsible for recording internal transfers and to other banks, as well as consulted them.</li><li>Approver: user responsible for approving transfers that have been previously registered by the user charger. Also the approver can view balances and transactions of the different products, issue statements and time deposit certificates.</li><li>Releaser: user responsible for releasing the transfers placed in approved status.</li><li>Consultation / Auditor: these users can view balances and transactions of different products, issue statements and time deposit certificates.</li></span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>


    <fieldset class="div_consulta">

        <input type="button" id="agregarUsuarioCU_FC" value="Agregar Usuario" class="botonBackoffice 0000000052_1">

    </fieldset>

    <fieldset class="div_consulta">
        <legend id="tagFiltroConsulta_FC">Filtro de consulta</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td class="datos2" ><span id="tagCU_UsuFiltro_FC">Usuario:</span></td>
                <td class="datos">
                    <input type="TEXT" id="CU_usuarioFiltro_FC" title="usuarioFiltro" style="width: 200px;"
                           maxlength="15" size="17" class="inputFormulario_FC">
                </td>

                <td class="datos2" ><span id="tagCU_NomFiltro_FC">Nombre:</span></td>
                <td class="datos">
                    <input type="TEXT" id="CU_nombreFiltro_FC" title="CU_nombreFiltro_FC" style="width: 200px;" maxlength="55"
                           size="35" class="inputFormulario_FC">
                </td>
            </tr>
            <tr>
                <td class="datos2"><span id="tagCU_CIRIF_FC">C.I./R.I.F:</span></td>
                <td class="datos">
                    <input type="text" title="CIRIF_FC" id="CU_CIRIF_FC" style="width:200px;" tabindex="2" maxlength="20"
                           size="12" class="inputFormulario_FC">
                </td>
            </tr>
            <tr>

                <td class="datos2">
                    <span id="tagCU_GrupoFiltro_FC">Grupo:</span>
                </td>
                <td class="datos">
                    <select id="CU_grupoFiltro_FC" class="selectFormulario_FC" title="grupoFiltro_FC" style="width:200px;">

                    </select>
                </td>
                <td class="datos2" >
                    <span id="tagEstatusFiltro_FC">Estatus:</span>
                </td>
                <td class="datos">
                    <select id="CU_estatusFiltro_FC" title="estatusFiltro_FC" style="width:200px;" class="selectFormulario_FC">

                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="4" class="botones_formulario">
                    <input type="button" id="CU_search_FC" value="Buscar" class="boton">
                    <input type="button" id="CU_reset_FC" value="Limpiar" class="boton">
                </td>
            </tr>
        </table>
    </fieldset>



    <fieldset class="div_consulta">
        <legend id="tagConsultarUsuarios_FC">Check Signatures Joint Users</legend>
        <div id="div_tabla_consultarUsuarios_FC" class="div_tabla_consultarUsuarios_FC">

        </div>
    </fieldset>
</div>
<div id="div_tabla_agregarUsuario_FC" style="display: none">
    <div>
        <h1 id="Titulo_agregarUsuarios">Usuario Firma Conjunta/ Agregar Usuario</h1>
    </div>
    <fieldset class="invisible_print div_info" style="width:90%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left">
                    <span id="TAG_INFO_USERS_FC_ADD" class="datosInfo">This option allows you to create users. You can create 5 types of users:<br><br><li><u>Charger</u>: user responsible for recording internal transfers and to other banks, as well as consulted them.</li><li><u>Approver</u>: user responsible for approving transfers that have been previously registered. Also the approver can view balances and transactions of the different products, issue statements and time deposit certificates.</li><li><u>Releaser</u>: user responsible for releasing the transfers placed in approved status.</li><li><u>View-only Access / Auditor</u>: these users can view balances and transactions of different products, issue statements and time deposit certificates.</li><br></span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset id="AU_botones_FC" class="formulario_fieldset div_consulta">
        <input type="button" id="AgregarUsuario_AU_FC" value="Agregar" class="boton">
        <input type="button" id="Resetear_AU_FC" value="Limpiar" class="boton">
        <input type="button" id="Cancelar_AU_FC" value="<< Volver" class="botonDerecha">
    </fieldset>
    <fieldset id="formularioAgregarUsuario_FC" class="formulario_fieldset div_consulta">

        <legend id="tagdatosUsuario">Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0"  border="0" width="80%" align="center">
            <tr>
                <td>
                    <label for="AU_usuario_FC" id="FirmasConjuntas_tagUsuario" class="datos">Usuario:</label>
                </td>
                <td>
                    <input id="AU_usuario_FC" type="text" class="obligatorio_AUFC inputFormulario only_login_FC" title="Usuario"
                           style="width:195px" maxlength="15" size="17"/> <label class="datos"> * </label>
                </td>
                <td>
                    <label for="AU_aciRif_FC" id="FirmasConjuntas_tagACiRif_FC" class="datos">C.I./R.I.F:</label>
                </td>
                <td>
                    <select id="AU_tipoPersona_FC" class="selectFormulario obligatorio_AUFC" title="Tipo Persona">

                    </select>
                    <input id="AU_aciRif_FC" type="text" class="obligatorio_AUFC inputFormulario" title="C.I./R.I.F"
                           maxlength="20" size="12"
                           onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/><label
                        class="datos"> * </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="AU_nombre_FC" id="FirmasConjuntas_tagNombre" class="datos">Nombre:</label>
                </td>
                <td>
                    <input id="AU_nombre_FC" type="text" class="obligatorio_AUFC  inputFormulario" title="Nombre"
                           style="width:195px" maxlength="55" size="35"/> <label class="datos"> * </label>
                </td>
                <td>
                    <label for="AU_telefonoCel_FC" id="FirmasConjuntas_TelefonoCel" class="datos">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td>
                    <input id="AU_telefonoCodCel_FC" type="tel" style="width:90px;text-align:center;" maxlength="4" size="4" class='codigoPais obligatorio_AUFC mascaraCodPais'/> <input id="AU_telefonoCel_FC" type="text" class="obligatorio_AUFC inputFormulario mascaraCelular" title="Tel&eacute;fono Celular"
                                                                                                                                                                                         style="width:150px" maxlength="20" size="20"/>  <label class="datos">
                    * </label>
                </td>

            </tr>

            <tr>
                <td>
                    <label for="AU_telefono_FC" id="FirmasConjuntas_Telefono" class="datos">Tel&eacute;fono:</label>
                </td>
                <td>
                    <input id="AU_telefono_FC" type="text" class="obligatorio_AUFC  inputFormulario"  onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                           title="Tel&eacute;fono" style="width:195px" maxlength="20" size="20"/> <label class="datos">
                    * </label>
                </td>
                <td>
                    <label for="AU_email_FC" id="FirmasConjuntas_tagEmail" class="datos">Email:</label>
                </td>
                <td>
                    <input id="AU_email_FC" type="text" class="obligatorio_AUFC  inputFormulario" title="Email"
                           style="width:242px" maxlength="55" size="20"/> <label class="datos"> * </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="AU_direccion_FC" id="FirmasConjuntas_tagDireccion"
                           class="datos">Direcci&oacute;n:</label>
                </td>
                <td >
                    <input id="AU_direccion_FC" type="text" class="obligatorio_AUFC  inputFormulario" style="width:300px" maxlength="120"
                           size="86"/>  <label class="datos"> * </label>
                </td>
                <td>

                </td>
                <td>

                </td>

            </tr>
            <tr>
                <td>
                    <label for="AU_grupo_FC" id="FirmasConjuntas_tagGrupo" class="datos">Grupo:</label>
                </td>
                <td>
                    <select id="AU_grupo_FC" title="Grupo" class="selectFormulario obligatorio_AUFC" style="width:195px" onchange="mostrarRolesAdd();">

                    </select>
                </td>
                <td>

                </td>
                <td>

                </td>

            </tr>
        </table>

    </fieldset>
    <fieldset id="asignacionRolesFCAdd" class="formulario_fieldset div_consulta oculto">

        <legend id= "asigancionRolesMain">Assigning Roles</legend>

        <table>
            <tr>
                <td width="40%">
                    <div id="rolesDinamicosAdd" style="padding-left:12px;">
                    </div>
                </td>
                <td>
                    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
                        <tr>
                            <td width="100%" rowspan="4" valign="middle" w>
                                <div id="rolesDinamicosAddLista" class="roles_descrip">

                                </div>

                            </td>
                       </tr>
                    </table>
                </td>
            </tr>
        </table>
    </fieldset>
</div>

<div id="div_tabla_editarUsuario_FC" style="display: none">
    <div>
        <h1 id="Titulo_editarUsuario_FC">Usuario Firma Conjunta / Editar Usuario</h1>
    </div>
    <fieldset class="invisible_print div_info" style="width:90%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left" >
                    <span id="TAG_INFO_USERS_FC_EDIT" class="datosInfo">This option allows you to modify, block and inactivate users. Types of users:<br><br><li><u>Charger</u>: user responsible for recording internal transfers and to other banks, as well as consulted them.</li><li><u>Approver</u>: user responsible for approving transfers that have been previously registered. Also the approver can view balances and transactions of the different products, issue statements and time deposit certificates.</li><li><u>Releaser</u>: user responsible for releasing the transfers placed in approved status.</li><li><u>View-only Access / Auditor</u>: these users can view balances and transactions of different products, issue statements and time deposit certificates.</li></span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset id="formulario2" class="formulario_fieldset div_consulta">
        <input align="left" type="button" id="btnSalvarEU_FC" value="Salvar" class="boton 0000000052_2">
        <input align="left" type="button" id="btnDeshacerEU_FC" value="Resetear" class="boton">
        <input align="left" type="button" id="btnResetearPasswordEU_FC" value="Reset Password" class="botonGrande 0000000052_5">
        <input align="left" type="button" id="btnSendUserEU_FC" value="Send User" class="botonGrande 0000000052_5">
        <input align="right" type="button" id="btnBackEU_FC" value="<< Volver" class="botonDerecha">
    </fieldset>

    <fieldset id="formularioEditarUsuario" class="formulario_fieldset div_consulta">

        <legend id="tagDatosUsuarioEU_FC">Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="80%" border="0" align="center">
            <tr>
                <td>
                    <label for="usuarioEU_FC" id="FirmasConjuntas_tagUsuarioEU" class="datos">Usuario:</label>
                </td>
                <td >
                    <b><label class="datos" id="usuarioEU_FC"> </label></b>
                </td>
                <td>
                    <label for="estatusEU_FC" id="FirmasConjuntas_tagEstatusEU" class="datos">Estatus:</label>
                </td>
                <td>
                    <select id="estatusEU_FC" onchange="cambioEstatusFC();" class="obligatorio_EUFC"
                            style="width: 220px;">

                    </select>
                    <label class="datos">*</label>
                </td>

            </tr>
            <tr>
                <td>
                    <label for="nombreEU_FC" id="FirmasConjuntas_tagNombreEU" class="datos">Nombre:</label>
                </td>
                <td>
                    <input id="nombreEU_FC" type="text" class="obligatorio_EUFC" size="35" maxlength="55"
                           style="width:220px;"/>
                            <label class="datos"> * </label>
                </td>
                <td>
                    <label for="ciRifEU_FC" id="FirmasConjuntas_tagCiRifEU" class="datos">C.I./R.I.F:</label>
                </td>
                <td>
                    <select id="tipoPersonaEU_FC" class="obligatorio_EUFC" style="width:90px;">

                    </select>
                    <input id="ciRifEU_FC" type="text" class="obligatorio_EUFC" style="width:125px;" maxlength="20"
                           size="12"/><label class="datos"> * </label>
                </td>

            </tr>

            <tr>
                <td>
                    <label for="telefonoEU_FC" id="FirmasConjuntas_tagTelefonoEU"
                           class="datos">Tel&eacute;fono:</label>
                </td>
                <td>
                    <input id="telefonoEU_FC" type="text" class="obligatorio_EUFC" style="width:220px;" maxlength="20" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                           size="20"/> <label class="datos"> * </label>
                </td>
                <td>
                    <label for="telefonoCelEU_FC" id="FirmasConjuntas_tagTelefonoCelEU" class="datos">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td>
                    <input id="telefonoCodCelEU_FC" type="tel" style="width:90px;text-align:center;"  readonly="readonly"  maxlength="4" size="4" class='codigoPais obligatorio_EUFC mascaraCodPais'/><input id="telefonoCelEU_FC" type="text" style="width:130px;" maxlength="20" size="20" class='obligatorio_EUFC mascaraCelular'/>  <label class="datos"> * </label>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="direccionEU_FC" id="FirmasConjuntas_tagDireccionEU"
                           class="datos">Direcci&oacute;n:</label>
                </td>
                <td>
                    <input id="direccionEU_FC" type="text" class="obligatorio_EUFC" maxlength="120" size="86"
                           style="width:220px;"/>
                    <label class="datos">*</label>
                </td>
                <td>
                    <label for="emailEU_FC" id="FirmasConjuntas_tagEmailEU" class="datos">Email:</label>
                </td>
                <td>
                    <input id="emailEU_FC" type="text" class="obligatorio_EUFC" size="35" maxlength="55"
                           style="width:220px;"/> <label class="datos"> * </label>
                </td>

            </tr>
            <tr>

                <td>
                    <label for="grupoEU_FC" id="FirmasConjuntas_tagGrupoEU" class="datos">Grupo:</label>
                </td>
                <td>
                    <select title="Grupo" id="grupoEU_FC" class="obligatorio_EUFC" style="width:220px;"  onchange="mostrarRolesEdit();">

                    </select>
                    <label class="datos">*</label>
                </td>
            </tr>

        </table>

    </fieldset>
    <fieldset id="asignacionRolesFCEdit" class="formulario_fieldset div_consulta oculto">

        <legend id= "asigancionRolesMainEdit">Assigning Roles</legend>

        <table>
            <tr>
                <td width="40%">
                    <div id="rolesDinamicosEdit" style="padding-left:12px;">
                    </div>
                </td>
                <td>
                    <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
                        <tr>
                            <td width="100%" rowspan="4" valign="middle" w>
                                <div id="rolesDinamicosEditLista" class="roles_descrip">
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </fieldset>
</div>--%>
<!-- <div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuariosFCV2"  class="banner__title banner__title--modifier">
                ADMINISTRADOR / USUARIOS FIRMAS CONJUNTAS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome31" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="USERS_FC_MANAGEMENT">MANAGEMENT</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="USERS_FC">USERS</li>
            </ul>
            <p id="TAG_INFO_USERS_FC" class="banner__description banner__description--modifier">
                This option allows you to create, modify, block and inactivate users.
            </p>
        </div>
    </div>
</div> -->
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_consultarUsuariosFCV2"  class="banner__title banner__title--modifier">
                ADMINISTRADOR / USUARIOS FIRMAS CONJUNTAS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome31" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="USERS_FC_MANAGEMENT">MANAGEMENT</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="USERS_FC">USERS</li>
            </ul>
            <p id="TAG_INFO_USERS_FC" class="banner__description banner__description--modifier">
                This option allows you to create, modify, block and inactivate users.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaCU_FC" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                    <input id="agregarUsuarioCU_FC" type="button" class="section__button button" value="Add User">
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span id="tagFiltroConsulta_FC" class="form-filter__title"></span>
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tagCU_UsuFiltro_FC" class="form-filter__label" for="CU_usuarioFiltro_FC">Label</label>
                            <input
                                    id="CU_usuarioFiltro_FC"
                                    title="User"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_FC"
                                    maxlength="15"
                                    size="17"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCU_CIRIF_FC" class="form-filter__label" for="CU_CIRIF_FC">Label</label>
                            <input
                                    id="CU_CIRIF_FC"
                                    title="C.I./R.I.F"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_FC"
                                    maxlength="20"
                                    size="12"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagCU_GrupoFiltro_FC" class="form-filter__label" for="CU_grupoFiltro_FC">Label</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="CU_grupoFiltro_FC"
                                        title="Group"
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
                            <label id="tagCU_NomFiltro_FC" class="form-filter__label" for="CU_nombreFiltro_FC">Label</label>
                            <input
                                    id="CU_nombreFiltro_FC"
                                    title="Name"
                                    type="text"
                                    class="form-filter__input input input--form inputFormulario_FC"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="tagEstatusFiltro_FC" class="form-filter__label" for="CU_estatusFiltro_FC">Label</label>
                            <div
                                    class="form-filter__select select-section select-section--form"
                            >
                                <select
                                        id="CU_estatusFiltro_FC"
                                        title="Status"
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
                    </div>
                    <div class="form-filter__buttons">
                        <input
                                id="CU_reset_FC"
                                title="Clear"
                                type="button"
                                class="form-filter__button button button--white"
                                value="Clear"
                        />
                        <input
                                id="CU_search_FC"
                                title="Search"
                                type="button"
                                class="form-filter__button button"
                                value="Search"
                        />
                    </div>
                </div>
                <div class="table">
                    <div class="table__titles">
                        <span id="tagConsultarUsuarios_FC" class="table__title">Check Joint Signatures Users</span>
                    </div>
                    <div id="div_tabla_consultarUsuarios_FC" class="div_tabla_consultarUsuarios_FC">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_agregarUsuario_FC" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_agregarUsuarios" class="section__title"></span>
                    <input type="button" id="Cancelar_AU_FC" value="<< back" class="section__button button button--white">
                </div>
                <div class="section__row section__row--spacebetween">
                    <div id="TAG_INFO_USERS_FC_ADD"></div>
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span id="tagdatosUsuario" class="form-filter__title"></span>
                    <div id="formularioAgregarUsuario_FC" class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagUsuario" class="form-filter__label field-obligatory" for="AU_usuario_FC">Label</label>
                            <input
                                    id="AU_usuario_FC"
                                    title="User"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUFC inputFormulario only_login_FC"
                                    maxlength="15"
                                    size="17"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagNombre" class="form-filter__label field-obligatory" for="AU_nombre_FC">Label</label>
                            <input
                                    id="AU_nombre_FC"
                                    title="Name"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUFC  inputFormulario"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_Telefono" class="form-filter__label field-obligatory" for="AU_telefono_FC">Label</label>
                            <input
                                    id="AU_telefono_FC"
                                    title="Telephone"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUFC  inputFormulario"
                                    maxlength="20"
                                    size="20"
                                    onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagDireccion" class="form-filter__label field-obligatory" for="AU_direccion_FC">Label</label>
                            <input
                                    id="AU_direccion_FC"
                                    title="Address"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUFC  inputFormulario"
                                    maxlength="120"
                                    size="86"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagGrupo" class="form-filter__label" for="AU_grupo_FC">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="AU_grupo_FC"
                                        title="Group"
                                        class="select-section__select select-section__select--form selectFormulario obligatorio_AUFC"
                                        onchange="mostrarRolesAdd();"
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
                            <label id="FirmasConjuntas_tagACiRif_FC" class="form-filter__label field-obligatory" for="AU_aciRif_FC">Label</label>
                            <div class="form-filter__choose">
                                <div class="form-filter__select select-section select-section--form">
                                    <select
                                            id="AU_tipoPersona_FC"
                                            title="Type Person"
                                            class="select-section__select select-section__select--form selectFormulario obligatorio_AUFC"
                                    >
                                    </select>
                                    <img
                                            class="select-section__icon select-section__icon--form"
                                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                            alt=""
                                    />
                                </div>
                                <input
                                        id="AU_aciRif_FC"
                                        title="C.I./R.I.F"
                                        type="text"
                                        class="form-filter__input input input--form obligatorio_AUFC inputFormulario"
                                        maxlength="20"
                                        size="12"
                                        onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_TelefonoCel" class="form-filter__label field-obligatory" for="AU_telefonoCel_FC">Label</label>
                            <div class="form-filter__choose">
                                <input
                                        id="AU_telefonoCodCel_FC"
                                        title="Country Code"
                                        type="tel"
                                        class="form-filter__input input input--form codigoPais obligatorio_AUFC mascaraCodPais right"
                                        maxlength="4"
                                        size="4"
                                />
                                <input
                                        id="AU_telefonoCel_FC"
                                        title="Cell Phone"
                                        type="text"
                                        class="form-filter__input input input--form obligatorio_AUFC inputFormulario mascaraCelular"
                                        maxlength="20"
                                        size="20"
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagEmail" class="form-filter__label field-obligatory" for="AU_email_FC">Label</label>
                            <input
                                    id="AU_email_FC"
                                    title="Email"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_AUFC  inputFormulario"
                                    maxlength="55"
                                    size="20"
                            />
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input type="button" id="Resetear_AU_FC" value="Reset" class="button button--white">
                        <input type="button" id="AgregarUsuario_AU_FC" value="Add" class="button">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_tabla_editarUsuario_FC" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_editarUsuario_FC" class="section__title"></span>
                    <input type="button" id="btnBackEU_FC" value="<< back" class="section__button button button--white">
                </div>
                <div class="section__row section__row--spacebetween">
                    <div id="TAG_INFO_USERS_FC_EDIT"></div>
                </div>
            </div>
            <div class="section__content">
                <div class="form-filter">
                    <span id="tagDatosUsuarioEU_FC" class="form-filter__title"></span>
                    <div id="formularioEditarUsuario" class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagUsuarioEU" class="form-filter__label" for="usuarioEU_FC">Label</label>
                            <span id="usuarioEU_FC" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagNombreEU" class="form-filter__label field-obligatory" for="nombreEU_FC">Label</label>
                            <input
                                    id="nombreEU_FC"
                                    title="Name"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUFC"
                                    maxlength="55"
                                    size="35"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagTelefonoEU" class="form-filter__label field-obligatory" for="telefonoEU_FC">Label</label>
                            <input
                                    id="telefonoEU_FC"
                                    title="Telephone"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUFC"
                                    maxlength="20"
                                    size="20"
                                    onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagDireccionEU" class="form-filter__label field-obligatory" for="direccionEU_FC">Label</label>
                            <input
                                    id="direccionEU_FC"
                                    title="Address"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUFC"
                                    maxlength="120"
                                    size="86"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagGrupoEU" class="form-filter__label field-obligatory" for="grupoEU_FC">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="grupoEU_FC"
                                        title="Group"
                                        class="select-section__select select-section__select--form obligatorio_EUFC"
                                        onchange="mostrarRolesEdit();"
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
                            <label id="FirmasConjuntas_tagEstatusEU" class="form-filter__label field-obligatory" for="estatusEU_FC">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select
                                        id="estatusEU_FC"
                                        title="Status"
                                        class="select-section__select select-section__select--form obligatorio_EUFC"
                                        onchange="cambioEstatusFC();"
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
                            <label id="FirmasConjuntas_tagCiRifEU" class="form-filter__label field-obligatory" for="ciRifEU_FC">Label</label>
                            <div class="form-filter__choose">
                                <div class="form-filter__select select-section select-section--form">
                                    <select
                                            id="tipoPersonaEU_FC"
                                            title="Type Person"
                                            class="select-section__select select-section__select--form obligatorio_EUFC"
                                    >
                                    </select>
                                    <img
                                            class="select-section__icon select-section__icon--form"
                                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png"
                                            alt=""
                                    />
                                </div>
                                <input
                                        id="ciRifEU_FC"
                                        title="C.I./R.I.F"
                                        type="text"
                                        class="form-filter__input input input--form obligatorio_EUFC"
                                        maxlength="20"
                                        size="12"
                                        onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagTelefonoCelEU" class="form-filter__label field-obligatory" for="telefonoCelEU_FC">Label</label>
                            <div class="form-filter__choose">
                                <input
                                        id="telefonoCodCelEU_FC"
                                        title="Country Code"
                                        type="tel"
                                        class="form-filter__input input input--form codigoPais obligatorio_EUFC mascaraCodPais right"
                                        maxlength="4"
                                        size="4"
                                />
                                <input
                                        id="telefonoCelEU_FC"
                                        title="Cell Phone"
                                        type="text"
                                        class="form-filter__input input input--form obligatorio_EUFC mascaraCelular"
                                        maxlength="20"
                                        size="20"
                                />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="FirmasConjuntas_tagEmailEU" class="form-filter__label field-obligatory" for="emailEU_FC">Label</label>
                            <input
                                    id="emailEU_FC"
                                    title="Email"
                                    type="text"
                                    class="form-filter__input input input--form obligatorio_EUFC"
                                    maxlength="55"
                                    size="20"
                            />
                        </div>
                    </div>
                    <div id="asignacionRolesFCEdit" class="form-filter__section oculto">
                        <span id= "asigancionRolesMainEdit" class="form-filter__title"></span>
                        <div class="form-filter__col2">
                            <div id="rolesDinamicosEdit">
                            </div>
                            <div id="rolesDinamicosEditLista">
                            </div>
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input type="button" id="btnDeshacerEU_FC" value="Clear" class="button button--white">
                        <input type="button" id="btnResetearPasswordEU_FC" value="Reset Password" class="button button--white">
                        <input type="button" id="btnSalvarEU_FC" value="Save" class="button">
                        <input type="button" id="btnSendUserEU_FC" value="Send User" class="button">
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>










<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="div_tabla_consultaCU_FC" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarUsuariosFC">Administrador / Usuarios Firmas Conjuntas</h1>
    </div>

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
                    <input id="AU_usuario_FC" type="text" class="obligatorio_AUFC inputFormulario" title="Usuario"
                           style="width:195px" maxlength="15" size="17"/> <label class="datos"> * </label>
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
                    <label for="AU_telefono_FC" id="FirmasConjuntas_Telefono" class="datos">Tel&eacute;fono:</label>
                </td>
                <td>
                    <input id="AU_telefono_FC" type="text" class="obligatorio_AUFC  inputFormulario"
                           title="Tel&eacute;fono" style="width:195px" maxlength="20" size="20"/> <label class="datos">
                    * </label>
                </td>
                <td>
                    <label for="AU_telefonoCel_FC" id="FirmasConjuntas_TelefonoCel" class="datos">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td>
                    <input id="AU_telefonoCel_FC" type="text" class="obligatorio_AUFC inputFormulario mascaraCelular" title="Tel&eacute;fono Celular"
                           style="width:195px" maxlength="20" size="20"/>  <label class="datos">
                    * </label>
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
                    <label for="AU_email_FC" id="FirmasConjuntas_tagEmail" class="datos">Email:</label>
                </td>
                <td>
                    <input id="AU_email_FC" type="text" class="obligatorio_AUFC  inputFormulario" title="Email"
                           style="width:195px" maxlength="55" size="20"/> <label class="datos"> * </label>
                </td>

            </tr>
            <tr>
                <td>
                    <label for="AU_grupo_FC" id="FirmasConjuntas_tagGrupo" class="datos">Grupo:</label>
                </td>
                <td>
                    <select id="AU_grupo_FC" title="Grupo" class="selectFormulario obligatorio_AUFC" style="width:195px">

                    </select>
                </td>

            </tr>
        </table>

    </fieldset>
</div>

<div id="div_tabla_editarUsuario_FC" style="display: none">
    <div>
        <h1 id="Titulo_editarUsuario_FC">Usuario Firma Conjunta / Editar Usuario</h1>
    </div>
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
                           style="width:220px;"/> <label class="datos"> * </label>
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
                    <input id="telefonoEU_FC" type="text" class="obligatorio_EUFC" style="width:220px;" maxlength="20"
                           size="20"/> <label class="datos"> * </label>
                </td>
                <td>
                    <label for="telefonoCelEU_FC" id="FirmasConjuntas_tagTelefonoCelEU" class="datos">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td>
                    <input id="telefonoCelEU_FC" type="text" style="width:220px;" maxlength="20" size="20" class='obligatorio_EUFC mascaraCelular'/>  <label class="datos"> * </label>
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
                    <select title="Grupo" id="grupoEU_FC" class="obligatorio_EUFC" style="width:220px;">

                    </select>
                    <label class="datos">*</label>
                </td>
            </tr>

        </table>

    </fieldset>
</div>









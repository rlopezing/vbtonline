<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="div_tabla_consultaCU" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarUsuarios">Usuarios</h1>
    </div>
<fieldset class="div_consulta 0000000003_1">
    <%--<legend>Usuarios</legend>--%>
    <%--<label class="datos2"> <a href="../../jsp/backOffice/AgregarUsuario.jsp"id="" >Agregar Usuario </a> </label>--%>
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

        <legend>Datos del Usuario</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0"  border="0" width="80%" align="center">
            <tr>
                <td >
                    <label for="AU_usuario" id="tagUsuario"  class="datos">Usuario:</label>
                </td>
                <td >
                    <input id="AU_usuario" type="text" class="obligatorio_AUBO inputFormulario" title="Usuario" style="width:195px" maxlength="15" size="17"/> <label class="datos">  * </label>
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
                    <input id="nombreEU" type="text" class="obligatorio_EUBO"  title="Nombre" size="35" maxlength="55" style="width:220px;"/> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="ciRifEU"  id="tagCiRifEU" class="datos2">C.I./R.I.F:</label>
                </td>
                <td >
                    <select id="tipoPersonaEU" class="obligatorio_EUBO" style="width:90px;" title="Tipo Persona">

                    </select>
                    <input id="ciRifEU" type="text" class="obligatorio_EUBO" style="width:125px;" title="C.I./R.I.F" maxlength="20" size="12"/><label class="datos">  * </label>
                </td>

            </tr>

            <tr>
                <td >
                    <label for="telefonoEU" id="tagTelefonoEU"  class="datos2">Tel&eacute;fono:</label>
                </td>
                <td >
                    <input id="telefonoEU" type="text" class="obligatorio_EUBO" style="width:220px;" maxlength="20" size="20" title="Telefono" /> <label class="datos">  * </label>
                </td>
                <td >
                    <label for="telefonoCelEU" id="tagTelefonoCelEU"  class="datos2">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td >
                    <input id="telefonoCelEU" type="text"  class="obligatorio_EUBO" style="width:220px;" maxlength="20" size="20" title='Telefono Celular'/> <label class="datos">  * </label>
                </td>
            </tr>
            <tr>
                <td >
                    <label for="direccionEU" id="tagDireccionEU"  class="datos2">Direcci&oacute;n:</label>
                </td>
                <td >
                    <input id="direccionEU" type="text" class="obligatorio_EUBO" maxlength="120" size="86" style="width:220px;" title="Direccion"/>
                    <label class="datos">*</label>
                </td>
                <td >
                    <label for="emailEU" id="tagEmailEU" class="datos2">Email:</label>
                </td>
                <td >
                    <input id="emailEU" type="text" class="obligatorio_EUBO" size="35" maxlength="55" style="width:220px;" title='Email'/> <label class="datos">  * </label>
                </td>

            </tr>
            <tr>

                <td>
                    <label for="grupoEU"  id="tagGrupoEU" class="datos2">Grupo:</label>
                </td>
                <td>
                    <select id="grupoEU" class="obligatorio_EUBO" style="width:220px;" title="Grupo">

                    </select>
                    <label class="datos" >*</label>
                </td>
                <td >
                    <label for="ambitoEU" id="tagAmbitoEU"  class="datos2">Ambito:</label>
                </td>
                <td >
                    <select id="ambitoEU" class="selectFormulario obligatorio_EUBO" title="&Aacute;mbito" style="width:195px">

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
        <%--<legend id="Legend_OpcionesSistema">  </legend>--%>
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







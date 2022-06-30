<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <input align="left" type="button" id="btnSalvarEU_CUC" value="Guardar" class="botonMin 0000000029_2">
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
                <td width="25%">
                    <label for="usuarioEU_CUC" id="tagUsuarioEU_CUC"  class="datos2">Usuario:</label>
                </td>
                <td width="25%">
                    <b><label class="datos" id="usuarioEU_CUC" > </label></b>
                </td>
                <td width="25%">
                    <label for="estatusEU_CUC" id="tagEstatusEU_CUC"  class="datos2">Estatus:</label>
                </td>
                <td width="25%">
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
                    <select id="telefonoEU_CUC" class="obligatorio_EUCBO cambioStatus" title="Telefono">

                    </select>
                    <label class="datos" >*</label>
                </td>
                <td >
                    <label for="telefonoCelEU_CUC" id="tagTelefonoCelEU_CUC"  class="datos2">Tel&eacute;fono C&eacute;lular:</label>
                </td>
                <td >
                    <select id="telefonoCelEU_CUC" class="obligatorio_EUCBO cambioStatus" title='Telefono Celular'>

                    </select>
                    <label class="datos" >*</label>
                </td>
            </tr>
            <tr>
                <td >
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
            <%--<legend id="Legend_OpcionesSistema">  </legend>--%>
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
</div>
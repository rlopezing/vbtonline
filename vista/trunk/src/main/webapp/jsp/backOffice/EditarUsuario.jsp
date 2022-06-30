<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 10:00 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="../vbtonline/resources/js/backoffice/backoffice.js?_expirecache_=1"></script>
<link rel="stylesheet" href="../vbtonline/resources/css/style.css?_expirecache_=1" type="text/css" />


<div>

    <div id="form_bloqueos">


        <fieldset id="formulario" class="formulario_fieldset">

            <legend>Datos del Usuario</legend>
            <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
                <tr>
                    <td width="25%">
                        <label for="usuario" id="tagUsuario"  class="datos2">Usuario:</label>
                    </td>
                    <td width="25%">
                        <label class="datos" id="usuario"> <b> usuario</b></label>
                    </td>
                    <td width="25%">
                        <label for="estatus" id="tagEstatus"  class="datos2">Estatus:</label>
                    </td>
                    <td width="25%">
                        <select id="estatus">
                            <option value="0">Activo</option>
                            <option value="1">Inactivo</option>
                            <option value="2">Bloqueado</option>
                        </select>
                        <label class="datos">*</label>
                    </td>

                </tr>
                <tr>
                    <td >
                        <label for="nombre" id="tagNombre"  class="datos2">Nombre:</label>
                    </td>
                    <td >
                        <input id="nombre" type="text" class="obligatorio"/> <label class="datos">  * </label>
                    </td>
                    <td >
                        <label for="ciRif"  id="tagCiRif" class="datos2">C.I./R.I.F:</label>
                    </td>
                    <td >
                        <select id="tipoPersona">
                            <option value="0">A</option>
                            <option value="1">C</option>
                            <option value="2">E</option>
                            <option value="3">G</option>
                            <option value="4">I</option>
                            <option value="5">J</option>
                            <option value="6">P</option>
                            <option value="7">R</option>
                            <option value="8">V</option>
                        </select>
                        <input id="ciRif" type="text" class="obligatorio"/><label class="datos">  * </label>
                    </td>

                </tr>
                <tr>
                    <td >
                        <label for="email" id="OJO_tagEmail" class="datos2">Email:</label>
                    </td>
                    <td >
                        <input id="email" type="text" class="obligatorio"/> <label class="datos">  * </label>
                    </td>
                    <td>
                        <label for="grupo"  id="tagGrupo" class="datos2">Grupo:</label>
                    </td>
                    <td>
                        <select id="grupo">
                            <option value="0">--- Select ----</option>
                            <option value="1">BackOffice Administrador</option>
                            <option value="2">BackOffice Loader</option>
                            <option value="3">BackOffice Supervisor</option>
                            <option value="4">BackOffice Asesor</option>
                        </select>
                        <label class="datos" >*</label>
                    </td>
                </tr>
                <tr>
                    <td >
                        <label for="telefono" id="tagTelefono"  class="datos2">Tel&eacute;fono:</label>
                    </td>
                    <td >
                        <input id="telefono" type="text" class="obligatorio"/> <label class="datos">  * </label>
                    </td>
                    <td >
                        <label for="telefonoCel" id="tagTelefonoCel"  class="datos2">Tel&eacute;fono C&eacute;lular:</label>
                    </td>
                    <td >
                        <input id="telefonoCel" type="text"/>
                    </td>
                </tr>
                <tr>
                    <td >
                        <label for="direccion" id="tagDireccion"  class="datos2">Direcci&oacute;n:</label>
                    </td>
                    <td  colspan="3">
                        <input id="direccion" type="text" class="obligatorio"/>
                        <label class="datos">*</label>
                    </td>
                </tr>

            </table>
        </fieldset>

        <fieldset id="formulario2" class="formulario_fieldset5">
            <input align="left" type="button" id="btnSalvarEU" value="Guardar" class="boton">
            <input align="left" type="reset" id="btnDeshacerEU" value="Resetear" class="boton" >
            <input align="left" type="reset" id="btnAccesosEspecialesEU" value="Accesos Especiales" class="boton3" >
            <input align="left" type="reset" id="btnResetearPasswordEU" value="Reset Password" class="boton2" >
            <input align="right" type="button" id="btnBackEU" value="Volver" class="boton4" >
        </fieldset>


    </div>

</div>
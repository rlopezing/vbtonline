<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" language="javascript" src="../vbtonline/resources/js/backoffice/grupos/gruposOpcionesSistema.js?_expirecache_=1"></script>

<fieldset class="div_consulta">
    <legend>Filtro de consulta</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion1" value="CONSULTAR"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion2" value="AGREGAR"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion3" value="EDITAR"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion4" value="ELIMINAR"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion5" value="EDITAR ESTATUS"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion6" value="REINICIAR CLAVE"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos"  type="checkbox" name="opcion7" value="APROBAR"/>
            </td>
        </tr>
        <tr>
            <td>
                <input class="datos" type="checkbox" name="opcion8" value="LIBERAR"/>
                <span id="mensaje"></span>
                <input class="datos" type="text" name="desopc" id="desopc"  value="LIBERAR"/>
            </td>
        </tr>
        <tr>
            <td class="botones_formulario">
                <input type="button" id="search" value="Search" class="boton4">
                <input type="reset" id="reset" value="Reset" class="boton4">
            </td>
        </tr>

      </tbody>
    </table>

</fieldset>

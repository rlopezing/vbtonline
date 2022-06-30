<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="tag_titulo_VisorDeSucesos"> Visor de Sucesos</h1>
</div>

<fieldset class="div_consulta">
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr id="fechas_sucesos">
            <td class="datos">
                <span id="sucesos_TAGDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_sucesos" class="inputFormulario_sucesos requeridoFecha_sucesos" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="sucesos_TAGHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_sucesos"  class="inputFormulario_sucesos requeridoFecha_sucesos" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>

            <td  class="botones_formulario">
                <input type="button" id="consultar_sucesos" value="Consultar" class="botonEDOCuenta">
                <input type="button" id="limpiar_sucesos" value="Limpiar" class="botonEDOCuenta">

            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="div_tabla_consulta_Sucesos" class="div_tabla_consulta">
    </div>
    <div id="paginacion_tabla_consulta_Sucesos" class="div_tabla_consulta">
    </div>
</fieldset>
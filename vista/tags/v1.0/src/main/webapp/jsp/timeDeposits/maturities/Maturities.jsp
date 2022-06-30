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
    <h1 id="colocaciones_titulo_V">Colocaciones / Vencimientos </h1>
</div>
<div id="div_vencimientos_colocaciones">
<fieldset class="div_consulta">
    <legend><span id="colocacionesvencimientos_TAGVencimientos">Vencimientos</span>&nbsp;<span id="colocacionesbloqueos_TAGAl">al</span>&nbsp;<span id="fechaCierreVencimiento"></span>  </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr>
            <td class="datos"> <span id="colocacionescertificados_TAGBuscar">Buscar </span></td>

            <td class="datos"></td>
        </tr>
        <tr>

            <td class="datos">
                <select  id="buscar_ColocacionesVencimientos" title="Buscar" class="requerido_ColVen" style="width:250px;" onchange="validarActivarFechas_ColVen(this.value)" >

                </select>
            </td>
            <td  class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                <a id="maturity_imprimir" onclick="print_VENCIMIENTOS()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
            <td  class="botones_formulario" width="10%">
                <input type="button" id="consultar_ColVen" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>
        <tr id="fechas_ColVen" style="display: none">
            <td class="datos">
                <span id="colocacionesvencimientos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_ColVen" class="invisible_print inputFormulario requeridoFecha_ColVen" tabindex="2">
                <span id="fechaDesdeFiltro_ColVen_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="colocacionesvencimientos_TAGFechaHasta">Fecha Hasta:</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_ColVen"  class="invisible_print inputFormulario requeridoFecha_ColVen" tabindex="2">
                <span id="fechaHastaFiltro_ColVen_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="vencimientos_ColVen_div_tabla_consulta" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_noPoseeColocacionesVencimientos" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones4">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>
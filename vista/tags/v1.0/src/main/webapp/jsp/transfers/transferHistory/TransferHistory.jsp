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
    <h1 id="tag_titulo_consultarTransferencias">Transferencias / Consultar Transferencias</h1>
</div>

<fieldset class="div_consulta">
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr id="fechas_Transferencias">
            <td class="datos">
                <span id="transferencias_TAGDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_Transferencias" class="invisible_print inputFormulario requeridoFecha_Transferencias" tabindex="2">
                <span id="fechaDesdeFiltro_Transferencias_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="transferencias_TAGHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_Transferencias"  class="invisible_print inputFormulario requeridoFecha_Transferencias" tabindex="2">
                <span id="fechaHastaFiltro_Transferencias_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

            <td class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                <a style="display: none" id="transfer_history_imprimir" onclick="printConsultTranfer()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

            <td  class="botones_formulario">
                <input type="button" id="consultar_Transferencias" value="Consultar" class="botonEDOCuenta">

            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="div_tabla_consulta_TransferenciasGenerales" class="div_tabla_consulta"> </div>
    <div id="paginacion_tabla_consulta_TransferenciasGenerales" class="div_tabla_consulta"> </div>
</fieldset>
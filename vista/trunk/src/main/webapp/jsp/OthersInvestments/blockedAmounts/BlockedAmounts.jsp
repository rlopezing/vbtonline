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
    <h1 id="OI_titulo_bloqueos">OTRAS INVERSIONES / Bloqueos </h1>
</div>
<div id="div_OtherInvestments_BA">

<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%">
        <tbody>
        <tr>
            <td class="datos"><span class="Fondo_label_Bloqueos_OI" id="otrasinversionesbloqueos_TAGOtrasInversiones">Otras Inversiones </span><span>:</span></td>
            <td class="datos">
                <select  id="numero_cuenta_Bloqueos_OI" title="Numero de cuenta" class="requerido_Bloqueos_OI" onchange="limpiarTablaBloqueosOI(this.value);" >

                </select>
            </td>
            <td  class="botones_formulario">
                <div id="botonVolverASaldoOI" style="display:none"> <input type="button" id="volverASaldosOIBoton" value="Volver" class="botonEDOCuenta"></div>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">

    <legend ><span id="labelSaldo_Bloqueos_OI"></span><span id="fechaBloqueado_Bloqueos_OI"></span> </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr>


            <td class="datos" width="20%">
                <span id="otrasinversionesbloqueos_TAGBuscar">Buscar </span><span>:</span>
                <select  id="buscar_Bloqueos_OI" title="Buscar" class="requerido_Bloqueos_OI selectFormulario_OI_BA" style="width:170px;" onchange="validarActivarFechas_Bloqueos_OI(this.value)" >

                </select>
            </td>
            <td width="20%"></td>
            <td width="20%"></td>
            <td  class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                <a id="other_blocked_imprimir" onclick="print_BLOQUEOS_FONDOS_OI()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
            <td  class="botones_formulario" width="10%">
                <input type="button" id="consulta_Bloqueos_OI" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>

        <tr id="fechas_Bloqueos_OI" style="display: none">
            <td class="datos"></td>
            <td class="datos" colspan="2">
                <span id="otrasinversionesbloqueos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_Bloqueos_OI" class="invisible_print inputFormulario_OI_BA requeridoFecha_Bloqueos_OI" tabindex="2">
                <span id="fechaDesdeFiltro_Bloqueos_OI_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="otrasinversionesbloqueos_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_Bloqueos_OI"  class="invisible_print inputFormulario_OI_BA requeridoFecha_Bloqueos_OI" tabindex="2">
                <span id="fechaHastaFiltro_Bloqueos_OI_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="div_tabla_consulta_Bloqueos_OI" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_noInfo_blockedAmount_otherInvestment" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="noInfo_otherInvestment2">Usted no posee Fondos Mutuales que consultar </span>
    </fieldset>
</div>
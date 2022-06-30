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
    <h1 id="FM_titulo_bloqueos">Fondos Mutuales / Bloqueos </h1>
</div>
<div id="div_mutualFunds_BA">

    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%">
            <tbody>
            <tr>
                <td class="datos"><span class="Fondo_label_Bloqueos_FM" id="fondosbloqueos_TAGFondoMutual">Fondos Mutuales </span><span>:</span></td>
                <td class="datos">
                    <select  id="numero_cuenta_Bloqueos_FM" title="Numero de cuenta" class="requerido_Bloqueos_FM" onchange="limpiarTablaBloqueosMF(this.value);" >

                    </select>
                </td>
                <td  class="botones_formulario">
                    <div id="botonVolverASaldoMutual" style="display:none"> <input type="button" id="volverASaldosMutualBoton" value="Volver" class="botonEDOCuenta"></div>
                </td>

            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">

        <legend ><span id="fechaBloqueado_Bloqueos_FM"></span><span id="label_fechaBloqueado"></span> </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tr>


                <td class="datos" width="20%">
                    <span id="fondosbloqueos_TAGBuscar">Buscar </span><span>:</span>
                    <select  id="buscar_Bloqueos_FM" title="Buscar" class="requerido_Bloqueos_FM selectFormulario_MF_BA" style="width:170px;" onchange="validarActivarFechas_Bloqueos_FM(this.value)" >

                    </select>
                </td>
                <td width="20%"></td>
                <td width="20%"></td>
                <td  class="botones_formulario">
                    <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                    <a id="mutual_blocked_imprimir" onclick="print_BLOQUEOS_FONDOS()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
                </td>
                <td  class="botones_formulario" width="10%">
                    <input type="button" id="consulta_Bloqueos_FM" value="Consultar" class="botonEDOCuenta">
                </td>
            </tr>

            <tr id="fechas_Bloqueos_FM" style="display: none">
                <td class="datos"></td>
                <td class="datos" colspan="2">
                    <span id="fondosbloqueos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                    <input type="text" title="Fecha desde" id="fechaDesdeFiltro_Bloqueos_FM" class="invisible_print inputFormulario_MF_BA requeridoFecha_Bloqueos_FM" tabindex="2">
                    <span id="fechaDesdeFiltro_Bloqueos_FM_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>
                <td class="datos">
                    <span id="fondosbloqueos_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                    <input type="text" title="Fecha hasta" id="fechaHastaFiltro_Bloqueos_FM"  class="invisible_print inputFormulario_MF_BA requeridoFecha_Bloqueos_FM" tabindex="2">
                    <span id="fechaHastaFiltro_Bloqueos_FM_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>

            </tr>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_Bloqueos_FM" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="div_noInfo_blockedAmount_mutualFunds" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="noInfo_mutualFunds2">Usted no posee Fondos Mutuales que consultar </span>
    </fieldset>
</div>
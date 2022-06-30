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
    <h1 id="colocaciones_titulo_BA">Colocaciones / Saldos Bloqueados </h1>
</div>
<div id="div_colocacionesBloqueos" style="display: none">
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%">
            <tbody>
            <tr>
                <td class="datos" width="14%"><span class="col_label_timeDepositNumber" id="colocacionesbloqueos_TAGNumeroColocacion">Colocaci&oacute;n N&uacute;mero </span><span>:</span></td>
                <td class="datos">
                    <select  id="colocaciones_numero_cuenta_ColBlo" title="Numero de cuenta" class="requerido_ColBlo invisible_print" style="width:240px;" onchange="limpiarColocacionesBA(this.value);">

                    </select>
                    <span id="colocaciones_numero_cuenta_ColBlo_select" class="visible_print"></span>
                </td>
                <td  class="botones_formulario">
                   <div id="botonVolverASaldo" style="display:none"> <input type="button" id="volverASaldos" value="Volver" class="botonEDOCuenta"></div>
                </td>

            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend ><span id="tag_fechaBloqueado_colocaciones"></span><span id="tag_fechaBloqueado"></span> </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tr>
                <td class="datos"> </td>

                <td class="datos">
                    <span id="colocacionesbloqueos_TAGBuscar">Buscar </span><span>:</span>
                    <select  id="buscar_ColocacionesBloqueos" title="Buscar" class="requerido_ColBlo invisible_print" style="width:150px;" onchange="validarActivarFechas_ColBlo(this.value)" >

                    </select>
                    <span id="buscar_ColocacionesBloqueos_select" class="visible_print"></span>
                </td>
                <td  class="botones_formulario">
                    <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                    <a id="time_blocked_imprimir" onclick="print_BLOQUEOS_COLOCACIONES()" title="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
                </td>
                <td  class="botones_formulario" width="10%">
                    <input type="button" id="consultar_ColBlo" value="Consultar" class="botonEDOCuenta">
                </td>
            </tr>

            <tr id="fechas_ColBlo" style="display: none">
                <td class="datos"></td>
                <td class="datos">
                    <span id="colocacionesbloqueos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                    <input type="text" title="Fecha desde" id="fechaDesdeFiltro_ColBlo" class="invisible_print inputFormulario requeridoFecha_ColBlo" tabindex="2">
                    <span id="fechaDesdeFiltro_ColBlo_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>
                <td class="datos">
                    <span id="colocacionesbloqueos_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                    <input type="text" title="Fecha hasta" id="fechaHastaFiltro_ColBlo"  class="invisible_print inputFormulario requeridoFecha_ColBlo" tabindex="2">
                    <span id="fechaHastaFiltro_ColBlo_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>

            </tr>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <div id="colocaciones_div_tabla_consulta_montoBloqueado" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="div_noPoseeColocacionesBloqueos" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones2">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>

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
    <h1 id="tagTitulo_Account_Bloqueos">Cuentas / Bloqueos</h1>
</div>
<div id="div_account_BlockedAmount">
<fieldset class="div_consulta">
    <table width="100%" SUMMARY='tabla' cellspacing="0" cellpadding="0">
        <tbody>
        <tr>
            <td class="datos"><span class="label_numero_cuenta" id="cuentasbloqueos_TAGNumeroCuenta">Numero de cuenta </span><span>:</span>
                <select  id="estado_cuenta_numero_cuenta_BA" title="Numero de cuenta" class="requeridoBA selectFormulario_account_BA" style="width:250px;"  onchange="limpiarBloqueoCuenta(); consultarMontosBloqueadoOnClick();">

                </select>
            </td>
            <td  class="botones_formulario">
                <div id="botonVolverASaldoCuentas" style="display:none"> <input type="button" id="volverASaldosCuentasBoton" value="Volver" class="botonEDOCuenta"></div>
            </td>
            <td class="botones_formulario">
                <a id="between_account_imprimir_bloqueos" onclick="crearPDF_BlockedAmount()" title="imprimir">
                    <img class="imprimir"  src="../vbtonline/resources/images/comun/impresora.gif" border="0" width="18" height="15"/>
                </a>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <legend>
        <span id="cuentassaldos_TAGTransacciones2" style="display: block">Transacciones</span>
        <span id="cuentassaldos_TAGTransacciones_al" style="display: none">Transacciones al: </span>
        <span id="cuentassaldos_TAGTransacciones_fechaCierre"></span>
    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr>
            <td class="datos"> <span id="cuentasbloqueos_TAGBuscar">Buscar </span><span>:</span></td>

            <td class="datos"></td>
        </tr>
        <tr>

            <td class="datos">
                <select  id="buscar_BA" title="Buscar" class="requeridoBA selectFormulario_account_BA" style="width:160px;" onchange="validarActivarFechasBA(this.value)" >

                </select>
            </td>

            <td  width="45%" class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                <a style="display: none" id="blocked_imprimir" HREF="javascript:crearPDF_BlockedAmount()" TITLE="imprimir"> <img class="imprimir"  src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

            <td  class="botones_formulario" width="10%">
                <input type="button" id="estado_cuenta_consultar_BA" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>
        <tr id="fechas_BA" style="display: none">
            <td class="datos">
                <span id="cuentasbloqueos_TAGFechaDesde">Fecha Desde:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltroBA" class="invisible_print inputFormulario_account_BA requeridoFechaBA" tabindex="2">
                <span id="fechaDesdeFiltroBA_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="cuentasbloqueos_TAGFechaHasta">Fecha Hasta:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltroBA"  class="invisible_print inputFormulario_account_BA requeridoFechaBA" tabindex="2">
                <span id="fechaHastaFiltroBA_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="estado_cuenta_div_tabla_consulta_BA" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="noInfo_blockedAmount_account" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account3">No posee cuentas que consultar </span>
    </fieldset>
</div>
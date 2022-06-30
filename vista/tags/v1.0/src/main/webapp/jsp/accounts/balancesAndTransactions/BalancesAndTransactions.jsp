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
    <h1 id="tagTitulo_Account_BT">Cuentas / Saldos y Transacciones</h1>
</div>
<div id="div_account_BalancesAndTransactions">
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tbody>
            <tr>
                <td class="datos"><span class="label_numero_cuenta" id="cuentassaldos_TAGNumeroCuenta">N&uacute;mero de cuenta </span><span>:</span></td>
                <td class="datos">
                    <select  id="estado_cuenta_numero_cuenta_BT" title="Numero de cuenta" class="invisible_print requeridoBT selectFormulario_Account_BA" style="width:266px;" onchange="cargarDetalleCuenta(this.value)" >

                    </select>
                    <span id="estado_cuenta_numero_cuenta_BT_select" class="visible_print"></span>
                </td>
                <td>
                    <span class="datos2" id="cuentassaldos_TAGDescripcionOpcionSaldos">Esta opci&oacute;n le permite consultar los saldos y transacciones de sus cuentas.</span>
                </td>
                <td  width='8%'>
                    <div id="botonVolverPortafolio"> <input type="button" id="btnVolverPortafolio" value="Volver" class="botonEDOCuenta oculto"></div>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend>
            <span id="tagAccount_DatosCuenta_BT" style="display: block">Saldo</span>
            <span id="tagAccount_DatosCuenta_BT2" style="display: none">Saldo al: </span>
            <span id="tagAccount_fecha_BT"></span>
        </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tbody>
            <tr>
                <td class="datos4">
                    <span class="label_titular" id="cuentassaldos_TAGTitularCuenta">Titular </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="titular_BT" class="spanFormulario_Account_BA">  </span>
                </td>
                <td class="datos4">
                    <%--<span class="label_bloqueado" id="cuentassaldos_TAGBloqueado">Bloqueado </span><span>:</span>--%>
                    <div id="linkBloqueo_account"></div>
                </td>
                <td class="datos6" >
                    <span id="bloqueado_BT" class="spanFormulario_Account_BA"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4">
                    <span class="label_moneda" id="cuentassaldos_TAGMoneda">Moneda </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="moneda_BT" class="spanFormulario_Account_BA">  </span>
                </td>
                <td class="datos4">
                    <span class="label_diferido" id="cuentassaldos_TAGDiferido">Diferido </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="diferido_BT" class="spanFormulario_Account_BA">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos4">
                </td>
                <td class="datos5" >
                </td>
                <td class="datos4">
                    <span class="label_disponible" id="cuentassaldos_TAGDisponible">Disponible </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="disponible_BT" class="spanFormulario_Account_BA">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos4">
                    <%--<input id="estado_cuenta_imprime" class="boton" value="imprimir">--%>
                </td>
                <td class="datos5" >
                </td>
                <td class="datos4">
                    <span class="label_bloqueado" id="cuentassaldos_TAGSaldoActual">Saldo Actual </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="saldo_actual_BT" class=" spanFormulario_Account_BA"> </span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend id="cuentassaldos_TAGTransacciones"> Transacciones </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 100%;">
            <tr>
                <td class="datos"> <span id="cuentassaldos_TAGTipoTransaccion">Tipo de Transacci&oacute;n </span><span>:</span></td>
                <td class="datos"> <span id="cuentassaldos_TAGIntervaloTransaccion">Ver Transacciones de </span><span>:</span></td>
                <td class="datos"></td>
            </tr>
            <tr>
                <td class="datos">
                    <select  id="tipo_transaccion_BT" title="Tipo Transaccion" class="" style="width:200px;"  >

                    </select>
                </td>
                <td class="datos">
                    <select  id="transaccion_desde_BT" title="Transaction desde" class="requeridoBT selectFormulario_Account_BA" style="width:100px;" onchange="validarActivarFechas(this.value)" >

                    </select>
                </td>

                <td  width="45%" class="botones_formulario">
                    <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                    <a style="display: none" id="balance_imprimir" onclick="crearPDF_balancesAndTransactions()" TITLE="imprimir"> <img class="imprimir"  src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
                </td>

                <td  class="botones_formulario" width="10%">
                    <input type="button" id="estado_cuenta_consultar_BT" value="Consultar" class="botonEDOCuenta">
                </td>
            </tr>
            <tr id="fechas_BT" style="display: none">
                <td class="datos" style="width: 31%;">
                    <span id="cuentassaldos_TAGFechaDesde">Desde</span><span>:</span>
                    <%--esto debe ser un calendario que permita seleccionar la fecha--%>
                    <input type="text" title="Fecha desde" id="fechaDesdeFiltroBT" class="invisible_print inputFormulario_Account_BA requeridoFechaBT" tabindex="2" maxlength="10">
                    <span id="fechaDesdeFiltroBT_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>
                <td class="datos" style="width: 31%;">
                    <span id="cuentassaldos_TAGFechaHasta">Hasta</span><span>:</span>
                    <%--esto debe ser un calendario que permita seleccionar la fecha--%>
                    <input type="text" title="Fecha hasta" id="fechaHastaFiltroBT"  class="invisible_print inputFormulario_Account_BA requeridoFechaBT" tabindex="2" maxlength="10">
                    <span id="fechaHastaFiltroBT_print" class="datos visible_print"></span>
                    <label class="datos invisible_print">dd/mm/yyyy</label>
                </td>
                <td>&nbsp;</td>
            </tr>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <div id="estado_cuenta_div_tabla_consulta_BT" class="div_tabla_consulta">
        </div>
        <div id="paginacion_estado_cuenta_tabla_consulta_BT" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="noInfo_balancesAndTransactions_account" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account">No posee cuentas que consultar </span>
    </fieldset>
</div>
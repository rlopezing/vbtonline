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
    <h1 id="OI_titulo_BT">Otras Inversiones / Saldos y Transacciones </h1>
</div>
<div id="div_OtherInvestments_BT">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td class="datos" width="11%"><span class="OtherInvestments_label_SalTrans_OI" id="otrasinversionessaldos_TAGOtrasInversiones">Otras Inversiones </span><span>:</span></td>
            <td class="datos" width="70%">
                <select  id="numero_cuenta_SalTrans_OI" title="Numero de cuenta" class="invisible_print requerido_SalTrans_OI" style="" onchange="cargarDetalleCuentaOtrasInversiones(this.value)">

                </select>
                <span id="numero_cuenta_SalTrans_OI_select" class="visible_print"></span>
            </td>
            <td  width='10%'>
                <div id="botonVolverPortafolioOI"> <input type="button" id="btnVolverPortafolioOI" value="Volver" class="botonEDOCuenta oculto"></div>
            </td>
            <td width="5%" class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                <a id="saldos_imprimir" title="Imprimir" onclick="print_SALDOS_FONDOS_OI()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <legend>
        <span id="saldo_BT_OtherInvestments" style="display: block"></span>
        <span id="saldo_BT_OtherInvestments2" style="display: none"></span>
        <span id="saldo_BT_OtherInvestmentsFecha" style="display: none"></span>
    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" >
                <span class="label_titular_SalTrans_OI" id="otrasinversionessaldos_TAGTitularOtrasInversiones">Titular Inversi&oacute;n</span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="titular_SalTrans_OI" class="spanFormulario_OI_BT">  </span>
            </td>

            <td class="datos4" >
                <%--<span class="label_unidadesBloqueadas_SalTrans_OI" id="otrasinversionessaldos_TAGBloqueado">Unidades Bloqueadas </span><span>:</span>--%>
                <div id="linkBloqueo_OI"></div>
            </td>
            <td class="datos6" >
                <span id="unidadesBloqueadas_SalTrans_OI" class="spanFormulario_OI_BT">  </span>
            </td>


        </tr>
        <tr>
            <td class="datos4" >
                <span class="label_moneda_SalTrans_OI" id="otrasinversionessaldos_TAGMoneda">Moneda </span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="moneda_SalTrans_OI" class="spanFormulario_OI_BT">  </span>
            </td>

            <td class="datos4" >
                <span class="label_unidadDisponible_SalTrans_OI" id="otrasinversionessaldos_TAGDisponible">Unidades Disponibles </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadDisponible_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >

            </td>
            <td class="datos5" >

            </td>
            <td class="datos4" >
                <span class="label_unidadesTotales_SalTrans_OI" id="otrasinversionessaldos_TAGValorActual">Unidades Totales </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadesTotales_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_VUI_SalTrans_OI" id="otrasinversionessaldos_TAGVUI">VUI </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="VUI_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_montoTransito_SalTrans_OI" id="otrasinversionessaldos_TAGEnTransito">Monto en Tr&aacute;nsito </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="montoTransito_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_totalMoneda_SalTrans_OI" id="otrasinversionessaldos_TAGTotalMoneda">Total en Moneda </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="totalMoneda_SalTrans_OI" class="spanFormulario_OI_BT"> </span>
            </td>
        </tr>

        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <%--<span id="fechaBloqueado_SalTrans_FM"></span>--%>
    <legend id="otrasinversionessaldos_TAGTransacciones"> Transacciones</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 100%;">
        <tr>
            <td class="datos" width="34%">
                <span id="otrasinversionessaldos_TAGTipoTransaccion">Tipo Transacci&oacute;n </span><span>:</span>
                <select  id="tipo_transaccion_SalTrans_OI" title="Tipo Transaccion" class="requerido_SalTrans_OI selectFormulario_OI_BT" style="width:220px;"  >

                </select>
            </td>
            <td class="datos"> </td>

            <td class="datos" width="30%">
                <span id="otrasinversionessaldos_TAGIntervaloTransaccion">Transacci&oacute;n por </span><span>:</span>
                <select  id="buscar_SalTrans_OI" title="Buscar" class="requerido_SalTrans_OI selectFormulario_OI_BT" style="width:100px;" onchange="validarActivarFechas_SalTrans_OI(this.value)" >

                </select>
            </td>

            <td  class="botones_formulario" width="20%">
                <input type="button" id="consulta_SalTrans_OI" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>

        <tr id="fechas_SalTrans_OI" style="display: none">
            <%--<td class="datos"></td>--%>
            <td class="datos" colspan="2">
                <span id="otrasinversionessaldos_TAGFechaDesde">Fecha Desde</span> <span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_SalTrans_OI" class="invisible_print inputFormulario_OI_BT requeridoFecha_SalTrans_OI" tabindex="2">
                <span id="fechaDesdeFiltro_SalTrans_OI_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos" colspan="2">
                <span id="otrasinversionessaldos_TAGFechaHasta">Fecha Hasta:</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_SalTrans_OI"  class="invisible_print inputFormulario_OI_BT requeridoFecha_SalTrans_OI" tabindex="2">
                <span id="fechaHastaFiltro_SalTrans_OI_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="div_tabla_consulta_SalTrans_OI" class="div_tabla_consulta"></div>
    <div id="paginacion_tabla_consulta_SalTrans_OI"></div>
</fieldset>
</div>
<div id="div_noInfo_BT_otherInvestment" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="noInfo_otherInvestment">Usted no posee Otras Inversiones que consultar </span>
    </fieldset>
</div>
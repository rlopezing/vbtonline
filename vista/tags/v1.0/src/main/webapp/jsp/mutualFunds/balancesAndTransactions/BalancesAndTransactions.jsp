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
    <h1 id="FM_titulo_BT">Fondos Mutuales / Saldos y Transacciones </h1>
</div>
<div id="div_mutualFunds_BT">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
        <tbody>
        <tr>
            <td class="datos"><span class="Fondo_label_SalTrans_FM" id="fondossaldos_TAGFondoMutual">Fondos Mutuales </span><span>:</span></td>
            <td class="datos">
                <select  id="numero_cuenta_SalTrans_FM" title="Numero de cuenta" class="requerido_SalTrans_FM invisible_print" style="" onchange="cargarDetalleCuentaFondosMutuales(this.value)">
                </select>
                <span id="numero_cuenta_SalTrans_FM_select" class="visible_print"></span>
            </td>

            <td  width='10%'>
                <div id="botonVolverPortafolioFM"> <input type="button" id="btnVolverPortafolioFM" value="Volver" class="botonEDOCuenta oculto"></div>
            </td>
            <td width="5%" class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
                <a id="saldos_imprimir" title="Imprimir" onclick="print_SALDOS_FONDOS();" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <legend>
        <span id="saldo_BT_MutualFunds" style="display: block"></span>
        <span id="saldo_BT_MutualFunds2" style="display: none"></span>
        <span id="saldo_BT_fechaSaldo" style="display: none"></span>
    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" >
                <span class="label_titular_SalTrans_FM" id="fondossaldos_TAGTitularFondo">Titular del Fondo</span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="titular_SalTrans_FM" class="spanFormulario_MF_BT">  </span>
            </td>

            <td class="datos4" >
                <div id="linkBloqueo_mutual"></div>
                <%--<span class="label_unidadesBloqueadas_SalTrans_FM" id="fondossaldos_TAGBloqueado">Unidades Bloqueadas </span><span>:</span>--%>
            </td>
            <td class="datos6" >
                <span id="unidadesBloqueadas_SalTrans_FM" class="spanFormulario_MF_BT">  </span>
            </td>


        </tr>
        <tr>
            <td class="datos4" >
                <span class="label_moneda_SalTrans_FM" id="fondossaldos_TAGMoneda">Moneda </span><span>:</span>
            </td>
            <td class="datos5" >
                <span id="moneda_SalTrans_FM" class="spanFormulario_MF_BT">  </span>
            </td>

            <td class="datos4" >
                <span class="label_unidadDisponible_SalTrans_FM" id="fondossaldos_TAGDisponible">Unidades Disponibles </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadDisponible_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >

            </td>
            <td class="datos5" >

            </td>
            <td class="datos4" >
                <span class="label_unidadesTotales_SalTrans_FM" id="fondossaldos_TAGValorActual">Unidades Totales </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="unidadesTotales_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_VUI_SalTrans_FM" id="fondossaldos_TAGVUI">VUI </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="VUI_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_montoTransito_SalTrans_FM" id="fondossaldos_TAGEnTransito">Monto en Tr&aacute;nsito </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="montoTransito_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" >
            </td>
            <td class="datos5" >
            </td>
            <td class="datos4" >
                <span class="label_totalMoneda_SalTrans_FM" id="fondossaldos_TAGTotalMoneda">Total en Moneda </span><span>:</span>
            </td>
            <td class="datos6" >
                <span id="totalMoneda_SalTrans_FM" class="spanFormulario_MF_BT"> </span>
            </td>
        </tr>

        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <%--<span id="fechaBloqueado_SalTrans_FM"></span>--%>
    <legend id="fondossaldos_TAGTransacciones"> Transacciones</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%;">
        <tr>
            <td class="datos" width="34%">
                <span id="fondossaldos_TAGTipoTransaccion">Tipo Transacci&oacute;n </span><span>:</span>
                <select  id="tipo_transaccion_SalTrans_FM" title="Tipo Transaccion" class="requerido_SalTrans_FM selectFormulario_MF_BT" style="width:220px;"  >

                </select>
            </td>
            <td class="datos"> </td>

            <td class="datos" width="30%">
                <span id="fondossaldos_TAGIntervaloTransaccion">Transacci&oacute;n por </span><span>:</span>
                <select  id="buscar_SalTrans_FM" title="Buscar" class="requerido_SalTrans_FM selectFormulario_MF_BT" style="width:100px;" onchange="validarActivarFechas_SalTrans_FM(this.value)" >

                </select>
            </td>
            <td  class="botones_formulario" width="20%">
                <input type="button" id="consulta_SalTrans_FM" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>

        <tr id="fechas_SalTrans_FM" style="display: none">
            <%--<td class="datos"></td>--%>
            <td class="datos" colspan="2">
                <span id="fondossaldos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_SalTrans_FM" class="invisible_print inputFormulario_MF_BT requeridoFecha_SalTrans_FM" tabindex="2">
                <span id="fechaDesdeFiltro_SalTrans_FM_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos" colspan="2">
                <span id="fondossaldos_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_SalTrans_FM"  class="invisible_print inputFormulario_MF_BT requeridoFecha_SalTrans_FM" tabindex="2">
                <span id="fechaHastaFiltro_SalTrans_FM_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="div_tabla_consulta_SalTrans_FM" class="div_tabla_consulta"></div>
    <div id="paginacion_tabla_consulta_SalTrans_FM" ></div>
</fieldset>
</div>
<div id="div_noInfo_BT_mutualFunds" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="noInfo_mutualFunds">Usted no posee Fondos Mutuales que consultar </span>
    </fieldset>
</div>
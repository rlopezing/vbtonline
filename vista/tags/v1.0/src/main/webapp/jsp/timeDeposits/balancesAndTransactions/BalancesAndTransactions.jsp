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
    <h1 id="tagTitulo_Colocaciones_BT">Colocaciones / Saldos y Transacciones</h1>
</div>
<div id="div_colocacionesSaldosTrans" style="display: none">
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%">
            <tbody>
            <tr>
                <td class="datos" width="15%"><span class="CBT_label_numero_cuenta_TDBT" id="colocacionessaldos_TAGNumeroColocacion">N&uacute;mero de Colocaci&oacute;n </span> <span>:</span></td>
                <td class="datos" width="70%">
                    <select  id="colocaciones_numero_cuenta_BT" title="Numero de cuenta" class="invisible_print" style="width:240px;"  >

                    </select>
                    <span id="colocaciones_numero_cuenta_BT_select" class="visible_print"></span>
                </td>
                <td  width='10%'>
                    <div id="botonVolverPortafolioColocaciones"> <input type="button" id="btnVolverPortafolioColocaciones" value="Volver" class="botonEDOCuenta oculto"></div>
                </td>
                <td width="5%"  class="botones_formulario">
                    <a id="between_imprimir_bloqueos" onclick="print_colocacionesSaldosTrans()" title="imprimir">
                        <img class="imprimir"  src="../vbtonline/resources/images/comun/impresora.gif" border="0" width="18" height="15"/>
                    </a>
                </td>

            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend>
            <span id="tag_saldo_colocaciones" style="display: block">Datos Cuenta</span>
            <span id="tag_saldo_colocaciones2" style="display: none"></span>
            <span id="tag_saldo_colocacionesFecha" style="display: none"></span>
        </legend>
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
            <tbody>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_titular" id="colocacionessaldos_TAGTitularColocacion">Titular Colocaci&oacute;n </span><span>:</span>
                </td>
                <td class="datos5" >
                    <b><span id="titular_BT_Colocaciones">  </span>   </b>
                </td>

                <td class="datos4" >
                    <span class="CBT_label_montoApertura" id="colocacionessaldos_TAGMontoApertura">Monto Apertura </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="montoApertura_BT_Colocaciones">  </span>
                </td>


            </tr>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_moneda" id="colocacionessaldos_TAGMoneda">Moneda </span><span>:</span>
                </td>
                <td class="datos5" >
                   <b><span id="moneda_BT_Colocaciones">  </span></b>
                </td>

                <td class="datos4" >
                    <div id="linkBloqueo"></div>
                </td>
                <td class="datos6" >
                    <span id="montoBloqueado_BT_Colocaciones"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_fechaApertura" id="colocacionessaldos_TAGFechaApertura">Fecha Apertura </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="fechaApertura_BT_Colocaciones"> </span>
                </td>
                <td class="datos4" >
                    <span class="CBT_label_montoVencimiento" id="colocacionessaldos_TAGMontoVencimiento">Monto al Vencimiento </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="montoVencimiento_BT_Colocaciones"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos4" >
                    <span class="CBT_label_fechaVencimiento" id="colocacionessaldos_TAGFechaVencimiento">Fecha Vencimiento </span><span>:</span>
                </td>
                <td class="datos5" >
                    <span id="fechaVencimiento_BT_Colocaciones"> </span>
                </td>
                <td class="datos4" >
                    <span class="CBT_label_tasa" id="colocacionessaldos_TAGTasa">Tasa </span><span>:</span>
                </td>
                <td class="datos6" >
                    <span id="tasa_BT_Colocaciones"> </span>
                </td>
            </tr>

            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_BT_Colocaciones" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>
<div id="noPoseeColocacionesSaldosTrans" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>

<%--<div id="div_mensajes_info_desc_tabla_TDBT" class="info_descp_tabla oculto">--%>
    <%--<div id="cerrar_div_mensajes_info_desc_tabla_TDBT"><img width="24px" height="24px" src="../vbtonline/resources/images/close.png"></div>--%>
    <%--<div id="mensajes_info_desc_tabla_TDBT">--%>
        <%--<table width="100%">--%>
            <%--<tr>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficiario">Beneficiary </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Beneficiary_TDBT"></span></td>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentassaldos_TAGCuentaNumero">Account </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Account_TDBT"></span></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBanco">Bank </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Bank_TDBT"></span></td>--%>
                <%--<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Currency </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Currency_TDBT"></span></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td width="25%"><span class="label_descp" id="cuentasbloqueos_TAGObservacion">Observation </span><span class="label_descp">:</span></td>--%>
                <%--<td width="25%"><span id="label_Observation_TDBT"></span></td>--%>
                <%--<td width="25%"></td>--%>
                <%--<td width="25%"></td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</div>--%>

<%--</div>--%>

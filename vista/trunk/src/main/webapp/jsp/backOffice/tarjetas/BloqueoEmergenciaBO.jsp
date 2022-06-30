<%--
  Created by IntelliJ IDEA.
  User: Rafael Nuñez
  Date: 19/07/21
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="TDC_titulo_bloquear_emergencia1">Tarjetas de Cr&eacute;dito / Bloqueo</h1>
</div>
<%--style="display: none"--%>
<div id="div_bc_creditCard_CLE" >
    <fieldset class="invisible_print div_info" style="width:99%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left" style="text-align:justify;">
                    <span id="TAG_EMERGENCY_CREDIT_CARDS_2" class="datosInfo">Esta opción le permite bloquear la tarjeta en caso de robo o extravío.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <legend>Bloquear Tarjetas</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td class="datos"><span class="label_numero_cuenta_TDC_CL">Nº Cartera</span><span>:</span>

                    <input type="text" id="carteraFiltroEmergencia" title="N° Cartera" class="inputFormulario"  maxlength="16" >
                    <%--                    <input type="button"  value="Buscar" class="">--%>
                    <img width="19" height="19" style="margin-left: 5px;" id="buscarBloqueoEmergenciaBO" src="resources/images/search.png" alt="">
                </td>
                <td class="datos" width="12%">

                </td>
                <td class="datos" align="left">
                    <input type="button" id="limpiarBloqueoEmergenciaBO" value="Limpiar" class="botonGrandeDerecha" style="margin-right:4px">
                </td>
            </tr>
            <tr>
                <td class="datos">
                    <input type="checkbox" value="1" class="datos checkFormulario" id="carteraExacto_Emergencia" style="width: 20px"><label class="datos"> Buscar por cartera exacta?</label>
                </td>
                <td class="datos" width="12%">
                    &nbsp;
                </td>
                <td class="datos" align="left">
                    &nbsp;
                </td>
            </tr>
        </table>
        <br>
        <div id="div_tarjetasInfo" class="oculto">
            <table SUMMARY='tabla' cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td class="datos" width="15%"><span class="label_numero_cuenta_TDC_CLE_BO" id="TAGNumeroTarjeta_Pago4">N&uacute;mero de Tarjeta</span><span>:</span></td>
                    <td class="datos" width="15%">
                        <select  id="numero_cuenta_TDC_CLE_BO" title="Numero de Tarjeta" style="width:320px;margin-right:4px" class="chosen-select requerido_TDC_CLE invisible_print" onchange="cargarEstatusBO(this.value)">
                        </select>
                        <span id="numero_cuenta_TDC_CLE_BO_select" class="visible_print"></span>
                    </td>
                    <td class="datos" align="left">
                        <div id="div_datos_TDC_CLE_estatus" class="oculto">
                            <span>La tarjeta se encuentra bloqueada</span>
                        </div>
                        <div id="div_datos_TDC_CLE">
                            <table width="100%">
                                <tr>
                                    <td width="70%">
                                        <div class="oculto" id="motivosCLEBO">
                                            <span class="label_motivosBloqueo" id="TAGMotivosBloqueo_1">Motivos Bloqueo</span><span>:</span>
                                            <select  id="motivosBloqueoBO" title="Motivos Bloqueo" class="chosen-select requerido_TDC_BO invisible_print" style="width:200px;margin-right:4px" >
                                            </select>
                                        </div>
                                    </td>
                                    <td width="30%" align="right">
                                        <div class="oculto" style="margin-right: -3px;" id="btnBloqueoBO" >
                                            <input type="button" id="crear_bloqueo_BO" value="Bloquear" class="0000000103_2 botonGrandeDerecha">
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td colspan="2"><span id="tag_cliente_bloqueo_clebo" class="datos"></span></td>
                </tr>
                </tbody>
            </table>
        </div>
    </fieldset>

</div>

<div id="div_InfoBloqueo_bc_tdc" class="oculto">
    <fieldset class="div_consulta" >
        <legend>Tarjetas bloqueadas</legend>
        <div id="div_InfoBloqueo_bc_table">
        </div>
    </fieldset>
</div>


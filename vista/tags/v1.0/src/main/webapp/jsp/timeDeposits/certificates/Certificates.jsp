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
    <h1 id="colocaciones_titulo_C">Colocaciones / Certificados </h1>
</div>
<div id="div_certificados_colocaciones">
<fieldset class="div_consulta">
    <legend>
        <span id="comun2_CERTIFICADOS" style="display: block">Certificados</span>
        <span id="comun2_CERTIFICADOS_al" style="display: none">Certificados al: </span>
        <span id="comun2_CERTIFICADOS_fechaCierre"></span>

    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr>
            <td class="datos"> <span id="colocacionescertificados_TAGBuscar">Buscar </span></td>

            <td class="datos"></td>
        </tr>
        <tr>

            <td class="datos">
                <select  id="buscar_ColocacionesCertificados" title="Buscar" class="requerido_ColCer" style="width:240px;" onchange="validarActivarFechas_ColCer(this.value)" ></select>
            </td>
            <td  class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
            </td>
            <td  class="botones_formulario" width="10%">
                <input type="button" id="consultar_ColCer" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>
        <tr id="fechas_ColCer" style="display: none">
            <td class="datos">
                <span id="colocacionescertificados_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_ColCer" class="inputFormulario requeridoFecha_ColCer" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="colocacionescertificados_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_ColCer"  class="inputFormulario requeridoFecha_ColCer" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="certificados_ColCer_div_tabla_consulta" class="div_tabla_consulta">
    </div>
</fieldset>


<div id="div_mensajes_info_desc_tabla_TD_C" class="info_descp_tabla oculto">
    <div id="cerrar_div_mensajes_info_desc_tabla_TD_C"><img width="24px" height="24px" src="../vbtonline/resources/images/close.png"></div>
    <div id="mensajes_info_desc_tabla_TD_C">
        <table width="100%">
            <tr>
                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGCarteraNumero">Cartera N </span><span class="label_descp">:</span></td>
                <td width="25%"><span id="label_cartera_TD_C"></span></td>
                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGMontoVencimiento">Monto </span><span class="label_descp">:</span></td>
                <td width="25%"><span id="label_Monto_TD_C"></span></td>
            </tr>
            <tr>
                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGTasa">Tasa </span><span class="label_descp">:</span></td>
                <td width="25%"><span id="label_tasa_TD_C"></span></td>
                <td width="25%"> </td>
                <td width="25%"></td>
            </tr>
        </table>
    </div>

</div>
</div>
<div id="div_noPoseeColocacionesCertificados" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones3">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>

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
    <h1>Consulta Pagos TDC</h1>
</div>

<div id="div_bloqueoTarjetas_BO">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="datos" width="6%"><span class="label_numero_cuenta_TDC_CL">Nº Cartera</span><span>:</span></td>
            <td class="datos">
                 <input type="text" id="carteraFiltroTarjetaPagossBO" title="N° Cartera" class="inputFormulario"  maxlength="16" >
				 <input type="checkbox" value="1" class="datos checkFormulario" id="carteraExacto_TDC_Pagos" style="width: 20px"><label class="datos"> Buscar por cartera exacta?</label>
            </td>
            <td class="datos" width="12%">

            </td>
            <td class="datos" align="left">
               <input type="button" id="buscarPagosTarjetaBO" value="Buscar" class="botonGrandeDerecha"> &nbsp;
               <input type="button" id="limpiarPagosTarjetaBO" value="Limpiar" class="botonGrandeDerecha" style="margin-right:4px">
            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta oculto"  id="div_result_pagos_bo">
    <div>
       <table width="100%">
           <tr>
               <td class="datos" width="55%">
                   <select  id="numero_cuenta_TDC_PAGOS_BO" title="Numero de Tarjeta" style="width:320px; margin-right:4px" class="chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla" onchange="buscarPagosTDC(this.value)">
                   </select>
                   <span id="tag_clientepagoso_bo" style="padding-right: 20px;"></span>
               </td>
               <td class="datos" width="25%">

               </td>
               <td width="20%">

               </td>
           </tr>
           <tr>
               <td colspan="3" class="datos">
                   <span style="padding-right: 20px;">Desde:</span><input title="Fecha desde" type="text" id="fec_desde_Pagos_bo" class="requeridoCrearRegla"/>
                   <span style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" title="Fecha hasta" id="fec_hasta_Pagos_bo" class="requeridoCrearRegla"/>

               </td>
           </tr>
       </table>



    </div>
</fieldset>
    <fieldset id="set_tabla_consulta_pagos_TDC_BO" class="div_consulta oculto">
        <legend>Historial Pagos de Tarjetas</legend>
        <div id="div_tabla_consulta_pagos_TDC_BO" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>

<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->

<div id="div_bitacora" class="div_tabla_consulta">

    <div>
        <h1 id="Titulo_Bitacora"> Bitacora </h1>
    </div>
<fieldset class="div_consulta">
    <legend>Motor de b&uacute;squeda</legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="datos2" id="tagBitacora_UsuFiltro">Usuario:</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_usuarioFiltro" title="Usuario" style="width: 200px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>

            <td class="datos2" id="TagBitacora_accion" title="Acción">Acci&oacute;n:</td>
            <td class="datos">
                <select  id="bitacora_accion" title="Accion" class="selectFormulario_bitacora" style="width:200px;"  >

                </select>
            </td>
            <td class="datos2" id="TagBitacora_objeto" >Objeto Afectado:</td>
            <td class="datos">
                <select  id="bitacora_objeto" title="Objeto Afectado" class="selectFormulario_bitacora" style="width:200px;"  >

                </select>
            </td>
        </tr>
        <tr>
            <td class="datos2"><span id="bitacora_TAGFechaDesde">Desde</span><span>:</span></td>
            <td class="datos">

                <%--esto debe ser un calendario que permita seleccionar la fecha--%>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltroBitacora" class="inputFormulario_bitacora fechadias" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos2">
                <span id="bitacora_TAGFechaHasta">Hasta</span><span>:</span>
            </td>
            <td class="datos">

                <%--esto debe ser un calendario que permita seleccionar la fecha--%>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltroBitacora"  class="inputFormulario_bitacora fechadias" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos2" id="tagBitacora_Ultimos">&Uacute;ltimos:</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_dias" title="Ultimos" style="width: 100px;"  maxlength="16" class="inputFormulario_bitacora fechadias" >
            </td>
        </tr>
        <tr>

            <td class="datos2" id="tagBitacora_idobjeto">
                    Id del Objeto:
            </td>
            <td class="datos">
                <input type="TEXT" id="bitacora_idobjeto" title="Id del Objeto" style="width: 100px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>
            <td class="datos2">IP</td>
            <td class="datos" >
                <input type="TEXT" id="bitacora_ip" title="Ultimos" style="width: 100px;"  maxlength="16" class="inputFormulario_bitacora" >
            </td>

        </tr>

        <tr>
            <td colspan="6" class="botones_formulario">
                <input type="button" id="bitacora_search" value="Buscar" class="botonEDOCuenta">
                <input type="button" id="bitacora_reset" value="Limpiar" class="botonEDOCuenta">
            </td>
        </tr>
     </table>

</fieldset>
<fieldset class="div_consulta">

    <div id="div_tabla_bitacora" class="div_tabla_bitacora">

    </div>
</fieldset>
</div>

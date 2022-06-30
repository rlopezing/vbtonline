<div id="div_inactivaraBancos" class="div_tabla_consulta">

    <div>
        <h1 id="Titulo_InactivarBancos"> Inactivar Bancos </h1>
    </div>
<fieldset class="div_consulta">
    <legend>Buscar Código de Banco</legend>
    <div id="inactivarBancosBO">
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0" width="100%" border="0">
            <tr>
                <td class="datos2"><label class="datos">Tipo Código Banco:</label></td>
                <td><select id="BankCodeType_buscarBO" class="selectFormulario" title="Tipo Código Banco" > </select></td>
                <td  class="datos2"><label class="datos">Código Banco:</label></td>
                <td><input id="BankCode_buscarBO" type="text" class="obligatorioBuscar inputFormulario"  title="Código Banco"/></td>
                <td width="40%"></td>
            </tr>
            <tr>
                <td class="datos2"><label class="datos">Nombre Banco</label></td>
                <td><input id="BankName_buscarBO" style="width:250px" type="text" class="inputFormulario"  title="Código Banco"/></td>
                <td  class="datos2"><label class="datos">Estatus:</label></td>
                <td><select id="estatus_buscarBO" class="selectFormulario" title="Estatus" > </select></td>
                <td width="40%"></td>
            </tr>
        </table>

        <div id="actions">
            <input type='button' id="limpiarInactivarBancos" href="#" value="Limpiar" class="boton"/>
            <input type='button' id="buscarInactivarBancos" href="#" value="Buscar" class="boton"/>
        </div>
    </div>

</fieldset>
<fieldset class="div_consulta">

    <div id="div_tabla_bancos_bo" class="div_tabla_bitacora">
    </div>
    <div id="paginacion_tabla_consulta_bancos_bo" class="result"></div>
</fieldset>
</div>


<div id="sign_up_sucursales" class="invisible_print">
    <h3>Sucursales</h3>
    <div id="div_carga_espera_sign_up" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='30px' alt="" /></div>
    <div id="buscarBanco_div_tabla_consulta_sucursales" class="result"></div>
    <div id="paginacion_buscarBanco_consulta_sucursales" class="result"></div>
    <a id="close_x_sucursales" class="close sprited" href="#">close</a>

    <div id="div_mensajes_info_desc" class="info_descp oculto">
        <div id="mensajes_info_desc">
            error
        </div>
        <div id="cerrar_div_mensajes_info_desc">[X]</div>
    </div>
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="DB_titulo_PP_FC">Par&aacute;metros Personales Firmas Conjuntas </h1>
</div>
<div id="div_parametrosPersonales_FC">

    <fieldset class=" div_consultaTransfers">
        <legend id="tagHaciaOtrosBancos_FC" >Par&aacute;metros de Transferencia Hacia Otros Bancos</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PP_numeroMaxTransDiarias_OB_FC" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    <input id="PP_numeroMaxTransDiarias_OB_FC" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PP_FC requerido_PP_FC" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransDiarias_OB_FC" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>
                   <input id="PP_montoMaxTransDiarias_OB_FC" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PP_FC requerido_PP_FC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransOpe_OB_FC" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <input id="PP_montoMaxTransOpe_OB_FC" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PP_FC requerido_PP_FC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_envioCorreo_FC" class="datos">Correos de Notificación </span><span class="datos">:</span>
                </td>
                <td>
                    <input name="PP_envioCorreo_FC" type="radio" title="Correos de Notificación" style="width: 20px" checked="checked" id="radioSi" value="SI"/><span class="datos" id="tagSi_PP">Si</span>
                    <input name="PP_envioCorreo_FC" type="radio" title="Correos de Notificación" style="width: 20px" id="radioNo" value="NO"/><span class="datos" id="tagNo_PP">No</span>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">

        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <input  type="button" id="btn_PP_aceptar_FC" value="Aceptar" class="boton">
                    <input  type="button" id="btn_PP_cancelar_FC" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>

</div>
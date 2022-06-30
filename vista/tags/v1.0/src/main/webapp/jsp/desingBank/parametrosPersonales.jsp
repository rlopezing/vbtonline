<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="DB_titulo_PP">Dise&ntilde;e su Banco / Par&aacute;metros Personales </h1>
</div>
<div id="div_parametrosPersonales">

    <fieldset class=" div_consultaTransfers">
        <legend id="tagCuentaPredeterminada" >Cuenta Predeterminada para operaciones de Transferencia</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="60%">
                    <select id="Accounts_PP" title="Account" class="selectFormulario_PP requerido_PP" >

                    </select>

                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset  class=" div_consultaTransfers">
        <legend id="tagDentroVBT" >Par&aacute;metros de Transferencia dentro de VBT</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PP_numeroMaxTransDiarias" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                 </td>
                <td>
                    <%--<select id="numeroMaxTransDiarias_PP" title="Número máximo de Transferencias Diarias" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="numeroMaximoTransfDiarias_PP" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>

            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransDiarias" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="montoMaxTransDiarias_PP" title="Monto máximo de Transferencias Diarias" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="montoMaxTransDiarias_PP" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px"  onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMinTransOpe" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="montoMinTransOpe_PP" title="Monto mínimo de Transferencias por Operación" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="montoMinTransOpe_PP" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransOpe" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="montoMinTransDiarias_PP" title="Monto maximo de Transferencia por Operación" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="montoMinTransDiarias_PP" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class=" div_consultaTransfers">
        <legend id="tagHaciaOtrosBancos" >Hacia Otros Bancos</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PP_numeroMaxTransDiarias_OB" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="PP_numeroMaxTransDiarias_OB" title="Número máximo de Transferencias Diarias" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="PP_numeroMaxTransDiarias_OB" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransDiarias_OB" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="PP_montoMaxTransDiarias_OB" title="Monto máximo de Transferencias Diarias" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="PP_montoMaxTransDiarias_OB" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMinTransOpe_OB" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="PP_montoMinTransOpe_OB" title="Monto mínimo de Transferencias por Operación" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="PP_montoMinTransOpe_OB" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PP_montoMaxTransOpe_OB" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>
                    <%--<select id="PP_montoMinTransDiarias_OB" title="Monto maximo de Transferencia por Operación" class="selectFormulario_PP" >--%>

                    <%--</select>--%>
                        <input id="PP_montoMaxTransOpe_OB" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PP requerido_PP" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">

        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td align="right">
                    <input  type="button" id="btn_PP_aceptar" value="Aceptar" class="boton 0000000045_2">
                    <input  type="button" id="btn_PP_cancelar" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>

</div>
<div id="metodosParametros" class=" oculto" >
    <fieldset  class=" div_consultaTransfers">
        <div id="metodosValidacionParametros">

        </div>
    </fieldset>
    <fieldset  class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <input  type="button" id="btn_PP_validar_clave" value="Aceptar" class="boton">
                    <input  type="button" id="btn_PP_cancelar_clave" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>
</div>
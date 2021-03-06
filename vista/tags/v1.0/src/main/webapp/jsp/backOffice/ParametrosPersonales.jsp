<div>
    <h1 id="DB_titulo_PPA">Dise&ntilde;e su Banco / Par&aacute;metros Personales </h1>
</div>
<div id="div_parametrosPersonalesA">

    <fieldset class=" div_consultaTransfers">
        <legend id="tagCuentaPredeterminada" >Buscar usaurio a modificar</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="60%">
                    ID usuario <input id="usuarioAmodificar_PPA" type="text" title="Usuario a Modificar" class="inputFormulario_PPA requerido_PPA" style="width: 400px" />
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class=" div_consultaTransfers">
        <legend id="tagDentroVBT" >Par&aacute;metros de Transferencia dentro de VBT</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PP_numeroMaxTransDiarias" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="numeroMaximoTransfDiarias_PPA" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>

            </tr>
            <tr>
                <td>
                    <span id="tag_PPA_montoMaxTransDiarias" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="montoMaxTransDiarias_PPA" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PPA requerido_PPA" style="width: 100px"  onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PPA_montoMinTransOpe" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="montoMinTransOpe_PPA" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PPA_montoMaxTransOpe" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="montoMinTransDiarias_PPA" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class=" div_consultaTransfers">
        <legend id="tagHaciaOtrosBancos" >Hacia Otros Bancos</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PPA_numeroMaxTransDiarias_OB" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PP_numeroMaxTransDiarias_OB" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PPA_montoMaxTransDiarias_OB" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PP_montoMaxTransDiarias_OB" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PPA_montoMinTransOpe_OB" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PP_montoMinTransOpe_OB" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PPA_montoMaxTransOpe_OB" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PP_montoMinTransDiarias_OB" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PPA requerido_PPA" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">

        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <input  type="button" id="btn_PPA_aceptar" value="Aceptar" class="boton">
                    <input  type="button" id="btn_PPA_volver" value="Regresar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>

</div>
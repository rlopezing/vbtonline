<div>
    <h1 id="DB_titulo_PG">Par&aacute;metros Globales VBT </h1>
</div>

<div id="div_parametrosEmpresa">
    <fieldset  class="formulario_fieldset div_consulta">

        <legend id="tagTipoParametro" >Par&aacute;metros</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td width="77%">
                    <select id="tipoParametroGlobal" title="Tipo Parametro" class="obligatorioTOC selectFormulario_TOCVBT" onchange="cargarParametrosGlobalesBC();">
                        <%--<option value="GC" selected="selected">Globales Clientes</option>--%>
                        <%--<option value="GFC">Globales Firmas Conjuntas</option>--%>
                    </select>
                </td>
            </tr>


        </table>

    </fieldset>
    <fieldset class=" div_consultaTransfers">
        <legend id="tagDentroVBT" >Par&aacute;metros de Transferencia dentro de VBT</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PG_numeroMaxTransDiarias" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="numeroMaximoTransfDiarias_PG" type="text" title="Numero maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>

            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransDiarias" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="montoMaxTransDiarias_PG" type="text" title="Monto maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px"  onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMinTransOpe" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="montoMinTransOpe_PG" type="text" title="Monto minimo de Transferencias por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransOpe" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="montoMinTransDiarias_PG" type="text" title="Monto maximo de Transferencia por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class=" div_consultaTransfers">
        <legend id="tagHaciaOtrosBancos" >Hacia Otros Bancos</legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <span id="tag_PG_numeroMaxTransDiarias_OB" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_numeroMaxTransDiarias_OB" type="text" title="Numero maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransDiarias_OB" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_montoMaxTransDiarias_OB" type="text" title="Monto maximo de Transferencias Diarias" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMinTransOpe_OB" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_montoMinTransOpe_OB" type="text" title="Monto minimo de Transferencias por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
            <tr>
                <td>
                    <span id="tag_PG_montoMaxTransOpe_OB" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                </td>
                <td>

                    <input id="PG_montoMaxTransOpe_OB" type="text" title="Monto maximo de Transferencia por Operacion" class="inputFormulario_PG requerido_PG" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                </td>
            </tr>
        </table>
    </fieldset>
    <fieldset class=" div_consultaTransfers">

        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
            <tr>
                <td>
                    <input  type="button" id="btn_PG_aceptar" value="Aceptar" class="boton 0000000071_2">
                    <input  type="button" id="btn_PG_cancelar" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>

</div>
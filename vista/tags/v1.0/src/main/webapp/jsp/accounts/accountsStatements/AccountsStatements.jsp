<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="tagTitulo_Account_AE">Cuentas / Estados de Cuenta</h1>
</div>
<div id="div_accountStatement_account">
<fieldset class="div_consulta">
    <legend><span class="label_estado_cuenta" id="cuentasedocuenta_TAGEstadoCuenta">Estado de Cuenta</span></legend>
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%" align="center">
        <tbody>
        <tr>
            <td class="datos" width="12%"><span class="label_numero_cuenta" id="cuentasedocuenta_TAGNumeroCuenta">N&uacute;mero de cuenta </span><span>:</span></td>
            <td class="datos" width="25%">
                <select  id="estado_cuenta_numero_cuenta" title="Numero de cuenta" class="selectFormulario_Accounts_AE requerido_account_AE" style="width:250px;" onchange="limpiarTabla_AE(); consultarEdoCuentaOnclick();"  >

                </select>
            </td>
            <td class="datos" width="3%"><span class="label_fecha_mes" id="cuentasedocuenta_TAGMes">Mes </span><span>:</span></td>
            <td class="datos" width="12%">
                <select  id="estado_cuenta_fecha_mes" title="Mes de consulta" class="selectFormulario_Accounts_AE requerido_account_AE" style="width:105px;" onchange="limpiarTabla_AE(); consultarEdoCuentaOnclick();" >

                </select>
            </td>
            <td class="datos" width="3%"><span class="label_fecha_anio" id="cuentasedocuenta_TAGAnio">A&ntilde;o </span><span>:</span></td>
            <td class="datos" width="12%">
                <input type="text"  id="estado_cuenta_fecha_anio" title="Año de consulta" class="inputFormulario_Accounts_AE requerido_account_AE" maxlength="4" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);" />

            </td>

            <td  class="botones_formulario" width="12%">
                <input type="button"  id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">
            </td>
            <td  width="3%" class="botones_formulario">
                <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>
               <a id="account_imprimir" onclick="crearPDF_accountStatement_account()" TITLE="Imprimir">
               <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <%--<legend id="tagAccount_DatosCuenta_AE"> Datos Cuenta </legend>--%>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tbody>
        <tr>
            <td class="datos4" width="50%">
                <span class="label_titular" id="cuentasedocuenta_TAGTitularCuenta">Titular </span><span>:</span>
            </td>
            <td class="datos5" width="40%">
                <span id="titular_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
            <td class="datos4" width="50%">
                <span class="label_bloqueado" id="cuentasedocuenta_TAGBloqueado">Bloqueado </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="bloqueado_AS" class="spanFormulario_Accounts_AE"> </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">
                <span class="label_moneda" id="cuentasedocuenta_TAGMoneda">Moneda </span><span>:</span>
            </td>
            <td class="datos5" width="40%">
                <span id="moneda_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
            <td class="datos4" width="50%">
                <span class="label_diferido" id="cuentasedocuenta_TAGDiferido">Diferido </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="diferido_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">
            </td>
            <td class="datos5" width="40%">
            </td>
            <td class="datos4" width="50%">
                <span class="label_disponible" id="cuentasedocuenta_TAGDisponible">Disponible </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="disponible_AS" class="spanFormulario_Accounts_AE">  </span>
            </td>
        </tr>
        <tr>
            <td class="datos4" width="50%">
                <%--<input id="estado_cuenta_imprime" class="boton" value="imprimir">--%>
            </td>
            <td class="datos5" width="40%">
            </td>
            <td class="datos4" width="50%">
                <span class="label_bloqueado" id="cuentasedocuenta_TAGSaldoActual">Saldo Actual </span><span>:</span>
            </td>
            <td class="datos6" width="5%">
                <span id="saldo_actual_AS" class="spanFormulario_Accounts_AE"> </span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="estado_cuenta_div_tabla_consulta" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_noInfo_accountStatement_account" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_account2">No posee cuentas que consultar </span>
    </fieldset>
</div>
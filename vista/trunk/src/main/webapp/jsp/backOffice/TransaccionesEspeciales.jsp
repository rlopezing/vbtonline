<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  User: bmundarain
  Date: 25/08/16
  Time: 09:11 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="div_tabla_consultaTransaccionEspecial" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_consultarTransaccionesEspeciales">Transacciones Especiales</h1>
    </div>
    <fieldset class="div_consulta 0000000097_1">
        <input type="button" id="CTE_Eliminar" value="Eliminar" class="botonBackoffice">
        <input type="button" id="agregarTransaccionEspecial" value="Agregar Transaccion" class="botonBackoffice">
    </fieldset>

    <fieldset class="div_consulta">
        <legend > Consultar Transacciones </legend>
        <div id="div_tabla_consultarTransaccionesEspeciales" class="div_tabla_consultarUsuarios">

        </div>
        <div id="paginacion_tabla_consultarTransaccionesEspeciales" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>

<div id="div_tabla_agregarTransaccionEspecial" style="display: none">
    <div>
        <h1 id="Titulo_agregarTransaccionEspecial_backoffice">Agregar Transacci&oacute;n Especial</h1>
    </div>
    <fieldset id="ATE_botones" class="formulario_fieldset div_consulta">
        <input type="button" id="CTE_InsertarTransaccionEspecial" value="Agregar" class="boton" >
        <input type="button" id="CTE_Resetear" value="Limpiar" class="boton" >
        <input type="button" id="CTE_Cancelar" value="<< Volver" class="botonDerecha" >
    </fieldset>
    <fieldset id="formularioAgregarTransaccionEspecial" class="formulario_fieldset div_consulta">

        <legend>Datos de la Cuenta</legend>
        <table SUMMARY='tabla'cellpadding="0" cellspacing="0"  border="0" width="80%" align="center">

            <tr>
                <td>
                    <label for="ATE_tipoMov" id="tagATE_tipoMov"  class="datos">Tipo de Movimiento:</label>
                </td>
                <td>
                    <input id="ATE_tipoMov" type="text" class="inputFormulario" title="Tipo de Movimiento" style="width:195px" maxlength="3" size="10"/><label class="datos"> * Ej: TCB</label>
                </td>
                <td>
                    <label for="ATE_direccion" id="tagATE_direccion"  class="datos">Dirección:</label>
                </td>
                <td>
                    <input id="ATE_direccion" type="text" class="inputFormulario" title="Dirección" style="width:210px" maxlength="150" size="20"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ATE_codigoBanco" id="tagATE_codigoBanco"  class="datos">Código Banco:</label>
                </td>
                <td>
                    <input id="ATE_codigoBanco" type="text" class="inputFormulario" title="Código Banco" style="width:195px" maxlength="10" size="17"/>
                </td>
                <td>
                    <label for="ATE_ciudad" id="tagATE_ciudad"  class="datos">Ciudad:</label>
                </td>
                <td>
                    <input id="ATE_ciudad" type="text" class="inputFormulario" title="Ciudad" style="width:195px" maxlength="50" size="17"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ATE_codigoSwift" id="tagATE_codigoSwift"  class="datos">Código Swift:</label>
                </td>
                <td>
                    <input id="ATE_codigoSwift" type="text" class="inputFormulario" title="Código Swift" style="width:195px" maxlength="10" size="17"/>
                </td>
                <td>
                    <label for="ATE_pais" id="tagATE_pais"  class="datos">País:</label>
                </td>
                <td>
                    <input id="ATE_pais" type="text" class="inputFormulario" title="Pais" style="width:195px" maxlength="50" size="17"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ATE_numeroCuenta"  id="tagATE_numeroCuenta" class="datos">Nro Cuenta:</label>
                </td>
                <td>
                    <input id="ATE_numeroCuenta" type="text" class="inputFormulario" title="Numero de cuenta" style="width:195px" maxlength="55" size="35"/>
                </td>
                <td>
                    <label for="ATE_codigoPostal" id="tagATE_codigoPostal"  class="datos">Código Postal:</label>
                </td>
                <td>
                    <input id="ATE_codigoPostal" type="text" class="inputFormulario" title="Pais" style="width:195px" maxlength="50" size="17"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="ATE_mostrarNroCuenta" id="tagATE_mostrarNroCuenta"  class="datos">Mostrar Número de Cuenta?</label>
                </td>
                <td>
                    <select id="ATE_mostrarNroCuenta" title="Mostrar Número de Cuenta?">
                        <option value="1">Sí</option>
                        <option value="0">No</option>
                    </select>
                </td>
            </tr>


        </table>
    </fieldset>
</div>
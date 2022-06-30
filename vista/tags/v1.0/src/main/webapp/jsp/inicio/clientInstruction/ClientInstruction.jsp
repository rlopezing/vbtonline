<%--
  Created by IntelliJ IDEA.
  User: Mary Bernot
  Date: 19/09/13
  Time: 02:41 PM
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="ClientInstruction_TAGTitle">Generar carta de instrucción</h1>
</div>
<div id="div_clientInstruction">
    <fieldset class="div_consulta">
        <legend><span class="label_client_instruction" id="ClientInstruction_TAGClientInstruction">Carta de Instrucción</span></legend>
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="98%" align="center">
            <tbody>
            <tr>
                <input  type="hidden"  id="ci_codperclicarta"  >
                <input  type="hidden"  id="ci_nm_transfer_type"  >
                <td class="datos" ><span class="label_ci_client" id="ClientInstruction_TAGClient">Cliente </span><span>:</span></td>
                <td class="datos" >

                    <select  id="ci_client" title="Cliente" class="selectFormulario_Client_Instruction requerido_Client_Instruction" style="width:250px;" onchange=""  >

                    </select>
                </td>
                <td class="datos"><span class="label_ci_id" id="ClientInstruction_TAGId">Id </span><span>:</span></td>
                <td class="datos">
                    <input  type="text"  id="ci_id" title="Id" class="inputFormulario_Client_Instruction requerido_Client_Instruction" readonly="readonly"  >


                </td>
                <td class="datos"></td>
                <td class="datos">

                </td>


                <td  width="3%" class="botones_formulario">
                    <%--<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">--%>

                </td>
            </tr>
            <tr>
                <td class="datos" ><span class="label_transfer_type" id="ClientInstruction_TAGTransferType">Tipo de Transferencia </span><span>:</span></td>
                <td class="datos" >
                    <select  id="ci_transfer_type" title="Tipo de Transferencia" class="selectFormulario_Client_Instruction requerido_Client_Instruction"  onkeypress="" />

                </td>

                <td class="datos" ><span class="label_ci_expiration" id="ClientInstruction_TAGExpiration">Vencimiento </span><span>:</span></td>
                <td class="datos" >
                    <select  id="ci_expiration" title="Vencimiento" class="selectFormulario_Client_Instruction requerido_Client_Instruction" onchange=""  >

                    </select>
                </td>
                <td class="datos" ><span class="label_ci_quantity" id="ClientInstruction_TAGQuantity">Cantidad </span><span>:</span></td>
                <td class="datos" >
                    <select  id="ci_quantity" title="Id" class="selectFormulario_Client_Instruction requerido_Client_Instruction" style="width:105px;" onchange="" >

                    </select>
                </td>

                <td  class="botones_formulario">
                    <input type="button" id="ClientInstruction_BTNConsultar" value="Consultar" class="botonEDOCuenta">

                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

</div>

<%--
  Created by IntelliJ IDEA.
  User: Bell Mundarain
  Date: 18/08/2016
  Time: 14:25
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="titulo_liberarPagosTdc">Credit Card / Release Payments </h1>
</div>
<fieldset style="width:97%; margin-left:1%;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left" style="text-align:justify;">
                    <span id="TAG_INFO_LIBERAR_PAGOSTDC_FC" class="datosInfo">
                        Esta opción le permite realizar el pago de sus tarjetas de crédito.
                    <br>Nota importante:<br>
                        <li>Los pagos realizados en día feriado bancario o fin de semana, y las transacciones realizadas después de las 3:00 pm (UTC/GMT -5) serán procesados el siguiente día hábil bancario.</li>
                        <li>Para conocer el costo de sus operaciones en el VBT Online, por favor referirse al Schedule of Fees ubicado en www.vbtbank.com</li>
                    </span>
            </td>
            <%--<td align="left">--%>
                <%--<span class="datosInfo" id="TAG_INFO_LIBERAR_PAGOSTDC_FC">This option allows you to release payments.</span>--%>
            <%--</td>--%>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_tabla_consultaPagosTDCPorLiberar" class="div_tabla_consulta">
    <fieldset class="div_consulta 0000000096_7">

        <input type="button" id="btn_rechazarLiberacionPagosTDC" value="Rechazar" class="boton">
        <input type="button" id="btn_liberarPagosTDC" value="Liberar" class="boton ">

    </fieldset>
    <fieldset class="div_consulta">
        <legend id="tag_pagosTdcLiberar">Payments pending of release </legend>
        <label id="tag_seleccionePagosTdcLiberar">Select the payments you want to release </label>
        <div id="div_tabla_PagosTDCPorLiberar" class="div_tabla_pagosTDCPorLiberar">
        </div>

    </fieldset>
</div>
<div id="clave_liberarPagosTDC" style="display: none">
    <fieldset id="form_12" class=" div_consultaPagos"  style="display: none" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="1" align="center">
            <tr>
                <td width="3%">&nbsp;</td>
                <td width="97%">
                    <TABLE border="0" cellspacing="0" cellpadding="5" width="100%">
                        <tr>
                            <td class="V8NNegroGris">
                                <span id="liberarPagos_TAGDescripcionPagoTdcExtClave1" class="datos">Please check on your e-mail account the message you received from VBT Bank & Trust indicating the 'Transaction Key' to confirm and complete this transfer operation.</span><br>
                                <span id="liberarPagos_TAGDescripcionPagoTdcExtClave2" class="datos"><br><b>Remember that this Transaction Key is valid only to confirm the specific transfer operation that it originated.</b><br><br> It will become invalid:<br>  1. after used to confirm the specific payment operation,<br>  2. when the payment itself has been canceled or<br>  3. once you logoff from VBT Online.</span>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
            <tr><td height="12"></td></tr>
        </TABLE>
    </fieldset>
    <div id="resumen_liberarPagosTDC_FC" class="div_consultaPagos3">

    </div>
    <fieldset id="form_11" class=" div_consultaPagos"  style="display: none" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" height="50" class="" align="center">
                                <b><span id="liberar_TAGClaveOperacion" class="datos">Enter your Transaction Key</span></b>
                                <input type="text" name="pwdClaveConfirmReleasePaymentsTdc" tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmReleasePaymentsTdc">
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_9" class=" div_consultaPagos"  style="display: none">
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" class="" align="center">
                                <b><span id="liberar_PagosTDC_TAGImportante" class="datos">IMPORTANT NOTICE:</span></b>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" class="V8NBNegroGris" align="center">
                                <b><span id="liberar_TAGMsgAdvertencia" class="datos">Once you press Accept the transaction will be processed according<BR>to your instructions and no modification is permitted.</span> </b>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_10" class="div_consultaPagos center" >
        <input  type="button" id="btn_cancelarClave_liberarPagosTdc" value="Cancel" class="boton">
        <input  type="button" id="btn_aceptarClave_liberarPagosTdc" value="Accept" class="boton">

    </fieldset>

</div>
<div id="div_tabla_consultarPagosTDCLiberar" class="div_tabla_consultarPagosTDCLiberar">

</div>

<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 23/07/2012
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
<div>
    <h1 id="titulo_liberarTransferencia">Transferencias / Liberar Transferencias </h1>
</div>
<fieldset style="width:97%; margin-left:1%;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_LIBERAR_FC">This option enables you to release transfers.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_tabla_consultaTransferenciasPorLiberar" class="div_tabla_consulta">
    <fieldset class="div_consulta 0000000043_7">
        <input type="button" id="rechazarTransferenciasRelease" value="Rechazar" class="boton">
        <input type="button" id="liberarTransferencias" value="Liberar" class="boton ">

    </fieldset>
    <fieldset class="div_consulta">
        <legend id="tag_transferenciasLiberar">Transfers pending of release</legend>
        <label id="tag_seleccioneTransferenciasLiberar">Select the transfers you want to release</label>
        <div id="div_tabla_TransferenciasPorLiberar" class="div_tabla_TransferenciasPorLiberar">
        </div>

    </fieldset>
    <fieldset id="avisoLiberarTransferencias" class="div_consulta">
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" height="35" class="V8NBNegroGris" align="center">
                                <b><span id="FC_AVISO_IMPORTANTE" class="datos">IMPORTANT NOTICE</span></b>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" class="V8NBNegroGris" align="left">
                            <span id="FC_TAGMsgDisclaimer_Liberar_" class="datos">
                                Please read carefully before entering any transaction.<br><br>

                                1. Internal Transfers: Provided that the transaction you request is placed on a business day and within the specified hours, your transaction will have 48 hours value date. If placed on holidays or weekend, value date will be 48 hours later started counting on next business day.<br><br>
								2. External Transfers: 1. Value date for transactions placed during business days and before 3:00  pm (UTC/GMT -5) will be 48 business hours. If placed on holidays or weekend, value date will be 48 hours later started counting on next business day.
                                2. Transactions placed after 3:00  pm (UTC/GMT -5) will be processed on the next business day.<br><br>
                                3. To learn about the fees applied to each transaction, please refer to the Schedule of Fees at <A HREF="http://www.vbtbank.com">www.vbtbank.com</a> <br><br>
                                4. You will receive a confirmation for each transaction on you registered email account.
                            </span>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>

    </fieldset>
</div>
<div id="clave_liberarTransferencias" style="display: none">
    <fieldset id="form_12" class=" div_consultaTransfers"  style="display: none" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="1" align="center">
            <tr>
                <td width="3%">&nbsp;</td>
                <td width="97%">
                    <TABLE border="0" cellspacing="0" cellpadding="5" width="100%">
                        <tr>
                            <td class="V8NNegroGris">
                                <span id="aprobarTransferencias_TAGDescripcionTransfExtClave1" class="datos">Please check on your e-mail account the message you received from VBT Bank & Trust indicating the 'Transaction Key' to confirm and complete this transfer operation.</span><br>
                                <span id="aprobarTransferencias_TAGDescripcionTransfExtClave2" class="datos"><br><b>Remember that this Transaction Key is valid only to confirm the specific transfer operation that it originated.</b><br><br> It will become invalid:<br>  1. after used to confirm the specific transfer operation,<br>  2. when the transfer itself has been canceled or<br>  3. once you logoff from VBT Online.</span>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
            <tr><td height="12"></td></tr>
        </TABLE>
    </fieldset>
    <div id="resumen_trans_FC_liberar" class="div_consultaTransfers3">

    </div>

    <fieldset id="form_11" class=" div_consultaTransfers" style="display: none" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" height="50" class="" align="center">
                                <b><span id="liberar_TAGClaveOperacion" class="datos">Enter your Transaction Key</span></b>
                                <input type="text" name="pwdClaveConfirmRelease" tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmRelease">
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_9" class=" div_consultaTransfers" style="display: none" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" class="" align="center">
                                <b><span id="liberar_transferencias_TAGImportante" class="datos">IMPORTANT NOTICE:</span></b>
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
    <fieldset id="form_10" class="div_consultaTransfers center" >
        <input  type="button" id="btn_cancelarClave_liberar" value="Cancel" class="boton">
        <input  type="button" id="btn_aceptarClave_liberar" value="Accept" class="boton">

    </fieldset>

</div>
--%>

<%--
<div class="banner">
    <img
            class="banner__img banner__img--modifier"
            src="../vbtonline/resources/img/bg_portfolio.png"
            alt="Banner Home"
    />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="titulo_liberarTransferencia" class="banner__title banner__title--modifier">
                Transferencias / Liberar Transferencias
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome9" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>TRANSFERS</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="tag_titulo_consultarTransferenciasLib">TRANSFER / TRANSFER HISTORY</li>
            </ul>
            <p id="TAG_INFO_LIBERAR_FC" class="banner__description banner__description--modifier">
                This option enables you to release transfers.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="titulo_liberarTransferencia" class="banner__title banner__title--modifier">
                Transferencias / Liberar Transferencias
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome9" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="tag_titulo_consultarTransferenciasLib">TRANSFER / TRANSFER HISTORY</li>
            </ul>
            <p id="TAG_INFO_LIBERAR_FC" class="banner__description banner__description--modifier">
                This option enables you to release transfers.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="section">
        <div id="transfer_history_section" class="section__container container">

            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="tag_transferenciasLiberar" class="section__title">Transfers pending of release</span>
                </div>
                <div class="section__row section__row--spacebetween">
                    <label id="tag_seleccioneTransferenciasLiberar">Select the transfers you want to release </label>
                    <div class="section__buttons">
                        <button class="section__button button" id="rechazarTransferenciasRelease">Rechazar</button>
                        <button class="section__button button" id="liberarTransferencias">Liberar</button>
                    </div>
                </div>
            </div>


            <div class="section__content">
                <div  class="table">
                     <div id="div_tabla_TransferenciasPorLiberar" class="div_tabla_TransferenciasPorLiberar table__content">
    
                    </div> 
                </div>
            </div>
        </div>
    </div>
</main>
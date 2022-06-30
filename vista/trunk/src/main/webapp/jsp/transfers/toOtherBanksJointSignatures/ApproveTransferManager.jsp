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
    <h1 id="titulo_aprobarTransferencias">Transferencias / Aprobar Transferencias </h1>
</div>
<fieldset style="width:97%; margin-left:1%;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_APROBAR_FC">This option allows you to approve transfers. Remember that such transfers must be subsequently released by users with the appropriate permissions.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_tabla_consultaTransferenciasPorAprobar" class="div_tabla_consulta">
    <fieldset class="div_consulta 0000000042_6">

        <input type="button" id="rechazarTransferencias" value="Rechazar" class="boton">
        <input type="button" id="aprobarTransferencias" value="Aprobar" class="boton ">

    </fieldset>
    <fieldset class="div_consulta">
        <legend id="tag_transferenciasAprobar">Transfers pending of approval </legend>
        <label id="tag_aprobarSeleccione">Select the transfers you want to approve </label>
        <div id="div_tabla_TransferenciasPorAprobar" class="div_tabla_TransferenciasPorAprobar">
        </div>

    </fieldset>
</div>
<div id="clave_aprobarTransferencias" style="display: none">
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
    <div id="resumen_trans_FC_aprobar" class="div_consultaTransfers3">

    </div>
    <fieldset id="form_11" class=" div_consultaTransfers"  style="display: none" >
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" height="50" class="" align="center">
                                <b><span id="aprobar_TAGClaveOperacion" class="datos">Enter your Transaction Key</span></b>
                                <input type="text" name="pwdClaveConfirmApprove" tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmApprove">
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_9" class=" div_consultaTransfers"  style="display: none">
        <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td height="50" >
                    <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td width="100%" class="" align="center">
                                <b><span id="aprobar_transferencias_TAGImportante" class="datos">IMPORTANT NOTICE:</span></b>
                            </td>
                        </tr>
                        <tr>
                            <td width="100%" class="V8NBNegroGris" align="center">
                                <b><span id="aprobar_TAGMsgAdvertencia" class="datos">Once you press Accept the transaction will be processed according<BR>to your instructions and no modification is permitted.</span> </b>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
        </TABLE>
    </fieldset>
    <fieldset id="form_10" class="div_consultaTransfers center" >
        <input  type="button" id="btn_cancelarClave_aprobar" value="Cancel" class="boton">
        <input  type="button" id="btn_aceptarClave_aprobar" value="Accept" class="boton">

    </fieldset>

</div>
<div id="div_tabla_consultarTransferenciasAprobar" class="div_tabla_consultarTransferenciasAprobar">

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
            <h2 id="titulo_aprobarTransferencias" class="banner__title banner__title--modifier">
                Transferencias / Aprobar Transferencias
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome16" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="TagTransferApro">CREDIT CARDS</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="TagTransferAproSub">PREVENTIVE BLOCK - UNBLOCK</li>
            </ul>
            <p id="TAG_INFO_APROBAR_FC" class="banner__description banner__description--modifier">
                This option allows you to approve transfers. Remember that such transfers must be subsequently released by users with the appropriate permissions.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <div class="banner__content">
                <h2 id="titulo_aprobarTransferencias" class="banner__title banner__title--modifier">
                    Transferencias / Aprobar Transferencias
                </h2>
            <ul class="banner__nav">
                <li><a id="TagHomeApro116" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagTransferAproCre">CREDIT CARDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TagTransferAproSubCre">PREVENTIVE BLOCK - UNBLOCK</li>
            </ul>
            <p id="TAG_INFO_APROBAR_FC" class="banner__description banner__description--modifier">
                This option allows you to approve transfers. Remember that such transfers must be subsequently released by users with the appropriate permissions.
            </p>
            <div id="ver_mas_aprove_transfer" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
    </div>
</div>
<div id="dialog-description-show-more"  style="display:none">
    <p></p>
</div>
<main class="main main--modifier">
    <div class="section">
        <div id="transfer_history_section" class="section__container container">

            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="tag_transferenciasAprobar" class="section__title">Transfers pending of approval</span>
                </div>
                <div class="section__row section__row--spacebetween">
                    <label id="tag_aprobarSeleccione">Select the transfers you want to approve </label>
                    <div class="section__buttons">
                        <button class="section__button button" id="rechazarTransferencias">Rechazar</button>
                        <button class="section__button button" id="aprobarTransferencias">Aprobar</button>
                    </div>
                </div>
            </div>


            <div class="section__content">
                <div  class="table">
                    <div id="div_tabla_TransferenciasPorAprobar" class="div_tabla_consultarTransferenciasAprobar table__content">
    
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>
<main class="main main--modifier">
    <div id="clave_aprobarTransferencias" style="display: none">
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
        <div id="resumen_trans_FC_aprobar" class="div_consultaTransfers3">
    
        </div>
        <fieldset id="form_11" class=" div_consultaTransfers"  style="display: none" >
            <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td height="50" >
                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="100%" height="50" class="" align="center">
                                    <b><span id="aprobar_TAGClaveOperacion" class="datos">Enter your Transaction Key</span></b>
                                    <input type="text" name="pwdClaveConfirmApprove" tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmApprove">
                                </td>
                            </tr>
                        </TABLE>
                    </td>
                </tr>
            </TABLE>
        </fieldset>
        <fieldset id="form_9" class=" div_consultaTransfers"  style="display: none">
            <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr>
                    <td height="50" >
                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td width="100%" class="" align="center">
                                    <b><span id="aprobar_transferencias_TAGImportante" class="datos">IMPORTANT NOTICE:</span></b>
                                </td>
                            </tr>
                            <tr>
                                <td width="100%" class="V8NBNegroGris" align="center">
                                    <b><span id="aprobar_TAGMsgAdvertencia" class="datos">Once you press Accept the transaction will be processed according<BR>to your instructions and no modification is permitted.</span> </b>
                                </td>
                            </tr>
                        </TABLE>
                    </td>
                </tr>
            </TABLE>
        </fieldset>
        <fieldset id="form_10" class="div_consultaTransfers center" >
            <input  type="button" id="btn_cancelarClave_aprobar" value="Cancel" class="boton">
            <input  type="button" id="btn_aceptarClave_aprobar" value="Accept" class="boton">
    
        </fieldset>
    
    </div>
<!--     <div id="div_tabla_consultarTransferenciasAprobar" class="div_tabla_consultarTransferenciasAprobar">
    
    </div> -->
</main>



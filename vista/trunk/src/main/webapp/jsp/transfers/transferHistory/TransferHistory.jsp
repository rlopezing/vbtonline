<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="tag_titulo_consultarTransferencias">Transferencias / Consultar Transferencias</h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info" id="TAG_INFO_TRANSFERENCIA_FI">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_TRANSF_FI">This option allows you to view the details of all transfers made through VBT Online.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info" id="TAG_INFO_TRANSFERENCIA_FC">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_TRANSF_FC">This option allows you to view the details of all transfers made through VBT Online.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr id="fechas_Transferencias">
            <td class="datos">
                <span id="transferencias_TAGDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_Transferencias" class="invisible_print inputFormulario requeridoFecha_Transferencias" tabindex="2">
                <span id="fechaDesdeFiltro_Transferencias_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="transferencias_TAGHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_Transferencias"  class="invisible_print inputFormulario requeridoFecha_Transferencias" tabindex="2">
                <span id="fechaHastaFiltro_Transferencias_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>

            <td class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a style="display: none" id="transfer_history_imprimir" onclick="printConsultTranfer()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>

            <td  class="botones_formulario">
                <input type="button" id="consultar_Transferencias" value="Consultar" class="botonEDOCuenta">

            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="div_tabla_consulta_TransferenciasGenerales" class="div_tabla_consulta"> </div>
    <div id="paginacion_tabla_consulta_TransferenciasGenerales" class="div_tabla_consulta"> </div>
</fieldset>--%>
<%--
<div class="banner">
    <img
            class="banner__img banner__img--modifier"
            src="../vbtonline/resources/img/bg_portfolio.png"
            alt="Banner Home"
    />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tag_titulo_consultarTransferencias" class="banner__title banner__title--modifier">
                TRANSFER / TRANSFER HISTORY
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
                <li>TRANSFER / TRANSFER HISTORY</li>
            </ul>
            <p id="TAG_INFO_TRANSF_FI" class="banner__description banner__description--modifier">
                This option allows you to view the details of all payments made through VBT Online.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tag_titulo_consultarTransferencias" class="banner__title banner__title--modifier">
                TRANSFER / TRANSFER HISTORY
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome9" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TransferHistory_Transfer">TRANSFERS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TransferHistory_TransferHistory">TRANSFER HISTORY</li>
            </ul>
            <p id="TAG_INFO_TRANSF_FI" class="banner__description banner__description--modifier">
                This option allows you to view the details of all payments made through VBT Online.
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
            <div id="fechas_Transferencias" class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <div></div>
                    <img id="transfer_history_imprimir" onclick="printConsultTranfer()" class="section__icon"
                         src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="transferencias_TAGDesde" for="fechaDesdeFiltro_Transferencias">LABEL</label>
                            <input type="text"
                                   id="fechaDesdeFiltro_Transferencias"
                                   class="input input--form ml-1_5 invisible_print inputFormulario requeridoFecha_Transferencias" />
                        </div>
                       <div class="section__field">
                            <label id="transferencias_TAGHasta" for="fechaHastaFiltro_Transferencias">LABEL</label>
                           <input type="text" id="fechaHastaFiltro_Transferencias"
                                  class="input input--form ml-1_5 invisible_print inputFormulario requeridoFecha_Transferencias" />
                        </div>
                    </div>
                    <div class="section__buttons">
                        <button class="section__button button" id="consultar_Transferencias">Consult</button>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div  class="table">
                    <table id="tabla_consulta_TransferenciasGenerales" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>


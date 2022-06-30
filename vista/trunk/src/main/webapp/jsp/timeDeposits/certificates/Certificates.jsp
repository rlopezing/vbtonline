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
    <h1 id="colocaciones_titulo_C">Colocaciones / Certificados </h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_COLOCACIONES_CERTIFICADOS">This option allows you to see and print the certificate(s) of your Time Deposit(s).</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_certificados_colocaciones">
<fieldset class="div_consulta">
    <legend>
        <span id="comun2_CERTIFICADOS" style="display: block">Certificados</span>
        <span id="comun2_CERTIFICADOS_al" style="display: none">Certificados al: </span>
        <span id="comun2_CERTIFICADOS_fechaCierre"></span>

    </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr>
            <td class="datos"> <span id="colocacionescertificados_TAGBuscar">Buscar </span></td>

            <td class="datos"></td>
        </tr>
        <tr>

            <td class="datos">
                <select  id="buscar_ColocacionesCertificados" title="Buscar" class="requerido_ColCer" style="width:240px;" onchange="validarActivarFechas_ColCer(this.value)" ></select>
            </td>
            <td  class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
            </td>
            <td  class="botones_formulario" width="10%">
                <input type="button" id="consultar_ColCer" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>
        <tr id="fechas_ColCer" style="display: none">
            <td class="datos">
                <span id="colocacionescertificados_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_ColCer" class="inputFormulario requeridoFecha_ColCer" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="colocacionescertificados_TAGFechaHasta">Fecha Hasta</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_ColCer"  class="inputFormulario requeridoFecha_ColCer" tabindex="2">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="certificados_ColCer_div_tabla_consulta" class="div_tabla_consulta">
    </div>
</fieldset>


<div id="div_mensajes_info_desc_tabla_TD_C" class="info_descp_tabla oculto">
    <div id="cerrar_div_mensajes_info_desc_tabla_TD_C"><img width="24px" height="24px" src="../vbtonline/resources/images/close.png"></div>
    <div id="mensajes_info_desc_tabla_TD_C">
        <table width="100%">
            <tr>
                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGCarteraNumero">Cartera N </span><span class="label_descp">:</span></td>
                <td width="25%"><span id="label_cartera_TD_C"></span></td>
                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGMontoVencimiento">Monto </span><span class="label_descp">:</span></td>
                <td width="25%"><span id="label_Monto_TD_C"></span></td>
            </tr>
            <tr>
                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGTasa">Tasa </span><span class="label_descp">:</span></td>
                <td width="25%"><span id="label_tasa_TD_C"></span></td>
                <td width="25%"> </td>
                <td width="25%"></td>
            </tr>
        </table>
    </div>

</div>
</div>
<div id="div_noPoseeColocacionesCertificados" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones3">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>
--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_transfers.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="colocaciones_titulo_C" class="banner__title banner__title--modifier">Time Deposits / Certificates</h2>
            <ul class="banner__nav">
                <li><a id="TagHome18" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="TAGTitleNavColocaciones3">TIME DEPOSITS</li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="colocaciones_titulo_Nav_C">CERTIFICATES</li>
            </ul>
            <p id="TAG_INFO_COLOCACIONES_CERTIFICADOS" class="banner__description banner__description--modifier">This option allows you to see and print the certificate(s) of your Time Deposit(s).
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="colocaciones_titulo_C" class="banner__title banner__title--modifier">Time Deposits / Certificates</h2>
            <ul class="banner__nav">
                <li><a id="TagHome18" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavColocaciones3">TIME DEPOSITS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="colocaciones_titulo_Nav_C">CERTIFICATES</li>
            </ul>
            <p id="TAG_INFO_COLOCACIONES_CERTIFICADOS" class="banner__description banner__description--modifier">This option allows you to see and print the certificate(s) of your Time Deposit(s).
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_certificados_colocaciones" class="section">
        <div class="section__container container">
            <div id="fechas_Transferencias" class="section__top">
                <div style="display: flex;flex-direction: row;justify-content: space-between;">
                    <div class="form-filter__select select-section select-section--form">
                        <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" style="margin-right: 1em;"/>
                        <select id="buscar_ColocacionesCertificados"
                                title="Search"
                                class="select-section__select select-section__select--form requerido_ColCer"
                                onchange="validarActivarFechas_ColCer(this.value)">
                        </select>
                        <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                    </div>
                    <div class="section__header" style="justify-content: end;">
                        <p class="balance-date">
                            <span id="comun2_CERTIFICADOS" style="display: block">Certificados</span>
                            <span id="comun2_CERTIFICADOS_al" style="display: none">Certificados al: </span>
                            <span id="comun2_CERTIFICADOS_fechaCierre"></span>
                        </p>
                        <img id="transfer_history_imprimir" onclick="printConsultTranfer()" class="section__icon"
                        src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div id="fechaDesdeFiltro_ColCerField" class="section__field">
                            <label id="colocacionescertificados_TAGFechaDesde" class="form-filter__label" for="fechaDesdeFiltro_ColCer">Label</label>
                            <input id="fechaDesdeFiltro_ColCer"
                                   class="form-filter__input input input--form inputFormulario requeridoFecha_ColCer"
                                   title="From"
                                   placeholder="dd/mm/yyyy"
                                   type="text"/>
                        </div>
                       <div id="fechaHastaFiltro_ColCerField" class="section__field">
                        <label id="colocacionescertificados_TAGFechaHasta" class="form-filter__label" for="fechaDesdeFiltro_ColCer">Label</label>
                        <input id="fechaHastaFiltro_ColCer"
                               class="form-filter__input input input--form inputFormulario requeridoFecha_ColCer"
                               title="To"
                               placeholder="dd/mm/yyyy"
                               type="text"/>
                        </div>
                    </div>
                    <div class="section__buttons">
                        <button class="section__button button" id="consultar_ColCer">Consult</button>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div id="panelControlesCertificates" class="form-filter" style="display: flex;flex-direction: row;align-content: space-between;">
                    <div class="form-filter__grid">
<!--                         <div class="form-filter__item">
                            <label id="colocacionescertificados_TAGBuscar" class="form-filter__label" for="buscar_ColocacionesCertificados">Label</label>

                        </div> -->
                        <div id="fechaDesdeFiltro_ColCerField" class="form-filter__item">
                            <label id="colocacionescertificados_TAGFechaDesde" class="form-filter__label" for="fechaDesdeFiltro_ColCer">Label</label>
                            <input id="fechaDesdeFiltro_ColCer"
                                   class="form-filter__input input input--form inputFormulario requeridoFecha_ColCer"
                                   title="From"
                                   placeholder="dd/mm/yyyy"
                                   type="text"/>
                        </div>
                        <div id="fechaHastaFiltro_ColCerField" class="form-filter__item">
                            <label id="colocacionescertificados_TAGFechaHasta" class="form-filter__label" for="fechaDesdeFiltro_ColCer">Label</label>
                            <input id="fechaHastaFiltro_ColCer"
                                   class="form-filter__input input input--form inputFormulario requeridoFecha_ColCer"
                                   title="To"
                                   placeholder="dd/mm/yyyy"
                                   type="text"/>
                        </div>
                    </div>
                    <div class="form-filter__buttons" style="display: block;margin-top: 1em;">
                        <input id="consultar_ColCer" type="button" class="form-filter__button button" value="Consult" />
                    </div>
                </div>
                <div class="table">
                    <table id="certificados_ColCer_tabla_consulta" class="table__content">
                    </table>
                </div>
                <div id="div_mensajes_info_desc_tabla_TD_C" class="info_descp_tabla oculto">
                    <div id="cerrar_div_mensajes_info_desc_tabla_TD_C"><img width="24px" height="24px"
                                                                            src="../vbtonline/resources/images/close.png"></div>
                    <div id="mensajes_info_desc_tabla_TD_C">
                        <table width="100%">
                            <tr>
                                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGCarteraNumero">Cartera N
                                </span><span class="label_descp">:</span></td>
                                <td width="25%"><span id="label_cartera_TD_C"></span></td>
                                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGMontoVencimiento">Monto </span><span
                                        class="label_descp">:</span></td>
                                <td width="25%"><span id="label_Monto_TD_C"></span></td>
                            </tr>
                            <tr>
                                <td width="25%"><span class="label_descp" id="colocacionescertificados_TAGTasa">Tasa </span><span
                                        class="label_descp">:</span></td>
                                <td width="25%"><span id="label_tasa_TD_C"></span></td>
                                <td width="25%"> </td>
                                <td width="25%"></td>
                            </tr>
                        </table>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div id="div_noPoseeColocacionesCertificados" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__content">
                <span id="mensaje_sinColocaciones3">Usted no posee colocaciones que consultar </span>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>
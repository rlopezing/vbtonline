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
    <h1 id="TDC_titulo_pago">Tarjetas de Cr&eacute;dito / Pago de Tarjetas</h1>
</div>


<div id="div_creditCard_pagos">
    <fieldset class="invisible_print div_info" style="width:99%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left" style="text-align:justify;">
                    <span id="TAG_INFO_TDC_PAGOS" class="datosInfo">
                        Esta opción le permite realizar el pago de sus tarjetas de crédito.
                    <br>Nota importante:<br>
                        <li>Los pagos realizados en día feriado bancario o fin de semana, y las transacciones realizadas después de las 3:00 pm (UTC/GMT -5) serán procesados el siguiente día hábil bancario.</li>
                        <li>Para conocer el costo de sus operaciones en el VBT Online, por favor referirse al Schedule of Fees ubicado en www.vbtbank.com</li>
                    </span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <table  width="100%"  SUMMARY='tabla' cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td class="datos" width="12%"><span class="label_numero_cuenta_TDC_CL" id="TAGNumeroTarjeta_Pago">N&uacute;mero de Tarjeta</span><span>:</span></td>
                <td class="datos">
                    <select  id="numero_cuenta_TDC_Pago" title="Numero de Tarjeta" style="width:320px;margin-right:4px" class="chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla" onchange="cargarHistorialPagos2(this.value)">
                    </select>
                    &lt;%&ndash;<input type="password" id="numero_cuenta_TDC_CL" title="Numero de Tarjeta" class="requerido_TDC_CL invisible_print" />&ndash;%&gt;
                    <span id="numero_cuenta_TDC_Pago_select" class="visible_print"></span>
                    <span id="tag_cliente_bloqueo_pago" style="padding-left: 14px;"></span>

                </td>
                <td class="datos" width="12%">
                    &lt;%&ndash;<span class="label_estatus_TDC_CL" id="TAGEstatusTarjeta" style="padding-left: 20px">Estatus</span><span>:</span>&ndash;%&gt;
                </td>
                <td class="datos" align="left">
                    <input type="button" id="clear_pagos" value="Limpiar" class="botonGrandeDerecha">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta 0000000093_1 oculto" id="pay_card">
        <legend id="pagos_actual">Pay Card</legend>
        <table>
            <tr>
                <td class="datos" height="20px" width="200px"><label id="tdcpagos_TAGSaldoActual"></label></td>
                <td class="datos" style="text-align:right" height="20px"><label id="tdcpagos_TAGSaldoActual_datos"></label></td>
                <td class="datos" height="20px" width="200px"></td>
            </tr>
            <tr id="tr_facturado">
                <td class="datos" height="20px" width="200px">
                    <label id="tdcpagos_TAGSaldoFacturado"></label>
                    <span id="tdcpagos_TAGFechaSaldoTotal"></span>
                </td>
                <td class="datos" style="text-align:right" height="20px">
                    <label id="tdcpagos_TAGSaldoFacturado_datos"></label>
                </td>
                <td class="datos" height="20px" width="200px"></td>
            </tr>
            <tr>
                <td class="datos"height="20px"><label id="tdcpagos_TAGMontoBloqueado"></label></td>
                <td class="datos"  style="text-align:right" height="20px"><label id="tdcpagos_TAGMontoBloqueado_datos"></label></td>
                <td class="datos"  style="text-align:right" height="20px"></td>
            </tr>
            <tr>
                <td class="datos"><label id="tdcpagos_TAGMontoDisponible"></label></td>
                <td class="datos" style="text-align:right"><label id="tdcpagos_TAGMontoDisponible_datos"></label></td>
                <td class="datos" height="20px" width=""></td>
            </tr>
            <tr id="tr_minimo">
                <td class="datos"   height="20px"><label id="tdcpagos_TAGPagoMin"></label></td>
                <td class="datos"  style="text-align:right" height="20px"><label id="tdcpagos_TAGPagoMin_datos"></label></td>
                <td class="datos"  style="text-align:right" height="20px"></td>
            </tr>
        </table>
        <table>
            <tr>
                <td class="datos"  height="20px" width="200px"><label id="TAGTipoPagoTDC">Pago</label></td>
                <td class="datos"   style="text-align:right" height="20px" width="200px"><select  id="tipoPagoTDC" title="Numero de Tarjeta" style="width:430px;margin-right:4px" class="chosen-select requerido_TDC_CL invisible_print requeridoCrearPagoTdc" onchange="validarTipoPagoTDC(this.id)">
                </select></td>
            </tr>
            <tr id="otroMontoPagoTDCTR" class="oculto">
                <td class="datos" height="20px"><label id="TAGPagoOtroMonto">Monto</label></td>
                <td height="20px"><input type="text" class="requeridoCrearPagoTdc" id="otroMontoPagoTDC" onkeypress="return onlyDigits_v2(event, this.value,true,true,true,'.',',',2,'OMP');" onblur="this.value=formatCurrency(this.value,true,2,'.');"><label class="datos">USD</label></td>

            </tr>
            <tr>
                <td class="datos" height="20px"><label  id="TAGCuentaPagoSelectTDC">Cuenta</label></td>
                <td class="datos" height="20px"><select  id="numero_cuenta_pago" title="Numero de Tarjeta" style="width:430px;" class="chosen-select requeridoCrearPagoTdc requerido_TDC_CL invisible_print requeridoCrearRegla">
                </select></td>
            </tr>
        </table>
        <br>
        <center> <input type="button" id="crear_pagos" value="Pagar" class="botonGrande" ></center>
        <br>
    </fieldset>
    &lt;%&ndash;  <fieldset class="div_consulta">
          <legend id="pagos_movimientos_por_facturar">Payments In Transit </legend>

          <div id="div_tabla_consulta_TDC_Pagos_movimientos" class="div_tabla_consulta" style="padding-top:20px;">
             <table id="tabla_consulta_TDC_Pagos_movimientos" width="100%">
                 <thead>
                     <tr>
                         <th></th>
                         <th></th>
                         <th></th>
                         <th></th>
                         <th></th>
                     </tr>
                 </thead>
                 <tbody>
                     <tr>
                         <td></td>
                         <td></td>
                         <td></td>
                         <td></td>
                         <td></td>
                     </tr>
                 </tbody>
             </table>
          </div>
        <br>
    </fieldset>  &ndash;%&gt;

    &lt;%&ndash;<fieldset class="div_consulta">&ndash;%&gt;
        &lt;%&ndash;<legend id="pagos_historial">Historial de Pagos</legend>&ndash;%&gt;
        &lt;%&ndash;<div id="filtroPagosTDC">&ndash;%&gt;
            &lt;%&ndash;<span id="tag_fec_desde_Pago" style="padding-right: 20px;">Desde:</span><input type="text" id="fec_desde_Pago" class="requeridoConsultarPagos"/>&ndash;%&gt;
            &lt;%&ndash;<span id="tag_fec_hasta_Pago" style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" id="fec_hasta_Pago" class="requeridoConsultarPagos"/>&ndash;%&gt;
            &lt;%&ndash;<input type="button" id="consultar_pagos" value="Buscar" class="botonGrandeDerecha">&ndash;%&gt;
        &lt;%&ndash;</div>&ndash;%&gt;
        &lt;%&ndash;<div id="div_tabla_consulta_historial_TDC_Pagos" class="div_tabla_consulta" style="padding-top:20px;">&ndash;%&gt;
            &lt;%&ndash;<table id="tabla_consulta_historial_TDC_Pagos" width="100%">&ndash;%&gt;
                &lt;%&ndash;<thead>&ndash;%&gt;
                &lt;%&ndash;<tr>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                    &lt;%&ndash;<th></th>&ndash;%&gt;
                &lt;%&ndash;</tr>&ndash;%&gt;
                &lt;%&ndash;</thead>&ndash;%&gt;
                &lt;%&ndash;<tbody>&ndash;%&gt;
                &lt;%&ndash;<tr>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                    &lt;%&ndash;<td></td>&ndash;%&gt;
                &lt;%&ndash;</tr>&ndash;%&gt;
                &lt;%&ndash;</tbody>&ndash;%&gt;
            &lt;%&ndash;</table>&ndash;%&gt;
        &lt;%&ndash;</div>&ndash;%&gt;
        &lt;%&ndash;</div>&ndash;%&gt;
        &lt;%&ndash;<br>&ndash;%&gt;
    &lt;%&ndash;</fieldset>&ndash;%&gt;

</div>--%>
<%--
<div class="banner">
    <img
            class="banner__img banner__img--modifier"
            src="../vbtonline/resources/img/bg_portfolio.png"
            alt="Banner Home"
    />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TDC_titulo_pago" class="banner__title banner__title--modifier">
                Credit Cards / Credit Card Payment
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome14" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="CC_PC_tag_ling_credit_cards">CREDIT CARDS</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="CC_PC_tag_ling_credit_card_payment">CREDIT CARD PAYMENT</li>
            </ul>
            <p id="TAG_INFO_TDC_PAGOS" class="banner__description banner__description--modifier" >
                This option allows you to view the details of all payments made through VBT Online.
            </p>
            <div id="ver_mas_payment_history" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TDC_titulo_pago" class="banner__title banner__title--modifier">
                Credit Cards / Credit Card Payment
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome14" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CC_PC_tag_ling_credit_cards">CREDIT CARDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CC_PC_tag_ling_credit_card_payment">CREDIT CARD PAYMENT</li>
            </ul>
            <p id="TAG_INFO_TDC_PAGOS" class="banner__description banner__description--modifier" >
                This option allows you to view the details of all payments made through VBT Online.
            </p>
            <div id="ver_mas_payment_history" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<div id="dialog-description-show-more"  style="display:none">
    <p></p>
</div>
<main id="div_creditCard_pagos" class="main main--modifier">
    <div class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <div class="section__field">
                        <label id="TAGNumeroTarjeta_Pago" for="numero_cuenta_TDC_Pago">LABEL</label>
                        <div class="select-section select-section--form">
                            <select id="numero_cuenta_TDC_Pago"
                                    title="Numero de Tarjeta"
                                    class="select-section__select select-section__select--form chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla"
                                    onchange="cargarHistorialPagos2(this.value)">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                        </div>
                        <span id="tag_cliente_bloqueo_pago"></span>
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div></div>
                    <button class="section__button button button--white" id="clear_pagos">Clear</button>
                </div>
            </div>
            <div class="section__content">
                <div id="pay_card" class="form-transfer oculto">
                    <div class="form-transfer__container">
                        <div class="form-transfer__grid" style="margin-top: 0;">
                            <div class="form-transfer__item">
                                <div id="pagos_actual" class="form-transfer__field">Transfer Parameters In VBT</div>
                                <div class="form-transfer__field">
                                    <label id="tdcpagos_TAGSaldoActual">Current Balance</label>
                                    <span id="tdcpagos_TAGSaldoActual_datos"></span>
                                </div>
                                <div id="tr_facturado" class="form-transfer__field">
                                    <div>
                                        <label id="tdcpagos_TAGSaldoFacturado">Total Payment as of</label>
                                        <label id="tdcpagos_TAGFechaSaldoTotal"></label>
                                    </div>
                                    <span id="tdcpagos_TAGSaldoFacturado_datos"></span>
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tdcpagos_TAGMontoBloqueado">Pending Transactions</label>
                                    <span id="tdcpagos_TAGMontoBloqueado_datos"></span>
                                </div>
                                <div class="form-transfer__field">
                                    <label id="tdcpagos_TAGMontoDisponible">Available Credit Limit</label>
                                    <span id="tdcpagos_TAGMontoDisponible_datos"></span>
                                </div>
                                <div id="tr_minimo" class="form-transfer__field">
                                    <label id="tdcpagos_TAGPagoMin">Minimum Payment Due</label>
                                    <span id="tdcpagos_TAGPagoMin_datos"></span>
                                </div>
                            </div>
                            <div class="form-transfer__item">
                                <div class="form-transfer__field"></div>
                                <div class="form-transfer__field">
                                    <label id="TAGTipoPagoTDC" for="tipoPagoTDC">Payment Option</label>
                                    <div class="select-section select-section--form">
                                        <select
                                                class="select-section__select select-section__select--form select-section__select--gray chosen-select requerido_TDC_CL invisible_print requeridoCrearPagoTdc"
                                                id="tipoPagoTDC" onchange="validarTipoPagoTDC(this.id)">

                                        </select><img class="select-section__icon select-section__icon--form"
                                                      src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                    </div>
                                </div>
                                <div id="otroMontoPagoTDCTR" class="form-transfer__field oculto">
                                    <label id="TAGPagoOtroMonto" for="otroMontoPagoTDC">Amount (USD)</label>
                                    <div class="input-flex">
                                        <input id="otroMontoPagoTDC" class="input input--form requeridoCrearPagoTdc" type="text" onkeypress="return onlyDigits_v2(event, this.value,true,true,true,'.',',',2,'OMP');"
                                               onblur="this.value=formatCurrency(this.value,true,2,'.');"/>
                                        <span>USD</span>
                                    </div>
                                </div>
                                <div class="form-transfer__field">
                                    <label id="TAGCuentaPagoSelectTDC" for="numero_cuenta_pago">Account</label>
                                    <div class="select-section select-section--form">
                                        <select
                                                class="select-section__select select-section__select--form select-section__select--gray chosen-select requeridoCrearPagoTdc requerido_TDC_CL invisible_print requeridoCrearRegla"
                                                id="numero_cuenta_pago">

                                        </select><img class="select-section__icon select-section__icon--form"
                                                      src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-transfer__spacebetween" style="padding-bottom:0em">
                            <span class="form-transfer__mandaroty"></span>
                            <div class="form-transfer__buttons">
                                <!-- cambio miguel-->
                                <button class="form-transfer__button form-transfer__button--next button"
                                        id="pagos_actual_2" data-tradution="pagos_actual">
                                    SAVE
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>

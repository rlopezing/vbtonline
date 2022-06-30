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
    <h1 id="colocaciones_titulo_V">Colocaciones / Vencimientos </h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_COLOCACIONES_VENCIMIENTOS">This option allows you to see the maturities of your Time Deposit(s).</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_vencimientos_colocaciones">
<fieldset class="div_consulta">
    <legend><span id="colocacionesvencimientos_TAGVencimientos">Vencimientos</span>&nbsp;<span id="colocacionesbloqueos_TAGAl">al</span>&nbsp;<span id="fechaCierreVencimiento"></span>  </legend>
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 98%;">
        <tr>
            <td class="datos"> <span id="colocacionescertificados_TAGBuscar">Buscar </span></td>

            <td class="datos"></td>
        </tr>
        <tr>

            <td class="datos">
                <select  id="buscar_ColocacionesVencimientos" title="Buscar" class="requerido_ColVen" style="width:250px;" onchange="validarActivarFechas_ColVen(this.value)" >

                </select>
            </td>
            <td  class="botones_formulario">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a id="maturity_imprimir" onclick="print_VENCIMIENTOS()" TITLE="imprimir"> <img class="imprimir" src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
            <td  class="botones_formulario" width="10%">
                <input type="button" id="consultar_ColVen" value="Consultar" class="botonEDOCuenta">
            </td>
        </tr>
        <tr id="fechas_ColVen" style="display: none">
            <td class="datos">
                <span id="colocacionesvencimientos_TAGFechaDesde">Fecha Desde</span><span>:</span>
                <input type="text" title="Fecha desde" id="fechaDesdeFiltro_ColVen" class="invisible_print inputFormulario requeridoFecha_ColVen" tabindex="2">
                <span id="fechaDesdeFiltro_ColVen_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td class="datos">
                <span id="colocacionesvencimientos_TAGFechaHasta">Fecha Hasta:</span><span>:</span>
                <input type="text" title="Fecha hasta" id="fechaHastaFiltro_ColVen"  class="invisible_print inputFormulario requeridoFecha_ColVen" tabindex="2">
                <span id="fechaHastaFiltro_ColVen_print" class="datos visible_print"></span>
                <label class="datos invisible_print">dd/mm/yyyy</label>
            </td>
            <td>&nbsp;</td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta">
    <div id="vencimientos_ColVen_div_tabla_consulta" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<div id="div_noPoseeColocacionesVencimientos" style="display: none">
    <fieldset class="div_consulta">
        <span class="datos" id="mensaje_sinColocaciones4">Usted no posee colocaciones que consultar </span>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_transfers.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="colocaciones_titulo_V" class="banner__title banner__title--modifier">TIME DEPOSITS / MATURITIES</h2>
            <ul class="banner__nav">
                <li><a id="TagHome19" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="TAGTitleNavColocaciones2">TIME DEPOSITS</li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li id="colocaciones_titulo_Nav_V">MATURITIES</li>
            </ul>
            <p id="TAG_INFO_COLOCACIONES_VENCIMIENTOS" class="banner__description banner__description--modifier">
                This option allows you to see and print the certificate(s) of your Time Deposit(s).
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="colocaciones_titulo_V" class="banner__title banner__title--modifier">TIME DEPOSITS / MATURITIES</h2>
            <ul class="banner__nav">
                <li><a id="TagHome19" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleNavColocaciones2">TIME DEPOSITS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="colocaciones_titulo_Nav_V">MATURITIES</li>
            </ul>
            <p id="TAG_INFO_COLOCACIONES_VENCIMIENTOS" class="banner__description banner__description--modifier">
                This option allows you to see and print the certificate(s) of your Time Deposit(s).
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_vencimientos_colocaciones" class="section">
        <div class="section__container container">
            <div id="fechas_Transferencias" class="section__top">
                <div style="display: flex;flex-direction: row;justify-content: space-between;">
                    <div class="form-filter__select select-section select-section--form">
                        <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" style="margin-right: 1em;"/>
                        <select id="buscar_ColocacionesVencimientos"
                                title="Search"
                                class="select-section__select select-section__select--form requerido_ColVen"
                                onchange="validarActivarFechas_ColVen(this.value)">
                        </select>
                        <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                    </div>
                    <div class="section__header" style="justify-content: end;">
                        <div class="balance-date">
                            <span id="colocacionesvencimientos_TAGVencimientos">Vencimientos</span>
                            <span id="colocacionesbloqueos_TAGAl">al</span>
                            <span id="fechaCierreVencimiento"></span>
                        </div>
                        <img id="maturity_imprimir" onclick="print_VENCIMIENTOS()" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div id="fechaDesdeFiltro_ColVenField" class="section__field">
                            <label id="colocacionesvencimientos_TAGFechaDesde" class="form-filter__label" for="fechaDesdeFiltro_ColVen">Label</label>
                            <input id="fechaDesdeFiltro_ColVen"
                                   class="form-filter__input input input--form invisible_print inputFormulario requeridoFecha_ColVen"
                                   title="From"
                                   placeholder="dd/mm/yyyy"
                                   type="text"/>
                        </div>
                       <div id="fechaHastaFiltro_ColVenField" class="section__field">
                        <label id="colocacionesvencimientos_TAGFechaHasta" class="form-filter__label" for="fechaHastaFiltro_ColVen">Label</label>
                        <input id="fechaHastaFiltro_ColVen"
                               class="form-filter__input input input--form invisible_print inputFormulario requeridoFecha_ColVen"
                               title="To"
                               placeholder="dd/mm/yyyy"
                               type="text"/>
                        </div>
                    </div>
                    <div class="section__buttons">
                        <button class="section__button button" id="consultar_ColVen">Consult</button>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <table id="vencimientos_ColVen_tabla_consulta" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div id="div_noPoseeColocacionesVencimientos" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__content">
                <span id="mensaje_sinColocaciones4"></span>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>
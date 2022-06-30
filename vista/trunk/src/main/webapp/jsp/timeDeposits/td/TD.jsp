<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="TAGcolocaciones_titulo">Colocaciones / Apertura </h1>
</div>
<div>
    <fieldset id="DIV_INFO_INTERNAS_FI" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
        <table cellspacing="0" cellpadding="0" width="100%" summary="tabla" align="center">
            <tbody>
            <tr>
                <td width="3%">
                    <img  src="resources/images/iconInfo.png" >
                </td>
                <td align="left">
                    <span id="TAGDescripcionTD" class="datosInfo" >This option enables you to create a new Time Deposit with VBT Bank & Trust.</span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <div id="aperturaTDForm">
    <fieldset id="TDCForm" class="formulario_fieldset div_consulta right">
         <section id="seleccDebitoTDC">
             <table>
                 <td class="datos">
                     <label for="buscar_tipoTDC" id="TAGbuscarTipo" >Tipo</label><span class="datos">:</span>
                     <select  id="buscar_tipoTDC" title="Tipo" class="invisible_print VerificarTD blankSelectTD VerificarTD2 calTasaTD" style="width:170px;" onchange="" ></select><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>
                 </td>
                 <td class="datos">
                     <label for="buscar_productoDebitoTD" id="TAGproductoDebito"  style="margin-left: 4px;">Producto Debito</label><span class="datos">:</span>
                     <select  id="buscar_productoDebitoTD" title="Numero de cuenta" class="invisible_printTD VerificarTD blankSelectTD VerificarTD2 calTasaTD" style="width:640px;" onchange="" >

                     </select><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>
                 </td>

                        <tr>
                     <td class="datos" id="plazosNor">
                         <label for="buscar_Plazo_TDC" id="TAGbuscarPlazo" >Plazo</label><span class="datos">:</span>
                         <select  id="buscar_Plazo_TDC" title="Plazo" class="invisible_printTD VerificarTD blankSelectTD calTasaTD" style="width:136px;" onchange="" ></select><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>
                     </td>

                            <td class="datos" id="plazosPref">
                                <label for="buscar_Plazo_TDC" id="TAGbuscarPlazo2" >Plazo Preferencial</label><span class="datos">:</span>
                                <select  id="buscar_Plazo_TDC2" title="Plazo" class="invisible_printTD VerificarTD2 blankSelectTD calTasaTD" style="width:136px;" onchange="" ></select><label class="datos6">&nbsp;&nbsp;&nbsp;*   </label>
                            </td>


                     <td class="datos">
                         <label for="Monto_TD" id="TAGbuscarMontoTD" >Monto</label><span class="datos">:</span>
                         <input id="Monto_TD" type="text" maxlength="15" size="15" class="invisible_print VerificarTD blankTD VerificarTD2 calTasaTD" onchange="" title="Monto" onblur="this.value=formatCurrency(this.value,true,2,'.');"  onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'EM');" /><label class="datos6">*</label>
                         <label id="TAGtasaTD" class="datos blankTD" style="width:100px;margin-top: 25px;margin-left: 350px;margin-right: 10px;" >Tasa:</label><label id="valorTasaTimeDeposits" class="tasaTDMostrar blankTD"></label>
                     </td>

                     </tr>
             </table>
         </section>
         <section id="seleccDatosTDC" >

             <label id="TAGcamposobligatoriosTD" style="margin-left: 100px;color: #ff0000;">* Campos obligatorios</label>
        </section>

    </fieldset>
    </div>
    <fieldset id="TDCBotones" class="formulario_fieldset div_consulta right">
        <input  type="button" id="TAG_TD_cancelar" value="Cancelar" class="boton">
        <input  type="button" id="TAG_TD_aceptar" value="Aceptar" class="boton" style="width: 100px">
    </fieldset>
    <div id="TDdisclaimer"  style="display:none">
        <p align="justify"></p>
    </div>
   <!-- <fieldset id="set_tabla_consulta_historial_TD" class="invisible_print div_info" style="width:90%; margin-left:4%;margin-right:auto;float:none;">
        <legend>Time deposits aperturados</legend>
        <div id="div_tabla_consulta_historial_TDC_CL_BO" class="div_tabla_consulta" >
        </div>
    </fieldset>-->
    <fieldset class="formulario_fieldset div_consulta " >
        <legend id="TAGLegendTableTD">Solicitudes de apertura </legend>
        <div id="div_datos_apertura_td" class="div_tabla_consulta" >
        </div>
    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TAGcolocaciones_titulo" class="banner__title banner__title--modifier">
                TIME DEPOSITS / TIME DEPOSIT REQUEST
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome20" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>TIME DEPOSITS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>TIME DEPOSIT REQUEST</li>
            </ul>
            <p id="TAGDescripcionTD" class="banner__description banner__description--modifier">
                This option allows you to check the account statement of your Mutual Funds.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TAGcolocaciones_titulo" class="banner__title banner__title--modifier">
                TIME DEPOSITS / TIME DEPOSIT REQUEST
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome20" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TD_TIME_DEPOSITS">TIME DEPOSITS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TD_TIME_DEPOSIT_REQUEST">TIME DEPOSIT REQUEST</li>
            </ul>
            <p id="TAGDescripcionTD" class="banner__description banner__description--modifier">
                This option allows you to check the account statement of your Mutual Funds.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="aperturaTDForm" class="section">
        <div id="TDCForm" class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">

                </div>
            </div>
            <div class="section__content">
                <div id="seleccDebitoTDC" class="form-filter">
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="TAGbuscarTipo" class="form-filter__label" for="buscar_tipoTDC">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="buscar_tipoTDC"
                                        title="Tipo"
                                        class="select-section__select select-section__select--form invisible_print VerificarTD blankSelectTD VerificarTD2 calTasaTD"
                                        >
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="TAGproductoDebito" class="form-filter__label" for="buscar_productoDebitoTD">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="buscar_productoDebitoTD"
                                        title="Numero de cuenta"
                                        class="select-section__select select-section__select--form invisible_printTD VerificarTD blankSelectTD VerificarTD2 calTasaTD"
                                        >
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="plazosNor" class="form-filter__item">
                            <label id="TAGbuscarPlazo" class="form-filter__label" for="buscar_Plazo_TDC">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="buscar_Plazo_TDC"
                                        title="Plazo"
                                        class="select-section__select select-section__select--form invisible_printTD VerificarTD blankSelectTD calTasaTD"
                                        >
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div id="plazosPref" style="display: none" class="form-filter__item">
                            <label id="TAGbuscarPlazo2" class="form-filter__label" for="buscar_Plazo_TDC">Label</label>
                            <div class="form-filter__select select-section select-section--form">
                                <select id="buscar_Plazo_TDC2"
                                        title="Plazo"
                                        class="select-section__select select-section__select--form invisible_printTD VerificarTD blankSelectTD calTasaTD"
                                        >
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="" />
                            </div>
                        </div>
                        <div class="form-filter__item">
                            <label id="TAGbuscarMontoTD" class="form-filter__label" for="Monto_TD">Label</label>
                            <input id="Monto_TD"
                                   class="form-filter__input input input--form invisible_print VerificarTD blankTD VerificarTD2 calTasaTD"
                                   title="Monto"
                                   maxlength="15"
                                   size="15"
                                   onblur="this.value=formatCurrency(this.value,true,2,'.');"
                                   onkeypress="return onlyDigits_v2(event, this.value,true,true,true,',','.',2,'EM');"
                                   type="text"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="TAGtasaTD" class="form-filter__label">Label</label>
                            <span class="" id="valorTasaTimeDeposits"></span>
                        </div>
                    </div>
                    <div class="form-filter__between">
                        <span id="TAGcamposobligatoriosTD" class="mandatory-fields"></span>
                        <div class="form-filter__buttons">
                            <input type="button" id="TAG_TD_cancelar" value="Cancel" class="button button--white">
                            <input type="button" id="TAG_TD_aceptar" value="Send Request" class="button">
                        </div>
                    </div>
                </div>
                <div class="table">
                    <div class="table__titles"><span class="table__title" id="TAGLegendTableTD">Time Deposit Request</span></div>
                    <table id="resumenAperturaTD" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
    <div id="TDdisclaimer"  style="display:none">
        <p align="justify"></p>
    </div>
</main>
<%--<jsp:include page="../../footer.jsp"/>--%>


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
    <h1>Programar Bloqueo</h1>
</div>

<div id="div_bloqueoTarjetas_BO">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
        <tr>
            <td class="datos" width="6%"><span class="label_numero_cuenta_TDC_CL">Nº Cartera</span><span>:</span></td>
            <td class="datos">
                <input type="text" id="carteraFiltroTarjetasBO" title="N° Cartera" class="inputFormulario"  maxlength="16" >
				 <input type="checkbox" value="1" class="datos checkFormulario" id="carteraExacto_TDC_CL" style="width: 20px"><label class="datos"> Buscar por cartera exacta?</label>
            </td>
            <td class="datos" width="12%">

            </td>
            <td class="datos" align="left">
               <input type="button" id="buscarBloqueoTarjetaBO" value="Buscar" class="botonGrandeDerecha"> &nbsp;
               <input type="button" id="limpiarBloqueoTarjetaBO" value="Limpiar" class="botonGrandeDerecha" style="margin-right:4px">
            </td>
        </tr>
    </table>
</fieldset>
<fieldset class="div_consulta oculto"  id="div_result_bloqueo_bo">
    <div>
       <table width="100%">
           <tr>
               <td class="datos" width="55%">
                   <select  id="numero_cuenta_TDC_CL_BO" title="Numero de Tarjeta" style="width:320px; margin-right:4px" class="chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla" onchange="cargarDatosBloqueo(this.value)">
                   </select>
                   <span id="tag_cliente_bloqueo_bo" style="padding-right: 20px;"></span>
               </td>
               <td class="datos" width="25%">
                   <span>Estatus</span>
                   <span id="tag_estatus_bloqueo_bo"></span>
               </td>
               <td width="20%">
                   <input type="button" id="crear_regla_bo" value="Crear Regla" class="botonGrandeDerecha">
               </td>
           </tr>
           <tr>
               <td colspan="3" class="datos">
                   <span style="padding-right: 20px;">Desde:</span><input title="Fecha desde" type="text" id="fec_desde_CL_bo" class="requeridoCrearRegla"/>
                   <span style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" title="Fecha hasta" id="fec_hasta_CL_bo" class="requeridoCrearRegla"/>
               </td>
           </tr>
       </table>



    </div>
</fieldset>
    <fieldset id="set_tabla_consulta_reglas_activas_TDC_CL_BO" class="div_consulta oculto">
        <legend>Activaciones programadas</legend>
        <div id="div_tabla_consulta_reglas_activas_TDC_CL_BO" class="div_tabla_consulta">
        </div>
    </fieldset>
    <fieldset id="set_tabla_consulta_historial_TDC_CL_BO" class="div_consulta oculto">
        <legend>Historial de activaciones programadas</legend>
        <div id="div_tabla_consulta_historial_TDC_CL_BO" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>

<div id="div_creditCard_CL_Edit_BO" style="display: none">

    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tbody>
            <tr>
                <td align="left" class="datos" width="11%"> <input type="button" id="edit_regla_bo" value="Accept" class="boton"></td>
                <td class="datos">

                </td>
                <td class="datos" width="12%">
                    &lt;%&ndash;<span class="label_estatus_TDC_CL" id="TAGEstatusTarjeta" style="padding-left: 20px">Estatus</span><span>:</span>&ndash;%&gt;
                </td>
                <td class="datos" align="left">

                </td>
                <td class="datos" align="left"  width="8%">
                    <input type="button" class="botonEDOCuenta" value="Regresar" id="volver_editar_regla_bo">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta" >
        <div id="div_datos_regla_TDC_CL_Edit_BO">
            <table>
                <tr>
                    <td  class="datos">
                        <span class="label_numero_cuenta_TDC_CL" id="TAGNumeroTarjeta_edit_bo">N&uacute;mero de Tarjeta</span><span>:</span>
                        <span class="label_numero_cuenta_TDC_CL" id="numero_cuenta_TDC_CL_edit_bo"></span>
                    </td>
                </tr>
                <tr>
                    <td  class="datos">
                        <span id="tag_fec_desde_CL_Edit_bo" style="padding-right: 20px;">Desde:</span><input type="text" id="fec_desde_CL_Edit_bo" class="requeridoCrearReglaEdit"/>
                        <span id="tag_fec_hasta_CL_Edit_bo" style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" id="fec_hasta_CL_Edit_bo" class="requeridoCrearReglaEdit" />
                        <span class="label_estatus_TDC_CL_bo" id="TAGEstatusTarjetaEdit" style="padding-left: 20px">Estatus</span><span>:&nbsp;</span></span><label id="tag_status_CL_Edit" class="datos" style="padding-right: 20px;"></label>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
</div>

<div id="div_noInfo_CL_creditCard_BO" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_TDC3">No posee tarjetas de cr&eacute;dito que consultar </span>
    </fieldset>
</div>

<div id="poppup_message_bo" class="oculto">
    <div>
        <table SUMMARY='tabla' cellpadding="4" cellspacing="1" class="tabla_resumen3">
            <tbody>
            <tr>
                <td colspan="2" style="text-align:center;">
                    <span class="datos negrita2">¿Está seguro que desea modificar el periodo de activación de la tarjeta de crédito?</span>
                    <br /><br />
                </td>
            </tr>
            <tr class="confirmButtons">
                <td   width="50%" align="center">
                    <input  type="button" id="btn_confirm_si_bo" class="dinamicButton" value="Aceptar">
                </td>
                <td   width="50%" align="center">
                    <input  type="button" id="btn_confirm_no_bo" class="dinamicButton" value="Cancelar">
                </td>
            </tr>
            </tbody>
        </table>
    </div>
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
            <h2 class="banner__title banner__title--modifier">
                Programar Bloqueo
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome43" href="Home">INICIO</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>OPERACIONES</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>PROGRAMAR BLOQUEO</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 class="banner__title banner__title--modifier">
                Programar Bloqueo
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome43" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>OPERACIONES</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>PROGRAMAR BLOQUEO</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_bloqueoTarjetas_BO" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <div class="section__form">
                        <div class="section__field">
                            <label for="carteraFiltroTarjetasBO">Nº Cartera</label>
                            <input id="carteraFiltroTarjetasBO"
                                   title="N° Cartera"
                                   class="input input--form inputFormulario"
                                   type="text"
                                   maxlength="16"
                            />
                        </div>
                        <div class="section__checkbox checkbox-container">
                            <label class="checkbox-container__label" for="carteraExacto_TDC_CL">Buscar por cartera exacta?</label>
                            <input id="carteraExacto_TDC_CL" value="1" class="checkbox-container__check checkFormulario" type="checkbox" />
                        </div>
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div></div>
                    <div class="section__buttons">
                        <input type="button" id="limpiarBloqueoTarjetaBO" value="Limpiar" class="section__button button button--white">
                        <input type="button" id="buscarBloqueoTarjetaBO" value="Buscar" class="section__button button">
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div id="div_result_bloqueo_bo" class="form-filter">
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <select id="numero_cuenta_TDC_CL_BO"
                                    title="Numero de Tarjeta"
                                    class="select-section__select select-section__select--form chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla"
                                    onchange="cargarDatosBloqueo(this.value)">
                            </select>
                        </div>
                        <div class="form-filter__item">
                            <span id="tag_cliente_bloqueo_bo" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label">Estatus</label>
                            <span id="tag_estatus_bloqueo_bo" class="form-filter__value"></span>
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="fec_desde_CL_bo">Desde:</label>
                            <input id="fec_desde_CL_bo"
                                   title="Fecha desde"
                                   class="form-filter__input input input--form requeridoCrearRegla"
                                   type="text"
                                   placeholder="dd/mm/yyyy"
                            />
                        </div>
                        <div class="form-filter__item">
                            <label class="form-filter__label" for="fec_hasta_CL_bo">Hasta:</label>
                            <input id="fec_hasta_CL_bo"
                                   title="Fecha hasta"
                                   class="form-filter__input input input--form requeridoCrearRegla"
                                   placeholder="dd/mm/yyyy"
                                   type="text"/>
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input type="button" id="crear_regla_bo" value="Crear Regla" class="button">
                    </div>
                </div>
                <div id="set_tabla_consulta_reglas_activas_TDC_CL_BO" class="table oculto">
                    <div class="table__titles">
                        <span class="table__title">Activaciones programadas</span>
                    </div>
                    <div id="div_tabla_consulta_reglas_activas_TDC_CL_BO" class="div_tabla_consultarUsuarios_FC">

                    </div>
                </div>
                <div id="set_tabla_consulta_historial_TDC_CL_BO" class="table oculto">
                    <div class="table__titles">
                        <span class="table__title">Historial de activaciones programadas</span>
                    </div>
                    <div id="div_tabla_consulta_historial_TDC_CL_BO" class="div_tabla_consultarUsuarios_FC">

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_creditCard_CL_Edit_BO" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                <div class="section__header">
                    <div class="section__info">
                        <span id="TAGNumeroTarjeta_edit_bo">Número de Tarjeta</span>
                        <span id="numero_cuenta_TDC_CL_edit_bo"></span>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div id="div_datos_regla_TDC_CL_Edit_BO" class="form-filter">
                    <div class="form-filter__grid">
                        <div class="form-filter__item">
                            <label id="tag_fec_desde_CL_Edit_bo" class="form-filter__label" for="fec_desde_CL_Edit_bo">Desde:</label>
                            <input id="fec_desde_CL_Edit_bo"
                                   class="form-filter__input input input--form requeridoCrearReglaEdit"
                                   type="text"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="tag_fec_hasta_CL_Edit_bo" class="form-filter__label" for="fec_hasta_CL_Edit_bo">Hasta:</label>
                            <input id="fec_hasta_CL_Edit_bo"
                                   class="form-filter__input input input--form requeridoCrearReglaEdit"
                                   type="text"/>
                        </div>
                        <div class="form-filter__item">
                            <label id="TAGEstatusTarjetaEdit" class="form-filter__label">Estatus</label>
                            <span id="tag_status_CL_Edit" class="form-filter__value"></span>
                        </div>
                    </div>
                    <div class="form-filter__buttons">
                        <input type="button" class="button button--white" value="Regresar" id="volver_editar_regla_bo">
                        <input type="button" id="edit_regla_bo" value="Accept" class="button">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_noInfo_CL_creditCard_BO"  style="display: none" class="section">
        <div class="section__container container">
            <div class="section__content">
                <span id="noInfo_TDC3">No posee tarjetas de cr&eacute;dito que consultar</span>
            </div>
        </div>
    </div>
    <div id="poppup_message_bo" class="oculto poppup">
        <p class="poppup_saveTemplate__text">¿Está seguro que desea modificar el periodo de activación de la tarjeta de
            crédito?</p>
        <div class="poppup_saveTemplate__buttons">
            <input type="button" id="btn_confirm_no_bo" class="button button--white" value="Cancelar">
            <input type="button" id="btn_confirm_si_bo" class="button" value="Aceptar">
        </div>
    </div>
</main>

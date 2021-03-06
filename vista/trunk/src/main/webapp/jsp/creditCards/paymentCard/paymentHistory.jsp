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
    <h1 id="TDC_titulo_historico_pago">Tarjetas de Cr&eacute;dito / Pago de Tarjetas</h1>
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
                    <span id="TAG_INFO_TDC_HISTORICO_PAGOS_FI" class="datosInfo">
                        This option allows you to view the details of all payments made through VBT Online.
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
                <td class="datos" width="12%"><span class="label_numero_cuenta_TDC_CL" id="TAGNumeroTarjeta_Pago2">N&uacute;mero de Tarjeta</span><span>:</span></td>
                <td class="datos">
                    <select  id="numero_cuenta_TDC_Pago2" title="Numero de Tarjeta" style="width:320px;margin-right:4px" class="chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla" onchange="cargarHistorialPagos(this.value)">
                    </select>
                    <span id="numero_cuenta_TDC_Pago_select2" class="visible_print"></span>
                    <span id="tag_cliente_bloqueo_pago2" style="padding-left: 14px;"></span>

                </td>
                <td class="datos" align="left">
                    <input type="button" id="clear_pagos2" value="Limpiar" class="botonGrandeDerecha">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>

    <fieldset class="div_consulta">
        <legend id="pagos_historial">Historial de Pagos</legend>
        <div id="filtroPagosTDC">
            <span id="tag_fec_desde_Pago" style="padding-right: 20px;">Desde:</span><input type="text" id="fec_desde_Pago" class="requeridoConsultarPagos"/>
            <span id="tag_fec_hasta_Pago" style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" id="fec_hasta_Pago" class="requeridoConsultarPagos"/>
            <input type="button" id="consultar_pagos" value="Buscar" class="botonGrandeDerecha">
        </div>
        <div id="div_tabla_consulta_historial_TDC_Pagos" class="div_tabla_consulta" style="padding-top:20px;">
            <table id="tabla_consulta_historial_TDC_Pagos" width="100%">
            <thead>
                <tr>
                    <th></th>
                    <th></th>
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
                    <td></td>
                    <td></td>
                </tr>
            </tbody>
            </table>
        </div>
    <br>
    </fieldset>
</div>--%>
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
    <h1 id="TDC_titulo_bloquear">Tarjetas de Cr&eacute;dito / Bloqueo de Tarjeta</h1>
</div>

<div id="div_creditCard_CL_alertaSeguridad" >

    <div id="alertaSeguridadTDC">
        <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
            <tr height="10%">
                <td align="center">
                    <span id="comun_TAGSubTituloAlertaTDCV_5">AVISOS DE SEGURIDAD PARA EVITAR FRAUDES.<P>POR FAVOR LEA CON DETENIMIENTO LA SIGUIENTE INFORMACI&Oacute;N</P></span>
                </td>
            </tr>
            <tr>
                <td>
                    <TABLE border="0" width="85%" align="center" cellpadding="0" cellspacing="0">
                        <tr>
                            <td>
                                <TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
                                    <tr><td  height="16" align="center" colspan="2"></td></tr>
                                    <tr>
                                        <td  align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV1_4">Firme su tarjeta en tinta indeleble tan pronto la reciba y destruya las tarjetas viejas cuando caduquen.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV2_4">Nunca permita que otras personas utilicen su tarjeta. Es suya y solo suya.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV3_4">Al realizar una compra, aseg&uacute;rese que el vendedor procese la transacci&oacute;n en su presencia.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV4_4">Revise su tarjeta cuando le es devuelta por el cajero para asegurar que es la suya y que no ha sido adulterada en ning&uacute;n modo.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV5_4">Siempre conserve sus recibos para reconciliarlos con su estado de cuenta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV6_4">Notifique al emisor de la tarjeta de cualquier cambio de direcci&oacute;n, para evitar que nuevas tarjetas y/o estados de cuenta sean enviados a la vieja direcci&oacute;n.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV7_4">En caso de robo o p&eacute;rdida de la tarjeta, notif&iacute;quelo al emisor de inmediato. Siempre mantenga el n&uacute;mero de tel&eacute;fono del emisor (Bank & Trust, Ltd.) a la mano.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV8_4">No entregue ninguna informaci&oacute;n de la tarjeta a personas que lo soliciten por tel&eacute;fono y/o correo y/o correo electr&oacute;nico.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV9_4">De hacer compras en l&iacute;nea, aseg&uacute;rese de realizar su compra a trav&eacute;s de un sitio web Secure Server Transaction (mejor aun si ha sido certificado por VISA).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV10_4">No firme vouchers de pago en blanco, haga una l&iacute;nea sobre el monto total y destruya el carb&oacute;n y recibos anulados.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV11_4">Evite los sitios web de apuestas, casinos y desconocidos.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV12_4">Si recibe confirmaciones de compra desconocidas v&iacute;a SMS (tel&eacute;fono celular) y/o correo electr&oacute;acute;nicos del Banco, por favor cont&aacute;ute;ctenos de inmediato (Atenci&oacute;n al Cliente, Ejecutivo de Ventas).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV13_4">Est&eacute; atento a cualquier correo electr&oacute;nico que solicite informaci&oacute;n personal &minus;tales como claves, PINs, o su n&uacute;mero de identificaci&oacute;n- o lo dirija a sitios web que soliciten dicha informaci&oacute;n. Estos mensajes pueden tratarse de un enga&ntilde;o, falsificando una direcci&oacute;n de correo electr&oacute;nico para aparentar ser otra direcci&oacute;n y negocio leg&iacute;timos. Recuerde nunca enviar su informaci&oacute;n personal v&iacute;a correo electr&oacute;nico. Si recibe un correo electr&oacute;nico enga&ntilde;oso, por favor informe a VISA acerca de los detalles de este correo electr&oacute;nico ilegal.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV14_4">Visa no lo contactar&aacute; o enviar&aacute; correos electr&oacute;nicos solicitando la informaci&oacute;n personal de su cuenta. Los centros de llamadas de VISA no inician llamadas de tele-mercadeo. Los clientes no deben responder a correos electr&oacute;nicos ni llamadas telef&oacute;nicas solicitando informaci&oacute;n personal de su tarjeta y se les sugiere reportar inmediatamente tal situaci&oacute;n a las autoridades locales as&iacute; como a la instituci&oacute;n financiera emisora de la tarjeta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV15_4">El tarjetahabiente ser&aacute; responsable ante El Banco por no reportar consumos en la tarjeta no autorizados. Tan pronto como el tarjetahabiente notifique debidamente al Banco, la responsabilidad de los cargos subsiguientes no autorizados y avances de efectivo podr&aacute; cesar.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV16_4">El tarjetahabiente es el &uacute;nico responsable por el correcto uso de la tarjeta. El tarjetahabiente asumir&aacute; las responsabilidades por todos los cargos y comisiones derivados del uso correcto de la tarjeta. Cualquier uso incorrecto de la tarjeta ser&aacute; de la sola responsabilidad del tarjetahabiente.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV17_4">El uso de su tarjeta por terceras personas no autorizadas (e.g. debido a p&eacute;rdida, robo, falsificaci&oacute;n o uso de la data de la tarjeta mientras el tarjetahabiente utiliza la tarjeta en l&iacute;nea o alg&uacute;n medio digital) es el mayor riesgo que el tarjetahabiente enfrenta al utilizar la tarjeta. La apropiaci&oacute;n de la tarjeta por terceras personas  facilitando el uso incorrecto de la tarjeta podr&aacute; causar da&ntilde;o a una o mas partes participantes del sistema de tarjetas de cr&eacute;dito (e.g. usuarios, establecimientos participantes y el emisor). El reparo de dicho da&ntilde;o surgido del incumplimiento de su obligaci&oacute;n/responsabilidad como tarjetahabiente de prevenir dichos da&ntilde;os puede resultar en compromisos monetarios de usted como tarjetahabiente.</span>
                                        </td>
                                    </tr>
                                    <tr><td class="V8NNegroGris" height="16" align="center" colspan="2"></td></tr>
                                </TABLE>
                            </td>
                        </tr>
                    </TABLE>
                </td>
            </tr>
            <tr height="30">
                <td align="center" class="A7NVerde">
                    <input type="button" class="botonGrande" id="comun_TAGTitleContinuarTDC3" value="Click para Continuar">
                </td>
            </tr>
        </TABLE>
        <input type="hidden" id="pantalla" value="">
    </div>
</div>


<div id="div_creditCard_CL" style="display: none">
    <fieldset class="invisible_print div_info" style="width:99%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left" style="text-align:justify;">
                    <span id="TAG_INFO_CREDIT_CARDS" class="datosInfo">Esta opci??n le permite programar la activaci??n de su Tarjeta de Cr??dito VBT Visa Gold durante un per??odo de tiempo determinado,  una vez finalice este per??odo su tarjeta ser?? nuevamente bloqueada. Para programar la activaci??n de su tarjeta debe ingresar  la fecha de inicio y la fecha de culminaci??n  del per??odo en que requiera utilizarla y presionar el bot??n Programar<br>Nota importante:<br><li>Planifique  los periodos de activaci??n de su tarjeta con antelaci??n, tome en cuenta que solo podr?? registrar per??odos de activaci??n con una fecha de inicio posterior a dos (2) d??as h??biles de la fecha actual.</li><li>Puede modificar un periodo de activaci??n hasta un d??a antes de la fecha de culminaci??n programada.</li><li>Puede eliminar los per??odos de activaci??n que se encuentren pendientes por ejecutar.</li></span>
                </td>
            </tr>
            </tbody>
        </table>
     </fieldset>
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0">
        <tbody>
        <tr>
            <td class="datos" width="20%"><span class="label_numero_cuenta_TDC_CL" id="TAGNumeroTarjeta">N&uacute;mero de Tarjeta</span><span>:</span></td>
            <td class="datos">
                <select  id="numero_cuenta_TDC_CL" title="Numero de Tarjeta" style="width:320px;margin-right:4px" class="chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla" onchange="cargarEstatusCL(this.value)">
                </select>
                &lt;%&ndash;<input type="password" id="numero_cuenta_TDC_CL" title="Numero de Tarjeta" class="requerido_TDC_CL invisible_print" />&ndash;%&gt;
                <span id="numero_cuenta_TDC_CL_select" class="visible_print"></span>
                <span id="tag_cliente_bloqueo_cl" style="padding-left: 14px;"></span>
            </td>
            <td class="datos" width="12%">
                &lt;%&ndash;<span class="label_estatus_TDC_CL" id="TAGEstatusTarjeta" style="padding-left: 20px">Estatus</span><span>:</span>&ndash;%&gt;
            </td>
            <td class="datos" align="left">
                &lt;%&ndash;<b><span id="estatus_TDC_CL"></span></b>&ndash;%&gt;
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<fieldset class="div_consulta" >
    <div id="div_datos_regla_TDC_CL">
        <span id="tag_fec_desde_CL" style="padding-right: 20px;">Desde:</span><input type="text" id="fec_desde_CL" class="requeridoCrearRegla"/>
        <span id="tag_fec_hasta_CL" style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" id="fec_hasta_CL" class="requeridoCrearRegla"/>
        <input type="button" id="crear_regla" value="Crear Regla" class="botonGrandeDerecha">
    </div>
    <div id="div_datos_regla_mensaje_TDC_CL" class="oculto">
        <span id="tag_datos_regla_mensaje_CL">Usted dispone de una regla en estatus Activa o En Proceso.</span>
    </div>
</fieldset>
    <fieldset id="set_tabla_consulta_reglas_activas_TDC_CL" class="div_consulta oculto">
        <legend id="reglas_activas">Reglas Activas</legend>
        <div id="div_tabla_consulta_reglas_activas_TDC_CL" class="div_tabla_consulta">
        </div>
    </fieldset>
    &lt;%&ndash;<fieldset id="set_tabla_consulta_reglas_corrientes_TDC_CL" class="div_consulta oculto">&ndash;%&gt;
        &lt;%&ndash;<legend id="reglas_corrientes">Reglas En Proceso</legend>&ndash;%&gt;
        &lt;%&ndash;<div id="div_tabla_consulta_reglas_corrientes_TDC_CL" class="div_tabla_consulta">&ndash;%&gt;
        &lt;%&ndash;</div>&ndash;%&gt;
    &lt;%&ndash;</fieldset>&ndash;%&gt;
    <fieldset id="set_tabla_consulta_historial_TDC_CL" class="div_consulta oculto">
        <legend id="reglas_historial">Historial de Reglas</legend>
        <div id="div_tabla_consulta_historial_TDC_CL" class="div_tabla_consulta">
        </div>
    </fieldset>
</div>


<div id="div_creditCard_CL_Edit" style="display: none">
    <fieldset class="invisible_print div_info" style="width:99%; margin-left:auto;margin-right:auto;float:none;">
        <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
            <tbody>
            <tr>
                <td width="4%">
                    <img src="resources/images/iconInfo.png">
                </td>
                <td align="left" style="text-align:justify;">
                    <span id="TAG_INFO_CREDIT_CARDS_EDIT" class="datosInfo">Esta opci??n le permite programar la activaci??n de su Tarjeta de Cr??dito VBT Visa Gold durante un per??odo de tiempo determinado,  una vez finalice este per??odo su tarjeta ser?? nuevamente bloqueada. Para programar la activaci??n de su tarjeta debe ingresar  la fecha de inicio y la fecha de culminaci??n  del per??odo en que requiera utilizarla y presionar el bot??n Programar<br>Nota importante:<br><li>Planifique  los periodos de activaci??n de su tarjeta con antelaci??n, tome en cuenta que solo podr?? registrar per??odos de activaci??n con una fecha de inicio posterior a dos (2) d??as h??biles de la fecha actual.</li><li>Puede modificar un periodo de activaci??n hasta un d??a antes de la fecha de culminaci??n programada.</li><li>Puede eliminar los per??odos de activaci??n que se encuentren pendientes por ejecutar.</li></span>
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%">
            <tbody>
            <tr>
                <td align="left" class="datos" width="11%"> <input type="button" id="edit_regla" value="Accept" class="boton"></td>
                <td class="datos">

                </td>
                <td class="datos" width="12%">
                    &lt;%&ndash;<span class="label_estatus_TDC_CL" id="TAGEstatusTarjeta" style="padding-left: 20px">Estatus</span><span>:</span>&ndash;%&gt;
                </td>
                <td class="datos" align="left">

                </td>
                <td class="datos" align="left"  width="8%">
                    <input type="button" class="botonEDOCuenta" value="Regresar" id="volver_editar_regla">
                </td>
            </tr>
            </tbody>
        </table>
    </fieldset>
    <fieldset class="div_consulta" >
        <div id="div_datos_regla_TDC_CL_Edit">
            <table>
                <tr>
                    <td  class="datos">
                        <span class="label_numero_cuenta_TDC_CL" id="TAGNumeroTarjeta_edit">N&uacute;mero de Tarjeta</span><span>:</span>
                        <span class="label_numero_cuenta_TDC_CL" id="numero_cuenta_TDC_CL_edit"></span>
                    </td>
                </tr>
                <tr>
                    <td  class="datos">
                        <span id="tag_fec_desde_CL_Edit" style="padding-right: 20px;">Desde:</span><input type="text" id="fec_desde_CL_Edit" class="requeridoCrearReglaEdit"/>
                        <span id="tag_fec_hasta_CL_Edit" style="padding-right: 20px; padding-left: 20px;">Hasta:</span><input type="text" id="fec_hasta_CL_Edit" class="requeridoCrearReglaEdit" />
                        <span class="label_estatus_TDC_CL" id="TAGEstatusTarjetaEdit" style="padding-left: 20px">Estatus</span><span>:&nbsp;</span></span><label id="tag_status_CL_Edit" class="datos" style="padding-right: 20px;"></label>
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>
</div>

<div id="div_noInfo_CL_creditCard" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_TDC3">No posee tarjetas de cr&eacute;dito que consultar </span>
    </fieldset>
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
            <h2 id="TDC_titulo_historico_pago" class="banner__title banner__title--modifier">
                Credit Cards / Credit Card Payment History
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome15" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>CREDIT CARDS</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>CREDIT CARD PAYMENT HISTORY</li>
            </ul>
            <p id="TAG_INFO_TDC_HISTORICO_PAGOS_FI" class="banner__description banner__description--modifier">
                This option allows you to view the details of all payments made through VBT Online.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TDC_titulo_historico_pago" class="banner__title banner__title--modifier">
                Credit Cards / Credit Card Payment History
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome15" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CREDIT_CARDS_CREDIT_CARD_PAYMENT_HISTORY">CREDIT CARDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CREDIT_CARD_PAYMENT_HISTORY">CREDIT CARD PAYMENT HISTORY</li>
            </ul>
            <p id="TAG_INFO_TDC_HISTORICO_PAGOS_FI" class="banner__description banner__description--modifier">
                This option allows you to view the details of all payments made through VBT Online.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_creditCard_pagos" class="section">
        <div id="filtroPagosTDC" class="section__container container">
            <div id="fechas_Transferencias" class="section__top">
                <div class="section__header">
                    <div class="section__field">
                        <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt="" />
                        <label id="TAGNumeroTarjeta_Pago2" for="numero_cuenta_TDC_Pago2">LABEL</label>
                        <div class="select-section select-section--form">
                            <select id="numero_cuenta_TDC_Pago2"
                                    title="Numero de Tarjeta"
                                    class="select-section__select select-section__select--form chosen-select requerido_TDC_CL invisible_print requeridoCrearRegla requeridoConsultarPagos"
                                    onchange="cargarHistorialPagos(this.value)">
                            </select>
                            <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                        </div>
                        <span id="tag_cliente_bloqueo_pago2"></span>
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <div class="section__form">
                        <div class="section__field">
                            <label id="tag_fec_desde_Pago" class="form-filter__label" for="fec_desde_Pago">Label</label>
                            <input id="fec_desde_Pago"
                                   class="form-filter__input input input--form requeridoConsultarPagos"
                                   type="text"/>
                        </div>
                       <div class="section__field">
                            <label id="tag_fec_hasta_Pago" class="form-filter__label" for="fec_hasta_Pago">Label</label>
                            <input id="fec_hasta_Pago"
                                class="form-filter__input input input--form requeridoConsultarPagos"
                                type="text"/>
                        </div>
                    </div>
                    <div class="section__buttons">
                        <input type="button" id="clear_pagos2" value="Clear" class="button button--white">
                        <input type="button" id="consultar_pagos" value="Search" class="button">
                    </div>
                </div>
            </div>
            
    
            <div class="section__content">
                <div  class="table">
                    <table id="tabla_consulta_historial_TDC_Pagos" class="table__content">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>

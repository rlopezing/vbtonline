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
    <h1 id="TDC_titulo_facturar">Tarjetas de Cr&eacute;dito / Movimientos por facturar</h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info oculto" id="TAG_INFO_FACTURAR">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_POR_FACTURAR">This option provides information about the pending billing transactions of your credit card.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_creditCard_ITT_alertaSeguridad" >

    <div id="alertaSeguridadTDC">
        <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
            <tr height="10%">
                <td align="center">
                    <span id="comun_TAGSubTituloAlertaTDCV_3">AVISOS DE SEGURIDAD PARA EVITAR FRAUDES.<P>POR FAVOR LEA CON DETENIMIENTO LA SIGUIENTE INFORMACI&Oacute;N</P></span>
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
                                            <span id="comun_TAGAlertaTDCV1_3">Firme su tarjeta en tinta indeleble tan pronto la reciba y destruya las tarjetas viejas cuando caduquen.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV2_3">Nunca permita que otras personas utilicen su tarjeta. Es suya y solo suya.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV3_3">Al realizar una compra, aseg&uacute;rese que el vendedor procese la transacci&oacute;n en su presencia.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV4_3">Revise su tarjeta cuando le es devuelta por el cajero para asegurar que es la suya y que no ha sido adulterada en ning&uacute;n modo.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV5_3">Siempre conserve sus recibos para reconciliarlos con su estado de cuenta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30"  valign="top">
                                            <span id="comun_TAGAlertaTDCV6_3">Notifique al emisor de la tarjeta de cualquier cambio de direcci&oacute;n, para evitar que nuevas tarjetas y/o estados de cuenta sean enviados a la vieja direcci&oacute;n.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV7_3">En caso de robo o p&eacute;rdida de la tarjeta, notif&iacute;quelo al emisor de inmediato. Siempre mantenga el n&uacute;mero de tel&eacute;fono del emisor (Bank & Trust, Ltd.) a la mano.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV8_3">No entregue ninguna informaci&oacute;n de la tarjeta a personas que lo soliciten por tel&eacute;fono y/o correo y/o correo electr&oacute;nico.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV9_3">De hacer compras en l&iacute;nea, aseg&uacute;rese de realizar su compra a trav&eacute;s de un sitio web Secure Server Transaction (mejor aun si ha sido certificado por VISA).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV10_3">No firme vouchers de pago en blanco, haga una l&iacute;nea sobre el monto total y destruya el carb&oacute;n y recibos anulados.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV11_3">Evite los sitios web de apuestas, casinos y desconocidos.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV12_3">Si recibe confirmaciones de compra desconocidas v&iacute;a SMS (tel&eacute;fono celular) y/o correo electr&oacute;acute;nicos del Banco, por favor cont&aacute;ute;ctenos de inmediato (Atenci&oacute;n al Cliente, Ejecutivo de Ventas).</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV13_3">Est&eacute; atento a cualquier correo electr&oacute;nico que solicite informaci&oacute;n personal &minus;tales como claves, PINs, o su n&uacute;mero de identificaci&oacute;n- o lo dirija a sitios web que soliciten dicha informaci&oacute;n. Estos mensajes pueden tratarse de un enga&ntilde;o, falsificando una direcci&oacute;n de correo electr&oacute;nico para aparentar ser otra direcci&oacute;n y negocio leg&iacute;timos. Recuerde nunca enviar su informaci&oacute;n personal v&iacute;a correo electr&oacute;nico. Si recibe un correo electr&oacute;nico enga&ntilde;oso, por favor informe a VISA acerca de los detalles de este correo electr&oacute;nico ilegal.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV14_3">Visa no lo contactar&aacute; o enviar&aacute; correos electr&oacute;nicos solicitando la informaci&oacute;n personal de su cuenta. Los centros de llamadas de VISA no inician llamadas de tele-mercadeo. Los clientes no deben responder a correos electr&oacute;nicos ni llamadas telef&oacute;nicas solicitando informaci&oacute;n personal de su tarjeta y se les sugiere reportar inmediatamente tal situaci&oacute;n a las autoridades locales as&iacute; como a la instituci&oacute;n financiera emisora de la tarjeta.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV15_3">El tarjetahabiente ser&aacute; responsable ante El Banco por no reportar consumos en la tarjeta no autorizados. Tan pronto como el tarjetahabiente notifique debidamente al Banco, la responsabilidad de los cargos subsiguientes no autorizados y avances de efectivo podr&aacute; cesar.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV16_3">El tarjetahabiente es el &uacute;nico responsable por el correcto uso de la tarjeta. El tarjetahabiente asumir&aacute; las responsabilidades por todos los cargos y comisiones derivados del uso correcto de la tarjeta. Cualquier uso incorrecto de la tarjeta ser&aacute; de la sola responsabilidad del tarjetahabiente.</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="V8NNegroGris" align="left" width="20" valign="top">
                                            <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                        </td>
                                        <td class="V8NNegroGris" height="30" valign="top">
                                            <span id="comun_TAGAlertaTDCV17_3">El uso de su tarjeta por terceras personas no autorizadas (e.g. debido a p&eacute;rdida, robo, falsificaci&oacute;n o uso de la data de la tarjeta mientras el tarjetahabiente utiliza la tarjeta en l&iacute;nea o alg&uacute;n medio digital) es el mayor riesgo que el tarjetahabiente enfrenta al utilizar la tarjeta. La apropiaci&oacute;n de la tarjeta por terceras personas  facilitando el uso incorrecto de la tarjeta podr&aacute; causar da&ntilde;o a una o mas partes participantes del sistema de tarjetas de cr&eacute;dito (e.g. usuarios, establecimientos participantes y el emisor). El reparo de dicho da&ntilde;o surgido del incumplimiento de su obligaci&oacute;n/responsabilidad como tarjetahabiente de prevenir dichos da&ntilde;os puede resultar en compromisos monetarios de usted como tarjetahabiente.</span>
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
                    &lt;%&ndash;<A class="" HREF="" title="Click para continuar" target="" id="TDC_continuar">Click para continuar >></A>&ndash;%&gt;
                    <input type="button" class="botonGrande" id="comun_TAGTitleContinuarTDC" value="Click para Continuar">
                </td>
            </tr>
        </TABLE>
        <input type="hidden" id="pantalla" value="">
    </div>
</div>
<div id="div_creditCard_ITT" style="display: none">
<fieldset class="div_consulta">
    <table SUMMARY='tabla' cellspacing="0" cellpadding="0">
        <tbody>
        <tr>
            <td class="datos"width="12%"><span class="label_numero_cuenta_TDC_ITT " id="tdcmovimientosxfacturar_TAGNumeroTarjeta">N&uacute;mero de Tarjeta </span><span>:</span></td>
            <td class="datos">
                <table>
                    <tr>
                        <td> <select  id="numero_cuenta_TDC_ITT" title="Numero de Tarjeta" class="requerido_TDC_ITT invisible_print" onchange="cargarSaldosITT(this.value)" >

                        </select>
                            <span id="numero_cuenta_TDC_ITT_select" class="visible_print"></span></td>
                        <td>&nbsp;&nbsp;</td>
                    </tr>
                </table>


            </td>
            <td  class="botones_formulario" width="45%">
                &lt;%&ndash;<input type="button" id="estado_cuenta_consultar" value="Consultar" class="botonEDOCuenta">&ndash;%&gt;
                <a id="transit_imprimir" onclick="print_MOVIMIENTOS_FACTURAR();" TITLE="imprimir"> <img class="imprimir"  src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'></a>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
    <fieldset class="div_consulta" id="div_creditCard_ITTDetalle"  style="display:none"  >
        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" style="width: 80%; padding-left:20px;">
            <tbody>
            <tr>
                <td class="datos8" width="20%">
                    <span id="label_nombre_tdc"></span><span>:</span>
                </td>
                <td class="datos9" width="40%">
                    <span id="valor_nombre_tdc">  </span>
                </td>
                <td width="5%">&nbsp;</td>
                <td class="datos8" width="20%">
                    <span id="label_email_tdc"></span><span>:</span>
                </td>
                <td class="datos9" width="20%">
                    <span id="valor_email_tdc">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos8">
                    <span id="label_direccion_tdc"></span><span>:</span>
                </td>
                <td class="datos9">
                    <span id="valor_direccion_tdc">  </span>
                </td>
                <td width="6%">&nbsp;</td>
                <td class="datos8">
                    <span id="label_nro_telefono_tdc"></span><span>:</span>
                </td>
                <td class="datos9">
                    <span id="valor_nro_telefono_tdc">  </span>
                </td>
            </tr>
            <tr>
                <td colspan="5"><br></td>
            </tr>
            <tr>
                <td class="datos8" >
                    <span id="label_tipo_pago_aut_tdc"></span><span class="">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_tipo_pago_aut_tdc" class="">  </span>
                </td>
                <td width="6%">&nbsp;</td>
                <td class="datos8">
                    <span id="label_fecha_ult_pago_tdc"></span><span class="oculto">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_fecha_ult_pago_tdc">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos8">
                    <span id="label_limite_credito_tdc"></span><span class="oculto">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_limite_credito_tdc">  </span>
                </td>
                <td>&nbsp;</td>
                <td class="datos8">
                    <span id="label_dias_mora_tdc" class="oculto"></span><span class="oculto">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_dias_mora_tdc" class="oculto">  </span>
                </td>
            </tr>
            <tr>
                <td class="datos8">
                    <span id="label_monto_facturar_tdc" class="oculto"></span><span class="oculto">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_monto_facturar_tdc" class="oculto">  </span>
                </td>
                <td>&nbsp;</td>
                <td class="datos8">
                    <span id="label_monto_ult_fact_tdc" class="oculto"></span><span class="oculto">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_monto_ult_fact_tdc" class="oculto">  </span>
                </td>
            </tr>

            <tr>
                <td class="datos8">
                    <span id="label_monto_a_debitar_tdc" class="oculto"></span><span class="oculto">:</span>
                </td>
                <td class="datos9">
                    <span id="valor_monto_a_debitar_tdc" class="oculto">  </span>
                </td>
                <td>&nbsp;</td>
                <td></td>
                <td></td>
            </tr>

            </tbody>
        </table>
    </fieldset>
<fieldset class="div_consulta" >
    <div id="div_tabla_consulta_TDC_ITT" class="div_tabla_consulta">
           <table id="tabla_consulta_TDC_ITT">
               <tr>
                   <td></td>
               </tr>
           </table>
    </div>
</fieldset>
</div>
<div id="div_noInfo_ITT_creditCard" style="display: none">
    <fieldset class="div_consulta">
        <span class="label_titular" id="noInfo_TDC2">No posee tarjetas de cr&eacute;dito que consultar </span>
    </fieldset>
</div>--%>
<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%--
<div>
    <h1 id="TDC_titulo_reclamos">Tarjetas de Cr&eacute;dito / Gesti&oacute;n de reclamos </h1>
</div>

<div id="div_gestionReclamos">
    <div id="div_gestionReclamos_alertaSeguridad" >


        <div id="alertaSeguridadTDC">
            <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
                <tr height="10%">
                    <td align="center">
                        <span id="comun_TAGSubTituloAlertaTDCV_2">AVISOS DE SEGURIDAD PARA EVITAR FRAUDES.<P>POR FAVOR LEA CON DETENIMIENTO LA SIGUIENTE INFORMACI&Oacute;N</P></span>
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
                                                <span id="comun_TAGAlertaTDCV1_2">Firme su tarjeta en tinta indeleble tan pronto la reciba y destruya las tarjetas viejas cuando caduquen.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV2_2">Nunca permita que otras personas utilicen su tarjeta. Es suya y solo suya.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="" height="30"  valign="top">
                                                <span id="comun_TAGAlertaTDCV3_2">Al realizar una compra, aseg&uacute;rese que el vendedor procese la transacci&oacute;n en su presencia.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="" height="30"  valign="top">
                                                <span id="comun_TAGAlertaTDCV4_2">Revise su tarjeta cuando le es devuelta por el cajero para asegurar que es la suya y que no ha sido adulterada en ning&uacute;n modo.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV5_2">Siempre conserve sus recibos para reconciliarlos con su estado de cuenta.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="" height="30"  valign="top">
                                                <span id="comun_TAGAlertaTDCV6_2">Notifique al emisor de la tarjeta de cualquier cambio de direcci&oacute;n, para evitar que nuevas tarjetas y/o estados de cuenta sean enviados a la vieja direcci&oacute;n.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV7_2">En caso de robo o p&eacute;rdida de la tarjeta, notif&iacute;quelo al emisor de inmediato. Siempre mantenga el n&uacute;mero de tel&eacute;fono del emisor (Bank & Trust, Ltd.) a la mano.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV8_2">No entregue ninguna informaci&oacute;n de la tarjeta a personas que lo soliciten por tel&eacute;fono y/o correo y/o correo electr&oacute;nico.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV9_2">De hacer compras en l&iacute;nea, aseg&uacute;rese de realizar su compra a trav&eacute;s de un sitio web Secure Server Transaction (mejor aun si ha sido certificado por VISA).</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV10_2">No firme vouchers de pago en blanco, haga una l&iacute;nea sobre el monto total y destruya el carb&oacute;n y recibos anulados.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV11_2">Evite los sitios web de apuestas, casinos y desconocidos.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV12_2">Si recibe confirmaciones de compra desconocidas v&iacute;a SMS (tel&eacute;fono celular) y/o correo electr&oacute;acute;nicos del Banco, por favor cont&aacute;ute;ctenos de inmediato (Atenci&oacute;n al Cliente, Ejecutivo de Ventas).</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV13_2">Est&eacute; atento a cualquier correo electr&oacute;nico que solicite informaci&oacute;n personal &minus;tales como claves, PINs, o su n&uacute;mero de identificaci&oacute;n- o lo dirija a sitios web que soliciten dicha informaci&oacute;n. Estos mensajes pueden tratarse de un enga&ntilde;o, falsificando una direcci&oacute;n de correo electr&oacute;nico para aparentar ser otra direcci&oacute;n y negocio leg&iacute;timos. Recuerde nunca enviar su informaci&oacute;n personal v&iacute;a correo electr&oacute;nico. Si recibe un correo electr&oacute;nico enga&ntilde;oso, por favor informe a VISA acerca de los detalles de este correo electr&oacute;nico ilegal.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV14_2">Visa no lo contactar&aacute; o enviar&aacute; correos electr&oacute;nicos solicitando la informaci&oacute;n personal de su cuenta. Los centros de llamadas de VISA no inician llamadas de tele-mercadeo. Los clientes no deben responder a correos electr&oacute;nicos ni llamadas telef&oacute;nicas solicitando informaci&oacute;n personal de su tarjeta y se les sugiere reportar inmediatamente tal situaci&oacute;n a las autoridades locales as&iacute; como a la instituci&oacute;n financiera emisora de la tarjeta.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV15_2">El tarjetahabiente ser&aacute; responsable ante El Banco por no reportar consumos en la tarjeta no autorizados. Tan pronto como el tarjetahabiente notifique debidamente al Banco, la responsabilidad de los cargos subsiguientes no autorizados y avances de efectivo podr&aacute; cesar.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV16_2">El tarjetahabiente es el &uacute;nico responsable por el correcto uso de la tarjeta. El tarjetahabiente asumir&aacute; las responsabilidades por todos los cargos y comisiones derivados del uso correcto de la tarjeta. Cualquier uso incorrecto de la tarjeta ser&aacute; de la sola responsabilidad del tarjetahabiente.</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="V8NNegroGris" align="left" width="20" valign="top">
                                                <img src="../vbtonline/resources/images/comun/approved.gif" border=0>
                                            </td>
                                            <td class="V8NNegroGris" height="30" valign="top">
                                                <span id="comun_TAGAlertaTDCV17_2">El uso de su tarjeta por terceras personas no autorizadas (e.g. debido a p&eacute;rdida, robo, falsificaci&oacute;n o uso de la data de la tarjeta mientras el tarjetahabiente utiliza la tarjeta en l&iacute;nea o alg&uacute;n medio digital) es el mayor riesgo que el tarjetahabiente enfrenta al utilizar la tarjeta. La apropiaci&oacute;n de la tarjeta por terceras personas  facilitando el uso incorrecto de la tarjeta podr&aacute; causar da&ntilde;o a una o mas partes participantes del sistema de tarjetas de cr&eacute;dito (e.g. usuarios, establecimientos participantes y el emisor). El reparo de dicho da&ntilde;o surgido del incumplimiento de su obligaci&oacute;n/responsabilidad como tarjetahabiente de prevenir dichos da&ntilde;os puede resultar en compromisos monetarios de usted como tarjetahabiente.</span>
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
                        &lt;%&ndash;<A class="" HREF="" title="Click para continuar" target="" id="TDC_continuar">Click para continuar >></A>&ndash;%&gt;
                        <input type="button" class="botonGrande" id="comun_TAGTitleContinuarTDC2" value="Click para Continuar">
                    </td>
                </tr>
            </TABLE>
            <input type="hidden" id="pantalla" value="">
        </div>
    </div>

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
            <h2 class="banner__title banner__title--modifier" id="TDC_titulo_facturar">
                Tarjetas de Cr&eacute;dito / Movimientos por facturar
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome10" href="Home">HOME</a></li>
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
                <li>IN TRANSIT TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_POR_FACTURAR_2" class="banner__description banner__description--modifier">
                Security advises to avoid frauds. Please read carefully the
                following information.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 class="banner__title banner__title--modifier" id="TDC_titulo_facturar">
                Tarjetas de Cr&eacute;dito / Movimientos por facturar
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome10" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CREDIT_CARDS_2">CREDIT CARDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="IN_TRANSIT_TRANSACTIONS">IN TRANSIT TRANSACTIONS</li>
            </ul>
            <p id="TAG_INFO_POR_FACTURAR_2" class="banner__description banner__description--modifier">
                Security advises to avoid frauds. Please read carefully the
                following information.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main id="alertaSeguridadTDC"  class="main main--modifier">
    <div id="div_creditCard_ITT_alertaSeguridad" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img
                        class="section__icon"
                        src="../vbtonline/resources/img/icons/ic_login_security_tips.png"
                        alt=""
                />
                <div class="section__header">
                    <span class="section__title">Tips</span>
                </div>
            </div>
            <div class="section__content">
                <ul class="section__list">
                    <li id="comun_TAGAlertaTDCV1_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV2_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV3_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV4_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV5_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV6_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV7_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV8_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV9_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV10_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV11_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV12_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV13_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV14_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV15_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV16_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                    <li id="comun_TAGAlertaTDCV17_3">
                        Visa will not call or e-mail you to request your personal
                        account information. Visa call centers do not initiate outbound
                        telemarketing calls.
                    </li>
                </ul>
                <input type="hidden" id="pantalla" value="div_creditCard_ITT">
                <div class="table__spacebetween table__spacebetween--margin">
                    <span class="table__mandaroty"></span>
                    <div class="table__buttons">
                        <button id="comun_TAGTitleContinuarTDC" class="table__button button" >
                            Click to Continue
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_creditCard_ITT" style="display: none" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__headline">
                    <div class="section__header">
                        <div class="section__field">
                            <label id="tdcmovimientosxfacturar_TAGNumeroTarjeta" for="numero_cuenta_TDC_ITT">LABEL</label>
                            <div class="select-section select-section--form">
                                <select id="numero_cuenta_TDC_ITT"
                                        title="Credit Card Number"
                                        class="select-section__select select-section__select--form requerido_TDC_ITT invisible_print"
                                        onchange="cargarSaldosITT(this.value)">
                                </select>
                                <img class="select-section__icon select-section__icon--form" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt="">
                            </div>
                        </div>
                        <img id="transit_imprimir" onclick="print_MOVIMIENTOS_FACTURAR();" class="section__icon" src="../vbtonline/resources/img/icons/ic_table_header_print.png" alt="" />
                    </div>
                </div>
            <!--     <div class="section__row section__row--spacebetween">
                    <span id="TAG_INFO_POR_FACTURAR"></span>
                </div> -->
            </div>
            <div class="section__content">
                <div class="table">
                    <table id="tabla_consulta_TDC_ITT" class="table__content"></table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>

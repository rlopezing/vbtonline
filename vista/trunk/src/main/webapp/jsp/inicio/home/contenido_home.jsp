<%--

<span id="x"></span>
<div class="botones_inicio">
    &lt;%&ndash;<table SUMMARY='tabla'cellpadding="4" cellspacing="1" align="center" class="tabla_inicio">&ndash;%&gt;
        &lt;%&ndash;<tbody>&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<td class="titulo_inicio2" rowspan="3">&ndash;%&gt;
                &lt;%&ndash;<table SUMMARY='tabla' align="center" width="100%">&ndash;%&gt;
                    &lt;%&ndash;<tr>&ndash;%&gt;
                        &lt;%&ndash;<td colspan="2" align="center">&ndash;%&gt;
                            &lt;%&ndash;<b><span id="inicio_label_bienvenida" align="center" >Bienvenido<br></span> </b>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                    &lt;%&ndash;</tr>&ndash;%&gt;
                    &lt;%&ndash;<tr>&ndash;%&gt;
                        &lt;%&ndash;<td colspan="2">&ndash;%&gt;
                            &lt;%&ndash;<span id="inicio_label_conexion" align="center">Su &uacute;ltima conexi&oacute;n fue:<br></span>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                    &lt;%&ndash;</tr>&ndash;%&gt;
                    &lt;%&ndash;<tr>&ndash;%&gt;
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<span id="inicio_label_bienvenida_fecha" align="left">Fecha:</span>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<span id="inicio_bienvenida_fecha" align="right" style="float: right"></span><br>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                    &lt;%&ndash;</tr>&ndash;%&gt;
                    &lt;%&ndash;<tr>&ndash;%&gt;
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<span id="inicio_label_bienvenida_hora" align="left" >Hora:</span>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<span id="inicio_bienvenida_hora" align="right" style="float: right"></span>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                    &lt;%&ndash;</tr>&ndash;%&gt;
                &lt;%&ndash;</table>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;

            &lt;%&ndash;<td class="titulo_inicio">&ndash;%&gt;
                &lt;%&ndash;<span id="inicio_label_nombreUsuario">Nombre Usuario:</span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;<td class="datos_inicio">&ndash;%&gt;
                &lt;%&ndash;<span id="inicio_nombreUsuario" class="datos_inicio"></span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
        &lt;%&ndash;</tr>&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<td class="titulo_inicio">&ndash;%&gt;
                &lt;%&ndash;<span id="inicio_label_fecha">Fecha(Actual):</span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;<td class="datos_inicio">&ndash;%&gt;
                &lt;%&ndash;<span id="inicio_fecha" class="datos_inicio"></span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
        &lt;%&ndash;</tr>&ndash;%&gt;
        &lt;%&ndash;<tr>&ndash;%&gt;
            &lt;%&ndash;<td class="titulo_inicio">&ndash;%&gt;
                &lt;%&ndash;<span id="inicio_label_hora">Hora(Actual):</span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;<td class="datos_inicio">&ndash;%&gt;
                &lt;%&ndash;<span id="inicio_hora" class="datos_inicio"></span>&ndash;%&gt;
            &lt;%&ndash;</td>&ndash;%&gt;
        &lt;%&ndash;</tr>&ndash;%&gt;
    &lt;%&ndash;</table>&ndash;%&gt;

        <table align="center" width="100%">
            <tbody><tr>
                <td  width="38%" valign="top" align="center"><div class="tablaBorde">
                    <table class="tabla_inicio2">
                        <tbody><tr>
                            <td class="titulo_inicio2">
                                <table width="100%" align="center" summary="tabla" valign="top">
                                    <tbody><tr>
                                        <td valign="top" align="center" colspan="2">
                                            <b><span align="center" id="inicio_label_bienvenida">Bienvenido</span> </b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <span id="inicio_label_conexion" align="center">Su última conexión fue el </span><span id="inicio_bienvenida_fecha" align="center">  </span> <span id="inicio_bienvenida_fecha_hora" align="center"> a las </span><span id="inicio_bienvenida_hora" align="center"> 02:06:03 pm<br></span>
                                        </td>
                                    </tr>
                                    </tbody></table>

                            </td>
                        </tr>
                        <tr class="datos_clientes_pdf">
                            <td class="datos_inicio">
                                <b><span id="TagNameFATCA">Información de Interés</span></b>
                            </td>
                        </tr>
                        <tr class="datos_clientes_pdf">
                            <td class="datos_inicio">
                                <table>
                                    <tbody><tr>
                                        <td>
                                            <a onclick="abrirContrato();" title="Click to open the document" id="titleContrato" style="cursor: pointer;">
                                                <img width="20" height="20" border="0" src="../vbtonline/resources/images/comun/new_pdf.png" style="cursor: pointer;">
                                            </a>
                                        </td>
                                        <td><span id="contratoWeb" title="Términos y Condiciones Generales">Términos y Condiciones Generales</span></td>
                                    </tr>
                                    </tbody></table>
                            </td>
                        </tr>
                        <tr class="datos_clientes_pdf">
                            <td class="datos_inicio">
                                <table>
                                    <tbody><tr>
                                        <td>
                                            <a onclick="abrirPDF('comisiones');" title="Click to open the document" id="titleComisiones" style="cursor: pointer;">
                                                <img width="20" height="20" border="0" src="../vbtonline/resources/images/comun/new_pdf.png" style="cursor: pointer;">
                                            </a>
                                        </td>
                                        <td><span id="comisionesTag" title="Comisiones">Tarifas y Comisiones</span></td>
                                    </tr>
                                    </tbody></table>
                            </td>
                        </tr>

                        <tr class="datos_inicio datos_clientes_pdf">
                            <td>
                                <table>
                                    <tbody><tr>
                                        <td>   <a style="cursor: pointer;" id="titleFATCA" title="Click to open the document" onclick="abrirFATCA();">
                                            <img width="20" height="20" border="0" style="cursor: pointer;" src="../vbtonline/resources/images/comun/new_pdf.png">
                                        </a></td>
                                        <td>FATCA</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr class="datos_inicio datos_clientes_pdf">
                            <td>
                                <table>
                                    <tbody><tr>
                                        <td>
                                        <a style="cursor: pointer;" id="titleManualActivacionTDC" title="Click to open the document" onclick="abrirManualTDC();">
                                            <img width="20" height="20" border="0" style="cursor: pointer;" src="../vbtonline/resources/images/comun/new_pdf.png">
                                        </a></td>
                                        <td><label id="tagManualActivacionTDC"></label></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr class="datos_inicio datos_clientes_pdf">
                            <td>
                                <table>
                                    <tbody><tr>
                                        <td>
                                            <a style="cursor: pointer;" id="titleCaymanPremier" title="Click to open the document" onclick="abrirCaymanPremier();">
                                                <img width="20" height="20" border="0" style="cursor: pointer;" src="../vbtonline/resources/images/comun/new_pdf.png">
                                            </a></td>
                                        <td><label id="tagCaymanPremier"></label><img width="16" height="16" border="0" src="../vbtonline/resources/images/comun/updated2.png"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr class="datos_inicio datos_clientes_pdf">
                            <td>
                                <table>
                                    <tbody><tr>
                                        <td>
                                            <a style="cursor: pointer;" id="titleCaymanFinance" title="Click to open the document" onclick="abrirCaymanFinance();">
                                                <img width="20" height="20" border="0" style="cursor: pointer;" src="../vbtonline/resources/images/comun/new_pdf.png">
                                            </a></td>

                                        <td><label id="tagCaymanFinance"></label><img width="16" height="16" border="0" src="../vbtonline/resources/images/comun/updated2.png"></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        &lt;%&ndash;<tr class="datos_inicio datos_clientes_pdf">&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                                &lt;%&ndash;<table>&ndash;%&gt;
                                    &lt;%&ndash;<tbody>&ndash;%&gt;
                                        &lt;%&ndash;<tr>&ndash;%&gt;
                                            &lt;%&ndash;<td>&ndash;%&gt;
                                                &lt;%&ndash;<a style="cursor: pointer;" id="titleVisaBenefits" title="Click to open the document" onclick="abrirVisaBenefits();">&ndash;%&gt;
                                                    &lt;%&ndash;<img width="20" height="20" border="0" style="cursor: pointer;" src="../vbtonline/resources/images/comun/image.png">&ndash;%&gt;
                                                &lt;%&ndash;</a></td>&ndash;%&gt;
                                            &lt;%&ndash;<td><label id="tagVisaBenefits"></label><img width="16" height="16" border="0" src="../vbtonline/resources/images/comun/updated2.png"></td>&ndash;%&gt;
                                        &lt;%&ndash;</tr>&ndash;%&gt;
                                    &lt;%&ndash;</tbody>&ndash;%&gt;
                                &lt;%&ndash;</table>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;
                        &lt;%&ndash;</tr>&ndash;%&gt;
                        &lt;%&ndash;<tr class="datos_inicio datos_clientes_pdf">&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                                &lt;%&ndash;<table>&ndash;%&gt;
                                    &lt;%&ndash;<tbody><tr>&ndash;%&gt;
                                        &lt;%&ndash;<td>&ndash;%&gt;
                                            &lt;%&ndash;<a style="cursor: pointer;" id="titleVisaPromotion" title="Click to open the document" onclick="abrirVisaPromotion();">&ndash;%&gt;
                                                &lt;%&ndash;<img width="20" height="20" border="0" style="cursor: pointer;" src="../vbtonline/resources/images/comun/image.png">&ndash;%&gt;
                                            &lt;%&ndash;</a></td>&ndash;%&gt;
                                        &lt;%&ndash;<td><label id="tagVisaPromotion"></label><img width="16" height="16" border="0" src="../vbtonline/resources/images/comun/updated2.png"></td>&ndash;%&gt;
                                    &lt;%&ndash;</tr>&ndash;%&gt;
                                    &lt;%&ndash;</tbody>&ndash;%&gt;
                                &lt;%&ndash;</table>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;
                        &lt;%&ndash;</tr>&ndash;%&gt;
                        <tr id="manualFC" class="datos_inicio datos_clientes_pdf oculto">
                            <td>
                                <table>
                                    <tbody><tr>
                                        <td>
                                            <a style="cursor: pointer;"  title=" Click to open the document" onclick="abrirGuiaFC();">
                                                <img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/new_pdf.png">
                                            </a>

                                        </td>
                                        <td>
                                            <a style="cursor: pointer;" title=" Click to open the document" onclick="abrirGuiaFC();">
                                                <span id="tagGuiaUsuario">User guide</span>
                                            </a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>

                        </tbody>
                    </table></div>
                </td>
                <td>
                </td>
                <td>
                    <div id="infoHomeDIV" style=" -webkit-border-radius: 15px -moz-border-radius: 15px; border-radius: 15px; border: none; width: 500px; height: 500px; ">

                        <div id="aviso_principal" style="background-image: url('jsp/imgHome.jsp?<%   java.util.Date date=new java.util.Date();out.print(date.getTime());%>');">
                            <div  class="recuadroCarga">
                                <img class="recuadroCargaImg" src="../vbtonline/resources/images/ajax-loader.gif" alt="" />
                            </div>
                            &lt;%&ndash;<h3 id="">Security Tips</h3>&ndash;%&gt;
                            &lt;%&ndash;<ul>&ndash;%&gt;
                                &lt;%&ndash;<li id="login_TAGTip1">Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to entering your User ID and Password.</li>&ndash;%&gt;
                                &lt;%&ndash;<li id="login_TAGTip2">Additionally, verify the Security Certification issued by VeriSign by double clicking the lock icon in the lower bottom right of the screen.</li>&ndash;%&gt;
                                &lt;%&ndash;<li id="login_TAGTip3">Your card and account information are personal and confidential. Information such as card number, password and card expiration date, must not be entered in sites different than the previously mentioned web address, which possesses the security certification required to encode and preserve your information.</li>&ndash;%&gt;
                            &lt;%&ndash;</ul>&ndash;%&gt;
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>


</div>
<div id='pdfFATCA' class="botones_inicio">
    <br>
    <br>
    <br>
    &lt;%&ndash;<fieldset id="1FC_form_8" class=" div_informacion_interes right" >&ndash;%&gt;

        &lt;%&ndash;<TABLE width="100%" border="0" cellspacing="0" cellpadding="0">&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
                &lt;%&ndash;<td width="100%" height="35" class="V8NBNegroGris" align="center">&ndash;%&gt;
                    &lt;%&ndash;<span id="TagNameFATCA" class="datos negrita"><b>Information of Interest</b></span>&ndash;%&gt;
                &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
                &lt;%&ndash;<td align="left">&ndash;%&gt;
                    &lt;%&ndash;<br>&ndash;%&gt;
                    &lt;%&ndash;<table cellpadding="4" cellspacing="1" align="center">&ndash;%&gt;
                        &lt;%&ndash;<tr>&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                                &lt;%&ndash;<a style="cursor: pointer;" id="titleContrato" title=" Click to open the document" onclick="abrirContrato();">&ndash;%&gt;
                                    &lt;%&ndash;<img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/pdf.gif">&ndash;%&gt;
                                &lt;%&ndash;</a>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                                &lt;%&ndash;<a style="cursor: pointer;" id="titleContrato1" title=" Click to open the document" onclick="abrirContrato();">&ndash;%&gt;
                                    &lt;%&ndash;<span id="contratoWeb">General Terms & Conditions</span>&ndash;%&gt;
                                &lt;%&ndash;</a>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;
                            &lt;%&ndash;<td><p></p></td>&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                                &lt;%&ndash;<a style="cursor: pointer;" id="titleFATCA" title=" Click to open the document" onclick="abrirFATCA();">&ndash;%&gt;
                                    &lt;%&ndash;<img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/pdf.gif">&ndash;%&gt;
                                &lt;%&ndash;</a>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;
                            &lt;%&ndash;<td>&ndash;%&gt;
                                &lt;%&ndash;<a style="cursor: pointer;" id="titleFATCA1" title=" Click to open the document" onclick="abrirFATCA();">&ndash;%&gt;
                                    &lt;%&ndash;<span id="">FATCA</span>&ndash;%&gt;
                                &lt;%&ndash;</a>&ndash;%&gt;
                            &lt;%&ndash;</td>&ndash;%&gt;

                        &lt;%&ndash;</tr>&ndash;%&gt;
                    &lt;%&ndash;</table>&ndash;%&gt;
                &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;<tr id="manualFC">&ndash;%&gt;
                &lt;%&ndash;<td align="center">&ndash;%&gt;
                &lt;%&ndash;<table align="center">&ndash;%&gt;
                    &lt;%&ndash;<tr>&ndash;%&gt;
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<a style="cursor: pointer;"  title=" Click to open the document" onclick="abrirGuiaFC();">&ndash;%&gt;
                                &lt;%&ndash;<img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/pdf.gif">&ndash;%&gt;
                            &lt;%&ndash;</a>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<a style="cursor: pointer;" title=" Click to open the document" onclick="abrirGuiaFC();">&ndash;%&gt;
                                &lt;%&ndash;<span id="tagGuiaUsuario">User guide</span>&ndash;%&gt;
                            &lt;%&ndash;</a>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                    &lt;%&ndash;</tr>&ndash;%&gt;
                &lt;%&ndash;</table>&ndash;%&gt;
                &lt;%&ndash;</td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
            &lt;%&ndash;<tr>&ndash;%&gt;
                &lt;%&ndash;<td align="left" style="padding-left:10px;padding-top:10px">&ndash;%&gt;
                  &lt;%&ndash;<span id="inicio_infoAccionesUsuario">&ndash;%&gt;
                    &lt;%&ndash;With VBT Bank &amp; Trust Online you can access all your accounts, consult balances and transactions, download your monthly statements and make internal transfers and transfers to other Banks.&ndash;%&gt;

                    &lt;%&ndash;To start navigate please select any option on the menu.    </span></td>&ndash;%&gt;
            &lt;%&ndash;</tr>&ndash;%&gt;
        &lt;%&ndash;</table>&ndash;%&gt;
    &lt;%&ndash;</fieldset>&ndash;%&gt;
</div>
<div id="inicio_infoVBT" class="botones_inicio">


</div>
<input type="hidden" id="tipo_usuario_app" value="">
<input type="hidden" id="tipo_contrato_app" value="">--%>
<section class="HomeDesignV2">
    <div  style="text-align: right;padding: 1em 0; width: 100%;">
        <span>Your last connection was: </span><span id="inicio_bienvenida_fecha"></span><span> at </span><span id="inicio_bienvenida_hora"></span>
<!--         <a class="banner__accounts button" data-modal="#notice-home-modal">NOTICE</a>
 -->    </div>
    <div class="HomeDesignV2-Content">
        <div class="HomeDesignV2-ContentLeft">
                <div>
                    <section class="BalaceTableHomeContent oculto">
                        <div>
                            <div class="BalaceTableHomeTitle"><span id="Balances_By_Product">Balances by product</span><span id="PortfolioNewHome" style="cursor: pointer;">All my Portfolio</span></div>
                            <div id="BalaceTableHome">

                            </div>
                        </div>
                    </section>
                    <section id="holidays-section" class="holidays-section">
                        <div class="holidays-section__container container" id="holidays-section-container">
                            <div class="titles-section">
                                <img class="titles-section__icon"
                                     src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_schedule.png" alt=""/>
                                <div>
                                    <h2 class="holidays-section__title titles-section__title" id="Home_new_design_calendar_title">
                                        Holidays
                                    </h2>
                                    <p class="holidays-section__description titles-section__description" id="Home_new_design_calendar_desc">
                                        This calendar provides information on holidays in the Cayman
                                        Islands.
                                    </p>
                                </div>
                            </div>
                            <div class="holidays-section__calendar calendar">
                                <div class="calendar__info">
                                    <span class="calendar__month" id="calendar__monthyearshort"></span>
                                    <div id="calendar__buttons" class="calendar__nav">
                                        <img id="calendar__prev" class="calendar__prev" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                                        <img id="calendar__next" class="calendar__next" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt=""/>
                                    </div>
                                </div>
                                <div class="calendar__wrap">
                <%--
                                    <div class="calendar__content">
                                        <div class="calendar__week">
                                            <div class="calendar__day calendar__item">SUN</div>
                                            <div class="calendar__day calendar__item">MON</div>
                                            <div class="calendar__day calendar__item">TUE</div>
                                            <div class="calendar__day calendar__item">WED</div>
                                            <div class="calendar__day calendar__item">THU</div>
                                            <div class="calendar__day calendar__item">FRI</div>
                                            <div class="calendar__day calendar__item">SAT</div>
                                        </div>
                                        <div class="calendar__dates">
                                            <div class="calendar__item"></div>
                                            <div class="calendar__item"></div>
                                            <div class="calendar__item"></div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">1</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">2</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">3</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">4</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">5</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">6</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">7</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">8</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">9</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">10</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">11</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">12</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">13</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">14</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">15</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">16</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">17</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">18</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">19</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">20</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">21</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">22</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">23</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">24</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">25</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">26</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date calendar__date--active">27</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date calendar__date--active">28</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">29</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">30</span>
                                            </div>
                                            <div class="calendar__item">
                                                <span class="calendar__date">31</span>
                                            </div>
                                        </div>
                                    </div>
                --%>
                                    <div id="jMonthCalendar"></div>
                                    <div class="calendar__events">
                                        <span class="calendar__title" id="Home_new_design_calendar_title2">HOLIDAYS</span>
                                        <div id="calendar-events" >
                <%--                            <div class="calendar__event">
                                                <span class="calendar__fulldate">DEC 27, MONDAY</span><span class="calendar__description">Christmas
                                  Day</span>
                                            </div>
                                            <div class="calendar__event">
                                                <span class="calendar__fulldate">DEC 28, TUESDAY</span><span class="calendar__description">Boxing
                                  Day</span>
                                            </div>
                                        </div>--%>
                                        </div>
                                    </div>
                            </div>
                        </div>
                    </section>


                </div>
        </div>
        <div class="HomeDesignV2-ContentRight">
            <a class="card-option card-option-v2" id="transferencias_TAGNoticeHomeModal">
                <span id="transferencias_TAGNoticeHome" class="card-option__title">NOTICE</span>
            </a>
            <a class="card-option" onclick="abrirContrato();" style="margin-top: 0em;"><img class="card-option__icon"
                src="../vbtonline/resources/img/icons/ic_info_download.png"
                alt=""/><span ID="contratoWeb"
                class="card-option__title">General Terms & Conditions</span>
                </a>
                <a class="card-option" onclick="abrirPDF('comisiones');"><img
                class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span id="comisionesTag"
                class="card-option__title">Schedule of fees. Updated</span>
                </a>
                <a class="card-option" onclick="abrirFATCA();">
                <img
                class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span
                class="card-option__title">FATCA</span>
                </a>
     <!--            <a class="card-option" onclick="abrirManualTDC();">
                <img
                class="card-option__icon"
                src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span id="tagManualActivacionTDC"
                class="card-option__title">VISA Card
                Activation</span>
                </a> -->
                <a class="card-option" onclick="abrirCaymanPremier();">
                <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png"
                alt=""/>
                <span class="card-option__title" id="tagCaymanPremier">Cayman's Premier
                Statement on EU Listing Decision</span>
                </a>
                <a class="card-option" onclick="abrirCaymanFinance();">
                <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                <span class="card-option__title" id="tagCaymanFinance">Cayman Finance
                Press Release on EU's recognition of Cayman
                Islands Cooperation
                </span>
                </a>
                <a id="manualFC" class="card-option oculto" onclick="abrirGuiaFC();">
                <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                <span class="card-option__title" id="tagGuiaUsuario">User Guide</span>
            </a>
        </div>
    </div>
</section>

<%--
<div class="banner">
    <img class="banner__img" src="../vbtonline/resources/img/bg-home.jpg" alt="Banner Home"/>
    <div class="banner__container container">
        <div class="banner__content">
            <span class="title-note">Bank & Trust</span>
            <h1 class="banner__title">VBT Online</h1>
            <p class="banner__description">
                Experience a new way to manage your savings.
            </p>
            <a class="banner__accounts button" data-modal="#notice-home-modal">NOTICE</a>
        </div>
    </div>
</div>
<main class="main">
    <div class="options" id="client-data">
        <div class="options__container container">
            <img class="options__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards.png" alt=""/><span
                class="options__title">General Info</span><span class="options__description">Download the files to keep
          updated.</span>
            <div class="options__grid">
                <a class="card-option" onclick="abrirContrato();"><img class="card-option__icon"
                                                     src="../vbtonline/resources/img/icons/ic_info_download.png"
                                                     alt=""/><span ID="contratoWeb"
                        class="card-option__title">General Terms & Conditions</span>
                </a>
                <a class="card-option" onclick="abrirPDF('comisiones');"><img
                    class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span id="comisionesTag"
                    class="card-option__title">Schedule of fees. Updated</span>
                </a>
                <a class="card-option" onclick="abrirFATCA();">
                    <img
                    class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span
                    class="card-option__title">FATCA</span>
                </a>
                <a class="card-option" onclick="abrirManualTDC();">
                    <img
                    class="card-option__icon"
                    src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span id="tagManualActivacionTDC"
                    class="card-option__title">VISA Card
              Activation</span>
                </a>
                <a class="card-option" onclick="abrirCaymanPremier();">
                    <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png"
                         alt=""/>
                    <span class="card-option__title" id="tagCaymanPremier">Cayman's Premier
              Statement on EU Listing Decision</span>
                </a>
                <a class="card-option" onclick="abrirCaymanFinance();">
                    <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                    <span class="card-option__title" id="tagCaymanFinance">Cayman Finance
                        Press Release on EU's recognition of Cayman
                        Islands Cooperation
                    </span>
                </a>
                <a id="manualFC" class="card-option oculto" onclick="abrirGuiaFC();">
                    <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                    <span class="card-option__title" id="tagGuiaUsuario">User Guide</span>
                </a>
            </div>
            <img class="options__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
        </div>
    </div>
    <section id="holidays-section" class="holidays-section">
        <div class="holidays-section__container container" id="holidays-section-container">
            <div class="titles-section">
                <img class="titles-section__icon"
                     src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_schedule.png" alt=""/>
                <div>
                    <h2 class="holidays-section__title titles-section__title">
                        Holidays
                    </h2>
                    <p class="holidays-section__description titles-section__description">
                        This calendar provides information on holidays in the Cayman
                        Islands.
                    </p>
                </div>
            </div>
            <div class="holidays-section__calendar calendar">
                <div class="calendar__info">
                    <span class="calendar__month" id="calendar__monthyearshort"></span>
                    <div id="calendar__buttons" class="calendar__nav">
                        <img id="calendar__prev" class="calendar__prev" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                        <img id="calendar__next" class="calendar__next" src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png" alt=""/>
                    </div>
                </div>
                <div class="calendar__wrap">

                    <div class="calendar__content">
                        <div class="calendar__week">
                            <div class="calendar__day calendar__item">SUN</div>
                            <div class="calendar__day calendar__item">MON</div>
                            <div class="calendar__day calendar__item">TUE</div>
                            <div class="calendar__day calendar__item">WED</div>
                            <div class="calendar__day calendar__item">THU</div>
                            <div class="calendar__day calendar__item">FRI</div>
                            <div class="calendar__day calendar__item">SAT</div>
                        </div>
                        <div class="calendar__dates">
                            <div class="calendar__item"></div>
                            <div class="calendar__item"></div>
                            <div class="calendar__item"></div>
                            <div class="calendar__item">
                                <span class="calendar__date">1</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">2</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">3</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">4</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">5</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">6</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">7</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">8</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">9</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">10</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">11</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">12</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">13</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">14</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">15</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">16</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">17</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">18</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">19</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">20</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">21</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">22</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">23</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">24</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">25</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">26</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date calendar__date--active">27</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date calendar__date--active">28</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">29</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">30</span>
                            </div>
                            <div class="calendar__item">
                                <span class="calendar__date">31</span>
                            </div>
                        </div>
                    </div>

                    <div id="jMonthCalendar"></div>
                    <div class="calendar__events">
                        <span class="calendar__title">HOLIDAYS</span>
                        <div id="calendar-events" >
                            <div class="calendar__event">
                                <span class="calendar__fulldate">DEC 27, MONDAY</span><span class="calendar__description">Christmas
                  Day</span>
                            </div>
                            <div class="calendar__event">
                                <span class="calendar__fulldate">DEC 28, TUESDAY</span><span class="calendar__description">Boxing
                  Day</span>
                            </div>
                        </div>
                        </div>
                    </div>
            </div>
        </div>
    </section>--%>
<%--    <jsp:include page="../../transfer-instructions.jsp" />--%>
<%-- </main>--%>
<%--<jsp:include page="../../footer.jsp" />--%>
<div class="notification-modal" id="notification-modal">
    <div class="notification-modal__top">
        <img class="notification-modal__icon" src="../vbtonline/resources/img/icons/ic_home_info_notice.png" alt=""/>
        <span class="notification-modal__title">Notice / Noticia</span></div>
    <div class="notification-modal__content">
        <div id="aviso_principal">
        </div>
<%--        <span class="notification-modal__subtitle">Dear Customer</span>
        <p class="notification-modal__paragraph">Your ultimate attention to this important metter is greatly appreciated
            as we prepare to update our database. In order to continue using this Online system, we appreciete that you
            update the information as soon as possible. You have fifteen (15) days from the first message.
        </p>
        <p class="notification-modal__paragraph">If you have any questions concerning this request, please contact your
            Relationship manager , Monday to Friday, between the hours of 8:00 a.m. - 5:00 p.m. Thank you in advance for
            your assistance and undrstanding as we move forward with this important service initiative.
        </p>
        <div class="notification-modal__greeting">
            <span>Sincerely Yours</span>
            <span class="notification-modal__vbt">VBT Bank & Trust, Ltd.</span>
        </div>--%>
        <div class="notification-modal__spacebetween notification-modal__spacebetween--modifier">
            <img class="notification-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
            <a class="button" href="#close" rel="modal:close" id="TAGModalNoticeClose">CLOSE</a>
        </div>
    </div>
</div>
<script>
/*     $("#notification-modal").modal({
        showClose: !1,
        modalClass: "notification-modal",
        fadeDuration: 100,
        blockerClass: "notification-modal--blocker",
    });
    $("a[data-modal]").click(function (e) {
        return (
            $("#notification-modal").modal({
                showClose: !1,
                modalClass: "notification-modal",
                fadeDuration: 100,
                blockerClass: "notification-modal--blocker",
            }),
                !1
        );
    }); */

    $("#transferencias_TAGNoticeHomeModal").click(function(){
            $("#notification-modal").modal({
                showClose: !1,
                modalClass: "notification-modal",
                fadeDuration: 100,
                blockerClass: "notification-modal--blocker",
            });
            $("a[data-modal]").click(function (e) {
                return (
                    $("#notification-modal").modal({
                        showClose: !1,
                        modalClass: "notification-modal",
                        fadeDuration: 100,
                        blockerClass: "notification-modal--blocker",
                    }),
                        !1
                );
            }); 
    });

    
    $("#PortfolioNewHome").click(function(){
                seleccionarOpcion("CONSOLIDADO");
                $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
            });
</script>

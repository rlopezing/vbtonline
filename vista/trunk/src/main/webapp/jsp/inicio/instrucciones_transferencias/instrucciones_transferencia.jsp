<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TAGTitleInstrucciones_2"  class="banner__title banner__title--modifier">
                INSTRUCCIONES TRANSFERENCIAS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome45" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleInstrucciones_3"> INSTRUCCIONES TRANSFERENCIAS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TAGTitleInstrucciones_2"  class="banner__title banner__title--modifier">
                INSTRUCCIONES TRANSFERENCIAS
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome45" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleInstrucciones_3"> INSTRUCCIONES TRANSFERENCIAS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>

<main class="main main--modifier">
    <div class="section">
        <div class="section__container container">
            <div class="section__content">
                <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
                    <tr height="15%">
                        <td class="V8NBNegro" align="center"></td>
                    </tr>
                    <tr>
                        <td>
                
                            <TABLE border="0" width="100%" align="center" cellpadding="0" cellspacing="0" >
                                <tr>
                                    <td>
                
                                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
                                            <tr><td height="16" align="center" colspan="5"></td></tr>
                                            <tr>
                                                <td  height="30" align="right" >
                
                                                </td>
                
                                                <td  align="center" class="borde_izq_punteado">
                                                    <label id="Standard_Chartered_Bank" class="datos"><B>Standard Chartered Bank</B></label><BR><BR>
                
                                                </td>
                                                <td  align="center" colspan="2"  class="borde_izq_punteado">
                                                    <!--<label class="datos"><B>Fieldpoint Private Bank & Trust</B></label><br><BR>   -->
                
                                                </td>
                                                <td  align="center">
                                                    <!-- <a id="DownloadtitleDolares3" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_US$.pdf','_blank');" target="_blank" >
                                                         <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20"><br><label class="A7NVerde">Opci&oacute;n 3</label>
                                                    </a>-->
                                                </td>
                                            </tr>
                                            <tr>
                                                <td  height="30" align="center" >
                                                    <span id="TAGTituloDolares"><B>D&oacute;lares Americanos (USD)</B></span>
                                                </td>
                
                                                <td  align="center" class="borde_izq_punteado" >
                                                    <a id="DownloadtitleDolares1" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_US$.pdf','_blank');" target="_blank" >
                                                       <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20"><br>
                                                    </a>
                                                </td>
                                                <td  align="center"  class="borde_izq_punteado">
                                                    <!--<a id="DownloadtitleDolares2" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_FPB_US$.pdf','_blank');" target="_blank" >
                                                        <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20"><br>   <label class="datos">Domestic and International</label> <BR><BR>
                                                    </a>-->
                                                </td>
                                                <td  align="center">
                                                    <!--<a id="DownloadtitleDolares3" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_FPB_SWIFT_US$.pdf','_blank');" target="_blank" >
                                                        <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20"><br>   <label class="datos">If the Originating Bank requires <br>a SWIFT ID to send USD Payment</label><BR><BR>
                                                    </a>-->
                                                </td>
                                                <td  align="center">
                                                   <!-- <a id="DownloadtitleDolares3" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_US$.pdf','_blank');" target="_blank" >
                                                        <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20"><br><label class="A7NVerde">Opci&oacute;n 3</label>
                                                    </a>-->
                                                </td>
                                            </tr>
                                          <tr>
                                              <td  height="30" align="center" >
                                                  <a id="titleEuros" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_EUR.pdf','_blank')" class="V8NBVerde" title="Haga click para abrir el documento" target="_blank"><span id="TAGTituloEuros"><B>Euros (EUR)</B></span></a>
                                              </td>
                                              <td  align="center"  class="borde_izq_punteado">
                                                  <a id="DownloadtitleEuros" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_EUR.pdf','_blank');">
                                                      <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20" >
                                                  </a>
                                              </td>
                                              <td  align="center" colspan=""  class="borde_izq_punteado" >
                
                                              </td>
                                          </tr>
                                       <!--
                                        <tr>
                                            <td  height="30" align="center">
                                                <a id="titleLibras" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_GBP.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloLibras">Libras Esterlinas (GBP)</span></a>
                                            </td>
                                            <td  align="center">
                                                <a id="DownloadtitleLibras" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_GBP.pdf','_blank');">
                                                    <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20">
                                                </a>
                                            </td>
                                            <td  align="center" colspan="2">
                
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  height="30" align="center">
                                                <a id="titleFrancos" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_CHF.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloFrancos">Francos Suizos (CHF)</span></a>
                                            </td>
                                            <td  align="center">
                                                <a id="DownloadtitleFrancos" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_CHF.pdf','_blank');">
                                                    <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20" />
                                                </a>
                                            </td>
                                            <td  align="center" colspan="2">
                
                                            </td>
                                        </tr>-->
                                            <tr><td class="V8NNegroGris" height="16" align="center" colspan="4"></td></tr>
                                        </TABLE>
                
                                    </td>
                                </tr>
                            </TABLE>
                        </td>
                    </tr>
                    <tr height="30">
                        <td align="center" class="A7NVerde">
                            <br><br>
                            <span id="comun_TAGMsgAcrobat1_V2">
                                Este sitio utiliza documentos PDF para lo cual deber&aacute; tener instalado Adobe Acrobat Reader.
                            </span>
                        </td>
                
                    </tr>
                    <tr height="30">
                        <td align="center" class="A7NVerde"><span id="comun_TAGMsgAcrobat2_V2">Si no posee el Acrobat Reader puede obtenerlo haciendo click en:</span> </td>
                    </tr>
                    <tr height="50">
                        <td align="center" class="A7NVerde">
                            <a id="titlePDF" onclick="printToPDF('http://www.adobe.com/products/acrobat/readstep2.html');">
                                <img src="../vbtonline/resources/images/comun/reader.gif" border=0 >
                            </a>
                        </td>
                    </tr>
                </TABLE>
            </div>
        </div>
    </div>
</main>





<%--

<fieldset id="1FC_form_instrucciones_transferencia" class=" div_informacion_interes right" >
<TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
    <tr height="15%">
        <td class="V8NBNegro" align="center"></td>
    </tr>
    <tr>
        <td>

            <TABLE border="0" width="100%" align="center" cellpadding="0" cellspacing="0" >
                <tr>
                    <td>

                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
                            <tr><td height="16" align="center" colspan="5"></td></tr>
                            <tr>
                                <td  height="30" align="right" >

                                </td>

                                <td  align="center" class="borde_izq_punteado">
                                    <label class="datos"><B>Standard Chartered Bank</B></label><BR><BR>

                                </td>
                                <td  align="center" colspan="2"  class="borde_izq_punteado">
                                    <!--<label class="datos"><B>Fieldpoint Private Bank & Trust</B></label><br><BR>   -->

                                </td>
                                <td  align="center">
                                    <!-- <a id="DownloadtitleDolares3" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_US$.pdf','_blank');" target="_blank" >
                                         <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20"><br><label class="A7NVerde">Opci&oacute;n 3</label>
                                    </a>-->
                                </td>
                            </tr>
                            <tr>
                                <td  height="30" align="center" >
                                    <span id="TAGTituloDolares"><B>D&oacute;lares Americanos (USD)</B></span>
                                </td>

                                <td  align="center" class="borde_izq_punteado" >
                                    <a id="DownloadtitleDolares1" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_US$.pdf','_blank');" target="_blank" >
                                       <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20"><br>
                                    </a>
                                </td>
                                <td  align="center"  class="borde_izq_punteado">
                                    <!--<a id="DownloadtitleDolares2" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_FPB_US$.pdf','_blank');" target="_blank" >
                                        <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20"><br>   <label class="datos">Domestic and International</label> <BR><BR>
                                    </a>-->
                                </td>
                                <td  align="center">
                                    <!--<a id="DownloadtitleDolares3" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_FPB_SWIFT_US$.pdf','_blank');" target="_blank" >
                                        <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20"><br>   <label class="datos">If the Originating Bank requires <br>a SWIFT ID to send USD Payment</label><BR><BR>
                                    </a>-->
                                </td>
                                <td  align="center">
                                   <!-- <a id="DownloadtitleDolares3" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_US$.pdf','_blank');" target="_blank" >
                                        <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20"><br><label class="A7NVerde">Opci&oacute;n 3</label>
                                    </a>-->
                                </td>
                            </tr>
                          <tr>
                              <td  height="30" align="center" >
                                  <a id="titleEuros" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_EUR.pdf','_blank')" class="V8NBVerde" title="Haga click para abrir el documento" target="_blank"><span id="TAGTituloEuros"><B>Euros (EUR)</B></span></a>
                              </td>
                              <td  align="center"  class="borde_izq_punteado">
                                  <a id="DownloadtitleEuros" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_EUR.pdf','_blank');">
                                      <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20" >
                                  </a>
                              </td>
                              <td  align="center" colspan=""  class="borde_izq_punteado" >

                              </td>
                          </tr>
                       <!--
                        <tr>
                            <td  height="30" align="center">
                                <a id="titleLibras" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_GBP.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloLibras">Libras Esterlinas (GBP)</span></a>
                            </td>
                            <td  align="center">
                                <a id="DownloadtitleLibras" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_GBP.pdf','_blank');">
                                    <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20">
                                </a>
                            </td>
                            <td  align="center" colspan="2">

                            </td>
                        </tr>
                        <tr>
                            <td  height="30" align="center">
                                <a id="titleFrancos" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_CHF.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloFrancos">Francos Suizos (CHF)</span></a>
                            </td>
                            <td  align="center">
                                <a id="DownloadtitleFrancos" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_CHF.pdf','_blank');">
                                    <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20" />
                                </a>
                            </td>
                            <td  align="center" colspan="2">

                            </td>
                        </tr>-->
                            <tr><td class="V8NNegroGris" height="16" align="center" colspan="4"></td></tr>
                        </TABLE>

                    </td>
                </tr>
            </TABLE>
        </td>
    </tr>
    <tr height="30">
        <td align="center" class="A7NVerde">
            <br><br>
            <span id="comun_TAGMsgAcrobat1">
                Este sitio utiliza documentos PDF para lo cual deber&aacute; tener instalado Adobe Acrobat Reader.
            </span>
        </td>

    </tr>
    <tr height="30">
        <td align="center" class="A7NVerde"><span id="comun_TAGMsgAcrobat2">Si no posee el Acrobat Reader puede obtenerlo haciendo click en:</span> </td>
    </tr>
    <tr height="50">
        <td align="center" class="A7NVerde">
            <a id="titlePDF" onclick="printToPDF('http://www.adobe.com/products/acrobat/readstep2.html');">
                <img src="../vbtonline/resources/images/comun/reader.gif" border=0 >
            </a>
        </td>
    </tr>
</TABLE>
</fieldset>
</div>

--%>
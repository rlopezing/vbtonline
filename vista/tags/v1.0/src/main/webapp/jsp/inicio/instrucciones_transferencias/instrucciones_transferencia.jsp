<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="TAGTitleInstrucciones">INSTRUCCIONES TRANSFERENCIAS </h1>
</div>
<div>

<fieldset id="1FC_form_instrucciones_transferencia" class=" div_informacion_interes right" >
<TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
    <tr height="15%">
        <td class="V8NBNegro" align="center"></td>
    </tr>
    <tr>
        <td>

            <TABLE border="0" width="85%" align="center" cellpadding="0" cellspacing="0" >
                <tr>
                    <td>

                        <TABLE width="100%" border="0" cellspacing="0" cellpadding="3">
                            <tr><td height="16" align="center" colspan="2"></td><td></td></tr>
                            <tr>
                                <td  height="30" align="center">
                                    <a id="titleDolares" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_US$_ve_es.pdf','_blank')" title="Haga click para abrir el documento"><span id="TAGTituloDolares">D&oacute;lares Americanos (USD)</span></a>
                                </td>
                                <td  align="center">
                                    <a id="DownloadtitleDolares1" onclick="window.open('../vbtonline/resources/documentos//wire_transfer_instructions_US$_ve_es.pdf','_blank');" target="_blank" >
                                        <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20"><br><label class="A7NVerde">Opci&oacute;n 1</label>
                                    </a>
                                </td>
                                <td  align="left">
                                    <a id="DownloadtitleDolares2" onclick="window.open('../vbtonline/resources/documentos//Wire_Transfer_Instructions_SCB_US$_us_en.pdf','_blank');" target="_blank" >
                                        &nbsp;&nbsp;&nbsp;&nbsp;<img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20"><br><label class="A7NVerde">Opci&oacute;n 2</label>
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td  height="30" align="center">
                                    <a id="titleEuros" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_EUR_ve_es.pdf','_blank')" class="V8NBVerde" title="Haga click para abrir el documento" target="_blank"><span id="TAGTituloEuros">Euros (EUR)</span></a>
                                </td>
                                <td  align="center">
                                    <a id="DownloadtitleEuros" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_EUR_ve_es.pdf','_blank');">
                                        <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20" >
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td  height="30" align="center">
                                    <a id="titleLibras" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_GBP_ve_es.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloLibras">Libras Esterlinas (GBP)</span></a>
                                </td>
                                <td  align="center">
                                    <a id="DownloadtitleLibras" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_GBP_ve_es.pdf','_blank');">
                                        <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20">
                                    </a>
                                </td>
                            </tr>
                            <tr>
                                <td  height="30" align="center">
                                    <a id="titleFrancos" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_CHF_ve_es.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloFrancos">Francos Suizos (CHF)</span></a>
                                </td>
                                <td  align="center">
                                    <a id="DownloadtitleFrancos" onclick="window.open('../vbtonline/resources/documentos/wire_transfer_instructions_CHF_ve_es.pdf','_blank');">
                                        <img src="../vbtonline/resources/images/comun/pdf.gif" border=0 height="20" width="20" />
                                    </a>
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
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
    <h1 id="comun_TAGFormatos">FORMATOS </h1>
</div>
<div id="formatos" class="botones_inicio2">
    <TABLE border="0" cellspacing="0" cellpadding="0" width="95%" align="center">
        <tr height="30%">
            <td class="V8NBNegro" align="center"></td>
        </tr>
        <tr>
            <td>
                <TABLE border="0" width="85%" align="center" cellpadding="0" cellspacing="0">
                    <tr>
                        <td align="center">
<TABLE width="400" border="0" cellspacing="0" cellpadding="3">
    <tr><td height="16" align="center" colspan="2"></td></tr>
    <tr>
        <td  width="300" height="30" align="center">
            <a id="CartaDeInstrunccionInterna" href="javascript:printToPDF('../vbtonline/resources/documentos/carta_instruccion_interna_ve_es.pdf');" class="V8NBVerde" title="Haga click para abrir el documento">
                Carta de Instrucci&oacute;n Operaciones Internas</a>
        </td>
        <td  align="left">
            <a href="javascript:printToPDF('../vbtonline/resources/documentos/carta_instruccion_interna_ve_es.pdf');">
                <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20" title="Haga click para abrir el documento">
            </a>
        </td>
    </tr>
    <tr>
        <td  width="300" height="30" align="center">
            <a id="cartaDeInstruccionExterna" href="javascript:printToPDF('../vbtonline/resources/documentos/carta_instruccion_externa_ve_es.pdf');" class="V8NBVerde" title="Haga click para abrir el documento">
                Carta de Instrucci&oacute;n Transferencia Externa</a>
        </td>
        <td  align="left">
            <a href="javascript:printToPDF('../vbtonline/resources/documentos/carta_instruccion_externa_ve_es.pdf');">
                <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20" title="Haga click para abrir el documento">
            </a>
        </td>
    </tr>
    <tr><td  height="16" align="center" colspan="2"></td></tr>
</TABLE>
    </td>
    </tr>
    </TABLE>
    </td>
    </tr>
    <tr height="30">
        <td class="" align="center"></td>
    </tr>
    <tr height="30">
        <td align="center" class=""><br><br><span id="comun_TAGMsgAcrobat1">Este sitio utiliza documentos PDF para lo cual deber&aacute; tener instalado Adobe Acrobat Reader.</span></td>
    </tr>
    <tr height="30">
        <td align="center" class=""><span id="comun_TAGMsgAcrobat2">Si no posee el Acrobat Reader puede obtenerlo haciendo click en:</span></td>
    </tr>
    <tr height="50">
        <td align="center" class="">
            <a href="javascript:printToPDF('http://www.adobe.com/products/acrobat/readstep2.html');">
                <img src="../vbtonline/resources/images/comun/reader.gif" border=0 title="Click para ir a descargar Acrobat Reader">
            </a>
        </td>
    </tr>
    </TABLE>
</div>
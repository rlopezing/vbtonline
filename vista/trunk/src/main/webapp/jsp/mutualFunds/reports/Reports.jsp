<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="TAGTitleInformesFondosMutuales">INFORMES </h1>
</div>
<div>
    <fieldset id="1FC_form_informes" class=" div_informes_fondos_mutuales right" >
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
                                            <a id="titleBOF_fondos_mutuales" onclick="window.open('../vbtonline/resources/documentos/BOF.pdf','_blank')" class="V8NBVerde" title="Haga click para abrir el documento" target="_blank"><span id="TAGTituloBOF_fondos_mutuales">Balanced Opportunity Fund</span></a>
                                        </td>
                                        <td  align="center">
                                            <a id="DownloadtitleBOF_fondos_mutuales" onclick="window.open('../vbtonline/resources/documentos/BOF.pdf','_blank');">
                                                <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20" >
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  height="30" align="center">
                                            <a id="titleEuro_fondos_mutuales" onclick="window.open('../vbtonline/resources/documentos/EURO.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloEuro_fondos_mutuales">Euro Fund</span></a>
                                        </td>
                                        <td  align="center">
                                            <a id="DownloadtitleEuro_fondos_mutuales" onclick="window.open('../vbtonline/resources/documentos/EURO.pdf','_blank');">
                                                <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20">
                                            </a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td  height="30" align="center">
                                            <a id="titleLIQ_fondos_mutuales" onclick="window.open('../vbtonline/resources/documentos/LIQ.pdf','_blank')"  title="Haga click para abrir el documento"><span id="TAGTituloLIQ_fondos_mutuales">Liquidity Fund</span></a>
                                        </td>
                                        <td  align="center">
                                            <a id="DownloadtitleLIQ_fondos_mutuales" onclick="window.open('../vbtonline/resources/documentos/LIQ.pdf','_blank');">
                                                <img src="../vbtonline/resources/images/comun/new_pdf.png" border=0 height="20" width="20" />
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
            <span id="comun_TAGMsgAcrobat1_fondos_mutuales">
                Este sitio utiliza documentos PDF para lo cual deber&aacute; tener instalado Adobe Acrobat Reader.
            </span>
                </td>

            </tr>
            <tr height="30">
                <td align="center" class="A7NVerde"><span id="comun_TAGMsgAcrobat2_fondos_mutuales">Si no posee el Acrobat Reader puede obtenerlo haciendo click en:</span> </td>
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
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_myinfo.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TAGTitleInformesFondosMutuales" class="banner__title banner__title--modifier">Fact Sheets</h2>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TAGTitleInformesFondosMutuales" class="banner__title banner__title--modifier">Fact Sheets</h2>
            <ul class="banner__nav">
                <li><a id="TagHomeFAct" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleMutualFoundFact">MUTUAL FUNDS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="TAGTitleMutualFoundFactSheet">FACT SHEETS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="options">
        <div class="options__container container">
            <div class="options__grid">
                <a id="DownloadtitleBOF_fondos_mutuales" class="card-option" onclick="window.open('../vbtonline/resources/documentos/BOF.pdf','_blank');">
                    <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                    <span id="TAGTituloBOF_fondos_mutuales" class="card-option__title">Balanced Opportunity Fund</span>
                </a>
                <a id="DownloadtitleEuro_fondos_mutuales" class="card-option" onclick="window.open('../vbtonline/resources/documentos/EURO.pdf','_blank');">
                    <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                    <span id="TAGTituloEuro_fondos_mutuales" class="card-option__title">Euro Fund</span>
                </a>
                <a id="DownloadtitleLIQ_fondos_mutuales" class="card-option" onclick="window.open('../vbtonline/resources/documentos/LIQ.pdf','_blank');">
                    <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                    <span id="TAGTituloLIQ_fondos_mutuales" class="card-option__title">Liquidity Fund</span>
                </a>
            </div>
            <img class="options__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
        </div>
    </div>
<%--    <jsp:include page="../../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../../footer.jsp" />--%>

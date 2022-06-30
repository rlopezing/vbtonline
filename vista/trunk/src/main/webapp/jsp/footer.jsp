<%--
  Created by IntelliJ IDEA.
  User: panorama
  Date: 1/17/2022
  Time: 12:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
  <div class="footer__main">
    <img class="footer__mark" src="../vbtonline/resources/img/img_footer_mark_lower_right.png" alt="" />
    <div class="footer__primary container">
      <div class="footer__item footer__item_links">
        <span id="TagSubtitleFooter1" class="footer__title">ACCOUNT</span>
        <ul class="footer__nav">
          <li><a id="TagItemFooter1" onclick="seleccionarHome('SALDOS_CUENTAS')">Balances & transactions</a></li>
          <li><a id="TagItemFooter2" onclick="seleccionarHome('ESTADO_CUENTA')">Account statements</a></li>
        </ul>
<%--        <div class="footer__images">
          <a href="#">
            <img src="../vbtonline/resources/img/img_footer_playstore.png" alt="" /></a><a href="#">
          <img src="../vbtonline/resources/img/img_footer_applestore.png" alt="" /></a>
        </div>--%>
      </div>
      <div class="footer__item footer__item_links">
        <span id="TagSubtitleFooter2" class="footer__title">TRANSFER</span>
        <ul class="footer__nav">
          <li><a id="TagItemFooter3" onclick="seleccionarHome('TRANSFERENCIA_INTERNA_MIS_CTAS')">Between my linked accounts</a></li>
          <li><a id="TagItemFooter4" onclick="seleccionarHome('TRANSFERENCIA_INTERNA_TERCEROS')">To other clients in vbt</a></li>
          <li><a id="TagItemFooter5" onclick="seleccionarHome('TRANSFERENCIA_EXTERNA')">To other banks</a></li>
          <li><a id="TagItemFooter6" onclick="seleccionarHome('DIRECTORIO')">Templates</a></li>
          <li><a id="TagItemFooter7" onclick="seleccionarHome('CONSULTA_TRANSFERENCIAS')">Transfer history</a></li>
        </ul>
      </div>
<!--       <div class="footer__item">
        <span id="TagSubtitleFooter3" class="footer__title">TIME DEPOSITS</span>
        <ul class="footer__nav">
          <li><a id="TagItemFooter8" href="#">Balances & Transactions</a></li>
          <li><a id="TagItemFooter9" href="#">Certificates</a></li>
          <li><a id="TagItemFooter10" href="#">Maturities</a></li>
          <li><a id="TagItemFooter11" href="#">Opening</a></li>
        </ul>
      </div> -->
<!--       <div class="footer__item">
        <span class="footer__title">GENERAL INFO</span>
        <ul class="footer__nav">
          <li><a id="contratoWeb2" onclick="abrirContrato();">General Terms & Conditions</a></li>
          <li><a id="comisionesTag2"onclick="abrirPDF('comisiones');">Schedule of fees. Updated</a></li>
          <li><a onclick="abrirFATCA();">FATCA</a></li>
          <li><a id="tagManualActivacionTDC2" onclick="abrirManualTDC();">VISA Card Activation</a></li>
          <li><a id="tagCaymanPremier2" onclick="abrirCaymanPremier();">Cayman's Premier Statement on EU Listing Decision</a></li>
          <li><a id="tagCaymanFinance2"onclick="abrirCaymanFinance();">Cayman Finance Press Release on EU's recognition of Cayman Islands Cooperation</a></li>
        </ul>
      </div> -->
      <div class="footer__item footer__item_links">
        <span id="Footer_CREDIT_CARDS" class="footer__title">GENERAL INFO</span>
        <ul class="footer__nav">
          <li><a id="Footer_MOVIMIENTOS_FACTURAR" onclick="seleccionarOpcionMenu('MOVIMIENTOS_FACTURAR');">In Transit Transactions</a></li>
          <li><a id="Footer_ESTADO_CUENTA_TARJETA"onclick="seleccionarOpcionMenu('ESTADO_CUENTA_TARJETA');">Account statement</a></li>
          <li><a id="Footer_GESTION_RECLAMO" onclick="seleccionarOpcionMenu('GESTION_RECLAMO');">Claim Management</a></li>
          <li><a id="Footer_BLOQUEO_TARJETA" onclick="seleccionarOpcionMenu('BLOQUEO_TARJETA');">Schedule activation</a></li>
          <li><a id="Footer_PAGO_TARJETA" onclick="seleccionarOpcionMenu('PAGO_TARJETA');">Credit Card Payment</a></li>
          <li><a id="Footer_CONSULTA_PAGOS_TDC"onclick="seleccionarOpcionMenu('CONSULTA_PAGOS_TDC');">Payments History</a></li>
          <li><a id="Footer_BLOQUEO_EMERGENCIA_TDC"onclick="seleccionarOpcionMenu('BLOQUEO_EMERGENCIA_TDC');">Preventive Block/ Unblock</a></li>
        </ul>
      </div>
      <div class="footer__item">
        <span id="TagSubtitleFooter4" class="footer__title">ABOUT</span>
        <p id="TagDescriptionFooter" class="footer__description">
          VBT Bank & Trust, LTD. is a financial institution dedicated to
          offer its private and exclusive clients, a broad selection of
          banking products, trust and investment services; based on a clear
          philosophy of preserving assets, discretion, and high quality
          service
        </p>
        <span class="footer__phone">+1 345 9496917</span>
        <div class="footer__networks">
          <a href="#" target="_blank">
            <img src="../vbtonline/resources/img/icons/ic_footer_social_facebook.png" alt="" /></a><a href="#">
          <img src="../vbtonline/resources/img/icons/ic_footer_social_instagram.png" alt="" /></a><a href="#">
          <img src="../vbtonline/resources/img/icons/ic_footer_social_twitter.png" alt="" /></a>
        </div>
      </div>
    </div>
  </div>
  <div class="footer__menu">
    <div class="footer__secondary container">
      <img class="footer__logo" src="../vbtonline/resources/img/logo.svg" alt="" /><span class="footer__credits">© 2021 VBT Bank & Trust,
      LTD. - All Rights Reserved.</span>
      <ul class="footer__submenu">
        <li><a id="TagItemFooter12" href="#">Terms & Conditions </a></li>
        <li><a id="TagItemFooter13" href="#">Privacy Policy</a></li>
        <li><a id="TagItemFooter14" href="#https://www.websecurity.digicert.com/security-topics/what-is-ssl-tls-https" target="_blank">About ssl certificates</a></li>
      </ul>
    </div>
  </div>
</footer>


<%--
  Created by IntelliJ IDEA.
  User: panorama
  Date: 1/15/2022
  Time: 11:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="transfer-instructions" class="transfer-instructions">
    <div class="transfer-instructions__container container">
        <div class="titles-section">
            <img class="titles-section__icon"
                 src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt=""/>
            <div>
                <div id="TAGTitleInstruccionesV2" class="titles-section__title">
                    Wire transfer instructions
                </div>
                <p id="TAGSubTitleInstrucciones" class="titles-section__description">Standard Chartered Bank</p>
            </div>
        </div>
        <div class="transfer-instructions__coins">
            <a class="transfer-instructions__coin card-coin" id="DownloadtitleDolares1" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_US$.pdf','_blank');">
                <div class="card-coin__content">
                    <img class="card-coin__icon"
                         src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt=""/>
                    <div class="card-coin__titles">
                        <span class="card-coin__title">USD</span><span
                            class="card-coin__description">US Dollars</span>
                    </div>
                </div>
                <img class="card-coin__down" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
            </a><a class="transfer-instructions__coin card-coin" id="DownloadtitleEuros" onclick="window.open('../vbtonline/resources/documentos/Wire_Transfer_Instructions_SCB_EUR.pdf','_blank');">
            <div class="card-coin__content">
                <img class="card-coin__icon" src="../vbtonline/resources/img/ic_wiretransfer_instruction_eur.png"
                     alt=""/>
                <div class="card-coin__titles">
                    <span class="card-coin__title">EUR</span><span class="card-coin__description">Euros</span>
                </div>
            </div>
            <img class="card-coin__down" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
        </a>
        </div>
    </div>
</div>


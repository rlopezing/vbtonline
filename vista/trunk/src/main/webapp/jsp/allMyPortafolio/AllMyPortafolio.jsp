<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="tag_titulo_consolidado">Todo mi portafolio / Consolidado </h1>
</div>
<fieldset style="width:99%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="3%">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_PORTAFOLIO">This option allows you to check balances of each of your products with VBT Bank & Trust, grouped by portfolio number.
                        To view the details of a specific product, click on the code or name of the same.
                </span>
            </td>
            <td width="5%" align="center">
                <img src="resources/images/comun/impresora.gif" title="Print" id="imprimirConsolidado">
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
<div id="div_portafolio">


</div>
--%>
<!-- <div class="banner"><img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_portfolio.png"
                         alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tag_titulo_consolidado" class="banner__title banner__title--modifier">All my Portfolio</h2>
            <ul class="banner__nav">
                <li><a id="TagHome29" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li>ALL MY PORTFOLIO</li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" /></li>
                <li>CONSOLIDATED</li>
            </ul>
            <p id="TAG_INFO_PORTAFOLIO" class="banner__description banner__description--modifier">This option allows you to check balances of each of
                your products with VBT Bank & Trust, grouped by portfolio number. To view the details of a specific product,
                click on the code or name of the same.</p>
        </div>
    </div>
</div> -->
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tag_titulo_consolidado" class="banner__title banner__title--modifier">All my Portfolio</h2>
            <ul class="banner__nav">
                <li><a id="TagHome29" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="PORT_CONSOLIDATED">CONSOLIDATED</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="PORT_CONSOLIDATED_ALL_MY_PORTFOLIO">ALL MY PORTFOLIO</li>
            </ul>
            <p id="TAG_INFO_PORTAFOLIO" class="banner__description banner__description--modifier">This option allows you to check balances of each of
                your products with VBT Bank & Trust, grouped by portfolio number. To view the details of a specific product,
                click on the code or name of the same.</p>                
            <div id="ver_mas_todo_mi_portafolio" class="banner__description__show_more"><span>Ver más</span></div>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>

<div id="dialog-description-show-more"  style="display:none">
    <p></p>
</div>

<main class="main main--modifier">
    <div id="div_portafolio">

    </div>
<%--    <div class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png" alt="" />
                <div class="section__headline">
                    <div class="section__titles">
                  <span class="section__title">Client</span>
                        <div class="section__info"><span>ID No: 0000002456</span>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table"><span class="table__title">ACCOUNTS</span>
                    <table class="table__content">
                        <tr class="table__row">
                            <th class="table__item table__item--head">ACCOUNT NUMBER</th>
                            <th class="table__item table__item--head">CURRENCY</th>
                            <th class="table__item table__item--head">CURRENT BALANCE</th>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>0000004542</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>0000004542</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>0000004542</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="section">
        <div class="section__container container">
            <div class="section__top"><img class="section__icon" src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png"
                                           alt="" />
                <div class="section__headline">
                    <div class="section__titles"><span class="section__title">Client</span>
                        <div class="section__info"><span>ID No: 0000002456</span><img class="section__icon"
                                                                                      src="../vbtonline/resources/img/icons/ic_table_header_download.png" alt="" /></div>
                    </div>
                </div>
            </div>
            <div class="section__content">
                <div class="table"><span class="table__title">ACCOUNTS</span>
                    <table class="table__content">
                        <tr class="table__row">
                            <th class="table__item table__item--head">ACCOUNT NUMBER</th>
                            <th class="table__item table__item--head">CURRENCY</th>
                            <th class="table__item table__item--head">CURRENT BALANCE</th>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>0000004542</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>0000004542</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>0000004542</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                    </table>
                </div>
                <div class="table"><span class="table__title">CREDIT CARDS</span>
                    <div id="table2">
                        <table class="table__content">
                            <tr class="table__row">
                                <th class="table__item table__item--head">INVESTMENTS</th>
                                <th class="table__item table__item--head">CURRENCY</th>
                                <th class="table__item table__item--head">TOTAL IN CURRENCY</th>
                            </tr>
                            <tr class="table__row">
                                <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                              alt="" /><img class="table__logo" src="../vbtonline/resources/img/img_table_card_visa.png" alt="" /><span>**** -
                      **** - **** - 6017</span></td>
                                <td class="table__item">
                                    <div class="currency"><img class="currency__icon"
                                                               src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                        <div class="currency__titles"><span class="currency__title">USD</span><span
                                                class="currency__description">US Dollars</span></div>
                                    </div>
                                </td>
                                <td class="table__item"> <span>2.696,93</span></td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div class="table"><span class="table__title">MUTUAL FUNDS</span>
                    <table class="table__content">
                        <tr class="table__row">
                            <th class="table__item table__item--head">INVESTMENTS</th>
                            <th class="table__item table__item--head">CURRENCY</th>
                            <th class="table__item table__item--head">TOTAL IN CURRENCY</th>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>Venecredit Liquidity Fund</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_usd.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">USD</span><span
                                            class="currency__description">US Dollars</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                        <tr class="table__row">
                            <td class="table__item"> <img class="table__icon" src="../vbtonline/resources/img/icons/ic_table_cell_item_arrow.png"
                                                          alt="" /><span>Venecredit Euro Fund</span></td>
                            <td class="table__item">
                                <div class="currency"><img class="currency__icon"
                                                           src="../vbtonline/resources/img/ic_wiretransfer_instruction_eur.png" alt="" />
                                    <div class="currency__titles"><span class="currency__title">EUR</span><span
                                            class="currency__description">Euros</span></div>
                                </div>
                            </td>
                            <td class="table__item"> <span>2.696,93</span></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>--%>
<%--    <jsp:include page="../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../footer.jsp"/>--%>

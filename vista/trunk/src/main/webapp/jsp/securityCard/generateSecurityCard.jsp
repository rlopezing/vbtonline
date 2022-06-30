<%--
  Created by IntelliJ IDEA.
  User: Mary Bernot
  Date: 19/09/13
  Time: 02:41 PM
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div>

    <h1 id="TarjetaCoordenadas_TAG_Title">Generar tarjeta de coordenada </h1>
</div>
<div id="div_tarjeta_coordenada">
    <div class="titulo_tarjeta_coord">

        <span id="TarjetaCoordenadas_Seguridad">Seguridad</span>
        <div class="descripcion_tarjeta_coord">


            <span id="TarjetaCoordenadas_TAG_Descripcion"> La tarjeta de coordenadas le permitirá realizar de manera segura sus operaciones, tales como:  Transferencias a otros bancos, afiliación de destinos de transferencia, actualización de parámetros personales, etc.</span><BR><BR>
           <B><span id="TarjetaCoordenadas_TAG_Subtitulo" class="">Pasos para generar su Tarjeta de Coordenadas></span>     </B><br>
                <span id="TarjetaCoordenadas_TAG_Pasos" class="">

                  <ol>



                      <li id="TarjetaCoordenadas_TAG_step1">Presione el botón Generar. </li>
                      <li id="TarjetaCoordenadas_TAG_step2">El sistema generará una tarjeta de coordenadas única asociada. Imprimala</li>
                      <li id="TarjetaCoordenadas_TAG_step3">Para la ctivación de dicha tarjeta dirigase a la opción del menú: Diseñe su Banco / Activar Tarjeta de Coordenadas </li>


                  </ol>


                    <span id="TarjetaCoordenadas_TAG_advice1">En caso de robo, extravío, deterioro o si presume que su tarjeta fue expuesta a terceras personas, vuelva a generar otra Tarjeta de Coordenadas siguiendo los pasos antes descritos, la tarjeta anterior será bloqueada automáticamente.</span>

                </span> <br>
        </div>
    </div>

    <div class="botones_salir">

        <table SUMMARY='tabla'>
            <tbody>
            <tr>
                <td class="datos" colspan="2">
                      <input type="hidden" id="TarjetaCoordenadas_status" value="">
                    <input type="hidden" id="TarjetaCoordenadas_mensaje" value="">

                      <B>  <span
                                id="TarjetaCoordenadas_TAG_Usuario">Usuario</span>:</B>   <span
                        id="TarjetaCoordenadas_usuario"></span>
                </td>
            </tr>

           &lt;%&ndash; <tr>
                <td class="datos" colspan="2">
                    <B><span class="label_TarjetaCoordActivacion_serial"
                              id="TarjetaCoordActivacion_TAG_Serial">Serial</span>:  </B> <span  id="TarjetaCoordenadas_serial">  </span>
                </td>
            </tr>
               &ndash;%&gt;
            <tr>
                <td class="datos" colspan="2">
                    <B>  <span  id="TarjetaCoordenadas_status">  </span>  </B>
                </td>
            </tr>

            <tr>
                <td class="datos">
                    <B> <span id="TarjetaCoordenada_msg"></span> </B>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td class="datos" colspan="2">
                    <input type="button" id="TarjetaCoordenadas_BTN_Generar" value="Generar" class="botonGrande">
                </td>
            </tr>
            <tr>
                <td class="datos" colspan="2">

                </td>
            </tr>
            </tbody>
        </table>
       <div id="TarjetaCoordenadas_activar" class="datos descripcion_tarjeta_coord">
            <span id="TarjetaCoordenadas_TAG_advice2">Recuerde que dispone de 24 horas para activar su Tarjeta de Coordenadas. Para activar su tarjeta presione <a onClick="javascript:cargarActivacionTarjetaCoordenadasJSONData();" class="V8NBVerde"> aquí </a> </span>    <BR><BR>
        </div>
    </div>


</div>
--%>
<%--
<div class="banner">
    <img
            class="banner__img banner__img--modifier"
            src="../vbtonline/resources/img/bg_myinfo.png"
            alt="Banner Home"
    />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="TarjetaCoordenadas_TAG_Title" class="banner__title banner__title--modifier">Order Code Card</h2>
            <ul class="banner__nav">
                <li><a id="TagHome27" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="Tag_link_desing_your_bank">DESIGN YOUR BANK</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li id="Tag_link_order_code_pack">ORDER CODE CARD</li>
            </ul>
            <p id="TarjetaCoordenadas_TAG_Descripcion" class="banner__description banner__description--modifier">
                The use of the code card will allow you to safely execute your
                transactions: transfers to other banks, affiliation of transfer
                destination, updating of personal settings, etc.
            </p>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="TarjetaCoordenadas_TAG_Title" class="banner__title banner__title--modifier">Order Code Card</h2>

            <ul class="banner__nav">
                <li><a id="TagHome27" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="Tag_link_desing_your_bank">DESIGN YOUR BANK</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="Tag_link_order_code_pack">ORDER CODE CARD</li>
            </ul>
            <p id="TarjetaCoordenadas_TAG_Descripcion" class="banner__description banner__description--modifier">
                The use of the code card will allow you to safely execute your
                transactions: transfers to other banks, affiliation of transfer
                destination, updating of personal settings, etc.
            </p>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="options">
        <div class="options__container container">
            <img
                    class="options__icon"
                    src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_payments.png"
                    alt=""
            /><span class="options__title" id="tag_title_generate_your_code_card" >Generate your code card</span
        ><span class="options__description" id="tag_options_follow_steps">Follow steps</span>
            <div class="options__grid">
                <div class="cursor-none"
                ><img
                        class="card-option__icon"
                        src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_payments.png"
                        alt=""
                /><span class="card-option__title card-option__title--modifier" id="tag_step_1"
                >STEP 1</span
                ><span id="TarjetaCoordenadas_TAG_step1" class="card-option__description"
                >Select the option Order Code Card 67</span
                ></div>
                <div class="cursor-none"><img
                    class="card-option__icon"
                    src="../vbtonline/resources/img/icons/ic_printer.png"
                    alt="" 
            /><span class="card-option__title card-option__title--modifier" id="tag_step_2"
            >STEP 2</span
            ><span id="TarjetaCoordenadas_TAG_step2" class="card-option__description"
            >Print the code card generated by the system</span
            ></div
            ><div class="cursor-none"
            ><img
                    class="card-option__icon"
                    src="../vbtonline/resources/img/icons/ic_dpdown_creditcards_payment_ok.png"
                    alt=""
            /><span class="card-option__title card-option__title--modifier" id="tag_step_3"
            >STEP 3</span
            ><span id="TarjetaCoordenadas_TAG_step3" class="card-option__description"
            >To activate the code card, select from the menu Design your
            Bank, the option Activate Code Card.</span
            ></div>
            </div>
            <div style="display: flex;flex-direction: row;flex-flow: wrap;">
                <p id="TarjetaCoordenadas_TAG_advice1" class="options__note" style="flex: 0 0 50%;">
                    In case of theft, loss, damage or presumed card was exposed to other
                    parties, order a new code card and the previous one will be
                    automatically blocked.
                </p>
                <div class="options__box" style="flex: 0 0 50%;">
                    <input type="hidden" id="TarjetaCoordenadas_status" value="">
                    <input type="hidden" id="TarjetaCoordenadas_mensaje" value="">
                    <span id="TarjetaCoordenadas_TAG_Usuario">USER</span>
                    <span id="TarjetaCoordenadas_usuario" class="options__user">Paul Phillips</span>
                </div>
            </div>
            <button id="TarjetaCoordenadas_BTN_Generar" class="options__button button">ORDER CODE CARD</button>
        </div>
    </div>
<%--    <jsp:include page="../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../footer.jsp" />--%>
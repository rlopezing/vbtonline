<%--
  Created by IntelliJ IDEA.
  User: Mary Bernot
  Date: 19/09/13
  Time: 02:41 PM
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<div id='tituloOrigen'>

</div>
<fieldset class=" div_consultaTransfers">
 <div id="pasosNavegacion" style="padding-top: 8px">
   <table width='500px' cellspacing="0" cellspadding="0" align="center">
       <tr>
           <td align="center"><div id="navPaso1" class="actual_metodos_validacion"></div></td>
           <td align="center"><div id="navPaso2" class="anterior_metodos_validacion"></div></td>
           <td align="center"><div id="navPaso3" class="siguiente_metodos_validacion"></div></td>
        </tr>
       <tr>
           <td align="center"><label id="TAGInstruccionesMetodo" class="datos negrita">Instructions</label></td>
           <td align="center"><label id="TAGSeleccionarMetodo" class="datos">Select Validation Method</label></td>
           <td align="center"><label id="TAGValidarOperacion" class="datos">Validate Operation</label></td>
      </tr>
   </table>
     <table width='500px' cellspacing="0" cellspadding="0" align="center" style="padding-top: 20px">
         <tr>
             <td align="center"> <label class="datos negrita oculto" id="TAGSerialTarjetaMTD"></label></td>
         </tr>
     </table>
 </div>

 <div id="paso1" class="divPaso"  style="display:none">
     <br>
     <br>
     <div style="width:800px;margin:auto;text-align: justify">
         <table align="center">
             <tr>
                 <td> <img width="276px" src="../vbtonline/resources/images/instruccionesImg.jpg"></td>
                 <td style="padding-left:30px">
                     <div class="divTituloMetodos">
                         <label class="datos negrita" id="TAGTituloProteccion">Para su protección, esta operación requiere ser validada, usted podrá seleccionar entre alguno de los dos métodos de validación:</label>
                     </div>
                     &lt;%&ndash;<br>&ndash;%&gt;
                     &lt;%&ndash;<label class="datos" id="TAGTituloProteccion1"> Para validar esta operación, usted podrá seleccionar entre alguno de los dos métodos de validación:</label>&ndash;%&gt;
                     <ul>
                         <li class="datos justificado" id="TAGInstrucciones1">Clave de confirmación (vía SMS)

                             El sistema le enviará un SMS a su número telefónico con una clave de confirmación

                             temporal. Deberá introducir dicha clave para finalizar la operación.
                         </li>
                         <li class="datos justificado" id="TAGInstrucciones2">Tarjeta de coordenadas.

                             Deberá introducir el valor de dos coordenadas (indicadas por el sistema) de su tarjeta

                             de coordenadas. Si aún no posee tarjetas de coordenadas puede generarla a través de

                             la opción Diseñe su Banco / Generar Tarjeta de Coordenadas y seguir los pasos para su

                             activación.</li>
                    </ul>
                 </td>
             </tr>
         </table>
     </div>
     <br>
     <br>
     <br>
 </div>
 <div id="paso2" class="divPaso" style="display:none;text-align: justify">
     <br>
     <div style="width:800px;margin:auto;">
         <table align="center">
             <tr>
                 <td colspan="2" align="center">
                     <label class="datos negrita" id="TAGSeleccionarTitulo"> Escoja el método de validación con el cual quiere validar su operación</label>
                 </td>
             </tr>
             <tr>
                 <td>
                     <table>
                         <tr>
                             <td>
                                 <div class="div_selec_sms img-with-text">
                                     <img id="selec_sms" style="cursor: pointer" width="47px" height="80px" src="resources/images/celular.png">
                                 </div>
                             </td>
                             <td valign="middle">
                                 <table>
                                     <tr>
                                         <td><label class="datos negrita" id="TAGClaveConfirmacionSMS" >Clave de confirmación (SMS)</label></td>
                                     </tr>
                                     <tr>
                                         <td><input type="radio" name="metodoSeleccionado"class="telefonoMetodos"  onChange="seleccionarMetodo(this,'sms');" /><label class="datos" id="TAGNumeroTelefonico" >Tarjeta de coordenadas</label> <input type='tel' id='countryCodeVM'  size='4' maxlength='4'  disabled="true" style='color:white;background-color:white;border:none;width:40px'><label class="datos" id="TAGNumeroTelefonicoValor" ></label></td>
                                     </tr>
                                 </table>
                             </td>
                         </tr>
                         <tr>
                             <td>
                                 <div class="div_selec_tarjetaCoord img-with-text">
                                     <img id="selec_tarjetaCoord" width="100px" height="60px" style="cursor: pointer" src="resources/images/tarjeta.png">
                                 </div>
                             </td>
                             <td valign="middle">
                                 <table>
                                     <tr>
                                         <td> <label class="datos negrita" id="TAGClaveConfirmacionTarjeta" >Tarjeta de coordenadas</label></td>
                                     </tr>
                                     <tr>
                                         <td style="text-align:justify"><input type="radio" name="metodoSeleccionado" class="tarjetaMetodos" onChange="seleccionarMetodo(this,'TarjetaCoordenada');"/><label class="datos" id="TAGSerialTarjeta" > Serial de la tarjeta:</label><label class="datos" id="TAGSerialTarjetaValor" ></label></td>
                                     </tr>
                                 </table>
                             </td>
                         </tr>
                     </table>
                 </td>
                 <td width='38%' style="padding-top:20px;">
                     <label class="datos negrita" id="TAGNotaMetodo"> Nota</label>
                     <br>
                     <ul>
                         <li class="datos justificado" id="TAGNotaMetodo1">Por razones de seguridad se ha ocultado parte de su número telefónico y tarjeta de coordenadas con asteriscos (***).</li>
                         <li class="datos justificado"  id="TAGNotaMetodo2">
                             El número de teléfono que ve en esta página es el que se encuentra actualmente asociado

                             a su usuario. Si desea actualizarlo, podrá hacerlo después, contactando a su asesor o

                             ejecutivo.
                         </li>
                     </ul>
                 </td>
             </tr>

         </table>
     </div>
     <br>
     <br>
     <br>
 </div>
 <div id="paso3" class="divPaso3" style="display:none">
     <div id="div_tarjeta_coordenadas" class=" div_opc_clave_transf oculto"  style="text-align: justify">

         <table align="center">
             <tr>
                 <td>
                     <span id="lbl_serial"  class="datos"></span>
                     <div id="tarjeta_coordenadas"></div>
                     <div class="coordenadas">
                         <div id="caja_coord_1" class="label_coordenadas opc1">
                             <span id="lbl_caja_coord_1" class="campo_coordenada"></span><input id="coordenada_1" type="text" size="1" style="width: 45px"  maxlength="3"/>
                         </div>
                         <div id="caja_coord_2" class="label_coordenadas opc2">
                             <span id="lbl_caja_coord_2" class="campo_coordenada"></span> <input id="coordenada_2" type="text" size="1" style="width: 45px" maxlength="3" />
                         </div>
                     </div>
                 </td>
                 <td height="50" >

                                 <ul>
                                     <li class="datos justificados" id="TAGTarjetaCoordenadas1">Ubique en su tarjeta de coordenadas el valor de cada una de las coordenadas que

                                         solicita el sistema. Recuerde que cada coordenada consta de tres dígitos y corresponde al

                                         punto donde coincide uno de los números de la fila con una de las letras de la columna.</li>
                                     <li class="datos justificado"  id="TAGTarjetaCoordenadas2">Introduzca los valores de las coordenadas en el campo de texto correspondiente y

                                         presione "Aceptar".
                                     </li>
                                 </ul>
                 </td>
             </tr>
         </table>

     </div>
     <div id="div_correo_clave_transf" class="div_correo_clave_transf div_opc_clave_transf oculto">
         <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
             <tr>
                 <td height="50" >
                     <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                         <tr>
                             <td width="100%" height="50" class="" align="center">

                                 <b><span id="comun_TAGClaveOperacion" class="datos">Enter your Transaction Key</span></b>
                                 <input type="password" name="pwdClaveConfirmTransfer" tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmTransfer_TOB">
                             </td>
                         </tr>
                     </TABLE>
                 </td>
             </tr>
         </TABLE>
     </div>
     <div id="div_sms_clave" class="div_sms_clave div_opc_clave_transf oculto" style="text-align: justify" >
         <TABLE width="100%" border="0" cellspacing="0" cellpadding="5">
             <tr>
                 <td height="50" >
                     <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
                         <tr>
                             <td width="100%" height="50" class="" align="center">

                                 <b><span id="comun_TAGClaveOperacionSMS" class="datos">Confirmation code</span></b>
                                 <input type="password"  tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmSMS">
                             </td>
                         </tr>
                     </TABLE>
                 </td>
                 <td height="50" >
                     <TABLE  align="center" width="500px" border="0" cellspacing="0" cellpadding="0">
                         <tr>
                             <td width="100%" height="50" class="" align="center">
                                 <ul>
                                     <li class="datos justificado" id="TAGClaveSMS1">1. Consulte en su teléfono móvil el mensaje con la clave temporal para confirmación de su

                                         operación.</li>
                                     <li class="datos justificado"  id="TAGClaveSMS2">2. Introduzca la clave en el campo de texto y presione "Siguiente".
                                     </li>
                                 </ul>
                             </td>
                         </tr>
                     </TABLE>
                 </td>
             </tr>
         </TABLE>
     </div>
 </div>
 <div id="pasoNoMetodos" class="divPaso" style="display:none">
     <br>
     <br>
     <br>
     <br>
     <div style="width:600px;margin:auto;text-align:center"><span id="TAGNOMetodos" class="datos">
         No posee un teléfono registrado o una tarjeta de coordenadas activa para validar su operación.

         Para poder realizar su operación debe generar su tarjeta de coordenadas a través de la opción

         Diseñe su Banco / Generar Tarjeta de Coordenadas y luego contactar a su Asesor o Ejecutivo de

         Cuentas para la activación de la misma.
     </span>
     </div>
     <br>
     <br>
     <br>
 </div>
 <div id="pasoNoMetodosActivacion" class="divPaso" style="display:none">
     <br>
     <br>
     <br>
     <br>
     <div style="width:600px;margin:auto;text-align: justify"><span id="TAGNOMetodosActivacion" class="datos">
         No posee un teléfono registrado para validar su operación. Para poder realizar su operación debe generar su tarjeta de coordenadas a través de la opción

         Diseñe su Banco / Generar Tarjeta de Coordenadas y luego contactar a su Asesor o Ejecutivo de

         Cuentas para la activación de la misma.
     </span>
     </div>
     <br>
     <br>
     <br>
 </div>
 <div id="pasoActivacion" class="divPaso" style="display:none">
     <br>
     <br>
     <div style="width:800px;margin:auto;text-align: justify">
         <table align="center">
             <tr>
                 <td> <img width="276px" src="../vbtonline/resources/images/instruccionesImg.jpg"></td>
                 <td style="padding-left: 30px;">
                     <div class="divTituloMetodos">
                         <label class="datos negrita" id="TAGTituloProteccionActivacion">Instrucciones Activacion de tarjeta de Coordenadas</label>
                     </div>
                     <br>
                     <span class="datos" id="TAGTituloProteccionActivacion1">A continuación puede proceder a activar su tarjeta de coordenadas, siguiendo los pasos que le indique el sistema.</span>   <p></p>
                     <span class="datos "  id="TAGTituloProteccionActivacion2">Se le informa que durante este proceso, el sistema le enviará una clave de confirmación, vía SMS, a su número telefónico. Deberá introducir dicha clave cuando el sistema lo solicite.</span>


                 </td>
             </tr>
         </table>
     </div>
     <br>
     <br>
     <br>
 </div>
    <div id="navNota">
        <table align="center">
            <tr>
                <td><label class="datos" id="TAGInstruccionesSiguiente">Presione el botón "Siguiente" para continuar con la operación</label></td>
            </tr>
        </table>

    </div>
</fieldset>
<fieldset class=" div_consultaTransfers">

    <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
        <tr>
            <td align="right">
                <input  type="button" id="btnPrev" value="Previous" class="boton oculto">
                <input  type="button" id="btnNext" value="Next" class="boton">
                <input  type="button" id="btnAceptar" value="Accept" class="boton oculto">
                <input  type="button" id="btnCancelar" value="Cancel" class="boton">
            </td>
        </tr>

    </table>
</fieldset>--%>
<%--<div class="banner">
    <img
            class="banner__img banner__img--modifier"
            src="../vbtonline/resources/img/bg_myinfo.png"
            alt="Banner Home"
    />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="tituloOrigen" class="banner__title banner__title--modifier">
                Order Code Card
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome28" href="Home">HOME</a></li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>TRANSFER</li>
                <li>
                    <img
                            src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png"
                            alt=""
                    />
                </li>
                <li>TO OTHERS BANKS</li>
            </ul>
            <p class="banner__description banner__description--modifier">
                The use of the code card will allow you to safely execute your
                transactions: transfers to other banks, affiliation of transfer
                destination, updating of personal settings, etc.
            </p>
        </div>
    </div>
</div>--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="tituloOrigen" class="banner__title banner__title--modifier">
                Order Code Card
            </h2>
<!--             <ul class="banner__nav">
                <li><a id="TagHome28" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="">TRANSFER</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="">TO OTHERS BANKS</li>
            </ul> -->
        <!--     <p class="banner__description banner__description--modifier">
                The use of the code card will allow you to safely execute your
                transactions: transfers to other banks, affiliation of transfer
                destination, updating of personal settings, etc.
            </p> -->
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="options">
        <div
                class="options__container container"
        >
            <img
                    class="options__icon"
                    src="../vbtonline/resources/img/icons/ic_login_security_tips.png"
                    alt=""
            /><span id="TagMethods" class="options__title" style="margin-top: 0;">Methods</span
        ><span id="TagMethodsSteps" class="options__description">Follow steps</span>
            <div id="pasosNavegacion" class="options__check check" style="margin-top: 2rem;">
                <div id="navPaso1" class="check__item check__item--uncomplete">
                    <img id="iconPaso1"
                            class="check__icon"
                            src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                            alt=""
                    />
                    <span id="TAGInstruccionesMetodo" class="check__text"
                    >Instructions</span
                    >
                </div>
                <div id="navPaso2" class="check__item">
                    <img id="iconPaso2"
                            class="check__icon"
                            src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                            alt=""
                    />
                    <span id="TAGSeleccionarMetodo" class="check__text">Selecting validation methods</span>
                </div>
                <div id="navPaso3" class="check__item">
                    <img id="iconPaso3"
                            class="check__icon"
                            src="../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png"
                            alt=""
                    />
                    <span id="TAGValidarOperacion" class="check__text"
                    >Validating Transaction</span
                    >
                </div>
            </div>
            <!-- <span class="oculto" id="TAGSerialTarjetaMTD" style="margin-top: 6rem;"></span> -->
            <div id="paso1" class="options__content" style="display:none;">
                <div class="options__descriptions" >
                    <p id="TAGTituloProteccion">
                        You can proceed to activate your code card, following the steps
                        indicated by the system.
                    </p>
                    <ul class="list">
                        <li id="TAGInstrucciones1"></li>
                        <li id="TAGInstrucciones2"></li>
                    </ul>
                </div>
            </div>
            <div id="pasoActivacion" class="options__content" style="display:none">
                <div class="options__descriptions">
                    <p id="TAGTituloProteccionActivacion1">
                        You can proceed to activate your code card, following the steps
                        indicated by the system.
                    </p>
                    <p id="TAGTituloProteccionActivacion2">
                        During this process, the system will send you a confirmation
                        code via SMS to your telephone number. You must enter this
                        password when prompted.
                    </p>
                </div>
            </div>
            <div id="paso2" style="display: none" class="options__content">
                <div class="options__validations validations">
                    <div class="validations__item">
                        <span class="validations__title" id="TAGClaveConfirmacionSMS">Confirmation code (SMS)</span>
                        <div class="validations__content">
                            <input
                                    class="telefonoMetodos"
                                    type="radio"
                                    name="metodoSeleccionado"
                                    onchange="seleccionarMetodo(this,'sms');"
                            />
                            <label id="TAGNumeroTelefonico">Telephone number:</label>
                            <span id="TAGNumeroTelefonicoValor">(+58)*******1601</span>
                        </div>
                    </div>
                    <div class="validations__item">
                        <span class="validations__title" id="TAGClaveConfirmacionTarjeta">Confirmation code (SMS)</span>
                        <div class="validations__content">
                            <input type="radio"
                                   name="metodoSeleccionado"
                                   class="tarjetaMetodos"
                                   onchange="seleccionarMetodo(this,'TarjetaCoordenada');">
                            <label id="TAGSerialTarjeta">Telephone number:</label>
                            <span id="TAGSerialTarjetaValor">(+58)*******1601</span>
                        </div>
                    </div>
                </div>
                <div class="options__note options__note--validation">
                    <span class="options__subtitle" id="TAGNotaMetodo">Note</span>
                    <ul class="list">
                        <li id="TAGNotaMetodo1"></li>
                        <li id="TAGNotaMetodo2"></li>
                    </ul>
                </div>
            </div>
            <div id="paso3" style="display: none" class="options__content">
                <div id="div_tarjeta_coordenadas" class="options__password">
                    <p
                            id="lbl_serial"
                            class="options__note options__note--validation"
                    >
                        Check you mobile phone for the SMS providing temporary code to
                        confirm you transaction.
                    </p>
                    <div class="">
                        <div id="tarjeta_coordenadas"></div>
                        <div class="box-coords">
                            <div id="caja_coord_1" class="box-coord box-coord--1">
                                <span id="lbl_caja_coord_1" class=""></span>
                                <input id="coordenada_1" type="text" size="1" class="input input--form"  maxlength="3"/>
                            </div>
                            <div id="caja_coord_2" class="box-coord box-coord--2">
                                <span id="lbl_caja_coord_2" class=""></span>
                                <input id="coordenada_2" type="text" size="1" class="input input--form" maxlength="3" />
                            </div>
                        </div>
                    </div>
                    <div class="options__content">
                        <div class="options__descriptions">
                            <p id="TAGTarjetaCoordenadas1">
                                You can proceed to activate your code card, following the steps
                                indicated by the system.
                            </p>
                            <p id="TAGTarjetaCoordenadas2">
                                During this process, the system will send you a confirmation
                                code via SMS to your telephone number. You must enter this
                                password when prompted.
                            </p>
                        </div>
                    </div>
                </div>
                <div id="div_sms_clave" class="options__password">
                        <p  id="TAGClaveSMS1" class="options__note options__note--validation" >Check you mobile phone for the SMS providing temporary code to confirm you transaction.</p>
                        <div>
                            <span class="oculto" id="TAGSerialTarjetaMTD" style="margin-top: 0;"></span>
                            <div class="PanelInputSms">
                                <div class="options__box options__box--validation">
                                <!--  <span class="options__confirmation">USER</span>
                                    <span class="options__user">SMS</span> -->
                                    <span id="Confirmation_code_val" class="options__user">SMS</span>
                                </div>
                                <input
                                        id="pwdClaveConfirmSMS"
                                        type="password"
                                        class="options__input input input--inline"
                                        placeholder="..."
                                />
                            </div>
                            <input type="password" name="pwdClaveConfirmTransfer" class="oculto" tabindex="1" maxlength="15" size="17" id="pwdClaveConfirmTransfer_TOB">
                        </div>
                </div>



            </div>
            <div id="pasoNoMetodos" style="display: none" class="options__content">
                <p
                        id="TAGNOMetodos"
                        class="options__note options__note--validation"
                >
                    You do not have an active telephone number. Contact your account officer or advisor.
                </p>
            </div>
            <div class="options__between">
                <button id="btnPrev" class="button button--blue oculto">
                    PREVIOUS
                </button>
                <button id="btnCancelar" class="button button--blue">CANCEL</button>
                <button id="btnNext" class="button">NEXT</button>
                <button id="btnAceptar" class="button oculto">ACCEPT</button>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../footer.jsp" />--%>

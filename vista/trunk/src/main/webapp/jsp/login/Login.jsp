<%@ page import="java.util.Random" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
    <div id="container_demo" >
<%--        <!-- Los TIPS de seguridad al usuario -->--%>
<%--        <div id="login_tips">--%>
<%--            <h3 id="login_TAGTipsSeguridad">Consejos de Seguridad</h3>--%>
<%--            <ul>--%>
<%--                <li id="login_TAGTip1">Antes de ingresar sus datos en esta pantalla, verifique si la direcci&oacute;n en la barra superior es: <a href="https://secure.vbtbank.com/">https://secure.vbtbank.com/</a></li>--%>
<%--                <li id="login_TAGTip2">Adicionalmente, verifique el Certificado de Seguridad emitido por VeriSign haciendo doble click en el icono del candado cerrado que se encuentra en la parte inferior derecha de la ventana.</li>--%>
<%--                <li id="login_TAGTip3">Los datos de uso de sus tarjetas o cuentas son personales y confidenciales, datos como n&uacute;mero de tarjeta, clave personal, fecha de expiraci&oacute;n no deben ser suministrados a terceros en circunstancias que no sean el acceso a sitios que usted conozca y que cuenten con los certificados de seguridad requeridos para codificar y preservar su informaci&oacute;n.</li>--%>
<%--                <li id="login_TAGTip4">No suministre estos datos por ninguna v&iacute;a, bien sea pantallas o v&iacute;nculos a p&aacute;ginas web, archivos adjuntos, comunicaciones electr&oacute;nicas, incluso llamadas telef&oacute;nicas de terceros, pues no es pol&iacute;tica del Banco en ning&uacute;n caso solicitar sus datos por ninguno de estos mecanismos.</li>--%>
<%--                <li id="login_TAGTip5">Evite acceder a VBT Bank & Trust Online desde computadores de uso p&uacute;blico.</li>--%>
<%--                <li id="login_TAGTip6">Use con el rat&oacute;n el teclado virtual para ingresar su clave.</li>--%>
<%--                <li id="login_TAGTip7"><strong>Para su seguridad y pleno funcionamiento del sistema utilice la ultima version de su browser.</strong></li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <!-- Los formularios de acceso y de olvido de clave -->--%>
<%--        <div id="wrapper" class="wrapper">--%>
<%--            <!-- FORMULARIO DE LOGIN -->--%>
<%--            <div id="login" class="form login" >--%>
<%--                    <h1 id="login_pagesubtitle">INICIO DE SESION</h1>--%>
<%--                    CLI-2 <p id="espacio1">--%>
<%--                     </p>--%>
<%--                        <label id="login_FIELDTxtLogin" for="username" class="uname" data-icon="u" >Nombre de usuario:</label>--%>
<%--                        <input id="username" name="username" required="required" type="text" />--%>

<%--                     <p id="espacio2">--%>
<%--                     </p>--%>
<%--                        <label id="login_FIELDPwdClave" for="pwdClave" class="youpasswd" data-icon="p">Clave de acceso:</label>--%>
<%--                        <input id="pwdClave" value="VBTONLINE12" name="pwdClave" required="required" type="password" class="teclado" />--%>

<%--                    <span  id="mensaje" class="errorLogin"></span>--%>
<%--                          <table align="center">--%>
<%--                              <tr>--%>
<%--                                  <td>--%>
<%--                                      <div id="showCaptcha" style="display: none">--%>
<%--                                          <table>--%>
<%--                                              <tr>--%>
<%--                                                  <td colspan="2">--%>
<%--                                                      <img id="imgCaptcha" src="jsp/captcha.jsp">--%>
<%--                                                  </td>--%>
<%--                                              </tr>--%>
<%--                                              <tr>--%>
<%--                                                  <td>--%>
<%--                                                     <input id="recaptcha_response_field" type="text" value="" size="20" class="inputChaptcha">--%>
<%--                                                  </td>--%>
<%--                                                  <td style="padding-top:5px;">--%>
<%--                                                     <img id="refreshCaptcha" style="cursor: pointer" width="25px" src="../vbtonline/resources/images/btn_actualizar.png">--%>
<%--                                                  </td>--%>
<%--                                              </tr>--%>
<%--                                          </table>--%>
<%--                                             &lt;%&ndash;<script type="text/javascript" src="https://www.google.com/recaptcha/api/challenge?k=6Lf0H-0SAAAAAA4XQAEnQIIXrEcsrqAioeVv_sh_"></script>&ndash;%&gt;--%>
<%--                                      </div>--%>
<%--                                   </td>--%>
<%--                             </tr>--%>
<%--                          </table>--%>

<%--                    <p id="botonLogin" class="login button espacioRelleno">--%>
<%--                        <button  id="btn__lgn_limpiar" class=""> Limpiar </button>--%>
<%--                        <button  id="btn__lgn_aceptar"  class="input"> Entrar  </button>--%>
<%--&lt;%&ndash;                        </br><a onclick="forgotPass();" id="btn_lgn_olvidopass" class="olvidopass">¿Olvidó su contraseña?</a>&ndash;%&gt;--%>
<%--                    </p>--%>
<%--            </div>--%>

<%--            <div id="div_carga_forgot" class="imagen_carga_forgot oculto">--%>
<%--                <img class="recuadroCargaImg"  src="../vbtonline/resources/images/ajax-loader.gif" alt="" />--%>
<%--            </div>--%>

<%--&lt;%&ndash;            <div id="forgotpass" class="form login" style="display:none;">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <h1 id="login_pagesubtitle2">SE TE OLVID&Oacute; TU CONTRASE&Ntilde;A</h1>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <p id="espacio3">&ndash;%&gt;--%>
<%--&lt;%&ndash;                </p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <label id="login_FIELDTxtLogin1" for="username2" class="uname" data-icon="u" >Nombre de usuario:</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input id="username2" name="username2" required="required" type="text" />&ndash;%&gt;--%>

<%--&lt;%&ndash;                <p id="espacio4">&ndash;%&gt;--%>
<%--&lt;%&ndash;                </p>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <label id="login_FIELDCorreo" for="correo" class="youpasswd" data-icon="e">Correo Electrónico:</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <input id="correo" name="correo" required="required" type="email"/>&ndash;%&gt;--%>

<%--&lt;%&ndash;                <span  id="mensaje2" class="errorForgot"></span>&ndash;%&gt;--%>
<%--&lt;%&ndash;                <p id="espacio5">&ndash;%&gt;--%>
<%--&lt;%&ndash;                <table align="center">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                            <div id="showCaptcha2">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                <table>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <td colspan="2">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <img id="imgCaptcha2" src="jsp/captcha.jsp">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <input id="recaptcha_response_field2" type="text" value="" size="20" class="inputChaptcha">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <td style="padding-top:5px;">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <img id="refreshCaptcha2" style="cursor: pointer" width="25px" src="../vbtonline/resources/images/btn_actualizar.png">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                </table>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                &lt;%&ndash;<script type="text/javascript" src="https://www.google.com/recaptcha/api/challenge?k=6Lf0H-0SAAAAAA4XQAEnQIIXrEcsrqAioeVv_sh_"></script>&ndash;%&gt;&ndash;%&gt;--%>
<%--&lt;%&ndash;                            </div>&ndash;%&gt;--%>
<%--&lt;%&ndash;                        </td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    </tr>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </table>&ndash;%&gt;--%>

<%--&lt;%&ndash;                <p id="botonForgotPass" class="login button">&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <button id="btn__lgn_regresar" class=""> Cancelar </button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <button id="btn__lgn_enviar"  class=""> Aceptar  </button>&ndash;%&gt;--%>
<%--&lt;%&ndash;                </p>&ndash;%&gt;--%>
<%--&lt;%&ndash;            </div>&ndash;%&gt;--%>
<%--            <input  id="IpLocal" name="IpLocal" type="hidden" value=""/>--%>
<%--        </div>--%>
        <main>
            <section class="authentication-section"><img class="authentication-section__mark" src="../vbtonline/resources/img/mark-login.svg" alt="mark image"/>
                <div class="authentication-section__language">
                    <img id="idioma_es" src="resources/images/flag_espanol.png" class="seleccionIdioma" alt="">
                    <img id="idioma_en" src="resources/images/flag_ingles.png" class="seleccionIdioma current_seleccionIdioma" alt="">
                </div>
                <div class="authentication-section__container"><img class="authentication-section__logo" src="../vbtonline/resources/img/logo.svg" alt="VBT Bank &amp; Trusts logo"/>
                    <div class="form-login" id="login">
                        <div class="form-login__item">
                            <input class="form-login__input input" value="backofficedemo" id="username" type="text" placeholder="username"/>
                        </div>
                        <div class="form-login__item">
                            <input class="form-login__input input" id="pwdClave" type="password" name="password" value="VBTONLINE12" placeholder="password..."/>
                        </div>
                        <input  id="IpLocal" name="IpLocal" type="hidden" value="<%
                                String ipAddress = request.getRemoteAddr();
                                    out.print(ipAddress);%>"/>
                        <div id="mensaje" class="form-login__text form-login__text--error"></div>
                        <div class="captcha" id="showCaptcha" style="display: none">
                            <img class="captcha__img" id="imgCaptcha" src="jsp/captcha.jsp" alt="">
                            <div class="captcha__field">
                                <input id="recaptcha_response_field" type="text" value="" size="20" class="input input--form captcha__input">
                                <img id="refreshCaptcha" style="cursor: pointer" class="captcha__reload " src="../vbtonline/resources/images/btn_actualizar.png" alt="">
                            </div>
                        </div>
                        <div class="form-login__item">
                            <button class="form-login__button button" id="btn__lgn_aceptar">LOGIN</button>
                        </div>
                    </div>
<%--                    <a class="authentication-section__redirect button-alternative" href="#">FORGOT PASSWORD?</a>--%>
                    <div class="authentication-section__tip tip"><img class="tip__icon" src="../vbtonline/resources/img/icons/ic_security_tips.svg" alt="Security tips icon" data-modal="#security-modal"/><span class="tip__title" id="TAGSecurityTips">SECURITY TIPS</span></div>
                </div><span class="authentication-section__credits">© 2021 VBT Bank & Trust - All Rights Reserved.</span>
            </section>
            <div id="div_carga_forgot" class="imagen_carga_forgot oculto">
                <img class="recuadroCargaImg"  src="../vbtonline/resources/images/ajax-loader.gif" alt="" />
            </div>
            <div class="security-modal" id="security-modal">
                <img class="security-modal__icon" src="../vbtonline/resources/img/icons/ic_security_tips.svg" alt="Security tips icon" data-modal="#security-modal"/>
                <span id="login_TAGTipsSeguridad" class="security-modal__title">SECURITY TIPS</span>
                <ul class="security-modal__list">
                    <li id="login_TAGTip1"><span>Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to 	     entering your User ID and Password.</span></li>
                    <li id="login_TAGTip2"><span>Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to 	     entering your User ID and Password.</span></li>
                    <li id="login_TAGTip3"><span>Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to 	     entering your User ID and Password.</span></li>
                    <li id="login_TAGTip4"><span>Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to 	     entering your User ID and Password.</span></li>
                    <li id="login_TAGTip5"><span>Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to 	     entering your User ID and Password.</span></li>
                    <li id="login_TAGTip6"><span>Verify that the address that appears in the address bar is https://secure.vbtbank.com/ prior to 	     entering your User ID and Password.</span></li>
                </ul><span id="login_TAGTip7" class="security-modal__note">We suggest using the following browsers: Internet Explorer 9 or higher, Google  	  	     Chrome 29.0.1 or higher, Mozilla Firefox 22 or higher.</span>
                <div class="security-modal__links"><a class="security-modal__button" href="#https://www.websecurity.digicert.com/security-topics/what-is-ssl-tls-https" id="TAGSecurityModalSSL" target="_blank">ABOUT SSL CERTIFICATES</a><a class="security-modal__button button-alternative" href="#close" rel="modal:close" id="TAGSecurityModalUnderstood">UNDERSTOOD</a></div><img class="security-modal__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt="vbt seal icon"/>
            </div>
        </main>
    </div>
<div id="div_carga_espera_login" class="loader oculto">
    <%--                <img src="../vbtonline/resources/images/ajax-loader.gif" alt="" />--%>
    <div class="lds-roller">
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
        <div></div>
    </div><span class="loader__text">Loading</span>
</div>



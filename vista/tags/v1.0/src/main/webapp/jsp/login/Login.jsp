<%@ page import="java.util.Random" %>
<%@ page import="net.tanesha.recaptcha.ReCaptcha" %>
<%@ page import="net.tanesha.recaptcha.ReCaptchaFactory" %>
    <div id="container_demo" >
        <!-- Los TIPS de seguridad al usuario -->
        <div id="login_tips">
            <h3 id="login_TAGTipsSeguridad">Consejos de Seguridad</h3>
            <ul>
                <li id="login_TAGTip1">Antes de ingresar sus datos en esta pantalla, verifique si la direcci&oacute;n en la barra superior es: <a href="https://secure.vbtbank.com/">https://secure.vbtbank.com/</a></li>
                <li id="login_TAGTip2">Adicionalmente, verifique el Certificado de Seguridad emitido por VeriSign haciendo doble click en el icono del candado cerrado que se encuentra en la parte inferior derecha de la ventana.</li>
                <li id="login_TAGTip3">Los datos de uso de sus tarjetas o cuentas son personales y confidenciales, datos como n&uacute;mero de tarjeta, clave personal, fecha de expiraci&oacute;n no deben ser suministrados a terceros en circunstancias que no sean el acceso a sitios que usted conozca y que cuenten con los certificados de seguridad requeridos para codificar y preservar su informaci&oacute;n.</li>
                <li id="login_TAGTip4">No suministre estos datos por ninguna v&iacute;a, bien sea pantallas o v&iacute;nculos a p&aacute;ginas web, archivos adjuntos, comunicaciones electr&oacute;nicas, incluso llamadas telef&oacute;nicas de terceros, pues no es pol&iacute;tica del Banco en ning&uacute;n caso solicitar sus datos por ninguno de estos mecanismos.</li>
                <li id="login_TAGTip5">Evite acceder a VBT Bank & Trust Online desde computadores de uso p&uacute;blico.</li>
                <li id="login_TAGTip6">Use con el rat&oacute;n el teclado virtual para ingresar su clave.</li>
                <li id="login_TAGTip7"><strong>Para su seguridad y pleno funcionamiento del sistema utilice la ultima version de su browser.</strong></li>
            </ul>
        </div>

        <!-- Los formularios de acceso y de olvido de clave -->
        <div id="wrapper" class="wrapper">
            <!-- FORMULARIO DE LOGIN -->
            <div id="login" class="form login" >
                    <h1 id="login_pagesubtitle">INICIO DE SESION</h1>
                     <p id="espacio1">
                     </p>
                        <label id="login_FIELDTxtLogin" for="username" class="uname" data-icon="u" >Nombre de usuario:</label>
                        <input id="username" name="username" required="required" type="text" />

                     <p id="espacio2">
                     </p>
                        <label id="login_FIELDPwdClave" for="pwdClave" class="youpasswd" data-icon="p">Clave de acceso:</label>
                        <input id="pwdClave" name="pwdClave" value="" required="required" type="password" class="teclado" />

                    <span  id="mensaje" class="errorLogin"></span>



                          <table align="center">
                              <tr>
                                  <td>
                                      <div id="showCaptcha" style="display: none">
                                             <script type="text/javascript" src="https://www.google.com/recaptcha/api/challenge?k=6Lf0H-0SAAAAAA4XQAEnQIIXrEcsrqAioeVv_sh_"></script>
                                      </div>
                                   </td>
                             </tr>
                          </table>

                    <p id="botonLogin" class="login button espacioRelleno">
                        <button  id="btn__lgn_limpiar" class=""> Limpiar </button>
                        <button  id="btn__lgn_aceptar"  class=""> Entrar  </button>
                    </p>
            </div>
            <input  id="IpLocal" name="IpLocal" type="hidden" value="<%
             String ipAddress = request.getRemoteAddr();
             out.print(ipAddress);
           %>"/>

        </div>
    </div>
<script type="text/javascript">
</script>



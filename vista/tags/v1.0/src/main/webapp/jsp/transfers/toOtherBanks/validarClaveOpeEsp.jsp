<%--
  Created by IntelliJ IDEA.
  User: Rodolfo Rivas
  Date: 15/07/12
  Time: 11:25 AM
  To change this template use File | Settings | File Templates.
--%>

<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="../vbtonline/resources/js/transfers/validarClaveOpeEsp.js?Math.random()"></script>
<div id="cambiarClaveOpeEsp" style="display: none">

    <fieldset class="div_consultaCambiaP">
        <div id="cambiarPassword_OpeEsp" class="form">

            <h1 id="login_pagesubtitle_OpeEsp">Cambiar Clave Operaciones Especiales</h1>
            <table align="center">

                <tr>
                    <td>
                        <label id="login_FIELDPwdClave_OpeEsp" for="pwdClave_cambiarP_OpeEsp" class="youpasswd" data-icon="p">Clave Ope. Especiales:</label>
                    </td>
                    <td>
                        <input id="pwdClave_cambiarP_OpeEsp" class="obligatorio_CambiarP_OpeEsp inputFormulario_CambiarP_OpeEsp" name="pwdClave_cambiarP" title="Clave de acceso" type="password" />
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="FIELDPwdNuevaClave_OpeEsp" for="pwdClaveNueva_cambiarP_OpeEsp" class="youpasswd" data-icon="p" >Nueva Clave:</label>
                    </td>
                    <td>
                        <input id="pwdClaveNueva_cambiarP_OpeEsp" class="obligatorio_CambiarP_OpeEsp inputFormulario_CambiarP_OpeEsp" name="pwdClaveNueva_cambiarP" title="Nueva Clave" type="password"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label id="FIELDPwdNuevaClaveRe_OpeEsp" for="pwdClaveNuevaRe_cambiarP_OpeEsp" class="youpasswd" data-icon="p">Repetir nueva clave:</label>
                    </td>
                    <td>
                        <input id="pwdClaveNuevaRe_cambiarP_OpeEsp" class="obligatorio_CambiarP_OpeEsp inputFormulario_CambiarP_OpeEsp" name="pwdClaveNuevaRe_cambiarP" title="Repetir nueva Clave" type="password" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" id="btn_CambiarPas_aceptar_OpeEsp"  class="boton" value="Cambiar">
                        <input type="button"  id="btn_CambiarPas_limpiar_OpeEsp" class="boton" value="Limpiar">
                    </td>
                </tr>
            </table>
        </div>
    </fieldset>

</div>
<div id="div_claveOpeEsp" style="display: block">

    <div class="titulo_seguridad">
        <span id="tagSeguridad">Seguridad</span>
        <div class="descripcion_seguridad">
            <span id="tagMensaje1Seguridad">Introduzca su clave de Operaciones Especiales<br></span>
            <span id="comun_TAGOpeEspMsg01" class=""><br>Es Nuevo Usuario? Olvid&oacute; su Clave de acceso?<br></span>
            <span id="comun_TAGOpeEspMsg02" class=""><br>
             Haga click <A href="#" onClick="javascript:CrearClaveOpeEsp();" class="V8NBVerde" title="">aqu&iacute;</A> para crear o recuperar su clave de Operaciones Especiales, la cual le ser&aacute; enviada al correo electr&oacute;nico que indic&oacute; en su contrato.
            </span>
        </div>
    </div>

    <div class="botones_salir">

        <table SUMMARY='tabla' >
            <tbody>
            <tr>
                <td class="datos" colspan="2">
                    <span id="TagOpeEspIntroduzcaClave">Introduzca su clave:</span>
                </td>
            </tr>
            <tr>
                <td class="datos" colspan="2">
                    <input type="password" id="input_seguridad">
                </td>
            </tr>
            <tr>
                <td>
                    <span id="mensaje"> </span>
                </td>
            </tr>
            <tr>
                <td class="datos" colspan="2">
                    <input type="button" id="btn__seguridad_aceptar" value="Aceptar" class="boton">
                    <%--<input type="button" id="btn__seguridad_cancelar" value="Cancelar" class="boton">--%>
                </td>
            </tr>

            </tbody>
        </table>

    </div>

    <%--<div>--%>
        <%--<fieldset class="div_consulta">--%>
            <%--<table>--%>
                <%--<tr>--%>
                    <%--<td align="center">--%>
                        <%--<span id="TAGOpeEspMsg01" class="datos">¿Es Nuevo Usuario? ¿Olvid&oacute; su Clave de acceso?<br></span>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td align="center">--%>
                        <%--<span id="TAGOpeEspMsg02" class="datos">--%>
                            <%--Haga click <A href="#" onClick="javascript:CrearClaveOpeEsp();" class="V8NBVerde" title="">aqu&iacute;</A> para crear o recuperar su clave de Operaciones Especiales, la cual le ser&aacute; enviada al correo electr&oacute;nico que indic&oacute; en su contrato.--%>
                        <%--</span>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            <%--</table>--%>


        <%--</fieldset>--%>

    <%--</div>--%>


</div>
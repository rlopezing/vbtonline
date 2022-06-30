<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div>
        <h1 id="DB_titulo_CambiarClavePrincipal">Cambiar Clave </h1>
    </div>
    <div style="margin-left: 30%;margin-bottom: 2%;" id="div_AVISOPrincipal">Bienvenido. Por razones de seguridad, por favor ingrese una nueva clave</div>
    <div id="cambiarPasswordPrincipal">
        <br>
    <fieldset class="div_consultaCambiaP">
                <h1 id="tag_cambiarClavePrincipal">Cambiar Clave de Acceso al Sistema</h1>
                <table align="center">
                   <tr>
                        <td>
                            <label id="login_FIELDPwdClavePrincipal"  class="youpasswd">Clave de acceso</label><span>:</span>
                        </td>
                        <td>
                            <input id="pwdClave_cambiarPrincipal" class="obligatorio_CambiarPrincipal inputFormulario_CambiarP" name="pwdClave_cambiarP" title="Clave de acceso" type="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="FIELDPwdNuevaClavePrincipal"  class="youpasswd">Nueva Clave</label><span>:</span>
                        </td>
                        <td>
                            <input id="pwdClaveNueva_cambiarPrincipal" class="obligatorio_CambiarPrincipal inputFormulario_CambiarP" title="Nueva Clave" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="FIELDPwdNuevaClaveRePrincipal" class="youpasswd">Repetir nueva clave</label><span>:</span>
                        </td>
                        <td>
                            <input id="pwdClaveNuevaRe_cambiarPrincipal" class="obligatorio_CambiarPrincipal inputFormulario_CambiarP" title="Repetir nueva Clave" type="password" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" id="btn_cambiar_pass"  class="boton" value="Cambiar">
                            <input type="button"  id="btn_limpiar_cambiar_pass" class="boton" value="Limpiar">
                        </td>
                    </tr>
                </table>

    </fieldset>
    </div>
<div id="metodosCambiarClave" class="oculto" >
    <fieldset  class=" div_consultaTransfers">
        <div id="metodosValidacionCambiarClave">

        </div>
    </fieldset>
    <fieldset  class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%" align='center'>
            <tr>
                <td  align='center'>
                    <input  type="button" id="btnValidarCambioClave" value="Aceptar" class="boton">
                    <input  type="button" id="btnCancelarCambioClave" value="Cancelar" class="boton">
                </td>
            </tr>

        </table>
    </fieldset>
</div>





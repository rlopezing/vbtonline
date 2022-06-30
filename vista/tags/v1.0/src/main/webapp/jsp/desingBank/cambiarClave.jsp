<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div>
        <h1 id="DB_titulo_CambiarClave">Dise&ntilde;e su Banco / Cambiar Clave </h1>
    </div>
    <div style="margin-left: 30%;margin-bottom: 2%;" id="div_AVISO"></div>
    <div id="cambiarPassword">
    <fieldset class="div_consultaCambiaP">
                <h1 id="tag_cambiarClave">Cambiar Clave de Acceso al Sistema</h1>
                <table align="center">
                   <tr>
                        <td>
                            <label id="login_FIELDPwdClave" for="pwdClave_cambiarP" class="youpasswd">Clave de acceso</label><span>:</span>
                        </td>
                        <td>
                            <input id="pwdClave_cambiarP" class="obligatorio_CambiarP inputFormulario_CambiarP" name="pwdClave_cambiarP" title="Clave de acceso" type="password" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="FIELDPwdNuevaClave" for="pwdClaveNueva_cambiarP" class="youpasswd">Nueva Clave</label><span>:</span>
                        </td>
                        <td>
                            <input id="pwdClaveNueva_cambiarP" class="obligatorio_CambiarP inputFormulario_CambiarP" title="Nueva Clave" type="password"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label id="FIELDPwdNuevaClaveRe" for="pwdClaveNuevaRe_cambiarP" class="youpasswd">Repetir nueva clave</label><span>:</span>
                        </td>
                        <td>
                            <input id="pwdClaveNuevaRe_cambiarP" class="obligatorio_CambiarP inputFormulario_CambiarP" title="Repetir nueva Clave" type="password" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" id="btn_CambiarPas_aceptar"  class="boton" value="Cambiar">
                            <input type="button"  id="btn_CambiarPas_limpiar" class="boton" value="Limpiar">
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





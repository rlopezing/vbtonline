<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
    <div>
        <h1 id="DB_titulo_CambiarClavePrincipal">Cambiar Clave </h1>
    </div>
<fieldset style="width:95%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="5%" align="center">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL">This option allows you to change your access password to VBT Online.<br> Remember that your password must have at least 8 characters (at least 6 letters and 2 numbers).<br>You must confirm such changes through a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.</span>
                <span class="datosInfo" id="TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL2">This option allows you to change your access password to VBT Online.<br> Remember that your password must have at least 8 characters (at least 6 letters and 2 numbers).<br>You must confirm such changes through a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
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
--%>


<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_myinfo.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="DB_titulo_CambiarClavePrincipal" class="banner__title banner__title--modifier">
                Design your Bank / Change Login Password
            </h2>
<%--            <ul class="banner__nav">
                <li><a id="TagHome26" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" />
                </li>
                <li>DESIGN YOUR BANK</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" />
                </li>
                <li>CHANGE LOGIN PASSWORD</li>
            </ul>--%>
        </div>
    </div>
</div>
<main class="main main--modifier">
    <div class="options">
        <div class="options__container options__container--validation container">
            <img class="options__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png" alt="" />
            <span id="div_AVISOPrincipal" class="options__title">Methods</span>
            <div class="options__content">
                <div id="TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL" class="options__descriptions">

                </div>
                <div id="TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL2" class="options__descriptions">

                </div>
                <div id="cambiarPasswordPrincipal" class="form-password">
                    <div class="form-password__item">
                        <label id="login_FIELDPwdClavePrincipal" for="pwdClave_cambiarPrincipal"></label>
                        <input id="pwdClave_cambiarPrincipal" type="password" class="input obligatorio_CambiarPrincipal inputFormulario_CambiarP" name="pwdClave_cambiarP">
                    </div>
                    <div class="form-password__item">
                        <label id="FIELDPwdNuevaClavePrincipal" for="pwdClaveNueva_cambiarPrincipal"></label>
                        <input id="pwdClaveNueva_cambiarPrincipal" type="password" class="input obligatorio_CambiarPrincipal inputFormulario_CambiarP">
                    </div>
                    <div class="form-password__item">
                        <label id="FIELDPwdNuevaClaveRePrincipal" for="pwdClaveNuevaRe_cambiarPrincipal"></label>
                        <input id="pwdClaveNuevaRe_cambiarPrincipal" type="password" class="input obligatorio_CambiarPrincipal inputFormulario_CambiarP">
                    </div>
                    <div class="form-password__buttons">
                        <input type="button" id="btn_limpiar_cambiar_pass" class="button button--white" value="Clear">
                        <input type="button" id="btn_cambiar_pass" class="button" value="Change">
                    </div>
                </div>
            </div>
        </div>
    </div>
</main>




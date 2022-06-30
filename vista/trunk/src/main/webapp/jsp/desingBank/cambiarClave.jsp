<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--    <div>
        <h1 id="DB_titulo_CambiarClave">Dise&ntilde;e su Banco / Cambiar Clave </h1>
    </div>
<fieldset style="width:95%; margin-left:auto;margin-right:auto;float:none;" class="invisible_print div_info">
    <table width="100%" cellspacing="0" cellpadding="0" align="center" summary="tabla">
        <tbody>
        <tr>
            <td width="5%" align="center">
                <img src="resources/images/iconInfo.png">
            </td>
            <td align="left">
                <span class="datosInfo" id="TAG_INFO_CAMBIAR_CLAVE">This option allows you to change your access password to VBT Online.<br> Remember that your password must have at least 8 characters (at least 6 letters and 2 numbers).<br>You must confirm such changes through a confirmation key which you will receive via SMS on your mobile telephone or by using your code card.</span>
            </td>
        </tr>
        </tbody>
    </table>
</fieldset>
    <div style="margin-left: 30%;margin-bottom: 2%;" id="div_AVISO"></div>
    <div id="cambiarPassword">
    <fieldset class="div_consultaCambiaP">
                <h1 id="tag_cambiarClave">Cambiar Clave de Acceso al Sistema</h1>
                <table align="center">
                   <tr>
                        <td>
                            <label id="login_FIELDPwdClaveP" for="pwdClave_cambiarP" class="youpasswd">Clave de acceso</label><span>:</span>
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
                        <td colspan="2" align="center">
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
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_myinfo.png" alt="Banner Home" />
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="DB_titulo_CambiarClave" class="banner__title banner__title--modifier">
                Design your Bank / Change Login Password
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome26" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" />
                </li>
                <li>DESIGN YOUR BANK</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt="" />
                </li>
                <li>CHANGE LOGIN PASSWORD</li>
            </ul>
        </div>
    </div>
</div>--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="DB_titulo_CambiarClave" class="banner__title banner__title--modifier">
                Design your Bank / Change Login Password
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome_CHANGE_LOGIN_PASSWORD" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CHANGE_LOGIN_PASSWORD_DESIGN_YOUR_BANK">DESIGN YOUR BANK</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="CHANGE_LOGIN_PASSWORD">CHANGE LOGIN PASSWORD</li>
            </ul>
           
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="options">
        <div class="containerParentDesignV2">               
                <div class="options__content containerDesignV2">
                    <div class="containerDesignV2-left">
                        <img class="options__icon" src="../vbtonline/resources/img/icons/ic_login_security_tips.png" alt="" style="display: inline-block; width: auto;"/>
                        <br />
                        <span id="tag_cambiarClave" class="options__title" style="display: inline-block;">Methods</span>
                        <br /> <br /> <br /> <br />
                        <div id="TAG_INFO_CAMBIAR_CLAVE" class="options__descriptions">

                        </div>
                    </div>
                    <div id="cambiarPassword" class="form-password containerDesignV2-right">
                        <div class="form-password__item">
                            <label id="login_FIELDPwdClaveP" for="pwdClave_cambiarP"></label>
                            <input id="pwdClave_cambiarP" type="password" class="input obligatorio_CambiarP inputFormulario_CambiarP" name="pwdClave_cambiarP">
                        </div>
                        <div class="form-password__item">
                            <label id="FIELDPwdNuevaClave" for="pwdClaveNueva_cambiarP"></label>
                            <input id="pwdClaveNueva_cambiarP" type="password" class="input obligatorio_CambiarP inputFormulario_CambiarP">
                        </div>
                        <div class="form-password__item">
                            <label id="FIELDPwdNuevaClaveRe" for="pwdClaveNuevaRe_cambiarP"></label>
                            <input id="pwdClaveNuevaRe_cambiarP" type="password" class="input obligatorio_CambiarP inputFormulario_CambiarP">
                        </div>
                        <div class="form-password__buttons">
                            <input type="button" id="btn_CambiarPas_limpiar" class="button button--white" value="Clear">
                            <input type="button" id="btn_CambiarPas_aceptar" class="button" value="Change">
                        </div>
                    </div>
                </div>
        </div>
    </div>
<%--    <jsp:include page="../transfer-instructions.jsp" />--%>
</main>
<%--<jsp:include page="../footer.jsp" />--%>




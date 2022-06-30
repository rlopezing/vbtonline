<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
<div>
    <h1 id="titulo_misdatos">My Information </h1>
</div>
<div id="div_myInformation">
    <fieldset class="div_consultaTransfers oculto 0000000023_2" id="actualizacion">
        <legend><span id="misdatos_TAGActualizarData">Client Update</span></legend>
        <span class="datos">  </span>
    </fieldset>
    <fieldset class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%" align="center">
            <tbody>
            <tr>
                <td>
                    <label class="datos" id="misdatos_TAGNombre">Nombre</label><span class="datos">:</span>
                </td>
                <td>
&lt;%&ndash;                    <span class="datos" id="nameMyInformation_info"></span>&ndash;%&gt;
                    <span class="datos" id=""></span>
                </td>
                <td>
                    <label class="datos" id="misdatos_TAGCIRIF">C.I./R.I.F</label><span class="datos">:</span>
                </td>
                <td>
                    <span class="datos" id="cirifMyInformation_info"></span>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="datos" id="misdatos_TAGDireccionEnvio">Direcci&oacute;n de Correo</label><span class="datos">:</span>
                </td>
                <td>
                    <span class="datos" id="mailMyInformation_info"></span>
                </td>
                <td><label class="datos oculto" id="TAGmisdatos_TAGtelefono">Cell phone</label><span class="datos">:</span></td>
                <td><label class="datos oculto" id="misdatos_TAGtelefono_info">Cell phone</label><span class="datos"></span></td>
            </tr>

            </tbody>
        </table>
    </fieldset>
    <input type="hidden" id="todoOculto" value="mostrar">
    <br><span class="spanEstilo"><a style="cursor: pointer" id='mostrar_todo'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9' /><b><span id='misdatos_TAGMostrarTodas'>Show all my information</span> </b></a></span>

    <div id="bloque_informacion_telefonos" >
    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_telefono'><img src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /> <b ><span id="misdatos_TAGTelefonos"> Contactos </span></b></a></span>
    <div id="fieldset_telefono" class="oculto div_Myinformacion_detalle">
        <fieldset class="div_consulta">
            <div id="div_tabla_consulta_MyInfo_Telefono" class="div_tabla_consulta">

            </div>
        </fieldset>
        <input type="hidden" id="telefonoAccion" value="mostrar">
    </div>
    </div>
    <div id="bloque_informacion_direccion">
        <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_direccion'> <img src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /> <b ><span id="misdatos_TAGDirecciones"> Contactos </span></b></a></span>
        <div id="fieldset_direccion" class="oculto div_Myinformacion_detalle">
            <fieldset class="div_consulta">
                <div id="div_tabla_consulta_MyInfo_Direccion" class="div_tabla_consulta">
                </div>
            </fieldset>
            <input type="hidden" id="direccionAccion" value="mostrar">
        </div>
    </div>
   &lt;%&ndash; <div id="bloque_informacion_documentos">
        <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_documentos'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /> <b><span id="misdatos_TAGDocumentos"> Contactos </span></b></a></span>
        <div id="fieldset_documentos" class="oculto div_Myinformacion_detalle">
            <fieldset class="div_consulta">
                <div id="div_tabla_consulta_MyInfo_Documentos" class="div_tabla_consulta">
                </div>
            </fieldset>
            <input type="hidden" id="documentosAccion" value="mostrar">
        </div>
    </div>    &ndash;%&gt;
    <div id="bloque_informacion_representantes">
        <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_represetantes'><img src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /><b><span id="misdatos_TAGRepresentantes"> Contactos </span></b></a></span>
        <div id="fieldset_represetantes" class="oculto div_Myinformacion_detalle">
            <fieldset class="div_consulta">
                <div id="div_tabla_consulta_MyInfo_Representantes" class="div_tabla_consulta">
                </div>
            </fieldset>
            <input type="hidden" id="representantesAccion" value="mostrar">
        </div>
    </div>
    <div id="bloque_informacion_contactos">
        <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_contactos'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /><b><span id="misdatos_TAGContactos"> Contactos </span></b></a></span>
        <div id="fieldset_contactos" class="oculto div_Myinformacion_detalle">
            <fieldset class="div_consulta" >
                <div id="div_tabla_consulta_MyInfo_Contactos" class="div_tabla_consulta">
                </div>
            </fieldset>
            <input type="hidden" id="contactosAccion" value="mostrar">
        </div>
    </div>
    <div id="bloque_informacion_carteras">
        <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_carteras'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /> <b ><span id="misdatos_TAGCarteras"> Contactos </span></b></a></span>
        <div id="fieldset_carteras" class="oculto div_Myinformacion_detalle">
            <fieldset class="div_consulta">
                <div id="div_tabla_consulta_MyInfo_Carteras" class="div_tabla_consulta">
                </div>
            </fieldset>
            <input type="hidden" id="carterasAccion" value="mostrar">
        </div>
    </div>
</div>--%>
<!-- <div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_myinfo.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="titulo_misdatos" class="banner__title banner__title--modifier">My Information</h2>
            <ul class="banner__nav">
                <li><a id="TagHome30" href="Home">HOME</a></li>
                <li><img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/></li>
                <li id="USER_PROFILE">USER PROFILE</li>
            </ul>
        </div>
    </div>
</div> -->
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="titulo_misdatos" class="banner__title banner__title--modifier">My Information</h2>

            <ul class="banner__nav">
                <li><a id="TagHome30" href="Home">HOME</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li id="USER_PROFILE">USER PROFILE</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div class="options">
        <div class="options__container container">
            <img class="options__icon"
                 src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png"
                 alt=""/><span id="nameMyInformation_info" class="options__title"></span>
            <span id="mailMyInformation_info" class="options__description"></span>
            <div class="options__grid">
                <a class="card-option">
                <img class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/>
                    <span id="misdatos_TAGDireccionEnvio" class="card-option__title">MAILING
              ADDRESS</span><span id="mailMyInformation_info2" class="card-option__description"></span></a>
                <a class="card-option"><img
                    class="card-option__icon" src="../vbtonline/resources/img/icons/ic_info_download.png" alt=""/><span id="misdatos_TAGCIRIF"
                    class="card-option__title">C.I./R.I.F:</span><span id="cirifMyInformation_info" class="card-option__description"></span></a><a
                    class="card-option"><img class="card-option__icon"
                                                      src="../vbtonline/resources/img/icons/ic_info_download.png"
                                                      alt=""/><span id="TAGmisdatos_TAGtelefono" class="card-option__title">CELL PHONE:</span><span id="misdatos_TAGtelefono_info"
                    class="card-option__description"></span></a></div>
            <img class="options__logo" src="../vbtonline/resources/img/img__security_tips_vbt_seal_tan.png" alt=""/>
        </div>
    </div>
    <div class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
<%--                    <span class="section__title">All my info</span>
                    <div class="section__info">
                        <span>Hide all</span>
                        <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_table_header_hide.png" alt=""/>
                    </div>--%>
                </div>
            </div>
            <div class="section__content">
                <div class="table">
                    <span id="misdatos_TAGTelefonos" class="table__title">ACCOUNTS</span>
                    <table class="table__content" id="table-info_telephone">
                    </table>
                </div>
                <div class="table">
                    <span id="misdatos_TAGDirecciones"  class="table__title">ADDRESSES</span>
                    <table class="table__content" id="table-info_addresses">
                    </table>
                </div>
            </div>
        </div>
    </div>
<%--    <jsp:include page="../transfer-instructions.jsp"/>--%>
</main>
<%--<jsp:include page="../footer.jsp" />--%>
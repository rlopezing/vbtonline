<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--<div id="div_tabla_opciones" class="div_tabla_consulta" style="display: none">
    <div>
        <h1 id="Titulo_opciones"> Opciones (Roles Firmas Conjuntas) </h1>
    </div>
    <fieldset class="div_consulta">
        <input type="button" id="btnRolesVoler" value="Regresar" class="botonDerecha"> <br>
        &lt;%&ndash;<legend> Consultar Grupos o Roles </legend>&ndash;%&gt;
        <label>Haga click sobre la opcion para asignarla </label>
        <div id="div_tabla_consultaOpciones" class="div_tabla_consultaOpciones">
        </div>

    </fieldset>
</div>
<div id="div_tabla_consultaRoles" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_roles"> Roles de Firmas Conjuntas </h1>
    </div>
    <fieldset class="div_consulta">
        <input type="button" id="btnEditRoles" value="Asignar Acciones" class="botonGrandeDerechaFC" > <br>
        &lt;%&ndash;<legend> Consultar Grupos o Roles </legend>&ndash;%&gt;
        <label>Para Editar los permisos de acceso de las opciones de un rol, haga click sobre el nombre </label>
        <div id="div_tabla_consultarRoles" class="div_tabla_consultarRoles">
        </div>

    </fieldset>
</div>
<div  id="div_tabla_consultaROS" class="div_tabla_consultaROS" style="display: none">
    <div>
        <h1 id="Titulo_rolesOpcionesSistema"> Roles / Opciones Sistema </h1>
    </div>
    <fieldset class="div_consulta">
        <input type="button" id="btnBackRoles" value="Volver" class="boton" >

    </fieldset>
    <fieldset class="div_consulta">
        <legend id="Legend_rolOpcionesSistema">  </legend>
        <label>Haga click sobre la opcion para asignarla </label>
        <div id="div_tabla_rolOpcionesSistema" class="div_tabla_rolOpcionesSistema">
        </div>

    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_roles" class="banner__title banner__title--modifier">
                Roles de Firmas Conjuntas
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome34" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ADMINISTRACIÓN DE GRUPOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ROLES DE FIRMAS CONJUNTAS</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_roles" class="banner__title banner__title--modifier">
                Roles de Firmas Conjuntas
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome34" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ADMINISTRACIÓN DE GRUPOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ROLES DE FIRMAS CONJUNTAS</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaRoles" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <div></div>
                    <input type="button" id="btnEditRoles" value="Asignar Acciones" class="section__button button">
                </div>
                <div class="section__row section__row--spacebetween">
                    <span>Para Editar los permisos de acceso de las opciones de un rol, haga click sobre el nombre</span>
                </div>
            </div>
            <div class="section__content">
                <div id="div_tabla_consultarRoles" class="div_tabla_consultarRoles"></div>
            </div>
        </div>
    </div>
    <div id="div_tabla_opciones" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_opciones" class="section__title">Opciones (Roles Firmas Conjuntas)</span>
                    <input type="button" id="btnRolesVoler" value="Regresar" class="section__button button button--white" />
                </div>
                <div class="section__row section__row--spacebetween">
                    <span>Haga click sobre la opcion para asignarla</span>
                </div>
            </div>
            <div class="section__content">
                <div id="div_tabla_consultaOpciones" class="div_tabla_consultaOpciones"></div>
            </div>
        </div>
    </div>
    <div id="div_tabla_consultaROS" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_rolesOpcionesSistema" class="section__title">Roles / Opciones Sistema</span>
                    <div class="section__buttons">
                        <input type="button" id="btnBackRoles" value="Volver" class="section__button button button--white" />
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <span>Haga click sobre la opcion para asignarla</span>
                </div>
            </div>
            <div class="section__content">
                <span id="Legend_rolOpcionesSistema" class="form-filter__title"></span>
                <div id="div_tabla_rolOpcionesSistema" class="div_tabla_rolOpcionesSistema"></div>
            </div>
        </div>
    </div>
</main>






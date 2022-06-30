<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--

<div id="div_tabla_consultaG" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_grupos"> Grupos o Roles </h1>
    </div>
<fieldset class="div_consulta">
    &lt;%&ndash;<legend> Consultar Grupos o Roles </legend>&ndash;%&gt;
    <label>Para Editar los permisos de acceso de las opciones de un grupo, haga click sobre el nombre </label>
    <div id="div_tabla_consultarGrupos" class="div_tabla_consultarGrupos">
    </div>

</fieldset>
</div>
<div  id="div_tabla_consultaGOS" class="div_tabla_consultaGOS" style="display: none">
    <div>
        <h1 id="Titulo_gruposOpcionesSistema"> Grupos o Roles / Opciones Sistema </h1>
    </div>
    <fieldset class="div_consulta">
        <input type="button" id="CGOS_back_CG" value="Volver" class="boton" >

    </fieldset>
    <fieldset class="div_consulta">
        <legend id="Legend_grupoOpcionesSistema">  </legend>
        <label>Para Editar haga click sobre la opcion </label>
        <div id="div_tabla_grupoOpcionesSistema" class="div_tabla_grupoOpcionesSistema">
        </div>

    </fieldset>
</div>

<div  id="div_tabla_consultaGOP" class="div_tabla_consultaGOP" style="display: none">
    <div>
        <h1 id="Titulo_gruposOpcionesPermisos"> Grupos o Roles / Opciones Permisos </h1>
    </div>
    <fieldset class="div_consulta">
        <input type="button" id="CGOP_back_CG" value="Volver" class="boton" >
        <input type="button" id="CGOP_save_CG" value="Guardar" class="0000000028_2 boton" >

    </fieldset>
    <fieldset class="div_consulta">
        <legend id="Legend_grupoOpcionesSistemaPermisos"></legend>
        <label>Para Editar haga click sobre la opcion </label>
        <div id="div_tabla_grupoOpcionesPermisos" class="div_tabla_grupoOpcionesPermisos">
        </div>

    </fieldset>
</div>--%>
<%--
<div class="banner">
    <img class="banner__img banner__img--modifier" src="../vbtonline/resources/img/bg_accounts.png" alt="Banner Home"/>
    <div class="banner__container banner__container--modifier container">
        <div class="banner__content">
            <h2 id="Titulo_grupos" class="banner__title banner__title--modifier">
                Grupos o Roles
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome33" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ADMINISTRACIÓN DE GRUPOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>GRUPOS O ROLES</li>
            </ul>
        </div>
    </div>
</div>
--%>
<div class="bannerV2">
    <div class="banner__container banner__container--modifier">
        <div class="banner__content">
            <h2 id="Titulo_grupos" class="banner__title banner__title--modifier">
                Grupos o Roles
            </h2>
            <ul class="banner__nav">
                <li><a id="TagHome33" href="Home">INICIO</a></li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>ADMINISTRACIÓN DE GRUPOS</li>
                <li>
                    <img src="../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_up.png" alt=""/>
                </li>
                <li>GRUPOS O ROLES</li>
            </ul>
        </div>
    </div>
    <div class="bannerImgV2">
        <img src="../vbtonline/resources/img/bannerV2.png" alt=""/>
    </div>
</div>
<main class="main main--modifier">
    <div id="div_tabla_consultaG" class="section">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span class="section__title">Consultar Grupos o Roles</span>
                </div>
                <div class="section__row section__row--spacebetween">
                    <span>Para Editar los permisos de acceso de las opciones de un grupo, hagaclick sobre el nombre</span>
                </div>
            </div>
            <div class="section__content">
                <div id="div_tabla_consultarGrupos" class="div_tabla_consultarGrupos"></div>
            </div>
        </div>
    </div>
    <div id="div_tabla_consultaGOS" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_gruposOpcionesSistema" class="section__title">Grupos o Roles / Opciones Sistema</span>
                    <input type="button" id="CGOS_back_CG" value="Volver" class="section__button button button--white" />
                </div>
                <div class="section__row section__row--spacebetween">
                    <span>Para Editar haga click sobre la opcion</span>
                </div>
            </div>
            <div class="section__content">
                <span id="Legend_grupoOpcionesSistema" class="form-filter__title"></span>
                <div id="div_tabla_grupoOpcionesSistema" class="div_tabla_grupoOpcionesSistema"></div>
            </div>
        </div>
    </div>
    <div id="div_tabla_consultaGOP" class="section" style="display: none">
        <div class="section__container container">
            <div class="section__top">
                <img class="section__icon" src="../vbtonline/resources/img/icons/ic_myinfo_summary_header.png" alt=""/>
                <div class="section__header">
                    <span id="Titulo_gruposOpcionesPermisos" class="section__title">Grupos o Roles / Opciones Permisos</span>
                    <div class="section__buttons">
                        <input type="button" id="CGOP_back_CG" value="Volver" class="section__button button button--white" />
                        <input
                                type="button"
                                id="CGOP_save_CG"
                                value="Guardar"
                                class="section__button button"
                        />
                    </div>
                </div>
                <div class="section__row section__row--spacebetween">
                    <span>Para Editar haga click sobre la opcion</span>
                </div>
            </div>
            <div class="section__content">
                <span id="Legend_grupoOpcionesSistemaPermisos" class="form-filter__title"></span>
                <div id="div_tabla_grupoOpcionesPermisos" class="div_tabla_grupoOpcionesPermisos"></div>
            </div>
        </div>
    </div>
</main>
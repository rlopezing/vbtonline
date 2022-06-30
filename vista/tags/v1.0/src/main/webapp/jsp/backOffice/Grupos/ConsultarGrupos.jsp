<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="div_tabla_consultaG" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_grupos"> Grupos o Roles </h1>
    </div>
<fieldset class="div_consulta">
    <%--<legend> Consultar Grupos o Roles </legend>--%>
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
</div>
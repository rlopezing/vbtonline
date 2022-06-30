<%--
  Created by IntelliJ IDEA.
  User: Rafael Godoy
  Date: 31/07/2012
  Time: 03:36 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="div_tabla_consultaContratos" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_Contratos"> Contratos</h1>
    </div>
<fieldset class="div_consulta 0000000004_1">

    <input type="button" id="agregarContrato" value="Agregar Contrato" class="botonGrandeDerecha">
</fieldset>


<fieldset class="div_consulta">

    <legend>Filtro de consulta</legend>
    <div id="filtroContratos">
    <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" border="0">
        <tbody><tr>
            <td class="datos2" id="tagConFiltro">Contrato N&deg;:</td>
            <td class="datos">
                <input type="TEXT" id="contratoFiltroCC" title="Contrato N°" class="inputFormulario"  maxlength="16" style="width: 200px" >
            </td>
            <td class="datos2" id="tagUsuFiltro">Usuario Asociado:</td>
            <td class="datos">
                <input type="TEXT" id="usuarioFiltroCC" title="Usuario Asociado" class="inputFormulario" maxlength="16" style="width: 200px" >

            </td>

            <td class="datos2" id="OJO_tagEstatusFiltro">
                Estatus:
            </td>
            <td class="datos">
                <select  id="estatusFiltroCC" title="Estatus" class="selectFormulario" style="width: 200px">

                </select>
            </td>
        </tr>
        <tr>
            <td><label  class="datos2">Cliente:</label></td>
            <td colspan="2">
                <input  type="text" title="Cliente" id="clienteCC" class="inputFormulario" style="width:200px;" tabindex="2" maxlength="50">
            </td>
            <td colspan="2"><label class="datos2">C.I./R.I.F:</label></td>
            <td>
                <input type="text" title="CIRIF" id="CIRIFCC" class="inputFormulario" tabindex="2" maxlength="15" style="width: 200px">
            </td>
        </tr>
        <tr>
            <td class="datos2">Creados Desde:</td>
            <td class="datos">
                <%--esto debe ser un calendario que permita seleccionar la fecha--%>
                <input type="text" title="Fecha Desde" id="fechaDesdeFiltroCC" class="inputFormulario" tabindex="2" style="width: 127px">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos2">Hasta:</td>
            <td class="datos">
                <%--esto debe ser un calendario que permita seleccionar la fecha--%>
                <input type="text" title="Fecha Hasta" id="fechaHastaFiltroCC"  class="inputFormulario" tabindex="2" style="width: 127px">
                <label class="datos">dd/mm/yyyy</label>
            </td>
            <td class="datos2" id="tagCreadoFiltro">
                    Creado Por:
            </td>
            <td class="datos">
                <select  id="creadoFiltro" title="Creado por" class="selectFormulario" style="width: 200px" >

                </select>
            </td>

        </tr>
        <tr>
            <td class="datos2" id="tagCartera">Cartera N&deg;:</td>
            <td class="datos">
                <input type="TEXT" id="carteraFiltro" title="Cartera" class="inputFormulario"  maxlength="16" style="width: 200px">
            </td>
            <td colspan="4" class="botones_formulario">
                <input type="button" id="searchContrato" value="Buscar" class="botonDerecha">
                <input type="reset" id="resetBusquedaContrato" value="Limpiar" class="boton">
            </td>
        </tr>

        </tbody></table>
     </div>
</fieldset>


<fieldset class="div_consulta">
    <legend> Consulta Contratos </legend>
    <div id="div_tabla_consulta_contratosBO" class="div_tabla_consulta_contratosBO">

    </div>
    <div id="paginacion_tabla_consulta_contratosBO" class="div_tabla_consulta">
    </div>
</fieldset>
</div>
<%--Agregar Contratos--%>
<div id="div_tabla_AgregarContratos" style="display: none" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_agregarContratos"> Contratos / Agregar Contrato</h1>
    </div>
    <fieldset class="div_consulta">

        <table SUMMARY='tabla'cellspacing="2" cellpadding="2" width="100%">
            <tr>
                <td colspan="4" class="botones_formulario">
                    <input type="button" id="AC_back" value="Volver" class="boton">
                    <input type="button" id="AC_reset" value="Limpiar" class="boton">
                    <input type="button" id="AC_salvar" value="Guardar" class="boton">
                </td>
            </tr>
            <tr >
                <td class="datos2" id="tagAC_observaciones">Observaciones:</td>
                <td class="datos" colspan="2">
                    <textarea rows="3" cols="95" name="txtDescripcionEditar" id="AC_observaciones" title="Observaciones" class="inputFormulario" ></textarea>
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class="div_consulta">
        <legend align="center" class="datos"><b> Usuarios </b></legend>

        <table align="right">
            <tr>
                <td> <input type="button" id="AC_search_usuario" value="Buscar Clientes" class="botonGrandeDerecha"></td>
                <td> <input type="button" id="AC_usuario_add" value="Agregar Firmas Conjuntas" class="botonGrande"></td>
            </tr>
        </table>

        <%--<div id="div_buscarUsuarioContrato" class="div_buscarUsuarioContrato" style="display: none">--%>
        <div id="div_buscarUsuarioContrato">
            <h3 id="see_id_ac_bu" >B&uacute;squeda de Usuarios</h3>
            <div id="buscaform_ac_bu">
                <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tbody><tr>
                        <td class="datos2" id="tagCIRIFBUC">C.I./R.I.F:</td>
                        <td class="datos">
                            <input type="TEXT" id="CIRIFBUC" title="C.I./R.I.F:"  class="inputFormulario">
                        </td>
                        <td class="datos2" id="tagClienteBUC">Cliente:</td>
                        <td class="datos">
                            <input type="TEXT" id="ClienteBUC" title="Cliente" class="inputFormulario" style="width: 250px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Asesor Financiero:</label></td>
                        <td class="datos">
                            <input  type="text" title="Asesor Financiero" id="financialAdvisorBUC" class="inputFormulario"  style="width: 200px">
                        </td>
                        <td><label class="datos2">Ejecutivo de Cuenta:</label></td>
                        <td class="datos">
                            <input type="text" title="Ejecutivo de Cuenta" id="accountExecutiveBUC" class="inputFormulario" style="width: 150px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Cartera N&deg;:</label></td>
                        <td class="datos" colspan="3">
                            <input  type="text" title="Cartera" id="carteraAUC" class="inputFormulario">
                            <input  type="checkbox" style="width: 20px" id="carteraExacto" class="datos checkFormulario" value="1"><label class="datos"> Buscar por n&uacute;mero de cartera exacto?</label></input>
                        </td>
                     </tr>
                     <tr align='center'>
                        <td>&nbsp;</td>
                         <td colspan="2" >
                            <%--<input type="button" id="BUC_close" value="Close" class="boton" />--%>
                            <div id="div_carga_usuario_ABC" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='30px' alt="" /></div>
                        </td>
                         <td >
                            <div id="actionsBUA" colspan="2">
                                 <input type="button" id="BUC_search" value="Buscar" class="boton" />
                                 <input type="button" id="BUC_reset" value="Limpiar" class="boton" />
                            </div>
                        </td>
                         <td>&nbsp;</td>
                    </tr>
                </table>
             </div>
             <div id="div_tabla_resultadoBusquedaUsuarioContrato" class="div_tabla_resultadoBusquedaUsuarioContrato" > </div>
             <div id="paginacion_tabla_resultadoBusquedaUsuarioContrato" class="div_tabla_resultadoBusquedaUsuarioContrato" ></div>
             <a id="close_x_ac_bu" class="close sprited_ac_bu" href="#">close</a>

                <div id="div_mensajes_info_desc_ac" class="info_descp oculto">
                    <div id="mensajes_info_desc_ac_bu">
                        error
                    </div>
                    <div id="cerrar_div_mensajes_info_desc_ac_bu">[X]</div>
                </div>

        </div>
        <div id="div_tabla_AC_consultarUsuarioContrato" class="div_tabla_AC_consultarUsuarioContrato">

        </div>
        <div id="div_AddBuscarUsuarioContrato">
            <h3 class="see_id_ac_bu">Agregar Usuarios</h3>
            <table SUMMARY='tabla'cellpadding="1" cellspacing="1"  border="0" width="95%" align="center">
                <tr>
                    <td>
                        <label class="datos">Nombre:</label>
                    </td>
                    <td>
                        <input id="AU_contratoNombre" type="text" class="obligatorio_AUContrato  inputFormulario inputFormularioAU" title="Nombre"
                               style="width:195px" maxlength="55" size="35"/> <label class="datos"> * </label>
                    </td>

                    <td>
                        <label class="datos">C.I./R.I.F:</label>
                    </td>
                    <td>
                        <select id="AU_contratoCirif" class="selectFormulario obligatorio_AUContrato" title="Tipo Persona">

                        </select>
                        <input id="AU_contratoCiRifvalor" type="text" class="obligatorio_AUContrato inputFormulario inputFormularioAU" title="C.I./R.I.F"
                               maxlength="20" size="12"
                               onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/><label
                            class="datos"> * </label>
                    </td>

                </tr>

                <tr>
                    <td>
                        <label  class="datos">Tel&eacute;fono:</label>
                    </td>
                    <td>
                        <input id="AU_contratoTelefono" type="text" class="obligatorio_AUContrato  inputFormulario inputFormularioAU"
                               title="Tel&eacute;fono" style="width:195px" maxlength="20" size="20"   onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/> <label class="datos">
                        * </label>
                    </td>
                    <td>
                        <label class="datos">Tel&eacute;fono C&eacute;lular:</label>
                    </td>
                    <td>
                        <input id="AU_contratoCelular" type="text" class="obligatorio_AUContrato inputFormulario inputFormularioAU" title="Tel&eacute;fono Celular"
                               style="width:195px" maxlength="20" size="20"   onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>  <label class="datos">
                        * </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label
                               class="datos">Direcci&oacute;n:</label>
                    </td>
                    <td >
                        <input id="AU_contratoDireccion" type="text" class="obligatorio_AUContrato  inputFormulario inputFormularioAU" title="direccion" style="width:300px" maxlength="120"
                               size="86"/>  <label class="datos"> * </label>
                    </td>
                    <td>
                        <label class="datos">Email:</label>
                    </td>
                    <td>
                        <input id="AU_contratoEmail" type="text" class="obligatorio_AUContrato  inputFormulario inputFormularioAU" title="Email"
                               style="width:195px" maxlength="55" size="20"/> <label class="datos"> * </label>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label   class="datos">Grupo:</label>
                    </td>
                    <td>
                        <select id="AU_contratoGrupo" title="Grupo" class="obligatorio_AUContrato" style="width:195px">

                        </select>
                    </td>
                    <td></td>
                    <td></td>

                </tr>
                <tr>
                    <td colspan="4" align="right">

                            <input type="button" class="boton" value="Agregar" id="btn_add_usr">
                            <input type="button" class="boton" value="Limpiar" id="btn_add_clear">

                    </td>
                </tr>
            </table>
            <br>
            <a id="close_x_ac_btn" class="close sprited_ac_bu" href="#">close</a>
        </div>
        <div id="div_AddBuscarUsuarioContratoEditar">
            <h3 class="see_id_ac_bu">Agregar Usuarios</h3>
            <table SUMMARY='tabla'cellpadding="1" cellspacing="1"  border="0" width="95%" align="center">
                <tr>
                    <td>
                        <label class="datos">Nombre:</label>
                    </td>
                    <td>
                        <input id="EU_contratoNombre" type="text" class="obligatorio_EUContrato  inputFormulario inputFormularioEU" title="Nombre"
                               style="width:195px" maxlength="55" size="35"/> <label class="datos"> * </label>
                    </td>

                    <td>
                        <label class="datos">C.I./R.I.F:</label>
                    </td>
                    <td>
                        <select id="EU_contratoCirif" class="selectFormulario obligatorio_EUContrato" title="Tipo Persona">

                        </select>
                        <input id="EU_contratoCiRifvalor" type="text" class="obligatorio_EUContrato inputFormulario inputFormularioEU" title="C.I./R.I.F"
                               maxlength="20" size="12"
                               onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/><label
                            class="datos"> * </label>
                    </td>

                </tr>

                <tr>
                    <td>
                        <label  class="datos">Tel&eacute;fono:</label>
                    </td>
                    <td>
                        <input id="EU_contratoTelefono" type="text" class="obligatorio_EUContrato  inputFormulario inputFormularioEU"
                               title="Tel&eacute;fono" style="width:195px" maxlength="20" size="20"   onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/> <label class="datos">
                        * </label>
                    </td>
                    <td>
                        <label class="datos">Tel&eacute;fono C&eacute;lular:</label>
                    </td>
                    <td>
                        <input id="EU_contratoCelular" type="text" class="obligatorio_EUContrato inputFormulario inputFormularioEU" title="Tel&eacute;fono Celular"
                               style="width:195px" maxlength="20" size="20"   onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>  <label class="datos">
                        * </label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label
                                class="datos">Direcci&oacute;n:</label>
                    </td>
                    <td >
                        <input id="EU_contratoDireccion" type="text" class="obligatorio_EUContrato  inputFormulario inputFormularioEU" title="direccion" style="width:300px" maxlength="120"
                               size="86"/>  <label class="datos"> * </label>
                    </td>
                    <td>
                        <label class="datos">Email:</label>
                    </td>
                    <td>
                        <input id="EU_contratoEmail" type="text" class="obligatorio_EUContrato  inputFormulario inputFormularioEU" title="Email"
                               style="width:195px" maxlength="55" size="20"/> <label class="datos"> * </label>
                    </td>

                </tr>
                <tr>
                    <td>
                        <label   class="datos">Grupo:</label>
                    </td>
                    <td>
                        <select id="EU_contratoGrupo" title="Grupo" class="obligatorio_EUContrato" style="width:195px">

                        </select>
                    </td>
                    <td></td>
                    <td></td>

                </tr>
                <tr>
                    <td colspan="4" align="right">
                        <input type="button" class="boton" value="Agregar" id="btn_add_usr_edit">
                        <input type="button" class="boton" value="Limpiar" id="btn_add_clear_edit">

                    </td>
                </tr>
            </table>
            <br>
            <a id="close_x_ec_btn_edit" class="close sprited_ac_bu" href="#">close</a>
        </div>

    </fieldset>
    <fieldset class="div_consulta">
        <legend align="center" class="datos"><b> Carteras </b></legend>
        <input type="button" id="AC_search_cartera" value="Buscar" class="botonDerecha">

        <div id="div_buscarCarteraContrato">
            <h3 id="see_id_ac_bc" >B&Uacute;SQUEDA DE CARTERA</h3>
            <%--<fieldset class="div_consulta">--%>
            <div id="buscaform_ac">
                <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tbody><tr>
                        <td class="datos2" id="tagCIRIFBCC">C.I./R.I.F:</td>
                        <td class="datos">
                            <input type="TEXT" id="CIRIFBCC" class="inputFormulario" title="C.I./R.I.F:" >
                        </td>
                        <td class="datos2" id="tagClienteBCC">Cliente:</td>
                        <td class="datos">
                            <input type="TEXT" id="ClienteBCC" title="Cliente" class="inputFormulario" style="width: 250px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Asesor Financiero:</label></td>
                        <td class="datos">
                            <input  type="text" title="Asesor Financiero" id="financialAdvisorBCC" class="inputFormulario" style="width: 200px">
                        </td>
                        <td><label class="datos2">Ejecutivo de Cuenta:</label></td>
                        <td class="datos">
                            <input type="text" title="Ejecutivo de Cuenta" id="accountExecutiveBCC" class="inputFormulario" style="width: 150px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Cartera N&deg;:</label></td>
                        <td class="datos" colspan="3">
                            <input  type="text" title="Cartera" id="carteraECC" class="inputFormulario">
                            <input  type="checkbox" style="width: 20px" id="carteraExactoC" class="datos checkFormulario" value="1"><label class="datos"> Buscar por n&uacute;mero de cartera exacto?</label></input>
                        </td>
                    </tr>
                    <tr align='center'>
                        <td>&nbsp;</td>
                        <td  colspan="2">
                            <div id="div_carga_carteras_ABC" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='30px' alt="" /></div>

                        </td>
                        <td colspan="2">
                             <input type="button" id="BCC_search" value="Buscar" class="boton" />
                             <input type="button" id="BCC_reset" value="Limpiar" class="boton" />
                        </td>
                        <td>&nbsp;</td>
                    </tr>

                </table>
            </div>
            <div id="div_tabla_resultadoBusquedaCarteraContrato" class="div_tabla_resultadoBusquedaCarteraContrato" >
            </div>
            <div id="paginacion_tabla_resultadoBusquedaCarteraContrato">

            </div>
            <a id="close_x_ac_bc" class="close sprited_ac_bc" href="#">close</a>

            <div id="div_mensajes_info_desc" class="info_descp oculto">
                <div id="mensajes_info_desc_ac">
                    error
                </div>
                <div id="cerrar_div_mensajes_info_desc_ac">[X]</div>
            </div>
            <%--</fieldset>--%>
        </div>
        <div id="div_tabla_AC_consultarCarterasContrato" class="div_tabla_EC_consultarCarterasContrato">

        </div>
    </fieldset>
</div>
<%--Editar Contratos--%>
<div id="div_tabla_EditarContratos" style="display: none" class="div_tabla_consulta">
    <div>
        <h1 id="Titulo_editarContratos"> Contratos / Editar Contrato</h1>
    </div>
    <fieldset class="div_consulta">

        <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%">
            <tr>
                <td class="datos2" id="tagAU_contrato">Contrato N&deg;:</td>
                <td class="datos">
                    <b><span id="EC_numContrato"></span></b>
                </td>

                <td class="datos2" id="tagEstatusContratoFiltro">
                    Estatus:
                </td>
                <td class="datos">
                    <select  id="EC_estatusContratoFiltro" title="Estatus" style="width:105px;" class="selectFormulario" onchange="mostrarRechazos();"  >

                    </select>
                </td>
                <td class="datos2" >
                    <input type="button" id="EC_back" value="Volver" class="boton">
                    <input type="button" id="EC_parametrosPersonales" value="Parámetros Personales" class="botonGrande 0000000072_2">
                    <input type="button" id="EC_salvar" value="Guardar" class="boton 0000000004_2">
                </td>
            </tr>
            <tr >
                <td class="datos2" id="tagEC_rechazos"> <div id="TitlemotivosRechazo" style="display: none">Motivos del Rechazo:</div></td>
                <td class="datos" colspan="4">
                   <div id="motivosRechazo" style="display: none">

                   </div>
                </td>
            </tr>

            <tr >
                <td class="datos2" id="tagEC_observaciones">Observaciones:</td>
                <td class="datos" colspan="4">
                    <textarea rows="3" cols="95" name="txtDescripcionEditar" id="observacion_CC" title="Observaciones" class="inputFormulario" ></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="5" class="botones_formulario">
                </td>
            </tr>
        </table>
    </fieldset>

    <fieldset class="div_consulta">
        <legend align="center" class="datos"><b> Usuarios </b></legend>

        <table align="right">
            <tr>
                <td> <input type="button" id="EC_search_usuario" value="Buscar Clientes" class="botonGrandeDerecha 0000000004_2"></td>
                <td> <input id="EC_usuario_add" class="botonGrande 0000000072_2" type="button" value="Agregar Firmas Conjuntas"></td>
            </tr>
        </table>

        <%--<div id="div_buscarUsuarioContratoEC" class="div_buscarUsuarioContratoEC" style="display: none">--%>
        <div id="div_buscarUsuarioContratoEC">
            <%--<fieldset class="div_consulta">--%>
                <h3 id="see_id_bu" >Busqueda de clientes</h3>
                <div id="buscaform">

                <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tbody><tr>
                        <td class="datos2" id="tagCIRIFBUC_EC">C.I./R.I.F:</td>
                        <td class="datos">
                            <input type="TEXT" id="CIRIFBUC_EC" class="inputFormulario" title="C.I./R.I.F:" >
                        </td>
                        <td class="datos2" id="tagClienteBUC_EC">Cliente:</td>
                        <td class="datos">
                            <input type="TEXT" id="ClienteBUC_EC" class="inputFormulario" title="Cliente" style="width: 250px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Asesor Financiero:</label></td>
                        <td class="datos">
                            <input  type="text" title="Asesor Financiero" class="inputFormulario" id="financialAdvisorBUC_EC" style="width: 200px">
                        </td>
                        <td><label class="datos2">Ejecutivo de Cuenta:</label></td>
                        <td class="datos">
                            <input type="text" title="Ejecutivo de Cuenta" class="inputFormulario" id="accountExecutiveBUC_EC" style="width: 150px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Cartera N°:</label></td>
                        <td class="datos" colspan="3">
                            <input  type="text" title="ID cliente" id="carteraEUC_EC" class="inputFormulario">
                            <input  type="checkbox" style="width: 20px" id="carteraExacto_EC" class="datos checkFormulario" value="1"><label class="datos"> Buscar por id cliente exacto?</label></input>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2">
                            <div id="div_carga_usuario_EBC" class="oculto carga_espera"><img src="../vbtonline/resources/images/comun/wait.gif" width='30px' alt="" /></div>
                        </td>
                        <td colspan="2">
                            <div id="actionsBU">
                                <input type="button" id="BUC_search_EC" value="Buscar" class="boton"/>
                                <input type="button" id="BUC_reset_EC" value="Limpiar" class="boton"/>
                                <%--<input type="button" id="BUC_close_EC" value="Close" class="boton"/>--%>
                            </div>
                        </td>
                    </tr>
                </table>
                </div>
                <div id="div_tabla_resultadoBusquedaUsuarioContratoEC" class="div_tabla_resultadoBusquedaUsuarioContratoEC" ></div>
                <div id="paginacion_tabla_resultadoBusquedaUsuarioContratoEC" class="div_tabla_resultadoBusquedaUsuarioContratoEC" ></div>
                <a id="close_x_bu" class="close sprited_bu" href="#">close</a>

                <div id="div_mensajes_info_desc" class="info_descp oculto">
                    <div id="mensajes_info_desc">
                        error
                    </div>
                    <div id="cerrar_div_mensajes_info_desc">[X]</div>
                </div>
            <%--</fieldset>--%>
        </div>

        <div id="div_tabla_EC_consultarUsuarioContrato" class="div_tabla_EC_consultarUsuarioContrato"></div>
    </fieldset>
    <fieldset class="div_consulta">
        <legend align="center" class="datos"><b> Carteras </b></legend>
        <input type="button" id="EC_search_cartera" value="Buscar" class="botonDerecha 0000000004_2">


        <%--<div id="div_buscarCarteraContratoEC" class="div_buscarCarteraContratoEC" style="display: none">--%>
        <div id="div_buscarCarteraContratoEC">
            <%--<fieldset class="div_consulta">--%>
                <h3 id="see_id_bc" >B&uacute;squeda de Carteras</h3>
                <div id="buscaformcartera">
                <table SUMMARY='tabla'cellspacing="0" cellpadding="0" width="100%" border="0">
                    <tbody><tr>
                        <td class="datos2" id="tagCIRIFBCC_EC">C.I./R.I.F:</td>
                        <td class="datos">
                            <input type="TEXT" id="CIRIFBCC_EC" title="C.I./R.I.F:" class="inputFormulario">
                        </td>
                        <td class="datos2" id="tagClienteBCC_EC">Cliente:</td>
                        <td class="datos">
                            <input type="TEXT" id="ClienteBCC_EC" title="Cliente" class="inputFormulario" style="width: 250px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Asesor Financiero:</label></td>
                        <td class="datos">
                            <input  type="text" title="Asesor Financiero" id="financialAdvisorBCC_EC" class="inputFormulario" style="width: 200px">
                        </td>
                        <td><label class="datos2">Ejecutivo de Cuenta:</label></td>
                        <td class="datos">
                            <input type="text" title="Ejecutivo de Cuenta" id="accountExecutiveBCC_EC" class="inputFormulario" style="width: 150px">
                        </td>
                    </tr>
                    <tr>
                        <td><label  class="datos2">Cartera N&deg;:</label></td>
                        <td class="datos" colspan="3">
                            <input  type="text" title="Cartera" id="carteraECC_EC" class="inputFormulario" />
                            <input  type="checkbox" style="width: 20px" id="carteraExactoC_EC" class="datos checkFormulario" value="1" /><label class="datos"> Buscar por n&uacute;mero de cartera exacto?</label></input>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" id="BCC_search_EC" value="Buscar" class="boton" />
                            <input type="button" id="BCC_reset_EC" value="Limpiar" class="boton" />
                            <%--<input type="button" id="BCC_close_EC" value="Close" class="boton" />--%>
                        </td>
                        <td colspan="2">
                            <div id="div_carga_carteras_EBC" class="oculto carga_espera_derecha"><img src="../vbtonline/resources/images/comun/wait.gif" width='30px' alt="" /></div>
                        </td>
                    </tr>

                </table>
                </div>
                <div id="div_tabla_resultadoBusquedaCarteraContrato_EC" class="div_tabla_resultadoBusquedaCarteraContrato_EC" ></div>
                <div id="paginacion_tabla_resultadoBusquedaCarteraContrato_EC" class="div_tabla_resultadoBusquedaCarteraContrato_EC" ></div>
                <a id="close_x_bc" class="close sprited_bc" href="#">close</a>

                <div id="div_mensajes_info_desc" class="info_descp oculto">
                    <div id="mensajes_info_desc_bc">
                        error
                    </div>
                    <div id="cerrar_div_mensajes_info_desc_bc">[X]</div>
                </div>
            <%--</fieldset>--%>
        </div>


        <div id="div_tabla_EC_consultarCarterasContrato" class="div_tabla_EC_consultarCarterasContrato">

        </div>
    </fieldset>
</div>

<div id="div_PARAMETROS_PERSONALES_CONTRATOS" style="display: none" class="div_tabla_consulta">
    <div>
        <h1 id="DB_titulo_PC">Par&aacute;metros Personales/ Contrato </h1>
    </div>
    <div id="div_parametrosPersonalesA">

        <fieldset class=" div_consultaTransfers">
            <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
                <tr>
                    <td width="60%">
                        <span class="datos">Contrato N°:</span> <b><span class="datos" id='numeroContratoPC'></span></b>
                    </td>
                </tr>
            </table>
        </fieldset>

        <fieldset class=" div_consultaTransfers">
            <legend id="tagDentroVBT" >Par&aacute;metros de Transferencia dentro de VBT</legend>
            <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
                <tr>
                    <td>
                        <span id="tag_PC_numeroMaxTransDiarias" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="numeroMaximoTransfDiarias_PC" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PC requerido_PC" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                    </td>

                </tr>
                <tr>
                    <td>
                        <span id="tag_PC_montoMaxTransDiarias" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="montoMaxTransDiarias_PC" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PC requerido_PC" style="width: 100px"  onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span id="tag_PC_montoMinTransOpe" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="montoMinTransOpe_PC" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PC requerido_PC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span id="tag_PC_montoMaxTransOpe" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="montoMinTransDiarias_PC" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PC requerido_PC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                    </td>
                </tr>
            </table>
        </fieldset>

        <fieldset class=" div_consultaTransfers">
            <legend id="tagHaciaOtrosBancos" >Hacia Otros Bancos</legend>
            <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
                <tr>
                    <td>
                        <span id="tag_PC_numeroMaxTransDiarias_OB" class="datos">N&uacute;mero M&aacute;ximo de Transferencias Diarias </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="PC_numeroMaxTransDiarias_OB" type="text" title="Número máximo de Transferencias Diarias" class="inputFormulario_PC requerido_PC" style="width: 100px" onkeypress="return onlyDigits(event, this.value,false,false,false,',','.',0);"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span id="tag_PC_montoMaxTransDiarias_OB" class="datos">Monto M&aacute;ximo de Transferencia Diarias </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="PC_montoMaxTransDiarias_OB" type="text" title="Monto máximo de Transferencias Diarias" class="inputFormulario_PC requerido_PC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span id="tag_PC_montoMinTransOpe_OB" class="datos">Monto M&iacute;nimo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="PC_montoMinTransOpe_OB" type="text" title="Monto mínimo de Transferencias por Operación" class="inputFormulario_PC requerido_PC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <span id="tag_PC_montoMaxTransOpe_OB" class="datos">Monto M&aacute;ximo de Transferencia por Operaci&oacute;n </span><span class="datos">:</span>
                    </td>
                    <td>

                        <input id="PC_montoMinTransDiarias_OB" type="text" title="Monto maximo de Transferencia por Operación" class="inputFormulario_PC requerido_PC" style="width: 100px" onblur="this.value=formatCurrency(this.value,true,2,',');" onkeypress="return onlyDigits(event, this.value,true,true,true,',','.',2);"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset class=" div_consultaTransfers">

            <table SUMMARY='tabla' cellspacing="0" cellpadding="0" border="0" width="100%">
                <tr>
                    <td>
                        <input  type="button" id="btn_PC_aceptar" value="Aceptar" class="boton">
                        <input  type="button" id="btn_PC_volver" value="Regresar" class="boton">
                    </td>
                </tr>

            </table>
        </fieldset>

    </div>

</div>
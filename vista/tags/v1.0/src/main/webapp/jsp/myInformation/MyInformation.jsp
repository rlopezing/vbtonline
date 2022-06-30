<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="titulo_misdatos">My Information </h1>
</div>
<div id="div_myInformation">
    <fieldset class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%" align="center">
            <tbody>
            <tr>
                <td>
                    <label class="datos" id="misdatos_TAGNombre">Nombre</label><span class="datos">:</span>
                </td>
                <td>
                    <span class="datos" id="nameMyInformation_info"></span>
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
    <div id="bloque_informacion_documentos">
        <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_documentos'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' alt="" border=0 width='9' height='9'  /> <b><span id="misdatos_TAGDocumentos"> Contactos </span></b></a></span>
        <div id="fieldset_documentos" class="oculto div_Myinformacion_detalle">
            <fieldset class="div_consulta">
                <div id="div_tabla_consulta_MyInfo_Documentos" class="div_tabla_consulta">
                </div>
            </fieldset>
            <input type="hidden" id="documentosAccion" value="mostrar">
        </div>
    </div>
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
</div>
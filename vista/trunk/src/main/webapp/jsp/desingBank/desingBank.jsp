<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1>Dise&ntilde;e su Banco / Par&aacute;metros Personales </h1>
</div>
<div id="div_parametrosPersonales">
    <fieldset class=" div_consultaTransfers">
        <table SUMMARY='tabla' cellspacing="0" cellpadding="0" width="100%" align="center">
            <tbody>
            <tr>
                <td>
                    <label class="datos" id="nameMyInformation">Name:</label>
                </td>
                <td>
                    <span class="datos" id="nameMyInformation_info"></span>
                </td>
                <td>
                    <label class="datos" id="cirifMyInformation">C.I./R.I.F:</label>
                </td>
                <td>
                    <span class="datos" id="cirifMyInformation_info"></span>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="datos" id="mailMyInformation">Mailing Address:</label>
                </td>
                <td>
                    <span class="datos" id="mailMyInformation_info"></span>
                </td>
                <td></td>
                <td></td>
            </tr>

            </tbody>
        </table>
    </fieldset>
    <input type="hidden" id="todoOculto" value="mostrar">
    <br><span class="spanEstilo"><a style="cursor: pointer" id='mostrar_todo'><img src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'> <b>Mostrar Toda la Informaci&oacute;n</b></a></span>
    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_telefono'><img src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'> <b>Tel&eacute;fono</b></a></span>
    <div id="fieldset_telefono" class="oculto">
        <fieldset class="div_consulta">
            <div id="div_tabla_consulta_MyInfo_Telefono" class="div_tabla_consulta">
                
            </div>
        </fieldset>
        <input type="hidden" id="telefonoAccion" value="mostrar">
    </div>

    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_direccion'> <img src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'> <b>Direcci&oacute;n</b></a></span>
    <div id="fieldset_direccion" class="oculto">
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_MyInfo_Direccion" class="div_tabla_consulta">
        </div>
    </fieldset>
    </div>
    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_documentos'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'> <b>Documentos</b></a></span>
    <div id="fieldset_documentos" class="oculto">
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_MyInfo_Documentos" class="div_tabla_consulta">
        </div>
    </fieldset>
        <input type="hidden" id="documentosAccion" value="mostrar">
    </div>
    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_represetantes'><img src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'><b> Representantes</b></a></span>
    <div id="fieldset_represetantes" class="oculto">
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_MyInfo_Representantes" class="div_tabla_consulta">
        </div>
    </fieldset>
        <input type="hidden" id="representantesAccion" value="mostrar">
    </div>
    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_contactos'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'><b> Contactos</b></a></span>
    <div id="fieldset_contactos" class="oculto">
    <fieldset class="div_consulta" >
        <div id="div_tabla_consulta_MyInfo_Contactos" class="div_tabla_consulta">
        </div>
    </fieldset>
        <input type="hidden" id="contactosAccion" value="mostrar">
    </div>
    <span class="spanEstilo"><a style="cursor: pointer" id='mostrar_carteras'><img  src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'> <b>Carteras</b></a></span>
    <div id="fieldset_carteras" class="oculto">
    <fieldset class="div_consulta">
        <div id="div_tabla_consulta_MyInfo_Carteras" class="div_tabla_consulta">
        </div>
    </fieldset>
        <input type="hidden" id="carterasAccion" value="mostrar">
    </div>
</div>
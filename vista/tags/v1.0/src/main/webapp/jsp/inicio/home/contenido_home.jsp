
<span id="x"></span>
<div class="botones_inicio">
    <table SUMMARY='tabla'cellpadding="4" cellspacing="1" align="center" class="tabla_inicio">
        <tbody>
        <tr>
            <td class="titulo_inicio2" rowspan="3">
                <table SUMMARY='tabla' align="center" width="100%">
                    <tr>
                        <td colspan="2" align="center">
                            <b><span id="inicio_label_bienvenida" align="center" >Bienvenido<br></span> </b>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <span id="inicio_label_conexion" align="center">Su &uacute;ltima conexi&oacute;n fue:<br></span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span id="inicio_label_bienvenida_fecha" align="left">Fecha:</span>
                        </td>
                        <td>
                            <span id="inicio_bienvenida_fecha" align="right" style="float: right"></span><br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <span id="inicio_label_bienvenida_hora" align="left" >Hora:</span>
                        </td>
                        <td>
                            <span id="inicio_bienvenida_hora" align="right" style="float: right"></span>
                        </td>
                    </tr>
                </table>
            </td>

            <td class="titulo_inicio">
                <span id="inicio_label_nombreUsuario">Nombre Usuario:</span>
            </td>
            <td class="datos_inicio">
                <span id="inicio_nombreUsuario" class="datos_inicio"></span>
            </td>
        </tr>
        <tr>
            <td class="titulo_inicio">
                <span id="inicio_label_fecha">Fecha(Actual):</span>
            </td>
            <td class="datos_inicio">
                <span id="inicio_fecha" class="datos_inicio"></span>
            </td>
        </tr>
        <tr>
            <td class="titulo_inicio">
                <span id="inicio_label_hora">Hora(Actual):</span>
            </td>
            <td class="datos_inicio">
                <span id="inicio_hora" class="datos_inicio"></span>
            </td>
        </tr>
    </table>
</div>
<div id='pdfFATCA' class="botones_inicio">
    <br>
    <br>
    <br>
    <fieldset id="1FC_form_8" class=" div_informacion_interes right" >

        <TABLE width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="100%" height="35" class="V8NBNegroGris" align="center">
                    <span id="TagNameFATCA" class="datos negrita"><b>Information of Interest</b></span>
                </td>
            </tr>
            <tr>
                <td align="left">
                    <br>
                    <table cellpadding="4" cellspacing="1" align="center">
                        <tr>
                            <td>
                                <a style="cursor: pointer;" id="titleContrato" title=" Click to open the document" onclick="abrirContrato();">
                                    <img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/pdf.gif">
                                </a>
                            </td>
                            <td>
                                <a style="cursor: pointer;" id="titleContrato1" title=" Click to open the document" onclick="abrirContrato();">
                                    <span id="contractoWeb">General Terms & Conditions</span>
                                </a>
                            </td>
                            <td><p></p></td>
                            <td>
                                <a style="cursor: pointer;" id="titleFATCA" title=" Click to open the document" onclick="abrirFATCA();">
                                    <img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/pdf.gif">
                                </a>
                            </td>
                            <td>
                                <a style="cursor: pointer;" id="titleFATCA1" title=" Click to open the document" onclick="abrirFATCA();">
                                    <span id="">FATCA</span>
                                </a>
                            </td>

                        </tr>
                    </table>
                </td>
            </tr>
            <tr id="manualFC">
                <td align="center">
                <table align="center">
                    <tr>
                        <td>
                            <a style="cursor: pointer;"  title=" Click to open the document" onclick="abrirGuiaFC();">
                                <img style="cursor: pointer;" width="20" height="20" border="0" src="../vbtonline/resources/images/comun/pdf.gif">
                            </a>
                        </td>
                        <td>
                            <a style="cursor: pointer;" title=" Click to open the document" onclick="abrirGuiaFC();">
                                <span id="tagGuiaUsuario">User guide</span>
                            </a>
                        </td>
                    </tr>
                </table>
                </td>
            </tr>
            <tr>
                <td align="left" style="padding-left:10px;padding-top:10px">
                  <span id="inicio_infoAccionesUsuario">
                    With VBT Bank &amp; Trust Online you can access all your accounts, consult balances and transactions, download your monthly statements and make internal transfers and transfers to other Banks.

                    To start navigate please select any option on the menu.    </span></td>
            </tr>
        </table>
    </fieldset>
</div>
<div id="inicio_infoVBT" class="botones_inicio">


</div>
<input type="hidden" id="tipo_usuario_app" value="">
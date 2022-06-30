<%--
  Created by IntelliJ IDEA.
  User: Mary Bernot
  Date: 19/09/13
  Time: 02:41 PM
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="TarjetaCoordActivacion_TAG_Title">Activar tarjeta de coordenadas </h1>
</div>
<div id="div_tarjeta_coordenada">

        <div class="titulo_tarjeta_coord">
            <span id="TarjetaCoordActivacion_TAG_Seguridad">Seguridad</span>

            <div class="descripcion_tarjeta_coord">

                <BR><B><span id="TarjetaCoordActivacion_TAG_Subtitulo" class="">Activación de la Tarjeta de Coordenadas></span>     </B><br>
                <span id="TarjetaCoordActivacion_TAG_Pasos" class="">

                  <ol>
                      <li id="TarjetaCoordActivacion_TAG_step1">Presione el botón Activar. </li>
                      <li id="TarjetaCoordActivacion_TAG_step2">Recibirá un mensaje de texto en su teléfono celular con su Clave de Activación.</li>
                      <li id="TarjetaCoordActivacion_TAG_step3">Ingrese la Clave de Activación.</li>
                      <li id="TarjetaCoordActivacion_TAG_step4">El sistema le indicará que la Tarjeta de Coordenadas ha sido activada.</li>

                  </ol>
                    <span id="TarjetaCoordActivacion_TAG_step5">En caso de algún inconveniente, comuníquese con su Asesor o Ejecutivo de Cuentas.</span>  <BR><BR>

                    <span id="TarjetaCoordenadas_TAG_advice1">En caso de robo, extravío, deterioro o si presume que su tarjeta fue expuesta a terceras personas, vuelva a generar otra Tarjeta de Coordenadas en la opción del menú: Diseñe su banco / Generar Tarjeta Coordenadas.</span>
                </span> <br>
            </div>
        </div>

        <div class="botones_salir">

            <table SUMMARY='tabla'>
                <tbody>
                <tr>
                    <td class="datos" colspan="2">
                        <span class="label_TarjetaCoordActivacion_serial"
                              id="TarjetaCoordActivacion_TAG_Serial">Serial</span>:
                    </td>
                </tr>
                <tr>
                    <td class="datos" colspan="2">
                        <span  id="TarjetaCoordActivacion_serial">  </span>
                    </td>
                </tr>
                <tr>
                    <td class="datos">
                       <B> <span id="TarjetaCoordActivacion_msg"></span> </B>
                    </td>
                </tr>
                <tr>
                    <td class="datos" colspan="2">
                        <input type="button" id="TarjetaCoordActivacion_BTN_Activar" value="Activar" class="botonEDOCuenta">
                    </td>
                </tr>

                </tbody>
            </table>
        </div>
</div>

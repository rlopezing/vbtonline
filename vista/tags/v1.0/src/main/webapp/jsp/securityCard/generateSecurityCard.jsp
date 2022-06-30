<%--
  Created by IntelliJ IDEA.
  User: Mary Bernot
  Date: 19/09/13
  Time: 02:41 PM
--%>
<!-- COLOCAR DIV CON CODIGO HTML DE FORMULARIO -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <h1 id="TarjetaCoordenadas_TAG_Title">Generar tarjeta de coordenada </h1>
</div>
<div id="div_tarjeta_coordenada">
    <div class="titulo_tarjeta_coord">

        <span id="TarjetaCoordenadas_Seguridad">Seguridad</span>
        <div class="descripcion_tarjeta_coord">


            <span id="TarjetaCoordenadas_TAG_Descripcion"> La tarjeta de coordenadas le permitirá realizar de manera segura sus operaciones, tales como:  Transferencias a otros bancos, afiliación de destinos de transferencia, actualización de parámetros personales, etc.</span><BR><BR>
           <B><span id="TarjetaCoordenadas_TAG_Subtitulo" class="">Pasos para generar su Tarjeta de Coordenadas></span>     </B><br>
                <span id="TarjetaCoordenadas_TAG_Pasos" class="">

                  <ol>



                      <li id="TarjetaCoordenadas_TAG_step1">Presione el botón Generar. </li>
                      <li id="TarjetaCoordenadas_TAG_step2">El sistema generará una tarjeta de coordenadas única asociada. Imprimala</li>
                      <li id="TarjetaCoordenadas_TAG_step3">Para la ctivación de dicha tarjeta dirigase a la opción del menú: Diseñe su Banco / Activar Tarjeta de Coordenadas </li>


                  </ol>


                    <span id="TarjetaCoordenadas_TAG_advice1">En caso de robo, extravío, deterioro o si presume que su tarjeta fue expuesta a terceras personas, vuelva a generar otra Tarjeta de Coordenadas siguiendo los pasos antes descritos, la tarjeta anterior será bloqueada automáticamente.</span>

                </span> <br>
        </div>
    </div>

    <div class="botones_salir">

        <table SUMMARY='tabla'>
            <tbody>
            <tr>
                <td class="datos" colspan="2">
                      <input type="hidden" id="TarjetaCoordenadas_status" value="">
                    <input type="hidden" id="TarjetaCoordenadas_mensaje" value="">

                      <B>  <span
                                id="TarjetaCoordenadas_TAG_Usuario">Usuario</span>:</B>   <span
                        id="TarjetaCoordenadas_usuario"></span>
                </td>
            </tr>

           <%-- <tr>
                <td class="datos" colspan="2">
                    <B><span class="label_TarjetaCoordActivacion_serial"
                              id="TarjetaCoordActivacion_TAG_Serial">Serial</span>:  </B> <span  id="TarjetaCoordenadas_serial">  </span>
                </td>
            </tr>
               --%>
            <tr>
                <td class="datos" colspan="2">
                    <B>  <span  id="TarjetaCoordenadas_status">  </span>  </B>
                </td>
            </tr>

            <tr>
                <td class="datos">
                    <B> <span id="TarjetaCoordenada_msg"></span> </B>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
            </tr>
            <tr>
                <td class="datos" colspan="2">
                    <input type="button" id="TarjetaCoordenadas_BTN_Generar" value="Generar" class="botonGrande">
                </td>
            </tr>
            <tr>
                <td class="datos" colspan="2">

                </td>
            </tr>
            </tbody>
        </table>
       <div id="TarjetaCoordenadas_activar" class="datos descripcion_tarjeta_coord">
            <span id="TarjetaCoordenadas_TAG_advice2">Recuerde que dispone de 24 horas para activar su Tarjeta de Coordenadas. Para activar su tarjeta presione <a onClick="javascript:cargarActivacionTarjetaCoordenadasJSONData();" class="V8NBVerde"> aquí </a> </span>    <BR><BR>
        </div>
    </div>


</div>

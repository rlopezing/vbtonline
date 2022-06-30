var urlValidarClaveMail="Transfers_validarClaveTransferenciasTOB.action";
var urlValidarClaveMail="Transfers_validarClaveTransferenciasTOB.action";
var urlValidarClaveSMS="Security_validarClaveSMS.action";
var urlCargarCoordenadas="Security_cargarCoordenadas.action";
var urlValidarClaveTarjetaCoordenada="Security_validarCoordenadas.action"
var urlCargarMetodos="Security_cargarMetodos.action";
var urlLogMetodosdeValidacion="DesingBank_guardarLogMetodosValidacion.action";
var cantMetodos=0;
var pasoActual=0;

$(document).ready(function(){

    $("#countryCodeVM").intlTelInput();

    $("#selec_correo").click(function(){
        TransferGenerarClaveJSONData();
        $(".div_opc_clave_transf").addClass("oculto");
        $("#div_correo_clave_transf").removeClass("oculto");
        transferencia_MetodoValidar_Clave="email";
        $("#pwdClaveConfirmTransfer_TOB").val("");
        $("#btnAceptar").removeClass("oculto");
        $("#btn_cambiarMtdClave").removeClass("oculto");
        if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
            $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");

    });


    $("#btn_cambiarMtdClave").click(function(){
        $(".div_opc_clave_transf").addClass("oculto");
        $("#filtro_opc_clave").removeClass("oculto");
        transferencia_MetodoValidar_Clave="";
        ocultarBotones(validationMethod);
        $("#btn_cambiarMtdClave").addClass("oculto");
        $("#btnAceptar").addClass("oculto");

    });


    $("#btnCancelar").click(function(){
        regresarOperaciones();
        guardarLogMetodosdeValidacionJSONData("27", "1","26", "Canceló la opción métodos de validación");

    });

    $("#btnAceptar").click(function(){

        $("#btnAceptar").prop('disabled', true);
        $("#div_carga_espera").removeClass("oculto");
        callValidationMethods();
        $("#btnAceptar").prop('disabled', false);
        $("#div_carga_espera").addClass("oculto");
    });

    $("#btnNext").click(function(){
        var enc=0;
        // $("#div_carga_espera").css("width",$("espacio_divs").css("width"));
        // $("#div_carga_espera").css("height",$("espacio_divs").css("height"));
        $("#div_carga_espera").removeClass("oculto");
        //$("#div_carga_espera").attr("style","display: block");
        switch(pasoActual){
            case 1:
                cargarMetodos();
                guardarLogMetodosdeValidacionJSONData("27", "1","26", "Avanzo al primer paso (Metodos de validación)");
                if (cantMetodos==0){
                    $("#paso1").attr("style","display: none");
                    $("#paso2").attr("style","display: none");
                    $("#paso3").attr("style","display: none");
                    $("#pasoActivacion").attr("style","display: none");
                    $("#navNota").addClass("oculto");
                    $("#btnNext").addClass("oculto");
                    $("#btnPrev").addClass("oculto");
                    if(origenMetodosValidacion!="tarjetaCoordenadas"){
                        $("#pasoNoMetodos").fadeIn("slow");
                    }else{
                        $("#pasoNoMetodosActivacion").fadeIn("slow");
                    }

                }else{

                    if(origenMetodosValidacion!="tarjetaCoordenadas"){
                        $("#paso1").attr("style","display: none");
                    }else{

                        cargarActivacionTarjetaCoordenadasJSONData();
                        $("#pasoActivacion").attr("style","display:none");
                        $("#TAGSerialTarjetaMTD").removeClass("oculto");
                    }

                    $("#btnPrev").removeClass("oculto");
                    $("#navNota").removeClass("oculto");
                    $("#navPaso1").removeClass("check__item--uncomplete");
                    $("#iconPaso1").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                    $("#navPaso2").addClass("check__item--uncomplete");
                    $("#iconPaso2").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
                    // $("#TAGSeleccionarMetodo").addClass("negrita");
                    $("#TAGSeleccionarMetodo").addClass("check__text--active");
                    // $("#TAGInstruccionesMetodo").removeClass("negrita");
                    $("#TAGInstruccionesMetodo").removeClass("check__text--active");
                    $("#TAGInstruccionesMetodo").addClass("check__text--complete");
                    $("#paso2").fadeIn("slow");
                }
                pasoActual=2;
                break;
            case 2:
                guardarLogMetodosdeValidacionJSONData("27", "1","26", "Avanzo al segundo paso (Metodos de validación)");
                $('input[name=metodoSeleccionado]:radio').each(function(){
                   if (this.checked != false){
                    enc++;
                   }
                });

                if (enc>0){
                    //$("#div_carga_espera").removeClass("oculto");
                    $("#paso2").attr("style","display: none");
                    $("#navPaso2").removeClass("check__item--uncomplete");
                    $("#iconPaso2").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                    $("#navPaso3").addClass("check__item--uncomplete");
                    $("#iconPaso3").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
                    $("#TAGSeleccionarMetodo").removeClass("check__text--active");
                    $("#TAGSeleccionarMetodo").addClass("check__text--complete");
                    $("#TAGValidarOperacion").addClass("check__text--active");
                    $("#btnNext").addClass("oculto");
                    $("#btnAceptar").removeClass("oculto");
                    $("#navNota").addClass("oculto");
                    $("#paso3").fadeIn("slow");
                    cargarDatosMetodo();
                    showMetodo();
                    pasoActual=3;
                    //$("#div_carga_espera").addClass("oculto");
                }else{

                    if(idioma=="_us_en")
                        alert("You must select a method of validation!");
                    else
                        alert("Debe seleccionar un metodo de validación!");
                }
                break;
        }
        $("#div_carga_espera").addClass("oculto");
        //$("#div_carga_espera").attr("style","display: none");
    });


    $("#btnPrev").click(function(){
        guardarLogMetodosdeValidacionJSONData("27", "1","26", "Se regreso al paso anterior (Metodos de validación)");
        switch(pasoActual){
            case 3:
                $("#btnNext").removeClass("oculto");
                $("#navPaso3").removeClass("check__item--active");
                $("#navPaso3").removeClass("check__item--uncomplete");
                $("#iconPaso3").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
                $("#navPaso2").addClass("check__item--uncomplete");
                $("#iconPaso2").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
                $("#TAGSeleccionarMetodo").addClass("check__text--active");
                $("#TAGValidarOperacion").removeClass("check__text--active");
                $("#TAGValidarOperacion").removeClass("check__text--complete");
                $("#btnAceptar").addClass("oculto");
                $("#navNota").removeClass("oculto");
                $("#paso3").attr("style","display: none");
                $("#paso2").fadeIn("slow");
                pasoActual=2;
                break;
            case 2:
                $("#paso3").attr("style","display: none");
                $("#navPaso2").removeClass("check__item--active");
                $("#iconPaso2").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
                $("#navPaso1").addClass("check__item--uncomplete");
                $("#iconPaso1").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
                $("#TAGSeleccionarMetodo").removeClass("check__text--active");
                $("#TAGSeleccionarMetodo").removeClass("check__text--complete");
                $("#TAGInstruccionesMetodo").addClass("check__text--active");
                $("#btnAceptar").addClass("oculto");
                $("#navNota").removeClass("oculto");
                $("#paso2").attr("style","display: none");
                $("#btnPrev").addClass("oculto");
                if(origenMetodosValidacion!="tarjetaCoordenadas"){
                    $("#paso1").attr("style","display: ");

                }else{
                    $("#pasoActivacion").attr("style","display:");
                }

                pasoActual=1;
                break;
            case 1:
                $("#btnPrev").addClass("oculto");
                pasoActual=0;
                break;
        }
    });

});


function callValidationMethods(){
    var mensaje="";


    switch(transferencia_MetodoValidar_Clave){
        case "email":

            if(Trim($("#pwdClaveConfirmTransfer_TOB").val())==""){
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field - "+ $("#pwdClaveConfirmTransfer_TOB").title+"<br>";
                else
                    mensaje=mensaje+"Campo requerido -  "+ $("#pwdClaveConfirmTransfer_TOB").title+"<br>";
                $("#pwdClaveConfirmTransfer_TOB").addClass("error_campo");
            }else{
                if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                    $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");
            }

            if(mensaje!=""){
                $("#div_carga_espera").addClass("oculto");
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                ValidarClaveMailJSONData();
            }
        break;
        case "TarjetaCoordenada":

            if(Trim($("#coordenada_1").val())==""){
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field - "+ $("#lbl_caja_coord_1").html()+"<br>";
                else
                    mensaje=mensaje+"Campo requerido -  "+ $("#lbl_caja_coord_1").html()+"<br>";
                $("#coordenada_1").addClass("error_campo");
            }else{
                if($("#coordenada_1").hasClass("error_campo"))
                    $("#coordenada_1").removeClass("error_campo");
            }

            if(Trim($("#coordenada_2").val())==""){
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field - "+ $("#lbl_caja_coord_2").html()+"<br>";
                else
                    mensaje=mensaje+"Campo requerido -  "+ $("#lbl_caja_coord_2").html()+"<br>";
                $("#coordenada_2").addClass("error_campo");
            }else{
                if($("#coordenada_2").hasClass("error_campo"))
                    $("#coordenada_2").removeClass("error_campo");
            }

            if(mensaje!=""){
                $("#div_carga_espera").addClass("oculto");
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                ValidarClaveTarjetaCoordenadaTransferJSONData();
            }


        break;
        case "sms":

            if(Trim($("#pwdClaveConfirmSMS").val())==""){
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field - Confirmation code <br>";
                else
                    mensaje=mensaje+"Campo requerido - C&oacute;digo de Confirmaci&oacute;n<br>";
                $("#pwdClaveConfirmSMS").addClass("error_campo");
            }else{
                if($("#pwdClaveConfirmSMS").hasClass("error_campo"))
                    $("#pwdClaveConfirmSMS").removeClass("error_campo");
            }

            if(mensaje!=""){
                $("#div_carga_espera").addClass("oculto");
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                ValidarClaveSMSJSONData();
            }

        break;
        default:
            if(idioma=="_us_en")
                alert("You must select a method of validation!");
            else
                alert("Debe seleccionar un metodo de validación!");
            $("#div_carga_espera").addClass("oculto");
    }

}


function callValidationMethodsSuccess(){
    $("#pwdClaveConfirmTransfer_TOB").val("");
    guardarLogMetodosdeValidacionJSONData("27", "1","26", "Se validó con éxito la operación");
    switch(origenMetodosValidacion){
        case "transferenciasOtrosBancos":
            seleccionarOpcionBack("TRANSFERENCIA_EXTERNA");
            TransferGuardarJSONData();
            break;
        case "parametrosPersonales":
            regresarOperaciones();
            parametrosPersonalesActualizarJSONData();
            break;
        case "cambiarClave":
            regresarOperaciones();
            cambiarClaveJSONData();
            break;
        case "tarjetaCoordenadas":
            activar_TarjetaCoordenadasJSONData();
            mostrarSeguridad="S";
            regresarOperaciones();
            break;
        case "aprobarTemplate":
            cambiarEstatusTemplate(idTemplate);
            regresarOperaciones();
            break;
        case "aprobarTemplateTensferencias":
            cambiarEstatusTemplate(idTemplate);
            regresarOperaciones();
            break;
        case "aprobarTransferenciaFC":
            saveTransfersToApproveJSONData(numReferenciaGlobal,montosGlobal);
            $("#btn_cancelarClave_aprobar").click();
            regresarOperaciones();
            break
        case "liberarTransferenciaFC":
            saveTransfersToReleaseJSONData(numReferenciaGlobal);
            $("#btn_cancelarClave_liberar").click();
            regresarOperaciones();
            break;
        case "aprobarPagosTdcFC":
            savePaymentsTdcToApproveJSONData(numReferenciaGlobal,montosGlobal);
            $("#btn_cancelarClave_aprobarPagosTdc").click();
            regresarOperaciones();
            break
        case "liberarPagosTdcFC":
            savePaymentsTdcToReleaseJSONData(numReferenciaGlobal);
            $("#btn_cancelarClave_liberarPagosTdc").click();
            regresarOperaciones();
            break;
        case "transferenciaTercerosVBT":
            TransferToOtherClientGuardarJSONData();
            regresarOperaciones();
            break;
        case "desbloqueoTarjeta": {
            crearReglaJSONData();
            seleccionarOpcionBack("BLOQUEO_TARJETA");
            break;
        }

        case "editarDesbloqueoTarjeta": {
            editarReglaCreditCardJSONData();
            seleccionarOpcionBack("BLOQUEO_TARJETA");
            break;
        }
        case "eliminarDesbloqueoTarjeta": {
            eliminarReglaJSONData(valores.split("|")[1]);
            seleccionarOpcionBack("BLOQUEO_TARJETA");
            break;
        }
        case "paymentCard": {
           //guardarPago
            guardarPagoTdc();
            seleccionarOpcionBack("PAGO_TARJETA");
            break;
        }

    }

    //guardarLogMetodosdeValidacionJSONData("27", "1","26", "Finalizo la opcion Métodos de Validación");
}

function callValidationMethodsFail(exito,mensaje){
    var mostrarMensaje=1;
    var elemento="";
    guardarLogMetodosdeValidacionJSONData("27", "1","26", "Falló la validación de la operación (Código o Coordenadas incorrectas)");
    switch(transferencia_MetodoValidar_Clave){
        case "email":
            elemento="#pwdClaveConfirmTransfer_TOB";
            $(elemento).addClass("error_campo");
            break;
        case "TarjetaCoordenada":
            $("#coordenada_1").addClass("error_campo");
            $("#coordenada_2").addClass("error_campo");
        case "sms":
            elemento="#pwdClaveConfirmSMS";
            $(elemento).addClass("error_campo");
            break;
    }

    switch(origenMetodosValidacion){
        case "metodosValidacionTransfer":
            $("#btn_aceptarClave").removeAttr("disabled");
             if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
               /* infoPaginaJSONData();
                $("#btn_cambiarMtdClave").click();
                $("#btn_PP_cancelar_clave").click();
                if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                     $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo"); */
             }

             break;
        case "parametrosPersonales":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
               /* $("#btnCancelar").click();
                if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                    $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;
        case "cambiarClave":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
               /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                    $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo"); */
            }
            break;
        case "tarjetaCoordenadas":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
               /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                    $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "aprobarTemplate":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
                /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                 $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "aprobarTemplateTensferencias":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
                /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                 $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "aprobarTransferenciaFC":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
                /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                 $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "liberarTransferenciaFC":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
                /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                 $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "aprobarPagosTdcFC":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
                /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                 $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "liberarPagosTdcFC":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
                /* if($("#pwdClaveConfirmTransfer_TOB").hasClass("error_campo"))
                 $("#pwdClaveConfirmTransfer_TOB").removeClass("error_campo");  */
            }
            break;

        case "transferenciaTercerosVBT":
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
            }
            break;
        case "desbloqueoTarjeta": {
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
            }
            break;
        }
        case "editarDesbloqueoTarjeta": {
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
            }
            break;
        }

        case "eliminarDesbloqueoTarjeta": {
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
            }
            break;
        }
        case "paymentCard": {
            if (exito=="Cancel"){
                mostrarMensaje=0;
                alert(mensaje);
                salir();
            }
            break;
        }


    }


    if (mostrarMensaje!=0){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
}



function ValidarClaveMailJSONData(){
    var url = urlValidarClaveMail;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#pwdClaveConfirmTransfer_TOB").val();

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,ValidarClaveMailSuccess,null,null);
}


function ValidarClaveMailSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.claveValida;
    var mensaje=result.mensaje;
    idioma = result.idioma;

    if(exito=="OK"){

        callValidationMethodsSuccess();

    }else{
        callValidationMethodsFail(exito,mensaje);

    }



}


function ValidarClaveSMSJSONData(){
    var url = urlValidarClaveSMS;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#pwdClaveConfirmSMS").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,ValidarClaveSMSSuccess,null,null);
}


function ValidarClaveSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.resultado;
    var mensaje=result.mensaje;
    idioma = result.idioma;


    if(exito=="OK"){

        callValidationMethodsSuccess();

    }else{
        callValidationMethodsFail(exito,mensaje);

    }



}


var tarjetaCoordenadas={
    dimensionX:9,
    dimensionY:5,
    //coordenada 1
    coordenada1_letra:"",
    coordenada1_letra_pos:0,
    coordenada1_num:"0",
    coordenada1_num_pos:0,
    //coordenada 2
    coordenada2_letra:"",
    coordenada2_letra_pos:0,
    coordenada2_num:"0",
    coordenada2_num_pos:0,
    color_1:"rgba(27, 135, 59, 0.59)",
    color_2:"rgba(40, 92, 144, 0.6)"
}



function crearTarjetaCoordenadas(){
    var tarjeta=$("#tarjeta_coordenadas");
    tarjeta.css({width:tarjetaCoordenadas.dimensionX*33});
    tarjeta.css({height:tarjetaCoordenadas.dimensionY*26});
    tarjeta.empty();


    // cabecera de numeros
    for(var i=1;i<=tarjetaCoordenadas.dimensionX;i++){
        if(tarjetaCoordenadas.coordenada1_letra_pos==i){
            $('<div class="campo_tarjeta letra_coord_1 campo_letra opaco posY_'+0+' posX_'+i+'"></div>').appendTo(tarjeta);
        }else if(tarjetaCoordenadas.coordenada2_letra_pos==i){
            $('<div class="campo_tarjeta letra_coord_2 campo_letra opaco posY_'+0+' posX_'+i+'"></div>').appendTo(tarjeta);
        }else{
            $('<div class="campo_tarjeta  campo_letra opaco posY_'+0+' posX_'+i+'"></div>').appendTo(tarjeta);
        }
    }
    $('.posY_0.posX_1').addClass("campo_invisible");

    for(var posY=1;posY<=tarjetaCoordenadas.dimensionY;posY++){
        for(var posX=1;posX<=tarjetaCoordenadas.dimensionX;posX++){
            if(posX==1){
                if(tarjetaCoordenadas.coordenada1_num_pos==posY){
                    $('<div class="campo_tarjeta num_coord_1 opaco posicion posY_'+posY+' posX_'+posX+'"></div>').appendTo(tarjeta);
                }else if(tarjetaCoordenadas.coordenada2_num_pos==posY){
                    $('<div class="campo_tarjeta num_coord_2 opaco posicion posY_'+posY+' posX_'+posX+'"></div>').appendTo(tarjeta);
                }else{
                    $('<div class="campo_tarjeta  posicion opaco posY_'+posY+' posX_'+posX+'"></div>').appendTo(tarjeta);
                }
            }else{
                if((tarjetaCoordenadas.coordenada1_num_pos==posY)&&(tarjetaCoordenadas.coordenada1_letra_pos==posX)){
                    $('<div id="opc1_selected" class="campo_tarjeta  campo_num opaco"></div>').appendTo(tarjeta);
                }else if((tarjetaCoordenadas.coordenada2_num_pos==posY)&&(tarjetaCoordenadas.coordenada2_letra_pos==posX)){
                    $('<div id="opc2_selected" class="campo_tarjeta  campo_num opaco"></div>').appendTo(tarjeta);
                }else{
                    $('<div class="campo_tarjeta  campo_num opaco posY_'+posY+' posX_'+posX+'"></div>').appendTo(tarjeta);
                }


            }
        }
    }
};


function pintarCoordenadas_1(color){
    $(".num_coord_1").removeClass("opaco").html(tarjetaCoordenadas.coordenada1_num);
    $(".letra_coord_1").removeClass("opaco").html(tarjetaCoordenadas.coordenada1_letra);
    $(".posY_"+tarjetaCoordenadas.coordenada1_num_pos).animate({
        backgroundColor: color,
        opacity: 1
    }, 900 );
    $(".posX_"+tarjetaCoordenadas.coordenada1_letra_pos).animate({
        backgroundColor: color,
        opacity:1
    }, 900 ,function(){
        $("#opc1_selected").removeClass("opaco").addClass("selected opc1");
    });

}



function pintarCoordenadas_2(color){
    $(".num_coord_2").removeClass("opaco").html(tarjetaCoordenadas.coordenada2_num);
    $(".letra_coord_2").removeClass("opaco").html(tarjetaCoordenadas.coordenada2_letra);
    $(".posY_"+tarjetaCoordenadas.coordenada2_num_pos).animate({
        backgroundColor: color,
        opacity: 1
    }, 600 );
    $(".posX_"+tarjetaCoordenadas.coordenada2_letra_pos).animate({
        backgroundColor: color,
        opacity: 1
    }, 600 ,function(){
        $("#opc2_selected").removeClass("opaco").addClass("selected opc2");
    });

}

function limpiarTarjeta(){
    $("#tarjeta_coordenadas  div").removeAttr("style");
    $(".num_coord_2").removeClass("opc2").addClass("opaco").html("");
    $(".letra_coord_2").removeClass("opc2").addClass("opaco").html("");
    $(".num_coord_1").removeClass("opc1").addClass("opaco").html("");
    $(".letra_coord_1").removeClass("opc1").addClass("opaco").html("");
    $("#opc1_selected").addClass("opaco").removeClass("selected opc1");
    $("#opc2_selected").addClass("opaco").removeClass("selected opc2");
}


function cargarTarjetaCoordenadas(){
    var url = urlCargarCoordenadas;
    var param={};

    sendServiceJSON_sinc(url,param,cargarTarjetaCoordenadasSuccess,null,null);
}


function cargarTarjetaCoordenadasSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    var respuesta=result.respuesta;
    var coordenadas= result.coordenadas;
    var mensaje="";

    if(respuesta=="0"){

        transferencia_MetodoValidar_Clave="TarjetaCoordenada";
        //Indica en que pantalla Esta y que botones debe mostrare



        tarjetaCoordenadas.dimensionX=parseInt(result.columnas)+1;
        tarjetaCoordenadas.dimensionY=result.filas;

        tarjetaCoordenadas.coordenada1_letra=result.coordenadas.split("*")[0].split("|")[1].split("-")[0];
        tarjetaCoordenadas.coordenada1_letra_pos=result.coordenadas.split("*")[0].split("|")[1].split("-")[1];
        tarjetaCoordenadas.coordenada1_num=result.coordenadas.split("*")[0].split("|")[0].split("-")[0];
        tarjetaCoordenadas.coordenada1_num_pos=result.coordenadas.split("*")[0].split("|")[0].split("-")[1];


        tarjetaCoordenadas.coordenada2_letra=result.coordenadas.split("*")[1].split("|")[1].split("-")[0];
        tarjetaCoordenadas.coordenada2_letra_pos=result.coordenadas.split("*")[1].split("|")[1].split("-")[1];
        tarjetaCoordenadas.coordenada2_num=result.coordenadas.split("*")[1].split("|")[0].split("-")[0];
        tarjetaCoordenadas.coordenada2_num_pos=result.coordenadas.split("*")[1].split("|")[0].split("-")[1];


        $("#lbl_serial").html("S/N: "+result.serial);
        $("#lbl_caja_coord_1").html(tarjetaCoordenadas.coordenada1_num+tarjetaCoordenadas.coordenada1_letra);
        $("#lbl_caja_coord_2").html(tarjetaCoordenadas.coordenada2_num+tarjetaCoordenadas.coordenada2_letra);


        crearTarjetaCoordenadas();
    } else{
        if (respuesta=="1"){
            if(idioma=="_us_en")
                mensaje="You do not have an active card Security";
            else
            mensaje="No posee una tarjeta de coordenadas activa";
        }else{
            if(respuesta=="2"){
                if(idioma=="_us_en")
                    mensaje="The Code card has expired";
                else
                    mensaje="La tarjeta de coordenadas asignada se encuentra vencida";
            }else{
                if(idioma=="_us_en")
                    mensaje="An error has been occurred";
                else
                    mensaje="Error consultando la tarjeta de coordenadas";

            }
        }



        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }
    }




}


function ValidarClaveTarjetaCoordenadaTransferJSONData(){
    var url = urlValidarClaveTarjetaCoordenada;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= tarjetaCoordenadas.coordenada1_letra;
    jsonTransfers[1]= tarjetaCoordenadas.coordenada1_num;
    jsonTransfers[2]= $("#coordenada_1").val();
    jsonTransfers[3]= tarjetaCoordenadas.coordenada2_letra;
    jsonTransfers[4]= tarjetaCoordenadas.coordenada2_num;
    jsonTransfers[5]= $("#coordenada_2").val();

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,ValidarClaveTarjetaCoordenadaSuccess,null,null);

}

function ValidarClaveTarjetaCoordenadaSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.resultado;
    var mensaje=result.mensaje;

    if(exito=="OK"){

        callValidationMethodsSuccess();

    }else{
        callValidationMethodsFail(exito,mensaje);

    }



}


function mainValidationMethods(origen){
    pasoActual=1;

    $("#navPaso1").removeClass("check__item--active");
    $("#navPaso1").removeClass("check__item--uncomplete");
    $("#navPaso2").removeClass("check__item--active");
    $("#navPaso2").removeClass("check__item--uncomplete");
    $("#navPaso3").removeClass("check__item--active");
    $("#navPaso3").removeClass("check__item--uncomplete");
    $("#navPaso1").addClass("check__item--uncomplete");
    $("#iconPaso1").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#navPaso2").addClass("");
    $("#iconPaso2").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#navPaso3").addClass("");
    $("#iconPaso3").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");

    $("#paso2").attr("style","display:none");
    $("#paso3").attr("style","display:none");
    $("#pasoNoMetodos").attr("style","display:none");
    $("#pasoNoMetodosActivacion").attr("style","display: none");
    $("#TAGSerialTarjetaMTD").html("");
    $("#TAGSerialTarjetaMTD").addClass("oculto");
    $("#navNota").removeClass("oculto");
    $("#btnNext").removeClass("oculto");
    $("#btnPrev").addClass("oculto");
    $("#btnAceptar").addClass("oculto");
    // $("#TAGInstruccionesMetodo").addClass("negrita");
    $("#TAGInstruccionesMetodo").removeClass("check__text--complete");
    $("#TAGInstruccionesMetodo").addClass("check__text--active");
    // $("#TAGSeleccionarMetodo").removeClass("negrita");
    $("#TAGSeleccionarMetodo").removeClass("check__text--complete");
    $("#TAGSeleccionarMetodo").removeClass("check__text--active");
    $("#TAGValidarOperacion").removeClass("check__text--complete");
    $("#TAGValidarOperacion").removeClass("check__text--active");
    valCambioClave="";
    if(origen!="tarjetaCoordenadas"){
        $("#paso1").attr("style","display:");
        $("#selec_tarjetaCoord").removeAttr("style","display:none");
        $("#TAGSerialTarjeta").removeClass("oculto");
        $("#TAGNotaMetodo1").removeClass("oculto");
        $("#TAGTituloProteccion1").removeClass("oculto");
        $("#TAGSeleccionarTitulo").removeClass("oculto");
        $("#pasoActivacion").attr("style","display:none");
    }else{
        $("#pasoActivacion").attr("style","display:");
        $("#paso1").attr("style","display:none");
        $("#selec_tarjetaCoord").attr("style","display:none");
        $("#TAGSerialTarjeta").addClass("oculto");
        $("#TAGSeleccionarTitulo").addClass("oculto");
        $("#TAGTituloProteccion1").addClass("oculto");
        $("#TAGNotaMetodo1").addClass("oculto");
    }

    switch(origen){
        case "transferenciasOtrosBancos":
            $("#tituloOrigen").html("<h1 id='titleOrigenTOB'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenTOB").html("Validation Methods (TRANSFERS / To other Banks)");
            else
                $("#titleOrigenTOB").html("Metodos de Validación (Transferencias / A Otros Bancos)");
            break;
        case "parametrosPersonales":

            $("#tituloOrigen").html("<h1 id='titleOrigenParametrosPersonales'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenParametrosPersonales").html("Validation Methods (Design Bank / Personal Parameters)");
            else
                $("#titleOrigenParametrosPersonales").html("Metodos de Validación (Diseñe su Banco / Parámetros Personales)");
            break;

        case "tarjetaCoordenadas":

            $("#tituloOrigen").html("<h1 id='titleOrigenTarjetaCoordenadas'></h1></h1>");
            if(idioma=="_ve_es")
                $("#titleOrigenTarjetaCoordenadas").html("Activar Tarjeta de Coordenadas");
            else
                $("#titleOrigenTarjetaCoordenadas").html("Activate Code Card");
            break;
        case "cambiarClave":
            valCambioClave="S";
            $("#tituloOrigen").html("<h1 id='titleOrigenCambiarClave'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenCambiarClave").html("Validation Methods (Design your Bank / Change Login Password)");
            else
                $("#titleOrigenCambiarClave").html("Metodos de Validación (Diseñe su Banco / Cambiar Clave)");
            break;
        case "aprobarTemplate":
            $("#tituloOrigen").html("<h1 id='titleOrigenAprobarTemplate'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenAprobarTemplate").html("Validation Methods (Approve Template)");
            else
                $("#titleOrigenAprobarTemplate").html("Metodos de Validación (Aprobar Plantilla)");
            break;

        case "aprobarTemplateTensferencias":
            $("#tituloOrigen").html("<h1 id='titleOrigenAprobarTemplate'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenAprobarTemplate").html("Validation Methods (Approve Template)");
            else
                $("#titleOrigenAprobarTemplate").html("Metodos de Validación (Aprobar Plantilla)");
            break;

        case "aprobarTransferenciaFC":
            $("#tituloOrigen").html("<h1 id='titleOrigenAprobarTransferencia'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenAprobarTransferencia").html("Validation Methods (Transfers / Approve Transfers Joint Signatures)");
            else
                $("#titleOrigenAprobarTransferencia").html("Metodos de Validación (Transferencias / Aprobar Transferencias Firmas Conjuntas)");
            break;


        case "liberarTransferenciaFC":
            $("#tituloOrigen").html("<h1 id='titleOrigenLiberarTransferencia'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenLiberarTransferencia").html("Validation Methods (Transfers / Release Transfers Joint Signatures)");
            else
                $("#titleOrigenLiberarTransferencia").html("Metodos de Validación (Transferencias / Liberar Transferencias Firmas Conjuntas)");
            break;

        case "aprobarPagosTdcFC":
            $("#tituloOrigen").html("<h1 id='title_CreditCards_PaymentCard_Approve'></h1>");
            if(idioma=="_us_en")
                $("#title_CreditCards_PaymentCard").html("Validation Methods (Credit Cards / Approve Payments Joint Signatures)");
            else
                $("#title_CreditCards_PaymentCard").html("Metodos de Validación (Tarjetas de Crédito / Aprobar Pagos Firmas Conjuntas)");
            break;


        case "liberarPagosTdcFC":
            $("#tituloOrigen").html("<h1 id='title_CreditCards_PaymentCard_Release'></h1>");
            if(idioma=="_us_en")
                $("#title_CreditCards_PaymentCard_Release").html("Validation Methods (Credit Cards / Release Payments Joint Signatures)");
            else
                $("#title_CreditCards_PaymentCard_Release").html("Metodos de Validación (Tarjetas de Crédito / Liberar Transferencias Firmas Conjuntas)");
            break;

        case "transferenciaTercerosVBT":{
            $("#tituloOrigen").html("<h1 id='titleOrigenTransfersVBT'></h1>");
            if(idioma=="_us_en")
                $("#titleOrigenTransfersVBT").html("Validation Methods (TRANSFERS / To other Clients in VBT)");
            else
                $("#titleOrigenTransfersVBT").html("Metodos de Validación (Transferencias / A Terceros en VBT)");
            break;
        }
        case "desbloqueoTarjeta": {
            cargarIdiomaJSONData_sinc();
            $("#tituloOrigen").html("<h1 id='title_CreditCards_CardLock'>" + vbtol_props[idioma]["title_CreditCards_CardLock"] + "</h1>");
            break;
        }

        case "editarDesbloqueoTarjeta": {
            cargarIdiomaJSONData_sinc();
            $("#tituloOrigen").html("<h1 id='title_CreditCards_CardLock'>" + vbtol_props[idioma]["title_CreditCards_CardLock"] + "</h1>");
            break;
        }

        case "eliminarDesbloqueoTarjeta": {
            cargarIdiomaJSONData_sinc();
            $("#tituloOrigen").html("<h1 id='title_CreditCards_CardLock_Delete'>" + vbtol_props[idioma]["title_CreditCards_CardLock_Delete"] + "</h1>");
            break;
        }
        case "paymentCard": {
            cargarIdiomaJSONData_sinc();
            $("#tituloOrigen").html("<h1 id='title_CreditCards_PaymentCard'>" + vbtol_props[idioma]["title_CreditCards_PaymentCard"] + "</h1>");
            break;
        }


    }

    origenMetodosValidacion=origen;
    $("btnAceptar").addClass("oculto");
    $("btnPrev").addClass("oculto");
    seleccionarOpcion("METODOS_VALIDACION");
    guardarLogMetodosdeValidacionJSONData("27", "1","26", "Ingreso a la opción métodos de validación");
}



function cargarMetodos(){
    var url = urlCargarMetodos;
    var param={};

    sendServiceJSON_sinc(url,param,cargarMetodosSuccess,null,null);

}

function cargarMetodosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    var mensaje=result.mensaje;
    cantMetodos=0;

    $('input[name=metodoSeleccionado]:radio').each(function(){
        this.checked = false;
    });

    if(respuesta=="OK"){
       $("#TAGNumeroTelefonicoValor").html(result.telefono);
       $("#countryCodeVM").intlTelInput("setNumber", "+"+result.codigoPais);
       $("#TAGClaveConfirmacionSMS").removeClass("oculto");
       $("#TAGNumeroTelefonico").removeClass("oculto");

        cantMetodos++;
        $(".telefonoMetodos").each(function(){
            if(origenMetodosValidacion!="tarjetaCoordenadas"){
                $(this).removeClass("oculto");
            }else{
                $(this).removeClass("oculto");
                $(this).attr('checked', true);
                transferencia_MetodoValidar_Clave="sms";
            }
        });
    }else{
        $(".telefonoMetodos").each(function(){
            $(this).addClass("oculto");
        });
        $("#TAGClaveConfirmacionSMS").addClass("oculto");
        $("#TAGNumeroTelefonicoValor").html(respuesta);
        $("#TAGNumeroTelefonico").addClass("oculto");
    }
        if (mensaje!=null){
            if (mensaje!=""){

                if(mensaje.indexOf('ORA')==-1){
                    if(mensaje=="OK"){
                        cantMetodos++;
                        $("#TAGSerialTarjetaValor").html(result.serial);
                        $("#TAGClaveConfirmacionTarjeta").removeClass("oculto");
                        $("#TAGSerialTarjeta").removeClass("oculto");
                        $(".tarjetaMetodos").each(function(){
                            $(this).removeClass("oculto");
                        });
                    }else{

                        $(".tarjetaMetodos").each(function(){
                            $(this).addClass("oculto");
                        });


                            $("#TAGClaveConfirmacionTarjeta").addClass("oculto");
                            $("#TAGSerialTarjeta").addClass("oculto");

                            if(origenMetodosValidacion!="tarjetaCoordenadas"){
                                $("#TAGSerialTarjetaValor").html(mensaje);
                            }else{
                                $("#TAGSerialTarjetaValor").html("");
                            }

                    }
                } else{
                    $(".tarjetaMetodos").each(function(){
                        $(this).addClass("oculto");
                    });


                    $("#TAGClaveConfirmacionTarjeta").addClass("oculto");
                    $("#TAGSerialTarjeta").addClass("oculto");

                    if(idioma=="_us_en")
                        $("#TAGSerialTarjetaValor").html("<span id='TAGErrorORA'>An error has been occurred</span>");
                    else
                        $("#TAGSerialTarjetaValor").html("<span id='TAGErrorORA'>Error consultando la tarjeta de coordenadas</span>");


                }
            }
        }
}

function seleccionarMetodo(obejto,metodoValidacion){
    if (this.checked != false){
        transferencia_MetodoValidar_Clave=metodoValidacion;
        guardarLogMetodosdeValidacionJSONData("27", "1","26", "Se selecciono "+metodoValidacion+" como método de validación");
    } else{
        transferencia_MetodoValidar_Clave="";
    }

}

function cargarDatosMetodo(){
    switch(transferencia_MetodoValidar_Clave){
        case "email":

            break;
        case "TarjetaCoordenada":
            $("#coordenada_1").val("");
            $("#coordenada_2").val("");
            if($("#coordenada_1").hasClass("error_campo"))
                $("#coordenada_1").removeClass("error_campo");
            if($("#coordenada_2").hasClass("error_campo"))
                $("#coordenada_2").removeClass("error_campo");
            cargarTarjetaCoordenadas();
            break;
        case "sms":

            /*setTimeout(function() {
                $("#div_carga_espera").removeClass("oculto");
            }, 1000);*/
            GenerarClaveSMSJSONData();
            /*setTimeout(function() {
                $("#div_carga_espera").addClass("oculto");
            }, 1000);*/
            //$("#div_carga_espera").addClass("oculto");
            $("#pwdClaveConfirmSMS").val("");
            if($("#pwdClaveConfirmSMS").hasClass("error_campo"))
                $("#pwdClaveConfirmSMS").removeClass("error_campo");

            break;
        default:
            if(idioma=="_us_en")
                alert("You must select a method of validation!");
            else
                alert("Debe seleccionar un metodo de validación!");
    }


}

function showMetodo(){

    $("#div_tarjeta_coordenadas").addClass("oculto");
    $("#div_correo_clave_transf").addClass("oculto");
    $("#div_sms_clave").addClass("oculto");

        switch(transferencia_MetodoValidar_Clave){
            case "email":
                $("#div_correo_clave_transf").removeClass("oculto");
                break;
            case "TarjetaCoordenada":
                $("#div_tarjeta_coordenadas").removeClass("oculto");
                break;
            case "sms":
                $("#div_sms_clave").removeClass("oculto");
                break;
        }

}


function guardarLogMetodosdeValidacionJSONData(accion, empresa,tipoObjeto, descripcion){
    var url = urlLogMetodosdeValidacion;
    var param={};

    var jsonDesingBank=[];
    jsonDesingBank[0]= accion;
    jsonDesingBank[1]= empresa;
    jsonDesingBank[2]= tipoObjeto;
    jsonDesingBank[3]= descripcion;

    param.jsonDesingBank=JSON.stringify({"parametros":jsonDesingBank});

    //sendServiceJSON(url,param,"guardarLogMetodosdeValidacionSuccess",null,null);
    sendServiceJSON(url,param,null,null,null);
}

function guardarLogMetodosdeValidacionSuccess(originalRequest){

}

function regresarOperaciones(){
    pasoActual=0;

    $("#paso1").attr("style","display: none");
    $("#paso2").attr("style","display: none");
    $("#paso3").attr("style","display: none");
    $("#pasoNoMetodos").attr("style","display: none");
    $("#pasoNoMetodosActivacion").attr("style","display: none");
    switch(origenMetodosValidacion){
        case "transferenciasOtrosBancos":
            $("#summaryToOtherBank #resumenBotones_TOB").attr("style","display: block");
            seleccionarOpcionBack("TRANSFERENCIA_EXTERNA");
            break;
        case "parametrosPersonales":
            seleccionarOpcionBack("PARAMETROS_PERSONALES");
            break;
        case "cambiarClave":
            seleccionarOpcionBack("CAMBIAR_CLAVE");
            break;
        case "tarjetaCoordenadas":
            seleccionarOpcionMenu("home");
            break;
        case "aprobarTemplate":
            if(idTemplate=="OK"){
                //$("#Template_btn_transferencia").attr("style","display: ");
                $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Make a Transfer" class="botonGrande 0000000040_0">');
                $("#Template_btn_TOB_aprobar").attr("style","display:none ");
            }else{
                $("#btnMakeTransfers").html("");
            }

            seleccionarOpcionBack("DIRECTORIO");
            break;

        case "aprobarTemplateTensferencias":
            seleccionarOpcionBack("TRANSFERENCIA_EXTERNA");
            $("#tagiTemplate").click();
            break;

        case "aprobarTransferenciaFC":
            seleccionarOpcionBack("APROBAR_TRANSFERENCIA");
            //$("#tagiTemplate").click();
            break;

        case "liberarTransferenciaFC":
            seleccionarOpcionBack("LIBERAR_TRANSFERENCIA");
            //$("#tagiTemplate").click();
            break;

        case "aprobarPagosTdcFC":
            seleccionarOpcionBack("APROBAR_PAGO_TARJETAS");
            break;

        case "liberarPagosTdcFC":
            seleccionarOpcionBack("LIBERAR_PAGO_TARJETAS");
            break;

        case "transferenciaTercerosVBT":
            $("#btn_resumen_aceptar_TOC").attr("disabled",false);
            seleccionarOpcionBack("TRANSFERENCIA_INTERNA_TERCEROS");
            break;
        case "desbloqueoTarjeta": {
            seleccionarOpcionBack("BLOQUEO_TARJETA");
            break;
        }

        case "editarDesbloqueoTarjeta": {
            seleccionarOpcionBack("BLOQUEO_TARJETA");
            break;
        }
        case "eliminarDesbloqueoTarjeta": {
            seleccionarOpcionBack("BLOQUEO_TARJETA");
            break;
        }
        case "paymentCard": {
            seleccionarOpcionBack("PAGO_TARJETA");
            break;
        }
    }
}

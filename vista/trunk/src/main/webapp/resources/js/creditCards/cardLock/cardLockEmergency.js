var urlConsultarEstatus_TDC_CLE="CreditCards_cargarEstatusTDCCLE.action";
var urlCargar_TDC_CLE="CreditCards_cargarTarjetasCLE.action";
var urlCambiarEStatus_TDC_CLE="CreditCards_cambiarEstatusCLE.action";

var idioma = "";
var noInfo="";
var valores="";

if (idioma == "_ve_es") {
    var config = {
        '.chosen-select'           : {},
        '.chosen-select-deselect'  : {allow_single_deselect:true},
        '.chosen-select-no-single' : {disable_search_threshold:10},
        '.search_contains' : {disable_search_threshold:10},
        '.chosen-select-no-results': {no_results_text:'No se encontraron resultados'},
        '.chosen-select-width'     : {width:"95%"}
    }
} else {
    var config = {
        '.chosen-select'           : {},
        '.chosen-select-deselect'  : {allow_single_deselect:true},
        '.chosen-select-no-single' : {disable_search_threshold:10},
        '.chosen-select-no-results': {no_results_text:'Nothing found!'},
        '.chosen-select-width'     : {width:"95%"}
    }
}

$(document).ready(function(){

    $("#crear_bloqueo").on( "click", function() {
        var motivoscl = $("#motivosBloqueoCLE").val();
        var motivosclTexto = $("#motivosBloqueoCLE option:selected").text();
        var tarjetas = $("#numero_cuenta_TDC_CLE option:selected").val();
        var mensaje = "";
        if(motivoscl != '-2' && tarjetas != '-2'){
            if (idioma == "_ve_es") {
                mensaje = "¿Est\u00e1 seguro que desea bloquear la tarjeta de crédito?. Al aceptar se inactivan todos los períodos de activación programados.";
            }else{
                mensaje = "Are you sure that you want to block the credit card?. Once the credit card is blocked all the set activation periods will be deactivated.";
            }
            if (confirm(mensaje)) {
                cambiarEstatusTDCCLEJSONData(tarjetas, motivoscl, 'BLQ',motivosclTexto);
            }
        }else{
            if (idioma == "_ve_es")
                alert('Debe completar los campos requeridos');
            else
                alert('You must complete the required fields');

        }

    });

    $("#solicita_activar").on( "click", function() {
        var tarjetas = $("#numero_cuenta_TDC_CLE option:selected").val();
        var mensaje = "";
        if(tarjetas != '-2'){
            if (idioma == "_ve_es") {
                mensaje = "¿Est\u00e1 seguro que desea desbloquear la tarjeta ?";
            }else{
                mensaje = "Are you sure you want to unblock the card ?";
            }
            if (confirm(mensaje)) {
                solicitudAnulJSONData(tarjetas, '', 'ANL');
            }
        }else{
            if (idioma == "_ve_es")
                alert('Debe completar los campos requeridos');
            else
                alert('You must complete the required fields');


        }

    });


});


function CardLockETDCInfoPaginaJSONData(){
    var url = urlCargar_TDC_CLE;
    var param={};

    $("#fec_desde_CLE").val("");
    $("#fec_hasta_CLE").val("");
    $("#div_tabla_consulta_reglas_activas_TDC_CLE").empty();
    $("#div_tabla_consulta_historial_TDC_CLE").empty();
    $("#div_creditCard_CLE_Edit").attr("style", "display: none");
//    $("#div_creditCard_CLE_Edit").addClass("oculto");
    $("#set_tabla_consulta_reglas_activas_TDC_CLE").addClass("oculto");
    $("#set_tabla_consulta_historial_TDC_CLE").addClass("oculto");
    $("#div_datos_regla_mensaje_TDC_CLE").addClass("oculto");
    $("#div_datos_regla_TDC_CLE").removeClass("oculto");
    $("#numero_cuenta_TDC_CLE_edit").text("");
    $("#fec_desde_CLE_Edit").val("");
    $("#fec_hasta_CLE_Edit").val("");
    $("#tag_status_CLE_Edit").text("");
    //$('#tabla_consulta_TDC_Pagos_movimientos').dataTable().fnClearTable();

//    $("#div_tabla_consulta_reglas_corrientes_TDC_CLE").empty();

    if(!$("#div_anularBloqueo").hasClass("oculto"))
        $("#div_anularBloqueo").addClass("oculto");

    if($("#div_noRespuesta_datos_tdc").is(":visible"))
        $("#div_noRespuesta_datos_tdc").hide();

    $("#TAG_INFO_BLOQUEAR").addClass("oculto");

    sendServiceJSON(url,param,CardLockETDCInfoSuccess,null,null);
}


function CardLockETDCInfoSuccess(originalRequest){
    var result = originalRequest;
    var tarjetasCLE = result.tarjetasCL;
    var codigo = result.codigo;
    idioma = result.idioma;


    /*console.log("cardLockEmergency.js - idioma - " + idioma); */

    if(codigo=="0" && tarjetasCLE!=null){
        $("#alertaSeguridadTDC #pantalla").val("div_creditCard_CLE");
        $("#div_creditCard_CLE_alertaSeguridad").attr("style", "display: ");
        $("#div_creditCard_CLE").attr("style", "display: none");
        $("#div_noInfo_CLE_creditCard").attr("style", "display: none");

        if(idioma=="_us_en")
            cargar_selectPersonal("numero_cuenta_TDC_CLE", tarjetasCLE,"Select","-2");
        else
            cargar_selectPersonal("numero_cuenta_TDC_CLE", tarjetasCLE,"Seleccione","-2");

        $("#numero_cuenta_TDC_CLE").val("-2");
        $('#numero_cuenta_TDC_CLE').trigger("chosen:updated");
    }else{
        $("#div_creditCard_CLE_alertaSeguridad").attr("style", "display: none");
        $("#div_creditCard_CLE").attr("style", "display: none");
        $("#div_noInfo_CLE_creditCard").attr("style", "display: ");
    }
    $("#numero_cuenta_TDC_CLE").chosen({ search_contains: true });
}

function cargarEstatusCLE(value){

    if(value!="-2") {
        consultarEstatusTDCCLEJSONData(value);
        $("#div_carga_espera").removeClass("oculto");
    }else{
        if($("#div_respuesta_datos_tdc").is(":visible"))
            $("#div_respuesta_datos_tdc").hide();
        if($("#div_noRespuesta_datos_tdc").is(":visible"))
            $("#div_noRespuesta_datos_tdc").hide();

    }
}

function consultarEstatusTDCCLEJSONData(value){
    var url = urlConsultarEstatus_TDC_CLE;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = value.split("_")[0];
    jsonCreditCards[1] = value;
    jsonCreditCards[2] = value.split("_")[1];

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,consultarEstatusCLESuccess,null,null);
}


function consultarEstatusCLESuccess(originalRequest){
    var result = originalRequest;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    var estatusTarjeta = result.estatusTarjeta;
    var motivoscle  = result.motivosCLE;
    var fechaBloq = result.fechaBloqueo;
    var motivoBloq = result.motivoBloqueo;
    var permiteReactivar =  result.permiteReactivar;


    if(estatusTarjeta == "B" || estatusTarjeta == "A"){
        if(estatusTarjeta == "B"){
            if($("#btnBloqueo").hasClass("oculto"))
                $("#btnBloqueo").removeClass("oculto");

            if(!$("#btnActivacion").hasClass("oculto"))
                $("#btnActivacion").addClass("oculto");

            if($("#motivosCLE").hasClass("oculto"))
                $("#motivosCLE").removeClass("oculto");

            if(!$("#div_anularBloqueo").hasClass("oculto"))
                $("#div_anularBloqueo").addClass("oculto");

            if(idioma=="_us_en")
                cargar_selectPersonal("motivosBloqueoCLE", motivoscle,"Select","-2");
            else
                cargar_selectPersonal("motivosBloqueoCLE", motivoscle,"Seleccione","-2");

        }else if(estatusTarjeta == "A"){
            /*aqui va */

            /* div_anularBloqueo */

            if ($("#div_anularBloqueo").hasClass("oculto"))
                $("#div_anularBloqueo").removeClass("oculto");



            /* noInfo_TDC5_motivo  */
            $("#noInfo_TDC5_motivo").text(motivoBloq + " - " + fechaBloq);

            if(permiteReactivar == 'S') {
                if($("#btnActivacion").hasClass("oculto"))
                    $("#btnActivacion").removeClass("oculto");

                if($("#noInfo_TDC5_2").hasClass("oculto"))
                    $("#noInfo_TDC5_2").removeClass("oculto");
            }else{
                if(!$("#btnActivacion").hasClass("oculto"))
                    $("#btnActivacion").addClass("oculto");

                if(!$("#noInfo_TDC5_2").hasClass("oculto"))
                    $("#noInfo_TDC5_2").addClass("oculto");

            }

            if(!$("#btnBloqueo").hasClass("oculto"))
                $("#btnBloqueo").addClass("oculto");
            if(!$("#motivosCLE").hasClass("oculto"))
                $("#motivosCLE").addClass("oculto");
        }

        if($("#div_respuesta_datos_tdc").is(":hidden"))
            $("#div_respuesta_datos_tdc").show( "fast" );

        if($("#div_noRespuesta_datos_tdc").is(":visible"))
            $("#div_noRespuesta_datos_tdc").hide( "fast" );
    }else{
        if(!$("#div_anularBloqueo").hasClass("oculto"))
            $("#div_anularBloqueo").addClass("oculto");

        if($("#div_respuesta_datos_tdc").is(":visible"))
            $("#div_respuesta_datos_tdc").hide( "fast" );

        if($("#div_noRespuesta_datos_tdc").is(":hidden"))
            $("#div_noRespuesta_datos_tdc").show( "fast" );

        if(!$("#btnActivacion").hasClass("oculto"))
            $("#btnActivacion").addClass("oculto");

        if(!$("#motivosCLE").hasClass("oculto"))
            $("#motivosCLE").addClass("oculto");

        if(!$("#btnBloqueo").hasClass("oculto"))
            $("#btnBloqueo").addClass("oculto");
    }

    $("#div_carga_espera").addClass("oculto");
}

function cambiarEstatusTDCCLEJSONData(tarjeta,motivos,accion,motivosclTexto){
    var url = urlCambiarEStatus_TDC_CLE;
    var param={};
    var jsonCreditCards=[];

    $("#div_carga_espera").removeClass("oculto");

    jsonCreditCards[0] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[0]: "";
    jsonCreditCards[1] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[1]: "";
    jsonCreditCards[2] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[2]: "";
    jsonCreditCards[3] = (!isEmpty(tarjeta) ) ? motivos.split("|")[0]: "";
    jsonCreditCards[4] = (!isEmpty(tarjeta) ) ? motivos.split("|")[1]: "";
    jsonCreditCards[5] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[4]: "";
    jsonCreditCards[6] = (!isEmpty(accion)  ) ? accion: "";
    jsonCreditCards[7] = (!isEmpty(motivosclTexto)  ) ? motivosclTexto: "";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,cambiarEstatusCLESuccess,null,null);
}

function cambiarEstatusCLESuccess(originalRequest){
    var result = originalRequest;
    var codigo = result.codigo;
    var mensaje = result.respuesta;
    var estatusTarjeta = result.estatusTarjeta;
    var motivoscle  = result.motivosCLE;


    $("#div_carga_espera").addClass("oculto");
    idioma = result.idioma;

    if(mensaje=="NO OK"){
        var mensaje="";
        $("#mensaje_error").empty();
        if(idioma=="_us_en"){
            mensaje="Lock service is currently not available, please contact your Financial Advisor or Account Executive.";
        }else{
            mensaje="El servicio de bloqueo no se encuentra disponible, por favor contacte a su Asesor o Ejecutivo.";
        }
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else {
        if(mensaje=="ERROR_EMAIL") {
            if(idioma=="_us_en")
                popupAlert("<span id='tag_popup_add'>;Lock instructions has been successfully received but we couldn't send notifications emails.</span>", "Accept");
            else
                popupAlert("<span id='tag_popup_add'>El bloqueo ha sido cargado exitosamente pero no hemos podido enviar los correos electrónicos.</span>", "Aceptar");
        }else{
            cargarEstatusCLE($("#numero_cuenta_TDC_CLE option:selected").val());

            if(idioma=="_us_en")
                popupAlert("<span id='tag_popup_add_1'>Your card has been blocked.</span>", "Accept");
            else
                popupAlert("<span id='tag_popup_add_1'>Su tarjeta ha sido bloqueada. </span>", "Aceptar");
        }

    }


}

function solicitudAnulJSONData(tarjeta,motivos,accion,motivosclTexto){
    var url = urlCambiarEStatus_TDC_CLE;
    var param={};
    var jsonCreditCards=[];

    $("#div_carga_espera").removeClass("oculto");

    jsonCreditCards[0] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[0]: "";
    jsonCreditCards[1] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[1]: "";
    jsonCreditCards[2] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[2]: "";
    jsonCreditCards[3] = (!isEmpty(tarjeta) ) ? motivos.split("|")[0]: "";
    jsonCreditCards[4] = (!isEmpty(tarjeta) ) ? motivos.split("|")[1]: "";
    jsonCreditCards[5] = (!isEmpty(tarjeta) ) ? tarjeta.split("_")[4]: "";
    jsonCreditCards[6] = (!isEmpty(accion)  ) ? accion: "";
    jsonCreditCards[7] = (!isEmpty(motivosclTexto)  ) ? motivosclTexto: "";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,solicitudAnulacionSuccess,null,null);
}

function solicitudAnulacionSuccess(originalRequest){
    var result = originalRequest;
    var codigo = result.codigo;
    var mensaje = result.respuesta;
    var estatusTarjeta = result.estatusTarjeta;
    var motivoscle  = result.motivosCLE;


    $("#div_carga_espera").addClass("oculto");
    idioma = result.idioma;

    if(mensaje=="NO OK"){
        var mensaje="";
        $("#mensaje_error").empty();
        if(idioma=="_us_en"){
            mensaje="Lock service is currently not available, please contact your Financial Advisor or Account Executive.";
        }else{
            mensaje="El servicio de bloqueo no se encuentra disponible, por favor contacte a su Asesor o Ejecutivo.";
        }
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else {
        if(mensaje=="ERROR_EMAIL") {
            if(idioma=="_us_en")
                popupAlert("<span id='tag_popup_mail_1'>Lock instructions has been successfully received but we couldn't send notifications emails.</span>", "Accept");
            else
                popupAlert("<span id='tag_popup_mail_1'>El bloqueo ha sido cargado exitosamente pero no hemos podido enviar los correos electrónicos.</span>", "Aceptar");
        }else{
            cargarEstatusCLE($("#numero_cuenta_TDC_CLE option:selected").val());

            if(idioma=="_us_en")
                popupAlert("<span id='tag_popup_add_3'>The activation request has been sent successfully.</span>", "Accept");
            else
                popupAlert("<span id='tag_popup_add_3'>Se ha enviado la solicitud de activación con exito. </span>", "Aceptar");
        }

    }

}

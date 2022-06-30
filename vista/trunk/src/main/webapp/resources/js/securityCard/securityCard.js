var urlValoresGeneracion_SecurityCard="SecurityCard_cargarValoresGeneracion.action";
var urlValoresActivacion_SecurityCard="SecurityCard_cargarValoresActivacion.action";
var urlActivar_SecurityCard="SecurityCard_activarTarjeta.action";

$(document).ready(function(){
     //Generar PDF de Tarjeta de Coordenadas
    $("#TarjetaCoordActivacion_BTN_Activar").hide();

    $("#TarjetaCoordenadas_BTN_Generar").click(function(){

        if($("#TarjetaCoordenadas_status").val()=="AC" ){
            confirma = confirm($("#TarjetaCoordenadas_mensaje").val());
        }  else {
            confirma = true;
        }

        if (confirma)   {
            mostrarSeguridad="N";
            crearPDF_TarjetaCoordenadas();
            mainValidationMethods("tarjetaCoordenadas");
        }

    });


    $("#TarjetaCoordActivacion_BTN_Activar").click(function(){

        activar_TarjetaCoordenadasJSONData();
    });
});

// ACTIVAR
function activar_TarjetaCoordenadasJSONData(){

    var url = urlActivar_SecurityCard;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    sendServiceJSON_sinc(url,param,activar_TarjetaCoordenadasSuccess,null,null);
}
function activar_TarjetaCoordenadasSuccess(originalRequest){
    var result = originalRequest;

    idioma = result.idioma;
    var codigo = result.codigo;
    var mensaje = result.mensaje;

    $("#mensaje_error").empty();
    if(codigo=="0"){
       // $("#TarjetaCoordActivacion_BTN_Activar").hide();

       // $("#TarjetaCoordActivacion_msg").text(mensaje);

    }else{
       // $("#TarjetaCoordActivacion_msg").text(mensaje);

    }
   alert(mensaje);
}
// CARGAR PAGINA DE GENERACION
function cargarGeneracionTarjetaCoordenadasJSONData(){

        var url = urlValoresGeneracion_SecurityCard;
        var param={};
        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");

    if($("#TarjetaCoordenadas_status").val()=="AS"){
        $("#TarjetaCoordenadas_activar").show();
    }  else  {
        $("#TarjetaCoordenadas_activar").hide();
    }


        sendServiceJSON(url,param,cargarGeneracion_TarjetaCoordenadasSuccess,null,null);
}
function cargarGeneracion_TarjetaCoordenadasSuccess(originalRequest){

    var result = originalRequest;
    var cirif = result.cirif;
    var usuario = result.usuario;
    var serial = result.serial;
    var status = result.status;

    idioma = result.idioma;
    var codigo = result.codigo;
    var mensaje = result.mensaje;

    if(codigo=="0"){
        $("#TarjetaCoordenadas_cirif").text(cirif);
        $("#TarjetaCoordenadas_usuario").text(usuario);

        //$("#TarjetaCoordenadas_serial").text(serial);
        $("#TarjetaCoordenadas_status").val(status);
        $("#TarjetaCoordenadas_mensaje").val(mensaje);



    }
}

// CARGAR PAGINA DE ACTIVACION
function cargarActivacionTarjetaCoordenadasJSONData(){

    var url = urlValoresActivacion_SecurityCard;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    sendServiceJSON_sinc(url,param,cargarActivacion_TarjetaCoordenadasSuccess,null,null);
}
function cargarActivacion_TarjetaCoordenadasSuccess(originalRequest){

    var result = originalRequest;
    var cirif = result.cirif;
    var usuario = result.usuario;
    var serial = result.serial;

    idioma = result.idioma;
    var codigo = result.codigo;

    var mensaje = result.mensaje;

    $("#TarjetaCoordActivacion_msg").empty();

        $("#TarjetaCoordActivacion_cirif").text(cirif);
        $("#TarjetaCoordActivacion_usuario").text(usuario);
        $("#TarjetaCoordActivacion_serial").text(serial);


        if(codigo ==0){
           $("#TAGSerialTarjetaMTD").html("Serial Nº "+serial);
              //$("#TarjetaCoordActivacion_BTN_Activar").show();
           if(origenMetodosValidacion!="tarjetaCoordenadas"){
                mainValidationMethods("tarjetaCoordenadas");
           }
        }else{

            if(mensaje!=null)
            $("#TarjetaCoordActivacion_msg").text(mensaje);
        }

}


function crearPDF_TarjetaCoordenadas(){

    var urlPDFSecurityCard="generatePdfSecurityCard.action";
    var confirma = false;


        $("#TarjetaCoordenadas_activar").show();
        window.open(urlPDFSecurityCard,'PDF','');


    //window.location.href = urlPDFSecurityCard;
    //return false;



}





function validarActivacionTarjetaCoordenadasJSONData(){
    ;
    var url = urlValoresActivacion_SecurityCard;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    sendServiceJSON_sinc(url,param,validarActivacion_TarjetaCoordenadasSuccess,null,null);
}
function validarActivacion_TarjetaCoordenadasSuccess(originalRequest){

    var result = originalRequest;
    var cirif = result.cirif;
    var usuario = result.usuario;
    var serial = result.serial;

    idioma = result.idioma;
    var codigo = result.codigo;

    var mensaje = result.mensaje;

    showAct="S";
    if(codigo!=0){
        showAct="N";
       alert(mensaje);

    }

}








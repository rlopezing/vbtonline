var urlBackOfficeCargar="BackOffice_cargar.action";

$(document).ready(function(){

    $('#enviar').click(function(){
        var valido=true;

        if($("#id").get(0).value.isEmpty()){
            $("#mensaje").html("Teclee id");
            $("#mensaje").removeClass("blue");
            $("#mensaje").hasClass("red");
            valido=false;
        }
        if(valido)
            BackOfficeCargarJSONData();

    });


    $("#limpiarPagosTarjetaBO").click(function(){
        $("#carteraFiltroTarjetaPagossBO").val("");
        $("#div_result_pagos_bo").addClass("oculto");
        $("#numero_cuenta_TDC_PAGOS_BO").html("");
        //$("#tag_cta_bloqueo_bo").text("");
        $("#fec_desde_Pagos_bo").val("");
        $("#fec_hasta_Pagos_bo").val("");
//        $("#tag_cliente_bloqueo_bo").text("");
//        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
//        $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");
//        $("#carteraExacto_TDC_Pagos").attr("checked",false);
//        $("#div_creditCard_CL_Edit_BO").fadeOut("fast");

    });



});

function BackOfficeCargarJSONData(){
    var url = urlBackOfficeCargar;
    var param={};
    var jsonBackOffice=[];
    var BackOffice={};

    BackOffice.id=$("#id").get(0).value;

    jsonBackOffice[0]= BackOffice;

    param.jsonBackOffice=JSON.stringify({"backOffices":jsonBackOffice});

    sendServiceJSON(url,param,BackOfficeCargarSuccess,null,null);
}

function BackOfficeCargarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    $("#mensaje").html(result.mensaje);
    $("#mensaje").removeClass("red");
    $("#mensaje").hasClass("red");

}







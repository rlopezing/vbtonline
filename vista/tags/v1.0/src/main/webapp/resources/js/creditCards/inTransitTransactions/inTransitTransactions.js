var urlConsultarSaldos_TDC_ITT="CreditCards_cargarMovimientosTDCITT.action";
var urlCargar_TDC_ITT="CreditCards_cargarTarjetasITT.action";

var idioma = "";
var noInfo="";


var bandera="";
$(document).ready(function(){





});

function cargarSaldosITT(value){
   if(value!="-2") {
       consultarSaldosTDCITTJSONData();
   }else{
      $("#div_tabla_consulta_TDC_ITT").empty();
   }


};

function InTransitTransactionsTDCInfoPaginaJSONData(){
    var url = urlCargar_TDC_ITT;
    var param={};

    $("#div_tabla_consulta_TDC_ITT").empty();



//    $("#alertaSeguridadTDC").fadeIn("fast");
    //$("#marco_trabajo").css("height","950px");
//    $("#div_creditCard_ITT_alertaSeguridad #alertaSeguridadTDC a ").attr("title","esto es una prueba ITT");

    sendServiceJSON(url,param,InTransitTransactionsTDCInfoSuccess,null,null);
}


function InTransitTransactionsTDCInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var tarjetasITT = result.tarjetasITT;
    var codigo = result.codigo;
    idioma = result.idioma;

    if(codigo=="0" && tarjetasITT!=null){
        $("#alertaSeguridadTDC #pantalla").val("div_creditCard_ITT");
        $("#div_creditCard_ITT_alertaSeguridad").attr("style", "display: ");
        $("#div_creditCard_ITT").attr("style", "display: none");
        $("#div_noInfo_ITT_creditCard").attr("style", "display: none");
        if(idioma=="_us_en")
          cargar_selectPersonal("numero_cuenta_TDC_ITT", tarjetasITT,"Select","-2");
        else
          cargar_selectPersonal("numero_cuenta_TDC_ITT", tarjetasITT,"Seleccione","-2");
    }else{
        $("#div_creditCard_ITT_alertaSeguridad").attr("style", "display: none");
        $("#div_creditCard_ITT").attr("style", "display: none");
        $("#div_noInfo_ITT_creditCard").attr("style", "display: ");
    }




}

function consultarSaldosTDCITTJSONData(){
    var url = urlConsultarSaldos_TDC_ITT;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_ITT").get(0).value;

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,consultarBloqueosITTSuccess,null,null);
}


function consultarBloqueosITTSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    idioma=result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    if(codigo=="0"){
        crearTabla('div_tabla_consulta_TDC_ITT','tabla_consulta_TDC_ITT',columnas,datos);
//
        var oTable = $('#tabla_consulta_TDC_ITT').dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                {  "sClass": "left" },
                { "sClass": "right" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}

function print_MOVIMIENTOS_FACTURAR(){

    var miValue = $("#numero_cuenta_TDC_ITT" ).val();
    $('#numero_cuenta_TDC_ITT_select').html($('#numero_cuenta_TDC_ITT option:selected').text());
    $("#numero_cuenta_TDC_ITT option[value='"+miValue+"']").attr("selected",true);
    printPageElement('div_MOVIMIENTOS_FACTURAR');  //Print EDO CUENTA


}
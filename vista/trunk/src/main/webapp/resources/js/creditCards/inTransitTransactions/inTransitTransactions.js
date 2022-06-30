var urlConsultarSaldos_TDC_ITT="CreditCards_cargarMovimientosTDCITT.action";
var urlCargar_TDC_ITT="CreditCards_cargarTarjetasITT.action";

var idioma = "";
var noInfo="";


var bandera="";
$(document).ready(function(){




});

function cargarSaldosITT(value){

    $("#valor_monto_facturar_tdc").text("");
    $("#valor_nombre_tdc").text("");
    $("#valor_limite_credito_tdc").text("");
    $("#valor_nro_telefono_tdc").text("");
    $("#valor_email_tdc").text("");
    $("#valor_tipo_pago_aut_tdc").text("");
    $("#valor_direccion_tdc").text("");
    $("#valor_disponible_tdc").text("");
    $("#valor_limite_credito_tdc").text("");
    $("#valor_fecha_ult_pago_tdc").text("");
    $("#tabla_consulta_TDC_ITT").empty();

   if(value!="-2") {
       $("#div_carga_espera").removeClass("oculto");
       consultarSaldosTDCITTJSONData();
   }
   /* else{
      $("#div_tabla_consulta_TDC_ITT").empty();
       //$("#div_creditCard_ITTDetalle").fadeOut("fast");
   }  */


};

function InTransitTransactionsTDCInfoPaginaJSONData(){
    var url = urlCargar_TDC_ITT;
    var param={};

    $("#div_tabla_consulta_TDC_ITT").empty();

    $("#TAG_INFO_FACTURAR").addClass("oculto");

    $("#valor_monto_facturar_tdc").text("");
    $("#valor_nombre_tdc").text("");
    $("#valor_limite_credito_tdc").text("");
    $("#valor_nro_telefono_tdc").text("");
    $("#valor_email_tdc").text("");
    $("#valor_direccion_tdc").text("");
    $("#valor_disponible_tdc").text("");
    $("#valor_limite_credito_tdc").text("");
    $("#valor_fecha_ult_pago_tdc").text("");


    $("#alertaSeguridadTDC #pantalla").val("div_creditCard_ITT");
    $("#div_creditCard_ITT_alertaSeguridad").attr("style", "display: ");
    $("#div_creditCard_ITT").attr("style", "display: none");
    $("#div_noInfo_ITT_creditCard").attr("style", "display: none");


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
        //TODO:MAPEAR CUANDO NO HAY DATA
        $("#div_noInfo_ITT_creditCard").attr("style", "display: ");
    }




}

function consultarSaldosTDCITTJSONData(){
    var url = urlConsultarSaldos_TDC_ITT;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_ITT").get(0).value.split("|")[0];

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
    var detalles=result.detalles;


    idioma=result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    if(codigo=="0"){


        $("#valor_monto_facturar_tdc").text(detalles[0]);
        $("#valor_nombre_tdc").text(detalles[2]);
        $("#valor_limite_credito_tdc").text(detalles[3]);
        $("#valor_nro_telefono_tdc").text(detalles[5]);
        $("#valor_email_tdc").text(detalles[6]);
        $("#valor_direccion_tdc").text(detalles[9]);
        $("#valor_disponible_tdc").text(detalles[10]);

        cargarIdiomaJSONData_sinc();
        //$("#mensaje_error").html("<span id='tagTipoPagAut"+detalles[7]+"'>" + vbtol_props[idioma]["tagTipoPagAut"+detalles[7]] + "</span>");
        $("#valor_tipo_pago_aut_tdc").html("<span id='tagTipoPagAut"+detalles[7]+"'>" + vbtol_props[idioma]["tagTipoPagAut"+detalles[7]] + "</span>");

        $("#valor_fecha_ult_pago_tdc").text(detalles[11]);
        $("#valor_dias_mora_tdc").text(detalles[14]);
        $("#valor_monto_ult_fact_tdc").text(detalles[13]);

      //$("#div_creditCard_ITTDetalle").fadeIn("slow");
      //   crearTabla('div_tabla_consulta_TDC_ITT','tabla_consulta_TDC_ITT',columnas,datos);
        crearTablaV2('tabla_consulta_TDC_ITT',columnas,datos,"",true);
//
/*        oTableIntransi = $('#tabla_consulta_TDC_ITT').dataTable({
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
        });*/
        oTableIntransi = $('#tabla_consulta_TDC_ITT').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
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
    $("#div_carga_espera").addClass("oculto");

}

function print_MOVIMIENTOS_FACTURAR(){

    var miValue = $("#numero_cuenta_TDC_ITT" ).val();
    $('#numero_cuenta_TDC_ITT_select').html($('#numero_cuenta_TDC_ITT option:selected').text());
    $("#numero_cuenta_TDC_ITT option[value='"+miValue+"']").attr("selected",true);
    // printPageElement('div_MOVIMIENTOS_FACTURAR');  //Print EDO CUENTA

    $("#div_creditCard_ITT").printThis({
        importCSS: true,            // import parent page css
        importStyle: true,         // import style tags
        printContainer: true,
    });


}
//var urlCargarCuentasPago2="Transfers_cargarCuentasPagos.action";
var urlCargarHistoricoPago="CreditCards_cargarHistoricoPagosTDC.action";
//var urlGuardarPagoTDC="CreditCards_guardarPagoTDC.action";
var urlCargar_TDC_Pagos2="CreditCards_cargarTdcPagos.action";

var opcBusquedaPagoTDC2="OK";

$(document).ready(function() {

    $("#numero_cuenta_TDC_Pago2").chosen({ search_contains: true });


    $("#fec_desde_Pago").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        defaultDate: 0,
        beforeShowDay: editDays,
        changeYear: true   ,
        onClose: function (selectedDate) {
            $("#fec_hasta_Pago").datepicker("option", "minDate", selectedDate);
        }

    });


    $("#fec_hasta_Pago").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        beforeShowDay: editDays,
        changeYear: true
    });


    $("#clear_pagos2").click(function(){
        $("#numero_cuenta_TDC_Pago2").val("-2");
        $("#numero_cuenta_TDC_Pago2").trigger("chosen:updated");
        $("#fec_desde_Pago").val("");
        $("#fec_hasta_Pago").val("");
        $("#tag_cliente_bloqueo_pago2").text("");
        //$("#pay_card").fadeOut("fast");
        $("#fec_desde_Pago").val(fechaMesAnterior);
        $("#fec_hasta_Pago").val(fechaHoy);
/*        $('#tabla_consulta_historial_TDC_Pagos').dataTable().fnClearTable();
        $('#tabla_consulta_TDC_Pagos_movimientos').dataTable().fnClearTable();     */
        $('#tabla_consulta_historial_TDC_Pagos').empty();
        // $('#tabla_consulta_TDC_Pagos_movimientos').empty();

    });


    $("#consultar_pagos").click(function(){
        var mensaje="";
        $("#filtroPagosTDC .requeridoConsultarPagos").each(function(){
            if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field - "+ this.title+"<br>";
                else
                    mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";

                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });

/*        if($("#numero_cuenta_TDC_Pago2").val()=="-2"){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fec_desde_CL").addClass("error_campo");
        }*/


        if(!isDate($( "#fec_desde_Pago").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fec_desde_CL").addClass("error_campo");
        }else if(!isDate($( "#fec_hasta_Pago").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"To date is not correct";
            else
                mensaje=mensaje+"La fecha Hasta no es correcta";
            $("#fec_hasta_Pago").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fec_desde_Pago").val().split("/")[1]
                +"/"+$("#fec_desde_Pago").val().split("/")[0]
                +"/"+$("#fec_desde_Pago").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fec_hasta_Pago").val().split("/")[1]
                +"/"+$("#fec_hasta_Pago").val().split("/")[0]
                +"/"+$("#fec_hasta_Pago").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The date range is not allowed. Remember: The From and To dates should not be the same. From date must be less than the To date."+"<br>";
                else
                    mensaje=mensaje+"El rango de fecha no es permitido. Recuerde: La fecha Desde y Hasta no deben ser iguales. La fecha Desde debe ser menor a la fecha Hasta."+"<br>";
                $("#fec_desde_Pago").addClass("error_campo");
                $("#fec_hasta_Pago").addClass("error_campo");

            }else{
                $("#fec_desde_Pago").removeClass("error_campo");
                $("#fec_hasta_Pago").removeClass("error_campo");
            }
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            buscarHistoricoPagos();
            //cargarCuentasPagosJSONData();


        }

    });

/*    var oTableHistorialPagosTDC2 = $('#tabla_consulta_historial_TDC_Pagos').dataTable({
        "iDisplayLength": 7,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "right" },
            { "sClass": "center" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
/*    var oTableHistorialPagosTDC2 = $('#tabla_consulta_historial_TDC_Pagos').dataTable({
        "iDisplayLength": 7,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "right" },
            { "sClass": "center" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/

});


function cargarPagoTarjetasPaginaJSONData2(){
    var url = urlCargar_TDC_Pagos2;
    var param={};

    sendServiceJSON(url,param,cargarPagoTarjetasSuccess2,null,null);
}


function cargarPagoTarjetasSuccess2(originalRequest){
    var result = originalRequest;
    var tarjetasCL = result.tarjetasCL;
    var codigo = result.codigo;
    idioma = result.idioma;

    opcBusquedaPagoTDC2="OK"
    $("#fec_desde_Pago").val(fechaMesAnterior);
    $("#fec_hasta_Pago").val(fechaHoy);

    $('#tdcpagos_TAGLimiteCredito_datos').html("");
    $('#tdcpagos_TAGCreditoDisp_datos').html("");
    $('#tdcpagos_TAGFechaFact_datos').html("");
    $('#tdcpagos_TAGFechaPagAntes_datos').html("");
    $('#tdcpagos_TAGSaldoActual_datos').html("");
    $('#tdcpagos_TAGPagoTotal_datos').html("");
    $('#tdcpagos_TAGPagoMin_datos').html("");

    // $('#tabla_consulta_historial_TDC_Pagos').dataTable().fnClearTable();

    if(codigo=="0" && tarjetasCL!=null){

        if(idioma=="_us_en"){
            cargar_selectPersonal("numero_cuenta_TDC_Pago2", tarjetasCL,"Select","-2");

        }else{
            cargar_selectPersonal("numero_cuenta_TDC_Pago2", tarjetasCL,"Seleccione","-2");

        }
        $("#numero_cuenta_TDC_Pago2").val("-2");
        $('#numero_cuenta_TDC_Pago2').trigger("chosen:updated");

    }

    $("#numero_cuenta_TDC_Pago2").chosen({ search_contains: true });
    $("#pay_card").fadeOut("fast");
    $("#tag_cliente_bloqueo_pago2").text("");
    $("#numero_cuenta_TDC_Pago2").val("-2");
}


function cargarHistorialPagos(value){
    opcBusquedaPagoTDC2="OK";
    if(value!="-2") {
        $("#otroMontoPagoTDCTR").addClass("oculto");
        $("#tag_cliente_bloqueo_pago2").text($("#numero_cuenta_TDC_Pago2").val().split("_")[3]);
        $("#consultar_pagos").click();

    }else{
        $("#clear_pagos2").click();
        $("#pay_card").fadeOut("fast");
    }
    opcBusquedaPagoTDC2="NO OK";
}





//function cargarCuentasPagosJSONData(){
//    var url = urlCargarCuentasPago2;
//    var param={};
//    var jsonTransfers=[];
//
//    jsonTransfers[0] = $("#numero_cuenta_TDC_Pago2").val().split("_")[0];
//
//    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
//
//    sendServiceJSON(url,param,cargarCuentasPagosJSONDataSuccess,null,null);
//}
//
//
//function cargarCuentasPagosJSONDataSuccess(originalRequest){
//    var result = originalRequest;
//    var cuentas = result.cuentasTOB;
//    var respuesta = result.respuesta;
//    var tipoPago=result.tipoPagoTDC;
//    idioma = result.idioma;
//
//    if(respuesta=="OK"){
//
//        if(idioma=="_us_en"){
//            //cargar_selectPersonalId("numero_cuenta_pago", cuentas,"Select","-2");
//            cargar_selectCuenta("numero_cuenta_pago", cuentas,"Select","-2",'-2','0');
//            cargar_selectPersonalId("tipoPagoTDC", tipoPago,"Select","-2");
//
//
//        }else{
//            //cargar_selectPersonalId("numero_cuenta_pago", cuentas,"Seleccione","-2");
//            cargar_selectCuenta("numero_cuenta_pago", cuentas,"Select","-2",'-2', '0');
//            cargar_selectPersonalId("tipoPagoTDC", tipoPago,"Seleccione","-2");
//
//        }
//        //Elimina las que no son en dolares
//        $("#numero_cuenta_pago option").each(function(){
//            var texto=$(this).text();
//            if ((texto.indexOf("USD") < 0)&&(texto!="Select")){
//                $(this).remove();
//            }
//        });
//        $("#numero_cuenta_pago").val("-2");
//        $('#numero_cuenta_pago').trigger("chosen:updated");
//        $("#tipoPagoTDC").val("-2");
//        $('#tipoPagoTDC').trigger("chosen:updated");
//    }else{
//
//    }
//
//}

function buscarHistoricoPagos(){

    $("#div_carga_espera").removeClass("oculto");
    var url = urlCargarHistoricoPago;
    var param={}
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_Pago2").val();
    jsonCreditCards[1] = $("#fec_desde_Pago").val();
    jsonCreditCards[2] = $("#fec_hasta_Pago").val();
    jsonCreditCards[3] = opcBusquedaPagoTDC2;

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    // sendServiceJSON_sinc(url,param,buscarHistoricoPagosJSONDataSuccess,null,null);
    sendServiceJSON(url,param,buscarHistoricoPagosJSONDataSuccess,null,null);
}


function buscarHistoricoPagosJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var respuesta = result.respuesta;
    var detalles = result.detalles;

    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto")
    if(result.mensaje=="NO OK"){
        var mensaje="";
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            mensaje="Payment service is currently not available, please try again later";
        else
            mensaje="El servicio de pagos no se encuentra disponible, por favor intente mas tarde";

        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
//    else{
//
//        if(result.mensaje!="NO OK 2"){
//            $('#tdcpagos_TAGSaldoActual_datos').html(detalles[0]);
//            $('#tdcpagos_TAGMontoBloqueado_datos').html(detalles[1]);
//            $('#tdcpagos_TAGMontoDisponible_datos').html(detalles[2]);
//            $('#tdcpagos_TAGPagoMin_datos').html(detalles[3]);
//            $("#pay_card").fadeIn("slow");
//        }
//    }

    // $("#div_tabla_consulta_historial_TDC_Pagos").empty();
    $("#tabla_consulta_historial_TDC_Pagos").empty();

    // crearTablaTmp('div_tabla_consulta_historial_TDC_Pagos','tabla_consulta_historial_TDC_Pagos',columnas,datos);
    crearTablaV2('tabla_consulta_historial_TDC_Pagos',columnas,datos,"",false);

    $(".detalle_resumen_pago_tdc").click(function(){
        consultarDetalle_PagosTdcFCJSONData($(this).attr("id").split("_")[1]);
    });

/*
    oTableHistorialPagosTDC2 = $('#tabla_consulta_historial_TDC_Pagos').dataTable({
        "iDisplayLength": 7,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "right" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });
*/

    oTableHistorialPagosTDC2 = $('#tabla_consulta_historial_TDC_Pagos').dataTable({
        "iDisplayLength": 7,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "right" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}

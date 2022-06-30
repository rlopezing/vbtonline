var urlPagosTDCLiberar="CreditCards_cargarPagosTdc.action";
//var urlconsultarDetallePagoTDCFC="CreditCards_detallePagoTDC.action";
var urlSavePagosTDCLiberar="CreditCards_cambiarEstatusPagosTdc.action";

var grupoID="";
var idioma="";
var noInfo="";
var parametros="";


$(document).ready(function(){

    $("#btn_liberarPagosTDC").click(function(){
        var numReferencia ="";
        var monto="";
        var mensaje="";
        var contSel=0;
        var saldoDisponible=0;
        var pago=0;

        $(".verificarSelecccionPagosTDC").each(function(){
            var ok="OK";
            if($("#div_"+this.id).hasClass("error_campo"))
                $("#div_"+this.id).removeClass("error_campo");
            if($("#"+this.id).val()=="1"){

                contSel++;

                saldoDisponible = Number(this.name.split("|")[9]);
                pago =  Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.'));
                if((saldoDisponible - pago < Number(parametros.minimun_balance))) {
                    if(idioma=="_us_en"){
                        mensaje = mensaje + "Payment Number:"+this.name.split("|")[0] + " The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                    }
                    else {
                        mensaje = mensaje + "Número de Pago:"+this.name.split("|")[0] + " Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                    }

                    ok="NO OK";
                }

                if(ok=="OK"){
                    if (numReferencia!="")
                        numReferencia = numReferencia + ",";
                    numReferencia = numReferencia + "'" + this.id + "'";

                    if (monto!="")
                        monto = monto + ",";

                    monto = monto + "'" + this.name.split("|")[1] + "'";
                }else{
                    $("#div_"+this.id).addClass("error_campo");
                }
            }


        });

        if(!contSel){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html('You must select at least one payment');
            else
                $("#mensaje_error").html('Debe seleccionar al menos un pago');
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(mensaje!=""){
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                $("#clave_liberarPagosTDC").attr("style","display: ");
                $("#pwdClaveConfirmReleasePaymentsTdc").val("");
                $("#div_tabla_consultaPagosTDCPorLiberar").attr("style", "display: none");
                crearResumenLiberarPagosTdc("resumen_liberarPagosTDC_FC",numReferencia);
                numReferenciaGlobal=numReferencia;
                montosGlobal=monto;
            }
        }
    });


    $("#btn_rechazarLiberacionPagosTDC").click(function(){
        var numReferencia ="";
        $(".verificarSelecccionPagosTDC").each(function(){
            if($("#"+this.id).val()=="1"){
                if (numReferencia!="")
                    numReferencia = numReferencia + ",";
                numReferencia = numReferencia + "'" + this.id + "'";

            }
        })

        if(numReferencia==""){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html('You must select at least one payment');
            else
                $("#mensaje_error").html('Debe seleccionar al menos un pago');
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(idioma=="_us_en"){
                if (confirm('Are you sure you want to reject the selected payments?')) {
                    savePaymentsTdcToRejectJSONData(numReferencia, "LIBERAR");
                }
            } else {
                if (confirm('¿Est\u00e1 seguro que desea rechazar los pagos seleccionados?')) {
                    savePaymentsTdcToRejectJSONData(numReferencia, "LIBERAR");
                }
            }
        }

    });



    $("#btn_aceptarClave_liberarPagosTdc").click(function(){

        $("#btn_cambiarMtdClave").click();
        $("#btn_cambiarMtdClave").addClass("oculto");
        $("#btnAceptar").addClass("oculto");

        //Se llama a la pantalla de metodos de validacion
        mainValidationMethods("liberarPagosTdcFC");
    });
    $("#btn_cancelarClave_liberarPagosTdc").click(function(){
        $("#clave_liberarPagosTDC").attr("style","display: none");
        $("#pwdClaveConfirmReleasePaymentsTdc").val("");
        $("#div_tabla_consultaPagosTDCPorLiberar").attr("style", "display: ");
    });

});


function crearResumenLiberarPagosTdc(contenedor_id,numReferencia){

    var contenedor= $("#"+contenedor_id);
    contenedor.empty();
    lista=numReferencia.split(",");
    $.each(lista,function(pos,item){
        var elementos=$("#"+item.replace("'","").replace("'","")).attr("name");
        var table=$(document.createElement("table")).appendTo(contenedor);
        table.attr("class","tabla_resumen2");
        table.attr("cellpadding","4");
        table.attr("cellspacing","1");
        var tr=$(document.createElement("tr")).appendTo(table);
        var td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_cod_pago'>Payment Number</span>");
        else
            td.html("<span id='resumen_detalle_cod_pago'>Número de Pago</span>");

        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        td.html(elementos.split("|")[0]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_credit_card'>Credit Card Number</span>");
        else
            td.html("<span id='resumen_detalle_credit_card'>Número de tarjeta</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[1]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_cuenta_debito'> From Account</span>");
        else
            td.html("<span id='resumen_detalle_cuenta_debito'>Cuenta Débito</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[2]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_tipo_pago'>Payment Option</span>");
        else
            td.html("<span id='resumen_detalle_tipo_pago'>Tipo de Pago</span>");

        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[8]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_monto_pago'>Amount</span>");
        else
            td.html("<span id='resumen_detalle_monto_pago'>Monto</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[6]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_estatus'>Status</span>");
        else
            td.html("<span id='resumen_detalle_estatus'>Estatus</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[7]);
    });
}


function LoadPaymentsTdcToReleaseJSONData(){

    var url = urlPagosTDCLiberar;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = 'A';

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON(url,param,LoadPaymentsTdcToReleaseSuccess,null,null);
}

function LoadPaymentsTdcToReleaseSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tabla="tabla_PagosTdcPorLiberar";
    idioma = result.idioma;

    $("#div_tabla_consultaPagosTDCPorLiberar").attr("style", "display: ");
    $("#clave_liberarPagosTDC").attr("style","display: none");
    $("#pwdClaveConfirmRelease").val("");

    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    crearTablaTmp("div_tabla_PagosTDCPorLiberar",tabla,columnas,datos);

    $(".detalle_resumen_pago_tdc").click(function(){
        consultarDetalle_PagosTdcFCJSONData($(this).attr("id").split("_")[1]);
    });

    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
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

    $("#div_carga_espera").addClass("oculto");
}

//function consultarDetalle_PagosTdcFCJSONData(codPago){
//    var url = urlconsultarDetallePagoTDCFC;
//    var param={};
//    var jsonCreditCards=[];
//
//    jsonCreditCards[0] = codPago;
//
//    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});
//
//    sendServiceJSON(url,param,consultarDetalle_PagosTdcFCSuccess,null,null);
//}

//function consultarDetalle_PagosTdcFCSuccess(originalRequest){
//
//    var result= originalRequest;
//
//    if(result.detalle_pagoTDC.length > 0){
//
//        $("#detalle_pagoTDC_CreditCardNumber").html(result.detalle_pagoTDC[0]);
//        $("#detalle_pagoTDC_DebitAccount").html(result.detalle_pagoTDC[2]);
//        $("#detalle_pagoTDC_PaymentOpcion").html(result.detalle_pagoTDC[1]);
//        $("#detalle_pagoTDC_Amount").html(result.detalle_pagoTDC[3]);
//        $("#detalle_pagoTDC_Status").html(result.detalle_pagoTDC[4]);
//        $("#detalle_pagoTDC_usr_carga2").html(result.detalle_pagoTDC[5]);
//        $("#detalle_pagoTDC_fecha_carga2").html(result.detalle_pagoTDC[6]);
//
//
//        //Ocultar
//        if ($("#detalle_pagoTDC_usr_carga2").html()==""){
//            $("#detalle_pagoTDC_Record").addClass("oculto");
//            $("#detalle_pagoTDC_div_usr_carga").addClass("oculto");
//            $("#detalle_pagoTDC_div_usr_carga2").addClass("oculto");
//        }
//
//        $("#detalle_pagoTDC_div_usr_apro").addClass("oculto");
//        $("#detalle_pagoTDC_div_usr_apro2").addClass("oculto");
//        $("#detalle_pagoTDC_div_usr_lib").addClass("oculto");
//        $("#detalle_pagoTDC_div_usr_lib2").addClass("oculto");
//
//
//        $("#detalle_pagoTDC_fc").fadeIn("slow");
//    }
//}

//function cambiarValorPayment(id){
//    if($("#"+id).is(':checked')){
//        $("#"+id).attr("value","1");
//    }else
//        $("#"+id).attr("value","0");
//}

function savePaymentsTdcToReleaseJSONData(numReferencia){
    var url = urlSavePagosTDCLiberar;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0]= numReferencia;
    jsonCreditCards[1]= "L";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON_sinc(url,param,savePaymentsTdcToReleaseSuccess,null,null);
}


function savePaymentsTdcToReleaseSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var res = result.respuesta;
    idioma = result.idioma;

    if(res=="ERROR_EMAIL") {
        if(idioma=="_us_en")
            alert("The selected Payments has been Released but we couldn't send notifications emails");
        else
            alert("Los pagos seleccionados han sido liberados pero no hemos podido enviar los correos electrónicos");
    }
    else if(res=="OK"){
        if(idioma=="_us_en")
            alert("The selected Payments has been Released");
        else
            alert("Los pagos seleccionados han sido liberados");

    }else{
        if(idioma=="_us_en")
            alert("The selected Payments has not been Released");
        else
            alert("Los pagos seleccionados no han sido liberados");
    }
    LoadPaymentsTdcToReleaseJSONData();
}
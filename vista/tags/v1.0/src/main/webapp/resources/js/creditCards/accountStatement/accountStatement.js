var urlConsultarSaldos_TDC_AE="CreditCards_cargarDetalleYMovimientosEstadoCuentaTDC.action";
var urlCargar_TDC_AE="CreditCards_cargarTarjetas.action";
var urlReportEdoCuentaTDC="reportEdoCuentaTDC.action";

var idioma ="";
var noInfo="";
$(document).ready(function(){



    $("#consulta_TDC_AE").click(function(){
        var mensaje="";
        $(".requerido_TDC_AE").each(function(){
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
//
        if(mensaje!=""){
            $("#div_tabla_consulta_TDC_AE").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#div_tabla_consulta_TDC_AE").fadeIn("fast");
            consultarSaldosTDCTransJSONData();
            $("#estado_cuenta_TDC_imprimir").fadeIn("fast");
        }

    });

});


function consultarOnClickTDCEstadoCuenta(valor){
        if(valor!="-2"){
            $("#div_mensajes_error").fadeOut("fast");
            $("#div_tabla_consulta_TDC_AE").fadeIn("fast");
            consultarSaldosTDCTransJSONData();
            $("#estado_cuenta_TDC_imprimir").fadeIn("fast");
        }
}

function crearPDF_accountStatement_TDC(){

//    var url = '/vbtonline/reporter?command=PDFCuentasEdoCuenta&numeroCuenta=<%=strNumeroCuenta%>&codigoCartera=<%=strCodigoCartera%>&cmbMes=<%=strCmbMes%>&txtAño=<%=strTxtAño%>';

    var num_cta =  $().crypt({method: "b64enc",source:$("#numero_cuenta_TDC_AE").get(0).value});
    var mes =  $().crypt({method: "b64enc",source:$("#date_TDC_AE").val()});

    urlReportEdoCuentaTDC="reportEdoCuentaTDC.action";
    urlReportEdoCuentaTDC = urlReportEdoCuentaTDC + "?num_cta="+num_cta+"&mes="+mes;
    window.open(urlReportEdoCuentaTDC);

}

function limpiarTabla_TDC(valor){
    if(valor=="-2"){
        $('#saldo_TDC_AE').html("");
        $('#limiteCredito_TDC_AE').html("");
        $('#creditoDisponible_TDC_AE').html("");
        $('#fechaFacturacion_TDC_AE').html("");
        $('#fechaLimitePago_TDC_AE').html("");
        $('#saldoActual_TDC_AE').html("");
        $('#pagoTotal_TDC_AE').html("");
        $('#pagoMinimo_TDC_AE').html("");

        $("#div_tabla_consulta_TDC_AE").empty();
        $("#estado_cuenta_TDC_imprimir").fadeOut("fast");
    }
}

function limpiarTabla_TDC_fecha(valor){

        $('#saldo_TDC_AE').html("");
        $('#limiteCredito_TDC_AE').html("");
        $('#creditoDisponible_TDC_AE').html("");
        $('#fechaFacturacion_TDC_AE').html("");
        $('#fechaLimitePago_TDC_AE').html("");
        $('#saldoActual_TDC_AE').html("");
        $('#pagoTotal_TDC_AE').html("");
        $('#pagoMinimo_TDC_AE').html("");

        $("#div_tabla_consulta_TDC_AE").empty();
      $("#estado_cuenta_TDC_imprimir").fadeOut("fast");

}

function AccountStatementTDCInfoPaginaJSONData(){
    var url = urlCargar_TDC_AE;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    $("#div_creditCard_AE .spanFormulario_creditCards_AE").each(function(){
        this.innerHTML="";
    });

    $("#div_tabla_consulta_TDC_AE").empty();

//    $("#alertaSeguridadTDC").fadeIn("fast");
    //$("#marco_trabajo").css("height","950px");
//    $("#div_creditCard_AE_alertaSeguridad #alertaSeguridadTDC a ").attr("title","esto es una prueba AE");

    sendServiceJSON(url,param,AccountStatementTDCInfoSuccess,null,null);
}


function AccountStatementTDCInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var tarjetas = result.tarjetas;
    var codigo = result.codigo;
    idioma = result.idioma;
    var fecha = result.fecha;

    if(codigo=="0"){
        $("#alertaSeguridadTDC #pantalla").val("div_creditCard_AE");
        $("#div_creditCard_AE_alertaSeguridad").attr("style", "display: ");
        $("#div_noInfo_accountStatement_creditCard").attr("style", "display: none");
        $("#div_creditCard_AE").attr("style", "display: none");
        $("#estado_cuenta_TDC_imprimir").fadeOut("fast");
        $("#numero_cuenta_TDC_AE").removeClass("error_campo");
        $("#btnVolverPortafolioCC").addClass("oculto");

        if(fecha!=null)
            cargar_selectPersonal2("date_TDC_AE", fecha);

        if(tarjetas!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("numero_cuenta_TDC_AE", tarjetas,"Select","-2");
            else
                cargar_selectPersonal("numero_cuenta_TDC_AE", tarjetas,"Seleccione","-2");
    }else{
        $("#div_creditCard_AE_alertaSeguridad").attr("style", "display: none");
        $("#div_creditCard_AE").attr("style", "display: none");
        $("#div_noInfo_accountStatement_creditCard").attr("style", "display: ");
    }




}

function consultarSaldosTDCTransJSONData(){
    var url = urlConsultarSaldos_TDC_AE;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_AE").get(0).value;

    jsonCreditCards[1] = $("#date_TDC_AE").get(0).value;
    $("#numero_cuenta_TDC_AE").removeClass("error_campo");


    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,consultarBloqueosAESuccess,null,null);
}


function consultarBloqueosAESuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var detalles = result.detalles;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    if(codigo=="0"){
        $('#saldo_TDC_AE').html(detalles[12]);
        $('#limiteCredito_TDC_AE').html(detalles[6]);
        $('#creditoDisponible_TDC_AE').html(detalles[7]);
        $('#fechaFacturacion_TDC_AE').html(detalles[8]);
        $('#fechaLimitePago_TDC_AE').html(detalles[11]);
        $('#saldoActual_TDC_AE').html(detalles[15]);
        $('#pagoTotal_TDC_AE').html(detalles[9]);
        $('#pagoMinimo_TDC_AE').html(detalles[10]);


        crearTabla('div_tabla_consulta_TDC_AE','tabla_consulta_TDC_AE',columnas,datos);

        var oTable = $('#tabla_consulta_TDC_AE').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bPaginate" : false,
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



//
//


}



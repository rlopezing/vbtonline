var urlBackOfficeConsultarTarjetasPagos="BackOffice_consultarTarjetas.action";
var urlConsultarPagos_TDC_BO="BackOffice_buscarPagosTDC.action";
var urlBackOfficeConsultarDetallePagos="BackOffice_consultarDetallePagos.action";
var oTablePagos;
var oTablePagosDetalle;
var numeroDetallePagos=0;
var consultaTDC="";
var valorActualTDC="";

$(document).ready(function(){

    $("#fec_desde_Pagos_bo").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $("#fec_hasta_Pagos_bo").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});


    $("#numero_cuenta_TDC_PAGOS_BO").chosen({ search_contains: true });

    $("#fec_desde_Pagos_bo").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        defaultDate: 0,
        beforeShowDay: editDays,
        changeYear: true
//        onClose: function (selectedDate) {
//            $("#fec_hasta_PAGOS_bo").datepicker("option", "minDate", sumarDiaFecha(selectedDate,1));
//        }

    });

    $("#fec_hasta_Pagos_bo").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        beforeShowDay: editDays,
        changeYear: true
    });




    $("#limpiarPagosTarjetaBO").click(function(){
        $("#carteraFiltroTarjetaPagossBO").val("");
        $("#div_result_pagos_bo").addClass("oculto");
        $("#set_tabla_consulta_pagos_TDC_BO").addClass("oculto");
        $("#numero_cuenta_TDC_PAGOS_BO").html("");
        $("#fec_desde_Pagos_bo").val("");
        $("#fec_hasta_Pagos_bo").val("");
        $("#carteraExacto_TDC_Pagos").attr("checked",false);
        consultaTDC="OK";
    });




    $("#buscarPagosTarjetaBO").click(function(){



        if ($("#carteraFiltroTarjetaPagossBO").val()!=valorActualTDC){
            valorActualTDC=$("#carteraFiltroTarjetaPagossBO").val();
            consultaTDC="NO OK";
            $("#div_carga_espera").removeClass("oculto");
            $("#div_result_pagos_bo").addClass("oculto");
            $("#set_tabla_consulta_pagos_TDC_BO").addClass("oculto");
            $("#tag_clientepagoso_bo").text("");

            buscarTarjetasPagosBOJSONData();
        } else{
            if (consultaTDC=="OK"){
                consultaTDC="NO OK";
                $("#div_carga_espera").removeClass("oculto");
                $("#div_result_pagos_bo").addClass("oculto");
                $("#tag_clientepagoso_bo").text("");
                buscarTarjetasPagosBOJSONData();

            } else{
                buscarPagosTDC($("#numero_cuenta_TDC_PAGOS_BO").val()) ;
            }
        }








    });


});


function buscarTarjetasPagosBOJSONData(){
    var url = urlBackOfficeConsultarTarjetasPagos;
    var param={};

    var jsonParametrosString=[];

    jsonParametrosString[0] ="'"+$("#carteraFiltroTarjetaPagossBO").val()+"'";

    if($("#carteraExacto_TDC_Pagos").is(':checked')){
        jsonParametrosString[1] ="SI";
    }else{
        jsonParametrosString[1] ="NO";
    }


    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,buscarTarjetasPagosBOSuccess,null,null);
}


function buscarTarjetasPagosBOSuccess(originalRequest){
    var result = originalRequest;
    var tarjetasCL = result.tarjetasCL;
    var codigo = result.mensaje;
    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto");
//    $("#div_tabla_consulta_reglas_activas_TDC_CL_BO").empty();
//    $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
//    $("#div_tabla_consulta_historial_TDC_CL_BO").empty();
//    $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");

    if(codigo=="OK" && tarjetasCL!=null){
        $("#div_result_pagos_bo").removeClass("oculto");
        //$("#fec_desde_CL_bo").val(result.fechaHoy);
        cargar_selectPersonal("numero_cuenta_TDC_PAGOS_BO", tarjetasCL,"Seleccione","-2");
        $('#numero_cuenta_TDC_PAGOS_BO').trigger("chosen:updated");

    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("No se encontraton resultados");
        $('#tabla_consulta_pagos_TDC_BO').dataTable().fnClearTable();
        $("#set_tabla_consulta_pagos_TDC_BO").addClass("oculto");
//        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
//        $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");
        $("#div_mensajes_error").fadeIn("slow");
    }

}








function buscarPagosTDC(valor){
    var url = urlConsultarPagos_TDC_BO;
    var param={};
    var jsonCreditCards=[];

        jsonCreditCards[0] = valor.split("_")[1];
        jsonCreditCards[1] = valor.split("_")[0];
        jsonCreditCards[2] = valor.split("_")[2];
        jsonCreditCards[3] = valor;
        jsonCreditCards[4] = $("#fec_desde_Pagos_bo").val();
        jsonCreditCards[5] = $("#fec_hasta_Pagos_bo").val();


        $('#tabla_consulta_pagos_TDC_BO').empty();

        param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

        sendServiceJSON(url,param,cargarDatosPagosSuccess,null,null);

}


function cargarDatosPagosSuccess(originalRequest){
    var result = originalRequest;
    var columnasActivas = result.tablaActivas_culumnas;
    var columnasHistoricas = result.tablaHistorico_culumnas;
    var filasActivas = result.contenidoTablaActivas_info;
    var filasHistoricas = result.contenidoTablaHistorico_info;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    var estatusTarjeta = result.estatusTarjeta;

    noInfo="No hay informaci&oacute;n disponible";
    if (codigo!="0") {
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
//        $("#set_tabla_consulta_pagos_TDC_BO").empty();
        $("#set_tabla_consulta_pagos_TDC_BO").addClass("oculto");
    } else {
        $("#div_carga_espera").removeClass("oculto");

        cargarIdiomaJSONData_sinc();
        $("#div_tabla_consulta_pagos_TDC_BO").empty();
        $("#set_tabla_consulta_pagos_TDC_BO").removeClass("oculto");
        crearTabla('div_tabla_consulta_pagos_TDC_BO', 'tabla_consulta_pagos_TDC_BO', columnasActivas, filasActivas);
         oTablePagos = $('#tabla_consulta_pagos_TDC_BO').dataTable({
            "iDisplayLength": 5,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bDestroy": true,
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


        $("#div_carga_espera").addClass("oculto");
    }
}



function abrirDetalleTablaPagos (elemento){
    var nTr =  $(elemento).parents('tr')[0];
    if ( oTablePagos.fnIsOpen(nTr) )
    {
        /* This row is already open - close it */
        elemento.src = "resources/images/comun/nolines_plus.gif";
        oTablePagos.fnClose( nTr );
    }
    else
    {
        /* Open this row */
        elemento.src = "resources/images/comun/nolines_minus.gif";
        numeroDetallePagos++;
        BackOfficeDetallePagosJSONData($(elemento).attr("id").split("|")[0],$(elemento).attr("id").split("|")[1]);

        oTablePagos.fnOpen( nTr,fnFormatDetailsPago, 'details');
    }
};






function BackOfficeDetallePagosJSONData(idPago,cartera){
    var url = urlBackOfficeConsultarDetallePagos;
    var param={};
    var jsonConsulta=[];

    jsonConsulta[0]= idPago;
    jsonConsulta[1]= cartera;

    param.jsonConsultaContratos=JSON.stringify({"parametros":jsonConsulta});

    sendServiceJSON(url,param,BackOfficeDetallePagosSuccess,null,null);
}

function BackOfficeDetallePagosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;


   var tabla="tabla_detallepagos_tdc"+numeroDetallePagos;


    crearTabla("div_tabla_detallepagos_tdc"+numeroDetallePagos,tabla,columnas,datos);

    oTablePagosDetalle = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bDestroy": true,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            {  "sClass": "left" }
        ],
        "oLanguage": {
            "sZeroRecords": "",
            "sInfoEmpty": "",
            "sSearch": "Buscar:",
            "sInfo": "Mostrando _START_ de _END_ de _TOTAL_ entidades"
        }
    });

}




function fnFormatDetailsPago ()
{   var sOut ='<table width="100%"><tr><td width="10%">';
        sOut +='<tr>';
        sOut +='<td><div id="div_tabla_detallepagos_tdc'+numeroDetallePagos+'"></div></td>';
        sOut +='</tr>';
        sOut +='</table>';
    return sOut;
}
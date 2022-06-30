var urlBackOfficeConsultarLogSMS ="BackOffice_consultarLogSms.action";
var cargar_pagina_sms = "";

$(document).ready(function() {
    $( "#fechaDesdeFiltroSMS" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true });
    $( "#fechaHastaFiltroSMS" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true });


    $("#sms_reset").click(function(){


        blanquearFormularios("log_sms");
        $("#fechaDesdeFiltroSMS" ).val(fechaHoy);
        $("#fechaHastaFiltroSMS" ).val(fechaHoy);

        //$("#div_tabla_consulta_resultado").empty();
        $('#tabla_resultadoBusquedaLogSms').dataTable().fnClearTable();
    });


    $("#sms_search").click(function(){

        BackOfficeConsultarLogSMSJSONData();

    });


});


function BackOfficeConsultarLogSMSJSONData(){
    var url = urlBackOfficeConsultarLogSMS;
    var param={};
    var jsonConsultaLogSms=[];
    var datosConsSMS={};



    $("#div_tabla_consulta_contratosBO").empty();
    $("#div_carga_espera").removeClass("oculto");
    $("#div_tabla_consulta_paginacion_sms").empty();



    if( ($("#estatusFiltroSMS").val()==null ))   {

        datosConsSMS.strTipoStatus="-2";
    } else{
        datosConsSMS.strTipoStatus= $("#estatusFiltroSMS").val();
    }

  if( ($("#motivoFiltroSMS").val()==null ))   {

        datosConsSMS.strTipoMotivo="-2";
    } else{
        datosConsSMS.strTipoMotivo= $("#motivoFiltroSMS").val();
    }

    datosConsSMS.strContrato=$("#sms_contratoFiltro").val();
    datosConsSMS.strUsuario=$("#SMS_usuarioFiltro").val();
    datosConsSMS.strTelefono=$("#SMS_telefonoFiltro").val();

    datosConsSMS.strTxtDesde=$("#fechaDesdeFiltroSMS").val();
    datosConsSMS.strTxtHasta=$("#fechaHastaFiltroSMS").val();


    jsonConsultaLogSms[0]= datosConsSMS;

    param.jsonConsultaLogSms=JSON.stringify({"consultaSMS":jsonConsultaLogSms});

    // sendServiceJSON_sinc(url,param,BackOfficeConsultarLogSMSJSONDataSuccess,null,null);
    sendServiceJSON(url,param,BackOfficeConsultarLogSMSJSONDataSuccess,null,null);

}


function BackOfficeConsultarLogSMSJSONDataSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;

    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center"},
        { "sClass": "center" },
        { "sClass": "center" } ,
        { "sClass": "left" }
    ];

    var tamano_pag=15;

    var id_contenedor="div_tabla_consulta_resultado";
    var tabla_id="tabla_resultadoBusquedaLogSms";
    // var tabla=crearTabla(id_contenedor,tabla_id,columnas,datos);
    crearTablaV3(id_contenedor,tabla_id,columnas,datos);

    $("#div_carga_espera").addClass("oculto");

    if ( cargar_pagina_sms==""){
        cargar_select("estatusFiltroSMS", result.tipoEnvio);
        cargar_select("motivoFiltroSMS", result.tipoMotivo);
        cargar_pagina_sms="NO";

   }


/*    oTable = $('#tabla_resultadoBusquedaLogSms').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "aoColumns": p_sclass,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    oTable = $('#tabla_resultadoBusquedaLogSms').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "aoColumns": p_sclass,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}



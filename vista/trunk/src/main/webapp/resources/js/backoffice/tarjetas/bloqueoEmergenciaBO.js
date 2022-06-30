var urlBackOfficeConsultarTarjetasBO="BackOffice_consultarTarjetasEmergencia.action"
var urlConsultarEstatus_TDCCL_BO="BackOffice_cargarEstatusTDCBO.action";
var urlCambiarEStatus_TDC_BO="BackOffice_cambiarEstatusTdcBO.action";

var idioma = "";
var noInfo="";
var valores="";
var valueBoton= "";

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

    $("#crear_bloqueo_BO").on( "click", function() {
        var motivoscl = $("#motivosBloqueoBO").val();
        var tarjetas = $("#numero_cuenta_TDC_CLE_BO").val();
        if(motivoscl != '-2' && tarjetas != '-2'){
            $("#div_carga_espera").removeClass("oculto");
            if(confirm('¿Est\u00e1 seguro que desea bloquear la tarjeta de crédito?. Al aceptar se inactivan todos los períodos de activación programados.')){
                valueBoton = $(this).val();
                cambiarEstatusBOJSONData(tarjetas,motivoscl);
            }
        }else{
            alert('Complete los campos');
        }

    });

    $("#buscarBloqueoEmergenciaBO").click(function(){
        $("#div_carga_espera").removeClass("oculto");
        $("#numero_cuenta_TDC_CLE_BO").html("");
        BackOficceBloqueoEmergenciaJSONData();
    });

});




function cargarEstatusBO(value){

    if(value!="-2") {
        consultarEstatusBOJSONData();
        $("#div_carga_espera").removeClass("oculto");
    }
}

function consultarEstatusBOJSONData(){
    var url = urlConsultarEstatus_TDCCL_BO;
    var param={};
    var jsonParametrosString=[];

    jsonParametrosString[0] ="";
    jsonParametrosString[1] ="";
    jsonParametrosString[2] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[0] :"";
    jsonParametrosString[3] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[1] :"";
    jsonParametrosString[4] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[2] :"";
    jsonParametrosString[5] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[4] :"";


    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});


    sendServiceJSON(url,param,consultarEstatusBOSuccess,null,null);
}


function consultarEstatusBOSuccess(originalRequest){
    var result = originalRequest;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    var estatusTarjeta = result.estatusTarjeta;
    var motivoscle  = result.motivosCLEBO;
    var filasBloqueos = result.contenidoTabla_infoTest;
    var columnasBloqueos = result.contenidoTabla_culumnasTest;

    $("#tag_cliente_bloqueo_clebo").html($("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[3]);

    if(estatusTarjeta == "B" || estatusTarjeta == "A"){
        if(estatusTarjeta == "B"){
            if($("#btnBloqueoBO").hasClass("oculto"))
                $("#btnBloqueoBO").removeClass("oculto");
            if($("#motivosCLEBO").hasClass("oculto"))
                $("#motivosCLEBO").removeClass("oculto");
            if(!$("#div_datos_TDC_CLE_estatus").hasClass("oculto"))
                $("#div_datos_TDC_CLE_estatus").addClass("oculto");

            if(idioma=="_us_en")
                cargar_selectPersonal("motivosBloqueoBO", motivoscle,"Select","-2");
            else
                cargar_selectPersonal("motivosBloqueoBO", motivoscle,"Seleccione","-2");

        }else if(estatusTarjeta == "A" ){
            if(!$("#motivosCLEBO").hasClass("oculto"))
                $("#motivosCLEBO").addClass("oculto");
            if(!$("#btnBloqueoBO").hasClass("oculto"))
                $("#btnBloqueoBO").addClass("oculto");
            if($("#div_datos_TDC_CLE_estatus").hasClass("oculto"))
                $("#div_datos_TDC_CLE_estatus").removeClass("oculto");
        }

        if($("#div_respuesta_datos_tdc").is(":hidden"))
            $("#div_respuesta_datos_tdc").show( "fast" );

        if($("#div_noRespuesta_datos_tdc").is(":visible"))
            $("#div_noRespuesta_datos_tdc").hide( "fast" );
    }else{
        if(!$("#motivosCLEBO").hasClass("oculto"))
            $("#motivosCLEBO").addClass("oculto");
        if(!$("#btnBloqueoBO").hasClass("oculto"))
            $("#btnBloqueoBO").addClass("oculto");

        if($("#div_respuesta_datos_tdc").is(":visible"))
            $("#div_respuesta_datos_tdc").hide( "fast" );

        if($("#div_noRespuesta_datos_tdc").is(":hidden"))
            $("#div_noRespuesta_datos_tdc").show( "fast" );
    }

    if( filasBloqueos!=null) {
        $("#div_InfoBloqueo_bc_table").empty();
        $("#div_InfoBloqueo_bc_tdc").removeClass("oculto");
        crearTabla('div_InfoBloqueo_bc_table', 'tabla_bloqueos_emergencia_BO', columnasBloqueos, filasBloqueos);
        var oTableActivas = $('#tabla_bloqueos_emergencia_BO').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": true,
            "bSort": true,
            "aoColumns": [
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"}

            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });

    } else{
        $("#div_InfoBloqueo_bc_tdc").addClass("oculto");
    }

    $("#div_carga_espera").addClass("oculto");
}

function cambiarEstatusBOJSONData(tarjeta,motivos){
    var url = urlCambiarEStatus_TDC_BO;
    var param={};
    var jsonParametrosString=[];



    jsonParametrosString[0] = (!isEmpty(tarjeta)) ? tarjeta.split("_")[0]:"";
    jsonParametrosString[1] = (!isEmpty(tarjeta)) ? tarjeta.split("_")[1]:"";
    jsonParametrosString[2] = (!isEmpty(tarjeta)) ? tarjeta.split("_")[2]:"";
    jsonParametrosString[3] = (!isEmpty(motivos)) ? motivos.split("|")[0]:"";
    jsonParametrosString[4] = (!isEmpty(motivos)) ? motivos.split("|")[1]:"";
    jsonParametrosString[5] = (!isEmpty(tarjeta)) ? tarjeta.split("_")[4]:"";
    jsonParametrosString[6] = "";
    jsonParametrosString[7] = "";
    jsonParametrosString[8] = "";
    jsonParametrosString[9] = "BLOQUEO";


    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,cambiarEstatusBloqueoBOSuccess,null,null);
}


function BackOficceBloqueoEmergenciaJSONData(){
    var url = urlBackOfficeConsultarTarjetasBO;
    var param={};

    var jsonParametrosString=[];

    jsonParametrosString[0] ="'"+$("#carteraFiltroEmergencia").val()+"'";

    if($("#carteraExacto_Emergencia").is(':checked')){
        jsonParametrosString[1] ="SI";
    }else{
        jsonParametrosString[1] ="NO";
    }

    jsonParametrosString[2] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[0] :"";
    jsonParametrosString[3] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[1] :"";
    jsonParametrosString[4] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[2] :"";
    jsonParametrosString[5] = ( !isEmpty($("#numero_cuenta_TDC_CLE_BO option:selected").val()) )?  $("#numero_cuenta_TDC_CLE_BO option:selected").val().split("_")[4] :"";

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,BloqueoEmergenciaBOSuccess,null,null);
}


function BloqueoEmergenciaBOSuccess(originalRequest){
    var result = originalRequest;
    var tarjetasCL = result.tarjetasCL;
    var filasBloqueos = result.contenidoTabla_infoTest;
    var columnasBloqueos = result.contenidoTabla_culumnasTest;

    var codigo = result.mensaje;
    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto");

    if(!isEmpty($("#carteraFiltroEmergencia").val())){
        if(codigo=="OK" && tarjetasCL!=null){
            $("#div_tarjetasInfo").removeClass("oculto");
            cargar_selectPersonal("numero_cuenta_TDC_CLE_BO", tarjetasCL,"Seleccione","-2");
            $('#numero_cuenta_TDC_CLE_BO').trigger("chosen:updated");
        }else{
            $("#mensaje_error").empty();
            $("#mensaje_error").html("No se encontraton resultados");
            $("#div_tarjetasInfo").addClass("oculto");
            $("#div_mensajes_error").fadeIn("slow");
        }
    }

    if( filasBloqueos!=null) {
        $("#div_InfoBloqueo_bc_table").empty();
        $("#div_InfoBloqueo_bc_tdc").removeClass("oculto");
        crearTabla('div_InfoBloqueo_bc_table', 'tabla_bloqueos_emergencia_BO', columnasBloqueos, filasBloqueos);
        var oTableActivas = $('#tabla_bloqueos_emergencia_BO').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": true,
            "bSort": true,
            "aoColumns": [
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"},
                {"sClass": "center"}

            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });

    } else{
        $("#div_InfoBloqueo_bc_tdc").addClass("oculto");
    }
}

$("#limpiarBloqueoEmergenciaBO").click(function(){
    $("#carteraFiltroEmergencia").val("");
    $("#div_tarjetas_emergencia").addClass("oculto");
    $("#carteraExacto_Emergencia").attr("checked",false);


    $("#div_tarjetasInfo").addClass("oculto");
    if(!$("#motivosCLEBO").hasClass("oculto"))
        $("#motivosCLEBO").addClass("oculto");
    if(!$("#btnBloqueoBO").hasClass("oculto"))
        $("#btnBloqueoBO").addClass("oculto");
    if(!$("#div_datos_TDC_CLE_estatus").hasClass("oculto"))
        $("#div_datos_TDC_CLE_estatus").addClass("oculto");
});

function btnBloqueoTabla(btn) {
    var id = $(btn).attr("id").split("|")[0];
    var accion = $(btn).attr("id").split("|")[1];
    var comando = $(btn).attr("id").split("|")[2];
    var nroDoc = $(btn).attr("id").split("|")[3];
    var fechVenDoc = $(btn).attr("id").split("|")[4];
    var codcar = $(btn).attr("id").split("|")[5];
    var descripcion = $(btn).val();
    //cambiar confirm dependiendo el action que eeste llegando

    if (confirm('¿Est\u00e1 seguro que desea '+ descripcion +' ?')) {
        $("#div_carga_espera").removeClass("oculto");
        valueBoton = descripcion;
        cambiarEstatusTDCBOJSONData(id,accion,comando,nroDoc,fechVenDoc,descripcion,codcar);

    }
}

function cambiarEstatusTDCBOJSONData(id,accion,comando,nroDoc,fechVenDoc,descripcion,codcar){
    var url = urlCambiarEStatus_TDC_BO;
    var param={};
    var jsonParametrosString=[];

    jsonParametrosString[0] = "";
    jsonParametrosString[1] = !isEmpty(codcar) ? codcar:"";
    jsonParametrosString[2] = !isEmpty(nroDoc) ? nroDoc:"";
    jsonParametrosString[3] = "";
    jsonParametrosString[4] = "";
    jsonParametrosString[5] = !isEmpty(fechVenDoc) ? fechVenDoc:"";
    jsonParametrosString[6] = !isEmpty(accion) ? accion:"";
    jsonParametrosString[7] = !isEmpty(id) ? id:"";
    jsonParametrosString[8] = !isEmpty(comando) ? comando:"";
    jsonParametrosString[9] = !isEmpty(descripcion) ? descripcion:"";

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,cambiarEstatusBloqueoBOSuccess,null,null);
}

function cambiarEstatusBloqueoBOSuccess(originalRequest){
    var result = originalRequest;
    var codigo = result.codigo;
    var mensaje = result.respuesta;

    $("#div_carga_espera").addClass("oculto");
    idioma = result.idioma;

    if(mensaje=="NO OK"){
        var mensaje="";
        $("#mensaje_error").empty();

        mensaje="El servicio de bloqueo no se encuentra disponible, por favor contacte a su Asesor o Ejecutivo.";

        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else {
        if(mensaje=="ERROR_EMAIL") {

            popupAlert("<span id='tag_popup_mail_1'>El bloqueo ha sido cargado exitosamente pero no hemos podido enviar los correos electrónicos.</span>", "Aceptar");
        }else{

            if(valueBoton == "Bloquear")
                popupAlert("<span id='tag_popup_add_1'>La tarjeta de crédito fue bloqueada con éxito </span>", "Aceptar");
            if(valueBoton == "Desbloquear")
                popupAlert("<span id='tag_popup_add_2'>La tarjeta de crédito fue desbloqueada con éxito </span>", "Accept");
            if(valueBoton == "Aprobar Desbloqueo")
                popupAlert("<span id='tag_popup_add_2'>La tarjeta de crédito fue desbloqueada con éxito </span>", "Accept");

            if( !isEmpty($("#numero_cuenta_TDC_CLE option:selected").val()))
                consultarEstatusBOJSONData();
            else
                BackOficceBloqueoEmergenciaJSONData();
        }
    }
}
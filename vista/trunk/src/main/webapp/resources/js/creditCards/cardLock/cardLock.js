var urlConsultarEstatus_TDC_CL="CreditCards_cargarEstatusTDCCL.action";
var urlCargar_TDC_CL="CreditCards_cargarTarjetasCL.action";
var urlCrearRegla_TDC_CL="CreditCards_crearReglaTDCCL.action";
var urlEliminarRegla_TDC_CL="CreditCards_eliminarReglaTDCCL.action";
var urlValidarFecha="CreditCards_validarFecha.action";
var urlValidarFechaEdit="CreditCards_validarFechaEdit.action";
var urlEditarRegla="CreditCards_editarRegla.action";
var urlProximoDiaHabil="CreditCards_cargarProximoDiaHabil.action";
var urlCargarFeriados="CreditCards_cargarFeriados.action";
var urlValidarLimite="CreditCards_validarLimiteSalvis.action";
//var disabledDates = ['07/13/2015', '07/14/2015'];
var disabledDates = [];
var idioma = "";
var noInfo="";
var valores="";

var bandera="";

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

    //cargarFeriados();

    $("#fec_desde_CL").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        defaultDate: 0,
        //beforeShowDay: editDays,
        minDate: new Date(),
        changeYear: true,
        onClose: function (selectedDate) {
            $("#fec_hasta_CL").datepicker("option", "minDate", selectedDate);
            $("#fec_hasta_CL").datepicker("option", "maxDate", sumarDiaFecha(selectedDate,30));
        }
    });

    $("#fec_hasta_CL").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        //beforeShowDay: editDays_hasta,
        minDate: new Date(),
        changeYear: true
    });



    $("#fec_desde_CL_Edit").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        defaultDate: 0,
        //beforeShowDay: editDays,
        maxDate: "+1y",
        minDate: new Date(),
        changeYear: true   ,
        onClose: function (selectedDate) {
            $("#fec_hasta_CL_Edit").datepicker("option", "minDate", selectedDate);
            $("#fec_hasta_CL_Edit").datepicker("option", "maxDate", sumarDiaFecha(selectedDate,30));
        }
    });


    $("#fec_hasta_CL_Edit").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        //beforeShowDay: editDays_hasta,
        minDate: "+1d",
        changeYear: true
    });

    $("#crear_regla").click(function(){
        var mensaje="";
        $("#div_creditCard_CL .requeridoCrearRegla").each(function(){
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


        if(!isDate($( "#fec_desde_CL").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fec_desde_CL").addClass("error_campo");
        }else if(!isDate($( "#fec_hasta_CL").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"To date is not correct";
            else
                mensaje=mensaje+"La fecha Hasta no es correcta";
            $("#fec_hasta_CL").addClass("error_campo");
        }/*else{
            var fdesdeFormat= $("#fec_desde_CL").val().split("/")[1]
                +"/"+$("#fec_desde_CL").val().split("/")[0]
                +"/"+$("#fec_desde_CL").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fec_hasta_CL").val().split("/")[1]
                +"/"+$("#fec_hasta_CL").val().split("/")[0]
                +"/"+$("#fec_hasta_CL").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The date range is not allowed. Remember: The From and To dates should not be the same. From date must be less than the To date."+"<br>";
                else
                    mensaje=mensaje+"El rango de fecha no es permitido. Recuerde: La fecha Desde y Hasta no deben ser iguales. La fecha Desde debe ser menor a la fecha Hasta."+"<br>";
                $("#fec_desde_CL").addClass("error_campo");
                $("#fec_hasta_CL").addClass("error_campo");

            }else{
                $("#fec_desde_CL").removeClass("error_campo");
                $("#fec_hasta_CL").removeClass("error_campo");
            }
        }  */

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#div_carga_espera").removeClass("oculto");
            validarFechaJSONData();
        }

    });

//    $("#edit_regla_bo").click(function(){
////        $("#div_creditCard_CL_Edit").fadeOut("fast");
//        $("#div_creditCard_CL_Edit").fadeOut("fast");
//        $("#numero_cuenta_TDC_CL_edit").text("");
//        $("#fec_desde_CL_Edit").val("");
//        $("#fec_hasta_CL_Edit").val("");
//        $("#tag_status_CL_Edit").text("");
//        $("#div_creditCard_CL").fadeIn("slow");
//    });


    $("#edit_regla").click(function(){
        var mensaje="";
        $("#div_creditCard_CL_Edit .requeridoCrearReglaEdit").each(function(){
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


        if(!isDate($( "#fec_desde_CL_Edit").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fec_desde_CL_Edit").addClass("error_campo");
        }else if(!isDate($( "#fec_hasta_CL_Edit").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"To date is not correct";
            else
                mensaje=mensaje+"La fecha Hasta no es correcta";
            $("#fec_hasta_CL_Edit").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fec_desde_CL_Edit").val().split("/")[1]
                +"/"+$("#fec_desde_CL_Edit").val().split("/")[0]
                +"/"+$("#fec_desde_CL_Edit").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fec_hasta_CL_Edit").val().split("/")[1]
                +"/"+$("#fec_hasta_CL_Edit").val().split("/")[0]
                +"/"+$("#fec_hasta_CL_Edit").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The date range is not allowed. Remember: The From and To dates should not be the same. From date must be less than the To date."+"<br>";
                else
                    mensaje=mensaje+"El rango de fecha no es permitido. Recuerde: La fecha Desde y Hasta no deben ser iguales. La fecha Desde debe ser menor a la fecha Hasta."+"<br>";
                $("#fec_desde_CL_Edit").addClass("error_campo");
                $("#fec_hasta_CL_Edit").addClass("error_campo");

            }else{
                $("#fec_desde_CL_Edit").removeClass("error_campo");
                $("#fec_hasta_CL_Edit").removeClass("error_campo");
            }
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            validarFechaEditJSONData();
        }

    });

});

//function eliminarRegla(idRegla) {
//    $("#div_carga_espera").removeClass("oculto");
//    eliminarReglaJSONData(idRegla);
//    $("#div_carga_espera").addClass("oculto");
//}

function eliminarReglaJSONData(idRegla) {
    $("#div_carga_espera").removeClass("oculto");
    var url = urlEliminarRegla_TDC_CL;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = idRegla;
    jsonCreditCards[1] = $("#numero_cuenta_TDC_CL").val();

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON_sinc(url,param,eliminarReglaJSONDataSuccess,null,null);

}

function eliminarReglaJSONDataSuccess(originalRequest) {
    var result = originalRequest;
    var eliminada = result.estatusTarjeta;

    $("#div_carga_espera").addClass("oculto");
    if (eliminada == "SUCCESS") {
        if (idioma == "_ve_es") {
            popupAlert("El periodo de activación fue eliminado satisfactoriamente.", "Aceptar");
        } else {
            popupAlert("The activation period was successfully removed.", "Accept");
        }
        cargarEstatusCL($("#numero_cuenta_TDC_CL").val());
    } else {
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").empty();
        $("#mensaje_error").html("<span id='error_eliminando_regla'>" + vbtol_props[idioma]["error_eliminando_regla"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
    }
}

function crearRegla() {
    var fdesdeFormat= $("#fec_desde_CL").val().split("/")[1]
        +"/"+$("#fec_desde_CL").val().split("/")[0]
        +"/"+$("#fec_desde_CL").val().split("/")[2]  ;
    var Fdesde = Date.parse(fdesdeFormat);

    var fhastaFormat= $("#fec_hasta_CL").val().split("/")[1]
        +"/"+$("#fec_hasta_CL").val().split("/")[0]
        +"/"+$("#fec_hasta_CL").val().split("/")[2] ;
    var Fhasta = Date.parse(fhastaFormat);

    $("#div_carga_espera").addClass("oculto");
    if (
        ($("#fec_desde_CL").val() != "") &&
        ($("#fec_hasta_CL").val() != "") &&
        (Fdesde <= Fhasta)
    ) {

        mainValidationMethods("desbloqueoTarjeta");

    } else {
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").empty();
        $("#mensaje_error").html("<span id='error_en_fechas'>" + vbtol_props[idioma]["error_en_fechas"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

function crearReglaJSONData() {
    var url = urlCrearRegla_TDC_CL;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_CL").val();
    jsonCreditCards[1] = $("#fec_desde_CL").val();
    jsonCreditCards[2] = $("#fec_hasta_CL").val();
    jsonCreditCards[3] = "P";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON_sinc(url,param,crearReglaJSONDataSuccess,null,null);

}

function crearReglaJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var creada = originalRequest.estatusTarjeta;
    $("#div_carga_espera").addClass("oculto");
    if (creada == "SUCCESS") {
//        mainValidationMethods()
        if (idioma == "_ve_es") {
            popupAlert("<span id='tag_popup_add'>El periodo de activación fue registrado satisfactoriamente</span>", "Aceptar");
        } else {
            popupAlert("<span id='tag_popup_add'>The activation period was successfully registered.</span>", "Accept");
        }
        cargarEstatusCL($("#numero_cuenta_TDC_CL").val());
    } else {
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").empty();
        if(mensaje="NO OK")
            $("#mensaje_error").html("<span id='tag_error_creando_regla'>" + vbtol_props[idioma]["tag_error_creando_regla_no_ok"] + "</span>");
        else
            $("#mensaje_error").html("<span id='tag_error_creando_regla'>" + vbtol_props[idioma]["tag_error_creando_regla"] + "</span>");

        $("#div_mensajes_error").fadeIn("slow");
    }
}

function activarFechaHasta(idDesde, idHasta) {
    if ($('#'+idDesde).val() != '') {
        $('#'+idHasta).datepicker("option", "disable", false);
        $('#'+idHasta).datepicker("option", "minDate", new Date($('#'+idDesde).datepicker('getDate').getFullYear(), $('#'+idDesde).datepicker('getDate').getMonth(), ($('#'+idDesde).datepicker('getDate').getDate() + 1)));
    } else {
        $('#'+idHasta).datepicker("option", "disable", true);
    }
}

function CardLockTDCInfoPaginaJSONData(){
    $("#div_carga_espera").removeClass("oculto");

    var url = urlCargar_TDC_CL;
    var param={};

    $("#fec_desde_CL").val("");
    $("#fec_hasta_CL").val("");
    $("#tabla_consulta_reglas_activas_TDC_CL").empty();
    $("#tabla_consulta_historial_TDC_CL").empty();
    //$("#div_creditCard_CL_Edit").attr("style", "display: none");
//    $("#div_creditCard_CL_Edit").addClass("oculto");
    $("#set_tabla_consulta_reglas_activas_TDC_CL").addClass("oculto");
    $("#set_tabla_consulta_historial_TDC_CL").addClass("oculto");
    $("#div_datos_regla_mensaje_TDC_CL").addClass("oculto");
    $("#div_datos_regla_TDC_CL").removeClass("oculto");
    $("#numero_cuenta_TDC_CL_edit").text("");
    $("#fec_desde_CL_Edit").val("");
    $("#fec_hasta_CL_Edit").val("");
    $("#tag_status_CL_Edit").text("");
    //$('#tabla_consulta_TDC_Pagos_movimientos').dataTable().fnClearTable();

//    $("#div_tabla_consulta_reglas_corrientes_TDC_CL").empty();

    $("#TAG_INFO_BLOQUEAR").addClass("oculto");

    sendServiceJSON(url,param,CardLockTDCInfoSuccess,null,null);
}


function CardLockTDCInfoSuccess(originalRequest){
    var result = originalRequest;
    var tarjetasCL = result.tarjetasCL;
    var codigo = result.codigo;
    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto");
    if(codigo=="0" && tarjetasCL!=null){
        $("#alertaSeguridadTDC #pantalla").val("div_creditCard_CL");
        $("#div_creditCard_CL_alertaSeguridad").attr("style", "display: ");
        $("#div_creditCard_CL").attr("style", "display: none");
        $("#div_noInfo_CL_creditCard").attr("style", "display: none");
        if(idioma=="_us_en")
            cargar_selectPersonal("numero_cuenta_TDC_CL", tarjetasCL,"Select","-2");
        else
            cargar_selectPersonal("numero_cuenta_TDC_CL", tarjetasCL,"Seleccione","-2");

        // $("#numero_cuenta_TDC_CL option").each(function () {
        //     //console.log($("#numero_cuenta_TDC_CL option"));
        //     if($(this).val().split("_")[5] == 'Z')
        //         $(this).remove();
        //         //console.log((this).val());
        //
        // });

        $("#numero_cuenta_TDC_CL").val("-2");
//        $("#estatus_TDC_CL").empty();
        $('#numero_cuenta_TDC_CL').trigger("chosen:updated");
    }else{
        $("#div_creditCard_CL_alertaSeguridad").attr("style", "display: none");
        $("#div_creditCard_CL").attr("style", "display: none");
        $("#div_noInfo_CL_creditCard").attr("style", "display: ");
    }
    $("#numero_cuenta_TDC_CL").chosen({ search_contains: true });
}

function cargarEstatusCL(value){
//    $("#estatus_TDC_CL").empty();
    $("#fec_desde_CL").val("");
    $("#fec_hasta_CL").val("");

    if(value!="-2") {
        $("#set_tabla_consulta_reglas_activas_TDC_CL").addClass("oculto");
        consultarEstatusTDCCLJSONData(value);
    }else{

        $("#set_tabla_consulta_reglas_activas_TDC_CL").addClass("oculto");
        $("#tabla_consulta_reglas_activas_TDC_CL").empty();
        $("#set_tabla_consulta_historial_TDC_CL").addClass("oculto");
        $("#tabla_consulta_historial_TDC_CL").empty();
//        $("#set_tabla_consulta_reglas_corrientes_TDC_CL").addClass("oculto");
//        $("#div_tabla_consulta_reglas_corrientes_TDC_CL").empty();
    }
}

function consultarEstatusTDCCLJSONData(value){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlConsultarEstatus_TDC_CL;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = value.split("_")[0];
    jsonCreditCards[1] = value;
    jsonCreditCards[2] = value.split("_")[1];

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,consultarEstatusCLSuccess,null,null);
}


function consultarEstatusCLSuccess(originalRequest){
    var result = originalRequest;
    var columnasActivas = result.tablaActivas_culumnas;
    var columnasHistoricas = result.tablaHistorico_culumnas;
//    var columnasCorrientes = result.contenidoTabla_culumnasTest;
    var filasActivas = result.contenidoTablaActivas_info;
    var filasHistoricas = result.contenidoTablaHistorico_info;
//    var filasCorrientes = result.contenidoTabla_infoTest;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    var estatusTarjeta = result.estatusTarjeta;
    idioma=result.idioma;
    $("#tag_cliente_bloqueo_cl").text($("#numero_cuenta_TDC_CL").val().split("_")[3]);
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    $("#div_carga_espera").addClass("oculto");
    if (codigo!="0") {
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        $("#tabla_consulta_reglas_activas_TDC_CL").empty();
        $("#set_tabla_consulta_reglas_activas_TDC_CL").addClass("oculto");
//        $("#div_tabla_consulta_reglas_corrientes_TDC_CL").empty();
//        $("#set_tabla_consulta_reglas_corrientes_TDC_CL").addClass("oculto");
        $("#tabla_consulta_historial_TDC_CL").empty();
        $("#set_tabla_consulta_historial_TDC_CL").addClass("oculto");
    } else {
//        $("#estatus_TDC_CL").empty();
        cargarIdiomaJSONData_sinc();
//        $("#estatus_TDC_CL").html("<span id='TAG_EST_DESC_" + estatusTarjeta + "'>" + vbtol_props[idioma]["TAG_EST_DESC_" + estatusTarjeta] + "</span>");
//        if (filasActivas.length > 0) {
//            $("#div_datos_regla_TDC_CL").addClass("oculto");
//            $("#div_datos_regla_mensaje_TDC_CL").removeClass("oculto");
        $("#tabla_consulta_reglas_activas_TDC_CL").empty();
        $("#set_tabla_consulta_reglas_activas_TDC_CL").removeClass("oculto");
        // crearTabla_i18n('div_tabla_consulta_reglas_activas_TDC_CL', 'tabla_consulta_reglas_activas_TDC_CL', columnasActivas, filasActivas);
        crearTabla_i18nV2('tabla_consulta_reglas_activas_TDC_CL', columnasActivas, filasActivas,"");
/*
        var oTableActivas = $('#tabla_consulta_reglas_activas_TDC_CL').dataTable({
            "iDisplayLength": 5,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
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

        var oTableActivas = $('#tabla_consulta_reglas_activas_TDC_CL').dataTable({
            "iDisplayLength": 5,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
            "aoColumns": [
                { "sClass": "center" },
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

//        } else {
//            $("#div_tabla_consulta_reglas_activas_TDC_CL").empty();
//            $("#set_tabla_consulta_reglas_activas_TDC_CL").addClass("oculto");
//            $("#div_datos_regla_TDC_CL").removeClass("oculto");
//            $("#div_datos_regla_mensaje_TDC_CL").addClass("oculto");
//        }
//        if ((columnasCorrientes != null) && (filasCorrientes != null)) {
//            $("#div_tabla_consulta_reglas_corrientes_TDC_CL").empty();
//            $("#set_tabla_consulta_reglas_corrientes_TDC_CL").removeClass("oculto");
//            crearTabla('div_tabla_consulta_reglas_corrientes_TDC_CL', 'tabla_consulta_reglas_corrientes_TDC_CL', columnasActivas, filasActivas);
//            var oTableActivas = $('#tabla_consulta_reglas_corrientes_TDC_CL').dataTable({
//                "iDisplayLength": 5,
//                "bJQueryUI": true,
//                "sPaginationType": "full_numbers",
//                "bFilter": false,
//                "bSort": false,
//                "aoColumns": [
//                    { "sClass": "center" },
//                    { "sClass": "center" },
//                    { "sClass": "center" },
//                    { "sClass": "center" }
//                ],
//                "oLanguage": {
//                    "sZeroRecords": noInfo,
//                    "sInfo": "",
//                    "sInfoEmpty": ""
//                }
//            });
//        }
//        if (filasHistoricas.length > 0) {
        $("#tabla_consulta_historial_TDC_CL").empty();
        $("#set_tabla_consulta_historial_TDC_CL").removeClass("oculto");
        // crearTabla_i18n('div_tabla_consulta_historial_TDC_CL', 'tabla_consulta_historial_TDC_CL', columnasHistoricas, filasHistoricas);
        crearTabla_i18nV2( 'tabla_consulta_historial_TDC_CL', columnasHistoricas, filasHistoricas,"");
/*
        var oTableActivas = $('#tabla_consulta_historial_TDC_CL').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
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

        var oTableActivas = $('#tabla_consulta_historial_TDC_CL').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
            "aoColumns": [
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

//        } else {
//            $("#set_tabla_consulta_historial_TDC_CL").addClass("oculto");
//            $("#div_tabla_consulta_historial_TDC_CL").empty();
//        }
    }
}

//function print_MOVIMIENTOS_FACTURAR(){
//
//    var miValue = $("#numero_cuenta_TDC_CL" ).val();
//    $('#numero_cuenta_TDC_CL_select').html($('#numero_cuenta_TDC_CL option:selected').text());
//    $("#numero_cuenta_TDC_CL option[value='"+miValue+"']").attr("selected",true);
//    printPageElement('div_MOVIMIENTOS_FACTURAR');  //Print EDO CUENTA
//
//
//}


function validarFechaJSONData() {
    var url = urlValidarFecha;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#fec_desde_CL").val();
    jsonCreditCards[1] = $("#fec_hasta_CL").val();
    jsonCreditCards[2] = $("#numero_cuenta_TDC_CL").val().split('_')[1];
    jsonCreditCards[3] = $("#numero_cuenta_TDC_CL").val().split('_')[0];
    jsonCreditCards[4] = $("#numero_cuenta_TDC_CL").val().split('_')[2];


    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    sendServiceJSON(url,param,validarFechaJSONDataSuccess,null,null);
}

function validarFechaJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    if (mensaje == "OK") {
        validarLimiteDispJSONData();
        /*crearRegla();   */
    } else {
        $("#div_carga_espera").addClass("oculto");
        if (mensaje=="NO OK"){
            cargarIdiomaJSONData_sinc();
            $("#mensaje_error").empty();
            $("#mensaje_error").html("<span id='error_editando_regla_fecha1'>" + vbtol_props[idioma]["error_editando_regla_fecha"] + "</span>");

        }else{
            if (mensaje=="NO OK DIAS"){
                cargarIdiomaJSONData_sinc();
                $("#mensaje_error").empty();
                $("#mensaje_error").html("<span id='error_editando_regla_fecha2'>" + vbtol_props[idioma]["error_editando_regla_fecha2"] + "</span>");
            }else{
                cargarIdiomaJSONData_sinc();
                $("#tabla1_"+mensaje).addClass("error_campo");
                $("#tabla2_"+mensaje).addClass("error_campo");
                $("#fec_desde_CL_Edit").addClass("error_campo");
                $("#fec_hasta_CL_Edit").addClass("error_campo");
                $("#mensaje_error").empty();
                $("#mensaje_error").html("<span id='error_regla_fecha'>" + vbtol_props[idioma]["error_regla_fecha"] + "</span>");
            }
        }
        $("#div_mensajes_error").fadeIn("slow");

    }
}

function validarLimiteDispJSONData(){
    var url = urlValidarLimite;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_CL").val().split('_')[1];
    jsonCreditCards[1] = $("#numero_cuenta_TDC_CL").val().split('_')[0];
    jsonCreditCards[2] = $("#numero_cuenta_TDC_CL").val().split('_')[2];

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    sendServiceJSON(url,param,validarLimitJSONDataSuccess,null,null);
}

function validarLimitJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    if (mensaje == "LIMIT" || mensaje == "NO SALVIS") {
        crearRegla();
    } else {

        $("#div_carga_espera").addClass("oculto");
        if (mensaje=="NO OK" || mensaje=="NO LIMIT"){
            cargarIdiomaJSONData_sinc();
            $("#mensaje_error").empty();
            $("#mensaje_error").html("<span id='error_validacion_limite'>" + vbtol_props[idioma]["error_validacion_limite"] + "</span>");
        }
        $("#div_mensajes_error").fadeIn("slow");
    }
}




function editarRegla(valor){
    valores=valor;
    $("#div_creditCard_CL").fadeOut("fast");

    if (idioma == "_ve_es") {
        $("#numero_cuenta_TDC_CL_edit").html($("#numero_cuenta_TDC_CL option:selected").text().replace("Cartera N°","<span id='tag_client_CD'>Cartera N°</span>"));
    }else{
        $("#numero_cuenta_TDC_CL_edit").html($("#numero_cuenta_TDC_CL option:selected").text().replace("Client ID N°","<span id='tag_client_CD'>Client ID N°</span>"));
    }

    proximoDiaHabilJSONData();

    $("#fec_desde_CL_Edit").val(valor.split("|")[4]);
    $("#fec_hasta_CL_Edit").val(valor.split("|")[5]);



    var fdesdeFormat= $("#fec_desde_CL_Edit").val().split("/")[1]
        +"/"+$("#fec_desde_CL_Edit").val().split("/")[0]
        +"/"+$("#fec_desde_CL_Edit").val().split("/")[2]  ;
    var Fdesde = Date.parse(fdesdeFormat);

    var fhastaFormat= fechaHoy.split("/")[1]
        +"/"+ fechaHoy.split("/")[0]
        +"/"+fechaHoy.split("/")[2] ;
    var Factual = Date.parse(fhastaFormat);


    if (Factual<Fdesde){
        $("#fec_hasta_CL_Edit").datepicker("option", "maxDate", sumarDiaFecha( $("#fec_desde_CL_Edit").val(),30));
        $("#fec_hasta_CL_Edit").datepicker("option", "minDate", sumarDiaFecha( $("#fec_desde_CL_Edit").val(),1));
    }
    else {
        var dias=restarFechas($("#fec_desde_CL_Edit").val(),fechaHoy);
        $("#fec_hasta_CL_Edit").datepicker("option", "minDate", sumarDiaFecha( $("#fec_desde_CL_Edit").val(),dias+1));
        $("#fec_hasta_CL_Edit").datepicker("option", "maxDate", sumarDiaFecha(fechaHoy,30-dias));
    }



//    $("#tag_status_CL_Edit").text(valor.split("|")[6]);
    cargarIdiomaJSONData_sinc();
    $("#tag_status_CL_Edit").html("<label id='EDIT_TAG_EST_"+valor.split("|")[7]+"' class='datos'>"+vbtol_props[idioma]["EDIT_TAG_EST_"+valor.split("|")[7]]+"</label>");

    $("#fec_desde_CL_Edit").removeClass("error_campo");
    $("#fec_hasta_CL_Edit").removeClass("error_campo");
    if (valor.split("|")[7]=="E"){
        $("#fec_desde_CL_Edit").attr("disabled",true);
    }else{
        $("#fec_desde_CL_Edit").attr("disabled",false);
    }
    $("#div_creditCard_CL_Edit").fadeIn("slow");

}



var editarReglaJSONData = function(){
    mainValidationMethods("editarDesbloqueoTarjeta");
}



$("#volver_editar_regla").click(function(){

    $("#div_creditCard_CL_Edit").fadeOut("fast");
    $("#numero_cuenta_TDC_CL_edit").text("");
    $("#fec_desde_CL_Edit").val("");
    $("#fec_hasta_CL_Edit").val("");
    $("#tag_status_CL_Edit").text("");
    $("#div_creditCard_CL").fadeIn("slow");
});

function editarReglaCreditCardJSONData(){
    var url = urlEditarRegla;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = valores.split("|")[0];
    jsonCreditCards[1] = valores.split("|")[1];
    jsonCreditCards[2] = valores.split("|")[2];
    jsonCreditCards[3] = valores.split("|")[3];
    jsonCreditCards[4] = $("#fec_desde_CL_Edit").val();
    jsonCreditCards[5] = $("#fec_hasta_CL_Edit").val();

    $("#div_carga_espera").removeClass("oculto");
    $("#volver_editar_regla").click();


    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    sendServiceJSON_sinc(url,param,editarReglaJSONDataSuccess,null,null);

}


function editarReglaJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;

    $("#div_creditCard_CL_Edit").attr("style", "display: none");
    $("#numero_cuenta_TDC_CL").trigger('change');

    cargarIdiomaJSONData_sinc();
    $("#mensaje_error").empty();
    if (mensaje == "OK") {
        popupAlert((vbtol_props[idioma]["tag_editando_regla"] ), "Accept");
        //$("#mensaje_error").html("<span id='tag_editando_regla'>" + vbtol_props[idioma]["tag_editando_regla"] + "</span>");
    }else{
        $("#mensaje_error").html("<span id='tag_error_editando_regla'>" + vbtol_props[idioma]["tag_error_editando_regla"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
    }



    $("#div_carga_espera").addClass("oculto");

}



function validarFechaEditJSONData  (){
    var url = urlValidarFechaEdit;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#fec_desde_CL_Edit").val();
    jsonCreditCards[1] = $("#fec_hasta_CL_Edit").val();
    jsonCreditCards[2] = valores.split('|')[1];
    jsonCreditCards[3] = valores.split('|')[2];
    jsonCreditCards[4] = valores.split('|')[3];
    jsonCreditCards[5] = valores.split('|')[0];
    jsonCreditCards[6] = "";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON_sinc(url,param,validarFechaEditJSONDataSuccess,null,null);

}


function validarFechaEditJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    $("#fec_desde_CL_Edit").removeClass("error_campo");
    $("#fec_hasta_CL_Edit").removeClass("error_campo");
    if (mensaje == "OK") {
        $("#div_carga_espera").addClass("oculto");
        if (idioma == "_ve_es") {
            popupConfirm("<span id='tag_popup_edit'>¿Está seguro que desea modificar el periodo de activación de la tarjeta de crédito?</span>","Aceptar","Cancelar",editarReglaJSONData);
        } else {
            popupConfirm("<span id='tag_popup_edit'>Are you sure you want to change the period of activation of the credit card?</span>","Accept","Cancel",editarReglaJSONData);
        }
    } else {
        $("#div_carga_espera").addClass("oculto");
        if (mensaje=="NO OK DIA"){
            cargarIdiomaJSONData_sinc();
            $("#mensaje_error").empty();
            $("#mensaje_error").html("<span id='error_editando_regla_fecha1'>" + vbtol_props[idioma]["error_editando_regla_fecha"] + "</span>");

        }else{

            if (mensaje=="NO OK DIAS"){
                cargarIdiomaJSONData_sinc();
                $("#mensaje_error").empty();
                $("#mensaje_error").html("<span id='error_editando_regla_fecha2'>" + vbtol_props[idioma]["error_editando_regla_fecha2"] + "</span>");

            }else{
                cargarIdiomaJSONData_sinc();
                $("#fec_desde_CL_Edit").addClass("error_campo");
                $("#fec_hasta_CL_Edit").addClass("error_campo");
                $("#mensaje_error").empty();
                $("#mensaje_error").html("<span id='error_regla_fecha'>" + vbtol_props[idioma]["error_regla_fecha"] + "</span>");
            }
        }
        $("#div_mensajes_error").fadeIn("slow");

    }
}

function proximoDiaHabilJSONData(){
    var url = urlProximoDiaHabil;
    var param={};
    var jsonCreditCards=[];

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON_sinc(url,param,proximoDiaHabilSuccess,null,null);

}

function proximoDiaHabilSuccess(originalRequest){
    var result = originalRequest;
    var fecha = originalRequest.mensaje;
    // $("#fec_desde_CL").datepicker("option", "minDate", fecha);
    // $("#fec_desde_CL_Edit").datepicker("option", "minDate", fecha);
    // $("#fec_hasta_CL_Edit").datepicker("option", "minDate",sumarDiaFecha(fecha,1));
    $("#fec_desde_CL").datepicker("option", "minDate", 0);
    $("#fec_desde_CL_Edit").datepicker("option", "minDate", 0);
    $("#fec_hasta_CL_Edit").datepicker("option", "minDate",0);
//    $("#fec_hasta_CL_Edit").datepicker("option", "minDate",fecha);
}



var eliminarReglaValidation = function(){
    mainValidationMethods("eliminarDesbloqueoTarjeta");
}

function eliminarReglaMain (id){
    valores=id;
    if (idioma == "_ve_es") {
        popupConfirm("<span id='tag_popup_delete'>¿Está seguro que desea eliminar la activación programada para <br>el </span>'"+valores.split("|")[5]+"'<span id='tag_popup_delete1'> al </span>'"+valores.split("|")[6]+"' ?","Aceptar","Cancelar",eliminarReglaValidation);
    } else {
        popupConfirm("<span id='tag_popup_delete'>Are you sure you want to delete the scheduled activation <br>from </span>'"+valores.split("|")[5]+"'<span id='tag_popup_delete1'> to </span>'"+valores.split("|")[6]+"' ?","Accept","Cancel",eliminarReglaValidation);
    }
}


function editDays(date) {

    for (var i = 0; i < disabledDates.length; i++) {
        if (new Date(disabledDates[i][0]).toString() == date.toString()) {
            return [true, "feriado",disabledDates[i][1]];
        }else{
            var day = date.getDay();
            if ( day == 6 || day == 0) {
                return [true, "fin_semana","WEEKEND"];
            }
        }
    }
    return [true];
}

function editDays_hasta(date) {

    for (var i = 0; i < disabledDates.length; i++) {
        if (new Date(disabledDates[i][0]).toString() == date.toString()) {
            return [false, "feriado",disabledDates[i][1]];
        }else{
            var day = date.getDay();
            if ( day == 6 || day == 0) {
                return [false, "fin_semana","WEEKEND"];
            }
        }
    }
    return [true];
}


function cargarFeriados(){
    var url = urlCargarFeriados;
    var param={};
    var jsonCreditCards=[];
    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON(url,param,cargarFeriadosSuccess,null,null);
}


function cargarFeriadosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    disabledDates = result.feriados;

}
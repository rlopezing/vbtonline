//var urlCargar_TDC_CL="CreditCards_cargarTarjetasCL.action";
var urlBackOfficeConsultarTarjetas="BackOffice_consultarTarjetas.action";
var urlConsultarEstatus_TDC_BO="BackOffice_cargarEstatusTDCCL.action";
var urlValidarFechaBO="CreditCards_validarFechaBO.action";
var urlCrearReglaBO_TDC_CL="CreditCards_crearReglaTDCCL.action";
var urlEliminarRegla_BO="BackOffice_eliminarReglaBO.action";


$(document).ready(function() {

    $("#fec_desde_CL_bo").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $("#fec_hasta_CL_bo").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    //$("#fec_desde_CL_bo").datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true });

    $("#numero_cuenta_TDC_CL_BO").chosen({ search_contains: true });

    $("#fec_desde_CL_bo").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        minDate: new Date(),
        defaultDate: 0,
        //beforeShowDay: editDays,
        changeYear: true,
        onClose: function (selectedDate) {
            $("#fec_hasta_CL_bo").datepicker("option", "minDate", selectedDate);
        }

    });

    $("#fec_hasta_CL_bo").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        //beforeShowDay: editDays,
        minDate: new Date(),
        changeYear: true
    });


    $("#fec_desde_CL_Edit_bo").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        defaultDate: 0,
        //beforeShowDay: editDays,
        maxDate: "+1y",
        minDate: new Date(),
        changeYear: true   ,
        onClose: function (selectedDate) {
            $("#fec_hasta_CL_Edit_bo").datepicker("option", "minDate", selectedDate);
            /*$("#fec_hasta_CL_Edit_bo").datepicker("option", "maxDate", sumarDiaFecha(selectedDate,365));*/
        }
    });


    $("#fec_hasta_CL_Edit_bo").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        //beforeShowDay: editDays,
        minDate: new Date(),
        changeYear: true
    });




    $("#limpiarBloqueoTarjetaBO").click(function(){
        $("#carteraFiltroTarjetasBO").val("");
        $("#div_result_bloqueo_bo").addClass("oculto");
        $("#tag_estatus_bloqueo_bo").text("");
        $("#numero_cuenta_TDC_CL_BO").html("");
        $("#tag_cta_bloqueo_bo").text("");
        $("#fec_desde_CL_bo").val("");
        $("#fec_hasta_CL_bo").val("");
        $("#tag_cliente_bloqueo_bo").text("");
        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
        $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");
        $("#carteraExacto_TDC_CL").attr("checked",false);
        $("#div_creditCard_CL_Edit_BO").fadeOut("fast");

    });

    $("#buscarBloqueoTarjetaBO").click(function(){

        $("#div_carga_espera").removeClass("oculto");
        $("#div_result_bloqueo_bo").addClass("oculto");
        $("#tag_cliente_bloqueo_bo").text("");
        $("#tag_estatus_bloqueo_bo").text("");
        buscarTarjetasBOJSONData();
    });


    $("#crear_regla_bo").click(function(){
        var mensaje="";
        $("#div_result_bloqueo_bo .requeridoCrearRegla").each(function(){
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

        if(mensaje==""){
            if(!isDate($( "#fec_desde_CL_bo").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fec_desde_CL_bo").addClass("error_campo");
            }else if(!isDate($( "#fec_hasta_CL_bo").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta";
                $("#fec_hasta_CL_bo").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fec_desde_CL_bo").val().split("/")[1]
                    +"/"+$("#fec_desde_CL_bo").val().split("/")[0]
                    +"/"+$("#fec_desde_CL_bo").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fec_hasta_CL_bo").val().split("/")[1]
                    +"/"+$("#fec_hasta_CL_bo").val().split("/")[0]
                    +"/"+$("#fec_hasta_CL_bo").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"The date range is not allowed. Remember: The From and To dates should not be the same. From date must be less than the To date."+"<br>";
                    else
                        mensaje=mensaje+"El rango de fecha no es permitido. Recuerde: La fecha Desde y Hasta no deben ser iguales. La fecha Desde debe ser menor a la fecha Hasta."+"<br>";
                    $("#fec_desde_CL_bo").addClass("error_campo");
                    $("#fec_hasta_CL_bo").addClass("error_campo");

                }else{
                    $("#fec_desde_CL_bo").removeClass("error_campo");
                    $("#fec_hasta_CL_bo").removeClass("error_campo");
                }
            }
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            //rearReglaBOJSONData();
            validarFechaBOJSONData();
        }

    });



    $("#volver_editar_regla_bo").click(function(){

        $("#div_creditCard_CL_Edit_BO").fadeOut("fast");
        $("#numero_cuenta_TDC_CL_edit_bo").text("");
        $("#fec_desde_CL_Edit_bo").val("");
        $("#fec_hasta_CL_Edit_bo").val("");
        $("#tag_status_CL_Edit_bo").text("");
        $("#div_bloqueoTarjetas_BO").fadeIn("slow");
    });



    $("#edit_regla_bo").click(function(){
        var mensaje="";
        $("#div_datos_regla_TDC_CL_Edit_BO .requeridoCrearReglaEdit").each(function(){
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


        if(!isDate($( "#fec_desde_CL_Edit_bo").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fec_desde_CL_Edit_bo").addClass("error_campo");
        }else if(!isDate($( "#fec_hasta_CL_Edit_bo").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"To date is not correct";
            else
                mensaje=mensaje+"La fecha Hasta no es correcta";
            $("#fec_hasta_CL_Edit_bo").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fec_desde_CL_Edit_bo").val().split("/")[1]
                +"/"+$("#fec_desde_CL_Edit_bo").val().split("/")[0]
                +"/"+$("#fec_desde_CL_Edit_bo").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fec_hasta_CL_Edit_bo").val().split("/")[1]
                +"/"+$("#fec_hasta_CL_Edit_bo").val().split("/")[0]
                +"/"+$("#fec_hasta_CL_Edit_bo").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The date range is not allowed. Remember: The From and To dates should not be the same. From date must be less than the To date."+"<br>";
                else
                    mensaje=mensaje+"El rango de fecha no es permitido. Recuerde: La fecha Desde y Hasta no deben ser iguales. La fecha Desde debe ser menor a la fecha Hasta."+"<br>";
                $("#fec_desde_CL_Edit_bo").addClass("error_campo");
                $("#fec_hasta_CL_Edit_bo").addClass("error_campo");

            }else{
                $("#fec_desde_CL_Edit_bo").removeClass("error_campo");
                $("#fec_hasta_CL_Edit_bo").removeClass("error_campo");
            }
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            validarFechaEditBOJSONData();
        }

    });

    $("#btn_confirm_si_bo").click(function(){
        editarReglaCreditCardBOJSONData();
        $("#poppup_message_bo").addClass("oculto");
    });

    $("#btn_confirm_no_bo").click(function(){
        $("#poppup_message_bo").addClass("oculto");
    });






});



var eliminarReglaBO = function(){
    eliminarReglaBOJSONData(valores.split("|")[1]);
}



var editarReglaValidaBO = function(){

    editarReglaCreditCardBOJSONData();
}



function validarFechaEditBOJSONData  (){
    var url = urlValidarFechaEdit;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#fec_desde_CL_Edit_bo").val();
    jsonCreditCards[1] = $("#fec_hasta_CL_Edit_bo").val();
    jsonCreditCards[2] = valores.split('|')[1];
    jsonCreditCards[3] = valores.split('|')[2];
    jsonCreditCards[4] = valores.split('|')[3];
    jsonCreditCards[5] = valores.split('|')[0];
    jsonCreditCards[6] = "BO";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON_sinc(url,param,validarFechaEditBOJSONDataSuccess,null,null);

}


function validarFechaEditBOJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    $("#fec_desde_CL_Edit_bo").removeClass("error_campo");
    $("#fec_hasta_CL_Edit_bo").removeClass("error_campo");
    if (mensaje == "OK") {
        $("#div_carga_espera").addClass("oculto");


        $("#poppup_message_bo").removeClass("oculto");


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




function buscarTarjetasBOJSONData(){
    var url = urlBackOfficeConsultarTarjetas;
    var param={};

    var jsonParametrosString=[];

    jsonParametrosString[0] ="'"+$("#carteraFiltroTarjetasBO").val()+"'";

    if($("#carteraExacto_TDC_CL").is(':checked')){
        jsonParametrosString[1] ="SI";
    }else{
        jsonParametrosString[1] ="NO";
    }


    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,buscarTarjetasBOSuccess,null,null);
}


function buscarTarjetasBOSuccess(originalRequest){
    var result = originalRequest;
    var tarjetasCL = result.tarjetasCL;
    var codigo = result.mensaje;
    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto");
    $("#div_tabla_consulta_reglas_activas_TDC_CL_BO").empty();
    $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
    $("#div_tabla_consulta_historial_TDC_CL_BO").empty();
    $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");

    if(codigo=="OK" && tarjetasCL!=null){
        $("#div_result_bloqueo_bo").removeClass("oculto");
        //$("#fec_desde_CL_bo").val(result.fechaHoy);
        cargar_selectPersonal("numero_cuenta_TDC_CL_BO", tarjetasCL,"Seleccione","-2");

        $('#numero_cuenta_TDC_CL_BO').trigger("chosen:updated");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("No se encontraton resultados");
        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
        $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");
        $("#div_mensajes_error").fadeIn("slow");
    }

}


function cargarDatosBloqueo(valor){
    $("#div_carga_espera").removeClass("oculto");

    var url = urlConsultarEstatus_TDC_BO;
    var param={};
    var jsonCreditCards=[];

    $("#fec_desde_CL_bo").val("");
    $("#fec_hasta_CL_bo").val("");

    if (valor!="-2"){
        $("#tag_cliente_bloqueo_bo").text(valor.split("_")[3]);

        if (Trim(valor.split("_")[5])==""){
            $("#tag_estatus_bloqueo_bo").text("Activa");
            $("#crear_regla_bo").removeClass("oculto");
        }else{
            /*if (Trim(valor.split("_")[4])=="Q"){
                $("#crear_regla_bo").removeClass("oculto");
            }else{
                $("#crear_regla_bo").addClass("oculto");
            }   */
            $("#tag_estatus_bloqueo_bo").text(valor.split("_")[5]);

        }




        jsonCreditCards[0] = valor.split("_")[0];
        jsonCreditCards[1] = valor;
        jsonCreditCards[2] = valor.split("_")[1];
        jsonCreditCards[3] = "'"+valor.split("_")[1]+ "'";

        param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

        sendServiceJSON(url,param,cargarDatosBloqueoSuccess,null,null);
    }
}


function cargarDatosBloqueoSuccess(originalRequest){
    var result = originalRequest;
    var columnasActivas = result.tablaActivas_culumnas;
    var columnasHistoricas = result.tablaHistorico_culumnas;
    var filasActivas = result.contenidoTablaActivas_info;
    var filasHistoricas = result.contenidoTablaHistorico_info;
    var codigo = result.codigo;
    var mensaje = result.mensaje;
    var estatusTarjeta = result.estatusTarjeta;

    $("#div_carga_espera").addClass("oculto");
    noInfo="No hay informaci&oacute;n disponible";
    if (codigo!="0") {
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        $("#div_tabla_consulta_reglas_activas_TDC_CL_BO").empty();
        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
        $("#div_tabla_consulta_historial_TDC_CL_BO").empty();
        $("#set_tabla_consulta_historial_TDC_CL_BO").addClass("oculto");
    } else {

        cargarIdiomaJSONData_sinc();
        $("#div_tabla_consulta_reglas_activas_TDC_CL_BO").empty();
        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").removeClass("oculto");
        // crearTabla_i18n('div_tabla_consulta_reglas_activas_TDC_CL_BO', 'tabla_consulta_reglas_activas_TDC_CL_BO', columnasActivas, filasActivas);
        crearTablaV4('div_tabla_consulta_reglas_activas_TDC_CL_BO', 'tabla_consulta_reglas_activas_TDC_CL_BO', columnasActivas, filasActivas);
/*        var oTableActivas = $('#tabla_consulta_reglas_activas_TDC_CL_BO').dataTable({
            "iDisplayLength": 5,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center" },
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
        });  */
        var oTableActivas = $('#tabla_consulta_reglas_activas_TDC_CL_BO').dataTable({
            "iDisplayLength": 5,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center" },
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
        $("#div_tabla_consulta_historial_TDC_CL_BO").empty();
        $("#set_tabla_consulta_historial_TDC_CL_BO").removeClass("oculto");
        // crearTabla_i18n('div_tabla_consulta_historial_TDC_CL_BO', 'tabla_consulta_historial_TDC_C_BOL', columnasHistoricas, filasHistoricas);
        crearTablaV4('div_tabla_consulta_historial_TDC_CL_BO', 'tabla_consulta_historial_TDC_C_BOL', columnasHistoricas, filasHistoricas);
/*        var oTableActivas = $('#tabla_consulta_historial_TDC_C_BOL').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
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
        });*/
        var oTableActivas = $('#tabla_consulta_historial_TDC_C_BOL').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
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
//
    }
}





//function crearReglaBOJSONData() {
//    var url = urlCrearRegla_TDC_CL;
//    var param={};
//    var jsonCreditCards=[];
//
//    jsonCreditCards[0] = $("#numero_cuenta_TDC_CL_BO").val();
//    jsonCreditCards[1] = $("#fec_desde_CL").val();
//    jsonCreditCards[2] = $("#fec_hasta_CL").val();
//    jsonCreditCards[3] ="P";
//
//    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});
//
//    $("#div_carga_espera").removeClass("oculto");
//
//    sendServiceJSON_sinc(url,param,crearReglaBOJSONDataSuccess,null,null);
//
//}

function crearReglaBOJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var respuesta = originalRequest.estatusTarjeta;
    var tarjetasCL = result.tarjetasCL;

    $("#div_carga_espera").addClass("oculto");
    if (respuesta == "SUCCESS") {
        if (idioma == "_ve_es") {
            popupAlert("<span id='tag_popup_add'>El periodo de activación fue registrado satisfactoriamente</span>", "Aceptar");
        } else {
            popupAlert("<span id='tag_popup_add'>The activation period was successfully registered.</span>", "Accept");
        }
        console.log('cargar_selectPersonal');

        cargarEstatusCLBO($("#numero_cuenta_TDC_CL_BO").val());
    } else {
        $("#div_carga_espera").addClass("oculto");
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").empty();
        if(mensaje="NO OK")
            $("#mensaje_error").html("<span id='tag_error_creando_regla'>" + vbtol_props[idioma]["tag_error_creando_regla_no_ok"] + "</span>");
        else
            $("#mensaje_error").html("<span id='tag_error_creando_regla'>" + vbtol_props[idioma]["tag_error_creando_regla"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
    }
}


function validarFechaBOJSONData() {
    var url = urlValidarFechaBO;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#fec_desde_CL_bo").val();
    jsonCreditCards[1] = $("#fec_hasta_CL_bo").val();
    jsonCreditCards[2] = $("#numero_cuenta_TDC_CL_BO").val().split('_')[1];
    jsonCreditCards[3] = $("#numero_cuenta_TDC_CL_BO").val().split('_')[0];
    jsonCreditCards[4] = $("#numero_cuenta_TDC_CL_BO").val().split('_')[2];


    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON_sinc(url,param,validarFechaBOJSONDataSuccess,null,null);

}

function validarFechaBOJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    if (mensaje == "OK") {
        // crearRegla();
        crearReglaBOJSONData();
    } else {
        $("#div_carga_espera").addClass("oculto");
        if (mensaje=="NO OK DIAS"){
            cargarIdiomaJSONData_sinc();
            $("#mensaje_error").empty();
            $("#mensaje_error").html("<span id='error_creando_regla_fecha'>" + vbtol_props[idioma]["error_creando_regla_fecha"] + "</span>");

        }else{
            cargarIdiomaJSONData_sinc();
            $("#tabla1_"+mensaje).addClass("error_campo");
            $("#tabla2_"+mensaje).addClass("error_campo");
            $("#mensaje_error").empty();
            $("#mensaje_error").html("<span id='error_regla_fecha'>" + vbtol_props[idioma]["error_regla_fecha"] + "</span>");
        }
        $("#div_mensajes_error").fadeIn("slow");

    }
}





function crearReglaBOJSONData() {
    var url = urlCrearReglaBO_TDC_CL;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_CL_BO").val();
    jsonCreditCards[1] = $("#fec_desde_CL_bo").val();
    jsonCreditCards[2] = $("#fec_hasta_CL_bo").val();
    jsonCreditCards[3] = "P";

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON_sinc(url,param,crearReglaBOJSONDataSuccess,null,null);

}

/*function crearReglaBOJSONDataSuccess(originalRequest){*
    var result = originalRequest;
    var creada = originalRequest.estatusTarjeta;

    $("#div_carga_espera").addClass("oculto");
    if (creada == "SUCCESS") {
        $("#numero_cuenta_TDC_CL_BO").trigger('change');

        if (idioma == "_ve_es") {
            popupAlert("<span id='tag_popup_add'>El fecha de bloqueo fue registrado satisfactoriamente</span>", "Aceptar");
        } else {
            popupAlert("<span id='tag_popup_add'>The activation period was successfully registered.</span>", "Accept");
        }

    } else {
        $("#div_carga_espera").addClass("oculto");
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").empty();
        $("#mensaje_error").html("<span id='tag_error_creando_regla'>" + vbtol_props[idioma]["tag_error_creando_regla"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
    }
}*/



function proximoDiaHabilBOJSONData(){
    var url = urlProximoDiaHabil;
    var param={};
    var jsonCreditCards=[];

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON_sinc(url,param,proximoDiaHabilBOSuccess,null,null);

}

function proximoDiaHabilBOSuccess(originalRequest){
    var result = originalRequest;
    var fecha = originalRequest.mensaje;
    $("#fec_desde_CL_bo").datepicker("option", "minDate", fecha);
//    $("#fec_hasta_CL_Edit").datepicker("option", "minDate",fecha);
}

function eliminarReglaMainBO (id){
    valores=id;
    popupConfirm("<span>¿Está seguro que desea eliminar la activación programada para <br>el </span>'"+valores.split("|")[5]+"'<span> al </span>'"+valores.split("|")[6]+"' ?","Aceptar","Cancelar",eliminarReglaBO);

}

function eliminarReglaBOJSONData(idRegla) {
    var url = urlEliminarRegla_BO;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = idRegla;
    jsonCreditCards[1] = $("#numero_cuenta_TDC_CL_BO").val();

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});

    sendServiceJSON_sinc(url,param,eliminarReglaBOJSONDataSuccess,null,null);

}

function eliminarReglaBOJSONDataSuccess(originalRequest) {
    var result = originalRequest;
    var eliminada = result.estatusTarjeta;
    if (eliminada == "SUCCESS") {
        if (idioma == "_ve_es") {
            popupAlert("El periodo de activación fue eliminado satisfactoriamente.", "Aceptar");
        } else {
            popupAlert("The activation period was successfully removed.", "Accept");
        }
        cargarEstatusCLBO($("#numero_cuenta_TDC_CL_BO").val());
    } else {
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").empty();
        $("#mensaje_error").html("<span id='error_eliminando_regla'>" + vbtol_props[idioma]["error_eliminando_regla"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
    }
}


function cargarEstatusCLBO(value){
//    $("#estatus_TDC_CL").empty();
    if(value!="-2") {
        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
        cargarDatosBloqueo(value);
    }else{

        $("#set_tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
        $("#div_tabla_consulta_reglas_activas_TDC_CL_BO").empty();
        $("#tabla_consulta_reglas_activas_TDC_CL_BO").addClass("oculto");
        $("#set_tabla_consulta_historial_TDC_CL_BO").empty();
//        $("#set_tabla_consulta_reglas_corrientes_TDC_CL").addClass("oculto");
//        $("#div_tabla_consulta_reglas_corrientes_TDC_CL").empty();
    }
}



function editarReglaBO(valor){
    valores=valor;
    $("#div_bloqueoTarjetas_BO").fadeOut("fast");


    $("#numero_cuenta_TDC_CL_edit_bo").html($("#numero_cuenta_TDC_CL_BO option:selected").text().replace("Cartera N°","<span id='tag_client_CD'>Cartera N°</span>"));


    $("#fec_desde_CL_Edit_bo").val(valor.split("|")[4]);
    $("#fec_hasta_CL_Edit_bo").val(valor.split("|")[5]);



    var fdesdeFormat= $("#fec_desde_CL_Edit_bo").val().split("/")[1]
        +"/"+$("#fec_desde_CL_Edit_bo").val().split("/")[0]
        +"/"+$("#fec_desde_CL_Edit_bo").val().split("/")[2]  ;
    var Fdesde = Date.parse(fdesdeFormat);

    var fhastaFormat= fechaHoy.split("/")[1]
        +"/"+ fechaHoy.split("/")[0]
        +"/"+fechaHoy.split("/")[2] ;
    var Factual = Date.parse(fhastaFormat);

    console.log("fechaHoy     --"  + fechaHoy );
    console.log("fdesdeFormat --"  + fdesdeFormat );
    console.log("fhastaFormat --"  + fhastaFormat );
    console.log("Fdesde       --"  + Fdesde);
    console.log("Factual      -- " + Factual);


    if (Factual<Fdesde){
        /*$("#fec_hasta_CL_Edit_bo").datepicker("option", "maxDate", sumarDiaFecha( $("#fec_hasta_CL_Edit_bo").val(),30));*/
        $("#fec_hasta_CL_Edit_bo").datepicker("option", "minDate", sumarDiaFecha( $("#fec_hasta_CL_Edit_bo").val(),1));
    }
    else {
        var dias=restarFechas($("#fec_desde_CL_Edit_bo").val(),fechaHoy);
        $("#fec_hasta_CL_Edit_bo").datepicker("option", "minDate", sumarDiaFecha( $("#fec_desde_CL_Edit_bo").val(),dias+1));
        /*$("#fec_hasta_CL_Edit_bo").datepicker("option", "maxDate", sumarDiaFecha(fechaHoy,30-dias));*/
    }




//    $("#tag_status_CL_Edit").text(valor.split("|")[6]);
    cargarIdiomaJSONData_sinc();
    $("#tag_status_CL_Edit_bo").html("<label id='EDIT_TAG_EST_"+valor.split("|")[7]+"' class='datos'>"+vbtol_props[idioma]["EDIT_TAG_EST_"+valor.split("|")[7]]+"</label>");

    $("#fec_desde_CL_Edit_bo").removeClass("error_campo");
    $("#fec_hasta_CL_Edit").removeClass("error_campo");
    if (valor.split("|")[7]=="E"){
        $("#fec_desde_CL_Edit_bo").attr("disabled",true);
    }else{
        $("#fec_desde_CL_Edit_bo").attr("disabled",false);
    }
    $("#div_creditCard_CL_Edit_BO").fadeIn("slow");

}





function editarReglaCreditCardBOJSONData(){
    var url = urlEditarRegla;
    var param={};
    var jsonCreditCards=[];

    jsonCreditCards[0] = valores.split("|")[0];
    jsonCreditCards[1] = valores.split("|")[1];
    jsonCreditCards[2] = valores.split("|")[2];
    jsonCreditCards[3] = valores.split("|")[3];
    jsonCreditCards[4] = $("#fec_desde_CL_Edit_bo").val();
    jsonCreditCards[5] = $("#fec_hasta_CL_Edit_bo").val();

    $("#div_carga_espera").removeClass("oculto");



    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    sendServiceJSON_sinc(url,param,editarReglaBOJSONDataSuccess,null,null);

}


function editarReglaBOJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    $("#volver_editar_regla_bo").click();
    //$("#div_creditCard_CL_Edit").attr("style", "display: none");
    $("#numero_cuenta_TDC_CL_BO").trigger('change');
    cargarIdiomaJSONData_sinc();
    $("#mensaje_error").empty();
    if (mensaje == "OK") {
        $("#mensaje_error").html("<span id='tag_editando_regla'>" + vbtol_props[idioma]["tag_editando_regla"] + "</span>");
    }else{
        $("#mensaje_error").html("<span id='tag_error_editando_regla'>" + vbtol_props[idioma]["tag_error_editando_regla"] + "</span>");
    }

    $("#div_mensajes_error").fadeIn("slow");

    $("#div_carga_espera").addClass("oculto");

}




var urlConsultarBloqueos_ColBlo="TimeDeposits_consultarBloqueos.action";
var urlColocacionesCargar_ColBlo="TimeDeposits_cargarColocacionesBloqueos.action";


var idioma="";
var noInfo="";

$(document).ready(function(){


    $( "#fechaDesdeFiltro_ColBlo").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_ColBlo").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    $( "#fechaDesdeFiltro_ColBlo").blur(function(){
        if(!isDate($( "#fechaDesdeFiltro_ColBlo").val())&& !Empty($( "#fechaDesdeFiltro_ColBlo").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaHastaFiltro_ColBlo").blur(function(){
        if(!isDate($( "#fechaHastaFiltro_ColBlo").val())&& !Empty($( "#fechaHastaFiltro_ColBlo").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaDesdeFiltro_ColBlo" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_ColBlo" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});

    $("#colocaciones_numero_cuenta_ColBlo").change(function(){
        consultarBloqueosTransJSONData();
    });


    $("#volverASaldos").click(function(){
        volverASaldos();
    });


    $("#consultar_ColBlo").click(function(){
        var mensaje="";
        $(".requerido_ColBlo").each(function(){
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
        if($("#buscar_ColBlo").val()=="rangoFechas")
        {
            $(".requeridoFecha_ColBlo").each(function(){
                if(Trim($("#"+this.id).val())==""){
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



            if(!isDate($( "#fechaDesdeFiltro_ColBlo").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltro_ColBlo").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltro_ColBlo").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltro_ColBlo").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltro_ColBlo").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltro_ColBlo").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltro_ColBlo").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltro_ColBlo").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltro_ColBlo").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltro_ColBlo").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltro_ColBlo").addClass("error_campo");
                    $("#fechaHastaFiltro_ColBlo").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltro_ColBlo").removeClass("error_campo");
                    $("#fechaHastaFiltro_ColBlo").removeClass("error_campo");
                }
            }

        }


        if(mensaje!=""){
            $("#colocaciones_div_tabla_consulta_montoBloqueado").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#colocaciones_div_tabla_consulta_montoBloqueado").fadeIn("fast");
            consultarBloqueosTransJSONData();
        }

    });



});

function detalleBloqueo(cuenta){
    $("#colocaciones_numero_cuenta_ColBlo").val(cuenta);
    var opcion= "BLOQUEOS_COLOCACIONES";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldo").attr("style","display: ");
    consultarBloqueosDesdeSaldosJSONData(cuenta);
}

function volverASaldos(){
    var opcion= "SALDOS_COLOCACIONES";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldo").attr("style","display: none");
}

function limpiarColocacionesBA(valor){
    var mensaje="";
    if(valor=="-2"){
        $("#fechaHastaFiltro_ColBlo").val("");
        $("#fechaDesdeFiltro_ColBlo").val("");
        $("#fechas_ColBlo").attr("style","display: none");
        $("#buscar_ColocacionesBloqueos").val("vigentes");
        $("#colocaciones_div_tabla_consulta_montoBloqueado").empty();
    }else{
        $("#colocaciones_div_tabla_consulta_montoBloqueado").empty();
        if($("#buscar_ColocacionesBloqueos").val()=="rangoFechas")
        {
            $(".requeridoFecha_ColBlo").each(function(){
                if(Trim($("#"+this.id).val())==""){
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
        }

        if(mensaje!=""){
            $("#colocaciones_div_tabla_consulta_montoBloqueado").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#colocaciones_div_tabla_consulta_montoBloqueado").fadeIn("fast");
            consultarBloqueosTransJSONData();
        }
    }


}


function BlockedAmountsColocacionesInfoPaginaJSONData(){
    var url = urlColocacionesCargar_ColBlo;
    var param={};
    $("#colocaciones_div_tabla_consulta_montoBloqueado").empty();
    $("#fechaHastaFiltro_ColBlo").val("");
    $("#fechaDesdeFiltro_ColBlo").val("");
    $("#fechas_ColBlo").attr("style","display: none");
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BlockedAmountsColocacionesInfoSuccess,null,null);
}


function BlockedAmountsColocacionesInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var colocaciones = result.colocaciones;
    var search = result.search_ColBlo;
    var fecha = result.fechaCierre;
    var codigo = result.codigo;
    idioma = result.idioma;

//    if(colocaciones == "" || colocaciones == null) {
//        $("#div_colocacionesBloqueos").fadeOut("fast");
//        $("#div_noPoseeColocacionesBloqueos").fadeIn("fast");
//    }else{
//        $("#div_colocacionesBloqueos").fadeIn("fast");
//        $("#div_noPoseeColocacionesBloqueos").fadeOut("fast");
//    }

    if(codigo=="0" && colocaciones!=null){
        $("#div_colocacionesBloqueos").attr("style","display: ");
        $("#div_noPoseeColocacionesBloqueos").attr("style","display: none");
        if(colocaciones!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("colocaciones_numero_cuenta_ColBlo", colocaciones,"Select","-2");
            else
                cargar_selectPersonal("colocaciones_numero_cuenta_ColBlo", colocaciones,"Seleccione","-2");

        if(search!=null)
            cargar_selectPersonal2("buscar_ColocacionesBloqueos", search);

        $("#tag_fechaBloqueado").html(" <b>"+fecha+"</b>");
    }else{
        $("#div_colocacionesBloqueos").attr("style","display: none");
        $("#div_noPoseeColocacionesBloqueos").attr("style","display: ");
    }




}

function validarActivarFechas_ColBlo(valor){
    //var myDate = new Date();
    if(valor=="rangoFechas"){
        $("#fechas_ColBlo").fadeIn("fast");
        $("#fechaDesdeFiltro_ColBlo").val("");
    //    $("#fechaHastaFiltro_ColBlo").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
        $("#colocaciones_div_tabla_consulta_montoBloqueado").empty();
    }
    else {
        $("#fechas_ColBlo").fadeOut("fast");
        $("#fechaDesdeFiltro_ColBlo").val("");
        $("#fechaHastaFiltro_ColBlo").val("");
        $("#colocaciones_div_tabla_consulta_montoBloqueado").empty();
    }

};


function consultarBloqueosDesdeSaldosJSONData(cuenta){
    var url = urlConsultarBloqueos_ColBlo;
    var param={};
    var jsonColocaciones=[];

    jsonColocaciones[0] = cuenta;

    jsonColocaciones[1] = $("#buscar_ColocacionesBloqueos").get(0).value;

    jsonColocaciones[2] = $("#fechaDesdeFiltro_ColBlo").get(0).value;
    jsonColocaciones[3] = $("#fechaHastaFiltro_ColBlo").get(0).value;

    param.jsonColocaciones=JSON.stringify({"parametros":jsonColocaciones});

    sendServiceJSON(url,param,consultarBloqueosSuccess,null,null);
}

function consultarBloqueosTransJSONData(){
    var url = urlConsultarBloqueos_ColBlo;
    var param={};
    var jsonColocaciones=[];

    jsonColocaciones[0] = $("#colocaciones_numero_cuenta_ColBlo").get(0).value;

    jsonColocaciones[1] = $("#buscar_ColocacionesBloqueos").get(0).value;

    jsonColocaciones[2] = $("#fechaDesdeFiltro_ColBlo").get(0).value;
    jsonColocaciones[3] = $("#fechaHastaFiltro_ColBlo").get(0).value;

    param.jsonColocaciones=JSON.stringify({"parametros":jsonColocaciones});

    sendServiceJSON(url,param,consultarBloqueosSuccess,null,null);
}


function consultarBloqueosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    var mensaje = result.mensaje;
    var codigo = result.codigo;

    if(codigo=="0"){
        crearTabla('colocaciones_div_tabla_consulta_montoBloqueado','tabla_consulta_montoBloqueado',columnas,datos);
//
//
        var oTable = $('#tabla_consulta_montoBloqueado').dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                {  "sClass": "center" },
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



function print_BLOQUEOS_COLOCACIONES(){
    var miValue = $("#colocaciones_numero_cuenta_ColBlo" ).val();
    $("#colocaciones_numero_cuenta_ColBlo option[value='"+miValue+"']").attr("selected",true);
    $('#colocaciones_numero_cuenta_ColBlo_select').html($('#colocaciones_numero_cuenta_ColBlo option:selected').text());

    miValue = $("#buscar_ColocacionesBloqueos" ).val();
    $("#buscar_ColocacionesBloqueos option[value='"+miValue+"']").attr("selected",true);
    $('#buscar_ColocacionesBloqueos_select').html($('#buscar_ColocacionesBloqueos option:selected').text());

    printPageElement('div_BLOQUEOS_COLOCACIONES');  //Print EDO CUENTA
}
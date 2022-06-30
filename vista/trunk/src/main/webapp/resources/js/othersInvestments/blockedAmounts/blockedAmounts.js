var urlOtrasInversionesCargarBloqueos_BT="OtherInvestments_cargarOtrasInversionesBloqueos.action";
var urlConsultarOtrasInversionesBloqueos="OtherInvestments_consultarBloqueosOtrasInversiones.action";

var idioma="";
var noInfo="";

$(document).ready(function(){

    $( "#fechaDesdeFiltro_Bloqueos_OI").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_Bloqueos_OI").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    $( "#fechaDesdeFiltro_Bloqueos_OI").blur(function(){
        if(!isDate($( "#fechaDesdeFiltro_Bloqueos_OI").val())&& !Empty($( "#fechaDesdeFiltro_Bloqueos_OI").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaHastaFiltro_Bloqueos_OI").blur(function(){
        if(!isDate($( "#fechaHastaFiltro_Bloqueos_OI").val())&& !Empty($( "#fechaHastaFiltro_Bloqueos_OI").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaDesdeFiltro_Bloqueos_OI" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_Bloqueos_OI" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});

    $("#consulta_Bloqueos_OI").click(function(){
        var mensaje="";
        $("#div_tabla_consulta_Bloqueos_OI").empty();
        $(".requerido_Bloqueos_OI").each(function(){
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
        if($("#buscar_Bloqueos_OI").val()=="rangoFechas")
        {
            $(".requeridoFecha_Bloqueos_OI").each(function(){
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


            if(!isDate($( "#fechaDesdeFiltro_Bloqueos_OI").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltro_Bloqueos_OI").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltro_Bloqueos_OI").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltro_Bloqueos_OI").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltro_Bloqueos_OI").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltro_Bloqueos_OI").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltro_Bloqueos_OI").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltro_Bloqueos_OI").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltro_Bloqueos_OI").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltro_Bloqueos_OI").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltro_Bloqueos_OI").addClass("error_campo");
                    $("#fechaHastaFiltro_Bloqueos_OI").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltro_Bloqueos_OI").removeClass("error_campo");
                    $("#fechaHastaFiltro_Bloqueos_OI").removeClass("error_campo");
                }
            }
        }


        if(mensaje!=""){
            $("#tabla_consulta_Bloqueos_OI").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#tabla_consulta_Bloqueos_OI").fadeIn("fast");
            consultarCuentaBloqueosOtraInversionJSONData();
        }

    });

    $("#volverASaldosOIBoton").click(function(){
        volverASaldosOI();
    });

});

function limpiarTablaBloqueosOI(valor){
    var mensaje ="";
    if(valor=="-2"){
        $("#div_tabla_consulta_Bloqueos_OI").empty();
        $("#fechas_Bloqueos_OI").attr("style","display: none");
        $("#buscar_Bloqueos_OI").val("vigentes");
        $("#fechaDesdeFiltro_Bloqueos_OI").val("");
        $("#fechaHastaFiltro_Bloqueos_OI").val("");
    }else{
        if($("#buscar_Bloqueos_OI").val()=="rangoFechas")
        {
            $(".requeridoFecha_Bloqueos_OI").each(function(){
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
            $("#tabla_consulta_Bloqueos_OI").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#tabla_consulta_Bloqueos_OI").fadeIn("fast");
            consultarCuentaBloqueosOtraInversionJSONData();
        }
    }


}

function validarActivarFechas_Bloqueos_OI(valor){
    //var myDate = new Date();
   if(valor=="rangoFechas"){
       $("#fechas_Bloqueos_OI").fadeIn("fast");
       $("#fechaDesdeFiltro_Bloqueos_OI").val("");
   //    $("#fechaHastaFiltro_Bloqueos_OI").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
   }
   else {
       $("#fechas_Bloqueos_OI").fadeOut("fast");
       $("#fechaDesdeFiltro_Bloqueos_OI").val("");
       $("#fechaHastaFiltro_Bloqueos_OI").val("");
   }
};


function BloqueosOtherInvestmentsInfoPaginaJSONData(){
    var url = urlOtrasInversionesCargarBloqueos_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BloqueosOtherInvestmentsInfoSuccess,null,null);
}


function BloqueosOtherInvestmentsInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var fondos = result.fondos;
    var rango = result.rango;
    var fechaCierre = result.fechaCierre;
    var codigo = result.codigo;
    idioma = result.idioma;

    //$("#marco_trabajo").css("height","900px");

    $("#div_OtherInvestments_BA .selectFormulario_MF_BA").each(function(){
        this.value="-2";
    });
    $("#div_OtherInvestments_BA .inputFormulario_MF_BA").each(function(){
        this.value="";
    });

    $("#div_tabla_consulta_Bloqueos_OI").empty();
    $("#fechas_Bloqueos_OI").attr("style","display: none");
    $("#botonVolverASaldoOI").attr("style","display: none");
    if(idioma=="_us_en"){
      $("#fechaBloqueado_Bloqueos_OI").html(" <b>" +fechaCierre+"</b>");
      $("#labelSaldo_Bloqueos_OI").html("Blocks to: ");
    }
    else{
        $("#fechaBloqueado_Bloqueos_OI").html(" <b>" +fechaCierre+"</b>");
        $("#labelSaldo_Bloqueos_OI").html("Bloqueos al: ");
    }

    if(codigo=="0"){
        if(fondos!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("numero_cuenta_Bloqueos_OI", fondos,"Select","-2");
            else
                cargar_selectPersonal("numero_cuenta_Bloqueos_OI", fondos,"Seleccione","-2");

        if(rango!=null)
            cargar_selectPersonal2("buscar_Bloqueos_OI", rango);
        $("#div_noInfo_blockedAmount_otherInvestment").attr("style","display: none");
        $("#div_OtherInvestments_BA").attr("style","display: ");
    }else{
        $("#div_noInfo_blockedAmount_otherInvestment").attr("style","display: ");
        $("#div_OtherInvestments_BA").attr("style","display: none");
    }



}

function volverASaldosOI(){
    var opcion= "SALDOS_FONDOS_OI";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldoOI").attr("style","display: none");
}

function detalleBloqueoOI(cuenta){
    $("#numero_cuenta_Bloqueos_OI").val(cuenta);
    var opcion= "BLOQUEOS_FONDOS_OI";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldoOI").attr("style","display: ");
    consultarCuentaBloqueosOtraInversionBTJSONData(cuenta);
}



function consultarCuentaBloqueosOtraInversionBTJSONData(cuenta){
    var url = urlConsultarOtrasInversionesBloqueos;
    var param={};
    var jsonOtherInvestmentsTabla=[];

    jsonOtherInvestmentsTabla[0] =  cuenta;

    jsonOtherInvestmentsTabla[1] = $("#buscar_Bloqueos_OI").get(0).value;
    jsonOtherInvestmentsTabla[2] = $("#fechaDesdeFiltro_Bloqueos_OI").get(0).value;
    jsonOtherInvestmentsTabla[3] = $("#fechaHastaFiltro_Bloqueos_OI").get(0).value;


    param.jsonOtherInvestmentsTabla=JSON.stringify({"parametros":jsonOtherInvestmentsTabla});

    sendServiceJSON(url,param,consultarCuentaBloqueosOtraInversionSuccess,null,null);
}

function consultarCuentaBloqueosOtraInversionJSONData(){
    var url = urlConsultarOtrasInversionesBloqueos;
    var param={};
    var jsonOtherInvestmentsTabla=[];

    jsonOtherInvestmentsTabla[0] =  $("#numero_cuenta_Bloqueos_OI").get(0).value;

    jsonOtherInvestmentsTabla[1] = $("#buscar_Bloqueos_OI").get(0).value;
    jsonOtherInvestmentsTabla[2] = $("#fechaDesdeFiltro_Bloqueos_OI").get(0).value;
    jsonOtherInvestmentsTabla[3] = $("#fechaHastaFiltro_Bloqueos_OI").get(0).value;


    param.jsonOtherInvestmentsTabla=JSON.stringify({"parametros":jsonOtherInvestmentsTabla});

    sendServiceJSON(url,param,consultarCuentaBloqueosOtraInversionSuccess,null,null);
}


function consultarCuentaBloqueosOtraInversionSuccess(originalRequest){
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
        crearTabla('div_tabla_consulta_Bloqueos_OI','tabla_consulta_Bloqueos_OI',columnas,datos);
//
        var oTable = $('#tabla_consulta_Bloqueos_OI').dataTable({
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


function print_BLOQUEOS_FONDOS_OI(){
    var miValue = $("#numero_cuenta_Bloqueos_OI" ).val();
    $("#numero_cuenta_Bloqueos_OI option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#buscar_Bloqueos_OI" ).val();
    $("#buscar_Bloqueos_OI option[value='"+miValue+"']").attr("selected",true);

    printPageElement('div_BLOQUEOS_FONDOS_OI');  //Print EDO CUENTA
}

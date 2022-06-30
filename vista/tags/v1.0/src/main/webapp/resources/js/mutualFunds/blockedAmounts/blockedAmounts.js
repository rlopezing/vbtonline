var urlFondosMutualesCargarBloqueos_BT="MutualFunds_cargarFondosMutualesBloqueos.action";
var urlConsultarFondosMutualesBloqueos="MutualFunds_consultarBloqueosFondosMutuales.action";


var idioma ="";
var noInfo ="";
$(document).ready(function(){


    $( "#fechaDesdeFiltro_Bloqueos_FM").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_Bloqueos_FM").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    $( "#fechaDesdeFiltro_Bloqueos_FM").blur(function(){
        if(!isDate($( "#fechaDesdeFiltro_Bloqueos_FM").val())&& !Empty($( "#fechaDesdeFiltro_Bloqueos_FM").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaHastaFiltro_Bloqueos_FM").blur(function(){
        if(!isDate($( "#fechaHastaFiltro_Bloqueos_FM").val())&& !Empty($( "#fechaHastaFiltro_Bloqueos_FM").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaDesdeFiltro_Bloqueos_FM" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_Bloqueos_FM" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});


    $("#consulta_Bloqueos_FM").click(function(){
        var mensaje="";
        $("#div_tabla_consulta_Bloqueos_FM").empty();
        $(".requerido_Bloqueos_FM").each(function(){
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
        if($("#buscar_Bloqueos_FM").val()=="rangoFechas")
        {
            $(".requeridoFecha_Bloqueos_FM").each(function(){
                if($("#"+this.id).val()==""){
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




            if(!isDate($( "#fechaDesdeFiltro_Bloqueos_FM").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltro_Bloqueos_FM").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltro_Bloqueos_FM").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltro_Bloqueos_FM").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltro_Bloqueos_FM").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltro_Bloqueos_FM").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltro_Bloqueos_FM").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltro_Bloqueos_FM").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltro_Bloqueos_FM").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltro_Bloqueos_FM").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltro_Bloqueos_FM").addClass("error_campo");
                    $("#fechaHastaFiltro_Bloqueos_FM").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltro_Bloqueos_FM").removeClass("error_campo");
                    $("#fechaHastaFiltro_Bloqueos_FM").removeClass("error_campo");
                }
            }
        }



        if(mensaje!=""){
            $("#tabla_consulta_Bloqueos_FM").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#tabla_consulta_Bloqueos_FM").fadeIn("fast");
            consultarCuentaBloqueosFondoMutualJSONData();
        }

    });


    $("#volverASaldosMutualBoton").click(function(){
        volverASaldosMutual();
    });

});

function volverASaldosMutual(){
    var opcion= "SALDOS_FONDOS";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldoMutual").attr("style","display: none");
}

function detalleBloqueoMutual(cuenta){
    $("#numero_cuenta_Bloqueos_FM").val(cuenta);
    var opcion= "BLOQUEOS_FONDOS";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldoMutual").attr("style","display: ");
    consultarCuentaBloqueosFondoMutualDesdeSaldoJSONData(cuenta);
}

function limpiarTablaBloqueosMF(valor){
    var mensaje="";
    if(valor=="-2"){
        $("#div_tabla_consulta_Bloqueos_FM").empty();
        $("#fechas_Bloqueos_FM").attr("style","display: none");
        $("#fechaDesdeFiltro_Bloqueos_FM").val("");
        $("#fechaHastaFiltro_Bloqueos_FM").val("");
        $("#buscar_Bloqueos_FM").val("vigentes");
    }else{
        $("#div_tabla_consulta_Bloqueos_FM").empty();
        if($("#buscar_Bloqueos_FM").val()=="rangoFechas")
        {
            $(".requeridoFecha_Bloqueos_FM").each(function(){
                if($("#"+this.id).val()==""){
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
            $("#tabla_consulta_Bloqueos_FM").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#tabla_consulta_Bloqueos_FM").fadeIn("fast");
            consultarCuentaBloqueosFondoMutualJSONData();
        }
    }


}

function validarActivarFechas_Bloqueos_FM(valor){
  //  var myDate = new Date();
    if(valor=="rangoFechas"){
        $("#fechas_Bloqueos_FM").fadeIn("fast");
        $("#fechaDesdeFiltro_Bloqueos_FM").val("");
  //      $("#fechaHastaFiltro_Bloqueos_FM").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
    }
    else{
        $("#fechas_Bloqueos_FM").fadeOut("fast");
        $("#fechaDesdeFiltro_Bloqueos_FM").val("");
        $("#fechaHastaFiltro_Bloqueos_FM").val("");
    }

};


function BloqueosMutualFundsInfoPaginaJSONData(){
    var url = urlFondosMutualesCargarBloqueos_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BloqueosMutualFundsInfoSuccess,null,null);
}


function BloqueosMutualFundsInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var fondos = result.fondos;
    var rango = result.rango;
    var fechaCierre = result.fechaCierre;
    var codigo = result.codigo;
    idioma = result.idioma;

    //$("#marco_trabajo").css("height","900px");

    $("#div_mutualFunds_BA .selectFormulario_MF_BA").each(function(){
        this.value="-2";
    });
    $("#div_mutualFunds_BA .inputFormulario_MF_BA").each(function(){
        this.value="";
    });

    $("#div_tabla_consulta_Bloqueos_FM").empty();
    $("#fechas_Bloqueos_FM").attr("style","display: none");
    $("#botonVolverASaldoMutual").attr("style","display: none");

    $("#label_fechaBloqueado").html(" <b>" +fechaCierre+"</b>");

    if(codigo =="0"){
        if(fondos!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("numero_cuenta_Bloqueos_FM", fondos,"Select","-2");
            else
                cargar_selectPersonal("numero_cuenta_Bloqueos_FM", fondos,"Seleccione","-2");

        if(rango!=null)
            cargar_selectPersonal2("buscar_Bloqueos_FM", rango);

        $("#div_mutualFunds_BA").attr("style","display: ");
        $("#div_noInfo_blockedAmount_mutualFunds").attr("style","display: none");
    }else{
        $("#div_mutualFunds_BA").attr("style","display: none");
        $("#div_noInfo_blockedAmount_mutualFunds").attr("style","display: ");
    }


}



function consultarCuentaBloqueosFondoMutualDesdeSaldoJSONData(cuenta){
    var url = urlConsultarFondosMutualesBloqueos;
    var param={};
    var jsonMutualFundsTabla=[];

    jsonMutualFundsTabla[0] =  cuenta;

    jsonMutualFundsTabla[1] = $("#buscar_Bloqueos_FM").get(0).value;
    jsonMutualFundsTabla[2] = $("#fechaDesdeFiltro_Bloqueos_FM").get(0).value;
    jsonMutualFundsTabla[3] = $("#fechaHastaFiltro_Bloqueos_FM").get(0).value;


    param.jsonMutualFundsTabla=JSON.stringify({"parametros":jsonMutualFundsTabla});

    sendServiceJSON(url,param,consultarCuentaBloqueosFondoMutualSuccess,null,null);
}

function consultarCuentaBloqueosFondoMutualJSONData(){
    var url = urlConsultarFondosMutualesBloqueos;
    var param={};
    var jsonMutualFundsTabla=[];

    jsonMutualFundsTabla[0] =  $("#numero_cuenta_Bloqueos_FM").get(0).value;

    jsonMutualFundsTabla[1] = $("#buscar_Bloqueos_FM").get(0).value;
    jsonMutualFundsTabla[2] = $("#fechaDesdeFiltro_Bloqueos_FM").get(0).value;
    jsonMutualFundsTabla[3] = $("#fechaHastaFiltro_Bloqueos_FM").get(0).value;


    param.jsonMutualFundsTabla=JSON.stringify({"parametros":jsonMutualFundsTabla});

    sendServiceJSON(url,param,consultarCuentaBloqueosFondoMutualSuccess,null,null);
}


function consultarCuentaBloqueosFondoMutualSuccess(originalRequest){
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
        crearTabla('div_tabla_consulta_Bloqueos_FM','tabla_consulta_Bloqueos_FM',columnas,datos);
        var oTable = $('#tabla_consulta_Bloqueos_FM').dataTable({
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




function print_BLOQUEOS_FONDOS(){
    var miValue = $("#numero_cuenta_Bloqueos_FM" ).val();
    $("#numero_cuenta_Bloqueos_FM option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#buscar_Bloqueos_FM" ).val();
    $("#buscar_Bloqueos_FM option[value='"+miValue+"']").attr("selected",true);

    printPageElement('div_BLOQUEOS_FONDOS');  //Print EDO CUENTA
}
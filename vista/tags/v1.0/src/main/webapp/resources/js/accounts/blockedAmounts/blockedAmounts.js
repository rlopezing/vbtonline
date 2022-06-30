var urlAccountStatementCargar_BA="Accounts_cargarAccountStatement.action";
var urlConsultarCuentasBloqueos_BA="Accounts_consultarCuentasBloqueos.action";


var idioma="";
var noInfo="";
$(document).ready(function(){

    $( "#fechaDesdeFiltroBA").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltroBA").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    $( "#fechaDesdeFiltroBA").blur(function(){
        if(!isDate($( "#fechaDesdeFiltroBA").val())&& !Empty($( "#fechaDesdeFiltroBA").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaHastaFiltroBA").blur(function(){
        if(!isDate($( "#fechaHastaFiltroBA").val())&& !Empty($( "#fechaHastaFiltroBA").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });


    $( "#fechaDesdeFiltroBA" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltroBA" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});


    $("#estado_cuenta_consultar_BA").click(function(){
        var mensaje="";
        $(".requeridoBA").each(function(){
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
        if($("#buscar_BA").val()=="rangoFechas")
        {
            $(".requeridoFechaBA").each(function(){
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


            if(!isDate($( "#fechaDesdeFiltroBA").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltroBA").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltroBA").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltroBA").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltroBA").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltroBA").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltroBA").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltroBA").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltroBA").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltroBA").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltroBA").addClass("error_campo");
                    $("#fechaHastaFiltroBA").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltroBA").removeClass("error_campo");
                    $("#fechaHastaFiltroBA").removeClass("error_campo");
                }
            }

        }

        if(mensaje!=""){
            $("#estado_cuenta_div_tabla_consulta_BA").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#estado_cuenta_div_tabla_consulta_BA").fadeIn("fast");
            $("#blocked_imprimir").fadeOut("fast");
            consultarCuentaBloqueoTransJSONData();
        }

    });

    $("#volverASaldosCuentasBoton").click(function(){
        volverASaldosCuentas();
    });
});



function volverASaldosCuentas(){
    var opcion= "SALDOS_CUENTAS";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldoCuentas").attr("style","display: none");
}

function detalleBloqueoAccount(cuenta){
    $("#estado_cuenta_numero_cuenta_BA").val(cuenta);
    var opcion= "BLOQUEOS_CUENTAS";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    $("#botonVolverASaldoCuentas").attr("style","display: ");
    consultarCuentaBloqueoDesdeSaldoTransJSONData(cuenta);
}

function consultarMontosBloqueadoOnClick(){
    var mensaje="";
    $(".requeridoBA").each(function(){
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

    if($("#buscar_BA").val()=="rangoFechas")
    {
        $(".requeridoFechaBA").each(function(){
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
        $("#estado_cuenta_div_tabla_consulta_BA").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#div_mensajes_error").fadeOut("fast");
        $("#estado_cuenta_div_tabla_consulta_BA").fadeIn("fast");
        $("#blocked_imprimir").fadeOut("fast");
        consultarCuentaBloqueoTransJSONData();

    }
}

function limpiarBloqueoCuenta(){
    $("#estado_cuenta_div_tabla_consulta_BA").empty();
}

function validarActivarFechasBA(valor){
    //var myDate = new Date();
    if(valor=="rangoFechas"){
        $("#fechaDesdeFiltroBA").val("");
     //   $("#fechaHastaFiltroBA").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
        $("#fechas_BA").fadeIn("fast");
    }
    else{
        $("#fechas_BA").fadeOut("fast");
        $("#fechaDesdeFiltroBA").val("");
        $("#fechaHastaFiltroBA").val("");
    }

};


function BlockedAmountInfoPaginaJSONData(){
    var url = urlAccountStatementCargar_BA;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    $("#div_account_BlockedAmount .selectFormulario_account_BA").each(function(){
        this.value="-2";
    });
    $("#div_account_BlockedAmount .inputFormulario_account_BA").each(function(){
        this.value="";
    });
    $("#div_account_BlockedAmount .spanFormulario_account_BA").each(function(){
        this.innerHTML="";
    });
    $("#fechas_BA").fadeOut("fast");
    $("#buscar_BA").empty();
    $("#fechaDesdeFiltroBA").empty();
    $("#fechaDesdeFiltroBA").empty();
    $("#estado_cuenta_div_tabla_consulta_BA").empty();
    $("#botonVolverASaldoCuentas").attr("style","display: none");
    $("#cuentassaldos_TAGTransacciones2").attr("style","display: ");
    $("#cuentassaldos_TAGTransacciones_al").attr("style","display: none");
    $("#cuentassaldos_TAGTransacciones_fechaCierre").html("");

    sendServiceJSON(url,param,BlockedAmountInfoSuccess,null,null);
}


function BlockedAmountInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var cuentasBA = result.cuentas;
    var buscar = result.searchBA;
    var codigo = result.codigo;

    if(codigo=="0" && cuentasBA!=null){
        if(idioma=="_us_en")
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BA", cuentasBA,"Select","-2");
        else
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BA", cuentasBA,"Seleccione","-2");
        if(buscar!=null)
            cargar_selectPersonal2("buscar_BA", buscar);

        $("#div_account_BlockedAmount").attr("style","display: ");
        $("#noInfo_blockedAmount_account").attr("style","display: none");
    }else{

        $("#div_account_BlockedAmount").attr("style","display: none");
        $("#noInfo_blockedAmount_account").attr("style","display: ");
    }


}


function consultarCuentaBloqueoDesdeSaldoTransJSONData(cuenta){
    var url = urlConsultarCuentasBloqueos_BA;
    var param={};
    var jsonDetalleSaldoTrans=[];

    jsonDetalleSaldoTrans[0] =  cuenta;

    jsonDetalleSaldoTrans[1] = $("#buscar_BA").get(0).value;
    jsonDetalleSaldoTrans[2] = $("#fechaDesdeFiltroBA").get(0).value;
    jsonDetalleSaldoTrans[3] = $("#fechaHastaFiltroBA").get(0).value;


    param.jsonDetalleSaldoTrans=JSON.stringify({"parametros":jsonDetalleSaldoTrans});

    sendServiceJSON(url,param,consultarCuentaBloqueoSuccess,null,null);
}

function consultarCuentaBloqueoTransJSONData(){
    var url = urlConsultarCuentasBloqueos_BA;
    var param={};
    var jsonDetalleSaldoTrans=[];

    jsonDetalleSaldoTrans[0] =  $("#estado_cuenta_numero_cuenta_BA").get(0).value;

    jsonDetalleSaldoTrans[1] = $("#buscar_BA").get(0).value;
    jsonDetalleSaldoTrans[2] = $("#fechaDesdeFiltroBA").get(0).value;
    jsonDetalleSaldoTrans[3] = $("#fechaHastaFiltroBA").get(0).value;


    param.jsonDetalleSaldoTrans=JSON.stringify({"parametros":jsonDetalleSaldoTrans});

    sendServiceJSON(url,param,consultarCuentaBloqueoSuccess,null,null);
}

/**
 * Imprime div del html
 */

function crearPDF_BlockedAmount(){
    var miValue = $("#estado_cuenta_numero_cuenta_BA" ).val();
    $("#estado_cuenta_numero_cuenta_BA option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#buscar_BA" ).val();
    $("#buscar_BA option[value='"+miValue+"']").attr("selected",true);


    printPageElement('div_BLOQUEOS_CUENTAS');  //Print EDO CUENTA

}

function consultarCuentaBloqueoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var mensaje = result.mensaje;
    var codigo = result.codigo;
    var fechacierre = result.fechaCierre;

    if(codigo !="0"){
        idioma = result.idioma;
        if(idioma=="_us_en")
            noInfo="No data available";
        else
            noInfo="No hay informaci&oacute;n disponible";

        crearTabla('estado_cuenta_div_tabla_consulta_BA','estado_cuenta_tabla_consulta_BA',columnas,datos);
//
//
        var oTable = $('#estado_cuenta_tabla_consulta_BA').dataTable({
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
        $("#cuentassaldos_TAGTransacciones2").attr("style","display: none");
        $("#cuentassaldos_TAGTransacciones_al").attr("style","display: ");
        $("#cuentassaldos_TAGTransacciones_fechaCierre").html(" "+"<b>"+fechacierre+"</b>");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}




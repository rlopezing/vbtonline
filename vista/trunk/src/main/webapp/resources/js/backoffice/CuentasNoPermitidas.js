var urlBackOfficeConsultarCuentasNoPermitidas="BackOffice_consultarCuentasNoPermitidas.action";
var urlBackOfficeInsertarCuentaNoPermitida="BackOffice_crearCuentaNoPermitida.action";
var urlBackOfficeEditarEstatusCuentaNoPermitida="BackOffice_editarEstatusCuentaNoPermitida.action";

$(document).ready(function(){

    $("#div_mensajes_error").fadeOut("fast");

    $("#agregarCuentaNoPermitida").click(function(){
       $("#div_tabla_consultaCuentaNoPermitida").fadeOut("fast");
       $("#div_tabla_agregarCuentaNoPermitida").fadeIn("fast");

        blanquearFormularios("div_tabla_agregarCuentaNoPermitida");
    });

    $("#CCNP_InsertarCuentaNoPermitida").click(function(){

        if ($("#ACNP_numeroCuenta").val() != "" && $("#ACNP_tipoBanco").val() != "" && $("#ACNP_codigoBanco").val() != "" ){
            BackOfficeInsertarCuentaNoPermitidaJSONData();
            blanquearFormularios("div_tabla_agregarCuentaNoPermitida");
        }else{
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Debe ingresar todos los campos necesarios");
            $("#div_mensajes_error").fadeIn("fast");
        }
    });

    $("#CCNP_Resetear").click(function(){
        $(".obligatorio_ACNP").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        blanquearFormularios("div_tabla_agregarCuentaNoPermitida");
    });

    $("#CCNP_Cancelar").click(function(){
        $(".obligatorio_ACNP").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarCuentaNoPermitida");
        $("#div_tabla_consultaCuentaNoPermitida").fadeIn("fast");
        $("#div_tabla_agregarCuentaNoPermitida").fadeOut("fast");
        BackOfficeCuentasNoPermitidasJSONData();
    });

    $("#CCNP_reset").click(function(){
        blanquearFormularios("div_tabla_consultaCuentaNoPermitida");
        BackOfficeCuentasNoPermitidasJSONData();
    });

    $("#CCNP_PermitirCuenta").click(function(){
        var listaCuentas=[];

        $.each($("#div_tabla_consultarCuentasNoPermitidas .datosCCNP"),function(pos,item){
            if($("#cuentaNoPermitida_"+this.id.split("_")[1]).is(':checked')){

                var cuenta={};
                cuenta.strCodigoBanco = $("#cuentaNoPermitida_"+this.id.split("_")[1]).val().split("|")[2];
                cuenta.strNombreBanco = $("#cuentaNoPermitida_"+this.id.split("_")[1]).val().split("|")[0];
                cuenta.strNumeroCuenta = $("#cuentaNoPermitida_"+this.id.split("_")[1]).val().split("|")[1];
                listaCuentas.push(cuenta);
            }
        });

        if (listaCuentas.length != 0){
            BackOfficeEditarEstatusCuentas(listaCuentas);
        }else{
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Debe seleccionar al menos una cuenta bancaria");
            $("#div_mensajes_error").fadeIn("fast");
        }
    });

    $("#CCNP_search").click(function(){
        BackOfficeCuentasNoPermitidasJSONData();
    });
});

function BackOfficeEditarEstatusCuentas(listaCuentas){
    var url = urlBackOfficeEditarEstatusCuentaNoPermitida;
    var jsonCtasNoPermitidas = [];
    var lista = {};
    var param = {};

    jsonCtasNoPermitidas = listaCuentas;

    param.jsonParametrosCtasNoPermitidas=JSON.stringify({"parametrosCtasNoPermitidas":jsonCtasNoPermitidas});
    sendServiceJSON(url,param,BackOfficeEditarEstatusCuentasSuccess,null,null);
}

function BackOfficeEditarEstatusCuentasSuccess(originalRequest){
    var result = originalRequest;
    respuesta = result.respuesta;

    if(respuesta == "OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Se ha modificado exitosamente el estatus de las cuentas seleccionadas");
        $("#div_mensajes_error").fadeIn("fast");
        BackOfficeCuentasNoPermitidasJSONData();
    }

}

function BackOfficeCuentasNoPermitidasJSONData(){
  var url = urlBackOfficeConsultarCuentasNoPermitidas;

    var jsonParametrosCtasNoPermitidas=[];
    var datosConsultaBO={};
    var param={};

    datosConsultaBO.strCodigoBanco = $("#CCNP_BancoFiltro").get(0).value;
    datosConsultaBO.strNumeroCuenta = $("#CCNP_NroCuentaFiltro").get(0).value;

    jsonParametrosCtasNoPermitidas[0]= datosConsultaBO;

    param.jsonParametrosCtasNoPermitidas=JSON.stringify({"parametrosCtasNoPermitidas":jsonParametrosCtasNoPermitidas});
    $("#div_carga_espera").removeClass("oculto");
    sendServiceJSON(url,param,BackOfficeCuentasNoPermitidasSuccess,null,null);
}

function BackOfficeCuentasNoPermitidasSuccess(originalRequest){
    //this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoBanco = result.tipoBanco;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" }
    ];
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consultarCtasNoPermitidas";
    var id_contenedor="div_tabla_consultarCuentasNoPermitidas";
    // tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
    crearTablaV3(id_contenedor,tabla_id,columnas,datos);
/*
    $("#"+tabla_id).dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "bScrollCollapse": true,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });
*/

    $("#"+tabla_id).dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": false,
        "bFilter": false,
        "sPaginationType": "full_numbers",
        "bSort": false,
        "bPaginate": true,
        "bScrollCollapse": true,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    // oTable=tabla;
    oTable=$("#"+tabla_id);
    $("#div_carga_espera").addClass("oculto");
    cargar_select("ACNP_tipoBanco",tipoBanco);

    if(datos!=null){
/*        if(datos.length!=0){
            $("#paginacion_"+tabla_id).paginate({
                count 		: parseInt(((datos.length%tamano_pag)==0)?(datos.length/tamano_pag):(datos.length/tamano_pag)+1),
                start 		: 1,
                display     : 5,
                border					: false,
                text_color  			: '#888',
                background_color    	: '#EEE',
                text_hover_color  		: 'black',
                background_hover_color	: '#CFCFCF',
                images					: false,
                mouse					: 'press',
                onChange     			: function(page){
                    guardarlogusu(datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag);
                    paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                }
            });
        }*/
    }
}

function BackOfficeInsertarCuentaNoPermitidaJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeInsertarCuentaNoPermitida;
    var jsonParametrosCtasNoPermitidas=[];
    var datosConsultaBO={};
    var param={};

    datosConsultaBO.strCodigoBanco = $("#ACNP_codigoBanco").get(0).value;
    datosConsultaBO.strNumeroCuenta = $("#ACNP_numeroCuenta").get(0).value;
    datosConsultaBO.strCodigoTipoBanco = $("#ACNP_tipoBanco").get(0).value;

    jsonParametrosCtasNoPermitidas[0]= datosConsultaBO;

    param.jsonParametrosCtasNoPermitidas=JSON.stringify({"parametrosCtasNoPermitidas":jsonParametrosCtasNoPermitidas});

    sendServiceJSON(url,param,BackOfficeInsertarCuentaNoPermitidaSuccess,null,null);
}

function BackOfficeInsertarCuentaNoPermitidaSuccess(originalRequest){
    var result = originalRequest;
    respuesta = result.respuesta;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta == "OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("La nueva cuenta bloqueada fue agregada satisfactoriamente");
        $("#div_mensajes_error").fadeIn("slow");

    }
}
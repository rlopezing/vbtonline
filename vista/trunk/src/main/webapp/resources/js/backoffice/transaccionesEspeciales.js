var urlBackOfficeConsultarTransaccionesEspeciales="BackOffice_consultarTransaccionesEspeciales.action";
var urlBackOfficeInsertarTransaccionEspecial="BackOffice_crearTransaccionEspecial.action";
var urlBackOfficeEliminarTransaccionEspecial="BackOffice_eliminarTransaccionEspecial.action";

$(document).ready(function(){

    $("#div_mensajes_error").fadeOut("fast");

    $("#agregarTransaccionEspecial").click(function(){
        $("#div_tabla_consultaTransaccionEspecial").fadeOut("fast");
        $("#div_tabla_agregarTransaccionEspecial").fadeIn("fast");

        blanquearFormularios("div_tabla_agregarTransaccionEspecial");
    });

    $("#CTE_InsertarTransaccionEspecial").click(function(){
        var mensaje = "";

        if($("#ATE_codigoBanco").val() != "" || $("#ATE_codigoSwift").val() != ""){
            if($("#ATE_tipoMov").val() != "") {
                BackOfficeInsertarTransaccionEspecialJSONData();
                blanquearFormularios("div_tabla_agregarTransaccionEspecial");
            }
            else
                mensaje = "El Tipo de Movimiento es obligatorio";
        }
        else
            mensaje = "Debe ingresar un Código de Banco o Swift para continuar";

        if(mensaje != "") {
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("fast");
        }

    });

    $("#CTE_Resetear").click(function(){
        $(".obligatorio_ATE").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        blanquearFormularios("div_tabla_agregarTransaccionEspecial");
    });

    $("#CTE_Cancelar").click(function(){
        $(".obligatorio_ATE").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarTransaccionEspecial");
        $("#div_tabla_consultaTransaccionEspecial").fadeIn("fast");
        $("#div_tabla_agregarTransaccionEspecial").fadeOut("fast");
        BackOfficeTransaccionesEspecialesJSONData();
    });

    $("#CTE_reset").click(function(){
        blanquearFormularios("div_tabla_consultarTransaccionesEspeciales");
        BackOfficeTransaccionesEspecialesJSONData();
    });

    $("#CTE_Eliminar").click(function(){
        var listaCuentas=[];

        $.each($("#div_tabla_consultarTransaccionesEspeciales .datosCTE"),function(pos,item){
            if($("#transaccionEspecial_"+this.id.split("_")[1]).is(':checked')){

                var cuenta={};
                cuenta.codTipoMov = $("#transaccionEspecial_"+this.id.split("_")[1]).val().split("|")[0];
                listaCuentas.push(cuenta);
            }
        });

        if (listaCuentas.length != 0){
            BackOfficeEliminarTransaccionEspecial(listaCuentas);
        }else{
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Debe seleccionar al menos una transacción");
            $("#div_mensajes_error").fadeIn("fast");
        }
    });

    $("#CTE_search").click(function(){
        BackOfficeCuentasNoPermitidasJSONData();
    });
});

function BackOfficeEliminarTransaccionEspecial(listaCuentas){
    var url = urlBackOfficeEliminarTransaccionEspecial;
    var jsonParametrosEspeciales=[];
    var param={};

    jsonParametrosEspeciales = listaCuentas;

    param.jsonParametrosEspeciales=JSON.stringify({"parametrosTransaccionesEspeciales":jsonParametrosEspeciales});
    sendServiceJSON(url,param,BackOfficeEliminarTransaccionEspecialSuccess,null,null);
}

function BackOfficeEliminarTransaccionEspecialSuccess(originalRequest){
    var result = originalRequest;
    respuesta = result.respuesta;

    if(respuesta == "OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Se ha eliminado exitósamente la transacción");
        $("#div_mensajes_error").fadeIn("fast");
        BackOfficeTransaccionesEspecialesJSONData();
    }

}

function BackOfficeTransaccionesEspecialesJSONData(){
    var url = urlBackOfficeConsultarTransaccionesEspeciales;

    var param={};

    $("#div_carga_espera").removeClass("oculto");
    sendServiceJSON(url,param,BackOfficeTransaccionesEspecialesSuccess,null,null);
}

function BackOfficeTransaccionesEspecialesSuccess(originalRequest){
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
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center" },
        { "sClass": "center" }
    ];
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consultarTransaccionesEspeciales";
    var id_contenedor="div_tabla_consultarTransaccionesEspeciales";
    tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
    tabla.dataTable({
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
    oTable=tabla;

    if(datos!=null){
        if(datos.length!=0){
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
        }
    }

    $("#div_carga_espera").addClass("oculto");
}

function BackOfficeInsertarTransaccionEspecialJSONData(){
    var url = urlBackOfficeInsertarTransaccionEspecial;
    var jsonParametrosEspeciales=[];
    var datosConsultaBO={};
    var param={};

    datosConsultaBO.strCodigoBanco = $("#ATE_codigoBanco").get(0).value;
    datosConsultaBO.strSwiftBanco = $("#ATE_codigoSwift").get(0).value;
    datosConsultaBO.strTipoMov = $("#ATE_tipoMov").get(0).value;
    datosConsultaBO.strNumeroCuenta = $("#ATE_numeroCuenta").get(0).value;
    datosConsultaBO.strAddressBanco = $("#ATE_direccion").get(0).value;
    datosConsultaBO.strCityBanco = $("#ATE_ciudad").get(0).value;
    datosConsultaBO.strCountryBanco = $("#ATE_pais").get(0).value;
    datosConsultaBO.strPostalCodeBanco = $("#ATE_codigoPostal").get(0).value;
    datosConsultaBO.showAccount = $("#ATE_mostrarNroCuenta").get(0).value;


    jsonParametrosEspeciales[0]= datosConsultaBO;
    param.jsonParametrosEspeciales=JSON.stringify({"parametrosTransaccionesEspeciales":jsonParametrosEspeciales});

    sendServiceJSON(url,param,BackOfficeInsertarTransaccionEspecialSuccess,null,null);
}

function BackOfficeInsertarTransaccionEspecialSuccess(originalRequest){

    var result = originalRequest;
    respuesta = result.respuesta;
    if(respuesta == "OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("La nueva transacción especial fue agregada satisfactoriamente");
        $("#div_mensajes_error").fadeIn("slow");

    }
}
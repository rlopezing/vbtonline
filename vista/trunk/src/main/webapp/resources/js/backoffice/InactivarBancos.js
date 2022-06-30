var urlCargarBuscarBancosBO="Transfers_cargarBuscarBanco.action";
var urlBuscarBancosBO="BackOffice_buscarBanco.action";
var urlInactivarBancosBO="BackOffice_inactivarBanco.action";
var urlActivarBancosBO="BackOffice_activarBanco.action";
var urlBuscarSucursales="Transfers_buscarBanco.action";

$(document).ready(function(){

    $("#limpiarInactivarBancos").click(function(){
        blanquearFormularios("inactivarBancosBO");
        $("#inactivarBancosBO .inputFormulario").each(function(){
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
        });
        $("#div_tabla_bancos_bo").empty();
        $("#paginacion_tabla_consulta_bancos_bo").empty();
    });

    $("#buscarInactivarBancos").click(function(){
        var mensaje="";

     /*   if($("#BankCode_buscarBO").get(0).value.isEmpty() &&
            $("#BankName_buscarBO").get(0).value.isEmpty()){
            mensaje='Debe indicar al menos uno de estos criterios: C&oacute;digo y/o Nombre del Banco';
        } else if($("#BankCodeType_buscarBO").val()=="-2"){
            mensaje='Debe seleccionar el tipo de banco';

        }   */
        if($("#BankCodeType_buscarBO").val()=="-2"){
            mensaje='Debe seleccionar el tipo de banco';

        }

        if(($("#BankCode_buscarBO").val()=="")&&($("#BankName_buscarBO").val()=="")){
            mensaje="Debe introducir el Código Banco y/o el Nombre del Banco";

        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            buscarBancosBOJSONData();
        }
    });


});

function BackOfficeBancosJSONData(){
    var url = urlCargarBuscarBancosBO;
    var param={};
    $("#div_carga_espera").removeClass("oculto");
    blanquearFormularios("inactivarBancosBO");
    $("#paginacion_tabla_consulta_bancos_bo").empty();
    $("#tabla_consulta_bancos_bo").empty();
    sendServiceJSON(url,param,BackOfficeBancosJSONDataSuccess,null,null);
}


function BackOfficeBancosJSONDataSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    Paises = result.paises;
    codBankBen = result.codBankBen;
    var estatus=result.estatusBanco;

    cargar_selectPersonal2("estatus_buscarBO", estatus);
    cargar_selectPersonal("BankCodeType_buscarBO", codBankBen,"Seleccione","-2");
    $("#div_carga_espera").addClass("oculto");

}

function buscarBancosBOJSONData(){
    var url = urlBuscarBancosBO;
    var param={};
    var jsonParametrosString=[];
        $("#div_carga_espera").removeClass("oculto");
        jsonParametrosString[0] =  $("#BankCodeType_buscarBO").get(0).value;
        jsonParametrosString[1] = $("#BankCode_buscarBO").get(0).value.toUpperCase();
        jsonParametrosString[2] = $("#BankName_buscarBO").val().toUpperCase();
        jsonParametrosString[3] = $("#estatus_buscarBO :selected").html();
        jsonParametrosString[4] = $("#estatus_buscarBO").val();


    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,buscarBancosBOSuccess,null,null);
}


function buscarBancosBOSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        {  "sClass": "center" },
        {  "sClass": "center" },
        {  "sClass": "center" }
    ];
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tabla;
    banco= result.banco;
    var tamano_pag=10;
    var tabla_id;
    var id_contenedor;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";


        tabla_id="tabla_consulta_bancos_bo";
        id_contenedor="div_tabla_bancos_bo";
        tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);

    tabla.dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "bScrollCollapse": true,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    oTable=tabla;

    if(datos.length>0){
        $("#paginacion_"+tabla_id).paginate({
            count 		: parseInt(((datos.length%tamano_pag)==0)?(datos.length/tamano_pag):(datos.length/tamano_pag)+1),
            start 		: 1,
            display     : 8,
            border					: false,
            text_color  			: '#888',
            background_color    	: '#EEE',
            text_hover_color  		: 'black',
            background_hover_color	: '#CFCFCF',
            images					: false,
            mouse					: 'press',
            onChange     			: function(page){
                paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
            }
        });
    }
    $("#div_carga_espera").addClass("oculto");
}




function desactivarBanco(codigo, tipo,cantidad){
     if(confirm("Al inactivar el codigo "+tipo+" "+codigo+" se inactivaran las "+ cantidad+" sucursales asociadas al mismo. \n\t\t\t\t\t\t¿Desea proceder con la operación?\t")){

        inactivarBancosBOJSONData(codigo,tipo);
     }
}





function inactivarBancosBOJSONData(codigo, tipo){
    var url = urlInactivarBancosBO;
    var param={};
    var jsonParametrosString=[];
    $("#div_carga_espera").removeClass("oculto");
    jsonParametrosString[0] = codigo;
    jsonParametrosString[1] = tipo;

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,inactivarBancosBOSuccess,null,null);
}


function inactivarBancosBOSuccess(originalRequest){
    var result = originalRequest;

    if (result.respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Operación realizada con éxito");
        $("#div_mensajes_error").fadeIn("slow");
        buscarBancosBOJSONData();
    } else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Ha ocurrido un error");
        $("#div_mensajes_error").fadeIn("slow");
    }

}








function activarBanco(codigo, tipo,cantidad){
    if(confirm("Al activar el codigo "+tipo+" "+codigo+" se activaran las "+ cantidad+" sucursales asociadas al mismo. \n\t\t\t\t\t\t¿Desea proceder con la operación?\t")){

        activarBancosBOJSONData(codigo,tipo);
    }
}





function activarBancosBOJSONData(codigo, tipo){
    var url = urlActivarBancosBO;
    var param={};
    var jsonParametrosString=[];
    $("#div_carga_espera").removeClass("oculto");
    jsonParametrosString[0] = codigo;
    jsonParametrosString[1] = tipo;

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,activarBancosBOSuccess,null,null);
}


function activarBancosBOSuccess(originalRequest){
    var result = originalRequest;

    if (result.respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Operación realizada con éxito");
        $("#div_mensajes_error").fadeIn("slow");
        buscarBancosBOJSONData();
    } else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Ha ocurrido un error");
        $("#div_mensajes_error").fadeIn("slow");
    }

}



function cargraSucursales(bankCode, BankTypeCode){
    $("#buscarBanco_consulta_sucursales").empty();
    $("#paginacion_buscarBanco_consulta_sucursales").empty();
    $("#paginacion_buscarBanco_consulta_sucursales").removeClass("jPaginate");
    $("#sign_up_sucursales").lightbox_me({centered: true, onLoad: function() {

    }});
    buscarSucursalesJSONData(bankCode, BankTypeCode);
}





function buscarSucursalesJSONData(bankCode, BankTypeCode){
    var url = urlBuscarSucursales;
    var param={};
    var jsonTransfers=[];


        jsonTransfers[0] = BankTypeCode;
        jsonTransfers[1] = bankCode;
        jsonTransfers[2] = "";
        jsonTransfers[3] = "";
        jsonTransfers[4] = "";
        jsonTransfers[5] = "";
        jsonTransfers[6] = $("#estatus_buscarBO").val();


    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,buscarSucursalesSuccess,null,null);
}


function buscarSucursalesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center"},
        {  "sClass": "center" },
        {  "sClass": "center" },
        {  "sClass": "right" }
    ];
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tabla;
    banco= result.banco;
    var tamano_pag=10;
    var tabla_id;
    var id_contenedor;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";


        tabla_id="buscarBanco_consulta_sucursales";
        id_contenedor="buscarBanco_div_tabla_consulta_sucursales";
        tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);

//
    tabla.dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "bScrollCollapse": true,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    oTable=tabla;

    if(datos.length>0){
        $("#paginacion_"+tabla_id).paginate({
            count 		: parseInt(((datos.length%tamano_pag)==0)?(datos.length/tamano_pag):(datos.length/tamano_pag)+1),
            start 		: 1,
            display     : 8,
            border					: false,
            text_color  			: '#888',
            background_color    	: '#EEE',
            text_hover_color  		: 'black',
            background_hover_color	: '#CFCFCF',
            images					: false,
            mouse					: 'press',
            onChange     			: function(page){
                paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
            }
        });
    }


    $("#div_carga_espera_sign_up").addClass("oculto");

}

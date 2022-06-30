var urlBackOfficeConsultarPaisesNoPermitidos ="BackOffice_consultarPaisesNoPermitidos.action";
var urlBackOfficeCambiaPaisesNoPermitidos ="BackOffice_cambiaPaisesNoPermitidos.action";
var urlBackOfficeCambiaPaisesRevision ="BackOffice_cambiaPaisesRevision.action";
var cargarTipoPais ="";
var cargarPermiteRevision ="";

$(document).ready(function() {

    $("#resetFiltroPaisesNoPermitidos").click(function(){

        blanquearFormularios("filtroPaisesNoPermitidos");

        $('#tabla_consulta_paises').dataTable().fnClearTable();
    });

    $("#searchListaPaisesNoPermitidos").click(function(){

        $("#div_carga_espera").removeClass("oculto");
        BackOfficeConsultarPaisesNoPermitidosJSONData();
       // BackOfficeConsultarPaisesRevisionJSONData();

    });

});

function BackOfficeConsultarPaisesNoPermitidosJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeConsultarPaisesNoPermitidos;
    var param={};
    var jsonParametrosString=[];

    jsonParametrosString[0]= $("#CodPaisNoPermitido").val();
    jsonParametrosString[1]= $("#NomPaisNoPermitido").val();
    jsonParametrosString[2]= $("#estatusFiltroPaises").val();
    jsonParametrosString[3]= $("#estatusRevisionPais").val();
    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    // sendServiceJSON_sinc(url,param,BackOfficeConsultarPaisesNoPermitidosSuccess,null,null);
    sendServiceJSON(url,param,BackOfficeConsultarPaisesNoPermitidosSuccess,null,null);

}

function BackOfficeConsultarPaisesNoPermitidosSuccess(originalRequest){

    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    $("#div_carga_espera").addClass("oculto");

    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center"},
        { "sClass": "center"}
    ];

    var tamano_pag=15;
    var id_contenedor="div_tabla_consulta_paises";
    var tabla_id="tabla_consulta_paises";
    // var tabla=crearTabla(id_contenedor,tabla_id,columnas,datos);
    var tabla=crearTablaV3(id_contenedor,tabla_id,columnas,datos);

    if (cargarTipoPais =="") {
       cargar_select("estatusFiltroPaises", result.tipoPaisPermitido);
        cargarTipoPais="SI";
    }

    if (cargarPermiteRevision =="") {
        cargar_select("estatusRevisionPais", result.tipoPaisPermitido);
        cargarPermiteRevision="SI";
    }

/*    oTable = $('#tabla_consulta_paises').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "aoColumns": p_sclass,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    oTable = $('#tabla_consulta_paises').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "aoColumns": p_sclass,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}

function cambiarListaPaises(id,operacion){

    if (operacion==0){
        if(confirm("Desea Inlcuir el Pa\u00eds para las transferencias \n ¿Est\u00e1 seguro que desea continuar?")){
            cambiaPaisesNoPermitidosJSONData(id,operacion);
        }
    }else{
        if(confirm("Desea Excluir el Pa\u00eds para las transferencias \n ¿Est\u00e1 seguro que desea continuar?")){
            cambiaPaisesNoPermitidosJSONData(id,operacion);
        }
    }

}

function cambiarRevisionPais(id,operacion){

    if (operacion==0){
        if(confirm("Desea que el Pa\u00eds requiere revision \n ¿Est\u00e1 seguro que desea continuar?")){
            cambiaPaisesRevsionJSONData(id,operacion);
        }
    }else{
        if(confirm("Desea que el Pa\u00eds no requeriere revision de \n ¿Est\u00e1 seguro que desea continuar?")){
            cambiaPaisesRevsionJSONData(id,operacion);
        }
    }

}

function cambiaPaisesNoPermitidosJSONData(id,operacion){
    var url = urlBackOfficeCambiaPaisesNoPermitidos;
    var param={};
    var jsonParametrosString=[];

    $("#div_carga_espera").removeClass("oculto");
    jsonParametrosString[0]= id.split("|")[0];
    jsonParametrosString[1]= id.split("|")[1];
    jsonParametrosString[2]= operacion;

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,cambiaPaisesNoPermitidosSuccess,null,null);

}

function cambiaPaisesRevsionJSONData(id,operacion){
    var url = urlBackOfficeCambiaPaisesRevision;
    var param={};
    var jsonParametrosString=[];

    $("#div_carga_espera").removeClass("oculto");
    jsonParametrosString[0]= id.split("|")[0];
    jsonParametrosString[1]= id.split("|")[1];
    jsonParametrosString[2]= operacion;

    console.log(jsonParametrosString);

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,cambiaPaisesRevisionSuccess,null,null);

}



function cambiaPaisesNoPermitidosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

     if    (result.respuesta == "OK")
     {

         $("#searchListaPaisesNoPermitidos").click();

         $("#mensaje_error").empty();
         $("#mensaje_error").html("Operacion Realizada con Exito");
         $("#div_mensajes_error").fadeIn("slow");
     }
    else
     {
         $("#searchListaPaisesNoPermitidos").click();

         $("#mensaje_error").empty();
         $("#mensaje_error").html("Operacion Fallida");
         $("#div_mensajes_error").fadeIn("slow");
     }

}

function cambiaPaisesRevisionSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    if    (result.respuesta == "OK")
    {

        $("#searchListaPaisesNoPermitidos").click();

        $("#mensaje_error").empty();
        $("#mensaje_error").html("Operacion Realizada con Exito");
        $("#div_mensajes_error").fadeIn("slow");
    } else{
        $("#searchListaPaisesNoPermitidos").click();

        $("#mensaje_error").empty();
        $("#mensaje_error").html("Operacion Fallida");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

function BackOfficeConsultarPaisesRevisionJSONData(){
    var url = urlBackOfficeConsultarPaisesRevision;
    var param={};
    var jsonParametrosString=[];

    jsonParametrosString[0]= $("#CodPaisNoPermitido").val();
    jsonParametrosString[1]= $("#NomPaisNoPermitido").val();
    jsonParametrosString[2]= $("#estatusFiltroPaises").val();
    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,BackOfficeConsultarPaisesRevisionSuccess,null,null);

}

function BackOfficeConsultarPaisesRevisionSuccess(originalRequest){

    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;

    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center"},
        { "sClass": "center"}
    ];

    var tamano_pag=15;
    var id_contenedor="div_tabla_consulta_paises";
    var tabla_id="tabla_consulta_paises";
    var tabla=crearTabla(id_contenedor,tabla_id,columnas,datos);

    if (cargarTipoPais =="") {
        cargar_select("estatusFiltroPaises", result.tipoPaisPermitido);
        cargarTipoPais="SI";
    }
}
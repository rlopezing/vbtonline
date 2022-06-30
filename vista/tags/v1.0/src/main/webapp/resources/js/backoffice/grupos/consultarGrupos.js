var urlBackOffice="BackOffice_consultarGrupos.action";
var urlBackOfficeGOS="BackOffice_consultarGruposOpcionesSistemas.action";
var urlBackOfficeGOP="BackOffice_consultarGrupoOpcPermisos.action";
var urlBackOfficeSalvarGOP="BackOffice_salvarPermisosGrupos.action";

//var grupoSeleccionado;
 var grupoID="";
var opcionID="";
$(document).ready(function(){

    $("#CGOS_back_CG").click(function(){
        $("#div_tabla_consultaGOS").fadeOut("fast");
        $("#div_tabla_consultaG").fadeIn("fast");
    });

    $("#CGOP_back_CG").click(function(){
        $("#div_tabla_consultaGOS").fadeIn("fast");
        $("#div_tabla_consultaGOP").fadeOut("fast");
    });

    $("#CGOP_save_CG").click(function(){
        var misAcciones ="";
        var accionesCero="";
        $("#tabla_grupoOpcionesPermisos .verificarSelecccion").each(function(){
            misAcciones   = misAcciones + $("#"+this.id).val();
            accionesCero  = accionesCero + "0";
        });
            BackOfficeSalvarAccionesGruposJSONData(misAcciones);

    });





});



function BackOfficeGruposJSONData(){
    var url = urlBackOffice;
    var param={};

    sendServiceJSON(url,param,BackOfficeGruposSuccess,null,null);
}


function BackOfficeGruposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    //$("#marco_trabajo").css("height","1000px");
    var tabla="tabla_consultarGrupos";
    $("#div_tabla_consultaG").attr("style","display:''");
    $("#div_tabla_consultaGOS").attr("style","display:none");
    $("#div_tabla_consultaGOP").attr("style","display:none");


    crearTabla("div_tabla_consultarGrupos",tabla,columnas,datos);

    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function seleccionarGrupoOpcion(id){
//    alert("valor: "+valor);
//    grupoID = valor;
    BackOfficeGruposOpcionesSistemaJSONData(id);
}

function BackOfficeGruposOpcionesSistemaJSONData(id){
    var url = urlBackOfficeGOS;
    var param={};
    var jsonGOS=[];
    var datosGrupoOS={};
    datosGrupoOS.grupoCategoria = id;
    grupoID = id;
    datosGrupoOS.grupo = id.split("!")[1];
//    datosGrupoOS.grupo = grupo;

    jsonGOS[0]= datosGrupoOS;

    param.jsonGOS=JSON.stringify({"consultaGrupoOS":jsonGOS});

    sendServiceJSON(url,param,BackOfficeGruposOpcionesSistemasSuccess,null,null);
}


function BackOfficeGruposOpcionesSistemasSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var grupo = result.datosGrupoOS;

    $("#div_tabla_consultaGOS").fadeIn("fast");
    $("#div_tabla_consultaG").fadeOut("fast");

    var tabla="tabla_grupoOpcionesSistema";
    $('#Legend_grupoOpcionesSistema').html(grupo.grupo);
    crearTabla("div_tabla_grupoOpcionesSistema",tabla,columnas,datos);

    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function seleccionarGrupoSistemaOpcion(id){

    BackOfficeGruposOpcionesPermisosJSONData(id);
}

function BackOfficeGruposOpcionesPermisosJSONData(id){
    var url = urlBackOfficeGOP;
    var param={};
    var jsonGOP=[];
    var datosGrupoOS={};
//    alert("grupo: "+id);
    datosGrupoOS.grupoCategoria = id;
    opcionID = id;
    datosGrupoOS.grupo = grupoID;
    opcion = $("#"+id).html();


    jsonGOP[0]= datosGrupoOS;

    param.jsonGOS=JSON.stringify({"consultaGrupoOS":jsonGOP});

    sendServiceJSON(url,param,BackOfficeGruposOpcionesPermisosSuccess,null,null);
}


function BackOfficeGruposOpcionesPermisosSuccess(originalRequest){
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var grupo = result.datosGrupoOS;

    $("#div_tabla_consultaGOP").fadeIn("fast");
    $("#div_tabla_consultaGOS").fadeOut("fast");

    var tabla="tabla_grupoOpcionesPermisos";
    $('#Legend_grupoOpcionesSistemaPermisos').html(opcion);
    crearTabla("div_tabla_grupoOpcionesPermisos",tabla,columnas,datos);

    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function cambiarValor(id){
    if($("#"+id).is(':checked')){
        $("#"+id).attr("value","1");
    }else
        $("#"+id).attr("value","0");
}

function BackOfficeSalvarAccionesGruposJSONData(acciones){
    var url = urlBackOfficeSalvarGOP;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= acciones;
    jsonGOP[1]= grupoID;
    jsonGOP[2]= opcionID;


    param.jsonGOS=JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,BackOfficeSalvarAccionesSuccess,null,null);
}


function BackOfficeSalvarAccionesSuccess(originalRequest){
    var result = originalRequest;

    var res = result.mensaje;
//    idioma = result.idioma;
    if(res=="OK"){
        alert("Los cambios fueron realizados");
        $("#div_tabla_consultaGOS").fadeIn("fast");
        $("#div_tabla_consultaGOP").fadeOut("fast");
    }else{
        alert("Los cambios no han podido ser realizados");
    }



//    alert("regrese");
//    var columnas = result.contenidoTabla_culumnasTest;
//    var datos = result.contenidoTabla_infoTest;
//    var grupo = result.datosGrupoOS;
//
//    $("#div_tabla_consultaGOP").fadeIn("fast");
//    $("#div_tabla_consultaGOS").fadeOut("fast");
//
//    var tabla="tabla_grupoOpcionesPermisos";
//    $('#Legend_grupoOpcionesSistemaPermisos').html(opcion);
//    crearTabla("div_tabla_grupoOpcionesPermisos",tabla,columnas,datos);
//
//    var oTable = $('#'+tabla).dataTable({
//        "bJQueryUI": true,
//        "sPaginationType": "full_numbers"
//    });


}







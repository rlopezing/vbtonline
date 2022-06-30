var urlBackOfficeUOS="BackOffice_consultarUsuarioOpcionesSistemas.action";
var urlBackOfficeUOP="BackOffice_consultarUsuariosOpcPermisos.action";
var urlBackOfficeSalvarUOP="BackOffice_salvarPermisosUsuarios.action";
var urlBackOfficeEliminarUOP="BackOffice_eliminarPermisosUsuarios.action";


$(document).ready(function(){

});


function BackOfficeUsuarioOpcionesSistemaJSONData(id){
    var url = urlBackOfficeUOS;
    var param={};
    var jsonBackOffice=[];


//    datosGrupoOS.grupo = grupo;

    jsonBackOffice[0]= id;

    param.jsonBackOffice=JSON.stringify({"parametros":jsonBackOffice});

    sendServiceJSON(url,param,BackOfficeUsuariosOpcionesSistemasSuccess,null,null);
}


function BackOfficeUsuariosOpcionesSistemasSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
//    var grupo = result.datosGrupoOS;

//    $("#div_tabla_consultaGOS").fadeIn("fast");
//    $("#div_tabla_consultaG").fadeOut("fast");

    var tabla="tabla_UsuarioOpcionesSistema";
//    $('#Legend_grupoOpcionesSistema').html(grupo.grupo);
    crearTabla("div_tabla_UsuarioOpcionesSistema",tabla,columnas,datos);

    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "sScrollY": "300px",
        "bPaginate": false,
        "bScrollCollapse": true,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function BackOfficeUsuarioOpcionesSistemaCUCJSONData(id){
    var url = urlBackOfficeUOS;
    var param={};
    var jsonBackOffice=[];


//    datosGrupoOS.grupo = grupo;

    jsonBackOffice[0]= id;

    param.jsonBackOffice=JSON.stringify({"parametros":jsonBackOffice});

    sendServiceJSON(url,param,BackOfficeUsuariosOpcionesSistemasCUCSuccess,null,null);
}


function BackOfficeUsuariosOpcionesSistemasCUCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
//    var grupo = result.datosGrupoOS;

//    $("#div_tabla_consultaGOS").fadeIn("fast");
//    $("#div_tabla_consultaG").fadeOut("fast");

    var tabla="tabla_UsuarioOpcionesSistema_CUC";
//    $('#Legend_grupoOpcionesSistema').html(grupo.grupo);
    crearTabla("div_tabla_UsuarioOpcionesSistema_CUC",tabla,columnas,datos);

    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 20,
        "bJQueryUI": true,
//        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "sScrollY": "300px",
        "bPaginate": false,
        "bScrollCollapse": true,
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

function BackOfficeUsuarioOpcionesPermisosJSONData(id,usuario){
    var url = urlBackOfficeUOP;
    var param={};
    var jsonBackOffice=[];

    jsonBackOffice[0]= id;
    jsonBackOffice[1]= usuario;
    $("#Legend_UsuarioOpcionesSistemaPermisos").html($("#"+id).html());
    $("#codOpc").val(id);

    param.jsonBackOffice=JSON.stringify({"parametros":jsonBackOffice});
    sendServiceJSON(url,param,BackOfficeUsuarioOpcionesPermisosSuccess,null,null);
}


function BackOfficeUsuarioOpcionesPermisosSuccess(originalRequest){
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;


    var tabla="tabla_usuarioOpcionesPermisos";
//    $('#Legend_UsuarioOpcionesSistemaPermisos').html(opcion);
    crearTabla("div_tabla_usuarioOpcionesPermisos",tabla,columnas,datos);

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

function BackOfficeUsuarioOpcionesPermisosCUCJSONData(id,usuario){
    var url = urlBackOfficeUOP;
    var param={};
    var jsonBackOffice=[];

    jsonBackOffice[0]= id;
    jsonBackOffice[1]= usuario;
    $("#Legend_UsuarioOpcionesSistemaPermisos_CUC").html($("#"+id).html());
    $("#codOpc_CUC").val(id);

    param.jsonBackOffice=JSON.stringify({"parametros":jsonBackOffice});
    sendServiceJSON(url,param,BackOfficeUsuarioOpcionesPermisosCUCSuccess,null,null);
}


function BackOfficeUsuarioOpcionesPermisosCUCSuccess(originalRequest){
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;


    var tabla="tabla_usuarioOpcionesPermisos_CUC";
//    $('#Legend_UsuarioOpcionesSistemaPermisos').html(opcion);
    crearTabla("div_tabla_usuarioOpcionesPermisos_CUC",tabla,columnas,datos);

    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function cambiarValorEU(id){
    if($("#"+id).is(':checked')){
        $("#"+id).attr("value","1");
    }else
        $("#"+id).attr("value","0");
}


function BackOfficeSalvarAccionesUsuarioJSONData(acciones, usuario, opcion){
    var url = urlBackOfficeSalvarUOP;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= acciones;
    jsonGOP[1]= usuario;
    jsonGOP[2]= opcion;


    param.jsonGOS=JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,BackOfficeSalvarAccionesUsuariosSuccess,null,null);
}


function BackOfficeSalvarAccionesUsuariosSuccess(originalRequest){
    var result = originalRequest;

    var valido = result.mensaje;
    if(valido!=null){
       alert("Los Permisos del usuario sobre la opci\u00f3n \nseleccionada, han sido guardados satisfactoriamente.");
    }else{
        alert("Ha ocurrido un error al intentar guardar los permisos \ndel usuario. Comun\u00edquelo al departamento de sistemas");
    }

}


function BackOfficeEliminarAccionesUsuarioJSONData(acciones, usuario){
    var url = urlBackOfficeEliminarUOP;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= acciones;
    jsonGOP[1]= usuario;


    param.jsonGOS=JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,BackOfficeEliminarAccionesUsuariosSuccess,null,null);
}





function BackOfficeEliminarAccionesUsuariosSuccess (originalRequest){
    var result = originalRequest;

    var valido = result.mensaje;
    if(valido!=null){
        $("#tabla_usuarioOpcionesPermisos .verificarSelecccion").each(function(){
            $(this).attr("checked",false);
        });
        alert("Los Permisos del usuario sobre la opci\u00f3n \nseleccionada, han sido eliminado satisfactoriamente.");
    }else{
        alert("Ha ocurrido un error al intentar eliminar los permisos \ndel usuario. Comun\u00edquelo al departamento de sistemas");
    }

}


function BackOfficeEliminarAccionesUsuarioCJSONData(acciones, usuario){
    var url = urlBackOfficeEliminarUOP;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= acciones;
    jsonGOP[1]= usuario;


    param.jsonGOS=JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,BackOfficeEliminarAccionesUsuariosCSuccess,null,null);
}



function BackOfficeEliminarAccionesUsuariosCSuccess (originalRequest){
    var result = originalRequest;

    var valido = result.mensaje;
    if(valido!=null){
        $("#div_tabla_usuarioOpcionesPermisos_CUC .verificarSelecccion").each(function(){
            $(this).attr("checked",false);
        });
        alert("Los Permisos del usuario sobre la opci\u00f3n \nseleccionada, han sido eliminado satisfactoriamente.");
    }else{
        alert("Ha ocurrido un error al intentar eliminar los permisos \ndel usuario. Comun\u00edquelo al departamento de sistemas");
    }

}
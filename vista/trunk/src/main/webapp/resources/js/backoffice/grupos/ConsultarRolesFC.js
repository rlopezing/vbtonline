var urlBackOfficeRolesFC="BackOffice_consultarRolesFC.action";
var urlBackOfficeOpcionesRol="BackOffice_consultarRolesOpcionesSistema.action";
var urlBackOfficeAOPRol="BackOffice_actualizarRolOpcionesSistemaEsquema.action";
var urlBackOfficeOpcionesFC="BackOffice_consultarAccionesFC.action";
var urlBackOfficeactualizarOpcionFC="BackOffice_actualizarOpcionesSistemaFC.action";

$(document).ready(function(){
    $("#btnBackRoles").click(function() {
        $("#div_tabla_consultaRoles").fadeIn("fast");
        $("#div_tabla_consultaROS").fadeOut("fast");
    });

    $("#btnEditRoles").click(function() {
//        BackOfficeRolesJSONData();
        BackOfficeAccionesJSONData();
    });
    $("#btnRolesVoler").click(function() {
//        BackOfficeAccionesJSONData();
        BackOfficeRolesJSONData();
        $("#tabla_consultaOpciones").empty();
    });

});


function BackOfficeRolesJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeRolesFC;
    var param={};

    sendServiceJSON(url,param,BackOfficRolesSuccess,null,null);
}


function BackOfficRolesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    $("#div_carga_espera").addClass("oculto");

    var tabla="tabla_consultarRoles";
//    $("#div_tabla_consultaGOP").attr("style","display:none");
    $("#div_tabla_consultaRoles").attr("style","display:''");
    $("#div_tabla_consultaROS").attr("style","display:none");
    $("#div_tabla_opciones").attr("style","display:none");
    $("#div_tabla_consultaOpciones").empty();
    // crearTabla("div_tabla_consultarRoles",tabla,columnas,datos);
    crearTablaV3("div_tabla_consultarRoles",tabla,columnas,datos);

/*    var oTable = $('#'+tabla).dataTable({
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
    });*/
    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "left" },
            { "sClass": "center" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}


function seleccionarRolOpcion(id){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeOpcionesRol;
    var param={};
    var jsonGOS=[];
    var datosGrupoOS={};
    datosGrupoOS.grupoCategoria =id.split("|")[0];
    grupoID = id;
    datosGrupoOS.grupo = id.split("|")[0];
    $('#Legend_rolOpcionesSistema').html(id.split("|")[1]);
//    datosGrupoOS.grupo = grupo;

    jsonGOS[0]= datosGrupoOS;

    param.jsonGOS=JSON.stringify({"consultaGrupoOS":jsonGOS});

    sendServiceJSON(url,param,BackOfficeRolesOpcionesSistemasSuccess,null,null);
}


function BackOfficeRolesOpcionesSistemasSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    $("#div_carga_espera").addClass("oculto");
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var rol = result.datosGrupoOS;


    if (result.codigo=="0000000005"){
        var p_sclass=[
            { "sClass": "center" },
            { "sClass": "left" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" }
        ];
    }else{
        var p_sclass=[
            { "sClass": "center" },
            { "sClass": "left" },
            { "sClass": "center" }
        ];

    }


//    var p_sclass=[
//        { "sClass": "center" },
//        { "sClass": "left" },
//        { "sClass": "center" }
//    ];
    $("#div_tabla_consultaROS").attr("style","display:''");
    $("#div_tabla_consultaRoles").attr("style","display:none");

    $("#div_tabla_consultaRos").fadeIn("fast");
    $("#div_tabla_consultaRoles").fadeOut("fast");

    $("#tabla_UsuarioOpcionesSistema").empty();
    $("#tabla_UsuarioOpcionesSistema_CUC").empty();
    $("#div_tabla_grupoOpcionesSistema").empty();
    $("#div_tabla_rolesOpcionesSistema").empty();
    $("#tabla_AccesosSistema").empty();

    var tabla="tabla_rolOpcionesSistema";

    // crearTabla("div_tabla_rolOpcionesSistema",tabla,columnas,datos);
    crearTablaV3("div_tabla_rolOpcionesSistema",tabla,columnas,datos);

/*    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        /!*"sPaginationType": "full_numbers",*!/
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": false,
        /*"sPaginationType": "full_numbers",*/
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}


function guardarOpcionRol(id) {
    var codtipacc = id.split("_")[0];
    var copopc = id.split("_")[1];
    var tipacc = id.split("_")[2];
    var tipacc_old = id.split("_")[2];
    var arrtipacc = tipacc.split("");
    var pos = parseInt(codtipacc) - 1;
    if (arrtipacc[pos] == "0") {
//    if ($("#"+id).is(":checked")) {
        arrtipacc[pos] = "1";
    } else {
        arrtipacc[pos] = "0";
    }
    tipacc = arrtipacc.toString().replace(/\,/g,"");
    $("#div_carga_espera").removeClass("oculto");
    actualizarOpcionRolJSONData(id.split("_")[3],copopc,tipacc);
    latidoWeb();
    if (
        (opcionActualizada == "SUCCESS") ||
            (opcionActualizada == "")
        ) {
        if ($("#2_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).length != 0) {
            $("#1_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","1_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#2_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","2_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#3_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","3_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#4_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","4_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#5_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","5_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#6_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","6_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#7_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","7_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#8_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","8_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
        } else {
            $("#"+id).attr("id",codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]);
        }
        if (arrtipacc[pos] == "1"){
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","checked");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/checked.gif");
        } else {
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/unchecked.gif");
        }
        opcionActualizada = '';
    } else {
        if (arrtipacc[pos] == "0"){
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","checked");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/checked.gif");
        } else {
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/unchecked.gif");
        }
    }
}

function actualizarOpcionRolJSONData(grupo,opcion,permiso) {
    var url = urlBackOfficeAOPRol;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= grupo;
    jsonGOP[1]= opcion;
    jsonGOP[2]= permiso;

    param.jsonGOS = JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,actualizarOpcionRolSuccess,null,null);
}

function actualizarOpcionRolSuccess(originalRequest) {
    var result = originalRequest;
    cargarIdiomaJSONData_sinc();
    opcionActualizada = result.mensaje;
    $("#div_carga_espera").addClass("oculto");
    if (opcionActualizada == "SUCCESS") {
        $("#mensaje_generico").removeClass("success");
        $("#mensaje_generico").removeClass("wrong");
        $("#mensaje_generico").html(vbtol_props[idioma]["popupChangeActionsSuccess"]);
        $("#mensaje_generico").addClass("success");
    } else {
        $("#mensaje_generico").removeClass("success");
        $("#mensaje_generico").removeClass("wrong");
        $("#mensaje_generico").html(vbtol_props[idioma]["popupChangeActionsWrong"]);
        $("#mensaje_generico").addClass("wrong");
    }
    if ($("#poppup_mensaje_generico").is(":hidden")) {
        $("#poppup_mensaje_generico").fadeIn(1000);
        $("#poppup_mensaje_generico").fadeToggle(5000);
        $("#poppup_mensaje_generico").fadeOut(1000);
    }

}



//V2.0
function BackOfficeAccionesJSONData(){
    var url = urlBackOfficeOpcionesFC;
    var param={};
     $("#div_carga_espera").removeClass("oculto");
    sendServiceJSON(url,param,BackOfficeAccionesSuccess,null,null);
}


function BackOfficeAccionesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    $("#div_carga_espera").addClass("oculto");

    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" }
    ];

    var tabla="tabla_consultaOpciones";
//    $("#div_tabla_consultaGOP").attr("style","display:none");
    $("#div_tabla_opciones").attr("style","display:''");
//    $("#div_tabla_consultaROS").attr("style","display:none");
    $("#div_tabla_consultaRoles").attr("style","display:none");
    // crearTabla("div_tabla_consultaOpciones",tabla,columnas,datos);
    crearTablaV3("div_tabla_consultaOpciones",tabla,columnas,datos);

/*    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 100,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 100,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });
}




function guardarOpcionFC(id) {
    var codtipacc = id.split("_")[0];
    var copopc = id.split("_")[1];
    var tipacc = id.split("_")[2];
    var tipacc_old = id.split("_")[2];
    var arrtipacc = tipacc.split("");
    var pos = parseInt(codtipacc) - 1;
    if (arrtipacc[pos] == "0") {
//    if ($("#"+id).is(":checked")) {
        arrtipacc[pos] = "1";
    } else {
        arrtipacc[pos] = "0";
    }
    tipacc = arrtipacc.toString().replace(/\,/g,"");
    $("#div_carga_espera").removeClass("oculto");
    actualizarOpcionFCJSONData(copopc,tipacc);
    latidoWeb();
    if (
        (opcionActualizada == "SUCCESS") ||
            (opcionActualizada == "")
        ) {
        if ($("#2_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).length != 0) {
            $("#1_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","1_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#2_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","2_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#3_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","3_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#4_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","4_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#5_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","5_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#6_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","6_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#7_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","7_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
            $("#8_"+copopc+"_"+tipacc_old+"_"+id.split("_")[3]).attr("id","8_" + copopc + "_" + tipacc + "_" + id.split("_")[3]);
        } else {
            $("#"+id).attr("id",codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]);
        }
        if (arrtipacc[pos] == "1"){
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","checked");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/checked.gif");
        } else {
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/unchecked.gif");
        }
        opcionActualizada = '';
    } else {
        if (arrtipacc[pos] == "0"){
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","checked");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/checked.gif");
        } else {
//            $("#1_"+copopc+"_"+id.split("_")[2]+id.split("_")[3]).attr("checked","");
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/unchecked.gif");
        }
    }
}



function actualizarOpcionFCJSONData(opcion,permiso) {
    var url = urlBackOfficeactualizarOpcionFC;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= opcion;
    jsonGOP[1]= permiso;

    param.jsonGOS = JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,actualizarOpcionFCSuccess,null,null);
}

function actualizarOpcionFCSuccess(originalRequest) {
    var result = originalRequest;
    cargarIdiomaJSONData_sinc();
    opcionActualizada = result.mensaje;
    $("#div_carga_espera").addClass("oculto");
    if (opcionActualizada == "SUCCESS") {
        $("#mensaje_generico").removeClass("success");
        $("#mensaje_generico").removeClass("wrong");
        $("#mensaje_generico").html(vbtol_props[idioma]["popupChangeActionsSuccess"]);
        $("#mensaje_generico").addClass("success");
    } else {
        $("#mensaje_generico").removeClass("success");
        $("#mensaje_generico").removeClass("wrong");
        $("#mensaje_generico").html(vbtol_props[idioma]["popupChangeActionsWrong"]);
        $("#mensaje_generico").addClass("wrong");
    }
    if ($("#poppup_mensaje_generico").is(":hidden")) {
        $("#poppup_mensaje_generico").fadeIn(1000);
        $("#poppup_mensaje_generico").fadeToggle(5000);
        $("#poppup_mensaje_generico").fadeOut(1000);
    }

}
var urlBackOffice="BackOffice_consultarGrupos.action";
var urlBackOfficeGOS="BackOffice_consultarGruposOpcionesSistemas.action";
var urlBackOfficeGOSH="BackOffice_consultarGrupoOpcionesSistemaEsquema.action";
var urlBackOfficeAOP="BackOffice_actualizarGrupoOpcionesSistemaEsquema.action";
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
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOffice;
    var param={};

    sendServiceJSON(url,param,BackOfficeGruposSuccess,null,null);
}


function BackOfficeGruposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    $("#div_carga_espera").addClass("oculto");
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    //$("#marco_trabajo").css("height","1000px");
    var tabla="tabla_consultarGrupos";
    $("#div_tabla_consultaG").attr("style","display:''");
    $("#div_tabla_consultaGOS").attr("style","display:none");
    $("#div_tabla_consultaGOP").attr("style","display:none");


    // crearTabla("div_tabla_consultarGrupos",tabla,columnas,datos);
    crearTablaV3("div_tabla_consultarGrupos",tabla,columnas,datos,"",false);

/*
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
*/

    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "left" },
            { "sClass": "center" },
            { "sClass": "center" },
        ],
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
//    BackOfficeGruposOpcionesSistemaJSONData(id);
    BackOfficeGruposOpcionesSistemaeEsquemaJSONData(id);
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

function BackOfficeGruposOpcionesSistemaeEsquemaJSONData(id){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeGOSH;
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
    $("#div_carga_espera").addClass("oculto");
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var grupo = result.datosGrupoOS;
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

    $("#div_tabla_consultaGOS").fadeIn("fast");
    $("#div_tabla_consultaG").fadeOut("fast");

    $("#tabla_UsuarioOpcionesSistema").empty();
    $("#tabla_UsuarioOpcionesSistema_CUC").empty();
    $("#div_tabla_grupoOpcionesSistema").empty();
    $("#tabla_AccesosSistema").empty();
    $("#div_tabla_rolesOpcionesSistema").empty();
    $("#tabla_consultaOpciones").empty();
    $("#tabla_rolOpcionesSistema").empty();


    var tabla="tabla_grupoOpcionesSistema";
    $('#Legend_grupoOpcionesSistema').html(grupo.grupo);
    // crearTabla("div_tabla_grupoOpcionesSistema",tabla,columnas,datos);
    crearTablaV3("div_tabla_grupoOpcionesSistema",tabla,columnas,datos);

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

function asignarEstiloPadre(id,par) {
    if (par == 0) {
        ($("#"+id).parent()).removeClass("even");
        ($("#"+id).parent()).removeClass("odd");
        ($("#"+id).parent()).addClass("even");
    } else {
        ($("#"+id).parent()).removeClass("even");
        ($("#"+id).parent()).removeClass("odd");
        ($("#"+id).parent()).addClass("odd");
    }
}

function ocultarFila(id,par) {
    try {
        (($("#"+id).parent()).parent()).addClass("oculto");
        if ($("#"+($("#"+id).val())).val() == "") {
            $("#"+($("#"+id).val())).val(id);
        } else {
            $("#"+($("#"+id).val())).val($("#"+($("#"+id).val())).val() + "_" + id);
        }
    } catch(excepcion) {
        //alert(excepcion);
    }
}

function abrirDetalle(id) {
    var i = 0;
    if ($("#"+id).val() != '') {
        if ($('#img'+id).attr("src") == "resources/images/comun/nolines_plus.gif") {
            $('#img'+id).attr("src", "resources/images/comun/nolines_minus.gif");
            for (i = 0; i < $("#"+id).val().split("_").length; i++) {
                ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).removeClass("even");
                ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).removeClass("odd");
                if ((i % 2) == 0) {
                    ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).addClass("even_sub");
                } else {
                    ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).addClass("odd_sub");
                }
                ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).removeClass("oculto");
            }
        } else {
            $('#img'+id).attr("src", "resources/images/comun/nolines_plus.gif");
            for (i = 0; i < $("#"+id).val().split("_").length; i++) {
                ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).removeClass("even");
                ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).removeClass("odd");
                if ((i % 2) == 0) {
                    ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).addClass("even_sub");
                } else {
                    ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).addClass("odd_sub");
                }
                ((($("#"+($("#"+id).val().split("_")[i]))).parent()).parent()).addClass("oculto");
            }
        }
    }
    latidoWeb();
}

function guardarOpcion(id) {
    var codtipacc = id.split("_")[0];
    var copopc = id.split("_")[1];
    var tipacc = id.split("_")[2];
    var tipacc_old = id.split("_")[2];
    var arrtipacc = tipacc.split("");
    var pos = parseInt(codtipacc) - 1;

    $("#div_carga_espera").removeClass("oculto");
    if (arrtipacc[pos] == "0") {
//    if ($("#"+id).is(":checked")) {
        arrtipacc[pos] = "1";
    } else {
        arrtipacc[pos] = "0";
    }
    tipacc = arrtipacc.toString().replace(/\,/g,"");
    actualizarOpcionJSONData(id.split("_")[3],copopc,tipacc);
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

function actualizarOpcionJSONData(grupo,opcion,permiso) {
    var url = urlBackOfficeAOP;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= grupo;
    jsonGOP[1]= opcion;
    jsonGOP[2]= permiso;

    param.jsonGOS = JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,actualizarOpcionSuccess,null,null);
}

function actualizarOpcionSuccess(originalRequest) {
    var result = originalRequest;
    $("#div_carga_espera").addClass("oculto");
    cargarIdiomaJSONData_sinc();
    opcionActualizada = result.mensaje;
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

function seleccionarGrupoSistemaOpcion(id){

    BackOfficeGruposOpcionesPermisosJSONData(id);
}

function BackOfficeGruposOpcionesPermisosJSONData(id){

    $("#div_carga_espera").removeClass("oculto");
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
    $("#div_carga_espera").addClass("oculto");

    $("#div_tabla_consultaGOP").fadeIn("fast");
    $("#div_tabla_consultaGOS").fadeOut("fast");

    var tabla="tabla_grupoOpcionesPermisos";
    $('#Legend_grupoOpcionesSistemaPermisos').html(opcion);
    // crearTabla("div_tabla_grupoOpcionesPermisos",tabla,columnas,datos);
    crearTablaV3("div_tabla_grupoOpcionesPermisos",tabla,columnas,datos);

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







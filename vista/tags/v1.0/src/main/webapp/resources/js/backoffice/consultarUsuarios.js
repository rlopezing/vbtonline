var urlBackOfficeConsultarUsuarios="BackOffice_consultarUsuarios.action";
var urlBackOfficeConsultaUsuario="BackOffice_consultaUsuario.action";
var urlBackOfficeEditarUsuario="BackOffice_editarUsuario.action";
var urlBackOfficeAgregarUsuario="BackOffice_agregarUsuario.action";
var urlBackOfficeEnviarClaveUsuarioBackoffice="Security_enviarClaveUsuarioBackoffice.action";
var urlBackOfficeGuardarLog = "BackOffice_guardarLog.action";

var usuarioEditar;
var hdnCambioEstatus = "No";
var oTable="";
var noInfo="";

$(document).ready(function(){

    $('#tabla_consultarUsuarios tbody td img').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsUsuario($(this).attr("id")), 'details');
        }

//        alert("hizo click : "+);
    } );


    $("#agregarUsuarioCU").click(function(){
       $("#div_tabla_consultaCU").fadeOut("fast");
       $("#div_tabla_agregarCU").fadeIn("fast");

        $("#div_USUARIOS .obligatorio_AUBO").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarCU");
//
    });

    $("#CU_Cancelar_AU").click(function(){
        $(".obligatorio_AUBO").each(function(){
           if($("#"+this.id).hasClass("error_campo"))
             $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarCU");
        $("#div_tabla_consultaCU").fadeIn("fast");
        $("#div_tabla_agregarCU").fadeOut("fast");
//        BackOfficeUsuariosJSONData();
//        $("#div_CU_agregarUsuario").fadeIn("fast");
    });

    $("#btnBackEU").click(function(){

        blanquearFormularios("div_tabla_editarUsuario");
        $(".obligatorio_EUBO").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        $("#div_tabla_consultaCU").fadeIn("fast");
        $("#div_tabla_editarUsuario").fadeOut("fast");
        BackOfficeUsuariosJSONData();
//        $("#div_CU_agregarUsuario").fadeIn("fast");
    });


    $("#btnDeshacerEU").click(function(){

        blanquearFormularios("div_tabla_editarUsuario");

//        $("#div_CU_agregarUsuario").fadeIn("fast");
    });

    $("#CU_Resetear_AU").click(function(){
        $(".obligatorio_AUBO").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarCU");
    });

    $("#CU_reset").click(function(){
        blanquearFormularios("div_tabla_consultaCU");
    });

    $("#CU_search").click(function(){

        BackOfficeUsuariosJSONData();

    });



    $("#CU_AgregarUsuario_AU").click(function(){

        var mensaje="";
        $("#formularioAgregarUsuario .obligatorio_AUBO").each(function(){
            if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#AU_email").get(0).value)){
                if($("#AU_email").hasClass("error_campo"))
                    $("#AU_email").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                confirm("Se ingresar\u00e1n los datos de un nuevo usuario.\n¿Est\u00e1 seguro de que los datos son correctos?");
                 BackOfficeSalvarAgregarUsuarioJSONData()
            }else{
                mensaje = "Introduzca una direcci&oacute;n de correo electr&oacute;nico v&aacute;lida";
                $("#AU_email").addClass("error_campo");
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }

//            $(".div_tabla_consulta").fadeIn("fast");
        }

    });

    $("#btnDeshacerEU").click(function(){
        $("#usuarioEU").html(usuarioEditar.login);
        $("#nombreEU").attr("value",usuarioEditar.nombres);
        $("#tipoPersonaEU").attr("value",usuarioEditar.tipo_cirif);
        $("#estatusEU").attr("value",usuarioEditar.status_cuenta);
        $("#ciRifEU").attr("value",usuarioEditar.cirif);
        $("#emailEU").attr("value",usuarioEditar.email);
        $("#telefonoEU").attr("value",usuarioEditar.telefono);

        if((usuarioEditar.telefono_celular!=null)&&(usuarioEditar.telefono_celular!="null")&&(usuarioEditar.telefono_celular!=""))
            $("#telefonoCelEU").attr("value",usuarioEditar.telefono_celular);
        else
            $("#telefonoCelEU").attr("value","");

        $("#direccionEU").attr("value",usuarioEditar.direccion);
        $("#grupoEU").attr("value",usuarioEditar.tipo);
        $("#ambitoEU").attr("value",usuarioEditar.ambito);
    });
//    btnDeshacerEU
    $("#btnSalvarEU").click(function(){

        var mensaje="";
        $("#formularioEditarUsuario .obligatorio_EUBO").each(function(){
            if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#emailEU").get(0).value)){
                if($("#emailEU").hasClass("error_campo"))
                    $("#emailEU").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                if(confirm("Se actualizar\u00e1n los datos del usuario \n ¿Est\u00e1 que los datos son correctos?")){
                 BackOfficeSalvarEditarUsuarioJSONData();   }
            }else{
                mensaje = "Introduzca una direcci&oacute;n de correo electr&oacute;nico v&aacute;lida";
                $("#emailEU").addClass("error_campo");
                $("#mensaje_error").empty();
                $("#mensaje_error").html("datos invalidos "+mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }

//            $(".div_tabla_consulta").fadeIn("fast");
        }

    });

    $("#btnResetearPasswordEU").click(function(){
        if(confirm("Se reiniciar\u00e1 la clave del usuario.\n ¿Est\u00e1 seguro?")){
            BackOfficeEnviarClaveUsuarioBJSONData();
        }

    });

    $("#btnAccesosEspecialesEU").click(function(){
        usuario="btnAccesosEspecialesEU";
        $("#div_tabla_UsuarioOpcionesSistema").empty();
        $("#div_tabla_consultaUsuarioOpcionesPermiso").attr("style","display: none");
        $("#usuarioOpcionesSistema").attr("style","display: ");
        BackOfficeUsuarioOpcionesSistemaJSONData($("#grupoEU").val());

        $("#div_tabla_consultaOpcionesSistema").lightbox_me({centered: true, onLoad: function() {
            $("#div_tabla_consultaOpcionesSistema").find("input:first").focus();
        }});
//            e.preventDefault();
    });

    $("#CUOP_back_CU").click(function(){
        $("#div_tabla_consultaUsuarioOpcionesPermiso").attr("style","display: none");
        $("#usuarioOpcionesSistema").attr("style","display: ");
    });

    $("#CUOP_save_CU").click(function(){
        var misAcciones ="";
        var accionesCero="";
        $("#tabla_usuarioOpcionesPermisos .verificarSelecccion").each(function(){
            misAcciones   = misAcciones + (($(this).is(":checked"))?"1":"0");
            accionesCero  = accionesCero + "0";
        });
       /* if(misAcciones==accionesCero){
            $("#mensaje_error").empty();
            $("#mensaje_error").html('Debe seleccionar al menos un valor');
            $("#div_mensajes_error").fadeIn("slow");
          */
       // }else{
       //     $("#div_mensajes_error").fadeOut("fast");
            BackOfficeSalvarAccionesUsuarioJSONData(misAcciones, $("#usuarioEU").html(), $("#codOpc").val());
      //  }

    });

    $("#CUOP_eliminar_CU").click(function(){
            BackOfficeEliminarAccionesUsuarioJSONData( $("#usuarioEU").html(), $("#codOpc").val());
    });

});


function seleccionarUsuarioBSistemaOpcion(id){
    usuario="btnAccesosEspecialesEU";
    $("#div_tabla_usuarioOpcionesPermisos").empty();
//    BackOfficeUsuarioOpcionesSistemaJSONData($("#grupoEU").val());
    BackOfficeUsuarioOpcionesPermisosJSONData(id,$("#usuarioEU").html());

    $("#div_tabla_consultaUsuarioOpcionesPermiso").attr("style","display: ");
    $("#usuarioOpcionesSistema").attr("style","display: none");
}

function fnFormatDetailsUsuario (valor)
{   var url = urlBackOfficeGuardarLog;
    var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="bac_usuario_cirif_det">C.I./R.I.F </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuario_telefono_det">Tel&eacute;fono </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuario_fecha_det">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuario_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuario_direccion_det">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="bac_usuario_cirif_det">C.I./R.I.F </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuario_telefono_det">Tel&eacute;fono </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuario_fecha_det">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuario_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuario_direccion_det">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }

    var param={};
    var jsonEditarUsuario=[];
    var datosUsuarioUBO={};
    datosUsuarioUBO.login = valor.split("|")[5];
    jsonEditarUsuario[0]= datosUsuarioUBO;
    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});
    param.codOpc="DT";
    sendServiceJSON(url,param,'',null,null);

    return sOut;

}


function BackOfficeEnviarClaveUsuarioBJSONData(id){
    var url = urlBackOfficeEnviarClave;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU").html();

    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON(url,param,BackOfficeEnviarClaveUsuarioBSuccess,null,null);
}


function BackOfficeEnviarClaveUsuarioBSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;

    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("La clave del usuario fue reiniciada satisfactoriamente.\nLa nueva clave ha sido enviada a la direcci&oacute;n de correo registrada.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}


function BackOfficeUsuariosJSONData(){
    var url = urlBackOfficeConsultarUsuarios;
    var param={};
    var jsonConsultaUsuarios=[];
    var datosConsultaUBO={};

    //$("#marco_trabajo").css("height","900px");
    $("#div_tabla_consultaCU").attr("style","display:block");
    $("#div_tabla_agregarCU").attr("style","display:none");
    $("#div_tabla_editarUsuario").attr("style","display:none");

    if($("#OJO_CU_usuarioFiltro").get(0).value!=null)
    {
        datosConsultaUBO.strTxtUsuario=$("#OJO_CU_usuarioFiltro").get(0).value;
    }else
        datosConsultaUBO.strTxtUsuario="";
    if($("#CU_nombreFiltro").get(0).value!=null)
        datosConsultaUBO.strTxtNombre=$("#CU_nombreFiltro").get(0).value;
    else
        datosConsultaUBO.strTxtNombre="";
    if($("#CU_CIRIF").get(0).value!=null)
        datosConsultaUBO.strTxtCIDRIF=$("#CU_CIRIF").get(0).value;
    else
        datosConsultaUBO.strTxtCIDRIF="";
    if($("#CU_grupoFiltro").get(0).value!="-2")
        datosConsultaUBO.strCmbTipoUsuario=$("#CU_grupoFiltro").get(0).value;
    else
        datosConsultaUBO.strCmbTipoUsuario="Default";
    if($("#CU_estatusFiltro").get(0).value!="-2")
        datosConsultaUBO.strCmbEstatus=$("#CU_estatusFiltro").get(0).value;
    else
        datosConsultaUBO.strCmbEstatus="Default";

    if($("#CU_ambitoFiltro").get(0).value!="-2")
        datosConsultaUBO.strCmbAmbito=$("#CU_ambitoFiltro").get(0).value;
    else
        datosConsultaUBO.strCmbAmbito="Default";

    if($("#OJO_CU_usuarioFiltro").get(0).value=='' && $("#CU_nombreFiltro").get(0).value=='' &&
        $("#CU_CIRIF").get(0).value=='' && ($("#CU_grupoFiltro").get(0).value=="-2" || $("#CU_grupoFiltro").get(0).value=='' )
        && ($("#CU_estatusFiltro").get(0).value=="-2" || $("#CU_estatusFiltro").get(0).value=='' )
        && ($("#CU_ambitoFiltro").get(0).value=="-2" || $("#CU_ambitoFiltro").get(0).value=='' ) ){
        datosConsultaUBO.hdnAccion=null;
    }else{
        datosConsultaUBO.hdnAccion="Buscar";
    }

    jsonConsultaUsuarios[0]= datosConsultaUBO;

    param.jsonConsultaUsuarios=JSON.stringify({"consultaUsuarioss":jsonConsultaUsuarios});

    sendServiceJSON(url,param,BackOfficeUsuariosSuccess,null,null);
}


function BackOfficeUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupos = result.tipoGrupo;
    tipoEstatus = result.tipoEstatus;
    tipoCiRif = result.tipoCiRif;
    tipoAmbito = result.tipoAmbito;
    primeraVez = result.primeraVez;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "left"},
        {  "sClass": "left" },
        {  "sClass": "center" },
        {  "sClass": "center" },
        {  "sClass": "center" }
    ];

    if(primeraVez == "SI"){
        cargar_select("CU_grupoFiltro",tipoGrupos);
        cargar_select("AU_grupo",tipoGrupos);
        cargar_select("grupoEU",tipoGrupos);
        cargar_select("CU_estatusFiltro",tipoEstatus);
        cargar_select("estatusEU",tipoEstatus);
        cargar_select("tipoPersonaEU",tipoCiRif);
        cargar_select("AU_tipoPersona",tipoCiRif);
        cargar_select("CU_ambitoFiltro",tipoAmbito);
        cargar_select("AU_ambito",tipoAmbito);
        cargar_select("ambitoEU",tipoAmbito);

    }
    privilegiosDatosUsuarios();



    //$("#marco_trabajo").css("height","1400px");
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consultarUsuarios";
    var id_contenedor="div_tabla_consultarUsuarios";
    tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
    //crearTabla("div_tabla_consultarUsuarios",tabla,columnas,datos);

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
                display     : 8,
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
    /*oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "aoColumns": p_sclass,
        "sPaginationType": "full_numbers",
        "oLanguage": {
              "sSearch": "Buscar:", "sInfo":"Mostrando _START_ de _END_ de _TOTAL_ entidades"
            }

    });*/



}

function guardarlogusu(contenidoTabla_info,reg_inicio,tam_pagina){
    var url = urlBackOfficeGuardarLog;
    for(var cantidad=reg_inicio;cantidad<(reg_inicio+tam_pagina);cantidad++){
        if(cantidad<contenidoTabla_info.length) {
            var data=contenidoTabla_info[cantidad];

                var usuario =data.data_culumn[1].data_columna.split("<b>")[1];
                    var param={};
                    var jsonEditarUsuario=[];
                    var datosUsuarioUBO={};
                    datosUsuarioUBO.login =usuario;
                    jsonEditarUsuario[0]= datosUsuarioUBO;
                    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});
                    param.codOpc="GN";
                    sendServiceJSON(url,param,'',null,null);
        }else{
            cantidad=  (reg_inicio+tam_pagina);
        }
    }
}

function BackOfficeEditarUsuarioJSONData(id, tipoGrupo){
    var url = urlBackOfficeConsultaUsuario;
    var param={};
    var jsonEditarUsuario=[];
    var datosUsuarioUBO={};

    datosUsuarioUBO.login = id;
    datosUsuarioUBO.tipo_grupo = tipoGrupo;

    jsonEditarUsuario[0]= datosUsuarioUBO;

    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});

    sendServiceJSON(url,param,BackOfficeEditarUsuariosSuccess,null,null);
}

function BackOfficeEditarUsuariosSuccess(originalRequest){
        //                   this is the json return data
        var result = originalRequest;
        usuarioEditar = result.usuario;



    $("#usuarioEU").html(usuarioEditar.login);
    $("#nombreEU").attr("value",usuarioEditar.nombres);
    $("#tipoPersonaEU").attr("value",usuarioEditar.tipo_cirif);
    $("#grupoEU").attr("value",usuarioEditar.tipo);
    $("#estatusEU").attr("value",usuarioEditar.status_cuenta);
    $("#ciRifEU").attr("value",usuarioEditar.cirif);
    $("#emailEU").attr("value",usuarioEditar.email);
    $("#telefonoEU").attr("value",usuarioEditar.telefono);
    $("#direccionEU").attr("value",usuarioEditar.direccion);
    $("#ambitoEU").attr("value",usuarioEditar.ambito);

     $("#estatusEU").attr("disabled",!privilegio_editar_estatusUsuarioBackoffice);

    if((usuarioEditar.telefono_celular!=null)&&(usuarioEditar.telefono_celular!="null")&&(usuarioEditar.telefono_celular!=""))
        $("#telefonoCelEU").attr("value",usuarioEditar.telefono_celular);
    else
        $("#telefonoCelEU").attr("value","");

}


function seleccionarUsuariosOpcion(id,tipoGrupo){
    $("#div_tabla_consultaCU").fadeOut("fast");
    $("#div_tabla_editarUsuario").fadeIn("fast");
    hdnCambioEstatus = "No";
//    alert(tipoGrupo);
    BackOfficeEditarUsuarioJSONData(id,tipoGrupo);
}

function BackOfficeSalvarEditarUsuarioJSONData(){
    var url = urlBackOfficeEditarUsuario;
    var param={};
    var jsonEditarUsuario=[];
    var datosUsuarioUBO={};

    datosUsuarioUBO.login = $("#usuarioEU").html();
    datosUsuarioUBO.nombres = $("#nombreEU").get(0).value;
    datosUsuarioUBO.tipo_cirif = $("#tipoPersonaEU").get(0).value;
    datosUsuarioUBO.cirif = $("#ciRifEU").get(0).value;
    datosUsuarioUBO.email = $("#emailEU").get(0).value;
    datosUsuarioUBO.telefono = $("#telefonoEU").get(0).value;
    datosUsuarioUBO.telefono_celular = $("#telefonoCelEU").get(0).value;
    datosUsuarioUBO.direccion = $("#direccionEU").get(0).value;
    datosUsuarioUBO.tipo = $("#grupoEU").get(0).value;
    datosUsuarioUBO.ambito = $("#ambitoEU").get(0).value;

    if ($("#grupoEU").get(0).value =="0")
        datosUsuarioUBO.tipo_grupo = "0000000001";
    else if ($("#grupoEU").get(0).value =="1")
        datosUsuarioUBO.tipo_grupo = "0000000002";
    else if ($("#grupoEU").get(0).value =="-1")
        datosUsuarioUBO.tipo_grupo = "0000000003";
    else
        datosUsuarioUBO.tipo_grupo = "0000000001";

//    datosUsuarioUBO.tipo_grupo = $("#grupoEU").get(0).value;
    datosUsuarioUBO.status_cuenta = $("#estatusEU").get(0).value;
    datosUsuarioUBO.cambioStatus = hdnCambioEstatus;


    jsonEditarUsuario[0]= datosUsuarioUBO;

    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});

    sendServiceJSON(url,param,BackOfficeSalvarEditarUsuariosSuccess,null,null);
}


function BackOfficeSalvarEditarUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var men = result.mensaje;
    var codigo = result.codigo;

        $("#mensaje_error").empty();
        $("#mensaje_error").html(men);
        $("#div_mensajes_error").fadeIn("slow");

}

function cambioEstatus() {
//    alert("entre");
    hdnCambioEstatus = "Si";
}

function BackOfficeSalvarAgregarUsuarioJSONData(){
    var url = urlBackOfficeAgregarUsuario;
    var param={};
    var jsonAgregarUsuario=[];
    var datosUsuarioUBO={};

//    AU_usuario
//    AU_nombre
//    AU_tipoPersona
//    AU_aciRif
//    AU_grupo
//    AU_email
//    AU_telefono
//    AU_telefonoCel
//    AU_direccion

    datosUsuarioUBO.login = $("#AU_usuario").get(0).value;
    datosUsuarioUBO.nombres = $("#AU_nombre").get(0).value;
    datosUsuarioUBO.tipo_cirif = $("#AU_tipoPersona").get(0).value;
    datosUsuarioUBO.cirif = $("#AU_aciRif").get(0).value;
    datosUsuarioUBO.email = $("#AU_email").get(0).value;
    datosUsuarioUBO.telefono = $("#AU_telefono").get(0).value;
    datosUsuarioUBO.telefono_celular = $("#AU_telefonoCel").get(0).value;
    datosUsuarioUBO.direccion = $("#AU_direccion").get(0).value;
    datosUsuarioUBO.ambito = $("#AU_ambito").get(0).value;

    if ($("#AU_grupo").get(0).value =="0")
        datosUsuarioUBO.tipo_grupo = "0000000001";
    else if ($("#AU_grupo").get(0).value =="1")
        datosUsuarioUBO.tipo_grupo = "0000000002";
    else if ($("#AU_grupo").get(0).value =="-1")
        datosUsuarioUBO.tipo_grupo = "0000000003";
    else
        datosUsuarioUBO.tipo_grupo = "0000000001";

    datosUsuarioUBO.tipo = $("#AU_grupo").get(0).value;

    jsonAgregarUsuario[0]= datosUsuarioUBO;

    param.jsonAgregarUsuario=JSON.stringify({"logins":jsonAgregarUsuario});

    sendServiceJSON(url,param,BackOfficeSalvarAgregarUsuariosSuccess,null,null);
}


function BackOfficeSalvarAgregarUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var men = result.mensaje;

    $("#mensaje_error").empty();
    $("#mensaje_error").html(men);
    $("#div_mensajes_error").fadeIn("slow");
    if(result.codigo=="OK"){
        $("#div_tabla_consultaCU").fadeIn("fast");
        $("#div_tabla_agregarCU").fadeOut("fast");
        BackOfficeUsuariosJSONData();
    }
}

function privilegiosDatosUsuarios(){
    //consulta
    if(!privilegio_verDatosCargador)
        $("#CU_grupoFiltro .datos_Cargador").remove();
    if(!privilegio_verDatosSupervisor)
        $("#CU_grupoFiltro .datos_Supervisor").remove();
    if(!privilegio_verDatosAdministrador)
        $("#CU_grupoFiltro .datos_Administrador").remove();

    //add
    if(!privilegio_addDatosCargador)
        $("#AU_grupo .datos_Cargador").remove();
    if(!privilegio_addDatosSupervisor)
        $("#AU_grupo .datos_Supervisor").remove();
    if(!privilegio_addDatosAdministrador)
        $("#AU_grupo .datos_Administrador").remove();

    //edit
    if(!privilegio_editDatosCargador)
        $("#grupoEU .datos_Cargador").remove();
    if(!privilegio_editDatosSupervisor)
        $("#grupoEU .datos_Supervisor").remove();
    if(!privilegio_editDatosAdministrador)
        $("#grupoEU .datos_Administrador").remove();


}


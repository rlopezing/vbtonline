var urlBackOfficeConsultarUsuarios="BackOffice_consultarUsuarios.action";
var urlBackOfficeConsultaUsuario="BackOffice_consultaUsuario.action";
var urlBackOfficeConsultarOpcionesEspeciales="BackOffice_consultarOpcionesEspeciales.action";
var urlBackOfficeEditarUsuario="BackOffice_editarUsuario.action";
var urlBackOfficeAgregarUsuario="BackOffice_agregarUsuario.action";
var urlBackOfficeEnviarClaveUsuarioBackoffice="Security_enviarClaveUsuarioBackoffice.action";
var urlBackOfficeGuardarLog = "BackOffice_guardarLog.action";
var urlBackOfficeActualizaOpcionesEspeciales="BackOffice_actualizarOpcionesEspecialesSistema.action";

var usuarioEditar;
var hdnCambioEstatus = "No";
var oTable="";
var noInfo="";

$(document).ready(function(){

    $('.only_login').keypress(function (e) {
        //var regex = new RegExp("^[A-Za-z][A-Za-z0-9._]+[^._]$");
        var regex = new RegExp("[A-Za-z0-9._]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        Trim(str);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

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

    $("#CU_Cancelar_AU").click(function(){
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

        if (!$("#AU_usuario").val().match("^[A-Za-z][A-Za-z0-9._]+[^._]$")){
            if(idioma=="_us_en"){
                mensaje=mensaje+"Invalid format User Name"+"<br>";
            }else{
                mensaje=mensaje+"Formato inválido Nombre de Usuario"+"<br>";
            }
            $("#AU_usuario").addClass("error_campo");

        }else{
            if($("#AU_usuario").hasClass("error_campo"))
                $("#AU_usuario").removeClass("error_campo");
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#AU_email").get(0).value)){
                if($("#AU_email").hasClass("error_campo"))
                    $("#AU_email").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
               if (confirm("Se ingresar\u00e1n los datos de un nuevo usuario.\n¿Est\u00e1 seguro de que los datos son correctos?")){
                   BackOfficeSalvarAgregarUsuarioJSONData();
               }

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
        // $("#div_tabla_consultaUsuarioOpcionesPermiso").attr("style","display: none");
        $("#usuarioOpcionesSistema").attr("style","display: ");

//        BackOfficeUsuarioOpcionesSistemaJSONData($("#grupoEU").val());
        consultarOpcionesEspecialesJSONData();

/*        $("#div_tabla_consultaOpcionesSistema").lightbox_me({centered: true, onLoad: function() {
            $("#div_tabla_consultaOpcionesSistema").find("input:first").focus();
        }});*/
        $("#div_tabla_consultaOpcionesSistema").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
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


function consultarOpcionesEspecialesJSONData(){
    var url = urlBackOfficeConsultarOpcionesEspeciales;
    var param={};
    var jsonBackOffice=[];

    jsonBackOffice[0] = $("#usuarioEU").text();
    jsonBackOffice[1] ="B";

    param.jsonBackOffice=JSON.stringify({"parametros":jsonBackOffice});

    sendServiceJSON_sinc(url,param,consultarOpcionesEspecialesSuccess,null,null);
}


function consultarOpcionesEspecialesSuccess(originalRequest) {
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tabla = "tabla_UsuarioOpcionesSistema";

    $("#"+tabla).empty();
    $("#div_tabla_grupoOpcionesSistema").empty();
    $("#tabla_AccesosSistema").empty();
    $("#div_tabla_rolesOpcionesSistema").empty();
    $("#tabla_UsuarioOpcionesSistema").empty();
    $("#tabla_UsuarioOpcionesSistema_CUC").empty();
    $("#tabla_consultaOpciones").empty();
    $("#tabla_rolOpcionesSistema").empty();


    // crearTabla("div_tabla_UsuarioOpcionesSistema",tabla,columnas,datos);
    crearTablaV3("div_tabla_UsuarioOpcionesSistema",tabla,columnas,datos);
    ocultarHijos();
/*
    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        //"sScrollY": "300px",
        "bPaginate": false,
        "bScrollCollapse": true,
        "aoColumns": [
            { "sClass": "center" } ,
            { "sClass": ""       } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" }
        ],
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
        "bFilter": false,
        "bSort": false,
        //"sScrollY": "300px",
        "bPaginate": false,
        // "bScrollCollapse": true,
        "aoColumns": [
            { "sClass": "center" } ,
            { "sClass": ""       } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

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
    $("#div_carga_espera").removeClass("oculto");
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
    $("#div_carga_espera").addClass("oculto");
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
        cargar_selectExtra("AU_grupo",tipoGrupos);
        cargar_selectExtra("grupoEU",tipoGrupos);
        cargar_select("CU_estatusFiltro",tipoEstatus);
        cargar_select("estatusEU",tipoEstatus);
        cargar_select("tipoPersonaEU",tipoCiRif);
        cargar_select("AU_tipoPersona",tipoCiRif);
        cargar_select("CU_ambitoFiltro",tipoAmbito);
        cargar_select("AU_ambito",tipoAmbito);
        cargar_select("ambitoEU",tipoAmbito);
    }

    cargar_select("CU_grupoFiltro",tipoGrupos);
    cargar_selectExtra("AU_grupo",tipoGrupos);
    cargar_selectExtra("grupoEU",tipoGrupos);
    privilegiosDatosUsuarios();



    //$("#marco_trabajo").css("height","1400px");
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consultarUsuarios";
    var id_contenedor="div_tabla_consultarUsuarios";
     //tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
    crearTablaV3(id_contenedor,tabla_id,columnas,datos);
    //crearTabla("div_tabla_consultarUsuarios",tabla,columnas,datos);

/*
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
*/

    oTable= $("#tabla_consultarUsuarios").dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": false,
        "bFilter": false,
        "bSort": false,
        "bPaginate": true,
        "bScrollCollapse": true,
        "bDestroy": true,
        "sPaginationType": "full_numbers",
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    if(datos!=null){
/*        if(datos.length!=0){
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
        }*/
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



    var valorPrivilegio=true;
    if(privilegio_editar_UsuarioBackoffice){
        valorPrivilegio=false;
    }else{
        valorPrivilegio=true;
    }


    var quitarBoton=true;
    if (privilegio_editar_UsuarioBackoffice){
        quitarBoton=false;
    }else{
        quitarBoton=true;
    }

    if (privilegio_editar_estatusUsuarioBackoffice){
        quitarBoton=false;
    }else{
        if (!quitarBoton){
            quitarBoton=false;
        }
    }

    if (quitarBoton){
        $("#btnSalvarEU").remove();
    }

    if (privilegio_editar_estatusUsuarioBackoffice){
        $("#estatusEU").removeAttr("disabled");
    }else{
        $("#estatusEU").attr("disabled","disabled");
    }


    $("#formularioEditarUsuario .ediitarUsuarioBack").each(function(){
        $("#"+this.id).attr("disabled",valorPrivilegio);
    });


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

    if ($("#grupoEU").get(0).value !="-2")
        datosUsuarioUBO.tipo_grupo = $("#grupoEU option:selected").attr("extra");
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

    if ($("#AU_grupo").get(0).value !="-2")
        datosUsuarioUBO.tipo_grupo = $("#AU_grupo option:selected").attr("extra");
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
    if(!privilegio_verDatosAsesor)
        $("#CU_grupoFiltro .datos_Asesor").remove();

    //add
    if(!privilegio_addDatosCargador)
        $("#AU_grupo .datos_Cargador").remove();
    if(!privilegio_addDatosSupervisor)
        $("#AU_grupo .datos_Supervisor").remove();
    if(!privilegio_addDatosAdministrador)
        $("#AU_grupo .datos_Administrador").remove();
    if(!privilegio_addDatosAsesor)
        $("#AU_grupo .datos_Asesor").remove();

    //edit
    if(!privilegio_editDatosCargador)
        $("#grupoEU .datos_Cargador").remove();
    if(!privilegio_editDatosSupervisor)
        $("#grupoEU .datos_Supervisor").remove();
    if(!privilegio_editDatosAdministrador)
        $("#grupoEU .datos_Administrador").remove();
    if(!privilegio_editDatosAsesor)
        $("#grupoEU .datos_Asesor").remove();

}

function ocultarFilaOE(id) {
    try {
        (($("#"+id).parent()).parent()).addClass("oculto");
        if ($("#"+($("#"+id).val())).val() == "") {
            $("#"+($("#"+id).val())).val(id);
            ($("#"+($("#"+id).val())).parent()).html(
                ($("#"+($("#"+id).val())).parent()).html() +
                "<img id='img" +
                $("#"+id).val() +
                "' onclick='abrirDetalleOpcEsp(\"" +
                $("#"+id).val() +
                "\")' class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
                "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />"
            );
        } else {
            $("#"+($("#"+id).val())).val($("#"+($("#"+id).val())).val() + "_" + id);
        }
    } catch(excepcion) {
        //alert(excepcion);
    }
}


function ocultarHijos() {
    try {

        $("#div_tabla_consultaOpcionesSistema .elemento").each(function(){
        var hijos="";
        var codPadre="";
            if ($("#"+this.id).hasClass("padre")){

                if ($("#"+this.id).val() == "") {
                    codPadre=this.id;
                    $("#div_tabla_consultaOpcionesSistema .hijo").each(function(){
                        if (this.value==codPadre){
                            if (hijos==""){
                                hijos+=this.id;
                            }else{
                                hijos=hijos+"_"+this.id;
                            }
                        }
                    });
                    if (hijos!=""){
                        ($("#"+this.id).parent()).html("<input type='hidden' id='"+this.id+"' value='"+hijos+"' class='padre elemento'/>"+
                            "<img id='img" +this.id +
                            "' onclick='abrirDetalleOpcEsp(\"" +this.id +
                            "\")' class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />");
                    } else{
                        ($("#"+this.id).parent()).html("<input type='hidden' id='"+this.id+"' value='"+hijos+"' class='padre elemento'/> ");
                    }

                }
            }else{
                (($("#"+this.id).parent()).parent()).addClass("oculto");
            }

        });

    } catch(excepcion) {
        //alert(excepcion);
    }
}

function abrirDetalleOpcEsp(id) {
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

function guardarOpcionEspecial(id) {
    var codtipacc = id.split("_")[0];
    var copopc = id.split("_")[1];
    var tipacc = id.split("_")[2];
    var tipacc_old = id.split("_")[2];
    var arrtipacc = tipacc.split("");
    var pos = parseInt(codtipacc) - 1;

//    setTimeout(function() {
        $("#div_carga_espera").removeClass("oculto");
//    }, 2000);

    if (arrtipacc[pos] == "0") {
        arrtipacc[pos] = "1";
    } else {
        arrtipacc[pos] = "0";
    }
    tipacc = arrtipacc.toString().replace(/\,/g,"");
    setTimeout(function() {
        actualizarOpcionEspecialJSONData(id.split("_")[3],copopc,tipacc);
        latidoWeb();
    }, 2000);
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
        if (arrtipacc[pos] == "1") {
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/checked.gif");
        } else {
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/unchecked.gif");
        }
        opcionActualizada = '';
    } else {
        if (arrtipacc[pos] == "0") {
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/checked.gif");
        } else {
            $("#"+codtipacc+"_"+copopc+"_"+tipacc+"_"+id.split("_")[3]).attr("src","resources/images/unchecked.gif");
        }
    }
    setTimeout(function() {
        $("#div_carga_espera").addClass("oculto");
    }, 3000);
}

function actualizarOpcionEspecialJSONData(login,opcion,permiso) {
    var url = urlBackOfficeActualizaOpcionesEspeciales;
    var param={};
    var jsonGOP=[];

    jsonGOP[0]= login;
    jsonGOP[1]= opcion;
    jsonGOP[2]= permiso;

    param.jsonGOS=JSON.stringify({"parametros":jsonGOP});

    sendServiceJSON(url,param,actualizarOpcionEspecialSuccess,null,null);
}

function actualizarOpcionEspecialSuccess(originalRequest) {
    var result = originalRequest;
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

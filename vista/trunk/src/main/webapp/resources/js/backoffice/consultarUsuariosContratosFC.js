var urlBackOfficeConsultarAccesosUsuarioFC="BackOffice_consultarAccesos.action";
var urlBackOfficeEnviarUsuarioSMS = "Security_enviarUsuario.action";
var urlBackOfficeEnviarClaveSMS = "Security_enviarClaveUsuarioFirmaConjunta.action";
var urlBackOfficeEnviarUsuarioCorreo="Security_enviarUsuarioCorreo.action";
var urlBackOfficeEnviarClave="Security_enviarClave.action";
$(document).ready(function(){

    $("#CUC_search_FC").click(function() {
        filtrarUsuariosContratosFCJSONData();
    });

    $("#CUC_reset_FC").click(function() {
        blanquearFormularios("div_tabla_consultaCUC_FC");
        filtrarUsuariosContratosFCJSONData();
    });

    $("#btnResetearClaveCUC_FC").click(function() {

        /*cargarIdiomaJSONData_sinc();
        popupConfirm(
            vbtol_props[idioma]["msg_conf_reiniciar_clave_cuc_fc"] +
                " " +
                $("#telefonoCelCUC_FC").html() +
                vbtol_props[idioma]["msg_conf_continuar"],
            vbtol_props[idioma]["btn_confirm_si"],
            vbtol_props[idioma]["btn_confirm_no"],
            enviarClave
        ); */

        if(idioma=="_us_en"){
            if(confirm("Are you sure you want to reset the user's password?")){
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeEnviarClaveCUCFCJSONDataSMS();
            }
        }else{
            if(confirm("¿Est\u00e1 seguro que desea reiniciar la clave del usuario?")){
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeEnviarClaveCUCFCJSONDataSMS();
            }
        }

    });

    $("#btnEnviarUsuarioCUC_FC").click(function() {
        popupConfirm
        /*cargarIdiomaJSONData_sinc();
        popupConfirm(
            vbtol_props[idioma]["msg_conf_enviar_usuario_cuc_fc"] +
                " " +
                $("#telefonoCelCUC_FC").html() +
                vbtol_props[idioma]["msg_conf_continuar"],
            vbtol_props[idioma]["btn_confirm_si"],
            vbtol_props[idioma]["btn_confirm_no"],
            enviarUsuario
        );*/

        if(idioma=="_us_en"){
            if(confirm("Are you sure you want to send the user name?")){
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeEnviarUsuarioCUCFCJSONDataSMS();
            }
        }else{
            if(confirm("¿Est\u00e1 seguro que desea enviar el nombre del usuario?")){
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeEnviarUsuarioCUCFCJSONDataSMS();
            }
        }

    });

    $("#btnAccesosCUC_FC").click(function(){
        usuario="btnAccesosCUC_FC";
        $("#div_tabla_UsuarioOpcionesSistema_CUC_FC").empty();
        $("#usuarioOpcionesSistema_CUC_FC").attr("style","display: ");
        consultarAccesosFCJSONData();
        $("#div_tabla_consultaOpcionesSistema_CUC_FC").lightbox_me({centered: true, onLoad: function() {
            $("#div_tabla_consultaOpcionesSistema_CUC_FC").find("input:first").focus();
        }});
    });

    $("#btnRegresarCUC_FC").click(function(){
        $("#div_tabla_consultaCUC_FC").fadeIn("fast");
        $("#div_tabla_detalleCUC_FC").fadeOut("fast");
        consultarUsuariosContratosFCJSONData();
    });

    $('#tabla_consultarUsuariosContratos_FC tbody td img').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) ) {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        } else {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsUsuarioContrato_FC($(this).attr("id")), 'details');
        }
    } );

    $('#tabla_consultarUsuariosContratos_FC tbody td a').live('click', function () {
        this.onclick = "";
        seleccionarUsuarioFCC($(this).attr("id"),$(this).val());
    } );


});


function BackOfficeEnviarClaveCUCFCJSONDataSMS(id){
    var url = urlBackOfficeEnviarClaveSMS;
    var param={};
    var jsonParametros=[];
    $('#div_carga_espera').removeClass('oculto');
    jsonParametros[0]= $("#usuarioCUC_FC").html();
    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON_sinc(url,param,BackOfficeEnviarClaveSMSSuccess,null,null);

}

function BackOfficeEnviarClaveSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    idioma = result.idioma;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        if(idioma=="_us_en"){
            //traducir
            $("#mensaje_error").html("The user's password was successfully reset.\n The new password has been sent to the registered mobile phone number");
        }
        else{
            $("#mensaje_error").html("La clave del usuario fue reiniciada satisfactoriamente.\nLa nueva clave ha sido enviada al número registrado.");
        }

        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            $("#mensaje_error").html("The user's password name could not be sent to your phone.");
        else
            $("#mensaje_error").html("La clave del usuario no pudo ser enviado a su telefono.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}


function BackOfficeEnviarUsuarioCUCFCJSONDataSMS(id){
    var url = urlBackOfficeEnviarUsuarioSMS;
    var param={};
    var jsonParametros=[];
    $('#div_carga_espera').removeClass('oculto');
    jsonParametros[0]= $("#usuarioCUC_FC").html();

    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON_sinc(url,param,BackOfficeEnviarUsuarioSMSSuccess,null,null);
}

function BackOfficeEnviarUsuarioSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            $("#mensaje_error").html("The user name was sent to the registered phone");
        else
            $("#mensaje_error").html("El nombre de usuario del cliente fue enviado a su telefono.");
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            $("#mensaje_error").html("The user name could not be sent to your phone.");
        else
            $("#mensaje_error").html("El nombre de usuario del cliente no pudo ser enviado a su telefono.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

//EnvioMediante Correo

$("#btnResetearPasswordCUC_FC_Correo").click(function(){
    if(confirm("Se reiniciar\u00e1 la clave del usuario.\n ¿Est\u00e1 seguro?")){
        BackOfficeEnviarClaveFCJSONData();
    }


});


$("#btnEnviarUsuarioCUC_FC_Correo").click(function(){
    if(confirm("Se enviar\u00e1 el nombre de usuario al cliente.\n¿Est\u00e1 seguro?")){
        BackOfficeEnviarUsuarioFCJSONData();
    }

});


function BackOfficeEnviarClaveFCJSONData(id){
    var url = urlBackOfficeEnviarClave;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioCUC_FC").html();

    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON(url,param,BackOfficeEnviarClaveSuccess,null,null);
}


function BackOfficeEnviarClaveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;

    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("La clave del usuario fue reiniciada satisfactoriamente.\nLa nueva clave ha sido enviada a la direcci&oacute;n de correo registrada.");
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Ha ocurrido un error durante el reinicio de la clave, <br> por favor intente mas tarde.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

function BackOfficeEnviarUsuarioFCJSONData(id){
    var url = urlBackOfficeEnviarUsuarioCorreo;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioCUC_FC").html();

    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON(url,param,BackOfficeEnviarUsuarioSuccess,null,null);

}


function BackOfficeEnviarUsuarioSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;

    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("El nombre de usuario del cliente fue enviado a su direcci&oacute;n de correo.");
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("El nombre de usuario del cliente fue enviado a su direcci&oacute;n de correo.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

//Fin de EnvioMediante Correo

function fnFormatDetailsUsuarioContrato_FC (valor) {
    var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="bac_cuc_usuario_cirif_det">ID </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_cuc_usuario_telefono_det">Telephone Number</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_cuc_usuario_fecha_det">Creation Date </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_cuc_usuario_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_cuc_usuario_direccion_det">Address </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="bac_cuc_usuario_cirif_det">C.I./R.I.F </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_cuc_usuario_telefono_det">Tel&eacute;fono </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_cuc_usuario_fecha_det">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_cuc_usuario_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_cuc_usuario_direccion_det">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_cuc_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }
    return sOut;
}

function consultarAccesosFCJSONData(){
    var url = urlBackOfficeConsultarAccesosUsuarioFC;
    var param = {};
    var jsonBackOffice = [];

    jsonBackOffice[0] = $("#usuarioCUC_FC").text();
    jsonBackOffice[1] = "FC";
    param.jsonBackOffice = JSON.stringify({"parametros":jsonBackOffice});

    sendServiceJSON_sinc(url,param,consultarAccesosFCSuccess,null,null);
}

function consultarAccesosFCSuccess(originalRequest) {
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tabla = "tabla_AccesosSistema";

    $("#"+tabla).empty();
    $("#tabla_UsuarioOpcionesSistema_CUC_FC").empty();
    $("#tabla_UsuarioOpcionesSistema").empty();
    $("#tabla_UsuarioOpcionesSistema_CUC").empty();
    $("#div_tabla_grupoOpcionesSistema").empty();
    $("#tabla_AccesosSistema").empty();
    $("#div_tabla_rolesOpcionesSistema").empty();
    $("#tabla_consultaOpciones").empty();
    $("#tabla_rolOpcionesSistema").empty();



    crearTabla("div_tabla_UsuarioOpcionesSistema_CUC_FC",tabla,columnas,datos);
    ocultarHijosFC();
    var oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
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

}

function filtrarUsuariosContratosFCJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlFirmaConjuntaConsultarUsuarios;
    var param={};
    var jsonConsultaUsuarios=[];
    var datosConsultaUBO={};

    $("#div_tabla_consultaCUC_FC").fadeIn("fast");

    if ($("#CUC_usuarioFiltro_FC").val() != null) {
        datosConsultaUBO.strTxtUsuario=$("#CUC_usuarioFiltro_FC").val();
    } else
        datosConsultaUBO.strTxtUsuario="";
    if ($("#CUC_nombreFiltro_FC").val() != null) {
        datosConsultaUBO.strTxtNombre=$("#CUC_nombreFiltro_FC").val();
    }else
        datosConsultaUBO.strTxtNombre="";
    if ($("#CUC_CIRIF_FC").val() != null) {
        datosConsultaUBO.strTxtCIDRIF=$("#CUC_CIRIF_FC").val();
    }else
        datosConsultaUBO.strTxtCIDRIF="";
    if ($("#CUC_grupoFiltro_FC").val() != "-2")
        datosConsultaUBO.strCmbTipoUsuario=$("#CUC_grupoFiltro_FC").val();
    else
        datosConsultaUBO.strCmbTipoUsuario="Default";
    if ($("#CUC_estatusFiltro_FC").val() != "-2")
        datosConsultaUBO.strCmbEstatus=$("#CUC_estatusFiltro_FC").val();
    else
        datosConsultaUBO.strCmbEstatus="Default";
    if ($("#CUC_CARTERA_FC").val() != "")
        datosConsultaUBO.strCartera=$("#CUC_CARTERA_FC").val();
    else
        datosConsultaUBO.strCartera="";


    if (
        $("#CUC_usuarioFiltro_FC").val() == '' &&
            $("#CUC_nombreFiltro_FC").val() == '' &&
            $("#CUC_CIRIF_FC").val() == '' &&
            $("#CUC_CARTERA_FC").val() == '' &&
            (
                $("#CUC_grupoFiltro_FC").val() == "-2" ||
                    $("#CUC_grupoFiltro_FC").val() == ''
                ) && (
            $("#CUC_estatusFiltro_FC").val() == "-2" ||
                $("#CUC_estatusFiltro_FC").val() == ''
            )
        ) {
        datosConsultaUBO.hdnAccion=null;
    } else {
        datosConsultaUBO.hdnAccion="Buscar";
    }

    jsonConsultaUsuarios[0]= datosConsultaUBO;

    param.jsonConsultaUsuarios=JSON.stringify({"consultaUsuarioss":jsonConsultaUsuarios});

    // sendServiceJSON_sinc(url,param,filtrarUsuariosContratosFCSuccess,null,null);
    sendServiceJSON(url,param,filtrarUsuariosContratosFCSuccess,null,null);
}


function filtrarUsuariosContratosFCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupos = result.tipoGrupo;
    tipoEstatus = result.tipoEstatus;
    tipoCiRif = result.tipoCiRif;
    primeraVez = result.primeraVez;
    idioma = result.idioma;
    $("#div_carga_espera").addClass("oculto");
    var buscar;
    var mostrando;
    var id_contenedor = "div_tabla_consultarUsuariosContratos_FC";
    var tabla="tabla_consultarUsuariosContratos_FC";
    var objTabla;
    var tamano_pag = 20;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "" },
        { "sClass": "" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" }
    ];
    // objTabla=crearTabla_paginacion(id_contenedor,tabla,columnas,datos,0,tamano_pag,p_sclass);
    crearTablaV3(id_contenedor,tabla,columnas,datos);

    if(idioma=="_ve_es"){
        buscar = "Buscar:";
        mostrando = "Mostrando _START_ a _END_ de _TOTAL_ entidades";
    }else{
        buscar = "Search:";
        mostrando = "Showing _START_ to _END_ of _TOTAL_ entries";
    }
/*    oTable = objTabla.dataTable({
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
    });*/
    oTable = $("#"+tabla).dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": false,
        "bFilter": false,
        "bSort": false,
        "sPaginationType": "full_numbers",
        "bPaginate": true,
        "bScrollCollapse": true,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    if(datos!=null){
/*        if(datos.length!=0){
            $("#paginacion_"+tabla).paginate({
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
                    guardarlogusucont(datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag);
                    paginacion(id_contenedor,tabla,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                    $('#paginacion_tabla_consultarUsuariosContratos_FC div').addClass("jPag-controls");
                }
            });
        }*/
    }
}

function consultarUsuariosContratosFCJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlFirmaConjuntaConsultarUsuarios;
    var param={};
    var jsonConsultaUsuarios=[];
    var datosConsultaUBO={};

    $("#div_tabla_consultaCUC_FC").fadeIn("fast");

    datosConsultaUBO.strTxtUsuario="";
    datosConsultaUBO.strTxtNombre="";
    datosConsultaUBO.strTxtCIDRIF="";
    datosConsultaUBO.strCmbTipoUsuario="Default";
    datosConsultaUBO.strCmbEstatus="Default";
    datosConsultaUBO.strCartera="";

    $("#CUC_usuarioFiltro_FC").val(datosConsultaUBO.strTxtUsuario);
    $("#CUC_nombreFiltro_FC").val(datosConsultaUBO.strTxtNombre);
    $("#CUC_CIRIF_FC").val(datosConsultaUBO.strTxtCIDRIF);
    $("#CUC_grupoFiltro_FC").val(datosConsultaUBO.strCmbTipoUsuario);
    $("#CUC_estatusFiltro_FC").val(datosConsultaUBO.strCmbEstatus);
    $("#CUC_CARTERA_FC").val(datosConsultaUBO.strCartera);

    datosConsultaUBO.hdnAccion=null;

    jsonConsultaUsuarios[0]= datosConsultaUBO;

    param.jsonConsultaUsuarios=JSON.stringify({"consultaUsuarioss":jsonConsultaUsuarios});

    // sendServiceJSON_sinc(url,param,consultarUsuariosContratosFCSuccess,null,null);
    sendServiceJSON(url,param,consultarUsuariosContratosFCSuccess,null,null);
}


function consultarUsuariosContratosFCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupos = result.tipoGrupo;
    tipoEstatus = result.tipoEstatus;
    tipoCiRif = result.tipoCiRif;
    primeraVez = result.primeraVez;
    idioma = result.idioma;
    $("#div_carga_espera").addClass("oculto");
    var buscar;
    var mostrando;
    var id_contenedor = "div_tabla_consultarUsuariosContratos_FC";
    var tabla="tabla_consultarUsuariosContratos_FC";
    var objTabla;
    var tamano_pag = 20;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "" },
        { "sClass": "" },
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center" }
    ];
    // objTabla=crearTabla_paginacion(id_contenedor,tabla,columnas,datos,0,tamano_pag,p_sclass);
    crearTablaV3(id_contenedor,tabla,columnas,datos);

    cargar_select("CUC_grupoFiltro_FC",tipoGrupos);
    cargar_select("grupoCUC_FC",tipoGrupos);
    cargar_select("CUC_estatusFiltro_FC",tipoEstatus);
    cargar_select("estatusCUC_FC",tipoEstatus);

//    var tabla="tabla_consultarUsuariosContratos_FC";
//    crearTabla("div_tabla_consultarUsuariosContratos_FC",tabla,columnas,datos);

    if(idioma=="_ve_es"){
        buscar = "Buscar:";
        mostrando = "Mostrando _START_ a _END_ de _TOTAL_ entidades";
    }else{
        buscar = "Search:";
        mostrando = "Showing _START_ to _END_ of _TOTAL_ entries";
    }

/*    oTable = objTabla.dataTable({
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
    });*/
    oTable = $("#"+tabla).dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": false,
        "bFilter": false,
        "bSort": false,
        "bPaginate": true,
        "sPaginationType": "full_numbers",
        "bScrollCollapse": true,
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    if(datos!=null){
/*        if(datos.length!=0){
            $("#paginacion_"+tabla).paginate({
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
                    guardarlogusucont(datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag);
                    paginacion(id_contenedor,tabla,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                    $('#paginacion_tabla_consultarUsuariosContratos_FC div').addClass("jPag-controls");
                }
            });
        }*/
    }
//    oTable = $('#'+tabla).dataTable({
//        "iDisplayLength": 25,
//        "bJQueryUI": true,
//        "sPaginationType": "full_numbers",
//        "aoColumns": [
//            { "sClass": "center" },
//            { "sClass": "" },
//            { "sClass": "" },
//            { "sClass": "center" },
//            { "sClass": "center" },
//            { "sClass": "center" }
//        ],
//        "oLanguage": {
//            "sSearch": buscar,
//            "sInfo":   mostrando
//        }
//    });
}

function seleccionarUsuarioFCC(id,value) {
    $("#div_tabla_consultaCUC_FC").fadeOut("fast");
    $("#div_tabla_detalleCUC_FC").fadeIn("fast");
    $("#RolesFCDetail").addClass("oculto");
    $("#rolesDinamicosDetail").html("");
    $("#RolesFCDetail .obligatorioRoles_editFC").each(function(){
        $("#"+this.id).attr('checked', false);
        $("#div_"+this.id).removeClass("error_campo");
    });
    hdnCambioEstatus = "No";
    $(".obligatorio_EUFC ").each(function(){

        if($("#"+this.id).hasClass("error_campo"))
            $("#"+this.id).removeClass("error_campo");

    });
    consultarUsuarioFCCJSONData(id);
}


function consultarUsuarioFCCJSONData(id){
    var url = urlFirmaConjuntaConsultaUsuario;
    var param={};
    var jsonEditarUsuario=[];
    var datosUsuarioUBO={};

    datosUsuarioUBO.login = id;

    jsonEditarUsuario[0]= datosUsuarioUBO;

    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});

    sendServiceJSON_sinc(url,param,consultarUsuarioFCCSuccess,null,null);
}

function consultarUsuarioFCCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    idioma = result.idioma;
    usuarioEditar = result.usuario;
    var roles= result.rolesCustom;
    var campos = "";
    var codigos = "";

    $("#usuarioCUC_FC").html(usuarioEditar.login);
    $("#estatusCUC_FC").val(usuarioEditar.status_cuenta);
    $("#nombreCUC_FC").html(usuarioEditar.nombres);
    $("#ciRifCUC_FC").html(usuarioEditar.tipo_cirif + "-" + usuarioEditar.cirif);
    $("#telefonoCUC_FC").html(usuarioEditar.telefono);
//    $("#telefonoCodCelEU_FC").html("setNumber", "+"+usuarioEditar.codigoPais);
    $("#telefonoCelCUC_FC").html("(+" + usuarioEditar.codigoPais + ") " + usuarioEditar.telefono_celular);
    $("#direccionCUC_FC").html(usuarioEditar.direccion);
    $("#emailCUC_FC").html(usuarioEditar.email);
    $("#grupoCUC_FC").val(usuarioEditar.tipo);

    if (usuarioEditar.tipo=="11"){

        $("#btnAccesosCUC_FC").addClass("oculto");

        campos="<table cellpadding='3' cellspacing='0' width='100%' border='0'>";
        $.each(roles,function(s,item){
            if (item.seleccionado=="S") {
//                campos=campos+"<tr><td width='8%'><div id='div_chkEditRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesEditJSONData();' checked class='obligatorioRoles_editFC' id='chkEditRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
                campos+="<tr><td width='8%' valign='top'><div id='div_chkDetailRol"+item.codigoRol+"'><img id='chkDetailRol"+item.codigoRol+"' src='resources/images/checked.gif'></div></td>";
                if (codigos != "") {
                    codigos+=",";
                }
                codigos+="'"+item.codigoRol+"'";
            } else {
//                campos=campos+"<tr><td width='8%'><div id='div_chkEditRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesEditJSONData();' class='obligatorioRoles_editFC' id='chkEditRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
                campos+="<tr><td width='8%' valign='top'><div id='div_chkDetailRol"+item.codigoRol+"'><img id='chkDetailRol"+item.codigoRol+"' src='resources/images/unchecked.gif'></div></td>";
            }
            campos=campos+"<td><label class='datos negrita2' id='TAGROLDETAIL"+item.codigoRol+"' for=''>"+item.nombreRol+"</label></td></tr>";
        });
        campos=campos+"</table>";
        $("#rolesDinamicosDetail").html(campos);

        mostrarOpcionesRolesDetailJSONData(codigos);

        $("#RolesFCDetail").removeClass("oculto");
    }else{
        $("#RolesFCDetail").addClass("oculto");
        $("#btnAccesosCUC_FC").removeClass("oculto");
    }

}

function mostrarOpcionesRolesDetailJSONData(codigo){

    var url = urlFirmaConjuntaRolesCustomDettale;
    var param={};

    if (codigo != ""){
        param.codigo=codigo;
        // sendServiceJSON_sinc(url,param,mostrarOpcionesRolesDetailSuccess,null,null);
        sendServiceJSON(url,param,mostrarOpcionesRolesDetailSuccess,null,null);
    }else{
        $("#rolesDinamicosDetailLista").html("");
    }

}

function mostrarOpcionesRolesDetailSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var padres = result.permisos;
    var hijos = result.permisos;
    var lista = "";
    var detalle = "";

    var lista = "<ul>";
    $.each(padres, function(s,padre){
        if (padre.tipo == "Padre") {
            lista += "<li><span id='TAGOPCIONDETAIL"+padre.opcion+"'>"+padre.valor+"</span> (";
            var detalle = "";
            $.each(hijos,function(d,hijo) {
                if (hijo.tipo == "Hijo") {
                    if(padre.opcion == hijo.padre) {
                        if (detalle == "") {
                            detalle += "<span id='TAGOPCIONDETAIL"+hijo.opcion+"'>"+hijo.valor+"</span>";
                        } else {
                            detalle += ", "+"<span id='TAGOPCIONDETAIL"+hijo.opcion+"'>"+hijo.valor+"</span>";
                        }
                    }
                }
            });
            lista += detalle + ")</li>";
        }
    });
    lista += "</ul>";
    $("#rolesDinamicosDetailLista").html(lista);
}


function ocultarHijosFC() {
    try {

        $("#div_tabla_UsuarioOpcionesSistema_CUC_FC .elemento").each(function(){
            var hijos="";
            var codPadre="";
            if ($("#"+this.id).hasClass("padre")){

                if ($("#"+this.id).val() == "") {
                    codPadre=this.id;
                    $("#div_tabla_UsuarioOpcionesSistema_CUC_FC .hijo").each(function(){
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


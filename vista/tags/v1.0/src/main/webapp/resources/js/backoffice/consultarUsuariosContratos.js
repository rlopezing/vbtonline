var urlBackOfficeConsultarUsuarioContratos="BackOffice_consultarUsuariosContratos.action";
var urlBackOfficeConsultarUsuarioContrato="BackOffice_consultarUsuarioContrato.action";
var urlBackOfficeConsultarUsuarioTelefono="BackOffice_consultarTelefonos.action";
var urlBackOfficeEditarUsuarioContrato="BackOffice_editarUsuarioContrato.action";
var urlBackOfficeEnviarClave="Security_enviarClave.action";
var urlBackOfficeEnviarClaveSMS="Security_enviarClaveSMS.action";
var urlBackOfficeEnviarUsuario="Security_enviarUsuario.action";
var urlBackOfficeEnviarUsuarioSMS="Security_enviarUsuarioSMS.action";
var urlBackOfficeGuardarLogUsuCon="BackOffice_guardarLogUsuariosContratos.action"

var usuarioEditarContrato;
var hdnCambioEstatusCUC = "No";
var oTable="";
var noInfo="";


$(document).ready(function(){


    $('#tabla_consultarUsuariosContratos tbody td img').live('click', function () {
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
            oTable.fnOpen( nTr, fnFormatDetailsUsuarioContrato($(this).attr("id")), 'details');
        }

//        alert("hizo click : "+);
    } );

    //Envio Mediante SMS

    $("#btnResetearPasswordEU_CUC").click(function(){
       if(confirm("Se reiniciar\u00e1 la clave del usuario. La nueva clave sera enviada al numero "+usuarioEditarContrato.telefono_celular+"\n ¿Desea Continuar?")){
           $("#div_carga_espera").removeClass("oculto");
           BackOfficeEnviarClaveJSONDataSMS();
       }

    });


    $("#btnEnviarUsuarioEU_CUC").click(function(){
       if(confirm("Se enviar\u00e1 el nombre de usuario al cliente, al numero "+usuarioEditarContrato.telefono_celular+"\n ¿Desea Continuar?")){
           $("#div_carga_espera").removeClass("oculto");
           BackOfficeEnviarUsuarioJSONDataSMS();
       }

    });


    //EnvioMediante Correo

    $("#btnResetearPasswordEU_CUC_Correo").click(function(){
        if(confirm("Se reiniciar\u00e1 la clave del usuario.\n ¿Est\u00e1 seguro?")){
            BackOfficeEnviarClaveJSONData();
        }

    });


    $("#btnEnviarUsuarioEU_CUC_Correo").click(function(){
        if(confirm("Se enviar\u00e1 el nombre de usuario al cliente.\n¿Est\u00e1 seguro?")){
            BackOfficeEnviarUsuarioJSONData();
        }

    });

    //Fin de notificaciones


    $("#CUC_reset").click(function(){
        blanquearFormularios("div_tabla_consultaCUC");
        $("#div_tabla_consultarUsuariosContratos").empty();
        $("#paginacion_tabla_consultarUsuariosContratos").empty();
    });

    $("#CUC_search").click(function(){

        BackOfficeUsuariosContratosJSONData();

    });
    $("#btnBackEU_CUC").click(function(){
        if($("#pantallaMostrar").val()=="contrato") {
           //seleccionarOpcionMenu("CONTRATOS");
            $("#div_tabla_consultaCUC").attr("style","display: ''");
            $("#div_tabla_editarUsuarioCUC").attr("style","display:none");
            if($("#div_CONTRATOS").css("display")=='none'){
                $(".opcion_seleccionada").fadeOut("slow",function(){
                    $(".opcion_seleccionada").removeClass("opcion_seleccionada");
                    $("#div_CONTRATOS").addClass("opcion_seleccionada");
                    $("#div_CONTRATOS").fadeIn("slow");
                    $("#searchContrato").click();
                });
            }


        }else{
           $("#div_tabla_consultaCUC").fadeIn("fast");
            $("#div_tabla_editarUsuarioCUC").fadeOut("fast");
            BackOfficeUsuariosContratosJSONData();
        }

            $("#div_tabla_consultaCUC").fadeIn("fast");
            $("#div_tabla_editarUsuarioCUC").fadeOut("fast");
           // BackOfficeUsuariosContratosJSONData();


    });

    $("#btnDeshacerEU_CUC").click(function(){

        $("#usuarioEU_CUC").html(usuarioEditarContrato.login);
        $("#nombreEU_CUC").html(usuarioEditarContrato.nombres);
        $("#grupoEU_CUC").attr("value",usuarioEditarContrato.tipo_grupo);
        $("#estatusEU_CUC").attr("value",usuarioEditarContrato.status_cuenta);
        $("#ciRifEU_CUC").html(usuarioEditarContrato.cirif);
        $("#emailEU_CUC").attr("value",usuarioEditarContrato.email);
        $("#telefonoEU_CUC").val(usuarioEditarContrato.telefono);
        $("#telefonoCelEU_CUC").val(usuarioEditarContrato.telefono_celular);
        $("#direccionEU_CUC").html(usuarioEditarContrato.direccion);
        $("#ambitoEU_CUC").attr(usuarioEditarContrato.ambito);
    });

    $("#btnSalvarEU_CUC").click(function(){

        if ($("#estatusEU_CUC").val()=="A"){
           $("#telefonoEU_CUC").addClass("obligatorio_EUCBO");
           $("#telefonoCelEU_CUC").addClass("obligatorio_EUCBO");
        }else{
            $("#telefonoEU_CUC").removeClass("obligatorio_EUCBO");
            $("#telefonoCelEU_CUC").removeClass("obligatorio_EUCBO");
        }


        var mensaje="";
        $(".obligatorio_EUCBO").each(function(){
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
            if(isMail($("#emailEU_CUC").get(0).value)){
                if($("#emailEU_CUC").hasClass("error_campo"))
                    $("#emailEU_CUC").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                if(idioma=="_us_en"){
                    if(confirm("Updating user data \n data are correct?")){
                        BackOfficeSalvarEditarUsuarioContratoJSONData();
                    }
                }else{
                    if(confirm("Se actualizaran los datos del usuario \n ¿Está seguro que los datos son correctos?")){
                        BackOfficeSalvarEditarUsuarioContratoJSONData();
                    }
                }
            }else{
                mensaje = "Email";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(" Formato Invalido "+ mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }

//            $(".div_tabla_consulta").fadeIn("fast");
        }
    });



    $("#btnBackEU_CUCbtnBackEU_CUC").click(function(){
        if (btnVolverContratos=="C"){
            $("#div_tabla_consultaCUC").attr("style","display: ");
            $("#div_tabla_editarUsuarioCUC").attr("style","display:none ");
            if($("#div_CONTRATOS").css("display")=='none'){
                $(".opcion_seleccionada").fadeOut("slow",function(){
                    $(".opcion_seleccionada").removeClass("opcion_seleccionada");
                    $("#div_CONTRATOS").addClass("opcion_seleccionada");
                    $("#div_CONTRATOS").fadeIn("slow");
                });    }
        }else{
            $("#div_tabla_consultaCUC").fadeIn("fast");
            $("#div_tabla_editarUsuarioCUC").fadeOut("fast");
        }

    });


    $("#btnAccesosEspecialesEU_CUC").click(function(){
        usuario="btnAccesosEspecialesEU_CUC";
        $("#div_tabla_UsuarioOpcionesSistema_CUC").empty();
        $("#div_tabla_consultaUsuarioOpcionesPermiso_CUC").attr("style","display: none");
        $("#usuarioOpcionesSistema_CUC").attr("style","display: ");
        BackOfficeUsuarioOpcionesSistemaCUCJSONData("2");

        $("#div_tabla_consultaOpcionesSistema_CUC").lightbox_me({centered: true, onLoad: function() {
            $("#div_tabla_consultaOpcionesSistema_CUC").find("input:first").focus();
        }});
//            e.preventDefault();
    });

    $("#CUOP_back_CUC").click(function(){
        $("#div_tabla_consultaUsuarioOpcionesPermiso_CUC").attr("style","display: none");
        $("#usuarioOpcionesSistema_CUC").attr("style","display: ");
    });

    $("#CUOP_save_CUC").click(function(){
        var misAcciones ="";
        var accionesCero="";
        $("#div_tabla_usuarioOpcionesPermisos_CUC .verificarSelecccion").each(function(){
            misAcciones   = misAcciones + (($(this).is(":checked"))?"1":"0");
            accionesCero  = accionesCero + "0";
        });
        /*if(misAcciones==accionesCero){
            $("#mensaje_error").empty();
            $("#mensaje_error").html('Debe seleccionar al menos un valor');
            $("#div_mensajes_error").fadeIn("slow");

        }else{
            $("#div_mensajes_error").fadeOut("fast");*/
            BackOfficeSalvarAccionesUsuarioJSONData(misAcciones, $("#usuarioEU_CUC").html(), $("#codOpc_CUC").val());
        //}

    });

    $("#CUOP_eliminar_CUC").click(function(){
        BackOfficeEliminarAccionesUsuarioCJSONData( $("#usuarioEU_CUC").html(), $("#codOpc_CUC").val());
    });


});

function seleccionarUsuarioCSistemaOpcion(id){
    usuario="btnAccesosEspecialesEU_CUC";
    $("#div_tabla_usuarioOpcionesPermisos_CUC").empty();
//    BackOfficeUsuarioOpcionesSistemaJSONData($("#grupoEU").val());
    BackOfficeUsuarioOpcionesPermisosCUCJSONData(id,$("#usuarioEU_CUC").html());

    $("#div_tabla_consultaUsuarioOpcionesPermiso_CUC").attr("style","display: ");
    $("#usuarioOpcionesSistema_CUC").attr("style","display: none");
}

function fnFormatDetailsUsuarioContrato (valor)
{   var url = urlBackOfficeGuardarLogUsuCon;
    var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="bac_usuarioContrato_cirif_det">C.I./R.I.F </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuarioContrato_telefono_det">Tel&eacute;fono </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuarioContrato_fecha_det">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuarioContrato_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuarioContrato_direccion_det">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%" colspan="3"><span id="label_bac_usuarioContrato_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="bac_usuarioContrato_cirif_det">C.I./R.I.F </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuarioContrato_telefono_det">Tel&eacute;fono </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuarioContrato_fecha_det">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="bac_usuarioContrato_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuarioContrato_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="bac_usuarioContrato_direccion_det">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%" colspan="3"><span id="label_bac_usuarioContrato_direccion_det">'+valor.split("|")[4]+'</span></td>';

        sOut +='</tr>';
        sOut +='</table>';
    }
    var param={};
    var jsonEditarUsuarioContrato=[];
    var datosUsuarioUCBO={};

    datosUsuarioUCBO.login = valor.split("|")[5];
    datosUsuarioUCBO.direccion = "Consulta Detalle de Usuario Contrato";
    param.codOpc="DT";
    jsonEditarUsuarioContrato[0]= datosUsuarioUCBO;

    param.jsonEditarUsuarioContrato=JSON.stringify({"logins":jsonEditarUsuarioContrato});
    sendServiceJSON(url,param,'',null,null);

    return sOut;
}

function BackOfficeUsuariosContratosJSONData(){
    var url = urlBackOfficeConsultarUsuarioContratos;
    var param={};
    var jsonConsultaUsuariosContratos=[];
    var datosConsultaUBO={};
//    alert("entro");
    $("#div_carga_espera").removeClass("oculto");
    $("#div_tabla_consultaCUC").attr("style","display: ");
    $("#div_tabla_editarUsuarioCUC").attr("style","display: none");
    $("#pantallaMostrar").val("usuariosContratos");
    if($("#CUC_usuarioFiltro").get(0).value!=null)
    {
        datosConsultaUBO.strTxtUsuario=$("#CUC_usuarioFiltro").get(0).value;
    }else
        datosConsultaUBO.strTxtUsuario="";
    if($("#CUC_nombreFiltro").get(0).value!=null)
        datosConsultaUBO.strTxtNombre=$("#CUC_nombreFiltro").get(0).value;
    else
        datosConsultaUBO.strTxtNombre="";
    if($("#CUC_CIRIF").get(0).value!=null)
        datosConsultaUBO.strTxtCIDRIF=$("#CUC_CIRIF").get(0).value;
    else
        datosConsultaUBO.strTxtCIDRIF="";
    if($("#CUC_grupoFiltro").get(0).value!="-2")
        datosConsultaUBO.strCmbTipoUsuario=$("#CUC_grupoFiltro").get(0).value;
    else
        datosConsultaUBO.strCmbTipoUsuario="Default";
    if($("#CUC_estatusFiltro").get(0).value!="-2")
        datosConsultaUBO.strCmbEstatus=$("#CUC_estatusFiltro").get(0).value;
    else
        datosConsultaUBO.strCmbEstatus="Default";
    if($("#CUC_ambitoFiltro").get(0).value!="-2")
        datosConsultaUBO.strCmbAmbito=$("#CUC_ambitoFiltro").get(0).value;
    else
        datosConsultaUBO.strCmbAmbito="Default";

    if($("#CUC_carteraFiltro").get(0).value!=null)
    {
        datosConsultaUBO.strCartera=$("#CUC_carteraFiltro").get(0).value;
    }else
        datosConsultaUBO.strCartera="";



    if($("#CUC_usuarioFiltro").get(0).value=='' && $("#CUC_nombreFiltro").get(0).value=='' &&
        $("#CUC_CIRIF").get(0).value=='' && ($("#CUC_grupoFiltro").get(0).value=="-2" || $("#CUC_grupoFiltro").get(0).value=='' )
        && ($("#CUC_estatusFiltro").get(0).value=="-2" || $("#CUC_estatusFiltro").get(0).value=='' )
        && ($("#CUC_ambitoFiltro").get(0).value=="-2" || $("#CUC_ambitoFiltro").get(0).value=='' )){
        datosConsultaUBO.hdnAccion=null;
    }else{
        datosConsultaUBO.hdnAccion="Buscar";
    }

    jsonConsultaUsuariosContratos[0]= datosConsultaUBO;

    param.jsonConsultaUsuariosContratos=JSON.stringify({"consultaUsuarioss":jsonConsultaUsuariosContratos});

    sendServiceJSON(url,param,BackOfficeUsuariosContratosSuccess,null,null);
}


function BackOfficeUsuariosContratosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupos = result.tipoGrupo;
    tipoEstatus = result.tipoEstatus;
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
        cargar_select("CUC_grupoFiltro",tipoGrupos);
        cargar_select("CUC_estatusFiltro",tipoEstatus);
        cargar_select("grupoEU_CUC",tipoGrupos);
        cargar_select("estatusEU_CUC",tipoEstatus);

        cargar_select("CUC_ambitoFiltro",tipoAmbito);
        cargar_select("ambitoEU_CUC",tipoAmbito);
    }
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consultarUsuariosContratos";
    var id_contenedor="div_tabla_consultarUsuariosContratos";
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
                    guardarlogusucont(datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag);
                    paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                }
            });
        }
    }
        /*var tabla="tabla_consultarUsuariosContratos";
        crearTabla("div_tabla_consultarUsuariosContratos",tabla,columnas,datos);

        oTable = $('#'+tabla).dataTable({
            "bJQueryUI": true,
            "iDisplayLength": 25,
            "sPaginationType": "full_numbers",
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "" },
                { "sClass": ""},
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" },
                { "sClass": "" }
            ],
            "oLanguage": {
                "sSearch": "Buscar:", "sInfo":"Mostrando _START_ de _END_ de _TOTAL_ entidades"
            }
        });   */




    $("#div_carga_espera").addClass("oculto");
}

function guardarlogusucont(contenidoTabla_info,reg_inicio,tam_pagina){
    var url = urlBackOfficeGuardarLogUsuCon;
    for(var cantidad=reg_inicio;cantidad<(reg_inicio+tam_pagina);cantidad++){
        if(cantidad<contenidoTabla_info.length) {
            var data=contenidoTabla_info[cantidad];

            var usuario =data.data_culumn[1].data_columna.split("<b>")[1];

            var param={};
            var jsonEditarUsuarioContrato=[];
            var datosUsuarioUCBO={};

                    datosUsuarioUCBO.login = usuario;

                    jsonEditarUsuarioContrato[0]= datosUsuarioUCBO;

                    param.jsonEditarUsuarioContrato=JSON.stringify({"logins":jsonEditarUsuarioContrato});
                    sendServiceJSON(url,param,'',null,null);
        }else{
            cantidad=  (reg_inicio+tam_pagina);
        }
    }
}

function seleccionarUsuariosContratosOpcion(id){
    $("#div_tabla_consultaCUC").fadeOut("fast");
    $("#div_tabla_editarUsuarioCUC").fadeIn("fast");

    if (privilegio_editar_estatusUsuarioContrato){
        if (id.split("|")[1]=="1")
            $("#estatusEU_CUC").removeAttr("disabled");
        else
            $("#estatusEU_CUC").attr("disabled","disabled");
    }else{
        $("#estatusEU_CUC").attr("disabled",true);
    }
        hdnCambioEstatusCUC = "No";
//    alert(tipoGrupo);



    BackOfficeEditarUsuarioContratoJSONData(id.split("|")[0]);
}

function BackOfficeEditarUsuarioContratoJSONData(id){
    var url = urlBackOfficeConsultarUsuarioContrato;
    var param={};
    var jsonEditarUsuarioContrato=[];
    var datosUsuarioUCBO={};

    datosUsuarioUCBO.login = id;

    jsonEditarUsuarioContrato[0]= datosUsuarioUCBO;

    param.jsonEditarUsuarioContrato=JSON.stringify({"logins":jsonEditarUsuarioContrato});

    sendServiceJSON(url,param,BackOfficeEditarUsuarioContratoSuccess,null,null);
}


function BackOfficeEditarUsuarioContratoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    usuarioEditarContrato = result.usuarioContrato;
    correos = result.correos;
    telefonos = result.telefonos;
    telefonoLocal = result.telefonoLocal;
    tipoAmbito= result.tipoAmbito;
    var valorPrivilegio=true;

    $.each($("#formularioEditarUsuarioCUC .obligatorio_EUCBO"),function(pos,item){
        $("#"+this.id).removeAttr("disabled", "disabled" );
    });

    cargar_selectPersonal2("emailEU_CUC",correos);
    cargar_selectPersonal2("telefonoEU_CUC",telefonoLocal);
    cargar_selectPersonal2("telefonoCelEU_CUC",telefonos);
    cargar_select("ambitoEU_CUC",tipoAmbito);

    $("#usuarioEU_CUC").html(usuarioEditarContrato.login);
    $("#nombreEU_CUC").html(usuarioEditarContrato.nombres);
    $("#grupoEU_CUC").attr("value",usuarioEditarContrato.tipo_grupo);
    $("#estatusEU_CUC").attr("value",usuarioEditarContrato.status_cuenta);
    $("#ciRifEU_CUC").html(usuarioEditarContrato.cirif);
    $("#emailEU_CUC").attr("value",usuarioEditarContrato.email);
    //$("#telefonoEU_CUC").html(usuarioEditarContrato.telefono);
    //$("#telefonoCelEU_CUC").html(usuarioEditarContrato.telefono_celular);
    $("#direccionEU_CUC").html(usuarioEditarContrato.direccion);
    $("#ambitoEU_CUC").val(usuarioEditarContrato.ambito);
    $("#estatusEU_CUC").attr("disabled",!privilegio_editar_estatusUsuarioContrato);

    if($("#estatusEU_CUC").val()=="I"){
        $("#btnResetearPasswordEU_CUC").addClass("oculto");
        $("#btnEnviarUsuarioEU_CUC").addClass("oculto");
    }else{
        $("#btnResetearPasswordEU_CUC").removeClass("oculto");
        $("#btnEnviarUsuarioEU_CUC").removeClass("oculto");
    }



    if (usuarioEditarContrato.statusContrato=="1"){
        $("#estatusEU_CUC").removeAttr("disabled", "disabled" );
        $.each($("#formulario_EU_CUC .ocultarStatus"),function(pos,item){
            $("#"+this.id).removeClass("oculto");
        });

    }else{

        $("#estatusEU_CUC").attr("disabled", "disabled" );
        $.each($("#formulario_EU_CUC .ocultarStatus"),function(pos,item){
            $("#"+this.id).addClass("oculto");
        });

        if (usuarioEditarContrato.statusContrato=="3"){
            $.each($("#formularioEditarUsuarioCUC .obligatorio_EUCBO"),function(pos,item){
                $("#"+this.id).attr("disabled", "disabled" );
            });
        }

    }

        if (privilegio_editar_estatusUsuarioContrato){
            if(privilegio_editar_usuarios){
                valorPrivilegio=false;
            }else{
                valorPrivilegio=true;
            }
        }

        $("#formularioEditarUsuarioCUC .cambioStatus").each(function(){
            $("#"+this.id).attr("disabled",valorPrivilegio);
        });

    ocultarGruposEU(usuarioEditarContrato.tipo_grupo);

}



function ocultarGruposEU(valor){

    if((valor=="0000000011")||(valor=="0000000012")||(valor=="0000000013")||(valor=="0000000014")||(valor=="0000000015")||(valor=="0000000016")){
            $("#grupoEU_CUC option[value='0000000009']").hide();
            $("#grupoEU_CUC option[value='0000000008']").hide();
            $("#grupoEU_CUC option[value='0000000010']").hide();
            $("#grupoEU_CUC option[value='0000000011']").show();
            $("#grupoEU_CUC option[value='0000000012']").show();
            $("#grupoEU_CUC option[value='0000000013']").show();
            $("#grupoEU_CUC option[value='0000000014']").show();
            $("#grupoEU_CUC option[value='0000000015']").show();
            $("#grupoEU_CUC option[value='0000000016']").show();

    } else{
            $("#grupoEU_CUC option[value='0000000009']").show();
            $("#grupoEU_CUC option[value='0000000008']").show();
            $("#grupoEU_CUC option[value='0000000010']").show();
            $("#grupoEU_CUC option[value='0000000011']").hide();
            $("#grupoEU_CUC option[value='0000000012']").hide();
            $("#grupoEU_CUC option[value='0000000013']").hide();
            $("#grupoEU_CUC option[value='0000000014']").hide();
            $("#grupoEU_CUC option[value='0000000015']").hide();
            $("#grupoEU_CUC option[value='0000000016']").hide();

    }
}

function BackOfficeCargarNumero(id){
    var url = urlBackOfficeConsultarUsuarioTelefono;
    var param={};
    var jsonEditarUsuarioContrato=[];
    var datosUsuarioUCBO={};

    datosUsuarioUCBO.codpercli = id;

    jsonEditarUsuarioContrato[0]= datosUsuarioUCBO;

    param.jsonEditarUsuarioContrato=JSON.stringify({"logins":jsonEditarUsuarioContrato});

    sendServiceJSON_sinc(url,param,BackOfficeCargarNumeroSuccess,null,null);
}

function BackOfficeCargarNumeroLogin(id,Login){
    var url = urlBackOfficeConsultarUsuarioTelefono;
    var param={};
    var jsonEditarUsuarioContrato=[];
    var datosUsuarioUCBO={};

    datosUsuarioUCBO.codpercli = id;
    datosUsuarioUCBO.login = Login;

    jsonEditarUsuarioContrato[0]= datosUsuarioUCBO;

    param.jsonEditarUsuarioContrato=JSON.stringify({"logins":jsonEditarUsuarioContrato});

    sendServiceJSON_sinc(url,param,BackOfficeCargarNumeroSuccess,null,null);
}


function BackOfficeCargarNumeroSuccess(originalRequest){
    var result = originalRequest;
    telefonosUsuario = result.telefonos;
    correosUsuario = result.correos;
    tipoGrupo=result.tipoGrupo;
}



function BackOfficeEnviarClaveJSONData(id){
    var url = urlBackOfficeEnviarClave;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU_CUC").html();


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



function BackOfficeEnviarClaveJSONDataSMS(id){
    var url = urlBackOfficeEnviarClaveSMS;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU_CUC").html();


    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON_sinc(url,param,BackOfficeEnviarClaveSMSSuccess,null,null);


}


function BackOfficeEnviarClaveSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    var mensaje="";
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("La clave del usuario fue reiniciada satisfactoriamente.\nLa nueva clave ha sido enviada al n&uacute;mero registrado.");
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Ha ocurrido un error durante el reinicio de la clave, <br> por favor verifique el número e intente mas tarde.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}




function BackOfficeEnviarUsuarioJSONData(id){
    var url = urlBackOfficeEnviarUsuario;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU_CUC").html();


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

function BackOfficeEnviarUsuarioJSONDataSMS(id){
    var url = urlBackOfficeEnviarUsuarioSMS;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU_CUC").html();


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
        $("#mensaje_error").html("El nombre de usuario del cliente fue enviado al n&uacute;mero registrado.");
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Ha ocurrido un error durante enviando el usuario, <br> por favor verifique el número e intente mas tarde.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}


function BackOfficeSalvarEditarUsuarioContratoJSONData(){
    var url = urlBackOfficeEditarUsuarioContrato;
    var param={};
    var jsonEditarUsuarioContrato=[];
    var datosUsuarioUCBO={};

    datosUsuarioUCBO.login = $("#usuarioEU_CUC").html();
    datosUsuarioUCBO.nombres = $("#nombreEU_CUC").html();
    datosUsuarioUCBO.cirif = $("#ciRifEU_CUC").html();
    datosUsuarioUCBO.email = $("#emailEU_CUC").get(0).value;
    datosUsuarioUCBO.telefono = $("#telefonoEU_CUC").val();
    datosUsuarioUCBO.telefono_celular = $("#telefonoCelEU_CUC").val();
    datosUsuarioUCBO.direccion = $("#direccionEU_CUC").html();
    datosUsuarioUCBO.tipo_grupo = $("#grupoEU_CUC").get(0).value;
    datosUsuarioUCBO.ambito = $("#ambitoEU_CUC").get(0).value;
    datosUsuarioUCBO.status_cuenta = $("#estatusEU_CUC").get(0).value;
    datosUsuarioUCBO.cambioStatus = hdnCambioEstatusCUC;


    jsonEditarUsuarioContrato[0]= datosUsuarioUCBO;

    param.jsonEditarUsuarioContrato=JSON.stringify({"logins":jsonEditarUsuarioContrato});

    sendServiceJSON(url,param,BackOfficeSalvarEditarUsuarioContratoSuccess,null,null);
}


function BackOfficeSalvarEditarUsuarioContratoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var men = result.mensaje;
    var code = result.codigo;

    if(code == '0'){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(men);
        $("#div_mensajes_error").fadeIn("slow");

        if (btnVolverContratos=="C"){
            $("#div_tabla_consultaCUC").attr("style","display: ");
            $("#div_tabla_editarUsuarioCUC").attr("style","display:none ");
            if($("#div_CONTRATOS").css("display")=='none'){
                $(".opcion_seleccionada").fadeOut("slow",function(){
                    $(".opcion_seleccionada").removeClass("opcion_seleccionada");
                    $("#div_CONTRATOS").addClass("opcion_seleccionada");
                    $("#div_CONTRATOS").fadeIn("slow");
                });    }
        }else{
            $("#div_tabla_consultaCUC").fadeIn("fast");
            $("#div_tabla_editarUsuarioCUC").fadeOut("fast");
            BackOfficeUsuariosContratosJSONData();
        }

    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(men);
        $("#div_mensajes_error").fadeIn("slow");

    }



}

function cambioEstatusEUC() {
//    alert("entre");
    hdnCambioEstatusCUC = "Si";
}




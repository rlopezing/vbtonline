var urlCrearUsuario="BackOffice_cargarDatos.action";
var urlBackOfficeConsultarBitacora="BackOffice_consultarBitacora.action";
var urlBackOfficeConsultaUsuario="BackOffice_consultaUsuario.action";
var urlBackOfficeEditarUsuario="BackOffice_editarUsuario.action";
var urlBackOfficeAgregarUsuario="BackOffice_agregarUsuario.action";
var urlSesionesActivas="BackOffice_buscarSesiones.action";
var urlSesionesLiberarActivas="BackOffice_liberarSesiones.action";

var usuarioEditar;
var hdnCambioEstatus = "No";
var oTable="";
var noInfo="";

$(document).ready(function(){


    $( "#fechaDesdeFiltroBitacora").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltroBitacora").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});

   /* $( "#fechaDesdeFiltroBitacora").blur(function(){
        if(!isDate($( "#fechaDesdeFiltroBitacora").val()) && !Empty($( "#fechaDesdeFiltroBitacora").val())){
            $("#mensaje_error").empty();
            $(this).val("");
            if(idioma=="_us_en")
                $("#mensaje_error").html("From date is not correct");
            else
                $("#mensaje_error").html("La fecha desde no es correcta");

            $("#div_mensajes_error").fadeIn("slow");
        }
    });

    $( "#fechaHastaFiltroBitacora").blur(function(){
        if(!isDate($( "#fechaHastaFiltroBitacora").val())&& !Empty($( "#fechaHastaFiltroBitacora").val())){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html("From date is not correct");
            else
                $("#mensaje_error").html("La fecha desde no es correcta");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });   */


    $( "#fechaDesdeFiltroBitacora" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltroBitacora" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});



    $('#tabla_consultarBitacora tbody td img').live('click', function () {
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
            oTable.fnOpen( nTr, fnFormatDetailsBitacora($(this).attr("id")), 'details');
        }

//        alert("hizo click : "+);
    } );

    $("#bitacora_reset").click(function(){
        $("#div_bitacora .inputFormulario_bitacora").each(function(){
            this.value="";
        });

        $("#div_bitacora  .selectFormulario_bitacora").each(function(){
            this.value="-2";
        });
        $("#div_tabla_bitacora").empty();
    });

    $("#bitacora_search").click(function(){

        $("#div_carga_espera").removeClass("oculto");
        cargar_pagina_new=false;
        if (($("#fechaDesdeFiltroBitacora").val()!="")&&($("#fechaHastaFiltroBitacora").val()!="")&&($("#bitacora_dias").val()!="")){


            $("#fechaDesdeFiltroBitacora").addClass("error_campo");
            $("#fechaHastaFiltroBitacora").addClass("error_campo");
            $("#bitacora_dias").addClass("error_campo");
            $("#bitacora_dias").val("");
            $("#mensaje_error").empty();
            $("#mensaje_error").html("La búsqueda sólo puede ser por Rango de Fechas o por un número de días válido (Últimos días)");
            $("#div_mensajes_error").fadeIn("slow");

        }else{
            $(".error_campo").each(function(){
                $("#"+this.id).removeClass("error_campo");
            });
            BackOfficeBitacoraJSONData();
        }
    });


    $("#btnActualizarSesion").click(function(){

        BackOfficeSesionesJSONData();

    });



    $("#btnLiberarSesion").click(function(){
        var lista_usuario=[];
        var mensaje="";

        $("#tabla_sesiones .liberarUsuarioSesion").each(function(){
            if($("#"+this.id).is(':checked')){
                var usuario={};
                usuario.login=limpiar_espacios($("#"+this.id).val());
                lista_usuario.push(usuario);

            }
        });

        if (lista_usuario.length==0){
            mensaje=mensaje+"Campo requerido - Debe Seleccionar un usuario <br>";
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            BackOfficeLiberarSesionesJSONData(lista_usuario);
        }

    });




});



function BackOfficeLiberarSesionesJSONData(usuarios){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlSesionesLiberarActivas;
    var param={};
    var contratoActualizado={};
    var jsonConsultaContratos=[];

    contratoActualizado.usuarios = usuarios;
    jsonConsultaContratos[0]= contratoActualizado;

    param.jsonConsultaContratos=JSON.stringify({"contratos":jsonConsultaContratos});

    // sendServiceJSON_sinc(url,param,BackOfficeLiberarSesionesSuccess,null,null);
    sendServiceJSON(url,param,BackOfficeLiberarSesionesSuccess,null,null);
}

function BackOfficeLiberarSesionesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#btnActualizarSesion").click();
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html("La operacion no pudo ser completada");
        $("#div_mensajes_error").fadeIn("slow");
    }

}





function fnFormatDetailsBitacora (valor)
{   var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="150px"><span class="label_descp" id="bac_nombre_usu_det">Nombre del Usuario </span><span class="label_descp">:</span></td>';
        sOut +='<td><span id="label_bac_nombre_usu_det">'+valor.split("|")[0]+'</span></td>';

        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="150px"><span class="label_descp" id="bac_nombre_usu_det">Nombre del Usuario </span><span class="label_descp">:</span></td>';
        sOut +='<td><span id="label_bac_nombre_usu_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }



    return sOut;
}



function BackOfficeBitacoraJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeConsultarBitacora;
    var param={};
    var jsonConsultaBitacora=[];
    var datosConsultBitacora={};

    //$("#marco_trabajo").css("height","900px");


    if(Trim($("#bitacora_usuarioFiltro").val())!="")
    {
        datosConsultBitacora.usuario=$("#bitacora_usuarioFiltro").val();
    }else
        datosConsultBitacora.usuario="";

    if(Trim($("#bitacora_accion").val())!="-2")
    {
        datosConsultBitacora.accion=$("#bitacora_accion").val();
    }else
        datosConsultBitacora.accion="-2";

    if(Trim($("#bitacora_objeto").val())!="-2")
    {
        datosConsultBitacora.objeto=$("#bitacora_objeto").val();
    }else
        datosConsultBitacora.objeto="-2";

    if(Trim($("#fechaDesdeFiltroBitacora").val())!="")
    {
        datosConsultBitacora.desde=$("#fechaDesdeFiltroBitacora").val();
    }else
        datosConsultBitacora.desde="";

    if(Trim($("#fechaHastaFiltroBitacora").val())!="")
    {
        datosConsultBitacora.hasta=$("#fechaHastaFiltroBitacora").val();
    }else
        datosConsultBitacora.hasta="";

    if(Trim($("#bitacora_dias").val())!="")
    {
        datosConsultBitacora.ultimos=$("#bitacora_dias").val();
    }else
        datosConsultBitacora.ultimos="";

    if(Trim($("#bitacora_idobjeto").val())!="")
    {
        datosConsultBitacora.idObjeto=$("#bitacora_idobjeto").val();
    }else
        datosConsultBitacora.idObjeto="";

    if(Trim($("#bitacora_ip").val())!="")
    {
        datosConsultBitacora.ip=$("#bitacora_ip").val();
    }else
        datosConsultBitacora.ip="";

    if(Trim($("#bitacora_comentarioFiltro").val())!="")
    {
        datosConsultBitacora.comentario=$("#bitacora_comentarioFiltro").val();
    }else
        datosConsultBitacora.comentario="";

//
    if(Trim($("#bitacora_usuarioFiltro").get(0).value)=='' && $("#bitacora_accion").get(0).value=='-2' &&
        $("#bitacora_objeto").get(0).value=="-2" && Trim($("#fechaDesdeFiltroBitacora").get(0).value)=='' && Trim($("#fechaHastaFiltroBitacora").get(0).value)==''
        && Trim($("#bitacora_dias").get(0).value)=='' && Trim($("#bitacora_idobjeto").get(0).value)==''&& Trim($("#bitacora_ip").get(0).value)=='' ){
        datosConsultBitacora.hdnAccion=null;
    }else{
        datosConsultBitacora.hdnAccion="Buscar";
    }
//
    jsonConsultaBitacora[0]= datosConsultBitacora;
//
    param.jsonConsultaBitacora=JSON.stringify({"consultaBitacorass":jsonConsultaBitacora});

    sendServiceJSON(url,param,BackOfficeBitacoraSuccess,null,null);
}


function BackOfficeBitacoraSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    acciones = result.acciones;
    objetos = result.objetos;
    var fecha= result.fechaFiltro;
    $("#div_carga_espera").addClass("oculto");

    if(cargar_pagina_new){
        cargar_selectPersonal("bitacora_accion",acciones,"All","-2");
        cargar_selectPersonal("bitacora_objeto",objetos,"All","-2");
        $("#fechaDesdeFiltroBitacora").val(fecha);
        $("#fechaHastaFiltroBitacora").val(fecha);
        $("#bitacora_search").click();
    } else {
        var tabla="tabla_consultarBitacora";
        // crearTabla("div_tabla_bitacora",tabla,columnas,datos);
        crearTablaV3("div_tabla_bitacora",tabla,columnas,datos);

/*        oTable = $('#'+tabla).dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers"

        }); */
        oTable = $('#'+tabla).dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers"

        });
    }


    if(Trim($("#bitacora_usuarioFiltro").get(0).value)=='' && $("#bitacora_accion").get(0).value=='-2' &&
        $("#bitacora_objeto").get(0).value=="-2" && Trim($("#fechaDesdeFiltroBitacora").get(0).value)=='' && Trim($("#fechaHastaFiltroBitacora").get(0).value)==''
        && Trim($("#bitacora_dias").get(0).value)=='' && Trim($("#bitacora_idobjeto").get(0).value)==''&& Trim($("#bitacora_ip").get(0).value)=='' ){

        $("#fechaDesdeFiltroBitacora").val(fecha);
        $("#fechaHastaFiltroBitacora").val(fecha);
        $("#bitacora_search").click();
    }

}




function BackOfficeSesionesJSONData(){
    var url = urlSesionesActivas;
    var param={};
    var jsonParametrosString=[];
    $("#div_carga_espera").removeClass("oculto");
    sendServiceJSON_sinc(url,param,BackOfficeSesionesJSONDataSuccess,null,null);
}


function BackOfficeSesionesJSONDataSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var p_sclass=[
        { "sClass": "right" },
        { "sClass": "center" },
        {  "sClass": "center" },
        {  "sClass": "center" },
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


    tabla_id="tabla_sesiones";
    id_contenedor="div_tabla_sesiones";
    // tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);
    crearTablaV3(id_contenedor,tabla_id,columnas,datos);

/*    tabla.dataTable({
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

    oTable=tabla;*/
    $("#"+tabla_id).dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": false,
        "bFilter": false,
        "bSort": false,
        "bPaginate": true,
        "bScrollCollapse": true,
        "sPaginationType": "full_numbers",
        "aoColumns": p_sclass,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    oTable=$("#"+tabla_id);

    if(datos.length>0){
/*        $("#paginacion_"+tabla_id).paginate({
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
        });*/
    }
    $("#div_carga_espera").addClass("oculto");
}
var urlCrearUsuario="BackOffice_cargarDatos.action";
var urlBackOfficeConsultarBitacora="BackOffice_consultarBitacora.action";
var urlBackOfficeConsultaUsuario="BackOffice_consultaUsuario.action";
var urlBackOfficeEditarUsuario="BackOffice_editarUsuario.action";
var urlBackOfficeAgregarUsuario="BackOffice_agregarUsuario.action";

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






});


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


    if(cargar_pagina_new){
        cargar_selectPersonal("bitacora_accion",acciones,"All","-2");
        cargar_selectPersonal("bitacora_objeto",objetos,"All","-2");
        $("#fechaDesdeFiltroBitacora").val(fecha);
        $("#fechaHastaFiltroBitacora").val(fecha);
        $("#bitacora_search").click();
    } else {
        var tabla="tabla_consultarBitacora";
        crearTabla("div_tabla_bitacora",tabla,columnas,datos);

        oTable = $('#'+tabla).dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
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


var urlConsultarSucesos="BackOffice_consultarSucesos.action";


var oTable="";
var idioma="";
var noInfo="";
$(document).ready(function(){

    $( "#fechaDesdeFiltro_sucesos" ).datepicker({ dateFormat: "dd-mm-yy",changeMonth: true,changeYear: true });
    $( "#fechaHastaFiltro_sucesos" ).datepicker({ dateFormat: "dd-mm-yy",changeMonth: true,changeYear: true });

    $('#tabla_consulta_Sucesos tbody td img').live('click', function () {
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
            oTable.fnOpen( nTr, fnFormatDetailsSucesos($(this).attr("id")), 'details');
        }

//        alert("hizo click : "+);
    } );


    $("#consultar_sucesos").click(function(){
        var mensaje="";
        $(".requeridoFecha_sucesos").each(function(){
                if(Trim($("#"+this.id).val())==""){
                    if(idioma=="_us_en")
                      mensaje=mensaje+"Required field - "+ this.title+"<br>";
                    else
                      mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            });


        if(mensaje!=""){
            $("#div_tabla_consulta_Sucesos").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#div_tabla_consulta_Sucesos").fadeIn("fast");
            consultarSucesosJSONData();
        }

    });

    $("#limpiar_sucesos").click(function(){
        $("#fechas_sucesos .inputFormulario_sucesos").each(function(){
            this.value="";
        });
    });

});


function fnFormatDetailsSucesos (valor)
{   var sOut = '<table width="100%" align="center">';

    if(idioma=="_us_en"){
        sOut +='<tr><td width="15%" ><span id="sucesos_TAGDireccionIp" class="label_descp">Direccion IP</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_DirecionIp">'+valor.split("||")[0]+'</span></td>';
        sOut +='<td width="15%" ><span id="sucesos_TAGNombreArchivo" class="label_descp">Nombre Archivo</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_NombreArchivo">'+valor.split("||")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="15%" ><span id="sucesos_TAGParametrosUrl" class="label_descp">Par&aacute;metros del URL</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_ParametrosUrl">'+valor.split("||")[2]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="15%" ><span id="sucesos_TAGParametrosSesion" class="label_descp">Par&aacute;metros de Sesi&oacute;n</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_ParametrosSesion">'+valor.split("||")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<tr><td width="15%" ><span id="sucesos_TAGDireccionIp" class="label_descp">Direccion IP</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_DirecionIp">'+valor.split("||")[0]+'</span></td>';
        sOut +='<td width="15%" ><span id="sucesos_TAGNombreArchivo" class="label_descp">Nombre Archivo</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_NombreArchivo">'+valor.split("||")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="15%" ><span id="sucesos_TAGParametrosUrl" class="label_descp">Par&aacute;metros del URL</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_ParametrosUrl">'+valor.split("||")[2]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="15%" ><span id="sucesos_TAGParametrosSesion" class="label_descp">Par&aacute;metros de Sesi&oacute;n</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_sucesos_ParametrosSesion">'+valor.split("||")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }
    return sOut;
}



function consultarSucesosJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlConsultarSucesos;
    var param={};
    var jsonConsultaSucesos=[];

    if($("#fechaDesdeFiltro_sucesos").get(0).value == "" && $("#fechaHastaFiltro_sucesos").get(0).value==""){
        jsonConsultaSucesos[0] = "";
        jsonConsultaSucesos[1] = "";
    }else{
//        jsonConsultaSucesos[0] = "Buscar";
        jsonConsultaSucesos[0] = $("#fechaDesdeFiltro_sucesos").get(0).value;
        jsonConsultaSucesos[1] = $("#fechaHastaFiltro_sucesos").get(0).value;
    }

    //$("#marco_trabajo").css("height","1500px");

    param.jsonConsultaSucesos=JSON.stringify({"parametros":jsonConsultaSucesos});

    sendServiceJSON(url,param,consultarSucesosSuccess,null,null);
}


function consultarSucesosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var desde = result.desde;
    var hasta = result.hasta;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "" },
        {  "sClass": "" }
    ];
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consulta_Sucesos";
    var id_contenedor="div_tabla_consulta_Sucesos";

    $("#div_carga_espera").addClass("oculto");

    $("#fechaDesdeFiltro_sucesos").val(desde);
    $("#fechaHastaFiltro_sucesos").val(hasta);


//    if(idioma=="_us_en")
//        noInfo="No data available";
//    else
        noInfo="No hay informaci&oacute;n disponible";

    $("#div_tabla_consulta_Sucesos").empty();



    // tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
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
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });
    oTable=tabla;*/
    oTable = $("#"+tabla_id).dataTable({
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
                    paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                }
            });
        }else{
            $("#paginacion_tabla_consulta_Sucesos").empty();
        }*/
    } else{
        // $("#paginacion_tabla_consulta_Sucesos").empty();
    }
}






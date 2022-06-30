var urlConsultarTransferenciasGenerales="Transfers_consultarTransferencias.action";


var oTable="";
var idioma="";
var noInfo="";
$(document).ready(function(){

    $( "#fechaDesdeFiltro_Transferencias").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_Transferencias").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});

   /* $( "#fechaDesdeFiltro_Transferencias").blur(function(){
        if(!isDate($( "#fechaDesdeFiltro_Transferencias").val())&& !Empty($( "#fechaDesdeFiltro_Transferencias").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });  */

   /* $( "#fechaHastaFiltro_Transferencias").blur(function(){
        if(!isDate($( "#fechaHastaFiltro_Transferencias").val())&& !Empty($( "#fechaHastaFiltro_Transferencias").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });   */

    $( "#fechaDesdeFiltro_Transferencias" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_Transferencias" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $('#tabla_consulta_TransferenciasGenerales tbody td img').live('click', function () {
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
            oTable.fnOpen( nTr, fnFormatDetailsTransferHistory($(this).attr("id")), 'details');
        }

//        alert("hizo click : "+);
    } );


    $("#consultar_Transferencias").click(function(){
        var mensaje="";
        $(".requeridoFecha_Transferencias").each(function(){
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



        if(!isDate($( "#fechaDesdeFiltro_Transferencias").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fechaDesdeFiltro_Transferencias").addClass("error_campo");
        }else if(!isDate($( "#fechaHastaFiltro_Transferencias").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"To date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
            $("#fechaHastaFiltro_Transferencias").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fechaDesdeFiltro_Transferencias").val().split("/")[1]
                +"/"+$("#fechaDesdeFiltro_Transferencias").val().split("/")[0]
                +"/"+$("#fechaDesdeFiltro_Transferencias").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fechaHastaFiltro_Transferencias").val().split("/")[1]
                +"/"+$("#fechaHastaFiltro_Transferencias").val().split("/")[0]
                +"/"+$("#fechaHastaFiltro_Transferencias").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                $("#fechaDesdeFiltro_Transferencias").addClass("error_campo");
                $("#fechaHastaFiltro_Transferencias").addClass("error_campo");

            }else{
                $("#fechaDesdeFiltro_Transferencias").removeClass("error_campo");
                $("#fechaHastaFiltro_Transferencias").removeClass("error_campo");
            }
        }

        if(mensaje!=""){
            $("#div_tabla_consulta_TransferenciasGenerales").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#transfer_history_imprimir").fadeIn("fast");
            $("#div_tabla_consulta_TransferenciasGenerales").fadeIn("fast");
            consultarTransferenciasGeneralesJSONData();
        }

    });

});

function cargarPaginaTransferHistory(){
    $("#div_tabla_consulta_TransferenciasGenerales").empty();
    $("#fechaDesdeFiltro_Transferencias" ).val(fechaHoy);
    $("#fechaHastaFiltro_Transferencias" ).val(fechaHoy);
    $("#consultar_Transferencias").click();
}

function fnFormatDetailsTransferHistory (valor)
{   var sOut = '<table width="100%" align="center">';

     if (($("#tipo_usuario_app").val()=="6")||($("#tipo_usuario_app").val()=="7")||($("#tipo_usuario_app").val()=="8")||($("#tipo_usuario_app").val()=="9")){

         var rechazo=(valor.split("|")[8]).trim();
         if(idioma=="_us_en"){
             sOut +='<tr>';
             sOut +='<td><span id="transferencias_TAGEmailBeneficiario4" class="label_descp">Beneficiary Email</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="label_transferHistory_email">'+valor.split("|")[0]+'</span></td>';


             sOut +='<td><span id="transferencias_TAGDescripcion" class="label_descp">Description</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="label_transferHistory_descripcion">'+valor.split("|")[1]+'</span></td>';


             sOut +='<td><span id="TAGusuarioCargadorFC" class="label_descp">Loaded by</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioCargadorFC_info">'+valor.split("|")[2]+'</span></td>';


             sOut +='<td><span id="TAGusuarioCargadorFCDate" class="label_descp"> Load date</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioCargadorFCDate_info">'+valor.split("|")[3]+'</span></td>';

             sOut +='</tr>';
             sOut +='<tr>';

             sOut +='<td><span id="TAGusuarioAprobadorFC" class="label_descp"> Approved by</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioAprobadorFC_info">'+valor.split("|")[4]+'</span></td>';


             sOut +='<td><span id="TAGusuarioAprobadorFCDate" class="label_descp"> Approval date</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioAprobadorFCDate_info">'+valor.split("|")[5]+'</span></td>';

             sOut +='<td><span id="TAGusuarioLiberadorFC" class="label_descp">Released by</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioLiberadorFC_info">'+valor.split("|")[6]+'</span></td>';


             sOut +='<td><span id="TAGusuarioLiberadorFCDate" class="label_descp">Release date</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioLiberadorFCDate_info">'+valor.split("|")[7]+'</span></td>';

             sOut +='</tr>';

             if (rechazo!=""){
                 sOut +='<tr>';
                 sOut +='<td><span id="TAGusuarioRechazoFC" class="label_descp"> Rejected by</span><span class="label_descp">:</span></td>';
                 sOut +='<td><span id="TAGusuarioRechazoFC_info">'+valor.split("|")[8]+'</span></td>';


                 sOut +='<td><span id="TAGusuarioRechazoFCrFCDate" class="label_descp">Rejection date</span><span class="label_descp">:</span></td>';
                 sOut +='<td><span id="TAGusuarioRechazoFCrFCDate_info">'+valor.split("|")[9]+'</span></td>';

                 sOut +='<td></td>';
                 sOut +='<td></td>';

                 sOut +='<td></td>';
                 sOut +='<td></td>';

                 sOut +='</tr>';
             }


             sOut +='</table>';
         }else{
             sOut +='<tr>';
             sOut +='<td><span id="transferencias_TAGEmailBeneficiario4" class="label_descp">Email del Beneficiario</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="label_transferHistory_email">'+valor.split("|")[0]+'</span></td>';


             sOut +='<td><span id="transferencias_TAGDescripcion" class="label_descp">Descripción</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="label_transferHistory_descripcion">'+valor.split("|")[1]+'</span></td>';


             sOut +='<td><span id="TAGusuarioCargadorFC" class="label_descp">Cargado por</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioCargadorFC_info">'+valor.split("|")[2]+'</span></td>';


             sOut +='<td><span id="TAGusuarioCargadorFCDate" class="label_descp"> Fecha carga</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioCargadorFCDate_info">'+valor.split("|")[3]+'</span></td>';

             sOut +='</tr>';
             sOut +='<tr>';

             sOut +='<td><span id="TAGusuarioAprobadorFC" class="label_descp">Aprobado por</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioAprobadorFC_info">'+valor.split("|")[4]+'</span></td>';


             sOut +='<td><span id="TAGusuarioAprobadorFCDate" class="label_descp"> Fecha Aprobación</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioAprobadorFCDate_info">'+valor.split("|")[5]+'</span></td>';



             sOut +='<td><span id="TAGusuarioLiberadorFC" class="label_descp">Liberado por</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioLiberadorFC_info">'+valor.split("|")[6]+'</span></td>';


             sOut +='<td><span id="TAGusuarioLiberadorFCDate" class="label_descp">Fecha liberación</span><span class="label_descp">:</span></td>';
             sOut +='<td><span id="TAGusuarioLiberadorFCDate_info">'+valor.split("|")[7]+'</span></td>';

             sOut +='</tr>';

             if (rechazo!=""){
                 sOut +='<tr>';
                 sOut +='<td><span id="TAGusuarioRechazoFC" class="label_descp"> Rechazado por</span><span class="label_descp">:</span></td>';
                 sOut +='<td><span id="TAGusuarioRechazoFC_info">'+valor.split("|")[8]+'</span></td>';


                 sOut +='<td><span id="TAGusuarioRechazoFCrFCDate" class="label_descp">Fecha de rechazo</span><span class="label_descp">:</span></td>';
                 sOut +='<td><span id="TAGusuarioRechazoFCrFCDate_info">'+valor.split("|")[9]+'</span></td>';

                 sOut +='<td></td>';
                 sOut +='<td></td>';

                 sOut +='<td></td>';
                 sOut +='<td></td>';

                 sOut +='</tr>';
             }




             sOut +='</table>';
         }

     }else{

        if(idioma=="_us_en"){
            sOut +='<tr><td width="15%" ><span id="transferencias_TAGEmailBeneficiario4" class="label_descp">Beneficiary Email</span><span class="label_descp">:</span></td>';
            sOut +='<td width="70%"><span id="label_transferHistory_email">'+valor.split("|")[0]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="15%" ><span id="transferencias_TAGDescripcion" class="label_descp">Description</span><span class="label_descp">:</span></td>';
            sOut +='<td width="70%"><span id="label_transferHistory_descripcion">'+valor.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='</table>';
        }else{
            sOut +='<tr><td width="15%" ><span id="transferencias_TAGEmailBeneficiario4" class="label_descp">Email del Beneficiario</span><span class="label_descp">:</span></td>';
            sOut +='<td width="70%"><span id="label_transferHistory_email">'+valor.split("|")[0]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="15%" ><span id="transferencias_TAGDescripcion" class="label_descp">Descripción</span><span class="label_descp">:</span></td>';
            sOut +='<td width="70%"><span id="label_transferHistory_descripcion">'+valor.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='</table>';
        }
     }

    return sOut;
}



function consultarTransferenciasGeneralesJSONData(){
    var url = urlConsultarTransferenciasGenerales;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0] = $("#fechaDesdeFiltro_Transferencias").get(0).value;
    jsonTransfers[1] = $("#fechaHastaFiltro_Transferencias").get(0).value;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,consultarTransferenciasGeneralesSuccess,null,null);
}

/**
 * Imprime div del html
 */

function printConsultTranfer(){

    printPageElement('div_CONSULTA_TRANSFERENCIAS');  //Print EDO CUENTA

}



function consultarTransferenciasGeneralesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    $("#div_tabla_consulta_TransferenciasGenerales").empty();

    crearTablaTmp('div_tabla_consulta_TransferenciasGenerales','tabla_consulta_TransferenciasGenerales',columnas,datos);
//
//

    oTable = $('#tabla_consulta_TransferenciasGenerales').dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "center" },
            {  "sClass": "" },
            {  "sClass": "center" },
            {  "sClass": "right" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}



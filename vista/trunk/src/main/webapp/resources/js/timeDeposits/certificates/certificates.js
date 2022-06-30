var urlConsultarCertificados_ColCer="TimeDeposits_consultarCertificados.action";

var idioma = "";
var oTable="";
var noInfo="";
$(document).ready(function(){

    $( "#fechaDesdeFiltro_ColCer").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_ColCer").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

//    $( "#fechaDesdeFiltro_ColCer").blur(function(){
//        if(!isDate($( "#fechaDesdeFiltro_ColCer").val())&& !Empty($( "#fechaDesdeFiltro_ColCer").val())){
//            $("#mensaje_error").empty();
//            if(idioma!="_us_en")
//                $("#mensaje_error").html("La fecha desde no es correcta");
//            else
//                $("#mensaje_error").html("From date is not correct");
//
//            $("#div_mensajes_error").fadeIn("slow");
//        }
//
//    });
//
//    $( "#fechaHastaFiltro_ColCer").blur(function(){
//        if(!isDate($( "#fechaHastaFiltro_ColCer").val())&& !Empty(( "#fechaHastaFiltro_ColCer").val())){
//            $("#mensaje_error").empty();
//            if(idioma!="_us_en")
//                $("#mensaje_error").html("La fecha desde no es correcta");
//            else
//                $("#mensaje_error").html("From date is not correct");
//
//            $("#div_mensajes_error").fadeIn("slow");
//        }
//
//    });

    $( "#fechaDesdeFiltro_ColCer" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_ColCer" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});



    $("#consultar_ColCer").click(function(){
        var mensaje="";
        $("#div_carga_espera").removeClass("oculto");
        $(".requerido_ColCer").each(function(){
            $("#div_carga_espera").removeClass("oculto");
            if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
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
//
        if($("#buscar_ColocacionesCertificados").val()=="FA" || $("#buscar_ColocacionesCertificados").val()=="FV")
        {
            $(".requeridoFecha_ColCer").each(function(){
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


            if(!isDate($( "#fechaDesdeFiltro_ColCer").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltro_ColCer").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltro_ColCer").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltro_ColCer").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltro_ColCer").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltro_ColCer").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltro_ColCer").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltro_ColCer").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltro_ColCer").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltro_ColCer").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltro_ColCer").addClass("error_campo");
                    $("#fechaHastaFiltro_ColCer").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltro_ColCer").removeClass("error_campo");
                    $("#fechaHastaFiltro_ColCer").removeClass("error_campo");
                }
            }

        }
        $("#div_carga_espera").addClass("oculto");
        if(mensaje!=""){
            $("#certificados_ColCer_tabla_consulta").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#certificados_ColCer_tabla_consulta").fadeIn("fast");
            consultarCertificadosTransJSONData();
        }

    });

});

function abrirDetalleTabla_certificados(elemento){
    var nTr = $(elemento).parents('tr')[0];
    if ( oTable.fnIsOpen(nTr) )
    {
        /* This row is already open - close it */
        elemento.src = "resources/images/comun/nolines_plus.gif";
        oTable.fnClose( nTr );
    }
    else
    {
        /* Open this row */
        elemento.src = "resources/images/comun/nolines_minus.gif";
        oTable.fnOpen( nTr, fnFormatDetailsTimeDepositCertificado($(elemento).attr("id")), 'details');
    }

};

function fnFormatDetailsTimeDepositCertificado (valor)
{   var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="15%"><span class="label_descp" id="colocacionescertificados_TAGCarteraNumero">Client ID N&deg; </span><span class="label_descp">:</span></td>';
        sOut +='<td width="15%"><span id="label_cartera_TD_C">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="colocacionescertificados_TAGMontoVencimiento">Maturity Amount </span><span class="label_descp">:</span></td>';
        sOut +='<td width="15%"><span id="label_Monto_TD_C">'+valor.split("|")[1]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="colocacionescertificados_TAGTasa">Rate </span><span class="label_descp">:</span></td>';
        sOut +='<td width="15%"><span id="label_tasa_TD_C">'+valor.split("|")[2]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="15%"><span class="label_descp" id="colocacionescertificados_TAGCarteraNumero">Cartera N </span><span class="label_descp">:</span></td>';
        sOut +='<td width="15%"><span id="label_cartera_TD_C">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="colocacionescertificados_TAGMontoVencimiento">Monto al vencimiento </span><span class="label_descp">:</span></td>';
        sOut +='<td width="15%"><span id="label_Monto_TD_C">'+valor.split("|")[1]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="colocacionescertificados_TAGTasa">Tasa </span><span class="label_descp">:</span></td>';
        sOut +='<td width="15%"><span id="label_tasa_TD_C">'+valor.split("|")[2]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }



    return sOut;
}

function cargarCertificados(){


    $("#certificados_ColCer_tabla_consulta").empty();
    $("#buscar_ColocacionesCertificados").val("VI");
    $("#fechaDesdeFiltro_ColCer").val("");
    $("#fechaHastaFiltro_ColCer").val("");
    $("#comun2_CERTIFICADOS").attr("style","display: ");
    $("#comun2_CERTIFICADOS_al").attr("style","display: none");
    $("#comun2_CERTIFICADOS_fechaCierre").html("");

    $("#fechas_ColCer").attr("style","display: none");
    $("#fechaDesdeFiltro_ColCerField").attr("style","display: none");
    $("#fechaHastaFiltro_ColCerField").attr("style","display: none");
    $("#panelControlesCertificates").hide();
    $("#buscar_ColocacionesCertificados").html("");
    consultarCertificadosTransJSONData();
}

function validarActivarFechas_ColCer(valor){
//    alert(valor);
   // var myDate = new Date();
    if(valor=="FV" || valor=="FA"){
        $("#fechas_ColCer").fadeIn("fast");
        $("#panelControlesCertificates").fadeIn("fast");
        $("#fechaDesdeFiltro_ColCerField").fadeIn("fast");
        $("#fechaHastaFiltro_ColCerField").fadeIn("fast");
        $("#fechaDesdeFiltro_ColCer").val("");
   //     $("#fechaHastaFiltro_ColCer").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
   }else{
        $("#fechas_ColCer").fadeOut("fast");
        $("#panelControlesCertificates").fadeIn("fast");
        $("#fechaDesdeFiltro_ColCerField").fadeOut("fast");
        $("#fechaHastaFiltro_ColCerField").fadeOut("fast");
        $("#fechaDesdeFiltro_ColCer").val("");
        $("#fechaHastaFiltro_ColCer").val("");
    }

};


function consultarCertificadosTransJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlConsultarCertificados_ColCer;
    var param={};
    var jsonColocaciones=[];
    if($("#buscar_ColocacionesCertificados").val() != null)
        jsonColocaciones[0] = $("#buscar_ColocacionesCertificados").val();
    else
        jsonColocaciones[0] = "VI";
    jsonColocaciones[1] = $("#fechaDesdeFiltro_ColCer").get(0).value;
    jsonColocaciones[2] = $("#fechaHastaFiltro_ColCer").get(0).value;

    param.jsonColocaciones=JSON.stringify({"parametros":jsonColocaciones});

    sendServiceJSON(url,param,consultarCertificadosSuccess,null,null);
}


function consultarCertificadosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var fechacierre = result.fechaCierre;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    var buscar = result.search_ColCer;
    var mensaje = result.mensaje;
    var codigo = result.codigo;

    $("#div_carga_espera").addClass("oculto");
    if(codigo=="0"){

        $("#div_certificados_colocaciones").attr("style","display: ");
        $("#div_noPoseeColocacionesCertificados").attr("style","display: none ");
        $("#certificados_ColCer_tabla_consulta").empty();
        if(Trim($("#buscar_ColocacionesCertificados").html())=="")
            cargar_selectPersonal2("buscar_ColocacionesCertificados", buscar);

        // crearTabla('certificados_ColCer_div_tabla_consulta','certificados_ColCer',columnas,datos);
        crearTablaV2('certificados_ColCer_tabla_consulta',columnas,datos,"",false);
//
//
/*
        oTable = $('#certificados_ColCer').dataTable({
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
                {  "sClass": "center" },
                {  "sClass": "right" },
                {  "sClass": "center" },
                { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
*/

        oTable = $('#certificados_ColCer_tabla_consulta').dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy":true,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center" },
                { "sClass": "center"},
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "right" },
                {  "sClass": "center" },
                { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });

        $("#comun2_CERTIFICADOS").attr("style","display: none");
        $("#comun2_CERTIFICADOS_al").attr("style","display: ");
        $("#comun2_CERTIFICADOS_fechaCierre").html(" "+"<b>"+fechacierre+"</b>");
    }else{
//        $("#mensaje_error").empty();
//        $("#mensaje_error").html(mensaje);
//        $("#div_mensajes_error").fadeIn("slow");
        $("#div_certificados_colocaciones").attr("style","display: none");
        $("#div_noPoseeColocacionesCertificados").attr("style","display: ");
    }



}

//function llamarDescrip_TD_C_click(img){
//
//    $("#label_cartera_TD_C").empty();
//    $("#label_Monto_TD_C").empty();
//    $("#label_tasa_TD_C").empty();
//
//    $("#label_cartera_TD_C").html(img.split("|")[0]);
//    $("#label_Monto_TD_C").html(img.split("|")[1]);
//    $("#label_tasa_TD_C").html(img.split("|")[2]);
//
//    $("#div_mensajes_info_desc_tabla_TD_C").fadeIn("slow");
//
//
//}
//
//function llamarDescrip_TD_C_out(img){
//
//    $("#div_mensajes_info_desc_tabla_TD_C").fadeOut("fast");
//    $("#mensajes_info_desc_tabla_TD_C").empty();
//
//}


function imprimir_detalle_certificados(valor){
    var colocacion;
    var fecha_apertura;
    var fecha_vencimiento;
    var moneda;
    var monto_apertura;
    var tasa;
    var monto;
    var n_cartera;
    var estatus;
    if(Trim(valor.split("|")[0])!=""){n_cartera=valor.split("|")[0];}else{n_cartera="_";}
    if(Trim(valor.split("|")[1])!=""){monto=valor.split("|")[1];}else{monto="_";}
    if(Trim(valor.split("|")[2])!=""){tasa=valor.split("|")[2];}else{tasa="_";}
    if(Trim(valor.split("|")[3])!=""){colocacion=valor.split("|")[3];}else{colocacion="_";}
    if(Trim(valor.split("|")[4])!=""){fecha_apertura=valor.split("|")[4];}else{fecha_apertura="_";}
    if(Trim(valor.split("|")[5])!=""){fecha_vencimiento=valor.split("|")[5];}else{fecha_vencimiento="_";}
    if(Trim(valor.split("|")[6])!=""){moneda=valor.split("|")[6];}else{moneda="_";}
    if(Trim(valor.split("|")[7])!=""){monto_apertura=valor.split("|")[7];}else{monto_apertura="_";}
    if(Trim(valor.split("|")[8])!=""){estatus=valor.split("|")[8];}else{estatus="_";}

    var banco="_";

    crearPDF_certificados(n_cartera,colocacion,
        fecha_apertura,
        fecha_vencimiento,
        moneda,
        monto_apertura,
        monto,
        tasa,
        estatus);
}


function crearPDF_certificados(n_cartera,
                               colocacion,
                               fecha_apertura,
                               fecha_vencimiento,
                               moneda,
                               monto_apertura,
                               monto,
                               tasa,
                               estatus){

    var urlreportcertificados="reportCertificados.action";

    urlreportcertificados = urlreportcertificados + "?num_cta="+$().crypt({method: "b64enc",source:n_cartera})
        +"&colocacion="+    $().crypt({method: "b64enc",source:colocacion})
        +"&fechaValor="+$().crypt({method: "b64enc",source:fecha_apertura})
        +"&fechavencimiento="+$().crypt({method: "b64enc",source:fecha_vencimiento})
        +"&moneda="+$().crypt({method: "b64enc",source:moneda})
        +"&descripcion="+$().crypt({method: "b64enc",source:monto_apertura})
        +"&tasainteres="+$().crypt({method: "b64enc",source:tasa})
        +"&monto="+$().crypt({method: "b64enc",source:monto})
        +"&estatus="+$().crypt({method: "b64enc",source:estatus});
    window.open(urlreportcertificados,'PDF','');
   // window.location.href=  urlreportcertificados;
}

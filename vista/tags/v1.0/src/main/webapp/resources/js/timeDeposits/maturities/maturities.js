var urlConsultarCertificados_ColVen="TimeDeposits_consultarVencimientos.action";

var idioma ="";
var oTable ="";
var noInfo ="";
$(document).ready(function(){


    $( "#fechaDesdeFiltro_ColVen").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_ColVen").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    $( "#fechaDesdeFiltro_ColVen").blur(function(){
        if(!isDate($( "#fechaDesdeFiltro_ColVen").val())&& !Empty($( "#fechaDesdeFiltro_ColVen").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaHastaFiltro_ColVen").blur(function(){
        if(!isDate($( "#fechaHastaFiltro_ColVen").val())&& !Empty($( "#fechaHastaFiltro_ColVen").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $( "#fechaDesdeFiltro_ColVen" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_ColVen" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});



    $("#consultar_ColVen").click(function(){
        cargar_pagina_new=false;
        var mensaje="";
        $("#certificados_ColVen_div_tabla_consulta").fadeOut("fast");
        $(".requerido_ColVen").each(function(){
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
        if($("#buscar_ColocacionesVencimientos").val()=="FV")
        {
            $(".requeridoFecha_ColVen").each(function(){
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

            var fdesdeFormat= $("#fechaDesdeFiltro_ColVen").val().split("/")[1]
                +"/"+$("#fechaDesdeFiltro_ColVen").val().split("/")[0]
                +"/"+$("#fechaDesdeFiltro_ColVen").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fechaHastaFiltro_ColVen").val().split("/")[1]
                +"/"+$("#fechaHastaFiltro_ColVen").val().split("/")[0]
                +"/"+$("#fechaHastaFiltro_ColVen").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(!isDate($( "#fechaDesdeFiltro_ColVen").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltro_ColVen").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltro_ColVen").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltro_ColVen").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltro_ColVen").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltro_ColVen").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltro_ColVen").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltro_ColVen").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltro_ColVen").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltro_ColVen").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltro_ColVen").addClass("error_campo");
                    $("#fechaHastaFiltro_ColVen").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltro_ColVen").removeClass("error_campo");
                    $("#fechaHastaFiltro_ColVen").removeClass("error_campo");
                }
            }
        }

        if(mensaje!=""){
            $("#certificados_ColVen_div_tabla_consulta").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#certificados_ColVen_div_tabla_consulta").fadeIn("fast");


            consultarVencimientosTransJSONData();
        }

    });

});

function fnFormatDetailsTimeDepositMaturities (valor)
{   var sOut = '<table width="100%">';

    if(idioma=="_us_en"){
        sOut +='<tr><td width="15%" ><span id="colocacionesvencimientos_TAGCarteraNumero" class="label_descp">Client ID</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_timeDeposit_maturities">'+valor+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<tr><td width="15%" ><span id="colocacionesvencimientos_TAGCarteraNumero" class="label_descp">Cartera Nro</span><span class="label_descp">:</span></td>';
        sOut +='<td width="70%"><span id="label_timeDeposit_maturities">'+valor+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }
    return sOut;
}


function validarActivarFechas_ColVen(valor){
   // var myDate = new Date();
    if(valor=="FV"){
        $("#fechas_ColVen").fadeIn("fast");
        $("#fechaDesdeFiltro_ColVen").val("");
   //     $("#fechaHastaFiltro_ColVen").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
    }
    else{
        $("#fechas_ColVen").fadeOut("fast");
        $("#fechaDesdeFiltro_ColVen").val("");
        $("#fechaHastaFiltro_ColVen").val("");
    }

};

function cargarVencimientos(){

    $("#buscar_ColocacionesVencimientos").value ="PV";
    $("#fechaDesdeFiltro_ColVen").value ="";
    $("#fechaHastaFiltro_ColVen").value ="";

    $("#fechas_ColVen").attr("style","display: none");
    consultarVencimientosTransJSONData();
}

function consultarVencimientosTransJSONData(){
    var url = urlConsultarCertificados_ColVen;
    var param={};
    var jsonColocaciones=[];
    if($("#buscar_ColocacionesVencimientos").get(0).value != null)
        jsonColocaciones[0] = $("#buscar_ColocacionesVencimientos").get(0).value;
    else
        jsonColocaciones[0] = "";
    jsonColocaciones[1] = $("#fechaDesdeFiltro_ColVen").get(0).value;
    jsonColocaciones[2] = $("#fechaHastaFiltro_ColVen").get(0).value;

    param.jsonColocaciones=JSON.stringify({"parametros":jsonColocaciones});

    sendServiceJSON(url,param,consultarVencimientosSuccess,null,null);
}


function consultarVencimientosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var codigo = result.codigo;
    var fechaCierre = result.fechaCierre;

    var buscar = result.search_ColVen;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    var mensaje = result.mensaje;
    var codigo = result.codigo;

    if(codigo=="0"){
        $("#div_noPoseeColocacionesVencimientos").attr("style","display: none");
        $("#div_vencimientos_colocaciones").attr("style","display: ");
        $("#fechaCierreVencimiento").html(fechaCierre);
        if(cargar_pagina_new)
            cargar_selectPersonal2("buscar_ColocacionesVencimientos", buscar);

        crearTabla('vencimientos_ColVen_div_tabla_consulta','certificados_ColVen',columnas,datos);
//
//
        oTable = $('#certificados_ColVen').dataTable({
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
                {  "sClass": "right" },
                {  "sClass": "right" },
                {  "sClass": "right" },
                { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
//        $("#mensaje_error").empty();
//        $("#mensaje_error").html(mensaje);
//        $("#div_mensajes_error").fadeIn("slow");
        $("#div_noPoseeColocacionesVencimientos").attr("style","display: ");
        $("#div_vencimientos_colocaciones").attr("style","display: none");
    }



}



function print_VENCIMIENTOS(){
    var miValue = $("#buscar_ColocacionesVencimientos" ).val();
    $("#buscar_ColocacionesVencimientos option[value='"+miValue+"']").attr("selected",true);

    printPageElement('div_VENCIMIENTOS');  //Print EDO CUENTA
}

function abrirDetalleTabla_ColocacionesVencimientos(elemento){
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
        oTable.fnOpen( nTr, fnFormatDetailsTimeDepositMaturities($(elemento).attr("id")), 'details');
    }

};

var urlDetalleEdoCuentaOI="OtherInvestments_consultarEstadoCuenta.action";
var urlOtrasInversionesCargar_EC="OtherInvestments_cargarOtrasInversionesRazonMoneda.action"
var urlGenerarEdoCuentaEC = "OtherInvestments_generarPDFEstadoCuenta.action";

var idioma="";
var noInfo="";
var meses="";

$(document).ready(function(){
    $("#div_encabezadogralIO").addClass("oculto");

    $("#estado_cuenta_otrasinversiones_consultar").click(function(){

        limpiarPantallaEDOOI();
        var mensaje="";
        $(".requerido_othersInvestments_AE").each(function(){
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

        if(mensaje!=""){
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            //detalleEdoCuentaFondoMutualJSONData();
            consultarEdoCuentaOtrasInversionesOnclick();
            $("#estado_cuenta_imprimir").fadeIn("fast");
        }
    });
});

function consultarEdoCuentaOtrasInversionesOnclick(){
    var mensaje="";
    $(".requerido_othersInvestments_AE").each(function(){
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

    if(mensaje!=""){
//        $("#estado_cuenta_div_tabla_consulta_BT").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
//      $("#estado_cuenta_imprimir").fadeOut("fast");
    }else{
        detalleEdoCuentaOtrasInversionesJSONData();
    }
}

function AccountStatementsOtherInvestmentsInfoPaginaJSONData(){
    var url = urlOtrasInversionesCargar_EC;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    sendServiceJSON(url,param,AccountStatementOtherInvestmentsInfoSuccess,null,null);
}

function AccountStatementOtherInvestmentsInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var fondosEC = result.fondos;
    meses = result.meses;
    idioma = result.idioma;

    if(fondosEC!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonal("estado_cuenta_otrasinversiones_numero_cuenta", fondosEC,"Select","-2");
        }else{
            cargar_selectPersonal("estado_cuenta_otrasinversiones_numero_cuenta", fondosEC,"Seleccione","-2");
        }
    }
    if(meses!=null)
        cargar_selectPersonal2("estado_cuenta_otrasinversiones_fecha_mes", meses);

    $("#estado_cuenta_otrasinversiones_fecha_anio").val(fechaHoy.split("/")[2]);

}

function detalleEdoCuentaOtrasInversionesJSONData(){
    $("#div_carga_espera").removeClass("oculto")
    var url = urlDetalleEdoCuentaOI;
    var param={};
    var jsonDetalleEdoCuentaOI=[];

    jsonDetalleEdoCuentaOI[0] = ($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[0];
    jsonDetalleEdoCuentaOI[1] = ($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[1];

    if ($("#estado_cuenta_otrasinversiones_fecha_mes").get(0).value<10)
        jsonDetalleEdoCuentaOI[2] = "01/0"+$("#estado_cuenta_otrasinversiones_fecha_mes").get(0).value+"/"+$("#estado_cuenta_otrasinversiones_fecha_anio").get(0).value;
    else
        jsonDetalleEdoCuentaOI[2] = "01/"+$("#estado_cuenta_otrasinversiones_fecha_mes").get(0).value+"/"+$("#estado_cuenta_otrasinversiones_fecha_anio").get(0).value;

    jsonDetalleEdoCuentaOI[3] = ($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[2];
    jsonDetalleEdoCuentaOI[4] = ($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[3];
    jsonDetalleEdoCuentaOI[5] = ($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[4];

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonDetalleEdoCuentaOI=JSON.stringify({"parametros":jsonDetalleEdoCuentaOI});
    sendServiceJSON(urlDetalleEdoCuentaOI,param,detalleEdoCuentaOtrasInversionesSuccess,null,null);
}

function detalleEdoCuentaOtrasInversionesSuccess(originalRequest){

    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var encabezado = result.listaEncabezado;
    var valorEncab = result.encabezado;
    var respuesta=result.mensaje;

    $("#div_carga_espera").addClass("oculto")
    if (respuesta=="OK"){
        $.each(encabezado,function(posGeneral,itemGeneral){
            var encontro=false;
            $.each(label_es,function(pos,item){
                if (Trim(item.split("=")[0])==Trim(itemGeneral.etiqueta)){
                    encontro=true;
                    label_es[pos]=Trim(itemGeneral.etiqueta)+"="+Trim(itemGeneral.texto_esp);
                }
            });
            if (encontro==false){
                label_es.push(Trim(itemGeneral.etiqueta)+"="+Trim(itemGeneral.texto_esp));
            }
            if(idioma=="_ve_es"){
                $("#"+Trim(itemGeneral.etiqueta)).text(Trim(itemGeneral.texto_esp))
            }
        });

        $.each(encabezado,function(posGeneral,itemGeneral){
            var encontro=false;
            $.each(label_en,function(pos,item){
                if (Trim(item.split("=")[0])==Trim(itemGeneral.etiqueta)){
                    encontro=true;
                    label_en[pos]=Trim(itemGeneral.etiqueta)+"="+Trim(itemGeneral.texto_ing);
                }
            });
            if (encontro==false){
                label_en.push(Trim(itemGeneral.etiqueta)+"="+Trim(itemGeneral.texto_ing));
            }
            if(idioma=="_us_en"){
                $("#"+Trim(itemGeneral.etiqueta)).text(Trim(itemGeneral.texto_ing))
            }
        });

        $("#ASIO_holder").html(valorEncab.cliente);
        $("#ASIO_totalBalance").html(valorEncab.unidadesTotales);
        // $("#ASIO_currency").html(valorEncab.moneda);
        $("#ASIO_currency").html(createCurrency(valorEncab.moneda));
        $("#ASIO_guarantee").html(valorEncab.unidadesGarantia);
        $("#ASIO_avalaible").html(valorEncab.unidadesDisponibles);
        $("#ASIO_NAV").html(valorEncab.VUI);
        // $("#ASIO_currencybalance").html(valorEncab.totalMoneda);
        $("#ASIO_currencybalance").html(createCurrency(valorEncab.totalMoneda));
        $("#div_encabezadogralIO").removeClass("oculto");
        // crearTabla('estado_cuenta_otrasinversiones_div_tabla_consulta','estado_cuenta_otrasinversiones_tabla_consulta',columnas,datos);
        crearTablaV2('estado_cuenta_otrasinversiones_tabla_consulta',columnas,datos,"",false);

        if(idioma=="_ve_es"){
/*
            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "No Hay Informaci&oacute;n",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
*/

            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": false,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bDestroy": true,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "No Hay Informaci&oacute;n",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });

        }else if(idioma=="_us_en"){
/*
            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "Nothing found...",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
*/
            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": false,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bDestroy":true,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "Nothing found...",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
        }
    }else{
        $("#div_encabezadogralIO").addClass("oculto");
        // crearTabla('estado_cuenta_otrasinversiones_div_tabla_consulta','estado_cuenta_otrasinversiones_tabla_consulta',columnas,datos);
        crearTablaV2('estado_cuenta_otrasinversiones_tabla_consulta',columnas,datos,"",true);

        if(idioma=="_ve_es"){
/*
            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "No Hay Informaci&oacute;n",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
*/

            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": false,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bDestroy" : true,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "No Hay Informaci&oacute;n",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });

        }else if(idioma=="_us_en"){
/*
            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "Nothing found...",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
*/

            var oTable = $('#estado_cuenta_otrasinversiones_tabla_consulta').dataTable({
                "bJQueryUI": false,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bDestroy" : true,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "Nothing found...",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });

        }
    }
}

function limpiarPantallaEDOOI(){
    $("#ASIO_holder").empty();
    $("#ASIO_NAV").empty();
    $("#ASIO_totalBalance").empty();
    $("#ASIO_currency").empty();
    $("#ASIO_guarantee").empty();
    $("#ASIO_avalaible").empty();
    $("#ASIO_currencybalance").empty();
    $("#div_encabezadogralIO").addClass("oculto");
    // $("#estado_cuenta_otrasinversiones_div_tabla_consulta").empty();
    $("#estado_cuenta_otrasinversiones_tabla_consulta").empty();
}

function crearPDF_ESTADO_CUENTA_OI(){
    var url = urlGenerarEdoCuentaEC;

    var paramCero =  $().crypt({method: "b64enc",source:($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[0]});
    var paramUno =  $().crypt({method: "b64enc",source:($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[1]});
    var fecha = null;

    if ($("#estado_cuenta_otrasinversiones_fecha_mes").get(0).value<10){
        fecha = "01/0"+$("#estado_cuenta_otrasinversiones_fecha_mes").get(0).value+"/"+$("#estado_cuenta_otrasinversiones_fecha_anio").get(0).value;
    }else{
        fecha = "01/"+$("#estado_cuenta_otrasinversiones_fecha_mes").get(0).value+"/"+$("#estado_cuenta_otrasinversiones_fecha_anio").get(0).value;
    }
    var paramDos =  $().crypt({method: "b64enc",source:fecha});
    var paramTres =  $().crypt({method: "b64enc",source:($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[2]});
    var paramCuatro =  $().crypt({method: "b64enc",source:($("#estado_cuenta_otrasinversiones_numero_cuenta").get(0).value).split("|")[3]});
    var paramCinco =  $().crypt({method: "b64enc",source:($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[4]});

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    var urlPDFECInstruction  = url;
    urlPDFECInstruction = urlPDFECInstruction+"?paramCero="+paramCero+"&paramUno="+paramUno+"&paramDos="+paramDos+"&paramTres="+paramTres+"&paramCuatro="+paramCuatro+"&paramCinco="+paramCinco;

    window.open(urlPDFECInstruction,'PDF','');
}

function generarPDFEdoCuentaOtrasInversionesSuccess(){
    $("#div_carga_espera").addClass("oculto");
}
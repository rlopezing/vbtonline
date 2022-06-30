urlFondosMutualesCargar_EC = "MutualFunds_cargarFondosMutualesRazonMoneda.action";
urlDetalleEdoCuentaFM = "MutualFunds_consultarEstadoCuenta.action";
urlGenerarEdoCuentaFM = "MutualFunds_generarPDFEstadoCuenta.action";
var mes = "";

$(document).ready(function(){

    $("#div_encabezadogralFM").addClass("oculto");
    $("#estado_cuenta_fondos_consultar").click(function(){

        limpiarPantallaEDOMutualFund();
        var mensaje="";
        $(".requerido_mutualFund_AE").each(function(){
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
            detalleEdoCuentaFondoMutualJSONData();
            $("#estado_cuenta_imprimir").fadeIn("fast");
        }
    });
});

function AccountStatementMutualFundsInfoPaginaJSONData(){
    var url = urlFondosMutualesCargar_EC;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    sendServiceJSON(url,param,AccountStatementMutualFundsInfoSuccess,null,null);
}

function AccountStatementMutualFundsInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var fondosEC = result.fondos;
    meses = result.meses;
    idioma = result.idioma;

    if(fondosEC!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonal("estado_cuenta_fondos_numero_cuenta", fondosEC,"Select","-2");
        }else{
            cargar_selectPersonal("estado_cuenta_fondos_numero_cuenta", fondosEC,"Seleccione","-2");
        }
    }
    if(meses!=null)
        cargar_selectPersonal2("estado_cuenta_fondos_fecha_mes", meses);

    $("#estado_cuenta_fondos_fecha_anio").val(fechaHoy.split("/")[2]);
}

function consultarEdoCuentaFondoMutualOnclick(){

    var mensaje="";
    $("#div_carga_espera").removeClass("oculto");

    $(".requerido_mutualFund_AE").each(function(){
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
        $("#estado_cuenta_div_tabla_consulta_BT").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
//        $("#estado_cuenta_imprimir").fadeOut("fast");
    }else{
        detalleEdoCuentaFondoMutualJSONData();
    }
}

function detalleEdoCuentaFondoMutualJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlDetalleEdoCuentaFM;
    var param={};
    var jsonDetalleEdoCuentaFM=[];

    jsonDetalleEdoCuentaFM[0] = ($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[0];
    jsonDetalleEdoCuentaFM[1] = ($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[1];
    if ($("#estado_cuenta_fondos_fecha_mes").get(0).value<10)
        jsonDetalleEdoCuentaFM[2] = "01/0"+$("#estado_cuenta_fondos_fecha_mes").get(0).value+"/"+$("#estado_cuenta_fondos_fecha_anio").get(0).value;
    else
        jsonDetalleEdoCuentaFM[2] = "01/"+$("#estado_cuenta_fondos_fecha_mes").get(0).value+"/"+$("#estado_cuenta_fondos_fecha_anio").get(0).value;

    jsonDetalleEdoCuentaFM[3] = ($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[2];
    jsonDetalleEdoCuentaFM[4] = ($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[3];
    jsonDetalleEdoCuentaFM[5] = ($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[4];

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonDetalleEdoCuentaFM=JSON.stringify({"parametros":jsonDetalleEdoCuentaFM});

    sendServiceJSON(urlDetalleEdoCuentaFM,param,detalleEdoCuentaFondoMutualSuccess,null,null);
}


function detalleEdoCuentaFondoMutualSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var encabezado = result.listaEncabezado;
    var valorEncab = result.encabezado;
    var respuesta=result.mensaje;

    $("#div_encabezadogralFM").removeClass("oculto");

    $("#div_carga_espera").addClass("oculto");
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
        $("#div_encabezadogralFM").removeClass("oculto");
        $("#ASFunds_holder").html(valorEncab.cliente);
        $("#ASFunds_totalBalance").html(valorEncab.unidadesTotales);
        // $("#ASFunds_currency").html(valorEncab.moneda);
        $("#ASFunds_currency").html(createCurrency(valorEncab.moneda));
        $("#ASFunds_guarantee").html(valorEncab.unidadesGarantia);
        $("#ASFunds_avalaible").html(valorEncab.unidadesDisponibles);
        $("#ASFunds_NAV").html(valorEncab.VUI);
        $("#ASFunds_currencybalance").html(valorEncab.totalMoneda);

        // crearTabla('estado_cuenta_fondos_div_tabla_consulta','estado_cuenta_fondos_tabla_consulta',columnas,datos);
        crearTablaV2('estado_cuenta_fondos_tabla_consulta',columnas,datos,"",true);

        if(idioma=="_us_en"){
/*            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
            });*/
            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
        }else if(idioma=="_ve_es"){
/*            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
            });*/
            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
                    "sZeroRecords": "No Hay Informaci&oacute;n",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
        }
    }else{
        // crearTabla('estado_cuenta_fondos_div_tabla_consulta','estado_cuenta_fondos_tabla_consulta',columnas,datos);
        crearTablaV2('estado_cuenta_fondos_tabla_consulta',columnas,datos,"",true);
        $("#div_encabezadogralFM").addClass("oculto");
        if(idioma=="_us_en"){
/*
            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
        }else if(idioma=="_ve_es"){
/*
            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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

            var oTable = $('#estado_cuenta_fondos_tabla_consulta').dataTable({
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
                    "sZeroRecords": "No Hay Informaci&oacute;n",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });

        }
    }
}

function crearPDF_ESTADO_CUENTA_FONDOS(){
    var url = urlGenerarEdoCuentaFM;

    var paramCero =  $().crypt({method: "b64enc",source:($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[0]});
    var paramUno =  $().crypt({method: "b64enc",source:($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[1]});
    var fecha = null;

    if ($("#estado_cuenta_fondos_fecha_mes").get(0).value<10){
        fecha = "01/0"+$("#estado_cuenta_fondos_fecha_mes").get(0).value+"/"+$("#estado_cuenta_fondos_fecha_anio").get(0).value;
    }else{
        fecha = "01/"+$("#estado_cuenta_fondos_fecha_mes").get(0).value+"/"+$("#estado_cuenta_fondos_fecha_anio").get(0).value;
    }
    var paramDos =  $().crypt({method: "b64enc",source:fecha});
    var paramTres =  $().crypt({method: "b64enc",source:($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[2]});
    var paramCuatro =  $().crypt({method: "b64enc",source:($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[3]});
    var paramCinco =  $().crypt({method: "b64enc",source:($("#estado_cuenta_fondos_numero_cuenta").get(0).value).split("|")[4]});

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    var urlPDFECInstruction  = url;
    urlPDFECInstruction = urlPDFECInstruction+"?paramCero="+paramCero+"&paramUno="+paramUno+"&paramDos="+paramDos+"&paramTres="+paramTres+"&paramCuatro="+paramCuatro+"&paramCinco="+paramCinco;

    window.open(urlPDFECInstruction,'PDF','');
}

function generarPDFEdoCuentaFondoMutualSuccess(){
}

function limpiarTabla_AEFM(){
    $("#div_accountStatement_account .spanFormulario_Accounts_AE").each(function(){
        this.innerHTML="";
    });
//    $("#estado_cuenta_fecha_mes").val(mes);
//    $("#estado_cuenta_fecha_anio").val(anio);
//    $("#estado_cuenta_imprimir").fadeOut("fast");
//    $("#estado_cuenta_div_tabla_consulta").empty();
}

function limpiarTabla_AEFM(){   /*
    $("#div_accountStatement_account .spanFormulario_Accounts_AE").each(function(){
        this.innerHTML="";
    });
//    $("#estado_cuenta_fecha_mes").val(mes);
//    $("#estado_cuenta_fecha_anio").val(anio);
    $("#estado_cuenta_imprimir").fadeOut("fast");
    $("#estado_cuenta_div_tabla_consulta").empty(); */
}

function crearPDF_accountStatement_MutualFounds(){

    var num_cta =  $().crypt({method: "b64enc",source:$("#estado_cuenta_numero_cuenta").get(0).value});
    var mes =  $().crypt({method: "b64enc",source:$("#estado_cuenta_fecha_mes").val()});
    var anio =  $().crypt({method: "b64enc",source:$("#estado_cuenta_fecha_anio").val()});

    urlReportEdoCuenta="reportEdoCuenta.action";
    urlReportEdoCuenta = urlReportEdoCuenta + "?num_cta="+num_cta+"&mes="+mes+"&anio="+anio;
    window.open(urlReportEdoCuenta);
}

function limpiarPantallaEDOMutualFund(){
    $("#ASFunds_holder").empty();
    $("#ASFunds_NAV").empty();
    $("#ASFunds_totalBalance").empty();
    $("#ASFunds_currency").empty();
    $("#ASFunds_guarantee").empty();
    $("#ASFunds_avalaible").empty();
    $("#ASFunds_currencybalance").empty();
    $("#div_encabezadogralFM").addClass("oculto");
    // $("#estado_cuenta_fondos_div_tabla_consulta").empty();
    $("#estado_cuenta_fondos_tabla_consulta").empty();
}

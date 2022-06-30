var urlAccountStatementCargar="Accounts_cargarAccountStatement.action";
var urlDetalleEdoCuenta="Accounts_consultarAccountStatement.action";
var urlReportEdoCuenta="reportEdoCuenta.action";

var mes = "";
var anio = "";
var idioma = "";

$(document).ready(function(){




    $("#estado_cuenta_consultar").click(function(){
//
        var mensaje="";
        $(".requerido_account_AE").each(function(){
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
//        if(mensaje==""){
//            if($("#estado_cuenta_fecha_anio").val()>anio || ($("#estado_cuenta_fecha_anio").val()==anio && $("#estado_cuenta_fecha_mes").val()>mes)){
//                if(idioma=="_us_en")
//                    mensaje=mensaje+ "The account statement you wish to consult is not available."+"<br>";
//                else
//                    mensaje=mensaje+ "El estado de cuenta que ud. desea consultar no est&aacute; disponible."+"<br>";
//                $("#estado_cuenta_fecha_anio").addClass("error_campo");
//                limpiarTabla_AE();
//            }
//        }

        if(mensaje!=""){
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{

            detalleEdoCuentaJSONData();
            $("#estado_cuenta_imprimir").fadeIn("fast");

        }

    });

});


function consultarEdoCuentaOnclick(){
    var mensaje="";
    $(".requerido_account_AE").each(function(){
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

//    if(mensaje==""){
//        if($("#estado_cuenta_fecha_anio").val()>anio || ($("#estado_cuenta_fecha_anio").val()==anio && $("#estado_cuenta_fecha_mes").val()>mes)){
//            if(idioma=="_us_en")
//                mensaje=mensaje+ "The account statement you wish to consult is not available."+"<br>";
//            else
//                mensaje=mensaje+ "El estado de cuenta que ud. desea consultar no est&aacute; disponible."+"<br>";
//            $("#estado_cuenta_fecha_anio").addClass("error_campo");
//            limpiarTabla_AE();
//        }
//    }


    if(mensaje!=""){
        $("#estado_cuenta_div_tabla_consulta_BT").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        $("#estado_cuenta_imprimir").fadeOut("fast");
    }else{

        detalleEdoCuentaJSONData();
        $("#estado_cuenta_imprimir").fadeIn("fast");

    }
}

function crearPDF_accountStatement_account(){

    var url = '/vbtonline/reporter?command=PDFCuentasEdoCuenta&numeroCuenta=<%=strNumeroCuenta%>&codigoCartera=<%=strCodigoCartera%>&cmbMes=<%=strCmbMes%>&txtAño=<%=strTxtAño%>';

    var num_cta =  $().crypt({method: "b64enc",source:$("#estado_cuenta_numero_cuenta").get(0).value});
    var mes =  $().crypt({method: "b64enc",source:$("#estado_cuenta_fecha_mes").val()});
    var anio =  $().crypt({method: "b64enc",source:$("#estado_cuenta_fecha_anio").val()});

    urlReportEdoCuenta="reportEdoCuenta.action";
    urlReportEdoCuenta = urlReportEdoCuenta + "?num_cta="+num_cta+"&mes="+mes+"&anio="+anio;
    window.open(urlReportEdoCuenta);
}

    function limpiarTabla_AE(){
    $("#div_accountStatement_account .spanFormulario_Accounts_AE").each(function(){
        this.innerHTML="";
    });
//    $("#estado_cuenta_fecha_mes").val(mes);
//    $("#estado_cuenta_fecha_anio").val(anio);
    $("#estado_cuenta_imprimir").fadeOut("fast");
    $("#estado_cuenta_div_tabla_consulta").empty();
}

function AccountsStatementInfoPaginaJSONData(){
    var url = urlAccountStatementCargar;
    var param={};
//    $("#mensaje_error").empty();
//    $("#div_mensajes_error").fadeOut("fast");
//    blanquearFormulariosPersonal("div_accountStatement_account","_Accounts_AE");
    $("#div_accountStatement_account .selectFormulario_Accounts_AE").each(function(){
        this.value="-2";
    });
    $("#div_accountStatement_account .inputFormulario_Accounts_AE").each(function(){
        this.value="";
    });
    $("#div_accountStatement_account .spanFormulario_Accounts_AE").each(function(){
        this.innerHTML="";
    });
    $("#estado_cuenta_fecha_mes").val(mes);
    $("#estado_cuenta_fecha_mes").removeClass("error_campo");
    $("#estado_cuenta_fecha_anio").val(anio);
    $("#estado_cuenta_fecha_anio").removeClass("error_campo");
    $("#estado_cuenta_imprimir").fadeOut("fast");
    $("#estado_cuenta_div_tabla_consulta").empty();
//    $("#estado_cuenta_tabla_consulta").empty();

    sendServiceJSON(url,param,AccountsStatementInfoSuccess,null,null);
}


function AccountsStatementInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var cuentasAS = result.cuentas;
    meses = result.meses;
    mes = result.mes;
    anio = result.anio;
    idioma = result.idioma;
    var codigo = result.codigo;


    if(codigo=="0" && cuentasAS!=null){
        if(idioma=="_us_en")
            cargar_selectPersonal("estado_cuenta_numero_cuenta", cuentasAS,"Select","-2");
        else
            cargar_selectPersonal("estado_cuenta_numero_cuenta", cuentasAS,"Seleccione","-2");
        if(meses!=null)
            cargar_selectPersonal2("estado_cuenta_fecha_mes", meses);

        $("#estado_cuenta_fecha_mes").val(mes);
        $("#estado_cuenta_fecha_anio").val(anio);
        $("#div_accountStatement_account").attr("style","display: ");
        $("#div_noInfo_accountStatement_account").attr("style","display: none");

    }else{
      $("#div_accountStatement_account").attr("style","display: none");
      $("#div_noInfo_accountStatement_account").attr("style","display: ");
    }



//    if(fechaCierre!=null)
//        $("#tagAvailableBalanceDate").html(fechaCierre);

}

function detalleEdoCuentaJSONData(){
    var param={};
    var jsonDetalleEdoCuenta=[];

//    vbt_cuentas_edo_cabecera_pr (p_strNumeroCuenta IN VARCHAR2,
//        p_strCodigoCartera IN VARCHAR2,
//        p_strCmbMes IN VARCHAR2,
//        p_strTxtAño IN VARCHAR2,
//        p_vbt_cuentas_edo_cabecera OUT vbt_cuentas_edo_cabecera,
//        p_resultado OUT VARCHAR2) AS
    jsonDetalleEdoCuenta[0] =  $("#estado_cuenta_numero_cuenta").get(0).value;
    jsonDetalleEdoCuenta[1] = $("#estado_cuenta_fecha_mes").get(0).value;
    jsonDetalleEdoCuenta[2] = $("#estado_cuenta_fecha_anio").val();

    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonDetalleEdoCuenta=JSON.stringify({"parametros":jsonDetalleEdoCuenta});

    sendServiceJSON(urlDetalleEdoCuenta,param,detalleEdoCuentaSuccess,null,null);
}


function detalleEdoCuentaSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var codigo = result.codigo;
    var mensaje = result.mensaje;

    if(codigo=="1"){

        $("#titular_AS").html(detalle.cliente);
        $("#moneda_AS").html(detalle.moneda);
        $("#bloqueado_AS").html(detalle.bloqueado);
        $("#diferido_AS").html(detalle.diferido);
        $("#disponible_AS").html(detalle.saldoDisp);
        $("#saldo_actual_AS").html(detalle.saldoActual);

        crearTabla('estado_cuenta_div_tabla_consulta','estado_cuenta_tabla_consulta',columnas,datos);
//
        if(idioma=="_us_en"){
            var oTable = $('#estado_cuenta_tabla_consulta').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "" },
                    {  "sClass": "center" },
                    {  "sClass": "right" },
                    {  "sClass": "right" },
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": "Nothing found",
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });
        }else{
            var oTable = $('#estado_cuenta_tabla_consulta').dataTable({
                "bJQueryUI": true,
                "sPaginationType": "full_numbers",
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    {  "sClass": "" },
                    {  "sClass": "center" },
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
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }

}

function reporteEdoCuentaJSONData(){
    var url = urlReportEdoCuenta;
    var param={};
    var jsonDetalleEdoCuenta=[];

    jsonDetalleEdoCuenta[0] =  $("#estado_cuenta_numero_cuenta").get(0).value;

    param.jsonDetalleEdoCuenta=JSON.stringify({"parametros":jsonDetalleEdoCuenta});

    sendServiceJSON(url,param,detalleEdoCuentaSuccess,null,null);
}




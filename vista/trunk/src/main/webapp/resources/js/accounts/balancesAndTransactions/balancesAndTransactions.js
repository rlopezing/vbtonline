var urlAccountStatementCargar_BT="Accounts_cargarAccountStatement.action";
var urlDetalleBalancesAndTransaction_BT="Accounts_consultarBalancesAndTransaction.action";
var urlConsultarCuentaBalancesAndTransaction_BT="Accounts_consultarCuentaSaldoTrans.action";
var urlGuardarComentario_BT = "Accounts_guardarComentarioTrans.action";


var idioma="";
var noInfo="";
var oTable="";


$(document).ready(function(){

    $( "#fechaDesdeFiltroBT").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltroBT").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

   /* $( "#fechaDesdeFiltroBT").blur(function(){
        if(!isDate($( "#fechaDesdeFiltroBT").val()) && !Empty($( "#fechaDesdeFiltroBT").val())){
            $("#mensaje_error").empty();
            $(this).val("");
            if(idioma=="_us_en")
                $("#mensaje_error").html("From date is not correct");
            else
                $("#mensaje_error").html("La fecha desde no es correcta");

            $("#div_mensajes_error").fadeIn("slow");
        }
    });  */

  /*  $( "#fechaHastaFiltroBT").blur(function(){
        if(!isDate($( "#fechaHastaFiltroBT").val())&& !Empty($( "#fechaHastaFiltroBT").val())){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html("From date is not correct");
            else
                $("#mensaje_error").html("La fecha desde no es correcta");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });   */



    $( "#fechaDesdeFiltroBT" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltroBT" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});


    $("#estado_cuenta_consultar_BT").click(function(){
        $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
        realizarConsultaBT();
    });


});

function abrirDetalleTabla(elemento){
    var nTr = $(elemento).parents('tr')[0];
    console.log('component muestra detalle ',nTr);
    console.log('component muestra detalle ',elemento);

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
        oTable.fnOpen( nTr, fnFormatDetailsAccount_BT($(elemento)), 'details');
    }

};


function cerrarAgregarComentario(elemento){
    var nTr = $(elemento).parents('tr').parents('tr').prev('tr')[0];
    if ( oTable.fnIsOpen(nTr) )
    {
        /* This row is already open - close it */
        elemento.src = "resources/images/comun/nolines_plus.gif";
        oTable.fnClose( nTr );
    }
};

function abrirAgregarComentario(elemento){
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
        oTable.fnOpen( nTr, fnFormatAddCommentary_BT($(elemento)), 'details');
    }

};

function fnFormatAddCommentary_BT (valor) {
    var intervalo = setInterval(function(){
        if ($("#"+valor.get(0).id.split("|")[0]).is(":visible") ){
            $("#"+valor.get(0).id.split("|")[0]).click(function(){
                var id = $(this).attr('id');
                var comentario = $("#comentario_BT"+id).attr('value');
                // $("#comentario_BT"+id).addClass('class','only_alpha_num');
                guardar_comentarioBT(id,comentario);
            })
            $('.only_alpha_num').keypress(function (e) {
                var regex = new RegExp("^[A-Za-z0-9]+$");
                var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
                /*console.log("Tecla : " + str + " -> " + e.which + " -> " + e.charCode);*/
                if (regex.test(str)) {
                    return true;
                }
                if ((e.which==8)||(e.which==13)||(e.which==32)||(e.which==0)) {
                    return true;
                }
                e.preventDefault();
                return false;
            });
            clearInterval(intervalo);
        }
    }, 1000);
    var sOut = '<table width="100%">'
    sOut +='<table>';
    sOut +='<tr>';
    sOut +='<td>' + vbtol_props[idioma]["BAT_Commentary"] + '&nbsp;&nbsp;<input maxlength="50" type="text" class="input input--inline input--form only_alpha_num" value="'+valor.get(0).id.split("|")[1]+'" style="width:266px;" id="comentario_BT'+valor.get(0).id.split("|")[0]+'" />&nbsp;&nbsp;<input type="button" id ="'+valor.get(0).id.split("|")[0]+'"  class="button button--detailsTable"  value="'+vbtol_props[idioma]["comun_TAGBtnGuardar"]+'" />&nbsp;&nbsp;<input type="button" id ="cl'+valor.get(0).id.split("|")[0]+'" onclick="cerrarAgregarComentario(this)"  class="button button--white button--detailsTable"  value="'+vbtol_props[idioma]["comun_TAGBtnCerrar"]+'" /></td>';
    sOut +='</tr>';
    sOut +='</table>';
    return sOut;
}

function fnFormatDetailsAccount_BT (valor) {
    var sOut = '<table width="100%">';
    cargarIdiomaJSONData_sinc();
    switch(valor.get(0).title) {
        case "CHC": { // Formato 1
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Beneficiary"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CheckNumber"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Place"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "CIC": { // Formato 2
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientIdSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_AccountNumberSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Originator"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "CID": { // Formato 3
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Beneficiary"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientIdDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_AccountNumberDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "DP":
        case "DPE": { // Formato 4
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Originator"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CheckNumber"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_IssuerBank"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "LOP":
        case "LOR": { // Formato 5
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_LoanDate"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Term"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_InterestRate"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "RCH": { // Formato 6
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Originator"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CheckNumber"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">&nbsp;</td>';
            sOut +='<td width="25%">&nbsp;</td>';
            break;
        }
        case "STP": { // Formato 7
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Beneficiary"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CheckNumber"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Description"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "TEI": { // Formato 8
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Originator"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_OriginatorBank"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ReceiverInformation"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "TEO": { // Formato 9
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Beneficiary"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_BeneficiaryBank"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_BeneficiaryInformation"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "TIC": { // Formato 10
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientNameSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientIdSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_TDSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "TID": { // Formato 11
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientNameDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientIdDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_TDDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "MFD": { // Formato 12
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Beneficiary"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientIdDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CompanyNameDestiny"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        case "MFC": { // Formato 13
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_ClientIdSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CompanyNameSource"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[8]+'</td>';
            sOut +='</tr><tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Originator"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[9]+'</td>';
            break;
        }
        default: { // Formato 0 - General
            sOut +='<tr>';
            sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_Observation"] + '</td>';
            sOut +='<td width="25%">'+valor.get(0).id.split("|")[7]+'</td>';
        }
    }

    sOut +='<td width="25%">' + vbtol_props[idioma]["BAT_CurrencyRate"] + '</td>';
    sOut +='<td width="25%">'+valor.get(0).id.split("|")[5]+'</td>';
    sOut +='</tr><tr>';
    sOut +='<td width="25%">&nbsp;</td>';
    sOut +='<td width="25%">&nbsp;</td>';
    sOut +='<td width="25%">&nbsp;</td>';
    sOut +='<td width="25%" style="text-align: right;"><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" /></td>';
    sOut +='</tr>';
    sOut +='</table>';
    return sOut;

}

function cargarDetalleCuenta(value){
    $("#div_carga_espera").removeClass("oculto");
    if(value!="-2"){
        detalleSaldoTransJSONData();
        $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
        $("#estado_cuenta_div_tabla_consulta_BT").empty();
//      $("#fechas_BT").fadeOut("fast");
//      $("#fechaDesdeFiltroBT").val("");
//      $("#fechaHastaFiltroBT").val("");
//      $("#transaccion_desde_BT").val("5");
    }
    else{
        $("#estado_cuenta_numero_cuenta_BT").removeClass("error_campo");

        $("#titular_BT").html("");
        $("#moneda_BT").html("");
        $("#bloqueado_BT").html("");
        $("#diferido_BT").html("");
        $("#disponible_BT").html("");
        $("#saldo_actual_BT").html("");
//      if(idioma=="_us_en"){
        $("#tagAccount_DatosCuenta_BT").attr("style","display: ");
        $("#tagAccount_DatosCuenta_BT2").attr("style","display: none");
        $("#tagAccount_fecha_BT").html("");
//      }
//      else{
//          $("#tagAccount_DatosCuenta_BT").html("Saldo");
//          $("#tagAccount_fecha_BT").html("");
//      }
        $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
        $("#estado_cuenta_div_tabla_consulta_BT").empty();
        $("#fechas_BT").fadeOut("fast");
        $("#cuentassaldos_TAGFechaDesde").attr("style","display: block");
        $("#cuentassaldos_TAGFechaHasta").attr("style","display: block");
        $("#fechaDesdeFiltroBT").attr("style","display: block");
        $("#fechaHastaFiltroBT").attr("style","display: block");
        $("#fechaDesdeFiltroBT").val("");
        $("#fechaHastaFiltroBT").val("");
        $("#transaccion_desde_BT").val("5");
    }
    $("#div_carga_espera").addClass("oculto");

};

function validarActivarFechas(valor){
    //var myDate = new Date();
    if(valor=="-1"){
        $("#fechaDesdeFiltroBT").val("");
        //$("#fechaHastaFiltroBT").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
        $("#fechas_BT").fadeIn("fast");
        $("#cuentassaldos_TAGFechaDesde").attr("style","display: block");
        $("#cuentassaldos_TAGFechaHasta").attr("style","display: block");
        $("#fechaDesdeFiltroBT").attr("style","display: block");
        $("#fechaHastaFiltroBT").attr("style","display: block");
    }
    else{
        $("#fechas_BT").fadeOut("fast");
        $("#cuentassaldos_TAGFechaDesde").attr("style","display: none");
        $("#cuentassaldos_TAGFechaHasta").attr("style","display: none");
        $("#fechaDesdeFiltroBT").attr("style","display: none");
        $("#fechaHastaFiltroBT").attr("style","display: none");
        $("#fechaDesdeFiltroBT").val("");
        $("#fechaHastaFiltroBT").val("");
    }

};


function BalancesAndTransactionInfoPaginaJSONData(){
    var url = urlAccountStatementCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    $("#div_account_BalancesAndTransactions .selectFormulario_Account_BA").each(function(){
        this.value="-2";
    });
    $("#div_account_BalancesAndTransactions .inputFormulario_Account_BA").each(function(){
        this.value="";
    });
    $("#div_account_BalancesAndTransactions .spanFormulario_Account_BA").each(function(){
        this.innerHTML="";
    });
    $("#fechas_BT").attr("style","display: none");
    $("#cuentassaldos_TAGFechaDesde").attr("style","display: none");
    $("#cuentassaldos_TAGFechaHasta").attr("style","display: none");
    $("#fechaDesdeFiltroBT").attr("style","display: none");
    $("#fechaHastaFiltroBT").attr("style","display: none");
    $("#tipo_transaccion_BT").empty();
    $("#transaccion_desde_BT").empty();

    $("#btnVolverPortafolio").addClass("oculto");

    $("#estado_cuenta_div_tabla_consulta_BT").empty();
    $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
//    $("#tagAccount_DatosCuenta_BT").attr("style","display: ");
    $( "#comentario_transaccion_BT ").val("");
    sendServiceJSON(url,param,BalancesAndTransactionInfoSuccess,null,null);
}


function BalancesAndTransactionInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var cuentasBT = result.cuentas;
    var periodo = result.transaccionesPeriodo;
    var codigo = result.codigo;
    idioma = result.idioma;

    if(codigo=="0" && cuentasBT!=null){
        if(idioma=="_us_en")
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BT", cuentasBT,"Select","-2");
        else
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BT", cuentasBT,"Seleccione","-2");

        if(periodo!=null)
            cargar_selectPersonal2("transaccion_desde_BT", periodo);

        $("#div_account_BalancesAndTransactions").attr("style","display: ");
        $("#noInfo_balancesAndTransactions_account").attr("style","display: none");
        if(idioma=="_us_en")
            $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked</span><span>:</span>");
        else
            $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Bloqueado</span><span>:</span>");
    }else{
        $("#div_account_BalancesAndTransactions").attr("style","display: none");
        $("#noInfo_balancesAndTransactions_account").attr("style","display: ");
    }
    $("#botonVolverASaldoCuentas").attr("style","display: none");
    BlockedAmountInfoPaginaJSONData();


}

function detalleSaldoTransJSONData(){
    var url = urlDetalleBalancesAndTransaction_BT;
    var param={};
    var jsonDetalleSaldoTrans=[];

    jsonDetalleSaldoTrans[0] =  $("#estado_cuenta_numero_cuenta_BT").get(0).value;

    param.jsonDetalleSaldoTrans=JSON.stringify({"parametros":jsonDetalleSaldoTrans});

    sendServiceJSON(url,param,detalleSaldoTransSuccess,null,null);
}


function detalleSaldoTransSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var  tipoTrans = result.tipoTransacciones;
    var mensaje = result.mensaje;
    var codigo = result.codigo;
    var numCuenta = result.numeroCuenta;

    if(codigo !="0"){
        $("#titular_BT").html(detalle.cliente);
        // $("#moneda_BT").html(detalle.moneda);
        $("#moneda_BT").html(createCurrency(detalle.moneda));
        if(detalle.bloqueado!="0,00"){
//            if(idioma=="_us_en")
//                $("#linkBloqueo_account").html("<a id='"+numCuenta+"' onclick='detalleBloqueoAccount(this.id);' style='cursor:pointer; text-decoration: underline' ><span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado' title='Click to see details of blocked amount'>Blocked Amount </span><span>:</span></a>");
//            else
//                $("#linkBloqueo_account").html("<a id='"+numCuenta+"' onclick='detalleBloqueoAccount(this.id);' style='cursor:pointer; text-decoration: underline'><span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'  title='Haga click para ver detalle del monto bloqueado'>Monto Bloqueado</span><span>:</span></a>");
            if(idioma=="_us_en")
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked</span><span>:</span>");
            else
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Bloqueado</span><span>:</span>");

        }else{
            if(idioma=="_us_en")
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked</span><span>:</span>");
            else
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Bloqueado</span><span>:</span>");
        }

        $("#bloqueado_BT").html(detalle.bloqueado);
        $("#diferido_BT").html(detalle.diferido);
        $("#disponible_BT").html(detalle.saldoDisp);
        $("#saldo_actual_BT").html(detalle.saldoActual);
//        if(idioma=="_us_en"){
//            $("#tagAccount_DatosCuenta_BT").attr("style","display: none");
//            $("#tagAccount_DatosCuenta_BT2").attr("style","display: ");
//            $("#tagAccount_fecha_BT").html(" "+"<b>"+detalle.fechaCierre+"</b>");
//        }
//        else{
        $("#tagAccount_DatosCuenta_BT").attr("style","display: none");
        $("#tagAccount_DatosCuenta_BT2").attr("style","display: ");
        $("#tagAccount_fecha_BT").html(" "+"<b>"+detalle.fechaCierre+"</b>");
//        }



        if(tipoTrans!=null){
            if(idioma=="_us_en")
                cargar_selectPersonal("tipo_transaccion_BT", tipoTrans,"All","-2");
            else
                cargar_selectPersonal("tipo_transaccion_BT", tipoTrans,"Todos","-2");

            realizarConsultaBT();
        }
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }





}

function realizarConsultaBT(){
    var mensaje="";
    $(".requeridoBT").each(function(){
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
    if($("#transaccion_desde_BT").val()=="-1")
    {
        $(".requeridoFechaBT").each(function(){
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

        if(!isDate($( "#fechaDesdeFiltroBT").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fechaDesdeFiltroBT").addClass("error_campo");
        }else if(!isDate($( "#fechaHastaFiltroBT").val())){
            if(idioma=="_us_en")
                $("#mensaje_error").html('To date is not correct');
            else
                $("#mensaje_error").html('La fecha Hasta no es correcta');
            $("#div_mensajes_error").fadeIn("slow");
            $("#fechaHastaFiltroBT").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fechaDesdeFiltroBT").val().split("/")[1]
                +"/"+$("#fechaDesdeFiltroBT").val().split("/")[0]
                +"/"+$("#fechaDesdeFiltroBT").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fechaHastaFiltroBT").val().split("/")[1]
                +"/"+$("#fechaHastaFiltroBT").val().split("/")[0]
                +"/"+$("#fechaHastaFiltroBT").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                $("#fechaDesdeFiltroBT").addClass("error_campo");
                $("#fechaHastaFiltroBT").addClass("error_campo");

            }else{
                $("#fechaDesdeFiltroBT").removeClass("error_campo");
                $("#fechaHastaFiltroBT").removeClass("error_campo");
            }
        }
    }

    if(mensaje!=""){
        $("#estado_cuenta_div_tabla_consulta_BT").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#div_mensajes_error").fadeOut("fast");
        $("#estado_cuenta_div_tabla_consulta_BT").fadeIn("fast");
        consultarCuentaSaldoTransJSONData();
        $("#balance_imprimir").fadeIn("fast");
    }
}

function consultarCuentaSaldoTransJSONData(){
    $("#div_carga_espera").removeClass("oculto")
    var url = urlConsultarCuentaBalancesAndTransaction_BT;
    var param={};
    var jsonDetalleSaldoTrans=[];

    jsonDetalleSaldoTrans[0] =  $("#estado_cuenta_numero_cuenta_BT").get(0).value;

    jsonDetalleSaldoTrans[1] = $("#transaccion_desde_BT").get(0).value;
    jsonDetalleSaldoTrans[2] = $("#fechaDesdeFiltroBT").get(0).value;
    jsonDetalleSaldoTrans[3] = $("#fechaHastaFiltroBT").get(0).value;

    jsonDetalleSaldoTrans[4] = $("#tipo_transaccion_BT").get(0).value;

    jsonDetalleSaldoTrans[5] = $("#comentario_transaccion_BT").get(0).value;

    param.jsonDetalleSaldoTrans=JSON.stringify({"parametros":jsonDetalleSaldoTrans});
    sendServiceJSON(url,param,consultarCuentaSaldoTransSuccess,null,null);
}

function guardar_comentarioBT(valor,comentario){
    var mensaje="";
    $(".ComentariorequeridoBT").each(function(){
        if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field - "+ vbtol_props[idioma]["BAT_Commentary"]+"<br>";
            else
                mensaje=mensaje+"Campo requerido - "+ vbtol_props[idioma]["BAT_Commentary"]+"<br>";

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
        $("#div_mensajes_error").fadeOut("fast");
        guardarComentarioJSONData(valor,comentario);
    }
}

function guardarComentarioJSONData(valor,comentario){
    var url = urlGuardarComentario_BT;
    var param={};
    var jsonComentarioTrans=[];
    jsonComentarioTrans[0] =  valor;
    jsonComentarioTrans[1] =  comentario;
    param.jsonComentarioTrans=JSON.stringify({"parametros":jsonComentarioTrans});
    sendServiceJSON(url,param,guardarComentarioSuccess,null,null);
}


function crearPDF_balancesAndTransactions(){
    var miValue = $("#estado_cuenta_numero_cuenta_BT" ).val();
    $("#estado_cuenta_numero_cuenta_BT option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#tipo_transaccion_BT" ).val();
    $("#tipo_transaccion_BT option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#transaccion_desde_BT" ).val();
    $("#transaccion_desde_BT option[value='"+miValue+"']").attr("selected",true);

    $('#estado_cuenta_numero_cuenta_BT_select').html($('#estado_cuenta_numero_cuenta_BT option:selected').text());

    // printPageElement('div_SALDOS_CUENTAS');  //Print EDO CUENTA

    $("#div_account_BalancesAndTransactions").printThis({
        importCSS: true,            // import parent page css
        importStyle: true,         // import style tags
        printContainer: true,
    });

}

function guardarComentarioSuccess(originalRequest){
    var result = originalRequest;
    var respuesta = result.respuesta;
    var mensaje = result.mensaje;
    console.log("respuesta"+respuesta);
    if(respuesta=="OK"){
        console.log("ok"+respuesta);
        realizarConsultaBT();
    }else{
        console.log("no ok"+respuesta);
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
}

function consultarCuentaSaldoTransSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center"},
        { "sClass": "center"},
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "right" },
        { "sClass": "right" }
    ];
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var mensaje = result.mensaje;
    var codigo = result.codigo;
    var tamano_pag=20;
    var tabla;
    var tabla_id="estado_cuenta_tabla_consulta_BT";
    var id_contenedor="estado_cuenta_div_tabla_consulta_BT";
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    $("#div_carga_espera").addClass("oculto")
    if(codigo!="0"){
        // tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
        // tabla=crearTabla_paginacionV2(tabla_id,columnas,datos,0,tamano_pag,"");
        crearTablaV2(tabla_id,columnas,datos,"",false);

/*        tabla.dataTable({
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
        });*/

        oTable = $("#"+tabla_id).dataTable({
            "iDisplayLength": tamano_pag,
            "bJQueryUI": false,
            "bFilter": false,
            "bSort": false,
            "bPaginate": true,
           "sPaginationType": "full_numbers",
            "bDestroy": true,
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });

        if(datos.length!=0){
/*
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
*/

        }
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}

function llamarDescrip_click(id){

    $("#label_Beneficiary").empty();
    $("#label_Banck").empty();
    $("#label_Observation").empty();

    $("#label_Beneficiary").html(id.split("|")[0]);
    $("#label_Banck").html(id.split("|")[1]);
    $("#label_Observation").html(id.split("|")[2]);

    $("#div_mensajes_info_desc_tabla").fadeIn("slow");


}

function llamarDescrip_out(img){


    $("#div_mensajes_info_desc_tabla").fadeOut("fast");
    $("#mensajes_info_desc_tabla").empty();

}



function imprimir_detalle_AccountBT(valor,tile){

    var urlreportaccountBT="reportaccountBT.action";

    var carteraBT=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[1]);
    var numCuentaBT=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);

    var fechaOperacionBT = Trim(valor.split("|")[0]) == "" ? "" : valor.split("|")[0];
    var mensajeBT = Trim(valor.split("|")[1]) == "" ? "" : valor.split("|")[1];

    var montoBT = Trim(valor.split("|")[2]) == "" ? "" : valor.split("|")[2];
    var monedaBT = Trim(valor.split("|")[3]) == "" ? "" : valor.split("|")[3];
    var fechaValorBT = Trim(valor.split("|")[4]) == "" ? "" : valor.split("|")[4];
    var tasaCambioBT = Trim(valor.split("|")[5]) == "" ? "" : valor.split("|")[5];
    var referenciaBT = Trim(valor.split("|")[6]) == "" ? "" : valor.split("|")[6];

    var beneficiarioBT = Trim(valor.split("|")[7]) == "" ? "" : valor.split("|")[7];
    var descripcionBT = Trim(valor.split("|")[8]) == "" ? "" : valor.split("|")[8];
    var observacionBT = Trim(valor.split("|")[9]) == "" ? "" : valor.split("|")[9];


    if (beneficiarioBT != "") {
        beneficiarioBT = $().crypt({method: "b64enc",source:beneficiarioBT});
    }
    tasaCambioBT = $().crypt({method: "b64enc",source:tasaCambioBT});
    if (descripcionBT != "") {
        descripcionBT = $().crypt({method: "b64enc",source:descripcionBT});
    }
    fechaOperacionBT = $().crypt({method: "b64enc",source:fechaOperacionBT});
    fechaValorBT = $().crypt({method: "b64enc",source:fechaValorBT});
    if (referenciaBT != "") {
        referenciaBT = $().crypt({method: "b64enc",source:referenciaBT});
    }
    mensajeBT = $().crypt({method: "b64enc",source:mensajeBT});
    montoBT = $().crypt({method: "b64enc",source:montoBT});
    numCuentaBT = $().crypt({method: "b64enc",source:numCuentaBT});
    carteraBT = $().crypt({method: "b64enc",source:carteraBT});
    monedaBT = $().crypt({method: "b64enc",source:monedaBT});
    if (observacionBT != "") {
        observacionBT = $().crypt({method: "b64enc",source:observacionBT});
    }

    tile = $().crypt({method: "b64enc",source:tile});

    urlreportaccountBT = urlreportaccountBT
        +"?beneficiario="+beneficiarioBT
        +"&tasadecambio="+tasaCambioBT
        +"&descripcion="+descripcionBT
        +"&referencia="+referenciaBT
        +"&fecha_operacion="+fechaOperacionBT
        +"&fechaValor="+fechaValorBT
        +"&monto="+montoBT
        +"&tipMov="+tile
        +"&num_cta="+numCuentaBT
        +"&mensaje="+mensajeBT
        +"&cartera="+carteraBT
        +"&moneda="+monedaBT
        +"&observacion="+observacionBT;

    window.open(urlreportaccountBT,'PDF','');
}




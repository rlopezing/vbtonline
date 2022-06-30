var urlColocacionesCargar_BT="TimeDeposits_cargarColocaciones.action";
var urlDetalleBalancesAndTransactionColocaciones_BT="TimeDeposits_consultarColocacionesSaldos.action";
var urlreportColOperation="reportColOperation.action";
var urlImprimirColocacionesSaldosTrans="TimeDeposits_ImprimirSaldosTrans.action";

var idioma="";
var oTable ="";
var noInfo="";

$(document).ready(function(){

    $("#colocaciones_numero_cuenta_BT").change(function(){
        cargarDetalleCuentaColocaciones(this.value);
    });

});

function cargarDetalleCuentaColocaciones(value){
    if(value!="-2")
        detalleSaldoTransColocacionesJSONData();
    else{
        if(idioma=="_us_en"){
            $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'>Blocked Amount </span><span>:</span>");
        }
        else {
            $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'>Monto Bloqueado </span><span>:</span>");
        }
        $("#tag_saldo_colocaciones").attr("style","display: ");
        $("#tag_saldo_colocaciones2").attr("style","display: none");
        $("#tag_saldo_colocacionesFecha").attr("style","display: none");

        $("#titular_BT_Colocaciones").html("");
        $("#montoApertura_BT_Colocaciones").html("");
        $("#moneda_BT_Colocaciones").html("");
        $("#montoBloqueado_BT_Colocaciones").html("");

        $("#fechaApertura_BT_Colocaciones").html("");
        $("#montoVencimiento_BT_Colocaciones").html("");
        $("#fechaVencimiento_BT_Colocaciones").html("");
        $("#tasa_BT_Colocaciones").html("");
        $("#div_tabla_consulta_BT_Colocaciones").empty();

    }
}

function BalancesAndTransactionColocacionesInfoPaginaJSONData(){
    var url = urlColocacionesCargar_BT;
    var param={};
    $("#div_tabla_consulta_BT_Colocaciones").empty();
    $("#tag_saldo_colocaciones").attr("style","display: ");
    $("#tag_saldo_colocaciones2").attr("style","display: none");
    $("#tag_saldo_colocacionesFecha").attr("style","display: none");
    $("#btnVolverPortafolioColocaciones").addClass("oculto");
    sendServiceJSON(url,param,BalancesAndTransactionColocacionesInfoSuccess,null,null);
}


function BalancesAndTransactionColocacionesInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var colocaciones = result.colocaciones;
    var codigo = result.codigo;
    idioma = result.idioma;

//    if(colocaciones == "" || colocaciones == null) {
//        $("#div_colocacionesSaldosTrans").fadeOut("fast");
//        $("#noPoseeColocacionesSaldosTrans").fadeIn("fast");
//    }else{
//        $("#div_colocacionesSaldosTrans").fadeIn("fast");
//        $("#noPoseeColocacionesSaldosTrans").fadeOut("fast");
//    }

    if(codigo=="0" && colocaciones != null){
        $("#div_colocacionesSaldosTrans").attr("style","display: ");
        $("#noPoseeColocacionesSaldosTrans").attr("style","display: none");
        $("#titular_BT_Colocaciones").html("");
        $("#montoApertura_BT_Colocaciones").html("");
        $("#moneda_BT_Colocaciones").html("");
        $("#montoBloqueado_BT_Colocaciones").html("");
        if(idioma=="_us_en")
            $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'>Blocked Amount </span><span>:</span>");
        else
            $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'>Monto Bloqueado </span><span>:</span>");
        $("#fechaApertura_BT_Colocaciones").html("");
        $("#montoVencimiento_BT_Colocaciones").html("");
        $("#fechaVencimiento_BT_Colocaciones").html("");
        $("#tasa_BT_Colocaciones").html("");

        if(colocaciones!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("colocaciones_numero_cuenta_BT", colocaciones,"Select","-2");
            else
                cargar_selectPersonal("colocaciones_numero_cuenta_BT", colocaciones,"Seleccione","-2");

        /*Carga en background la pantalla de bloqueos*/
        BlockedAmountsColocacionesInfoPaginaJSONData();
    }else{
        $("#div_colocacionesSaldosTrans").attr("style","display: none");
        $("#noPoseeColocacionesSaldosTrans").attr("style","display: ");
    }

}

function detalleSaldoTransColocacionesJSONData(){
    var url = urlDetalleBalancesAndTransactionColocaciones_BT;
    var param={};
    var jsonDetalleColocaciones=[];

    jsonDetalleColocaciones[0] =  $("#colocaciones_numero_cuenta_BT").get(0).value;

    param.jsonDetalleColocaciones=JSON.stringify({"parametros":jsonDetalleColocaciones});

    sendServiceJSON(url,param,detalleSaldoTransColocacionesSuccess,null,null);
}


function detalleSaldoTransColocacionesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var detalleTabla = result.contenidoTabla_infoDetalle;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var numCuenta = result.numeroCuenta;
    var mensaje = result.mensaje;
    var codigo = result.codigo;

    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    if(codigo=="0"){
        $("#titular_BT_Colocaciones").html(detalle.clientePrincipal);
        $("#montoApertura_BT_Colocaciones").html(detalle.montoApertura);
        $("#moneda_BT_Colocaciones").html(detalle.moneda);
        $("#montoBloqueado_BT_Colocaciones").html(detalle.bloqueado);
        if(idioma=="_us_en")
//            if(detalle.bloqueado=="0,00")
                $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'  title='Click to see details of blocked amount'>Blocked Amount </span><span>:</span>");
//            else
//                $("#linkBloqueo").html("<a id='"+numCuenta+"' onclick='detalleBloqueo(this.id);' style='cursor:pointer; text-decoration: underline'><span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'  title='Click to see details of blocked amount'>Blocked Amount </span><span>:</span></a>");
        else
//            if(detalle.bloqueado=="0,00")
                $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado' title='Haga click para ver detalle del monto bloqueado'>Monto Bloqueado</span><span>:</span>");
//            else
//                $("#linkBloqueo").html("<a id='"+numCuenta+"' onclick='detalleBloqueo(this.id);' style='cursor:pointer; text-decoration: underline' ><span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado' title='Haga click para ver detalle del monto bloqueado'>Monto Bloqueado</span><span>:</span></a>");
        $("#fechaApertura_BT_Colocaciones").html(detalle.fechaApertura);
        $("#montoVencimiento_BT_Colocaciones").html(detalle.montoVencimiento);
        $("#fechaVencimiento_BT_Colocaciones").html(detalle.fechaVencimiento);
        $("#tasa_BT_Colocaciones").html(detalle.tasa);

        $("#tag_saldo_colocaciones").attr("style","display: none");
        $("#tag_saldo_colocaciones2").attr("style","display: ");
        $("#tag_saldo_colocacionesFecha").attr("style","display: ");
        $("#tag_saldo_colocacionesFecha").html(" <b>"+detalle.fechaCierre+"</b>");


        $("#div_tabla_consulta_BT_Colocaciones").empty();
//    crearTabla('div_tabla_consulta_BT_Colocaciones','tabla_consulta_BT_Colocaciones',columnas,datos);
//    crearTablaDesc('div_tabla_consulta_BT_Colocaciones','tabla_consulta_BT_Colocaciones',columnas,datos,detalleTabla);
        crearTabla('div_tabla_consulta_BT_Colocaciones','tabla_consulta_BT_Colocaciones',columnas,datos);
//
//
        oTable = $('#tabla_consulta_BT_Colocaciones').dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center" },
                { "sClass": "center"},
                {  "sClass": "" },
                {  "sClass": "center" },
                {  "sClass": "right" },
                { "sClass": "right" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }

}

//function llamarDescrip_TD_BT_click(img){
//
//    $("#label_Beneficiary_TDBT").empty();
//    $("#label_Bank_TDBT").empty();
//    $("#label_Observation_TDBT").empty();
//    $("#label_Account_TDBT").empty();
//    $("#label_Currency_TDBT").empty();
//
//    $("#label_Beneficiary_TDBT").html(img.split("|")[0]);
//    $("#label_Account_TDBT").html(img.split("|")[1]);
//    $("#label_Bank_TDBT").html("");
//    $("#label_Currency_TDBT").html(img.split("|")[2]);
//    $("#label_Observation_TDBT").html(img.split("|")[3]);
//
//    $("#div_mensajes_info_desc_tabla_TDBT").fadeIn("slow");
//
//
//}
//
//function llamarDescrip_TD_BT_out(img){
//
//    $("#div_mensajes_info_desc_tabla_TDBT").fadeOut("fast");
//    $("#mensajes_info_desc_tabla_TDBT").empty();
//
//}



   function imprimir_detalle(valor){
       var beneficiario;
       var num_cuenta;
       var moneda;
       var observaciones;
       var fecha_operacion;
       var fecha_valor;
       var descripcion;
       var referencia;
       var tasa_cambio;
       var monto;
       var tasa_interes;
       if(Trim(valor.split("|")[0])!=""){beneficiario=valor.split("|")[0];}else{beneficiario="_";}
       if(Trim(valor.split("|")[1])!=""){num_cuenta=valor.split("|")[1];}else{num_cuenta="_";}
       if(Trim(valor.split("|")[2])!=""){moneda=valor.split("|")[2];}else{moneda="_";}
       if(Trim(valor.split("|")[3])!=""){observaciones=valor.split("|")[3];}else{observaciones="_";}
       if(Trim(valor.split("|")[4])!=""){fecha_operacion=valor.split("|")[4];}else{fecha_operacion="_";}
       if(Trim(valor.split("|")[5])!=""){fecha_valor=valor.split("|")[5];}else{fecha_valor="_";}
       if(Trim(valor.split("|")[6])!=""){descripcion=valor.split("|")[6];}else{descripcion="_";}
       if(Trim(valor.split("|")[7])!=""){referencia=valor.split("|")[7];}else{referencia="_";}
       if(Trim(valor.split("|")[8])!=""){tasa_cambio=valor.split("|")[8];}else{tasa_cambio="_";}
       if(Trim(valor.split("|")[9])!=""){monto=valor.split("|")[9];}else{monto="_";}
       if(Trim(valor.split("|")[10])!=""){tasa_interes=valor.split("|")[10];}else{tasa_interes="_";}

       var banco="_";

       crearPDF_balancesAndTransactionTD_account(fecha_operacion,
                                                          fecha_valor,
                                                          descripcion,
                                                          referencia,
                                                          tasa_interes,
                                                          monto,
                                                          beneficiario,
                                                          num_cuenta,
                                                          banco,
                                                          observaciones,
                                                          moneda,
                                                          tasa_cambio );
   }




function print_colocacionesSaldosTrans(){
    url =  urlImprimirColocacionesSaldosTrans;
    var param={};
    var jsonDetalleColocaciones=[];

    jsonDetalleColocaciones[0] =  $("#colocaciones_numero_cuenta_BT").get(0).value;

    param.jsonDetalleColocaciones=JSON.stringify({"parametros":jsonDetalleColocaciones});

    sendServiceJSON(url,param,null,null,null);
    var miValue = $("#colocaciones_numero_cuenta_BT" ).val();
    $('#colocaciones_numero_cuenta_BT_select').html($('#colocaciones_numero_cuenta_BT option:selected').text());
    $("#colocaciones_numero_cuenta_BT option[value='"+miValue+"']").attr("selected",true);

    printPageElement('div_SALDOS_COLOCACIONES');  //Print EDO CUENTA




}

function abrirDetalleTabla_DetailsTimeDeposit(elemento){
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
        oTable.fnOpen( nTr, fnFormatDetailsTimeDeposit($(elemento).attr("id")), 'details');
    }

};


function fnFormatDetailsTimeDeposit (valor)  {
    var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficiario">Beneficiary </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentassaldos_TAGCuentaNumero">Account </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBanco">Bank </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT"></span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Currency </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.split("|")[2]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasbloqueos_TAGObservacion">Observation </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Observation_TDBT">'+valor.split("|")[3]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"> <img style="cursor:pointer" onclick="imprimir_detalle(\''+valor+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" > </td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficiario">Beneficiario </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentassaldos_TAGCuentaNumero">Cuenta Nro </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBanco">Banco </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT"></span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Moneda </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.split("|")[2]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasbloqueos_TAGObservacion">Observaciones </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Observation_TDBT">'+valor.split("|")[3]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"> <img style="cursor:pointer" onclick="imprimir_detalle(\''+valor+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" > </td>';
        sOut +='</tr>';
        sOut +='</table>';

    }



    return sOut;
}

function crearPDF_balancesAndTransactionTD_account(fecha_operacion,fecha_valor,descripcion,referencia,tasa_interes,monto,beneficiario,numero_cuenta,banco,observacion,moneda,tasa_cambio ){

    var fechavencimiento = $().crypt({method: "b64enc",source:$("#fechaVencimiento_BT_Colocaciones").html()});
    var colocacion = $().crypt({method: "b64enc",source:$("#colocaciones_numero_cuenta_BT").val().split("|")[0]});
    fecha_operacion = $().crypt({method: "b64enc",source:fecha_operacion});
    fecha_valor = $().crypt({method: "b64enc",source:fecha_valor});
    descripcion = $().crypt({method: "b64enc",source:descripcion});
    referencia = $().crypt({method: "b64enc",source:referencia});
    tasa_interes = $().crypt({method: "b64enc",source:tasa_interes});
    monto = $().crypt({method: "b64enc",source:monto});
    beneficiario = $().crypt({method: "b64enc",source:beneficiario});
    numero_cuenta = $().crypt({method: "b64enc",source:numero_cuenta});
    banco = $().crypt({method: "b64enc",source:banco});
    observacion = $().crypt({method: "b64enc",source:observacion});
    moneda = $().crypt({method: "b64enc",source:moneda});
    tasa_cambio = $().crypt({method: "b64enc",source:tasa_cambio});


    urlreportColOperation="reportColOperation.action";
    urlreportColOperation = urlreportColOperation + "?fechavencimiento="+fechavencimiento
        +"&fecha_operacion="+fecha_operacion
        +"&fechaValor="+fecha_valor
        +"&descripcion="+descripcion
        +"&referencia="+referencia
        +"&tasainteres="+tasa_interes
        +"&monto="+monto
        +"&beneficiario="+beneficiario
        +"&num_cta="+numero_cuenta
        +"&banco="+banco
        +"&observacion="+observacion
        +"&moneda="+moneda
        +"&tasadecambio="+tasa_cambio
        +"&colocacion="+colocacion;

    window.open(urlreportColOperation,'PDF','');
}
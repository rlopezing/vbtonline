var urlCargarCuentasPago="Transfers_cargarCuentasPagos.action";
var urlCargarDatosPago="CreditCards_cargarDatosPagosTDC.action";
var urlGuardarPagoTDC="CreditCards_guardarPagoTDC.action";
var urlCargar_TDC_Pagos="CreditCards_cargarTdcPagos.action";

var opcBusquedaPagoTDC="OK";

$(document).ready(function() {

    $("#numero_cuenta_TDC_Pago").chosen({ search_contains: true });
//    $("#numero_cuenta_pago").chosen({ search_contains: true });
//    $("#tipoPagoTDC").chosen({ search_contains: true });


//    $("#fec_desde_Pago").datepicker({
//        dateFormat: "dd/mm/yy",
//        changeMonth: true,
//        gotoCurrent: true,
//        defaultDate: 0,
//        beforeShowDay: editDays,
//        changeYear: true   ,
//        onClose: function (selectedDate) {
//            $("#fec_hasta_Pago").datepicker("option", "minDate", selectedDate);
//        }
//
//    });
//
//
//    $("#fec_hasta_Pago").datepicker({
//        dateFormat: "dd/mm/yy",
//        changeMonth: true,
//        beforeShowDay: editDays,
//        changeYear: true
//    });


    $("#clear_pagos").click(function(){
        $("#numero_cuenta_TDC_Pago").val("-2");
        $("#numero_cuenta_TDC_Pago").trigger("chosen:updated");
//        $("#fec_desde_Pago").val("");
//        $("#fec_hasta_Pago").val("");
        $("#tag_cliente_bloqueo_pago").text("");
        $("#pay_card").addClass("oculto");
        //$("#pay_card").fadeOut("fast");
//        $("#fec_desde_Pago").val(fechaMesAnterior);
//        $("#fec_hasta_Pago").val(fechaHoy);
//        $('#tabla_consulta_historial_TDC_Pagos').dataTable().fnClearTable();
       // $('#tabla_consulta_TDC_Pagos_movimientos').dataTable().fnClearTable();

    });


//    $("#consultar_pagos").click(function(){
//        var mensaje="";
//        $("#filtroPagosTDC .requeridoConsultarPagos").each(function(){
//            if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
//                if(idioma=="_us_en")
//                    mensaje=mensaje+"Required field - "+ this.title+"<br>";
//                else
//                    mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
//
//                $("#"+this.id).addClass("error_campo");
//            }else{
//                if($("#"+this.id).hasClass("error_campo"))
//                    $("#"+this.id).removeClass("error_campo");
//            }
//        });
//
//
//        if(!isDate($( "#fec_desde_Pago").val())){
//            if(idioma=="_us_en")
//                mensaje=mensaje+"From date is not correct"+"<br>";
//            else
//                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
//            $("#fec_desde_CL").addClass("error_campo");
//        }else if(!isDate($( "#fec_hasta_Pago").val())){
//            if(idioma=="_us_en")
//                mensaje=mensaje+"To date is not correct";
//            else
//                mensaje=mensaje+"La fecha Hasta no es correcta";
//            $("#fec_hasta_Pago").addClass("error_campo");
//        }else{
//            var fdesdeFormat= $("#fec_desde_Pago").val().split("/")[1]
//                +"/"+$("#fec_desde_Pago").val().split("/")[0]
//                +"/"+$("#fec_desde_Pago").val().split("/")[2]  ;
//            var Fdesde = Date.parse(fdesdeFormat);
//
//            var fhastaFormat= $("#fec_hasta_Pago").val().split("/")[1]
//                +"/"+$("#fec_hasta_Pago").val().split("/")[0]
//                +"/"+$("#fec_hasta_Pago").val().split("/")[2] ;
//            var Fhasta = Date.parse(fhastaFormat);
//
//            if(Fdesde>Fhasta){
//                if(idioma=="_us_en")
//                    mensaje=mensaje+"The date range is not allowed. Remember: The From and To dates should not be the same. From date must be less than the To date."+"<br>";
//                else
//                    mensaje=mensaje+"El rango de fecha no es permitido. Recuerde: La fecha Desde y Hasta no deben ser iguales. La fecha Desde debe ser menor a la fecha Hasta."+"<br>";
//                $("#fec_desde_Pago").addClass("error_campo");
//                $("#fec_hasta_Pago").addClass("error_campo");
//
//            }else{
//                $("#fec_desde_Pago").removeClass("error_campo");
//                $("#fec_hasta_Pago").removeClass("error_campo");
//            }
//        }
//
//        if(mensaje!=""){
//            $("#mensaje_error").empty();
//            $("#mensaje_error").html(mensaje);
//            $("#div_mensajes_error").fadeIn("slow");
//        }else{
//            $("#div_mensajes_error").fadeOut("fast");
//            $("#div_carga_espera").removeClass("oculto");
//            buscarHistoricoPagos();
//            cargarCuentasPagosJSONData();
//
//
//        }
//
//    });

    //cambio miguel
    $("#pagos_actual_2").click(function(){
        var mensaje="";

       if($("#tipoPagoTDC").val()=="-2"){
           if(idioma=="_us_en")
               mensaje+="Required field - Payment option <BR>";
           else
               mensaje+="Campo requerido - Tipo de Pago <BR>";
           $("#tipoPagoTDC").addClass("error_campo");
       }else{
           if($("#tipoPagoTDC").hasClass("error_campo"))
               $("#tipoPagoTDC").removeClass("error_campo");
       }


        if($("#tipoPagoTDC").val()=="OM"){

            if($("#otroMontoPagoTDC").val()==0){
                if(idioma=="_us_en")
                    mensaje+="Required field - Amount <BR>";
                else
                    mensaje+="Campo requerido - Monto <BR>";
                 $("#otroMontoPagoTDC").addClass("error_campo");
            }
            if($("#otroMontoPagoTDC").val()==""){
                if(idioma=="_us_en")
                    mensaje+="Required field - Amount <BR>";
                else
                    mensaje+="Campo requerido -  Monto <BR>";
                $("#otroMontoPagoTDC").addClass("error_campo");
            }

        }else{
            if($("#otroMontoPagoTDC").hasClass("error_campo"))
                $("#otroMontoPagoTDC").removeClass("error_campo");
        }

        if($("#numero_cuenta_pago").val()=="-2"){
            if(idioma=="_us_en")
                mensaje+="Required field -  Account <BR>";
            else
                mensaje+="Campo requerido - Cuenta <BR>";
            $("#numero_cuenta_pago").addClass("error_campo");
        } else{
            var saldoDisponibleCta = Number(unFormatCurrency($('#numero_cuenta_pago :selected').attr("extra")));
            if($("#numero_cuenta_pago").hasClass("error_campo"))
                $("#numero_cuenta_pago").removeClass("error_campo");
        }


        var montoPagoVal="";
        var montoSaldoVal="";


        if ($("#otroMontoPagoTDC").val().length>5){
            montoPagoVal= ($("#otroMontoPagoTDC").val()).replace(/\,/g,'');
          //  montoPagoVal= (montoPagoVal).replace(/\,/g,'.');
        }else{
            montoPagoVal= $("#otroMontoPagoTDC").val();
        }


        if ($("#tdcpagos_TAGSaldoActual_datos").text().replace("-","").length>5){
            montoSaldoVal= ($("#tdcpagos_TAGSaldoActual_datos").text().replace("-","")).replace(/\,/g,'');
            //montoSaldoVal= (montoSaldoVal).replace(/\,/g,'.');
        }else{
            montoSaldoVal= $("#otroMontoPagoTDC").val();
        }



        if(parseFloat($("#otroMontoPagoTDC").val())=="0" || parseFloat($("#otroMontoPagoTDC").val())=="0,00" || parseFloat(montoSaldoVal)<0){
            if(idioma=="_us_en")
                mensaje+="Amount must be greater than zero";
            else
                mensaje+="El monto debe ser mayor a cero";

            $("#otroMontoPagoTDC").addClass("error_campo");
        }else{
            /**
             * Validación del saldo mínimo en cuenta
             */
            if((saldoDisponibleCta - montoPagoVal < parametros.minimun_balance)) {
                if(idioma=="_us_en"){
                    mensaje = mensaje + "The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                }
                else {
                    mensaje = mensaje + "Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                }
                $("#otroMontoPagoTDC").addClass("error_campo");
            }
            else {
                if($("#otroMontoPagoTDC").hasClass("error_campo"))
                    $("#otroMontoPagoTDC").removeClass("error_campo");
            }

            //var saldoDisponibleCta = Trim($("#numero_cuenta_pago").val().split("|")[2]);
            //saldoDisponibleCuenta= (saldoDisponibleCuenta).replace(/\,/g,'.');
            //saldoDisponibleCuenta= ($("#numero_cuenta_pago").text().replace("-","")).replace(/\,/g,'');
            //var montoAux="";

            //if ($("#otroMontoPagoTDC").val().length>5){
            //    montoAux= ($("#otroMontoPagoTDC").val()).replace(/\,/g,'');
            //}else{
            //    montoAux= $("#otroMontoPagoTDC").val();
            //}


          /*  if (saldoDisponibleCta - montoAux>=parametros.minimun_balance){

                if($("#otroMontoPagoTDC").hasClass("error_campo"))
                    $("#otroMontoPagoTDC").removeClass("error_campo");

            }else{
                if(idioma=="_us_en")
                    mensaje+=vbtol_props[idioma]["saldo_minimo_disponible_msg"] +" "+ parametros.minimun_balance+ "</BR>";
                else
                    mensaje+=vbtol_props[idioma]["saldo_minimo_disponible_msg"] +" "+ parametros.minimun_balance+ "</BR>";

                $("#otroMontoPagoTDC").addClass("error_campo");
            }  */

        }

        if(parseFloat(montoPagoVal)>parseFloat(montoSaldoVal)){
            if(idioma=="_us_en")
                mensaje+="Amount must be less than or equal to the current balance";
            else
                mensaje+="El monto debe ser menor o igual al saldo actual";

            $("#otroMontoPagoTDC").addClass("error_campo");
        }else{
            if($("#otroMontoPagoTDC").hasClass("error_campo"))
                $("#otroMontoPagoTDC").removeClass("error_campo");
        }




        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            if ($("#tipo_contrato_app").val()=="FC"){
                /**
                 *  Si es usuario de firmas conjuntas no se le piden los métodos de validación.
                 *  Date: 26/08/2016
                 */
                if(idioma=="_us_en"){
                    if (confirm('Are you sure you want to pay credit card?')) {
                        guardarPagoTdc();
                    }
                } else {
                    if (confirm('¿Est\u00e1 seguro que desea realizar el pago?')) {
                        guardarPagoTdc();
                    }
                }
            }
            else {
                //Realiza las validaciones
                mainValidationMethods("paymentCard");
            }
        }
    });


  /*  var oTableHMovimientosPagosTdc = $('#tabla_consulta_TDC_Pagos_movimientos').dataTable({
        "iDisplayLength": 5,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bDestry": true,
        "bSort": false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
            { "sClass": "right"},
            { "sClass": "center" },
            { "sClass": "center"}
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });  */

//    var oTableHistorialPagosTDC2 = $('#tabla_consulta_historial_TDC_Pagos').dataTable({
//        "iDisplayLength": 7,
//        "bJQueryUI": true,
//        "sPaginationType": "full_numbers",
//        "bFilter": false,
//        "bSort": false,
//        "aoColumns": [
//            { "sClass": "center" },
//            { "sClass": "center" },
//            { "sClass": "center" },
//            { "sClass": "center" },
//            { "sClass": "right" },
//            { "sClass": "center" },
//            { "sClass": "center" }
//        ],
//        "oLanguage": {
//            "sZeroRecords": noInfo,
//            "sInfo": "",
//            "sInfoEmpty": ""
//        }
//    });



});


function cargarPagoTarjetasPaginaJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlCargar_TDC_Pagos;
    var param={};

    sendServiceJSON(url,param,cargarPagoTarjetasSuccess,null,null);
}


function cargarPagoTarjetasSuccess(originalRequest){
    var result = originalRequest;
    var tarjetasCL = result.tarjetasCL;
    var codigo = result.codigo;
    idioma = result.idioma;

    opcBusquedaPagoTDC="OK"
//    $("#fec_desde_Pago").val(fechaMesAnterior);
//    $("#fec_hasta_Pago").val(fechaHoy);

    $("#clear_pagos").click();
    $('#tdcpagos_TAGLimiteCredito_datos').html("");
    $('#tdcpagos_TAGCreditoDisp_datos').html("");
    $('#tdcpagos_TAGFechaFact_datos').html("");
    $('#tdcpagos_TAGFechaPagAntes_datos').html("");
    $('#tdcpagos_TAGSaldoActual_datos').html("");
    $('#tdcpagos_TAGPagoTotal_datos').html("");
    $('#tdcpagos_TAGPagoMin_datos').html("");


    //$("#pay_card").fadeOut("fast");
  //  $('#tabla_consulta_TDC_Pagos_movimientos').dataTable().fnClearTable();
//    $('#tabla_consulta_historial_TDC_Pagos').dataTable().fnClearTable();


    $("#div_carga_espera").addClass("oculto");
    if(codigo=="0" && tarjetasCL!=null){

        if(idioma=="_us_en"){
            cargar_selectPersonal("numero_cuenta_TDC_Pago", tarjetasCL,"Select","-2");

        }else{
            cargar_selectPersonal("numero_cuenta_TDC_Pago", tarjetasCL,"Seleccione","-2");

        }
        //cargarCuentasPagosJSONData();
        $("#numero_cuenta_TDC_Pago").val("-2");
        $('#numero_cuenta_TDC_Pago').trigger("chosen:updated");

        //crearTabla_i18n('div_tabla_consulta_historial_TDC_Pagos', 'tabla_consulta_historial_TDC_Pagos', columnasActivas, filasActivas);

    }

    $("#numero_cuenta_TDC_Pago").chosen({ search_contains: true });
    //$("#pay_card").fadeOut("fast");
    $("#tag_cliente_bloqueo_pago").text("");
    $("#numero_cuenta_TDC_Pago").val("-2");
}


function cargarHistorialPagos2(value){
    $('#tr_facturado').removeClass("oculto");
    $('#tr_minimo').removeClass("oculto");
    opcBusquedaPagoTDC="OK";
    if(value!="-2") {
      //  $("#set_tabla_consulta_reglas_activas_TDC_CL").addClass("oculto");
       // consultarEstatusTDCCLJSONData(value);
        $("#otroMontoPagoTDCTR").addClass("oculto");
        $("#tag_cliente_bloqueo_pago").text($("#numero_cuenta_TDC_Pago").val().split("_")[3]);
        //$("#pay_card").fadeIn("slow");
        $("#pay_card").removeClass("oculto");
        //$("#consultar_pagos").click();
        buscarDatosPagos();
        cargarCuentasPagosJSONData();

    }else{
        $("#clear_pagos").click();
        //$("#pay_card").fadeOut("fast");
    }

    opcBusquedaPagoTDC="NO OK";
}





function cargarCuentasPagosJSONData(){
    $("#div_carga_espera2").removeClass("oculto");
    var url = urlCargarCuentasPago;
    var param={};
    var jsonTransfers=[];


    jsonTransfers[0] = $("#numero_cuenta_TDC_Pago").val().split("_")[0];


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,cargarCuentasPagosJSONDataSuccess,null,null);
}


function cargarCuentasPagosJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var cuentas = result.cuentasTOB;
    var respuesta = result.respuesta;
    var tipoPago=result.tipoPagoTDC;
    idioma = result.idioma;

    $("#div_carga_espera2").addClass("oculto");
    if(respuesta=="OK"){

        if(idioma=="_us_en"){
            //cargar_selectPersonalId("numero_cuenta_pago", cuentas,"Select","-2");
            cargar_selectCuenta("numero_cuenta_pago", cuentas,"Select","-2",'-2','0');
            cargar_selectPersonalId("tipoPagoTDC", tipoPago,"Select","-2");


        }else{
            //cargar_selectPersonalId("numero_cuenta_pago", cuentas,"Seleccione","-2");
            cargar_selectCuenta("numero_cuenta_pago", cuentas,"Select","-2",'-2', '0');
            cargar_selectPersonalId("tipoPagoTDC", tipoPago,"Seleccione","-2");

        }
        //Elimina el pago minimo en las tarjetas autorizadas
        if($("#numero_cuenta_TDC_Pago").val().split("_")[5] == 'A') {
            $("#tipoPagoTDC option[value='PM']").remove();
        }

        //Elimina las que no son en dolares
        $("#numero_cuenta_pago option").each(function(){
            var texto=$(this).text();
            if ((texto.indexOf("USD") < 0)&&(texto!="Select")){
                $(this).remove();
            }
        });
        $("#numero_cuenta_pago").val("-2");
        $('#numero_cuenta_pago').trigger("chosen:updated");
        $("#tipoPagoTDC").val("-2");
        $('#tipoPagoTDC').trigger("chosen:updated");
    }else{
        alert("Error unexpected");
    }

}


function buscarDatosPagos(){
    $("#div_carga_espera").removeClass("oculto");

    var url = urlCargarDatosPago;
    var param={}
    var jsonCreditCards=[];

    jsonCreditCards[0] = $("#numero_cuenta_TDC_Pago").val();
    jsonCreditCards[1] = opcBusquedaPagoTDC;

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    // sendServiceJSON_sinc(url,param,buscarDatosPagosJSONDataSuccess,null,null);
    sendServiceJSON(url,param,buscarDatosPagosJSONDataSuccess,null,null);
}


function buscarDatosPagosJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var respuesta = result.respuesta;
    var detalles = result.detalles;

    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto");
    if(result.mensaje=="NO OK"){
        var mensaje="";
        //$("#pay_card").fadeOut("fast");
        $("#pay_card").addClass("oculto");
        $("#mensaje_error").empty();
        if(idioma=="_us_en"){
            mensaje="Payment service is currently not available, please contact your Financial Advisor or Account Executive.";
        //mensaje="Payment service is currently not available, please try again later";
        }else{
            mensaje="El servicio de pagos no se encuentra disponible, por favor contacte a su Asesor o Ejecutivo.";
            //mensaje="El servicio de pagos no se encuentra disponible, por favor intente mas tarde";
        }
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{

        if(result.mensaje!="NO OK 2"){

            $('#tdcpagos_TAGSaldoActual_datos').html(detalles[0]);
            $('#tdcpagos_TAGMontoBloqueado_datos').html(detalles[1]);
            $('#tdcpagos_TAGMontoDisponible_datos').html(detalles[2]);

            if($("#numero_cuenta_TDC_Pago").val().split("_")[5] == 'P') {
                $('#tdcpagos_TAGPagoMin_datos').html(detalles[3]);
                $('#tdcpagos_TAGSaldoFacturado_datos').html(detalles[4]);
                $('#tdcpagos_TAGFechaSaldoTotal').html(detalles[5]);
            }
            else {
                $('#tr_facturado').addClass("oculto");
                $('#tr_minimo').addClass("oculto");
            }

            //$("#pay_card").fadeIn("slow");
            $("#pay_card").removeClass("oculto");
        }

    }
}

function validarTipoPagoTDC(id){
    if ($("#"+id).val()=="OM"){
        $("#otroMontoPagoTDC").val("");
        $("#otroMontoPagoTDCTR").removeClass("oculto");
        $("#otroMontoPagoTDC").val("0");
    }else{
        $("#otroMontoPagoTDCTR").addClass("oculto");
        if ($("#"+id).val()=="PM"){
            $("#otroMontoPagoTDC").val($("#tdcpagos_TAGPagoMin_datos").text());
        }
        if ($("#"+id).val()=="SA"){
            $("#otroMontoPagoTDC").val($("#tdcpagos_TAGSaldoActual_datos").text().replace("-",""));
        }

    }
}


function guardarPagoTdc(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlGuardarPagoTDC;
    var param={}
    var jsonCreditCards=[];

    var montoAux="";

        if ($("#otroMontoPagoTDC").val().length>5){
            montoAux= ($("#otroMontoPagoTDC").val()).replace(/\,/g,'');
        }else{
            montoAux= $("#otroMontoPagoTDC").val();
        }

    jsonCreditCards[0] = $("#numero_cuenta_TDC_Pago").val().split("_")[1];
    jsonCreditCards[1] = $("#numero_cuenta_TDC_Pago").val().split("_")[0];
    jsonCreditCards[2] = $("#numero_cuenta_TDC_Pago").val().split("_")[2];
    jsonCreditCards[3] = $("#tipoPagoTDC").val();
    jsonCreditCards[4] = montoAux;
    jsonCreditCards[5] = Trim($("#numero_cuenta_pago").val().split("|")[0]);
    jsonCreditCards[6] = $("#tdcpagos_TAGSaldoActual_datos").text();

    param.jsonCreditCards=JSON.stringify({"parametros":jsonCreditCards});


    sendServiceJSON_sinc(url,param,guardarPagoTdcJSONDataSuccess,null,null);
}


function guardarPagoTdcJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var respuesta = result.mensaje;

    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="ERROR_EMAIL") {
        if(idioma=="_us_en")
            popupAlert("<span id='tag_popup_add'>Payment instructions has been successfully received but we couldn't send notifications emails.</span>", "Accept");
        else
            popupAlert("<span id='tag_popup_add'>La instrucción de pago ha sido cargada exitosamente pero no hemos podido enviar los correos electrónicos.</span>", "Aceptar");

        $("#tipoPagoTDC").val("-2");
        $("#otroMontoPagoTDC").val("0");
        $("#numero_cuenta_pago").val("-2");

        cargarHistorialPagos2($("#numero_cuenta_TDC_Pago").val());
        $("#clear_pagos").click();
    }
    else if(respuesta=="TAGmsg_PP_saldoMinEnCuenta") {

        if(idioma=="_us_en")
            popupAlert("<span id='tag_popup_add'>The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "</span>", "Accept");
        else
            popupAlert("<span id='tag_popup_add'>Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + "</span>", "Aceptar");

        $("#tipoPagoTDC").val("-2");
        $("#otroMontoPagoTDC").val("0");
        $("#numero_cuenta_pago").val("-2");

        cargarHistorialPagos2($("#numero_cuenta_TDC_Pago").val());
        $("#clear_pagos").click();

    }
    else if(respuesta=="OK"){
        $("#tipoPagoTDC").val("-2");
        $("#otroMontoPagoTDC").val("0");
        $("#numero_cuenta_pago").val("-2");

        cargarHistorialPagos2($("#numero_cuenta_TDC_Pago").val());
        if (idioma == "_ve_es") {
            popupAlert("<span id='tag_popup_add'>La instrucción de pago ha sido cargada exitosamente.</span>", "Aceptar");
        } else {
            popupAlert("<span id='tag_popup_add'>Payment instructions has been successfully received.</span>", "Accept");
        }
        $("#clear_pagos").click();
    }else{
        $("#mensaje_error").empty();


        if (idioma == "_ve_es") {

            $("#mensaje_error").html("La instrucción de pago no pudo ser registrado, por favor intente de nuevo. Si el error persiste contacte a su ejecutivo o asesor..");

        }else{
            $("#mensaje_error").html("Payment instructions cannot be registered, please try again. If the error persists contact your executive or advisor");
        }


        $("#div_mensajes_error").fadeIn("slow");
    }



}

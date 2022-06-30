var urlTransfersToApprove="Transfers_transferenciasPorAprobar.action";
var urlsaveTransfersToApprove="Transfers_cambiarEstatusTransferenciaAprobar.action";
var urlconsultarDetalleFC="Transfers_detalle_transferencia.action";
var urlValidarClaveAprobar="Transfers_validarClaveTransferenciasAprobar.action";
var urlAprobarGenerarClaveTOB="Security_crearClaveRamdom.action";
var urlsaveTransfersToReject="Transfers_cambiarEstatusTransferenciaRechazar.action";
var urlParametrosPersonalesAPRV="DesingBank_cargarParametrosPersonales.action";

//var grupoSeleccionado;
 var grupoID="";
var opcionID="";
var idioma="";
var noInfo="";
var parametros="";


$(document).ready(function(){

    $("#aprobarTransferencias").click(function(){
        var numReferencia ="";
        var monto="";
        var mensaje="";
        var accionesCero="";
        $(".verificarSelecccionTrans").each(function(){
            var ok="OK";
            if($("#div_"+this.id).hasClass("error_campo"))
                $("#div_"+this.id).removeClass("error_campo");
            if($("#"+this.id).val()=="1"){


                if (this.name.split("|")[8]=="TEO"){
                    if(idioma == "_us_en"){
                        if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))){
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[3]+" Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                            ok="NO OK";
                        }
                        if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[3]+" The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                            ok="NO OK";
                        }
                    }else{
                        if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))){
                            mensaje = mensaje +  "Número de Referencia:"+this.name.split("|")[3]+" Monto no permitido. Por favor ingrese un monto mayor o igual a (Min. USD "+parametros.ex_mminto+")."+"<br>" ;
                            ok="NO OK";
                        }
                        if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                            mensaje = mensaje +  "Número de Referencia:"+this.name.split("|")[3]+" Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.ex_mmto+")."+"<br>" ;
                            ok="NO OK";
                        }
                    }
                }else{
                    if (this.name.split("|")[8]=="CID"){
                        if(idioma == "_us_en"){
                            if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))){
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[3]+" Invalid amount. Please enter an Amount equal or greater of "+parametros.vbt_mminto+"."+"<br>";
                                ok="NO OK";
                            }
                            if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[3]+" The amount exceeds the maximum of USD "+parametros.vbt_mmto+"."+"<br>";
                                ok="NO OK";
                            }
                        }else{
                            if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))){
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[3]+" Monto no permitido. Por favor ingrese un monto mayor o igual a (Min. USD "+parametros.vbt_mminto+")."+"<br>" ;
                                ok="NO OK";
                            }
                            if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                                mensaje = mensaje +  "Número de Referencia:"+this.name.split("|")[3]+" Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.vbt_mmto+")."+"<br>" ;
                                ok="NO OK";
                            }
                        }
                    }
                }
                 if(ok=="OK"){
                       if (numReferencia!="")
                            numReferencia = numReferencia + ",";
                       numReferencia = numReferencia + "'" + this.id + "'";
                     if (monto!="")
                         monto = monto + ",";

                        monto = monto + "'" + this.name.split("|")[6] + "'";
                 }else{
                     $("#div_"+this.id).addClass("error_campo");
                 }
            }


        })
    /*    var ok = "OK";
        var lista=numReferencia.split(",");
        var mensaje = "";
        $.each(lista,function(pos,item){
            var elementos=$("#"+item.replace("'","").replace("'","")).attr("name");
            if(idioma == "_us_en"){
               if(Number(unFormatCurrency(elementos.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))){
                   mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                   ok = "NOK";
               }
               if (Number(unFormatCurrency(elementos.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                   mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                   ok = "NOK";
               }
            }else{
                if(Number(unFormatCurrency(elementos.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))){
                    mensaje = mensaje + "Monto y Descripci&oacute;n: Monto no permitido. Por favor ingrese un monto mayor o igual a (Min. USD "+parametros.ex_mminto+")."+"<br>" ;
                    ok = "NOK";
                }
                if (Number(unFormatCurrency(elementos.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                    mensaje = mensaje + "Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.ex_mmto+")."+"<br>" ;
                    ok = "NOK";
                }
            }
            if(ok=="NOK")
                return false;
        }); */

        if(numReferencia==""){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
              $("#mensaje_error").html('You must select at least one transfer');
            else
              $("#mensaje_error").html('Debe seleccionar al menos una Transferencia');
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(mensaje!=""){
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                $("#clave_aprobarTransferencias").attr("style","display: ");
                $("#pwdClaveConfirmApprove").val("");
                $("#div_tabla_consultaTransferenciasPorAprobar").attr("style", "display: none");
                crearResumen("resumen_trans_FC_aprobar",numReferencia);
                numReferenciaGlobal=numReferencia;
                montosGlobal=monto;
                //AprobarGenerarClaveJSONData(numReferencia);
            }
        }
    });


    $("#rechazarTransferencias").click(function(){
        var numReferencia ="";
        var accionesCero="";
        $(".verificarSelecccionTrans").each(function(){
            if($("#"+this.id).val()=="1"){
                if (numReferencia!="")
                    numReferencia = numReferencia + ",";
                numReferencia = numReferencia + "'" + this.id + "'";

            }
        })

        if(numReferencia==""){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html('You must select at least one transfer');
            else
                $("#mensaje_error").html('Debe seleccionar al menos una Transferencia');
            $("#div_mensajes_error").fadeIn("slow");
        }else{
                if(idioma=="_us_en"){
                    if (confirm('Are you sure you want to reject the selected transfers?')) {
                        saveTransfersToRejectJSONData(numReferencia);
                    }
                } else {
                    if (confirm('¿Est\u00e1 seguro que desea rechazar las transferencias seleccionadas?')) {
                        saveTransfersToRejectJSONData(numReferencia);
                    }
                }
            }

    });



    $("#btn_aceptarClave_aprobar").click(function(){

        $("#btn_cambiarMtdClave").click();
        $("#btn_cambiarMtdClave").addClass("oculto");
        $("#btnAceptar").addClass("oculto");

        //Se llama a la pantalla de metodos de validacion
         mainValidationMethods("aprobarTransferenciaFC");
        //ValidarClaveAprobarJSONData();
    });
    $("#btn_cancelarClave_aprobar").click(function(){
        $("#clave_aprobarTransferencias").attr("style","display: none");
        $("#pwdClaveConfirmApprove").val("");
        $("#div_tabla_consultaTransferenciasPorAprobar").attr("style", "display: ");
    });

});


function crearResumen(contenedor_id,numReferencia){

    var contenedor= $("#"+contenedor_id);
    contenedor.empty();
    lista=numReferencia.split(",");
    $.each(lista,function(pos,item){
        var elementos=$("#"+item.replace("'","").replace("'","")).attr("name");
       var table=$(document.createElement("table")).appendTo(contenedor);
       table.attr("class","tabla_resumen2");
       table.attr("cellpadding","4");
       table.attr("cellspacing","1");
       var tr=$(document.createElement("tr")).appendTo(table);
       var td=$(document.createElement("td")).appendTo(tr);
       td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_num_ref'>Reference Number</span>");
        else
            td.html("<span id='resumen_detalle_num_ref'>Número de Referencia</span>");

        td=$(document.createElement("td")).appendTo(tr);
       td.attr("class","titulo_resumen");
       td.html(elementos.split("|")[5]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_Date'>Date</span>");
        else
            td.html("<span id='resumen_detalle_Date'>Fecha</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[1]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_cuenta_O'> From Account</span>");
        else
            td.html("<span id='resumen_detalle_cuenta_O'>Cuenta Débito</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[2]);


        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_cuenta_B'>Beneficiary Account</span>");
        else
            td.html("<span id='resumen_detalle_cuenta_B'>Cuenta Crédito</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[3]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_beneficiario'>Beneficiary</span>");
        else
            td.html("<span id='resumen_detalle_beneficiario'>Nombre Beneficiario</span>");

        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[4]);

        tr=$(document.createElement("tr")).appendTo(table);
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","titulo_resumen");
        if(idioma=="_us_en")
            td.html("<span id='resumen_detalle_monto'>Amount</span>");
        else
            td.html("<span id='resumen_detalle_monto'>Monto</span>");
        td=$(document.createElement("td")).appendTo(tr);
        td.attr("class","datos_resumen");
        td.html(elementos.split("|")[6]);
    });
}


function AprobarGenerarClaveJSONData(numReferencia){
    var url = urlAprobarGenerarClaveTOB;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= numReferencia;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,AprobarGenerarClaveSuccess,null,null);
}


function AprobarGenerarClaveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
//    alert("se ha enviado una clave a su correo");

}





function ValidarClaveAprobarJSONData(){
    var url = urlValidarClaveAprobar;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#pwdClaveConfirmApprove").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,ValidarClaveAprobarSuccess,null,null);
}


function ValidarClaveAprobarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.claveValida;
    var numReferencia = result.numReferencia;
    idioma = result.idioma;



    if(exito=="OK"){
        saveTransfersToApproveJSONData(numReferencia);
//        alert("OK");
    }else if (exito=="NO OK"){
        $("#btn_aceptarClave_aprobar").attr("disabled",false);
        if(idioma=="_us_en")
            mensaje ="The Transaction Key you entered is wrong"+"<br>"+"If you failed three times entering your Transaction Key, this approved operation will be canceled";
        else
            mensaje ="Clave de confirmaci&oacute;n de operaci&oacute;n incorrecta."+"<br>"+"Si se equivoca tres veces colocando su clave de operaciones, Esta Aprobaci&oacute;n ser&aacute; cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        ParametrosPersonalesCamposAPRVJSONData();
        LoadTransfersToApproveJSONData();
        if(idioma=="_us_en")
          mensaje ="The Transaction Key you entered is wrong for third time, your transfer operation was canceled";
        else
          mensaje ="La clave de confirmaci&oacute;n que ingres&oacute; es incorrecta por tercera vez consecutiva, su aprobaci&oacute;n ha sido cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}

function LoadTransfersToApproveJSONData(){
    var url = urlTransfersToApprove;
    var param={};

    sendServiceJSON_sinc(url,param,LoadTransfersToApproveSuccess,null,null);
}

function LoadTransfersToApproveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    idioma = result.idioma;
    $("#div_tabla_consultaTransferenciasPorAprobar").attr("style", "display: ");
    $("#clave_aprobarTransferencias").attr("style","display: none");
    $("#pwdClaveConfirmApprove").val("");
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    //$("#marco_trabajo").css("height","1000px");
    var tabla="tabla_TransferenciasPorAprobar";

    crearTablaTmp("div_tabla_TransferenciasPorAprobar",tabla,columnas,datos);

    $(".detalle_resumen_transferencia").click(function(){
        consultarDetalle_TransferenciasFCJSONData($(this).attr("id").split("_")[1]);
    });

    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "" },
            {  "sClass": "" },
            {  "sClass": "" },
            {  "sClass": "" },
            {  "sClass": "" },
            {  "sClass": "right" },
            { "sClass": "center" },
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function consultarDetalle_TransferenciasFCJSONData(numReferencia){
    var url = urlconsultarDetalleFC;
    var param={};
    var jsonTransfers=[];

    param.jsonTransfers=numReferencia;

    sendServiceJSON(url,param,consultarDetalle_TransferenciasFCSuccess,null,null);
}

function consultarDetalle_TransferenciasFCSuccess(originalRequest){

    var result= originalRequest;

    if(result.detalle_transferencia.length > 0){

        $("#detalle_RAccounts").html(result.detalle_transferencia[0]);
        $("#detalle_RBankCode").html(result.detalle_transferencia[1]);
        $("#detalle_RNameBank").html(result.detalle_transferencia[2]);
        $("#detalle_Rname").html(result.detalle_transferencia[4]);
        $("#detalle_RAccountNumber").html(result.detalle_transferencia[5]);
        $("#detalle_BeneficiaryAddress1").html(result.detalle_transferencia[8]);
        $("#detalle_RCorreo").html(result.detalle_transferencia[6]);
        $("#detalle_RTelephoneNumber").html(result.detalle_transferencia[7]);
        $("#detalle_RBankCodeIB").html(result.detalle_transferencia[9]);
        $("#detalle_RNameBankIB").html(result.detalle_transferencia[10]);
        $("#detalle_RAccountNumberFFC").html(result.detalle_transferencia[13]);
        $("#detalle_RNameFFC").html(result.detalle_transferencia[14]);
        $("#detalle_RAmmountAI").html(result.detalle_transferencia[15]);

        $("#detalle_transferencias_usr_carga2").html(result.detalle_transferencia[18]);
        $("#detalle_transferencias_fecha_carga2").html(result.detalle_transferencia[19]);
        $("#detalle_transferencias_usr_aprueba2").html(result.detalle_transferencia[20]);
        $("#detalle_transferencias_fecha_aprueba2").html(result.detalle_transferencia[21]);

        if(idioma=="_us_en"){
            if(result.detalle_transferencia[17]=="C"){
                $("#detalle_status_TOB").html("Input");
            }else if(result.detalle_transferencia[17]=="A"){
                $("#detalle_status_TOB").html("Approved");
            }
        }else{
            if(result.detalle_transferencia[17]=="C"){
                $("#detalle_status_TOB").html("Cargada");
            }else if(result.detalle_transferencia[17]=="A"){
                $("#detalle_status_TOB").html("Aprobada");
            }
        }
        $("#detalle_RReceiverInformation").html(result.detalle_transferencia[16]);


        //Ocultar
         if ($("#detalle_RNameBank").html()==""){
             $("#BeneficiaryBankDT").addClass("oculto");
             $("#BeneficiaryBankDT1").addClass("oculto");
             $("#BeneficiaryBankDT2").addClass("oculto");
         }else{
             $("#BeneficiaryBankDT").removeClass("oculto");
             $("#BeneficiaryBankDT1").removeClass("oculto");
             $("#BeneficiaryBankDT2").removeClass("oculto");
         }

        if ($("#detalle_Rname").html()==""){
            $("#detalle_RnameDT").addClass("oculto");
            $("#detalle_RAccountNumberDT").addClass("oculto");
            $("#detalle_div_RTelephoneNumber").addClass("oculto");
        }else{
            $("#detalle_RnameDT").removeClass("oculto");
            $("#detalle_RAccountNumberDT").removeClass("oculto");
            $("#detalle_div_RTelephoneNumber").removeClass("oculto");
        }


        if($("#detalle_RReceiverInformation").html()==""){
            $("#detalle_div_RReceiverInformation").addClass("oculto");
        }else{
            $("#detalle_div_RReceiverInformation").removeClass("oculto");
        }

        if($("#detalle_RTelephoneNumber").html()==""){
            $("#detalle_div_RTelephoneNumber").addClass("oculto");
        }else{
            $("#detalle_div_RTelephoneNumber").removeClass("oculto");
        }

        if($("#detalle_RNameBankIB").html()==""){
            $("#detalle_div_RNameBankIB").addClass("oculto");
        }else{
            $("#detalle_div_RNameBankIB").removeClass("oculto");
        }



        if ($("#detalle_RBankCodeIB").html()==""){
            $("#detalle_div_TOB_resumen_intermediary").addClass("oculto");
            $("#detalle_div_RBankCodeIB").addClass("oculto");
            $("#detalle_div_RNameBankIB").addClass("oculto");
        }else{
            $("#detalle_div_TOB_resumen_intermediary").removeClass("oculto");
            $("#detalle_div_RBankCodeIB").removeClass("oculto");
            $("#detalle_div_RNameBankIB").removeClass("oculto");
        }

        if ($("#detalle_RAccountNumberFFC").html()==""){
            $("#detalle_div_TOB_resumen_furtherCredit").addClass("oculto");
            $("#detalle_div_RAccountNumberFFC").addClass("oculto");
            $("#detalle_div_RNameFFC").addClass("oculto");
        }else{
            $("#detalle_div_TOB_resumen_furtherCredit").removeClass("oculto");
            $("#detalle_div_RAccountNumberFFC").removeClass("oculto");
            $("#detalle_div_RNameFFC").removeClass("oculto");
        }


        if (($("#detalle_transferencias_usr_aprueba2").html()=="")||($("#detalle_transferencias_usr_aprueba2").html()=="null")){
            $("#detalle_div_usr_apro").addClass("oculto");
            $("#detalle_div_usr_apro2").addClass("oculto");

        }else{
            $("#detalle_div_usr_apro").removeClass("oculto");
            $("#detalle_div_usr_apro2").removeClass("oculto");
        }

        $("#detalle_transferencia_fc").fadeIn("slow");
    }
}

function cambiarValorTransfer(id){
    if($("#"+id).is(':checked')){
        $("#"+id).attr("value","1");
    }else
        $("#"+id).attr("value","0");
}

function saveTransfersToApproveJSONData(numReferencia, montoGlobal){
    var url = urlsaveTransfersToApprove;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0]= numReferencia;
    jsonTransfers[1]= montoGlobal;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,saveTransfersToApproveSuccess,null,null);
}


function saveTransfersToApproveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var res = result.respuesta;
    idioma = result.idioma;
    if(res=="OK"){
        if(idioma=="_us_en")
          alert("The selected Transfers has been Approved");
        else
          alert("La(s) transferencia(s) seleccionada(s) ha(n) sido aprobada(s)");
        LoadTransfersToApproveJSONData();
    }else{
        if(idioma=="_us_en")
            alert("The selected Transfers has not been Approved");
        else
            alert("La(s) transferencia(s) seleccionada(s) no ha(n) sido aprobada(s");

    }

}

function saveTransfersToRejectJSONData(numReferencia){
    var url = urlsaveTransfersToReject;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0]= numReferencia;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,saveTransfersToRejectSuccess,null,null);
}


function saveTransfersToRejectSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var res = result.respuesta;
    idioma = result.idioma;
    if(res=="OK"){
        if(idioma=="_us_en")
            alert("Selected Transfers have been rejected");
        else
            alert("Las Transferencias seleccionadas han sido rechazadas");
        LoadTransfersToApproveJSONData();
    }else{
        if(idioma=="_us_en")
            alert("Selected transfers could not be rejected");
        else
            alert("Las Transferencias seleccionadas no han podido ser rechazadas");

    }

}

function ParametrosPersonalesCamposAPRVJSONData(){
    var url = urlParametrosPersonalesAPRV;
    var param={};

    sendServiceJSON(url,param,ParametrosPersonalesAPRVCamposSuccess,null,null);
}


function ParametrosPersonalesAPRVCamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;
    parametrosBase = result.parametrosPersonalesBase;

    if(parametros == null || parametros == ""){
        parametros = parametrosBase;
    }
}






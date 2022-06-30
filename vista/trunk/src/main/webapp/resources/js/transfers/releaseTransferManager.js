var urlTransfersToRelease="Transfers_transferenciasPorLiberar.action";
var urlsaveTransfersToRelease="Transfers_cambiarEstatusTransferenciaLiberar.action";
var urlValidarClaveLiberar="Transfers_validarClaveTransferenciasAprobar.action";
var urlLiberarGenerarClaveTOB="Security_crearClaveRamdom.action";
var urlsaveTransfersReleaseToReject="Transfers_cambiarEstatusTransferenciaRechazar.action";
var urlObtenerMontosFC="Transfers_obtenerMontosLiberadorFC.action";
//var grupoSeleccionado;
 var grupoID="";
var opcionID="";
var idioma ="";
var noInfo ="";
var resPP="";
var opea=0;
var cantidadInternaFC=0;
var cantidadExternaFC=0;
var montoInternaFC=0;
var montoInternasFC=0;
var montoExternaFC=0;


$(document).ready(function(){

    $("#liberarTransferencias").click(function(){
        var numReferencia="";
        var monto="";
        var mensaje="";
        var saldoDisponible=0;
        var precioFM = 0;
        var unidadesdisponibles = 0;
        var cancelacion = 0;
        var contSel=0;
        var saldoTotalFM=0;
        validarMontosLiberadorFC();
       /* var montoInternaFC=0;
        var montoExternaFC=0;           */
        $(".verificarSelecccionTrans").each(function(){
            var ok="OK";
            if($("#div_"+this.id).hasClass("error_campo"))
                $("#div_"+this.id).removeClass("error_campo");

            if($("#"+this.id).val()=="1"){
                contSel++;
                if (this.name.split("|")[8]=="TEO"){
                    //montoExternaFC=parseFloat(montoExternaFC)+parseFloat(this.name.split("|")[6]);
                    montoExternaFC=montoExternaFC+Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.'));
                    //Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')
                    if(idioma == "_us_en"){
                        //valida los montos minimos
                        if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))){
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" Invalid amount (USD "+parametros.ex_mminto+"."+")<br>";
                            ok="NO OK";
                        }
                        if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                            ok="NO OK";
                        }

                        if (parseInt(cantidadExternaFC) > parseInt(parametros.ex_nmtd)) {
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" You have exceeded the Maximum Number of Daily External Transfers "+parametros.ex_nmtd+"<br>";
                            ok="NO OK";
                        }else{
                            cantidadExternaFC++;
                        }

                        if(parseFloat(montoExternaFC)  >  Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.'))){
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" You have exceeded the Maximum Amount of Daily External Transfers (USD "+parametros.ex_mmtd+"."+")<br>";
                            ok="NO OK";
                        }



                    }else{
                        //valida los montos minimos
                        if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))){
                            mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Monto no permitido (USD "+parametros.ex_mminto+")."+").<br>" ;
                            ok="NO OK";
                        }
                        if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                            mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.ex_mmto+")."+"<br>" ;
                            ok="NO OK";
                        }

                        if (parseInt(cantidadExternaFC) > parseInt(parametros.ex_nmtd)) {
                            mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Ha Excedido el N&uacute;mero M&aacute;ximo de Transferencias Externas Diarias "+parametros.ex_nmtd+"<br>";
                            ok="NO OK";
                        }else{
                            cantidadExternaFC++;
                        }
                        if (parseFloat(montoExternaFC)  >  Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.'))){
                            mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Ha Excedido el Monto M&aacute;ximo de Transferencias Externas Diarias ( USD"+parametros.ex_mmtd+")."+")<br>" ;
                            ok="NO OK";
                        }



                    }


                }else{
                    if (this.name.split("|")[8]=="CID"){
                        //test
                        montoInternasFC=montoInternasFC+Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.'));

                        //test
                        if(idioma == "_us_en"){
                            //valida los montos minimos
                            if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))){
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" Invalid amount (USD "+parametros.vbt_mminto+"."+")<br>";
                                ok="NO OK";
                            }
                            if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" The amount exceeds the maximum of USD "+parametros.vbt_mmto+"."+"<br>";
                                ok="NO OK";
                            }

                            if (parseInt(cantidadInternaFC) > parseInt(parametros.vbt_nmtd)) {
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" You have exceeded the Maximum Number of Daily Internal Transfers "+parametros.vbt_nmtd+"<br>";
                                ok="NO OK";
                            }else{
                                cantidadInternaFC++;
                            }

                            if (parseFloat(montoInternasFC)  >  Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.'))){
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[5]+" You have exceeded the Maximum Amount of Daily Internal Transfers (USD "+parametros.vbt_mmaxtd+"."+")<br>";
                                ok="NO OK";
                            }


                        }else{
                            //valida los montos minimos
                            if(Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) <  Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))){
                                mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Monto no permitido (USD"+parametros.vbt_mminto+")."+")<br>" ;
                                ok="NO OK";
                            }
                            if (Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                                mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.vbt_mmto+")."+"<br>" ;
                                ok="NO OK";
                            }
                            if (parseInt(cantidadInternaFC) > parseInt(parametros.vbt_nmtd)) {
                                mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Ha Excedido el N&uacute;mero M&aacute;ximo de Transferencias Internas Diarias "+parametros.vbt_nmtd+"<br>";
                                ok="NO OK";
                            }else{
                                cantidadInternaFC++;
                            }

                            if (parseFloat(montoInternasFC)  >  Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.'))){
                                mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5]+" Ha Excedido el Monto M&aacute;ximo de Transferencias Internas Diarias (USD "+parametros.vbt_mmaxtd+")."+")<br>" ;
                                ok="NO OK";
                            }
                        }
                    }
                }

                saldoDisponible = Number(this.name.split("|")[13]);
                precioFM = (this.name.split("|")[14]);
                unidadesdisponibles = (this.name.split("|")[15]);
                cancelacion = (this.name.split("|")[16]);

                if (precioFM != null){
                    saldoTotalFM = (saldoDisponible * precioFM);
                }

                if (cancelacion == "N"){
                    if (precioFM == null){
                        if((saldoDisponible - Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) < parametros.minimun_balance)) {
                            if(idioma=="_us_en"){
                                mensaje = mensaje + "Reference Number:"+this.name.split("|")[5] + " The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                            }
                            else {
                                mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5] + " Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                            }

                            ok="NO OK";
                        }
                    }else{
                        if (unidadesdisponibles == "0"){
                            if((saldoTotalFM - Number(unFormatCurrency(this.name.split("|")[6],',').replace(',','.')) < parametros.minimun_balance)) {
                                if(idioma=="_us_en"){
                                    mensaje = mensaje + "Reference Number:"+this.name.split("|")[5] + " The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                                }
                                else {
                                    mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5] + " Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                                }

                                ok="NO OK";
                            }
                        }else{
                            ok="OK";
                        }
                    }
                }else{
                    if (saldoDisponible>"0"){
                        ok="OK";
                    }else{
                        if(idioma=="_us_en"){
                            mensaje = mensaje + "Reference Number:"+this.name.split("|")[5] + " The amount exceeds the minimun in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                        }
                        else {
                            mensaje = mensaje + "Número de Referencia:"+this.name.split("|")[5] + " Monto m&iacute;nimo en cuenta excedido (M&iacute;n. " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                        }
                        ok="NO OK";
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
        });


        if((!contSel)&&(mensaje=="")){
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
                $("#clave_liberarTransferencias").attr("style","display: ");
                $("#pwdClaveConfirmRelease").val("");
                $("#div_tabla_consultaTransferenciasPorLiberar").attr("style", "display: none");
                crearResumen("resumen_trans_FC_liberar",numReferencia);
                numReferenciaGlobal=numReferencia;
            }
            //LiberarGenerarClaveJSONData(numReferencia);
//            saveTransfersToReleaseJSONData(numReferencia);
        }

    });

    $("#rechazarTransferenciasRelease").click(function(){
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
                $("#mensaje_error").html('You must select at least one transfer');
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(idioma=="_us_en"){
                if (confirm('Are you sure you want to reject the selected transfers?')) {
                    saveTransfersReleaseToRejectJSONData(numReferencia);
                }
            } else {
                if (confirm('¿Est\u00e1 seguro que desea rechazar las transferencias seleccionadas?')) {
                    saveTransfersReleaseToRejectJSONData(numReferencia);
                }
            }
        }

    });

    $("#btn_aceptarClave_liberar").click(function(){
        mainValidationMethods("liberarTransferenciaFC");
    });

    $("#btn_cancelarClave_liberar").click(function(){
        $("#clave_liberarTransferencias").attr("style","display: none");
        $("#pwdClaveConfirmRelease").val("");
        $("#div_tabla_consultaTransferenciasPorLiberar").attr("style", "display: ");
    });

});

function LiberarGenerarClaveJSONData(numReferencia){
    var url = urlLiberarGenerarClaveTOB;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= numReferencia;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,LiberarGenerarClaveSuccess,null,null);
}

function LiberarGenerarClaveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
}

function ValidarClaveLiberarJSONData(){
    var url = urlValidarClaveLiberar;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#pwdClaveConfirmRelease").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,ValidarClaveLiberarSuccess,null,null);
}

function ValidarClaveLiberarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.claveValida;
    var numReferencia = result.numReferencia;
    idioma = result.idioma;



    if(exito=="OK"){
        saveTransfersToReleaseJSONData(numReferencia);
        LoadTransfersToReleaseJSONData();
    }else if (exito=="NO OK"){
        $("#btn_aceptarClave_liberar").attr("disabled",false);
        if(idioma=="_us_en")
            mensaje ="The Transaction Key you entered is wrong"+"<br>"+"If you failed three times entering your Transaction Key, this release operation will be canceled";
        else
            mensaje ="Clave de confirmaci&oacute;n de operaci&oacute;n incorrecta."+"<br>"+"Si se equivoca tres veces colocando su clave de operaciones, Esta Liberaci&oacute;n ser&aacute; cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        LoadTransfersToReleaseJSONData();
        if(idioma=="_us_en")
            mensaje ="The Transaction Key you entered is wrong for third time, your release operation was canceled";
        else
            mensaje ="La clave de confirmaci&oacute;n que ingres&oacute; es incorrecta por tercera vez consecutiva, su liberaci&oacute;n ha sido cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}

function saveTransfersToReleaseSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var res = result.respuesta;
    idioma = result.idioma;
    if(res=="OK"){
        if(idioma=="_us_en")
            alert("Selected Transfers were approved");
        else
            alert("Las Transferencias seleccionadas han sido aprobadas");
    }else{
        if(idioma=="_us_en")
            alert("Selected Transfers have not been approved");
        else
            alert("Las Transferencias seleccionadas no han podido ser aprobadas");
    }
    LoadTransfersToReleaseJSONData();
}

function LoadTransfersToReleaseJSONData(){
    console.log('entre LoadTransfersToReleaseJSONData');
    var url = urlTransfersToRelease;
    var param={};

    sendServiceJSON_sinc(url,param,LoadTransfersToReleaseSuccess,null,null);
}

function LoadTransfersToReleaseSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    idioma = result.idioma;
    $("#div_tabla_consultaTransferenciasPorLiberar").attr("style", "display: ");
    $("#clave_liberarTransferencias").attr("style","display: none");
    $("#pwdClaveConfirmRelease").val("");
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    //$("#marco_trabajo").css("height","1000px");
    var tabla="tabla_TransferenciasPorLiberar";

    crearTablaTmp("div_tabla_TransferenciasPorLiberar",tabla,columnas,datos);

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
            { "sClass": "center" } ,
            { "sClass": "center" } ,
            { "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function cambiarValorTransfer(id){
    if($("#"+id).is(':checked')){
        $("#"+id).attr("value","1");
    }else
        $("#"+id).attr("value","0");
}

function saveTransfersToReleaseJSONData(numReferencia){
    var url = urlsaveTransfersToRelease;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0]= numReferencia;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,saveTransfersToReleaseSuccess,null,null);
}

function saveTransfersToReleaseSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var res = result.respuesta;
    idioma=result.idioma;
    if(res=="OK"){
        if(idioma=="_us_en")
            alert("Selected Transfers have been released");
        else
            alert("Las Transferencias seleccionadas han sido Liberadas");
        LoadTransfersToReleaseJSONData();
    }else{
        if(idioma=="_us_en")
            alert("Selected Transfers have not been released");
        else
            alert("Las Transferencias seleccionadas no han podido ser Liberadas");
    }



}

function saveTransfersReleaseToRejectJSONData(numReferencia){
    var url = urlsaveTransfersReleaseToReject;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0]= numReferencia;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,saveTransfersReleaseToRejectSuccess,null,null);
}

function saveTransfersReleaseToRejectSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var res = result.respuesta;
    idioma = result.idioma;
    if(res=="OK"){
        if(idioma=="_us_en")
            alert("Selected Transfers have been rejected");
        else
            alert("Las Transferencias seleccionadas han sido rechazadas");
        LoadTransfersToReleaseJSONData();
    }else{
        if(idioma=="_us_en")
            alert("Selected transfers could not be rejected");
        else
            alert("Las Transferencias seleccionadas no han podido ser rechazadas");

    }

}

function validarMontosLiberadorFC(){
    var url = urlObtenerMontosFC;
    var param={};
    var jsonTransfers=[];

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,validarMontosLiberadorFCSuccess,null,null);
}

function validarMontosLiberadorFCSuccess(originalRequest){
    //                   this is the json return data


    var result = originalRequest;
    var test=0;
    cantidadInternaFC=Number(result.cantidadInternas);
    cantidadExternaFC=Number(result.cantidadExternas);
    montoInternaFC=parseFloat(result.montoInternas);
    montoExternaFC=parseFloat(result.montoExternas);
    montoInternasFC=parseFloat(result.montoInternas);

   /* montoInternaFC=Number(result.montoInternas);
    montoExternaFC=Number(result.montoExternas);
    cantidadInternaFC=Number(result.cantidadInternas);
    cantidadExternaFC=Number(result.cantidadExternas);*/

   /* montoInternaFC=result.montoInternas;
    montoExternaFC=result.montoExternas;
    cantidadInternaFC=result.cantidadInternas;
    cantidadExternaFC=result.cantidadExternas;   */
}
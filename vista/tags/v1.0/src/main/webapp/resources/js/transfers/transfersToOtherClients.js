var urlTransfersCargarOtherClient="Transfers_cargarToOtherClient.action";
var urlTransfersValidarOtherClient="Transfers_validarCuentaDestino_TOC.action";
var urlTransfersSalvarOtherClient="Transfers_saveToOtherClient.action";
var urlParametrosPersonalesTOC="DesingBank_cargarParametrosPersonales.action";


var idioma="";
var parametros="";
var parametrosBase="";
var usuario = "";


$(document).ready(function(){

    $("#btn_home_TOC_final").click(function(){
        seleccionarOpcionMenu("home");
    });


    $("#ToOtherClient_btn_aceptar").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var montoAux="";

        montoAux=($("#ToOtherClient_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        $(".obligatorioTOC").each(function(){

            if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                if(idioma=="_us_en"){
                    //mensaje=mensaje+"Required field - "+ this.title+"<br>";
                    mensaje = mensaje + "Please complete the required information";
                    return;
                }else{
                    //mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    mensaje = mensaje + "Por favor complete la informacion requerida";
                    return;
                }
                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });

        if(mensaje==""){
            if ($("#ToOtherClient_NumCuentaDestino").get(0).value.length < 10) {
                if(idioma=="_us_en")
                    mensaje = mensaje + "Please enter a valid 'To account'"+"<br>The account must have 10 digits";
                else
                    mensaje = mensaje + "Debe Indicar una cuenta valida"+"<br>La cuenta debe poseer 10 dígitos";
                $("#ToOtherClient_beneficiaryEmail").addClass("error_campo");
                invalido ="1";
            }
            var valor1 = Trim($("#ToOtherClient_Accounts").get(0).value.substring(0, $("#ToOtherClient_Accounts").get(0).value.indexOf('|')));
            var valor2 =$("#ToOtherClient_NumCuentaDestino").get(0).value;
            if (Trim($("#ToOtherClient_Accounts").get(0).value.substring(0, $("#ToOtherClient_Accounts").get(0).value.indexOf('|'))) == Trim($("#ToOtherClient_NumCuentaDestino").get(0).value)) {
                if(idioma=="_us_en")
                    mensaje = mensaje + "The 'From Account' and the 'To Account' are the same. Please try again."+"<br>The 'To Account' must have 10 digits";
                else
                    mensaje = mensaje + "Las cuentas deben ser diferentes"+"<br>La Cuenta Destino debe poseer 10 dígitos";
                $("#ToOtherClient_NumCuentaDestino").addClass("error_campo");
                invalido ="1";
            }
            if (Number(unFormatCurrency(montoAux,',').replace(',','.')) < Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))) {
            //if (Number(unFormatCurrency($("#ToOtherClient_Monto").val(),',').replace(',','.')) < Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))) {
                if(idioma=="_us_en")
                    mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of "+parametros.vbt_mminto+"."+"<br>";
                else
                    mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a "+parametros.vbt_mminto+"."+"<br>";
                $("#ToOtherClient_Monto").addClass("error_campo");
                invalido ="1";
            }
            if(usuario.tipo!="6"){
                if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.')) || Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                //if (Number(unFormatCurrency($("#ToOtherClient_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.')) || Number(unFormatCurrency($("#ToOtherClient_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                    if(idioma=="_us_en")
                    {
                        if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.')))
                        //if(Number(unFormatCurrency($("#ToOtherClient_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.')))
                            mensaje = mensaje + "The amount exceeds the maximum of USD "+parametros.vbt_mmto+"."+"<br>";
                        else
                            mensaje = mensaje + "The amount exceeds the maximum of USD "+parametros.vbt_mmaxtd+"."+"<br>";
                    }
                    else {
                        if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.')))
                        //if(Number(unFormatCurrency($("#ToOtherClient_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.')))
                            mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.vbt_mmto+")."+"<br>";
                        else
                            mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+ parametros.vbt_mmaxtd+")."+"<br>";
                    }

                    $("#ToOtherClient_Monto").addClass("error_campo");
                    invalido ="1";
                }
            }else{
                if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                //if (Number(unFormatCurrency($("#ToOtherClient_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                    if(idioma=="_us_en")
                    {
                        mensaje = mensaje + "The amount exceeds the maximum of USD "+parametros.vbt_mmto+"."+"<br>";
                    }
                    else {
                        mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+ parametros.vbt_mmto+")."+"<br>";
                    }

                    $("#ToOtherClient_Monto").addClass("error_campo");
                    invalido ="1";
                }
            }
        }

        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#ToOtherClient_beneficiaryEmail").get(0).value)){
                if($("#ToOtherClient_beneficiaryEmail").hasClass("error_campo"))
                    $("#ToOtherClient_beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
            }else{
                if(idioma=="_ve_es")
                  mensaje = "Por favor Indique un correo v&aacute;lido."+"<br>";
                else
                  mensaje = "Please enter a valid Email Address."+"<br>";
                $("#ToOtherClient_beneficiaryEmail").addClass("error_campo");
                invalido ="1";
            }

//            var cuentaDebito = $("#RToOtherClient_Accounts").get(0).value.substring(0, $("#RToOtherClient_Accounts").get(0).value.indexOf('|'));

            if(invalido=="0"){
                $("#div_mensajes_error").fadeOut("fast");
                TransfersValidateTOCJSONData();
            }else{
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
//

        }


    });

    $("#ToOtherClient_btn_cancelar").click(function(){
        $("#ToOtherClient_form .selectFormulario_TOCVBT").each(function(){
            this.value="-2";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        $("#ToOtherClient_form .inputFormulario_TOCVBT").each(function(){
            this.value="";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
    });

    $("#btn_resumen_cancelar_TOC").click(function(){
        $("#summaryToOtherClient").fadeOut("fast");
        $("#ToOtherClient_form").fadeIn("fast");
    });

    $("#btn_resumen_aceptar_TOC").click(function(){
        $("#btn_resumen_aceptar_TOC").attr("disabled",true);
        if(idioma=="_us_en"){
            if(confirm("Are you sure that the information is correct?")){
                TransferToOtherClientGuardarJSONData();
            } else{
                $("#btn_resumen_aceptar_TOC").attr("disabled",false);
            }
        }
        else{
            if(confirm("¿Est\u00e1 seguro de que los datos son correctos?")){
                TransferToOtherClientGuardarJSONData();
            } else{
                $("#btn_resumen_aceptar_TOC").attr("disabled",false);
            }
        }

    });

    $("#homeExitoso_TOC").click(function(){
        seleccionarOpcionMenu("home");

    });

    $("#btn_finalizar_TOC_final").click(function(){

      if (makeTranfers=="OK"){
        $("#ToOtherClient_form").attr("style","display: ");
        $("#summaryToOtherClient").attr("style","display: none");
        $("#resumenBotones_TOC_Finales").attr("style","display: none");
        $("#resumenBotones_TOC").attr("style","display: ");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").attr("style","display: none");
        infoPaginaToOtherClientJSONData();
    }else{

        $("#mensaje_error").empty();
        $("#mensaje_error").html(makeTranfers);
        $("#div_mensajes_error").fadeIn("slow");
    }
    });

    $("#btn_imprimir_TOC_final").click(function(){
        printOtherClientinVBT();
    });


});



function ParametrosPersonalesCamposJSONData(){
    var url = urlParametrosPersonalesTOC;
    var param={};

    sendServiceJSON(url,param,ParametrosPersonalesCamposSuccess,null,null);
}


function ParametrosPersonalesCamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;
    parametrosBase = result.parametrosPersonalesBase;

    if(parametros == null || parametros == ""){
        parametros = parametrosBase;
    }

}



function infoPaginaToOtherClientJSONData(){
    var url = urlTransfersCargarOtherClient;
    var param={};

    sendServiceJSON(url,param,infoPaginaToOtherClientSuccess,null,null);
}


function infoPaginaToOtherClientSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    valorCuentas = result.cuentasTOB;
    moneda = result.moneda;
    fechaCierre = result.fechaCierre;
    idioma = result.idioma;
    usuario = result.usuario;
    var mensaje =result.mensaje;
    var respuesta =result.respuesta;
    if (mensaje=="OK"){
        $("#ToOtherClient_form").fadeIn("fast");
        $("#summaryToOtherClient").fadeOut("fast");
    //    $("#resultToOtherClient").fadeOut("fast");
        $("#resultToOtherClient").attr("style","display: none");

        $("#ToOtherClient_form .selectFormulario_TOCVBT").each(function(){
            this.value="-2";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        $("#ToOtherClient_form .inputFormulario_TOCVBT").each(function(){
            this.value="";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });

        ParametrosPersonalesCamposJSONData();
        if(parametros==""){
            parametros = parametrosBase;
        }

        if(idioma=="_us_en")
            cargar_selectCuenta("ToOtherClient_Accounts", valorCuentas,"Select","-2",parametros.account_num);
        else
            cargar_selectCuenta("ToOtherClient_Accounts", valorCuentas,"Seleccione","-2",parametros.account_num);

        $("#monedaTOC").html(moneda);
        $("#ToOtherClient_tagAvailableBalanceDate").html(fechaCierre);



        if(parametros.account_num!="-2"){
           $("#ToOtherClient_Accounts").val(parametros.account_num);
        }
    }else{
        $("#ToOtherClient_form").attr("style","display: none");
        $("#noToOtherClient_form").fadeIn("low");
        $("#noInfo_noToOtherClient_form").html(respuesta);
    }
}

function  cargarData_toOtherClient(){
//    blanquearFormularios("ToOtherClient_form");
    //$("#marco_trabajo").css("height","700px");
    //cargo todos los datos que van en la pagina
    $("#noToOtherClient_form").attr("style","display: none");
    infoPaginaToOtherClientJSONData();

}



function TransfersValidateTOCJSONData(){
    var url = urlTransfersValidarOtherClient;
    var param={};
    var jsonTransfers_ValidarCuenta_TOC=[];

    jsonTransfers_ValidarCuenta_TOC[0]= $("#ToOtherClient_NumCuentaDestino").val();

    param.jsonTransfers_ValidarCuenta_TOC=JSON.stringify({"parametros":jsonTransfers_ValidarCuenta_TOC});

    sendServiceJSON(url,param,TransfersValidateTOCSuccess,null,null);
}


function TransfersValidateTOCSuccess(originalRequest){
    var result = originalRequest;
    var moneda = result.monedaTOC;
    var respuesta = result.respuesta;
    idioma=result.idioma;

    if(respuesta == "OK"){
       if (($("#ToOtherClient_Accounts option:selected").text()).indexOf(moneda)>0){
            $("#RToOtherClient_Accounts").html(quitarSaldo($('#ToOtherClient_Accounts :selected').html()));
            $("#RToOtherClient_NumCuentaDestino").html($("#ToOtherClient_NumCuentaDestino").val());
            $("#RToOtherClient_NombreBeneficiario").html($("#ToOtherClient_NombreBeneficiario").val());
            $("#RToOtherClient_beneficiaryEmail").html($("#ToOtherClient_beneficiaryEmail").val());
            $("#RToOtherClient_Monto").html($("#ToOtherClient_Monto").val()+" "+moneda);


            $("#summaryToOtherClient").fadeIn("fast");
            $("#ToOtherClient_form").fadeOut("fast");
            $("#div_mensajes_error").fadeOut("fast");

            if($("#ToOtherClient_Concepto").val()==""){
                $("#summaryToOtherClient #div_conceptp_TOC").attr("style","display: none");
            }else{
                $("#summaryToOtherClient #div_conceptp_TOC").attr("style","display: ");
                $("#RToOtherClient_Concepto").html($("#ToOtherClient_Concepto").val());
            }

            $("#summaryToOtherClient #resumenBotones_TOC").attr("style","display: ");
            $("#tituloExitoso_TOC").attr("style","display:none ");
            $("#summaryToOtherClient #resumenBotones_TOC_Finales").attr("style","display: none");
            $("#summaryToOtherClient #div_numref_TOC").attr("style","display: none");
            $("#summaryToOtherClient #div_estatus_TOC").attr("style","display: none");
            $("#summaryToOtherClient #tituloResumen_TOC").attr("style","display: ");
            $("#summaryToOtherClient #tituloExitoso_TOC").attr("style","display: none");
        }else{

           if(idioma=="_us_en")
               mensaje =  "Transfer must be made between same currency accounts."+"<br>";
           else
               mensaje =  "La transferencia debe ser entre cuentas de la misma moneda."+"<br>";
           $("#mensaje_error").empty();
           $("#mensaje_error").html(mensaje);
           $("#div_mensajes_error").fadeIn("slow");

        }
    }else{
        if(idioma=="_us_en")
          mensaje = "'To account' was not found. Please try again.";
        else
          mensaje = "No existe el n&uacute;mero de Cuenta Destino. Por favor indique un n&uacute;mero de Cuenta Destino correcto.";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }

//


}

function TransferToOtherClientGuardarJSONData(){
    var url = urlTransfersSalvarOtherClient;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};

    var montoAux="";

    montoAux=($("#ToOtherClient_Monto").get(0).value).replace(/,/g,'t');
    montoAux=(montoAux).replace(/\./g,',');
    montoAux=(montoAux).replace(/t/g,'.');

    $("#div_carga_espera").removeClass("oculto");
    ResumenTOB.account=$('#ToOtherClient_Accounts :selected').html();
    ResumenTOB.accountCode=$("#ToOtherClient_Accounts").get(0).value;
    ResumenTOB.beneficiaryName=$("#ToOtherClient_NombreBeneficiario").val();
    ResumenTOB.beneficiaryAccount=$("#ToOtherClient_NumCuentaDestino").val();
    ResumenTOB.beneficiaryEmail=$("#ToOtherClient_beneficiaryEmail").val();
    //ResumenTOB.amount=$("#ToOtherClient_Monto").val();
    ResumenTOB.amount=montoAux;
    ResumenTOB.recieverName=$("#ToOtherClient_Concepto").val();

    jsonTransfers[0]= ResumenTOB;

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,TransferToOTherClientGuardarSuccess,null,null);
}


function TransferToOTherClientGuardarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    var exito = result.code;
    var numref = result.numref;
    idioma = result.idioma;
    makeTranfers = result.respuesta;

//    $("#summaryToOtherClient").fadeOut("fast");
//    $("#resultToOtherClient").fadeIn("fast");
//    $("#txt_resultadoTransferTOC").html(result.mensaje);
    $("#btn_resumen_aceptar_TOC").attr("disabled",false);
    $("#div_carga_espera").addClass("oculto");
    if(exito!="0"){
        $("#ToOtherClient_form").attr("style","display: ");
        $("#summaryToOtherClient").attr("style","display: none");
        $("#resumenBotones_TOC_Finales").attr("style","display: none");
        $("#resumenBotones_TOC").attr("style","display: ");
//        $("#mensaje_error").empty();
//        $("#div_mensajes_error").attr("style","display: none");
        $("#mensaje_error").empty();
        if (makeTranfers!="1"){
            $("#mensaje_error").html(makeTranfers);

        } else{
            $("#mensaje_error").html(mensaje);
        }

        $("#div_mensajes_error").fadeIn("slow");
        infoPaginaToOtherClientJSONData();
    }else{
//        $("#img_resultadoTransferTOC").attr("src","../vbtonline/resources/images/exito.png");
        $("#div_numref_TOC").attr("style","display: ");
        $("#div_estatus_TOC").attr("style","display: ");
        $("#tituloExitoso_TOC").attr("style","display: ");
        $("#tituloResumen_TOC").attr("style","display: none");

        $("#resumenBotones_TOC_Finales").attr("style","display: ");
        $("#resumenBotones_TOC").attr("style","display: none");
//        $("#txt_resultadoTransferTOB").html(result.mensaje);

        if ( $("#tipo_usuario_app").val()=="6"){
            var mensaje2="";
            if(idioma=="_us_en"){
                $("#status_TOC").html("Input");
                mensaje2="This transfer instruction was successfully created; it must be Approved and Released by users with the appropriate permissions";
            }else{
                $("#status_TOC").html("Cargada");
                mensaje2="Esta orden de transferencia fue cargada exitosamente, la misma debe ser Aprobada y Liberada por los usuarios con los permisos correspondientes";
            }
            $("#mensaje_error").html(mensaje2);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(idioma=="_us_en")
                $("#status_TOC").html("In Process");
            else
                $("#status_TOC").html("En Proceso");
        }
         $("#numRef_TOC").html(numref);
    }


}


/**
 * Imprime div del html
 */



function printOtherClientinVBT(){

    var miValue = $("#ToOtherClient_Accounts").val();
    $("#ToOtherClient_Accounts option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#Accounts").val();
    $("#Accounts option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#BankCode").val();
    $("#BankCode option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#CountryBank").val();
    $("#CountryBank option[value='"+miValue+"']").attr("selected",true);

miValue = $("#AccountBank").val();
    $("#AccountBank option[value='"+miValue+"']").attr("selected",true);

miValue = $("#Country").val();
    $("#Country option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#BankCodeIB").val();
    $("#BankCodeIB option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#CountryBankIB").val();
    $("#CountryBankIB option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#BankCodeType_buscar").val();
    $("#BankCodeType_buscar option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#BankCountry_buscar").val();
    $("#BankCountry_buscar option[value='"+miValue+"']").attr("selected",true);


    printPageElement('div_TRANSFERENCIA_INTERNA_TERCEROS');  //Print EDO CUENTA

}


function validarCuentaOrigen(){
    var mensaje="";

    if($("#ToOtherClient_NumCuentaDestino").hasClass("error_campo"))
        $("#ToOtherClient_NumCuentaDestino").removeClass("error_campo");

    if (Trim($("#ToOtherClient_Accounts").get(0).value.substring(0, $("#ToOtherClient_Accounts").get(0).value.indexOf('|'))) == Trim($("#ToOtherClient_NumCuentaDestino").get(0).value)) {
        if(idioma=="_us_en")
            mensaje = mensaje + "The 'From Account' and the 'To Account' are the same. Please try again."+"<br>";
        else
            mensaje = mensaje + "Las cuentas deben ser diferentes"+"<br>";
        $("#ToOtherClient_NumCuentaDestino").addClass("error_campo");
    }
    if(mensaje!=""){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
};


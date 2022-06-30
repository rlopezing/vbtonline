var urlTransfersCargarBMLA="Transfers_cargarTranEntreMisCuentas.action";
var urlTransfersValidarBMLA="Transfers_validarCuentaDestino_TOC.action";
var urlTransfersSalvarBMLA="Transfers_saveBetweenMyAccounts.action";
var urlParametrosPersonalesBMLA="DesingBank_cargarParametrosPersonales.action";


var aMonedas="0";
var idioma = "";
var parametros="";
var parametrosBase="";
var tipoMoneda = "";
var usuario = "";
$(document).ready(function(){
    $("#BetweenLinkedAccounts_Accounts").change(function(){
        if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('USD')>=0){
            tipoMoneda = "USD";
        }else if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('EUR')>=0){
            tipoMoneda = "EUR";
        }
        infoPaginaChangeBMLAJSONData();
    });


    $("#BetweenLinkedAccounts_NumCuentaDestino").change(function(){
        var mensaje="";

        if($("#BetweenLinkedAccounts_NumCuentaDestino").hasClass("error_campo"))
            $("#BetweenLinkedAccounts_NumCuentaDestino").removeClass("error_campo");

        if ($("#BetweenLinkedAccounts_Accounts").get(0).value == $("#BetweenLinkedAccounts_NumCuentaDestino").get(0).value) {
            if(idioma=="_us_en")
                mensaje = mensaje + "The 'From Account' and the 'To Account' are the same. Please try again."+"<br>";
            else
                mensaje = mensaje + "Las cuentas deben ser diferentes."+"<br>";
            $("#BetweenLinkedAccounts_NumCuentaDestino").addClass("error_campo");
        }
        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }
    });


    $("#btn_home_BMLA_final").click(function(){
        $("#summaryBetweenLinkedAccounts").fadeOut("fast");
        seleccionarOpcionMenu("home");
    });

    $("#BetweenLinkedAccounts_btn_aceptar").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var montoAux="";

        montoAux=($("#BetweenLinkedAccounts_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        $(".obligatorioBMLA").each(function(){

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

        if(mensaje==""){
            if ($("#BetweenLinkedAccounts_Accounts").get(0).value == $("#BetweenLinkedAccounts_NumCuentaDestino").get(0).value) {
                if(idioma=="_us_en")
                    mensaje = mensaje + "The 'From Account' and the 'To Account' are the same. Please try again."+"<br>";
                else
                    mensaje = mensaje + "Las cuentas deben ser diferentes."+"<br>";
                $("#BetweenLinkedAccounts_NumCuentaDestino").addClass("error_campo");
            }

            if($("#BetweenLinkedAccounts_NumCuentaDestino").prop("selectedIndex")!=0 && $("#BetweenLinkedAccounts_Accounts").prop("selectedIndex")!=0){
//                if (aMonedas[$("#BetweenLinkedAccounts_NumCuentaDestino").prop("selectedIndex")-1] != aMonedas[$("#BetweenLinkedAccounts_Accounts").prop("selectedIndex")-1]) {
                if($("#BetweenLinkedAccounts_NumCuentaDestino option:selected").text().indexOf(tipoMoneda)<=0){
                    if(idioma=="_us_en")
                        mensaje = mensaje + "Transfer must be made between same currency accounts."+"<br>";
                    else
                        mensaje = mensaje + "La transferencia debe ser entre cuentas de la misma moneda."+"<br>";
                    $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                }
            }
            if (Number(unFormatCurrency(montoAux,',').replace(',','.')) < Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))) {
            //if (Number(unFormatCurrency($("#BetweenLinkedAccounts_Monto").val(),',').replace(',','.')) < Number(unFormatCurrency(parametros.vbt_mminto,',').replace(',','.'))) {
                if(idioma=="_us_en")
                    mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of "+parametros.vbt_mminto+"."+"<br>";
                else
                    mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a "+parametros.vbt_mminto+"."+"<br>";
                $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
            }
            if(usuario.tipo!="6"){
                if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.')) || Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                //if (Number(unFormatCurrency($("#BetweenLinkedAccounts_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.')) || Number(unFormatCurrency($("#BetweenLinkedAccounts_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                    if(idioma=="_us_en"){
    //                mensaje = mensaje + "The amount exceeds the maximum of USD 50.000,00."+"<br>";
                        if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.')))
                        //if(Number(unFormatCurrency($("#BetweenLinkedAccounts_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.')))
                            mensaje = mensaje + "The amount exceeds the maximum of USD "+parametros.vbt_mmto+"."+"<br>";
                        else
                            mensaje = mensaje + "The amount exceeds the maximum of USD "+parametros.vbt_mmaxtd+"."+"<br>";
                    }
                    else{
                        if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.')))
                        //if(Number(unFormatCurrency($("#BetweenLinkedAccounts_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd,',').replace(',','.')))
                            mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.vbt_mmaxtd+")."+"<br>";
                        else
                            mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+ parametros.vbt_mmaxtd+")."+"<br>";
    //                mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD 50.000,00)."+"<br>"
                    }

                    $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                }
            }else{
                if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                //if (Number(unFormatCurrency($("#BetweenLinkedAccounts_Monto").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.vbt_mmto,',').replace(',','.'))) {
                    if(idioma=="_us_en"){
                        //                mensaje = mensaje + "The amount exceeds the maximum of USD 50.000,00."+"<br>";
                        mensaje = mensaje + "The amount exceeds the maximum of USD "+parametros.vbt_mmto+"."+"<br>";
                    }
                    else{
                        mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+ parametros.vbt_mmto+")."+"<br>";
                        //                mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD 50.000,00)."+"<br>"
                    }

                    $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                }
            }
        }




        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#BetweenLinkedAccounts_form").fadeOut("fast");
            $("#summaryBetweenLinkedAccounts").fadeIn("fast");
//            $("#BetweenLinkedAccounts_btn_aceptar").attr("disabled",false);


            $("#RBetweenLinkedAccounts_Accounts").html(quitarSaldo($('#BetweenLinkedAccounts_Accounts :selected').html()));
            $("#RBetweenLinkedAccounts_NumCuentaDestino").html(quitarSaldo($("#BetweenLinkedAccounts_NumCuentaDestino :selected").html()));
            $("#RBetweenLinkedAccounts_Monto").html($("#BetweenLinkedAccounts_Monto").val()+" "+moneda);
            if(Trim($("#BetweenLinkedAccounts_Concepto").val())==""){
                $("#summaryBetweenLinkedAccounts #div_conceptp_BMLA").attr("style","display: none");
            }else{
                $("#summaryBetweenLinkedAccounts #div_conceptp_BMLA").attr("style","display: ");
                $("#RBetweenLinkedAccounts_Concepto").html($("#BetweenLinkedAccounts_Concepto").val());
            }

            $("#summaryBetweenLinkedAccounts #resumenBotones_BMLA").attr("style","display: ");
            $("#tituloExitoso_BMLA").attr("style","display:none ");
            $("#summaryBetweenLinkedAccounts #resumenBotones_BMLA_Finales").attr("style","display: none");
            $("#summaryBetweenLinkedAccounts #div_numref_BMLA").attr("style","display: none");
            $("#summaryBetweenLinkedAccounts #div_estatus_BMLA").attr("style","display: none");
            $("#summaryBetweenLinkedAccounts #tituloResumen_BMLA").attr("style","display: ");
            $("#summaryBetweenLinkedAccounts #tituloExitoso_BMLA").attr("style","display: none");

     }


    });

    $("#homeExitoso_BMLA").click(function(){
        seleccionarOpcionMenu("home");

    });

    $("#BetweenLinkedAccounts_btn_cancelar").click(function(){
        $("#BetweenLinkedAccounts_form .selectFormulario_BMLA").each(function(){
            this.value="-2";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        $("#BetweenLinkedAccounts_form .inputFormulario_BMLA").each(function(){
            this.value="";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });


    });

    $("#btn_resumen_cancelar_BMLA").click(function(){
        $("#summaryBetweenLinkedAccounts").fadeOut("fast");
        $("#BetweenLinkedAccounts_form").fadeIn("fast");
    });

    $("#btn_resumen_aceptar_BMLA").click(function(){
        $("#btn_resumen_aceptar_BMLA").attr("disabled",true);
        if(idioma=="_us_en"){
            if(confirm("Are you sure that the information is correct?")){
                TransferBetweenLinkedAccountsGuardarJSONData();  }else{
                $("#btn_resumen_aceptar_BMLA").attr("disabled",false);
            }
        }else{
            if(confirm("¿Est\u00e1 seguro de que los datos son correctos?")){
                TransferBetweenLinkedAccountsGuardarJSONData();  }else{
                $("#btn_resumen_aceptar_BMLA").attr("disabled",false);
            }
        }


    });

    $("#btn_finalizar_BMLA_final").click(function(){

            $("#BetweenLinkedAccounts_form").attr("style","display: ");
            $("#summaryBetweenLinkedAccounts").attr("style","display: none");
            $("#resumenBotones_BMLA_Finales").attr("style","display: none");
            $("#resumenBotones_BMLA").attr("style","display: ");
            $("#mensaje_error").empty();
            $("#div_mensajes_error").attr("style","display: none");
            infoPaginaBMLAJSONData();


    });

    $("#btn_imprimir_BMLA_final").click(function(){
        printTransfers();
    });



});

function ParametrosPersonalesBMLACamposJSONData(){
    var url = urlParametrosPersonalesBMLA;
    var param={};

    sendServiceJSON(url,param,ParametrosPersonalesBMLACamposSuccess,null,null);
}


function ParametrosPersonalesBMLACamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;
    parametrosBase = result.parametrosPersonalesBase;

    if(parametros == null || parametros == ""){
        parametros = parametrosBase;
    }

}


function infoPaginaChangeBMLAJSONData(){
    var url = urlTransfersCargarBMLA;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= "OTHER";


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,infoPaginaChangeBMLASuccess,null,null);
}


function infoPaginaChangeBMLASuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    idioma = result.idioma;
    valorCuentas = result.cuentasTOB;
    moneda = result.moneda;
    fechaCierre = result.fechaCierre;
    aMonedas =result.monedas;

    var mensaje =result.mensaje;
    var respuesta=result.respuesta;
    var selected= "-2";
    var cuentasNuevas = [];
    var i = 0;
    $.each(valorCuentas,function(s,item){
        if(item.label.indexOf(tipoMoneda)>=0){
            cuentasNuevas[i] = item;
            i++;
        }
    });
    cargar_selectPersonal("BetweenLinkedAccounts_NumCuentaDestino", cuentasNuevas,"Select","-2");

}


function infoPaginaBMLAJSONData(){
    var url = urlTransfersCargarBMLA;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= "OTHER";


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,infoPaginaBMLASuccess,null,null);
}


function infoPaginaBMLASuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
     idioma = result.idioma;
    valorCuentas = result.cuentasTOB;
    moneda = result.moneda;
    fechaCierre = result.fechaCierre;
    aMonedas =result.monedas;
    usuario = result.usuario;
    var mensaje =result.mensaje;
    var respuesta=result.respuesta;
    var selected= "-2";
    var cuentasNuevas = [];
    var i = 0;
    if (result.accountSelected!=null)
        selected=result.accountSelected;
    if (mensaje=="OK"){
        $("#BetweenLinkedAccounts_form").fadeIn("fast");
        $("#summaryBetweenLinkedAccounts").fadeOut("fast");
        $("#noBetweenLinkedAccounts_form").fadeOut("fast");
        $("#BetweenLinkedAccounts_form .selectFormulario_BMLA").each(function(){
            this.value="-2";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        $("#BetweenLinkedAccounts_form .inputFormulario_BMLA").each(function(){
            this.value="";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });

        if(idioma=="_us_en"){
            cargar_selectCuenta("BetweenLinkedAccounts_Accounts", valorCuentas,"Select","-2",selected);
            if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('USD')>=0){
                tipoMoneda = "USD";
            }else if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('EUR')>=0){
                tipoMoneda = "EUR";
            }
            i = 0;
            $.each(valorCuentas,function(s,item){
                if(item.label.indexOf(tipoMoneda)>=0){
                    cuentasNuevas[i] = item;
                    i++;
                }
            });
          cargar_selectPersonal("BetweenLinkedAccounts_NumCuentaDestino", cuentasNuevas,"Select","-2");
        }else{
            cargar_selectCuenta("BetweenLinkedAccounts_Accounts", valorCuentas,"Seleccione","-2",selected);
            if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('USD')>=0){
                tipoMoneda = "USD";
            }else if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('EUR')>=0){
                tipoMoneda = "EUR";
            }
            i = 0;
            $.each(valorCuentas,function(s,item){
                if(item.label.indexOf(tipoMoneda)>=0){
                    cuentasNuevas[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal("BetweenLinkedAccounts_NumCuentaDestino", cuentasNuevas,"Seleccione","-2");
        }


        $("#monedaBMLA").html(moneda);
        $("#BetweenLinkedAccounts_tagAvailableBalanceDate").html(fechaCierre);
        ParametrosPersonalesBMLACamposJSONData();

    }else{
        $("#BetweenLinkedAccounts_form").attr("style","display: none");
        $("#noBetweenLinkedAccounts_form").fadeIn("low");
        $("#noInfo_BetweenLinkedAccounts_form").html(respuesta);
    }


}

function  cargarData_BMLA(){
//    blanquearFormularios("BetweenLinkedAccounts_form");
    //$("#marco_trabajo").css("height","700px");
    //cargo todos los datos que van en la pagina
    $("#noBetweenLinkedAccounts_form").attr("style","display: none");
    infoPaginaBMLAJSONData();

}



function TransferBetweenLinkedAccountsGuardarJSONData(){
    var url = urlTransfersSalvarBMLA;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};
    var montoAux="";

    montoAux=($("#BetweenLinkedAccounts_Monto").get(0).value).replace(/,/g,'t');
    montoAux=(montoAux).replace(/\./g,',');
    montoAux=(montoAux).replace(/t/g,'.');

    $("#div_carga_espera").removeClass("oculto");
    ResumenTOB.account=$('#BetweenLinkedAccounts_Accounts :selected').html();
    ResumenTOB.accountCode=$("#BetweenLinkedAccounts_Accounts").get(0).value;
//    ResumenTOB.beneficiaryName=$("#BetweenLinkedAccounts_NombreBeneficiario").val();
    ResumenTOB.beneficiaryAccount=$("#BetweenLinkedAccounts_NumCuentaDestino").get(0).value;
//    ResumenTOB.beneficiaryEmail=$("#BetweenLinkedAccounts_beneficiaryEmail").val();

    //ResumenTOB.amount=$("#BetweenLinkedAccounts_Monto").val();

    ResumenTOB.amount=montoAux;
    ResumenTOB.recieverName=$("#BetweenLinkedAccounts_Concepto").val();

    jsonTransfers[0]= ResumenTOB;

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,TransferBetweenLinkedAccountsGuardarSuccess,null,null);
}

/**
 * Imprime div del html
 */

function printTransfers(){
    var miValue = $("#BetweenLinkedAccounts_NumCuentaDestino" ).val();
    $("#BetweenLinkedAccounts_NumCuentaDestino option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#BetweenLinkedAccounts_Accounts" ).val();
    $("#BetweenLinkedAccounts_Accounts option[value='"+miValue+"']").attr("selected",true);
    printPageElement('div_TRANSFERENCIA_INTERNA_MIS_CTAS');  //Print EDO CUENTA


}


function TransferBetweenLinkedAccountsGuardarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    var exito = result.code;
    var numref = result.numref;
    var mensaje = result.mensaje;
    mensaje = result.mensaje;
    makeTranfers = result.respuesta;
    idioma = result.idioma;
    $("#btn_resumen_aceptar_BMLA").attr("disabled",false);
    $("#div_carga_espera").addClass("oculto");
    if(exito!="0"){
        $("#BetweenLinkedAccounts_form").attr("style","display: ");
        $("#summaryBetweenLinkedAccounts").attr("style","display: none");
        $("#resumenBotones_BMLA_Finales").attr("style","display: none");
        $("#resumenBotones_BMLA").attr("style","display: ");

        if (makeTranfers!="1"){
            $("#mensaje_error").html(makeTranfers);

        } else{
            $("#mensaje_error").html(mensaje);
        }
        $("#div_mensajes_error").fadeIn("slow");
        infoPaginaBMLAJSONData();
    }else{
        $("#div_numref_BMLA").attr("style","display: ");
        $("#div_estatus_BMLA").attr("style","display: ");
        $("#tituloExitoso_BMLA").attr("style","display: ");
        $("#tituloResumen_BMLA").attr("style","display: none");

        $("#resumenBotones_BMLA_Finales").attr("style","display: ");
        $("#resumenBotones_BMLA").attr("style","display: none");
//        $("#txt_resultadoTransferTOB").html(result.mensaje);
        if ( $("#tipo_usuario_app").val()=="6"){
            var mensaje2="";
            if(idioma=="_us_en"){
                $("#status_BMLA").html("Input");
                mensaje2="This transfer instruction was successfully created; it must be Approved and Released by users with the appropriate permissions";
            }else{
                $("#status_BMLA").html("Cargada");
                mensaje2="Esta orden de transferencia fue cargada exitosamente, la misma debe ser Aprobada y Liberada por los usuarios con los permisos correspondientes";
            }
            $("#mensaje_error").html(mensaje2);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(idioma=="_us_en")
                $("#status_BMLA").html("In Process");
            else
                $("#status_BMLA").html("En Proceso");

        }
        $("#numRef_BMLA").html(numref);

//        $("#img_resultadoTransferBMLA").attr("src","../vbtonline/resources/images/exito.png");
    }


}






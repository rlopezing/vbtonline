var urlTransfersCargarBMLA="Transfers_cargarTranEntreMisCuentas.action";
var urlTransfersValidarBMLA="Transfers_validarCuentaDestino_TOC.action";
var urlTransfersSalvarBMLA="Transfers_saveBetweenMyAccounts.action";
var urlParametrosPersonalesBMLA="DesingBank_cargarParametrosPersonales.action";
var urlTransfersCalcularComision="Transfers_calcularComisionCancelacion.action";


var aMonedas="0";
var idioma = "";
var parametros="";
var parametrosBase="";
var tipoMoneda = "";
var usuario = "";
var moneda = "";
var cancelacion = "";
var units = "";
var comision = "";
var montoSACancelacion = "";
var codigoProducto = "";

$(document).ready(function(){
    $("#motivoCancelacionProductoBMLA").hide();
    $("#unitsBMLA").hide();
    $("#BetweenLinkedAccounts_Accounts").change(function(){
        if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('USD')>=0){
            tipoMoneda = "USD";
        }else if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('EUR')>=0){
            tipoMoneda = "EUR";
        }

/*        if ( $("#BetweenLinkedAccounts_Accounts option:selected").val() === "-2" ){
            $("#monedaBMLA").html("-");
        }*/

        codigoProducto = $("#BetweenLinkedAccounts_Accounts option:selected").attr("valor");

        // $("#monedaBMLA").html(tipoMoneda);
        $("#montoCuentaBMLA").hide();
        $('#transferencias_TAGCurrency').show();
        $("#monedaBMLA").html(createCurrency(tipoMoneda));
        $("#unidadesMostrar").hide();
        $("#cantidadUnidades").hide();
        $("#cancelacionProductorMostrar").hide();
        $("#montoMostrarBMLA").hide();
        $("#unidadesInputOcultoBMLA").hide();
        $("#montoComisionMostrarBMLA").hide();
        $("#motivoCancelacionProductoBMLA").hide();
        $("#unitsBMLA").hide();
        $("#descripcionTransferenciaBMLA").show();
        infoPaginaChangeBMLAJSONData();
    });

    $("#BetweenLinkedAccounts_NumCuentaDestino").change(function(){
        var mensaje="";

        if($("#BetweenLinkedAccounts_NumCuentaDestino").hasClass("error_campo"))
            $("#BetweenLinkedAccounts_NumCuentaDestino").removeClass("error_campo");

        if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("valor") != "SA"){
            if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("valor") == $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("valor") ){
                if ($("#BetweenLinkedAccounts_Accounts option:selected").val().split("| ")[1] == $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").val().split("| ")[1]) {
                    if(idioma=="_us_en")
                        mensaje = mensaje + "The 'From Product' and the 'To Product' are the same. Please try again."+"<br>";
                    else
                        mensaje = mensaje + "Los productos son los mismos, deben ser diferentes. Por favor intente nuevamente"+"<br>";
                    $("#BetweenLinkedAccounts_NumCuentaDestino").addClass("error_campo");
                }
            }
        }else{
            if ($("#BetweenLinkedAccounts_Accounts").get(0).value == $("#BetweenLinkedAccounts_NumCuentaDestino").get(0).value) {
                if(idioma=="_us_en")
                    mensaje = mensaje + "The 'From Product' and the 'To Product' are the same. Please try again."+"<br>";
                else
                    mensaje = mensaje + "Los productos son los mismos, deben ser diferentes. Por favor intente nuevamente"+"<br>";
                $("#BetweenLinkedAccounts_NumCuentaDestino").addClass("error_campo");
            }
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
        var montoOrigen="";
        var descripcion="";

        montoAux=($("#BetweenLinkedAccounts_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        if ($("#transferirUnidades").prop("checked")){
            $(".obligatorioCambioBMLA").each(function(){

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
        }else{
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
        }

        if ($("#productCancelation").prop("checked")){
            $(".obligatorioCancelacionBMLA").each(function () {
                if (Trim($("#" + this.id).val()) == "" || $("#" + this.id).val() == "-2") {
                    if (idioma == "_us_en")
                        mensaje = mensaje + "Required field - " + this.title + "<br>";
                    else
                        mensaje = mensaje + "Campo requerido - " + this.title + "<br>";
                    $("#" + this.id).addClass("error_campo");
                } else {
                    if ($("#" + this.id).hasClass("error_campo"))
                        $("#" + this.id).removeClass("error_campo");
                }
            })
        }

        if(mensaje=="") {
            if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("valor") != "SA"){
                if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("valor") == $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("valor") ){
                    if ($("#BetweenLinkedAccounts_Accounts option:selected").val().split("| ")[1] == $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").val().split("| ")[1]) {
                        if(idioma=="_us_en")
                            mensaje = mensaje + "The 'From Product' and the 'To Product' are the same. Please try again."+"<br>";
                        else
                            mensaje = mensaje + "Los productos son los mismos, deben ser diferentes. Por favor intente nuevamente"+"<br>";
                        $("#BetweenLinkedAccounts_NumCuentaDestino").addClass("error_campo");
                    }
                }
            }else{
                if ($("#BetweenLinkedAccounts_Accounts").get(0).value == $("#BetweenLinkedAccounts_NumCuentaDestino").get(0).value) {
                    if(idioma=="_us_en")
                        mensaje = mensaje + "The 'From Product' and the 'To Product' are the same. Please try again."+"<br>";
                    else
                        mensaje = mensaje + "Los productos son los mismos, deben ser diferentes. Por favor intente nuevamente"+"<br>";
                    $("#BetweenLinkedAccounts_NumCuentaDestino").addClass("error_campo");
                }
            }

            if ( Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra") ){
                if (idioma == "_us_en") {
                    mensaje = mensaje + "The amount exceeds the maximum available in your account " + currencyFormat($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra")) + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                }
                else{
                    mensaje = mensaje + "El monto excede el maximo disponible de su cuenta " + currencyFormat($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra")) + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                }

            }

            if ($("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1") != $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1")) {
                if (idioma == "_us_en")
                    mensaje = mensaje + "Transfer must be made between same currency accounts." + "<br>";
                else
                    mensaje = mensaje + "La transferencia debe ser entre cuentas de la misma moneda." + "<br>";
                $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
            }

            /** Validación del saldo mínimo transferencia Savings **/
            if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") == "Savings Account") {
                if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'))) {
                    if (idioma == "_us_en")
                        mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + currencyFormat(parametros.vbt_mminto)+ " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                    else
                        mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + currencyFormat(parametros.vbt_mminto)+ " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                    $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                }
            }else{
                if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra3")){
                    if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'))) {
                        if (idioma == "_us_en")
                            mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + currencyFormat(parametros.vbt_mminto)+ " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                        else
                            mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + currencyFormat(parametros.vbt_mminto) + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                        $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                    }
                }else{
                    if ($("#transferirMontoBMLA").prop("checked")){
                        if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'))) {
                            if (idioma == "_us_en")
                                mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + currencyFormat(parametros.vbt_mminto)+ " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                            else
                                mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + currencyFormat(parametros.vbt_mminto) + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") + "." + "<br>";
                            $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                        }
                    }else{
                        var precio = ( $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra5") * $("#BMLA_Unidades").get(0).value ).toFixed(2);
                        var parametroddd = Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'));
                        var paramvalido = ( parametroddd / $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra5") );

                        if ( precio < parametroddd ){
                            if (idioma == "_us_en")
                                mensaje = mensaje + "Invalid units. Please enter a number of units equal or greater of " + paramvalido + " Units" + "." + "<br>";
                            else
                                mensaje = mensaje + "Unidades inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + paramvalido + " Unidades" + "." + "<br>";
                            $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                        }
                        if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") < $("#BMLA_Unidades").get(0).value){
                            if (idioma == "_us_en")
                                mensaje = mensaje + "Invalid units. Please enter an Amount equal or below of " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") + " Units" + "." + "<br>";
                            else
                                mensaje = mensaje + "Unidades inv&aacute;lido. Por favor introduzca un valor menor o igual a " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") + " Unidades" + "." + "<br>";
                            $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                        }
                    }

                }

            }

            if ($("#productCancelation").prop("checked")) {
                mensaje = "";
            } else {
                if ($("#tipo_contrato_app").val() != "FC") {
                    if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd, ',').replace(',', '.')) || Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.'))) {
                        if (idioma == "_us_en") {

                            if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.')))
                                mensaje = mensaje + "The amount exceeds the maximum of " + parametros.vbt_mmto + $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1") +"." + "<br>";
                            else
                                mensaje = mensaje + "The amount exceeds the maximum of " + parametros.vbt_mmaxtd + $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1") + "." + "<br>";
                        } else {
                            if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd, ',').replace(',', '.')))
                                mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido " + parametros.vbt_mmto +$("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1")+  "." + "<br>";
                            else
                                mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido " + parametros.vbt_mmaxtd +$("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1") + "." + "<br>";
                        }

                        $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                    }
                } else {
                    if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.'))) {
                        if (idioma == "_us_en") {
                            mensaje = mensaje + "The amount exceeds the maximum of USD " + parametros.vbt_mmto + $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1") + "." + "<br>";
                        } else {
                            mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido " + parametros.vbt_mmto + $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1") + "." + "<br>";
                        }

                        $("#BetweenLinkedAccounts_Monto").addClass("error_campo");
                    }
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

            if ($("#productCancelation").prop("checked")){

                $("#div_cancelacion_BMLA").show();
                if(idioma=="_us_en")
                    $("#cancelacion_BMLA").html("Yes");
                else
                    $("#cancelacion_BMLA").html("Si");
            }else{
                $("#div_cancelacion_BMLA").hide();
            }

            $("#RBetweenLinkedAccounts_Accounts").html(quitarSaldo($('#BetweenLinkedAccounts_Accounts :selected').html()));
            $("#RBetweenLinkedAccounts_NumCuentaDestino").html(quitarSaldo($("#BetweenLinkedAccounts_NumCuentaDestino :selected").html()));
            if ($("#transferirUnidades").prop("checked")){
                $("#montoResumenBMLAOcultar").hide();
                $("#unidadesResumenBMLAOcultar").show();
                $("#RBetweenLinkedAccounts_Unidades").html($("#BMLA_Unidades").val());
            }else{
                $("#unidadesResumenBMLAOcultar").hide();
                $("#montoResumenBMLAOcultar").show();
                $("#RBetweenLinkedAccounts_Monto").html($("#BetweenLinkedAccounts_Monto").val()+" "+$("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1"));
            }

            if ($("#productCancelation").prop('checked')){
                descripcion = $("#BetweenLinkedAccounts_Motivo").val();
            }else{
                descripcion = $("#BetweenLinkedAccounts_Concepto").val();
            }
            if(descripcion ==""){
                $("#summaryBetweenLinkedAccounts #div_conceptp_BMLA").attr("style","display: none");
            }else{
                if ($("#productCancelation").prop('checked')){
                    // descripcion = $("#BetweenLinkedAccounts_Motivo").val();
                    $("#RBetweenLinkedAccounts_Concepto").html(descripcion);
                }else{
                    //  descripcion = $("#BetweenLinkedAccounts_Concepto").val();
                    $("#RBetweenLinkedAccounts_Concepto").html(descripcion);
                }
                $("#summaryBetweenLinkedAccounts #div_conceptp_BMLA").attr("style","display: ");
                $("#RToOtherClient_Concepto").html(descripcion);
            }

            $("#summaryBetweenLinkedAccounts #resumenBotones_BMLA").attr("style","display: ");
            $("#tituloExitoso_BMLA").attr("style","display:none ");
            $("#summaryBetweenLinkedAccounts #resumenBotones_BMLA_Finales").attr("style","display: none");
            $("#summaryBetweenLinkedAccounts #div_numref_BMLA").attr("style","display: none");
            $("#summaryBetweenLinkedAccounts #div_estatus_BMLA").attr("style","display: none");

            $("#summaryBetweenLinkedAccounts #tituloResumen_BMLA").attr("style","display: ");
            $("#summaryBetweenLinkedAccounts #sumaryConfirm_TOB2").attr("style","display: ");
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
        $("#productCancelation").prop("checked", false);
        $("#transferirUnidades").prop("checked", false);
        $("#unidadesMostrar").hide();
        $("#cantidadUnidades").hide();
        $("#cancelacionProductorMostrar").hide();
        $("#montoMostrarBMLA").hide();
        $("#unidadesInputOcultoBMLA").hide();
        $("#monedaBMLA").html(" ");
        $("#montoComisionMostrarBMLA").hide();
        $("#montoComisionBMLA").html(" ");
        $("#BetweenLinkedAccounts_Monto").attr('readOnly',false);
        $("#BMLA_Unidades").attr('readOnly',false);
        $("#motivoCancelacionProductoBMLA").hide();
        $("#descripcionTransferenciaBMLA").show();
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
        $("#productCancelation").prop("checked", false);
        $("#transferirUnidades").prop("checked", false);
        $("#unidadesMostrar").hide();
        $("#cantidadUnidades").hide();
        $("#cancelacionProductorMostrar").hide();
        $("#montoMostrarBMLA").hide();
        $("#unidadesInputOcultoBMLA").hide();
        $("#monedaBMLA").html(" ");
        $("#montoComisionMostrarBMLA").hide();
        $("#montoComisionBMLA").html(" ");
        $("#BetweenLinkedAccounts_Monto").attr('readOnly',false);
        $("#BMLA_Unidades").attr('readOnly',false);
        $("#motivoCancelacionProductoBMLA").hide();
        $("#descripcionTransferenciaBMLA").show();
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
        // printTransfers();
        $("#summaryBetweenLinkedAccountsForm").printThis({
            importCSS: true,            // import parent page css
            importStyle: true,         // import style tags
            printContainer: true,
        });
    });

    $("#productCancelation").click(function () {

        console.log('#####disclaimer-modal-transfers-others-client_v2');
        $("#disclaimer-modal-transfers-others-client_v2").modal({
            showClose: !1,
            modalClass: "notification-modal",
            fadeDuration: 100,
            blockerClass: "notification-modal--blocker",
        });

/*         if ($("#productCancelation").prop('checked')) {
            $("#motivoCancelacionProductoBMLA").show();
            $("#descripcionTransferenciaBMLA").hide();
            if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != "Savings Account"){
                if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3")==$("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra3")){
                    $("#montoInputMostrarBMLA").hide();
                    $("#unidadesInputOcultoBMLA").show();
                    $("#unidadesMostrar").hide();
                    $("#montoMostrarBMLA").hide();
                    $("#transferirMontoBMLA").hide();
                    $("#transferirMontoBMLA").prop("checked", false);
                    $("#transferirUnidades").prop("checked", true);
                    $("#BetweenLinkedAccounts_Monto").val(" ");
                    montoBMLA = $("#BMLA_Unidades").val( $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") );
                    montoBMLA = $("#BetweenLinkedAccounts_Monto").val(" ");

                    if (idioma == "_us_en") {
                        $("#cantidadUnidades").text("Units Available: " + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") );
                        $("#cantidadUnidades").show();
                    } else {
                        $("#cantidadUnidades").text("Unidades Disponibles: " + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") );
                        $("#cantidadUnidades").show();
                    }
                }else{
                    montoBMLA = $("#BetweenLinkedAccounts_Monto").val(formatCurrency($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra"),true,2,'.'));
                    montoBMLA = $("#BMLA_Unidades").val(" ");
                    $("#montoComisionMostrarBMLA").hide();
                    $("#montoComisionBMLA").html(" ");
                }
            }else{
                calcularComisionCancelacion( $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"), $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4"), $("#BetweenLinkedAccounts_Accounts option:selected").val().split(" |")[0], $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("valor"));

                $("#transferirMontoBMLA").prop("checked", false);
                $("#cantidadUnidades").hide();
                $("#montoInputMostrarBMLA").show();
                $("#unidadesInputOcultoBMLA").hide();
                $("#BMLA_Unidades").val(" ");
            }
            cancelacion = "S";
            $("#BetweenLinkedAccounts_Monto").attr('readOnly',true);
            $("#BMLA_Unidades").attr('readOnly',true);
        } else{
            $("#productCancelation").prop('checked', false);
            montoBMLA = $("#BetweenLinkedAccounts_Monto").val('');
            montoBMLA = $("#BMLA_Unidades").val('');
            $("#montoComisionMostrarBMLA").hide();
            $("#montoComisionBMLA").html(" ");
            cancelacion = "N";
            $("#BetweenLinkedAccounts_Monto").attr('readOnly',false);
            $("#BMLA_Unidades").attr('readOnly',true);
            $("#motivoCancelacionProductoBMLA").hide();
            $("#descripcionTransferenciaBMLA").show();
            $("#transferirMontoBMLA").prop("checked", true);

            if ( ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != "Savings Account") ) {
                if (($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4")) == ($("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra4"))) {
                    $("#unidadesMostrar").show();
                    $("#montoMostrarBMLA").show();
                    $("#transferirMontoBMLA").show();
                    $("#transferirMontoBMLA").prop("checked", true);
                } else {
                    $("#unidadesMostrar").hide();
                    $("#montoMostrarBMLA").hide();
                }
            }
        } */

    });

    $("#btn_TOB_volver_bt_2_4_continuar_v2").click(function () {
        $("#motivoCancelacionProductoBMLA").show();
        $("#descripcionTransferenciaBMLA").hide();
        if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != "Savings Account"){
            if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3")==$("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra3")){
                $("#montoInputMostrarBMLA").hide();
                $("#unidadesInputOcultoBMLA").show();
                $("#unidadesMostrar").hide();
                $("#montoMostrarBMLA").hide();
                $("#transferirMontoBMLA").hide();
                $("#transferirMontoBMLA").prop("checked", false);
                $("#transferirUnidades").prop("checked", true);
                $("#BetweenLinkedAccounts_Monto").val(" ");
                montoBMLA = $("#BMLA_Unidades").val( $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") );
                montoBMLA = $("#BetweenLinkedAccounts_Monto").val(" ");

                if (idioma == "_us_en") {
                    $("#cantidadUnidades").text("Units Available: " + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") );
                    $("#cantidadUnidades").show();
                } else {
                    $("#cantidadUnidades").text("Unidades Disponibles: " + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") );
                    $("#cantidadUnidades").show();
                }
            }else{
                montoBMLA = $("#BetweenLinkedAccounts_Monto").val(formatCurrency($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra"),true,2,'.'));
                montoBMLA = $("#BMLA_Unidades").val(" ");
                $("#montoComisionMostrarBMLA").hide();
                $("#montoComisionBMLA").html(" ");
            }
        }else{
            calcularComisionCancelacion( $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"), $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4"), $("#BetweenLinkedAccounts_Accounts option:selected").val().split(" |")[0], $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("valor"));

            $("#transferirMontoBMLA").prop("checked", false);
            $("#cantidadUnidades").hide();
            $("#montoInputMostrarBMLA").show();
            $("#unidadesInputOcultoBMLA").hide();
            $("#BMLA_Unidades").val(" ");
        }
        cancelacion = "S";
        $("#BetweenLinkedAccounts_Monto").attr('readOnly',true);
        $("#BMLA_Unidades").attr('readOnly',true);

        $("#productCancelation").prop('checked', true);

     //   $('.blocker').remove();
      //  $(".modal-backdrop").hide(); 
        
      //  $('#disclaimer-modal-transfers-others-client').modal('hide');

        //$('#disclaimer-modal-transfers-others-client').hide();
        $( "body" ).removeAttr( 'style' );
        $('.notification-modal--blocker').hide();
        $( "body" ).removeAttr( 'style' );

    });

    $("#btn_TOB_volver_bt_2_4_cancelar_v2").click(function () {
        $("#productCancelation").prop('checked', false);
        montoBMLA = $("#BetweenLinkedAccounts_Monto").val('');
        montoBMLA = $("#BMLA_Unidades").val('');
        $("#montoComisionMostrarBMLA").hide();
        $("#montoComisionBMLA").html(" ");
        cancelacion = "N";
        $("#BetweenLinkedAccounts_Monto").attr('readOnly',false);
        $("#BMLA_Unidades").attr('readOnly',true);
        $("#motivoCancelacionProductoBMLA").hide();
        $("#descripcionTransferenciaBMLA").show();
        $("#transferirMontoBMLA").prop("checked", true);

        $("#BetweenLinkedAccounts_Motivo").val('');
        $("#BetweenLinkedAccounts_Motivo").text('');
        $("#transferirUnidades").prop("checked", false);
        $("#BetweenLinkedAccounts_NumCuentaDestino").val('');



        if ( ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != "Savings Account") ) {
            if (($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4")) == ($("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra4"))) {
                $("#unidadesMostrar").show();
                $("#montoMostrarBMLA").show();
                $("#transferirMontoBMLA").show();
                $("#transferirMontoBMLA").prop("checked", true);
            } else {
                $("#unidadesMostrar").hide();
                $("#montoMostrarBMLA").hide();
            }
        }

        procancelTOC = "S";
        $( "body" ).removeAttr( 'style' );
        $("#productCancelation").prop('checked', false);
        $( "body" ).removeAttr( 'style' );


      //  $('.blocker').remove();
       // $(".modal-backdrop").hide(); 

       // $('#disclaimer-modal-transfers-others-client').modal('hide');

       //$('#disclaimer-modal-transfers-others-client').hide();
        $('.notification-modal--blocker').hide();

    });

    $("#unidadesMostrar").hide();
    $("#cantidadUnidades").hide();
    $("#cancelacionProductorMostrar").hide();
    $("#montoMostrarBMLA").hide();
    $("#unidadesInputOcultoBMLA").hide();
    $("#montoComisionMostrarBMLA").hide();
    $("#motivoCancelacionProductoBMLA").hide();

    $("#BetweenLinkedAccounts_NumCuentaDestino").change(function (){

        if ( ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != "Savings Account") ) {
            if (($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4")) == ($("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra4"))) {
                $("#unidadesMostrar").show();
                $("#montoMostrarBMLA").show();
                $("#transferirMontoBMLA").show();
                $("#transferirMontoBMLA").prop("checked", true);
            } else {
                $("#unidadesMostrar").hide();
                $("#montoMostrarBMLA").hide();
            }
        }

        if ($("#tipo_contrato_app").val() == "FC"){
            $("#cancelacionProductorMostrar").hide();
        }else{
            if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra6") <= "0"){
                if ( $("#BetweenLinkedAccounts_Accounts option:selected").val() != "-2"){
                    $("#cancelacionProductorMostrar").show();

                }else{
                    $("#cancelacionProductorMostrar").hide();
                }
            }else{
                $("#cancelacionProductorMostrar").hide();
            }
        }
    });

    $("#transferirUnidades").click(function (){

        var montoAux="";
        var mensaje="";

        montoAux=($("#BetweenLinkedAccounts_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        if ($("#transferirUnidades").prop('checked')){
            $("#montoInputMostrarBMLA").hide();

            $("#unitsBMLA").show();
            $("#currencyBMLA").hide();

            $("#unidadesInputOcultoBMLA").show();
            $("#unidadesMostrar").show();
            $("#montoMostrarBMLA").show();
            $("#transferirMontoBMLA").show();
            $("#transferirMontoBMLA").prop("checked", false);
            $("#BetweenLinkedAccounts_Monto").val(" ");
            if (idioma == "_us_en") {
                $("#cantidadUnidades").text("Units Available: " + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"));
                $("#cantidadUnidades").show();
            } else {
                $("#cantidadUnidades").text("Unidades Disponibles: " + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"));
                $("#cantidadUnidades").show();
            }

            if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2")) {
                if (mensaje = "") {
                    if (idioma == "_us_en") {
                        mensaje = (mensaje + "The Amount cant be greater than" + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"));
                    } else {
                        mensaje = (mensaje + "El Monto no puede ser mayor a" + " " + $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"));
                    }
                }
            }
        } else{
            $("#transferirMontoBMLA").prop("checked", false);
            $("#cantidadUnidades").hide();
            $("#montoInputMostrarBMLA").show();
            $("#unidadesInputOcultoBMLA").hide();
            $("#BMLA_Unidades").val(" ");

            $("#unitsBMLA").hide();
            $("#currencyBMLA").show();

        }
    });

    $("#BetweenLinkedAccounts_Accounts").change(function (){

        $("#cantidadUnidades").hide();
        $("#montoComisionMostrarBMLA").hide();
        $("#montoInputMostrarBMLA").show();
        $("#unidadesMostrar").hide();
        $("#productCancelation").prop("checked", false);
        $("#transferirUnidades").prop("checked", false);
        $("#unidadesInputOcultoBMLA").hide();
        $("#BMLA_Unidades").val(" ");
        $("#transferirMontoBMLA").hide();
        montoBMLA = $("#BetweenLinkedAccounts_Monto").val('');
        montoBMLA = $("#BMLA_Unidades").val('');



    });

    $("#transferirMontoBMLA").click(function (){

        if ($("#transferirMontoBMLA").prop('checked')){
            $("#montoInputMostrarBMLA").show();
            $("#unidadesMostrar").show();

            $("#unitsBMLA").hide();
            $("#currencyBMLA").show();

            $("#transferirUnidades").prop("checked", false);
            $("#unidadesInputOcultoBMLA").hide();
            $("#BMLA_Unidades").val(" ");
        }else{
            $("#BetweenLinkedAccounts_Monto").val(" ");
            $("#transferirMontoBMLA").prop("checked", false);
        }

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
    jsonTransfers[1]= $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4");
    jsonTransfers[2]= "D";

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,infoPaginaChangeBMLASuccess,null,null);
}

function infoPaginaChangeBMLASuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var valMonto = $("#BetweenLinkedAccounts_Monto").val();
    idioma = result.idioma;
    valorCuentas = result.cuentasTOB;
    var fondoMutualFM = result.cuentasFM;
    moneda = result.moneda;

    fechaCierre = result.fechaCierre;
    aMonedas =result.monedas;
    montoBMLA = result.valMonto;

    var mensaje =result.mensaje;
    var respuesta=result.respuesta;
    var selected= "-2";
    var cuentasNuevas = [];
    var cuentasFM = [];
    var i = 0;

    $.each(valorCuentas,function(s,item){
        if(item.label.indexOf(tipoMoneda)>=0){
            cuentasNuevas[i] = item;
            i++;
        }
    });

    cargar_selectCuenta("BetweenLinkedAccounts_NumCuentaDestino", cuentasNuevas,"Select","-2");

    console.log(result);
}

function infoPaginaBMLAJSONData(){
    var url = urlTransfersCargarBMLA;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= "OTHER";
    jsonTransfers[1]= "";
    jsonTransfers[2]= "O";


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


        //$("#monedaBMLA").html($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"));
        $('#montoCuentaBMLA').html($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2"));
        $("#BetweenLinkedAccounts_tagAvailableBalanceDate").html(fechaCierre);
        ParametrosPersonalesBMLACamposJSONData();

    }else{
        $("#BetweenLinkedAccounts_form").attr("style","display: none");
        $("#noBetweenLinkedAccounts_form").fadeIn("low");
        $("#noInfo_BetweenLinkedAccounts_form").html(respuesta);
    }
    //console.log(result);

}

function  cargarData_BMLA(){
    $("#noBetweenLinkedAccounts_form").attr("style","display: none");
    if ($("#tipo_contrato_app").val()=="FC"){
       $("#DIV_INFO_INTERNAS_FI").remove();
    }else{
        $("#DIV_INFO_INTERNAS_FC").remove();
    }
    infoPaginaBMLAJSONData();

}

function TransferBetweenLinkedAccountsGuardarJSONData(){
    var url = urlTransfersSalvarBMLA;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};
    var montoAux="";
    var moneda="";
    var checkFM="";
    var checkFMTo="";
    var checkUnits="";
    var procancel="";
    var concepto="";

    if ($("#transferirUnidades").prop('checked')){
        montoAux=($("#BMLA_Unidades").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        checkUnits="S"   /** ES UNIDADES **/

    }else{
        montoAux=($("#BetweenLinkedAccounts_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        checkUnits="N"   /** ES MONTO **/
    }

    if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('USD')>=0){
        moneda = "USD";
    }else if($("#BetweenLinkedAccounts_Accounts option:selected").text().indexOf('EUR')>=0){
        moneda = "EUR";
    }

    if ($("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra3") != "Savings Account"){
        checkFM = "S"   /** FM **/
    }else{
        checkFM = "N"   /** SAVING **/
    }

    if ($("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3") != "Savings Account"){
        checkFMTo = "S"   /** FM **/
    }else{
        checkFMTo = "N"   /** SAVING **/
    }

    if (cancelacion==""){
        cancelacion = "N";
    }

    if ($("#productCancelation").prop('checked')){
        procancel = "S";
        concepto = $("#BetweenLinkedAccounts_Motivo").val();
    }else{
        procancel = "N";
        concepto = $("#BetweenLinkedAccounts_Concepto").val();
    }



    $("#div_carga_espera").removeClass("oculto");
    ResumenTOB.account=$('#BetweenLinkedAccounts_Accounts :selected').html();
    ResumenTOB.accountCode=$("#BetweenLinkedAccounts_Accounts").get(0).value;
    ResumenTOB.beneficiaryAccount=$("#BetweenLinkedAccounts_NumCuentaDestino").get(0).value;
    ResumenTOB.amount=montoAux;
    ResumenTOB.recieverName= concepto;
    ResumenTOB.verificarFM = checkFM;
    ResumenTOB.productoTipo = $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra3");
    ResumenTOB.cuentaTipo = $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra3");
    ResumenTOB.verfUnits = checkUnits;
    ResumenTOB.verfDesdeCuenta = checkFMTo;
    ResumenTOB.codigoProductoDestino = $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra4");
    ResumenTOB.carteraDest = $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").val().split("| ")[1];
    ResumenTOB.codigoProductoOrigen = $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra4");
    ResumenTOB.siglasProducto = $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("valor");
    ResumenTOB.monedaBLA = $("#BetweenLinkedAccounts_NumCuentaDestino option:selected").attr("extra1");
    ResumenTOB.productCancelation = procancel;

    jsonTransfers[0]= ResumenTOB;

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,TransferBetweenLinkedAccountsGuardarSuccess,null,null);
}

function printTransfers(){
    var miValue = $("#BetweenLinkedAccounts_NumCuentaDestino" ).val();
    $("#BetweenLinkedAccounts_NumCuentaDestino option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#BetweenLinkedAccounts_Accounts" ).val();
    $("#BetweenLinkedAccounts_Accounts option[value='"+miValue+"']").attr("selected",true);
    // printPageElement('div_TRANSFERENCIA_INTERNA_MIS_CTAS');  //Print EDO CUENTA
    // printPageElement('BetweenLinkedAccounts_form');  //Print EDO CUENTA
    $("#BetweenLinkedAccounts_form").printThis({
        importCSS: true,            // import parent page css
        importStyle: true,         // import style tags
        printContainer: true,
    });


}

function TransferBetweenLinkedAccountsGuardarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    var exito = result.code;
    var numref = result.numref;
    var mensaje = result.mensaje;
    mensaje = result.mensaje;
    //makeTranfers = result.respuesta;
    idioma = result.idioma;
    $("#btn_resumen_aceptar_BMLA").attr("disabled",false);
    $("#div_carga_espera").addClass("oculto");
    if(exito!="0"){
        $("#BetweenLinkedAccounts_form").attr("style","display: ");
     //   $("#summaryBetweenLinkedAccounts").attr("style","display: none");
        $("#resumenBotones_BMLA_Finales").attr("style","display: none");
        $("#resumenBotones_BMLA").attr("style","display: ");

        $("#mensaje_error").html(mensaje);

        $("#div_mensajes_error").fadeIn("slow");
        $("#productCancelation").prop('checked', false);
        $("#cancelacionProductorMostrar").hide();
        infoPaginaBMLAJSONData();
    }else{
        $("#div_numref_BMLA").attr("style","display: ");
        $("#div_estatus_BMLA").attr("style","display: ");
        $("#tituloExitoso_BMLA").attr("style","display: ");
       // $("#tituloResumen_BMLA").attr("style","display: none");

        $("#sumaryConfirm_TOB2").attr("style","display: none");

       

        $("#resumenBotones_BMLA_Finales").attr("style","display: ");
        $("#resumenBotones_BMLA").attr("style","display: none");

        if ($("#tipo_contrato_app").val()=="FC"){
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
    }
}

function calcularComisionCancelacion(monto,codemp,cuenta,destino){
    var url = urlTransfersCalcularComision;
    var param={};
    var jsonTransfers=[];
    var tipomov = "";

    if (destino == "SA"){
        tipomov = "CID"
    }else{
        tipomov = "MFD"
    }

    jsonTransfers[0] = cuenta;
    jsonTransfers[1] = monto;
    jsonTransfers[2] = tipomov;
    jsonTransfers[3] = codemp;
    jsonTransfers[4] = "";
    jsonTransfers[5] = "";

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,calcularComisionCancelacionSuccess,null,null);
}

function calcularComisionCancelacionSuccess(originalRequest) {
    var result = originalRequest;
    var comisionAux = result.comisionCalculada;
    var montoComision = "";

    montoComision = $("#BetweenLinkedAccounts_Accounts option:selected").attr("extra2") - comisionAux;

    if (montoComision <= "0"){
        mensaje = "El monto a transferir tiene que ser mayor a 0"
        popupAlert(mensaje)
        $("#productCancelation").prop('checked',false);
        montoBMLA = $("#BetweenLinkedAccounts_Monto").val("");
        $("#cancelacionProductorMostrar").hide();
    }else{
        if (comisionAux == null){
            comisionAux = "0";
        }
        $("#montoComisionMostrarBMLA").show();

        $("#montoComisionBMLA").html(comisionAux + " " +$("#BetweenLinkedAccounts_Accounts option:selected").attr("extra1") );

        montoBMLA = $("#BetweenLinkedAccounts_Monto").val(formatCurrency(montoComision,true,2,'.'));
    }



  //  console.log(montoComision);

}

function currencyFormat(valor){
    return  Number.parseFloat(valor).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');  // 12,345.67
}
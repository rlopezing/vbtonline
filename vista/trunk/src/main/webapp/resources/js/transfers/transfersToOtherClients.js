var urlTransfersCargarOtherClient="Transfers_cargarToOtherClient.action";
var urlTransfersValidarOtherClient="Transfers_validarCuentaDestino_TOC.action";
var urlTransfersValidarCarteraOtherClient="Transfers_validarCarteraDestino_TOC.action";
var urlTransfersSalvarOtherClient="Transfers_saveToOtherClient.action";
var urlParametrosPersonalesTOC="DesingBank_cargarParametrosPersonales.action";
var urlTransfersCargarProductoOtherClient="Transfers_cargarProductosToOtherClient.action";

var idioma="";
var parametros="";
var parametrosBase="";
var usuario = "";
var tipoMoneda = "";
var procancelTOC = "";

$(document).ready(function(){

    $("#btn_home_TOC_final").click(function(){
        seleccionarOpcionMenu("home");
    });

    $("#productosMostrar").hide();

    $('#ToOtherClient_Accounts').change(function(){

        $("#checksMostrarOtrosClientes").hide();
        $("#unidadesMostrarOtrosClientes").hide();
        $("#montoMostrarOtrosClientes").hide();

        $("#transferirMontoOtrosClientes").hide();
        $("#transferirMontoOtrosClientes").prop("checked", false);
        $("#transferirUnidadesOtrosClientes").prop("checked", false);
        $("#productCancelationOtrosClientesVBT").prop("checked", false);
        $("#montoComisionMostrarTOC").hide();
        $("#ToOtherClient_Monto").val('');
        $("#ToOtherClient_Unidades").val('');
        $("#motivoCancelacionProducto").hide();
        $("#descripcionTransferencia").show();

        if ($("#tipo_contrato_app").val() == "FC"){
            $("#cancelacionProductorMostrar").hide();
        }else{
            if ($("#ToOtherClient_Accounts option:selected").attr("extra6") <= "0"){
                if ($("#ToOtherClient_Accounts option:selected").val() != "-2"){
                    $("#cancelarProductoMostrar").show();
                }else{
                    $("#cancelarProductoMostrar").hide();
                }
            }else{
                $("#cancelarProductoMostrar").hide();
            }
        }


        if($(this).val() != '-2' ) {

            if($("#ToOtherClient_Accounts option:selected").text().indexOf('USD')>=0){
                    // $("#monedaTOC").html("USD");
                    $("#monedaTOC").html(createCurrency("USD"));
            }else if($("#ToOtherClient_Accounts option:selected").text().indexOf('EUR')>=0){
                    // $("#monedaTOC").html("EUR");
                    $("#monedaTOC").html(createCurrency("EUR"));
            }
            infoProductosToOtherClientJSONData();
        }else
            $("#monedaTOC").html();
    });

    $("#ToOtherClient_btn_aceptar").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var montoAux="";
        var montoOrigen;

        montoAux=($("#ToOtherClient_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        if ($("#ToOtherClient_Producto option:selected").val() == "SA") {                   /** REVISAR VALIDACION **/

            $(".obligatorioTOC").each(function(){
                if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2") {
                    if(idioma=="_us_en")
                        mensaje=mensaje+"Required field - "+ this.title+"<br>";
                    else
                        mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            })
        }else{
            $(".obligatorioCambioTOC").each(function () {
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
        if ($("#productCancelationOtrosClientesVBT").prop("checked")){
            $(".obligatorioCancelacion").each(function () {
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
            if ($("#ToOtherClient_Producto option:selected").val() == "SA") {
                if (($("#ToOtherClient_NumCuentaDestino").get(0).value.length < 10)) {
                    if (idioma == "_us_en")
                        mensaje = mensaje + "Please enter a valid 'To account'" + "<br>The account must have 10 digits";
                    else
                        mensaje = mensaje + "Debe Indicar una cuenta valida" + "<br>La cuenta debe poseer 10 dígitos";
                    $("#ToOtherClient_NumCuentaDestino").addClass("error_campo");
                    invalido = "1";
                }
            }

            if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > $("#ToOtherClient_Accounts option:selected").attr("extra")) {
                if (idioma == "_us_en") {
                    mensaje = mensaje + "The amount exceeds the maximum available in your account " + currencyFormat($("#ToOtherClient_Accounts option:selected").attr("extra")) + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                } else {
                    mensaje = mensaje + "El monto excede el maximo disponible de su cuenta " + currencyFormat($("#ToOtherClient_Accounts option:selected").attr("extra")) + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                }

            }

            var valor1 = Trim($("#ToOtherClient_Accounts").get(0).value.substring(0, $("#ToOtherClient_Accounts").get(0).value.indexOf('|')));
            var valor2 = $("#ToOtherClient_NumCuentaDestino").get(0).value;
            if (Trim($("#ToOtherClient_Accounts").get(0).value.substring(0, $("#ToOtherClient_Accounts").get(0).value.indexOf('|'))) == Trim($("#ToOtherClient_NumCuentaDestino").get(0).value)) {
                if (idioma == "_us_en")
                    mensaje = mensaje + "The 'From Account' and the 'To Account' are the same. Please try again." + "<br>The 'To Account' must have 10 digits";
                else
                    mensaje = mensaje + "Las cuentas deben ser diferentes" + "<br>La Cuenta Destino debe poseer 10 dígitos";
                $("#ToOtherClient_NumCuentaDestino").addClass("error_campo");
                invalido = "1";
            }

            if ($("#ToOtherClient_Producto option:selected").attr("extra") == "S") {
                if ((Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.')))) {
                    if (idioma == "_us_en")
                        mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + currencyFormat(parametros.vbt_mminto)+ " " + $("#ToOtherClient_Producto option:selected").attr("extra1") + "." + "<br>";
                    else
                        mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + currencyFormat(parametros.vbt_mminto)+ " " + $("#ToOtherClient_Producto option:selected").attr("extra1") + "." + "<br>";
                    $("#ToOtherClient_Monto").addClass("error_campo");
                    invalido = "1";
                }
            } else {
                if ($("#ToOtherClient_Accounts option:selected").attr("valor") != $("#ToOtherClient_Producto option:selected").attr("extra3")) {
                    if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'))) {
                        if (idioma == "_us_en")
                            mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + currencyFormat(parametros.vbt_mminto)+ " " + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                        else
                            mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + currencyFormat(parametros.vbt_mminto)+ " " + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                        $("#ToOtherClient_Monto").addClass("error_campo");
                    }
                } else {
                    if ($("#transferirMontoOtrosClientes").prop("checked")) {
                        if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'))) {
                            if (idioma == "_us_en")
                                mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + currencyFormat(parametros.vbt_mminto)+ " " + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                            else
                                mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + currencyFormat(parametros.vbt_mminto)+ " " + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                            $("#ToOtherClient_Monto").addClass("error_campo");
                        }
                    } else {
                        var precio = ($("#ToOtherClient_Accounts option:selected").attr("extra5") * $("#ToOtherClient_Unidades").get(0).value).toFixed(2);
                        var parametroddd = Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'));
                        var paramvalido = ( parametroddd / $("#ToOtherClient_Accounts option:selected").attr("extra5") );

                        if ($("#ToOtherClient_Accounts option:selected").attr("valor") == $("#ToOtherClient_Producto option:selected").attr("extra3")) {
                            if (precio < parametroddd) {
                                if (idioma == "_us_en")
                                    mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + paramvalido + " Units" + "." + "<br>";
                                else
                                    mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + paramvalido + " Unidades" + "." + "<br>";
                                $("#ToOtherClient_Monto").addClass("error_campo");
                            }
                        } else {
                            if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < Number(unFormatCurrency(parametros.vbt_mminto, ',').replace(',', '.'))) {
                                if (idioma == "_us_en")
                                    mensaje = mensaje + "Invalid amount. Please enter an Amount equal or greater of " + parametros.vbt_mminto + " " + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                                else
                                    mensaje = mensaje + "Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a " + parametros.vbt_mminto + " " + $("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                                $("#ToOtherClient_Monto").addClass("error_campo");
                            }
                        }

                    }
                }
                if ($("#productCancelationOtrosClientesVBT").prop("checked")) {
                    mensaje = "";
                } else {
                    if ($("#tipo_contrato_app").val() != "FC") {
                        if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmaxtd, ',').replace(',', '.')) || Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.'))) {
                            if (idioma == "_us_en") {
                                if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.')))
                                    mensaje = mensaje + "The amount exceeds the maximum of " + parametros.vbt_mmto + $("#ToOtherClient_Producto option:selected").attr("extra1") +"." + "<br>";
                                else
                                    mensaje = mensaje + "The amount exceeds the maximum of " + parametros.vbt_mmaxtd + $("#ToOtherClient_Producto option:selected").attr("extra1") + "." + "<br>";
                            } else {
                                if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.')))
                                    mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido " + parametros.vbt_mmto +$("#ToOtherClient_Producto option:selected").attr("extra1")+  "." + "<br>";
                                else
                                    mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido " + parametros.vbt_mmaxtd +$("#ToOtherClient_Producto option:selected").attr("extra1") + "." + "<br>";
                            }

                            $("#ToOtherClient_Monto").addClass("error_campo");
                            invalido = "1";
                        }
                    } else {
                        if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > Number(unFormatCurrency(parametros.vbt_mmto, ',').replace(',', '.'))) {
                            if (idioma == "_us_en") {
                                mensaje = mensaje + "The amount exceeds the maximum of " + parametros.vbt_mmto + $("#ToOtherClient_Producto option:selected").attr("extra1") +"." + "<br>";
                            } else {
                                mensaje = mensaje + "Monto m&aacute;ximo permitido por transferencia excedido " + parametros.vbt_mmaxtd +$("#ToOtherClient_Producto option:selected").attr("extra1") + "." + "<br>";
                            }

                            $("#ToOtherClient_Monto").addClass("error_campo");
                            invalido = "1";
                        }
                    }
                }


                /**
                 * Validación del saldo mínimo en cuenta
                 */
                if ($("#productCancelationOtrosClientesVBT").prop('checked')) {
                    mensaje = mensaje + "";
                } else {
                    montoOrigen = Number(unFormatCurrency($('#ToOtherClient_Accounts :selected').attr("extra")));
                    if ((montoOrigen - Number(unFormatCurrency(montoAux, ',').replace(',', '.')) < parametros.minimun_balance)) {
                        if (idioma == "_us_en") {
                            mensaje = mensaje + "The amount exceeds the minimun of " + formatMoneda(parametros.minimun_balance) + $("#ToOtherClient_Accounts option:selected").attr("extra1") +"." + "<br>";
                        } else {
                            mensaje = mensaje + "Monto m&iacute;nimo en cuenta excedido " + formatMoneda(parametros.minimun_balance) +$("#ToOtherClient_Accounts option:selected").attr("extra1") + "." + "<br>";
                        }
                    }
                }

            }

            if (mensaje != "") {

                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            } else {
                if (isMail($.trim($("#ToOtherClient_beneficiaryEmail").get(0).value))) {
                    if ($("#ToOtherClient_beneficiaryEmail").hasClass("error_campo"))
                        $("#ToOtherClient_beneficiaryEmail").removeClass("error_campo");
                    $("#div_mensajes_error").fadeOut("fast");
                } else {
                    if (idioma == "_ve_es")
                        mensaje = "Por favor Indique un correo v&aacute;lido." + "<br>";
                    else
                        mensaje = "Please enter a valid Email Address." + "<br>";
                    $("#ToOtherClient_beneficiaryEmail").addClass("error_campo");
                    invalido = "1";
                }

                if (invalido == "0") {
                    if ($("#ToOtherClient_Producto option:selected").attr("extra") != "S") {

                        if (idioma == "_ve_es") {
                            $("#TOCdisclaimer p").html("<br><p align='center'><table><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer\" >La Transferia hacia este Producto no es inmediata.</label></td></tr><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer2\">¿Acepta que esta operacion no requiera soporte?</label></td></tr></table></p></br>");
                            $("#TOCdisclaimer").dialog({
                                resizable: false,
                                height: "auto",
                                width: 600,
                                // modal: true,
                                closeOnEscape: false,
                                position: {
                                    my: "center",
                                    at: "center"
                                },
                                buttons: {
                                    "Cancel": function () {
                                        $("#summaryToOtherClient").fadeOut("fast");
                                        $("#ToOtherClient_form").fadeIn("fast");
                                        $("#lastname2").addClass("error_campo");
                                        eliminarFile(false);
                                        $(this).dialog("close");
                                    },
                                    "Continue": function () {
                                        $("#div_mensajes_error").fadeOut("fast");
                                        TransfersValidateTOCJSONData();
                                        $(this).dialog("close");
                                    }
                                }
                            });
                        } else {
                            $("#TOCdisclaimer p").html("<br><p align='center'><table><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer\" >The Transfer to this Product is not immediate.</label></td></tr><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer2\">Do you accept that this operation does not require support?</label></td></tr></table></p></br>");
                            $("#TOCdisclaimer").dialog({
                                resizable: false,
                                height: "auto",
                                width: 600,
                                // modal: true,
                                closeOnEscape: false,
                                position: {
                                    my: "center",
                                    at: "center"
                                },
                                buttons: {
                                    "Cancel": function () {
                                        $("#summaryToOtherClient").fadeOut("fast");
                                        $("#ToOtherClient_form").fadeIn("fast");
                                        $("#lastname2").addClass("error_campo");
                                        eliminarFile(false);
                                        $(this).dialog("close");
                                    },
                                    "Continue": function () {
                                        $("#div_mensajes_error").fadeOut("fast");
                                        TransfersValidateTOCJSONData();
                                        $(this).dialog("close");
                                    }
                                }
                            });
                        }


                    } else {
                        $("#div_mensajes_error").fadeOut("fast");
                        TransfersValidateTOCJSONData();
                    }
                } else {
                    $("#mensaje_error").empty();
                    $("#mensaje_error").html(mensaje);
                    $("#div_mensajes_error").fadeIn("slow");
                }

            }
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
        $("#productCancelationOtrosClientesVBT").prop("checked", false);
        $("#transferirMontoOtrosClientes").prop("checked", false);
        $("#ToOtherClient_Unidades").prop("checked", false);
        $("#checksMostrarOtrosClientes").hide();
        $("#unidadesMostrarOtrosClientes").hide();
      //  $("#cantidadUnidadesOtrosClientes").hide();
        $("#unidadesInputOculto").hide();
        $("#montoMostrarOtrosClientes").hide();
        $("#cancelarProductoMostrar").hide();
        $("#numCuentaDestinoMostrar").show();
        $("#monedaTOC").html(" ");
        $("#montoComisionMostrarBMLA").hide();
        $("#montoComisionBMLA").html(" ");
        $("#ToOtherClient_Monto").attr('readOnly',false);
        $("#ToOtherClient_Unidades").attr('readOnly',false);
        $("#ToOtherClient_Monto").val('');
        $("#ToOtherClient_Unidades").val('');
        $("#motivoCancelacionProducto").hide();
        $("#descripcionTransferencia").show();
    });

    $("#btn_resumen_cancelar_TOC").click(function(){
        $("#summaryToOtherClient").fadeOut("fast");
        $("#ToOtherClient_form").fadeIn("fast");
    });

    $("#btn_resumen_aceptar_TOC").click(function(){

        $("#btn_resumen_aceptar_TOC").attr("disabled",true);

        if ($("#tipo_contrato_app").val()=="FC"){

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

        }else{

            if(idioma=="_us_en"){
                if(confirm("Are you sure that the information is correct?")){

                    $("#btn_cambiarMtdClave").click();
                    $("#btn_cambiarMtdClave").addClass("oculto");
                    $("#btnAceptar").addClass("oculto");

                    //Se llama a la pantalla de metodos de validacion
                    mainValidationMethods("transferenciaTercerosVBT");
                } else{
                    $("#btn_resumen_aceptar_TOC").attr("disabled",false);
                }
            }
            else{
                if(confirm("¿Est\u00e1 seguro de que los datos son correctos?")){
                    $("#btn_cambiarMtdClave").click();
                    $("#btn_cambiarMtdClave").addClass("oculto");
                    $("#btnAceptar").addClass("oculto");

                    //Se llama a la pantalla de metodos de validacion
                    mainValidationMethods("transferenciaTercerosVBT");
                } else{
                    $("#btn_resumen_aceptar_TOC").attr("disabled",false);
                }
            }
        }
        $("#productCancelationOtrosClientesVBT").prop("checked", false);
        $("#transferirMontoOtrosClientes").prop("checked", false);
        $("#ToOtherClient_Unidades").prop("checked", false);
        $("#checksMostrarOtrosClientes").hide();
        $("#unidadesMostrarOtrosClientes").hide();
     //   $("#cantidadUnidadesOtrosClientes").hide();
        $("#unidadesInputOculto").hide();
        $("#montoMostrarOtrosClientes").hide();
        $("#cancelarProductoMostrar").hide();
        $("#numCuentaDestinoMostrar").show();
        $("#monedaTOC").html(" ");
        $("#montoComisionMostrarTOC").hide();
        $("#montoComisionBMLA").html(" ");
        $("#btn_resumen_aceptar_TOC").attr("disabled",false);
        $("#ToOtherClient_Monto").attr('readOnly',false);
        $("#ToOtherClient_Unidades").attr('readOnly',false);
        $("#transferirUnidadesOtrosClientes").prop("checked", false);
        $("#motivoCancelacionProducto").hide();
        $("#descripcionTransferencia").show();
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
        $("#montoInputMostrar").show();
        infoPaginaToOtherClientJSONData();
    }else{

        $("#mensaje_error").empty();
        $("#mensaje_error").html(makeTranfers);
        $("#div_mensajes_error").fadeIn("slow");
    }
    });

    $("#btn_imprimir_TOC_final").click(function(){
        printOtherClientinVBT("summaryToOtherClientForm");
    });

    $("#otherClientinVBT_imprimir").click(function () {
        printOtherClientinVBT("ToOtherClient_form");
    })

    $("#TipoPersona").change(function(){
      /*Cuando se realiza el cambio de tipo de persona[Natural o juridico]
      se muestran o se coultan los campos correspondiente a los apellidos de
      los beneficiarios, y se coloca la clase que indica si son campos obligatorios
      */

        $("#lastname1").removeClass("error_campo");
        $("#lastname2").removeClass("error_campo");

          if($("#TipoPersona").val()=='NAT'){
              $("div[name='transferencias_apellidos']").removeClass("ocultoCampo");
              $("div[name='transferencias_apellidos2']").removeClass("ocultoCampo");
              $("#lastname1").addClass("obligatorioTOB");
            //  $("#lastname2").addClass("obligatorioTOB");
          } else {
              $("div[name='transferencias_apellidos']").addClass("ocultoCampo");
              $("div[name='transferencias_apellidos2']").addClass("ocultoCampo");
              $("#lastname1").removeClass("obligatorioTOB");
             // $("#lastname2").removeClass("obligatorioTOB");
          }
    });

    $('.only_alpha').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)||(e.which==0)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $('.only_alpha_punto').keypress(function (e) {
       // var regex = new RegExp("^[a-zA-Z.]+$");
        var regex = new RegExp("^[A-Za-z][A-Za-z0-9._]+[^._]$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)||(e.which==0)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $('.only_alpha_num_punto').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z0-9.,/?:+#$&=\-]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)||(e.which==0)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $('.only_alpha_num').keypress(function (e) {
        var regex = new RegExp("^[A-Za-z0-9]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)||(e.which==0)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $('.only_alpha_num_more').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z0-9.,/-]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)||(e.which==0)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $("#productCancelationOtrosClientesVBT").click(function () {

        if ($("#productCancelationOtrosClientesVBT").prop('checked')) {
            procancelTOC = "S";
            $("#ToOtherClient_Motivo").val('');
            $("#motivoCancelacionProducto").show();
            $("#descripcionTransferencia").hide();
            if ($("#ToOtherClient_Accounts option:selected").attr("valor") != "SA"){
                if ($("#ToOtherClient_Accounts option:selected").attr("valor") == $("#ToOtherClient_Producto").val()){
                    $("#unidadesInputOculto").show();
                    $("#montoInputMostrar").hide();
                    $("#checksMostrarOtrosClientes").hide();
                    $("#unidadesMostrarOtrosClientes").hide();
                    $("#montoMostrarOtrosClientes").hide();
                    $("#transferirMontoOtrosClientes").hide();
                    $("#transferirUnidadesOtrosClientes").prop('checked', true);
                    montoTOC = $("#ToOtherClient_Unidades").val($("#ToOtherClient_Accounts option:selected").attr("extra2"));
                    $("#cantidadUnidadesOtrosClientes").text("Units Available: " + " " + $("#ToOtherClient_Accounts option:selected").attr("extra2"));
                }else{
                    montoTOC = $("#ToOtherClient_Monto").val( formatCurrency($("#ToOtherClient_Accounts option:selected").attr("extra"),true,2,'.') );
                }
            }else{
                calcularComisionCancelacionTOC( $("#ToOtherClient_Accounts option:selected").attr("extra2"), $("#ToOtherClient_Accounts option:selected").attr("extra4"), $("#ToOtherClient_Accounts option:selected").val().split(" |")[0], $("#ToOtherClient_Producto option:selected").val());
            }
            $("#ToOtherClient_Monto").attr('readOnly',true);
            $("#ToOtherClient_Unidades").attr('readOnly',true);
            console.log('#####disclaimer-modal-transfers-others-client');
        } else{
            console.log('#######discheck');
            montoTOC = $("#ToOtherClient_Monto").val('');
            montoTOC = $("#ToOtherClient_Unidades").val('');
            $("#montoComisionMostrarTOC").hide();
            $("#montoComisionTOC").html(" ");
            $("#motivoCancelacionProducto").hide();
            $("#descripcionTransferencia").show();    
            if ($("#ToOtherClient_Accounts option:selected").attr("valor") != "SA"){
                if ( ( $("#ToOtherClient_Accounts option:selected").attr("valor") ) == ( $("#ToOtherClient_Producto option:selected").val() ) ){
                    //$("#montoInputMostrar").show();
                    $("#checksMostrarOtrosClientes").show();                
                    $("#unidadesMostrarOtrosClientes").show();
                    $("#montoMostrarOtrosClientes").show();
                    $("#transferirMontoOtrosClientes").show();
                    $("#transferirMontoOtrosClientes").prop("checked", false);
                    $("#transferirUnidadesOtrosClientes").prop("checked", true);
                    
                }
            }
            $("#ToOtherClient_Monto").attr('readOnly',false);
            $("#ToOtherClient_Unidades").attr('readOnly',false);
    
            procancelTOC = "S";
        } 

        console.log('#####disclaimer-modal-transfers-others-client');
        $("#disclaimer-modal-transfers-others-client").modal({
            showClose: !1,
            modalClass: "notification-modal",
            fadeDuration: 100,
            blockerClass: "notification-modal--blocker",
        });
    });

    $("#btn_TOB_volver_bt_2_4_continuar").click(function () {
        $( "body" ).removeAttr( 'style' );
        $('.notification-modal--blocker').hide();
        $( "body" ).removeAttr( 'style' );
    });

    $("#btn_TOB_volver_bt_2_4_cancelar").click(function () {
        if (!($("#productCancelationOtrosClientesVBT").prop('checked'))) {
            $("#productCancelationOtrosClientesVBT").prop('checked', true);
            procancelTOC = "S";
            $("#ToOtherClient_Motivo").val('');
            $("#motivoCancelacionProducto").show();
            $("#descripcionTransferencia").hide();
            if ($("#ToOtherClient_Accounts option:selected").attr("valor") != "SA"){
                if ($("#ToOtherClient_Accounts option:selected").attr("valor") == $("#ToOtherClient_Producto").val()){
                    $("#unidadesInputOculto").show();
                    $("#cantidadUnidadesOtrosClientes").show();
                    $("#montoInputMostrar").hide();
                    $("#checksMostrarOtrosClientes").hide();
                    $("#unidadesMostrarOtrosClientes").hide();
                    $("#montoMostrarOtrosClientes").hide();
                    $("#transferirMontoOtrosClientes").hide();
                    $("#transferirUnidadesOtrosClientes").prop('checked', true);
                    montoTOC = $("#ToOtherClient_Unidades").val($("#ToOtherClient_Accounts option:selected").attr("extra2"));
                    $("#cantidadUnidadesOtrosClientes").text("Units Available: " + " " + $("#ToOtherClient_Accounts option:selected").attr("extra2"));
                }else{
                    montoTOC = $("#ToOtherClient_Monto").val( formatCurrency($("#ToOtherClient_Accounts option:selected").attr("extra"),true,2,'.') );
                }
            }else{
                calcularComisionCancelacionTOC( $("#ToOtherClient_Accounts option:selected").attr("extra2"), $("#ToOtherClient_Accounts option:selected").attr("extra4"), $("#ToOtherClient_Accounts option:selected").val().split(" |")[0], $("#ToOtherClient_Producto option:selected").val());
            }
            $("#ToOtherClient_Monto").attr('readOnly',true);
            $("#ToOtherClient_Unidades").attr('readOnly',true);
            console.log('#####disclaimer-modal-transfers-others-client');
        } else{
            $("#productCancelationOtrosClientesVBT").prop('checked', false);
            console.log('#######discheck');
            montoTOC = $("#ToOtherClient_Monto").val('');
            montoTOC = $("#ToOtherClient_Unidades").val('');
            $("#montoComisionMostrarTOC").hide();
            $("#montoComisionTOC").html(" ");
            $("#motivoCancelacionProducto").hide();
            $("#descripcionTransferencia").show();    
            if ($("#ToOtherClient_Accounts option:selected").attr("valor") != "SA"){
                if ( ( $("#ToOtherClient_Accounts option:selected").attr("valor") ) == ( $("#ToOtherClient_Producto option:selected").val() ) ){
                    //$("#montoInputMostrar").show();
                    $("#checksMostrarOtrosClientes").show();                
                    $("#unidadesMostrarOtrosClientes").show();
                    $("#montoMostrarOtrosClientes").show();
                    $("#transferirMontoOtrosClientes").show();
                    $("#transferirMontoOtrosClientes").prop("checked", false);
                    $("#transferirUnidadesOtrosClientes").prop("checked", true);
                }
            }
            $("#ToOtherClient_Monto").attr('readOnly',false);
            $("#ToOtherClient_Unidades").attr('readOnly',false);
    
            procancelTOC = "S";
        } 

        $( "body" ).removeAttr( 'style' );
        $('.notification-modal--blocker').hide();
    });

    $("#checksMostrarOtrosClientes").hide();
    $("#cancelarProductoMostrar").hide();
    $("#unidadesMostrarOtrosClientes").hide();
   // $("#cantidadUnidadesOtrosClientes").hide();
    $("#unidadesInputOculto").hide();
    $("#montoMostrarOtrosClientes").hide();
    $("#montoComisionMostrarTOC").hide();
    $("#motivoCancelacionProducto").hide();
    $("#montoInputMostrar").show();

    $("#ToOtherClient_Producto").change(function (){

            if ( ($("#ToOtherClient_Accounts option:selected").attr("extra3") != "Savings Account") ){
                if ( ( $("#ToOtherClient_Accounts option:selected").attr("valor") ) == ( $("#ToOtherClient_Producto option:selected").val() ) ){
                    $("#checksMostrarOtrosClientes").show();
                    $("#unidadesMostrarOtrosClientes").show();
                    $("#montoMostrarOtrosClientes").show();
                    $("#transferirMontoOtrosClientes").show();
                    $("#transferirMontoOtrosClientes").prop("checked", true);
                }else{
                    $("#unidadesMostrarOtrosClientes").hide();
                    $("#montoMostrarOtrosClientes").hide();
                    $("#checksMostrarOtrosClientes").hide();
                    $("#numCuentaDestinoMostrar").show();
                }

                if ($("#ToOtherClient_Producto option:selected").attr("extra") != "S"){
                    $("#numCuentaDestinoMostrar").hide();
                }else{
                    $("#numCuentaDestinoMostrar").show();
                    $("#unidadesMostrarOtrosClientes").hide();
                    $("#montoMostrarOtrosClientes").hide();
                    $("#checksMostrarOtrosClientes").hide();

                }
            }else{
                if ($("#ToOtherClient_Producto option:selected").attr("extra") != "S"){
                    $("#numCuentaDestinoMostrar").hide();
                }else{
                    $("#numCuentaDestinoMostrar").show();
                    $("#unidadesMostrarOtrosClientes").hide();
                    $("#montoMostrarOtrosClientes").hide();
                    $("#checksMostrarOtrosClientes").hide();

                }
            }



        if ($("#ToOtherClient_Accounts").get(0).value == "-2"){
            $("#checksMostrarOtrosClientes").show();
            $("#unidadesMostrarOtrosClientes").hide();
            $("#numCuentaDestinoMostrar").show();
            $("#montoMostrarOtrosClientes").show();

        }


    });

    $("#transferirMontoOtrosClientes").click(function (){

        if ($("#transferirMontoOtrosClientes").prop('checked')){

            $("#montoInputMostrar").show();
            $("#transferirUnidadesOtrosClientes").show();
            $("#transferirUnidadesOtrosClientes").prop("checked", false);
            $("#unidadesInputOculto").hide();

        }else{
            $("#transferirMontoOtrosClientes").prop("checked", false);
        }
    });

    $("#transferirUnidadesOtrosClientes").click(function (){

        var montoAux="";
        var mensaje="";

        montoAux=($("#ToOtherClient_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        if ($("#transferirUnidadesOtrosClientes").prop('checked')) {
            $("#checksMostrarOtrosClientes").show();
            $("#montoMostrarOtrosClientes").show();
            $("#numCuentaDestinoMostrar").hide();

            $("#montoInputMostrar").hide();
            $("#unidadesInputOculto").show();
            $("#transferirMontoOtrosClientes").prop("checked", false);
                if ($("#ToOtherClient_Accounts option:selected").attr("extra3") != "Savings Account") {
                    if (idioma == "_us_en") {
                        $("#cantidadUnidadesOtrosClientes").text("Units Available: " + " " + $("#ToOtherClient_Accounts option:selected").attr("extra2"));
                        $("#cantidadUnidadesOtrosClientes").show();
                    } else {
                        $("#cantidadUnidadesOtrosClientes").text("Unidades Disponibles: " + " " + $("#ToOtherClient_Accounts option:selected").attr("extra2"));
                        $("#cantidadUnidadesOtrosClientes").show();
                    }

                    if (Number(unFormatCurrency(montoAux, ',').replace(',', '.')) > $("#ToOtherClient_Accounts option:selected").attr("extra2")) {
                        if (mensaje = "") {
                            if (idioma == "_us_en") {
                                mensaje = (mensaje + "The Amount cant be greater than" + " " + $("#ToOtherClient_Accounts option:selected").attr("extra2"));
                            } else {
                                mensaje = (mensaje + "El Monto no puede ser mayor a" + " " + $("#ToOtherClient_Accounts option:selected").attr("extra2"));
                            }
                        }
                    }
                }
        } else{
            $("#transferirMontoOtrosClientes").prop("checked", false);
          //  $("#cantidadUnidadesOtrosClientes").hide();
            $("#montoInputMostrar").show();
            $("#unidadesInputOculto").hide();
        }
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

function infoProductosToOtherClientJSONData(){
    var url = urlTransfersCargarProductoOtherClient;
    var param={};
    var jsonTransfersTOC=[];


    jsonTransfersTOC[0]= $("#ToOtherClient_Accounts option:selected").attr("extra1");
    jsonTransfersTOC[1]= $("#ToOtherClient_Accounts option:selected").attr("extra4");

    param.jsonTransfersTOC=JSON.stringify({"parametros":jsonTransfersTOC});

    sendServiceJSON(url,param,cargarproductosSuccess,null,null);
}

function cargarproductosSuccess(originalRequest){

    var result = originalRequest;
    var selected= "-2";
    valocuentasFM = result.cuentasFM;
    moneda = result.moneda;
    fechaCierre = result.fechaCierre;
    idioma = result.idioma;
    usuario = result.usuario;
    var mensaje =result.mensaje;
    var respuesta =result.respuesta;

    $("#productosMostrar").show();

    if(idioma=="_us_en") {
        cargar_selectCuenta("ToOtherClient_Producto", valocuentasFM, "Select", "-2", selected);
    }else {
        cargar_selectCuenta("ToOtherClient_Producto", valocuentasFM, "Seleccione", "-2", selected);
    }
    $("#productosMostrar").show();

    console.log(result);
}

function infoPaginaToOtherClientJSONData(){
    var url = urlTransfersCargarOtherClient;
    var param={};

    sendServiceJSON(url,param,infoPaginaToOtherClientSuccess,null,null);
}

function infoPaginaToOtherClientSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var selected= "-2";
    valorCuentas = result.cuentasTOB;
    valocuentasFM = result.cuentasFM;
    moneda = result.moneda;
    fechaCierre = result.fechaCierre;
    idioma = result.idioma;
    usuario = result.usuario;
    var mensaje =result.mensaje;
    var respuesta =result.respuesta;


    if (mensaje=="OK"){
        $("#ToOtherClient_form").fadeIn("fast");
        $("#summaryToOtherClient").fadeOut("fast");
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

        $("#montoInputMostrar").show();

        ParametrosPersonalesCamposJSONData();
        if(parametros==""){
            parametros = parametrosBase;
        }
        $("#productosMostrar").show();

        if(idioma=="_us_en"){
            cargar_selectCuenta("ToOtherClient_Accounts", valorCuentas,"Select","-2",parametros.account_num);
        }else {
            cargar_selectCuenta("ToOtherClient_Accounts", valorCuentas, "Seleccione", "-2", parametros.account_num);
        }

    /*    if(idioma=="_us_en") {
            cargar_selectCuenta("ToOtherClient_Producto", valocuentasFM, "Select", "-2", selected);
        }else {
            cargar_selectCuenta("ToOtherClient_Producto", valocuentasFM, "Seleccione", "-2", selected);
        }*/

        $("#monedaTOC").html($("#ToOtherClient_Accounts option:selected").attr("extra2"));
        $("#ToOtherClient_tagAvailableBalanceDate").html(fechaCierre);

        if(parametros.account_num!="-2"){
           $("#ToOtherClient_Accounts").val(parametros.account_num);
        }
    }else{
        $("#ToOtherClient_form").attr("style","display: none");
        $("#noToOtherClient_form").fadeIn("low");
        $("#noInfo_noToOtherClient_form").html(respuesta);
    }

    console.log(result);
}

function cargarData_toOtherClient(){
//    blanquearFormularios("ToOtherClient_form");
    //$("#marco_trabajo").css("height","700px");
    //cargo todos los datos que van en la pagina
    $("#noToOtherClient_form").attr("style","display: none");
    if ($("#tipo_contrato_app").val()=="FC"){
        $("#DIV_INFO_TERCEROS_FI").remove();
    }else{
        $("#DIV_INFO_TERCEROS_FC").remove();
    }
    infoPaginaToOtherClientJSONData();

}

function TransfersValidateTOCJSONData(){
    var url = urlTransfersValidarOtherClient;
    var param={};
    var jsonTransfers_ValidarCuenta_TOC=[];
    var tranferencia_FM="";

    if ($("#ToOtherClient_Producto option:selected").val() == "SA"){
        tranferencia_FM = "S"
    }else{
        tranferencia_FM = "N"
    }

    jsonTransfers_ValidarCuenta_TOC[0]= $("#ToOtherClient_NumCuentaDestino").val();
    jsonTransfers_ValidarCuenta_TOC[1]= $("#ToOtherClient_NumCarteraDestino").val();
    jsonTransfers_ValidarCuenta_TOC[2]= tranferencia_FM;
    jsonTransfers_ValidarCuenta_TOC[3]= $("#ToOtherClient_Producto option:selected").attr("extra4");
    jsonTransfers_ValidarCuenta_TOC[4]= $("#ToOtherClient_Producto option:selected").val();

    param.jsonTransfers_ValidarCuenta_TOC=JSON.stringify({"parametros":jsonTransfers_ValidarCuenta_TOC});

    sendServiceJSON(url,param,TransfersValidateTOCSuccess,null,null);
}

function TransfersValidateTOCSuccess(originalRequest){
    var result = originalRequest;
    var moneda = result.monedaTOC;
    var monedaFM = result.monedaTOCFM;
    var respuesta = result.respuesta;
    var descripcion = "";
    idioma=result.idioma;



    if(respuesta == "OK"){
       if ((($("#ToOtherClient_Accounts option:selected").text()).indexOf(moneda)>0) || (($("#ToOtherClient_Accounts option:selected").text()).indexOf(monedaFM)>0)){
           if ($('#ToOtherClient_Accounts option:selected').attr("valor")=="SA"){
               $("#RToOtherClient_Accounts").html(quitarSaldo($('#ToOtherClient_Accounts :selected').html()));
           }else{
               $("#RToOtherClient_Accounts").html($("#ToOtherClient_Accounts option:selected").attr("extra3")+" - (Client ID N° : "+$('#ToOtherClient_Accounts option:selected').val().split("| ")[1]);
           }


           if ($("#ToOtherClient_NumCuentaDestino").val()==""){
               $("#RToOtherClient_NumCuentaDestino").html($("#ToOtherClient_Producto option:selected").attr("extra2")+" - (Client ID N° : "+$("#ToOtherClient_NumCarteraDestino").val()+")");
           }else{
               $("#RToOtherClient_NumCuentaDestino").html($("#ToOtherClient_Producto option:selected").attr("extra2")+ " - " + $("#ToOtherClient_NumCuentaDestino").val()+" - (Client ID N° : "+$("#ToOtherClient_NumCarteraDestino").val()+")");
           }
            $("#RToOtherClient_NombreBeneficiario").html($("#ToOtherClient_NombreBeneficiario").val());
            $("#RToOtherClient_beneficiaryEmail").html($.trim($("#ToOtherClient_beneficiaryEmail").val()));

           if ($("#transferirUnidadesOtrosClientes").prop('checked')){
               $("#montoResumenMostraO").hide()
               $("#unidadesResumenMostraO").show()
               $("#RToOtherClient_Unidades").html($("#ToOtherClient_Unidades").val());
           }else{
               $("#montoResumenMostraO").show()
               $("#unidadesResumenMostraO").hide()
               $("#RToOtherClient_Monto").html($("#ToOtherClient_Monto").val()+" "+ $("#ToOtherClient_Accounts option:selected").attr("extra1"));
               $("#monedaTransferTOC").val($("#ToOtherClient_Accounts option:selected").attr("extra1"));
           }

           if ($("#productCancelationOtrosClientesVBT").prop('checked')){
               $("#cancelacionMostrarR").show()
               if(idioma=="_us_en"){
                   $("#RToOtherClient_cancelacionMostrarR").html("Yes");
               }else{
                   $("#RToOtherClient_cancelacionMostrarR").html("Si");
               }
           }else{
               $("#cancelacionMostrarR").hide()
           }

           $("#RToOtherClient_Producto").html($("#ToOtherClient_Producto").attr("extra2"));
            $("#summaryToOtherClient").fadeIn("fast");
            $("#ToOtherClient_form").fadeOut("fast");
            $("#div_mensajes_error").fadeOut("fast");

            if ($("#productCancelationOtrosClientesVBT").prop('checked')){
                descripcion = $("#ToOtherClient_Motivo").val();
            }else{
                descripcion = $("#ToOtherClient_Concepto").val();
            }

            if(descripcion ==""){
                $("#summaryToOtherClient #div_conceptp_TOC").attr("style","display: none");
            }else{
                if ($("#productCancelationOtrosClientesVBT").prop('checked')){
                    descripcion = $("#ToOtherClient_Motivo").val();
                }else{
                    descripcion = $("#ToOtherClient_Concepto").val();
                }
                $("#summaryToOtherClient #div_conceptp_TOC").attr("style","display: ");
                $("#RToOtherClient_Concepto").html(descripcion);
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
        if(moneda == "BENEFICIARY_ERROR") {
            mensaje = result.mensaje;
            $("#ToOtherClient_NumCuentaDestino").addClass("error_campo");
            $("#ToOtherClient_NumCarteraDestino").addClass("error_campo");
        }
        else {
            if(idioma=="_us_en")
                mensaje = "'To account' was not found. Please try again.";
            else
                mensaje = "No existe el n&uacute;mero de Cuenta Destino. Por favor indique un n&uacute;mero de Cuenta Destino correcto.";
        }

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
    var moneda="";
    var checkFM="";
    var checkFMTo="";
    var checkUnits="";
    var descripcion="";
    var cancelacionTOC="";

    if ($("#ToOtherClient_Monto").get(0).value == ""){
        montoAux=($("#ToOtherClient_Unidades").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        checkUnits="S"   /** ES UNIDADES **/
    }else{
        montoAux=($("#ToOtherClient_Monto").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        checkUnits="N"   /** ES MONTO **/
    }

    if ($("#productCancelationOtrosClientesVBT").prop('checked')){
        if ($("#ToOtherClient_Accounts option:selected").attr("valor") == "SA"){
            montoAux=($("#ToOtherClient_Monto").get(0).value).replace(/,/g,'t');
            montoAux=(montoAux).replace(/\./g,',');
            montoAux=(montoAux).replace(/t/g,'.');

            checkUnits="N"   /** ES MONTO **/
        }else{
            if ($("#ToOtherClient_Accounts option:selected").attr("valor") != $("#ToOtherClient_Producto option:selected").val()) {
                montoAux = ($("#ToOtherClient_Monto").get(0).value).replace(/,/g, 't');
                montoAux = (montoAux).replace(/\./g, ',');
                montoAux = (montoAux).replace(/t/g, '.');

                checkUnits = "N"   /** ES MONTO **/
            }else{
                montoAux=($("#ToOtherClient_Unidades").get(0).value).replace(/,/g,'t');
                montoAux=(montoAux).replace(/\./g,',');
                montoAux=(montoAux).replace(/t/g,'.');

                checkUnits="S"   /** ES UNIDADES **/
            }
        }
    }

    if($("#ToOtherClient_Accounts option:selected").text().indexOf('USD')>=0){
        moneda = "USD";
    }else if($("#ToOtherClient_Accounts option:selected").text().indexOf('EUR')>=0){
        moneda = "EUR";
    }

    if ($("#ToOtherClient_Producto option:selected").val() != "SA"){
        checkFM = "S"   /** FM **/
    }else{
        checkFM = "N"   /** SAVING **/
    }

    if ($("#ToOtherClient_Accounts option:selected").val() != "SA"){
        checkFMTo = "S"   /** FM **/
    }else{
        checkFMTo = "N"   /** SAVING **/
    }

    if ($("#productCancelationOtrosClientesVBT").prop('checked')){
        descripcion = $("#ToOtherClient_Motivo").val();
        cancelacionTOC = "S";
    }else{
        descripcion = $("#ToOtherClient_Concepto").val();
        cancelacionTOC = "N";
    }

    ResumenTOB.account=$('#ToOtherClient_Accounts :selected').html();
    ResumenTOB.accountCode=$("#ToOtherClient_Accounts").get(0).value;
    ResumenTOB.beneficiaryName=$("#ToOtherClient_NombreBeneficiario").val();
    ResumenTOB.beneficiaryAccount=$("#ToOtherClient_NumCuentaDestino").val();
    ResumenTOB.beneficiaryClientId=$("#ToOtherClient_NumCarteraDestino").val();
    ResumenTOB.beneficiaryEmail= $.trim($("#ToOtherClient_beneficiaryEmail").val());
    ResumenTOB.amount=montoAux;
    ResumenTOB.productoTipo = $("#ToOtherClient_Producto option:selected").attr("extra2");
    ResumenTOB.cuentaTipo = $("#ToOtherClient_Accounts option:selected").attr("extra3");
    ResumenTOB.recieverName= descripcion;
    ResumenTOB.verfUnits = checkUnits;
    ResumenTOB.verfDesdeCuenta = checkFMTo;
    ResumenTOB.verificarFM = checkFM;
    ResumenTOB.codigoProductoDestino = $("#ToOtherClient_Producto option:selected").attr("extra1");
    ResumenTOB.carteraDest = $("#ToOtherClient_NumCarteraDestino").val();
    ResumenTOB.codigoProductoOrigen = $("#ToOtherClient_Accounts option:selected").attr("extra4");
    ResumenTOB.siglasProducto = $("#ToOtherClient_Producto option:selected").attr("extra3");
    ResumenTOB.monedaBLA = $("#ToOtherClient_Accounts option:selected").attr("extra1");
    ResumenTOB.origen = $("#ToOtherClient_Accounts option:selected").attr("valor");
    ResumenTOB.productCancelation2 = cancelacionTOC;
    ResumenTOB.units = $("#ToOtherClient_Unidades").get(0).value;


    jsonTransfers[0]= ResumenTOB;

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON_sinc(url,param,TransferToOTherClientGuardarSuccess,null,null);
}

function TransferToOTherClientGuardarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    var exito = result.code;
    var numref = result.numref;
    idioma = result.idioma;
    makeTranfers = result.respuesta;

    $("#btn_resumen_aceptar_TOC").attr("disabled",false);
    if(exito!="0"){
        $("#ToOtherClient_form").attr("style","display: ");
        $("#summaryToOtherClient").attr("style","display: none");
        $("#resumenBotones_TOC_Finales").attr("style","display: none");
        $("#resumenBotones_TOC").attr("style","display: ");
        $("#mensaje_error").empty();
        if (makeTranfers!="1"){
            $("#mensaje_error").html(makeTranfers);

        } else{
            $("#mensaje_error").html(mensaje);
        }

        $("#div_mensajes_error").fadeIn("slow");
        infoPaginaToOtherClientJSONData();
    }else{
        $("#div_numref_TOC").attr("style","display: ");
        $("#div_estatus_TOC").attr("style","display: ");
        $("#tituloExitoso_TOC").attr("style","display: ");
        $("#tituloResumen_TOC").attr("style","display: none");

        $("#resumenBotones_TOC_Finales").attr("style","display: ");
        $("#resumenBotones_TOC").attr("style","display: none");

        if ($("#tipo_contrato_app").val()=="FC"){
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

function printOtherClientinVBT(section){

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

    // printPageElement('div_TRANSFERENCIA_INTERNA_TERCEROS');  //Print EDO CUENTA

    $("#"+section).printThis({
        importCSS: true,            // import parent page css
        importStyle: true,         // import style tags
        printContainer: true,
    });

}

function validarCuentaOrigen(){
    var mensaje="";

    if($("#ToOtherClient_NumCuentaDestino").hasClass("error_campo"))
        $("#ToOtherClient_NumCuentaDestino").removeClass("error_campo");

    if($("#ToOtherClient_NumCuentaDestino").get(0).value.length > 0){
        if (Trim($("#ToOtherClient_Accounts").get(0).value.substring(0, $("#ToOtherClient_Accounts").get(0).value.indexOf('|'))) == Trim($("#ToOtherClient_NumCuentaDestino").get(0).value)) {
            if(idioma=="_us_en")
                mensaje = mensaje + "The 'From Account' and the 'To Account' are the same. Please try again."+"<br>";
            else
                mensaje = mensaje + "Las cuentas deben ser diferentes"+"<br>";
            $("#ToOtherClient_NumCuentaDestino").addClass("error_campo");
        }
    }
    if(mensaje!=""){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }   else{

        if ($("#ToOtherClient_Producto option:selected").attr("extra3") != "SA"){
            if ($("#ToOtherClient_NumCarteraDestino").get(0).value.length > 0){
                validarCartera();
            }else{
                $("#ToOtherClient_NumCarteraDestino").prop('disabled', false);
            }
        }else{
            if( $("#ToOtherClient_NumCarteraDestino").get(0).value.length > 0 && $("#ToOtherClient_NumCarteraDestino").get(0).value.length > 0 )
                validarCartera();
            else
                $("#ToOtherClient_NumCarteraDestino").prop('disabled', false);
        }

    }
};

function validarCartera(){

    var mensaje="";
    var invalido="0";

        if($("#ToOtherClient_NumCarteraDestino").hasClass("error_campo"))
            $("#ToOtherClient_NumCarteraDestino").removeClass("error_campo");

        if ($("#ToOtherClient_Producto option:selected").attr("extra3") != "SA"){
            if ($("#ToOtherClient_NumCarteraDestino").get(0).value.length < 1){
                invalido="1";
            }else{
                invalido="0";
            }

        }else{
            if( $("#ToOtherClient_NumCarteraDestino").get(0).value.length < 1 || $("#ToOtherClient_NumCuentaDestino").get(0).value.length < 1  ){
                invalido="1";
            }
        }

        if(invalido=="0"){
            $("#div_mensajes_error").fadeOut("fast");
            TransfersValidateCarteraTOCJSONData();
        }
}

function TransfersValidateCarteraTOCJSONData(){
    var url = urlTransfersValidarCarteraOtherClient;
    var param={};
    var jsonTransfers_ValidarCuentaCartera_TOC=[];
    var tranferencia_FM="";

    if ($("#ToOtherClient_Producto option:selected").attr("extra") == "S"){
        tranferencia_FM = "S"
    }else{
        tranferencia_FM = "F"
    }

    jsonTransfers_ValidarCuentaCartera_TOC[0]= $("#ToOtherClient_NumCuentaDestino").val();
    jsonTransfers_ValidarCuentaCartera_TOC[1]= $("#ToOtherClient_NumCarteraDestino").val();
    jsonTransfers_ValidarCuentaCartera_TOC[2]= tranferencia_FM;
    jsonTransfers_ValidarCuentaCartera_TOC[3]= $("#ToOtherClient_Producto option:selected").val();

    param.jsonTransfers_ValidarCuentaCartera_TOC=JSON.stringify({"parametros":jsonTransfers_ValidarCuentaCartera_TOC});

    sendServiceJSON(url,param,TransfersValidateCarteraTOCSuccess,null,null);

}

function TransfersValidateCarteraTOCSuccess(originalRequest){

    var result = originalRequest;
    var cartera = result.carteraTOC;
    var respuesta = result.respuesta;
    idioma=result.idioma;

    if (respuesta == "OK"){
            $("#ToOtherClient_NumCarteraDestino").val(cartera);
            $("#ToOtherClient_NumCarteraDestino").removeClass("error_campo");
    }else{
        if (cartera == "BENEFICIARY_ERROR"){
            mensaje = result.mensaje;
            $("#ToOtherClient_NumCarteraDestino").addClass("error_campo");
        }
        else {
            if ($("#ToOtherClient_Producto option:selected").val() == "SA"){
                if (idioma == "_us_en")
                    mensaje = "Account number is not linked with the selected client ID.";
                else
                    mensaje = "Número de cuenta no corresponde con la cartera indicada.";

                $("#ToOtherClient_NumCarteraDestino").addClass("error_campo");
            }
        }

        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
}

function calcularComisionCancelacionTOC(monto,codemp,cuenta,destino){
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

    sendServiceJSON(url,param,calcularComisionCancelacionTOCSuccess,null,null);
}

function calcularComisionCancelacionTOCSuccess(originalRequest) {
    var result = originalRequest;
    var comisionAux = result.comisionCalculada;
    var montoComision="";

    montoComision = $("#ToOtherClient_Accounts option:selected").attr("extra2") - comisionAux;

    if (montoComision <= "0"){
        mensaje = "El monto a transferir tiene que ser mayor a 0"
        popupAlert(mensaje)
        $("#productCancelationOtrosClientesVBT").prop('checked',false);
        montoTOC = $("#ToOtherClient_Monto").val("");
        $("#cancelarProductoMostrar").hide();
    }else{
        if (comisionAux == null){
            comisionAux = "0";
        }
        $("#montoComisionMostrarTOC").show();

        $("#montoComisionTOC").html(comisionAux + " " +$("#ToOtherClient_Accounts option:selected").attr("extra1") );

        montoTOC = $("#ToOtherClient_Monto").val(formatCurrency(montoComision,true,2,'.'));
    }



  //  console.log(montoComision);

}
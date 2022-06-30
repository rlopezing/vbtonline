var urlTransfersCargar="Transfers_cargar.action";
var urlDirectorioCargar="Transfers_consultarDirectorio.action";
var urlDirectorioEditarCargar="Transfers_cargarDetalleDirectorio.action";
var urlDirectorioEditarSalvar="Transfers_editarDetalleDirectorio.action";
var urlDirectorioSalvarNuevo="Transfers_salvarNuevoDirectorio.action";
var urlDirectorioBorrar="Transfers_borrarDirectorio.action";

var idTemplate;
var datos="";
var idioma = "";
var noInfo = "";
$(document).ready(function(){



    $("#Template_createTemplate .inputFormulario_templateAdd" ).keypress(function() {
       $("#Template_btn_transferencia").attr("style","display:none");
       $("#Template_btn_TOB_aprobar").attr("style","display:none");
    });

    $("#Template_createTemplate .selectFormulario_templateAdd" ).change(function() {
        $("#Template_btn_transferencia").attr("style","display:none");
        $("#Template_btn_TOB_aprobar").attr("style","display:none");
    });

    $("#Template_createTemplate .inputFormulario_templateAdd" ).blur(function() {
        $("#Template_btn_transferencia").attr("style","display:none");
        $("#Template_btn_TOB_aprobar").attr("style","display:none");

    });





    $("#template_TAGCodigoBanco").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#template_TAGNumeroCuenta").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#Template_btn_transferencia").live('click', function () {
        //if($("#tipo_usuario_app").val()=="2"){
        origenMetodosValidacion="transferenciasOtrosBancos";
               if (makeTranfers=="1"){
                    hacerTransferencia();
                    if($("#div_TRANSFERENCIA_EXTERNA").css("display")=='none'){
                        $(".opcion_seleccionada").fadeOut("slow",function(){
                            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
                            $("#div_TRANSFERENCIA_EXTERNA").addClass("opcion_seleccionada");
                            $("#div_TRANSFERENCIA_EXTERNA").fadeIn("slow");
                        });
                    }
                    $("#Template_btn_back2").attr("style","display:''");
                    $("#div_mensajes_error").fadeOut("fast");
                   // Deshabilitar

                   $("#BankCode").attr("disabled", true);
                   $("#BankCode2").attr("readonly", true);
                   $("#BankCodeSWIFTtext").attr("readonly", true);
                   $("#BankCodeIBSWIFTtext").attr("readonly", true);
                   $("#NameBank").attr("readonly", true);
                   $("#AddressLineBank1").attr("readonly", true);
                   $("#AddressLineBank2").attr("readonly", true);
                   $("#AddressLineBank3").attr("readonly", true);
                   $("#CountryBank").attr("disabled", true);

                   $("#name").attr("readonly", true);
                   $("#AccountBank").attr("disabled", true);
                   $("#AccountNumber").attr("readonly", true);
                   $("#beneficiaryEmail").attr("readonly", true);
                   $("#AddressLine1").attr("readonly", true);
                   $("#AddressLine2").attr("readonly", true);
                   $("#AddressLine3").attr("readonly", true);
                   $("#TelephoneNumber").attr("readonly", true);
                   $("#Country").attr("disabled", true);
                   $("#BankCodeIB").attr("disabled", true);
                   $("#BankCodeIB2").attr("readonly", true);
                   $("#NameBankIB").attr("readonly", true);
                   $("#AddressLineBankIB1").attr("readonly", true);
                   $("#AddressLineBankIB2").attr("readonly", true);
                   $("#AddressLineBankIB3").attr("readonly", true);
                   $("#AccountNumberFFC").attr("readonly", true);
                   $("#NameFFC").attr("readonly", true);
                   $("#CountryBankIB").attr("disabled", true);
                   $("#AmmountAI").val("");
                   $("#ReceiverInformation").val("");
                   $("#MotivoAI").val("");
                   //
               }else{

                   $("#mensaje_error").empty();
                   $("#mensaje_error").html(makeTranfers);
                   $("#div_mensajes_error").fadeIn("slow");
               }
        /*}else{
            hacerTransferenciaFC();
            if($("#div_TRANSFERENCIA_EXTERNA_FC").css("display")=='none'){
                $(".opcion_seleccionada").fadeOut("slow",function(){
                    $(".opcion_seleccionada").removeClass("opcion_seleccionada");
                    $("#div_TRANSFERENCIA_EXTERNA_FC").addClass("opcion_seleccionada");
                    $("#div_TRANSFERENCIA_EXTERNA_FC").fadeIn("slow");
                });
            }
            $("#div_mensajes_error").fadeOut("fast");
        }      */

    });


    $("#Template_add").click(function(){
        $("#div_carga_espera").removeClass("oculto");
        $("#Templateform_7").attr("style","display:none");
        $("#Template_nombreTemplate").attr("disabled",false);
        $("#Template_createTemplate").fadeIn("fast");
        $("#Template_consulta").fadeOut("fast");
       // $("#Template_btn_transferencia").attr("style","display: none");
        $("#btnMakeTransfers").html("");
        $("#Template_createTemplate .selectFormulario_templateAdd").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#Template_createTemplate .inputFormulario_templateAdd").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
        datos = "";
        $("#accionTemplate").val("nuevo");
        $("#Template_btn_TOB_borrar").attr("style","display: none");
        //$("#marco_trabajo").css("height","750px");

       // cargarData_TemplateToOtherBank();
        newTemplateJSONData();

    });


    $("#Template_btn_TOB_aprobar").live('click', function () {
        idTemplate=$("#templateID").val();
        //Se llama a la pantalla de metodos de validacion
        mainValidationMethods("aprobarTemplate");

    });


    $("#Template_btn_TOB_aceptar").click(function(){
        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        $(".obligatorio_Template").each(function(){

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

        if(mensaje ==""){
            /*validaciones*/
            if(idioma =="_us_en"){
                if (!isAlphanumericWithSpace($("#Template_name").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_name").addClass("error_campo");
                    invalido ="1";
                }
                if ($("#Template_AccountBank").get(0).value  == "DD") {

                    if (Trim($("#Template_AccountNumber").get(0).value)== "" || !esSoloLetrasNumeros($("#Template_AccountNumber").get(0).value)) {
                        mensaje = mensaje + "Beneficiary: The Beneficiary Account Number is invalid."+"<br>"
                        $("#Template_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                } else {
                    if ($("#Template_AccountNumber").get(0).value.length < 15 || $("#Template_AccountNumber").get(0).value.length > 34 ) {

                        mensaje = mensaje + "Beneficiary: The IBAN format is invalid. Remember that the IBAN consists\nof a minimum of 15 and a maximum of 34 alphanumeric characters."+"<br>"
                        $("#Template_AccountNumber").addClass("error_campo");
                        invalido ="1";
                        auxIban=true;
                    }
                    if (auxIban!=true){
                        if (!isAlphabetic($("#Template_AccountNumber").get(0).value.substring(0, 2)) || !esSoloLetrasNumeros($("#Template_AccountNumber").get(0).value.substring(2, $("#Template_AccountNumber").get(0).value.length))) {
                            mensaje = mensaje + "Beneficiary: The IBAN format is invalid. \nPlease check."+"<br>"
                            $("#Template_AccountNumber").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }
                if (Trim($("#Template_AddressLine1").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary: Type the address of the Beneficiary of the funds"+"<br>"
                    $("#Template_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace($("#Template_AddressLine1").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLine2").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLine2").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_AddressLine2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLine3").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLine3").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_AddressLine3").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim( $("#Template_TelephoneNumber").get(0).value)!= "" && TlfNoValido($("#Template_TelephoneNumber").get(0).value)) {
                    mensaje = mensaje + "Beneficiary: The format for Phone Number is invalid."+"<br>"
                    $("#Template_TelephoneNumber").addClass("error_campo");
                    invalido ="1";
                }

                if ($("#Template_BankCode").get(0).value == "ABA") {
                    if ($("#Template_BankCode2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCode2").get(0).value)) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                } else if ($("#Template_BankCode").get(0).value == "SWIFT") {
                    if ($("#Template_BankCode2").get(0).value.length != 8 && $("#Template_BankCode2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#Template_BankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_BankCode2").get(0).value.substring(6, $("#Template_BankCode2").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#Template_BankCode2").get(0).value)== "" && Trim($("#Template_SwiftBankCode2").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary Bank: Indicate the code of Beneficiary Bank."+"<br>"
                    $("#Template_BankCode2").addClass("error_campo");
                    $("#Template_SwiftBankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (!esSoloLetrasNumeros($("#Template_BankCode2").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank´s Bank Code contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_NameBank").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary Bank: Indicate the name of the Beneficiary Bank"+"<br>"
                    $("#Template_NameBank").addClass("error_campo");
                    invalido ="1";
                }

                if (!isAlphanumericWithSpace($("#Template_AddressLineBank1").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_AddressLineBank1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLineBank2").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBank1").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_AddressLineBank2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLineBank3").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBank3").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_AddressLineBank3").addClass("error_campo");
                    invalido ="1";
                }
                if(Trim( $("#Template_BankCodeIB2").get(0).value)!= "" ||  Trim($("#Template_NameBankIB").get(0).value)!= "" ||
                    Trim($("#Template_AddressLineBankIB1").get(0).value)!= "" || Trim($("#Template_AddressLineBankIB2").get(0).value)!= "" ||
                    Trim($("#Template_AddressLineBankIB3").get(0).value)!= "" || Trim($("#Template_BankCodeIB").get(0).value) == "ACCOUNT"  ) {

                    if ($("#Template_BankCodeIB").get(0).value == "ABA") {
                        if ($("#Template_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCodeIB2").get(0).value)) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    } else if ($("#Template_BankCodeIB").get(0).value == "SWIFT") {
                        if ($("#Template_BankCodeIB").get(0).value.length != 8 && $("#Template_BankCodeIB").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                        if (!isAlphabetic($("#Template_BankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_BankCodeIB2").get(0).value.substring(6, $("#Template_BankCodeIB2").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (Trim($("#Template_BankCodeIB2").get(0).value)== "" && Trim($("#Template_SwiftBankCodeIB2").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros($("#Template_BankCodeIB2").get(0).value) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank´s Bank Code contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";

                    }
                    if (Trim($("#Template_NameBankIB").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Indicate the name of the Intermediary Bank."+"<br>"
                        $("#Template_NameBankIB").addClass("error_campo");
                        invalido ="1";
                    }

                    if (Trim($("#Template_AddressLineBankIB1").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Type the address of the Intermediary Bank."+"<br>"
                        $("#Template_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace($("#Template_AddressLineBankIB1").get(0).value) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#Template_AddressLineBankIB2").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBankIB2").get(0).value) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_AddressLineBankIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#Template_AddressLineBankIB3").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBankIB3").get(0).value) ) {
                        mensaje = mensaje + "Intermediary:The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_AddressLineBankIB3").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#Template_CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>"
                        $("#Template_CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }

                    if(Trim($("#Template_SwiftBankCode2").get(0).value) ==""){
                        $("#Template_SwiftBankCode2").val("");
                        $("#Template_SwiftBankCode").val("");
                    }


                }

                if(Trim( $("#Template_AccountNumberFFC").get(0).value)!= "" || Trim($("#Template_NameFFC").get(0).value)!= "" ) {

                    if (Trim($("#Template_AccountNumberFFC").get(0).value)== "") {
                        mensaje = mensaje + "For further credit: Type the Account Number for Further Credit to."+"<br>";
                        $("#Template_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros($("#Template_AccountNumberFFC").get(0).value) || $("#Template_AccountNumberFFC").get(0).value.length < 4) {
                        mensaje = mensaje + " For further credit to Account Number must consist of a minimum of 4 alpha-numeric characters and is not allowed to contain any invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#Template_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#Template_NameFFC").get(0).value)== "") {
                        mensaje = mensaje + " For further credit to: Type the name of the Account Holder of the account for Further Credit to."+"<br>";
                        $("#Template_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace( $("#Template_NameFFC").get(0).value) ) {
                        mensaje = mensaje + " For further credito to Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#Template_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                }
            }else{
                if (!isAlphanumericWithSpace($("#Template_name").get(0).value) ) {
                    mensaje = mensaje + "Beneficiario: El Nombre del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_name").addClass("error_campo");
                    invalido ="1";
                }
                if ($("#Template_AccountBank").get(0).value  == "DD") {

                    if (Trim($("#Template_AccountNumber").get(0).value)== "" || !esSoloLetrasNumeros($("#Template_AccountNumber").get(0).value)) {
                        mensaje = mensaje + "Beneficiario: N&uacute;mero de cuenta del Beneficiario inv&aacute;lido."+"<br>"
                        $("#Template_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                } else {
                    if ($("#Template_AccountNumber").get(0).value.length < 15 || $("#Template_AccountNumber").get(0).value.length > 34 ) {

                        mensaje = mensaje + "Beneficiario: Formato de IBAN inv&aacute;lido. Recuerde que el IBAN tiene\nal menos 15 y m&aacute;ximo 34 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_AccountNumber").addClass("error_campo");
                        invalido ="1";
                        auxIban=true;
                    }
                    if (auxIban!=true){
                        if (!isAlphabetic($("#Template_AccountNumber").get(0).value.substring(0, 2)) || !esSoloLetrasNumeros($("#Template_AccountNumber").get(0).value.substring(2, $("#Template_AccountNumber").get(0).value.length))) {
                            mensaje = mensaje + "Beneficiario:  Formato de IBAN inv&aacute;lido. \nPor favor revise."+"<br>"
                            $("#Template_AccountNumber").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }
                if (Trim($("#Template_AddressLine1").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiario: Indique la direcci&oacute;n del beneficiario de la transferencia."+"<br>"
                    $("#Template_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace($("#Template_AddressLine1").get(0).value) ) {
                    mensaje = mensaje + "Beneficiario:  La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLine2").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLine2").get(0).value) ) {
                    mensaje = mensaje + "Beneficiario: La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)"+"<br>"
                    $("#Template_AddressLine2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLine3").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLine3").get(0).value) ) {
                    mensaje = mensaje + "Beneficiario: La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)"+"<br>"
                    $("#Template_AddressLine3").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim( $("#Template_TelephoneNumber").get(0).value)!= "" && TlfNoValido($("#Template_TelephoneNumber").get(0).value)) {
                    mensaje = mensaje + "Beneficiario: Formato del N&uacute;mero de Tel&eacute;fono incorrecto."+"<br>"
                    $("#Template_TelephoneNumber").addClass("error_campo");
                    invalido ="1";
                }

                if ($("#Template_BankCode").get(0).value == "ABA") {
                    if ($("#Template_BankCode2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCode2").get(0).value)) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                } else if ($("#Template_BankCode").get(0).value == "SWIFT") {
                    if ($("#Template_BankCode2").get(0).value.length != 8 && $("#Template_BankCode2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#Template_BankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_BankCode2").get(0).value.substring(6, $("#Template_BankCode2").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#Template_BankCode2").get(0).value)== "" && Trim($("#Template_SwiftBankCode2").get(0).value)== "") {
                    mensaje = mensaje + "Banco Beneficiario: Indique el c&oacute;digo del banco beneficiario."+"<br>"
                    $("#Template_BankCode2").addClass("error_campo");
                    $("#Template_SwiftBankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (!esSoloLetrasNumeros($("#Template_BankCode2").get(0).value) ) {
                    mensaje = mensaje + "Banco Beneficiario: El C&oacute;digo del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_NameBank").get(0).value)== "") {
                    mensaje = mensaje + "Banco Beneficiario: Indique el nombre del banco beneficiario."+"<br>"
                    $("#Template_NameBank").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace($("#Template_AddressLineBank1").get(0).value) ) {
                    mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_AddressLineBank1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLineBank2").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBank1").get(0).value) ) {
                    mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_AddressLineBank2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#Template_AddressLineBank3").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBank3").get(0).value) ) {
                    mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_AddressLineBank3").addClass("error_campo");
                    invalido ="1";
                }


                if( Trim($("#Template_BankCodeIB2").get(0).value)!= "" ||  Trim($("#Template_NameBankIB").get(0).value)!= "" ||
                    Trim($("#Template_AddressLineBankIB1").get(0).value)!= "" || Trim($("#Template_AddressLineBankIB2").get(0).value)!= "" ||
                    Trim($("#Template_AddressLineBankIB3").get(0).value)!= "" || $("#Template_CountryBankIB").get(0).value != "-2" || $("#Template_BankCodeIB").get(0).value == "ACCOUNT"  ) {

                    if ($("#Template_BankCodeIB").get(0).value == "ABA") {
                        if ($("#Template_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCodeIB2").get(0).value)) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    } else if ($("#Template_BankCodeIB").get(0).value == "SWIFT") {
                        if ($("#Template_BankCodeIB2").get(0).value.length != 8 && $("#Template_BankCodeIB2").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                        if (!isAlphabetic($("#Template_BankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_BankCodeIB2").get(0).value.substring(6, $("#Template_BankCodeIB2").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (Trim($("#Template_BankCodeIB2").get(0).value)== "" && Trim($("#Template_SwiftBankCodeIB2").get(0).value)== "") {
                        mensaje = mensaje + "Intermediario: Indique el c&oacute;digo del banco intermediario."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros($("#Template_BankCodeIB2").get(0).value) ) {
                        mensaje = mensaje + "Intermediario: El C&oacute;digo del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";

                    }
                    if (Trim($("#Template_NameBankIB").get(0).value)== "") {
                        mensaje = mensaje + "Intermediario: Indique el nombre del banco intermediario."+"<br>"
                        $("#Template_NameBankIB").addClass("error_campo");
                        invalido ="1";
                    }

                    if (Trim($("#Template_AddressLineBankIB1").get(0).value)== "") {
                        mensaje = mensaje + "Intermediario: Indique la direcci&oacute;n del banco intermediario."+"<br>"
                        $("#Template_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace($("#Template_AddressLineBankIB1").get(0).value) ) {
                        mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                        $("#Template_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#Template_AddressLineBankIB2").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBankIB2").get(0).value) ) {
                        mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                        $("#Template_AddressLineBankIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#Template_AddressLineBankIB3").get(0).value)!= "" && !isAlphanumericWithSpace($("#Template_AddressLineBankIB3").get(0).value) ) {
                        mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                        $("#Template_AddressLineBankIB3").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#Template_CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediario: Indique el pa&iacute;s del banco intermediario."+"<br>"
                        $("#Template_CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if(Trim( $("#Template_AccountNumberFFC").get(0).value)!= "" || Trim( $("#Template_NameFFC").get(0).value)!= "" ) {

                    if (Trim($("#Template_AccountNumberFFC").get(0).value)== "") {
                        mensaje = mensaje + "Para Cr&eacute;dito Final a: Indique el N&uacute;mero de Cuenta para Cr&eacute;dito Final a."+"<br>";
                        $("#Template_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros($("#Template_AccountNumberFFC").get(0).value) || $("#Template_AccountNumberFFC").get(0).value.length < 4) {
                        mensaje = mensaje + " El N&uacute;mero de Cuenta para Cr&eacute;dito Final debe ser mayor de 4 caracteres alfanum&eacute;ricos y no debe contener caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#Template_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#Template_NameFFC").get(0).value)== "") {
                        mensaje = mensaje + " Para Cr&eacute;dito Final a: Indique el Nombre del Titular de la Cuenta para Cr&eacute;dito Final a."+"<br>";
                        $("#Template_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace( $("#Template_NameFFC").get(0).value) ) {
                        mensaje = mensaje + " El Nombre para Cr&eacute;dito Final tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#Template_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                }


            }
            /*validaciones*/
        }




        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//
        }else{
            if(isMail($("#Template_beneficiaryEmail").get(0).value)){
                if($("#Template_beneficiaryEmail").hasClass("error_campo"))
                    $("#Template_beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                resumenEditarTemplate();
            }else{
                if(idioma=="_us_en")
                  mensaje = "Beneficiary: Email"+"<br>"
                else
                  mensaje = "Beneficiario: Correo Electr&oacute;nico"+"<br>"
                $("#Template_beneficiaryEmail").addClass("error_campo");
                $("#mensaje_error").empty();
                if(idioma=="_us_en")
                  $("#mensaje_error").html("Invalid Format - "+mensaje);
                else
                  $("#mensaje_error").html("Formato invalido - "+mensaje);

                $("#div_mensajes_error").fadeIn("slow");
                $("#Template_beneficiaryEmail").focus();
            }
        }

    });

    $("#btn_resumen_aceptar_template").click(function(){
        salvarEditarTemplateJSONData();
    });

    $("#btn_resumen_cancelar_template").click(function(){
        $("#Template_createTemplate").fadeIn("fast");
        $("#summaryTemplate").fadeOut("fast");
    });


    $("#Template_btn_back").click(function(){

        $("#Template_createTemplate").fadeOut("fast");
        $("#Template_consulta").fadeIn("fast");
        $("#accionTemplate").val("");
        infoPaginaTemplateConsultaJSONData();

    });

    $("#Template_btn_TOB_cancelar").click(function(){
        $("#Template_createTemplate .selectFormulario_templateAdd").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#Template_createTemplate .inputFormulario_templateAdd").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        if(datos!=""){
            $("#Template_BankCode").val(datos.beneficiaryBankCodeType);
            $("#Template_BankCode2").val(datos.beneficiaryBankCodeNumber);
            $("#Template_NameBank").val(datos.beneficiaryBankName);
            $("#Template_AddressLineBank1").val(datos.beneficiaryBankAddress1);
            $("#Template_AddressLineBank2").val(datos.beneficiaryBankAddress2);
            $("#Template_AddressLineBank3").val(datos.beneficiaryBankAddress3);
            $("#Template_CountryBank").val(datos.beneficiaryBankCountryCode);

            $("#Template_name").val(datos.beneficiaryName);
            $("#Template_AccountBank").val(datos.accountCode);
            $("#Template_AccountNumber").val(datos.account);
            $("#Template_beneficiaryEmail").val(datos.beneficiaryEmail);
            $("#Template_AddressLine1").val(datos.beneficiaryAddress1);
            $("#Template_AddressLine2").val(datos.beneficiaryAddress2);
            $("#Template_AddressLine3").val(datos.beneficiaryAddress3);
            $("#Template_TelephoneNumber").val(datos.beneficiaryTelephone);
            $("#Template_Country").val(datos.beneficiaryCountryCode);

            $("#Template_BankCodeIB").val(datos.intermediaryBankCodeType);
            $("#Template_BankCodeIB2").val(datos.intermediaryBankCodeNumber);
            $("#Template_NameBankIB").val(datos.intermediaryBankName);
            $("#Template_AddressLineBankIB1").val(datos.intermediaryBankAddress1);
            $("#Template_AddressLineBankIB2").val(datos.intermediaryBankAddress2);
            $("#Template_AddressLineBankIB3").val(datos.intermediaryBankAddress3);
            $("#Template_CountryBankIB").val(datos.intermediaryBankCountryCode);

            $("#Template_AccountNumberFFC").val(datos.furtherCreditAccount);
            $("#Template_NameFFC").val(datos.furtherCreditName);


            $("#Template_nombreTemplate").val(datos.nombreTemplate);
        }




    });

    $("#Template_btn_TOB_borrar").click(function(){
        if(idioma=="_us_en"){
          if(confirm("Delete Template?")){
            infoTemplateBorrarJSONData();
          }
        }
        else {
          if(confirm("Esta seguro que quiere eliminar la plantilla?")){
            infoTemplateBorrarJSONData();
          }
        }
    });


});

function hacerTransferencia(){
    //$("#marco_trabajo").css("height","1200px");

        $("#BankCode").val($("#Template_BankCode").val());
        $("#BankCode2").val($("#Template_BankCode2").val());
        $("#NameBank").val($("#Template_NameBank").val());
        $("#AddressLineBank1").val($("#Template_AddressLineBank1").val());
        $("#AddressLineBank2").val($("#Template_AddressLineBank2").val());
        $("#AddressLineBank3").val($("#Template_AddressLineBank3").val());
        $("#CountryBank").val($("#Template_CountryBank").val());

        $("#name").val($("#Template_name").val());
        $("#AccountBank").val($("#Template_AccountBank").val());
        $("#AccountNumber").val($("#Template_AccountNumber").val());
        $("#beneficiaryEmail").val($("#Template_beneficiaryEmail").val());
        $("#AddressLine1").val($("#Template_AddressLine1").val());
        $("#AddressLine2").val($("#Template_AddressLine2").val());
        $("#AddressLine3").val($("#Template_AddressLine3").val());
        $("#TelephoneNumber").val($("#Template_TelephoneNumber").val());
        $("#Country").val($("#Template_Country").val());
        $("#BankCodeSWIFTtext").val($("#Template_SwiftBankCode2").val());


        $("#BankCodeIB").val($("#Template_BankCodeIB").val());
        $("#BankCodeIB2").val($("#Template_BankCodeIB2").val());
        $("#BankCodeIBSWIFTtext").val($("#Template_SwiftBankCodeIB2").val());

        $("#NameBankIB").val($("#Template_NameBankIB").val());
        $("#AddressLineBankIB1").val($("#Template_AddressLineBankIB1").val());
        $("#AddressLineBankIB2").val($("#Template_AddressLineBankIB2").val());
        $("#AddressLineBankIB3").val($("#Template_AddressLineBankIB3").val());
        $("#CountryBankIB").val($("#Template_CountryBankIB").val());

        $("#AccountNumberFFC").val($("#Template_AccountNumberFFC").val());
        $("#NameFFC").val($("#Template_NameFFC").val());
}

function hacerTransferenciaFC(){
    //$("#marco_trabajo").css("height","1200px");
    $("#FC_BankCode").val($("#Template_BankCode").val());
    $("#FC_BankCode2").val($("#Template_BankCode2").val());
    $("#FC_NameBank").val($("#Template_NameBank").val());
    $("#FC_AddressLineBank1").val($("#Template_AddressLineBank1").val());
    $("#FC_AddressLineBank2").val($("#Template_AddressLineBank2").val());
    $("#FC_AddressLineBank3").val($("#Template_AddressLineBank3").val());
    $("#FC_CountryBank").val($("#Template_CountryBank").val());

    $("#FC_name").val($("#Template_name").val());
    $("#FC_AccountBank").val($("#Template_AccountBank").val());
    $("#FC_AccountNumber").val($("#Template_AccountNumber").val());
    $("#FC_beneficiaryEmail").val($("#Template_beneficiaryEmail").val());
    $("#FC_AddressLine1").val($("#Template_AddressLine1").val());
    $("#FC_AddressLine2").val($("#Template_AddressLine2").val());
    $("#FC_AddressLine3").val($("#Template_AddressLine3").val());
    $("#FC_TelephoneNumber").val($("#Template_TelephoneNumber").val());
    $("#FC_Country").val($("#Template_Country").val());

    $("#FC_BankCodeIB").val($("#Template_BankCodeIB").val());
    $("#FC_BankCodeIB2").val($("#Template_BankCodeIB2").val());
    $("#FC_NameBankIB").val($("#Template_NameBankIB").val());
    $("#FC_AddressLineBankIB1").val($("#Template_AddressLineBankIB1").val());
    $("#FC_AddressLineBankIB2").val($("#Template_AddressLineBankIB2").val());
    $("#FC_AddressLineBankIB3").val($("#Template_AddressLineBankIB3").val());
    $("#FC_CountryBankIB").val($("#Template_CountryBankIB").val());

    $("#FC_AccountNumberFFC").val($("#Template_AccountNumberFFC").val());
    $("#FC_NameFFC").val($("#Template_NameFFC").val());

}

function infoTemplateBorrarJSONData(){
    var url = urlDirectorioBorrar;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#templateID").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,infoTemplateBorrarSuccess,null,null);
}

function infoTemplateBorrarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    respuesta = result.respuesta;
    idioma=result.idioma;
    if(respuesta=="OK"){
        if(idioma=="_us_en")
          alert("The template was successfully removed");
        else
          alert("Se ha eliminado satifactoriamente la plantilla");
        $("#Template_createTemplate").fadeOut("fast");
        $("#Template_consulta").fadeIn("fast");
        infoPaginaTemplateConsultaJSONData();
    }else{
        if(idioma=="_us_en")
            alert("The template was not removed");
        else
            alert("No se ha eliminado el directorio");
        $("#Template_createTemplate").fadeOut("fast");
        $("#Template_consulta").fadeIn("fast");
        infoPaginaTemplateConsultaJSONData();
    }
}

function seleccionarTemplateEditar(id){
    $("#Templateform_7").attr("style","display:none");
    $("#Template_createTemplate").fadeIn("fast");
    $("#Template_consulta").fadeOut("fast");
    popupTmp="NO OK";
    $("#Template_createTemplate .selectFormulario_templateAdd").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#Template_createTemplate .inputFormulario_templateAdd").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });
//    cargarData_TemplateToOtherBank();
    $("#accionTemplate").val("editar");
    $("#Template_btn_TOB_borrar").attr("style","display: ");
    //$("#marco_trabajo").css("height","750px");
    idTemplate=id;
    origenTemplate="TMP";
    $("#div_carga_espera").removeClass("oculto");
    infoPaginaTemplateJSONData();

}

function infoPaginaTemplateConsultaJSONData(){
    var url = urlDirectorioCargar;
    var param={};

    sendServiceJSON(url,param,infoPaginaTemplateConsultaSuccess,null,null);
}

function infoPaginaTemplateConsultaSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datosTabla = result.contenidoTabla_infoTest;
    idioma =result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    $("#accionTemplate").val("");

    crearTablaTmp('div_tabla_Templates_consulta','tabla_Templates_consulta',columnas,datosTabla);

    var oTable = $('#tabla_Templates_consulta').dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    $("#Template_createTemplate").attr("style","display: none");
    $("#Template_consulta").attr("style","display: ");
    $("#summaryTemplate").attr("style","display: none");
    $("#accionTemplate").val("");



}

function infoPaginaTemplateJSONData(){
    var url = urlTransfersCargar;
    var param={};
    var jsonTransfers=[];
    //Se envia para indicar que el origen es una plantilla
    jsonTransfers[0]= "TMP";

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,infoPaginaTemplateSuccess,null,null);
}

function infoPaginaTemplateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    PaisesBeneficiario = result.paisesBeneficiario;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    idioma =result.idioma;
    makeTranfers =result.respuesta;
    var codSwift = [];

    //Trsnfers
    if(valorCuentas!=null){
        cargar_selectPersonal2("Accounts", valorCuentas);
    }

    if(cuentas!=null){
      if (origenTemplate=="TMP"){
        cargar_selectPersonal2("Template_AccountBank", cuentas);
      }
      //Trsnfers
      cargar_selectPersonal2("AccountBank", cuentas);
    }

    if(paises!=null){
        if(idioma=="_us_en") {
            if (origenTemplate=="TMP"){
                cargar_selectPersonal("Template_Country", PaisesBeneficiario,"Select","-2");
                cargar_selectPersonal("Template_CountryBank", paises,"Select","-2");
                cargar_selectPersonal("Template_CountryBankIB", paises,"Select","-2");
            }

            //Trsnfers
            cargar_selectPersonal("Country", PaisesBeneficiario,"Select","-2");
            cargar_selectPersonal("CountryBank", paises,"Select","-2");
            cargar_selectPersonal("CountryBankIB", paises,"Select","-2");

        }else{
            if (origenTemplate=="TMP"){
                cargar_selectPersonal("Template_Country", PaisesBeneficiario,"Seleccione","-2");
                cargar_selectPersonal("Template_CountryBank", paises,"Seleccione","-2");
                cargar_selectPersonal("Template_CountryBankIB", paises,"Seleccione","-2");
            }

            //Trsnfers
            cargar_selectPersonal("Country", PaisesBeneficiario,"Seleccione","-2");
            cargar_selectPersonal("CountryBank", paises,"Seleccione","-2");
            cargar_selectPersonal("CountryBankIB", paises,"Seleccione","-2");
        }

    }
    if(codBankBen!=null){
        if(idioma=="_us_en"){
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            if (origenTemplate=="TMP"){
                cargar_selectPersonal("Template_BankCode", codSwift,"Select","-2");
                cargar_selectPersonal("Template_BankCodeIB", codSwift,"Select","-2");
            }
            //Trsnfers
            cargar_selectPersonal("BankCode", codSwift,"Select","-2");
            cargar_selectPersonal("BankCodeIB", codSwift,"Select","-2");

            codSwift = [];
            $.each(codBankBen, function (s, item) {
                if (item.value == "SWIFT")
                    codSwift[0] = item;
            });
            //cargar_selectPersonal("Template_SwiftBankCode", codSwift,"Select","-2");
            //cargar_selectPersonal("Template_SwiftBankCodeIB", codSwift,"Select","-2");
            //cargar_selectPersonal("BankCodeSWIFT", codSwift,"Select","-2");
            //cargar_selectPersonal("BankCodeIBSWIFT", codSwift,"Select","-2");
        }else{
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            if (origenTemplate=="TMP"){
                cargar_selectPersonal("Template_BankCode", codSwift,"Seleccione","-2");
                cargar_selectPersonal("Template_BankCodeIB", codSwift,"Seleccione","-2");
            }
            //Trsnfers
            cargar_selectPersonal("BankCode", codSwift,"Seleccione","-2");
            cargar_selectPersonal("BankCodeIB", codSwift,"Seleccione","-2");
            codSwift = [];
            $.each(codBankBen,function(s,item){
                if (item.value == "SWIFT")
                    codSwift[0] = item;
            });
            //cargar_selectPersonal("Template_SwiftBankCode", codSwift,"Seleccione","-2");
            //cargar_selectPersonal("Template_SwiftBankCodeIB", codSwift,"Seleccione","-2");
            //cargar_selectPersonal("BankCodeSWIFT", codSwift,"Seleccione","-2");
            //cargar_selectPersonal("BankCodeIBSWIFT", codSwift,"Seleccione","-2");

        }

    }
    infoTemplateEditarJSONData(idTemplate);
}

function infoTemplateEditarJSONData(id){
    var url = urlDirectorioEditarCargar;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= id;

    $("#templateID").val(id);

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,infoTemplateEditarSuccess,null,null);
}

function infoTemplateEditarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    datos = result.datos;
    //makeTranfers=result.respuesta;
    //$("#marco_trabajo").css("height","750px");

    $("#Template_BankCodeIB2OB").attr("style","display:none");
    $("#Template_NameBankIBOB").attr("style","display:none");
    $("#Template_AddressLineBankIB1OB").attr("style","display:none");
    $("#Template_CountryBankIBOB").attr("style","display:none");

    $("#btnMakeTransfers").html("");
    $("#btnAproveTemplate").html('');

    //Los Oculta en caso de que esten habilitados por otro template
    //$("#Template_btn_transferencia").attr("style","display:none ");


   /* if(datos.beneficiaryBankCodeType=="SWIFT"){
        $("#Template_SwiftBankCode").val(datos.beneficiaryBankCodeType);
        $("#Template_SwiftBankCode2").val(datos.beneficiaryBankCodeNumber);
        $("#Template_BankCode").val(datos.beneficiaryBankCodeTypeSwift);
        $("#Template_BankCode2").val(datos.beneficiaryBankCodeNumberSwift);
    }else{
        $("#Template_BankCode").val(datos.beneficiaryBankCodeType);
        $("#Template_BankCode2").val(datos.beneficiaryBankCodeNumber);
    } */


    $("#Template_BankCode").val(datos.beneficiaryBankCodeType);
    $("#Template_BankCode2").val(datos.beneficiaryBankCodeNumber);
    $("#Template_SwiftBankCode").val(datos.beneficiaryBankCodeTypeSwift);
    $("#Template_SwiftBankCode2").val(datos.beneficiaryBankCodeNumberSwift);




    $("#Template_NameBank").val(datos.beneficiaryBankName);
    $("#Template_AddressLineBank1").val(datos.beneficiaryBankAddress1);
    $("#Template_AddressLineBank2").val(datos.beneficiaryBankAddress2);
    $("#Template_AddressLineBank3").val(datos.beneficiaryBankAddress3);
    $("#Template_CountryBank").val(datos.beneficiaryBankCountryCode);

    $("#Template_name").val(datos.beneficiaryName);
    $("#Template_AccountBank").val(datos.accountCode);
    $("#Template_AccountNumber").val(datos.account);
    $("#Template_beneficiaryEmail").val(datos.beneficiaryEmail);
    $("#Template_AddressLine1").val(datos.beneficiaryAddress1);
    $("#Template_AddressLine2").val(datos.beneficiaryAddress2);
    $("#Template_AddressLine3").val(datos.beneficiaryAddress3);
    $("#Template_TelephoneNumber").val(datos.beneficiaryTelephone);
    $("#Template_Country").val(datos.beneficiaryCountryCode);


   /* if(datos.intermediaryBankCodeType=="SWIFT"){
        $("#Template_BankCodeIB").val(datos.intermediaryBankCodeTypeSwift);
        $("#Template_BankCodeIB2").val(datos.intermediaryBankCodeNumberSwift);
        $("#Template_SwiftBankCodeIB").val(datos.intermediaryBankCodeType);
        $("#Template_SwiftBankCodeIB2").val(datos.intermediaryBankCodeNumber);
    }else{
        $("#Template_BankCodeIB").val(datos.intermediaryBankCodeType);
        $("#Template_BankCodeIB2").val(datos.intermediaryBankCodeNumber);
    }  */



   /* if(datos.intermediaryBankCodeType=="SWIFT"){
        $("#Template_BankCodeIB").val(datos.intermediaryBankCodeTypeSwift);
        $("#Template_BankCodeIB2").val(datos.intermediaryBankCodeNumberSwift);
        $("#Template_SwiftBankCodeIB").val(datos.intermediaryBankCodeType);
        $("#Template_SwiftBankCodeIB2").val(datos.intermediaryBankCodeNumber);
    }else{
        $("#Template_BankCodeIB").val(datos.intermediaryBankCodeType);
        $("#Template_BankCodeIB2").val(datos.intermediaryBankCodeNumber);
    }  */

    $("#Template_BankCodeIB").val(datos.intermediaryBankCodeType);
    $("#Template_BankCodeIB2").val(datos.intermediaryBankCodeNumber);
    $("#Template_SwiftBankCodeIB").val(datos.intermediaryBankCodeTypeSwift);
    $("#Template_SwiftBankCodeIB2").val(datos.intermediaryBankCodeNumberSwift);


    $("#Template_NameBankIB").val(datos.intermediaryBankName);
    $("#Template_AddressLineBankIB1").val(datos.intermediaryBankAddress1);
    $("#Template_AddressLineBankIB2").val(datos.intermediaryBankAddress2);
    $("#Template_AddressLineBankIB3").val(datos.intermediaryBankAddress3);
    $("#Template_CountryBankIB").val(datos.intermediaryBankCountryCode);

    $("#Template_AccountNumberFFC").val(datos.furtherCreditAccount);
    $("#Template_NameFFC").val(datos.furtherCreditName);


    $("#Template_nombreTemplate").val(datos.nombreTemplate);
    $("#Template_nombreTemplate").attr("disabled",true);

    validarTransfer=datos.statusAprobacion;
    if (datos.statusAprobacion=="S") {
        if(idioma=="_us_en"){
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Make a Transfer" class="botonGrande 0000000040_0">');
        }else{
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Hacer Transferencia" class="botonGrande 0000000040_0">');
        }
        //$("#Template_btn_transferencia").attr("style","display: ");
    }
    else{
        $("#btnMakeTransfers").html("");
        if(idioma=="_us_en")
            $("#btnAproveTemplate").html('<input  type="button" id="Template_btn_TOB_aprobar" value="Approve" class="botonGrande 0000000070_6">');
        else
            $("#btnAproveTemplate").html('<input  type="button" id="Template_btn_TOB_aprobar" value="Aprobar" class="botonGrande 0000000070_6">');
        //$("#Template_btn_TOB_aprobar").removeClass("oculto");
    }

    //Verifica que no sea un cargador de firmas conjuntas
    if ($("#tipo_usuario_app").val()=="6"){
        if(idioma=="_us_en"){
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Make a Transfer" class="botonGrande 0000000040_0">');
        }else{
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Hacer Transferencia" class="botonGrande 0000000040_0">');
        }
        $("#btnAproveTemplate").remove();
    }


     ///Nuevo////
    $("#resultToOtherBank").attr("style","display: none");
    $("#createToOtherBank").attr("style","display: block");
    $("#summaryToOtherBank").attr("style","display: none");
    $("#mensaje_error").empty();
    $("#div_mensajes_error").attr("style","display: none");
    $("#div_numref_TOB").attr("style","display: none ");
    $("#div_estatus_TOB").attr("style","display: none");
    $("#tituloExitoso_TOB").attr("style","display: none");

    $("#div_carga_espera").addClass("oculto");
    $("#Templateform_7").attr("style","display:");

    if(paises==null){
        infoPaginaTransferTemplateJSONData();
    }


    ////NuEVO



    //if($("#tipo_usuario_app").val()=="2")
//      infoPaginaTransferTemplateJSONData();
   // else
    //  FC_infoPaginaTransferTemplateJSONData();

}

function resumenEditarTemplate(){

    //blanquearFormularios("summaryTemplate");
    $("#Template_createTemplate").fadeOut("fast");
    $("#summaryTemplate").fadeIn("fast");


    $("#Template_Rname").html($('#Template_name').val());
    $("#Template_RAccountNumber").html($('#Template_AccountBank :selected').html()+"   |   "+$("#Template_AccountNumber").get(0).value);
    $("#Template_RbeneficiaryEmail").html($("#Template_beneficiaryEmail").get(0).value);
    $("#Template_RAddressLine1").html($("#Template_AddressLine1").get(0).value);
    if(Trim($("#Template_AddressLine2").get(0).value) !=""){
        $("#div_Template_RAddressLine2").fadeIn("fast");
        $("#Template_RAddressLine2").html($("#Template_AddressLine2").get(0).value);
    }else{
        $("#div_Template_RAddressLine2").fadeOut("fast");
    }

    if(Trim($("#Template_AddressLine3").get(0).value)!=""){
        $("#div_Template_RAddressLine3").fadeIn("fast");
        $("#Template_RAddressLine3").html($("#Template_AddressLine3").get(0).value);
    }else{
        $("#div_Template_RAddressLine3").fadeOut("fast");
    }

    if(Trim($("#Template_TelephoneNumber").get(0).value) !=""){
        $("#div_Template_RTelephoneNumber").fadeIn("fast");
        $("#Template_RTelephoneNumber").html($("#Template_TelephoneNumber").get(0).value);
    }else{
        $("#div_Template_RTelephoneNumber").fadeOut("fast");
    }

    $("#Template_RCountry").html($('#Template_Country :selected').html());
    if($('#Template_BankCode2').val()!=""){
        $("#div_RBeneficiaryBankCode").fadeIn("fast");
        $("#Template_RBankCode").html($('#Template_BankCode :selected').html()+"   |   "+$('#Template_BankCode2').val());
    }else{
        $("#div_RBeneficiaryBankCode").fadeOut("fast");
    }
    if(Trim($("#Template_SwiftBankCode2").get(0).value) !=""){
        $("#div_RBeneficiarySwiftBankCode").fadeIn("fast");
        $("#Template_RSwiftBankCode").html("SWIFT   |   "+$('#Template_SwiftBankCode2').val());
    }else{
        $("#div_RBeneficiarySwiftBankCode").fadeOut("fast");
    }

    $("#Template_RNameBank").html($("#Template_NameBank").val());


    $("#Template_RAddressLineBank1").html($("#Template_AddressLineBank1").val());
    if(Trim($("#Template_AddressLineBank2").get(0).value) !=""){
        $("#div_Template_RAddressLineBank2").fadeIn("fast");
        $("#Template_RAddressLineBank2").html($("#Template_AddressLineBank2").get(0).value);
    }else{
        $("#div_Template_RAddressLineBank2").fadeOut("fast");
    }

    if(Trim($("#Template_AddressLineBank3").get(0).value) !=""){
        $("#div_Template_RAddressLineBank3").fadeIn("fast");
        $("#Template_RAddressLineBank3").html($("#Template_AddressLineBank3").get(0).value);
    }else{
        $("#div_Template_RAddressLineBank3").fadeOut("fast");
    }

    $("#Template_RCountryBank").html($('#Template_CountryBank :selected').html());

    if(Trim($("#Template_BankCodeIB2").get(0).value) !="" || Trim($("#Template_NameBankIB").get(0).value) !="" || Trim($("#Template_AddressLineBankIB1").get(0).value) !=""
         ||Trim( $("#Template_AddressLineBankIB2").get(0).value) !="" || Trim($("#Template_AddressLineBankIB3").get(0).value) !="" || $('#Template_CountryBankIB :selected').val() !="-2") {
        $("#div_Template_resumen_intermediary").fadeIn("fast");
    }else{
        $("#div_Template_resumen_intermediary").fadeOut("fast");
    }

    if(Trim($("#Template_BankCodeIB2").get(0).value) !=""){
        $("#div_Template_RBankCodeIB").fadeIn("fast");
        $("#Template_RBankCodeIB").html($('#Template_BankCodeIB :selected').html()+"|"+ Trim($("#Template_BankCodeIB2").get(0).value));
    }else{
        $("#div_Template_RBankCodeIB").fadeOut("fast");
    }


    if(Trim($("#Template_SwiftBankCodeIB2").get(0).value) !=""){
        $("#div_Template_RSwiftBankCodeIB").fadeIn("fast");
        $("#Template_RSwiftBankCodeIB").html("SWIFT   |   "+$('#Template_SwiftBankCodeIB2').val());
    }else{
        $("#div_Template_RSwiftBankCodeIB").fadeOut("fast");
    }

    if(Trim($("#Template_NameBankIB").get(0).value) !=""){
        $("#div_Template_RNameBankIB").fadeIn("fast");
        $("#Template_RNameBankIB").html($("#Template_NameBankIB").get(0).value);
    }else{
        $("#div_Template_RNameBankIB").fadeOut("fast");
    }


    if(Trim($("#Template_AddressLineBankIB1").get(0).value) !=""){
        $("#div_Template_RAddressLineBankIB1").fadeIn("fast");
        $("#Template_RAddressLineBankIB1").html(Trim($("#Template_AddressLineBankIB1").get(0).value));
    }else{
        $("#div_Template_RAddressLineBankIB1").fadeOut("fast");
    }

    if(Trim($("#Template_AddressLineBankIB2").get(0).value) !=""){
        $("#div_Template_RAddressLineBankIB2").fadeIn("fast");
        $("#Template_RAddressLineBankIB2").html(Trim($("#Template_AddressLineBankIB2").get(0).value));
    }else{
        $("#div_Template_RAddressLineBankIB2").fadeOut("fast");
    }

    if(Trim($("#Template_AddressLineBankIB3").get(0).value) !=""){
        $("#div_Template_RAddressLineBankIB3").fadeIn("fast");
        $("#Template_RAddressLineBankIB3").html(Trim($("#Template_AddressLineBankIB3").get(0).value));
    }else{
        $("#div_Template_RAddressLineBankIB3").fadeOut("fast");
    }

    if($('#Template_CountryBankIB :selected').val() !="-2"){
        $("#div_Template_RCountryBankIB").fadeIn("fast");
        $("#Template_RCountryBankIB").html($('#Template_CountryBankIB :selected').html());
    } else{
        $("#div_Template_RCountryBankIB").fadeOut("fast");
    }

    if(Trim($("#Template_AccountNumberFFC").get(0).value) !="" || Trim($("#Template_NameFFC").get(0).value) !=""){
        $("#div_Template_resumen_furtherCredit").fadeIn("fast");
    }else{
        $("#div_Template_resumen_furtherCredit").fadeOut("fast");
    }

    if(Trim($("#Template_AccountNumberFFC").get(0).value) !=""){
        $("#div_Template_RAccountNumberFFC").fadeIn("fast");
        $("#Template_RAccountNumberFFC").html(Trim($("#Template_AccountNumberFFC").get(0).value));
    }else{
        $("#div_Template_RAccountNumberFFC").fadeOut("fast");
    }

    if(Trim($("#Template_NameFFC").get(0).value) !=""){
        $("#div_Template_RNameFFC").fadeIn("fast");
        $("#Template_RNameFFC").html(Trim($("#Template_NameFFC").get(0).value));
    }else{
        $("#div_Template_RNameFFC").fadeOut("fast");
    }
    //Template_NameFFC
}







function limpiarResumen(){
    $("#Template_RAddressLine2").html("");
    $("#Template_RAddressLine3").html("");
    $("#Template_RTelephoneNumber").html("");
    $("#Template_RCountry").html("");
    $("#Template_RBankCode").html("");
    $("#Template_RNameBank").html("");
    $("#Template_RAddressLineBank2").html("");
    $("#Template_RAddressLineBank3").html("");
    $("#Template_RCountryBank").html("");
    $("#Template_RBankCodeIB").html("");
    $("#Template_RNameBankIB").html("");
    $("#Template_RAddressLineBankIB1").html("");
    $("#Template_RAddressLineBankIB2").html("");
    $("#Template_RAddressLineBankIB3").html("");
    $("#Template_RCountryBankIB").html("");
    $("#Template_RAccountNumberFFC").html("");
    $("#Template_RNameFFC").html("");

}



function salvarEditarTemplateJSONData(){
    var url;

    if($("#accionTemplate").val()=="editar")
     url = urlDirectorioEditarSalvar;
    else if($("#accionTemplate").val()=="nuevo")
      url = urlDirectorioSalvarNuevo;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};


    ResumenTOB.beneficiaryName=$("#Template_name").get(0).value
    ResumenTOB.beneficiaryAccount=$("#Template_AccountNumber").get(0).value;
    ResumenTOB.beneficiaryAccountBank=$("#Template_AccountBank").get(0).value
    ResumenTOB.beneficiaryEmail=$("#Template_beneficiaryEmail").get(0).value;
    ResumenTOB.beneficiaryAddress1=$("#Template_AddressLine1").get(0).value;
    ResumenTOB.beneficiaryAddress2=$("#Template_AddressLine2").get(0).value;
    ResumenTOB.beneficiaryAddress3=$("#Template_AddressLine3").get(0).value;
    ResumenTOB.beneficiaryTelephone=$("#Template_TelephoneNumber").get(0).value;
    //ResumenTOB.beneficiaryCountry=$('#Template_Country :selected').html();
    //ResumenTOB.beneficiaryCountry=$("#Template_Country").val();
    if($("#Template_Country").val()=="-2"){
        ResumenTOB.beneficiaryCountry='';
    }else{
        ResumenTOB.beneficiaryCountry=$("#Template_Country").val();
    }



    ResumenTOB.beneficiaryCountryCode=$("#Template_Country").get(0).value;
   // ResumenTOB.beneficiaryBankCodeType=$('#Template_BankCode :selected').html();
   //ResumenTOB.beneficiaryBankCodeType=$("#Template_BankCode").val();
    if($("#Template_BankCode").val()=="-2"){
        ResumenTOB.beneficiaryBankCodeType='';
    }else{
        ResumenTOB.beneficiaryBankCodeType=$("#Template_BankCode").val();
    }

    ResumenTOB.beneficiaryBankCodeNumber=$("#Template_BankCode2").get(0).value;
    if($("#Template_SwiftBankCode2").get(0).value != ""){
        ResumenTOB.beneficiaryBankCodeTypeSwift="SWIFT";
        ResumenTOB.beneficiaryBankCodeNumberSwift=$("#Template_SwiftBankCode2").get(0).value;
    }else{
        ResumenTOB.beneficiaryBankCodeTypeSwift="";
        ResumenTOB.beneficiaryBankCodeNumberSwift="";
    }

    ResumenTOB.beneficiaryBankName=$("#Template_NameBank").get(0).value;
    ResumenTOB.beneficiaryBankAddress1=$("#Template_AddressLineBank1").get(0).value;
    ResumenTOB.beneficiaryBankAddress2=$("#Template_AddressLineBank2").get(0).value;
    ResumenTOB.beneficiaryBankAddress3=$("#Template_AddressLineBank3").get(0).value;
    //ResumenTOB.beneficiaryBankCountry=$('#Template_CountryBank :selected').html();
    //ResumenTOB.beneficiaryBankCountry=$("#Template_CountryBank").val();
    if($("#Template_CountryBank").val()=="-2"){
        ResumenTOB.beneficiaryBankCountry='';
    }else{
        //ResumenTOB.beneficiaryBankCountry=$("#Template_CountryBank").val();
        ResumenTOB.beneficiaryBankCountry=$('#Template_CountryBank :selected').html();
    }

    ResumenTOB.beneficiaryBankCountryCode=$("#Template_CountryBank").get(0).value;

    if($("#Template_BankCodeIB").val()=="-2"){
        ResumenTOB.intermediaryBankCodeType='';
    }else{
        ResumenTOB.intermediaryBankCodeType=$("#Template_BankCodeIB").val();
    }

    /*if($("#Template_SwiftBankCodeIB").val()=="-2"){
        ResumenTOB.intermediaryBankCodeTypeSwift="";
    }else{
        ResumenTOB.intermediaryBankCodeTypeSwift=$("#Template_SwiftBankCodeIB").val();
    } */

    ResumenTOB.intermediaryBankCodeNumber=$("#Template_BankCodeIB2").get(0).value;
    if($("#Template_SwiftBankCodeIB2").get(0).value!=""){
        ResumenTOB.intermediaryBankCodeTypeSwift="SWIFT";
        ResumenTOB.intermediaryBankCodeNumberSwift=$("#Template_SwiftBankCodeIB2").get(0).value;
    }else{
        ResumenTOB.intermediaryBankCodeTypeSwift="";
        ResumenTOB.intermediaryBankCodeTypeSwift="";
    }






    ResumenTOB.intermediaryBankName=$("#Template_NameBankIB").get(0).value;
    ResumenTOB.intermediaryBankAddress1=$("#Template_AddressLineBankIB1").get(0).value;
    ResumenTOB.intermediaryBankAddress2=$("#Template_AddressLineBankIB2").get(0).value;
    ResumenTOB.intermediaryBankAddress3=$("#Template_AddressLineBankIB3").get(0).value;
    if($("#Template_CountryBankIB").get(0).value== "-2"){
        ResumenTOB.intermediaryBankCountry="";
        ResumenTOB.intermediaryBankCountryCode="";
    }else{
        ResumenTOB.intermediaryBankCountry=$('#Template_CountryBankIB :selected').html();
        ResumenTOB.intermediaryBankCountryCode=$("#Template_CountryBankIB").get(0).value;
    }

    ResumenTOB.furtherCreditAccount=$("#Template_AccountNumberFFC").get(0).value;
    ResumenTOB.furtherCreditName=$("#Template_NameFFC").get(0).value;

    if($("#accionTemplate").val()=="editar")
      ResumenTOB.nombreTemplate=$("#templateID").val();
    else if($("#accionTemplate").val()=="nuevo")
      ResumenTOB.nombreTemplate=$("#Template_nombreTemplate").val();
    jsonTransfers[0]= ResumenTOB;

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,salvarTemplateEditarSuccess,null,null);
}

function salvarTemplateEditarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    //idioma = result.idioma;
    var encontro = result.encontro;
    var mensaje = "";
        respuesta = result.respuesta;

    if(respuesta=="OK"){


        if ($("#tipo_usuario_app").val()=="6"){

            if(idioma=="_us_en"){
                mensaje= "Template successfully saved<br>";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                mensaje=  "Plantilla guardada exitosamente.<br>";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
            cambiarEstatusTemplateFC(idTemplate);
        }else{

            if(idioma=="_us_en"){
                mensaje= "Template successfully saved<br>To perform operations must be approved";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                mensaje=  "Plantilla guardada exitosamente.<br>Por favor apruebe la plantilla para poder utilizarla";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }


        }


        $("#Template_createTemplate").fadeOut("fast");
        $("#Template_consulta").fadeIn("fast");
        $("#summaryTemplate").fadeOut("fast")
        $("#accionTemplate").val("");
        limpiarResumen();
        infoPaginaTemplateConsultaJSONData();


    }else{
        //  1 = resultado de la acci&oacute;n fue exitoso.
        //  0 = resultado de la acci&oacute;n no fue exitoso porque el alias ya existe.
        // -1 = resultado de la acci&oacute;n no fue exitoso porque ya existen los datos de la cta beneficiario.
        // -2 = resultado de la acci&oacute;n no fue exitoso porque ya existe el alias y los datos de la cta beneficiario.
        if(encontro=="0"){
            if(idioma=="_us_en"){
                mensaje= "No se guardo la plantilla, " +
                    "ya existe una plantilla con el mismo Alias";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                mensaje= "The template was not saved, the template name already exist";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }else if(encontro=="-1"){
            if(idioma=="_us_en"){
                mensaje="No se guardo la plantilla, los datos de la cuenta del beneficiario ya se encuentran registrados en otra plantilla";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                mensaje= "The template was not saved, the info of beneficiary are already register in another template";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }else if(encontro=="-2"){
            if(idioma=="_us_en"){
                mensaje="No se guardó la plantilla, ya que existe una plantilla con el mismo Alias y los datos de la cuenta del beneficiario ya se encuentran registrados en la misma.";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                mensaje= "The template was not saved because there is a template with the same alias and the details of the beneficiary account are registered in the same.";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }

    }

}


function infoPaginaTransferTemplateJSONData(){
    var url = urlTransfersCargar;
    var param={};
    var jsonTransfers=[];
    $("#createToOtherBank .selectFormulario_TOB").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#createToOtherBank .inputFormulario_TOB").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#resultToOtherBank").attr("style","display: none");
    $("#createToOtherBank").attr("style","display: block");
    $("#summaryToOtherBank").attr("style","display: none");
    $("#mensaje_error").empty();
    $("#div_mensajes_error").attr("style","display: none");
    $("#div_numref_TOB").attr("style","display: none ");
    $("#div_estatus_TOB").attr("style","display: none");
    $("#tituloExitoso_TOB").attr("style","display: none");

    jsonTransfers[0]= origenTemplate;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,infoPaginaTransferTemplateSuccess,null,null);
}


function infoPaginaTransferTemplateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    PaisesBeneficiario = result.paisesBeneficiario;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    templates = result.listaTemplates;
//    TAGMsgInfoNombreBeneficiario = result.TAGMsgInfoNombreBeneficiario;
    idioma = result.idioma;
    makeTranfers=result.respuesta;
    var codSwift = [];
    if(valorCuentas!=null)
        if(idioma=="_us_en")
            cargar_selectPersonal("Accounts", valorCuentas,"Select","-2");
        else
            cargar_selectPersonal("Accounts", valorCuentas,"Seleccione","-2");

    if(cuentas!=null)
        cargar_selectPersonal2("AccountBank", cuentas);

    if(paises!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonal("Country", PaisesBeneficiario,"Select","-2");
            cargar_selectPersonal("CountryBank", paises,"Select","-2");
            cargar_selectPersonal("CountryBankIB", paises,"Select","-2");
        }else{
            cargar_selectPersonal("Country", PaisesBeneficiario,"Seleccione","-2");
            cargar_selectPersonal("CountryBank", paises,"Seleccione","-2");
            cargar_selectPersonal("CountryBankIB", paises,"Seleccione","-2");
        }
    }
    if(codBankBen!=null){
        if(idioma=="_us_en"){
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal("BankCode", codSwift,"Select","-2");
            cargar_selectPersonal("BankCodeIB", codSwift,"Select","-2");
        }else{
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal("BankCode", codSwift,"Seleccione","-2");
            cargar_selectPersonal("BankCodeIB", codSwift,"Seleccione","-2");
        }

    }

    if(fechaCierre!=null)
        $("#tagAvailableBalanceDate").html(fechaCierre);

//    alert(TAGMsgInfoNombreBeneficiario);
    $("#div_carga_espera").addClass("oculto");
    $("#Templateform_7").attr("style","display:");

}

function FC_infoPaginaTransferTemplateJSONData(){
    var url = urlTransfersCargar;
    var param={};
    $("#FC_createToOtherBank .selectFormulario_TOB_FC").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#FC_createToOtherBank .inputFormulario_TOB_FC").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#FC_resultToOtherBank").attr("style","display: none");
    $("#FC_createToOtherBank").attr("style","display: block");
    $("#FC_summaryToOtherBank").attr("style","display: none");
    $("#mensaje_error").empty();
    $("#div_mensajes_error").attr("style","display: none");
    $("#FC_div_numref_TOB").attr("style","display: none ");
    $("#FC_div_estatus_TOB").attr("style","display: none");
    $("#FC_tituloExitoso_TOB").attr("style","display: none");



    sendServiceJSON(url,param,FC_infoPaginaTransferTemplateSuccess,null,null);
}


function FC_infoPaginaTransferTemplateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    templatesFC = result.listaTemplates;
//    TAGMsgInfoNombreBeneficiario = result.TAGMsgInfoNombreBeneficiario;
    idioma = result.idioma;

    if(valorCuentas!=null)
        if(idioma=="_us_en")
            cargar_selectPersonal("FC_Accounts", valorCuentas,"Select","-2");
        else
            cargar_selectPersonal("FC_Accounts", valorCuentas,"Seleccione","-2");

    if(cuentas!=null)
        cargar_selectPersonal2("FC_AccountBank", cuentas);

    if(paises!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonal("FC_Country", paises,"Select","-2");
            cargar_selectPersonal("FC_CountryBank", paises,"Select","-2");
            cargar_selectPersonal("FC_CountryBankIB", paises,"Select","-2");
        }else{
            cargar_selectPersonal("FC_Country", paises,"Seleccione","-2");
            cargar_selectPersonal("FC_CountryBank", paises,"Seleccione","-2");
            cargar_selectPersonal("FC_CountryBankIB", paises,"Seleccione","-2");
        }
    }
    if(codBankBen!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonal("FC_BankCode", codBankBen,"Select","-2");
            cargar_selectPersonal("FC_BankCodeIB", codBankBen,"Select","-2");
        }else{
            cargar_selectPersonal("FC_BankCode", codBankBen,"Seleccione","-2");
            cargar_selectPersonal("FC_BankCodeIB", codBankBen,"Seleccione","-2");
        }

    }

    if(fechaCierre!=null)
        $("#FC_tagAvailableBalanceDate").html(fechaCierre);

//    alert(TAGMsgInfoNombreBeneficiario);
    $("#div_carga_espera").addClass("oculto");
}


function newTemplateJSONData(){
    var url = urlTransfersCargar;
    var param={};
    var jsonTransfers=[];
    //Se envia para indicar que el origen es una plantilla
    jsonTransfers[0]= "TMP";

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,newTemplateSuccess,null,null);
}

function newTemplateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    PaisesBeneficiario = result.paisesBeneficiario;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    idioma =result.idioma;
    var codSwift = []
    if(cuentas!=null)
        cargar_selectPersonal2("Template_AccountBank", cuentas);


    if(paises!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonal("Template_Country", PaisesBeneficiario,"Select","-2");
            cargar_selectPersonal("Template_CountryBank", paises,"Select","-2");
            cargar_selectPersonal("Template_CountryBankIB", paises,"Select","-2");
        }else{
            cargar_selectPersonal("Template_Country", PaisesBeneficiario,"Seleccione","-2");
            cargar_selectPersonal("Template_CountryBank", paises,"Seleccione","-2");
            cargar_selectPersonal("Template_CountryBankIB", paises,"Seleccione","-2");
        }

    }
    if(codBankBen!=null){
        if(idioma=="_us_en"){
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal("Template_BankCode", codSwift,"Select","-2");
            cargar_selectPersonal("Template_BankCodeIB", codSwift,"Select","-2");
            $.each(codBankBen, function (s, item) {
                if (item.value == "SWIFT")
                    codSwift[0] = item;
            });
            //cargar_selectPersonal("Template_SwiftBankCode", codSwift,"Select","-2");
            //cargar_selectPersonal("Template_SwiftBankCodeIB", codSwift,"Select","-2");
        }else{
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal("Template_BankCode", codSwift,"Seleccione","-2");
            cargar_selectPersonal("Template_BankCodeIB", codSwift,"Seleccione","-2");
            $.each(codBankBen, function (s, item) {
                if (item.value == "SWIFT")
                    codSwift[0] = item;
            });
            //cargar_selectPersonal("Template_SwiftBankCode", codSwift,"Seleccione","-2");
            //cargar_selectPersonal("Template_SwiftBankCodeIB", codSwift,"Seleccione","-2");
        }

    }
    //$("Template_btn_TOB_aprobar").addClass("oculto");
    $("#btnAproveTemplate").html('');
    $("#div_carga_espera").addClass("oculto");
    $("#Templateform_7").attr("style","display:");
}

//Cuando se selecciona account obliga a tener banco intermediario
function validarIntermediario(){

   if ($("#BankCode").val()=="ACCOUNT"){
       $("#BankCodeIB2").addClass("obligatorioTOB");
       $("#NameBankIB").addClass("obligatorioTOB");
       $("#AddressLineBankIB1").addClass("obligatorioTOB");
       $("#CountryBankIB").addClass("obligatorioTOB");
       $("#BankCodeIB2OB").attr("style","display:''");
       $("#NameBankIBOB").attr("style","display:''");
       $("#AddressLineBankIB1OB").attr("style","display:''");
       $("#CountryBankIBOB").attr("style","display:''");
   }else{
       $("#BankCodeIB2").removeClass("obligatorioTOB error_campo");
       $("#NameBankIB").removeClass("obligatorioTOB error_campo");
       $("#AddressLineBankIB1").removeClass("obligatorioTOB error_campo");
       $("#CountryBankIB").removeClass("obligatorioTOB error_campo");
       $("#BankCodeIB2OB").attr("style","display:none");
       $("#NameBankIBOB").attr("style","display:none");
       $("#AddressLineBankIB1OB").attr("style","display:none");
       $("#CountryBankIBOB").attr("style","display:none");
   }
}

function validarIntermediarioTemplate(){

    if ($("#Template_BankCode").val()=="ACCOUNT"){
        $("#Template_BankCodeIB2").addClass("obligatorio_Template");
        $("#Template_NameBankIB").addClass("obligatorio_Template");
        $("#Template_AddressLineBankIB1").addClass("obligatorio_Template");
        $("#Template_CountryBankIB").addClass("obligatorio_Template");
        $("#Template_BankCodeIB2OB").attr("style","display:''");
        $("#Template_NameBankIBOB").attr("style","display:''");
        $("#Template_AddressLineBankIB1OB").attr("style","display:''");
        $("#Template_CountryBankIBOB").attr("style","display:''");
    }else{
        $("#Template_BankCodeIB2").removeClass("obligatorio_Template  error_campo");
        $("#Template_NameBankIB").removeClass("obligatorio_Template error_campo");
        $("#Template_AddressLineBankIB1").removeClass("obligatorio_Template error_campo");
        $("#Template_CountryBankIB").removeClass("obligatorio_Template error_campo");
        $("#Template_BankCodeIB2OB").attr("style","display:none");
        $("#Template_NameBankIBOB").attr("style","display:none");
        $("#Template_AddressLineBankIB1OB").attr("style","display:none");
        $("#Template_CountryBankIBOB").attr("style","display:none");
    }
}



function newTemplateJSONDataTemplate(){
    var url = urlTransfersCargar;
    var param={};
    var jsonTransfers=[];
    //Se envia para indicar que el origen es una plantilla
    jsonTransfers[0]= "TMP";

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON_sinc(url,param,newTemplateSuccess,null,null);
}

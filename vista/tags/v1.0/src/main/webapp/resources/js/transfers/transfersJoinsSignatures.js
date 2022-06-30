var urlTransfersCargarFC="Transfers_cargar.action";
var urlTransfersValidateTOBFC="Transfers_validateToOtherBanks.action";
var urlTransfersSaveTOBFC="Transfers_saveToOtherBanks_JS.action";
//var urlLoginOpeEsp="cargarLoginOpeEsp.action";
var urlDirectorioCargarTemplateFC="Transfers_cargarDetalleDirectorio.action";
var urlTransfersGenerarClaveTOBFC="Security_crearClaveRamdom.action";
var urlValidarClaveTOBFC="Transfers_validarClaveTransferenciasTOB.action";
var urlParametrosPersonalesTOBFC="FirmasConjuntas_cargarParametrosPersonalesFC.action";
var urlDirectorioCargarTransfer="Transfers_consultarDirectorioTransfer.action";
var urlDirectorioCargarTemplate="Transfers_cargarDetalleDirectorio.action";

var resumentransferToOtherBankFC;
var templatesFC = "";

var idioma = "";
var parametrosFC="";
var parametrosFCBase="";

$(document).ready(function(){

    $("#FC_tagiTemplate").focus(function(){
        $("#FC_tagiTemplate").autocomplete({
            source: templatesFC,
            select: function(event, ui) {
                $("#FC_tagiTemplate").val(ui.item.label);

                FC_cargarInfoTemplateJSONData(ui.item.key);
            }
        });
    });

    $("#FC_tagiTemplate").click(function(){
        $("#div_tabla_template_consulta_transfer").empty();
        $("#paginacion_buscarBanco_consulta_bancos_template_transfer").empty();
        infoPaginaTemplateFCConsultaJSONData();

        $("#sign_up_template_transfer").lightbox_me({centered: true, onLoad: function() {
            $("#sign_up_template_transfer").find("input:first").focus();
        }});


        //$("#div_carga_espera_template_transfer").removeClass("oculto");

//            e.preventDefault();

    });


    $("#FC_btn_TOB_aceptar").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido


        $(".obligatorioTOB_FC").each(function(){

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
            if(idioma =="_us_en"){
                if (!isAlphanumericWithSpace(Trim($("#FC_name").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_name").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AccountBank").get(0).value)  == "DD") {

                    if (Trim($("#FC_AccountNumber").get(0).value)== "" || !esSoloLetrasNumeros(Trim($("#FC_AccountNumber").get(0).value))) {
                        mensaje = mensaje + "Beneficiary: The Beneficiary Account Number is invalid."+"<br>";
                        $("#FC_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                } else {
                    if ($("#FC_AccountNumber").get(0).value.length < 15 || $("#FC_AccountNumber").get(0).value.length > 34 ) {

                        mensaje = mensaje + "Beneficiary: The IBAN format is invalid. Remember that the IBAN consists\nof a minimum of 15 and a maximum of 34 alphanumeric characters."+"<br>";
                        $("#FC_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphabetic($("#FC_AccountNumber").get(0).value.substring(0, 2)) || !esSoloLetrasNumeros($("#FC_AccountNumber").get(0).value.substring(2, $("#FC_AccountNumber").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary: The IBAN format is invalid. \nPlease check."+"<br>";
                        $("#FC_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#FC_AddressLine1").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary: Type the address of the Beneficiary of the funds"+"<br>";
                    $("#FC_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace(Trim($("#FC_AddressLine1").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLine2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLine2").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_AddressLine2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLine3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLine3").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_AddressLine3").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim( $("#FC_TelephoneNumber").get(0).value) != "" && TlfNoValido(Trim($("#FC_TelephoneNumber").get(0).value))) {
                    mensaje = mensaje + "Beneficiary: The format for Phone Number is invalid."+"<br>";
                    $("#FC_TelephoneNumber").addClass("error_campo");
                    invalido ="1";
                }

                if (Trim($("#FC_BankCode").get(0).value) == "ABA") {
                    if ($("#FC_BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#FC_BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#FC_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                } else if (Trim($("#FC_BankCode").get(0).value) == "SWIFT") {
                    if ($("#FC_BankCode2").get(0).value.length != 8 && $("#FC_BankCode2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#FC_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#FC_BankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#FC_BankCode2").get(0).value.substring(6, $("#FC_BankCode2").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#FC_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#FC_BankCode2").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary Bank: Indicate the code of Beneficiary Bank."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (!esSoloLetrasNumeros(Trim($("#FC_BankCode2").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank´s Bank Code contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_NameBank").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary Bank: Indicate the name of the Beneficiary Bank"+"<br>";
                    $("#FC_NameBank").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace(Trim($("#FC_NameBank").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_NameBank").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace(Trim($("#FC_AddressLineBank1").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_AddressLineBank1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLineBank2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBank1").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_AddressLineBank2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLineBank3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBank3").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_AddressLineBank3").addClass("error_campo");
                    invalido ="1";
                }


                if( Trim($("#FC_BankCodeIB2").get(0).value)!= "" ||  Trim($("#FC_NameBankIB").get(0).value)!= "" ||
                    Trim($("#FC_AddressLineBankIB1").get(0).value)!= "" || Trim($("#FC_AddressLineBankIB2").get(0).value)!= "" ||
                    Trim($("#FC_AddressLineBankIB3").get(0).value)!= "" || $("#FC_CountryBankIB").get(0).value != "-2" || Trim($("#FC_BankCodeIB").get(0).value) == "ACCOUNT"  ) {

                    if (Trim($("#FC_BankCodeIB").get(0).value) == "ABA") {
                        if ($("#FC_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#FC_BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                            $("#FC_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    } else if (Trim($("#FC_BankCodeIB").get(0).value) == "SWIFT") {
                        if ($("#FC_BankCodeIB").get(0).value.length != 8 && $("#FC_BankCodeIB").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#FC_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                        if (!isAlphabetic($("#FC_BankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#FC_BankCodeIB2").get(0).value.substring(6, $("#FC_BankCodeIB2").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#FC_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (Trim($("#FC_BankCodeIB2").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#FC_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros(Trim($("#FC_BankCodeIB2").get(0).value)) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank´s Bank Code contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_BankCodeIB2").addClass("error_campo");
                        invalido ="1";

                    }
                    if (Trim($("#FC_NameBankIB").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Indicate the name of the Intermediary Bank."+"<br>";
                        $("#FC_NameBankIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace(Trim($("#FC_NameBankIB").get(0).value)) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_NameBankIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_AddressLineBankIB1").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Type the address of the Intermediary Bank."+"<br>";
                        $("#FC_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace(Trim($("#FC_AddressLineBankIB1").get(0).value)) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_AddressLineBankIB2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBankIB2").get(0).value)) ) {
                        mensaje = mensaje + "Intermediary: The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_AddressLineBankIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_AddressLineBankIB3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBankIB3").get(0).value)) ) {
                        mensaje = mensaje + "Intermediary:The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_AddressLineBankIB3").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_CountryBankIB").get(0).value) == "-2") {
                        mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>";
                        $("#FC_CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }


                }




//            <% //*********** Inicio de Validaciones Provisionales mientras en Sif no se implemente la emisi\u00f3n de cheques para estos casos ***** %>
                if( $('#BankCode :selected').html() == "SWIFT" &&
                    ($("#FC_BankCode2").get(0).value.toUpperCase() == "VZCRKYKY" || $("#FC_BankCode2").get(0).value.toUpperCase() == "VZCRVECA") ) {
                    mensaje = "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $('#BankCode :selected').html() == "ACCOUNT" &&
                    $("#FC_BankCode2").get(0).value=="6550052216" && ($("#FC_BankCodeIB2").get(0).value=="026009593" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $('#BankCode :selected').html() == "ACCOUNT" && $("#FC_BankCode2").get(0).value=="2000192005241"
                    && ($("#FC_BankCodeIB2").get(0).value=="026005092" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "PNBPUS3NNYC") ) {
                    mensaje ="It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information.";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if(  $('#BankCode :selected').html() == "ACCOUNT" &&
                    $("#FC_BankCode2").get(0).value=="400938448" && ($("#FC_BankCodeIB2").get(0).value=="021000021" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "CHASUS33") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $('#BankCode :selected').html() == "ACCOUNT" &&
                    $("#FC_BankCode2").get(0).value=="6550847584" && ($("#FC_BankCodeIB2").get(0).value=="026009593" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountBank").get(0).value == "DD" &&
                    $("#FC_AccountNumber").get(0).value=="6550747853" && ($("#FC_BankCode2").get(0).value=="026009593" || $("#FC_BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountNumber").get(0).value=="6550052216" && ($("#FC_BankCode2").get(0).value=="026009593" || $("#FC_BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if($("#FC_AccountNumber").get(0).value=="2000192005241" && ($("#FC_BankCode2").get(0).value=="026005092" || $("#FC_BankCode2").get(0).value.toUpperCase()=="PNBPUS3NNYC") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountNumber").get(0).value=="400938448" && ($("#FC_BankCode2").get(0).value=="021000021" || $("#FC_BankCode2").get(0).value.toUpperCase()=="CHASUS33") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountNumber").get(0).value=="6550847584" && ($("#FC_BankCode2").get(0).value=="026009593" || $("#FC_BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                    mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }

                if( Trim($("#FC_AccountNumberFFC").get(0).value)!= "" || Trim($("#FC_NameFFC").get(0).value)!= "" ) {

                    if (Trim($("#FC_AccountNumberFFC").get(0).value)== "") {
                        mensaje = mensaje + "For further credit: Type the Account Number for Further Credit to."+"<br>";
                        $("#FC_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros(Trim($("#FC_AccountNumberFFC").get(0).value)) || $("#FC_AccountNumberFFC").get(0).value.length < 4) {
                        mensaje = mensaje + " For further credit to Account Number must consist of a minimum of 4 alpha-numeric characters and is not allowed to contain any invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_NameFFC").get(0).value)== "") {
                        mensaje = mensaje + " For further credit to: Type the name of the Account Holder of the account for Further Credit to."+"<br>";
                        $("#FC_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace( Trim($("#FC_NameFFC").get(0).value)) ) {
                        mensaje = mensaje + " For further credito to Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                        $("#FC_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if (Number(unFormatCurrency($("#FC_AmmountAI").get(0).value,',').replace(',','.')) <  Number("1")) {
                    mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametrosFC.ex_mminto+"."+"<br>";
                    $("#FC_AmmountAI").addClass("error_campo");
                    invalido ="1";
                }
                if (Number(unFormatCurrency($("#FC_AmmountAI").get(0).value,',').replace(',','.'))  > Number(unFormatCurrency(parametrosFC.vbt_mmtd,',').replace(',','.')) || Number(unFormatCurrency($("#FC_AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametrosFC.vbt_mmto,',').replace(',','.'))) {
//               mensaje = mensaje + " Amount & Instructions: The amount exceeds the maximum of USD 50.000,00."+"<br>";
                    if(Number(unFormatCurrency($("#FC_AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametrosFC.vbt_mmtd,',').replace(',','.')))
                        mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametrosFC.vbt_mmtd+"."+"<br>";
                    else
                        mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametrosFC.vbt_mmto+"."+"<br>";
                    $("#FC_AmmountAI").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_ReceiverInformation").get(0).value)) ) {
                    mensaje = mensaje + " Amount & Instructions: Receiver Information contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#FC_ReceiverInformation").addClass("error_campo");
                    invalido ="1";
                }
            }else{
                if (!isAlphanumericWithSpace(Trim($("#FC_name").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiario: El Nombre del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_name").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AccountBank").get(0).value)  == "DD") {

                    if (Trim($("#FC_AccountNumber").get(0).value)== "" || !esSoloLetrasNumeros(Trim($("#FC_AccountNumber").get(0).value))) {
                        mensaje = mensaje + "Beneficiario: N&uacute;mero de cuenta del Beneficiario inv&aacute;lido."+"<br>";
                        $("#FC_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                } else {
                    if ($("#FC_AccountNumber").get(0).value.length < 15 || $("#FC_AccountNumber").get(0).value.length > 34 ) {

                        mensaje = mensaje + "Beneficiario: Formato de IBAN inv&aacute;lido. Recuerde que el IBAN tiene\nal menos 15 y m&aacute;ximo 34 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#FC_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphabetic($("#FC_AccountNumber").get(0).value.substring(0, 2)) || !esSoloLetrasNumeros($("#FC_AccountNumber").get(0).value.substring(2, $("#FC_AccountNumber").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiario:  Formato de IBAN inv&aacute;lido. \nPor favor revise."+"<br>";
                        $("#FC_AccountNumber").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#FC_AddressLine1").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiario: Indique la direcci&oacute;n del beneficiario de la transferencia."+"<br>";
                    $("#FC_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace(Trim($("#FC_AddressLine1").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiario:  La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_AddressLine1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLine2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLine2").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiario: La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)"+"<br>";
                    $("#FC_AddressLine2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLine3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLine3").get(0).value)) ) {
                    mensaje = mensaje + "Beneficiario: La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)"+"<br>";
                    $("#FC_AddressLine3").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim( $("#FC_TelephoneNumber").get(0).value) != "" && TlfNoValido(Trim($("#FC_TelephoneNumber").get(0).value))) {
                    mensaje = mensaje + "Beneficiario: Formato del N&uacute;mero de Tel&eacute;fono incorrecto."+"<br>";
                    $("#FC_TelephoneNumber").addClass("error_campo");
                    invalido ="1";
                }

                if (Trim($("#FC_BankCode").get(0).value) == "ABA") {
                    if ($("#FC_BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#FC_BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#FC_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                } else if (Trim($("#FC_BankCode").get(0).value) == "SWIFT") {
                    if ($("#FC_BankCode2").get(0).value.length != 8 && $("#FC_BankCode2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#FC_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#FC_BankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#FC_BankCode2").get(0).value.substring(6, $("#FC_BankCode2").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#FC_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#FC_BankCode2").get(0).value)== "") {
                    mensaje = mensaje + "Banco Beneficiario: Indique el c&oacute;digo del banco beneficiario."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (!esSoloLetrasNumeros(Trim($("#FC_BankCode2").get(0).value)) ) {
                    mensaje = mensaje + "Banco Beneficiario: El C&oacute;digo del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_NameBank").get(0).value)== "") {
                    mensaje = mensaje + "Banco Beneficiario: Indique el nombre del banco beneficiario."+"<br>";
                    $("#FC_NameBank").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace(Trim($("#FC_NameBank").get(0).value)) ) {
                    mensaje = mensaje + "Banco Beneficiario: El Nombre del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_NameBank").addClass("error_campo");
                    invalido ="1";
                }
                if (!isAlphanumericWithSpace(Trim($("#FC_AddressLineBank1").get(0).value)) ) {
                    mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_AddressLineBank1").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLineBank2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBank1").get(0).value)) ) {
                    mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_AddressLineBank2").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_AddressLineBank3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBank3").get(0).value)) ) {
                    mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_AddressLineBank3").addClass("error_campo");
                    invalido ="1";
                }


                if( Trim($("#FC_BankCodeIB2").get(0).value)!= "" ||  Trim($("#FC_NameBankIB").get(0).value)!= "" ||
                    Trim( $("#FC_AddressLineBankIB1").get(0).value)!= "" || Trim($("#FC_AddressLineBankIB2").get(0).value)!= "" ||
                    Trim($("#FC_AddressLineBankIB3").get(0).value)!= "" || $("#FC_CountryBankIB").get(0).value != "-2" || $("#FC_BankCodeIB").get(0).value == "ACCOUNT"  ) {

                    if ($("#FC_BankCodeIB").get(0).value == "ABA") {
                        if ($("#FC_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#FC_BankCodeIB2").get(0).value)) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                            $("#FC_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    } else if ($("#FC_BankCodeIB").get(0).value == "SWIFT") {
                        if ($("#FC_BankCodeIB2").get(0).value.length != 8 && $("#FC_BankCodeIB2").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#FC_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                        if (!isAlphabetic($("#FC_BankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#FC_BankCodeIB2").get(0).value.substring(6, $("#FC_BankCodeIB2").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#FC_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (Trim($("#FC_BankCodeIB2").get(0).value)== "") {
                        mensaje = mensaje + "Intermediario: Indique el c&oacute;digo del banco intermediario."+"<br>";
                        $("#FC_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros(Trim($("#FC_BankCodeIB2").get(0).value)) ) {
                        mensaje = mensaje + "Intermediario: El C&oacute;digo del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#FC_BankCodeIB2").addClass("error_campo");
                        invalido ="1";

                    }
                    if (Trim($("#FC_NameBankIB").get(0).value)== "") {
                        mensaje = mensaje + "Intermediario: Indique el nombre del banco intermediario."+"<br>";
                        $("#FC_NameBankIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace(Trim($("#FC_NameBankIB").get(0).value)) ) {
                        mensaje = mensaje + "Intermediario: El Nombre del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros).."+"<br>";
                        $("#FC_NameBankIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_AddressLineBankIB1").get(0).value)== "") {
                        mensaje = mensaje + "Intermediario: Indique la direcci&oacute;n del banco intermediario."+"<br>";
                        $("#FC_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace(Trim($("#FC_AddressLineBankIB1").get(0).value)) ) {
                        mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#FC_AddressLineBankIB1").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_AddressLineBankIB2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBankIB2").get(0).value)) ) {
                        mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#FC_AddressLineBankIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_AddressLineBankIB3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_AddressLineBankIB3").get(0).value)) ) {
                        mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#FC_AddressLineBankIB3").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#FC_CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediario: Indique el Pais del banco intermediario."+"<br>";
                        $("#FC_CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }


                }




//            <% //*********** Inicio de Validaciones Provisionales mientras en Sif no se implemente la emisi&oacute;n de cheques para estos casos ***** %>
                if( $('#BankCode :selected').html() == "SWIFT" &&
                    ($("#FC_BankCode2").get(0).value.toUpperCase() == "VZCRKYKY" || $("#FC_BankCode2").get(0).value.toUpperCase() == "VZCRVECA") ) {
                    mensaje = "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $('#BankCode :selected').html() == "ACCOUNT" &&
                    $("#FC_BankCode2").get(0).value=="6550052216" && ($("#FC_BankCodeIB2").get(0).value=="026009593" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $('#BankCode :selected').html() == "ACCOUNT" && $("#FC_BankCode2").get(0).value=="2000192005241"
                    && ($("#FC_BankCodeIB2").get(0).value=="026005092" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "PNBPUS3NNYC") ) {
                    mensaje ="Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n.";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if(  $('#BankCode :selected').html() == "ACCOUNT" &&
                    $("#FC_BankCode2").get(0).value=="400938448" && ($("#FC_BankCodeIB2").get(0).value=="021000021" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "CHASUS33") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $('#BankCode :selected').html() == "ACCOUNT" &&
                    $("#FC_BankCode2").get(0).value=="6550847584" && ($("#FC_BankCodeIB2").get(0).value=="026009593" || $("#FC_BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountBank").get(0).value == "DD" &&
                    $("#FC_AccountNumber").get(0).value=="6550747853" && ($("#FC_BankCode2").get(0).value=="026009593" || $("#FC_BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountNumber").get(0).value=="6550052216" && ($("#FC_BankCode2").get(0).value=="026009593" || $("#FC_BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if($("#FC_AccountNumber").get(0).value=="2000192005241" && ($("#FC_BankCode2").get(0).value=="026005092" || $("#FC_BankCode2").get(0).value.toUpperCase()=="PNBPUS3NNYC") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountNumber").get(0).value=="400938448" && ($("#FC_BankCode2").get(0).value=="021000021" || $("#FC_BankCode2").get(0).value.toUpperCase()=="CHASUS33") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }
                if( $("#FC_AccountNumber").get(0).value=="6550847584" && ($("#FC_BankCode2").get(0).value=="026009593" || $("#FC_BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                    mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                    $("#FC_AccountNumber").addClass("error_campo");
                    invalido ="1";
                }

                if(Trim( $("#FC_AccountNumberFFC").get(0).value)!= "" || Trim($("#FC_NameFFC").get(0).value)!= "" ) {

                    if (Trim($("#FC_AccountNumberFFC").get(0).value)== "") {
                        mensaje = mensaje + "Para Cr&eacute;dito Final a: Indique el N&uacute;mero de Cuenta para Cr&eacute;dito Final a."+"<br>";
                        $("#FC_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!esSoloLetrasNumeros(Trim($("#FC_AccountNumberFFC").get(0).value)) || $("#FC_AccountNumberFFC").get(0).value.length < 4) {
                        mensaje = mensaje + " El N&uacute;mero de Cuenta para Cr&eacute;dito Final debe ser mayor de 4 caracteres alfanum&eacute;ricos y no debe contener caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#FC_AccountNumberFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (Trim($("#FC_NameFFC").get(0).value)== "") {
                        mensaje = mensaje + " Para Cr&eacute;dito Final a: Indique el Nombre del Titular de la Cuenta para Cr&eacute;dito Final a."+"<br>";
                        $("#FC_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphanumericWithSpace( Trim($("#FC_NameFFC").get(0).value)) ) {
                        mensaje = mensaje + " El Nombre para Cr&eacute;dito Final tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                        $("#FC_NameFFC").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if (Number(unFormatCurrency($("#FC_AmmountAI").get(0).value,',').replace(',','.')) < Number("1")) {
                    mensaje = mensaje + "Monto y Descripci&oacute;n:  Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a 1."+"<br>";
                    $("#FC_AmmountAI").addClass("error_campo");
                    invalido ="1";
                }
                if (Number(unFormatCurrency($("#FC_AmmountAI").get(0).value,',').replace(',','.'))  > Number(unFormatCurrency(parametrosFC.vbt_mmtd,',').replace(',','.')) || Number(unFormatCurrency($("#FC_AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametrosFC.vbt_mmto,',').replace(',','.'))) {
//               mensaje = mensaje + " Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD 50.000,00)."+"<br>";
                    if(Number(unFormatCurrency($("#FC_AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametrosFC.vbt_mmtd,',').replace(',','.')))
                        mensaje = mensaje + "Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametrosFC.vbt_mmtd+")."+"<br>";
                    else
                        mensaje = mensaje + "Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametrosFC.vbt_mmto+")."+"<br>" ;
                    $("#FC_AmmountAI").addClass("error_campo");
                    invalido ="1";
                }
                if (Trim($("#FC_ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#FC_ReceiverInformation").get(0).value)) ) {
                    mensaje = mensaje + " Monto y Descripci&oacute;n: La Descripci&oacute;n tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                    $("#FC_ReceiverInformation").addClass("error_campo");
                    invalido ="1";
                }
            }
        }



        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            $("#marco_trabajo").css("height","870px");
        }else{
            if(isMail($("#FC_beneficiaryEmail").get(0).value)){
                if($("#FC_beneficiaryEmail").hasClass("error_campo"))
                    $("#FC_beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#FC_summaryToOtherBank #FC_clave_TOB").attr("style","display: none");
                $("#FC_summaryToOtherBank #FC_tituloResumen_TOB").attr("style","display: block");
                $("#FC_summaryToOtherBank #FC_resumenBotones_TOB").fadeIn("fast");
                $("#FC_summaryToOtherBank #FC_resumenBotones_TOB_Finales").attr("style","display: none");
                $("#FC_summaryToOtherBank #FC_tituloExitoso_TOB").attr("style","display: none");
                FC_TransfersValidateJSONData();
            }else{
                if(idioma=="_us_en")
                    mensaje = "Beneficiary: Email"+"<br>";
                else
                    mensaje = "Beneficiario: Email"+"<br>";
                $("#FC_beneficiaryEmail").addClass("error_campo");
                $("#mensaje_error").empty();
                if(idioma=="_us_en")
                    $("#mensaje_error").html("Invalid format - "+mensaje);
                else
                    $("#mensaje_error").html("Formato invalido - "+mensaje);
                $("#div_mensajes_error").fadeIn("slow");
                $("#FC_beneficiaryEmail").focus();
            }



        }


    });

    $("#FC_btn__resumen_aceptar").click(function(){
        $("#FC_summaryToOtherBank #FC_clave_TOB").attr("style","display: block");
        $("#FC_pwdClaveConfirmTransfer_TOB").val("");
        $("#FC_summaryToOtherBank #FC_tituloResumen_TOB").attr("style","display: none");
        $("#FC_summaryToOtherBank #FC_resumenBotones_TOB").attr("style","display: none");
        $("#FC_btn_aceptarClave").attr("disabled",false);
        //$("#marco_trabajo").css("height","1200px");
        FC_TransferGenerarClaveJSONData();

    });

    $("#FC_btn_cancelarClave").click(function(){
//        $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
        $("#FC_createToOtherBank").attr("style","display: block");
        $("#FC_summaryToOtherBank").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").attr("style","display: none");
        $("#FC_btn_aceptarClave").attr("disabled",false);
        FC_infoPaginaJSONData();
    });

    $("#FC_btn_aceptarClave").click(function(){
//        $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
        $("#FC_btn_aceptarClave").attr("disabled",true);
        FC_ValidarClaveTransferJSONData();
    });
    $("#FC_btn_TOB_cancelar").click(function(){
//        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");
        $(".error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#FC_createToOtherBank .selectFormulario_TOB_FC").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#FC_createToOtherBank .inputFormulario_TOB_FC").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
//        blanquearFormularios("createToOtherBank");
    });

    $("#FC_btn__resumen_cancelar").click(function(){

        $("#FC_createToOtherBank").fadeIn("fast");
        $("#FC_summaryToOtherBank").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");
        //$("#marco_trabajo").css("height","1200px");

    });



    $("#FC_btn_finalizar_TOB_final").click(function(){
        $("#FC_createToOtherBank").attr("style","display: block");
        $("#FC_summaryToOtherBank").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").attr("style","display: none");
        FC_infoPaginaJSONData();

    });

    $("#FC_btn_ok_TOB_final").click(function(){
        seleccionarOpcion("home");
    });

    $("#FC_homeTOB").click(function(){
        seleccionarOpcionMenu("home");

    });

    /**
     * Print html TOB
     */
    $("#FC_imprimirCampos_TOB").click(function(){

        var miValue = $("#FC_Accounts" ).val();
        $("#FC_Accounts option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#FC_BankCode" ).val();
        $("#FC_BankCode option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#FC_BankCodeIB" ).val();
        $("#FC_BankCodeIB option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#FC_AccountBank" ).val();
        $("#FC_AccountBank option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#FC_CountryBank" ).val();
        $("#FC_CountryBank option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#FC_CountryBankIB" ).val();
        $("#FC_CountryBankIB option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#FC_Country" ).val();
        $("#FC_Country option[value='"+miValue+"']").attr("selected",true);





        printPageElement('div_TRANSFERENCIA_EXTERNA_FC');
    });

    $("#FC_btn_imprimir_TOB_final").click(function(){

        printPageElement('FC_summaryToOtherBank');
    });

    $("#FC_btn_TemplateGuardar_TOB_final").click(function(){
//        alert("Esta funcion aun no esta disponible");

        if($("#div_DIRECTORIO").css("display")=='none'){
            $(".opcion_seleccionada").fadeOut("slow",function(){
                $(".opcion_seleccionada").removeClass("opcion_seleccionada");
                $("#div_DIRECTORIO").addClass("opcion_seleccionada");
                $("#div_DIRECTORIO").fadeIn("slow");
            });

        };

        $("#div_mensajes_error").fadeOut("fast");
        $("#Template_createTemplate").attr("style","display: ");
        $("#Template_consulta").attr("style","display: none");
        $("#Template_btn_transferencia").attr("style","display: none");
        $("#accionTemplate").val("nuevo");
        //$("#marco_trabajo").css("height","750px");
        FC_cargarSalvarTemplateTransferSuccess();
        $("#Template_btn_TOB_borrar").attr("style","display: none");

    });




});

function infoPaginaTemplateFCConsultaJSONData(){
    var url = urlDirectorioCargarTransfer;
    var param={};

    sendServiceJSON(url,param,infoPaginaTemplateFCConsultaSuccess,null,null);
}

function infoPaginaTemplateFCConsultaSuccess(originalRequest){
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

    crearTabla('div_tabla_template_consulta_transfer','tabla_Templates_consulta_transfer',columnas,datosTabla);

    var oTable = $('#tabla_Templates_consulta_transfer').dataTable({
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


}

function FC_ParametrosPersonalesCamposTOBJSONData(){
    var url = urlParametrosPersonalesTOBFC;
    var param={};

    sendServiceJSON(url,param,FC_ParametrosPersonalesTOBCamposSuccess,null,null);
}


function FC_ParametrosPersonalesTOBCamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametrosFC = result.parametrosPersonalesFC;
    parametrosFCBase = result.parametrosPersonalesBaseFC;

    if(parametrosFC == null || parametrosFC == ""){
        parametrosFC = parametrosFCBase;
    }

    $("#div_carga_espera").addClass("oculto");
}


function FC_cargarInfoTemplateJSONData(id){
    var url = urlDirectorioCargarTemplateFC;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= id;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,FC_cargarInfotemplatesFCuccess,null,null);
}


function FC_cargarInfotemplatesFCuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var datos = result.datos;
    idioma = result.idioma;

    $("#FC_createToOtherBank .selectFormulario_TOB_FC").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#FC_createToOtherBank .inputFormulario_TOB_FC").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });

    $("#FC_createToOtherBank").fadeIn("fast");
    $("#FC_summaryToOtherBank").fadeOut("fast");
    $("#FC_resultToOtherBank").attr("style","display: none");

    $("#FC_BankCode").val(datos.beneficiaryBankCodeType);
    $("#FC_BankCode2").val(datos.beneficiaryBankCodeNumber);
    $("#FC_NameBank").val(datos.beneficiaryBankName);
    $("#FC_AddressLineBank1").val(datos.beneficiaryBankAddress1);
    $("#FC_AddressLineBank2").val(datos.beneficiaryBankAddress2);
    $("#FC_AddressLineBank3").val(datos.beneficiaryBankAddress3);
    $("#FC_CountryBank").val(datos.beneficiaryBankCountryCode);

    $("#FC_name").val(datos.beneficiaryName);
    $("#FC_AccountBank").val(datos.accountCode);
    $("#FC_AccountNumber").val(datos.account);
    $("#FC_beneficiaryEmail").val(datos.beneficiaryEmail);
    $("#FC_AddressLine1").val(datos.beneficiaryAddress1);
    $("#FC_AddressLine2").val(datos.beneficiaryAddress2);
    $("#FC_AddressLine3").val(datos.beneficiaryAddress3);
    $("#FC_TelephoneNumber").val(datos.beneficiaryTelephone);
    $("#FC_Country").val(datos.beneficiaryCountryCode);


    $("#FC_BankCodeIB").val(datos.intermediaryBankCodeType);


    $("#FC_BankCodeIB2").val(datos.intermediaryBankCodeNumber);
    $("#FC_NameBankIB").val(datos.intermediaryBankName);
    $("#FC_AddressLineBankIB1").val(datos.intermediaryBankAddress1);
    $("#FC_AddressLineBankIB2").val(datos.intermediaryBankAddress2);
    $("#FC_AddressLineBankIB3").val(datos.intermediaryBankAddress3);
    $("#FC_CountryBankIB").val(datos.intermediaryBankCountryCode);

    $("#FC_AccountNumberFFC").val(datos.furtherCreditAccount);
    $("#FC_NameFFC").val(datos.furtherCreditName);

}


function FC_infoPaginaJSONData(){
    var url = urlTransfersCargarFC;
    var param={};
    var jsonTransfers=[];
    $("#div_carga_espera").removeClass("oculto");
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
    jsonTransfers[0]= origenTemplate;
    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,FC_infoPaginaSuccess,null,null);
}


function FC_infoPaginaSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    templatesFC = result.listaTemplates;
    TAGMsgInfoNombreBeneficiario = result.TAGMsgInfoNombreBeneficiario;
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

    alert(TAGMsgInfoNombreBeneficiario);
    FC_ParametrosPersonalesCamposTOBJSONData();

}

function habilitarPanel_con_boton(formulario, formularioHabilitar){
    var valido=true;
    $("#"+formulario+" .obligatorioTOB").each(function(){
        if(Trim(this.value)==""){
            valido=false;
        }
    });

    if(valido){
        $("#"+formularioHabilitar).fadeIn("fast");
    }else{
        $("#"+formularioHabilitar).fadeOut("fast");
    }

}


function  FC_cargarData_toOtherBank(){
    origenTemplate="TOB";
    FC_infoPaginaJSONData();
}


function FC_cargarDatos_template(key){
//   alert("key: "+ key);
    alert(key);
    $("#FC_BankCode2").val(key);
}


function FC_TransfersValidateJSONData(){

    var url = urlTransfersValidateTOBFC;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};

    ResumenTOB.account=$('#FC_Accounts :selected').html();
    ResumenTOB.accountCode=$("#FC_Accounts").get(0).value;
    ResumenTOB.beneficiaryName=$("#FC_name").get(0).value;
    ResumenTOB.beneficiaryAccount=$("#FC_AccountNumber").get(0).value;
    ResumenTOB.beneficiaryAccountBank=$("#FC_AccountBank").get(0).value;
    ResumenTOB.beneficiaryEmail=$("#FC_beneficiaryEmail").get(0).value;
    ResumenTOB.beneficiaryAddress1=$("#FC_AddressLine1").get(0).value;
    ResumenTOB.beneficiaryAddress2=$("#FC_AddressLine2").get(0).value;
    ResumenTOB.beneficiaryAddress3=$("#FC_AddressLine3").get(0).value;
    ResumenTOB.beneficiaryTelephone=$("#FC_TelephoneNumber").get(0).value;
    ResumenTOB.beneficiaryCountry=$('#FC_Country :selected').html();
    ResumenTOB.beneficiaryCountryCode=$("#FC_Country").get(0).value;
    ResumenTOB.beneficiaryBankCodeType=$('#FC_BankCode :selected').html();
    ResumenTOB.beneficiaryBankCodeNumber=$("#FC_BankCode2").get(0).value;
    ResumenTOB.beneficiaryBankName=$("#FC_NameBank").get(0).value;
    ResumenTOB.beneficiaryBankAddress1=$("#FC_AddressLineBank1").get(0).value;
    ResumenTOB.beneficiaryBankAddress2=$("#FC_AddressLineBank2").get(0).value;
    ResumenTOB.beneficiaryBankAddress3=$("#FC_AddressLineBank3").get(0).value;
    ResumenTOB.beneficiaryBankCountry=$('#FC_CountryBank :selected').html();
    ResumenTOB.beneficiaryBankCountryCode=$("#FC_CountryBank").get(0).value;

    if($("#FC_BankCodeIB").get(0).value=="-2" || $("#FC_BankCodeIB2").get(0).value.length == 0 ){
        ResumenTOB.intermediaryBankCodeType="";
    }else{
        ResumenTOB.intermediaryBankCodeType=$('#FC_BankCodeIB :selected').html();
    }

    ResumenTOB.intermediaryBankCodeNumber=$("#FC_BankCodeIB2").get(0).value;
    ResumenTOB.intermediaryBankName=$("#FC_NameBankIB").get(0).value;
    ResumenTOB.intermediaryBankAddress1=$("#FC_AddressLineBankIB1").get(0).value;
    ResumenTOB.intermediaryBankAddress2=$("#FC_AddressLineBankIB2").get(0).value;
    ResumenTOB.intermediaryBankAddress3=$("#FC_AddressLineBankIB3").get(0).value;
    if($("#FC_CountryBankIB").get(0).value=="-2"){
        ResumenTOB.intermediaryBankCountry="";
        ResumenTOB.intermediaryBankCountryCode="";
    }else{
        ResumenTOB.intermediaryBankCountry=$('#FC_CountryBankIB :selected').html();
        ResumenTOB.intermediaryBankCountryCode=$("#FC_CountryBankIB").get(0).value;
    }


    ResumenTOB.furtherCreditAccount=$("#FC_AccountNumberFFC").get(0).value;
    ResumenTOB.furtherCreditName=$("#FC_NameFFC").get(0).value;
//    ResumenTOB.amount=parseFloat($("#AmmountAI").get(0).value);
    ResumenTOB.amount=$("#FC_AmmountAI").get(0).value;
//    ResumenTOB.amount="0";
    ResumenTOB.recieverName=$("#FC_ReceiverInformation").get(0).value;
    ResumenTOB.motivo=$("#FC_Motivo").val();

    jsonTransfers[0]= ResumenTOB;

    $(".TOB_borrarData").each(function(){
        $("#"+this.id).html("");
    });

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,FC_TransfersValidateSuccess,null,null);
}

function FC_TransfersValidateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var resumen;
    var existeBen =result.existeBen;
    var existeInt = result.existeInt;
    idioma = result.idioma;

    //$("#marco_trabajo").css("height","900px");
    resumen = result.resumenTOB;
    resumentransferToOtherBankFC = result.resumenTOB;


    $("#FC_RAccounts").html(quitarSaldo($('#FC_Accounts :selected').html()));
    $("#FC_Rname").html($('#FC_name').val());
    $("#FC_RAccountNumber").html($('#FC_AccountBank :selected').html()+"  |  "+$("#FC_AccountNumber").get(0).value);
    $("#FC_RbeneficiaryEmail").html($("#FC_beneficiaryEmail").get(0).value);
    $("#FC_RAddressLine1").html($("#FC_AddressLine1").get(0).value);
    if($("#FC_AddressLine2").get(0).value !=""){
        $("#FC_div_RAddressLine2").fadeIn("fast");
        $("#FC_RAddressLine2").html($("#FC_AddressLine2").get(0).value);
    }
    if($("#FC_AddressLine3").get(0).value !=""){
        $("#FC_div_RAddressLine3").fadeIn("fast");
        $("#FC_RAddressLine3").html(Trim($("#FC_AddressLine3").get(0).value));
    }
    if(Trim($("#FC_TelephoneNumber").get(0).value) !=""){
        $("#FC_div_RTelephoneNumber").fadeIn("fast");
        $("#FC_RTelephoneNumber").html($("#FC_TelephoneNumber").get(0).value);
    }

    $("#FC_RCountry").html($('#FC_Country :selected').html());
    $("#FC_RBankCode").html($('#FC_BankCode :selected').html()+"  |  "+$('#FC_BankCode2').val());
    $("#FC_RNameBank").html(resumen.beneficiaryBankName);


    $("#FC_RAddressLineBank1").html(resumen.beneficiaryBankAddress1);
    if(Trim($("#FC_AddressLineBank2").get(0).value) !=""){
        $("#FC_div_RAddressLineBank2").fadeIn("fast");
        $("#FC_RAddressLineBank2").html($("#FC_AddressLineBank2").get(0).value);
    }

    if(Trim($("#FC_AddressLineBank3").get(0).value) !=""){
        $("#FC_div_RAddressLineBank3").fadeIn("fast");
        $("#FC_RAddressLineBank3").html($("#FC_AddressLineBank3").get(0).value);
    }

    $("#FC_RCountryBank").html($('#FC_CountryBank :selected').html());

    if(Trim($("#FC_BankCodeIB2").get(0).value) !="" || Trim($("#FC_NameBankIB").get(0).value) !="" || Trim($("#FC_AddressLineBankIB1").get(0).value) !=""
        || Trim($("#FC_AddressLineBankIB2").get(0).value) !="" || Trim($("#FC_AddressLineBankIB3").get(0).value) !="" || $('#FC_CountryBankIB :selected').val() !="-2") {
        $("#FC_div_TOB_resumen_intermediary").fadeIn("fast");
    }else{
        $("#FC_div_TOB_resumen_intermediary").attr("style","display: none");
    }
    if(Trim($("#FC_BankCodeIB2").get(0).value) !=""){
        $("#FC_div_RBankCodeIB").fadeIn("fast");
        $("#FC_RBankCodeIB").html($('#FC_BankCodeIB :selected').html()+"  |  "+Trim($("#FC_BankCodeIB2").get(0).value));
    }else{
        $("#FC_div_RBankCodeIB").attr("style","display: none");
    }
    if(Trim($("#FC_NameBankIB").get(0).value) !=""){
        $("#FC_div_RNameBankIB").fadeIn("fast");
        $("#FC_RNameBankIB").html(Trim($("#FC_NameBankIB").get(0).value));
    }else{
        $("#FC_div_RNameBankIB").attr("style","display: none");
    }
    if(Trim($("#FC_AddressLineBankIB1").get(0).value) !=""){
        $("#FC_div_RAddressLineBankIB1").fadeIn("fast");
        $("#FC_RAddressLineBankIB1").html(Trim($("#FC_AddressLineBankIB1").get(0).value));
    }else{
        $("#FC_div_RAddressLineBankIB1").attr("style","display: none");
    }
    if(Trim($("#FC_AddressLineBankIB2").get(0).value) !=""){
        $("#FC_div_RAddressLineBankIB2").fadeIn("fast");
        $("#FC_RAddressLineBankIB2").html(Trim($("#FC_AddressLineBankIB2").get(0).value));
    }else{
        $("#FC_div_RAddressLineBankIB").attr("style","display: none");
    }
    if(Trim($("#FC_AddressLineBankIB3").get(0).value) !=""){
        $("#FC_div_RAddressLineBankIB3").fadeIn("fast");
        $("#FC_RAddressLineBankIB3").html(Trim($("#FC_AddressLineBankIB3").get(0).value));
    }else{
        $("#FC_div_RAddressLineBankIB3").attr("style","display: none");
    }

    if($('#FC_CountryBankIB :selected').val() !="-2"){
        $("#FC_div_RCountryBankIB").fadeIn("fast");
        $("#FC_RCountryBankIB").html($('#FC_CountryBankIB :selected').html());
    }else{
        $("#FC_div_RCountryBankIB").attr("style","display: none");
    }
    if(Trim($("#FC_AccountNumberFFC").get(0).value) !="" || Trim($("#FC_NameFFC").get(0).value) !=""){
        $("#FC_div_TOB_resumen_furtherCredit").fadeIn("fast");
    }else{
        $("#FC_div_TOB_resumen_furtherCredit").attr("style","display: none");
    }
    if(Trim($("#FC_AccountNumberFFC").get(0).value) !=""){
        $("#FC_div_RAccountNumberFFC").fadeIn("fast");
        $("#FC_RAccountNumberFFC").html(Trim($("#FC_AccountNumberFFC").get(0).value));
    }else{
        $("#FC_div_RAccountNumberFFC").attr("style","display: none");
    }
    if(Trim($("#FC_NameFFC").get(0).value) !=""){
        $("#FC_div_RNameFFC").fadeIn("fast");
        $("#FC_RNameFFC").html(Trim($("#FC_NameFFC").get(0).value));
    }else{
        $("#FC_div_RNameFFC").attr("style","display: none");
    }

    Trim($("#FC_RAmmountAI").html($("#FC_AmmountAI").get(0).value)+" "+"USD");
    $("#FC_RMotivo").html($("#FC_Motivo").get(0).value);
    if(Trim($("#FC_ReceiverInformation").get(0).value) !=""){
        $("#FC_div_RReceiverInformation").fadeIn("fast");
        $("#FC_RReceiverInformation").html(Trim($("#FC_ReceiverInformation").get(0).value));
    }else{
        $("#FC_div_RReceiverInformation").attr("style","display: none");
    }



    var mensaje ="";
    if(existeBen == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The code of the Beneficiary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo del Banco Beneficiario.<br>";

    }
    if(existeInt == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The code of the Intermediary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo del Banco Intermediario.<br>";
    }

    if (existeInt == 0 || existeBen == 0){
        if(idioma=="_us_en"){
            $("#FC_btn__resumen_cancelar").val("Back");
            mensaje = mensaje + "If problems persist, please contact your Financial Advisor or Account Executive. <br>";
        }
        else{
            $("#FC_btn__resumen_cancelar").val("Volver");
            mensaje = mensaje + "En caso de persistir los inconvenientes, comun&iacute;quese con su Asesor Financiero o Ejecutivo de Cuenta. <br>";
        }

        $("#FC_btn__resumen_aceptar").fadeOut("fast");


    }else{
        if(idioma=="_us_en")
            $("#FC_btn__resumen_cancelar").val("Back");
        else
            $("#FC_btn__resumen_cancelar").val("Volver");
        $("#FC_btn__resumen_aceptar").attr("style","display: ");
    }


    if(mensaje!=""){

        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#div_mensajes_error").fadeOut("fast");
        $("#FC_createToOtherBank").fadeOut("fast");
        $("#FC_summaryToOtherBank").fadeIn("fast");

    }



}


function FC_TransferGuardarJSONData(){
    var url = urlTransfersSaveTOBFC;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};
//    resumentransferToOtherBankFC.amount = parseFloat($("#AmmountAI").get(0).value.trim());
    resumentransferToOtherBankFC.amount = Trim($("#FC_AmmountAI").get(0).value);
    resumentransferToOtherBankFC.claveTemporal = Trim($("#FC_pwdClaveConfirmTransfer_TOB").get(0).value);
    jsonTransfers[0]= resumentransferToOtherBankFC;


    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,FC_TransfersGuardarSuccess,null,null);
}


function FC_TransfersGuardarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var numRef = result.numref;
    idioma = result.idioma;
    var mensaje = result.mensaje;
    resumentransferToOtherBankFC = result.resumenTOB;

    var exito = result.code;
//    $("#summaryToOtherBank").fadeOut("fast");
//    $("#resultToOtherBank").fadeIn("fast");



    if(exito!="0"){
//        $("#FC_createToOtherBank").attr("style","display: block");
//        $("#FC_summaryToOtherBank").attr("style","display: none");
//        $("#mensaje_error").empty();
//        $("#div_mensajes_error").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        //FC_infoPaginaJSONData();
//        $("#img_resultadoTransferTOB").attr("src","../vbtonline/resources/images/error.png");
    }else{
        $("#FC_div_numref_TOB").attr("style","display: ");
        $("#FC_div_estatus_TOB").attr("style","display: ");
        $("#FC_tituloExitoso_TOB").attr("style","display: block");

        $("#FC_resumenBotones_TOB_Finales").attr("style","display: ");
//        $("#txt_resultadoTransferTOB").html(result.mensaje);
        if(idioma=="_us_en")
            $("#FC_status_TOB").html("In Process");
        else
            $("#FC_status_TOB").html("En Proceso");
        $("#FC_numRef_TOB").html(numRef);
        $("#FC_summaryToOtherBank #FC_clave_TOB").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("slow");
        /*carga previa de la pagina de template*/
        //infoPaginaTemplateJSONData();
        $("#Template_createTemplate .selectFormulario_templateAdd").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#Template_createTemplate .inputFormulario_templateAdd").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
//        $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
//        $("#img_resultadoTransferTOB").attr("src","../vbtonline/resources/images/exito.png");
    }


}

function FC_TransferGenerarClaveJSONData(){
    var url = urlTransfersGenerarClaveTOBFC;
    var param={};

    sendServiceJSON(url,param,FC_TransferGenerarClaveSuccess,null,null);
}


function FC_TransferGenerarClaveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
//    alert("se ha enviado una clave a su correo");

}

function FC_ValidarClaveTransferJSONData(){
    var url = urlValidarClaveTOBFC;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#FC_pwdClaveConfirmTransfer_TOB").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,FC_ValidarClaveTransferSuccess,null,null);
}


function FC_ValidarClaveTransferSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.claveValida;
    idioma = result.idioma;



    if(exito=="OK"){
        FC_TransferGuardarJSONData();
    }else if (exito=="NO OK"){
        $("#FC_btn_aceptarClave").attr("disabled",false);
        if(idioma=="_us_en")
            mensaje ="The Transaction Key you entered is wrong"+"<br>"+"If you failed three times entering your Transaction Key, this transfer operation will be canceled";
        else
            mensaje ="Clave de confirmaci&oacute;n de operaci&oacute;n incorrecta."+"<br>"+"Si se equivoca tres veces colocando su clave de operaciones, Esta transferencia ser&aacute; cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        FC_infoPaginaJSONData();
        if(idioma=="_us_en")
            mensaje ="The Transaction Key you entered is wrong for third time, your transfer operation was canceled";
        else
            mensaje ="La clave de confirmaci&oacute;n que ingres&oacute; es incorrecta por tercera vez consecutiva, su transferencia ha sido cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}



function FC_cargarSalvarTemplateTransferSuccess(){
    //                   this is the json return data

    //$("#marco_trabajo").css("height","750px");

    $("#Template_BankCode").val(resumentransferToOtherBankFC.beneficiaryBankCodeType);
    $("#Template_BankCode2").val(resumentransferToOtherBankFC.beneficiaryBankCodeNumber);
    $("#Template_NameBank").val(resumentransferToOtherBankFC.beneficiaryBankName);
    $("#Template_AddressLineBank1").val(resumentransferToOtherBankFC.beneficiaryBankAddress1);
    $("#Template_AddressLineBank2").val(resumentransferToOtherBankFC.beneficiaryBankAddress2);
    $("#Template_AddressLineBank3").val(resumentransferToOtherBankFC.beneficiaryBankAddress3);
    $("#Template_CountryBank").val(resumentransferToOtherBankFC.beneficiaryBankCountryCode);

    $("#Template_name").val(resumentransferToOtherBankFC.beneficiaryName);
    $("#Template_AccountBank").val(resumentransferToOtherBankFC.beneficiaryAccountBank);
    $("#Template_AccountNumber").val(resumentransferToOtherBankFC.beneficiaryAccount);
    $("#Template_beneficiaryEmail").val(resumentransferToOtherBankFC.beneficiaryEmail);
    $("#Template_AddressLine1").val(resumentransferToOtherBankFC.beneficiaryAddress1);
    $("#Template_AddressLine2").val(resumentransferToOtherBankFC.beneficiaryAddress2);
    $("#Template_AddressLine3").val(resumentransferToOtherBankFC.beneficiaryAddress3);
    $("#Template_TelephoneNumber").val(resumentransferToOtherBankFC.beneficiaryTelephone);
    $("#Template_Country").val(resumentransferToOtherBankFC.beneficiaryCountryCode);

    if(resumentransferToOtherBankFC.intermediaryBankCodeType=="ACCOUNT" && Trim(resumentransferToOtherBankFC.intermediaryBankCodeNumber)!=""){
        $("#Template_BankCodeIB").val(resumentransferToOtherBankFC.intermediaryBankCodeType);
    }else{
        $("#Template_BankCodeIB").val("-2");
    }
    $("#Template_BankCodeIB").val(resumentransferToOtherBankFC.intermediaryBankCodeType);
    $("#Template_BankCodeIB2").val(resumentransferToOtherBankFC.intermediaryBankCodeNumber);
    $("#Template_NameBankIB").val(resumentransferToOtherBankFC.intermediaryBankName);
    $("#Template_AddressLineBankIB1").val(resumentransferToOtherBankFC.intermediaryBankAddress1);
    $("#Template_AddressLineBankIB2").val(resumentransferToOtherBankFC.intermediaryBankAddress2);
    $("#Template_AddressLineBankIB3").val(resumentransferToOtherBankFC.intermediaryBankAddress3);
    $("#Template_CountryBankIB").val(resumentransferToOtherBankFC.intermediaryBankCountryCode);

    $("#Template_AccountNumberFFC").val(resumentransferToOtherBankFC.furtherCreditAccount);
    $("#Template_NameFFC").val(resumentransferToOtherBankFC.furtherCreditName);


    $("#Template_nombreTemplate").val(resumentransferToOtherBankFC.beneficiaryName);



}
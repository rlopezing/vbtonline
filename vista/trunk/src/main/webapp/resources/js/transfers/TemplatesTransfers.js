var urlTransfersCargar="Transfers_cargar.action";
var urlDirectorioCargar="Transfers_consultarDirectorio.action";
var urlDirectorioEditarCargar="Transfers_cargarDetalleDirectorio.action";
var urlDirectorioEditarSalvar="Transfers_editarDetalleDirectorio.action";
var urlDirectorioSalvarNuevo="Transfers_salvarNuevoDirectorio.action";
var urlDirectorioBorrar="Transfers_borrarDirectorio.action";
var urlTemplateValidateTOB="Transfers_validateToOtherBanks.action";
var urlTransfersCargarTemplateLog="Transfers_guardarLogTemplate.action";

var idTemplate;
var datos="";
var idioma = "";
var noInfo = "";
$(document).ready(function(){


    $("#Template_DateBirthIB").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#Template_DateBirthIB" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true,yearRange: "-100:+0"});


    $("#Template_TipoPersona").change(function() {
        fieldsetBeneficiaryInformationTemplate();
    });


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
        //$("#btn_TemplateGuardar_TOB_cancel").addClass("oculto");
        if ($("#tipo_contrato_app").val()=="FC"){
            $("#DIV_INFO_EXTERNA_FI").remove();
        }else{
            $("#DIV_INFO_EXTERNA_FC").remove();
        }
        $("#btn_TOB_volver").removeClass("oculto");
        //Elimina las que no son en dolares
        $("#Accounts option").each(function(){
            var texto=$(this).text();
            if ((texto.indexOf("USD") < 0)&&(texto!="Select")){
                $(this).remove();
            }
        });
        origenMetodosValidacion="transferenciasOtrosBancos";
        var mensaje =  validateTemplate();
               if (makeTranfers=="1" && mensaje==""){
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

                   $('#btnCodBancoBuscar').attr("disabled", true);
                   $('#btnCodBancoBuscarSWIFT').attr("disabled", true);
                   $('#btnCodBancoBuscarIB').attr("disabled", true);
                   $('#btnCodBancoBuscarIBSWIFT').attr("disabled", true);

                   $("#name").attr("readonly", true);
                   $("#AccountBank").attr("disabled", true);
                   $("#AccountNumber").attr("readonly", true);
                   $("#beneficiaryEmail").attr("readonly", true);
                   $("#AddressLine1").attr("readonly", true);
                   $("#AddressLine2").attr("readonly", true);
                   //$("#AddressLine3").attr("readonly", true);
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

                   $("#MotivoAI").attr("style","display:none");
                   $("#MotivoAI").val("");
                   $("#Motivos").attr("readonly", false);

                   $("#FullNameIndividualIB").attr("readonly", true);
                   $("#DateBirthIB").attr("readonly", true);
                   $("#NationalityIB").attr("disabled", true);
                   $("#IdPassportIB").attr("readonly", true);
                   $("#FullNameIB").attr("readonly", true);
                   $("#CountryIncorporationIB").attr("disabled", true);



                   if($("#Template_TipoPersona").get(0).value!="-2"){
                       $("#TipoPersona").attr("disabled", true);
                       $("#lastname1").attr("readonly", true);
                       $("#lastname2").attr("readonly", true);
                       $("#name").attr("readonly", true);
                   }else{
                       $("#TipoPersona").attr("disabled", false);
                       $("#lastname1").attr("readonly", false);
                       $("#lastname2").attr("readonly", false);
                       $("#name").attr("readonly", false);
                   }



                   if(motivos!=null){
                       if(idioma=="_us_en")
                           cargar_selectAtributosPersonal("Motivos", motivos,"Select","-2");
                       else
                           cargar_selectAtributosPersonal("Motivos", motivos,"Seleccione","-2");
                   }
                   if(nacionalidadBI!=null){
                       if(idioma=="_us_en") {
                           cargar_selectPersonal("NationalityIB", nacionalidadBI,"Select","-2");
                       }else{
                           cargar_selectPersonal("NationalityIB", nacionalidadBI,"Seleccione","-2");
                       }
                   }
                   if(paisesBI!=null){
                       if(idioma=="_us_en") {
                           cargar_selectPersonal("CountryIncorporationIB", paisesBI,"Select","-2");
                       }else{
                           cargar_selectPersonal("CountryIncorporationIB", paisesBI,"Seleccione","-2");
                       }
                   }
                   $("#NationalityIB").val($("#Template_NationalityIB").val());
                   $("#CountryIncorporationIB").val($("#Template_CountryIncorporationIB").val());

                   if(idioma=="_ve_es")
                       $("#transferencias_TAGMsgSoporteMotivo").html(
                           $('<div class="Nofiles" style="position:relative;height:17px;width:300px;"/>').text('No hay archivos seleccionados '));
                   if(idioma=="_us_en")
                       $("#transferencias_TAGMsgSoporteMotivo").html(
                           $('<div class="Nofiles" style="position:relative;height:17px;width:300px;"/>').text('No files were selected'));

                   $('#progress .bar').css('width','0%');


                   $('.percent').html('0%');

                   archivosSubidos="";
                   archivosTotales=0;
                   archivosaSubir=0;


                   fieldsetBeneficiaryInformationTransferencia();
                   //
               }else{

                   $("#mensaje_error").empty();
                   $("#mensaje_error").html(mensaje);
                   /*$("#mensaje_error").html(makeTranfers);*/
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
        $("#TAG_INFO_TEMPLATE_ADD").removeClass("oculto");
        $("#TAG_INFO_TEMPLATE_EDIT").addClass("oculto");
        $("#Templateform_7").attr("style","display:none");
        $("#Templateform_8").attr("style","display:none");
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

        $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
        $("#Template_lastname1").val("");
        $("#Template_lastname2").val("");


        //$("#marco_trabajo").css("height","750px");

       // cargarData_TemplateToOtherBank();
        newTemplateJSONData();

    });


    $("#Template_btn_TOB_aprobar").live('click', function () {
        idTemplate=$("#templateID").val();
        //Se llama a la pantalla de metodos de validacion
        mainValidationMethods("aprobarTemplate");

    });


    $("#Template_btn_TOB_aceptar").click(function (){
        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;

        /*$("#BankCodeSWIFTtext").val("Template_SwiftBankCodeIB2");
        $("#BankCodeIBSWIFTtext").val("Template_SwiftBankCode2");        */

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

        if (Trim($("#Template_BankCode").get(0).value)== "-2") {
            // no se ha seleccionado el tipo de codigo

            if (Trim($("#Template_BankCode2").get(0).value)== "" && Trim($("#Template_SwiftBankCode2").get(0).value)== "")  {
                //campo codigo es vacio , no hay error
                $("#Template_BankCode").removeClass("error_campo");
                $("#Template_BankCode2").removeClass("error_campo");

            }else{
                //campo codigo tiene valor , debe seleccionar un tipo codigo

                $("#Template_BankCode").addClass("error_campo");
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field<br>";
                else
                    mensaje=mensaje+"Campo requerido<br>";
            }

        }else{
            // esta seleccionado un tipo de codigo

            if (Trim($("#Template_BankCode2").get(0).value)== "" && Trim($("#Template_SwiftBankCode2").get(0).value)== "") {
                // campo codigo es vacio, debe llenar el campo codigo
                $("#Template_SwiftBankCode2").addClass("error_campo");
                $("#Template_BankCode2").addClass("error_campo");
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field<br>";
                else
                    mensaje=mensaje+"Campo requerido<br>";

            }else{
                //campo codigo es vacio , no hay error
                $("#Template_SwiftBankCode2").removeClass("error_campo");
                $("#Template_BankCode2").removeClass("error_campo");
            }
        }

        /*if (Trim($("#Template_BankCodeIB").get(0).value)== "-2") {
            // no se ha seleccionado el tipo de codigo

            if (Trim($("#Template_BankCodeIB2").get(0).value)== "") {
                //campo codigo es vacio , no hay error
                $("#Template_BankCodeIB").removeClass("error_campo");
                $("#Template_BankCodeIB2").removeClass("error_campo");
            }else{
                //campo codigo tiene valor , debe seleccionar un tipo codigo

                $("#Template_BankCodeIB").addClass("error_campo");
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field<br>";
                else
                    mensaje=mensaje+"Campo requerido<br>";
            }

        }else{ */
            // esta seleccionado un tipo de codigo

           /* if (Trim($("#Template_SwiftBankCodeIB2").get(0).value)== "" ) {
                // campo codigo es vacio, debe llenar el campo codigo
                $("#Template_SwiftBankCodeIB2").addClass("error_campo");
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field<br>";
                else
                    mensaje=mensaje+"Campo requerido<br>";

            }else{
                //campo codigo es vacio , no hay error
                $("#Template_SwiftBankCodeIB2").removeClass("error_campo");
                //$("#Template_BankCodeIB2").removeClass("error_campo");
            } */


        //}




        if(mensaje ==""){
            /*validaciones*/
            if(idioma =="_us_en"){
                if (!isAlphanumericWithSpace($("#Template_name").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                    $("#Template_name").addClass("error_campo");
                    invalido ="1";
                }


                /* Validaciones Campos Nuevos (BeneficiaryInformation)*/

                if($("#Template_TipoPersona").get(0).value == "NAT"){

                    if (!isAlphanumericWithSpace($("#Template_FullNameIndividualIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }

                    if($("#Template_FullNameIndividualIB").get(0).value.length < 3 || $("#Template_FullNameIndividualIB").get(0).value.length > 50){
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#Template_FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }

                    if (!isDate($("#Template_DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                        $("#Template_DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#Template_NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                        $("#Template_NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#Template_IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                        $("#Template_IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#Template_TipoPersona").get(0).value == "JUR"){
                    if ($("#Template_FullNameIB").get(0).value.length < 3 && $("#Template_FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The entity Full Name consists \n of a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#Template_FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#Template_FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#Template_FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#Template_CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                        $("#Template_CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /* fin Campos nuevos (beneficiaryInformation Ingles )*/

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

                if ($("#Template_BankCode").get(0).value == "ABA" && Trim($("#Template_CountryBank").get(0).value) == "US") {
                //if ($("#Template_BankCode2").get(0).value != "") {
                    if ($("#Template_BankCode2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCode2").get(0).value)) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                //if ($("#Template_BankCode").get(0).value == "SWIFT") {
                /*if (Trim($("#Template_SwiftBankCode2").get(0).value)== "") {
                    mensaje = mensaje + "Beneficiary Bank: Indicate the code of Beneficiary Bank."+"<br>";
                    $("#Template_SwiftBankCode2").addClass("error_campo");
                    invalido ="1";
                }else*/

                if (Trim($("#Template_SwiftBankCode2").get(0).value) != "") {
                    if ($("#Template_SwiftBankCode2").get(0).value.length != 8 && $("#Template_SwiftBankCode2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                        $("#Template_SwiftBankCode2").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#Template_SwiftBankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCode2").get(0).value.substring(6, $("#Template_SwiftBankCode2").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                        $("#Template_SwiftBankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                //}

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

                    if ($("#Template_BankCodeIB").get(0).value == "ABA" && $("#Template_CountryBankIB").get(0).value == "US") {
                    //if ($("#Template_BankCodeIB2").get(0).value != "") {
                        if ($("#Template_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCodeIB2").get(0).value)) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    //if ($("#Template_BankCodeIB").get(0).value == "SWIFT") {
                    /*if (Trim($("#Template_SwiftBankCodeIB2").get(0).value)== "") {
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }else {  */
                    if (Trim($("#Template_SwiftBankCodeIB2").get(0).value)!= "") {
                        if ($("#Template_SwiftBankCodeIB2").get(0).value.length != 8 && $("#Template_SwiftBankCodeIB2").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                            $("#Template_SwiftBankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        } else if (!isAlphabetic($("#Template_SwiftBankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCodeIB2").get(0).value.substring(6, $("#Template_SwiftBankCodeIB2").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                            $("#Template_SwiftBankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    //}

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
                        mensaje = mensaje + "Intermediary:The Intermediary Bank City contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_AddressLineBankIB3").addClass("error_campo");
                        invalido ="1";
                    }

                    if ($("#Template_CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>"
                        $("#Template_CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }

                    /*if(Trim($("#Template_SwiftBankCode2").get(0).value) ==""){
                        $("#Template_SwiftBankCode2").val("");
                        $("#Template_SwiftBankCode").val("");
                    }  */


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

                if($("#Template_TipoPersona").get(0).value=="NAT"){

                    if ($("#Template_lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#Template_lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#Template_lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#Template_lastname1").addClass("error_campo");
                            invalido ="1";
                        }
                    }


                    $("#Template_lastname2").removeClass("error_campo");
                    if ($("#Template_lastname2").val().length >0 && $("#Template_lastname2").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid second lastname"+"<br>";
                        $("#Template_lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#Template_lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#Template_lastname2").addClass("error_campo");
                            invalido ="1";
                        }else{
                            $("#Template_lastname2").removeClass("error_campo");
                        }
                    }


                }



            }else{
                if (!isAlphanumericWithSpace($("#Template_name").get(0).value) ) {
                    mensaje = mensaje + "Beneficiario: El Nombre del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                    $("#Template_name").addClass("error_campo");
                    invalido ="1";
                }
                /* Validaciones Campos Nuevos (BeneficiaryInformation)*/

                if($("#Template_TipoPersona").get(0).value == "NAT"){
                    if ($("#Template_FullNameIndividualIB").get(0).value.length < 3 && $("#Template_FullNameIndividualIB").get(0).value.length > 50 ){
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste \n de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                        $("#Template_FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#Template_FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                            $("#Template_FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#Template_DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                        $("#Template_DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#Template_NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                        $("#Template_NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#Template_IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#Template_IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#Template_TipoPersona").get(0).value == "JUR"){
                    if ($("#Template_FullNameIB").get(0).value.length < 3 && $("#Template_FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste \n de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                        $("#Template_FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#Template_FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#Template_FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#Template_CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                        $("#Template_CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /* fin Campos nuevos (beneficiaryInformation español)*/

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

                if ($("#Template_BankCode").get(0).value == "ABA" && $("#Template_CountryBank").get(0).value == "US") {
                //if ($("#Template_BankCode2").get(0).value != "") {
                    if ($("#Template_BankCode2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCode2").get(0).value)) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>"
                        $("#Template_BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if ($("#Template_SwiftBankCode2").get(0).value != "") {
                    if ($("#Template_SwiftBankCode2").get(0).value.length != 8 && $("#Template_SwiftBankCode2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_SwiftBankCode2").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#Template_SwiftBankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCode2").get(0).value.substring(6, $("#Template_BankCode2").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_SwiftBankCode2").addClass("error_campo");
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

                    if ($("#Template_BankCodeIB").get(0).value == "ABA" && $("#Template_CountryBankIB").get(0).value == "US") {
                    //if ($("#Template_BankCodeIB2").get(0).value != "") {
                        if ($("#Template_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCodeIB2").get(0).value)) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>"
                            $("#Template_BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if ($("#Template_SwiftBankCodeIB2").get(0).value != "") {
                        if ($("#Template_SwiftBankCodeIB2").get(0).value.length != 8 && $("#Template_SwiftBankCodeIB2").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                            $("#Template_SwiftBankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                        if (!isAlphabetic($("#Template_SwiftBankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCodeIB2").get(0).value.substring(6, $("#Template_SwiftBankCodeIB2").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                            $("#Template_SwiftBankCodeIB2").addClass("error_campo");
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
                if($("#Template_TipoPersona").get(0).value=="NAT"){

                    if ($("#Template_lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                        $("#Template_lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#Template_lastname1").val())) {
                            mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                            $("#Template_lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }

                    $("#Template_lastname2").removeClass("error_campo");
                    if ($("#Template_lastname2").val().length >0 && $("#Template_lastname2").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                        $("#Template_lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabeticPunto($("#Template_lastname2").val())) {
                            mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                            $("#Template_lastname2").addClass("error_campo");
                            invalido ="1";
                        } else {
                            $("#Template_lastname2").removeClass("error_campo");
                        }
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



                if ($("#Template_lastname2").val().length==0 ) {
                    if(idioma=="_us_en") {
                        $( "#dialog-confirm p").text("Due to compliance with the Regulations Related to Risk Management Related to Anti Money Laundering and in particular with reference to the Know Your Customer Policy and Due Diligence standards, we ask you to complete the required information in the appropriate fields. In case any of the fields is not properly completed, the ordering Client assumes the total, absolute and full responsibility for the charges and delays that may be generated by the returns executed by correspondent banks, intermediaries and / or final beneficiaries, on the operations so ordered." );
                        $( "#dialog-confirm" ).dialog({
                            resizable: false,
                            height: "auto",
                            width: 600,
                            // modal: true,
                            position: {
                                my: "center",
                                at: "center",
                                of: $('#Template_btnCodBancoBuscarIB')
                            },
                            buttons: {
                                "Cancel": function() {
                                    $("#Template_lastname2").addClass("error_campo");
                                    $( this ).dialog( "close" );
                                },
                                Continue: function() {
                                    TemplateValidateJSONData();
                                    $( this ).dialog( "close" );
                                }
                            }
                        });

                    } else {
                        $( "#dialog-confirm p").text("Trabajando en cumplimiento a las Normas Relativas a la Administración de Riesgos Relacionados con Legitimación de Capitales y muy particularmente en referencia a la Política Conozca a su Cliente y los estándares de Debida Diligencia, solicitamos de ustedes se complete la información requerida en los campos correspondientes. En caso de que alguno de los campos no sea debidamente completado, El Cliente ordenante, asume la total, absoluta y plena responsabilidad por los cargos y demoras que pudieran generar las devoluciones que ejecuten los bancos corresponsales, intermediarios y/o beneficiarios finales, sobre las operaciones así ordenadas." );
                        $( "#dialog-confirm" ).dialog({
                            resizable: false,
                            height: "auto",
                            width: 600,
                            // modal: true,
                            position: {
                                my: "center",
                                at: "center",
                                of: $('#Template_btnCodBancoBuscarIB')
                            },
                            buttons: {
                                "Cancelar": function() {
                                    $("#Template_lastname2").addClass("error_campo");
                                    $( this ).dialog( "close" );
                                },
                                Continuar: function() {
                                    TemplateValidateJSONData()
                                    $( this ).dialog( "close" );
                                }
                            }
                        });

                    }
                } else {
                    TemplateValidateJSONData();
                }
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

        if (tmpTranfers=="OK"){
            seleccionarOpcionBack("TRANSFERENCIA_EXTERNA");
        }else{
            $("#Template_createTemplate").fadeOut("fast");
            $("#Template_consulta").fadeIn("fast");
            $("#accionTemplate").val("");
            infoPaginaTemplateConsultaJSONData();
        }


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

        $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
        $("#Template_name").val("");

        if(datos!=""){

            if (datos.beneficiaryTypePerson==""){
                $("#Template_TipoPersona").val("-2");
                $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
            }else{

                if (datos.beneficiaryTypePerson=="NAT"){
                    $("div[name='Template_transferencias_apellidos']").removeClass("ocultoCampo");
                    $("#Template_lastname1").val(datos.beneficiaryLastName1);
                    $("#Template_lastname2").val(datos.beneficiaryLastName2);
                    $("#Template_FullNameIndividualIB").val(datos.beneficiaryFullName);
                    $("#Template_DateBirthIB").val(datos.beneficiaryBirthDate);
                    $("#Template_NationalityIB").val(datos.beneficiaryNationality);
                    $("#Template_IdPassportIB").val(datos.beneficiaryIdPassport);
                }else{
                    $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
                    $("#Template_FullNameIB").val(datos.beneficiaryFullName);
                    $("#Template_CountryIncorporationIB").val(datos.beneCountryIncorporation);
                }

                $("#Template_TipoPersona").val(datos.beneficiaryTypePerson);
            }



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

    $("#Template_TipoPersona").change(function(){
        /*Cuando se realiza el cambio de tipo de persona[Natural o juridico]
         se muestran o se coultan los campos correspondiente a los apellidos de
         los beneficiarios, y se coloca la clase que indica si son campos obligatorios
         */
        $("#Template_lastname1").removeClass("error_campo");
        $("#Template_lastname2").removeClass("error_campo");

        if($("#Template_TipoPersona").val()=='NAT'){
            $("div[name='Template_transferencias_apellidos']").removeClass("ocultoCampo");
            $("#Template_lastname1").addClass("obligatorio_Template");
          //  $("#Template_lastname2").addClass("obligatorio_Template");
        } else {
            $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
            $("#Template_lastname1").removeClass("obligatorio_Template");
          //  $("#Template_lastname2").removeClass("obligatorio_Template");
            $("#Template_lastname1").val("");
            $("#Template_lastname2").val("");
        }
    });




});

function fieldsetBeneficiaryInformationTemplate(){
    if($("#Template_TipoPersona").get(0).value != "-2"){
        $("#Templateform_8").attr("style","display:block");
        if($("#Template_TipoPersona").get(0).value == "NAT"){
            /* Remover Tabla de Companies */
            $("#Template_TableCompanies").attr("style","display:none");
            $("#Template_FullNameIB").removeClass("obligatorio_Template");
            $("#Template_CountryIncorporationIB").removeClass("obligatorio_Template");
            /* Agregar Tabla de Individual */
            $("#Template_TableIndividual").attr("style","");
            $("#Template_FullNameIndividualIB").addClass("obligatorio_Template");
            $("#Template_DateBirthIB").addClass("obligatorio_Template");
            $("#Template_NationalityIB").addClass("obligatorio_Template");
            $("#Template_IdPassportIB").addClass("obligatorio_Template");

        }else if($("#Template_TipoPersona").get(0).value == "JUR"){
            /* Remover Tabla de Individual */
            $("#Template_TableIndividual").attr("style","display:none");
            $("#Template_FullNameIndividualIB").removeClass("obligatorio_Template");
            $("#Template_DateBirthIB").removeClass("obligatorio_Template");
            $("#Template_NationalityIB").removeClass("obligatorio_Template");
            $("#Template_IdPassportIB").removeClass("obligatorio_Template");
            /* Agregar Tabla de Companies */
            $("#Template_FullNameIB").addClass("obligatorio_Template");
            $("#Template_CountryIncorporationIB").addClass("obligatorio_Template");
            $("#Template_TableCompanies").attr("style","");
        }
    }else{
        $("#Templateform_8").attr("style","display:none");
    }
}

function hacerTransferencia(){
    //$("#marco_trabajo").css("height","1200px");
        guardarLogTemplateJSONData();
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
        $("#BeneficiaryPostalCode").val($("#Template_BeneficiaryPostalCode").val());
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



    if ($("#Template_TipoPersona").get(0).value==""){
        $("#TipoPersona").val("-2");
        // $("tr[name='transferencias_apellidos']").addClass("oculto");
        $("div[name='transferencias_apellidos']").addClass("oculto");
    }else{

        if ($("#Template_TipoPersona").get(0).value=="NAT"){
            $("#FullNameIndividualIB").val($("#Template_FullNameIndividualIB").val());
            $("#DateBirthIB").val($("#Template_DateBirthIB").val());
            $("#IdPassportIB").val($("#Template_IdPassportIB").val());
            $("#NationalityIB").val($("#Template_NationalityIB").val());
            // $("tr[name='transferencias_apellidos']").removeClass("oculto");
            $("div[name='transferencias_apellidos']").removeClass("oculto");

        }else{
            $("#FullNameIB").val($("#Template_FullNameIB").val());
            $("#CountryIncorporationIB").val($("#Template_CountryIncorporationIB").val());
            // $("tr[name='transferencias_apellidos']").addClass("oculto");
            $("div[name='transferencias_apellidos']").addClass("oculto");

        }

    }

        $("#TipoPersona").val($("#Template_TipoPersona").get(0).value);
        $("#lastname1").val($("#Template_lastname1").val());
        $("#lastname2").val($("#Template_lastname2").val());



     if ($("#Template_AddressLine3").val()!=""){
         $("#AddressLine3").attr("readonly", true);
         $("#BeneficiaryPostalCode").attr("readonly", true);
     }else{
         $("#AddressLine3").attr("readonly", false);
         $("#BeneficiaryPostalCode").attr("readonly", false);
     }

    $("#TABSoporte").css({ display: "none" });
    $("#TABSoporte2").css({ display: "none" });
    $("#TABSoporte3").css({ display: "none" });
    $("#TABSoporte4").css({ display: "none" });




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
    idTemplate=id;
    $("#Templateform_7").attr("style","display:none");
    $("#Templateform_8").attr("style","display:none");
    $("#TAG_INFO_TEMPLATE_ADD").addClass("oculto");
    $("#TAG_INFO_TEMPLATE_EDIT").removeClass("oculto");
    $("#Template_createTemplate").fadeIn("fast");
    $("#Template_consulta").fadeOut("fast");

    $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
    $("#Template_lastname1").val("");
    $("#Template_lastname2").val("");

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
    tmpTranfers="NO OK";
    // crearTablaTmp('div_tabla_Templates_consulta','tabla_Templates_consulta',columnas,datosTabla);
    crearTablaTmpV2('tabla_Templates_consulta',columnas,datosTabla,'table__row--template');
    /*var p_sclass=[
        { "sClass": "left" },
        { "sClass": "left" },
        { "sClass": "left"},
        {  "sClass": "left" },
        {  "sClass": "left" },
        {  "sClass": "center" }
    ];

    var oTable = $('#tabla_Templates_consulta').dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "aoColumns": p_sclass,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/

    var p_sclass=[
        { "sClass": "left" },
        { "sClass": "left" },
        { "sClass": "left"},
        {  "sClass": "left" },
        {  "sClass": "left" },
        {  "sClass": "center" }
    ];

    var oTable = $('#tabla_Templates_consulta').dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "aoColumns": p_sclass,
        "bSort": false,
        "bDestroy": true,
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

    transferFinalAprobada="NO OK";

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
    motivos= result.motivos;
    PaisesBeneficiario = result.paisesBeneficiario;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    validacionArchivos=result.validacionArchivos;
    cantidadMaximaArchivos=result.cantidadMaximaArchivos;
    pesoMaximoArchivos=result.pesoMaximoArchivos;
    nacionalidadBI = result.nacionalidadBI;
    paisesBI = result.paisesBI;
    validaArchivosContrato=result.validaArchivosContrato;
    /*alert("validaArchivosContrato " + validaArchivosContrato);*/

    idioma =result.idioma;

    makeTranfers =result.respuesta;
    var codSwift = [];

    //Trsnfers
    if(valorCuentas!=null){
        //cargar_selectPersonal2("Accounts", valorCuentas);
        cargar_selectCuenta("Accounts", valorCuentas,"Select","-2","0","0");
    }

    if(cuentas!=null){
      if (origenTemplate=="TMP"){
        cargar_selectPersonal2("Template_AccountBank", cuentas);
      }
      //Trsnfers
      cargar_selectPersonal2("AccountBank", cuentas);
    }

    if(motivos!=null){
        if(idioma=="_us_en")
            cargar_selectAtributosPersonal("Motivos", motivos,"Select","-2");
        else
            cargar_selectAtributosPersonal("Motivos", motivos,"Seleccione","-2");
    }

    if(paises!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonalpaisesMod("Template_Country", PaisesBeneficiario,"Select","-2","1");
            cargar_selectPersonalpaisesMod("Template_CountryBank", paises,"Select","-2","");
            cargar_selectPersonalpaisesMod("Template_CountryBankIB", paises,"Select","-2","");
        }else{
            cargar_selectPersonalpaisesMod("Template_Country", PaisesBeneficiario,"Seleccione","-2","1");
            cargar_selectPersonalpaisesMod("Template_CountryBank", paises,"Seleccione","-2","");
            cargar_selectPersonalpaisesMod("Template_CountryBankIB", paises,"Seleccione","-2","");
        }
    }
    if(nacionalidadBI!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonal("Template_NationalityIB", nacionalidadBI,"Select","-2");
        }else{
            cargar_selectPersonal("Template_NationalityIB", nacionalidadBI,"Seleccione","-2");
        }
    }
    if(paisesBI!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonal("Template_CountryIncorporationIB", paisesBI,"Select","-2");
        }else{
            cargar_selectPersonal("Template_CountryIncorporationIB", paisesBI,"Seleccione","-2");
        }
    }
    if(paises!=null){
        if(idioma=="_us_en") {
            if (origenTemplate=="TMP"){
                cargar_selectPersonalpaisesMod("Template_Country", PaisesBeneficiario,"Select","-2","1");
                cargar_selectPersonalpaisesMod("Template_CountryBank", paises,"Select","-2","");
                cargar_selectPersonalpaisesMod("Template_CountryBankIB", paises,"Select","-2","");
            }

            //Trsnfers
            cargar_selectPersonalpaisesMod("Country", PaisesBeneficiario,"Select","-2","1");
            cargar_selectPersonalpaisesMod("CountryBank", paises,"Select","-2","");
            cargar_selectPersonalpaisesMod("CountryBankIB", paises,"Select","-2","");

        }else{
            if (origenTemplate=="TMP"){
                cargar_selectPersonalpaisesMod("Template_Country", PaisesBeneficiario,"Seleccione","-2","1");
                cargar_selectPersonalpaisesMod("Template_CountryBank", paises,"Seleccione","-2","");
                cargar_selectPersonalpaisesMod("Template_CountryBankIB", paises,"Seleccione","-2","");
            }

            //Trsnfers
            cargar_selectPersonal("Country", PaisesBeneficiario,"Seleccione","-2","1");
            cargar_selectPersonal("CountryBank", paises,"Seleccione","-2","");
            cargar_selectPersonal("CountryBankIB", paises,"Seleccione","-2","");
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
                cargar_selectPersonal2("Template_BankCode", codSwift);
                cargar_selectPersonal("Template_BankCodeIB", codSwift,"Select","-2");
            }
            //Trsnfers
            cargar_selectPersonal2("BankCode", codSwift);
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
                cargar_selectPersonal2("Template_BankCode", codSwift);
                cargar_selectPersonal("Template_BankCodeIB", codSwift,"Seleccione","-2");
            }
            //Trsnfers
            cargar_selectPersonal2("BankCode", codSwift);
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
    /*$("#Template_SwiftBankCode").val(datos.beneficiaryBankCodeTypeSwift);  */
    $("#Template_SwiftBankCode2").val(datos.beneficiaryBankCodeNumberSwift);




    $("#Template_NameBank").val(datos.beneficiaryBankName);
    $("#Template_AddressLineBank1").val(datos.beneficiaryBankAddress1);
    $("#Template_AddressLineBank2").val(datos.beneficiaryBankAddress2);
    //$("#Template_AddressLineBank3").val(datos.beneficiaryBankAddress3);
    $("#Template_AddressLineBank3").val(datos.beneficiaryBankCity);
    $("#Template_CountryBank").val(datos.beneficiaryBankCountryCode);

    $("#Template_name").val(datos.beneficiaryName);


    if (datos.beneficiaryTypePerson==""){
        $("#Template_TipoPersona").val("-2");
        $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
    }else{

        if (datos.beneficiaryTypePerson=="NAT"){
            $("div[name='Template_transferencias_apellidos']").removeClass("ocultoCampo");
            $("#Template_lastname1").val(datos.beneficiaryLastName1);
            $("#Template_lastname2").val(datos.beneficiaryLastName2);
            //$("#Template_lastname2").addClass("obligatorio_Template");
            $("#Template_FullNameIndividualIB").val(datos.beneficiaryFullName);
            $("#Template_DateBirthIB").val(datos.beneficiaryBirthDate);
            $("#Template_NationalityIB").val(datos.beneficiaryNationality);
            $("#Template_IdPassportIB").val(datos.beneficiaryIdPassport);
            $("#Template_lastname1").addClass("obligatorio_Template");
        }else{
            $("div[name='Template_transferencias_apellidos']").addClass("ocultoCampo");
            $("#Template_lastname1").removeClass("obligatorio_Template");
           // $("#Template_lastname2").removeClass("obligatorio_Template");
            $("#Template_FullNameIB").val(datos.beneficiaryFullName);
            $("#Template_CountryIncorporationIB").val(datos.beneCountryIncorporation);
        }

        $("#Template_TipoPersona").val(datos.beneficiaryTypePerson);

        //Cuando abre una template ya cargada
        fieldsetBeneficiaryInformationTemplate();
    }




    $("#Template_AccountBank").val(datos.accountCode);
    $("#Template_AccountNumber").val(datos.account);
    $("#Template_beneficiaryEmail").val(datos.beneficiaryEmail);
    $("#Template_AddressLine1").val(datos.beneficiaryAddress1);
    $("#Template_AddressLine2").val(datos.beneficiaryAddress2);
    //$("#Template_AddressLine3").val(datos.beneficiaryAddress3);
    $("#Template_AddressLine3").val(datos.beneficiaryCity);
    $("#Template_BeneficiaryPostalCode").val(datos.beneficiaryPostalCode);
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
    //$("#Template_SwiftBankCodeIB").val(datos.intermediaryBankCodeTypeSwift);
    $("#Template_SwiftBankCodeIB2").val(datos.intermediaryBankCodeNumberSwift);


    $("#Template_NameBankIB").val(datos.intermediaryBankName);
    $("#Template_AddressLineBankIB1").val(datos.intermediaryBankAddress1);
    $("#Template_AddressLineBankIB2").val(datos.intermediaryBankAddress2);
    //$("#Template_AddressLineBankIB3").val(datos.intermediaryBankAddress3);
    $("#Template_AddressLineBankIB3").val(datos.intermediaryBankCity);
    $("#Template_CountryBankIB").val(datos.intermediaryBankCountryCode);

    $("#Template_AccountNumberFFC").val(datos.furtherCreditAccount);
    $("#Template_NameFFC").val(datos.furtherCreditName);


    $("#Template_nombreTemplate").val(datos.nombreTemplate);
    $("#Template_nombreTemplate").attr("disabled",false);

    validarTransfer=datos.statusAprobacion;
    if (datos.statusAprobacion=="S") {
        if(idioma=="_us_en"){
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Make a Transfer" class="form-transfer__button form-transfer__button--next button">');
        }else{
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Hacer Transferencia" class="form-transfer__button form-transfer__button--next button">');
        }
        //$("#Template_btn_transferencia").attr("style","display: ");
    }
    else{
        $("#btnMakeTransfers").html("");
        if(idioma=="_us_en")
            $("#btnAproveTemplate").html('<input  type="button" id="Template_btn_TOB_aprobar" value="Approve" class="form-transfer__button form-transfer__button--next button">');
        else
            $("#btnAproveTemplate").html('<input  type="button" id="Template_btn_TOB_aprobar" value="Aprobar" class="form-transfer__button form-transfer__button--next button">');
        //$("#Template_btn_TOB_aprobar").removeClass("oculto");
    }

    //Verifica que no sea un cargador de firmas conjuntas
   // if ($("#tipo_usuario_app").val()=="6"){
    if ($("#tipo_contrato_app").val()=="FC"){
        if(idioma=="_us_en"){
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Make a Transfer" class="form-transfer__button form-transfer__button--next button">');
        }else{
            $("#btnMakeTransfers").html('<input  type="button" id="Template_btn_transferencia" value="Hacer Transferencia" class="form-transfer__button form-transfer__button--next button">');
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
    $("#Templateform_7").attr("style","display:flex");

    if(paises==null){
        infoPaginaTransferTemplateJSONData();
    }


    if (datos.beneficiaryTypePerson==""){

        $("#mensaje_error").empty();
        cargarIdiomaJSONData_sinc();
        $("#mensaje_error").html("<span id='tagActualizarDatos'>" + vbtol_props[idioma]["tagActualizarDatos"] + "</span>");
        $("#div_mensajes_error").fadeIn("slow");
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




    var nombreCompleto =   $('#Template_name').val();

    if($("#Template_TipoPersona").get(0).value=="NAT"){
        if($('#Template_lastname1').val().length>0)  nombreCompleto +=  ' '+$('#Template_lastname1').val();
        if($('#Template_lastname2').val().length>0)  nombreCompleto +=  ' '+$('#Template_lastname2').val();
    }

    $("#Template_Rname").html(nombreCompleto);



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


    if(Trim($("#Template_BeneficiaryPostalCode").get(0).value) !=""){
        $("#div_Template_PostalCode").fadeIn("fast");
        $("#template_RBeneficiaryPostalCode").html($("#Template_BeneficiaryPostalCode").get(0).value);
    }else{
        $("#div_Template_PostalCode").fadeOut("fast");
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

    if($("#Template_TipoPersona").get(0).value == "NAT"){

        if(Trim($("#Template_FullNameIndividualIB").get(0).value) !=""){
            $("#div_Template_RFullNameIndividualIB").fadeIn("fast");
            $("#Template_RFullNameIndividualIB").html(Trim($("#Template_FullNameIndividualIB").get(0).value));
        }else{
            $("#div_Template_RFullNameIndividualIB").fadeOut("fast");
        }

        if(Trim($("#Template_DateBirthIB").get(0).value) !=""){
            $("#div_Template_RDateBirthIB").fadeIn("fast");
            $("#Template_RDateBirthIB").html(Trim($("#Template_DateBirthIB").get(0).value));
        }else{
            $("#div_Template_RDateBirthIB").fadeOut("fast");
        }

        if(Trim($("#Template_NationalityIB").get(0).value) !="-2"){
            $("#div_Template_RNationalityIB").fadeIn("fast");
            $("#Template_RNationalityIB").html(Trim($("#Template_NationalityIB :selected").html()));
        }else{
            $("#div_Template_RNationalityIB").fadeOut("fast");
        }

        if(Trim($("#Template_IdPassportIB").get(0).value) !=""){
            $("#div_Template_RIdPassportIB").fadeIn("fast");
            $("#Template_RIdPassportIB").html(Trim($("#Template_IdPassportIB").get(0).value));
        }else{
            $("#div_Template_RIdPassportIB").fadeOut("fast");
        }

    }else{

        if(Trim($("#Template_FullNameIB").get(0).value) !=""){
            $("#div_Template_RFullNameIB").fadeIn("fast");
            $("#Template_RFullNameIB").html(Trim($("#Template_FullNameIB").get(0).value));
        }else{
            $("#div_Template_RFullNameIB").fadeOut("fast");
        }

        if(Trim($("#Template_CountryIncorporationIB").get(0).value) !="-2"){
            $("#div_Template_RCountryIncorporationIB").fadeIn("fast");
            $("#Template_RCountryIncorporationIB").html(Trim($("#Template_CountryIncorporationIB :selected").html()));
        }else{
            $("#div_Template_RCountryIncorporationIB").fadeOut("fast");
        }

    }

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
    $("#Template_RFullNameIndividualIB").html("");
    $("#Template_RDateBirthIB").html("");
    $("#Template_RNationalityIB").html("");
    $("#Template_RIdPassportIB").html("");
    $("#Template_FullNameIB").html("");
    $("#Template_RCountryIncorporationIB").html("");


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


//    ResumenTOB.beneficiaryName=Trim(normalize($("#Template_name").get(0).value));
    ResumenTOB.beneficiaryName=Trim(normalize($("#Template_name").get(0).value));
    ResumenTOB.beneficiaryLastName1=Trim(normalize($("#Template_lastname1").get(0).value));
    ResumenTOB.beneficiaryLastName2=Trim(normalize($("#Template_lastname2").get(0).value));
    ResumenTOB.beneficiaryTypePerson=Trim(normalize($("#Template_TipoPersona").get(0).value));


    ResumenTOB.beneficiaryAccount=$("#Template_AccountNumber").get(0).value;
    ResumenTOB.beneficiaryAccountBank=$("#Template_AccountBank").get(0).value;
    ResumenTOB.beneficiaryEmail=Trim(normalize($("#Template_beneficiaryEmail").get(0).value));
    ResumenTOB.beneficiaryAddress1=Trim(normalize($("#Template_AddressLine1").get(0).value));
    ResumenTOB.beneficiaryAddress2=Trim(normalize($("#Template_AddressLine2").get(0).value));
    //ResumenTOB.beneficiaryAddress3=$("#Template_AddressLine3").get(0).value;
    ResumenTOB.beneficiaryAddress3="";
    ResumenTOB.beneficiaryTelephone=$("#Template_TelephoneNumber").get(0).value;
    ResumenTOB.beneficiaryCity=Trim(normalize($("#Template_AddressLine3").get(0).value));
    ResumenTOB.beneficiaryPostalCode=$("#Template_BeneficiaryPostalCode").get(0).value;
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

    ResumenTOB.beneficiaryBankName=Trim(normalize($("#Template_NameBank").get(0).value));
    ResumenTOB.beneficiaryBankAddress1=Trim(normalize($("#Template_AddressLineBank1").get(0).value));
    ResumenTOB.beneficiaryBankAddress2=Trim(normalize($("#Template_AddressLineBank2").get(0).value));
    //ResumenTOB.beneficiaryBankAddress3=$("#Template_AddressLineBank3").get(0).value;
    ResumenTOB.beneficiaryBankAddress3="";
    ResumenTOB.beneficiaryBankCity=Trim(normalize($("#Template_AddressLineBank3").get(0).value));
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






    ResumenTOB.intermediaryBankName=Trim(normalize($("#Template_NameBankIB").get(0).value));
    ResumenTOB.intermediaryBankAddress1=Trim(normalize($("#Template_AddressLineBankIB1").get(0).value));
    ResumenTOB.intermediaryBankAddress2=Trim(normalize($("#Template_AddressLineBankIB2").get(0).value));
    //ResumenTOB.intermediaryBankAddress3=$("#Template_AddressLineBankIB3").get(0).value;
    ResumenTOB.intermediaryBankAddress3="";
    ResumenTOB.intermediaryBankCity=Trim(normalize($("#Template_AddressLineBankIB3").get(0).value));
    if($("#Template_CountryBankIB").get(0).value== "-2"){
        ResumenTOB.intermediaryBankCountry="";
        ResumenTOB.intermediaryBankCountryCode="";
    }else{
        ResumenTOB.intermediaryBankCountry=$('#Template_CountryBankIB :selected').html();
        ResumenTOB.intermediaryBankCountryCode=$("#Template_CountryBankIB").get(0).value;
    }


    ResumenTOB.furtherCreditAccount=$("#Template_AccountNumberFFC").get(0).value;
    ResumenTOB.furtherCreditName=Trim(normalize($("#Template_NameFFC").get(0).value));




    if($("#Template_TipoPersona").get(0).value== "-2"){
        ResumenTOB.beneficiaryFullName="";
        ResumenTOB.beneficiaryBirthDate="";
        ResumenTOB.beneficiaryNationality="";
        ResumenTOB.beneficiaryIdPassport="";
        ResumenTOB.beneCountryIncorporation="";
    }else if($("#Template_TipoPersona").get(0).value== "NAT"){
        ResumenTOB.beneficiaryFullName = Trim($("#Template_FullNameIndividualIB").get(0).value);
        ResumenTOB.beneficiaryBirthDate = $("#Template_DateBirthIB").get(0).value;
        ResumenTOB.beneficiaryNationality = Trim($("#Template_NationalityIB").get(0).value);
        ResumenTOB.beneficiaryIdPassport = $("#Template_IdPassportIB").get(0).value;
        ResumenTOB.beneCountryIncorporation= "";

    }else if($("#Template_TipoPersona").get(0).value== "JUR"){
        ResumenTOB.beneficiaryFullName = Trim($("#Template_FullNameIB").get(0).value);
        ResumenTOB.beneCountryIncorporation =  Trim($("#Template_CountryIncorporationIB").get(0).value);
        ResumenTOB.beneficiaryBirthDate = "";
        ResumenTOB.beneficiaryNationality = "";
        ResumenTOB.beneficiaryIdPassport = "";
    }

    if($("#accionTemplate").val()=="editar"){
      ResumenTOB.idTemplate=$("#templateID").val();
      ResumenTOB.nombreTemplate=Trim(normalize($("#Template_nombreTemplate").val()));
    }else if($("#accionTemplate").val()=="nuevo")
      ResumenTOB.nombreTemplate=Trim(normalize($("#Template_nombreTemplate").val()));
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
    idTemplate=result.idTemplate;
        respuesta = result.respuesta;

    if(respuesta=="OK"){


        //if ($("#tipo_usuario_app").val()=="6"){
        if (($("#tipo_contrato_app").val()=="FC")||( transferFinalAprobada=="OK")){

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
                //mensaje= "Template successfully saved<br>To perform operations must be approved";
                mensaje= "Template successfully saved\nTo perform operations must be approved";
              /*  $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");  */
                alert(mensaje);
            }else{
                //mensaje=  "Plantilla guardada exitosamente.<br>Por favor apruebe la plantilla para poder utilizarla";
                mensaje=  "Plantilla guardada exitosamente.\nPor favor apruebe la plantilla para poder utilizarla";
              /*  $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");   */
                alert(mensaje);
            }
            buscarIdTemplate();
            tmpTranfers="NO OK";
            //Se llama a la pantalla de metodos de validacion
            mainValidationMethods("aprobarTemplate");

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
            //cargar_selectPersonal("Accounts", valorCuentas,"Select","-2");
            cargar_selectCuenta("Accounts", valorCuentas,"Select","-2"); // Cambio para admitir parámetro extra 11/08/2016
        else
            //cargar_selectPersonal("Accounts", valorCuentas,"Seleccione","-2");
            cargar_selectCuenta("Accounts", valorCuentas,"Seleccione","-2"); // Cambio para admitir parámetro extra 11/08/2016

    if(cuentas!=null)
        cargar_selectPersonal2("AccountBank", cuentas);

    if(paises!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonalpaisesMod("Country", PaisesBeneficiario,"Select","-2","1");
            cargar_selectPersonalpaisesMod("CountryBank", paises,"Select","-2","");
            cargar_selectPersonalpaisesMod("CountryBankIB", paises,"Select","-2","");
        }else{
            cargar_selectPersonalpaisesMod("Country", PaisesBeneficiario,"Seleccione","-2","1");
            cargar_selectPersonalpaisesMod("CountryBank", paises,"Seleccione","-2","");
            cargar_selectPersonalpaisesMod("CountryBankIB", paises,"Seleccione","-2","");
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
            cargar_selectPersonal2("BankCode", codSwift);
            cargar_selectPersonal("BankCodeIB", codSwift,"Select","-2");
        }else{
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal2("BankCode", codSwift);
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
            //cargar_selectCuenta("FC_Accounts", valorCuentas,"Select","-2"); // Cambio para admitir parámetro extra 11/08/2016
        else
            cargar_selectPersonal("FC_Accounts", valorCuentas,"Seleccione","-2");
            //cargar_selectCuenta("FC_Accounts", valorCuentas,"Seleccione","-2"); // Cambio para admitir parámetro extra 11/08/2016

    if(cuentas!=null)
        cargar_selectPersonal2("FC_AccountBank", cuentas);

    if(paises!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonalpaisesMod("FC_Country", paises,"Select","-2","1");
            cargar_selectPersonalpaisesMod("FC_CountryBank", paises,"Select","-2","");
            cargar_selectPersonalpaisesMod("FC_CountryBankIB", paises,"Select","-2","");
        }else{
            cargar_selectPersonalpaisesMod("FC_Country", paises,"Seleccione","-2","1");
            cargar_selectPersonalpaisesMod("FC_CountryBank", paises,"Seleccione","-2","");
            cargar_selectPersonalpaisesMod("FC_CountryBankIB", paises,"Seleccione","-2","");
        }
    }
    if(codBankBen!=null){
        if(idioma=="_us_en"){
            cargar_selectPersonal2("FC_BankCode", codBankBen);
            cargar_selectPersonal("FC_BankCodeIB", codBankBen,"Select","-2");
        }else{
            cargar_selectPersonal2("FC_BankCode", codBankBen);
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
    nacionalidadBI = result.nacionalidadBI;
    paisesBI = result.paisesBI;
    idioma =result.idioma;
    var codSwift = []
    if(cuentas!=null)
        cargar_selectPersonal2("Template_AccountBank", cuentas);
    if(paises!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonalpaisesMod("Template_Country", PaisesBeneficiario,"Select","-2","1");
            cargar_selectPersonalpaisesMod("Template_CountryBank", paises,"Select","-2","");
            cargar_selectPersonalpaisesMod("Template_CountryBankIB", paises,"Select","-2","");
        }else{
            cargar_selectPersonalpaisesMod("Template_Country", PaisesBeneficiario,"Seleccione","-2","1");
            cargar_selectPersonalpaisesMod("Template_CountryBank", paises,"Seleccione","-2","");
            cargar_selectPersonalpaisesMod("Template_CountryBankIB", paises,"Seleccione","-2","");
        }
    }
    if(nacionalidadBI!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonal("Template_NationalityIB", nacionalidadBI,"Select","-2");
        }else{
            cargar_selectPersonal("Template_NationalityIB", nacionalidadBI,"Seleccione","-2");
        }
    }
    if(paisesBI!=null){
        if(idioma=="_us_en") {
            cargar_selectPersonalpaisesMod("Template_CountryIncorporationIB", paisesBI,"Select","-2","1");
        }else{
            cargar_selectPersonalpaisesMod("Template_CountryIncorporationIB", paisesBI,"Seleccione","-2","1");
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
            cargar_selectPersonal2("Template_BankCode", codSwift);
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
            cargar_selectPersonal2("Template_BankCode", codSwift);
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
        $("#Template_NameBankIB").removeClass("obligatorio_Template error_campo");                     consultarTarjetasEmergencia
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

function validarAprobacionTMP(id){
    $.modal.close();
    idTemplate=id;
    //Se llama a la pantalla de metodos de validacion
    mainValidationMethods("aprobarTemplate");
}




function TemplateValidateJSONData(){
    var url = urlTemplateValidateTOB;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};


    if($("#Template_BankCode").val()=="-2"){
        ResumenTOB.beneficiaryBankCodeType="";
    }else{
        ResumenTOB.beneficiaryBankCodeType=$("#Template_BankCode").val();
    }
    ResumenTOB.beneficiaryBankCodeNumber=$("#Template_BankCode2").get(0).value;
    ResumenTOB.beneficiaryBankCountry=$('#Template_CountryBank :selected').html();
    ResumenTOB.beneficiaryBankCountryCode=$("#Template_CountryBank").get(0).value;
    ResumenTOB.beneficiaryBankCity=Trim(normalize($("#Template_AddressLineBank3").get(0).value));



    ResumenTOB.beneficiaryName=Trim(normalize($("#Template_name").get(0).value));
    ResumenTOB.beneficiaryLastName1=Trim(normalize($("#Template_lastname1").get(0).value));
    ResumenTOB.beneficiaryLastName2=Trim(normalize($("#Template_lastname2").get(0).value));
    ResumenTOB.beneficiaryTypePerson=Trim(normalize($("#Template_TipoPersona").get(0).value));


    if($("#Template_SwiftBankCode2").get(0).value.length == 0 ){
        ResumenTOB.beneficiaryBankCodeTypeSwift="";
    }else{
        ResumenTOB.beneficiaryBankCodeTypeSwift="SWIFT";
    }

    ResumenTOB.beneficiaryBankCodeNumberSwift=$("#Template_SwiftBankCode2").get(0).value;


    if($("#Template_SwiftBankCodeIB2").get(0).value.length == 0 ){
        ResumenTOB.intermediaryBankCodeTypeSwift="";
    }else{
        ResumenTOB.intermediaryBankCodeTypeSwift="SWIFT";
    }

    ResumenTOB.intermediaryBankCodeNumberSwift=$("#Template_SwiftBankCodeIB2").get(0).value;

    if($("#Template_BankCodeIB").get(0).value=="-2" || $("#Template_BankCodeIB2").get(0).value.length == 0 ){
        ResumenTOB.intermediaryBankCodeType="";
    }else{
        //ResumenTOB.intermediaryBankCodeType=$('#BankCodeIB :selected').html();
        ResumenTOB.intermediaryBankCodeType=$("#Template_BankCodeIB").val();
    }

    ResumenTOB.intermediaryBankCodeNumber=$("#Template_BankCodeIB2").get(0).value;


    if($("#Template_CountryBankIB").get(0).value=="-2"){
        ResumenTOB.intermediaryBankCountry="";
        ResumenTOB.intermediaryBankCountryCode="";
    }else{
        ResumenTOB.intermediaryBankCountry=$('#Template_CountryBankIB :selected').html();
        ResumenTOB.intermediaryBankCountryCode=$("#Template_CountryBankIB").get(0).value;
        ResumenTOB.intermediaryBankCity=Trim(normalize($("#Template_AddressLineBankIB3").get(0).value));
    }

    if($("#Template_TipoPersona").get(0).value== "-2"){
        ResumenTOB.beneficiaryFullName="";
        ResumenTOB.beneficiaryBirthDate="";
        ResumenTOB.beneficiaryNationality="";
        ResumenTOB.beneficiaryIdPassport="";
        ResumenTOB.beneCountryIncorporation="";
    }else if($("#Template_TipoPersona").get(0).value== "NAT"){
        ResumenTOB.beneficiaryFullName = Trim($("#Template_FullNameIndividualIB").get(0).value);
        ResumenTOB.beneficiaryBirthDate = $("#Template_DateBirthIB").get(0).value;
        ResumenTOB.beneficiaryNationality = $("#Template_NationalityIB").get(0).value;
        ResumenTOB.beneficiaryIdPassport = $("#Template_IdPassportIB").get(0).value;
        ResumenTOB.beneCountryIncorporation= "";

    }else if($("#Template_TipoPersona").get(0).value== "JUR"){
        ResumenTOB.beneficiaryFullName = Trim($("#Template_FullNameIB").get(0).value);
        ResumenTOB.beneCountryIncorporation = $("#Template_CountryIncorporationIB").get(0).value;
        ResumenTOB.beneficiaryBirthDate = "";
        ResumenTOB.beneficiaryNationality = "";
        ResumenTOB.beneficiaryIdPassport = "";
    }


    jsonTransfers[0]= ResumenTOB;

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,TemplateValidateSuccess,null,null);
}

function TemplateValidateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var resumen;
    var existeBen =result.existeBen;
    var existeInt = result.existeInt;
    var existeBenSwift =result.existeBenSwift;
    var existeIntSwift = result.existeIntSwift;
    idioma = result.idioma;

    //$("#marco_trabajo").css("height","900px");
    resumen = result.resumenTOB;
    resumentransferToOtherBank = result.resumenTOB;

    var mensaje ="";
    if(existeBen == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The code of the Beneficiary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo del Banco Beneficiario.<br>";
        $("#Template_BankCode2").addClass("error_campo");
    }else{
        if($("#Template_BankCode2").hasClass("error_campo"))
            $("#Template_BankCode2").removeClass("error_campo");
    }

    if(existeInt == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The code of the Intermediary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo del Banco Intermediario.<br>";
        $("#Template_BankCodeIB2").addClass("error_campo");
    }else{
        if($("#Template_BankCodeIB2").hasClass("error_campo"))
            $("#Template_BankCodeIB2").removeClass("error_campo");
    }

    if(existeBenSwift == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The Swift code of the Beneficiary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo Swift del Banco Beneficiario.<br>";
        $("#Template_SwiftBankCode2").addClass("error_campo");
    }else{
        if($("#Template_SwiftBankCode2").hasClass("error_campo"))
            $("#Template_SwiftBankCode2").removeClass("error_campo");
    }

    if(existeIntSwift == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The Swift code of the Intermediary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo Swift del Banco Intermediario.<br>";
        $("#Template_SwiftBankCodeIB2").addClass("error_campo");
    }else{
        if($("#Template_SwiftBankCodeIB2").hasClass("error_campo"))
            $("#Template_SwiftBankCodeIB2").removeClass("error_campo");
    }

    if (existeInt == 0 || existeBen == 0){
        if(idioma=="_us_en"){
            mensaje = mensaje + "If problems persist, please contact your Financial Advisor or Account Executive. <br>";
        }
        else{
            mensaje = mensaje + "En caso de persistir los inconvenientes, comun&iacute;quese con su Asesor Financiero o Ejecutivo de Cuenta. <br>";
        }
    }


    if(mensaje!=""){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        resumenEditarTemplate();
        $("#div_mensajes_error").fadeOut("fast");
        $("#createToOtherBank").fadeOut("fast");
        $("#summaryToOtherBank").fadeIn("fast");

    }



}


function guardarLogTemplateJSONData(){
    var url = urlTransfersCargarTemplateLog;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0]= idTemplate;
    jsonTransfers[1]= $("#Template_nombreTemplate").val();

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,guardarLogTemplateSuccess,null,null);
}

function guardarLogTemplateSuccess(originalRequest){
}

function validateTemplate(){
    var mensaje="";
    var invalido="0";//0 es valido
    var auxIban=false;

    $("#BankCodeSWIFTtext").val("Template_SwiftBankCode");
    $("#BankCodeIBSWIFTtext").val("Template_SwiftBankCodeIB2");


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

    if (Trim($("#Template_BankCode").get(0).value)== "-2") {
        // no se ha seleccionado el tipo de codigo

        if (Trim($("#Template_BankCode2").get(0).value)== "") {
            //campo codigo es vacio , no hay error
            $("#Template_BankCode").removeClass("error_campo");
            $("#Template_BankCode2").removeClass("error_campo");

        }else{
            //campo codigo tiene valor , debe seleccionar un tipo codigo

            $("#Template_BankCode").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";
        }

    }else{
        // esta seleccionado un tipo de codigo

        if (Trim($("#Template_BankCode2").get(0).value)== "" && Trim($("#Template_SwiftBankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#Template_BankCode2").addClass("error_campo");
            $("#Template_SwiftBankCode2").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            $("#Template_BankCode").removeClass("error_campo");
            $("#Template_BankCode2").removeClass("error_campo");
            $("#Template_SwiftBankCode2").removeClass("error_campo");
        }


    }

    if (Trim($("#Template_BankCodeIB").get(0).value)== "-2") {
        // no se ha seleccionado el tipo de codigo

        if (Trim($("#Template_BankCodeIB2").get(0).value)== "" ) {
            //campo codigo es vacio , no hay error
            $("#Template_BankCodeIB").removeClass("error_campo");
            $("#Template_BankCodeIB2").removeClass("error_campo");
        }else{
            //campo codigo tiene valor , debe seleccionar un tipo codigo

            $("#Template_BankCodeIB").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";
        }

    }else{
        // esta seleccionado un tipo de codigo

        if (Trim($("#Template_BankCodeIB2").get(0).value)== "" && Trim($("#Template_SwiftBankCodeIB2").get(0).value) == "") {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#Template_BankCodeIB2").addClass("error_campo");
            $("#Template_SwiftBankCodeIB2").removeClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            $("#Template_BankCodeIB").removeClass("error_campo");
            $("#Template_BankCodeIB2").removeClass("error_campo");
            $("#Template_SwiftBankCodeIB2").removeClass("error_campo");
        }


    }




    if(mensaje ==""){
        /*validaciones*/
        if(idioma =="_us_en"){
            if (!isAlphanumericWithSpace($("#Template_name").get(0).value) ) {
                mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                $("#Template_name").addClass("error_campo");
                invalido ="1";
            }


            /* Validaciones Campos Nuevos (BeneficiaryInformation)*/

            if($("#Template_TipoPersona").get(0).value == "NAT"){
                if ($("#Template_FullNameIndividualIB").get(0).value.length < 3 && $("#Template_FullNameIndividualIB").get(0).value.length > 50 ) {
                    mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                    $("#Template_FullNameIndividualIB").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphanumericWithSpace($("#Template_FullNameIndividualIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (!isDate($("#Template_DateBirthIB").get(0).value) ) {
                    mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                    $("#Template_DateBirthIB").addClass("error_campo");
                    invalido ="1";
                }
                if ($("#Template_NationalityIB").get(0).value == "-2") {
                    mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                    $("#Template_NationalityIB").addClass("error_campo");
                    invalido ="1";
                }
                if ($("#Template_IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                    mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                    $("#Template_IdPassportIB").addClass("error_campo");
                    invalido ="1";
                }
            }else if($("#Template_TipoPersona").get(0).value == "JUR"){
                if ($("#Template_FullNameIB").get(0).value.length < 3 && $("#Template_FullNameIB").get(0).value.length > 50 ) {
                    mensaje = mensaje + "Beneficiary Information: The entity Full Name consists \n of a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                    $("#Template_FullNameIB").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphanumericWithSpace($("#Template_FullNameIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if ($("#Template_CountryIncorporationIB").get(0).value == "-2") {
                    mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                    $("#Template_CountryIncorporationIB").addClass("error_campo");
                    invalido ="1";
                }
            }
            /* fin Campos nuevos (beneficiaryInformation Ingles )*/

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

            //if ($("#Template_BankCode").get(0).value == "ABA" && Trim($("#Template_CountryBank").get(0).value) == "US") {
            if ($("#Template_BankCode2").get(0).value != "") {
                if ($("#Template_BankCode2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCode2").get(0).value)) {
                    mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>"
                    $("#Template_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
            }

            if ($("#Template_SwiftBankCode2").get(0).value != "") {
                if ($("#Template_SwiftBankCode2").get(0).value.length != 8 && $("#Template_SwiftBankCode2").get(0).value.length != 11 ) {
                    mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                    $("#Template_SwiftBankCode2").addClass("error_campo");
                    invalido ="1";
                }else if (!isAlphabetic($("#Template_SwiftBankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCode2").get(0).value.substring(6, $("#Template_SwiftBankCode2").get(0).value.length))) {
                    mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                    $("#Template_SwiftBankCode2").addClass("error_campo");
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

                //if ($("#Template_BankCodeIB").get(0).value == "ABA" && Trim($("#Template_CountryBankIB").get(0).value) == "US") {
                if ($("#Template_BankCodeIB2").get(0).value != "") {
                    if ($("#Template_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCodeIB2").get(0).value)) {
                        mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if ($("#Template_SwiftBankCodeIB2").get(0).value != "") {
                    if ($("#Template_SwiftBankCodeIB2").get(0).value.length != 8 && $("#Template_SwiftBankCodeIB2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                        $("#Template_SwiftBankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphabetic($("#Template_SwiftBankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCodeIB2").get(0).value.substring(6, $("#Template_SwiftBankCodeIB2").get(0).value.length))) {
                        mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>"
                        $("#Template_SwiftBankCodeIB2").addClass("error_campo");
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
                    mensaje = mensaje + "Intermediary:The Intermediary Bank City contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
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

            if($("#Template_TipoPersona").get(0).value=="NAT"){

                if ($("#Template_lastname1").val().length <2 ) {
                    //mbernott traducir
                    mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                    $("#Template_lastname1").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphabetic($("#Template_lastname1").val())) {
                        mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                        $("#Template_lastname1").addClass("error_campo");
                        invalido ="1";
                    }
                }


                $("#Template_lastname2").removeClass("error_campo");
                if ($("#Template_lastname2").val().length >0 && $("#Template_lastname2").val().length <2 ) {
                    //mbernott traducir
                    mensaje = mensaje + "Beneficiary: Indicate a valid second lastname"+"<br>";
                    $("#Template_lastname2").addClass("error_campo");
                    invalido ="1";
                }else{

                    if (!isAlphabetic($("#Template_lastname2").val())) {
                        mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                        $("#Template_lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{
                        $("#Template_lastname2").removeClass("error_campo");
                    }
                }


            }



        }else{
            if (!isAlphanumericWithSpace($("#Template_name").get(0).value) ) {
                mensaje = mensaje + "Beneficiario: El Nombre del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                $("#Template_name").addClass("error_campo");
                invalido ="1";
            }
            /* Validaciones Campos Nuevos (BeneficiaryInformation)*/

            if($("#Template_TipoPersona").get(0).value == "NAT"){

                if ($("#Template_FullNameIndividualIB").get(0).value.length < 3 && $("#Template_FullNameIndividualIB").get(0).value.length > 50 ) {
                    mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste \n de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                    $("#Template_FullNameIndividualIB").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphanumericWithSpace($("#Template_FullNameIndividualIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                        $("#Template_FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (!isDate($("#Template_DateBirthIB").get(0).value) ) {
                    mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                    $("#Template_DateBirthIB").addClass("error_campo");
                    invalido ="1";
                }
                if ($("#Template_NationalityIB").get(0).value == "-2") {
                    mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                    $("#Template_NationalityIB").addClass("error_campo");
                    invalido ="1";
                }
                if ($("#Template_IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                    mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                    $("#Template_IdPassportIB").addClass("error_campo");
                    invalido ="1";
                }
            }else if($("#Template_TipoPersona").get(0).value == "JUR"){
                if ($("#Template_FullNameIB").get(0).value.length < 3 && $("#Template_FullNameIB").get(0).value.length > 50 ) {
                    mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste \n de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                    $("#Template_FullNameIB").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphanumericWithSpace($("#Template_FullNameIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                        $("#Template_FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if ($("#Template_CountryIncorporationIB").get(0).value == "-2") {
                    mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                    $("#Template_CountryIncorporationIB").addClass("error_campo");
                    invalido ="1";
                }
            }
            /* fin Campos nuevos (beneficiaryInformation español)*/

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

            //if ($("#Template_BankCode").get(0).value == "ABA" && Trim($("#Template_CountryBank").get(0).value) == "US") {
            if ($("#Template_BankCode2").get(0).value != "") {
                if ($("#Template_BankCode2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCode2").get(0).value)) {
                    mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>"
                    $("#Template_BankCode2").addClass("error_campo");
                    invalido ="1";
                }
            }

            if ($("#Template_SwiftBankCode2").get(0).value != "") {
                if ($("#Template_SwiftBankCode2").get(0).value.length != 8 && $("#Template_SwiftBankCode2").get(0).value.length != 11 ) {
                    mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                    $("#Template_SwiftBankCode2").addClass("error_campo");
                    invalido ="1";
                }else if (!isAlphabetic($("#Template_SwiftBankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCode2").get(0).value.substring(6, $("#Template_SwiftBankCode2").get(0).value.length))) {
                    mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                    $("#Template_SwiftBankCode2").addClass("error_campo");
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

                //if ($("#Template_BankCodeIB2").get(0).value == "ABA" && Trim($("#Template_CountryBankIB").get(0).value) == "US") {
                if ($("#Template_BankCodeIB2").get(0).value != "") {
                    if ($("#Template_BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#Template_BankCodeIB2").get(0).value)) {
                        mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>"
                        $("#Template_BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if ($("#Template_SwiftBankCodeIB2").get(0).value != "") {
                    if ($("#Template_SwiftBankCodeIB2").get(0).value.length != 8 && $("#Template_SwiftBankCodeIB2").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_SwiftBankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    }
                    if (!isAlphabetic($("#Template_SwiftBankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#Template_SwiftBankCodeIB2").get(0).value.substring(6, $("#Template_BankCodeIB2").get(0).value.length))) {
                        mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>"
                        $("#Template_SwiftBankCodeIB2").addClass("error_campo");
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
            if($("#Template_TipoPersona").get(0).value=="NAT"){

                if ($("#Template_lastname1").val().length <2 ) {
                    //mbernott traducir
                    mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                    $("#Template_lastname1").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphabetic($("#Template_lastname1").val())) {
                        mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                        $("#Template_lastname1").addClass("error_campo");
                        invalido ="1";
                    }

                }

                $("#Template_lastname2").removeClass("error_campo");
                if ($("#Template_lastname2").val().length >0 && $("#Template_lastname2").val().length <2 ) {
                    //mbernott traducir
                    mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                    $("#Template_lastname2").addClass("error_campo");
                    invalido ="1";
                }else{
                    if (!isAlphabeticPunto($("#Template_lastname2").val())) {
                        mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                        $("#Template_lastname2").addClass("error_campo");
                        invalido ="1";
                    } else {
                        $("#Template_lastname2").removeClass("error_campo");
                    }
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
    }
    if(mensaje!=""){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
    return mensaje;
}
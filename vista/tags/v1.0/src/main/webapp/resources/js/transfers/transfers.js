var urlTransfersCargar="Transfers_cargar.action";
var urlTransfersValidateTOB="Transfers_validateToOtherBanks.action";
var urlTransfersSaveTOB="Transfers_saveToOtherBanks.action";
//var urlLoginOpeEsp="cargarLoginOpeEsp.action";
var urlDirectorioCargarTemplate="Transfers_cargarDetalleDirectorio.action";
var urlTransfersGenerarClaveTOB="Security_crearClaveRamdom.action";
var urlValidarClaveTOB="Transfers_validarClaveTransferenciasTOB.action";
var urlParametrosPersonalesTOB="DesingBank_cargarParametrosPersonales.action";
var urlAceptarCondicionesTrasfe="Transfers_aceptarCondicionesTransfe.action";
var urlDirectorioCargarTransfer="Transfers_consultarDirectorioTransfer.action";
var urlSecurityGenerarClaveSMS="Security_crearClaveRamdomSMS.action";
var urlCambiarEstatusTemplate = "Transfers_cambiarEstatusTemplate.action"

//var cuentas = [{label:"1234567890987654432",value:"1"},{label:"xxxxxxxxxxxxxxxxxxxxxxxx",value:"2"},{label:"2222222222222222222222222",value:"3"}]
//{"cuentas":{"contenido":[{"label":"prueba","value":"prueba"},{"label":"prueba2","value":"prueba2"}]}
var resumentransferToOtherBank;
var templates = "";

var idioma = "";
var parametros="";
var parametrosBase="";
var origenTemplate="";
var validarTransfer="";
var acepto="";
var usuario= "";
var transferencia_MetodoValidar_Clave;
var popupTmp="NO OK";

$(document).ready(function(){

    $('#pwdClaveConfirmTransfer_TOB').keyboard({
        stayOpen : false,
        layout   : 'qwerty',
        lockInput: false,
        restrictInput : false, // Prevent keys not in the displayed keyboard from being typed in
        preventPaste : false,  // prevent ctrl-v and right click
        autoAccept : true
    });

    /*$('#pwdClaveConfirmSMS').keyboard({
        stayOpen : false,
        layout   : 'qwerty',
        lockInput: true,
        restrictInput : false, // Prevent keys not in the displayed keyboard from being typed in
        preventPaste : false,  // prevent ctrl-v and right click
        autoAccept : true
    }); */

    $("#transferencias_TAGCodigoBanco").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#transferencias_TAGCodigoBanco2").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#transferencias_TAGCodigoBancoSWIFT").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#transferencias_TAGNumeroCuenta").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#transferencias_TAGCodigoBancoIBSwift").click(function(){
        $("#poppup_ayuda_transferencia").fadeIn("slow");
    });

    $("#tagiTemplate").focus(function(){
        $("#tagiTemplate").autocomplete({
            source: templates,
            select: function(event, ui) {
                $("#tagiTemplate").val(ui.item.label);
                $("#Accounts").addClass("focus_selecionado");
                cargarInfoTemplateJSONData(ui.item.key);
            }
        });
    });

    $("#tagiTemplate").click(function(){
        $("#div_tabla_template_consulta_transfer").empty();
        $("#paginacion_buscarBanco_consulta_bancos_template_transfer").empty();
        infoPaginaTemplateConsultaJSONData();

        $("#sign_up_template_transfer").lightbox_me({centered: true, onLoad: function() {
            $("#sign_up_template_transfer").find("input:first").focus();
        }});


        //$("#div_carga_espera_template_transfer").removeClass("oculto");

//            e.preventDefault();

    });

    function infoPaginaTemplateConsultaJSONData(){
        var url = urlDirectorioCargarTransfer;
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


    $("#aceptar_terminoCondiciones").click(function(){

        var mensaje="";
            if(idioma=="_us_en")
                mensaje="Are you sure you want to agree the application?";
            else
                mensaje="Está seguro que desea Aceptar el contrato?";
        if (confirm(mensaje)){
            acepto="S";
            aceptarCondicionesJSONDATA("S");
        }


    });

    $("#rechazar_terminoCondiciones").click(function(){

        var mensaje="";

        if(idioma=="_us_en")
            mensaje="Are you sure you do not agree the application?";
        else
            mensaje="Está seguro que No Acepta el contrato?";

        if (confirm(mensaje)){
            acepto="N";
            aceptarCondicionesJSONDATA("N");
        }

    });



    $("#btn_TOB_aceptar").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        var montoAux="";

        //var montoaux =($("#AmmountAI").get(0).value).replace(/,/g,'t');


        montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');



        if (Trim($("#BankCode2").get(0).value)== "") {
            $("#BankCode").removeClass("obligatorioTOB");
        }else{
            $("#BankCode").addClass("obligatorioTOB");
        }


        if (Trim($("#BankCodeIB2").get(0).value)== "") {
            $("#BankCodeIB").removeClass("obligatorioTOB");
        }else{
            $("#BankCodeIB").addClass("obligatorioTOB");
        }

        $(".obligatorioTOB").each(function(){

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
               if (!isAlphanumericWithSpace(Trim($("#name").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary: The Beneficiary Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#name").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AccountBank").get(0).value)  == "DD") {

                   if (Trim($("#AccountNumber").get(0).value)== "" || !esSoloLetrasNumeros(Trim($("#AccountNumber").get(0).value))) {
                       mensaje = mensaje + "Beneficiary: The Beneficiary Account Number is invalid."+"<br>";
                       $("#AccountNumber").addClass("error_campo");
                       invalido ="1";
                   }
               } else {
                   if ($("#AccountNumber").get(0).value.length < 15 || $("#AccountNumber").get(0).value.length > 34 ) {

                       mensaje = mensaje + "Beneficiary: The IBAN format is invalid. Remember that the IBAN consists\nof a minimum of 15 and a maximum of 34 alphanumeric characters."+"<br>";
                       $("#AccountNumber").addClass("error_campo");
                       invalido ="1";
                       auxIban=true;
                   }
                   if (auxIban!=true){
                       if (!isAlphabetic($("#AccountNumber").get(0).value.substring(0, 2)) || !esSoloLetrasNumeros($("#AccountNumber").get(0).value.substring(2, $("#AccountNumber").get(0).value.length))) {
                           mensaje = mensaje + "Beneficiary: The IBAN format is invalid. \nPlease check."+"<br>";
                           $("#AccountNumber").addClass("error_campo");
                           invalido ="1";
                       }
                   }
               }
               if (Trim($("#AddressLine1").get(0).value)== "") {
                   mensaje = mensaje + "Beneficiary: Type the address of the Beneficiary of the funds"+"<br>";
                   $("#AddressLine1").addClass("error_campo");
                   invalido ="1";
               }
               if (!isAlphanumericWithSpace(Trim($("#AddressLine1").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#AddressLine1").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLine2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLine2").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#AddressLine2").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLine3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLine3").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary: The Beneficiary Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#AddressLine3").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim( $("#TelephoneNumber").get(0).value) != "" && TlfNoValido(Trim($("#TelephoneNumber").get(0).value))) {
                   mensaje = mensaje + "Beneficiary: The format for Phone Number is invalid."+"<br>";
                   $("#TelephoneNumber").addClass("error_campo");
                   invalido ="1";
               }

               if (Trim($("#BankCode").get(0).value) == "ABA") {
                   if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                       mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }
               } else if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                   if ($("#BankCode2").get(0).value.length != 8 && $("#BankCode2").get(0).value.length != 11 ) {
                       mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }else if (!isAlphabetic($("#BankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCode2").get(0).value.substring(6, $("#BankCode2").get(0).value.length))) {
                       mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }
               }
               if (Trim($("#BankCode2").get(0).value)== "" && Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                   mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   $("#BankCodeSWIFTtext").addClass("error_campo");
                   invalido ="1";
               }
               if (!esSoloLetrasNumeros(Trim($("#BankCode2").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank´s Bank Code contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#NameBank").get(0).value)== "") {
                   mensaje = mensaje + "Beneficiary Bank: Indicate the name of the Beneficiary Bank"+"<br>";
                   $("#NameBank").addClass("error_campo");
                   invalido ="1";
               }

               if (!isAlphanumericWithSpace(Trim($("#AddressLineBank1").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#AddressLineBank1").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLineBank2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBank1").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#AddressLineBank2").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLineBank3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBank3").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiary Bank: The Beneficiary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#AddressLineBank3").addClass("error_campo");
                   invalido ="1";
               }


               if( Trim($("#BankCodeIB2").get(0).value)!= "" ||  Trim($("#NameBankIB").get(0).value)!= "" ||
                   Trim($("#AddressLineBankIB1").get(0).value)!= "" || Trim($("#AddressLineBankIB2").get(0).value)!= "" ||
                       Trim($("#AddressLineBankIB3").get(0).value)!= "" || $("#CountryBankIB").get(0).value != "-2" || Trim($("#BankCodeIB").get(0).value) == "ACCOUNT"  ) {

                   if (Trim($("#BankCodeIB").get(0).value) == "ABA") {
                       if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                           mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                   } else if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                       if ($("#BankCodeIB").get(0).value.length != 8 && $("#BankCodeIB").get(0).value.length != 11 ) {
                           mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                       if (!isAlphabetic($("#BankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIB2").get(0).value.substring(6, $("#BankCodeIB2").get(0).value.length))) {
                           mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                   }

                   if (Trim($("#BankCodeIB2").get(0).value)== "" && Trim($("#BankCodeIBSWIFTtext").get(0).value)== "") {
                       mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                       $("#BankCodeIB2").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!esSoloLetrasNumeros(Trim($("#BankCodeIB2").get(0).value)) ) {
                       mensaje = mensaje + "Intermediary: The Intermediary Bank´s Bank Code contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                       $("#BankCodeIB2").addClass("error_campo");
                       invalido ="1";

                   }
                   if (Trim($("#NameBankIB").get(0).value)== "") {
                       mensaje = mensaje + "Intermediary: Indicate the name of the Intermediary Bank."+"<br>";
                       $("#NameBankIB").addClass("error_campo");
                       invalido ="1";
                   }

                   if (Trim($("#AddressLineBankIB1").get(0).value)== "") {
                       mensaje = mensaje + "Intermediary: Type the address of the Intermediary Bank."+"<br>";
                       $("#AddressLineBankIB1").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!isAlphanumericWithSpace(Trim($("#AddressLineBankIB1").get(0).value)) ) {
                       mensaje = mensaje + "Intermediary: The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                       $("#AddressLineBankIB1").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#AddressLineBankIB2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBankIB2").get(0).value)) ) {
                       mensaje = mensaje + "Intermediary: The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                       $("#AddressLineBankIB2").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#AddressLineBankIB3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBankIB3").get(0).value)) ) {
                       mensaje = mensaje + "Intermediary:The Intermediary Bank Address contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                       $("#AddressLineBankIB3").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#CountryBankIB").get(0).value) == "-2") {
                       mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>";
                       $("#CountryBankIB").addClass("error_campo");
                       invalido ="1";
                   }


               }




//            <% //*********** Inicio de Validaciones Provisionales mientras en Sif no se implemente la emisi\u00f3n de cheques para estos casos ***** %>
               /*if( $('#BankCode :selected').html() == "SWIFT" &&
                   ($("#BankCode2").get(0).value.toUpperCase() == "VZCRKYKY" || $("#BankCode2").get(0).value.toUpperCase() == "VZCRVECA") ) {
                   mensaje = "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }*/
               if( $('#BankCode :selected').html() == "ACCOUNT" &&
                   $("#BankCode2").get(0).value=="6550052216" && ($("#BankCodeIB2").get(0).value=="026009593" || $("#BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if( $('#BankCode :selected').html() == "ACCOUNT" && $("#BankCode2").get(0).value=="2000192005241"
                   && ($("#BankCodeIB2").get(0).value=="026005092" || $("#BankCodeIB2").get(0).value.toUpperCase() == "PNBPUS3NNYC") ) {
                   mensaje ="It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information.";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if(  $('#BankCode :selected').html() == "ACCOUNT" &&
                   $("#BankCode2").get(0).value=="400938448" && ($("#BankCodeIB2").get(0).value=="021000021" || $("#BankCodeIB2").get(0).value.toUpperCase() == "CHASUS33") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if( $('#BankCode :selected').html() == "ACCOUNT" &&
                   $("#BankCode2").get(0).value=="6550847584" && ($("#BankCodeIB2").get(0).value=="026009593" || $("#BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountBank").get(0).value == "DD" &&
                   $("#AccountNumber").get(0).value=="6550747853" && ($("#BankCode2").get(0).value=="026009593" || $("#BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountNumber").get(0).value=="6550052216" && ($("#BankCode2").get(0).value=="026009593" || $("#BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if($("#AccountNumber").get(0).value=="2000192005241" && ($("#BankCode2").get(0).value=="026005092" || $("#BankCode2").get(0).value.toUpperCase()=="PNBPUS3NNYC") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountNumber").get(0).value=="400938448" && ($("#BankCode2").get(0).value=="021000021" || $("#BankCode2").get(0).value.toUpperCase()=="CHASUS33") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountNumber").get(0).value=="6550847584" && ($("#BankCode2").get(0).value=="026009593" || $("#BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                   mensaje =  "It is currently not possible to execute this money transfer via VBT Online. Please send your transfer order via fax to VBT Bank & Trust or contact you Financial Advisor or Account Executive for more information."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }

               if( Trim($("#AccountNumberFFC").get(0).value)!= "" || Trim($("#NameFFC").get(0).value)!= "" ) {

                   if (Trim($("#AccountNumberFFC").get(0).value)== "") {
                       mensaje = mensaje + "For further credit: Type the Account Number for Further Credit to."+"<br>";
                       $("#AccountNumberFFC").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!esSoloLetrasNumeros(Trim($("#AccountNumberFFC").get(0).value)) || $("#AccountNumberFFC").get(0).value.length < 4) {
                       mensaje = mensaje + " For further credit to Account Number must consist of a minimum of 4 alpha-numeric characters and is not allowed to contain any invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                       $("#AccountNumberFFC").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#NameFFC").get(0).value)== "") {
                       mensaje = mensaje + " For further credit to: Type the name of the Account Holder of the account for Further Credit to."+"<br>";
                       $("#NameFFC").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!isAlphanumericWithSpace( Trim($("#NameFFC").get(0).value)) ) {
                       mensaje = mensaje + " For further credito to Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                       $("#NameFFC").addClass("error_campo");
                       invalido ="1";
                   }
               }
               if(usuario.tipo!="6"){
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                       mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.'))  > Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.')) || Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.'))  > Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.')) || Number(unFormatCurrency($("#AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
    //               mensaje = mensaje + " Amount & Instructions: The amount exceeds the maximum of USD 50.000,00."+"<br>";
                       if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.')))
                       //if(Number(unFormatCurrency($("#AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.')))
                           mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                       else
                           mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmtd+"."+"<br>";
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
               }else{
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                       mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                       //               mensaje = mensaje + " Amount & Instructions: The amount exceeds the maximum of USD 50.000,00."+"<br>";
                       mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
               }
               if (Trim($("#ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#ReceiverInformation").get(0).value)) ) {
                   mensaje = mensaje + " Amount & Instructions: Receiver Information contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#ReceiverInformation").addClass("error_campo");
                   invalido ="1";
               }
           }else{
               if (!isAlphanumericWithSpace(Trim($("#name").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiario: El Nombre del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#name").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AccountBank").get(0).value)  == "DD") {

                   if (Trim($("#AccountNumber").get(0).value)== "" || !esSoloLetrasNumeros(Trim($("#AccountNumber").get(0).value))) {
                       mensaje = mensaje + "Beneficiario: N&uacute;mero de cuenta del Beneficiario inv&aacute;lido."+"<br>";
                       $("#AccountNumber").addClass("error_campo");
                       invalido ="1";
                   }
               } else {
                   if ($("#AccountNumber").get(0).value.length < 15 || $("#AccountNumber").get(0).value.length > 34 ) {

                       mensaje = mensaje + "Beneficiario: Formato de IBAN inv&aacute;lido. Recuerde que el IBAN tiene\nal menos 15 y m&aacute;ximo 34 caracteres alfanum&eacute;ricos."+"<br>";
                       $("#AccountNumber").addClass("error_campo");
                       invalido ="1";
                       auxIban=true;
                   }
                   if (auxIban!=true){
                       if (!isAlphabetic($("#AccountNumber").get(0).value.substring(0, 2)) || !esSoloLetrasNumeros($("#AccountNumber").get(0).value.substring(2, $("#AccountNumber").get(0).value.length))) {
                           mensaje = mensaje + "Beneficiario:  Formato de IBAN inv&aacute;lido. \nPor favor revise."+"<br>";
                           $("#AccountNumber").addClass("error_campo");
                           invalido ="1";
                       }
                   }
               }
               if (Trim($("#AddressLine1").get(0).value)== "") {
                   mensaje = mensaje + "Beneficiario: Indique la direcci&oacute;n del beneficiario de la transferencia."+"<br>";
                   $("#AddressLine1").addClass("error_campo");
                   invalido ="1";
               }
               if (!isAlphanumericWithSpace(Trim($("#AddressLine1").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiario:  La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#AddressLine1").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLine2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLine2").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiario: La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)"+"<br>";
                   $("#AddressLine2").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLine3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLine3").get(0).value)) ) {
                   mensaje = mensaje + "Beneficiario: La Direcci&oacute;n del Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)"+"<br>";
                   $("#AddressLine3").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim( $("#TelephoneNumber").get(0).value) != "" && TlfNoValido(Trim($("#TelephoneNumber").get(0).value))) {
                   mensaje = mensaje + "Beneficiario: Formato del N&uacute;mero de Tel&eacute;fono incorrecto."+"<br>";
                   $("#TelephoneNumber").addClass("error_campo");
                   invalido ="1";
               }

               if (Trim($("#BankCode").get(0).value) == "ABA") {
                   if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                       mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }
               } else if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                   if ($("#BankCode2").get(0).value.length != 8 && $("#BankCode2").get(0).value.length != 11 ) {
                       mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }else if (!isAlphabetic($("#BankCode2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCode2").get(0).value.substring(6, $("#BankCode2").get(0).value.length))) {
                       mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }
               }
               if (Trim($("#BankCode2").get(0).value)== "" && Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                   mensaje = mensaje + "Banco Beneficiario: Por favor indique al menos un c&oacute;digo de banco beneficiario."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if (!esSoloLetrasNumeros(Trim($("#BankCode2").get(0).value)) ) {
                   mensaje = mensaje + "Banco Beneficiario: El C&oacute;digo del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#NameBank").get(0).value)== "") {
                   mensaje = mensaje + "Banco Beneficiario: Indique el nombre del banco beneficiario."+"<br>";
                   $("#NameBank").addClass("error_campo");
                   invalido ="1";
               }

               if (!isAlphanumericWithSpace(Trim($("#AddressLineBank1").get(0).value)) ) {
                   mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#AddressLineBank1").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLineBank2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBank1").get(0).value)) ) {
                   mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#AddressLineBank2").addClass("error_campo");
                   invalido ="1";
               }
               if (Trim($("#AddressLineBank3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBank3").get(0).value)) ) {
                   mensaje = mensaje + "Banco Beneficiario: La Direcci&oacute;n del Banco Beneficiario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#AddressLineBank3").addClass("error_campo");
                   invalido ="1";
               }


               if( Trim($("#BankCodeIB2").get(0).value)!= "" ||  Trim($("#NameBankIB").get(0).value)!= "" ||
                   Trim( $("#AddressLineBankIB1").get(0).value)!= "" || Trim($("#AddressLineBankIB2").get(0).value)!= "" ||
                       Trim($("#AddressLineBankIB3").get(0).value)!= "" || $("#CountryBankIB").get(0).value != "-2" || $("#BankCodeIB").get(0).value == "ACCOUNT"  ) {

                   if ($("#BankCodeIB").get(0).value == "ABA") {
                       if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros($("#BankCodeIB2").get(0).value)) {
                           mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                   } else if ($("#BankCodeIB").get(0).value == "SWIFT") {
                       if ($("#BankCodeIB2").get(0).value.length != 8 && $("#BankCodeIB2").get(0).value.length != 11 ) {
                           mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                       if (!isAlphabetic($("#BankCodeIB2").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIB2").get(0).value.substring(6, $("#BankCodeIB2").get(0).value.length))) {
                           mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                   }

                   if (Trim($("#BankCodeIB2").get(0).value)== "" && Trim($("#BankCodeIBSWIFTtext").get(0).value)== "") {
                       mensaje = mensaje + "Intermediario: Indique el c&oacute;digo del banco intermediario."+"<br>";
                       $("#BankCodeIB2").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!esSoloLetrasNumeros(Trim($("#BankCodeIB2").get(0).value)) ) {
                       mensaje = mensaje + "Intermediario: El C&oacute;digo del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                       $("#BankCodeIB2").addClass("error_campo");
                       invalido ="1";

                   }
                   if (Trim($("#NameBankIB").get(0).value)== "") {
                       mensaje = mensaje + "Intermediario: Indique el nombre del banco intermediario."+"<br>";
                       $("#NameBankIB").addClass("error_campo");
                       invalido ="1";
                   }

                   if (Trim($("#AddressLineBankIB1").get(0).value)== "") {
                       mensaje = mensaje + "Intermediario: Indique la direcci&oacute;n del banco intermediario."+"<br>";
                       $("#AddressLineBankIB1").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!isAlphanumericWithSpace(Trim($("#AddressLineBankIB1").get(0).value)) ) {
                       mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                       $("#AddressLineBankIB1").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#AddressLineBankIB2").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBankIB2").get(0).value)) ) {
                       mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                       $("#AddressLineBankIB2").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#AddressLineBankIB3").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#AddressLineBankIB3").get(0).value)) ) {
                       mensaje = mensaje + "Intermediario: La Direcci&oacute;n del Banco Intermediario tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                       $("#AddressLineBankIB3").addClass("error_campo");
                       invalido ="1";
                   }
                   if ($("#CountryBankIB").get(0).value == "-2") {
                       mensaje = mensaje + "Intermediario: Indique el Pais del banco intermediario."+"<br>";
                       $("#CountryBankIB").addClass("error_campo");
                       invalido ="1";
                   }


               }




//            <% //*********** Inicio de Validaciones Provisionales mientras en Sif no se implemente la emisi&oacute;n de cheques para estos casos ***** %>
               /*if( $('#BankCode :selected').html() == "SWIFT" &&
                   ($("#BankCode2").get(0).value.toUpperCase() == "VZCRKYKY" || $("#BankCode2").get(0).value.toUpperCase() == "VZCRVECA") ) {
                   mensaje = "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }*/
               if( $('#BankCode :selected').html() == "ACCOUNT" &&
                   $("#BankCode2").get(0).value=="6550052216" && ($("#BankCodeIB2").get(0).value=="026009593" || $("#BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if( $('#BankCode :selected').html() == "ACCOUNT" && $("#BankCode2").get(0).value=="2000192005241"
                   && ($("#BankCodeIB2").get(0).value=="026005092" || $("#BankCodeIB2").get(0).value.toUpperCase() == "PNBPUS3NNYC") ) {
                   mensaje ="Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n.";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if(  $('#BankCode :selected').html() == "ACCOUNT" &&
                   $("#BankCode2").get(0).value=="400938448" && ($("#BankCodeIB2").get(0).value=="021000021" || $("#BankCodeIB2").get(0).value.toUpperCase() == "CHASUS33") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if( $('#BankCode :selected').html() == "ACCOUNT" &&
                   $("#BankCode2").get(0).value=="6550847584" && ($("#BankCodeIB2").get(0).value=="026009593" || $("#BankCodeIB2").get(0).value.toUpperCase() == "BOFAUS3N") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#BankCode2").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountBank").get(0).value == "DD" &&
                   $("#AccountNumber").get(0).value=="6550747853" && ($("#BankCode2").get(0).value=="026009593" || $("#BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountNumber").get(0).value=="6550052216" && ($("#BankCode2").get(0).value=="026009593" || $("#BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if($("#AccountNumber").get(0).value=="2000192005241" && ($("#BankCode2").get(0).value=="026005092" || $("#BankCode2").get(0).value.toUpperCase()=="PNBPUS3NNYC") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountNumber").get(0).value=="400938448" && ($("#BankCode2").get(0).value=="021000021" || $("#BankCode2").get(0).value.toUpperCase()=="CHASUS33") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }
               if( $("#AccountNumber").get(0).value=="6550847584" && ($("#BankCode2").get(0).value=="026009593" || $("#BankCode2").get(0).value.toUpperCase()=="BOFAUS3N") ) {
                   mensaje =  "Estimado Cliente, usted no puede realizar esta transferencia a trav&eacute;s del VBT Online. Por Favor envi&eacute; su orden de transferencia v&iacute;a fax a VBT Bank & Trust &oacute; comun&iacute;quese con su Asesor Financiero &oacute; Ejecutivo de Cuenta para m&aacute;s informaci&oacute;n."+"<br>";
                   $("#AccountNumber").addClass("error_campo");
                   invalido ="1";
               }

               if(Trim( $("#AccountNumberFFC").get(0).value)!= "" || Trim($("#NameFFC").get(0).value)!= "" ) {

                   if (Trim($("#AccountNumberFFC").get(0).value)== "") {
                       mensaje = mensaje + "Para Cr&eacute;dito Final a: Indique el N&uacute;mero de Cuenta para Cr&eacute;dito Final a."+"<br>";
                       $("#AccountNumberFFC").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!esSoloLetrasNumeros(Trim($("#AccountNumberFFC").get(0).value)) || $("#AccountNumberFFC").get(0).value.length < 4) {
                       mensaje = mensaje + " El N&uacute;mero de Cuenta para Cr&eacute;dito Final debe ser mayor de 4 caracteres alfanum&eacute;ricos y no debe contener caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                       $("#AccountNumberFFC").addClass("error_campo");
                       invalido ="1";
                   }
                   if (Trim($("#NameFFC").get(0).value)== "") {
                       mensaje = mensaje + " Para Cr&eacute;dito Final a: Indique el Nombre del Titular de la Cuenta para Cr&eacute;dito Final a."+"<br>";
                       $("#NameFFC").addClass("error_campo");
                       invalido ="1";
                   }
                   if (!isAlphanumericWithSpace( Trim($("#NameFFC").get(0).value)) ) {
                       mensaje = mensaje + " El Nombre para Cr&eacute;dito Final tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                       $("#NameFFC").addClass("error_campo");
                       invalido ="1";
                   }
               }

               if (Number(unFormatCurrency(montoAux,',').replace(',','.')) < Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
               //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) < Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                   mensaje = mensaje + "Monto y Descripci&oacute;n:  Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a "+parametros.ex_mminto+"."+"<br>";
                   $("#AmmountAI").addClass("error_campo");
                   invalido ="1";
               }
               if(usuario.tipo!="6"){
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.'))  > Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.')) || Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.'))  > Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.')) || Number(unFormatCurrency($("#AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
    //               mensaje = mensaje + " Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD 50.000,00)."+"<br>";
                       if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.')))
                       //if(Number(unFormatCurrency($("#AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.')))
                           mensaje = mensaje + "Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.ex_mmto+")."+"<br>";
                       else
                           mensaje = mensaje + "Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.ex_mmtd+")."+"<br>" ;
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }

               }else{
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").val(),',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                       //               mensaje = mensaje + " Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD 50.000,00)."+"<br>";
                       mensaje = mensaje + "Monto y Descripci&oacute;n: Monto m&aacute;ximo permitido por transferencia excedido (M&aacute;x. USD "+parametros.ex_mmto+")."+"<br>" ;
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
               }
               if (Trim($("#ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#ReceiverInformation").get(0).value)) ) {
                   mensaje = mensaje + " Monto y Descripci&oacute;n: La Descripci&oacute;n tiene caracteres inv&aacute;lidos (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>";
                   $("#ReceiverInformation").addClass("error_campo");
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
            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#summaryToOtherBank #clave_TOB").attr("style","display: none");
                $("#summaryToOtherBank #tituloResumen_TOB").attr("style","display: block");
                $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
                $("#summaryToOtherBank #resumenBotones_TOB_Finales").attr("style","display: none");
                $("#summaryToOtherBank #tituloExitoso_TOB").attr("style","display: none");
                TransfersValidateJSONData();
            }else{
                if(idioma=="_us_en")
                  mensaje = "Beneficiary: Email"+"<br>";
                else
                  mensaje = "Beneficiario: Email"+"<br>";
                $("#beneficiaryEmail").addClass("error_campo");
                $("#mensaje_error").empty();
                if(idioma=="_us_en")
                  $("#mensaje_error").html("Invalid format - "+mensaje);
                else
                  $("#mensaje_error").html("Formato invalido - "+mensaje);
                $("#div_mensajes_error").fadeIn("slow");
                $("#beneficiaryEmail").focus();
            }



        }


        });




    $("#btn_TOB_volver").click(function(){

        if (origenTemplate=="TMP"){
            $("#TemplateBankCode").val($("#BankCode").val());
            $("#TemplateBankCode2").val($("#BankCode2").val());
            $("#TemplateNameBank").val($("#NameBank").val());
            $("#TemplateAddressLineBank1").val($("#AddressLineBank1").val());
            $("#TemplateAddressLineBank2").val($("#AddressLineBank2").val());
            $("#TemplateAddressLineBank3").val($("#Template_AddressLineBank3").val());
            $("#TemplateCountryBank").val($("#CountryBank").val());

            $("#Templatename").val($("#name").val());
            $("#TemplateAccountBank").val($("#AccountBank").val());
            $("#TemplateAccountNumber").val($("#AccountNumber").val());
            $("#TemplatebeneficiaryEmail").val($("#_beneficiaryEmail").val());
            $("#TemplateAddressLine1").val($("#AddressLine1").val());
            $("#TemplateAddressLine2").val($("#AddressLine2").val());
            $("#TemplateAddressLine3").val($("#AddressLine3").val());
            $("#TemplateTelephoneNumber").val($("#TelephoneNumber").val());
            $("#TemplateCountry").val($("#Country").val());

            $("#TemplateBankCodeIB").val($("#BankCodeIB").val());
            $("#TemplateBankCodeIB2").val($("#BankCodeIB2").val());
            $("#TemplateNameBankIB").val($("#NameBankIB").val());
            $("#TemplateAddressLineBankIB1").val($("#AddressLineBankIB1").val());
            $("#TemplateAddressLineBankIB2").val($("#AddressLineBankIB2").val());
            $("#TemplateAddressLineBankIB3").val($("#_AddressLineBankIB3").val());
            $("#TemplateCountryBankIB").val($("#CountryBankIB").val());

            $("#TemplateAccountNumberFFC").val($("#AccountNumberFFC").val());
            $("#TemplateNameFFC").val($("#NameFFC").val());

            $("#div_TRANSFERENCIA_EXTERNA").removeClass("opcion_seleccionada");
            $("#div_DIRECTORIO").addClass("opcion_seleccionada");
            $("#div_TRANSFERENCIA_EXTERNA").fadeOut("fast");
            $("#div_DIRECTORIO").fadeIn("slow");



            $("#div_mensajes_error").fadeOut("fast");

            seleccionarTemplateEditar(idTemplate);
        }else{
            $("#div_TRANSFERENCIA_EXTERNA").fadeOut("fast");
            $("#div_home").fadeIn("slow");
        }


    });

    $("#btn__resumen_aceptar").click(function(){

        $("#summaryToOtherBank").attr("style","display: block");
        $("#pwdClaveConfirmTransfer_TOB").val("");
        $("#summaryToOtherBank #tituloResumen_TOB").attr("style","display: none");
        $("#summaryToOtherBank #resumenBotones_TOB").attr("style","display: none");
        $("#btn_aceptarClave").attr("disabled",false);
        if ($("#tipo_usuario_app").val()=="6"){
            validarTransfer="S";
        }
        if (validarTransfer!="S"){
            //Se llama a la pantalla de metodos de validacion
            mainValidationMethods("transferenciasOtrosBancos");
        }else{
            $("#div_carga_espera").removeClass("oculto");
            origenMetodosValidacion="transferenciasOtrosBancos";
            setTimeout(function() {
                callValidationMethodsSuccess();
                }, 2000);
        }


    });

    $("#btn_cancelarClave").click(function(){
//        $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
        $("#createToOtherBank").attr("style","display: block");
        $("#summaryToOtherBank").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").attr("style","display: none");
        $("#btn_aceptarClave").attr("disabled",false);
        infoPaginaJSONData();
    });

    $("#btn_aceptarClave").click(function(){

        $("#btn_aceptarClave").attr("disabled",true);

        callValidationMethods();


    });

    $("#btn_TOB_cancelar").click(function(){
//        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");
        $(".error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#createToOtherBank .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#createToOtherBank .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        $("#BankCode").attr("disabled", false);
        $("#BankCode2").attr("readonly", false);
        $("#BankCodeSWIFTtext").attr("readonly", false);
        $("#BankCodeIBSWIFTtext").attr("readonly", false);
        $("#NameBank").attr("readonly", false);
        $("#AddressLineBank1").attr("readonly", false);
        $("#AddressLineBank2").attr("readonly", false);
        $("#AddressLineBank3").attr("readonly", false);
        $("#CountryBank").attr("disabled", false);

        $("#name").attr("readonly", false);
        $("#AccountBank").attr("disabled", false);
        $("#AccountNumber").attr("readonly", false);
        $("#beneficiaryEmail").attr("readonly", false);
        $("#AddressLine1").attr("readonly", false);
        $("#AddressLine2").attr("readonly", false);
        $("#AddressLine3").attr("readonly", false);
        $("#TelephoneNumber").attr("readonly", false);
        $("#Country").attr("disabled", false);
        $("#BankCodeIB").attr("disabled", false);
        $("#BankCodeIB2").attr("readonly", false);
        $("#NameBankIB").attr("readonly", false);
        $("#AddressLineBankIB1").attr("readonly", false);
        $("#AddressLineBankIB2").attr("readonly", false);
        $("#AddressLineBankIB3").attr("readonly", false);
        $("#AccountNumberFFC").attr("readonly", false);
        $("#NameFFC").attr("readonly", false);
        $("#CountryBankIB").attr("disabled", false);
//        blanquearFormularios("createToOtherBank");
    });

    $("#btn__resumen_cancelar").click(function(){

        $("#createToOtherBank").fadeIn("fast");
        $("#summaryToOtherBank").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");
        //$("#marco_trabajo").css("height","1200px");

    });



    $("#btn_finalizar_TOB_final").click(function(){
        $("#createToOtherBank").attr("style","display: block");
        $("#summaryToOtherBank").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").attr("style","display: none");
        validarTransfer="";
        infoPaginaJSONData();

    });

    $("#btn_ok_TOB_final").click(function(){
        seleccionarOpcion("home");
    });

    $("#homeTOB").click(function(){
        seleccionarOpcionMenu("home");

    });

    /**
     * Print html TOB
     */
    $("#imprimirCampos_TOB").click(function(){

        var miValue = $("#Accounts" ).val();
        $("#Accounts option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#BankCode" ).val();
        $("#BankCode option[value='"+miValue+"']").attr("selected",true);

         miValue = $("#BankCodeIB" ).val();
        $("#BankCodeIB option[value='"+miValue+"']").attr("selected",true);

         miValue = $("#AccountBank" ).val();
        $("#AccountBank option[value='"+miValue+"']").attr("selected",true);

         miValue = $("#CountryBank" ).val();
        $("#CountryBank option[value='"+miValue+"']").attr("selected",true);

         miValue = $("#CountryBankIB" ).val();
        $("#CountryBankIB option[value='"+miValue+"']").attr("selected",true);

        miValue = $("#Country" ).val();
        $("#Country option[value='"+miValue+"']").attr("selected",true);





        printPageElement('div_TRANSFERENCIA_EXTERNA');
    });

    $("#btn_imprimir_TOB_final").click(function(){

        printPageElement('summaryToOtherBank');
    });

    $("#btn_TemplateGuardar_TOB_final").click(function(){
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
        //$("#Template_btn_transferencia").attr("style","display: none");
        $("#btnMakeTransfers").html("");
        $("#accionTemplate").val("nuevo");
        //$("#marco_trabajo").css("height","750px");
        newTemplateJSONDataTemplate();
        cargarSalvarTemplateTransferSuccess();
        $("#Template_btn_TOB_borrar").attr("style","display: none");

    });



    $("#coordenada_1").keyup(function(){
        $("#opc1_selected").html($("#coordenada_1").val());
    });

    $("#coordenada_2").keyup(function(){
        $("#opc2_selected").html($("#coordenada_2").val());
    });

    $("#coordenada_1").focus(function(){
        limpiarTarjeta();
        pintarCoordenadas_1("#195266");
    });

//    $("#coordenada_1").keyboard({
//        layout: 'custom',
//            customLayout: {
//            'default' : [
//
//                '8 9 ',
//                '4 5 6 7',
//                '0 1 2 3',
//                '{bksp} {a} {c}'
//            ]
//        },
//        maxLength : 3,
//        autoAccept : true,
//        lockInput: true,
//        restrictInput : true, // Prevent keys not in the displayed keyboard from being typed in
//        useCombos : false // don't want A+E to become a ligature
//    });

    $("#coordenada_2").focus(function(){
        limpiarTarjeta();
        pintarCoordenadas_2("#195266");
    });

//    $("#coordenada_2").keyboard({
//        layout: 'custom',
//        customLayout: {
//            'default' : [
//
//                '8 9 ',
//                '4 5 6 7',
//                '0 1 2 3',
//                '{bksp} {a} {c}'
//            ]
//        },
//        maxLength : 3,
//        autoAccept : true,
//        lockInput: true,
//        restrictInput : true, // Prevent keys not in the displayed keyboard from being typed in
//        useCombos : false // don't want A+E to become a ligature
//    });


});

function ParametrosPersonalesCamposTOBJSONData(){
    var url = urlParametrosPersonalesTOB;
    var param={};

    sendServiceJSON(url,param,ParametrosPersonalesTOBCamposSuccess,null,null);
}


function ParametrosPersonalesTOBCamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;
    parametrosBase = result.parametrosPersonalesBase;

    if(parametros == null || parametros == ""){
        parametros = parametrosBase;
    }

    $("#div_carga_espera").addClass("oculto");
}


function cargarInfoTemplateJSONData(id){
    var url = urlDirectorioCargarTemplate;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= id;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,cargarInfoTemplateSuccess,null,null);
}


function cargarInfoTemplateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var datos = result.datos;
    var enc=false;
    idioma = result.idioma;
    popupTmp="OK";

    $("#createToOtherBank .focus_selecionado").each(function(){
        $("#Accounts").removeClass("selectFormulario_TOB");
        $("#Accounts").removeClass("focus_selecionado");
        enc=true;
    });

    $("#createToOtherBank .selectFormulario_TOB").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });

    $("#createToOtherBank .inputFormulario_TOB").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });

    if (enc){
        $("#Accounts").addClass("selectFormulario_TOB");
    }

    $("#createToOtherBank").fadeIn("fast");
    $("#summaryToOtherBank").fadeOut("fast");
    $("#resultToOtherBank").attr("style","display: none");

  /*  if(datos.beneficiaryBankCodeType=="SWIFT"){
        $("#BankCodeSWIFTtext").val(datos.beneficiaryBankCodeNumber);
        $("#BankCode").val(datos.beneficiaryBankCodeTypeSwift);
        $("#BankCode2").val(datos.beneficiaryBankCodeNumberSwift);
    }else{
        $("#BankCode").val(datos.beneficiaryBankCodeType);
        $("#BankCode2").val(datos.beneficiaryBankCodeNumber);
    }    */

    $("#BankCodeSWIFTtext").val(datos.beneficiaryBankCodeNumberSwift);


    if (datos.beneficiaryBankCodeType!="")
        $("#BankCode").val(datos.beneficiaryBankCodeType);
    else
        $("#BankCode").val("-2")


    $("#BankCode2").val(datos.beneficiaryBankCodeNumber);


    $("#NameBank").val(datos.beneficiaryBankName);
    $("#AddressLineBank1").val(datos.beneficiaryBankAddress1);
    $("#AddressLineBank2").val(datos.beneficiaryBankAddress2);
    $("#AddressLineBank3").val(datos.beneficiaryBankAddress3);
    $("#CountryBank").val(datos.beneficiaryBankCountryCode);

    $("#name").val(datos.beneficiaryName);
    $("#AccountBank").val(datos.accountCode);
    $("#AccountNumber").val(datos.account);
    $("#beneficiaryEmail").val(datos.beneficiaryEmail);
    $("#AddressLine1").val(datos.beneficiaryAddress1);
    $("#AddressLine2").val(datos.beneficiaryAddress2);
    $("#AddressLine3").val(datos.beneficiaryAddress3);
    $("#TelephoneNumber").val(datos.beneficiaryTelephone);
    $("#Country").val(datos.beneficiaryCountryCode);


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
    //

   /* if (datos.intermediaryBankCodeType!="")
        if(datos.intermediaryBankCodeType!="SWIFT"){
            $("#BankCodeIB").val(datos.intermediaryBankCodeType)
            $("#BankCodeIB2").val(datos.intermediaryBankCodeNumber);
        }else{
            $("#BankCodeIBSWIFTtext").val(datos.intermediaryBankCodeNumber);
            $("#BankCodeIB").val(datos.intermediaryBankCodeTypeSwift)
            $("#BankCodeIB2").val(datos.intermediaryBankCodeNumberSwift);
        }
    else
        $("#BankCodeIB").val("-2")  */


    $("#BankCodeIBSWIFTtext").val(datos.intermediaryBankCodeNumberSwift);

    //$("#BankCodeIB").val(datos.intermediaryBankCodeType);
    if (datos.intermediaryBankCodeType!="")
        $("#BankCodeIB").val(datos.intermediaryBankCodeType);
    else
        $("#BankCodeIB").val("-2")

    $("#BankCodeIB2").val(datos.intermediaryBankCodeNumber);

    $("#NameBankIB").val(datos.intermediaryBankName);
    $("#AddressLineBankIB1").val(datos.intermediaryBankAddress1);
    $("#AddressLineBankIB2").val(datos.intermediaryBankAddress2);
    $("#AddressLineBankIB3").val(datos.intermediaryBankAddress3);

    if (datos.intermediaryBankCountryCode!="")
        $("#CountryBankIB").val(datos.intermediaryBankCountryCode)
    else
        $("#CountryBankIB").val("-2")

    validarTransfer =datos.statusAprobacion;

    $("#AccountNumberFFC").val(datos.furtherCreditAccount);
    $("#NameFFC").val(datos.furtherCreditName);
    origenTemplate="TMP";
}


function infoPaginaJSONData(){
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
    $("#BankCodeIB2OB").attr("style","display:none");
    $("#NameBankIBOB").attr("style","display:none");
    $("#AddressLineBankIB1OB").attr("style","display:none");
    $("#CountryBankIBOB").attr("style","display:none");
    $("#Template_btn_back2").attr("style","display:none");
    $("#resultToOtherBank").attr("style","display: none");
    $("#createToOtherBank").attr("style","display: block");
    $("#summaryToOtherBank").attr("style","display: none");
    $("#mensaje_error").empty();
    $("#div_mensajes_error").attr("style","display: none");
    $("#div_numref_TOB").attr("style","display: none ");
    $("#div_estatus_TOB").attr("style","display: none");
    $("#tituloExitoso_TOB").attr("style","display: none");
    $("#div_carga_espera").removeClass("oculto");
    //Indica que el origen son lsa transferencias a otros bancos
    jsonTransfers[0]= origenTemplate;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});


    sendServiceJSON(url,param,infoPaginaSuccess,null,null);
}


function infoPaginaSuccess(originalRequest){
    console.log('######originalRequest',originalRequest);
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    templates = result.listaTemplates;
    TAGMsgInfoNombreBeneficiario = result.TAGMsgInfoNombreBeneficiario;
    idioma = result.idioma;
    usuario = result.usuario;
    PaisesBeneficiario = result.paisesBeneficiario;
    var mensaje =result.mensaje;
    var respuesta =result.respuesta;
    var selected= "-2";
    var codSwift = [];
    if (result.accountSelected!=null)
        selected=result.accountSelected;
    if (mensaje=="OK"){
        // Habilitar

        $("#BankCode").attr("disabled", false);
        $("#BankCode2").attr("readonly", false);
        $("#BankCodeSWIFTtext").attr("readonly", false);
        $("#BankCodeIBSWIFTtext").attr("readonly", false);
        $("#NameBank").attr("readonly", false);
        $("#AddressLineBank1").attr("readonly", false);
        $("#AddressLineBank2").attr("readonly", false);
        $("#AddressLineBank3").attr("readonly", false);
        $("#CountryBank").attr("disabled", false);

        $("#name").attr("readonly", false);
        $("#AccountBank").attr("disabled", false);
        $("#AccountNumber").attr("readonly", false);
        $("#beneficiaryEmail").attr("readonly", false);
        $("#AddressLine1").attr("readonly", false);
        $("#AddressLine2").attr("readonly", false);
        $("#AddressLine3").attr("readonly", false);
        $("#TelephoneNumber").attr("readonly", false);
        $("#Country").attr("disabled", false);
        $("#BankCodeIB").attr("disabled", false);
        $("#BankCodeIB2").attr("readonly", false);
        $("#NameBankIB").attr("readonly", false);
        $("#AddressLineBankIB1").attr("readonly", false);
        $("#AddressLineBankIB2").attr("readonly", false);
        $("#AddressLineBankIB3").attr("readonly", false);
        $("#AccountNumberFFC").attr("readonly", false);
        $("#NameFFC").attr("readonly", false);
        $("#CountryBankIB").attr("disabled", false);
        //
        $("#noCreateToOtherBank").attr("style","display: none");
        if(valorCuentas!=null)
          if(idioma=="_us_en")
              cargar_selectCuenta("Accounts", valorCuentas,"Select","-2", selected);
          else
              cargar_selectCuenta("Accounts", valorCuentas,"Seleccione","-2", selected);

        //Elimina las que no son en dolares
        $("#Accounts option").each(function(){
            var texto=$(this).text();
            if ((texto.indexOf("USD") < 0)&&(texto!="Select")){
                $(this).remove();
            }
        });


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
                //cargar_selectPersonal("BankCodeSWIFT", codSwift,"Seleccione","-2");
                //cargar_selectPersonal("BankCodeIBSWIFT", codSwift,"Seleccione","-2");
            }

        }

        if(fechaCierre!=null)
            $("#tagAvailableBalanceDate").html(fechaCierre);

        alert(TAGMsgInfoNombreBeneficiario);
        ParametrosPersonalesCamposTOBJSONData();
}  else{
        $("#createToOtherBank").attr("style","display: none");
        $("#div_carga_espera").addClass("oculto");
        $("#noCreateToOtherBank").fadeIn("low");
        $("#noInfo_noCreateToOtherBank").html(respuesta);
    }
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


function  cargarData_toOtherBank(){
    //Indica que el origen son transferencias a otros bancos
    origenTemplate="TOB";
    popupTmp="NO OK";
    $("#nocreateToOtherBank").attr("style","display: none");

    infoPaginaJSONData();
}


function cargarDatos_template(key){
//   alert("key: "+ key);
    alert(key);
    $("#BankCode2").val(key);
}


function TransfersValidateJSONData(){
    var url = urlTransfersValidateTOB;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};

    ResumenTOB.account=$('#Accounts :selected').html();
    ResumenTOB.accountCode=$("#Accounts").get(0).value;
    ResumenTOB.beneficiaryName=$("#name").get(0).value;
    ResumenTOB.beneficiaryAccount=$("#AccountNumber").get(0).value;
    ResumenTOB.beneficiaryAccountBank=$("#AccountBank").get(0).value;
    ResumenTOB.beneficiaryEmail=$("#beneficiaryEmail").get(0).value;
    ResumenTOB.beneficiaryAddress1=$("#AddressLine1").get(0).value;
    ResumenTOB.beneficiaryAddress2=$("#AddressLine2").get(0).value;
    ResumenTOB.beneficiaryAddress3=$("#AddressLine3").get(0).value;
    ResumenTOB.beneficiaryTelephone=$("#TelephoneNumber").get(0).value;
    ResumenTOB.beneficiaryCountry=$('#Country :selected').html();
    ResumenTOB.beneficiaryCountryCode=$("#Country").get(0).value;
    //ResumenTOB.beneficiaryBankCodeType=$('#BankCode :selected').html();
    if($("#BankCode").val()=="-2"){
        ResumenTOB.beneficiaryBankCodeType="";
    }else{
        ResumenTOB.beneficiaryBankCodeType=$("#BankCode").val();
    }

    ResumenTOB.beneficiaryBankCodeNumber=$("#BankCode2").get(0).value;
    ResumenTOB.beneficiaryBankName=$("#NameBank").get(0).value;
    ResumenTOB.beneficiaryBankAddress1=$("#AddressLineBank1").get(0).value;
    ResumenTOB.beneficiaryBankAddress2=$("#AddressLineBank2").get(0).value;
    ResumenTOB.beneficiaryBankAddress3=$("#AddressLineBank3").get(0).value;
    ResumenTOB.beneficiaryBankCountry=$('#CountryBank :selected').html();
    ResumenTOB.beneficiaryBankCountryCode=$("#CountryBank").get(0).value;

    if($("#BankCodeSWIFTtext").get(0).value.length == 0 ){
        ResumenTOB.beneficiaryBankCodeTypeSwift="";
    }else{
        ResumenTOB.beneficiaryBankCodeTypeSwift="SWIFT";
    }

    ResumenTOB.beneficiaryBankCodeNumberSwift=$("#BankCodeSWIFTtext").get(0).value;

    if($("#BankCodeIBSWIFTtext").get(0).value.length == 0 ){
        ResumenTOB.intermediaryBankCodeTypeSwift="";
    }else{
        ResumenTOB.intermediaryBankCodeTypeSwift="SWIFT";
    }

    ResumenTOB.intermediaryBankCodeNumberSwift=$("#BankCodeIBSWIFTtext").get(0).value;

    if($("#BankCodeIB").get(0).value=="-2" || $("#BankCodeIB2").get(0).value.length == 0 ){
        ResumenTOB.intermediaryBankCodeType="";
    }else{
        //ResumenTOB.intermediaryBankCodeType=$('#BankCodeIB :selected').html();
        ResumenTOB.intermediaryBankCodeType=$("#BankCodeIB").val();
    }

    ResumenTOB.intermediaryBankCodeNumber=$("#BankCodeIB2").get(0).value;
    ResumenTOB.intermediaryBankName=$("#NameBankIB").get(0).value;
    ResumenTOB.intermediaryBankAddress1=$("#AddressLineBankIB1").get(0).value;
    ResumenTOB.intermediaryBankAddress2=$("#AddressLineBankIB2").get(0).value;
    ResumenTOB.intermediaryBankAddress3=$("#AddressLineBankIB3").get(0).value;
    if($("#CountryBankIB").get(0).value=="-2"){
        ResumenTOB.intermediaryBankCountry="";
        ResumenTOB.intermediaryBankCountryCode="";
    }else{
        ResumenTOB.intermediaryBankCountry=$('#CountryBankIB :selected').html();
        ResumenTOB.intermediaryBankCountryCode=$("#CountryBankIB").get(0).value;
    }


    ResumenTOB.furtherCreditAccount=$("#AccountNumberFFC").get(0).value;
    ResumenTOB.furtherCreditName=$("#NameFFC").get(0).value;
//    ResumenTOB.amount=parseFloat($("#AmmountAI").get(0).value);

    var montoAux="";

    montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
    montoAux=(montoAux).replace(/\./g,',');
    montoAux=(montoAux).replace(/t/g,'.');

    //ResumenTOB.amount=$("#AmmountAI").get(0).value;
    ResumenTOB.amount=montoAux;
//    ResumenTOB.amount="0";
    ResumenTOB.recieverName=$("#ReceiverInformation").get(0).value;

    ResumenTOB.motivo=$("#MotivoAI").val();

    jsonTransfers[0]= ResumenTOB;

    $(".TOB_borrarData").each(function(){
        $("#"+this.id).html("");
    });

    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON(url,param,TransfersValidateSuccess,null,null);
}

function TransfersValidateSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var resumen;
    var existeBen =result.existeBen;
    var existeInt = result.existeInt;
    idioma = result.idioma;

    //$("#marco_trabajo").css("height","900px");
    resumen = result.resumenTOB;
    resumentransferToOtherBank = result.resumenTOB;


    $("#RAccounts").html(quitarSaldo($('#Accounts :selected').html()));
    $("#Rname").html($('#name').val());
    $("#RAccountNumber").html($('#AccountBank :selected').html()+"  |  "+$("#AccountNumber").get(0).value);
    $("#RbeneficiaryEmail").html($("#beneficiaryEmail").get(0).value);
    $("#RAddressLine1").html($("#AddressLine1").get(0).value);
    if($("#AddressLine2").get(0).value !=""){
        $("#div_RAddressLine2").fadeIn("fast");
        $("#RAddressLine2").html($("#AddressLine2").get(0).value);
    }
    if($("#AddressLine3").get(0).value !=""){
        $("#div_RAddressLine3").fadeIn("fast");
        $("#RAddressLine3").html(Trim($("#AddressLine3").get(0).value));
    }
    if(Trim($("#TelephoneNumber").get(0).value) !=""){
        $("#div_RTelephoneNumber").fadeIn("fast");
        $("#RTelephoneNumber").html($("#TelephoneNumber").get(0).value);
    }

    $("#RCountry").html($('#Country :selected').html());
    if(Trim($("#BankCode2").get(0).value) !=""){
        $("#div_RBankCode").fadeIn("fast");
        $("#RBankCode").html($('#BankCode :selected').html()+"  |  "+$('#BankCode2').val());
    }
    $("#RNameBank").html(resumen.beneficiaryBankName);


    $("#RAddressLineBank1").html(resumen.beneficiaryBankAddress1);
    if(Trim($("#AddressLineBank2").get(0).value) !=""){
        $("#div_RAddressLineBank2").fadeIn("fast");
        $("#RAddressLineBank2").html($("#AddressLineBank2").get(0).value);
    }

    if(Trim($("#AddressLineBank3").get(0).value) !=""){
        $("#div_RAddressLineBank3").fadeIn("fast");
        $("#RAddressLineBank3").html($("#AddressLineBank3").get(0).value);
    }

    $("#RCountryBank").html($('#CountryBank :selected').html());

    if(Trim($("#BankCodeIB2").get(0).value) !="" || Trim($("#NameBankIB").get(0).value) !="" || Trim($("#AddressLineBankIB1").get(0).value) !=""
        || Trim($("#AddressLineBankIB2").get(0).value) !="" || Trim($("#AddressLineBankIB3").get(0).value) !="" || $('#CountryBankIB :selected').val() !="-2") {
        $("#div_TOB_resumen_intermediary").fadeIn("fast");
    }else{
        $("#div_TOB_resumen_intermediary").attr("style","display: none");
    }
    if(Trim($("#BankCodeSWIFTtext").get(0).value) !=""){
        $("#div_RBankCodeSwift").fadeIn("fast");
        $("#RBankCodeSwift").html("SWIFT  |  "+Trim($("#BankCodeSWIFTtext").get(0).value));
    }else{
        $("#div_RBankCodeSwift").attr("style","display: none");
    }

    if(Trim($("#BankCodeIBSWIFTtext").get(0).value) !=""){
        $("#div_RBankCodeIBSwift").fadeIn("fast");
        $("#RBankCodeIBSwift").html("SWIFT  |  "+Trim($("#BankCodeIBSWIFTtext").get(0).value));
    }else{
        $("#div_RBankCodeIBSwift").attr("style","display: none");
    }
    if(Trim($("#BankCodeIB2").get(0).value) !=""){
        $("#div_RBankCodeIB").fadeIn("fast");
        $("#RBankCodeIB").html($('#BankCodeIB :selected').html()+"  |  "+Trim($("#BankCodeIB2").get(0).value));
    }else{
        $("#div_RBankCodeIB").attr("style","display: none");
    }
    if(Trim($("#NameBankIB").get(0).value) !=""){
        $("#div_RNameBankIB").fadeIn("fast");
        $("#RNameBankIB").html(Trim($("#NameBankIB").get(0).value));
    }else{
        $("#div_RNameBankIB").attr("style","display: none");
    }   div_RAddressLineBankIB2
    if(Trim($("#AddressLineBankIB1").get(0).value) !=""){
        $("#div_RAddressLineBankIB1").fadeIn("fast");
        $("#RAddressLineBankIB1").html(Trim($("#AddressLineBankIB1").get(0).value));
    }else{
        $("#div_RAddressLineBankIB1").attr("style","display: none");
    }
    if(Trim($("#AddressLineBankIB2").get(0).value) !=""){
        $("#div_RAddressLineBankIB2").fadeIn("fast");
        $("#RAddressLineBankIB2").html(Trim($("#AddressLineBankIB2").get(0).value));
    }else{
        $("#div_RAddressLineBankIB2").attr("style","display: none");
    }
    if(Trim($("#AddressLineBankIB3").get(0).value) !=""){
        $("#div_RAddressLineBankIB3").fadeIn("fast");
        $("#RAddressLineBankIB3").html(Trim($("#AddressLineBankIB3").get(0).value));
    }else{
        $("#div_RAddressLineBankIB3").attr("style","display: none");
    }

    if($('#CountryBankIB :selected').val() !="-2"){
        $("#div_RCountryBankIB").fadeIn("fast");
        $("#RCountryBankIB").html($('#CountryBankIB :selected').html());
    }else{
        $("#div_RCountryBankIB").attr("style","display: none");
    }
    if(Trim($("#AccountNumberFFC").get(0).value) !="" || Trim($("#NameFFC").get(0).value) !=""){
        $("#div_TOB_resumen_furtherCredit").fadeIn("fast");
    }else{
        $("#div_TOB_resumen_furtherCredit").attr("style","display: none");
    }
    if(Trim($("#AccountNumberFFC").get(0).value) !=""){
        $("#div_RAccountNumberFFC").fadeIn("fast");
        $("#RAccountNumberFFC").html(Trim($("#AccountNumberFFC").get(0).value));
    }else{
        $("#div_RAccountNumberFFC").attr("style","display: none");
    }
    if(Trim($("#NameFFC").get(0).value) !=""){
        $("#div_RNameFFC").fadeIn("fast");
        $("#RNameFFC").html(Trim($("#NameFFC").get(0).value));
    }else{
        $("#div_RNameFFC").attr("style","display: none");
    }

    Trim($("#RAmmountAI").html($("#AmmountAI").get(0).value)+" "+"USD");
    $("#RMotivoAI").html($("#MotivoAI").get(0).value);

    if(Trim($("#ReceiverInformation").get(0).value) !=""){
        $("#div_RReceiverInformation").fadeIn("fast");
        $("#RReceiverInformation").html(Trim($("#ReceiverInformation").get(0).value));
    }else{
        $("#div_RReceiverInformation").attr("style","display: none");
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
            $("#btn__resumen_cancelar").val("Back");
            mensaje = mensaje + "If problems persist, please contact your Financial Advisor or Account Executive. <br>";
        }
        else{
            $("#btn__resumen_cancelar").val("Volver");
            mensaje = mensaje + "En caso de persistir los inconvenientes, comun&iacute;quese con su Asesor Financiero o Ejecutivo de Cuenta. <br>";
        }

        $("#btn__resumen_aceptar").fadeOut("fast");


    }else{
        if(idioma=="_us_en")
          $("#btn__resumen_cancelar").val("Back");
        else
          $("#btn__resumen_cancelar").val("Volver");
        $("#btn__resumen_aceptar").attr("style","display: ");
    }


    if(mensaje!=""){

        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#div_mensajes_error").fadeOut("fast");
        $("#createToOtherBank").fadeOut("fast");
        $("#summaryToOtherBank").fadeIn("fast");

    }



}


function TransferGuardarJSONData(){
    var url = urlTransfersSaveTOB;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};
//    resumentransferToOtherBank.amount = parseFloat($("#AmmountAI").get(0).value.trim());
    var montoAux="";


    montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
    montoAux=(montoAux).replace(/\./g,',');
    montoAux=(montoAux).replace(/t/g,'.');

    //resumentransferToOtherBank.amount = Trim($("#AmmountAI").get(0).value);
    resumentransferToOtherBank.amount = Trim(montoAux);
    resumentransferToOtherBank.claveTemporal = Trim($("#pwdClaveConfirmTransfer_TOB").get(0).value);
    jsonTransfers[0]= resumentransferToOtherBank;


    param.jsonTransfers=JSON.stringify({"resumenTOBs":jsonTransfers});

    sendServiceJSON_sinc(url,param,TransfersGuardarSuccess,null,null);
}


function TransfersGuardarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var numRef = result.numref;
    idioma = result.idioma;
    var mensaje = result.mensaje;
    resumentransferToOtherBank = result.resumenTOB;
    encontro=result.encontro;
    var exito = result.code;
//    $("#summaryToOtherBank").fadeOut("fast");
//    $("#resultToOtherBank").fadeIn("fast");
    //econtro=1 no existe la plantilla  y la puede guardar
    if(encontro=="1"){
        if(popupTmp!="OK"){
            $("#btn_TemplateGuardar_TOB_final").attr("style","display:''");
        }else{
            $("#btn_TemplateGuardar_TOB_final").attr("style","display: none");
         }
    }else{
        $("#btn_TemplateGuardar_TOB_final").attr("style","display: none");
    }

    if(exito!="0"){
        $("#createToOtherBank").attr("style","display: block");
        $("#summaryToOtherBank").attr("style","display: none");
//        $("#mensaje_error").empty();
//        $("#div_mensajes_error").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        //infoPaginaJSONData();

//        $("#img_resultadoTransferTOB").attr("src","../vbtonline/resources/images/error.png");
    }else{
        $("#div_numref_TOB").attr("style","display: ");
        $("#div_estatus_TOB").attr("style","display: ");
        $("#tituloExitoso_TOB").attr("style","display: block");

        $("#resumenBotones_TOB_Finales").attr("style","display: ");
//        $("#txt_resultadoTransferTOB").html(result.mensaje);

        if ( $("#tipo_usuario_app").val()=="6"){
            var mensaje2="";
            if(idioma=="_us_en"){
                $("#status_TOB").html("Input");
                mensaje2="This transfer instruction was successfully created; it must be Approved and Released by users with the appropriate permissions";
            }else{
                $("#status_TOB").html("Cargada");
                mensaje2="Esta orden de transferencia fue cargada exitosamente, la misma debe ser Aprobada y Liberada por los usuarios con los permisos correspondientes";
            }
            $("#mensaje_error").html(mensaje2);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(idioma=="_us_en")
                $("#status_TOB").html("In Process");
            else
                $("#status_TOB").html("En Proceso");

        }


        $("#numRef_TOB").html(numRef);
        $("#summaryToOtherBank #clave_TOB").attr("style","display: none");
        if ( $("#tipo_usuario_app").val()!="6"){
            $("#mensaje_error").empty();
            $("#div_mensajes_error").fadeOut("slow");
        }
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
        if(popupTmp!="OK"){
            if(origenTemplate=="TMP"){
                $("#btn_TemplateGuardar_TOB_final").attr("style","display:none");
            }else{
                $("#btn_TemplateGuardar_TOB_final").attr("style","display:");
            }
        }else{
            $("#btn_TemplateGuardar_TOB_final").attr("style","display: none");
        }




//        $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
//        $("#img_resultadoTransferTOB").attr("src","../vbtonline/resources/images/exito.png");
    }
    $("#div_carga_espera").addClass("oculto");
}

function TransferGenerarClaveJSONData(){
    var url = urlTransfersGenerarClaveTOB;
    var param={};

    sendServiceJSON(url,param,TransferGenerarClaveSuccess,null,null);
}


function TransferGenerarClaveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
//    alert("se ha enviado una clave a su correo");
}


function GenerarClaveSMSJSONData(){
    var url = urlSecurityGenerarClaveSMS;
    var param={};

    sendServiceJSON_sinc(url,param,GenerarClaveSMSSuccess,null,null);
}


function GenerarClaveSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
  /*  $("#mensaje_error").empty();
    $("#mensaje_error").html(result.respuesta);
    $("#div_mensajes_error").fadeIn("slow");  */


}


function ValidarClaveTransferJSONData(){
    var url = urlValidarClaveTOB;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#pwdClaveConfirmTransfer_TOB").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,ValidarClaveTransferSuccess,null,null);
}


function ValidarClaveTransferSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var exito = result.claveValida;
    var mensaje="";
    idioma = result.idioma;



    if(exito=="OK"){
        TransferGuardarJSONData();
    }else if (exito=="NO OK"){
        $("#btn_aceptarClave").attr("disabled",false);
        if(idioma=="_us_en")
          mensaje ="The Transaction Key you entered is wrong"+"<br>"+"If you failed three times entering your Transaction Key, this transfer operation will be canceled";
        else
          mensaje ="Clave de confirmaci\u00f3n de operaci\u00f3n incorrecta."+"<br>"+"Si se equivoca tres veces colocando su clave de operaciones, Esta transferencia ser&aacute; cancelada";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        if(idioma=="_us_en")
            mensaje ="The Transaction Key you entered is wrong for third time, your transfer operation was canceled";
        else
            mensaje ="La clave de confirmaci\u00f3n que ingres\u00f3 es incorrecta por tercera vez consecutiva, su transferencia ha sido cancelada";
        alert(mensaje);
        infoPaginaJSONData();
        /*$("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");*/

    }


}



function cargarSalvarTemplateTransferSuccess(){
    //                   this is the json return data
    //$("#marco_trabajo").css("height","750px");

    $("#Template_BankCode").val(resumentransferToOtherBank.beneficiaryBankCodeType);
    $("#Template_BankCode2").val(resumentransferToOtherBank.beneficiaryBankCodeNumber);
    //$("#Template_SwiftBankCode").val(resumentransferToOtherBank.beneficiaryBankCodeTypeSwift);
    $("#Template_SwiftBankCode2").val(resumentransferToOtherBank.beneficiaryBankCodeNumberSwift);
    $("#Template_NameBank").val(resumentransferToOtherBank.beneficiaryBankName);
    $("#Template_AddressLineBank1").val(resumentransferToOtherBank.beneficiaryBankAddress1);
    $("#Template_AddressLineBank2").val(resumentransferToOtherBank.beneficiaryBankAddress2);
    $("#Template_AddressLineBank3").val(resumentransferToOtherBank.beneficiaryBankAddress3);
    $("#Template_CountryBank").val(resumentransferToOtherBank.beneficiaryBankCountryCode);

    $("#Template_name").val(resumentransferToOtherBank.beneficiaryName);
    $("#Template_AccountBank").val(resumentransferToOtherBank.beneficiaryAccountBank);
    $("#Template_AccountNumber").val(resumentransferToOtherBank.beneficiaryAccount);
    $("#Template_beneficiaryEmail").val(resumentransferToOtherBank.beneficiaryEmail);
    $("#Template_AddressLine1").val(resumentransferToOtherBank.beneficiaryAddress1);
    $("#Template_AddressLine2").val(resumentransferToOtherBank.beneficiaryAddress2);
    $("#Template_AddressLine3").val(resumentransferToOtherBank.beneficiaryAddress3);
    $("#Template_TelephoneNumber").val(resumentransferToOtherBank.beneficiaryTelephone);
    $("#Template_Country").val(resumentransferToOtherBank.beneficiaryCountryCode);

    if(resumentransferToOtherBank.intermediaryBankCodeType=="ACCOUNT" && Trim(resumentransferToOtherBank.intermediaryBankCodeNumber)!=""){
        $("#BankCodeIB").val(resumentransferToOtherBank.intermediaryBankCodeType);
    }else{
        $("#BankCodeIB").val("-2");
    }
    $("#Template_BankCodeIB").val(resumentransferToOtherBank.intermediaryBankCodeType);
    $("#Template_BankCodeIB2").val(resumentransferToOtherBank.intermediaryBankCodeNumber);
    $("#Template_SwiftBankCodeIB2").val(resumentransferToOtherBank.intermediaryBankCodeNumberSwift);
    $("#Template_NameBankIB").val(resumentransferToOtherBank.intermediaryBankName);
    $("#Template_AddressLineBankIB1").val(resumentransferToOtherBank.intermediaryBankAddress1);
    $("#Template_AddressLineBankIB2").val(resumentransferToOtherBank.intermediaryBankAddress2);
    $("#Template_AddressLineBankIB3").val(resumentransferToOtherBank.intermediaryBankAddress3);
    $("#Template_CountryBankIB").val(resumentransferToOtherBank.intermediaryBankCountryCode);

    $("#Template_AccountNumberFFC").val(resumentransferToOtherBank.furtherCreditAccount);
    $("#Template_NameFFC").val(resumentransferToOtherBank.furtherCreditName);
    $("#Template_nombreTemplate").val(resumentransferToOtherBank.beneficiaryName);

}


function aceptarCondicionesJSONDATA(valor){
    var url = urlAceptarCondicionesTrasfe;
    var param={};
    param.code=valor;
    sendServiceJSON(url,param,aceptarCondicionesSuccess,null,null);
}

function aceptarCondicionesSuccess(originalRequest){
   if (acepto=="S")
     seleccionarOpcion(bk_opcion);
   else
    seleccionarOpcion("home");
}

function cargarTemplatePoppup(alias){
    var arr = alias.split("|");
    $("#Accounts").addClass("focus_selecionado");
    cargarInfoTemplateJSONData(arr[1]);
    $("#sign_up_template_transfer").trigger('close');
    $("#tagiTemplate").val(arr[0]);
    popupTmp="OK";
}



function mostrarBotones(actual){
    switch(actual){
        case "metodosValidacionTransfer":
            $("#form_10_botones").removeClass("oculto");
            break;
        default:
            $("#form_10_botones").addClass("oculto");
    }
    $("#div_btn_CambiarMtd").attr("style","display: block");

}

function ocultarBotones (actual){
    switch(actual){
        case "metodosValidacionTransfer":
            $("#form_10_botones").addClass("oculto");
            break;
        default:
            $("#form_10_botones").addClass("oculto");
    }

    $("#div_btn_CambiarMtd").attr("style","display: none");
}


function validarAprobacion(id){
    idTemplate=id;
    $("#sign_up_template_transfer").trigger('close');
    //Se llama a la pantalla de metodos de validacion
    mainValidationMethods("aprobarTemplateTensferencias");
}


function cambiarEstatusTemplate(id){
    var url = urlCambiarEstatusTemplate;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= id;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON_sinc(url,param,infoPaginaTemplateCambiarEstatusSuccess,null,null);
}

function infoPaginaTemplateCambiarEstatusSuccess(originalRequest){
   var mensaje="";
    var result = originalRequest;
    var exito = result.respuesta;
    //var datosTabla = result.contenidoTabla_infoTest;
    idioma =result.idioma;

    if(exito == "OK"){
      idTemplate="OK";
        if(idioma=="_us_en"){
            mensaje= "Template successfully Approved";

        }else
            mensaje= "La plantilla fue aprobada satisfactoriamente";

        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        infoPaginaTemplateConsultaJSONData();
       // $("#tagiTemplate").click();
    }
}



function cambiarEstatusTemplateFC(id){
    var url = urlCambiarEstatusTemplate;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= id;


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON_sinc(url,param,infoPaginaTemplateCambiarEstatusSuccessFC,null,null);
}

function infoPaginaTemplateCambiarEstatusSuccessFC(originalRequest){
    var mensaje="";
    var result = originalRequest;
    var exito = result.respuesta;
    //var datosTabla = result.contenidoTabla_infoTest;
    idioma =result.idioma;

    if(exito == "OK"){
        idTemplate="OK";
    }
}


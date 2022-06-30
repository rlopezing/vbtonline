var urlTransfersCargar="Transfers_cargar.action";
var urlTransfersValidateTOB="Transfers_validateToOtherBanks.action";
var urlTransfersValidateTCB="Transfers_validateToCaymanBranch.action";
var urlTransfersSaveTOB="Transfers_saveToOtherBanks.action";
var urlDirectorioCargarTemplate="Transfers_cargarDetalleDirectorio.action";
var urlTransfersGenerarClaveTOB="Security_crearClaveRamdom.action";
var urlValidarClaveTOB="Transfers_validarClaveTransferenciasTOB.action";
var urlParametrosPersonalesTOB="DesingBank_cargarParametrosPersonales.action";
var urlAceptarCondicionesTrasfe="Transfers_aceptarCondicionesTransfe.action";
var urlDirectorioCargarTransfer="Transfers_consultarDirectorioTransfer.action";
var urlSecurityGenerarClaveSMS="Security_crearClaveRamdomSMS.action";
var urlCambiarEstatusTemplate = "Transfers_cambiarEstatusTemplate.action";
var urlIdTemplate = "Transfers_bucarIdTemplate.action";
var urlValidacionBancoBeneficiario = "Transfers_validacionBancoBeneficiario.action";
var urlLimpiarSoporte = "Transfers_removerSoporte.action";
var urlBorrarArchivo = "Transfers_borrarArchivo.action";
var urlVerificarDiaTOB = "Transfers_mismoDia.action";

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
var tmpTranfers="";
var transferFinalAprobada="";
var archivosSubidos="";
var archivosTotales=0;
var archivosaSubir=0;
var cantidadMaximaArchivos="";
var pesoMaximoArchivos="";
var validacionArchivos="";
var validaArchivosContrato="";
var monedaRTOB="";
var mismoDiaTOB="";
var cancelacionProducto="";

$(document).ready(function(){

    $("#cancelarProductoMostrarTOB").hide();

    $("#montoComisionMostrar").hide();

    $('#Accounts').change(function(){

        $('#Motivos').prop('disabled', false);
        if ($("#productCancelationTOB").prop('checked')) {
            $("#productCancelationTOB").prop("checked", false);
            montoTOB = $("#AmmountAI").val('');
        }

        if($(this).val() != '-2' ) {
            if($("#Accounts option:selected").text().indexOf('USD')>=0){
                // $("#AmountCodMon").html("USD");
                $("#AmountCodMon").html(createCurrency("USD"));
                monedaRTOB = "USD";
            }else if($("#Accounts option:selected").text().indexOf('EUR')>=0){
                // $("#AmountCodMon").html("EUR");
                $("#AmountCodMon").html(createCurrency("EUR"));
                monedaRTOB = "EUR";
            }

        $('#CountryBank').change(function (){
            if ($("#CountryBank").get(0).value != "-2"){
                if ($("#tipo_contrato_app").val() == "FC"){
                    $("#cancelarProductoMostrarTOB").hide();
                }else{
                    if ($("#Accounts option:selected").attr("extra6") <= "0"){
                        $("#cancelarProductoMostrarTOB").show();
                    }else{
                        $("#cancelarProductoMostrarTOB").hide();
                    }
                }

            }else{
                $("#cancelarProductoMostrarTOB").hide();
            }
        });

        }else{
            $("#AmountCodMon").html("");
            $("#cancelarProductoMostrarTOB").hide();
        }

        if ($("#Accounts option:selected").attr("extra1") != "USD"){
            $("#mismoDiaTOB").hide();
        }else{
            $("#mismoDiaTOB").show();
        }

        // $("#AmountCodMon").html($("#Accounts option:selected").attr("extra1"));
        $("#AmountCodMon").html(createCurrency($("#Accounts option:selected").attr("extra1")));

        if ($("#tipo_contrato_app").val() == "FC"){
            $("#cancelarProductoMostrarTOB").hide();
        }else {

            if (($("#Accounts option:selected").attr("extra6") <= "0") && ($('#CountryBank option:selected').get(0).value != "-2")) {
                $("#cancelarProductoMostrarTOB").show();
            } else {
                $("#cancelarProductoMostrarTOB").hide();
            }
        }
    });

    $("#productCancelationTOB").click(function () {

        if ($("#productCancelationTOB").prop('checked')) {
            $('#Motivos > option[value="16"]').attr('selected', 'selected');
            $('#Motivos').prop('disabled', true);
            $("#mismoDiaTOB").hide();
            if ( ($("#Accounts option:selected").get(0).value != "-2") && ($('#CountryBank option:selected').get(0).value != "-2") ){
                calcularComisionCancelacionTOB( $("#Accounts option:selected").attr("extra2"), $("#Accounts option:selected").attr("extra4"), $("#Accounts option:selected").val().split(" |")[0], mismoDiaTOB,$('#CountryBank option:selected').val());
            }

            if ($("#Accounts option:selected").attr("extra1") != "USD"){
                $("#mismoDiaTOB").hide();
            }
           // montoTOB = $("#AmmountAI").val($("#Accounts option:selected").attr("extra2"));
            cancelacionProducto="S";

        } else{
            $("#montoComisionMostrar").hide();
            $("#mismoDiaTOB").show();
            $("#montoComision").html(" ");
            montoTOB = $("#AmmountAI").val('');
            cancelacionProducto="N";
            $('#Motivos').prop('disabled', false);
        }
    });

    $('#fileupload').fileupload({
        dataType: 'json',
        maxNumberOfFiles:10,
        change : function (e, data) {
            var cantidadSubidos = 0;
            if(archivosSubidos!=null&&archivosSubidos!=""&&archivosSubidos!=undefined){
                cantidadSubidos = archivosSubidos.length;
            }


            if(data.files.length>cantidadMaximaArchivos || cantidadSubidos + data.files.length>cantidadMaximaArchivos){
                if(idioma=="_us_en")
                    alert("Max "+cantidadMaximaArchivos+" files are allowed");
                else
                    alert("Se permiten máximo "+cantidadMaximaArchivos+" archivos");
                return false;

            }else{
                /*console.log("1 - archivosaSubir:"+data.files.length);*/
                archivosaSubir += data.files.length;
                /*console.log("2 - archivosaSubir:"+data.files.length);*/
            }

        },
        add :  function ( e , data ) {
            /*console.log("Entro al Add del fileUpload (evalua los archivos)");*/
            var msgError = [];
            var cantidadSubidos = 0;
            /*console.log("archivosSubidos:"+archivosSubidos);*/
            if(archivosSubidos!=null&&archivosSubidos!=""&&archivosSubidos!=undefined)
                cantidadSubidos = archivosSubidos.length;
            /*console.log("data.files");
            console.log(data.files);*/
            $.each(data.files, function(index, file) {
                if(data.files[index].size > pesoMaximoArchivos){
                    /*console.log("Mayor del peso Respectivo");
                    console.log(data.files[index]);
                    console.log(pesoMaximoArchivos);*/
                    if(idioma=="_us_en")
                        msgError.push('The file size exceeds the maximum allowed: '+ data.files[index].name);
                    else
                        msgError.push( 'Tamaño del archivo supera el maximo permitido: ' + data.files[index].name);
                }
                /*var acceptFileTypes = /^application\/(pdf)$/i;   /*^(application|image)\/(pdf|jpe?g|png)$*/
                var acceptFileTypes = /^(application|image)\/(pdf|jpe?g|png)$/i;
                if(data.files[index]['type'].length==0 || !acceptFileTypes.test(data.files[index]['type'])) {
                    /*console.log("Diferente Formato");
                    console.log("data.files[index]['type']:" + data.files[index]['type']);
                    console.log("Soportados:" + acceptFileTypes);*/
                    if(idioma=="_us_en")
                        msgError.push('File type is not allowed: '+ data.files[index].name);
                    else
                        msgError.push('Tipo de archivo no es permitido: '+ data.files[index].name);
                }
            });

            if(msgError.length>0){
                alert(msgError);
                    /*if(archivosTotales>0)*/
                        /*archivosTotales--;*/
                archivosaSubir--;
                archivosTotales = (archivosaSubir+cantidadSubidos);
                /*console.log("archivosaSubir:" + archivosaSubir);
                console.log("cantidadSubidos:" + cantidadSubidos);
                console.log("archivosTotales:" + archivosTotales);*/
            }else{
                archivosTotales = (archivosaSubir+cantidadSubidos);
                /*console.log("archivosaSubir:" + archivosaSubir);
                console.log("cantidadSubidos:" + cantidadSubidos);
                console.log("archivosTotales:" + archivosTotales);
                console.log("Hace el Sub mite del fileUpload (los Archivos a montar)");*/
                data.submit();
                $("#loading").addClass("load");
                $(".Nofiles").html("");
            }
            if(!archivosTotales == 0 )
                progreso(cantidadSubidos,archivosTotales);
        },
        done: function (e, data) {
            archivosaSubir--;
            /*console.log("Termino de Cargar los Archivitos en el Done del fileUpload (los Archivos a montar)");
            console.log("DATA RESULT ");
            console.log(data.result.arhivosSubidos);*/
            if(data.result.arhivosSubidos.length>0){
                if(data.result.arhivosSubidos.length >= 1 && !data.result.flag)
                    $("#transferencias_TAGMsgSoporteMotivo").children(".Nofiles").remove();

                /*console.log("data.result.flag: "+data.result.flag);*/
                //if(!data.result.flag){
                    var imagen_url = "";
                    if(data.result.archivo.indexOf(".pdf")!=-1)
                        imagen_url = "../vbtonline/resources/images/comun/new_pdf.png";
                    else
                        imagen_url = "../vbtonline/resources/images/comun/image.png";
                    var fileName=data.result.archivo;

                    /*console.log("fileName: "+fileName);*/

                    if(fileName.length >25)
                        fileName = fileName.substr(0,25) + "...";
                    $("#transferencias_TAGMsgSoporteMotivo").append(
                        $('<div class="fileupload" id="'+data.result.archivo+'" />')
                            .append($('<div style="float: left;" class="name"/><img border="0" alt=""  src="'+imagen_url+'" height="16"><span title="'+data.result.archivo+'">&nbsp;&nbsp;'+fileName+' -   '+ Math.round( data.files[0].size / 1024 ) +'KB</span>'))
                            .append($('<div  style="float: right;" id="delete-'+data.result.archivo+'" onclick="deleteFile(\''+data.result.archivo+'\',false);"><img border="0" alt=""  src="../vbtonline/resources/images/close2.png" height="10">'))
                    );
                /*}else
                    archivosTotales--;    */
            }
            /*console.log("archivosTotales: "+archivosTotales);
            console.log("data.result.arhivosSubidos: "+data.result.arhivosSubidos);*/
            if(data.result.arhivosSubidos!=null){
                archivosSubidos=data.result.arhivosSubidos;
                progreso(archivosSubidos.length,archivosTotales);
            }
            if(data.result.mensajes!=null)
                popups(data.result.mensajes);

        }
    });

    $("#DateBirthIB").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    $("#DateBirthIB").datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true,yearRange: "-100:+0"});

    $("#TipoPersona").change(function() {
        fieldsetBeneficiaryInformationTransferencia();
    });

    $('#Motivos').change(function(){
        var v_motivos = $('#Motivos option:selected').attr('descripcion');
        var v_soporte =  $('#Motivos option:selected').attr('soporte');

        if(v_motivos == "S" ){
   /*         $("#MotivoAI").css({
                width: "300px" });*/
            $("#RequiredMotivoAI").css({
                display: "inline" });
            $("#MotivoAI").addClass("obligatorioTOB");
        }else {
        /*    $("#MotivoAI").css({
                width: "300px" });*/
            $("#RequiredMotivoAI").css({
                display: "none" });
            $("#MotivoAI").removeClass("obligatorioTOB");
        }

        if(v_soporte == "S" ){
            $("#TABSoporte").css({ display: "" });
            $("#TABSoporte2").css({ display: "" });
            $("#TABSoporte3").css({ display: "" });
            $("#TABSoporte4").css({ display: "" });
/*
            $("#TABSoporte").css({ display: "table-row" });
            $("#TABSoporte2").css({ display: "table-row" });
            $("#TABSoporte3").css({ display: "table-row" });
            $("#TABSoporte4").css({ display: "table-row" });
*/
            if(idioma=="_us_en")
                $("#fileupload").parent().addClass("uploadEng").removeClass("upload");
            else
                $("#fileupload").parent().addClass("upload").removeClass("uploadEng");

            /*$("#fileupload").addClass("obligatorioTOB");*/
        }else {
            $("#TABSoporte").css({ display: "none" });
            $("#TABSoporte2").css({ display: "none" });
            $("#TABSoporte3").css({ display: "none" });
            $("#TABSoporte4").css({ display: "none" });

            /*$("#fileupload").removeClass("obligatorioTOB");*/
        }
    });

    $('#pwdClaveConfirmTransfer_TOB').keyboard({
        stayOpen : false,
        layout   : 'qwerty',
        lockInput: false,
        restrictInput : false, // Prevent keys not in the displayed keyboard from being typed in
        preventPaste : false,  // prevent ctrl-v and right click
        autoAccept : true
    });

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

    $("#transferencias_TAGMonto_2").mouseenter(function(){
        infoParametrosPersonalesPopPupJSONData();
        $("#poppup_parametrosPersonalesTOB").fadeIn("slow");
    });

    $("#transferencias_TAGMonto_2").mouseleave(function(){
        $("#poppup_parametrosPersonalesTOB").fadeOut("fast");
    });

    $("#transferencias_TAGMonto").mouseenter(function(){
        infoParametrosPersonalesPopPupJSONData();
        $("#poppup_parametrosPersonalesCID").fadeIn("slow");
    });

    $("#transferencias_TAGMonto").mouseleave(function(){
        $("#poppup_parametrosPersonalesCID").fadeOut("fast");
    });

    $("#transferencias_TAGMonto_1").mouseenter(function(){
        infoParametrosPersonalesPopPupJSONData();
        $("#poppup_parametrosPersonalesCID").fadeIn("slow");
    });

    $("#transferencias_TAGMonto_1").mouseleave(function(){
        $("#poppup_parametrosPersonalesCID").fadeOut("fast");
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

    $("#tagiTemplate").click(function() {
        $("#tabla_template_consulta_transfer").empty();
        $("#paginacion_buscarBanco_consulta_bancos_template_transfer").empty();
        infoPaginaTemplateConsultaJSONData();

 /*       $("#sign_up_template_transfer").lightbox_me({
            centered: true, onLoad: function () {
                $("#sign_up_template_transfer").find("input:first").focus();
            }
        });

*/
        $("#sign_up_template_transfer").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })

    });

    $("#transMismoDia").click(function (){

        if (idioma == "_ve_es") {
            $("#TOBdisclaimer p").html("<br><p align='center'><table><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer\" >The Transfer to this Product is not immediate.</label></td></tr><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer2\">AQUI VA MENSAJE</label></td></tr></table></p></br>");
            $("#TOBdisclaimer").dialog({
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
                        $("#mismoDiaTOB").hide();
                        $("#transMismoDia").prop('checked',false);
                        mismoDiaTOB="48H"
                        $(this).dialog("close");
                    },
                    "Continue": function () {
                        $("#div_mensajes_error").fadeOut("fast");
                        mismoDiaTOB="MD"
                        $(this).dialog("close");
                    }
                }
            });
        } else {
            $("#TOBdisclaimer p").html("<br><p align='center'><table><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer\" >The Transfer to this Product is not immediate.</label></td></tr><tr><td><label class=\"datos negrita\" id=\"transferFondoDisclaimer2\">AQUI VA MENSAJE</label></td></tr></table></p></br>");
            $("#TOBdisclaimer").dialog({
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
                        $("#mismoDiaTOB").hide();
                        $("#transMismoDia").prop('checked',false);
                        mismoDiaTOB="48H"
                        $(this).dialog("close");
                    },
                    "Continue": function () {
                        $("#div_mensajes_error").fadeOut("fast");
                        mismoDiaTOB="MD"
                        $(this).dialog("close");
                    }
                }
            });
        }

            if ($("#transMismoDia").prop('checked')){
                mismoDiaTOB="MD"
                if ($("#productCancelationTOB").prop('checked')){
                    if ( ($("#Accounts option:selected").get(0).value != "-2") && ($('#CountryBank option:selected').get(0).value != "-2") ){
                        calcularComisionCancelacionTOB( $("#Accounts option:selected").attr("extra2"), $("#Accounts option:selected").attr("extra4"), $("#Accounts option:selected").val().split(" |")[0], mismoDiaTOB, $('#CountryBank option:selected').val());
                    }
                }
              //calcularComisionCancelacionTOB( $("#Accounts option:selected").attr("extra2"), $("#Accounts option:selected").attr("extra4"), $("#Accounts option:selected").val().split(" |")[0], mismoDiaTOB, $('#Country option:selected').val());

            }else{
                mismoDiaTOB="48H"
                if ($("#productCancelationTOB").prop('checked')){
                    if ( ($("#Accounts option:selected").get(0).value != "-2") && ($('#CountryBank option:selected').get(0).value != "-2") ){
                        calcularComisionCancelacionTOB( $("#Accounts option:selected").attr("extra2"), $("#Accounts option:selected").attr("extra4"), $("#Accounts option:selected").val().split(" |")[0], "48H", $('#CountryBank option:selected').val());
                    }
                }
            }

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

        // crearTabla('div_tabla_template_consulta_transfer','tabla_Templates_consulta_transfer',columnas,datosTabla);
        crearTablaV2('tabla_Templates_consulta_transfer',columnas,datosTabla,"",false);

        /*var oTable = $('#tabla_Templates_consulta_transfer').dataTable({
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
        });*/
        var oTable = $('#tabla_Templates_consulta_transfer').dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
            "aoColumns": [
                { "sClass": "" },
                { "sClass": ""},
                {  "sClass": "" },
                { "sClass": "" },
                { "sClass": "" },
                { "sClass": "" },
                { "sClass": "center" },
            ],
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

    //ACCORDEON BENEFICIARY BANK
    $("#btnBeneficiaryBankAccordeonNext").click(function(){
/*
        var mensaje="";
        var invalido="0";

        if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#BankCode2").addClass("error_campo");
            $("#BankCodeSWIFTtext").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            /!*$("#BankCode").removeClass("error_campo");    *!/
            $("#BankCode2").removeClass("error_campo");
            $("#BankCodeSWIFTtext").removeClass("error_campo");
        }


        $("#beneficiaryBankAccordeonBlock .obligatorioTOB").each(function(){


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
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }
                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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

            }else{

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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
            }
        }
*/

/*        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            $("#beneficiaryBankAccordeonBlock").addClass("accordeon__block--active")
            $("#beneficiaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
            $("#beneficiaryAccordeonContent").addClass("accordeon__content--active")
        }*/

        $("#beneficiaryBankAccordeonBlock").addClass("accordeon__block--active")
        $("#beneficiaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#beneficiaryAccordeonContent").addClass("accordeon__content--active")
    });
    $("#btnBeneficiaryBankAccordeonClear").click(function (){
        if(tempateCargado == true){
            return;
        }
        $("#div_mensajes_error").fadeOut("fast");
        $("#beneficiaryBankAccordeonBlock .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });
        $("#beneficiaryBankAccordeonBlock .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#beneficiaryBankAccordeonBlock .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        $("#BankCode").attr("disabled", false);
        $("#BankCode2").attr("readonly", false);
        $("#BankCodeSWIFTtext").attr("readonly", false);
        $("#NameBank").attr("readonly", false);
        $("#AddressLineBank1").attr("readonly", false);
        $("#AddressLineBank2").attr("readonly", false);
        $("#AddressLineBank3").attr("readonly", false);
        $("#CountryBank").attr("disabled", false);

        $('#btnCodBancoBuscar').attr("disabled", false);
        $('#btnCodBancoBuscarSWIFT').attr("disabled", false);
    })

    //ACCORDEON BENEFICIARY
/*
    $("#btnBeneficiaryAccordeonNext").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;

        if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#BankCode2").addClass("error_campo");
            $("#BankCodeSWIFTtext").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            /!*$("#BankCode").removeClass("error_campo");    *!/
            $("#BankCode2").removeClass("error_campo");
            $("#BankCodeSWIFTtext").removeClass("error_campo");
        }

        $("#beneficiaryAccordeonBlock .obligatorioTOB").each(function(){


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

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }


                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        } else {
                            $("#lastname2").removeClass("error_campo");

                        }
                    }

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
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }

                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {

                        mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabeticPunto($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }
            }
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#beneficiaryAccordeonBlock").addClass("accordeon__block--active")
                $("#beneficiaryAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                $("#beneficiaryInformationAccordeonContent").addClass("accordeon__content--active")

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
*/
    $("#btnBeneficiaryAccordeonNext").click(function(){
        var mensaje="";

        $("#beneficiaryAccordeonBlock .obligatorioTOB").each(function(){


            if($("#"+this.id).val()=="-2"){
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
        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        } else{
            $("#beneficiaryAccordeonBlock").addClass("accordeon__block--active")
            $("#beneficiaryAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
            $("#beneficiaryInformationAccordeonContent").addClass("accordeon__content--active")
        }
    });
    $("#btnBeneficiaryAccordeonClear").click(function (){
        if(tempateCargado == true){
            return;
        }
        $("#div_mensajes_error").fadeOut("fast");
        $("#beneficiaryAccordeonBlock .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#beneficiaryAccordeonBlock .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#beneficiaryAccordeonBlock .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        $("#name").attr("readonly", false);
        $("#AccountBank").attr("disabled", false);
        $("#AccountNumber").attr("readonly", false);
        $("#beneficiaryEmail").attr("readonly", false);
        $("#AddressLine1").attr("readonly", false);
        $("#AddressLine2").attr("readonly", false);
        $("#AddressLine3").attr("readonly", false);
        $("#TelephoneNumber").attr("readonly", false);
        $("#Country").attr("disabled", false);

        $("#TipoPersona").attr("disabled",false);
    })

    //ACCORDEON BENEFICIARY INFORMATION
/*
    $("#btnBeneficiaryInformationAccordeonNext").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        var montoAux="";
        var montoOrigen="";

        montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        // esta seleccionado un tipo de codigo

        if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#BankCode2").addClass("error_campo");
            $("#BankCodeSWIFTtext").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            /!*$("#BankCode").removeClass("error_campo");    *!/
            $("#BankCode2").removeClass("error_campo");
            $("#BankCodeSWIFTtext").removeClass("error_campo");
        }

        $("#form_14 .obligatorioTOB").each(function(){


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
                //if( Trim($("#BankCode2").get(0).value) != "" ){
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }
                }
                //}

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

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }


                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        } else {
                            $("#lastname2").removeClass("error_campo");

                        }
                    }
                }

                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/

                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length< 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation Ingles )*!/

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

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }

                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {

                        mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabeticPunto($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }

                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/
                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length < 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation español)*!/
            }
        }

        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#form_14").addClass("accordeon__block--active")
                $("#beneficiaryInformationAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                $("#intermediaryBankAccordeonContent").addClass("accordeon__content--active")
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
*/
    $("#btnBeneficiaryInformationAccordeonNext").click(function(){
        $("#form_14").addClass("accordeon__block--active")
        $("#beneficiaryInformationAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#intermediaryBankAccordeonContent").addClass("accordeon__content--active")
    });
    $("#btnBeneficiaryInformationAccordeonClear").click(function (){
        if(tempateCargado == true){
            return;
        }
        $("#div_mensajes_error").fadeOut("fast");
        $("#form_14 .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#form_14 .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#form_14 .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        $("#FullNameIndividualIB").attr("readonly", false);
        $("#DateBirthIB").attr("disabled", false);
        $("#IdPassportIB").attr("readonly", false);
        $("#NationalityIB").attr("disabled", false);
        $("#FullNameIB").attr("readonly", false);
        $("#CountryIncorporationIB").attr("disabled", false);

        removeFiles();
        validarTransfer="N";

    })

    //ACCORDEON INTERMEDIARY BANK
/*
    $("#btnIntermediaryBankAccordeonNext").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        var montoAux="";
        var montoOrigen="";

        montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        // esta seleccionado un tipo de codigo

        if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#BankCode2").addClass("error_campo");
            $("#BankCodeSWIFTtext").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            /!*$("#BankCode").removeClass("error_campo");    *!/
            $("#BankCode2").removeClass("error_campo");
            $("#BankCodeSWIFTtext").removeClass("error_campo");
        }

        $("#intermediaryBankAccordeonBlock .obligatorioTOB").each(function(){


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
                //if( Trim($("#BankCode2").get(0).value) != "" ){
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }
                }
                //}

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

                    //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /!*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*!/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    //}

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
                    }else{
                        $("#AddressLineBankIB3").removeClass("error_campo");
                    }
                    if (Trim($("#CountryBankIB").get(0).value) == "-2") {
                        mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>";
                        $("#CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    } else{
                        $("#CountryBankIB").removeClass("error_campo");
                    }
                }

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }


                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        } else {
                            $("#lastname2").removeClass("error_campo");

                        }
                    }

                }

                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/
                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length< 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation Ingles )*!/

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

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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


                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /!*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*!/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
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

                    if ($("#CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediario: Indique el Pais del banco intermediario."+"<br>";
                        $("#CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }
                }

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }

                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {

                        mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabeticPunto($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }

                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/
                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length < 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation español)*!/
            }
        }



        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#intermediaryBankAccordeonBlock").addClass("accordeon__block--active")
                $("#intermediaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                $("#futherCreditAccordeonContent").addClass("accordeon__content--active")
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
*/
    $("#btnIntermediaryBankAccordeonNext").click(function(){
        $("#intermediaryBankAccordeonBlock").addClass("accordeon__block--active")
        $("#intermediaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#futherCreditAccordeonContent").addClass("accordeon__content--active")
    });
    $("#btnIntermediaryBankAccordeonSkip").click(function (){
        $("#div_mensajes_error").fadeOut("fast");
        $("#intermediaryBankAccordeonBlock  .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#intermediaryBankAccordeonBlock  .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#intermediaryBankAccordeonBlock  .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        $("#BankCodeIBSWIFTtext").attr("readonly", false);
        $("#BankCodeIB").attr("disabled", false);
        $("#BankCodeIB2").attr("readonly", false);
        $("#NameBankIB").attr("readonly", false);
        $("#AddressLineBankIB1").attr("readonly", false);
        $("#AddressLineBankIB2").attr("readonly", false);
        $("#AddressLineBankIB3").attr("readonly", false);
        $("#CountryBankIB").attr("disabled", false);

        $("#intermediaryBankAccordeonBlock").addClass("accordeon__block--active")
        $("#intermediaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#futherCreditAccordeonContent").addClass("accordeon__content--active")
    })

    //ACCORDEON FUTHER CREDIT
/*
    $("#btnFutherCreditAccordeonNext").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        var montoAux="";
        var montoOrigen="";

        montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        // esta seleccionado un tipo de codigo

        if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#BankCode2").addClass("error_campo");
            $("#BankCodeSWIFTtext").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            /!*$("#BankCode").removeClass("error_campo");    *!/
            $("#BankCode2").removeClass("error_campo");
            $("#BankCodeSWIFTtext").removeClass("error_campo");
        }

        $("#futherCreditAccordeonBlock .obligatorioTOB").each(function(){


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
                //if( Trim($("#BankCode2").get(0).value) != "" ){
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }
                }
                //}

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

                    //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /!*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*!/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    //}

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
                    }else{
                        $("#AddressLineBankIB3").removeClass("error_campo");
                    }
                    if (Trim($("#CountryBankIB").get(0).value) == "-2") {
                        mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>";
                        $("#CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    } else{
                        $("#CountryBankIB").removeClass("error_campo");
                    }
                }

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }


                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        } else {
                            $("#lastname2").removeClass("error_campo");

                        }
                    }

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
                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/

                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length< 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation Ingles )*!/

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

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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


                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /!*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*!/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
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

                    if ($("#CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediario: Indique el Pais del banco intermediario."+"<br>";
                        $("#CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }
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

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }

                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {

                        mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabeticPunto($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }

                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/
                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length < 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation español)*!/
            }
        }

        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#futherCreditAccordeonBlock").addClass("accordeon__block--active")
                $("#futherCreditAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                $("#amountInstructionsAccordeonContent").addClass("accordeon__content--active")
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
*/
    $("#btnFutherCreditAccordeonNext").click(function(){
        $("#futherCreditAccordeonBlock").addClass("accordeon__block--active")
        $("#futherCreditAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#amountInstructionsAccordeonContent").addClass("accordeon__content--active")
    });
    $("#btnFutherCreditAccordeonSkip").click(function (){
        $("#div_mensajes_error").fadeOut("fast");
        $("#futherCreditAccordeonBlock .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#futherCreditAccordeonBlock .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#futherCreditAccordeonBlock .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });

        $("#AccountNumberFFC").attr("readonly", false);
        $("#NameFFC").attr("readonly", false);

        $("#futherCreditAccordeonBlock").addClass("accordeon__block--active")
        $("#futherCreditAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#amountInstructionsAccordeonContent").addClass("accordeon__content--active")

    })

    //ACCORDEON AMMOUNT INSTRUCTIONS
/*
    $("#btnAmountInstructionsAccordeonNext").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        var montoAux="";
        var montoOrigen="";

        montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

        // esta seleccionado un tipo de codigo

        if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
            // campo codigo es vacio, debe llenar el campo codigo
            $("#BankCode2").addClass("error_campo");
            $("#BankCodeSWIFTtext").addClass("error_campo");
            if(idioma=="_us_en")
                mensaje=mensaje+"Required field<br>";
            else
                mensaje=mensaje+"Campo requerido<br>";

        }else{
            //campo codigo es vacio , no hay error
            /!*$("#BankCode").removeClass("error_campo");    *!/
            $("#BankCode2").removeClass("error_campo");
            $("#BankCodeSWIFTtext").removeClass("error_campo");
        }

        $("#amountInstructionsAccordeonBlock .obligatorioTOB").each(function(){


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
                //if( Trim($("#BankCode2").get(0).value) != "" ){
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }
                }
                //}

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

                    //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /!*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*!/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    //}

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
                    }else{
                        $("#AddressLineBankIB3").removeClass("error_campo");
                    }
                    if (Trim($("#CountryBankIB").get(0).value) == "-2") {
                        mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>";
                        $("#CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    } else{
                        $("#CountryBankIB").removeClass("error_campo");
                    }
                }

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }


                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        } else {
                            $("#lastname2").removeClass("error_campo");

                        }
                    }

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
                //if(usuario.tipo!="6"){
                if ($("#tipo_contrato_app").val()!="FC"){
                    if (Number(unFormatCurrency(montoAux,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                        //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                        mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                        $("#AmmountAI").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#productCancelationTOB").prop('checked')){
                        mensaje = mensaje;
                    }else{
                        if (Number(unFormatCurrency(montoAux,',').replace(',','.'))  > Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.')) || Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                            if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.')))
                                mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                            else
                                mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmtd+"."+"<br>";
                            $("#AmmountAI").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                }else{
                    if (Number(unFormatCurrency(montoAux,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                        mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                        $("#AmmountAI").addClass("error_campo");
                        invalido ="1";
                    }

                    if ($("#productCancelationTOB").prop('checked')){
                        mensaje = mensaje;
                    }else{
                        if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                            mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                            $("#AmmountAI").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }

                /!**
                 * Validación del mínimo en cuenta
                 *!/
                montoOrigen = Number(unFormatCurrency($('#Accounts :selected').attr("extra")));

                if ($("#productCancelationTOB").prop('checked')){
                    mensaje = mensaje;
                }else{
                    if((montoOrigen - Number(unFormatCurrency(montoAux,',').replace(',','.')) < parametros.minimun_balance)) {
                        if(idioma=="_us_en"){
                            mensaje = mensaje + "The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                        }
                        else {
                            mensaje = mensaje + "Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                        }
                        $("#AmmountAI").addClass("error_campo");
                        invalido ="1";
                    }
                }



                if (Trim($("#ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#ReceiverInformation").get(0).value)) ) {
                    mensaje = mensaje + " Amount & Instructions: Receiver Information contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#ReceiverInformation").addClass("error_campo");
                    invalido ="1";
                }
                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/

                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length< 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation Ingles )*!/

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

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                    //if (Trim($("#BankCode2").get(0).value) != ""){
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                /!* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*!/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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


                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /!*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*!/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
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

                    if ($("#CountryBankIB").get(0).value == "-2") {
                        mensaje = mensaje + "Intermediario: Indique el Pais del banco intermediario."+"<br>";
                        $("#CountryBankIB").addClass("error_campo");
                        invalido ="1";
                    }
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

                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }

                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {

                        mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabeticPunto($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                }

                if (Number(unFormatCurrency(montoAux,',').replace(',','.')) < Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                    //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) < Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                    mensaje = mensaje + "Monto y Descripci&oacute;n:  Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a "+parametros.ex_mminto+"."+"<br>";
                    $("#AmmountAI").addClass("error_campo");
                    invalido ="1";
                }
                // if(usuario.tipo!="6"){
                if ($("#tipo_contrato_app").val()!="FC"){
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

                /!* Validaciones Campos Nuevos (BeneficiaryInformation)*!/
                if($("#TipoPersona").get(0).value == "NAT"){
                    if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIndividualIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                            $("#FullNameIndividualIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (!isDate($("#DateBirthIB").get(0).value) ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                        $("#DateBirthIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#NationalityIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                        $("#NationalityIB").addClass("error_campo");
                        invalido ="1";
                    }
                    if ($("#IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                        mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                        $("#IdPassportIB").addClass("error_campo");
                        invalido ="1";
                    }
                }else if($("#TipoPersona").get(0).value == "JUR"){
                    if ($("#FullNameIB").get(0).value.length < 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                        mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                        $("#FullNameIB").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                            mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                            $("#FullNameIB").addClass("error_campo");
                            invalido ="1";
                        }
                    }
                    if ($("#CountryIncorporationIB").get(0).value == "-2") {
                        mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                        $("#CountryIncorporationIB").addClass("error_campo");
                        invalido ="1";
                    }
                }
                /!* fin Campos nuevos (beneficiaryInformation español)*!/
            }
        }



        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                $("#amountInstructionsAccordeonBlock").addClass("accordeon__block--active")
                $("#amountInstructionsAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
                $("#purposePaymentAccordeonContent").addClass("accordeon__content--active")
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
*/
    $("#btnAmountInstructionsAccordeonNext").click(function(){
        $("#amountInstructionsAccordeonBlock").addClass("accordeon__block--active")
        $("#amountInstructionsAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
        $("#purposePaymentAccordeonContent").addClass("accordeon__content--active")
    });
    $("#btnAmountInstructionsAccordeonClear").click(function (){
        if(tempateCargado == true){
            return;
        }
        $("#div_mensajes_error").fadeOut("fast");
        $("#amountInstructionsAccordeonBlock .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });

        $("#amountInstructionsAccordeonBlock .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#amountInstructionsAccordeonBlock .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#cancelarProductoMostrarTOB").hide();
        $("#montoComisionMostrar").hide();
        $("#montoComision").html(" ");

    })

    //ACCORDEON PORPUSEPAYMENT
    $("#btnPurposePaymentAccordeonClear").click(function (){
        if(tempateCargado == true){
            return;
        }
        $("#div_mensajes_error").fadeOut("fast");
        $("#purposePaymentAccordeonBlock .error_campo").each(function(){
            $("#"+this.id).removeClass("error_campo");
        });
        $("#purposePaymentAccordeonBlock .selectFormulario_TOB").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#purposePaymentAccordeonBlock .inputFormulario_TOB").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
        $('#Motivos').prop('disabled', false);

        $("#TABSoporte").attr("style","display:none");
        $("#TABSoporte2").attr("style","display:none");
        $("#TABSoporte3").attr("style","display:none");

        removeFiles();
    })

    $("#btn_TOB_aceptar").click(function(){

        console.log("BTN ACEPTAR - A OTROS BANCOS");

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;
        var montoAux="";
        var montoOrigen="";

        montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
        montoAux=(montoAux).replace(/\./g,',');
        montoAux=(montoAux).replace(/t/g,'.');

            // esta seleccionado un tipo de codigo

            if (Trim($("#BankCodeSWIFTtext").get(0).value)== "" && Trim($("#BankCode2").get(0).value)== "" ) {
                // campo codigo es vacio, debe llenar el campo codigo
                $("#BankCode2").addClass("error_campo");
                $("#BankCodeSWIFTtext").addClass("error_campo");
                if(idioma=="_us_en")
                    mensaje=mensaje+"Please complete the required fields in red<br>";
                else
                    mensaje=mensaje+"Por favor complete los campos requeridos en rojo<br>";

            }else{
                //campo codigo es vacio , no hay error
                /*$("#BankCode").removeClass("error_campo");    */
                $("#BankCode2").removeClass("error_campo");  
                $("#BankCodeSWIFTtext").removeClass("error_campo");
            }

   /*      $(".obligatorioTOB").each(function(){


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

                        // console.log($("#"+this.id).parent( "#beneficiaryBankAccordeonBlock" ));
                        // console.log($("#"+this.id).parent( "#beneficiaryAccordeonBlock" ));
                        // console.log($("#"+this.id).parent( "#intermediaryBankAccordeonBlock" ));
                        // console.log($("#"+this.id).parent( "#futherCreditAccordeonBlock" ));
                        // console.log($("#"+this.id).parent( "#amountInstructionsAccordeonBlock" ));
                        // console.log($("#"+this.id).parent( "#purposePaymentAccordeonBlock" ));

        }); */
        /*alert("validaArchivosContrat " + validaArchivosContrato);*/
        if( $('#Motivos option:selected').attr('soporte')=="S" && validacionArchivos=="true" && validaArchivosContrato=="S"){
            if($("#transferencias_TAGMsgSoporteMotivo").children(".Nofiles").html()!=""&&$("#transferencias_TAGMsgSoporteMotivo").children(".Nofiles").html()!=null){
                $(".Nofiles").addClass("error_campo");
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field - Purpose of Payment: Support <br>";
                else
                    mensaje=mensaje+"Campo requerido - Motivo de la Transferencia: Soporte <br>";
            } else{
                $(".Nofiles").removeClass("error_campo");
            }
        }

       if(mensaje==""){
           if(idioma =="_us_en"){
               if( $('#Motivos option:selected').attr('soporte')=="S" && validacionArchivos=="true" && validaArchivosContrato=="S"){
                   if ( archivosSubidos.length==undefined || archivosSubidos.length==0) {
                       mensaje = mensaje + "The  Purposes Support: upload a file at least."+"<br>";
                       $(".Nofiles").addClass("error_campo");
                       invalido ="1";
                   } else{
                       $(".Nofiles").addClass("error_campo");
                   }
               }else
                   $("#transferencias_TAGMsgSoporteMotivo").removeClass("error_campo");

               if( $('#Motivos option:selected').attr('descripcion')=="S"){
                   if ( $("#MotivoAI").get(0).value.length<3 || $("#MotivoAI").get(0).value.length>60) {
                       mensaje = mensaje + "Purpose of Payment: The Purpose of Payment format is invalid. The Purpose of Payment consists\nof a minimum of 3 and a maximum of 60 alphabetic characters."+"<br>";
                       $("#MotivoAI").addClass("error_campo");
                       invalido ="1";
                   } else{
                       $("#MotivoAI").removeClass("error_campo");
                   }
               }else
                   $("#MotivoAI").removeClass("error_campo");

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
               //if( Trim($("#BankCode2").get(0).value) != "" ){
               if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                   if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                       mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                       $("#BankCode2").addClass("error_campo");
                       invalido ="1";
                   }
               }

               //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
              /* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                   mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                   $("#BankCodeSWIFTtext").addClass("error_campo");
                   invalido ="1";
               }else*/

               if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                   if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                       mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                       $("#BankCodeSWIFTtext").addClass("error_campo");
                       invalido ="1";
                   }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                       mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                       $("#BankCodeSWIFTtext").addClass("error_campo");
                       invalido ="1";
                   }
               }
               //}

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

                   //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                   if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                       if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                           mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                   }

                   // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                   /*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                       mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                       $("#BankCodeIB2").addClass("error_campo");
                       invalido ="1";
                   } else*/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                       if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                           mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                           $("#BankCodeIBSWIFTtext").addClass("error_campo");
                           invalido ="1";
                       }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                           mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                           $("#BankCodeIBSWIFTtext").addClass("error_campo");
                           invalido ="1";
                       }
                   }
                   //}

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
                   }else{
                       $("#AddressLineBankIB3").removeClass("error_campo");
                   }
                   if (Trim($("#CountryBankIB").get(0).value) == "-2") {
                       mensaje = mensaje + "Intermediary: Indicate the country of the Intermediary Bank."+"<br>";
                       $("#CountryBankIB").addClass("error_campo");
                       invalido ="1";
                   } else{
                       $("#CountryBankIB").removeClass("error_campo");
                   }
               }

               if($("#TipoPersona").get(0).value=="NAT"){

                   if ($("#lastname1").val().length <2 ) {
                       //mbernott traducir
                       mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                       $("#lastname1").addClass("error_campo");
                       invalido ="1";
                   }else{
                       if (!isAlphabetic($("#lastname1").val())) {
                           mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                           $("#lastname1").addClass("error_campo");
                           invalido ="1";
                       }

                   }


                   if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                       //mbernott traducir
                       mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                       $("#lastname2").addClass("error_campo");
                       invalido ="1";
                   }else{

                       if (!isAlphabetic($("#lastname2").val())) {
                           mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                           $("#lastname2").addClass("error_campo");
                           invalido ="1";
                       } else {
                           $("#lastname2").removeClass("error_campo");

                       }
                   }

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
               //if(usuario.tipo!="6"){
               if ($("#tipo_contrato_app").val()!="FC"){
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                   //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                       mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
                   if ($("#productCancelationTOB").prop('checked')){
                       mensaje = mensaje;
                   }else{
                       if (Number(unFormatCurrency(montoAux,',').replace(',','.'))  > Number(unFormatCurrency(parametros.ex_mmtd,',').replace(',','.')) || Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                           if(Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.')))
                               mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                           else
                               mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmtd+"."+"<br>";
                           $("#AmmountAI").addClass("error_campo");
                           invalido ="1";
                       }
                   }

               }else{
                   if (Number(unFormatCurrency(montoAux,',').replace(',','.')) <  Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                       mensaje = mensaje + "Amount & Instructions:  Invalid amount. Please enter an Amount equal or greater of "+parametros.ex_mminto+"."+"<br>";
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }

                   if ($("#productCancelationTOB").prop('checked')){
                       mensaje = mensaje;
                   }else{
                       if (Number(unFormatCurrency(montoAux,',').replace(',','.')) > Number(unFormatCurrency(parametros.ex_mmto,',').replace(',','.'))) {
                           mensaje = mensaje + "Amount & Instructions: The amount exceeds the maximum of USD "+parametros.ex_mmto+"."+"<br>";
                           $("#AmmountAI").addClass("error_campo");
                           invalido ="1";
                       }
                   }
               }

               /**
                * Validación del mínimo en cuenta
                */
               montoOrigen = Number(unFormatCurrency($('#Accounts :selected').attr("extra")));

               if ($("#productCancelationTOB").prop('checked')){
                   mensaje = mensaje;
               }else{
                   if((montoOrigen - Number(unFormatCurrency(montoAux,',').replace(',','.')) < parametros.minimun_balance)) {
                       if(idioma=="_us_en"){
                           mensaje = mensaje + "The amount exceeds the minimun of USD in account " + formatMoneda(parametros.minimun_balance) + "<br>";
                       }
                       else {
                           mensaje = mensaje + "Monto m&iacute;nimo en cuenta excedido (M&iacute;n. USD " + formatMoneda(parametros.minimun_balance) + ")" + "<br>";
                       }
                       $("#AmmountAI").addClass("error_campo");
                       invalido ="1";
                   }
               }



               if (Trim($("#ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#ReceiverInformation").get(0).value)) ) {
                   mensaje = mensaje + " Amount & Instructions: Receiver Information contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                   $("#ReceiverInformation").addClass("error_campo");
                   invalido ="1";
               }
               /* Validaciones Campos Nuevos (BeneficiaryInformation)*/

               if($("#TipoPersona").get(0).value == "NAT"){
                   if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                       mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                       $("#FullNameIndividualIB").addClass("error_campo");
                       invalido ="1";
                   }else{
                       if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                           mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                           $("#FullNameIndividualIB").addClass("error_campo");
                           invalido ="1";
                       }
                   }

                   if (!isDate($("#DateBirthIB").get(0).value) ) {
                       mensaje = mensaje + "Beneficiary Information: The format for Date of Birth is invalid."+"<br>"
                       $("#DateBirthIB").addClass("error_campo");
                       invalido ="1";
                   }
                   if ($("#NationalityIB").get(0).value == "-2") {
                       mensaje = mensaje + "Beneficiary Information: Indicate the nationality."+"<br>"
                       $("#NationalityIB").addClass("error_campo");
                       invalido ="1";
                   }
                   if ($("#IdPassportIB").get(0).value.length < 3 || $("#IdPassportIB").get(0).value.length > 20 ) {
                       mensaje = mensaje + "Beneficiary Information: The Id or PassportNr. format is invalid.The Id or Passport Nr. consists\nof a minimum of 3 and a maximum of 20 alphanumeric characters."+"<br>"
                       $("#IdPassportIB").addClass("error_campo");
                       invalido ="1";
                   }
               }else if($("#TipoPersona").get(0).value == "JUR"){
                   if ($("#FullNameIB").get(0).value.length< 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                       mensaje = mensaje + "Beneficiary Information: The Beneficiary Full Name consists\nof a minimum of 3 and a maximum of 50 alphanumeric characters."+"<br>"
                       $("#FullNameIB").addClass("error_campo");
                       invalido ="1";
                   }else{
                       if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                           mensaje = mensaje + "Beneficiary Information: The entity Full Name contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>"
                           $("#FullNameIB").addClass("error_campo");
                           invalido ="1";
                       }
                   }
                   if ($("#CountryIncorporationIB").get(0).value == "-2") {
                       mensaje = mensaje + "Beneficiary Information: Indicate entity's country of incorporation."+"<br>"
                       $("#CountryIncorporationIB").addClass("error_campo");
                       invalido ="1";
                   }
               }
               /* fin Campos nuevos (beneficiaryInformation Ingles )*/

           }else{
               if( $('#Motivos option:selected').attr('soporte')=="S" && validacionArchivos=="true" && validaArchivosContrato=="S"){
                   if ( archivosSubidos.length==undefined || archivosSubidos.length==0) {
                       mensaje = mensaje + "Soportes de Motivos: subir almenos un archivo."+"<br>";
                       $(".Nofiles").addClass("error_campo");
                       invalido ="1";
                   } else{
                       $(".Nofiles").removeClass("error_campo");
                   }
               }else
                   $("#transferencias_TAGMsgSoporteMotivo").removeClass("error_campo");

               if( $('#Motivos option:selected').attr('descripcion')=="S" ){
                   if ( $("#MotivoAI").get(0).value.length < 3 || $("#MotivoAI").get(0).value.length > 60) {
                       mensaje = mensaje + "Motivo de la Transferencia: Formato de Motivo Invalido. El motivo de la transferencia consiste\nde un minimo de 3 y un maximo de 60 caracteres alfabeticos."+"<br>";
                       $("#MotivoAI").addClass("error_campo");
                       invalido ="1";
                   } else{
                       $("#MotivoAI").removeClass("error_campo");
                   }
               }else
                   $("#MotivoAI").removeClass("error_campo");

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

               if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US" ) {
                //if (Trim($("#BankCode2").get(0).value) != ""){
                   if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                           mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                           $("#BankCode2").addClass("error_campo");
                           invalido ="1";
                       }
                }

               /* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                    mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                    $("#BankCodeSWIFTtext").addClass("error_campo");
                    invalido ="1";
                }else*/

               if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                   if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                       mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                       $("#BankCodeSWIFTtext").addClass("error_campo");
                       invalido ="1";
                   }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                       mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                       $("#BankCodeSWIFTtext").addClass("error_campo");
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


                   if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                   //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                       if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                           mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                           $("#BankCodeIB2").addClass("error_campo");
                           invalido ="1";
                       }
                   }

                   // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                   /*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                       mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                       $("#BankCodeIB2").addClass("error_campo");
                       invalido ="1";
                   } else*/
                   if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                       if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                           mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                           $("#BankCodeIBSWIFTtext").addClass("error_campo");
                           invalido ="1";
                       }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                           mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                           $("#BankCodeIBSWIFTtext").addClass("error_campo");
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

                   if ($("#CountryBankIB").get(0).value == "-2") {
                       mensaje = mensaje + "Intermediario: Indique el Pais del banco intermediario."+"<br>";
                       $("#CountryBankIB").addClass("error_campo");
                       invalido ="1";
                   }
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

               if($("#TipoPersona").get(0).value=="NAT"){

                   if ($("#lastname1").val().length <2 ) {
                       //mbernott traducir
                       mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                       $("#lastname1").addClass("error_campo");
                       invalido ="1";
                   }else{
                       if (!isAlphabetic($("#lastname1").val())) {
                           mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                           $("#lastname1").addClass("error_campo");
                           invalido ="1";
                       }

                   }

                   if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {

                       mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                       $("#lastname2").addClass("error_campo");
                       invalido ="1";
                   }else{

                       if (!isAlphabeticPunto($("#lastname2").val())) {
                           mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                           $("#lastname2").addClass("error_campo");
                           invalido ="1";
                       }
                   }
               }

               if (Number(unFormatCurrency(montoAux,',').replace(',','.')) < Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
               //if (Number(unFormatCurrency($("#AmmountAI").get(0).value,',').replace(',','.')) < Number(unFormatCurrency(parametros.ex_mminto,',').replace(',','.'))) {
                   mensaje = mensaje + "Monto y Descripci&oacute;n:  Monto inv&aacute;lido. Por favor introduzca un valor mayor o igual a "+parametros.ex_mminto+"."+"<br>";
                   $("#AmmountAI").addClass("error_campo");
                   invalido ="1";
               }
              // if(usuario.tipo!="6"){
               if ($("#tipo_contrato_app").val()!="FC"){
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

               /* Validaciones Campos Nuevos (BeneficiaryInformation)*/
               if($("#TipoPersona").get(0).value == "NAT"){
                   if ($("#FullNameIndividualIB").val().length< 3 && $("#FullNameIndividualIB").val().length > 50 ) {
                       mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                       $("#FullNameIndividualIB").addClass("error_campo");
                       invalido ="1";
                   }else{
                       if (!isAlphanumericWithSpace($("#FullNameIndividualIB").get(0).value) ) {
                           mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo del Beneficiario tiene caracteres inv&aacute;lidos \n (Ejemplo: % # & @ * & _ : ñ entre otros)."+"<br>"
                           $("#FullNameIndividualIB").addClass("error_campo");
                           invalido ="1";
                       }
                   }

                   if (!isDate($("#DateBirthIB").get(0).value) ) {
                       mensaje = mensaje + "Informacion Beneficiario: El Formato de la Fecha de Nacimiento es invalido.\n Formato Valido : 'DD/MM/YYYY'"+"<br>"
                       $("#DateBirthIB").addClass("error_campo");
                       invalido ="1";
                   }
                   if ($("#NationalityIB").get(0).value == "-2") {
                       mensaje = mensaje + "Informacion Beneficiario: Indicar la Nacionalidad del Beneficiario."+"<br>"
                       $("#NationalityIB").addClass("error_campo");
                       invalido ="1";
                   }
                   if ($("#IdPassportIB").get(0).value.length < 3 || $("#Template_IdPassportIB").get(0).value.length > 20 ) {
                       mensaje = mensaje + "Informacion Beneficiario: El Id o Pasaporte Nr. consiste de un mínimo de 3 y un máximo de 20 caracteres alfanuméricos."+"<br>"
                       $("#IdPassportIB").addClass("error_campo");
                       invalido ="1";
                   }
               }else if($("#TipoPersona").get(0).value == "JUR"){
                   if ($("#FullNameIB").get(0).value.length < 3 && $("#FullNameIB").get(0).value.length > 50 ) {
                       mensaje = mensaje + "Informacion del Beneficiario: El Nombre Completo de la entidad consiste de un mínimo de 3 y un máximo de 50 caracteres alfanuméricos."+"<br>"
                       $("#FullNameIB").addClass("error_campo");
                       invalido ="1";
                   }else{
                       if (!isAlphanumericWithSpace($("#FullNameIB").get(0).value) ) {
                           mensaje = mensaje + "Informacion Beneficiario: El Nombre Completo de la entidad tiene caracteres inv&aacute;lidos (Example: % # & @ * & _ : ñ among others)."+"<br>"
                           $("#FullNameIB").addClass("error_campo");
                           invalido ="1";
                       }
                   }
                   if ($("#CountryIncorporationIB").get(0).value == "-2") {
                       mensaje = mensaje + "Informacion Beneficiario: Indique el país de incorporación de la entidad."+"<br>"
                       $("#CountryIncorporationIB").addClass("error_campo");
                       invalido ="1";
                   }
               }
               /* fin Campos nuevos (beneficiaryInformation español)*/
           }
       }



        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
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

                $("#purposePaymentAccordeonBlock").addClass("accordeon__block--active")
                $("#purposePaymentAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");

                if ($("#TipoPersona").get(0).value=="NAT" && $("#lastname2").val().length==0 ) {
                    eliminarFile(true);
                    if(idioma=="_us_en") {
                        $( "#dialog-confirm p").html("Due to compliance with the Regulations Related to Risk Management Related to Anti Money Laundering and in particular with reference to the Know Your Customer Policy and Due Diligence standards, we ask you to complete the required information in the appropriate fields. In case any of the fields is not properly completed, the ordering Client assumes the total, absolute and full responsibility for the charges and delays that may be generated by the returns executed by correspondent banks, intermediaries and / or final beneficiaries, on the operations so ordered.  <br><p align='center'><b>(1/2)</b></p>" );
                        $( "#dialog-confirm" ).dialog({
                            resizable: false,
                            draggable: false,
                            height: "auto",
                            width: 600,
                            // modal: true,
                            closeOnEscape: false,
                            position: {
                                // my: "center",
                                // at: "center",
                                // of: $('#marco_trabajo')
                                // of: $('#Motivos')
                            },
                            buttons: {
                                Continue: function() {
                                    /*validateBeneficiaryBankJSONData()*/
                                    mostrarDisclaimer2("(2/2)");
                                    $( this ).dialog( "close" );
                                }
                            }
                        });

                    } else {
                        $( "#dialog-confirm p").html("Trabajando en cumplimiento a las Normas Relativas a la Administración de Riesgos Relacionados con Legitimación de Capitales y muy particularmente en referencia a la Política Conozca a su Cliente y los estándares de Debida Diligencia, solicitamos de ustedes se complete la información requerida en los campos correspondientes. En caso de que alguno de los campos no sea debidamente completado, El Cliente ordenante, asume la total, absoluta y plena responsabilidad por los cargos y demoras que pudieran generar las devoluciones que ejecuten los bancos corresponsales, intermediarios y/o beneficiarios finales, sobre las operaciones así ordenadas.  <br><p align='center'><b>(1/2)</b></p>"  );
                        $( "#dialog-confirm" ).dialog({
                            resizable: false,
                            draggable: false,
                            height: "auto",
                            width: 600,
                           // modal: true,
                            closeOnEscape: false,
                            position: {
                                // my: "center",
                                // at: "center",
                                // of: $('#marco_trabajo')
                                // of: $('#Motivos')
                            },
                            buttons: {
                                Continuar: function() {
                                    /*validateBeneficiaryBankJSONData()*/
                                    mostrarDisclaimer2("(2/2)");
                                    $( this ).dialog( "close" );
                                }
                            }
                        });

                    }
                  // alert("Trabajando en cumplimiento a las Normas Relativas a la Administración de Riesgos Relacionados con Legitimación de Capitales y muy particularmente en referencia a la Política Conozca a su Cliente y los estándares de Debida Diligencia, solicitamos de ustedes se complete la información requerida en los campos correspondientes. En caso de que alguno de los campos no sea debidamente completado, El Cliente ordenante, asume la total, absoluta y plena responsabilidad por los cargos y demoras que pudieran generar las devoluciones que ejecuten los bancos corresponsales, intermediarios y/o beneficiarios finales, sobre las operaciones así ordenadas.");
                } else {
                    eliminarFile(true);
                    mostrarDisclaimer2("");
                }
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

    $("#btn_TemplateGuardar_TOB_close").click(function(){
        $("#poppup_saveTemplate").fadeOut("fast");
        seleccionarOpcion("home");
    });

    $("#btn_TemplateGuardar_TOB_inicio").click(function(){

        var mensaje="";
        var invalido="0";//0 es valido
        var auxIban=false;


        $("#poppup_saveTemplate").fadeOut("fast");
        if (Trim($("#BankCode2").get(0).value)== "") {
            $("#BankCode").removeClass("obligatorioTOBTMP");
        }else{
            $("#BankCode").addClass("obligatorioTOBTMP");
        }


        if (Trim($("#BankCodeIB2").get(0).value)== "") {
            $("#BankCodeIB").removeClass("obligatorioTOBTMP");
        }else{
            $("#BankCodeIB").addClass("obligatorioTOBTMP");
        }

        $(".obligatorioTOBTMP").each(function(){


            if (this.id!="Accounts"){
                if (this.id!="AmmountAI"){
                    if (this.id!="MotivoAI"){
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
                    }
                }
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

                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9 || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Beneficiary Bank: The ABA format for the Beneficiary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters." + "<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido = "1";
                    }
                }

                //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                if (Trim($("#BankCodeSWIFTtext").get(0).value) != "") {
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Beneficiary Bank: The SWIFT format for the Beneficiary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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

                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && Trim($("#CountryBankIB").get(0).value) == "US") {
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediary: The ABA format for the Intermediary Bank is invalid. \nRemember that the ABA (or Routing Transit Number) consists of 9 numeric characters."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value) == "") {
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }
                        if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediary: The SWIFT format for the Intermediary Bank is invalid. \nRemember that the SWIFT (or BIC code) consists of 8 or 11 alphanumeric characters."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
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

                if (Trim($("#ReceiverInformation").get(0).value)!= "" && !isAlphanumericWithSpace(Trim($("#ReceiverInformation").get(0).value)) ) {
                    mensaje = mensaje + " Amount & Instructions: Receiver Information contains invalid characters (Example: % # & @ * & _ : ñ among others)."+"<br>";
                    $("#ReceiverInformation").addClass("error_campo");
                    invalido ="1";
                }


                if($("#TipoPersona").get(0).value=="NAT"){

                    if ($("#lastname1").val().length <2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid First surname"+"<br>";
                        $("#lastname1").addClass("error_campo");
                        invalido ="1";
                    }else{
                        if (!isAlphabetic($("#lastname1").val())) {
                            mensaje = mensaje + "Beneficiary: The First surname contains invalid characters."+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }

                    }


                    if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                        //mbernott traducir
                        mensaje = mensaje + "Beneficiary: Indicate a valid Second surname"+"<br>";
                        $("#lastname2").addClass("error_campo");
                        invalido ="1";
                    }else{

                        if (!isAlphabetic($("#lastname2").val())) {
                            mensaje = mensaje + "Beneficiary: The Second surname contains invalid characters."+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }
                    }
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

                //if( trim($("#BankCode2").get(0).value) != "" ){
                if (Trim($("#BankCode").get(0).value) == "ABA" && $("#CountryBank").get(0).value == "US") {
                    if ($("#BankCode2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCode2").get(0).value))) {
                        mensaje = mensaje + "Banco Beneficiario : Formato de ABA del banco beneficiario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                        $("#BankCode2").addClass("error_campo");
                        invalido ="1";
                    }
                }

                //if (Trim($("#BankCode").get(0).value) == "SWIFT") {
                /* if (Trim($("#BankCodeSWIFTtext").get(0).value)=="") {
                     mensaje = mensaje + "Beneficiary Bank: Please provide at least one code of the beneficiary bank."+"<br>";
                     $("#BankCodeSWIFTtext").addClass("error_campo");
                     invalido ="1";
                 }else*/

                if (Trim($("#BankCodeSWIFTtext").get(0).value)!=""){
                    if ($("#BankCodeSWIFTtext").get(0).value.length != 8 && $("#BankCodeSWIFTtext").get(0).value.length != 11 ) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
                        invalido ="1";
                    }else if (!isAlphabetic($("#BankCodeSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeSWIFTtext").get(0).value.substring(6, $("#BankCodeSWIFTtext").get(0).value.length))) {
                        mensaje = mensaje + "Banco Beneficiario: Formato de SWIFT de banco beneficiario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                        $("#BankCodeSWIFTtext").addClass("error_campo");
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

                    //if (Trim($("#BankCodeIB2").get(0).value) != ""){
                    if (Trim($("#BankCodeIB").get(0).value) == "ABA" && $("#CountryBankIB").get(0).value != "US" ) {
                        if ($("#BankCodeIB2").get(0).value.length != 9  || !esSoloNumeros(Trim($("#BankCodeIB2").get(0).value))) {
                            mensaje = mensaje + "Intermediario: Formato de ABA del banco intermediario inv&aacute;lido.\nRecuerde que el ABA (&oacute; Routing Transit Number) debe tener 9 caracteres n&uacute;mericos."+"<br>";
                            $("#BankCodeIB2").addClass("error_campo");
                            invalido ="1";
                        }
                    }

                    // if (Trim($("#BankCodeIB").get(0).value) == "SWIFT") {
                    /*if (Trim($("#BankCodeIBSWIFTtext").get(0).value)== ""){
                        mensaje = mensaje + "Intermediary: Indicate the code of the Intermediary Bank."+"<br>";
                        $("#BankCodeIB2").addClass("error_campo");
                        invalido ="1";
                    } else*/
                    if (Trim($("#BankCodeIBSWIFTtext").get(0).value)!= ""){
                        if ($("#BankCodeIBSWIFTtext").get(0).value.length != 8 && $("#BankCodeIBSWIFTtext").get(0).value.length != 11 ) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
                            invalido ="1";
                        }else if (!isAlphabetic($("#BankCodeIBSWIFTtext").get(0).value.substring(0, 6)) || !esSoloLetrasNumeros($("#BankCodeIBSWIFTtext").get(0).value.substring(6, $("#BankCodeIBSWIFTtext").get(0).value.length))) {
                            mensaje = mensaje + "Intermediario: Formato de SWIFT de banco intermediario inv&aacute;lido.\nRecuerde que el SWIFT (&oacute; BIC code) debe tener 8 &oacute; 11 caracteres alfanum&eacute;ricos."+"<br>";
                            $("#BankCodeIBSWIFTtext").addClass("error_campo");
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

                    if($("#TipoPersona").get(0).value=="NAT"){

                        if ($("#lastname1").val().length <2 ) {
                            //mbernott traducir
                            mensaje = mensaje + "Beneficiario: Indicar un primer apellido válido"+"<br>";
                            $("#lastname1").addClass("error_campo");
                            invalido ="1";
                        }else{
                            if (!isAlphabetic($("#lastname1").val())) {
                                mensaje = mensaje + "Beneficiario: El primer apellido contiene caracteres no válidos."+"<br>";
                                $("#lastname1").addClass("error_campo");
                                invalido ="1";
                            }

                        }

                        if ($("#lastname2").val().length> 0 && $("#lastname2").val().length < 2 ) {
                            //mbernott traducir
                            mensaje = mensaje + "Beneficiario: Indicar un segundo apellido válido"+"<br>";
                            $("#lastname2").addClass("error_campo");
                            invalido ="1";
                        }else{

                            if (!isAlphabetic($("#lastname2").val())) {
                                mensaje = mensaje + "Beneficiario: El segundo apellido contiene caracteres no válidos"+"<br>";
                                $("#lastname2").addClass("error_campo");
                                invalido ="1";
                            } else {
                                    $("#lastname2").removeClass("error_campo");

                            }
                        }
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

            $("#Template_nombreTemplate").attr("disabled",false);

            if(isMail($("#beneficiaryEmail").get(0).value)){
                if($("#beneficiaryEmail").hasClass("error_campo"))
                    $("#beneficiaryEmail").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
               /* $("#summaryToOtherBank #clave_TOB").attr("style","display: none");
                $("#summaryToOtherBank #tituloResumen_TOB").attr("style","display: block");
                $("#summaryToOtherBank #resumenBotones_TOB").fadeIn("fast");
                $("#summaryToOtherBank #resumenBotones_TOB_Finales").attr("style","display: none");
                $("#summaryToOtherBank #tituloExitoso_TOB").attr("style","display: none");
                TransfersValidateJSONData();   */
                tmpTranfers="OK";
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
                cargarSalvarTemplateTransferInicio();
                $("#Template_btn_TOB_borrar").attr("style","display: none");



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

        $("#Template_nombreTemplate").attr("disabled",false);

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

            $("#Template_FullNameIndividualIB").val($("#FullNameIndividualIB").val());
            $("#Template_DateBirthIB").val($("#DateBirthIB").val());
            $("#Template_IdPassportIB").val($("#IdPassportIB").val());
            $("#Template_NationalityIB").val($("#NationalityIB").val());

            $("#Template_FullNameIB").val($("#FullNameIB").val());
            $("#Template_CountryIncorporationIB").val($("#CountryIncorporationIB").val());


            $("#div_TRANSFERENCIA_EXTERNA").removeClass("opcion_seleccionada");
            $("#div_DIRECTORIO").addClass("opcion_seleccionada");
            $("#div_TRANSFERENCIA_EXTERNA").fadeOut("fast");
            $("#div_DIRECTORIO").fadeIn("slow");



            $("#div_mensajes_error").fadeOut("fast");

            seleccionarTemplateEditar(idTemplate);
            fieldsetBeneficiaryInformationTemplate();
        }else{
            $("#div_TRANSFERENCIA_EXTERNA").fadeOut("fast");
            $("#div_home").fadeIn("slow");
        }
    });

    $("#btn__resumen_aceptar").click(function(){
        eliminarFile(false);
        $("#summaryToOtherBank").attr("style","display: block");
        $("#pwdClaveConfirmTransfer_TOB").val("");
        $("#summaryToOtherBank #tituloResumen_TOB").attr("style","display: none");
        $("#summaryToOtherBank #resumenBotones_TOB").attr("style","display: none");
        $("#btn_aceptarClave").attr("disabled",false);
        $("#cancelarProductoMostrarTOB").hide();
        $("#montoComisionMostrar").hide();
        $("#montoComision").html(" ");
        $("#productCancelationTOB").prop('checked', true);
        $('#Motivos').prop('disabled', false);
        if($('#Motivos option:selected').attr('soporte')=="N")
            removeFiles();
        if ($("#tipo_contrato_app").val()=="FC"){
            validarTransfer="S";
        }
        if (validarTransfer!="S"){
            //Se llama a la pantalla de metodos de validacion
            mainValidationMethods("transferenciasOtrosBancos");
        }else{
            origenMetodosValidacion="transferenciasOtrosBancos";
                callValidationMethodsSuccess();
        }
    });

    $("#btn_cancelarClave").click(function(){
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
        clearAccordeons();
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
        $("#cancelarProductoMostrarTOB").hide();
        $("#montoComisionMostrar").hide();
        $("#montoComision").html(" ");
        $('#Motivos').prop('disabled', false);
        $("div[name='transferencias_apellidos']").addClass("oculto");

        $("#BankCode").attr("disabled", false);
        $("#BankCode2").attr("readonly", false);
        $("#BankCodeSWIFTtext").attr("readonly", false);
        $("#BankCodeIBSWIFTtext").attr("readonly", false);
        $("#NameBank").attr("readonly", false);
        $("#AddressLineBank1").attr("readonly", false);
        $("#AddressLineBank2").attr("readonly", false);
        $("#AddressLineBank3").attr("readonly", false);
        $("#CountryBank").attr("disabled", false);

        $('#btnCodBancoBuscar').attr("disabled", false);
        $('#btnCodBancoBuscarSWIFT').attr("disabled", false);
        $('#btnCodBancoBuscarIB').attr("disabled", false);
        $('#btnCodBancoBuscarIBSWIFT').attr("disabled", false);

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

        $("#FullNameIndividualIB").attr("readonly", false);
        $("#DateBirthIB").attr("disabled", false);
        $("#IdPassportIB").attr("readonly", false);
        $("#NationalityIB").attr("disabled", false);
        $("#FullNameIB").attr("readonly", false);
        $("#CountryIncorporationIB").attr("disabled", false);

        $("#TipoPersona").attr("disabled",false);
        $("#form_14").attr("style","display:none;");

        $("#TABSoporte").attr("style","display:none");
        $("#TABSoporte2").attr("style","display:none");
        $("#TABSoporte3").attr("style","display:none");

        removeFiles();
        validarTransfer="N";
    });

    $("#btn__resumen_cancelar").click(function(){

       
        eliminarFile(false);
        $("#createToOtherBank").fadeIn("fast");
        $("#summaryToOtherBank").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");
        //$("#marco_trabajo").css("height","1200px");

        setTimeout(() => {
            console.log('btn__resumen_cancelar');
            $('#createToOtherBank').scrollTop();
            var element = document.querySelector("#createToOtherBank");
            // scroll to element
            element.scrollIntoView();
        }, 1000);

    });

    $("#btn_TemplateGuardar_TOB_cancel").click(function(){
        tempateCargado = false;
        removeFiles();
     if (origenTemplate!="TMP"){
        $("#poppup_saveTemplate").fadeIn("slow");
     }else{
         seleccionarOpcion("home");
     }
    });

    $("#btn_finalizar_TOB_final").click(function(){
        $("#btn_TOB_cancelar").click();
        $("#createToOtherBank").attr("style","display: block");
        $("#summaryToOtherBank").attr("style","display: none");
        $("#mensaje_error").empty();
        $("#div_mensajes_error").attr("style","display: none");
        validarTransfer="";
        infoPaginaJSONData();

    });

    $("#btn_ok_TOB_final").click(function(){
        removeFiles();
        seleccionarOpcion("home");
    });

    $("#homeTOB").click(function(){
        removeFiles();
        seleccionarOpcionMenu("home");

    });
    /**
     * Print html TOB
     */
    $("#imprimirCampos_TOBFI").click(function(){

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

        // printPageElement('div_TRANSFERENCIA_EXTERNA');
        $("#createToOtherBank").printThis({
            importCSS: true,            // import parent page css
            importStyle: true,         // import style tags
            printContainer: true,
        });
    });

    $("#imprimirCampos_TOBFC").click(function(){

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
        transferFinalAprobada="OK";
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
        $("#Template_nombreTemplate").attr("disabled",false);
        newTemplateJSONDataTemplate();
        cargarSalvarTemplateTransferSuccess();
        $("#Template_btn_TOB_borrar").attr("style","display: none");
        $("#Template_nombreTemplate").attr("disabled",false);

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

    $("#coordenada_2").focus(function(){
        limpiarTarjeta();
        pintarCoordenadas_2("#195266");
    });

});

function transferenciaMismoDia(){
    var url = urlVerificarDiaTOB;
    var param={};

    sendServiceJSON(url,param,transferenciaMismoDiaSuccess,null,null);
}

function transferenciaMismoDiaSuccess(originalRequest){
    var result = originalRequest;
    var mismoDia="";
    var comprobar = result.verfDia;

    if (comprobar=="1"){
        mismoDia="S";
    }else{
        mismoDia="N";
    }
    if ($("#tipo_contrato_app").val() == "FC"){
        $("#mismoDiaTOB").hide();
    }else{
        if (mismoDia=="S"){
            $("#mismoDiaTOB").show();

        }else{
            $("#mismoDiaTOB").hide();
        }
    }

}

function ParametrosPersonalesCamposTOBJSONData(){
    $("#div_carga_espera").removeClass("oculto");

    var url = urlParametrosPersonalesTOB;
    var param={};

    sendServiceJSON(url,param,ParametrosPersonalesTOBCamposSuccess,null,null);
}

function ParametrosPersonalesTOBCamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;
    parametrosBase = result.parametrosPersonalesBase;
    $("#div_carga_espera").addClass("oculto");
    if(parametros == null || parametros == ""){
        parametros = parametrosBase;
    }

}

function cargarInfoTemplateJSONData(id){
    $("#div_carga_espera").removeClass("oculto")
    var url = urlDirectorioCargarTemplate;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= id;
    idTemplate=id;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON(url,param,cargarInfoTemplateSuccess,null,null);
}

function cargarInfoTemplateSuccess(originalRequest){
    tempateCargado = true;
    //                   this is the json return data
    var result = originalRequest;
    var datos = result.datos;
    var enc=false;
    idioma = result.idioma;
    $("#div_carga_espera").addClass("oculto")
    popupTmp="OK";

    if ($("#tipo_contrato_app").val() == "FC"){
        $("#cancelarProductoMostrarTOB").hide();
    }else {
        if ($("#Accounts option:selected").attr("extra6") <= "0") {
            $("#cancelarProductoMostrarTOB").show();
        } else {
            $("#cancelarProductoMostrarTOB").hide();
        }
    }

    $("#createToOtherBank .focus_selecionado").each(function(){
        $("#Accounts").removeClass("selectFormulario_TOB");
        $("#Accounts").removeClass("focus_selecionado");
        enc=true;
    });

    $("#createToOtherBank .selectFormulario_TOB").each(function(){
        if(!$("#"+this.id).selector == "#Motivos"){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        }
    });

    $("#createToOtherBank .inputFormulario_TOB").each(function(){
        if(!$("#"+this.id).selector == "#MotivoAI"){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        }
    });

    if (enc){
        $("#Accounts").addClass("selectFormulario_TOB");
    }

    $("#createToOtherBank").fadeIn("fast");
    $("#summaryToOtherBank").fadeOut("fast");
    $("#resultToOtherBank").attr("style","display: none");

    $("#BankCodeSWIFTtext").val(datos.beneficiaryBankCodeNumberSwift);

    if (datos.beneficiaryBankCodeType!="")
        $("#BankCode").val(datos.beneficiaryBankCodeType);
    else
        $("#BankCode").val("-2")

    $("#BankCode2").val(datos.beneficiaryBankCodeNumber);

    $("#NameBank").val(datos.beneficiaryBankName);
    $("#AddressLineBank1").val(datos.beneficiaryBankAddress1);
    $("#AddressLineBank2").val(datos.beneficiaryBankAddress2);
    $("#AddressLineBank3").val(datos.beneficiaryBankCity);
    $("#CountryBank").val(datos.beneficiaryBankCountryCode);

    $("#name").val(datos.beneficiaryName);
    $("#AccountBank").val(datos.accountCode);
    $("#AccountNumber").val(datos.account);
    $("#beneficiaryEmail").val(datos.beneficiaryEmail);
    $("#AddressLine1").val(datos.beneficiaryAddress1);
    $("#AddressLine2").val(datos.beneficiaryAddress2);
    $("#AddressLine3").val(datos.beneficiaryCity);
    $("#AddressLine3").val(datos.beneficiaryCity);
    $("#BeneficiaryPostalCode").val(datos.beneficiaryPostalCode);
    $("#Country").val(datos.beneficiaryCountryCode);
    if (datos.beneficiaryTypePerson==""){
        $("#TipoPersona").val("-2");
        $("div[name='transferencias_apellidos']").addClass("oculto");
    }else{

        if (datos.beneficiaryTypePerson==="NAT"){
            $("#FullNameIndividualIB").val(datos.beneficiaryFullName);
            $("#DateBirthIB").val(datos.beneficiaryBirthDate);
            $("#NationalityIB").val(datos.beneficiaryNationality);
            $("#IdPassportIB").val(datos.beneficiaryIdPassport);

            $("div[name='transferencias_apellidos']").removeClass("oculto");
        }else if (datos.beneficiaryTypePerson==="JUR"){
            $("#FullNameIB").val(datos.beneficiaryFullName);
            $("#CountryIncorporationIB").val(datos.beneCountryIncorporation);
            $("div[name='transferencias_apellidos']").addClass("oculto");
        }

    }

    $("#TipoPersona").val(datos.beneficiaryTypePerson);
    $("#lastname1").val(datos.beneficiaryLastName1);
    $("#lastname2").val(datos.beneficiaryLastName2);



    // Deshabilitar

    $("#BankCode").attr("disabled", true);
    $("#BankCode2").attr("readonly", true);

    if(!$("#BankCodeSWIFTtext").get(0).value.isEmpty())
        $("#BankCodeSWIFTtext").attr("readonly", true);
    else
        $("#BankCodeSWIFTtext").attr("readonly", false);

    //$("#BankCodeIBSWIFTtext").attr("readonly", true);

    $("#NameBank").attr("readonly", true);
    $("#AddressLineBank1").attr("readonly", true);
    $("#AddressLineBank2").attr("readonly", true);
   // $("#AddressLineBank3").attr("readonly", true);
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
    //$("#AddressLineBankIB3").attr("readonly", true);
    $("#AccountNumberFFC").attr("readonly", true);
    $("#NameFFC").attr("readonly", true);
    $("#CountryBankIB").attr("disabled", true);

    if(datos.beneficiaryTypePerson!=""){
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

    if (datos.beneficiaryCity!=""){
        $("#AddressLine3").attr("readonly", true);
        $("#BeneficiaryPostalCode").attr("readonly", true);
    }else{
        $("#AddressLine3").attr("readonly", false);
        $("#BeneficiaryPostalCode").attr("readonly", false);
    }

    if (datos.beneficiaryTypePerson=="NAT"){
        (datos.beneficiaryFullName=="") ? $("#FullNameIndividualIB").attr("readonly", false):$("#FullNameIndividualIB").attr("readonly", true);
        (datos.beneficiaryBirthDate=="") ? $("#DateBirthIB").attr("disabled", false):$("#DateBirthIB").attr("disabled", true);
        (datos.beneficiaryNationality=="") ? $("#NationalityIB").attr("disabled", false):$("#NationalityIB").attr("disabled", true);
        (datos.beneficiaryIdPassport=="") ? $("#IdPassportIB").attr("readonly", false):$("#IdPassportIB").attr("readonly", true);
    }else if(datos.beneficiaryTypePerson=="JUR"){
        (datos.beneficiaryFullName=="") ? $("#FullNameIB").attr("readonly", false):$("#FullNameIB").attr("readonly", true);
        (datos.beneCountryIncorporation=="") ? $("#CountryIncorporationIB").attr("disabled", false):$("#CountryIncorporationIB").attr("disabled", true);
    }

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


    if(!$("#BankCodeIBSWIFTtext").get(0).value.isEmpty())
        $("#BankCodeIBSWIFTtext").attr("readonly", true);
    else
        $("#BankCodeIBSWIFTtext").attr("readonly", false);

    //$("#BankCodeIB").val(datos.intermediaryBankCodeType);
    if (datos.intermediaryBankCodeType!="")
        $("#BankCodeIB").val(datos.intermediaryBankCodeType);
    else
        $("#BankCodeIB").val("-2")

    $("#BankCodeIB2").val(datos.intermediaryBankCodeNumber);

    $("#NameBankIB").val(datos.intermediaryBankName);
    $("#AddressLineBankIB1").val(datos.intermediaryBankAddress1);
    $("#AddressLineBankIB2").val(datos.intermediaryBankAddress2);
    $("#AddressLineBankIB3").val(datos.intermediaryBankCity);

    if (datos.intermediaryBankCountryCode!="")
        $("#CountryBankIB").val(datos.intermediaryBankCountryCode)
    else
        $("#CountryBankIB").val("-2")

    validarTransfer =datos.statusAprobacion;

    $("#AccountNumberFFC").val(datos.furtherCreditAccount);
    $("#NameFFC").val(datos.furtherCreditName);
    origenTemplate="TMP";

    fieldsetBeneficiaryInformationTransferencia();
}

function infoPaginaJSONData(){
    var url = urlTransfersCargar;
    var param={};
    var jsonTransfers=[];

    $("#div_carga_espera").removeClass("oculto");
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
    $("#TABSoporte").css({ display: "none" });
    $("#TABSoporte2").css({ display: "none" });
    $("#TABSoporte3").css({ display: "none" });
    removeFiles();
    //Indica que el origen son lsa transferencias a otros bancos
    jsonTransfers[0]= origenTemplate;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});


    sendServiceJSON(url,param,infoPaginaSuccess,null,null);
}

function infoPaginaSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    cuentas = result.cuentas;
    paises = result.paises;
    motivos = result.motivos;
    codBankBen = result.codBankBen;
    valorCuentas = result.cuentasTOB;
    fechaCierre = result.fechaCierre;
    templates = result.listaTemplates;
    TAGMsgInfoNombreBeneficiario = result.TAGMsgInfoNombreBeneficiario;
    idioma = result.idioma;
    usuario = result.usuario;
    PaisesBeneficiario = result.paisesBeneficiario;
    nacionalidadBI = result.nacionalidadBI;
    paisesBI = result.paisesBI;
    cantidadMaximaArchivos=result.cantidadMaximaArchivos;
    pesoMaximoArchivos=result.pesoMaximoArchivos;
    validacionArchivos=result.validacionArchivos;
    validaArchivosContrato=result.validaArchivosContrato;

    var mensaje =result.mensaje;
    var respuesta =result.respuesta;
    var selected= "-2";
    var codSwift = [];
    if (result.accountSelected!=null)
        selected=result.accountSelected;
    $("#div_carga_espera").addClass("oculto");
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

        $('#btnCodBancoBuscar').attr("disabled", false);
        $('#btnCodBancoBuscarSWIFT').attr("disabled", false);
        $('#btnCodBancoBuscarIB').attr("disabled", false);
        $('#btnCodBancoBuscarIBSWIFT').attr("disabled", false);

        $("#name").attr("readonly", false);
        $("#AccountBank").attr("disabled", false);
        $("#AccountNumber").attr("readonly", false);
        $("#beneficiaryEmail").attr("readonly", false);
        $("#AddressLine1").attr("readonly", false);
        $("#AddressLine2").attr("readonly", false);
        $("#AddressLine3").attr("readonly", false);
        $("#TelephoneNumber").attr("readonly", false);
        $("#Country").attr("disabled", false);

        $("#Motivos").attr("readonly", false);

        $("#BankCodeIB").attr("disabled", false);
        $("#BankCodeIB2").attr("readonly", false);
        $("#NameBankIB").attr("readonly", false);
        $("#AddressLineBankIB1").attr("readonly", false);
        $("#AddressLineBankIB2").attr("readonly", false);
        $("#AddressLineBankIB3").attr("readonly", false);
        $("#AccountNumberFFC").attr("readonly", false);
        $("#NameFFC").attr("readonly", false);
        $("#CountryBankIB").attr("disabled", false);

        $("#TipoPersona").attr("disabled", false);
        $("#lastname1").attr("readonly", false);
        $("#lastname2").attr("readonly", false);
        $("#name").attr("readonly", false);
        $("#AddressLine3").attr("readonly", false);
        $("#BeneficiaryPostalCode").attr("readonly", false);

        $("#FullNameIB").attr("readonly", false);
        $("#DateBirthIB").attr("disabled", false);
        $("#NationalityIB").attr("disabled", false);
        $("#IdPassportIB").attr("readonly", false);
        $("#FullNameIndividualIB").attr("readonly", false);
        $("#CountryIncorporationIB").attr("disabled", false);

        $("div[name='transferencias_apellidos']").addClass("oculto");


        //
        $("#noCreateToOtherBank").attr("style","display: none");
        if(valorCuentas!=null)
          if(idioma=="_us_en")
              cargar_selectCuenta("Accounts", valorCuentas,"Select","-2", selected);
          else
              cargar_selectCuenta("Accounts", valorCuentas,"Seleccione","-2", selected);

        //Elimina las que no son en dolares
        /*$("#Accounts option").each(function(){
            var texto=$(this).text();
            if ((texto.indexOf("USD") < 0)&&(texto!="Select")){
                $(this).remove();
            }
        });*/


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
        if(motivos!=null){
            if(idioma=="_us_en")
                cargar_selectAtributosPersonal("Motivos", motivos,"Select","-2");
            else
                cargar_selectAtributosPersonal("Motivos", motivos,"Seleccione","-2");

        }

            //cargar_selectPersonal2("Motivos", motivos);

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
                //cargar_selectPersonal("BankCodeSWIFT", codSwift,"Seleccione","-2");
                //cargar_selectPersonal("BankCodeIBSWIFT", codSwift,"Seleccione","-2");
            }

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
                cargar_selectPersonalpaisesMod("CountryIncorporationIB", paisesBI,"Select","-2","1");
            }else{
                cargar_selectPersonalpaisesMod("CountryIncorporationIB", paisesBI,"Seleccione","-2","1");
            }
        }

        if(fechaCierre!=null)
            $("#tagAvailableBalanceDate").html(fechaCierre);

        //alert(TAGMsgInfoNombreBeneficiario);

            $( "#dialog-confirm p").text(TAGMsgInfoNombreBeneficiario );
            $( "#dialog-confirm" ).dialog({
                resizable: false,
                draggable: false,
                height: "auto",
                width: 400,
                modal: true,
                dialogClass: "no-close",
                position: {
/*                    my: "center",
                    at: "center",*/
                    // of: $('#marco_trabajo')
                    // of: $('#btnCodBancoBuscar')
                },
                buttons: {
                    "OK": function() {
                        $( this ).dialog( "close" );
                    }
                }
            });


        ParametrosPersonalesCamposTOBJSONData();
}  else{
        $("#createToOtherBank").attr("style","display: none");
        $("#noCreateToOtherBank").fadeIn("low");
        $("#noInfo_noCreateToOtherBank").html(respuesta);
    }

  //  console.log(result);
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
    //$("#btn_TemplateGuardar_TOB_cancel").removeClass("oculto");
    $("#btn_TOB_volver").addClass("oculto");
    $("#poppup_saveTemplate").fadeOut("fast");

    if ($("#tipo_contrato_app").val()=="FC"){
        $("#DIV_INFO_EXTERNA_FI").remove();
    }else{
        $("#DIV_INFO_EXTERNA_FC").remove();
    }
    infoPaginaJSONData();
}

function cargarDatos_template(key){
    alert(key);
    $("#BankCode2").val(key);
}

function TransfersValidateJSONData(){
    var url = urlTransfersValidateTOB;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};

    //no permitir codigos swift 10//11/2017 , solicitado en reunion con RR
    //$("#BankCodeSWIFTtext").val("");

    if ($("#productCancelationTOB").prop('checked')){
        ResumenTOB.productCancelation = "S";
    }else{
        ResumenTOB.productCancelation = "N";
    }
    if ($("#transMismoDia").prop('checked')){
        ResumenTOB.mismoDia = "MD";
    }else{
        ResumenTOB.mismoDia = "T48";
    }

    ResumenTOB.account=$('#Accounts :selected').html();
    ResumenTOB.accountCode=$("#Accounts").get(0).value;

    ResumenTOB.beneficiaryName=Trim(normalize($("#name").get(0).value));
    ResumenTOB.beneficiaryLastName1=Trim(normalize($("#lastname1").get(0).value));
    ResumenTOB.beneficiaryLastName2=Trim(normalize($("#lastname2").get(0).value));
    ResumenTOB.beneficiaryTypePerson=Trim(normalize($("#TipoPersona").get(0).value));

    ResumenTOB.beneficiaryAccount=$("#AccountNumber").get(0).value;
    ResumenTOB.beneficiaryAccountBank=$("#AccountBank").get(0).value;
    ResumenTOB.beneficiaryEmail=$("#beneficiaryEmail").get(0).value;
    ResumenTOB.beneficiaryAddress1=Trim(normalize($("#AddressLine1").get(0).value));
    ResumenTOB.beneficiaryAddress2=Trim(normalize($("#AddressLine2").get(0).value));
    //ResumenTOB.beneficiaryAddress3=$("#AddressLine3").get(0).value;
    ResumenTOB.beneficiaryAddress3="";
    ResumenTOB.beneficiaryCity=Trim(normalize($("#AddressLine3").get(0).value));
    ResumenTOB.beneficiaryPostalCode=$("#BeneficiaryPostalCode").get(0).value;

    ResumenTOB.beneficiaryTelephone=$("#TelephoneNumber").get(0).value;
    ResumenTOB.beneficiaryCountry=$('#Country :selected').html();
    ResumenTOB.beneficiaryCountryCode=Trim(normalize($("#Country").get(0).value));


    if($("#BankCode").val()=="-2"){
        ResumenTOB.beneficiaryBankCodeType="";
    }else{
        ResumenTOB.beneficiaryBankCodeType=$("#BankCode").val();
    }

    ResumenTOB.beneficiaryBankCodeNumber=$("#BankCode2").get(0).value;
    ResumenTOB.beneficiaryBankName=$("#NameBank").get(0).value;
    ResumenTOB.beneficiaryBankAddress1=Trim(normalize($("#AddressLineBank1").get(0).value));
    ResumenTOB.beneficiaryBankAddress2=Trim(normalize($("#AddressLineBank2").get(0).value));
    //ResumenTOB.beneficiaryBankAddress3=$("#AddressLineBank3").get(0).value;
    ResumenTOB.beneficiaryBankAddress3=""
    ResumenTOB.beneficiaryBankCity=Trim(normalize($("#AddressLineBank3").get(0).value));
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
    ResumenTOB.intermediaryBankAddress1=Trim(normalize($("#AddressLineBankIB1").get(0).value));
    ResumenTOB.intermediaryBankAddress2=Trim(normalize($("#AddressLineBankIB2").get(0).value));
    //ResumenTOB.intermediaryBankAddress3=$("#AddressLineBankIB3").get(0).value;
    ResumenTOB.intermediaryBankAddress3="";
    ResumenTOB.intermediaryBankCity=Trim(normalize($("#AddressLineBankIB3").get(0).value));
    if($("#CountryBankIB").get(0).value=="-2"){
        ResumenTOB.intermediaryBankCountry="";
        ResumenTOB.intermediaryBankCountryCode="";
    }else{
        ResumenTOB.intermediaryBankCountry=$('#CountryBankIB :selected').html();
        ResumenTOB.intermediaryBankCountryCode=$("#CountryBankIB").get(0).value;
    }


    ResumenTOB.furtherCreditAccount=$("#AccountNumberFFC").get(0).value;
    ResumenTOB.furtherCreditName=Trim(normalize($("#NameFFC").get(0).value));
//    ResumenTOB.amount=parseFloat($("#AmmountAI").get(0).value);

    var montoAux="";

    montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
    montoAux=(montoAux).replace(/\./g,',');
    montoAux=(montoAux).replace(/t/g,'.');

    //ResumenTOB.amount=$("#AmmountAI").get(0).value;
    ResumenTOB.amount=montoAux;
//    ResumenTOB.amount="0";
    ResumenTOB.recieverName=$("#ReceiverInformation").get(0).value;



    var resumenmotivo=  $('#Motivos option:selected').attr('descripcion');
    if(resumenmotivo =="S"){
        ResumenTOB.motivo=$("#MotivoAI").val();
    }else{
        //ResumenTOB.motivo=$("#Motivos").get(0).val()
        ResumenTOB.motivo=$('#Motivos :selected').html();
    }

    //ResumenTOB  BENEFICIARY INFORMATION (INDIVIDUALS/ COMPANIES)
    if($("#TipoPersona").get(0).value== "-2"){
        ResumenTOB.beneficiaryFullName="";
        ResumenTOB.beneficiaryBirthDate="";
        ResumenTOB.beneficiaryNationality="";
        ResumenTOB.beneficiaryIdPassport="";
        ResumenTOB.beneCountryIncorporation="";
    }else if($("#TipoPersona").get(0).value== "NAT"){
        ResumenTOB.beneficiaryFullName = Trim($("#FullNameIndividualIB").get(0).value);
        ResumenTOB.beneficiaryBirthDate = $("#DateBirthIB").get(0).value;
        ResumenTOB.beneficiaryNationality = $("#NationalityIB").get(0).value;
        ResumenTOB.beneficiaryIdPassport = $("#IdPassportIB").get(0).value;
        ResumenTOB.beneCountryIncorporation= "";

    }else if($("#TipoPersona").get(0).value== "JUR"){
        ResumenTOB.beneficiaryFullName = Trim($("#FullNameIB").get(0).value);
        ResumenTOB.beneCountryIncorporation = $("#CountryIncorporationIB").get(0).value;
        ResumenTOB.beneficiaryBirthDate = "";
        ResumenTOB.beneficiaryNationality = "";
        ResumenTOB.beneficiaryIdPassport = "";

    }


    for (var i in ResumenTOB){
        ResumenTOB[i]=Trim(normalize(ResumenTOB[i]));
    }
    var moneda="";

   /* if($("#Accounts option:selected").text().indexOf('USD')>=0){
        moneda = "USD";
    }else if($("#Accounts option:selected").text().indexOf('EUR')>=0){
        moneda = "EUR";
    }*/

    ResumenTOB.moneda=$("#Accounts option:selected").attr("extra1");


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
    var existeBenSwift =result.existeBenSwift;
    var existeIntSwift = result.existeIntSwift;
    idioma = result.idioma;

    //$("#marco_trabajo").css("height","900px");
    resumen = result.resumenTOB;
    resumentransferToOtherBank = result.resumenTOB;


    $("#RAccounts").html(quitarSaldo($('#Accounts :selected').html()));

    var nombreCompleto =   $('#name').val();

    if($("#TipoPersona").get(0).value=="NAT"){
        if($('#lastname1').val().length>0)  nombreCompleto +=  ' '+$('#lastname1').val();
        if($('#lastname2').val().length>0)  nombreCompleto +=  ' '+$('#lastname2').val();
    }




    $("#Rname").html(nombreCompleto);


    $("#RAccountNumber").html($('#AccountBank :selected').html()+"  |  "+$("#AccountNumber").get(0).value);
    $("#RbeneficiaryEmail").html($("#beneficiaryEmail").get(0).value);
    $("#RAddressLine1").html($("#AddressLine1").get(0).value);

    if($("#NameBank").get(0).value !=""){
        $("#div_RBankName").fadeIn("fast");
        $("#RBankName").html($("#NameBank").get(0).value);
    }

    if($("#AddressLine2").get(0).value !=""){
        $("#div_RAddressLine2").fadeIn("fast");
        $("#RAddressLine2").html($("#AddressLine2").get(0).value);
    } else{
            $("#div_RAddressLine2").fadeOut("fast");
        }

    if($("#AddressLine3").get(0).value !=""){
        $("#div_RAddressLine3").fadeIn("fast");
        $("#RAddressLine3").html(Trim($("#AddressLine3").get(0).value));
    }
    if($("#BeneficiaryPostalCode").get(0).value !=""){
        $("#div_PostalCode").fadeIn("fast");
        $("#RBeneficiaryPostalCode").html(Trim($("#BeneficiaryPostalCode").get(0).value));
    }
    if(Trim($("#TelephoneNumber").get(0).value) !=""){
        $("#div_RTelephoneNumber").fadeIn("fast");
        $("#RTelephoneNumber").html($("#TelephoneNumber").get(0).value);
    }else{
        $("#div_RTelephoneNumber").fadeOut("fast");
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
    }
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

    $("#RAmmountAI").html($("#AmmountAI").get(0).value+" "+monedaRTOB);


    $("#RMotivoAI").html(resumen.motivo);

    if(Trim($("#ReceiverInformation").get(0).value) !=""){
        $("#div_RReceiverInformation").fadeIn("fast");
        $("#RReceiverInformation").html(Trim($("#ReceiverInformation").get(0).value));
    }else{
        $("#div_RReceiverInformation").attr("style","display: none");
    }

    //Transfer_benficiaryInformation ResumenTOB

    /*if($("#TipoPersona").get(0).value == "NAT"){ */

        if(Trim($("#FullNameIndividualIB").get(0).value) !=""){
            $("#div_Transferencias_RFullNameIndividualIB").fadeIn("fast");
            $("#RFullNameIndividualIB").html(Trim($("#FullNameIndividualIB").get(0).value));
        }else{
            $("#div_Transferencias_RFullNameIndividualIB").attr("style","display: none");
        }

        if(Trim($("#DateBirthIB").get(0).value) !=""){
            $("#div_Transferencias_RDateBirthIB").fadeIn("fast");
            $("#RDateBirthIB").html(Trim($("#DateBirthIB").get(0).value));
        }else{
            $("#div_Transferencias_RDateBirthIB").attr("style","display: none");
        }

        if(Trim($("#NationalityIB").get(0).value) !="-2"){
            $("#div_Transferencias_RNationalityIB").fadeIn("fast");
            $("#RNationalityIB").html(Trim($("#NationalityIB :selected").html()));
        }else{
            $("#div_Transferencias_RNationalityIB").attr("style","display: none");
        }

        if(Trim($("#IdPassportIB").get(0).value) !=""){
            $("#div_Transferencias_RIdPassportIB").fadeIn("fast");
            $("#RIdPassportIB").html(Trim($("#IdPassportIB").get(0).value));
        }else{
            $("#div_Transferencias_RIdPassportIB").attr("style","display: none");
        }

    /*}else if($("#TipoPersona").get(0).value == "JUR"){  */

        if(Trim($("#FullNameIB").get(0).value) !=""){
            $("#div_Transferencias_RFullNameIB").fadeIn("fast");
            $("#RFullNameIB").html(Trim($("#FullNameIB").get(0).value));
        }else{
            $("#div_Transferencias_RFullNameIB").attr("style","display: none");
        }

        if(Trim($("#CountryIncorporationIB").get(0).value) !="-2"){
            $("#div_Transferencias_RCountryIncorporationIB").fadeIn("fast");
            $("#RCountryIncorporationIB").html(Trim($("#CountryIncorporationIB :selected").html()));
        }else{
            $("#div_Transferencias_RCountryIncorporationIB").attr("style","display: none");
        }

        if ($("#productCancelationTOB").prop('checked')){
            if (idioma=="_us_en")
                $("#RProductoCancelacionIB").html("Yes");
            else
                $("#RProductoCancelacionIB").html("Si");
        }else{
            $("#div_Transferencias_productoCancelacionIB").hide();
        }

    if ($("#transMismoDia").prop('checked')){
        if (idioma=="_us_en")
            $("#mismoDiaIB").html("Yes");
        else
            $("#mismoDiaIB").html("Si");
    }else{
        $("#div_Transferencias_mismoDiaIB").hide();
    }



    /*} */
    /* SupportTitle*/
    if(archivosSubidos.length>0 && $('#Motivos option:selected').attr('soporte')=="S"){
        $("#SupportTitle").fadeIn("fast");
        var archivos = "<ul>";
        for(i=0;i<archivosSubidos.length;i++){
            archivos += "<li>"+ archivosSubidos[i]+"</li>";
        }
        archivos += "</ul>"

            $("#div_Transferencias_RarchivoSoporte").fadeIn("fast");
            $("#RSupportfiles").html(archivos);
    }else{
        $("#div_Transferencias_RarchivoSoporte").attr("style","display: none");
        $("#SupportTitle").attr("style","display: none");
    }


    var mensaje ="";
    if(existeBen == 0){
        if(idioma=="_us_en")
          mensaje = mensaje + "The code of the Beneficiary Bank does not exist.<br>";
        else
          mensaje = mensaje + "No existe el c&oacute;digo del Banco Beneficiario.<br>";
        $("#BankCode2").addClass("error_campo");
    }else{
        if($("#BankCode2").hasClass("error_campo"))
            $("#BankCode2").removeClass("error_campo");
    }
    if(existeInt == 0){
        if(idioma=="_us_en")
          mensaje = mensaje + "The code of the Intermediary Bank does not exist.<br>";
        else
          mensaje = mensaje + "No existe el c&oacute;digo del Banco Intermediario.<br>";
        $("#BankCodeIB2").addClass("error_campo");
    }else{
        if($("#BankCodeIB2").hasClass("error_campo"))
            $("#BankCodeIB2").removeClass("error_campo");
    }

    if(existeBenSwift == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The Swift code of the Beneficiary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo Swift del Banco Beneficiario.<br>";
        $("#BankCodeSWIFTtext").addClass("error_campo");
    }else{
        if($("#BankCodeSWIFTtext").hasClass("error_campo"))
            $("#BankCodeSWIFTtext").removeClass("error_campo");
    }

    if(existeIntSwift == 0){
        if(idioma=="_us_en")
            mensaje = mensaje + "The Swift code of the Intermediary Bank does not exist.<br>";
        else
            mensaje = mensaje + "No existe el c&oacute;digo Swift del Banco Intermediario.<br>";
        $("#BankCodeIBSWIFTtext").addClass("error_campo");
    }else{
        if($("#BankCodeIBSWIFTtext").hasClass("error_campo"))
            $("#BankCodeIBSWIFTtext").removeClass("error_campo");
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
    $("#div_carga_espera").removeClass("oculto")
    var url = urlTransfersSaveTOB;
    var param={};
    var jsonTransfers=[];
    var ResumenTOB={};
    var montoAux="";


    montoAux=($("#AmmountAI").get(0).value).replace(/,/g,'t');
    montoAux=(montoAux).replace(/\./g,',');
    montoAux=(montoAux).replace(/t/g,'.');

    resumentransferToOtherBank.amount = Trim(montoAux);
    resumentransferToOtherBank.claveTemporal = Trim($("#pwdClaveConfirmTransfer_TOB").get(0).value);
    resumentransferToOtherBank.origenTemplate = origenTemplate;
    resumentransferToOtherBank.idTemplate = idTemplate;

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

    $("#div_carga_espera").addClass("oculto");
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

       // if ( $("#tipo_usuario_app").val()=="6"){
        if ($("#tipo_contrato_app").val()=="FC"){
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
//        if ( $("#tipo_usuario_app").val()!="6"){
        if ($("#tipo_contrato_app").val()!="FC"){
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
    }
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
    $("#div_carga_espera").removeClass("oculto")
    var url = urlSecurityGenerarClaveSMS;
    var param={};

    sendServiceJSON_sinc(url,param,GenerarClaveSMSSuccess,null,null);
    // sendServiceJSON(url,param,GenerarClaveSMSSuccess,null,null);
}

function GenerarClaveSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    $("#div_carga_espera").addClass("oculto")

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
    $("#Template_AddressLineBank3").val(resumentransferToOtherBank.beneficiaryBankCity);
    $("#Template_CountryBank").val(resumentransferToOtherBank.beneficiaryBankCountryCode);

    $("#Template_name").val(resumentransferToOtherBank.beneficiaryName);
    $("#Template_AccountBank").val(resumentransferToOtherBank.beneficiaryAccountBank);
    $("#Template_AccountNumber").val(resumentransferToOtherBank.beneficiaryAccount);
    $("#Template_beneficiaryEmail").val(resumentransferToOtherBank.beneficiaryEmail);
    $("#Template_AddressLine1").val(resumentransferToOtherBank.beneficiaryAddress1);
    $("#Template_AddressLine2").val(resumentransferToOtherBank.beneficiaryAddress2);
    $("#Template_AddressLine3").val(resumentransferToOtherBank.beneficiaryCity);
    $("#Template_BeneficiaryPostalCode").val(resumentransferToOtherBank.beneficiaryPostalCode);
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
    $("#Template_AddressLineBankIB3").val(resumentransferToOtherBank.intermediaryBankCity);
    $("#Template_CountryBankIB").val(resumentransferToOtherBank.intermediaryBankCountryCode);

    $("#Template_AccountNumberFFC").val(resumentransferToOtherBank.furtherCreditAccount);
    $("#Template_NameFFC").val(resumentransferToOtherBank.furtherCreditName);
//    $("#Template_nombreTemplate").val(resumentransferToOtherBank.beneficiaryName);
    $("#Template_nombreTemplate").val("");



        if (resumentransferToOtherBank.beneficiaryTypePerson=="NAT"){
            $("div[name='Template_transferencias_apellidos']").removeClass("oculto");
        }else{
            $("div[name='Template_transferencias_apellidos']").addClass("oculto");
        }


    $("#Template_TipoPersona").val(resumentransferToOtherBank.beneficiaryTypePerson);
    $("#Template_lastname1").val(resumentransferToOtherBank.beneficiaryLastName1);
    $("#Template_lastname2").val(resumentransferToOtherBank.beneficiaryLastName2);


    if(resumentransferToOtherBank.beneficiaryTypePerson == "NAT"){
        $("#Template_FullNameIndividualIB").val(resumentransferToOtherBank.beneficiaryFullName);
        $("#Template_DateBirthIB").val(resumentransferToOtherBank.beneficiaryBirthDate);
        $("#Template_IdPassportIB").val(resumentransferToOtherBank.beneficiaryIdPassport);
        $("#Template_NationalityIB").val(resumentransferToOtherBank.beneficiaryNationality);
    }else if(resumentransferToOtherBank.beneficiaryTypePerson == "JUR"){
        $("#Template_FullNameIB").val(resumentransferToOtherBank.beneficiaryFullName);
        $("#Template_CountryIncorporationIB").val(resumentransferToOtherBank.beneCountryIncorporation);
    }

    fieldsetBeneficiaryInformationTemplate();

}

function cargarSalvarTemplateTransferInicio(){
    //                   this is the json return data
    //$("#marco_trabajo").css("height","750px");

    $("#Template_BankCode").val($("#BankCode").val());
    $("#Template_BankCode2").val($("#BankCode2").val());
    //$("#Template_SwiftBankCode").val(resumentransferToOtherBank.beneficiaryBankCodeTypeSwift);
    $("#Template_SwiftBankCode2").val($("#BankCodeSWIFTtext").val());
    $("#Template_NameBank").val($("#NameBank").val());
    $("#Template_AddressLineBank1").val($("#AddressLineBank1").val());
    $("#Template_AddressLineBank2").val($("#AddressLineBank2").val());
    $("#Template_AddressLineBank3").val($("#AddressLineBank3").val());
    $("#Template_CountryBank").val($("#CountryBank").val());

    $("#Template_name").val($("#name").val());
    $("#Template_AccountBank").val($("#AccountBank").val());
    $("#Template_AccountNumber").val($("#AccountNumber").val());
    $("#Template_beneficiaryEmail").val($("#beneficiaryEmail").val());
    $("#Template_AddressLine1").val( $("#AddressLine1").val());
    $("#Template_AddressLine2").val( $("#AddressLine2").val());
    $("#Template_AddressLine3").val( $("#AddressLine3").val());
    $("#Template_BeneficiaryPostalCode").val( $("#BeneficiaryPostalCode").val());
    $("#Template_TelephoneNumber").val($("#TelephoneNumber").val());
    $("#Template_Country").val($("#Country").val());

   /* if($("#BankCodeIB").val()=="ACCOUNT" && Trim($("#BankCodeIB2").val())!=""){
        $("#BankCodeIB").val($("#BankCodeIB").val());
    }else{
        $("#BankCodeIB").val("-2");
    }   */
    $("#Template_BankCodeIB").val($("#BankCodeIB").val());
    $("#Template_BankCodeIB2").val($("#BankCodeIB2").val());
    $("#Template_SwiftBankCodeIB2").val($("#BankCodeIBSWIFTtext").val());
    $("#Template_NameBankIB").val($("#NameBankIB").val());
    $("#Template_AddressLineBankIB1").val($("#AddressLineBankIB1").val());
    $("#Template_AddressLineBankIB2").val($("#AddressLineBankIB2").val());
    $("#Template_AddressLineBankIB3").val($("#AddressLineBankIB3").val());
    $("#Template_CountryBankIB").val($("#CountryBankIB").val());

    $("#Template_AccountNumberFFC").val( $("#AccountNumberFFC").val());
    $("#Template_NameFFC").val($("#NameFFC").val());
    $("#Template_nombreTemplate").val($("#nombreTemplate").val());



    if ($("#TipoPersona").val()=="NAT"){
        $("div[name='Template_transferencias_apellidos']").removeClass("oculto");
    }else{
        $("div[name='Template_transferencias_apellidos']").addClass("oculto");
    }


    $("#Template_TipoPersona").val($("#TipoPersona").val());
    $("#Template_lastname1").val($("#lastname1").val());
    $("#Template_lastname2").val($("#lastname2").val());




    if ($("#TipoPersona").val()=="NAT"){
        $("#Template_FullNameIndividualIB").val($("#FullNameIndividualIB").val());
        $("#Template_DateBirthIB").val($("#DateBirthIB").val());
        $("#Template_NationalityIB").val($("#NationalityIB").val());
        $("#Template_IdPassportIB").val($("#IdPassportIB").val());
    }else if($("#TipoPersona").val()=="JUR"){
        $("#Template_FullNameIB").val($("#FullNameIB").val());
        $("#Template_CountryIncorporationIB").val($("#CountryIncorporationIB").val());
    }

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

function clearAccordeons(){
    //ACCORDEON BENEFICIARY BANK
    $("#beneficiaryBankAccordeonBlock").removeClass("accordeon__block--active")
    $("#beneficiaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#beneficiaryAccordeonContent").removeClass("accordeon__content--active")

//ACCORDEON BENEFICIARY
    $("#beneficiaryAccordeonBlock").removeClass("accordeon__block--active")
    $("#beneficiaryAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#beneficiaryInformationAccordeonContent").removeClass("accordeon__content--active")

//ACCORDEON BENEFICIARY INFORMATION
    $("#form_14").removeClass("accordeon__block--active")
    $("#beneficiaryInformationAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#intermediaryBankAccordeonContent").removeClass("accordeon__content--active")

//ACCORDEON INTERMEDIARY BANK
    $("#intermediaryBankAccordeonBlock").removeClass("accordeon__block--active")
    $("#intermediaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#futherCreditAccordeonContent").removeClass("accordeon__content--active")

//ACCORDEON FUTHER CREDIT
    $("#futherCreditAccordeonBlock").removeClass("accordeon__block--active")
    $("#futherCreditAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#amountInstructionsAccordeonContent").removeClass("accordeon__content--active")

//ACCORDEON AMMOUNT INSTRUCTIONS
    $("#amountInstructionsAccordeonBlock").removeClass("accordeon__block--active")
    $("#amountInstructionsAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");
    $("#purposePaymentAccordeonContent").removeClass("accordeon__content--active")

    $("#purposePaymentAccordeonBlock").removeClass("accordeon__block--active")
    $("#purposePaymentAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");


}
function openAccordeons(){
    //ACCORDEON BENEFICIARY BANK
    $("#beneficiaryBankAccordeonBlock").addClass("accordeon__block--active")
    $("#beneficiaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
    $("#beneficiaryAccordeonContent").addClass("accordeon__content--active")

//ACCORDEON BENEFICIARY
    $("#beneficiaryAccordeonBlock").addClass("accordeon__block--active")
    $("#beneficiaryAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
    $("#beneficiaryInformationAccordeonContent").addClass("accordeon__content--active")

//ACCORDEON BENEFICIARY INFORMATION
    $("#form_14").addClass("accordeon__block--active")
    $("#beneficiaryInformationAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
    $("#intermediaryBankAccordeonContent").addClass("accordeon__content--active")

//ACCORDEON INTERMEDIARY BANK
    $("#intermediaryBankAccordeonBlock").addClass("accordeon__block--active")
    $("#intermediaryBankAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
    $("#futherCreditAccordeonContent").addClass("accordeon__content--active")

//ACCORDEON FUTHER CREDIT
    $("#futherCreditAccordeonBlock").addClass("accordeon__block--active")
    $("#futherCreditAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_complete.png");
    $("#amountInstructionsAccordeonContent").addClass("accordeon__content--active")

//ACCORDEON AMMOUNT INSTRUCTIONS
    $("#amountInstructionsAccordeonBlock").removeClass("accordeon__block--active")
    $("#amountInstructionsAccordeonStep").attr("src","../vbtonline/resources/img/icons/ic_table_templates_step_uncomplete.png");

    $("#purposePaymentAccordeonBlock").removeClass("accordeon__block--active")
    $("#purposePaymentAccordeonContent").addClass("accordeon__content--active")
}
function cargarTemplatePoppup(alias){
    var arr = alias.split("|");
    $("#Accounts").addClass("focus_selecionado");

    cargarInfoTemplateJSONData(arr[1]);
    //$("#btn_TemplateGuardar_TOB_cancel").addClass("oculto");
    // $("#sign_up_template_transfer").trigger('close');
    $.modal.close();
    $("#btn_TOB_volver").addClass("oculto");
    //$("#tagiTemplate").val(arr[0]);
    popupTmp="OK";
    openAccordeons();
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
    // $("#sign_up_template_transfer").trigger('close');
    $.modal.close();
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

function buscarIdTemplate(){
    var url = urlIdTemplate;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= $("#Template_nombreTemplate").val();


    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});
    sendServiceJSON_sinc(url,param,buscarIdTemplateSuccessFC,null,null);
}

function buscarIdTemplateSuccessFC(originalRequest){
    var mensaje="";
    var result = originalRequest;


    idTemplate=result.respuesta;

}

function infoParametrosPersonalesPopPupJSONData(){
    var url = urlParametrosPersonalesCargarCamposBMLA;
    var param={};

    sendServiceJSON_sinc(url,param,infoParametrosPersonalesPopPup,null,null);
}

function infoParametrosPersonalesPopPup(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;


    $("#pupupTag_PP_numeroMaxTransDiariasValue").text(parametros.vbt_nmtd);
    $("#pupupTag_PP_montoMaxTransDiariasValue").text(parametros.vbt_mmaxtd);
    $("#pupupTag_PP_montoMinTransOpeValue").text(parametros.vbt_mminto);
    $("#pupupTag_PP_montoMaxTransOpeValue").text(parametros.vbt_mmto);

    $("#popupTag_PP_numeroMaxTransDiarias_OBValue").text(parametros.ex_nmtd);
    $("#popupTag_PP_montoMaxTransDiarias_OBValue").text(parametros.ex_mmtd);
    $("#popupTag_PP_montoMinTransOpe_OBValue").text(parametros.ex_mminto);
    $("#popupTag_PP_montoMaxTransOpe_OBValue").text(parametros.ex_mmto);


}

function mostrarDisclaimer2(texto) {
    if(idioma=="_us_en") {
        $( "#dialog-disclaimer p").html("Please be advised that recent implementations by the United States of America of sanctions in relation to certain jurisdictions and individuals, as well as further measures being imposed for correspondent banks, have necessitated additional internal procedures. As such delays may be encountered when executing requests, and, in some instances, it may not be possible to execute the request.  We take this opportunity to reaffirm our commitment to ensuring that we make the greatest efforts in order to efficiently carry out requests and thank you for your understanding. <br><p align='center'><b>"+texto+"</b></p>"  );
        $( "#dialog-disclaimer" ).dialog({
            resizable: false,
            height: "auto",
            width: 600,
            // modal: true,
            closeOnEscape: false,
            position: {
                my: "center",
                at: "center",
                of: $('#Motivos')
            },

            buttons: {
                "Cancel": function() {
                    $("#lastname2").addClass("error_campo");
                    eliminarFile(false);
                    $( this ).dialog( "close" );
                },
                Continue: function() {
                    validateBeneficiaryBankJSONData()
                    $( this ).dialog( "close" );
                }
            }
        });

    } else {
        $( "#dialog-disclaimer p").html("Tenga en cuenta que debido a las recientes sanciones por parte de los Estados Unidos en relación con determinadas jurisdicciones e individuos, así como otras medidas impuestas a los bancos corresponsales, nos vemos en la necesidad de implementar procedimientos de validación internos adicionales. En tal sentido pueden ocurrir retrasos al ejecutar su solicitud, y, en algunos casos, puede que no sea posible ejecutar la misma. Aprovechamos esta oportunidad para reafirmar nuestro compromiso de garantizar que hacemos los mayores esfuerzos para procesar su solicitud de una manera eficiente y le agradecemos su comprensión al respecto. <br><p align='center'><b>"+texto+"</b></p>"  );
        $( "#dialog-disclaimer" ).dialog({
            resizable: false,
            height: "auto",
            width: 600,
            // modal: true,
            closeOnEscape: false,
            position: {
                my: "center",
                at: "center",
                of: $('#Motivos')
            },
            buttons: {
                "Cancelar": function() {
                    $("#lastname2").addClass("error_campo");
                    eliminarFile(false);
                    $( this ).dialog( "close" );
                },
                Continuar: function() {
                    validateBeneficiaryBankJSONData()
                    $( this ).dialog( "close" );
                }
            }
        });

    }
}

function validateBeneficiaryBankJSONData() {
    var url = urlValidacionBancoBeneficiario;
    var param = {};
    var jsonTransfers = [];

    jsonTransfers[0] = $("#BankCodeSWIFTtext").val() != '' ? $("#BankCodeSWIFTtext").val() : $("#BankCode2").val();
    jsonTransfers[1] = $("#AccountNumber").val();
    jsonTransfers[2] = $("#BankCodeIBSWIFTtext").val() != '' ? $("#BankCodeIBSWIFTtext").val() : $("#BankCodeIB2").val();
    jsonTransfers[3] = Trim($("#Accounts").val().split("|")[1]);


    $("#BankCodeSWIFTtext").removeClass("error_campo");
    $("#BankCodeIBSWIFTtext").removeClass("error_campo");
    $("#BankCode2").removeClass("error_campo");
    $("#BankCodeIB2").removeClass("error_campo");
    $("#AccountNumber").removeClass("error_campo");

    param.jsonTransfers = JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON_sinc(url,param,validateBeneficiaryBank,null,null);

}

function fieldsetBeneficiaryInformationTransferencia(){
    if($("#TipoPersona").get(0).value != "-2"){
        $("#form_14").attr("style","");
        if($("#TipoPersona").get(0).value == "NAT"){
            /* Remover Tabla de Companies */
            $("#TableCompanies").attr("style","display:none");
            $("#FullNameIB").removeClass("obligatorioTOB");
            $("#CountryIncorporationIB").removeClass("obligatorioTOB");
            /* Agregar Tabla de Individual */
            $("#TableIndividual").attr("style","");
            $("#FullNameIndividualIB").addClass("obligatorioTOB");
            $("#DateBirthIB").addClass("obligatorioTOB");
            $("#NationalityIB").addClass("obligatorioTOB");
            $("#IdPassportIB").addClass("obligatorioTOB");

        }else if($("#TipoPersona").get(0).value == "JUR"){
            /* Remover Tabla de Individual */
            $("#TableIndividual").attr("style","display:none");
            $("#FullNameIndividualIB").removeClass("obligatorioTOB");
            $("#DateBirthIB").removeClass("obligatorioTOB");
            $("#NationalityIB").removeClass("obligatorioTOB");
            $("#IdPassportIB").removeClass("obligatorioTOB");
            /* Agregar Tabla de Companies */
            $("#FullNameIB").addClass("obligatorioTOB");
            $("#CountryIncorporationIB").addClass("obligatorioTOB");
            $("#TableCompanies").attr("style","");
        }
    }else{
        $("#form_14").attr("style","display:none");
    }
}

function validateBeneficiaryBank(originalRequest) {
    var result = originalRequest;
    var resultado = result.respuesta;
    switch (resultado) {
        case '0': {// SUCCESS
            TransfersValidateJSONData();
            break;
        }
        case '1': {// Codigo Beneficiario
//            if ($("#BankCodeSWIFTtext").val() != '') {
//                $("#BankCodeSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCode2").val() != '') {
//                $("#BankCode2").addClass("error_campo");
//            }
            break;
        }
        case '2': {// Cuenta Beneficiario
            $("#AccountNumber").addClass("error_campo");
            break;
        }
        case '3': {// Codigo Intermediario
//            if ($("#BankCodeIBSWIFTtext").val() != '') {
//                $("#BankCodeIBSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCodeIB2").val() != '') {
//                $("#BankCodeIB2").addClass("error_campo");
//            }
            break;
        }
        case '4': {// Codigo y Cuenta Beneficiario
//            if ($("#BankCodeSWIFTtext").val() != '') {
//                $("#BankCodeSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCode2").val() != '') {
//                $("#BankCode2").addClass("error_campo");
//            }
            $("#AccountNumber").addClass("error_campo");
            break;
        }
        case '5': {// Codigo Beneficiario e Intermediario
//            if ($("#BankCodeSWIFTtext").val() != '') {
//                $("#BankCodeSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCode2").val() != '') {
//                $("#BankCode2").addClass("error_campo");
//            }
//            if ($("#BankCodeIBSWIFTtext").val() != '') {
//                $("#BankCodeIBSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCodeIB2").val() != '') {
//                $("#BankCodeIB2").addClass("error_campo");
//            }
            break;
        }
        case '6': {// Codigo Beneficiario e Intermediario y Cuenta Beneficiario
//            if ($("#BankCodeSWIFTtext").val() != '') {
//                $("#BankCodeSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCode2").val() != '') {
//                $("#BankCode2").addClass("error_campo");
//            }
//            if ($("#BankCodeIBSWIFTtext").val() != '') {
//                $("#BankCodeIBSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCodeIB2").val() != '') {
//                $("#BankCodeIB2").addClass("error_campo");
//            }
            $("#AccountNumber").addClass("error_campo");
            break;
        }
        case '7': {// Codico Intermediario y Cuenta Beneficiario
//            if ($("#BankCodeIBSWIFTtext").val() != '') {
//                $("#BankCodeIBSWIFTtext").addClass("error_campo");
//            }
//            if ($("#BankCodeIB2").val() != '') {
//                $("#BankCodeIB2").addClass("error_campo");
//            }
            $("#AccountNumber").addClass("error_campo");
            break;
        }
        default: {
            $("#mensaje_error").empty();
            cargarIdiomaJSONData_sinc();
            $("#mensaje_error").html("<span id='TAGErrorPDF'>" + vbtol_props[idioma]["TAGErrorPDF"] + "</span>");

            $("#div_mensajes_error").fadeIn("slow");
        }

    }
    if (resultado != "0") {
        $("#mensaje_error").empty();
        cargarIdiomaJSONData_sinc();
        if (resultado=="12"){
            $("#mensaje_error").html("<span id='TAGErrorCorreoOriginador'>" + vbtol_props[idioma]["TAGErrorCorreoOriginador"] + "</span>");
        }else{
            $("#mensaje_error").html("<span id='TAG_TOB_ERROR_BANK'>" + vbtol_props[idioma]["TAG_TOB_ERROR_BANK"] + "</span>");
        }
        $("#div_mensajes_error").fadeIn("slow");
    }
}

function popups(text,fileName){
    if(idioma=="_us_en") {
        $( "#dialog-confirm p").html(text);
        $( "#dialog-confirm" ).dialog({
            resizable: false,
            draggable: false,
            height: "auto",
            width: 400,
            closeOnEscape: false,
            position: {
                // my: "center",
                // at: "center",
                // of: $('#marco_trabajo')
                // of: $('#Motivos')
            },
            buttons: {
                "Cancel": function() {
                    deleteFile(fileName,true,false);
                    $( this ).dialog( "close" );
                },
                "Accept": function() {
                    deleteFile(fileName,true,true);
                    $( this ).dialog( "close" );
                }
            }
        });
    }else{
        $( "#dialog-confirm p").html(text);
        $( "#dialog-confirm" ).dialog({
            resizable: false,
            draggable: false,
            height: "auto",
            width: 400,
            closeOnEscape: false,
            position: {
                // my: "center",
                // at: "center",
                // of: $('#marco_trabajo')
                // of: $('#Motivos')
            },
            buttons: {
                "Cancelar": function() {
                    deleteFile(fileName,true,false);
                    $( this ).dialog( "close" );
                },
                "Aceptar": function() {
                    deleteFile(fileName,true,true);
                    $( this ).dialog( "close" );
                }
            }
        });
    }
}

function removeFiles(){
    /*console.log("Eliminar los Archivos" );*/
    $.ajax({
        url: urlLimpiarSoporte,
        method: 'POST',
        success: function(data){
            if(idioma=="_ve_es")
                $("#transferencias_TAGMsgSoporteMotivo").html(
                    $('<div class="Nofiles"/>').text('No hay archivos seleccionados '));
            if(idioma=="_us_en")
                $("#transferencias_TAGMsgSoporteMotivo").html(
                    $('<div class="Nofiles"/>').text('No files were selected'));

            $('#progress .bar').css(
                'width',
                data.mensaje + '%'
            );

            $('.percent').html( data.mensaje + '%');

            archivosSubidos="";
            archivosTotales=0;
        },
        error: function (e) {
            console.log(e.responseText)
        }
    });
}

function deleteFile(fileName,confirmado,borrar){
    /*console.log("Eliminar Archivo:" +fileName );*/
    var data = {'delete':fileName};
    if(idioma=="_us_en" && confirmado==false){
        borrar = popups("Are you sure you want to delete the file: "+ fileName + " ?", fileName);
    }else if(idioma=="_ve_es" && confirmado==false){
        borrar = popups("¿Está seguro que desea eliminar el archivo: "+ fileName + " ?",fileName);
    }
    if(borrar&&confirmado){
        $.ajax({
            url: urlBorrarArchivo,
            method: 'POST',
            data: data,
            success: function(data){
                if(data.flag) {
                    id = data.archivo;
                    try{
                        document.getElementById(id).remove();
                    }catch(err) {
                        console.log("err: " + err);
                        $("#"+id).html("");
                    }
                    /*document.getElementById(id).remove();*/
                    if(data.arhivosSubidos.length == 0){
                        if(idioma=="_ve_es")
                            $("#transferencias_TAGMsgSoporteMotivo").html(
                                $('<div class="Nofiles"/>').text('No hay archivos seleccionados'));
                        if(idioma=="_us_en")
                            $("#transferencias_TAGMsgSoporteMotivo").html(
                                $('<div class="Nofiles"/>').text('No files were selected'));

                        $('#progress .bar').css(
                            'width',
                            data.mensaje + '%'
                        );
                        $('.percent').html( data.mensaje + '%');

                        archivosTotales=0;
                        archivosSubidos = "";
                    }else{

                        if(data.arhivosSubidos!=null){
                            archivosSubidos=data.arhivosSubidos;
                            archivosTotales= archivosSubidos.length;
                            progreso(archivosSubidos.length,archivosTotales);
                        }

                    }


                }
            },
            error: function (e) {
                console.log(e.responseText)
            }
        });
    }
}

function progreso (cargado, total) {
    var progress = parseInt( cargado / total * 100, 10);
    /*console.log("progress: "+progress);*/
    if(progress==100)
        $("#loading").removeClass("load");
    $('#progress .bar').css(
        'width',
        progress + '%'
    );
    $('.percent').html( (progress) + '%' + "  " + cargado + "/" + total);
}

function eliminarFile(eliminar){
    if(eliminar==true){
        $("div[id^='delete-']").addClass('oculto');
    }else
        $("div[id^='delete-']").removeClass('oculto');

}

function calcularComisionCancelacionTOB(monto,codemp,cuenta,mismoDia,codpais){
    var url = urlTransfersCalcularComision;
    var param={};
    var jsonTransfers=[];

    jsonTransfers[0] = cuenta;
    jsonTransfers[1] = monto;
    jsonTransfers[2] = "TEO";
    jsonTransfers[3] = codemp;
    jsonTransfers[4] = mismoDia;
    jsonTransfers[5] = codpais;

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,calcularComisionCancelacionTOBSuccess,null,null);
}

function calcularComisionCancelacionTOBSuccess(originalRequest) {
    var result = originalRequest;
    var comisionAux = result.comisionCalculada;
    var montoComision="";
    var mensaje="";

    montoComision = $("#Accounts option:selected").attr("extra2") - comisionAux;

    if (montoComision <= "0"){
        if (idioma == "_us_en")
            mensaje = "You cannot cancel this product because the amount to be transferred has to be greater than 0.";
        else
            mensaje = "No puede realizar la cancelación de este producto debido a que el monto a transferir tiene que ser mayor a 0";
        popupAlert(mensaje)
        $("#productCancelationTOB").prop('checked',false);
        montoTOB = $("#AmmountAI").val("");
        $("#cancelarProductoMostrarTOB").hide();
    }else{
        $("#montoComisionMostrar").show();

        $("#montoComision").html(comisionAux + " " +$("#Accounts option:selected").attr("extra1") );

        montoTOB = $("#AmmountAI").val(montoComision);
    }
  //  console.log(montoComision);
}
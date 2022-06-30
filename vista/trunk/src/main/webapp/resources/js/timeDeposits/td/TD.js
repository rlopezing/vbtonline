var urlTimeDepositsCargar="TimeDeposits_cargarTimeDeposits.action";
var urlLlenarSolicitudTD="TimeDeposits_llenarSolicitudTD.action";
var urlcalcularTD="TimeDeposits_calcularTasaTD.action";
var urlcalcularminTD = "TimeDeposits_validarParametrosPersonalesTD.action";

var tasa;
var tipoPlazo;
var pMinBalance = "";
var mensaje2 ="";

$(document).ready(function() {

    $("#plazosPref").hide();

    $("#valorTasaTimeDeposits").hide();

    $("#buscar_tipoTDC").change(function (){
       if ($("#buscar_tipoTDC option:selected").val()=="P"){
           tipoPlazo="PREFERENCIAL";
           $("#plazosPref").show();
           $("#plazosNor").hide();
       }else{
           tipoPlazo="NORMAL";
           $("#plazosPref").hide();
           $("#plazosNor").show();
       }
    });

    $(".calTasaTD").change(function (){
        var tipoTDLleno=$("#buscar_tipoTDC option:selected").attr("extra");
        var cuentaTDLleno=$("#buscar_productoDebitoTD option:selected").val();
        var plazoTDLleno="";
        var montoAux=$("#Monto_TD").val();
        var monedaTDLleno=$("#buscar_productoDebitoTD option:selected").attr("extra1");
        var montoTDLleno="";
        var tasfa;

        if (tipoPlazo!="PREFERENCIAL"){
            plazoTDLleno = $("#buscar_Plazo_TDC option:selected").attr("extra");
        }else{
            plazoTDLleno = $("#buscar_Plazo_TDC2 option:selected").attr("extra");
        }


        montoTDLleno=(montoAux).replace(/,/g,'t');
        montoTDLleno=(montoTDLleno).replace(/\./g,',');
        montoTDLleno=(montoTDLleno).replace(/t/g,'');

        if ((tipoTDLleno != null) & (cuentaTDLleno != null) & (plazoTDLleno != null) & (montoTDLleno > "0.00")) {
            calculoTasaTDJSONData(tipoTDLleno, cuentaTDLleno, plazoTDLleno, montoTDLleno, monedaTDLleno);

        }
    });

    $("#TAG_TD_aceptar").click(function(){
        var mensaje="";
        var texto= "";
        var texto1= "";
        var texto2= "";
        var texto3= "";
        var texto4= "";
        var montolimite=$("#buscar_tipoTDC option:selected").attr("extra2");
        var maximoCuenta=$("#buscar_productoDebitoTD option:selected").attr("extra");
        var tipoTDLleno=$("#buscar_tipoTDC option:selected").attr("extra");
        var cuentaTDLleno=$("#buscar_productoDebitoTD option:selected").val();
        var plazoTDLleno="";
        var montoAux=$("#Monto_TD").val();
        var monedaTDLleno=$("#buscar_productoDebitoTD option:selected").attr("extra1");
        var montoTDLleno="";
        var totalCuenta=$("#buscar_productoDebitoTD option:selected").attr("extra4");

        if (tipoPlazo!="PREFERENCIAL"){
            plazoTDLleno = $("#buscar_Plazo_TDC option:selected").attr("extra");
        }else{
            plazoTDLleno = $("#buscar_Plazo_TDC2 option:selected").attr("extra");
        }

        montoTDLleno=(montoAux).replace(/,/g,'t');
        montoTDLleno=(montoTDLleno).replace(/\./g,',');
        montoTDLleno=(montoTDLleno).replace(/t/g,'');

        if (tipoPlazo!="PREFERENCIAL"){
            $(".VerificarTD").each(function(){

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
            $(".VerificarTD2").each(function(){

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


        montoMinimoCuenta ();

        if ((tipoTDLleno != null) & (cuentaTDLleno != null) & (plazoTDLleno != null) & (montoTDLleno > "0.00")){

            var montoResultado = (totalCuenta) - (Number(unFormatCurrency(montoTDLleno,',').replace(',','.'))) ;
            //Validacion Monto minimo dejado en la cuenta

            if (  montoResultado  < pMinBalance ){
                if (idioma == "_us_en")
                    mensaje = mensaje + "Your account can't have less than:" + " " + Number(unFormatCurrency(pMinBalance,',').replace(',','.')) + " " + monedaTDLleno + ". ";
                else
                    mensaje = mensaje + "El Monto de su cuenta no debe quedar menor a:" + " " + Number(unFormatCurrency(pMinBalance,',').replace(',','.')) + " " + monedaTDLleno + ". ";
            }else{
                //Validacion Monto minimo del tipo de TD
                if (montoTDLleno >= montolimite) {
                    mensaje="";
                }else{
                    if (idioma == "_us_en")
                        mensaje = mensaje + "The Amount must be greater than 100.000,00";
                    else
                        mensaje = mensaje + "El Monto debe ser mayor a 100.000,00.";
                }
            }


            //Validacion Monto maximo de la cuenta
            if (Number(unFormatCurrency(montoTDLleno,',').replace(',','.')) > maximoCuenta){
                if (idioma == "_us_en")
                    mensaje = mensaje + "The Amount should not be greater than the one you have in your account" + " " + maximoCuenta + " " + monedaTDLleno + " ";
                else
                    mensaje = mensaje + "El Monto no debe ser mayor al que posee en su cuenta." + " " + maximoCuenta + " " + monedaTDLleno + " " ;
            }

            //Disclaimer
            if(mensaje==""){
                if(idioma=="_us_en") {
                    texto = "Type: "+$("#buscar_tipoTDC option:selected").attr("extra");
                    texto1 ="Debit Product: " +$("#buscar_productoDebitoTD option:selected").attr("extra2");
                    texto2 ="Time Limit: " + plazoTDLleno;
                    texto3 ="Amount: " +$("#Monto_TD").val()+" "+$("#buscar_productoDebitoTD option:selected").attr("extra1");
                    texto4 ="Rate: " + tasa;

                    $( "#TDdisclaimer p").html("Check the data <br><p align='center'><table><tr><td><b>"+texto+"</b></td><tr><td><b>"+texto1+"</b></td><tr><td><b>"+texto2+"</b></td><tr><td><b>"+texto3+"</b></td></tr><tr><td><b>"+texto4+"</b></td></tr></table></p>"  );
                    $( "#TDdisclaimer" ).dialog({
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
                            "Cancel": function() {
                                $("#lastname2").addClass("error_campo");
                                eliminarFile(false);
                                $( this ).dialog( "close" );
                            },
                            "Continue": function() {
                                llenarSolTDJSONData(tipoTDLleno, cuentaTDLleno, plazoTDLleno, montoTDLleno, monedaTDLleno);
                                $(this).dialog("close");
                          /*      if (mensaje2 == "") {
                                    popupAlert("The request to open the Time Deposit has been sent successfully. Your executive / advisor will contact you.", "Accept");
                                    $("#aperturaTDForm .blankSelectTD").each(function () {
                                        this.value = "-2";
                                        if ($("#" + this.id).hasClass("error_campo"))
                                            $("#" + this.id).removeClass("error_campo");
                                    });
                                    $("#aperturaTDForm .blankTD").each(function () {
                                        this.value = "";
                                        if ($("#" + this.id).hasClass("error_campo"))
                                            $("#" + this.id).removeClass("error_campo");
                                    });
                                    infoPaginaTDJSONData();
                                    // $("#valorTasaTimeDeposits").hide();
                                    $(this).dialog("close");
                                }else {
                                    popupAlert(mensaje2);
                                }*/
                            }
                        }
                    });
                } else {
                    texto = "Tipo: "+$("#buscar_tipoTDC option:selected").attr("extra");
                    texto1 ="Producto Débito: " +$("#buscar_productoDebitoTD option:selected").attr("extra2");
                    texto2 ="Plazo: " + plazoTDLleno;
                    texto3 ="Monto: " +$("#Monto_TD").val()+$("#buscar_productoDebitoTD option:selected").attr("extra1");
                    texto4 ="Tasa: " + tasa

                    $( "#TDdisclaimer p").html("Verifique los datos ingresados<br><p align='center'><table><tr><td><b>"+texto+"</b></td><tr><td><b>"+texto1+"</b></td><tr><td><b>"+texto2+"</b></td><tr><td><b>"+texto3+"</b></td></tr><tr><td><b>"+texto4+"</b></td></tr></table></p>"  );
                    $( "#TDdisclaimer" ).dialog({
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
                                $("#lastname2").addClass("error_campo");
                                eliminarFile(false);
                                $(this).dialog("close");
                            },
                            "Continue": function () {
                                llenarSolTDJSONData(tipoTDLleno, cuentaTDLleno, plazoTDLleno, montoTDLleno, monedaTDLleno);
                                $(this).dialog("close");
                            /*    if (mensaje2 == ""){
                                    popupAlert("La solicitud de apertura de la colocación ha sido enviada con exito. Será contactado por su ejecutivo/asesor.", "Aceptar");
                                $("#aperturaTDForm .blankSelectTD").each(function () {
                                    this.value = "-2";
                                    if ($("#" + this.id).hasClass("error_campo"))
                                        $("#" + this.id).removeClass("error_campo");
                                });
                                $("#aperturaTDForm .blankTD").each(function () {
                                    this.value = "";
                                    if ($("#" + this.id).hasClass("error_campo"))
                                        $("#" + this.id).removeClass("error_campo");
                                });

                                infoPaginaTDJSONData();
                                //$("#valorTasaTimeDeposits").hide();
                                $(this).dialog("close");
                            }else {
                                    popupAlert(mensaje2);
                                }*/
                         }
                        }
                    });
                }
            }else{
                popupAlert(mensaje);
            }
        }else {

            if (idioma == "_us_en"){
                mensaje = "<br>You must fill in all the required fields (*)</br>" + "<br> </br>" + mensaje;
                popupAlert(mensaje);
            }else{
                mensaje = "<br>Debe llenar todos los campos obligatorios (*)</br>" + "<br> </br>" + mensaje;
                popupAlert(mensaje);
            }

        }
    });

    $("#TAG_TD_cancelar").click(function(){
        $("#aperturaTDForm .blankSelectTD").each(function(){
            this.value="-2";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
        $("#aperturaTDForm .blankTD").each(function(){
            this.value="";
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });

        $("#valorTasaTimeDeposits").hide();
    });

});

function infoPaginaTDJSONData() {
    $("#div_carga_espera").removeClass("oculto");
    var url = urlTimeDepositsCargar;
    var param = {};
    var jsonTransfers = [];
    jsonTransfers[0] = "OTHER";

    param.jsonTransfers = JSON.stringify({"parametros": jsonTransfers});

    sendServiceJSON(url, param, infoPaginaTDSuccess, null, null);
}

function infoPaginaTDSuccess(originalRequest){
    var resultado = originalRequest;
    var cuentas = resultado.cuentasTD;
    var tipoTimeDepo = resultado.tiposTD;
    var plazos = resultado.plazosTD;
    var plazos_p = resultado.plazosPrefTD;
    var tituloTD = resultado.aperturaTD;
    var tasaTD = resultado.tasaTD;
    var columna = resultado.contenidoTabla_culumt;
    var infotabla = resultado.contenidoTabla_info;

    var selected= "-2";

    if(tipoTimeDepo!=null) {
        if (idioma == "_us_en")
            cargar_selectCuenta("buscar_tipoTDC", tipoTimeDepo, "Select", "-2", selected);
        else
            cargar_selectCuenta("buscar_tipoTDC", tipoTimeDepo, "Seleccione", "-2", selected);
    }
    if(cuentas!=null) {
        if (idioma == "_us_en")
            cargar_selectCuenta("buscar_productoDebitoTD", cuentas, "Select", "-2", selected);
        else
            cargar_selectCuenta("buscar_productoDebitoTD", cuentas, "Seleccione", "-2", selected);
    }
    if(plazos!=null) {

        if (idioma == "_us_en")
            cargar_timeDeposits("buscar_Plazo_TDC", plazos, "Select", "-2", selected);
        else
            cargar_timeDeposits("buscar_Plazo_TDC", plazos, "Seleccione", "-2", selected);
    }
    if(plazos_p!=null) {
       if (idioma == "_us_en")
           cargar_timeDeposits("buscar_Plazo_TDC2", plazos_p, "Select", "-2", selected);
       else
           cargar_timeDeposits("buscar_Plazo_TDC2", plazos_p, "Seleccione", "-2", selected);


    }
    $("#div_carga_espera").addClass("oculto");
    if(tituloTD!=null){
        // crearTabla("div_datos_apertura_td", "resumenAperturaTD", columna, infotabla);
        crearTablaV2("resumenAperturaTD", columna, infotabla,"",false);

/*
        var oTable = $('#resumenAperturaTD').dataTable({
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "right" },
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        })
*/

        var oTable = $('#resumenAperturaTD').dataTable({
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "right" },
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        })

    }
    montoMinimoCuenta();
   // console.log(resultado);
}

function llenarSolTDJSONData (tipoTDLleno,cuentaTDLleno,plazoTDLleno,montoTDLleno,monedaTDLleno) {
    $("#div_carga_espera").removeClass("oculto");
    var url = urlLlenarSolicitudTD;
    var param={};
    var jsonTD=[];
 //   var tasa;
    var i = 0;

    jsonTD[0]=$("#buscar_tipoTDC option:selected").val();
    jsonTD[1]=cuentaTDLleno.split(" |")[0];
    jsonTD[2]=plazoTDLleno;
    jsonTD[3]=montoTDLleno;
    jsonTD[4]=monedaTDLleno;
    jsonTD[5]=tasa;
    jsonTD[6]=cuentaTDLleno.split("| ")[1];
    jsonTD[7]=$("#buscar_tipoTDC option:selected").attr("extra");

    param.jsonTD=JSON.stringify({"parametros":jsonTD});

    sendServiceJSON(url,param,llenarSolTDDataSuccess,null,null);

}

function llenarSolTDDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje3 = result.mensaje;
    var exitoso= result.resumenTD;
    var mensaje = "";

    $("#div_carga_espera").addClass("oculto");
    if (exitoso != "OK"){
        if (idioma=="_us_en"){
            mensaje = "An error has occurred, please try again or contact your Financial Advisor or Account Executive.";
            mensaje2 = "An error has occurred, please try again or contact your Financial Advisor or Account Executive.";
        }else{
            mensaje = "Ha ocurrido un error, intente de nuevo, si el error persiste por favor contacte a su Asesor o Ejecutivo.";
            mensaje2 = "Ha ocurrido un error, intente de nuevo, si el error persiste por favor contacte a su Asesor o Ejecutivo.";
        }

        popupAlert(mensaje);
    }else{
        if (idioma=="_us_en"){
            popupAlert("The request to open the Time Deposit has been sent successfully. Your executive / advisor will contact you.", "Accept");

        }else{
            popupAlert("La solicitud de apertura del Depósito a Plazo Fijo ha sido enviada con éxito. Su ejecutivo/asesor se pondrá en contacto con usted.", "Aceptar");
        }
        $("#aperturaTDForm .blankSelectTD").each(function () {
            this.value = "-2";
            if ($("#" + this.id).hasClass("error_campo"))
                $("#" + this.id).removeClass("error_campo");
        });
        $("#aperturaTDForm .blankTD").each(function () {
            this.value = "";
            if ($("#" + this.id).hasClass("error_campo"))
                $("#" + this.id).removeClass("error_campo");
        });
        infoPaginaTDJSONData();
        $("#valorTasaTimeDeposits").hide();
        $(this).dialog("close");
    }

   // console.log(result);

}

function calculoTasaTDJSONData (tipoTDLleno,cuentaTDLleno,plazoTDLleno,montoTDLleno,monedaTDLleno) {
    var url = urlcalcularTD;
    var param={};
    var jsonTD=[];
    var i = 0;

    jsonTD[0]=$("#buscar_tipoTDC option:selected").val();
    jsonTD[1]=cuentaTDLleno.split(" |")[0];
    jsonTD[2]=plazoTDLleno;
    jsonTD[3]=montoTDLleno;
    jsonTD[4]=monedaTDLleno;

    param.jsonTD=JSON.stringify({"parametros":jsonTD});

    sendServiceJSON(url,param,llenarTasaTDDataSuccess,null,null);

}

function llenarTasaTDDataSuccess(originalRequest){
    var result = originalRequest;
    var RE = /^\d*(\.\d{1})?\d{0,1}$/
    var tasaNew = result.tasaTDFill;

    tasa = tasaNew;

    $("#valorTasaTimeDeposits").attr("style","display:block;");
    $("#valorTasaTimeDeposits").html(tasa);

  //  console.log(result);


}

function montoMinimoCuenta () {
    var url = urlcalcularminTD;
    var param={};
    var jsonTD=[];
    var i = 0;
    var montoaux2 = "";

    montoaux2=$("#Monto_TD").val().replace(/,/g,'t');
    montoaux2=(montoaux2).replace(/\./g,',');
    montoaux2=(montoaux2).replace(/t/g,'');

    jsonTD[0]="";

    param.jsonTD=JSON.stringify({"parametros":jsonTD});

    sendServiceJSON(url,param,montoMinimoCuentaSuccess,null,null);

}

function montoMinimoCuentaSuccess(originalRequest){
    var result = originalRequest;
    var montoVal = result.minBalance;

    pMinBalance = montoVal;



    console.log(montoVal);


}

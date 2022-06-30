var urlParametrosPersonalesCargarBMLA="Transfers_cargarTranEntreMisCuentas.action";
var urlParametrosPersonalesCargarCamposBMLA="DesingBank_cargarParametrosPersonales.action";
var urlParametrosPersonalesGuardarCamposBMLA="DesingBank_guardarParametrosPersonales.action";



var idioma="";
var parametrosBase="";
$(document).ready(function(){

   $("#btn_PP_cancelar").click(function(){
       seleccionarOpcionMenu("home");
   });

    $("#btn_PP_cancelar").click(function(){
        $("#div_parametrosPersonales").removeClass("oculto");
        $("#metodosParametros").addClass("oculto");
        $("#btn_cambiarMtdClave").click();

    });

    $("#btn_PP_validar_clave").click(function(){
        var mensaje="";
        var elemento="";

        switch(transferencia_MetodoValidar_Clave){
            case "email":
                elemento="#pwdClaveConfirmTransfer_TOB";
                break;
            case "TarjetaCoordenada":
                elemento="#pwdClaveConfirmTransfer_TOB";
            case "sms":
                elemento="#pwdClaveConfirmSMS";
                break;
        }


        if($(elemento).val()==""){
            if(idioma=="_us_en")
                mensaje = mensaje + "Enter your Transaction Key ";
            else
                mensaje = mensaje + "Ingrese su Clave de Operaciones >";
            $(elemento).addClass("error_campo");
        }else
            $(elemento).removeClass("error_campo");

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            callValidationMethods();
        }





    });

    $("#btn_PP_aceptar").click(function(){

        var mensaje ="";
        $(".requerido_PP").each(function(){

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


        var mensaje="";
        var parametrosPersonales={};
        var parametrosPersonales2={};
        var parametrosValidacion={};



        parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PP").val();
        //parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PP").val().replace(".","");
        parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PP").val().replace(/\./g,"");
        parametrosPersonales.vbt_mmaxtd= parametrosPersonales.vbt_mmaxtd.replace(",",".");
        //parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PP").val().replace(".","");
        parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PP").val().replace(/\./g,"");
        parametrosPersonales.vbt_mminto= parametrosPersonales.vbt_mminto.replace(",",".");
       //parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PP").val().replace(".","");
        parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PP").val().replace(/\./g,"");
        parametrosPersonales.vbt_mmto= parametrosPersonales.vbt_mmto.replace(",",".");

        parametrosPersonales.ex_nmtd= $("#PP_numeroMaxTransDiarias_OB").val();
       //parametrosPersonales.ex_mmtd= $("#PP_montoMaxTransDiarias_OB").val().replace(".","");
        parametrosPersonales.ex_mmtd= $("#PP_montoMaxTransDiarias_OB").val().replace(/\./g,"");
        parametrosPersonales.ex_mmtd= parametrosPersonales.ex_mmtd.replace(",",".");
        //parametrosPersonales.ex_mminto= $("#PP_montoMinTransOpe_OB").val().replace(".","");
        parametrosPersonales.ex_mminto= $("#PP_montoMinTransOpe_OB").val().replace(/\./g,"");
        parametrosPersonales.ex_mminto= parametrosPersonales.ex_mminto.replace(",",".");
       // parametrosPersonales.ex_mmto= $("#PP_montoMaxTransOpe_OB").val().replace(".","");
        parametrosPersonales.ex_mmto= $("#PP_montoMaxTransOpe_OB").val().replace(/\./g,"");
        parametrosPersonales.ex_mmto= parametrosPersonales.ex_mmto.replace(",",".");

        if(parseInt(parametros.vbt_nmtd) <= parseInt(parametrosBase.vbt_nmtd)){
            parametrosValidacion.vbt_nmtd = parametrosBase.vbt_nmtd;
        }else{
            parametrosValidacion.vbt_nmtd = parametros.vbt_nmtd;
        }

        if(parseFloat(formatearMonto(parametros.vbt_mmaxtd)) <= parseFloat(formatearMonto(parametrosBase.vbt_mmaxtd))){
            parametrosValidacion.vbt_mmaxtd = parametrosBase.vbt_mmaxtd;
        }else{
            parametrosValidacion.vbt_mmaxtd = parametros.vbt_mmaxtd;
        }

        if(parseFloat(formatearMonto(parametros.vbt_mminto)) >= parseFloat(formatearMonto(parametrosBase.vbt_mminto))){
                parametrosValidacion.vbt_mminto = parametrosBase.vbt_mminto;
        }else{
            parametrosValidacion.vbt_mminto = parametros.vbt_mminto;
        }

        if(parseFloat(formatearMonto(parametros.vbt_mmto)) <= parseFloat(formatearMonto(parametrosBase.vbt_mmto))){
            parametrosValidacion.vbt_mmto = parametrosBase.vbt_mmto;
        }else{
            parametrosValidacion.vbt_mmto = parametros.vbt_mmto;
        }

        if(parseFloat(formatearMonto(parametros.ex_mminto)) >= parseFloat(formatearMonto(parametrosBase.ex_mminto))){
                parametrosValidacion.ex_mminto = parametrosBase.ex_mminto;
        }else{
            parametrosValidacion.ex_mminto = parametros.ex_mminto;
        }

        if(parseFloat(formatearMonto(parametros.ex_mmtd)) <= parseFloat(formatearMonto(parametrosBase.ex_mmtd))){
            parametrosValidacion.ex_mmtd = parametrosBase.ex_mmtd;
        }else{
            parametrosValidacion.ex_mmtd = parametros.ex_mmtd;
        }

        if(parseFloat(formatearMonto(parametros.ex_mmto)) <= parseFloat(formatearMonto(parametrosBase.ex_mmto))){
            parametrosValidacion.ex_mmto = parametrosBase.ex_mmto;
        }else{
            parametrosValidacion.ex_mmto = parametros.ex_mmto;
        }

        if(parseInt(parametros.ex_nmtd) <= parseInt(parametrosBase.ex_nmtd)){
            parametrosValidacion.ex_nmtd = parametrosBase.ex_nmtd;
        }else{
            parametrosValidacion.ex_nmtd = parametros.ex_nmtd;
        }

        //parametrosPersonales2.vbt_mmaxtd =  parametrosBase.vbt_mmaxtd.replace(".","");
        //parametrosPersonales2.vbt_mmaxtd =  parametrosBase.vbt_mmaxtd.replace(/\./g,"");
        parametrosPersonales2.vbt_mmaxtd =  parametrosValidacion.vbt_mmaxtd.replace(/\./g,"");
        parametrosPersonales2.vbt_mmaxtd =  parametrosPersonales2.vbt_mmaxtd.replace(",",".");

        //parametrosPersonales2.vbt_mminto =  parametrosBase.vbt_mminto.replace(".","");
        //parametrosPersonales2.vbt_mminto =  parametrosBase.vbt_mminto.replace(/\./g,"");
        parametrosPersonales2.vbt_mminto =  parametrosValidacion.vbt_mminto.replace(/\./g,"");
        parametrosPersonales2.vbt_mminto =  parametrosPersonales2.vbt_mminto.replace(",",".");

        //parametrosPersonales2.vbt_mmto =  parametrosBase.vbt_mmto.replace(".","");
        //parametrosPersonales2.vbt_mmto =  parametrosBase.vbt_mmto.replace(/\./g,"");
        parametrosPersonales2.vbt_mmto =  parametrosValidacion.vbt_mmto.replace(/\./g,"");
        parametrosPersonales2.vbt_mmto =  parametrosPersonales2.vbt_mmto.replace(",",".");

        //parametrosPersonales2.ex_mminto =  parametrosBase.ex_mminto.replace(".","");
        //parametrosPersonales2.ex_mminto =  parametrosBase.ex_mminto.replace(/\./g,"");
        parametrosPersonales2.ex_mminto =  parametrosValidacion.ex_mminto.replace(/\./g,"");
        parametrosPersonales2.ex_mminto =  parametrosPersonales2.ex_mminto.replace(",",".");

        //parametrosPersonales2.ex_mmtd =  parametrosBase.ex_mmtd.replace(".","");
        //parametrosPersonales2.ex_mmtd =  parametrosBase.ex_mmtd.replace(/\./g,"");
        parametrosPersonales2.ex_mmtd =  parametrosValidacion.ex_mmtd.replace(/\./g,"");
        parametrosPersonales2.ex_mmtd =  parametrosPersonales2.ex_mmtd.replace(",",".");

        //parametrosPersonales2.ex_mmto =  parametrosBase.ex_mmto.replace(".","");
        //parametrosPersonales2.ex_mmto =  parametrosBase.ex_mmto.replace(/\./g,"");
        parametrosPersonales2.ex_mmto =  parametrosValidacion.ex_mmto.replace(/\./g,"");
        parametrosPersonales2.ex_mmto =  parametrosPersonales2.ex_mmto.replace(",",".");

        //parametrosPersonales2.ex_nmtd =  parametrosBase.ex_nmtd;
        parametrosPersonales2.ex_nmtd =  parametrosValidacion.ex_nmtd;
        //parametrosPersonales2.vbt_nmtd =  parametrosBase.vbt_nmtd;
        parametrosPersonales2.vbt_nmtd =  parametrosValidacion.vbt_nmtd;

        // mensaje = mensaje + "parametrosValidacion.vbt_nmtd"+parametrosValidacion.vbt_nmtd+"<br>";
        // mensaje = mensaje + "parametrosPersonales.vbt_nmtd"+parametrosPersonales.vbt_nmtd+"<br>";
        // mensaje = mensaje + "parametrosPersonales2.vbt_nmtd"+parametrosPersonales2.vbt_nmtd+"<br>";



      if(parseInt(parametrosPersonales.vbt_nmtd) > parseInt(parametrosPersonales2.vbt_nmtd)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The maximum number of transfers exceeds the established daily, please enter an amount less than: "+parametrosBase.vbt_nmtd+"<br>";
                mensaje = mensaje + "The maximum number of transfers exceeds the established daily, please enter an amount less than: "+parametrosValidacion.vbt_nmtd+"<br>";
            else
                //mensaje = mensaje + "El n&uacute;mero m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.vbt_nmtd+"<br>";
                mensaje = mensaje + "El n&uacute;mero m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosValidacion.vbt_nmtd+"<br>";
            $("#numeroMaximoTransfDiarias_PP").addClass("error_campo");
        }else
            $("#numeroMaximoTransfDiarias_PP").removeClass("error_campo");
        if(parseFloat(parametrosPersonales.vbt_mmaxtd) > parseFloat(parametrosPersonales2.vbt_mmaxtd)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The maximum daily transfers exceeds the established, please enter an amount less than: "+parametrosBase.vbt_mmaxtd+"<br>";
                mensaje = mensaje + "The maximum daily transfers exceeds the established, please enter an amount less than: "+parametrosValidacion.vbt_mmaxtd+"<br>";
            else
                //mensaje = mensaje + "El monto m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.vbt_mmaxtd+"<br>";
                mensaje = mensaje + "El monto m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosValidacion.vbt_mmaxtd+"<br>";
            $("#montoMaxTransDiarias_PP").addClass("error_campo");
        }else
            $("#montoMaxTransDiarias_PP").removeClass("error_campo");
        if(parseFloat(parametrosPersonales.vbt_mminto) < parseFloat(parametrosPersonales2.vbt_mminto)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The minimum amount of the transfer exceeds the established operation, please enter an amount less than: "+parametrosBase.vbt_mminto+"<br>";
                mensaje = mensaje + "The minimum amount of the transfer exceeds the established operation, please enter an amount greater than: "+parametrosValidacion.vbt_mminto+"<br>";
            else
                //mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.vbt_mminto+"<br>";
                mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad mayor a: "+parametrosValidacion.vbt_mminto+"<br>";
            $("#montoMinTransOpe_PP").addClass("error_campo");
        }else
            $("#montoMinTransOpe_PP").removeClass("error_campo");
        if(parseFloat(parametrosPersonales.vbt_mmto) > parseFloat(parametrosPersonales2.vbt_mmto)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The maximum transfer amount per transaction exceeds the established, please enter an amount less than: "+parametrosBase.vbt_mmto+"<br>";
                mensaje = mensaje + "The maximum transfer amount per transaction exceeds the established, please enter an amount less than: "+parametrosValidacion.vbt_mmto+"<br>";
            else
                //mensaje = mensaje + "El monto m&aacute;ximo de transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.vbt_mmto+"<br>";
                mensaje = mensaje + "El monto m&aacute;ximo de transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosValidacion.vbt_mmto+"<br>";
            $("#montoMinTransDiarias_PP").addClass("error_campo");
        }else
            $("#montoMinTransDiarias_PP").removeClass("error_campo");







        if(parseInt(parametrosPersonales.ex_nmtd) > parseInt(parametrosPersonales2.ex_nmtd)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The maximum number of transfers exceeds the established external daily, please enter an amount less than: "+parametrosBase.ex_nmtd+"<br>";
                mensaje = mensaje + "The maximum number of transfers exceeds the established external daily, please enter an amount less than: "+parametrosValidacion.ex_nmtd+"<br>";
            else
                //mensaje = mensaje + "El n&uacute;mero m&aacute;ximo de transferencias diarias externas sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.ex_nmtd+"<br>";
                mensaje = mensaje + "El n&uacute;mero m&aacute;ximo de transferencias diarias externas sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosValidacion.ex_nmtd+"<br>";
            $("#PP_numeroMaxTransDiarias_OB").addClass("error_campo");
        }else
            $("#PP_numeroMaxTransDiarias_OB").removeClass("error_campo");
        if(parseFloat(parametrosPersonales.ex_mmtd) > parseFloat(parametrosPersonales2.ex_mmtd)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The maximum daily transfers exceeds the established, please enter an amount less than: "+parametrosBase.ex_mmtd+"<br>";
                mensaje = mensaje + "The maximum daily transfers exceeds the established, please enter an amount less than: "+parametrosValidacion.ex_mmtd+"<br>";
            else
                //mensaje = mensaje + "El monto m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.ex_mmtd+"<br>";
                mensaje = mensaje + "El monto m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosValidacion.ex_mmtd+"<br>";
            $("#PP_montoMaxTransDiarias_OB").addClass("error_campo");
        }else
            $("#PP_montoMaxTransDiarias_OB").removeClass("error_campo");
        if(parseFloat(parametrosPersonales.ex_mminto) < parseFloat(parametrosPersonales2.ex_mminto)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The minimum amount of the transfer exceeds the established operation, please enter an amount less than: "+parametrosBase.ex_mminto+"<br>";
                mensaje = mensaje + "The minimum amount of the transfer exceeds the established operation, please enter an amount greater than: "+parametrosValidacion.ex_mminto+"<br>";
            else
                //mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.ex_mminto+"<br>";
                mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad mayor a: "+parametrosValidacion.ex_mminto+"<br>";
            $("#PP_montoMinTransOpe_OB").addClass("error_campo");
        }else
            $("#PP_montoMinTransOpe_OB").removeClass("error_campo");
        if(parseFloat(parametrosPersonales.ex_mmto) > parseFloat(parametrosPersonales2.ex_mmto)){
            if(idioma=="_us_en")
                //mensaje = mensaje + "The maximum transfer amount per transaction exceeds the established, please enter an amount less than: "+parametrosBase.ex_mmto+"<br>";
                mensaje = mensaje + "The maximum transfer amount per transaction exceeds the established, please enter an amount less than: "+parametrosValidacion.ex_mmto+"<br>";
            else
                //mensaje = mensaje + "El monto m&aacute;ximo de transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBase.ex_mmto+"<br>";
                mensaje = mensaje + "El monto m&aacute;ximo de transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosValidacion.ex_mmto+"<br>";
            $("#PP_montoMaxTransOpe_OB").addClass("error_campo");
        }else
            $("#PP_montoMaxTransOpe_OB").removeClass("error_campo");


        //20/11/2017
        /*if(parseFloat(parametrosPersonales.vbt_mminto) > parseFloat(parametrosPersonales.vbt_mmaxtd)){
            mensaje = mensaje + "El monto m&iacute;nimo de  transferencias por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.vbt_mmaxtd+"<br>";
            $("#montoMinTransOpe_PP").addClass("error_campo");
        }else
            $("#montoMinTransOpe_PP").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.vbt_mminto) > parseFloat(parametrosPersonales.vbt_mmto)){
            mensaje = mensaje + "El monto m&iacute;nimo de transferencias por operaci&oacute;n sobrepasa monto m&aacute;ximo de transferencia por operaci&oacute;n, por favor introduzca una cantidad menor a: "+parametrosPersonales.vbt_mmaxtd+"<br>";
            $("#montoMinTransOpe_PP").addClass("error_campo");
        }else
            $("#montoMinTransOpe_PP").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.vbt_mmto) > parseFloat(parametrosPersonales.vbt_mmaxtd)){
            mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.vbt_mmaxtd+"<br>";
            $("#montoMinTransDiarias_PP").addClass("error_campo");
        }else
            $("#montoMinTransDiarias_PP").removeClass("error_campo");

        //validacion Otros Bncos




        if(parseFloat(parametrosPersonales.ex_mminto) > parseFloat(parametrosPersonales.ex_mmtd)){
            mensaje = mensaje + "El monto m&iacute;nimo de  transferencias por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.ex_mmtd+"<br>";
            $("#PP_montoMinTransOpe_OB").addClass("error_campo");
        }else
            $("#PP_montoMinTransOpe_OB").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.ex_mminto) > parseFloat(parametrosPersonales.ex_mmto)){
            mensaje = mensaje + "El monto m&iacute;nimo de transferencias por operaci&oacute;n sobrepasa monto m&aacute;ximo de transferencia por operaci&oacute;n, por favor introduzca una cantidad menor a: "+parametrosPersonales.ex_mmto+"<br>";
            $("#PP_montoMinTransOpe_OB").addClass("error_campo");
        }else
            $("#PP_montoMinTransOpe_OB").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.ex_mmto) > parseFloat(parametrosPersonales.ex_mmtd)){
            mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.ex_mmtd+"<br>";
            $("#PP_montoMinTransDiarias_OB").addClass("error_campo");
        }else
            $("#PP_montoMinTransDiarias_OB").removeClass("error_campo");
         */
        if(mensaje!=""){
            if(idioma=="_us_en")
                mensaje = mensaje + "For more information, please contact your Account Executive or Advisory";
            else
                mensaje = mensaje + "Para mayor información, por favor comuníquese con su Asesor o Ejecutivo de Cuentas.";
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{

            $("#btn_cambiarMtdClave").click();
            $("#btn_cambiarMtdClave").addClass("oculto");
            $("#btnAceptar").addClass("oculto");

            //Se llama a la pantalla de metodos de validacion
            mainValidationMethods("parametrosPersonales");


        }


   })

});

function formatearMonto(monto){
    var monto_formato = 0;
    monto_formato  =  monto.replace(/\./g,"");
    monto_formato  =  monto_formato.replace(",",".");
    return monto_formato;
}

function infoPaginaParametrosPersonalesJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlParametrosPersonalesCargarBMLA;
    var param={};
    var jsonTransfers=[];
    //Indiica que el origen es parametros personales
    jsonTransfers[0]= "PP";
    jsonTransfers[1]= "";
    jsonTransfers[2]= "";
    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,infoPaginaParametrosPersonalesSuccess,null,null);
}

function infoPaginaParametrosPersonalesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    idioma = result.idioma;

    valorCuentas = result.cuentasTOB;

    if(idioma=="_us_en")
      cargar_selectPersonal("Accounts_PP", valorCuentas,"Select","-2");
    else
      cargar_selectPersonal("Accounts_PP", valorCuentas,"Seleccione","-2");
    $("#btn_cambiarMtdClave").click();
    $("#btn_PP_cancelar_clave").click();
    infoPaginaParametrosPersonalesCamposJSONData();

}

function infoPaginaParametrosPersonalesCamposJSONData(){
    var url = urlParametrosPersonalesCargarCamposBMLA;
    var param={};

    sendServiceJSON(url,param,infoPaginaParametrosPersonalesCamposSuccess,null,null);
}

function infoPaginaParametrosPersonalesCamposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosPersonales;
    parametrosBase = result.parametrosPersonalesBase;


    $("#numeroMaximoTransfDiarias_PP").val(parametros.vbt_nmtd);
    $("#montoMaxTransDiarias_PP").val(parametros.vbt_mmaxtd);
    $("#montoMinTransOpe_PP").val(parametros.vbt_mminto);
    $("#montoMinTransDiarias_PP").val(parametros.vbt_mmto);

    $("#PP_numeroMaxTransDiarias_OB").val(parametros.ex_nmtd);
    $("#PP_montoMaxTransDiarias_OB").val(parametros.ex_mmtd);
    $("#PP_montoMinTransOpe_OB").val(parametros.ex_mminto);
    $("#PP_montoMaxTransOpe_OB").val(parametros.ex_mmto);

    if(parametros.account_num=="&nbsp;")
      $("#Accounts_PP").val("-2");
    else
      $("#Accounts_PP").val(parametros.account_num);

    $("#div_carga_espera").addClass("oculto");

}

function parametrosPersonalesActualizarJSONData(){
    var url = urlParametrosPersonalesGuardarCamposBMLA;
    var param={};
    var jsonDesingBank=[];
    var parametrosPersonales={};

    parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PP").val();
    parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PP").val();
    parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PP").val();
    parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PP").val();

    parametrosPersonales.ex_nmtd= $("#PP_numeroMaxTransDiarias_OB").val();
    parametrosPersonales.ex_mmtd= $("#PP_montoMaxTransDiarias_OB").val();
    parametrosPersonales.ex_mminto= $("#PP_montoMinTransOpe_OB").val();
    parametrosPersonales.ex_mmto= $("#PP_montoMaxTransOpe_OB").val();

    parametrosPersonales.account_num= $("#Accounts_PP").val();

    jsonDesingBank[0]=parametrosPersonales;

    param.jsonDesingBank=JSON.stringify({"parametrosPersonales":jsonDesingBank});
    sendServiceJSON(url,param,parametrosPersonalesActualizarSuccess,null,null);
}

function parametrosPersonalesActualizarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    idioma = result.idioma;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta =="OK"){
        if(idioma =="_us_en")
         alert("Successful upgrade");
        else
         alert("Actualizaci\u00f3n exitosa");
    }else{
        if(idioma =="_us_en")
            alert("Update Failed");
        else
            alert("Actualizaci\u00f3n fallida");
    }





    function ValidarClaveSMSTransferJSONData(){

    }

}







var urlParametrosPersonalesCargarGlobales="BackOffice_cargarParametrosGlobales.action";
var urlParametrosPersonalesGuardarBO="BackOffice_guardarParametrosPersonalesBO.action";
var PrimeraVezTipo="SI";

$(document).ready(function(){

    $("#btn_PG_cancelar").click(function(){
        seleccionarOpcionMenu("home");
    });


    $("#btn_PG_aceptar").click(function(){

        var mensaje ="";
        $(".requerido_PG").each(function(){

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

        $(".requerido_PG").each(function(){

            if(parseFloat($("#"+this.id).val())=="0" || parseFloat($("#"+this.id).val())=="0,00"){
                    mensaje=mensaje+ this.title+" debe ser mayor a cero<br>";
                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });


        if(mensaje == ""){
            var parametrosPersonales={};
            var parametrosPersonales2={};




            parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PG").val();
            parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PG").val().replace(/\./g,"");
            parametrosPersonales.vbt_mmaxtd= parametrosPersonales.vbt_mmaxtd.replace(",",".");
            parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PG").val().replace(/\./g,"");
            parametrosPersonales.vbt_mminto= parametrosPersonales.vbt_mminto.replace(",",".");
            parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PG").val().replace(/\./g,"");
            parametrosPersonales.vbt_mmto= parametrosPersonales.vbt_mmto.replace(",",".");

            parametrosPersonales.ex_nmtd= $("#PG_numeroMaxTransDiarias_OB").val();
            parametrosPersonales.ex_mmtd= $("#PG_montoMaxTransDiarias_OB").val().replace(/\./g,"");
            parametrosPersonales.ex_mmtd= parametrosPersonales.ex_mmtd.replace(",",".");
            parametrosPersonales.ex_mminto= $("#PG_montoMinTransOpe_OB").val().replace(/\./g,"");
            parametrosPersonales.ex_mminto= parametrosPersonales.ex_mminto.replace(",",".");
            parametrosPersonales.ex_mmto= $("#PG_montoMaxTransOpe_OB").val().replace(/\./g,"");
            parametrosPersonales.ex_mmto= parametrosPersonales.ex_mmto.replace(",",".");
            parametrosPersonales.minimun_balance= $("#PG_saldoMinimoCuenta").val().replace(/\./g,"");
            parametrosPersonales.minimun_balance= parametrosPersonales.minimun_balance.replace(",",".");

//            parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PG").val();
//            parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PG").val().replace(".","");
//            parametrosPersonales.vbt_mmaxtd= parametrosPersonales.vbt_mmaxtd.replace(",",".");
//            parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PG").val().replace(".","");
//            parametrosPersonales.vbt_mminto= parametrosPersonales.vbt_mminto.replace(",",".");
//            parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PG").val().replace(".","");
//            parametrosPersonales.vbt_mmto= parametrosPersonales.vbt_mmto.replace(",",".");
//
//            parametrosPersonales.ex_nmtd= $("#PG_numeroMaxTransDiarias_OB").val();
//            parametrosPersonales.ex_mmtd= $("#PG_montoMaxTransDiarias_OB").val().replace(".","");
//            parametrosPersonales.ex_mmtd= parametrosPersonales.ex_mmtd.replace(",",".");
//            parametrosPersonales.ex_mminto= $("#PG_montoMinTransOpe_OB").val().replace(".","");
//            parametrosPersonales.ex_mminto= parametrosPersonales.ex_mminto.replace(",",".");
//            parametrosPersonales.ex_mmto= $("#PG_montoMaxTransOpe_OB").val().replace(".","");
//            parametrosPersonales.ex_mmto= parametrosPersonales.ex_mmto.replace(",",".");

            if(parseInt(parametrosPersonales.vbt_mmto)> parseInt(parametrosPersonales.vbt_mmaxtd)){
                mensaje = mensaje + "El monto m&aacute;ximo de transferencias por operaci&oacute;n sobrepasa el monto m&aacute;ximo  de de transferencias diarias <br>";
                $("#montoMinTransDiarias_PG").addClass("error_campo");
            }else
                $("#montoMinTransDiarias_PG").removeClass("error_campo");

            if(parseFloat(parametrosPersonales.vbt_mminto) > parseFloat(parametrosPersonales.vbt_mmaxtd)){
                    mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo  de transferencias diarias<br>";
                $("#montoMinTransOpe_PG").addClass("error_campo");
            }else
                $("#montoMinTransOpe_PG").removeClass("error_campo");

            if(parseFloat( parametrosPersonales.vbt_mminto) > parseFloat(parametrosPersonales.vbt_mmto)){
                mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo  de transferencias por operacion<br>";
                $("#montoMinTransOpe_PG").addClass("error_campo");
            }else
                $("#montoMinTransOpe_PG").removeClass("error_campo");



            if(parseInt(parametrosPersonales.ex_mminto)> parseInt(parametrosPersonales.ex_mmto)){
                mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo  de transferencias por operacion<br>";
                $("#PG_montoMinTransOpe_OB").addClass("error_campo");
            }else
                $("#PG_montoMinTransOpe_OB").removeClass("error_campo");


            if(parseFloat(parametrosPersonales.ex_mminto) > parseFloat(parametrosPersonales.ex_mmto)){
                mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo  de de transferencias diarias<br>";
                $("#montoMaxTransOpe_OB").addClass("error_campo");
            }else
                $("#montoMaxTransOpe_OB").removeClass("error_campo");

            if(parseInt(parametrosPersonales.ex_mmto)> parseInt(parametrosPersonales.ex_mmtd)){
                mensaje = mensaje + "El monto m&aacute;ximo de transferencias por operaci&oacute;n sobrepasa el monto m&aacute;ximo  de de transferencias diarias <br>";
                $("#PG_montoMaxTransOpe_OB").addClass("error_campo");
            }else
                $("#PG_montoMaxTransOpe_OB").removeClass("error_campo");


        }


        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            parametrosGlobalesActualizarJSONData();
        }


    })

});

function infoPaginaParametrosGlobalesJSONData(){
    var url = urlParametrosPersonalesCargarGlobales;
    var param={};
    param.tipoParametro=$("#tipoParametroGlobal").val();
    sendServiceJSON_sinc(url,param,infoPaginaParametrosGlobalesSuccess,null,null);
}

function infoPaginaParametrosGlobalesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosGlobales;
    var parametrosTipos = result.parametrosSelect;
    if (PrimeraVezTipo=="SI"){
        cargar_selectPersonal2("tipoParametroGlobal",parametrosTipos);
        PrimeraVezTipo ="NO";
    }

    $("#numeroMaximoTransfDiarias_PG").val(parametros.vbt_nmtd);
    $("#montoMaxTransDiarias_PG").val(parametros.vbt_mmaxtd);
    $("#montoMinTransOpe_PG").val(parametros.vbt_mminto);
    $("#montoMinTransDiarias_PG").val(parametros.vbt_mmto);

    $("#PG_numeroMaxTransDiarias_OB").val(parametros.ex_nmtd);
    $("#PG_montoMaxTransDiarias_OB").val(parametros.ex_mmtd);
    $("#PG_montoMinTransOpe_OB").val(parametros.ex_mminto);
    $("#PG_montoMaxTransOpe_OB").val(parametros.ex_mmto);
    $("#PG_saldoMinimoCuenta").val(parametros.minimun_balance);
}


function parametrosGlobalesActualizarJSONData(){
    var url = urlParametrosPersonalesGuardarBO;
    var param={};
    var jsonParametrosGlobales=[];
    var parametrosPersonales={};


    parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PG").val();
    parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PG").val();
    parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PG").val();
    parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PG").val();

    parametrosPersonales.ex_nmtd= $("#PG_numeroMaxTransDiarias_OB").val();
    parametrosPersonales.ex_mmtd= $("#PG_montoMaxTransDiarias_OB").val();
    parametrosPersonales.ex_mminto= $("#PG_montoMinTransOpe_OB").val();
    parametrosPersonales.ex_mmto= $("#PG_montoMaxTransOpe_OB").val();
    parametrosPersonales.minimun_balance= $("#PG_saldoMinimoCuenta").val();
    parametrosPersonales.account_num= "";

    jsonParametrosGlobales[0]=parametrosPersonales;

    param.jsonParametrosGlobales=JSON.stringify({"parametrosGlobales":jsonParametrosGlobales});
    param.codigo="VBT";
    param.tipoParametro=$("#tipoParametroGlobal").val();
    sendServiceJSON(url,param,parametrosGlobalesActualizarSuccess,null,null);
}

function parametrosGlobalesActualizarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;


    if(respuesta =="OK")
        alert("Actualizaci\u00f3n exitosa");
    else
         alert("Actualizaci\u00f3n fallida");




}


function cargarParametrosGlobalesBC(originalRequest){
  $("#div_carga_espera").removeClass("oculto");
    infoPaginaParametrosGlobalesJSONData();
  $("#div_carga_espera").addClass("oculto");
}








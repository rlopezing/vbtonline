var urlParametrosPersonalesCargarCamposBMLA_FC="FirmasConjuntas_cargarParametrosPersonalesFC.action";
var urlParametrosPersonalesGuardarCamposBMLA_FC="FirmasConjuntas_guardarParametrosPersonalesFC.action";



var idioma="";
var parametrosBaseFC="";
$(document).ready(function(){

   $("#btn_PP_cancelar_FC").click(function(){
       seleccionarOpcionMenu("home");
   });

    $("#btn_PP_aceptar_FC").click(function(){

        var mensaje ="";
        $(".requerido_PP_FC").each(function(){

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

        if(mensaje == ""){
            var parametrosPersonalesFC={};

            parametrosPersonalesFC.vbt_nmtd= $("#PP_numeroMaxTransDiarias_OB_FC").val();
            parametrosPersonalesFC.vbt_mmtd= $("#PP_montoMaxTransDiarias_OB_FC").val();
            parametrosPersonalesFC.vbt_mmto= $("#PP_montoMaxTransOpe_OB_FC").val();
            parametrosPersonalesFC.correo= $("#PP_envioCorreo_FC").val();

//            private Integer vbt_nmtd;
//            private String vbt_mmtd;
//            private String vbt_mmto;
//            private String correo;


            if(parseInt(parametrosPersonalesFC.vbt_nmtd) > parseInt(parametrosBaseFC.vbt_nmtd)){
                mensaje = mensaje + "El n&uacute;mero m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBaseFC.vbt_nmtd+"<br>";
                $("#PP_numeroMaxTransDiarias_OB_FC").addClass("error_campo");
            }else
                $("#PP_numeroMaxTransDiarias_OB_FC").removeClass("error_campo");

            if(parseFloat(parametrosPersonalesFC.vbt_mmtd) > parseFloat(parametrosBaseFC.vbt_mmtd)){
                mensaje = mensaje + "El monto m&aacute;ximo de transferencias diarias sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBaseFC.vbt_mmtd+"<br>";
                $("#PP_montoMaxTransDiarias_OB_FC").addClass("error_campo");
            }else
                $("#PP_montoMaxTransDiarias_OB_FC").removeClass("error_campo");

            if(parseFloat(parametrosPersonalesFC.vbt_mmto) > parseFloat(parametrosBaseFC.vbt_mmto)){
                mensaje = mensaje + "El monto m&aacute;ximo de transferencia por operaci&oacute;n sobrepasa lo establecido, por favor introduzca una cantidad menor a: "+parametrosBaseFC.vbt_mmto+"<br>";
                $("#PP_montoMaxTransOpe_PP_FC").addClass("error_campo");
            }else
                $("#PP_montoMaxTransOpe_PP_FC").removeClass("error_campo");

        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            parametrosPersonalesActualizarFCJSONData();
        }


   })

});


function infoPaginaParametrosPersonalesCamposFCJSONData(){
    var url = urlParametrosPersonalesCargarCamposBMLA_FC;
    var param={};

    sendServiceJSON(url,param,infoPaginaParametrosPersonalesCamposFCSuccess,null,null);
}


function infoPaginaParametrosPersonalesCamposFCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var parametrosFC = result.parametrosPersonalesFC;
    parametrosBaseFC = result.parametrosPersonalesBaseFC;


    $("#PP_numeroMaxTransDiarias_OB_FC").val(parametrosFC.vbt_nmtd);
    $("#PP_montoMaxTransDiarias_OB_FC").val(parametrosFC.vbt_mmtd);
    $("#PP_montoMaxTransOpe_OB_FC").val(parametrosFC.vbt_mmto);
    if(parametrosFC.correo=="SI"){
        $("#radioSi").attr("checked","checked");
        $("#radioNo").removeAttr('checked');
    }else{
        $("#radioSi").removeAttr('checked');
        $("#radioNo").attr("checked","checked");
    }



}


function parametrosPersonalesActualizarFCJSONData(){
    var url = urlParametrosPersonalesGuardarCamposBMLA_FC;
    var param={};
    var jsonFirmasConjuntas=[];
    var parametrosPersonalesFC={};



    parametrosPersonalesFC.vbt_nmtd= $("#PP_numeroMaxTransDiarias_OB_FC").val();
    parametrosPersonalesFC.vbt_mmtd= $("#PP_montoMaxTransDiarias_OB_FC").val();
    parametrosPersonalesFC.vbt_mmto= $("#PP_montoMaxTransOpe_OB_FC").val();
    parametrosPersonalesFC.correo= $('input:radio[name=PP_envioCorreo_FC]:checked').val();

    jsonFirmasConjuntas[0]=parametrosPersonalesFC;

    param.jsonFirmasConjuntas=JSON.stringify({"parametrosPersonalesFC":jsonFirmasConjuntas});
    sendServiceJSON(url,param,parametrosPersonalesActualizarFCSuccess,null,null);
}

function parametrosPersonalesActualizarFCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    idioma = result.idioma;

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



}







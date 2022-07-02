var urlCreditCardsCargar="CreditCards_cargar.action";

$(document).ready(function(){
    $("#alertaSeguridadTDC #comun_TAGTitleContinuarTDC,#comun_TAGTitleContinuarTDC1,#comun_TAGTitleContinuarTDC2,#comun_TAGTitleContinuarTDC3, #comun_TAGTitleContinuarTDC4").click(function(){
        console.log("cerrar tip");
        var campo = "";
        var campo = $("#alertaSeguridadTDC #pantalla").val();
        console.log("campo"+ campo);
        if(campo=="div_gestionReclamos"){
            window.open("../vbtonline/resources/documentos/tdc_forma_reclamo.xls",'XLS','');
        }else{
            var campo2 ="";
            campo2 = campo+"_alertaSeguridad";
            
        console.log("campo2"+ campo2);
            $("#"+campo2).attr("style", "display: none");

            $("#"+campo).attr("style", "display: block");
        }

        $("#TAG_INFO_FACTURAR").removeClass("oculto");
        $("#TAG_INFO_ESTADOS_TARJETAS").removeClass("oculto");
        $("#TAG_INFO_BLOQUEAR").removeClass("oculto");
        proximoDiaHabilJSONData();
    });
});


function cargarGestionReclamos(){
    //$("#marco_trabajo").css("height","950px");
    $("#alertaSeguridadTDC #pantalla").val("div_gestionReclamos");
    $("#TAG_INFO_ESTADOS_TARJETAS").addClass("oculto");

}








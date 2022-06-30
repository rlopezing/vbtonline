var urlCreditCardsCargar="CreditCards_cargar.action";

$(document).ready(function(){
      $("#alertaSeguridadTDC #comun_TAGTitleContinuarTDC").click(function(){
          var campo = "";
          var campo = $("#alertaSeguridadTDC #pantalla").val();
          if(campo=="div_gestionReclamos"){
              window.open("../vbtonline/resources/documentos/tdc_forma_reclamo.xls",'XLS','');
          }else{
              var campo2 ="";
              campo2 = campo+"_alertaSeguridad";
              $("#"+campo2).attr("style", "display: none");

              $("#"+campo).attr("style", "display: block");
          }



      });
});


function cargarGestionReclamos(){
    //$("#marco_trabajo").css("height","950px");
    $("#alertaSeguridadTDC #pantalla").val("div_gestionReclamos");
}








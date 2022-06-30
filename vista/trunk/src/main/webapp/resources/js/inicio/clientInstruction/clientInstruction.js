var urlLoadValues_ClientInstruction="ClientInstruction_loadValues.action";
var urlPDFClientInstruction="generatePdfClientInstruction.action";

$(document).ready(function(){

    //cargar listas de valores
    //loadValues_ClientInstructionJSONData();
    $("#ci_client").change(function(){

        var codperclicarta =  $("#ci_client").val().split("#")[0];
        var rif =  $("#ci_client").val().split("#")[1];
        $("#ci_id").val(rif);
        $("#ci_codperclicarta").val(codperclicarta);

    });
    $("#ci_transfer_type").change(function(){

        $("#ci_nm_transfer_type").val($("#ci_transfer_type option:selected").text());

    });

    //Generar PDF de carta de isntruccion
    $("#ClientInstruction_BTNConsultar").click(function(){
//
        var mensaje="";



        $("#div_clientInstruction .requerido_Client_Instruction").each(function(){
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



       if(mensaje!=""){
           // $("#estado_cuenta_div_tabla_consulta_BT").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{

           crearPDF_clientInstruction();
          //  detalleEdoCuentaJSONData();
          //  $("#estado_cuenta_imprimir").fadeIn("fast");

        }

    });

});

function crearPDF_clientInstruction(){

    //var url = '/vbtonline/reporter?command=PDFCuentasEdoCuenta&numeroCuenta=<%=strNumeroCuenta%>&codigoCartera=<%=strCodigoCartera%>&cmbMes=<%=strCmbMes%>&txtAño=<%=strTxtAño%>';

    var client =  $().crypt({method: "b64enc",source:$("#ci_client").get(0).value});
    var id =  $().crypt({method: "b64enc",source:$("#ci_id").val()});
    var transferType =  $().crypt({method: "b64enc",source:$("#ci_transfer_type").val()});
    var quantity =  $().crypt({method: "b64enc",source:$("#ci_quantity").val()});
    var vencimiento =  $().crypt({method: "b64enc",source:$("#ci_expiration").val()});
    var codperclicarta =  $().crypt({method: "b64enc",source:$("#ci_codperclicarta").val()});
    var descTipoTransf =  $().crypt({method: "b64enc",source:$("#ci_nm_transfer_type").val()});

   var urlPDFClientInstruction   = "generatePdfClientInstruction.action";
    urlPDFClientInstruction = urlPDFClientInstruction+"?client="+client+"&id="+id+"&transferType="+transferType+"&quantity="+quantity+"&vencimiento="+vencimiento+"&codperclicarta="+codperclicarta+"&descTipoTransf="+descTipoTransf;

    window.open(urlPDFClientInstruction,'PDF','');

}

/*Limpiar campos del formulario */
function cleanFields_ClientInstruction(){
    $("#div_clientInstruction .formulario_Client_Instruction").each(function(){
        this.val("");
    });

    $("#div_clientInstruction .selectFormulario_Client_Instruction").each(function(){
        this.value="-2";
    });
    $("#div_clientInstruction .inputFormulario_Client_Instruction").each(function(){
        this.value="";
    });
    $("#div_clientInstruction .spanFormulario_Client_Instruction").each(function(){
        this.innerHTML="";
    });
}

/*Invocar al servicio que retorna listas de valores del formulario */
function loadValues_ClientInstructionJSONData(){

    var url = urlLoadValues_ClientInstruction;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    sendServiceJSON(url,param,loadValues_ClientInstructionSuccess,null,null);
}

/*Cargar listas de valores en el formulario */
function loadValues_ClientInstructionSuccess(originalRequest){
    //                   this is the json return data


    var result = originalRequest;
    var clients = result.clients;
    var transferTypes = result.transferTypes;
    var quantities = result.maxCartas;
    var vencimientos = result.vencimientos;

    idioma = result.idioma;
    var codigo = result.codigo;


    if(codigo=="0" && clients!=null){
        if(idioma=="_us_en"){
            cargar_selectId("ci_client", clients,"Select","-2");
            cargar_selectId("ci_transfer_type", transferTypes,"Select","-2");
            cargar_selectId("ci_quantity", quantities,"Select","-2");
            cargar_selectId("ci_expiration", vencimientos,"Select","-2");
        } else  {
            cargar_selectId("ci_client", clients,"Seleccione","-2");
            cargar_selectId("ci_transfer_type", transferTypes,"Seleccione","-2");
            cargar_selectId("ci_quantity", quantities,"Seleccione","-2");
            cargar_selectId("ci_expiration", vencimientos,"Seleccione","-2");
        }
    }
}




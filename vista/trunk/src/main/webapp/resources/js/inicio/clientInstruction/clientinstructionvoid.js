var urlconsultarCartaDeInstruccion = "ClientInstruction_evaluarCartaDeInstruccion.action";
var urlanularCartaDeInstruccion = "ClientInstruction_anularCartaDeInstruccion.action";
var urlLoadValues_ClientInstructionVoid = "ClientInstruction_loadValues.action";
$(document).ready(function () {




    //cargar listas de valores
    //loadValues_ClientInstructionVoidJSONData();
    $("#ci_client_cancel").change(function(){

        var codperclicarta =  $("#ci_client_cancel").val().split("#")[0];
        var rif =  $("#ci_client_cancel").val().split("#")[1];
        $("#ci_id_cancel").val(rif);
        $("#ci_codperclicarta").val(codperclicarta);

    });
    $("#ci_transfer_type").change(function(){

        $("#ci_nm_transfer_type").val($("#ci_transfer_type option:selected").text());

    });

    //Validacion
    $("#instrucciones_cliente_consultar").click(function(){
//

        $("#div_cliente_anular").html("");
        $("#div_clientInstructionCancel2").attr("style","display:none");

        var mensaje="";

        $("#div_clientInstructionVoid .requerido_ACI").each(function(){
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


            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");

        }
        else  {
            evaluarCartaDeInstruccionJSONData();
        }
    });



    $("#instrucciones_cliente_anular").live('click', function () {

        if(idioma=="_us_en"){
            if(confirm("Are you sure you want to continue?")){
                anularCartaDeInstruccionJSONData();
                blanquearFormularios("div_clientInstructionVoid");
            }
        }else{
            if(confirm("Est\u00e1 seguro que desea continuar?")){
                anularCartaDeInstruccionJSONData();
                blanquearFormularios("div_clientInstructionVoid");
            }
        }
    });



});

function consultar_clientInstructionVoid(){

    //var url = '/vbtonline/reporter?command=PDFCuentasEdoCuenta&numeroCuenta=<%=strNumeroCuenta%>&codigoCartera=<%=strCodigoCartera%>&cmbMes=<%=strCmbMes%>&txtAño=<%=strTxtAño%>';

    var client         =  $().crypt({method: "b64enc",source:$("#ci_client_cancel").get(0).value});
    /* var id             =  $().crypt({method: "b64enc",source:$("#ci_id").val()});
     var transferType   =  $().crypt({method: "b64enc",source:$("#ci_transfer_type").val()});
     var quantity       =  $().crypt({method: "b64enc",source:$("#ci_quantity").val()});
     var vencimiento    =  $().crypt({method: "b64enc",source:$("#ci_expiration").val()});
     var codperclicarta =  $().crypt({method: "b64enc",source:$("#ci_codperclicarta").val()});
     var descTipoTransf =  $().crypt({method: "b64enc",source:$("#ci_nm_transfer_type").val()});      */

    var urlPDFClientInstructionVoid   = "generatePdfClientInstructionVoid.action";
    // alert("Ahora\nsigue la validación y mensajes");
    urlPDFClientInstructionVoid = urlPDFClientInstructionVoid+"?client="+client //+"&id="+id+"&transferType="+transferType+"&quantity="+quantity+"&vencimiento="+vencimiento+"&codperclicarta="+codperclicarta+"&descTipoTransf="+descTipoTransf;

    // window.open(urlPDFClientInstruction,'PDF','');

}

/*Limpiar campos del formulario */
function cleanFields_ClientInstructionVoid(){
    $("#div_clientInstructionVoid .formulario_Client_InstructionVoid").each(function(){
        this.val("");
    });

    $("#div_clientInstructionVoid .selectFormulario_Client_InstructionVoid").each(function(){
        this.value="-2";
    });
    $("#div_clientInstructionVoid .inputFormulario_Client_InstructionVoid").each(function(){
        this.value="";
    });
    $("#div_clientInstructionVoid .spanFormulario_Client_InstructionVoid").each(function(){
        this.innerHTML="";
    });
}

/*Invocar al servicio que retorna listas de valores del formulario */
function loadValues_ClientInstructionVoidJSONData(){

    var url = urlLoadValues_ClientInstructionVoid;
    var param={};
    $("#mensaje_error").empty();                   3
    $("#div_mensajes_error").fadeOut("fast");
    $("#div_cliente_anular").html("");
    $("#numero_de_control").val("");
    $("#ci_client_cancel").val("-2");
    $("#div_clientInstructionCancel2").attr("style","display:none");
    sendServiceJSON(url,param,loadValues_ClientInstructionVoidSuccess,null,null);
}

/*Cargar listas de valores en el formulario */
function loadValues_ClientInstructionVoidSuccess(originalRequest){
    //                   this is the json return data


    var result = originalRequest;
    var clients = result.clients;
    /* var transferTypes = result.transferTypes;
     var quantities = result.maxCartas;
     var vencimientos = result.vencimientos;   */

    idioma = result.idioma;
    var codigo = result.codigo;


    if(codigo=="0" && clients!=null){
        if(idioma=="_us_en"){
            cargar_selectId("ci_client_cancel", clients,"Select","-2");
            /*   cargar_selectId("ci_transfer_type", transferTypes,"Select","-2");
               cargar_selectId("ci_quantity", quantities,"Select","-2");
               cargar_selectId("ci_expiration", vencimientos,"Select","-2");    */
        } else  {
            cargar_selectId("ci_client_cancel", clients,"Seleccione","-2");
            /* cargar_selectId("ci_transfer_type", transferTypes,"Seleccione","-2");
             cargar_selectId("ci_quantity", quantities,"Seleccione","-2");
             cargar_selectId("ci_expiration", vencimientos,"Seleccione","-2");       */
        }
    }
}

function evaluarCartaDeInstruccionJSONData(){
    var url = urlconsultarCartaDeInstruccion;
    var param={};
    var jsonParametrosString=[];

    $("#div_carga_espera").removeClass("oculto");      // muestra cargando....

    jsonParametrosString[0]=  $("#ci_client_cancel").val().split("#")[0];
    jsonParametrosString[1]=  $("#ci_client_cancel").val().split("#")[1];
    jsonParametrosString[2]=  $("#ci_client_cancel").val().split("#")[2];
    jsonParametrosString[3]=  $("#numero_de_control").val();
    jsonParametrosString[4]=  $("#ci_client_cancel").val();

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,evaluarCartaDeInstruccionJSuccess,null,null);

}

function evaluarCartaDeInstruccionJSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var mensaje="";

    $("#div_carga_espera").addClass("oculto");      //quita cargando........

    if    (result.respuesta == "0")                 //mostrar boton de anulacion
    {

        $("#div_clientInstructionCancel2").fadeIn("slow");
        cargarIdiomaJSONData_sinc();
        var boton=vbtol_props[idioma]["instrucciones_cliente_anular"];
        $("#div_cliente_anular").html('<div style="width:600px;margin:auto;text-align:center"><input type="button"  id="instrucciones_cliente_anular"  value="'+boton+'" class="botonGrande2"></div>');

    }

    else if    (result.respuesta == "1")         //para pruebas
    {
        if(idioma=="_us_en")
            mensaje="Your letter of instruction was executed";
        else
            mensaje="La carta de instrucción ya fue utilizada";

    }

    else if    (result.respuesta == "2")
    {
        if(idioma=="_us_en")
            mensaje="Your letter of instruction expired";
        else
            mensaje="La carta de instrucción ya expiró";

    }
    else {

        if(idioma=="_us_en")
            mensaje="Your letter of instruction is not valid";
        else
            mensaje="La carta de instrucción no es válida";


    }

    if (mensaje!=""){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }

}

function anularCartaDeInstruccionJSONData(){
    var url = urlanularCartaDeInstruccion;
    var param={};
    var jsonParametrosString=[];

    $("#div_clientInstructionVoid").removeClass("oculto");
    jsonParametrosString[0]=  $("#ci_client_cancel").val().split("#")[0];
    jsonParametrosString[1]=  $("#ci_client_cancel").val().split("#")[1];
    jsonParametrosString[2]=  $("#ci_client_cancel").val().split("#")[2];
    jsonParametrosString[3]=  $("#numero_de_control").val();
    jsonParametrosString[4]=  $("#ci_client_cancel").val();

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,anularCartaDeInstruccionJSuccess,null,null);

}

function anularCartaDeInstruccionJSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var mensaje="";

    if    (result.respuesta == "01")
    {
        if(idioma=="_us_en")
            mensaje="Your letter of instruction was successfully cancelled";
        else
            mensaje="La carta de instrucción fue anulada exitosamente";



        //$("#mensajeClienteInstruccionAnulacion").html(mensaje);
    }

    else if    (result.respuesta == "1")
    {
        if(idioma=="_us_en")

            mensaje="Your letter of instruction was executed";
        else
            mensaje="La carta de instrucción ya fue utilizada";


    }

    else if    (result.respuesta == "2")
    {
        if(idioma=="_us_en")
            mensaje="Your letter of instruction expired";
        else
            mensaje="La carta de instrucción ya expiró";

        //  $("#mensajeClienteInstruccionAnulacion").html(mensaje);

    }
    else {

        if(idioma=="_us_en")
            mensaje="Your letter of instruction is not valid";
        else
            mensaje="La carta de instrucción no es válida";


    }


    $("#div_cliente_anular").html("");
    $("#div_clientInstructionCancel2").fadeOut("fast");

    //muestro mensajes
    $("#mensaje_error").empty();
    $("#mensaje_error").html(mensaje);
    $("#div_mensajes_error").fadeIn("slow");

}



function validarCamposAnularCarta(){
    if (($("#numero_de_control").val()!="")&&($("#ci_client_cancel").val()!="-2")){

        // if ($("#numero_de_control").val().length==15){
        $("#instrucciones_cliente_consultar").click();
        // }
    }

}

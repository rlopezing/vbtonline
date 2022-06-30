var urlCargarMiInformacion="MyInformation_cargarMiInformacion.action";
//var urlCargar_TDC_AE="CreditCards_cargarTarjetas.action";


var idioma ="";
var noInfo="";
$(document).ready(function(){

    $("#mostrar_todo").click(function(){
        if($("#div_myInformation #todoOculto").val() =="mostrar"){
            $("#fieldset_telefono").fadeIn("fast");
            $("#fieldset_direccion").fadeIn("fast");
            $("#fieldset_documentos").fadeIn("fast");
            $("#fieldset_represetantes").fadeIn("fast");
            $("#fieldset_contactos").fadeIn("fast");
            $("#fieldset_carteras").fadeIn("fast");
            $("#div_myInformation #todoOculto").val("ocultar");
            $("#fieldset_telefono #telefonoAccion").val("ocultar");
            $("#fieldset_direccion #direccionAccion").val("ocultar");
            $("#fieldset_documentos #documentosAccion").val("ocultar");
            $("#fieldset_represetantes #representantesAccion").val("ocultar");
            $("#fieldset_contactos #contactosAccion").val("ocultar");
            $("#fieldset_carteras #carterasAccion").val("ocultar");
            if(idioma=="_us_en")
                $("#mostrar_todo").html(" <img  width='9' height='9' border='0' src='../vbtonline/resources/images/comun/small_arrow.gif'> <b> <span id='misdatos_TAGOcultarTodas'> Hide all my information </span></b>");
            else
                $("#mostrar_todo").html(" <img  src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'><b> <span id='misdatos_TAGOcultarTodas'>Ocultar Toda la Informaci&oacute;n</span></b>");
        }else{

            $("#fieldset_telefono").fadeOut("fast");
            $("#fieldset_direccion").fadeOut("fast");
            $("#fieldset_documentos").fadeOut("fast");
            $("#fieldset_represetantes").fadeOut("fast");
            $("#fieldset_contactos").fadeOut("fast");
            $("#fieldset_carteras").fadeOut("fast");
            $("#div_myInformation #todoOculto").val("mostrar");
            $("#fieldset_telefono #telefonoAccion").val("mostrar");
            $("#fieldset_direccion #direccionAccion").val("mostrar");
            $("#fieldset_documentos #documentosAccion").val("mostrar");
            $("#fieldset_represetantes #representantesAccion").val("mostrar");
            $("#fieldset_contactos #contactosAccion").val("mostrar");
            $("#fieldset_carteras #carterasAccion").val("mostrar");
            if(idioma=="_us_en")
              $("#mostrar_todo").html("<img  src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'><b><span id='misdatos_TAGMostrarTodas'> Show all my information</span></b>");
            else
              $("#mostrar_todo").html("<img src='../vbtonline/resources/images/comun/small_arrow.gif' border=0 width='9' height='9'><b><span id='misdatos_TAGMostrarTodas'> Mostrar Toda la Informaci&oacute;n</span></b>");
        }
    });



    $("#mostrar_telefono").click(function(){
        if($("#fieldset_telefono #telefonoAccion").val() =="mostrar"){
            $("#fieldset_telefono").fadeIn("fast");
            $("#fieldset_telefono #telefonoAccion").val("ocultar");
        }else{
            $("#fieldset_telefono").fadeOut("fast");
            $("#fieldset_telefono #telefonoAccion").val("mostrar");
        }
    });

    $("#mostrar_direccion").click(function(){
        if($("#fieldset_direccion #direccionAccion").val() =="mostrar"){
            $("#fieldset_direccion").fadeIn("fast");
            $("#fieldset_direccion #direccionAccion").val("ocultar");
        }else{
            $("#fieldset_direccion").fadeOut("fast");
            $("#fieldset_direccion #direccionAccion").val("mostrar");
        }
    });

    $("#mostrar_documentos").click(function(){
        if($("#fieldset_documentos #documentosAccion").val() =="mostrar"){
            $("#fieldset_documentos").fadeIn("fast");
            $("#fieldset_documentos #documentosAccion").val("ocultar");
        }else{
            $("#fieldset_documentos").fadeOut("fast");
            $("#fieldset_documentos #documentosAccion").val("mostrar");
        }
    });
    $("#mostrar_represetantes").click(function(){
        if($("#fieldset_represetantes #representantesAccion").val() =="mostrar"){
            $("#fieldset_represetantes").fadeIn("fast");
            $("#fieldset_represetantes #representantesAccion").val("ocultar");
        }else{
            $("#fieldset_represetantes").fadeOut("fast");
            $("#fieldset_represetantes #representantesAccion").val("mostrar");
        }
    });
    $("#mostrar_contactos").click(function(){
        if($("#fieldset_contactos #contactosAccion").val() =="mostrar"){
            $("#fieldset_contactos").fadeIn("fast");
            $("#fieldset_contactos #contactosAccion").val("ocultar");
        }else{
            $("#fieldset_contactos").fadeOut("fast");
            $("#fieldset_contactos #contactosAccion").val("mostrar");
        }
    });
    $("#mostrar_carteras").click(function(){
        if($("#fieldset_carteras #carterasAccion").val() =="mostrar"){
            $("#fieldset_carteras").fadeIn("fast");
            $("#fieldset_carteras #carterasAccion").val("ocultar");
        }else{
            $("#fieldset_carteras").fadeOut("fast");
            $("#fieldset_carteras #carterasAccion").val("mostrar");
        }
    });


});

function InfoPaginaMiInformacionJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlCargarMiInformacion;
    var param={};

    sendServiceJSON(url,param,InfoPaginaMiInformacionSuccess,null,null);
}


function InfoPaginaMiInformacionSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var oTable;
    var detalleCliente = result.detalleCliente;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    $("#div_carga_espera").addClass("oculto");
    if(result.mostrarActualizacion != "0"){
        if((result.linkActualizacion) != null){
            var infoDescrip = "";
            if(idioma=="_us_en")
                infoDescrip+= "<h3 style='text-align: center;'>Client Data Update - VBT Bank & Trust Ltd.</h3>"+
                    "<p style='text-align: justify;'> Dear Client,<br><br>Your immediate attention to this important matter is greatly appreciated as we prepare to update our database." +
                    "<br><br> If you have any questions concerning this request , please contact your Relationship Manager Monday to Friday, " +
                    "between the hours of 8:00 a.m. – 5:00 p.m. Thank you in advance for your assistance and understanding as we move " +
                    "forward with this important service improvement initiative.</p>"+
                    "<br><center><a style='' href='"+linkActualizacion+"'>Update Data Information.<br> <img border='0' src='../vbtonline/resources/images/finger.png'></a></center>";

            else
                infoDescrip+= "<h3 style='text-align: center;'>Actualización de Información - VBT Bank & Trust Ltd.</h3>"+
                    "<p style='text-align: justify;'>Estimado Cliente,<br><br>Apreciamos su inmediata atención a este importante asunto mientras nos preparamos para actualizar nuestra base de datos." +
                    "<br><br> Si tiene alguna pregunta al respecto, comuníquese con su Gerente de Relaciones de lunes a viernes, " +
                    "entre las 8:00 a.m. y las 5:00 p.m. Gracias de antemano por su ayuda y comprensión a " +
                    "medida que avanzamos con esta importante iniciativa de mejora del servicio.</p>"+
                    "<br><center><a href='"+linkActualizacion+"'>Actualizar Información de Datos.<br> <img border='0' src='../vbtonline/resources/images/finger.png'></a></center>";

            $("#actualizacion > span.datos").html(infoDescrip);

            if($("#actualizacion").hasClass("oculto"))
                $("#actualizacion").removeClass("oculto");

        }else {

            if(!$("#actualizacion").hasClass("oculto"))
                $("#actualizacion").addClass("oculto");
        }
    }

    //$("#marco_trabajo").css("height","1500px");
      //debugger;
    $("#nameMyInformation_info").html(detalleCliente[0]);
    $("#cirifMyInformation_info").html(detalleCliente[1]);
    $("#mailMyInformation_info").html(detalleCliente[2]);
    $("#mailMyInformation_info2").html(detalleCliente[2]);

   // if (($("#tipo_usuario_app").val()=="6")||($("#tipo_usuario_app").val()=="7")||($("#tipo_usuario_app").val()=="8")||($("#tipo_usuario_app").val()=="9")){
        $("#misdatos_TAGtelefono_info").html(Trim(detalleCliente[3]));
        $("#TAGmisdatos_TAGtelefono").removeClass("oculto");
        $("#misdatos_TAGtelefono_info").removeClass("oculto");
        $("#misdatos_TAGtelefono_info").html(detalleCliente[3]);
  /*  }else{
        $("#TAGmisdatos_TAGtelefono").addClass("oculto");
        $("#misdatos_TAGtelefono_info").addClass("oculto");
        $("#misdatos_TAGtelefono_info").html("");
    } */


    var colTelefono = result.contenidoTabla_culumnasTelefono;
    var datosTelefono = result.contenidoTabla_infoTelefono;
    if(datosTelefono.length!=0){
        $("#bloque_informacion_telefonos").removeClass("oculto");
        // crearTabla('div_tabla_consulta_MyInfo_Telefono','tabla_consulta_MyInfo_Telefono',colTelefono,datosTelefono);
        crearTablaV2("table-info_telephone",colTelefono,datosTelefono,"");
        /*oTable = $('#tabla_consulta_MyInfo_Telefono').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                { "sClass": "center"},
                { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });*/
        oTable = $('#table-info_telephone').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                { "sClass": "center"},
                { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#bloque_informacion_telefonos").addClass("oculto");
    }
    var colDireccion = result.contenidoTabla_culumnasDireccion;
    var datosDireccion = result.contenidoTabla_infoDireccion;
    if(datosDireccion.length!=0){
        $("#bloque_informacion_direccion").removeClass("oculto");
        // crearTabla('div_tabla_consulta_MyInfo_Direccion','tabla_consulta_MyInfo_Direccion',colDireccion,datosDireccion);
        crearTablaV2("table-info_addresses",colDireccion,datosDireccion,"table__row--address",true)
        /*oTable = $('#tabla_consulta_MyInfo_Direccion').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                { "sClass": "" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });*/
        oTable = $('#table-info_addresses').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bDestroy": true,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "center"},
                { "sClass": "" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#bloque_informacion_direccion").addClass("oculto");
    }

  /*  var colDocumentos = result.contenidoTabla_culumnasDocumentos;
    var datosDocumentos = result.contenidoTabla_infoDocumentos;
    if(datosDocumentos.length!=0){
        $("#bloque_informacion_documentos").removeClass("oculto");
        crearTabla('div_tabla_consulta_MyInfo_Documentos','tabla_consulta_MyInfo_Documentos',colDocumentos,datosDocumentos);

        oTable = $('#tabla_consulta_MyInfo_Documentos').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": ""},
                { "sClass": "center"},
                { "sClass": "center"},
                { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#bloque_informacion_documentos").addClass("oculto");
    }
     */
    var colRepresentantes = result.contenidoTabla_culumnasRepresentantes;
    var datosRepresentantes = result.contenidoTabla_infoRepresentantes;
    if(datosRepresentantes.length!=0){
        $("#bloque_informacion_representantes").removeClass("oculto");
        crearTabla('div_tabla_consulta_MyInfo_Representantes','tabla_consulta_MyInfo_Representantes',colRepresentantes,datosRepresentantes);

        oTable = $('#tabla_consulta_MyInfo_Representantes').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
            { "sClass": "" },
            { "sClass": "center"},
            { "sClass": "center"},
            { "sClass": "center"},
            { "sClass": "center" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#bloque_informacion_representantes").addClass("oculto");
    }

    var colContactos = result.contenidoTabla_culumnasContactos;
    var datosContactos = result.contenidoTabla_infoContactos;
    if(datosContactos.length!=0){
        $("#bloque_informacion_contactos").removeClass("oculto");
        crearTabla('div_tabla_consulta_MyInfo_Contactos','tabla_consulta_MyInfo_Contactos',colContactos,datosContactos);

        oTable = $('#tabla_consulta_MyInfo_Contactos').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
            { "sClass": "" },
            { "sClass": "center"},
            { "sClass": "center"},
            { "sClass": "center"},
            { "sClass": "center" }
           ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#bloque_informacion_contactos").addClass("oculto");
    }


    var colCarteras= result.contenidoTabla_culumnasCarteras;
    var datosCarteras = result.contenidoTabla_infoCarteras;
    if(datosCarteras.length!=0){
        $("#bloque_informacion_carteras").removeClass("oculto");
        crearTabla('div_tabla_consulta_MyInfo_Carteras','tabla_consulta_MyInfo_Carteras',colCarteras,datosCarteras);
        oTable = $('#tabla_consulta_MyInfo_Carteras').dataTable({
            "iDisplayLength": 10,
            "bJQueryUI": true,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
            { "sClass": "center"},
            { "sClass": ""},
            { "sClass": ""},
            { "sClass": "" }
            ],
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
    }else{
        $("#bloque_informacion_carteras").addClass("oculto");
    }
}
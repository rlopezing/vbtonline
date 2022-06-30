var urlBackOfficeConsultarAvisos="BackOffice_consultarAvisos.action";
var urlBackOfficeValidarAvisos="BackOffice_validarAvisos.action";

var cargarFiltrosAvisos=0;
var fechaFiltoHoy;
var opcionAvisos;
var textoIdioma;
var guardoAviso="";

$(document).ready(function() {

    $("#fechaDesdeFiltroAviso").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $("#fechaHastaFiltrAviso").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $("#fechaDesdeFiltroAviso").datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true });
    $("#fechaHastaFiltrAviso").datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true });
    $("#fechaDesdeFiltroAviso").val("");
    $("#fechaHastaFiltrAviso").val("");
    $("#fechaInicioAviso").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $("#fechaFinAviso").mask('99/99/9999', {'translation': {0: {pattern: /[0-9*]/}}});
    $("#fechaFinAviso").datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true });

    $("#fechaInicioAviso").datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        gotoCurrent: true,
        defaultDate: 0,
        beforeShowDay: editDays,
//        minDate: "+2d",
        changeYear: true   ,
        onClose: function (selectedDate) {
            $("#fechaFinAviso").datepicker("option", "minDate",selectedDate);
        }
    });



    var objEditor=$('#editorAviso').tinymce({
        language:"es",
        resize:false,
        plugins:"textcolor",
        width:"514",
        menubar: "file edit view",
        toolbar: "insertfile undo redo | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | l      ink image | print preview media fullpage | forecolor backcolor emoticons sizeselect | fontselect | fontsizeselect",
        height: "255",
        setup : function(objEditor) {
            objEditor.on('keyDown', function(e) {
                $("#editorAviso_ifr").attr("scrolling","no");
                if (e.keyCode==13){
                    return true;
                }else if (e.keyCode==8){
                    return true;
                }else if (e.keyCode==46){
                    return true;
                }else{
                    var x=objEditor.getContent();
                    var regex=/<[^>]*>/gi;
                    outputstr=x.replace(regex,"");
                 /*   if(outputstr.length > 100){
                        return false;
                    }  */
                }
            });
            objEditor.on('change', function(e) {
                $("#editorAviso_ifr").attr("scrolling","no");

                if (textoIdioma=="esp"){
                    $("#textoAvisoEsp").val($("#editorAviso").val());
                }else{
                    $("#textoAvisoIng").val($("#editorAviso").val());
                }
            });
        }
    });


    $("#uploadImagenAviso").on("change", function(){
        $("#cambioImagen").val("si");

        var files = !!this.files ? this.files : [];

        if (!files.length || !window.FileReader) return;

            var reader = new FileReader();
            reader.readAsDataURL(files[0]);

            reader.onloadend = function(){
                $("#imagePreviewAviso").css("background-image", "url('"+this.result+"')");
                $("#imagenAvisoBase64").val(reader.result);
         }


    });


    $("#uploadImagenAviso").on("change", function(){

        var ext = $('#uploadImagenAviso').val().split('.').pop().toLowerCase();
        /*if($.inArray(ext, ['jpg','jpeg']) == -1) {
            $("#uploadImagenAviso").val("");
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Formato de imagen inválida. Solo se admiten imagenes jpg y jpeg");
            $("#div_mensajes_error").fadeIn("slow");
        }*/

});


    $("#vistaPreviaAviso").click(function(){

        if (textoIdioma=="esp"){
            $("#imagePreviewAviso").html($("#textoAvisoEsp").val());
            $("#textoTituloAvisoEspPrev").addClass("negrita");
            $("#textoTituloAvisoIngPrev").removeClass("negrita");
        }else{
            $("#textoTituloAvisoEspPrev").removeClass("negrita");
            $("#textoTituloAvisoIngPrev").addClass("negrita");
            $("#imagePreviewAviso").html($("#textoAvisoIng").val());

        }

        $("#detalle_aviso").fadeIn("slow");

    });

    $("#btn_cerrar_vista_previa").click(function(){

        $("#detalle_aviso").fadeOut("fast");

    });



    $("#limpiarFiltroAvisos").click(function(){
        blanquearFormularios("avisosBO");
        /*
        $("#fechaDesdeFiltroAviso").val(fechaFiltoHoy);
        $("#fechaHastaFiltrAviso").val(fechaFiltoHoy);
        */
        $("#chkFechas").attr("checked",false);
        $("#div_tabla_avisos_bo").empty();
        $("#paginacion_tabla_consulta_avisos_bo").empty();
    });

    $("#buscarAvisos").click(function(){
        if (($("#fechaDesdeFiltroAviso").val()!="")&&($("#fechaHastaFiltroAviso").val()!="")) {
            BackOfficeAvisosJSONData("");
        }else{
            BackOfficeAvisosJSONData("-2");
        }

    });


    $("#agregarAvisos").click(function(){
        opcionAvisos="add";
        $("#operacionAviso").val("add");
        textoIdioma="esp";
        $("#avisoGuardar").val("Agregar");
        $("#cambioImagen").val("no");
        $("#textoTituloAvisoEsp").addClass("negrita");
        $("#textoTituloAvisoIng").removeClass("negrita");
        $("#avisoLimpiar").click();

//        $("#AddStatusAvisoBO").val("A");
        $("#AddStatusAvisoBO").val("");
        $("#AddStatusAvisoBO").attr("disabled", false);

        $("#AddTipoAvisoBO").val("N");
        $("#AddTipoAvisoBO").attr("disabled", true);
        $("#avisoLimpiar").removeClass("oculto");
        $("#title_opc_aviso").html("Crear Aviso");
        $("#div_Avisos_BO").fadeIn("slow");
        $("#div_consultarAvisos").fadeOut("fast");
        $("#fechaInicioAviso").datepicker("option", "minDate", fechaHoy);
        $("#fechaFinAviso").datepicker("option", "minDate",fechaHoy);
        $("#uploadImagenAviso").replaceWith( $("#uploadImagenAviso").val("").clone(true));
//        $("#uploadImagenAviso").addClass("obligatorioAviso");


    });

    $("#avisoVolver").click(function(){
        $("#div_Avisos_BO").fadeOut("fast");
        $("#div_consultarAvisos").fadeIn("slow");
        $("#imagePreviewAviso").css("background-image", "url('"+"jsp/imgAvisoBO.jsp?id="+Math.random()+"')");
    });

    $("#avisoGuardar").click(function(){
        var mensaje="";
//        if (opcionAvisos=="add"){

          if (opcionAvisos=="edit"){
              $("#datosAvisoAdd .obligatorioAviso").each(function(){
                if ( ( ( Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2") && Trim($("#AddTipoAvisoBO").val())!="S" )
                       || ( Trim($("#AddTipoAvisoBO").val())=="S" && this.title=="Descripcion" && Trim($("#"+this.id).val())=="") ){
                            mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                            $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
              });
          }else{
              $("#datosAvisoAdd .obligatorioAviso").each(function(){
                  if ( Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                      mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                      $("#"+this.id).addClass("error_campo");
                  }else{
                      if($("#"+this.id).hasClass("error_campo"))
                          $("#"+this.id).removeClass("error_campo");
                  }
              });
          }

            var fdesdeFormat= $("#fechaInicioAviso").val().split("/")[1]
                +"/"+$("#fechaInicioAviso").val().split("/")[0]
                +"/"+$("#fechaInicioAviso").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fechaFinAviso").val().split("/")[1]
                +"/"+$("#fechaFinAviso").val().split("/")[0]
                +"/"+$("#fechaFinAviso").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if ( (Fdesde>Fhasta) && (Trim($("#AddTipoAvisoBO").val())!="S") ){
                mensaje=mensaje+"La fecha de inicio no puede ser mayor a la fecha de finalización<br>";
                $("#fechaInicioAviso").addClass("error_campo");
                $("#fechaFinAviso").addClass("error_campo");
            }else if ( (Fdesde<Fhasta) && (Trim($("#AddTipoAvisoBO").val())!="S") ){
                $("#fechaInicioAviso").removeClass("error_campo");
                $("#fechaFinAviso").removeClass("error_campo");
            }

            if (($("#textoAvisoEsp").val()=="") && ($("#textoAvisoIng").val()=="") && ($("#uploadImagenAviso").val()=="")){
                mensaje=mensaje+"Debes ingresar al menos una imagen o un texto (en ambos idiomas).<br>";
            }

            if (($("#textoAvisoEsp").val()=="") && ($("#textoAvisoIng").val()!="")){
                mensaje=mensaje+"El texto en lenguaje español no debe ser vacío<br>";
            } else if (($("#textoAvisoEsp").val()!="") && ($("#textoAvisoIng").val()=="")){
                mensaje=mensaje+"El texto en lenguaje inglés no debe ser vacío<br>";
            }

            if(mensaje!=""){
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
              //Inserta
                validarAvisosBOJSONData();
            }

//        }else{
//          //Editar
//
//        }



    });



    $("#avisoLimpiar").click(function(){
        blanquearFormularios("datosAvisoAdd");
        blanquearFormularios("div_Avisos_BO");
        $("#AddTipoAvisoBO").attr("disabled", true);
        $("#AddTipoAvisoBO").val("N");
        $("#datosAvisoAdd .obligatorioAviso").each(function(){
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
        });
        $("#mensaje_error").empty();
        $("#div_mensajes_error").fadeOut("fast");
        $("#AddStatusAvisoBO").val("");
        $("#AddTipoAvisoBO").val("N");
        $("#AddTipoAvisoBO").attr("disabled", true);
        $("#imagePreviewAviso").css("background-image", "url('')");
        $("#editorAviso").val("");
        $("#textoAvisoEsp").val("");
        $("#textoAvisoIng").val("");
        $("#uploadImagenAviso").replaceWith( $("#uploadImagenAviso").val("").clone(true));
        editorActual("esp");
    });


});

function BackOfficeAvisosJSONData(defecto){
    var url = urlBackOfficeConsultarAvisos;
    var param={};
    var jsonParametrosString=[];

    $("#div_carga_espera").removeClass("oculto");
    jsonParametrosString[0]= $("#tipoAvisoBO").val();
    jsonParametrosString[1]= $("#estatusAvisoBO").val();
    jsonParametrosString[2]= $("#fechaDesdeFiltroAviso").val();
    jsonParametrosString[3]= $("#fechaHastaFiltrAviso").val();
    jsonParametrosString[4]= cargarFiltrosAvisos;
    jsonParametrosString[5]= defecto;


    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON(url,param,BackOfficeAvisosSuccess,null,null);
}


function BackOfficeAvisosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tipoAviso = result.tipoGrupo;
    var tipoEstatus = result.tipoEstatus;
    fecha= result.fechaFiltro;
    $("#div_Avisos_BO").fadeOut("fast");
    $("#detalle_aviso").fadeOut("fast");
    $("#div_carga_espera").addClass("oculto");

    if(cargarFiltrosAvisos=="0"){
        cargar_selectPersonal("tipoAvisoBO",tipoAviso,"Todo","-2");
        cargar_selectPersonal("AddTipoAvisoBO",tipoAviso,"Seleccione","-2");
        cargar_selectPersonal("estatusAvisoBO",tipoEstatus,"Todo","-2");
        cargar_select("AddStatusAvisoBO",tipoEstatus,"Todo","-2");
        /*
        $("#fechaDesdeFiltroAviso").val(fecha);
        $("#fechaHastaFiltrAviso").val(fecha);
        */
        cargarFiltrosAvisos=1;
        fechaFiltoHoy= fecha;
        //$("#bitacora_search").click();
    }
        var tabla="tabla_avisos_bo";
        // crearTablaAvisos("div_tabla_avisos_bo",tabla,columnas,datos);
        crearTablaV3("div_tabla_avisos_bo",tabla,columnas,datos);

/*        oTable = $('#'+tabla).dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "left" },
                { "sClass": "center"},
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" }
            ],
            "sPaginationType": "full_numbers"

        });*/
        oTable = $('#'+tabla).dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": false,
            "bSort": false,
            "aoColumns": [
                { "sClass": "center" },
                { "sClass": "left" },
                { "sClass": "center"},
                {  "sClass": "center" },
                {  "sClass": "center" },
                {  "sClass": "center" }
            ],
            "sPaginationType": "full_numbers"

        });

}

  /*
function abrirDetalleTablaAvisos(elemento){
    var nTr =  $(elemento).parents('tr')[0];
    var sOut = '<table width="100%">';
    sOut +='<tr><td width="10%"><span class="label_descp">Texto Español</span></td>';
    sOut +='<td width="90%">'+elemento.id.split("|")[1]+'</td>';
    sOut +='</tr>';
    sOut +='<tr><td width="10%"><span class="label_descp">Texto Inglés</span></td>';
    sOut +='<td width="90%">'+elemento.id.split("|")[2]+'</td>';
    sOut +='</tr>';
    sOut +='</table>';
    if ( oTable.fnIsOpen(nTr) )
    {

        elemento.src = "resources/images/comun/nolines_plus.gif";
        oTable.fnClose( nTr );
    }
    else
    {

        elemento.src = "resources/images/comun/nolines_minus.gif";
        numeroDetalle = numeroDetalle + 1;
        oTable.fnOpen( nTr, sOut, 'details');
    }
}  */

function abrirDetalleTablaAvisos(elemento){
    var nTr =  $(elemento).parents('tr')[0];
    var sOut = '<table width="100%">';
    opcionAvisos="edit";
    sOut +='<tr><td width="10%"><span class="label_descp">Texto Español</span></td>';
    sOut +='<td width="90%">'+elemento.id.split("|")[1]+'</td>';
    sOut +='</tr>';
    sOut +='<tr><td width="10%"><span class="label_descp">Texto Inglés</span></td>';
    sOut +='<td width="90%">'+elemento.id.split("|")[2]+'</td>';
    sOut +='</tr>';
    sOut +='</table>';
    if ( oTable.fnIsOpen(nTr) )
    {
        /* This row is already open - close it */
        elemento.src = "resources/images/comun/nolines_plus.gif";
        oTable.fnClose( nTr );
    }
    else
    {
        /* Open this row */
        elemento.src = "resources/images/comun/nolines_minus.gif";
        numeroDetalle = numeroDetalle + 1;
        oTable.fnOpen( nTr, sOut, 'details');
    }
}

function editorActual(idiomaAvisoTxt){

    $("#editorAviso").val("");
    if (idiomaAvisoTxt=="esp"){
        textoIdioma="esp";
        $("#textoTituloAvisoEsp").addClass("negrita");
        $("#textoTituloAvisoIng").removeClass("negrita");
        $("#editorAviso").val($("#textoAvisoEsp").val());

    }else{
        textoIdioma="ing";
        $("#textoTituloAvisoEsp").removeClass("negrita");
        $("#textoTituloAvisoIng").addClass("negrita");
        $("#editorAviso").val($("#textoAvisoIng").val());
    }

}


function editorActualPrev(idiomaAvisoTxt){
    if (idiomaAvisoTxt=="esp"){

        $("#textoTituloAvisoEspPrev").addClass("negrita");
        $("#textoTituloAvisoIngPrev").removeClass("negrita");
        $("#imagePreviewAviso").html($("#textoAvisoEsp").val());

    }else{

        $("#textoTituloAvisoEspPrev").removeClass("negrita");
        $("#textoTituloAvisoIngPrev").addClass("negrita");
        $("#imagePreviewAviso").html($("#textoAvisoIng").val());

    }

}


function BackOfficeCrearAvisosJSONData(){
    console.log('pase por crear avisoooooooo');
    $("#div_carga_espera").removeClass("oculto");
    $("#AddStatusAvisoBO").attr("disabled", false);
    $("#AddTipoAvisoBO").attr("disabled", false);
    $("#frmCrearAviso").submit();
    setTimeout(function() {
        $("#div_carga_espera").removeClass("oculto");
        $("#div_consultarAvisos").fadeIn("slow");
        BackOfficeAvisosJSONData("-2");
    }, 8000);

}


function BackOfficeCrearAvisosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
  /*  var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tipoAviso = result.tipoGrupo;
    var tipoEstatus = result.tipoEstatus;
    fecha= result.fechaFiltro;
    $("#div_Avisos_BO").fadeOut("fast");
    $("#detalle_aviso").fadeOut("fast");
    $("#div_carga_espera").addClass("oculto");      */



}




function getBase64Image(imgElem) {
// imgElem must be on the same server otherwise a cross-origin error will be thrown "SECURITY_ERR: DOM Exception 18"
    var canvas = document.createElement("canvas");
    canvas.width = imgElem.clientWidth;
    canvas.height = imgElem.clientHeight;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(imgElem, 0, 0);
    var dataURL = canvas.toDataURL("image/png");
    return dataURL.replace(/^data:image\/(jpg|jpeg);base64,/, "");
}





function validarAvisosBOJSONData() {
    var url = urlBackOfficeValidarAvisos;
    var param={};
    var jsonParametrosString=[];
                  /*
    jsonParametrosString[0] = $("#AddTipoAvisoBO").val();
    jsonParametrosString[1] = $("#fechaInicioAviso").val();
    jsonParametrosString[2] = $("#fechaFinAviso").val();
    jsonParametrosString[3] =  opcionAvisos;
     alert($("#AddStatusAvisoBO").val());
    if (opcionAvisos=="edit") {
        jsonParametrosString[4] = $("#codigoAviso").val();
    }               */

    jsonParametrosString[0] = $("#AddTipoAvisoBO").val();
    jsonParametrosString[1] = $("#AddStatusAvisoBO").val();
    jsonParametrosString[2] = $("#fechaInicioAviso").val();
    jsonParametrosString[3] = $("#fechaFinAviso").val();
    jsonParametrosString[4] = opcionAvisos;

    if (opcionAvisos=="edit") {
        jsonParametrosString[5] = $("#codigoAviso").val();
    }

    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON_sinc(url,param,validarAvisosBOJSONDataSuccess,null,null);

}

function validarAvisosBOJSONDataSuccess(originalRequest){
    var result = originalRequest;
    var mensaje = originalRequest.mensaje;
    if (mensaje == "OK") {
        // crearRegla();
        BackOfficeCrearAvisosJSONData();
    } else {
        $("#div_carga_espera").addClass("oculto");
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Ya existe un aviso activo en ese rango de fechas");
        $("#div_mensajes_error").fadeIn("slow");

    }
}


function seleccionarAvisoOpcion(datos){
    /*
    $("#datosAvisoAdd .obligatorioAviso").each(function(){
        if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
            mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
            $("#"+this.id).addClass("error_campo");
            alert(this.id+" put");
        }else{
            alert(this.id+" delete");
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        }
    });
    */
    opcionAvisos="edit";
    $("#datosAvisoAdd .obligatorioAviso").each(function(){
        $("#"+this.id).removeClass("error_campo");
    });

    $("#operacionAviso").val("edit");
    textoIdioma="esp";
    $("#cambioImagen").val("no");
    $("#avisoGuardar").val("Editar");
    $("#textoTituloAvisoEsp").addClass("negrita");
    $("#textoTituloAvisoIng").removeClass("negrita");
    $("#AddStatusAvisoBO").attr("disabled", true);
    $("#AddTipoAvisoBO").attr("disabled", true);
    $("#avisoLimpiar").addClass("oculto");
    $("#title_opc_aviso").html("Editar Aviso");
    $("#div_Avisos_BO").fadeIn("slow");
    $("#div_consultarAvisos").fadeOut("fast");
    $("#uploadImagenAviso").replaceWith( $("#uploadImagenAviso").val("").clone(true));
    $("#uploadImagenAviso").removeClass("obligatorioAviso");

    $("#fechaInicioAviso").datepicker("option", "minDate", fechaHoy);
    $("#fechaFinAviso").datepicker("option", "minDate",fechaHoy);
    $("#uploadImagenAviso").replaceWith( $("#uploadImagenAviso").val("").clone(true));

    $("#codigoAviso").val(datos.split("|")[0]);
    $("#addTextoAviso").val(datos.split("|")[1]);
    $("#AddStatusAvisoBO").val(datos.split("|")[7]);

    if (datos.split("|")[4] != ""){
        $("#fechaInicioAviso").val(datos.split("|")[4]);
    }else{
        $("#fechaInicioAviso").val("");
    }

    if (datos.split("|")[5] != ""){
        $("#fechaFinAviso").val(datos.split("|")[5]);
    }else{
        $("#fechaFinAviso").val("");
    }

    if (datos.split("|")[3] != ""){
        $("#textoAvisoIng").val(datos.split("|")[3]);
    }else{
        $("#textoAvisoIng").val("");
    }

    if (datos.split("|")[2] != ""){
        $("#textoAvisoEsp").val(datos.split("|")[2]);
    }else{
        $("#textoAvisoEsp").val("");
    }

    $("#AddTipoAvisoBO").val(datos.split("|")[6]);
    if( $("#AddTipoAvisoBO").val()=="S"){
        $("#AddStatusAvisoBO").attr("disabled",true);
    }else{
        $("#AddStatusAvisoBO").attr("disabled",false);
    }

    $("#editorAviso").val($("#textoAvisoEsp").val());
    $("#imagePreviewAviso").css("background-image", "url('"+"jsp/imgAvisoBO.jsp?id="+datos.split("|")[0]+"&rdn="+Math.random()+"')");

}
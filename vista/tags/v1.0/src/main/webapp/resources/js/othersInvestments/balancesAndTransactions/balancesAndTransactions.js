var urlOtrasInversionesCargar_BT="OtherInvestments_cargarOtrasInversiones.action";
var urlDetalleBalancesAndTransactionOtrasInversiones_BT="OtherInvestments_consultarDetalleOtrasInversionesBT.action";
var urlConsultarOtrasInversionesBalancesAndTransaction="OtherInvestments_consultarSaldosTransOtrasInversiones.action";


var idioma="";
var noInfo="";
$(document).ready(function(){

    $( "#fechaDesdeFiltro_SalTrans_OI").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_SalTrans_OI").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

  /*  $( "#fechaDesdeFiltro_SalTrans_OI").blur(function(){
        if(!isDate($( "#fechaDesdeFiltro_SalTrans_OI").val())&& !Empty($( "#fechaDesdeFiltro_SalTrans_OI").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });     */

  /*  $( "#fechaHastaFiltro_SalTrans_OI").blur(function(){
        if(!isDate($( "#fechaHastaFiltro_SalTrans_OI").val())&& !Empty($( "#fechaHastaFiltro_SalTrans_OI").val())){
            $("#mensaje_error").empty();
            if(idioma!="_us_en")
                $("#mensaje_error").html("La fecha desde no es correcta");
            else
                $("#mensaje_error").html("From date is not correct");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });   */

    $( "#fechaDesdeFiltro_SalTrans_OI" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_SalTrans_OI" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});

    $("#consulta_SalTrans_OI").click(function(){
        var mensaje="";
        $(".requerido_SalTrans_OI").each(function(){
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
//
        if($("#buscar_SalTrans_OI").val()=="-1")
        {
            $(".requeridoFecha_SalTrans_OI").each(function(){
                if(Trim($("#"+this.id).val())==""){
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

            if(!isDate($( "#fechaDesdeFiltro_SalTrans_OI").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"From date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
                $("#fechaDesdeFiltro_SalTrans_OI").addClass("error_campo");
            }else if(!isDate($( "#fechaHastaFiltro_SalTrans_OI").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"To date is not correct"+"<br>";
                else
                    mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
                $("#fechaHastaFiltro_SalTrans_OI").addClass("error_campo");
            }else{
                var fdesdeFormat= $("#fechaDesdeFiltro_SalTrans_OI").val().split("/")[1]
                    +"/"+$("#fechaDesdeFiltro_SalTrans_OI").val().split("/")[0]
                    +"/"+$("#fechaDesdeFiltro_SalTrans_OI").val().split("/")[2]  ;
                var Fdesde = Date.parse(fdesdeFormat);

                var fhastaFormat= $("#fechaHastaFiltro_SalTrans_OI").val().split("/")[1]
                    +"/"+$("#fechaHastaFiltro_SalTrans_OI").val().split("/")[0]
                    +"/"+$("#fechaHastaFiltro_SalTrans_OI").val().split("/")[2] ;
                var Fhasta = Date.parse(fhastaFormat);

                if(Fdesde>Fhasta){
                    if(idioma=="_us_en")
                        mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                    else
                        mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                    $("#fechaDesdeFiltro_SalTrans_OI").addClass("error_campo");
                    $("#fechaHastaFiltro_SalTrans_OI").addClass("error_campo");

                }else{
                    $("#fechaDesdeFiltro_SalTrans_OI").removeClass("error_campo");
                    $("#fechaHastaFiltro_SalTrans_OI").removeClass("error_campo");
                }
            }

        }

        if(mensaje!=""){
            $("#tabla_consulta_SalTrans_OI").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#tabla_consulta_SalTrans_OI").fadeIn("fast");
            consultarCuentaSaldoTransOtherInvestmentsJSONData();
        }

    });

});

function cargarDetalleCuentaOtrasInversiones(value){
  if(value!="-2"){
      $("#div_tabla_consulta_SalTrans_OI").empty();
      $("#paginacion_tabla_consulta_SalTrans_OI").empty();
      detalleSaldoTransOtrasInversionesJSONData();

  }
  else{
      $("#numero_cuenta_SalTrans_OI").removeClass("error_campo");

      $("#div_OtherInvestments_BT .spanFormulario_OI_BT").each(function(){
          this.innerHTML="";
      });

      $("#div_tabla_consulta_SalTrans_OI").empty();
      $("#paginacion_tabla_consulta_SalTrans_OI").empty();
      $("#saldo_BT_OtherInvestments").attr("style","display: ");
      $("#saldo_BT_OtherInvestments2").attr("style","display: none");
      $("#saldo_BT_OtherInvestmentsFecha").attr("style","display: none");


//      $("#fechas_SalTrans_OI").fadeOut("fast");
//      $("#fechaDesdeFiltro_SalTrans_OI").val("");
//      $("#fechaHastaFiltro_SalTrans_OI").val("");
//      $("#buscar_SalTrans_OI").val("5");
  }


};

function validarActivarFechas_SalTrans_OI(valor){
  //  var myDate = new Date();
   if(valor=="-1"){
       $("#fechas_SalTrans_OI").fadeIn("fast");
       $("#fechaDesdeFiltro_SalTrans_OI").val("");
  //     $("#fechaHastaFiltro_SalTrans_OI").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
   }
   else{
       $("#fechas_SalTrans_OI").fadeOut("fast");
       $("#fechaDesdeFiltro_SalTrans_OI").val("");
       $("#fechaHastaFiltro_SalTrans_OI").val("");
   }
};


function BalancesAndTransactionOtherInvestmentsInfoPaginaJSONData(){
    var url = urlOtrasInversionesCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BalancesAndTransactionOtherInvestmentsInfoSuccess,null,null);
}


function BalancesAndTransactionOtherInvestmentsInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var periodo = result.rango;
    var fondos = result.fondos;
    var codigo = result.codigo;
    idioma = result.idioma;

    $("#div_OtherInvestments_BT .selectFormulario_OI_BT").each(function(){
        this.value="-2";
    });
    $("#div_OtherInvestments_BT .inputFormulario_OI_BT").each(function(){
        this.value="";
    });
    $("#div_OtherInvestments_BT .spanFormulario_OI_BT").each(function(){
        this.innerHTML="";
    });

    $("#div_tabla_consulta_SalTrans_OI").empty();
    $("#paginacion_tabla_consulta_SalTrans_OI").empty();
    $("#fechas_SalTrans_OI").attr("style","display: none");
    $("#saldo_BT_OtherInvestments").attr("style","display: ");
    $("#saldo_BT_OtherInvestments2").attr("style","display: none");
    $("#saldo_BT_OtherInvestmentsFecha").attr("style","display: none");

    $("#btnVolverPortafolioOI").addClass("oculto");

    if(codigo=="0"){
        if(fondos!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("numero_cuenta_SalTrans_OI", fondos,"Select","-2");
            else
                cargar_selectPersonal("numero_cuenta_SalTrans_OI", fondos,"Seleccione","-2");

        if(periodo!=null)
            cargar_selectPersonal2("buscar_SalTrans_OI", periodo);
        $("#div_noInfo_BT_otherInvestment").attr("style","display: none");
        $("#div_OtherInvestments_BT").attr("style","display: ");

        if(idioma=="_us_en")
            $("#linkBloqueo_OI").html("<span class='CBT_label_montoBloqueado' id='otrasinversionessaldos_TAGBloqueado'>Units Blocked </span><span>:</span>");
        else
            $("#linkBloqueo_OI").html("<span class='CBT_label_montoBloqueado' id='otrasinversionessaldos_TAGBloqueado'>Unidades Bloqueadas </span><span>:</span>");
    }else{
        $("#div_noInfo_BT_otherInvestment").attr("style","display: ");
        $("#div_OtherInvestments_BT").attr("style","display: none");
    }

    BloqueosOtherInvestmentsInfoPaginaJSONData();

}

function detalleSaldoTransOtrasInversionesJSONData(){
    var url = urlDetalleBalancesAndTransactionOtrasInversiones_BT;
    var param={};
    var jsonOtherInvestments=[];

    jsonOtherInvestments[0] =  $("#numero_cuenta_SalTrans_OI").get(0).value;

    param.jsonOtherInvestments=JSON.stringify({"parametros":jsonOtherInvestments});

    sendServiceJSON(url,param,detalleSaldoTransOtrasInversionesSuccess,null,null);
}


function detalleSaldoTransOtrasInversionesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var  tipoTrans = result.tipoTransacciones;
    idioma = result.idioma;
    var numCuenta = result.numeroCuenta;

    var mensaje = result.mensaje;
    var codigo = result.codigo;

    if(codigo=="0"){
        $("#titular_SalTrans_OI").html(detalle[9]);
        if(detalle[3]!="0,00000000"){
            if(idioma=="_us_en")
                $("#linkBloqueo_OI").html("<a id='"+numCuenta+"' onclick='detalleBloqueoOI(this.id);' style='cursor:pointer; text-decoration: underline'><span class='CBT_label_montoBloqueado' id='otrasinversionessaldos_TAGBloqueado' title='Click to see details of blocked amount'>Units Blocked </span><span>:</span></a>");
            else
                $("#linkBloqueo_OI").html("<a id='"+numCuenta+"' onclick='detalleBloqueoOI(this.id);' style='cursor:pointer; text-decoration: underline' ><span class='CBT_label_montoBloqueado' id='otrasinversionessaldos_TAGBloqueado' title='Haga click para ver detalle del monto bloqueado'>Unidades Bloqueadas</span><span>:</span></a>");
        }else{
            if(idioma=="_us_en")
                $("#linkBloqueo_OI").html("<span class='CBT_label_montoBloqueado' id='otrasinversionessaldos_TAGBloqueado'>Units Blocked </span><span>:</span>");
            else
                $("#linkBloqueo_OI").html("<span class='CBT_label_montoBloqueado' id='otrasinversionessaldos_TAGBloqueado'>Unidades Bloqueadas </span><span>:</span>");
        }

        $("#unidadesBloqueadas_SalTrans_OI").html(detalle[3]);
        $("#moneda_SalTrans_OI").html(detalle[1]);
        $("#unidadDisponible_SalTrans_OI").html(detalle[2]);
        $("#unidadesTotales_SalTrans_OI").html(detalle[4]);
        $("#VUI_SalTrans_OI").html(detalle[5]);
        $("#montoTransito_SalTrans_OI").html(detalle[7]);
        $("#totalMoneda_SalTrans_OI").html(detalle[6]);


        $("#saldo_BT_OtherInvestments").attr("style","display: none");
        $("#saldo_BT_OtherInvestments2").attr("style","display: ");
        $("#saldo_BT_OtherInvestmentsFecha").attr("style","display: ");
        $("#saldo_BT_OtherInvestmentsFecha").html(" <b>"+detalle[8]+"</b>");

        if(tipoTrans!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("tipo_transaccion_SalTrans_OI", tipoTrans,"All","All");
            else
                cargar_selectPersonal("tipo_transaccion_SalTrans_OI", tipoTrans,"Todos","All");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }




}

function consultarCuentaSaldoTransOtherInvestmentsJSONData(){
    var url = urlConsultarOtrasInversionesBalancesAndTransaction;
    var param={};
    var jsonOtherInvestmentsTabla=[];

    jsonOtherInvestmentsTabla[0] =  $("#numero_cuenta_SalTrans_OI").get(0).value;

    jsonOtherInvestmentsTabla[1] = $("#buscar_SalTrans_OI").get(0).value;
    jsonOtherInvestmentsTabla[2] = $("#fechaDesdeFiltro_SalTrans_OI").get(0).value;
    jsonOtherInvestmentsTabla[3] = $("#fechaHastaFiltro_SalTrans_OI").get(0).value;

    jsonOtherInvestmentsTabla[4] = $("#tipo_transaccion_SalTrans_OI").get(0).value;

    param.jsonOtherInvestmentsTabla=JSON.stringify({"parametros":jsonOtherInvestmentsTabla});

    sendServiceJSON(url,param,consultarCuentaSaldoTransOtherInvestmentsSuccess,null,null);
}


//function consultarCuentaSaldoTransFondoMutualSuccess(originalRequest){
//    //                   this is the json return data
//    var result = originalRequest;
//    var columnas = result.contenidoTabla_culumnasTest;
//    var datos = result.contenidoTabla_infoTest;
//    crearTabla('div_tabla_consulta_SalTrans_OI','tabla_consulta_SalTrans_OI',columnas,datos);
////
////
//    var oTable = $('#tabla_consulta_SalTrans_OI').dataTable({
//        "bJQueryUI": true,
//        "sPaginationType": "full_numbers",
//        "bFilter": false,
//        "bSort": false
//    });
//
//}

function consultarCuentaSaldoTransOtherInvestmentsSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;

    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center"},
        {  "sClass": "" },
        {  "sClass": "right" },
        {  "sClass": "right" },
        {  "sClass": "right" },
        {  "sClass": "right" },
        { "sClass": "center" }
    ];
    var tamano_pag=25;
    var tabla;
    var tabla_id='tabla_consulta_SalTrans_OI';
    var id_contenedor='div_tabla_consulta_SalTrans_OI';

    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    var mensaje = result.mensaje;
    var codigo = result.codigo;

    if(codigo=="0"){
        tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);

        oTable =tabla.dataTable({
            "iDisplayLength": 25,
            "bJQueryUI": true,
            "bFilter": false,
            "bSort": false,
            "bPaginate": false,
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });

        if(datos.length!=0){
            $("#paginacion_"+tabla_id).paginate({
                count 		: parseInt(((datos.length%tamano_pag)==0)?(datos.length/tamano_pag):(datos.length/tamano_pag)+1),
                start 		: 1,
                display     : 8,
                border					: false,
                text_color  			: '#888',
                background_color    	: '#EEE',
                text_hover_color  		: 'black',
                background_hover_color	: '#CFCFCF',
                images					: false,
                mouse					: 'press',
                onChange     			: function(page){
                    paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                }
            });
        }
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }

//
//
}


function print_SALDOS_FONDOS_OI(){
    var miValue = $("#tipo_transaccion_SalTrans_OI" ).val();
    $("#tipo_transaccion_SalTrans_OI option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#numero_cuenta_SalTrans_OI" ).val();
    $("#numero_cuenta_SalTrans_OI option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#buscar_SalTrans_OI" ).val();
    $("#buscar_SalTrans_OI option[value='"+miValue+"']").attr("selected",true);

    $('#numero_cuenta_SalTrans_OI_select').html($('#numero_cuenta_SalTrans_OI option:selected').text());

    printPageElement('div_SALDOS_FONDOS_OI');  //Print EDO CUENTA
}


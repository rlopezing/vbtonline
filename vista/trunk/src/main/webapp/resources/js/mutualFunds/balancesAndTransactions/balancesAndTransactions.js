var urlFondosMutualesCargar_BT="MutualFunds_cargarFondosMutuales.action";
var urlDetalleBalancesAndTransactionFondosMutuales_BT="MutualFunds_consultarDetalleFondosMutualesBT.action";
var urlConsultarFondosMutualesBalancesAndTransaction="MutualFunds_consultarSaldosTransFondosMutuales.action";


var idioma="";
var noInfo="";

$(document).ready(function(){

    $( "#fechaDesdeFiltro_SalTrans_FM").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltro_SalTrans_FM").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

    /*  $( "#fechaDesdeFiltro_SalTrans_FM").blur(function(){
          if(!isDate($( "#fechaDesdeFiltro_SalTrans_FM").val())&& !Empty($( "#fechaDesdeFiltro_SalTrans_FM").val())){
              $("#mensaje_error").empty();
              if(idioma!="_us_en")
                  $("#mensaje_error").html("La fecha desde no es correcta");
              else
                  $("#mensaje_error").html("From date is not correct");

              $("#div_mensajes_error").fadeIn("slow");
          }

      });    */

    /*  $( "#fechaHastaFiltro_SalTrans_FM").blur(function(){
          if(!isDate($( "#fechaHastaFiltro_SalTrans_FM").val())&& !Empty($( "#fechaHastaFiltro_SalTrans_FM").val())){
              $("#mensaje_error").empty();
              if(idioma!="_us_en")
                  $("#mensaje_error").html("La fecha desde no es correcta");
              else
                  $("#mensaje_error").html("From date is not correct");

              $("#div_mensajes_error").fadeIn("slow");
          }

      });     */

    $( "#fechaDesdeFiltro_SalTrans_FM" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltro_SalTrans_FM" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});


    $("#consulta_SalTrans_FM").click(function(){
        var mensaje="";
        $(".requerido_SalTrans_FM").each(function(){
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
        if($("#buscar_SalTrans_FM").val()=="-1")
        {
            $(".requeridoFecha_SalTrans_FM").each(function(){
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
        }


        if(!isDate($( "#fechaDesdeFiltro_SalTrans_FM").val())&& !Empty($( "#fechaDesdeFiltro_SalTrans_FM").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fechaDesdeFiltro_SalTrans_FM").addClass("error_campo");
        }else if(!isDate($( "#fechaHastaFiltro_SalTrans_FM").val())&& !Empty($( "#fechaDesdeFiltro_SalTrans_FM").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"To date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Hasta no es correcta"+"<br>";
            $("#fechaHastaFiltro_SalTrans_FM").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fechaDesdeFiltro_SalTrans_FM").val().split("/")[1]
                +"/"+$("#fechaDesdeFiltro_SalTrans_FM").val().split("/")[0]
                +"/"+$("#fechaDesdeFiltro_SalTrans_FM").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fechaHastaFiltro_SalTrans_FM").val().split("/")[1]
                +"/"+$("#fechaHastaFiltro_SalTrans_FM").val().split("/")[0]
                +"/"+$("#fechaHastaFiltro_SalTrans_FM").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                $("#fechaDesdeFiltro_SalTrans_FM").addClass("error_campo");
                $("#fechaHastaFiltro_SalTrans_FM").addClass("error_campo");

            }else{
                $("#fechaDesdeFiltro_SalTrans_FM").removeClass("error_campo");
                $("#fechaHastaFiltro_SalTrans_FM").removeClass("error_campo");
            }
        }

        if(mensaje!=""){
            $("#tabla_consulta_SalTrans_FM").fadeOut("fast");
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            alert("error");
        }else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#tabla_consulta_SalTrans_FM").fadeIn("fast");
            consultarCuentaSaldoTransFondoMutualJSONData();
        }

    });

});

function cargarDetalleCuentaFondosMutuales(value){
    if(value!="-2") {
        detalleSaldoTransFondosMutualesJSONData();
        $("#tabla_consulta_SalTrans_FM").empty();
        $("#paginacion_tabla_consulta_SalTrans_FM").empty();

        //$("#fechas_SalTrans_FM").fadeOut("fast");
        // $("#fechaHastaFiltro_SalTrans_FM").val("");
        //$("#fechaDesdeFiltro_SalTrans_FM").val("");
        //$("#buscar_SalTrans_FM").val("5");
    }
    else{
        $("#numero_cuenta_SalTrans_FM").removeClass("error_campo");

        $("#titular_SalTrans_FM").html("");
        $("#unidadesBloqueadas_SalTrans_FM").html("");
        $("#unidadDisponible_SalTrans_FM").html("");
        $("#unidadesTotales_SalTrans_FM").html("");
        $("#VUI_SalTrans_FM").html("");
        $("#montoTransito_SalTrans_FM").html("");
        $("#totalMoneda_SalTrans_FM").html("");
        //$("#div_mutualFunds_BT .selectFormulario_MF_BT").each(function(){
        //    this.value="-2";
        //});
        //$("#div_mutualFunds_BT .inputFormulario_MF_BT").each(function(){
        //    this.value="";
        //});
        $("#div_mutualFunds_BT .spanFormulario_MF_BT").each(function(){
            this.innerHTML="";
        });

        $("#tabla_consulta_SalTrans_FM").empty();
        $("#paginacion_tabla_consulta_SalTrans_FM").empty();
        $("#saldo_BT_MutualFunds").attr("style","display: ");
        $("#saldo_BT_MutualFunds2").attr("style","display:none ");
        $("#saldo_BT_fechaSaldo").attr("style","display: none");
    }

};

function validarActivarFechas_SalTrans_FM(valor){
    //  var myDate = new Date();
    if(valor=="-1"){
        $("#fechas_SalTrans_FM").fadeIn("fast");
        //      $("#fechaHastaFiltro_SalTrans_FM").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
        $("#fechaDesdeFiltro_SalTrans_FM").val("");
    }
    else{
        $("#fechas_SalTrans_FM").fadeOut("fast");
        $("#fechaHastaFiltro_SalTrans_FM").val("");
        $("#fechaDesdeFiltro_SalTrans_FM").val("");
    }

};


function BalancesAndTransactionMutualFundsInfoPaginaJSONData(){
    var url = urlFondosMutualesCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BalancesAndTransactionMutualFundsInfoSuccess,null,null);
}


function BalancesAndTransactionMutualFundsInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var periodo = result.rango;
    var fondos = result.fondos;
    var codigo = result.codigo;
    idioma = result.idioma;


    $("#div_mutualFunds_BT .selectFormulario_MF_BT").each(function(){
        this.value="-2";
    });
    $("#div_mutualFunds_BT .inputFormulario_MF_BT").each(function(){
        this.value="";
    });
    $("#div_mutualFunds_BT .spanFormulario_MF_BT").each(function(){
        this.innerHTML="";
    });

    $("#tabla_consulta_SalTrans_FM").empty();
    $("#paginacion_tabla_consulta_SalTrans_FM").empty();

    $("#fechas_SalTrans_FM").attr("style","display: none");

    $("#saldo_BT_MutualFunds").attr("style","display: ");
    $("#saldo_BT_MutualFunds2").attr("style","display: none");
    $("#saldo_BT_fechaSaldo").attr("style","display: none");

    $("#btnVolverPortafolioFM").addClass("oculto");

    $("#titular_SalTrans_FM").html("");
    $("#unidadesBloqueadas_SalTrans_FM").html("");
    $("#unidadDisponible_SalTrans_FM").html("");
    $("#unidadesTotales_SalTrans_FM").html("");
    $("#VUI_SalTrans_FM").html("");
    $("#montoTransito_SalTrans_FM").html("");
    $("#totalMoneda_SalTrans_FM").html("");

    console.log('carga bien tabla otros');



    //$("#marco_trabajo").css("height","1200px");
    if(codigo=="0"){
        if(fondos!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("numero_cuenta_SalTrans_FM", fondos,"Select","-2");
            else
                cargar_selectPersonal("numero_cuenta_SalTrans_FM", fondos,"Seleccione","-2");

        if(periodo!=null)
            cargar_selectPersonal2("buscar_SalTrans_FM", periodo);
        $("#div_mutualFunds_BT").attr("style","display: ");
        $("#div_noInfo_BT_mutualFunds").attr("style","display: none");
        if(idioma=="_us_en")
            $("#linkBloqueo_mutual").html("<span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado'>Units Blocked </span><span>:</span>");
        else
            $("#linkBloqueo_mutual").html("<span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado'>Unidades Bloqueadas </span><span>:</span>");

    }else{
        $("#div_mutualFunds_BT").attr("style","display: none");
        $("#div_noInfo_BT_mutualFunds").attr("style","display: ");
    }

    BloqueosMutualFundsInfoPaginaJSONData();

}

function detalleSaldoTransFondosMutualesJSONData(){
    var url = urlDetalleBalancesAndTransactionFondosMutuales_BT;
    var param={};
    var jsonMutualFunds=[];

    jsonMutualFunds[0] =  $("#numero_cuenta_SalTrans_FM").get(0).value;
    param.jsonMutualFunds=JSON.stringify({"parametros":jsonMutualFunds});

    sendServiceJSON(url,param,detalleSaldoTransFondosMutualesSuccess,null,null);
}


function detalleSaldoTransFondosMutualesSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var  tipoTrans = result.tipoTransacciones;
    var mensaje = result.mensaje;
    var codigo = result.codigo;
    var numCuenta = result.numeroCuenta;

    if(codigo=="0"){
        $("#titular_SalTrans_FM").html(detalle[9]);
        if(detalle[3]!="0,00000000"){
//            if(idioma=="_us_en")
//                $("#linkBloqueo_mutual").html("<a id='"+numCuenta+"' onclick='detalleBloqueoMutual(this.id);' style='cursor:pointer; text-decoration: underline' ><span class='CBT_label_montoBloqueado' title='Click to see details of blocked amount' id='fondossaldos_TAGBloqueado'>Units Blocked </span><span>:</span></a>");
//            else
//                $("#linkBloqueo_mutual").html("<a id='"+numCuenta+"' onclick='detalleBloqueoMutual(this.id);' style='cursor:pointer; text-decoration: underline' ><span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado' title='Haga click para ver detalle del monto bloqueado'>Unidades Bloqueadas</span><span>:</span></a>");
            if(idioma=="_us_en")
                $("#linkBloqueo_mutual").html("<span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado'>Units Blocked </span><span>:</span>");
            else
                $("#linkBloqueo_mutual").html("<span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado'>Unidades Bloqueadas </span><span>:</span>");
        }else{
            if(idioma=="_us_en")
                $("#linkBloqueo_mutual").html("<span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado'>Units Blocked </span><span>:</span>");
            else
                $("#linkBloqueo_mutual").html("<span class='CBT_label_montoBloqueado' id='fondossaldos_TAGBloqueado'>Unidades Bloqueadas </span><span>:</span>");
        }
        $("#unidadesBloqueadas_SalTrans_FM").html(detalle[3]);
        // $("#moneda_SalTrans_FM").html(detalle[1]);
        $("#moneda_SalTrans_FM").html(createCurrency(detalle[1]));
        $("#unidadDisponible_SalTrans_FM").html(detalle[4]);
        $("#unidadesTotales_SalTrans_FM").html(detalle[2]);
        $("#VUI_SalTrans_FM").html(detalle[5]);
        $("#montoTransito_SalTrans_FM").html(detalle[7]);
        $("#totalMoneda_SalTrans_FM").html(detalle[6]);

        $("#saldo_BT_MutualFunds").attr("style","display: none");
        $("#saldo_BT_MutualFunds2").attr("style","display: ");
        $("#saldo_BT_fechaSaldo").attr("style","display: ");
        $("#saldo_BT_fechaSaldo").html(" <b>"+detalle[8]+"</b>");



        if(tipoTrans!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("tipo_transaccion_SalTrans_FM", tipoTrans,"All","All");
            else
                cargar_selectPersonal("tipo_transaccion_SalTrans_FM", tipoTrans,"Todos","All");
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }
    $("#consulta_SalTrans_FM").click();


}

function consultarCuentaSaldoTransFondoMutualJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlConsultarFondosMutualesBalancesAndTransaction;
    var param={};
    var jsonMutualFundsTabla=[];

    jsonMutualFundsTabla[0] =  $("#numero_cuenta_SalTrans_FM").get(0).value;

    jsonMutualFundsTabla[1] = $("#buscar_SalTrans_FM").get(0).value;
    jsonMutualFundsTabla[2] = $("#fechaDesdeFiltro_SalTrans_FM").get(0).value;
    jsonMutualFundsTabla[3] = $("#fechaHastaFiltro_SalTrans_FM").get(0).value;

    jsonMutualFundsTabla[4] = $("#tipo_transaccion_SalTrans_FM").get(0).value;

    param.jsonMutualFundsTabla=JSON.stringify({"parametros":jsonMutualFundsTabla});

    sendServiceJSON(url,param,consultarCuentaSaldoTransFondoMutualSuccess,null,null);
}


function consultarCuentaSaldoTransFondoMutualSuccess(originalRequest){
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
    // var tabla_id='tabla_consulta_SalTrans_FM';
    var tabla_id='tabla_consulta_SalTrans_FM';
    // var id_contenedor='div_tabla_consulta_SalTrans_FM';

    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";
    var mensaje = result.mensaje;
    var codigo = result.codigo;

    $("#div_carga_espera").addClass("oculto");
    if(codigo=="0"){
        // tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);
        crearTablaV2(tabla_id,columnas,datos,"",false);

        oTable =$("#"+tabla_id).dataTable({
            "iDisplayLength": tamano_pag,
            "bJQueryUI": false,
            "sPaginationType": "full_numbers",
            "bFilter": false,
            "bSort": false,
            "bPaginate": true,
            "bDestroy": true,
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
/*
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
        }*/
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


//
//
}




function print_SALDOS_FONDOS(){
    var miValue = $("#numero_cuenta_SalTrans_FM" ).val();
    $("#numero_cuenta_SalTrans_FM option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#tipo_transaccion_SalTrans_FM" ).val();
    $("#tipo_transaccion_SalTrans_FM option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#buscar_SalTrans_FM" ).val();
    $("#buscar_SalTrans_FM option[value='"+miValue+"']").attr("selected",true);

    $('#numero_cuenta_SalTrans_FM_select').html($('#numero_cuenta_SalTrans_FM option:selected').text());

    // printPageElement('div_SALDOS_FONDOS');  //Print EDO CUENTA
    $("#div_mutualFunds_BT").printThis({
        importCSS: true,            // import parent page css
        importStyle: true,         // import style tags
        printContainer: true,
    });
}
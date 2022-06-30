var urlAllMyPortafolioCargar="AllMyPortafolio_cargarPortafolio.action";
var urlConsultarCtaBalancesAndTransaction_BT="Accounts_consultarCuentaSaldoTrans.action";
var cuentaEstadoCuentaPortafolio;
var FMEstadoCuentaPortafolio;
var OIEstadoCuentaPortafolio;
var colocacionesEstadoCuentaPortafolio;
var TDCEstadoCuentaPortafolio;
var idioma ="";
var noInfo ="";
$(document).ready(function(){

    $("#imprimirConsolidado").click(function(){
        printPageElement('div_CONSOLIDADO');
    });

    $('#enviar').click(function(){
        var valido=true;

        if($("#id").get(0).value.isEmpty()){
            $("#mensaje").html("Teclee id");
            $("#mensaje").removeClass("blue");
            $("#mensaje").hasClass("red");
            valido=false;
        }
        if(valido)
            AllMyPortafolioCargarJSONData();

    });



    $('#btnVolverPortafolio').click(function(){
        $("#div_SALDOS_CUENTAS").fadeOut("fast");

        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").addClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").fadeIn("slow");
        });
        AllMyPortafolioCargarJSONData();
    });


    $('#btnVolverPortafolioColocaciones').click(function(){
        $("#div_SALDOS_COLOCACIONESS").fadeOut("fast");

        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").addClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").fadeIn("slow");
        });
        AllMyPortafolioCargarJSONData();
    });

    $('#btnVolverPortafolioFM').click(function(){
        $("#div_SALDOS_FONDOS").fadeOut("fast");

        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").addClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").fadeIn("slow");
        });
        AllMyPortafolioCargarJSONData();
    });

    $('#btnVolverPortafolioOI').click(function(){
        $("#div_SALDOS_FONDOS_OI").fadeOut("fast");

        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").addClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").fadeIn("slow");
        });
        AllMyPortafolioCargarJSONData();
    });

    $('#btnVolverPortafolioCC').click(function(){
        $("#div_ESTADO_CUENTA_TARJETA").fadeOut("fast");

        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").addClass("opcion_seleccionada");
            $("#div_CONSOLIDADO").fadeIn("slow");
        });
        AllMyPortafolioCargarJSONData();
    });
});



function seleccionarVerCuentasOpcion(id){
//    seleccionarOpcionMenu("ESTADO_CUENTA");
////    $("#estado_cuenta_numero_cuenta").val(id);
//    detalleEdoCuentaPortafolioJSONData(id);
//    $("#estado_cuenta_numero_cuenta").val(id);
//    $("#estado_cuenta_imprimir").fadeIn("fast");
    alert("VerCuentas: "+id);
}

function detalleEdoCuentaPortafolioJSONData(id){
    var url = urlDetalleEdoCuenta;
    var param={};
    var jsonDetalleEdoCuenta=[];


    jsonDetalleEdoCuenta[0] =  id;
    jsonDetalleEdoCuenta[1] = $("#estado_cuenta_fecha_mes").get(0).value;
    jsonDetalleEdoCuenta[2] = $("#estado_cuenta_fecha_anio").val();


    param.jsonDetalleEdoCuenta=JSON.stringify({"parametros":jsonDetalleEdoCuenta});

    sendServiceJSON(url,param,detalleEdoCuentaPortafolioSuccess,null,null);
}


function detalleEdoCuentaPortafolioSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    $("#titular_AS").html(detalle.cliente);
    $("#moneda_AS").html(detalle.moneda);
    $("#bloqueado_AS").html(detalle.bloqueado);
    $("#diferido_AS").html(detalle.diferido);
    $("#disponible_AS").html(detalle.saldoDisp);
    $("#saldo_actual_AS").html(detalle.saldoActual);


    crearTabla('estado_cuenta_div_tabla_consulta','estado_cuenta_tabla_consulta',columnas,datos);
//
//
    var oTable = $('#estado_cuenta_tabla_consulta').dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "" },
            {  "sClass": "center" },
            {  "sClass": "right" },
            {  "sClass": "right" },
            { "sClass": "right" }
        ],
        "oLanguage": {
            "sZeroRecords": "No Hay informaci&oacute;n",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


}

function seleccionarVerColocacionesOpcion(id){
    alert("VerColocaciones: "+id) ;
}

function seleccionarVerFondosOpcion(id){
    alert("VerFondos: "+id) ;
}

function seleccionarVerOtrasInvOpcion(id){
    alert("VerOtrasInv: "+id) ;
}


function AllMyPortafolioCargarJSONData(){
    var url = urlAllMyPortafolioCargar;
    var param={};
    $("#div_carga_espera").css("width",$("espacio_divs").css("width"));
    $("#div_carga_espera").css("height",$("espacio_divs").css("height"));
    $("#div_carga_espera").removeClass("oculto");

    sendServiceJSON(url,param,AllMyPortafolioCargarSuccess,null,null);
}


function AllMyPortafolioCargarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var portafolio = result.portafolio;
    idioma = result.idioma;

    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    var divPrincipal=$("#div_portafolio");
    divPrincipal.empty();
    //$("#marco_trabajo").css("height","3800px");
    var columnas ="";
    var info="";
    $.each(portafolio,function(dc,item){
        var data=item;
        var divName=$("#"+data.numCartera);
        var div= $(document.createElement('div')).appendTo(divPrincipal);
        div.attr("id",data.numCartera);
        div.addClass("section")

        var divContainer = $(document.createElement('div')).appendTo(div);
        divContainer.addClass("section__container")
        divContainer.addClass("container")

        var fieldset = $(document.createElement('div')).appendTo(divContainer);
        // fieldset.attr("class","div_consulta");

        var divTop = $(document.createElement('div')).appendTo(divContainer);
        divTop.addClass("section__top")

        var imgIcon =  $(document.createElement('img')).appendTo(divTop);
        imgIcon.addClass("section__icon");
        imgIcon.attr("src","../vbtonline/resources/img/icons/ic_dpdown_creditcards_statement.png")

        var divHeader = $(document.createElement('div')).appendTo(divTop);
        divHeader.addClass("section__header")


        var legend= $(document.createElement('span')).appendTo(divHeader);
        // legend.attr("class","datos");
        legend.addClass("section__titles");
        if(idioma=="_us_en")
          legend.html("<b><span id='tag_cartera_consolidado'> "+"Client ID No: "+'</span>'+data.numCartera+"</b>");
        else
          legend.html("<b><span id='tag_cartera_consolidado'>"+"Cartera: "+'</span>'+data.numCartera+"</b>");

        var fieldset2= $(document.createElement('div')).appendTo(divContainer);
        // fieldset2.attr("class","div_consulta");
        fieldset2.addClass("section__content")

        $.each(data.tabla,function(d,item){

            var data_tablas=item;
            var divTable = $(document.createElement('div')).appendTo(fieldset2);
            divTable.addClass("table")

            legend= $(document.createElement('span')).appendTo(divTable);
            // legend.attr("class","datos");
            legend.addClass("table__title")
            legend.html("<b><span id='tag_"+data_tablas.nombre.toString().replace(" ","_")+"_consolidado'"+'</span>'+data_tablas.nombre+"</b>");
            var div2= $(document.createElement('div')).appendTo(divTable);
            div2.attr("id","div_"+data_tablas.div+"_"+data.numCartera);
            var datosTabla = data_tablas.datos;
            columnas= datosTabla.contenidoTabla_culumnas;
            info= datosTabla.contenidoTabla_info;

            // crearTabla("div_"+data_tablas.div+"_"+data.numCartera,data_tablas.div+"_"+data.numCartera+"_tabla",columnas,info);
            crearTablaV3("div_"+data_tablas.div+"_"+data.numCartera,data_tablas.div+"_"+data.numCartera+"_tabla",columnas,info,"",false);
//
//
/*            var oTable = $("#"+data_tablas.div+"_"+data.numCartera+"_tabla").dataTable({
                "iDisplayLength": 25,
                "bJQueryUI": true,
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": noInfo,
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });     */
            var oTable = $("#"+data_tablas.div+"_"+data.numCartera+"_tabla").dataTable({
                "iDisplayLength": 25,
                "bJQueryUI": false,
                "bFilter": false,
                "bSort": false,
                "bPaginate" : false,
                "bDestroy": true,
                "aoColumns": [
                    { "sClass": "center" },
                    { "sClass": "center"},
                    { "sClass": "right" }
                ],
                "oLanguage": {
                    "sZeroRecords": noInfo,
                    "sInfo": "",
                    "sInfoEmpty": ""
                }
            });

        });

    });

//    BalancesAndTransactionInfoPaginaJSONData();

    $("#div_carga_espera").addClass("oculto");

}




function consultarCuentaDesdePortafolio(cuenta){

    var opcion= "ESTADO_CUENTA";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    AccountsStatementFromPortafolioInfoPaginaJSONData();
    cuentaEstadoCuentaPortafolio=cuenta;

}


function AccountsStatementFromPortafolioInfoPaginaJSONData(){
    $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
    var url = urlAccountStatementCargar;
    var param={};
//    $("#mensaje_error").empty();
//    $("#div_mensajes_error").fadeOut("fast");
//    blanquearFormulariosPersonal("div_accountStatement_account","_Accounts_AE");
    $("#div_accountStatement_account .selectFormulario_Accounts_AE").each(function(){
        this.value="-2";
    });
    $("#div_accountStatement_account .inputFormulario_Accounts_AE").each(function(){
        this.value="";
    });
    $("#div_accountStatement_account .spanFormulario_Accounts_AE").each(function(){
        this.innerHTML="";
    });
    $("#estado_cuenta_fecha_mes").val(mes);
    $("#estado_cuenta_fecha_mes").removeClass("error_campo");
    $("#estado_cuenta_fecha_anio").val(anio);
    $("#estado_cuenta_fecha_anio").removeClass("error_campo");
    $("#estado_cuenta_imprimir").fadeOut("fast");
    $("#estado_cuenta_div_tabla_consulta").empty();

    sendServiceJSON(url,param,AccountsStatementFromPortafolioSuccess,null,null);
}


function AccountsStatementFromPortafolioSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var cuentasAS = result.cuentas;
    meses = result.meses;
    mes = result.mes;
    anio = result.anio;
    idioma = result.idioma;
    var codigo = result.codigo;


    if(codigo=="0" && cuentasAS!=null){
        if(idioma=="_us_en")
            cargar_selectPersonal("estado_cuenta_numero_cuenta", cuentasAS,"Select","-2");
        else
            cargar_selectPersonal("estado_cuenta_numero_cuenta", cuentasAS,"Seleccione","-2");
        if(meses!=null)
            cargar_selectPersonal2("estado_cuenta_fecha_mes", meses);

        $("#estado_cuenta_fecha_mes").val(mes);
        $("#estado_cuenta_fecha_anio").val(anio);
        $("#estado_cuenta_numero_cuenta").val(cuentaEstadoCuentaPortafolio);
        $("#div_accountStatement_account").attr("style","display: ");
        $("#div_noInfo_accountStatement_account").attr("style","display: none");
        detalleEdoCuentaJSONData();
    }else{
        $("#div_accountStatement_account").attr("style","display: none");
        $("#div_noInfo_accountStatement_account").attr("style","display: ");
    }

}

/////////////FONDOS MUTUALES /////////////////////////////////7


function BTFMDesdePortafolio(fm){

    var opcion= "SALDOS_FONDOS";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    $("#btnVolverPortafolioFM").removeClass("oculto");
    FMEstadoCuentaPortafolio=fm;
    BTMutualFundsFromPortafolioInfoPaginaJSONData();


}

function BTMutualFundsFromPortafolioInfoPaginaJSONData(){
    $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
    var url = urlFondosMutualesCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BTMutualFundsFromPortafolioInfoSuccess,null,null);
}

function BTMutualFundsFromPortafolioInfoSuccess(originalRequest){
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

    $("#div_tabla_consulta_SalTrans_FM").empty();
    $("#fechas_SalTrans_FM").attr("style","display: none");

    $("#saldo_BT_MutualFunds").attr("style","display: ");
    $("#saldo_BT_MutualFunds2").attr("style","display: none");
    $("#saldo_BT_fechaSaldo").attr("style","display: none");




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

    $("#numero_cuenta_SalTrans_FM").val(FMEstadoCuentaPortafolio);
    $("#numero_cuenta_SalTrans_FM").change();
    BloqueosMutualFundsInfoPaginaJSONData();

}



/////////////OTRAS INVERSIONES /////////////////////////////////7


function BTOIDesdePortafolio(oi){

    var opcion= "SALDOS_FONDOS_OI";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    OIEstadoCuentaPortafolio=oi;
    BTOtrasInversionesFromPortafolioInfoPaginaJSONData();


}


function BTOtrasInversionesFromPortafolioInfoPaginaJSONData(){
    $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
    var url = urlOtrasInversionesCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    sendServiceJSON(url,param,BTOtrasInversionesFromPortafolioInfoSuccess,null,null);
}


function BTOtrasInversionesFromPortafolioInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var periodo = result.rango;
    var fondos = result.fondos;
    var codigo = result.codigo;
    idioma = result.idioma;

    $("#div_OtherInvestments_BT .selectFormulario_Account_BA").each(function(){
        this.value="-2";
    });
    $("#div_OtherInvestments_BT .inputFormulario_OI_BT").each(function(){
        this.value="";
    });
    $("#div_OtherInvestments_BT .spanFormulario_OI_BT").each(function(){
        this.innerHTML="";
    });

    $("#div_tabla_consulta_SalTrans_OI").empty();
    $("#fechas_SalTrans_OI").attr("style","display: none");
    $("#saldo_BT_OtherInvestments").attr("style","display: ");
    $("#saldo_BT_OtherInvestments2").attr("style","display: none");
    $("#saldo_BT_OtherInvestmentsFecha").attr("style","display: none");




    //$("#marco_trabajo").css("height","1200px");

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
    $("#btnVolverPortafolioOI").removeClass("oculto");
    $("#numero_cuenta_SalTrans_OI").val(OIEstadoCuentaPortafolio);
    $("#numero_cuenta_SalTrans_OI").change();
    BloqueosOtherInvestmentsInfoPaginaJSONData();

}


///////////// COLOCACIONES /////////////////////////////////7


function BTColocacionesDesdePortafolio(colocacion){

    var opcion= "SALDOS_COLOCACIONES";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    colocacionesEstadoCuentaPortafolio=colocacion;
    BTColocacionesFromPortafolioInfoPaginaJSONData();


}


function BTColocacionesFromPortafolioInfoPaginaJSONData(){
    $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
    var url = urlColocacionesCargar_BT;
    var param={};
    $("#div_tabla_consulta_BT_Colocaciones").empty();
    $("#tag_saldo_colocaciones").attr("style","display: ");
    $("#tag_saldo_colocaciones2").attr("style","display: none");
    $("#tag_saldo_colocacionesFecha").attr("style","display: none");

    sendServiceJSON(url,param,BTColocacionesFromPortafolioInfoSuccess,null,null);
}


function BTColocacionesFromPortafolioInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var colocaciones = result.colocaciones;
    var codigo = result.codigo;
    idioma = result.idioma;

    if(codigo=="0" && colocaciones != null){
        $("#div_colocacionesSaldosTrans").attr("style","display: ");
        $("#noPoseeColocacionesSaldosTrans").attr("style","display: none");
        $("#titular_BT_Colocaciones").html("");
        $("#montoApertura_BT_Colocaciones").html("");
        $("#moneda_BT_Colocaciones").html("");
        $("#montoBloqueado_BT_Colocaciones").html("");
        if(idioma=="_us_en")
            $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'>Blocked Amount </span><span>:</span>");
        else
            $("#linkBloqueo").html("<span class='CBT_label_montoBloqueado' id='colocacionessaldos_TAGBloqueado'>Monto Bloqueado </span><span>:</span>");
        $("#fechaApertura_BT_Colocaciones").html("");
        $("#montoVencimiento_BT_Colocaciones").html("");
        $("#fechaVencimiento_BT_Colocaciones").html("");
        $("#tasa_BT_Colocaciones").html("");

        if(colocaciones!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("colocaciones_numero_cuenta_BT", colocaciones,"Select","-2");
            else
                cargar_selectPersonal("colocaciones_numero_cuenta_BT", colocaciones,"Seleccione","-2");

        /*Carga en background la pantalla de bloqueos*/

        $("#colocaciones_numero_cuenta_BT").val(colocacionesEstadoCuentaPortafolio);
        $("#colocaciones_numero_cuenta_BT").change();
        $("#btnVolverPortafolioColocaciones").removeClass("oculto");
        BlockedAmountsColocacionesInfoPaginaJSONData();
    }else{
        $("#div_colocacionesSaldosTrans").attr("style","display: none");
        $("#noPoseeColocacionesSaldosTrans").attr("style","display: ");
    }

}


///////////// Tarjeta de credito /////////////////////////////////7


function BTTDCDesdePortafolio(tarjeta){

    var opcion= "ESTADO_CUENTA_TARJETA";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    TDCEstadoCuentaPortafolio=tarjeta;
    BTTDCFromPortafolioInfoPaginaJSONData();


}

function BTTDCFromPortafolioInfoPaginaJSONData(){
    $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
    var url = urlCargar_TDC_AE;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    $("#div_creditCard_AE .spanFormulario_creditCards_AE").each(function(){
        this.innerHTML="";
    });

    $("#div_tabla_consulta_TDC_AE").empty();

    sendServiceJSON(url,param,BTTDCFromPortafolioInfoSuccess,null,null);
}


function BTTDCFromPortafolioInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var tarjetas = result.tarjetas;
    var codigo = result.codigo;
    idioma = result.idioma;
    var fecha = result.fecha;

    if(codigo=="0"){
        $("#alertaSeguridadTDC #pantalla").val("div_creditCard_AE");
        $("#div_creditCard_AE_alertaSeguridad").attr("style", "display: ");
        $("#div_noInfo_accountStatement_creditCard").attr("style", "display: none");
        $("#div_creditCard_AE").attr("style", "display: none");
        $("#estado_cuenta_TDC_imprimir").fadeOut("fast");
        $("#numero_cuenta_TDC_AE").removeClass("error_campo");
        $("#btnVolverPortafolioCC").removeClass("oculto");

        if(fecha!=null)
            cargar_selectPersonal2("date_TDC_AE", fecha);

        if(tarjetas!=null)
            if(idioma=="_us_en")
                cargar_selectPersonal("numero_cuenta_TDC_AE", tarjetas,"Select","-2");
            else
                cargar_selectPersonal("numero_cuenta_TDC_AE", tarjetas,"Seleccione","-2");


        $("#numero_cuenta_TDC_AE").val(TDCEstadoCuentaPortafolio);
        $("#consulta_TDC_AE").click();
    }else{
        $("#div_creditCard_AE_alertaSeguridad").attr("style", "display: none");
        $("#div_creditCard_AE").attr("style", "display: none");
        $("#div_noInfo_accountStatement_creditCard").attr("style", "display: ");
    }




}









function consultarSaldosDesdePortafolio(cuenta){

    var opcion= "SALDOS_CUENTAS";
    if($("#div_"+opcion).css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }
    cuentaEstadoCuentaPortafolio=cuenta;
    BTransactionInfoPaginaJSONData();


}


function BTransactionInfoPaginaJSONData(){
    $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
    var url = urlAccountStatementCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    $("#div_account_BalancesAndTransactions .selectFormulario_Account_BA").each(function(){
        this.value="-2";
    });
    $("#div_account_BalancesAndTransactions .inputFormulario_Account_BA").each(function(){
        this.value="";
    });
    $("#div_account_BalancesAndTransactions .spanFormulario_Account_BA").each(function(){
        this.innerHTML="";
    });
    $("#fechas_BT").attr("style","display: none");
    $("#tipo_transaccion_BT").empty();
    $("#transaccion_desde_BT").empty();


    $("#estado_cuenta_div_tabla_consulta_BT").empty();
//    $("#tagAccount_DatosCuenta_BT").attr("style","display: ");
    sendServiceJSON(url,param,BTransactionInfoSuccess,null,null);
}


function BTransactionInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var cuentasBT = result.cuentas;
    var periodo = result.transaccionesPeriodo;
    var codigo = result.codigo;
    idioma = result.idioma;

    if(codigo=="0" && cuentasBT!=null){
        if(idioma=="_us_en")
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BT", cuentasBT,"Select","-2");
        else
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BT", cuentasBT,"Seleccione","-2");

        if(periodo!=null)
            cargar_selectPersonal2("transaccion_desde_BT", periodo);

        $("#div_account_BalancesAndTransactions").attr("style","display: ");
        $("#noInfo_balancesAndTransactions_account").attr("style","display: none");
        if(idioma=="_us_en")
            $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked </span><span>:</span>");
        else
            $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Bloqueado </span><span>:</span>");
    }else{
        $("#div_account_BalancesAndTransactions").attr("style","display: none");
        $("#noInfo_balancesAndTransactions_account").attr("style","display: ");
    }
    $("#botonVolverASaldoCuentas").attr("style","display: none");
    $("#btnVolverPortafolio").removeClass("oculto");
    $("#estado_cuenta_numero_cuenta_BT").val(cuentaEstadoCuentaPortafolio);
    $("#estado_cuenta_numero_cuenta_BT").change();
    consultarCuentaSaldoTransJSONData();
    BlockedAmountInfoPaginaJSONData();

}
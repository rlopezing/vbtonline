var menu=[];
var bk_opcion;
var urlSalir="Login";
var urlsalirAVBT="login_abrir_vbtbank";
var urllatido="Login_latido";
var urlHome="Login_home.action";

var urlMostrarPantalla="mostrarPantalla.action";
var urlcambioIdioma="Login_cargarProperties.action";
var urlcambiarIdioma="Login_cambiarIdioma.action";
var urlcargarCalendario="Login_cargarCalendario.action";
var calendario_mes=new Date().getMonth();
var calendario_ano;
var validationMethod="div_METODOS_VALIDACION";
var origenMetodosValidacion="";
var mostrarSeguridad="";
var showAct="";
var telefonosUsuario;
var correosUsuario;

var fechaLogueo="";
var horalogueo="";
var nombreUsuario="";
var fechaHoy="";
var horaHoy="";
var valores="";
var idioma="";
var numReferenciaGlobal="";
var montosGlobal="";
var label_idioma="es";

var label_es=[];

var label_en=[];

var title_es=[];

var title_en=[];



/*$(window).blind("beforeClose",function(e) {
    sendServiceJSON(urlSalir,null,null,null,null);
});*/

$(document).ready(function(){

    $.ajaxSetup({cache: false});

//    $.getJSON("http://jsonip.appspot.com?callback=?",
//        function(data){
//            $("#IpLocal").val(data.ip);
//        });
/* $(document).keydown(function(e) {
    CancelKeys(e);
});*/


    $("#span_cancelar").fadeOut("fast");
    $("#span_aceptar").fadeOut("fast");
    $("#detalle_transferencia_fc").fadeOut("fast");

    $("#btn_cerrar_detalleTransferencia").click(function(){
        $("#detalle_transferencia_fc").fadeOut("fast");
    });

    $("#btn_imprimir_detalleTransferencia").click(function(){
        printPageElement("detalle_transferencia_fc");
    });

    $("#btn__sir_cancelar").mouseover(function(){
        $("#span_cancelar").fadeOut("fast");
    });

    $("#btn__sir_aceptar").mouseover(function(){
        $("#span_aceptar").fadeOut("fast");
    });

    $("#btn__sir_aceptar").mouseenter(function(){
        $("#span_aceptar").fadeIn("fast");
    });

    $("#btn__sir_cancelar").mouseenter(function(){
        $("#span_cancelar").fadeIn("fast");
    });


//    $("#container").notify();

    $("#btn__sir_aceptar").click(function(){
        salir();
    });

    $("#btn__sir_cancelar").click(function(){
            seleccionarOpcionMenu("home");
    });


    $("#cerrar_div_mensaje_error").click(function(){
        $("#div_mensajes_e" +
            "rror").fadeOut("fast");
    });

    $("#cerrar_div_mensajes_info_desc_tabla").click(function(){
        $("#div_mensajes_info_desc_tabla").fadeOut("fast");
    });



    $("#idioma_es").click(function(){
        if(label_idioma!="es"){
            $.each(label_es,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });

            $.each(title_es,function(pos,item){
                $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
            });
            label_idioma="es";
            cambiarIdiomaJSONData(label_idioma);
        }

    });

    $("#idioma_en").click(function(){
        if(label_idioma!="en"){
            $.each(label_en,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });
            $.each(title_en,function(pos,item){
                $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
            });
            label_idioma="en";
            cambiarIdiomaJSONData(label_idioma);
        }
    });



});


function crearMenu(){
    var opciones=menu;
    var div_menu = $("#box_menu");
    div_menu.empty();
    var  ul_principal= $(document.createElement('ul')).appendTo(div_menu);
    ul_principal.attr('id',"nav");

    $.each(opciones,function(o,item){
        var opcion=menu[o];
        var li_opcion=$(document.createElement('li')).appendTo(ul_principal);
        var a=$(document.createElement('a')).appendTo(li_opcion);
        var span= $(document.createElement('span')).appendTo(a);
        span.html(opcion.html_span);
        span.attr("id",opcion.html_spanID);


        if(opcion.estilo!=""){
            a.attr('class',opcion.estilo);
            var  ul_segundario= $(document.createElement('ul')).appendTo(li_opcion);

            var sub_opciones=opcion.sub_opciones;
            $.each(sub_opciones,function(so,item){
                var sub_opcion=sub_opciones[so];
                var li_sub_opcion=$(document.createElement('li')).appendTo(ul_segundario);
                li_sub_opcion.attr('id',sub_opcion.id);
                $("#div_"+sub_opcion.id).removeClass("div_opcion_menu");
                li_sub_opcion.attr('class',sub_opcion.codope+"_0");
                var a_sub_opcion=$(document.createElement('a')).appendTo(li_sub_opcion);
                var span_sub_opcion= $(document.createElement('span')).appendTo(a_sub_opcion);
                span_sub_opcion.html(sub_opcion.span_html);
                span_sub_opcion.attr("id",sub_opcion.span_htmlID);
                li_sub_opcion.click(function(){
                    seleccionarOpcion(sub_opcion.id);
                });
            });
        }else{
            li_opcion.attr('id',opcion.id);
            $("#div_"+opcion.id).removeClass("div_opcion_menu");
            li_opcion.attr('class',opcion.codope+"_0");
            li_opcion.click(function(){
                seleccionarOpcion(opcion.id);
            });
        }
    });
    //$("#div_CAMBIAR_CLAVE").removeClass("div_opcion_menu");
    $(".div_opcion_menu").remove();
    $("#salir").css("color","red");


}

function seleccionarOpcion(opcion){

        if (opcion!="SALIR"){
            if(cambioClave=="S"){
               opcion="CAMBIAR_CLAVE_PRINCIPAL";
            }
        }
    validarPantalla_a_mostrar(opcion);
}




function validarPantalla_a_mostrar(opcion){
    var url = urlMostrarPantalla;
    var param={};
    var menu={};

    param.jsonOpcion=opcion;

    sendServiceJSON(url,param,validarPantalla_a_mostrarSuccess,null,null);

}

function validarPantalla_a_mostrarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    if(result.mensaje=="TERMINOS_CONDICIONES"){
        bk_opcion=result.jsonOpcion;
        seleccionarOpcionMenu("terminos_condiciones_transferencia");
    }else if(result.mensaje=="VALIDAR"){
            bk_opcion=result.jsonOpcion;
            validarClaveProvisionalOpeEspJSONData();
            seleccionarOpcionMenu("validarOpeEsp");
    }else if(result.jsonOpcion=="TARJETA_COORDENADAS_ACTIVA"){

            validarActivacionTarjetaCoordenadasJSONData();
            if(showAct=="S"){
                seleccionarOpcionMenu("METODOS_VALIDACION");
                mainValidationMethods("tarjetaCoordenadas");
            }else{
                seleccionarOpcion("home");
            }

    }else{
            if(result.jsonOpcion!=null)
              seleccionarOpcionMenu(result.jsonOpcion);
            else{
                validarClaveProvisionalOpeEspJSONData();
                seleccionarOpcionMenu("validarOpeEsp");
            }

        }

    //MostrarPantalla
}

function seleccionarOpcionMenu(opcion){
    if($("#div_"+opcion).css("display")=='none'){
        $(".div_menu").fadeOut("fast");
        $(".div_menu2").fadeOut("fast");
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
            if(cambioClave == 'S'){
                $("#div_AVISOPrincipal").fadeIn("slow");
            }else{
                $("#div_AVISOPrincipal").fadeOut("fast");
            }
        });

    }
    $("#div_mensajes_error").fadeOut("fast");
    cargarDataOpcion(opcion);

}

function seleccionarOpcionBack(opcion){
    if($("#div_"+opcion).css("display")=='none'){
        $(".div_menu").fadeOut("fast");
        $(".div_menu2").fadeOut("fast");
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
            if(cambioClave == 'S'){
                $("#div_AVISOPrincipal").fadeIn("slow");
            }else{
                $("#div_AVISOPrincipal").fadeOut("fast");
            }
        });

    }
    //$("#div_mensajes_error").fadeOut("fast");
}



function seleccionarHome(opcion){
    if($("#div_"+opcion).css("display")=='none'){
        $(".div_menu").fadeOut("fast");
        $(".div_menu2").fadeOut("fast");
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_"+opcion).addClass("opcion_seleccionada");
            $("#div_"+opcion).fadeIn("slow");
        });

    }

    cargarDataOpcion(opcion);

}


function cargarDataOpcion(opcion){

    $("#div_carga_espera").addClass("oculto");
    if(cambioClave=="S"){
        opcion="CAMBIAR_CLAVE_PRINCIPAL";
    }
    if(opcion=="CALENDARIO_BANCARIO"){
        cargarData_calendar(new Date().getMonth(),parseInt(new Date().getYear())+parseInt(1900));
    }if(opcion=="TRANSFERENCIA_EXTERNA"){
        validarTransfer="";
        cargarData_toOtherBank();
        return true;
    }else if(opcion=="TRANSFERENCIA_EXTERNA_FC"){
        cargarData_toOtherBank();
        return true;
        //FC_cargarData_toOtherBank();
    }else if(opcion=="GRUPOS"){
        BackOfficeGruposJSONData();
    }else if(opcion=="USUARIOS"){
        BackOfficeUsuariosJSONData();
    }else if(opcion=="BITACORA"){
        cargar_pagina_new=true;
        $("#fechaDesdeFiltroBitacora").val("");
        $("#fechaHastaFiltroBitacora").val("");
        BackOfficeBitacoraJSONData();
    }else if(opcion=="EXCEPCIONES"){
        $("#fechaDesdeFiltro_sucesos").val("");
        $("#fechaHastaFiltro_sucesos").val("");
        consultarSucesosJSONData();
    }else if(opcion=="USUARIO_CLIENTE"){
        cargar_pagina_new=true;
        btnVolverContratos="U";
        blanquearFormularios("filtroUsuariosClientes");
        BackOfficeUsuariosContratosJSONData();
    }else if(opcion=="CONTRATOS"){
        cargar_pagina_new=true;
        btnVolverContratos="C";
        blanquearFormularios("filtroContratos");
        $("#div_tabla_consultaContratos").attr("style","dislplay:''");
        $("#div_tabla_EditarContratos").attr("style","display:none");
        $("#div_PARAMETROS_PERSONALES_CONTRATOS").attr("style","display:none");
       BackOfficeCargarFiltroContratosJSONData();
    }else if(opcion=="TRANSFERENCIA_INTERNA_TERCEROS"){
        cargarData_toOtherClient();
    }else if(opcion=="TRANSFERENCIA_INTERNA_MIS_CTAS"){
        cargarData_BMLA();
    }else if(opcion=="APROBAR_TRANSFERENCIA"){
        LoadTransfersToApproveJSONData();
    }else if(opcion=="LIBERAR_TRANSFERENCIA"){
        LoadTransfersToReleaseJSONData();
    }else if(opcion=="ESTADO_CUENTA"){
        AccountsStatementInfoPaginaJSONData();
    }else if(opcion=="SALDOS_CUENTAS"){
        BalancesAndTransactionInfoPaginaJSONData();
    }else if(opcion=="BLOQUEOS_CUENTAS"){
        BlockedAmountInfoPaginaJSONData();
    }else if(opcion=="CERTIFICADOS"){
        cargarCertificados();
    }else if(opcion=="VENCIMIENTOS"){
        cargar_pagina_new=true;
        cargarVencimientos();
    }else if(opcion=="SALDOS_COLOCACIONES"){
        BalancesAndTransactionColocacionesInfoPaginaJSONData();
    }else if(opcion=="BLOQUEOS_COLOCACIONES"){
        BlockedAmountsColocacionesInfoPaginaJSONData();
    }else if(opcion=="SALDOS_FONDOS"){
        BalancesAndTransactionMutualFundsInfoPaginaJSONData();
    }else if(opcion=="BLOQUEOS_FONDOS"){
        BloqueosMutualFundsInfoPaginaJSONData();
    }else if(opcion=="SALDOS_FONDOS_OI"){
        BalancesAndTransactionOtherInvestmentsInfoPaginaJSONData();
    }else if(opcion=="BLOQUEOS_FONDOS_OI"){
        BloqueosOtherInvestmentsInfoPaginaJSONData();
    }else if(opcion=="ESTADO_CUENTA_TARJETA"){
        AccountStatementTDCInfoPaginaJSONData();
    }else if(opcion=="MOVIMIENTOS_FACTURAR"){
        InTransitTransactionsTDCInfoPaginaJSONData();
    }else if(opcion=="DIRECTORIO"){
        infoPaginaTemplateConsultaJSONData();
    }else if(opcion=="GESTION_RECLAMO"){
        cargarGestionReclamos();
    }else if(opcion=="MIS_DATOS"){
        InfoPaginaMiInformacionJSONData();
    }else if(opcion=="PARAMETROS_PERSONALES"){
        infoPaginaParametrosPersonalesJSONData();
    }else if(opcion=="PARAMETROS_PERSONALES_FC"){
        infoPaginaParametrosPersonalesJSONData();
        //infoPaginaParametrosPersonalesCamposFCJSONData();
    }else if(opcion=="USUARIOS_FC"){
        $("#CU_reset_FC").click();
        FirmaConjuntaUsuariosJSONData();
    }else if(opcion=="CONSOLIDADO"){
        AllMyPortafolioCargarJSONData();
    }else if(opcion=="CONSULTA_TRANSFERENCIAS"){
        cargarPaginaTransferHistory();
    }else if(opcion=="CAMBIAR_CLAVE"){
        $("#btnCancelarCambioClave").click();
        cargarCambiarClave();
    }else if(opcion=="CARTA_INSTRUCCION"){
        loadValues_ClientInstructionJSONData();
    } else if(opcion=="PARAMETROS_GLOBALES_VBT"){
        infoPaginaParametrosGlobalesJSONData();
    }  else if(opcion=="PARAMETROS_GLOBALES_VBT"){
        infoPaginaParametrosGlobalesJSONData();
    } else if (opcion=="TARJETA_COORDENADAS"){
        cargarGeneracionTarjetaCoordenadasJSONData();
    } else if (opcion=="TARJETA_COORDENADAS_ACTIVA") {
        origenMetodosValidacion="";
        cargarActivacionTarjetaCoordenadasJSONData();
    }
}


function  cargarData_calendar(mes,ano){
    var url = urlcargarCalendario;
    var param={};
    param.mes_parametro=mes;
    param.ano_parametro=ano;
    sendServiceJSON(url,param,cargarData_calendarSuccess,null,null);
}

function cargarData_calendarSuccess(originalRequest){

    var events = [];
    $.each(originalRequest.listaFeriados,function(pos,item){
        var fecha2="new Date("+parseInt(originalRequest.ano_parametro)+","+parseInt(originalRequest.mes_parametro)+","+item.dia.split('-')[0]+")";

        events.push({ "EventID": pos, "Date":fecha2 , "Title":  item.descripcion, "URL": "#", "Description": "This is a sample event description", "CssClass": "Meeting" });

    });
    $.jMonthCalendar.DeleteEvents();
    $.jMonthCalendar.AddEvents(events);
}

function salir(){
    var param={};
    param.username="EXISTE";
    sendServiceJSON_sinc(urlSalir,param,SalirSuccess,null,null);

}

function SalirSuccess(originalRequest){
    redireccionar(urlSalir);
}

function accesoDenegado(){
    var param={};
    param.username="EXISTE";
    sendServiceJSON_sinc(urlSalir,param,accesoDenegadoSuccess,null,null);

}

function accesoDenegadoSuccess(originalRequest){
    location.href=urlSalir;
}

function CargarHomeJSONData(){
    var url = urlHome;
    var param={};
    sendServiceJSON_sinc(url,param,CargarHomeSuccess,null,null);
}


//function CargarHomeSuccess(originalRequest){
//    //                   this is the json return data
//    var result = originalRequest;
//    fechaLogueo= result.fechaLogueo;
//    horalogueo=result.horalogueo;
//    nombreUsuario=result.nombreUsuario;
//    fechaHoy=result.fechaHoy;
//    horaHoy=result.horaHoy;
//    idioma = result.idioma;
//    eventoSalirHome();
//    $("#inicio_bienvenida_fecha").html(fechaLogueo);
//    $("#inicio_bienvenida_hora").html(horalogueo);
//    $("#inicio_nombreUsuario").html(nombreUsuario);
//    $("#inicio_fecha").html(fechaHoy);
//    $("#inicio_hora").html(horaHoy);
//
//
//    menu=result.menu;
//
//
//    idleTimeOut();
//    if ($.browser.msie){
//        if ($.browser.version==9){
//            var latido=self.setInterval(function(){latidoWeb()},180000);
//        } else{
//            $(document).idleTimeout();
//        }
//    }else{
//        $(document).idleTimeout();
//    }
//
//
//
//    crearMenu();
//
//    var events=[];
//    $.each(originalRequest.listaFeriados,function(pos,item){
//        var fecha2=new Date(parseInt(item.dia.split("-")[2]),parseInt(item.dia.split("-")[1])-1,parseInt(item.dia.split("-")[0]));
//        var d = parseInt(fecha2.getDate());
//        var m =  parseInt(fecha2.getMonth());
//        var y = parseInt(fecha2.getYear())+ parseInt(1810);
//        events.push({ "EventID": pos, "StartDateTime":new Date(y,m,d) , "Title":  item.descripcion, "URL": "#", "Description": "This is a sample event description", "CssClass": "Meeting" });
//    });
//
//    var options = {
//        height: 400,
//        width: 1150,
//        navHeight: 25,
//        labelHeight: 25,
//        onMonthChanging: function(dateIn) {
//            calendario_ano= parseInt(dateIn.getYear())+parseInt(1900);
//            calendario_mes=dateIn.getMonth();
//            cargarData_calendar(calendario_mes,calendario_ano);
//
//            return true;
//        }
//    };
//    $.jMonthCalendar.Initialize(options, events);
//
//    cargarPermisologia(result.listaPrivilegios);
//
//    if(cambioClave=="S"){
//        /*if(idioma=="_ve_es"){
//         alert("Bienvenido. Por razones de seguridad, por favor ingrese una nueva clave");
//         }else{
//         alert("Welcome. Since it is your first time, you must indicate your new password");
//         } */
//        seleccionarOpcionMenu("CAMBIAR_CLAVE_PRINCIPAL");
//    }else{
//        seleccionarOpcion("home");
//    }
//
//
//    ParametrosPersonalesCamposTOBJSONData();
//    FC_ParametrosPersonalesCamposTOBJSONData();
//
//}


function establecerIdioma(idioma){
    $("#idioma_es").removeClass("current_seleccionIdioma");
    $("#idioma_en").removeClass("current_seleccionIdioma");

    if (idioma=="_ve_es"){
        $("#idioma_es").addClass("current_seleccionIdioma");
            $.each(label_es,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });

            $.each(title_es,function(pos,item){
                $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
            });
            label_idioma="es";

    }else{
        $("#idioma_en").addClass("current_seleccionIdioma");
        $.each(label_en,function(pos,item){
            if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
            }else{
                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
            }
        });
        $.each(title_en,function(pos,item){
            $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
        });
        label_idioma="en";

    }


}
function CargarHomeSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    fechaLogueo= result.fechaLogueo;
    horalogueo=result.horalogueo;
    nombreUsuario=result.nombreUsuario;
    fechaHoy=result.fechaHoy;
    horaHoy=result.horaHoy;
    idioma = result.idioma;
    eventoSalirHome();
    establecerIdioma(idioma);

    if ((result.usuarioAut!=null)&&(result.usuarioAut!="")){
        cambioClave=result.cambioPass;
        $("#tipo_usuario_app").val(result.tipo);
        if (result.grupo!="0000000016"){
            $("#manualFC").remove();
        }
        if(result.backoffice) {
            $("#pdfFATCA").addClass("oculto");
            $("#label_idiomas").addClass("oculto");
            $.each(label_es,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });

            $.each(title_es,function(pos,item){
                $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
            });
            label_idioma="es";
            cambiarIdiomaJSONData(label_idioma);
        }



        $("#inicio_bienvenida_fecha").html(fechaLogueo);
        $("#inicio_bienvenida_hora").html(horalogueo);
        $("#inicio_nombreUsuario").html(nombreUsuario);
        $("#inicio_fecha").html(fechaHoy);
        $("#inicio_hora").html(horaHoy);


        menu=result.menu;


        idleTimeOut();
        if ($.browser.msie){
            if ($.browser.version==9){
                var latido=self.setInterval(function(){latidoWeb()},180000);
            } else{
                $(document).idleTimeout();
            }
        }else{
            $(document).idleTimeout();
        }



        crearMenu();

        var events=[];
        $.each(originalRequest.listaFeriados,function(pos,item){
            var fecha2=new Date(parseInt(item.dia.split("-")[2]),parseInt(item.dia.split("-")[1])-1,parseInt(item.dia.split("-")[0]));
            var d = parseInt(fecha2.getDate());
            var m =  parseInt(fecha2.getMonth());
            var y = parseInt(fecha2.getYear())+ parseInt(1810);
            events.push({ "EventID": pos, "StartDateTime":new Date(y,m,d) , "Title":  item.descripcion, "URL": "#", "Description": "This is a sample event description", "CssClass": "Meeting" });
        });

        var options = {
            height: 400,
            width: 1150,
            navHeight: 25,
            labelHeight: 25,
            onMonthChanging: function(dateIn) {
                calendario_ano= parseInt(dateIn.getYear())+parseInt(1900);
                calendario_mes=dateIn.getMonth();
                cargarData_calendar(calendario_mes,calendario_ano);

                return true;
            }
        };
        $.jMonthCalendar.Initialize(options, events);

        cargarPermisologia(result.listaPrivilegios);

        if(cambioClave=="S"){
            /*if(idioma=="_ve_es"){
                alert("Bienvenido. Por razones de seguridad, por favor ingrese una nueva clave");
            }else{
                alert("Welcome. Since it is your first time, you must indicate your new password");
            } */
            seleccionarOpcionMenu("CAMBIAR_CLAVE_PRINCIPAL");
        }else{
            seleccionarOpcion("home");
        }


        ParametrosPersonalesCamposTOBJSONData();
        //FC_ParametrosPersonalesCamposTOBJSONData();
    }else{
        accesoDenegado();
    }
}

function createNotify( template, vars, opts ){
    return $("#container").notify("create", template, vars, opts);
}

function cargarIdiomaJSONData(){
    var url = urlcambioIdioma;
    var param={};

    sendServiceJSON(url,param,cargarIdiomaSuccess,null,null);
}


function cargarIdiomaSuccess(originalRequest){
    label_es= originalRequest.valor_es;
    label_en= originalRequest.valor_en;
    title_es= originalRequest.valor_title_es;
    title_en= originalRequest.valor_title_en;
    idioma= originalRequest.idioma;
    var usuarioAut=originalRequest.usuarioAut;
    if(idioma=="_us_en"){
        $.each(label_en,function(pos,item){
            if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
            }else{
                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
            }
        });
        $.each(title_en,function(pos,item){
            $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
        });
        label_idioma="en";
        cambiarIdiomaJSONData(label_idioma);
    } else

    if(idioma=="_ve_es"){
        $.each(label_es,function(pos,item){
            if(($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button")||
                ($("#"+item.substring(0, item.indexOf("="))).attr("type")=="reset")){
                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
            }else{
                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
            }
        });

        $.each(title_es,function(pos,item){
            $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
        });
        label_idioma="es";
        cambiarIdiomaJSONData(label_idioma);
    }

            $("#capa_carga").fadeOut("slow");
            $("#username").focus();


}

function cambiarIdiomaJSONData(label){
    var url = urlcambiarIdioma;
    var param={};
    var jsonLogin=[];

    jsonLogin[0] =  label;
//    jsonLogin[1] =  valores;

    param.jsonLogin=JSON.stringify({"parametros":jsonLogin});

    sendServiceJSON(url,param,cambiarIdiomaSuccess,null,null);
}


function cambiarIdiomaSuccess(originalRequest){
    var result= originalRequest;
    idioma = result.idioma;

//    alert(idioma);



}


function latidoWeb(){
    var url = urllatido;
    var param={};
    sendServiceJSON(url,param,latidoWebSuccess,null,null);
}

function latidoWebSuccess(originalRequest){
    var result=originalRequest;

    if(result.mensaje!="LATIDO"){
         redireccionar("Login.action");
    }

}


function  abrirFATCA(mes,ano){
    if(idioma=="_us_en")
        window.open('../vbtonline/resources/documentos/FATCA_10_2013_english.pdf','_blank');
    else
        window.open('../vbtonline/resources/documentos/FATCA_10_2013_spanish.pdf','_blank')
}



function  abrirGuiaFC(){
    if(idioma=="_us_en")
        window.open('../vbtonline/resources/documentos/Joint_Signatures_User_Administration.pdf','_blank');
    else
        window.open('../vbtonline/resources/documentos/Firmas_Conjuntas_Administracion_de_Usuarios.pdf','_blank')
}

function showValidationMethods(div_actual,div_nuevo){
   if (div_actual!=div_nuevo){
        $("#"+div_actual).children().appendTo("#"+div_nuevo);
        $("#"+div_actual).empty();
        validationMethod=div_nuevo;
   }
}


function abrirContrato(){
    var url = urlAceptarCondicionesTrasfe;
    var param={};
    var menu={};

    sendServiceJSON(url,param,abrirContratoSuccess,null,null);

}

function abrirContratoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    window.open('../vbtonline/resources/documentos/General_Terms_Conditions.pdf','_blank');


}

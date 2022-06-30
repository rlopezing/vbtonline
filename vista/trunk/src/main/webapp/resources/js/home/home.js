var menu=[];
var bk_opcion;
var urlSalir="Login_logout.action";
var urlsalirAVBT="login_abrir_vbtbank";
var urllatido="Login_latido";
var urlHome="Login_home.action";
var urlHomeUsuarioAut="Login_mostrarHome.action";
var urlMostrarPantalla="mostrarPantalla.action";
var urlcambioIdioma="Login_cargarProperties.action";
var urlcambiarIdioma="Login_cambiarIdioma.action";
var urlcargarCalendario="Login_cargarCalendario.action";
var urlTimeOut = "Login_minutesTimeOut.action";
var urlTransfersCargarBMLAHome="Transfers_cargarTranEntreMisCuentas.action";


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
var msg_pendientes = 0;
var fechaMesAnterior="";
var linkActualizacion="";
var diasRestantesPlazo = "";
var motivosAct = ""
var diasPlazo = "";

var label_es=[];

var label_en=[];

var title_es=[];

var title_en=[];

var vbtol_props = {};
var opcionActualizada = "";

var minutesTimeOut= 0;


/*$(window).blind("beforeClose",function(e) {
    sendServiceJSON(urlSalir,null,null,null,null);
});*/
window.onbeforeunload = function () {
    window.scrollTo(0, 0);
}
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
    $("#detalle_pagoTDC_fc").fadeOut("fast");

    $("#btn_cerrar_detalleTransferencia").click(function(){
        // $("#detalle_transferencia_fc").fadeOut("fast");
        $.modal.close();
    });

    $("#btn_imprimir_detalleTransferencia").click(function(){
        // printPageElement("detalle_transferencia_fc");
        printPageElement("detalle_transferencia_fc_content");
    });

    $("#btn_cerrar_detallePagoTdc").click(function(){
        // $("#detalle_pagoTDC_fc").fadeOut("fast");
        $.modal.close();
    });

    $("#btn_imprimir_detallePagoTdc").click(function(){
        // printPageElement("detalle_pagoTDC_fc");
        printPageElement("detalle_pagoTDC_fc_content");
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
        $.modal.close();
        seleccionarOpcionMenu("home");
    });


    $("#cerrar_div_mensaje_error").click(function(){
        $("#div_mensajes_error").fadeOut("fast");
    });

    $("#cerrar_div_mensajes_info_desc_tabla").click(function(){
        $("#div_mensajes_info_desc_tabla").fadeOut("fast");
    });


    $(".dataTables_wrapper .paginate_button").click(function(){
        console.log('click paginacion tabla');
        $(window).scrollTop();
    });

    var paginadoresRef = $('.dataTables_wrapper .paginate_button');
    console.log('paginadoresRef ',paginadoresRef);

    $(document).on("click",".dataTables_wrapper .paginate_button",function(){
        console.log('click paginacion tabla');
        $(window).scrollTop();
    });
    



    $("#idioma_es").click(function(){
        console.log('cambio es');
        if(label_idioma!="es"){
            $.each(label_es,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="submit"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });

            $.each(label_es,function(pos,item){
                //console.log($(item.substring(0, item.indexOf("="))));
                var key = item.substring(0, item.indexOf("="));
                //console.log($('*[data-tradution="'+key+'"]'));
    
                const keyList = document.querySelectorAll('[data-tradution="'+key+'"]');
                document.querySelectorAll('[data-tradution="'+key+'"]').innerText =  item.substring(item.indexOf("=")+1, item.length);
    
                if(keyList.length > 0){
                    console.log('####keyList ');
                    console.log(keyList);
                    for (let index = 0; index < keyList.length; index++) {
                        const element = keyList[index];
                        element.onload
                        console.log('####element ');
                        console.log(element);
        
                        let text = item.substring(item.indexOf("=")+1, item.length);
                        element.innerText  = text;
                        element.innerHTML  = text;
                        element.nodeValue  = text;
                         element.textContent  = text;
                         element.value   = text;
    
                        
                        console.log('####last element ');
                        console.log(element);
                    }
                }
    
    
            });

            $.each(title_es,function(pos,item){
                $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
            });
            label_idioma="es";
            cambiarIdiomaJSONData(label_idioma);
        }

    });

    $("#idioma_es_home").click(function(){
        console.log('espanol 1');
        if(label_idioma!="es"){
            $.each(label_es,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="submit"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });

            $.each(label_es,function(pos,item){
                //console.log($(item.substring(0, item.indexOf("="))));
                var key = item.substring(0, item.indexOf("="));
                //console.log($('*[data-tradution="'+key+'"]'));
    
                const keyList = document.querySelectorAll('[data-tradution="'+key+'"]');
                document.querySelectorAll('[data-tradution="'+key+'"]').innerText =  item.substring(item.indexOf("=")+1, item.length);
    
                if(keyList.length > 0){
                    console.log('item');
                    console.log(item);
                    console.log('####keyList ');
                    console.log(keyList);
                    for (let index = 0; index < keyList.length; index++) {
                        const element = keyList[index];
                        element.onload
                        console.log('####element ');
                        console.log(element);
        
                        let text = item.substring(item.indexOf("=")+1, item.length);
                        element.innerText  = text;
                        element.innerHTML  = text;
                        element.nodeValue  = text;
                         element.textContent  = text;
                         element.value   = text;
    
                        
                        console.log('####last element ');
                        console.log(element);
                    }
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

    $("#idioma_en_home").click(function(){
        if(label_idioma!="en"){
            $.each(label_en,function(pos,item){
                if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="submit"){
                    $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
                }else{
                    $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
                }
            });

            $.each(label_en,function(pos,item){
                //console.log($(item.substring(0, item.indexOf("="))));
                var key = item.substring(0, item.indexOf("="));
                //console.log($('*[data-tradution="'+key+'"]'));
    
                const keyList = document.querySelectorAll('[data-tradution="'+key+'"]');
                document.querySelectorAll('[data-tradution="'+key+'"]').innerText =  item.substring(item.indexOf("=")+1, item.length);
    
                if(keyList.length > 0){
                    console.log('item');
                    console.log(item);
                    console.log('####keyList ');
                    console.log(keyList);
                    for (let index = 0; index < keyList.length; index++) {
                        const element = keyList[index];
                        element.onload
                        console.log('####element ');
                        console.log(element);
        
                        let text = item.substring(item.indexOf("=")+1, item.length);
                        element.innerText  = text;
                        element.innerHTML  = text;
                        element.nodeValue  = text;
                         element.textContent  = text;
                         element.value   = text;
    
                        
                        console.log('####last element ');
                        console.log(element);
                    }
                }
    
    
            });

            $.each(title_en,function(pos,item){
                $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
            });
            label_idioma="en";
            cambiarIdiomaJSONData(label_idioma);
        }
    });

    /**
     *      TAG:            NOTI_SOLPEN_FC
     *      AUTOR:          RRANGEL
     *      FECHA:          09/10/2014
     *      DESCRIPCIÓN:    Proceso de mostrar notificación de solicitudes pendientes para firmas conjuntas a
     *                      Aprobadores (9) y Liberadores (8).
     * */
    $("#popupTagCerrarFC").click(function(){
        $("#div_mensajes_error").fadeOut("fast");
        msg_pendientes = 0;
    });

    $("#popupTagNotiFC").live("click",function(){
        $("#div_mensajes_error").fadeOut("fast");
        $("#div_carga_en_espera").removeClass("oculto");

        seleccionarOpcion("APROBAR_TRANSFERENCIA");

        msg_pendientes = 0;
        $("#div_carga_en_espera").addClass("oculto");
    });
    /** FIN NOTI_SOLPEN_FC */

    $("#popupTagNotiFCLiberar").live("click",function(){
        $("#div_mensajes_error").fadeOut("fast");
        $("#div_carga_en_espera").removeClass("oculto");

        seleccionarOpcion("LIBERAR_TRANSFERENCIA");

        msg_pendientes = 0;
        $("#div_carga_en_espera").addClass("oculto");

        /** FIN NOTI_SOLPEN_FC */
    });


    $("#popupTagNotiPaymentTdcFC").live("click",function(){
        $("#div_mensajes_error").fadeOut("fast");
        $("#div_carga_en_espera").removeClass("oculto");

        seleccionarOpcion("APROBAR_PAGO_TARJETAS");

        msg_pendientes = 0;
        $("#div_carga_en_espera").addClass("oculto");
    });

    $("#popupTagNotiPaymentTdcFCLiberar").live("click",function(){
        $("#div_mensajes_error").fadeOut("fast");
        $("#div_carga_en_espera").removeClass("oculto");

        seleccionarOpcion("LIBERAR_PAGO_TARJETAS");

        msg_pendientes = 0;
        $("#div_carga_en_espera").addClass("oculto");

    });


});



function crearMenu(){
    var opciones=menu;
    var ul_principal= $("#main-menu");
    var menu_general=$("#general-menu");

    $.each(opciones,function(o,item){
        var opcion=menu[o];
        console.log(opcion.html_spanID)
        if(opcion.html_spanID=="TAGTitleMisDatos"){
            $("#TAGTitleMisDatos").click(function(){
                seleccionarOpcion("MIS_DATOS");
                $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
            });
        } else if(opcion.html_spanID=="menu_TAGTitleSalir"){
            $("#menu_TAGTitleSalir").click(function(){
                // seleccionarOpcion("SALIR");
                $("#menu").removeClass("header__content--active");
                $("#logout-modal").modal({
                    showClose: !1,
                    modalClass: "notification-modal",
                    fadeDuration: 100,
                    blockerClass: "notification-modal--blocker",
                });
            });
        } else if(opcion.html_spanID=="TAGTitleCalendario"){

        } else if(opcion.html_spanID=="TAGTitleTodoPortafolio"){

            var li_opcion=$(document.createElement('LI')).prependTo(menu_general);
            li_opcion.addClass("general-menu__item")
            li_opcion.attr("id",opcion.html_spanID);
            var a=$(document.createElement('A')).appendTo(li_opcion);
            a.html(opcion.html_span);
            a.attr("id","CONSOLIDADO");
            a.addClass("general-menu__link");
            $('.BalaceTableHomeContent').show();

            $("#TAGTitleTodoPortafolio").click(function(){
                seleccionarOpcion("CONSOLIDADO");
                $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
            });

        } else{
            var li_opcion=$(document.createElement('LI')).appendTo(ul_principal);
            li_opcion.addClass("main-menu__item")
            var a=$(document.createElement('A')).appendTo(li_opcion);
            // var span= $(document.createElement('span')).appendTo(a);
            a.html(opcion.html_span);
            a.attr("id",opcion.html_spanID);
            a.addClass("main-menu__link")

            if(opcion.estilo!==""){
                // a.attr('class',opcion.estilo);
                var icon_down=$(document.createElement('IMG')).appendTo(li_opcion);
                icon_down.attr("src","../vbtonline/resources/img/icons/ic_templates_help_carrusel_chevron_down.png");
                icon_down.addClass("main-menu__down");

                var div_submenu = $(document.createElement('DIV'));
                div_submenu.appendTo(li_opcion);
                div_submenu.addClass("main-menu__submenu");
                div_submenu.addClass("submenu");
                div_submenu.addClass("submenu--left");

/*                var title_submenu = $(document.createElement('SPAN'));
                title_submenu.addClass("submenu__title");
                title_submenu.html(opcion.html_span);
                title_submenu.appendTo(div_submenu);*/

                var ul_segundario= $(document.createElement('UL'));
                ul_segundario.addClass("submenu__list");
                ul_segundario.appendTo(div_submenu);

                var sub_opciones=opcion.sub_opciones;
                $.each(sub_opciones,function(so,item){
                    var sub_opcion=sub_opciones[so];
                    var li_sub_opcion=$(document.createElement('LI')).appendTo(ul_segundario);
                    li_sub_opcion.addClass("submenu__item");
                    li_sub_opcion.attr('id',sub_opcion.id);
                    // $("#div_"+sub_opcion.id).removeClass("div_opcion_menu");
                    // li_sub_opcion.attr('class',sub_opcion.codope+"_0");
                    var a_sub_opcion=$(document.createElement('A')).appendTo(li_sub_opcion);
                    // var span_sub_opcion= $(document.createElement('span')).appendTo(a_sub_opcion);
                    // span_sub_opcion.html(sub_opcion.span_html);
                    // span_sub_opcion.attr("id",sub_opcion.span_htmlID);
                    a_sub_opcion.html(sub_opcion.span_html);
                    a_sub_opcion.attr("id",sub_opcion.span_htmlID);
                    a_sub_opcion.addClass("submenu__link")
                    li_sub_opcion.click(function(){
                        $("#menu").removeClass("header__content--active");
                        if(sub_opcion.span_htmlID==="TAGTitleInstrucciones"){
                            //$('html, body').animate({scrollTop: $('#transfer-instructions').offset().top -150 }, 'slow');
                            $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
                        } else
                            $('html, body').animate({scrollTop: $('body').offset().top -0 }, 'slow');
                        seleccionarOpcion(sub_opcion.id);
                    });
                });
            }else{
                li_opcion.attr('id',opcion.id);
                // $("#div_"+opcion.id).removeClass("div_opcion_menu");
                // li_opcion.attr('class',opcion.codope+"_0");
                li_opcion.click(function(){
                    $("#menu").removeClass("header__content--active");
                    seleccionarOpcion(opcion.id);
                });
            }
        }

    });

    //$("#div_CAMBIAR_CLAVE").removeClass("div_opcion_menu");
    // $(".div_opcion_menu").remove();
    // $("#salir").css("color","red")

    const menuItems = document.querySelectorAll(".main-menu__item");
    const menuItemsGeneral = document.querySelectorAll(".general-menu__item");

    menuItems.forEach((item, i) => {
        menuItems[i].addEventListener("click", (e) => {
            if (menuItems[i].lastElementChild) {
                const subMenu = menuItems[i].lastElementChild;
                subMenu.classList.toggle("submenu--active");
            }
        });
    });

    menuItemsGeneral.forEach((title, i) => {
        menuItemsGeneral[i].addEventListener("click", (e) => {
            if (menuItemsGeneral[i].lastElementChild) {
                const subMenu = menuItemsGeneral[i].lastElementChild;
                subMenu.classList.toggle("submenu--active");
            }
        });
    });

    $("#TAGTitleCalendario").click(function(){
        $("#home").click();
        setTimeout(()=>{
            $('html, body').animate({scrollTop: $('#holidays-section').offset().top -150 }, 'slow');
        }, 1500);
    });

/*    $("#TAGTitleInstrucciones").click(function(){
        $('html, body').animate({scrollTop: $('#transfer-instructions').offset().top -150 }, 'slow');
/!*        $("#home").click();
        setTimeout(()=>{
            $('html, body').animate({scrollTop: $('#transfer-instructions').offset().top -150 }, 'slow');
        }, 1500);*!/
    });*/

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
    if (msg_pendientes == 0) {
        $("#div_mensajes_error").fadeOut("fast");
    }
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
    console.log('cargarDataOpcion',opcion);

    $("#div_carga_espera").addClass("oculto");
    if(cambioClave=="S"){
        opcion="CAMBIAR_CLAVE_PRINCIPAL";
    }
    if(opcion=="CALENDARIO_BANCARIO"){
        cargarData_calendar(new Date().getMonth(),parseInt(new Date().getYear())+parseInt(1900));
    }if(opcion=="TRANSFERENCIA_EXTERNA"){
        validarTransfer="";
        tempateCargado = false;
        transferenciaMismoDia();
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
        $("#BetweenLinkedAccounts_Motivo").val('');
        $("#BetweenLinkedAccounts_Motivo").text('');
        $("#transferirUnidades").prop("checked", false);
        $("#cancelacionProductorMostrar").hide();
        $("#motivoCancelacionProductoBMLA").hide();
        $("#descripcionTransferenciaBMLA").show();
        $("#BMLA_Unidades").val('');
        $("#BMLA_Unidades").text('');
        $("#BetweenLinkedAccounts_NumCuentaDestino").val('');
        cargarData_BMLA();
    }else if(opcion=="APROBAR_TRANSFERENCIA"){
        LoadTransfersToApproveJSONData();
    }else if(opcion=="LIBERAR_TRANSFERENCIA"){
        console.log('LoadTransfersToReleaseJSONData');
        LoadTransfersToReleaseJSONData();
    }else if(opcion=="LIBERAR_TRANSFERENCIA"){
        LoadTransfersToReleaseJSONData();
    }
    else if(opcion=="ESTADO_CUENTA"){
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
    }else if(opcion=="BLOQUEO_TARJETA"){
        CardLockTDCInfoPaginaJSONData();
    }else if(opcion=="BLOQUEO_EMERGENCIA_TDC"){
        CardLockETDCInfoPaginaJSONData();
    }else if(opcion=="USUARIO_CLIENTE_FC"){
        consultarUsuariosContratosFCJSONData();
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
        $("#TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL2").addClass("oculto");
        $("#TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL").removeClass("oculto");
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
    }else if (opcion=="LOG_SMS") {
        blanquearFormularios("filtro_log_sms");
        $("#fechaDesdeFiltroSMS" ).val(fechaHoy);
        $("#fechaHastaFiltroSMS" ).val(fechaHoy);
        BackOfficeConsultarLogSMSJSONData();
    }else if (opcion=="PAISES_NO_PERMITIDOS") {
        blanquearFormularios("filtro_log_sms");
        $("#div_carga_espera").removeClass("oculto");
        BackOfficeConsultarPaisesNoPermitidosJSONData();
    }else if (opcion=="ANULAR_CARTA_INSTRUCCION") {
        // $("#div_carga_espera").removeClass("oculto");
        loadValues_ClientInstructionVoidJSONData();
    } else if (opcion=="ROLES_FC") {
        // $("#div_carga_espera").removeClass("oculto");
//        BackOfficeRolesJSONData();
        BackOfficeRolesJSONData();
    } else if (opcion=="INACTIVAR_BANCOS") {
        BackOfficeBancosJSONData();
    }else if (opcion=="AVISOS") {
        $("#limpiarFiltroAvisos").click();
        $("#div_consultarAvisos").fadeIn("slow");
        BackOfficeAvisosJSONData("-2");
    }else if (opcion=="BLOQUEO_TARJETAS_BO") {
        $("#volver_editar_regla_bo").click();
        $("#div_creditCard_CL_Edit_BO").fadeOut("fast");
        $("#limpiarBloqueoTarjetaBO").click();
        // proximoDiaHabilBOJSONData();

    }else if(opcion=="PAGO_TARJETA"){

        cargarPagoTarjetasPaginaJSONData();

    }
    else if(opcion=="APROBAR_PAGO_TARJETAS"){
        LoadPaymentsTdcToApproveJSONData();
    }
    else if(opcion=="LIBERAR_PAGO_TARJETAS"){
        LoadPaymentsTdcToReleaseJSONData();
    }
    else if(opcion=="CONSULTA_PAGOS_TDC"){
        cargarPagoTarjetasPaginaJSONData2();
    }
    else if (opcion=="ESTADO_CUENTA_FONDOS"){
        limpiarPantallaEDOMutualFund();
        AccountStatementMutualFundsInfoPaginaJSONData();
    }else if (opcion=="ESTADO_CUENTA_FONDOS_OI"){
        limpiarPantallaEDOOI();
        AccountStatementsOtherInvestmentsInfoPaginaJSONData();
    }else if (opcion=="SESIONES_ACTIVAS"){
        BackOfficeSesionesJSONData();
    }else if (opcion=="PAGOS_TDC") {
        //$("#volver_editar_regla_bo").click();
        $("#div_creditCard_Pagos_Edit_BO").fadeOut("fast");
        $("#limpiarPagosTarjetaBO").click();
        consultaTDC="OK";
    }else if (opcion=="CUENTAS_NO_PERMITIDAS"){
        BackOfficeCuentasNoPermitidasJSONData();
    }else if (opcion=="TRANSACCIONES_ESPECIALES"){
        BackOfficeTransaccionesEspecialesJSONData();
    }else if(opcion=="TDC_TIME_DEPOSITS"){
        infoPaginaTDJSONData();
    }else if(opcion=="MAIL_BOX"){
        /*infoPaginaMailBoxJSONData();*/
    }else if (opcion=="BLOQUEO_EMERGENCIA_BO") {
        $("#div_carga_espera").removeClass("oculto");
        $("#limpiarBloqueoEmergenciaBO").click();
        BackOficceBloqueoEmergenciaJSONData();
    }else if(opcion=="MAIL_BOX"){
        //infoPaginaMailBoxJSONData();
    }

    $('#estado_cuenta_tabla_consulta_BT_wrapper .table__content').empty();
    $('#estado_cuenta_tabla_consulta_BT_wrapper .dataTables_paginate').empty();

    $('#tabla_consulta_SalTrans_FM_wrapper .table__content').empty();
    $('#tabla_consulta_SalTrans_FM_wrapper .dataTables_paginate').empty();

    $('#tabla_consulta_SalTrans_OI_wrapper .table__content').empty();
    $('#tabla_consulta_SalTrans_OI_wrapper .dataTables_paginate').empty();

    $('#tabla_consulta_TDC_ITT_wrapper .table__content').empty();
    $('#tabla_consulta_TDC_ITT_wrapper .dataTables_paginate').empty();

    $("#numero_cuenta_TDC_Pago").val("-2");
    $("#numero_cuenta_TDC_Pago").trigger("chosen:updated");
    $("#tag_cliente_bloqueo_pago").text("");
    $("#pay_card").addClass("oculto");
    
     
    $(window).scrollTop();
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
    var calendarEvents=$("#calendar-events");
    calendarEvents.empty();
    $.each(originalRequest.listaFeriados,function(pos,item){
        var fecha2="new Date("+parseInt(originalRequest.ano_parametro)+","+parseInt(originalRequest.mes_parametro)+","+item.dia.split('-')[0]+")";

        events.push({ "EventID": pos, "Date":fecha2 , "Title":  item.descripcion, "URL": "#", "Description": "This is a sample event description", "CssClass": "Meeting" });

        var holidayItem=$(document.createElement('DIV'));
        holidayItem.addClass("calendar__event");

        var holidayItemTitle=$(document.createElement('SPAN')).appendTo(holidayItem);
        holidayItemTitle.addClass("calendar__fulldate")

        var fulldate=item.dia.split('-');
        holidayItemTitle.html(fulldate[0]+"-"+(parseInt(fulldate[1])+1)+"-"+fulldate[2])

        var holidayItemDescription=$(document.createElement('SPAN')).appendTo(holidayItem);
        holidayItemDescription.addClass("calendar__description")
        holidayItemDescription.html(item.descripcion)

        holidayItem.appendTo(calendarEvents);

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

function CargarHomeUsuarioJSONData(){
    var url = urlHomeUsuarioAut;
    var param={};
    sendServiceJSON_sinc(url,param,CargarHomeUsuarioSuccess,null,null);
}

function CargarHomeUsuarioSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;

    usuarioAutenticado=result.usuarioAut;

    if ((usuarioAutenticado!=null)&&(usuarioAutenticado!="")){
        $("#encabezado").addClass("oculto");
        $("#div_logo").addClass("oculto");
        $("#espacio_divs").addClass("oculto");
        $(location).attr('href','Home');
    }
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

function cargarIdiomaJSONData(){
    var url = urlcambioIdioma;
    var param={};

    sendServiceJSON_sinc(url,param,cargarIdiomaSuccess,null,null);
}

function cargarIdiomaSuccess(originalRequest){
    label_es= originalRequest.valor_es;
    label_en= originalRequest.valor_en;
    title_es= originalRequest.valor_title_es;
    title_en= originalRequest.valor_title_en;
    idioma= originalRequest.idioma;
    var usuarioAut=originalRequest.usuarioAut;

    var props_en = {};
    var props_es = {};

    if(idioma=="_us_en"){
        console.log('cargarIdiomaSuccess ');
        $.each(label_en,function(pos,item){
            //console.log($("#"+item.substring(0, item.indexOf("="))));
            if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
            }else if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="submit"){
                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
            }else{
                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
            }
        });

        $.each(label_en,function(pos,item){
            //console.log($(item.substring(0, item.indexOf("="))));
            var key = item.substring(0, item.indexOf("="));
            //console.log($('*[data-tradution="'+key+'"]'));

            const keyList = document.querySelectorAll('[data-tradution="'+key+'"]');
            document.querySelectorAll('[data-tradution="'+key+'"]').innerText =  item.substring(item.indexOf("=")+1, item.length);

            if(keyList.length > 0){
                console.log('####keyList ');
                console.log(keyList);
                for (let index = 0; index < keyList.length; index++) {
                    const element = keyList[index];
                    element.onload
                    console.log('####element ');
                    console.log(element);
    
                    let text = item.substring(item.indexOf("=")+1, item.length);
                    element.innerText  = text;
                    element.innerHTML  = text;
                    element.nodeValue  = text;
                     element.textContent  = text;
                     element.value   = text;

                    
                    console.log('####last element ');
                    console.log(element);
                }
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
            }else if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="submit"){
                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
            }else{
                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
            }
        });

        $.each(label_es,function(pos,item){
            //console.log($(item.substring(0, item.indexOf("="))));
            var key = item.substring(0, item.indexOf("="));
            //console.log($('*[data-tradution="'+key+'"]'));

            const keyList = document.querySelectorAll('[data-tradution="'+key+'"]');
            document.querySelectorAll('[data-tradution="'+key+'"]').innerText =  item.substring(item.indexOf("=")+1, item.length);

            if(keyList.length > 0){
                console.log('####keyList ');
                console.log(keyList);
                for (let index = 0; index < keyList.length; index++) {
                    const element = keyList[index];
                    element.onload
                    console.log('####element ');
                    console.log(element);
    
                    let text = item.substring(item.indexOf("=")+1, item.length);
                    element.innerText  = text;
                    element.innerHTML  = text;
                    element.nodeValue  = text;
                     element.textContent  = text;
                     element.value   = text;

                    
                    console.log('####last element ');
                    console.log(element);
                }
            }


        });

        $.each(title_es,function(pos,item){
            $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
        });
        label_idioma="es";
        cambiarIdiomaJSONData(label_idioma);
    }

    /**
     * Almacenar en vbtol_props
     * */
    var i = 0;
    for (i = 0; i < label_en.length; i++) {
        var key = "";
        var value = "";
        key = label_en[i].substring(0, label_en[i].indexOf("="));
        value = label_en[i].substring(label_en[i].indexOf("=")+1, label_en[i].length);
        props_en[key] = value;
    }
    for (i = 0; i < title_en.length; i++) {
        var key = "";
        var value = "";
        key = title_en[i].substring(0, title_en[i].indexOf("="));
        value = title_en[i].substring(title_en[i].indexOf("=")+1, title_en[i].length);
        props_en[key] = value;
    }
    for (i = 0; i < label_es.length; i++) {
        var key = "";
        var value = "";
        key = label_es[i].substring(0, label_es[i].indexOf("="));
        value = label_es[i].substring(label_es[i].indexOf("=")+1, label_es[i].length);
        props_es[key] = value;
    }
    for (i = 0; i < title_es.length; i++) {
        var key = "";
        var value = "";
        key = title_es[i].substring(0, title_es[i].indexOf("="));
        value = title_es[i].substring(title_es[i].indexOf("=")+1, title_es[i].length);
        props_es[key] = value;
    }

    vbtol_props["_us_en"] = {};
    vbtol_props["_us_en"] = props_en;
    vbtol_props["_ve_es"] = {};
    vbtol_props["_ve_es"] = props_es;

    /** FIN vbtol_props */

    $("#capa_carga").fadeOut("slow");
    $("#username").focus();
}

/**
 * SE DEBE UTILIZAR CUANDO SE VAYA A INVOCAR LA VARIABLE VBTOL_PROPS, DE LO CONTRARIO NO FUNCIONARÁ EN MS IE.
 * EJEMPLO:
 *
 * ...
 * cargarIdiomaJSONData_sinc();
 * <span id="clave"> vbtol_props[idioma]["clave"] </span>
 * ...
 *
 * */

function cargarIdiomaJSONData_sinc(){
    var url = urlcambioIdioma;
    var param={};
    sendServiceJSON_sinc(url,param,cargarIdiomaSuccess_sinc,null,null);
}

function cargarIdiomaSuccess_sinc(originalRequest){
    label_es= originalRequest.valor_es;
    label_en= originalRequest.valor_en;
    title_es= originalRequest.valor_title_es;
    title_en= originalRequest.valor_title_en;
    idioma= originalRequest.idioma;
    var usuarioAut=originalRequest.usuarioAut;

    var props_en = {};
    var props_es = {};

    /**
     * Almacenar en vbtol_props
     * */
    var i = 0;
    for (i = 0; i < label_en.length; i++) {
        var key = "";
        var value = "";
        key = label_en[i].substring(0, label_en[i].indexOf("="));
        value = label_en[i].substring(label_en[i].indexOf("=")+1, label_en[i].length);
        props_en[key] = value;
    }
    for (i = 0; i < title_en.length; i++) {
        var key = "";
        var value = "";
        key = title_en[i].substring(0, title_en[i].indexOf("="));
        value = title_en[i].substring(title_en[i].indexOf("=")+1, title_en[i].length);
        props_en[key] = value;
    }
    for (i = 0; i < label_es.length; i++) {
        var key = "";
        var value = "";
        key = label_es[i].substring(0, label_es[i].indexOf("="));
        value = label_es[i].substring(label_es[i].indexOf("=")+1, label_es[i].length);
        props_es[key] = value;
    }
    for (i = 0; i < title_es.length; i++) {
        var key = "";
        var value = "";
        key = title_es[i].substring(0, title_es[i].indexOf("="));
        value = title_es[i].substring(title_es[i].indexOf("=")+1, title_es[i].length);
        props_es[key] = value;
    }

    vbtol_props["_us_en"] = {};
    vbtol_props["_us_en"] = props_en;
    vbtol_props["_ve_es"] = {};
    vbtol_props["_ve_es"] = props_es;

    /** FIN vbtol_props */

    $("#capa_carga").fadeOut("slow");
    $("#username").focus();

}

function establecerIdioma(idioma){
    console.log('#establecerIdioma');
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
    fechaMesAnterior=result.fechaMesAnterior;
    idioma = result.idioma;
    linkActualizacion = result.linkActualizacion;
    mostrarActualizacion = result.mostrarActualizacion;
    diasRestantesPlazo = result.diasRestantesPlazo;
    diasPlazo = result.diasPlazo;
    motivosAct = result.motivosAct;
    $("#nameMyInformation_profile").html(nombreUsuario);
    eventoSalirHome();
    establecerIdioma(idioma);
    cargarData_calendar(new Date().getMonth(),parseInt(new Date().getYear())+parseInt(1900));
    console.log('antes de cargar');
    setTimeout(() => {
        infoPaginaBMLAJSONDataHome();
    }, 1000);

//    nobackbutton();
    if ((result.usuarioAut!=null)&&(result.usuarioAut!="")){
        cambioClave=result.cambioPass;
        $("#tipo_usuario_app").val(result.tipo);
        $("#tipo_contrato_app").val(result.tipoContrato);

        if(idioma=="_ve_es"){
            $("#aviso_principal").html(result.infoHome[0]);
        }else{
            $("#aviso_principal").html(result.infoHome[1]);
        }

        label_es.push("aviso_principal="+result.infoHome[0]);
        label_en.push("aviso_principal="+result.infoHome[1]);

        if (result.grupo!="0000000016"){
            $("#manualFC").remove();
            $("#manualFC").addClass("oculto");
        }else{
            $("#manualFC").removeClass("oculto");
        }
        if(result.backoffice) {
            console.log('espanol 3');
            //$("#pdfFATCA").addClass("oculto");
            $("#client-data").addClass("display-none");
            $("#holidays-section-container").addClass("holidays-section__container--backoffice");
            $("#label_idiomas").addClass("oculto");
            $(".header__languages").addClass("oculto");
            $(".footer__item_links").addClass("oculto");

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

        console.log(fechaLogueo);
        console.log(horalogueo);

        $("#inicio_bienvenida_fecha").html(fechaLogueo);
        $("#inicio_bienvenida_hora").html(horalogueo);
        /*  $("#inicio_nombreUsuario").html(nombreUsuario);
          $("#inicio_fecha").html(fechaHoy);
          $("#inicio_hora").html(horaHoy);      */


        menu=result.menu;


        //idleTimeOut();
        TimeOut();
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
            height: 200,
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
            $("#TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL").addClass("oculto");
            $("#TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL2").removeClass("oculto");
        }else{
            $("#TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL2").addClass("oculto");
            $("#TAG_INFO_CAMBIAR_CLAVE_PRINCIPAL").removeClass("oculto");
            seleccionarOpcion("home");
        }

        if(!result.backoffice) {
            ParametrosPersonalesCamposTOBJSONData();
        }
        //FC_ParametrosPersonalesCamposTOBJSONData();
    }else{
        accesoDenegado();
    }


    var mensaje = "";
    /**
     *      AUTOR:          RRANGEL
     *      FECHA:          09/10/2014
     *      DESCRIPCIÓN:    Proceso de mostrar notificación de solicitudes pendientes para firmas conjuntas a
     *                      Aprobadores (9) y Liberadores (8).
     *      TAG:            NOTI_SOLPEN_ACTION_FC
     * */
    if (
        (
            ($("#tipo_usuario_app").val() == "8") ||
            ($("#tipo_usuario_app").val() == "7") ||
            ($("#tipo_usuario_app").val() == "11")
        ) &&
        (result.cantidadSolicitudesPendientes > 0)||
        (result.cantidadSolicitudesPendientesLiberar > 0)
    ) {
        //Usted tiene (#) transferencias pendientes, haga click aqui para su aprobación.
        msg_pendientes = 1;

//        if(idioma=="_us_en") {
//            mensaje = "<span id='popupTagNotiFC1'>You have (</span>" + Trim(result.cantidadSolicitudesPendientes);
//            if ($("#tipo_usuario_app").val() == "8") {
//                mensaje += "<span id='popupTagNotiFC2L'>) pending transfers, click <b id='popupTagNotiFC'>here</b> for release.</span>";
//            }
//            if ($("#tipo_usuario_app").val() == "9") {
//                mensaje += "<span id='popupTagNotiFC2A'>) pending transfers, click <b id='popupTagNotiFC'>here</b> for approval.</span>";
//            }
//        } else {
//            mensaje = "<span id='popupTagNotiFC1'>Usted tiene (</span>" + Trim(result.cantidadSolicitudesPendientes);
//            if ($("#tipo_usuario_app").val() == "8") {
//                mensaje += "<span id='popupTagNotiFC2L'>) transferencias pendientes, haga click <b id='popupTagNotiFC'>aquí</b> para su liberación.</span>";
//            }
//            if ($("#tipo_usuario_app").val() == "9") {
//                mensaje += "<span id='popupTagNotiFC2A'>) transferencias pendientes, haga click <b id='popupTagNotiFC'>aquí</b> para su aprobación.</span>";
//            }
//        }

        if(($("#tipo_usuario_app").val() == "8")||($("#tipo_usuario_app").val() == "7")){
//            cargarIdiomaJSONData_sinc();
            mensaje = "<span id='popupTagNotiFC1'>" + vbtol_props[idioma]["popupTagNotiFC1"] + "</span>" + Trim(result.cantidadSolicitudesPendientes);
            if ($("#tipo_usuario_app").val() == "8") {
                mensaje += "<span id='popupTagNotiFC2L'>" + vbtol_props[idioma]["popupTagNotiFC2L"] + "</span>";
            }
            if ($("#tipo_usuario_app").val() == "7") {
                mensaje += "<span id='popupTagNotiFC2A'>" + vbtol_props[idioma]["popupTagNotiFC2A"] + "</span>";
            }
        }else{
//            cargarIdiomaJSONData_sinc();
            if ((result.rolesCustom=="A")||(result.rolesCustom=="AL")){
                if (Trim(result.cantidadSolicitudesPendientes)>0){
                    mensaje += "<span id='popupTagNotiFC1'>" + vbtol_props[idioma]["popupTagNotiFC1"] + "</span>" + Trim(result.cantidadSolicitudesPendientes);
                    mensaje += "<span id='popupTagNotiFC2A'>" + vbtol_props[idioma]["popupTagNotiFC2A"] + "</span>";
                }
            }
            if ((result.rolesCustom=="L")||(result.rolesCustom=="AL")){
                if (Trim(result.cantidadSolicitudesPendientesLiberar)>0){
                    if(mensaje!="")
                        mensaje +="<br>";
                    mensaje += "<span id='popupTagNotiFC2'>" + vbtol_props[idioma]["popupTagNotiFC2"] + "</span>" + Trim(result.cantidadSolicitudesPendientesLiberar);
                    mensaje += "<span id='popupTagNotiFC2L'>" + vbtol_props[idioma]["popupTagNotiFC2L"] + "</span>";
                }
            }
        }
    }
    /** FIN NOTI_SOLPEN_ACTION_FC */


    /**
     * MOSTRAR MENSAJE DE NOTIFICACION DE PAGOS PENDIENTES POR APROBAR O LIBERAR
     */
    if (
        (
            ($("#tipo_usuario_app").val() == "8") ||
            ($("#tipo_usuario_app").val() == "7") ||
            ($("#tipo_usuario_app").val() == "11")
        ) &&
        (result.cantidadPagosTdcPendientes > 0)||
        (result.cantidadPagosTdcPendientesLiberar > 0)
    ) {

        msg_pendientes = 1;

        if(mensaje!="")
            mensaje+="<br>";

        if(($("#tipo_usuario_app").val() == "8")||($("#tipo_usuario_app").val() == "7")){
            mensaje += "<span id='popupTagNotiPaymentTdcFC1'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC1"] + "</span>" + Trim(result.cantidadPagosTdcPendientes);
            if ($("#tipo_usuario_app").val() == "8") {
                mensaje += "<span id='popupTagNotiPaymentTdcFC2L'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC2L"] + "</span>";
            }
            if ($("#tipo_usuario_app").val() == "7") {
                mensaje += "<span id='popupTagNotiPaymentTdcFC2A'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC2A"] + "</span>";
            }
        }else{
            if ((result.rolesCustom=="A")||(result.rolesCustom=="AL")){
                if (Trim(result.cantidadPagosTdcPendientes)>0){
                    mensaje += "<span id='popupTagNotiPaymentTdcFC1'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC1"] + "</span>" + Trim(result.cantidadPagosTdcPendientes);
                    mensaje += "<span id='popupTagNotiPaymentTdcFC2A'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC2A"] + "</span>";
                }
            }
            if ((result.rolesCustom=="L")||(result.rolesCustom=="AL")){
                if (Trim(result.cantidadPagosTdcPendientesLiberar)>0){
                    if(mensaje!="")
                        mensaje +="<br>";
                    mensaje += "<span id='popupTagNotiPaymentTdcFC2'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC2"] + "</span>" + Trim(result.cantidadPagosTdcPendientesLiberar);
                    mensaje += "<span id='popupTagNotiPaymentTdcFC2L'>" + vbtol_props[idioma]["popupTagNotiPaymentTdcFC2L"] + "</span>";
                }
            }
        }
    }

    if (mensaje!=""){
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }

    /*loadNewsHome();    */
    if(mostrarActualizacion == "2"){
        if(linkActualizacion != null)
            loadActClient("");
    }else if(mostrarActualizacion == "3"){
        loadActClient(mostrarActualizacion);
    }


}

function TimeOut(){
    var url = urlTimeOut;
    var param={};
    sendServiceJSON(url,param,TimeOutSuccess,null,null);
}

function TimeOutSuccess(originalRequest){
    var result=originalRequest;
    minutesTimeOut = result.minutesTimeOut;
    idleTimeOut(minutesTimeOut);
}

function createNotify( template, vars, opts ){
    return $("#container").notify("create", template, vars, opts);
}

function infoPaginaBMLAJSONDataHome(){
    console.log('infoPaginaBMLAJSONDataHome');
    var url = urlTransfersCargarBMLAHome;
    var param={};
    var jsonTransfers=[];
    jsonTransfers[0]= "OTHER";
    jsonTransfers[1]= "";
    jsonTransfers[2]= "O";

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    console.log(url);
    console.log('###param ',param);

    sendServiceJSON(url,param,infoPaginaBMLASuccessHome,null,null);
}


function infoPaginaBMLASuccessHome(originalRequest){
    console.log('originalRequest',originalRequest);
    let result = originalRequest;
    let tamano = result.cuentasTOB.length;
    if(tamano > 3){
        tamano = 3;
    }

    let tableContent = '';
    for (let index = 0; index < tamano; index++) {
        const item = result.cuentasTOB[index];
        console.log('item ',item);
        let valor = item.value.split('|');
        tableContent += '<tr><td>'+item.extra3+'</td><td>'+valor[0]+'</td><td>'+valor[1]+'</td><td style="text-align: right;padding-right: 2em;"> $ '+ currencyFormat(item.extra) +' '+ item.extra1 + '</td></tr>';
    }

    let accountTableHome = '<table><tr class="TableHeadersTitle"><th id="Home_new_design_Type">Type</th><th id="Home_new_design_Account">Account</th><th id="Home_new_design_Product">Product</th><th style="text-align: right;padding-right: 2em;" id="Home_new_design_Available_Balance">Available balance</th></tr>'+tableContent+'</table>';
    $('#BalaceTableHome').html(accountTableHome);
}

//function cargarIdiomaJSONData(){
//    var url = urlcambioIdioma;
//    var param={};
//
//    sendServiceJSON(url,param,cargarIdiomaSuccess,null,null);
//}
//
//
//function cargarIdiomaSuccess(originalRequest){
//    label_es= originalRequest.valor_es;
//    label_en= originalRequest.valor_en;
//    title_es= originalRequest.valor_title_es;
//    title_en= originalRequest.valor_title_en;
//    idioma= originalRequest.idioma;
//    var usuarioAut=originalRequest.usuarioAut;
//
//    var props_en = {};
//    var props_es = {};
//
//    if(idioma=="_us_en"){
//        $.each(label_en,function(pos,item){
//            if($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button"){
//                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
//            }else{
//                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
//            }
//        });
//        $.each(title_en,function(pos,item){
//            $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
//        });
//        label_idioma="en";
//        cambiarIdiomaJSONData(label_idioma);
//    } else
//
//    if(idioma=="_ve_es"){
//        $.each(label_es,function(pos,item){
//            if(($("#"+item.substring(0, item.indexOf("="))).attr("type")=="button")||
//                ($("#"+item.substring(0, item.indexOf("="))).attr("type")=="reset")){
//                $("#"+item.substring(0, item.indexOf("="))).val(item.substring(item.indexOf("=")+1, item.length));
//            }else{
//                $("#"+item.substring(0, item.indexOf("="))).html(item.substring(item.indexOf("=")+1, item.length));
//            }
//        });
//
//        $.each(title_es,function(pos,item){
//            $("#"+Trim(item.split("=")[0])).attr("title",item.split("=")[1]);
//        });
//        label_idioma="es";
//        cambiarIdiomaJSONData(label_idioma);
//    }
//
//    /**
//     * Almacenar en vbtol_props
//     * */
//    var i = 0;
//    for (i = 0; i < label_en.length; i++) {
//        var key = "";
//        var value = "";
//        key = label_en[i].substring(0, label_en[i].indexOf("="));
//        value = label_en[i].substring(label_en[i].indexOf("=")+1, label_en[i].length);
//        props_en[key] = value;
//    }
//    for (i = 0; i < title_en.length; i++) {
//        var key = "";
//        var value = "";
//        key = title_en[i].substring(0, title_en[i].indexOf("="));
//        value = title_en[i].substring(title_en[i].indexOf("=")+1, title_en[i].length);
//        props_en[key] = value;
//    }
//    for (i = 0; i < label_es.length; i++) {
//        var key = "";
//        var value = "";
//        key = label_es[i].substring(0, label_es[i].indexOf("="));
//        value = label_es[i].substring(label_es[i].indexOf("=")+1, label_es[i].length);
//        props_es[key] = value;
//    }
//    for (i = 0; i < title_es.length; i++) {
//        var key = "";
//        var value = "";
//        key = title_es[i].substring(0, title_es[i].indexOf("="));
//        value = title_es[i].substring(title_es[i].indexOf("=")+1, title_es[i].length);
//        props_es[key] = value;
//    }
//
//    vbtol_props["_us_en"] = {};
//    vbtol_props["_us_en"] = props_en;
//    vbtol_props["_ve_es"] = {};
//    vbtol_props["_ve_es"] = props_es;
//
//    /** FIN vbtol_props */
//
//            $("#capa_carga").fadeOut("slow");
//            $("#username").focus();
//}

function cambiarIdiomaJSONData(label){
    console.log("cambiarIdiomaJSONData: "+ label);
    var url = urlcambiarIdioma;
    var param={};
    var jsonLogin=[];

    jsonLogin[0] =  label;
//    jsonLogin[1] =  valores;
    console.log("cambiarIdiomaJSONData: "+ jsonLogin[0]);

    param.jsonLogin=JSON.stringify({"parametros":jsonLogin});

    sendServiceJSON(url,param,cambiarIdiomaSuccess,null,null);
}


function cambiarIdiomaSuccess(originalRequest){
    var result= originalRequest;
    
    console.log("cambiarIdiomaJSONData: "+ result.idioma);
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


function  abrirManualTDC(mes,ano){
    if(idioma=="_us_en")
        window.open('../vbtonline/resources/documentos/VBT_VISA_Card_Activation.pdf','_blank');
    else
        window.open('../vbtonline/resources/documentos/VBT_Activacion_Tarjeta_VISA.pdf','_blank')
}

function  abrirCaymanPremier(){
    window.open('../vbtonline/resources/documentos/Cayman_premier.pdf','_blank');
}

function  abrirCaymanFinance(){
    window.open('../vbtonline/resources/documentos/Cayman_financial.pdf','_blank')
}

function  abrirVisaBenefits(){
    //if(idioma=="_us_en")
    window.open('../vbtonline/resources/documentos/VISA_BENEFITS_PORTAL.jpg','_blank');
    //else
    // window.open('../vbtonline/resources/documentos/VBT_Activacion_Tarjeta_VISA.pdf','_blank')
}

function  abrirVisaPromotion(){
    //if(idioma=="_us_en")
    window.open('../vbtonline/resources/documentos/VISA_PROMOTION.jpg','_blank');
    //else
    // window.open('../vbtonline/resources/documentos/VBT_Activacion_Tarjeta_VISA.pdf','_blank')
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

function abrirPDF(tipo){
    var url = "urlAceptarCondicionesTrasfe";
    var param={};
    var menu={};
    if (tipo="comisiones") {
        window.open('../vbtonline/resources/documentos/VBT_Schedule_of_Fees.pdf','_blank');
    }
}



function popupConfirm(mensaje, botonSi, botonNo, funcion) {
    botonSi = botonSi || "Ok";
    botonNo = botonNo || "No";
    if (!$("#div_bloqueo").hasClass("oculto")) {
        $("#div_bloqueo").addClass("oculto");
        $("#poppup_message").addClass("oculto");
        $("#confirmButtons").addClass("oculto");
        $("#msg_texto").empty();
        $("#btn_confirm_si").val("");
        $("#btn_confirm_no").val("");
        $("#alertButton").addClass("oculto");
        $("#btn_alert_ok").val("");
    }
    $("#div_bloqueo").removeClass("oculto");
    $("#poppup_message").removeClass("oculto");
    $("#confirmButtons").removeClass("oculto");
    $("#msg_texto").html(mensaje);
    $("#btn_confirm_si").val(botonSi);
    $("#btn_confirm_no").val(botonNo);
    $("#btn_confirm_si").click(function(){
        $("#div_bloqueo").addClass("oculto");
        $("#poppup_message").addClass("oculto");
        $("#confirmButtons").addClass("oculto");
        $("#msg_texto").empty();
        $("#btn_confirm_si").val("");
        $("#btn_confirm_no").val("");


        funcion();
    });
    //$("#btn_confirm_si").attr("onclick",funcion);
    $("#btn_confirm_no").click(function(){
        $("#div_bloqueo").addClass("oculto");
        $("#poppup_message").addClass("oculto");
        $("#confirmButtons").addClass("oculto");
        $("#msg_texto").empty();
        $("#btn_confirm_si").val("");
        $("#btn_confirm_no").val("");
    });
}

function popupAlert(mensaje, boton) {
    boton = boton || "OK";
    if (!$("#div_bloqueo").hasClass("oculto")) {
        $("#div_bloqueo").addClass("oculto");
        $("#poppup_message").addClass("oculto");
        $("#confirmButtons").addClass("oculto");
        $("#msg_texto").empty();
        $("#btn_confirm_si").val("");
        $("#btn_confirm_no").val("");
        $("#alertButton").addClass("oculto");
        $("#btn_alert_ok").val("");
    }
    $("#div_bloqueo").removeClass("oculto");
    $("#poppup_message").removeClass("oculto");
    $("#alertButton").removeClass("oculto");
    $("#msg_texto").html(mensaje);
    $("#btn_alert_ok").val(boton);
    $("#btn_alert_ok").click(function(){
        $("#div_bloqueo").addClass("oculto");
        $("#poppup_message").addClass("oculto");
        $("#alertButton").addClass("oculto");
        $("#msg_texto").empty();
        $("#btn_alert_ok").val("");
    });
}

//function nobackbutton(){
//    window.location.hash="no-back-button";
//    window.location.hash="Again-No-back-button" //chrome
//    window.onhashchange=function(){window.location.hash="no-back-button";}
//}



function sumarDiaFecha(fechaActual, sumarDias){

    var fecha=fechaActual.split('/')[2]+"/"+fechaActual.split('/')[1]+"/"+fechaActual.split('/')[0];

    // fecha=fecha.replace("-", "/").replace("-", "/");

    fecha= new Date(fecha);
    fecha.setDate(fecha.getDate()+sumarDias);

    var anio=fecha.getFullYear();
    var mes= fecha.getMonth()+1;
    var dia= fecha.getDate();

    if(mes.toString().length<2){
        mes="0".concat(mes);
    }

    if(dia.toString().length<2){
        dia="0".concat(dia);
    }

    return (dia+"/"+mes+"/"+anio);
}



// Función para calcular los días transcurridos entre dos fechas restaFechas =
//
function restarFechas(f1,f2) {
    var aFecha1 = f1.split('/');
    var aFecha2 = f2.split('/');
    var fFecha1 = Date.UTC(aFecha1[2],aFecha1[1]-1,aFecha1[0]);
    var fFecha2 = Date.UTC(aFecha2[2],aFecha2[1]-1,aFecha2[0]);
    var dif = fFecha2 - fFecha1;
    var dias = Math.floor(dif / (1000 * 60 * 60 * 24));
    return dias;
}

function random(){
    Math.floor((Math.random() * 1000000) + 1);
}

/*function loadNewsHome() {
    var mensaje="";
    var title ="";
    var nmButton ="";
    var notifyBefore = 120;
    if(idioma=="_us_en"){
        mensaje=mensaje+ "<h3 style='text-align: center;'>Maintenance service of VBT Bank & Trust Ltd. IT Platform for VISA Credit Cards<br>"
        mensaje=mensaje+ "Sunday Oct 20, from 2:00 am to 6:00 am (AST)</h3>"
        mensaje=mensaje+ "<br><p>Dear Customer,<br><br>In order to improve the quality of service offered to our clientele, " +
            "we advice you that next Sunday, October 20, 2019, from 2:00 am to 6:00 am (AST), we will be carrying out maintenance services in our " +
            "VISA Credit Card technology platform.<br><br> Please note that during this maintenance time, the following services may be interrupted: "   +
            "<ul><li>Payments process through points of sale (POS)</li><li>ATMs</li></ul> "+
            "<br>We appreciate your patience and understanding. Kindly ask you to take the necessary measures during this "+
            "time and we apologize for any inconvenience this may cause.  "+
            "<br><br>Sincerely,<br><br> VBT Bank & Trust Ltd.<p> ";
        nmButton = "Close";
        title ="ATTENTION";
    }else{
        mensaje=mensaje+ "<h3 style='text-align: center;'>Servicio de mantenimiento de nuestra plataforma tecnológica de tarjetas de crédito VISA de VBT Bank & Trust Ltd.<br>"
        mensaje=mensaje+ "Domingo, 20 de octubre de 2019, desde las 2:00 am hasta las 6:00 am (Hora Atlántico)</h3>"
        mensaje=mensaje+ "<br><p>Estimado cliente,<br><br>Con el propósito de mejorar la calidad de servicio ofrecida a nuestra clientela," +
            " le informamos que el próximo domingo 20 de octubre de 2019, desde las 2:00 am hasta las 6:00 am (hora Atlántico)," +
            " estaremos llevando a cabo servicios de mantenimiento en nuestra plataforma tecnológica de tarjetas de Crédito VISA. " +
            "<br><br> Durante este mantenimiento, se podrán ver afectados los siguientes servicios: "   +
            "<ul><li>Servicios de procesamiento a través de puntos de venta (POS)</li><li>Cajeros automáticos (ATMs)</li></ul> "+
            "<br>Agradecemos su paciencia y compresión. Le pedimos tomar las previsiones necesarias y ofrecemos disculpas  "+
            "por las molestias que esto pudiera ocasionarles. "+
            "<br><br>Atentamente,<br<br> VBT Bank & Trust Ltd.<p> ";
        nmButton = "Cerrar";
        title ="COMUNICADO";
    }
    $(function() {
        $( "#dialog" ).dialog({
            autoOpen: true,
            notifyBefore: 90,
            width: 600,
            height: "auto",
            resizable: false,
            title: title,
            modal: true,
            open: function(event, ui) {
                idleTime = setTimeout(function(){
                    $('#dialog').dialog('close');   // al abrir el cuadro de dialogo de la alerta se cierra en 30 seg si el usuario no hace clic en mantener sesión
                }, notifyBefore * 1000);

            },
            buttons: [{
                text : nmButton,
                click: function() {
                    $('#dialog').dialog("close");
                }
            }]
        });
    });
    $("#dialog").html(mensaje);
}
*/
function loadActClient(mostrar) {
    var mensaje="";
    var title ="";
    var nmButton ="";
    var notifyBefore = 120;
    var sectionsActs = "";
    if(idioma=="_us_en"){

        mensaje=mensaje+ "<h3 style='text-align: center;'>Client Data Update - VBT Bank & Trust Ltd.</h3>";

        if(mostrar=="3"){
            mensaje=mensaje+ "<p style='text-align: justify;'> Dear Client,<br><br>Your immediate attention to this important matter is greatly appreciated as we prepare to update our database. In order to continue using the Online system, we apreciate that you updating the information as soon as possible. You have fifteen (15) days from the first message." +
                "<br><br> If you have any questions concerning this request ," +
                " please contact your Relationship Manager Monday to Friday, between the hours of 8:00 a.m. – 5:00 p.m." +
                " Thank you in advance for your assistance and understanding as we move forward with this important service improvement initiative.";

        }else{
            if(!(motivosAct!=null)){
                mensaje=mensaje+ "<p style='text-align: justify;'> Dear Client,<br><br>Your immediate attention to this important matter is greatly appreciated as we prepare to update our database. In order to continue using the Online system, we apreciate that you updating the information as soon as possible. You have fifteen (15) days from the first message." +
                    "<br><br> If you have any questions concerning this request ," +
                    " please contact your Relationship Manager Monday to Friday, between the hours of 8:00 a.m. – 5:00 p.m." +
                    " Thank you in advance for your assistance and understanding as we move forward with this important service improvement initiative.";
            }else{
                mensaje=mensaje+ "<p style='text-align: justify;'> Dear Client,<br><br>We appreciate the timely manner you submitted the information required; however, we have verified the data and found that some information need modifying." +
                    " Please refer to the enclosed comments on the data and attachments provided." +
                    "<br><br> Keeping your data updated is important to offer you our best services." +
                    "<br><br> Should you have any further queries or concerns, please do not hesitate to contact your Account Manager from Monday through Friday between 8:00 a.m. to 5:00 p.m. (GMT-4).";
            }

            mensaje=mensaje+  "<br><center><a id='linkAct' href='"+linkActualizacion+"'>Update Data Information.<br> <img border='0' src='../vbtonline/resources/images/finger.png'></a></center>";
        }
        nmButton = "Accept";
        title ="ATTENTION";
    }else{
        mensaje=mensaje+ "<h3 style='text-align: center;'>Actualización de Información - VBT Bank & Trust Ltd.</h3>";

        if(mostrar=="3"){
            mensaje=mensaje+ "<p style='text-align: justify;'>Estimado Cliente,<br><br>Apreciamos su inmediata atención a este importante asunto mientras nos preparamos para actualizar nuestra base de datos. Para poder seguir haciendo uso del sistema Online agradecemos actualizar la información lo antes posible. Dispone de un lapso de quince (15) días a partir del primer mensaje." +
                "<br><br> Si tiene alguna pregunta al respecto, comuníquese con su Gerente de Relaciones de lunes a viernes," +
                " entre las 8:00 a.m. y las 5:00 p.m. Gracias de antemano por su ayuda y comprensión a medida que avanzamos " +
                "con esta importante iniciativa de mejora del servicio.</p>";

        }else{
            if(!(motivosAct!=null)){
                mensaje=mensaje+ "<p style='text-align: justify;'>Estimado Cliente,<br><br>Apreciamos su inmediata atención a este importante asunto mientras nos preparamos para actualizar nuestra base de datos. Para poder seguir haciendo uso del sistema Online agradecemos actualizar la información lo antes posible. Dispone de un lapso de quince (15) días a partir del primer mensaje." +
                    "<br><br> Si tiene alguna pregunta al respecto, comuníquese con su Gerente de Relaciones de lunes a viernes," +
                    " entre las 8:00 a.m. y las 5:00 p.m. Gracias de antemano por su ayuda y comprensión a medida que avanzamos " +
                    "con esta importante iniciativa de mejora del servicio.</p>";
            }else{
                mensaje=mensaje+ "<p style='text-align: justify;'>Estimado Cliente,<br><br>Agradecemos la manera oportuna en que envió la información solicitada; sin embargo, al verificar la data, hemos encontrado infromacion que necesita ser revisada. Por favor revise nuestros comentarios a la información y a los documentos adjuntos proporcionados." +
                    "<br><br> Mantener sus datos actualizados es importante para ofrecerle nuestro mejor servicio" +
                    "<br><br> Si tiene alguna pregunta o inquietud, no dude en comunicarse con su asesor de Cuenta de lunes a viernes de 8:00 a.m. a las 5:00 p.m. (GMT-4).";

            }
            mensaje = mensaje+"<br><center><a id='linkAct' href='"+linkActualizacion+"'>Actualizar Información de Datos.<br> <img border='0' src='../vbtonline/resources/images/finger.png'></a></center>";
        }
        nmButton = "Aceptar";
        title ="COMUNICADO";
    }
    $(function() {
        $( "#dialog_act" ).dialog({
            autoOpen: true,
            notifyBefore: 90,
            width: 650,
            height: "auto",
            closeOnEscape: diasRestantesPlazo > 0 ? true:false,
            resizable: false,
            title: title,
            modal: true,
            buttons: [{
                text : nmButton,
                click: function() {
                    if(diasRestantesPlazo > 0 ){
                        $('#dialog_act').dialog('close');
                    }else{
                        redireccionar(linkActualizacion);
                    }
                }
            }],
            close: function() {}
        });
    });
    $("#dialog_act").html(mensaje);
}

function loadAlertTimeout() {
    console.log("IDIOMA SELECCIONADO ES: ", idioma);
    var idleTime;
    var notifyBefore = 30;  // 30 segundos
    var mensaje="";
    var titte ="";
    var nmButton ="";
    if(idioma=="_us_en"){
        mensaje=mensaje+"During the last minutes no activity was detected on the page."
        nmButton = "Keep Session";
        titte =" Next session to expire";
    }else{
        mensaje=mensaje+"Durante los últimos minutos no se ha detectado actividad en la página."
        nmButton = "Mantener Sesión";
        titte ="La sesión está próxima a expirar";
    }
    $(function() {
        $( "#dialog" ).dialog({
            autoOpen: true,
            width: 400,
            height: "auto",
            resizable: false,
            title: titte,
            dialogClass: "no-close",
            modal: true,
            open: function(event, ui) {
                idleTime = setTimeout(function(){
                    $('#dialog').dialog('close');   // al abrir el cuadro de dialogo de la alerta se cierra en 30 seg si el usuario no hace clic en mantener sesión
                    //console.log("no mantuvo sesión...");
                    //salir();
                    redireccionar(urlSalir);
                }, notifyBefore * 1000);
            },
            buttons: [{
                text : nmButton,
                click: function() {
                    //console.log("si mantuvo sesión...");
                    if(idleTime){
                        clearTimeout(idleTime);
                        idleTime = null;
                    }
                    latidoWeb();
                    $('#dialog').dialog("close");
                }
            }],
            close: function() {}
        });
    });
    $("#dialog").html(mensaje);
}

function currencyFormat(valor){
  return  Number.parseFloat(valor).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');  // 12,345.67
}
var urlBackOfficeConsultarContratos="BackOffice_consultarContratos.action";
var urlBackOfficeCargarFiltroContratos="BackOffice_consultarContratos.action";
var urlBackOfficeCargarEditarContratosContratos="BackOffice_cargarEditarContratos.action";
var urlBackOfficeCargarCartera="BackOffice_cargarCartera.action";
var urlBackOfficeCargarAgregarContratosContratos="BackOffice_cargarAgregarContratos.action";
var urlBackOfficeBusquedaUsuariosContratos="BackOffice_busquedaUsuariosContrato.action";
var urlBackOfficeBusquedaCarterasContratos="BackOffice_busquedaCarterasContrato.action";
var urlBackOfficeConsultarDetallesContratos="BackOffice_consultarDetallesContratos.action";
var urlBackOfficeAgregarContratos="BackOffice_agregarContrato.action";
var urlBackOfficeEditarContratos="BackOffice_editarContrato.action";
var urlBackOfficeValidarUsuario="BackOffice_validarUsuario.action";
var urlParametrosPersonalesCargarContratos="BackOffice_cargarParametrosContratos.action";
var urlBackOfficeGuardarLogContratos = "BackOffice_guardarLogContratos.action";
var cargar_pagina_new = "";
var existeUsuario="OK";
var habilitarCheck="OK";
var btnVolverContratos="C";
var mensajeCartera="";

var usuario = "";
var contrato = "";
var estatus = "";
var oTable = "";
var oTable2 = "";
var div = "div_tabla_resultadoBusquedaUsuarioContrato";
var noInfo ="";
var tipoGrupo ="";
var tipoGrupoFiltroFC ="";
var tipoGrupoFiltroFA ="";
var giCount = 0;
var giCount2 = 0;
var giCountC = 0;
var giCountC2 = 0;
var numeroDetalle = 0;
var carteras = "";
var detalleCartera="";
var observacion="";
var habilitarBtn="";
var nCount=0;
var nCount2=0;
var AU_contratoGrupo="";
var AU_contratoCirif="";
var tipoContrato="";
var tipoGrupos="";
var tipoGruposFA="";
var tipoGruposFC="";
var tipoContratoGB="";
var requiereSoporteEc="";
var administradoresFC=0;

$(document).ready(function(){

    $('.nombre_AUcontrato').keypress(function (e) {
        var regex = new RegExp("^[a-zA-Z]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $("#tabla_AC_consultarUsuarioContrato .only_login").live('keypress', function (e) {
        //var regex = new RegExp("^[A-Za-z][A-Za-z0-9._]+[^._]$");
        var regex = new RegExp("[A-Za-z0-9._]+$");
        var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
        if (regex.test(str)) {
            return true;
        }
        if ((e.which==8)||(e.which==13)||(e.which==32)) {
            return true;
        }
        e.preventDefault();
        return false;
    });

    $.mask.definitions['~']='[12]';
    $.mask.definitions['+']='[246]';
    $(".mascaraCelular").mask("?/99999999999");

    $( "#AC_tipoContrato").change(function() {


        if ($("#AC_tipoContrato").val()!="-2"){
            if ($("#AC_tipoContrato").val()=="FI"){
                $("#AC_usuario_add").addClass("oculto");
            }else{
                $("#AC_usuario_add").removeClass("oculto");
            }

            $('#tabla_AC_consultarUsuarioContrato').dataTable().fnClearTable();
        }else{
            $("#AC_tipoContrato").val(tipoContratoGB);
        }


        tipoContratoGB= $("#AC_tipoContrato").val();
    });
     //*detalle de la tabla consulta contrato*//
//    $('#tabla_consulta_contratosBO .detalleBusquedaContrato').live('click', function () {
//        var nTr = $(this).parents('tr')[0];
//        if ( oTable.fnIsOpen(nTr) )
//        {
//            /* This row is already open - close it */
//            this.src = "resources/images/comun/nolines_plus.gif";
//            oTable.fnClose( nTr );
//        }
//        else
//        {
//            /* Open this row */
//            this.src = "resources/images/comun/nolines_minus.gif";
//            numeroDetalle = numeroDetalle + 1;
//            BackOfficeDetalleContratosJSONData($(this).attr("id").split("|")[0],$(this).attr("id").split("|")[2]);
//            oTable.fnOpen( nTr, fnFormatDetailsConsultarContratos($(this).attr("id")), 'details');
//        }
//
//    } );

    /*detalle de usuarios dentro de detalle de contrato*/
//    $('#tabla_consulta_contratosBO .detalleBusquedaContratoUsuario').live('click', function () {
//        var nTr = $(this).parents('tr')[0];
//        if ( oTable2.fnIsOpen(nTr) )
//        {
//            /* This row is already open - close it */
//            this.src = "resources/images/comun/nolines_plus.gif";
//            oTable2.fnClose( nTr );
//        }
//        else
//        {
//            /* Open this row */
//            this.src = "resources/images/comun/nolines_minus.gif";
//            oTable2.fnOpen( nTr, fnFormatDetailsResultadoBusquedaUsuarioDetalle($(this).attr("id")), 'details');
//        }
//
//    } );

    $("#codigoCelV").intlTelInput();
    $("#AU_codigoCelV").intlTelInput();


    //*detalle de la tabla buscar usuario contrato - editar contrato*//
    $('#tabla_resultadoBusquedaUsuarioContratoEC .detalleBusqueda').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsResultadoBusquedaUsuarioContrato($(this).attr("id")), 'details');
        }

    } );
    //*detalle de la tabla buscar usuario contrato*//
    $('#tabla_resultadoBusquedaUsuarioContrato .detalleBusqueda').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsResultadoBusquedaUsuarioContrato($(this).attr("id")), 'details');
        }

    } );
    //*detalle de la tabla buscar cartera contrato - editar contrato*//
    $('#tabla_resultadoBusquedaCarteraContrato_EC .detalleBusqueda').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsResultadoBusquedaCarteraContrato($(this).attr("id")), 'details');


        }

    } );
    //*detalle de la tabla buscar cartera contrato *//
    $('#tabla_resultadoBusquedaCarteraContrato .detalleBusqueda').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsResultadoBusquedaCarteraContrato($(this).attr("id")), 'details');


        }

    } );



    //Detalle cartera
    $('#tabla_EC_consultarCarterasContrato .detalleBusquedaCartera').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        var oTable2=$('#tabla_EC_consultarCarterasContrato').dataTable();
        if ( oTable2.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable2.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable2.fnOpen( nTr, fnFormatDetailsConsultarCarteras($(this).attr("id")), 'details');
        }

    } );

    //Detalle cartera add
    $('#tabla_AC_consultarCarterasContrato .detalleBusquedaCartera').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        var oTable2=$('#tabla_AC_consultarCarterasContrato').dataTable();
        if ( oTable2.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable2.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable2.fnOpen( nTr, fnFormatDetailsConsultarCarteras($(this).attr("id")), 'details');
        }

    } );

    $("#AC_reset").click(function(){
        var  noInfo1=">> Haga click en el correspondiente boton \'Buscar\' para buscar clientes y asociarlos como usuarios del contrato << ";
        var noInfo2=">> Haga click en el correspondiente boton \'Buscar\' para buscar carteras y asociarlas al contrato << ";

        $('#AC_observaciones').val("");
        $('#tabla_AC_consultarUsuarioContrato').dataTable().fnClearTable();
        $('#tabla_AC_consultarCarterasContrato').dataTable().fnClearTable();

        $("#AC_salvar").removeAttr("disabled", "disabled");
    });




    $( "#fechaDesdeFiltroCC" ).datepicker({ dateFormat: "dd-mm-yy",changeMonth: true,changeYear: true });
    $( "#fechaHastaFiltroCC" ).datepicker({ dateFormat: "dd-mm-yy",changeMonth: true,changeYear: true });

    $("#EC_search_usuario").click(function(){


        if ($("#EC_tipoContrato").val()=="FC"){
            contarAdministradoresEC();

            if (administradoresFC>=1){
                $("#mensaje_error").empty();
                $("#mensaje_error").html("Solo puede existir un Administrador por contrato");
                $("#div_mensajes_error").fadeIn("slow");
                administradoresFC=0;
            }else{
                usuario="EC_search_usuario";
                blanquearFormularios("div_buscarUsuarioContratoEC");
                $("#div_tabla_resultadoBusquedaUsuarioContratoEC").empty();
                //        infoPaginaBuscarBancosJSONData();

                $("#div_buscarUsuarioContratoEC").lightbox_me({centered: true, onLoad: function() {
                    $("#div_buscarUsuarioContratoEC").find("input:first").focus();
                }});
            }
        }else{
            usuario="EC_search_usuario";
            blanquearFormularios("div_buscarUsuarioContratoEC");
            $("#div_tabla_resultadoBusquedaUsuarioContratoEC").empty();
    //        infoPaginaBuscarBancosJSONData();

            $("#div_buscarUsuarioContratoEC").lightbox_me({centered: true, onLoad: function() {
                $("#div_buscarUsuarioContratoEC").find("input:first").focus();
            }});

//            e.preventDefault();
        }
    });


    $("#EC_parametrosPersonales").click(function(){
        $("#numeroContratoPC").html( $("#EC_numContrato").html());
        infoPaginaParametrosContratosJSONData();
        $("#div_tabla_EditarContratos").attr("style","display: none");
        $("#div_PARAMETROS_PERSONALES_CONTRATOS").attr("style","display:''");
    });

    $("#btn_PC_volver").click(function(){
        $("#div_tabla_EditarContratos").attr("style","display:''");
        $("#div_PARAMETROS_PERSONALES_CONTRATOS").attr("style","display:none");
    });


    $("#EC_search_cartera").click(function(){
        usuario="EC_search_cartera";
        blanquearFormularios("buscaformcartera");
        $("#div_tabla_resultadoBusquedaCarteraContrato_EC").empty();
        $("#paginacion_tabla_resultadoBusquedaCarteraContrato_EC").empty();
//        infoPaginaBuscarBancosJSONData();

        $("#div_buscarCarteraContratoEC").lightbox_me({centered: true, onLoad: function() {
            $("#div_buscarCarteraContratoEC").find("input:first").focus();
        }});

//            e.preventDefault();

    });

    $("#searchContrato").click(function(){
        cargar_pagina_new=false;
        BackOfficeContratosJSONData();

    });

    $("#resetBusquedaContrato").click(function(){
        blanquearFormularios("div_tabla_consultaContratos");
        $("#div_tabla_consulta_contratosBO").empty();
        $("#paginacion_tabla_consulta_contratosBO").empty();
    });


    $("#BUC_reset").click(function(){
        blanquearFormularios("buscaform_ac_bu");
    });


    $("#BUC_reset_EC").click(function(){
        blanquearFormularios("buscaform");
    });

    $("#BCC_reset").click(function(){
        blanquearFormularios("buscaform_ac");
    });

    $("#BCC_reset_EC").click(function(){
        blanquearFormularios("buscaformcartera");
    });


    $("#agregarContrato").click(function(){
        nCount=1;
        administradoresFC=0;
        $("#AC_tipoContrato").val("-2");
        if($("#AC_tipoContrato").hasClass("error_campo"))
            $("#AC_tipoContrato").removeClass("error_campo");
        BackOfficeCargarAgregarContratoJSONData();

    });

    $("#BUC_close").click(function(){
        $("#div_buscarUsuarioContrato").fadeOut("fast");

    });

    $("#BCC_close").click(function(){
        $("#div_buscarCarteraContrato").fadeOut("fast");

    });

    $("#BUC_close_EC").click(function(){
        $("#div_buscarUsuarioContratoEC").fadeOut("fast");

    });

    $("#BCC_close_EC").click(function(){
        $("#div_buscarCarteraContratoEC").fadeOut("fast");

    });

    $("#AC_search_usuario").click(function(){

        if ($("#AC_tipoContrato").val()!="-2"){

            if ($("#AC_tipoContrato").val()=="FC"){
                contarAdministradores();

                if (administradoresFC>=1){
                    $("#mensaje_error").empty();
                    $("#mensaje_error").html("Solo puede existir un Administrador por contrato");
                    $("#div_mensajes_error").fadeIn("slow");
                    administradoresFC=0;
                }else{
                    if($("#AC_tipoContrato").hasClass("error_campo"))
                        $("#AC_tipoContrato").removeClass("error_campo");
                    usuario="AC_search_usuario";
                    blanquearFormularios("buscaform_ac_bu");
                    $("#div_tabla_resultadoBusquedaUsuarioContrato").empty();
                    $("#paginacion_tabla_resultadoBusquedaUsuarioContrato").empty();

       /*             $("#div_buscarUsuarioContrato").lightbox_me({centered: true, onLoad: function() {
                        $("#div_buscarUsuarioContrato").find("input:first").focus();
                    }});*/

                    $("#div_buscarUsuarioContrato").modal({
                        showClose: !1,
                        modalClass: "form-modal",
                        fadeDuration: 100,
                        blockerClass: "form-modal--blocker",
                    })
                }
            }else{

                if($("#AC_tipoContrato").hasClass("error_campo"))
                    $("#AC_tipoContrato").removeClass("error_campo");
                usuario="AC_search_usuario";
                blanquearFormularios("buscaform_ac_bu");
                $("#div_tabla_resultadoBusquedaUsuarioContrato").empty();
                $("#paginacion_tabla_resultadoBusquedaUsuarioContrato").empty();

                /*$("#div_buscarUsuarioContrato").lightbox_me({centered: true, onLoad: function() {
                    $("#div_buscarUsuarioContrato").find("input:first").focus();
                }});*/

                $("#div_buscarUsuarioContrato").modal({
                    showClose: !1,
                    modalClass: "form-modal",
                    fadeDuration: 100,
                    blockerClass: "form-modal--blocker",
                })
        }
        }else{
            $("#AC_tipoContrato").addClass("error_campo");
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Debe Seleccionar el Tipo de Contrato");
            $("#div_mensajes_error").fadeIn("slow");
        }

    });



    $("#AC_usuario_add").click(function(){

        if ($("#AC_tipoContrato").val()!="-2"){

            contarAdministradores();

            if (administradoresFC>=1){
                $("#mensaje_error").empty();
                $("#mensaje_error").html("Solo puede existir un Administrador por contrato");
                $("#div_mensajes_error").fadeIn("slow");
                administradoresFC=0;
            }else{

                if($("#AC_tipoContrato").hasClass("error_campo"))
                    $("#AC_tipoContrato").removeClass("error_campo");

                usuario="AC_search_usuario";
                $("#div_AddBuscarUsuarioContrato .inputFormularioAU").each(function(){
                    this.value="";
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                });

                $("#AU_contratoCirif").val("-2");

                $("#AU_contratoGrupo").val("0000000016");

                $("#AU_codigoCelV").intlTelInput("setNumber", "+58");

                $("#AU_contratoCelular").mask("?/99999999999").val(" ");

/*                $("#div_AddBuscarUsuarioContrato").lightbox_me({centered: true, onLoad: function() {
                    $("#div_AddBuscarUsuarioContrato").find("input:first").focus();
                }});*/

                $("#div_AddBuscarUsuarioContrato").modal({
                    showClose: !1,
                    modalClass: "form-modal",
                    fadeDuration: 100,
                    blockerClass: "form-modal--blocker",
                })
            }

        }else{
            $("#AC_tipoContrato").addClass("error_campo");
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Debe Seleccionar el Tipo de Contrato");
            $("#div_mensajes_error").fadeIn("slow");
        }
    });



    $("#EC_usuario_add").click(function(){

        contarAdministradoresEC();

        if (administradoresFC>=1){
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Solo puede existir un Administrador por contrato");
            $("#div_mensajes_error").fadeIn("slow");
            administradoresFC=0;
        }else{
            usuario="AC_search_usuario";


            $("#div_AddBuscarUsuarioContratoEditar .inputFormularioEU").each(function(){
                this.value="";
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            });

            $("#EU_contratoCirif").val("-2");


            $("#EU_contratoGrupo").val("0000000016");

            $("#codigoCelV").intlTelInput("setNumber", "+58");

            $("#EU_contratoCelular").mask("?/99999999999").val(" ");

            $("#div_AddBuscarUsuarioContratoEditar").lightbox_me({centered: true, onLoad: function() {
                $("#div_AddBuscarUsuarioContratoEditar").find("input:first").focus();
            }});
        }

    });



    $("#btn_add_clear").click(function(){

        blanquearFormularios("div_AddBuscarUsuarioContrato");
        $("#div_AddBuscarUsuarioContrato .obligatorio_AUContrato").each(function(){

                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
        });
    });

    $("#btn_add_clear_edit").click(function(){

        blanquearFormularios("div_AddBuscarUsuarioContratoEditar");
        $("#div_AddBuscarUsuarioContratoEditar .obligatorio_EUContrato").each(function(){

            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");
        });
    });


    $("#btn_add_usr").click(function(){
      var mensaje="";
        $("#div_AddBuscarUsuarioContrato .obligatorio_AUContrato").each(function(){
                if((Trim($("#"+this.id).val())=="") || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
        });

        if(!isMail($("#AU_contratoEmail").val())){
            mensaje=mensaje+"Correo Invalido <br>";
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            var valor= $("#AU_contratoNombre").val()+"|"+$("#AU_contratoCirif").val()+"-"+$("#AU_contratoCiRifvalor").val()+"|"+$("#AU_contratoTelefono").val()+"|"+parseInt($("#AU_contratoCelular").val())+"|"+$("#AU_contratoDireccion").val()+"|"+$("#AU_contratoEmail").val()+"|"+$("#AU_contratoGrupo").val()+"|"+$("#AU_codigoCelV").intlTelInput("getSelectedCountryData").dialCode;
            fnClickAddRowUsuarioACAdministrator(valor);
            $("#AC_tipoContrato").val("FC");
            $("#div_AddBuscarUsuarioContrato").trigger('close');
        }

    });



    $("#btn_add_usr_edit").click(function(){
        var mensaje="";
        $("#div_AddBuscarUsuarioContratoEditar .obligatorio_EUContrato").each(function(){
            if((Trim($("#"+this.id).val())=="") || $("#"+this.id).val()=="-2"){
                mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });

        if(!isMail($("#EU_contratoEmail").val())){
            mensaje=mensaje+"Correo Invalido <br>";
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            var valor= $("#EU_contratoNombre").val()+"|"+$("#EU_contratoCirif").val()+"-"+$("#EU_contratoCiRifvalor").val()+"|"+$("#EU_contratoTelefono").val()+"|"+parseInt($("#EU_contratoCelular").val())+"|"+$("#EU_contratoDireccion").val()+"|"+$("#EU_contratoEmail").val()+"|"+$("#EU_contratoGrupo").val()+"|"+$("#codigoCelV").intlTelInput("getSelectedCountryData").dialCode;
            fnClickAddRowUsuarioACAdministratorEdit(valor);
            $("#div_AddBuscarUsuarioContratoEditar").trigger('close');
        }

    });






    $("#AC_search_cartera").click(function(){

        if ($("#AC_tipoContrato").val()!="-2"){
            if($("#AC_tipoContrato").hasClass("error_campo"))
                $("#AC_tipoContrato").removeClass("error_campo");
            usuario="AC_search_cartera";
            blanquearFormularios("buscaform_ac");
            $("#div_tabla_resultadoBusquedaCarteraContrato").empty();
            $("#paginacion_tabla_resultadoBusquedaCarteraContrato").empty();


   /*         $("#div_buscarCarteraContrato").lightbox_me({centered: true, onLoad: function() {
                $("#div_buscarCarteraContrato").find("input:first").focus();
            }});*/
            $("#div_buscarCarteraContrato").modal({
                showClose: !1,
                modalClass: "form-modal",
                fadeDuration: 100,
                blockerClass: "form-modal--blocker",
            })
        }else{
            $("#AC_tipoContrato").addClass("error_campo");
            $("#mensaje_error").empty();
            $("#mensaje_error").html("Debe Seleccionar el Tipo de Contrato");
            $("#div_mensajes_error").fadeIn("slow");
        }

    });

    $("#EC_search_usuario").click(function(){
        $("#paginacion_tabla_resultadoBusquedaUsuarioContratoEC").empty();
       // $("#div_buscarUsuarioContratoEC").fadeIn("fast");
    });

    $("#EC_search_cartera").click(function(){
        $("#div_buscarCarteraContratoEC").fadeIn("fast");
    });

    $("#BUC_search_EC").click(function(){

        if(Trim($("#CIRIFBUC_EC").val())=="" &&
            Trim($("#ClienteBUC_EC").val())=="" &&
                Trim($("#financialAdvisorBUC_EC").val())=="" &&
                    Trim($("#carteraEUC_EC").val())=="" &&
                        Trim($("#accountExecutiveBUC_EC").val())==""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html('Debe colocar al menos un par&aacute;metro de busqueda');
            $("#div_mensajes_error").fadeIn("slow");
        }
        else{
            $("#div_mensajes_error").fadeOut("fast");
            $("#div_carga_usuario_EBC").removeClass("oculto");
            BackOfficeBusquedaUsuariosECJSONData();
        }
    });

    $("#BCC_search_EC").click(function(){
        $("#div_carga_carteras_EBC").removeClass("oculto");
        BackOfficeBusquedaCarterasECJSONData();
    });

    //agregar Usuario
    $("#BUC_search").click(function(){
        if(Trim($("#CIRIFBUC").val())=="" &&
            Trim($("#ClienteBUC").val())=="" &&
                Trim($("#financialAdvisorBUC").val())=="" &&
                    Trim($("#accountExecutiveBUC").val())=="" &&
                         Trim($("#carteraAUC").val())=="")
        {

            $("#mensaje_error").empty();
            $("#mensaje_error").html('Debe colocar al menos un par&aacute;metro de búsqueda');
            $("#div_mensajes_error").fadeIn("slow");
        }
        else {
          $("#div_mensajes_error").fadeOut("fast");
          $("#div_carga_usuario_ABC").removeClass("oculto");
          $("#paginacion_tabla_resultadoBusquedaUsuarioContrato").empty();
          BackOfficeBusquedaUsuariosJSONData();
        }
    });

    $("#BCC_search").click(function(){
        $("#div_carga_carteras_ABC").removeClass("oculto");
        BackOfficeBusquedaCarterasJSONData();
    });




    $("#btn_PC_aceptar").click(function(){

        var mensaje ="";
        $(".requerido_PC").each(function(){

            if(Trim($("#"+this.id).val())==""){
                if(idioma=="_us_en")
                    mensaje=mensaje+"Required field / must be greater than zero   - "+ this.title+"<br>";
                else
                    mensaje=mensaje+"Campo requerido / debe ser mayou a cero - "+ this.title+"<br>";
                $("#"+this.id).addClass("error_campo");
            }else{
                if($("#"+this.id).hasClass("error_campo"))
                    $("#"+this.id).removeClass("error_campo");
            }
        });

        if(mensaje == ""){
            var parametrosPersonales={};
            var parametrosPersonales2={};

            parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PC").val();
            parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PC").val().replace(/\./g,"");
            parametrosPersonales.vbt_mmaxtd= parametrosPersonales.vbt_mmaxtd.replace(",",".");
            parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PC").val().replace(/\./g,"");
            parametrosPersonales.vbt_mminto= parametrosPersonales.vbt_mminto.replace(",",".");
            parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PC").val().replace(/\./g,"");
            parametrosPersonales.vbt_mmto= parametrosPersonales.vbt_mmto.replace(",",".");

            parametrosPersonales.ex_nmtd= $("#PC_numeroMaxTransDiarias_OB").val();
            parametrosPersonales.ex_mmtd= $("#PC_montoMaxTransDiarias_OB").val().replace(/\./g,"");
            parametrosPersonales.ex_mmtd= parametrosPersonales.ex_mmtd.replace(",",".");
            parametrosPersonales.ex_mminto= $("#PC_montoMinTransOpe_OB").val().replace(/\./g,"");
            parametrosPersonales.ex_mminto= parametrosPersonales.ex_mminto.replace(",",".");
            parametrosPersonales.ex_mmto= $("#PC_montoMinTransDiarias_OB").val().replace(/\./g,"");
            parametrosPersonales.ex_mmto= parametrosPersonales.ex_mmto.replace(",",".");

//            parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PC").val();
//            parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PC").val().replace(".","");
//            parametrosPersonales.vbt_mmaxtd= parametrosPersonales.vbt_mmaxtd.replace(",",".");
//            parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PC").val().replace(".","");
//            parametrosPersonales.vbt_mminto= parametrosPersonales.vbt_mminto.replace(",",".");
//            parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PC").val().replace(".","");
//            parametrosPersonales.vbt_mmto= parametrosPersonales.vbt_mmto.replace(",",".");
//
//            parametrosPersonales.ex_nmtd= $("#PC_numeroMaxTransDiarias_OB").val();
//            parametrosPersonales.ex_mmtd= $("#PC_montoMaxTransDiarias_OB").val().replace(".","");
//            parametrosPersonales.ex_mmtd= parametrosPersonales.ex_mmtd.replace(",",".");
//            parametrosPersonales.ex_mminto= $("#PC_montoMinTransOpe_OB").val().replace(".","");
//            parametrosPersonales.ex_mminto= parametrosPersonales.ex_mminto.replace(",",".");
//            parametrosPersonales.ex_mmto= $("#PC_montoMinTransDiarias_OB").val().replace(".","");
//            parametrosPersonales.ex_mmto= parametrosPersonales.ex_mmto.replace(",",".");

        }

         //validacion vbt



        if(parseFloat(parametrosPersonales.vbt_mminto) > parseFloat(parametrosPersonales.vbt_mmaxtd)){
             mensaje = mensaje + "El monto m&iacute;nimo de  transferencias por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.vbt_mmaxtd+"<br>";
            $("#montoMinTransOpe_PC").addClass("error_campo");
        }else
            $("#montoMinTransOpe_PC").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.vbt_mminto) > parseFloat(parametrosPersonales.vbt_mmto)){
            mensaje = mensaje + "El monto m&iacute;nimo de transferencias por operaci&oacute;n sobrepasa monto m&aacute;ximo de transferencia por operaci&oacute;n, por favor introduzca una cantidad menor a: "+parametrosPersonales.vbt_mmaxtd+"<br>";
            $("#montoMinTransOpe_PC").addClass("error_campo");
        }else
            $("#montoMinTransOpe_PC").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.vbt_mmto) > parseFloat(parametrosPersonales.vbt_mmaxtd)){
             mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.vbt_mmaxtd+"<br>";
            $("#montoMinTransDiarias_PC").addClass("error_campo");
        }else
            $("#montoMinTransDiarias_PC").removeClass("error_campo");

         //validacion Otros Bncos




        if(parseFloat(parametrosPersonales.ex_mminto) > parseFloat(parametrosPersonales.ex_mmtd)){
            mensaje = mensaje + "El monto m&iacute;nimo de  transferencias por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.ex_mmtd+"<br>";
            $("#PC_montoMinTransOpe_OB").addClass("error_campo");
        }else
            $("#PC_montoMinTransOpe_OB").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.ex_mminto) > parseFloat(parametrosPersonales.ex_mmto)){
            mensaje = mensaje + "El monto m&iacute;nimo de transferencias por operaci&oacute;n sobrepasa monto m&aacute;ximo de transferencia por operaci&oacute;n, por favor introduzca una cantidad menor a: "+parametrosPersonales.ex_mmto+"<br>";
            $("#PC_montoMinTransOpe_OB").addClass("error_campo");
        }else
            $("#PC_montoMinTransOpe_OB").removeClass("error_campo");


        if(parseFloat(parametrosPersonales.ex_mmto) > parseFloat(parametrosPersonales.ex_mmtd)){
            mensaje = mensaje + "El monto m&iacute;nimo de la transferencia por operaci&oacute;n sobrepasa el monto m&aacute;ximo de transferencias diarias, por favor introduzca una cantidad menor a: "+parametrosPersonales.ex_mmtd+"<br>";
            $("#PC_montoMinTransDiarias_OB").addClass("error_campo");
        }else
            $("#PC_montoMinTransDiarias_OB").removeClass("error_campo");

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            parametrosContratosActualizarJSONData();
        }


    })



    $("#AC_back").click(function(){
        $("#div_tabla_AgregarContratos").attr("style","display: none");
        $("#div_tabla_consultaContratos").attr("style","display: ");
        blanquearFormularios("div_tabla_AgregarContratos");
        $("#AC_requiereSoporte").val('S');

    });

    $("#EC_back").click(function(){
        $("#div_tabla_EditarContratos").attr("style","display: none");
        $("#div_tabla_AgregarContratos").attr("style","display: none");
        $("#div_tabla_consultaContratos").attr("style","display: ");
        blanquearFormularios("div_tabla_editarContratos");
    });

    $("#AC_salvar").click(function(){
        var lista_usuario=[];
        var registrar="OK";

        var mensaje="";
        $("#tabla_AC_consultarUsuarioContrato .obligatorio_AUBO").each(function(){
            if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
                if((Trim($("#"+this.id).val())=="") || $("#"+this.id).val()=="-2" || $("#"+this.id).val()=="58| "){
                    mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            }
        });


        if($("#tabla_AC_consultarUsuarioContrato tbody").find(".dataTables_empty").size()==1) {
            mensaje=mensaje+"Campo requerido - Debe Seleccionar un usuario <br>";
        }

        if($("#tabla_AC_consultarCarterasContrato tbody").find(".dataTables_empty").size()==1) {
            mensaje=mensaje+"Campo requerido - Debe Seleccionar alguna cartera <br>";
        }

        var lgt=0;
        var caracateres=0;
        $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
            if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
                if (Trim($("#loginUsuario_"+this.id.split("_")[1]).val()).length<=5){
                    lgt++;
                    $("#loginUsuario_"+this.id.split("_")[1]).addClass("error_campo");
                }

                //if (!esSoloLetrasNumeros(Trim($("#loginUsuario_"+this.id.split("_")[1]).val()))){
                if (!isLogin(Trim($("#loginUsuario_"+this.id.split("_")[1]).val()))){
                    caracateres++;
                    $("#loginUsuario_"+this.id.split("_")[1]).addClass("error_campo");
                }

            }
        });

        if(lgt>0){
            mensaje=mensaje+"El usuario debe tener  al menos 6 caracteres<br>";
        }

        if(caracateres>0){
            mensaje=mensaje+"El usuario solo puede contener caracteres alfanumericos separados por punto (.) o guión bajo(_)<br>";
        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{

            $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
                if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
                    var usuario={};

                    BackOfficeValidarUsuarioJSONData (limpiar_espacios($("#loginUsuario_"+this.id.split("_")[1]).val()));
                    //Indica que el login no existe por lo tanto el usuario puede ser agregado
                    if (existeUsuario=="OK"){
                        usuario.nombres=$("#nombreUsuario_"+this.id.split("_")[1]).html();
                        usuario.login=limpiar_espacios($("#loginUsuario_"+this.id.split("_")[1]).val());
                        usuario.email=$("#email_"+this.id.split("_")[1]).val();
                        usuario.tipo=$("#tipoCliente_"+this.id.split("_")[1]).val();


                        if (($("#datosUC_"+this.id.split("_")[1])).length){
                            usuario.codpercli="";
                            usuario.tipo_cirif=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[1].split("-")[0];
                            usuario.cirif=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[1].split("-")[1];
                            usuario.nombres=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[0];
                            usuario.telefono=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[2];
                            usuario.direccion=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[4];
                            usuario.relacion="NO OK";
                        }else{
                            usuario.codpercli=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[2];
                            usuario.tipo_cirif=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[1].split("-")[0];
                            usuario.cirif=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[1].split("-")[1];
                            usuario.relacion="OK";

                        }

                        usuario.telefono_celular=$("#telefonoCelular_"+this.id.split("_")[1]).val().split("|")[1];
                        usuario.codigoPais=$("#telefonoCelular_"+this.id.split("_")[1]).val().split("|")[0];


                        lista_usuario.push(usuario);
                    }else{
                        registrar="NO OK";
                        $("#loginUsuario_"+this.id.split("_")[1]).addClass("error_campo");
                        mensaje="El Login ya se encuentra registrado <br>";
                    }
                }
            });

            if (lista_usuario.length==0){
                mensaje=mensaje+"Campo requerido - Debe Seleccionar un usuario <br>";
                registrar="NO OK";
            }else{
                var lista_carteras=[];
                $.each($("#tabla_AC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
                    if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
                        var cartera={};
                        cartera.codigoCartera=$("#codigoCartera_"+this.id.split("_")[1]).html();
                        cartera.modalidad=$("#modalidad_"+this.id.split("_")[1]).html();
                        cartera.estatus=$("#estatus_"+this.id.split("_")[1]).html();
                        cartera.clientePrincipal=$("#clientePrincipal_"+this.id.split("_")[1]).html();
                        cartera.asesor=$("#asesor_"+this.id.split("_")[1]).html();
                        cartera.ejecutivo=$("#ejecutivo_"+this.id.split("_")[1]).html();

                        lista_carteras.push(cartera);
                    }
                });
                if (lista_carteras.length==0){
                    mensaje=mensaje+"Campo requerido - Debe Seleccionar alguna cartera <br>";
                    registrar="NO OK";
                }
            }
//         alert(JSON.stringify(lista_carteras));
            if (registrar=="OK")  {
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeAgregarContratoJSONData(lista_carteras,lista_usuario);
            }else{
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }


    });

    $("#EC_salvar").click(function(){
        var lista_usuarios_nuevos=[];
        var mensaje="";
        var registrar="OK";
        var enc=false;
        if($("#tabla_EC_consultarUsuarioContrato tbody").find(".dataTables_empty").size()==1) {
            mensaje=mensaje+"Campo requerido - Debe Seleccionar un usuario <br>";
        }

        if($("#tabla_EC_consultarUsuarioContrato tbody").find(".dataTables_empty").size()==1) {
            mensaje=mensaje+"Campo requerido - Debe Seleccionar alguna cartera <br>";
        }

        if(($("#EC_estatusContratoFiltro").val()==4)) {
            $.each($("#motivosRechazo .motivoRechazo"),function(pos,item){
                if($("#"+this.id).is(':checked')){
                   enc=true;
                }
            });
           if (!enc){
               mensaje=mensaje+"Debe seleccionar un motivo de rechazo <br>";
           }

        }


        if(($("#EC_estatusContratoFiltro").val()==2)||($("#EC_estatusContratoFiltro").val()==3)) {
            $.each($("#tabla_EC_consultarUsuarioContrato .ObligatorioECEmail"),function(pos,item){
               $("#"+this.id).removeClass("ObligatorioECEmail");

            });
            $.each($("#tabla_EC_consultarUsuarioContrato .ObligatorioECTelefono"),function(pos,item){
                $("#"+this.id).removeClass("ObligatorioECTelefono");
            });

        }
//        else {
//            $.each($("#tabla_EC_consultarUsuarioContrato .ObligatorioECEmail"),function(pos,item){
//                if($("#"+this.id).val() == "") {
//                    mensaje=mensaje+"Campo requerido - "+ this.title+" - "+ $("#loginUsuarioV_"+pos).html() +"<br>";
//                    $("#"+this.id).addClass("error_campo");
//                }
//                else {
//                    $("#"+this.id).removeClass("error_campo");
//                }
//            });
//
//            $.each($("#tabla_EC_consultarUsuarioContrato .ObligatorioECTelefono"),function(pos,item){
//                if($("#"+this.id).val() == "58| ") {
//                    mensaje=mensaje+"Campo requerido - "+ this.title+" - "+ $("#loginUsuarioV_"+pos).html() +"<br>";
//                    $("#"+this.id).addClass("error_campo");
//                }
//                else {
//                    $("#"+this.id).removeClass("error_campo");
//                }
//            });
//        }

        $("#tabla_EC_consultarUsuarioContrato .obligatorio_EUBO").each(function(){
            if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
                if((Trim($("#"+this.id).val())=="") || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            }
        });

        $("#tabla_EC_consultarUsuarioContrato .ObligatorioECTelefono").each(function(pos,item){
            if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
                if(($("#"+this.id).val()=="58| ") || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Campo requerido - "+ this.title + " - " + $("#loginUsuarioV_"+pos).html() + "<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            }
        });


        $("#tabla_EC_consultarUsuarioContrato .ObligatorioECEmail").each(function(pos,item){
            if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
                if(($("#"+this.id).val()=="") || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Campo requerido - "+ this.title + " - " + $("#loginUsuarioV_"+pos).html() +"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            }
        });


        var lgt=0;
        var caracateres=0;
        $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
            if($("#"+item.id).is(':checked')){
                if (Trim($("#loginUsuario_"+this.id.split("_")[1]).val()).length<=5){
                    lgt++;
                    $("#loginUsuario_"+this.id.split("_")[1]).addClass("error_campo");
                }

                if (!esSoloLetrasNumeros(Trim($("#loginUsuario_"+this.id.split("_")[1]).val()))){
                    caracateres++;
                    $("#loginUsuario_"+this.id.split("_")[1]).addClass("error_campo");
                }

            }
        });

        if(lgt>0){
            mensaje=mensaje+"El usuario debe tener  al menos 6 caracteres<br>";
        }

        if(caracateres>0){
            mensaje=mensaje+"El usuario no puede contener caracteres especiales ni acentos<br>";
        }


        //Valida los campos Obligatorios
        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            mensajeCartera="";
            //Valida las carteras con el tipo de Contrato
            //Debe validar ya que es un contrato activo o se esta activando
//            if(($("#estatusContratoAnterior").val()=="0")&&($("#EC_estatusContratoFiltro").val()=="1")){
//                $.each($("#tabla_EC_consultarCarterasContrato .carteraVieja"),function(pos,item){
//                    if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
//                        BackOfficevalidarCarteraContratoJSONData($("#codigoCarteraV_"+this.id.split("_")[1]).html());
//                    }
//                });
//
//                $.each($("#tabla_EC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
//                    if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
//                        BackOfficevalidarCarteraContratoJSONData($("#codigoCarteraV_"+this.id.split("_")[1]).html());
//                    }
//                });
//
//            }


            if(($("#EC_estatusContratoFiltro").val()=="1")){
                $.each($("#tabla_EC_consultarCarterasContrato .carteraVieja"),function(pos,item){
                    if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
                        BackOfficevalidarCarteraContratoJSONData($("#codigoCarteraV_"+this.id.split("_")[1]).html());
                    }
                });

                $.each($("#tabla_EC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
                    if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
                        BackOfficevalidarCarteraContratoJSONData($("#codigoCarteraV_"+this.id.split("_")[1]).html());
                    }
                });

            }

            if(mensajeCartera!=""){
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensajeCartera);
                $("#div_mensajes_error").fadeIn("slow");
            }else{
                $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
                    if($("#"+item.id).is(':checked')){

                        BackOfficeValidarUsuarioJSONData (limpiar_espacios($("#loginUsuario_"+this.id.split("_")[1]).val()));
                        //Indica que el login no existe por lo tanto el usuario puede ser agregado
                        if (existeUsuario=="OK"){

                            var usuario={};
                            usuario.nombres=$("#nombreUsuario_"+this.id.split("_")[1]).html();
                            usuario.login=limpiar_espacios($("#loginUsuario_"+this.id.split("_")[1]).val());
                            usuario.email=$("#emailV_"+this.id.split("_")[1]).val();
                            usuario.tipo=$("#tipoCliente_"+this.id.split("_")[1]).val();
                            usuario.telefono_celular=$("#telefonoCelularV_"+this.id.split("_")[1]).val().split("|")[1];
                            usuario.codigoPais=$("#telefonoCelularV_"+this.id.split("_")[1]).val().split("|")[0];

                            if (($("#datosUC_"+this.id.split("_")[1])).length){
                                usuario.codpercli="";
                                usuario.tipo_cirif=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[1].split("-")[0];
                                usuario.cirif=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[1].split("-")[1];
                                usuario.nombres=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[0];
                                usuario.telefono=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[2];
                                usuario.direccion=$("#datosUC_"+this.id.split("_")[1]).val().split("|")[4];
                                usuario.relacion="NO OK";
                            }else{
                                usuario.codpercli=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[2];
                                usuario.tipo_cirif=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[1].split("-")[0];
                                usuario.cirif=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[1].split("-")[1];
                                usuario.relacion="OK";

                            }





                            lista_usuarios_nuevos.push(usuario);


                        }else{
                            registrar="NO OK";
                            $("#loginUsuario_"+this.id.split("_")[1]).addClass("error_campo");
                            mensaje="El Login ya se encuentra registrado <br>";
                        }
                    }
                });

                var lista_usuarios_viejos=[];
                $.each($("#tabla_EC_consultarUsuarioContrato .loginUsuario"),function(pos,item){

                        var usuarioViejo={};
                        usuarioViejo.nombres=Trim($("#nombreUsuarioV_"+this.id.split("_")[1]).html());
                        usuarioViejo.login=Trim($("#loginUsuarioV_"+this.id.split("_")[1]).html());
                        usuarioViejo.email=$("#emailV_"+this.id.split("_")[1]).val();
                        usuarioViejo.tipo=$("#tipoClienteCodigoV_"+this.id).val();
    //                    usuarioViejo.codpercli=$("#"+this.id).val().split("|")[1];
                        usuarioViejo.codpercli=$("#codpercli_"+this.id.split("_")[1]).val().split("|")[2];
                        usuarioViejo.tipo_cirif=$("#"+this.id).val().split("|")[0].split("-")[0];
                        usuarioViejo.cirif=$("#"+this.id).val().split("|")[0].split("-")[1];
                        usuarioViejo.telefono_celular=$("#telefonoCelularV_"+this.id.split("_")[1]).val().split("|")[1];
                        usuarioViejo.codigoPais=$("#telefonoCelularV_"+this.id.split("_")[1]).val().split("|")[0];
                        lista_usuarios_viejos.push(usuarioViejo);


                });


                if ((lista_usuarios_nuevos.length==0)&&(lista_usuarios_viejos.length==0)){
                    mensaje=mensaje+"Campo requerido - Debe Seleccionar un usuario <br>";
                    registrar="NO OK";
                }else{
                    var lista_carteras_viejas=[];
                    $.each($("#tabla_EC_consultarCarterasContrato .carteraVieja"),function(pos,item){
                        if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
                            var cartera_vieja={};
                            cartera_vieja.codigoCartera=$("#codigoCarteraV_"+this.id.split("_")[1]).html();
                            cartera_vieja.modalidad=$("#modalidadV_"+this.id.split("_")[1]).html();
                            cartera_vieja.estatus=$("#estatusV_"+this.id.split("_")[1]).html();
                            cartera_vieja.clientePrincipal=$("#clientePrincipalV_"+this.id.split("_")[1]).html();
                            cartera_vieja.asesor=$("#asesorV_"+this.id.split("_")[1]).html();
                            cartera_vieja.ejecutivo=$("#ejecutivoV_"+this.id.split("_")[1]).html();

                            lista_carteras_viejas.push(cartera_vieja);
                        }
                    });

                    var lista_carteras_nuevas=[];
                    $.each($("#tabla_EC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
                        if($("#guardarCartera_"+this.id.split("_")[1]).is(':checked')){
                            var cartera_nueva={};
                            cartera_nueva.codigoCartera=$("#codigoCarteraV_"+this.id.split("_")[1]).html();
                            cartera_nueva.modalidad=$("#modalidad_"+this.id.split("_")[1]).html();
                            cartera_nueva.estatus=$("#estatus_"+this.id.split("_")[1]).html();
                            cartera_nueva.clientePrincipal=$("#clientePrincipal_"+this.id.split("_")[1]).html();
                            cartera_nueva.asesor=$("#asesor_"+this.id.split("_")[1]).html();
                            cartera_nueva.ejecutivo=$("#ejecutivo_"+this.id.split("_")[1]).html();

                            lista_carteras_nuevas.push(cartera_nueva);
                        }
                    });

                    if ((lista_carteras_nuevas.length==0)&&(lista_carteras_viejas.length==0)){
                        mensaje=mensaje+"Campo requerido - Debe Seleccionar alguna cartera <br>";
                        registrar="NO OK";
                    }
                }
        //         alert(JSON.stringify(lista_carteras));

                if (registrar=="OK")  {
                    $("#div_carga_espera").removeClass("oculto");
                    BackOfficeEditarContratoJSONData(lista_carteras_nuevas,lista_usuarios_nuevos,lista_carteras_viejas,lista_usuarios_viejos);
                }else{
                    $("#mensaje_error").empty();
                    $("#mensaje_error").html(mensaje);
                    $("#div_mensajes_error").fadeIn("slow");
                }
            }
        }
    });





});

function verificarSiExisteUsuario_AC(valor){
    cuenta = 0;
    var encontro = "NO";
    $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
        if($("#codpercli_"+this.id.split("_")[1]).val().split("|")[0]==valor.split("|")[2]){
            encontro ="SI";
            return false;
        }
    });
    if(encontro=="NO"){

        fnClickAddRowUsuarioAC(valor);

    }else{
        alert("Este usuario ya fue seleccionado");
    }
}

function verificarSiExisteUsuario_EC(valor){
    cuenta = 0;
    var encontro = "NO";
    $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
        if($("#guardar_"+this.id.split("_")[1]).val().split("|")[1]==valor.split("|")[4]){
            encontro ="SI";
            return false;
        }
    });
    $.each($("#tabla_EC_consultarUsuarioContrato .usuarioViejo"),function(pos,item){
        if($("#guardar_"+this.id.split("_")[1]).val().split("|")[1]==valor.split("|")[4]){
            encontro ="SI";
            return false;
        }
    });
    if(encontro=="NO"){
        fnClickAddRowUsuario(valor);
        $("#EC_usuario_add").addClass("oculto");
    }else{
        alert("Este usuario ya fue seleccionado");
    }
}

function fnClickAddRowUsuario(valor) {
    var id = valor.split("|")[1];
    var codpercli=(valor.split("|")[2]+'|'+valor.split("|")[3]+'|'+valor.split("|")[4]).split("|")[2];
     var disabled="";


    if ($("#EC_tipoContrato").val()=="FC")
        disabled="disabled='disabled'";

    $('#tabla_EC_consultarUsuarioContrato').dataTable().fnAddData( [
        '<input type="checkbox" checked="checked" class="usuarioAgregarEC nuevoUsuario" id="guardar_'+giCount+'" value="'+valor.split("|")[3]+'|'+valor.split("|")[4]+'">',
        '<span id="nombreUsuario_'+giCount+'" class="nombreValidar">'+valor.split("|")[0]+'</span>',
        '<input type="TEXT" id="loginUsuario_'+giCount+'"title="Usuario" class="input input--form loginUsuario obligatorio_EUBO" maxlength="16" style="width: 220px" >',
       // '<span id="email_'+giCount+'" class="email">'+valor.split("|")[1]+'</span><input type="hidden" value="'+valor.split("|")[2]+'|'+valor.split("|")[3]+'|'+valor.split("|")[4]+'" id="codpercli_'+giCount+'"> ',
        '<select  id="emailV_'+giCount+'" title="Email" class="select-section__select select-section__select--form email inputFormulario ObligatorioECEmail" ></select>'+'<input type="hidden" value="'+valor.split("|")[2]+'|'+valor.split("|")[3]+'|'+valor.split("|")[4]+'" id="codpercli_'+giCount+'"> ',
        '<select  id="tipoCliente_'+giCount+'" title="Tipo Cliente" class="select-section__select select-section__select--form tipoCliente" '+disabled+'></select>',
        '<select  id="telefonoCelularV_'+giCount+'" title="Telefono Celular" class="select-section__select select-section__select--form tipoCliente   inputFormulario ObligatorioECTelefono" ></select>',
        ''] );
        BackOfficeCargarNumero(codpercli);
        cargar_selectTelefono("telefonoCelularV_"+giCount,telefonosUsuario);
        cargar_selectEmail("emailV_"+giCount,correosUsuario);
        cargar_select("tipoCliente_"+giCount,tipoGrupo);
        removerGruposEC($("#tipoClienteCodigoV_0").val());
        removerGruposAllEC();

    if ($("#EC_tipoContrato").val()=="FC")
        $("#tipoCliente_"+giCount2).val("0000000016");

        giCount++;
//    verificarSiExiste();
}

function fnClickAddRowCartera(valor) {


    cuenta = 0;
    var encontro = "NO";
    $.each($("#tabla_EC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
        if($("#codigoCarteraV_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });
    $.each($("#tabla_EC_consultarCarterasContrato .carteraVieja"),function(pos,item){
        if($("#codigoCarteraV_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });

    $.each($("#tabla_EC_consultarCarterasContrato .codigoCartera"),function(pos,item){
        if($("#codigoCarteraV_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });

    if(encontro=="NO"){

        if (valor.split("|")[6]!="0")
            habilitarCheck="checked";
        else
            habilitarCheck="disabled";
        BackOfficeCargarCarteraJSONData(valor.split("|")[0]);

    }else{
        alert("Esta cartera ya fue agregada al contrato");
    }


}

function fnClickAddRowUsuarioAC(valor) {
    var id = valor.split("|")[1];
    var codpercli=(valor.split("|")[2]+'|'+valor.split("|")[3]+'|'+valor.split("|")[4]).split("|")[2];
    var tipoGrupoFiltroFC ="";
    var tipoGrupoFiltroFA ="";
    var disabled="";

    if ($("#AC_tipoContrato").val()=="FC")
        disabled="disabled='disabled'";

    $('#tabla_AC_consultarUsuarioContrato').dataTable().fnAddData( [
        '<input type="checkbox" checked="checked" class="usuarioAgregarAC nuevoUsuario" id="guardar_'+giCount2+'">',
        '<span id="nombreUsuario_'+giCount2+'" class="nombreValidar">'+valor.split("|")[0]+'</span>',
        '<input type="TEXT" id="loginUsuario_'+giCount2+'" title="Usuario" class="input input--form loginUsuario obligatorio_AUBO  inputFormulario only_login" maxlength="16" style="width: 220px" >',
        '<select  id="email_'+giCount2+'" title="Email" class="select-section__select select-section__select--form tipoCliente  inputFormulario obligatorio_AUBO" ></select><input type="hidden" value="'+valor.split("|")[2]+'|'+valor.split("|")[3]+'|'+valor.split("|")[4]+'" id="codpercli_'+giCount2+'"> ',
        '<select  id="tipoCliente_'+giCount2+'" title="Tipo Cliente" class="select-section__select select-section__select--form tipoCliente obligatorio_AUBO  inputFormulario" onChange="removerGrupos(this.value);" '+disabled+' ></select>',
        '<select  id="telefonoCelular_'+giCount2+'" title="Telefono Celular" class="select-section__select select-section__select--form tipoCliente  inputFormulario obligatorio_AUBO" ></select>'] );
        BackOfficeCargarNumero(codpercli);


         if ($("#AC_tipoContrato").val()=="FC"){
             cargar_select("tipoCliente_"+giCount2,tipoGruposFC);
         }else{
             cargar_select("tipoCliente_"+giCount2,tipoGruposFA);
         }

         if ($("#AC_tipoContrato").val()=="FC")
             $("#tipoCliente_"+giCount2).val("0000000016");


   /* //if (giCount2>0){
        if ($("#AC_tipoContrato").val()=="FC")
            removerGrupos("0000000016");//Cliente Administrador Firma Conjunta
        else
            removerGrupos("0000000010"); //Cliente Full Acceso
    //}   */

    if (($("#tipoCliente_"+giCount2).val()=="0000000010")||($("#tipoCliente_"+giCount2).val()=="0000000009")||($("#tipoCliente_"+giCount2).val()=="0000000008")){
        $("#AC_usuario_add").addClass("oculto");
    }
    removerGruposAllEC();
    cargar_selectTelefono("telefonoCelular_"+giCount2,telefonosUsuario);
    cargar_selectEmail("email_"+giCount2,correosUsuario);
    giCount2++;
//    verificarSiExiste();
}

function fnClickAddRowUsuarioACAdministrator(valor) {
    var id = valor.split("|")[1];
    var codpercli="-2";
    var tipoGrupoFiltroFC ="";
    var tipoGrupoFiltroFA ="";

    $('#tabla_AC_consultarUsuarioContrato').dataTable().fnAddData( [
        '<input type="checkbox" checked="checked" class="usuarioAgregarAC nuevoUsuario" id="guardar_'+giCount2+'" onClick="validarAdministradoresFC(this.id)">',
        '<span id="nombreUsuario_'+giCount2+'" class="nombreValidar">'+valor.split("|")[0]+'</span>',
        '<input type="TEXT" id="loginUsuario_'+giCount2+'" title="Usuario" class="input input--form loginUsuario obligatorio_AUBO  inputFormulario only_login" maxlength="16" style="width: 220px" >',
        '<select  id="email_'+giCount2+'" title="Email" class="select-section__select select-section__select--form tipoCliente inputFormulario obligatorio_AUBO" ><option value="'+valor.split("|")[5]+'">'+valor.split("|")[5]+'</option></select><input type="hidden" value="-2" id="codpercli_'+giCount2+'"> '+'<input type="hidden" value="'+valor+'" id="datosUC_'+giCount2+'"> ',
        '<select  id="tipoCliente_'+giCount2+'" title="Tipo Cliente" class="select-section__select select-section__select--form tipoCliente obligatorio_AUBO  inputFormulario" onChange="removerGrupos(this.value);" ></select>',
        '<select  id="telefonoCelular_'+giCount2+'" title="Telefono Celular" class="select-section__select select-section__select--form tipoCliente inputFormulario obligatorio_AUBO" ><option value="'+valor.split("|")[7]+"|"+valor.split("|")[3]+'">(+'+valor.split("|")[7]+") "+valor.split("|")[3]+'</option></select>'] );

    BackOfficeCargarNumero("");
    cargar_select("tipoCliente_"+giCount2,tipoGruposFC);
  /*  if (giCount2>0){
        removerGrupos($("#tipoCliente_0").val());
    }
    removerGruposAllEC();  */
//    cargar_selectTelefono("telefonoCelular_"+giCount2,telefonosUsuario);
//    cargar_selectTelefono("email_"+giCount2,correosUsuario);

    $("#tipoCliente_"+giCount2).attr("disabled", "disabled");
    $("#tipoCliente_"+giCount2).val("0000000016");
    giCount2++;
//    verificarSiExiste();
}


function fnClickAddRowUsuarioACAdministratorEdit(valor) {
    var id = valor.split("|")[1];
    var codpercli="-2";
    var tipoGrupoFiltroFC ="";
    var tipoGrupoFiltroFA ="";


    $('#tabla_EC_consultarUsuarioContrato').dataTable().fnAddData( [
        '<input type="checkbox" checked="checked" class="usuarioAgregarEC nuevoUsuario" id="guardar_'+giCount+'" onClick="ECvalidarAdministradoresFC(this.id)">',
        '<span id="nombreUsuario_'+giCount+'" class="nombreValidar">'+valor.split("|")[0]+'</span>',
        '<input type="TEXT" id="loginUsuario_'+giCount+'" title="Usuario" class="input input--form obligatorio_EUBO  inputFormulario" maxlength="16" style="width: 220px" >',
        '<select  id="emailV_'+giCount+'" title="Email" class="select-section__select select-section__select--form tipoCliente obligatorio_EUBO  inputFormulario ObligatorioECEmail" ><option value="'+valor.split("|")[5]+'">'+valor.split("|")[5]+'</option></select><input type="hidden" value="-2" id="codpercli_'+giCount+'"> '+'<input type="hidden" value="'+valor+'" id="datosUC_'+giCount+'"> ',
        '<select  id="tipoCliente_'+giCount+'" title="Tipo Cliente" class="select-section__select select-section__select--form tipoCliente obligatorio_EUBO  inputFormulario" onChange="removerGrupos(this.value);" ></select>',
        '<select  id="telefonoCelularV_'+giCount+'" title="Telefono Celular" class="select-section__select select-section__select--form tipoCliente   inputFormulario ObligatorioECTelefono" ><option value="'+valor.split("|")[7]+"|"+valor.split("|")[3]+'">(+'+valor.split("|")[7]+") "+valor.split("|")[3]+'</option></select>',
        '']);
    BackOfficeCargarNumero("");
    cargar_select("tipoCliente_"+giCount,tipoGrupo);
    if (giCount>0){
        removerGrupos($("#tipoCliente_0").val());
    }
    removerGruposAllEC();
//    cargar_selectTelefono("telefonoCelular_"+giCount2,telefonosUsuario);
//    cargar_selectTelefono("email_"+giCount2,correosUsuario);

    $("#tipoCliente_"+giCount).attr("disabled", "disabled");
    $("#tipoCliente_"+giCount).val("0000000016");
    giCount++;
//    verificarSiExiste();
}




function removerGrupos(valor){
    var rowCount = $("#tabla_AC_consultarUsuarioContrato .nuevoUsuario").length;
    if(valor!="-2"){
       //if (rowCount>1){
            if(valor=="0000000016"){
                $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000009']").remove();
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000008']").remove();
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000010']").remove();
                    $("#AC_usuario_add").removeClass("oculto");
                });
            } else{
                $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000016']").remove();
                    $("#AC_usuario_add").addClass("oculto");
                });
            }
       } else{
           if(valor=="0000000016"){
               $("#AC_usuario_add").removeClass("oculto");
           }else{
               $("#AC_usuario_add").addClass("oculto");
           }
      // }
    }
}



function removerGruposAllEC(){
    $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000011']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000012']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000013']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000014']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000015']").remove();
    });
    $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000011']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000012']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000013']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000014']").remove();
        $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000015']").remove();
    });
}

function removerGruposEC(valor){

    if((valor=="0000000011")||(valor=="0000000012")||(valor=="0000000013")||(valor=="0000000014")||(valor=="0000000015")||(valor=="0000000016")){
                $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000009']").remove();
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000008']").remove();
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000010']").remove();

                });
            } else{
                $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
                    $("#tipoCliente_"+this.id.split("_")[1]+" option[value='0000000016']").remove();

                });
            }
}






function verificarSiExisteCartera_AC(valor){
    cuenta = 0;
    var encontro = "NO";
    $.each($("#tabla_AC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
        if($("#codigoCartera_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });
    if(encontro=="NO"){
        //if (valor.split("|")[6]!="0")
            habilitarCheck="checked";
        //else
           // habilitarCheck="disabled";
        BackOfficeCargarCarteraACJSONData(valor);

    }else{
        alert("Esta cartera ya fue agregada al contrato");
    }
}

function verificarSiExisteCartera(valor){
    cuenta = 0;
    var encontro = "NO";

    $.each($("#tabla_EC_consultarCarterasContrato .nuevaCartera"),function(pos,item){
        if($("#codigoCartera_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });
    $.each($("#tabla_EC_consultarCarterasContrato .carteraVieja"),function(pos,item){
        if($("#codigoCartera_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });

    $.each($("#tabla_EC_consultarCarterasContrato .codigoCartera"),function(pos,item){
        if($("#codigoCartera_"+this.id.split("_")[1]).html()==valor.split("|")[0]){
            encontro ="SI";
        }
    });



    if(encontro=="NO"){
        fnClickAddRowCarteraAC(valor);
    }else{
        alert("Esta cartera ya fue agregada al contrato");
    }
}
//*agregar contrato*//
function BackOfficeAgregarContratoJSONData(carteras,usuarios){
    var url = urlBackOfficeAgregarContratos;
    var param={};
    var contrato={};
    var jsonConsultaContratos=[];

    contrato.carteras = carteras;
    contrato.usuarios = usuarios;
    contrato.descripcion = $("#AC_observaciones").val();
    contrato.tipoContrato = $("#AC_tipoContrato").val();
    contrato.requiereSoporte = $("#AC_requiereSoporte").val();
    jsonConsultaContratos[0]= contrato;

    param.jsonConsultaContratos=JSON.stringify({"contratos":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeAgregarContratoSuccess,null,null);
}

function BackOfficeAgregarContratoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#AC_salvar").attr("disabled", "disabled" );
        alert("El nuevo contrato fue agregado satisfactoriamente\nEl login y la clave serán enviados al numero de telefono registrado cuando se active el contrato.");
        BackOfficeCargarFiltroContratosJSONData();
    }else{
        alert("Ha ocurrido un error durante la creacion del contrato");
    }

}
//*agregar contrato*//
//
// //*editar contrato*//
function BackOfficeEditarContratoJSONData(carterasNuevas,usuariosNuevos,carterasViejas,usuariosViejos){
    var url = urlBackOfficeEditarContratos;
    var param={};
    var contratoActualizado={};
    var contratoViejo={};
    var jsonConsultaContratos=[];

    contratoViejo.carteras = carterasViejas;
    contratoViejo.usuarios = usuariosViejos;
    contratoViejo.descripcion = $("#observacion_CC").val();
    contratoViejo.estatus = $("#EC_estatusContratoFiltro").val();
    contratoViejo.numeroContrato = $("#EC_numContrato").html();
    contratoViejo.requiereSoporte = $("#EC_requiereSoporte").val();
    contratoViejo.motivosRechazo=generarListaRechazos();
    jsonConsultaContratos[0]= contratoViejo;

    contratoActualizado.carteras = carterasNuevas;
    contratoActualizado.usuarios = usuariosNuevos;
    jsonConsultaContratos[1]= contratoActualizado;

    param.jsonConsultaContratos=JSON.stringify({"contratos":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeEditarContratoSuccess,null,null);
}

function BackOfficeEditarContratoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;

    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){


        if ((estatus==0)&&($("#EC_estatusContratoFiltro").val()==1)){
            //Unicamente cuando este Nuevo ys ea activado
            alert("Se ha editado el nuevo contrato exitosamente\nEl login y la clave fueron enviados a los numeros de telefonos registrados");
        }else{
            alert("Se ha editado el nuevo contrato exitosamente");
        }

        $("#searchContrato").click();
    }else{
        alert("Ha ocurrido un error durante la edici\u00f3n del contrato");
    }

}

function generarListaRechazos(){
    var lista_rechazos=[];
    $.each($("#motivosRechazo .motivoRechazo"),function(pos,item){
        if($("#"+this.id).is(':checked')){
            var rechazo={};
            rechazo.value=$("#"+this.id).val();
            lista_rechazos.push(rechazo);
        }
    });
    return lista_rechazos;
}



//*editar contrato*//


function fnFormatDetailsConsultarContratos (valor)
{   var sOut = '<table width="100%">';
    var url = urlBackOfficeGuardarLogContratos;

    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="10%"><span class="label_descp" id="carteraNRo">Cartera N&deg; </span><span class="label_descp">:</span></td>';
        sOut +='<td width="90%"><span id="carteras'+numeroDetalle+'"></span></td>';
        sOut +='</tr>';
        if(valor.split("|")[1]!=" ") {
            sOut +='<tr><td width="10%"><span class="label_descp" id="label_descripcionCC">Descripci&oacute;n </span><span class="label_descp">:</span></td>';
            sOut +='<td width="90%"><span id="descripcionCC">'+valor.split("|")[1]+'</span></td>';
            sOut +='</tr>';
        }
        sOut +='<tr><td colspan="2"><div id="div_tabla_detalleContrato_usuarios'+numeroDetalle+'" class="div_tabla_detalleContrato_usuarios'+numeroDetalle+'"></div></td>';
        sOut +='</tr>';sOut +='<tr><td colspan="2"><div id="div_tabla_detalleContrato_usuarios'+numeroDetalle+'" class="div_tabla_detalleContrato_usuarios'+numeroDetalle+'"></div></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="10%"><span class="label_descp" id="carteraNRo">Cartera N&deg; </span><span class="label_descp">:</span></td>';
        sOut +='<td width="90%"><span id="carteras'+numeroDetalle+'"></span></td>';
        sOut +='</tr>';
        if(valor.split("|")[1]!=" ") {
            sOut +='<tr><td width="10%"><span class="label_descp" id="label_descripcionCC">Descripci&oacute;n </span><span class="label_descp">:</span></td>';
            sOut +='<td width="90%"><span id="descripcionCC">'+valor.split("|")[1]+'</span></td>';
            sOut +='</tr>';
        }
        sOut +='<tr><td colspan="2"><div id="div_tabla_detalleContrato_usuarios'+numeroDetalle+'" class="div_tabla_detalleContrato_usuarios'+numeroDetalle+'"></div></td>';
        sOut +='</tr>';sOut +='<tr><td colspan="2"><div id="div_tabla_detalleContrato_usuarios'+numeroDetalle+'" class="div_tabla_detalleContrato_usuarios'+numeroDetalle+'"></div></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }

        var param={};
        var jsonEditarContrato=[];
        var datosContrato={};

        param.codOpc="DT";
        datosContrato.num_contrato = valor.split("|")[0];
        jsonEditarContrato[0]= datosContrato;
        param.jsonEditarContrato=JSON.stringify({"consultaEditarContratos":jsonEditarContrato});
        sendServiceJSON(url,param,'',null,null);

    return sOut;
}




function fnFormatDetailsConsultarCarteras (valor)
{   var sOut = '<table width="100%">';

    sOut +='<table width="100%"><tr><td width="10%"><span class="label_descp" id="label_cedulaRIF">Asesor </span><span class="label_descp">:</span></td>';
    sOut +='<td ><span id="asesor">'+valor.split("||")[0]+'</span></td>';
    sOut +='<td width="15%"><span class="label_descp" id="label_fechaCreacionUD">Ejecutivo</span><span class="label_descp">:</span></td>';
    sOut +='<td ><span id="ejecutivo">'+valor.split("||")[1]+'</span></td>';
    sOut +='</tr>';
    var clientes=Trim(valor.split("||")[2]);
    var cantProductos=Trim(valor.split("||")[3]);
    if (clientes.length>2){
        sOut +='<tr><td><span class="label_descp" id="clientessecundarios">Clientes Secundarios </span><span class="label_descp"></span></td>';
        sOut +='<td ><span id="clientesS">'+(clientes.substring(0,(clientes).length-1))+'</span></td>';
        sOut +='<td></td>';
        sOut +='<td ></td>';
        sOut +='</tr>';
    }
    if (cantProductos=="0"){
        sOut +='<tr><td><span class="label_descp_rojo" id="observaciones">Observaciones</span><span class="label_descp_rojo"></span></td>';
        sOut +='<td ><span id="observaciones">La cartera no tiene productos</span></td>';
        sOut +='<td></td>';
        sOut +='<td ></td>';
        sOut +='</tr>';
    }
    sOut +='</table>';

    return sOut;
}





function BackOfficeDetalleContratosJSONData(valor,status){
    var url = urlBackOfficeConsultarDetallesContratos;
    var param={};
    var jsonConsultaContratos=[];

    jsonConsultaContratos[0]= valor;
    jsonConsultaContratos[1]= status;

    param.jsonConsultaContratos=JSON.stringify({"parametros":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeDetalleContratosSuccess,null,null);
}

function BackOfficeDetalleContratosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    //$("#marco_trabajo").css("height","1000px");
    carteras = result.carterasContrato;
    var tipoEstatus = result.tipoEstatus;
    var tipoGrupos = result.tipoGrupo;

    cargar_select("grupoEU_CUC",tipoGrupos);
    cargar_select("estatusEU_CUC",tipoEstatus);

    var tabla="tabla_detalleContrato_usuarios"+numeroDetalle;

    crearTabla("div_tabla_detalleContrato_usuarios"+numeroDetalle,tabla,columnas,datos);

    oTable2 = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "" },
            { "sClass": ""},
            {  "sClass": "center" },
            {  "sClass": "center" },
            {  "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": "",
            "sInfoEmpty": "",
            "sSearch": "Buscar:",
            "sInfo": "Mostrando _START_ de _END_ de _TOTAL_ entidades"
        }
    });


    $("#carteras"+numeroDetalle).html(carteras);
}


function mostrarRechazos(){
    if ($("#EC_estatusContratoFiltro").val()==4){

        $("#TitlemotivosRechazo").attr("style","display: ");
        $("#motivosRechazo").attr("style","display: ");
    }else{
        $("#TitlemotivosRechazo").attr("style","display: none");
        $("#motivosRechazo").attr("style","display: none");
    }



};

function fnFormatDetailsResultadoBusquedaUsuarioContrato (valor)
{   var sOut = '<table width="100%">';
    var i = 0;
    var splitResult = valor.split('*');
    while(i<splitResult.length){
        if(idioma=="_us_en"){
            sOut +='<tr><td width="80px"><span class="label_descp" id="carteraNro">Cartera N&deg; </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_cartera_TD_C">'+valor.split('*')[i].split('|')[0]+'</span></td>';
            sOut +='<td><span class="label_descp" id="tipoCliente">Tipo Cliente </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_cartera_TD_C">'+valor.split('*')[i].split('|')[1]+'</span></td>';
            sOut +='<td"><span class="label_descp" id="modalidad">Modalidad </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_Monto_TD_C">'+valor.split('*')[i].split('|')[2]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="80px"><span class="label_descp" id="estatus">Estatus </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_Monto_TD_C">'+valor.split('*')[i].split('|')[3]+'</span></td>';
            sOut +='<td><span class="label_descp" id="finalcialAdvisor">Finalcial Advisor </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_tasa_TD_C">'+valor.split('*')[i].split('|')[4]+'</span></td>';
            sOut +='<td><span class="label_descp" id="finalcialAdvisor">Account Executive </span><span class="label_descp">:</span> </td>';
            sOut +='<td><span id="label_tasa_TD_C">'+valor.split('*')[i].split('|')[5]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td colspan="6"><HR width="100%" align="center"></td></td></tr>';
        }else{
            sOut +='<tr><td width="80px"><span class="label_descp" id="carteraNro">Cartera N&deg; </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_cartera_TD_C">'+valor.split('*')[i].split('|')[0]+'</span></td>';
            sOut +='<td><span class="label_descp" id="tipoCliente">Tipo Cliente </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_cartera_TD_C">'+valor.split('*')[i].split('|')[1]+'</span></td>';
            sOut +='<td><span class="label_descp" id="modalidad">Modalidad </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_Monto_TD_C">'+valor.split('*')[i].split('|')[2]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="80px"><span class="label_descp" id="estatus">Estatus </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_Monto_TD_C">'+valor.split('*')[i].split('|')[3]+'</span></td>';
            sOut +='<td><span class="label_descp" id="finalcialAdvisor">Asesor Financiero </span><span class="label_descp">:</span></td>';
            sOut +='<td><span id="label_tasa_TD_C">'+valor.split('*')[i].split('|')[4]+'</span></td>';
            sOut +='<td><span class="label_descp" id="finalcialAdvisor">Ejecutivo de Cuentas </span><span class="label_descp">:</span> </td>';
            sOut +='<td><span id="label_tasa_TD_C">'+valor.split('*')[i].split('|')[5]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td colspan="6"><HR width="100%" align="center"></td></td></tr>';
        }
        i++;
    }
    sOut +='</table>';
    return sOut;
}

function fnFormatDetailsResultadoBusquedaUsuarioDetalle (valor)
{   var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="10%"><span class="label_descp" id="label_cedulaRIF">C.I./R.I.F&deg; </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="cedulaRIF">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="label_fechaCreacionUD">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="fechaCreacionUD">'+valor.split("|")[1]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="label_fechaCreacionUD">Telefono Celular </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="fechaCreacionUD">'+valor.split("|")[4]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td><span class="label_descp" id="label_direccionUD">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="direccionUD">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td><span class="label_descp" id="label_emailUD">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="emailUD">'+valor.split("|")[3]+'</span></td>';
        sOut +='<td></td>';
        sOut +='<td></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="10%"><span class="label_descp" id="label_cedulaRIF">C.I./R.I.F&deg; </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="cedulaRIF">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="label_fechaCreacionUD">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="fechaCreacionUD">'+valor.split("|")[1]+'</span></td>';
        sOut +='<td width="15%"><span class="label_descp" id="label_fechaCreacionUD">Telefono Celular </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="fechaCreacionUD">'+valor.split("|")[4]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td><span class="label_descp" id="label_direccionUD">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="direccionUD">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td><span class="label_descp" id="label_emailUD">Correo </span><span class="label_descp">:</span></td>';
        sOut +='<td ><span id="emailUD">'+valor.split("|")[3]+'</span></td>';
        sOut +='<td></td>';
        sOut +='<td></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }
    return sOut;
}

function fnFormatDetailsResultadoBusquedaCarteraContrato (valor)
{   var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="tipoCliente">Tipo Cliente </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_cartera_TD_C">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="modalidad">Modalidad </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Monto_TD_C">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="finalcialAdvisor">Finalcial Advisor </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_tasa_TD_C">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="finalcialAdvisor">Account Executive </span><span class="label_descp">:</span> </td>';
        sOut +='<td width="25%"><span id="label_tasa_TD_C">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="tipoCliente">Tipo Cliente </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_cartera_TD_C">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="modalidad">Modalidad </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Monto_TD_C">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="finalcialAdvisor">Asesor Financiero </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_tasa_TD_C">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="finalcialAdvisor">Ejecutivo de Cuentas </span><span class="label_descp">:</span> </td>';
        sOut +='<td width="25%"><span id="label_tasa_TD_C">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }
    return sOut;
}

function BackOfficeContratosJSONData(){
    var url = urlBackOfficeConsultarContratos;
    var param={};
    var jsonConsultaContratos=[];
    var datosConsContBO={};

    $("#paginacion_tabla_consulta_contratosBO").empty();
    $("#div_tabla_consultaContratos").fadeIn("fast");
    $("#div_tabla_AgregarContratos").fadeOut("fast");
    $("#div_tabla_EditarContratos").fadeOut("fast");
    $("#div_tabla_consulta_contratosBO").empty();
    $("#div_carga_espera").removeClass("oculto");

    datosConsContBO.strInicial="NO OK";
    if($("#contratoFiltroCC").get(0).value!=null)
        datosConsContBO.strTxtNumeroContrato=Trim($("#contratoFiltroCC").get(0).value);
    else
        datosConsContBO.strTxtNumeroContrato="";

    if($("#usuarioFiltroCC").get(0).value!=null)
        datosConsContBO.strTxtUsuario=Trim($("#usuarioFiltroCC").get(0).value);
    else
        datosConsContBO.strTxtUsuario="";

    if($("#estatusFiltroCC").get(0).value!="-2")
        datosConsContBO.strTipoStatus=$("#estatusFiltroCC").get(0).value;
    else
        datosConsContBO.strTipoStatus="Default";

    if($("#clienteCC").get(0).value!=null)
        datosConsContBO.strTxtNombreCliente=Trim($("#clienteCC").get(0).value);
    else
        datosConsContBO.strTxtNombreCliente="";

    if($("#CIRIFCC").get(0).value!=null)
        datosConsContBO.strTxtCIRIFCliente=$("#CIRIFCC").get(0).value;
    else
        datosConsContBO.strTxtCIRIFCliente="";

    if($("#fechaDesdeFiltroCC").get(0).value!=null)
        datosConsContBO.strTxtDesde=$("#fechaDesdeFiltroCC").get(0).value;
    else
        datosConsContBO.strTxtDesde="";

    if($("#fechaHastaFiltroCC").get(0).value!=null)
        datosConsContBO.strTxtHasta=$("#fechaHastaFiltroCC").get(0).value;
    else
        datosConsContBO.strTxtHasta="";

    if($("#creadoFiltro").get(0).value!="-2")
        datosConsContBO.strCmbCreadoPor=$("#creadoFiltro").get(0).value;
    else
        datosConsContBO.strCmbCreadoPor="Default";

    if($("#carteraFiltro").get(0).value!=null)
        datosConsContBO.txtNumeroCartera=Trim($("#carteraFiltro").get(0).value);
    else
        datosConsContBO.txtNumeroCartera="";


    if($("#tipoContratoFiltro").get(0).value!="-2")
        datosConsContBO.strTipoContrato=$("#tipoContratoFiltro").get(0).value;
    else
        datosConsContBO.strTipoContrato="Default";

    jsonConsultaContratos[0]= datosConsContBO;

    param.jsonConsultaContratos=JSON.stringify({"consultaContratoss":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeContratosSuccess,null,null);
}


function BackOfficeContratosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;

    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center"},
        {  "sClass": "center" },
        {  "sClass": "center" }
    ];
    var tamano_pag=20;
    var tabla;
    var tabla_id="tabla_consulta_contratosBO";
    var id_contenedor="div_tabla_consulta_contratosBO";
    //$("#marco_trabajo").css("height","1000px");
    tipoEstatusContrato = result.tipoEstatusContrato;
    usuarioCreador = result.usuarioCreador;

    if ( cargar_pagina_new ){
        $("#div_tabla_EditarContratos").attr("style","display: none");
        $("#div_tabla_AgregarContratos").attr("style","display: none");
        cargar_select("estatusFiltroCC",tipoEstatusContrato);
        cargar_select("EC_estatusContratoFiltro",tipoEstatusContrato);
        cargar_select("creadoFiltro",usuarioCreador);
    }else{
        // tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);
        crearTablaV3(id_contenedor,tabla_id,columnas,datos);



/*        tabla.dataTable({
            "iDisplayLength": tamano_pag,
            "bJQueryUI": true,
            "bFilter": false,
            "bSort": false,
            "bPaginate": false,
            "bScrollCollapse": true,
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": "",
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
        oTable=tabla;*/
        oTable = $("#"+tabla_id).dataTable({
            "iDisplayLength": tamano_pag,
            "bJQueryUI": false,
            "bFilter": false,
            "bSort": false,
            "bPaginate": true,
            "sPaginationType": "full_numbers",
            "bScrollCollapse": true,
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": "",
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });

        if(datos!=null){
/*            if(datos.length!=0){
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
                        guardarlog(datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag);
                        paginacion(id_contenedor,tabla_id,columnas,datos,(parseInt(page-1)*parseInt(tamano_pag)),tamano_pag,p_sclass);
                    }
                });
            }*/
        }
    }

    $("#div_carga_espera").addClass("oculto");
}


function guardarlog(contenidoTabla_info,reg_inicio,tam_pagina){
    var url = urlBackOfficeGuardarLogContratos;
    for(var cantidad=reg_inicio;cantidad<(reg_inicio+tam_pagina);cantidad++){
        if(cantidad<contenidoTabla_info.length) {
            var data=contenidoTabla_info[cantidad];
//            $.each(data.data_culumn,function(d,item){
//                var data_info=item;
//                if(!isNaN(data_info.data_columna.substring(data_info.data_columna.lastIndexOf("id='")+4, data_info.data_columna.indexOf("|"))) && data_info.data_columna.substring(data_info.data_columna.lastIndexOf("id='")+4, data_info.data_columna.indexOf("|")).length>=4){


            var param={};
                    var jsonEditarContrato=[];
                    var datosContrato={};

                    //datosContrato.num_contrato = data_info.data_columna.substring(data_info.data_columna.lastIndexOf("id='")+4, data_info.data_columna.indexOf("|"));
                    datosContrato.num_contrato = data.data_culumn[1].data_columna.split("<b>")[1];
                    jsonEditarContrato[0]= datosContrato;
                    param.codOpc="GN";
                    param.jsonEditarContrato=JSON.stringify({"consultaEditarContratos":jsonEditarContrato});
                    sendServiceJSON(url,param,'',null,null);
                //}
//            });
        }else{
            cantidad=  (reg_inicio+tam_pagina);
        }
    }
}


function cargarMotivos(lista,listaMotivos){


    $('#motivosRechazo').empty();

    $.each(lista,function(s,item){

        var enc=false;
        $.each(listaMotivos,function(l,itemMotivo){

             if (item.value==itemMotivo.value)
                enc=true;

        });
        if (enc){
            $('#motivosRechazo').append('<input class="motivoRechazo" type="checkbox" checked id="motivoRechazo_'+item.value+'" value="'+item.value+'" /> ' + item.label+ '<br />');
        }else{
            $('#motivosRechazo').append('<input class="motivoRechazo" type="checkbox" id="motivoRechazo_'+item.value+'" value="'+item.value+'" /> ' + item.label+ '<br />');
        }
    });

}



function BackOfficeCargarFiltroContratosJSONData(){
    var url = urlBackOfficeCargarFiltroContratos;
    var param={};
    var jsonConsultaContratos=[];
    var datosConsContBO={};
    $("#div_tabla_consultaContratos").fadeIn("fast");
    $("#div_tabla_AgregarContratos").fadeOut("fast");
    $("#div_tabla_EditarContratos").fadeOut("fast");
    $("#div_tabla_consulta_contratosBO").empty();
    $("#div_carga_espera").removeClass("oculto");
    $("#paginacion_tabla_consulta_contratosBO").empty();
    datosConsContBO.strInicial="OK";
    if($("#estatusFiltroCC").get(0).value!="-2")
        datosConsContBO.strTipoStatus=$("#estatusFiltroCC").get(0).value;
    else
        datosConsContBO.strTipoStatus="Default";

    if($("#creadoFiltro").get(0).value!="-2")
        datosConsContBO.strCmbCreadoPor=$("#creadoFiltro").get(0).value;
    else
        datosConsContBO.strCmbCreadoPor="Default";


    jsonConsultaContratos[0]= datosConsContBO;

    param.jsonConsultaContratos=JSON.stringify({"consultaContratoss":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeCargarFiltroContratosJSONDataSuccess,null,null);

}


function BackOfficeCargarFiltroContratosJSONDataSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    //$("#marco_trabajo").css("height","1000px");
    tipoEstatusContrato = result.tipoEstatusContrato;
    usuarioCreador = result.usuarioCreador;
    tipoContrato=result.tipoContrato;
    tipoGrupos = result.tipoGrupo;
    tipoGruposFA = result.tipoGrupoFA;
    tipoGruposFC = result.tipoGrupoFC;
    tipoCiRif = result.tipoCiRif;
    requiereSoporte =  result.requiereSoporte;

   AU_contratoGrupo = result.tipoGrupo;
   AU_contratoCirif = result.tipoCiRif;



    if (cargar_pagina_new ){
        $("#div_tabla_EditarContratos").attr("style","display: none");
        $("#div_tabla_AgregarContratos").attr("style","display: none");
        cargar_select("estatusFiltroCC",tipoEstatusContrato);
        cargar_select("EC_estatusContratoFiltro",tipoEstatusContrato);
        cargar_select("creadoFiltro",usuarioCreador);
        cargar_selectPersonal("tipoContratoFiltro", tipoContrato,"Seleccione","-2");
        cargar_selectPersonal("AC_tipoContrato", tipoContrato,"Seleccione","-2");
        cargar_selectPersonal("EC_tipoContrato", tipoContrato,"Seleccione","-2");
        cargar_selectId("AC_requiereSoporte",requiereSoporte,"Seleccione","-2","S");
        cargar_selectPersonal("EC_requiereSoporte", requiereSoporte,"Seleccione","-2");
        $("#searchContrato").click();
    }else
        $("#searchContrato").click();

    idioma = "_ve_es";

    cargar_select("AU_contratoGrupo",AU_contratoGrupo);
    cargar_select("EU_contratoGrupo",AU_contratoGrupo);

    cargar_select("AU_contratoCirif",AU_contratoCirif);
    cargar_select("EU_contratoCirif",AU_contratoCirif);

    $("#AU_contratoGrupo").attr("disabled", "disabled");
    $("#EU_contratoGrupo").attr("disabled", "disabled");
}


























//inicio busqueda de usuarios para agregar al contrato

function BackOfficeBusquedaUsuariosJSONData(){
    $("#div_carga_espera").removeClass("oculto");

    var url = urlBackOfficeBusquedaUsuariosContratos;
    var param={};
    var jsonConsultaContratos=[];

    var exacto="0";

    if($("#carteraExacto").attr("checked"))
      exacto = "1";
    if ($("#carteraAUC").val()==null)
        $("#carteraAUC").val("");
    if ($("#CIRIFBUC").val()==null)
        $("#CIRIFBUC").val("");
    if ($("#ClienteBUC").val()==null)
        $("#ClienteBUC").val("");
    if ($("#financialAdvisorBUC").val()==null)
        $("#financialAdvisorBUC").val("");
    if ($("#accountExecutiveBUC").val()==null)
        $("#accountExecutiveBUC").val("");

    jsonConsultaContratos[0] = $("#carteraAUC").val();
    jsonConsultaContratos[1] = exacto;
    jsonConsultaContratos[2] = $("#CIRIFBUC").val();
    jsonConsultaContratos[3] = $("#ClienteBUC").val();
    jsonConsultaContratos[4] = $("#financialAdvisorBUC").val();
    jsonConsultaContratos[5] = $("#accountExecutiveBUC").val();
    jsonConsultaContratos[6] = "agregar";

    param.jsonConsultaContratos=JSON.stringify({"parametros":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeBusquedaUsuariosSuccess,null,null);
}


function BackOfficeBusquedaUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupo = result.tipoGrupo;

    var p_sclass=[];
    var tamano_pag=15;
    var id_contenedor="div_tabla_resultadoBusquedaUsuarioContrato";
    var tabla_id="tabla_resultadoBusquedaUsuarioContrato";
    div =id_contenedor;
    // var tabla=crearTabla(id_contenedor,tabla_id,columnas,datos);
    crearTablaV3(id_contenedor,tabla_id,columnas,datos);

/*    oTable = $('#tabla_resultadoBusquedaUsuarioContrato').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    oTable = $('#tabla_resultadoBusquedaUsuarioContrato').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    /*if(datos.length!=0){
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

    }      */
    $("#div_carga_espera").addClass("oculto");
}

//fin busqueda de usuarios para agregar al contrato

//inicio busqueda de carteras para agregar al contrato

function BackOfficeBusquedaCarterasJSONData(){
    $("#div_carga_espera").removeClass("oculto");

    var url = urlBackOfficeBusquedaCarterasContratos;
    var param={};
    var jsonConsultaContratos=[];



    var exacto="0";

    if($("#carteraExactoC").attr("checked"))
        exacto = "1";
    if ($("#carteraECC").val()==null)
        $("#carteraECC").val("");
    if ($("#CIRIFBCC").val()==null)
        $("#CIRIFBCC").val("");
    if ($("#ClienteBCC").val()==null)
        $("#ClienteBCC").val("");
    if ($("#financialAdvisorBCC").val()==null)
        $("#financialAdvisorBCC").val("");
    if ($("#accountExecutiveBCC").val()==null)
        $("#accountExecutiveBCC").val("");

    jsonConsultaContratos[0] = $("#carteraECC").val();
    jsonConsultaContratos[1] = exacto;
    jsonConsultaContratos[2] = $("#CIRIFBCC").val();
    jsonConsultaContratos[3] = $("#ClienteBCC").val();
    jsonConsultaContratos[4] = $("#financialAdvisorBCC").val();
    jsonConsultaContratos[5] = $("#accountExecutiveBCC").val();
    jsonConsultaContratos[6] = "agregar";

    param.jsonConsultaContratos=JSON.stringify({"parametros":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeBusquedaCarterasSuccess,null,null);
}


function BackOfficeBusquedaCarterasSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center"},
        {  "sClass": "left" },
        {  "sClass": "left" },
        { "sClass": "center" }
    ];
    var tamano_pag=10;
    var tabla_id="tabla_resultadoBusquedaCarteraContrato";
    var id_contenedor="div_tabla_resultadoBusquedaCarteraContrato";
    // var tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);
    crearTablaV3(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);

/*    oTable = tabla.dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "bScrollCollapse": true,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    oTable = $("#"+tabla_id).dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": false,
        "bFilter": false,
        "bSort": false,
        "bPaginate": true,
        "sPaginationType": "full_numbers",
        "bScrollCollapse": true,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    if(datos.length!=0){
/*        $("#paginacion_"+tabla_id).paginate({
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
        });*/
    }

    $("#div_carga_espera").addClass("oculto");
}

//fin busqueda de Carteras para agregar al contrato

//inicio busqueda editar contratos usuarios
function BackOfficeBusquedaUsuariosECJSONData(){
    var url = urlBackOfficeBusquedaUsuariosContratos;
    var param={};
    var jsonConsultaContratos=[];



    var exacto="0";

    if($("#carteraExacto_EC").attr("checked"))
        exacto = "1";
    if ($("#carteraEUC_EC").val()==null)
        $("#carteraEUC_EC").val("");
    if ($("#CIRIFBUC_EC").val()==null)
        $("#CIRIFBUC_EC").val("");
    if ($("#ClienteBUC_EC").val()==null)
        $("#ClienteBUC_EC").val("");
    if ($("#financialAdvisorBUC_EC").val()==null)
        $("#financialAdvisorBUC_EC").val("");
    if ($("#accountExecutiveBUC_EC").val()==null)
        $("#accountExecutiveBUC_EC").val("");

    jsonConsultaContratos[0] = $("#carteraEUC_EC").val();
    jsonConsultaContratos[1] = exacto;
    jsonConsultaContratos[2] = $("#CIRIFBUC_EC").val();
    jsonConsultaContratos[3] = $("#ClienteBUC_EC").val();
    jsonConsultaContratos[4] = $("#financialAdvisorBUC_EC").val();
    jsonConsultaContratos[5] = $("#accountExecutiveBUC_EC").val();
    jsonConsultaContratos[6] = "editar";

    param.jsonConsultaContratos=JSON.stringify({"parametros":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeBusquedaUsuariosECSuccess,null,null);
}


function BackOfficeBusquedaUsuariosECSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupo = result.tipoGrupo;

    var p_sclass=[];
    var tamano_pag=15;
    var id_contenedor="div_tabla_resultadoBusquedaUsuarioContratoEC";
    var tabla_id="tabla_resultadoBusquedaUsuarioContratoEC";
    div =id_contenedor;
    var tabla=crearTabla(id_contenedor,tabla_id,columnas,datos);

    oTable = $('#tabla_resultadoBusquedaUsuarioContratoEC').dataTable({
        "iDisplayLength": 15,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });


    $("#div_carga_usuario_EBC").addClass("oculto");
}
//fin busqueda usuarios para editar contratos

//inicio buscar carteras editar contrato
function BackOfficeBusquedaCarterasECJSONData(){
    var url = urlBackOfficeBusquedaCarterasContratos;
    var param={};
    var jsonConsultaContratos=[];

    var exacto="0";

    if($("#carteraExactoC_EC").attr("checked"))
        exacto = "1";
    if ($("#carteraECC_EC").val()==null)
        $("#carteraECC_EC").val("");
    if ($("#CIRIFBCC_EC").val()==null)
        $("#CIRIFBCC_EC").val("");
    if ($("#ClienteBCC_EC").val()==null)
        $("#ClienteBCC_EC").val("");
    if ($("#financialAdvisorBCC_EC").val()==null)
        $("#financialAdvisorBCC_EC").val("");
    if ($("#accountExecutiveBCC_EC").val()==null)
        $("#accountExecutiveBCC_EC").val("");

    jsonConsultaContratos[0] = $("#carteraECC_EC").val();
    jsonConsultaContratos[1] = exacto;
    jsonConsultaContratos[2] = $("#CIRIFBCC_EC").val();
    jsonConsultaContratos[3] = $("#ClienteBCC_EC").val();
    jsonConsultaContratos[4] = $("#financialAdvisorBCC_EC").val();
    jsonConsultaContratos[5] = $("#accountExecutiveBCC_EC").val();
    jsonConsultaContratos[6] = "editar";

    param.jsonConsultaContratos=JSON.stringify({"parametros":jsonConsultaContratos});

    sendServiceJSON(url,param,BackOfficeBusquedaCarterasECSuccess,null,null);
}



function BackOfficeBusquedaCarterasECSuccess(originalRequest){
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    //                   this is the json return data
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center"},
        {  "sClass": "left" },
        {  "sClass": "left" },
        { "sClass": "center" }
    ];
    var tamano_pag=15;
    var id_contenedor="div_tabla_resultadoBusquedaCarteraContrato_EC";
    var tabla_id="tabla_resultadoBusquedaCarteraContrato_EC";
    div =id_contenedor;
    var tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);

    oTable = tabla.dataTable({
        "iDisplayLength": tamano_pag,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "sScrollY": "300px",
        "bPaginate": false,
        "bScrollCollapse": true,
        "oLanguage": {
            "sZeroRecords": "",
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

    $("#div_carga_carteras_EBC").addClass("oculto");
}
//fin

function seleccionarContratoOpcion(id){

    estatus = id.split("|")[1];
    $("#estatusContratoAnterior").val(id.split("|")[1]);
    contrato = id.split("|")[0];
    administradoresFC=0;

    if (id.split("|")[2]!=null)
        observacion = id.split("|")[2];


    if (id.split("|")[3]!=null)
        habilitarBtn = id.split("|")[3];

    if (id.split("|")[4]!=null)
        tipoContratoGB = id.split("|")[4];

    if (id.split("|")[5]!=null)
        requiereSoporteEc = id.split("|")[5];


    cargar_select_EC(tipoEstatusContrato);


    BackOfficeCargarEditarContratoJSONData(id.split("|")[0]);

}

function BackOfficeCargarEditarContratoJSONData(id){
    var url = urlBackOfficeCargarEditarContratosContratos;
    var param={};
    var jsonEditarContrato=[];
    var datosContrato={};

    $("#div_carga_espera").removeClass("oculto");
    datosContrato.num_contrato = id;

    jsonEditarContrato[0]= datosContrato;

    param.jsonEditarContrato=JSON.stringify({"consultaEditarContratos":jsonEditarContrato});

    sendServiceJSON(url,param,BackOfficeCargarEditarContratosSuccess,null,null);
}


function BackOfficeCargarEditarContratosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var columnascarteras = result.contenidoTabla_culumnasTestaux;
    var datoscarteras = result.contenidoTabla_infoTestaux;
    var motivos=result.motivosRechazo;
    var motivosContrato=result.motivosRechazoContrato;

    tipoGrupo = result.tipoGrupo;
    nCount2=datoscarteras.length;
    giCount=datos.length;
    $("#EC_estatusContratoFiltro").val(estatus);
    $("#EC_numContrato").html(contrato);
    $("#observacion_CC").val(observacion);
    $("#EC_tipoContrato").val(tipoContratoGB);
    $("#EC_requiereSoporte").val(requiereSoporteEc);


    var tabla="tabla_EC_consultarUsuarioContrato";

    // crearTabla("div_tabla_EC_consultarUsuarioContrato",tabla,columnas,datos);
    crearTablaV3("div_tabla_EC_consultarUsuarioContrato",tabla,columnas,datos);

    var tabla2="tabla_EC_consultarCarterasContrato";

    // crearTabla("div_tabla_EC_consultarCarterasContrato",tabla2,columnascarteras,datoscarteras);
    crearTablaV3("div_tabla_EC_consultarCarterasContrato",tabla2,columnascarteras,datoscarteras);

    $("#EC_usuario_add").removeClass("oculto");



    if (estatus==4){
        $("#TitlemotivosRechazo").attr("style","display: ");
        $("#motivosRechazo").attr("style","display: ");
    }else{
        $("#TitlemotivosRechazo").attr("style","display: none");
        $("#motivosRechazo").attr("style","display: none");
    }

        cargarMotivos(motivos,motivosContrato);

/*    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "" },
            { "sClass": ""},
            {  "sClass": "" },
            {  "sClass": "center" } ,
            {  "sClass": "center" } ,
            {  "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "" },
            { "sClass": ""},
            {  "sClass": "" },
            {  "sClass": "center" } ,
            {  "sClass": "center" } ,
            {  "sClass": "center" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

/*    var oTable2 = $('#'+tabla2).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "center" },
            {  "sClass": "" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    var oTable2 = $('#'+tabla2).dataTable({
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "center" },
            {  "sClass": "" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

    //PRIVILEGIOS SOBRE LA ACCION DE EDITAR CONTRATO
    if((privilegio_cambioEstatus_contrato)&&(habilitarBtn=="1")){
        $("#EC_estatusContratoFiltro").attr("disabled",false);
        $("#EC_salvar").removeClass("oculto");
    }else{
        $("#EC_estatusContratoFiltro").attr("disabled",true);
        $("#EC_salvar").addClass("oculto");
        if(privilegio_editar_contrato)
                $("#EC_salvar").removeClass("oculto");
    }

    if(privilegio_editar_contrato){
        $(".0000000004_2").removeClass("oculto");
        $("#EC_salvar").removeClass("oculto");
    }else{
       $(".0000000004_2").addClass("oculto");
        $("#EC_salvar").addClass("oculto");
        if((privilegio_cambioEstatus_contrato)&&(habilitarBtn=="1"))
            $("#EC_salvar").removeClass("oculto");
        }


    //PRIVILEGIOS SOBRE LA ACCION DE EDITAR CONTRATO

    $("#div_tabla_EditarContratos").attr("style","display: ");
    $("#div_tabla_consultaContratos").attr("style","display: none");


    $.each($("#tabla_EC_consultarUsuarioContrato .usuarioViejo"),function(pos,item){

       var codpercli=this.value.split("|")[1];
       var usuario=Trim($("#loginUsuarioV_"+this.id.split("_")[1]).html());

        //telefonosUsuario.length=0;
        BackOfficeCargarNumeroLogin(codpercli,usuario);
        cargar_selectTelefono("telefonoCelularV_"+this.id.split("_")[1],telefonosUsuario);
        cargar_selectEmail("emailV_"+this.id.split("_")[1],correosUsuario);
       $("#telefonoCelularV_"+this.id.split("_")[1]).val($("#telefonoCelularSeleccionadoV_"+this.id.split("_")[1]).val());

       $("#emailV_"+this.id.split("_")[1]).val($("#emailSeleccionadoV_"+this.id.split("_")[1]).val());

        if(privilegio_cambioEstatus_contrato){
            if(privilegio_editar_contrato){
                $("#observacion_CC").attr("disabled",false);
                $("#telefonoCelularV_"+this.id.split("_")[1]).attr("disabled",false);
                $("#emailV_"+this.id.split("_")[1]).attr("disabled",false);
            }else{
                $("#observacion_CC").attr("disabled",true);
                $("#telefonoCelularV_"+this.id.split("_")[1]).attr("disabled",true);
                $("#emailV_"+this.id.split("_")[1]).attr("disabled",true);
            }
        }
        $('#telefonoCelularV_'+this.id.split("_")[1]+' > option[value="'+$("#telefonoCelularSeleccionadoV_"+this.id.split("_")[1]).val()+'"]').attr('selected', 'selected');

        if( ($("#tipoClienteCodigoV_"+this.id.split("_")[1]).val()=="0000000010")||( $("#tipoClienteCodigoV_"+this.id.split("_")[1]).val()=="0000000008")||( $("#tipoClienteCodigoV_"+this.id.split("_")[1]).val()=="0000000009")){
            $("#EC_usuario_add").addClass("oculto");
        }

    });

    $("#div_carga_espera").addClass("oculto");
}



function BackOfficeCargarCarteraJSONData(cartera){
    var url = urlBackOfficeCargarCartera;
    var param={};
    var jsonEditarContrato=[];
    var datosContrato={};

    datosContrato.num_cartera = cartera;

    datosContrato.num_cartera = cartera.split("|")[0];

    if ($("#EC_tipoContrato").val()=="FI"){
        datosContrato.tipoContrato="FC";
    }else{
        datosContrato.tipoContrato="FI";
    }

    jsonEditarContrato[0]= datosContrato;

    param.jsonEditarContrato=JSON.stringify({"consultaEditarContratos":jsonEditarContrato});

    sendServiceJSON(url,param,BackOfficeCargarCarteraSuccess,null,null);
}

function BackOfficeCargarCarteraSuccess(originalRequest){
    //
    detalleCartera="";
    var count=nCount2;
    var result = originalRequest;
    detalleCartera=result.numCartera;
    var clientePrincipal=detalleCartera.split("||")[1];
    var detalle=detalleCartera.split("--")[1];
    var respuesta=result.respuesta;

    var checked="";
   // if ((detalleCartera.split("||")[4]).split("--")[0]!="0")
        checked="checked";


  //  if (respuesta=="OK"){
        $('#tabla_EC_consultarCarterasContrato').dataTable().fnAddData( [
            '<img style="cursor:pointer" src="resources/images/comun/nolines_plus.gif" class="detalleBusquedaCartera" width="9" height="9" id="'+detalle+'" type="button">',
            '<input type="checkbox" '+habilitarCheck+' class="carteraAgregarEC" id="guardarCartera_'+count+'">',
            '<span id="codigoCarteraV_'+count+'" class="nuevaCartera">'+detalleCartera.split("||")[0]+'</span>',
            '<span id="modalidad_'+count+'" class="modalidad">'+detalleCartera.split("||")[2]+'</span>',
            '<span id="estatus_'+count+'" class="estatus">'+detalleCartera.split("||")[3]+'</span>',
            '<span id="clientePrincipal_'+count+'" class="clientePrincipal">'+detalleCartera.split("||")[1]+'</span>'] );

        nCount2++;
//    }else{
//        $("#mensaje_error").empty();
//        $("#mensaje_error").html(respuesta);
//        $("#div_mensajes_error").fadeIn("slow");
//    }
}


function BackOfficeCargarCarteraACJSONData(cartera){
    var url = urlBackOfficeCargarCartera;
    var param={};
    var jsonEditarContrato=[];
    var datosContrato={};

    datosContrato.num_cartera = cartera.split("|")[0];

    if ($("#AC_tipoContrato").val()=="FI"){
        datosContrato.tipoContrato="FC";
    }else{
        datosContrato.tipoContrato="FI";
    }
    jsonEditarContrato[0]= datosContrato;

    param.jsonEditarContrato=JSON.stringify({"consultaEditarContratos":jsonEditarContrato});

    sendServiceJSON(url,param,BackOfficeCargarCarteraACSuccess,null,null);
}

function BackOfficeCargarCarteraACSuccess(originalRequest){
    //
    detalleCartera="";
    //var count=$("#tabla_AC_consultarCarterasContrato tbody .nuevaCartera").find("tr").size()+1;
    var count=nCount;
    var result = originalRequest;
    detalleCartera=result.numCartera;
    var clientePrincipal=detalleCartera.split("||")[1];
    var detalle=detalleCartera.split("--")[1];
    var respuesta=result.respuesta;

    var checked="";
    //if ((detalleCartera.split("||")[4]).split("--")[0]!="0")
        checked="checked";

   // if (respuesta=="OK"){

        $('#tabla_AC_consultarCarterasContrato').dataTable().fnAddData( [
            '<img style="cursor:pointer" src="resources/images/comun/nolines_plus.gif" class="detalleBusquedaCartera" width="9" height="9" id="'+detalle+'" type="button">',
            '<input type="checkbox" '+habilitarCheck+' class="carteraAgregarEC nuevaCartera" id="guardarCartera_'+count+'">',
            '<span id="codigoCartera_'+count+'">'+detalleCartera.split("||")[0]+'</span>',
            '<span id="modalidad_'+count+'" class="modalidad">'+detalleCartera.split("||")[2]+'</span>',
            '<span id="estatus_'+count+'" class="estatus">'+detalleCartera.split("||")[3]+'</span>',
            '<span id="clientePrincipal_'+count+'" class="clientePrincipal">'+detalleCartera.split("||")[1]+'</span>'] );

    nCount++;
//    }else{
//        $("#mensaje_error").empty();
//        $("#mensaje_error").html(respuesta);
//        $("#div_mensajes_error").fadeIn("slow");
//    }
}







function BackOfficeCargarAgregarContratoJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBackOfficeCargarAgregarContratosContratos;
    var param={};

    sendServiceJSON(url,param,BackOfficeCargarAgregarContratosSuccess,null,null);
}

function BackOfficeCargarAgregarContratosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    $("#div_carga_espera").addClass("oculto");
    var datos = result.contenidoTabla_infoTest;
    var columnascarteras = result.contenidoTabla_culumnasTestaux;
    var datoscarteras = result.contenidoTabla_infoTestaux;
    var  noInfo1=">> Haga click en el correspondiente boton \'Buscar\' para buscar clientes y asociarlos como usuarios del contrato << ";
    var noInfo2=">> Haga click en el correspondiente boton \'Buscar\' para buscar carteras y asociarlas al contrato << ";
    var tabla="tabla_AC_consultarUsuarioContrato";

    $("#AC_usuario_add").removeClass("oculto");
    // crearTabla("div_tabla_AC_consultarUsuarioContrato",tabla,columnas,datos);
    crearTablaV3("div_tabla_AC_consultarUsuarioContrato",tabla,columnas,datos);

    var tabla2="tabla_AC_consultarCarterasContrato";

    // crearTabla("div_tabla_AC_consultarCarterasContrato",tabla2,columnascarteras,datoscarteras);
    crearTablaV3("div_tabla_AC_consultarCarterasContrato",tabla2,columnascarteras,datoscarteras);

/*    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "" },
            { "sClass": ""},
            {  "sClass": "" },
            {  "sClass": "" },
            {  "sClass": "rigth" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo1,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    var oTable = $('#'+tabla).dataTable({
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "" },
            { "sClass": ""},
            {  "sClass": "" },
            {  "sClass": "" },
            {  "sClass": "rigth" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo1,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

/*    var oTable2 = $('#'+tabla2).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "center" },
            {  "sClass": "" },
            {  "sClass": "" },
            { "sClass": "" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo2,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });*/
    var oTable2 = $('#'+tabla2).dataTable({
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
        "bFilter": false,
        "bSort": false,
        "bDestroy": true,
        "bPaginate" : false,
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "center"},
            {  "sClass": "center" },
            {  "sClass": "" },
            {  "sClass": "" },
            { "sClass": "" }
        ],
        "oLanguage": {
            "sZeroRecords": noInfo2,
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });
    $("#div_tabla_consultaContratos").fadeOut("fast",function(){
        $("#div_tabla_AgregarContratos").fadeIn("fast");
    });
    $("#AC_salvar").removeAttr("disabled", "disabled");
}

function seleccionarUsuariosContratosOpcionDetalle(id){
    $("#div_tabla_consultaCUC").attr("style","display: none");
    $("#div_tabla_editarUsuarioCUC").attr("style","display: ");
    if($("#div_USUARIO_CLIENTE").css("display")=='none'){
        $(".opcion_seleccionada").fadeOut("slow",function(){
            $(".opcion_seleccionada").removeClass("opcion_seleccionada");
            $("#div_USUARIO_CLIENTE").addClass("opcion_seleccionada");
            $("#div_USUARIO_CLIENTE").fadeIn("slow");
        });    }
    $("#div_mensajes_error").fadeOut("fast");
    /*funcion dentro del js consultarUsuariosContratos*/
    $("#pantallaMostrar").val("contrato");
    btnVolverContratos="C";
    BackOfficeEditarUsuarioContratoJSONData(id);
}



function abrirDetalleTablaContrato(elemento){
    var nTr =  $(elemento).parents('tr')[0];
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
        BackOfficeDetalleContratosJSONData( $(elemento).attr("id").split("|")[0], $(elemento).attr("id").split("|")[2]);
        oTable.fnOpen( nTr, fnFormatDetailsConsultarContratos( $(elemento).attr("id")), 'details');
    }
};



function abrirDetalleTablaUsuario(elemento){
    var oTable2=$("#"+$(elemento).parents('table')[0].id).dataTable();
    var nTr =  $(elemento).parents('tr')[0];
        if ( oTable2.fnIsOpen(nTr) )
        {
            /* This row is already open - close it */
            elemento.src = "resources/images/comun/nolines_plus.gif";
            oTable2.fnClose( nTr );
        }
        else
        {
            /* Open this row */
            elemento.src= "resources/images/comun/nolines_minus.gif";
            oTable2.fnOpen( nTr, fnFormatDetailsResultadoBusquedaUsuarioDetalle($(elemento).attr("id")), 'details');
        }


};
//Valida que no exista el Login en base de Datos
function BackOfficeValidarUsuarioJSONData(usuario){
    var url = urlBackOfficeValidarUsuario;
    var param={};
    var jsonParametrosString=[];

    jsonParametrosString[0]= usuario;
    existeUsuario="NO OK";
    param.jsonParametrosString=JSON.stringify({"parametros":jsonParametrosString});

    sendServiceJSON_sinc(url,param,BackOfficeValidarUsuarioSuccess,null,null);
}

function BackOfficeValidarUsuarioSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    existeUsuario = result.respuesta;
    //OK el login esta libre y puede ser usado

}



function cargar_select_EC(lista){
    var select=$("#EC_estatusContratoFiltro");
    select.empty();
    var option;

    $.each(lista,function(s,item){

       if (estatus=="0"){
         if ((item.value=='1')||(item.value=='2')||(item.value=='4')||(item.value=='0')){
             option = $(document.createElement('option')).appendTo(select);
             option.attr("value",item.value);
             option.html(item.label);
         }
       }else if(estatus=="1"){
           if ((item.value=='2')||(item.value=='3')||(item.value=='1')){
               option = $(document.createElement('option')).appendTo(select);
               option.attr("value",item.value);
               option.html(item.label);
           }
       }else if(estatus=="2"){
//           if ((item.value=='1')||(item.value=='2')||(item.value=='4')){
//               option = $(document.createElement('option')).appendTo(select);
//               option.attr("value",item.value);
//               option.html(item.label);
//           }
       }else if((estatus=="3")){
           if ((item.value=='1')||(item.value=='2')||(item.value=='3')){
               option = $(document.createElement('option')).appendTo(select);
               option.attr("value",item.value);
               option.html(item.label);
           }

       }else if((estatus=="4")){
            if ((item.value=='1')||(item.value=='2')||(item.value=='4')){
                option = $(document.createElement('option')).appendTo(select);
                option.attr("value",item.value);
                option.html(item.label);
            }

        }
    });
}

function infoPaginaParametrosContratosJSONData(){
    var url = urlParametrosPersonalesCargarContratos;
    var param={};
    param.carterasContrato=$("#numeroContratoPC").html();
    sendServiceJSON(url,param,infoPaginaParametrosContratosSuccess,null,null);
}

function infoPaginaParametrosContratosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    parametros = result.parametrosGlobales;

    $("#numeroMaximoTransfDiarias_PC").val(parametros.vbt_nmtd);
    $("#montoMaxTransDiarias_PC").val(parametros.vbt_mmaxtd);
    $("#montoMinTransOpe_PC").val(parametros.vbt_mminto);
    $("#montoMinTransDiarias_PC").val(parametros.vbt_mmto);

    $("#PC_numeroMaxTransDiarias_OB").val(parametros.ex_nmtd);
    $("#PC_montoMaxTransDiarias_OB").val(parametros.ex_mmtd);
    $("#PC_montoMinTransOpe_OB").val(parametros.ex_mminto);
    $("#PC_montoMinTransDiarias_OB").val(parametros.ex_mmto);

}






function parametrosContratosActualizarJSONData(){
    var url = urlParametrosPersonalesGuardarBO;
    var param={};
    var jsonParametrosGlobales=[];
    var parametrosPersonales={};


    parametrosPersonales.vbt_nmtd= $("#numeroMaximoTransfDiarias_PC").val();
    parametrosPersonales.vbt_mmaxtd= $("#montoMaxTransDiarias_PC").val();
    parametrosPersonales.vbt_mminto= $("#montoMinTransOpe_PC").val();
    parametrosPersonales.vbt_mmto= $("#montoMinTransDiarias_PC").val();

    parametrosPersonales.ex_nmtd= $("#PC_numeroMaxTransDiarias_OB").val();
    parametrosPersonales.ex_mmtd= $("#PC_montoMaxTransDiarias_OB").val();
    parametrosPersonales.ex_mminto= $("#PC_montoMinTransOpe_OB").val();
    parametrosPersonales.ex_mmto= $("#PC_montoMinTransDiarias_OB").val();

    parametrosPersonales.num_contrato=$("#numeroContratoPC").html();



    jsonParametrosGlobales[0]=parametrosPersonales;

    param.jsonParametrosGlobales=JSON.stringify({"parametrosGlobales":jsonParametrosGlobales});
    param.codigo="USR";
    sendServiceJSON(url,param,parametrosContratosActualizarSuccess,null,null);
}

function parametrosContratosActualizarSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;


    if(respuesta =="OK")
        alert("Actualizaci\u00f3n exitosa");
    else
        alert("Actualizaci\u00f3n fallida");

}


function contarAdministradores(){
    administradoresFC=0;
    $.each($("#tabla_AC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
        if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
            if ($("#tipoCliente_"+this.id.split("_")[1]).val()=="0000000016"){
                administradoresFC++;
            }
        }
    });

}


function validarAdministradoresFC(id){
    contarAdministradores();
    if (administradoresFC>1){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Solo puede existir un Administrador por contrato");
        $("#div_mensajes_error").fadeIn("slow");
        $("#"+id).attr('checked', false);
    }

}


function ECvalidarAdministradoresFC(id){
    contarAdministradoresEC();
    if (administradoresFC>1){
        $("#mensaje_error").empty();
        $("#mensaje_error").html("Solo puede existir un Administrador por contrato");
        $("#div_mensajes_error").fadeIn("slow");
        $("#"+id).attr('checked', false);
    }

}


function contarAdministradoresEC(){
    administradoresFC=0;

    $.each($("#tabla_EC_consultarUsuarioContrato .usuarioViejo"),function(pos,item){
            if (($("#tipoClienteCodigoV_"+this.id.split("_")[1]).val()=="0000000016")&&($("#estatusClienteV_"+this.id.split("_")[1]).val()=="Activo")){
                administradoresFC++;
            }
    });

    $.each($("#tabla_EC_consultarUsuarioContrato .nuevoUsuario"),function(pos,item){
        if($("#guardar_"+this.id.split("_")[1]).is(':checked')){
            if ($("#tipoCliente_"+this.id.split("_")[1]).val()=="0000000016"){
                administradoresFC++;
            }
        }
    });

}



function BackOfficevalidarCarteraContratoJSONData(cartera){
    var url = urlBackOfficeCargarCartera;
    var param={};
    var jsonEditarContrato=[];
    var datosContrato={};

    datosContrato.num_cartera = cartera;

    if ($("#EC_tipoContrato").val()=="FI"){
        datosContrato.tipoContrato="FC";
    }else{
        datosContrato.tipoContrato="FI";
    }

    jsonEditarContrato[0]= datosContrato;

    param.jsonEditarContrato=JSON.stringify({"consultaEditarContratos":jsonEditarContrato});

    sendServiceJSON_sinc(url,param,BackOfficevalidarCarteraContratoJSONDataSuccess,null,null);
}

function BackOfficevalidarCarteraContratoJSONDataSuccess(originalRequest){

    var result = originalRequest;
    var respuesta=result.respuesta;

     if (respuesta!="OK"){
         mensajeCartera=mensajeCartera+"<br>"+respuesta;
     }

}


var urlCargarBuscarBancos="Transfers_cargarBuscarBanco.action";
var urlBuscarBancos="Transfers_buscarBanco.action";
var banco=""
var noInfo=""

$(document).ready(function(){

    $("#btnCodBancoBuscar").click(function(){
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        banco="btnCodBancoBuscar";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();

        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })

//            e.preventDefault();

    });



    $("#btnCodBancoBuscarIB").click(function(){
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        banco="btnCodBancoBuscarIB";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();

        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });

    $("#btnCodBancoBuscarIBSWIFT").click(function(){
        banco="btnCodBancoBuscarIBSWIFT";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();

        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });

    $("#btnCodBancoBuscarSWIFT").click(function(){
        banco="btnCodBancoBuscarSWIFT";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();

        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });

    $("#Template_btnCodBancoBuscarIBSWIFT").click(function(){
        $("#paginacion_buscarBanco_consulta_bancos_template").removeClass("jPaginate");
        banco="Template_btnCodBancoBuscarIBSWIFT";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();
        console.log("Event Action");


        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });

    $("#Template_btnCodBancoBuscarSWIFT").click(function(){
        banco="Template_btnCodBancoBuscarSWIFT";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();

        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });

    $("#FC_btnCodBancoBuscar").click(function(){
        banco="FC_btnCodBancoBuscar";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();

        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });

    $("#FC_btnCodBancoBuscarIB").click(function(){
        banco="FC_btnCodBancoBuscarIB";
        $("#tabla_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").empty();
        $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
        infoPaginaBuscarBancosJSONData();


        $("#sign_up_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });


    $("#Template_btnCodBancoBuscar").click(function(){
        $("#paginacion_buscarBanco_consulta_bancos_template").removeClass("jPaginate");
        banco="Template_btnCodBancoBuscar";
        $("#tabla_consulta_bancos_template").empty();
        $("#paginacion_buscarBanco_consulta_bancos_template").empty();
        infoPaginaBuscarBancosJSONData();

/*            $("#sign_up_template").lightbox_me({centered: true, onLoad: function() {
                $("#sign_up_template").find("input:first").focus();
            }});*/
        $("#sign_up_template_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })

//            e.preventDefault();

    });

    $("#Template_btnCodBancoBuscarIB").click(function(){
        $("#paginacion_buscarBanco_consulta_bancos_template").removeClass("jPaginate");
        banco="Template_btnCodBancoBuscarIB";
        $("#tabla_consulta_bancos_template").empty();
        $("#paginacion_buscarBanco_consulta_bancos_template").empty();

        infoPaginaBuscarBancosJSONData();
        // $("#sign_up_template").lightbox_me({centered: true, onLoad: function() {
        //     $("#sign_up_template").find("input:first").focus();
        // }});
        $("#sign_up_template_search").modal({
            showClose: !1,
            modalClass: "form-modal",
            fadeDuration: 100,
            blockerClass: "form-modal--blocker",
        })
    });


    $('#buscar').click(function(){
        var valido=true;
        var mensaje="Introduzca ";

        if($("#BankCode_buscar").get(0).value.isEmpty() &&
            $("#BankName_buscar").get(0).value.isEmpty()){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html('You must enter at least one of these criteria: Bank Code / Bank Name');
            else
                $("#mensaje_error").html('Debe indicar al menos uno de estos criterios: C&oacute;digo y/o Nombre del Banco');

            $("#div_mensajes_error").fadeIn("slow");
        }
        else{
            $("#tabla_consulta_bancos_template").empty();
            $("#paginacion_buscarBanco_consulta_bancos_template").empty();

            $("#div_carga_espera_sign_up").removeClass("oculto");
            buscarBancoJSONData() ;
        }
    });

    $('#buscar_FC').click(function(){
        var valido=true;
        var mensaje="Introduzca ";

        if($("#BankCode_buscar").get(0).value.isEmpty() &&
            $("#BankName_buscar").get(0).value.isEmpty()){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html('You must enter at least one of these criteria: Bank Code / Bank Name');
            else
                $("#mensaje_error").html('Debe indicar al menos uno de estos criterios: C&oacute;digo y/o Nombre del Banco');

            $("#div_mensajes_error").fadeIn("slow");
        }
        else{
            $("#paginacion_buscarBanco_consulta_bancos").empty();
            $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
            $("#tabla_consulta_bancos").empty();
            $("#div_carga_espera_sign_up").removeClass("oculto");
            buscarBancoJSONData() ;
        }
    });

    $('#Template_buscar').click(function(){
        var valido=true;
        var mensaje="Introduzca ";

        if($("#Template_BankCode_buscar").get(0).value.isEmpty() &&
            $("#Template_BankName_buscar").get(0).value.isEmpty()){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html('You must enter at least one of these criteria: Bank Code / Bank Name');
            else
                $("#mensaje_error").html('Debe indicar al menos uno de estos criterios: C&oacute;digo y/o Nombre del Banco');

            $("#div_mensajes_error").fadeIn("slow");
        }
        else{
            $("#tabla_consulta_bancos_template").empty();
            $("#paginacion_buscarBanco_consulta_bancos_template").empty();

            $("#div_carga_espera_ctas_no_permitidas").removeClass("oculto");
            buscarBancoJSONData() ;
        }
    });


    $('#cancel').click(function(){
        clearPoppup();
    });

    $('#cancel_FC').click(function(){
        // clearPoppup();
        $.modal.close();
    });

    $('#Template_cancel').click(function(){
        // clearPoppup();
        $.modal.close();
    });




});

function infoPaginaBuscarBancosJSONData(){
    var url = urlCargarBuscarBancos;
    var param={};
    $("#sign_up_search .selectFormulario_buscar").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#sign_up_search .inputFormulario_buscar").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });

    $("#sign_up_template .selectFormulario_buscar").each(function(){
        this.value="-2";
        $("#"+this.id).removeClass("error_campo");
    });
    $("#sign_up_template .inputFormulario_buscar").each(function(){
        this.value="";
        $("#"+this.id).removeClass("error_campo");
    });

    sendServiceJSON(url,param,infoPaginaBuscarBancoSuccess,null,null);
}


function infoPaginaBuscarBancoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    Paises = result.paises;
    PaisesBeneficiario = result.PaisesBeneficiario;
    codBankBen = result.codBankBen;
    idioma = result.idioma;

    if(Paises!=null){
        if(banco=="Template_btnCodBancoBuscarIB" || banco=="Template_btnCodBancoBuscar"){
            if(idioma == "_ve_es")
                cargar_selectPersonal("Template_BankCountry_buscar", Paises,"Seleccione","-2");
            else
                cargar_selectPersonal("Template_BankCountry_buscar", Paises,"Select","-2");
        }else{
            if(idioma == "_ve_es")
                cargar_selectPersonal("BankCountry_buscar", Paises,"Seleccione","-2");
            else
                cargar_selectPersonal("BankCountry_buscar", Paises,"Select","-2");
        }


    }

    if(codBankBen!=null){
        if(banco=="Template_btnCodBancoBuscarIB" || banco=="Template_btnCodBancoBuscar"){
            var codSwift=[];
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal2("Template_BankCodeType_buscar", codSwift);
        }else if(banco=="btnCodBancoBuscarSWIFT" || banco=="btnCodBancoBuscarIBSWIFT" || banco=="Template_btnCodBancoBuscarSWIFT" || banco=="Template_btnCodBancoBuscarIBSWIFT"){
            var codSwift=[];
            $.each(codBankBen, function (s, item) {
                if (item.value == "SWIFT")
                    codSwift[0] = item;
            });
            cargar_selectPersonal2("BankCodeType_buscar", codSwift);
        }else{
            var codSwift=[];
            var i = 0;
            $.each(codBankBen, function (s, item) {
                if (item.value != "SWIFT" && item.value != "ACCOUNT"){
                    codSwift[i] = item;
                    i++;
                }
            });
            cargar_selectPersonal2("BankCodeType_buscar", codSwift);
        }
    }

}

function buscarBancoJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlBuscarBancos;
    var param={};
    var jsonTransfers=[];
    if(banco=="Template_btnCodBancoBuscarIB" || banco=="Template_btnCodBancoBuscar"){
        jsonTransfers[0] =  $("#Template_BankCodeType_buscar").get(0).value;
        jsonTransfers[1] = $("#Template_BankCode_buscar").get(0).value.toUpperCase();
        jsonTransfers[2] = $("#Template_BankName_buscar").val().toUpperCase();
        jsonTransfers[3] = $('#Template_BankCountry_buscar :selected').html();
        jsonTransfers[4] = $("#Template_BankCountry_buscar").val();
        jsonTransfers[5] = banco;
        jsonTransfers[6] = "A";
    }else if(banco=="btnCodBancoBuscarIB" || banco=="btnCodBancoBuscar" || banco=="btnCodBancoBuscarSWIFT" || banco=="btnCodBancoBuscarIBSWIFT" || banco=="Template_btnCodBancoBuscarSWIFT" || banco=="Template_btnCodBancoBuscarIBSWIFT"){
        jsonTransfers[0] =  $("#BankCodeType_buscar").get(0).value;
        jsonTransfers[1] = $("#BankCode_buscar").get(0).value.toUpperCase();
        jsonTransfers[2] = $("#BankName_buscar").val().toUpperCase();
        jsonTransfers[3] = $('#BankCountry_buscar :selected').html();
        jsonTransfers[4] = $("#BankCountry_buscar").val();
        jsonTransfers[5] = banco;
        jsonTransfers[6] = "A";
    }else if(banco=="FC_btnCodBancoBuscarIB" || banco=="FC_btnCodBancoBuscar"){
        jsonTransfers[0] =  $("#BankCodeType_buscar").get(0).value;
        jsonTransfers[1] = $("#BankCode_buscar").get(0).value.toUpperCase();
        jsonTransfers[2] = $("#BankName_buscar").val().toUpperCase();
        jsonTransfers[3] = $('#BankCountry_buscar :selected').html();
        jsonTransfers[4] = $("#BankCountry_buscar").val();
        jsonTransfers[5] = banco;
        jsonTransfers[6] = "A";
    };


    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");

    param.jsonTransfers=JSON.stringify({"parametros":jsonTransfers});

    sendServiceJSON(url,param,buscarBancoSuccess,null,null);
}


function buscarBancoSuccess(originalRequest){
    //                   this is the json return data
    $("#div_carga_espera").addClass("oculto");
    var result = originalRequest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "left" },
        { "sClass": "center"},
        {  "sClass": "center" },
        {  "sClass": "center" },
        {  "sClass": "right" }
    ];
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var tabla;
    banco= result.banco;
    var tamano_pag=10;
    var tabla_id;
    var id_contenedor;
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    if(banco=="Template_btnCodBancoBuscarIB" || banco=="Template_btnCodBancoBuscar"){
        // tabla_id="buscarBanco_consulta_bancos_template";
        tabla_id="tabla_consulta_bancos_template";
        id_contenedor="buscarBanco_div_tabla_consulta_bancos_template";
        // tabla=crearTabla_paginacionV2(id_contenedor,tabla_id,columnas,datos,0,tamano_pag);
        // tabla=crearTabla_paginacionV2(tabla_id,columnas,datos,0,tamano_pag,"table__row--modal");
        crearTablaV2(tabla_id,columnas,datos,"", false);
    }else{
        // tabla_id="tabla_consulta_bancos";
        tabla_id="tabla_consulta_bancos";
        id_contenedor="buscarBanco_div_tabla_consulta_bancos";
        // tabla=crearTabla_paginacionV2(tabla_id,columnas,datos,0,tamano_pag,"table__row--modal");
        crearTablaV2(tabla_id,columnas,datos,"", false);
    }
//
        /*tabla.dataTable({
            "iDisplayLength": tamano_pag,
            "bJQueryUI": true,
            "bFilter": false,
            "bSort": false,
            "bPaginate": false,
            "bScrollCollapse": true,
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });*/

/*
        oTable=tabla;

        if(datos.length>0){
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


    $("#div_carga_espera_sign_up").addClass("oculto");
    $("#div_carga_espera_template").addClass("oculto");
*/

    oTable=$("#"+tabla_id).dataTable({
            "iDisplayLength": tamano_pag,
            "bJQueryUI": false,
            "bFilter": false,
            "bSort": false,
            "bPaginate": true,
            //"bScrollCollapse": true,
            "bDestroy": true,
            "sPaginationType": "full_numbers",
            "aoColumns": p_sclass,
            "oLanguage": {
                "sZeroRecords": noInfo,
                "sInfo": "",
                "sInfoEmpty": ""
            }
        });
}

var scrollCachePosition = 0;

$(function() {
    $("#abrirPop").click(function(event) {
        scrollCachePosition = $(window).scrollTop();
        //Envío el scroll a la posici\u00f3n 0 (left), 0 (top), es decir, arriba de todo.
        window.top.scroll(0,0);

        //Si el body es mas grande que la capa 'wrapper' incrementa la altura del body a la capa 'capaPopUp'.
        if ($("body").outerHeight()>$("#wrapper").outerHeight()){
            var altura=$("body").outerHeight();
        }else{
            //Si la capa 'wrapper' es más grande que el body incrementa la altura de la capa 'wrapper' a la capa 'capaPopUp'.
            var altura=$("#wrapper").outerHeight();
        }
        window.document.getElementById("capaPopUp").style.height=altura+"px";
        event.preventDefault();
        //Muestro la capa con el efecto 'slideToggle'.
        $("#capaPopUp").slideToggle();

        //Calculo la altura de la capa 'popUpDiv' y lo divido entre 2 para darle un margen negativo.
        var altura=$("#popUpDiv").outerHeight();
        $("#popUpDiv").css("margin-top","-"+parseInt(altura/2)+"px");

        //Calculo la anchura de la capa 'popUpDiv' y lo divido entre 2 para darle un margen negativo.
        var anchura=$("#popUpDiv").outerWidth();
        $("#popUpDiv").css("margin-left","-"+parseInt(anchura/2)+"px");

        //Muestro la capa con el efecto 'slideToggle'.
        $("#popUpDiv").slideToggle();
    });
    $("#cerrar").click(function(event) {
        event.preventDefault();
        //Cierro las capas con el efecto 'slideToggle'.
        $("#capaPopUp").slideToggle();
        $("#popUpDiv").slideToggle();
        //Si la variable scrollCachePosition es mayor que 0 incrementará la posici\u00f3n del scroll al valor que se almacen\u00f3.
        if (scrollCachePosition > 0) {
            window.top.scroll(0,scrollCachePosition);
            //Reseteamos la variable scrollCachePosition a 0 para poder ejecutar el script tantas veces sea necesario.
            scrollCachePosition = 0;
        }
    });
});


function clearPoppup(){
    $("#paginacion_buscarBanco_consulta_bancos").empty();
    $("#paginacion_buscarBanco_consulta_bancos").removeClass("jPaginate");
    if(banco=="Template_btnCodBancoBuscarIB" || banco=="Template_btnCodBancoBuscar"){
        $("#buscarBanco_div_tabla_consulta_bancos_template").empty();
        $("#paginacion_buscarBanco_consulta_bancos_template").empty();


        $("#sign_up_template .selectFormulario_buscar").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#sign_up_template .inputFormulario_buscar").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
    }
    else{
        $("#buscarBanco_div_tabla_consulta_bancos").empty();
        $("#sign_up_search .selectFormulario_buscar").each(function(){
            this.value="-2";
            $("#"+this.id).removeClass("error_campo");
        });
        $("#sign_up_search .inputFormulario_buscar").each(function(){
            this.value="";
            $("#"+this.id).removeClass("error_campo");
        });
    }



}


function cargarBanco(id){
    //console.log(id.split("|"));
    if (banco=="btnCodBancoBuscar"){
      $("#BankCode2").val(id.split("|")[0]);
      $("#NameBank").val(id.split("|")[1]);
      $("#AddressLineBank1").val(id.split("|")[2]);
      $("#AddressLineBank3").val(id.split("|")[3]);
     /* if(!id.split("|")[3]=="&nbsp;")
          $("#AddressLineBank2").val(id.split("|")[3]);   */

      $("#CountryBank").val(id.split("|")[4]);
      $("#BankCode").val($("#BankCodeType_buscar").val());
      $("#BankCodeSWIFTtext").val(id.split("|")[5]);

    }else if(banco=="btnCodBancoBuscarIB"){
        $("#BankCodeIB2").val(id.split("|")[0]);
        $("#NameBankIB").val(id.split("|")[1]);
        $("#AddressLineBankIB1").val(id.split("|")[2]);
//        if(!id.split("|")[3]=="&nbsp;")
//            $("#AddressLineBankIB2").val(id.split("|")[3]);
        $("#AddressLineBankIB3").val(id.split("|")[3]);

        $("#CountryBankIB").val(id.split("|")[4]);
        $("#BankCodeIB").val($("#BankCodeType_buscar").val());

        $("#BankCodeIBSWIFTtext").val(id.split("|")[5]);

    }else if(banco=="btnCodBancoBuscarIBSWIFT"){
        $("#BankCodeIBSWIFTtext").val(id.split("|")[0]);
        $("#BankCodeIBSWIFT").val($("#BankCodeType_buscar").val());
        $("#NameBankIB").val(id.split("|")[1]);
        $("#AddressLineBankIB1").val(id.split("|")[2]);
        $("#AddressLineBankIB3").val(id.split("|")[3]);
        $("#CountryBankIB").val(id.split("|")[4]);

        $("#BankCodeIB2").val(id.split("|")[5]);

    }else if(banco=="btnCodBancoBuscarSWIFT"){
        $("#BankCodeSWIFTtext").val(id.split("|")[0]);
        $("#BankCodeSWIFT").val($("#BankCodeType_buscar").val());
        $("#NameBank").val(id.split("|")[1]);
        $("#AddressLineBank1").val(id.split("|")[2]);
        $("#AddressLineBank3").val(id.split("|")[3]);
        $("#CountryBank").val(id.split("|")[4]);

        $("#BankCode2").val(id.split("|")[5]);

    }else if(banco=="Template_btnCodBancoBuscarSWIFT"){
        $("#Template_SwiftBankCode2").val(id.split("|")[0]);
        $("#Template_SwiftBankCode").val($("#BankCodeType_buscar").val());
        $("#Template_NameBank").val(id.split("|")[1]);
        $("#Template_AddressLineBank1").val(id.split("|")[2]);
        $("#Template_AddressLineBank3").val(id.split("|")[3]);
        $("#Template_CountryBank").val(id.split("|")[4]);
        $("#Template_BankCode2").val(id.split("|")[5]);

    }else if(banco=="Template_btnCodBancoBuscarIBSWIFT"){

        $("#Template_SwiftBankCodeIB2").val(id.split("|")[0]);
        $("#Template_SwiftBankCodeIB").val($("#BankCodeType_buscar").val());
        $("#Template_NameBankIB").val(id.split("|")[1]);
        $("#Template_AddressLineBankIB1").val(id.split("|")[2]);
        $("#Template_AddressLineBankIB3").val(id.split("|")[3]);
        $("#Template_CountryBankIB").val(id.split("|")[4]);
        $("#Template_BankCode2").val(id.split("|")[5]);

    }else if (banco=="FC_btnCodBancoBuscar"){
        $("#FC_BankCode2").val(id.split("|")[0]);
        $("#FC_NameBank").val(id.split("|")[1]);
        $("#FC_AddressLineBank1").val(id.split("|")[2]);
        if(!id.split("|")[3]=="&nbsp;")
            $("#FC_AddressLineBank2").val(id.split("|")[3]);

        $("#FC_CountryBank").val(id.split("|")[4]);
        $("#FC_BankCode").val($("#BankCodeType_buscar").val());
    }else if(banco=="FC_btnCodBancoBuscarIB"){
        $("#FC_BankCodeIB2").val(id.split("|")[0]);
        $("#FC_NameBankIB").val(id.split("|")[1]);
        $("#FC_AddressLineBankIB1").val(id.split("|")[2]);
        if(!id.split("|")[3]=="&nbsp;")
            $("#FC_AddressLineBankIB2").val(id.split("|")[3]);

        $("#FC_CountryBankIB").val(id.split("|")[4]);
        $("#FC_BankCodeIB").val($("#BankCodeType_buscar").val());
    }
    else  if (banco=="Template_btnCodBancoBuscar"){
        $("#Template_BankCode2").val(id.split("|")[0]);
        $("#Template_NameBank").val(id.split("|")[1]);
        $("#Template_AddressLineBank1").val(id.split("|")[2]);
        $("#Template_AddressLineBank3").val(id.split("|")[3]);
       // if(!id.split("|")[3]=="&nbsp;")
            //$("#Template_AddressLineBank2").val(id.split("|")[3]);

        $("#Template_CountryBank").val(id.split("|")[4]);
        $("#Template_BankCode").val($("#Template_BankCodeType_buscar").val());
        $("#Template_SwiftBankCode2").val(id.split("|")[5]);
    }else if(banco=="Template_btnCodBancoBuscarIB"){
        $("#Template_BankCodeIB2").val(id.split("|")[0]);
        $("#Template_NameBankIB").val(id.split("|")[1]);
        $("#Template_AddressLineBankIB1").val(id.split("|")[2]);
        $("#Template_AddressLineBankIB3").val(id.split("|")[3]);
        //$("#Template_AddressLineBankIB1").val(id.split("|")[2]);
       // if(!id.split("|")[3]=="&nbsp;")
           // $("#Template_AddressLineBankIB2").val(id.split("|")[3]);

        $("#Template_CountryBankIB").val(id.split("|")[4]);
        $("#Template_BankCodeIB").val($("#Template_BankCodeType_buscar").val());
        $("#BankCodeIBSWIFTtext").val(id.split("|")[5]);
    }
    if(banco=="Template_btnCodBancoBuscarIB" || banco=="Template_btnCodBancoBuscar"){

    $.modal.close();
      //  $("#sign_up_template_search").modal('close');
    }
    else{

        $.modal.close();
        //$("#sign_up_search").modal('close');
    }
    banco="";


    if (origenTemplate=="TMP"){
        $("#Template_btn_transferencia").attr("style","display:none");
        $("#Template_btn_TOB_aprobar").attr("style","display:none");
    }
}
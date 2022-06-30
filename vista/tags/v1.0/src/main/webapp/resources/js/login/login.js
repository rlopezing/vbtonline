var urlValidarUsuario="Login_validarUsuario.action";
var urlHomeAction="Home";
var urlValidarCaptcha="Login_validarCaptcha.action";
var userAgent = navigator.userAgent.toLowerCase();
var cambioClave="";
var privilegio_cambioEstatus_contrato=true;
var privilegio_editar_contrato=true;
var privilegio_editar_usuarios=true;
var privilegio_editar_estatusUsuarioBackoffice=true;
var privilegio_editar_estatusUsuarioContrato=true;
var privilegio_verDatosAdministrador=true;
var privilegio_verDatosCargador=true;
var privilegio_verDatosSupervisor=true;
var privilegio_editDatosAdministrador=true;
var privilegio_editDatosCargador=true;
var privilegio_editDatosSupervisor=true;
var privilegio_addDatosAdministrador=true;
var privilegio_addDatosCargador=true;
var privilegio_addDatosSupervisor=true;
var valCambioClave="";
var valCaptcha="N";
var tipoUsuLogin="";
var idioma="";
var numReferenciaGlobal="";
var montosGlobal="";
var label_idioma="es";

var label_es=[];

var label_en=[];

var title_es=[];

var title_en=[];

if(idioma!="_ve_es"){
    var RecaptchaOptions = {
        theme : 'white',
        custom_translations : {
            instructions_visual : "Type the text",
            instructions_audio : "Type what you hear",
            play_again : "Play sound again",
            cant_hear_this : "Download sound as MP3",
            visual_challenge : "Get a visual challenge",
            audio_challenge : "Get an audio challenge",
            refresh_btn : "Get a new challenge",
            help_btn : "Help",
            incorrect_try_again : "Incorrect. Try again"
        }
    };
}else{
    var RecaptchaOptions = {
        theme : 'white',
        custom_translations : {
            instructions_visual : "Introduzca el texto",
            instructions_audio : "Escribe lo que oigas",
            play_again : "Volver a reproducir el sonido",
            cant_hear_this : "Descargar el sonido en MP3",
            visual_challenge : "Obtener una pista visual",
            audio_challenge : "Obtener una pista sonora",
            refresh_btn : "Obtener una pista nueva",
            help_btn : "Ayuda",
            incorrect_try_again : "Incorrecto"
        }
    };
}

$(document).ready(function(){

    $(".seleccionIdioma").click(function(){
        $(".current_seleccionIdioma").removeClass("current_seleccionIdioma");
        $(this).addClass("current_seleccionIdioma");
    });

    $("#poppup_ayuda_transferencia_cerrar").click(function(){
        $("#poppup_ayuda_transferencia").fadeOut("fast");
    });

    $("#tabs_popup_ayuda").tabs();

    $.ajaxSetup({cache: false});

    detectarPlataforma();

    $("#btn__lgn_aceptar").click(function(){
        console.log("btn__lgn_aceptar de v1");
        $("#btn__lgn_aceptar").attr("disabled",true);
        var valido=true;
        if($("#username").get(0).value.isEmpty()){
            if(idioma!="_us_en")
                $(".errorLogin").html("Introduzca Usuario!");
            else
                $(".errorLogin").html("Enter User!");
            valido=false;
        }else{
            if ($("#pwdClave").get(0).value.isEmpty() ){
                if(idioma!="_us_en")
                    $(".errorLogin").html("Introduzca Clave!");
                else
                    $(".errorLogin").html("Enter Password!");
                valido=false;
            }
            if (valCaptcha!="N"){
                if ($("#recaptcha_response_field").get(0).value.isEmpty() ){
                    if(idioma!="_us_en")
                        $(".errorLogin").html("Introduzca el codigo de confirmacion");
                    else
                        $(".errorLogin").html("Enter confirmation code");
                    valido=false;
                }
            }
        }

        if(valido){
            $(".error").html("");
            $("#div_carga_espera").removeClass("oculto");
            if (valCaptcha=="N"){
                login();
                $("#showCaptcha").attr("style","display:none");
            }else{
                $("#showCaptcha").attr("style","display:");
                validarCaptcha();
            }
        }else{
            $("#btn__lgn_aceptar").attr("disabled",false);
        }

    });


    $("#btn__lgn_limpiar").click(function(){
        $("#username").val("");
        $("#pwdClave").val("");
        $(".error").html("");
        $("#mensaje").val("");
        $("#recaptcha_response_field").val("");

    });

    cargarIdiomaJSONData();

    $(document).bind("contextmenu",function(e){
        return false;
    });

});


function login(){
    var url = urlValidarUsuario;
    var param={};
    param.username=$("#username").val();
    param.pwdClave=$().crypt({method: "b64enc",source:$("#pwdClave").val()});
    param.IpLocal = $("#IpLocal").val();
    sendServiceJSON(url,param,loginSuccess,null,null);

}

//function loginSuccess(originalRequest){
//    var result = originalRequest;
//    if(result.mensaje=="OK"){
//       /* redireccionar(urlHomeAction);
//        //location.href=urlHomeAction;   */
//        CargarHomeJSONData();
//
//    }else{
//        $("#mensaje").html(result.mensaje);
//        $("#btn__lgn_aceptar").attr("disabled",false);
//        $("#div_carga_espera").addClass("oculto");
//        valCaptcha="S";
//        $("#showCaptcha").attr("style","display:");
//        $("#login").attr("width","380px");
//        $("#botonLogin").removeClass("espacioRelleno");
//        $("#espacio1").remove();
//        $("#espacio2").remove();
//        $("#username").val("");
//        $("#pwdClave").val("");
//        $("#recaptcha_response_field").val("");
//    }
//}

function loginSuccess(originalRequest){
    var result = originalRequest;
    console.log("entro loginSuccess")
    if(result.mensaje=="OK"){
        console.log("entro loginSuccess->result.mensaje: ")
        cambioClave=result.cambioPass;
        $("#btn__lgn_aceptar").attr("disabled",false);
        $("#tipo_usuario_app").val(result.tipo);
        if(result.backoffice) {
            console.log("entro loginSuccess->result.backoffice: ");
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
            label_idioma="en";
            console.log("loginSuccess->label_idioma: ", label_idioma);
            cambiarIdiomaJSONData(label_idioma);
        }
        //CargarHomeJSONData();
        $(location).attr('href',urlHomeAction);
    }else{
        $("#mensaje").html(result.mensaje);
        $("#btn__lgn_aceptar").attr("disabled",false);
        $("#div_carga_espera").addClass("oculto");

        if ((result.mensaje!="User is blocked")) {
            if ((result.mensaje!="Su usuario se encuentra bloqueado")) {
                valCaptcha="S";
                $("#showCaptcha").attr("style","display:");
                $("#login").attr("width","380px");
                $("#botonLogin").removeClass("espacioRelleno");
                $("#espacio1").remove();
                $("#espacio2").remove();
                $("#username").val("");
                $("#pwdClave").val("");
                $("#recaptcha_response_field").val("");
            }
        }
    }
}

function validarCaptcha(){
    var url = urlValidarCaptcha;
    var param={};
    param.challengeCaptcha=$("#recaptcha_challenge_field").val();
    param.responseCaptcha=$("#recaptcha_response_field").val();
    param.IpLocal = $("#IpLocal").val();
    sendServiceJSON(url,param,captchaSuccess,null,null);

}

function captchaSuccess(originalRequest){
    var result = originalRequest;
    if(result.mensaje=="OK"){
        login();
    }else{
        $("#btn__lgn_aceptar").attr("disabled",false);
        $("#div_carga_espera").addClass("oculto");
        $("#username").val("");
        $("#pwdClave").val("");
        $("#recaptcha_response_field").val("");
        Recaptcha.switch_type('image');
        $("#username").focus();
        if(idioma!="_us_en")
            $(".errorLogin").html("Codigo de confirmacion Invalido");
        else
            $(".errorLogin").html("Invalid confirmation code");
    }
}


function detectarPlataforma(){
    var plataforma= navigator.platform;
    if(plataforma == 'Win32' || plataforma == 'Win64' || plataforma == 'Linux' || plataforma == 'MacIntel')
    {
        if(!actualizar_navegador()){
            plataforma="PC";
            $('.teclado .ui-widget-content .ui-corner-all .ui-keyboard-preview').live("keypress",function(){
                BloqueaTecla(event);
            });

            $('#pwdClave').keyboard({
                stayOpen : false,
                layout   : 'qwerty',
                lockInput: true,
                restrictInput : true, // Prevent keys not in the displayed keyboard from being typed in
                preventPaste : true,  // prevent ctrl-v and right click
                autoAccept : true
            });
        }
    }else{
        plataforma="NO-PC";
    }
}


function BloqueaTecla(e) {
    alert('In order to indicate its key it must use the Virtual Keyboard.');
    return false;
}

function cargarPermisologia(listaPermisos){
    $.each(listaPermisos,function(posOpciones,ItemOpciones){
        $.each(ItemOpciones.privilegios,function(posPrivilegios,ItemPrivilegios){


                if((ItemOpciones.opcion=="0000000004") && (posPrivilegios=="2")){// SI ESTA EN LA OPCION CONTRATO Y ACCION EDITAR CONTRATO
                   //no se elimina el boton se deben dejar los demas se deja en caso de que se requiera agregar una variable global para validar

                }else{
                    if((ItemOpciones.opcion=="0000000029") && (posPrivilegios=="2")){

                    }else{
                        if(ItemPrivilegios!="1")
                            $("."+ItemOpciones.opcion+"_"+posPrivilegios).remove();
                    }
                }




            if((ItemOpciones.opcion=="0000000004") && (posPrivilegios=="4")){// SI ESTA EN LA OPCION CONTRATO Y ACCION EDITAR ESTATUS
                if(ItemPrivilegios=="0"){
                    privilegio_cambioEstatus_contrato=false;
                }
            }

            if((ItemOpciones.opcion=="0000000004") && (posPrivilegios=="2")){// SI ESTA EN LA OPCION CONTRATO Y ACCION EDITAR CONTRATO
                if(ItemPrivilegios=="0"){
                    privilegio_editar_contrato=false;
                }
            }

            if((ItemOpciones.opcion=="0000000003") && (posPrivilegios=="4")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if(ItemPrivilegios=="0"){
                    privilegio_editar_estatusUsuarioBackoffice=false;
                }
            }


            if((ItemOpciones.opcion=="0000000025")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if((ItemPrivilegios=="0") && (posPrivilegios=="0")){
                    privilegio_verDatosAdministrador=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="1")){
                    privilegio_addDatosAdministrador=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="2")){
                    privilegio_editDatosAdministrador=false;

                }
            }

            if((ItemOpciones.opcion=="0000000026")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if((ItemPrivilegios=="0") && (posPrivilegios=="0")){
                    privilegio_verDatosSupervisor=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="1")){
                    privilegio_addDatosSupervisor=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="2")){
                    privilegio_editDatosSupervisor=false;

                }
            }

            if((ItemOpciones.opcion=="0000000027")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if((ItemPrivilegios=="0") && (posPrivilegios=="0")){
                    privilegio_verDatosCargador=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="1")){
                    privilegio_addDatosCargador=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="2")){
                    privilegio_editDatosCargador=false;

                }
            }


            if((ItemOpciones.opcion=="0000000029") && (posPrivilegios=="4")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if(ItemPrivilegios=="0"){
                    privilegio_editar_estatusUsuarioContrato=false;
                }
            }

            if((ItemOpciones.opcion=="0000000029") && (posPrivilegios=="2")){// SI ESTA EN LA OPCION CONTRATO Y ACCION EDITAR CONTRATO
                if(ItemPrivilegios=="0"){
                    privilegio_editar_usuarios=false;
                }
            }
        });
    });

}



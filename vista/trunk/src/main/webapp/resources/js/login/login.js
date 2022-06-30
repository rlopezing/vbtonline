var urlValidarUsuario="Login_validarUsuario.action";
var urlHomeAction="Home";
var urlValidarCaptcha="Login_validarCaptcha.action";
var urlForgotPass="Login_forgotPassword.action";
var urlLoginEnviarClaveSMS="Security_enviarClaveSMS.action";

var userAgent = navigator.userAgent.toLowerCase();
var cambioClave="";
var privilegio_cambioEstatus_contrato=true;
var privilegio_editar_contrato=true;
var privilegio_editar_usuarios=true;
var privilegio_editar_estatusUsuarioBackoffice=true;
var privilegio_editar_UsuarioBackoffice=true;
var privilegio_editar_estatusUsuarioContrato=true;
var privilegio_verDatosAdministrador=true;
var privilegio_verDatosCargador=true;
var privilegio_verDatosSupervisor=true;
var privilegio_verDatosAsesor=true;

var privilegio_editDatosAdministrador=true;
var privilegio_editDatosCargador=true;
var privilegio_editDatosSupervisor=true;
var privilegio_editDatosAsesor=true;

var privilegio_addDatosAdministrador=true;
var privilegio_addDatosCargador=true;
var privilegio_addDatosSupervisor=true;
var privilegio_addDatosAsesor=true;
var valCambioClave="";
var valCaptcha="N";
var forgot_pass = "N";
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
        console.log("Entro #btn__lgn_aceptar");
        $("#btn__lgn_aceptar").attr("disabled",true);
        var valido=true;
        if($("#username").get(0).value.isEmpty()){
            if(idioma!="_us_en")
                $("#mensaje").html("Introduzca Usuario!");
                // $("#mensaje").html("Introduzca Usuario!");
            else
                $("#mensaje").html("Enter User!");
            valido=false;
        }else{
            if ($("#pwdClave").get(0).value.isEmpty() ){
                if(idioma!="_us_en")
                    $("#mensaje").html("Introduzca Clave!");
                else
                    $("#mensaje").html("Enter Password!");
                valido=false;
            }
            if (valCaptcha!="N"){
                if ($("#recaptcha_response_field").get(0).value.isEmpty() ){
                    if(idioma!="_us_en")
                        $("#mensaje").html("Introduzca el código de confirmación");
                    else
                        $("#mensaje").html("Enter confirmation code");
                    valido=false;
                }
            }
        }

        if(valido){
            $("#mensaje").html("");
            $("#div_carga_espera_login").removeClass("oculto");
            if (valCaptcha=="N"){                
            console.log("Entro valCaptcha== N");
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
        $("#mensaje").html("");
        $("#mensaje").val("");
        $("#recaptcha_response_field").val("");

    });

    $("#refreshCaptcha").click(function(){
        $("#imgCaptcha").attr('src', $("#imgCaptcha").attr('src')+'?'+Math.random());
        $("#recaptcha_response_field").val("");
    });

    $("#refreshCaptcha2").click(function(){
        $("#imgCaptcha2").attr('src', $("#imgCaptcha2").attr('src')+'?'+Math.random());
        $("#recaptcha_response_field2").val("");
    });

    cargarIdiomaJSONData();

    $(document).bind("contextmenu",function(e){
        return false;
    });

    $("#btn__lgn_regresar").click(function(){
        forgot_pass = "N";
        location.reload();
    });

    // $("#btn__lgn_enviar").click(function(){
    //     //$("#btn__lgn_enviar").attr("disabled",true);
    //     var valido=true;
    //     var correo = $("#correo").val();
    //     if($("#username2").get(0).value.isEmpty()){
    //         if(idioma!="_us_en")
    //             $(".errorForgot").html("Introduzca Usuario!");
    //         else
    //             $(".errorForgot").html("Enter User!");
    //         valido=false;
    //     }else{
    //         if ($("#correo").get(0).value.isEmpty() ){
    //             if(idioma!="_us_en")
    //                 $(".errorForgot").html("Introduzca Correo!");
    //             else
    //                 $(".errorForgot").html("Enter Email!");
    //             valido=false;
    //         }
    //         if ($("#recaptcha_response_field2").get(0).value.isEmpty() ){
    //             if(idioma!="_us_en")
    //                 $(".errorForgot").html("Introduzca el código de confirmación");
    //             else
    //                 $(".errorForgot").html("Enter confirmation code");
    //             valido=false;
    //         }
    //         if(!validarCorreo(correo)){
    //             if(idioma!="_us_en")
    //                 $(".errorForgot").html("Formato de correo invalido");
    //             else
    //                 $(".errorForgot").html("Invalid email format");
    //             valido=false;
    //         }
    //     }
    //     if(valido){
    //         $("#btn__lgn_enviar").attr("disabled",false);
    //         $(".errorForgot").html("");
    //         var msj = "";
    //         if(idioma!="_us_en")
    //             msj = "Se reiniciar\u00e1 su contrase\u00F1a y se le enviar\u00e1 al n\u00FAmero de tel\u00E9fono registrado. \n ¿Desea Continuar?";
    //         else
    //             msj = "Your password will be reset and sent to the registered phone number. \n Do you wish to continue?";
    //
    //         if(confirm(msj)){
    //             $("#div_carga_espera2").removeClass("oculto");
    //             validarCaptcha();
    //         }
    //     }
    //
    // });


});


function login(){
    console.log("Entro login");
    var url = urlValidarUsuario;
    var param={};
    param.username=$("#username").val();
    param.pwdClave=$().crypt({method: "b64enc",source:$("#pwdClave").val()});
    param.IpLocal = $("#IpLocal").val();

    var navegadorWeb="";
    if($.browser.msie){
        navegadorWeb="(Navegador Internet Explorer Version " + $.browser.version+")";
    }
    if($.browser.webkit || $.browser.safari){
        navegadorWeb= "(Navegador Chrome o Safari Version " + $.browser.version+")";
    }
    if($.browser.mozilla){
        navegadorWeb="(Navegador Mozilla Version " + $.browser.version+")";
    }
    if($.browser.opera){
        navegadorWeb="(Navegador Opera Version " + $.browser.version+")";
    }


    var isChrome = /Chrome/.test(navigator.userAgent) && /Google Inc/.test(navigator.vendor);
    var isSafari = /Safari/.test(navigator.userAgent) && /Apple Computer/.test(navigator.vendor);

    if (isChrome)
        navegadorWeb="(Navegador Chrome Version " + $.browser.version+")";

    if (isSafari)
        navegadorWeb="(Navegador Safari Version " + $.browser.version+")";

    if (!!navigator.userAgent.match(/Trident\/7\./))
        navegadorWeb="(Navegador Internet Explorer Version " + $.browser.version+")";


    if (navegadorWeb.length<=0){
        navegadorWeb="";
    }

    param.navegador= navegadorWeb;

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
    result
    console.log("entro loginSuccess")
    if(result.mensaje=="OK"){
        console.log("entro loginSuccess->result.mensaje: ")
        cambioClave=result.cambioPass;
        $("#btn__lgn_aceptar").attr("disabled",false);
        $("#tipo_usuario_app").val(result.tipo);
        $("#tipo_contrato_app").val(result.tipoContrato);
        if(result.backoffice) {
            console.log("entro loginSuccess->result.backoffice: ");
            $("#pdfFATCA").addClass("oculto");
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
            label_idioma="en";
            console.log("loginSuccess->label_idioma: ", label_idioma);
            cambiarIdiomaJSONData(label_idioma);
        }
        //CargarHomeJSONData();

        $(location).attr('href',urlHomeAction);
    }else{
        $("#mensaje").html(result.mensaje);
        $("#btn__lgn_aceptar").attr("disabled",false);
        $("#div_carga_espera_login").addClass("oculto");


        if ((result.mensaje!="User is blocked")) {
            if ((result.mensaje!="Su usuario se encuentra bloqueado")) {
                if ((result.mensaje!="User inactive")) {
                    if ((result.mensaje!="Su usuario se encuentra inactivo")) {
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
    }
}

function validarCaptcha(){
    
    console.log("Entro validarCaptcha");
    var url = urlValidarCaptcha;
    var param={};
    // param.challengeCaptcha=$("#recaptcha_challenge_field").val();
    param.responseCaptcha=$("#recaptcha_response_field").val();
    if(forgot_pass=="S")
        param.responseCaptcha=$("#recaptcha_response_field2").val();
    //param.IpLocal = $("#IpLocal").val();
    sendServiceJSON(url,param,captchaSuccess,null,null);

}

function captchaSuccess(originalRequest){
    
    console.log("Entro captchaSuccess");
    var result = originalRequest;
    
    console.log("Entro captchaSuccess-> ",  result.mensaje);
    if(result.mensaje=="OK"){
        if(forgot_pass == "N")
            login();
        else{
            sendForgotPass();
        }
    }else{
        $("#btn__lgn_aceptar").attr("disabled",false);
        $("#div_carga_espera2").addClass("oculto");
        $("#username").val("");
        $("#pwdClave").val("");
        $("#recaptcha_response_field").val("");
        //Recaptcha.switch_type('image');
        $("#refreshCaptcha").click();
        $("#username").focus();
        if(idioma!="_us_en")
            $("#mensaje").html("Código de confirmación no válido");
        else
            $("#mensaje").html("Invalid confirmation code");
        if(forgot_pass == "S"){
            limpiarcampos();
            if(idioma!="_us_en")
                $(".errorForgot").html("Código de confirmación no válido");
            else
                $(".errorForgot").html("Invalid confirmation code");
        }
    }
}


function detectarPlataforma(){
    var plataforma= navigator.platform;
    if(plataforma == 'Win32' || plataforma == 'Win64' || plataforma == 'Linux' || plataforma == 'MacIntel')
    {
        if(!actualizar_navegador()){
            plataforma="PC";
            //$('.teclado .ui-widget-content .ui-corner-all .ui-keyboard-preview').live("keypress",function(){
            $('.teclado .ui-widget-content .ui-corner-all .ui-keyboard-preview').on('keypress', '', function(){
                BloqueaTecla(event);
            });

/*             $('#pwdClave').keyboard({
                stayOpen : false,
                layout   : 'qwerty',
                lockInput: false,
                restrictInput : true, // Prevent keys not in the displayed keyboard from being typed in
                preventPaste : true,  // prevent ctrl-v and right click
                autoAccept : true
            }); */
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
                if((ItemOpciones.opcion=="0000000029")&& (posPrivilegios=="2")){
                    // No se elimina el boton de editar
                }else{
                    if((ItemOpciones.opcion=="0000000029")&& (posPrivilegios=="4")){
                        // No se elimina el boton de editar
                    }else{

                        if((ItemOpciones.opcion=="0000000003")&& (posPrivilegios=="2")){
                            // No se elimina el boton de editar
                        }else{
                            if((ItemOpciones.opcion=="0000000003")&& (posPrivilegios=="4")){
                                // No se elimina el boton de editar
                            }else{
                                if(ItemPrivilegios!="1")
                                    $("."+ItemOpciones.opcion+"_"+posPrivilegios).remove();
                            }
                        }
                    }
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

            if((ItemOpciones.opcion=="0000000003") && (posPrivilegios=="2")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if(ItemPrivilegios=="0"){
                    privilegio_editar_UsuarioBackoffice=false;
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

            if((ItemOpciones.opcion=="0000000102")){// SI ESTA EN LA OPCION USUARIO BACKOFFICE Y ACCION EDITAR ESTATUS
                if((ItemPrivilegios=="0") && (posPrivilegios=="0")){
                    privilegio_verDatosAsesor=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="1")){
                    privilegio_addDatosAsesor=false;

                }else if((ItemPrivilegios=="0") && (posPrivilegios=="2")){
                    privilegio_editDatosAsesor=false;
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

function forgotPass(){
    forgot_pass = "S";
    $("#login").attr("style","display:none");
    $("#div_carga_forgot").removeClass("oculto");
    setTimeout(function(){
        $("#div_carga_forgot").addClass("oculto");
    }, 1000);
    setTimeout(function(){
        $("#forgotpass").attr("style","display:");
    }, 1000);
}

function sendForgotPass(){
    var data = {'username2':$("#username2").val(), 'correo':$("#correo").val()};
    $.ajax({
        url: urlForgotPass,
        method: 'POST',
        data: data,
        success: function(data){
            if(data.recoverUserExist == "N"){
                limpiarcampos();
                $("#div_carga_espera2").addClass("oculto");
                if(idioma!="_us_en")
                    $(".errorForgot").html("Los datos ingresados son incorrectos.");
                else
                    $(".errorForgot").html("The information you entered is not valid.");
            }else if(data.recoverUserExist == "S"){
                LoginEnviarClaveJSONDataSMS();
            }
        },
        error: function (e) {
            console.log(e.responseText);
        }
    });
}

function LoginEnviarClaveJSONDataSMS(id){
    var url = urlLoginEnviarClaveSMS;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#username2").val();
    jsonParametros[1]= $("#IpLocal").val();

    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON_sinc(url,param,LoginEnviarClaveSMSSuccess,null,null);
}

function LoginEnviarClaveSMSSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    var msj="";

    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        $("#div_carga_espera2").addClass("oculto");

        if(idioma!="_us_en")
            msj = "Su clave provisional ha sido enviada al número de teléfono registrado.";
        else
            msj = "Your temporary password has been sent to the registered telephone number.";

        $("#mensaje_error").html(msj);
        $("#div_mensajes_error").fadeIn("slow");
        $("#cerrar_div_mensaje_error").on("click", function() {
            forgot_pass = "N";
            location.reload();
        });
    }else{
        $("#mensaje_error").empty();
        $("#div_carga_espera2").addClass("oculto");

        if(idioma!="_us_en")
            msj = "Ha ocurrido un error durante el reinicio de la clave, \npor favor comun&iacute;quese con su Asesor Financiero o Ejecutivo de Cuenta.";
        else
            msj = "An error occurred during password reset, \nplease contact your Financial Advisor or Account Executive.";

        $("#mensaje_error").html(msj);
        $("#div_mensajes_error").fadeIn("slow");
        $("#cerrar_div_mensaje_error").on("click", function() {
            limpiarcampos();
        });
    }

}

function limpiarcampos(){
    $("#username2").val("");
    $("#correo").val("");
    $("#recaptcha_response_field2").val("");
    $("#refreshCaptcha2").click();
    $("#username2").focus();
}

function validarCorreo(campo){
    var valido = true;

    emailRegex = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    if (emailRegex.test(campo)) {
        valido = true;
    } else {
        valido = false;
    }
    return valido;
}




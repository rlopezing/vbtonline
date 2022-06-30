var urlLoginOpeEsp="loginOpeEsp.action";
var urlclaveOpeEsp="Security_crearClaveRamdomOpeEsp.action";
var urlvalidarClaveProvisionalOpeEsp="Security_verificarSiClaveProvisonalOpeEsp.action";
var urlCambiarClaveProvisionalOpeEsp="Security_cambiarClaveOpeEspPersonal.action";



$(document).ready(function(){
    activar_teclado_seguridad();
    $('#btn__seguridad_aceptar').click(function(){
        var valido=true;

        if ($("#input_seguridad").get(0).value.isEmpty() ){
            valido=false;
        }
        if(valido)
            loginOpeEspJSONData();

    });

    var invalidos = "\"´`^&ªº:'";


    $("#btn_CambiarPas_aceptar_OpeEsp").click(function(){
        var mensaje="";
        $(".obligatorio_CambiarP_OpeEsp").each(function(){

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

        if(mensaje==""){
            if($("#pwdClave_cambiarP_OpeEsp").val() == $("#pwdClaveNueva_cambiarP_OpeEsp").val()){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The new password must be different to the actual password.\nPlease verify. <br>";
                else
                    mensaje=mensaje+"La nueva clave debe ser diferente a la clave actual.\nPor favor verifique. <br>";
            }
            if($("#pwdClaveNuevaRe_cambiarP_OpeEsp").val() != $("#pwdClaveNueva_cambiarP_OpeEsp").val()){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The new password and confirmation do not match.\nPlease verify. <br>";
                else
                    mensaje=mensaje+"La nueva clave y la confirmaci&oacute;n no coinciden. \nPor favor verifique. <br>";
            }
            if($("#pwdClaveNuevaRe_cambiarP_OpeEsp").get(0).value.length < 8){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The new password must contain at least 8 characters (at least 6 letters and 2 numbers).\nPlease verify. <br>";
                else
                    mensaje=mensaje+"La nueva clave debe tener como m&iacute;nimo 8 caracteres (al menos 6 letras y 2 n&uacute;meros).\nPor favor verifique. <br>";
            }
            if(!passworValido($("#pwdClaveNuevaRe_cambiarP_OpeEsp").val())){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The new password must contain at least 6 letters and 2 numbers.\nPlease verify. <br>";
                else
                    mensaje=mensaje+"La nueva clave debe tener al menos 6 letras y 2 n&uacute;meros.\nPor favor verifique. <br>";
            }
            if(searchChars($("#pwdClaveNuevaRe_cambiarP_OpeEsp").val(),invalidos)){
                if(idioma=="_us_en")
                    mensaje=mensaje+"The new password should not contain special characters.\nPlease verify. <br>";
                else
                    mensaje=mensaje+"La nueva clave no debe tener caracteres especiales.\nPor favor verifique. <br>";
            }
        }

        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            $("#marco_trabajo").css("height","870px");
        }else{

                CambiarClaveOpeEsp();

        }
    });

    $("#btn_CambiarPas_limpiar_OpeEsp").click(function(){
        $("#cambiarClaveOpeEsp .inputFormulario_CambiarP_OpeEsp").each(function(){
            this.value="";
        });
    });

});


function CrearClaveOpeEsp(){
    var url = urlclaveOpeEsp;
    var param={};
    sendServiceJSON(url,param,crearClaveOpeEspSuccess,null,null);
}

function crearClaveOpeEspSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    idioma = result.idioma;
    var mensaje = "";
    $("#login_FIELDPwdClave_OpeEsp").val("");
    $("#FIELDPwdNuevaClave_OpeEsp").val("");
    $("#FIELDPwdNuevaClaveRe_OpeEsp").val("");
    $("#cambiarClaveOpeEsp").attr("style", "display: ");
    $("#div_claveOpeEsp").attr("style", "display: none");

    if(idioma=="_ve_es"){
        mensaje ="Se ha enviado una clave temporal a su direcci&oacute;n de correo electr&oacute;nico";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        mensaje ="We sent a temporary password to your email address";
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}

function CambiarClaveOpeEsp(){
    var url = urlCambiarClaveProvisionalOpeEsp;
    var param={};

    var jsonOpeEsp=[];
    jsonOpeEsp[0]= $("#pwdClaveNuevaRe_cambiarP_OpeEsp").val();
    jsonOpeEsp[1]= $("#pwdClave_cambiarP_OpeEsp").val();

    param.jsonOpeEsp=JSON.stringify({"parametros":jsonOpeEsp});
    sendServiceJSON(url,param,CambiarClaveOpeEspSuccess,null,null);
}

function CambiarClaveOpeEspSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    resultado = result.resultado;
    idioma = result.idioma;
    var mensaje = "";
    if(resultado == "OK"){
        if(idioma=="_us_en"){
            mensaje ="The key for special operations was changed successfully";
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }
        else{
            mensaje ="Se ha cambiado la clave de Operaciones Especiales exitosamente";
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }

        $("#cambiarClaveOpeEsp").attr("style","display: none");
        $("#div_claveOpeEsp").attr("style","display: ");
    }else{
        if(resultado == "NO OK"){
            if(idioma=="_us_en"){
                mensaje ="An error occurred when attempting to change your password for Special Operations. Please check and try again.";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
            else{
                mensaje ="Ha ocurrido un error al intentar cambiar su clave de Operaciones Especiales. Verifique e intente nuevamente.";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }else{
            if(idioma=="_us_en"){
                mensaje ="An error occurred when attempting to change your password for Special Operations:<br>Your password for Special Operations has to be different from your login password you use to enter VBT Online.";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
            else{
                mensaje ="Ha ocurrido un error al intentar cambiar su clave de Operaciones Especiales:<br>La clave de Operaciones Especiales debe ser diferente a la clave de ingreso al VBT Online.";
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }

       // $("#cambiarClaveOpeEsp").attr("style","display: none");
       // $("#div_claveOpeEsp").attr("style","display: ");
    }

}

function loginOpeEspJSONData(){
    var url = urlLoginOpeEsp;
    var param={};
    var jsonLoginOpeEsp=[];
    var Login={};


    param.jsonOpcion=bk_opcion;
    param.jsonLoginOpeEsp=$().crypt({method: "b64enc",source:$("#input_seguridad").val()});

    sendServiceJSON(url,param,LoginOpeEspSuccess,null,null);
}


function LoginOpeEspSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var intentos = result.intentosOpeEsp;
//    alert(result.code);
    if(result.code=="OK"){
        $("#div_mensajes_error").fadeOut("fast");
        seleccionarOpcionMenu(result.jsonOpcion);
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(result.mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        $("#input_seguridad").val("");
        if(intentos==3)
          salir();
    }


}

function validarClaveProvisionalOpeEspJSONData(){
    var url = urlvalidarClaveProvisionalOpeEsp;
    var param={};

    sendServiceJSON(url,param,validarClaveProvisionalOpeEspSuccess,null,null);
}


function validarClaveProvisionalOpeEspSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
//    alert(result.code);
    if(result.cambiar=="S"){
        $("#login_FIELDPwdClave_OpeEsp").val("");
        $("#FIELDPwdNuevaClave_OpeEsp").val("");
        $("#FIELDPwdNuevaClaveRe_OpeEsp").val("");
        $("#cambiarClaveOpeEsp").attr("style","display: ");
        $("#div_claveOpeEsp").attr("style","display: none");
    }else{
        $("#login_FIELDPwdClave_OpeEsp").val("");
        $("#FIELDPwdNuevaClave_OpeEsp").val("");
        $("#FIELDPwdNuevaClaveRe_OpeEsp").val("");
        $("#cambiarClaveOpeEsp").attr("style","display: none");
        $("#div_claveOpeEsp").attr("style","display: ");
    }


}



function activar_teclado_seguridad(){
    var plataforma= navigator.platform;
    if(plataforma == 'Win32' || plataforma == 'Win64' || plataforma == 'Linux' || plataforma == 'MacIntel')
    {
        plataforma="PC";
        $('#input_seguridad').keyboard({
            stayOpen : false,
            layout   : 'qwerty',
            lockInput: true,
            restrictInput : true, // Prevent keys not in the displayed keyboard from being typed in
            preventPaste : true,  // prevent ctrl-v and right click
            autoAccept : true
        });
    }else{
        plataforma="NO-PC";
    }
}







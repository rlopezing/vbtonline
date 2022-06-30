var urlCambiarClave="DesingBank_cambiarClave.action";



var idioma="";
$(document).ready(function(){
    var invalidos = "\"´`^&ªº:'";
   $("#btn_CambiarPas_aceptar").click(function(){
       var mensaje="";
       $(".obligatorio_CambiarP").each(function(){

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

       if(mensaje!=""){

           $("#mensaje_error").empty();
           $("#mensaje_error").html(mensaje);
           $("#div_mensajes_error").fadeIn("slow");
//            $("#marco_trabajo").css("height","870px");
       }else{
           if(idioma=="_us_en"){
               if($("#pwdClave_cambiarP").val() == $("#pwdClaveNueva_cambiarP").val()){
                   mensaje=mensaje+"The new password must be different to the actual password.\nPlease verify. <br>";
               }
               if($("#pwdClaveNuevaRe_cambiarP").val() != $("#pwdClaveNueva_cambiarP").val()){
                   mensaje=mensaje+"The new password and confirmation do not match.\nPlease verify. <br>";
               }
               if($("#pwdClaveNuevaRe_cambiarP").get(0).value.length < 8){
                   mensaje=mensaje+"The new password must contain at least 8 characters (at least 6 letters and 2 numbers).\nPlease verify.. <br>";
               }
               if(!passworValido($("#pwdClaveNuevaRe_cambiarP").val())){
                   mensaje=mensaje+" The new password must contain at least 6 letters and 2 numbers.\nPlease verify. <br>";
               }
               if(searchChars($("#pwdClaveNuevaRe_cambiarP").val(),invalidos)){
                   mensaje=mensaje+"The new password should not contain special characters.\nPlease verify. <br>";
               }

               if(mensaje!=""){
                   $("#mensaje_error").empty();
                   $("#mensaje_error").html(mensaje);
                   $("#div_mensajes_error").fadeIn("slow");
               }else{
//                   cambiarClaveJSONData();
//                  /* showValidationMethods(validationMethod,"metodosValidacionCambiarClave");
//                   $("#cambiarPassword").addClass("oculto");
//                   $("#metodosCambiarClave").removeClass("oculto");  */
                   //Se llama a la pantalla de metodos de validacion
                   if(cambioClave=="N"){
                       mainValidationMethods("cambiarClave");
                   }else{
                       cambiarClaveJSONData();
                   }
               }
           } else{
               if($("#pwdClave_cambiarP").val() == $("#pwdClaveNueva_cambiarP").val()){
                   mensaje=mensaje+"La nueva clave debe ser diferente a la clave actual.\nPor favor verifique. <br>";
               }
               if($("#pwdClaveNuevaRe_cambiarP").val() != $("#pwdClaveNueva_cambiarP").val()){
                   mensaje=mensaje+"La nueva clave y la confirmaci&oacute;n no coinciden. \nPor favor verifique. <br>";
               }
               if($("#pwdClaveNuevaRe_cambiarP").get(0).value.length < 8){
                   mensaje=mensaje+"La nueva clave debe tener como m&iacute;nimo 8 caracteres (al menos 6 letras y 2 n&uacute;meros).\nPor favor verifique. <br>";
               }
               if(!passworValido($("#pwdClaveNuevaRe_cambiarP").val())){
                   mensaje=mensaje+"La nueva clave debe tener al menos 6 letras y 2 n&uacute;meros.\nPor favor verifique. <br>";
               }
               if(searchChars($("#pwdClaveNuevaRe_cambiarP").val(),invalidos)){
                   mensaje=mensaje+"La nueva clave no debe tener caracteres especiales.\nPor favor verifique. <br>";
               }

               if(mensaje!=""){
                   $("#mensaje_error").empty();
                   $("#mensaje_error").html(mensaje);
                   $("#div_mensajes_error").fadeIn("slow");
               }else{
//                   //cambiarClaveJSONData();
//                   showValidationMethods(validationMethod,"metodosValidacionCambiarClave");
//                   $("#cambiarPassword").addClass("oculto");
//                   $("#metodosCambiarClave").removeClass("oculto");
                   if(cambioClave=="N"){
                       mainValidationMethods("cambiarClave");
                   }else{
                       cambiarClaveJSONData();
                   }
               }
           }
       }
   });

    $("#btnCancelarCambioClave").click(function(){
        $("#cambiarPassword").removeClass("oculto");
        $("#metodosCambiarClave").addClass("oculto");
        $("#btn_cambiarMtdClave").click();
        $("#btn_CambiarPas_limpiar").click();
    });





    $("#btn_cambiar_pass").click(function(){
        var mensaje="";
        $(".obligatorio_CambiarPrincipal").each(function(){

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

        if(mensaje!=""){

            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
//            $("#marco_trabajo").css("height","870px");
        }else{
            if(idioma=="_us_en"){
                if($("#pwdClave_cambiarPrincipal").val() == $("#pwdClaveNueva_cambiarPrincipal").val()){
                    mensaje=mensaje+"The new password must be different to the actual password.\nPlease verify. <br>";
                }
                if($("#pwdClaveNuevaRe_cambiarPrincipal").val() != $("#pwdClaveNueva_cambiarPrincipal").val()){
                    mensaje=mensaje+"The new password and confirmation do not match.\nPlease verify. <br>";
                }
                if($("#pwdClaveNuevaRe_cambiarPrincipal").get(0).value.length < 8){
                    mensaje=mensaje+"The new password must contain at least 8 characters (at least 6 letters and 2 numbers).\nPlease verify.. <br>";
                }
                if(!passworValido($("#pwdClaveNuevaRe_cambiarPrincipal").val())){
                    mensaje=mensaje+" The new password must contain at least 6 letters and 2 numbers.\nPlease verify. <br>";
                }
                if(searchChars($("#pwdClaveNuevaRe_cambiarPrincipal").val(),invalidos)){
                    mensaje=mensaje+"The new password should not contain special characters.\nPlease verify. <br>";
                }

                if(mensaje!=""){
                    $("#mensaje_error").empty();
                    $("#mensaje_error").html(mensaje);
                    $("#div_mensajes_error").fadeIn("slow");
                }else{
//                   cambiarClaveJSONData();
//                  /* showValidationMethods(validationMethod,"metodosValidacionCambiarClave");
//                   $("#cambiarPassword").addClass("oculto");
//                   $("#metodosCambiarClave").removeClass("oculto");  */
                    //Se llama a la pantalla de metodos de validacion

                    cambiarClaveJSONDataPrincipal();
                }
            } else{
                if($("#pwdClave_cambiarPrincipal").val() == $("#pwdClaveNueva_cambiarPrincipal").val()){
                    mensaje=mensaje+"La nueva clave debe ser diferente a la clave actual.\nPor favor verifique. <br>";
                }
                if($("#pwdClaveNuevaRe_cambiarPrincipal").val() != $("#pwdClaveNueva_cambiarPrincipal").val()){
                    mensaje=mensaje+"La nueva clave y la confirmaci&oacute;n no coinciden. \nPor favor verifique. <br>";
                }
                if($("#pwdClaveNuevaRe_cambiarPrincipal").get(0).value.length < 8){
                    mensaje=mensaje+"La nueva clave debe tener como m&iacute;nimo 8 caracteres (al menos 6 letras y 2 n&uacute;meros).\nPor favor verifique. <br>";
                }
                if(!passworValido($("#pwdClaveNuevaRe_cambiarPrincipal").val())){
                    mensaje=mensaje+"La nueva clave debe tener al menos 6 letras y 2 n&uacute;meros.\nPor favor verifique. <br>";
                }
                if(searchChars($("#pwdClaveNuevaRe_cambiarPrincipal").val(),invalidos)){
                    mensaje=mensaje+"La nueva clave no debe tener caracteres especiales.\nPor favor verifique. <br>";
                }

                if(mensaje!=""){
                    $("#mensaje_error").empty();
                    $("#mensaje_error").html(mensaje);
                    $("#div_mensajes_error").fadeIn("slow");
                }else{
//
                    cambiarClaveJSONDataPrincipal();
                }
            }
        }
    });

    $("#btn_limpiar_cambiar_pass").click(function(){
        $("#cambiarPasswordPrincipal .inputFormulario_CambiarP").each(function(){
            this.value="";
        });
    });











    $("#btnValidarCambioClave").click(function(){
        var mensaje="";
        var elemento="";

        switch(transferencia_MetodoValidar_Clave){
            case "email":
                elemento="#pwdClaveConfirmTransfer_TOB";
                break;
            case "TarjetaCoordenada":
                elemento="#pwdClaveConfirmTransfer_TOB";
            case "sms":
                elemento="#pwdClaveConfirmSMS";
                break;
        }


        if($(elemento).val()==""){
            if(idioma=="_us_en")
                mensaje = mensaje + "Enter your Transaction Key ";
            else
                mensaje = mensaje + "Ingrese su Clave de Operaciones >";
            $(elemento).addClass("error_campo");
        }else
            $(elemento).removeClass("error_campo");

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            callValidationMethods();
        }
    });


    $("#btn_CambiarPas_limpiar").click(function(){
        $("#cambiarPassword .inputFormulario_CambiarP").each(function(){
            this.value="";
        });
   })

});

function passworValido(nuevoPassword) {
    var cantidadLetras  = 0;
    var cantidadNumeros = 0;

    for (var i=0; i < nuevoPassword.length; i++) {
        caracter = nuevoPassword.charAt(i);
        if ( isAlpha(caracter) ) {
            cantidadLetras = cantidadLetras + 1;
        }
        if ( !isNaN(caracter) ) {
            cantidadNumeros = cantidadNumeros + 1;
        }
    } // end for

    if ( (cantidadLetras < 6) || (cantidadNumeros < 2) ) {
        return false;
    }
    return true;

};

function searchChars(entry, lista) {
    var temp = entry; 	// temporary holder
    var res  = false;
    var i	 = 0;
    while ((i<lista.length) && !res) {
        c = lista.charAt(i);
        if (temp.indexOf(c) != -1) {
            res = true;
        } // end if
        i++;
    } // end while
    return res;
};

function cargarCambiarClave(){

    $(".obligatorio_CambiarP").each(function(){
        $("#"+this.id).val("");
         if($("#"+this.id).hasClass("error_campo"))
            $("#"+this.id).removeClass("error_campo");
    });

}

function cambiarClaveJSONData(){
    var url = urlCambiarClave;
    var param={};


    var jsonDesingBank=[];
    jsonDesingBank[0]= $("#pwdClaveNuevaRe_cambiarP").val();
    jsonDesingBank[1]= $("#pwdClave_cambiarP").val();


    param.jsonDesingBank=JSON.stringify({"parametros":jsonDesingBank});

    sendServiceJSON_sinc(url,param,cambiarClaveSuccess,null,null);
}


function cambiarClaveJSONDataPrincipal(){
    var url = urlCambiarClave;
    var param={};


    var jsonDesingBank=[];
    jsonDesingBank[0]= $("#pwdClaveNuevaRe_cambiarPrincipal").val();
    jsonDesingBank[1]= $("#pwdClave_cambiarPrincipal").val();


    param.jsonDesingBank=JSON.stringify({"parametros":jsonDesingBank});

    sendServiceJSON(url,param,cambiarClaveSuccess,null,null);
}


function cambiarClaveSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    var mensaje = result.mensaje;
    idioma = result.idioma;

    if(respuesta =="OK"){
        cambioClave="N";
//        $("#mensaje_error").html(mensaje);
//        $("#div_mensajes_error").fadeIn("slow");
        $(".inputFormulario_CambiarP").each(function(){
            $("#"+this.id).val("");
        });
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
        seleccionarHome("home");
    }else{
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}








/**
 * LIBRERIA: main.js
 *
 * AUTOR: RIVAS, Ronel
 * DESCRIPCION: Contiene las funciones jQuery generales al proyecto
 */


/**** DESHABILITAR BOTON DERECHO DEL MOUSE ****/
function CancelMouse() {
    var me = window.event;
    if(me != null) {
        if ((me.button == 2)||(me.which == 2)) {
            var errorMsg = "";
            errorMsg += "ERROR:";
            errorMsg += "\nSe han bloqueado los botones secundarios del mouse.";
            alert(errorMsg);
            return false;
        }
    }
    return true;
}

/**** DEFINIR TECLAS PROHIBIDAS ****/
// Definicion de teclas
var keyBACKSPC = 8;
var keyF5 = 116;
var keyF12 = 123;

//DESHABILITAR TECLAS PROHIBIDAS
function CancelKeys(e) {
    var e = e;
    if(e != null) {
        var key = (e.keyCode) ? e.keyCode : e.which;

        if(e.ctrlKey){
            cancelCTRL(key, e);
        } else {
            if(e.altKey){
                cancelALT(key, e);
            } else {
                if(key == keyBACKSPC) {
                   // cancelBACKSPC(e);
                    return true;
                } else {
                    if(key == keyF5) {
                        return  false;
                        //cancelF5(e);
                    } else {
                        if(key == keyF12) {
                            return  false;
                            //cancelF12(e);
                        }
                    }
                }
            }
        }
    }
    return true;
}

// Desactivar el BACKSPC, con excepciones
function cancelBACKSPC(e){
    if(e != null) {
        var keyErrorMsg = "";
        var elementType = document.activeElement.getAttribute('type');
        elementType = elementType.toLowerCase();

        switch (elementType) {
            case "text":
            case "number":
            case "date":
            case "textarea":
            case "email":
            case "password": {
                if((document.activeElement["readOnly"])||(document.activeElement["disabled"])) {
                    break;
                } else {
                    if (document.activeElement.value.length > 0) {
                        /* Realiza el Backspace. */
                        document.activeElement.value.keyCode = keyBACKSPC;
                        e.returnValue = true;
                        return true;
                    }
                }
            }
        }
        keyErrorMsg += "ERROR:";
        keyErrorMsg += "\nSe ha presionado una tecla no permitida: BACKSPC";
        //alert(keyErrorMsg);
        e.returnValue = false;
    }
    return false;
}

// Desactivar el F5
function cancelF5(e){
    alert("Se ha presionado una tecla no permitida: F5");
    e.returnValue = false;
    return false;
}

// Desactivar el F12
function cancelF12(e){
    var keyErrorMsg = "";
    keyErrorMsg += "ERROR:";
    keyErrorMsg += "\nSe ha presionado una tecla no permitida: F12";
    alert(keyErrorMsg);
    e.returnValue = false;
    return false;
}

// Desactivar el ALT, con excepciones
function cancelALT(key, e){
    if((key != null)&&(e != null)) {
        // Lista de todas las combinaciones de CTRL + key a deshabilitar
        // <-, ->, 'a', 'e', 'f', 'h', 't', 'u', '?', F5
        var disabledKeys = new Array(
            37, /* <- */
            39, /* -> */
            65, /* A */
            69, /* E */
            70, /* F */
            72, /* H */
            84, /* T */
            85, /* U */
            188, /* ? */
            keyF5  /* F5 */
            );
        if(e.altKey){
            for ( var i = 0; i < disabledKeys.length; i++) {
                if (key == disabledKeys[i]) {
                    var keyErrorMsg = "";
                    keyErrorMsg += "ERROR:";
                    keyErrorMsg += "\nSe ha presionado una combinacion de teclas no permitida: ";

                    var caracter = String.fromCharCode(key);
                    if(key == 37) caracter = "<-";
                    else if(key == 39) caracter = "->";
                    else if(key == keyF5) caracter = "F5";

                    keyErrorMsg += "\nALT + " + caracter;
                    //alert(keyErrorMsg);

                    e.returnValue = false;
                }
            }
        }
    }
    return true;
}

// Desactivar el CTRL, con excepciones
function cancelCTRL(key, e){
    if((key != null)&&(e != null)) {
        // Lista de todas las combinaciones de CTRL + key a deshabilitar
        // 'a', 'l', 'n', 'o', 'p', 'r', 't', 'u', 'w', F5
        var disabledKeys = new Array(
            65, /* A */
            76, /* L */
            78, /* N */
            79, /* O */
            80, /* P */
            82, /* R */
            84, /* T */
            85, /* U */
            87, /* W */
            keyF5  /* F5 */
            );
        if(e.ctrlKey){
            for ( var i = 0; i < disabledKeys.length; i++) {
                if (key == disabledKeys[i]) {
                    var keyErrorMsg = "";
                    keyErrorMsg += "ERROR:";
                    keyErrorMsg += "\nSe ha presionado una combinacion de teclas no permitida: ";

                    var caracter = String.fromCharCode(key);
                    if(key == keyF5) caracter = "F5";

                    keyErrorMsg += "\nCRLT + " + caracter;
                    //alert(keyErrorMsg);

                    e.returnValue = false;
                }
            }
        }
    }
    return true;
}

// DESHABILITAR TECLAS ESPECIALES EN LA PAGINA
//document.onkeydown = CancelKeys;
//document.onmousedown = CancelMouse;



// Permite saltarse la cache en los enlaces
function generarUrl(url) {
    var cadena = "4d8cb669b2aa7e6280fec9366aaa4cd9=";
    var timestamp = Number(new Date());
    var token = cadena+timestamp;
    var href = url;
    var cad1;
    var cad2;
    if (href != undefined){
        /* Aqui eliminar token */
        if (href.indexOf('4d8cb669b2aa7e6280fec9366aaa4cd9') != -1){
            //Si tengo token
            cad1 = href.substring(0,href.indexOf('4d8cb669b2aa7e6280fec9366aaa4cd9'));
            cad2 = href.substring(href.indexOf('4d8cb669b2aa7e6280fec9366aaa4cd9')+token.length+1);
            href=cad1+cad2;
        }

        /* Reasignar el token si no es un link v��a AJAX */
        if (href != null){
            if (href.indexOf('#') != -1){
                //Si tengo un numeral - para los tabs
                cad1 = href.substring(0,href.indexOf('#'));
                cad2 = href.substring(href.indexOf('#')-1);
                href=cad1;
            }else{
                cad2 = "";
            }
            /* Agrego el token */
            if (href.indexOf('?') == -1){
                href += "?"+token+cad2;
            }else{
                href += "&"+token+cad2;
            }
        }
        return href;
    }
    return "#";
};



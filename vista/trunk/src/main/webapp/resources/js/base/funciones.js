function getFormErrors(form) {
    var errors = new Array();

    // loop thru all form elements
    for (var elementIndex = 0; elementIndex < form.elements.length; elementIndex++) {
        var element = form.elements[elementIndex];

        // text and textarea types
        if (element.type == "text" || element.type == "textarea") {
            element.value = trimWhitespace(element.value)

            // required element
            if (element.required  && element.value == '') {
                errors[errors.length] = element.requiredError;
                //element.value='Requerido...';
                element.style.background='#FFFFC0';
            }

            // maximum length
            else if (element.maxlength && isValidLength(element.value, 0, element.maxlength) == false) {
                errors[errors.length] = element.maxlengthError;
                element.style.background='#FFFFC0';

            }

            // minimum length
            else if (element.minlength && isValidLength(element.value, element.minlength, Number.MAX_VALUE) == false) {
                errors[errors.length] = element.minlengthError;
                element.style.background='#FFFFC0';
            }

            // pattern (credit card number, email address, zip or postal code, alphanumeric, numeric)
            else if (element.pattern) {
                if ( ( (element.pattern.toLowerCase() == 'visa' || element.pattern.toLowerCase() == 'mastercard' || element.pattern.toLowerCase() == 'american express' || element.pattern.toLowerCase() == 'diners club' || element.pattern.toLowerCase() == 'discover' || element.pattern.toLowerCase() == 'enroute' || element.pattern.toLowerCase() == 'jcb' || element.pattern.toLowerCase() == 'credit card') && isValidCreditCard(element.value, element.pattern) == false) ||
                    (element.pattern.toLowerCase() == 'email' && isValidEmailStrict(element.value) == false) ||
                    (element.pattern.toLowerCase() == 'zip or postal code' && isValidZipcode(element.value) == false && isValidPostalcode(element.value) == false) ||
                    (element.pattern.toLowerCase() == 'zipcode' && isValidZipcode(element.value) == false) ||
                    (element.pattern.toLowerCase() == 'postal code' && isValidPostalcode(element.value) == false) ||
                    (element.pattern.toLowerCase() == 'us phone number' &&
                        ( (element.prefix && element.suffix && isValidUSPhoneNumber(element.value, form[element.prefix].value, form[element.suffix].value) == false) || (isValidUSPhoneNumber(element.value) == false) ) ) ||
                    (element.pattern.toLowerCase() == 'alphanumeric' && isAlphanumeric(element.value) == false) ||
                    (element.pattern.toLowerCase() == 'numeric' && isNumeric(element.value) == false) ||
                    (element.pattern.toLowerCase() == 'date' && isValidDate(element.required,element.value) == false) ||
                    (element.pattern.toLowerCase() == 'alphabetic' && isAlphabetic(element.value) == false) ) {
                    errors[errors.length] = element.patternError;
                    element.style.background='#FFFFC0';
                }
                else
                {
                    //element.style.color='gray';
                }
            }
            else
            {
                //element.style.color='gray';
            }

        }

        // password
        else if (element.type == "password") {

            // required element
            if (element.required  && element.value == '') {
                errors[errors.length] = element.requiredError;
                //element.value='Requerido...';
                element.style.background='#FFFFC0';

            }

            // maximum length
            else if (element.maxlength && isValidLength(element.value, 0, element.maxlength) == false) {
                errors[errors.length] = element.maxLengthError;
                // element.value='Requerido...';
                element.style.background='#FFFFC0';

            }

            // minimum length
            else if (element.minlength && isValidLength(element.value, element.minlength, Number.MAX_VALUE) == false) {
                errors[errors.length] = element.minLengthError;
                //element.value='Requerido...';
                element.style.background='#FFFFC0';

            }
            //else
            // element.style.color='gray';

        }

        // file upload
        if (element.type == "file") {

            // required element
            if (element.required  && element.value == '') {
                errors[errors.length] = element.requiredError;
                //element.value='Requerido...';
                element.style.background='#FFFFC0';

            }
            /*else
             element.style.color='gray';*/
        }

        // select
        else if (element.type == "select-one" || element.type == "select-multiple" || element.type == "select") {

            // required element
            if (element.required && element.selectedIndex == -1) {
                errors[errors.length] = element.requiredError;
                element.style.background='#FFFFC0';
            }

            // disallow empty value selection
            else if (element.disallowEmptyValue && element.options[element.selectedIndex].value == '') {
                errors[errors.length] = element.disallowEmptyValueError;
                element.style.background='#FFFFC0';
            }
            /*else
             element.style.color='gray';*/

        }

        // radio buttons
        else if (element.type == "radio") {

            // required element
            if (element.required && element.length) {
                var checkedRadioButton = -1;
                for (var radioIndex = 0; radioIndex < element.length; radioIndex++) {
                    if (element[radioIndex].checked == true) {
                        checkedRadioButton = radioIndex;
                        break;
                    }
                }
                if (checkedRadioButton == -1) {
                    errors[errors.length] = element.requiredError;
                    element.style.background='#FFFFC0';
                }
            }
            /*else
             element.style.color='gray';*/

        }
    } // end for

    return errors;
}


// Check that the number of characters in a string is between a max and a min
function isValidLength(string, min, max) {
    if (string.length < min || string.length > max) return false;
    else return true;
}

// Check that an email address is valid based on RFC 821 (?)
function isValidEmail(address) {
    if (address.indexOf('@') < 3) return false;
    var name = address.substring(0, address.indexOf('@'));
    var domain = address.substring(address.indexOf('@') + 1);
    if (name.indexOf('(') != -1 || name.indexOf(')') != -1 || name.indexOf('<') != -1 || name.indexOf('>') != -1 || name.indexOf(',') != -1 || name.indexOf(';') != -1 || name.indexOf(':') != -1 || name.indexOf('\\') != -1 || name.indexOf('"') != -1 || name.indexOf('[') != -1 || name.indexOf(']') != -1 || name.indexOf(' ') != -1) return false;
    if (domain.indexOf('(') != -1 || domain.indexOf(')') != -1 || domain.indexOf('<') != -1 || domain.indexOf('>') != -1 || domain.indexOf(',') != -1 || domain.indexOf(';') != -1 || domain.indexOf(':') != -1 || domain.indexOf('\\') != -1 || domain.indexOf('"') != -1 || domain.indexOf('[') != -1 || domain.indexOf(']') != -1 || domain.indexOf(' ') != -1) return false;
    return true;
}

// Check that an email address has the form something@something.something
// This is a stricter standard than RFC 821 (?) which allows addresses like postmaster@localhost
function isValidEmailStrict(address) {
    if (isValidEmail(address) == false) return false;
    var domain = address.substring(address.indexOf('@') + 1);
    if (domain.indexOf('.') == -1) return false;
    if (domain.indexOf('.') == 0 || domain.indexOf('.') == domain.length - 1) return false;
    return true;
}

// Check that a string contains only letters and numbers
function isAlphanumeric(string) {
    if (string.search) {

        if (string.search(/[^(A-Za-z0-9._)]/) != -1)
            return false;
    }
    return true;
}

function isAlphanumericWithSpace(string) {
    if (string.search) {

        if (string.search(/[^(A-Za-z0-9.,áéíóúñÑ \-\+)]/) != -1)
            return false;
    }
    return true;
}

function esSoloLetrasNumeros(string) {
    if (string.search) {

        if (string.search(/[^A-Za-z0-9]/) != -1)
            return false;
    }
    return true;
}

function isLogin(string) {
    if (string.search) {

        if (string.search(/^[A-Za-z][A-Za-z0-9._]+[^._]$/) != -1)
            return true;
    }
    return false;
}

// Check that a string contains only letters
function isAlphabetic(string) {
    if (string.search) {
        if (string.search(/[^a-zA-Z\s]/) != -1)
            return false;
        //if (!string.search(/^[a-zA-Z0-9]+$/)) return false;
    }
    return true;
}

function isAlphabeticPunto(string) {
    if (string.search) {
        if (string.search(/[^a-zA-Z.\s]/) != -1)
            return false;
        //if (!string.search(/^[a-zA-Z0-9]+$/)) return false;
    }
    return true;
}

// Check that a string contains only numbers
function isNumeric(string) {

    if (string.search) {
        if (string.search(/[^(0-9,.)]/) != -1)
            return false;
    }
    return true;
}

// Check that a string contains only numbers
function esSoloNumeros(string) {

    if (string.search) {
        if (string.search(/[^0-9]/) != -1)
            return false;
    }
    return true;
}

// Remove characters that might cause security problems from a string
function removeBadCharacters(string) {
    if (string.replace) {
        string.replace(/[<>\"\'%;\)\(&\+]/, '');
    }
    return string;
}

// Remove all spaces from a string
function removeSpaces(string) {
    var newString = '';
    for (var i = 0; i < string.length; i++) {
        if (string.charAt(i) != ' ') newString += string.charAt(i);
    }
    return newString;
}

// Remove leading and trailing whitespace from a string
function trimWhitespace(string) {
    var newString  = '';
    var substring  = '';
    beginningFound = false;

    // copy characters over to a new string
    // retain whitespace characters if they are between other characters
    for (var i = 0; i < string.length; i++) {

        // copy non-whitespace characters
        if (string.charAt(i) != ' ' && string.charCodeAt(i) != 9) {

            // if the temporary string contains some whitespace characters, copy them first
            if (substring != '') {
                newString += substring;
                substring = '';
            }
            newString += string.charAt(i);
            if (beginningFound == false) beginningFound = true;
        }

        // hold whitespace characters in a temporary string if they follow a non-whitespace character
        else if (beginningFound == true) substring += string.charAt(i);
    }
    return newString;
}

// Returns a checksum digit for a number using mod 10
function getMod10(number) {

    // convert number to a string and check that it contains only digits
    // return -1 for illegal input
    number = '' + number;
    number = removeSpaces(number);
    if (!isNumeric(number)) return -1;

    // calculate checksum using mod10
    var checksum = 0;
    for (var i = number.length - 1; i >= 0; i--) {
        var isOdd = ((number.length - i) % 2 != 0) ? true : false;
        digit = number.charAt(i);

        if (isOdd) checksum += parseInt(digit);
        else {
            var evenDigit = parseInt(digit) * 2;
            if (evenDigit >= 10) checksum += 1 + (evenDigit - 10);
            else checksum += evenDigit;
        }
    }
    return (checksum % 10);
}

function isValidDate(daterequired,dateStr) {
// Date validation function courtesty of
// Sandeep V. Tamhankar (stamhankar@hotmail.com) -->

// Checks for the following valid date formats:
// MM/DD/YY   MM/DD/YYYY   MM-DD-YY   MM-DD-YYYY


    if (daterequired ==false)
        return true;

    var datePat = /^(\d{1,2})(\/|-)(\d{1,2})\2(\d{4})$/; // requires 4 digit year
    var matchArray = dateStr.match(datePat); // is the format ok?

    if (matchArray == null) {
        return false;
    }

    month = matchArray[3]; // parse date into variables
    day = matchArray[1];
    year = matchArray[4];
    if (day < 1 || day > 31) {
        return false;
    }

    if (month < 1 || month > 12) { // check month range
        return false;
    }

    if ((month==4 || month==6 || month==9 || month==11) && day==31) {
        return false;
    }

    if (month == 2) { // check for february 29th
        var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
        if (day>29 || (day==29 && !isleap)) {
            return false;
        }

    }
    return true;
}


function isEmail (s)
{   if (isEmpty(s))
    if (isEmail.arguments.length == 1) return defaultEmptyOK;
    else return (isEmail.arguments[1] == true);

    // is s whitespace?
    if (isWhitespace(s)) return false;

    // there must be >= 1 character before @, so we
    // start looking at character position 1
    // (i.e. second character)
    var i = 1;
    var sLength = s.length;

    // look for @
    while ((i < sLength) && (s.charAt(i) != "@"))
    { i++
    }

    if ((i >= sLength) || (s.charAt(i) != "@")) return false;
    else i += 2;

    // look for .
    while ((i < sLength) && (s.charAt(i) != "."))
    { i++
    }

    // there must be at least one character after the .
    if ((i >= sLength - 1) || (s.charAt(i) != ".")) return false;
    else return true;
}

/**********************************  Nuevas  ************************************/

// FUNCION replaceChars(entry, lista): elimina los caracteres de "lista" del string "entry"
function replaceChars(entry, lista) {
    var add = ""; 			// with this
    var temp = entry; 	// temporary holder

    for (var i=0; i<lista.length; i++) {
        out = lista.charAt(i); // eliminate this
        while (temp.indexOf(out)>-1) {
            pos  = temp.indexOf(out);
            temp = "" + (temp.substring(0, pos) + add + temp.substring((pos + out.length), temp.length));
        } // end while
    } // end for
    return temp;
};

// FUNCION TlfNoValido(str): VERDAD si str no contiene caracteres validos para un tlf (0..9,(,),-," ",.), FALSO ecc.
function TlfNoValido(str) {
    var tlf = '0123456789()- .+';
    var i = 0;
    while (i < str.length) {
        if (tlf.indexOf(str.charAt(i)) == -1) return true;
        i++;
    }
}; // END TlfNoValido(str) =====

// Funci\u00f3n para mostrar y ocultar elementos englobados en un <span>
function showhide(what){
    if (what.style.display=='none') {
        what.style.display='';
        window.event.srcElement.src = '/vbtonline/images/comun/nolines_minus.gif';
    } else {
        what.style.display='none';
        window.event.srcElement.src = '/vbtonline/images/comun/nolines_plus.gif';
    }
};

/**
 * FUNCION:  Cambiar_Fondo(fila, color)
 * OBJETIVO: Le asigna el color "color" al fondo de la fila "fila" de una tabla.
 */
function Cambiar_Fondo(fila, color) {
    if (document.all) {
        fila.style.backgroundColor = color;
    }
    return true;
};

/**
 * FUNCION:  Marcar_Todos(f)
 * OBJETIVO: Selecciona todos los elementos de tipo "checkbox" en la forma "f".
 */
function Marcar_Todos(f, language_) {
    var i, cVariable;
    for (i = 0; i < f.elements.length;i++) {
        cVariable = f.elements[i];
        if (cVariable.name != "chkTodos" && cVariable.type == "checkbox")
            cVariable.checked = f.chkTodos.checked;
    }
    if ( f.chkTodos.checked ) {
        if (language_ == 'es') {
            f.chkTodos.title = "Des" + f.chkTodos.title.substr(1);
        } else {
            f.chkTodos.title = "Unc" + f.chkTodos.title.substr(1);
        }
    } else {
        if (language_ == 'es') {
            f.chkTodos.title = "S" + f.chkTodos.title.substr(3);
        } else {
            f.chkTodos.title = "C" + f.chkTodos.title.substr(3);
        }
    }
};

/**
 * FUNCION:  Desmarcar(chkT, chkA)
 * OBJETIVO: Marca el checkbox "chkT" si todos los checkbox "chkA" estan marcados.
 *           Desmarca el checkbox "chkT" en caso contrario.
 */
function Desmarcar(chkT, chkA, language_) {

    var blnTodos = true;

    if (chkA.length != null) {		// Si hay mas de un checkbox
        for (var i=0; i<chkA.length; i++) {	//Determinamos si seleccion\u00f3 todos los items para eliminar
            if ( ! chkA[i].checked && blnTodos) {
                blnTodos = false;
            }
        };
    }
    else {		// Si hay un s\u00f3lo checkBox
        if ( ! chkA.checked)	{
            blnTodos = false;
        }
    } // end if else

    if ( blnTodos ) {
        if ( ! chkT.checked ) {
            if (language_ == 'es') {
                chkT.title = "Des" + chkT.title.substr(1);
            } else {
                chkT.title = "Unc" + chkT.title.substr(1);
            }
        }
        chkT.checked = true;
    } else {
        if ( chkT.checked ) {
            if (language_ == 'es') {
                chkT.title = "S" + chkT.title.substr(3);
            } else {
                chkT.title = "C" + chkT.title.substr(3);
            }
        }
        chkT.checked = false;
    }
};

/**
 *FUNCION Mostrar(iden, title): Muestra/Oculta el div="iden" con el tooltip="title"
 */
function Mostrar(iden, title, language_) {
    var elem = document.getElementById(iden);
    if (elem.style.display =='') {
        elem.style.display = 'none';
        window.event.srcElement.src = '/vbtonline/images/comun/nolines_plus.gif';
        if (language_ == 'es') {
            window.event.srcElement.title = 'Abrir ' + title;
        } else {
            window.event.srcElement.title = 'Open ' + title;
        }
    } else {
        elem.style.display='';
        window.event.srcElement.src = '/vbtonline/images/comun/nolines_minus.gif';
        if (language_ == 'es') {
            window.event.srcElement.title = 'Cerrar ' + title;
        } else {
            window.event.srcElement.title = 'Close ' + title;
        }
    }
};  // END function Mostrar(iden, title)

/**
 *FUNCION verificar_Eliminar(frm): Funci\u00f3n usada en Usuarios.jsp para verificar y confirmar la eliminaci\u00f3n de usuarios.
 */
function verificar_Eliminar(f,MsgBorrarSingular_,MsgBorrarPlural_,MsgEliminarError1_,MsgEliminarError2_) {

    var blnSelecciono = false;
    var cuantos = 0;

    var MsgAdvertencia = "";
    var MsgBorrarSingular = MsgBorrarSingular_;
    var MsgBorrarPlural   = MsgBorrarPlural_;
    if (f.chkLista != null) {

        if (f.chkLista.length != null) {		// Si hay mas de un checkBox
            for (var i=0; i<f.chkLista.length; i++) {	//Contamos cuantos usuarios seleccion\u00f3 para eliminar
                if (f.chkLista[i].checked) {
                    cuantos ++;
                }
            };
            for (var i=0; i<f.chkLista.length; i++) {
                if (f.chkLista[i].checked) {
                    blnSelecciono = true;
                    break;
                }
            };
        }
        else {		// Si hay un s\u00f3lo checkBox
            if (f.chkLista.checked)	{
                blnSelecciono = true;
                cuantos = 1;
            }
        }

        if (blnSelecciono) {
            if (cuantos==1) {
                return(confirm(MsgAdvertencia + MsgBorrarSingular));
            } else {
                return(confirm(MsgAdvertencia + MsgBorrarPlural));
            }
        }
        else {
            alert (MsgEliminarError1_);
            return false;
        }
    } // end if (f.chkLista != null)

    alert(MsgEliminarError2_);
    return false;

}; // end function verificar_EliminarUsuario(f)

/**
 *FUNCION confirmar_accion(Accion, Status, MsgBorrarOk_, MsgModificarOk_, MsgAgregarOk_): Confirma la eliminaci\u00f3n, ingreso y modificaci\u00f3n.
 */
function confirmar_accion(Accion, Status, MsgBorrarOk_, MsgModificarOk_, MsgAgregarOk_) {
    if ( Status == "true" ) {
        if ( Accion == "Borrar" ) {
            alert(MsgBorrarOk_);
        }
        else if ( Accion == "Agregar" ) {
            alert(MsgAgregarOk_);
        }
        else if ( Accion == "Modificar" ) {
            alert(MsgModificarOk_);
        }
    }
    return true;
};  // END function confirmar_accion(Accion, Status, MsgBorrarOk_, MsgModificarOk_, MsgAgregarOk_)

/**
 *FUNCION isDigit(theDigit): verifica que 'theDigit' es un digito.
 */
function isDigit(theDigit)
{
    var digitArray = new Array('0','1','2','3','4','5','6','7','8','9'),j;

    for (j = 0; j < digitArray.length; j++)
    {if (theDigit == digitArray[j])
        return true
    }
    return false

} // END function isDigit(theDigit)

/**
 *FUNCION isPositiveInteger(theString): verifica si un string dado es un número entero positivo.
 */
function isPositiveInteger(theString)
{
    var theData = new String(theString)

    if (!isDigit(theData.charAt(0)))
        if (!(theData.charAt(0)== '+'))
            return false

    for (var i = 1; i < theData.length; i++)
        if (!isDigit(theData.charAt(i)))
            return false
    return true
} // END function isPositiveInteger(theString)

/**
 *FUNCION isDate(s,f): verifica que la fecha 's' sea valida en el formato definido por 'f'.
 */
function isDate(s,f) {
    var a1=s.split("/");
    var e=true;
    if (a1.length!=3) {
        e=false;
    }
    else {
        if (a1.length==3)
            var na=a1;
        if (isPositiveInteger(na[0]) && isPositiveInteger(na[1]) && isPositiveInteger(na[2])) {
            if (f==1) {
                var d=na[1],m=na[0];
            }
            else {
                var d=na[0],m=na[1];
            }
            var y=na[2];
            if (((e) && (y<1000)||y.length>4))
                e=false
            if (e) {
                v=new Date(m+"/"+d+"/"+y);
                if (v.getMonth()!=m-1)
                    e=false;
            }
        }
        else {
            e=false;
        }
    }
    return e
};  // END function isDate(s,f)


/**
 *FUNCION checkDate(v,canbenull,msg): verifica que el valor del campo 'v' sea una fecha correcta. Si es incorrecta muestra el mensaje de error 'msg'.
 */
function checkDate(v,canbenull,msg) {
    var s=v.value;
    if (canbenull == 'yes') {
        if (s.length==0)
            return true;
    }
    if (!isDate(s,0)) { //dd/mm/yyyy format
        alert(msg);
        v.value='';
        return false;
    }
    return true;
};  // END function checkDate(v,canbenull,msg)

/**
 *FUNCION limpiar_forma(frm): Funci\u00f3n usada para limpiar o resetear los campos de un formulario
 */
function limpiar_forma(frm) {
    var primero = -1;

    // loop thru all form elements
    for (var elementIndex = 0; elementIndex < frm.elements.length; elementIndex++) {
        var element = frm.elements[elementIndex];

        // text and textarea types
        if (element.type == "text" || element.type == "textarea") {
            element.value = '';
            if (primero == -1)
                primero = elementIndex;
        }
        // password
        else if (element.type == "password") {
            element.value = '';
            if (primero == -1)
                primero = elementIndex;
        }
        // file upload
        else if (element.type == "file") {
            element.value = '';
            if (primero == -1)
                primero = elementIndex;
        }
        // select
        else if ((!element.disabled) && (element.type == "select-one" || element.type == "select-multiple" || element.type == "select")) {
            element.selectedIndex = 0;
            if (primero == -1)
                primero = elementIndex;
        }
        // radio buttons
        else if (element.type == "radio") {
            if (primero == -1)
                primero = elementIndex;
        }
        // checkbox
        else if (element.type == "checkbox") {
            if(element.checked) { element.click();}
            if (primero == -1)
                primero = elementIndex;
        }
    } // end for
    if (primero != -1)
    {
        frm.elements[primero].focus();
    }
};  // END function limpiar_forma(frm)

// FUNCION searchChars(entry, lista): verifica si los caracteres de "lista" estpan en el string "entry"
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

function getFormInfo() {
    myParams = "";

    for(i = 0; i < document.forms[0].elements.length; i++) {
        fe = document.forms[0].elements[i];
        switch(fe.type) {
            case "hidden":
                myParams+= "&" +fe.name + "=" + fe.value;
                break;
            case "password":       break;
            case "text":           myParams+= "&" +fe.name + "=" + fe.value;
                break;
            case "textarea":       myParams+= "&" + fe.name + "= " + fe.value;
                break;
            case "select-multiple":
            case "select-one":
                myNotFirst = false;
                myParams +=  "&" + fe.name + "="
                for(j = 0; j < fe.options.length; j++) {
                    if(fe.options[j].selected) {
                        if(myNotFirst) {
                            myParams += ", " + fe.options[j].value;
                        } else {
                            myNotFirst = true;
                            myParams += fe.options[j].value;
                        }
                    }
                }
                break;
            case "radio":
                myParams +=  "&" + fe.name + "=";
                if(fe.checked) {
                    myParams += fe.value;
                }
                break;
            case "checkbox":
                if(fe.checked) {
                    myParams +=  "&" + fe.name + "=" + fe.value + "\n";
                }
                break;
        }
    }
    return(myParams);
}

/**
 * FUNCION:  countChars(elem, limite, msg)
 * OBJETIVO: Restringe la entrada hasta "limite" caracteres en el elemento "elem" (text \u00f3 textarea).
 **/
function countChars(elem, limite, msg) {
    var cadena = elem.value;
    if (cadena.length > limite) {
        elem.value = cadena.substring(0,limite);
        alert(msg);
        elem.focus(); return false;
    }
};

/**
 * FUNCION openPopup(htmlurl): abre una nueva ventana con el htmlurl especificado.
 * Se usa asi: <a href="mipagina.htm" onClick='return openPopup(this.href, "miNombre", "yes", 600, 500,'yes')'>Mi enlace</A>
 **/
function openPopup(url, nombre, sino, ancho, alto, maximizar) {
    var newWin=window.open(url, nombre, "toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars="+sino+", width="+ancho+", height="+alto+", resizable="+maximizar);
    newWin.focus();
    return false;
};

/**
 * FUNCION contarChequeados(checkLista): retorna la cantidad de checkbox activados para el campo "checkLista".
 **/
function contarChequeados(checkLista) {
    var cuantos = 0;
    if (checkLista != null) {
        if (checkLista.length != null) {		// Si hay mas de un checkBox
            for (var i=0; i<checkLista.length; i++) {	//Contamos cuantos usuarios seleccion\u00f3 para eliminar
                if (checkLista[i].checked) {
                    cuantos ++;
                }
            };
            for (var i=0; i<checkLista.length; i++) {
                if (checkLista[i].checked) {
                    blnSelecciono = true;
                    break;
                }
            };
        }
        else {		// Si hay un s\u00f3lo checkBox
            if (checkLista.checked) {
                blnSelecciono = true;
                cuantos = 1;
            }
        }
    }
    return cuantos;
};

/**
 *FUNCION FechaMayorIgual(fecha1_,fecha2_): VERDAD si fecha1_ es mayor que fecha2_, FALSO ecc.
 */
function FechaMayorIgual(fecha1_,fecha2_) {
    dia1    = fecha1_.substring(0,fecha1_.indexOf("/"));
    fecha1_ = fecha1_.substring((fecha1_.indexOf("/")+1),fecha1_.length);
    mes1    = fecha1_.substring(0,fecha1_.indexOf("/"));
    ano1    = fecha1_.substring((fecha1_.indexOf("/")+1),fecha1_.length);
    fecha1_ = ano1 + "/" + mes1 + "/" + dia1;

    var fecha1 = new Date (fecha1_);

    dia2    = fecha2_.substring(0,fecha2_.indexOf("/"));
    fecha2_ = fecha2_.substring((fecha2_.indexOf("/")+1),fecha2_.length);
    mes2    = fecha2_.substring(0,fecha2_.indexOf("/"));
    ano2    = fecha2_.substring((fecha2_.indexOf("/")+1),fecha2_.length);
    fecha2_ = ano2 + "/" + mes2 + "/" + dia2;

    var fecha2 = new Date (fecha2_);

    if (fecha1>=fecha2) {return true;}
    else {return false;}
}; // END function FechaMayorIgual(aaaa1,mm1,dd1,aaaa2,mm2,dd2)

function StatusAnimado(n,mensaje1,mensaje2)
{
    var i, j, k = 0, p = 0, q;
    var loop = 1; /* Set this to 1 to loop, 0 not to loop */
    var tmp;
    var stop = 0;


    StatusAnimado2(i,j,k,p,q,loop,tmp,stop,mensaje1,mensaje2,n);
};

/**
 *FUNCION StatusAnimado(n,mensaje1,mensaje2): Mustra siempre el mismo status, rotando alternando los mensajes 'mensaje1' y 'mensaje2'.
 */
function StatusAnimado2(i,j,k,p,q,loop,tmp,stop,mensaje1,mensaje2,n)
{
    m = Array (2);
    m[0] = mensaje1;
    m[1] = mensaje2;
    var msg  = " ";

    if (stop == 1)
    {
        k = 0;
        p = 0;
        window.status = msg;
        stop = 0;
        return;
    }
    if (n < m[p].length + 1)
    {
        if (k == 0) i = n;
        else i = m[p].length - n;
        n++;
        tmp = m[p].substring (0, i);
        window.status = (tmp.length > 0) ? tmp : " ";
        var cmd = "StatusAnimado2("+i+","+j+","+k+","+p+","+q+","+loop+",'"+tmp+"',"+stop+",'"+mensaje1+"','"+mensaje2+"',"+n+")";
        timerTwo = window.setTimeout (cmd, 100);
    }
    else
    {
        n = 1;
        if (k == 0) k = 1;
        else
        {
            if (loop == 0)
            {
                if (p < m.length - 1)
                {
                    k = 0;
                    p++;
                }
                else
                {
                    window.status = msg;
                    n = m[p].length + 1;
                    k = 0;
                    p = 0;
                    return;
                }
            }
            else
            {
                k = 0;
                if (p < m.length - 1) p++;
                else p = 0;
            }
        }
        var cmd = "StatusAnimado2("+i+","+j+","+k+","+p+","+q+","+loop+",'"+tmp+"',"+stop+",'"+mensaje1+"','"+mensaje2+"',"+n+")";
        timerTwo = window.setTimeout (cmd, 100);
    }
};

/**
 *FUNCION passworValido(nuevoPassword): true si nuevoPassword tiene al menos 6 letras y al menos 2 números, false en caso contrario.
 */
function passworValido(nuevoPassword) {
    var cantidadLetras  = 0;
    var cantidadNumeros = 0;

    for (var i=0; i < nuevoPassword.length; i++) {
        caracter = nuevoPassword.charAt(i);
        if ( isAlphabetic(caracter) ) {
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

function unFormatCurrency (inputstring, charDec) {
    var charSep;
    if((charDec =="") || (charDec == null))
        charDec = ".";

    if(charDec.indexOf('.') != -1)
    {
        charDec = ".";
        charSep = ",";
    }
    else
    {
        charDec = ",";
        charSep = ".";
    }

    var fixedCurrency="";
    for (var x = 0; x < inputstring.length; x++)
    {
        var ch = inputstring.charAt(x);
        if (ch != charSep)
        {
            fixedCurrency+= ch;
        }
    }

    if(charDec.indexOf('.') != -1)
    {
        fixedCurrency.replace(charDec,'.');
    }
    return fixedCurrency;
}

function formatMoneda(num, decSep, thousandSep, decLength)
{
    if(num == '') return '';
    var arg, entero, decLengthpow, sign = true;cents = '';
    if(typeof(num) == 'undefined') return;
    if(typeof(decLength) == 'undefined') decLength = 2;
    if(typeof(decSep) == 'undefined') decSep = ',';
    if(typeof(thousandSep) == 'undefined') thousandSep = '.';
    if(thousandSep == '.') arg=/\./g;
    else if(thousandSep == ',') arg=/\,/g;
    if(typeof(arg) != 'undefined') num = num.toString().replace(arg, '');
    num = num.toString().replace(/,/g,'.');
    if(num.indexOf('.') != -1)
        entero = num.substring(0, num.indexOf('.'));
    else
        entero = num;
    if(isNaN(num))
        return "0";
    if(decLength > 0)
    {
        decLengthpow = Math.pow(10, decLength);
        sign = (num == (num = Math.abs(num)));
        num = Math.round(num * decLengthpow);
        cents = num % decLengthpow;
        num = Math.floor(num / decLengthpow).toString();
        if(cents < 10)
            cents = "0" + cents;
    }
    if(thousandSep != '')
    { for(var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) num = num.substring(0, num.length - (4 * i + 3)) + thousandSep + num.substring(num.length - (4 * i + 3)); }	else
    { for(var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) num = num.substring(0, num.length - (4 * i + 3)) + num.substring(num.length - (4 * i + 3));
    }	if(decLength > 0) return (((sign) ? '' : '-') + num + decSep + cents); else return (((sign) ? '' : '-') + num);
}

function onlyDigits(e, value, allowDecimal, allowNegative, allowThousand, decSep, thousandSep, decLength){
    var _ret = true, key;
    if(window.event) { key = window.event.keyCode; isCtrl = window.event.ctrlKey }
    else if(e) { if(e.which) { key = e.which; isCtrl = e.ctrlKey; }}
    if(key == 8) return true;
    if(isNaN(key)) return true;
    if(key < 44 || key > 57) { return false; }
    keychar = String.fromCharCode(key);
    if(decLength == 0) allowDecimal = false;
    if(!allowDecimal && keychar == decSep || !allowNegative && keychar == '-' || !allowThousand && keychar == thousandSep) return false;
    return _ret;}

function formatCurrency(num, markThousands, decLength, decSep)
{
    if(num == '') return formatCurrency("0", markThousands, decLength, decSep);
    var res = num;
    var arg, entero, decLengthpow, sign = true;cents = '';

    if (decSep == '.') {
        thousandSep = ',';
    }
    else {
        decSep = ',';
        thousandSep = '.';
    }

    if(typeof(num) == 'undefined') return formatCurrency("0", markThousands, decLength, decSep);
    if(typeof(decLength) == 'undefined') decLength = 2;
    if(typeof(decSep) == 'undefined') decSep = ',';
    if(typeof(thousandSep) == 'undefined') thousandSep = '.';
    if(thousandSep == '.') arg=/\./g;
    else if(thousandSep == ',') arg=/\,/g;
    if(typeof(arg) != 'undefined') num = num.toString().replace(arg, '');
    num = num.toString().replace(/,/g,'.');
    if(num.indexOf('.') != -1)
        entero = num.substring(0, num.indexOf('.'));
    else
        entero = num;
    if(isNaN(num))
        return "0";
    if(decLength > 0)
    {
        decLengthpow = Math.pow(10, decLength);
        sign = (num == (num = Math.abs(num)));
        num = Math.round(num * decLengthpow);
        cents = num % decLengthpow;
        num = Math.floor(num / decLengthpow).toString();
        if(cents < 10)
            cents = "0" + cents;
    }
    if(thousandSep != '')
    { for(var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) num = num.substring(0, num.length - (4 * i + 3)) + thousandSep + num.substring(num.length - (4 * i + 3)); }	else
    { for(var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++) num = num.substring(0, num.length - (4 * i + 3)) + num.substring(num.length - (4 * i + 3));
    }	if(decLength > 0) res = (((sign) ? '' : '-') + num + decSep + cents); else res = (((sign) ? '' : '-') + num);

    if (!markThousands) {
        res = res.replace(thousandSep,'');
    }
    return res;
}

function lpad(str,c,len)
{
    var res = str;

    if (str != "") {
        while (res.length < len) {
            res = c + res;
        }
    }
    return res;
}

/*** Funciona para los números de carteras ***/
function lpad2(str,c,len)
{
    var res = str;

    if (str != "" && res.length >= 5) {
        while (res.length < len) {
            res = c + res;
        }
    }
    return res;
}

// **** Abrir Ventana Derecha ****
// javascript:abreVentanaDer('url','nome','w','h','t','scroll')
function abreVentanaDer(url,name,w,h,t,scroll) {
    x = (window.screen.width) - (+w+ + 10); // largo de la pantalla menos largo de la ventana (mas 5 pixeis borders)
    popupVen = window.open(url, name, 'menubar=0,toolbar=0,location=0,directories=0,status=1,scrollbars='+scroll+',resizable=0,width='+w+',height='+h+',left='+x+',top='+t+'');
}

function isEmpty(value) {
    if ((value == null) || (value == '')|| (value == undefined)) {
        return true;
    } else {
        return false;
    }
}
var logOut="/vbtonline/Login_logOut.action";
var quiniela="/vbtonline/Quiniela_input.action";
var crearUsuario="/vbtonline/usuario_init.action";
var olvidoContra="/vbtonline/usuario_init1.action";

String.prototype.capitalize = function() {
    return this.replace(/\S+/g, function(a){
        return a.charAt(0).toUpperCase() + a.slice(1).toLowerCase();
    });
};

String.prototype.reducirCadena = function(tamano) {
    if (this.length > tamano){
        return this.substring(0,20) + '...';
    }else{
        return this;
    }
};
// prototipos anti-bobo, por si se utiliza "capitalize" con variables
// tipo Number o Boolean. No previene el mal uso de null o de undefined.
Number.prototype.capitalize = function() {
    return ''
};
Boolean.prototype.capitalize = function() {
    return ''
};
String.prototype.formatoFecha = function() { //v1.0
    var fecha = this.replace('/', '-');
    fecha = fecha.replace('/', '-');
    return fecha;
};

String.prototype.isEmpty = function() {
    if ((this == null) || (this == '')|| (this == undefined)) {
        return true;
    } else {
        return false;
    }
}
// Empty: devuelve verdadero si value es vacio
function Empty(value) {
    var pattern = new RegExp("^[ ]*$");
    if (value.match) {
        return value.match(pattern) || value.length == 0;
    } else {
        return false;
    }
}
function isPeso(value) {
    var pattern = new RegExp("^([0-9]*\.[0-9]+|[0-9]+)$");
    return value.match(pattern);
}
function isTelefono(value) {
    var pattern = new RegExp("^[0-9]+$");
    return value.match(pattern);
}
// isInteger: devuelve verdero si value es un entero
function isInteger(value) {
    var pattern = new RegExp("^[0-9]+$");
    return value.match(pattern);
}

// isMoney: devuelve verdadero si un valor es tipo moneda
function isMoney(value, isEnglish) {
    if (isEnglish) {
        var pattern = new RegExp("^[0-9]+(\\.[0-9]+){0,1}$");
        return value.match(pattern);
    } else {
        var pattern = new RegExp("^[0-9]+(,[0-9]+){0,1}$");
        return value.match(pattern);
    }
}
// isMail: devuelve verdadero si value es una direccion de correo valida
function isMail(value) {
    if(value == null)
        value="";
    var pattern = new RegExp("^([a-zA-Z0-9_\\-]+\\.{0,1})+@([a-zA-Z0-9_\\-]+\\.)+[a-zA-Z0-9_\\-]+$");
    return value.match(pattern);
}
// isWeb: devuelve verdadero si value es una direccion de web v�lida
function isWeb(value) {
    var pattern = new RegExp("http://([\w-]+\.)+[\w-]+(/[\w- ./?%&amp;=]*)?");
    return value.match(pattern);
}
//isTlfMovil: devuelve verdadero si value es un numero movil en formato 0000-0000000
function isTlfMovil(value) {
    var pattern2 = new RegExp("^[0-9]{4}[-][0-9]{7}$");
    if (value.match(pattern2)) {
        return true;
    }else{
        return false;
    }
}

// isDate: devuelve verdadero si value es una fecha valida en formato dd/mm/aaaa
function isDate(value) {
    var pattern2 = new RegExp("^(0[1-9]|[12][0-9]|3[01])[/](0[1-9]|1[012])[](19|20)[0-9]{2}$");
    if (value.match(pattern2)) {
        /*if (parseInt(value.substr(6, 4)) % 4 != 0 && parseInt(value.substr(3, 2)) == 2 && parseInt(value.substr(0, 2)) == 29) {
         return false;
         } else {
         return true;
         }*/
        //Rafael Ferrero 03/12/2009 Registro de siniestros automovil web.
        var dia  =  parseInt(value.substring(0,2),10);
        var mes  =  parseInt(value.substring(3,5),10);
        var anio =  parseInt(value.substring(6),10);
        switch(mes){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                numDias=31;
                break;
            case 4: case 6: case 9: case 11:
            numDias=30;
            break;
            case 2:
                if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) {
                    numDias=29
                }
                else {
                    numDias=28
                }
                break;
            default:
                return false;
        }
        if (dia>numDias || dia==0){
            return false;
        }

        return true;
    } else {
        return false;
    }
}
// isAlpha: devuelve verdadero si la cadena contiene solo caracteres alfabeticos
// o espacios
function isAlpha(value) {
    if(value == null)
        value="";
    var pattern = new RegExp("^[a-zA-Z\\s]+$");
    return value.match(pattern);
}
// isAlphaNum: devuelve true si el valor contiene s�lo caracteres alfabeticos (may�sculas o min�sculas)
// o n�meros, incluye espacios en blanco
function isAlphaNum(value) {
    var pattern = new RegExp("^[A-z0-9\s]+$");
    return value.match(pattern);
}

// LTrim: Quita espacios en blanco a la izquerda de una cadena
function LTrim(value) {
    var pattern = new RegExp("^\\s+", "g");
    if(value == null)
        value="";
    return value.replace(pattern, "");
}
// RTrim: Quita espacios en blanco a la derecha de una cadena
function RTrim(value) {
    var pattern = new RegExp("\\s+$", "g");
    if(value == null)
        value="";
    return value.replace(pattern, "");
}
// Trim: Quita espacios en blanco a la derecha y a la izquierda de una cadena
function Trim(value) {
    if(value == null)
      value="";

    return RTrim(LTrim(value));
}


function sendServiceJSON(uriRestService,JsonData,funcionOnSuccess,funcionOnRequest,funcionOnFailure){

    $.ajax({
        cache: false,
        url: uriRestService,
        method: "POST",
        data:(JsonData == undefined)?null:JsonData,
        contentType: "application/json; charset=UTF-8",
        dataType:"json",
        success: funcionOnSuccess

    });

    jQuery.browser = {
        version: (userAgent.match( /.+(?:rv|it|ra|ie|me)[\/: ]([\d.]+)/ ) || [])[1],
        chrome: /chrome/.test( userAgent ),
        safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
        opera: /opera/.test( userAgent ),
        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
        mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
    };


//    if ((!$.browser.msie)&&(!$.browser.safari)){
//        console.log(window.console);
//        if(window.console || window.console.firebug) {
//            console.clear();
//        }
//    }
};


function sendServiceJSON_sinc(uriRestService,JsonData,funcionOnSuccess,funcionOnRequest,funcionOnFailure){

    $.ajax({
        async:false,
        cache: false,
        url: uriRestService,
        method: "POST",
        data:(JsonData == undefined)?null:JsonData,
        contentType: 'application/json; charset=utf-8',
        dataType:"json",
        success: funcionOnSuccess

    });

    jQuery.browser = {
        version: (userAgent.match( /.+(?:rv|it|ra|ie|me)[\/: ]([\d.]+)/ ) || [])[1],
        chrome: /chrome/.test( userAgent ),
        safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
        opera: /opera/.test( userAgent ),
        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
        mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
    };


//    if ((!$.browser.msie)&&(!$.browser.safari)){
//        console.log(window.console);
//        if(window.console || window.console.firebug) {
//            console.clear();
//        }
//    }
};





function redireccionar(RUTA) {
    setTimeout("location.href='"+RUTA+"'", 10);

}


function puntosDisponibles(numero){
    if(parseInt(numero)<= parseInt($("#puntosDisponibles").html()))
        return true;
    else
        return false;
}

function  cargarEspacioTrabajo(espacio){
    $(".divEspacio").each(function(){
        this.className="divEspacio disNone";
    });
    $("#"+espacio).removeClass("disNone");
}


//Funci�n que crea las notificaciones
function notify(msg,speed,fadeSpeed,type){

    //Borra cualquier mensaje existente
    $('.notify').remove();

    //Si el temporizador para hacer desaparecer el mensaje est�
    //activo, lo desactivamos.
    if (typeof fade != "undefined"){
        clearTimeout(fade);
    }

    //Creamos la notificaci�n con la clase (type) y el texto (msg)
    $('body').append('<div class="notify '+type+'" style="display:none;position:fixed;left:10"><p>'+msg+'</p></div>');

    //Calculamos la altura de la notificaci�n.
    notifyHeight = $('.notify').outerHeight();

    //Creamos la animaci�n en la notificaci�n con la velocidad
    //que pasamos por el parametro speed
    $('.notify').css('top',-notifyHeight).animate({top:10,opacity:'toggle'},speed);

    //Creamos el temporizador para hacer desaparecer la notificaci�n
    //con el tiempo almacenado en el parametro fadeSpeed
    fade = setTimeout(function(){

        $('.notify').animate({top:notifyHeight+10,opacity:'toggle'}, speed);

    }, fadeSpeed);

}


//     notify('Probando notificaciones',500,3000);

//        notify('Debe ingresar al portal para poder continuar gracias.',500,5000,'error');

function cargarEfectoImagenApuesta(){
    $('.galleryImageApuesta').hover(
            function()
            {

                $(this).find('img').animate({width:50, marginTop:10, marginLeft:10}, 500);

            },
            function()
            {

                $(this).find('img').animate({width:100, marginTop:0, marginLeft:0},100);

            });

}

function recargarPuntosVista(monto){
    // ENCABEZADO
    $("#puntosDisponibles").html(monto);
    // para la pantalla de recarga
    $("#puntosDisponibleRecarga").html(monto);
    // para la pantalla de APUESTA
    $("#puntosDisponibleApuesta").html(monto);
    // para la pantalla de retiro
    $("#puntosDisponibleRetiro").html(monto);
}

function blanquearFormularios(div){
    $("#"+div+" .inputFormulario").each(function(){
        this.value="";
    });

    $("#"+div+" .selectFormulario").each(function(){
        this.value="-2";
    });

    $(".spanFormulario").each(function(){
        $("#"+div).html("");
    });

    $("#"+div+" .checkFormulario").each(function(){
        $(this).attr("checked",false);
    });

}

function blanquearFormulariosPersonal(div,pantalla){
    $("#"+div+" .inputFormulario"+pantalla).each(function(){
        this.value="";
    });

//    $("#"+div+" .selectFormulario"+pantalla).each(function(){
//        this.value="-2";
//    });
//
//    $("#"+div+" .spanFormulario"+pantalla).each(function(){
//        $("#"+div).html("");
//    });

}


function cargar_select(select,lista){
    var select=$("#"+select);
    select.empty();
    var option;
    option = $(document.createElement('option')).appendTo(select);
    option.attr("value",-2);
    if(idioma=="_us_en")
        option.html("Select");
    else
        option.html("Seleccione");

    $.each(lista,function(s,item){
        option = $(document.createElement('option')).appendTo(select);
        option.attr("value",item.value);
        option.attr("class","datos_"+item.label);
        if(item.seleccionado!=null){
            if(item.seleccionado=="SI")
              option.attr('selected', 'selected');
        }
        option.html(item.label);
    });

}


function cargar_selectTelefono(select,lista){
    var select=$("#"+select);
    select.empty();
    var option;
    option = $(document.createElement('option')).appendTo(select);
    option.attr("value"," ");
    option.html("Seleccione&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
    $.each(lista,function(s,item){
        option = $(document.createElement('option')).appendTo(select);
        option.attr("value",item.value);
        option.attr("class","datos_"+item.label);
        if(item.seleccionado!=null){
            if(item.seleccionado=="SI")
                option.attr('selected', 'selected');
        }
        option.html(item.label);
    });

}

function cargar_selectPersonal(selecti,lista,campo,valor){
    var select=$("#"+selecti);
    select.empty();
    var option;
    option = $(document.createElement('option')).appendTo(select);
    option.attr("value",valor);
    option.attr("id", selecti+"Select");
    option.html(campo);
    $.each(lista,function(s,item){
        option = $(document.createElement('option')).appendTo(select);
        option.attr("value",item.value);
        option.html(item.label);
    });

}

function cargar_selectCuenta(selecti,lista,campo,valor,defecto){
    var select=$("#"+selecti);

    select.empty();
    var option;
    option = $(document.createElement('option')).appendTo(select);
    option.attr("value",valor);
    option.attr("id",selecti+"Select");
    option.html(campo);
    $.each(lista,function(s,item){
        option = $(document.createElement('option')).appendTo(select);
        option.attr("value",item.value);
        option.html(item.label);
    });
    select.val(defecto);
}

function cargar_selectPersonal2(select,lista){
    var select=$("#"+select);
    select.empty();
    var option;

    $.each(lista,function(s,item){
        option = $(document.createElement('option')).appendTo(select);
        option.attr("value",item.value);
        option.html(item.label);
    });

}


function cargar_selectId(selecti,lista,campo,valor,defecto){
    var select=$("#"+selecti);

    select.empty();
    var option;
    option = $(document.createElement('option')).appendTo(select);
    option.attr("value",valor);
    option.attr("id",selecti+"Select");
    option.html(campo);
    $.each(lista,function(s,item){
        option = $(document.createElement('option')).appendTo(select);
        option.attr("value",item.value);
        option.html(item.label);
    });
    select.val(defecto);
}

function onlyDigits(e, value, allowDecimal, allowNegative, allowThousand, decSep, thousandSep, decLength){
    var _ret = true, key;
    if(window.event) { key = window.event.keyCode; isCtrl = window.event.ctrlKey }
    else if(e) { if(e.which) { key = e.which; isCtrl = e.ctrlKey; }}
    if(key == 8) return true;
    if(isNaN(key)) return true;
    if(key < 44 || key > 57) { return false; }
    if(key == 47) return false;
    keychar = String.fromCharCode(key);
    if(decLength == 0) allowDecimal = false;
    if(!allowDecimal && keychar == decSep || !allowNegative && keychar == '-' || !allowThousand && keychar == thousandSep) return false;
    return _ret;}

function onlyDigits_v2(e, value, allowDecimal, allowNegative, allowThousand, decSep, thousandSep, decLength,objeto){
    var _ret = true, key;
    if(window.event) { key = window.event.keyCode; isCtrl = window.event.ctrlKey }
    else if(e) { if(e.which) { key = e.which; isCtrl = e.ctrlKey; }}
    if(key == 8) return true;
    if(isNaN(key)) return true;
    if(key < 44 || key > 57) { return false; }
    if(key == 44) return false;

    if(key == 46){

        if (objeto=="EM"){
            if ($("#BetweenLinkedAccounts_Monto").val().indexOf('.')!=-1) {
               return false;
            }
        }else  if (objeto=="OC"){
            if ($("#ToOtherClient_Monto").val().indexOf('.')!=-1) {
                return false;
            }
        }else  if (objeto=="OB"){
            if ($("#AmmountAI").val().indexOf('.')!=-1) {
                return false;
            }
        }
    }

    var arr;
    var validar=false;

    if (objeto=="EM"){
        if ($("#BetweenLinkedAccounts_Monto").val().indexOf('.')!=-1) {
            arr=$("#BetweenLinkedAccounts_Monto").val().split(".");
            validar=true;
        }
    }else  if (objeto=="OC"){
        if ($("#ToOtherClient_Monto").val().indexOf('.')!=-1) {
            arr=$("#ToOtherClient_Monto").val().split(".");
            validar=true;
        }
    }else  if (objeto=="OB"){
        if ($("#AmmountAI").val().indexOf('.')!=-1) {
            arr=$("#AmmountAI").val().split(".");
            validar=true;
        }
    }
//    if ((validar)&&(key!=51))
//        if (arr[1].length>=2){
//            return false;
//        }


    if(key == 47) return false;

    keychar = String.fromCharCode(key);
    if(decLength == 0) allowDecimal = false;
    if(!allowDecimal && keychar == decSep || !allowNegative && keychar == '-' || !allowThousand && keychar == thousandSep) return false;
//    var _ret = true, key;
//    if(window.event) { key = window.event.keyCode; isCtrl = window.event.ctrlKey }
//    else if(e) { if(e.which) { key = e.which; isCtrl = e.ctrlKey; }}
//    if(key == 8) return true;
//    if(isNaN(key)) return true;
//    if(key < 44 || key > 57) { return false; }
//    if(key == 47) return false;
//    if(key == 46) key=44;
//    keychar = String.fromCharCode(key);
//    if(decLength == 0) allowDecimal = false;
//    if(!allowDecimal && keychar == decSep || !allowNegative && keychar == '-' || !allowThousand && keychar == thousandSep) return false;
    return _ret;}

function crearTabla(div_contenedor,id_tabla,contenidoTabla_culumnas,contenidoTabla_info){
    var div=$("#"+div_contenedor);
    div.empty();
    var tabla = $(document.createElement('table')).appendTo(div);
    tabla.attr("cellpadding","0");
    tabla.attr("cellspacing","0");
    tabla.attr("border","0");
    tabla.attr("class","display");
    tabla.attr("id",id_tabla);

    var thead=$(document.createElement('thead')).appendTo(tabla);
    var  tr= $(document.createElement('tr')).appendTo(thead);

    var  th;
    var  td;
    $.each(contenidoTabla_culumnas,function(c,item){
        var columna=contenidoTabla_culumnas[c];
        th= $(document.createElement('th')).appendTo(tr);
        th.attr("class","center");
        th.attr("id","tag_"+columna.span_html.toString().replace(" ","_").substring(0,columna.span_html.toString().lastIndexOf(" ")>0?columna.span_html.toString().lastIndexOf(" "):columna.span_html.toString().length)+"_consolidado_det");
        th.html(columna.span_html);
        //th.html("<span id='tag_"+columna.span_html.toString().substring(0,columna.span_html.toString().indexOf(" ")!=-1?columna.span_html.toString().indexOf(" "):columna.span_html.toString().length-1)+"_consolidado_det'>"+columna.span_html+"</span>");
    });

    var tbody=$(document.createElement('tbody')).appendTo(tabla);
    $.each(contenidoTabla_info,function(dc,item){
        var data=contenidoTabla_info[dc];
        tr= $(document.createElement('tr')).appendTo(tbody);
        $.each(data.data_culumn,function(d,item){
            var data_info=data.data_culumn[d];
            td= $(document.createElement('td')).appendTo(tr);
            td.attr("class","");
//            td.attr("class","center");
            td.html(data_info.data_columna);
        });

    });

}



function crearTablaTmp(div_contenedor,id_tabla,contenidoTabla_culumnas,contenidoTabla_info){
    var div=$("#"+div_contenedor);
    div.empty();
    var tabla = $(document.createElement('table')).appendTo(div);
    tabla.attr("cellpadding","0");
    tabla.attr("cellspacing","0");
    tabla.attr("border","0");
    tabla.attr("class","display");
    tabla.attr("id",id_tabla);

    var thead=$(document.createElement('thead')).appendTo(tabla);
    var  tr= $(document.createElement('tr')).appendTo(thead);

    var  th;
    var  td;
    $.each(contenidoTabla_culumnas,function(c,item){
        var columna=contenidoTabla_culumnas[c];
        th= $(document.createElement('th')).appendTo(tr);
        th.attr("class","center");
        //th.attr("id","tag_"+columna.span_html.toString().replace(" ","_")+"_detalle");
        th.attr("id","tag_"+columna.span_html.toString() .replace(/\s/g,"_")+"_detalle");
        th.html(columna.span_html);
        //th.html("<span id='tag_"+columna.span_html.toString().substring(0,columna.span_html.toString().indexOf(" ")!=-1?columna.span_html.toString().indexOf(" "):columna.span_html.toString().length-1)+"_consolidado_det'>"+columna.span_html+"</span>");
    });

    var tbody=$(document.createElement('tbody')).appendTo(tabla);
    $.each(contenidoTabla_info,function(dc,item){
        var data=contenidoTabla_info[dc];
        tr= $(document.createElement('tr')).appendTo(tbody);
        $.each(data.data_culumn,function(d,item){
            var data_info=data.data_culumn[d];
            td= $(document.createElement('td')).appendTo(tr);
            td.attr("class","");
//            td.attr("class","center");
            td.html(data_info.data_columna);
        });

    });

}

function    crearTablaAgregar(div_contenedor,id_tabla,contenidoTabla_culumnas,contenidoTabla_info, tabla_a_agregar){
    var div=$("#"+div_contenedor);
    div.empty();
    var tabla = $(document.createElement('table')).appendTo(div);
    tabla.attr("cellpadding","0");
    tabla.attr("cellspacing","0");
    tabla.attr("border","0");
    tabla.attr("class","display");
    tabla.attr("id",id_tabla);

    var thead=$(document.createElement('thead')).appendTo(tabla);
    var  tr= $(document.createElement('tr')).appendTo(thead);
    var  th;
    var  td;
    $.each(contenidoTabla_culumnas,function(c,item){
        var columna=contenidoTabla_culumnas[c];
        th= $(document.createElement('th')).appendTo(tr);
        th.html(columna.span_html);
    });

    var tbody=$(document.createElement('tbody')).appendTo(tabla);
    $.each(contenidoTabla_info,function(dc,item){
        var data=contenidoTabla_info[dc];
        tr= $(document.createElement('tr')).appendTo(tbody);
        $.each(data.data_culumn,function(d,item){
            var data_info=data.data_culumn[d];
            var lista_data=data_info.data_columna.split("-");

            td= $(document.createElement('td')).appendTo(tr);
            td.attr("class","center");
            if(lista_data.length>1){
                tr.attr("id",lista_data[0]);
                td.html(lista_data[1]);
            }else{
                td.html(lista_data[0]);
            }

        });

        tr.click(function(){
            agregar_a_tabla(tabla_a_agregar,this);
        });

    });

}


function agregar_a_tabla(tabla,tr){
    var campo1, campo2;


    $.each(tr.children,function (index2) {
        switch (index2) {

            case 0:
                campo1 = tr.children[index2].textContent;
                break;
            case 1:
                campo2 = tr.children[index2].textContent;
                break;
            case 2:
                campo3 = tr.children[index2].textContent;
                break;
        }
    })
    var data_columna =
    {   "data_culumn":[
        {"data_columna":campo1},
        {"data_columna":campo2}
    ]};

    datos_prueba[datos_prueba.length]=data_columna;
    crearTabla("div_"+tabla,tabla,columnas_prueba,datos_prueba);
    var oTable2 = $('#'+tabla).dataTable({
        "bJQueryUI": true,
        "sPaginationType": "full_numbers"
    });
}

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

function esSoloNumeros(string) {

    if (string.search) {
        if (string.search(/[^0-9]/) != -1)
            return false;
    }
    return true;
}


/**
 * @author Jorge Maguina
 * This function print whatever element of a html
 * @param element
 */
function printPageElement(element){

    $.each($(".invisible_print"),function(pos,item){
        $("#"+item.id+"_print").html($(item).val());
    });

    $("#"+element).jqprint({
        importCSS: true,
        printContainer: true
    });



}


function agregar_descripcion(element){
   alert(element.title);
}

/*
jQuery.browser = {
    version: (userAgent.match( /.+(?:rv|it|ra|ie|me)[\/: ]([\d.]+)/ ) || [])[1],
    chrome: /chrome/.test( userAgent ),
    safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
    opera: /opera/.test( userAgent ),
    msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
    mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
};  */

function cargar_estilos(){   //va fuera de la funcion
    if ($.browser.chrome) {
        document.write("<link rel='stylesheet' type='text/css' href='css/browsers/chrome.css'>");
    }else if ($.browser.msie){
        if ($.browser.version>=7){
            document.write("<link rel='stylesheet' type='text/css' href='css/browsers/ie7.css'>");
        }else{
            document.write("<link rel='stylesheet' type='text/css' href='css/browsers/ie6.css'>");
        }
    }else if ($.browser.safari) {
        document.write("<link rel='stylesheet' type='text/css' href='css/browsers/safari.css'>");
    }else if ($.browser.opera) {
        document.write("<link rel='stylesheet' type='text/css' href='css/browsers/opera.css'>");
    }else if ($.browser.mozilla) {
        document.write("<link rel='stylesheet' type='text/css' href='css/browsers/firefox.css'>");
    }
}


function comprobarScreen()
{ if(window.screen.availWidth <= 640)
{window.parent.document.body.style.zoom="50%";}
    if(window.screen.availWidth == 800)
    {window.parent.document.body.style.zoom="65%";}
    if(window.screen.availWidth == 1024)
    {window.parent.document.body.style.zoom="90%";}
    if(window.screen.availWidth == 1280)
    {window.parent.document.body.style.zoom="90%";}
    if(window.screen.availWidth >= 1440)
    {window.parent.document.body.style.zoom="100%";}
}

  //Original
//function actualizar_navegador(){
//    var actualizar=false;
//    jQuery.browser = {
//        version: (userAgent.match( /.+(?:rv|it|ra|ie|me)[\/: ]([\d.]+)/ ) || [])[1],
//        chrome: /chrome/.test( userAgent ),
//        safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
//        opera: /opera/.test( userAgent ),
//        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
//        mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
//    };
//
//    var p=$('<p id="texto_actualizar"> Por favor, haga click en la imagen para actualizar su Navegador</p>');
//
//
//    if ($.browser.chrome) {
//        $(window).unload(function() { salir(); });
//    }else if ($.browser.msie){
//        if ($.browser.version<8){
//            actualizar=true;
//            $("#login").html('<a target="_blank" href="http://windows.microsoft.com/es-es/internet-explorer/download-ie"> <img src="../vbtonline/resources/images/actualizar-navegador.png" alt="download navegador new" class="imagen_descarga"></a>');
//            p.appendTo($("#login"));
//        }else{
//            $(window).bind("beforeunload", function() { salir();});
//        }
//    }else{
//        $(window).bind("beforeunload", function() { salir();});
//    } /*if ($.browser.safari) {
//        alert("safari");
//    }else if ($.browser.opera) {
//        alert("opera");
//    }else if ($.browser.mozilla) {
//        alert("Mozilla");
//    }*/
//
//
//
//    return actualizar;
//
//}




function actualizar_navegador(){
    var actualizar=false;
    jQuery.browser = {
        version: (userAgent.match( /.+(?:rv|it|ra|ie|me)[\/: ]([\d.]+)/ ) || [])[1],
        chrome: /chrome/.test( userAgent ),
        safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
        opera: /opera/.test( userAgent ),
        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
        mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
    };

    var p=$('<p id="texto_actualizar"> Por favor, haga click en la imagen para actualizar su Navegador</p>');


    if ($.browser.chrome) {

    }else if ($.browser.msie){
        if ($.browser.version<8){
            actualizar=true;
            $("#login").html('<a target="_blank" href="http://windows.microsoft.com/es-es/internet-explorer/download-ie"> <img src="../vbtonline/resources/images/actualizar-navegador.png" alt="download navegador new" class="imagen_descarga"></a>');
            p.appendTo($("#login"));
        }else{

        }
    }else{

    } /*if ($.browser.safari) {
     alert("safari");
     }else if ($.browser.opera) {
     alert("opera");
     }else if ($.browser.mozilla) {
     alert("Mozilla");
     }*/



    return actualizar;

}


function eventoSalirHome(){
    var actualizar=false;
    jQuery.browser = {
        version: (userAgent.match( /.+(?:rv|it|ra|ie|me)[\/: ]([\d.]+)/ ) || [])[1],
        chrome: /chrome/.test( userAgent ),
        safari: /webkit/.test( userAgent ) && !/chrome/.test( userAgent ),
        opera: /opera/.test( userAgent ),
        msie: /msie/.test( userAgent ) && !/opera/.test( userAgent ),
        mozilla: /mozilla/.test( userAgent ) && !/(compatible|webkit)/.test( userAgent )
    };



    if ($.browser.chrome) {
        $(window).unload(function() { salir(); });
    }else if ($.browser.msie){
        if ($.browser.version<8){

        }else{
            $(window).bind("beforeunload", function() { salir();});
        }
    }else{
        $(window).bind("beforeunload", function() { salir();});
    } /*if ($.browser.safari) {
     alert("safari");
     }else if ($.browser.opera) {
     alert("opera");
     }else if ($.browser.mozilla) {
     alert("Mozilla");
     }*/



    return actualizar;

}





function crearTabla_paginacion(div_contenedor,id_tabla,contenidoTabla_culumnas,contenidoTabla_info,reg_inicio,tam_pagina,p_sclass){
    var div_contenedor=$("#"+div_contenedor);
    div_contenedor.empty();
    var tabla = $(document.createElement('table')).appendTo(div_contenedor);
    tabla.attr("cellpadding","0");
    tabla.attr("cellspacing","0");
    tabla.attr("border","0");
    tabla.attr("class","display");
    tabla.attr("id",id_tabla);

    var thead=$(document.createElement('thead')).appendTo(tabla);
    var  tr= $(document.createElement('tr')).appendTo(thead);

    var  th;
    var  td;
    if (contenidoTabla_culumnas!=null) {
        $.each(contenidoTabla_culumnas,function(c,item){
            var columna=item;
            th= $(document.createElement('th')).appendTo(tr);
            th.attr("class","center");
            th.html(columna.span_html);
        });
    }
    var tbody=$(document.createElement('tbody')).appendTo(tabla);

    if (contenidoTabla_info!=null) {
        if(contenidoTabla_info.length<=tam_pagina){
            $.each(contenidoTabla_info,function(dc,item){
                var data=contenidoTabla_info[dc];
                tr= $(document.createElement('tr')).appendTo(tbody);
                $.each(data.data_culumn,function(d,item){
                    var data_info=data.data_culumn[d];
                    td= $(document.createElement('td')).appendTo(tr);
                    td.attr("class","");
    //            td.attr("class","center");
                    td.html(data_info.data_columna);
                });

            });
        }else{
            for(var cantidad=reg_inicio;cantidad<(reg_inicio+tam_pagina);cantidad++){
                var data=contenidoTabla_info[cantidad];
                tr= $(document.createElement('tr')).appendTo(tbody);
                $.each(data.data_culumn,function(d,item){
                    var data_info=item;
                    td= $(document.createElement('td')).appendTo(tr);
                    td.attr("class","");
                    td.html(data_info.data_columna);
                });
            }
        }
    }
    return ($("#"+id_tabla)) ;
}

function  paginacion(div_contenedor,id_tabla,contenidoTabla_culumnas,contenidoTabla_info,reg_inicio,tam_pagina,p_sclass){
    $("#"+div_contenedor).empty();
    var tabla = $(document.createElement('table'));
    $("#"+div_contenedor).prepend(tabla);
    tabla.attr("cellpadding","0");
    tabla.attr("cellspacing","0");
    tabla.attr("border","0");
    tabla.attr("class","display");
    tabla.attr("id",id_tabla);

    var thead=$(document.createElement('thead')).appendTo(tabla);
    var  tr= $(document.createElement('tr')).appendTo(thead);

    var  th;
    var  td;
    $.each(contenidoTabla_culumnas,function(c,item){
        var columna=item;
        th= $(document.createElement('th')).appendTo(tr);
        th.attr("class","center");
        th.html(columna.span_html);
    });
    var tbody=$(document.createElement('tbody')).appendTo(tabla);

    for(var cantidad=reg_inicio;cantidad<(reg_inicio+tam_pagina);cantidad++){
        if(cantidad<contenidoTabla_info.length) {
            var data=contenidoTabla_info[cantidad];
            tr= $(document.createElement('tr')).appendTo(tbody);
            $.each(data.data_culumn,function(d,item){
                var data_info=item;
                td= $(document.createElement('td')).appendTo(tr);
                td.attr("class","");
                td.html(data_info.data_columna);
            });
        }else{
            cantidad=  (reg_inicio+tam_pagina);
        }
    }

    oTable = $("#"+id_tabla).dataTable({
        "iDisplayLength": tam_pagina,
        "bJQueryUI": true,
        "bFilter": false,
        "bSort": false,
        "bPaginate": false,
        "bScrollCollapse": true,
        "aoColumns": p_sclass ,
        "oLanguage": {
            "sZeroRecords": "",
            "sInfo": "",
            "sInfoEmpty": ""
        }
    });

}




idleTime = 0;
function idleTimeOut(){
    //Increment the idle time counter every minute.
    var idleInterval = setInterval("timerIncrement()", 60000); // 1 minute

    //Zero the idle timer on mouse movement.
    $(document).click(function (e) {
        latidoWeb();
        idleTime = 0;
    });
    $(document).keypress(function (e) {
        idleTime = 0;
    });

};

function timerIncrement() {
    idleTime = idleTime + 1;
    if (idleTime > 4) { // 5 minutes
        salir();
    }
}

function quitarSaldo(cadena){

    var p=cadena.split("(");
    var  newCad=p[0]+" - ("+p[2];
    return newCad;

}

function limpiar_espacios(cadena){
    var result = cadena.replace(/\s/g,"");
    return result;
}


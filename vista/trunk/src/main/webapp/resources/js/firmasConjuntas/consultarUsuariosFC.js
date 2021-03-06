var urlFirmaConjuntaConsultarUsuarios="FirmasConjuntas_consultarUsuarios.action";
var urlFirmaConjuntaConsultaUsuario="FirmasConjuntas_consultaUsuario.action";
var urlFirmaConjuntaEditarUsuario="FirmasConjuntas_editarUsuario.action";
var urlFirmaConjuntaAgregarUsuario="FirmasConjuntas_agregarUsuario.action";
var urlFirmaConjuntaEnviarClave="Security_enviarClaveUsuarioFirmaConjunta.action";
var urlBackOfficeEnviarUsuario="Security_enviarUsuario.action";
var urlFirmaConjuntaConsultarUsuariosTipos="FirmasConjuntas_cargarGruposFC.action";
var urlFirmaConjuntaRolesCustom="FirmasConjuntas_cargarRolesCustom.action";
var urlFirmaConjuntaRolesCustomDettale="FirmasConjuntas_cargarRolesCustomDetalle.action";

var usuarioEditar;
var hdnCambioEstatus = "No";
var oTable="";
var noInfo="";
var idioma="";

$(document).ready(function(){

    $('.only_login_FC').keypress(function (e) {
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


    $("#telefonoCodCelEU_FC").intlTelInput();
    $("#AU_telefonoCodCel_FC").intlTelInput();

    $.mask.definitions['~']='[12]';
    $.mask.definitions['+']='[246]';
    $.mask.definitions['/']='[123456789]';
    //$(".mascaraCelular").mask("(04~+) ?999-9999");
    $(".mascaraCelular").mask("?/99999999999");




/*    $('#tabla_consultarUsuarios_FC tbody td a').live('click', function () {
        seleccionarUsuariosFirmaConjuntaOpcion($(this).attr("id"),$(this).val());
    } );

    $('#tabla_consultarUsuarios_FC tbody td img').live('click', function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /!* This row is already open - close it *!/
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /!* Open this row *!/
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsUsuario_FC($(this).attr("id")), 'details');
        }

//        alert("hizo click : "+);
    } );*/
    $('#tabla_consultarUsuarios_FC tbody td a').live("click",function () {
        seleccionarUsuariosFirmaConjuntaOpcion($(this).attr("id"),$(this).val());
    } );

    $('#tabla_consultarUsuarios_FC tbody td img').live("click",function () {
        var nTr = $(this).parents('tr')[0];
        if ( oTable.fnIsOpen(nTr) )
        {
            /!* This row is already open - close it *!/
            this.src = "resources/images/comun/nolines_plus.gif";
            oTable.fnClose( nTr );
        }
        else
        {
            /!* Open this row *!/
            this.src = "resources/images/comun/nolines_minus.gif";
            oTable.fnOpen( nTr, fnFormatDetailsUsuario_FC($(this).attr("id")), 'details');
        }

    } );



    $("#agregarUsuarioCU_FC").click(function(){

        $("#AU_telefonoCodCel_FC").intlTelInput("setNumber", "+58");
        $("#AU_telefonoCel_FC").mask("?/99999999999").val(" ");


        $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){
            $("#div_"+this.id).removeClass("error_campo");
        });

        $("#asignacionRolesFCAdd").addClass("oculto");
        $("#rolesDinamicosAddLista").html("");

        $("#div_tabla_consultaCU_FC").fadeOut("fast");
        $("#div_tabla_agregarUsuario_FC").fadeIn("fast");

        $(".obligatorio_AUFC").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarUsuario_FC");
        $("#AU_telefonoCel_FC").mask("?/99999999999").val(" ");
    });

    $("#Cancelar_AU_FC").click(function(){
        $(".obligatorio_AUFC").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarUsuario_FC");
        $("#div_tabla_consultaCU_FC").fadeIn("fast");
        $("#div_tabla_agregarUsuario_FC").fadeOut("fast");
    });

    $("#btnBackEU_FC").click(function(){

        blanquearFormularios("div_tabla_editarUsuario_FC");
        $("#div_tabla_consultaCU_FC").fadeIn("fast");
        $("#div_tabla_editarUsuario_FC").fadeOut("fast");
        FirmaConjuntaUsuariosJSONData();
//        $("#div_CU_agregarUsuario").fadeIn("fast");
    });


    $("#btnDeshacerEU_FC").click(function(){

        blanquearFormularios("div_tabla_editarUsuario_FC");

//        $("#div_CU_agregarUsuario").fadeIn("fast");
    });

    $("#Resetear_AU_FC").click(function(){
        $(".obligatorio_AUFC").each(function(){
            if($("#"+this.id).hasClass("error_campo"))
                $("#"+this.id).removeClass("error_campo");

        });
        blanquearFormularios("div_tabla_agregarUsuario_FC");
        $("#rolesDinamicosAddLista").html("");
        $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){
            $("#"+this.id).attr("checked", false);
            $("#"+this.id).removeAttr("checked");

        });
    });


    $("#AgregarUsuario_AU_FC").click(function(){

        var mensaje="";

        $(".obligatorio_AUFC").each(function(){
            if(idioma=="_us_en"){
                if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Required field -"+this.title+"<br>";

                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }

            }else{
                if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Campo requerido - "+this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }

            }

        });



        var roles=0;
        if ($("#AU_grupo_FC").val()=="11"){
            $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){

                if($("#"+this.id).is(':checked')) {
                    roles++;
                    if($("#div_"+this.id).hasClass("error_campo"))
                        $("#div_"+this.id).removeClass("error_campo");
                }
            });

            if (roles==0){
                $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){
                    $("#div_"+this.id).addClass("error_campo");
                });

                if(idioma=="_us_en"){
                    mensaje=mensaje+"Please select at least a role.<br>";
                }else{
                    mensaje=mensaje+"Por favor seleccione un rol como m??nimo.<br>";
                }
            } else{
                $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){
                    $("#div_"+this.id).removeClass("error_campo");
                });
            }


        }

        if ($("#AU_usuario_FC").val().length<=5){
            if(idioma=="_us_en"){
                mensaje=mensaje+"The user name must be at least 6 characters<br>";
            }else{
                mensaje=mensaje+"El usuario debe tener  al menos 6 caracteres<br>";
            }
            $("#AU_usuario_FC").addClass("error_campo");

        }else{
            if($("#AU_usuario_FC").hasClass("error_campo"))
                $("#AU_usuario_FC").removeClass("error_campo");
        }

        if (!$("#AU_usuario_FC").val().match("^[A-Za-z][A-Za-z0-9._]+[^._]$")){
            if(idioma=="_us_en"){
                mensaje=mensaje+"Invalid format User Name"+"<br>";
            }else{
                mensaje=mensaje+"Formato inv??lido Nombre de Usuario"+"<br>";
            }
            $("#AU_usuario_FC").addClass("error_campo");

        }else{
            if($("#AU_usuario_FC").hasClass("error_campo"))
                $("#AU_usuario_FC").removeClass("error_campo");
        }


        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#AU_email_FC").get(0).value)){
                if($("#AU_email_FC").hasClass("error_campo"))
                    $("#AU_email_FC").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                if(idioma=="_us_en"){
                    //traducir
                    if(confirm("New user information will be entered.\n??Please verify the information is correct?")){
                        $("#div_carga_espera").removeClass("oculto");
                        FirmaConjuntaSalvarAgregarUsuarioJSONData();

                    }
                }else{
                    if(confirm("Se ingresar\u00e1n  los datos de un nuevo usuario.\n????Esta seguro de que los datos son correctos?")){
                        $("#div_carga_espera").removeClass("oculto");
                        FirmaConjuntaSalvarAgregarUsuarioJSONData();
                    }
                }


            }else{
                if(idioma=="_us_en"){
                    //traducir
                    mensaje = "Enter a valid email address";
                }else{
                    mensaje = "Introduzca una direcci&oacute;n de correo electr&oacute;nico v&aacute;lida";
                }

                $("#AU_email_FC").addClass("error_campo");
                $("#mensaje_error").empty();
                $("#mensaje_error").html(mensaje);
                $("#div_mensajes_error").fadeIn("slow");
            }
        }
    });

    $("#btnDeshacerEU_FC").click(function(){
        $("#usuarioEU_FC").html(usuarioEditar.login);
        $("#nombreEU_FC").attr("value",usuarioEditar.nombres);
        $("#tipoPersonaEU_FC").attr("value",usuarioEditar.tipo_cirif);
        $("#estatusEU_FC").attr("value",usuarioEditar.status_cuenta);
        $("#ciRifEU_FC").attr("value",usuarioEditar.cirif);
        $("#emailEU_FC").attr("value",usuarioEditar.email);
        $("#telefonoEU_FC").attr("value",usuarioEditar.telefono);
        $("#telefonoCelEU_FC").attr("value",usuarioEditar.telefono_celular);
        $("#direccionEU_FC").attr("value",usuarioEditar.direccion);
        $("#grupoEU_FC").attr("value",usuarioEditar.tipo);
        if (usuarioEditar.tipo=="11"){
            $("#asignacionRolesFCEdit").removeClass("oculto");
            rolesCustomEditFCJSONData();
        }else{
            $("#asignacionRolesFCEdit").addClass("oculto");
        }

    });
//    btnDeshacerEU
    $("#btnSalvarEU_FC").click(function(){

        var mensaje="";
        $(".obligatorio_EUFC").each(function(){
            if(idioma=="_us_en"){
                if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Required field - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            }else{
                if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
                    mensaje=mensaje+"Campo requerido - "+ this.title+"<br>";
                    $("#"+this.id).addClass("error_campo");
                }else{
                    if($("#"+this.id).hasClass("error_campo"))
                        $("#"+this.id).removeClass("error_campo");
                }
            }
        });

        var roles=0;
        if ($("#grupoEU_FC").val()=="11"){
            $("#asignacionRolesFCEdit .obligatorioRoles_editFC").each(function(){

                if($("#"+this.id).is(':checked')) {
                    roles++;
                    if($("#div_"+this.id).hasClass("error_campo"))
                        $("#div_"+this.id).removeClass("error_campo");

                }
            });

            if (roles==0){
                $("#asignacionRolesFCEdit .obligatorioRoles_editFC").each(function(){
                    $("#div_"+this.id).addClass("error_campo");
                });

                if(idioma=="_us_en"){
                    mensaje=mensaje+"Please select at least a role.<br>";
                }else{
                    mensaje=mensaje+"Por favor seleccione un rol como m??nimo.<br>";
                }
            } else{
                $("#asignacionRolesFCEdit .obligatorioRoles_editFC").each(function(){
                    $("#div_"+this.id).removeClass("error_campo");
                });
            }


        }

        if(mensaje!=""){
            $("#mensaje_error").empty();
            $("#mensaje_error").html(mensaje);
            $("#div_mensajes_error").fadeIn("slow");
        }else{
            if(isMail($("#emailEU_FC").get(0).value)){
                if($("#emailEU_FC").hasClass("error_campo"))
                    $("#emailEU_FC").removeClass("error_campo");
                $("#div_mensajes_error").fadeOut("fast");
                if(idioma=="_us_en"){
                    //traducir
                    if(confirm("User data will be updated. \n Please verify the information is correct")){
                        FirmaConjuntaSalvarEditarUsuarioJSONData();
                    }
                }else{
                    if(confirm("Se actualizar\u00e1n los datos del usuario  ??Est\u00e1 seguro que los datos son correctos?")){
                        FirmaConjuntaSalvarEditarUsuarioJSONData();
                    }
                }

            }else{
                mensaje = "Email";

                $("#emailEU").addClass("error_campo");
                $("#mensaje_error").empty();
                if(idioma=="_us_en"){
                    $("#mensaje_error").html("Invalid field - "+mensaje);
                }else{
                    $("#mensaje_error").html("Campo invalido - "+mensaje);
                }

                $("#div_mensajes_error").fadeIn("slow");
            }
        }
    });

    $("#btnResetearPasswordEU_FC").click(function(){
        if(idioma=="_us_en"){
            if(confirm("Are you sure you want to reset the user's password?")){
                $("#div_carga_espera").removeClass("oculto");
                FirmaConjuntaEnviarClaveUsuarioJSONData();
            }
        }else{
            if(confirm("??Est\u00e1 seguro que desea reiniciar la clave del usuario?")){
                $("#div_carga_espera").removeClass("oculto");
                FirmaConjuntaEnviarClaveUsuarioJSONData();
            }
        }

    });

    $("#CU_reset_FC").click(function(){
        //blanquearFormularios("div_tabla_consultaCU_FC");
        $("#CU_usuarioFiltro_FC").val("");
        $("#CU_nombreFiltro_FC").val("");
        $("#CU_CIRIF_FC").val("");
        $("#CU_grupoFiltro_FC").val("-2");
        $("#CU_estatusFiltro_FC").val("-2");

    });

    $("#CU_search_FC").click(function(){

        FirmaConjuntaUsuariosJSONData();

    });

    $("#btnSendUserEU_FC").click(function(){
        if(idioma=="_us_en"){
            if(confirm("Are you sure you want to send the user name?")){
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeEnviarUsuarioJSONDataFC();
            }
        }else{
            if(confirm("??Est\u00e1 seguro que desea enviar el nombre del usuario?")){
                $("#div_carga_espera").removeClass("oculto");
                BackOfficeEnviarUsuarioJSONDataFC();
            }
        }

    });

});



function fnFormatDetailsUsuario_FC (valor)
{   var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_cirif_det">ID </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_telefono_det">Telephone Number</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_fecha_det">Creation Date </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_direccion_det">Address </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else{
        sOut +='<table width="100%"><tr><td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_cirif_det">C.I./R.I.F </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_cirif_det">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_telefono_det">Tel&eacute;fono </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_telefono_det">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_fecha_det">Fecha Creaci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_fecha_det">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_email_det">Email </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_email_det">'+valor.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="FirmasConjuntas_usuario_direccion_det">Direcci&oacute;n </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_bac_usuario_direccion_det">'+valor.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }



    return sOut;
}

//Enviar Clave al Usuario

function FirmaConjuntaEnviarClaveUsuarioJSONData(id){
    var url = urlFirmaConjuntaEnviarClave;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU_FC").html();

    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON(url,param,FirmaConjuntaEnviarClaveUsuarioSuccess,null,null);


}


function FirmaConjuntaEnviarClaveUsuarioSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    idioma = result.idioma;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        if(idioma=="_us_en"){
            //traducir
            $("#mensaje_error").html("The user's password was successfully reset.\n The new password has been sent to the registered mobile phone number");
        }
        else{
            $("#mensaje_error").html("La clave del usuario fue reiniciada satisfactoriamente.\nLa nueva clave ha sido enviada al n??mero registrado.");
        }

        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            $("#mensaje_error").html("The user's password name could not be sent to your phone.");
        else
            $("#mensaje_error").html("La clave del usuario no pudo ser enviado a su telefono.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

//FIN Enviar Clave al Usuario


function FirmaConjuntaUsuariosJSONData(){
    $("#div_carga_espera").removeClass("oculto");
    var url = urlFirmaConjuntaConsultarUsuarios;
    var param={};
    var jsonConsultaUsuarios=[];
    var datosConsultaUBO={};

    //$("#marco_trabajo").css("height","900px");
    $("#div_tabla_agregarUsuario_FC").attr("style","display: none");
    $("#asignacionRolesFCAdd").addClass("oculto");
    $("#div_tabla_editarUsuario_FC").attr("style","display: none");
    $("#div_tabla_consultaCU_FC").fadeIn("fast");
    if($("#CU_usuarioFiltro_FC").get(0).value!=null)
    {
        datosConsultaUBO.strTxtUsuario=$("#CU_usuarioFiltro_FC").get(0).value;
    }else
        datosConsultaUBO.strTxtUsuario="";
    if($("#CU_nombreFiltro_FC").get(0).value!=null)
        datosConsultaUBO.strTxtNombre=$("#CU_nombreFiltro_FC").get(0).value;
    else
        datosConsultaUBO.strTxtNombre="";
    if($("#CU_CIRIF_FC").get(0).value!=null)
        datosConsultaUBO.strTxtCIDRIF=$("#CU_CIRIF_FC").get(0).value;
    else
        datosConsultaUBO.strTxtCIDRIF="";
    if($("#CU_grupoFiltro_FC").get(0).value!="-2")
        datosConsultaUBO.strCmbTipoUsuario=$("#CU_grupoFiltro_FC").get(0).value;
    else
        datosConsultaUBO.strCmbTipoUsuario="Default";
    if($("#CU_estatusFiltro_FC").get(0).value!="-2")
        datosConsultaUBO.strCmbEstatus=$("#CU_estatusFiltro_FC").get(0).value;
    else
        datosConsultaUBO.strCmbEstatus="Default";

    if($("#CU_usuarioFiltro_FC").get(0).value=='' && $("#CU_nombreFiltro_FC").get(0).value=='' &&
        $("#CU_CIRIF_FC").get(0).value=='' && ($("#CU_grupoFiltro_FC").get(0).value=="-2" || $("#CU_grupoFiltro_FC").get(0).value=='' )
        && ($("#CU_estatusFiltro_FC").get(0).value=="-2" || $("#CU_estatusFiltro_FC").get(0).value=='' )){
        datosConsultaUBO.hdnAccion=null;
    }else{
        datosConsultaUBO.hdnAccion="Buscar";
    }

    jsonConsultaUsuarios[0]= datosConsultaUBO;

    param.jsonConsultaUsuarios=JSON.stringify({"consultaUsuarioss":jsonConsultaUsuarios});

    // sendServiceJSON_sinc(url,param,FirmaConjuntaUsuariosSuccess,null,null);
   try{
    sendServiceJSON(url,param,FirmaConjuntaUsuariosSuccess,null,null);
    }catch(e){
        $("#div_carga_espera").addClass("oculto");
    }
   }


function FirmaConjuntaUsuariosSuccess(originalRequest){
    console.log('FirmaConjuntaUsuariosSuccess');
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupos = result.tipoGrupo;
    tipoEstatus = result.tipoEstatus;
    tipoCiRif = result.tipoCiRif;
    primeraVez = result.primeraVez;
    idioma = result.idioma;
    var buscar;
    var mostrando;
    $("#div_carga_espera").addClass("oculto");

//    if(primeraVez == "SI"){
    cargar_select("AU_grupo_FC",tipoGrupos);
    cargar_select("grupoEU_FC",tipoGrupos);
    cargar_select("estatusEU_FC",tipoEstatus);
    cargar_select("tipoPersonaEU_FC",tipoCiRif);
    cargar_select("AU_tipoPersona_FC",tipoCiRif);
    cargar_select("CU_estatusFiltro_FC",tipoEstatus);
    cargar_select("CU_grupoFiltro_FC",tipoGrupos);
//    }
    //$("#marco_trabajo").css("height","1400px");
    var tabla="tabla_consultarUsuarios_FC";
    // crearTabla("div_tabla_consultarUsuarios_FC",tabla,columnas,datos);
    crearTablaV3("div_tabla_consultarUsuarios_FC",tabla,columnas,datos);

    if(idioma=="_ve_es"){
        buscar = "Buscar:";
        mostrando = "Mostrando _START_ a _END_ de _TOTAL_ entidades";
    }else{
        buscar = "Search:";
        mostrando = "Showing _START_ to _END_ of _TOTAL_ entries";
    }


/*   oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": true,
        "sPaginationType": "full_numbers",
        "aoColumns": [
            { "sClass": "center" },
            { "sClass": "" },
            { "sClass": ""},
            {  "sClass": "center" },
            {  "sClass": "center" },
            {  "sClass": "center" }
        ],
        "oLanguage": {
            "sSearch": buscar, "sInfo":mostrando
        }

    });*/
   oTable = $('#'+tabla).dataTable({
        "iDisplayLength": 25,
        "bJQueryUI": false,
        "sPaginationType": "full_numbers",
       "aoColumns": [
           { "sClass": "center" },
           { "sClass": "" },
           { "sClass": ""},
           {  "sClass": "center" },
           {  "sClass": "center" },
           {  "sClass": "center" }
       ],
       "oLanguage": {
           "sSearch": buscar, "sInfo":mostrando
       }

    });

}

function FirmaConjuntaEditarUsuarioJSONData(id, tipoGrupo){
    var url = urlFirmaConjuntaConsultaUsuario;
    var param={};
    var jsonEditarUsuario=[];
    var datosUsuarioUBO={};

    datosUsuarioUBO.login = id;
    datosUsuarioUBO.tipo_grupo = tipoGrupo;

    jsonEditarUsuario[0]= datosUsuarioUBO;

    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});

    sendServiceJSON_sinc(url,param,FirmaConjuntaEditarUsuariosSuccess,null,null);
}

function FirmaConjuntaEditarUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    idioma = result.idioma;
    usuarioEditar = result.usuario;
    var roles= result.rolesCustom;

    $("#usuarioEU_FC").html(usuarioEditar.login);
    $("#nombreEU_FC").attr("value",usuarioEditar.nombres);
    $("#tipoPersonaEU_FC").attr("value",usuarioEditar.tipo_cirif);
    $("#grupoEU_FC").attr("value",usuarioEditar.tipo);
    $("#estatusEU_FC").attr("value",usuarioEditar.status_cuenta);
    $("#ciRifEU_FC").attr("value",usuarioEditar.cirif);
    $("#emailEU_FC").attr("value",usuarioEditar.email);
    $("#telefonoEU_FC").attr("value",usuarioEditar.telefono);
    //$("#telefonoCelEU_FC").attr("value",usuarioEditar.telefono_celular);
//    $("#telefonoCelEU_FC").val(usuarioEditar.telefono_celular).mask("(?9999) 999-9999");
    $("#telefonoCelEU_FC").val(usuarioEditar.telefono_celular);
    $("#direccionEU_FC").attr("value",usuarioEditar.direccion);
    $("#telefonoCodCelEU_FC").intlTelInput("setNumber", "+"+usuarioEditar.codigoPais);

    if (usuarioEditar.tipo=="11"){

        var campos="<table cellpadding='3' cellspacing='0' width='100%' border='0'>";
        $.each(roles,function(s,item){
            if (item.seleccionado=="S"){
                campos=campos+"<tr><td width='8%'><div id='div_chkEditRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesEditJSONData();' checked class='obligatorioRoles_editFC' id='chkEditRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
            }else{
                campos=campos+"<tr><td width='8%'><div id='div_chkEditRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesEditJSONData();' class='obligatorioRoles_editFC' id='chkEditRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
            }
            campos=campos+"<td><label class='datos negrita2' id='TAGROLEDIT"+item.codigoRol+"' for=''>"+item.nombreRol+"</label></td></tr>";

        });
        campos=campos+"</table>";
        $("#rolesDinamicosEdit").html(campos);

        mostrarOpcionesRolesEditJSONData();

        $("#asignacionRolesFCEdit").removeClass("oculto");
    }else{
        $("#asignacionRolesFCEdit").addClass("oculto");
    }



}


function seleccionarUsuariosFirmaConjuntaOpcion(id,tipoGrupo){

    $("#div_tabla_consultaCU_FC").fadeOut("fast");
    $("#div_tabla_editarUsuario_FC").fadeIn("fast");
    $("#asignacionRolesFCEdit").addClass("oculto");
    $("#rolesDinamicosEditLista").html("");
    $("#asignacionRolesFCEdit .obligatorioRoles_editFC").each(function(){
        $("#"+this.id).attr('checked', false);
        $("#div_"+this.id).removeClass("error_campo");
    });
    hdnCambioEstatus = "No";

    $(".obligatorio_EUFC ").each(function(){

        if($("#"+this.id).hasClass("error_campo"))
            $("#"+this.id).removeClass("error_campo");

    });
//    alert(tipoGrupo);
    FirmaConjuntaEditarUsuarioJSONData(id,tipoGrupo);
}

function FirmaConjuntaSalvarEditarUsuarioJSONData(){
    var url = urlFirmaConjuntaEditarUsuario;
    var param={};
    var jsonEditarUsuario=[];
    var datosUsuarioUBO={};

    datosUsuarioUBO.login = $("#usuarioEU_FC").html();
    datosUsuarioUBO.nombres = $("#nombreEU_FC").get(0).value;
    datosUsuarioUBO.tipo_cirif = $("#tipoPersonaEU_FC").get(0).value;
    datosUsuarioUBO.cirif = $("#ciRifEU_FC").get(0).value;
    datosUsuarioUBO.email = $("#emailEU_FC").get(0).value;
    datosUsuarioUBO.telefono = $("#telefonoEU_FC").get(0).value;
    // datosUsuarioUBO.telefono_celular = $("#telefonoCelEU_FC").get(0).value;
    datosUsuarioUBO.telefono_celular = $("#telefonoCelEU_FC").val();
    datosUsuarioUBO.codigoPais =$("#telefonoCodCelEU_FC").intlTelInput("getSelectedCountryData").dialCode;

    //refreshMask();
    datosUsuarioUBO.direccion = $("#direccionEU_FC").get(0).value;
    datosUsuarioUBO.tipo = $("#grupoEU_FC").get(0).value;

    if ($("#grupoEU_FC").get(0).value =="5")
        datosUsuarioUBO.tipo_grupo = "0000000011";
    else if ($("#grupoEU_FC").get(0).value =="6")
        datosUsuarioUBO.tipo_grupo = "0000000012";
    else if ($("#grupoEU_FC").get(0).value =="7")
        datosUsuarioUBO.tipo_grupo = "0000000013";
    else if ($("#grupoEU_FC").get(0).value =="8")
        datosUsuarioUBO.tipo_grupo = "0000000014";
    else if ($("#grupoEU_FC").get(0).value =="9")
        datosUsuarioUBO.tipo_grupo = "0000000015";
    else if ($("#grupoEU_FC").get(0).value =="11")
        datosUsuarioUBO.tipo_grupo = "0000000017";
    else
        datosUsuarioUBO.tipo_grupo = "0000000011";


    var roles="";
    $("#asignacionRolesFCEdit .obligatorioRoles_editFC").each(function(){

        if($("#"+this.id).is(':checked')){

            if (roles==""){
                roles=roles+"'"+$("#"+this.id).val()+"'";
            }else{
                roles=roles+",'"+$("#"+this.id).val()+"'";
            }

        }
    });

    datosUsuarioUBO.roles=roles;


    datosUsuarioUBO.status_cuenta = $("#estatusEU_FC").get(0).value;
    datosUsuarioUBO.cambioStatus = hdnCambioEstatus;

    jsonEditarUsuario[0]= datosUsuarioUBO;
    jsonEditarUsuario[1]= usuarioEditar;
    param.jsonEditarUsuario=JSON.stringify({"logins":jsonEditarUsuario});

    sendServiceJSON(url,param,FirmaConjuntaSalvarEditarUsuariosSuccess,null,null);
}


function FirmaConjuntaSalvarEditarUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var men = result.mensaje;
    idioma = result.idioma;

    $("#mensaje_error").empty();
    $("#mensaje_error").html(men);
    $("#div_mensajes_error").fadeIn("slow");
    $("#btnBackEU_FC").click();
    FirmaConjuntaUsuariosJSONData();

}

function cambioEstatusFC() {
//    alert("entre");
    hdnCambioEstatus = "Si";
}

function FirmaConjuntaSalvarAgregarUsuarioJSONData(){
    var url = urlFirmaConjuntaAgregarUsuario;
    var param={};
    var jsonAgregarUsuario=[];
    var datosUsuarioUBO={};

    datosUsuarioUBO.login = limpiar_espacios($("#AU_usuario_FC").get(0).value);
    datosUsuarioUBO.nombres = $("#AU_nombre_FC").get(0).value;
    datosUsuarioUBO.tipo_cirif = $("#AU_tipoPersona_FC").get(0).value;
    datosUsuarioUBO.cirif = $("#AU_aciRif_FC").get(0).value;
    datosUsuarioUBO.email = $("#AU_email_FC").get(0).value;
    datosUsuarioUBO.telefono = $("#AU_telefono_FC").get(0).value;
    // datosUsuarioUBO.telefono_celular = $("#AU_telefonoCel_FC").get(0).value;
    datosUsuarioUBO.telefono_celular = $("#AU_telefonoCel_FC").val();
    //datosUsuarioUBO.codigoPais = $("#AU_telefonoCodCel_FC").val();
    datosUsuarioUBO.codigoPais =$("#AU_telefonoCodCel_FC").intlTelInput("getSelectedCountryData").dialCode;
    datosUsuarioUBO.direccion = $("#AU_direccion_FC").get(0).value;
    //refreshMask();
    if ($("#AU_grupo_FC").get(0).value =="5")
        datosUsuarioUBO.tipo_grupo = "0000000011";
    else if ($("#AU_grupo_FC").get(0).value =="6")
        datosUsuarioUBO.tipo_grupo = "0000000012";
    else if ($("#AU_grupo_FC").get(0).value =="7")
        datosUsuarioUBO.tipo_grupo = "0000000013";
    else if ($("#AU_grupo_FC").get(0).value =="8")
        datosUsuarioUBO.tipo_grupo = "0000000014";
    else if ($("#AU_grupo_FC").get(0).value =="9")
        datosUsuarioUBO.tipo_grupo = "0000000015";
    else if ($("#AU_grupo_FC").get(0).value =="11")
        datosUsuarioUBO.tipo_grupo = "0000000017";
    else
        datosUsuarioUBO.tipo_grupo = "0000000011";


    var roles="";
    $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){

        if($("#"+this.id).is(':checked')){

            if (roles==""){
                roles=roles+"'"+$("#"+this.id).val()+"'";
            }else{
                roles=roles+",'"+$("#"+this.id).val()+"'";
            }

        }
    });

    datosUsuarioUBO.roles=roles;

    datosUsuarioUBO.tipo = $("#AU_grupo_FC").get(0).value;

    jsonAgregarUsuario[0]= datosUsuarioUBO;

    param.jsonAgregarUsuario=JSON.stringify({"logins":jsonAgregarUsuario});

    sendServiceJSON(url,param,FirmaConjuntaSalvarAgregarUsuariosSuccess,null,null);
}


function FirmaConjuntaSalvarAgregarUsuariosSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var men = result.mensaje;
    idioma = result.idioma;

    $("#div_carga_espera").addClass("oculto");
    $("#mensaje_error").empty();
    $("#mensaje_error").html(men);
    $("#div_mensajes_error").fadeIn("slow");
    if(result.codigo=="OK"){
        $("#div_tabla_consultaCU_FC").fadeIn("fast");
        $("#div_tabla_agregarUsuario_FC").fadeOut("fast");
        FirmaConjuntaUsuariosJSONData();
    }
}

function BackOfficeEnviarUsuarioJSONDataFC(id){
    var url = urlBackOfficeEnviarUsuario;
    var param={};
    var jsonParametros=[];
    jsonParametros[0]= $("#usuarioEU_FC").html();


    param.jsonParametros=JSON.stringify({"parametros":jsonParametros});
    sendServiceJSON(url,param,BackOfficeEnviarUsuarioFCSuccess,null,null);


}

function BackOfficeEnviarUsuarioFCSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var respuesta = result.respuesta;
    $("#div_carga_espera").addClass("oculto");
    if(respuesta=="OK"){
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            $("#mensaje_error").html("The user name was sent to the registered phone");
        else
            $("#mensaje_error").html("El nombre de usuario del cliente fue enviado a su telefono.");
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#mensaje_error").empty();
        if(idioma=="_us_en")
            $("#mensaje_error").html("The user name could not be sent to your phone.");
        else
            $("#mensaje_error").html("El nombre de usuario del cliente no pudo ser enviado a su telefono.");
        $("#div_mensajes_error").fadeIn("slow");
    }

}

function FirmaConjuntaUsuariosTiposJSONData(){
    var url = urlFirmaConjuntaConsultarUsuariosTipos;
    var param={};
    var jsonConsultaUsuarios=[];
    var datosConsultaUBO={};



    param.jsonConsultaUsuarios=JSON.stringify({"consultaUsuarioss":jsonConsultaUsuarios});

    sendServiceJSON(url,param,FirmaConjuntaUsuariosTiposSuccess,null,null);
}


function FirmaConjuntaUsuariosTiposSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    tipoGrupos = result.tipoGrupo;
    tipoCiRif = result.tipoCiRif;



    cargar_select("AU_contratoGrupo",tipoGrupos);
    // cargar_select("grupoEU_FC",tipoGrupos);

    cargar_select("AU_contratoCirif",tipoCiRif);
    // cargar_select("AU_tipoPersona_FC",tipoCiRif);


}

function refreshMask(){
    $.mask.definitions['~']='[12]';
    $.mask.definitions['+']='[246]';
    $.mask.definitions['/']='[123456789]';
    //$(".mascaraCelular").mask("(04~+) ?999-9999");
    $(".mascaraCelular").mask("?/99999999999");
    $(".mascaraCodPais").mask("?9999");
}


function mostrarRolesAdd(){

    if ($("#AU_grupo_FC").val()=="11"){
        rolesCustomFCJSONData();
        $("#asignacionRolesFCAdd").removeClass("oculto");
    } else{
        $("#asignacionRolesFCAdd").addClass("oculto");
    }
}


function rolesCustomFCJSONData(){
    var url = urlFirmaConjuntaRolesCustom;
    var param={};
    param.login=$("#AU_usuario_FC").val();

    sendServiceJSON_sinc(url,param,rolesCustomSuccess,null,null);
}


function rolesCustomSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var roles= result.rolesCustom;
    var campos="<table cellpadding='3' cellspacing='0' width='100%' border='0'>";
    $.each(roles,function(s,item){
        campos=campos+"<tr><td width='8%'><div id='div_chkAddRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesAddJSONData();' class='obligatorioRoles_addFC' id='chkAddRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
        campos=campos+"<td><label class='datos negrita2' id='TAGROL"+item.codigoRol+"' for=''>"+item.nombreRol+"</label></td></tr>";

    });
    campos=campos+"</table>";
    $("#rolesDinamicosAdd").html(campos);
}

function mostrarOpcionesRolesAddJSONData(){

    var url = urlFirmaConjuntaRolesCustomDettale;
    var param={};
    var codigo="";
    var cantidad=0;


    $("#asignacionRolesFCAdd .obligatorioRoles_addFC").each(function(){

        if($("#"+this.id).is(':checked')){
            cantidad++;
            if (codigo==""){
                codigo=codigo+"'"+$("#"+this.id).val()+"'";
            }else{
                codigo=codigo+",'"+$("#"+this.id).val()+"'";
            }

        }
    });
    if (cantidad>0){
        param.codigo=codigo;
        sendServiceJSON_sinc(url,param,mostrarOpcionesRolesAddSuccess,null,null);
    }else{
        $("#rolesDinamicosAddLista").html("");
    }

}

function mostrarOpcionesRolesAddSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var padres= result.permisos;
    var hijos= result.permisos;
    var lista="";
    var detalle="";

    var lista="<ul>";
    $.each(padres,function(s,padre){
        if (padre.tipo=="Padre"){
            lista=lista+"<li><span id='TAGOPCION"+padre.opcion+"'>"+padre.valor+"</span> (";
//            lista=lista+"TAGOPCION"+padre.opcion+"="+padre.valor+"<br>";
            var detalle="";
            $.each(hijos,function(d,hijo){
                if (hijo.tipo=="Hijo"){
                    if(padre.opcion==hijo.padre){
                        if (detalle==""){
                            detalle=detalle+"<span id='TAGOPCION"+hijo.opcion+"'>"+hijo.valor+"</span>";
                        }else{
                            detalle=detalle+", "+"<span id='TAGOPCION"+hijo.opcion+"'>"+hijo.valor+"</span>";
                        }
                        //detalle=detalle+"TAGOPCION"+hijo.opcion+"="+hijo.valor+"<br>";
                    }
                }
            });
            lista=lista+detalle+")</li>";
//            lista=lista+detalle;
        }
    });
    lista=lista+"</ul>";
    $("#rolesDinamicosAddLista").html(lista);
}


function mostrarRolesEdit(){

    if ($("#grupoEU_FC").val()=="11"){
        rolesCustomEditFCJSONData();
        $("#asignacionRolesFCEdit").removeClass("oculto");
    } else{
        $("#asignacionRolesFCEdit").addClass("oculto");
    }
}

function mostrarOpcionesRolesEditJSONData(){

    var url = urlFirmaConjuntaRolesCustomDettale;
    var param={};
    var codigo="";
    var cantidad=0;


    $("#asignacionRolesFCEdit .obligatorioRoles_editFC").each(function(){

        if($("#"+this.id).is(':checked')){
            cantidad++;
            if (codigo==""){
                codigo=codigo+"'"+$("#"+this.id).val()+"'";
            }else{
                codigo=codigo+",'"+$("#"+this.id).val()+"'";
            }

        }
    });
    if (cantidad>0){
        param.codigo=codigo;
        sendServiceJSON_sinc(url,param,mostrarOpcionesRolesEditSuccess,null,null);
    }else{
        $("#rolesDinamicosEditLista").html("");
    }

}

function mostrarOpcionesRolesEditSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var padres= result.permisos;
    var hijos= result.permisos;
    var lista="";
    var detalle="";

    var lista="<ul>";
    $.each(padres,function(s,padre){
        if (padre.tipo=="Padre"){
            lista=lista+"<li><span id='TAGOPCIONEDIT"+padre.opcion+"'>"+padre.valor+"</span> (";
//            lista=lista+"TAGOPCIONEDIT"+padre.opcion+"="+padre.valor+"<br>";
            var detalle="";
            $.each(hijos,function(d,hijo){
                if (hijo.tipo=="Hijo"){
                    if(padre.opcion==hijo.padre){
                        if (detalle==""){
                            detalle=detalle+"<span id='TAGOPCIONEDIT"+hijo.opcion+"'>"+hijo.valor+"</span>";
                        }else{
                            detalle=detalle+", "+"<span id='TAGOPCIONEDIT"+hijo.opcion+"'>"+hijo.valor+"</span>";
                        }
                        //detalle=detalle+"TAGOPCIONEDIT"+hijo.opcion+"="+hijo.valor+"<br>";
                    }
                }
            });
            lista=lista+detalle+")</li>";
//            lista=lista+detalle;
        }
    });
    lista=lista+"</ul>";
    $("#rolesDinamicosEditLista").html(lista);
}





function rolesCustomEditFCJSONData(){
    var url = urlFirmaConjuntaRolesCustom;
    var param={};
    param.login=$("#usuarioEU_FC").text();

    sendServiceJSON_sinc(url,param,rolesCustomEditSuccess,null,null);
}


function rolesCustomEditSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var roles= result.rolesCustom;
    var campos="<table cellpadding='3' cellspacing='0' width='100%' border='0'>";
    $.each(roles,function(s,item){
        if (item.seleccionado=="S"){
            campos=campos+"<tr><td width='8%'><div id='div_chkEditRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesEditJSONData();' checked class='obligatorioRoles_editFC' id='chkEditRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
        }else{
            campos=campos+"<tr><td width='8%'><div id='div_chkEditRol"+item.codigoRol+"'><input type='checkbox' onclick='mostrarOpcionesRolesEditJSONData();' class='obligatorioRoles_editFC' id='chkEditRol"+item.codigoRol+"' value='"+item.codigoRol+"'></div></td>";
        }
        campos=campos+"<td><label class='datos negrita2' id='TAGROLEDIT"+item.codigoRol+"' for=''>"+item.nombreRol+"</label></td></tr>";

    });
    campos=campos+"</table>";
    $("#rolesDinamicosEdit").html(campos);
    mostrarOpcionesRolesEditJSONData();
}


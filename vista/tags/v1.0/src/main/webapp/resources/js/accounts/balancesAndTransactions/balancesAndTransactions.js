var urlAccountStatementCargar_BT="Accounts_cargarAccountStatement.action";
var urlDetalleBalancesAndTransaction_BT="Accounts_consultarBalancesAndTransaction.action";
var urlConsultarCuentaBalancesAndTransaction_BT="Accounts_consultarCuentaSaldoTrans.action";


var idioma="";
var noInfo="";
var oTable="";


$(document).ready(function(){

    $( "#fechaDesdeFiltroBT").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});
    $( "#fechaHastaFiltroBT").mask('00/00/0000', {'translation': {0: {pattern: /[0-9*]/}}});

   /* $( "#fechaDesdeFiltroBT").blur(function(){
        if(!isDate($( "#fechaDesdeFiltroBT").val()) && !Empty($( "#fechaDesdeFiltroBT").val())){
            $("#mensaje_error").empty();
            $(this).val("");
            if(idioma=="_us_en")
                $("#mensaje_error").html("From date is not correct");
            else
                $("#mensaje_error").html("La fecha desde no es correcta");

            $("#div_mensajes_error").fadeIn("slow");
        }
    });  */

  /*  $( "#fechaHastaFiltroBT").blur(function(){
        if(!isDate($( "#fechaHastaFiltroBT").val())&& !Empty($( "#fechaHastaFiltroBT").val())){
            $("#mensaje_error").empty();
            if(idioma=="_us_en")
                $("#mensaje_error").html("From date is not correct");
            else
                $("#mensaje_error").html("La fecha desde no es correcta");

            $("#div_mensajes_error").fadeIn("slow");
        }

    });   */

    console.log('paginate_button');
    $('#estado_cuenta_tabla_consulta_BT_paginate span').on('click', function(event){
        console.log('bottton paginacion');
    });


    $( "#fechaDesdeFiltroBT" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});
    $( "#fechaHastaFiltroBT" ).datepicker({ dateFormat: "dd/mm/yy",changeMonth: true,changeYear: true});


    $("#estado_cuenta_consultar_BT").click(function(){
        $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
        realizarConsultaBT();
    });

});

function abrirDetalleTabla(elemento){
    var nTr = $(elemento).parents('tr')[0];
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
        oTable.fnOpen( nTr, fnFormatDetailsAccount_BT($(elemento)), 'details');
    }

};



function fnFormatDetailsAccount_BT (valor)
{   var sOut = '<table width="100%">';
    if(idioma=="_us_en"){
        if(valor.get(0).title=="CHC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiary </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Currency rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGPlazaLugar">Place </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> Check Number </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span>' +
                '<img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CIC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraOrigen">Client Id - Source </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero"> Account N° - Source </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Originator </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasa2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CID"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiary  </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Currency rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Client Id - Destiny</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGCuentaNumeroD">Client Id - Destiny </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="MFC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraOrigen"> Client Id - Source </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGEmpresaOrigen">Company name - Source </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Originator </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasa2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="MFD"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiary</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraDestino">Client Id - Destiny</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGEmpresaDestino">Company name - Destiny </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="RCH"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Originator </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> Check Number  </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasa2">Currency Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='<td width="25%"></td>';
            sOut +='<td width="25%"></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="DP"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Originator </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> Check Number   </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBancoEmisor">Issuer Bank</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Currency rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="STP"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiary</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGMotivo">Description</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> Check Number </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TIC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGClienteOrigen">Client name - Source</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraOrigen"> Client Id - Source</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGColocacionesOrigen">Time Deposits source</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TID"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGClienteDestino">Client name - destiny</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraDestino">Client Id - Destiny</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGColocacionesDestino">Time Deposits Destiny</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CIF"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGNombreCompañia">Company Name</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Currency rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="colocacionessaldos_TAGCuentaNumero">Account N°</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="colocacionessaldos_TAGBanco">Bank</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="colocacionessaldos_TAGDescripcion">Description</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[4]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="colocacionessaldos_TAGReferencia">Reference</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[5]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGFechaIncorporacion">Incorporation Date</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[6]+'</span></td>';
            sOut +='<td width="25%"> </td>';
            sOut +='<td width="25%"><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="IAL"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGFechaPrestamo">Loan Date</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Currency rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGPlazo">Term</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGTasaCambioReferencial">Interest Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CSF"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGTituloValor">Financial Asset</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGRendimiento">Return (Yield)</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGBancoOrigen">Originators Bank</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CPA"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGCupon">Coupon</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGRendimiento">Return (Yield)</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGEmisor">Issuer</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGFechaVencimiento">Maturity</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[4]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGPrecio">Price</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[5]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TEI"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Originator</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBancoOrigen">Originators Bank </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGReceptorInfo">Receiver Information</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TEO"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficiarioInfo">Beneficiary</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBancoBeneficiario">Beneficiary Bank</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficarioInfo">Beneficiary Information</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';
        }else if(valor.get(0).title=="CCPA"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGObservacion">Observation</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[1]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';
        }else{
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGObservacion">Observation</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Currency Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[1]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }
    }else{
        if(valor.get(0).title=="CHC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiario </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio2">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGPlazaLugar">Plaza Lugar </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> N° Cheque </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span>' +
                '<img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CIC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraOrigen">Cartera Origen </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Cuenta Origen </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Ordenante </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CID"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiario </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Cartera Destino </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Cuenta Destino </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="MFC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraOrigen"> Cartera Origen </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGEmpresaOrigen">Nombre Empresa </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Ordenante </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="MFD"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiario</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraDestino">Cartera Destino</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGEmpresaDestino">Empresa destino </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="RCH"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Ordenante </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> N° Cheque  </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='<td width="25%"></td>';
            sOut +='<td width="25%"></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="DP"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Ordenante </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> N° Cheque   </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBancoEmisor">Banco Emisor</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="STP"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiario</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGMotivo">Observaciones</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGNumCheque"> N° Cheque </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TIC"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGClienteOrigen">Cliente Origen</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraOrigen"> Cartera Origen</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGColocacionesOrigen">Time Deposits source</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TID"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGClienteDestino">Cliente Destino</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGCarteraDestino">Cartera Destino</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGColocacionesDestino">Colocaciones Destino</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CIF"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGNombreCompañia">Nombre Compañia</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="colocacionessaldos_TAGCuentaNumero">Account N°</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="colocacionessaldos_TAGBanco">Bank</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="colocacionessaldos_TAGDescripcion">Observaciones</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[4]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="colocacionessaldos_TAGReferencia">Reference</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[5]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGFechaIncorporacion">Fecha Incorporación</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[6]+'</span></td>';
            sOut +='<td width="25%"> </td>';
            sOut +='<td width="25%"><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="IAL"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGFechaPrestamo">Fecha Préstamo</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGPlazo">Term</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGTasaCambioReferencial">Interest Rate</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CSF"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGTituloValor">Financial Asset</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGRendimiento">Rendimiento</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGBancoOrigen">Banco Origen</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="CPA"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGCupon">Coupon</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGRendimiento">Rendimiento</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGEmisor">Issuer</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGFechaVencimiento">Fecha Vencimiento</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[4]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentassaldos_TAGPrecio">Precio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[5]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TEI"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGOrdenante">Ordenante</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBancoOrigen">Banco Origen </span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGReceptorInfo">Información Receptor</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else if(valor.get(0).title=="TEO"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficiarioInfo">Beneficiarior</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"><span class="label_descp" id="cuentassaldos_TAGBancoBeneficiario">Banco Beneficiario</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
            sOut +='</tr>';
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentassaldos_TAGBeneficarioInfo">Información Beneficiario</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';
        }else if(valor.get(0).title=="CCPA"){
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGObservacion">Observación</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[1]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }else{
            sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasedocuenta_TAGObservacion">Observación</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
            sOut +='<td width="25%"> <span class="label_descp" id="cuentasBalance_TAGTasaCambio">Tasa de Cambio</span><span class="label_descp">:</span></td>';
            sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[1]+'</span><img style="cursor:pointer;margin-left:40px" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
            sOut +='</tr>';
            sOut +='</table>';

        }

    }



    return sOut;
}


function cargarDetalleCuenta(value){
    if(value!="-2"){
        detalleSaldoTransJSONData();
        $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
        $("#estado_cuenta_div_tabla_consulta_BT").empty();
//      $("#fechas_BT").fadeOut("fast");
//      $("#fechaDesdeFiltroBT").val("");
//      $("#fechaHastaFiltroBT").val("");
//      $("#transaccion_desde_BT").val("5");
    }
    else{
        $("#estado_cuenta_numero_cuenta_BT").removeClass("error_campo");

        $("#titular_BT").html("");
        $("#moneda_BT").html("");
        $("#bloqueado_BT").html("");
        $("#diferido_BT").html("");
        $("#disponible_BT").html("");
        $("#saldo_actual_BT").html("");
//      if(idioma=="_us_en"){
        $("#tagAccount_DatosCuenta_BT").attr("style","display: ");
        $("#tagAccount_DatosCuenta_BT2").attr("style","display: none");
        $("#tagAccount_fecha_BT").html("");
//      }
//      else{
//          $("#tagAccount_DatosCuenta_BT").html("Saldo");
//          $("#tagAccount_fecha_BT").html("");
//      }
        $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
        $("#estado_cuenta_div_tabla_consulta_BT").empty();
        $("#fechas_BT").fadeOut("fast");
        $("#fechaDesdeFiltroBT").val("");
        $("#fechaHastaFiltroBT").val("");
        $("#transaccion_desde_BT").val("5");
    }


};

function validarActivarFechas(valor){
    //var myDate = new Date();
    if(valor=="-1"){
        $("#fechaDesdeFiltroBT").val("");
        //$("#fechaHastaFiltroBT").val((myDate.getDate()) + '/' + (((myDate.getMonth()+1)>10)?(myDate.getMonth()+1):'0'+(myDate.getMonth()+1)) + '/' + myDate.getFullYear());
        $("#fechas_BT").fadeIn("fast");
    }
    else{
        $("#fechas_BT").fadeOut("fast");
        $("#fechaDesdeFiltroBT").val("");
        $("#fechaHastaFiltroBT").val("");
    }

};


function BalancesAndTransactionInfoPaginaJSONData(){
    var url = urlAccountStatementCargar_BT;
    var param={};
    $("#mensaje_error").empty();
    $("#div_mensajes_error").fadeOut("fast");
    $("#div_account_BalancesAndTransactions .selectFormulario_Account_BA").each(function(){
        this.value="-2";
    });
    $("#div_account_BalancesAndTransactions .inputFormulario_Account_BA").each(function(){
        this.value="";
    });
    $("#div_account_BalancesAndTransactions .spanFormulario_Account_BA").each(function(){
        this.innerHTML="";
    });
    $("#fechas_BT").attr("style","display: none");
    $("#tipo_transaccion_BT").empty();
    $("#transaccion_desde_BT").empty();

    $("#btnVolverPortafolio").addClass("oculto");

    $("#estado_cuenta_div_tabla_consulta_BT").empty();
    $("#paginacion_estado_cuenta_tabla_consulta_BT").empty();
//    $("#tagAccount_DatosCuenta_BT").attr("style","display: ");
    sendServiceJSON(url,param,BalancesAndTransactionInfoSuccess,null,null);
}


function BalancesAndTransactionInfoSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var cuentasBT = result.cuentas;
    var periodo = result.transaccionesPeriodo;
    var codigo = result.codigo;
    idioma = result.idioma;

    if(codigo=="0" && cuentasBT!=null){
        if(idioma=="_us_en")
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BT", cuentasBT,"Select","-2");
        else
            cargar_selectPersonal("estado_cuenta_numero_cuenta_BT", cuentasBT,"Seleccione","-2");

        if(periodo!=null)
            cargar_selectPersonal2("transaccion_desde_BT", periodo);

        $("#div_account_BalancesAndTransactions").attr("style","display: ");
        $("#noInfo_balancesAndTransactions_account").attr("style","display: none");
        if(idioma=="_us_en")
            $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked Amount </span><span>:</span>");
        else
            $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Monto Bloqueado </span><span>:</span>");
    }else{
        $("#div_account_BalancesAndTransactions").attr("style","display: none");
        $("#noInfo_balancesAndTransactions_account").attr("style","display: ");
    }
    $("#botonVolverASaldoCuentas").attr("style","display: none");
    BlockedAmountInfoPaginaJSONData();


}

function detalleSaldoTransJSONData(){
    var url = urlDetalleBalancesAndTransaction_BT;
    var param={};
    var jsonDetalleSaldoTrans=[];

    jsonDetalleSaldoTrans[0] =  $("#estado_cuenta_numero_cuenta_BT").get(0).value;

    param.jsonDetalleSaldoTrans=JSON.stringify({"parametros":jsonDetalleSaldoTrans});

    sendServiceJSON(url,param,detalleSaldoTransSuccess,null,null);
}


function detalleSaldoTransSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var detalle = result.detalle;
    var  tipoTrans = result.tipoTransacciones;
    var mensaje = result.mensaje;
    var codigo = result.codigo;
    var numCuenta = result.numeroCuenta;

    if(codigo !="0"){
        $("#titular_BT").html(detalle.cliente);
        $("#moneda_BT").html(detalle.moneda);
        if(detalle.bloqueado!="0,00"){
//            if(idioma=="_us_en")
//                $("#linkBloqueo_account").html("<a id='"+numCuenta+"' onclick='detalleBloqueoAccount(this.id);' style='cursor:pointer; text-decoration: underline' ><span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado' title='Click to see details of blocked amount'>Blocked Amount </span><span>:</span></a>");
//            else
//                $("#linkBloqueo_account").html("<a id='"+numCuenta+"' onclick='detalleBloqueoAccount(this.id);' style='cursor:pointer; text-decoration: underline'><span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'  title='Haga click para ver detalle del monto bloqueado'>Monto Bloqueado</span><span>:</span></a>");
            if(idioma=="_us_en")
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked Amount </span><span>:</span>");
            else
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Monto Bloqueado </span><span>:</span>");

        }else{
            if(idioma=="_us_en")
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Blocked Amount </span><span>:</span>");
            else
                $("#linkBloqueo_account").html("<span class='CBT_label_montoBloqueado' id='cuentassaldos_TAGBloqueado'>Monto Bloqueado </span><span>:</span>");
        }

        $("#bloqueado_BT").html(detalle.bloqueado);
        $("#diferido_BT").html(detalle.diferido);
        $("#disponible_BT").html(detalle.saldoDisp);
        $("#saldo_actual_BT").html(detalle.saldoActual);
//        if(idioma=="_us_en"){
//            $("#tagAccount_DatosCuenta_BT").attr("style","display: none");
//            $("#tagAccount_DatosCuenta_BT2").attr("style","display: ");
//            $("#tagAccount_fecha_BT").html(" "+"<b>"+detalle.fechaCierre+"</b>");
//        }
//        else{
        $("#tagAccount_DatosCuenta_BT").attr("style","display: none");
        $("#tagAccount_DatosCuenta_BT2").attr("style","display: ");
        $("#tagAccount_fecha_BT").html(" "+"<b>"+detalle.fechaCierre+"</b>");
//        }



        if(tipoTrans!=null){
            if(idioma=="_us_en")
                cargar_selectPersonal("tipo_transaccion_BT", tipoTrans,"All","-2");
            else
                cargar_selectPersonal("tipo_transaccion_BT", tipoTrans,"Todos","-2");

            realizarConsultaBT();
        }
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }





}

function realizarConsultaBT(){
    var mensaje="";
    $(".requeridoBT").each(function(){
        if(Trim($("#"+this.id).val())=="" || $("#"+this.id).val()=="-2"){
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
//
    if($("#transaccion_desde_BT").val()=="-1")
    {
        $(".requeridoFechaBT").each(function(){
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

        if(!isDate($( "#fechaDesdeFiltroBT").val())){
            if(idioma=="_us_en")
                mensaje=mensaje+"From date is not correct"+"<br>";
            else
                mensaje=mensaje+"La fecha Desde no es correcta"+"<br>";
            $("#fechaDesdeFiltroBT").addClass("error_campo");
        }else if(!isDate($( "#fechaHastaFiltroBT").val())){
            if(idioma=="_us_en")
                $("#mensaje_error").html('To date is not correct');
            else
                $("#mensaje_error").html('La fecha Hasta no es correcta');
            $("#div_mensajes_error").fadeIn("slow");
            $("#fechaHastaFiltroBT").addClass("error_campo");
        }else{
            var fdesdeFormat= $("#fechaDesdeFiltroBT").val().split("/")[1]
                +"/"+$("#fechaDesdeFiltroBT").val().split("/")[0]
                +"/"+$("#fechaDesdeFiltroBT").val().split("/")[2]  ;
            var Fdesde = Date.parse(fdesdeFormat);

            var fhastaFormat= $("#fechaHastaFiltroBT").val().split("/")[1]
                +"/"+$("#fechaHastaFiltroBT").val().split("/")[0]
                +"/"+$("#fechaHastaFiltroBT").val().split("/")[2] ;
            var Fhasta = Date.parse(fhastaFormat);

            if(Fdesde>Fhasta){
                if(idioma=="_us_en")
                    mensaje=mensaje+"'From date' can not be greater than the 'to Date'"+"<br>";
                else
                    mensaje=mensaje+"La fecha Desde no puede ser mayor que la fecha Hasta."+"<br>";
                $("#fechaDesdeFiltroBT").addClass("error_campo");
                $("#fechaHastaFiltroBT").addClass("error_campo");

            }else{
                $("#fechaDesdeFiltroBT").removeClass("error_campo");
                $("#fechaHastaFiltroBT").removeClass("error_campo");
            }
        }
    }

    if(mensaje!=""){
        $("#estado_cuenta_div_tabla_consulta_BT").fadeOut("fast");
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }else{
        $("#div_mensajes_error").fadeOut("fast");
        $("#estado_cuenta_div_tabla_consulta_BT").fadeIn("fast");
        consultarCuentaSaldoTransJSONData();
        $("#balance_imprimir").fadeIn("fast");
    }
}

function consultarCuentaSaldoTransJSONData(){
    var url = urlConsultarCuentaBalancesAndTransaction_BT;
    var param={};
    var jsonDetalleSaldoTrans=[];

    jsonDetalleSaldoTrans[0] =  $("#estado_cuenta_numero_cuenta_BT").get(0).value;

    jsonDetalleSaldoTrans[1] = $("#transaccion_desde_BT").get(0).value;
    jsonDetalleSaldoTrans[2] = $("#fechaDesdeFiltroBT").get(0).value;
    jsonDetalleSaldoTrans[3] = $("#fechaHastaFiltroBT").get(0).value;

    jsonDetalleSaldoTrans[4] = $("#tipo_transaccion_BT").get(0).value;

    param.jsonDetalleSaldoTrans=JSON.stringify({"parametros":jsonDetalleSaldoTrans});

    sendServiceJSON(url,param,consultarCuentaSaldoTransSuccess,null,null);
}

function crearPDF_balancesAndTransactions(){
    var miValue = $("#estado_cuenta_numero_cuenta_BT" ).val();
    $("#estado_cuenta_numero_cuenta_BT option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#tipo_transaccion_BT" ).val();
    $("#tipo_transaccion_BT option[value='"+miValue+"']").attr("selected",true);

    miValue = $("#transaccion_desde_BT" ).val();
    $("#transaccion_desde_BT option[value='"+miValue+"']").attr("selected",true);

    $('#estado_cuenta_numero_cuenta_BT_select').html($('#estado_cuenta_numero_cuenta_BT option:selected').text());

    printPageElement('div_SALDOS_CUENTAS');  //Print EDO CUENTA

}

function consultarCuentaSaldoTransSuccess(originalRequest){
    //                   this is the json return data
    var result = originalRequest;
    var p_sclass=[
        { "sClass": "center" },
        { "sClass": "center" },
        { "sClass": "center"},
        {  "sClass": "" },
        {  "sClass": "center" },
        {  "sClass": "right" },
        { "sClass": "right" }
    ];
    var columnas = result.contenidoTabla_culumnasTest;
    var datos = result.contenidoTabla_infoTest;
    var mensaje = result.mensaje;
    var codigo = result.codigo;
    var tamano_pag=20;
    var tabla;
    var tabla_id="estado_cuenta_tabla_consulta_BT";
    var id_contenedor="estado_cuenta_div_tabla_consulta_BT";
    idioma = result.idioma;
    if(idioma=="_us_en")
        noInfo="No data available";
    else
        noInfo="No hay informaci&oacute;n disponible";

    if(codigo!="0"){
        tabla=crearTabla_paginacion(id_contenedor,tabla_id,columnas,datos,0,tamano_pag,p_sclass);

        tabla.dataTable({
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
        });

        oTable=tabla;

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
    }else{
        $("#mensaje_error").empty();
        $("#mensaje_error").html(mensaje);
        $("#div_mensajes_error").fadeIn("slow");
    }


}

function llamarDescrip_click(id){

    $("#label_Beneficiary").empty();
    $("#label_Banck").empty();
    $("#label_Observation").empty();

    $("#label_Beneficiary").html(id.split("|")[0]);
    $("#label_Banck").html(id.split("|")[1]);
    $("#label_Observation").html(id.split("|")[2]);

    $("#div_mensajes_info_desc_tabla").fadeIn("slow");


}

function llamarDescrip_out(img){


    $("#div_mensajes_info_desc_tabla").fadeOut("fast");
    $("#mensajes_info_desc_tabla").empty();

}



function imprimir_detalle_AccountBT(valor,tile){

    var urlreportaccountBT="reportaccountBT.action";

    if(tile=="CHC"){
        var beneficiaro;
        var tasacambio;
        var plazalugar;
        var num_cheque;
        var fechaOperacion;
        var fechaValor;
        var referencia;
        var mensaje;
        var monto;
        var num_cta=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);
        if (Trim(valor.split("|")[0]) != "") {
            beneficiaro = valor.split("|")[0];
        } else {
            beneficiaro = "_";
        };
        if (Trim(valor.split("|")[1]) != "") {
            tasacambio = valor.split("|")[1];
        } else {
            tasacambio = "_";
        };
        if (Trim(valor.split("|")[2]) != "") {
            plazalugar = valor.split("|")[2];
        } else {
            plazalugar = "_";
        };
        if (Trim(valor.split("|")[3]) != "") {
            num_cheque = valor.split("|")[3];
        } else {
            num_cheque = "_";
        };
        if (Trim(valor.split("|")[4]) != "") {
            fechaOperacion = valor.split("|")[4];
        } else {
            fechaOperacion = "_";
        };
        if (Trim(valor.split("|")[5]) != "") {
            fechaValor = valor.split("|")[5];
        } else {
            fechaValor = "_";
        };
        if (Trim(valor.split("|")[6]) != "") {
            plazalugar = valor.split("|")[6];
        } else {
            referencia = "_";
        };
        if (Trim(valor.split("|")[7]) != "") {
            mensaje = valor.split("|")[7];
        } else {
            mensaje = "_";
        };
        if (Trim(valor.split("|")[8]) != "") {
            monto = valor.split("|")[8];
        } else {
            monto = "_";
        };

        beneficiaro=$().crypt({method: "b64enc",source:beneficiaro});
        tasacambio=$().crypt({method: "b64enc",source:tasacambio});
        plazalugar=$().crypt({method: "b64enc",source:plazalugar});
        num_cheque=$().crypt({method: "b64enc",source:num_cheque});
        fechaOperacion=$().crypt({method: "b64enc",source:fechaOperacion});
        fechaValor=$().crypt({method: "b64enc",source:fechaValor});
        referencia=$().crypt({method: "b64enc",source:referencia});
        mensaje=$().crypt({method: "b64enc",source:mensaje});
        monto=$().crypt({method: "b64enc",source:monto});
        num_cta=$().crypt({method: "b64enc",source:num_cta});
        tile=$().crypt({method: "b64enc",source:tile});

        urlreportaccountBT = urlreportaccountBT + "?beneficiario="+beneficiario+"&tasadecambio="+tasacambio+"&descripcion="+plazalugar+"&num_cheque="+num_cheque+"&referencia="+referencia+"&fecha_operacion="+fechaOperacion+"&fechaValor="+fechaValor+"&monto="+monto+"&tipMov="+tile+"&num_cta="+num_cta+"&mensaje="+mensaje;
    }else if(tile=="CIC"){
        var cartaOrigen;
        var cuentaOrigen;
        var ordenante;
        var tasadecambio;
        var fechaOperacion;
        var fechaValor;
        var referencia;
        var mensaje;
        var monto;
        var num_cta=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);
        if (Trim(valor.split("|")[0]) != "") {
            cartaOrigen = valor.split("|")[0];
        } else {
            cartaOrigen = "_";
        };
        if (Trim(valor.split("|")[1]) != "") {
            cuentaOrigen = valor.split("|")[1];
        } else {
            cuentaOrigen = "_";
        };
        if (Trim(valor.split("|")[2]) != "") {
            ordenante = valor.split("|")[2];
        } else {
            ordenante = "_";
        };
        if (Trim(valor.split("|")[3]) != "") {
            tasadecambio = valor.split("|")[3];
        } else {
            tasadecambio = "_";
        };
        if (Trim(valor.split("|")[4]) != "") {
            fechaOperacion = valor.split("|")[5];
        } else {
            fechaOperacion = "_";
        };
        if (Trim(valor.split("|")[5]) != "") {
            fechaValor = valor.split("|")[6];
        } else {
            fechaValor = "_";
        };
        if (Trim(valor.split("|")[4]) != "") {
            referencia = valor.split("|")[4];
        } else {
            referencia = "_";
        };
        if (Trim(valor.split("|")[7]) != "") {
            mensaje = valor.split("|")[7];
        } else {
            mensaje = "_";
        };
        if (Trim(valor.split("|")[8]) != "") {
            monto = valor.split("|")[8];
        } else {
            monto = "_";
        };
        cartaOrigen=$().crypt({method: "b64enc",source:cartaOrigen});
        cuentaOrigen=$().crypt({method: "b64enc",source:cuentaOrigen});
        ordenante=$().crypt({method: "b64enc",source:ordenante});
        tasadecambio=$().crypt({method: "b64enc",source:tasadecambio});
        referencia=$().crypt({method: "b64enc",source:referencia});
        fechaValor=$().crypt({method: "b64enc",source:fechaValor});
        fechaOperacion=$().crypt({method: "b64enc",source:fechaOperacion});
        mensaje=$().crypt({method: "b64enc",source:mensaje});
        monto=$().crypt({method: "b64enc",source:monto});
        num_cta=$().crypt({method: "b64enc",source:num_cta});
        tile=$().crypt({method: "b64enc",source:tile});

        urlreportaccountBT = urlreportaccountBT + "?beneficiario="+cartaOrigen+"&descripcion="+cuentaOrigen+"&observacion="+ordenante
            +"&tasadecambio="+tasadecambio+"&referencia="+referencia+"&fecha_operacion="+fechaOperacion+"&fechaValor="+fechaValor
            +"&monto="+monto+"&tipMov="+tile+"&num_cta="+num_cta+"&mensaje="+mensaje;
    }else if(tile=="CID"){
        var cartaOrigen;
        var cuentaOrigen;
        var ordenante;
        var tasadecambio;
        var fechaOperacion;
        var fechaValor;
        var referencia;
        var mensaje;
        var monto;
        var num_cta=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);
        if (Trim(valor.split("|")[0]) != "") {
            cartaOrigen = valor.split("|")[0];
        } else {
            cartaOrigen = "_";
        }
        ;
        if (Trim(valor.split("|")[1]) != "") {
            tasadecambio = valor.split("|")[1];
        } else {
            tasadecambio = "_";
        }
        ;
        if (Trim(valor.split("|")[2]) != "") {
            cuentaOrigen = valor.split("|")[2];
        } else {
            cuentaOrigen = "_";
        }
        ;
        if (Trim(valor.split("|")[3]) != "") {
            ordenante = valor.split("|")[3];
        } else {
            ordenante = "_";
        }
        ;
        if (Trim(valor.split("|")[4]) != "") {
            referencia = valor.split("|")[4];
        } else {
            referencia = "_";
        }
        ;
        if (Trim(valor.split("|")[5]) != "") {
            fechaOperacion = valor.split("|")[5];
        } else {
            fechaOperacion = "_";
        }
        ;
        if (Trim(valor.split("|")[6]) != "") {
            fechaValor = valor.split("|")[6];
        } else {
            fechaValor = "_";
        }
        ;
        if (Trim(valor.split("|")[7]) != "") {
            mensaje = valor.split("|")[7];
        } else {
            mensaje = "_";
        }
        ;
        if (Trim(valor.split("|")[8]) != "") {
            monto = valor.split("|")[8];
        } else {
            monto = "_";
        }
        ;

        cartaOrigen=$().crypt({method: "b64enc",source:cartaOrigen});
        cuentaOrigen=$().crypt({method: "b64enc",source:cuentaOrigen});
        ordenante=$().crypt({method: "b64enc",source:ordenante});
        tasadecambio=$().crypt({method: "b64enc",source:tasadecambio});
        referencia=$().crypt({method: "b64enc",source:referencia});
        fechaValor=$().crypt({method: "b64enc",source:fechaValor});
        fechaOperacion=$().crypt({method: "b64enc",source:fechaOperacion});
        mensaje=$().crypt({method: "b64enc",source:mensaje});
        monto=$().crypt({method: "b64enc",source:monto});
        num_cta=$().crypt({method: "b64enc",source:num_cta});
        tile=$().crypt({method: "b64enc",source:tile});

        urlreportaccountBT = urlreportaccountBT + "?beneficiario="+cartaOrigen+"&descripcion="+cuentaOrigen+"&observacion="+ordenante
            +"&tasadecambio="+tasadecambio+"&referencia="+referencia+"&fecha_operacion="+fechaOperacion+"&fechaValor="+fechaValor
            +"&monto="+monto+"&tipMov="+tile+"&num_cta="+num_cta+"&mensaje="+mensaje;

    }else if(tile=="MFC"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Cartera Origen </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Empresa Origen </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Ordenante </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="MFD"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiario </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Cartera Destino</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Empresa Destino </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="RCH"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Ordenante </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">N° Cheque </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Tasa de Cambio</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='<td width="25%"></td>';
        sOut +='<td width="25%"></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="DP"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Ordenante </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">N° Cheque  </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Banco Emisor</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="STP"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Beneficiario </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Motivo</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">N° Cheque</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="TIC"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Cliente Origen</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Cartera Origen</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Colocaci&oacute;n Origen</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="TID"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Cliente Destino</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Cartera Destino</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Colocaci&oacute;n Destino</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="CIF"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Nombre Compañia</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Cuenta N°</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Banco</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Observaciones</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Referencia</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[5]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Fecha Incorporaci&oacute;n</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[6]+'</span></td>';
        sOut +='<td width="25%"> </td>';
        sOut +='<td width="25%"><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="IAL"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Fecha Pr&eacute;stamo</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Plazo</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Tasa de Inter&eacute;s</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="CSF"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">T&iacute;tulo Valor</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Rendimiento</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Banco Origen</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';

    }else if(tile=="CPA"){
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBeneficiario">Cup&oacute;n</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Beneficiary_TDBT">'+valor.get(0).id.split("|")[0]+'</span></td>';
        sOut +='<td width="25%"><span class="label_descp" id="cuentasBalance_TAGCuentaNumero">Tasa de Cambio </span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Account_TDBT">'+valor.get(0).id.split("|")[1]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Rendimiento</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[2]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Emisor</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[3]+'</span></td>';
        sOut +='</tr>';
        sOut +='<tr><td width="25%"><span class="label_descp" id="cuentasBalance_TAGBanco">Fecha Vencimiento</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Bank_TDBT">'+valor.get(0).id.split("|")[4]+'</span></td>';
        sOut +='<td width="25%"> <span class="label_descp" id="cuentasedocuenta_TAGMoneda">Precio</span><span class="label_descp">:</span></td>';
        sOut +='<td width="25%"><span id="label_Currency_TDBT">'+valor.get(0).id.split("|")[5]+'</span><img style="cursor:pointer" onclick="imprimir_detalle_AccountBT(\''+valor.get(0).id+'\',\''+valor.get(0).title+'\')"  width="18" height="15"  src="../vbtonline/resources/images/comun/impresora.gif" ></td>';
        sOut +='</tr>';
        sOut +='</table>';
    }else if(tile=="TEI"){
        var beneficiario;
        var banco_origen;
        var informacion;
        var tasadecambio;
        var fechaOperacion;
        var fechaValor;
        var referencia;
        var mensaje;
        var monto;

        var num_cta=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);
        if(Trim(valor.split("|")[0])!=""){beneficiario=valor.split("|")[0];}else{beneficiario="_";};
        if(Trim(valor.split("|")[1])!=""){banco_origen=valor.split("|")[1];}else{banco_origen="_";};
        if(Trim(valor.split("|")[2])!=""){informacion=valor.split("|")[2];}else{informacion="_";};
        if(Trim(valor.split("|")[3])!=""){tasadecambio=valor.split("|")[3];}else{tasadecambio="_";};
        if(Trim(valor.split("|")[5])!=""){fechaOperacion=valor.split("|")[5];}else{fechaOperacion="_";};
        if(Trim(valor.split("|")[6])!=""){fechaValor=valor.split("|")[6];}else{fechaValor="_";};
        if(Trim(valor.split("|")[4])!=""){referencia=valor.split("|")[4];}else{referencia="_";};
        if(Trim(valor.split("|")[7])!=""){mensaje=valor.split("|")[7];}else{mensaje="_";};
        if(Trim(valor.split("|")[8])!=""){monto=valor.split("|")[8];}else{monto="_";};

        beneficiario=$().crypt({method: "b64enc",source:beneficiario});
        banco_origen=$().crypt({method: "b64enc",source:banco_origen});
        informacion=$().crypt({method: "b64enc",source:informacion});
        tasadecambio=$().crypt({method: "b64enc",source:tasadecambio});
        referencia=$().crypt({method: "b64enc",source:referencia});
        fechaValor=$().crypt({method: "b64enc",source:fechaValor});
        fechaOperacion=$().crypt({method: "b64enc",source:fechaOperacion});
        mensaje=$().crypt({method: "b64enc",source:mensaje});
        monto=$().crypt({method: "b64enc",source:monto});
        num_cta=$().crypt({method: "b64enc",source:num_cta});
        tile=$().crypt({method: "b64enc",source:tile});

        urlreportaccountBT = urlreportaccountBT + "?beneficiario="+beneficiario+"&descripcion="+banco_origen+"&observacion="+informacion
            +"&tasadecambio="+tasadecambio+"&referencia="+referencia+"&fecha_operacion="+fechaOperacion+"&fechaValor="+fechaValor
            +"&monto="+monto+"&tipMov="+tile+"&num_cta="+num_cta+"&mensaje="+mensaje;
    }else if(tile=="TEO"){
        var beneficiario;
        var banco_beneficiario;
        var informacion;
        var tasadecambio;
        var fechaOperacion;
        var fechaValor;
        var referencia;
        var mensaje;
        var monto;
        var num_cta=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);
        if(Trim(valor.split("|")[0])!=""){beneficiario=valor.split("|")[0];}else{beneficiario="_";};
        if(Trim(valor.split("|")[1])!=""){banco_beneficiario=valor.split("|")[1];}else{banco_beneficiario="_";};
        if(Trim(valor.split("|")[2])!=""){informacion=valor.split("|")[2];}else{informacion="_";};
        if(Trim(valor.split("|")[3])!=""){tasadecambio=valor.split("|")[3];}else{tasadecambio="_";};
        if(Trim(valor.split("|")[5])!=""){fechaOperacion=valor.split("|")[5];}else{fechaOperacion="_";};
        if(Trim(valor.split("|")[6])!=""){fechaValor=valor.split("|")[6];}else{fechaValor="_";};
        if(Trim(valor.split("|")[4])!=""){referencia=valor.split("|")[4];}else{referencia="_";};
        if(Trim(valor.split("|")[7])!=""){mensaje=valor.split("|")[7];}else{mensaje="_";};
        if(Trim(valor.split("|")[8])!=""){monto=valor.split("|")[8];}else{monto="_";};

        beneficiario=$().crypt({method: "b64enc",source:beneficiario});
        banco_beneficiario=$().crypt({method: "b64enc",source:banco_beneficiario});
        informacion=$().crypt({method: "b64enc",source:informacion});
        tasadecambio=$().crypt({method: "b64enc",source:tasadecambio});
        referencia=$().crypt({method: "b64enc",source:referencia});
        fechaValor=$().crypt({method: "b64enc",source:fechaValor});
        fechaOperacion=$().crypt({method: "b64enc",source:fechaOperacion});
        mensaje=$().crypt({method: "b64enc",source:mensaje});
        monto=$().crypt({method: "b64enc",source:monto});
        num_cta=$().crypt({method: "b64enc",source:num_cta});
        tile=$().crypt({method: "b64enc",source:tile});
        urlreportaccountBT = urlreportaccountBT + "?beneficiario="+beneficiario+"&descripcion="+banco_beneficiario
            +"&observacion="+informacion+"&tasadecambio="+tasadecambio+"&referencia="+referencia+"&fecha_operacion="+fechaOperacion
            +"&fechaValor="+fechaValor+"&monto="+monto+"&tipMov="+tile+"&num_cta="+num_cta+"&mensaje="+mensaje;

    }else{
        var observacion;
        var monto_debit;
        var ordenante;
        var tasadecambio;
        var fechaOperacion;
        var fechaValor;
        var referencia;
        var mensaje;
        var monto;
        var num_cta=Trim($("#estado_cuenta_numero_cuenta_BT").val().split("|")[0]);
        if(Trim(valor.split("|")[0])!=""){observacion=valor.split("|")[0];}else{observacion="_";};
        if(Trim(valor.split("|")[1])!=""){tasadecambio=valor.split("|")[1];}else{tasadecambio="_";};
        if(Trim(valor.split("|")[2])!=""){referencia=valor.split("|")[2];}else{referencia="_";};
        if(Trim(valor.split("|")[3])!=""){fechaOperacion=valor.split("|")[3];}else{fechaOperacion="_";};
        if(Trim(valor.split("|")[4])!=""){fechaValor=valor.split("|")[4];}else{fechaValor="_";};
        if(Trim(valor.split("|")[5])!=""){mensaje=valor.split("|")[5];}else{mensaje="_";};
        if(Trim(valor.split("|")[6])!=""){monto_debit=valor.split("|")[6];}else{monto_debit="_";};
        if((Trim(valor.split("|")[7])!="0")){monto=valor.split("|")[7];}else{monto="_";};

        monto_debit=$().crypt({method: "b64enc",source:monto_debit});
        observacion=$().crypt({method: "b64enc",source:observacion});
        tasadecambio=$().crypt({method: "b64enc",source:tasadecambio});
        referencia=$().crypt({method: "b64enc",source:referencia});
        fechaValor=$().crypt({method: "b64enc",source:fechaValor});
        fechaOperacion=$().crypt({method: "b64enc",source:fechaOperacion});
        mensaje=$().crypt({method: "b64enc",source:mensaje});
        monto=$().crypt({method: "b64enc",source:monto});
        num_cta=$().crypt({method: "b64enc",source:num_cta});
        tile=$().crypt({method: "b64enc",source:tile});
        urlreportaccountBT = urlreportaccountBT + "?observacion="+observacion+"&tasadecambio="+tasadecambio+"&descripcion="+monto_debit
            +"&referencia="+referencia+"&fecha_operacion="+fechaOperacion+"&fechaValor="+fechaValor+"&monto="+monto+"&tipMov="+tile+"&num_cta="+num_cta+"&mensaje="+mensaje;

    }

    window.open(urlreportaccountBT,'PDF','');
}




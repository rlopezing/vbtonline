CREATE OR REPLACE PACKAGE VBTONLINE_TRANS.TransferenciaHandler AS

TYPE EmailsInternos IS REF CURSOR;
TYPE direccion_transf IS REF CURSOR;
TYPE na_clientes IS REF CURSOR;
TYPE ac_contrato IS REF CURSOR;
TYPE elementos_ext IS REF CURSOR;
TYPE trans_paises IS REF CURSOR;
TYPE bankCode_elementos IS REF CURSOR;
TYPE trans_exter_recibo IS REF CURSOR;
TYPE trans_codmon IS REF CURSOR;  
TYPE trans_inter_codcar IS REF CURSOR;  
TYPE transfe_consulta IS REF CURSOR;
--TYPE cuentas_corrie IS REF CURSOR;
TYPE cuentas_extern IS REF CURSOR;
TYPE trans_cuentas_inter IS REF CURSOR; 
TYPE validateuser_handler IS REF CURSOR; 
TYPE validateuser_cartera  IS REF CURSOR;
--TYPE bac_contratos1 IS REF CURSOR;
TYPE transfe_consu_fc  IS REF CURSOR; 
TYPE contratos IS REF CURSOR;
TYPE emails IS REF CURSOR;


FUNCTION num_referencia RETURN VARCHAR2;

PROCEDURE emails_internos_pr (p_codcol in varchar2, p_emails OUT EmailsInternos, p_resultado OUT VARCHAR2);

PROCEDURE emails_internos_asesores_pr (p_codcol IN VARCHAR2, p_emails OUT VARCHAR2, p_resultado OUT VARCHAR2);

PROCEDURE act_secuencia_trans_pr (p_codemp in VARCHAR2, p_codsec in VARCHAR2,  p_resultado OUT VARCHAR2);

PROCEDURE direc_orig_trans_pr (p_codcardeb in VARCHAR2, p_direccion OUT direccion_transf, p_resultado OUT VARCHAR2); 
  
 PROCEDURE orden_transferencia_pr(P_NUM_CONTRATO          IN VARCHAR2, 
        P_CODCAR                    IN VARCHAR2, 
        P_ESTATUS_INSTRUCCION       IN VARCHAR2,
        P_BOF00_CODMOV              IN VARCHAR2, 
        P_BOF00_CODEMP              IN VARCHAR2, 
        P_BOF00_CODCOL              IN VARCHAR2, 
        P_BOF00_CODINST             IN VARCHAR2, 
        P_CODTIPOMOV                IN VARCHAR2,    
        P_BOF16_AMOUNT              IN NUMBER, 
        P_REFBANMOV                 IN VARCHAR2, 
        P_BOF03_CURRENCY_CODE       IN VARCHAR2, 
        P_BENEFICIARIO              IN VARCHAR2, 
        P_OBSERV                    IN VARCHAR2, 
        P_CODEMP_ORIGEN             IN VARCHAR2,  
        P_CODCAR_ORIGEN             IN VARCHAR2, 
        P_USRID_LIBERA              IN VARCHAR2, 
        P_SOURCE                    IN VARCHAR2, 
        P_EMAIL_BENEFICIARIO        IN VARCHAR2, 
        P_EMAIL_ORIGEN              IN VARCHAR2, 
        P_BOF16_BANK_REFERENCE_NUMBER IN VARCHAR2, 
        P_BOF00_CODCAR              IN VARCHAR2, 
        P_BENEFICIARY_TYPE          IN VARCHAR2, 
        P_BENEFICIARY_TYPE_NUMBER   IN VARCHAR2, 
        P_BENEFICIARY_DESCRIPTION   IN VARCHAR2, 
        P_TELEFONO_BENEFICIARIO     IN VARCHAR2, 
        P_BENEFICIARY_BANK_TYPE     IN VARCHAR2, 
        P_BENEFICIARY_BANK_TYPE_NUMBER IN VARCHAR2, 
        P_BENEFICIARY_BANK_DESCRIPTION IN VARCHAR2, 
        P_INTERMEDIARY_BANK_TYPE         IN VARCHAR2, 
        P_INTERMEDIARY_BANK_TYPE_NUM IN VARCHAR2, 
        P_INTERMEDIARY_BANK_DESCRIPT IN VARCHAR2, 
        P_ORIGINATORS_INFO              IN VARCHAR2, 
        P_CODTIPOMOV_BOFA               IN VARCHAR2, 
        P_CODEMP                        IN VARCHAR2, 
        P_CODCOL                        IN VARCHAR2, 
        P_CODINST                       IN VARCHAR2, 
        P_BENEFICIARY_NAME              IN VARCHAR2, 
        P_BENEFICIARY_BANK_NAME         IN VARCHAR2, 
        P_INTERMEDIARY_BANK_NAME        IN VARCHAR2, 
        P_ORIGINATORS_NAME              IN VARCHAR2, 
        P_DETAILS_OF_PAYMENT_NAME       IN VARCHAR2, 
        P_DETAILS_OF_PAYMENT_DESCRIPT IN VARCHAR2, 
        P_CODMON_CREDIT                 IN VARCHAR2, 
        P_FFC_NUMBER                    IN VARCHAR2, 
        P_FFC_NAME                      IN VARCHAR2, 
        P_SECUENCIA                     out varchar2,   
        p_resultado                     OUT VARCHAR2,
        P_MOTIVO                        IN VARCHAR2,
        P_TYPE_SWIFT                    IN VARCHAR2,
        P_NUMBER_SWIFT                  IN VARCHAR2,
        P_IB_SWIFT                      IN VARCHAR2,
        P_IB_NUMBER_SWIFT               IN VARCHAR2,
        P_TIPOUSUARIO                   IN VARCHAR2,
        P_ORIGINATORS_ADDRESS           IN VARCHAR2,
        P_ORIGINATORS_CITY              IN VARCHAR2,
        P_ORIGINATORS_COUNTRY           IN VARCHAR2,
        P_ORIGINATORS_POSTAL_CODE       IN VARCHAR2,
        P_BENEFICIARY_ADDRESS           IN VARCHAR2,
        P_BENEFICIARY_CITY              IN VARCHAR2,
        P_BENEFICIARY_COUNTRY           IN VARCHAR2,
        P_BENEFICIARY_POSTAL_CODE       IN VARCHAR2,  
        P_BENEFICIARY_BANK_ADDRESS      IN VARCHAR2,
        P_BENEFICIARY_BANK_CITY         IN VARCHAR2,
        P_BENEFICIARY_BANK_COUNTRY      IN VARCHAR2,
        P_INTERMEDIARY_BANK_ADDRESS     IN VARCHAR2,
        P_INTERMEDIARY_BANK_CITY        IN VARCHAR2,
        P_INTERMEDIARY_BANK_COUNTRY     IN VARCHAR2,
        P_ORIGEN_TEMPLATE               IN VARCHAR2,
        P_ID_TEMPLATE                   IN VARCHAR2,
        P_BENEFICIARY_TYPE_PERSON       IN VARCHAR2,
        P_BENEFICIARY_LASTNAME1         IN VARCHAR2,
        P_BENEFICIARY_LASTNAME2         IN VARCHAR2
        );
        
        
        PROCEDURE orden_transfe_externa_pr(
                    P_BOF00_CODMOV                      IN VARCHAR2, 
                    P_BOF00_CODEMP                      IN VARCHAR2, 
                    P_BOF00_CODCOL                      IN VARCHAR2, 
                    P_BOF00_CODINST                     IN VARCHAR2, 
                    P_CODTIPOMOV                        IN VARCHAR2,
                    P_BOF16_AMOUNT                      IN NUMBER, 
                    P_REFBANMOV                         IN VARCHAR2, 
                    P_BOF03_CURRENCY_CODE               IN VARCHAR2, 
                    P_BENEFICIARIO                      IN VARCHAR2, 
                    P_OBSERV                            IN VARCHAR2, 
                    P_CODEMP_ORIGEN                     IN VARCHAR2, 
                    P_CODCAR_ORIGEN                     IN VARCHAR2, 
                    P_SOURCE                            IN VARCHAR2,
                    P_BOF16_BANK_REFERENCE_NUMBER       IN VARCHAR2, 
                    P_BOF00_CODCAR                      IN VARCHAR2, 
                    P_BENEFICIARY_TYPE                  IN VARCHAR2, 
                    P_BENEFICIARY_TYPE_NUMBER           IN VARCHAR2, 
                    P_BENEFICIARY_DESCRIPTION           IN VARCHAR2, 
                    P_BENEFICIARY_BANK_TYPE             IN VARCHAR2, 
                    P_BENEFICIARY_BANK_TYPE_NUMBER     IN VARCHAR2, 
                    P_BENEFICIARY_BANK_DESCRIPTION     IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_TYPE           IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_TYPE_NUMB      IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_DESCRIPTI      IN VARCHAR2, 
                    P_ORIGINATORS_INFO                 IN VARCHAR2, 
                    P_CODTIPOMOV_BOFA                  IN VARCHAR2, 
                    P_CODEMP                           IN VARCHAR2, 
                    P_CODCOL                           IN VARCHAR2, 
                    P_CODINST                          IN VARCHAR2, 
                    P_BENEFICIARY_NAME                 IN VARCHAR2, 
                    P_BENEFICIARY_BANK_NAME            IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_NAME           IN VARCHAR2, 
                    P_ORIGINATORS_NAME                 IN VARCHAR2, 
                    P_DETAILS_OF_PAYMENT_NAME          IN VARCHAR2, 
                    P_DETAILS_OF_PAYMENT_DESCRIPTI     IN VARCHAR2, 
                    P_CODMON_CREDIT                    IN VARCHAR2, 
                    P_BOF01_CODMOV                     IN VARCHAR2, 
                    P_RECEIVER_INFO                    IN VARCHAR2, 
                    P_EXOCOM                           IN VARCHAR2,
                    P_NUM_INSTRUCCION               IN VARCHAR2,
                    p_resultado                     OUT VARCHAR2,
                    P_ORIGINATORS_ADDRESS           IN VARCHAR2,
                    P_ORIGINATORS_CITY              IN VARCHAR2,
                    P_ORIGINATORS_COUNTRY           IN VARCHAR2,
                    P_ORIGINATORS_POSTAL_CODE       IN VARCHAR2,
                    P_BENEFICIARY_ADDRESS           IN VARCHAR2,
                    P_BENEFICIARY_CITY              IN VARCHAR2,
                    P_BENEFICIARY_COUNTRY           IN VARCHAR2,
                    P_BENEFICIARY_POSTAL_CODE       IN VARCHAR2,  
                    P_BENEFICIARY_BANK_ADDRESS      IN VARCHAR2,
                    P_BENEFICIARY_BANK_CITY         IN VARCHAR2,
                    P_BENEFICIARY_BANK_COUNTRY      IN VARCHAR2,
                    P_INTERMEDIARY_BANK_ADDRESS     IN VARCHAR2,
                    P_INTERMEDIARY_BANK_CITY        IN VARCHAR2,
                    P_INTERMEDIARY_BANK_COUNTRY     IN VARCHAR2); 
                    
 PROCEDURE na_clientes_pr (p_CodigoCarteraDebito in VARCHAR2, p_clientes OUT na_clientes, p_resultado OUT VARCHAR2);                   
                    
 PROCEDURE ac_contrato_pr (p_strNumContrato in VARCHAR2, p_contrato OUT ac_contrato, p_resultado OUT VARCHAR2);    
 
 PROCEDURE act_trans_contrato(p_acepto_tran in VARCHAR2, p_usuario_tran IN VARCHAR2, p_ip_tran IN VARCHAR2, p_num_contrato IN VARCHAR2, p_resultado OUT VARCHAR2);
                
 PROCEDURE trans_elementos_ext_pr (p_elementos_ext OUT elementos_ext, p_resultado OUT VARCHAR2);
 
 PROCEDURE trans_paises_pr (p_trans_paises OUT trans_paises, p_resultado OUT VARCHAR2);
 
 PROCEDURE trans_paises_beneficiario_pr (p_trans_paises OUT trans_paises, p_resultado OUT VARCHAR2);
 
 PROCEDURE bankCode_elementos_pr (p_bankCode_elementos OUT bankCode_elementos, p_resultado OUT VARCHAR2);  
 
 
  PROCEDURE trans_ext_confir_pr (TipoCodBancoBeneficiario   IN VARCHAR2, 
                                CodBancoBeneficiario       IN VARCHAR2, 
                                CodPaisBancoBeneficiario   IN VARCHAR2,
                                TipoCodBancoIntermediario  IN VARCHAR2,
                                CodBancoBancoIntermediario IN VARCHAR2,
                                CodPaisBancoIntermediario  IN VARCHAR2,
                                conta_inst                 OUT NUMBER,
                                p_resultado OUT VARCHAR2,
                                p_sql OUT VARCHAR2);
 
 
 
 PROCEDURE trans_exter_recibo_pr (p_strLiberada  IN VARCHAR2,
                                   p_strAprobada  IN VARCHAR2,
                                   p_strCargada   IN VARCHAR2,
                                   p_strRechazada IN VARCHAR2,
                                   p_strAnulada   IN VARCHAR2,
                                   P_strTxtNumeroReferencia IN VARCHAR2,
                                   p_trans_exter_recibo OUT trans_exter_recibo, 
                                   p_resultado OUT VARCHAR2);
  
                     
  
  PROCEDURE trans_codmon_pr (p_strTxtCuentaCredito IN VARCHAR2, p_trans_codmon OUT trans_codmon, p_resultado OUT VARCHAR2);

   PROCEDURE trans_inter_codcar_pr (p_strNumeroCuentaCredito IN VARCHAR2, p_trans_inter_codcar OUT trans_inter_codcar, p_resultado OUT VARCHAR2);


     PROCEDURE transfe_consulta_pr (p_strCarteras    IN OUT VARCHAR2,
                                p_strNumContrato IN VARCHAR2,
                                p_strTxtDesde    IN VARCHAR2,
                                p_strTxtHasta    IN VARCHAR2,
                                p_transfe_consulta OUT transfe_consulta,
                                p_resultado OUT VARCHAR2,
                                p_sql OUT VARCHAR2); 
                                
   
 --  PROCEDURE cuentas_corri_pr (p_strCarteras in out VARCHAR2, p_cuentas_corri OUT cuentas_corrie, p_resultado OUT VARCHAR2);
    PROCEDURE cuentas_corri_pr (p_strCarteras in  out VARCHAR2, 
 p_cuentas_corri OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2, p_salida out varchar2);                           

  PROCEDURE cuentas_exter_pr (p_strCarteras in VARCHAR2, p_cuentas_exter OUT cuentas_extern, p_resultado OUT VARCHAR2);

    PROCEDURE trans_cuentas_inter_pr (p_strCarteras IN VARCHAR2, p_trans_cuentas_inter OUT trans_cuentas_inter, p_resultado OUT VARCHAR2);
    
    PROCEDURE validateuser_handler_pr (p_login           in VARCHAR2,
                                   p_validateuser_handler OUT validateuser_handler, 
                                   p_resultado OUT VARCHAR2,
                                   p_inte_login out varchar2); 
                                   
    PROCEDURE validateuser_cartera_pr (p_num_contrato IN VARCHAR2, 
                                   p_validateuser_cartera OUT validateuser_cartera, 
                                   p_resultado OUT VARCHAR2);
                                   
     PROCEDURE tra_numreferencia_pr ( p_referencia OUT VARCHAR2, 
                                        p_resultado OUT VARCHAR2);
                    
     PROCEDURE ORDEN_TRANSFERENCIA_INTERNA
              (P_NUM_CONTRATO                 IN VARCHAR2, 
               P_STRCODIGOCARTERADEBITO    IN VARCHAR2, 
               P_NUMEROREFERENCIADEBITO    IN VARCHAR2,
               P_NUMEROCUENTADEBITO           IN VARCHAR2,
               P_STRHDMMONTO                       IN NUMBER,
               P_STRCODIGOCARTERACREDITO   IN VARCHAR2,
               P_STRTXTMONEDA                       IN VARCHAR2,
               P_STRTXTNOMBREBENEFICIARIO     IN VARCHAR2,
               P_STRTXTCONCEPTO                   IN VARCHAR2,
               P_STRCLIENTEDEBITO                 IN VARCHAR2,
               P_NUMEROCUENTACREDITO         IN VARCHAR2,
               P_LOGIN                                 IN VARCHAR2,
               P_STRTXTEMAILBENEFICIARIO   IN VARCHAR2,
               P_STREMAILORIGEN            IN VARCHAR2,
               P_ESTATUSTRANSFERENCIA            IN VARCHAR2,
               P_TIPOUSUARIO            IN VARCHAR2,
               P_SECUENCIA            OUT VARCHAR2,
               p_resultado                          OUT VARCHAR2);    
      
     PROCEDURE trans_movimiento_bofa_it
        (P_NUMEROREFERENCIADEBITO    IN VARCHAR2,
        P_NUMEROCUENTADEBITO        IN VARCHAR2,
        P_STRHDMMONTO                 IN NUMBER,
        P_STRCODIGOCARTERACREDITO   IN VARCHAR2,
        P_STRTXTMONEDA                IN VARCHAR2,
        P_STRTXTNOMBREBENEFICIARIO    IN VARCHAR2,
        P_STRTXTCONCEPTO            IN VARCHAR2,
        P_STRCLIENTEDEBITO            IN VARCHAR2,
        P_NUMEROCUENTACREDITO        IN VARCHAR2,
        P_NUM_INSTRUCCION IN VARCHAR2,
        p_resultado                 OUT VARCHAR2);  
        
      PROCEDURE trans_movimiento_bofa_tmp_it
        (P_NUMEROREFERENCIADEBITO    IN VARCHAR2,
        P_NUMEROCUENTADEBITO        IN VARCHAR2,
        P_STRHDMMONTO                 IN NUMBER,
        P_STRCODIGOCARTERACREDITO   IN VARCHAR2,
        P_STRTXTMONEDA                IN VARCHAR2,
        P_STRTXTNOMBREBENEFICIARIO    IN VARCHAR2,
        P_STRTXTCONCEPTO            IN VARCHAR2,
        P_STRCLIENTEDEBITO            IN VARCHAR2,
        P_NUMEROCUENTACREDITO        IN VARCHAR2,
        P_NUMERO_INSTRUCCION          IN VARCHAR2,
        p_resultado                 OUT VARCHAR2);                   
                    
                               
   /*  
      PROCEDURE bac_contratos1_pr (p_strNuevo     IN VARCHAR2,
                                      p_strActivo         IN VARCHAR2,
                                      p_strCancelado     IN VARCHAR2,
                                      p_strInactivo     IN VARCHAR2,
                                      p_strRechazado     IN VARCHAR2,
                                      p_strDesconocido  IN VARCHAR2,
                                      p_hdnAccion        IN VARCHAR2,
                                      p_txtNumeroCartera IN VARCHAR2,
                                      p_strTipoUsuario    IN VARCHAR2,
                                      p_login            IN VARCHAR2,
                                      p_strTxtNumeroContrato    IN VARCHAR2,
                                      p_strTxtUsuario             IN VARCHAR2,
                                      p_strTxtNombreCliente        IN VARCHAR2,
                                      p_strTxtCIRIFCliente        IN VARCHAR2,
                                      p_strCmbEstatus            IN VARCHAR2,        
                                      p_strTxtDesde                IN VARCHAR2,
                                      p_strTxtHasta                IN VARCHAR2,
                                      p_strOrden                IN VARCHAR2,
                                      p_bac_contratos1 OUT bac_contratos1, 
                                        p_resultado OUT VARCHAR2);   
                                                             
*/

PROCEDURE trans_movimiento_bofa_it_cre
        (P_NUMEROREFERENCIADEBITO    IN VARCHAR2,
        P_NUMEROCUENTADEBITO        IN VARCHAR2,
        P_STRHDMMONTO                 IN NUMBER,
        P_STRCODIGOCARTERACREDITO   IN VARCHAR2,
        P_STRTXTMONEDA                IN VARCHAR2,
        P_STRTXTNOMBREBENEFICIARIO    IN VARCHAR2,
        P_STRTXTCONCEPTO            IN VARCHAR2,
        P_STRCLIENTEDEBITO            IN VARCHAR2,
        P_NUMEROCUENTACREDITO        IN VARCHAR2,
        P_NUM_INSTRUCCION IN VARCHAR2,
        p_resultado                 OUT VARCHAR2); 
        
PROCEDURE trans_mov_bofa_tmp_it_cre
        (P_NUMEROREFERENCIADEBITO    IN VARCHAR2,
        P_NUMEROCUENTADEBITO        IN VARCHAR2,
        P_STRHDMMONTO                 IN NUMBER,
        P_STRCODIGOCARTERACREDITO   IN VARCHAR2,
        P_STRTXTMONEDA                IN VARCHAR2,
        P_STRTXTNOMBREBENEFICIARIO    IN VARCHAR2,
        P_STRTXTCONCEPTO            IN VARCHAR2,
        P_STRCLIENTEDEBITO            IN VARCHAR2,
        P_NUMEROCUENTACREDITO        IN VARCHAR2,
        P_NUMERO_INSTRUCCION        IN VARCHAR2,
        p_resultado                 OUT VARCHAR2); 

PROCEDURE orden_externa_temp_pr(
                    P_BOF00_CODMOV                     IN VARCHAR2, 
                    P_BOF00_CODEMP                  IN VARCHAR2, 
                    P_BOF00_CODCOL                  IN VARCHAR2, 
                    P_BOF00_CODINST                 IN VARCHAR2, 
                    P_CODTIPOMOV                    IN VARCHAR2,
                    P_BOF16_AMOUNT                  IN NUMBER, 
                    P_REFBANMOV                     IN VARCHAR2, 
                    P_BOF03_CURRENCY_CODE              IN VARCHAR2, 
                    P_BENEFICIARIO                    IN VARCHAR2, 
                    P_OBSERV                        IN VARCHAR2, 
                    P_CODEMP_ORIGEN                 IN VARCHAR2, 
                    P_CODCAR_ORIGEN                 IN VARCHAR2, 
                    P_SOURCE                         IN VARCHAR2,
                    P_BOF16_BANK_REFERENCE_NUMBER     IN VARCHAR2, 
                    P_BOF00_CODCAR                     IN VARCHAR2, 
                    P_BENEFICIARY_TYPE                 IN VARCHAR2, 
                    P_BENEFICIARY_TYPE_NUMBER         IN VARCHAR2, 
                    P_BENEFICIARY_DESCRIPTION         IN VARCHAR2, 
                    P_BENEFICIARY_BANK_TYPE         IN VARCHAR2, 
                    P_BENEFICIARY_BANK_TYPE_NUMBER     IN VARCHAR2, 
                    P_BENEFICIARY_BANK_DESCRIPTION     IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_TYPE         IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_TYPE_NUMB     IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_DESCRIPTI     IN VARCHAR2, 
                    P_ORIGINATORS_INFO                 IN VARCHAR2, 
                    P_CODTIPOMOV_BOFA                 IN VARCHAR2, 
                    P_CODEMP                         IN VARCHAR2, 
                    P_CODCOL                         IN VARCHAR2, 
                    P_CODINST                         IN VARCHAR2, 
                    P_BENEFICIARY_NAME                 IN VARCHAR2, 
                    P_BENEFICIARY_BANK_NAME         IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_NAME         IN VARCHAR2, 
                    P_ORIGINATORS_NAME                 IN VARCHAR2, 
                    P_DETAILS_OF_PAYMENT_NAME         IN VARCHAR2, 
                    P_DETAILS_OF_PAYMENT_DESCRIPTI  IN VARCHAR2, 
                    P_CODMON_CREDIT                 IN VARCHAR2, 
                    P_BOF01_CODMOV                     IN VARCHAR2, 
                    P_RECEIVER_INFO                 IN VARCHAR2, 
                    P_EXOCOM                         IN VARCHAR2,
                              P_NUM_INSTRUCCION                         IN NUMBER,
                    p_resultado OUT VARCHAR2,
                    P_ORIGINATORS_ADDRESS           IN VARCHAR2,
                    P_ORIGINATORS_CITY              IN VARCHAR2, 
                    P_ORIGINATORS_COUNTRY           IN VARCHAR2,
                    P_ORIGINATORS_POSTAL_CODE       IN VARCHAR2,
                    P_BENEFICIARY_ADDRESS           IN VARCHAR2,
                    P_BENEFICIARY_CITY              IN VARCHAR2,
                    P_BENEFICIARY_COUNTRY           IN VARCHAR2,
                    P_BENEFICIARY_POSTAL_CODE       IN VARCHAR2,
                    P_BENEFICIARY_BANK_ADDRESS      IN VARCHAR2,
                    P_BENEFICIARY_BANK_CITY         IN VARCHAR2,
                    P_BENEFICIARY_BANK_COUNTRY      IN VARCHAR2,
                    P_INTERMEDIARY_BANK_ADDRESS     IN VARCHAR2,
                    P_INTERMEDIARY_BANK_CITY        IN VARCHAR2,
                    P_INTERMEDIARY_BANK_COUNTRY     IN VARCHAR2);
                    
          PROCEDURE trans_inserta_bofa_pr (p_num_instruccion IN out VARCHAR2, 
                                       p_resultado OUT VARCHAR2);   
                     
                     
   PROCEDURE transfe_consu_fc_pr (p_strCarteras    IN  OUT VARCHAR2,
                                p_strNumContrato IN VARCHAR2,
                                p_status    IN VARCHAR2,
                                p_transfe_consu_fc OUT  SYS_REFCURSOR,
                                p_resultado OUT VARCHAR2,
                                p_salida OUT VARCHAR2);     
                                
     PROCEDURE transfe_consu_fc_detalle_pr (p_strCarteras    IN  OUT VARCHAR2,
                                p_strNumContrato IN VARCHAR2,
                                p_numref    IN VARCHAR2,
                                p_transfe_consu_fc OUT  SYS_REFCURSOR,
                                p_resultado OUT VARCHAR2,
                                p_salida OUT VARCHAR2);     
                                
     PROCEDURE tran_cambiar_estatus_fc_pr (p_status  IN VARCHAR2,
                                                  p_num_instruccion IN OUT VARCHAR2,
                                                  p_user IN VARCHAR2,
                                                  p_correo IN OUT VARCHAR2,
                                                  p_resultado OUT VARCHAR2);  
                                                  
     PROCEDURE cambiar_estatus_template_pr (  p_id   IN VARCHAR2,
                                                   p_user IN VARCHAR2,
                                                   p_resultado OUT VARCHAR2);                                                  
                          
                          
     PROCEDURE orden_transfere_fc_pr(P_NUM_CONTRATO          IN VARCHAR2, 
        P_CODCAR                    IN VARCHAR2, 
        P_ESTATUS_INSTRUCCION       IN VARCHAR2,
        P_BOF00_CODMOV              IN VARCHAR2, 
        P_BOF00_CODEMP              IN VARCHAR2, 
        P_BOF00_CODCOL              IN VARCHAR2, 
        P_BOF00_CODINST             IN VARCHAR2, 
        P_CODTIPOMOV                IN VARCHAR2,    
        P_BOF16_AMOUNT                IN NUMBER, 
        P_REFBANMOV                 IN VARCHAR2, 
        P_BOF03_CURRENCY_CODE       IN VARCHAR2, 
        P_BENEFICIARIO              IN VARCHAR2, 
        P_OBSERV                    IN VARCHAR2, 
        P_CODEMP_ORIGEN             IN VARCHAR2,  
        P_CODCAR_ORIGEN             IN VARCHAR2, 
        P_USRID_LIBERA              IN VARCHAR2, 
        P_SOURCE                    IN VARCHAR2, 
        P_EMAIL_BENEFICIARIO        IN VARCHAR2, 
        P_EMAIL_ORIGEN              IN VARCHAR2, 
        P_BOF16_BANK_REFERENCE_NUMBER IN VARCHAR2, 
        P_BOF00_CODCAR              IN VARCHAR2, 
        P_BENEFICIARY_TYPE          IN VARCHAR2, 
        P_BENEFICIARY_TYPE_NUMBER   IN VARCHAR2, 
        P_BENEFICIARY_DESCRIPTION   IN VARCHAR2, 
        P_TELEFONO_BENEFICIARIO     IN VARCHAR2, 
        P_BENEFICIARY_BANK_TYPE     IN VARCHAR2, 
        P_BENEFICIARY_BANK_TYPE_NUMBER IN VARCHAR2, 
        P_BENEFICIARY_BANK_DESCRIPTION IN VARCHAR2, 
        P_INTERMEDIARY_BANK_TYPE         IN VARCHAR2, 
        P_INTERMEDIARY_BANK_TYPE_NUM IN VARCHAR2, 
        P_INTERMEDIARY_BANK_DESCRIPT IN VARCHAR2, 
        P_ORIGINATORS_INFO              IN VARCHAR2, 
        P_CODTIPOMOV_BOFA               IN VARCHAR2, 
        P_CODEMP                        IN VARCHAR2, 
        P_CODCOL                        IN VARCHAR2, 
        P_CODINST                       IN VARCHAR2, 
        P_BENEFICIARY_NAME              IN VARCHAR2, 
        P_BENEFICIARY_BANK_NAME         IN VARCHAR2, 
        P_INTERMEDIARY_BANK_NAME        IN VARCHAR2, 
        P_ORIGINATORS_NAME              IN VARCHAR2, 
        P_DETAILS_OF_PAYMENT_NAME       IN VARCHAR2, 
        P_DETAILS_OF_PAYMENT_DESCRIPT IN VARCHAR2, 
        P_CODMON_CREDIT                 IN VARCHAR2, 
        P_FFC_NUMBER                    IN VARCHAR2, 
        P_FFC_NAME                      IN VARCHAR2, 
        p_resultado OUT VARCHAR2);                     
                                                                     
PROCEDURE actualizar_contrato (p_codpercli varchar2, p_num_contrato varchar2, p_vbt_nmtd number, p_vbt_mmtd number,  p_ex_nmtd  number,   p_ex_mmtd  number, p_resultado OUT VARCHAR2);
PROCEDURE consultar_contrato (p_codpercli varchar2, p_num_contrato varchar2,c_contratos OUT contratos, p_resultado OUT VARCHAR2);

PROCEDURE validar_pp_pr(p_codpercli IN VARCHAR2, p_monto in pls_integer, p_contrato IN varchar2, p_tipo_transf IN varchar2, po_tag out varchar2, po_resultado out varchar, p_tipo in varchar2);
PROCEDURE ActualizarTerminosCondiciones (numcontrato in varchar2, usuario in varchar2, direccionip in varchar2, fecha in varchar2,acepto in varchar2, p_resultado out VARCHAR2); 
PROCEDURE ConsultarAprobadoresContrato (p_numcontrato IN VARCHAR2, p_tipo IN VARCHAR2,c_emails OUT emails, p_resultado out VARCHAR2);
PROCEDURE montos_liberador_fc(p_contrato IN VARCHAR2, p_monto_interna OUT number, p_monto_externa OUT number,p_cantidad_interna OUT number, p_cantidad_externa OUT number,p_resultado OUT VARCHAR2);
PROCEDURE correos_rechazo (p_num_instruccion IN VARCHAR2, c_emails OUT emails, p_resultado out VARCHAR2);
PROCEDURE cargar_numero_referencia (p_num_instruccion IN VARCHAR2, p_referencias OUT  SYS_REFCURSOR, p_resultado out VARCHAR2);
PROCEDURE cargar_montos_fc (p_num_referencia IN VARCHAR2, p_datos OUT  SYS_REFCURSOR, p_resultado out VARCHAR2);


/**
 *      FUNCIÓN:        VALIDAR_BANCO_BENEFICIARIO(PI_BIC_BENEFICIARIO, PI_CUENTA_BENEFICIARIO, PI_BIC_INTERMEDIARIO, PO_ERROR_CODIGO, PO_ERROR_MENSAJE)
 *      AUTOR:          RRANGEL
 *      FECHA:          10/10/2014
 *      DESCRIPCIÓN:    CUENTA LA CANTIDAD DE SOLICITUDES PENDIENTES EXISTENTES 
 *                      PARA LOS ESTATUS 'C' (CARGADO) Y 'A' (APROBADO) 
 * */
 PROCEDURE VALIDAR_BANCO_BENEFICIARIO(
    PI_BIC_BENEFICIARIO IN VARCHAR2, 
    PI_CUENTA_BENEFICIARIO IN VARCHAR2,
    PI_BIC_INTERMEDIARIO IN VARCHAR2, 
    PO_ERROR_CODIGO OUT VARCHAR2, 
    PO_ERROR_MENSAJE OUT VARCHAR2
 );
/** FIN VALIDAR_BANCO_BENEFICIARIO */
  PROCEDURE REGISTRAR_PAGO_MOV_BOFA
        (P_CODBOFA    IN VARCHAR2,
        P_CARTERA                   IN VARCHAR2,
        P_NUMERO_CUENTA_DEBITO      IN VARCHAR2,
        P_NUMCTA_TDC                    IN VARCHAR2,
        P_NUMDOC_TDC                   IN VARCHAR2,
          P_MONTO                     IN VARCHAR2,
        P_SALDO                     IN VARCHAR2,
        p_resultado                 OUT VARCHAR2);
END TransferenciaHandler;
/


CREATE OR REPLACE PACKAGE VBT_TARJETA.PCK_TARJETA AS

  PROCEDURE PR_CARGAR_TARJETAS (
    I_CODCAR IN VARCHAR2,
    O_TARJETAS OUT SYS_REFCURSOR,
    O_MENSAJE OUT VARCHAR2,
    I_ORIGEN     IN     VARCHAR2,
    I_EXACTA     IN     VARCHAR2
  );

 /* PROCEDURE PR_CARGAR_TDC_PRINCIPALES (I_CODCAR     IN     VARCHAR2,
                                 O_TARJETAS      OUT SYS_REFCURSOR,
                                 O_MENSAJE       OUT VARCHAR2,
                                 I_ORIGEN     IN     VARCHAR2,
                                 I_EXACTA     IN     VARCHAR2);*/
                                 
                                 
  PROCEDURE PR_CONSULTAR_ESTATUS (
    I_CODCAR IN VARCHAR2,
    I_NUM_CTA IN VARCHAR2,    
    I_NUM_DOC IN VARCHAR2,

   
    O_ESTATUS OUT VARCHAR2,
    O_LISTA_ACTIVAS OUT SYS_REFCURSOR,
    O_LISTA_HISTORICO OUT SYS_REFCURSOR,
    O_CODIGO OUT VARCHAR2,
    O_MENSAJE OUT VARCHAR2
  );
  
  PROCEDURE PR_REGISTRAR_DESBLOQUEO (
    I_CODCAR IN	VARCHAR2,
    I_NRO_DOC IN VARCHAR2,
    I_NRO_CTA IN VARCHAR2,
    I_FEC_INICIAL	IN VARCHAR2,
    I_FEC_FINAL	IN VARCHAR2,
    I_USRID	IN VARCHAR2,
    O_MENSAJE OUT VARCHAR2,
    I_ESTATUS    IN VARCHAR2
  );
  
   PROCEDURE PR_MODIFICAR_DESBLOQUEO (I_COD_DESBLOQUEO        IN     VARCHAR2,
                                      I_CODCAR        IN     VARCHAR2,
                                      I_NRO_DOC       IN     VARCHAR2,
                                      I_NRO_CTA       IN     VARCHAR2,
                                      I_FEC_INICIAL   IN     VARCHAR2,
                                      I_FEC_FINAL     IN     VARCHAR2,
                                      I_USRID         IN     VARCHAR2,
                                      O_MENSAJE          OUT VARCHAR2);  

  PROCEDURE PR_ELIMINAR_REGLA (
    I_COD_DESBLOQUEO IN	VARCHAR2,
    O_MENSAJE OUT VARCHAR2
  );
  
  PROCEDURE PR_CAMBIAR_STATUS_REGLA ( IN_COD_DESBLOQUEO      IN NUMBER,
                                      IN_FECHA_PROCESO      IN VARCHAR2,
                                      IN_PROCESO             IN VARCHAR2); 
                                      
  PROCEDURE PR_CONSULTAR_X_FECHAINI (O_REGLAS_PENDIENTES OUT SYS_REFCURSOR, IN_FECHA_PROCESO IN VARCHAR2);
  
  PROCEDURE PR_CONSULTAR_X_FECHAFIN (O_REGLAS_X_CONCLUIR OUT SYS_REFCURSOR, IN_FECHA_PROCESO IN VARCHAR2);                                       

PROCEDURE PR_NOTIFICAR (  
                           IN_CORREO_ORIGEN      IN VARCHAR2,
                           IN_CORREO_DEST        IN VARCHAR2,
                           IN_TITULO VARCHAR2,
                           IN_TEXTO VARCHAR2);
   
  PROCEDURE PR_PROXIMO_DIA_HABIL (O_FECHA OUT VARCHAR2,MENSAJE OUT VARCHAR2); 
  PROCEDURE PR_CARGAR_FERIADOS (I_ANIO     IN     VARCHAR2,
                                 O_FERIADOS      OUT SYS_REFCURSOR,
                                 O_MENSAJE       OUT VARCHAR2);
                                 
  PROCEDURE PR_VALIDAR_FECHA_REGLA (I_CODCAR        IN     VARCHAR2,
                                      I_NRO_DOC       IN     VARCHAR2,
                                      I_NRO_CTA       IN     VARCHAR2,
                                      I_FEC_INICIAL   IN     VARCHAR2,
                                      I_FEC_FINAL     IN     VARCHAR2,
                                      I_USRID         IN     VARCHAR2,
                                      I_COD_REGLA     IN     VARCHAR2,
                                      I_OPERACION     IN     VARCHAR2,
                                      O_MENSAJE          OUT VARCHAR2,
                                      O_ID_REGLA         OUT VARCHAR2);
                                      
  PROCEDURE PR_VERIFICAR_TARJETAS;
  
  PROCEDURE PR_CARGAR_DETALLE_MOVIMIENTO(P_NUM_DOC IN VARCHAR2, P_TOTAL OUT VARCHAR2, P_RESULTADO OUT VARCHAR2, P_CURSOR OUT SYS_REFCURSOR);
  
  PROCEDURE PR_HISTORICO_PAGOS(P_CARTERA IN VARCHAR2, P_NUM_CTA IN VARCHAR2, P_NUM_DOC VARCHAR2, P_FECHA_DESDE IN VARCHAR2, P_FECHA_HASTA IN VARCHAR2,P_ESTATUS IN VARCHAR2, P_RESULTADO OUT VARCHAR2, P_CURSOR OUT SYS_REFCURSOR);                                              
  
  PROCEDURE PR_GET_TRACE_AUDIT( P_TRACE_NUMBER OUT VARCHAR2);
  
     PROCEDURE PR_GUARDAR_BTRANS_PAGOS_TDC (
        P_COD_PAGO                           IN VARCHAR2,
        P_TIPO_MENSAJE                       IN VARCHAR2,
        P_PROCESSING_CODE                    IN VARCHAR2,
        P_AMOUNT                             IN VARCHAR2,
        P_AMOUNT_SETTLEMENT                  IN VARCHAR2,
        P_TRANSMISSION_DATETIME              IN VARCHAR2,
        P_AUDIT_NUMBER                       IN VARCHAR2,
        P_TIME_LOCAL_TRANSACTION             IN VARCHAR2,
        P_DATE_LOCAL_TRANSACTION             IN VARCHAR2,
        P_DATE_EXPIRATION                    IN VARCHAR2,
        P_DATE_CAPTURE                       IN VARCHAR2,
        P_MERCHANT_TYPE                      IN VARCHAR2,
        P_POINT_OF_SERVICE_ENTRY_MODE        IN VARCHAR2,
        P_POINT_OF_SERVICE_COND_COD          IN VARCHAR2,
        P_ACQUIRING_INSTITUTION_CODE         IN VARCHAR2,
        P_TRACK2                             IN VARCHAR2,
        P_RETRIEVAL_REFERENCE_NUMBER         IN VARCHAR2,
        P_AUTORIZATION_ID_REVERSAL           IN VARCHAR2,
        P_RESPONSE_CODE_REVERSAL             IN VARCHAR2,
        P_CARD_ACCEPTOR_TERMINAL_ID          IN VARCHAR2,
        P_CARD_ACCEPTOR_ID_CODE              IN VARCHAR2,
        P_CARD_ACCEPTOR_NAME_LOCATION        IN VARCHAR2,
        P_ADDITIONAL_DATA_PRIVATE            IN VARCHAR2,
        P_COUNTRY_CODE                       IN VARCHAR2,
        P_PERSONAL_ID_CODE                   IN VARCHAR2,
        P_ADVICE_REASON_CODE                 IN VARCHAR2,
        P_PRIVATE                            IN VARCHAR2,
        P_ORIGINAL_DATA_REVERSAL             IN VARCHAR2,
        P_CVV                                IN VARCHAR2,
        P_NAME_LOCATION                      IN VARCHAR2,
        P_RESERVE_FOR_PRIVATE_USE            IN VARCHAR2,
        P_NETWORK_MANAGEMENT_INFO            IN VARCHAR2,
        P_RAFAGA                             IN VARCHAR2);
  
  PROCEDURE PR_CARGAR_PAGOS_TDC (P_STATUS IN VARCHAR2,P_CURSOR OUT SYS_REFCURSOR);
  
   PROCEDURE PR_CAMBIAR_STATUS_PAGO (P_COD_PAGO         IN      VARCHAR2,
                                     P_STATUS           IN      VARCHAR2,
                                     P_INTENTOS         IN      VARCHAR2,
                                     P_COD_RESPUESTA    IN      VARCHAR2,
                                     P_RESULTADO        OUT     VARCHAR2);
  
  PROCEDURE PR_RESPUESTA_BTRANS_PAGO (  P_RESPONSE_TRANS_DATETIME         IN VARCHAR2,     
                                                P_RESPONSE_TRACE_AUDIT_NUMBER     IN VARCHAR2,
                                                P_RESPONSE_TIME_LOCAL_TRANS       IN VARCHAR2,   
                                                P_RESPONSE_DATE_LOCAL_TRANS       IN VARCHAR2,   
                                                P_RESPONSE_RETRIVAL_REF_NUM       IN VARCHAR2,  
                                                P_RESPONSE_AUTORIZATION_ID        IN VARCHAR2,   
                                                P_RESPONSE_CODE                   IN VARCHAR2,   
                                                P_AUDIT_NUMBER                    IN VARCHAR2,   
                                                P_COD_PAGO                        IN VARCHAR2,
                                                P_RESULTADO                     OUT VARCHAR2);
                                                
                                                
 PROCEDURE PR_CONSULTAR_PAGOS_TDC (P_CODCAR            IN     VARCHAR2,
                                   P_NUM_CTA           IN     VARCHAR2,
                                   P_NUM_DOC           IN     VARCHAR2,
                                   P_FECHA_DESDE       IN     VARCHAR2,
                                   P_FECHA_HASTA       IN     VARCHAR2,
                                   P_LISTA_PAGOS       OUT SYS_REFCURSOR,
                                   P_RESPUESTA         OUT VARCHAR2);
                                   
                                   
 PROCEDURE PR_DETALLE_PAGOS_TDC   (P_CODPAGO           IN     VARCHAR2,
                                   P_LISTA_DETALLE     OUT SYS_REFCURSOR,
                                   P_RESPUESTA         OUT VARCHAR2);
                                   
 PROCEDURE PR_GUARDAR_PAGO_TDC(P_CODCAR          IN VARCHAR2,     
                               P_NRO_CTA         IN VARCHAR2,
                               P_NRO_DOC         IN VARCHAR2,
                               P_TIPO_PAGO       IN VARCHAR2,
                               P_MONTO           IN VARCHAR2,
                               P_NRO_CTA_DEBITO  IN VARCHAR2,
                               P_BALANCE_TDC     IN VARCHAR2,
                                  P_CODBOFA     IN VARCHAR2,
                               P_RESULTADO       OUT VARCHAR2,
                               P_USUARIO         IN VARCHAR2);                                  
                                   
                                   
                                                                                    
    
END PCK_TARJETA;
/


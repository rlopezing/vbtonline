CREATE OR REPLACE PACKAGE VBTONLINE_TRANS.LIBRO_ORDENES
IS

PROCEDURE PR_INS_FLUJO_DOC_FA (IN_CODCAR           IN     VARCHAR2,
                                  IN_TIPO_OPERACION   IN     VARCHAR2,
                                  IN_PRODUCTO         IN     VARCHAR2,
                                  IN_MONEDA           IN     VARCHAR2,
                                  IN_MONTO            IN     VARCHAR2,
                                  IN_DETALLE          IN     VARCHAR2,
                                  IN_NUM_INSTRUCCION  IN     VARCHAR2,
                                  IN_USUARIO          IN     VARCHAR2,
                                  IN_IP               IN     VARCHAR2,
                                  P_RESULTADO            OUT VARCHAR2
   );
PROCEDURE PR_INS_FLUJO_DOC_FC (
      IN_NUM_REF          IN VARCHAR2,
      IN_USUARIO           IN VARCHAR2,
      IN_IP                IN VARCHAR2
      ,
      p_resultado OUT VARCHAR2
      );
      
  PROCEDURE PR_INS_FLUJO_DOC (
   IN_ID                IN OUT VARCHAR2,
   IN_CODCAR            IN     VARCHAR2,
   IN_PRECIRIF          IN     VARCHAR2,
   IN_CIRIF             IN     VARCHAR2,
   IN_NOMBRE            IN     VARCHAR2,
   IN_NOMBSEG           IN     VARCHAR2,
   IN_APELLIDO          IN     VARCHAR2,
   IN_APELLSEG          IN     VARCHAR2,
   IN_APELLCAS          IN     VARCHAR2,
   IN_CODASESOR         IN     VARCHAR2,
   IN_CODEJECUTIVO      IN     VARCHAR2,
   IN_ID_ESTATUS        IN     VARCHAR2,
   IN_ID_REQ_GRUP       IN     VARCHAR2,
   OBSERVACIONES        IN     VARCHAR2,
   IN_GUIA_FEDEX        IN     VARCHAR2,
   IN_NUMERO            IN OUT VARCHAR2,
   IN_FECHA_VALOR       IN     VARCHAR2,
   IN_TIPO_OPERACION    IN     VARCHAR2,
   IN_FECHA_OPERACION   IN     VARCHAR2,
   IN_PRODUCTO          IN     VARCHAR2,
   IN_MONEDA            IN     VARCHAR2,
   IN_MONTO             IN     VARCHAR2,
   IN_COMISION          IN     VARCHAR2,
   IN_COND_ESPECIAL     IN     VARCHAR2,
   IN_TASA_CAMBIO       IN     VARCHAR2,
   IN_NUMERO_CONTROL    IN     VARCHAR2,
   IN_CONTRATO_ONLINE   IN     VARCHAR2,
   IN_NUM_REF_ONLINE       IN     VARCHAR2,   
   IN_USUARIO           IN     VARCHAR2,
   IN_IP                IN     VARCHAR2,
   IN_OPERACION         IN     VARCHAR2);

PROCEDURE PR_UPD_GRUPO_NUMERO (
   IN_ID_REQ_GRUP   IN VARCHAR2,
   IN_NUMERO        IN VARCHAR2,
   IN_FECHA_OPERACION  IN VARCHAR2,   
   IN_USUARIO       IN VARCHAR2,
   IN_IP            IN VARCHAR2,
   IN_OPERACION     IN VARCHAR2);
                                             
END LIBRO_ORDENES;
/


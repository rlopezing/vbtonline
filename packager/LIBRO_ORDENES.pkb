CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.LIBRO_ORDENES
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
                                  P_RESULTADO            OUT VARCHAR2)
   IS
      V_CODCAR           CTACAR.CODCAR%TYPE := IN_CODCAR;

      V_ID_REQ_GRUP      NUMBER := 0;
      V_TIPO_OPERACION   ELEMENTOS_TIPOS.DESCRIPCION%TYPE
                            := IN_TIPO_OPERACION;

      V_PRODUCTO         FLUJO_DOC.PRODUCTO%TYPE := IN_PRODUCTO;
      V_MONEDA           FLUJO_DOC.MONEDA%TYPE := IN_MONEDA;

      V_DETALLE          FLUJO_DOC.OBSERVACIONES%TYPE := IN_DETALLE;
      V_NUM_INSTRUCCION          FLUJO_DOC.NUM_REF_ONLINE%TYPE := IN_NUM_INSTRUCCION;

      V_ID               FLUJO_DOC.ID%TYPE := '';
      V_PRECIRIF         FLUJO_DOC.PRECIRIF%TYPE := '';
      V_CIRIF            FLUJO_DOC.CIRIF%TYPE := '';
      V_NOMBRE           FLUJO_DOC.NOMBRE%TYPE := '';
      V_NOMBSEG          FLUJO_DOC.NOMBSEG%TYPE := '';
      V_APELLIDO         FLUJO_DOC.APELLIDO%TYPE := '';
      V_APELLSEG         FLUJO_DOC.APELLSEG%TYPE := '';
      V_APELLCAS         FLUJO_DOC.APELLCAS%TYPE := '';
      V_CODASESOR        CTACAR.CODNEG%TYPE := '';
      V_CODEJECUTIVO     CTACAR.CODEJE%TYPE := '';
     

      V_ID_ESTATUS       FLUJO_DOC.ID_ESTATUS%TYPE := '';

      V_NUMERO           FLUJO_DOC.NUMERO%TYPE := '';

      BENEFICIARY_BANK_TYPE_NUMBER     ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_NUMBER%TYPE;
      INTER_BANK_TYPE_NUMBER           ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_NUMBER%TYPE;
      --

      MESSAGE            VARCHAR2 (800) := '';
      NO_VALIDO          EXCEPTION;
      V_FASE             NUMBER := 0;
      V_USUARIO          VARCHAR2 (30) := UPPER (IN_USUARIO);
      V_IP               VARCHAR2 (16) := IN_IP;
   --
   BEGIN
      V_FASE := 100;
      --DBMS_OUTPUT.ENABLE (1000000);
      --DBMS_OUTPUT.PUT_LINE ('entro');

      SELECT DESCRIPCION
        INTO V_ID_REQ_GRUP
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000011' AND CODELEMENTO = 'LIBRO_ORD';


      V_FASE := 200;

      SELECT DESCRIPCION
        INTO V_TIPO_OPERACION
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000012' AND CODELEMENTO = V_TIPO_OPERACION;

      V_FASE := 300;

      SELECT DESCRIPCION
        INTO V_PRODUCTO
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000013' AND CODELEMENTO = V_PRODUCTO;

      V_FASE := 400;

      SELECT CA.CODCAR,
             PE.PRECIRIF,
             UPPER (PE.CIRIF),
             UPPER (PE.APELLIDO),
             UPPER (PE.APELLSEG),
             UPPER (PE.APELLCAS),
             UPPER (PE.NOMBRE),
             UPPER (PE.NOMBSEG),
             CA.CODEJE,
             CA.CODNEG
        INTO V_CODCAR,
             V_PRECIRIF,
             V_CIRIF,
             V_APELLIDO,
             V_APELLSEG,
             V_APELLCAS,
             V_NOMBRE,
             V_NOMBSEG,
             V_CODEJECUTIVO,
             V_CODASESOR
        FROM CTAPER PE, CTACAR CA
       WHERE     CA.CODPERCLI = PE.CODPER
             AND CA.STATCAR = 'A'
             AND CA.CODCAR = V_CODCAR;

      V_FASE := 500;
      
            
           for reg in ( SELECT NUM_INSTRUCCION, 
                CODCAR, 
                FECHA_ESTATUS, 
                BOF00_CODMOV, 
                BOF03_CURRENCY_CODE, 
                BOF16_AMOUNT,   
                BOF00_CODCOL,  
                BENEFICIARY_TYPE, 
                BENEFICIARY_TYPE_NUMBER, 
                BENEFICIARY_DESCRIPTION, 
                BENEFICIARY_BANK_TYPE, 
                BENEFICIARY_BANK_TYPE_NUMBER, 
                BENEFICIARY_BANK_DESCRIPTION, 
                INTERMEDIARY_BANK_TYPE, 
                INTERMEDIARY_BANK_TYPE_NUMBER, 
                INTERMEDIARY_BANK_DESCRIPTION,
                 ORIGINATORS_BANK_TYPE, 
                CODTIPOMOV, 
                CODCOL_ORIGEN,   
                REFBANMOV, 
                BENEFICIARIO, 
                OBSERV, 
                EMAIL_BENEFICIARIO, 
                EMAIL_ORIGEN, 
                MOTIVO_ESTATUS, 
                CODCOL,  
                BENEFICIARY_NAME, 
                BENEFICIARY_BANK_NAME, 
                INTERMEDIARY_BANK_NAME, 
                MOTIVO, 
                BENEFICIARY_BANK_TYPE_SWIFT, 
                BENEFICIARY_BANK_TNUM_SWIFT, 
                INTERMEDIARY_BANK_TYPE_SWIFT, 
                INTERMEDIARY_BANK_TNUM_SWIFT,
                ORIGINATORS_INFO,
                ORIGINATORS_NAME,
                FFC_NUMBER,
                FFC_NAME,
                DECODE(BENEFICIARY_TYPE_PERSON,'NAT','PERSONA NATURAL','JUR','PERSONA JURIDICA') tipo_persona
                FROM ORDEN_TRANSFERENCIA T 
                WHERE to_char(T.NUM_INSTRUCCION ) = (V_NUM_INSTRUCCION)) 
                loop

  IF reg.CODTIPOMOV = 'CID'
            THEN
            

               V_DETALLE :=
                     'Cartera Origen: '
                  || reg.CODCAR
                  || ' - Cuenta: '
                  || reg.BOF00_CODCOL
                       
                  || '<BR>Nombre del Beneficiario: '
                  || reg.BENEFICIARIO
                  || '<BR>Cartera Destino: '
                  || reg.REFBANMOV
                  || ' - Cuenta: '
                  || reg.CODCOL_ORIGEN;
            ELSE
            


               IF reg.BENEFICIARY_BANK_TNUM_SWIFT IS NULL
               THEN
                  BENEFICIARY_BANK_TYPE_NUMBER :=
                     reg.BENEFICIARY_BANK_TYPE_NUMBER;
               ELSE
                  BENEFICIARY_BANK_TYPE_NUMBER :=
                     reg.BENEFICIARY_BANK_TNUM_SWIFT;
               END IF;


               V_DETALLE :=
                     'Cartera Origen: '
                  || reg.CODCAR
                  || ' - Cuenta: '
                  || reg.BOF00_CODCOL
         ||'<BR>Tipo Beneficiario:'
                  || reg.tipo_persona                      
                  || '<BR>Beneficiario: '
                  || reg.BENEFICIARIO
                  || '<BR>Cuenta Destino: '
                  || reg.BENEFICIARY_TYPE_NUMBER
                  || '<BR>Banco Beneficiario: '
                  || BENEFICIARY_BANK_TYPE_NUMBER
                  || '- '
                  || reg.BENEFICIARY_BANK_NAME;
                  
                IF LENGTH(reg.FFC_NUMBER)>0 THEN
                  V_DETALLE := V_DETALLE || '<BR>Para Futuro Credito a ctaNro.: '
                      || reg.FFC_NUMBER
                      || ' A NOMBRE DE : '
                      || reg.FFC_NAME;
                  
                  END IF;
            END IF;

      
      

      PR_INS_FLUJO_DOC (V_ID,                                        --OUT
                            V_CODCAR,
                            V_PRECIRIF,
                            V_CIRIF,
                            V_NOMBRE,
                            V_NOMBSEG,
                            V_APELLIDO,
                            V_APELLSEG,
                            V_APELLCAS,
                            V_CODASESOR,
                            V_CODEJECUTIVO,
                            V_ID_ESTATUS,
                            V_ID_REQ_GRUP, --V_ID_REQ_GRUP (EN ELEMENTOS TIPO)*
                            V_DETALLE, --V_OBSERVACIONES
                            '',                                 --V_GUIA_FEDEX
                            V_NUMERO,                                    --OUT
                            '',                                --V_FECHA_VALOR
                            V_TIPO_OPERACION,           --(EN ELEMENTOS TIPO)*
                            TO_CHAR(SYSDATE,'DD/MM/YYYY'),                       --V_FECHA_OPERACION
                            V_PRODUCTO,                 --(EN ELEMENTOS TIPO)*
                            V_MONEDA,                         -- POR PARAMETRO
                            IN_MONTO,                          --POR PARAMETRO
                            '',                                   --V_COMISION
                            '',                              --V_COND_ESPECIAL
                            '',                                --V_TASA_CAMBIO
                            '',                             --V_NUMERO_CONTROL
                            '',                              --CONTRATO ONLINE
                            V_NUM_INSTRUCCION, --IN_NUM_INSTRUCCION
                            'VBTONLINE',
                            V_IP,
                            'IN');
                            
                            
                            end loop;
      V_FASE := 600;

      P_RESULTADO := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         MESSAGE := SQLERRM;

         ROLLBACK;
         PR_INS_ERRORES (V_USUARIO,
                             V_IP,
                             SYSDATE,
                             MESSAGE);

         P_RESULTADO := SUBSTR (SQLERRM, 1, 300);
      
   END PR_INS_FLUJO_DOC_FA;

   PROCEDURE PR_INS_FLUJO_DOC_FC (IN_NUM_REF    IN     VARCHAR2,
                                  IN_USUARIO    IN     VARCHAR2,
                                  IN_IP         IN     VARCHAR2,
                                  P_RESULTADO      OUT VARCHAR2)
   IS
      V_NUM_REFERENCIA                 VARCHAR (1000);


      V_ID_REQ_GRUP                    NUMBER := 0;
      V_TIPO_OPERACION                 ELEMENTOS_TIPOS.DESCRIPCION%TYPE := '';

      V_PRODUCTO                       FLUJO_DOC.PRODUCTO%TYPE := '';
      V_MONEDA                         FLUJO_DOC.MONEDA%TYPE := '';

      V_DETALLE                        FLUJO_DOC.OBSERVACIONES%TYPE := '';

      V_ID                             FLUJO_DOC.ID%TYPE := '';
      V_PRECIRIF                       FLUJO_DOC.PRECIRIF%TYPE := '';
      V_CIRIF                          FLUJO_DOC.CIRIF%TYPE := '';
      V_NOMBRE                         FLUJO_DOC.NOMBRE%TYPE := '';
      V_NOMBSEG                        FLUJO_DOC.NOMBSEG%TYPE := '';
      V_APELLIDO                       FLUJO_DOC.APELLIDO%TYPE := '';
      V_APELLSEG                       FLUJO_DOC.APELLSEG%TYPE := '';
      V_APELLCAS                       FLUJO_DOC.APELLCAS%TYPE := '';
      V_CODASESOR                      FLUJO_DOC.CODASE%TYPE := '';
      V_CODEJECUTIVO                   FLUJO_DOC.CODEJE%TYPE := '';

      V_ID_ESTATUS                     FLUJO_DOC.ID_ESTATUS%TYPE := '';

      V_NUMERO                         FLUJO_DOC.NUMERO%TYPE := '';


      BENEFICIARY_BANK_TYPE_NUMBER     ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_NUMBER%TYPE;
      INTER_BANK_TYPE_NUMBER           ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_NUMBER%TYPE;


      V_NUM_INSTRUCCION                ORDEN_TRANSFERENCIA.NUM_INSTRUCCION%TYPE;
      V_CODCAR                         ORDEN_TRANSFERENCIA.CODCAR%TYPE;

      V_FECHA_ESTATUS                  ORDEN_TRANSFERENCIA.FECHA_ESTATUS%TYPE;
      V_BOF00_CODMOV                   ORDEN_TRANSFERENCIA.BOF00_CODMOV%TYPE;
      V_BOF03_CURRENCY_CODE            ORDEN_TRANSFERENCIA.BOF03_CURRENCY_CODE%TYPE;
      V_BOF16_AMOUNT                   ORDEN_TRANSFERENCIA.BOF16_AMOUNT%TYPE;
      V_BOF00_CODCOL                   ORDEN_TRANSFERENCIA.BOF00_CODCOL%TYPE;
      V_BENEFICIARY_TYPE               ORDEN_TRANSFERENCIA.BENEFICIARY_TYPE%TYPE;
      V_BENEFICIARY_TYPE_NUMBER        ORDEN_TRANSFERENCIA.BENEFICIARY_TYPE_NUMBER%TYPE;
      V_BENEFICIARY_DESCRIPTION        ORDEN_TRANSFERENCIA.BENEFICIARY_DESCRIPTION%TYPE;
      V_BENEFICIARY_BANK_TYPE          ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE%TYPE;
      V_BENEFICIARY_BANK_TYPE_NUMBER   ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_NUMBER%TYPE;
      V_BENEFICIARY_BANK_DESCRIPTION   ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_DESCRIPTION%TYPE;
      V_INTERMEDIARY_BANK_TYPE         ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE%TYPE;
      V_INTER_BANK_TYPE_NUM            ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_NUMBER%TYPE;
      V_INTER_BANK_DESC                ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_DESCRIPTION%TYPE;
      V_ORIGINATORS_BANK_TYPE          ORDEN_TRANSFERENCIA.ORIGINATORS_BANK_TYPE%TYPE;
      V_CODTIPOMOV                     ORDEN_TRANSFERENCIA.CODTIPOMOV%TYPE;
      V_CODCOL_ORIGEN                  ORDEN_TRANSFERENCIA.CODCOL_ORIGEN%TYPE;
      V_REFBANMOV                      ORDEN_TRANSFERENCIA.REFBANMOV%TYPE;
      V_BENEFICIARIO                   ORDEN_TRANSFERENCIA.BENEFICIARIO%TYPE;
      V_OBSERV                         ORDEN_TRANSFERENCIA.OBSERV%TYPE;
      V_EMAIL_BENEFICIARIO             ORDEN_TRANSFERENCIA.EMAIL_BENEFICIARIO%TYPE;
      V_EMAIL_ORIGEN                   ORDEN_TRANSFERENCIA.EMAIL_ORIGEN%TYPE;
      V_MOTIVO_ESTATUS                 ORDEN_TRANSFERENCIA.MOTIVO_ESTATUS%TYPE;
      V_CODCOL                         ORDEN_TRANSFERENCIA.CODCOL%TYPE;
      V_BENEFICIARY_NAME               ORDEN_TRANSFERENCIA.BENEFICIARY_NAME%TYPE;
      V_BENEFICIARY_BANK_NAME          ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_NAME%TYPE;
      V_INTERMEDIARY_BANK_NAME         ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_NAME%TYPE;
      V_MOTIVO                         ORDEN_TRANSFERENCIA.MOTIVO%TYPE;
      V_BENEFICIARY_BANK_TYPE_SWIFT    ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_SWIFT%TYPE;
      V_BENEFICIARY_BANK_TNUM_SWIFT    ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TNUM_SWIFT%TYPE;
      V_INTERMEDIARY_BANK_TYPE_SWIFT   ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_SWIFT%TYPE;
      V_INTERMEDIARY_BANK_TNUM_SWIFT   ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TNUM_SWIFT%TYPE;

      V_ORIGINATORS_INFO               ORDEN_TRANSFERENCIA.ORIGINATORS_INFO%TYPE;
      V_ORIGINATORS_NAME               ORDEN_TRANSFERENCIA.ORIGINATORS_NAME%TYPE;
      V_FFC_NUMBER                     ORDEN_TRANSFERENCIA.FFC_NUMBER%TYPE;
      V_FFC_NAME                       ORDEN_TRANSFERENCIA.FFC_NAME%TYPE;

      V_OPERACION_INTERNA              ELEMENTOS_TIPOS.DESCRIPCION%TYPE := '';
      V_OPERACION_EXTERNA              ELEMENTOS_TIPOS.DESCRIPCION%TYPE := '';
      
      V_tipo_persona    varchar2(30) := '';
      --

      DATOS                            SYS_REFCURSOR;

      D                                ORDEN_TRANSFERENCIA%ROWTYPE;
      SQLELEM                          VARCHAR2 (8000);
      SQLSTRING                        VARCHAR2 (8000);
      MENSAJE                          VARCHAR2 (8000);
      MESSAGE                          VARCHAR2 (1000) := '';
      NO_VALIDO                        EXCEPTION;
      V_FASE                           NUMBER := 0;
      V_USUARIO                        VARCHAR2 (30) := UPPER (IN_USUARIO);
      V_IP                             VARCHAR2 (16) := IN_IP;
   --
   BEGIN
      V_FASE := 100;

      --V_num_instruccion := trim(substr(IN_NUM_REF,2,length(IN_NUM_REF)));

      --V_num_instruccion := trim(substr(IN_NUM_REF,1,length(IN_NUM_REF)-1));


      SELECT DESCRIPCION
        INTO V_ID_REQ_GRUP
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000011' AND CODELEMENTO = 'LIBRO_ORD';

      V_FASE := 200;

      SELECT DESCRIPCION
        INTO V_OPERACION_INTERNA
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000012' AND CODELEMENTO = 'INTERNA';

      V_FASE := 300;

      SELECT DESCRIPCION
        INTO V_OPERACION_EXTERNA
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000012' AND CODELEMENTO = 'EXTERNA';

      V_FASE := 400;

      SELECT DESCRIPCION
        INTO V_PRODUCTO
        FROM ELEMENTOS_TIPOS
       WHERE CODTIPO = '0000000013' AND CODELEMENTO = 'SAVING';

      V_FASE := 500;

      SQLSTRING :=
            'SELECT NUM_INSTRUCCION, 
                CODCAR, 
                FECHA_ESTATUS, 
                BOF00_CODMOV, 
                BOF03_CURRENCY_CODE, 
                BOF16_AMOUNT,   
                BOF00_CODCOL,  
                BENEFICIARY_TYPE, 
                BENEFICIARY_TYPE_NUMBER, 
                BENEFICIARY_DESCRIPTION, 
                BENEFICIARY_BANK_TYPE, 
                BENEFICIARY_BANK_TYPE_NUMBER, 
                BENEFICIARY_BANK_DESCRIPTION, 
                INTERMEDIARY_BANK_TYPE, 
                INTERMEDIARY_BANK_TYPE_NUMBER, 
                INTERMEDIARY_BANK_DESCRIPTION,
                 ORIGINATORS_BANK_TYPE, 
                CODTIPOMOV, 
                CODCOL_ORIGEN,   
                REFBANMOV, 
                BENEFICIARIO, 
                OBSERV, 
                EMAIL_BENEFICIARIO, 
                EMAIL_ORIGEN, 
                MOTIVO_ESTATUS, 
                CODCOL,  
                BENEFICIARY_NAME, 
                BENEFICIARY_BANK_NAME, 
                INTERMEDIARY_BANK_NAME, 
                MOTIVO, 
                BENEFICIARY_BANK_TYPE_SWIFT, 
                BENEFICIARY_BANK_TNUM_SWIFT, 
                INTERMEDIARY_BANK_TYPE_SWIFT, 
                INTERMEDIARY_BANK_TNUM_SWIFT,
                ORIGINATORS_INFO,
                ORIGINATORS_NAME,
                FFC_NUMBER,
                FFC_NAME,
                DECODE(BENEFICIARY_TYPE_PERSON,''NAT'',''PERSONA NATURAL'',''JUR'',''PERSONA JURIDICA'') tipo_persona
                FROM ORDEN_TRANSFERENCIA T 
                WHERE to_char(T.NUM_INSTRUCCION ) IN ('
         || IN_NUM_REF
         || ')';

      V_FASE := 600;

      OPEN DATOS FOR SQLSTRING;

      BEGIN
         LOOP
            V_FASE := 601;

            FETCH DATOS
            INTO V_NUM_INSTRUCCION,
                 V_CODCAR,
                 V_FECHA_ESTATUS,
                 V_BOF00_CODMOV,
                 V_BOF03_CURRENCY_CODE,
                 V_BOF16_AMOUNT,
                 V_BOF00_CODCOL,
                 V_BENEFICIARY_TYPE,
                 V_BENEFICIARY_TYPE_NUMBER,
                 V_BENEFICIARY_DESCRIPTION,
                 V_BENEFICIARY_BANK_TYPE,
                 V_BENEFICIARY_BANK_TYPE_NUMBER,
                 V_BENEFICIARY_BANK_DESCRIPTION,
                 V_INTERMEDIARY_BANK_TYPE,
                 V_INTER_BANK_TYPE_NUM,
                 V_INTER_BANK_DESC,
                 V_ORIGINATORS_BANK_TYPE,
                 V_CODTIPOMOV,
                 V_CODCOL_ORIGEN,
                 V_REFBANMOV,
                 V_BENEFICIARIO,
                 V_OBSERV,
                 V_EMAIL_BENEFICIARIO,
                 V_EMAIL_ORIGEN,
                 V_MOTIVO_ESTATUS,
                 V_CODCOL,
                 V_BENEFICIARY_NAME,
                 V_BENEFICIARY_BANK_NAME,
                 V_INTERMEDIARY_BANK_NAME,
                 V_MOTIVO,
                 V_BENEFICIARY_BANK_TYPE_SWIFT,
                 V_BENEFICIARY_BANK_TNUM_SWIFT,
                 V_INTERMEDIARY_BANK_TYPE_SWIFT,
                 V_INTERMEDIARY_BANK_TNUM_SWIFT,
                 V_ORIGINATORS_INFO,
                 V_ORIGINATORS_NAME,
                 V_FFC_NUMBER,
                 V_FFC_NAME,
                 v_tipo_persona;

            EXIT WHEN DATOS%NOTFOUND;

            V_FASE := 602;

            SELECT CA.CODCAR,
                   PE.PRECIRIF,
                   UPPER (PE.CIRIF),
                   UPPER (PE.APELLIDO),
                   UPPER (PE.APELLSEG),
                   UPPER (PE.APELLCAS),
                   UPPER (PE.NOMBRE),
                   UPPER (PE.NOMBSEG),
                   CA.CODEJE,
                   CA.CODNEG
              INTO V_CODCAR,
                   V_PRECIRIF,
                   V_CIRIF,
                   V_APELLIDO,
                   V_APELLSEG,
                   V_APELLCAS,
                   V_NOMBRE,
                   V_NOMBSEG,
                   V_CODEJECUTIVO,
                   V_CODASESOR
              FROM CTAPER PE, CTACAR CA
             WHERE     CA.CODPERCLI = PE.CODPER
                   AND CA.STATCAR = 'A'
                   AND CA.CODCAR = V_CODCAR;


            IF V_CODTIPOMOV = 'CID'
            THEN
               V_TIPO_OPERACION := V_OPERACION_INTERNA;

               V_DETALLE :=
                     'Cartera Origen: '
                  || V_CODCAR
                  || ' - Cuenta: '
                  || V_BOF00_CODCOL
                  || '<BR>Nombre del Beneficiario: '
                  || V_BENEFICIARIO
                  || '<BR>Cartera Destino: '
                  || V_REFBANMOV
                  || ' - Cuenta: '
                  || V_CODCOL_ORIGEN;
            ELSE
               V_TIPO_OPERACION := V_OPERACION_EXTERNA;


               IF V_BENEFICIARY_BANK_TNUM_SWIFT IS NULL
               THEN
                  BENEFICIARY_BANK_TYPE_NUMBER :=
                     V_BENEFICIARY_BANK_TYPE_NUMBER;
               ELSE
                  BENEFICIARY_BANK_TYPE_NUMBER :=
                     V_BENEFICIARY_BANK_TNUM_SWIFT;
               END IF;


               V_DETALLE :=
                     'Cartera Origen: '
                  || V_CODCAR
                  || ' - Cuenta: '
                  || V_BOF00_CODCOL
 ||'<BR>Tipo Beneficiario:'
                  || v_tipo_persona                          
                  || '<BR>Beneficiario: '
                  || V_BENEFICIARIO
                  || '<BR>Cuenta Destino: '
                  || V_BENEFICIARY_TYPE_NUMBER
                  || '<BR>Banco Beneficiario: '
                  || BENEFICIARY_BANK_TYPE_NUMBER
                  || '- '
                  || V_BENEFICIARY_BANK_NAME;
                  
                IF LENGTH(V_FFC_NUMBER)>0 THEN
                  V_DETALLE := V_DETALLE || '<BR>Para Futuro Credito a ctaNro.: '
                      || V_FFC_NUMBER
                      || ' A NOMBRE DE : '
                      || V_FFC_NAME;
                  
                  END IF;
            END IF;

            PR_INS_FLUJO_DOC (V_ID,                                  --OUT
                                  V_CODCAR,
                                  V_PRECIRIF,
                                  V_CIRIF,
                                  V_NOMBRE,
                                  V_NOMBSEG,
                                  V_APELLIDO,
                                  V_APELLSEG,
                                  V_APELLCAS,
                                  V_CODASESOR,
                                  V_CODEJECUTIVO,
                                  V_ID_ESTATUS,
                                  V_ID_REQ_GRUP, --V_ID_REQ_GRUP (EN ELEMENTOS TIPO)*
                                  V_DETALLE, --V_OBSERVACIONES
                                  '',                           --V_GUIA_FEDEX
                                  V_NUMERO,                              --OUT
                                  '',                          --V_FECHA_VALOR
                                  V_TIPO_OPERACION,     --(EN ELEMENTOS TIPO)*
                                  TO_CHAR(SYSDATE,'DD/MM/YYYY'),                 --V_FECHA_OPERACION
                                  V_PRODUCTO,           --(EN ELEMENTOS TIPO)*
                                  V_BOF03_CURRENCY_CODE,              --moneda
                                  ABS (V_BOF16_AMOUNT),                --monto
                                  '',                             --V_COMISION
                                  '',                        --V_COND_ESPECIAL
                                  '',                          --V_TASA_CAMBIO
                                  '',                       --V_NUMERO_CONTROL
                                  '',                        --CONTRATO ONLINE
                                  V_NUM_INSTRUCCION, --V_NUM_INSTRUCCION
                                  'VBTONLINE',
                                  V_IP,
                                  'IN');
         END LOOP;


         CLOSE DATOS;
      END;

      V_FASE := 700;

      P_RESULTADO := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         P_RESULTADO := SUBSTR (SQLERRM, 1, 300);
        
         ROLLBACK;
         MESSAGE := SQLERRM;
         PR_INS_ERRORES (V_USUARIO,
                             V_IP,
                             SYSDATE,
                             MESSAGE);

        
   END PR_INS_FLUJO_DOC_FC;
   
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
   IN_OPERACION         IN     VARCHAR2)
IS
   V_ID                FLUJO_DOC.ID%TYPE := IN_ID;
   V_CODCAR            FLUJO_DOC.CODCAR%TYPE := IN_CODCAR;
   V_PRECIRIF          FLUJO_DOC.PRECIRIF%TYPE := TRIM (UPPER (IN_PRECIRIF));
   V_CIRIF             FLUJO_DOC.CIRIF%TYPE := UPPER (IN_CIRIF);
   V_NOMBRE            FLUJO_DOC.NOMBRE%TYPE := UPPER (IN_NOMBRE);
   V_NOMBSEG           FLUJO_DOC.NOMBSEG%TYPE := UPPER (IN_NOMBSEG);
   V_APELLIDO          FLUJO_DOC.APELLIDO%TYPE := UPPER (IN_APELLIDO);
   V_APELLSEG          FLUJO_DOC.APELLSEG%TYPE := UPPER (IN_APELLSEG);
   V_APELLCAS          FLUJO_DOC.APELLCAS%TYPE := UPPER (IN_APELLCAS);
   V_CODASE            FLUJO_DOC.CODASE%TYPE := IN_CODASESOR;
   V_CODEJE            FLUJO_DOC.CODEJE%TYPE := IN_CODEJECUTIVO;
   V_ID_ESTATUS        FLUJO_DOC.ID_ESTATUS%TYPE := TO_NUMBER (IN_ID_ESTATUS);
   V_ID_REQ_GRUP       FLUJO_DOC.ID_REQ_GRUP%TYPE := TO_NUMBER (IN_ID_REQ_GRUP);

   V_OBSERVACIONES     FLUJO_DOC.OBSERVACIONES%TYPE := UPPER (OBSERVACIONES);
   V_GUIA_FEDEX        FLUJO_DOC.GUIA_FEDEX%TYPE := UPPER (IN_GUIA_FEDEX);
   V_NUMERO            FLUJO_DOC.NUMERO%TYPE := UPPER (IN_NUMERO);
   --Agregado por mbernot 17/05/2013
   V_FECHA_VALOR       FLUJO_DOC.FECHA_VALOR%TYPE := IN_FECHA_VALOR;
   V_TIPO_OPERACION    FLUJO_DOC.TIPO_OPERACION%TYPE := IN_TIPO_OPERACION;
   V_FECHA_OPERACION   FLUJO_DOC.FECHA_OPERACION%TYPE
                          := TO_DATE (IN_FECHA_OPERACION, 'DD/MM/YYYY');
   V_PRODUCTO          FLUJO_DOC.PRODUCTO%TYPE := IN_PRODUCTO;
   V_MONEDA            FLUJO_DOC.MONEDA%TYPE := IN_MONEDA;
   V_MONTO             FLUJO_DOC.MONTO%TYPE
                          := TO_NUMBER (IN_MONTO, '99999999999999999.99');
   V_COMISION          FLUJO_DOC.COMISION%TYPE
                          := TO_NUMBER (IN_COMISION, '99999999999999999.99');
   V_COND_ESPECIAL     FLUJO_DOC.COND_ESPECIAL%TYPE := IN_COND_ESPECIAL;
   V_TASA_CAMBIO       FLUJO_DOC.TASA_CAMBIO%TYPE
      := TO_NUMBER (IN_TASA_CAMBIO, '99999999999999999.99');
   V_NUMERO_CONTROL    FLUJO_DOC.NUMERO_CONTROL%TYPE := IN_NUMERO_CONTROL;
   V_CONTRATO_ONLINE   FLUJO_DOC.CONTRATO_ONLINE%TYPE := IN_CONTRATO_ONLINE;
   V_NUM_REF_ONLINE       FLUJO_DOC.NUM_REF_ONLINE%TYPE := IN_NUM_REF_ONLINE;   
   --
   T_FECHA_VALOR       FLUJO_DOC.FECHA_VALOR%TYPE := '';
   T_TIPO_OPERACION    FLUJO_DOC.TIPO_OPERACION%TYPE := '';
   T_FECHA_OPERACION   FLUJO_DOC.FECHA_OPERACION%TYPE := '';
   T_PRODUCTO          FLUJO_DOC.PRODUCTO%TYPE := '';
   T_MONEDA            FLUJO_DOC.MONEDA%TYPE := '';
   T_MONTO             FLUJO_DOC.MONTO%TYPE := '';
   T_COMISION          FLUJO_DOC.COMISION%TYPE := '';
   T_COND_ESPECIAL     FLUJO_DOC.COND_ESPECIAL%TYPE := '';
   T_TASA_CAMBIO       FLUJO_DOC.TASA_CAMBIO%TYPE := '';
   T_NUMERO_CONTROL    FLUJO_DOC.NUMERO_CONTROL%TYPE := '';
   T_NUMERO            FLUJO_DOC.NUMERO%TYPE := '';
   --
   V_EXISTE            PLS_INTEGER := 0;
   V_EST_GRU_DOCS      FLUJO_DOC.EST_GRU_DOCS%TYPE := '';
   V_NM_REQ_GRUP       GRUPO_REQUISITO.NOMBRE%TYPE := '';
   V_GENERA_NUMERO     FLUJOS_ESTATUS.GENERA_NUMERO%TYPE := NULL;
   --V_NUMERO            FLUJO_DOC.NUMERO%TYPE := NULL;
   COMENTARIO          FLUJO_DOC_HIS.OBSERVACIONES%TYPE := '';
   MESSAGE             VARCHAR2 (600) := '';
   NO_VALIDO           EXCEPTION;
   V_FASE              NUMBER := 0;
   V_OPERACION         VARCHAR2 (3) := UPPER (IN_OPERACION);
   V_USUARIO           VARCHAR2 (30) := UPPER (IN_USUARIO);
   V_IP                VARCHAR2 (16) := IN_IP;
   --   V_NUMERO          FLUJO_DOC.NUMERO%TYPE := 1;
   V_ID_GRU_NUM        GRUPO_NUMERO.ID_GRU_NUM%TYPE := '';

   V_DESCRIPCION       FLUJO_DOC_HIS.OBSERVACIONES%TYPE := '';
   V_CONT              NUMBER := 0;
   --
   V_PAD_NIVEL         PLS_INTEGER := 0;
   V_PAD_IDPAD         FLUJO_DOC.ID%TYPE := 0;
   V_PAD_NUMERO        FLUJO_DOC.NUMERO%TYPE := '';
   V_PAD_NUMFLUJOS     PLS_INTEGER := 0;

   V_APER_ACT          BOOLEAN := FALSE;
   V_CAMBIO            BOOLEAN := FALSE;
--
BEGIN
   V_FASE := 10;

   --DBMS_OUTPUT.enable (1000000);

   --DBMS_OUTPUT.put_line ('entro');
   IF V_OPERACION = 'I' OR V_OPERACION = 'IN'
   THEN
      V_FASE := 20;

      SELECT NVL (MAX (ID), 0) + 1 INTO V_ID FROM FLUJO_DOC;


      V_FASE := 30;

      BEGIN
         SELECT F.ID_ESTATUS
           INTO V_ID_ESTATUS
           FROM FLUJOS_ESTATUS F,
                ESTATUS E,
                (SELECT FE.ID_REQ_GRUP, ID_ESTATUS
                   FROM (SELECT MIN (F.NUMERO_PASO) NUMERO_PASO
                           FROM FLUJOS_ESTATUS F
                          WHERE F.ID_REQ_GRUP = V_ID_REQ_GRUP) F,
                        FLUJOS_ESTATUS FE
                  WHERE     FE.ID_REQ_GRUP = V_ID_REQ_GRUP
                        AND FE.NUMERO_PASO = F.NUMERO_PASO) PASO
          WHERE     F.ID_REQ_GRUP = PASO.ID_REQ_GRUP
                AND F.ID_ESTATUS = PASO.ID_ESTATUS
                AND F.ID_ESTATUS = E.ID;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            MESSAGE :=
                  'DEBE DEFINIR EL FLUJO PARA EL GRUPO DE DOCUMENTOS '
               || V_ID_REQ_GRUP;
            RAISE NO_VALIDO;
      END;

      V_FASE := 35;

      BEGIN
         SELECT V_USUARIO
           INTO V_USUARIO
           FROM FLUJO_ENC
          WHERE     ID_REQ_GRUP = V_ID_REQ_GRUP
                AND ID_ESTATUS = V_ID_ESTATUS
                AND LOGIN = V_USUARIO;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            MESSAGE :=
                  'EL USUARIO '
               || V_USUARIO
               || ' NO TIENE PERMISO EN ESTA FASE PARA CREAR FLUJOS DE DOCUMENTOS';
            RAISE NO_VALIDO;
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR CONSULTANDO USUARIO EN EL FLUJO ' || SQLERRM;
            RAISE NO_VALIDO;
      END;

      -- isabel Pallares: 03/10/2012
      --      IF V_OBSERVACIONES IS NULL
      --      THEN
      --         MESSAGE := 'DEBE AGREGAR UNA OBSERVACION';
      --         RAISE NO_VALIDO;
      --     END IF;

      V_FASE := 40;

      IF V_PRECIRIF IS NULL
      THEN
         MESSAGE := 'DEBE COLOCAR UN PREFIJO PARA EL RIF/CI VALIDO';
         RAISE NO_VALIDO;
      END IF;

      IF V_CIRIF IS NULL
      THEN
         MESSAGE := 'DEBE COLOCAR UN NUMERO PARA EL RIF/CI VALIDO';
         RAISE NO_VALIDO;
      END IF;

      IF V_APELLIDO IS NULL
      THEN
         MESSAGE :=
            'DEBE COLOCAR UN VALOR VALIDO PARA EL CAMPO PRIMER APELLIDO/EMPRESA';
         RAISE NO_VALIDO;
      END IF;

      IF V_CODCAR IS NOT NULL
      THEN
         BEGIN
            SELECT CAR.CODCAR, CLI.CODNEG, CLI.CODEJE
              INTO V_CODCAR, V_CODASE, V_CODEJE
              FROM CTACLI_VENBSF CLI,
                   CTAPER_VENBSF PER,
                   CTACAR_VENBSF CAR,
                   CARCLI_VENBSF CARCLI
             WHERE     CAR.CODPERCLI = CLI.CODPERCLI
                   AND CARCLI.CODCAR = CAR.CODCAR
                   AND CARCLI.CODPERCLI = PER.CODPER
                   AND PER.PRECIRIF = V_PRECIRIF
                   AND PER.CIRIF = V_CIRIF
                   AND CAR.CODCAR = V_CODCAR
                   AND CAR.STATCAR = 'A';
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               MESSAGE :=
                     'CARTERA '
                  || V_CODCAR
                  || ' NO EXISTENTE PARA EL CLIENTE '
                  || V_PRECIRIF
                  || ' '
                  || V_CIRIF;
               RAISE NO_VALIDO;
            WHEN TOO_MANY_ROWS
            THEN
               NULL;
            WHEN OTHERS
            THEN
               MESSAGE := 'ERROR CONSULTANDO DATOS DE LA CARTERA ' || SQLERRM;
               RAISE NO_VALIDO;
         END;
      END IF;

      IF V_CODASE IS NULL
      THEN
         MESSAGE := 'DEBE SELECCIONAR UN ASESOR VALIDO';
         RAISE NO_VALIDO;
      END IF;

      IF V_CODEJE IS NULL
      THEN
         MESSAGE := 'DEBE SELECCIONAR UN ASESOR VALIDO';
         RAISE NO_VALIDO;
      END IF;

      --Se genera el numero para el tracking de documentos
      SELECT GENERA_NUMERO
        INTO V_GENERA_NUMERO
        FROM FLUJOS_ESTATUS
       WHERE ID_REQ_GRUP = V_ID_REQ_GRUP AND ID_ESTATUS = V_ID_ESTATUS;

      IF V_GENERA_NUMERO = 'S'
      THEN
         BEGIN
            SELECT NUMERO
              INTO V_NUMERO
              FROM FLUJO_DOC
             WHERE ID = V_ID;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               V_NUMERO := NULL;
         END;


         IF V_NUMERO IS NULL OR LENGTH (V_NUMERO) = 0
         THEN
            V_NUMERO := FNC_GET_ULT_NUM_GRU (V_ID_REQ_GRUP);

            UPDATE FLUJO_DOC
               SET NUMERO = V_NUMERO
             WHERE ID = V_ID;


            LIBRO_ORDENES.PR_UPD_GRUPO_NUMERO (TO_CHAR (V_ID_REQ_GRUP),
                                 TO_CHAR (V_NUMERO),
                                 '',
                                 V_USUARIO,
                                 V_IP,
                                 'U');
         END IF;

         IN_NUMERO := V_NUMERO;
      END IF;

      V_FASE := 41;



      IF LENGTH (V_COMISION) = 0 OR V_COMISION IS NULL
      THEN
         V_COMISION :=
            FNC_GET_COMISION (V_MONEDA,
                              V_TIPO_OPERACION,
                              IN_MONTO,
                              V_FECHA_VALOR);
      END IF;

      V_FASE := 42;

      
      INSERT INTO FLUJO_DOC (ID,
                             CODCAR,
                             PRECIRIF,
                             CIRIF,
                             NOMBRE,
                             NOMBSEG,
                             APELLIDO,
                             APELLSEG,
                             APELLCAS,
                             ID_ESTATUS,
                             ID_REQ_GRUP,
                             OBSERVACIONES,
                             FECHA_INC,
                             USUARIO_INC,
                             FECHA_ULT_MOD,
                             USUARIO_ULT_MOD,
                             EST_GRU_DOCS,
                             CODASE,
                             CODEJE,
                             NUMERO,
                             ID_PADRE,
                             ID_ESTATUS_PADRE,
                             FECHA_VALOR,
                             TIPO_OPERACION,
                             FECHA_OPERACION,
                             PRODUCTO,
                             MONEDA,
                             MONTO,
                             COMISION,
                             COND_ESPECIAL,
                             TASA_CAMBIO,
                             NUMERO_CONTROL,
                             CONTRATO_ONLINE,
                             TIPO_CONTRATO_ONLINE,
                             NUM_REF_ONLINE)
           VALUES (V_ID,
                   V_CODCAR,
                   V_PRECIRIF,
                   V_CIRIF,
                   V_NOMBRE,
                   V_NOMBSEG,
                   V_APELLIDO,
                   V_APELLSEG,
                   V_APELLCAS,
                   V_ID_ESTATUS,
                   V_ID_REQ_GRUP,
                   V_OBSERVACIONES,
                   SYSDATE,
                   V_USUARIO,
                   SYSDATE,
                   V_USUARIO,
                   'C',
                   V_CODASE,
                   V_CODEJE,
                   V_NUMERO,
                   '',
                   '',
                   V_FECHA_VALOR,
                   V_TIPO_OPERACION,
                   V_FECHA_OPERACION,
                   V_PRODUCTO,
                   V_MONEDA,
                   V_MONTO,
                   V_COMISION,
                   V_COND_ESPECIAL,
                   V_TASA_CAMBIO,
                   V_NUMERO_CONTROL,
                   V_CONTRATO_ONLINE,
                   'TIPO_CONTRATO',
                   V_NUM_REF_ONLINE);

      --AQUI INSERTA EN FLUJO DOC PADRE
      V_FASE := 50;

      IF LENGTH (V_TIPO_OPERACION) > 0
      THEN
         --sE INSERTAN LOS REQUISITOS DEL DETALLE EN LA PRIMERA FASE DEL FLUJO.
         FOR REG
            IN (SELECT R.ID
                  FROM TIPO_OPERACION_REQUISITO TR, REQUISITO R
                 WHERE     TR.ID_REQ = R.ID
                       AND TR.ID_TIPO_OPERACION = V_TIPO_OPERACION)
         LOOP
            V_CONT := V_CONT + 1;

            INSERT INTO FLUJO_DOC_DET (ID,
                                       ID_ESTATUS,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (V_ID,
                         V_ID_ESTATUS,
                         V_ID_REQ_GRUP,
                         REG.ID,
                         'GENERADOS PARA EL PRIMER PASO',
                         SYSDATE,
                         V_USUARIO,
                         'CR');

            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (FNC_GET_ID_HISTORICO,
                         V_ID,
                         V_ID_ESTATUS,
                         '0',
                         V_ID_REQ_GRUP,
                         REG.ID,
                         'GENERADOS PARA EL PRIMER PASO',
                         SYSDATE,
                         V_USUARIO,
                         'CR');
         END LOOP;

         IF V_CONT = 0
         THEN
            MESSAGE := 'No existen requisitos asociados al Tipo de Operacion';
            RAISE NO_VALIDO;
         END IF;
      ELSE
         --sE INSERTAN LOS REQUISITOS DEL DETALLE EN LA PRIMERA FASE DEL FLUJO.
         FOR REG
            IN (SELECT R.ID
                  FROM GRUPOS_REQUISITOS GR, REQUISITO R, GRUPO_REQUISITO G
                 WHERE     GR.ID_GRUPO = G.ID
                       AND GR.ID_REQ = R.ID
                       AND ID_GRUPO = V_ID_REQ_GRUP)
         LOOP
            INSERT INTO FLUJO_DOC_DET (ID,
                                       ID_ESTATUS,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (V_ID,
                         V_ID_ESTATUS,
                         V_ID_REQ_GRUP,
                         REG.ID,
                         'GENERADOS PARA EL PRIMER PASO',
                         SYSDATE,
                         V_USUARIO,
                         'CR');

            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (FNC_GET_ID_HISTORICO,
                         V_ID,
                         V_ID_ESTATUS,
                         '0',
                         V_ID_REQ_GRUP,
                         REG.ID,
                         'GENERADOS PARA EL PRIMER PASO',
                         SYSDATE,
                         V_USUARIO,
                         'CR');
         END LOOP;
      END IF;

      IN_ID := TO_CHAR (V_ID);
   -- IN_NUMERO := TO_CHAR (V_NUMERO);
   END IF;

   IF V_OPERACION = 'D'
   THEN
      V_FASE := 60;

      IF IN_ID IS NULL
      THEN
         MESSAGE := 'NUMERO DE DOCUMENTO INVALIDO';
         RAISE NO_VALIDO;
      ELSE
         V_ID := IN_ID;
      END IF;

      V_FASE := 70;

      UPDATE FLUJO_DOC D
         SET D.ACTIVO = 'I'
       WHERE ID = V_ID;
   END IF;

   IF V_OPERACION = 'RS'
   THEN
      --RESTAURAR_DATA ();
      NULL;
   END IF;

   IF V_OPERACION = 'LD'
   THEN
      --DELETE      FLUJO_DOC_HIS;

      --DELETE      FLUJO_DOC_DET;

      --DELETE      FLUJO_DOC_AUT;

      --DELETE      FLUJO_DOC;
      NULL;
   END IF;

   IF V_OPERACION = 'UC'
   THEN
      IF V_CODCAR IS NULL
      THEN
         MESSAGE := 'CARTERA INVALIDA';
         RAISE NO_VALIDO;
      END IF;

      V_ID := IN_ID;

      IF V_ID IS NULL
      THEN
         MESSAGE := 'GRUPO INVALIDO';
         RAISE NO_VALIDO;
      END IF;

      --Busqueda del nombre Davidspp: 28/06/2013
      FOR REG IN (SELECT P.NOMBRE,
                         P.NOMBSEG,
                         P.APELLIDO,
                         P.APELLSEG,
                         P.APELLCAS,
                         C.CODCAR
                    FROM CTAPER P, CTACAR C
                   WHERE C.CODCAR = V_CODCAR AND C.CODPERCLI = P.CODPER)
      LOOP
         UPDATE FLUJO_DOC D
            SET D.NOMBRE = REG.NOMBRE,
                D.NOMBSEG = REG.NOMBSEG,
                D.APELLIDO = REG.APELLIDO,
                D.APELLSEG = REG.APELLSEG,
                D.APELLCAS = REG.APELLCAS,
                D.CODCAR = V_CODCAR
          WHERE D.ID = V_ID;
      END LOOP;

      --Busqueda del nombre Davidspp: 28/06/2013fin

      BEGIN
         UPDATE FLUJO_DOC
            SET CODCAR = V_CODCAR
          WHERE ID = V_ID;
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR ACTUALIZANDO CARTERA ' || SQLERRM;
            RAISE NO_VALIDO;
      END;

      --Se guarda en el historico .
      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         ID_ESTATUS,
                         ID_ESTATUS_ANT
                    FROM FLUJO_DOC_DET F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      '',
                      '',
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'CARTERA ASOCIADA:' || V_CODCAR,
                      'N',
                      SYSDATE,
                      V_USUARIO,
                      'CA');
      END LOOP;

      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         F.PRECIRIF,
                         F.CIRIF,
                         F.ID_ESTATUS,
                         F.NOMBRE,
                         F.ID_ESTATUS_ANT
                    FROM FLUJO_DOC_AUT F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      DET.PRECIRIF,
                      DET.CIRIF,
                      DET.NOMBRE,
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'CARTERA ASOCIADA:' || V_CODCAR,
                      'S',
                      SYSDATE,
                      V_USUARIO,
                      'CA');
      END LOOP;
   END IF;

   IF V_OPERACION = 'STA'
   THEN
      IF IN_ID IS NULL
      THEN
         MESSAGE := 'NUMERO DE DOCUMENTO INVALIDO';
         RAISE NO_VALIDO;
      ELSE
         V_ID := IN_ID;
      END IF;

      --Validacion que todos los requisitos se encuentren en el mismo paso
      IF FNC_ES_FLUJO_COM (V_ID) = 'N'
      THEN
         MESSAGE :=
            'TODOS LOS REQUISITOS DEL GRUPO DE DOCUMENTOS DEBEN ENCONTRARSE EN EL MISMO PASO';
         RAISE NO_VALIDO;
      END IF;

      --Si continua es que todos los documentos se encuentran en el mismo estatus asi
      FOR REG
         IN (SELECT E.STANDBY
               FROM FLUJO_DOC D, FLUJOS_ESTATUS E
              WHERE     D.ID = V_ID
                    AND D.ID_REQ_GRUP = E.ID_REQ_GRUP
                    AND D.ID_ESTATUS = E.ID_ESTATUS)
      LOOP
         IF REG.STANDBY = 'N'
         THEN
            MESSAGE :=
               'EN ESTE PASO NO SE PERMITE COLOCAR GRUPOS DE DOCUMENTOS EN STANDBY';
            RAISE NO_VALIDO;
         END IF;
      END LOOP;

      BEGIN
         UPDATE FLUJO_DOC
            SET EST_GRU_DOCS = 'S'
          WHERE ID = V_ID AND EST_GRU_DOCS NOT IN 'S';
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR ACTUALIZANDO ESTATUS ' || SQLERRM;
            RAISE NO_VALIDO;
      END;

      --Se guarda en el historico .
      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         ID_ESTATUS,
                         ID_ESTATUS_ANT
                    FROM FLUJO_DOC_DET F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      '',
                      '',
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'COLOCADO EN STANDBY',
                      'N',
                      SYSDATE,
                      V_USUARIO,
                      'ST');
      END LOOP;

      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         F.PRECIRIF,
                         F.CIRIF,
                         F.ID_ESTATUS,
                         F.NOMBRE,
                         F.ID_ESTATUS_ANT
                    FROM FLUJO_DOC_AUT F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      DET.PRECIRIF,
                      DET.CIRIF,
                      DET.NOMBRE,
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'COLOCADO EN STANDBY',
                      'S',
                      SYSDATE,
                      V_USUARIO,
                      'ST');
      END LOOP;
   END IF;

   IF V_OPERACION = 'DST'
   THEN
      IF IN_ID IS NULL
      THEN
         MESSAGE := 'NUMERO DE DOCUMENTO INVALIDO';
         RAISE NO_VALIDO;
      ELSE
         V_ID := IN_ID;
      END IF;


      --      BEGIN
      --         SELECT DES_MAESTRO
      --           INTO V_EST_GRU_DOCS
      --           FROM FLUJO_DOC
      --          WHERE ID = V_ID AND EST_GRU_DOCS = 'S';
      --      EXCEPTION
      --         WHEN OTHERS
      --         THEN
      --            MESSAGE := 'ERROR CONSULTANDO ESTATUS ' || SQLERRM;
      --            RAISE NO_VALIDO;
      --      END;

      BEGIN
         UPDATE FLUJO_DOC
            SET EST_GRU_DOCS = 'C'
          WHERE ID = V_ID AND EST_GRU_DOCS = 'S';
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR ACTUALIZANDO ESTATUS ' || SQLERRM;
            RAISE NO_VALIDO;
      END;

      --Se guarda en el historico .
      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         ID_ESTATUS,
                         ID_ESTATUS_ANT
                    FROM FLUJO_DOC_DET F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      '',
                      '',
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'ACTIVADO DESDE STANDBY',
                      'N',
                      SYSDATE,
                      V_USUARIO,
                      'DST');
      END LOOP;

      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         F.PRECIRIF,
                         F.CIRIF,
                         F.ID_ESTATUS,
                         F.NOMBRE,
                         F.ID_ESTATUS_ANT
                    FROM FLUJO_DOC_AUT F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      DET.PRECIRIF,
                      DET.CIRIF,
                      DET.NOMBRE,
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'ACTIVADO DESDE STANDBY',
                      'S',
                      SYSDATE,
                      V_USUARIO,
                      'DST');
      END LOOP;
   END IF;

   --   IF V_OPERACION = 'IGF'
   --   THEN
   --      IF V_GUIA_FEDEX IS NULL
   --      THEN
   --         MESSAGE := 'NUMERO DE GUIA FEDEX INVALIDO';
   --         RAISE NO_VALIDO;
   --      END IF;
   --
   --      V_ID := IN_ID;
   --
   --      IF V_ID IS NULL
   --      THEN
   --         MESSAGE := 'GRUPO INVALIDO';
   --         RAISE NO_VALIDO;
   --      END IF;
   --
   --      BEGIN
   --         UPDATE FLUJO_DOC
   --            SET GUIA_FEDEX = V_GUIA_FEDEX
   --          WHERE ID = V_ID;
   --      EXCEPTION
   --         WHEN OTHERS
   --         THEN
   --            MESSAGE := 'ERROR ACTUALIZANDO GUIA FEDEX ' || SQLERRM;
   --            RAISE NO_VALIDO;
   --      END;
   --
   --      --Se guarda en el historico .
   --      FOR DET IN (SELECT F.ID_REQ, F.ID_REQ_GRUP, ID_ESTATUS,
   --                         ID_ESTATUS_ANT
   --                    FROM FLUJO_DOC_DET F
   --                   WHERE ID = V_ID AND F.ACTIVO = 'A')
   --      LOOP
   --         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
   --                                    ID,
   --                                    PRECIRIF,
   --                                    CIRIF,
   --                                    ID_ESTATUS,
   --                                    ID_ESTATUS_ANT,
   --                                    ID_REQ_GRUP,
   --                                    ID_REQ,
   --                                    OBSERVACIONES,
   --                                    CND_REPRESENTANTE,
   --                                    FECHA_INC,
   --                                    USUARIO_INC,
   --                                    TIPO_HIS)
   --              VALUES (FNC_GET_ID_HISTORICO,
   --                      V_ID,
   --                      '',
   --                      '',
   --                      DET.ID_ESTATUS,
   --                      DET.ID_ESTATUS,
   --                      DET.ID_REQ_GRUP,
   --                      DET.ID_REQ,
   --                      'GUIA FEDEX ASOCIADA:' || V_GUIA_FEDEX,
   --                      'N',
   --                      SYSDATE,
   --                      V_USUARIO,
   --                      'FE');
   --      END LOOP;
   --
   --      FOR DET IN (SELECT F.ID_REQ, F.ID_REQ_GRUP, F.PRECIRIF,
   --                         F.CIRIF, F.ID_ESTATUS, F.NOMBRE,
   --                         F.ID_ESTATUS_ANT
   --                    FROM FLUJO_DOC_AUT F
   --                   WHERE ID = V_ID AND F.ACTIVO = 'A')
   --      LOOP
   --         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
   --                                    ID,
   --                                    PRECIRIF,
   --                                    CIRIF,
   --                                    NOMBRE,
   --                                    ID_ESTATUS,
   --                                    ID_ESTATUS_ANT,
   --                                    ID_REQ_GRUP,
   --                                    ID_REQ,
   --                                    OBSERVACIONES,
   --                                    CND_REPRESENTANTE,
   --                                    FECHA_INC,
   --                                    USUARIO_INC,
   --                                    TIPO_HIS)
   --              VALUES (FNC_GET_ID_HISTORICO,
   --                      V_ID,
   --                      DET.PRECIRIF,
   --                      DET.CIRIF,
   --                      DET.NOMBRE,
   --                      DET.ID_ESTATUS,
   --                      DET.ID_ESTATUS,
   --                      DET.ID_REQ_GRUP,
   --                      DET.ID_REQ,
   --                      'GUIA FEDEX ASOCIADA:' || V_GUIA_FEDEX,
   --                      'S',
   --                      SYSDATE,
   --                      V_USUARIO,
   --                      'FE');
   --      END LOOP;
   --   END IF;

   --   IF V_OPERACION = 'UOB'
   --   THEN
   --
   --
   --      V_ID := IN_ID;
   --
   --      IF V_ID IS NULL
   --      THEN
   --         MESSAGE := 'GRUPO INVALIDO';
   --         RAISE NO_VALIDO;
   --      END IF;
   --
   --      BEGIN
   --         UPDATE FLUJO_DOC
   --            SET OBSERVACIONES = V_OBSERVACIONES
   --          WHERE ID = V_ID;
   --      EXCEPTION
   --         WHEN OTHERS
   --         THEN
   --            MESSAGE := 'ERROR ACTUALIZANDO GUIA FEDEX ' || SQLERRM;
   --            RAISE NO_VALIDO;
   --      END;
   --
   --      --Se guarda en el historico .
   --      FOR DET IN (SELECT F.ID_REQ, F.ID_REQ_GRUP, ID_ESTATUS,
   --                         ID_ESTATUS_ANT
   --                    FROM FLUJO_DOC_DET F
   --                   WHERE ID = V_ID AND F.ACTIVO = 'A')
   --      LOOP
   --         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
   --                                    ID,
   --                                    PRECIRIF,
   --                                    CIRIF,
   --                                    ID_ESTATUS,
   --                                    ID_ESTATUS_ANT,
   --                                    ID_REQ_GRUP,
   --                                    ID_REQ,
   --                                    OBSERVACIONES,
   --                                    CND_REPRESENTANTE,
   --                                    FECHA_INC,
   --                                    USUARIO_INC,
   --                                    TIPO_HIS)
   --              VALUES (FNC_GET_ID_HISTORICO,
   --                      V_ID,
   --                      '',
   --                      '',
   --                      DET.ID_ESTATUS,
   --                      DET.ID_ESTATUS,
   --                      DET.ID_REQ_GRUP,
   --                      DET.ID_REQ,
   --                      'OBSERVACION ACTUALIZADA:' || V_OBSERVACIONES,
   --                      'N',
   --                      SYSDATE,
   --                      V_USUARIO,
   --                      'OB');
   --      END LOOP;
   --
   --      FOR DET IN (SELECT F.ID_REQ, F.ID_REQ_GRUP, F.PRECIRIF,
   --                         F.CIRIF, F.ID_ESTATUS, F.NOMBRE,
   --                         F.ID_ESTATUS_ANT
   --                    FROM FLUJO_DOC_AUT F
   --                   WHERE ID = V_ID AND F.ACTIVO = 'A')
   --      LOOP
   --         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
   --                                    ID,
   --                                    PRECIRIF,
   --                                    CIRIF,
   --                                    NOMBRE,
   --                                    ID_ESTATUS,
   --                                    ID_ESTATUS_ANT,
   --                                    ID_REQ_GRUP,
   --                                    ID_REQ,
   --                                    OBSERVACIONES,
   --                                    CND_REPRESENTANTE,
   --                                    FECHA_INC,
   --                                    USUARIO_INC,
   --                                    TIPO_HIS)
   --              VALUES (FNC_GET_ID_HISTORICO,
   --                      V_ID,
   --                      DET.PRECIRIF,
   --                      DET.CIRIF,
   --                      DET.NOMBRE,
   --                      DET.ID_ESTATUS,
   --                      DET.ID_ESTATUS,
   --                      DET.ID_REQ_GRUP,
   --                      DET.ID_REQ,
   --                      'OBSERVACION ACTUALIZADA:' || V_OBSERVACIONES,
   --                      'S',
   --                      SYSDATE,
   --                      V_USUARIO,
   --                      'OB');
   --      END LOOP;
   --   END IF;

   IF V_OPERACION = 'UP'
   THEN
      V_ID := IN_ID;

      IF V_ID IS NULL
      THEN
         MESSAGE := 'GRUPO INVALIDO';
         RAISE NO_VALIDO;
      END IF;

      V_CAMBIO := TRUE;

      FOR REG
         IN (SELECT OBSERVACIONES
               FROM FLUJO_DOC
              WHERE     ID = V_ID
                    AND NVL (OBSERVACIONES, '@') = NVL (V_OBSERVACIONES, '@'))
      LOOP
         V_CAMBIO := FALSE;
      END LOOP;

      IF V_CAMBIO
      THEN
         BEGIN
            UPDATE FLUJO_DOC
               SET OBSERVACIONES = V_OBSERVACIONES
             WHERE ID = V_ID;
         EXCEPTION
            WHEN OTHERS
            THEN
               MESSAGE := 'ERROR ACTUALIZANDO GUIA FEDEX ' || SQLERRM;
               RAISE NO_VALIDO;
         END;

         --Se guarda en el historico .
         FOR DET IN (SELECT F.ID_REQ,
                            F.ID_REQ_GRUP,
                            ID_ESTATUS,
                            ID_ESTATUS_ANT
                       FROM FLUJO_DOC_DET F
                      WHERE ID = V_ID AND F.ACTIVO = 'A')
         LOOP
            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       PRECIRIF,
                                       CIRIF,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       CND_REPRESENTANTE,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (FNC_GET_ID_HISTORICO,
                         V_ID,
                         '',
                         '',
                         DET.ID_ESTATUS,
                         DET.ID_ESTATUS,
                         DET.ID_REQ_GRUP,
                         DET.ID_REQ,
                         'OBSERVACION ACTUALIZADA:' || V_OBSERVACIONES,
                         'N',
                         SYSDATE,
                         V_USUARIO,
                         'OB');
         END LOOP;

         FOR DET IN (SELECT F.ID_REQ,
                            F.ID_REQ_GRUP,
                            F.PRECIRIF,
                            F.CIRIF,
                            F.ID_ESTATUS,
                            F.NOMBRE,
                            F.ID_ESTATUS_ANT
                       FROM FLUJO_DOC_AUT F
                      WHERE ID = V_ID AND F.ACTIVO = 'A')
         LOOP
            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       PRECIRIF,
                                       CIRIF,
                                       NOMBRE,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       CND_REPRESENTANTE,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (FNC_GET_ID_HISTORICO,
                         V_ID,
                         DET.PRECIRIF,
                         DET.CIRIF,
                         DET.NOMBRE,
                         DET.ID_ESTATUS,
                         DET.ID_ESTATUS,
                         DET.ID_REQ_GRUP,
                         DET.ID_REQ,
                         'OBSERVACION ACTUALIZADA:' || V_OBSERVACIONES,
                         'S',
                         SYSDATE,
                         V_USUARIO,
                         'OB');
         END LOOP;
      END IF;

      V_CAMBIO := TRUE;

      FOR REG
         IN (SELECT GUIA_FEDEX
               FROM FLUJO_DOC
              WHERE     ID = V_ID
                    AND NVL (GUIA_FEDEX, '@') IN NVL (V_GUIA_FEDEX, '@'))
      LOOP
         V_CAMBIO := FALSE;
      END LOOP;

      IF V_CAMBIO
      THEN
         BEGIN
            UPDATE FLUJO_DOC
               SET GUIA_FEDEX = V_GUIA_FEDEX
             WHERE ID = V_ID;
         EXCEPTION
            WHEN OTHERS
            THEN
               MESSAGE := 'ERROR ACTUALIZANDO GUIA FEDEX ' || SQLERRM;
               RAISE NO_VALIDO;
         END;

         --Se guarda en el historico .
         FOR DET IN (SELECT F.ID_REQ,
                            F.ID_REQ_GRUP,
                            ID_ESTATUS,
                            ID_ESTATUS_ANT
                       FROM FLUJO_DOC_DET F
                      WHERE ID = V_ID AND F.ACTIVO = 'A')
         LOOP
            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       PRECIRIF,
                                       CIRIF,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       CND_REPRESENTANTE,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (FNC_GET_ID_HISTORICO,
                         V_ID,
                         '',
                         '',
                         DET.ID_ESTATUS,
                         DET.ID_ESTATUS,
                         DET.ID_REQ_GRUP,
                         DET.ID_REQ,
                         'GUIA FEDEX ASOCIADA:' || V_GUIA_FEDEX,
                         'N',
                         SYSDATE,
                         V_USUARIO,
                         'FE');
         END LOOP;

         FOR DET IN (SELECT F.ID_REQ,
                            F.ID_REQ_GRUP,
                            F.PRECIRIF,
                            F.CIRIF,
                            F.ID_ESTATUS,
                            F.NOMBRE,
                            F.ID_ESTATUS_ANT
                       FROM FLUJO_DOC_AUT F
                      WHERE ID = V_ID AND F.ACTIVO = 'A')
         LOOP
            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       PRECIRIF,
                                       CIRIF,
                                       NOMBRE,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       CND_REPRESENTANTE,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (FNC_GET_ID_HISTORICO,
                         V_ID,
                         DET.PRECIRIF,
                         DET.CIRIF,
                         DET.NOMBRE,
                         DET.ID_ESTATUS,
                         DET.ID_ESTATUS,
                         DET.ID_REQ_GRUP,
                         DET.ID_REQ,
                         'GUIA FEDEX ASOCIADA:' || V_GUIA_FEDEX,
                         'S',
                         SYSDATE,
                         V_USUARIO,
                         'FE');
         END LOOP;
      END IF;

      V_CAMBIO := TRUE;

      FOR REG
         IN (SELECT CONTRATO_ONLINE
               FROM FLUJO_DOC
              WHERE     ID = V_ID
                    AND NVL (CONTRATO_ONLINE, '@') IN
                           NVL (V_CONTRATO_ONLINE, '@'))
      LOOP
         V_CAMBIO := FALSE;
      END LOOP;

      IF V_CAMBIO
      THEN
         BEGIN
            UPDATE FLUJO_DOC
               SET OBSERVACIONES = V_OBSERVACIONES,
                   GUIA_FEDEX = V_GUIA_FEDEX,
                   CONTRATO_ONLINE = V_CONTRATO_ONLINE,
                   TIPO_CONTRATO_ONLINE = 'TIPO_CONTRATO'
             WHERE ID = V_ID;
         EXCEPTION
            WHEN OTHERS
            THEN
               MESSAGE := 'ERROR ACTUALIZANDO EL TIPO DE CONTRATO ' || SQLERRM;
               RAISE NO_VALIDO;
         END;

         --Se guarda en el historico .
         FOR DET IN (SELECT F.ID_REQ,
                            F.ID_REQ_GRUP,
                            ID_ESTATUS,
                            ID_ESTATUS_ANT
                       FROM FLUJO_DOC_DET F
                      WHERE ID = V_ID AND F.ACTIVO = 'A')
         LOOP
            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       PRECIRIF,
                                       CIRIF,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       CND_REPRESENTANTE,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (
                           FNC_GET_ID_HISTORICO,
                           V_ID,
                           '',
                           '',
                           DET.ID_ESTATUS,
                           DET.ID_ESTATUS,
                           DET.ID_REQ_GRUP,
                           DET.ID_REQ,
                              'TIPO DE CONTRATO ONLINE ASOCIADO:'
                           || V_CONTRATO_ONLINE,
                           'N',
                           SYSDATE,
                           V_USUARIO,
                           'CW');
         END LOOP;

         FOR DET IN (SELECT F.ID_REQ,
                            F.ID_REQ_GRUP,
                            F.PRECIRIF,
                            F.CIRIF,
                            F.ID_ESTATUS,
                            F.NOMBRE,
                            F.ID_ESTATUS_ANT
                       FROM FLUJO_DOC_AUT F
                      WHERE ID = V_ID AND F.ACTIVO = 'A')
         LOOP
            INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                       ID,
                                       PRECIRIF,
                                       CIRIF,
                                       NOMBRE,
                                       ID_ESTATUS,
                                       ID_ESTATUS_ANT,
                                       ID_REQ_GRUP,
                                       ID_REQ,
                                       OBSERVACIONES,
                                       CND_REPRESENTANTE,
                                       FECHA_INC,
                                       USUARIO_INC,
                                       TIPO_HIS)
                 VALUES (
                           FNC_GET_ID_HISTORICO,
                           V_ID,
                           DET.PRECIRIF,
                           DET.CIRIF,
                           DET.NOMBRE,
                           DET.ID_ESTATUS,
                           DET.ID_ESTATUS,
                           DET.ID_REQ_GRUP,
                           DET.ID_REQ,
                              'TIPO DE CONTRATO ONLINE ASOCIADO:'
                           || V_CONTRATO_ONLINE,
                           'S',
                           SYSDATE,
                           V_USUARIO,
                           'CW');
         END LOOP;
      END IF;
   END IF;

   IF V_OPERACION = 'UCR'
   THEN
      IF V_PRECIRIF IS NULL OR V_CIRIF IS NULL
      THEN
         MESSAGE := 'CEDULA O RIF INVALIDOS';
         RAISE NO_VALIDO;
      END IF;

      --Validacion de que la cedula pertenezca al grupo de requisitos
      BEGIN
         --         SELECT '1'
         --           INTO V_EXISTE
         --           FROM GRUPO_REQUISITO G,
         --                (SELECT DECODE (TIPO_CODIGO,  'NATURAL', 'N',  'JURIDICO', 'J') TIPO
         --                   FROM CODIGOS_INTERNOS
         --                  WHERE     CODIGO = V_PRECIRIF
         --                        AND TIPO_CODIGO IN ('NATURAL', 'JURIDICO')
         --                        AND STATUS = 'A') H
         --          WHERE     PRE_CIRIF IN (H.TIPO)
         --                AND CND_REPRESENTANTE = 'N'
         --                AND ID = V_ID_REQ_GRUP;

         SELECT '1'
           INTO V_EXISTE
           FROM GRUPO_REQUISITO G,
                (SELECT DECODE (TIPO_CODIGO,
                                'NATURAL', 'N',
                                'JURIDICO', 'J')
                           TIPO
                   FROM CODIGOS_INTERNOS
                  WHERE     CODIGO = NVL (V_PRECIRIF, CODIGO)
                        AND TIPO_CODIGO IN ('NATURAL', 'JURIDICO')
                        AND STATUS = 'A'
                 UNION
                 SELECT 'A' FROM DUAL) H
          WHERE     PRE_CIRIF IN (H.TIPO)
                AND CND_REPRESENTANTE = 'N'
                AND G.ACTIVO = 'A'
                AND G.ID = V_ID_REQ_GRUP;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            MESSAGE := 'LA CEDULA/RIF NO PERTENECE A ESTE GRUPO DE REQUISITOS';
            RAISE NO_VALIDO;
      END;


      V_ID := IN_ID;


      BEGIN
         UPDATE FLUJO_DOC
            SET PRECIRIF = V_PRECIRIF, CIRIF = V_CIRIF
          WHERE ID = V_ID;
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR CEDULA/RIF' || SQLERRM;
            RAISE NO_VALIDO;
      END;

      --Inclusion del nombre de Personas en el caso que existiera   Davidspp: 07/06/2013 .
      FOR REG
         IN (  SELECT UPPER (PE.APELLIDO) APELLIDO,
                      UPPER (PE.APELLSEG) APELLSEG,
                      UPPER (PE.APELLCAS) APELLCAS,
                      UPPER (PE.NOMBRE) NOMBRE,
                      UPPER (PE.NOMBSEG) NOMBSEG
                 FROM CTACLI_VENBSF CL,
                      CTAPER_VENBSF PE,
                      CARCLI_VENBSF CACLI,
                      CTACAR_VENBSF CA
                WHERE     CL.CODPERCLI = PE.CODPER
                      AND CL.CODPERCLI = CACLI.CODPERCLI
                      AND CA.CODCAR = CACLI.CODCAR
                      AND CA.STATCAR = 'A'
                      AND UPPER (PE.PRECIRIF) = V_PRECIRIF
                      AND UPPER (PE.CIRIF) = V_CIRIF
                      AND ROWNUM = 1
             ORDER BY FLGPRI DESC)
      LOOP
         --MESSAGE := 'PRECIRIF:' || V_PRECIRIF ||' CIRIF:' || V_CIRIF ||' NOMBRE:' || REG.NOMBRE ||' APELLIDO:' || REG.APELLIDO;
         --RAISE NO_VALIDO;
         UPDATE FLUJO_DOC D
            SET D.NOMBRE = REG.NOMBRE,
                D.NOMBSEG = REG.NOMBSEG,
                D.APELLIDO = REG.APELLIDO,
                D.APELLSEG = REG.APELLSEG,
                D.APELLCAS = REG.APELLCAS
          WHERE ID = V_ID;
      END LOOP;

      -- Davidspp: 07/06/2013

      --Se guarda en el historico .
      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         ID_ESTATUS,
                         ID_ESTATUS_ANT
                    FROM FLUJO_DOC_DET F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      '',
                      '',
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS_ANT,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'CI/RIF ACTUALIZADO:' || V_PRECIRIF || ' ' || V_CIRIF,
                      'N',
                      SYSDATE,
                      V_USUARIO,
                      'CI');
      END LOOP;

      FOR DET IN (SELECT F.ID_REQ,
                         F.ID_REQ_GRUP,
                         F.PRECIRIF,
                         F.CIRIF,
                         F.ID_ESTATUS,
                         F.NOMBRE,
                         F.ID_ESTATUS_ANT
                    FROM FLUJO_DOC_AUT F
                   WHERE ID = V_ID AND F.ACTIVO = 'A')
      LOOP
         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      DET.PRECIRIF,
                      DET.CIRIF,
                      DET.NOMBRE,
                      DET.ID_ESTATUS,
                      DET.ID_ESTATUS_ANT,
                      DET.ID_REQ_GRUP,
                      DET.ID_REQ,
                      'CI/RIF ACTUALIZADO:' || V_PRECIRIF || ' ' || V_CIRIF,
                      'S',
                      SYSDATE,
                      V_USUARIO,
                      'CI');
      END LOOP;
   END IF;

   --Para insercion de un subflujo.
   IF V_OPERACION = 'ISF'
   THEN
      --MESSAGE := 'ENTRO A ISF ID:' || IN_ID || ' ID_REQ_GRUP:' || V_ID_REQ_GRUP;
      --RAISE NO_VALIDO;
      V_FASE := 20;

      --Busqueda del siguiente valor en la tabla de documentos.
      SELECT NVL (MAX (ID), 0) + 1 INTO V_ID FROM FLUJO_DOC;


      V_FASE := 30;

      --Nombre del grupo de requisito
      BEGIN
         SELECT NOMBRE
           INTO V_NM_REQ_GRUP
           FROM GRUPO_REQUISITO
          WHERE ID = V_ID_REQ_GRUP;
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'GRUPO DE REQUISITOS INVALIDO';
            RAISE NO_VALIDO;
      END;

      --Busqueda del minimo valor de estaus para el nuevo flujo con el grupo corresponiente.
      BEGIN
         SELECT F.ID_ESTATUS
           INTO V_ID_ESTATUS
           FROM FLUJOS_ESTATUS F,
                ESTATUS E,
                (SELECT FE.ID_REQ_GRUP, ID_ESTATUS
                   FROM (SELECT MIN (F.NUMERO_PASO) NUMERO_PASO
                           FROM FLUJOS_ESTATUS F
                          WHERE F.ID_REQ_GRUP = V_ID_REQ_GRUP) F,
                        FLUJOS_ESTATUS FE
                  WHERE     FE.ID_REQ_GRUP = V_ID_REQ_GRUP
                        AND FE.NUMERO_PASO = F.NUMERO_PASO) PASO
          WHERE     F.ID_REQ_GRUP = PASO.ID_REQ_GRUP
                AND F.ID_ESTATUS = PASO.ID_ESTATUS
                AND F.ID_ESTATUS = E.ID;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            MESSAGE :=
                  'DEBE DEFINIR EL FLUJO PARA EL GRUPO DE DOCUMENTOS '
               || V_NM_REQ_GRUP;
            RAISE NO_VALIDO;
      END;

      --Se genera el numero para el tracking de documentos
      SELECT GENERA_NUMERO
        INTO V_GENERA_NUMERO
        FROM FLUJOS_ESTATUS
       WHERE ID_REQ_GRUP = V_ID_REQ_GRUP AND ID_ESTATUS = V_ID_ESTATUS;

      IF V_GENERA_NUMERO = 'S'
      THEN
         BEGIN
            SELECT NUMERO
              INTO V_NUMERO
              FROM FLUJO_DOC
             WHERE ID = V_ID;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               V_NUMERO := NULL;
         END;


         IF V_NUMERO IS NULL OR LENGTH (V_NUMERO) = 0
         THEN
            V_NUMERO := FNC_GET_ULT_NUM_GRU (V_ID_REQ_GRUP);

            UPDATE FLUJO_DOC
               SET NUMERO = V_NUMERO
             WHERE ID = V_ID;


            PR_UPD_GRUPO_NUMERO (TO_CHAR (V_ID_REQ_GRUP),
                                 TO_CHAR (V_NUMERO),
                                 '',
                                 V_USUARIO,
                                 V_IP,
                                 'U');
         END IF;
      END IF;

      --
      V_FASE := 40;

      --Davidspp 21/06/2013 Verificacion de Flujos existentes
      BEGIN
             SELECT COUNT (F.ID)
               INTO V_EXISTE
               FROM FLUJO_DOC F,
                    GRUPO_REQUISITO G,
                    (    SELECT CONNECT_BY_ROOT (ID) IDPAD
                           FROM FLUJO_DOC D,
                                (    SELECT MAX (LEVEL) NIVEL
                                       FROM FLUJO_DOC
                                      WHERE ID = TO_NUMBER (IN_ID)
                                 CONNECT BY PRIOR ID = ID_PADRE) MAX
                          WHERE D.ID = TO_NUMBER (IN_ID) AND LEVEL = MAX.NIVEL
                     CONNECT BY PRIOR D.ID = D.ID_PADRE) IDPAD
              WHERE     F.ID_REQ_GRUP = G.ID
                    AND F.ACTIVO = 'A'
                    AND F.ID_REQ_GRUP = V_ID_REQ_GRUP
         START WITH F.ID = IDPAD.IDPAD
         CONNECT BY PRIOR F.ID = F.ID_PADRE;

         IF V_EXISTE > 0
         THEN
            MESSAGE :=
               'YA EXISTE UN GRUPO DE DOCUMENTOS DE ESTE TRACKING ASOCIADO AL FLUJO';
            RAISE NO_VALIDO;
         END IF;
      END;

      --Davidspp 21/06/2013 Verificacion de Flujos existentes: fin


      FOR REG IN (SELECT ID,
                         CODCAR,
                         PRECIRIF,
                         CIRIF,
                         NOMBRE,
                         ID_ESTATUS,
                         ID_REQ_GRUP,
                         OBSERVACIONES,
                         FECHA_INC,
                         USUARIO_INC,
                         FECHA_ULT_MOD,
                         USUARIO_ULT_MOD,
                         NOMBSEG,
                         APELLIDO,
                         APELLSEG,
                         APELLCAS,
                         EST_GRU_DOCS,
                         TIPO_CODIGO,
                         DES_MAESTRO,
                         GUIA_FEDEX,
                         CODEJE,
                         CODASE,
                         ULT_OBSERVACION,
                         NUMERO,
                         ACTIVO,
                         TIPO_ACTIVO,
                         ID_PADRE,
                         ID_ESTATUS_PADRE,
                         FECHA_VALOR,
                         TIPO_OPERACION,
                         FECHA_OPERACION,
                         PRODUCTO,
                         MONEDA,
                         MONTO,
                         COMISION,
                         COND_ESPECIAL,
                         TASA_CAMBIO,
                         NUMERO_CONTROL
                    FROM FLUJO_DOC
                   WHERE ID = TO_NUMBER (IN_ID))
      LOOP
         --Davidspp 25/06/2013 Verificacion que el flujo se cree en donde este parametrizado
         BEGIN
            V_EXISTE := 0;

            --Se busca si el flujo tiene parametrizado en el paso que se tiene que crear subflujo,
            SELECT COUNT (FE.ID_REQ_GRUP)
              INTO V_EXISTE
              FROM FLUJOS_ESTATUS FE, FLUJO_DOC F
             WHERE     F.ID = REG.ID
                   AND FE.ID_REQ_GRUP = F.ID_REQ_GRUP
                   AND FE.ID_ESTATUS = REG.ID_ESTATUS
                   AND SUBFLUJO = 'S';

            IF V_EXISTE <= 0
            THEN
               MESSAGE :=
                  'NO SE PUEDEN CREAR SUBFLUJOS EN ESTE PASO PARA ESTE GRUPO DE REQUISITOS';
               RAISE NO_VALIDO;
            END IF;
         END;

         --Davidspp 25/06/2013 Verificacion que el flujo se cree en donde este parametrizado: fin
         INSERT INTO FLUJO_DOC (ID,
                                CODCAR,
                                PRECIRIF,
                                CIRIF,
                                NOMBRE,
                                NOMBSEG,
                                APELLIDO,
                                APELLSEG,
                                APELLCAS,
                                ID_ESTATUS,
                                ID_REQ_GRUP,
                                OBSERVACIONES,
                                FECHA_INC,
                                USUARIO_INC,
                                FECHA_ULT_MOD,
                                USUARIO_ULT_MOD,
                                EST_GRU_DOCS,
                                CODASE,
                                CODEJE,
                                NUMERO,
                                ID_PADRE,
                                ID_ESTATUS_PADRE,
                                FECHA_VALOR,
                                TIPO_OPERACION,
                                FECHA_OPERACION,
                                PRODUCTO,
                                MONEDA,
                                MONTO,
                                COMISION,
                                COND_ESPECIAL,
                                TASA_CAMBIO,
                                NUMERO_CONTROL)
              VALUES (V_ID,
                      REG.CODCAR,
                      REG.PRECIRIF,
                      REG.CIRIF,
                      REG.NOMBRE,
                      REG.NOMBSEG,
                      REG.APELLIDO,
                      REG.APELLSEG,
                      REG.APELLCAS,
                      V_ID_ESTATUS,
                      V_ID_REQ_GRUP,
                      V_OBSERVACIONES,
                      SYSDATE,
                      V_USUARIO,
                      SYSDATE,
                      V_USUARIO,
                      'C',
                      REG.CODASE,
                      REG.CODEJE,
                      V_NUMERO,
                      REG.ID,
                      REG.ID_ESTATUS,
                      REG.FECHA_VALOR,
                      REG.TIPO_OPERACION,
                      REG.FECHA_OPERACION,
                      REG.PRODUCTO,
                      REG.MONEDA,
                      REG.MONTO,
                      REG.COMISION,
                      REG.COND_ESPECIAL,
                      REG.TASA_CAMBIO,
                      REG.NUMERO_CONTROL);

             --      ********** Insercion den flujo doc padre para las busquedas    ********     --
             SELECT MAX (LEVEL) NIVEL
               INTO V_PAD_NIVEL
               FROM FLUJO_DOC
              WHERE ID = V_ID
         CONNECT BY PRIOR ID = ID_PADRE;

             SELECT CONNECT_BY_ROOT (ID) IDPAD
               INTO V_PAD_IDPAD
               FROM FLUJO_DOC D
              WHERE D.ID = V_ID AND LEVEL = V_PAD_NIVEL
         CONNECT BY PRIOR D.ID = D.ID_PADRE;


             --Numero de flujos que tiene el tracking
             SELECT COUNT (F.ID)
               INTO V_PAD_NUMFLUJOS
               FROM FLUJO_DOC F, GRUPO_REQUISITO G
              WHERE F.ID_REQ_GRUP = G.ID AND F.ACTIVO = 'A'
         START WITH F.ID = V_PAD_IDPAD
         CONNECT BY PRIOR F.ID = F.ID_PADRE;

             SELECT SUBSTR (SYS_CONNECT_BY_PATH (NUMERO, ' - '), 4)
               INTO V_PAD_NUMERO
               FROM FLUJO_DOC D
              WHERE D.ID = V_ID AND LEVEL = V_PAD_NIVEL
         CONNECT BY PRIOR D.ID = D.ID_PADRE;

         INSERT INTO FLUJO_DOC_PADRE (ID,
                                      ID_PADRE,
                                      NUMERO_TOTAL,
                                      NUMERO_FLUJOS,
                                      NIVEL)
              VALUES (V_ID,
                      V_PAD_IDPAD,
                      V_PAD_NUMERO,
                      V_PAD_NUMFLUJOS,
                      V_PAD_NIVEL);
      --Fin: ********** Insercion den flujo doc padre para las busquedas    ******** Fin: --

      END LOOP;

      V_FASE := 50;

      --sE INSERTAN LOS REQUISITOS DEL DETALLE EN LA PRIMERA FASE DEL FLUJO.
      FOR DET IN (SELECT ID,
                         ID_ESTATUS,
                         ID_REQ_GRUP,
                         ID_REQ,
                         OBSERVACIONES,
                         FECHA_INC,
                         USUARIO_INC,
                         ID_ESTATUS_ANT,
                         ACTIVO,
                         TIPO_CODIGO,
                         TIPO_HIS,
                         TIPO_CODIGO_HIS,
                         TIPO_HIS_BLOQUEO
                    FROM FLUJO_DOC_DET
                   WHERE ID = TO_NUMBER (IN_ID))
      LOOP
         INSERT INTO FLUJO_DOC_DET (ID,
                                    ID_ESTATUS,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    ID_ESTATUS_ANT,
                                    ACTIVO,
                                    TIPO_HIS)
              VALUES (V_ID,
                      V_ID_ESTATUS,
                      V_ID_REQ_GRUP,
                      DET.ID_REQ,
                      'GENERADOS PARA EL PRIMER PASO',
                      SYSDATE,
                      V_USUARIO,
                      0,
                      DET.ACTIVO,
                      'CR');

         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      '',
                      '',
                      '',
                      V_ID_ESTATUS,
                      0,
                      V_ID_REQ_GRUP,
                      DET.ID_REQ,
                      'GENERADOS PARA EL PRIMER PASO:' || V_OBSERVACIONES,
                      'N',
                      SYSDATE,
                      V_USUARIO,
                      'CR');
      END LOOP;

      FOR AUT IN (SELECT ID,
                         PRECIRIF,
                         CIRIF,
                         NOMBRE,
                         ID_ESTATUS,
                         ID_REQ_GRUP,
                         ID_REQ,
                         OBSERVACIONES,
                         FECHA_INC,
                         USUARIO_INC,
                         ID_ESTATUS_ANT,
                         DESCRIPCION,
                         ACTIVO,
                         TIPO_CODIGO,
                         TIPO_HIS,
                         TIPO_CODIGO_HIS,
                         ID_REQ_GRUP_AUT,
                         TIPO_HIS_BLOQUEO
                    FROM FLUJO_DOC_AUT
                   WHERE ID = TO_NUMBER (IN_ID))
      LOOP
         INSERT INTO FLUJO_DOC_AUT (ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    ID_ESTATUS_ANT,
                                    DESCRIPCION,
                                    ACTIVO,
                                    TIPO_HIS,
                                    ID_REQ_GRUP_AUT)
              VALUES (V_ID,
                      AUT.PRECIRIF,
                      AUT.CIRIF,
                      AUT.NOMBRE,
                      V_ID_ESTATUS,
                      V_ID_REQ_GRUP,
                      AUT.ID_REQ,
                      'GENERADOS PARA EL PRIMER PASO',
                      SYSDATE,
                      V_USUARIO,
                      0,
                      AUT.DESCRIPCION,
                      AUT.ACTIVO,
                      'CR',
                      AUT.ID_REQ_GRUP_AUT);


         INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                    ID,
                                    PRECIRIF,
                                    CIRIF,
                                    NOMBRE,
                                    ID_ESTATUS,
                                    ID_ESTATUS_ANT,
                                    ID_REQ_GRUP,
                                    ID_REQ,
                                    OBSERVACIONES,
                                    CND_REPRESENTANTE,
                                    FECHA_INC,
                                    USUARIO_INC,
                                    TIPO_HIS)
              VALUES (FNC_GET_ID_HISTORICO,
                      V_ID,
                      AUT.PRECIRIF,
                      AUT.CIRIF,
                      AUT.NOMBRE,
                      AUT.ID_ESTATUS,
                      0,
                      V_ID_REQ_GRUP,
                      AUT.ID_REQ,
                      'GENERADOS PARA EL PRIMER PASO:' || V_OBSERVACIONES,
                      'S',
                      SYSDATE,
                      V_USUARIO,
                      'CR');
      END LOOP;
   END IF;


   --++++++++++++++++++++++++++++++++++++++++++++++++++++

   --Para MODIFICACION DEL FLUJO.
   IF V_OPERACION = 'UPD'
   THEN
      V_FASE := 60;

      FOR REG IN (SELECT FC.ID_ESTATUS,
                         PRODUCTO,
                         FECHA_VALOR,
                         FECHA_OPERACION,
                         COMISION,
                         COND_ESPECIAL,
                         TASA_CAMBIO,
                         NUMERO,
                         NUMERO_CONTROL,
                         MONTO,
                         MONEDA,
                         CIP.DESCRIPCION DESC_PRODUCTO,
                         CIV.DESCRIPCION DESC_FECHA_VALOR,
                         CIC.DESCRIPCION DESC_COND_ESPECIAL
                    FROM FLUJO_DOC FC
                         LEFT JOIN CODIGOS_INTERNOS CIP
                            ON     FC.PRODUCTO = CIP.CODIGO
                               AND FC.TIPO_PRODUCTO = CIP.TIPO_CODIGO
                               AND CIP.STATUS = 'A'
                         LEFT JOIN CODIGOS_INTERNOS CIV
                            ON     FC.FECHA_VALOR = CIV.CODIGO
                               AND FC.TIPO_FECHA_VALOR = CIV.TIPO_CODIGO
                               AND CIV.STATUS = 'A'
                         LEFT JOIN CODIGOS_INTERNOS CIC
                            ON     FC.COND_ESPECIAL = CIC.CODIGO
                               AND FC.TIPO_COND_ESPECIAL = CIC.TIPO_CODIGO
                               AND CIC.STATUS = 'A'
                   WHERE ID = V_ID)
      LOOP
         --Davidspp: 21/06/2013 Validacion de Usuario del paso Inicio
         BEGIN
            SELECT COUNT (D.ID)
              INTO V_EXISTE
              FROM FLUJO_ENC E, FLUJO_DOC D
             WHERE     E.ID_REQ_GRUP = D.ID_REQ_GRUP
                   --AND E.ID_ESTATUS = D.ID_ESTATUS
                   AND E.ID_ESTATUS = REG.ID_ESTATUS
                   AND E.LOGIN = V_USUARIO
                   AND D.ID = V_ID;

            IF V_EXISTE <= 0
            THEN
               MESSAGE :=
                     'NO TIENE PERMISO PARA MODIFICAR DOCUMENTOS DE ESTE PASO DEL FLUJO PARA EL TRACKING '
                  || REG.NUMERO;
               RAISE NO_VALIDO;
            END IF;
         END;

         --Davidspp: 21/06/2013 Validacion de Usuario del paso fin
         V_DESCRIPCION := '';

         IF NVL (V_PRODUCTO, '@') != NVL (REG.PRODUCTO, '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ' TIPO_PRODUCTO= ' || REG.DESC_PRODUCTO;
         END IF;

         IF NVL (V_FECHA_VALOR, '@') != NVL (REG.FECHA_VALOR, '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ' FECHA_VALOR= ' || REG.DESC_FECHA_VALOR;
         END IF;

         IF NVL (TO_CHAR (V_FECHA_OPERACION), '@') !=
               NVL (TO_CHAR (REG.FECHA_OPERACION), '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ', FECHA_OPERACION= ' || V_FECHA_OPERACION;
         END IF;

         IF NVL (TO_CHAR (V_COMISION), '@') !=
               NVL (TO_CHAR (REG.COMISION), '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION := V_DESCRIPCION || ' COMISION= ' || V_COMISION;
         END IF;

         IF NVL (TO_CHAR (V_MONTO), '@') != NVL (TO_CHAR (REG.MONTO), '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION := V_DESCRIPCION || ' MONTO= ' || V_MONTO;
         END IF;

         IF NVL (V_MONEDA, '@') != NVL (REG.MONEDA, '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION := V_DESCRIPCION || ' MONEDA= ' || REG.MONEDA;
         END IF;

         IF NVL (V_COND_ESPECIAL, '@') != NVL (REG.COND_ESPECIAL, '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ' COND_ESPECIAL= ' || REG.DESC_COND_ESPECIAL;
         END IF;

         IF NVL (TO_CHAR (V_TASA_CAMBIO), '@') !=
               NVL (TO_CHAR (REG.TASA_CAMBIO), '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ' TASA_CAMBIO= ' || TO_CHAR (V_TASA_CAMBIO);
         END IF;



         IF NVL (TO_CHAR (V_NUMERO), '@') != NVL (TO_CHAR (REG.NUMERO), '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ' NUMERO= ' || TO_CHAR (V_NUMERO);
         END IF;

         IF NVL (V_NUMERO_CONTROL, '@') != NVL (REG.NUMERO_CONTROL, '@')
         THEN
            IF LENGTH (V_DESCRIPCION) >= 0
            THEN
               V_DESCRIPCION := V_DESCRIPCION || ',';
            END IF;

            V_DESCRIPCION :=
               V_DESCRIPCION || ' NUMERO_CONTROL= ' || V_NUMERO_CONTROL;
         END IF;

         FOR REG
            IN (SELECT ID
                  FROM FLUJO_DOC FC
                 WHERE     FECHA_OPERACION = V_FECHA_OPERACION
                       AND ID_REQ_GRUP = V_ID_REQ_GRUP
                       AND ACTIVO = 'A'
                       AND NUMERO = V_NUMERO
                       AND ID NOT IN V_ID)
         LOOP
            MESSAGE :=
                  'EL NUMERO '
               || V_NUMERO
               || ' YA FUE ASIGNADO A OTRO FLUJO CON FECHA DE OPERACION '
               || TO_CHAR (V_FECHA_OPERACION, 'DD/MM/YYYY');
            RAISE NO_VALIDO;
         END LOOP;

         IF LENGTH (V_DESCRIPCION) >= 0
         THEN
            V_DESCRIPCION :=
               'SE ASIGNARON LOS SIGUIENTES VALORES: ' || V_DESCRIPCION;


            UPDATE FLUJO_DOC
               SET PRODUCTO = V_PRODUCTO,
                   FECHA_VALOR = V_FECHA_VALOR,
                   FECHA_OPERACION = V_FECHA_OPERACION,
                   COMISION = V_COMISION,
                   COND_ESPECIAL = V_COND_ESPECIAL,
                   TASA_CAMBIO = V_TASA_CAMBIO,
                   NUMERO = V_NUMERO,
                   NUMERO_CONTROL = V_NUMERO_CONTROL,
                   MONTO = V_MONTO,
                   MONEDA = V_MONEDA
             WHERE ID = V_ID;

            V_FASE := 70;

            FOR DET IN (SELECT F.ID_REQ,
                               F.ID_REQ_GRUP,
                               ID_ESTATUS,
                               ID_ESTATUS_ANT
                          FROM FLUJO_DOC_DET F
                         WHERE ID = V_ID AND F.ACTIVO = 'A')
            LOOP
               INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                          ID,
                                          PRECIRIF,
                                          CIRIF,
                                          ID_ESTATUS,
                                          ID_ESTATUS_ANT,
                                          ID_REQ_GRUP,
                                          ID_REQ,
                                          OBSERVACIONES,
                                          CND_REPRESENTANTE,
                                          FECHA_INC,
                                          USUARIO_INC,
                                          TIPO_HIS)
                    VALUES (FNC_GET_ID_HISTORICO,
                            V_ID,
                            '',
                            '',
                            DET.ID_ESTATUS,
                            DET.ID_ESTATUS,
                            DET.ID_REQ_GRUP,
                            DET.ID_REQ,
                            V_DESCRIPCION,
                            'N',
                            SYSDATE,
                            V_USUARIO,
                            'MCD');
            END LOOP;

            V_FASE := 80;

            FOR DET IN (SELECT F.ID_REQ,
                               F.ID_REQ_GRUP,
                               F.PRECIRIF,
                               F.CIRIF,
                               F.ID_ESTATUS,
                               F.NOMBRE,
                               F.ID_ESTATUS_ANT
                          FROM FLUJO_DOC_AUT F
                         WHERE ID = V_ID AND F.ACTIVO = 'A')
            LOOP
               INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                          ID,
                                          PRECIRIF,
                                          CIRIF,
                                          NOMBRE,
                                          ID_ESTATUS,
                                          ID_ESTATUS_ANT,
                                          ID_REQ_GRUP,
                                          ID_REQ,
                                          OBSERVACIONES,
                                          CND_REPRESENTANTE,
                                          FECHA_INC,
                                          USUARIO_INC,
                                          TIPO_HIS)
                    VALUES (FNC_GET_ID_HISTORICO,
                            V_ID,
                            DET.PRECIRIF,
                            DET.CIRIF,
                            DET.NOMBRE,
                            DET.ID_ESTATUS,
                            DET.ID_ESTATUS,
                            DET.ID_REQ_GRUP,
                            DET.ID_REQ,
                            V_DESCRIPCION,
                            'S',
                            SYSDATE,
                            V_USUARIO,
                            'MCD');
            END LOOP;
         END IF;
      END LOOP;
   END IF;

   --Para MODIFICACION DEL NUMERO
   IF V_OPERACION = 'UN'
   THEN
      V_FASE := 90;



      FOR REG IN (SELECT NUMERO
                    FROM FLUJO_DOC FC
                   WHERE ID = V_ID)
      LOOP
         V_DESCRIPCION := '';

         IF NVL (TO_CHAR (V_NUMERO), '@') != NVL (TO_CHAR (REG.NUMERO), '@')
         THEN
            V_DESCRIPCION := 'SE ASIGNO EL NUMERO: ' || V_NUMERO;


            FOR REG
               IN (SELECT ID
                     FROM FLUJO_DOC FC
                    WHERE     FECHA_OPERACION = V_FECHA_OPERACION
                          AND ID_REQ_GRUP = V_ID_REQ_GRUP
                          AND ACTIVO = 'A'
                          AND NUMERO = V_NUMERO)
            LOOP
               MESSAGE :=
                     'EL NUMERO '
                  || V_NUMERO
                  || ' YA FUE ASIGNADO A OTRO FLUJO CON FECHA DE OPERACION '
                  || TO_CHAR (V_FECHA_OPERACION, 'DD/MM/YYYY');
               RAISE NO_VALIDO;
            END LOOP;


            UPDATE FLUJO_DOC
               SET NUMERO = V_NUMERO
             WHERE ID = V_ID;

            V_FASE := 100;

            FOR DET IN (SELECT F.ID_REQ,
                               F.ID_REQ_GRUP,
                               ID_ESTATUS,
                               ID_ESTATUS_ANT
                          FROM FLUJO_DOC_DET F
                         WHERE ID = V_ID AND F.ACTIVO = 'A')
            LOOP
               INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                          ID,
                                          PRECIRIF,
                                          CIRIF,
                                          ID_ESTATUS,
                                          ID_ESTATUS_ANT,
                                          ID_REQ_GRUP,
                                          ID_REQ,
                                          OBSERVACIONES,
                                          CND_REPRESENTANTE,
                                          FECHA_INC,
                                          USUARIO_INC,
                                          TIPO_HIS)
                    VALUES (FNC_GET_ID_HISTORICO,
                            V_ID,
                            '',
                            '',
                            DET.ID_ESTATUS,
                            DET.ID_ESTATUS,
                            DET.ID_REQ_GRUP,
                            DET.ID_REQ,
                            V_DESCRIPCION,
                            'N',
                            SYSDATE,
                            V_USUARIO,
                            'MCD');
            END LOOP;

            V_FASE := 110;

            FOR DET IN (SELECT F.ID_REQ,
                               F.ID_REQ_GRUP,
                               F.PRECIRIF,
                               F.CIRIF,
                               F.ID_ESTATUS,
                               F.NOMBRE,
                               F.ID_ESTATUS_ANT
                          FROM FLUJO_DOC_AUT F
                         WHERE ID = V_ID AND F.ACTIVO = 'A')
            LOOP
               INSERT INTO FLUJO_DOC_HIS (ID_HISTORICO,
                                          ID,
                                          PRECIRIF,
                                          CIRIF,
                                          NOMBRE,
                                          ID_ESTATUS,
                                          ID_ESTATUS_ANT,
                                          ID_REQ_GRUP,
                                          ID_REQ,
                                          OBSERVACIONES,
                                          CND_REPRESENTANTE,
                                          FECHA_INC,
                                          USUARIO_INC,
                                          TIPO_HIS)
                    VALUES (FNC_GET_ID_HISTORICO,
                            V_ID,
                            DET.PRECIRIF,
                            DET.CIRIF,
                            DET.NOMBRE,
                            DET.ID_ESTATUS,
                            DET.ID_ESTATUS,
                            DET.ID_REQ_GRUP,
                            DET.ID_REQ,
                            V_DESCRIPCION,
                            'S',
                            SYSDATE,
                            V_USUARIO,
                            'MCD');
            END LOOP;
         END IF;
      END LOOP;
   END IF;

       --      ********** Insercion den flujo doc padre para las busquedas    ********     --
       SELECT MAX (LEVEL) NIVEL
         INTO V_PAD_NIVEL
         FROM FLUJO_DOC
        WHERE ID = V_ID
   CONNECT BY PRIOR ID = ID_PADRE;

       SELECT CONNECT_BY_ROOT (ID) IDPAD
         INTO V_PAD_IDPAD
         FROM FLUJO_DOC D
        WHERE D.ID = V_ID AND LEVEL = V_PAD_NIVEL
   CONNECT BY PRIOR D.ID = D.ID_PADRE;


       --Numero de flujos que tiene el tracking
       SELECT COUNT (F.ID)
         INTO V_PAD_NUMFLUJOS
         FROM FLUJO_DOC F, GRUPO_REQUISITO G
        WHERE F.ID_REQ_GRUP = G.ID AND F.ACTIVO = 'A'
   START WITH F.ID = V_PAD_IDPAD
   CONNECT BY PRIOR F.ID = F.ID_PADRE;

       SELECT SUBSTR (SYS_CONNECT_BY_PATH (NUMERO, ' - '), 4)
         INTO V_PAD_NUMERO
         FROM FLUJO_DOC D
        WHERE D.ID = V_ID AND LEVEL = V_PAD_NIVEL
   CONNECT BY PRIOR D.ID = D.ID_PADRE;

   BEGIN
      INSERT INTO FLUJO_DOC_PADRE (ID,
                                   ID_PADRE,
                                   NUMERO_TOTAL,
                                   NUMERO_FLUJOS,
                                   NIVEL)
           VALUES (V_ID,
                   V_PAD_IDPAD,
                   V_PAD_NUMERO,
                   V_PAD_NUMFLUJOS,
                   V_PAD_NIVEL);
   EXCEPTION
      WHEN OTHERS
      THEN
         NULL;
   END;

   UPDATE FLUJO_DOC_PADRE
      SET ID_PADRE = V_PAD_IDPAD,
          NUMERO_TOTAL = V_PAD_NUMERO,
          NUMERO_FLUJOS = V_PAD_NUMFLUJOS,
          NIVEL = V_PAD_NIVEL
    WHERE ID = V_ID;


   --Fin: ********** Insercion den flujo doc padre para las busquedas    ******** Fin: --


   UPDATE FLUJO_DOC
      SET FECHA_ULT_MOD = SYSDATE, USUARIO_ULT_MOD = V_USUARIO
    WHERE ID = V_ID;

EXCEPTION
   WHEN NO_VALIDO
   THEN
   rollback;
      MESSAGE := 'PR_INS_FLUJO_DOC <ERROR>' || MESSAGE || '<ERROR> ';
      PR_INS_ERRORES (V_USUARIO,V_IP, SYSDATE, MESSAGE);
          COMMIT;
      RAISE_APPLICATION_ERROR (-20002, MESSAGE);
   WHEN OTHERS
   THEN
   rollback;   
      MESSAGE :=
            'PR_INS_FLUJO_DOC <ERROR> 002099 '
         || V_FASE
         || MESSAGE
         || ' - ERROR EN PROCEDIMIENTO PARA  FLUJOS <ERROR>'
         || SQLERRM;
      PR_INS_ERRORES (V_USUARIO,V_IP, SYSDATE, MESSAGE);
            COMMIT;
      RAISE_APPLICATION_ERROR (-20099, MESSAGE);
END PR_INS_FLUJO_DOC;

PROCEDURE PR_UPD_GRUPO_NUMERO (
   IN_ID_REQ_GRUP   IN VARCHAR2,
   IN_NUMERO        IN VARCHAR2,
   IN_FECHA_OPERACION  IN VARCHAR2,   
   IN_USUARIO       IN VARCHAR2,
   IN_IP            IN VARCHAR2,
   IN_OPERACION     IN VARCHAR2)
IS
   V_ID_REQ_GRUP   GRUPO_REQUISITO.ID%TYPE := IN_ID_REQ_GRUP;
   V_NUMERO        GRUPO_NUMERO.ULTIMO_NUMERO%TYPE := TO_NUMBER (IN_NUMERO);
   V_FECHA_OPERACION        GRUPO_NUMERO.ULTIMA_FECHA_OPER%TYPE := TO_DATE (IN_FECHA_OPERACION,'DD/MM/YYYY');
   V_ID_GRU_NUM    GRUPO_REQUISITO.ID_GRU_NUM%TYPE := '';

   V_OPERACION     VARCHAR2 (2) := UPPER (IN_OPERACION);
   V_USUARIO       VARCHAR2 (30) := UPPER (IN_USUARIO);
   V_IP            VARCHAR2 (16) := IN_IP;
   --

   MESSAGE         VARCHAR2 (300) := '';
   NO_VALIDO       EXCEPTION;
   V_FASE          NUMBER := 0;
BEGIN
   V_FASE := 100;

   
   -- Modificacion del numero, (cada vez que se incrementa)
   IF V_OPERACION = 'U' 
   THEN
      V_FASE := 200;

      BEGIN
         SELECT ID_GRU_NUM
           INTO V_ID_GRU_NUM
           FROM GRUPO_REQUISITO
          WHERE ID = V_ID_REQ_GRUP;

         UPDATE GRUPO_NUMERO
            SET ULTIMO_NUMERO = V_NUMERO
          WHERE ID_GRU_NUM = V_ID_GRU_NUM;
      END;
      
   ELSIF V_OPERACION = 'R' --Resetear el numero a 0 (cuando ocurre el cierre diario)
   THEN
      V_FASE := 200;

      BEGIN
         SELECT ID_GRU_NUM
           INTO V_ID_GRU_NUM
           FROM GRUPO_REQUISITO
          WHERE ID = V_ID_REQ_GRUP;

         UPDATE GRUPO_NUMERO g
            SET ULTIMO_NUMERO = 0, ULTIMA_FECHA_OPER = V_FECHA_OPERACION,
                USUARIO_ULT_MOD = V_USUARIO
          WHERE ID_GRU_NUM = V_ID_GRU_NUM
          AND G.NUM_DIARIO = 'S';  -- Davidspp: 05/04/2013
          
      END;
   END IF;
EXCEPTION
   WHEN OTHERS
   THEN
      ROLLBACK;
      MESSAGE :=
            'PR_UPD_GRUPO_NUMERO <ERROR> 001099 FASE:'
         || V_FASE
         || ' - ERROR EN PROCEDIMIENTO PARA MODIFICAR EL NUMERO CONSECUTIVO <ERROR>'
         || SQLERRM;
      PR_INS_ERRORES (V_USUARIO,
                      V_IP,
                      SYSDATE,
                      MESSAGE);
      COMMIT;
      RAISE_APPLICATION_ERROR (-20099, MESSAGE);
END PR_UPD_GRUPO_NUMERO;
 
   
   
END LIBRO_ORDENES;
/


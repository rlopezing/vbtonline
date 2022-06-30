CREATE OR REPLACE PACKAGE BODY VBT_TARJETA.PCK_TARJETA
AS
   PROCEDURE PR_CARGAR_TARJETAS (I_CODCAR     IN     VARCHAR2,
                                 O_TARJETAS      OUT SYS_REFCURSOR,
                                 O_MENSAJE       OUT VARCHAR2,
                                 I_ORIGEN     IN     VARCHAR2,
                                 I_EXACTA     IN     VARCHAR2)
   AS
      NCARTERA   VARCHAR2 (50);
   BEGIN
      O_MENSAJE := 'SUCCESS';

      IF (I_ORIGEN = 'B')
      THEN
         NCARTERA := REPLACE (I_CODCAR, '''');

         IF (I_EXACTA = 'SI')
         THEN
            OPEN O_TARJETAS FOR
                  'SELECT 
                    NRO_DOC || ''_'' || COD_CAR || ''_'' || NRO_PLASTICO || ''_'' || REPLACE(NOMBRE_PLASTICO,'','','' '') || ''_'' || BLOQUEO  COD_PLASTICO,
                    COD_CAR,
                    DECODE(B.COD_TPO_TDC,''V'', ''VISA '',''Master '') || ''****-****-*'' || SUBSTR(NRO_PLASTICO, -7 ,17) NRO_PLASTICO
                    FROM   
                      VBT_TARJETA.CTACAR_TEMP C, 
                      VBT_TARJETA.BINES_TDC B                   
                    WHERE  
                     COD_CAR IN ('
               || I_CODCAR
               || ') AND (
                        SIT_DOC = ''1'' OR 
                        SIT_DOC = ''2''
                      ) AND
                      SUBSTR(C.NRO_PLASTICO, 5 ,6) = B.BIN 
                         
                    ORDER BY 
                      C.NRO_PLASTICO ASC';
         ELSE
            OPEN O_TARJETAS FOR
                  'SELECT 
                        NRO_DOC || ''_'' || COD_CAR || ''_'' || NRO_PLASTICO || ''_'' || REPLACE(NOMBRE_PLASTICO,'','','' '') || ''_'' || BLOQUEO  COD_PLASTICO,
                        COD_CAR,
                        DECODE(B.COD_TPO_TDC,''V'', ''VISA '',''Master '') || ''****-****-*'' || SUBSTR(NRO_PLASTICO, -7 ,17) NRO_PLASTICO
                        FROM   
                          VBT_TARJETA.CTACAR_TEMP C, 
                          VBT_TARJETA.BINES_TDC B                   
                        WHERE  
                         COD_CAR LIKE ''%'
               || NCARTERA
               || '''  AND (
                            SIT_DOC = ''1'' OR 
                            SIT_DOC = ''2''
                          ) AND
                          SUBSTR(C.NRO_PLASTICO, 5 ,6) = B.BIN 
                             
                        ORDER BY 
                          C.NRO_PLASTICO ASC';
         END IF;
      ELSE
         OPEN O_TARJETAS FOR
               'SELECT 
                NRO_DOC || ''_'' || COD_CAR || ''_'' || NRO_PLASTICO || ''_'' || REPLACE(NOMBRE_PLASTICO ,'','','' '') COD_PLASTICO,
                COD_CAR,
                DECODE(B.COD_TPO_TDC,''V'', ''VISA '',''Master '') || ''****-****-****'' || SUBSTR(NRO_PLASTICO, -4 ,17) NRO_PLASTICO
                FROM   
                  VBT_TARJETA.CTACAR_TEMP C, 
                  VBT_TARJETA.BINES_TDC B                   
                WHERE  
                  COD_CAR IN ('
            || I_CODCAR
            || ') AND (
                    SIT_DOC = ''1'' OR 
                    SIT_DOC = ''2''
                  ) AND
                  SUBSTR(C.NRO_PLASTICO, 5 ,6) = B.BIN 
                  AND
                  (bloqueo IS NULL OR BLOQUEO= ''Q'')          
                ORDER BY 
                  C.NRO_PLASTICO ASC';
      END IF;
   END PR_CARGAR_TARJETAS;
   
 /* PROCEDURE PR_CARGAR_TDC_PRINCIPALES (I_CODCAR     IN     VARCHAR2,
                                 O_TARJETAS      OUT SYS_REFCURSOR,
                                 O_MENSAJE       OUT VARCHAR2,
                                 I_ORIGEN     IN     VARCHAR2,
                                 I_EXACTA     IN     VARCHAR2)
   AS
      NCARTERA   VARCHAR2 (50);
   BEGIN
      O_MENSAJE := 'SUCCESS';

         OPEN O_TARJETAS FOR
               'SELECT 
                NRO_DOC || ''_'' || COD_CAR || ''_'' || NRO_PLASTICO || ''_'' || REPLACE(NOMBRE_PLASTICO ,'','','' '') COD_PLASTICO,
                COD_CAR,
                DECODE(B.COD_TPO_TDC,''V'', ''VISA '',''Master '') || ''****-****-****'' || SUBSTR(NRO_PLASTICO, -4 ,17) NRO_PLASTICO
                FROM   
                  VBT_TARJETA.CTACAR_TEMP C, 
                  VBT_TARJETA.BINES_TDC B                   
                WHERE  
                  COD_CAR IN ('
            || I_CODCAR
            || ') AND (
                    SIT_DOC = ''1'' OR 
                    SIT_DOC = ''2''
                  ) AND
                  SUBSTR(C.NRO_PLASTICO, 5 ,6) = B.BIN 
                  
                 
                ORDER BY 
                  C.NRO_PLASTICO ASC';
    
   END PR_CARGAR_TDC_PRINCIPALES;   
*/
   PROCEDURE PR_CONSULTAR_ESTATUS (I_CODCAR            IN     VARCHAR2,
                                   I_NUM_CTA           IN     VARCHAR2,
                                   I_NUM_DOC           IN     VARCHAR2,
                                   O_ESTATUS              OUT VARCHAR2,
                                   O_LISTA_ACTIVAS        OUT SYS_REFCURSOR,
                                   O_LISTA_HISTORICO      OUT SYS_REFCURSOR,
                                   O_CODIGO               OUT VARCHAR2,
                                   O_MENSAJE              OUT VARCHAR2)
   AS
      C_ESTATUS   SYS_REFCURSOR;
   BEGIN
      O_CODIGO := '0';
      O_MENSAJE := 'SUCCESS';


      OPEN C_ESTATUS FOR
            'SELECT 
      NVL(TCC.BLOQUEO,''A'') ESTATUS    
      FROM 
        CTACAR_TEMP TCC       
      WHERE
        TCC.COD_CAR IN ('
         || I_CODCAR
         || ') AND
        TCC.NRO_PLASTICO = '
         || I_NUM_DOC
         || ' AND
        TCC.NRO_DOC = '
         || I_NUM_CTA;



      FETCH C_ESTATUS INTO O_ESTATUS;

      OPEN O_LISTA_ACTIVAS FOR
            'SELECT 
        TO_CHAR(TTD.FECHA_INICIO,''DD/MM/YYYY''),
        TO_CHAR(TTD.FECHA_FIN,''DD/MM/YYYY''),
        DECODE(TTD.STATUS,''EC'', ''E'', TTD.STATUS ),
        TTD.COD_DESBLOQUEO,
        TTD.CODCAR,
        TTD.NRO_CTA,
        TTD.NRO_DOC,
        TTD.USRID,
        TO_CHAR(TTD.FECHA_CARGA,''DD/MM/YYYY HH:MM:SS AM''),
        G.NOMBGRP,
        G.CATEGORIA     
        FROM 
          TARJETAS_DESBLOQUEO TTD,
          USUGRP_V1 UG,
          CTAGRP G     
        WHERE
          TTD.STATUS IN (''P'', ''E'', ''EC'') AND
          TTD.CODCAR IN ('
         || I_CODCAR
         || ') AND
          TTD.NRO_CTA = '
         || I_NUM_CTA
         || ' AND
          TTD.NRO_DOC = '
         || I_NUM_DOC
         || ' AND TTD.USRID = UG.LOGIN AND UG.CODGRP = G.CODGRP '
         || ' ORDER BY TTD.FECHA_FIN ASC';

      OPEN O_LISTA_HISTORICO FOR
            'SELECT 
        TO_CHAR(TTD.FECHA_INICIO,''DD/MM/YYYY''),
        TO_CHAR(TTD.FECHA_FIN,''DD/MM/YYYY''),
         TTD.STATUS,
        TTD.USRID,
        TO_CHAR(TTD.FECHA_CARGA,''DD/MM/YYYY HH:MM:SS AM''),
        G.NOMBGRP  
        FROM 
          TARJETAS_DESBLOQUEO TTD,
          USUGRP_V1 UG,
          CTAGRP G  
        WHERE
          TTD.STATUS IN (''C'', ''I'') AND
          TTD.CODCAR IN ('
         || I_CODCAR
         || ') AND
          TTD.NRO_CTA = '
         || I_NUM_CTA
         || ' AND
          TTD.NRO_DOC = '
         || I_NUM_DOC
         || ' AND TTD.USRID = UG.LOGIN AND UG.CODGRP = G.CODGRP '
         || ' ORDER BY TTD.FECHA_FIN DESC';
   END PR_CONSULTAR_ESTATUS;


   PROCEDURE PR_REGISTRAR_DESBLOQUEO (I_CODCAR        IN     VARCHAR2,
                                      I_NRO_DOC       IN     VARCHAR2,
                                      I_NRO_CTA       IN     VARCHAR2,
                                      I_FEC_INICIAL   IN     VARCHAR2,
                                      I_FEC_FINAL     IN     VARCHAR2,
                                      I_USRID         IN     VARCHAR2,
                                      O_MENSAJE          OUT VARCHAR2,
                                      I_ESTATUS       IN     VARCHAR2)
   AS
      V_CANT_ACTIVAS   NUMBER;
   BEGIN
      O_MENSAJE := 'SUCCESS';
      V_CANT_ACTIVAS := 0;

      /*
            SELECT COUNT(*)
              INTO
                V_CANT_ACTIVAS
              FROM
                TARJETAS_DESBLOQUEO TTD
              WHERE
                TTD.CODCAR = I_CODCAR AND
                TTD.NRO_CTA = I_NRO_CTA AND
                TTD.NRO_DOC = I_NRO_DOC AND
                TTD.STATUS IN ('P','E');   */


      --Valida que la fecha de fin de la nueva regla no este contenida en una de las viejas
      /*  SELECT COUNT (*)
          INTO V_CANT_ACTIVAS
          FROM TARJETAS_DESBLOQUEO TTD
         WHERE     TTD.CODCAR = I_CODCAR
               AND TTD.NRO_DOC = I_NRO_DOC
               AND TTD.NRO_CTA = I_NRO_CTA
               AND TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy') >=
                      TO_DATE (TO_CHAR (TTD.FECHA_INICIO, 'dd/mm/yyyy'),
                               'dd/mm/yyyy')
               AND TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy') <=
                      TO_DATE (TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                               'dd/mm/yyyy')
               AND TTD.STATUS IN ('P', 'E');
  */


      -- Valida que la fecha fin de las viejas reglas  no este contenida en las nueva

      /*  IF (V_CANT_ACTIVAS = 0)
        THEN
           SELECT COUNT (*)
             INTO V_CANT_ACTIVAS
             FROM TARJETAS_DESBLOQUEO TTD
            WHERE     TTD.CODCAR = I_CODCAR
                  AND TTD.NRO_DOC = I_NRO_DOC
                  AND TTD.NRO_CTA = I_NRO_CTA
                  AND TO_DATE (TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                               'dd/mm/yyyy') <=
                         TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                  AND TO_DATE (TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                               'dd/mm/yyyy') >=
                         TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                  AND TTD.STATUS IN ('P', 'E');
        END IF;
  */

      IF (V_CANT_ACTIVAS = 0)
      THEN
         INSERT INTO TARJETAS_DESBLOQUEO (COD_DESBLOQUEO,
                                          CODCAR,
                                          NRO_CTA,
                                          NRO_DOC,
                                          FECHA_INICIO,
                                          FECHA_FIN,
                                          USRID,
                                          STATUS)
              VALUES (SEQ_DESBLOQUEO.NEXTVAL,
                      I_CODCAR,
                      I_NRO_CTA,
                      I_NRO_DOC,
                      TO_DATE (I_FEC_INICIAL, 'DD/MM/YYYY'),
                      TO_DATE (I_FEC_FINAL, 'DD/MM/YYYY'),
                      I_USRID,
                      I_ESTATUS);
      ELSE
         O_MENSAJE := 'WRONG';
      END IF;
   END PR_REGISTRAR_DESBLOQUEO;

   PROCEDURE PR_MODIFICAR_DESBLOQUEO (I_COD_DESBLOQUEO   IN     VARCHAR2,
                                      I_CODCAR           IN     VARCHAR2,
                                      I_NRO_DOC          IN     VARCHAR2,
                                      I_NRO_CTA          IN     VARCHAR2,
                                      I_FEC_INICIAL      IN     VARCHAR2,
                                      I_FEC_FINAL        IN     VARCHAR2,
                                      I_USRID            IN     VARCHAR2,
                                      O_MENSAJE             OUT VARCHAR2)
   AS
      V_STATUS   VARCHAR2 (1);
   BEGIN
      O_MENSAJE := 'SUCCESS';

      SELECT TTD.STATUS
        INTO V_STATUS
        FROM TARJETAS_DESBLOQUEO TTD
       WHERE TTD.COD_DESBLOQUEO = I_COD_DESBLOQUEO;


      IF (V_STATUS = 'P')
      THEN
         UPDATE TARJETAS_DESBLOQUEO
            SET FECHA_INICIO = TO_DATE (I_FEC_INICIAL, 'DD/MM/YYYY'),
                FECHA_FIN = TO_DATE (I_FEC_FINAL, 'DD/MM/YYYY'),
                FECHA_MODIFICACION = SYSDATE,
                USRID_MODIFICACION = I_USRID
          WHERE COD_DESBLOQUEO = I_COD_DESBLOQUEO;
      ELSE
         IF (V_STATUS = 'E' OR V_STATUS = 'EC')
         THEN
            IF (TO_DATE (I_FEC_FINAL, 'DD/MM/YYYY') > SYSDATE)
            THEN
               UPDATE TARJETAS_DESBLOQUEO
                  SET FECHA_FIN = TO_DATE (I_FEC_FINAL, 'DD/MM/YYYY'),
                      FECHA_MODIFICACION = SYSDATE,
                      USRID_MODIFICACION = I_USRID
                WHERE COD_DESBLOQUEO = I_COD_DESBLOQUEO;
            ELSE
               O_MENSAJE := 'NO OK';
            END IF;
         END IF;
      END IF;
   --AGREGAR FECHA DE MODIFICACION
   /*    UPDATE VBT_TARJETA.TARJETAS_DESBLOQUEO
       SET    COD_DESBLOQUEO = I_COD_DESBLOQUEO,
              CODCAR         = I_CODCAR,
              NRO_CTA        = I_NRO_CTA,
              NRO_DOC        = I_NRO_DOC,
              FECHA_INICIO   = TO_DATE (I_FEC_INICIAL, 'DD/MM/YYYY'),
              FECHA_FIN      = TO_DATE (I_FEC_FINAL, 'DD/MM/YYYY'),


              USRID          = I_USRID
       WHERE  COD_DESBLOQUEO = I_COD_DESBLOQUEO;*/


   END PR_MODIFICAR_DESBLOQUEO;



   PROCEDURE PR_ELIMINAR_REGLA (I_COD_DESBLOQUEO   IN     VARCHAR2,
                                O_MENSAJE             OUT VARCHAR2)
   AS
      V_EXISTE   NUMBER;
   BEGIN
      O_MENSAJE := 'SUCCESS';

      SELECT COUNT (*)
        INTO V_EXISTE
        FROM TARJETAS_DESBLOQUEO TTD
       WHERE TTD.COD_DESBLOQUEO = I_COD_DESBLOQUEO;

      IF (V_EXISTE > 0)
      THEN
         UPDATE TARJETAS_DESBLOQUEO
            SET STATUS = 'I', FECHA_STATUS = SYSDATE
          WHERE COD_DESBLOQUEO = I_COD_DESBLOQUEO;
      ELSE
         O_MENSAJE := 'WRONG';
      END IF;
   END PR_ELIMINAR_REGLA;


   PROCEDURE PR_CAMBIAR_STATUS_REGLA (IN_COD_DESBLOQUEO   IN NUMBER,
                                      IN_FECHA_PROCESO    IN VARCHAR2,
                                      IN_PROCESO          IN VARCHAR2)
   IS
      V_COD_DESBLOQUEO   TARJETAS_DESBLOQUEO.COD_DESBLOQUEO%TYPE
                            := IN_COD_DESBLOQUEO;
      V_FECHA_PROCESO    DATE := SYSDATE;
      V_PROCESO          TARJETAS_DESBLOQUEO.STATUS%TYPE := IN_PROCESO;

      --
      V_FASE             PLS_INTEGER := 0;
      MESSAGE            VARCHAR2 (300) := '';
      NO_VALIDO          EXCEPTION;
   BEGIN
      V_FASE := 100;


      IF IN_FECHA_PROCESO IS NOT NULL
      THEN
         V_FECHA_PROCESO := TO_DATE (IN_FECHA_PROCESO, 'DD/MM/YYYY');
      END IF;

      IF V_PROCESO = 'B'
      THEN
         V_FASE := 200;

         UPDATE TARJETAS_DESBLOQUEO
            SET ENVIADO_BLOQUEO = V_FECHA_PROCESO
          WHERE COD_DESBLOQUEO = V_COD_DESBLOQUEO;
      ELSIF V_PROCESO = 'D'
      THEN
         V_FASE := 300;

         UPDATE TARJETAS_DESBLOQUEO
            SET ENVIADO_DESBLOQUEO = V_FECHA_PROCESO
          WHERE COD_DESBLOQUEO = V_COD_DESBLOQUEO;
      END IF;

      V_FASE := 400;
   EXCEPTION
      WHEN OTHERS
      THEN
         --ROLLBACK;
         MESSAGE :=
               'PCK_TARJETA.PR_CAMBIAR_STATUS_REGLA <ERROR> 000002 '
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO PARA PR_CAMBIAR_STATUS_REGLA<ERROR>'
            || SQLERRM;
         -- PR_ERRORES (V_USUARIO_ENV,V_IP_ENV, MESSAGE,V_XML_ENV);
         --COMMIT;
         RAISE_APPLICATION_ERROR (-20002, MESSAGE);
   END PR_CAMBIAR_STATUS_REGLA;



   PROCEDURE PR_CONSULTAR_X_FECHAINI (
      O_REGLAS_PENDIENTES      OUT SYS_REFCURSOR,
      IN_FECHA_PROCESO      IN     VARCHAR2)
   IS
      V_FECHA           VARCHAR2 (20) := '';
      SQLSTRING         VARCHAR2 (600) := '';
      SIGUIENTE_HABIL   VARCHAR2 (20) := '';
      --
      V_FASE            PLS_INTEGER := 0;
      MESSAGE           VARCHAR2 (300) := '';
      NO_VALIDO         EXCEPTION;
   BEGIN
      V_FASE := 1;

      IF IN_FECHA_PROCESO IS NULL
      THEN
         V_FECHA := TO_CHAR (TRUNC (SYSDATE), 'DD/MM/YYYY');
      ELSE
         V_FECHA := IN_FECHA_PROCESO;
      END IF;

      SIGUIENTE_HABIL :=
         TO_CHAR (GET_NEXT_FECHA ('0000009539', V_FECHA), 'DD/MM/YYYY');


      SQLSTRING :=
            'SELECT D.COD_DESBLOQUEO, D.CODCAR, D.NRO_CTA, 
                D.NRO_DOC, D.FECHA_INICIO, D.FECHA_FIN, 
                D.STATUS, D.TIPO_STATUS, T.BLOQUEO
                FROM TARJETAS_DESBLOQUEO D, CTACAR_TEMP T 
                WHERE D.FECHA_INICIO <= TO_DATE('''
         || SIGUIENTE_HABIL
         || ''',''DD/MM/YYYY'')'
         || ' AND D.FECHA_FIN      >=  TO_DATE('''
         || V_FECHA
         || ''',''DD/MM/YYYY'')'
         || ' AND D.STATUS =''P''  
                 AND (T.BLOQUEO IS NULL OR T.BLOQUEO = ''Q'') 
                    AND T.COD_CAR = D.CODCAR 
                    AND T.NRO_DOC = D.NRO_CTA 
                    AND T.NRO_PLASTICO = D.NRO_DOC ';


      --             to_char(A.FECHCIERRE-1,''dd/mm/yyyy'') FECHA_CIERRE,
      --  p_cuentas_corri:= cs_cuentas_corrie;

      OPEN O_REGLAS_PENDIENTES FOR SQLSTRING;

      V_FASE := 2;
   EXCEPTION
      WHEN OTHERS
      THEN
         --ROLLBACK;
         MESSAGE :=
               'PCK_TARJETA.PR_CONSULTAR_X_FECHAINI <ERROR> 000002 '
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO PARA PR_CONSULTAR_X_FECHAINI<ERROR>'
            || SQLERRM;
         -- PR_ERRORES (V_USUARIO_ENV,V_IP_ENV, MESSAGE,V_XML_ENV);
         --COMMIT;
         RAISE_APPLICATION_ERROR (-20002, MESSAGE);
   END PR_CONSULTAR_X_FECHAINI;

   PROCEDURE PR_CONSULTAR_X_FECHAFIN (
      O_REGLAS_X_CONCLUIR      OUT SYS_REFCURSOR,
      IN_FECHA_PROCESO      IN     VARCHAR2)
   IS
      V_FECHA_PROCESO     DATE := SYSDATE;
      V_FECHA             VARCHAR2 (20) := IN_FECHA_PROCESO;
      SIGUIENTE_HABIL     VARCHAR2 (20) := '';
      SIGUIENTE_HABIL_D   DATE;
      SQLSTRING           VARCHAR2 (3000) := '';
      --
      V_FASE              PLS_INTEGER := 0;
      MESSAGE             VARCHAR2 (300) := '';
      NO_VALIDO           EXCEPTION;
   BEGIN
      V_FASE := 1;

      IF IN_FECHA_PROCESO IS NULL
      THEN
         V_FECHA := TO_CHAR (TRUNC (V_FECHA_PROCESO), 'DD/MM/YYYY');
      ELSE
         V_FECHA := IN_FECHA_PROCESO;
         V_FECHA_PROCESO := TO_DATE (V_FECHA, 'DD/MM/YYYY');
      END IF;

      V_FASE := 2;

      SIGUIENTE_HABIL :=
         TO_CHAR (GET_NEXT_FECHA ('0000009539', V_FECHA), 'DD/MM/YYYY');

      V_FASE := 3;

      --    OPEN cs_cuentas_corrie FOR
      --     SQLSTRING := 'SELECT D.COD_DESBLOQUEO, D.CODCAR, D.NRO_CTA,
      --                D.NRO_DOC, D.FECHA_INICIO, D.FECHA_FIN,
      --                D.STATUS, D.TIPO_STATUS, T.BLOQUEO
      --                FROM TARJETAS_DESBLOQUEO D, CTACAR_TEMP T
      --                WHERE D.FECHA_FIN <= TO_DATE('''||V_FECHA||''',''DD/MM/YYYY'')
      --                AND D.STATUS = ''E''
      --                 AND (T.BLOQUEO IS NULL)
      --                 AND T.COD_CAR = D.CODCAR
      --                AND T.NRO_DOC = D.NRO_CTA
      --                AND T.NRO_PLASTICO = D.NRO_DOC
      --                ';

      FOR REG IN (SELECT D1.COD_DESBLOQUEO
                    FROM TARJETAS_DESBLOQUEO D1, CTACAR_TEMP T
                   WHERE     D1.FECHA_FIN <= V_FECHA_PROCESO  -- fecha proceso
                         AND D1.STATUS = 'E'
                         AND (T.BLOQUEO IS NULL OR T.BLOQUEO = 'Q')
                         AND T.COD_CAR = D1.CODCAR
                         AND T.NRO_DOC = D1.NRO_CTA
                         AND T.NRO_PLASTICO = D1.NRO_DOC
                         AND (SELECT COUNT (D2.COD_DESBLOQUEO)
                                FROM TARJETAS_DESBLOQUEO D2
                               WHERE     D2.FECHA_INICIO > D1.FECHA_INICIO  --
                                     AND D2.FECHA_FIN > D1.FECHA_FIN        --
                                     AND D2.FECHA_INICIO <=
                                            GET_NEXT_FECHA ('0000009539',
                                                            V_FECHA) --siguiente dia habil
                                     AND D2.STATUS IN ('P', 'E')
                                     AND D2.CODCAR = D1.CODCAR
                                     AND D2.NRO_CTA = D1.NRO_CTA
                                     AND D2.NRO_DOC = D1.NRO_DOC) > 0)
      LOOP
         UPDATE TARJETAS_DESBLOQUEO DD
            SET DD.STATUS = 'EC', DD.FECHA_STATUS = SYSDATE
          WHERE DD.COD_DESBLOQUEO = REG.COD_DESBLOQUEO;
      END LOOP;



      -- COMMIT;
      V_FASE := 4;

      SQLSTRING :=
            'SELECT D1.COD_DESBLOQUEO,
       D1.CODCAR,
       D1.NRO_CTA,
       D1.NRO_DOC,
       D1.FECHA_INICIO,
       D1.FECHA_FIN,
       D1.STATUS,
       D1.TIPO_STATUS,
       T.BLOQUEO
  FROM TARJETAS_DESBLOQUEO D1, CTACAR_TEMP T
 WHERE     D1.FECHA_FIN <=  TO_DATE('''
         || V_FECHA
         || ''',''DD/MM/YYYY'')-- fecha proceso
       AND D1.STATUS = ''E''
       AND (T.BLOQUEO IS NULL OR T.BLOQUEO = ''Q'')
       AND T.COD_CAR = D1.CODCAR
       AND T.NRO_DOC = D1.NRO_CTA
       AND T.NRO_PLASTICO = D1.NRO_DOC
       AND (SELECT COUNT (D2.COD_DESBLOQUEO)
              FROM TARJETAS_DESBLOQUEO D2
             WHERE  D2.FECHA_INICIO > d1.fecha_inicio -- 
                    and D2.FECHA_fin > d1.fecha_fin -- 
                    and D2.FECHA_INICIO <= TO_DATE('''
         || SIGUIENTE_HABIL
         || ''',''DD/MM/YYYY'') --siguiente dia habil
                   AND D2.STATUS IN ( ''P'',''E'')
                   AND D2.CODCAR = D1.CODCAR
                   AND D2.NRO_CTA = D1.NRO_CTA
                   AND D2.NRO_DOC = D1.NRO_DOC) = 0';

      V_FASE := 5;

      OPEN O_REGLAS_X_CONCLUIR FOR SQLSTRING;

      V_FASE := 6;
   EXCEPTION
      WHEN OTHERS
      THEN
         --ROLLBACK;
         MESSAGE :=
               'PCK_TARJETA.PR_CONSULTAR_X_FECHAINI <ERROR> 000002 '
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO PARA PR_CONSULTAR_X_FECHAINI<ERROR>'
            || SQLERRM;
         -- PR_ERRORES (V_USUARIO_ENV,V_IP_ENV, MESSAGE,V_XML_ENV);
         --COMMIT;
         RAISE_APPLICATION_ERROR (-20002, MESSAGE);
   END PR_CONSULTAR_X_FECHAFIN;


   --   PROCEDURE PR_NOTIFICAR (IN_CORREO_ORIGEN      IN VARCHAR2,
   --                           IN_CORREO_DEST        IN VARCHAR2,
   --                           IN_NOMBRE_ARCHIVO     IN VARCHAR2,
   --                           IN_NUMERO_REGISTROS   IN VARCHAR2)
   --   IS
   --        V_TITULO            VARCHAR2 (1000) := '';
   --        V_MOTIVO            elementos_tipos.descripcion%TYPE := '';
   --        V_CUERPO            VARCHAR2 (32767) := '';
   --        CRLF                VARCHAR2 (2) := CHR (13) || CHR (10);
   --        V_FASE              PLS_INTEGER := 0;
   --        FLAG                PLS_INTEGER := 0;
   --        MESSAGE             VARCHAR2 (300) := '';
   --        NO_VALIDO           EXCEPTION;
   --
   --   BEGIN
   --      V_FASE := 100;
   --
   --      SELECT
   --        DESCRIPCION
   --        INTO V_MOTIVO
   --        FROM ELEMENTOS_TIPOS WHERE
   --        CODTIPO='0000000005' AND IN_NOMBRE_ARCHIVO  LIKE '%'||CODELEMENTO||'%';
   --
   --     V_FASE := 110;
   --
   --      V_TITULO :=
   --            'Archivo para '|| V_MOTIVO ||' generado para la fecha: '
   --         || TO_CHAR (TRUNC (SYSDATE + 1), 'DD/MM/YYYY');
   --
   --      V_CUERPO := '<html> ' || CRLF;
   ----      V_CUERPO := V_CUERPO || '<head>' || CRLF;
   ----      V_CUERPO :=
   ----            V_CUERPO
   ----         || '<meta http-equiv=''Content-Type'' content=''text/html; charset=iso-8859-1''>'
   ----         || CRLF;
   ----      V_CUERPO := V_CUERPO || '<title></title>' || CRLF;
   ----      V_CUERPO := V_CUERPO || '<style type=''text/css''>';
   ----      V_CUERPO := V_CUERPO || ' TR.A8NBBlancoVerde {';
   ----      V_CUERPO :=
   ----         V_CUERPO || '  font-family: Arial,helvetica,verdana,sans serif;';
   ----      V_CUERPO := V_CUERPO || '  font-size: 8pt;';
   ----      V_CUERPO := V_CUERPO || '  color: #FFFFFF;';
   ----      V_CUERPO := V_CUERPO || '  background-color: #195266;';
   ----      V_CUERPO := V_CUERPO || '  font-weight: bold;';
   ----      V_CUERPO := V_CUERPO || ' }' || CRLF;
   ----      V_CUERPO := V_CUERPO || ' TR.V8NNegroBlanco {';
   ----      V_CUERPO :=
   ----         V_CUERPO || '  font-family: Arial, Helvetica,verdana,sans-serif;';
   ----      V_CUERPO := V_CUERPO || '  font-size: 8pt;';
   ----      V_CUERPO := V_CUERPO || '  font-style: normal;';
   ----      V_CUERPO := V_CUERPO || '  font-weight: normal;';
   ----      V_CUERPO := V_CUERPO || '  color: #000000;';
   ----      V_CUERPO := V_CUERPO || '  background-color: #FFFFFF';
   ----      V_CUERPO := V_CUERPO || ' }' || CRLF;
   ----      V_CUERPO := V_CUERPO || ' TR.V8NNegroGris {';
   ----      V_CUERPO :=
   ----         V_CUERPO || '  font-family: Arial, Helvetica,verdana,sans-serif;';
   ----      V_CUERPO := V_CUERPO || '  font-size: 8pt;';
   ----      V_CUERPO := V_CUERPO || '  font-style: normal;';
   ----      V_CUERPO := V_CUERPO || '  font-weight: normal;';
   ----      V_CUERPO := V_CUERPO || '  color: #000000;';
   ----      V_CUERPO := V_CUERPO || '  background-color: #F5F5F5;';
   ----      V_CUERPO := V_CUERPO || ' }' || CRLF;
   ----      V_CUERPO := V_CUERPO || ' TR.V8NNegroRojo {';
   ----      V_CUERPO :=
   ----         V_CUERPO || '  font-family: Arial, Helvetica,verdana,sans-serif;';
   ----      V_CUERPO := V_CUERPO || '  font-size: 8pt;';
   ----      V_CUERPO := V_CUERPO || '  font-style: normal;';
   ----      V_CUERPO := V_CUERPO || '  font-weight: normal;';
   ----      V_CUERPO := V_CUERPO || '  color: #FF0000;';
   ----      V_CUERPO := V_CUERPO || '  background-color: #F5F5F5;';
   ----      V_CUERPO := V_CUERPO || ' }' || CRLF;
   ----      V_CUERPO := V_CUERPO || '</style>' || CRLF;
   ----      V_CUERPO := V_CUERPO || '</head>' || CRLF;
   --
   --      V_CUERPO :=
   --            V_CUERPO
   --         || '<body bgcolor=''#FFFFFF'' topmargin=''0'' leftmargin=''0''>'
   --         || CRLF;
   --
   --      V_CUERPO := V_CUERPO || '<P> Estimados:</P>' || CRLF;
   --
   --     V_FASE := 120;
   --
   --      V_CUERPO := V_CUERPO || '<P>' || V_TITULO || '</BR></BR>' || CRLF;
   --     V_FASE := 130;
   --
   --      V_CUERPO :=
   --         V_CUERPO || 'Se ha generado el archivo: <B>' || IN_NOMBRE_ARCHIVO || '</B> para '||v_motivo||'' ||CRLF;
   --
   --      V_CUERPO :=
   --            V_CUERPO
   --         || '<P> <b>Cantidad de tarjetas:</b> '
   --         || IN_NUMERO_REGISTROS
   --         || CRLF;
   --     V_FASE := 140;
   --
   --      V_FASE := 150;
   --
   --
   --      BEGIN
   --
   --         funciones.SEND_HTML_EMAIL (IN_CORREO_ORIGEN,
   --                          IN_CORREO_DEST,
   --                          V_TITULO,
   --                          V_CUERPO);
   --      EXCEPTION
   --         WHEN OTHERS
   --         THEN
   --            MESSAGE :=
   --                  ' ERROR ENVIANDO CORREO ELECTRONICO A '
   --               || IN_CORREO_DEST
   --               || ' '
   --               || SQLERRM;
   --            RAISE NO_VALIDO;
   --      END;
   --
   --
   --
   --   EXCEPTION
   --      WHEN NO_VALIDO
   --      THEN
   --         RAISE_APPLICATION_ERROR (-20001,
   --                                  'FASE:' || V_FASE || '-' || MESSAGE);
   --      WHEN OTHERS
   --      THEN
   --         RAISE_APPLICATION_ERROR (
   --            -20000,
   --               ' FASE:'
   --            || V_FASE
   --            || ' LONGITUD:'
   --            || LENGTH (V_CUERPO)
   --            || ' ERROR EN PROCEDIMIENTO PAR ENVIAR CORREOS ELECTRONICOS '
   --            || SQLERRM);
   --   END PR_NOTIFICAR;


   PROCEDURE PR_NOTIFICAR (IN_CORREO_ORIGEN   IN VARCHAR2,
                           IN_CORREO_DEST     IN VARCHAR2,
                           IN_TITULO             VARCHAR2,
                           IN_TEXTO              VARCHAR2)
   IS
      V_CUERPO    VARCHAR2 (32767) := '';
      CRLF        VARCHAR2 (2) := CHR (13) || CHR (10);
      V_FASE      PLS_INTEGER := 0;
      FLAG        PLS_INTEGER := 0;
      MESSAGE     VARCHAR2 (300) := '';
      NO_VALIDO   EXCEPTION;
   BEGIN
      V_FASE := 100;

      V_CUERPO := '<html> ' || CRLF;

      V_CUERPO :=
            V_CUERPO
         || '<body bgcolor=''#FFFFFF'' topmargin=''0'' leftmargin=''0''>'
         || CRLF;

      V_CUERPO := V_CUERPO || '<P> Estimados:</P>' || CRLF;

      V_FASE := 120;


      V_CUERPO :=
            V_CUERPO
         || 'Se ha ejecutado el proceso para bloqueo y desbloqueo de TDC programados a la fecha '
         || CRLF;

      V_CUERPO := V_CUERPO || CRLF || IN_TEXTO || CRLF;


      V_FASE := 150;


      BEGIN
         FUNCIONES.SEND_EMAIL_DESTINATARIOS (IN_CORREO_ORIGEN,
                                             IN_CORREO_DEST,
                                             IN_TITULO,
                                             V_CUERPO);
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE :=
                  ' ERROR ENVIANDO CORREO ELECTRONICO A '
               || IN_CORREO_DEST
               || ' '
               || SQLERRM;
            RAISE NO_VALIDO;
      END;
   EXCEPTION
      WHEN NO_VALIDO
      THEN
         RAISE_APPLICATION_ERROR (-20001,
                                  'FASE:' || V_FASE || '-' || MESSAGE);
      WHEN OTHERS
      THEN
         RAISE_APPLICATION_ERROR (
            -20000,
               ' FASE:'
            || V_FASE
            || ' LONGITUD:'
            || LENGTH (V_CUERPO)
            || ' ERROR EN PROCEDIMIENTO PAR ENVIAR CORREOS ELECTRONICOS '
            || SQLERRM);
   END PR_NOTIFICAR;



   PROCEDURE PR_PROXIMO_DIA_HABIL (O_FECHA   OUT VARCHAR2,
                                   MENSAJE   OUT VARCHAR2)
   IS
   BEGIN
      MENSAJE := 'OK';

      SELECT TO_CHAR (
                GET_NEXT_FECHA (
                   '0000009539',
                   TO_CHAR (
                      TRUNC (
                         GET_NEXT_FECHA (
                            '0000009539',
                            TO_CHAR (TRUNC (SYSDATE), 'DD/MM/YYYY'))),
                      'DD/MM/YYYY')),
                'DD/MM/YYYY')
        INTO O_FECHA
        FROM DUAL;
   EXCEPTION
      WHEN OTHERS
      THEN
         MENSAJE := SQLERRM;
   END PR_PROXIMO_DIA_HABIL;


   PROCEDURE PR_CARGAR_FERIADOS (I_ANIO       IN     VARCHAR2,
                                 O_FERIADOS      OUT SYS_REFCURSOR,
                                 O_MENSAJE       OUT VARCHAR2)
   AS
   BEGIN
      O_MENSAJE := 'SUCCESS';


      OPEN O_FERIADOS FOR
         SELECT TO_CHAR (DIAFER, 'MM/DD/YYYY'), DESCFER
           FROM TABFER@VARIOS
          WHERE CODCAL = '0000009539' AND TO_CHAR (DIAFER, 'YYYY') = I_ANIO;

      O_MENSAJE := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         O_MENSAJE := SUBSTR (SQLERRM, 1, 300);
   END PR_CARGAR_FERIADOS;


   PROCEDURE PR_VALIDAR_FECHA_REGLA (I_CODCAR        IN     VARCHAR2,
                                     I_NRO_DOC       IN     VARCHAR2,
                                     I_NRO_CTA       IN     VARCHAR2,
                                     I_FEC_INICIAL   IN     VARCHAR2,
                                     I_FEC_FINAL     IN     VARCHAR2,
                                     I_USRID         IN     VARCHAR2,
                                     I_COD_REGLA     IN     VARCHAR2,
                                     I_OPERACION     IN     VARCHAR2,
                                     O_MENSAJE          OUT VARCHAR2,
                                     O_ID_REGLA         OUT VARCHAR2)
   AS
      V_CANT_ACTIVAS   NUMBER;
   BEGIN
      O_MENSAJE := 'OK';
      V_CANT_ACTIVAS := 0;

      BEGIN
         IF (I_OPERACION = 'EDITAR')
         THEN
            SELECT COUNT (*)
              INTO V_CANT_ACTIVAS
              FROM TARJETAS_DESBLOQUEO TTD
             WHERE     TTD.CODCAR = I_CODCAR
                   AND TTD.NRO_DOC = I_NRO_DOC
                   AND TTD.NRO_CTA = I_NRO_CTA
                   AND TTD.STATUS IN ('P', 'E', 'EC')
                   AND (   (    TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy') >=
                                   TO_DATE (
                                      TO_CHAR (TTD.FECHA_INICIO,
                                               'dd/mm/yyyy'),
                                      'dd/mm/yyyy')
                            AND TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy') <=
                                   TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy'))
                        OR (    TO_DATE (
                                   TO_CHAR (TTD.FECHA_INICIO, 'dd/mm/yyyy'),
                                   'dd/mm/yyyy') <=
                                   TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                            AND TO_DATE (
                                   TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                   'dd/mm/yyyy') >=
                                   TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                        OR (    TO_DATE (
                                   TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                   'dd/mm/yyyy') >=
                                   TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                            AND TO_DATE (
                                   TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                   'dd/mm/yyyy') <=
                                   TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))     --
                                --                                          OR
                                 --                                          (
 --                                            TTD.FECHA_INICIO>TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy') AND (VALIDA_MAX_HABILES('0000009539',I_FEC_FINAL,TO_CHAR(TTD.FECHA_INICIO,'dd/MM/yyyy'),2)='N')
                                 --                                          )
                                                                            --
                                --                                          OR
                                 --                                          (
 --                                           TTD.FECHA_FIN<TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy') AND (VALIDA_MAX_HABILES('0000009539',TO_CHAR(TTD.FECHA_FIN,'dd/MM/yyyy'),I_FEC_FINAL,1)='N')
                                 --                                          )
                       )
                   AND TTD.COD_DESBLOQUEO <> I_COD_REGLA;



            SELECT TTD.COD_DESBLOQUEO
              INTO O_ID_REGLA
              FROM TARJETAS_DESBLOQUEO TTD
             WHERE     TTD.CODCAR = I_CODCAR
                   AND TTD.NRO_DOC = I_NRO_DOC
                   AND TTD.NRO_CTA = I_NRO_CTA
                   AND (   (    TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy') >=
                                   TO_DATE (
                                      TO_CHAR (TTD.FECHA_INICIO,
                                               'dd/mm/yyyy'),
                                      'dd/mm/yyyy')
                            AND TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy') <=
                                   TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy'))
                        OR (    TO_DATE (
                                   TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                   'dd/mm/yyyy') >=
                                   TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                            AND TO_DATE (
                                   TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                   'dd/mm/yyyy') <=
                                   TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))     --
                                --                                          OR
                                 --                                          (
 --                                            TTD.FECHA_INICIO>TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy') AND (VALIDA_MAX_HABILES('0000009539',I_FEC_FINAL,TO_CHAR(TTD.FECHA_INICIO,'dd/MM/yyyy'),2)='N')
                                 --                                          )
                                                                            --
                                --                                          OR
                                 --                                          (
 --                                           TTD.FECHA_FIN<TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy') AND (VALIDA_MAX_HABILES('0000009539',TO_CHAR(TTD.FECHA_FIN,'dd/MM/yyyy'),I_FEC_FINAL,1)='N')
                                 --                                          )
                       )
                   AND TTD.COD_DESBLOQUEO <> I_COD_REGLA;
         ELSE
            IF (I_OPERACION = 'CREAR')
            THEN
               SELECT COUNT (*)
                 INTO V_CANT_ACTIVAS
                 FROM TARJETAS_DESBLOQUEO TTD
                WHERE     TTD.CODCAR = I_CODCAR
                      AND TTD.NRO_DOC = I_NRO_DOC
                      AND TTD.NRO_CTA = I_NRO_CTA
                      AND (   (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_INICIO,
                                               'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                           OR (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')))
                      AND TTD.STATUS IN ('P', 'E', 'EC');



               SELECT TTD.COD_DESBLOQUEO
                 INTO O_ID_REGLA
                 FROM TARJETAS_DESBLOQUEO TTD
                WHERE     TTD.CODCAR = I_CODCAR
                      AND TTD.NRO_DOC = I_NRO_DOC
                      AND TTD.NRO_CTA = I_NRO_CTA
                      AND (   (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_INICIO,
                                               'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                           OR (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')))
                      AND TTD.STATUS IN ('P', 'E', 'EC');
            ELSE
               --Crear Regla backoffice
               /* SELECT COUNT (*)
                  INTO V_CANT_ACTIVAS
                  FROM TARJETAS_DESBLOQUEO TTD
                 WHERE     TTD.CODCAR = I_CODCAR
                       AND TTD.NRO_DOC = I_NRO_DOC
                       AND TTD.NRO_CTA = I_NRO_CTA
                       AND ( ( (    TO_DATE (
                                       TO_CHAR (TTD.FECHA_INICIO,
                                                'dd/mm/yyyy'),
                                       'dd/mm/yyyy') <=
                                       TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                                AND TO_DATE (
                                       TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                       'dd/mm/yyyy') >=
                                       TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))))

                       AND TTD.STATUS IN ('P', 'E','EC');



                SELECT TTD.COD_DESBLOQUEO
                  INTO O_ID_REGLA
                  FROM TARJETAS_DESBLOQUEO TTD
                 WHERE     TTD.CODCAR = I_CODCAR
                       AND TTD.NRO_DOC = I_NRO_DOC
                       AND TTD.NRO_CTA = I_NRO_CTA
                       AND ( ( (    TO_DATE (
                                       TO_CHAR (TTD.FECHA_INICIO,
                                                'dd/mm/yyyy'),
                                       'dd/mm/yyyy') <=
                                       TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                                AND TO_DATE (
                                       TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                       'dd/mm/yyyy') >=
                                       TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))))



                       AND TTD.STATUS IN ('P', 'E','EC');*/

               SELECT COUNT (*)
                 INTO V_CANT_ACTIVAS
                 FROM TARJETAS_DESBLOQUEO TTD
                WHERE     TTD.CODCAR = I_CODCAR
                      AND TTD.NRO_DOC = I_NRO_DOC
                      AND TTD.NRO_CTA = I_NRO_CTA
                      AND (   (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_INICIO,
                                               'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                           OR (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')))
                      AND TTD.STATUS IN ('P', 'E', 'EC');



               SELECT TTD.COD_DESBLOQUEO
                 INTO O_ID_REGLA
                 FROM TARJETAS_DESBLOQUEO TTD
                WHERE     TTD.CODCAR = I_CODCAR
                      AND TTD.NRO_DOC = I_NRO_DOC
                      AND TTD.NRO_CTA = I_NRO_CTA
                      AND (   (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_INICIO,
                                               'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                           OR (    TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') >=
                                      TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                               AND TO_DATE (
                                      TO_CHAR (TTD.FECHA_FIN, 'dd/mm/yyyy'),
                                      'dd/mm/yyyy') <=
                                      TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')))
                      AND TTD.STATUS IN ('P', 'E', 'EC');
            END IF;
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            O_ID_REGLA := 'OK';
      END;


      IF (V_CANT_ACTIVAS = 0)
      THEN
         O_MENSAJE := 'OK';
      ELSE
         O_MENSAJE := 'NO OK REGLA';
      END IF;
   END PR_VALIDAR_FECHA_REGLA;


   PROCEDURE PR_VERIFICAR_TARJETAS
   IS
      V_BLOQUEO   VARCHAR2 (2) := '';
      MESSAGE     VARCHAR2 (300) := '';

      V_FASE      NUMBER := 0;
   BEGIN
      V_FASE := 1000;

      --SE CONSULTAN LAS REGLAS QUE SE MANDARON A DESBLOQUEAR
      FOR REG
         IN (SELECT COD_DESBLOQUEO,
                    CODCAR,
                    NRO_CTA,
                    NRO_DOC,
                    FECHA_INICIO,
                    FECHA_FIN,
                    STATUS
               FROM TARJETAS_DESBLOQUEO
              WHERE (    ENVIADO_DESBLOQUEO IS NOT NULL
                     AND VERIFICADO_DESBLOQUEO IS NULL
                     AND STATUS NOT IN ('I')))
      LOOP
         V_BLOQUEO := '';

         SELECT BLOQUEO
           INTO V_BLOQUEO
           FROM CTACAR_TEMP T
          WHERE     T.COD_CAR = REG.CODCAR
                AND T.NRO_DOC = REG.NRO_CTA
                AND T.NRO_PLASTICO = REG.NRO_DOC;

         --SI YA SE ENCUENTRA DESBLOQUEADA
         --SE ACTUALIZA LA REGLA A STATUS DE EJECUCION
         IF NVL (V_BLOQUEO, '@') = '@'
         THEN
            UPDATE TARJETAS_DESBLOQUEO
               SET STATUS = 'E',
                   FECHA_STATUS = SYSDATE,
                   VERIFICADO_DESBLOQUEO = SYSDATE
             WHERE COD_DESBLOQUEO = REG.COD_DESBLOQUEO;
         END IF;
      END LOOP;

      V_FASE := 2000;

      --SE CONSULTAN LAS REGLAS QUE SE MANDARON A BLOQUEAR
      FOR REG
         IN (SELECT COD_DESBLOQUEO,
                    CODCAR,
                    NRO_CTA,
                    NRO_DOC,
                    FECHA_INICIO,
                    FECHA_FIN,
                    STATUS
               FROM TARJETAS_DESBLOQUEO
              WHERE (    ENVIADO_BLOQUEO IS NOT NULL
                     AND VERIFICADO_BLOQUEO IS NULL
                     AND STATUS NOT IN ('I')))
      LOOP
         V_BLOQUEO := '';

         /*  Se presento el caso de una tarjeta con sit_doc = 4 que no estaba en la vista.
                  SELECT BLOQUEO
                    INTO V_BLOQUEO
                    FROM CTACAR_TEMP T
                   WHERE     T.COD_CAR = REG.CODCAR
                         AND T.NRO_DOC = REG.NRO_CTA
                         AND T.NRO_PLASTICO = REG.NRO_DOC;*/

         SELECT BLOQUE
           INTO V_BLOQUEO
           FROM MAS_TARJETA T
          WHERE     T.CODCAR = REG.CODCAR
                AND T.NRO_CTA = REG.NRO_CTA
                AND T.NRO_DOC = REG.NRO_DOC;

         --SI YA SE ENCUENTRA BLOQUEADA
         --SE ACTUALIZA LA REGLA A STATUS DE CULMINADA
         IF V_BLOQUEO = 'Q'
         THEN
            UPDATE TARJETAS_DESBLOQUEO
               SET STATUS = 'C',
                   FECHA_STATUS = SYSDATE,
                   VERIFICADO_BLOQUEO = SYSDATE
             WHERE COD_DESBLOQUEO = REG.COD_DESBLOQUEO;
         END IF;
      END LOOP;
 V_FASE := 3000;
      -- SE CULMINAN LAS REGLAS QUE NO SE ENVIARON EN EL ARCHIVO DE BLOQUEO
      -- PORQUE UNA REGLA COMENZABA EN EL SIGUIENTE DIA HABIL
      -- POR LO TANTO LA TARJETA PERMANECE ACTIVA
      UPDATE TARJETAS_DESBLOQUEO
         SET STATUS = 'C',
             FECHA_STATUS = SYSDATE,
             MOTIVO_STATUS =
                'SE CULMINA AUTOMATICAMENTE SIN ENVIAR A BLOQUEAR PORQUE EL DIA SIGUIENTE INICIA OTRO DESBLOQUEO'
       WHERE STATUS = 'EC';

 V_FASE := 4000;
      -- SE INACTIVAN LAS REGLAS CUYAS FECHAS PASARON Y NO SE PROCESARON
      UPDATE TARJETAS_DESBLOQUEO
         SET STATUS = 'I',
             FECHA_STATUS = SYSDATE,
             MOTIVO_STATUS =
                'SE INACTIVA AUTOMATICAMENTE PORQUE NO SE EJECUTO EN EL RANGO DE FECHAS PROGRAMADO'
       WHERE STATUS = 'P' AND FECHA_FIN < SYSDATE;
 V_FASE := 5000;
      -- SE CULMINAN TODAS LAS REGLAS QUE SE ENCUENTRAN EN ESTATUS EJECUCION CUYA FECHA FIN TRANSCURRIO
      -- PERO NO SE ENVIARON A BLOQUEAR (CON STATUS Q) DEBIDO A QUE EL ESTATUS DE LA TARJETA HABIA CAMBIADO
      -- POR OTRA VIA Y NO SE ENCUENTRAN NI ACTIVAS NI EN STATUS Q (QUE SON LOS UNICOS STATUS QUE PODEMOS CAMBIAR)
     /* FOR REG
         IN (SELECT D1.COD_DESBLOQUEO, T.BLOQUE
               FROM TARJETAS_DESBLOQUEO D1, MAS_TARJETA T
              WHERE     D1.FECHA_FIN < TRUNC(SYSDATE) --PIENSO QUE SE DEBE QUITAR ESA FECHA 
                    AND D1.STATUS = 'E'
                    AND (T.BLOQUE IS NOT NULL AND T.BLOQUE <> 'Q')
                    AND T.CODCAR = D1.CODCAR
                    AND T.NRO_CTA = D1.NRO_CTA
                    AND T.NRO_DOC = D1.NRO_DOC)
      LOOP
         UPDATE TARJETAS_DESBLOQUEO DD
            SET DD.STATUS = 'C',
                DD.FECHA_STATUS = SYSDATE,
                DD.MOTIVO_STATUS =
                   'SE CULMINA AUTOMATICAMENTE PORQUE SE CUMPLE LA FECHA FIN. '||
                   ' LA TDC NO SE ENVIO A BLOQUEAR EN EL ARCHIVO PORQUE SU STATUS ES: '|| REG.BLOQUE
          WHERE DD.COD_DESBLOQUEO = REG.COD_DESBLOQUEO;
      END LOOP;*/
      
      -- SE CULMINAN TODAS LAS REGLAS QUE SE ENCUENTRAN EN ESTATUS EJECUCION Y CUYO ESTATUS 
      -- DE LA TARJETA HABIA CAMBIADO POR OTRA VIA Y NO SE ENCUENTRAN NI ACTIVAS NI EN STATUS Q
      -- (QUE SON LOS UNICOS STATUS QUE PODEMOS CAMBIAR)
      FOR REG
         IN (SELECT D1.COD_DESBLOQUEO, T.BLOQUE
               FROM TARJETAS_DESBLOQUEO D1, MAS_TARJETA T
              WHERE   
                     D1.STATUS = 'E'
                    AND (T.BLOQUE IS NOT NULL AND T.BLOQUE <> 'Q')
                    AND T.CODCAR = D1.CODCAR
                    AND T.NRO_CTA = D1.NRO_CTA
                    AND T.NRO_DOC = D1.NRO_DOC)
      LOOP
         UPDATE TARJETAS_DESBLOQUEO DD
            SET DD.STATUS = 'C',
                DD.FECHA_STATUS = SYSDATE,
                DD.MOTIVO_STATUS =
                   'SE CULMINA AUTOMATICAMENTE AL STATUS QUE PRESENTA LA TDC. EL STATUS ES: '|| REG.BLOQUE
          WHERE DD.COD_DESBLOQUEO = REG.COD_DESBLOQUEO;
      END LOOP;  

      V_FASE := 6000;
      
-- sE COLOCAN EN EJECUCION TODAS LAS REGLAS QUE PARA LA FECHA LAS TDC YA SE ENCUENTRAN ACTIVAS
-- PASA CUANDO SON CARGADAS POR OPERACIONES EN EL BACKOFFICE Y FUERON ACTIVADAS POR EL OTRO SISTEMA
      FOR REG
         IN (SELECT D1.COD_DESBLOQUEO, T.BLOQUE
               FROM TARJETAS_DESBLOQUEO D1, MAS_TARJETA T
              WHERE     D1.FECHA_INICIO <= TRUNC(SYSDATE) 
              AND FECHA_FIN > TRUNC(SYSDATE) 
                    AND D1.STATUS = 'P'
                    AND (T.BLOQUE IS NULL )
                    AND T.CODCAR = D1.CODCAR
                    AND T.NRO_CTA = D1.NRO_CTA
                    AND T.NRO_DOC = D1.NRO_DOC)
      LOOP
         UPDATE TARJETAS_DESBLOQUEO DD
            SET DD.STATUS = 'E',
                DD.FECHA_STATUS = SYSDATE,
                DD.MOTIVO_STATUS =
                   'SE COLOCA EN EJECUCION PORQUE LA TARJETA YA SE ENCUENTRA ACTIVA.'
          WHERE DD.COD_DESBLOQUEO = REG.COD_DESBLOQUEO;
      END LOOP;          
   EXCEPTION
      WHEN OTHERS
      THEN
         MESSAGE :=
               'PR_VERIFICAR_TARJETAS <ERROR> 001099 FASE:'
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO PARA VERIFICAR STATUS DE TARJETAS <ERROR>'
            || SQLERRM;

         RAISE_APPLICATION_ERROR (-20099, MESSAGE);
   END;



   PROCEDURE PR_CARGAR_DETALLE_MOVIMIENTO (
      P_NUM_DOC     IN     VARCHAR2,
      P_TOTAL          OUT VARCHAR2,
      P_RESULTADO      OUT VARCHAR2,
      P_CURSOR         OUT SYS_REFCURSOR)
   AS
      TOTALABONOS          VARCHAR (200);
      TOTALCARGOS          VARCHAR (200);
      V_MES                VARCHAR2 (200);
      V_TIP_PAG_AUT        VARCHAR2 (200);
      V_CREDITO_DISP       VARCHAR2 (200);
      V_SAL_ANTERIOR       VARCHAR2 (200);
      V_TIP_PAG_AUT_DESC   VARCHAR2 (200);
      V_LIM_CREDITO        VARCHAR2 (200);
      V_NOMBRE_PLASTICO    VARCHAR2 (200);
   BEGIN
      BEGIN
         SELECT MAX (MES)
           INTO V_MES
           FROM EDO_CTA_TDC
          WHERE NUM_CTA_PLAST_PPAL = P_NUM_DOC;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            V_MES := '';
      END;



      BEGIN
         SELECT CREDITO_DISP, SAL_ANTERIOR, LIM_CREDITO
           INTO V_CREDITO_DISP, V_SAL_ANTERIOR, V_LIM_CREDITO
           FROM EDO_CTA_TDC
          WHERE NUM_CTA_PLAST_PPAL = P_NUM_DOC AND MES = V_MES;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            V_CREDITO_DISP := '';
            V_SAL_ANTERIOR := '';
            V_LIM_CREDITO := '';
      END;


      BEGIN
         SELECT ET.DESCRIPCION,
                TIP_PAG_AUT,
                DECODE (
                   NOMBRE_PLASTICO,
                   NULL, (SELECT DISTINCT (NOMBRE_PLASTICO)
                            FROM MAS_TARJETA
                           WHERE     TIPO_PLASTICO = 'P'
                                 AND NRO_CTA =
                                        SUBSTR (P_NUM_DOC, 1, 17) || '000'),
                   NOMBRE_PLASTICO)
           INTO V_TIP_PAG_AUT_DESC, V_TIP_PAG_AUT, V_NOMBRE_PLASTICO
           FROM ELEMENTOS_TIPOS ET, MAESTRO_TARJETAS MT
          WHERE     ET.CODELEMENTO = MT.TIP_PAG_AUT
                AND MT.NRO_PLASTICO = P_NUM_DOC
                AND ET.CODTIPO = '0000000006';
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            V_TIP_PAG_AUT_DESC := '';
      END;



      OPEN P_CURSOR FOR
         SELECT MASTAR.NRO_DOC,
                DECODE (MASTAR.NOMBRE_PLASTICO,
                        NULL, V_NOMBRE_PLASTICO,
                        MASTAR.NOMBRE_PLASTICO),
                DECODE (MASTAR.LIM_CTO, 0, V_LIM_CREDITO, MASTAR.LIM_CTO),
                MASTAR.FECHA_EXP,
                MASTAR.TELEFONO_HABITACION,
                MASTAR.EMAIL,
                V_TIP_PAG_AUT,
                V_TIP_PAG_AUT_DESC,
                MASTAR.DIR_L1 || ' ' || MASTAR.DIR_L2 || ' ' || MASTAR.DIR_L3,
                V_CREDITO_DISP,
                (SELECT TO_CHAR (FECHA_ULT_PAG, 'DD/MM/YYYY')
                   FROM MAS_TARJETA
                  WHERE NRO_DOC = MASTAR.NRO_CTA)
                   FECHA_ULT_PAG,
                PAGAUT.MTO_PAG,
                V_SAL_ANTERIOR,
                (SELECT TRUNC (
                             TO_DATE (SYSDATE, 'DD/MM/YYYY')
                           - TO_DATE (FECHA_ULT_PAG, 'DD/MM/YYYY'))
                   FROM MAS_TARJETA
                  WHERE NRO_DOC = MASTAR.NRO_CTA)
                   FECHA_ULT_PAG
           FROM MAS_TARJETA MASTAR, PAGOS_AUTOMATICOS_TDC PAGAUT
          WHERE     PAGAUT.NRO_DOC(+) = MASTAR.NRO_DOC
                AND MASTAR.NRO_DOC = P_NUM_DOC;

      /*       SELECT
              MASTAR.NRO_DOC
             ,DECODE(MASTAR.NOMBRE_PLASTICO,NULL,MASTAR.NOM_CLI,MASTAR.NOMBRE_PLASTICO)
             ,MASTAR.LIM_CTO
             ,MASTAR.FECHA_EXP
             ,MASTAR.TELEFONO_HABITACION
             ,MASTAR.EMAIL
             ,EDOCTA.TIP_PAG_AUT
             ,ET.DESCRIPCION TIP_PAG_AUT_DESC
             ,MASTAR.DIR_L1 || ' ' || MASTAR.DIR_L2 || ' ' || MASTAR.DIR_L3
             ,EDOCTA.CREDITO_DISP
             ,(SELECT TO_CHAR(FECHA_ULT_PAG,'DD/MM/YYYY') FROM MAS_TARJETA
                                           WHERE NRO_DOC=MASTAR.NRO_CTA) FECHA_ULT_PAG
             ,PAGAUT.MTO_PAG
             ,EDOCTA.SAL_ANTERIOR
             ,(SELECT TRUNC(TO_DATE(SYSDATE,'DD/MM/YYYY')-TO_DATE(FECHA_ULT_PAG,'DD/MM/YYYY')) FROM MAS_TARJETA
                                           WHERE NRO_DOC=MASTAR.NRO_CTA) FECHA_ULT_PAG
                 FROM
                     MAS_TARJETA MASTAR, ELEMENTOS_TIPOS ET,PAGOS_AUTOMATICOS_TDC PAGAUT, EDO_CTA_TDC EDOCTA
                     WHERE
                         EDOCTA.TIP_PAG_AUT=ET.CODELEMENTO
                         AND ET.CODTIPO='0000000006'
                         AND EDOCTA.NUM_CTA_PLAST_PPAL(+)=MASTAR.NRO_DOC
                         AND EDOCTA.MES=(SELECT MAX(MES) FROM EDO_CTA_TDC
                                           WHERE NUM_CTA_PLAST_PPAL=P_NUM_DOC)
                         AND PAGAUT.NRO_DOC(+)=MASTAR.NRO_DOC
                         AND MASTAR.NRO_DOC=P_NUM_DOC;*/



      BEGIN
         SELECT SUM (NVL (MTO_TRN_ABONO, 0)), SUM (NVL (MTO_TRN_CARGO, 0))
           INTO TOTALABONOS, TOTALCARGOS
           FROM VBT_TARJETA.DET_TRN_TDC_DIA_VU
          WHERE     NRO_DOC = P_NUM_DOC
                AND COD_TRN IN
                       ('10',
                        '12',
                        '14',
                        '16',
                        '24',
                        '25',
                        '26',
                        '27',
                        '28',
                        '30',
                        '32',
                        '34',
                        '40',
                        '42',
                        '46',
                        '48',
                        '51',
                        '60',
                        '61',
                        '62',
                        '96',
                        '11',
                        '13',
                        '15',
                        '17',
                        '20',
                        '21',
                        '22',
                        '23',
                        '31',
                        '33',
                        '41',
                        '43',
                        '49',
                        '50');
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            TOTALABONOS := 0;
            TOTALCARGOS := 0;
      END;


      IF (TOTALCARGOS > TOTALABONOS)
      THEN
         P_TOTAL := TOTALCARGOS - TOTALABONOS;
      ELSE
         P_TOTAL := -1 * (TOTALABONOS - TOTALCARGOS);
      END IF;



      P_RESULTADO := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         P_RESULTADO := SUBSTR (SQLERRM, 1, 300);
   END PR_CARGAR_DETALLE_MOVIMIENTO;


   PROCEDURE PR_GET_TRACE_AUDIT (P_TRACE_NUMBER OUT VARCHAR2)
   AS
   BEGIN
      SELECT LPAD (SEQ_TRACE_AUDIT.NEXTVAL, 6, 0)
        INTO P_TRACE_NUMBER
        FROM DUAL;
   END PR_GET_TRACE_AUDIT;



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
        P_rafaga                            IN VARCHAR2)
   AS
   BEGIN
        INSERT INTO OPERACION_BTRANS_PAGOS_TDC (COD_PAGO,
                                                TIPO_MENSAJE,
                                                PROCESSING_CODE,
                                                AMOUNT,
                                                AMOUNT_SETTLEMENT,
                                                TRANSMISSION_DATETIME,
                                                AUDIT_NUMBER,
                                                TIME_LOCAL_TRANSACTION,
                                                DATE_LOCAL_TRANSACTION,
                                                DATE_EXPIRATION,               
                                                DATE_CAPTURE,
                                                MERCHANT_TYPE,
                                                POINT_OF_SERVICE_ENTRY_MODE,
                                                POINT_OF_SERVICE_CONDITION_COD,
                                                ACQUIRING_INSTITUTION_CODE,
                                                TRACK2,
                                                RETRIEVAL_REFERENCE_NUMBER,
                                                AUTORIZATION_ID_REVERSAL,
                                                RESPONSE_CODE_REVERSAL,
                                                CARD_ACCEPTOR_TERMINAL_ID,
                                                CARD_ACCEPTOR_ID_CODE,
                                                CARD_ACCEPTOR_NAME_LOCATION,
                                                ADDITIONAL_DATA_PRIVATE,
                                                COUNTRY_CODE,
                                                PERSONAL_ID_CODE,
                                                ADVICE_REASON_CODE,
                                                PRIVATE,
                                                ORIGINAL_DATA_REVERSAL,
                                                CVV,
                                                NAME_LOCATION,
                                                RESERVE_FOR_PRIVATE_USE,
                                                NETWORK_MANAGEMENT_INFO,
                                                RAFAGA_ORIGINAL)
            VALUES (P_COD_PAGO,
                    P_TIPO_MENSAJE,
                    P_PROCESSING_CODE,
                    P_AMOUNT,
                    P_AMOUNT_SETTLEMENT,
                    P_TRANSMISSION_DATETIME,
                    P_AUDIT_NUMBER,
                    P_TIME_LOCAL_TRANSACTION,
                    P_DATE_LOCAL_TRANSACTION,
                    P_DATE_EXPIRATION,               
                    P_DATE_CAPTURE,
                    P_MERCHANT_TYPE,
                    P_POINT_OF_SERVICE_ENTRY_MODE,
                    P_POINT_OF_SERVICE_COND_COD,
                    P_ACQUIRING_INSTITUTION_CODE,
                    P_TRACK2,
                    P_RETRIEVAL_REFERENCE_NUMBER,
                    P_AUTORIZATION_ID_REVERSAL,
                    P_RESPONSE_CODE_REVERSAL,
                    P_CARD_ACCEPTOR_TERMINAL_ID,
                    P_CARD_ACCEPTOR_ID_CODE,
                    P_CARD_ACCEPTOR_NAME_LOCATION,
                    P_ADDITIONAL_DATA_PRIVATE,
                    P_COUNTRY_CODE,
                    P_PERSONAL_ID_CODE,
                    P_ADVICE_REASON_CODE,
                    P_PRIVATE,
                    P_ORIGINAL_DATA_REVERSAL,
                    P_CVV,
                    P_NAME_LOCATION,
                    P_RESERVE_FOR_PRIVATE_USE,
                    P_NETWORK_MANAGEMENT_INFO,
                    P_RAFAGA);
                    
   END PR_GUARDAR_BTRANS_PAGOS_TDC;



   PROCEDURE PR_HISTORICO_PAGOS (P_CARTERA       IN     VARCHAR2,
                                 P_NUM_CTA       IN     VARCHAR2,
                                 P_NUM_DOC       IN     VARCHAR2,
                                 P_FECHA_DESDE   IN     VARCHAR2,
                                 P_FECHA_HASTA   IN     VARCHAR2,
                                 P_ESTATUS       IN     VARCHAR2,
                                 P_RESULTADO     OUT    VARCHAR2,
                                 P_CURSOR        OUT    SYS_REFCURSOR)
   AS
   BEGIN
   
    /* IF P_ESTATUS = 'C' THEN
     
      OPEN P_CURSOR FOR
         SELECT TO_CHAR (FEC_REG, 'DD/MM/YYYY'),
                TO_CHAR (FEC_PRO, 'DD/MM/YYYY'),
                TIPO_PAGO,
              --  MONTO,
                TRIM(TO_CHAR(MONTO,'999,999,999,999,999.99')) MONTO,
                ESTATUS_PAGO,
                CODCOL
           FROM PAGOS_ONLINE_TDC
          WHERE     CODCAR = P_CARTERA
                AND NRO_CTA = P_NUM_CTA
                AND NRO_DOC = P_NUM_DOC
                AND ESTATUS_PAGO IN ('C','A');
               -- AND TO_DATE(TO_CHAR(FEC_REG,'dd/mm/yyyy'),'dd/mm/yyyy') >= TO_DATE(P_FECHA_DESDE,'dd/mm/yyyy')
               -- AND TO_DATE(TO_CHAR(FEC_REG,'dd/mm/yyyy'),'dd/mm/yyyy') <= TO_DATE(P_FECHA_HASTA,'dd/mm/yyyy');
      ELSE */
            OPEN P_CURSOR FOR
             SELECT TO_CHAR (FEC_REG, 'DD/MM/YYYY'),
                    TO_CHAR (FEC_PRO, 'DD/MM/YYYY'),
                    TIPO_PAGO,
                    --MONTO,
                    TO_CHAR(MONTO,'999,999,999,999,999.99') MONTO,
                    ESTATUS_PAGO,
                    CODCOL,
                    CODCAR
               FROM PAGOS_ONLINE_TDC
              WHERE     CODCAR = P_CARTERA
                    AND NRO_CTA = P_NUM_CTA
                    AND NRO_DOC = P_NUM_DOC
                  --  AND ESTATUS_PAGO NOT IN('C','A')
                    AND TO_DATE(TO_CHAR(FEC_REG,'dd/mm/yyyy'),'dd/mm/yyyy') >= TO_DATE(P_FECHA_DESDE,'dd/mm/yyyy')
                    AND TO_DATE(TO_CHAR(FEC_REG,'dd/mm/yyyy'),'dd/mm/yyyy') <= TO_DATE(P_FECHA_HASTA,'dd/mm/yyyy')
             ORDER BY FEC_REG DESC;
   --   END IF;


      P_RESULTADO := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         P_RESULTADO := SUBSTR (SQLERRM, 1, 300);
   END PR_HISTORICO_PAGOS;


   PROCEDURE PR_CARGAR_PAGOS_TDC (P_STATUS   IN     VARCHAR2,
                                  P_CURSOR      OUT SYS_REFCURSOR)
   AS
   BEGIN
      OPEN P_CURSOR FOR
         SELECT COD_PAGO,
                NRO_CTA,
                NRO_DOC,
                CODCAR,
                TIPO_PAGO,
                TO_CHAR (FEC_REG, 'DD/MM/YYYY'),
                TO_CHAR (FEC_PRO, 'DD/MM/YYYY'),
                CODMON,
                MONTO,
                --TO_CHAR(MONTO,'999,999,999,999,999.99') MONTO,

                ESTATUS_PAGO
           FROM PAGOS_ONLINE_TDC
          WHERE ESTATUS_PAGO = P_STATUS;
   END PR_CARGAR_PAGOS_TDC;


   PROCEDURE PR_CAMBIAR_STATUS_PAGO (P_COD_PAGO         IN      VARCHAR2,
                                     P_STATUS           IN      VARCHAR2,
                                     P_INTENTOS         IN      VARCHAR2,
                                     P_COD_RESPUESTA    IN      VARCHAR2,
                                     P_RESULTADO        OUT     VARCHAR2)
   AS
   BEGIN
   
  
          UPDATE PAGOS_ONLINE_TDC 
             SET ESTATUS_PAGO = P_STATUS,
                 FEC_PRO = SYSDATE,
                 NRO_INTENTOS = P_INTENTOS,
                 COD_RESPUESTA = P_COD_RESPUESTA
           WHERE COD_PAGO = P_COD_PAGO;
 
           
      

      P_RESULTADO := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         P_RESULTADO := 'NO OK';
   END PR_CAMBIAR_STATUS_PAGO;
   
   
   PROCEDURE PR_RESPUESTA_BTRANS_PAGO ( P_RESPONSE_TRANS_DATETIME         IN VARCHAR2,     
                                                P_RESPONSE_TRACE_AUDIT_NUMBER     IN VARCHAR2,
                                                P_RESPONSE_TIME_LOCAL_TRANS       IN VARCHAR2,   
                                                P_RESPONSE_DATE_LOCAL_TRANS       IN VARCHAR2,   
                                                P_RESPONSE_RETRIVAL_REF_NUM       IN VARCHAR2,  
                                                P_RESPONSE_AUTORIZATION_ID        IN VARCHAR2,   
                                                P_RESPONSE_CODE                   IN VARCHAR2,   
                                                P_AUDIT_NUMBER                    IN VARCHAR2,   
                                                P_COD_PAGO                        IN VARCHAR2,
                                                P_RESULTADO                     OUT VARCHAR2)
   AS
   BEGIN
      UPDATE OPERACION_BTRANS_PAGOS_TDC
            SET RESPONSE_TRANSMISSION_DATETIME=P_RESPONSE_TRANS_DATETIME,        
                RESPONSE_TRACE_AUDIT_NUMBER=P_RESPONSE_TRACE_AUDIT_NUMBER, 
                RESPONSE_TIME_LOCAL_TRANS=P_RESPONSE_TIME_LOCAL_TRANS,   
                RESPONSE_DATE_LOCAL_TRANS=P_RESPONSE_DATE_LOCAL_TRANS,    
                RESPONSE_RETRIEVAL_REF_NUMBER=P_RESPONSE_RETRIVAL_REF_NUM,  
                RESPONSE_AUTORIZATION_ID=P_RESPONSE_AUTORIZATION_ID,       
                RESPONSE_CODE=P_RESPONSE_CODE                 
       WHERE COD_PAGO = P_COD_PAGO AND AUDIT_NUMBER=P_AUDIT_NUMBER;
  

      P_RESULTADO := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         P_RESULTADO := 'NO OK';
   END PR_RESPUESTA_BTRANS_PAGO;
   
   
  PROCEDURE PR_CONSULTAR_PAGOS_TDC (P_CODCAR            IN     VARCHAR2,
                                    P_NUM_CTA           IN     VARCHAR2,
                                    P_NUM_DOC           IN     VARCHAR2,
                                    P_FECHA_DESDE       IN     VARCHAR2,
                                    P_FECHA_HASTA       IN     VARCHAR2,
                                    P_LISTA_PAGOS        OUT SYS_REFCURSOR,
                                    P_RESPUESTA              OUT VARCHAR2)
   IS
      SQLSTRING           VARCHAR2 (3000) := '';

   BEGIN
   

      SQLSTRING :='SELECT  PG.COD_PAGO,
        PG.NRO_CTA,
        PG.NRO_DOC,
        PG.CODCAR,
        ET.DESCRIPCION TIPO_PAGO,
        TO_CHAR(PG.FEC_REG, ''DD/MM/YYYY'') FECHA_REGISTRO,
        TO_CHAR(PG.FEC_PRO, ''DD/MM/YYYY'') FECHA_PROCESO,
        PG.CODMON,
        PG.MONTO,
        ET2.DESCRIPCION ESTATUS_PAGO,
        PG.NRO_INTENTOS
            FROM PAGOS_ONLINE_TDC PG, ELEMENTOS_TIPOS ET, ELEMENTOS_TIPOS ET2
                    WHERE PG.TIPO_PAGO=ET.CODELEMENTO
                          AND ET.CODTIPO=''0000000007'' 
                          AND PG.ESTATUS_PAGO=ET2.CODELEMENTO
                          AND ET2.CODTIPO=''0000000008''
                          AND PG.NRO_CTA='''||P_NUM_CTA||'''
                          AND PG.NRO_DOC='''||P_NUM_DOC||'''';
                          
                          
        IF P_FECHA_DESDE IS NOT NULL AND P_FECHA_HASTA IS NOT NULL THEN
                        
            SQLSTRING:= SQLSTRING || ' AND     TO_DATE(TO_CHAR(PG.FEC_REG,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||P_FECHA_DESDE||''',''dd/mm/yyyy'') 
                                       AND     TO_DATE(TO_CHAR(PG.FEC_REG,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||P_FECHA_HASTA||''',''dd/mm/yyyy'')';
            
        END IF;                   
                          
                          

      OPEN P_LISTA_PAGOS FOR SQLSTRING;

      P_RESPUESTA:='OK';
    
   END PR_CONSULTAR_PAGOS_TDC;


 PROCEDURE PR_DETALLE_PAGOS_TDC   (P_CODPAGO           IN     VARCHAR2,
                                   P_LISTA_DETALLE     OUT SYS_REFCURSOR,
                                   P_RESPUESTA         OUT VARCHAR2)
   IS
      SQLSTRING           VARCHAR2 (3000) := '';

   BEGIN
   

      SQLSTRING :='SELECT  TO_CHAR(PG.FECHA_REGISTRO, ''DD/MM/YYYY'') FECHA_REGISTRO,         
                           PG.RESPONSE_AUTORIZATION_ID,       
                           ER.DESCRIPCION
                    FROM OPERACION_BTRANS_PAGOS_TDC PG, RESPONSE_BTRANS_PAGOS_TDC ER 
                    WHERE PG.RESPONSE_CODE=ER.CODIGO
                          AND PG.COD_PAGO='''||P_CODPAGO||'''';
                          
               

      OPEN P_LISTA_DETALLE FOR SQLSTRING;

      P_RESPUESTA:='OK';
    
   END PR_DETALLE_PAGOS_TDC;
   
   
  
   PROCEDURE PR_GUARDAR_PAGO_TDC(P_CODCAR        IN VARCHAR2,     
                               P_NRO_CTA         IN VARCHAR2,
                               P_NRO_DOC         IN VARCHAR2,
                               P_TIPO_PAGO       IN VARCHAR2,
                               P_MONTO           IN VARCHAR2,
                               P_NRO_CTA_DEBITO  IN VARCHAR2,
                               P_BALANCE_TDC     IN VARCHAR2,
                               P_CODBOFA     IN VARCHAR2,
                               P_RESULTADO       OUT VARCHAR2,
                               P_USUARIO IN VARCHAR2)
  AS
  
  V_monto    PAGOS_ONLINE_TDC.MONTO%TYPE := TO_NUMBER (P_MONTO);
  V_BALANCE_TDC    PAGOS_ONLINE_TDC.MONTO%TYPE := TO_NUMBER (P_BALANCE_TDC);

   BEGIN
   
              INSERT INTO PAGOS_ONLINE_TDC (COD_PAGO,      
                                            NRO_CTA,      
                                            NRO_DOC,      
                                            CODCAR,       
                                            TIPO_PAGO,    
                                            FEC_REG,          
                                            CODMON,       
                                            MONTO,        
                                            ESTATUS_PAGO, 
                                            NRO_INTENTOS, 
                                            SALDO_ACTUAL, 
                                        
                                            USUARIO,
                                            BOF00_CODMOV,
                                            CODEMP,
                                            CODCOL,
                                            CODINST)
                                     VALUES(SEQ_PAGO_TDC.NEXTVAL,
                                            P_NRO_CTA,
                                            P_NRO_DOC,
                                            P_CODCAR,
                                            P_TIPO_PAGO,
                                            SYSDATE,
                                            'USD',
                                            V_monto,
                                            'C',
                                            0, 
                                            V_BALANCE_TDC,
                                       
                                            P_USUARIO,
                                            P_CODBOFA,
                                            '0000009539',
                                            P_NRO_CTA_DEBITO,
                                            'CAH');
        P_RESULTADO:='OK';                                  
   
   END PR_GUARDAR_PAGO_TDC;
   
END PCK_TARJETA;
/


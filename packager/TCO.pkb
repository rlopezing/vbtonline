CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.TCO
AS
   --CONSULTA DE STATUS DE LA TARJETA DE COORDENADAS
   PROCEDURE VBT_PR_GET_ESTATUS (IN_LOGIN        IN     VARCHAR2,
                                 IN_SERIAL       IN     VARCHAR2,
                                 IN_USUARIO      IN     VARCHAR2,
                                 IN_IP           IN     VARCHAR2,
                                 IN_PROCESO      IN     VARCHAR2,
                                 OUT_MENSAJE        OUT VARCHAR2,
                                 OUT_RESULTADO      OUT VARCHAR2)
   IS
      V_FECHA_ASI     DATE;
      V_ESTATUS       TCO_SERIAL_ASIGNACION.ESTATUS%TYPE;
      --
      LHORASACT       PLS_INTEGER := 24;
      V_VENCIMIENTO   PLS_INTEGER := 0;
      --
      V_CONT          PLS_INTEGER := 0;
      --
      V_FASE          PLS_INTEGER := 0;
      V_PROCESO       VARCHAR2 (6) := IN_PROCESO;
      MESSAGE         VARCHAR2 (600);
      NO_VALIDO       EXCEPTION;
   BEGIN
      --resultados:
      --SecurityCard_MSG0=Su tarjeta de coordenadas ha sido activada satisfactoriamente
      --SecurityCard_MSG1=No posee una tarjeta de coordenadas válida para activación, debe generar alguna
      --SecurityCard_MSG2=Tarjeta de coordenadas no válida para activación, debe generar alguna
      --SecurityCard_MSG3=Su tarjeta de coordenadas se encuentra activa
      --SecurityCard_MSG4=El tiempo disponible para la activación de su tarjeta de coordenadas expiró. Recuerde que una vez generada debe activarla en un plazo de 24 horas. Se le agradece volver a generar otra tarjeta.
      --SecurityCard_MSG5=Su tarjeta de coordenadas es valida para ser activada
      --SecurityCard_MSG6=Su tarjeta de coordenadas se encuentra bloqueada
      --SecurityCard_MSG7=Su tarjeta de coordenadas se encuentra vencida
      --SecurityCard_MSG10=Error consultando tarjeta de coordenadas.

      --Se verifica que el usuario tenga tarjeta de coordenadas vàlidas para activación,deberìa ser una nada mas
      OUT_RESULTADO := 0;
      OUT_MENSAJE := 'TARJETA ACTIVADA EXITOSAMENTE';
      V_CONT := 0;

      IF V_PROCESO = 'L'                                --VALIDACION POR login
      THEN
         BEGIN
            SELECT COUNT (LOGIN)
              INTO V_CONT
              FROM TCO_SERIAL_ASIGNACION A
             WHERE A.LOGIN = IN_LOGIN;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               V_CONT := 0;
            WHEN OTHERS
            THEN
               OUT_RESULTADO := '10';
               OUT_MENSAJE := 'ERROR CONSULTANDO TARJETA DE COORDENADAS';
               MESSAGE :=
                  'ERROR CONSULTANDO TARJETA DE COORDENADAS ' || SQLERRM;
               RAISE NO_VALIDO;
         END;

         --DBMS_OUTPUT.PUT_LINE ('contador:' || V_CONT);

         IF V_CONT = 0
         THEN
            OUT_RESULTADO := '1';
            OUT_MENSAJE :=
               'NO POSEE TARJETA DE COORDENADAS ACTIVA NI VALIDA PARA ACTIVACION';
         ELSE
            BEGIN
               SELECT A.FECHA_ASI,
                      A.ESTATUS,
                        TO_DATE (A.FECHA_ASI + M.VENCIMIENTO, 'dd/mm/YY')
                      - TO_DATE (SYSDATE, 'dd/mm/YY')
                 INTO V_FECHA_ASI, V_ESTATUS, V_VENCIMIENTO
                 FROM TCO_SERIAL_ASIGNACION A, TCO_SERIAL_MAESTRO M
                WHERE     A.SERIAL = M.SERIAL
                      AND A.LOGIN = IN_LOGIN
                      AND A.SERIAL = SEGURIDAD.ENCRIPTA (IN_SERIAL);
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  NULL;
               WHEN OTHERS
               THEN
                  OUT_RESULTADO := '11';
                  OUT_MENSAJE := 'ERROR ACTIVANDO LA TARJETA DE COORDENADAS';
                  MESSAGE :=
                     'ERROR ACTIVANDO LA TARJETA DE COORDENADAS ' || SQLERRM;
                  RAISE NO_VALIDO;
            END;
         END IF;
      ELSIF V_PROCESO = 'S'                            --VALIDACION POR SERIAL
      THEN
         BEGIN
            SELECT A.FECHA_ASI,
                   A.ESTATUS,
                     TO_DATE (A.FECHA_ASI + M.VENCIMIENTO, 'dd/mm/YY')
                   - TO_DATE (SYSDATE, 'dd/mm/YY')
              INTO V_FECHA_ASI, V_ESTATUS, V_VENCIMIENTO
              FROM TCO_SERIAL_ASIGNACION A, TCO_SERIAL_MAESTRO M
             WHERE     A.SERIAL = M.SERIAL
                   AND A.SERIAL = SEGURIDAD.ENCRIPTA (IN_SERIAL);
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               NULL;
            WHEN OTHERS
            THEN
               OUT_RESULTADO := '11';
               OUT_MENSAJE := 'ERROR CONSULTANDO LA TARJETA DE COORDENADAS';
               MESSAGE :=
                  'ERROR CONSULTANDO LA TARJETA DE COORDENADAS ' || SQLERRM;
               RAISE NO_VALIDO;
         END;
      END IF;

      --En caso de encontrarse la tarjeta se hacen las validaciones neesarias para activarla
      IF OUT_RESULTADO = 0
      THEN
         --Se trae el valor de las horas de activación
         BEGIN
            SELECT TO_NUMBER (DESCRIPCION)
              INTO LHORASACT
              FROM CODIGOS_INTERNOS
             WHERE     TIPO_CODIGO = 'TCO'
                   AND CODIGO = 'LHORASACT'
                   AND STATUS = 'A';
         EXCEPTION
            WHEN OTHERS
            THEN
               LHORASACT := 24;
         END;

         --Validacion de estatus
         IF V_ESTATUS = 'BL'
         THEN
            OUT_RESULTADO := 6;
            OUT_MENSAJE :=
               'LA TARJETA DE COORDENADAS ASIGNADA SE ENCUENTRA BLOQUEADA';
         ELSIF V_ESTATUS != 'AS'
         THEN
            OUT_RESULTADO := 2;
            OUT_MENSAJE := 'TARJETA DE COORDENADAS NO VALIDA PARA ACTIVACIÓN';
         END IF;

         --Validacion de estatus
         IF V_ESTATUS = 'AS'
         THEN
            OUT_RESULTADO := 5;
            OUT_MENSAJE := 'TARJETA DE COORDENADAS VALIDA PARA SER ACTIVADA';
         END IF;

         --Validacion de horas desde que se asignò la tarjeta (generación)
         IF V_FECHA_ASI + (LHORASACT / 24) < SYSDATE
         THEN
            OUT_RESULTADO := 4;
            OUT_MENSAJE :=
                  'LA TARJETA DE COORDENADAS HA EXCEDIDO EL TIEMPO DE ACTIVACIÓN DE '
               || LHORASACT
               || ' HORAS';
         END IF;

         --Validacion de estatus
         IF V_ESTATUS = 'AC'
         THEN
            IF V_VENCIMIENTO < 0
            THEN
               OUT_RESULTADO := 7;
               OUT_MENSAJE := 'LA TARJETA DE COORDENADAS SE ENCUENTRA VENCIDA';
            ELSE
               OUT_RESULTADO := 3;
               OUT_MENSAJE := 'LA TARJETA DE COORDENADAS SE ENCUENTRA ACTIVA';
            END IF;
         END IF;
      END IF;      /*
   BEGIN
   DECLARE
      OUT_MENSAJE     VARCHAR2 (300);
      OUT_RESULTADO   VARCHAR2 (10) := '';
      V_SERIAL        VARCHAR2 (30) := '718293816874';
      --V_SERIAL        VARCHAR2 (30) := '613924925773';
   BEGIN
      --DBMS_OUTPUT.ENABLE (1000000);
      PR_GET_ESTATUS ('mbernot',
                          V_SERIAL,
                          'mbernot',
                          '',
                          OUT_MENSAJE,
                          OUT_RESULTADO);

      DBMS_OUTPUT.PUT_LINE (
         'Mensaje:' || OUT_MENSAJE );
         DBMS_OUTPUT.PUT_LINE (
         'Resultado:' || OUT_RESULTADO);
   END;
END;

   */
   EXCEPTION
      WHEN NO_VALIDO
      THEN
         ROLLBACK;
         MESSAGE := 'VBT_PR_GET_ESTATUS<ERROR>' || MESSAGE || '<ERROR> ';
         PR_INS_ERRORES (IN_USUARIO,
                         IN_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
         RAISE_APPLICATION_ERROR (-20002, MESSAGE);
      WHEN OTHERS
      THEN
         ROLLBACK;
         MESSAGE :=
               'VBT_PR_GET_ESTATUS <ERROR> 001099 FASE:'
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO PARA OBTENER STATUS TARJETA DE COORDENADAS<ERROR>'
            || SQLERRM;
         PR_INS_ERRORES (IN_USUARIO,
                         IN_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
         RAISE_APPLICATION_ERROR (-20099, MESSAGE);
   END VBT_PR_GET_ESTATUS;

   --CONSULTAS DE TARJETAS DE COORDENADAS
   PROCEDURE VBT_PR_CONSULTAS (IN_SERIAL            IN OUT VARCHAR2,
                               IN_LOGIN             IN     VARCHAR2,
                               IN_OPERACION         IN     VARCHAR2,
                               IN_SERIAL_PANTALLA      OUT VARCHAR2,
                               IN_STATUS               OUT VARCHAR2,
                               P_VBT_TARJETA           OUT SYS_REFCURSOR,
                               P_RESULTADO             OUT VARCHAR2)
   IS
      V_SERIAL            TCO_SERIAL_MAESTRO.SERIAL%TYPE := IN_SERIAL;
      V_SERIAL_PANTALLA   TCO_SERIAL_MAESTRO.SERIAL%TYPE := IN_SERIAL;
      V_LOGIN             TCO_SERIAL_ASIGNACION.LOGIN%TYPE := IN_LOGIN;
      V_OPERACION         VARCHAR2 (2) := UPPER (IN_OPERACION);
      SQLSTRING           VARCHAR2 (2000);
      MESSAGE             VARCHAR2 (600);
      V_MENSAJE           VARCHAR2 (300) := '';
   BEGIN
      --p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));

      IF V_OPERACION = 'CD'            --Consulta las cordenadas de la tarjeta
      THEN
         --  OPEN cs_vbt_cuentas_edo_cuenta FOR
         SQLSTRING :=
               'SELECT  SEGURIDAD.DESENCRIPTA(fila) FILA 
                    ,SEGURIDAD.DESENCRIPTA(columna) COLUMNA 
                    ,SEGURIDAD.DESENCRIPTA(valor) VALOR 
                    FROM    tco_serial_detalle 
                    WHERE   serial = SEGURIDAD.ENCRIPTA('
            || V_SERIAL
            || ') 
                    ORDER BY fila, columna';

         OPEN P_VBT_TARJETA FOR SQLSTRING;

         --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;
         P_RESULTADO := '0';
      ELSIF V_OPERACION = 'CM'                --Consulta maestro de la tarjeta
      THEN
         SQLSTRING :=
               'SELECT usuario_inc 
                   ,TO_CHAR(fecha_inc,''dd/mm/yyyy'') fecha_inc 
                   ,ip_inc 
                   , TO_CHAR(to_date(fecha_inc + vencimiento ,''dd/mm/YY''),''dd/mm/YYYY'')  
                   
            FROM   tco_serial_maestro 
            WHERE  serial = SEGURIDAD.ENCRIPTA('
            || V_SERIAL
            || ')';

         OPEN P_VBT_TARJETA FOR SQLSTRING;



         --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;
         P_RESULTADO := '0';
      ELSIF V_OPERACION = 'C'                           --Consulta por usuario
      THEN
         BEGIN
            --OPEN p_vbt_tarjeta FOR
            SELECT    '*********'
                   || SUBSTR (SEGURIDAD.DESENCRIPTA (A.SERIAL), -4),
                   SEGURIDAD.DESENCRIPTA (A.SERIAL),
                   A.ESTATUS
              INTO IN_SERIAL_PANTALLA, IN_SERIAL, IN_STATUS
              FROM TCO_SERIAL_ASIGNACION A
             WHERE     A.LOGIN = V_LOGIN
                   AND (A.ESTATUS = 'AS' OR A.ESTATUS = 'AC');


            --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;
            VBT_PR_GET_ESTATUS (V_LOGIN,
                                IN_SERIAL,
                                V_LOGIN,
                                '',
                                'L',                               --por login
                                V_MENSAJE,
                                P_RESULTADO);
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               IN_SERIAL_PANTALLA := ' ';
               IN_SERIAL := ' ';
               P_RESULTADO := 1;
         END;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         P_RESULTADO := 10;
   END VBT_PR_CONSULTAS;

   --GENERACION DE TARJETA DE COORDENADAS
   PROCEDURE VBT_PR_GENERACION (IN_SERIAL        IN OUT VARCHAR2,
                                IN_LOGIN         IN     VARCHAR2,
                                IN_CODCAR        IN     VARCHAR2,
                                IN_VENCIMIENTO   IN     VARCHAR2,
                                IN_USUARIO       IN     VARCHAR2,
                                IN_IP            IN     VARCHAR2,
                                OUT_RESULTADO       OUT VARCHAR2)
   IS
      V_SERIAL               TCO_SERIAL_MAESTRO.SERIAL%TYPE := IN_SERIAL;
      V_LOGIN                TCO_SERIAL_ASIGNACION.LOGIN%TYPE := IN_LOGIN;
      V_CODCAR               TCO_SERIAL_ASIGNACION.CODCAR%TYPE := IN_CODCAR;
      V_VENCIMIENTO          TCO_SERIAL_MAESTRO.VENCIMIENTO%TYPE
                                := TO_NUMBER (IN_VENCIMIENTO);
      V_USUARIO              VARCHAR2 (30) := UPPER (IN_USUARIO);
      V_IP                   VARCHAR2 (16) := IN_IP;
      --
      V_SEMILLA              NUMBER (9);
      V_SERIAL_GENERADO      NUMBER (15) := 0;
      V_FILAS_GENERADAS      VARCHAR2 (10) := '';
      V_COLUMNAS_GENERADAS   VARCHAR2 (10) := '';
      V_VALOR_GENERADO       VARCHAR2 (5) := '';
      LFILA                  PLS_INTEGER := 5;
      LCOLUMNA               PLS_INTEGER := 8;
      LVALOR                 PLS_INTEGER := 3;
      LVENCIMIENTO           PLS_INTEGER := 730;
      LHORASACT              PLS_INTEGER := 24;
      NINICIO                NUMBER (9) := 0;
      NFIN                   NUMBER (9) := 0;
      CFILA                  PLS_INTEGER := 0;
      CCOLUMNA               PLS_INTEGER := 0;
      CHFILA                 VARCHAR2 (1) := '';
      CHCOLUMNA              VARCHAR2 (1) := '';
      V_FECHA_ASI            TCO_SERIAL_ASIGNACION.FECHA_ASI%TYPE;
      V_ESTATUS              TCO_SERIAL_ASIGNACION.ESTATUS%TYPE;
      --
      V_EXISTE               BOOLEAN;
      V_EXISTE_N             PLS_INTEGER;
      V_CONT                 PLS_INTEGER;
      MESSAGE                VARCHAR2 (600);
      NO_VALIDO              EXCEPTION;
      V_FASE                 NUMBER := 0;
   BEGIN
      --Procedimiento para Generar una Tarjeta de coordenadas:

      V_FASE := 100;


      --inactivación de la tarjeta de coordenadas.
      BEGIN
         UPDATE TCO_SERIAL_ASIGNACION
            SET ESTATUS = 'BL',
                USUARIO_BLO = V_USUARIO,
                FECHA_BLO = SYSDATE,
                IP_BLO = V_IP
          WHERE LOGIN = V_LOGIN AND ESTATUS NOT IN ('BL');
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR INACTIVANDO TARJETA DE COORDENADAS ' || SQLERRM;
            RAISE NO_VALIDO;
      END;

      --ser buscan los valores de las longitus
      BEGIN
           SELECT TO_NUMBER (DESCRIPCION)
             INTO LFILA
             FROM CODIGOS_INTERNOS
            WHERE     TIPO_CODIGO = 'TCO'
                  AND CODIGO = 'LFILA'
                  AND STATUS = 'A'
                  AND ROWNUM = 1
         ORDER BY ORDEN;

         V_FASE := 300;

         IF LFILA > 9
         THEN
            V_FASE := 400;
            LFILA := 5;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;

      V_FASE := 500;

      BEGIN
         V_FASE := 600;

           SELECT TO_NUMBER (DESCRIPCION)
             INTO LCOLUMNA
             FROM CODIGOS_INTERNOS
            WHERE     TIPO_CODIGO = 'TCO'
                  AND CODIGO = 'LCOLUMNA'
                  AND STATUS = 'A'
                  AND ROWNUM = 1
         ORDER BY ORDEN;

         V_FASE := 700;

         IF LCOLUMNA > 9
         THEN
            V_FASE := 800;
            LCOLUMNA := 8;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;

      V_FASE := 900;

      BEGIN
         V_FASE := 1000;

         SELECT TO_NUMBER (DESCRIPCION)
           INTO LVALOR
           FROM CODIGOS_INTERNOS
          WHERE TIPO_CODIGO = 'TCO' AND CODIGO = 'LVALOR' AND STATUS = 'A';

         V_FASE := 1100;

         IF LVALOR > 3
         THEN
            V_FASE := 1200;
            LVALOR := 3;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            NULL;
      END;

      V_FASE := 1200;

      BEGIN
         SELECT TO_NUMBER (DESCRIPCION)
           INTO LVENCIMIENTO
           FROM CODIGOS_INTERNOS
          WHERE     TIPO_CODIGO = 'TCO'
                AND CODIGO = 'LVENCIMIENTO'
                AND STATUS = 'A';

         V_FASE := 1250;

         IF LVENCIMIENTO > 4000
         THEN
            V_FASE := 1200;
            LVENCIMIENTO := 730;
         END IF;
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE := 'ERROR CONSULTANDO VALOR DE VENCIMIENTO ' || SQLERRM;
            RAISE NO_VALIDO;
            NULL;
      END;

      IF V_VENCIMIENTO IS NULL
      THEN
         V_VENCIMIENTO := LVENCIMIENTO;
      END IF;

      --
      V_FASE := 1300;
      --SE GENERA  EL NUMERO RANDOM A PARTIR DE SYSDATE
      V_SEMILLA := TO_NUMBER (TO_CHAR (SYSTIMESTAMP, 'hhmissff3'));
      V_FASE := 1400;
      DBMS_RANDOM.INITIALIZE (V_SEMILLA);
      V_FASE := 1500;
      --Sr parte que el número ya existe en la tabla de seriales.
      V_EXISTE := TRUE;

      --Mientras el serial exista en la tabla se siguen generando números aleatorios
      WHILE V_EXISTE = TRUE
      LOOP
         V_FASE := 1600;
         --El numero generado siempre sera de 10 digitos
         V_SERIAL_GENERADO := DBMS_RANDOM.VALUE (100000000000, 999999999999);
         -- se revisa si existe en la tabla de seriales.
         V_SERIAL := NULL;
         V_FASE := 1700;

         BEGIN
            V_FASE := 1800;

            SELECT SERIAL
              INTO V_SERIAL
              FROM TCO_SERIAL_MAESTRO
             WHERE SERIAL = SEGURIDAD.ENCRIPTA (V_SERIAL_GENERADO);
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               V_FASE := 1900;
               V_EXISTE := FALSE;
            WHEN OTHERS
            THEN
               MESSAGE := 'ERROR CONSULTANDO SERIAL ' || SQLERRM;
               RAISE NO_VALIDO;
         END;
      END LOOP;

      V_FASE := 2000;
      --DBMS_OUTPUT.PUT_LINE ('Serial:' || V_SERIAL_GENERADO);
      IN_SERIAL := V_SERIAL_GENERADO;
      V_SERIAL := SEGURIDAD.ENCRIPTA (TO_CHAR (V_SERIAL_GENERADO));
      V_FASE := 2100;

      --Generado el número se inserta en la tabla maestro:
      INSERT INTO TCO_SERIAL_MAESTRO (SERIAL,
                                      USUARIO_INC,
                                      IP_INC,
                                      VENCIMIENTO)
           VALUES (V_SERIAL,
                   V_USUARIO,
                   V_IP,
                   V_VENCIMIENTO);

      V_FASE := 2200;
      --Se tienen que buscar aleatorios para filas y columnas
      V_FILAS_GENERADAS := '';
      V_EXISTE := FALSE;
      NINICIO := POWER (10, (LFILA - 1));
      NFIN := POWER (10, LFILA) - 1;

      WHILE V_EXISTE = FALSE
      LOOP
         V_FASE := 2400;
         V_FILAS_GENERADAS := FLOOR (DBMS_RANDOM.VALUE (NINICIO, NFIN));
         V_EXISTE := ES_CADENA_SIN_REP (V_FILAS_GENERADAS);
      END LOOP;

      V_FASE := 2500;
      V_COLUMNAS_GENERADAS := '';
      V_EXISTE := FALSE;

      WHILE V_EXISTE = FALSE
      LOOP
         V_COLUMNAS_GENERADAS := DBMS_RANDOM.STRING ('U', LCOLUMNA);
         V_EXISTE := ES_CADENA_SIN_REP (V_COLUMNAS_GENERADAS);
      END LOOP;

      V_FASE := 2700;

      --DBMS_OUTPUT.PUT_LINE ('fila    : ' || V_FILAS_GENERADAS);
      --DBMS_OUTPUT.PUT_LINE ('columna : ' || V_COLUMNAS_GENERADAS);

      FOR CFILA IN 1 .. LENGTH (V_FILAS_GENERADAS)
      LOOP
         V_FASE := 2800;
         CHFILA := SUBSTR (V_FILAS_GENERADAS, CFILA, 1);

         FOR CCOLUMNA IN 1 .. LENGTH (V_COLUMNAS_GENERADAS)
         LOOP
            V_FASE := 2900;
            CHCOLUMNA := SUBSTR (V_COLUMNAS_GENERADAS, CCOLUMNA, 1);
            --Calculo aleatorio del valor
            NINICIO := POWER (10, (LVALOR - 1));
            NFIN := POWER (10, LVALOR) - 1;

            --Se consulta si el valor generado ya existe en la tabla:
            V_CONT := 0;
            V_EXISTE := TRUE;
            V_EXISTE_N := 0;

            WHILE V_EXISTE = TRUE
            LOOP
               V_CONT := V_CONT + 1;
               V_VALOR_GENERADO := FLOOR (DBMS_RANDOM.VALUE (NINICIO, NFIN));

               BEGIN
                  SELECT 1
                    INTO V_EXISTE_N
                    FROM TCO_SERIAL_DETALLE
                   WHERE     SERIAL = V_SERIAL
                         AND VALOR = SEGURIDAD.ENCRIPTA (V_VALOR_GENERADO);
               EXCEPTION
                  WHEN NO_DATA_FOUND
                  THEN
                     V_EXISTE := FALSE;
                  WHEN OTHERS
                  THEN
                     MESSAGE :=
                           'ERROR CONSULTANDO VALORES DE LA TARJETA DE COORDENADAS '
                        || SQLERRM;
                     RAISE NO_VALIDO;
               END;

               --DBMS_OUTPUT.PUT_LINE ('Valor Generado : '|| V_VALOR_GENERADO|| ' contador:'|| V_CONT);

               IF V_CONT > 3000
               THEN
                  MESSAGE :=
                     'ERROR GENERANDO VALOR EN LA TARJETA DE COORDENADAS';
                  RAISE NO_VALIDO;
               END IF;
            END LOOP;



            --DBMS_OUTPUT.PUT_LINE ('Fila : '|| CHFILA|| ' Columna :'|| CHCOLUMNA|| ' Valor:'|| V_VALOR_GENERADO);

            INSERT INTO TCO_SERIAL_DETALLE (SERIAL,
                                            FILA,
                                            COLUMNA,
                                            VALOR)
                 VALUES (V_SERIAL,
                         SEGURIDAD.ENCRIPTA (CHFILA),
                         SEGURIDAD.ENCRIPTA (CHCOLUMNA),
                         SEGURIDAD.ENCRIPTA (V_VALOR_GENERADO));
         END LOOP;
      END LOOP;

      --Luego de creada la tarjeta se asigna al usuario con el estatus de asignada, faltarìa activarla
      BEGIN
         INSERT INTO TCO_SERIAL_ASIGNACION (SERIAL,
                                            LOGIN,
                                            CODCAR,
                                            USUARIO_ASI,
                                            FECHA_ASI,
                                            IP_ASI,
                                            ESTATUS)
              VALUES (V_SERIAL,
                      V_LOGIN,
                      V_CODCAR,
                      V_USUARIO,
                      SYSDATE,
                      V_IP,
                      'AS');
      EXCEPTION
         WHEN OTHERS
         THEN
            MESSAGE :=
                  'ERROR ASIGNANDO LA TARJETA DE COORDENADAS AL USUARIO '
               || V_LOGIN
               || ' '
               || SQLERRM;
            RAISE NO_VALIDO;
      END;

      DBMS_RANDOM.TERMINATE;


      /*
         SET SERVEROUTPUT ON;
         BEGIN
             DECLARE
                 V_RESULTADO   VARCHAR2 (10) := '';
                 V_SERIAL      VARCHAR2 (30) := '';
             BEGIN
                 --DBMS_OUTPUT.ENABLE (1000000);
                 VBT_PR_GENERACION (V_SERIAL,
                                  'maraujo3',
                                  '',
                                  '',
                                  'davidspp',
                                  '192.168.223.46',

                                  V_RESULTADO);
                                  dbms_output.put_line(v_serial);
                 COMMIT;
             END;
         END;

      */

      V_FASE := 2500;
   EXCEPTION
      WHEN NO_VALIDO
      THEN
         ROLLBACK;
         MESSAGE := 'VBT_PR_GENERACION<ERROR>' || MESSAGE || '<ERROR> ';
         PR_INS_ERRORES (V_USUARIO,
                         V_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
         RAISE_APPLICATION_ERROR (-20002, MESSAGE);
      WHEN OTHERS
      THEN
         ROLLBACK;
         MESSAGE :=
               'VBT_PR_GENERACION <ERROR> FASE:'
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO DE GENERACION DE TARJETA DE COORDENADAS <ERROR>'
            || SQLERRM;
         PR_INS_ERRORES (V_USUARIO,
                         V_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
         RAISE_APPLICATION_ERROR (-20099, MESSAGE);
   END VBT_PR_GENERACION;

   PROCEDURE VBT_PR_ACTIVACION (IN_SERIAL       IN OUT VARCHAR2,
                                IN_LOGIN        IN     VARCHAR2,
                                IN_CODCAR       IN     VARCHAR2,
                                IN_USUARIO      IN     VARCHAR2,
                                IN_IP           IN     VARCHAR2,
                                OUT_RESULTADO      OUT VARCHAR2)
   IS
      V_SERIAL               TCO_SERIAL_MAESTRO.SERIAL%TYPE := IN_SERIAL;
      V_LOGIN                TCO_SERIAL_ASIGNACION.LOGIN%TYPE := IN_LOGIN;
      V_CODCAR               TCO_SERIAL_ASIGNACION.CODCAR%TYPE := IN_CODCAR;

      V_USUARIO              VARCHAR2 (30) := UPPER (IN_USUARIO);
      V_IP                   VARCHAR2 (16) := IN_IP;
      --
      V_SEMILLA              NUMBER (9);
      V_SERIAL_GENERADO      NUMBER (15) := 0;
      V_FILAS_GENERADAS      VARCHAR2 (10) := '';
      V_COLUMNAS_GENERADAS   VARCHAR2 (10) := '';
      V_VALOR_GENERADO       VARCHAR2 (5) := '';
      LFILA                  PLS_INTEGER := 5;
      LCOLUMNA               PLS_INTEGER := 8;
      LVALOR                 PLS_INTEGER := 3;
      LVENCIMIENTO           PLS_INTEGER := 730;
      LHORASACT              PLS_INTEGER := 24;
      NINICIO                NUMBER (9) := 0;
      NFIN                   NUMBER (9) := 0;
      CFILA                  PLS_INTEGER := 0;
      CCOLUMNA               PLS_INTEGER := 0;
      CHFILA                 VARCHAR2 (1) := '';
      CHCOLUMNA              VARCHAR2 (1) := '';
      V_FECHA_ASI            TCO_SERIAL_ASIGNACION.FECHA_ASI%TYPE;
      V_ESTATUS              TCO_SERIAL_ASIGNACION.ESTATUS%TYPE;
      --
      V_EXISTE               BOOLEAN;
      V_EXISTE_N             PLS_INTEGER;
      V_CONT                 PLS_INTEGER;
      MESSAGE                VARCHAR2 (600);
      NO_VALIDO              EXCEPTION;
      V_FASE                 NUMBER := 0;
   BEGIN
      --Procedimiento para Tarjeta de coordenadas para activar una tarjeta de coordenadas
      V_FASE := 100;


      OUT_RESULTADO := 0;

      --para obtener el resultado de las validacione
      VBT_PR_GET_ESTATUS (V_LOGIN,
                          V_SERIAL,
                          V_USUARIO,
                          V_IP,
                          'L',                                     --por login
                          MESSAGE,
                          OUT_RESULTADO);

      IF OUT_RESULTADO = 5
      THEN
         --Activación de la tarjeta de coordenadas.
         BEGIN
            UPDATE TCO_SERIAL_ASIGNACION
               SET ESTATUS = 'AC',
                   USUARIO_ACT = V_USUARIO,
                   FECHA_ACT = SYSDATE,
                   IP_ACT = V_IP
             WHERE     LOGIN = V_LOGIN
                   AND SERIAL = SEGURIDAD.ENCRIPTA (V_SERIAL)
                   AND ESTATUS = 'AS';

            OUT_RESULTADO := 0;
         EXCEPTION
            WHEN OTHERS
            THEN
               MESSAGE := 'ERROR ACTIVANDO TARJETA DE COORDENADAS ' || SQLERRM;
               RAISE NO_VALIDO;
         END;
      END IF;

      /*
         SET SERVEROUTPUT ON;
         BEGIN
             DECLARE
                 V_RESULTADO   VARCHAR2 (10) := '';
                 V_SERIAL      VARCHAR2 (30) := '';
             BEGIN
                 --DBMS_OUTPUT.ENABLE (1000000);
                 VBT_PR_ACTIVACION (V_SERIAL,
                                  'maraujo3',
                                  '',
                                  '',
                                  'davidspp',
                                  '192.168.223.46',

                                  V_RESULTADO);
                                  dbms_output.put_line(v_serial);
                 COMMIT;
             END;
         END;

        --PARA PRUEBAS ACTIVACION

        BEGIN
           DECLARE
               V_RESULTADO   VARCHAR2 (10) := '';
               V_SERIAL      VARCHAR2 (30) := '918962717449';
           BEGIN
               --DBMS_OUTPUT.ENABLE (1000000);
               PR_SERIAL (V_SERIAL,
                                'maraujo3',
                                '',
                                '',
                                'davidspp',
                                '192.168.223.46',
                                'A',
                                V_RESULTADO);
               COMMIT;
               DBMS_OUTPUT.PUT_LINE('Serial:' ||v_serial || ' resultado:' || v_resultado);
           END;
        END;
      */

      V_FASE := 2500;
   EXCEPTION
      WHEN NO_VALIDO
      THEN
         ROLLBACK;
         MESSAGE := 'VBT_PR_ACTIVACION<ERROR>' || MESSAGE || '<ERROR> ';
         PR_INS_ERRORES (V_USUARIO,
                         V_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
         RAISE_APPLICATION_ERROR (-20002, MESSAGE);
      WHEN OTHERS
      THEN
         ROLLBACK;
         MESSAGE :=
               'VBT_PR_ACTIVACION <ERROR> 001099 FASE:'
            || V_FASE
            || ' - ERROR EN PROCEDIMIENTO DE ACTIVACION DE TARJETA DE COORDENADAS <ERROR>'
            || SQLERRM;
         PR_INS_ERRORES (V_USUARIO,
                         V_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
         RAISE_APPLICATION_ERROR (-20099, MESSAGE);
   END VBT_PR_ACTIVACION;


   --SELECCION DE COORDENADAS A VALIDAR
   PROCEDURE VBT_PR_SELECCION (IN_LOGIN            IN     VARCHAR2,
                               IN_IP               IN     VARCHAR2,
                               IN_SERIAL              OUT VARCHAR2,
                               P_VBT_FILAS            OUT SYS_REFCURSOR,
                               P_VBT_COLUMNAS         OUT SYS_REFCURSOR,
                               P_VBT_COORDENADAS      OUT SYS_REFCURSOR,
                               OUT_RESULTADO          OUT VARCHAR2,
                               OUT_MENSAJE            OUT VARCHAR2)
   IS
      V_LOGIN                 TCO_VALIDACION.USUARIO_INC%TYPE := IN_LOGIN;
      V_IP                    TCO_VALIDACION.IP_INC%TYPE := IN_IP;
      V_SERIAL_PANTALLA       TCO_SERIAL_MAESTRO.SERIAL%TYPE := '';
      V_FILA                  TCO_VALIDACION.FILA%TYPE := '';
      V_COLUMNA               TCO_VALIDACION.COLUMNA%TYPE := '';
      V_CANTIDAD              NUMBER := 0;
      V_CANTIDAD_VALIDACION   NUMBER := 0;
      SQLSTRING               VARCHAR2 (2000);
      MESSAGE                 VARCHAR2 (600);
   BEGIN
      --OUT_RESULTADO:
      --0 OK
      --1 'EL USUARIO NO POSEE TARJETA ACTIVA';
      --2 'LA TARJETA DE COORDENADAS SE ENCUENTRA VENCIDA';
      --3 ERROR EN LA SELECCION DE COORDENADAS

      --  DBMS_OUTPUT.enable (1000000);

      OUT_RESULTADO := 0;

      BEGIN
         -- SE BUSCA EL SERIAL DE LA TARJETA DE COORDENADAS
         SELECT '*********' || SUBSTR (SEGURIDAD.DESENCRIPTA (A.SERIAL), -4),
                A.SERIAL
           INTO V_SERIAL_PANTALLA, IN_SERIAL
           FROM TCO_SERIAL_ASIGNACION A
          WHERE A.LOGIN = V_LOGIN AND A.ESTATUS = 'AC';



         --DBMS_OUTPUT.put_line ('IN_SERIAL ' || IN_SERIAL);

         --SE COMPRUEBA LA VALIDEZ DE LA TARJETA
         SELECT COUNT (M.SERIAL)
           INTO V_CANTIDAD
           FROM TCO_SERIAL_ASIGNACION A, TCO_SERIAL_MAESTRO M
          WHERE A.SERIAL = M.SERIAL AND A.LOGIN = V_LOGIN AND ESTATUS = 'AC';

         SELECT COUNT (M.SERIAL)
           INTO V_CANTIDAD
           FROM TCO_SERIAL_ASIGNACION A, TCO_SERIAL_MAESTRO M
          WHERE     A.SERIAL = M.SERIAL
                AND A.LOGIN = V_LOGIN
                AND ESTATUS = 'AC'
                AND (SYSDATE - A.FECHA_ACT) <= M.VENCIMIENTO;

         IF V_CANTIDAD = 0
         THEN                                                 --TAEJTA VENCIDA
            OUT_RESULTADO := 2;
            OUT_MENSAJE := 'LA TARJETA DE COORDENADAS SE ENCUENTRA VENCIDA';
         END IF;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            OUT_RESULTADO := 1;
            OUT_MENSAJE := 'EL USUARIO NO POSEE TARJETA ACTIVA';
      END;


      IF OUT_RESULTADO = '0'                                  --TARJETA VALIDA
      THEN
         --cONSULTA DE NUMERO DE COORDENADAS A SELECCIONAR
         SELECT TO_NUMBER (DESCRIPCION)
           INTO V_CANTIDAD_VALIDACION
           FROM CODIGOS_INTERNOS
          WHERE     TIPO_CODIGO = 'TCO'
                AND CODIGO = 'LCOORVALIDACION'
                AND STATUS = 'A';

         --SE VERIFICA SI LAS COORDENADAS DE VALIDACION YA FUERON SELECCIONADAS
         SELECT COUNT (SERIAL)
           INTO V_CANTIDAD
           FROM TCO_VALIDACION
          WHERE SERIAL = IN_SERIAL;


         IF    V_CANTIDAD = 0
            OR (V_CANTIDAD > 0 AND V_CANTIDAD < V_CANTIDAD_VALIDACION)
         --NO HAY NINGUNAS COORDENADAS PRESELECCIONADAS o hay menos coordenadas preseleccionadas que lo parametrizado
         THEN
            -- SE SELECCIONAN  LAS COORDENADAS DE VALIDACION
            FOR REG IN 1 .. V_CANTIDAD_VALIDACION - V_CANTIDAD
            LOOP
               SELECT FILA, COLUMNA
                 INTO V_FILA, V_COLUMNA
                 FROM (  SELECT D.FILA FILA, D.COLUMNA COLUMNA
                           FROM TCO_SERIAL_DETALLE D, TCO_SERIAL_ASIGNACION A
                          WHERE     D.SERIAL = A.SERIAL
                                AND A.LOGIN = V_LOGIN
                                AND A.ESTATUS = 'AC'
                                AND FILA NOT IN
                                       (SELECT NVL (FILA, ' ')
                                          FROM TCO_VALIDACION S
                                         WHERE     S.USUARIO_INC = A.LOGIN
                                               AND S.SERIAL = A.SERIAL)
                                AND COLUMNA NOT IN
                                       (SELECT NVL (COLUMNA, ' ')
                                          FROM TCO_VALIDACION S
                                         WHERE     S.USUARIO_INC = A.LOGIN
                                               AND S.SERIAL = A.SERIAL)
                       ORDER BY DBMS_RANDOM.VALUE)
                WHERE ROWNUM = '1';

               INSERT INTO TCO_VALIDACION (SERIAL,
                                           FILA,
                                           COLUMNA,
                                           USUARIO_INC,
                                           IP_INC,
                                           FECHA_INC)
                    VALUES (IN_SERIAL,
                            V_FILA,
                            V_COLUMNA,
                            V_LOGIN,
                            V_IP,
                            SYSDATE);
            -- DBMS_OUTPUT.put_line ('FILA '||seguridad.desencripta(REG.FILA)||' Columna'||seguridad.desencripta(REG.columna));

            --  EXIT WHEN REG.ROWNUM = (V_CANTIDAD_VALIDACION - v_cantidad);
            END LOOP;
         END IF;

         --CURSOR CON LAS COORDENADAS SELECCIONADAS
         OPEN P_VBT_COORDENADAS FOR
              SELECT SEGURIDAD.DESENCRIPTA (FILA),
                     SEGURIDAD.DESENCRIPTA (COLUMNA)
                FROM TCO_VALIDACION
               WHERE SERIAL = IN_SERIAL AND ROWNUM <= V_CANTIDAD_VALIDACION
            ORDER BY FECHA_INC ASC;



         -- fILAS QUE COMPONEN LA TARJETA
         OPEN P_VBT_FILAS FOR
              SELECT DISTINCT (SEGURIDAD.DESENCRIPTA (FILA)) FILA
                FROM TCO_SERIAL_DETALLE
               WHERE SERIAL = IN_SERIAL
            ORDER BY FILA;

         -- COLUMNAS QUE COMPONEN LA TARJETA
         OPEN P_VBT_COLUMNAS FOR
              SELECT DISTINCT (SEGURIDAD.DESENCRIPTA (COLUMNA)) COLUMNA
                FROM TCO_SERIAL_DETALLE
               WHERE SERIAL = IN_SERIAL
            ORDER BY COLUMNA;

         -- SERIAL A MOSTRAR EN PANTALLA
         IN_SERIAL := V_SERIAL_PANTALLA;
      --DBMS_OUTPUT.put_line ('IN_SERIAL ' || IN_SERIAL);
      --DBMS_OUTPUT.put_line ('IN_TOTAL_FILAS ' || IN_TOTAL_FILAS);
      --DBMS_OUTPUT.put_line ('IN_TOTAL_COLUMNAS ' || IN_TOTAL_COLUMNAS);
      ELSE
         -- nO POSEE TARJETA DE COORDENADAS VALIDA
         V_SERIAL_PANTALLA := ' ';
         IN_SERIAL := ' ';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         OUT_RESULTADO := 3;
         ROLLBACK;
         MESSAGE :=
               'VBT_PR_SELECCION <ERROR> '
            || ' - ERROR EN PROCEDIMIENTO DE SELECCION DE COORDENADAS <ERROR>'
            || SQLERRM;
         PR_INS_ERRORES (IN_LOGIN,
                         IN_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
   END VBT_PR_SELECCION;

   -- VALIDACION DE COORDENADAS
   PROCEDURE VBT_PR_VALIDACION (IN_LOGIN        IN     VARCHAR2,
                                IN_IP           IN     VARCHAR2,
                                IN_FILA         IN     VARCHAR2,
                                IN_COLUMNA      IN     VARCHAR2,
                                IN_VALOR        IN     VARCHAR2,
                                OUT_RESULTADO      OUT VARCHAR2,
                                OUT_MENSAJE        OUT VARCHAR2)
   IS
      V_LOGIN      TCO_VALIDACION.USUARIO_INC%TYPE := IN_LOGIN;
      V_IP         TCO_VALIDACION.IP_INC%TYPE := IN_IP;
      V_FILA       TCO_SERIAL_DETALLE.FILA%TYPE := IN_FILA;
      V_COLUMNA    TCO_SERIAL_DETALLE.COLUMNA%TYPE := IN_COLUMNA;

      V_VALOR      TCO_SERIAL_DETALLE.VALOR%TYPE := IN_VALOR;
      V_SERIAL     TCO_SERIAL_DETALLE.SERIAL%TYPE := '';
      V_CANTIDAD   NUMBER := 0;

      SQLSTRING    VARCHAR2 (2000);
      MESSAGE      VARCHAR2 (600);
   BEGIN
      --OUT_RESULTADO:
      --0 VALIDACION EXITOSA
      --1 valor de la coordenada no es valido
      --2 ERROR EN LA VALIDACION DE LAS COORDENADAS

      --DBMS_OUTPUT.ENABLE (1000000);

      -- SE BUSCA EL SERIAL DE LA TARJETA DE COORDENADAS
      SELECT A.SERIAL
        INTO V_SERIAL
        FROM TCO_SERIAL_ASIGNACION A
       WHERE A.LOGIN = V_LOGIN AND A.ESTATUS = 'AC';


      --SE COMPRUEBA LA VALIDEZ DE LA TARJETA

      SELECT COUNT (V.FILA)
        INTO V_CANTIDAD
        FROM TCO_SERIAL_DETALLE D, TCO_VALIDACION V, TCO_SERIAL_ASIGNACION A
       WHERE     D.SERIAL = V.SERIAL
             AND D.FILA = V.FILA
             AND D.COLUMNA = V.COLUMNA
             AND D.VALOR = SEGURIDAD.ENCRIPTA (V_VALOR)
             AND D.FILA = SEGURIDAD.ENCRIPTA (V_FILA)
             AND D.COLUMNA = SEGURIDAD.ENCRIPTA (V_COLUMNA)
             AND V.SERIAL = A.SERIAL
             AND A.LOGIN = V_LOGIN
             AND A.ESTATUS = 'AC';

      IF V_CANTIDAD = 1
      THEN
         DELETE FROM TCO_VALIDACION
               WHERE     SERIAL = V_SERIAL
                     AND FILA = SEGURIDAD.ENCRIPTA (V_FILA)
                     AND COLUMNA = SEGURIDAD.ENCRIPTA (V_COLUMNA);

         OUT_RESULTADO := 0;
         OUT_MENSAJE := 'VALIDACION EXITOSA';
      ELSE
         OUT_RESULTADO := 1;
         OUT_MENSAJE := 'VALOR DE LA COORDENADA NO ES VALIDO';
      END IF;

      INSERT INTO TCO_VALIDACION_HIS (SERIAL,
                                      FILA,
                                      COLUMNA,
                                      USUARIO_VAL,
                                      IP_VAL,
                                      FECHA_VAL,
                                      RESULTADO)
           VALUES (V_SERIAL,
                   SEGURIDAD.ENCRIPTA (V_FILA),
                   SEGURIDAD.ENCRIPTA (V_COLUMNA),
                   V_LOGIN,
                   V_IP,
                   SYSDATE,
                   OUT_RESULTADO);
   -- DBMS_OUTPUT.PUT_LINE ('OUT_RESULTADO '||OUT_RESULTADO);
   EXCEPTION
      WHEN OTHERS
      THEN
         OUT_RESULTADO := 2;
         OUT_MENSAJE := 'ERROR EN LA VALIDACION DE COORDENADAS ';

         ROLLBACK;
         MESSAGE :=
               'VBT_PR_SELECCION <ERROR> '
            || ' - ERROR EN PROCEDIMIENTO DE VALIDACION DE COORDENADAS <ERROR>'
            || SQLERRM;
         PR_INS_ERRORES (IN_LOGIN,
                         IN_IP,
                         SYSDATE,
                         MESSAGE);
         COMMIT;
   END VBT_PR_VALIDACION;

   FUNCTION FNC_GET_ESTATUS (IN_LOGIN VARCHAR2)
      RETURN VARCHAR2
   IS
      V_LOGIN     varchar2(100):= IN_LOGIN;

 V_FECHA_ASI     DATE;
 V_FECHA_VENC    varchar2(30):=''; 
      V_ESTATUS       TCO_SERIAL_ASIGNACION.ESTATUS%TYPE;
      --
      LHORASACT       PLS_INTEGER := 24;
      V_VENCIMIENTO   PLS_INTEGER := 0;
      --
      V_CONT          PLS_INTEGER := 0;
      V_MENSAJE   VARCHAR2 (1000) := '';
     
   BEGIN
   
      V_CONT := 0;

      BEGIN
         SELECT COUNT (LOGIN)
           INTO V_CONT
           FROM TCO_SERIAL_ASIGNACION A
          WHERE A.LOGIN = IN_LOGIN;
      EXCEPTION
         WHEN NO_DATA_FOUND
         THEN
            V_CONT := 0;
        
      END;

      --DBMS_OUTPUT.PUT_LINE ('contador:' || V_CONT);

      IF V_CONT = 0
      THEN
         V_MENSAJE := 'No posee';
      ELSE
      
     
         BEGIN
         
            SELECT A.FECHA_ASI,
                   A.ESTATUS,
                     TO_DATE (A.FECHA_ASI + M.VENCIMIENTO, 'dd/MM/YY')
                   - TO_DATE (SYSDATE, 'dd/MM/YY'), 
                   to_char(TO_DATE (FECHA_INC + VENCIMIENTO, 'dd/MM/YY') , 'dd/MM/YYYY') 
              INTO V_FECHA_ASI, V_ESTATUS, V_VENCIMIENTO, V_FECHA_VENC
              FROM TCO_SERIAL_ASIGNACION A, TCO_SERIAL_MAESTRO M
             WHERE     A.SERIAL = M.SERIAL
                     and a.estatus in ('AS','AC')
                   AND A.LOGIN = V_LOGIN;
         EXCEPTION
            WHEN NO_DATA_FOUND
            THEN
               V_CONT := 0;
           
         END;

        if V_ESTATUS IS NULL THEN
                 
             V_MENSAJE := 'No posee'  ;
             
             
             ELSE 
         --En caso de encontrarse la tarjeta se hacen las validaciones neesarias para activarla
   
    
            --Se trae el valor de las horas de activación
            BEGIN
               SELECT TO_NUMBER (DESCRIPCION)
                 INTO LHORASACT
                 FROM CODIGOS_INTERNOS
                WHERE     TIPO_CODIGO = 'TCO'
                      AND CODIGO = 'LHORASACT'
                      AND STATUS = 'A';
            EXCEPTION
               WHEN OTHERS
               THEN
                  LHORASACT := 24;
            END;


            --Validacion de estatus
            IF V_ESTATUS = 'AS'
            THEN
               V_MENSAJE :=
                  'Esperando activación';
            END IF;

            --Validacion de horas desde que se asignò la tarjeta (generación)
            IF V_FECHA_ASI + (LHORASACT / 24) < SYSDATE
            THEN
               V_MENSAJE :=
                     'HA EXCEDIDO EL TIEMPO DE ACTIVACIÓN DE '
                  || LHORASACT
                  || ' HORAS';
            END IF;

            --Validacion de estatus
            IF V_ESTATUS = 'AC'
            THEN
               IF V_VENCIMIENTO < 0
               THEN
                  V_MENSAJE :=
                     'VENCIDA. FECHA DE VENCIMIENTO: '||V_FECHA_VENC;
               ELSE
                  V_MENSAJE :=
                     'ACTIVA. FECHA DE VENCIMIENTO: '||V_FECHA_VENC;
               END IF;
               
            END IF;
     
            
         END IF;
         
          
      END IF;
       RETURN V_MENSAJE;
   EXCEPTION
      WHEN OTHERS
      THEN
         RETURN ' ';
         
        
   END FNC_GET_ESTATUS;
END TCO;
/


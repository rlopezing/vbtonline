CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.backoffice
AS
   PROCEDURE bac_usuario_con_pr (p_strRoot             IN     VARCHAR2,
                                 p_strLoader           IN     VARCHAR2,
                                 p_strSupervisor       IN     VARCHAR2,
                                 p_strCliente          IN     VARCHAR2,
                                 p_strActiva           IN     VARCHAR2,
                                 p_strInactiva         IN     VARCHAR2,
                                 p_strCancelada        IN     VARCHAR2,
                                 p_strBloqueado        IN     VARCHAR2,
                                 p_strDesconocido      IN     VARCHAR2,
                                 p_strTxtUsuario       IN     VARCHAR2,
                                 p_strTxtNombre        IN     VARCHAR2,
                                 p_strTxtCIRIF         IN     VARCHAR2,
                                 p_strCmbTipoUsuario   IN     VARCHAR2,
                                 p_strCmbEstatus       IN     VARCHAR2,
                                 p_strCmbAmbito        IN     VARCHAR2,
                                 p_strOrden            IN     VARCHAR2,
                                 hdnAccion             IN     VARCHAR2,
                                 bac_usuario_con          OUT SYS_REFCURSOR,
                                 p_resultado              OUT VARCHAR2,
                                 p_salida                 OUT VARCHAR2,
                                 p_login               IN     VARCHAR2)
   AS
      SQLSTRING   VARCHAR2 (2000);
      SQLELEM     VARCHAR2 (200);
      whereSQL    VARCHAR2 (500);
      ordenSQL    VARCHAR2 (100);

      l_grupo     usugrp_v1.codgrp%TYPE;


      CURSOR ELEMENTO
      IS
           SELECT et.codelemento, et.descripcion
             FROM grpciaopc_v1 gco, usrciaopc_v1 cco, elementos_tipos et
            WHERE     gco.codgrp = l_grupo
                  AND gco.codsis = 'ONLINE'
                  AND gco.codcia = 'VBT'
                  AND GCO.CODOPC = CCO.CODOPC(+)
                  AND cco.login(+) = p_login
                  AND gco.codopc = et.codopc
                  AND et.codtipo = '0000000002'
                  AND SUBSTR (COALESCE (CCO.TIPACC, gco.tipacc), 1, 1) = '1'
         ORDER BY DESCRIPCION ASC;
   BEGIN
      SQLSTRING :=
            'SELECT LOGIN,                                     
         TO_CHAR(FECHA_INGRESO,''dd/mm/yyyy hh:mi A.M.''), 
         NOMBRES, 
         EMAIL, 
         TELEFONO_CELULAR || DECODE(TELEFONO_CELULAR,NULL,TELEFONO, DECODE(TELEFONO,NULL,'''','' / '' || TELEFONO)) TELEFONOS,  
         DECODE(TIPO,''-1'','''
         || p_strRoot
         || ''',''0'','''
         || p_strLoader
         || ''',''1'','''
         || p_strSupervisor
         || ''',''2'','''
         || p_strCliente
         || ''') TIPO_USUARIO,
         DIRECCION, 
         TO_CHAR(FECHA_LOGIN,''dd/mm/yyyy hh:mi A.M.'') ULTIMO_LOGIN, 
         DECODE(STATUS_CUENTA,''A'','''
         || p_strActiva
         || ''',''I'','''
         || p_strInactiva
         || ''',''C'','''
         || p_strCancelada
         || ''',''B'','''
         || p_strBloqueado
         || ''','''
         || p_strDesconocido
         || ''') STATUS, 
        TIPO,
         STATUS_CUENTA,
         CIRIF,
         TO_CHAR(FECHA_STATUS,''dd/mm/yyyy''),
         AMBITO,
         elementos_tipos.descripcion
         FROM vbt_users , elementos_tipos
         WHERE AMBITO = ELEMENTOS_TIPOS.CODELEMENTO AND
              ELEMENTOS_TIPOS.CODTIPO =''0000000008'' AND ';



      BEGIN
         SELECT codgrp
           INTO l_grupo
           FROM USUGRP_v1
          WHERE login = p_login;

         FOR I IN ELEMENTO
         LOOP
            BEGIN
               SQLELEM := SQLELEM || '''' || I.codelemento || '''' || ',';
            EXCEPTION
               WHEN NO_DATA_FOUND
               THEN
                  SQLELEM := ',';
            END;
         END LOOP;

         SQLELEM :=
            'TIPO IN (' || SUBSTR (SQLELEM, 1, (LENGTH (SQLELEM) - 1)) || ')';
      END;

      SQLSTRING := SQLSTRING || SQLELEM;


      IF hdnAccion IS NOT NULL
      THEN
         IF p_strTxtUsuario IS NOT NULL OR p_strTxtUsuario <> ''
         THEN
            whereSQL := ' AND LOGIN =  ''' || p_strTxtUsuario || '''';
         ELSE
            IF p_strTxtNombre IS NOT NULL OR p_strTxtNombre <> ''
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND UPPER(NOMBRES) like '''
                  || '%'
                  || p_strTxtNombre
                  || '%'
                  || '''';
            END IF;

            IF p_strTxtCIRIF IS NOT NULL OR p_strTxtCIRIF <> ''
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND CIRIF like '''
                  || '%'
                  || p_strTxtCIRIF
                  || '%'
                  || '''';
            END IF;

            IF     p_strCmbTipoUsuario IS NOT NULL
               AND p_strCmbTipoUsuario <> 'Default'
            THEN
               whereSQL :=
                  whereSQL || 'AND TIPO =  ''' || p_strCmbTipoUsuario || '''';
            END IF;

            IF p_strCmbEstatus IS NOT NULL AND p_strCmbEstatus <> 'Default'
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND STATUS_CUENTA = '''
                  || p_strCmbEstatus
                  || '''';
            END IF;

            IF p_strCmbAmbito IS NOT NULL AND p_strCmbAmbito <> 'Default'
            THEN
               whereSQL :=
                  whereSQL || ' AND AMBITO = ''' || p_strCmbAmbito || '''';
            END IF;
         END IF;



         --whereSQL := 'AND LOGIN =  '''|| p_strTxtUsuario || ''' AND UPPER(NOMBRES) like ''' ||p_strTxtNombre||''' AND CIRIF like ''' ||'%' ||p_strTxtNombre||'%' ||'''';
         --whereSQL := whereSQL || ' AND TIPO =  '''|| p_strCmbTipoUsuario || ''' AND STATUS_CUENTA = '''|| p_strCmbEstatus || '''';

         SQLSTRING := SQLSTRING || whereSQL;
      END IF;

      IF p_strOrden = 'Nombre'
      THEN
         ordenSQL := 'ORDER BY NOMBRES ASC';
      ELSIF p_strOrden = 'Tipo'
      THEN
         ordenSQL := ' ORDER BY TIPO_USUARIO ASC';
      ELSIF p_strOrden = 'Estatus'
      THEN
         ordenSQL := ' ORDER BY STATUS_CUENTA ASC';
      ELSIF p_strOrden = 'Ultimo_Acceso'
      THEN
         ordenSQL := ' ORDER BY FECHA_LOGIN DESC';
      ELSIF p_strOrden = 'Fecha_Creacion'
      THEN
         ordenSQL := ' ORDER BY FECHA_INGRESO DESC';
      ELSE
         ordenSQL := ' ORDER BY LOGIN ASC';
      END IF;

      SQLSTRING := SQLSTRING || ordenSQL;
      p_salida := SQLSTRING;

      OPEN bac_usuario_con FOR SQLSTRING;

      --  execute immediate SQLSTRING into p_bac_usuario_con;
      --p_bac_usuario_con := cs_bac_usuario_con;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuario_con_pr;

   --END bac_usuario_con_pr;


   /******************************************************************************
    NAME:    bac_usuarioagregar_pr
    PURPOSE: Agrega un usuario a tabla VBT_USERS
    ******************************************************************************/

   PROCEDURE bac_usuarioagregar_pr (
      p_strTxtUsuarioAgregar       IN     VARCHAR2,
      p_strRandomPassword          IN     VARCHAR2,
      p_login                      IN     VARCHAR2,
      p_strTxtdireccion            IN     VARCHAR2,
      p_strTxtTlfCelularAgregar    IN     VARCHAR2,
      p_strTxtTelefonoAgregar      IN     VARCHAR2,
      p_strTxtEmailAgregar         IN     VARCHAR2,
      p_miPasswTemp                IN     VARCHAR2,
      p_strCmbTipoUsuarioAgregar   IN     VARCHAR2,
      p_strCmbPRECIRIFAgregar      IN     VARCHAR2,
      p_strTxtCIRIFAgregar         IN     VARCHAR2,
      p_strTxtNombre               IN     VARCHAR2,
      p_strTxtgrupo                IN     VARCHAR2,
      p_strTxtAmbito               IN     VARCHAR2,
      p_resultado                     OUT VARCHAR2,
      p_inte_login                    OUT VARCHAR2)
   AS
      p_encryp_password   VARCHAR2 (100);
      p_loginn            VARCHAR2 (20);
   /*
    SELECT CODELEMENTO
    FROM  ELEMENTOS_TIPOS
    WHERE CODTIPO = '0000000015' OR CODTIPO = '0000000016'
    ORDER BY CODELEMENTO
   */
   BEGIN
      SELECT LOGIN
        INTO p_loginn
        FROM VBT_USERS
       WHERE LOGIN = LOWER (p_strTxtUsuarioAgregar);

      p_inte_login := 'Usuario Registrado';
      p_resultado := 'Usuario Registrado';
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         /*SELECT TO_CHAR(UPPER(P_strRandomPassword))
         INTO p_encryp_password
         FROM DUAL;*/

         INSERT INTO VBT_USERS (LOGIN,
                                FECHA_INGRESO,
                                DIRECCION,
                                TELEFONO_CELULAR,
                                TELEFONO,
                                EMAIL,
                                PASSWORD,
                                TIPO,
                                NOMBRES,
                                CIRIF,
                                STATUS_CUENTA,
                                FECHA_STATUS,
                                AMBITO)
              VALUES (p_strTxtUsuarioAgregar,
                      SYSDATE,
                      p_strTxtdireccion,
                      p_strTxtTlfCelularAgregar,
                      p_strTxtTelefonoAgregar,
                      LOWER (p_strTxtEmailAgregar),
                      SEGURIDAD.ENCRIPTA (p_miPasswTemp),
                      p_strCmbTipoUsuarioAgregar,
                      INITCAP (p_strTxtNombre),
                      p_strCmbPRECIRIFAgregar || '-' || p_strTxtCIRIFAgregar,
                      'A',
                      SYSDATE,
                      p_strTxtAmbito);

         INSERT INTO USUGRP_v1 (LOGIN,
                                CODGRP,
                                CODSIS,
                                CODCIA,
                                FECHASIG,
                                USRID)
              VALUES (p_strTxtUsuarioAgregar,
                      p_strTxtgrupo,
                      'ONLINE',
                      'VBT',
                      SYSDATE,
                      p_login);

         --COMMIT;
         p_resultado := 'OK';
   END bac_usuarioagregar_pr;

   /******************************************************************************
 NAME:    bac_usuarioeditar_pr
 PURPOSE: Editar información del usuario  y el grupo de uaurio tabla VBT_USERS
 ******************************************************************************/

   PROCEDURE bac_usuarioeditar_pr (
      p_strTxtUsuarioEditar        IN     VARCHAR2,
      p_strTxtTlfCelularEditar     IN     VARCHAR2,
      p_strTxtTelefonoEditar       IN     VARCHAR2,
      p_strTxtEmailEditar          IN     VARCHAR2,
      p_strCmbPRECIRIFEditar       IN     VARCHAR2,
      p_strTxtCIRIFEditar          IN     VARCHAR2,
      p_strTxtdireccion            IN     VARCHAR2,
      p_strTxtNombre               IN     VARCHAR2,
      p_strCmbStatusCuentaEditar   IN     VARCHAR2,
      p_strCmbTipoUsuarioEditar    IN     VARCHAR2,
      p_hdnCambioEstatus           IN     VARCHAR2,
      p_strTxtgrupo                IN     VARCHAR2,
      p_strTxtCodPerCli            IN     VARCHAR2,
      p_strTxtAmbito               IN     VARCHAR2,
      p_strCodPais                 IN     VARCHAR2,
      p_resultado                     OUT VARCHAR2)
   AS
      sqlquery   VARCHAR2 (1000);
   BEGIN
      sqlquery :=
            'UPDATE VBT_USERS SET DIRECCION = '''
         || p_strTxtdireccion
         || ''',
                      TELEFONO_CELULAR = '''
         || p_strTxtTlfCelularEditar
         || ''',
                      TELEFONO         = '''
         || p_strTxtTelefonoEditar
         || ''',
                      CODIGO_PAIS         = '''
         || p_strCodPais
         || ''',
                      EMAIL            = lower('''
         || p_strTxtEmailEditar
         || '''),
                      CIRIF            = upper('''
         || p_strCmbPRECIRIFEditar
         || ''') ||''-''|| upper('''
         || p_strTxtCIRIFEditar
         || '''),
                      NOMBRES          = INITCAP('''
         || p_strTxtNombre
         || ''') ';

      IF p_strCmbStatusCuentaEditar IS NOT NULL
      THEN
         sqlquery :=
               sqlquery
            || ', STATUS_CUENTA             = '''
            || p_strCmbStatusCuentaEditar
            || '''';
      END IF;

      IF    p_strCmbTipoUsuarioEditar <> '2'
         OR p_strCmbTipoUsuarioEditar <> 'Default'
      THEN
         sqlquery :=
               sqlquery
            || ', TIPO             = '''
            || p_strCmbTipoUsuarioEditar
            || '''';
      END IF;

      IF p_strTxtAmbito <> '2' OR p_strTxtAmbito <> 'Default'
      THEN
         sqlquery :=
            sqlquery || ', AMBITO             = ''' || p_strTxtAmbito || '''';
      END IF;

      IF p_hdnCambioEstatus = 'Si'
      THEN
         sqlquery := sqlquery || ', INTENTOS_LOGIN   = 0 ';
         sqlquery := sqlquery || ', FECHA_STATUS     = SYSDATE ';
      END IF;

      sqlquery :=
         sqlquery || ' WHERE LOGIN = ''' || p_strTxtUsuarioEditar || ''' ';


      EXECUTE IMMEDIATE sqlquery;

      IF p_strTxtgrupo IS NOT NULL
      THEN
         sqlquery :=
               ' UPDATE USUGRP_v1 SET CODGRP = '''
            || p_strTxtgrupo
            || '''
                        ,FECHASIG = SYSDATE, 
                        USRID    = '''
            || p_strTxtCodPerCli
            || '''
                        WHERE LOGIN = '''
            || p_strTxtUsuarioEditar
            || '''';

         EXECUTE IMMEDIATE sqlquery;
      END IF;

      --COMMIT;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuarioeditar_pr;


   /******************************************************************************
     NAME:    bac_usuariosetpass_pr
     PURPOSE: Editar información del usuario encrypta password VBT_USERS
     ******************************************************************************/

   PROCEDURE bac_usuariosetpass_pr (p_strTxtUsuarioEditar   IN     VARCHAR2,
                                    p_miPasswTemp           IN     VARCHAR2,
                                    p_resultado                OUT VARCHAR2)
   AS
   BEGIN
      UPDATE VBT_USERS
         SET PASSWORD = SEGURIDAD.ENCRIPTA (p_miPasswTemp),
             --LAST_LOGIN     = NULL,
             INTENTOS_LOGIN = 0,
             STATUS_CUENTA = 'A',
             CAMBIO_PASS = 'S',
             FECHA_STATUS = SYSDATE
       WHERE LOGIN = p_strTxtUsuarioEditar;

      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuariosetpass_pr;


   /******************************************************************************
    NAME:    bac_usuariosconcon_pr
    PURPOSE: Consulta usuario de tablas vbtuser y VBT_USERS_CONTRATO
 ******************************************************************************/


   PROCEDURE bac_usuariosconcon_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_bac_usuariosconcon       OUT bac_usuariosconcon,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_usuariosconcon   bac_usuariosconcon;
   BEGIN
      OPEN cs_bac_usuariosconcon FOR
         SELECT USU.EMAIL, USU_CON.NUM_CONTRATO
           FROM VBT_USERS USU, VBT_USERS_CONTRATO USU_CON
          WHERE     USU.LOGIN = p_strTxtUsuarioEditar
                AND USU.LOGIN = USU_CON.LOGIN(+);

      p_bac_usuariosconcon := cs_bac_usuariosconcon;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuariosconcon_pr;


   /******************************************************************************
   NAME:    bac_usuariostacon_pr
   PURPOSE: Consulta usuario de tablas vbtuser y VBT_USERS_CONTRATO el estatus de los contratos
******************************************************************************/


   PROCEDURE bac_usuariostacon_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_bac_usuariostacon        OUT bac_usuariostacon,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_usuariostacon   bac_usuariostacon;
   BEGIN
      OPEN cs_bac_usuariostacon FOR
         SELECT STATUS
           FROM VBT_CONTRATO CON, VBT_USERS_CONTRATO USU_CON
          WHERE     USU_CON.LOGIN = p_strTxtUsuarioEditar
                AND USU_CON.NUM_CONTRATO = CON.NUM_CONTRATO;

      p_bac_usuariostacon := cs_bac_usuariostacon;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuariostacon_pr;



   /******************************************************************************
   NAME:    bac_usuariostacon
   PURPOSE: Consulta usuario de tablas vbtuser
   ******************************************************************************/

   PROCEDURE bac_usuarioconsul_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_bac_usuarioconsul        OUT bac_usuarioconsul,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_usuarioconsul   bac_usuarioconsul;
   BEGIN
      OPEN cs_bac_usuarioconsul FOR
         SELECT NOMBRES,
                DIRECCION,
                TELEFONO,
                TELEFONO_CELULAR,
                EMAIL,
                TIPO,
                STATUS_CUENTA,
                CIRIF,
                AMBITO,
                FNC_GET_NUMERICO(CODIGO_PAIS)
           FROM VBT_USERS
          WHERE LOGIN = p_strTxtUsuarioEditar;

      p_bac_usuarioconsul := cs_bac_usuarioconsul;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuarioconsul_pr;


   /******************************************************************************
      NAME:    bac_usuOpcionGrupoInt_pr
      PURPOSE: Para verificar que el usuario no tenga ya la misma configuración de privilegios
      ******************************************************************************/

   PROCEDURE bac_usuOpcionGrupoInt_pr (
      p_strTxtUsuarioEditar     IN     VARCHAR2,
      p_strTxtcodcia            IN     VARCHAR2,
      p_strTxtcodsis            IN     VARCHAR2,
      p_strTxtcodopc            IN     VARCHAR2,
      p_bac_usuOpcionGrupoInt      OUT bac_usuOpcionGrupoInt,
      p_resultado                  OUT VARCHAR2)
   AS
      cs_bac_usuOpcionGrupoInt   bac_usuOpcionGrupoInt;
   BEGIN
      OPEN cs_bac_usuOpcionGrupoInt FOR
         SELECT gco.tipacc accion
           FROM grpciaopc_v1 gco, usugrp_v1 ug, ctaopc_v1 o
          WHERE     gco.codopc = o.codopc
                AND o.codsis = gco.codsis
                AND ug.codgrp = gco.codgrp
                AND ug.codcia = gco.codcia
                AND ug.codsis = gco.codsis
                AND gco.codcia = p_strTxtcodcia
                AND gco.codsis = p_strTxtcodsis
                AND ug.login = p_strTxtUsuarioEditar
                AND o.codopc = p_strTxtcodopc;

      p_bac_usuOpcionGrupoInt := cs_bac_usuOpcionGrupoInt;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuOpcionGrupoInt_pr;


   /******************************************************************************
    NAME:    bac_usuOpcionUsuarioInt_pr
    PURPOSE: Para verificar que el usuario no tenga ya la misma configuración de privilegios
    ******************************************************************************/

   PROCEDURE bac_usuOpcionUsuarioInt_pr (
      p_strTxtUsuarioEditar       IN     VARCHAR2,
      p_strTxtcodcia              IN     VARCHAR2,
      p_strTxtcodsis              IN     VARCHAR2,
      p_strTxtcodopc              IN     VARCHAR2,
      p_bac_usuOpcionUsuarioInt      OUT bac_usuOpcionUsuarioInt,
      p_resultado                    OUT VARCHAR2)
   AS
      cs_bac_usuOpcionUsuarioInt   bac_usuOpcionUsuarioInt;
      V_PERMISOS_USUARIO  INTEGER :=0;
      V_PERMISOS_GRUPO    INTEGER :=0;
   BEGIN
   
         
        SELECT COUNT (UCO.TIPACC) 
            INTO V_PERMISOS_USUARIO
                FROM usrciaopc_v1 UCO
                        WHERE UCO.CODCIA=p_strTxtcodcia
                              AND UCO.CODSIS=p_strTxtcodsis
                              AND UCO.LOGIN= p_strTxtUsuarioEditar
                              AND UCO.CODOPC=p_strTxtcodopc;
                              
        SELECT COUNT (UCO.TIPACC) 
            INTO V_PERMISOS_GRUPO
                FROM GRPCIAOPC_V1 UCO, USUGRP_V1 UG
                        WHERE UG.LOGIN=p_strTxtUsuarioEditar
                              AND UG.CODGRP=UCO.CODGRP
                              AND UCO.CODCIA=p_strTxtcodcia
                              AND UCO.CODSIS=p_strTxtcodsis
                              AND UCO.CODOPC=p_strTxtcodopc;                       
            
            
      IF (V_PERMISOS_USUARIO>0) THEN
       OPEN cs_bac_usuOpcionUsuarioInt FOR
         SELECT  uco.tipacc  accion
           FROM usrciaopc_v1 uco, ctaopc_v1 o
          WHERE uco.codopc(+) = o.codopc
                AND o.codsis = uco.codsis(+)
                AND uco.codcia(+) = p_strTxtcodcia
                AND uco.codsis(+) = p_strTxtcodsis
                AND uco.login(+) = p_strTxtUsuarioEditar
                AND o.codopc = p_strTxtcodopc;
                
                
      ELSE
      
          IF (V_PERMISOS_GRUPO>0) THEN
           OPEN cs_bac_usuOpcionUsuarioInt FOR
             SELECT UCO.TIPACC accion
                FROM GRPCIAOPC_V1 UCO, USUGRP_V1 UG
                        WHERE UG.LOGIN=p_strTxtUsuarioEditar
                              AND UG.CODGRP=UCO.CODGRP
                              AND UCO.CODCIA=p_strTxtcodcia
                              AND UCO.CODSIS=p_strTxtcodsis
                              AND UCO.CODOPC=p_strTxtcodopc;
          ELSE
             OPEN cs_bac_usuOpcionUsuarioInt FOR
                SELECT '00000000' accion FROM DUAL;
          END IF;
      
      END IF;    

      p_bac_usuOpcionUsuarioInt := cs_bac_usuOpcionUsuarioInt;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuOpcionUsuarioInt_pr;

   /******************************************************************************
NAME:    bac_OpcionUsuario_pr
PURPOSE: Para verificar que el usuario no tenga ya la misma configuración de privilegios
******************************************************************************/

   PROCEDURE bac_OpcionUsuario_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_strTxtcodcia          IN     VARCHAR2,
      p_strTxtcodsis          IN     VARCHAR2,
      p_strTxtcodopc          IN     VARCHAR2,
      p_bac_OpcionUsuario        OUT bac_OpcionUsuario,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_OpcionUsuario   bac_OpcionUsuario;
   BEGIN
      OPEN cs_bac_OpcionUsuario FOR
         SELECT uco.tipacc accion
           FROM usrciaopc_v1 uco, ctaopc_v1 o
          WHERE     uco.codopc = o.codopc
                AND o.codsis = uco.codsis
                AND uco.codcia = p_strTxtcodcia
                AND uco.codsis = p_strTxtcodsis
                AND uco.login = p_strTxtUsuarioEditar
                AND o.codopc = p_strTxtcodopc;

      p_bac_OpcionUsuario := cs_bac_OpcionUsuario;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_OpcionUsuario_pr;


   /******************************************************************************
  NAME:    bac_usudel_insr_pr
  PURPOSE: Borra o inserta registros en tabla usrciaopc_v1
  ******************************************************************************/

   PROCEDURE bac_usudel_insr_pr (p_strTxtUsuarioEditar   IN     VARCHAR2,
                                 p_strTxtcodcia          IN     VARCHAR2,
                                 p_strTxtcodsis          IN     VARCHAR2,
                                 p_strTxtcodopc          IN     VARCHAR2,
                                 p_strTxttipacc          IN     VARCHAR2,
                                 p_strflag               IN     VARCHAR2,
                                 p_resultado                OUT VARCHAR2)
   AS
   BEGIN
      IF p_strflag = '1'
      THEN
         DELETE FROM usrciaopc_v1
               WHERE login = p_strTxtUsuarioEditar;
      ELSIF p_strflag = '2'
      THEN
         INSERT INTO usrciaopc_v1 (login,
                                   codcia,
                                   codsis,
                                   codopc,
                                   tipacc,
                                   usrid)
              VALUES (p_strTxtUsuarioEditar,
                      p_strTxtcodcia,
                      p_strTxtcodsis,
                      p_strTxtcodopc,
                      p_strTxttipacc,
                      p_strTxtUsuarioEditar);
      END IF;

      p_resultado := 'OK';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usudel_insr_pr;



   /******************************************************************************
  NAME:    bac_usudel_insr_pr
  PURPOSE: Actuliza en tabla vbt_users estatus de la cuenta  y grupo
  ******************************************************************************/

   PROCEDURE bac_usuclienteeditar_pr (
      p_strTxtEmailEditar          IN     VARCHAR2,
      p_hdnCambioEstatus           IN     VARCHAR2,
      p_strCmbStatusCuentaEditar   IN     VARCHAR2,
      p_strTxtUsuarioEditar        IN     VARCHAR2,
      p_strCmbTipoUsuarioEditar    IN     VARCHAR2,
      p_strCmbAmbitoEditar         IN     VARCHAR2,
      p_login                      IN     VARCHAR2,
      p_telefono_local             IN     VARCHAR2,
      p_telefono_celular           IN     VARCHAR2,
      p_codigo_pais                IN     VARCHAR2,
      p_nombre                     IN     VARCHAR2,                         
      p_direccion                  IN     VARCHAR2,                     
      p_rif                        IN     VARCHAR2,                           
      p_resultado                     OUT VARCHAR2)
   AS
      sqlquery   VARCHAR2 (1000);
   BEGIN
      sqlquery := 'UPDATE VBT_USERS SET ';

      IF p_strTxtEmailEditar <> 'Default'
      THEN
         sqlquery :=
            sqlquery || ' EMAIL = lower(''' || p_strTxtEmailEditar || ''')';
      END IF;

      IF p_hdnCambioEstatus = 'Si' AND p_strCmbStatusCuentaEditar IS NOT NULL
      THEN
         IF p_strTxtEmailEditar <> 'Default'
         THEN
            sqlquery :=
                  sqlquery
               || ', STATUS_CUENTA = '''
               || p_strCmbStatusCuentaEditar
               || ''' , INTENTOS_LOGIN = 0, FECHA_STATUS = SYSDATE';
         END IF;
      END IF;


      IF p_telefono_local IS NOT NULL
      THEN
         sqlquery :=
            sqlquery || ' , TELEFONO = ''' || p_telefono_local || '''';
      END IF;

      IF p_telefono_celular IS NOT NULL
      THEN
         sqlquery :=
               sqlquery
            || ' , TELEFONO_CELULAR = '''
            || p_telefono_celular
            || '''';
      END IF;
      
      IF p_codigo_pais IS NOT NULL
      THEN
         sqlquery :=
               sqlquery
            || ' , CODIGO_PAIS = '''
            || p_codigo_pais
            || '''';
      END IF;

      IF p_nombre IS NOT NULL
      THEN
         sqlquery :=
               sqlquery
            || ' , NOMBRES = '''
            || p_nombre
            || '''';
      END IF;
      
      IF p_direccion IS NOT NULL
      THEN
         sqlquery :=
               sqlquery
            || ' , DIRECCION = '''
            || p_direccion
            || '''';
      END IF;
      
     IF p_rif IS NOT NULL
      THEN
         sqlquery :=
               sqlquery
            || ' , CIRIF = '''
            || p_rif
            || '''';
      END IF;


      IF p_strCmbAmbitoEditar <> 'Default' AND p_strCmbAmbitoEditar <>'Commit'
      THEN
      
         sqlquery :=
            sqlquery || ', AMBITO = ''' || p_strCmbAmbitoEditar || '''';
            
            
      END IF;

      sqlquery :=
         sqlquery || ' WHERE LOGIN = ''' || p_strTxtUsuarioEditar || '''';

      --   if p_verifica_estatus = true or p_verifica_editar = true then

      IF    p_strTxtEmailEditar <> 'Default'
         OR (    p_hdnCambioEstatus = 'Si'
             AND p_strCmbStatusCuentaEditar IS NOT NULL)
      THEN
         EXECUTE IMMEDIATE sqlquery;

         --COMMIT;
      END IF;

      IF p_strCmbTipoUsuarioEditar <> 'Default'
      THEN
         sqlquery :=
               ' UPDATE USUGRP_v1 SET CODGRP = '''
            || p_strCmbTipoUsuarioEditar
            || ''',
                      FECHASIG = SYSDATE, 
                      USRID    = '''
            || p_login
            || '''
                     WHERE LOGIN = '''
            || p_strTxtUsuarioEditar
            || '''';

         EXECUTE IMMEDIATE sqlquery;

         --COMMIT;
      END IF;



      --   end if;


      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuclienteeditar_pr;


   /******************************************************************************
  NAME:   bac_usueditar_con_pr
  PURPOSE: Devuelve registros de tablas VBT_USERS Y USUGRP_v1
  ******************************************************************************/


   PROCEDURE bac_usueditar_con_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_bac_usueditar_con        OUT bac_usueditar_con,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_usueditar_con   bac_usueditar_con;
   BEGIN
      OPEN cs_bac_usueditar_con FOR
         SELECT USU.NOMBRES,
                USU.DIRECCION,
                USU.TELEFONO,
                USU.TELEFONO_CELULAR,
                USU.EMAIL,
                USU.TIPO,
                USU.STATUS_CUENTA,
                USU.CIRIF,
                USU.CODPERCLI,
                GRP.CODGRP,
                USU.AMBITO,
                CON.STATUS,
                FNC_GET_NUMERICO(USU.CODIGO_PAIS)
           FROM VBT_USERS USU,
                USUGRP_v1 GRP,
                VBT_CONTRATO CON,
                VBT_USERS_CONTRATO USRC
          WHERE     USU.LOGIN = LOWER (p_strTxtUsuarioEditar)
                AND USU.LOGIN = GRP.LOGIN
                AND CON.NUM_CONTRATO = USRC.NUM_CONTRATO
                AND USRC.LOGIN = USU.LOGIN;


      p_bac_usueditar_con := cs_bac_usueditar_con;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usueditar_con_pr;


     /******************************************************************************
NAME:    bac_elementostipo_pr
PURPOSE: Query para obtener todos los estatus
******************************************************************************/

   PROCEDURE bac_elementostipo_pr (
      p_strTxttipo          IN     VARCHAR2,
      p_bac_elementostipo      OUT bac_elementostipo,
      p_resultado              OUT VARCHAR2)
   AS
      cs_bac_elementostipo   bac_elementostipo;
   BEGIN
      IF (p_strTxttipo = '0000000002')
      THEN
         OPEN cs_bac_elementostipo FOR
              SELECT codelemento, descripcion
                FROM elementos_tipos
               WHERE codtipo = p_strTxttipo
            ORDER BY DESCRIPCION ASC;

         p_bac_elementostipo := cs_bac_elementostipo;
         p_resultado := 'OK';
      ELSE
         OPEN cs_bac_elementostipo FOR
              SELECT codelemento, descripcion
                FROM elementos_tipos
               WHERE codtipo = p_strTxttipo
            ORDER BY DESCRIPCION ASC;

         p_bac_elementostipo := cs_bac_elementostipo;
         p_resultado := 'OK';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_elementostipo_pr;


   /******************************************************************************
 NAME:    bac_correoenvio_pr
 PURPOSE: Query para obtener la dirrecion de correo electronico x codpercli
 ******************************************************************************/

   PROCEDURE bac_correoenvio_pr (p_strTxtcodpercli   IN     VARCHAR2,
                                 p_bac_correoenvio      OUT bac_correoenvio,
                                 p_resultado            OUT VARCHAR2)
   AS
      cs_bac_correoenvio   bac_correoenvio;
   BEGIN
      OPEN cs_bac_correoenvio FOR
         SELECT LOWER (DIRECC1) EMAIL, FLAG_ENVIO DE_ENVIO
           FROM PRODUCCION.DIRECCIONES
          WHERE     CODPERCLI = p_strTxtcodpercli
                AND TIPODIR = 'Email Online'
                AND UPPER (DIRECC1) <> 'HOLD MAIL';

      p_bac_correoenvio := cs_bac_correoenvio;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_correoenvio_pr;

   /******************************************************************************
 NAME:    bac_correoenvio_login_pr
 PURPOSE: Query para obtener la dirrecion de correo electronico x codpercli
 ******************************************************************************/

   PROCEDURE bac_correoenvio_login_pr (
      p_strTxtcodpercli   IN     VARCHAR2,
      p_strTxtlogin       IN     VARCHAR2,
      p_bac_correoenvio      OUT bac_correoenvio,
      p_resultado            OUT VARCHAR2)
   AS
      cs_bac_correoenvio   bac_correoenvio;
   BEGIN
      OPEN cs_bac_correoenvio FOR
         SELECT LOWER (EMAIL) EMAIL, 'X', '1' ORDEN
           FROM VBT_USERS
          WHERE LOGIN = p_strTxtlogin
         UNION ALL
         SELECT LOWER (DIRECC1) EMAIL, FLAG_ENVIO DE_ENVIO, '2' ORDEN
           FROM PRODUCCION.DIRECCIONES
          WHERE     CODPERCLI = p_strTxtcodpercli
                AND TIPODIR = 'Email Online'
                AND UPPER (DIRECC1) <> 'HOLD MAIL'
                AND LOWER (DIRECC1) NOT IN (SELECT LOWER (EMAIL) EMAIL
                                              FROM VBT_USERS
                                             WHERE LOGIN = p_strTxtlogin)
         ORDER BY ORDEN;

      p_bac_correoenvio := cs_bac_correoenvio;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_correoenvio_login_pr;

   /******************************************************************************
   NAME:   bac_telefonoenvio_login_pr
   PURPOSE: Query para obtener los telefonos por login y codpercli
   ******************************************************************************/
   PROCEDURE bac_telefonoenvio_login_pr (
      p_strTxtcodpercli     IN     VARCHAR2,
      p_strTxtlogin         IN     VARCHAR2,
      p_bac_telefonoenvio      OUT bac_telefonoenvio,
      p_resultado              OUT VARCHAR2)
   AS
      cs_bac_telefonoenvio   bac_telefonoenvio;
   BEGIN
      OPEN cs_bac_telefonoenvio FOR
      
            SELECT FNC_GET_NUMERICO (TEL), ORDEN, CODIGO
                FROM (SELECT TELEFONO_CELULAR TEL,
                             '1' ORDEN,
                             FNC_GET_NUMERICO (CODIGO_PAIS) CODIGO
                        FROM VBT_USERS
                       WHERE LOGIN = p_strTxtlogin
                      UNION ALL
                      SELECT T.AREA || T.TELEFONO TEL,
                             '3' ORDEN,
                             FNC_GET_NUMERICO (T.CODPAIS) CODIGO
                        FROM PRODUCCION.TELEFONOS T
                       WHERE     T.CODPERCLI = p_strTxtcodpercli
                             AND T.TIPO = 'Celular'
                             AND NOT EXISTS
                                        (SELECT V.LOGIN
                                           FROM VBT_USERS V
                                          WHERE     NVL (V.TELEFONO_CELULAR, '@') =
                                                       NVL (TRIM (T.AREA || T.TELEFONO),
                                                            '@')
                                                AND V.CODIGO_PAIS = T.CODPAIS
                                                AND V.LOGIN = p_strTxtlogin)) A
               WHERE TEL IS NOT NULL
            ORDER BY ORDEN;

      p_bac_telefonoenvio := cs_bac_telefonoenvio;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_telefonoenvio_login_pr;

   /******************************************************************************
   NAME:   bac_telefonoenvio_login_pr
   PURPOSE: Query para obtener los telefonos por login y codpercli
   ******************************************************************************/
   PROCEDURE bac_telefonolocal_login_pr (
      p_strTxtcodpercli     IN     VARCHAR2,
      p_strTxtlogin         IN     VARCHAR2,
      p_bac_telefonolocal      OUT bac_telefonolocal,
      p_resultado              OUT VARCHAR2)
   AS
      cs_bac_telefonolocal   bac_telefonolocal;
   BEGIN
      OPEN cs_bac_telefonolocal FOR
           SELECT FNC_GET_NUMERICO (TEL), ORDEN
             FROM (SELECT TELEFONO TEL, '2' ORDEN
                     FROM VBT_USERS
                    WHERE LOGIN = p_strTxtlogin
                   UNION ALL
                   SELECT AREA || TELEFONO TEL, '3' ORDEN
                     FROM PRODUCCION.TELEFONOS
                    WHERE     CODPERCLI = p_strTxtcodpercli
                          AND tipo NOT IN ('Celular')
                          AND TRIM (AREA || TELEFONO) NOT IN
                                 (SELECT NVL (TELEFONO, ' ')
                                    FROM VBT_USERS
                                   WHERE LOGIN = p_strTxtlogin)) A
         -- WHERE TEL IS NOT NULL
         ORDER BY ORDEN;

      p_bac_telefonolocal := cs_bac_telefonolocal;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_telefonolocal_login_pr;



   /******************************************************************************
 NAME:    bac_grupocliente_pr
 PURPOSE: Query para obtener todos los Grupos Clientes
 ******************************************************************************/

   PROCEDURE bac_grupocliente_pr (p_bac_grupocliente   OUT bac_grupocliente,
                                  p_resultado          OUT VARCHAR2)
   AS
      cs_bac_grupocliente   bac_grupocliente;
   BEGIN
      OPEN cs_bac_grupocliente FOR
           SELECT codgrp, nombgrp
             FROM ctagrp
            WHERE categoria = 'C' --OR categoria = 'FC'                       --OR categoria = 'FC'
         ORDER BY nombgrp ASC;

      p_bac_grupocliente := cs_bac_grupocliente;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_grupocliente_pr;


     /******************************************************************************
NAME:    bac_usuopcionsis_pr
PURPOSE: Query para obtener todos los Opciones del sistema de los usuarios
******************************************************************************/




  PROCEDURE bac_grupocliente_categoria_pr (p_categoria IN VARCHAR2, p_bac_grupocliente   OUT bac_grupocliente,
                                  p_resultado          OUT VARCHAR2)
   AS
      cs_bac_grupocliente   bac_grupocliente;
   BEGIN
     
      IF p_categoria='C' THEN
           OPEN cs_bac_grupocliente FOR
               SELECT codgrp, nombgrp
                 FROM ctagrp
                WHERE categoria = 'C' AND codgrp<>'0000000016'
             ORDER BY nombgrp ASC;
      ELSE
          OPEN cs_bac_grupocliente FOR
               SELECT codgrp, nombgrp
                 FROM ctagrp
                WHERE categoria = 'C' AND codgrp='0000000016'
             ORDER BY nombgrp ASC;
      END IF;

      p_bac_grupocliente := cs_bac_grupocliente;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_grupocliente_categoria_pr;

   PROCEDURE bac_usuopcionsis_pr (
      p_strCmbTipoUsuarioEditar   IN     VARCHAR2,
      p_strCmbcodsys              IN     VARCHAR2,
      p_bac_usuopcionsis             OUT bac_usuopcionsis,
      p_resultado                    OUT VARCHAR2)
   AS
      cs_bac_usuopcionsis   bac_usuopcionsis;
   BEGIN
      OPEN cs_bac_usuopcionsis FOR
           SELECT DISTINCT
                  DECODE (NVL (padcodopc, ''), '', codopc, padcodopc)
                     codigo_padre,
                  TO_NUMBER (nivel) opcion_nivel,
                  TO_NUMBER (orden) opcion_orden,
                  desopc opcion_descripcion,
                  codopc opcion_codigo
             FROM ctaopc_v1
            WHERE     codsis = p_strCmbcodsys
                  AND (   selectora = p_strCmbTipoUsuarioEditar
                       OR selectora IS NULL)
         ORDER BY codigo_padre ASC,
                  opcion_nivel ASC,
                  opcion_orden ASC,
                  opcion_descripcion ASC,
                  opcion_codigo ASC;

      p_bac_usuopcionsis := cs_bac_usuopcionsis;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuopcionsis_pr;


    /******************************************************************************
NAME:    bac_gruposcon_pr
PURPOSE: Query para obtener todos los Grupos de uaurios
******************************************************************************/


   PROCEDURE bac_gruposcon_pr (p_bac_gruposcon   OUT bac_gruposcon,
                               p_resultado       OUT VARCHAR2)
   AS
      cs_bac_gruposcon   bac_gruposcon;
   BEGIN
      OPEN cs_bac_gruposcon FOR
           SELECT codgrp codigo, nombgrp nombre, categoria categoria
             FROM ctagrp
         ORDER BY nombgrp ASC;

      p_bac_gruposcon := cs_bac_gruposcon;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_gruposcon_pr;



   /******************************************************************************
 NAME:    bac_gruposgrp_pr
 PURPOSE: Para verificar si el grupo tiene usuarios asociados
 ******************************************************************************/


   PROCEDURE bac_gruposgrp_pr (p_strCmbcodgrp    IN     VARCHAR2,
                               p_bac_gruposgrp      OUT bac_gruposgrp,
                               p_resultado          OUT VARCHAR2)
   AS
      cs_bac_gruposgrp   bac_gruposgrp;
   BEGIN
      OPEN cs_bac_gruposgrp FOR
         SELECT *
           FROM usugrp_v1
          WHERE codgrp = p_strCmbcodgrp;

      p_bac_gruposgrp := cs_bac_gruposgrp;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_gruposgrp_pr;


   /******************************************************************************
   NAME:    bac_gruposopc_pr
   PURPOSE: Para verificar si el grupo tiene opciones asociadas
   ******************************************************************************/

   PROCEDURE bac_gruposopc_pr (p_strCmbcodopc    IN     VARCHAR2,
                               p_bac_gruposopc      OUT bac_gruposopc,
                               p_resultado          OUT VARCHAR2)
   AS
      cs_bac_gruposopc   bac_gruposopc;
   BEGIN
      OPEN cs_bac_gruposopc FOR
         SELECT *
           FROM grpciaopc_v1
          WHERE codgrp = p_strCmbcodopc;

      p_bac_gruposopc := cs_bac_gruposopc;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_gruposopc_pr;


   /******************************************************************************
NAME:    bac_gruposopcsis_pr
PURPOSE: devuelve las opciones y los niveles de los grupos y los sistemas
******************************************************************************/


   PROCEDURE bac_gruposopcsis_pr (p_strCmbcodsis       IN     VARCHAR2,
                                  p_editar_categoria   IN     VARCHAR2,
                                  p_bac_gruposopcsis      OUT SYS_REFCURSOR,
                                  p_resultado             OUT VARCHAR2)
   AS
      sqlstring   VARCHAR2 (4000);
   BEGIN
      IF p_editar_categoria = 'FC'
      THEN
         sqlstring :=
               'SELECT DISTINCT DECODE(NVL(padcodopc,''''),'''',codopc,padcodopc) codigo_padre 
                     ,TO_NUMBER(nivel) opcion_nivel 
                     ,TO_NUMBER(orden) opcion_orden 
                     ,desopc opcion_descripcion 
                     ,codopc opcion_codigo 
                     FROM       ctaopc_v1 
                     WHERE    codsis = '''
            || p_strCmbcodsis
            || '''
                     AND      (selectora = ''FC'' OR selectora=''C''  OR selectora is null) 
                     ORDER BY    codigo_padre ASC 
                     ,opcion_nivel ASC 
                     ,opcion_orden ASC 
                     ,opcion_descripcion ASC 
                     ,opcion_codigo ASC';
      ELSE
         sqlstring :=
               'SELECT DISTINCT DECODE(NVL(padcodopc,''''),'''',codopc,padcodopc) codigo_padre 
                     ,TO_NUMBER(nivel) opcion_nivel 
                     ,TO_NUMBER(orden) opcion_orden 
                     ,desopc opcion_descripcion 
                     ,codopc opcion_codigo 
                     FROM       ctaopc_v1 
                     WHERE    codsis = '''
            || p_strCmbcodsis
            || '''
                     AND      (selectora = '''
            || p_editar_categoria
            || '''  OR selectora is null) 
                     ORDER BY    codigo_padre ASC 
                     ,opcion_nivel ASC 
                     ,opcion_orden ASC 
                     ,opcion_descripcion ASC 
                     ,opcion_codigo ASC';
      END IF;



      OPEN p_bac_gruposopcsis FOR sqlstring;

      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_gruposopcsis_pr;

   /******************************************************************************
 NAME:    bac_grpudel_insr_pr
 PURPOSE: Borra o inserta registros en tabla grpciaopc_v1
 ******************************************************************************/

   PROCEDURE bac_grpudel_insr_pr (p_strTxtUsuarioEditar   IN     VARCHAR2,
                                  p_strTxtcodgrp          IN     VARCHAR2,
                                  p_strTxtcodcia          IN     VARCHAR2,
                                  p_strTxtcodsis          IN     VARCHAR2,
                                  p_strTxtcodopc          IN     VARCHAR2,
                                  p_strTxttipacc          IN     VARCHAR2,
                                  p_strflag               IN     VARCHAR2,
                                  p_resultado                OUT VARCHAR2)
   AS
   BEGIN
      IF p_strflag = '1'
      THEN
         DELETE FROM grpciaopc_v1
               WHERE     codgrp = p_strTxtcodgrp
                     AND codcia = p_strTxtcodcia
                     AND codsis = p_strTxtcodsis
                     AND codopc = p_strTxtcodopc;
      ELSIF p_strflag = '2'
      THEN
         INSERT INTO grpciaopc_v1 (codgrp,
                                   codcia,
                                   codsis,
                                   codopc,
                                   tipacc,
                                   usrid)
              VALUES (p_strTxtcodgrp,
                      p_strTxtcodcia,
                      p_strTxtcodsis,
                      p_strTxtcodopc,
                      p_strTxttipacc,
                      p_strTxtUsuarioEditar);
      END IF;

      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_grpudel_insr_pr;


   PROCEDURE bac_permisosEditarUsuario_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_strTxtLogin           IN     VARCHAR2,
      p_strTxtcodcia          IN     VARCHAR2,
      p_strTxtcodsis          IN     VARCHAR2,
      p_strTxtcodopc          IN     VARCHAR2,
      p_strTxttipacc          IN     VARCHAR2,
      p_strflag               IN     VARCHAR2,
      p_resultado                OUT VARCHAR2)
   AS
   BEGIN
      IF p_strflag = '1'
      THEN
         DELETE FROM usrciaopc_v1
               WHERE     login = p_strTxtUsuarioEditar
                     AND codcia = p_strTxtcodcia
                     AND codsis = p_strTxtcodsis
                     AND codopc = p_strTxtcodopc;
      ELSIF p_strflag = '2'
      THEN
         INSERT INTO usrciaopc_v1 (login,
                                   codcia,
                                   codsis,
                                   codopc,
                                   tipacc,
                                   usrid)
              VALUES (p_strTxtUsuarioEditar,
                      p_strTxtcodcia,
                      p_strTxtcodsis,
                      p_strTxtcodopc,
                      p_strTxttipacc,
                      p_strTxtLogin);
      END IF;

      p_resultado := 'OK';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_permisosEditarUsuario_pr;

   /******************************************************************************
  NAME:    bac_grpuopcper_pr
  PURPOSE: Borra o inserta registros en tabla grpciaopc_v1
  ******************************************************************************/

   PROCEDURE bac_grpuopcper_pr (
      p_strTxtUsuarioEditar   IN     VARCHAR2,
      p_strTxtcodgrp          IN     VARCHAR2,
      p_strTxtcodcia          IN     VARCHAR2,
      p_strTxtcodsis          IN     VARCHAR2,
      p_strTxtcodopc          IN     VARCHAR2,
      p_strTxttipacc          IN     VARCHAR2,
      p_strflag               IN     VARCHAR2,
      p_bac_grpuopcper           OUT bac_grpuopcper,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_grpuopcper   bac_grpuopcper;
   BEGIN
      OPEN cs_bac_grpuopcper FOR
         SELECT gco.tipacc accion
           FROM grpciaopc_v1 gco, ctaopc_v1 o
          WHERE     gco.codopc = o.codopc
                AND gco.codsis = o.codsis
                AND gco.codgrp = p_strTxtcodgrp
                AND gco.codopc = p_strTxtcodopc
                AND gco.codsis = p_strTxtcodsis
                AND gco.codcia = p_strTxtcodcia;

      p_bac_grpuopcper := cs_bac_grpuopcper;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_grpuopcper_pr;


      /******************************************************************************
NAME:    bac_contratousuarios_pr
PURPOSE: Devuelve usuarios de tabla vbt_users
******************************************************************************/

   PROCEDURE bac_contratousuarios_pr (
      p_bac_contratousuarios   OUT bac_contratousuarios,
      p_resultado              OUT VARCHAR2)
   AS
      cs_bac_contratousuarios   bac_contratousuarios;
   BEGIN
      OPEN cs_bac_contratousuarios FOR
        SELECT DISTINCT creado_por
           FROM vbt_contrato
          ORDER BY creado_por ASC;

      p_bac_contratousuarios := cs_bac_contratousuarios;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contratousuarios_pr;


   /******************************************************************************
 NAME:    bac_contratousugrupo_pr
 PURPOSE: Devuelve usuarios de tabla vbt_users, vbt_users_contrato, usugrp_v1
 ******************************************************************************/

   PROCEDURE bac_contratousugrupo_pr (
      p_strContrato            IN     VARCHAR2,
      p_strActiva              IN     VARCHAR2,
      p_strInactiva            IN     VARCHAR2,
      p_strCancelada           IN     VARCHAR2,
      p_strBloqueado           IN     VARCHAR2,
      p_strDesconocido         IN     VARCHAR2,
      p_bac_contratousugrupo      OUT bac_contratousugrupo,
      p_resultado                 OUT VARCHAR2)
   AS
      cs_bac_contratousugrupo   bac_contratousugrupo;
   BEGIN
      OPEN cs_bac_contratousugrupo FOR
           SELECT USU.login,
                  TO_CHAR (USU.fecha_ingreso, 'dd/mm/yyyy hh:mi A.M.'),
                  USU.nombres,
                  USU.email,
                  TO_CHAR (USU.fecha_login, 'dd/mm/yyyy hh:mi A.M.')
                     ULTIMO_LOGIN,
                  DECODE (USU.status_cuenta,
                          'A', p_strActiva,
                          'I', p_strInactiva,
                          'C', p_strCancelada,
                          'B', p_strBloqueado,
                          p_strDesconocido)
                     STATUS,
                  TO_CHAR (USU.fecha_status, 'dd/mm/yyyy'),
                  GRP.nombgrp,
                  USU.cirif,
                  USU.direccion,
                  USU.status_cuenta,
                  USU.tipo,
                  C.TIPO_CONTRATO,
                  USU.TELEFONO_CELULAR,
                  USU.CODIGO_PAIS
             FROM vbt_users USU,
                  vbt_users_contrato USU_CON,
                  usugrp_v1,
                  ctagrp GRP,
                  vbt_contrato C
            WHERE     USU_CON.num_contrato = p_strContrato
                  AND USU_CON.login = USU.login
                  AND USU.login = usugrp_v1.login
                  AND usugrp_v1.codgrp = GRP.codgrp
                  AND GRP.CATEGORIA<>'FC'
                  AND C.NUM_CONTRATO=USU_CON.NUM_CONTRATO
         ORDER BY USU.fecha_ingreso ASC;

      p_bac_contratousugrupo := cs_bac_contratousugrupo;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contratousugrupo_pr;


   /******************************************************************************
   NAME:    bac_contratos_pr
   PURPOSE: Devuelve informacion de los contratos, vbt_users_contrato
   ******************************************************************************/
   /*
   PROCEDURE bac_contratos_pr (p_strNuevo     IN VARCHAR2,
                                      p_strActivo         IN VARCHAR2,
                                      p_strCancelado     IN VARCHAR2,
                                      p_strInactivo     IN VARCHAR2,
                                      p_strRechazado     IN VARCHAR2,
                                      p_strDesconocido  IN VARCHAR2,
                                      p_hdnAccion        IN VARCHAR2,
                                      p_txtNumeroCartera IN VARCHAR2,
                                      p_strTipoUsuario    IN VARCHAR2,
                                      p_login            IN VARCHAR2,
                                      p_strCmbCreadoPor    IN VARCHAR2,
                                      p_strTxtNumeroContrato    IN VARCHAR2,
                                      p_strTxtUsuario             IN VARCHAR2,
                                      p_strTxtNombreCliente        IN VARCHAR2,
                                      p_strTxtCIRIFCliente        IN VARCHAR2,
                                      p_strTxtDesde                IN VARCHAR2,
                                      p_strTxtHasta                IN VARCHAR2,
                                      p_strOrden                IN VARCHAR2,
                                      p_bac_contratos OUT bac_contratos,
                                        p_resultado OUT VARCHAR2) AS
                                 cs_bac_contratos bac_contratos;
                                 sqlsquery      VARCHAR2(2000);
                                 sqlele         VARCHAR2(200);
                                 p_strCmbEstatus  VARCHAR2(2);
        begin
   --     null;

        sqlsquery := 'SELECT DISTINCT CON.num_contrato,
         CON.descripcion,
         CON.creado_por,
         TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy HH:MI A.M.''),
         DECODE(CON.status,''0'','''||p_strNuevo||''',''1'','''||p_strActivo||''',''2'','''||p_strCancelado||''',''3'','''||p_strInactivo||''',''4'','''||p_strRechazado||''','''||p_strDesconocido||''') status,
         CON.status CODIGO_ESTATUS,
         TO_CHAR(CON.FECHA_STATUS,''dd/mm/yyyy'') FECHA_STATUS,
         CON.fecha_creacion
         FROM vbt_contrato CON
         ,vbt_users USU
         ,vbt_users_contrato USU_CON';

        IF p_hdnAccion IS NOT NULL AND p_txtNumeroCartera <> '' THEN
            sqlsquery := sqlsquery || ', vbt_contrato_carteras CON_CAR';
            sqlele :=  ' AND CON.num_contrato = CON_CAR.num_contrato';
        else
            sqlele :=  ' ';
        END IF;
            sqlsquery := sqlsquery || ' WHERE USU_CON.login = USU.login  AND USU_CON.num_contrato = CON.num_contrato  AND USU.tipo = ''2''';
            sqlsquery := sqlsquery || sqlele;
        IF     p_strTipoUsuario='1' THEN
            IF p_hdnAccion IS NULL THEN
                sqlsquery := sqlsquery || ' AND CON.status = ''0''';
                p_strCmbEstatus := '0';
            END IF;
        ELSIF p_strTipoUsuario ='0'    THEN
            IF p_hdnAccion IS NULL THEN
                sqlsquery := sqlsquery || ' AND CON.status = ''4'' AND CON.creado_por = '''|| p_login ||'''';
                p_strCmbEstatus:='4'; -- Rechazado
            END IF;
        END IF;

            IF p_strTxtNumeroContrato IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND CON.num_contrato = '''||p_strTxtNumeroContrato||'''';
            ELSIF p_strTxtUsuario IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND AND USU.login = '''|| p_strTxtUsuario||'''';
            ELSIF p_strCmbCreadoPor IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND CON.creado_por = LOWER('''||p_strCmbCreadoPor||''')';
            ELSIF p_strTxtNombreCliente IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND UPPER(USU.nombres) LIKE '''||p_strTxtNombreCliente||'''';
            ELSIF p_strTxtCIRIFCliente IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND USU.cirif LIKE  '''||p_strTxtCIRIFCliente||'''';
            ELSIF p_strCmbEstatus IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND CON.status = '''||p_strCmbEstatus||'''';
            ELSIF p_strTxtDesde IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||p_strTxtDesde||''',''dd/mm/yyyy''))';
            ELSIF p_strTxtHasta IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||p_strTxtHasta||''',''dd/mm/yyyy''))';
            ELSIF p_txtNumeroCartera IS NOT NULL THEN
                sqlsquery := sqlsquery || '    AND CON_CAR.codcar LIKE    ''' ||'%' ||p_txtNumeroCartera||'%' ||'''';
            END IF;

        IF p_strOrden='Contrato' THEN
                sqlsquery := sqlsquery || ' ORDER BY TO_NUMBER(CON.num_contrato) ASC';
        ELSIF  p_strOrden='CreadoPor' THEN
                sqlsquery := sqlsquery || ' ORDER BY CON.creado_por ASC';
        ELSIF  p_strOrden='Estatus' THEN
                sqlsquery := sqlsquery || ' ORDER BY status ASC, TO_NUMBER(CON.num_contrato) DESC';
        ELSE
                sqlsquery := sqlsquery || ' ORDER BY CON.fecha_creacion DESC';
        END IF;

            execute immediate sqlsquery into cs_bac_contratos;
            p_bac_contratos:= cs_bac_contratos;
            p_resultado:= 'OK';
         -- commit;
    EXCEPTION
    WHEN OTHERS THEN
        p_resultado:= SUBSTR(SQLERRM,1,300);

   end bac_contratos_pr;
*/
   /*
      PROCEDURE contratosnew_pr (
                                         p_strNuevo                 IN VARCHAR2,
                                         p_strActivo                 IN VARCHAR2,
                                         p_strCancelado             IN VARCHAR2,
                                         p_strInactivo             IN VARCHAR2,
                                         p_strRechazado             IN VARCHAR2,
                                         p_strDesconocido          IN VARCHAR2,
                                         p_hdnAccion                IN VARCHAR2,
                                         p_txtNumeroCartera         IN VARCHAR2,
                                         p_strTipoUsuario            IN VARCHAR2,
                                         p_login                    IN VARCHAR2,
                                         p_strCmbCreadoPor            IN VARCHAR2,
                                         p_strTxtNumeroContrato    IN VARCHAR2,
                                         p_strTxtUsuario           IN VARCHAR2,
                                         p_strTxtNombreCliente     IN VARCHAR2,
                                         p_strTxtCIRIFCliente      IN VARCHAR2,
                                         p_strTxtDesde             IN VARCHAR2,
                                         p_strTxtHasta             IN VARCHAR2,
                                         p_strOrden                IN VARCHAR2,
                                         p_bac_contratos OUT bac_contratos,
                                         p_resultado OUT VARCHAR2,
                                         p_salida OUT VARCHAR2) AS
                                           cs_bac_contratos bac_contratos;
                                           sqlsquery      VARCHAR2(1500);
                                           sqlele         VARCHAR2(200);
                                           p_strCmbEstatus  VARCHAR2(2);
           begin

           sqlsquery := 'SELECT DISTINCT CON.num_contrato,
            CON.descripcion,
            CON.creado_por,
            TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy HH:MI A.M.''),
            DECODE(CON.status,''0'','''||p_strNuevo||''',''1'','''||p_strActivo||''',''2'','''||p_strCancelado||''',''3'','''||p_strInactivo||''',''4'','''||p_strRechazado||''','''||p_strDesconocido||''') status,
            CON.status CODIGO_ESTATUS,
            TO_CHAR(CON.FECHA_STATUS,''dd/mm/yyyy'') FECHA_STATUS,
            CON.fecha_creacion
            FROM vbt_contrato CON
            ,vbt_users USU
            ,vbt_users_contrato USU_CON';

           IF p_hdnAccion IS NOT NULL AND p_txtNumeroCartera <> '' THEN
               sqlsquery := sqlsquery || ', vbt_contrato_carteras CON_CAR';
               sqlele :=  ' AND CON.num_contrato = CON_CAR.num_contrato';
           else
               sqlele :=  ' ';
           END IF;
               sqlsquery := sqlsquery || ' WHERE USU_CON.login = USU.login  AND USU_CON.num_contrato = CON.num_contrato  AND USU.tipo = ''2''';
               sqlsquery := sqlsquery || sqlele;
           IF     p_strTipoUsuario='1' THEN
               IF p_hdnAccion IS NULL THEN
                   sqlsquery := sqlsquery || ' AND CON.status = ''0''';
                   p_strCmbEstatus := '0';
               END IF;
           ELSIF p_strTipoUsuario ='0'    THEN
               IF p_hdnAccion IS NULL THEN
                   sqlsquery := sqlsquery || ' AND CON.status = ''4'' AND CON.creado_por = '''|| p_login ||'''';
                   p_strCmbEstatus:='4'; -- Rechazado
               END IF;
           END IF;

               IF p_strTxtNumeroContrato IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND CON.num_contrato = '''||p_strTxtNumeroContrato||'''';
               ELSIF p_strTxtUsuario IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND AND USU.login = '''|| p_strTxtUsuario||'''';
               ELSIF p_strCmbCreadoPor IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND CON.creado_por = LOWER('''||p_strCmbCreadoPor||''')';
               ELSIF p_strTxtNombreCliente IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND UPPER(USU.nombres) LIKE '''||p_strTxtNombreCliente||'''';
               ELSIF p_strTxtCIRIFCliente IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND USU.cirif LIKE  '''||p_strTxtCIRIFCliente||'''';
               ELSIF p_strCmbEstatus IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND CON.status = '''||p_strCmbEstatus||'''';
               ELSIF p_strTxtDesde IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||p_strTxtDesde||''',''dd/mm/yyyy''))';
               ELSIF p_strTxtHasta IS NOT NULL THEN
                   sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||p_strTxtHasta||''',''dd/mm/yyyy''))';
               ELSIF p_txtNumeroCartera IS NOT NULL THEN
                   sqlsquery := sqlsquery || '    AND CON_CAR.codcar LIKE    ''' ||'%' ||p_txtNumeroCartera||'%' ||'''';
               END IF;

           IF p_strOrden='Contrato' THEN
                   sqlsquery := sqlsquery || ' ORDER BY TO_NUMBER(CON.num_contrato) ASC';
           ELSIF  p_strOrden='CreadoPor' THEN
                   sqlsquery := sqlsquery || ' ORDER BY CON.creado_por ASC';
           ELSIF  p_strOrden='Estatus' THEN
                   sqlsquery := sqlsquery || ' ORDER BY status ASC, TO_NUMBER(CON.num_contrato) DESC';
           ELSE
                   sqlsquery := sqlsquery || ' ORDER BY CON.fecha_creacion DESC';
           END IF;
               p_salida:=sqlsquery;
               execute immediate sqlsquery into cs_bac_contratos;
               p_bac_contratos:= cs_bac_contratos;
               p_resultado:= 'OK';
            -- commit;
       EXCEPTION
       WHEN OTHERS THEN
           p_resultado:= SUBSTR(SQLERRM,1,300);

      end contratosnew_pr;
      */
           /******************************************************************************
NAME:    bac_contraagregarctaper_pr
PURPOSE: Devuelve in formacion de la tabla ctaper
******************************************************************************/

   PROCEDURE bac_contraagregarctaper_pr (
      p_codper                    IN     VARCHAR2,
      p_bac_contraagregarctaper      OUT bac_contraagregarctaper,
      p_resultado                    OUT VARCHAR2)
   AS
      cs_bac_contraagregarctaper   bac_contraagregarctaper;
   BEGIN
      OPEN cs_bac_contraagregarctaper FOR
         SELECT INITCAP (
                      per.apellido
                   || ' '
                   || per.apellseg
                   || ' '
                   || per.apellcas
                   || ' '
                   || per.nombre
                   || ' '
                   || per.nombseg)
                   NOMBRE
           FROM produccion.ctaper per
          WHERE per.codper = p_codper;

      p_bac_contraagregarctaper := cs_bac_contraagregarctaper;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraagregarctaper_pr;


           /******************************************************************************
NAME:    bac_contraagregardir
PURPOSE: Devuelve in formacion de la tabla ctaper
******************************************************************************/

   PROCEDURE bac_contraagregardir_pr (
      p_codper                 IN     VARCHAR2,
      p_bac_contraagregardir      OUT bac_contraagregardir,
      p_resultado                 OUT VARCHAR2)
   AS
      cs_bac_contraagregardir   bac_contraagregardir;
   BEGIN
      OPEN cs_bac_contraagregardir FOR
         SELECT LOWER (DIRECC1) EMAIL, FLAG_ENVIO DE_ENVIO
           FROM PRODUCCION.DIRECCIONES
          WHERE     CODPERCLI = p_codper
                AND TIPODIR = 'Email Online'
                AND UPPER (DIRECC1) <> 'HOLD MAIL';


      p_bac_contraagregardir := cs_bac_contraagregardir;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraagregardir_pr;


   /******************************************************************************
  NAME:    bac_contraagregarclien_pr
  PURPOSE: Query para obtener todos los Tipos de Usuario Clientes
  ******************************************************************************/

   PROCEDURE bac_contraagregarclien_pr (
      p_codper                   IN     VARCHAR2,
      p_bac_contraagregarclien      OUT bac_contraagregarclien,
      p_resultado                   OUT VARCHAR2)
   AS
      cs_bac_contraagregarclien   bac_contraagregarclien;
   BEGIN
      OPEN cs_bac_contraagregarclien FOR
           SELECT codgrp, nombgrp
             FROM ctagrp
            WHERE categoria = 'C'
         ORDER BY nombgrp ASC;


      p_bac_contraagregarclien := cs_bac_contraagregarclien;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraagregarclien_pr;

   /******************************************************************************
  NAME:    bac_contraagregarcarteras_pr
  PURPOSE: Query inforacion de la cartera tabla produccion.clientes
  ******************************************************************************/

   PROCEDURE bac_contraagregarcarteras_pr (
      p_TAGCompartida               IN     VARCHAR2,
      p_TAGIndividual               IN     VARCHAR2,
      p_TAGActiva                   IN     VARCHAR2,
      p_TAGInactiva                 IN     VARCHAR2,
      p_codcar                      IN     VARCHAR2,
      p_bac_contraagregarcarteras      OUT bac_contraagregarcarteras,
      p_resultado                      OUT VARCHAR2)
   AS
      cs_bac_contraagregarcarteras   bac_contraagregarcarteras;
   BEGIN
      OPEN cs_bac_contraagregarcarteras FOR
         SELECT DISTINCT
                codcar,
                DECODE (modcar,  0, p_TAGCompartida,  1, p_TAGIndividual),
                DECODE (UPPER (statcar),
                        'A', p_TAGActiva,
                        'I', p_TAGInactiva),
                INITCAP (na),
                INITCAP (referido) asesor,
                INITCAP (responsable) ejecutivo
           FROM produccion.clientes
          WHERE codcar = p_codcar AND UPPER (statcar) = 'A';

      p_bac_contraagregarcarteras := cs_bac_contraagregarcarteras;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraagregarcarteras_pr;


   /******************************************************************************
 NAME:    bac_contraeditarcon_pr
 PURPOSE: Query para obtener informacion de los contratos de la tabla vbt_contrato
 ******************************************************************************/

   PROCEDURE bac_contraeditarcon_pr (
      p_strTxtNumeroContratoEditar   IN     VARCHAR2,
      p_bac_contraeditarcon             OUT bac_contraeditarcon,
      p_resultado                       OUT VARCHAR2)
   AS
      cs_bac_contraeditarcon   bac_contraeditarcon;
   BEGIN
      OPEN cs_bac_contraeditarcon FOR
         SELECT CON.descripcion,
                CON.status,
                CON.creado_por,
                DECODE (
                     TO_DATE (CON.FECHA_CREACION, 'dd/mm/rrrr')
                   - TO_DATE (SYSDATE, 'dd/mm/rrrr'),
                   0, 'no',
                   'si')
                   activable
           FROM vbt_contrato CON
          WHERE CON.num_contrato = p_strTxtNumeroContratoEditar;

      p_bac_contraeditarcon := cs_bac_contraeditarcon;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraeditarcon_pr;


   /******************************************************************************
      NAME:    bac_contraeditarrechazo_pr
      PURPOSE: Query Busca y guarda los motivos del rechazo
      ******************************************************************************/

   PROCEDURE bac_contraeditarrechazo_pr (
      p_strTxtNumeroContratoEditar   IN     VARCHAR2,
      p_bac_contraeditarrechazo         OUT bac_contraeditarrechazo,
      p_resultado                       OUT VARCHAR2)
   AS
      cs_bac_contraeditarrechazo   bac_contraeditarrechazo;
   BEGIN
      OPEN cs_bac_contraeditarrechazo FOR
         SELECT COD_MOTIVO_RECHAZO
           FROM VBT_CONTRATO_MOTIVO_RECHAZO
          WHERE NUM_CONTRATO = p_strTxtNumeroContratoEditar;

      p_bac_contraeditarrechazo := cs_bac_contraeditarrechazo;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraeditarrechazo_pr;

   /******************************************************************************
NAME:    bac_contraeditarcart_pr
PURPOSE: Query Busca registros de tabla vbt_contrato_carteras
******************************************************************************/

   PROCEDURE bac_contraeditarcart_pr (
      p_strTxtNumeroContratoEditar   IN     VARCHAR2,
      p_bac_contraeditarcart            OUT bac_contraeditarcart,
      p_resultado                       OUT VARCHAR2)
   AS
      cs_bac_contraeditarcart   bac_contraeditarcart;
   BEGIN
      OPEN cs_bac_contraeditarcart FOR
         SELECT codcar
           FROM vbt_contrato_carteras
          WHERE num_contrato = p_strTxtNumeroContratoEditar;

      p_bac_contraeditarcart := cs_bac_contraeditarcart;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraeditarcart_pr;


   /******************************************************************************
   NAME:    bac_contraeditarrechat_pr
   PURPOSE: Query Busca todos registros de tabla VBT_MOTIVO_RECHAZO
   ******************************************************************************/

   PROCEDURE bac_contraeditarrechat_pr (
      p_bac_contraeditarrechat   OUT bac_contraeditarrechat,
      p_resultado                OUT VARCHAR2)
   AS
      cs_bac_contraeditarrechat   bac_contraeditarrechat;
   BEGIN
      OPEN cs_bac_contraeditarrechat FOR
           SELECT CODIGO, DESCRIPCION
             FROM VBT_MOTIVO_RECHAZO
         ORDER BY DESCRIPCION;


      p_bac_contraeditarrechat := cs_bac_contraeditarrechat;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraeditarrechat_pr;


   /******************************************************************************
 NAME:    bac_contraeditarrlogin_pr
 PURPOSE: Query chequea el login con las tablas vbt_users y vbt_users_contrato
 ******************************************************************************/


   PROCEDURE contralog_pr (
      p_strTxtNumeroContratoEditar   IN     VARCHAR2,
      p_contraeditarrlog                OUT contraeditarrlog,
      p_resultado                       OUT VARCHAR2)
   AS
      cs_contraeditarrlog   contraeditarrlog;
   BEGIN
      OPEN cs_contraeditarrlog FOR
         SELECT USU.login
           FROM vbt_users USU, vbt_users_contrato USU_CON
          WHERE     USU_CON.num_contrato = p_strTxtNumeroContratoEditar
                AND USU_CON.login = USU.login
                AND USU.fecha_login IS NOT NULL;


      p_contraeditarrlog := cs_contraeditarrlog;
      p_resultado := 'OK';
   --commit;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END contralog_pr;

   PROCEDURE bac_usuario_clientes_pr (
      p_strRoot               IN     VARCHAR2,
      p_strLoader             IN     VARCHAR2,
      p_strSupervisor         IN     VARCHAR2,
      p_strCliente            IN     VARCHAR2,
      p_strActiva             IN     VARCHAR2,
      p_strInactiva           IN     VARCHAR2,
      p_strCancelada          IN     VARCHAR2,
      p_strBloqueado          IN     VARCHAR2,
      p_strDesconocido        IN     VARCHAR2,
      p_strTxtUsuario         IN     VARCHAR2,
      p_strTxtNombre          IN     VARCHAR2,
      p_strTxtCIRIF           IN     VARCHAR2,
      p_strCmbTipoUsuario     IN     VARCHAR2,
      p_strCmbEstatus         IN     VARCHAR2,
      p_strAmbito             IN     VARCHAR2,
      p_strOrden              IN     VARCHAR2,
      hdnAccion               IN     VARCHAR2,
      p_strCartera            IN     VARCHAR2,
      bac_usuarios_clientes      OUT SYS_REFCURSOR,
      p_resultado                OUT VARCHAR2,
      p_salida                   OUT VARCHAR2)
   AS
      SQLSTRING   VARCHAR2 (2000);
      SQLELEM     VARCHAR2 (200);
      whereSQL    VARCHAR2 (500);
      ordenSQL    VARCHAR2 (100);
   BEGIN
      SQLSTRING :=
            'SELECT DISTINCT USU.LOGIN,                                     
         TO_CHAR(USU.FECHA_INGRESO,''dd/mm/yyyy hh:mi A.M.''), 
         USU.NOMBRES, 
         USU.EMAIL, 
         USU.TELEFONO_CELULAR || DECODE(USU.TELEFONO_CELULAR,NULL,USU.TELEFONO, DECODE(USU.TELEFONO,NULL,'''','' / '' || USU.TELEFONO)) TELEFONOS,  
         GRP.NOMBGRP GRUPO_USUARIO,
         USU.DIRECCION, 
         TO_CHAR(USU.FECHA_LOGIN,''dd/mm/yyyy hh:mi A.M.'') ULTIMO_LOGIN, 
         DECODE(USU.STATUS_CUENTA,''A'','''
         || p_strActiva
         || ''',''I'','''
         || p_strInactiva
         || ''',''C'','''
         || p_strCancelada
         || ''',''B'','''
         || p_strBloqueado
         || ''','''
         || p_strDesconocido
         || ''') STATUS, 
         USU.TIPO,
         USU.STATUS_CUENTA,
         USU.CIRIF,
         TO_CHAR(USU.FECHA_STATUS,''dd/mm/yyyy''),
          CON.STATUS ESTATUS_CONTRATO,
          AMBITO,
          elementos_tipos.DESCRIPCION
          
         FROM vbt_users USU
         ,usugrp_v1 UG
        ,ctagrp GRP
        ,vbt_contrato CON
        ,vbt_users_contrato USUCON  
        , elementos_tipos
        ,vbt_contrato_carteras CONCAR
         WHERE USU.TIPO IN (''2'',''6'',''7'',''8'')
        AND   USU.LOGIN  = UG.LOGIN
        AND   GRP.CODGRP = UG.CODGRP
        AND   CON.NUM_CONTRATO = USUCON.NUM_CONTRATO
        AND   USUCON.LOGIN =USU.LOGIN
        AND  AMBITO = ELEMENTOS_TIPOS.CODELEMENTO 
        AND CONCAR.NUM_CONTRATO=CON.NUM_CONTRATO
        AND ELEMENTOS_TIPOS.CODTIPO =''0000000008''
        AND GRP.CATEGORIA<>''FC'' ';


      IF p_strCartera IS NOT NULL OR p_strCartera <> ''
      THEN
         SQLSTRING :=
            --SQLSTRING || ' AND CONCAR.CODCAR =  ''' || p_strCartera || '''';
            SQLSTRING || ' AND CONCAR.CODCAR LIKE    ''' ||'%' ||p_strCartera||'%' ||''' ';
      END IF;

      IF hdnAccion IS NOT NULL
      THEN
         IF p_strTxtUsuario IS NOT NULL OR p_strTxtUsuario <> ''
         THEN
            whereSQL := ' AND USU.LOGIN =  ''' || p_strTxtUsuario || '''';
         ELSE
            IF p_strTxtNombre IS NOT NULL OR p_strTxtNombre <> ''
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND UPPER(USU.NOMBRES) like '''
                  || '%'
                  || p_strTxtNombre
                  || '%'
                  || '''';
            END IF;

            IF p_strTxtCIRIF IS NOT NULL OR p_strTxtCIRIF <> ''
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND USU.CIRIF like '''
                  || '%'
                  || p_strTxtCIRIF
                  || '%'
                  || '''';
            END IF;

            IF     p_strCmbTipoUsuario IS NOT NULL
               AND p_strCmbTipoUsuario <> 'Default'
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND UG.CODGRP =  '''
                  || p_strCmbTipoUsuario
                  || '''';
            END IF;

            IF p_strCmbEstatus IS NOT NULL AND p_strCmbEstatus <> 'Default'
            THEN
               whereSQL :=
                     whereSQL
                  || ' AND USU.STATUS_CUENTA = '''
                  || p_strCmbEstatus
                  || '''';
            END IF;

            IF p_strAmbito IS NOT NULL AND p_strAmbito <> 'Default'
            THEN
               whereSQL :=
                  whereSQL || ' AND USU.AMBITO = ''' || p_strAmbito || '''';
            END IF;
         END IF;

         SQLSTRING := SQLSTRING || whereSQL;
      END IF;

      IF p_strOrden = 'Nombre'
      THEN
         ordenSQL := ' ORDER BY USU.NOMBRES ASC';
      ELSIF p_strOrden = 'Tipo'
      THEN
         ordenSQL := ' ORDER BY BY GRP.NOMBGRP ASC';
      ELSIF p_strOrden = 'Estatus'
      THEN
         ordenSQL := ' ORDER BY USU.STATUS_CUENTA ASC';
      ELSIF p_strOrden = 'Ultimo_Acceso'
      THEN
         ordenSQL := ' ORDER BY USU.FECHA_LOGIN DESC';
      ELSIF p_strOrden = 'Fecha_Creacion'
      THEN
         ordenSQL := ' ORDER BY USU.FECHA_INGRESO DESC';
      ELSE
         ordenSQL := ' ORDER BY USU.LOGIN ASC';
      END IF;

      SQLSTRING := SQLSTRING || ordenSQL;
      p_salida := SQLSTRING;

      OPEN bac_usuarios_clientes FOR SQLSTRING;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuario_clientes_pr;


   /******************************************************************************
   NAME:    bac_gruposopctot_pr
   PURPOSE: Para verificar si el grupo tiene opciones asociadas cantidades
   ******************************************************************************/

   PROCEDURE bac_gruposopctot_pr (
      p_strCmbcodopc       IN     VARCHAR2,
      p_bac_gruposopctot      OUT bac_gruposopctot,
      p_resultado             OUT VARCHAR2)
   AS
      cs_bac_gruposopc   bac_gruposopctot;
   BEGIN
      OPEN cs_bac_gruposopc FOR
           SELECT codgrp codigo,
                  nombgrp nombre,
                  categoria categoria,
                  (SELECT COUNT (*)
                     FROM usugrp_v1
                    WHERE codgrp = gp.codgrp)
                     cantidadusugrp,
                  (SELECT COUNT (*)
                     FROM grpciaopc_v1
                    WHERE codgrp = gp.codgrp)
                     cantidadgrpciaopc
             FROM ctagrp gp
             WHERE gp.visible='S'
         ORDER BY nombgrp ASC;

      p_bac_gruposopctot := cs_bac_gruposopc;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_gruposopctot_pr;


   /******************************************************************************
    NAME:    bac_coneditargrupos_pr
    PURPOSE: devuaelve informacion de las tablas vbt_users_contrato , usugrp_v1,ctagrp
    ******************************************************************************/


   PROCEDURE bac_coneditargrupos_pr (
      p_strTxtNumeroContratoEditar   IN     VARCHAR2,
      p_bac_coneditargrupos             OUT bac_coneditargrupos,
      p_resultado                       OUT VARCHAR2)
   AS
      cs_bac_coneditargrupos   bac_coneditargrupos;
   BEGIN
      OPEN cs_bac_coneditargrupos FOR
           SELECT USU.login,
                  USU.nombres,
                  USU.last_login,
                  USU.cirif,
                  USU.email,
                  GRP.nombgrp,
                  USU.codpercli,
                  GRP.codgrp,
                  FNC_GET_NUMERICO(USU.telefono_celular),
                  DECODE (USU.STATUS_CUENTA,  'A', 'Activo',  'I', 'Inactivo',  'B', 'Bloqueado',  'C', 'Cancelado'),
                  FNC_GET_NUMERICO(USU.CODIGO_PAIS)
             FROM vbt_users_contrato USU_CON,
                  vbt_users USU,
                  usugrp_v1 USU_GRP,
                  ctagrp GRP
            WHERE     USU_CON.num_contrato = p_strTxtNumeroContratoEditar
                  AND USU_CON.login = USU.login
                  AND USU_GRP.login = USU.login
                  AND USU_GRP.codgrp = GRP.codgrp
                  --AND USU.CODPERCLI IS NOT NULL
         ORDER BY USU.fecha_ingreso ASC;

      p_bac_coneditargrupos := cs_bac_coneditargrupos;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_coneditargrupos_pr;


   /******************************************************************************
  NAME:    bac_coneditarctas_pr
  PURPOSE: devuelve cantidad de registros de tabla CTAS_CTTES
  ******************************************************************************/


   PROCEDURE bac_coneditarctas_pr (
      p_codcar              IN     VARCHAR2,
      p_bac_coneditarctas      OUT bac_coneditarctas,
      p_resultado              OUT VARCHAR2)
   AS
      cs_bac_coneditarctas   bac_coneditarctas;
   BEGIN
      OPEN cs_bac_coneditarctas FOR
         SELECT COUNT (*)
           FROM BANCO_VBT.CTAS_CTTES C
          WHERE C.CODCAR = p_codcar;

      p_bac_coneditarctas := cs_bac_coneditarctas;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_coneditarctas_pr;


   /******************************************************************************
  NAME:    bac_coneditardep_pr
  PURPOSE: devuelve cantidad de registros de tabla TIME_DEPOSIT_1
  ******************************************************************************/


   PROCEDURE bac_coneditardep_pr (
      p_codcar             IN     VARCHAR2,
      p_bac_coneditardep      OUT bac_coneditardep,
      p_resultado             OUT VARCHAR2)
   AS
      cs_bac_coneditardep   bac_coneditardep;
   BEGIN
      OPEN cs_bac_coneditardep FOR
         SELECT COUNT (*)
           FROM BANCO_VBT.TIME_DEPOSIT_1 TD
          WHERE TD.CODCAR = p_codcar AND STATCOL <> 'A';

      p_bac_coneditardep := cs_bac_coneditardep;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_coneditardep_pr;

   /******************************************************************************
    NAME:    bac_contraeditarcarteras_pr
    PURPOSE: Query Busca registros de tabla produccion.clientes
    ******************************************************************************/


   PROCEDURE bac_contraeditarcarteras_pr (
      p_TAGCompartida                IN     VARCHAR2,
      p_TAGIndividual                IN     VARCHAR2,
      p_TAGActiva                    IN     VARCHAR2,
      p_TAGInactiva                  IN     VARCHAR2,
      p_strTxtNumeroContratoEditar   IN     VARCHAR2,
      p_bac_contraeditarcarteras        OUT bac_contraeditarcarteras,
      p_resultado                       OUT VARCHAR2)
   AS
      cs_bac_contraeditarcarteras   bac_contraeditarcarteras;
   BEGIN
      OPEN cs_bac_contraeditarcarteras FOR
           SELECT CA.CODCAR,
                  DECODE (DECODE (CA.MODCAR1,  'C', 0,  'I', 1),
                          0, p_TAGCompartida,
                          1, p_TAGIndividual),
                  DECODE (UPPER (CA.STATCAR),
                          'A', p_TAGActiva,
                          'I', p_TAGInactiva),
                  INITCAP (
                     SUBSTR (
                           CL.apellido
                        || ' '
                        || CL.apellseg
                        || ' '
                        || CL.apellcas
                        || ' '
                        || CL.nombre
                        || ' '
                        || CL.nombseg,
                        1,
                        255)),
                     REF.apellido
                  || ' '
                  || REF.apellseg
                  || ' '
                  || REF.apellcas
                  || ' '
                  || REF.nombre
                  || ' '
                  || REF.nombseg,
                     EJECUTIVO.apellido
                  || ' '
                  || EJECUTIVO.apellseg
                  || ' '
                  || EJECUTIVO.apellcas
                  || ' '
                  || EJECUTIVO.nombre
                  || ' '
                  || EJECUTIVO.nombseg,
                  CC.FLGPRI,
                  (SELECT COUNT (*)
                     FROM BANCO_VBT.CTAS_CTTES C
                    WHERE C.CODCAR IN
                             (SELECT codcar
                                FROM vbt_contrato_carteras
                               WHERE num_contrato =
                                        p_strTxtNumeroContratoEditar))
                     AS CUENTAS,
                  (SELECT COUNT (*)
                     FROM BANCO_VBT.TIME_DEPOSIT_1 TD
                    WHERE     TD.CODCAR IN
                                 (SELECT codcar
                                    FROM vbt_contrato_carteras
                                   WHERE num_contrato =
                                            p_strTxtNumeroContratoEditar)
                          AND STATCOL <> 'A')
                     AS DEPOSITOS,
                  (SELECT COUNT (*)
                     FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 FM
                    WHERE FM.CODCAR IN
                             (SELECT codcar
                                FROM vbt_contrato_carteras
                               WHERE num_contrato =
                                        p_strTxtNumeroContratoEditar))
                     AS FONDOS
             FROM CTAPER CL,
                  CTACAR CA,
                  CARCLI CC,
                  CTAPER REF,
                  CTAPER EJECUTIVO
            WHERE     CC.CODPERCLI = CL.CODPER
                  AND CA.CODCAR = CC.CODCAR
                  AND CA.CODNEG = REF.CODPER(+)
                  AND CA.CODEJE = EJECUTIVO.CODPER(+)
                  AND CA.CODCAR IN
                         (SELECT codcar
                            FROM vbt_contrato_carteras
                           WHERE num_contrato = p_strTxtNumeroContratoEditar)
         ORDER BY CA.CODPERCLI;


      p_bac_contraeditarcarteras := cs_bac_contraeditarcarteras;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_contraeditarcarteras_pr;

   /******************************************************************************
   NAME:    bac_coneditarmut_pr
   PURPOSE: devuelve cantidad de registros de tabla SALDOS_FONDO_MUTUAL_1
   ******************************************************************************/

   /******************************************************************************
   NAME:    bac_cargarcarteraindividual_pr
   PURPOSE:
   ******************************************************************************/


   PROCEDURE bac_cargarcarteraindividual_pr (
      p_TAGCompartida                 IN     VARCHAR2,
      p_TAGIndividual                 IN     VARCHAR2,
      p_TAGActiva                     IN     VARCHAR2,
      p_TAGInactiva                   IN     VARCHAR2,
      p_strTxtNumeroCartera           IN     VARCHAR2,
      p_bac_cargarcarteraindividual      OUT bac_cargarcarteraindividual,
      p_resultado                        OUT VARCHAR2)
   AS
      cs_bac_cargarcarteraindividual   bac_cargarcarteraindividual;
   BEGIN
      OPEN cs_bac_cargarcarteraindividual FOR
           SELECT CA.CODCAR,
                  DECODE (DECODE (CA.MODCAR1,  'C', 0,  'I', 1),
                          0, p_TAGCompartida,
                          1, p_TAGIndividual),
                  DECODE (UPPER (CA.STATCAR),
                          'A', p_TAGActiva,
                          'I', p_TAGInactiva),
                  INITCAP (
                     SUBSTR (
                           CL.apellido
                        || ' '
                        || CL.apellseg
                        || ' '
                        || CL.apellcas
                        || ' '
                        || CL.nombre
                        || ' '
                        || CL.nombseg,
                        1,
                        255)),
                     REF.apellido
                  || ' '
                  || REF.apellseg
                  || ' '
                  || REF.apellcas
                  || ' '
                  || REF.nombre
                  || ' '
                  || REF.nombseg,
                     EJECUTIVO.apellido
                  || ' '
                  || EJECUTIVO.apellseg
                  || ' '
                  || EJECUTIVO.apellcas
                  || ' '
                  || EJECUTIVO.nombre
                  || ' '
                  || EJECUTIVO.nombseg,
                  CC.FLGPRI,
                  (SELECT COUNT (*)
                     FROM BANCO_VBT.CTAS_CTTES C
                    WHERE C.CODCAR = p_strTxtNumeroCartera)
                     AS CUENTAS,
                  (SELECT COUNT (*)
                     FROM BANCO_VBT.TIME_DEPOSIT_1 TD
                    WHERE TD.CODCAR = p_strTxtNumeroCartera AND STATCOL <> 'A')
                     AS DEPOSITOS,
                  (SELECT COUNT (*)
                     FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 FM
                    WHERE FM.CODCAR = p_strTxtNumeroCartera)
                     AS FONDOS
             FROM CTAPER CL,
                  CTACAR CA,
                  CARCLI CC,
                  CTAPER REF,
                  CTAPER EJECUTIVO
            WHERE     CC.CODPERCLI = CL.CODPER
                  AND CA.CODCAR = CC.CODCAR
                  AND CA.CODNEG = REF.CODPER(+)
                  AND CA.CODEJE = EJECUTIVO.CODPER(+)
                  AND CA.CODCAR = p_strTxtNumeroCartera
         ORDER BY CA.CODPERCLI;


      p_bac_cargarcarteraindividual := cs_bac_cargarcarteraindividual;
      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_cargarcarteraindividual_pr;

   /******************************************************************************
   NAME:    bac_cargarcarteraindividual_pr
   PURPOSE:
   ******************************************************************************/


   PROCEDURE bac_coneditarmut_pr (
      p_codcar             IN     VARCHAR2,
      p_bac_coneditarmut      OUT bac_coneditarmut,
      p_resultado             OUT VARCHAR2)
   AS
      cs_bac_coneditarmut   bac_coneditarmut;
   BEGIN
      OPEN cs_bac_coneditarmut FOR
         SELECT COUNT (*)
           FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 FM
          WHERE FM.CODCAR = p_codcar;

      p_bac_coneditarmut := cs_bac_coneditarmut;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_coneditarmut_pr;


   /******************************************************************************
   NAME:    BAC_INGRE_P
   PURPOSE: Query VALIDA USUARIO Y PASSWORD EN BD
   ******************************************************************************/


   PROCEDURE BAC_INGRE_P (P_LOGIN      IN     VARCHAR2,
                          P_PASSJ      IN     VARCHAR2,
                          prespuesta      OUT VARCHAR2)
   AS
      PASSBD    VARCHAR2 (50);
      LOGINBD   VARCHAR2 (20);

      CURSOR ING
      IS
         SELECT A.LOGIN,
                A.FECHA_INGRESO,
                A.PASSWORD,
                A.INTENTOS_LOGIN,
                B.CODCIA,
                B.CODSIS
           FROM VBT_USERS A, usugrp_v1 b
          WHERE A.LOGIN = B.LOGIN AND A.LOGIN = P_LOGIN;
   BEGIN

      IF P_LOGIN IS NOT NULL
      THEN
         --    BEGIN
         SELECT A.LOGIN LOGIN, SEGURIDAD.DESENCRIPTA (A.PASSWORD)
           INTO LOGINBD, PASSBD
           FROM VBT_USERS A, usugrp_v1 b
          WHERE A.LOGIN = B.LOGIN AND A.LOGIN = p_login;

         IF P_PASSJ = PASSBD
         THEN
            prespuesta := 'OK';
         ELSE
            prespuesta := 'NO OK';

            BEGIN
               FOR N_ING IN ING
               LOOP
                  IF N_ING.INTENTOS_LOGIN < 4 AND prespuesta = 'OK'
                  THEN
                     UPDATE VBT_USERS
                        SET FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = 0
                      WHERE UPPER (N_ING.LOGIN) = UPPER (P_LOGIN);
                  ELSE
                     UPDATE VBT_USERS
                        SET FECHA_LOGIN = SYSDATE,
                            INTENTOS_LOGIN = INTENTOS_LOGIN + 1,
                            STATUS_CUENTA = 'B'
                      WHERE UPPER (N_ING.LOGIN) <> UPPER (P_LOGIN);
                  END IF;
               END LOOP;
            EXCEPTION
               WHEN OTHERS
               THEN
                  prespuesta := 'NO OK';

                  BEGIN
                     FOR N_ING IN ING
                     LOOP
                        UPDATE VBT_USERS
                           SET FECHA_LOGIN = SYSDATE,
                               INTENTOS_LOGIN = INTENTOS_LOGIN + 1,
                               STATUS_CUENTA = 'B'
                         WHERE UPPER (N_ING.LOGIN) <> UPPER (P_LOGIN);
                     END LOOP;
                  END;
            END;
         --EXCEPTION
         -- WHEN NO_DATA_FOUND THEN
         --     NULL;

         --   END;
         END IF;
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         prespuesta := 'NO OK';
   END BAC_INGRE_P;

   /******************************************************************************
   NAME:    bac_elemencodtipo_pr
   PURPOSE: Query para obtener todos los estatus
   ******************************************************************************/

   PROCEDURE bac_elemencodtipo_pr (
      p_bac_elemencodtipo   OUT bac_elemencodtipo,
      p_resultado           OUT VARCHAR2)
   AS
      cs_bac_elemencodtipo   bac_elemencodtipo;
   BEGIN
      OPEN cs_bac_elemencodtipo FOR
           SELECT CODELEMENTO
             FROM VARIOS.ELEMENTOS_TIPOS
            WHERE CODTIPO = '0000000015' OR CODTIPO = '0000000016'
         ORDER BY CODELEMENTO;

      p_bac_elemencodtipo := cs_bac_elemencodtipo;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_elemencodtipo_pr;


   /******************************************************************************
   NAME:    bac_carterasbuscar_pr
   PURPOSE: Retorna informacion de la tabla produccion.clientes
   ******************************************************************************/



   PROCEDURE bac_carterasbuscar_pr (
      p_TAGCompartida               IN     VARCHAR2,
      p_TAGIndividual               IN     VARCHAR2,
      p_TAGPrincipal                IN     VARCHAR2,
      p_TAGSecundario               IN     VARCHAR2,
      p_hdnAccion                   IN     VARCHAR2,
      p_strTxtCarteraNumero         IN     VARCHAR2,
      p_strChkNumeroExactoCartera   IN     VARCHAR2,
      p_strTxtCedula_Rif            IN     VARCHAR2,
      p_strTxtNombreCliente         IN     VARCHAR2,
      p_strTxtAsesor                IN     VARCHAR2,
      p_strTxtEjecutivo             IN     VARCHAR2,
      p_bac_carterasbuscar             OUT SYS_REFCURSOR,
      p_resultado                      OUT VARCHAR2,
      p_sqlstring                      OUT VARCHAR2)
   AS
      SQLSTRING    VARCHAR2 (2500);
      wheresql     VARCHAR2 (2500);
      agregarAnd   BOOLEAN := FALSE;
   BEGIN
      SQLSTRING :=
            'SELECT   
     CA.CODCAR cartera, 
     DECODE(CA.modcar,0,'''
         || p_TAGCompartida
         || ''',1,'''
         || p_TAGIndividual
         || ''') modalidad,
     DECODE(UPPER(CA.STATCAR),''A'',''TAGActiva'',''I'',''TAGInactiva'') status,
     DECODE(CC.FLGPRI,-1,'''
         || p_TAGPrincipal
         || ''','''
         || p_TAGSecundario
         || ''') flag_primario,
     REF.apellido||'' ''||REF.apellseg|| '' '' || REF.apellcas || '' '' ||REF.nombre || '' ''|| REF.nombseg asesor,
     EJECUTIVO.apellido||'' ''||EJECUTIVO.apellseg|| '' '' || EJECUTIVO.apellcas || '' '' ||EJECUTIVO.nombre || '' ''|| EJECUTIVO.nombseg ejecutivo,
     DECODE(CL.concirif,''0'',CL.precirif || ''-'' || CL.cirif, CL.precirif || ''-'' || CL.cirif || ''-'' || CL.concirif),
     INITCAP(SUBSTR ( CL.apellido || '' '' || CL.apellseg || '' '' || CL.apellcas || '' '' || CL.nombre || '' '' || CL.nombseg,1,255)) nombre, 
     upper(CA.statcar) status_cartera,
     CC.codpercli,
     COUNT(CTAS.CODCAR) CTASCTT,
     COUNT(TDEPT.CODCAR) T_DEP,
     COUNT(SFM.CODCAR) S_FM
    FROM CTAPER CL, 
         CTACAR CA, 
         CARCLI CC, 
         CTAPER REF,  CTAPER EJECUTIVO,
         BANCO_VBT.CTAS_CTTES CTAS, 
         BANCO_VBT.TIME_DEPOSIT_1 TDEPT,
         FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 SFM
    WHERE
            CC.CODPERCLI = CL.CODPER 
            AND CA.CODCAR = CC.CODCAR 
            AND CA.CODNEG   = REF.CODPER(+)
            AND CA.CODEJE = EJECUTIVO.CODPER(+) 
            AND CA.CODCAR = CTAS.CODCAR(+)
            AND CA.CODCAR = TDEPT.CODCAR(+)
            AND CA.CODCAR = SFM.CODCAR(+)  ';

      IF p_hdnAccion IS NOT NULL
      THEN
         IF p_strTxtCarteraNumero IS NOT NULL
         THEN
            IF p_strChkNumeroExactoCartera = '1'
            THEN
               wheresql :=
                     wheresql
                  || ' AND CA.codcar = '''
                  || p_strTxtCarteraNumero
                  || '''  ';
            ELSE
               wheresql :=
                     wheresql
                  || ' AND CA.codcar LIKE   '''
                  || '%'
                  || p_strTxtCarteraNumero
                  || '%'
                  || '''  ';
            END IF;
         END IF;

         IF p_strTxtCedula_Rif IS NOT NULL
         THEN
            wheresql :=
                  wheresql
               || ' AND UPPER(DECODE(CL.concirif,''0'',CL.precirif || ''-'' || CL.cirif, CL.precirif || ''-'' || CL.cirif || ''-'' || CL.concirif)) LIKE '''
               || '%'
               || p_strTxtCedula_Rif
               || '%'
               || '''  ';
         END IF;

         IF p_strTxtNombreCliente IS NOT NULL
         THEN
            wheresql :=
                  wheresql
               || ' AND replace(CL.apellido || '' '' || CL.apellseg || '' '' || CL.apellcas || '' '' || CL.nombre || '' '' || CL.nombseg,'' '',null) LIKE replace(''%'
               || UPPER (p_strTxtNombreCliente)
               || '%'','' '',null) ';
         END IF;

         IF p_strTxtAsesor IS NOT NULL
         THEN
            wheresql :=
                  wheresql
               || ' AND replace(REF.apellido||'' ''||REF.apellseg|| '' '' || REF.apellcas || '' '' ||REF.nombre || '' ''|| REF.nombseg,'' '',null) LIKE replace(''%'
               || UPPER (p_strTxtAsesor)
               || '%'','' '',null) ';
         END IF;

         IF p_strTxtEjecutivo IS NOT NULL
         THEN
            wheresql :=
                  wheresql
               || ' AND replace(EJECUTIVO.apellido||'' ''||EJECUTIVO.apellseg|| '' '' || EJECUTIVO.apellcas || '' '' ||EJECUTIVO.nombre || '' ''|| EJECUTIVO.nombseg,'' '',null) LIKE replace(''%'
               || UPPER (p_strTxtEjecutivo)
               || '%'','' '',null) ';
         END IF;


         IF wheresql IS NOT NULL
         THEN
            SQLSTRING :=
                  SQLSTRING
               || wheresql
               || '   group by (CTAS.CODCAR, TDEPT.CODCAR, SFM.CODCAR, CA.CODCAR,CA.modcar, CA.STATCAR, CC.FLGPRI, REF.apellido, REF.apellseg, EJECUTIVO.apellido, EJECUTIVO.apellseg, EJECUTIVO.apellcas, EJECUTIVO.nombre, EJECUTIVO.nombseg, REF.apellcas, REF.nombre, REF.nombseg, CL.concirif, CL.precirif, CL.cirif, CL.apellido,CL.apellseg, CL.apellcas, CL.nombre, CL.nombseg, CA.statcar, CC.codpercli) ORDER BY nombre ASC ';
         ELSE
            SQLSTRING :=
                  SQLSTRING
               || '   group by (CTAS.CODCAR, TDEPT.CODCAR, SFM.CODCAR, CA.CODCAR,CA.modcar, CA.STATCAR, CC.FLGPRI, REF.apellido, REF.apellseg, EJECUTIVO.apellido, EJECUTIVO.apellseg, EJECUTIVO.apellcas, EJECUTIVO.nombre, EJECUTIVO.nombseg, REF.apellcas, REF.nombre, REF.nombseg, CL.concirif, CL.precirif, CL.cirif, CL.apellido,CL.apellseg, CL.apellcas, CL.nombre, CL.nombseg, CA.statcar, CC.codpercli)  ORDER BY nombre ASC';
         END IF;
      END IF;

      p_sqlstring := SQLSTRING;

      OPEN p_bac_carterasbuscar FOR SQLSTRING;

      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_carterasbuscar_pr;


   /******************************************************************************
  NAME:    bac_clienteprodu_pr
  PURPOSE: Retorna informacion de la tabla produccion.clientes
  ******************************************************************************/

   PROCEDURE bac_clienteprodu_pr (p_TAGCompartida      IN     VARCHAR2,
                                  p_TAGIndividual      IN     VARCHAR2,
                                  p_TAGPrincipal       IN     VARCHAR2,
                                  p_TAGSecundario      IN     VARCHAR2,
                                  p_codpercli          IN     VARCHAR2,
                                  p_bac_clienteprodu      OUT SYS_REFCURSOR,
                                  p_resultado             OUT VARCHAR2)
   AS
      sqlstring   VARCHAR2 (1500);
   BEGIN
      SQLSTRING :=
            'SELECT DISTINCT CLI.codcar cartera
    ,DECODE(CLI.modcar,0,'''
         || p_TAGCompartida
         || ''',1,'''
         || p_TAGIndividual
         || ''') modalidad
    ,DECODE(upper(CLI.statcar),''A'',''TAGActiva'',''I'',''TAGInactiva'') status
    ,DECODE(CLI.flgpri,-1,'''
         || p_TAGPrincipal
         || ''','''
         || p_TAGSecundario
         || ''') flag_primario
    ,INITCAP(CLI.referido) asesor
    ,INITCAP(CLI.responsable) ejecutivo
    FROM produccion.clientes CLI
    WHERE  codpercli ='''
         || p_codpercli
         || '''';

      OPEN p_bac_clienteprodu FOR SQLSTRING;

      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_clienteprodu_pr;


   /******************************************************************************
   NAME:    bac_clientebuscar_pr
   PURPOSE: Retorna informacion de la tabla produccion.clientes
   ******************************************************************************/


   PROCEDURE bac_clientebuscar_pr (
      p_TAGCompartida               IN     VARCHAR2,
      p_TAGIndividual               IN     VARCHAR2,
      p_TAGPrincipal                IN     VARCHAR2,
      p_TAGSecundario               IN     VARCHAR2,
      p_strTxtCarteraNumero         IN     VARCHAR2,
      p_strChkNumeroExactoCartera   IN     VARCHAR2,
      p_strTxtCedula_Rif            IN     VARCHAR2,
      p_strTxtNombreCliente         IN     VARCHAR2,
      p_strTxtAsesor                IN     VARCHAR2,
      p_strTxtEjecutivo             IN     VARCHAR2,
      p_bac_clientebuscar              OUT SYS_REFCURSOR,
      p_resultado                      OUT VARCHAR2,
      p_select                         OUT VARCHAR2)
   AS
      SQLSTRING    VARCHAR2 (1500);
      wheresql     VARCHAR2 (1500);
      agregarAnd   BOOLEAN := FALSE;
   BEGIN
      SQLSTRING :=
            'SELECT DISTINCT CLI.codcar cartera
    ,DECODE(CLI.modcar,0,'''
         || p_TAGCompartida
         || ''',1,'''
         || p_TAGIndividual
         || ''') modalidad
    ,DECODE(upper(CLI.statcar),''A'',''TAGActiva'',''I'',''TAGInactiva'') status
    ,DECODE(CLI.flgpri,-1,'''
         || p_TAGPrincipal
         || ''','''
         || p_TAGSecundario
         || ''') flag_primario
    ,INITCAP(CLI.referido) asesor
    ,INITCAP(CLI.responsable) ejecutivo
    ,DECODE(CLI.concirif,''0'',CLI.precirif || ''-'' || CLI.cirif, CLI.precirif || ''-'' || CLI.cirif || ''-'' || CLI.concirif)
    ,INITCAP(CLI.na) nombre
    ,upper(CLI.statcar) status_cartera
    ,LOWER(DIR.direcc1) email
    ,CLI.codpercli  
    FROM produccion.clientes CLI,
    produccion.direcciones DIR WHERE';


      IF p_strTxtCarteraNumero IS NOT NULL
      THEN
         IF p_strChkNumeroExactoCartera = '1'
         THEN
            IF agregarAnd = FALSE
            THEN
               wheresql :=
                     wheresql
                  || ' CLI.codcar = '''
                  || p_strTxtCarteraNumero
                  || '''  ';
            ELSE
               wheresql :=
                     wheresql
                  || ' AND CLI.codcar = '''
                  || p_strTxtCarteraNumero
                  || '''  ';
            END IF;

            agregarAnd := TRUE;
         ELSE
            IF agregarAnd = FALSE
            THEN
               wheresql :=
                     wheresql
                  || ' CLI.codcar LIKE   '''
                  || '%'
                  || p_strTxtCarteraNumero
                  || '%'
                  || '''  ';
            ELSE
               wheresql :=
                     wheresql
                  || ' AND CLI.codcar LIKE   '''
                  || '%'
                  || p_strTxtCarteraNumero
                  || '%'
                  || '''  ';
            END IF;

            agregarAnd := TRUE;
         END IF;
      END IF;

      IF p_strTxtCedula_Rif IS NOT NULL
      THEN
         IF agregarAnd = FALSE
         THEN
            wheresql :=
                  wheresql
               || ' UPPER(DECODE(CLI.concirif,''0'',CLI.precirif || ''-'' || CLI.cirif, CLI.precirif || ''-'' || CLI.cirif || ''-'' || CLI.concirif)) LIKE '''
               || '%'
               || p_strTxtCedula_Rif
               || '%'
               || '''  ';
         ELSE
            wheresql :=
                  wheresql
               || ' AND UPPER(DECODE(CLI.concirif,''0'',CLI.precirif || ''-'' || CLI.cirif, CLI.precirif || ''-'' || CLI.cirif || ''-'' || CLI.concirif)) LIKE '''
               || '%'
               || p_strTxtCedula_Rif
               || '%'
               || '''  ';
         END IF;

         agregarAnd := TRUE;
      END IF;

      IF p_strTxtNombreCliente IS NOT NULL
      THEN
         IF agregarAnd = FALSE
         THEN
            wheresql :=
                  wheresql
               || ' CLI.na LIKE '''
               || p_strTxtNombreCliente
               || ''' ';
         ELSE
            wheresql :=
                  wheresql
               || ' AND CLI.na LIKE '''
               || p_strTxtNombreCliente
               || '''';
         END IF;
      END IF;

      IF p_strTxtAsesor IS NOT NULL
      THEN
         IF agregarAnd = FALSE
         THEN
            wheresql :=
               wheresql || ' CLI.referido LIKE ''' || p_strTxtAsesor || ''' ';
         ELSE
            wheresql :=
                  wheresql
               || ' AND CLI.referido LIKE '''
               || p_strTxtAsesor
               || '''';
         END IF;

         agregarAnd := TRUE;
      END IF;

      IF p_strTxtEjecutivo IS NOT NULL
      THEN
         IF agregarAnd = FALSE
         THEN
            wheresql :=
                  wheresql
               || ' CLI.responsable LIKE  '''
               || p_strTxtEjecutivo
               || ''' ';
         ELSE
            wheresql :=
                  wheresql
               || ' AND CLI.responsable LIKE  '''
               || p_strTxtEjecutivo
               || '''';
         END IF;

         agregarAnd := TRUE;
      END IF;

      IF wheresql IS NOT NULL
      THEN
         --auxSQL += " AND CLI.codpercli  = DIR.codpercli(+)";
         --  auxSQL += " AND DIR.tipodir(+) = 'Email Online'";
         SQLSTRING :=
               SQLSTRING
               --|| '    CLI.codpercli  = DIR.codpercli(+) AND DIR.tipodir(+) = ''Email''  AND '
            || '    CLI.codpercli  = DIR.codpercli(+) AND DIR.tipodir(+) = ''Email Online'' AND '
            || wheresql
            || '';
      --where codpercli ='''|| p_codpercli||'''
      END IF;


      SQLSTRING := SQLSTRING || ' ORDER BY CLI.codpercli  ASC';



      p_select := SQLSTRING;

      OPEN p_bac_clientebuscar FOR SQLSTRING;

      p_resultado := 'OK';
      --COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_clientebuscar_pr;


   PROCEDURE accionessistema_pr (p_accionessistema   OUT accionessistema,
                                 p_resultado         OUT VARCHAR2)
   AS
      cs_accionessistema   accionessistema;
   BEGIN
      OPEN cs_accionessistema FOR
           SELECT accion
             FROM accsistcia
            WHERE codsis = 'ONLINE' AND codpercia = 'VBT'
         ORDER BY posicion;

      p_accionessistema := cs_accionessistema;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END accionessistema_pr;

   /******************************************************************************
  NAME:    accesosistema_pr
  PURPOSE: Query para obtener los niveles y los grupos de acceso al sistema
  ******************************************************************************/

   PROCEDURE accesosistema_pr (p_login           IN     VARCHAR2,
                               p_codcia          IN     VARCHAR2,
                               p_codsis          IN     VARCHAR2,
                               p_accesosistema      OUT accesosistema,
                               p_resultado          OUT VARCHAR2)
   AS
      cs_accesosistema   accesosistema;
   BEGIN
      OPEN cs_accesosistema FOR
           SELECT o.codopc codigo,
                  o.desopc descripcion,
                  o.padcodopc codigo_padre,
                  o.nivel nivel,
                  o.orden orden,
                  gco.tipacc acciones
             FROM usugrp_v1 ug, grpciaopc_v1 gco, ctaopc_v1 o
            WHERE     ug.codsis = p_codsis
                  AND ug.codcia = p_codcia
                  AND ug.login = p_login
                  AND gco.codsis = ug.codsis
                  AND gco.codcia = ug.codcia
                  AND gco.codgrp = ug.codgrp
                  AND o.codsis = gco.codsis
                  AND o.codopc = gco.codopc
         ORDER BY o.nivel,
                  o.orden,
                  o.padcodopc,
                  o.codopc;

      p_accesosistema := cs_accesosistema;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END accesosistema_pr;

   /******************************************************************************
    NAME:    accesoopcusuario_pr
    PURPOSE: Query para hallar las opciones del usuario
    ******************************************************************************/

   PROCEDURE accesoopcusuario_pr (
      p_login              IN     VARCHAR2,
      p_codcia             IN     VARCHAR2,
      p_codsis             IN     VARCHAR2,
      p_accesoopcusuario      OUT accesoopcusuario,
      p_resultado             OUT VARCHAR2)
   AS
      cs_accesoopcusuario   accesoopcusuario;
   BEGIN
      OPEN cs_accesoopcusuario FOR
           SELECT o.codopc codigo,
                  o.desopc descripcion,
                  o.padcodopc opcion_padre,
                  o.nivel nivel,
                  o.orden orden,
                  uco.tipacc acciones
             FROM usrciaopc_v1 uco, ctaopc_v1 o
            WHERE     uco.codsis = p_codsis
                  AND uco.codcia = p_codcia
                  AND uco.login = p_login
                  AND o.codsis = uco.codsis
                  AND o.codopc = uco.codopc
         ORDER BY o.nivel,
                  o.orden,
                  o.padcodopc,
                  o.codopc;

      p_accesoopcusuario := cs_accesoopcusuario;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END accesoopcusuario_pr;

   /******************************************************************************
     NAME:    accesogrupossuario_pr
     PURPOSE: Query sql para hallar todos los grupos a los cuales pertenece el usuario
     ******************************************************************************/

   PROCEDURE accesogrupossuario_pr (
      p_login                IN     VARCHAR2,
      p_codcia               IN     VARCHAR2,
      p_codsis               IN     VARCHAR2,
      p_accesogrupossuario      OUT accesogrupossuario,
      p_resultado               OUT VARCHAR2)
   AS
      cs_accesogrupossuario   accesogrupossuario;
   BEGIN
      OPEN cs_accesogrupossuario FOR
           SELECT g.codgrp, g.nombgrp
             FROM ctagrp g, usugrp_v1 ug
            WHERE     ug.codsis = p_codsis
                  AND ug.codcia = p_codcia
                  AND ug.login = p_login
                  AND ug.codgrp = g.codgrp
         ORDER BY g.nombgrp;

      p_accesogrupossuario := cs_accesogrupossuario;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END accesogrupossuario_pr;

   PROCEDURE accionFiltro_pr (p_acciones    OUT acciones,
                              p_resultado   OUT VARCHAR2)
   AS
      cs_acciones   acciones;
   BEGIN
      OPEN cs_acciones FOR
           SELECT DESC_ACTION
             FROM COMMON_ACTIONS_TO_LOG
         ORDER BY 1 ASC;

      p_acciones := cs_acciones;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END accionFiltro_pr;
   
   PROCEDURE accionFiltroFC_pr (p_num_contrato IN VARCHAR2,p_acciones    OUT acciones,
                              p_resultado   OUT VARCHAR2)
   AS
      cs_acciones   acciones;
   BEGIN
      OPEN cs_acciones FOR
           SELECT DISTINCT DESC_ACTION
             FROM COMMON_ACTIONS_TO_LOG A, COMMON_LOG L
             WHERE A.ID_ACTION=L.ID_ACTION AND NUM_CONTRATO=p_num_contrato
         ORDER BY 1 ASC;

      p_acciones := cs_acciones;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END accionFiltroFC_pr;

   PROCEDURE objetosFiltro_pr (p_objetos     OUT objetos,
                               p_resultado   OUT VARCHAR2)
   AS
      cs_objetos   objetos;
   BEGIN
      OPEN cs_objetos FOR
           SELECT DISTINCT DESC_OBJECT
             FROM COMMON_OBJECTS_TO_LOG
            WHERE ID_APP = '1'
         ORDER BY 1 ASC;

      p_objetos := cs_objetos;
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END objetosFiltro_pr;

   PROCEDURE bac_numeroNuevoContrato_pr (p_respuesta   OUT VARCHAR2,
                                         p_resultado   OUT VARCHAR2)
   AS
   BEGIN
      SELECT NUM_CONTRATO_SEQ.NEXTVAL INTO p_respuesta FROM DUAL;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_numeroNuevoContrato_pr;


   PROCEDURE bac_crearContrato_pr (
      p_num_contrato                    VARCHAR2,
      p_descripcion                     VARCHAR2,
      p_creado_por                      VARCHAR2,
      p_ip_contrato_transferencia       VARCHAR2,
      p_tipo_contrato                   VARCHAR2,
      p_resultado                   OUT VARCHAR2)
   IS
   BEGIN
      INSERT INTO vbt_contrato (num_contrato,
                                descripcion,
                                creado_por,
                                fecha_creacion,
                                fecha_status,
                                status,
                                acepto_transferencias,
                                tipo_contrato)
           VALUES (p_num_contrato,
                   p_descripcion,
                   p_creado_por,
                   SYSDATE,
                   SYSDATE,
                   '0',
                   'S',
                   p_tipo_contrato);

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_crearContrato_pr;


   PROCEDURE bac_buscarDireccion_pr (p_codpercli        VARCHAR2,
                                     cs_direccion   OUT SYS_REFCURSOR,
                                     p_resultado    OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_direccion FOR
         SELECT direc_completa direccion, tipodir tipo, flag_envio de_envio
           FROM produccion.direcciones
          WHERE codpercli = p_codpercli AND UPPER (flag_envio) = 'X';

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_buscarDireccion_pr;



   PROCEDURE bac_buscarTelefonos_pr (p_codpercli        VARCHAR2,
                                     cs_telefonos   OUT SYS_REFCURSOR,
                                     p_resultado    OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_telefonos FOR
         SELECT '(' || area || ') ' || telefono, tipo
           FROM produccion.telefonos
          WHERE codpercli = p_codpercli;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_buscarTelefonos_pr;


   PROCEDURE bac_agregarNuevoUsuario_pr (p_login                  VARCHAR2,
                                         p_codpercli              VARCHAR2,
                                         p_direccion              VARCHAR2,
                                         p_telefono_celular       VARCHAR2,
                                         p_telefono               VARCHAR2,
                                         p_email                  VARCHAR2,
                                         p_password               VARCHAR2,
                                         p_nombres                VARCHAR2,
                                         p_cirif                  VARCHAR2,
                                         p_codigo_pais            VARCHAR2,
                                         p_resultado          OUT VARCHAR2)
   IS
   BEGIN
      INSERT INTO vbt_users (login,
                             codpercli,
                             fecha_ingreso,
                             direccion,
                             telefono_celular,
                             telefono,
                             email,
                             password,
                             tipo,
                             nombres,
                             status_cuenta,
                             cirif,
                             fecha_status,
                             ambito,
                             cambio_pass,
                             codigo_pais)
           VALUES (p_login,
                   p_codpercli,
                   SYSDATE,
                   p_direccion,
                   p_telefono_celular,
                   p_telefono,
                   LOWER (p_email),
                   SEGURIDAD.ENCRIPTA (p_password),
                   '2',
                   INITCAP (p_nombres),
                   'I',
                   p_cirif,
                   SYSDATE,
                   'A',
                   'S',
                   p_codigo_pais);

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_agregarNuevoUsuario_pr;


   PROCEDURE bac_agregarUsuarioGrupo_pr (p_login           VARCHAR2,
                                         p_codgrp          VARCHAR2,
                                         p_codsis          VARCHAR2,
                                         p_codcia          VARCHAR2,
                                         p_usrid           VARCHAR2,
                                         p_resultado   OUT VARCHAR2)
   IS
   BEGIN
      INSERT INTO usugrp_v1 (login,
                             codgrp,
                             codsis,
                             codcia,
                             fechasig,
                             usrid)
           VALUES (p_login,
                   p_codgrp,
                   p_codsis,
                   p_codcia,
                   SYSDATE,
                   p_usrid);

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_agregarUsuarioGrupo_pr;


   PROCEDURE bac_agregarUsuarioContrato_pr (p_login              VARCHAR2,
                                            p_num_contrato       VARCHAR2,
                                            p_resultado      OUT VARCHAR2)
   IS
   BEGIN
      INSERT INTO vbt_users_contrato (login, num_contrato)
           VALUES (p_login, p_num_contrato);

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_agregarUsuarioContrato_pr;



   PROCEDURE bac_agregarCarterasContrato_pr (p_num_contrato       VARCHAR2,
                                             p_codcar             VARCHAR2,
                                             p_resultado      OUT VARCHAR2)
   IS
   BEGIN
      INSERT INTO vbt_contrato_carteras (num_contrato, codcar)
           VALUES (p_num_contrato, p_codcar);

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_agregarCarterasContrato_pr;



   PROCEDURE bac_verificarStatusContrato_pr (
      p_num_contrato               VARCHAR2,
      p_statusContratoEditar       VARCHAR2,
      cs_status                OUT SYS_REFCURSOR,
      p_resultado              OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_status FOR
         SELECT status
           FROM vbt_contrato
          WHERE     num_contrato = p_num_contrato
                AND status <> p_statusContratoEditar;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_verificarStatusContrato_pr;



   PROCEDURE bac_editarContrato_pr (p_strDescripcionEditar          VARCHAR2,
                                    p_strStatusContratoEditar       VARCHAR2,
                                    p_strNumeroContratoEditar       VARCHAR2,
                                    p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      IF p_strStatusContratoEditar IS NOT NULL
      THEN
         UPDATE vbt_contrato
            SET descripcion = p_strDescripcionEditar,
                status = p_strStatusContratoEditar,
                fecha_status = SYSDATE
          WHERE num_contrato = p_strNumeroContratoEditar;

         p_resultado := 'OK';
      ELSE
         UPDATE vbt_contrato
            SET descripcion = p_strDescripcionEditar
          WHERE num_contrato = p_strNumeroContratoEditar;

         p_resultado := 'OK';
      END IF;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_editarContrato_pr;



   PROCEDURE bac_editarUsuario_pr (p_strNumeroContratoEditar       VARCHAR2,
                                   p_strStatusUsuario              VARCHAR2,
                                   p_resultado                 OUT VARCHAR2)
   AS
      V_STATUS   VARCHAR2 (1);
   BEGIN

   SELECT STATUS INTO V_STATUS
        FROM VBT_CONTRATO
        WHERE NUM_CONTRATO=p_strNumeroContratoEditar;
        
      --Se Inactivo el Contrato
      IF (V_STATUS='3') THEN
        UPDATE vbt_users
         SET status_cuenta = p_strStatusUsuario, fecha_status = SYSDATE
             WHERE login IN (SELECT login
                         FROM vbt_users_contrato
                        WHERE num_contrato = p_strNumeroContratoEditar) AND status_cuenta='A';
      ELSE
           
            IF (V_STATUS='1') THEN
                UPDATE vbt_users
                     SET status_cuenta = p_strStatusUsuario, fecha_status = SYSDATE
                     WHERE login IN (SELECT login
                                 FROM vbt_users_contrato
                                WHERE num_contrato = p_strNumeroContratoEditar) AND status_cuenta='I'; 
            ELSE 
          
    
                UPDATE vbt_users
                     SET status_cuenta = p_strStatusUsuario, fecha_status = SYSDATE
                     WHERE login IN (SELECT login
                                 FROM vbt_users_contrato
                                WHERE num_contrato = p_strNumeroContratoEditar); 
                
            END IF;
      END IF;

      

      --AND    status_cuenta IN ('A', 'I');

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_editarUsuario_pr;



   PROCEDURE bac_eliminarAsocContrato_pr (
      p_strNumeroContratoEditar       VARCHAR2,
      p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      DELETE FROM vbt_contrato_motivo_rechazo
            WHERE num_contrato = p_strNumeroContratoEditar;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_eliminarAsocContrato_pr;



   PROCEDURE bac_agregarMotivoRechazo_pr (
      p_num_contrato             VARCHAR2,
      p_cod_motivo_rechazo       VARCHAR2,
      p_resultado            OUT VARCHAR2)
   IS
   BEGIN
      INSERT
        INTO vbt_contrato_motivo_rechazo (num_contrato, cod_motivo_rechazo)
      VALUES (p_num_contrato, p_cod_motivo_rechazo);

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_agregarMotivoRechazo_pr;

   PROCEDURE bac_borraCartContrat_pr (
      p_strNumeroContratoEditar       VARCHAR2,
      p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      DELETE FROM vbt_contrato_carteras
            WHERE num_contrato = p_strNumeroContratoEditar;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_borraCartContrat_pr;

   PROCEDURE bac_seleccionaCarteras_pr (
      p_strNumeroContratoEditar       VARCHAR2,
      cs_carteras                 OUT SYS_REFCURSOR,
      p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_carteras FOR
         SELECT codcar
           FROM vbt_contrato_carteras
          WHERE num_contrato = p_strNumeroContratoEditar;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_seleccionaCarteras_pr;


   PROCEDURE bac_buscarUsuarios_pr (
      p_strNumeroContratoEditar       VARCHAR2,
      cs_usuarios                 OUT SYS_REFCURSOR,
      p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_usuarios FOR
         SELECT USU.login, USU.email
           FROM vbt_users USU, vbt_users_contrato UC
          WHERE     UC.num_contrato = p_strNumeroContratoEditar
                AND UC.login = USU.login
                AND USU.status_cuenta = 'A';

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_buscarUsuarios_pr;

   PROCEDURE bac_usuariosIngresaron_pr (
      p_strNumeroContratoEditar       VARCHAR2,
      cs_usuarios                 OUT SYS_REFCURSOR,
      p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_usuarios FOR
         SELECT USU.login
           FROM vbt_users USU, vbt_users_contrato USU_CON
          WHERE     USU_CON.num_contrato = p_strNumeroContratoEditar
                AND USU_CON.login = USU.login
                AND USU.fecha_login IS NOT NULL;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_usuariosIngresaron_pr;


   PROCEDURE bac_actPassUser_pr (p_miPasswTemp          VARCHAR2,
                                 p_strLoginEditar       VARCHAR2,
                                 p_resultado        OUT VARCHAR2)
   IS
   BEGIN
      UPDATE vbt_users
         SET password = SEGURIDAD.ENCRIPTA (p_miPasswTemp)
       WHERE login = p_strLoginEditar;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_actPassUser_pr;

   PROCEDURE bac_eliminarAccionUser_pr (p_login           VARCHAR2,
                                        p_opcion          VARCHAR2,
                                        p_resultado   OUT VARCHAR2)
   IS
   BEGIN
      DELETE usrciaopc_v1
       WHERE login = p_login AND codopc = p_opcion;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_eliminarAccionUser_pr;

   PROCEDURE bac_validarusuario_pr (p_usuario     IN     VARCHAR2,
                                    p_resultado      OUT VARCHAR2)
   AS
      p_login   VARCHAR2 (20);
   BEGIN
      SELECT LOGIN
        INTO p_login
        FROM VBT_USERS
       WHERE LOGIN = LOWER (p_usuario);

      p_resultado := 'NO OK';
   EXCEPTION
      WHEN NO_DATA_FOUND
      THEN
         p_resultado := 'OK';
   END bac_validarusuario_pr;
   
   PROCEDURE bac_mail_ejecutivos (
      p_cartera         IN     VARCHAR2,
      cs_mails              OUT SYS_REFCURSOR,
      p_resultado                      OUT VARCHAR2)
      AS 
                                SQLSTRING VARCHAR2(2000);
   BEGIN
     SQLSTRING:='SELECT DISTINCT CLI.EMAIL_EJECUTIVO
                    FROM  produccion.clientes CLI
                        WHERE  CLI.CODCAR IN ('||p_cartera||') AND CLI.EMAIL_EJECUTIVO LIKE ''%@VBTBANK.COM'' ';
          open cs_mails for SQLSTRING;  
      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_mail_ejecutivos;
   
   PROCEDURE bac_carterasUsuario_pr (
      p_login       VARCHAR2,
      cs_carteras                 OUT SYS_REFCURSOR,
      p_resultado                 OUT VARCHAR2)
   IS
   BEGIN
      OPEN cs_carteras FOR
         SELECT DISTINCT CAR.CODCAR,CAR.NUM_CONTRATO
           FROM VBT_CONTRATO_CARTERAS CAR, VBT_USERS_CONTRATO UCON, VBT_USERS USR
          WHERE UCON.NUM_CONTRATO=CAR.NUM_CONTRATO AND USR.LOGIN=UCON.LOGIN AND USR.LOGIN= p_login;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_carterasUsuario_pr;
   
    PROCEDURE bac_validarContratos_pr (
      p_num_cartera       IN     VARCHAR2,
      p_tipo_contrato     IN     VARCHAR2,
      c_datos             OUT SYS_REFCURSOR,
      p_resultado         OUT VARCHAR2)
   IS
   BEGIN
      OPEN c_datos FOR
          SELECT CON.NUM_CONTRATO, CON.TIPO_CONTRATO, COUNT(*) 
                FROM VBT_CONTRATO CON, VBT_CONTRATO_CARTERAS CCAR
                    WHERE CON.NUM_CONTRATO=CCAR.NUM_CONTRATO 
                          AND CCAR.CODCAR=p_num_cartera
                          AND CON.TIPO_CONTRATO=p_tipo_contrato
                          AND CON.STATUS NOT IN (0,2,3,4)
                          GROUP BY CON.NUM_CONTRATO,CON.TIPO_CONTRATO;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_validarContratos_pr;

   
   
     PROCEDURE bac_consultarlogsms_pr (
                                      p_strContrato                 IN VARCHAR2,
                                      p_strUsuario                 IN VARCHAR2,
                                      p_strTelefono             IN VARCHAR2,
                                      p_strTxtDesde             IN VARCHAR2,
                                      p_strTxtHasta             IN VARCHAR2,
                                      p_strTipoStatus          IN VARCHAR2,
                                      p_strTipoMotivo          IN VARCHAR2,
                                      p_data  OUT SYS_REFCURSOR,
                                      p_resultado OUT VARCHAR2,
                                      p_salida OUT VARCHAR2)  AS 
                                      sqlsquery      VARCHAR2(2000);
                                        
                                        
        begin                         
                
          
        sqlsquery :=  ' SELECT TO_CHAR(VMENSAJE.FECHA_GEN,''dd/mm/yyyy HH:MI A.M.''), 
               CU.NUM_CONTRATO, 
               VMENSAJE.LOGIN, 
               VMENSAJE.TELEFONO, 
               VMENSAJE.NM_PROVEEDOR, 
               ET2.DESCRIPCION,
               ET.DESCRIPCION
        FROM NOTIFICACION.V_MENSAJE VMENSAJE, VBTONLINE_TRANS.VBT_USERS_CONTRATO CU,ELEMENTOS_TIPOS ET, ELEMENTOS_TIPOS ET2
        WHERE  VMENSAJE.MODULO=''VBT ONLINE'' AND VMENSAJE.LOGIN = CU.LOGIN AND ET2.CODTIPO=''0000000015'' AND ET2.CODELEMENTO=VMENSAJE.EST_ENV_M ';

        
                
        

            IF (length(p_strContrato)=4) THEN
                  IF p_strContrato IS NOT NULL THEN
                        sqlsquery := sqlsquery || ' AND CU.NUM_CONTRATO = '''||p_strContrato||'''';
                    END IF;
            ELSE
                  IF p_strContrato IS NOT NULL THEN
                        sqlsquery := sqlsquery || ' AND CU.NUM_CONTRATO LIKE ''%'||p_strContrato||'%''';
                    END IF;
            END IF;
            
            
            
            IF p_strUsuario IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND VMENSAJE.LOGIN = '''|| p_strUsuario||'''';
            END IF;
            
            IF p_strTelefono IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND VMENSAJE.TELEFONO LIKE ''%'||p_strTelefono||'%''';
            END IF;
            
            IF p_strTxtDesde IS NOT NULL THEN
                 sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(VMENSAJE.FECHA_GEN,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||p_strTxtDesde||''',''dd/mm/yyyy'')';
            END IF;
            
            IF p_strTxtHasta IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(VMENSAJE.FECHA_GEN,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||p_strTxtHasta||''',''dd/mm/yyyy'')';
            END IF;
            
            IF p_strTipoStatus IS NOT NULL THEN
                IF p_strTipoStatus <>'-2' THEN
                    sqlsquery := sqlsquery || ' AND VMENSAJE.EST_ENV_M = '''||p_strTipoStatus||'''';
                END IF;
            END IF;
            
              IF p_strTipoMotivo IS NOT NULL THEN
                IF p_strTipoMotivo <>'-2' THEN
                    sqlsquery := sqlsquery || ' AND ET.CODTIPO=''0000000016'' AND ET.CODELEMENTO=VMENSAJE.MOTIVO  ' || ' AND ET.CODELEMENTO = '''||p_strTipoMotivo||'''';
                ELSE
                   sqlsquery := sqlsquery || ' AND ET.CODTIPO=''0000000016'' AND ET.CODELEMENTO=VMENSAJE.MOTIVO  ';
                 
                END IF;
            END IF;
            
            
            
       
            p_salida:=sqlsquery;
            open  p_data for sqlsquery;
            --execute immediate sqlsquery into cs_bac_contratos; 
            --p_bac_contratos:= cs_bac_contratos;    
            p_resultado:= 'OK';    
         -- commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end bac_consultarlogsms_pr;
  
     PROCEDURE bac_paisesNoPermitidos_pr (
    p_strCodPais         IN VARCHAR2,
    p_strNomPais         IN VARCHAR2,
    p_strPaises          IN VARCHAR2,
    p_data           OUT SYS_REFCURSOR,
    p_resultado      OUT VARCHAR2,
    p_salida         OUT VARCHAR2) AS 
    SQLSTRING    VARCHAR2(2000);
    SQLWHERE     VARCHAR2(10);

    BEGIN

        SQLSTRING :=  'SELECT DISTINCT
                          VPAISES.COUNTRY_CODE,
                          VPAISES.COUNTRY,
                          DECODE (N.COUNTRY_CODE, NULL, ''N'', ''S'') LISTA_NEGRA
                          
                     FROM INSTITUCION@BANCO_VBTF VPAISES, LISTA_NEGRA N
                    WHERE 
                          VPAISES.COUNTRY_CODE = N.COUNTRY_CODE(+) ';

        IF p_strPaises='0' THEN
        
            SQLSTRING := SQLSTRING || 'AND VPAISES.COUNTRY_CODE NOT IN (SELECT COUNTRY_CODE FROM LISTA_NEGRA)';

        ELSE 
        
            IF (p_strPaises='1') THEN
            
                SQLSTRING := SQLSTRING || 'AND VPAISES.COUNTRY_CODE IN (SELECT COUNTRY_CODE FROM LISTA_NEGRA)';
          
            END IF;
            
        END IF;
    
        IF p_strCodPais IS NOT NULL THEN
           
                SQLSTRING := SQLSTRING || 'AND vPAISES.COUNTRY_CODE = '''|| p_strCodPais||'''';
         
        END IF;
        
        IF p_strNomPais IS NOT NULL THEN
          
                    SQLSTRING := SQLSTRING || 'AND UPPER(vPAISES.COUNTRY)  LIKE ''%'||p_strNomPais||'%''';
 
        END IF;

        
        SQLSTRING := SQLSTRING || ' ORDER BY vPAISES.country ASC';

        OPEN  p_data FOR SQLSTRING;
        p_resultado:='OK';
        p_salida:=SQLSTRING;
        EXCEPTION
        WHEN OTHERS THEN 
            p_resultado:= SUBSTR(SQLERRM,1,300);
      
         
 
   end bac_paisesNoPermitidos_pr;
   
    
  PROCEDURE bac_cambiaPaisNoPermitidos_pr(
                            p_strCodPais   IN VARCHAR2,
                            p_strNomPais   IN VARCHAR2,
                            p_strOperacion IN VARCHAR2 ,
                            p_resultado      OUT VARCHAR2)        

                            
                            
   AS
   BEGIN
      IF p_strOperacion = '0'
      THEN 
         DELETE FROM LISTA_NEGRA
               WHERE UPPER(COUNTRY_CODE) = UPPER(p_strCodPais)
               AND UPPER(COUNTRY) = UPPER(p_strNomPais);
      ELSIF p_strOperacion = '1'
      THEN  
         INSERT INTO LISTA_NEGRA (COUNTRY_CODE,COUNTRY)
              VALUES (p_strCodPais,    p_strNomPais);
      END IF;
      
      --COMMIT;
      p_resultado := 'OK';
      
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_cambiaPaisNoPermitidos_pr;
      
  /*
    TAG:          BAC_PRC_LISTAR_CATOPC_PADRES
    AUTOR:        RRANGEL
    FECHA:        2014/11/04
    DESCRIPCION:  PROCEDIMIENTO PARA LA OBTENCIÓN DE CATEGORIAS DE OPCIONES POR 
                  GRUPO.
  */  
  PROCEDURE BAC_PRC_LISTAR_CATOPC_PADRES (
      I_CODIGO_GRUPO IN VARCHAR2,
      O_LISTA_CATEGORIAS_PADRES OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    OPEN O_LISTA_CATEGORIAS_PADRES FOR
      SELECT DISTINCT TOP2.*, NVL(TD1.TIPACC,'00000000') TIPACC
        FROM 
          CTAOPC_V1 TOP2,
          GRPCIAOPC_V1 TGO2,
          (
            SELECT DISTINCT TOP1.SELECTORA, TGO1.TIPACC, TOP1.CODOPC
              FROM 
                CTAOPC_V1 TOP1,  
                GRPCIAOPC_V1 TGO1
              WHERE
                TGO1.CODGRP = I_CODIGO_GRUPO AND
                TOP1.CODOPC = TGO1.CODOPC AND
                (
                  TOP1.PADCODOPC IS NULL OR
                  TOP1.PADCODOPC IN ('9999999999','999999')
                )
                --TOP1.PADCODOPC IS NULL
          ) TD1
        WHERE
          TGO2.CODOPC = TOP2.CODOPC AND
          TGO2.CODOPC = TD1.CODOPC(+) AND
          (
            TOP2.SELECTORA IS NULL OR
            TOP2.SELECTORA IN (
              SELECT CASE TCG1.CATEGORIA WHEN 'FC' THEN 'C' ELSE TCG1.CATEGORIA END
                FROM 
                  CTAGRP TCG1
                WHERE
                  TCG1.CODGRP = I_CODIGO_GRUPO 
            )
      
          ) AND
          --TOP2.PADCODOPC IS NULL
          (
            TOP2.PADCODOPC IS NULL OR
            TOP2.PADCODOPC IN ('9999999999','999999')
          )
        ORDER BY 
          TOP2.SELECTORA DESC, 
          TOP2.CODOPC ASC
      ;
      
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_LISTAR_CATOPC_PADRES;
  /* FIN BAC_PRC_LISTAR_CATOPC_PADRES */

  /*
    TAG:          BAC_PRC_LISTAR_CATOPC
    AUTOR:        RRANGEL
    FECHA:        2014/11/04
    DESCRIPCION:  PROCEDIMIENTO PARA LA OBTENCIÓN DE CATEGORIAS DE OPCIONES POR 
                  OPCIÓN PADRE.
  */  
  PROCEDURE BAC_PRC_LISTAR_CATOPC (
      I_CODIGO_GRUPO IN VARCHAR2,
      I_CODIGO_OPCION_PADRE IN VARCHAR2,
      O_LISTA_CATEGORIAS OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    OPEN O_LISTA_CATEGORIAS FOR
      SELECT DISTINCT TOP1.*, NVL(TGO.TIPACC,'00000000') TIPACC
        FROM 
          CTAOPC_V1 TOP1,  
          GRPCIAOPC_V1 TGO
        WHERE
          TGO.CODGRP(+) = I_CODIGO_GRUPO AND
          TOP1.PADCODOPC = I_CODIGO_OPCION_PADRE AND
          TOP1.CODOPC = TGO.CODOPC(+)
          AND TOP1.SELECTORA !='NA'
        ORDER BY 
          TOP1.SELECTORA DESC, 
          TOP1.CODOPC ASC
    ;
    
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_LISTAR_CATOPC;
  /* FIN BAC_PRC_LISTAR_CATOPC */

  /*
    TAG:          BAC_PRC_ACTUALIZAR_CATOPC
    AUTOR:        RRANGEL
    FECHA:        2014/11/04
    DESCRIPCION:  PROCEDIMIENTO PARA LA OBTENCIÓN DE CATEGORIAS DE OPCIONES POR 
                  OPCIÓN PADRE.
  */  
  PROCEDURE BAC_PRC_ACTUALIZAR_CATOPC (
      I_CODIGO_GRUPO IN VARCHAR2,
      I_CODIGO_OPCION IN VARCHAR2,
      I_TIPO_ACCION IN VARCHAR2,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2,
      I_USUARIO IN VARCHAR2
  ) AS
    V_EXISTE_OPCION NUMBER;
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    V_EXISTE_OPCION := 0;
    
    SELECT COUNT(*)
      INTO
        V_EXISTE_OPCION
      FROM 
        GRPCIAOPC_V1 TOG
      WHERE
        TOG.CODGRP = I_CODIGO_GRUPO AND
        TOG.CODOPC = I_CODIGO_OPCION
    ;    
    
    IF (V_EXISTE_OPCION = 0) THEN
    
      INSERT INTO 
        GRPCIAOPC_V1(
          CODGRP,
          CODSIS,
          CODCIA,
          CODOPC,
          TIPACC,
          USRID
        ) VALUES (
          I_CODIGO_GRUPO,
          'ONLINE',
          'VBT',
          I_CODIGO_OPCION,
          I_TIPO_ACCION,
          I_USUARIO
        );
        
    ELSE
    
      UPDATE 
        GRPCIAOPC_V1 TOG
        SET
          TOG.TIPACC = I_TIPO_ACCION
        WHERE
          TOG.CODGRP = I_CODIGO_GRUPO AND
          TOG.CODOPC = I_CODIGO_OPCION
      ;
      
    END IF;
    
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_ACTUALIZAR_CATOPC;
  /* FIN BAC_PRC_ACTUALIZAR_CATOPC */

  /*
    TAG:          BAC_PRC_CONSULTAR_OPC_ESP
    AUTOR:        RRANGEL
    FECHA:        2014/12/09
    DESCRIPCION:  PROCEDIMIENTO PARA LA OBTENCIÓN DE CATEGORIAS DE OPCIONES 
                  ESPECIALES POR USUARIO.
  */  
  PROCEDURE BAC_PRC_CONSULTAR_OPC_ESP (
      I_LOGIN IN VARCHAR2,
      O_LISTADO OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2,
      I_TIPO IN VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := 0;
    O_RESP_MENSAJE := 'SUCCESS';
    
    
    IF I_TIPO!='FC' THEN
    
    
        OPEN O_LISTADO FOR
          SELECT 
            TD.CODIGO CODIGO,
            TD.DESCRIPCION,
            TD.CODIGO_PADRE,
            TD.NIVEL,
            TD.ORDEN,
            NVL(TD.ACCIONES_GRUPO,TD.ACCIONES_USER) ACCIONES,
            TD.CODGRP,
            TD.MNOPCIONACCION,
            TD.SELECTORA
          FROM
            (
              SELECT *
                FROM
                (
                  SELECT 
                    COG.CODOPC CODIGO,
                    COG.DESOPC DESCRIPCION,
                    NVL(COG.PADCODOPC,0) CODIGO_PADRE,
                    COG.NIVEL NIVEL,
                    COG.ORDEN ORDEN,
                    '00000000' ACCIONES_GRUPO,
                    '0' CODGRP,
                    COG.MNOPCIONACCION,
                    TO_NUMBER(COG.ORDEN_RESULTSET),
                    NULL ACCIONES_USER,
                    COG.SELECTORA
                    FROM
                      CTAOPC_V1 COG
                    WHERE
                      COG.CODOPC NOT IN (
                        SELECT GCO.CODOPC 
                          FROM
                            GRPCIAOPC_V1 GCO
                          WHERE
                            GCO.CODGRP = (
                              SELECT 
                                UG.CODGRP
                                FROM
                                  USUGRP_V1 UG
                                WHERE
                                  LOWER(UG.LOGIN) = LOWER(I_LOGIN)
                            )
                      ) AND
                      COG.CODOPC NOT IN (
                        SELECT UCO.CODOPC 
                          FROM
                            USRCIAOPC_V1 UCO
                          WHERE
                            LOWER(UCO.LOGIN) = LOWER(I_LOGIN)
                      ) AND (
                        COG.SELECTORA = (
                          SELECT CG.CATEGORIA 
                            FROM
                              CTAGRP CG
                            WHERE
                              CG.CODGRP = (
                                SELECT 
                                  UG.CODGRP
                                  FROM
                                    USUGRP_V1 UG
                                  WHERE
                                    LOWER(UG.LOGIN) = LOWER(I_LOGIN)
                              ) 
                        ) OR
                        COG.SELECTORA IS NULL
                      )
                  UNION ALL    
                  SELECT 
                    COG.CODOPC CODIGO,
                    COG.DESOPC DESCRIPCION,
                    NVL(COG.PADCODOPC,0) CODIGO_PADRE,
                    COG.NIVEL NIVEL,
                    COG.ORDEN ORDEN,
                    GCO.TIPACC ACCIONES_GRUPO,
                    GCO.CODGRP,
                    COG.MNOPCIONACCION,
                    TO_NUMBER(COG.ORDEN_RESULTSET),
                    NULL ACCIONES_USER,
                    COG.SELECTORA
                    FROM
                      GRPCIAOPC_V1 GCO,
                      CTAOPC_V1 COG
                    WHERE
                      GCO.CODGRP = (
                        SELECT UGG.CODGRP 
                          FROM
                            USUGRP_V1 UGG
                          WHERE
                            LOWER(UGG.LOGIN) = LOWER(I_LOGIN)
                      ) AND
                      GCO.CODOPC NOT IN (
                        SELECT UCOG.CODOPC
                          FROM
                            USRCIAOPC_V1 UCOG
                          WHERE  
                            LOWER(UCOG.LOGIN) = LOWER(I_LOGIN)
                      ) AND
                      COG.CODOPC = GCO.CODOPC AND (
                        COG.SELECTORA = (
                          SELECT CG.CATEGORIA 
                            FROM
                              CTAGRP CG
                            WHERE
                              CG.CODGRP = (
                                SELECT 
                                  UG.CODGRP
                                  FROM
                                    USUGRP_V1 UG
                                  WHERE
                                    LOWER(UG.LOGIN) = LOWER(I_LOGIN)
                              ) 
                        ) OR
                        COG.SELECTORA IS NULL
                      )
                  UNION ALL
                  SELECT  
                    UCO.CODOPC CODIGO,
                    COU.DESOPC DESCRIPCION,
                    NVL(COU.PADCODOPC,0) CODIGO_PADRE,
                    COU.NIVEL NIVEL,
                    COU.ORDEN ORDEN,
                    NULL ACCIONES_GRUPO,
                    UGU.CODGRP,
                    COU.MNOPCIONACCION,
                    TO_NUMBER(COU.ORDEN_RESULTSET),
                    UCO.TIPACC ACCIONES_USER,
                    COU.SELECTORA
                    FROM
                      USRCIAOPC_V1 UCO,
                      CTAOPC_V1 COU,
                      USUGRP_V1 UGU
                    WHERE
                      LOWER(UCO.LOGIN) = LOWER(I_LOGIN) AND
                      COU.CODOPC = UCO.CODOPC AND
                      LOWER(UGU.LOGIN) = LOWER(UCO.LOGIN)
                ) 
                ORDER BY 9
            ) TD
          ;
          
     ELSE
     
         IF I_TIPO!='B' THEN
              OPEN O_LISTADO FOR
          SELECT 
            TD.CODIGO CODIGO,
            TD.DESCRIPCION,
            TD.CODIGO_PADRE,
            TD.NIVEL,
            TD.ORDEN,
            NVL(TD.ACCIONES_GRUPO,TD.ACCIONES_USER) ACCIONES,
            TD.CODGRP,
            TD.MNOPCIONACCION,
            TD.SELECTORA
          FROM
            (
              SELECT *
                FROM
                (
                  SELECT 
                    COG.CODOPC CODIGO,
                    COG.DESOPC DESCRIPCION,
                    NVL(COG.PADCODOPC,0) CODIGO_PADRE,
                    COG.NIVEL NIVEL,
                    COG.ORDEN ORDEN,
                    '00000000' ACCIONES_GRUPO,
                    '0' CODGRP,
                    COG.MNOPCIONACCION,
                    TO_NUMBER(COG.ORDEN_RESULTSET),
                    NULL ACCIONES_USER,
                    COG.SELECTORA
                    FROM
                      CTAOPC_V1 COG
                    WHERE
                      COG.CODOPC NOT IN (
                        SELECT GCO.CODOPC 
                          FROM
                            GRPCIAOPC_V1 GCO
                          WHERE
                            GCO.CODGRP = (
                              SELECT 
                                UG.CODGRP
                                FROM
                                  USUGRP_V1 UG
                                WHERE
                                  LOWER(UG.LOGIN) = LOWER(I_LOGIN)
                            )
                      ) AND
                      COG.CODOPC NOT IN (
                        SELECT UCO.CODOPC 
                          FROM
                            USRCIAOPC_V1 UCO
                          WHERE
                            LOWER(UCO.LOGIN) = LOWER(I_LOGIN)
                      ) AND (
                        COG.SELECTORA !='B' OR
                        COG.SELECTORA IS NULL
                      )
                  UNION ALL    
                  SELECT 
                    COG.CODOPC CODIGO,
                    COG.DESOPC DESCRIPCION,
                    NVL(COG.PADCODOPC,0) CODIGO_PADRE,
                    COG.NIVEL NIVEL,
                    COG.ORDEN ORDEN,
                    GCO.TIPACC ACCIONES_GRUPO,
                    GCO.CODGRP,
                    COG.MNOPCIONACCION,
                    TO_NUMBER(COG.ORDEN_RESULTSET),
                    NULL ACCIONES_USER,
                    COG.SELECTORA
                    FROM
                      GRPCIAOPC_V1 GCO,
                      CTAOPC_V1 COG
                    WHERE
                      GCO.CODGRP = (
                        SELECT UGG.CODGRP 
                          FROM
                            USUGRP_V1 UGG
                          WHERE
                            LOWER(UGG.LOGIN) = LOWER(I_LOGIN)
                      ) AND
                      GCO.CODOPC NOT IN (
                        SELECT UCOG.CODOPC
                          FROM
                            USRCIAOPC_V1 UCOG
                          WHERE  
                            LOWER(UCOG.LOGIN) = LOWER(I_LOGIN)
                      ) AND
                      COG.CODOPC = GCO.CODOPC AND (
                        COG.SELECTORA !='B'
                      )
                  UNION ALL
                  SELECT  
                    UCO.CODOPC CODIGO,
                    COU.DESOPC DESCRIPCION,
                    NVL(COU.PADCODOPC,0) CODIGO_PADRE,
                    COU.NIVEL NIVEL,
                    COU.ORDEN ORDEN,
                    NULL ACCIONES_GRUPO,
                    UGU.CODGRP,
                    COU.MNOPCIONACCION,
                    TO_NUMBER(COU.ORDEN_RESULTSET),
                    UCO.TIPACC ACCIONES_USER,
                    COU.SELECTORA
                    FROM
                      USRCIAOPC_V1 UCO,
                      CTAOPC_V1 COU,
                      USUGRP_V1 UGU
                    WHERE
                      LOWER(UCO.LOGIN) = LOWER(I_LOGIN) AND
                      COU.CODOPC = UCO.CODOPC AND
                      LOWER(UGU.LOGIN) = LOWER(UCO.LOGIN)
                ) 
                ORDER BY 9
            ) TD;
         ELSE
              OPEN O_LISTADO FOR
          SELECT 
            TD.CODIGO CODIGO,
            TD.DESCRIPCION,
            TD.CODIGO_PADRE,
            TD.NIVEL,
            TD.ORDEN,
            NVL(TD.ACCIONES_GRUPO,TD.ACCIONES_USER) ACCIONES,
            TD.CODGRP,
            TD.MNOPCIONACCION,
            TD.SELECTORA
          FROM
            (
              SELECT *
                FROM
                (
                  SELECT 
                    COG.CODOPC CODIGO,
                    COG.DESOPC DESCRIPCION,
                    NVL(COG.PADCODOPC,0) CODIGO_PADRE,
                    COG.NIVEL NIVEL,
                    COG.ORDEN ORDEN,
                    '00000000' ACCIONES_GRUPO,
                    '0' CODGRP,
                    COG.MNOPCIONACCION,
                    TO_NUMBER(COG.ORDEN_RESULTSET),
                    NULL ACCIONES_USER,
                    COG.SELECTORA
                    FROM
                      CTAOPC_V1 COG
                    WHERE
                      COG.CODOPC NOT IN (
                        SELECT GCO.CODOPC 
                          FROM
                            GRPCIAOPC_V1 GCO
                          WHERE
                            GCO.CODGRP = (
                              SELECT 
                                UG.CODGRP
                                FROM
                                  USUGRP_V1 UG
                                WHERE
                                  LOWER(UG.LOGIN) = LOWER(I_LOGIN)
                            )
                      ) AND
                      COG.CODOPC NOT IN (
                        SELECT UCO.CODOPC 
                          FROM
                            USRCIAOPC_V1 UCO
                          WHERE
                            LOWER(UCO.LOGIN) = LOWER(I_LOGIN)
                      ) AND (
                        COG.SELECTORA ='B' OR
                        COG.SELECTORA IS NULL
                      )
                  UNION ALL    
                  SELECT 
                    COG.CODOPC CODIGO,
                    COG.DESOPC DESCRIPCION,
                    NVL(COG.PADCODOPC,0) CODIGO_PADRE,
                    COG.NIVEL NIVEL,
                    COG.ORDEN ORDEN,
                    GCO.TIPACC ACCIONES_GRUPO,
                    GCO.CODGRP,
                    COG.MNOPCIONACCION,
                    TO_NUMBER(COG.ORDEN_RESULTSET),
                    NULL ACCIONES_USER,
                    COG.SELECTORA
                    FROM
                      GRPCIAOPC_V1 GCO,
                      CTAOPC_V1 COG
                    WHERE
                      GCO.CODGRP = (
                        SELECT UGG.CODGRP 
                          FROM
                            USUGRP_V1 UGG
                          WHERE
                            LOWER(UGG.LOGIN) = LOWER(I_LOGIN)
                      ) AND
                      GCO.CODOPC NOT IN (
                        SELECT UCOG.CODOPC
                          FROM
                            USRCIAOPC_V1 UCOG
                          WHERE  
                            LOWER(UCOG.LOGIN) = LOWER(I_LOGIN)
                      ) AND
                      COG.CODOPC = GCO.CODOPC AND (
                        COG.SELECTORA ='B'
                      )
                  UNION ALL
                  SELECT  
                    UCO.CODOPC CODIGO,
                    COU.DESOPC DESCRIPCION,
                    NVL(COU.PADCODOPC,0) CODIGO_PADRE,
                    COU.NIVEL NIVEL,
                    COU.ORDEN ORDEN,
                    NULL ACCIONES_GRUPO,
                    UGU.CODGRP,
                    COU.MNOPCIONACCION,
                    TO_NUMBER(COU.ORDEN_RESULTSET),
                    UCO.TIPACC ACCIONES_USER,
                    COU.SELECTORA
                    FROM
                      USRCIAOPC_V1 UCO,
                      CTAOPC_V1 COU,
                      USUGRP_V1 UGU
                    WHERE
                      LOWER(UCO.LOGIN) = LOWER(I_LOGIN) AND
                      COU.CODOPC = UCO.CODOPC AND
                      LOWER(UGU.LOGIN) = LOWER(UCO.LOGIN)
                ) 
                ORDER BY 9
            ) TD;
         END IF;
      END IF;
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE;
        O_RESP_MENSAJE := 'WRONG - ' || SQLERRM;
        
  END BAC_PRC_CONSULTAR_OPC_ESP;
  /* FIN BAC_PRC_CONSULTAR_OPC_ESP */

  /*
    TAG:          BAC_PRC_ACTUALIZAR_OPC_ESP
    AUTOR:        RRANGEL
    FECHA:        2014/12/09
    DESCRIPCION:  PROCEDIMIENTO PARA LA OBTENCIÓN DE CATEGORIAS DE OPCIONES 
                  ESPECIALES POR USUARIO.
  */  
  PROCEDURE BAC_PRC_ACTUALIZAR_OPC_ESP (
      I_LOGIN IN VARCHAR2,
      I_CODOPC IN VARCHAR2,
      I_TIPACC IN VARCHAR2,
      I_USRID IN VARCHAR2,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
    V_TIPACC GRPCIAOPC_V1.TIPACC%TYPE;
    V_EXISTE NUMBER;
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
        DELETE FROM
          USRCIAOPC_V1 TUCO
          WHERE  
            LOWER(TUCO.LOGIN) = LOWER(I_LOGIN) AND
            TUCO.CODOPC = I_CODOPC
        ;    
          
     INSERT INTO 
              USRCIAOPC_V1 (
                LOGIN,
                CODCIA,
                CODSIS,
                CODOPC,
                TIPACC,
                USRID,
                NIVSUP
              ) VALUES (
                I_LOGIN,
                'VBT',
                'ONLINE',
                I_CODOPC,
                I_TIPACC,
                I_USRID,
                NULL
              )
            ;
      
      O_RESP_CODIGO := '1';
      O_RESP_MENSAJE := 'SUCCESS';
      

    
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE;
        O_RESP_MENSAJE := 'WRONG - ' || SQLERRM;
        
    
  END BAC_PRC_ACTUALIZAR_OPC_ESP;
  /* FIN BAC_PRC_ACTUALIZAR_OPC_ESP */

  PROCEDURE bac_roles_fc_pr (
      p_bac_roles             OUT SYS_REFCURSOR,
      p_resultado             OUT VARCHAR2)
  IS
   BEGIN
      OPEN p_bac_roles FOR
             SELECT CTA.CODROL, CTA.NOMBROL, CTA.USRID,  CTA.CATEGORIA, CTA.VISIBLE,
                  DECODE(CTA.CODROL,'0000000005', (SELECT COUNT (UGRP.LOGIN)
                     FROM USUGRP_V1 UGRP
                    WHERE UGRP.CODGRP ='0000000017'), (SELECT COUNT (UROL.LOGIN)
                          FROM FC_USUROL UROL
                                WHERE UROL.CODROL  = CTA.CODROL))  CANTIDADUSUARIOS,
               (SELECT COUNT (*)
                          FROM FC_ROLCIAOPC ROPC
                    WHERE ROPC.CODROL  = CTA.CODROL)
                         CANTIDADOPCIONES
            FROM FC_CTAROL CTA;

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END bac_roles_fc_pr;


 PROCEDURE BAC_PRC_CATOPC_PADRES_ROLES (
      I_CODIGO_GRUPO IN VARCHAR2,
      O_LISTA_CATEGORIAS_PADRES OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    OPEN O_LISTA_CATEGORIAS_PADRES FOR
       SELECT DISTINCT OPC.*, NVL((SELECT OAC1.TIPACC
            FROM  FC_OPCACC OAC1, FC_ROLCIAOPC ROL1 
            WHERE ROL1.CODROL=I_CODIGO_GRUPO AND ROL1.CODOPC=OPC.CODOPC
            AND ROL1.CODOPC=OAC1.CODOPC),'00000000') TIPACC
          FROM CTAOPC_V1 OPC,FC_OPCACC OAC
          WHERE 
              OPC.CODOPC=OAC.CODOPC(+)
              AND
                (
                  OPC.PADCODOPC IS NULL OR
                  OPC.PADCODOPC IN ('9999999999','999999')
                )
              AND
                (
                  OPC.SELECTORA IS NULL OR  OPC.SELECTORA IN('FC', 'C')

                )
                
          ORDER BY OPC.SELECTORA DESC, OPC.CODOPC ASC;
          
       
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_CATOPC_PADRES_ROLES;

 PROCEDURE BAC_PRC_LISTAR_CATOPC_ROLES (
      I_CODIGO_GRUPO IN VARCHAR2,
      I_CODIGO_OPCION_PADRE IN VARCHAR2,
      O_LISTA_CATEGORIAS OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    OPEN O_LISTA_CATEGORIAS FOR
      SELECT DISTINCT TOP1.*, NVL((SELECT OAC1.TIPACC
            FROM  FC_OPCACC OAC1, FC_ROLCIAOPC ROL1 
            WHERE ROL1.CODROL=I_CODIGO_GRUPO AND ROL1.CODOPC=TOP1.CODOPC
            AND ROL1.CODOPC=OAC1.CODOPC),'00000000') TIPACC
        FROM 
          CTAOPC_V1 TOP1,  
          FC_OPCACC OAC
        WHERE
          TOP1.PADCODOPC = I_CODIGO_OPCION_PADRE
          AND TOP1.SELECTORA !='NA'
          --AND OAC.CODOPC=TOP1.CODOPC
        ORDER BY 
          TOP1.SELECTORA DESC, 
          TOP1.CODOPC ASC;     
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_LISTAR_CATOPC_ROLES;

 PROCEDURE BAC_PRC_ACTUALIZAR_CATOPC_ROL (
      I_CODIGO_ROL IN VARCHAR2,
      I_CODIGO_OPCION IN VARCHAR2,
      I_TIPO_ACCION IN VARCHAR2,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2,
      I_USUARIO IN VARCHAR2
  ) AS
    V_EXISTE_OPCION NUMBER;
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    V_EXISTE_OPCION := 0;
         
             
                  DELETE FROM
                    FC_ROLCIAOPC TOG
                        WHERE
                            TOG.CODROL = I_CODIGO_ROL AND
                            TOG.CODOPC = I_CODIGO_OPCION;
        
 
        IF (TO_NUMBER(I_TIPO_ACCION)>0) THEN
          INSERT INTO 
            FC_ROLCIAOPC(
              CODROL,
              CODOPC,
              USRID
            ) VALUES (
              I_CODIGO_ROL,
              I_CODIGO_OPCION,
              I_USUARIO
            );
         END IF;
    --Actualiza las usuarios tipo Personalizado
    BACKOFFICE.ACTUALIZAR_USUARIOS_CUSTOM_FC(I_USUARIO);
  
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_ACTUALIZAR_CATOPC_ROL;
     
PROCEDURE BAC_PRC_CATOPC_PADRES (
      O_LISTA_CATEGORIAS_PADRES OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    OPEN O_LISTA_CATEGORIAS_PADRES FOR
        SELECT DISTINCT OPC.*, NVL(OAC.TIPACC,'00000000') TIPACC
          FROM CTAOPC_V1 OPC,FC_OPCACC OAC
          WHERE 
              OPC.CODOPC=OAC.CODOPC(+)
              AND
                (
                  OPC.PADCODOPC IS NULL OR
                  OPC.PADCODOPC IN ('9999999999','999999')
                )
              AND
                (
                  OPC.SELECTORA IS NULL OR  OPC.SELECTORA IN('FC', 'C')

                )
          ORDER BY OPC.SELECTORA DESC, OPC.CODOPC ASC;  
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_CATOPC_PADRES;
   PROCEDURE BAC_PRC_CATOPC_HIJOS (
      I_CODIGO_OPCION_PADRE IN VARCHAR2,
      O_LISTA_CATEGORIAS OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    OPEN O_LISTA_CATEGORIAS FOR
      SELECT DISTINCT OPC.*, NVL(OAC.TIPACC,'00000000') TIPACC
          FROM CTAOPC_V1 OPC,FC_OPCACC OAC
          WHERE 
              OPC.CODOPC=OAC.CODOPC(+)
              AND
                (
                (OPC.SELECTORA !='NA') OR SELECTORA IS NULL

                )
              AND  OPC.PADCODOPC = I_CODIGO_OPCION_PADRE
           ORDER BY OPC.SELECTORA DESC,OPC.CODOPC ASC;
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_CATOPC_HIJOS;
  
  PROCEDURE BAC_PRC_ACTUALIZAR_OPCACC (
      I_LOGIN IN VARCHAR2,
      I_CODIGO_OPCION IN VARCHAR2,
      I_TIPO_ACCION IN VARCHAR2,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  ) AS
    V_EXISTE_OPCION NUMBER;
  BEGIN
  
    O_RESP_CODIGO := '0';
    O_RESP_MENSAJE := 'SUCCESS';
    
    V_EXISTE_OPCION := 0;
         
             
                  DELETE FROM
                    FC_OPCACC OPC
                        WHERE
                            OPC.CODOPC = I_CODIGO_OPCION;
            
     IF (TO_NUMBER(I_TIPO_ACCION)>0) THEN
        
          INSERT INTO 
            FC_OPCACC(
              CODOPC,
              TIPACC,
              USRID,
              VISIBLE
            ) VALUES (
              I_CODIGO_OPCION,
              I_TIPO_ACCION,
              I_LOGIN,
              'S'
            );
    END IF;
   
    EXCEPTION
      WHEN OTHERS THEN
        O_RESP_CODIGO := SQLCODE ;
        O_RESP_MENSAJE := SQLERRM;
      
  END BAC_PRC_ACTUALIZAR_OPCACC;
  
  
  
  
    
  PROCEDURE ACTUALIZAR_USUARIOS_CUSTOM_FC (
      I_USUARIO IN VARCHAR2
  ) AS
   
   CURSOR CURSOR_FC_USUROL IS 
      SELECT DISTINCT FC_USUROL.LOGIN
          FROM FC_USUROL
          ORDER BY LOGIN; 
  
BEGIN 
    FOR I IN CURSOR_FC_USUROL LOOP
        BEGIN
              DELETE FROM USRCIAOPC_V1
                                     WHERE LOGIN=I.LOGIN;
               
              INSERT INTO USRCIAOPC_V1(LOGIN,CODSIS, CODCIA, CODOPC,TIPACC, USRID)
                             SELECT DISTINCT I.LOGIN,
                                    'ONLINE', 'VBT',OPC.CODOPC,OAC.TIPACC, I_USUARIO
                                    FROM FC_ROLCIAOPC OPC, FC_OPCACC OAC
                                    WHERE OPC.CODROL IN (SELECT CODROL FROM FC_USUROL WHERE LOGIN=I.LOGIN) AND OAC.CODOPC=OPC.CODOPC;

        END;
                     
    END LOOP;
    
   COMMIT;
             
   EXCEPTION 
   WHEN OTHERS THEN    
        ROLLBACK;
  END ACTUALIZAR_USUARIOS_CUSTOM_FC;
  
   PROCEDURE BAC_CREAR_AVISO (p_tipo_aviso       IN     VARCHAR2,
                                 p_titulo        IN     VARCHAR2,
                                 p_estatus       IN     VARCHAR2,
                                 p_fecha_inicio  IN     VARCHAR2,
                                 p_fecha_fin     IN     VARCHAR2,
                                 p_texto_ing     IN     VARCHAR2,
                                 p_texto_esp     IN     VARCHAR2,
                                 p_imagen        IN     BLOB,
                                 p_usuario       IN     VARCHAR2,
                                 p_resultado     OUT    VARCHAR2)
   AS
   BEGIN
  
        IF (p_imagen IS NOT NULL) THEN
  
            INSERT INTO VBT_AVISO (CODIGO, TITULO, TEXTO_ESP, TEXTO_ING, 
                                FECHA_INICIO, FECHA_FIN, POR_DEFECTO, IMAGEN,STATUS, CREADO_POR, 
                                FECHA_CREACION)
              VALUES (NUM_AVISO_SEQ.NEXTVAL,
                     p_titulo,
                     p_texto_esp,
                     p_texto_ing,
                     TO_DATE (p_fecha_inicio, 'DD/MM/YYYY'),
                     TO_DATE (p_fecha_fin, 'DD/MM/YYYY'),
                     p_tipo_aviso,
                     p_imagen,
                     p_estatus,
                     p_usuario,
                     SYSDATE
                    );
        ELSE 
              INSERT INTO VBT_AVISO (CODIGO, TITULO, TEXTO_ESP, TEXTO_ING, 
                                FECHA_INICIO, FECHA_FIN, POR_DEFECTO, STATUS, CREADO_POR, 
                                FECHA_CREACION)
              VALUES (NUM_AVISO_SEQ.NEXTVAL,
                     p_titulo,
                     p_texto_esp,
                     p_texto_ing,
                     TO_DATE (p_fecha_inicio, 'DD/MM/YYYY'),
                     TO_DATE (p_fecha_fin, 'DD/MM/YYYY'),
                     p_tipo_aviso,
                     p_estatus,
                     p_usuario,
                     SYSDATE
                    );        
        END IF;
        

      p_resultado := 'OK';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END BAC_CREAR_AVISO;


 PROCEDURE PR_VALIDAR_FECHA_AVISOS ( I_TIPO_AVISO    IN     VARCHAR2,
                                     I_STATUS_AVISO  IN     VARCHAR2,                                     
                                     I_FEC_INICIAL   IN     VARCHAR2,
                                     I_FEC_FINAL     IN     VARCHAR2,
                                     I_OPERACION     IN     VARCHAR2,                                     
                                     I_CODIGO        IN     VARCHAR2,
                                     O_MENSAJE          OUT VARCHAR2)
   AS
      V_CANT_ACTIVAS   NUMBER;
   BEGIN
      O_MENSAJE := 'OK';
      V_CANT_ACTIVAS := 0;
      
      
        IF (I_OPERACION='edit') THEN
              SELECT COUNT (*)
                 INTO V_CANT_ACTIVAS
                 FROM VBT_AVISO AV
                WHERE AV.STATUS='A' AND AV.POR_DEFECTO<>'S' AND
                          ((   TO_DATE (TO_CHAR (AV.FECHA_INICIO,'dd/mm/yyyy'),'dd/mm/yyyy') <=TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                              AND TO_DATE (TO_CHAR (AV.FECHA_FIN, 'dd/mm/yyyy'),'dd/mm/yyyy') >=TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                              
                              OR (TO_DATE (TO_CHAR (AV.FECHA_FIN, 'dd/mm/yyyy'),'dd/mm/yyyy') >=TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                                  AND TO_DATE (TO_CHAR (AV.FECHA_FIN, 'dd/mm/yyyy'),'dd/mm/yyyy') <=TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                                 )) AND AV.CODIGO<>I_CODIGO;                    
         
        ELSE
              SELECT COUNT (*)
                 INTO V_CANT_ACTIVAS
                 FROM VBT_AVISO AV
                WHERE   AV.STATUS='A' AND AV.POR_DEFECTO<>'S' AND
                          ((   TO_DATE (TO_CHAR (AV.FECHA_INICIO,'dd/mm/yyyy'),'dd/mm/yyyy') <=TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                              AND TO_DATE (TO_CHAR (AV.FECHA_FIN, 'dd/mm/yyyy'),'dd/mm/yyyy') >=TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy'))
                              
                              OR (TO_DATE (TO_CHAR (AV.FECHA_FIN, 'dd/mm/yyyy'),'dd/mm/yyyy') >=TO_DATE (I_FEC_INICIAL, 'dd/mm/yyyy')
                                  AND TO_DATE (TO_CHAR (AV.FECHA_FIN, 'dd/mm/yyyy'),'dd/mm/yyyy') <=TO_DATE (I_FEC_FINAL, 'dd/mm/yyyy')
                                 ));
                  
       
        END IF;
        
      IF ( (V_CANT_ACTIVAS = 0) OR ((V_CANT_ACTIVAS > 0) AND (I_STATUS_AVISO <> 'A')) )
      THEN
        O_MENSAJE := 'OK';
      ELSE 
        O_MENSAJE := 'NO OK';        
      END IF;  
       
   END PR_VALIDAR_FECHA_AVISOS;


 PROCEDURE BAC_EDITAR_AVISO (p_tipo_aviso       IN     VARCHAR2,
                                 p_titulo        IN     VARCHAR2,
                                 p_estatus       IN     VARCHAR2,
                                 p_fecha_inicio  IN     VARCHAR2,
                                 p_fecha_fin     IN     VARCHAR2,
                                 p_texto_ing     IN     VARCHAR2,
                                 p_texto_esp     IN     VARCHAR2,
                                 p_imagen        IN     BLOB,
                                 p_cambioImagen  IN     VARCHAR2,
                                 p_codigo        IN     VARCHAR2,
                                 p_usuario       IN     VARCHAR2,
                                 p_resultado     OUT VARCHAR2)
   AS
   BEGIN
  
        IF (p_tipo_aviso='N') THEN
        
              IF (p_cambioImagen='si') THEN
              
                 UPDATE VBT_AVISO SET  TITULO=p_titulo, TEXTO_ESP=p_texto_esp, TEXTO_ING=p_texto_ing, 
                                    FECHA_INICIO=TO_DATE (p_fecha_inicio, 'DD/MM/YYYY'), FECHA_FIN=TO_DATE (p_fecha_fin, 'DD/MM/YYYY'), IMAGEN=p_imagen, STATUS=p_estatus
                 WHERE CODIGO=p_codigo;
                 
              ELSE
              
                  UPDATE VBT_AVISO SET  TITULO=p_titulo, TEXTO_ESP=p_texto_esp, TEXTO_ING=p_texto_ing, 
                                    FECHA_INICIO=TO_DATE (p_fecha_inicio, 'DD/MM/YYYY'), FECHA_FIN=TO_DATE (p_fecha_fin, 'DD/MM/YYYY'), STATUS=p_estatus
                  WHERE CODIGO=p_codigo; 
                  
              END IF;
              
        ELSE
        
             
           IF (p_cambioImagen='si') THEN
              
                UPDATE VBT_AVISO SET  TITULO=p_titulo, TEXTO_ESP=p_texto_esp, TEXTO_ING=p_texto_ing, 
                                FECHA_INICIO=TO_DATE (p_fecha_inicio, 'DD/MM/YYYY'), FECHA_FIN=TO_DATE (p_fecha_fin, 'DD/MM/YYYY'), IMAGEN=p_imagen
                    WHERE CODIGO=p_codigo;
             
                 
              ELSE
              
                 UPDATE VBT_AVISO SET  TITULO=p_titulo, TEXTO_ESP=p_texto_esp, TEXTO_ING=p_texto_ing, 
                                FECHA_INICIO=TO_DATE (p_fecha_inicio, 'DD/MM/YYYY'), FECHA_FIN=TO_DATE (p_fecha_fin, 'DD/MM/YYYY')
                        WHERE CODIGO=p_codigo;
             
                  
              END IF;    
             
        END IF;


      p_resultado := 'OK';
      COMMIT;
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END BAC_EDITAR_AVISO;

 PROCEDURE BAC_CONSULTAR_CTA_NOPERMITIDA(
                                p_strCmbTipoCodBanco IN VARCHAR2,
                                p_strNumeroCuenta IN VARCHAR2,
                                bac_cuentanopermit OUT  SYS_REFCURSOR, 
                                p_resultado OUT VARCHAR2,
                                p_salida OUT VARCHAR2,
                                p_sqlstring OUT VARCHAR2)  AS                                
                                SQLSTRING VARCHAR2(3500);
                                SQLQUERY  VARCHAR2(1500);
                                WHERESQL VARCHAR2(1500);
    BEGIN
    
    SQLSTRING := 'SELECT DISTINCT I.CD_TITLE NOMBRE_BANCO
                        ,CN.BC_BANK_ACCOUNT 
                        ,CN.BC_BANK_CODDE
                        ,CN.BANK_CODE_TYPE                         
                 FROM INSTITUCION@BANCO_VBTF I,
                     TOB_BANKS_CONF CN
                 WHERE CN.BC_BANK_CODDE = I.ROUTNUM
                        AND I.ROUTNUM IS NOT NULL
                        AND CN.BANK_CODE_TYPE = I.ROUTCODE 
                        AND CN.BC_BANK_ACCOUNT IS NOT NULL
                        AND CN.STATUS = ''A'' ';
                        
    if p_strCmbTipoCodBanco is not null then
        SQLSTRING := SQLSTRING || ' AND CN.BC_BANK_CODDE = '''|| p_strCmbTipoCodBanco ||''' ';                            
    end if;
    
    if p_strNumeroCuenta is not null then
        SQLSTRING := SQLSTRING || ' AND CN.BC_BANK_ACCOUNT = '''|| p_strNumeroCuenta ||''' '; 
    end if;
    
     SQLSTRING := SQLSTRING || 'UNION ALL 
                                SELECT DISTINCT I.CD_TITLE NOMBRE_BANCO
                                        ,CN.BC_BANK_ACCOUNT
                                        ,CN.BC_BANK_CODDE
                                        ,CN.BANK_CODE_TYPE                                      
                                FROM INSTITUCION@BANCO_VBTF I,
                                     TOB_BANKS_CONF CN
                                WHERE CN.BC_BANK_CODDE = I.BIC
                                        AND I.BIC IS NOT NULL 
                                        AND CN.BC_BANK_ACCOUNT IS NOT NULL
                                        AND CN.STATUS = ''A'' ';
    
    if p_strCmbTipoCodBanco is not null then
        SQLSTRING := SQLSTRING || ' AND CN.BC_BANK_CODDE = '''|| p_strCmbTipoCodBanco ||''' ';                            
    end if;
    
    if p_strNumeroCuenta is not null then
        SQLSTRING := SQLSTRING || ' AND CN.BC_BANK_ACCOUNT = '''|| p_strNumeroCuenta ||''' '; 
    end if;
    
    SQLSTRING := SQLSTRING || 'UNION ALL
                                SELECT DISTINCT I.CD_TITLE NOMBRE_BANCO
                                    ,CN.BC_BANK_ACCOUNT
                                    ,CN.BC_BANK_CODDE
                                    ,CN.BANK_CODE_TYPE                                   
                                FROM INSTITUCION@BANCO_VBTF I,
                                     TOB_BANKS_CONF CN
                                WHERE CN.BC_BANK_CODDE = I.CHIPS_UID
                                        AND I.CHIPS_UID IS NOT NULL
                                        AND CN.BC_BANK_ACCOUNT IS NOT NULL        
                                        AND CN.STATUS = ''A'' ';
    
    if p_strCmbTipoCodBanco is not null then
        SQLSTRING := SQLSTRING || ' AND CN.BC_BANK_CODDE = '''|| p_strCmbTipoCodBanco ||''' ';                            
    end if;
    
    if p_strNumeroCuenta is not null then
        SQLSTRING := SQLSTRING || ' AND CN.BC_BANK_ACCOUNT = '''|| p_strNumeroCuenta ||''' '; 
    end if;
    
    p_sqlstring := SQLSTRING;
    OPEN bac_cuentanopermit FOR SQLSTRING; 
                    
    p_resultado:= 'OK';   
   
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);    
                   
    END BAC_CONSULTAR_CTA_NOPERMITIDA;                            
  
                                        
PROCEDURE BAC_INGRESAR_CTA_NO_PERMITIDA(p_strCmbCodBanco IN VARCHAR2,  
                                        p_strCmbNroCuentaBanco IN VARCHAR2,
                                        p_strCmbTipoCodBanco IN VARCHAR2,
                                        p_resultado OUT VARCHAR2)
    AS
    BEGIN
        INSERT INTO TOB_BANKS_CONF (BC_BANK_CODDE, BC_BANK_ACCOUNT,  BANK_CODE_TYPE, STATUS)
        VALUES (p_strCmbCodBanco, p_strCmbNroCuentaBanco,  p_strCmbTipoCodBanco, 'A'); 
       
        p_resultado := 'OK'; 
        
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:= SUBSTR(SQLERRM,1,300);    
    END BAC_INGRESAR_CTA_NO_PERMITIDA;      
    
    PROCEDURE BAC_MODIFICAR_CTANOPERMITIDA (p_strCodBanco IN VARCHAR2,
                                            p_strNroCuenta IN VARCHAR2,
                                            p_resultado OUT VARCHAR2)    AS
    BEGIN
        UPDATE TOB_BANKS_CONF 
        SET STATUS = 'I'
        WHERE BC_BANK_CODDE LIKE p_strCodBanco 
            AND BC_BANK_ACCOUNT LIKE p_strNroCuenta;
        
        p_resultado := 'OK';
           
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:= SUBSTR(SQLERRM,1,300); 
    END BAC_MODIFICAR_CTANOPERMITIDA;                                                                      
END backoffice;
/


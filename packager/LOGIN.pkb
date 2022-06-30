CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.LOGIN AS

 /******************************************************************************
 NAME: BAC_INGRE_P 
 PURPOSE: Query VALIDA USUARIO Y PASSWORD EN BD
 ******************************************************************************/ 
 
 
 PROCEDURE BAC_INGRE_P (P_LOGIN IN VARCHAR2, 
 P_PASSJ IN out VARCHAR2, 
 prespuesta OUT VARCHAR2) as 
 PASSBD VARCHAR2(100);
 LOGINBD VARCHAR2(20);
 pru varchar2(2):='0';
 logeo_estatusBD varchar2(1);
 estatus_cuentaBD varchar2(1);
 
 CURSOR ING IS
 SELECT A.LOGIN,
 A.FECHA_INGRESO,
 A.PASSWORD,
 A.INTENTOS_LOGIN,
 B.CODCIA,
 B.CODSIS
 FROM VBT_USERS A,
 usugrp_v1 b
 WHERE A.LOGIN = B.LOGIN
 AND A.LOGIN = P_LOGIN;

 
 begin
 IF P_LOGIN IS NOT NULL THEN
 -- BEGIN
 begin
 SELECT A.LOGIN LOGIN,
             SEGURIDAD.DESENCRIPTA(A.PASSWORD),
             '1',
             a.logeo_status,
             A.STATUS_CUENTA
            INTO
             LOGINBD, PASSBD, pru , logeo_estatusBD ,estatus_cuentaBD  
             FROM   VBT_USERS A,
              usugrp_v1 b
              WHERE  upper(A.LOGIN) = upper(B.LOGIN)
              AND    upper(A.LOGIN) = upper(p_login);
              --and  SEGURIDAD.DESENCRIPTA(A.PASSWORD) = P_PASSJ; 
          exception    
          when no_data_found then
            prespuesta := 'Usuario no existe';
          end;
               
              
       IF  trim(P_PASSJ) =  trim(PASSBD) THEN
        
          IF estatus_cuentaBD <> 'B' THEN       
               --IF  logeo_estatusBD = 'I'  THEN
                        prespuesta := 'OK';
                         --UPDATE VBT_USERS SET  FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = 0, logeo_status = 'A' 
                         UPDATE VBT_USERS SET  FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = 0
                                    WHERE  upper(LOGIN)= upper(P_LOGIN);
                                    commit; 
              --  ELSE
              --      prespuesta :=  'SESSION DUPLICADA';
              -- END IF;     
          ELSE
                    prespuesta :=  'CUENTA BLOQUEADA';
          END IF;   
      ELSE
         prespuesta :=  'CLAVE INVALIDA';
                 --  prespuesta := 'cadena recibe : ' || P_PASSJ ||' tamaño : '|| length(P_PASSJ) || ' - ' || 'cadena desen : ' || PASSBD || ' tamaño :  ' ||length(PASSBD) || ' clave invalida';
                begin
            
                        FOR N_ING IN ING LOOP
                        
                            IF  (N_ING.INTENTOS_LOGIN +1)  < 3 AND prespuesta = 'CLAVE INVALIDA' THEN 
                            
                            
                                UPDATE VBT_USERS SET last_login = SYSDATE, FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = (N_ING.INTENTOS_LOGIN +1)
                                WHERE  upper(LOGIN)= upper(P_LOGIN);
                                commit;                
                            ELSE 
                            --prespuesta := (N_ING.INTENTOS_LOGIN +1) || ' login : ' || p_login;
                            
                            UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = (0)
                                   ,STATUS_CUENTA = 'B'
                            WHERE upper(LOGIN) = upper(P_LOGIN); 
                             commit;    
                            END IF;     
                        
                        END LOOP;
                            commit;    
                    EXCEPTION
                        WHEN OTHERS THEN
                            prespuesta := 'USUARIO INVALIDO';
                            BEGIN
                            FOR N_ING IN ING LOOP
                                UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = (N_ING.INTENTOS_LOGIN +1),STATUS_CUENTA = 'B'
                                 WHERE upper(LOGIN) <> upper(P_LOGIN); 
                            END LOOP;
                            commit;
                            END;
                    END;      
                --EXCEPTION 
               -- WHEN NO_DATA_FOUND THEN
               --     NULL;
                
             --   END;
             END IF;     
                   
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            prespuesta := 'NO OK';
    END BAC_INGRE_P;  
  PROCEDURE logout_pr (P_LOGIN  IN VARCHAR2,
                       p_respuesta OUT VARCHAR2) as  
  BEGIN                
        UPDATE VBT_USERS SET last_login = SYSDATE, logeo_status = 'I' 
        WHERE  upper(LOGIN)= upper(P_LOGIN);              
                      
        p_respuesta:='OK';
    EXCEPTION
        WHEN OTHERS THEN
            p_respuesta := 'NO OK';
  END logout_pr;

   
    PROCEDURE buscarPassword_pr (P_LOGIN  IN VARCHAR2, 
                                 P_PASSJ OUT VARCHAR2, 
                                 P_RESPUESTA OUT VARCHAR2) as  
                        PASSBD VARCHAR2(100);
                        LOGINBD VARCHAR2(20);
                  
    
                        
    begin
       
       
            SELECT A.LOGIN LOGIN,
             SEGURIDAD.DESENCRIPTA(A.PASSWORD)
            INTO
             LOGINBD, PASSBD  
            FROM   VBT_USERS A
            WHERE upper(A.LOGIN) = upper(p_login);
         
            P_PASSJ := PASSBD; 
            P_RESPUESTA := 'OK';  
    
     
    EXCEPTION
        WHEN OTHERS THEN
            P_RESPUESTA := 'NO OK';
    END buscarPassword_pr;  
    
    /******************************************************************************
   NAME:    login_tagusuarios_pr      
   PURPOSE: Retorna la cadena de texto con el script que genera el menú de opciones.
   ******************************************************************************/
    
   
 PROCEDURE login_tagusuarios_pr (p_login            IN VARCHAR2, 
                                   p_codcia            IN VARCHAR2, 
                                   p_codsis            IN VARCHAR2, 
                                  p_login_tagusuarios OUT login_tagusuarios, 
                                  p_resultado OUT VARCHAR2) AS 
                                cs_login_tagusuarios login_tagusuarios;    
   
   begin
    OPEN cs_login_tagusuarios FOR 
    /*
        SELECT * FROM
                    (SELECT o.codopc codigo,
                     o.desopc descripcion,
                     nvl(o.padcodopc,0) codigo_padre,
                     o.nivel nivel,
                     o.orden  orden,
                     gco.tipacc acciones_group,
                     UG.CODGRP,     
                     decode(UG.CODGRP,'0000000003','TAGTitleBackOffice',
                     '0000000002','TAGTitleBackOffice',
                     '0000000001','TAGTitleBackOffice',
                     'TAGTitleMiPortafolio') mnBackoffice,
                     'TAGTitleInicio' mnInicio,
                     'TAGTitleSalir' mnSalir,
                      mnOpcionAccion,
                      to_number(orden_resultset),
                      CCO.TIPACC acciones_user,
                      o.selectora selectora
                        FROM usugrp_v1 ug,
                             grpciaopc_v1 gco,
                             usrciaopc_v1 cco,
                             ctaopc_v1 o                                                                    
                        WHERE   ug.codsis = UPPER(p_codsis)
                          AND   ug.codcia = UPPER(p_codcia)
                          AND   ug.login =  p_login
                          AND   gco.codsis = ug.codsis
                          AND   gco.codcia = ug.codcia
                          AND   gco.codgrp = ug.codgrp
                          AND   o.codsis = gco.codsis
                          AND   o.codopc = gco.codopc
                          AND   CCO.LOGIN(+)=p_login
                          AND   o.codopc = cco.codopc(+)
                          AND   coalesce(substr(CCO.TIPACC,1,1),substr(gco.tipacc,1,1) )!='0'    
                          AND   (case when nvl(o.padcodopc,0)!=0
                                      then (SELECT coalesce(substr(CCO.TIPACC,1,1),substr(gco.tipacc,1,1))
                                            FROM usugrp_v1 ug,
                                                 grpciaopc_v1 gco,
                                                 usrciaopc_v1 cco,
                                                 ctaopc_v1 o2                                                                    
                                            WHERE   O2.CODOPC=nvl(o.padcodopc,0)
                                              AND   ug.codsis = UPPER(p_codsis)
                                              AND   ug.codcia = UPPER(p_codcia)
                                              AND   ug.login = p_login
                                              AND   gco.codsis = ug.codsis
                                              AND   gco.codcia = ug.codcia
                                              AND   gco.codgrp = ug.codgrp
                                              AND   o2.codsis = gco.codsis
                                              AND   o2.codopc = gco.codopc
                                              AND   CCO.LOGIN(+)=p_login
                                              AND   o2.codopc = cco.codopc(+))
                                      else '1'
                                 end) !='0'
                                )
        WHERE (case when codigo_padre=0 and codigo not in('0000000023','0000000074') and selectora!='B' THEN
                        ( SELECT count(*)
                                FROM usugrp_v1 ug,
                                     grpciaopc_v1 gco,
                                     usrciaopc_v1 cco,
                                     ctaopc_v1 o                                                                    
                                WHERE   ug.codsis = UPPER(p_codsis)
                                  AND   ug.codcia = UPPER(p_codcia)
                                  AND   ug.login =  p_login
                                  AND   gco.codsis = ug.codsis
                                  AND   gco.codcia = ug.codcia
                                  AND   gco.codgrp = ug.codgrp
                                  AND   o.codsis = gco.codsis
                                  AND   o.codopc = gco.codopc
                                  AND   CCO.LOGIN(+)=p_login
                                  AND   o.codopc = cco.codopc(+)
                                  AND   coalesce(substr(CCO.TIPACC,1,1),substr(gco.tipacc,1,1) )!='0'    
                                  AND   (case when nvl(o.padcodopc,0)!=0
                                              then (SELECT coalesce(substr(CCO.TIPACC,1,1),substr(gco.tipacc,1,1))
                                                    FROM usugrp_v1 ug,
                                                         grpciaopc_v1 gco,
                                                         usrciaopc_v1 cco,
                                                         ctaopc_v1 o2                                                                    
                                                    WHERE   O2.CODOPC=nvl(o.padcodopc,0)
                                                      AND   ug.codsis = UPPER(p_codsis)
                                                      AND   ug.codcia = UPPER(p_codcia)
                                                      AND   ug.login =  p_login
                                                      AND   gco.codsis = ug.codsis
                                                      AND   gco.codcia = ug.codcia
                                                      AND   gco.codgrp = ug.codgrp
                                                      AND   o2.codsis = gco.codsis
                                                      AND   o2.codopc = gco.codopc
                                                      AND   CCO.LOGIN(+)=p_login
                                                      AND   o2.codopc = cco.codopc(+))
                                              else '1'
                                         end) !='0'
                                  AND nvl(o.padcodopc,0)=codigo)
               else 100 
               end) > 0
        ORDER BY  12;
*/
SELECT * 
  FROM
  (
    SELECT 
      GCO.CODOPC CODIGO,
      COG.DESOPC DESCRIPCION,
      NVL(COG.PADCODOPC,0) CODIGO_PADRE,
      COG.NIVEL NIVEL,
      COG.ORDEN ORDEN,
      GCO.TIPACC ACCIONES_GRUPO,
      GCO.CODGRP,
      DECODE(GCO.CODGRP,'0000000003','TAGTitleBackOffice','0000000002','TAGTitleBackOffice','0000000001','TAGTitleBackOffice','TAGTitleMiPortafolio') MNBACKOFFICE,
      'TAGTitleInicio' MNINICIO,
      'TAGTitleSalir' MNSALIR,
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
              LOWER(UGG.LOGIN) = LOWER(p_login)
        ) AND
        GCO.CODOPC NOT IN (
          SELECT UCOG.CODOPC
            FROM
              USRCIAOPC_V1 UCOG
            WHERE  
              LOWER(UCOG.LOGIN) = LOWER(p_login)
        ) AND
        COG.CODOPC = GCO.CODOPC AND
        TO_NUMBER(GCO.TIPACC) > 0
    UNION ALL
    SELECT  
      UCO.CODOPC CODIGO,
      COU.DESOPC DESCRIPCION,
      NVL(COU.PADCODOPC,0) CODIGO_PADRE,
      COU.NIVEL NIVEL,
      COU.ORDEN ORDEN,
      NULL ACCIONES_GRUPO,
      UG.CODGRP,
      DECODE(UG.CODGRP,'0000000003','TAGTitleBackOffice','0000000002','TAGTitleBackOffice','0000000001','TAGTitleBackOffice','TAGTitleMiPortafolio') MNBACKOFFICE,
      'TAGTitleInicio' MNINICIO,
      'TAGTitleSalir' MNSALIR,
      COU.MNOPCIONACCION,
      TO_NUMBER(COU.ORDEN_RESULTSET),
      UCO.TIPACC ACCIONES_USER,
      COU.SELECTORA
      FROM
        USRCIAOPC_V1 UCO,
        CTAOPC_V1 COU,
        USUGRP_V1 UG
      WHERE
        LOWER(UCO.LOGIN) = LOWER(p_login) AND
        COU.CODOPC = UCO.CODOPC AND
        LOWER(UG.LOGIN) = LOWER(UCO.LOGIN) AND
        TO_NUMBER(UCO.TIPACC) > 0
  )
  ORDER BY 12  ;  
          
          

       
     p_login_tagusuarios:= cs_login_tagusuarios;     
            p_resultado:= 'OK'; 
             EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
            WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                p_resultado:= SUBSTR(SQLERRM,1,300);    
   end login_tagusuarios_pr;
        
   PROCEDURE OPE_ESPECIALES_P (P_LOGIN  IN VARCHAR2, 
                        P_PASSJ IN VARCHAR2, 
                        prespuesta OUT VARCHAR2) as  
                        PASSBD VARCHAR2(50);
                        LOGINBD VARCHAR2(20);
                        
     CURSOR ING IS
        SELECT A.LOGIN,
               A.FECHA_INGRESO,
               A.PASSWORD_OPE,
               A.INTENTOS_OPE,
               B.CODCIA,
               B.CODSIS
        FROM   VBT_USERS A,
               usugrp_v1 b
        WHERE  UPPER(A.LOGIN) = UPPER(B.LOGIN)
        AND    UPPER(A.LOGIN) = UPPER(P_LOGIN);

                        
    begin
        IF P_LOGIN IS NOT NULL THEN
        --    BEGIN
        
            SELECT A.LOGIN LOGIN,
             SEGURIDAD.DESENCRIPTA(A.PASSWORD_OPE)
            INTO
             LOGINBD, PASSBD    
             FROM   VBT_USERS A,
              usugrp_v1 b
              WHERE  UPPER(A.LOGIN) = UPPER(B.LOGIN)
              AND    UPPER(A.LOGIN) = UPPER(p_login); 
              
         IF  P_PASSJ = PASSBD THEN
                    prespuesta := 'OK';
                     UPDATE VBT_USERS SET INTENTOS_OPE = 0, LAST_LOGIN_OPE = SYSDATE 
                               WHERE  UPPER(LOGIN)= UPPER(P_LOGIN);
                      commit;  
          ELSE
                    prespuesta := 'CLAVE INVALIDA';
                begin
            
                        FOR N_ING IN ING LOOP
                        
                            IF  (N_ING.INTENTOS_OPE +1) < 3 AND prespuesta = 'CLAVE INVALIDA' THEN 
                            
                            
                                UPDATE VBT_USERS SET INTENTOS_OPE = (N_ING.INTENTOS_OPE +1), LAST_LOGIN_OPE = SYSDATE 
                                WHERE  UPPER(LOGIN)= UPPER(P_LOGIN);
                                commit;                
                            ELSE 
                            --prespuesta := (N_ING.INTENTOS_LOGIN +1) || ' login : ' || p_login;
                            
                            UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_OPE = 0
                                 ,STATUS_CUENTA = 'B'
                            WHERE UPPER(LOGIN) = UPPER(P_LOGIN); 

                             commit;    
                            END IF;     
                        
                        END LOOP;
                            commit;    
                    EXCEPTION
                        WHEN OTHERS THEN
                            prespuesta := 'USUARIO INVALIDO';
                            BEGIN
                            FOR N_ING IN ING LOOP
                                UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_OPE = (N_ING.INTENTOS_OPE +1)
                                 WHERE upper(LOGIN) = upper(P_LOGIN); 
                            END LOOP;
                            commit;
                            END;
                    END;      
                --EXCEPTION 
               -- WHEN NO_DATA_FOUND THEN
               --     NULL;
                
             --   END;
             END IF;     
                   
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            prespuesta := 'NO OK';
    END OPE_ESPECIALES_P;  
    
    
    
    PROCEDURE login_updatepass_pr(
                            p_miPasswTemp   IN VARCHAR2,
                            p_login                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2) AS

        BEGIN
        
            
                update vbt_users 
                set password=SEGURIDAD.ENCRIPTA(p_miPasswTemp), CAMBIO_PASS='N' 
                where upper(login)=upper(p_login);
                    p_resultado:='OK';
                    commit;

        EXCEPTION
        WHEN others THEN
        p_resultado:= SUBSTR(SQLERRM,1,300);  
                                    
                            
end login_updatepass_pr;
   
 
PROCEDURE login_updateuser_pr(
                            p_miPassw  IN VARCHAR2,
                            p_login                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2) AS

        BEGIN
        
            
                update vbt_users 
                set PASSWORD_OPE=SEGURIDAD.ENCRIPTA(p_miPassw), CAMBIO_PASS_OPE='N' 
                where upper(login)=upper(p_login);
                    p_resultado:='OK';
                    commit;

        EXCEPTION
        WHEN others THEN
        p_resultado:= SUBSTR(SQLERRM,1,300);  
                                    
                            
end login_updateuser_pr;

PROCEDURE login_asignarPassOpeEsp_pr(
                            p_miPassw  IN VARCHAR2,
                            p_login                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2) AS

        BEGIN
        
            
                update vbt_users 
                set PASSWORD_OPE=SEGURIDAD.ENCRIPTA(p_miPassw), CAMBIO_PASS_OPE='S' 
                where upper(login)=upper(p_login);
                    p_resultado:='OK';
                    commit;

        EXCEPTION
        WHEN others THEN
        p_resultado:= SUBSTR(SQLERRM,1,300);  
                                    
                            
end login_asignarPassOpeEsp_pr;


PROCEDURE verificarPasswordOpeEsp_pr(p_login                IN VARCHAR2,
                                            p_cursorOpeEsp    OUT login_opeEsp,
                                           p_resultado                OUT VARCHAR2) AS
                                    cs_login_opeEsp login_opeEsp; 
        BEGIN
        OPEN cs_login_opeEsp FOR 
         SELECT  login, 
                 PASSWORD_OPE, 
                 INTENTOS_OPE, 
                 CAMBIO_PASS_OPE,
                 LAST_LOGIN_OPE,
                 SEGURIDAD.DESENCRIPTA(PASSWORD_OPE)
                 FROM vbt_users  
                 WHERE upper(login) = upper(p_login);
                 
                 p_cursorOpeEsp:= cs_login_opeEsp;          
                 p_resultado:='OK';
                 
           commit; 
        EXCEPTION
        WHEN others THEN
        p_resultado:= SUBSTR(SQLERRM,1,300);  
                                    
                            
end verificarPasswordOpeEsp_pr;




PROCEDURE login2_pr (P_LOGIN  IN VARCHAR2, 
                     p_login_taglogueo OUT login_taglogueo,
                     p_resultado                OUT VARCHAR2) as  
                        cs_login_taglogueo login_taglogueo;
 begin
    OPEN cs_login_taglogueo FOR                        
   
        SELECT A.LOGIN,
               A.FECHA_INGRESO,
               A.PASSWORD,
               A.INTENTOS_LOGIN,
               A.EMAIL,
               INITCAP(A.NOMBRES)
             
        FROM   VBT_USERS A,
               usugrp_v1 b
        WHERE  A.LOGIN = B.LOGIN
        AND    A.LOGIN = P_LOGIN;
        
            p_login_taglogueo:= cs_login_taglogueo;     
            p_resultado:= 'OK'; 
     EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
     WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
     p_resultado:= SUBSTR(SQLERRM,1,300); 
end login2_pr;   

PROCEDURE permisosUsuarioOpcion_pr (p_login in varchar2,  
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2,  
                                  p_bac_grpuopcper OUT bac_grpuopcper, 
                                  p_resultado OUT VARCHAR2) AS 
    cs_bac_grpuopcper bac_grpuopcper;
    l_grupo VARCHAR2(20);    
    begin
    
    select codgrp into l_grupo FROM USUGRP_v1 WHERE login = p_login;
    
   OPEN cs_bac_grpuopcper FOR 
       SELECT coalesce(CCO.TIPACC ,gco.tipacc) accion, gco.codopc operacion
             FROM      grpciaopc_v1 gco, usrciaopc_v1 cco 
             WHERE  gco.codgrp =  l_grupo  
             AND    gco.codsis = p_strTxtcodsis
             AND    gco.codcia = p_strTxtcodcia
             AND    GCO.CODOPC=CCO.CODOPC(+) 
             AND    cco.login(+)=p_login;  
   
      /*SELECT tipacc accion, codopc operacion
             FROM      grpciaopc 
             WHERE  codgrp = l_grupo  
             AND    codsis = p_strTxtcodsis
             AND    codcia = p_strTxtcodcia ;*/
             
            p_bac_grpuopcper:= cs_bac_grpuopcper;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);         
    
  end permisosUsuarioOpcion_pr; 

  PROCEDURE validarterminoscondiciones_pr (P_CONTRATO  IN VARCHAR2,
                     aceptoterminos             OUT varchar2,
                     p_resultado                OUT VARCHAR2) as 
                               ACEPTO VARCHAR2(100);
 begin
        
        SELECT C.ACEPTO_TRANSFERENCIAS
        INTO 
          ACEPTO
        FROM   VBT_CONTRATO C
        WHERE  C.NUM_CONTRATO = P_CONTRATO;
        
        aceptoterminos := ACEPTO;    
        p_resultado:= 'OK'; 
     EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
     WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
     p_resultado:= SUBSTR(SQLERRM,1,300); 
end validarterminoscondiciones_pr;



  PROCEDURE bloquear_usuario_pr (P_LOGIN  IN VARCHAR2,
                       p_respuesta OUT VARCHAR2) as  
  BEGIN                
        UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = (0)
                                   ,STATUS_CUENTA = 'B'
                            WHERE upper(VBT_USERS.LOGIN) = upper(P_LOGIN) ;              
                      
        p_respuesta:='OK';
    EXCEPTION
        WHEN OTHERS THEN
            p_respuesta := 'NO OK';
  END bloquear_usuario_pr;

           
END LOGIN;
/


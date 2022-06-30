CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.MENU AS
/******************************************************************************
   NAME:       MENU
   PURPOSE:

   REVISIONS:
   Ver        Date        Author           Description
   ---------  ----------  ---------------  ------------------------------------
   1.0        19/07/2012      Lgarrido       1. Created this package body.
******************************************************************************/

  PROCEDURE LOG_1P (P_CODSIS VARCHAR2, P_CODCIA VARCHAR2, P_LOGIN VARCHAR2, param3 OUT CURDATOS, prespuesta OUT VARCHAR2) as
        MENU1  CURDATOS;
            CURSOR ING IS
        SELECT A.LOGIN,
               A.FECHA_INGRESO,
               A.PASSWORD,
               A.INTENTOS_LOGIN,
               B.CODCIA,
               B.CODSIS
        FROM   VBT_USERS A,
               usugrp_v1 b
        WHERE  UPPER(A.LOGIN) = UPPER(B.LOGIN)
        AND    UPPER(A.LOGIN) = UPPER(P_LOGIN);


    begin
        
        FOR N_ING IN ING LOOP
        
            IF  N_ING.INTENTOS_LOGIN < 4 THEN 
                UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = 0
                WHERE  UPPER(N_ING.LOGIN) = UPPER(P_LOGIN);
    
                IF P_CODSIS IS NOT NULL AND P_CODCIA IS NOT NULL AND P_LOGIN IS NOT NULL THEN
                    open MENU1 for SELECT       o.codopc codigo,
                                                 o.desopc descripcion,
                                                 o.padcodopc codigo_padre,
                                                 o.nivel nivel,
                                                 o.orden orden,
                                                 gco.tipacc acciones,
                                                 UG.CODGRP
                                    FROM         usugrp_v1 ug,
                                                 grpciaopc_v1 gco,
                                                 ctaopc_v1 o
                                    WHERE        ug.codsis = UPPER(P_CODSIS)
                                    AND          ug.codcia = UPPER(P_CODCIA)
                                    AND          upper(ug.login) =  upper(P_LOGIN)
                                    AND          gco.codsis = ug.codsis
                                    AND          gco.codcia = ug.codcia
                                    AND          gco.codgrp = ug.codgrp
                                    AND          o.codsis = gco.codsis
                                    AND          o.codopc = gco.codopc
                                    ORDER BY     o.codopc, o.padcodopc, o.orden;
                    param3 := MENU1;
                    prespuesta := 'OK';
                ELSE
                    prespuesta := 'NO OK';
                END IF;
                
            ELSE UPDATE VBT_USERS SET FECHA_LOGIN = SYSDATE, INTENTOS_LOGIN = INTENTOS_LOGIN +1,STATUS_CUENTA = 'B'
                 WHERE UPPER(N_ING.LOGIN) <> UPPER(P_LOGIN); 
                 
            END IF;     
        
        END LOOP;
                
    EXCEPTION
        WHEN OTHERS THEN
            prespuesta := 'NO OK';
    END LOG_1P;
    
    
   PROCEDURE INGRE (P_LOGIN VARCHAR2, param3 OUT CURDATOS1, prespuesta OUT VARCHAR2) as  
        INGRES  CURDATOS1;
    begin
        IF P_LOGIN IS NOT NULL THEN
            open INGRES for SELECT A.LOGIN LOGIN,
                                   A.FECHA_INGRESO FECHA_INGRESO,
                                   SEGURIDAD.DESENCRIPTA(A.PASSWORD) PASSWORD,
                                   A.INTENTOS_LOGIN INTENTOS,
                                   B.CODCIA CODCIA,
                                   B.CODSIS CODSIS
                            FROM   VBT_USERS A,
                                   usugrp_v1 b
                            WHERE  A.LOGIN = B.LOGIN
                            AND    A.LOGIN = p_login;                        
            param3 := INGRES;
            prespuesta := 'OK';
        ELSE
            prespuesta := 'NO OK';
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            prespuesta := 'NO OK';
    END INGRE;
    
    
    PROCEDURE ENCRIPTA_PW (P_PASS VARCHAR2, param3 OUT CURDATOS2, prespuesta OUT VARCHAR2) as
        PASS1  CURDATOS2;
    begin
        IF P_PASS IS NOT NULL THEN
            open PASS1 for SELECT TO_CHAR(P_PASS) FROM DUAL;
            param3 := PASS1;
            prespuesta := 'OK';
        ELSE
            prespuesta := 'NO OK';
        END IF;
    EXCEPTION
        WHEN OTHERS THEN
            prespuesta := 'NO OK';
    END ENCRIPTA_PW;
    
    
    
END MENU;
/


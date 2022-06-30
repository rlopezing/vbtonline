CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.TransferenciaHandler AS

/******************************************************************************
 NAME: num_referencia 
 PURPOSE: Obtener el número de referencia
******************************************************************************/
FUNCTION num_referencia RETURN VARCHAR2
IS
num_refe VARCHAR2(10):= '0';
BEGIN
 
 SELECT lpad(to_char((to_number(valsec)+1)),10,'0') 
 INTO num_refe
 FROM SECUENCIA@BANCO_VBTF 
 WHERE CODEMP = '0000009539' 
 AND CODSEC = '04' 
 FOR UPDATE OF valsec; 
 
 RETURN num_refe;
 EXCEPTION
 WHEN OTHERS THEN
 RETURN '0';
END num_referencia; 


/******************************************************************************
   NAME:    emails_internos_pr       
   PURPOSE: Devuelve correos Internos
******************************************************************************/
PROCEDURE emails_internos_pr (p_codcol in VARCHAR2, p_emails OUT EmailsInternos, p_resultado OUT VARCHAR2) AS 
  cs_emails EmailsInternos;
  V_COUNT NUMBER := 0;
BEGIN

    SELECT COUNT(DISTINCT CTA.CODCAR)
    INTO V_COUNT
    FROM CUENTA CTA, fLUJO_CARTERA_ESPECIAL@REQUISITOS_VBTM E
    WHERE CTA.CODCOL = P_CODCOL
    AND CTA.CODCAR = E.CODCAR;
    
    IF V_COUNT = 0 THEN
        OPEN cs_emails FOR SELECT lower(dir.linea1) email 
                        FROM   cuenta cta 
                                ,cliente_principal cli 
                                ,ctadir dir 
                                ,ctaeje ctaeje 
                            WHERE  cta.codcol = p_codcol 
                            AND    cta.codinst = 'CAH' 
                            AND    cta.codcar = cli.codcar 
                            AND    ((cli.codeje = dir.codpercli and cli.codeje = ctaeje.codpereje and ctaeje.stateje = 'A') 
                            OR     (cli.codneg = dir.codpercli and cli.codneg = ctaeje.codpereje and ctaeje.stateje = 'A')) 
                            AND    dir.TIPODIR = 'E' 
                            AND    (dir.linea1 LIKE '%@VBTBANK%' OR dir.linea1 LIKE '%@VENECREDIT%') 
                            AND    ctaeje.codpereje <> '0000015681'
                        UNION 
                            SELECT lower(ctadir.linea1) email 
                            FROM  ctaeje ctaeje 
                                    ,Ctadir ctadir 
                            WHERE  ctaeje.codpereje = ctadir.codpercli 
                            AND    ctaeje.tipeje = 'T' 
                            AND   ctaeje.stateje = 'A' 
                            AND   ctadir.TIPODIR = 'E' 
                            AND   (ctadir.linea1 LIKE '%@VBTBANK%' OR ctadir.linea1 LIKE '%@VENECREDIT%');
     else
        
        OPEN cs_emails FOR SELECT 'rrodriguez@vbtbank.com' FROM DUAL;          
     END IF;     
       p_emails := cs_emails;                                                                                                                                                                                                                                                                                                                                                                              
                p_resultado:='OK';
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                                                                                 
        p_resultado:= SUBSTR(SQLERRM,1,300);  
       

END emails_internos_pr;




PROCEDURE emails_internos_asesores_pr (p_codcol IN VARCHAR2, p_emails OUT VARCHAR2, p_resultado OUT VARCHAR2) AS 
  cs_emails SYS_REFCURSOR;
  V_EMAIL CTADIR.LINEA1%TYPE;
  V_COUNT NUMBER;
  
BEGIN

        OPEN cs_emails FOR SELECT lower(dir.linea1) email 
                            FROM   cuenta cta 
                                    ,cliente_principal cli 
                                    ,ctadir dir 
                                    ,ctaeje ctaeje 
                                WHERE  cta.codcol = p_codcol 
                                AND    cta.codinst = 'CAH' 
                                AND    cta.codcar = cli.codcar 
                                AND    ((cli.codeje = dir.codpercli and cli.codeje = ctaeje.codpereje and ctaeje.stateje = 'A') 
                                OR     (cli.codneg = dir.codpercli and cli.codneg = ctaeje.codpereje and ctaeje.stateje = 'A')) 
                                AND    dir.TIPODIR = 'E' 
                                AND    (dir.linea1 LIKE '%@VBTBANK%' OR dir.linea1 LIKE '%@VENECREDIT%') 
                                AND    ctaeje.codpereje <> '0000015681'
                            UNION 
                                SELECT lower(ctadir.linea1) email 
                                FROM  ctaeje ctaeje 
                                        ,Ctadir ctadir 
                                WHERE  ctaeje.codpereje = ctadir.codpercli 
                                AND    ctaeje.tipeje = 'T' 
                                AND   ctaeje.stateje = 'A' 
                                AND   ctadir.TIPODIR = 'E' 
                                AND   (ctadir.linea1 LIKE '%@VBTBANK%' OR ctadir.linea1 LIKE '%@VENECREDIT%');
                                
                                
                                
                 LOOP
              
                  FETCH cs_emails INTO V_EMAIL;
                                 
                    EXIT WHEN cs_emails%NOTFOUND; 
                    
                          IF V_EMAIL IS NOT NULL THEN
                            p_emails:=p_emails||V_EMAIL||',';
                          END IF;
                                  
                         
                END LOOP;
              CLOSE cs_emails;           
                                                                                                                                                                                                                                                                                                                                                                            
               p_resultado:='OK';
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                                                                                 
        p_resultado:= SUBSTR(SQLERRM,1,300);  
       

END emails_internos_asesores_pr;



/******************************************************************************
   NAME:    act_secuencia_trans_pr       
   PURPOSE: Actualiza secuencia de referencia de transaccion
******************************************************************************/
PROCEDURE act_secuencia_trans_pr (p_codemp in VARCHAR2, p_codsec in VARCHAR2,  p_resultado OUT VARCHAR2) AS 
  BEGIN

    UPDATE SECUENCIA@BANCO_VBTF 
    SET valsec = (SELECT lpad(to_char((to_number(valsec)+1)),10,'0') 
                   FROM SECUENCIA@BANCO_VBTF WHERE CODEMP = p_codemp AND CODSEC = p_codsec) 
    WHERE CODEMP = p_codemp AND CODSEC = p_codsec;

    p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);   
 END act_secuencia_trans_pr;
 
 
 /******************************************************************************
   NAME:    direc_orig_trans_pr       
   PURPOSE: Obtener La direccion del originador de la transferencia. 
******************************************************************************/
 
 PROCEDURE direc_orig_trans_pr (p_codcardeb in VARCHAR2, p_direccion OUT direccion_transf, p_resultado OUT VARCHAR2) AS 
  cs_direccion direccion_transf;
 
 BEGIN
    /*OPEN cs_direccion FOR SELECT Direccion 
                        FROM  (SELECT DECODE (dir.LINEA1, 
                        NULL,    dir.DIRECC1 
                         || ' ' 
                         || dir.DIRECC2 
                         || ' ' 
                         || dir.DIRECC3, 
                         dir.LINEA1 
                         || ' ' 
                         || dir.LINEA2 
                         || ' ' 
                         || DECODE (dir.NUMPISO, NULL, '', 'PISO ' || dir.NUMPISO) 
                          || ' ' 
                         || DECODE (dir.NUMAPTO, NULL, '', 'APTO ' || dir.NUMAPTO) 
                         || ' ' 
                         || dir.NOMBURB 
                         || ' ' 
                         || dir.CIUDAD 
                         || ' ' 
                         || dir.ESTADO 
                         || ' ' 
                         || dir.codpais 
                         || ' ' 
                         || dir.ZONAP) Direccion 
                        , dir.FLGDIRFISCAL 
                        , dir.FLGDIRENV 
                        , dir.TIPODIR, 
                         decode(DIR.FLGDIRFISCAL,'-1',1,decode(dir.tipodir,'H',2,'O',3,4)) orden 
                        FROM  ctacar car 
                         ,ctadir dir 
                         WHERE  car.codcar = p_codcardeb  
                        AND    car.codpercli = dir.codpercli 
                        AND    dir.tipodir NOT IN ('E','I','A') 
                         AND    upper(replace(dir.linea1,' ','')) NOT LIKE '%HOLDMAIL%' 
                        ORDER BY orden ASC) 
                        WHERE ROWNUM = 1; */
            OPEN cs_direccion 
                FOR   SELECT  DIRECCION,
                                CIUDAD,
                                DECODE (PAIS_INSTI, NULL, CODPAIS, PAIS_INSTI) PAIS,
                                ZONAP,
                                DIRECCION_CORTA
                  FROM (  SELECT DECODE (
                                    DIR.LINEA1,
                                    NULL,    DIR.DIRECC1
                                          || ' '
                                          || DIR.DIRECC2
                                          || ' '
                                          || DIR.DIRECC3,
                                       DIR.LINEA1
                                    || ' '
                                    || DIR.LINEA2
                                    || ' '
                                    || DECODE (DIR.NUMPISO, NULL, '', 'PISO ' || DIR.NUMPISO)
                                    || ' '
                                    || DECODE (DIR.NUMAPTO, NULL, '', 'APTO ' || DIR.NUMAPTO)
                                    || ' '
                                    || DIR.NOMBURB
                                    || ' '
                                    || DIR.CIUDAD
                                    || ' '
                                    || DIR.ESTADO
                                    || ' '
                                    || DIR.CODPAIS
                                    || ' '
                                    || DIR.ZONAP)
                                    DIRECCION,
                                 DIR.FLGDIRFISCAL,
                                 DIR.FLGDIRENV,
                                 DIR.TIPODIR,
                                 DECODE (DIR.FLGDIRFISCAL,
                                         '-1', 1,
                                         DECODE (DIR.TIPODIR,  'H', 2,  'O', 3,  4))
                                    ORDEN,
                                 DIR.CIUDAD,
                                 DIR.CODPAIS,
                                 IP.COUNTRY_CODE PAIS_INSTI,
                                 DIR.ZONAP,
                                 DIR.LINEA1,
                                 NULL,
                                 DIR.DIRECC1 || ' ' || DIR.DIRECC2 || ' ' || DIR.DIRECC3,
                                    DIR.LINEA1
                                 || ' '
                                 || DIR.LINEA2
                                 || ' '
                                 || DECODE (DIR.NUMPISO, NULL, '', 'PISO ' || DIR.NUMPISO)
                                 || ' '
                                 || DECODE (DIR.NUMAPTO, NULL, '', 'APTO ' || DIR.NUMAPTO)
                                 || ' '
                                 || DIR.NOMBURB
                                    DIRECCION_CORTA
                            FROM CTACAR CAR, CTADIR DIR, INSTITUCION_PAIS@BANCO_VBT IP
                           WHERE     CAR.CODCAR = p_codcardeb  
                                 AND CAR.CODPERCLI = DIR.CODPERCLI
                                 AND DIR.TIPODIR NOT IN ('E', 'I', 'A')
                                 AND DIR.CODPAIS = IP.CODPAIS(+)
                                 AND UPPER (REPLACE (DIR.LINEA1, ' ', '')) NOT LIKE
                                        '%HOLDMAIL%'
                        ORDER BY ORDEN ASC)
                             WHERE ROWNUM = 1; 
                       p_direccion := cs_direccion;  
                        p_resultado:='OK';
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                                                                                
        p_resultado:= SUBSTR(SQLERRM,1,300);  
  end direc_orig_trans_pr;
  
 
 /******************************************************************************
   NAME:    orden_transferencia_pr       
   PURPOSE: Inserta regsitros en tabla orden transferencia. 
******************************************************************************/

    PROCEDURE orden_transferencia_pr(P_NUM_CONTRATO          IN VARCHAR2, 
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
        P_SECUENCIA                     OUT VARCHAR2,   
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
        ) AS
        
        V_BIC_BENEFICIARIO              TOB_BANKS_CONF.BC_BANK_CODDE%TYPE;
        V_CODTIPMOV                     MOVIMIENTO_BOFA.CODTIPOMOV@BANCO_VBTF%TYPE; 
        MONEDA_CUENTA               VARCHAR2(20);
        V_TYPE_BENEFICIARY          VARCHAR2(4);
        
        V_CONT                      NUMBER;
        V_CODCAR_ESPECIAL           VARCHAR2(10);
  
  BEGIN
  
      
  
        IF P_NUMBER_SWIFT IS NOT NULL THEN
            BEGIN
            
            
            SELECT DESCRIPCION 
                INTO V_BIC_BENEFICIARIO
            FROM ELEMENTOS_TIPOS
            WHERE CODTIPO='0000000024'
            AND CODELEMENTO='TCB' 
            AND DESCRIPCION = P_NUMBER_SWIFT;
               /* SELECT 
                    TBC.BC_BANK_CODDE
                    INTO
                      V_BIC_BENEFICIARIO
                    FROM
                      TOB_BANKS_CONF TBC
                    WHERE
                      TBC.BC_STATUS = 'V' AND 
                      TBC.BC_MOVEMENT_TYPE = 'TCB' AND
                      TBC.BC_BANK_CODDE = P_NUMBER_SWIFT 
                  ;*/
                  
                  EXCEPTION
                    WHEN OTHERS THEN
                      V_BIC_BENEFICIARIO := '';
                    
            END;
      
      
            IF (V_BIC_BENEFICIARIO = P_NUMBER_SWIFT) THEN
               V_CODTIPMOV := 'TCB';
            ELSE
                V_CODTIPMOV:=P_CODTIPOMOV;
            END IF;
        ELSE
            BEGIN
              
        SELECT DESCRIPCION 
                INTO V_BIC_BENEFICIARIO
            FROM ELEMENTOS_TIPOS
            WHERE CODTIPO='0000000024'
            AND CODELEMENTO='TCB' 
            AND DESCRIPCION = P_BENEFICIARY_BANK_TYPE_NUMBER;
               /* SELECT 
                    TBC.BC_BANK_CODDE
                    INTO
                      V_BIC_BENEFICIARIO
                    FROM
                      TOB_BANKS_CONF TBC
                    WHERE
                      TBC.BC_STATUS = 'V' AND 
                      TBC.BC_MOVEMENT_TYPE = 'TCB' AND
                      TBC.BC_BANK_CODDE = P_BENEFICIARY_BANK_TYPE_NUMBER 
                  ;*/
                  
                  EXCEPTION
                    WHEN OTHERS THEN
                      V_BIC_BENEFICIARIO := '';
                    
            END;
      
      
            IF (V_BIC_BENEFICIARIO = P_BENEFICIARY_BANK_TYPE_NUMBER) THEN
               V_CODTIPMOV := 'TCB';
            ELSE
                V_CODTIPMOV:=P_CODTIPOMOV;
            END IF;
        END IF;
  
  
            BEGIN
                SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_BOF00_CODCOL;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:='USD'; 
                END;
  
  
  
    IF P_ORIGEN_TEMPLATE='TMP' THEN
    
             BEGIN
                    SELECT 
                        NVL(BENEFICIARY_TYPE_PERSON,'0')
                        INTO
                          V_TYPE_BENEFICIARY
                        FROM
                         VBT_DIRECTORIO TMP
                        WHERE
                         TMP.NUM_CONTRATO=P_NUM_CONTRATO
                         AND TMP.CODIGO=P_ID_TEMPLATE;
                      
                EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   V_TYPE_BENEFICIARY:='0'; 
                END;
  
                          
                             
             
                 
      
       IF (V_TYPE_BENEFICIARY='0') THEN
        


                UPDATE VBT_DIRECTORIO SET BENEFICIARY_TYPE_PERSON=P_BENEFICIARY_TYPE_PERSON,
                    BENEFICIARY_LASTNAME1=P_BENEFICIARY_LASTNAME1, 
                    BENEFICIARY_LASTNAME2=P_BENEFICIARY_LASTNAME2,
                    BENEFICIARY_CITY=P_BENEFICIARY_CITY
                WHERE  NUM_CONTRATO=P_NUM_CONTRATO
                       AND CODIGO=P_ID_TEMPLATE;

            
       END IF;
            
    
    
    END IF;
  
        SELECT COUNT(C.CODCAR)
        INTO V_CONT 
        FROM FLUJO_CARTERA_ESPECIAL@REQUISITOS_VBTM C
        WHERE CODCAR = P_CODCAR;
            
        IF V_CONT > 0 THEN
            V_CODCAR_ESPECIAL := 'S'; 
        ELSE
            V_CODCAR_ESPECIAL := 'N';
        END IF;
  
  

        SELECT NUM_TRANSFERENCIA_SEQ.NEXTVAL
        INTO  P_SECUENCIA
        FROM DUAL; 

  
           IF P_TIPOUSUARIO!='FC' THEN
                --Firmas Indistintas
                INSERT INTO ORDEN_TRANSFERENCIA ( 
                NUM_INSTRUCCION, 
                NUM_CONTRATO, 
                CODCAR, 
                ESTATUS_INSTRUCCION, 
                FECHA_ESTATUS, 
                BOF00_CODMOV, 
                BOF00_CODEMP, 
                BOF00_CODCOL, 
                BOF00_CODINST, 
                CODTIPOMOV,    
                BOF02_AS_OF_DATE, 
                BOF02_AS_OF_TIME, 
                BOF16_AMOUNT, 
                REFBANMOV, 
                BOF03_CURRENCY_CODE, 
                BENEFICIARIO, 
                OBSERV, 
                CODEMP_ORIGEN, 
                CODCAR_ORIGEN, 
                USRID_LIBERA, 
                FECHA_LIBERA, 
                SOURCE, 
                STATWS, 
                EMAIL_BENEFICIARIO, 
                EMAIL_ORIGEN, 
                BOF16_BANK_REFERENCE_NUMBER, 
                BOF00_CODCAR, 
                BENEFICIARY_TYPE, 
                BENEFICIARY_TYPE_NUMBER, 
                BENEFICIARY_DESCRIPTION, 
                TELEFONO_BENEFICIARIO, 
                BENEFICIARY_BANK_TYPE, 
                BENEFICIARY_BANK_TYPE_NUMBER, 
                BENEFICIARY_BANK_DESCRIPTION, 
                INTERMEDIARY_BANK_TYPE, 
                INTERMEDIARY_BANK_TYPE_NUMBER, 
                INTERMEDIARY_BANK_DESCRIPTION, 
                ORIGINATORS_INFO, 
                CHARGE_TO, 
                CODTIPOMOV_BOFA, 
                CODEMP, 
                CODCOL, 
                CODINST, 
                BENEFICIARY_NAME, 
                BENEFICIARY_BANK_NAME, 
                INTERMEDIARY_BANK_NAME, 
                ORIGINATORS_NAME, 
                DETAILS_OF_PAYMENT_NAME, 
                DETAILS_OF_PAYMENT_DESCRIPTION, 
                CODMON_CREDIT, 
                FFC_NUMBER, 
                FFC_NAME,
                MOTIVO,
                BENEFICIARY_BANK_TYPE_SWIFT,
                beneficiary_bank_tnum_swift,
                intermediary_bank_type_swift,
                intermediary_bank_tnum_swift,
                ORIGINATORS_ADDRESS,
                ORIGINATORS_CITY,
                ORIGINATORS_COUNTRY,
                ORIGINATORS_POSTAL_CODE,
                BENEFICIARY_ADDRESS, 
                BENEFICIARY_CITY,
                BENEFICIARY_COUNTRY,
                BENEFICIARY_POSTAL_CODE,   
                BENEFICIARY_BANK_ADDRESS,
                BENEFICIARY_BANK_CITY,
                BENEFICIARY_BANK_COUNTRY, 
                INTERMEDIARY_BANK_ADDRESS,
                INTERMEDIARY_BANK_CITY,
                INTERMEDIARY_BANK_COUNTRY,
                BENEFICIARY_TYPE_PERSON,
                CODCAR_ESPECIAL
                ) 
                VALUES (
                P_SECUENCIA, 
                P_NUM_CONTRATO, 
                P_CODCAR, 
                P_ESTATUS_INSTRUCCION,
                sysdate, 
                P_BOF00_CODMOV, 
                P_BOF00_CODEMP, 
                P_BOF00_CODCOL, 
                P_BOF00_CODINST, 
                P_CODTIPOMOV,    
                sysdate, 
                to_char(sysdate, 'hh24mi'), 
                abs(P_BOF16_AMOUNT)*-1, 
                P_REFBANMOV, 
                --P_BOF03_CURRENCY_CODE,
                MONEDA_CUENTA, 
                P_BENEFICIARIO, 
                P_OBSERV, 
                P_CODEMP_ORIGEN, 
                P_CODCAR_ORIGEN, 
                P_USRID_LIBERA, 
                sysdate, 
                P_SOURCE, 
                'V',
                P_EMAIL_BENEFICIARIO, 
                P_EMAIL_ORIGEN, 
                P_BOF16_BANK_REFERENCE_NUMBER, 
                P_BOF00_CODCAR, 
                P_BENEFICIARY_TYPE, 
                P_BENEFICIARY_TYPE_NUMBER, 
                P_BENEFICIARY_DESCRIPTION, 
                P_TELEFONO_BENEFICIARIO, 
                P_BENEFICIARY_BANK_TYPE, 
                P_BENEFICIARY_BANK_TYPE_NUMBER, 
                P_BENEFICIARY_BANK_DESCRIPTION, 
                P_INTERMEDIARY_BANK_TYPE, 
                P_INTERMEDIARY_BANK_TYPE_NUM, 
                P_INTERMEDIARY_BANK_DESCRIPT, 
                P_ORIGINATORS_INFO, 
                'OUR', 
                V_CODTIPMOV, 
                P_CODEMP, 
                P_CODCOL, 
                P_CODINST, 
                P_BENEFICIARY_NAME, 
                P_BENEFICIARY_BANK_NAME, 
                P_INTERMEDIARY_BANK_NAME, 
                P_ORIGINATORS_NAME, 
                '', --P_DETAILS_OF_PAYMENT_NAME
                P_DETAILS_OF_PAYMENT_DESCRIPT, 
                P_CODMON_CREDIT, 
                P_FFC_NUMBER, 
                P_FFC_NAME,
                P_MOTIVO,
                P_TYPE_SWIFT,
                P_NUMBER_SWIFT,
                P_IB_SWIFT,
                P_IB_NUMBER_SWIFT,
                P_ORIGINATORS_ADDRESS,
                P_ORIGINATORS_CITY, 
                P_ORIGINATORS_COUNTRY,
                P_ORIGINATORS_POSTAL_CODE,
                P_BENEFICIARY_ADDRESS,
                P_BENEFICIARY_CITY,
                P_BENEFICIARY_COUNTRY,
                P_BENEFICIARY_POSTAL_CODE,  
                P_BENEFICIARY_BANK_ADDRESS,
                P_BENEFICIARY_BANK_CITY,
                P_BENEFICIARY_BANK_COUNTRY, 
                P_INTERMEDIARY_BANK_ADDRESS,
                P_INTERMEDIARY_BANK_CITY,
                P_INTERMEDIARY_BANK_COUNTRY,
                P_BENEFICIARY_TYPE_PERSON,
                V_CODCAR_ESPECIAL);
            ELSE
                 --Firmas Conjuntas
                 INSERT INTO ORDEN_TRANSFERENCIA ( 
                    NUM_INSTRUCCION, 
                    NUM_CONTRATO, 
                    CODCAR, 
                    ESTATUS_INSTRUCCION, 
                    FECHA_ESTATUS, 
                    BOF00_CODMOV, 
                    BOF00_CODEMP, 
                    BOF00_CODCOL, 
                    BOF00_CODINST, 
                    CODTIPOMOV,    
                    BOF02_AS_OF_DATE, 
                    BOF02_AS_OF_TIME, 
                    BOF16_AMOUNT, 
                    REFBANMOV, 
                    BOF03_CURRENCY_CODE, 
                    BENEFICIARIO, 
                    OBSERV, 
                    CODEMP_ORIGEN, 
                    CODCAR_ORIGEN, 
                    USRID_CARGA, 
                    FECHA_CARGA, 
                    SOURCE, 
                    STATWS, 
                    EMAIL_BENEFICIARIO, 
                    EMAIL_ORIGEN, 
                    BOF16_BANK_REFERENCE_NUMBER, 
                    BOF00_CODCAR, 
                    BENEFICIARY_TYPE, 
                    BENEFICIARY_TYPE_NUMBER, 
                    BENEFICIARY_DESCRIPTION, 
                    TELEFONO_BENEFICIARIO, 
                    BENEFICIARY_BANK_TYPE, 
                    BENEFICIARY_BANK_TYPE_NUMBER, 
                    BENEFICIARY_BANK_DESCRIPTION, 
                    INTERMEDIARY_BANK_TYPE, 
                    INTERMEDIARY_BANK_TYPE_NUMBER, 
                    INTERMEDIARY_BANK_DESCRIPTION, 
                    ORIGINATORS_INFO, 
                    CHARGE_TO, 
                    CODTIPOMOV_BOFA, 
                    CODEMP, 
                    CODCOL, 
                    CODINST, 
                    BENEFICIARY_NAME, 
                    BENEFICIARY_BANK_NAME, 
                    INTERMEDIARY_BANK_NAME, 
                    ORIGINATORS_NAME, 
                    DETAILS_OF_PAYMENT_NAME, 
                    DETAILS_OF_PAYMENT_DESCRIPTION, 
                    CODMON_CREDIT, 
                    FFC_NUMBER, 
                    FFC_NAME,
                    MOTIVO,
                    BENEFICIARY_BANK_TYPE_SWIFT,
                    beneficiary_bank_tnum_swift,
                    intermediary_bank_type_swift,
                    intermediary_bank_tnum_swift,
                    ORIGINATORS_ADDRESS,
                    ORIGINATORS_CITY,
                    ORIGINATORS_COUNTRY,
                    ORIGINATORS_POSTAL_CODE,
                    BENEFICIARY_ADDRESS, 
                    BENEFICIARY_CITY,
                    BENEFICIARY_COUNTRY,
                    BENEFICIARY_POSTAL_CODE,   
                    BENEFICIARY_BANK_ADDRESS,
                    BENEFICIARY_BANK_CITY,
                    BENEFICIARY_BANK_COUNTRY, 
                    INTERMEDIARY_BANK_ADDRESS,
                    INTERMEDIARY_BANK_CITY,
                    INTERMEDIARY_BANK_COUNTRY,
                    BENEFICIARY_TYPE_PERSON,
                    CODCAR_ESPECIAL
                    ) 
                    VALUES (
                    P_SECUENCIA, 
                    P_NUM_CONTRATO, 
                    P_CODCAR, 
                    P_ESTATUS_INSTRUCCION,
                    sysdate, 
                    P_BOF00_CODMOV, 
                    P_BOF00_CODEMP, 
                    P_BOF00_CODCOL, 
                    P_BOF00_CODINST, 
                    P_CODTIPOMOV,    
                    sysdate, 
                    to_char(sysdate, 'hh24mi'), 
                    abs(P_BOF16_AMOUNT)*-1, 
                    P_REFBANMOV, 
                    --P_BOF03_CURRENCY_CODE,
                    MONEDA_CUENTA, 
                    P_BENEFICIARIO, 
                    P_OBSERV, 
                    P_CODEMP_ORIGEN, 
                    P_CODCAR_ORIGEN, 
                    P_USRID_LIBERA, 
                    sysdate, 
                    P_SOURCE, 
                    'V',
                    P_EMAIL_BENEFICIARIO, 
                    P_EMAIL_ORIGEN, 
                    P_BOF16_BANK_REFERENCE_NUMBER, 
                    P_BOF00_CODCAR, 
                    P_BENEFICIARY_TYPE, 
                    P_BENEFICIARY_TYPE_NUMBER, 
                    P_BENEFICIARY_DESCRIPTION, 
                    P_TELEFONO_BENEFICIARIO, 
                    P_BENEFICIARY_BANK_TYPE, 
                    P_BENEFICIARY_BANK_TYPE_NUMBER, 
                    P_BENEFICIARY_BANK_DESCRIPTION, 
                    P_INTERMEDIARY_BANK_TYPE, 
                    P_INTERMEDIARY_BANK_TYPE_NUM, 
                    P_INTERMEDIARY_BANK_DESCRIPT, 
                    P_ORIGINATORS_INFO, 
                    'OUR', 
                    V_CODTIPMOV, 
                    P_CODEMP, 
                    P_CODCOL, 
                    P_CODINST, 
                    P_BENEFICIARY_NAME, 
                    P_BENEFICIARY_BANK_NAME, 
                    P_INTERMEDIARY_BANK_NAME, 
                    P_ORIGINATORS_NAME, 
                    '', --P_DETAILS_OF_PAYMENT_NAME 
                    P_DETAILS_OF_PAYMENT_DESCRIPT, 
                    P_CODMON_CREDIT, 
                    P_FFC_NUMBER, 
                    P_FFC_NAME,
                    P_MOTIVO,
                    P_TYPE_SWIFT,
                    P_NUMBER_SWIFT,
                    P_IB_SWIFT,
                    P_IB_NUMBER_SWIFT,
                    P_ORIGINATORS_ADDRESS,
                    P_ORIGINATORS_CITY, 
                    P_ORIGINATORS_COUNTRY,
                    P_ORIGINATORS_POSTAL_CODE,
                    P_BENEFICIARY_ADDRESS,
                    P_BENEFICIARY_CITY,
                    P_BENEFICIARY_COUNTRY,
                    P_BENEFICIARY_POSTAL_CODE,  
                    P_BENEFICIARY_BANK_ADDRESS,
                    P_BENEFICIARY_BANK_CITY,
                    P_BENEFICIARY_BANK_COUNTRY, 
                    P_INTERMEDIARY_BANK_ADDRESS,
                    P_INTERMEDIARY_BANK_CITY,
                    P_INTERMEDIARY_BANK_COUNTRY,
                    P_BENEFICIARY_TYPE_PERSON,
                    V_CODCAR_ESPECIAL); 
            END IF;    
            --COMMI
                                                                                                                                                                                                                                                                                                                                                                                                         
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   orden_transferencia_pr;

/******************************************************************************
   NAME:    orden_transfe_externa_pr      
   PURPOSE: Guarda la orden de transferencia externa en MOVIMIENTO_BOFA@BANCO_VBTF
******************************************************************************/

  PROCEDURE orden_transfe_externa_pr(
                    P_BOF00_CODMOV                  IN VARCHAR2, 
                    P_BOF00_CODEMP                  IN VARCHAR2, 
                    P_BOF00_CODCOL                  IN VARCHAR2, 
                    P_BOF00_CODINST                 IN VARCHAR2, 
                    P_CODTIPOMOV                    IN VARCHAR2,
                    P_BOF16_AMOUNT                  IN NUMBER, 
                    P_REFBANMOV                     IN VARCHAR2, 
                    P_BOF03_CURRENCY_CODE           IN VARCHAR2, 
                    P_BENEFICIARIO                  IN VARCHAR2, 
                    P_OBSERV                        IN VARCHAR2, 
                    P_CODEMP_ORIGEN                 IN VARCHAR2, 
                    P_CODCAR_ORIGEN                 IN VARCHAR2, 
                    P_SOURCE                        IN VARCHAR2,
                    P_BOF16_BANK_REFERENCE_NUMBER   IN VARCHAR2, 
                    P_BOF00_CODCAR                  IN VARCHAR2, 
                    P_BENEFICIARY_TYPE              IN VARCHAR2, 
                    P_BENEFICIARY_TYPE_NUMBER       IN VARCHAR2, 
                    P_BENEFICIARY_DESCRIPTION       IN VARCHAR2, 
                    P_BENEFICIARY_BANK_TYPE         IN VARCHAR2, 
                    P_BENEFICIARY_BANK_TYPE_NUMBER  IN VARCHAR2, 
                    P_BENEFICIARY_BANK_DESCRIPTION  IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_TYPE        IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_TYPE_NUMB   IN VARCHAR2, 
                    P_INTERMEDIARY_BANK_DESCRIPTI   IN VARCHAR2, 
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
                    P_DETAILS_OF_PAYMENT_DESCRIPTI  IN VARCHAR2, 
                    P_CODMON_CREDIT                 IN VARCHAR2, 
                    P_BOF01_CODMOV                  IN VARCHAR2, 
                    P_RECEIVER_INFO                 IN VARCHAR2, 
                    P_EXOCOM                        IN VARCHAR2,
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
                    P_INTERMEDIARY_BANK_COUNTRY     IN VARCHAR2
                    ) AS 
                    
                    
                    V_REFBANMOV                     varchar2(100) :='';
                    
                    V_TCB                           NUMBER := 0;
                    V_BIC_BENEFICIARIO              TOB_BANKS_CONF.BC_BANK_CODDE%TYPE;
                    V_BIC_BEN_BOFA                  INSTITUCION.BIC@BANCO_VBTF%TYPE;
                    
                    V_BENEFICIARY_ADDRESS           MOVIMIENTO_BOFA.BENEFICIARY_ADDRESS@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_CITY              MOVIMIENTO_BOFA.BENEFICIARY_CITY@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_COUNTRY           MOVIMIENTO_BOFA.BENEFICIARY_COUNTRY@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_POSTAL_CODE       MOVIMIENTO_BOFA.BENEFICIARY_POSTAL_CODE@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_DESCRIPTION       MOVIMIENTO_BOFA.BENEFICIARY_DESCRIPTION@BANCO_VBTF%TYPE;
                    
                    V_DETAILS_OF_PAYMENT_NAME       MOVIMIENTO_BOFA.DETAILS_OF_PAYMENT_NAME@BANCO_VBTF%TYPE;
                    
                    V_BENEFICIARY_BANK_TYPE         MOVIMIENTO_BOFA.BENEFICIARY_BANK_TYPE@BANCO_VBTF%TYPE := 'SA';
                    V_BENEFICIARY_BANK_TYPE_NUMBER  MOVIMIENTO_BOFA.BENEFICIARY_BANK_TYPE_NUMBER@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_DESCRIPTION  MOVIMIENTO_BOFA.BENEFICIARY_DESCRIPTION@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_NAME         MOVIMIENTO_BOFA.BENEFICIARY_BANK_NAME@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_ADDRESS      MOVIMIENTO_BOFA.BENEFICIARY_BANK_ADDRESS@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_CITY         MOVIMIENTO_BOFA.BENEFICIARY_BANK_CITY@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_COUNTRY      MOVIMIENTO_BOFA.BENEFICIARY_BANK_COUNTRY@BANCO_VBTF%TYPE;
                    
                    V_INT_BANK_TYPE                 MOVIMIENTO_BOFA.INTERMEDIARY_BANK_TYPE@BANCO_VBTF%TYPE;
                    V_INT_BANK_TYPE_NUMB            MOVIMIENTO_BOFA.INTERMEDIARY_BANK_TYPE_NUMBER@BANCO_VBTF%TYPE;
                    V_INT_BANK_DESCRIPTION          MOVIMIENTO_BOFA.INTERMEDIARY_BANK_DESCRIPTION@BANCO_VBTF%TYPE;
                    V_INT_BANK_NAME                 MOVIMIENTO_BOFA.INTERMEDIARY_BANK_NAME@BANCO_VBTF%TYPE;
                    V_INT_BANK_ADDRESS              MOVIMIENTO_BOFA.INTERMEDIARY_BANK_ADDRESS@BANCO_VBTF%TYPE;
                    V_INT_BANK_CITY                 MOVIMIENTO_BOFA.INTERMEDIARY_BANK_CITY@BANCO_VBTF%TYPE;
                    V_INT_BANK_COUNTRY              MOVIMIENTO_BOFA.INTERMEDIARY_BANK_COUNTRY@BANCO_VBTF%TYPE;
                    
                    V_CODTIPMOV                     MOVIMIENTO_BOFA.CODTIPOMOV@BANCO_VBTF%TYPE;
                    V_CODTIPOMOV_BOFA               MOVIMIENTO_BOFA.CODTIPOMOV_BOFA@BANCO_VBTF%TYPE;
                    MONEDA_CUENTA                   VARCHAR2(20);
                    
    BEGIN            
            
        SELECT COUNT (BOF00_CODCOL) + 1
            INTO V_REFBANMOV
            FROM MOVIMIENTO_BOFA@BANCO_VBTF 
            WHERE     CODTIPOMOV = 'TEO'
                  AND SOURCE = 'ONL'
                  AND TRUNC (BOF02_AS_OF_DATE) = TRUNC (SYSDATE)
                  AND BOF00_CODCAR=P_BOF00_CODCAR AND BOF00_CODCOL= P_BOF00_CODCOL;
                  
        V_REFBANMOV := 'CT'||TO_NUMBER(P_BOF00_CODCAR)||'CC'||TO_NUMBER(P_BOF00_CODCOL) || 'O'||V_REFBANMOV;

        BEGIN
      SELECT DESCRIPCION 
                INTO V_BIC_BENEFICIARIO
            FROM ELEMENTOS_TIPOS
            WHERE CODTIPO='0000000024'
            AND CODELEMENTO='TCB' 
            AND DESCRIPCION = P_BENEFICIARY_BANK_TYPE_NUMBER;
          /*SELECT 
            TBC.BC_BANK_CODDE
            INTO
              V_BIC_BENEFICIARIO
            FROM
              TOB_BANKS_CONF TBC
            WHERE
              TBC.BC_STATUS = 'V' AND 
              TBC.BC_MOVEMENT_TYPE = 'TCB' AND
              TBC.BC_BANK_CODDE = P_BENEFICIARY_BANK_TYPE_NUMBER
          ;*/
          
          EXCEPTION
            WHEN OTHERS THEN
              V_BIC_BENEFICIARIO := '';
            
        END;
        
        BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_BOF00_CODCOL;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:='USD'; 
                END;
  
        
        

        IF (V_BIC_BENEFICIARIO = P_BENEFICIARY_BANK_TYPE_NUMBER) THEN
          
          SELECT 
            TI.BIC BANK_TYPE_NUMBER,
            SUBSTR(TI.CD_TITLE,1,150),-- BANK_DESCRIPTION,
            SUBSTR(TI.CD_TITLE,1,35),-- BANK_NAME,
            TI.STATE BANK_ADDRESS,
            TI.CITY BANK_CITY,
            TI.COUNTRY_CODE BANK_COUNTRY
            INTO
              V_BENEFICIARY_BANK_TYPE_NUMBER,
              V_BENEFICIARY_BANK_DESCRIPTION,
              V_BENEFICIARY_BANK_NAME,
              V_BENEFICIARY_BANK_ADDRESS,
              V_BENEFICIARY_BANK_CITY,
              V_BENEFICIARY_BANK_COUNTRY
            FROM 
              INSTITUCION@BANCO_VBTF TI
            WHERE
              TI.BIC = (
                SELECT 
                  TPE.VALOR 
                  FROM 
                    PRODUCTO_EMPRESA@BANCO_VBTF TPE
                  WHERE
                    TPE.CODPRODINT = '0000000041' AND
                    TPE.CODEMP = '0000009539'
              )
          ;
       
          V_INT_BANK_TYPE := '';
          V_INT_BANK_TYPE_NUMB := '';
          V_INT_BANK_DESCRIPTION := '';
          V_INT_BANK_NAME := '';
          V_INT_BANK_ADDRESS := '';
          V_INT_BANK_CITY := '';
          V_INT_BANK_COUNTRY := '';

          V_CODTIPOMOV_BOFA := 'TCB';

       SELECT  
            TCD.CIUDAD BENEFICIARY_CITY,
            TCD.ZONAP BENEFICIARY_POSTAL_CODE,
            (TCD.NUMPISO || ' FLOOR ' || DECODE(TCD.NUMAPTO,NULL,'',' SUITE '|| TCD.NUMAPTO)
             || ' ' || TCD.LINEA1 || ' ' || TCD.LINEA2) BENEFICIARY_ADDRESS,
            TIP.COUNTRY_CODE BENEFICIARY_COUNTRY
            INTO
              V_BENEFICIARY_CITY,
              V_BENEFICIARY_POSTAL_CODE,
              V_BENEFICIARY_ADDRESS,
              V_BENEFICIARY_COUNTRY
            FROM 
              CTADIR TCD,
              INSTITUCION_PAIS@BANCO_VBTF TIP
            WHERE
              TCD.CODPERCLI = '0000009539' AND
              TCD.TIPODIR = 'O' AND
              TIP.CODPAIS = TCD.CODPAIS
              AND TCD.FLGDIRFISCAL = -1;
          
          V_CODTIPMOV := 'TCB';
          
          V_DETAILS_OF_PAYMENT_NAME := 'PAY BY CHECK #' || P_BENEFICIARY_TYPE_NUMBER;
          V_BENEFICIARY_DESCRIPTION := V_BENEFICIARY_ADDRESS;

        ELSE
        
          V_BENEFICIARY_BANK_TYPE := P_BENEFICIARY_BANK_TYPE;
          V_BENEFICIARY_BANK_TYPE_NUMBER := P_BENEFICIARY_BANK_TYPE_NUMBER;
          V_BENEFICIARY_BANK_DESCRIPTION := P_BENEFICIARY_BANK_DESCRIPTION;
          V_BENEFICIARY_BANK_NAME := P_BENEFICIARY_BANK_NAME;
          V_BENEFICIARY_BANK_ADDRESS := P_BENEFICIARY_BANK_ADDRESS;
          V_BENEFICIARY_BANK_CITY := P_BENEFICIARY_BANK_CITY;
          V_BENEFICIARY_BANK_COUNTRY := P_BENEFICIARY_BANK_COUNTRY;
          
          V_INT_BANK_TYPE := P_INTERMEDIARY_BANK_TYPE;
          V_INT_BANK_TYPE_NUMB := P_INTERMEDIARY_BANK_TYPE_NUMB;
          V_INT_BANK_DESCRIPTION := P_INTERMEDIARY_BANK_DESCRIPTI;
          V_INT_BANK_NAME := P_INTERMEDIARY_BANK_NAME;
          V_INT_BANK_ADDRESS := P_INTERMEDIARY_BANK_ADDRESS;
          V_INT_BANK_CITY := P_INTERMEDIARY_BANK_CITY;
          V_INT_BANK_COUNTRY := P_INTERMEDIARY_BANK_COUNTRY;
          
          V_CODTIPOMOV_BOFA := P_CODTIPOMOV_BOFA;

          V_BENEFICIARY_ADDRESS := P_BENEFICIARY_ADDRESS;
          V_BENEFICIARY_CITY := P_BENEFICIARY_CITY;
          V_BENEFICIARY_COUNTRY := P_BENEFICIARY_COUNTRY;
          V_BENEFICIARY_POSTAL_CODE := P_BENEFICIARY_POSTAL_CODE;
          
          V_DETAILS_OF_PAYMENT_NAME := '';
          V_CODTIPMOV := P_CODTIPOMOV;
          V_BENEFICIARY_DESCRIPTION := P_BENEFICIARY_DESCRIPTION;
          
        END IF;
        
        INSERT INTO MOVIMIENTO_BOFA@BANCO_VBTF ( 
          BOF00_CODMOV, 
          BOF00_CODEMP, 
          BOF00_CODCOL, 
          BOF00_CODINST, 
          CODTIPOMOV, 
          BOF02_AS_OF_DATE, 
          BOF02_AS_OF_TIME, 
          BOF16_AMOUNT, 
          REFBANMOV, 
          BOF03_CURRENCY_CODE, 
          BENEFICIARIO, 
          OBSERV, 
          CODEMP_ORIGEN, 
          CODCAR_ORIGEN, 
          SOURCE, 
          STATWS, 
          BOF16_BANK_REFERENCE_NUMBER, 
          BOF00_CODCAR, 
          BENEFICIARY_TYPE, 
          BENEFICIARY_TYPE_NUMBER, 
          BENEFICIARY_DESCRIPTION, 
          BENEFICIARY_BANK_TYPE, 
          BENEFICIARY_BANK_TYPE_NUMBER,-- SWIFT 
          BENEFICIARY_BANK_DESCRIPTION, 
          INTERMEDIARY_BANK_TYPE, 
          INTERMEDIARY_BANK_TYPE_NUMBER, 
          INTERMEDIARY_BANK_DESCRIPTION, 
          ORIGINATORS_INFO, 
          CHARGE_TO, 
          CODTIPOMOV_BOFA, 
          CODEMP, 
          CODCOL, 
          CODINST, 
          BENEFICIARY_NAME, 
          BENEFICIARY_BANK_NAME, 
          INTERMEDIARY_BANK_NAME, 
          ORIGINATORS_NAME, 
          DETAILS_OF_PAYMENT_NAME, 
          DETAILS_OF_PAYMENT_DESCRIPTION, 
          CODMON_CREDIT, 
          BOF01_CODMOV, 
          RECEIVER_INFO, 
          EXOCOM, 
          FECHESPECIAL,
          NUM_INSTRUCCION,
          ORIGINATORS_ADDRESS,
          ORIGINATORS_CITY,
          ORIGINATORS_COUNTRY,
          ORIGINATORS_POSTAL_CODE,
          BENEFICIARY_ADDRESS, 
          BENEFICIARY_CITY,
          BENEFICIARY_COUNTRY,
          BENEFICIARY_POSTAL_CODE,  
          BENEFICIARY_BANK_ADDRESS,
          BENEFICIARY_BANK_CITY,
          BENEFICIARY_BANK_COUNTRY,
          INTERMEDIARY_BANK_ADDRESS,
          INTERMEDIARY_BANK_CITY,
          INTERMEDIARY_BANK_COUNTRY
        ) VALUES (
          P_BOF00_CODMOV, 
          P_BOF00_CODEMP, 
          P_BOF00_CODCOL, 
          P_BOF00_CODINST, 
          V_CODTIPMOV,--P_CODTIPOMOV,--'TCB'
          sysdate,
          to_char(sysdate, 'hh24mi'),
          abs(P_BOF16_AMOUNT)*-1, 
          V_REFBANMOV, 
          --P_BOF03_CURRENCY_CODE,
          MONEDA_CUENTA, 
          P_BENEFICIARIO, 
          P_OBSERV, 
          P_CODEMP_ORIGEN, 
          P_CODCAR_ORIGEN, 
          P_SOURCE,
          'PV', 
          P_BOF16_BANK_REFERENCE_NUMBER, 
          P_BOF00_CODCAR, 
          P_BENEFICIARY_TYPE, 
          P_BENEFICIARY_TYPE_NUMBER, 
          V_BENEFICIARY_DESCRIPTION,--P_BENEFICIARY_DESCRIPTION
          V_BENEFICIARY_BANK_TYPE, 
          V_BENEFICIARY_BANK_TYPE_NUMBER, 
          V_BENEFICIARY_BANK_DESCRIPTION, 
          V_INT_BANK_TYPE, 
          V_INT_BANK_TYPE_NUMB, 
          V_INT_BANK_DESCRIPTION, 
          P_ORIGINATORS_INFO, 
          'OUR', 
          V_CODTIPOMOV_BOFA, --'TCB',--P_CODTIPOMOV_BOFA, 
          P_CODEMP, 
          P_CODCOL, 
          P_CODINST, 
          P_BENEFICIARY_NAME, 
          V_BENEFICIARY_BANK_NAME, 
          V_INT_BANK_NAME, 
          P_ORIGINATORS_NAME, 
          V_DETAILS_OF_PAYMENT_NAME,--'',--P_DETAILS_OF_PAYMENT_NAME 
          P_DETAILS_OF_PAYMENT_DESCRIPTI, 
          P_CODMON_CREDIT, 
          P_BOF01_CODMOV, 
          P_RECEIVER_INFO, 
          'N', --EXOCOM 
          sysdate,
          P_NUM_INSTRUCCION,
          P_ORIGINATORS_ADDRESS,
          P_ORIGINATORS_CITY,
          P_ORIGINATORS_COUNTRY,
          P_ORIGINATORS_POSTAL_CODE,
          V_BENEFICIARY_ADDRESS,
          V_BENEFICIARY_CITY,
          V_BENEFICIARY_COUNTRY,
          V_BENEFICIARY_POSTAL_CODE,  
          V_BENEFICIARY_BANK_ADDRESS,
          V_BENEFICIARY_BANK_CITY,
          V_BENEFICIARY_BANK_COUNTRY, 
          V_INT_BANK_ADDRESS,
          V_INT_BANK_CITY,
          V_INT_BANK_COUNTRY
        );

        
        --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
        p_resultado :='OK';
        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
          WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:=  SUBSTR(SQLERRM,1,300);  
  
END orden_transfe_externa_pr;  


 /******************************************************************************
   NAME:    na_clientes_pr       
   PURPOSE: Busca titular de la cartera destino y origen. 
******************************************************************************/
 PROCEDURE na_clientes_pr (p_CodigoCarteraDebito in VARCHAR2, p_clientes OUT na_clientes, p_resultado OUT VARCHAR2) AS 
    cs_clientes na_clientes;
  
      BEGIN
        OPEN cs_clientes FOR     SELECT NA 
                                FROM produccion.clientes 
                                WHERE codcar=P_CodigoCarteraDebito 
                                AND FLGPRI=-1;
                        
                    p_clientes:= cs_clientes;
                    p_resultado:='OK';
     EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                                                                                     
            p_resultado:= SUBSTR(SQLERRM,1,300);  
 END  na_clientes_pr;



 /******************************************************************************
   NAME:    ac_contrato_pr       
   PURPOSE: Recupera si actpto transferencia de la tabla vbtcontrato. 
******************************************************************************/
  PROCEDURE ac_contrato_pr (p_strNumContrato in VARCHAR2, p_contrato OUT ac_contrato, p_resultado OUT VARCHAR2) AS 
  cs_contrato ac_contrato;
  
  BEGIN
     OPEN cs_contrato FOR     SELECT c.acepto_transferencias 
                            FROM   vbtonline_trans.vbt_contrato c 
                            WHERE  c.num_contrato = p_strNumContrato;
                        p_contrato:= cs_contrato;
                        p_resultado:='OK';
     EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);                     
                        
  END  ac_contrato_pr;
  
   /******************************************************************************
   NAME:    act_trans_contrato       
   PURPOSE: Actualiza en la tabla vbt_contrato: acrpto, usuario, ip y fecha. 
******************************************************************************/
 PROCEDURE act_trans_contrato(p_acepto_tran in VARCHAR2, p_usuario_tran IN VARCHAR2, p_ip_tran IN VARCHAR2, p_num_contrato IN VARCHAR2, p_resultado OUT VARCHAR2) AS 
 BEGIN
                                
 
        UPDATE VBT_CONTRATO SET ACEPTO_TRANSFERENCIAS = p_acepto_tran,
        USUARIO_CONTRATO_TRANSFERENCIA = p_usuario_tran,
        IP_CONTRATO_TRANSFERENCIA      = p_ip_tran,
        FECHA_CONTRATO_TRANSFERENCIA = SYSDATE 
        WHERE NUM_CONTRATO = p_num_contrato;
 
    p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SQLERRM; 
 END act_trans_contrato;


  /******************************************************************************
   NAME:    trans_elementos_ext_pr       
   PURPOSE: Obtener elementos de descripcion de cuentas externas
******************************************************************************/
  
  PROCEDURE trans_elementos_ext_pr (p_elementos_ext OUT elementos_ext, p_resultado OUT VARCHAR2) AS 
  cs_elementos_ext elementos_ext;
   begin 
        OPEN cs_elementos_ext FOR   SELECT ET.CODELEMENTO
                    , ET.DESCRIPCION 
                    FROM VBTONLINE_TRANS.ELEMENTOS_TIPOS ET 
                    WHERE ET.CODTIPO = '0000000006' 
                    ORDER BY ET.CODELEMENTO ASC; 
  
        p_elementos_ext:= cs_elementos_ext;     
                      
        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end trans_elementos_ext_pr;  
 
   /******************************************************************************
   NAME:    trans_paises_pr       
   PURPOSE: Obtener paises y docigos con instirucion financiera
   ******************************************************************************/
  PROCEDURE trans_paises_pr (p_trans_paises OUT trans_paises, p_resultado OUT VARCHAR2) AS 
  cs_trans_paises trans_paises;
 begin
    OPEN cs_trans_paises FOR SELECT DISTINCT vPAISES.COUNTRY_CODE
    , vPAISES.COUNTRY 
    FROM   INSTITUCION@BANCO_VBTF vPAISES
      WHERE COUNTRY_CODE NOT IN (SELECT COUNTRY_CODE FROM LISTA_NEGRA) 
    ORDER BY vPAISES.country ASC;
    p_trans_paises:= cs_trans_paises;     
    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end trans_paises_pr; 
 

 PROCEDURE trans_paises_beneficiario_pr (p_trans_paises OUT trans_paises, p_resultado OUT VARCHAR2) AS 
  cs_trans_paises trans_paises;
 begin
    OPEN cs_trans_paises FOR SELECT DISTINCT vPAISES.COUNTRY_CODE
    , vPAISES.COUNTRY 
    FROM   INSTITUCION@BANCO_VBTF vPAISES 
    ORDER BY vPAISES.country ASC;
    
    /* OPEN cs_trans_paises FOR SELECT DISTINCT vPAISES.COUNTRY_CODE
    , vPAISES.COUNTRY 
    FROM   INSTITUCION@BANCO_VBTF vPAISES 
  UNION ALL SELECT DISTINCT CODELEMENTO, DESCRIPCION
    FROM   ELEMENTOS_TIPOS ET
    WHERE ET.CODTIPO='0000000022' ORDER BY 2;*/
    
    p_trans_paises:= cs_trans_paises;     
    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end trans_paises_beneficiario_pr; 
 
 
 /******************************************************************************
   NAME:    bankCode_elementos_pr       
   PURPOSE: Obtener bankCode de tabla elementos tipo
   ******************************************************************************/
    
   PROCEDURE bankCode_elementos_pr (p_bankCode_elementos OUT bankCode_elementos, p_resultado OUT VARCHAR2) AS 
  cs_bankCode_elementos bankCode_elementos;
  BEGIN 
        OPEN cs_bankCode_elementos FOR   SELECT ET.CODELEMENTO
                            , ET.DESCRIPCION 
                            FROM VBTONLINE_TRANS.ELEMENTOS_TIPOS ET 
                            WHERE ET.CODTIPO = '0000000005'
                            ORDER BY ET.ORDEN ASC; 
  
        p_bankCode_elementos:= cs_bankCode_elementos;     
                      
        p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end bankCode_elementos_pr;  

 /******************************************************************************
   NAME:    trans_ext_confir_pr       
   PURPOSE: cuenta confirmaciones externas
   ******************************************************************************/
    
 PROCEDURE trans_ext_confir_pr (TipoCodBancoBeneficiario   IN VARCHAR2, 
                                CodBancoBeneficiario       IN VARCHAR2, 
                                CodPaisBancoBeneficiario   IN VARCHAR2,
                                TipoCodBancoIntermediario  IN VARCHAR2,
                                CodBancoBancoIntermediario IN VARCHAR2,
                                CodPaisBancoIntermediario  IN VARCHAR2,
                                conta_inst                 OUT NUMBER,
                                p_resultado OUT VARCHAR2,
                                p_sql OUT VARCHAR2) AS 
 
                                SQLSTRING  VARCHAR2(1000);
                                l_cnt      NUMBER(5):=0;
 
 
BEGIN
    SQLSTRING := 'SELECT COUNT (*) 
                  FROM INSTITUCION@BANCO_VBTF I,
                       INSTITUCION_ELIMINADOS@BANCO_VBTF B,
                       INSTITUCION_ELIMINADOS@BANCO_VBTF D,
                       INSTITUCION_ELIMINADOS@BANCO_VBTF C
                  WHERE     I.COUNTRY_CODE NOT IN ( 
                  SELECT L.COUNTRY_CODE
                   FROM LISTA_NEGRA L)
                   AND I.ROUTNUM = B.CODIGO_BANCO(+)
                   AND ''ROUTNUM'' = B.TIPO_CODIGO(+)
                   AND I.BIC = C.CODIGO_BANCO(+)
                   AND ''BIC'' = C.TIPO_CODIGO(+)
                   AND I.CHIPS_UID = D.CODIGO_BANCO(+) 
                   AND ''CHIPS_UID'' = D.TIPO_CODIGO(+)
                   AND B.CODIGO_BANCO IS NULL
                   AND D.CODIGO_BANCO IS NULL
                   AND C.CODIGO_BANCO IS NULL '; 
  
  IF CodBancoBeneficiario IS NOT NULL AND TipoCodBancoBeneficiario <> 'ACCOUNT' THEN
            
         IF TipoCodBancoBeneficiario ='ABA' THEN
            SQLSTRING := SQLSTRING || 'AND I.ROUTCODE =  UPPER('''||TipoCodBancoBeneficiario||''') AND I.ROUTNUM = UPPER(''' ||CodBancoBeneficiario|| ''') AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoBeneficiario||''')';
          elsif   TipoCodBancoBeneficiario ='SWIFT' THEN
              --SQLSTRING := SQLSTRING || 'I.BIC=  UPPER('''||CodBancoBeneficiario||''') AND  I.COUNTRY_CODE = UPPER('''||CodPaisBancoBeneficiario||''')';
              SQLSTRING := SQLSTRING || 'AND I.BIC=  UPPER('''||CodBancoBeneficiario||''')  AND I.BIC<>''VBTBKYKY''';
          elsif   TipoCodBancoBeneficiario ='CHIPS' THEN
              SQLSTRING := SQLSTRING || 'AND I.CHIPS_UID =  UPPER('''||CodBancoBeneficiario||''') AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoBeneficiario||''')';
          else
             SQLSTRING := SQLSTRING || 'AND I.COUNTRY_CODE =  UPPER('''||TipoCodBancoBeneficiario||''')  AND I.ROUTNUM = UPPER(''' ||CodBancoBeneficiario|| ''') AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoBeneficiario||''')';
          end if;    
         
  ELSIF CodBancoBancoIntermediario IS  NOT NULL THEN
         
          IF  TipoCodBancoIntermediario='ABA' THEN
             SQLSTRING := SQLSTRING || 'AND I.ROUTCODE =  UPPER('''||TipoCodBancoIntermediario||''') AND I.ROUTNUM = UPPER(''' ||CodBancoBancoIntermediario|| ''') AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoIntermediario||''')';
          ELSIF TipoCodBancoIntermediario='SWIFT' THEN
             --SQLSTRING := SQLSTRING || 'I.BIC =  UPPER('''||CodBancoBancoIntermediario||''')  AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoIntermediario||''')';
             SQLSTRING := SQLSTRING || 'AND I.BIC =  UPPER('''||CodBancoBancoIntermediario||''') AND I.BIC<>''VBTBKYKY''';
          ELSIF  TipoCodBancoIntermediario='CHIPS'  THEN
             SQLSTRING := SQLSTRING || 'AND I.CHIPS_UID =  UPPER('''||CodBancoBancoIntermediario||''')  AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoIntermediario||''')';
          ELSE
             SQLSTRING := SQLSTRING || 'AND I.COUNTRY_CODE =  UPPER('''||TipoCodBancoIntermediario||''') AND I.ROUTNUM = UPPER(''' ||CodBancoBancoIntermediario|| ''') AND I.COUNTRY_CODE = UPPER('''||CodPaisBancoIntermediario||''')';
          END IF;  
   END IF;
   p_sql := SQLSTRING;
    EXECUTE IMMEDIATE SQLSTRING INTO l_cnt;
     conta_inst :=l_cnt;
   p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
     
--END;
END trans_ext_confir_pr;

  /******************************************************************************
   NAME:    trans_ext_confir_pr       
   PURPOSE: cuenta confirmaciones externas
   ******************************************************************************/

  PROCEDURE trans_exter_recibo_pr (p_strLiberada  IN VARCHAR2,
                                   p_strAprobada  IN VARCHAR2,
                                   p_strCargada   IN VARCHAR2,
                                   p_strRechazada IN VARCHAR2,
                                   p_strAnulada   IN VARCHAR2,
                                   P_strTxtNumeroReferencia IN VARCHAR2,
                                   p_trans_exter_recibo OUT trans_exter_recibo, 
                                   p_resultado OUT VARCHAR2) AS 
  cs_trans_exter_recibo trans_exter_recibo;
  BEGIN 
        OPEN cs_trans_exter_recibo FOR SELECT to_char(O.BOF02_AS_OF_DATE, 'DD/MM/YYYY') 
        ,decode(O.ESTATUS_INSTRUCCION,'L', p_strLiberada, 'A', p_strAprobada , 'C',p_strCargada, 'R', p_strRechazada, 'E', p_strAnulada,'D', p_strRechazada) 
        FROM   VBTONLINE_TRANS.ORDEN_TRANSFERENCIA O 
        WHERE  BOF00_CODMOV = '" + strTxtNumeroReferencia + "';
        p_trans_exter_recibo:= cs_trans_exter_recibo;     
        p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end trans_exter_recibo_pr;  

    /******************************************************************************
   NAME:    trans_ext_confir_pr       
   PURPOSE: Devuelve codigo moneda x cuenta
   ******************************************************************************/

  PROCEDURE trans_codmon_pr (p_strTxtCuentaCredito IN VARCHAR2, p_trans_codmon OUT trans_codmon, p_resultado OUT VARCHAR2) AS 
  cs_trans_codmon trans_codmon;
  BEGIN 
        OPEN cs_trans_codmon FOR     SELECT codmon 
                FROM   ctas_cttes@banco_vbt 
                WHERE  codcol = p_strTxtCuentaCredito ;
                p_trans_codmon:= cs_trans_codmon;     
                p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 END trans_codmon_pr;


     /******************************************************************************
   NAME:    trans_ext_confir_pr       
   PURPOSE: Devuelve codigo moneda y codigo x cuenta credito
   ******************************************************************************/

    PROCEDURE trans_inter_codcar_pr (p_strNumeroCuentaCredito IN VARCHAR2, p_trans_inter_codcar OUT trans_inter_codcar, p_resultado OUT VARCHAR2) AS 
  cs_trans_inter_codcar trans_inter_codcar;
  BEGIN 
        OPEN cs_trans_inter_codcar FOR  SELECT codcar 
                    ,codmon
                    FROM   ctas_cttes@banco_vbt 
                    WHERE  codcol = p_strNumeroCuentaCredito;
                    p_trans_inter_codcar:= cs_trans_inter_codcar;     
                    p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 END trans_inter_codcar_pr;  


/******************************************************************************
   NAME:    cuentas_exter_pr       
   PURPOSE: consulta transferencia x tarjeta y contrato
******************************************************************************/

 PROCEDURE transfe_consulta_pr (p_strCarteras    IN OUT VARCHAR2,
                                p_strNumContrato IN VARCHAR2,
                                p_strTxtDesde    IN VARCHAR2,
                                p_strTxtHasta    IN VARCHAR2,
                                p_transfe_consulta OUT transfe_consulta,
                                p_resultado OUT VARCHAR2,
                                p_sql OUT VARCHAR2) AS 
  --cs_transfe_consulta transfe_consulta;
   SQLSTRING  VARCHAR2(2500);                             
 
 BEGIN
         --OPEN cs_transfe_consulta FOR 
         p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
         
                  SQLSTRING:=      'SELECT OT.NUM_INSTRUCCION
                        ,INITCAP(ET.DESCRIPCION) ESTATUS_TRANSFERENCIA
                        ,TO_CHAR(OT.BOF02_AS_OF_DATE, ''DD/MM/YYYY'') FECHA
                        ,(OT.BOF16_AMOUNT*-1) MONTO
                        ,OT.BOF03_CURRENCY_CODE MONEDA
                        ,UPPER(OT.BENEFICIARIO) BENEFICIARIO
                        ,OT.BOF00_CODMOV REFERENCIA
                        ,OT.ESTATUS_INSTRUCCION COD_ESTATUS 
                        ,decode(OT.codtipomov,''TEO'',OT.BENEFICIARY_TYPE_NUMBER, OT.CODCOL_ORIGEN) 
                        ,OT.BOF00_CODCOL 
                        ,INITCAP(OT.OBSERV) 
                        ,EMAIL_BENEFICIARIO
                        ,OT.USRID_CARGA
                        ,TO_CHAR(OT.FECHA_CARGA, ''DD/MM/YYYY'')
                        ,OT.USRID_APRUEBA
                        ,TO_CHAR(OT.FECHA_APRUEBA, ''DD/MM/YYYY'')
                        ,OT.USRID_LIBERA
                        ,TO_CHAR(OT.FECHA_LIBERA, ''DD/MM/YYYY'')
                        ,OT.USRID_RECHAZA
                        ,TO_CHAR(OT.FECHA_RECHAZA, ''DD/MM/YYYY'')
                        FROM   VBTONLINE_TRANS.ORDEN_TRANSFERENCIA OT
                        ,VBTONLINE_TRANS.ELEMENTOS_TIPOS ET 
                        WHERE  OT.CODCAR IN ('''||p_strCarteras||''') 
                        AND    OT.NUM_CONTRATO = '''||p_strNumContrato||''' 
                        AND    ET.CODTIPO = ''0000000004'' 
                        AND    ET.CODELEMENTO = OT.ESTATUS_INSTRUCCION';
                        
                        IF p_strTxtHasta IS NOT NULL AND p_strTxtDesde IS NOT NULL THEN
                        
                          SQLSTRING:= SQLSTRING || ' AND     TO_DATE(TO_CHAR(OT.BOF02_AS_OF_DATE,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||p_strTxtDesde||''',''dd/mm/yyyy'') 
                                                   AND     TO_DATE(TO_CHAR(OT.BOF02_AS_OF_DATE,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||p_strTxtHasta||''',''dd/mm/yyyy'')';
            
                        END IF;
                        
                        SQLSTRING:= SQLSTRING || ' ORDER BY OT.BOF02_AS_OF_DATE DESC';
                        --p_transfe_consulta:= cs_transfe_consulta;
                        open  p_transfe_consulta for SQLSTRING;     
                        p_sql := SQLSTRING;
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end transfe_consulta_pr;


 /******************************************************************************
   NAME:    cuentas_corri_pr       
   PURPOSE: Obtener saldos y cartera Las cuentas corrientes nacionales
******************************************************************************/
     
 PROCEDURE cuentas_corri_pr (p_strCarteras in  out VARCHAR2, 
 p_cuentas_corri OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2, p_salida out varchar2) AS 
 sqlstring varchar2(1000);
--  cs_cuentas_corrie cuentas_corrie;
 
 BEGIN
 begin
 
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
  end; 
    --    OPEN cs_cuentas_corrie FOR 
  sqlstring :='  SELECT A.CODCOL VALUE,
                 A.TIPOCOL tipo,
                 A.CODCAR CARTERA, 
                 to_char(to_date(A.FECHCIERRE),''dd/mm/YYYY'') FECHA_CIERRE,                  
                 A.SALDO_DISPONIBLE DISPONIBLE,
                 A.CODMON MONEDA
                 FROM BANCO_VBT.CTAS_CTTES A,
                 PRODUCCION.CTAPER E 
                 WHERE A.CODCAR IN ('''||p_strCarteras||''')  
                 AND A.CODEMP = E.CODPER
                 AND A.STATCOL = ''V''
                 ORDER BY A.FECHAPER DESC' ;
--             to_char(A.FECHCIERRE-1,''dd/mm/yyyy'') FECHA_CIERRE,
          --  p_cuentas_corri:= cs_cuentas_corrie;     
               p_salida:= sqlstring;    
            open  p_cuentas_corri for sqlstring;     
            
        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end cuentas_corri_pr;


/******************************************************************************
   NAME:    cuentas_exter_pr       
   PURPOSE: Obtener saldos y cartera Las cuentas corrientes en dolares
******************************************************************************/

   
 
 PROCEDURE cuentas_exter_pr (p_strCarteras in VARCHAR2, p_cuentas_exter OUT cuentas_extern, p_resultado OUT VARCHAR2) AS 
  cs_cuentas_exter cuentas_extern;
 begin
        OPEN cs_cuentas_exter FOR
             SELECT A.CODCOL VALUE, 
             A.TIPOCOL tipo,
             A.CODCAR CARTERA, 
             to_char(A.FECHCIERRE-1,'dd/mm/yyyy') FECHA_CIERRE, 
             A.SALDO_DISPONIBLE DISPONIBLE,
             A.CODMON MONEDA
             FROM BANCO_VBT.CTAS_CTTES A, 
             PRODUCCION.CTAPER E 
             WHERE A.CODCAR IN (p_strCarteras) 
             AND A.CODEMP = E.CODPER 
             AND A.STATCOL = 'V' 
             AND A.CODMON  = 'USD'  
             ORDER BY A.FECHAPER DESC ;
            p_cuentas_exter:= cs_cuentas_exter;     
                      
        p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end cuentas_exter_pr;


     /******************************************************************************
   NAME:    trans_cuentas_inter_pr       
   PURPOSE: Devuelve codigo moneda x cuenta
   ******************************************************************************/

      PROCEDURE trans_cuentas_inter_pr (p_strCarteras IN VARCHAR2, p_trans_cuentas_inter OUT trans_cuentas_inter, p_resultado OUT VARCHAR2) AS
    cs_trans_cuentas_inter trans_cuentas_inter;
 BEGIN
 
        OPEN cs_trans_cuentas_inter FOR  SELECT A.CODCOL VALUE, 
                 A.TIPOCOL tipo,
                 A.CODCAR CARTERA, 
                 to_char(A.FECHCIERRE-1,'dd/mm/yyyy') FECHA_CIERRE, 
                 A.SALDO_DISPONIBLE DISPONIBLE,
                 A.CODMON MONEDA
                 FROM BANCO_VBT.CTAS_CTTES A, 
                 PRODUCCION.CTAPER E 
                 WHERE A.CODCAR IN (p_strCarteras) 
                 AND A.CODEMP = E.CODPER 
                 AND A.STATCOL = 'V' 
                 ORDER BY A.FECHAPER DESC;
                p_trans_cuentas_inter:= cs_trans_cuentas_inter;     
                p_resultado:= 'OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 END trans_cuentas_inter_pr;



     /******************************************************************************
   NAME:   validateuser_handler _pr       
   PURPOSE: Verifica si essiete el usuario y registra numero de intentos
   ******************************************************************************/

 PROCEDURE validateuser_handler_pr (p_login           in VARCHAR2,
                                   p_validateuser_handler OUT validateuser_handler, 
                                   p_resultado OUT VARCHAR2,
                                   p_inte_login out varchar2) AS 
                cs_validateuser_handler validateuser_handler;
  
                p_codcar           varchar2(10);
                p_intIntentosLogin  number(1);
  
  BEGIN
                BEGIN
                    OPEN cs_validateuser_handler
                    FOR     SELECT usu.tipo,
                             UPPER(usu.status_cuenta),
                             TO_CHAR(usu.last_login,'dd/mm/yyyy'),
                             TO_CHAR(usu.last_login,'hh:mi:ss am'),
                             INITCAP(usu.nombres),
                             usu.codpercli,
                             uc.num_contrato,
                             usu.email email,
                             USU.CAMBIO_PASS,
                             DECODE(USU.PASSWORD_OPE,NULL,'',SEGURIDAD.DESENCRIPTA(USU.PASSWORD_OPE)),
                             USU.CIRIF,
                             USU.AMBITO,
                             USU.TELEFONO_CELULAR,
                             GRP.CODGRP,
                             FNC_GET_NUMERICO(USU.CODIGO_PAIS),
                             C.TIPO_CONTRATO,
                             GR.NOMBGRP
                             FROM vbt_users usu,
                             vbt_users_contrato uc, USUGRP_V1 GRP, VBT_CONTRATO C,CTAGRP GR
                             WHERE lower(usu.login) = lower(p_login) 
                             AND GRP.LOGIN=USU.LOGIN
                             AND uc.login(+)= usu.login
                             AND GRP.CODGRP=GR.CODGRP
                             AND C.NUM_CONTRATO(+)=UC.NUM_CONTRATO;                 
                    
                    p_validateuser_handler:= cs_validateuser_handler;
                    p_resultado:='OK';
                EXCEPTION
                WHEN NO_DATA_FOUND THEN
                     
                    begin
                        select intentos_login
                        into p_intIntentosLogin
                        from vbt_users
                        where login = p_login
                        and status_cuenta = 'B' 
                        and fecha_status = SYSDATE;
                    
                    if p_intIntentosLogin > 0   then
                        p_intIntentosLogin:= p_intIntentosLogin +1;
                    end if;
                    
                    exception
                    when no_data_found then
                        p_intIntentosLogin:=1;
                    end;
                    
                    
                    UPDATE vbt_users
                    SET intentos_login = p_intIntentosLogin
                    ,status_cuenta = 'B', 
                    fecha_status = SYSDATE
                    where login = p_login;
                    
                    if p_intIntentosLogin = 3 then
                        p_inte_login:='3 intentos consecutivos fallidos';
                    end if;
                END;
    
               
                
                
 EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                                                                                                                                                                                                                                                                                 
        p_resultado:= SUBSTR(SQLERRM,1,300);  
 END  validateuser_handler_pr;

   /******************************************************************************
   NAME:  validateuser_cartera_pr
   PURPOSE: Devuelve registros de tabla vbt_contrato_carteras segun numero de contrato
   ******************************************************************************/


PROCEDURE validateuser_cartera_pr (p_num_contrato IN VARCHAR2, 
                                   p_validateuser_cartera OUT validateuser_cartera, 
                                   p_resultado OUT VARCHAR2) AS 
     cs_validateuser_cartera validateuser_cartera;                              
    begin
    OPEN cs_validateuser_cartera FOR 
                    SELECT   codcar 
                    FROM     vbt_contrato_carteras 
                    WHERE    num_contrato = p_num_contrato;
                    
                    p_validateuser_cartera:= cs_validateuser_cartera;     
                    p_resultado:= 'OK';                            
                    
                    exception
                    when OTHERS then
                     p_resultado:= SUBSTR(SQLERRM,1,300);
                    
    end validateuser_cartera_pr;
    
    /******************************************************************************
   NAME:    tra_numreferencia_pr      
   PURPOSE: numero de referencia de tabla secuencia@banco_vbtf
   ******************************************************************************/
   
   PROCEDURE tra_numreferencia_pr ( p_referencia OUT VARCHAR2, 
                                        p_resultado OUT VARCHAR2) IS  
   CURSOR C_SECUENCIA IS
   SELECT lpad(to_char((to_number(valsec)+1)),10,'0') 
     INTO p_referencia
     FROM SECUENCIA@BANCO_VBTF 
     WHERE CODEMP = '0000009539' 
     AND CODSEC = '04' 
     FOR UPDATE OF valsec; 
     
                                
   BEGIN
   
        OPEN C_SECUENCIA;
           FETCH C_SECUENCIA INTO p_referencia;
    
               UPDATE SECUENCIA@BANCO_VBTF 
                    SET valsec = p_referencia
                        WHERE CURRENT OF C_SECUENCIA;

              COMMIT;


         CLOSE C_SECUENCIA;
           
      
         p_resultado:= 'OK';    
   
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
    p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   END tra_numreferencia_pr;     
    
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
         P_ESTATUSTRANSFERENCIA      IN VARCHAR2,
         P_TIPOUSUARIO      IN VARCHAR2,
         P_SECUENCIA      OUT VARCHAR2,
         p_resultado                          OUT VARCHAR2) AS
         p_Estatus varchar(10);
         MONEDA_CUENTA       VARCHAR2(20);
         BEGIN
         
         BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_NUMEROCUENTADEBITO;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:=''; 
                END;
                
         
          SELECT NUM_TRANSFERENCIA_SEQ.NEXTVAL
            INTO  P_SECUENCIA
            FROM DUAL;  
  
            p_Estatus := 'L';
            if p_ESTATUSTRANSFERENCIA is not null then
               p_Estatus := p_ESTATUSTRANSFERENCIA;
            end if;
            
           
            IF P_TIPOUSUARIO != 'FC' THEN
            
                --Firmas Insdistintas
                INSERT INTO ORDEN_TRANSFERENCIA (NUM_INSTRUCCION, NUM_CONTRATO, CODCAR, ESTATUS_INSTRUCCION, FECHA_ESTATUS, 
                 BOF00_CODMOV, BOF00_CODEMP, BOF00_CODCOL, BOF00_CODINST, CODTIPOMOV, BOF02_AS_OF_DATE, 
                 BOF02_AS_OF_TIME, BOF16_AMOUNT, REFBANMOV, BOF03_CURRENCY_CODE, BENEFICIARIO, OBSERV, 
                 ORIGINATORS_INFO, CODEMP_ORIGEN, CODCAR_ORIGEN, CODCOL_ORIGEN, USRID_LIBERA, FECHA_LIBERA, 
                 SOURCE, STATWS, EMAIL_BENEFICIARIO, EMAIL_ORIGEN) 
                 VALUES     (P_SECUENCIA,                  
                            P_NUM_CONTRATO, 
                            P_STRCODIGOCARTERADEBITO, 
                            p_Estatus, 
                            sysdate, 
                            P_NUMEROREFERENCIADEBITO,
                            '0000009539',
                            P_NUMEROCUENTADEBITO,
                            'CAH',
                            'CID', 
                            sysdate, 
                            to_char(sysdate, 'hh24mi'), 
                            abs(P_STRHDMMONTO)*-1,  
                            P_STRCODIGOCARTERACREDITO,
                            --P_STRTXTMONEDA,
                            MONEDA_CUENTA,
                            P_STRTXTNOMBREBENEFICIARIO,
                            P_STRTXTCONCEPTO,
                            P_STRCLIENTEDEBITO,
                            '0000009539',
                            P_STRCODIGOCARTERACREDITO,
                            P_NUMEROCUENTACREDITO,
                            P_LOGIN,
                            sysdate, 
                            'ONL',
                            'V', 
                            P_STRTXTEMAILBENEFICIARIO,
                            P_STREMAILORIGEN);    
                     --COMMIT;
            ELSE
             --Firmas Conjuntas
             INSERT INTO ORDEN_TRANSFERENCIA (NUM_INSTRUCCION, NUM_CONTRATO, CODCAR, ESTATUS_INSTRUCCION, FECHA_ESTATUS, 
             BOF00_CODMOV, BOF00_CODEMP, BOF00_CODCOL, BOF00_CODINST, CODTIPOMOV, BOF02_AS_OF_DATE, 
             BOF02_AS_OF_TIME, BOF16_AMOUNT, REFBANMOV, BOF03_CURRENCY_CODE, BENEFICIARIO, OBSERV, 
             ORIGINATORS_INFO, CODEMP_ORIGEN, CODCAR_ORIGEN, CODCOL_ORIGEN, USRID_CARGA, FECHA_CARGA, 
             SOURCE, STATWS, EMAIL_BENEFICIARIO, EMAIL_ORIGEN) 
             VALUES     (P_SECUENCIA,                  
                        P_NUM_CONTRATO, 
                        P_STRCODIGOCARTERADEBITO, 
                        p_Estatus, 
                        sysdate, 
                        P_NUMEROREFERENCIADEBITO,
                        '0000009539',
                        P_NUMEROCUENTADEBITO,
                        'CAH',
                        'CID', 
                        sysdate, 
                        to_char(sysdate, 'hh24mi'), 
                        abs(P_STRHDMMONTO)*-1,  
                        P_STRCODIGOCARTERACREDITO,
                        --P_STRTXTMONEDA,
                        MONEDA_CUENTA,
                        P_STRTXTNOMBREBENEFICIARIO,
                        P_STRTXTCONCEPTO,
                        P_STRCLIENTEDEBITO,
                        '0000009539',
                        P_STRCODIGOCARTERACREDITO,
                        P_NUMEROCUENTACREDITO,
                        P_LOGIN,
                        sysdate, 
                        'ONL',
                        'V', 
                        P_STRTXTEMAILBENEFICIARIO,
                        P_STREMAILORIGEN);    
                 --COMMIT;   
            END IF;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   ORDEN_TRANSFERENCIA_INTERNA;

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
        P_NUM_INSTRUCCION       IN VARCHAR2,
        p_resultado                 OUT VARCHAR2) AS
        MONEDA_CUENTA       VARCHAR2(20);
        
begin

            BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_NUMEROCUENTADEBITO;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:=''; 
                END;
        
        INSERT INTO MOVIMIENTO_BOFA@BANCO_VBTF (BOF00_CODMOV, BOF00_CODEMP, BOF00_CODCOL, BOF00_CODINST, CODTIPOMOV, BOF02_AS_OF_DATE, 
        BOF02_AS_OF_TIME, BOF16_AMOUNT, REFBANMOV, BOF03_CURRENCY_CODE, BENEFICIARIO, OBSERV, 
        ORIGINATORS_INFO, CODEMP_ORIGEN, CODCAR_ORIGEN, CODCOL_ORIGEN, SOURCE, STATWS, TIPOCREDITO, NUM_INSTRUCCION) 
            VALUES (P_NUMEROREFERENCIADEBITO,
                    '0000009539',
                    P_NUMEROCUENTADEBITO,
                    'CAH',
                    'CID', 
                    sysdate, 
                    to_char(sysdate, 'hh24mi'), 
                    abs(P_STRHDMMONTO)*-1, 
                    P_STRCODIGOCARTERACREDITO,
                    --P_STRTXTMONEDA,
                    MONEDA_CUENTA,
                    P_STRTXTNOMBREBENEFICIARIO,
                    P_STRTXTCONCEPTO,
                    P_STRCLIENTEDEBITO,
                    '0000009539',
                    P_STRCODIGOCARTERACREDITO,
                    P_NUMEROCUENTACREDITO,
                    'ONL',
                    'PV',
                    'OL2',P_NUM_INSTRUCCION);  
                    
 --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   trans_movimiento_bofa_it;
  
  
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
        P_NUMERO_INSTRUCCION      IN VARCHAR2,
        p_resultado                 OUT VARCHAR2) AS
        MONEDA_CUENTA       VARCHAR2(20);
        
begin

        BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_NUMEROCUENTADEBITO;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:=''; 
                END;
        
        INSERT INTO MOVIMIENTO_BOFA_TEMP (BOF00_CODMOV, BOF00_CODEMP, BOF00_CODCOL, BOF00_CODINST, CODTIPOMOV, BOF02_AS_OF_DATE, 
        BOF02_AS_OF_TIME, BOF16_AMOUNT, REFBANMOV, BOF03_CURRENCY_CODE, BENEFICIARIO, OBSERV, 
        ORIGINATORS_INFO, CODEMP_ORIGEN, CODCAR_ORIGEN, CODCOL_ORIGEN, SOURCE, STATWS, TIPOCREDITO, NUM_INSTRUCCION) 
            VALUES (P_NUMEROREFERENCIADEBITO,
                    '0000009539',
                    P_NUMEROCUENTADEBITO,
                    'CAH',
                    'CID', 
                    sysdate, 
                    to_char(sysdate, 'hh24mi'), 
                    abs(P_STRHDMMONTO)*-1, 
                    P_STRCODIGOCARTERACREDITO,
                   -- P_STRTXTMONEDA,
                    MONEDA_CUENTA,
                    P_STRTXTNOMBREBENEFICIARIO,
                    P_STRTXTCONCEPTO,
                    P_STRCLIENTEDEBITO,
                    '0000009539',
                    P_STRCODIGOCARTERACREDITO,
                    P_NUMEROCUENTACREDITO,
                    'ONL',
                    'PV',
                    'OL2',
                    P_NUMERO_INSTRUCCION);  
                    
 --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   trans_movimiento_bofa_tmp_it;
  
  
  
  
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
        P_NUM_INSTRUCCION       IN VARCHAR2,
        p_resultado                 OUT VARCHAR2) AS
        MONEDA_CUENTA       VARCHAR2(20);
        
begin        


        BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_NUMEROCUENTADEBITO;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:=''; 
                END;
  

        INSERT INTO MOVIMIENTO_BOFA@BANCO_VBTF (BOF00_CODMOV, BOF00_CODEMP, BOF00_CODCOL, BOF00_CODINST, CODTIPOMOV, BOF02_AS_OF_DATE, 
        BOF02_AS_OF_TIME, BOF16_AMOUNT, REFBANMOV, BOF03_CURRENCY_CODE, BENEFICIARIO, OBSERV, 
        ORIGINATORS_INFO, CODEMP_ORIGEN, CODCAR_ORIGEN, CODCOL_ORIGEN, SOURCE, STATWS, TIPOCREDITO,NUM_INSTRUCCION) 
            VALUES (P_NUMEROREFERENCIADEBITO,
                    '0000009539',
                    P_NUMEROCUENTADEBITO,
                    'CAH',
                    'CIC', 
                    sysdate, 
                    to_char(sysdate, 'hh24mi'), 
                    P_STRHDMMONTO, 
                    P_STRCODIGOCARTERACREDITO,
                    --P_STRTXTMONEDA,
                    MONEDA_CUENTA,
                    P_STRTXTNOMBREBENEFICIARIO,
                    P_STRTXTCONCEPTO,
                    P_STRCLIENTEDEBITO,
                    '0000009539',
                    P_STRCODIGOCARTERACREDITO,
                    P_NUMEROCUENTACREDITO,
                    'ONL',
                    'PV',
                    'OL2',
                    P_NUM_INSTRUCCION);  
                    
 --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   trans_movimiento_bofa_it_cre;

     /******************************************************************************
   NAME:    orden_externa_temp_pr      
   PURPOSE: Guarda la orden de transferencia externa en MOVIMIENTO_BOFA_TEMP
******************************************************************************/



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
        p_resultado                 OUT VARCHAR2) AS
        
begin        
        INSERT INTO MOVIMIENTO_BOFA_TEMP (BOF00_CODMOV, BOF00_CODEMP, BOF00_CODCOL, BOF00_CODINST, CODTIPOMOV, BOF02_AS_OF_DATE, 
        BOF02_AS_OF_TIME, BOF16_AMOUNT, REFBANMOV, BOF03_CURRENCY_CODE, BENEFICIARIO, OBSERV, 
        ORIGINATORS_INFO, CODEMP_ORIGEN, CODCAR_ORIGEN, CODCOL_ORIGEN, SOURCE, STATWS, TIPOCREDITO, NUM_INSTRUCCION) 
            VALUES (P_NUMEROREFERENCIADEBITO,
                    '0000009539',
                    P_NUMEROCUENTADEBITO,
                    'CAH',
                    'CIC', 
                    sysdate, 
                    to_char(sysdate, 'hh24mi'), 
                    P_STRHDMMONTO, 
                    P_STRCODIGOCARTERACREDITO,
                    P_STRTXTMONEDA,
                    P_STRTXTNOMBREBENEFICIARIO,
                    P_STRTXTCONCEPTO,
                    P_STRCLIENTEDEBITO,
                    '0000009539',
                    P_STRCODIGOCARTERACREDITO,
                    P_NUMEROCUENTACREDITO,
                    'ONL',
                    'PV',
                    'OL2',
                     P_NUMERO_INSTRUCCION);  
                    
 --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   trans_mov_bofa_tmp_it_cre;

     /******************************************************************************
   NAME:    orden_externa_temp_pr      
   PURPOSE: Guarda la orden de transferencia externa en MOVIMIENTO_BOFA_TEMP
******************************************************************************/


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
                    P_NUM_INSTRUCCION                 IN NUMBER,
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
                    P_INTERMEDIARY_BANK_COUNTRY     IN VARCHAR2) AS
                    
                    V_TCB                           NUMBER := 0;
                    V_BIC_BENEFICIARIO              TOB_BANKS_CONF.BC_BANK_CODDE%TYPE;
                    V_BIC_BEN_BOFA                  INSTITUCION.BIC@BANCO_VBTF%TYPE;
                    
                    V_BENEFICIARY_ADDRESS           MOVIMIENTO_BOFA.BENEFICIARY_ADDRESS@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_CITY              MOVIMIENTO_BOFA.BENEFICIARY_CITY@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_COUNTRY           MOVIMIENTO_BOFA.BENEFICIARY_COUNTRY@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_POSTAL_CODE       MOVIMIENTO_BOFA.BENEFICIARY_POSTAL_CODE@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_DESCRIPTION       MOVIMIENTO_BOFA.BENEFICIARY_DESCRIPTION@BANCO_VBTF%TYPE;
                    
                    V_DETAILS_OF_PAYMENT_NAME       MOVIMIENTO_BOFA.DETAILS_OF_PAYMENT_NAME@BANCO_VBTF%TYPE;
                    
                    V_BENEFICIARY_BANK_TYPE         MOVIMIENTO_BOFA.BENEFICIARY_BANK_TYPE@BANCO_VBTF%TYPE := 'SA';
                    V_BENEFICIARY_BANK_TYPE_NUMBER  MOVIMIENTO_BOFA.BENEFICIARY_BANK_TYPE_NUMBER@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_DESCRIPTION  MOVIMIENTO_BOFA.BENEFICIARY_DESCRIPTION@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_NAME         MOVIMIENTO_BOFA.BENEFICIARY_BANK_NAME@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_ADDRESS      MOVIMIENTO_BOFA.BENEFICIARY_BANK_ADDRESS@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_CITY         MOVIMIENTO_BOFA.BENEFICIARY_BANK_CITY@BANCO_VBTF%TYPE;
                    V_BENEFICIARY_BANK_COUNTRY      MOVIMIENTO_BOFA.BENEFICIARY_BANK_COUNTRY@BANCO_VBTF%TYPE;
                    
                    V_INT_BANK_TYPE                 MOVIMIENTO_BOFA.INTERMEDIARY_BANK_TYPE@BANCO_VBTF%TYPE;
                    V_INT_BANK_TYPE_NUMB            MOVIMIENTO_BOFA.INTERMEDIARY_BANK_TYPE_NUMBER@BANCO_VBTF%TYPE;
                    V_INT_BANK_DESCRIPTION          MOVIMIENTO_BOFA.INTERMEDIARY_BANK_DESCRIPTION@BANCO_VBTF%TYPE;
                    V_INT_BANK_NAME                 MOVIMIENTO_BOFA.INTERMEDIARY_BANK_NAME@BANCO_VBTF%TYPE;
                    V_INT_BANK_ADDRESS              MOVIMIENTO_BOFA.INTERMEDIARY_BANK_ADDRESS@BANCO_VBTF%TYPE;
                    V_INT_BANK_CITY                 MOVIMIENTO_BOFA.INTERMEDIARY_BANK_CITY@BANCO_VBTF%TYPE;
                    V_INT_BANK_COUNTRY              MOVIMIENTO_BOFA.INTERMEDIARY_BANK_COUNTRY@BANCO_VBTF%TYPE;
                    
                    V_CODTIPMOV                     MOVIMIENTO_BOFA.CODTIPOMOV@BANCO_VBTF%TYPE;
                    V_CODTIPOMOV_BOFA               MOVIMIENTO_BOFA.CODTIPOMOV_BOFA@BANCO_VBTF%TYPE;
                    MONEDA_CUENTA       VARCHAR2(20);
                    
    BEGIN            
                
        BEGIN
            SELECT DESCRIPCION 
                INTO V_BIC_BENEFICIARIO
            FROM ELEMENTOS_TIPOS
            WHERE CODTIPO='0000000024'
            AND CODELEMENTO='TCB' 
            AND DESCRIPCION = P_BENEFICIARY_BANK_TYPE_NUMBER;
          /*SELECT 
            TBC.BC_BANK_CODDE
            INTO
              V_BIC_BENEFICIARIO
            FROM
              TOB_BANKS_CONF TBC
            WHERE
              TBC.BC_STATUS = 'V' AND 
              TBC.BC_MOVEMENT_TYPE = 'TCB' AND
              TBC.BC_BANK_CODDE = P_BENEFICIARY_BANK_TYPE_NUMBER
          ;*/
          
          EXCEPTION
            WHEN OTHERS THEN
              V_BIC_BENEFICIARIO := '';
            
        END;
        
        BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_BOF00_CODCOL;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:='USD'; 
                END;
        

        IF (V_BIC_BENEFICIARIO = P_BENEFICIARY_BANK_TYPE_NUMBER) THEN
          
          SELECT 
            TI.BIC BANK_TYPE_NUMBER,
            SUBSTR(TI.CD_TITLE,1,150),-- BANK_DESCRIPTION,
            SUBSTR(TI.CD_TITLE,1,35),-- BANK_NAME,
            TI.STATE BANK_ADDRESS,
            TI.CITY BANK_CITY,
            TI.COUNTRY_CODE BANK_COUNTRY
            INTO
              V_BENEFICIARY_BANK_TYPE_NUMBER,
              V_BENEFICIARY_BANK_DESCRIPTION,
              V_BENEFICIARY_BANK_NAME,
              V_BENEFICIARY_BANK_ADDRESS,
              V_BENEFICIARY_BANK_CITY,
              V_BENEFICIARY_BANK_COUNTRY
            FROM 
              INSTITUCION@BANCO_VBTF TI
            WHERE
              TI.BIC = (
                SELECT 
                  TPE.VALOR 
                  FROM 
                    PRODUCTO_EMPRESA@BANCO_VBTF TPE
                  WHERE
                    TPE.CODPRODINT = '0000000041' AND
                    TPE.CODEMP = '0000009539'
              )
          ;
       
          V_INT_BANK_TYPE := '';
          V_INT_BANK_TYPE_NUMB := '';
          V_INT_BANK_DESCRIPTION := '';
          V_INT_BANK_NAME := '';
          V_INT_BANK_ADDRESS := '';
          V_INT_BANK_CITY := '';
          V_INT_BANK_COUNTRY := '';

          V_CODTIPOMOV_BOFA := 'TCB';

         SELECT  
            TCD.CIUDAD BENEFICIARY_CITY,
            TCD.ZONAP BENEFICIARY_POSTAL_CODE,
            (TCD.NUMPISO || ' FLOOR ' || DECODE(TCD.NUMAPTO,NULL,'',' SUITE '|| TCD.NUMAPTO)
             || ' ' || TCD.LINEA1 || ' ' || TCD.LINEA2) BENEFICIARY_ADDRESS,
            TIP.COUNTRY_CODE BENEFICIARY_COUNTRY
            INTO
              V_BENEFICIARY_CITY,
              V_BENEFICIARY_POSTAL_CODE,
              V_BENEFICIARY_ADDRESS,
              V_BENEFICIARY_COUNTRY
            FROM 
              CTADIR TCD,
              INSTITUCION_PAIS@BANCO_VBTF TIP
            WHERE
              TCD.CODPERCLI = '0000009539' AND
              TCD.TIPODIR = 'O' AND
              TIP.CODPAIS = TCD.CODPAIS
              AND TCD.FLGDIRFISCAL = -1;
          
          V_CODTIPMOV := 'TCB';
          
          V_DETAILS_OF_PAYMENT_NAME := 'PAY BY CHECK #' || P_BENEFICIARY_TYPE_NUMBER;
          V_BENEFICIARY_DESCRIPTION := V_BENEFICIARY_ADDRESS;

        ELSE
        
          V_BENEFICIARY_BANK_TYPE := P_BENEFICIARY_BANK_TYPE;
          V_BENEFICIARY_BANK_TYPE_NUMBER := P_BENEFICIARY_BANK_TYPE_NUMBER;
          V_BENEFICIARY_BANK_DESCRIPTION := P_BENEFICIARY_BANK_DESCRIPTION;
          V_BENEFICIARY_BANK_NAME := P_BENEFICIARY_BANK_NAME;
          V_BENEFICIARY_BANK_ADDRESS := P_BENEFICIARY_BANK_ADDRESS;
          V_BENEFICIARY_BANK_CITY := P_BENEFICIARY_BANK_CITY;
          V_BENEFICIARY_BANK_COUNTRY := P_BENEFICIARY_BANK_COUNTRY;
          
          V_INT_BANK_TYPE := P_INTERMEDIARY_BANK_TYPE;
          V_INT_BANK_TYPE_NUMB := P_INTERMEDIARY_BANK_TYPE_NUMB;
          V_INT_BANK_DESCRIPTION := P_INTERMEDIARY_BANK_DESCRIPTI;
          V_INT_BANK_NAME := P_INTERMEDIARY_BANK_NAME;
          V_INT_BANK_ADDRESS := P_INTERMEDIARY_BANK_ADDRESS;
          V_INT_BANK_CITY := P_INTERMEDIARY_BANK_CITY;
          V_INT_BANK_COUNTRY := P_INTERMEDIARY_BANK_COUNTRY;
          
          V_CODTIPOMOV_BOFA := P_CODTIPOMOV_BOFA;
          
          V_BENEFICIARY_ADDRESS := P_BENEFICIARY_ADDRESS;
          V_BENEFICIARY_CITY := P_BENEFICIARY_CITY;
          V_BENEFICIARY_COUNTRY := P_BENEFICIARY_COUNTRY;
          V_BENEFICIARY_POSTAL_CODE := P_BENEFICIARY_POSTAL_CODE;
          
          V_DETAILS_OF_PAYMENT_NAME := '';
          V_CODTIPMOV := P_CODTIPOMOV;
          V_BENEFICIARY_DESCRIPTION := P_BENEFICIARY_DESCRIPTION;
          
        END IF;

        INSERT INTO MOVIMIENTO_BOFA_TEMP ( 
          BOF00_CODMOV, 
          BOF00_CODEMP, 
          BOF00_CODCOL, 
          BOF00_CODINST, 
          CODTIPOMOV, 
          BOF02_AS_OF_DATE, 
          BOF02_AS_OF_TIME, 
          BOF16_AMOUNT, 
          REFBANMOV, 
          BOF03_CURRENCY_CODE, 
          BENEFICIARIO, 
          OBSERV, 
          CODEMP_ORIGEN, 
          CODCAR_ORIGEN, 
          SOURCE, 
          STATWS, 
          BOF16_BANK_REFERENCE_NUMBER, 
          BOF00_CODCAR, 
          BENEFICIARY_TYPE, 
          BENEFICIARY_TYPE_NUMBER, 
          BENEFICIARY_DESCRIPTION, 
          BENEFICIARY_BANK_TYPE, 
          BENEFICIARY_BANK_TYPE_NUMBER, 
          BENEFICIARY_BANK_DESCRIPTION, 
          INTERMEDIARY_BANK_TYPE, 
          INTERMEDIARY_BANK_TYPE_NUMBER, 
          INTERMEDIARY_BANK_DESCRIPTION, 
          ORIGINATORS_INFO, 
          CHARGE_TO, 
          CODTIPOMOV_BOFA, 
          CODEMP, 
          CODCOL, 
          CODINST, 
          BENEFICIARY_NAME, 
          BENEFICIARY_BANK_NAME, 
          INTERMEDIARY_BANK_NAME, 
          ORIGINATORS_NAME, 
          DETAILS_OF_PAYMENT_NAME, 
          DETAILS_OF_PAYMENT_DESCRIPTION, 
          CODMON_CREDIT, 
          BOF01_CODMOV, 
          RECEIVER_INFO, 
          EXOCOM, 
          FECHESPECIAL,
          NUM_INSTRUCCION,
          ORIGINATORS_ADDRESS,
          ORIGINATORS_CITY,
          ORIGINATORS_COUNTRY,
          ORIGINATORS_POSTAL_CODE,
          BENEFICIARY_ADDRESS,
          BENEFICIARY_CITY,
          BENEFICIARY_COUNTRY,
          BENEFICIARY_POSTAL_CODE,  
          BENEFICIARY_BANK_ADDRESS,
          BENEFICIARY_BANK_CITY,
          BENEFICIARY_BANK_COUNTRY, 
          INTERMEDIARY_BANK_ADDRESS,
          INTERMEDIARY_BANK_CITY,
          INTERMEDIARY_BANK_COUNTRY 
        ) VALUES (
          P_BOF00_CODMOV, 
          P_BOF00_CODEMP, 
          P_BOF00_CODCOL, 
          P_BOF00_CODINST, 
          V_CODTIPMOV,--P_CODTIPOMOV,--'TCB',
          sysdate,
          to_char(sysdate, 'hh24mi'),
          abs(P_BOF16_AMOUNT)*-1, 
          P_REFBANMOV, 
          --P_BOF03_CURRENCY_CODE,
          MONEDA_CUENTA, 
          P_BENEFICIARIO, 
          P_OBSERV, 
          P_CODEMP_ORIGEN, 
          P_CODCAR_ORIGEN, 
          P_SOURCE,
          'PV', 
          P_BOF16_BANK_REFERENCE_NUMBER, 
          P_BOF00_CODCAR, 
          P_BENEFICIARY_TYPE, 
          P_BENEFICIARY_TYPE_NUMBER, 
          V_BENEFICIARY_DESCRIPTION,--P_BENEFICIARY_DESCRIPTION 
          V_BENEFICIARY_BANK_TYPE, 
          V_BENEFICIARY_BANK_TYPE_NUMBER, 
          V_BENEFICIARY_BANK_DESCRIPTION, 
          V_INT_BANK_TYPE, 
          V_INT_BANK_TYPE_NUMB, 
          V_INT_BANK_DESCRIPTION, 
          P_ORIGINATORS_INFO, 
          'OUR', 
          V_CODTIPOMOV_BOFA, 
          P_CODEMP, 
          P_CODCOL, 
          P_CODINST, 
          P_BENEFICIARY_NAME, 
          V_BENEFICIARY_BANK_NAME, 
          V_INT_BANK_NAME, 
          P_ORIGINATORS_NAME, 
          V_DETAILS_OF_PAYMENT_NAME,--'',--P_DETAILS_OF_PAYMENT_NAME 
          P_DETAILS_OF_PAYMENT_DESCRIPTI, 
          P_CODMON_CREDIT, 
          P_BOF01_CODMOV, 
          P_RECEIVER_INFO, 
          'N', --EXOCOM 
          sysdate,
          P_NUM_INSTRUCCION,
          P_ORIGINATORS_ADDRESS,
          P_ORIGINATORS_CITY,
          P_ORIGINATORS_COUNTRY,
          P_ORIGINATORS_POSTAL_CODE,
          V_BENEFICIARY_ADDRESS,
          V_BENEFICIARY_CITY,
          V_BENEFICIARY_COUNTRY,
          V_BENEFICIARY_POSTAL_CODE,  
          V_BENEFICIARY_BANK_ADDRESS,
          V_BENEFICIARY_BANK_CITY,
          V_BENEFICIARY_BANK_COUNTRY, 
          V_INT_BANK_ADDRESS,
          V_INT_BANK_CITY,
          V_INT_BANK_COUNTRY
        );
            
        p_resultado :='OK'; 
        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
          WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:=  SUBSTR(SQLERRM,1,300);  
  
    END orden_externa_temp_pr;  
  /******************************************************************************
   NAME:    trans_inserta_bofa_pr       
   PURPOSE: Devuelve codigo moneda x cuenta
   ******************************************************************************/

    PROCEDURE trans_inserta_bofa_pr (p_num_instruccion IN OUT VARCHAR2, 
                                       p_resultado OUT VARCHAR2) 
    AS 
    SQLSTRING VARCHAR2(20000);
    cs_imstruccion SYS_REFCURSOR;
    V_CODMOV MOVIMIENTO_BOFA_TEMP.BOF00_CODMOV%TYPE;
    V_CANTIDAD NUMBER;
      BEGIN
      
        SQLSTRING:='SELECT COUNT(BOF00_CODMOV)
                                FROM   MOVIMIENTO_BOFA_TEMP T
                                    WHERE  to_char(T.NUM_INSTRUCCION) IN ('||p_num_instruccion||')';
                                                                
        EXECUTE IMMEDIATE SQLSTRING INTO V_CANTIDAD;
        
        
         IF V_CANTIDAD>0 THEN
        
 
                 SQLSTRING:='SELECT BOF00_CODMOV 
                                    FROM   MOVIMIENTO_BOFA_TEMP T
                                        WHERE  to_char(T.NUM_INSTRUCCION) IN ('||p_num_instruccion||')';
                
                OPEN cs_imstruccion FOR SQLSTRING;
                
                SQLSTRING:='';
                

        
        
                        LOOP
                              
                                  FETCH cs_imstruccion INTO V_CODMOV;
                                                 
                                    EXIT WHEN cs_imstruccion%NOTFOUND; 
                                    
                                          IF V_CODMOV IS NOT NULL THEN
                                          
                                            SQLSTRING:='INSERT INTO MOVIMIENTO_BOFA@BANCO_VBTF(BOF00_CODMOV, 
                                                  BOF01_SENDER_IDENTIFICATION, BOF01_RECEIVER_IDENTIFICATION,
                                                  BOF01_FILE_CREATION_DATE,  BOF01_FILE_CREATION_TIME,  BOF01_SEQUENCE_NUMBER, BOF01_PHYSICAL_RECORD_LENGTH,
                                                  BOF01_BLOCKING_FACTOR,  BOF01_VERSION_NUMBER,  BOF02_ULT_RECEIV_IDENTIF,  BOF02_ORIGINATOR_IDENTIF,
                                                  BOF02_GROUP_STATUS, BOF02_AS_OF_DATE,      BOF02_AS_OF_TIME,  BOF03_COMM_ACCOUNT_NUMBER,
                                                  BOF03_CURRENCY_CODE,  BOF03_TYPE_CODE,  BOF03_AMOUNT,  BOF03_FUNDS_TYPE,
                                                  BOF16_TYPE_CODE,  BOF16_AMOUNT,  BOF16_FUNDS_TYPE,  BOF16_BANK_REFERENCE_NUMBER,
                                                  BOF16_CUST_REFERENCE_NUMBER,  BOF16_TEXT,      BOF49_ACCOUNT_CONTROL_TOTAL,  BOF49_NUMBER_OF_RECORDS,
                                                  BOF98_GROUP_CONTROL_TOTAL,  BOF98_NUMBER_OF_ACCOUNTS,      BOF98_NUMBER_OF_RECORDS,      BOF99_FILE_CONTROL_TOTAL,
                                                  BOF99_NUMBER_OF_GROUPS,  BOF99_NUMBER_OF_RECORDS,      BOF00_CODEMP,  BOF00_CODCAR,
                                                  BOF00_CODCOL,  BOF00_CODINST,      BENEFICIARY_TYPE,  BENEFICIARY_TYPE_NUMBER,
                                                  BENEFICIARY_DESCRIPTION,  BENEFICIARY_BANK_TYPE,      BENEFICIARY_BANK_TYPE_NUMBER,      BENEFICIARY_BANK_DESCRIPTION,
                                                  INTERMEDIARY_BANK_TYPE,  INTERMEDIARY_BANK_TYPE_NUMBER,      INTERMEDIARY_BANK_DESCRIPTION,  ORIGINATORS_BANK_TYPE,
                                                  ORIGINATORS_BANK_TYPE_NUMBER,      ORIGINATORS_BANK_DESCRIPTION,      BENEFICIARY_INFO,      ORIGINATORS_INFO,
                                                  RECEIVER_INFO,  CHARGE_TO,  TRNSEQ,  STATWS,
                                                  FECHA_LECTURA,  FECHA_MODIFICA,  FECHA_PASEMOV,  USRID_LECTURA,
                                                  USRID_MODIFICA,  USRID_PASEMOV,  BOF01_CODMOV,  SEQNBR,
                                                  SWIFT,  STANDBY,  STANDBY_OBSERVACION,      STANDBY_USRIDINICIO,
                                                  STANDBY_FECHAINICIO,      STANDBY_USRIDLIBERA,  STANDBY_FECHALIBERA,      CODTIPOMOV,
                                                  TIPOCREDITO,  SOURCE,  CODEMP_ORIGEN,  CODCAR_ORIGEN,
                                                  CODCOL_ORIGEN,  CODINST_ORIGEN,  CODTIPOMOV_BOFA,  CODEMP,
                                                  CODCOL,  CODINST,  STATMOV,  ORGMOV,
                                                  REFBANMOV,  TASACAMB,  TASACAMB_REF,  TIPOCTA,
                                                  BENEFICIARIO,  OBSERV,  FECHESPECIAL,  PLAZO,
                                                  PRECIO,  RENDIMIENTO,  EXOCOM,  CIRIF,
                                                  USRID_CARGA,  USRID_VERIFICA,  USRID_LIBERA,  USRID_GENERA,
                                                  USRID_TRASPASA,  FECHA_CARGA,  FECHA_VERIFICA,  FECHA_LIBERA,
                                                  FECHA_GENERA,  FECHA_TRASPASA,  CODCOM,  MTOCOM,
                                                  BENEFICIARY_NAME,      BENEFICIARY_BANK_NAME,  INTERMEDIARY_BANK_NAME,  ORIGINATORS_BANK_NAME,
                                                  ORIGINATORS_NAME,      DETAILS_OF_PAYMENT_NAME,  DETAILS_OF_PAYMENT_DESCRIPTION,  CODMON_CREDIT,NUM_INSTRUCCION, ORIGINATORS_ADDRESS,
                                                  ORIGINATORS_CITY, ORIGINATORS_COUNTRY, ORIGINATORS_POSTAL_CODE, BENEFICIARY_ADDRESS , BENEFICIARY_CITY, BENEFICIARY_COUNTRY,
                                                  BENEFICIARY_POSTAL_CODE, BENEFICIARY_BANK_ADDRESS, BENEFICIARY_BANK_CITY, BENEFICIARY_BANK_COUNTRY, INTERMEDIARY_BANK_ADDRESS,
                                                  INTERMEDIARY_BANK_CITY,INTERMEDIARY_BANK_COUNTRY)
                                            SELECT BOF00_CODMOV, 
                                                  BOF01_SENDER_IDENTIFICATION, BOF01_RECEIVER_IDENTIFICATION,
                                                  BOF01_FILE_CREATION_DATE,  BOF01_FILE_CREATION_TIME,  BOF01_SEQUENCE_NUMBER, BOF01_PHYSICAL_RECORD_LENGTH,
                                                  BOF01_BLOCKING_FACTOR,  BOF01_VERSION_NUMBER,  BOF02_ULT_RECEIV_IDENTIF,  BOF02_ORIGINATOR_IDENTIF,
                                                  BOF02_GROUP_STATUS, SYSDATE,      BOF02_AS_OF_TIME,  BOF03_COMM_ACCOUNT_NUMBER,
                                                  BOF03_CURRENCY_CODE,  BOF03_TYPE_CODE,  BOF03_AMOUNT,  BOF03_FUNDS_TYPE,
                                                  BOF16_TYPE_CODE, BOF16_AMOUNT,  BOF16_FUNDS_TYPE,  BOF16_BANK_REFERENCE_NUMBER,
                                                  BOF16_CUST_REFERENCE_NUMBER,  BOF16_TEXT,      BOF49_ACCOUNT_CONTROL_TOTAL,  BOF49_NUMBER_OF_RECORDS,
                                                  BOF98_GROUP_CONTROL_TOTAL,  BOF98_NUMBER_OF_ACCOUNTS,      BOF98_NUMBER_OF_RECORDS,      BOF99_FILE_CONTROL_TOTAL,
                                                  BOF99_NUMBER_OF_GROUPS,  BOF99_NUMBER_OF_RECORDS,      BOF00_CODEMP,  BOF00_CODCAR,
                                                  BOF00_CODCOL,  BOF00_CODINST,      BENEFICIARY_TYPE,  BENEFICIARY_TYPE_NUMBER,
                                                  BENEFICIARY_DESCRIPTION,  BENEFICIARY_BANK_TYPE,      BENEFICIARY_BANK_TYPE_NUMBER,      BENEFICIARY_BANK_DESCRIPTION,
                                                  INTERMEDIARY_BANK_TYPE,  INTERMEDIARY_BANK_TYPE_NUMBER,      INTERMEDIARY_BANK_DESCRIPTION,  ORIGINATORS_BANK_TYPE,
                                                  ORIGINATORS_BANK_TYPE_NUMBER,      ORIGINATORS_BANK_DESCRIPTION,      BENEFICIARY_INFO,      ORIGINATORS_INFO,
                                                  RECEIVER_INFO,  CHARGE_TO,  TRNSEQ,  STATWS,
                                                  FECHA_LECTURA,  FECHA_MODIFICA,  FECHA_PASEMOV,  USRID_LECTURA,
                                                  USRID_MODIFICA,  USRID_PASEMOV,  BOF01_CODMOV,  SEQNBR,
                                                  SWIFT,  STANDBY,  STANDBY_OBSERVACION,      STANDBY_USRIDINICIO,
                                                  STANDBY_FECHAINICIO,      STANDBY_USRIDLIBERA,  STANDBY_FECHALIBERA,      CODTIPOMOV,
                                                  TIPOCREDITO,  SOURCE,  CODEMP_ORIGEN,  CODCAR_ORIGEN,
                                                  CODCOL_ORIGEN,  CODINST_ORIGEN,  CODTIPOMOV_BOFA,  CODEMP,
                                                  CODCOL,  CODINST,  STATMOV,  ORGMOV,
                                                  DECODE(CODTIPOMOV,''TEO'',
                                                        ''CT''||TO_NUMBER(BOF00_CODCAR)||''CC''||TO_NUMBER(BOF00_CODCOL)||''O''|| (   SELECT COUNT (BOF00_CODCOL) + 1
                                                            FROM MOVIMIENTO_BOFA@BANCO_VBTF
                                                    WHERE     CODTIPOMOV = ''TEO''
                                                          AND SOURCE = ''ONL''
                                                          AND TRUNC (BOF02_AS_OF_DATE) = TRUNC (SYSDATE)
                                                          AND BOF00_CODCAR = M.BOF00_CODCAR
                                                          AND BOF00_CODCOL = M.BOF00_CODCOL) , REFBANMOV),
                                                  TASACAMB,  TASACAMB_REF,  TIPOCTA,
                                                  BENEFICIARIO,  OBSERV,  FECHESPECIAL,  PLAZO,
                                                  PRECIO,  RENDIMIENTO,  EXOCOM,  CIRIF,
                                                  USRID_CARGA,  USRID_VERIFICA,  USRID_LIBERA,  USRID_GENERA,
                                                  USRID_TRASPASA,  FECHA_CARGA,  FECHA_VERIFICA,  FECHA_LIBERA,
                                                  FECHA_GENERA,  FECHA_TRASPASA,  CODCOM,  MTOCOM,
                                                  BENEFICIARY_NAME,      BENEFICIARY_BANK_NAME,  INTERMEDIARY_BANK_NAME,  ORIGINATORS_BANK_NAME,
                                                  ORIGINATORS_NAME,      DETAILS_OF_PAYMENT_NAME,  DETAILS_OF_PAYMENT_DESCRIPTION,  CODMON_CREDIT,
                                                  NUM_INSTRUCCION,ORIGINATORS_ADDRESS,ORIGINATORS_CITY, ORIGINATORS_COUNTRY, ORIGINATORS_POSTAL_CODE, BENEFICIARY_ADDRESS , BENEFICIARY_CITY, BENEFICIARY_COUNTRY,
                                                  BENEFICIARY_POSTAL_CODE, BENEFICIARY_BANK_ADDRESS, BENEFICIARY_BANK_CITY, BENEFICIARY_BANK_COUNTRY, INTERMEDIARY_BANK_ADDRESS,
                                                  INTERMEDIARY_BANK_CITY,INTERMEDIARY_BANK_COUNTRY
                                                   FROM 
                                                MOVIMIENTO_BOFA_TEMP M 
                                            WHERE BOF00_CODMOV IN ('||V_CODMOV||')';
                                            EXECUTE IMMEDIATE SQLSTRING;
                                          
                                          END IF;
                                                  
                        END LOOP;
                    CLOSE cs_imstruccion;           
              
               -- COMMIT;
                SQLSTRING:='DELETE FROM 
                    MOVIMIENTO_BOFA_TEMP 
                WHERE to_char(Num_instruccion) IN ('||p_num_instruccion||')';
                 EXECUTE IMMEDIATE SQLSTRING;
               --  COMMIT;
                    p_resultado:= 'OK';   
                
         ELSE
             p_resultado:= 'NO OK';   
         END IF;       
                                                                                                                                                                                                                                                                                                                                                                                                            
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 END trans_inserta_bofa_pr;

/******************************************************************************
   NAME:    transfe_consu_fc_pr       
   PURPOSE: consulta transferencia x tarjeta y contrato
******************************************************************************/

 PROCEDURE transfe_consu_fc_pr (p_strCarteras    IN  OUT VARCHAR2,
                                p_strNumContrato IN VARCHAR2,
                                p_status    IN VARCHAR2,
                                p_transfe_consu_fc OUT  SYS_REFCURSOR,
                                p_resultado OUT VARCHAR2,
                                p_salida OUT VARCHAR2) AS 
                                SQLSTRING VARCHAR2(2500);
 
 
 
 BEGIN
  
 
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2)); 
 
 
      --   OPEN cs_transfe_consu_fc FOR 
         /*
                        SELECT OT.NUM_INSTRUCCION
                        ,INITCAP(ET.DESCRIPCION) ESTATUS_TRANSFERENCIA
                        ,TO_CHAR(OT.BOF02_AS_OF_DATE, 'DD/MM/YYYY') FECHA
                        ,(OT.BOF16_AMOUNT*-1) MONTO
                        ,OT.BOF03_CURRENCY_CODE MONEDA
                        ,UPPER(OT.BENEFICIARIO) BENEFICIARIO
                        ,OT.BOF00_CODMOV REFERENCIA
                        ,OT.ESTATUS_INSTRUCCION COD_ESTATUS 
                        ,decode(OT.codtipomov,'TEO',OT.BENEFICIARY_TYPE_NUMBER, OT.CODCOL_ORIGEN) 
                        ,OT.BOF00_CODCOL 
                        ,INITCAP(OT.OBSERV) 
                        ,EMAIL_BENEFICIARIO 
                        FROM   VBTONLINE_TRANS.ORDEN_TRANSFERENCIA OT
                        ,VBTONLINE_TRANS.ELEMENTOS_TIPOS ET 
                        WHERE  OT.CODCAR IN (p_strCarteras) 
                        AND    OT.NUM_CONTRATO = p_strNumContrato 
                        AND    ET.CODTIPO = '0000000004' 
                        AND    ET.CODELEMENTO = OT.ESTATUS_INSTRUCCION
                        AND    OT.ESTATUS_INSTRUCCION= p_status
                        ORDER BY OT.BOF02_AS_OF_DATE DESC;
                        */
                SQLSTRING:='                  SELECT OT.NUM_INSTRUCCION
                        ,INITCAP(ET.DESCRIPCION) ESTATUS_TRANSFERENCIA
                        ,TO_CHAR(OT.BOF02_AS_OF_DATE, ''DD/MM/YYYY'') FECHA
                        ,(OT.BOF16_AMOUNT*-1) MONTO
                        ,OT.BOF03_CURRENCY_CODE MONEDA
                        ,UPPER(OT.BENEFICIARIO) BENEFICIARIO
                        ,OT.BOF00_CODMOV REFERENCIA
                        ,OT.ESTATUS_INSTRUCCION COD_ESTATUS 
                        ,decode(OT.codtipomov,''TEO'',OT.BENEFICIARY_TYPE_NUMBER, OT.CODCOL_ORIGEN) 
                        ,OT.BOF00_CODCOL 
                        ,INITCAP(OT.OBSERV) 
                        ,EMAIL_BENEFICIARIO
                        ,OT.codtipomov
                        ,OT.USRID_CARGA
                        ,TO_CHAR(OT.FECHA_CARGA, ''DD/MM/YYYY'')
                        ,OT.USRID_APRUEBA
                        ,TO_CHAR(OT.FECHA_APRUEBA, ''DD/MM/YYYY'')
                        FROM   VBTONLINE_TRANS.ORDEN_TRANSFERENCIA OT
                        ,VBTONLINE_TRANS.ELEMENTOS_TIPOS ET 
                        WHERE  OT.NUM_CONTRATO = '''||P_strNumContrato||''' 
                        AND    ET.CODTIPO = ''0000000004'' 
                        AND    ET.CODELEMENTO = OT.ESTATUS_INSTRUCCION
            AND    OT.ESTATUS_INSTRUCCION= '''||p_status||'''
                        ORDER BY OT.BOF02_AS_OF_DATE DESC';     
                       p_salida:= SQLSTRING;
                      --  p_transfe_consu_fc:= cs_transfe_consu_fc;     
                      open  p_transfe_consu_fc for SQLSTRING;  
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end transfe_consu_fc_pr;
 
 /******************************************************************************
   NAME:    transfe_consu_fc_pr       
   PURPOSE: consulta transferencia x tarjeta y contrato
******************************************************************************/

 PROCEDURE transfe_consu_fc_detalle_pr (p_strCarteras    IN  OUT VARCHAR2,
                                p_strNumContrato IN VARCHAR2,
                                p_numref    IN VARCHAR2,
                                p_transfe_consu_fc OUT  SYS_REFCURSOR,
                                p_resultado OUT VARCHAR2,
                                p_salida OUT VARCHAR2) AS 
                                SQLSTRING VARCHAR2(3500);
 BEGIN
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
     -- SQLSTRING:='select NUM_CONTRATO || '' - '' || CODCAR,  
                SQLSTRING:='select  BOF00_CODCOL, 
            BENEFICIARY_BANK_TYPE || '' - '' || BENEFICIARY_BANK_TYPE_NUMBER , 
            BENEFICIARY_BANK_NAME,
            BENEFICIARY_BANK_DESCRIPTION, 
            BENEFICIARIO, 
            DECODE(CODTIPOMOV,''TEO'',BENEFICIARY_TYPE || '' - '' || BENEFICIARY_TYPE_NUMBER, CODCOL_ORIGEN), 
            EMAIL_BENEFICIARIO,
            TELEFONO_BENEFICIARIO,
            BENEFICIARY_DESCRIPTION, 
            INTERMEDIARY_BANK_TYPE || '' - '' || INTERMEDIARY_BANK_TYPE_NUMBER, 
            INTERMEDIARY_BANK_NAME,
            INTERMEDIARY_BANK_DESCRIPTION, 
            CODMON_CREDIT, 
            FFC_NUMBER, 
            FFC_NAME,
            (BOF16_AMOUNT * -1),
            OBSERV,
            ESTATUS_INSTRUCCION, 
            FECHA_ESTATUS,
            USRID_CARGA,
            TO_CHAR(FECHA_CARGA, ''DD/MM/YYYY''),
            USRID_APRUEBA,
            TO_CHAR(FECHA_APRUEBA, ''DD/MM/YYYY''),
            BENEFICIARY_BANK_TYPE_SWIFT || '' - '' || BENEFICIARY_BANK_TNUM_SWIFT , 
            INTERMEDIARY_BANK_TYPE_SWIFT || '' - '' || INTERMEDIARY_BANK_TNUM_SWIFT,
            USRID_LIBERA,
            TO_CHAR(FECHA_LIBERA, ''DD/MM/YYYY'')
                        FROM   VBTONLINE_TRANS.ORDEN_TRANSFERENCIA 
                        WHERE  CODCAR IN ('''||p_strCarteras||''') 
                        AND    NUM_CONTRATO = '''||P_strNumContrato||''' 
                        AND   to_char(num_instruccion) ='''|| p_numref||'''
                         ORDER BY BOF02_AS_OF_DATE DESC';     
                       p_salida:= SQLSTRING;
                      --  p_transfe_consu_fc:= cs_transfe_consu_fc;     
                      open  p_transfe_consu_fc for SQLSTRING;  
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
       p_resultado:= SUBSTR(SQLERRM,1,300);
 
 end transfe_consu_fc_detalle_pr;
 /******************************************************************************
   NAME:    tran_cambiar_estatus_fc_pr      
   PURPOSE: actualiza informacion en tabla orden transferencia
   ******************************************************************************/            
                
       PROCEDURE tran_cambiar_estatus_fc_pr (p_status  IN VARCHAR2,
                                                  p_num_instruccion IN OUT VARCHAR2,
                                                  p_user IN VARCHAR2,
                                                  p_correo IN OUT VARCHAR2,
                                                  p_resultado OUT VARCHAR2) AS 
                                                  sqlstring varchar2(1000);    
        
   begin
   
 --   p_num_instruccion := trim(substr(p_num_instruccion,2,length(p_num_instruccion)));
 
 --  p_num_instruccion := trim(substr(p_num_instruccion,1,length(p_num_instruccion)-1));
 --  p_num_instruccion:=  replace(p_num_instruccion,'''');
   
   if p_status ='A' then
   
        sqlstring:='    update orden_transferencia orden set orden.usrid_aprueba ='''||p_user||'''
                        ,orden.estatus_instruccion ='''||p_status||''',
                        orden.fecha_aprueba=sysdate,
                        orden.fecha_estatus=sysdate 
                        where to_char(orden.num_instruccion) IN ('|| p_num_instruccion||')';
    elsif     p_status ='L' then                
            sqlstring:='    update orden_transferencia orden set
                            orden.usrid_libera ='''||p_user||'''
                            ,orden.estatus_instruccion ='''||p_status||''',
                            orden.fecha_libera=sysdate,
                            orden.fecha_estatus=sysdate 
                            where to_char(orden.num_instruccion) IN ('|| p_num_instruccion||')';
  elsif     p_status ='D' then                
           sqlstring:='    update orden_transferencia orden set
                            orden.usrid_rechaza ='''||p_user||'''
                            ,orden.estatus_instruccion ='''||p_status||''',
                            orden.fecha_rechaza=sysdate,
                            orden.fecha_estatus=sysdate 
                            where to_char(orden.num_instruccion) IN ('|| p_num_instruccion||')';
   end if;
   
      
     EXECUTE IMMEDIATE SQLSTRING;
     
     if     p_status ='D' then  
      
            SQLSTRING:='DELETE FROM 
                MOVIMIENTO_BOFA_TEMP 
            WHERE to_char(Num_instruccion) IN ('||p_num_instruccion||')';
            
             EXECUTE IMMEDIATE SQLSTRING;
     
     end if;
     
       
        
        p_resultado:= 'OK';    
 --       commit; 
--        IF     p_status ='L' THEN     
--            VBTONLINE.vbt_correos_liberar(p_num_instruccion,p_correo);
--        END IF;  
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end tran_cambiar_estatus_fc_pr;    
   
  /******************************************************************************
   NAME:    tran_cambiar_estatus_template_pr      
   PURPOSE: actualiza el estatus del template
   ******************************************************************************/            
                
   PROCEDURE cambiar_estatus_template_pr (p_id   IN VARCHAR2,
                                                   p_user IN VARCHAR2,
                                                   p_resultado OUT VARCHAR2) AS 
                                                   sqlstring   varchar2(1000);    
        
   begin
      
      sqlstring:='   UPDATE VBT_DIRECTORIO template SET
                            template.ESTATUS_APROBACION =''S'',
                            template.FECHA_MODIFICA=sysdate 
                            WHERE template.username ='''||p_user||''' 
                              AND template.codigo = '''||p_id||'''';
   
      
     EXECUTE IMMEDIATE SQLSTRING;
        
        
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);
       
   
   end cambiar_estatus_template_pr;   
   
 /******************************************************************************
   NAME:    orden_transfere_fc_pr       
   PURPOSE: Inserta regsitros en tabla orden transferencia. 
******************************************************************************/

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
        p_resultado OUT VARCHAR2) AS 
  
  BEGIN
            INSERT INTO ORDEN_TRANSFERENCIA ( 
            NUM_INSTRUCCION, 
            NUM_CONTRATO, 
            CODCAR, 
            ESTATUS_INSTRUCCION, 
            FECHA_ESTATUS, 
            BOF00_CODMOV, 
            BOF00_CODEMP, 
            BOF00_CODCOL, 
            BOF00_CODINST, 
            CODTIPOMOV,    
            BOF02_AS_OF_DATE, 
            BOF02_AS_OF_TIME, 
            BOF16_AMOUNT, 
            REFBANMOV, 
            BOF03_CURRENCY_CODE, 
            BENEFICIARIO, 
            OBSERV, 
            CODEMP_ORIGEN, 
            CODCAR_ORIGEN, 
            USRID_CARGA, 
            FECHA_CARGA, 
            SOURCE, 
            STATWS, 
            EMAIL_BENEFICIARIO, 
            EMAIL_ORIGEN, 
            BOF16_BANK_REFERENCE_NUMBER, 
            BOF00_CODCAR, 
            BENEFICIARY_TYPE, 
            BENEFICIARY_TYPE_NUMBER, 
            BENEFICIARY_DESCRIPTION, 
            TELEFONO_BENEFICIARIO, 
            BENEFICIARY_BANK_TYPE, 
            BENEFICIARY_BANK_TYPE_NUMBER, 
            BENEFICIARY_BANK_DESCRIPTION, 
            INTERMEDIARY_BANK_TYPE, 
            INTERMEDIARY_BANK_TYPE_NUMBER, 
            INTERMEDIARY_BANK_DESCRIPTION, 
            ORIGINATORS_INFO, 
            CHARGE_TO, 
            CODTIPOMOV_BOFA, 
            CODEMP, 
            CODCOL, 
            CODINST, 
            BENEFICIARY_NAME, 
            BENEFICIARY_BANK_NAME, 
            INTERMEDIARY_BANK_NAME, 
            ORIGINATORS_NAME, 
            DETAILS_OF_PAYMENT_NAME, 
            DETAILS_OF_PAYMENT_DESCRIPTION, 
            CODMON_CREDIT, 
            FFC_NUMBER, 
            FFC_NAME 
            ) 
            VALUES (
            NUM_TRANSFERENCIA_SEQ.NEXTVAL, 
            P_NUM_CONTRATO, 
            P_CODCAR, 
            P_ESTATUS_INSTRUCCION,
            sysdate, 
            P_BOF00_CODMOV, 
            P_BOF00_CODEMP, 
            P_BOF00_CODCOL, 
            P_BOF00_CODINST, 
            P_CODTIPOMOV,    
            sysdate, 
            to_char(sysdate, 'hh24mi'), 
            abs(P_BOF16_AMOUNT)*-1, 
            P_REFBANMOV, 
            P_BOF03_CURRENCY_CODE, 
            P_BENEFICIARIO, 
            P_OBSERV, 
            P_CODEMP_ORIGEN, 
            P_CODCAR_ORIGEN, 
            P_USRID_LIBERA, 
            sysdate, 
            P_SOURCE, 
            'V',
            P_EMAIL_BENEFICIARIO, 
            P_EMAIL_ORIGEN, 
            P_BOF16_BANK_REFERENCE_NUMBER, 
            P_BOF00_CODCAR, 
            P_BENEFICIARY_TYPE, 
            P_BENEFICIARY_TYPE_NUMBER, 
            P_BENEFICIARY_DESCRIPTION, 
            P_TELEFONO_BENEFICIARIO, 
            P_BENEFICIARY_BANK_TYPE, 
            P_BENEFICIARY_BANK_TYPE_NUMBER, 
            P_BENEFICIARY_BANK_DESCRIPTION, 
            P_INTERMEDIARY_BANK_TYPE, 
            P_INTERMEDIARY_BANK_TYPE_NUM, 
            P_INTERMEDIARY_BANK_DESCRIPT, 
            P_ORIGINATORS_INFO, 
            'OUR', 
            P_CODTIPOMOV_BOFA, 
            P_CODEMP, 
            P_CODCOL, 
            P_CODINST, 
            P_BENEFICIARY_NAME, 
            P_BENEFICIARY_BANK_NAME, 
            P_INTERMEDIARY_BANK_NAME, 
            P_ORIGINATORS_NAME, 
            '',--P_DETAILS_OF_PAYMENT_NAME 
            P_DETAILS_OF_PAYMENT_DESCRIPT, 
            P_CODMON_CREDIT, 
            P_FFC_NUMBER, 
            P_FFC_NAME );    
            --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   orden_transfere_fc_pr;

       
PROCEDURE actualizar_contrato (p_codpercli varchar2, p_num_contrato varchar2, p_vbt_nmtd number, p_vbt_mmtd number,  p_ex_nmtd  number,   p_ex_mmtd  number, p_resultado OUT VARCHAR2) 
IS
    encontrado number(3);
BEGIN
    select count(*) into encontrado
    from operaciones_cliente
    where codpercli=p_codpercli 
    and num_contrato=p_num_contrato;

    if encontrado>0 then
        update operaciones_cliente
        set vbt_nmtd=p_vbt_nmtd,
            vbt_mmtd=p_vbt_mmtd,  
            ex_nmtd=p_ex_nmtd,   
            ex_mmtd=p_ex_mmtd  
        where codpercli=p_codpercli 
        and num_contrato=p_num_contrato;
    else
        insert into operaciones_cliente
        values (p_codpercli, p_num_contrato, p_vbt_nmtd, p_vbt_mmtd,  p_ex_nmtd ,   p_ex_mmtd, sysdate) ;
    end if;
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300); 
END actualizar_contrato;



PROCEDURE consultar_contrato (p_codpercli varchar2, p_num_contrato varchar2,c_contratos OUT contratos, p_resultado OUT VARCHAR2) 
IS
BEGIN
    open c_contratos
    for select codpercli,
           num_contrato,
           VBT_NMTD,
           VBT_MMTD,
           EX_NMTD,
           EX_MMTD,
           to_char(FECHA_OPE,'DD/MM/YYYY')
        from operaciones_cliente
        where codpercli=p_codpercli 
        and num_contrato=p_num_contrato;
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);         
END consultar_contrato;

    PROCEDURE validar_pp_pr(p_codpercli IN VARCHAR2, p_monto in pls_integer, p_contrato IN varchar2,p_tipo_transf IN varchar2, po_tag out varchar2, po_resultado out varchar, p_tipo in varchar2) IS

        v_contrato VBTONLINE_TRANS.vbt_movimi_ope_codpercli.NUM_CONTRATO%type;
        v_VBT_NMTD VBTONLINE_TRANS.vbt_movimi_ope_codpercli.VBT_NMTD%type; 
        v_VBT_MMTD VBTONLINE_TRANS.vbt_movimi_ope_codpercli.VBT_MMTD%type;  
        v_VBT_MMINTO VBTONLINE_TRANS.vbt_movimi_ope_codpercli.VBT_MMINTO%type; 
        v_VBT_MMTO VBTONLINE_TRANS.vbt_movimi_ope_codpercli.VBT_MMTO%type; 
        v_EX_NMTD VBTONLINE_TRANS.vbt_movimi_ope_codpercli.EX_NMTD%type; 
        v_EX_MMTD VBTONLINE_TRANS.vbt_movimi_ope_codpercli.EX_MMTD%type; 
        v_EX_MMINTO VBTONLINE_TRANS.vbt_movimi_ope_codpercli.EX_MMINTO%type; 
        v_EX_MMTO VBTONLINE_TRANS.vbt_movimi_ope_codpercli.EX_MMTO%type;
        v_MINIMUN_BALANCE VBTONLINE_TRANS.VBT_MOVIMI_OPERACIONES.MINIMUM_BALANCE%type;        
        
        v_cant_transferencia pls_integer;
        v_sum_transferencia number(20);
        
    BEGIN
        BEGIN
            SELECT NUM_CONTRATO
            INTO v_contrato
            FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
            WHERE NUM_CONTRATO=p_contrato;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
            
            
                  IF p_tipo='FC' THEN
                  --Firmas Conjuntas
                       Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, MINIMUM_BALANCE
                            INTO v_VBT_NMTD, v_VBT_MMTD, v_VBT_MMINTO, v_VBT_MMTO,v_EX_NMTD, v_EX_MMTD, v_EX_MMINTO, v_EX_MMTO, V_MINIMUN_BALANCE
                            FROM VBTONLINE_TRANS.PARAMETROS_GLOBALES_FC;
                  ELSE
                  --Clientes Full Acceso
                       Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, MINIMUM_BALANCE
                            INTO v_VBT_NMTD, v_VBT_MMTD, v_VBT_MMINTO, v_VBT_MMTO,v_EX_NMTD, v_EX_MMTD, v_EX_MMINTO, v_EX_MMTO, V_MINIMUN_BALANCE
                            FROM VBTONLINE_TRANS.vbt_movimi_operaciones;
                  END IF;
               
                
              
                  
                po_resultado:='OK';
                
                IF p_tipo_transf='TIN' THEN
                
                      SELECT count(*), sum(abs(O.BOF16_AMOUNT))
                            INTO v_cant_transferencia, v_sum_transferencia
                               FROM ORDEN_TRANSFERENCIA o
                               WHERE trunc(O.FECHA_ESTATUS) = trunc(sysdate)
                                  AND o.num_contrato=p_contrato
                                  AND o.BENEFICIARY_TYPE_NUMBER is null; 
                              
                
                
                
                    IF v_cant_transferencia>=v_VBT_NMTD THEN
                        po_resultado:='NO OK';
                        po_tag:='TAGmsg_PP_numeroMaxTransDiarias';
                        RETURN;
                    END IF;
                    
                    IF v_sum_transferencia+p_monto>v_VBT_MMTD THEN
                        po_resultado:='NO OK';
                        po_tag:='TAGmsg_PP_montoMaxTransDiarias';
                        RETURN;
                    END IF;
                    
                    IF p_monto>0 THEN
                        IF p_monto<v_VBT_MMINTO THEN
                            po_resultado:='NO OK';
                            po_tag:='TAGmsg_PP_montoMinTransOpe';
                            RETURN;
                        END IF;
                    END IF;
                    
                    IF p_monto>v_VBT_MMTO THEN
                        po_resultado:='NO OK';
                        po_tag:='TAGmsg_PP_montoMaxTransOpe';
                        RETURN;
                    END IF;
                    
                    IF po_resultado='OK' THEN
                        po_tag:='1';
                        RETURN;
                    END IF;
                    
                ELSE
                    
                      SELECT count(*), sum(abs(O.BOF16_AMOUNT))
                                INTO v_cant_transferencia, v_sum_transferencia
                                   FROM ORDEN_TRANSFERENCIA o
                                   WHERE trunc(O.FECHA_ESTATUS) = trunc(sysdate)
                                      AND o.num_contrato=p_contrato
                                      AND o.BENEFICIARY_TYPE_NUMBER is not null; 
                
                
                    IF v_cant_transferencia>=v_EX_NMTD THEN
                        po_resultado:='NO OK';
                        po_tag:='TAGmsg_PP_numeroMaxTransDiarias';
                        RETURN;
                    END IF;
                    
                    IF v_sum_transferencia+p_monto>v_EX_MMTD THEN
                        po_resultado:='NO OK';
                        po_tag:='TAGmsg_PP_montoMaxTransDiarias';
                        RETURN;
                    END IF;
                    
                    IF p_monto>0 THEN
                        IF p_monto<v_EX_MMINTO THEN
                            po_resultado:='NO OK';
                            po_tag:='TAGmsg_PP_montoMinTransOpe';
                            RETURN;
                        END IF;
                    END IF;
                    
                    IF p_monto>v_EX_MMTO THEN
                        po_resultado:='NO OK';
                        po_tag:='TAGmsg_PP_montoMaxTransOpe';
                        RETURN;
                    END IF;
                    
                    IF po_resultado='OK' THEN
                        po_tag:='1';
                        RETURN;
                    END IF;
                    
                END IF;
                
        END;

        
        SELECT VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO
        INTO v_VBT_NMTD, v_VBT_MMTD, v_VBT_MMINTO, v_VBT_MMTO,v_EX_NMTD, v_EX_MMTD, v_EX_MMINTO, v_EX_MMTO
        FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
        WHERE NUM_CONTRATO=p_contrato;
                
        
                  
        po_resultado:='OK';
                
        IF p_tipo_transf='TIN' THEN
                
                SELECT count(*), sum(abs(O.BOF16_AMOUNT))
                INTO v_cant_transferencia, v_sum_transferencia
                FROM ORDEN_TRANSFERENCIA o
                WHERE trunc(O.FECHA_ESTATUS) = trunc(sysdate)
                  AND o.num_contrato=p_contrato
                  AND o.BENEFICIARY_TYPE_NUMBER is null; 
        
        
            IF v_cant_transferencia>=v_VBT_NMTD THEN
                po_resultado:='NO OK';
                po_tag:='TAGmsg_PP_numeroMaxTransDiarias';
                RETURN;
            END IF;
                    
            IF v_sum_transferencia+p_monto>v_VBT_MMTD THEN
                po_resultado:='NO OK';
                po_tag:='TAGmsg_PP_montoMaxTransDiarias';
                RETURN;
            END IF;
                 
            
            IF p_monto>0 THEN   
                IF p_monto<v_VBT_MMINTO THEN
                    po_resultado:='NO OK';
                    po_tag:='TAGmsg_PP_montoMinTransOpe';
                    RETURN;
                END IF;
            END IF;
                
            IF p_monto>v_VBT_MMTO THEN
                po_resultado:='NO OK';
                po_tag:='TAGmsg_PP_montoMaxTransOpe';
                RETURN;
            END IF;
                    
            IF po_resultado='OK' THEN
                po_tag:='1';
                RETURN;
            END IF;
                    
        ELSE
            SELECT count(*), sum(abs(O.BOF16_AMOUNT))
                INTO v_cant_transferencia, v_sum_transferencia
                FROM ORDEN_TRANSFERENCIA o
                WHERE trunc(O.FECHA_ESTATUS) = trunc(sysdate)
                  AND o.num_contrato=p_contrato
                  AND o.BENEFICIARY_TYPE_NUMBER is not null;
                           
            IF v_cant_transferencia>=v_EX_NMTD THEN
                po_resultado:='NO OK';
                po_tag:='TAGmsg_PP_numeroMaxTransDiarias';
                RETURN;
            END IF;
                    
            IF v_sum_transferencia+p_monto>v_EX_MMTD THEN
                po_resultado:='NO OK';
                po_tag:='TAGmsg_PP_montoMaxTransDiarias';
                RETURN;
            END IF;
                    
            IF p_monto>0 THEN
                IF p_monto<v_EX_MMINTO THEN
                    po_resultado:='NO OK';
                    po_tag:='TAGmsg_PP_montoMinTransOpe';
                    RETURN;
                END IF;
            END IF;
                    
            IF p_monto>v_EX_MMTO THEN
                po_resultado:='NO OK';
                po_tag:='TAGmsg_PP_montoMaxTransOpe';
                RETURN;
            END IF;
                    
            IF po_resultado='OK' THEN
                po_tag:='1';
                RETURN;
            END IF;
                    
        END IF;
    END validar_pp_pr;
    
    PROCEDURE ActualizarTerminosCondiciones (numcontrato varchar2, usuario varchar2, direccionip varchar2, fecha varchar2, acepto varchar2, p_resultado OUT VARCHAR2) 
    IS   
    BEGIN
            UPDATE vbt_users usu
            SET
                usu.IP_CONTRATO_TRANSFERENCIA = direccionip ,   
                usu.FECHA_CONTRATO_TRANSFERENCIA = sysdate 
            WHERE USU.LOGIN = usuario ;
            commit;
                p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
            EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
            WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:=  SUBSTR(SQLERRM,1,300);         
    END ActualizarTerminosCondiciones;
    /*    PROCEDURE ActualizarTerminosCondiciones (numcontrato varchar2, usuario varchar2, direccionip varchar2, fecha varchar2, acepto varchar2, p_resultado OUT VARCHAR2) 
    IS   
    BEGIN
            UPDATE vbtonline_trans.vbt_IP_CONTRATO_TRANSFERENCIAcontrato c
            SET c.ACEPTO_TRANSFERENCIAS = acepto,
                c.USUARIO_CONTRATO_TRANSFERENCIA = usuario ,  
                c.IP_CONTRATO_TRANSFERENCIA = direccionip ,   
                c.FECHA_CONTRATO_TRANSFERENCIA = sysdate 
            WHERE c.NUM_CONTRATO = numcontrato ;
            commit;
                p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
            EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
            WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:=  SUBSTR(SQLERRM,1,300);         
    END ActualizarTerminosCondiciones;*/
    PROCEDURE ConsultarAprobadoresContrato (p_numcontrato IN VARCHAR2, p_tipo IN VARCHAR2, c_emails OUT emails, p_resultado out VARCHAR2)
    IS
    BEGIN
      open c_emails
      for  SELECT EMAIL
        FROM (SELECT L.EMAIL

         FROM VBT_USERS L, VBT_USERS_CONTRATO X
        WHERE     L.STATUS_CUENTA = 'A'
              AND L.LOGIN = X.LOGIN
              AND X.NUM_CONTRATO = ''|| p_numcontrato ||'' 
              AND L.TIPO = ''|| p_tipo ||''
       UNION ALL
       SELECT US.EMAIL
         FROM FC_CTAROL FCR,
              FC_USUROL USR,
              VBT_USERS_CONTRATO UC,
              VBT_USERS US
        WHERE     UC.NUM_CONTRATO = ''|| p_numcontrato ||'' 
              AND UC.LOGIN = US.LOGIN
              AND US.LOGIN = USR.LOGIN
              AND FCR.CODROL = USR.CODROL
              AND FCR.TIPO =''|| p_tipo ||'');  
          p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);
    END ConsultarAprobadoresContrato;
    
    PROCEDURE correos_rechazo (p_num_instruccion IN VARCHAR2, c_emails OUT emails, p_resultado out VARCHAR2) AS 
                                SQLSTRING VARCHAR2(2000);
 
    BEGIN
    
         SQLSTRING:='SELECT DISTINCT USR.EMAIL
                        FROM ORDEN_TRANSFERENCIA TRA, VBT_USERS USR
                             WHERE to_char(TRA.NUM_INSTRUCCION) IN ('||p_num_instruccion||') AND USR.LOGIN=TRA.USRID_CARGA 
                                OR USR.LOGIN=TRA.USRID_APRUEBA AND to_char(TRA.NUM_INSTRUCCION) IN ('||p_num_instruccion||')';
                                
          open c_emails for SQLSTRING;  
          p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);
    END correos_rechazo;
    
    
    
   PROCEDURE cargar_numero_referencia (p_num_instruccion IN VARCHAR2, p_referencias OUT  SYS_REFCURSOR, p_resultado out VARCHAR2) AS 
                                SQLSTRING VARCHAR2(2000);
 
    BEGIN
    
         SQLSTRING:='SELECT DISTINCT TRA.BOF00_CODMOV
                        FROM ORDEN_TRANSFERENCIA TRA
                             WHERE to_char(TRA.NUM_INSTRUCCION) IN ('||p_num_instruccion||')';
                                
          open p_referencias for SQLSTRING;  
          p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);
    END cargar_numero_referencia;
    
    
      PROCEDURE montos_liberador_fc(p_contrato IN VARCHAR2, p_monto_interna OUT number, p_monto_externa OUT number,p_cantidad_interna OUT number, p_cantidad_externa OUT number,p_resultado OUT VARCHAR2) IS

        v_sum_transferencia_interna number;
        v_sum_transferencia_externa number;
        v_cant_transferencia_interna number;
        v_cant_transferencia_externa number;
    BEGIN
        
         SELECT NVL(count(*), 0), NVL(TO_CHAR(sum(abs(O.BOF16_AMOUNT))), 0)
                            INTO v_cant_transferencia_interna, v_sum_transferencia_interna
                               FROM ORDEN_TRANSFERENCIA o
                               WHERE trunc(O.FECHA_ESTATUS) = trunc(sysdate)
                                  AND o.num_contrato=p_contrato
                                  AND o.ESTATUS_INSTRUCCION='L'
                                  AND o.CODTIPOMOV='CID'; 
          SELECT NVL(count(*), 0), NVL(TO_CHAR(sum(abs(O.BOF16_AMOUNT))), 0)
                            INTO v_cant_transferencia_externa, v_sum_transferencia_externa
                               FROM ORDEN_TRANSFERENCIA o
                               WHERE trunc(O.FECHA_ESTATUS) = trunc(sysdate)
                                  AND o.num_contrato=p_contrato
                                  AND o.ESTATUS_INSTRUCCION='L'
                                  AND o.CODTIPOMOV='TEO';   
            p_monto_interna:=v_sum_transferencia_interna;                            
            p_monto_externa:=v_sum_transferencia_externa;  
            p_cantidad_interna:=v_cant_transferencia_interna;                            
            p_cantidad_externa:=v_cant_transferencia_externa;       
            p_resultado :='OK';                     
       EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300); 
    END montos_liberador_fc;
    
    PROCEDURE cargar_montos_fc (p_num_referencia IN VARCHAR2, p_datos OUT  SYS_REFCURSOR, p_resultado out VARCHAR2) AS 
                                SQLSTRING VARCHAR2(2000);
 
    BEGIN
    
         SQLSTRING:='SELECT CODTIPOMOV, ABS(BOF16_AMOUNT),BOF03_CURRENCY_CODE
                         FROM ORDEN_TRANSFERENCIA T 
                              WHERE to_char(T.BOF00_CODMOV) IN ('||p_num_referencia||')';
                                
          open p_datos for SQLSTRING;  
          p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);
    END cargar_montos_fc;
    
    /**
 *      FUNCIÓN:        VALIDAR_BANCO_BENEFICIARIO(PI_BIC_BENEFICIARIO, PI_CUENTA_BENEFICIARIO, PI_BIC_INTERMEDIARIO, PO_ERROR_CODIGO, PO_ERROR_MENSAJE)
 *      AUTOR:          RRANGEL
 *      FECHA:          24/10/2014
 *      DESCRIPCIÓN:    
 *                      
 * */
 PROCEDURE VALIDAR_BANCO_BENEFICIARIO(
    PI_BIC_BENEFICIARIO IN VARCHAR2, 
    PI_CUENTA_BENEFICIARIO IN VARCHAR2,
    PI_BIC_INTERMEDIARIO IN VARCHAR2, 
    PO_ERROR_CODIGO OUT VARCHAR2, 
    PO_ERROR_MENSAJE OUT VARCHAR2
 ) AS
 
  V_CANTIDAD NUMBER;
  
 BEGIN

    PO_ERROR_CODIGO := '0';
    PO_ERROR_MENSAJE := 'SUCCESS';

  -- VALIDAR SWIFT (BIC) DEL BENEFICIARIO
    /*SELECT COUNT(*)
        INTO V_CANTIDAD
        FROM TOB_BANKS_CONF
        WHERE BC_STATUS = 'I'
        AND BC_BANK_ACCOUNT IS NULL
        AND BC_BANK_CODDE = PI_BIC_BENEFICIARIO
    ;

    IF V_CANTIDAD > 0 THEN
        PO_ERROR_CODIGO := '1';
        PO_ERROR_MENSAJE := 'BICBEN';
    END IF;*/
  
  -- VALIDAR SWIFT (BIC) DEL INTERMEDIARIO
  /*  SELECT COUNT(*)
        INTO V_CANTIDAD
        FROM TOB_BANKS_CONF
        WHERE BC_STATUS = 'I'
        AND BC_BANK_ACCOUNT IS NULL
        AND BC_BANK_CODDE = PI_BIC_INTERMEDIARIO
    ;

    IF V_CANTIDAD > 0 THEN
        IF PO_ERROR_CODIGO = '0' THEN
            PO_ERROR_CODIGO := '3';
            PO_ERROR_MENSAJE := 'BICINT';
        ELSE
            PO_ERROR_CODIGO := '5';
            PO_ERROR_MENSAJE := 'BICALL';
        END IF;
    END IF;*/
    
  -- VALIDAR CUENTA DEL BENEFICIARIO SWIFT (BIC) DEL BENEFICIARIO
  
  
        SELECT COUNT(*)
        INTO V_CANTIDAD
        FROM TOB_BANKS_CONF
        WHERE STATUS = 'A'
        AND BC_BANK_ACCOUNT = PI_CUENTA_BENEFICIARIO
        AND BC_BANK_CODDE = PI_BIC_BENEFICIARIO;
       /*   
   SELECT COUNT(*)
        INTO V_CANTIDAD
        FROM TOB_BANKS_CONF
        WHERE BC_STATUS = 'I'
        AND BC_BANK_ACCOUNT = PI_CUENTA_BENEFICIARIO
        AND BC_BANK_CODDE = PI_BIC_BENEFICIARIO
 
    */
    IF V_CANTIDAD > 0 THEN
    
      PO_ERROR_CODIGO := '2';
      PO_ERROR_MENSAJE := 'All';
      /*  CASE PO_ERROR_CODIGO
            WHEN '0' THEN
                PO_ERROR_CODIGO := '4';
                PO_ERROR_MENSAJE := 'ALLBEN';
            WHEN '1' THEN
                PO_ERROR_CODIGO := '4';
                PO_ERROR_MENSAJE := 'ALLBEN';
            WHEN '3' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
            WHEN '5' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
        END CASE;*/
    END IF;
     
  -- VALIDAR CUENTA DEL BENEFICIARIO SWIFT (BIC) DEL INTERMEDIARIO
    SELECT COUNT(*)
        INTO V_CANTIDAD
        FROM TOB_BANKS_CONF
        WHERE STATUS = 'A'
        AND BC_BANK_ACCOUNT = PI_CUENTA_BENEFICIARIO
        AND BC_BANK_CODDE = PI_BIC_INTERMEDIARIO;
    
    IF V_CANTIDAD > 0 THEN
    
      PO_ERROR_CODIGO := '4';
      PO_ERROR_MENSAJE := 'All';
       /* CASE PO_ERROR_CODIGO
            WHEN '0' THEN
                PO_ERROR_CODIGO := '7';
                PO_ERROR_MENSAJE := 'ACCBEN-BICINT';
            WHEN '1' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
            WHEN '2' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
            WHEN '3' THEN
                PO_ERROR_CODIGO := '7';
                PO_ERROR_MENSAJE := 'ACCBEN-BICINT';
            WHEN '4' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
            WHEN '5' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
            WHEN '6' THEN
                PO_ERROR_CODIGO := '6';
                PO_ERROR_MENSAJE := 'ALL';
        END CASE;*/
    END IF;
    
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      PO_ERROR_CODIGO := SQLCODE ;
      PO_ERROR_MENSAJE := SQLCODE || ' - ' || SQLERRM;      
    WHEN TOO_MANY_ROWS THEN
      PO_ERROR_CODIGO := SQLCODE ;
      PO_ERROR_MENSAJE := SQLCODE || ' - ' || SQLERRM; 
    WHEN OTHERS THEN
      PO_ERROR_CODIGO := SQLCODE ;
      PO_ERROR_MENSAJE := SQLCODE || ' - ' || SQLERRM; 
      
 END VALIDAR_BANCO_BENEFICIARIO;
/** FIN VALIDAR_BANCO_BENEFICIARIO */
    
     PROCEDURE REGISTRAR_PAGO_MOV_BOFA
        (P_CODBOFA    IN VARCHAR2,
        P_CARTERA                   IN VARCHAR2,
        P_NUMERO_CUENTA_DEBITO      IN VARCHAR2,
        P_NUMCTA_TDC                IN VARCHAR2,
        P_NUMDOC_TDC                IN VARCHAR2,
        P_MONTO                     IN VARCHAR2,
        P_SALDO                     IN VARCHAR2,
        p_resultado                 OUT VARCHAR2) AS
        
        MONEDA_CUENTA       VARCHAR2(20);
        V_CLIENTE       VARCHAR2(40);
        
          V_monto    number := TO_NUMBER (P_MONTO);
  v_SALDO    number := TO_NUMBER (P_SALDO);
        
begin



        SELECT NOM_CLI
        INTO V_CLIENTE
        FROM VBT_TARJETA.MAS_TARJETA
        WHERE CODCAR = P_CARTERA 
        AND  NRO_DOC = P_NUMDOC_TDC
        AND NRO_CTA= P_NUMCTA_TDC;

            BEGIN
            SELECT CODMON
                    INTO MONEDA_CUENTA
                FROM BANCO_VBT.CTAS_CTTES 
                WHERE 
                  CODCOL=P_NUMERO_CUENTA_DEBITO;  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   MONEDA_CUENTA:=''; 
                END;
        
        INSERT INTO MOVIMIENTO_BOFA@BANCO_VBTF (
                    BOF00_CODMOV, 
                    BOF00_CODEMP, 
                    BOF00_CODCAR, 
                    BOF00_CODCOL, 
                    BOF00_CODINST, 
                    CODTIPOMOV, 
                    BOF02_AS_OF_DATE, 
                    BOF02_AS_OF_TIME, 
                    BOF16_AMOUNT, 
                   -- REFBANMOV, 
                    BOF03_CURRENCY_CODE,
                    BENEFICIARY_TYPE_NUMBER,
                    BENEFICIARY_DESCRIPTION,  
                    BENEFICIARIO,
                    
                    OBSERV, 
                    CODEMP_ORIGEN, 
                    CODCAR_ORIGEN, 
                    CODCOL_ORIGEN,
                    CODINST_ORIGEN,
                    CODTIPOMOV_BOFA, 
                    SOURCE, 
                    STATWS,
                    NUMCTABAN,
                    TDC_BALANCE 
                  --  TIPOCREDITO, 
                    
                    ) 
            VALUES (P_CODBOFA,
                    '0000009539',
                    P_CARTERA,
                    P_NUMERO_CUENTA_DEBITO,
                    'CAH',
                    'CCP', 
                    sysdate, 
                    to_char(sysdate, 'hh24mi'), 
                    abs(v_MONTO)*-1, 
                   -- P_STRCODIGOCARTERACREDITO,
             
                    MONEDA_CUENTA,
                    P_NUMDOC_TDC,
                   V_CLIENTE,
                   -- P_STRTXTNOMBREBENEFICIARIO,
                    V_CLIENTE,
                    'CREDIT CARD NUMBER ' || P_NUMCTA_TDC,
                    '0000009539',
                    P_cartera,
                    
                
                    P_NUMERO_CUENTA_DEBITO,
                    'CAH', 
                    'CCP',
                    'ONL',
                    'V',
                    P_NUMCTA_TDC ,
                    v_SALDO
                   -- 'OL2',
                    );  
                    
 --COMMIT;                                                                                                                                                                                                                                                                                                                                                                                                        
            p_resultado :='OK';                                                                                                                                                                                                                                                                                                                                                                                              
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  END   REGISTRAR_PAGO_MOV_BOFA;
  


END TransferenciaHandler;
/


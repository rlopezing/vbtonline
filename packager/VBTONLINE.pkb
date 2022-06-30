CREATE OR REPLACE PACKAGE BODY VBTONLINE_TRANS.VBTONLINE AS
    
   
   
    PROCEDURE vbt_contratos_pr (
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
                                      p_strCmbEstatus           IN OUT VARCHAR2,
                                      p_strTipoContrato         IN VARCHAR2,
                                      vbt_contratos  OUT SYS_REFCURSOR,
                                      p_resultado OUT VARCHAR2,
                                      p_salida OUT VARCHAR2) AS 
                                    --    cs_bac_contratos bac_contratos;  
                                        sqlsquery      VARCHAR2(2000);
                                        sqlele         VARCHAR2(200);
                                        
        begin                         
                                 
        sqlsquery := 'SELECT DISTINCT CON.num_contrato,
         CON.descripcion,
         CON.creado_por,
         TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy HH:MI A.M.''),
         DECODE(CON.status,''0'','''||p_strNuevo||''',''1'','''||p_strActivo||''',''2'','''||p_strCancelado||''',''3'','''||p_strInactivo||''',''4'','''||p_strRechazado||''','''||p_strDesconocido||''') status,
         CON.status CODIGO_ESTATUS,
         TO_CHAR(CON.FECHA_STATUS,''dd/mm/yyyy'') FECHA_STATUS, 
         CON.fecha_creacion,
         CON.tipo_contrato
         FROM vbt_contrato CON
         ,vbt_users USU 
         ,vbt_contrato_carteras CON_CAR                      
         ,vbt_users_contrato USU_CON';
          
        IF p_hdnAccion IS NOT NULL AND p_txtNumeroCartera is not null THEN
            sqlsquery := sqlsquery || ', vbt_contrato_carteras CON_CAR'; 
            sqlele :=  ' AND CON.num_contrato = CON_CAR.num_contrato';
        else
            sqlele :=  ' ';
        END IF;
            sqlsquery := sqlsquery || ' WHERE USU_CON.login = USU.login  AND USU_CON.num_contrato = CON.num_contrato AND con_car.num_contrato = con.num_contrato  AND USU.tipo = ''2''';
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
   

            IF (length(p_strTxtNumeroContrato)=4) THEN
                  IF p_strTxtNumeroContrato IS NOT NULL THEN
                        sqlsquery := sqlsquery || ' AND CON.num_contrato = '''||p_strTxtNumeroContrato||'''';
                    END IF;
            ELSE
                  IF p_strTxtNumeroContrato IS NOT NULL THEN
                        sqlsquery := sqlsquery || ' AND CON.num_contrato LIKE ''%'||p_strTxtNumeroContrato||'%''';
                    END IF;
            END IF;
            
            
            IF p_strTxtUsuario IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND USU.login = '''|| p_strTxtUsuario||'''';
            END IF;
            
            IF p_strCmbCreadoPor IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND CON.creado_por = LOWER('''||p_strCmbCreadoPor||''')';
            END IF;
            
            IF p_strTxtNombreCliente IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND UPPER(USU.nombres) LIKE UPPER(''%'||p_strTxtNombreCliente||'%'')';
            END IF;
            
            IF p_strTxtCIRIFCliente IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND USU.cirif LIKE  ''%'||UPPER(p_strTxtCIRIFCliente)||'%''';
            END IF;
            
            IF p_strCmbEstatus IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND CON.status = '''||p_strCmbEstatus||'''';
            END IF;
            
            IF p_strTxtDesde IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||p_strTxtDesde||''',''dd/mm/yyyy'')';
            END IF;
            
            IF p_strTxtHasta IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND TO_DATE(TO_CHAR(CON.fecha_creacion,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||p_strTxtHasta||''',''dd/mm/yyyy'')';
            END IF;
            
            IF p_txtNumeroCartera IS NOT NULL THEN
                sqlsquery := sqlsquery || '    AND CON_CAR.codcar LIKE    ''' ||'%' ||p_txtNumeroCartera||'%' ||''' ';
            END IF;
            
            IF p_strTipoContrato IS NOT NULL THEN
                sqlsquery := sqlsquery || ' AND CON.TIPO_CONTRATO = '''||p_strTipoContrato||'''';
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
            open  vbt_contratos for sqlsquery;
            --execute immediate sqlsquery into cs_bac_contratos; 
            --p_bac_contratos:= cs_bac_contratos;    
            p_resultado:= 'OK';    
         -- commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_contratos_pr;
        
   
      /******************************************************************************
   NAME:    vbt_BankCodeBuscar_pr      
   PURPOSE: Retorna informacion de la tabla institucion@BANCO_VBTF 
   ******************************************************************************/
       
    PROCEDURE vbt_BankCodeBuscar_pr (p_strCmbTipoCodBanco           IN VARCHAR2, 
                                    p_strTxtCodBanco                 IN VARCHAR2,
                                    p_strTxtNombreBanco             IN VARCHAR2,
                                    p_strCmbPais                      IN VARCHAR2,
                                    p_strCodPais                      IN VARCHAR2,
                                    vbt_BankCodeBuscar  OUT SYS_REFCURSOR,
                                   p_resultado OUT VARCHAR2,
                                   p_salida OUT VARCHAR2,
                                   p_strStatus             IN VARCHAR2) AS 
                                   SQLSTRING  VARCHAR2(1500);
                                   SQLQUERY  VARCHAR2(1500);
                                   WHERESQL VARCHAR2(1500);
                                   agregarAnd BOOLEAN:=FALSE;
    
    begin        
    SQLSTRING :='    SELECT distinct I.CD_TITLE NOMBRE 
        ,I.STATE DIR_L3 
        ,I.CITY DIR_L1 
        ,I.COUNTRY PAIS 
        ,I.COUNTRY_CODE COD_PAIS ';
    
     if p_strCmbTipoCodBanco ='ABA' then
        SQLSTRING := SQLSTRING || ' ,I.ROUTNUM ';
     elsif p_strCmbTipoCodBanco ='SWIFT' then
        SQLSTRING := SQLSTRING || ' ,I.BIC ';
     elsif p_strCmbTipoCodBanco ='CHIPS' then
        SQLSTRING := SQLSTRING || ' ,I.CHIPS_UID ';
     else
        SQLSTRING := SQLSTRING || ' ,I.ROUTNUM  ';
     end if;    
     
     SQLSTRING := SQLSTRING || ' FROM   INSTITUCION@BANCO_VBTF I,
       INSTITUCION_ELIMINADOS@BANCO_VBTF B,
       INSTITUCION_ELIMINADOS@BANCO_VBTF D,
       INSTITUCION_ELIMINADOS@BANCO_VBTF C ';
     
     if p_strCmbTipoCodBanco ='ABA' then
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' I.ROUTCODE =  upper('''|| p_strCmbTipoCodBanco||''') AND I.ROUTNUM IS NOT null';  
            agregarAnd := true;
        end if;
     
         if p_strTxtCodBanco is not null then
            if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND ';
            end if;
            WHERESQL := WHERESQL || ' I.ROUTNUM = upper('''||  p_strTxtCodBanco ||''')';    
            agregarAnd := true;
         end if;
     elsif p_strCmbTipoCodBanco='SWIFT' then
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' ';
        else    
            WHERESQL := WHERESQL || ' AND';
        end if;
        WHERESQL := WHERESQL || ' I.BIC IS NOT null AND I.BIC<>''VBTBKYKY''';
        agregarAnd := true;
        if p_strTxtCodBanco is not null then
            if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND';
            end if;              
            WHERESQL := WHERESQL || ' I.BIC = upper('''||  p_strTxtCodBanco ||''')';          
            agregarAnd := true;
         end if;
     elsif p_strCmbTipoCodBanco='CHIPS' then
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' ';
        else    
            WHERESQL := WHERESQL || ' AND';
        end if;
        WHERESQL := WHERESQL || ' I.CHIPS_UID IS NOT null';
        agregarAnd := true;        
        if p_strTxtCodBanco is not null then
            if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND';
            end if;
            WHERESQL := WHERESQL || ' I.CHIPS_UID = upper('''||  p_strTxtCodBanco ||''')';  
            agregarAnd := true;
         end if;
    else
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' ';
        else    
            WHERESQL := WHERESQL || ' AND';
        end if;
        WHERESQL := WHERESQL || ' I.COUNTRY_CODE = upper(''' || p_strCmbTipoCodBanco ||''') AND I.ROUTNUM IS NOT null AND I.ROUTCODE <> ''ABA''';
        agregarAnd := true;        
        if p_strTxtCodBanco is not null then
            if  agregarAnd =false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND';
            end if;
            WHERESQL := WHERESQL || ' I.ROUTNUM = upper('''||  p_strTxtCodBanco ||''')'; 
            
            agregarAnd := true;
         end if    ;
        
     end if;
     if p_strTxtNombreBanco is not null  then
        if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
        else    
                WHERESQL := WHERESQL || ' AND';
        end if;
                WHERESQL := WHERESQL || ' I.CD_TITLE like '''||'%' ||  p_strTxtNombreBanco ||'%' ||'''' ;   
     end if;        
     if p_strCodPais is not null  AND p_strCodPais <> '-2' then
        if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
        else    
                WHERESQL := WHERESQL || ' AND';
        end if;
                WHERESQL := WHERESQL || ' I.COUNTRY_CODE = '''||p_strCodPais ||'''';    
     end if;
     
     
       IF p_strStatus='A' THEN
             if p_strCmbTipoCodBanco ='ABA' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='SWIFT' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='CHIPS' then
               SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             else
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             end if;
         
      
      ELSE
            if p_strCmbTipoCodBanco ='ABA' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NOT NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='SWIFT' then
                SQLQUERY := SQLQUERY || '  AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS  NOT NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='CHIPS' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NOT NULL  ';
             else
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NOT NULL AND C.TIPO_CODIGO IS NULL ';
             end if;

      END IF;  
     
     
     
     
     if WHERESQL is not null then
        SQLSTRING := SQLSTRING || ' where ';
        SQLSTRING := SQLSTRING || WHERESQL || ' AND I.COUNTRY_CODE NOT IN (SELECT L.COUNTRY_CODE FROM LISTA_NEGRA L) '
         ||         ' AND I.ROUTNUM = B.CODIGO_BANCO(+) AND ''ROUTNUM'' = B.TIPO_CODIGO(+) '
         ||         ' AND I.BIC = C.CODIGO_BANCO(+)     AND ''BIC'' = C.TIPO_CODIGO(+) '
         ||         ' AND I.CHIPS_UID = D.CODIGO_BANCO(+) AND ''CHIPS_UID'' = D.TIPO_CODIGO(+) ';

        
        
        SQLSTRING := SQLSTRING || SQLQUERY || ' ORDER BY I.CD_TITLE ASC ';
        
     end if;
    
     
     p_salida := SQLSTRING;
     open  vbt_BankCodeBuscar for SQLSTRING;
      p_resultado:= 'OK'; 
             EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
            WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                p_resultado:= SUBSTR(SQLERRM,1,300);  
   end vbt_BankCodeBuscar_pr;
   
   /******************************************************************************
   NAME:    vbt_bankCodeType_pr      
   PURPOSE: Retorna informacion de la tabla VBTONLINE_TRANS.ELEMENTOS_TIPOS
   ******************************************************************************/        
    
            
       PROCEDURE vbt_bankCodeType_pr (p_vbt_bankCodeType OUT vbt_bankCodeType, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_bankCodeType vbt_bankCodeType;  
   begin
   
     OPEN cs_vbt_bankCodeType FOR 
        SELECT ET.CODELEMENTO
        , ET.DESCRIPCION 
        FROM VBTONLINE_TRANS.ELEMENTOS_TIPOS ET 
        WHERE ET.CODTIPO = '0000000005'
        ORDER BY ET.ORDEN ASC; 
       
            p_vbt_bankCodeType:= cs_vbt_bankCodeType;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_bankCodeType_pr;
   
    /******************************************************************************
   NAME:    vbt_bankpaises_pr      
   PURPOSE: Retorna informacion de la tabla VPAISES_INSTITUCION@BANCO_VBTF
   ******************************************************************************/        
        
            
       PROCEDURE vbt_bankpaises_pr (p_vbt_bankpaises OUT vbt_bankpaises, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_bankpaises vbt_bankpaises;  
   begin
   
     OPEN cs_vbt_bankpaises FOR 
        SELECT vPAISES.COUNTRY_CODE
        , vPAISES.COUNTRY 
        FROM   VPAISES_INSTITUCION@BANCO_VBTF vPAISES 
        ORDER BY vPAISES.country ASC; 
        
            p_vbt_bankpaises:= cs_vbt_bankpaises;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_bankpaises_pr;
   
                 
          
    /******************************************************************************
   NAME:    vbt_colocasaldos_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1
   ******************************************************************************/        
      
            
       PROCEDURE vbt_colocasaldos_pr (p_strCarteras IN OUT VARCHAR2,
                                        p_vbt_colocasaldos OUT vbt_colocasaldos, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_colocasaldos vbt_colocasaldos; 
                                        SQLSTRING VARCHAR2(2000); 
   begin
   
       
          
           p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
   
   
   --  OPEN cs_vbt_cuentas_edo_cuenta FOR 
   SQLSTRING:='        SELECT A.CODCOL VALUE,
         A.CODCOL DESCRIPCION,
         A.CODCAR CARTERA,
         TO_CHAR(A.FECHCIERRE,''dd/mm/yyyy'') FECHA_CIERRE
         FROM BANCO_VBT.TIME_DEPOSIT_1 A,
         PRODUCCION.CTAPER E 
         WHERE A.CODCAR IN ('''||p_strCarteras||''') 
        AND A.CODEMP = E.CODPER
         AND STATCOL <> ''A''
         AND A.FECHA_VCTO > FECHCIERRE
         ORDER BY A.FECHAPER DESC' ;
          open  p_vbt_colocasaldos for sqlstring;     
    
        p_resultado:= 'OK';    
        commit;  
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colocasaldos_pr;    
 
     /******************************************************************************
   NAME:    vbt_saldoscolo_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1
   ******************************************************************************/        
        
            
       PROCEDURE vbt_saldoscolo_pr (p_strCodigoCartera IN VARCHAR2,
                                        p_strNumeroColocacion IN VARCHAR2,
                                        p_vbt_saldoscolo OUT vbt_saldoscolo, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_saldoscolo vbt_saldoscolo;  
   begin
   
     OPEN cs_vbt_saldoscolo FOR 
          SELECT TD.codcol codigo,
         TD.codmon codigo_moneda,
         to_char(TD.fechaper,'dd/mm/yyyy') fecha_apertura,
         to_char(TD.fechvenc,'dd/mm/yyyy') fecha_vencimiento,
         TD.mtoapert monto_apertura,
         TD.saldo_bloqueado saldo_bloqueado,
         TD.mtovcto monto_vencimiento,
         TD.tasaorg tasa,
         to_char(TD.fechcierre-1,'dd/mm/yyyy') fecha_cierre,
         INITCAP(C.NA) NOMBRE_CLIENTE
         FROM BANCO_VBT.TIME_DEPOSIT_1 TD,
         PRODUCCION.CLIENTES C              
         WHERE TD.CODCAR = p_strCodigoCartera 
         AND TD.codcol =  p_strNumeroColocacion 
         AND C.codcar =  p_strCodigoCartera 
         AND C.flgpri = '-1'            
         ORDER BY TD.fechaper DESC;
        
            p_vbt_saldoscolo:= cs_vbt_saldoscolo;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_saldoscolo_pr;    
  
/******************************************************************************
   NAME:    vbt_colocaope_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1
   ******************************************************************************/        
         
            
       PROCEDURE vbt_colocaope_pr (p_strCodigoCartera IN VARCHAR2,
                                        p_strNumeroColocacion IN VARCHAR2,
                                        p_vbt_colocaope OUT vbt_colocaope, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_colocaope vbt_colocaope;  
   begin
   
     OPEN cs_vbt_colocaope FOR 
          SELECT to_char(OPE.fechlib,'dd/mm/yyyy'),
         to_char(OPE.fechval,'dd/mm/yyyy'),
         OPE.descripcion,
         OPE.referencia,
         OPE.tasaint,
         OPE.monto,
         OPE.beneficiario beneficiario,
         OPE.NUMCTABAN numero_cuenta,
         INITCAP(NOMBAN) banco,
         ' ' banco,
         OPE.observ observacion,
         OPE.codmon moneda,
         OPE.tasacambio
         FROM banco_vbt.movimiento_td_1 OPE
         WHERE OPE.codcol = p_strNumeroColocacion 
         AND OPE.CODCAR = p_strCodigoCartera 
         AND OPE.codtipomov <> 'GR'
         ORDER BY to_date(OPE.fechval,'dd/mm/yyyy') ASC;
        
            p_vbt_colocaope:= cs_vbt_colocaope;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colocaope_pr;    


    /******************************************************************************
   NAME:    vbt_colocaope_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1
   ******************************************************************************/        
      /*  
            
       PROCEDURE vbt_colocaope_pr (p_strCodigoCartera IN VARCHAR2,
                                        p_strNumeroColocacion IN VARCHAR2,
                                        p_vbt_colocaope OUT vbt_colocaope, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_colocaope vbt_colocaope;  
   begin
   
     OPEN cs_vbt_colocaope FOR 
          SELECT to_char(OPE.fechlib,'dd/mm/yyyy'),
         to_char(OPE.fechval,'dd/mm/yyyy'),
         OPE.descripcion,
         OPE.referencia,
         OPE.tasaint,
         OPE.monto,
         OPE.beneficiario beneficiario,
         OPE.NUMCTABAN numero_cuenta,
         INITCAP(NOMBAN) banco,
         ' ' banco,
         OPE.observ observacion,
         OPE.codmon moneda,
         OPE.tasacambio
         FROM banco_vbt.movimiento_td_1 OPE
         WHERE OPE.codcol = p_strNumeroColocacion 
         AND OPE.CODCAR = p_strCodigoCartera 
         AND OPE.codtipomov <> 'GR'
         ORDER BY to_date(OPE.fechval,'dd/mm/yyyy') ASC;
        
            p_vbt_colocaope:= cs_vbt_colocaope;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colocaope_pr;    
*/
             
      /******************************************************************************
   NAME:    vbt_bitacora_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1
   ******************************************************************************/        
    -- TYPE vbt_bitacora  IS REF CURSOR;         
            
       PROCEDURE vbt_bitacora_pr (p_strIdApp IN VARCHAR2,
                                  p_strTxtUsuario IN VARCHAR2,
                                  p_strTxtIdObjeto IN VARCHAR2,
                                  p_strCmbAccion IN VARCHAR2,
                                  p_strCmbObjetoAfectado IN VARCHAR2,
                                  p_strTxtDesde IN VARCHAR2,
                                  p_strTxtHasta IN VARCHAR2,
                                  p_strTxtUltimos IN VARCHAR2,
                                  p_strOrden       IN NUMBER,
                                  p_strIp       IN VARCHAR2,
                                  p_num_contrato       IN VARCHAR2,
                                  p_strComentario       IN VARCHAR2,
                                   p_vbt_bitacora  OUT SYS_REFCURSOR,
                                        p_resultado OUT VARCHAR2,
                                        p_sql OUT VARCHAR2) AS 
                                        SQLSTRING   VARCHAR2(1500);
                                      
   begin
   
            
    SQLSTRING:='SELECT USERNAME, 
         A.DESC_ACTION, 
         O.DESC_OBJECT, 
         L.AFFECTED_OBJECT_ID, 
         TO_CHAR(L.ACTION_DATE,''DD/MM/YYYY HH:MI:SS A.M.''), 
         L.IP, 
         L.COMMENTS, 
         U.NOMBRES 
        FROM   COMMON_LOG L, 
         COMMON_ACTIONS_TO_LOG A, 
         COMMON_OBJECTS_TO_LOG O, 
         COMMON_APPS_TO_LOG AP, 
         VBT_USERS U 
        WHERE  L.ID_ACTION=A.ID_ACTION 
           AND AP.ID_APP= O.ID_APP            
           AND L.ID_OBJECT= O.ID_OBJECT 
           AND L.ID_APP= AP.ID_APP 
           AND L.USERNAME=  U.LOGIN (+)
           AND L.ID_APP = '''|| p_strIdApp ||''''; 
       

            if p_strTxtUsuario is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                SQLSTRING :=  SQLSTRING || '    AND     L.USERNAME = LOWER('''||p_strTxtUsuario||''')'; 
            end if;
            if p_strTxtIdObjeto is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                SQLSTRING :=  SQLSTRING || '    AND     L.AFFECTED_OBJECT_ID = '''|| p_strTxtIdObjeto ||''''; 
            end if;
            if p_strCmbAccion is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND     A.DESC_ACTION = '''|| p_strCmbAccion || ''''; 
            end if;
            if p_strCmbObjetoAfectado is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND     O.DESC_OBJECT = '''||p_strCmbObjetoAfectado ||''''; 
            end if;
            if p_strTxtDesde is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND     TO_DATE(TO_CHAR(L.ACTION_DATE,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''|| p_strTxtDesde|| ''',''dd/mm/yyyy'') '; 
            end if;
            if p_strTxtHasta is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND     TO_DATE(TO_CHAR(L.ACTION_DATE,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''|| p_strTxtHasta ||''',''dd/mm/yyyy'') '; 
            end if;
            if p_strTxtUltimos is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND     L.ACTION_DATE >=  SYSDATE- '''|| p_strTxtUltimos||'''  '; 
            end if;
            
            if p_strIp is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND    L.IP = '''|| p_strIp ||'''  '; 
            end if;
            
            
            if p_num_contrato is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING || '    AND    L.NUM_CONTRATO = '''|| p_num_contrato ||'''  '; 
            end if;
            
            if  p_strComentario is null then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                    SQLSTRING :=  SQLSTRING  ||  ' AND UPPER(L.COMMENTS) LIKE UPPER(''%'||p_strComentario||'%'')';
            end if;
            
           
         
           
            
            
            
            
            if p_strOrden <> '5' then
                SQLSTRING :=  SQLSTRING || ' ORDER BY ''|| strOrden ||'' ASC';
            else
                SQLSTRING :=  SQLSTRING || ' ORDER BY L.ACTION_DATE DESC ';
            end if;
            
            p_sql := SQLSTRING;
            open  p_vbt_bitacora for SQLSTRING;
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_bitacora_pr;    
   
    /******************************************************************************
   NAME:    vbt_calendario_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1
   ******************************************************************************/        
      
            
       PROCEDURE vbt_calendario_pr (p_mes IN NUMBER,
                                        p_año IN NUMBER,
                                        p_fin_mes IN NUMBER,
                                        p_vbt_calendario OUT vbt_calendario, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_calendario vbt_calendario;  
   begin
   
     OPEN cs_vbt_calendario FOR 
          SELECT TO_CHAR(fer.DIAFER,'dd') DIA 
          ,INITCAP(fer.DESCFER) DESCRIPCION 
        FROM varios.ctafer@VARIOS cal 
       ,varios.tabfer@VARIOS fer 
        WHERE fer.CODCAL = '0000009539' 
       AND TO_CHAR(cal.ANO,'yyyy') = p_año  
       AND cal.CODCAL = fer.CODCAL 
       AND fer.DIAFER >= TO_DATE('01/' || (p_mes + 1) || '/' || p_año || '','dd/mm/yyyy') 
       AND fer.DIAFER <= TO_DATE('' || p_fin_mes || '/' || (p_mes + 1) || '/' || p_año  ||'','dd/mm/yyyy');
        
            p_vbt_calendario:= cs_vbt_calendario;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);  
      --p_resultado:=  '01/' || (p_mes + 1) || '/' || p_año || '';    
   
   end vbt_calendario_pr;    
   
   
   
   
   /******************************************************************************
   NAME:    vbt_colobloquedepo_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1 bloqueos cartera
   ******************************************************************************/        
    
            
       PROCEDURE vbt_colobloquedepo_pr (p_strCarteras IN VARCHAR2,
                                        p_vbt_colobloquedepo OUT vbt_colobloquedepo, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_colobloquedepo vbt_colobloquedepo;  
   begin
   
     OPEN cs_vbt_colobloquedepo FOR 
         SELECT  A.CODCOL VALUE, 
        A.CODCOL DESCRIPCION,
        A.CODCAR CARTERA, 
        TO_CHAR(A.FECHCIERRE-1,'dd/mm/yyyy') FECHA_CIERRE 
        FROM BANCO_VBT.TIME_DEPOSIT_1 A, 
        PRODUCCION.CTAPER E 
        WHERE A.CODCAR IN (p_strCarteras) 
        AND A.CODEMP=E.CODPER 
        AND STATCOL <> 'A' 
          AND A.FECHA_VCTO > FECHCIERRE 
        ORDER BY A.FECHAPER DESC;
        
            p_vbt_colobloquedepo:= cs_vbt_colobloquedepo;    
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colobloquedepo_pr;    
   
        /******************************************************************************
   NAME:    vbt_colobloquemovi_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.TIME_DEPOSIT_1 bloqueos cartera
   ******************************************************************************/        
                
       PROCEDURE vbt_colobloquemovi_pr (p_strNumeroColocacion IN VARCHAR2,
                                        p_strCodigoCartera IN VARCHAR2,
                                        p_strCmbBuscar        IN VARCHAR2,
                                        p_strTxtFechaDesde IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_vbt_colobloquemovi OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2) AS 
                                        sqlstring varchar2(1000);
   begin
   
        sqlstring := ' SELECT   TO_CHAR(M.FECHLIB,''dd/mm/yyyy'') FECHA_OPERACION, 
            TO_CHAR(M.FECHVAL,''dd/mm/yyyy'') FECHA_VALOR, 
            INITCAP(M.DESCRIPCION) DESCRIPCION, 
            M.MONTO MONTO_BLOQUEADO, 
            INITCAP(M.OBSERV) BENEFICIARIO_CREDITO, 
            INITCAP(C.NOMBCLS) TIPO_CREDITO,  
            M.NUMCTABAN NUMERO_CUENTA,  
            M.REFERENCIA REFERENCIA,  
            M.CODMON MONEDA,  
            M.TASACAMBIO TASA_REFERENCIA, 
            INITCAP(M.BENEFICIARIO) BENEFICIARIO_BLOQUEO  
            FROM     BANCO_VBT.MOVIMIENTO_TD_1 M,
             VARIOS.CTACLS C 
            WHERE  M.CODCOL = '''||p_strNumeroColocacion ||'''
             AND M.CODCAR = '''||p_strCodigoCartera ||'''
             AND M.CODTIPOMOV =''GR'' 
             AND M.CODCLS = C.CODCLS(+) 
             AND C.TIPCLS(+) = ''04''';
        
        if p_strCmbBuscar = 'vigentes' then
          sqlstring := sqlstring || '  AND M.STATDIF = ''G''';
        elsif p_strCmbBuscar = 'vencidos' then
            sqlstring := sqlstring || '  AND M.STATDIF = ''L''';
        elsif p_strCmbBuscar = 'rangoFechas' then
            if p_strTxtFechaDesde is not null then
                sqlstring := sqlstring || '  AND M.FECHLIB >= TO_DATE('''|| p_strTxtFechaDesde||''',''dd/mm/yyyy'') ';
            end if;
            if p_strTxtFechaHasta is not null then
                sqlstring := sqlstring || '  AND M.FECHLIB <= TO_DATE('''|| p_strTxtFechaHasta||''',''dd/mm/yyyy'') ';
            end if;
        end if;
            sqlstring := sqlstring || ' ORDER BY TO_DATE(M.FECHLIB,''dd/mm/yyyy'') DESC ';
            
        open  p_vbt_colobloquemovi for sqlstring;   
          p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colobloquemovi_pr;    
   
       PROCEDURE vbt_portafo_conso_user_pr (p_strLogin IN VARCHAR2,
                                        p_vbt_portafo_conso_user OUT vbt_portafo_conso_user, 
                                        p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_portafo_conso_user vbt_portafo_conso_user;  
   begin
   
     OPEN cs_vbt_portafo_conso_user FOR 
        
         SELECT CC.CODCAR 
    FROM   VBT_USERS_CONTRATO UC, 
    VBT_CONTRATO_CARTERAS CC 
    WHERE  UC.LOGIN = p_strLogin 
    AND    UC.NUM_CONTRATO = CC.NUM_CONTRATO;
        
    p_vbt_portafo_conso_user:= cs_vbt_portafo_conso_user;  
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_portafo_conso_user_pr;    
   
   /******************************************************************************
   NAME:    vbt_porta_con_saldo_pr      
   PURPOSE: Retorna informacion saldo facturado de la tabla EDO_CTA_TDC
   ******************************************************************************/        

        
       PROCEDURE vbt_porta_con_saldo_pr (p_num_cta IN VARCHAR2,
                                            p_codproserv IN VARCHAR2,
                                            p_vbt_porta_con_saldo OUT vbt_porta_con_saldo, 
                                            p_resultado OUT VARCHAR2) AS 
                                        cs_vbt_porta_con_saldo vbt_porta_con_saldo;  
   begin
   
     OPEN cs_vbt_porta_con_saldo FOR 
        
        SELECT NVL(saldo_actual,'0') saldo_actual_facturado 
        FROM   VBT_TARJETA.EDO_CTA_TDC 
        WHERE  num_cta    = p_num_cta 
        AND    codproserv = p_codproserv 
        AND    mes = (SELECT to_char(max(to_date(ECT.MES,'mm/yyyy')),'mm/yyyy') 
        FROM     VBT_TARJETA.EDO_CTA_TDC ECT);         
        
    p_vbt_porta_con_saldo:= cs_vbt_porta_con_saldo;  
            p_resultado:= 'OK';    
          commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_porta_con_saldo_pr;         

    /******************************************************************************
   NAME:    vbt_porta_con_movi_pr      
   PURPOSE: Retorna informacion delos movimientos del portafolio consolidado
   ******************************************************************************/        
                
   PROCEDURE vbt_porta_con_movi_pr (p_strCodigoCarterai IN VARCHAR2,
                                    p_ver_opcion IN VARCHAR2,
                                    p_login IN VARCHAR2,
                                    p_vbt_porta_con_movi OUT SYS_REFCURSOR, 
                                    p_resultado OUT VARCHAR2) AS 
    sqlstring varchar2(1000);
    l_grupo varchar2(20);
    accion varchar2(20);
    error_06535 exception;   
    pragma exception_init(error_06535,-06535);    
                                        
   begin
    
    if p_strCodigoCarterai is not null then

        select codgrp into l_grupo 
        FROM USUGRP_v1 
        WHERE login = p_login;



        if p_ver_opcion = 'verCuentas' then
        
           BEGIN
                SELECT substr(CCO.TIPACC,1,1) accion
                INTO accion
                FROM    usrciaopc_v1 cco 
                WHERE 
                  cco.codsis = 'ONLINE'
                  AND    cco.codcia ='VBT' 
                  AND    cco.login= p_login
                  AND cco.codopc = '0000000009'; 
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   SELECT substr(coalesce(CCO.TIPACC ,gco.tipacc),1,1) accion
                INTO accion
                FROM      grpciaopc_v1 gco, usrciaopc_v1 cco 
                WHERE  gco.codgrp = l_grupo  
                  AND    gco.codsis = 'ONLINE'
                  AND    gco.codcia ='VBT' 
                  AND    GCO.CODOPC=CCO.CODOPC(+) 
                  AND    cco.login(+)= p_login
                  AND gco.codopc = '0000000009'; 
                    
                END ;

            if accion='1' THEN
                sqlstring :='SELECT A.CODCOL CODIGO,
                            A.CODMON MONEDA, 
                            A.SALDO_ACTUAL SALDO_ACTUAL
                            FROM BANCO_VBT.CTAS_CTTES A, 
                            PRODUCCION.CTAPER E 
                            WHERE A.CODCAR = '''||p_strCodigoCarterai ||'''
                            AND A.CODEMP = E.CODPER ';
             END IF;
        elsif p_ver_opcion = 'verColocaciones' then
         
              
              
            BEGIN
                   SELECT substr(CCO.TIPACC,1,1) accion
                        INTO accion
                        FROM    usrciaopc_v1 CCO 
                        WHERE  
                          CCO.codsis = 'ONLINE'
                          AND    CCO.codcia ='VBT' 
                          AND    CCO.login= p_login
                          AND CCO.codopc = '0000000013';     
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    SELECT substr(coalesce(CCO.TIPACC ,gco.tipacc),1,1) accion
                        INTO accion
                        FROM      grpciaopc_v1 gco, usrciaopc_v1 cco 
                        WHERE  gco.codgrp = l_grupo  
                          AND    gco.codsis = 'ONLINE'
                          AND    gco.codcia ='VBT' 
                          AND    GCO.CODOPC=CCO.CODOPC(+) 
                          AND    cco.login(+)= p_login
                          AND gco.codopc = '0000000013'; 
                END ;

            if accion='1' THEN
                sqlstring :='SELECT A.CODCOL CODIGO,
                            A.CODMON MONEDA, 
                            A.MTOAPERT SALDO_ACTUAL 
                            FROM BANCO_VBT.TIME_DEPOSIT_1 A, 
                            PRODUCCION.CTAPER E 
                            WHERE A.CODCAR = '''||p_strCodigoCarterai ||'''
                            AND STATCOL <> ''A'' 
                            AND A.FECHA_VCTO > FECHCIERRE
                            AND A.CODEMP = E.CODPER ';
            END IF;
        elsif p_ver_opcion = 'verFondos'  then
   
             BEGIN
                  SELECT substr(cco.TIPACC,1,1) accion
                    INTO accion
                    FROM   usrciaopc_v1 cco 
                    WHERE  
                             cco.codsis = 'ONLINE'
                      AND    cco.codcia ='VBT' 
                      AND    cco.login= p_login
                      AND cco.codopc = '0000000018'; 
             EXCEPTION
                WHEN NO_DATA_FOUND THEN
                   SELECT substr(coalesce(CCO.TIPACC ,gco.tipacc),1,1) accion
                        INTO accion
                        FROM      grpciaopc_v1 gco, usrciaopc_v1 cco 
                        WHERE  gco.codgrp = l_grupo  
                          AND    gco.codsis = 'ONLINE'
                          AND    gco.codcia ='VBT' 
                          AND    GCO.CODOPC=CCO.CODOPC(+) 
                          AND    cco.login(+)= p_login
                          AND gco.codopc = '0000000018';    
                END ;  
              
            if accion='1' THEN
            
                sqlstring :='SELECT INITCAP(A.NA) NOMBRE_FONDO,
                            A.CODMON MONEDA, 
                            A.TOTAL TOTAL_MONEDA, 
                            A.CODEMP CODIGO 
                            FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 A, 
                            PRODUCCION.CTAPER E 
                            WHERE A.CODCAR = '''|| p_strCodigoCarterai ||'''
                            AND A.CODEMP NOT IN (SELECT CODEMP FROM OTRAS_INVERSIONES@FONDOMUTUAL_VBT WHERE APLICACION=''ONLINE'' ) 
                            AND A.CODEMP = E.CODPER ';
            
            END IF;
        elsif p_ver_opcion = 'verOtrasInv'  then
          
              
              
              BEGIN
                SELECT substr(CCO.TIPACC,1,1) accion
                    INTO accion
                    FROM   usrciaopc_v1 cco 
                    WHERE  
                      cco.codsis = 'ONLINE'
                      AND    cco.codcia ='VBT' 
                      AND    cco.login= p_login
                      AND cco.codopc = '0000000048'; 
                EXCEPTION
                WHEN NO_DATA_FOUND THEN
                     SELECT substr(coalesce(CCO.TIPACC ,gco.tipacc),1,1) accion
                        INTO accion
                        FROM      grpciaopc_v1 gco, usrciaopc_v1 cco 
                        WHERE  gco.codgrp = l_grupo  
                          AND    gco.codsis = 'ONLINE'
                          AND    gco.codcia ='VBT' 
                          AND    GCO.CODOPC=CCO.CODOPC(+) 
                          AND    cco.login(+)= p_login
                          AND gco.codopc = '0000000048';   
                END ; 
              
            if accion='1' THEN
                sqlstring :='SELECT INITCAP(A.NA) NOMBRE_FONDO,
                            A.CODMON MONEDA, 
                            A.TOTAL TOTAL_MONEDA, 
                            A.CODEMP CODIGO 
                            FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 A, 
                            PRODUCCION.CTAPER E 
                            WHERE A.CODCAR = '''|| p_strCodigoCarterai ||'''
                            AND A.CODEMP IN (SELECT CODEMP FROM OTRAS_INVERSIONES@FONDOMUTUAL_VBT WHERE APLICACION=''ONLINE'' and visible=''S'') 
                            AND A.CODEMP = E.CODPER '; 
           end if;                 
        elsif p_ver_opcion = 'verTarjetas' then        
           
              
              BEGIN
               SELECT substr(CCO.TIPACC,1,1) accion
                    INTO accion
                    FROM   usrciaopc_v1 cco 
                    WHERE  
                             cco.codsis = 'ONLINE'
                      AND    cco.codcia ='VBT' 
                      AND    cco.login= p_login
                      AND cco.codopc = '0000000034'; 
              
                EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    SELECT substr(coalesce(CCO.TIPACC ,gco.tipacc),1,1) accion
                        INTO accion
                        FROM      grpciaopc_v1 gco, usrciaopc_v1 cco 
                        WHERE  gco.codgrp = l_grupo  
                          AND    gco.codsis = 'ONLINE'
                          AND    gco.codcia ='VBT' 
                          AND    GCO.CODOPC=CCO.CODOPC(+) 
                          AND    cco.login(+)= p_login
                          AND gco.codopc = '0000000034'; 
                            
                END ; 
  
            if accion='1' THEN
                    sqlstring :='SELECT DECODE(B.COD_TPO_TDC,''V'',''VISA'',''MC'') ||'' **** - **** - **** - '' || SUBSTR(CC.NRO_PLASTICO,17,4) NRO_PLASTICO, 
                                CC.COD_CAR CARTERA, 
                                CC.NRO_DOC NRO_CTA 
                                FROM VBT_TARJETA.CTACAR_TEMP CC,  
                                VBT_TARJETA.BINES_TDC B 
                                WHERE CC.COD_CAR = '''|| p_strCodigoCarterai ||'''
                                AND CC.TIPO_DOC = ''P'' 
                                AND (CC.SIT_DOC = ''1'' OR CC.SIT_DOC = ''2'')                   
                                AND SUBSTR(CC.NRO_PLASTICO,5,6) = B.BIN 
                                ORDER BY CC.NRO_PLASTICO ASC ';
            end if;
        end if;
    
    
    end if;

    open  p_vbt_porta_con_movi for sqlstring;   
    
     p_resultado:= 'OK';    
          commit; 
 
 
    EXCEPTION 
 
     WHEN error_06535 THEN
      p_resultado:='NO OK';          
   
   end vbt_porta_con_movi_pr;    
   
    /******************************************************************************
   NAME:    vbt_cuentas_bloqueo_ctas_pr      
   PURPOSE: Retorna informacion de cuentas bloqueo
   ******************************************************************************/        
            
       PROCEDURE vbt_cuentas_bloqueo_ctas_pr (p_strCarteras IN VARCHAR2,
                                         p_vbt_cuentas_bloqueo_ctas OUT vbt_cuentas_bloqueo_ctas, 
                                         p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_cuentas_bloqueo_ctas vbt_cuentas_bloqueo_ctas;  
   begin
   
     OPEN cs_vbt_cuentas_bloqueo_ctas FOR 
        
            SELECT A.CODCOL VALUE, 
        A.TIPOCOL TIPO,
        A.CODCAR CARTERA, 
        TO_CHAR(A.FECHCIERRE-1,'dd/mm/yyyy') FECHA_CIERRE 
            FROM BANCO_VBT.CTAS_CTTES A, 
        PRODUCCION.CTAPER E 
        WHERE A.CODCAR IN (p_strCarteras) 
        AND A.CODEMP=E.CODPER 
            ORDER BY A.FECHAPER DESC ;
        
            p_vbt_cuentas_bloqueo_ctas:= cs_vbt_cuentas_bloqueo_ctas;  
        p_resultado:= 'OK';    
        commit;    
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentas_bloqueo_ctas_pr;    
   
    /******************************************************************************
   NAME:    vbt_cuentas_bloqueo_vigen_pr      
   PURPOSE: Retorna informacion delos movimientos del portafolio consolidado
   ******************************************************************************/        
                
       PROCEDURE vbt_cuentas_bloqueo_vigen_pr (p_strCmbBuscar IN VARCHAR2,
                                        p_strNumeroCuenta IN VARCHAR2,
                                        p_strTxtFechaDesde  IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_vbt_cuentas_bloqueo_vigen OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2) AS 
                                        sqlstring varchar2(1500);
   begin
   
        sqlstring:='    SELECT   TO_CHAR(M.FECHLIB,''dd/mm/yyyy'') FECHA_OPERACION, 
            TO_CHAR(M.FECHVAL,''dd/mm/yyyy'') FECHA_VALOR, 
            INITCAP(M.DESCRIPCION) DESCRIPCION, 
            M.MONTO MONTO_BLOQUEADO, 
            INITCAP(M.OBSERV) BENEFICIARIO_CREDITO, 
            INITCAP(C.NOMBCLS) TIPO_CREDITO,  
            M.NUMCTABAN NUMERO_CUENTA,  
            M.REFERENCIA REFERENCIA,  
            M.CODMON MONEDA,  
            M.TASACAMBIO TASA_REFERENCIA, 
            INITCAP(M.BENEFICIARIO) BENEFICIARIO_BLOQUEO 
            FROM     BANCO_VBT.MOVIMIENTO_CTAS_CTTES_1 M,
            VARIOS.CTACLS C 
            WHERE    M.CODCOL='''||p_strNumeroCuenta ||''' 
             AND M.CODTIPOMOV =''GR'' 
             AND M.CODCLS = C.CODCLS(+) 
             AND C.TIPCLS(+) = ''04'''; 
                    
          if    p_strCmbBuscar ='vigentes' then
            sqlstring:= sqlstring || '   AND M.STATDIF = ''G''';
          elsif p_strCmbBuscar ='vencidos' then
            sqlstring:= sqlstring || '   AND M.STATDIF = ''L''';
          elsif p_strCmbBuscar ='rangoFechas' then
           if p_strTxtFechaDesde is not null then
            sqlstring:= sqlstring || '    AND M.FECHLIB >= TO_DATE('''|| p_strTxtFechaDesde ||''',''dd/mm/yyyy'')';    
           end if;
           if p_strTxtFechaHasta is not null then
            sqlstring:= sqlstring || '    AND M.FECHLIB <= TO_DATE('''|| p_strTxtFechaHasta||''',''dd/mm/yyyy'') ';    
           end if;
            end if;
                   
           sqlstring:= sqlstring || '  ORDER BY TO_DATE(M.FECHLIB,''dd/mm/yyyy'') DESC';
        
        open  p_vbt_cuentas_bloqueo_vigen for sqlstring;   
   
   
    p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentas_bloqueo_vigen_pr;      
   
   /******************************************************************************
   NAME:    vbt_cuentas_edo_cuenta_pr      
   PURPOSE: Retorna informacion de cuentas estado de cuenta
   ******************************************************************************/        
                
       PROCEDURE vbt_cuentas_edo_cuenta_pr (p_strCarteras             IN  OUT VARCHAR2,
                                            p_strCuentaCorriente     IN VARCHAR2,
                                            p_strCuentaAhorro         IN VARCHAR2,
                                            p_strDesconocido         IN VARCHAR2,
                                            p_vbt_cuentas_edo_cuenta OUT SYS_REFCURSOR, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_cuentas_edo_cuenta vbt_cuentas_edo_cuenta;  
                      SQLSTRING VARCHAR2(2000);
   begin
   
   
 
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
   
   
   --  OPEN cs_vbt_cuentas_edo_cuenta FOR 
   SQLSTRING:='        SELECT A.CODCOL VALUE, 
        DECODE(A.TIPOCOL,''CAH'','''||p_strCuentaCorriente||''' ,''CCT'','''||p_strCuentaAhorro||''','''||p_strDesconocido||''') tipo,
        A.CODCAR CARTERA, 
        A.FECHCIERRE FECHA_CIERRE 
        FROM BANCO_VBT.CTAS_CTTES A, 
        PRODUCCION.CTAPER E 
        WHERE A.CODCAR IN ('''||p_strCarteras||''') 
        AND A.CODEMP=E.CODPER
        ORDER BY A.FECHAPER DESC' ;
          open  p_vbt_cuentas_edo_cuenta for sqlstring;     
    --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentas_edo_cuenta_pr;    
   
    /******************************************************************************
   NAME:    vbt_cuentas_edo_mesano_pr      
   PURPOSE: Retorna informacion de cuentas estado de cuenta mes año
   ******************************************************************************/        
        
       PROCEDURE vbt_cuentas_edo_mesano_pr (p_vbt_cuentas_edo_mesano OUT vbt_cuentas_edo_mesano, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_cuentas_edo_mesano vbt_cuentas_edo_mesano;  
   begin
   
     OPEN cs_vbt_cuentas_edo_mesano FOR 
        
                SELECT to_char(MAX(CAB.FECHA_FIN_MES),'mm') MESMAX, 
                to_char(MAX(CAB.FECHA_FIN_MES),'YYYY') ANOMAX  
                FROM BANCO_VBT.EDO_CABECERA CAB 
                WHERE CAB.CODEMP  = '0000009539' 
                AND CAB.CODINST = 'CAH';
        
            p_vbt_cuentas_edo_mesano:= cs_vbt_cuentas_edo_mesano;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentas_edo_mesano_pr;    
   
  
   /******************************************************************************
   NAME:    vbt_cuentas_edo_cabecera_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.EDO_CABECERA
   ******************************************************************************/        
                
       PROCEDURE vbt_cuentas_edo_cabecera_pr (p_strNumeroCuenta IN VARCHAR2,
                                            p_strCodigoCartera IN VARCHAR2,
                                            p_strCmbMes IN VARCHAR2,
                                            p_strTxtAño IN VARCHAR2,
                                            p_vbt_cuentas_edo_cabecera OUT vbt_cuentas_edo_cabecera, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_cuentas_edo_cabecera vbt_cuentas_edo_cabecera;  
   begin
   
     OPEN cs_vbt_cuentas_edo_cabecera FOR 
        
        SELECT INITCAP(CAB.NA_PRINCIPAL) cliente_principal,
        CAB.CODMON moneda,
        CAB.GARANTIAB bloqueado,
        CAB.DIFERIDOB diferido,
        CAB.SALDISPONIBLE disponible,
        (CAB.GARANTIAB+CAB.DIFERIDOB+CAB.SALDISPONIBLE) saldo_actual,
        CAB.SALMESANTE saldo_anterior
        FROM BANCO_VBT.EDO_CABECERA CAB
        WHERE CAB.CODEMP  = '0000009539'
        AND CAB.CODCOL  = p_strNumeroCuenta 
        AND CAB.CODINST = 'CAH'
        AND CAB.CODCAR  = p_strCodigoCartera 
        AND to_char(CAB.FECHA_FIN_MES,'MMYYYY')   = LPAD(p_strCmbMes || p_strTxtAño, 6, '0');
        
        p_vbt_cuentas_edo_cabecera:= cs_vbt_cuentas_edo_cabecera;
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentas_edo_cabecera_pr;    
  
  /******************************************************************************
   NAME:    vbt_cuentas_edo_detalle_pr      
   PURPOSE: Retorna informacion de la tabla BANCO_VBT.EDO_CABECERA
   ******************************************************************************/        
    
            
       PROCEDURE vbt_cuentas_edo_detalle_pr (p_strNumeroCuenta IN VARCHAR2,
                                            p_strCodigoCartera IN VARCHAR2,
                                            p_strCmbMes IN VARCHAR2,
                                            p_strTxtAño IN VARCHAR2,
                                            p_vbt_cuentas_edo_detalle OUT vbt_cuentas_edo_detalle, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_cuentas_edo_detalle vbt_cuentas_edo_detalle;  
   begin
   
     OPEN cs_vbt_cuentas_edo_detalle FOR 
        
        SELECT to_char(DET.MOVFECHOPE,'DD/MM/YYYY') FECHAOPERACION, 
            to_char(DET.MOVFECHVAL,'DD/MM/YYYY') FECHAVALOR, 
            DET.DESCMOV, 
            DET.REFBANMOV REFERENCIA, 
            DET.MTOMOV 
            FROM BANCO_VBT.EDO_DETALLE DET 
            WHERE DET.CODEMP  = '0000009539' 
            AND DET.CODCOL  = p_strNumeroCuenta 
            AND DET.CODINST = 'CAH' 
            AND to_char(DET.MOVFECHOPE,'MMYYYY') = LPAD(p_strCmbMes || p_strTxtAño, 6, '0') 
            ORDER BY DET.MOVFECHOPE,DET.MOVFECHVAL,DET.DESCMOV ASC, DET.MTOMOV DESC;
        
        p_vbt_cuentas_edo_detalle:= cs_vbt_cuentas_edo_detalle;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentas_edo_detalle_pr;    
  
  
  /******************************************************************************
   NAME:    vbt_fondos_blo_cartera_pr   
   PURPOSE: Retorna informacion de la tabla FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1
   ******************************************************************************/        
                
       PROCEDURE vbt_fondos_blo_cartera_pr (p_strCarteras IN VARCHAR2,
                                            p_vbt_fondos_blo_cartera OUT vbt_fondos_blo_cartera, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_fondos_blo_cartera vbt_fondos_blo_cartera;  
   begin
   
     OPEN cs_vbt_fondos_blo_cartera FOR 
        
       SELECT A.CODEMP EMPRESA, 
        INITCAP(A.NA) NOMBRE_FONDO,
        A.CODCAR CARTERA, 
        A.FECHCIERRE FECHA_CIERRE 
        FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 A, 
        PRODUCCION.CTAPER E 
        WHERE A.CODCAR IN (p_strCarteras) 
        AND A.CODEMP NOT IN (SELECT CODEMP FROM OTRAS_INVERSIONES@FONDOMUTUAL_VBT WHERE APLICACION='ONLINE' and visible='N')                 
        AND A.CODEMP = E.CODPER ;
    
        p_vbt_fondos_blo_cartera:= cs_vbt_fondos_blo_cartera;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_fondos_blo_cartera_pr;    
  
  
  /******************************************************************************
   NAME:    vbt_fondos_blo_movimi_pr      
   PURPOSE: Retorna informacion de los movimientos de inversion
   ******************************************************************************/        
                
       PROCEDURE vbt_fondos_blo_movimi_pr (p_strCmbBuscar  IN VARCHAR2,
                                        p_strCodigoFondo   IN VARCHAR2,
                                        p_strCodigoCartera IN VARCHAR2,
                                        p_strTxtFechaDesde IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_vbt_fondos_blo_movimi OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2) AS 
                                        sqlstring varchar2(1500);
   begin
   
         sqlstring:='SELECT   TO_CHAR(M.FECHOPE,''dd/mm/yyyy'') FECHA_OPERACION, 
            TO_CHAR(M.FECHVAL,''dd/mm/yyyy'') FECHA_VALOR, 
            INITCAP(M.DESCRIPCION) DESCRIPCION, 
            M.MTOINV MONTO_INVERSION, 
            M.CLIENTE CLIENTE,  
            M.TIPO TIPO, 
             M.TASA_REFERENCIAL, 
             M.REFERENCIA, 
             C.NOMBCLS TIPO_CREDITO, 
             M.BENEFICIARIO_CREDITO, 
             M.BENEFICIARIO_GARANTIA, 
             M.NRO_CUENTA_CLIENTE 
           FROM     FONDOMUTUAL_VBT.MOVIMIENTO_FMUT_1 M 
           ,VARIOS.CTACLS C 
           WHERE    M.CODEMP='''|| p_strCodigoFondo||'''
            AND M.CODCAR = '''||p_strCodigoCartera ||''' 
            AND M.TIPOPE = ''003'' 
             AND M.TIPO_CREDITO = C.CODCLS(+)';
                    
           
            if    p_strCmbBuscar ='vigentes' then
                sqlstring:= sqlstring || '   AND M.STATOPE = ''G''';
            elsif p_strCmbBuscar ='vencidos' then
                sqlstring:= sqlstring || '   AND M.STATOPE = ''L''';
            elsif p_strCmbBuscar ='rangoFechas' then
                 if p_strTxtFechaDesde is not null then
                    sqlstring:= sqlstring || '    AND M.FECHOPE >= TO_DATE('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'')';    
                 end if;
                 if p_strTxtFechaHasta is not null then
                    sqlstring:= sqlstring || '    AND M.FECHOPE <= TO_DATE('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'')';    
                 end if;
            end if;
                   
           sqlstring:= sqlstring || '  ORDER BY TO_DATE(M.FECHOPE,''dd/mm/yyyy'') DESC ';
        
        open  p_vbt_fondos_blo_movimi for sqlstring;   
   
   
    p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_fondos_blo_movimi_pr;        
    
   /******************************************************************************
   NAME:    vbt_fondos_sal_cartera_pr   
   PURPOSE: Retorna informacion de la tabla FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1
   ******************************************************************************/        
                
       PROCEDURE vbt_fondos_sal_cartera_pr (p_strCarteras IN OUT VARCHAR2,
                                            p_vbt_fondos_sal_cartera OUT vbt_fondos_sal_cartera, 
                                            p_resultado OUT VARCHAR2,
                                            p_sql OUT VARCHAR2) AS 
                                            cs_vbt_fondos_sal_cartera vbt_fondos_sal_cartera; 
                                             SQLSTRING VARCHAR2(2000);
   begin
   
   --p_strCarteras := trim(substr(p_strCarteras,2,100));
 
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
   
   
   --  OPEN cs_vbt_cuentas_edo_cuenta FOR 
   SQLSTRING:='        SELECT A.CODEMP EMPRESA, 
         INITCAP(A.NA) NOMBRE_FONDO,
         A.CODCAR CARTERA, 
         A.FECHCIERRE FECHA_CIERRE ,
         A.CODMON MONEDA,
         DECODE(P.VALOR,''8'',P.VALOR,''6'') DECIMALES
         FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 A,  PRODUCTO_EMPRESA@FONDOMUTUAL_VBT P,
         PRODUCCION.CTAPER E 
         WHERE A.CODCAR IN ('''||p_strCarteras||''') 
        AND A.CODEMP NOT IN (SELECT CODEMP FROM OTRAS_INVERSIONES@FONDOMUTUAL_VBT WHERE APLICACION=''ONLINE'' )                 
         AND A.CODEMP = E.CODPER
         AND A.CODEMP = P.CODEMP
         AND P.CODPRODINT = ''0000000025''' ;
   p_sql := SQLSTRING;      
          open  p_vbt_fondos_sal_cartera for sqlstring;     
    --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;  
        p_resultado:= 'OK'; 
     
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_fondos_sal_cartera_pr;    
  
   /******************************************************************************
   NAME:    vbt_fondos_sal_saldos_pr   
   PURPOSE: Retorna informacion de la tabla FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1
   ******************************************************************************/        
                
       PROCEDURE vbt_fondos_sal_saldos_pr (p_strCodigoCartera IN VARCHAR2,
                                            p_strCodigoFondo IN VARCHAR2,
                                            p_vbt_fondos_sal_saldos OUT vbt_fondos_sal_saldos, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_fondos_sal_saldos vbt_fondos_sal_saldos;  
   begin
   
     OPEN cs_vbt_fondos_sal_saldos FOR 
        
          SELECT A.NA NOMBRE_FONDO,
         A.CODMON MONEDA,
         A.SALDO VALOR_ACTUAL,
         A.BLOQ BLOQUEADO,
         A.SALDO_DISP DISPONIBLE,
         A.PRECIO VUI,
         A.TOTAL TOTAL_MONEDA,
         A.SALDO_TRANSITO SALDO_TRANSITO,
         A.FECHCIERRE FECHA_CIERRE,
         INITCAP(C.NA) NOMBRE_CLIENTE
         FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 A,
         PRODUCCION.CTAPER E,
         PRODUCCION.CLIENTES C
         WHERE A.CODCAR = p_strCodigoCartera 
         AND A.CODEMP = p_strCodigoFondo 
         AND C.CODCAR = p_strCodigoCartera 
         AND A.CODEMP = E.CODPER 
         AND C.FLGPRI = '-1'
         ORDER BY A.NA,A.CODCAR;
        
        p_vbt_fondos_sal_saldos:= cs_vbt_fondos_sal_saldos;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_fondos_sal_saldos_pr;    
  
    /******************************************************************************
   NAME:    vbt_fondos_sal_movimi_pr   
   PURPOSE: Retorna informacion de la tabla FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1
   ******************************************************************************/        
            
       PROCEDURE vbt_fondos_sal_movimi_pr (p_strCodigoCartera IN VARCHAR2,
                                            p_strCodigoFondo IN VARCHAR2,
                                            p_vbt_fondos_sal_movimi OUT vbt_fondos_sal_movimi, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_fondos_sal_movimi vbt_fondos_sal_movimi;  
   begin
   
     OPEN cs_vbt_fondos_sal_movimi FOR 
        
         SELECT distinct descripcion
         FROM FONDOMUTUAL_VBT.MOVIMIENTO_FMUT_1
         WHERE CODCAR = p_strCodigoCartera 
         AND CODEMP = p_strCodigoFondo 
         AND TIPOPE <>'003'
         AND DESCRIPCION IS NOT NULL
         ORDER BY 1 ASC;
        
        p_vbt_fondos_sal_movimi:= cs_vbt_fondos_sal_movimi;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_fondos_sal_movimi_pr;    
  
  /******************************************************************************
   NAME:    vbt_porta_con_det_pr      
   PURPOSE: Retorna informacion delos movimientos del portafolio consolidado
   ******************************************************************************/            
                
       PROCEDURE vbt_porta_con_det_pr (p_nro_cuenta IN VARCHAR2,
                                        p_vbt_porta_con_det OUT vbt_porta_con_det, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_porta_con_det vbt_porta_con_det;  
   begin
   
     OPEN cs_vbt_porta_con_det FOR 
        
        SELECT SUM(DECODE(SIGNO_TRN,'-', -1*NVL(MTO_TRN_ABONO,0),NVL(MTO_TRN_CARGO,0))) MONTO_NO_FACTURADO_USD 
        FROM   VBT_TARJETA.DET_TRN_TDC_DIA_VU 
        WHERE  NRO_CTA = p_nro_cuenta 
        AND    COD_TRN  IN ('10','12','14','16','24','25','26','27','28','30','32','34','40','42','46','48','51','60','61','62','96','11','13','15','17','20','21','22','23','31','33','41','43','49','50');
   
        
        p_vbt_porta_con_det:= cs_vbt_porta_con_det;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_porta_con_det_pr;    
   
   /******************************************************************************
   NAME:    vbt_fondos_sal_trans_pr      
   PURPOSE: Retorna informacion de los movimientos transacciones
   ******************************************************************************/        
                
   PROCEDURE vbt_fondos_sal_trans_pr (p_TAGEnTransito  IN VARCHAR2,
                                        p_strCodigoFondo   IN VARCHAR2,
                                        p_strCodigoCartera IN VARCHAR2,
                                        p_strCmbDiasConsulta IN VARCHAR2,
                                        p_strTxtFechaDesde  IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_strTipoTransaccion IN VARCHAR2,
                                        p_vbt_fondos_sal_trans OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2,
                                        p_idioma IN VARCHAR2) AS 
                                        sqlstring varchar2(1500);
   begin
   
         sqlstring:=' SELECT TO_CHAR(M.FECHOPE,''dd/mm/yyyy'') FECHA_OPERACION,
             TO_CHAR(M.FECHVAL,''dd/mm/yyyy'') FECHA_VALOR,
             DECODE(''_ve_es'','''||p_idioma||''',M.DESCRIPCION,M.DESCRIPCION_ING) DESCRIPCION,
             M.MTOINV TOTAL_MONEDA,
             M.VAN VUI,
             DECODE(M.TIPO,''-'',M.NUMACC,0) RESCATES,
             DECODE(M.TIPO,''+'',M.NUMACC,0) SUSCRIPCIONES,
             DECODE(M.STATOPE,''T'', '''||p_TAGEnTransito||''','' '')
             FROM FONDOMUTUAL_VBT.MOVIMIENTO_FMUT_1 M
             WHERE M.CODCAR='''||p_strCodigoCartera ||'''
             AND M.CODEMP='''|| p_strCodigoFondo ||'''
             AND M.TIPOPE <>''003''' ;
              
            if p_strCmbDiasConsulta <> '-1' then
                sqlstring:= sqlstring || ' AND M.FECHOPE >= sysdate - '''|| p_strCmbDiasConsulta ||''' ';
            else
                if p_strTxtFechaDesde is not null then
                    sqlstring:= sqlstring || ' AND M.FECHOPE >= TO_DATE('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'')';
                end if;
                if p_strTxtFechaHasta is not null then
                    sqlstring:= sqlstring || ' AND M.FECHOPE <= TO_DATE('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'')';
                end if;
            end if;
            if p_strTipoTransaccion <> 'All' then
              sqlstring:= sqlstring || ' AND UPPER(M.DESCRIPCION) = UPPER('''||p_strTipoTransaccion ||''') ';
            end if;
             sqlstring:= sqlstring || '  ORDER BY TO_DATE(FECHOPE,''dd/mm/yyyy'') DESC';
             
        
        open  p_vbt_fondos_sal_trans for sqlstring;   
   
   
    p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_fondos_sal_trans_pr;        
  
   /******************************************************************************
   NAME:    vbt_tdcconsulta_tar_pr      
   PURPOSE: Retorna informacion de los movimientos de tarjetas
   ******************************************************************************/        
 
  
     PROCEDURE vbt_tdcconsulta_tar_pr (p_strCarteras IN OUT VARCHAR2,
                                        p_vbt_tdcconsulta_tar OUT vbt_tdcconsulta_tar, 
                                            p_resultado OUT VARCHAR2) AS 
                                            --cs_vbt_tdcconsulta_tar vbt_tdcconsulta_tar; 
                                            sqlstring varchar2(2500);
                                             
   begin
   
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
   
     --OPEN cs_vbt_tdcconsulta_tar FOR 
        
    sqlstring:=    'SELECT DECODE(B.COD_TPO_TDC,''V'',''VISA'',''MC'') ||'' ****-****-****-'' || SUBSTR(CC.NRO_PLASTICO,17,4) NRO_PLASTICO, 
        CC.COD_CAR CARTERA, 
        CC.NRO_DOC NRO_CTA 
        FROM VBT_TARJETA.CTACAR_TEMP CC,  
        VBT_TARJETA.BINES_TDC B 
        WHERE CC.COD_CAR IN ('''||p_strCarteras||''') 
        AND CC.TIPO_DOC = ''P'' 
        AND SUBSTR(CC.NRO_PLASTICO,5,6) = B.BIN 
         ORDER BY CC.COD_CAR, CC.NRO_PLASTICO ASC';
        
        --p_vbt_tdcconsulta_tar:= cs_vbt_tdcconsulta_tar;  
        open  p_vbt_tdcconsulta_tar for sqlstring;
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_tdcconsulta_tar_pr;    
 
  
   /******************************************************************************
   NAME:    vbt_tdcconsulta_mes_pr      
   PURPOSE: Retorna informacion delos movimientos del portafolio consolidado
   ******************************************************************************/            
                
       PROCEDURE vbt_tdcconsulta_mes_pr (p_nro_cuenta IN VARCHAR2,
                                         p_vbt_tdcconsulta_mes OUT vbt_tdcconsulta_mes, 
                                         p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_tdcconsulta_mes vbt_tdcconsulta_mes;  
   begin
   
     OPEN cs_vbt_tdcconsulta_mes FOR 
        
          SELECT   distinct(ECT.MES) FECHA, 
          ECT.FEC_REG FECHA_REG                 
          FROM     VBT_TARJETA.EDO_CTA_TDC ECT  
          -- //+ " WHERE    ECT.FEC_REG <> to_date('26/02/2011', 'DD/MM/YYYY') "   // OJO: Debe ser comentado cuando Evertec corrija la data para este mes
          ORDER BY ECT.FEC_REG DESC;
        
        p_vbt_tdcconsulta_mes:= cs_vbt_tdcconsulta_mes;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_tdcconsulta_mes_pr;    
  
  /******************************************************************************
   NAME:    vbt_tdcconsulta_cab_pr      
   PURPOSE: Retorna informacion delos movimientos de tarjetas encabezado
   ******************************************************************************/            
            
       PROCEDURE vbt_tdcconsulta_cab_pr (p_num_cta    IN VARCHAR2,
                                      p_codproserv  IN VARCHAR2,
                                      p_mes         IN VARCHAR2,
                                                          p_vbt_tdcconsulta_cab OUT vbt_tdcconsulta_cab, 
                                                          p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_tdcconsulta_cab vbt_tdcconsulta_cab;  
   begin
   
     OPEN cs_vbt_tdcconsulta_cab FOR 
        
         SELECT SUBSTR(SUBSTR(num_cta_plast_ppal,5),1,4)||'-'||SUBSTR(SUBSTR(num_cta_plast_ppal,5),5,4)||'-'||SUBSTR(SUBSTR(num_cta_plast_ppal,5),9,4)||'-'||SUBSTR(SUBSTR(num_cta_plast_ppal,5),13,4) num_cta_plast_ppal,
         desprodserv,
          nombre_cliente_cta, 
         NVL(direccion_uno,'-') direccion_uno,
         NVL(direccion_dos,'-') direccion_dos,
         NVL(direccion_tres,'-') direccion_tres,
         SUBSTR(zona_postal,4,4) zona_postal, 
         NVL(lim_credito,0) lim_credito,
         NVL(credito_disp,0) credito_disp,
         to_char(fecha_factura,'dd/MM/yyyy') fecha_factura,
         NVL(pag_total,'0') pag_total,
         NVL(pag_min_mes,'0') pag_min_mes,
         to_char(fec_pag_antes,'dd/MM/yyyy') fec_pag_antes,
         NVL(saldo_actual,'0') saldo_actual,
         NVL(cuo_ven,0) cuo_ven,
         NVL(imp_ven,0) imp_ven,
         NVL(cuo_mes,0) cuo_mes,
         NVL(int_bon,0) int_bon,
         NVL(tas_int,0) tas_int,
         NVL(tasa_mora,0) tasa_mora,
         NVL(periodo_fact,0) periodo_fact,
         NVL(sal_anterior,0) sal_anterior,
         NVL(cargos,0) cargos,
         NVL(pagos,0) abonos,
         NVL(tpo_tdc,'') tpo_tdc,
         mensaje1 observaciones 
         FROM   VBT_TARJETA.EDO_CTA_TDC 
         WHERE  num_cta= p_num_cta 
         AND    codproserv = p_codproserv 
         AND    mes = p_mes;
        
        p_vbt_tdcconsulta_cab:= cs_vbt_tdcconsulta_cab;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_tdcconsulta_cab_pr;    
   
  /******************************************************************************
   NAME:    vbt_tdcconsulta_mov_pr      
   PURPOSE: Retorna informacion delos movimientos de tarjetas 
   ******************************************************************************/            
        
            
       PROCEDURE vbt_tdcconsulta_mov_pr (p_num_cta    IN VARCHAR2,
                                         p_codproserv  IN VARCHAR2,
                                         p_mes         IN VARCHAR2,
                                         p_vbt_tdcconsulta_mov OUT vbt_tdcconsulta_mov, 
                                         p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_tdcconsulta_mov vbt_tdcconsulta_mov;  
   begin
   
     OPEN cs_vbt_tdcconsulta_mov FOR 
        
        SELECT SUBSTR(nro_plastico,5) nro_plastico,
         TO_CHAR(fec_trn,'dd/MM/yyyy') fec_trn, 
         num_ref,
         descripcion descripcion,
         cat_comercio,
         NVL(mto_trn_ml,0) mto_trn_ml,
         NVL(tpo_trn,'') tpo_trn,
         mto_trn_mo,
         tas_cam,
         dsc_moneda dsc_moneda, 
         '****-****-****-'||SUBSTR(SUBSTR(nro_plastico,5),13,4) nro_plastico_formato 
        --//                                   " SUBSTR(SUBSTR(nro_plastico,5),1,4)||'-'||SUBSTR(SUBSTR(nro_plastico,5),5,4)||'-'||SUBSTR(SUBSTR(nro_plastico,5),9,4)||'-'||SUBSTR(SUBSTR(nro_plastico,5),13,4) nro_plastico_formato " +  // 10
        FROM  VBT_TARJETA.DET_TRN_EDO_TDC 
        WHERE num_cta= p_num_cta 
        AND   codproserv = p_codproserv 
        AND   mes = p_mes 
        AND   COD_TRN  NOT IN  ('00') 
        ORDER BY nro_plastico,to_date(fec_trn,'dd/MM/yyyy') DESC;    
        
        p_vbt_tdcconsulta_mov:= cs_vbt_tdcconsulta_mov;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_tdcconsulta_mov_pr;    
  
  
  
    /******************************************************************************
   NAME:    vbt_tdmovimi_ctas_pr      
   PURPOSE: Retorna informacion delos movimientos de tarjetas 
   ******************************************************************************/            
     
            
       PROCEDURE vbt_tdmovimi_ctas_pr (p_strCarteras IN OUT VARCHAR2,
                                         p_vbt_tdmovimi_ctas OUT vbt_tdmovimi_ctas, 
                                         p_resultado OUT VARCHAR2) AS 
                                         --cs_vbt_tdmovimi_ctas vbt_tdmovimi_ctas;  
                                         sqlstring varchar2(2500);
   begin
   
   
   
     --p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
     
     --OPEN cs_vbt_tdmovimi_ctas FOR 
        
     sqlstring:=     'SELECT NRO_DOC, 
         COD_CAR,      
         DECODE(B.COD_TPO_TDC,''V'', ''VISA '',''Master '') || ''****-****-****'' || SUBSTR(NRO_PLASTICO, -4 ,17) NRO_PLASTICO, 
         TIPO_DOC
        ,NRO_PLASTICO ,
         NOMBRE_PLASTICO
         FROM   VBT_TARJETA.CTACAR_TEMP C, VBT_TARJETA.BINES_TDC B                   
        WHERE  COD_CAR IN ('||p_strCarteras||') 
        AND    (SIT_DOC = ''1'' OR SIT_DOC = ''2'')
        AND TIPO_DOC = ''P''
        AND    SUBSTR(C.NRO_PLASTICO, 5 ,6) = B.BIN 
        ORDER BY C.COD_CAR, C.NRO_PLASTICO ASC'; 
        
        --p_vbt_tdmovimi_ctas:= cs_vbt_tdmovimi_ctas; 
        open  p_vbt_tdmovimi_ctas for sqlstring;
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_tdmovimi_ctas_pr;    
   
   /******************************************************************************
   NAME:    vbt_tdmovimi_trans_pr      
   PURPOSE: Retorna informacion delos movimientos de tarjetas 
   ******************************************************************************/            

            
       PROCEDURE vbt_tdmovimi_trans_pr (p_strNumeroCuentaTdc    IN VARCHAR2,
                                       p_vbt_tdmovimi_trans OUT vbt_tdmovimi_trans, 
                                         p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_tdmovimi_trans vbt_tdmovimi_trans;  
   begin
   
     OPEN cs_vbt_tdmovimi_trans FOR 
        
         SELECT TO_CHAR(FEC_TRN, 'DD/MM/YYYY') FECHA_TRANSACCION, 
         TO_CHAR(FEC_POSTEO, 'DD/MM/YYYY') FECHA_PROCESO, 
         DSC_TRN ||' '|| DECODE(CODMON,'USD', '', '/ ('||CODMON||' '||MTO_TRN_MO||')') DESCRIPCION, 
         DECODE(SIGNO_TRN,'-', -1*NVL(MTO_TRN_ABONO,0),NVL(MTO_TRN_CARGO,0)) MONTO_USD 
         FROM   VBT_TARJETA.DET_TRN_TDC_DIA_VU                   
         WHERE  ( NRO_DOC = p_strNumeroCuentaTdc OR NRO_DOC = substr(p_strNumeroCuentaTdc,1,17) ||'000') 
         --  // +  "  WHERE  NRO_CTA = '" + strNumeroCuentaTdc + "'" 
         AND COD_TRN  IN ('10','12','14','16','24','25','26','27','28','30','32','34','40','42','46','48','51','60','61','62','96','11','13','15','17','20','21','22','23','31','33','41','43','49','50') ORDER BY FEC_TRN;
        
        p_vbt_tdmovimi_trans:= cs_vbt_tdmovimi_trans;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_tdmovimi_trans_pr;    
  
   
    /******************************************************************************
   NAME:    vbt_cuentasaldo_cuentas_pr      
   PURPOSE: Retorna informacion de cuentas  saldo cuentas corrientes
   ******************************************************************************/            
            
       PROCEDURE vbt_cuentasaldo_cuentas_pr (p_strCarteras    IN VARCHAR2,
                                       p_vbt_cuentasaldo_cuentas OUT vbt_cuentasaldo_cuentas, 
                                         p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_cuentasaldo_cuentas vbt_cuentasaldo_cuentas;  
   begin
   
     OPEN cs_vbt_cuentasaldo_cuentas FOR 
        
        SELECT A.CODCOL VALUE,
         A.TIPOCOL tipo,
         A.CODCAR CARTERA, 
         A.FECHCIERRE FECHA_CIERRE 
         FROM BANCO_VBT.CTAS_CTTES A,
         PRODUCCION.CTAPER E 
         WHERE A.CODCAR IN (p_strCarteras) 
         AND A.CODEMP = E.CODPER 
         ORDER BY A.FECHAPER DESC ;
        
        p_vbt_cuentasaldo_cuentas:= cs_vbt_cuentasaldo_cuentas;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentasaldo_cuentas_pr;    
   
   
   /******************************************************************************
   NAME:    vbt_cuentasaldo_saldos_pr      
   PURPOSE: Retorna informacion de cuentas  saldo cuentas corrientes
   ******************************************************************************/            
                
       PROCEDURE vbt_cuentasaldo_saldos_pr (p_strCarteras    IN VARCHAR2,
                                            p_strNumeroCuenta  IN VARCHAR2,
                      p_strCodigoCartera IN VARCHAR2,
                                            p_vbt_cuentasaldo_saldos OUT vbt_cuentasaldo_saldos, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_cuentasaldo_saldos vbt_cuentasaldo_saldos;  
   begin
   
     OPEN cs_vbt_cuentasaldo_saldos FOR 
        
        SELECT A.CODCOL CUENTA,
         A.TIPOCOL TIPO,
         A.CODMON MONEDA,
         A.SALDO_BLOQUEADO BLOQUEADO,
         A.SALDO_DIFERIDO DIFERIDO,
         A.SALDO_DISPONIBLE DISPONIBLE,
         A.SALDO_ACTUAL SALDO_ACTUAL,
         to_char(A.FECHCIERRE-1,'dd/mm/yyyy') FECHA_CIERRE,
     --A.FECHCIERRE FECHA_CIERRE,
         INITCAP(C.NA) NOMBRE_CLIENTE
         FROM BANCO_VBT.CTAS_CTTES A,                           
         PRODUCCION.CTAPER E,
         PRODUCCION.CLIENTES C
         WHERE A.CODCAR= p_strCodigoCartera 
         AND A.CODCOL= p_strNumeroCuenta 
         AND A.codemp = E.codper
         AND C.codcar = p_strCodigoCartera 
         AND C.flgpri = '-1';
        
        p_vbt_cuentasaldo_saldos:= cs_vbt_cuentasaldo_saldos;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentasaldo_saldos_pr;    
   
    /******************************************************************************
   NAME:    vbt_cuentasaldo_tipos_pr      
   PURPOSE: Retorna informacion de cuentas  saldo cuentas corrientes
   ******************************************************************************/            
        
            
       PROCEDURE vbt_cuentasaldo_tipos_pr (p_strNumeroCuenta  IN VARCHAR2,
                                            p_strCodigoCartera IN VARCHAR2,
                                            p_vbt_cuentasaldo_tipos OUT vbt_cuentasaldo_tipos, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_cuentasaldo_tipos vbt_cuentasaldo_tipos;  
   begin
   
     OPEN cs_vbt_cuentasaldo_tipos FOR 
        
        SELECT distinct descripcion
         FROM BANCO_VBT.movimiento_ctas_cttes_1
         WHERE codcol = p_strNumeroCuenta 
         AND codcar = p_strCodigoCartera 
         AND CodTipoMov <> 'GR'
         AND descripcion is not null
         AND statdif <> 'C' 
         ORDER BY 1 ASC;
        
        p_vbt_cuentasaldo_tipos:= cs_vbt_cuentasaldo_tipos;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentasaldo_tipos_pr;    
   
   
  
     PROCEDURE vbt_cuentasaldo_trans_pr (p_strNumeroCuenta   IN VARCHAR2,
                                        p_strCmbDiasConsulta   IN VARCHAR2,
                                        p_strTxtFechaDesde     IN VARCHAR2,
                                        p_strTxtFechaHasta     IN VARCHAR2,
                                        p_strCmbTipoTransaccion IN VARCHAR2,
                                        p_vbt_cuentasaldo_trans OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2,
                                        p_sql OUT VARCHAR2) AS 
                                        sqlstring varchar2(4000);
   begin
   
         sqlstring:=' Select * from ( SELECT  to_char(m.fechlib,''dd/mm/yyyy'') fecha_operacion,to_char(m.fechval,''dd/mm/yyyy'') fecha_valor,
                 m.descripcion descripcion, m.referencia referencia,
                 0 debito ,     m.monto credito,
                 INITCAP(m.beneficiario) beneficiario, m.NUMCTABAN numero_cuenta,
                 INITCAP(m.NOMBAN) banco, m.observ observacion,
                 m.codmon moneda, m.tasacambio tasa_cambio
                ,m.CodTipoMov tipo_mov     ,m.beneficiary_type 
                ,m.beneficiary_type_number,m.beneficiary_description
                ,m.beneficiary_bank_type ,m.beneficiary_bank_type_number
                ,m.beneficiary_bank_description ,m.intermediary_bank_type 
                ,m.intermediary_bank_type_number,m.intermediary_bank_description 
                ,m.originators_bank_type ,m.originators_bank_type_number 
                ,m.originators_bank_description    ,m.beneficiary_info 
                ,m.originators_info ,m.receiver_info 
                ,m.charge_to ,m.trnseq 
                ,m.statws ,m.codemp_orig_dest 
                ,m.codcar_orig_dest ,m.codcol_orig_dest 
                ,to_char(m.fechespecial,''dd/mm/yyyy'') fecha_especial,m.plazomov 
                ,m.precio    ,m.rendimiento 
                ,m.tasacamb_ref      
                ,m.CODMON    
                FROM BANCO_VBT.movimiento_ctas_cttes_1 m
                 WHERE m.codcol='''||p_strNumeroCuenta||'''
                 AND m.monto >= 0
                 AND m.CodTipoMov <>''GR''
                 AND m.statdif <> ''C''';
                 
        if p_strCmbDiasConsulta <> '-1'     then
            sqlstring := sqlstring || '  AND m.fechlib >= sysdate -'''|| p_strCmbDiasConsulta||'''';
        else
             if p_strTxtFechaDesde is not null then
                sqlstring := sqlstring || ' AND m.fechlib >= to_date('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'')';
             end if;
             if p_strTxtFechaHasta is not null then
                sqlstring := sqlstring || ' AND m.fechlib <= to_date('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'')';
             end if;
             
        end if;
        if p_strCmbTipoTransaccion <> '-2' then
            sqlstring := sqlstring || ' AND upper(m.descripcion) = upper('''||p_strCmbTipoTransaccion||''') ';
     end if;
      
            sqlstring := sqlstring || ' UNION ALL SELECT to_char(m.fechlib,''dd/mm/yyyy'') fecha_operacion,
                 to_char(m.fechval,''dd/mm/yyyy'') fecha_valor,     m.descripcion,
                 m.referencia,    m.monto debito,    0 credito,
                    INITCAP(m.beneficiario) beneficiario, m.NUMCTABAN numero_cuenta,
                    INITCAP(m.NOMBAN) banco,m.observ observacion,
                 m.codmon moneda, m.tasacambio tasa_cambio
                ,m.CodTipoMov tipo_mov     ,m.beneficiary_type 
                ,m.beneficiary_type_number ,m.beneficiary_description 
                ,m.beneficiary_bank_type ,m.beneficiary_bank_type_number 
                ,m.beneficiary_bank_description ,m.intermediary_bank_type 
                ,m.intermediary_bank_type_number    ,m.intermediary_bank_description 
                ,m.originators_bank_type     ,m.originators_bank_type_number 
                ,m.originators_bank_description ,m.beneficiary_info 
                ,m.originators_info ,m.receiver_info 
                ,m.charge_to ,m.trnseq 
                ,m.statws     ,m.codemp_orig_dest 
                ,m.codcar_orig_dest ,m.codcol_orig_dest 
                ,to_char(m.fechespecial,''dd/mm/yyyy'') fecha_especial,m.plazomov 
                ,m.precio ,m.rendimiento 
                ,m.tasacamb_ref
                ,m.CODMON  
                 FROM BANCO_VBT.movimiento_ctas_cttes_1 m
                 WHERE m.codcol= '''||p_strNumeroCuenta ||'''
                 AND m.monto < 0
                 AND m.CodTipoMov <>''GR''
                 AND m.statdif <> ''C''';
         
         if p_strCmbDiasConsulta <> '-1'     then
          sqlstring := sqlstring || '  AND m.fechlib >= sysdate -'''|| p_strCmbDiasConsulta||'''';
        else
           if p_strTxtFechaDesde is not null then
            sqlstring := sqlstring || ' AND m.fechlib >= to_date('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'')';
           end if;
           if p_strTxtFechaHasta is not null then
            sqlstring := sqlstring || ' AND m.fechlib <= to_date('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'')';
           end if;
             
       end if;
       if p_strCmbTipoTransaccion <> '-2' then
            sqlstring := sqlstring || ' AND upper(m.descripcion) = upper('''||p_strCmbTipoTransaccion||''') ';
       end if;
       sqlstring:= sqlstring || ' ) ORDER BY to_date(fecha_operacion,''dd/mm/yyyy'') DESC';
       p_sql := sqlstring; 
        open  p_vbt_cuentasaldo_trans for sqlstring;   
   
   
    p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_cuentasaldo_trans_pr;        
   
   
    /******************************************************************************
   NAME:    vbt_directorio_pr      
   PURPOSE: Retorna informacion de cuentas  saldo cuentas corrientes
   ******************************************************************************/            
                
       PROCEDURE vbt_directorio_pr (p_contrato  IN VARCHAR2,
                                                                    p_vbt_directorio OUT vbt_directorio, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_directorio vbt_directorio;  
   begin
   


        OPEN cs_vbt_directorio FOR 

        SELECT DIR.CODIGO COD_TEMPLATE 
            ,DIR.NOMBRE_REGISTRO NOMBRE_TEMPLATE 
            ,DIR.BENEFICIARY_NAME NOMBRE_BENEFICIARIO 
            ,DIR.BENEFICIARY_TYPE_NUMBER NRO_CTA_BENEFICIARIO 
            ,DIR.BENEFICIARY_BANK_NAME BANCO_BENEFICIARIO 
            ,DIR.BENEFICIARY_BANK_COUNTRY PAIS_BANCO_BENEFICIARIO 
            ,DIR.BENEFICIARY_TYPE 
            ,DIR.BENEFICIARY_EMAIL 
            ,DIR.BENEFICIARY_ADDRESS_L1 
            ,DIR.BENEFICIARY_ADDRESS_L2 
            ,DIR.BENEFICIARY_ADDRESS_L3 
            ,DIR.BENEFICIARY_PHONE 
            ,DIR.BENEFICIARY_COUNTRY 
            ,DIR.BENEFICIARY_BANK_TYPE 
            ,DIR.BENEFICIARY_BANK_TYPE_NUMBER 
            ,DIR.BENEFICIARY_BANK_ADDRESS_L1 
            ,DIR.BENEFICIARY_BANK_ADDRESS_L2 
            ,DIR.BENEFICIARY_BANK_ADDRESS_L3 
            ,DIR.INTERMEDIARY_BANK_TYPE 
            ,DIR.INTERMEDIARY_BANK_TYPE_NUMBER 
            ,DIR.INTERMEDIARY_BANK_NAME 
            ,DIR.INTERMEDIARY_BANK_ADDRESS_L1 
            ,DIR.INTERMEDIARY_BANK_ADDRESS_L2 
            ,DIR.INTERMEDIARY_BANK_ADDRESS_L3 
            ,DIR.INTERMEDIARY_BANK_COUNTRY 
            ,DIR.FFC_NUMBER 
            ,DIR.FFC_NUMBER
            ,DIR.ESTATUS_APROBACION 
            ,DIR.BENEFICIARY_TYPE
            ,DIR.BENEFICIARY_LASTNAME1
            ,DIR.BENEFICIARY_LASTNAME2 
             FROM   VBTONLINE_TRANS.VBT_DIRECTORIO DIR 
             WHERE  DIR.NUM_CONTRATO = p_contrato
             AND ACTIVO = 'A'
       ORDER BY DIR.NOMBRE_REGISTRO ASC; 

        
        p_vbt_directorio:= cs_vbt_directorio;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_directorio_pr;
   
   
    PROCEDURE vbt_directorioAutocompletar_pr (p_strLogin  IN VARCHAR2,
                                              p_vbt_directorioautocompletar OUT vbt_directorioautocompletar, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_directorioautocmpletar vbt_directorioautocompletar;  
   begin
   
     OPEN cs_vbt_directorioautocmpletar FOR 
        
        SELECT DIR.CODIGO COD_TEMPLATE 
            ,DIR.NOMBRE_REGISTRO NOMBRE_TEMPLATE 
            FROM   VBTONLINE_TRANS.VBT_DIRECTORIO DIR 
             WHERE  DIR.USERNAME = p_strLogin
             AND ACTIVO='A'
       ORDER BY DIR.NOMBRE_REGISTRO ASC;  
        
        p_vbt_directorioautocompletar:= cs_vbt_directorioautocmpletar;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_directorioAutocompletar_pr;        
   
    /******************************************************************************
   NAME:    vbt_directorio_agregar_pr      
   PURPOSE: Retorna informacion de cuentas  saldo cuentas corrientes
   ******************************************************************************/            
    
            
       PROCEDURE vbt_directorio_agregar_pr (p_strLogin  IN VARCHAR2,
                                            p_codigoTemplate  IN VARCHAR2,
                                            p_vbt_directorio_agregar OUT vbt_directorio_agregar, 
                                            p_resultado OUT VARCHAR2) AS 
                                            cs_vbt_directorio_agregar vbt_directorio_agregar;  
   begin
   
     OPEN cs_vbt_directorio_agregar FOR 
        
        SELECT DIR.CODIGO COD_TEMPLATE 
            ,DIR.NOMBRE_REGISTRO NOMBRE_TEMPLATE 
            ,DIR.BENEFICIARY_NAME NOMBRE_BENEFICIARIO 
            ,DIR.BENEFICIARY_TYPE_NUMBER NRO_CTA_BENEFICIARIO 
            ,DIR.BENEFICIARY_BANK_NAME BANCO_BENEFICIARIO 
            ,DIR.BENEFICIARY_BANK_COUNTRY PAIS_BANCO_BENEFICIARIO 
            ,DIR.BENEFICIARY_TYPE 
            ,DIR.BENEFICIARY_EMAIL 
            ,DIR.BENEFICIARY_ADDRESS_L1 
            ,DIR.BENEFICIARY_ADDRESS_L2 
            ,DIR.BENEFICIARY_ADDRESS_L3 
            ,DIR.BENEFICIARY_PHONE 
            ,DIR.BENEFICIARY_COUNTRY 
            ,DIR.BENEFICIARY_BANK_TYPE 
            ,DIR.BENEFICIARY_BANK_TYPE_NUMBER 
            ,DIR.BENEFICIARY_BANK_ADDRESS_L1 
            ,DIR.BENEFICIARY_BANK_ADDRESS_L2 
            ,DIR.BENEFICIARY_BANK_ADDRESS_L3 
            ,DIR.INTERMEDIARY_BANK_TYPE 
            ,DIR.INTERMEDIARY_BANK_TYPE_NUMBER 
            ,DIR.INTERMEDIARY_BANK_NAME 
            ,DIR.INTERMEDIARY_BANK_ADDRESS_L1 
            ,DIR.INTERMEDIARY_BANK_ADDRESS_L2 
            ,DIR.INTERMEDIARY_BANK_ADDRESS_L3 
            ,DIR.INTERMEDIARY_BANK_COUNTRY 
            ,DIR.FFC_NUMBER 
            ,DIR.FFC_NAME 
            ,DIR.ESTATUS_APROBACION
            ,DIR.BENEFICIARY_SWIFT_TYPE
            ,DIR.BENEFICIARY_SWIFT_TYPE_NUMBER
            ,DIR.INTERMEDIARY_SWIFT_TYPE
            ,DIR.INTERMEDIARY_SWIFT_TYPE_NUMBER
            ,DIR.BENEFICIARY_CITY
            ,DIR.BENEFICIARY_POSTAL_CODE
            ,DIR.BENEFICIARY_BANK_CITY
            ,DIR.INTERMEDIARY_BANK_CITY
            ,DIR.BENEFICIARY_TYPE_PERSON
            ,DIR.BENEFICIARY_LASTNAME1
            ,DIR.BENEFICIARY_LASTNAME2
             FROM   VBTONLINE_TRANS.VBT_DIRECTORIO DIR 
             WHERE /* DIR.USERNAME = p_strLogin
             AND*/DIR.CODIGO = p_codigoTemplate;  
  
        
        p_vbt_directorio_agregar:= cs_vbt_directorio_agregar;  
        p_resultado:= 'OK';    
       -- commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_directorio_agregar_pr;    
    /******************************************************************************
   NAME:    vbt_colocacerti_cierre_pr      
   PURPOSE: Retorna informacion de las fechas de cirre de la tabla ctaper
   ******************************************************************************/            
             
            
       PROCEDURE vbt_colocacerti_cierre_pr (p_strCarteras  IN OUT VARCHAR2,
                                                  p_vbt_colocacerti_cierre OUT vbt_colocacerti_cierre, 
                                                  p_resultado OUT VARCHAR2) AS 
                                                  cs_vbt_colocacerti_cierre vbt_colocacerti_cierre; 
                                                  SQLSTRING VARCHAR2(500); 
                                                  
   begin
   
   
 
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
   
   
   --  OPEN cs_vbt_cuentas_edo_cuenta FOR 
   SQLSTRING:='        SELECT distinct to_char(TD.FECHCIERRE-1,''dd/mm/YYYY'') 
        FROM BANCO_VBT.TIME_DEPOSIT_1 TD,
        PRODUCCION.CTAPER PER
        WHERE TD.CODCAR IN ('''||p_strCarteras||''') 
        AND TD.CODEMP = PER.CODPER' ;
          open  p_vbt_colocacerti_cierre for sqlstring;     
    --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;  
        p_resultado:= 'OK';                                                 
       
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colocacerti_cierre_pr;    
   
   /******************************************************************************
   NAME:    vbt_colocacerti_coloca_pr      
   PURPOSE: Retorna informacion de cuentas  saldo cuentas corrientes
   ******************************************************************************/            
                
PROCEDURE vbt_colocacerti_coloca_pr (p_login  IN OUT VARCHAR2,
                                                  p_strFechaCierre IN VARCHAR2,
                                                  p_strCmbBuscar   IN VARCHAR2,
                                                  p_strTxtFechaDesde   IN VARCHAR2,
                                                  p_strTxtFechaHasta   IN VARCHAR2,
                                                  p_vbt_colocacerti_coloca OUT SYS_REFCURSOR,  
                                                  p_resultado OUT VARCHAR2,
                                                  p_sql OUT VARCHAR2) AS 
                                                  sqlstring varchar2(2000);    
                          l_fechaCierre varchar2(10);
        
   begin
   
  SELECT max (to_char(TD.FECHCIERRE-1,'dd/mm/YYYY')) into l_fechaCierre FROM BANCO_VBT.TIME_DEPOSIT_1 TD;
  
  
       sqlstring:='    SELECT c.codcol codigo,
                 c.codmon codigo_moneda,
                 to_char(c.fechaper,''dd/mm/yyyy'') fecha_apertura,
                 to_char(c.fechvenc,''dd/mm/yyyy'') fecha_vencimiento,
                 c.mtoapert monto_apertura,
                 c.mtovcto monto_vencimiento,
                 c.tasaorg tasa,
                 DECODE(GREATEST(MONTHS_BETWEEN(c.fechvenc,to_date('''||l_fechaCierre||''',''dd/mm/yyyy'')), 0),0,''vencida'',''vigente'') status,
                 c.codcar
                 FROM BANCO_VBT.TD_CARACTERISTICAS C,
          VBT_USERS_CONTRATO UC,
          VBT_CONTRATO_CARTERAS CC 
                 WHERE   UC.LOGIN = '''||p_login||'''
          AND UC.NUM_CONTRATO = CC.NUM_CONTRATO
          AND C.CODCAR = CC.CODCAR 
          AND STATCOL <> ''A''';
          
                if p_strCmbBuscar='VI' then
                    sqlstring:= sqlstring || '    AND c.fecha_vcto >= to_date('''||l_fechaCierre||''',''dd/mm/yyyy'')';
                elsif p_strCmbBuscar='FV' then
                    sqlstring:= sqlstring || '    AND c.fecha_vcto >= to_date('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'')  AND c.fecha_vcto <= to_date('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'') ';
                elsif p_strCmbBuscar='FA' then
                    sqlstring:= sqlstring || '    AND c.fechaper >= to_date('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'')  AND c.fechaper <= to_date('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'') ';
                else
                    sqlstring:= sqlstring || ' AND c.fecha_vcto < to_date('''||l_fechaCierre||''',''dd/mm/yyyy'')';
                end if;
                sqlstring:= sqlstring || ' ORDER BY c.fechaper DESC';
                
        
        p_sql := sqlstring;
        open  p_vbt_colocacerti_coloca for sqlstring;   
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_colocacerti_coloca_pr;    
   
   
/******************************************************************************
   NAME:    vbt_colocavenci_cierre_pr       
   PURPOSE: consulta fechas de cierra para colocaciones vencimientos
******************************************************************************/

 PROCEDURE vbt_colocavenci_cierre_pr (p_strCarteras    IN VARCHAR2,
                                p_vbt_colocavenci_cierre OUT vbt_colocavenci_cierre,
                                p_resultado OUT VARCHAR2) AS 
  cs_vbt_colocavenci_cierre vbt_colocavenci_cierre;
 
 
 
 BEGIN
         OPEN cs_vbt_colocavenci_cierre FOR 
                        SELECT distinct to_char(TD.FECHCIERRE-1,'dd/mm/YYYY') FECHA_CIERRE
                        FROM BANCO_VBT.TIME_DEPOSIT_1 TD,
                        PRODUCCION.CTAPER PER
                        WHERE TD.CODCAR IN (p_strCarteras)
                        AND TD.CODEMP = PER.CODPER;     
                p_vbt_colocavenci_cierre:= cs_vbt_colocavenci_cierre;           
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
    p_resultado:= SUBSTR(SQLERRM,1,300); 
 
   end vbt_colocavenci_cierre_pr;
   
   /******************************************************************************
   NAME:    vbt_colocavenci_coloca_pr       
   PURPOSE: consulta fechas de cierra para colocaciones vencimientos
******************************************************************************/

 PROCEDURE vbt_colocavenci_coloca_pr (p_strCarteras            IN OUT VARCHAR2,
                                      p_strFechaCierre         IN VARCHAR2,
                                      p_strCmbBuscar           IN VARCHAR2,
                                      p_strTxtFechaDesde     IN VARCHAR2,
                                      p_strTxtFechaHasta    IN VARCHAR2,
                                      p_vbt_colocavenci_cierre OUT SYS_REFCURSOR,  
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2) AS 
                                      sqlstring varchar2(1000);
 
 
 
 BEGIN
 
 p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
 
                 sqlstring:='SELECT codcol codigo,
                         codmon codigo_moneda,
                         to_char(fechaper,''dd/mm/yyyy'') fecha_apertura,
                         to_char(fechvenc,''dd/mm/yyyy'') fecha_vencimiento,
                         mtoapert monto_apertura,
                         mtovcto monto_vencimiento,
                         tasaorg tasa,
                         DECODE(GREATEST(MONTHS_BETWEEN(fechvenc,to_date('''||p_strFechaCierre||''',''dd/mm/yyyy'')), 0),0,''vencida'',''vigente'') status,
                         codcar
                         FROM BANCO_VBT.TD_CARACTERISTICAS
                         WHERE CODCAR IN ('''||p_strCarteras ||''')
                         AND STATCOL <> ''A''';     
                
                    if p_strCmbBuscar <> 'PV' then
                        if p_strCmbBuscar <> 'FV' then
                            sqlstring:= sqlstring || ' AND fecha_vcto >= to_date('''||p_strFechaCierre||''',''dd/mm/yyyy'') - '''||p_strCmbBuscar||'''';
                            sqlstring:= sqlstring || ' AND fecha_vcto <= to_date('''||p_strFechaCierre||''',''dd/mm/yyyy'')';
                        else
                            sqlstring:= sqlstring || ' AND fecha_vcto >= to_date('''||p_strTxtFechaDesde||''',''dd/mm/yyyy'') AND fecha_vcto <= to_date('''||p_strTxtFechaHasta||''',''dd/mm/yyyy'')';
                        end if;
                    else
                            sqlstring:= sqlstring || ' AND fecha_vcto > to_date('''||p_strFechaCierre||''',''dd/mm/yyyy'')';
                    end if;
                            
                            sqlstring:= sqlstring || ' ORDER BY fechaper ASC';
                p_sql := sqlstring;
                open  p_vbt_colocavenci_cierre for sqlstring;   
                p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_colocavenci_coloca_pr;
 
 /******************************************************************************
   NAME:    vbt_ConsultaExcepcion_pr       
   PURPOSE: consulta fechas de cierra para colocaciones vencimientos
******************************************************************************/

 PROCEDURE vbt_ConsultaExcepcion_pr  (p_strTxtDesde         IN VARCHAR2,
                                      p_strTxtHasta            IN VARCHAR2,
                                      p_vbt_ConsultaExcepcion OUT SYS_REFCURSOR,  
                                      p_resultado OUT VARCHAR2,
                                      p_sql       OUT VARCHAR2) AS 
                                      sqlstring varchar2(1000);
 
 BEGIN
                 sqlstring:='SELECT TO_CHAR(EXCE.FECHA,''dd/mm/yyyy HH:MI A.M.''), 
                             EXCE.DIRECCION_IP, 
                             EXCE.MENSAJE_JAVA, 
                             EXCE.NOMBRE_ARCHIVO, 
                             EXCE.VALORES_URL, 
                             EXCE.VALORES_SESION 
                             FROM VBT_EXCEPCION EXCE 
                             WHERE EXCE.ID > = 0';
   
                    
                        sqlstring:= sqlstring || ' AND     (TO_DATE(TO_CHAR(EXCE.FECHA,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''||p_strTxtDesde||''',''dd/mm/yyyy''))';
                        sqlstring:= sqlstring || ' AND     (TO_DATE(TO_CHAR(EXCE.FECHA,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''||p_strTxtHasta||''',''dd/mm/yyyy''))';      
                
   
                        sqlstring:= sqlstring || ' ORDER BY TO_DATE(EXCE.FECHA,''dd/mm/yyyy HH:MI A.M.'') desc, to_date(TO_char(EXCE.FECHA,''HH:MI A.M.''),''HH:MI A.M.'') desc';
                        p_sql := sqlstring;
                        open  p_vbt_ConsultaExcepcion for sqlstring;   
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_ConsultaExcepcion_pr ;
    
 /******************************************************************************
   NAME:    vbt_PDFBloqueoDetalle_pr       
   PURPOSE: Obtenemos los datos del cliente
******************************************************************************/

 PROCEDURE vbt_PDFBloqueoDetalle_pr (p_cliente    IN VARCHAR2,
                                p_vbt_PDFBloqueoDetalle OUT vbt_PDFBloqueoDetalle,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_PDFBloqueoDetalle vbt_PDFBloqueoDetalle;
 
 
 
 BEGIN
         OPEN cs_vbt_PDFBloqueoDetalle FOR 
                        select INITCAP(na) nombre_cliente, 
                        precirif || '-' || cirif cedula 
                        from       produccion.clientes 
                        where     codpercli=p_cliente;
                       
                        p_vbt_PDFBloqueoDetalle:= cs_vbt_PDFBloqueoDetalle;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_PDFBloqueoDetalle_pr;
 
  
  /******************************************************************************
   NAME:    vbt_PDFColCertiApertura_certi_pr       
   PURPOSE: Obtenemos los datos del cliente
******************************************************************************/
 


 PROCEDURE vbt_PDFCertiApertura_certi_pr (p_strCodInstrumento    IN VARCHAR2,
                                p_vbt_PDFCertiApertura_certi OUT vbt_PDFCertiApertura_certi,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_PDFCertiApertura_certi vbt_PDFCertiApertura_certi;
 
 
 
 BEGIN
         OPEN cs_vbt_PDFCertiApertura_certi FOR 
                       SELECT  distinct C.na nombre_cliente, 
                        C.direnvio direccion, 
                        TD.codmon codigo_moneda, 
                        DECODE(TD.codmon,'USD','US$','VEB','Bs','EUR','EUR','GBP','£','CHF','CHF',TD.codmon) simbolo_moneda, 
                        to_char(TD.fechaper,'dd/mm/yyyy') fecha_apertura, 
                        to_char(TD.fechvenc,'dd/mm/yyyy') fecha_vencimiento, 
                        to_char(TD.mtoapert,'999G999G999G999G999G999G999G999G999G999G999G990D99') monto_apertura, 
                        to_char(TD.mtovcto,'999G999G999G999G999G999G999G999G999G999G999G990D99') monto_vencimiento, 
                        to_char(TD.intvcto,'999G999G999G999G999G999G999G999G999G999G999G990D99') intereses, 
                        to_char(TD.tasaorg,'999G999G999G999G999G999G999G999G999G999G999G990D99') tasa, 
                        to_char(TD.fechaper,'mm') mes_apertura, 
                        DECODE(NVL(TD.fechaper,''),'','',TRIM(TO_CHAR(TD.fechaper, 'Month','nls_date_language=english')) || ', ' || TO_CHAR(TD.fechaper, 'dd yyyy','nls_date_language=english')) fecha_apertura,
                        TD.clasificador clasificador,
                        C.FLGPRI
                        FROM BANCO_VBT.TD_CARACTERISTICAS TD, 
                        PRODUCCION.CLIENTES C 
                        WHERE TD.codcol =p_strCodInstrumento  
                        AND TD.CODCAR = C.CODCAR;
                       
                        p_vbt_PDFCertiApertura_certi:= cs_vbt_PDFCertiApertura_certi;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_PDFCertiApertura_certi_pr;
 

 
   /******************************************************************************
   NAME:    vbt_PDFCertiApertura_movi_pr       
   PURPOSE: Obtenemos los datos del cliente
******************************************************************************/



 PROCEDURE vbt_PDFCertiApertura_movi_pr (p_strCodInstrumento    IN VARCHAR2,
                                p_vbt_PDFCertiApertura_movi OUT vbt_PDFCertiApertura_movi,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_PDFCertiApertura_movi vbt_PDFCertiApertura_movi;
 
 
 
 BEGIN
         OPEN cs_vbt_PDFCertiApertura_movi FOR 
                       SELECT m.descripcion descripcion_movimiento, 
                        to_char(m.monto,'999G999G999G999G999G999G999G999G999G999G999G990D99') monto_movimiento, 
                        m.codtipomov codigo_movimiento, 
                        m.codmon moneda, 
                        DECODE(m.codmon,'USD','US$','VEB','Bs','EUR','EUR','GBP','£','CHF','CHF',m.codmon) simbolo_moneda, 
                        m.referencia referencia 
                        FROM BANCO_VBT.movimiento_td_1 m  
                        WHERE m.codcol =p_strCodInstrumento 
                        AND (m.orgmov = 'A' OR m.orgmov IS NULL) 
                        ORDER BY to_date(m.fechval,'dd/mm/yyyy') ASC ;
                       
                        p_vbt_PDFCertiApertura_movi:= cs_vbt_PDFCertiApertura_movi;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_PDFCertiApertura_movi_pr;
   
/******************************************************************************
   NAME:    vbt_PDFCertivenci_certi_pr       
   PURPOSE: Obtenemos los datos del certificado de apertura
******************************************************************************/



 PROCEDURE vbt_PDFCertivenci_certi_pr (p_strCodInstrumento    IN VARCHAR2,
                                p_vbt_PDFCertivenci_certi OUT vbt_PDFCertivenci_certi,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_PDFCertivenci_certi vbt_PDFCertivenci_certi;
 
 
 
 BEGIN
         OPEN cs_vbt_PDFCertivenci_certi FOR 
          SELECT m.descripcion descripcion_movimiento, 
 to_char(ABS(m.monto),'999G999G999G999G999G999G999G999G999G999G999G990D99') monto_movimiento, 
 m.codtipomov codigo_movimiento,  
 m.codmon moneda,  
 DECODE(m.codmon,'USD','US$','VEB','Bs','EUR','EUR','GBP','£','CHF','CHF',m.codmon) simbolo_moneda,  
 m.referencia referencia  
 FROM BANCO_VBT.movimiento_td_1 m 
 WHERE m.codcol = p_strCodInstrumento 
 AND (m.orgmov = 'A' OR m.orgmov = 'V' OR m.orgmov IS NULL) 
 ORDER BY to_date(m.fechval,'dd/mm/yyyy') ASC ;
                       
                        p_vbt_PDFCertivenci_certi:= cs_vbt_PDFCertivenci_certi;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_PDFCertivenci_certi_pr;
 
 

/******************************************************************************
   NAME:    vbt_movimi_ope_codper_pr       
   PURPOSE: Inserta o actualiza registros en tabla vbt_movimi_ope_codpercli
******************************************************************************/

 PROCEDURE vbt_movimi_ope_codper_pr (p_codpercli   IN VARCHAR2,
                                      P_NUM_CONTRATO  IN VARCHAR2,
                                      P_VBT_NMTD       IN NUMBER,
                                       P_VBT_MMTD       IN NUMBER,
                                      P_VBT_MMINTO     IN NUMBER,
                                      P_VBT_MMTO       IN NUMBER,
                                      P_EX_NMTD        IN NUMBER,
                                      P_EX_MMTD        IN NUMBER,
                                      P_EX_MMINTO      IN NUMBER,
                                      P_EX_MMTO        IN NUMBER,
                                      P_CUENTA  IN VARCHAR2,
                                      p_resultado OUT VARCHAR2) AS 
                                     sqlstring    VARCHAR2(1000); 
                                      VAL_NUMCONTRATO  VARCHAR2(10):=NULL;        
 
 
 
 BEGIN
        
        BEGIN
            SELECT  NUM_CONTRATO
            INTO VAL_NUMCONTRATO
            FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
            WHERE NUM_CONTRATO=P_NUM_CONTRATO;
        EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    VAL_NUMCONTRATO:=NULL;
                    
                END ;
        
        IF VAL_NUMCONTRATO IS NULL THEN
            sqlstring := 'INSERT INTO VBTONLINE_TRANS.vbt_movimi_ope_codpercli (
                           NUM_CONTRATO, VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM)
                           VALUES('''||P_NUM_CONTRATO||''','''||P_VBT_NMTD||''','''||P_VBT_MMTD||''',
                           '''||P_VBT_MMINTO||''','''||P_VBT_MMTO||''','''||P_EX_NMTD||''','''||P_EX_MMTD||''',
                           '''||P_EX_MMINTO||''','''||P_EX_MMTO||''','''||P_CUENTA||''')';
                           COMMIT;
                           
        ELSE
            sqlstring := 'UPDATE VBTONLINE_TRANS.vbt_movimi_ope_codpercli SET ';
                IF P_VBT_NMTD > 0 THEN
                        sqlstring := sqlstring || ' VBT_NMTD =     '''||P_VBT_NMTD||'''';
                END IF;
                
                IF P_VBT_MMTD > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMTD = '''||P_VBT_MMTD ||'''';
                END IF;
                
                IF P_VBT_MMINTO > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMINTO = '''||P_VBT_MMINTO ||'''';    
                END IF;
                
                IF P_VBT_MMTO > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMTO = '''||P_VBT_MMTO ||'''';        
                END IF;
                
                IF P_EX_NMTD > 0 THEN
                        sqlstring := sqlstring || ' ,EX_NMTD = '''||P_EX_NMTD ||'''';
                END IF;
                
                IF P_EX_MMTD > 0 THEN
                        sqlstring := sqlstring || ' ,EX_MMTD = '''||P_EX_MMTD ||'''';
                END IF;
                
                IF P_EX_MMINTO > 0 THEN
                        sqlstring := sqlstring || ' ,EX_MMINTO = '''||P_EX_MMINTO ||'''';
                END IF;
                
                IF P_EX_MMTO > 0 THEN
                        sqlstring := sqlstring || ' ,EX_MMTO = '''||P_EX_MMTO ||'''';            
                END IF;
                    sqlstring := sqlstring || ' ,ACCOUNT_NUM= '''||P_CUENTA ||'''';
                    sqlstring := sqlstring || ' WHERE NUM_CONTRATO = '''||P_NUM_CONTRATO|| '''';
                COMMIT;    
        END IF;
        EXECUTE IMMEDIATE sqlstring;
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_movimi_ope_codper_pr;  

 /******************************************************************************
   NAME:    vbt_movimi_ope_pr       
   PURPOSE: Inserta o actualiza registros en tabla vbt_movimi_operaciones
******************************************************************************/

 PROCEDURE vbt_movimi_ope_pr (p_flag_inser   IN VARCHAR2,
                                      P_TIPO_PERS      IN VARCHAR2,
                                      P_VBT_NMTD       IN NUMBER,
                                       P_VBT_MMTD       IN NUMBER,
                                      P_VBT_MMINTO     IN NUMBER,
                                      P_VBT_MMTO       IN NUMBER,
                                      P_EX_NMTD        IN NUMBER,
                                      P_EX_MMTD        IN NUMBER,
                                      P_EX_MMINTO      IN NUMBER,
                                      P_EX_MMTO        IN NUMBER,
                    p_resultado OUT VARCHAR2) AS 
                                     sqlstring    VARCHAR2(2500); 
        
 BEGIN
        
                
        IF p_flag_inser='1' THEN
            sqlstring := 'INSERT INTO VBTONLINE_TRANS.vbt_movimi_operaciones (TIPO_PERS,
                           VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO)
                           VALUES('''||P_TIPO_PERS||''','||P_VBT_NMTD||','||P_VBT_MMTD||',
                           '||P_VBT_MMINTO||','||P_VBT_MMTO||','||P_EX_NMTD||','||P_EX_MMTD||',
                           '||P_EX_MMINTO||','||P_EX_MMTO||')';
                           COMMIT;
                           
        ELSE
            sqlstring := 'UPDATE VBTONLINE_TRANS.vbt_movimi_operaciones SET TIPO_PERS = '''||P_TIPO_PERS ||''' ';
                IF P_VBT_NMTD > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_NMTD =     '||P_VBT_NMTD||' ';
                ELSIF P_VBT_MMTD > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMTD = '||P_VBT_MMTD ||'';
                ELSIF P_VBT_MMTD > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMTD = '||P_VBT_MMTD ||'';
                ELSIF P_VBT_MMINTO > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMINTO = '||P_VBT_MMINTO ||'';    
                ELSIF P_VBT_MMTO > 0 THEN
                        sqlstring := sqlstring || ' ,VBT_MMTO = '||P_VBT_MMTO ||'';        
                ELSIF P_EX_NMTD > 0 THEN
                        sqlstring := sqlstring || ' ,EX_NMTD = '||P_EX_NMTD ||'';
                ELSIF P_EX_MMTD > 0 THEN
                        sqlstring := sqlstring || ' ,EX_MMTD = '||P_EX_MMTD ||'';
                ELSIF P_EX_MMINTO > 0 THEN
                        sqlstring := sqlstring || ' ,EX_MMINTO = '||P_EX_MMINTO ||'';
                ELSIF P_EX_MMTO > 0 THEN
                        sqlstring := sqlstring || ' ,EX_MMTO = '||P_EX_MMTO ||'';            
                END IF;
                    sqlstring := sqlstring || ' WHERE TIPO_PERS = '''||P_TIPO_PERS|| '''';
                COMMIT;    
        END IF;
           EXECUTE IMMEDIATE sqlstring;
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_movimi_ope_pr;  
 
 PROCEDURE vbt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
                                      P_NUM_CONTRATO  IN VARCHAR2,
                                      P_tipo_persona IN VARCHAR2,
                                      p_datosPorDefecto OUT vbt_datos_diseneBanco,
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2) AS 
                                      sqlstring    VARCHAR2(1500); 
                                      VAL_CONTRATO  VARCHAR2(10):=NULL;        
 
 
 
 BEGIN
        
        BEGIN
            SELECT  NUM_CONTRATO
            INTO VAL_CONTRATO
            FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
                  WHERE NUM_CONTRATO=P_NUM_CONTRATO;
                  
        EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    VAL_CONTRATO:=NULL;
                    
                END ;
        
        IF VAL_CONTRATO IS NULL THEN
            --Parametros Globales
            IF P_tipo_persona='C' THEN
            --Clientes
                sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM, MINIMUM_BALANCE
                          FROM VBTONLINE_TRANS.vbt_movimi_operaciones';
            ELSE
            --Firmas Conjuntas
                sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM, MINIMUM_BALANCE
                          FROM VBTONLINE_TRANS.PARAMETROS_GLOBALES_FC';
            END IF;   
        
            
                           
                           
        ELSE
            sqlstring := 'Select c.VBT_NMTD, c.VBT_MMTD, c.VBT_MMINTO, c.VBT_MMTO,
                           c.EX_NMTD, c.EX_MMTD, c.EX_MMINTO, c.EX_MMTO, c.ACCOUNT_NUM, g.MINIMUM_BALANCE
                          FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli c, VBTONLINE_TRANS.vbt_movimi_operaciones g 
                          WHERE c.NUM_CONTRATO='''||P_NUM_CONTRATO||'''';
                    
                    
        END IF;
        
         p_sql :=sqlstring;     
         open  p_datosPorDefecto for sqlstring;   
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_movimi_codpercli_pr;
 
 PROCEDURE vbt_movimi_pordefecto_pr (p_datosBase OUT vbt_datos_diseneBancoBase,
                                      p_resultado OUT VARCHAR2,
                                      p_tipo IN VARCHAR2) AS 
                                     sqlstring    VARCHAR2(1500); 
                                            
 
 
 
 BEGIN
       
        IF p_tipo='FC' THEN
        --Firmas Conjuntas
         sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.PARAMETROS_GLOBALES_FC';
        ELSE
        --Clientes
         sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.vbt_movimi_operaciones';
        END IF;
  
       
                           
       
        
        
         open  p_datosBase for sqlstring;   
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_movimi_pordefecto_pr;



PROCEDURE vbt_consultarTelefono_pr (p_strCliente    IN VARCHAR2,
                                         p_TAGHabitacion    IN VARCHAR2,
                                         p_TAGCelular    IN VARCHAR2,
                                         p_TAGOficina    IN VARCHAR2,
                                         p_TAGFax    IN VARCHAR2,
                                         p_TAGOtros    IN VARCHAR2,
                                p_vbt_consultarTelefono OUT vbt_consultarTelefono,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_consultarTelefono vbt_consultarTelefono;
 
 
 BEGIN
         OPEN cs_vbt_consultarTelefono FOR 
         SELECT   DECODE(TIPO,'Habitacion',p_TAGHabitacion,'Celular',p_TAGCelular,'Oficina',p_TAGOficina,'Fax',p_TAGFax,p_TAGOtros) TIPO,
         CODPAIS, 
         AREA, 
          TELEFONO 
 FROM     PRODUCCION.TELEFONOS 
 WHERE    CODPERCLI= p_strCliente;
                       
                        p_vbt_consultarTelefono:= cs_vbt_consultarTelefono;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_consultarTelefono_pr;
 
 
 PROCEDURE vbt_consultarDirecciones_pr (p_strCliente    IN VARCHAR2,
                                         p_TAGApartadoPostal    IN VARCHAR2,
                                         p_TAGHabitacion    IN VARCHAR2,
                                         p_TAGOficina    IN VARCHAR2,
                                         p_TAGSi    IN VARCHAR2,
                                         p_TAGOtros    IN VARCHAR2,
                                         p_TAGNo    IN VARCHAR2,
                                         p_TAGEmail IN VARCHAR2,
                                p_vbt_consultarDirecciones OUT vbt_consultarDirecciones,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_consultarDirecciones vbt_consultarDirecciones;
 
 
 BEGIN
         OPEN cs_vbt_consultarDirecciones FOR 
                SELECT   INITCAP(DIREC_COMPLETA) DIERCCION, 
                DECODE(TIPODIR,'Apartado Postal',p_TAGApartadoPostal,'Email Online',p_TAGEmail,'Habitacion',p_TAGHabitacion,'Oficina',p_TAGOficina,p_TAGOtros) TIPO, 
                DECODE(FLAG_ENVIO,'X',p_TAGSi,p_TAGNo) DE_ENVIO 
                FROM     PRODUCCION.DIRECCIONES 
                WHERE    CODPERCLI= p_strCliente;
                       
                        p_vbt_consultarDirecciones:= cs_vbt_consultarDirecciones;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_consultarDirecciones_pr;
 
 
   
   PROCEDURE vbt_consultarDocumentos_pr (p_strCliente    IN VARCHAR2,
                                p_vbt_consultarDocumentos OUT vbt_consultarDocumentos,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_consultarDocumentos vbt_consultarDocumentos;
 
 
 BEGIN
         OPEN cs_vbt_consultarDocumentos FOR 
                SELECT   NUMDOC NUMERO, 
                        INITCAP(DESCDOC) DESCRIPCION, 
                        TO_CHAR(FECHVENC,'dd/mm/yyyy') VENCIMIENTO, 
                        CODUBI UBICACION, 
                        INITCAP(AUTORIDAD) AUTORIDAD 
               FROM     PRODUCCION.DOCUMENTOS 
               WHERE    CODPERCLI=p_strCliente;
                       
                        p_vbt_consultarDocumentos:= cs_vbt_consultarDocumentos;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_consultarDocumentos_pr;



   PROCEDURE vbt_consultarRepresentantes_pr (p_strCliente    IN VARCHAR2,
                                            p_TAGApoderado      IN VARCHAR2,
                                            p_TAGRepresentante IN VARCHAR2,
                                            p_TAGOtros            IN VARCHAR2,
                                            p_TAGActivo            IN VARCHAR2,
                                            p_TAGInactivo        IN VARCHAR2,
                                            p_vbt_consultarRepresentantes OUT vbt_consultarRepresentantes,
                                p_resultado OUT VARCHAR2) AS 
                                cs_vbt_consultarRepresentantes vbt_consultarRepresentantes;
 
 
 BEGIN
         OPEN cs_vbt_consultarRepresentantes FOR 
                        SELECT   INITCAP(NA) NOMBRE, 
                        DECODE(CONCIRIF,'0',PRECIRIF || '-' || CIRIF, PRECIRIF || '-' || CIRIF || '-' || CONCIRIF) CIRIF, 
                        DECODE(TIPOREP,'Apoderado',p_TAGApoderado,'Representante',p_TAGRepresentante,p_TAGOtros) TIPO, 
                        DECODE(STATREP,'Activo',p_TAGActivo,'Inactivo',p_TAGInactivo,p_TAGOtros) STATUS, 
                        TO_CHAR(FECHVEN,'dd/mm/yyyy') VENCIMIENTO 
                        FROM     PRODUCCION.REPRESENTANTES 
                        WHERE    CODPERCLI=p_strCliente  
                        AND      UPPER(STATREP) = 'ACTIVO';
                       
                        p_vbt_consultarRepresentantes:= cs_vbt_consultarRepresentantes;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_consultarRepresentantes_pr;

PROCEDURE vbt_consultarContactos_pr (p_strCliente    IN VARCHAR2,
                                     p_vbt_consultarContactos OUT vbt_consultarContactos,
                                     p_resultado OUT VARCHAR2) AS 
                                cs_vbt_consultarContactos vbt_consultarContactos;
 
 
 BEGIN
         OPEN cs_vbt_consultarContactos FOR 
                        
                SELECT   DECODE(CONCIRIF,'0',PRECIRIF || '-' || CIRIF, PRECIRIF || '-' || CIRIF || '-' || CONCIRIF) CIRIF,  
                         INITCAP(NA) NOMBRE,          
                         INITCAP(GERENCIA) GERENCIA,   
                         INITCAP(CARGO) CARGO,         
                         TELEFONO TELEFONO             
                FROM     PRODUCCION.CONTACTOS  
                WHERE    CODPERCLI= p_strCliente;
                       
                        p_vbt_consultarContactos:= cs_vbt_consultarContactos;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_consultarContactos_pr;


PROCEDURE vbt_consultarCarteras_pr (p_strCliente    IN VARCHAR2,
                                       p_TAGCompartida      IN VARCHAR2,
                                       p_TAGIndividual IN VARCHAR2,
                                        p_TAGInactiva            IN VARCHAR2,
                                        p_TAGActiva  IN VARCHAR2,
                                            p_strCarteras            IN OUT VARCHAR2,
                                            p_vbt_consultarCarteras OUT SYS_REFCURSOR, 
                                            p_resultado OUT VARCHAR2,
                                            p_sql OUT VARCHAR2) AS 
                                            sqlstring varchar2(1000);    
 
 
 BEGIN
         p_strCarteras := trim(substr(p_strCarteras,2,100));
         p_strCarteras := trim(substr(p_strCarteras,1,length(p_strCarteras)-1));
 
 
         
        sqlstring:= ' SELECT DISTINCT CLI.CODCAR, 
                      DECODE(CLI.MODCAR,0,'''||p_TAGCompartida||''',1,'''||p_TAGIndividual||'''), 
                      DECODE(UPPER(CLI.STATCAR),''A'','''||p_TAGActiva||''',''I'','''||p_TAGInactiva||'''), 
                      DECODE(CLI.CONCIRIF,''0'',CLI.PRECIRIF || ''-'' || CLI.CIRIF, CLI.PRECIRIF || ''-'' || CLI.CIRIF || ''-'' || CLI.CONCIRIF) CIRIF, 
                      INITCAP(CLI.NA) NOMBRE, 
                      INITCAP(CLI.REFERIDO) ASESOR, 
                      INITCAP(CLI.RESPONSABLE) EJECUTIVO, 
                      INITCAP(CLI.DIRENVIO) DIRECCION
                      FROM PRODUCCION.CLIENTES CLI
                      WHERE CLI.CODPERCLI = '''||p_strCliente||''' 
                      AND CLI.CODCAR IN ('''||p_strCarteras||''' )';
          p_sql:= SQLSTRING;             
                        --execute immediate SQLSTRING into p_vbt_consultarCarteras;
                        open   p_vbt_consultarCarteras for SQLSTRING;       
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_consultarCarteras_pr;


    PROCEDURE vbt_buscar_template_pr  (p_login                    IN VARCHAR2,
                                        p_TxtNombreTemplate    IN VARCHAR2,
                                        p_TipoIdCtaCredito    IN VARCHAR2,
                                        p_TxtCuentaCredito        IN VARCHAR2,
                                        p_TipoCodBancoBeneficiario  IN VARCHAR2,
                                        p_CodBancoBeneficiario        IN VARCHAR2,
                                        p_TipoCodBancoIntermediario    IN VARCHAR2,
                                        p_CodBancoBancoIntermediario    IN VARCHAR2,
                                        p_CuentaFuturoCredito            IN VARCHAR2,
                                       p_vbt_template_handler OUT SYS_REFCURSOR,  
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2) AS 
                                      String varchar2(3200);
 

 
                
    begin            
                
             String := '(SELECT ''0'' RESULTADO 
                                  FROM   VBT_DIRECTORIO DIR 
                                  WHERE  DIR.ACTIVO=''A'' AND  DIR.USERNAME         = '''||p_login||''' ';
                       
            
               if p_TxtNombreTemplate is not null then
                  String := string ||     ' AND    UPPER(DIR.NOMBRE_REGISTRO)  = UPPER('''||p_TxtNombreTemplate||''') 
                     AND    (DIR.BENEFICIARY_TYPE <> '''||p_TipoIdCtaCredito||'''
                                  OR     DIR.BENEFICIARY_TYPE_NUMBER <> UPPER('''||p_TxtCuentaCredito||''')
                                  OR     DIR.BENEFICIARY_BANK_TYPE <> '''||p_TipoCodBancoBeneficiario||'''
                                  OR     DIR.BENEFICIARY_BANK_TYPE_NUMBER <> UPPER('''||p_CodBancoBeneficiario||''')';
               else
                 String := string ||     'AND    (DIR.BENEFICIARY_TYPE <> '''||p_TipoIdCtaCredito||'''
                                  AND     DIR.BENEFICIARY_TYPE_NUMBER <> UPPER('''||p_TxtCuentaCredito||''')
                                  AND     DIR.BENEFICIARY_BANK_TYPE <> '''||p_TipoCodBancoBeneficiario||'''
                                  AND     DIR.BENEFICIARY_BANK_TYPE_NUMBER <> UPPER('''||p_CodBancoBeneficiario||''')';
               end if;   
                       
           

            if p_CodBancoBancoIntermediario is not null then
                    String := string || ' OR    DIR.INTERMEDIARY_BANK_TYPE <> '''|| p_TipoCodBancoIntermediario||'''
                            OR    DIR.INTERMEDIARY_BANK_TYPE_NUMBER <> UPPER('''||p_CodBancoBancoIntermediario||''')';
                    end if;    
                    
            if p_CuentaFuturoCredito is not null then        
                    String := string || ' OR    DIR.FFC_NUMBER <> UPPER('''||p_CuentaFuturoCredito||''')';
            end if;
         
          String := string || ' ) ) UNION (
                                    SELECT ''-1'' Resultado 
                                    FROM   VBT_DIRECTORIO DIR 
                                    WHERE  DIR.ACTIVO=''A'' AND DIR.USERNAME         = '''||p_login ||''' ';
                       
            
             if p_TxtNombreTemplate is not null then
                  String := string ||     ' AND    UPPER(DIR.NOMBRE_REGISTRO)  = UPPER('''||p_TxtNombreTemplate||''') ';
              
              end if;   
                       
            String := string || ' AND    DIR.BENEFICIARY_TYPE = '''||p_TipoIdCtaCredito||''' 
                                    AND    DIR.BENEFICIARY_TYPE_NUMBER = UPPER('''||p_TxtCuentaCredito||''') 
                                    AND    DIR.BENEFICIARY_BANK_TYPE = '''||p_TipoCodBancoBeneficiario||''' 
                                    AND    DIR.BENEFICIARY_BANK_TYPE_NUMBER = UPPER('''||p_CodBancoBeneficiario||''')';

            if p_CodBancoBancoIntermediario is not null then
                String := string || ' AND    DIR.INTERMEDIARY_BANK_TYPE ='''|| p_TipoCodBancoIntermediario||'''
                                    AND    DIR.INTERMEDIARY_BANK_TYPE_NUMBER = UPPER('''||p_CodBancoBancoIntermediario||''')';
            
            end if;
                                    
            if p_CuentaFuturoCredito is not null then
                    String := string || ' AND    DIR.FFC_NUMBER = UPPER('''||p_CuentaFuturoCredito||''')';   
            end if;
            
            String := string || ' ) UNION ( 
                     SELECT ''-2'' Resultado  
                     FROM   VBT_DIRECTORIO DIR 
                     WHERE  DIR.ACTIVO=''A'' AND  DIR.USERNAME         = '''||p_login||'''';
                       
            
               if p_TxtNombreTemplate is not null then
                  String := string ||     ' AND    UPPER(DIR.NOMBRE_REGISTRO)  = UPPER('''||p_TxtNombreTemplate||''') ';
              
              end if;   
                       
            String := string || ' AND    DIR.BENEFICIARY_TYPE = '''||p_TipoIdCtaCredito||'''
                     AND    DIR.BENEFICIARY_TYPE_NUMBER = UPPER('''||p_TxtCuentaCredito||''') 
                     AND    DIR.BENEFICIARY_BANK_TYPE = '''||p_TipoCodBancoBeneficiario||'''
                     AND    DIR.BENEFICIARY_BANK_TYPE_NUMBER = UPPER('''||p_CodBancoBeneficiario ||''')';
            
            if p_CodBancoBancoIntermediario is not null then
                    String := string || ' AND    DIR.INTERMEDIARY_BANK_TYPE = '''||p_TipoCodBancoIntermediario||''' 
                        AND    DIR.INTERMEDIARY_BANK_TYPE_NUMBER = UPPER('''||p_CodBancoBancoIntermediario||''')'; 
            end if;
            
            if p_CuentaFuturoCredito is not null then
                String := string || '  AND    DIR.FFC_NUMBER = UPPER('''||p_CuentaFuturoCredito||''')';
            
            end if;
            
            String := string || ' )';
          p_sql := String;
      
           open   p_vbt_template_handler for String; 
          --  open  p_vbt_template_handler for string;   
                        p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_buscar_template_pr ;
 



  PROCEDURE vbt_insertar_directorio_pr(
             p_login  IN VARCHAR2, 
             numContrato IN VARCHAR2, 
             p_NombreBeneficiario IN VARCHAR2,
             p_TipoIdCtaCredito IN VARCHAR2,
             p_CuentaCredito IN VARCHAR2,
             p_EmailBeneficiario IN VARCHAR2,
             p_Direccion1Beneficiario IN VARCHAR2,
             p_Direccion2Beneficiario IN VARCHAR2,
             p_Direccion3Beneficiario IN VARCHAR2,
             p_TelefonoBeneficiario IN VARCHAR2,
             p_PaisBeneficiario IN VARCHAR2,
             p_TipoCodBancoBene IN VARCHAR2,
             p_CodBancoBene IN VARCHAR2,                    
             p_NombreBancoBene IN VARCHAR2,
             p_Direccion1BancoBene IN VARCHAR2,
             p_Direccion2BancoBene IN VARCHAR2,
             p_Direccion3BancoBene IN VARCHAR2,
             p_PaisDestino IN VARCHAR2,
             p_TipoCodBancoInterme IN VARCHAR2,
             p_CodBancoBancoInterme IN VARCHAR2,
             p_NombreBancoInterme IN VARCHAR2,
             p_Direccion1BancoInterme IN VARCHAR2,
             p_Direccion2BancoInterme IN VARCHAR2,
             p_Direccion3BancoInterme IN VARCHAR2,
             p_PaisBancoInterme IN VARCHAR2,
             p_CuentaFuturoCredito IN VARCHAR2,
             p_NombreFuturoCredito IN VARCHAR2,
             p_NombreTemplate IN VARCHAR2,
             p_TipoCodBancoBeneSwift IN VARCHAR2,
             p_CodBancoBeneSwift IN VARCHAR2,
             p_TipoCodInterBeneSwift IN VARCHAR2,
             p_CodBancoInterSwift IN VARCHAR2,
             p_resultado OUT VARCHAR2,
             sqls out VARCHAR2,
             P_BENEFICIARY_CITY IN VARCHAR2,
             P_BENEFICIARY_POSTAL_CODE IN VARCHAR2, 
             P_BENEFICIARY_BANK_CITY IN VARCHAR2,
             P_INTERMEDIARY_BANK_CITY IN VARCHAR2,
             P_BENEFICIARY_TYPE  IN VARCHAR2,
             P_BENEFICIARY_LASTNAME1  IN VARCHAR2,
             P_BENEFICIARY_LASTNAME2  IN VARCHAR2             
             ) AS   
             sqlstring varchar2(4000);  
 
 begin
 
                  
 
 sqlstring :='
 INSERT INTO VBTONLINE_TRANS.VBT_DIRECTORIO ( 
                      CODIGO, 
                      USERNAME, 
                      NUM_CONTRATO, 
                      TIPO_REGISTRO, 
                      BENEFICIARY_NAME, 
                      BENEFICIARY_TYPE, 
                      BENEFICIARY_TYPE_NUMBER, 
                      BENEFICIARY_EMAIL, 
                      BENEFICIARY_ADDRESS_L1, 
                      BENEFICIARY_ADDRESS_L2, 
                      BENEFICIARY_ADDRESS_L3, 
                      BENEFICIARY_PHONE, 
                      BENEFICIARY_COUNTRY, 
                      BENEFICIARY_BANK_TYPE, 
                      BENEFICIARY_BANK_TYPE_NUMBER, 
                      BENEFICIARY_BANK_NAME, 
                      BENEFICIARY_BANK_ADDRESS_L1, 
                      BENEFICIARY_BANK_ADDRESS_L2, 
                      BENEFICIARY_BANK_ADDRESS_L3, 
                      BENEFICIARY_BANK_COUNTRY, 
                      INTERMEDIARY_BANK_TYPE, 
                      INTERMEDIARY_BANK_TYPE_NUMBER, 
                      INTERMEDIARY_BANK_NAME, 
                      INTERMEDIARY_BANK_ADDRESS_L1, 
                      INTERMEDIARY_BANK_ADDRESS_L2, 
                      INTERMEDIARY_BANK_ADDRESS_L3, 
                      INTERMEDIARY_BANK_COUNTRY, 
                      FFC_NUMBER, 
                      FFC_NAME, 
                      USRID_CARGA, 
                      FECHA_CARGA, 
                      NOMBRE_REGISTRO,
                      BENEFICIARY_SWIFT_TYPE, 
                      BENEFICIARY_SWIFT_TYPE_NUMBER, 
                      INTERMEDIARY_SWIFT_TYPE, 
                      INTERMEDIARY_SWIFT_TYPE_NUMBER,
                      BENEFICIARY_CITY,
                      BENEFICIARY_POSTAL_CODE, 
                      BENEFICIARY_BANK_CITY,
                      INTERMEDIARY_BANK_CITY,
                      BENEFICIARY_TYPE_PERSON,
                      BENEFICIARY_LASTNAME1,
                      BENEFICIARY_LASTNAME2,
                      ACTIVO
                      ) 
                VALUES (DIRECTORIO_SEQ.NEXTVAL, 
                       '''|| p_login||''', 
                       '''||  numContrato||''' ,
                         ''TEO'' ,
                        '''|| p_NombreBeneficiario||''',
                        '''|| p_TipoIdCtaCredito||''',
                        '''|| p_CuentaCredito||''',
                        '''|| p_EmailBeneficiario||''',
                        '''|| p_Direccion1Beneficiario||''',
                        '''|| p_Direccion2Beneficiario||''',
                        '''|| p_Direccion3Beneficiario||''',
                        '''|| p_TelefonoBeneficiario||''',
                        '''|| p_PaisBeneficiario||''',
                        '''|| p_TipoCodBancoBene||''',
                        '''|| p_CodBancoBene||''',                    
                        '''|| p_NombreBancoBene||''',
                        '''|| p_Direccion1BancoBene||''',
                        '''|| p_Direccion2BancoBene||''',
                        '''|| p_Direccion3BancoBene||''',
                        '''|| p_PaisDestino||''',
                        '''|| p_TipoCodBancoInterme||''',
                        '''|| p_CodBancoBancoInterme||''',
                        '''|| p_NombreBancoInterme||''',
                        '''|| p_Direccion1BancoInterme||''',
                        '''|| p_Direccion2BancoInterme||''',
                        '''|| p_Direccion3BancoInterme||''',
                        '''|| p_PaisBancoInterme||''',
                        '''|| p_CuentaFuturoCredito||''',
                        '''|| p_NombreFuturoCredito||''',
                        '''|| p_login||''',
                         SYSDATE,
                        '''|| p_NombreTemplate||''',
                        '''|| p_TipoCodBancoBeneSwift ||''',
                        '''|| p_CodBancoBeneSwift ||''',
                        '''|| p_TipoCodInterBeneSwift ||''',
                        '''|| p_CodBancoInterSwift ||''',
                        '''|| P_BENEFICIARY_CITY ||''',
                        '''|| P_BENEFICIARY_POSTAL_CODE ||''',
                        '''|| P_BENEFICIARY_BANK_CITY ||''',
                        '''|| P_INTERMEDIARY_BANK_CITY ||''',
                         '''|| P_BENEFICIARY_TYPE ||''',
                        '''|| P_BENEFICIARY_LASTNAME1 ||''',
                        '''|| P_BENEFICIARY_LASTNAME2 ||''',
                        ''A'')';
                       execute immediate  sqlstring;
                sqls:=SQLSTRING; 
                p_resultado :='OK';         
                        commit;
             EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:=  SUBSTR(SQLERRM,1,300);  
  
  
    END vbt_insertar_directorio_pr;  


     PROCEDURE vbt_busca_directorio_pr (p_login                            IN VARCHAR2,
                                            p_codigoTemplate              IN VARCHAR2,
                                            p_strCmbTipoIdCtaCredito     IN VARCHAR2,
                                            p_strTxtCuentaCredito        IN VARCHAR2,
                                            p_CmbTipoCodBancoBene        IN VARCHAR2,
                                            p_strTxtCodBancoBene         IN VARCHAR2,
                                            p_CmbTipoCodBancoInterme    IN VARCHAR2,
                                            p_TxtCodBancoBancoInter        IN VARCHAR2,
                                            p_TxtCuentaFuturoCredito    IN VARCHAR2,
                                            p_vbt_busca_directorio      OUT vbt_busca_directorio,
                                            p_resultado                 OUT VARCHAR2) AS 
                                                cs_vbt_busca_directorio vbt_busca_directorio;
 
 
 BEGIN
         OPEN cs_vbt_busca_directorio FOR 
                       SELECT '-1' Resultado 
                     FROM   VBT_DIRECTORIO DIR 
                     WHERE  DIR.USERNAME         = p_login 
                     AND    DIR.CODIGO <> p_codigoTemplate
                     AND    DIR.BENEFICIARY_TYPE = p_strCmbTipoIdCtaCredito
                     AND    DIR.BENEFICIARY_TYPE_NUMBER = UPPER(p_strTxtCuentaCredito) 
                     AND    DIR.BENEFICIARY_BANK_TYPE = p_CmbTipoCodBancoBene 
                     AND    DIR.BENEFICIARY_BANK_TYPE_NUMBER = UPPER(p_strTxtCodBancoBene) 
                     AND    DIR.INTERMEDIARY_BANK_TYPE = p_CmbTipoCodBancoInterme
                     AND    DIR.INTERMEDIARY_BANK_TYPE_NUMBER = UPPER(p_TxtCodBancoBancoInter)
                     AND    DIR.FFC_NUMBER = UPPER(p_TxtCuentaFuturoCredito)
                     AND ACTIVO = 'A';  
                       
                        p_vbt_busca_directorio:= cs_vbt_busca_directorio;       
                        p_resultado:='OK'; 
                        commit;                                                                                                                                                                                                                                                                                                                                                                                                
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_busca_directorio_pr;



PROCEDURE vbt_update_directo_pr (
   
             p_TxtNombreBeneficiario         IN VARCHAR2,
             p_strCmbTipoIdCtaCredito         IN VARCHAR2,
             p_strTxtCuentaCredito             IN VARCHAR2,
             p_TxtEmailBeneficiario            IN VARCHAR2,
             p_strTxtDireccion1Bene            IN VARCHAR2,
             p_strTxtDireccion2Bene            IN VARCHAR2,
             p_strTxtDireccion3Bene            IN VARCHAR2,
             p_strTxtTelefonoBene            IN VARCHAR2,
             p_strCmbPaisBeneficiario        IN VARCHAR2,
             p_strCmbTipoCodBancoBene        IN VARCHAR2,
             p_strTxtCodBancoBene            IN VARCHAR2,
             p_strTxtNombreBancoBene        IN VARCHAR2,
             p_TxtDireccion1BancoBene        IN VARCHAR2,
             p_TxtDireccion2BancoBene        IN VARCHAR2,
             p_TxtDireccion3BancoBene        IN VARCHAR2,
             p_strCmbPaisDestino            IN VARCHAR2,
             p_TxtCodBancoBancoInter        IN VARCHAR2,
             p_CmbTipoCodBancoInter            IN VARCHAR2,
             p_TxtNombreBancoInter            IN VARCHAR2,
             p_TxtDireccion1BancoInter        IN VARCHAR2,
             p_TxtDireccion2BancoInter        IN VARCHAR2,
             p_TxtDireccion3BancoInter        IN VARCHAR2,
             p_strCmbPaisBancoInter            IN VARCHAR2,
             p_strTxtCuentaFuturoCre        IN VARCHAR2,
             p_strTxtNombreFuturoCre        IN VARCHAR2,
             p_login                        IN VARCHAR2,
             p_codigoTemplate               IN VARCHAR2,
             p_benefswifttypecode           IN VARCHAR2,
             p_benefswiftcodenumber         IN VARCHAR2,
             p_interswifttypecode           IN VARCHAR2,
             p_interswiftcodenumber         IN VARCHAR2,
             p_resultado                 OUT VARCHAR2,
             P_BENEFICIARY_CITY IN VARCHAR2,
             P_BENEFICIARY_POSTAL_CODE IN VARCHAR2, 
             P_BENEFICIARY_BANK_CITY IN VARCHAR2,
             P_INTERMEDIARY_BANK_CITY IN VARCHAR2,
             P_BENEFICIARY_TYPE_PERSON  IN VARCHAR2,
             P_BENEFICIARY_LASTNAME1  IN VARCHAR2,
             P_BENEFICIARY_LASTNAME2  IN VARCHAR2
             ) as
            sqlstring varchar2(3000);    
   
   
   begin
   
   
   
   sqlstring:= 'UPDATE VBTONLINE_TRANS.VBT_DIRECTORIO SET                           
                BENEFICIARY_NAME = '''||p_TxtNombreBeneficiario||'''
                ,BENEFICIARY_TYPE =  '''||p_strCmbTipoIdCtaCredito||'''
                ,BENEFICIARY_TYPE_NUMBER = '''||p_strTxtCuentaCredito||'''
                ,BENEFICIARY_EMAIL =  '''||p_TxtEmailBeneficiario||'''
                ,BENEFICIARY_ADDRESS_L1 = '''||p_strTxtDireccion1Bene||'''
                ,ESTATUS_APROBACION = ''N''';
            --if    p_strTxtDireccion2Bene is not null then
                sqlstring:=sqlstring || ' ,BENEFICIARY_ADDRESS_L2 = '''||p_strTxtDireccion2Bene||'''';
           -- end if;
           -- if     p_strTxtDireccion3Bene  is not null then
                sqlstring:=sqlstring || '  ,BENEFICIARY_ADDRESS_L3 = '''||p_strTxtDireccion3Bene||'''';
           -- end if;
           -- if  p_strTxtTelefonoBene is not null then
                sqlstring:=sqlstring || '   ,BENEFICIARY_PHONE = '''||p_strTxtTelefonoBene||'''';
           -- end if;
                    sqlstring:= sqlstring || '  ,BENEFICIARY_COUNTRY = '''||p_strCmbPaisBeneficiario||'''
                      , BENEFICIARY_BANK_TYPE = '''||p_strCmbTipoCodBancoBene||'''
                      , BENEFICIARY_BANK_TYPE_NUMBER = '''||p_strTxtCodBancoBene||'''
                      , BENEFICIARY_BANK_NAME = '''||p_strTxtNombreBancoBene||'''
                      , BENEFICIARY_BANK_ADDRESS_L1 = '''||p_TxtDireccion1BancoBene ||'''';
              
            --if p_TxtDireccion2BancoBene is not null then
                sqlstring:= sqlstring || '  ,BENEFICIARY_BANK_ADDRESS_L2 = '''|| p_TxtDireccion2BancoBene||'''';
            --end if;
              
          -- if p_TxtDireccion3BancoBene is not null then
                sqlstring:= sqlstring || '  ,BENEFICIARY_BANK_ADDRESS_L3 = '''|| p_TxtDireccion3BancoBene||'''';
           -- end if;
              sqlstring:= sqlstring || ' ,BENEFICIARY_BANK_COUNTRY ='''||p_strCmbPaisDestino ||'''';
           
           --if p_TxtCodBancoBancoInter is not null then
                sqlstring:= sqlstring || '  , INTERMEDIARY_BANK_TYPE =  '''||p_CmbTipoCodBancoInter||'''
                                , INTERMEDIARY_BANK_TYPE_NUMBER = '''||p_TxtCodBancoBancoInter||'''
                                , INTERMEDIARY_BANK_NAME = '''||p_TxtNombreBancoInter||'''
                                , INTERMEDIARY_BANK_ADDRESS_L1 = '''||p_TxtDireccion1BancoInter||'''';
          -- end if;
            
           -- if p_TxtDireccion2BancoInter is not null then
                    sqlstring:= sqlstring || ' ,INTERMEDIARY_BANK_ADDRESS_L2 =  '''||p_TxtDireccion2BancoInter||'''';
           -- end if;

          --  if p_TxtDireccion3BancoInter is not null then
                    sqlstring:= sqlstring || ' ,INTERMEDIARY_BANK_ADDRESS_L3 =  '''||p_TxtDireccion3BancoInter||'''';
          --  end if;

          --  if p_TxtCodBancoBancoInter is not null then
                sqlstring:= sqlstring || ' ,INTERMEDIARY_BANK_COUNTRY = '''|| p_strCmbPaisBancoInter||'''';
          --  end if;

          --  if  p_strTxtCuentaFuturoCre is not null then
                    sqlstring:= sqlstring || ', FFC_NUMBER = '''||p_strTxtCuentaFuturoCre||'''
                                , FFC_NAME =  '''||p_strTxtNombreFuturoCre||'''';
          --  end if;                    
          --  if p_benefswiftcodenumber is not null then
                    sqlstring:= sqlstring || ', BENEFICIARY_SWIFT_TYPE = '''|| p_benefswifttypecode ||'''
                                              , BENEFICIARY_SWIFT_TYPE_NUMBER = '''|| p_benefswiftcodenumber ||'''';
           -- end if;
            
           -- if p_interswiftcodenumber is not null then
                    sqlstring:= sqlstring || ', INTERMEDIARY_SWIFT_TYPE = '''|| p_interswifttypecode ||'''
                                              , INTERMEDIARY_SWIFT_TYPE_NUMBER = '''|| p_interswiftcodenumber ||'''';
           -- end if;
            
         
                   sqlstring:= sqlstring || ' ,BENEFICIARY_CITY = '''|| P_BENEFICIARY_CITY||'''';
                   
                   sqlstring:= sqlstring || ' ,BENEFICIARY_POSTAL_CODE = '''|| P_BENEFICIARY_POSTAL_CODE||'''';
                   
                   sqlstring:= sqlstring || ' ,BENEFICIARY_BANK_CITY = '''|| P_BENEFICIARY_BANK_CITY||'''';
                   
                   sqlstring:= sqlstring || ' ,INTERMEDIARY_BANK_CITY = '''|| P_INTERMEDIARY_BANK_CITY||'''';
           
            sqlstring:= sqlstring || ' ,BENEFICIARY_TYPE_PERSON = '''|| P_BENEFICIARY_TYPE_PERSON||'''';
            sqlstring:= sqlstring || ' ,BENEFICIARY_LASTNAME1 = '''|| P_BENEFICIARY_LASTNAME1||'''';
            sqlstring:= sqlstring || ' ,BENEFICIARY_LASTNAME2 = '''|| P_BENEFICIARY_LASTNAME2||'''';
           
           
           
                    sqlstring:= sqlstring || '    , USRID_MODIFICA = '''||p_login||'''
                                , FECHA_MODIFICA = SYSDATE 
                                where CODIGO = '''|| p_codigoTemplate ||'''';
            
            
            execute immediate sqlstring;
            p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
            EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
            WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                p_resultado:= SUBSTR(SQLERRM,1,300); 
         
         end vbt_update_directo_pr;

  PROCEDURE vbt_borrar_directorio_pr (p_login                            IN VARCHAR2,
                                      p_codigoTemplate              IN VARCHAR2,
                                      p_resultado                 OUT VARCHAR2) AS
                                             
 
 BEGIN
 
    UPDATE VBTONLINE_TRANS.VBT_DIRECTORIO D
    SET    ACTIVO = 'I', D.USRID_MODIFICA = P_LOGIN, D.FECHA_MODIFICA = SYSDATE
    
    WHERE  CODIGO = p_codigoTemplate;          
         
       p_resultado:= 'OK';    
       commit;                                                                                                                                                                                                                                                                                                                                                                                              
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_borrar_directorio_pr;
 
 
     
   PROCEDURE vbt_inverio_bloqueo_pr (p_strCarteras IN OUT VARCHAR2,
                                            p_vbt_inverio_bloqueo_pr OUT vbt_otrasinver_cartera, 
                                            p_resultado OUT VARCHAR2,
                                            p_sql OUT VARCHAR2) AS 
                                             SQLSTRING VARCHAR2(2000);
   begin
   
   --p_strCarteras := trim(substr(p_strCarteras,2,100));
 
   p_strCarteras := trim(substr(p_strCarteras,2,length(p_strCarteras)-2));
   
   
   --  OPEN cs_vbt_cuentas_edo_cuenta FOR 
    SQLSTRING :='SELECT A.CODEMP EMPRESA, 
             INITCAP(A.NA) NOMBRE_FONDO,
             A.CODCAR CARTERA, 
             A.FECHCIERRE FECHA_CIERRE ,
             A.CODMON,
             DECODE(P.VALOR,''8'',P.VALOR,''6'') DECIMALES
             FROM FONDOMUTUAL_VBT.SALDOS_FONDO_MUTUAL_1 A, PRODUCTO_EMPRESA@FONDOMUTUAL_VBT P, 
             PRODUCCION.CTAPER E 
             WHERE A.CODCAR IN ('''||p_strCarteras||''') 
             AND A.CODEMP IN (SELECT CODEMP FROM OTRAS_INVERSIONES@FONDOMUTUAL_VBT WHERE APLICACION=''ONLINE'' and visible=''S'') 
             AND A.CODEMP = E.CODPER
             AND A.CODEMP = P.CODEMP
             AND P.CODPRODINT = ''0000000025'''
              ;
   p_sql := SQLSTRING;      
          open  p_vbt_inverio_bloqueo_pr for sqlstring;     
    --    p_vbt_cuentas_edo_cuenta:= cs_vbt_cuentas_edo_cuenta;  
        p_resultado:= 'OK'; 
     
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_inverio_bloqueo_pr;   
   
   
    PROCEDURE vbt_consulta_paises_excluidos (c_paises_excluidos out vbt_paises_excluidos, p_resultado OUT VARCHAR2)
    IS
    BEGIN
       open c_paises_excluidos
       for select bank_type_number, bank_type, user_name, fecha_creacion, destino_a_bloquear from vbt_codigos_banco_bloqueados;
    p_resultado:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
    END;


    PROCEDURE vbt_inserta_paises_excluidos (P_BANK_TYPE_NUMBER VARCHAR2, P_BANK_TYPE VARCHAR2,  P_USER_NAME VARCHAR2, P_FECHA_CREACION DATE, P_DESTINO_A_BLOQUEAR VARCHAR2, p_resultado OUT VARCHAR2)
    IS
    BEGIN
        insert into vbt_codigos_banco_bloqueados
        values (P_BANK_TYPE_NUMBER, P_BANK_TYPE, P_USER_NAME ,P_FECHA_CREACION, P_DESTINO_A_BLOQUEAR);
    p_resultado:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);   
    END;

    PROCEDURE vbt_logAction_pr (p_username VARCHAR2, p_id_accion VARCHAR2, p_id_app VARCHAR2, p_id_object VARCHAR2, p_AFFECTED_OBJECT_ID VARCHAR2, p_IP VARCHAR2, p_COMMENTS VARCHAR2, p_resultado OUT VARCHAR2)
    IS
    BEGIN
        insert into COMMON_LOG (USERNAME,ID_ACTION,ID_APP,ID_OBJECT,AFFECTED_OBJECT_ID,ACTION_DATE,IP,COMMENTS)
        values (p_username, p_id_accion, p_id_app, p_id_object, p_AFFECTED_OBJECT_ID,SYSDATE,  p_IP, p_COMMENTS);
    p_resultado:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);   
    END vbt_logAction_pr;
    
    PROCEDURE vbt_logActionFC_pr (p_username VARCHAR2, p_id_accion VARCHAR2, p_id_app VARCHAR2, p_id_object VARCHAR2, p_AFFECTED_OBJECT_ID VARCHAR2, p_IP VARCHAR2, p_COMMENTS VARCHAR2, p_num_contrato VARCHAR2, p_resultado OUT VARCHAR2)
    IS
    BEGIN
        insert into COMMON_LOG (USERNAME,ID_ACTION,ID_APP,ID_OBJECT,AFFECTED_OBJECT_ID,ACTION_DATE,IP,COMMENTS, NUM_CONTRATO)
        values (p_username, p_id_accion, p_id_app, p_id_object, p_AFFECTED_OBJECT_ID,SYSDATE,  p_IP, p_COMMENTS, p_num_contrato);
    p_resultado:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);   
    END vbt_logActionFC_pr;

 PROCEDURE vbt_parametrosGlobalesFC  ( cs_parametrosGlobalesFC OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2)
 IS
 BEGIN
    OPEN cs_parametrosGlobalesFC FOR
        SELECT VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
        FROM parametros_globales_fc;
    
        p_resultado:= 'OK';    
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
 END vbt_parametrosGlobalesFC;

 PROCEDURE vbt_parametrosPersonales_FC  (p_num_contrato VARCHAR2, cs_parametrosPersonalesFC OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2)
 IS
 BEGIN
    OPEN cs_parametrosPersonalesFC FOR
        SELECT num_max_trans_diarias, mto_max_trans_diarias, mto_max_por_trans, correo_notificacion
        FROM parametros_personales_fc
        WHERE num_contrato=p_num_contrato;
    
        p_resultado:= 'OK';    
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
 END vbt_parametrosPersonales_FC; 
 
 
 PROCEDURE vbt_logParametrosPersonalesFC  ( p_num_max_trans_diarias NUMBER, p_mto_max_trans_diarias NUMBER, p_mto_max_por_trans NUMBER, p_correo_notificacion VARCHAR2, p_codpercli VARCHAR2, p_resultado OUT VARCHAR2)
 IS
    existe number;
 BEGIN
        SELECT count(*) INTO existe
        FROM log_parametros_personales_fc
        WHERE codpercli=p_codpercli;
 
        IF existe=0 THEN
        
            INSERT INTO log_parametros_personales_fc(num_max_trans_diarias,mto_max_trans_diarias,mto_max_por_trans,correo_notificacion, codpercli)
            VALUES (p_num_max_trans_diarias, p_mto_max_trans_diarias, p_mto_max_por_trans, p_correo_notificacion,SYSDATE);

        ELSE
        
            UPDATE log_parametros_personales_fc
            SET num_max_trans_diarias=p_num_max_trans_diarias,
                mto_max_trans_diarias=p_mto_max_trans_diarias,
                mto_max_por_trans=p_mto_max_por_trans,
                fecha=SYSDATE                
            WHERE codpercli=p_codpercli;
        
        END IF;
 
        p_resultado:= 'OK';    
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
 END vbt_logParametrosPersonalesFC; 
 
 PROCEDURE vbt_guardarParametrosP_FC_pr (
                                      P_NUM_CONTRATO  IN VARCHAR2,
                                      P_VBT_NMTD       IN NUMBER,
                                       P_VBT_MMTD       IN NUMBER,
                                      P_VBT_MMTO       IN NUMBER,                                      
                                      P_CORREO  IN VARCHAR2,
                                      p_resultado OUT VARCHAR2) AS 
                                     sqlstring    VARCHAR2(1000); 
                                      VAL_CODPER  VARCHAR2(10):=NULL;        
 
 
 
 BEGIN
        
        BEGIN
            SELECT  NUM_CONTRATO
            INTO VAL_CODPER
            FROM VBTONLINE_TRANS.PARAMETROS_PERSONALES_FC
            WHERE NUM_CONTRATO=P_NUM_CONTRATO;
        EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    VAL_CODPER:=NULL;
                    
                END ;
        
        IF VAL_CODPER IS NULL THEN
            sqlstring := 'INSERT INTO VBTONLINE_TRANS.PARAMETROS_PERSONALES_FC (
                            NUM_MAX_TRANS_DIARIAS, MTO_MAX_TRANS_DIARIAS, MTO_MAX_POR_TRANS, CORREO_NOTIFICACION, NUM_CONTRATO)
                           VALUES('''||P_VBT_NMTD||''','''||P_VBT_MMTD||''','''||P_VBT_MMTO||''','''||P_CORREO||''','''||P_NUM_CONTRATO||''')';
                           COMMIT;
                           
        ELSE
            sqlstring := 'UPDATE VBTONLINE_TRANS.PARAMETROS_PERSONALES_FC SET  ';
                
                        sqlstring := sqlstring || ' NUM_MAX_TRANS_DIARIAS =     '''||P_VBT_NMTD||'''';
             
                        sqlstring := sqlstring || ' ,MTO_MAX_TRANS_DIARIAS = '''||P_VBT_MMTD ||'''';
              
                        sqlstring := sqlstring || ' ,MTO_MAX_POR_TRANS = '''||P_VBT_MMTO ||'''';
               
                        sqlstring := sqlstring || ' ,CORREO_NOTIFICACION = '''||P_CORREO ||'''';    
                
                       
                    sqlstring := sqlstring || ' WHERE NUM_CONTRATO = '''||P_NUM_CONTRATO|| '''';
                COMMIT;    
        END IF;
         EXECUTE IMMEDIATE sqlstring;
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_guardarParametrosP_FC_pr; 
 
 PROCEDURE vbt_usuarioFirmaConjunta_pr (
                    p_strConsulta             in VARCHAR2, 
                    p_strCargador         in VARCHAR2, 
                    p_strAprobador     in VARCHAR2, 
                    p_strLiberador         in VARCHAR2, 
                    p_strAuditor         in VARCHAR2, 
                    p_strActiva         in VARCHAR2, 
                    p_strInactiva         in VARCHAR2, 
                    p_strCancelada         in VARCHAR2, 
                    p_strBloqueado         in VARCHAR2, 
                    p_strDesconocido     in VARCHAR2, 
                    p_strTxtUsuario     in VARCHAR2,
                    p_strTxtNombre        in VARCHAR2,
                    p_strTxtCIRIF        in VARCHAR2,
                    p_strCmbTipoUsuario in VARCHAR2,
                    p_strCmbEstatus        in VARCHAR2,
                    p_strOrden          in VARCHAR2,
                    hdnAccion           in VARCHAR2,
                    numContrato           in VARCHAR2,
                    loginUsuario           in VARCHAR2,
                    vbt_usuarioFirmaConjunta  OUT SYS_REFCURSOR,
                    p_resultado OUT VARCHAR2,
                    p_salida OUT VARCHAR2,
                    p_strPersonalizado  in VARCHAR2,
                    p_strCartera  in VARCHAR2 ) AS 
                      SQLSTRING  VARCHAR2(2000);
                    SQLELEM    VARCHAR2(200);
                    whereSQL   VARCHAR2(500);
                    ordenSQL   VARCHAR2(100);                                    

  CURSOR ELEMENTO IS 
  SELECT codelemento,
  descripcion
  FROM elementos_tipos 
  WHERE codtipo ='0000000007'
  ORDER BY DESCRIPCION ASC;

                    
BEGIN

   SQLSTRING :='SELECT DISTINCT U.LOGIN,                                     
         TO_CHAR(U.FECHA_INGRESO,''dd/mm/yyyy hh:mi A.M.''), 
         U.NOMBRES, 
         U.EMAIL, 
         U.TELEFONO_CELULAR || DECODE(U.TELEFONO_CELULAR,NULL,U.TELEFONO, DECODE(U.TELEFONO,NULL,'''','' / '' || U.TELEFONO)) TELEFONOS,  
         DECODE(U.TIPO,''5'','''||p_strConsulta|| ''',''6'','''||p_strCargador|| ''',''7'','''||p_strAprobador|| ''',''8'','''||p_strLiberador|| ''',''9'','''||p_strAuditor|| ''',''11'','''||p_strPersonalizado|| ''') TIPO_USUARIO,
         U.DIRECCION, 
         TO_CHAR(U.FECHA_LOGIN,''dd/mm/yyyy hh:mi A.M.'') ULTIMO_LOGIN, 
         DECODE(U.STATUS_CUENTA,''A'','''||p_strActiva|| ''',''I'','''||p_strInactiva|| ''',''C'','''||p_strCancelada|| ''',''B'','''||p_strBloqueado|| ''','''||p_strDesconocido|| ''') STATUS, 
        U.TIPO,
         U.STATUS_CUENTA,
         U.CIRIF,
         TO_CHAR(U.FECHA_STATUS,''dd/mm/yyyy'')
         FROM vbt_users U, 
         vbt_users_contrato UC';
         if      p_strCartera is not null and p_strCartera <> 'Default'  then
          SQLSTRING := SQLSTRING || ', vbt_contrato_carteras CC ';
         end if;
         
         SQLSTRING := SQLSTRING || ' WHERE ';
         
        BEGIN
        FOR I IN ELEMENTO LOOP
                    BEGIN
                    SQLELEM := SQLELEM|| '''' || I.codelemento || '''' || ',';
                    
                   
                    EXCEPTION 
                    WHEN NO_DATA_FOUND THEN
                          SQLELEM:=',';
                    END;
        END LOOP;
           SQLELEM:= ' TIPO IN ('|| SUBSTR(SQLELEM,1,(LENGTH(SQLELEM)-1)) ||') ';  
        END;
        SQLSTRING :=     SQLSTRING || SQLELEM;
        
        --SQLSTRING :=SQLSTRING || ' AND UC.num_contrato = '''|| numContrato || '''';
        IF ((numContrato IS NOT NULL) OR (numContrato <> '')) THEN
          SQLSTRING := SQLSTRING || ' AND UC.num_contrato = '''|| numContrato || '''';
        END IF;
        SQLSTRING :=SQLSTRING || ' AND UC.LOGIN = U.LOGIN ';
        
        IF hdnAccion IS NOT NULL THEN
          
            if p_strTxtUsuario is not null or p_strTxtUsuario <> '' then
                  whereSQL := ' AND U.LOGIN =  '''|| p_strTxtUsuario || '''';
            else
                if p_strTxtNombre is not null or p_strTxtNombre <> '' then
                     whereSQL := whereSQL || ' AND UPPER(NOMBRES) like ''' ||'%' ||p_strTxtNombre||'%' ||''''; 
                end if;     
                     
               if p_strTxtCIRIF is not null or p_strTxtCIRIF <> ''   then      
                     whereSQL := whereSQL || ' AND CIRIF like ''' ||'%' ||p_strTxtCIRIF||'%' ||'''';
                end if;     
                if p_strCmbTipoUsuario is not null and p_strCmbTipoUsuario <> 'Default' then
                        whereSQL := whereSQL || 'AND TIPO =  '''|| p_strCmbTipoUsuario || '''';
                        
                end if;        
                if      p_strCmbEstatus is not null and p_strCmbEstatus <> 'Default'  then
                       whereSQL := whereSQL || ' AND STATUS_CUENTA = '''|| p_strCmbEstatus || '''';
                end if;     
                if      p_strCartera is not null and p_strCartera <> 'Default'  then
                       whereSQL := whereSQL || ' AND CC.CODCAR like ''%'|| p_strCartera || '%''';
                       whereSQL := whereSQL || ' AND CC.NUM_CONTRATO = UC.NUM_CONTRATO ';
                end if; 
            end if;
          
         
        SQLSTRING :=     SQLSTRING || whereSQL;
        --ELSE
          
        END IF;
         
         if p_strOrden ='Nombre' then
            ordenSQL:= 'ORDER BY NOMBRES ASC';
        elsif p_strOrden='Tipo' then
            ordenSQL:= ' ORDER BY TIPO_USUARIO ASC';
        elsif p_strOrden='Estatus'then
            ordenSQL:= ' ORDER BY STATUS_CUENTA ASC';      
        elsif p_strOrden='Ultimo_Acceso' then
            ordenSQL:= ' ORDER BY FECHA_LOGIN DESC';      
        elsif p_strOrden='Fecha_Creacion' then
            ordenSQL:= ' ORDER BY FECHA_INGRESO DESC';
        else 
            ordenSQL:= ' ORDER BY LOGIN ASC';
        end if;  
        SQLSTRING :=     SQLSTRING || ordenSQL;
        p_salida:=SQLSTRING;
        open  vbt_usuarioFirmaConjunta for SQLSTRING;
                                                                                                                                                                                                                                                                                                                                                                                    
        p_resultado:='OK';
    EXCEPTION
    WHEN OTHERS THEN
    p_resultado:= SUBSTR(SQLERRM,1,300); 
  END vbt_usuarioFirmaConjunta_pr;
  
  PROCEDURE vbt_agregarUsuarioFC_pr (p_strTxtUsuarioAgregar         in VARCHAR2,
                                 p_strRandomPassword            in VARCHAR2,
                                 p_login                        in VARCHAR2,
                                 p_strTxtdireccion              in VARCHAR2,
                                 p_strTxtTlfCelularAgregar      in VARCHAR2,
                                 p_strTxtTelefonoAgregar        in VARCHAR2,
                                 p_strTxtEmailAgregar           in VARCHAR2,
                                 p_miPasswTemp                  in VARCHAR2,
                                 p_strCmbTipoUsuarioAgregar     in VARCHAR2,
                                 p_strCmbPRECIRIFAgregar        in VARCHAR2,
                                 p_strTxtCIRIFAgregar           in VARCHAR2,
                                 p_strTxtNombre                 in VARCHAR2,
                                 p_strTxtgrupo                  in VARCHAR2,
                                 p_strCodPais                   in VARCHAR2,
                                 p_resultado                    out VARCHAR2,
                                 p_inte_login                   out varchar2,
                                 p_roles                       in VARCHAR2,
                                 p_usuarioSesion               in VARCHAR2) AS 
                                 SQLSTRING  VARCHAR2(2500);
                                 p_encryp_password    varchar2(100);
                                 p_loginn              varchar2(20);
 
    BEGIN
    SELECT LOGIN
    into p_loginn
    FROM VBT_USERS 
    WHERE LOGIN = LOWER(p_strTxtUsuarioAgregar);
    p_inte_login:='Usuario Registrado';
    p_resultado:='Usuario Registrado';
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
        
        INSERT INTO VBT_USERS (LOGIN, FECHA_INGRESO, DIRECCION, TELEFONO_CELULAR, TELEFONO, EMAIL, PASSWORD, 
        TIPO, NOMBRES, CIRIF, STATUS_CUENTA, FECHA_STATUS,CODIGO_PAIS) 
        VALUES (LOWER(p_strTxtUsuarioAgregar),
                SYSDATE,
                p_strTxtdireccion,
                p_strTxtTlfCelularAgregar,
                p_strTxtTelefonoAgregar,
                lower(p_strTxtEmailAgregar),
                SEGURIDAD.ENCRIPTA(p_miPasswTemp),
                p_strCmbTipoUsuarioAgregar,
                INITCAP(p_strTxtNombre),
                p_strCmbPRECIRIFAgregar || '-' || p_strTxtCIRIFAgregar,
                'A',
                SYSDATE,
                p_strCodPais);
                
                
          INSERT INTO USUGRP_v1 (LOGIN, CODGRP, CODSIS, CODCIA, FECHASIG, USRID)
                VALUES (LOWER(p_strTxtUsuarioAgregar),
                        p_strTxtgrupo,
                        'ONLINE', 
                        'VBT',
                         SYSDATE, 
                 p_usuarioSesion);  
        
        --Es usuario personalizado y debe ser agregado de otra manera       
        IF p_strCmbTipoUsuarioAgregar='11' THEN
                            
             SQLSTRING:='INSERT INTO USRCIAOPC_V1(LOGIN,CODSIS, CODCIA, CODOPC,TIPACC, USRID)
                            SELECT DISTINCT '''|| LOWER(p_strTxtUsuarioAgregar)||''', 
                                    ''ONLINE'', ''VBT'', OPC.CODOPC,OPC.TIPACC, '''||p_usuarioSesion||'''
                                     FROM FC_OPCACC OPC, FC_ROLCIAOPC ROL
                                    WHERE OPC.CODOPC=ROL.CODOPC AND  ROL.CODROL IN ('||p_roles||')';
                            EXECUTE IMMEDIATE SQLSTRING;
                            
             SQLSTRING:='INSERT INTO FC_USUROL(LOGIN,CODROL,FECHAREG)
                            SELECT DISTINCT '''|| LOWER(p_strTxtUsuarioAgregar)||''', 
                                    CODROL, SYSDATE
                                    FROM FC_ROLCIAOPC
                                    WHERE CODROL IN ('||p_roles||')';
                            EXECUTE IMMEDIATE SQLSTRING;
                   
         END IF;      
  
        p_resultado:='OK';
 
     END vbt_agregarUsuarioFC_pr;

    
 
PROCEDURE vbt_parametros_globales_pr (p_datosPorDefecto OUT vbt_datos_diseneBanco,
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2,
                                      p_tipo IN VARCHAR2) AS 
                                     sqlstring    VARCHAR2(1500);        
 
 
 
 BEGIN
       IF p_tipo='GC' THEN
       --Clientes Full Acceso
         sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.vbt_movimi_operaciones';
      ELSE
      --Firmas Conjuntas
         sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.PARAMETROS_GLOBALES_FC';
      END IF;

        
         open  p_datosPorDefecto for sqlstring;   
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_parametros_globales_pr;

PROCEDURE vbt_parametros_personales_pr (p_codpercli   IN VARCHAR2,
                                      P_NUM_CONTRATO  IN VARCHAR2,
                                      P_VBT_NMTD       IN NUMBER,
                                      P_VBT_MMTD       IN NUMBER,
                                      P_VBT_MMINTO     IN NUMBER,
                                      P_VBT_MMTO       IN NUMBER,
                                      P_EX_NMTD        IN NUMBER,
                                      P_EX_MMTD        IN NUMBER,
                                      P_EX_MMINTO      IN NUMBER,
                                      P_EX_MMTO        IN NUMBER,
                                      P_CUENTA  IN VARCHAR2,
                                      P_TIPO  IN VARCHAR2,
                                      P_TIPO_PARAMETRO  IN VARCHAR2,
                                      p_resultado OUT VARCHAR2) AS 
                                      sqlstring    VARCHAR2(1000); 
                                      VAL_NUMCONTRATO  VARCHAR2(10):=NULL;        
 
 
 
         BEGIN
         
                BEGIN
                    SELECT  NUM_CONTRATO
                    INTO VAL_NUMCONTRATO
                    FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
                    WHERE NUM_CONTRATO=P_NUM_CONTRATO;
                EXCEPTION
                        WHEN NO_DATA_FOUND THEN
                            VAL_NUMCONTRATO:=NULL;
                            
                        END ;

            IF P_TIPO='VBT' THEN
            
                 IF P_TIPO_PARAMETRO='GC' THEN
                    sqlstring := 'UPDATE VBTONLINE_TRANS.vbt_movimi_operaciones SET ';
                 ELSE
                    sqlstring := 'UPDATE VBTONLINE_TRANS.PARAMETROS_GLOBALES_FC SET ';
                 END IF;
                
                    IF P_VBT_NMTD > 0 THEN
                            sqlstring := sqlstring || ' VBT_NMTD =     '''||P_VBT_NMTD||'''';
                    END IF;
                    
                    IF P_VBT_MMTD > 0 THEN
                            sqlstring := sqlstring || ' ,VBT_MMTD = '''||P_VBT_MMTD ||'''';
                    END IF;
                    
                    IF P_VBT_MMINTO > 0 THEN
                            sqlstring := sqlstring || ' ,VBT_MMINTO = '''||P_VBT_MMINTO ||'''';    
                    END IF;
                    
                    IF P_VBT_MMTO > 0 THEN
                            sqlstring := sqlstring || ' ,VBT_MMTO = '''||P_VBT_MMTO ||'''';        
                    END IF;
                    
                    IF P_EX_NMTD > 0 THEN
                            sqlstring := sqlstring || ' ,EX_NMTD = '''||P_EX_NMTD ||'''';
                    END IF;
                    
                    IF P_EX_MMTD > 0 THEN
                            sqlstring := sqlstring || ' ,EX_MMTD = '''||P_EX_MMTD ||'''';
                    END IF;
                    
                    IF P_EX_MMINTO > 0 THEN
                            sqlstring := sqlstring || ' ,EX_MMINTO = '''||P_EX_MMINTO ||'''';
                    END IF;
                    
                    IF P_EX_MMTO > 0 THEN
                            sqlstring := sqlstring || ' ,EX_MMTO = '''||P_EX_MMTO ||'''';            
                    END IF;
                    
                    --COMMIT;
            ELSE  

                    
                    if VAL_NUMCONTRATO IS NULL THEN
                        sqlstring := 'INSERT INTO VBTONLINE_TRANS.vbt_movimi_ope_codpercli (
                                       NUM_CONTRATO, VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                                       EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM)
                                       VALUES('''||P_NUM_CONTRATO||''','''||P_VBT_NMTD||''','''||P_VBT_MMTD||''',
                                       '''||P_VBT_MMINTO||''','''||P_VBT_MMTO||''','''||P_EX_NMTD||''','''||P_EX_MMTD||''',
                                       '''||P_EX_MMINTO||''','''||P_EX_MMTO||''','''||P_CUENTA||''')';
                                       --COMMIT;
                        
                    ELSE
                        sqlstring := 'UPDATE VBTONLINE_TRANS.vbt_movimi_ope_codpercli  SET ';
                        IF P_VBT_NMTD > 0 THEN
                                sqlstring := sqlstring || ' VBT_NMTD =     '''||P_VBT_NMTD||'''';
                        END IF;
                        
                        IF P_VBT_MMTD > 0 THEN
                                sqlstring := sqlstring || ' ,VBT_MMTD = '''||P_VBT_MMTD ||'''';
                        END IF;
                        
                        IF P_VBT_MMINTO > 0 THEN
                                sqlstring := sqlstring || ' ,VBT_MMINTO = '''||P_VBT_MMINTO ||'''';    
                        END IF;
                        
                        IF P_VBT_MMTO > 0 THEN
                                sqlstring := sqlstring || ' ,VBT_MMTO = '''||P_VBT_MMTO ||'''';        
                        END IF;
                        
                        IF P_EX_NMTD > 0 THEN
                                sqlstring := sqlstring || ' ,EX_NMTD = '''||P_EX_NMTD ||'''';
                        END IF;
                        
                        IF P_EX_MMTD > 0 THEN
                                sqlstring := sqlstring || ' ,EX_MMTD = '''||P_EX_MMTD ||'''';
                        END IF;
                        
                        IF P_EX_MMINTO > 0 THEN
                                sqlstring := sqlstring || ' ,EX_MMINTO = '''||P_EX_MMINTO ||'''';
                        END IF;
                        
                        IF P_EX_MMTO > 0 THEN
                                sqlstring := sqlstring || ' ,EX_MMTO = '''||P_EX_MMTO ||'''';            
                        END IF;
                            sqlstring := sqlstring || ' WHERE NUM_CONTRATO = '''||P_NUM_CONTRATO|| '''';
                    END IF;
                    --COMMIT;
            END IF;
         EXECUTE IMMEDIATE sqlstring;
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_parametros_personales_pr;
PROCEDURE vbt_parametros_contratos_pr (P_NUM_CONTRATO  IN VARCHAR2,
                                       p_datosPorDefecto OUT vbt_datos_diseneBanco,
                                       p_resultado OUT VARCHAR2
                                   )  AS 
                                     sqlstring    VARCHAR2(1500); 
                                     VAL_NUMCONTRATO  VARCHAR2(10):=NULL;  
                                     VAL_CANTIDAD  NUMBER:=0; 
                              
 
 
 
 BEGIN
        
        BEGIN
            SELECT  NUM_CONTRATO
            INTO VAL_NUMCONTRATO
            FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
            WHERE NUM_CONTRATO=P_NUM_CONTRATO;
        EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    VAL_NUMCONTRATO:=NULL;
                    
                END ;
                
         --Busca Administrador de Firmas Conjuntas       
         BEGIN
            SELECT COUNT(*) 
            INTO VAL_CANTIDAD
            FROM VBT_USERS USR, VBT_CONTRATO CON, VBT_USERS_CONTRATO UCON, USUGRP_V1 GRU
            WHERE USR.LOGIN=UCON.LOGIN AND UCON.NUM_CONTRATO=CON.NUM_CONTRATO
            AND CON.NUM_CONTRATO=P_NUM_CONTRATO AND USR.STATUS_CUENTA='A' AND GRU.LOGIN=USR.LOGIN AND GRU.CODGRP='0000000016';
        EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    VAL_CANTIDAD:=0;
                    
                END ;        
                
        
        IF VAL_NUMCONTRATO IS NULL THEN
        
            IF VAL_CANTIDAD>0 THEN
             sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.PARAMETROS_GLOBALES_FC';
            ELSE
             sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.vbt_movimi_operaciones';
            END IF;
        
           
                           
                           
        ELSE
            sqlstring := 'Select VBT_NMTD, VBT_MMTD, VBT_MMINTO, VBT_MMTO,
                           EX_NMTD, EX_MMTD, EX_MMINTO, EX_MMTO, ACCOUNT_NUM
                          FROM VBTONLINE_TRANS.vbt_movimi_ope_codpercli
                          WHERE NUM_CONTRATO='''||P_NUM_CONTRATO||'''';           
                    
        END IF;
        
        
         open  p_datosPorDefecto for sqlstring;   
                    p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300); 
 
 end vbt_parametros_contratos_pr;
 
 
 --Busca todo los clientes principales de cada cartera asociada al contrato
 PROCEDURE vbt_cliente_principal_pr  (p_num_contrato VARCHAR2, cs_cliente_principal OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2)
 IS
 BEGIN
    OPEN cs_cliente_principal FOR
        SELECT DISTINCT  CC.*, DIR.DIRECC1
        FROM CTAPER CL, CTACAR CA, CARCLI CC, VBT_CONTRATO CON,VBT_CONTRATO_CARTERAS CCA, DIRECCIONES DIR
        WHERE CC.CODPERCLI = CL.CODPER AND CA.CODCAR = CC.CODCAR
              AND  CON.NUM_CONTRATO=CCA.NUM_CONTRATO AND CCA.CODCAR=CC.CODCAR AND CC.CODPERCLI=DIR.CODPERCLI  
              AND DIR.TIPODIR = 'Email Online'
              AND UPPER (DIRECC1) <> 'HOLD MAIL'
              AND CC.FLGPRI='-1' AND  CON.NUM_CONTRATO=p_num_contrato;
              
              
        p_resultado:= 'OK';    
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);   
        
           
 END vbt_cliente_principal_pr; 
  PROCEDURE vbt_correo_cliente_cuenta  (p_cuenta VARCHAR2, cs_cliente_principal OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2)
 IS
 BEGIN
    OPEN cs_cliente_principal FOR
        SELECT  
            DIR.DIRECC1
             FROM CTAPER CL,
                  CTACAR CA,
                  CARCLI CC,
                  PRODUCCION.DIRECCIONES DIR,
                  BANCO_VBT.CTAS_CTTES CUENTAS
            WHERE     CC.CODPERCLI = CL.CODPER
                  AND CA.CODCAR = CC.CODCAR
                  AND CC.CODPERCLI = DIR.CODPERCLI
                  AND DIR.TIPODIR = 'Email Online'
                  AND UPPER (DIRECC1) <> 'HOLD MAIL'
                  AND CC.FLGPRI = '-1'
                  AND CUENTAS.CODCAR = CA.CODCAR
                  AND CUENTAS.CODCOL =p_cuenta;
                  
            -- union 
               --     select ' ' from dual;
        p_resultado:= 'OK';    
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
 END vbt_correo_cliente_cuenta; 
 

 
PROCEDURE vbt_correos_liberar (
                    p_num_referencia             in VARCHAR2,
                    p_correo             in VARCHAR2 
                   ) AS 
                   
                   
                    datos SYS_REFCURSOR;
                    
                    D ORDEN_TRANSFERENCIA%ROWTYPE;
                    SQLELEM    VARCHAR2(8000);
                    SQLSTRING  VARCHAR2(8000);
                    mensaje   VARCHAR2(8000);
                    mensajeAsesores   VARCHAR2(8000);  
                    correosAsesores VARCHAR2(2000);
                    p_resultado VARCHAR2(10);
                    
                   
      
                    BENEFICIARY_BANK_TYPE_NUMBER ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_NUMBER%TYPE;
                    INTERMEDIARY_BANK_TYPE_NUMBER  ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_NUMBER%TYPE;
                    
                    
                    V_NUM_INSTRUCCION ORDEN_TRANSFERENCIA.NUM_INSTRUCCION%TYPE; 
                        V_CODCAR ORDEN_TRANSFERENCIA.CODCAR%TYPE;
                               
                        V_FECHA_ESTATUS ORDEN_TRANSFERENCIA.FECHA_ESTATUS%TYPE;
                        V_BOF00_CODMOV ORDEN_TRANSFERENCIA.BOF00_CODMOV%TYPE;
                        V_BOF03_CURRENCY_CODE ORDEN_TRANSFERENCIA.BOF03_CURRENCY_CODE%TYPE;
                        V_BOF16_AMOUNT ORDEN_TRANSFERENCIA.BOF16_AMOUNT%TYPE;
                        V_BOF00_CODCOL ORDEN_TRANSFERENCIA.BOF00_CODCOL%TYPE;
                        V_BENEFICIARY_TYPE ORDEN_TRANSFERENCIA.BENEFICIARY_TYPE%TYPE; 
                        V_BENEFICIARY_TYPE_NUMBER ORDEN_TRANSFERENCIA.BENEFICIARY_TYPE_NUMBER%TYPE; 
                        V_BENEFICIARY_DESCRIPTION ORDEN_TRANSFERENCIA.BENEFICIARY_DESCRIPTION%TYPE; 
                        V_BENEFICIARY_BANK_TYPE ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE%TYPE; 
                        V_BENEFICIARY_BANK_TYPE_NUMBER ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_NUMBER%TYPE; 
                        V_BENEFICIARY_BANK_DESCRIPTION ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_DESCRIPTION%TYPE; 
                        V_INTERMEDIARY_BANK_TYPE ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE%TYPE; 
                        V_INTERMEDIARY_BANK_TYPE_NUM ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_NUMBER%TYPE; 
                        V_INTERMEDIARY_BANK_DESC ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_DESCRIPTION%TYPE; 
                        V_ORIGINATORS_BANK_TYPE ORDEN_TRANSFERENCIA.ORIGINATORS_BANK_TYPE%TYPE; 
                        V_CODTIPOMOV ORDEN_TRANSFERENCIA.CODTIPOMOV%TYPE; 
                        V_CODCOL_ORIGEN ORDEN_TRANSFERENCIA.CODCOL_ORIGEN%TYPE;   
                        V_REFBANMOV ORDEN_TRANSFERENCIA.REFBANMOV%TYPE; 
                        V_BENEFICIARIO ORDEN_TRANSFERENCIA.BENEFICIARIO%TYPE; 
                        V_OBSERV ORDEN_TRANSFERENCIA.OBSERV%TYPE; 
                        V_EMAIL_BENEFICIARIO ORDEN_TRANSFERENCIA.EMAIL_BENEFICIARIO%TYPE; 
                        V_EMAIL_ORIGEN ORDEN_TRANSFERENCIA.EMAIL_ORIGEN%TYPE; 
                        V_MOTIVO_ESTATUS ORDEN_TRANSFERENCIA.MOTIVO_ESTATUS%TYPE; 
                        V_CODCOL ORDEN_TRANSFERENCIA.CODCOL%TYPE;  
                        V_BENEFICIARY_NAME ORDEN_TRANSFERENCIA.BENEFICIARY_NAME%TYPE; 
                        V_BENEFICIARY_BANK_NAME ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_NAME%TYPE; 
                        V_INTERMEDIARY_BANK_NAME ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_NAME%TYPE; 
                        V_MOTIVO ORDEN_TRANSFERENCIA.MOTIVO%TYPE;
                        V_BENEFICIARY_BANK_TYPE_SWIFT ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TYPE_SWIFT%TYPE; 
                        V_BENEFICIARY_BANK_TNUM_SWIFT ORDEN_TRANSFERENCIA.BENEFICIARY_BANK_TNUM_SWIFT%TYPE; 
                        V_INTERMEDIARY_BANK_TYPE_SWIFT ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TYPE_SWIFT%TYPE; 
                        V_INTERMEDIARY_BANK_TNUM_SWIFT ORDEN_TRANSFERENCIA.INTERMEDIARY_BANK_TNUM_SWIFT%TYPE;
                        
                        V_ORIGINATORS_INFO ORDEN_TRANSFERENCIA.ORIGINATORS_INFO%TYPE; 
                        V_ORIGINATORS_NAME ORDEN_TRANSFERENCIA.ORIGINATORS_NAME%TYPE;
                        V_FFC_NUMBER ORDEN_TRANSFERENCIA.FFC_NUMBER%TYPE;
                        V_FFC_NAME  ORDEN_TRANSFERENCIA.FFC_NAME%TYPE;
                    
BEGIN




    SQLSTRING:='SELECT NUM_INSTRUCCION, 
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
                FFC_NAME
                FROM ORDEN_TRANSFERENCIA T 
                WHERE to_char(T.NUM_INSTRUCCION ) IN ('|| p_num_referencia||')';

      
        OPEN datos FOR SQLSTRING;  
   
        BEGIN
            LOOP
          
              FETCH datos INTO V_NUM_INSTRUCCION,
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
                                V_INTERMEDIARY_BANK_TYPE_NUM, 
                                V_INTERMEDIARY_BANK_DESC, 
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
                                V_FFC_NAME;
          
                    EXIT WHEN datos%NOTFOUND;
                     
                         IF V_CODTIPOMOV = 'CID' THEN        
                                 
                               mensaje:= 'Se ha ejecutado una orden de transferencia interna a través del VBT Bank ' || '&' || ' Trust Online.'
                            || CHR(10)
                           || CHR(10) || 'Fecha y Hora: ' || V_FECHA_ESTATUS
                           || CHR(10) || 'Num de Referencia: ' || V_BOF00_CODMOV
                           || CHR(10) || 'Cliente Pagador: ' || V_ORIGINATORS_INFO
                           || CHR(10) || 'Numero de Cuenta Origen: '  || '********'||SUBSTR(V_BOF00_CODCOL,LENGTH(V_BOF00_CODCOL)-3,LENGTH(V_BOF00_CODCOL))
                           || CHR(10) || 'Numero de Cartera Origen: ' || '********'||SUBSTR(V_CODCAR,LENGTH(V_CODCAR)-3,LENGTH(V_CODCAR))
                           || CHR(10) || 'Numero de Cuenta Destino: ' || '********'||SUBSTR(V_CODCOL_ORIGEN,LENGTH(V_CODCOL_ORIGEN)-3,LENGTH(V_CODCOL_ORIGEN))
                           || CHR(10) || 'Numero de Cartera Destino: ' ||'********'||SUBSTR(V_REFBANMOV,LENGTH(V_REFBANMOV)-3,LENGTH(V_REFBANMOV))
                           || CHR(10) || 'Nombre del Beneficiario: ' || V_BENEFICIARIO
                           || CHR(10) || 'Monto: ' || To_char((V_BOF16_AMOUNT*-1),'999G999G999G999G999D99','NLS_NUMERIC_CHARACTERS = ,.') || ' ' || V_BOF03_CURRENCY_CODE
                           --|| CHR(10) || 'Concepto: ' || :new.OBSERV           
                           || CHR(10) || 'Estatus Transferencia: EN PROCESO' 
                           || CHR(10) || 'Motivo: ' || V_MOTIVO_ESTATUS
                           || CHR(10)
                           || CHR(10);
                           
                               mensajeAsesores:= 'Se ha ejecutado una orden de transferencia interna a través del VBT Bank ' || '&' || ' Trust Online.'
                            || CHR(10)
                           || CHR(10) || 'Fecha y Hora: ' || V_FECHA_ESTATUS
                           || CHR(10) || 'Num de Referencia: ' || V_BOF00_CODMOV
                           || CHR(10) || 'Cliente Pagador: ' || V_ORIGINATORS_INFO
                           || CHR(10) || 'Numero de Cuenta Origen: ' || V_BOF00_CODCOL
                           || CHR(10) || 'Numero de Cartera Origen: ' || V_CODCAR
                           || CHR(10) || 'Numero de Cuenta Destino: ' || V_CODCOL_ORIGEN
                           || CHR(10) || 'Numero de Cartera Destino: ' || V_REFBANMOV
                           || CHR(10) || 'Nombre del Beneficiario: ' || V_BENEFICIARIO
                           || CHR(10) || 'Monto: ' || To_char((V_BOF16_AMOUNT*-1),'999G999G999G999G999D99','NLS_NUMERIC_CHARACTERS = ,.') || ' ' || V_BOF03_CURRENCY_CODE
                           --|| CHR(10) || 'Concepto: ' || :new.OBSERV           
                           || CHR(10) || 'Estatus Transferencia: EN PROCESO' 
                           || CHR(10) || 'Motivo: ' || V_MOTIVO_ESTATUS
                           || CHR(10)
                           || CHR(10);
                                
                                TransferenciaHandler.emails_internos_asesores_pr(V_BOF00_CODCOL,correosAsesores,p_resultado);
                           
                           
                                       --FUNCIONES.Send_Email ('vbt@vbtbank.com', 'transfers@vbtbank.com','Alert: Internal transfer through VBT Bank & Trust Online', mensaje);
                                FUNCIONES.Send_Email_Destinatarios ('vbt@vbtbank.com', p_correo,'Alert: Internal transfer through VBT Bank ' || '&' || ' Trust Online', mensaje);
                                        --Envia el correo a los asesores
                                FUNCIONES.Send_Email_Destinatarios ('vbt@vbtbank.com', correosAsesores,'Alert: Internal transfer through VBT Bank ' || '&' || ' Trust Online', mensajeAsesores);
                              
                              
                           ELSE  
                           
                     

                               IF D.BENEFICIARY_BANK_TNUM_SWIFT IS NULL THEN
                                    BENEFICIARY_BANK_TYPE_NUMBER := V_BENEFICIARY_BANK_TYPE_NUMBER;
                               ELSE
                                    BENEFICIARY_BANK_TYPE_NUMBER := V_BENEFICIARY_BANK_TNUM_SWIFT;
                               END IF;
                               
                               IF D.INTERMEDIARY_BANK_TNUM_SWIFT IS NULL THEN
                                    INTERMEDIARY_BANK_TYPE_NUMBER := V_INTERMEDIARY_BANK_TYPE_NUM;
                               ELSE
                                    INTERMEDIARY_BANK_TYPE_NUMBER := V_INTERMEDIARY_BANK_TNUM_SWIFT;
                               END IF;
                               mensaje:= 'Se ha ejecutado una orden de transferencia a otro Banco a través del VBT Bank ' || '&' || ' Trust Online.'
                                       || CHR(10)
                                       || CHR(10) || 'Fecha y Hora: ' || V_FECHA_ESTATUS
                                       || CHR(10) || 'Num de Referencia: ' || V_BOF00_CODMOV
                                       || CHR(10) || 'Cliente Pagador: ' || V_ORIGINATORS_NAME
                                       || CHR(10) || 'Numero de Cuenta Origen: '  || '********'||SUBSTR(V_BOF00_CODCOL,LENGTH(V_BOF00_CODCOL)-3,LENGTH(V_BOF00_CODCOL))  
                                       || CHR(10) || 'Numero de Cartera Origen: ' || '********'||SUBSTR(V_CODCAR,LENGTH(V_CODCAR)-3,LENGTH(V_CODCAR))
                                       || CHR(10) || 'Beneficiario: ' || V_BENEFICIARIO
                                       || CHR(10) || 'Numero de Cuenta Destino: ' || '********'||SUBSTR(V_BENEFICIARY_TYPE_NUMBER,LENGTH(V_BENEFICIARY_TYPE_NUMBER)-3,LENGTH(V_BENEFICIARY_TYPE_NUMBER))
                                       || CHR(10) || 'Codigo de Banco Beneficiario: ' || BENEFICIARY_BANK_TYPE_NUMBER    
                                       || CHR(10) || 'Nombre de Banco Beneficiario: ' || V_BENEFICIARY_BANK_NAME               
                                       || CHR(10) || 'Codigo de Banco Intermediario: ' || INTERMEDIARY_BANK_TYPE_NUMBER
                                       || CHR(10) || 'Nombre de Banco Intermediario: ' || V_INTERMEDIARY_BANK_NAME
                                       || CHR(10) || 'Para Futuro Credito a cuenta Nro.: ' || V_FFC_NUMBER
                                       || CHR(10) || 'Para Fututo Credito a nombre de: ' || V_FFC_NAME
                                       || CHR(10) || 'Monto: ' || To_char((V_BOF16_AMOUNT*-1),'999G999G999G999G999D99','NLS_NUMERIC_CHARACTERS = ,.') || ' ' || V_BOF03_CURRENCY_CODE
                                       --|| CHR(10) || 'Concepto: ' || :new.OBSERV           
                                       || CHR(10) || 'Estatus Transferencia: ' || 'En proceso'
                                       || CHR(10) || 'Motivo: ' || V_MOTIVO_ESTATUS
                                       || CHR(10)
                                       || CHR(10)
                                       || CHR(10)
                                       || CHR(10);
                                       
                                       
                                       
                                   mensajeAsesores:= 'Se ha ejecutado una orden de transferencia a otro Banco a través del VBT Bank ' || '&' || ' Trust Online.'
                                       || CHR(10)
                                       || CHR(10) || 'Fecha y Hora: ' || V_FECHA_ESTATUS
                                       || CHR(10) || 'Num de Referencia: ' || V_BOF00_CODMOV
                                       || CHR(10) || 'Cliente Pagador: ' || V_ORIGINATORS_NAME
                                       || CHR(10) || 'Numero de Cuenta Origen: ' || V_BOF00_CODCOL
                                       || CHR(10) || 'Numero de Cartera Origen: ' || V_CODCAR
                                       || CHR(10) || 'Beneficiario: ' || V_BENEFICIARIO
                                       || CHR(10) || 'Numero de Cuenta Destino: ' || V_BENEFICIARY_TYPE_NUMBER
                                       || CHR(10) || 'Codigo de Banco Beneficiario: ' || BENEFICIARY_BANK_TYPE_NUMBER
                                       || CHR(10) || 'Nombre de Banco Beneficiario: ' || V_BENEFICIARY_BANK_NAME               
                                       || CHR(10) || 'Codigo de Banco Intermediario: ' || INTERMEDIARY_BANK_TYPE_NUMBER
                                       || CHR(10) || 'Nombre de Banco Intermediario: ' || V_INTERMEDIARY_BANK_NAME
                                       || CHR(10) || 'Para Futuro Credito a cuenta Nro.: ' || V_FFC_NUMBER
                                       || CHR(10) || 'Para Fututo Credito a nombre de: ' || V_FFC_NAME
                                       || CHR(10) || 'Monto: ' || To_char((V_BOF16_AMOUNT*-1),'999G999G999G999G999D99','NLS_NUMERIC_CHARACTERS = ,.') || ' ' || V_BOF03_CURRENCY_CODE
                                       --|| CHR(10) || 'Concepto: ' || :new.OBSERV           
                                       || CHR(10) || 'Estatus Transferencia: ' || 'En proceso'
                                       || CHR(10) || 'Motivo: ' || V_MOTIVO_ESTATUS
                                       || CHR(10)
                                       || CHR(10)
                                       || CHR(10)
                                       || CHR(10); 
                                       
                                       
                                      TransferenciaHandler.emails_internos_asesores_pr(V_BOF00_CODCOL,correosAsesores,p_resultado);
                           
       
                               
                                --FUNCIONES.Send_Email ('vbt@vbtbank.com', 'transfers@vbtbank.com','Alert: Transfer to other banks through the VBT Bank & Trust Online', mensaje);
                                FUNCIONES.Send_Email_Destinatarios ('vbt@vbtbank.com', p_correo,'Alert: Transfer to other banks through the VBT Bank  '|| '&' || ' Trust Online', mensaje);
                                
                                FUNCIONES.Send_Email_Destinatarios ('vbt@vbtbank.com', correosAsesores,'Alert: Transfer to other banks through the VBT Bank  '|| '&' || ' Trust Online', mensajeAsesores);
                             
                                                                    
                           END IF;
             
            
           END LOOP;
          CLOSE datos; 
          
          
        END;
       
    EXCEPTION
    WHEN OTHERS THEN
    SQLELEM:=',';
  END vbt_correos_liberar;
  
  
   PROCEDURE vbt_errores_pr  (p_direccion_ip IN VARCHAR2, p_mensaje_error IN VARCHAR2, p_nombre_archivo IN VARCHAR2, p_valores_url IN VARCHAR2, p_user IN VARCHAR2, p_num_contrato IN VARCHAR2,p_resultado OUT VARCHAR2)
    IS
    BEGIN
        INSERT INTO VBT_EXCEPCION (ID,FECHA,DIRECCION_IP,MENSAJE_JAVA,NOMBRE_ARCHIVO,VALORES_URL,USERNAME,NUM_CONTRATO)
        values (NUM_EXCEPCION_SEQ.NEXTVAL,SYSDATE,p_direccion_ip, p_mensaje_error, p_nombre_archivo,p_valores_url,p_user,p_num_contrato);
    p_resultado:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);   
    END vbt_errores_pr;
  
     PROCEDURE vbt_templateId_pr (p_usuario    IN VARCHAR2,
                                  p_nombre_template    IN VARCHAR2,
                                  p_datos OUT SYS_REFCURSOR,
                                  p_resultado OUT VARCHAR2) IS
     
     
     BEGIN
             OPEN p_datos FOR 
                          SELECT   DIR.CODIGO
                            FROM     VBT_DIRECTORIO DIR
                            WHERE    DIR.USERNAME=p_usuario
                                     AND DIR.NOMBRE_REGISTRO=p_nombre_template
                                     AND ACTIVO='A';

                            p_resultado:='OK';                                                                                                                                                                                                                                                                                                                                                                                               
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:= SUBSTR(SQLERRM,1,300); 
     
     END vbt_templateId_pr;


    /**
     *      FUNCIÓN:        vbt_cant_solicitudes_pendientes_pr(IN VARCHAR2, IN VARCHAR2, OUT SYS_REFCURSOR , OUT VARCHAR2)
     *      AUTOR:          RRANGEL
     *      FECHA:          10/10/2014
     *      DESCRIPCIÓN:    CUENTA LA CANTIDAD DE SOLICITUDES PENDIENTES EXISTENTES 
     *                      PARA LOS ESTATUS 'C' (CARGADO) Y 'A' (APROBADO) 
     * */
    PROCEDURE vbt_cant_solpend_pr (P_IN_CONTRATO IN VARCHAR2, P_IN_ESTATUS IN VARCHAR2, P_OUT_CANTIDAD OUT NUMBER, P_OUT_RESULTADO OUT VARCHAR2) AS
    BEGIN
        SELECT COUNT(*) 
            INTO
                P_OUT_CANTIDAD
            FROM 
                ORDEN_TRANSFERENCIA TOT
            WHERE
                TOT.NUM_CONTRATO = P_IN_CONTRATO AND
                TOT.ESTATUS_INSTRUCCION = P_IN_ESTATUS;
   
        P_OUT_RESULTADO := 'OK';

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            P_OUT_RESULTADO := SUBSTR(SQLERRM,1,300);

        WHEN TOO_MANY_ROWS THEN
            P_OUT_RESULTADO := SUBSTR(SQLERRM,1,300);

        WHEN OTHERS THEN
            P_OUT_RESULTADO := SUBSTR(SQLERRM,1,300);

    END vbt_cant_solpend_pr;
    /** FIN vbt_cant_solicitudes_pendientes_pr(IN VARCHAR2, IN VARCHAR2, OUT SYS_REFCURSOR , OUT VARCHAR2) */
  PROCEDURE vbt_roles_custom_pr (p_categoria IN VARCHAR2, p_roles  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 ) AS
                                     
   BEGIN
   
     OPEN p_roles FOR 
        SELECT CODROL, NOMBROL, CATEGORIA, VISIBLE 
            FROM FC_CTAROL
            WHERE   CATEGORIA = p_categoria;
            
            p_resultado:= 'OK';  
                
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   END vbt_roles_custom_pr;           

 PROCEDURE vbt_roles_custom_detalle_pr (p_roles IN VARCHAR2, p_padres OUT SYS_REFCURSOR, p_hijos  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 ) 
   AS 
    SQLSTRING VARCHAR2(20000);
    SQLSTRING2 VARCHAR2(20000);

      BEGIN
 
         SQLSTRING:=' SELECT  T1.CODOPC, T1.MNOPCIONACCION, TO_NUMBER(T1.ORDEN_RESULTSET)
                            FROM CTAOPC_V1 T1
                            WHERE T1.CODOPC IN (
                                                SELECT DISTINCT T2.PADCODOPC 
                                                    FROM CTAOPC_V1 T2, FC_ROLCIAOPC T3
                                                    WHERE T2.CODOPC=T3.CODOPC AND T3.CODROL IN ('||p_roles||') AND PADCODOPC IS NOT NULL) 
                           ORDER BY TO_NUMBER(T1.ORDEN_RESULTSET)';
                           
    
        SQLSTRING2:='SELECT  T2.CODOPC, T2.MNOPCIONACCION, T3.VISIBLE, T2.PADCODOPC, TO_NUMBER(T2.ORDEN_RESULTSET)
            FROM FC_ROLCIAOPC T1, CTAOPC_V1 T2, FC_OPCACC T3
                WHERE T1.CODOPC=T2.CODOPC  
                AND T3.CODOPC=T2.CODOPC
                AND T1.CODROL IN ('||p_roles||') AND T2.PADCODOPC IS NOT NULL
                GROUP BY  T2.CODOPC, T2.MNOPCIONACCION, T3.VISIBLE, T2.PADCODOPC, TO_NUMBER(T2.ORDEN_RESULTSET)
                ORDER BY  TO_NUMBER(T2.ORDEN_RESULTSET) ';
                                                              
                     
    
    OPEN p_padres FOR SQLSTRING;
    OPEN p_hijos FOR SQLSTRING2;

            p_resultado:= 'OK';  
                
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   END vbt_roles_custom_detalle_pr;
   
   PROCEDURE vbt_roles_usuario_pr (p_login IN VARCHAR2, p_roles  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 ) AS
                                     
   BEGIN
   
     OPEN p_roles FOR 
        SELECT CROL.CODROL, NOMBROL, CATEGORIA, VISIBLE,DECODE(UROL.CODROL,NULL,'N', 'S') SELECCIONADO
                    FROM FC_CTAROL CROL, FC_USUROL UROL
                        WHERE   CATEGORIA = 'FC'
                            AND UROL.CODROL(+)=CROL.CODROL
                            AND UROL.LOGIN(+)=p_login
                            GROUP BY CROL.CODROL, NOMBROL, CATEGORIA, VISIBLE,  DECODE(UROL.CODROL,NULL,'N', 'S')
                            ORDER BY CROL.CODROL;
            
            p_resultado:= 'OK';  
                
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   END vbt_roles_usuario_pr;
   
   
   PROCEDURE vbt_usuarioeditar_pr (
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
      p_strRoles                   IN     VARCHAR2,
      p_resultado                     OUT VARCHAR2,
      p_usuarioSesion              IN     VARCHAR2)
   AS
      sqlquery   VARCHAR2 (1000);
      SQLSTRING   VARCHAR2 (1000);
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
      
      
      
      SQLSTRING:='DELETE FROM USRCIAOPC_V1
                                     WHERE LOGIN='''||p_strTxtUsuarioEditar||'''';
                            EXECUTE IMMEDIATE SQLSTRING;
        
      SQLSTRING:='DELETE FROM FC_USUROL
                                     WHERE LOGIN='''||p_strTxtUsuarioEditar||'''';
                            EXECUTE IMMEDIATE SQLSTRING;
      
         --Es usuario personalizado y debe ser editado de otra manera       
        IF p_strCmbTipoUsuarioEditar='11' THEN
        

             SQLSTRING:='INSERT INTO USRCIAOPC_V1(LOGIN,CODSIS, CODCIA, CODOPC,TIPACC, USRID)
                            SELECT DISTINCT '''|| LOWER(p_strTxtUsuarioEditar)||''', 
                                    ''ONLINE'', ''VBT'', OPC.CODOPC,OPC.TIPACC, '''||p_usuarioSesion||'''
                                     FROM FC_OPCACC OPC, FC_ROLCIAOPC ROL
                                    WHERE OPC.CODOPC=ROL.CODOPC AND  ROL.CODROL IN ('||p_strRoles||')';
                            EXECUTE IMMEDIATE SQLSTRING;
     
                            
             SQLSTRING:='INSERT INTO FC_USUROL(LOGIN,CODROL,FECHAREG)
                            SELECT DISTINCT '''|| LOWER(p_strTxtUsuarioEditar)||''', 
                                    CODROL, SYSDATE
                                    FROM FC_ROLCIAOPC
                                    WHERE CODROL IN ('||p_strRoles||')';
                            EXECUTE IMMEDIATE SQLSTRING;
                   
         END IF;      

      p_resultado := 'OK';
   EXCEPTION
      WHEN OTHERS
      THEN
         p_resultado := SUBSTR (SQLERRM, 1, 300);
   END vbt_usuarioeditar_pr;
 
 
   PROCEDURE vbt_aprobar_liberar_custom_pr (p_login IN VARCHAR2, p_roles  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 ) AS
                                     
   BEGIN
   
     OPEN p_roles FOR 
       SELECT DECODE(UROL.CODROL,'0000000002','A','0000000003','L') ROL
                    FROM  FC_USUROL UROL
                        WHERE  UROL.LOGIN=p_login
                        AND UROL.CODROL IN ('0000000002','0000000003')
                        ORDER BY UROL.CODROL;
            
            p_resultado:= 'OK';  
                
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   END vbt_aprobar_liberar_custom_pr;
   PROCEDURE VBT_BankCodeBuscar_pr (p_strCmbTipoCodBanco           IN VARCHAR2, 
                                    p_strTxtCodBanco                 IN VARCHAR2,
                                    p_strTxtNombreBanco             IN VARCHAR2,
                                    p_strCmbEstatus                      IN VARCHAR2,
                                    p_strEstatus                        IN VARCHAR2,
                                    p_cs_BankCodeBuscar OUT SYS_REFCURSOR,
                                   p_resultado OUT VARCHAR2,
                                   p_salida OUT VARCHAR2) AS 
                                   SQLSTRING  VARCHAR2(1500);
                                   SQLGROUP  VARCHAR2(1500);
                                   SQLQUERY  VARCHAR2(1500);
                                   WHERESQL VARCHAR2(1500);
                                   agregarAnd BOOLEAN:=FALSE;

    
    begin        
    SQLSTRING :='   SELECT distinct I.CD_TITLE NOMBRE 
        ,I.COUNTRY PAIS 
        ,I.COUNTRY_CODE COD_PAIS ';
    
     if p_strCmbTipoCodBanco ='ABA' then
        SQLSTRING := SQLSTRING || ' ,I.ROUTNUM,  (SELECT COUNT(DISTINCT C.CITY) FROM   INSTITUCION@BANCO_VBTF C WHERE  I.ROUTNUM=C.ROUTNUM) CANTIDAD,';
        SQLGROUP := SQLGROUP || ' GROUP BY (I.CD_TITLE ,I.COUNTRY ,I.COUNTRY_CODE ,I.ROUTNUM)';
     elsif p_strCmbTipoCodBanco ='SWIFT' then
        SQLSTRING := SQLSTRING || ' ,I.BIC ,  (SELECT COUNT(DISTINCT C.CITY) FROM   INSTITUCION@BANCO_VBTF C WHERE  I.BIC=C.BIC) CANTIDAD,  ';
        SQLGROUP := SQLGROUP || ' GROUP BY (I.CD_TITLE ,I.COUNTRY ,I.COUNTRY_CODE ,I.BIC)';
     elsif p_strCmbTipoCodBanco ='CHIPS' then
        SQLSTRING := SQLSTRING || ' ,I.CHIPS_UID ,  (SELECT COUNT(DISTINCT C.CITY) FROM   INSTITUCION@BANCO_VBTF C WHERE  I.CHIPS_UID=C.CHIPS_UID) CANTIDAD,  ';
         SQLGROUP := SQLGROUP || ' GROUP BY (I.CD_TITLE ,I.COUNTRY ,I.COUNTRY_CODE ,I.CHIPS_UID)';
     else
        SQLSTRING := SQLSTRING || ' ,I.ROUTNUM , (SELECT COUNT(DISTINCT C.CITY) FROM   INSTITUCION@BANCO_VBTF C WHERE  I.ROUTNUM=C.ROUTNUM) CANTIDAD,  ';
        SQLGROUP := SQLGROUP || ' GROUP BY (I.CD_TITLE ,I.COUNTRY ,I.COUNTRY_CODE ,I.ROUTNUM)';
     end if; 
     
      SQLSTRING := SQLSTRING ||''''||p_strCmbTipoCodBanco||''' ';
      

              
       
     
     
     SQLSTRING := SQLSTRING || ' FROM   INSTITUCION@BANCO_VBTF I,
       INSTITUCION_ELIMINADOS@BANCO_VBTF B,
       INSTITUCION_ELIMINADOS@BANCO_VBTF D,
       INSTITUCION_ELIMINADOS@BANCO_VBTF C ';
     
     if p_strCmbTipoCodBanco ='ABA' then
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' I.ROUTCODE =  upper('''|| p_strCmbTipoCodBanco||''') AND I.ROUTNUM IS NOT null';  
            agregarAnd := true;
        end if;
     
         if p_strTxtCodBanco is not null then
            if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND ';
            end if;
            WHERESQL := WHERESQL || ' I.ROUTNUM like '''||  p_strTxtCodBanco ||'%' ||'''';
            agregarAnd := true;
         end if;
     elsif p_strCmbTipoCodBanco='SWIFT' then
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' ';
        else    
            WHERESQL := WHERESQL || ' AND';
        end if;
        WHERESQL := WHERESQL || ' I.BIC IS NOT null';
        agregarAnd := true;
        if p_strTxtCodBanco is not null then
            if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND';
            end if;
            WHERESQL := WHERESQL || ' I.BIC like '''||  p_strTxtCodBanco ||'%' ||'''';
            agregarAnd := true;
         end if;
     elsif p_strCmbTipoCodBanco='CHIPS' then
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' ';
        else    
            WHERESQL := WHERESQL || ' AND';
        end if;
        WHERESQL := WHERESQL || ' I.CHIPS_UID IS NOT null';
        agregarAnd := true;        
        if p_strTxtCodBanco is not null then
            if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND';
            end if;
            WHERESQL := WHERESQL || ' I.CHIPS_UID like '''||  p_strTxtCodBanco ||'%' ||'''';
            agregarAnd := true;
         end if;
    else
        if  agregarAnd = false then
            WHERESQL := WHERESQL || ' ';
        else    
            WHERESQL := WHERESQL || ' AND';
        end if;
        WHERESQL := WHERESQL || ' I.COUNTRY_CODE = upper(''' || p_strCmbTipoCodBanco ||''') AND I.ROUTNUM IS NOT null AND I.ROUTCODE <> ''ABA''';
        agregarAnd := true;        
        if p_strTxtCodBanco is not null then
            if  agregarAnd =false then
                WHERESQL := WHERESQL || ' ';
            else    
                WHERESQL := WHERESQL || ' AND';
            end if;
            WHERESQL := WHERESQL || ' I.ROUTNUM like '''||  p_strTxtCodBanco ||'%' ||'''';
            
            agregarAnd := true;
         end if    ;
        
     end if;
     if p_strTxtNombreBanco is not null  then
        if  agregarAnd = false then
                WHERESQL := WHERESQL || ' ';
        else    
                WHERESQL := WHERESQL || ' AND';
        end if;
                WHERESQL := WHERESQL || ' I.CD_TITLE like '''||'%' ||  p_strTxtNombreBanco ||'%' ||'''' ;   
     end if;        
    
     
     if WHERESQL is not null then
     
     
        SQLSTRING := SQLSTRING || ' where ';
        SQLSTRING := SQLSTRING || WHERESQL || ' AND I.COUNTRY_CODE NOT IN (SELECT L.COUNTRY_CODE FROM LISTA_NEGRA@BANCO_VBTF L) '
         ||         ' AND I.ROUTNUM = B.CODIGO_BANCO(+) AND ''ROUTNUM'' = B.TIPO_CODIGO(+) '
         ||         ' AND I.CHIPS_UID = D.CODIGO_BANCO(+) AND ''CHIPS_UID'' = D.TIPO_CODIGO(+) '
         ||         ' AND I.BIC = C.CODIGO_BANCO(+)     AND ''BIC'' = C.TIPO_CODIGO(+) '
         ||         ' ' ;
     
     
     
      IF p_strEstatus='A' THEN
             if p_strCmbTipoCodBanco ='ABA' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='SWIFT' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='CHIPS' then
               SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             else
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             end if;
         
      
      ELSE
            if p_strCmbTipoCodBanco ='ABA' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NOT NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='SWIFT' then
                SQLQUERY := SQLQUERY || '  AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS  NOT NULL AND D.TIPO_CODIGO IS NULL  ';
             elsif p_strCmbTipoCodBanco ='CHIPS' then
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NULL AND C.TIPO_CODIGO IS NULL AND D.TIPO_CODIGO IS NOT NULL  ';
             else
                SQLQUERY := SQLQUERY || ' AND B.TIPO_CODIGO IS NOT NULL AND C.TIPO_CODIGO IS NULL ';
             end if;

      END IF;  
      
         SQLSTRING := SQLSTRING || SQLQUERY ||SQLGROUP  || ' ORDER BY I.CD_TITLE ASC ';
      
        
     end if;
     
    
     
     p_salida := SQLSTRING;
     open  p_cs_BankCodeBuscar for SQLSTRING; 
      p_resultado:= 'OK'; 
             EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
            WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
                p_resultado:= SUBSTR(SQLERRM,1,300);  
   end VBT_BankCodeBuscar_pr;
   
   PROCEDURE VBT_EXCLUIR_BANCO (P_BANK_CODE VARCHAR2, P_BANK_TYPE VARCHAR2, P_USUARIO VARCHAR2, P_RESULTADO OUT VARCHAR2) AS
        BANK_TYPE  VARCHAR2(1500);
    BEGIN
    
    
    
     IF P_BANK_TYPE ='ABA' THEN
        BANK_TYPE:='ROUTNUM';
     ELSIF P_BANK_TYPE ='SWIFT' THEN
        BANK_TYPE:='BIC';
     ELSIF P_BANK_TYPE ='CHIPS' THEN
        BANK_TYPE:='CHIPS_UID';
     ELSE
        BANK_TYPE:='ROUTNUM';
     END IF;    
    
        INSERT INTO  INSTITUCION_ELIMINADOS@BANCO_VBTF (CODIGO_BANCO,TIPO_CODIGO,FECHA_INC,USUARIO_INC)
                VALUES (P_BANK_CODE,BANK_TYPE,SYSDATE, P_USUARIO);  
        
    P_RESULTADO:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        P_RESULTADO:= SUBSTR(SQLERRM,1,300);   
    END VBT_EXCLUIR_BANCO;
    
    
    PROCEDURE VBT_INCLUIR_BANCO (P_BANK_CODE VARCHAR2, P_BANK_TYPE VARCHAR2, P_USUARIO VARCHAR2, P_RESULTADO OUT VARCHAR2) AS
        BANK_TYPE  VARCHAR2(1500);
    BEGIN
    
        DELETE FROM INSTITUCION_ELIMINADOS@BANCO_VBTF I
                WHERE 
                   I.CODIGO_BANCO=P_BANK_CODE;
        
    P_RESULTADO:= 'OK'; 
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        P_RESULTADO:= SUBSTR(SQLERRM,1,300);   
    END VBT_INCLUIR_BANCO;
    
    
     
   PROCEDURE vbt_imagen_home (p_fecha IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR) AS
                                 
   v_cantidad number;                                
   BEGIN
   
    v_cantidad:=0;
   
         BEGIN 
             SELECT (CODIGO)
             INTO v_cantidad
                 FROM VBT_AVISO
                     WHERE TRUNC(SYSDATE)<=TRUNC(FECHA_FIN) AND TRUNC(FECHA_INICIO)<=SYSDATE 
                     AND STATUS='A' AND POR_DEFECTO='N';
         EXCEPTION
                WHEN NO_DATA_FOUND THEN
                      v_cantidad:=0;
                    
                END ;
   
        
        IF v_cantidad>0 THEN
             OPEN p_cursor FOR 
                       SELECT *
                         FROM VBT_AVISO
                            WHERE TRUNC(SYSDATE)<=TRUNC(FECHA_FIN) AND TRUNC(FECHA_INICIO)<=SYSDATE 
                             AND STATUS='A' AND POR_DEFECTO='N' ORDER BY CODIGO DESC; 
         
             
        ELSE
           OPEN p_cursor FOR 
               SELECT *
                 FROM VBT_AVISO
                     WHERE POR_DEFECTO='S' AND STATUS='A' ORDER BY CODIGO DESC; 
         
   
        END IF;
    
        p_resultado:= 'OK';  

   
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_imagen_home;
   
   
   PROCEDURE vbt_info_home (p_contenido OUT VARCHAR2, p_resultado OUT VARCHAR2, p_contenido_eng  OUT VARCHAR2) AS
                                 
   v_cantidad number;                                
   BEGIN
   
    v_cantidad:=0;
   
         BEGIN 
             SELECT (CODIGO)
             INTO v_cantidad
                 FROM VBT_AVISO
                     WHERE TRUNC(SYSDATE)<=TRUNC(FECHA_FIN) AND TRUNC(FECHA_INICIO)<=SYSDATE 
                     AND STATUS='A' AND POR_DEFECTO='N';
         EXCEPTION
                WHEN NO_DATA_FOUND THEN
                      v_cantidad:=0;
                    
                END ;
   
        
        IF v_cantidad>0 THEN
     
                       SELECT TEXTO_ESP, TEXTO_ING
                        INTO p_contenido, p_contenido_eng 
                         FROM VBT_AVISO
                            WHERE TRUNC(SYSDATE)<=TRUNC(FECHA_FIN) AND TRUNC(FECHA_INICIO)<=SYSDATE 
                             AND STATUS='A' AND POR_DEFECTO='N' ORDER BY CODIGO DESC; 
         
             
        ELSE
          
                SELECT TEXTO_ESP, TEXTO_ING
                 INTO p_contenido, p_contenido_eng 
                 FROM VBT_AVISO
                     WHERE POR_DEFECTO='S' AND STATUS='A' ORDER BY CODIGO DESC; 
         
   
        END IF;
    
        p_resultado:= 'OK';  

   
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_info_home;  
   
   
       PROCEDURE VBT_AVISOS_PR (p_tipo_aviso IN VARCHAR2,
                             p_tipo_status IN VARCHAR2,
                             p_fecha_inicio IN VARCHAR2,
                             p_fecha_fin VARCHAR2,
                             p_vbt_avisos  OUT SYS_REFCURSOR,
                             p_resultado OUT VARCHAR2,
                             p_sql OUT VARCHAR2,
                             p_excluir_fechas IN VARCHAR2) AS 
                             SQLSTRING   VARCHAR2(1500);
                                      
   BEGIN
   
   
            
    SQLSTRING:='SELECT CODIGO,
                    TITULO,
                    TEXTO_ESP, 
                    TEXTO_ING,
                    TO_CHAR(FECHA_INICIO,''dd/mm/yyyy''),
                    TO_CHAR(FECHA_FIN,''dd/mm/yyyy''), 
                    POR_DEFECTO,
                    STATUS
                    FROM VBT_AVISO
        WHERE  CODIGO IS NOT NULL '; 
       

            if p_tipo_aviso  ='-2' then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                SQLSTRING :=  SQLSTRING || '    AND     POR_DEFECTO = '''||p_tipo_aviso||''''; 
            end if;
            if p_tipo_status  ='-2' then
                    SQLSTRING :=  SQLSTRING || ' ';
            else
                SQLSTRING :=  SQLSTRING || '    AND     STATUS = '''|| p_tipo_status ||''''; 
            end if;
            
            if p_excluir_fechas !='-2' then
                if p_fecha_inicio is null then
                        SQLSTRING :=  SQLSTRING || ' ';
                else
                        SQLSTRING :=  SQLSTRING || '    AND     TO_DATE(TO_CHAR(FECHA_INICIO,''dd/mm/yyyy''),''dd/mm/yyyy'') >= TO_DATE('''|| p_fecha_inicio|| ''',''dd/mm/yyyy'') '; 
                end if;
                if p_fecha_fin is null then
                        SQLSTRING :=  SQLSTRING || ' ';
                else
                        SQLSTRING :=  SQLSTRING || '    AND     TO_DATE(TO_CHAR(FECHA_FIN,''dd/mm/yyyy''),''dd/mm/yyyy'') <= TO_DATE('''|| p_fecha_fin ||''',''dd/mm/yyyy'') '; 
                end if;
            end if;    
            
           SQLSTRING :=  SQLSTRING || ' ORDER BY FECHA_INICIO ASC';
           
            
            p_sql := SQLSTRING;
            open  p_vbt_avisos for SQLSTRING;
            p_resultado:= 'OK';    
   
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   END VBT_AVISOS_PR;    
   
     


   PROCEDURE vbt_imagen_id (p_codigo IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR) AS
                               
   BEGIN
   
   
             OPEN p_cursor FOR 
                       SELECT *
                         FROM VBT_AVISO
                            WHERE CODIGO=p_codigo; 
         
       
    
        p_resultado:= 'OK';  

   
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_imagen_id;

PROCEDURE VBT_ESTADO_CUENTA_FONDOS (p_codemp IN VARCHAR2, p_codcar IN VARCHAR2, p_fecha_emision IN VARCHAR2, p_razon IN VARCHAR2, p_moneda IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR) AS
    V_FLGEXTRT varchar2(20);
   V_fecha_emision varchar2(20);    
                        
   BEGIN
      V_fecha_emision := TO_CHAR(LAST_DAY(TO_DATE(p_fecha_emision,'DD/MM/YYYY')),'DD/MM/YYYY');  
   
        BEGIN
                SELECT VALOR
                INTO V_FLGEXTRT
                FROM   PRODUCTO_EMPRESA@BANCO_VBTF PE 
                WHERE 
                    PE.CODPRODINT = '0000000037' 
                    AND PE.CODEMP=p_codemp;
                  
            EXCEPTION
                WHEN NO_DATA_FOUND THEN
                    V_FLGEXTRT:='';
                END ;

    OPEN p_cursor FOR 

    SELECT 
        2 CONCIRIF,
        TRIM(CLIENTE_PRINCIPAL.NA) INVERSIONISTA,
        CLIENTE_PRINCIPAL.DIRENVIO DIRENVIO,
        ACCIONISTA.CODCAR,
        ACCIONISTA.CODEMP,
        ACCIONISTA.CODACC,
        p_moneda AS MONEDA, 
        CLIENTE_PRINCIPAL.CODEJE,
        CLIENTE_PRINCIPAL.NOMBEJE NOMBEJE,
        0 NUMACCGAR,
        0 NUMACCDISP,
        0 SALDO_UNI_ANT,
        PRECIO AS PRECIO_VUI,
        TO_CHAR(OPERACION.FECHOPE,'DD/MM/YYYY') FECHOPE,
        TO_CHAR(OPERACION.FECHVAL,'DD/MM/YYYY') FECHVAL,
        NOMBTIPO,
        NOMBTIPOING,
        OPERACION.MTOINV,
        OPERACION.PRECUNI,
        DECODE(SIGNOTIPO, -1, NUMUNI, 0, ABS(NUMUNI) * -1,0) NUMUNI,
        OPERACION.TIPOPE,
        CODTIPO,
        SIGNOTIPO,
        FECHA_VUI,
        ACCIONISTA.CODEMP CODEMP_A,
        FLGREINV,
        p_razon AS RAZCOMER, 
        0 NUMACC, 
        PRECIO_EXT AS PRECIO_VUI_EXT, CODOPE, 
        V_FLGEXTRT AS FLGEXTRT, 
        TRUNC(TO_DATE(V_fecha_emision,'DD/MM/YYYY'))  AS FECHA_FIN_MES, 
        GET_NOMBRE_CTECOMPARTIDOS@FONDOMUTUAL_VBT (ACCIONISTA.CODEMP, ACCIONISTA.CODCAR) AS ACC_SECUNDARIO,
        OPERACION.FECHOPE FECHA_ORDEN,
        OPERACION.CODOPE CODIGO_OPERACION 
        FROM CLIENTE_PRINCIPAL, OPERACION@FONDOMUTUAL_VBT,TIPO_OPERACION@FONDOMUTUAL_VBT,HISTORICO_PRECIO@FONDOMUTUAL_VBT,ACCIONISTA@FONDOMUTUAL_VBT 
        WHERE 
        CLIENTE_PRINCIPAL.CODCAR = ACCIONISTA.CODCAR 
        AND (ACCIONISTA.CODCAR=p_codcar)
        AND HISTORICO_PRECIO.CODEMP = p_codemp 
        AND ACCIONISTA.CODEMP =p_codemp
        AND HISTORICO_PRECIO.FECHA_VUI =   (SELECT  MAX(FECHA_VUI)  
                                       FROM HISTORICO_PRECIO@FONDOMUTUAL_VBT  
                                       WHERE  FECHA_VUI <=  TO_DATE(V_fecha_emision,'DD/MM/YYYY') 
                                          AND CODEMP = p_codemp AND PRECIO <> 0 )
        AND 
        ( OPERACION.FECHVAL >=  TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE(V_fecha_emision,'DD/MM/YYYY'),-1))+1)
        AND OPERACION.FECHVAL <=  TO_DATE(V_fecha_emision,'DD/MM/YYYY') 
        AND OPERACION.CODEMP = p_codemp
        AND OPERACION.TIPOPE <> '003' 
        AND OPERACION.TIPOPE <> '006' 
        AND OPERACION.STATOPE <> 'A' 
        AND OPERACION.CODCAR = ACCIONISTA.CODCAR 
        AND TIPO_OPERACION.CODTIPO = OPERACION.TIPOPE 
        ) 
        UNION 

         SELECT 
            1 CONCIRIF ,
            TRIM(CLIENTE_PRINCIPAL.NA) INVERSIONISTA,
            CLIENTE_PRINCIPAL.DIRENVIO DIRENVIO,
            ACCIONISTA.CODCAR ,
            ACCIONISTA.CODEMP,
            ACCIONISTA.CODACC,
            p_moneda AS MONEDA, 
            CLIENTE_PRINCIPAL.CODEJE,
            CLIENTE_PRINCIPAL.NOMBEJE NOMBEJE,
            A.UNIBLOQ AS NUMACCGAR,
            (A.SALDO_UNI_ANT + A.UNISUSC -(A.UNIRESC + ABS(A.UNIBLOQ) + ABS(A.UNIDIFE))) AS NUMACCDISP,
            B.SALDO_UNI_ANT,
            PRECIO AS PRECIO_VUI,
            TO_CHAR(SYSDATE,'DD/MM/YYYY') FECHOPE,
            TO_CHAR(SYSDATE,'DD/MM/YYYY') FECHVAL,
            NULL NOMBTIPO,
            NULL NOMBTIPOING,
            0 MTOINV,
            0 PRECUNI,
            0 NUMUNI,
            NULL TIPOPE,
            NULL CODTIPO,
            0 SIGNOTIPO,
            FECHA_VUI,
            ACCIONISTA.CODEMP CODEMP_A,
            FLGREINV,
            p_razon AS RAZCOMER, 
            ((A.SALDO_UNI_ANT + A.UNISUSC )- A.UNIRESC) AS NUMACC, 
            PRECIO_EXT AS PRECIO_VUI_EXT, NULL CODOPE, 
            V_FLGEXTRT AS FLGEXTRT, 
            NVL(A.FECHA_FIN_MES, TRUNC(TO_DATE(V_fecha_emision,'DD/MM/YYYY'))), 
            GET_NOMBRE_CTECOMPARTIDOS@FONDOMUTUAL_VBT  (ACCIONISTA.CODEMP, ACCIONISTA.CODCAR) AS ACC_SECUNDARIO,
            SYSDATE  FECHA_ORDEN,
            '0' CODIGO_OPERACION 
            FROM CLIENTE_PRINCIPAL,HISTORICO_PRECIO@FONDOMUTUAL_VBT,SALDO_ACCIONISTA@FONDOMUTUAL_VBT  A, 
                 SALDO_ACCIONISTA@FONDOMUTUAL_VBT B,ACCIONISTA@FONDOMUTUAL_VBT 
            WHERE 
            CLIENTE_PRINCIPAL.CODCAR = ACCIONISTA.CODCAR 
            AND A.CODCAR = ACCIONISTA.CODCAR 
            AND B.CODCAR = ACCIONISTA.CODCAR 
            AND HISTORICO_PRECIO.CODEMP = p_codemp
              AND (ACCIONISTA.CODCAR=p_codcar)
              AND B.FECHA_FIN_MES >= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE(V_fecha_emision,'DD/MM/YYYY'),-1))+1)
            AND A.CODEMP = p_codemp 
            AND B.CODEMP = p_codemp
            AND ACCIONISTA.CODEMP = p_codemp
            AND HISTORICO_PRECIO.FECHA_VUI =  (SELECT  MAX(FECHA_VUI)  
                                           FROM HISTORICO_PRECIO@FONDOMUTUAL_VBT   

                                           WHERE  FECHA_VUI <=  TO_DATE(V_fecha_emision,'DD/MM/YYYY') 
                                              AND CODEMP =p_codemp AND PRECIO <> 0 )

            AND A.FECHA_FIN_MES =  TRUNC(TO_DATE(V_fecha_emision,'DD/MM/YYYY'))
                AND B.FECHA_FIN_MES = (SELECT min(X.FECHA_FIN_MES) 
                                       FROM   SALDO_ACCIONISTA@FONDOMUTUAL_VBT  X 

                                        WHERE X.CODCAR = B.CODCAR 
                                        AND X.CODEMP = B.CODEMP 
                                        AND X.FECHA_FIN_MES >= TRUNC(TO_DATE(V_fecha_emision,'DD/MM/YYYY'))
                                        )
              

                AND (EXISTS (SELECT NULL
                            FROM OPERACION@FONDOMUTUAL_VBT , 
                                TIPO_OPERACION@FONDOMUTUAL_VBT  


                            WHERE 
                                                 
                            OPERACION.FECHVAL >=  TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE(V_fecha_emision,'DD/MM/YYYY'),-1))+1)
                            AND OPERACION.FECHVAL <=  TO_DATE(V_fecha_emision,'DD/MM/YYYY') 
                            AND OPERACION.CODEMP = p_codemp
                            AND OPERACION.TIPOPE <> '003' 
                            AND OPERACION.TIPOPE <> '006' 
                            AND OPERACION.STATOPE <> 'A' 
                            AND OPERACION.CODCAR = ACCIONISTA.CODCAR 
                            AND TIPO_OPERACION.CODTIPO = OPERACION.TIPOPE  ) 

                OR  (A.SALDO_UNI_ANT > 0 
                    AND  NOT EXISTS (SELECT NULL 
                                        FROM OPERACION@FONDOMUTUAL_VBT , 
                                            TIPO_OPERACION@FONDOMUTUAL_VBT  


                                        WHERE 
                                       OPERACION.FECHVAL >= TRUNC(LAST_DAY(ADD_MONTHS(TO_DATE(V_fecha_emision,'DD/MM/YYYY'),-1))+1)
                                       AND OPERACION.FECHVAL <=  TO_DATE(V_fecha_emision,'DD/MM/YYYY') 
                                       AND OPERACION.CODEMP = p_codemp
                                        AND OPERACION.TIPOPE <> '003' 
                                        AND OPERACION.TIPOPE <> '006' 
                                        AND OPERACION.STATOPE <> 'A' 
                                        AND OPERACION.CODCAR = ACCIONISTA.CODCAR 
                                        AND TIPO_OPERACION.CODTIPO = OPERACION.TIPOPE  )))
            ORDER BY 4, 2, 1, 3, FECHA_ORDEN ASC, CODIGO_OPERACION ASC;

       p_resultado:='OK';
  
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN
       p_resultado:=SQLERRM;   

   END VBT_ESTADO_CUENTA_FONDOS;

PROCEDURE VBT_EDO_FONDOS_DIVIDENDO (p_codemp IN VARCHAR2, p_fecha_emision IN VARCHAR2, p_cursor OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2) AS
        
    BEGIN
        OPEN p_cursor FOR
            SELECT CODEMP, CODDIV, TIPDIV, DIVPOR, CANTUNI, FECHREG, FECHPRECIO, FECHPAGO, STATDIV, CODMON, USRID, TIPOREINV 
            FROM DIVIDENDO@FONDOMUTUAL_VBT  
            WHERE CODEMP = p_codemp 
            AND DIVIDENDO.FECHPRECIO >=  TRUNC(ADD_MONTHS(TO_DATE(p_fecha_emision,'DD/MM/YYYY'),-3),'MM')
            AND DIVIDENDO.FECHPRECIO <=  TO_DATE(p_fecha_emision,'DD-MM-YYYY') 
            AND DIVIDENDO.STATDIV <> 'A'  
            ORDER BY FECHREG DESC; 
        
        p_resultado := 'OK';
        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:= SUBSTR(SQLERRM,1,300);    
   
   END VBT_EDO_FONDOS_DIVIDENDO;
   
  PROCEDURE vbt_detalle_pago_tdc (p_num_cta    IN VARCHAR2,
                                                          p_vbt_tdcconsulta_cab OUT SYS_REFCURSOR, 
                                                          p_resultado OUT VARCHAR2) AS 
                                         cs_vbt_tdcconsulta_cab vbt_tdcconsulta_cab;  
   begin
   
     OPEN cs_vbt_tdcconsulta_cab FOR 
         SELECT SUBSTR(SUBSTR(num_cta_plast_ppal,5),1,4)||'-'||SUBSTR(SUBSTR(num_cta_plast_ppal,5),5,4)||'-'||SUBSTR(SUBSTR(num_cta_plast_ppal,5),9,4)||'-'||SUBSTR(SUBSTR(num_cta_plast_ppal,5),13,4) num_cta_plast_ppal,
         desprodserv,
          nombre_cliente_cta, 
         NVL(direccion_uno,'-') direccion_uno,
         NVL(direccion_dos,'-') direccion_dos,
         NVL(direccion_tres,'-') direccion_tres,
         SUBSTR(zona_postal,4,4) zona_postal, 
         NVL(lim_credito,0) lim_credito,
         NVL(credito_disp,0) credito_disp,
         to_char(fecha_factura,'dd/MM/yyyy') fecha_factura,
         NVL(pag_total,'0') pag_total,
         NVL(pag_min_mes,'0') pag_min_mes,
         to_char(fec_pag_antes,'dd/MM/yyyy') fec_pag_antes,
         NVL(saldo_actual,'0') saldo_actual,
         NVL(cuo_ven,0) cuo_ven,
         NVL(imp_ven,0) imp_ven,
         NVL(cuo_mes,0) cuo_mes,
         NVL(int_bon,0) int_bon,
         NVL(tas_int,0) tas_int,
         NVL(tasa_mora,0) tasa_mora,
         NVL(periodo_fact,0) periodo_fact,
         NVL(sal_anterior,0) sal_anterior,
         NVL(cargos,0) cargos,
         NVL(pagos,0) abonos,
         NVL(tpo_tdc,'') tpo_tdc,
         mensaje1 observaciones 
         FROM   VBT_TARJETA.EDO_CTA_TDC 
         WHERE  num_cta= p_num_cta
         AND    codproserv = '002' 
         AND FECHA_FACTURA=(SELECT MAX(FECHA_FACTURA)
                    FROM   VBT_TARJETA.EDO_CTA_TDC 
                            WHERE  num_cta=p_num_cta);         
                            
        p_vbt_tdcconsulta_cab:= cs_vbt_tdcconsulta_cab;  
        p_resultado:= 'OK';    
        commit;    
    EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
    WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
        p_resultado:= SUBSTR(SQLERRM,1,300);      
   
   end vbt_detalle_pago_tdc;    
   
   
   PROCEDURE VBT_CARGAR_EDO_FONDOMUTUAL(p_codemp IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR) AS
   BEGIN
        OPEN p_cursor FOR
            SELECT CODEMP, ETIQUETA, IDIOMA, MES_CONDICION, TEXTO_ING, TEXTO_ESP, CONDICION, IMAGEN
            FROM EDO_CUENTA_CONFIG@FONDOMUTUAL_VBT 
            WHERE CODEMP = p_codemp;
         
        p_resultado := 'OK';
        
        EXCEPTION                                                                                                                                                                                                                                                                                                                                                                                                       
        WHEN OTHERS THEN                                                                                                                                                                                                                                                                                                                                                                                               
            p_resultado:= SUBSTR(SQLERRM,1,300);
   
   END VBT_CARGAR_EDO_FONDOMUTUAL;
      
   
 END VBTONLINE;
/


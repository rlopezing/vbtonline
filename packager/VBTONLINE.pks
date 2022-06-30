CREATE OR REPLACE PACKAGE VBTONLINE_TRANS.VBTONLINE AS
   TYPE contraeditarrlog IS REF CURSOR; 
   TYPE vbt_bankCodeType  IS REF CURSOR; 
   TYPE vbt_bankpaises  IS REF CURSOR;         
   TYPE vbt_colocasaldos  IS REF CURSOR;         
   TYPE vbt_saldoscolo  IS REF CURSOR;         
   TYPE vbt_colocaope  IS REF CURSOR;  
   TYPE vbt_calendario  IS REF CURSOR;         
   TYPE vbt_colobloquedepo  IS REF CURSOR;         
   TYPE vbt_portafo_conso_user  IS REF CURSOR; 
   TYPE vbt_porta_con_saldo  IS REF CURSOR;         
   TYPE vbt_cuentas_bloqueo_ctas  IS REF CURSOR; 
   TYPE vbt_cuentas_edo_cuenta  IS REF CURSOR;         
   TYPE vbt_cuentas_edo_mesano  IS REF CURSOR;         
   TYPE vbt_cuentas_edo_cabecera  IS REF CURSOR;         
   TYPE vbt_cuentas_edo_detalle  IS REF CURSOR;
   TYPE vbt_fondos_blo_cartera  IS REF CURSOR;     
   TYPE vbt_fondos_sal_cartera  IS REF CURSOR;     
   TYPE vbt_otrasinver_cartera  IS REF CURSOR;     
   TYPE vbt_fondos_sal_saldos  IS REF CURSOR;         
   TYPE vbt_fondos_sal_movimi  IS REF CURSOR;         
   TYPE vbt_porta_con_det  IS REF CURSOR;         
   TYPE vbt_tdcconsulta_tar  IS REF CURSOR;         
   TYPE vbt_tdcconsulta_mes  IS REF CURSOR;         
   TYPE vbt_tdcconsulta_cab  IS REF CURSOR;         
   TYPE vbt_tdcconsulta_mov  IS REF CURSOR;         
   TYPE vbt_tdmovimi_ctas  IS REF CURSOR;         
   TYPE vbt_tdmovimi_trans  IS REF CURSOR;         
   TYPE vbt_cuentasaldo_cuentas  IS REF CURSOR;
   TYPE vbt_cuentasaldo_saldos  IS REF CURSOR;         
   TYPE vbt_cuentasaldo_tipos  IS REF CURSOR;     
   TYPE vbt_directorio  IS REF CURSOR;
   TYPE vbt_directorioAutocompletar IS REF CURSOR;    
   TYPE vbt_directorio_agregar  IS REF CURSOR;         
   TYPE vbt_colocacerti_cierre  IS REF CURSOR;         
   TYPE vbt_colocavenci_cierre  IS REF CURSOR; 
   TYPE vbt_PDFBloqueoDetalle  IS REF CURSOR; 
   TYPE vbt_PDFCertiApertura_certi  IS REF CURSOR; 
   TYPE vbt_PDFCertiApertura_movi  IS REF CURSOR; 
   TYPE vbt_PDFCertivenci_certi  IS REF CURSOR; 
   TYPE vbt_consultarTelefono  IS REF CURSOR; 
   TYPE vbt_consultarDirecciones  IS REF CURSOR; 
   TYPE vbt_consultarDocumentos  IS REF CURSOR; 
   TYPE vbt_consultarRepresentantes  IS REF CURSOR; 
   TYPE vbt_consultarContactos IS REF CURSOR; 
   TYPE vbt_consultarCarteras  IS REF CURSOR; 
   TYPE vbt_busca_directorio  IS REF CURSOR; 
   TYPE vbt_datos_diseneBanco  IS REF CURSOR; 
   TYPE vbt_datos_diseneBancoBase  IS REF CURSOR;
   TYPE vbt_paises_excluidos is REF CURSOR;
       
   /* TYPE vbt_alertaseguridad  IS REF CURSOR; 
   -- TYPE bac_contratos  IS REF CURSOR; 
   
    
    
   PROCEDURE vbt_alertaseguridad_pr (p_strCarteras in varchar2,
                                        p_vbt_alertaseguridad OUT vbt_alertaseguridad, 
                                        p_resultado OUT VARCHAR2);
                                        
                                        
    */
   
   PROCEDURE vbt_contratos_pr (       p_strNuevo                 IN VARCHAR2,
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
                                      p_salida OUT VARCHAR2);
                                      
     PROCEDURE vbt_BankCodeBuscar_pr (p_strCmbTipoCodBanco           IN VARCHAR2, 
                                    p_strTxtCodBanco                 IN VARCHAR2,
                                    p_strTxtNombreBanco             IN VARCHAR2,
                                    p_strCmbPais                      IN VARCHAR2,
                                    p_strCodPais                      IN VARCHAR2,
                                    vbt_BankCodeBuscar  OUT SYS_REFCURSOR,
                                   p_resultado OUT VARCHAR2,
                                   p_salida OUT VARCHAR2,
                                   p_strStatus             IN VARCHAR2);
                                   
           
            
       PROCEDURE vbt_bankCodeType_pr (p_vbt_bankCodeType OUT vbt_bankCodeType, 
                                        p_resultado OUT VARCHAR2);
       
       PROCEDURE vbt_bankpaises_pr (p_vbt_bankpaises OUT vbt_bankpaises, 
                                        p_resultado OUT VARCHAR2);
       
              
       PROCEDURE vbt_colocasaldos_pr (p_strCarteras IN OUT VARCHAR2,
                                        p_vbt_colocasaldos OUT vbt_colocasaldos, 
                                        p_resultado OUT VARCHAR2);  
                                        
       PROCEDURE vbt_saldoscolo_pr (p_strCodigoCartera IN VARCHAR2,
                                        p_strNumeroColocacion IN VARCHAR2,
                                        p_vbt_saldoscolo OUT vbt_saldoscolo, 
                                        p_resultado OUT VARCHAR2); 
                                        
        PROCEDURE vbt_colocaope_pr (p_strCodigoCartera IN VARCHAR2,
                                        p_strNumeroColocacion IN VARCHAR2,
                                        p_vbt_colocaope OUT vbt_colocaope, 
                                        p_resultado OUT VARCHAR2);                                                               
                                        
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
                                        p_sql OUT VARCHAR2);   
                                        
      PROCEDURE vbt_calendario_pr (p_mes IN NUMBER,
                                        p_año IN NUMBER,
                                        p_fin_mes IN NUMBER,
                                        p_vbt_calendario OUT vbt_calendario, 
                                        p_resultado OUT VARCHAR2);       
                                       
     PROCEDURE vbt_colobloquedepo_pr (p_strCarteras IN VARCHAR2,
                                        p_vbt_colobloquedepo OUT vbt_colobloquedepo, 
                                        p_resultado OUT VARCHAR2);         

        PROCEDURE vbt_colobloquemovi_pr (p_strNumeroColocacion IN VARCHAR2,
                                        p_strCodigoCartera IN VARCHAR2,
                                        p_strCmbBuscar        IN VARCHAR2,
                                        p_strTxtFechaDesde IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_vbt_colobloquemovi OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2);    
                                        
     PROCEDURE vbt_portafo_conso_user_pr (p_strLogin IN VARCHAR2,
                                        p_vbt_portafo_conso_user OUT vbt_portafo_conso_user, 
                                        p_resultado OUT VARCHAR2);   
                    
     PROCEDURE vbt_porta_con_movi_pr (p_strCodigoCarterai IN VARCHAR2,
                                        p_ver_opcion IN VARCHAR2,
                                        p_login IN VARCHAR2,
                                        p_vbt_porta_con_movi OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2);   
                    
     PROCEDURE vbt_cuentas_bloqueo_ctas_pr (p_strCarteras IN VARCHAR2,
                                         p_vbt_cuentas_bloqueo_ctas OUT vbt_cuentas_bloqueo_ctas, 
                                         p_resultado OUT VARCHAR2);
                     
              
       PROCEDURE vbt_cuentas_bloqueo_vigen_pr (p_strCmbBuscar IN VARCHAR2,
                                        p_strNumeroCuenta IN VARCHAR2,
                                        p_strTxtFechaDesde  IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_vbt_cuentas_bloqueo_vigen OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2);   
                    
     PROCEDURE vbt_cuentas_edo_cuenta_pr (p_strCarteras             IN OUT  VARCHAR2,
                                            p_strCuentaCorriente     IN VARCHAR2,
                                            p_strCuentaAhorro         IN VARCHAR2,
                                            p_strDesconocido         IN VARCHAR2,
                                            --p_vbt_cuentas_edo_cuenta OUT vbt_cuentas_edo_cuenta, 
                      p_vbt_cuentas_edo_cuenta OUT SYS_REFCURSOR, 
                                            p_resultado OUT VARCHAR2);                                                        
                     
     PROCEDURE vbt_cuentas_edo_mesano_pr (p_vbt_cuentas_edo_mesano OUT vbt_cuentas_edo_mesano, 
                                            p_resultado OUT VARCHAR2);
     
      PROCEDURE vbt_cuentas_edo_cabecera_pr (p_strNumeroCuenta IN VARCHAR2,
                                            p_strCodigoCartera IN VARCHAR2,
                                            p_strCmbMes IN VARCHAR2,
                                            p_strTxtAño IN VARCHAR2,
                                            p_vbt_cuentas_edo_cabecera OUT vbt_cuentas_edo_cabecera, 
                                            p_resultado OUT VARCHAR2);
                      
               
            
       PROCEDURE vbt_cuentas_edo_detalle_pr (p_strNumeroCuenta IN VARCHAR2,
                                            p_strCodigoCartera IN VARCHAR2,
                                            p_strCmbMes IN VARCHAR2,
                                            p_strTxtAño IN VARCHAR2,
                                            p_vbt_cuentas_edo_detalle OUT vbt_cuentas_edo_detalle, 
                                            p_resultado OUT VARCHAR2);
                      
            
       PROCEDURE vbt_fondos_blo_cartera_pr (p_strCarteras IN VARCHAR2,
                                            p_vbt_fondos_blo_cartera OUT vbt_fondos_blo_cartera, 
                                            p_resultado OUT VARCHAR2);   
                      
     PROCEDURE vbt_fondos_blo_movimi_pr (p_strCmbBuscar  IN VARCHAR2,
                                        p_strCodigoFondo   IN VARCHAR2,
                                        p_strCodigoCartera IN VARCHAR2,
                                        p_strTxtFechaDesde IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_vbt_fondos_blo_movimi OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2);                                                              
     
            PROCEDURE vbt_fondos_sal_cartera_pr (p_strCarteras IN OUT VARCHAR2,
                                            p_vbt_fondos_sal_cartera OUT vbt_fondos_sal_cartera, 
                                            p_resultado OUT VARCHAR2,
                                            p_sql OUT VARCHAR2);        

      
     PROCEDURE vbt_fondos_sal_saldos_pr (p_strCodigoCartera IN VARCHAR2,
                                            p_strCodigoFondo IN VARCHAR2,
                                            p_vbt_fondos_sal_saldos OUT vbt_fondos_sal_saldos, 
                                            p_resultado OUT VARCHAR2);
                     
     PROCEDURE vbt_fondos_sal_movimi_pr (p_strCodigoCartera IN VARCHAR2,
                                            p_strCodigoFondo IN VARCHAR2,
                                            p_vbt_fondos_sal_movimi OUT vbt_fondos_sal_movimi, 
                                            p_resultado OUT VARCHAR2);               
     
          
      PROCEDURE vbt_porta_con_saldo_pr (p_num_cta IN VARCHAR2,
                                            p_codproserv IN VARCHAR2,
                                            p_vbt_porta_con_saldo OUT vbt_porta_con_saldo, 
                                            p_resultado OUT VARCHAR2);   
                   
       PROCEDURE vbt_porta_con_det_pr (p_nro_cuenta IN VARCHAR2,
                                        p_vbt_porta_con_det OUT vbt_porta_con_det, 
                                            p_resultado OUT VARCHAR2);
                      
       PROCEDURE vbt_fondos_sal_trans_pr (p_TAGEnTransito  IN VARCHAR2,
                                        p_strCodigoFondo   IN VARCHAR2,
                                        p_strCodigoCartera IN VARCHAR2,
                                        p_strCmbDiasConsulta IN VARCHAR2,
                                        p_strTxtFechaDesde  IN VARCHAR2,
                                        p_strTxtFechaHasta IN VARCHAR2,
                                        p_strTipoTransaccion IN VARCHAR2,
                                        p_vbt_fondos_sal_trans OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2,
                                        p_idioma  IN VARCHAR2);                   
                            
             PROCEDURE vbt_tdcconsulta_tar_pr (p_strCarteras IN OUT VARCHAR2,
                                        p_vbt_tdcconsulta_tar OUT vbt_tdcconsulta_tar, 
                                        p_resultado OUT VARCHAR2);      
                    
     PROCEDURE vbt_tdcconsulta_mes_pr (p_nro_cuenta IN VARCHAR2,
                                         p_vbt_tdcconsulta_mes OUT vbt_tdcconsulta_mes, 
                                         p_resultado OUT VARCHAR2);               
                                                                                  
   
     PROCEDURE vbt_tdcconsulta_cab_pr (p_num_cta    IN VARCHAR2,
                                      p_codproserv  IN VARCHAR2,
                                      p_mes         IN VARCHAR2,
                                                          p_vbt_tdcconsulta_cab OUT vbt_tdcconsulta_cab, 
                                                          p_resultado OUT VARCHAR2);
                                     
            PROCEDURE vbt_tdcconsulta_mov_pr (p_num_cta    IN VARCHAR2,
                                         p_codproserv  IN VARCHAR2,
                                         p_mes         IN VARCHAR2,
                                         p_vbt_tdcconsulta_mov OUT vbt_tdcconsulta_mov, 
                                         p_resultado OUT VARCHAR2);        
         

      
            PROCEDURE vbt_tdmovimi_ctas_pr (p_strCarteras    IN OUT VARCHAR2,
                                         p_vbt_tdmovimi_ctas OUT vbt_tdmovimi_ctas, 
                                         p_resultado OUT VARCHAR2);
                     
         PROCEDURE vbt_tdmovimi_trans_pr (p_strNumeroCuentaTdc    IN VARCHAR2,
                                       p_vbt_tdmovimi_trans OUT vbt_tdmovimi_trans, 
                                         p_resultado OUT VARCHAR2); 
                     
         PROCEDURE vbt_cuentasaldo_cuentas_pr (p_strCarteras    IN VARCHAR2,
                                       p_vbt_cuentasaldo_cuentas OUT vbt_cuentasaldo_cuentas, 
                                         p_resultado OUT VARCHAR2);            
                     
                PROCEDURE vbt_cuentasaldo_saldos_pr (p_strCarteras    IN VARCHAR2,
                                            p_strNumeroCuenta  IN VARCHAR2,
                      p_strCodigoCartera IN VARCHAR2,
                                            p_vbt_cuentasaldo_saldos OUT vbt_cuentasaldo_saldos, 
                                        p_resultado OUT VARCHAR2);         
                     
              PROCEDURE vbt_cuentasaldo_tipos_pr (p_strNumeroCuenta  IN VARCHAR2,
                                            p_strCodigoCartera IN VARCHAR2,
                                            p_vbt_cuentasaldo_tipos OUT vbt_cuentasaldo_tipos, 
                                            p_resultado OUT VARCHAR2);               
          
       
        PROCEDURE vbt_cuentasaldo_trans_pr (p_strNumeroCuenta   IN VARCHAR2,
                                        p_strCmbDiasConsulta   IN VARCHAR2,
                                        p_strTxtFechaDesde     IN VARCHAR2,
                                        p_strTxtFechaHasta     IN VARCHAR2,
                                        p_strCmbTipoTransaccion IN VARCHAR2,
                                        p_vbt_cuentasaldo_trans OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2,
                                        p_sql OUT VARCHAR2);
                    
      PROCEDURE vbt_directorio_pr (p_contrato  IN VARCHAR2,
                                                                    p_vbt_directorio OUT vbt_directorio, 
                                            p_resultado OUT VARCHAR2);    
                                            
      PROCEDURE vbt_directorioAutocompletar_pr (p_strLogin  IN VARCHAR2,
                                              p_vbt_directorioautocompletar OUT vbt_directorioautocompletar, 
                                            p_resultado OUT VARCHAR2);                                      
                        
      PROCEDURE vbt_directorio_agregar_pr (p_strLogin  IN VARCHAR2,
                                            p_codigoTemplate  IN VARCHAR2,
                                            p_vbt_directorio_agregar OUT vbt_directorio_agregar, 
                                          p_resultado OUT VARCHAR2);
       
      PROCEDURE vbt_colocacerti_cierre_pr(p_strCarteras  IN OUT VARCHAR2,
                                                  p_vbt_colocacerti_cierre OUT vbt_colocacerti_cierre, 
                                                  p_resultado OUT VARCHAR2);    
                          
       PROCEDURE vbt_colocacerti_coloca_pr (p_login  IN OUT VARCHAR2,
                                                  p_strFechaCierre IN VARCHAR2,
                                                  p_strCmbBuscar   IN VARCHAR2,
                                                  p_strTxtFechaDesde   IN VARCHAR2,
                                                  p_strTxtFechaHasta   IN VARCHAR2,
                                                p_vbt_colocacerti_coloca OUT SYS_REFCURSOR,  
                                                  p_resultado OUT VARCHAR2,
                                                  p_sql OUT VARCHAR2);    
                          
      PROCEDURE vbt_colocavenci_cierre_pr (p_strCarteras    IN VARCHAR2,
                                p_vbt_colocavenci_cierre OUT vbt_colocavenci_cierre,
                                p_resultado OUT VARCHAR2);
                                
       PROCEDURE vbt_colocavenci_coloca_pr (p_strCarteras            IN OUT VARCHAR2,
                                      p_strFechaCierre         IN VARCHAR2,
                                      p_strCmbBuscar           IN VARCHAR2,
                                      p_strTxtFechaDesde     IN VARCHAR2,
                                      p_strTxtFechaHasta    IN VARCHAR2,
                                      p_vbt_colocavenci_cierre OUT SYS_REFCURSOR,  
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2);
                    
    PROCEDURE vbt_ConsultaExcepcion_pr  (p_strTxtDesde         IN VARCHAR2,
                                      p_strTxtHasta            IN VARCHAR2,
                                      p_vbt_ConsultaExcepcion OUT SYS_REFCURSOR,  
                                      p_resultado OUT VARCHAR2,
                                      p_sql       OUT VARCHAR2);                                                                             
                                  
      PROCEDURE vbt_PDFBloqueoDetalle_pr (p_cliente    IN VARCHAR2,
                                p_vbt_PDFBloqueoDetalle OUT vbt_PDFBloqueoDetalle,
                                p_resultado OUT VARCHAR2);  
                                
     
 PROCEDURE vbt_PDFCertiApertura_certi_pr (p_strCodInstrumento    IN VARCHAR2,
                                p_vbt_PDFCertiApertura_certi OUT vbt_PDFCertiApertura_certi,
                                p_resultado OUT VARCHAR2);
                                
    PROCEDURE vbt_PDFCertiApertura_movi_pr (p_strCodInstrumento    IN VARCHAR2,
                                p_vbt_PDFCertiApertura_movi OUT vbt_PDFCertiApertura_movi,
                                p_resultado OUT VARCHAR2);   
                                
                                
   PROCEDURE vbt_PDFCertivenci_certi_pr (p_strCodInstrumento    IN VARCHAR2,
                                p_vbt_PDFCertivenci_certi OUT vbt_PDFCertivenci_certi,
                                p_resultado OUT VARCHAR2);                          
                 
    
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
                                      p_resultado OUT VARCHAR2);
                    
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
                                      p_resultado OUT VARCHAR2);   
                                      
PROCEDURE vbt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
                                      P_NUM_CONTRATO  IN VARCHAR2,
                                      P_tipo_persona IN VARCHAR2,
                                     p_datosPorDefecto OUT vbt_datos_diseneBanco,
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2);   
                                      
PROCEDURE vbt_movimi_pordefecto_pr (p_datosBase OUT vbt_datos_diseneBancoBase,
                                      p_resultado OUT VARCHAR2,
                                      p_tipo IN VARCHAR2);                                                                             
                                      
   PROCEDURE vbt_consultarTelefono_pr (p_strCliente    IN VARCHAR2,
                                         p_TAGHabitacion    IN VARCHAR2,
                                         p_TAGCelular    IN VARCHAR2,
                                         p_TAGOficina    IN VARCHAR2,
                                         p_TAGFax    IN VARCHAR2,
                                         p_TAGOtros    IN VARCHAR2,
                                p_vbt_consultarTelefono OUT vbt_consultarTelefono,
                                p_resultado OUT VARCHAR2);                                            
    
   
   PROCEDURE vbt_consultarDirecciones_pr (p_strCliente    IN VARCHAR2,
                                         p_TAGApartadoPostal    IN VARCHAR2,
                                         p_TAGHabitacion    IN VARCHAR2,
                                         p_TAGOficina    IN VARCHAR2,
                                         p_TAGSi    IN VARCHAR2,
                                         p_TAGOtros    IN VARCHAR2,
                                         p_TAGNo    IN VARCHAR2,
                                         p_TAGEmail IN VARCHAR2,
                                p_vbt_consultarDirecciones OUT vbt_consultarDirecciones,
                                p_resultado OUT VARCHAR2);
   
    PROCEDURE vbt_consultarDocumentos_pr (p_strCliente    IN VARCHAR2,
                                p_vbt_consultarDocumentos OUT vbt_consultarDocumentos,
                                p_resultado OUT VARCHAR2);
       
    
    PROCEDURE vbt_consultarRepresentantes_pr (p_strCliente    IN VARCHAR2,
                                            p_TAGApoderado      IN VARCHAR2,
                                            p_TAGRepresentante IN VARCHAR2,
                                            p_TAGOtros            IN VARCHAR2,
                                            p_TAGActivo            IN VARCHAR2,
                                            p_TAGInactivo        IN VARCHAR2,
                                            p_vbt_consultarRepresentantes OUT vbt_consultarRepresentantes,
                                p_resultado OUT VARCHAR2);
                                
    PROCEDURE vbt_consultarContactos_pr (p_strCliente    IN VARCHAR2,
                                         p_vbt_consultarContactos OUT vbt_consultarContactos,
                                         p_resultado OUT VARCHAR2);

      
 PROCEDURE vbt_consultarCarteras_pr (p_strCliente    IN VARCHAR2,
                                       p_TAGCompartida      IN VARCHAR2,
                                       p_TAGIndividual      IN VARCHAR2,
                                        p_TAGInactiva         IN VARCHAR2,
                                        p_TAGActiva  IN VARCHAR2,
                                            p_strCarteras            IN OUT VARCHAR2,
                                            p_vbt_consultarCarteras OUT SYS_REFCURSOR, 
                                            p_resultado OUT VARCHAR2,
                                            p_sql OUT VARCHAR2);
                                            
                                            
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
                                      p_sql OUT VARCHAR2); 
                                                                 
                                      
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
             );     
             
             
             
             
             PROCEDURE vbt_busca_directorio_pr (p_login                   IN VARCHAR2,
                                            p_codigoTemplate              IN VARCHAR2,
                                            p_strCmbTipoIdCtaCredito      IN VARCHAR2,
                                            p_strTxtCuentaCredito         IN VARCHAR2,
                                            p_CmbTipoCodBancoBene         IN VARCHAR2,
                                            p_strTxtCodBancoBene         IN VARCHAR2,
                                            p_CmbTipoCodBancoInterme      IN VARCHAR2,
                                            p_TxtCodBancoBancoInter       IN VARCHAR2,
                                            p_TxtCuentaFuturoCredito      IN VARCHAR2,
                                            p_vbt_busca_directorio        OUT vbt_busca_directorio,
                                            p_resultado                   OUT VARCHAR2); 
                                            
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
             );  
                                                                                   
 PROCEDURE vbt_borrar_directorio_pr (p_login                            IN VARCHAR2,
                                      p_codigoTemplate              IN VARCHAR2,
                                      p_resultado                 OUT VARCHAR2);                                  
  
  PROCEDURE vbt_inverio_bloqueo_pr (p_strCarteras IN OUT VARCHAR2,
                                            p_vbt_inverio_bloqueo_pr OUT vbt_otrasinver_cartera, 
                                            p_resultado OUT VARCHAR2,
                                            p_sql OUT VARCHAR2);
 
  PROCEDURE vbt_consulta_paises_excluidos (c_paises_excluidos out vbt_paises_excluidos, p_resultado OUT VARCHAR2);
  
  PROCEDURE vbt_inserta_paises_excluidos (P_BANK_TYPE_NUMBER VARCHAR2, P_BANK_TYPE VARCHAR2,  P_USER_NAME VARCHAR2, P_FECHA_CREACION DATE, P_DESTINO_A_BLOQUEAR VARCHAR2, p_resultado OUT VARCHAR2);                 

  PROCEDURE vbt_logAction_pr (p_username VARCHAR2, p_id_accion VARCHAR2, p_id_app VARCHAR2, p_id_object VARCHAR2, p_AFFECTED_OBJECT_ID VARCHAR2, p_IP VARCHAR2, p_COMMENTS VARCHAR2, p_resultado OUT VARCHAR2);
  
  PROCEDURE vbt_logActionFC_pr (p_username VARCHAR2, p_id_accion VARCHAR2, p_id_app VARCHAR2, p_id_object VARCHAR2, p_AFFECTED_OBJECT_ID VARCHAR2, p_IP VARCHAR2, p_COMMENTS VARCHAR2,p_num_contrato VARCHAR2, p_resultado OUT VARCHAR2);

  PROCEDURE vbt_parametrosGlobalesFC  ( cs_parametrosGlobalesFC OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);

  PROCEDURE vbt_parametrosPersonales_FC  (p_num_contrato VARCHAR2, cs_parametrosPersonalesFC OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);

  PROCEDURE vbt_logParametrosPersonalesFC  ( p_num_max_trans_diarias NUMBER, p_mto_max_trans_diarias NUMBER, p_mto_max_por_trans NUMBER, p_correo_notificacion VARCHAR2, p_codpercli VARCHAR2, p_resultado OUT VARCHAR2);
 
  PROCEDURE vbt_guardarParametrosP_FC_pr (
                                      P_NUM_CONTRATO  IN VARCHAR2,
                                      P_VBT_NMTD       IN NUMBER,
                                       P_VBT_MMTD       IN NUMBER,
                                      P_VBT_MMTO       IN NUMBER,                                      
                                      P_CORREO  IN VARCHAR2,
                                      p_resultado OUT VARCHAR2); 
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
                     p_strPersonalizado   in VARCHAR2,
                    p_strCartera  in VARCHAR2 );
                    
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
                                 p_roles                        in VARCHAR2,
                                 p_usuarioSesion               in VARCHAR2);
                                 
                                         
 PROCEDURE vbt_parametros_globales_pr (p_datosPorDefecto OUT vbt_datos_diseneBanco,
                                      p_resultado OUT VARCHAR2,
                                      p_sql OUT VARCHAR2,
                                      p_tipo IN VARCHAR2);                                         
                                                          
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
                                      p_resultado OUT VARCHAR2);
                                      
                                    
PROCEDURE vbt_parametros_contratos_pr (P_NUM_CONTRATO  IN VARCHAR2,
                                       p_datosPorDefecto OUT vbt_datos_diseneBanco,
                                       p_resultado OUT VARCHAR2
                                   );
PROCEDURE vbt_cliente_principal_pr  (p_num_contrato VARCHAR2, 
                                     cs_cliente_principal OUT SYS_REFCURSOR, 
                                     p_resultado OUT VARCHAR2);
PROCEDURE vbt_correo_cliente_cuenta  (p_cuenta VARCHAR2, cs_cliente_principal OUT SYS_REFCURSOR, 
                                       p_resultado OUT VARCHAR2);
 
PROCEDURE vbt_correos_liberar (p_num_referencia IN VARCHAR2,p_correo IN VARCHAR2  );

PROCEDURE vbt_errores_pr (p_direccion_ip IN VARCHAR2, p_mensaje_error IN VARCHAR2, p_nombre_archivo IN VARCHAR2, p_valores_url IN VARCHAR2, p_user IN VARCHAR2, p_num_contrato IN VARCHAR2,   p_resultado OUT VARCHAR2);
PROCEDURE vbt_templateId_pr (p_usuario    IN VARCHAR2, p_nombre_template    IN VARCHAR2, p_datos OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);

/**
 *      FUNCIÓN:        vbt_cant_solicitudes_pendientes_pr(IN VARCHAR2, IN VARCHAR2, OUT NUMBER, OUT VARCHAR2)
 *      AUTOR:          RRANGEL
 *      FECHA:          10/10/2014
 *      DESCRIPCIÓN:    CUENTA LA CANTIDAD DE SOLICITUDES PENDIENTES EXISTENTES 
 *                      PARA LOS ESTATUS 'C' (CARGADO) Y 'A' (APROBADO) 
 * */
PROCEDURE vbt_cant_solpend_pr (P_IN_CONTRATO IN VARCHAR2, P_IN_ESTATUS IN VARCHAR2, P_OUT_CANTIDAD OUT NUMBER, P_OUT_RESULTADO OUT VARCHAR2);
/** FIN vbt_cant_solicitudes_pendientes_pr(IN VARCHAR2, IN VARCHAR2, OUT NUMBER, OUT VARCHAR2) */
 
PROCEDURE vbt_roles_custom_pr (p_categoria IN VARCHAR2, p_roles  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 );

PROCEDURE vbt_roles_custom_detalle_pr (p_roles IN VARCHAR2, p_padres OUT SYS_REFCURSOR, p_hijos  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 );

PROCEDURE vbt_roles_usuario_pr (p_login IN VARCHAR2, p_roles  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 );  

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
      p_usuarioSesion              IN     VARCHAR2);
 PROCEDURE vbt_aprobar_liberar_custom_pr (p_login IN VARCHAR2, p_roles  OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2 );
   PROCEDURE VBT_BankCodeBuscar_pr (p_strCmbTipoCodBanco           IN VARCHAR2, 
                                    p_strTxtCodBanco                 IN VARCHAR2,
                                    p_strTxtNombreBanco             IN VARCHAR2,
                                    p_strCmbEstatus                      IN VARCHAR2,
                                    p_strEstatus                      IN VARCHAR2,
                                    p_cs_BankCodeBuscar OUT SYS_REFCURSOR,
                                   p_resultado OUT VARCHAR2,
                                   p_salida OUT VARCHAR2);
                                   
  PROCEDURE VBT_EXCLUIR_BANCO (P_BANK_CODE VARCHAR2, P_BANK_TYPE VARCHAR2, P_USUARIO VARCHAR2, P_RESULTADO OUT VARCHAR2);
  
  PROCEDURE VBT_INCLUIR_BANCO (P_BANK_CODE VARCHAR2, P_BANK_TYPE VARCHAR2, P_USUARIO VARCHAR2, P_RESULTADO OUT VARCHAR2);
  PROCEDURE vbt_imagen_home (p_fecha IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR);
  PROCEDURE vbt_info_home (p_contenido OUT VARCHAR2, p_resultado OUT VARCHAR2, p_contenido_eng  OUT VARCHAR2);
  PROCEDURE VBT_AVISOS_PR (p_tipo_aviso IN VARCHAR2,
                             p_tipo_status IN VARCHAR2,
                             p_fecha_inicio IN VARCHAR2,
                             p_fecha_fin VARCHAR2,
                             p_vbt_avisos  OUT SYS_REFCURSOR,
                             p_resultado OUT VARCHAR2,
                             p_sql OUT VARCHAR2,
                             p_excluir_fechas IN VARCHAR2);
  PROCEDURE vbt_imagen_id (p_codigo IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR);
    
   PROCEDURE VBT_ESTADO_CUENTA_FONDOS (p_codemp IN VARCHAR2, p_codcar IN VARCHAR2, p_fecha_emision IN VARCHAR2, p_razon IN VARCHAR2, p_moneda IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR);

   PROCEDURE VBT_EDO_FONDOS_DIVIDENDO (p_codemp IN VARCHAR2, p_fecha_emision IN VARCHAR2, p_cursor OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);
   
   PROCEDURE vbt_detalle_pago_tdc (p_num_cta    IN VARCHAR2,
                                      p_vbt_tdcconsulta_cab OUT SYS_REFCURSOR,
                                      p_resultado OUT VARCHAR2);
                                      
  PROCEDURE VBT_CARGAR_EDO_FONDOMUTUAL(p_codemp IN VARCHAR2, p_resultado OUT VARCHAR2, p_cursor OUT SYS_REFCURSOR);
                           
 END VBTONLINE;
/


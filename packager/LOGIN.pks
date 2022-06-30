CREATE OR REPLACE PACKAGE VBTONLINE_TRANS.LOGIN AS
TYPE login_tagusuarios  IS REF CURSOR;
TYPE login_taglogueo  IS REF CURSOR;
TYPE login_opeEsp  IS REF CURSOR;
TYPE bac_grpuopcper  IS REF CURSOR;
--RETURN tt%ROWTYPE;
 --TYPE login_tagusuarios IS REF CURSOR
--    RETURN 
 --            mnBackoffice%ROWTYPE;
                        
          PROCEDURE BAC_INGRE_P (P_LOGIN  IN VARCHAR2, 
                        P_PASSJ IN out VARCHAR2, 
                        prespuesta OUT VARCHAR2);
                        
  PROCEDURE logout_pr (P_LOGIN  IN VARCHAR2,
                       p_respuesta OUT VARCHAR2);

             PROCEDURE buscarPassword_pr (P_LOGIN  IN VARCHAR2, 
                                 P_PASSJ OUT VARCHAR2, 
                                 P_RESPUESTA OUT VARCHAR2);              
         
   
    PROCEDURE login_tagusuarios_pr (p_login            IN VARCHAR2, 
                                   p_codcia            IN VARCHAR2, 
                                   p_codsis            IN VARCHAR2, 
                                  p_login_tagusuarios OUT login_tagusuarios, 
                                  p_resultado OUT VARCHAR2);
                                  
        PROCEDURE OPE_ESPECIALES_P (P_LOGIN  IN VARCHAR2, 
                        P_PASSJ IN VARCHAR2, 
                        prespuesta OUT VARCHAR2);     
                        
        PROCEDURE login_updatepass_pr(
                            p_miPasswTemp   IN VARCHAR2,
                            p_login                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2);
                            
        PROCEDURE login_updateuser_pr(
                            p_miPassw   IN VARCHAR2,
                            p_login                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2); 
                            
       PROCEDURE login_asignarPassOpeEsp_pr(
                            p_miPassw   IN VARCHAR2,
                            p_login                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2);   
                            
       PROCEDURE verificarPasswordOpeEsp_pr(p_login                IN VARCHAR2,
                                            p_cursorOpeEsp    OUT login_opeEsp,
                                           p_resultado                OUT VARCHAR2); 
                                           
     PROCEDURE login2_pr (P_LOGIN  IN VARCHAR2, 
                     p_login_taglogueo OUT login_taglogueo,
                     p_resultado                OUT VARCHAR2);   
                     
     PROCEDURE validarterminoscondiciones_pr (P_CONTRATO  IN VARCHAR2, aceptoterminos OUT VARCHAR2, p_resultado OUT VARCHAR2);                
                     
     PROCEDURE permisosUsuarioOpcion_pr (p_login in varchar2,  
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2,  
                                  p_bac_grpuopcper OUT bac_grpuopcper, 
                                  p_resultado OUT VARCHAR2);                                                                                                   
                                  
     PROCEDURE bloquear_usuario_pr (P_LOGIN  IN VARCHAR2,
                                  p_respuesta OUT VARCHAR2); 

END LOGIN;
/


CREATE OR REPLACE PACKAGE VBTONLINE_TRANS.backoffice AS
-- bac_usuario_con  SYS_REFCURSOR;
 TYPE bac_usuariosconcon IS REF CURSOR;
 TYPE bac_usuariostacon IS REF CURSOR;
 TYPE bac_usuarioconsul IS REF CURSOR;
 TYPE bac_usuOpcionGrupoInt IS REF CURSOR;
 TYPE bac_usuOpcionUsuarioInt IS REF CURSOR;
 TYPE bac_OpcionUsuario  IS REF CURSOR;
 TYPE bac_usueditar_con  IS REF CURSOR;
 TYPE bac_elementostipo  IS REF CURSOR;
 TYPE bac_correoenvio  IS REF CURSOR;
 TYPE bac_telefonolocal  IS REF CURSOR; 
 TYPE bac_telefonoenvio  IS REF CURSOR; 
 TYPE bac_grupocliente  IS REF CURSOR;
 TYPE bac_usuopcionsis  IS REF CURSOR;
 TYPE bac_gruposcon  IS REF CURSOR;
 TYPE bac_gruposgrp  IS REF CURSOR;
 TYPE bac_gruposopc  IS REF CURSOR;
 TYPE bac_gruposopcsis  IS REF CURSOR;
 TYPE bac_grpuopcper  IS REF CURSOR;
 TYPE bac_contratousuarios  IS REF CURSOR;
 TYPE bac_contratousugrupo  IS REF CURSOR;
 TYPE bac_contratos  IS REF CURSOR;
 TYPE bac_contraagregarctaper  IS REF CURSOR;  
 TYPE bac_contraagregardir  IS REF CURSOR;  
 TYPE bac_contraagregarclien  IS REF CURSOR;  
 TYPE bac_contraagregarcarteras  IS REF CURSOR;  
 TYPE bac_contraeditarcon  IS REF CURSOR;  
 TYPE bac_contraeditarrechazo  IS REF CURSOR;  
 TYPE bac_contraeditarcart  IS REF CURSOR;  
 TYPE bac_contraeditarrechat  IS REF CURSOR;
 TYPE bac_cargarcarteraindividual  IS REF CURSOR;
 TYPE contraeditarrlog  IS REF CURSOR;  
 TYPE bac_gruposopctot  IS REF CURSOR;   
 TYPE bac_coneditargrupos  IS REF CURSOR;    
 TYPE bac_coneditarctas  IS REF CURSOR; 
 TYPE bac_coneditardep  IS REF CURSOR;   
 TYPE bac_contraeditarcarteras IS REF CURSOR;
 TYPE bac_coneditarmut  IS REF CURSOR;    
 TYPE bac_elemencodtipo IS REF CURSOR;   
 TYPE accionessistema IS REF CURSOR;
 TYPE accesosistema IS REF CURSOR;
 TYPE accesoopcusuario IS REF CURSOR;
 TYPE accesogrupossuario IS REF CURSOR;
 TYPE acciones IS REF CURSOR;
 TYPE objetos IS REF CURSOR;
 
 /*
    DESCRIPCION:    Cursor a devolver por el procedimiento 
                    BAC_PRC_LISTAR_CATEGORIAS_PADRES_OPCIONES
    AUTOR:          RRANGEL
    FECHA:          2014/04/11
    TAG:            C_CATOPC_PADCODOPC
 */
 TYPE C_CATOPC IS REF CURSOR;
 /* FIN: C_CATOPC_PADCODOPC */
 
 
 
 
     PROCEDURE bac_usuario_con_pr (
                    p_strRoot             in VARCHAR2, 
                    p_strLoader         in VARCHAR2, 
                    p_strSupervisor     in VARCHAR2, 
                    p_strCliente         in VARCHAR2, 
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
                     p_strCmbAmbito in VARCHAR2,
                    p_strOrden          in VARCHAR2,
                    hdnAccion           in VARCHAR2,
                     bac_usuario_con  OUT  SYS_REFCURSOR,
                --    p_bac_usuario_con OUT bac_usuario_con, 
                    p_resultado OUT VARCHAR2,
                    p_salida OUT VARCHAR2,
                    p_login             in VARCHAR2);

      PROCEDURE bac_usuarioagregar_pr (p_strTxtUsuarioAgregar         in VARCHAR2,
                                 p_strRandomPassword                in VARCHAR2,
                                 p_login                            in VARCHAR2,
                                 p_strTxtdireccion                  in VARCHAR2,
                                 p_strTxtTlfCelularAgregar          in VARCHAR2,
                                 p_strTxtTelefonoAgregar            in VARCHAR2,
                                 p_strTxtEmailAgregar               in VARCHAR2,
                                 p_miPasswTemp                      in VARCHAR2,
                                 p_strCmbTipoUsuarioAgregar         in VARCHAR2,
                                 p_strCmbPRECIRIFAgregar            in VARCHAR2,
                                 p_strTxtCIRIFAgregar               in VARCHAR2,
                                 p_strTxtNombre                     in VARCHAR2,
                                 p_strTxtgrupo                      in VARCHAR2,
                                  p_strTxtAmbito                  in VARCHAR2,
                                 p_resultado OUT VARCHAR2,
                                 p_inte_login out varchar2);    
                                 
                                 
    PROCEDURE bac_usuarioeditar_pr(p_strTxtUsuarioEditar      IN VARCHAR2,
                            p_strTxtTlfCelularEditar   IN VARCHAR2,
                            p_strTxtTelefonoEditar     IN VARCHAR2,
                            p_strTxtEmailEditar        IN VARCHAR2,
                            p_strCmbPRECIRIFEditar     IN VARCHAR2,
                            p_strTxtCIRIFEditar        IN VARCHAR2,
                            p_strTxtdireccion          IN VARCHAR2,
                            p_strTxtNombre             IN VARCHAR2,
                            p_strCmbStatusCuentaEditar IN VARCHAR2,
                            p_strCmbTipoUsuarioEditar  IN VARCHAR2,
                            p_hdnCambioEstatus         IN VARCHAR2,
                            p_strTxtgrupo              IN VARCHAR2,
                            
                            p_strTxtCodPerCli          IN VARCHAR2, 
                            p_strTxtAmbito             IN VARCHAR2, 
                            p_strCodPais               IN VARCHAR2, 
                            p_resultado                OUT VARCHAR2);
                            
    PROCEDURE bac_usuariosetpass_pr(
                            p_strTxtUsuarioEditar   IN VARCHAR2,
                            p_miPasswTemp                IN VARCHAR2,
                            p_resultado                OUT VARCHAR2);                        

     PROCEDURE bac_usuariosconcon_pr (p_strTxtUsuarioEditar in varchar2, 
                                      p_bac_usuariosconcon OUT bac_usuariosconcon, 
                                      p_resultado OUT VARCHAR2);


     PROCEDURE bac_usuariostacon_pr (p_strTxtUsuarioEditar in varchar2, 
                                    p_bac_usuariostacon OUT bac_usuariostacon, 
                                    p_resultado OUT VARCHAR2);
                                    
       PROCEDURE bac_usuarioconsul_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_bac_usuarioconsul OUT bac_usuarioconsul, 
                                  p_resultado OUT VARCHAR2);                              

     PROCEDURE bac_usuOpcionGrupoInt_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2, 
                                  p_bac_usuOpcionGrupoInt OUT bac_usuOpcionGrupoInt, 
                                  p_resultado OUT VARCHAR2);

    PROCEDURE bac_usuOpcionUsuarioInt_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2, 
                                  p_bac_usuOpcionUsuarioInt OUT bac_usuOpcionUsuarioInt, 
                                  p_resultado OUT VARCHAR2);
                                  
                                  
    PROCEDURE bac_OpcionUsuario_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2, 
                                  p_bac_OpcionUsuario OUT bac_OpcionUsuario, 
                                  p_resultado OUT VARCHAR2);

    PROCEDURE bac_usudel_insr_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2, 
                                  p_strTxttipacc in varchar2, 
                                  p_strflag in varchar2, 
                                  p_resultado OUT VARCHAR2);
                                  
    PROCEDURE bac_usuclienteeditar_pr (p_strTxtEmailEditar             in varchar2, 
                                   p_hdnCambioEstatus                  in varchar2,
                                   p_strCmbStatusCuentaEditar          in varchar2, 
                                   p_strTxtUsuarioEditar               in varchar2, 
                                   p_strCmbTipoUsuarioEditar           in varchar2,
                                    p_strCmbAmbitoEditar     in varchar2,
                                   p_login                             in varchar2,
                                   p_telefono_local                    in varchar2,
                                   p_telefono_celular                  in varchar2,
                                   p_codigo_pais                       in varchar2,
                                   p_nombre                            in varchar2,
                                   p_direccion                         in varchar2,
                                   p_rif                               in varchar2,
                                   p_resultado OUT VARCHAR2);
                                   
    PROCEDURE bac_usueditar_con_pr (p_strTxtUsuarioEditar IN VARCHAR2, 
                                   p_bac_usueditar_con OUT bac_usueditar_con, 
                                   p_resultado OUT VARCHAR2);                               


      PROCEDURE bac_elementostipo_pr (p_strTxttipo IN VARCHAR2, 
                                   p_bac_elementostipo OUT bac_elementostipo, 
                                   p_resultado OUT VARCHAR2);
 
       PROCEDURE bac_correoenvio_pr (p_strTxtcodpercli IN VARCHAR2, 
                                   p_bac_correoenvio OUT bac_correoenvio, 
                                   p_resultado OUT VARCHAR2);

       PROCEDURE bac_grupocliente_pr (p_bac_grupocliente OUT bac_grupocliente, 
                                   p_resultado OUT VARCHAR2);
                                   
       PROCEDURE bac_grupocliente_categoria_pr (p_categoria IN VARCHAR2, p_bac_grupocliente OUT bac_grupocliente, 
                                   p_resultado OUT VARCHAR2);
                                   
                                   
         PROCEDURE bac_usuopcionsis_pr (p_strCmbTipoUsuarioEditar IN VARCHAR2, 
                                  p_strCmbcodsys            IN VARCHAR2, 
                                  p_bac_usuopcionsis OUT bac_usuopcionsis, 
                                  p_resultado OUT VARCHAR2);                            

        PROCEDURE bac_gruposcon_pr (p_bac_gruposcon OUT bac_gruposcon, 
                                  p_resultado OUT VARCHAR2);

      PROCEDURE bac_gruposgrp_pr (p_strCmbcodgrp            IN VARCHAR2, 
                                  p_bac_gruposgrp OUT bac_gruposgrp, 
                                  p_resultado OUT VARCHAR2);  
 
      PROCEDURE bac_gruposopc_pr (p_strCmbcodopc            IN VARCHAR2, 
                                  p_bac_gruposopc OUT bac_gruposopc, 
                                  p_resultado OUT VARCHAR2);
                                    
       PROCEDURE bac_gruposopcsis_pr (p_strCmbcodsis        IN VARCHAR2, 
                                  p_editar_categoria        IN VARCHAR2,
                                  p_bac_gruposopcsis        OUT SYS_REFCURSOR, 
                                  p_resultado OUT VARCHAR2);  
   
        PROCEDURE bac_grpudel_insr_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtcodgrp in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2,
                                  p_strTxttipacc in varchar2,         
                                  p_strflag in varchar2, 
                                  p_resultado OUT VARCHAR2);
                                  
PROCEDURE bac_permisosEditarUsuario_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtLogin in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2,
                                  p_strTxttipacc in varchar2,         
                                  p_strflag in varchar2, 
                                  p_resultado OUT VARCHAR2);                                  
 
    PROCEDURE bac_grpuopcper_pr (p_strTxtUsuarioEditar in varchar2, 
                                  p_strTxtcodgrp in varchar2, 
                                  p_strTxtcodcia in varchar2, 
                                  p_strTxtcodsis in varchar2, 
                                  p_strTxtcodopc in varchar2,
                                  p_strTxttipacc in varchar2,         
                                  p_strflag in varchar2, 
                                  p_bac_grpuopcper OUT bac_grpuopcper, 
                                  p_resultado OUT VARCHAR2);  
    
    PROCEDURE bac_contratousuarios_pr (p_bac_contratousuarios OUT bac_contratousuarios, 
                                  p_resultado OUT VARCHAR2); 
                                  
      PROCEDURE bac_contratousugrupo_pr (p_strContrato     IN VARCHAR2,
                                      p_strActiva         IN VARCHAR2,
                                      p_strInactiva     IN VARCHAR2,
                                      p_strCancelada     IN VARCHAR2,
                                      p_strBloqueado     IN VARCHAR2,
                                      p_strDesconocido  IN VARCHAR2,
                                      p_bac_contratousugrupo OUT bac_contratousugrupo, 
                                        p_resultado OUT VARCHAR2);  
/*
    PROCEDURE bac_contratos_pr(
                                    p_strNuevo     IN VARCHAR2,
                                      p_strActivo         IN VARCHAR2,
                                      p_strCancelado     IN VARCHAR2,
                                      p_strInactivo     IN VARCHAR2,
                                      p_strRechazado     IN VARCHAR2,
                                      p_strDesconocido  IN VARCHAR2,
                                      p_hdnAccion        IN VARCHAR2,
                                      p_txtNumeroCartera IN VARCHAR2,
                                      p_strTipoUsuario    IN VARCHAR2,
                                      p_login            IN VARCHAR2,
                                      p_strTxtNumeroContrato    IN VARCHAR2,
                                      p_strTxtUsuario             IN VARCHAR2,
                                      p_strTxtNombreCliente        IN VARCHAR2,
                                      p_strTxtCIRIFCliente        IN VARCHAR2,
                                      p_strCmbEstatus            IN VARCHAR2,        
                                      p_strTxtDesde                IN VARCHAR2,
                                      p_strTxtHasta                IN VARCHAR2,
                                      p_strOrden                IN VARCHAR2,
                                      p_bac_contratos OUT bac_contratos, 
                                        p_resultado OUT VARCHAR2);*/
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
                                      p_resultado OUT VARCHAR2);
                                      */
   PROCEDURE bac_contraagregarctaper_pr (p_codper in varchar2,
                                        p_bac_contraagregarctaper OUT bac_contraagregarctaper, 
                                        p_resultado OUT VARCHAR2);

      PROCEDURE bac_contraagregardir_pr (p_codper in varchar2,
                                        p_bac_contraagregardir OUT bac_contraagregardir, 
                                        p_resultado OUT VARCHAR2);
                                        
      PROCEDURE bac_contraagregarclien_pr (p_codper in varchar2,
                                        p_bac_contraagregarclien OUT bac_contraagregarclien, 
                                        p_resultado OUT VARCHAR2); 
  
      PROCEDURE bac_contraagregarcarteras_pr (p_TAGCompartida in varchar2,
                                        p_TAGIndividual in varchar2,
                                        p_TAGActiva in varchar2,
                                        p_TAGInactiva in varchar2,
                                        p_codcar in varchar2,
                                        p_bac_contraagregarcarteras OUT bac_contraagregarcarteras, 
                                        p_resultado OUT VARCHAR2);
    
     PROCEDURE bac_contraeditarcon_pr (p_strTxtNumeroContratoEditar in varchar2,
                                           p_bac_contraeditarcon OUT bac_contraeditarcon, 
                                           p_resultado OUT VARCHAR2); 
 
      PROCEDURE bac_contraeditarrechazo_pr (p_strTxtNumeroContratoEditar in varchar2,
                                           p_bac_contraeditarrechazo OUT bac_contraeditarrechazo, 
                                           p_resultado OUT VARCHAR2);
                                          
       PROCEDURE bac_contraeditarcart_pr (p_strTxtNumeroContratoEditar in varchar2,
                                           p_bac_contraeditarcart OUT bac_contraeditarcart, 
                                           p_resultado OUT VARCHAR2);
 
         PROCEDURE bac_contraeditarrechat_pr (p_bac_contraeditarrechat OUT bac_contraeditarrechat, 
                                           p_resultado OUT VARCHAR2);
 
   PROCEDURE contralog_pr (p_strTxtNumeroContratoEditar in varchar2,
                                        p_contraeditarrlog OUT contraeditarrlog, 
                                        p_resultado OUT VARCHAR2);       
                                        
       PROCEDURE bac_usuario_clientes_pr (
                    p_strRoot             in VARCHAR2, 
                    p_strLoader         in VARCHAR2, 
                    p_strSupervisor     in VARCHAR2, 
                    p_strCliente         in VARCHAR2, 
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
                    p_strAmbito        in VARCHAR2,                     
                    p_strOrden          in VARCHAR2,
                    hdnAccion           in VARCHAR2,
                    p_strCartera        in VARCHAR2,
                    bac_usuarios_clientes  OUT SYS_REFCURSOR,
                    p_resultado OUT VARCHAR2,
                    p_salida OUT VARCHAR2);
                    
    PROCEDURE bac_gruposopctot_pr (p_strCmbcodopc            IN VARCHAR2, 
                                  p_bac_gruposopctot OUT bac_gruposopctot, 
                                  p_resultado OUT VARCHAR2);
 
      PROCEDURE bac_coneditargrupos_pr (p_strTxtNumeroContratoEditar            IN VARCHAR2, 
                                  p_bac_coneditargrupos OUT bac_coneditargrupos, 
                                  p_resultado OUT VARCHAR2); 
                                         
         PROCEDURE bac_coneditarctas_pr (p_codcar            IN VARCHAR2, 
                                  p_bac_coneditarctas OUT bac_coneditarctas, 
                                  p_resultado OUT VARCHAR2);
        
       PROCEDURE bac_coneditardep_pr (p_codcar            IN VARCHAR2, 
                                  p_bac_coneditardep OUT bac_coneditardep, 
                                  p_resultado OUT VARCHAR2);
                                  
         PROCEDURE bac_contraeditarcarteras_pr (p_TAGCompartida in varchar2,
                                        p_TAGIndividual in varchar2,
                                        p_TAGActiva in varchar2,
                                        p_TAGInactiva in varchar2,
                                        p_strTxtNumeroContratoEditar in varchar2,
                                        p_bac_contraeditarcarteras OUT bac_contraeditarcarteras, 
                                        p_resultado OUT VARCHAR2);
                                        
        PROCEDURE bac_cargarcarteraindividual_pr (p_TAGCompartida in varchar2,
                                        p_TAGIndividual in varchar2,
                                        p_TAGActiva in varchar2,
                                        p_TAGInactiva in varchar2,
                                        p_strTxtNumeroCartera in varchar2,
                                        p_bac_cargarcarteraindividual OUT bac_cargarcarteraindividual, 
                                        p_resultado OUT VARCHAR2);
                                        
        PROCEDURE bac_coneditarmut_pr (p_codcar            IN VARCHAR2, 
                                  p_bac_coneditarmut OUT bac_coneditarmut, 
                                  p_resultado OUT VARCHAR2);
                                  
  /*        PROCEDURE BAC_INGRE_P (P_LOGIN  IN VARCHAR2, 
                        P_PASSJ IN VARCHAR2, 
                        prespuesta OUT VARCHAR2);*/
 
PROCEDURE bac_elemencodtipo_pr (p_bac_elemencodtipo OUT bac_elemencodtipo, 
                                   p_resultado OUT VARCHAR2);  
 
        
       PROCEDURE bac_carterasbuscar_pr (p_TAGCompartida IN VARCHAR2,
                                        p_TAGIndividual IN VARCHAR2,
                                        p_TAGPrincipal    IN VARCHAR2,
                                        p_TAGSecundario    IN VARCHAR2,
                                        p_hdnAccion     IN VARCHAR2, 
                                        p_strTxtCarteraNumero IN VARCHAR2, 
                                        p_strChkNumeroExactoCartera IN VARCHAR2,
                                        p_strTxtCedula_Rif  IN VARCHAR2,
                                        p_strTxtNombreCliente IN VARCHAR2,
                                        p_strTxtAsesor IN VARCHAR2,
                                        p_strTxtEjecutivo  IN VARCHAR2,
                                        p_bac_carterasbuscar OUT SYS_REFCURSOR,
                                        p_resultado OUT VARCHAR2,
                                        p_sqlstring OUT VARCHAR2);
                                        
      PROCEDURE bac_clienteprodu_pr (p_TAGCompartida         IN VARCHAR2,
                                        p_TAGIndividual     IN VARCHAR2,
                                        p_TAGPrincipal        IN VARCHAR2,
                                        p_TAGSecundario        IN VARCHAR2,
                                        p_codpercli         IN VARCHAR2,
                                        p_bac_clienteprodu    OUT SYS_REFCURSOR,
                                        p_resultado         OUT VARCHAR2);
                                        
        PROCEDURE bac_clientebuscar_pr (p_TAGCompartida         IN VARCHAR2,
                                        p_TAGIndividual     IN VARCHAR2,
                                        p_TAGPrincipal        IN VARCHAR2,
                                        p_TAGSecundario        IN VARCHAR2,
                                        p_strTxtCarteraNumero IN VARCHAR2, 
                                        p_strChkNumeroExactoCartera IN VARCHAR2,
                                        p_strTxtCedula_Rif  IN VARCHAR2,
                                        p_strTxtNombreCliente IN VARCHAR2,
                                        p_strTxtAsesor IN VARCHAR2,
                                        p_strTxtEjecutivo  IN VARCHAR2,
                                        p_bac_clientebuscar OUT SYS_REFCURSOR, 
                                        p_resultado OUT VARCHAR2,
                                        p_select OUT VARCHAR2);                             

        PROCEDURE accesosistema_pr (p_login IN VARCHAR2,
                                p_codcia IN VARCHAR2,
                                p_codsis IN VARCHAR2,
                                p_accesosistema OUT accesosistema, 
                                   p_resultado OUT VARCHAR2);
        PROCEDURE accionessistema_pr (p_accionessistema OUT accionessistema,
                 p_resultado OUT VARCHAR2);
                 
        PROCEDURE accesoopcusuario_pr (p_login IN VARCHAR2,
                                p_codcia IN VARCHAR2,
                                p_codsis IN VARCHAR2,
                                p_accesoopcusuario OUT accesoopcusuario, 
                                   p_resultado OUT VARCHAR2);       
                                   
       PROCEDURE accesogrupossuario_pr (p_login IN VARCHAR2,
                                p_codcia IN VARCHAR2,
                                p_codsis IN VARCHAR2,
                                p_accesogrupossuario OUT accesogrupossuario, 
                                   p_resultado OUT VARCHAR2);  

PROCEDURE accionFiltro_pr (p_acciones OUT acciones, 
                                   p_resultado OUT VARCHAR2);  
                                   
PROCEDURE objetosFiltro_pr (p_objetos OUT objetos, 
                                   p_resultado OUT VARCHAR2);   
                                   
                                                                                                                             
PROCEDURE bac_numeroNuevoContrato_pr(p_respuesta OUT VARCHAR2, p_resultado OUT VARCHAR2);
PROCEDURE bac_crearContrato_pr (p_num_contrato VARCHAR2, p_descripcion VARCHAR2, p_creado_por VARCHAR2, p_ip_contrato_transferencia VARCHAR2, p_tipo_contrato VARCHAR2, p_resultado OUT VARCHAR2);
PROCEDURE bac_buscarDireccion_pr (p_codpercli VARCHAR2, cs_direccion OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);
PROCEDURE bac_buscarTelefonos_pr(p_codpercli VARCHAR2, cs_telefonos OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);
PROCEDURE bac_agregarNuevoUsuario_pr( p_login varchar2, p_codpercli varchar2, p_direccion varchar2, p_telefono_celular varchar2, p_telefono varchar2, p_email varchar2, p_password varchar2, p_nombres varchar2, p_cirif varchar2, p_codigo_pais varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_agregarUsuarioGrupo_pr(p_login varchar2, p_codgrp varchar2, p_codsis varchar2, p_codcia varchar2, p_usrid varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_agregarUsuarioContrato_pr(p_login varchar2, p_num_contrato varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_agregarCarterasContrato_pr(p_num_contrato varchar2, p_codcar varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_verificarStatusContrato_pr(p_num_contrato varchar2, p_statusContratoEditar varchar2, cs_status OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);
PROCEDURE bac_editarContrato_pr(p_strDescripcionEditar VARCHAR2, p_strStatusContratoEditar VARCHAR2, p_strNumeroContratoEditar VARCHAR2, p_resultado OUT VARCHAR2);
PROCEDURE bac_editarUsuario_pr(p_strNumeroContratoEditar varchar2, p_strStatusUsuario varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_eliminarAsocContrato_pr(p_strNumeroContratoEditar varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_agregarMotivoRechazo_pr(p_num_contrato varchar2, p_cod_motivo_rechazo varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_borraCartContrat_pr(p_strNumeroContratoEditar varchar2, p_resultado OUT VARCHAR2);
PROCEDURE bac_seleccionaCarteras_pr(p_strNumeroContratoEditar VARCHAR2, cs_carteras OUT SYS_REFCURSOR , p_resultado OUT VARCHAR2);
PROCEDURE bac_buscarUsuarios_pr( p_strNumeroContratoEditar VARCHAR2, cs_usuarios OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);
PROCEDURE bac_usuariosIngresaron_pr( p_strNumeroContratoEditar VARCHAR2, cs_usuarios OUT SYS_REFCURSOR, p_resultado OUT VARCHAR2);
PROCEDURE bac_actPassUser_pr(p_miPasswTemp VARCHAR2, p_strLoginEditar VARCHAR2, p_resultado OUT VARCHAR2);
PROCEDURE bac_eliminarAccionUser_pr(p_login VARCHAR2, p_opcion VARCHAR2,p_resultado OUT VARCHAR2);
PROCEDURE bac_validarusuario_pr (p_usuario          VARCHAR2,p_resultado           OUT VARCHAR2);
PROCEDURE bac_correoenvio_login_pr (p_strTxtcodpercli IN VARCHAR2, p_strTxtlogin IN VARCHAR2, p_bac_correoenvio OUT bac_correoenvio, p_resultado OUT VARCHAR2);
PROCEDURE bac_telefonoenvio_login_pr (p_strTxtcodpercli IN VARCHAR2, p_strTxtlogin IN VARCHAR2, p_bac_telefonoenvio OUT bac_telefonoenvio, p_resultado OUT VARCHAR2);
PROCEDURE bac_telefonolocal_login_pr (p_strTxtcodpercli IN VARCHAR2, p_strTxtlogin IN VARCHAR2, p_bac_telefonolocal OUT bac_telefonolocal, p_resultado OUT VARCHAR2);
PROCEDURE accionFiltroFC_pr (p_num_contrato IN VARCHAR2,p_acciones    OUT acciones,p_resultado   OUT VARCHAR2);
PROCEDURE bac_mail_ejecutivos (p_cartera IN VARCHAR2,cs_mails OUT SYS_REFCURSOR,p_resultado   OUT VARCHAR2);
PROCEDURE bac_carterasUsuario_pr (p_login IN VARCHAR2, cs_carteras OUT SYS_REFCURSOR,p_resultado OUT VARCHAR2);
PROCEDURE bac_validarContratos_pr (p_num_cartera IN VARCHAR2,p_tipo_contrato IN VARCHAR2,c_datos  OUT SYS_REFCURSOR,p_resultado OUT VARCHAR2);



PROCEDURE bac_consultarlogsms_pr (
                                      p_strContrato             IN VARCHAR2,
                                      p_strUsuario               IN VARCHAR2,
                                      p_strTelefono             IN VARCHAR2,
                                      p_strTxtDesde             IN VARCHAR2,
                                      p_strTxtHasta             IN VARCHAR2,
                                      p_strTipoStatus          IN VARCHAR2,
                                      p_strTipoMotivo          IN VARCHAR2,
                                      p_data  OUT SYS_REFCURSOR,
                                      p_resultado OUT VARCHAR2,
                                      p_salida OUT VARCHAR2);
                                      
 PROCEDURE bac_paisesNoPermitidos_pr (
    p_strCodPais            IN VARCHAR2,
    p_strNomPais            IN VARCHAR2,
    p_strPaises             IN VARCHAR2,
    p_data           OUT SYS_REFCURSOR,
    p_resultado      OUT VARCHAR2,
    p_salida         OUT VARCHAR2); 
    
  PROCEDURE bac_cambiaPaisNoPermitidos_pr(
                            p_strCodPais   IN VARCHAR2,
                            p_strNomPais   IN VARCHAR2,
                            p_strOperacion IN VARCHAR2 ,
                            p_resultado      OUT VARCHAR2);    
    
  /*
    TAG:          BAC_PRC_LISTAR_CATEGORIAS_PADRES_OPCIONES
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
  );
  /* FIN BAC_PRC_LISTAR_CATEGORIAS_PADRES_OPCIONES */

  /*
    TAG:          BAC_PRC_LISTAR_CATEGORIAS_OPCIONES
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
  );
  /* FIN BAC_PRC_LISTAR_CATEGORIAS_PADRES_OPCIONES */

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
  );
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
  );
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
  );
   PROCEDURE bac_roles_fc_pr (
      p_bac_roles             OUT SYS_REFCURSOR,
      p_resultado             OUT VARCHAR2);
  /* FIN BAC_PRC_ACTUALIZAR_OPC_ESP */

 PROCEDURE BAC_PRC_CATOPC_PADRES_ROLES (
      I_CODIGO_GRUPO IN VARCHAR2,
      O_LISTA_CATEGORIAS_PADRES OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  );
  PROCEDURE BAC_PRC_LISTAR_CATOPC_ROLES (
      I_CODIGO_GRUPO IN VARCHAR2,
      I_CODIGO_OPCION_PADRE IN VARCHAR2,
      O_LISTA_CATEGORIAS OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  );
  PROCEDURE BAC_PRC_ACTUALIZAR_CATOPC_ROL (
      I_CODIGO_ROL IN VARCHAR2,
      I_CODIGO_OPCION IN VARCHAR2,
      I_TIPO_ACCION IN VARCHAR2,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2,
      I_USUARIO IN VARCHAR2
  );
       
PROCEDURE BAC_PRC_CATOPC_PADRES (
      O_LISTA_CATEGORIAS_PADRES OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  );
  
PROCEDURE BAC_PRC_CATOPC_HIJOS (
      I_CODIGO_OPCION_PADRE IN VARCHAR2,
      O_LISTA_CATEGORIAS OUT C_CATOPC,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  );
   PROCEDURE BAC_PRC_ACTUALIZAR_OPCACC (
      I_LOGIN IN VARCHAR2,
      I_CODIGO_OPCION IN VARCHAR2,
      I_TIPO_ACCION IN VARCHAR2,
      O_RESP_CODIGO OUT VARCHAR2,
      O_RESP_MENSAJE OUT VARCHAR2
  );
   PROCEDURE ACTUALIZAR_USUARIOS_CUSTOM_FC (
      I_USUARIO IN VARCHAR2
  );
  
   PROCEDURE BAC_CREAR_AVISO (p_tipo_aviso       IN     VARCHAR2,
                                 p_titulo        IN     VARCHAR2,
                                 p_estatus       IN     VARCHAR2,
                                 p_fecha_inicio  IN     VARCHAR2,
                                 p_fecha_fin     IN     VARCHAR2,
                                 p_texto_ing     IN     VARCHAR2,
                                 p_texto_esp     IN     VARCHAR2,
                                 p_imagen        IN     BLOB,
                                 p_usuario       IN     VARCHAR2,
                                 p_resultado     OUT VARCHAR2);
                                 
  
 
 
 
 PROCEDURE PR_VALIDAR_FECHA_AVISOS ( I_TIPO_AVISO    IN     VARCHAR2,
                                     I_STATUS_AVISO  IN     VARCHAR2, 
                                     I_FEC_INICIAL   IN     VARCHAR2,
                                     I_FEC_FINAL     IN     VARCHAR2,
                                     I_OPERACION     IN     VARCHAR2,
                                     I_CODIGO        IN     VARCHAR2,
                                     O_MENSAJE          OUT VARCHAR2);                      

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
                                 p_resultado     OUT VARCHAR2);  
                                 
PROCEDURE BAC_CONSULTAR_CTA_NOPERMITIDA(p_strCmbTipoCodBanco IN VARCHAR2,
                                p_strNumeroCuenta IN VARCHAR2,
                                bac_cuentanopermit OUT  SYS_REFCURSOR, 
                                p_resultado OUT VARCHAR2,
                                p_salida OUT VARCHAR2,
                                p_sqlstring OUT VARCHAR2); 
                                    
PROCEDURE BAC_INGRESAR_CTA_NO_PERMITIDA(p_strCmbCodBanco IN VARCHAR2,  
                                        p_strCmbNroCuentaBanco IN VARCHAR2,
                                        p_strCmbTipoCodBanco IN VARCHAR2,
                                        p_resultado OUT VARCHAR2);        
                                        
PROCEDURE BAC_MODIFICAR_CTANOPERMITIDA (p_strCodBanco IN VARCHAR2,
                                            p_strNroCuenta IN VARCHAR2,
                                            p_resultado OUT VARCHAR2);                                        
                                                                                                                                                                 
end backoffice;
/


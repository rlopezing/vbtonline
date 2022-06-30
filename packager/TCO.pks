CREATE OR REPLACE PACKAGE VBTONLINE_TRANS.TCO
AS
   PROCEDURE VBT_PR_GET_ESTATUS (IN_LOGIN        IN     VARCHAR2,
                                     IN_SERIAL       IN     VARCHAR2,
                                     IN_USUARIO      IN     VARCHAR2,
                                     IN_IP           IN     VARCHAR2,
                                     IN_PROCESO      IN     VARCHAR2,
                                     OUT_MENSAJE        OUT VARCHAR2,
                                     OUT_RESULTADO      OUT VARCHAR2);
                                     
   PROCEDURE VBT_PR_CONSULTAS (IN_SERIAL            IN OUT VARCHAR2,
                                   IN_LOGIN             IN     VARCHAR2,
                                   IN_OPERACION         IN     VARCHAR2,
                                   IN_SERIAL_pantalla      OUT VARCHAR2,
                                   IN_STATUS                OUT VARCHAR2,
                                   p_vbt_tarjeta           OUT SYS_REFCURSOR,
                                   p_resultado             OUT VARCHAR2);

   PROCEDURE VBT_PR_GENERACION (IN_SERIAL        IN OUT VARCHAR2,
                                    IN_LOGIN         IN     VARCHAR2,
                                    IN_CODCAR        IN     VARCHAR2,
                                    IN_VENCIMIENTO   IN     VARCHAR2,
                                    IN_USUARIO       IN     VARCHAR2,
                                    IN_IP            IN     VARCHAR2,
                                    OUT_RESULTADO       OUT VARCHAR2);

   PROCEDURE VBT_PR_ACTIVACION (IN_SERIAL        IN OUT VARCHAR2,
                                    IN_LOGIN         IN     VARCHAR2,
                                    IN_CODCAR        IN     VARCHAR2,
                                  
                                    IN_USUARIO       IN     VARCHAR2,
                                    IN_IP            IN     VARCHAR2,
                                    OUT_RESULTADO       OUT VARCHAR2);

   PROCEDURE VBT_PR_SELECCION (IN_LOGIN            IN     VARCHAR2,
                                   IN_IP               IN     VARCHAR2,
                                   IN_SERIAL              OUT VARCHAR2,
                                    P_VBT_FILAS          OUT SYS_REFCURSOR,
                                    P_VBT_COLUMNAS       OUT SYS_REFCURSOR,
                                   P_VBT_COORDENADAS      OUT SYS_REFCURSOR,
                                   OUT_RESULTADO          OUT VARCHAR2,
                                   OUT_MENSAJE            OUT VARCHAR2);

   PROCEDURE VBT_PR_VALIDACION (IN_LOGIN        IN     VARCHAR2,
                                    IN_IP           IN     VARCHAR2,
                                    IN_FILA         IN     VARCHAR2,
                                    IN_COLUMNA      IN     VARCHAR2,
                                    IN_VALOR        IN     VARCHAR2,
                                    OUT_RESULTADO    OUT VARCHAR2,
                                    OUT_MENSAJE      OUT VARCHAR2);
                                    
  FUNCTION FNC_GET_ESTATUS (IN_LOGIN VARCHAR2)
      RETURN VARCHAR2;
                                          
END TCO;
/



package ve.com.vbtonline.servicio.io;

import ve.com.vbtonline.servicio.util.MailManager365;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.SimpleCrypt;
import ve.com.vbtonline.servicio.util.TransformTable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class DesingBankIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(DesingBankIo.class);
    private LoggerIo loggerIo;
    /** Constructor de la clase
     */
    public DesingBankIo() {
    }


    public String cambiarClave (String clave, String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "DesingBankIo.cambiarClave";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();

              connection=getConnection();

              statement  = connection.prepareCall ("begin LOGIN.login_updatepass_pr(?,?,?); end;");

            if(clave==null)
                statement.setNull(1,OracleTypes.NULL);
            else{
                codigo = simpleCrypt.doCrypt(clave.toUpperCase());
                codigo = codigo.replaceAll("\r\n",""); //elimina los ultimos caracteres especiales
                statement.setString(1,codigo.trim());
            }


            if(login==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,login);

            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String verificarClave (String clave, String login) throws Exception {
        final String origen = "DesingBankIo.verificarClave";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();

              connection=getConnection();

              statement  = connection.prepareCall ("begin LOGIN.buscarPassword_pr(?,?,?); end;");
//            buscarPassword_pr (P_LOGIN  IN VARCHAR2,
//                    P_PASSJ OUT VARCHAR2,
//                    P_RESPUESTA OUT VARCHAR2)

            if(login==null)
                statement.setNull(1,OracleTypes.NULL);
            else{

                statement.setString(1,login);
            }



            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            String pass = statement.getString(2);
            clave = clave.toUpperCase();
            codigo = simpleCrypt.doCrypt(clave);
            codigo = codigo.replaceAll("\r\n","");
            codigo = codigo.trim();
            if(respuesta.equalsIgnoreCase("OK")){
                if(codigo.equalsIgnoreCase(pass)){
                   respuesta = "OK";
                }else{
                    respuesta ="NO OK";
                }
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public ParametrosPersonalesOd cargarParametrosPersonales (List<String> parametros) throws Exception {
        final String origen = "DesingBankIo.cargarParametrosPersonales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonales=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_movimi_codpercli_pr(?,?,?,?,?,?); end;");
//            bt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
//                    P_NUM_CONTRATO  IN VARCHAR2,
//                    P_tipo_persona IN VARCHAR2,
//                    p_datosPorDefecto OUT vbt_datos_diseneBanco,
//                    p_resultado OUT VARCHAR2)


////
            if(parametros.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if(parametros.get(1)==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);
            String sql = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);

           String monto = new String();
            while (result.next()){
                parametrosPersonales.setCodpercli(parametros.get(0));
                parametrosPersonales.setNum_contrato(parametros.get(1));
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmaxtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))),2,","));
                parametrosPersonales.setVbt_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
                parametrosPersonales.setEx_nmtd(result.getInt(5));
                parametrosPersonales.setEx_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
                parametrosPersonales.setEx_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
                parametrosPersonales.setEx_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(8))),2,","));
                parametrosPersonales.setAccount_num(NullFormatter.formatHtmlBlank(result.getString(9)));
                parametrosPersonales.setMinimun_balance(NullFormatter.formatHtmlBlank(result.getString(10)));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public ParametrosPersonalesOd cargarParametrosPersonalesBase (String tipo) throws Exception {
        final String origen = "DesingBankIo.cargarParametrosPersonalesBase";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonales=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_movimi_pordefecto_pr(?,?,?); end;");
//            bt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
//                    P_NUM_CONTRATO  IN VARCHAR2,
//                    P_tipo_persona IN VARCHAR2,
//                    p_datosPorDefecto OUT vbt_datos_diseneBanco,
//                    p_resultado OUT VARCHAR2)


////
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            if(tipo==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,tipo);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

           String monto = new String();
            while (result.next()){
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmaxtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))),2,","));
                parametrosPersonales.setVbt_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
                parametrosPersonales.setEx_nmtd(result.getInt(5));
                parametrosPersonales.setEx_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
                parametrosPersonales.setEx_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
                parametrosPersonales.setEx_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(8))),2,","));
                parametrosPersonales.setAccount_num(NullFormatter.formatHtmlBlank(result.getString(9)));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public String guardarParametrosPersonales (ParametrosPersonalesOd parametrosPersonales) throws Exception {
        final String origen = "DesingBankIo.guardarParametrosPersonales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonalesOd=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_movimi_ope_codper_pr(?,?,?,?,?,?,?,?,?,?,?,?); end;");
//           vbt_movimi_ope_codper_pr (p_codpercli   IN VARCHAR2,
//            P_NUM_CONTRATO  IN VARCHAR2,
//                    P_VBT_NMTD       IN NUMBER,
//            P_VBT_MMTD       IN NUMBER,
//                    P_VBT_MMINTO     IN NUMBER,
//            P_VBT_MMTO       IN NUMBER,
//                    P_EX_NMTD        IN NUMBER,
//            P_EX_MMTD        IN NUMBER,
//                    P_EX_MMINTO      IN NUMBER,
//            P_EX_MMTO        IN NUMBER,
//                    p_resultado OUT VARCHAR2)
////
            if(parametrosPersonales.getCodpercli()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,parametrosPersonales.getCodpercli());

            if(parametrosPersonales.getNum_contrato()==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,parametrosPersonales.getNum_contrato());

            if(parametrosPersonales.getVbt_nmtd()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setInt(3,parametrosPersonales.getVbt_nmtd());

            if(parametrosPersonales.getVbt_mmaxtd()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setDouble(4,Double.parseDouble(parametrosPersonales.getVbt_mmaxtd()));

            if(parametrosPersonales.getVbt_mminto()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setDouble(5,Double.parseDouble(parametrosPersonales.getVbt_mminto()));

            if(parametrosPersonales.getVbt_mmto()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setDouble(6,Double.parseDouble(parametrosPersonales.getVbt_mmto()));

            if(parametrosPersonales.getEx_nmtd()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setInt(7,parametrosPersonales.getEx_nmtd());

            if(parametrosPersonales.getEx_mmtd()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setDouble(8,Double.parseDouble(parametrosPersonales.getEx_mmtd()));

            if(parametrosPersonales.getEx_mminto()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setDouble(9,Double.parseDouble(parametrosPersonales.getEx_mminto()));

            if(parametrosPersonales.getEx_mmto()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setDouble(10,Double.parseDouble(parametrosPersonales.getEx_mmto()));

            if(parametrosPersonales.getAccount_num()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11,parametrosPersonales.getAccount_num());


            statement.registerOutParameter(12, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(12);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }
    /*
    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "DesingBankIo.GuardarLog";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;

        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin vbtonline.vbt_logAction_pr(?,?,?,?,?,?,?,?); end;");

            statementLog.setString(1, parametro1);
            statementLog.setString(2, parametro2);
            statementLog.setString(3, parametro3);
            statementLog.setString(4, parametro4);
            statementLog.setString(5, parametro5);
            statementLog.setString(6, parametro6);
            statementLog.setString(7, parametro7);

            statementLog.registerOutParameter(8, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(8);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }   */

    public String correoClientePrincipal (VBTUsersOd usuario,String idioma, ParametrosPersonalesOd parametrosPersonales) throws Exception {
        final String origen = "DesingBankIo.correoClientePrincipal";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_cliente_principal_pr(?,?,?); end;");



            if(usuario.getNumContrato()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,usuario.getNumContrato());

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);


             String correos="";

            if (respuesta.equalsIgnoreCase("OK")){

                if (usuario.getGrupo().equalsIgnoreCase("0000000016")){

                    result = (ResultSet) statement.getObject (2);

                    MailManager365 mailManager = new MailManager365("vbtonline");

                    while (result.next()){

                    String htmlText = "";

                    htmlText=ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_numeroMaxTransDiarias")  +  ": "+  parametrosPersonales.getVbt_mmaxtd()    +
                            "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransDiarias") + ": " + parametrosPersonales.getEx_mminto()    +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMinTransOpe") + ": " +    parametrosPersonales.getVbt_mmto()    +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransOpe")+   ": "   +   parametrosPersonales.getVbt_mmaxtd()    +
                            "\n"+
                            "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_numeroMaxTransDiarias_OB") +  ": "+   parametrosPersonales.getVbt_mmaxtd()    +
                            "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransDiarias_OB") +   ": "+    parametrosPersonales.getVbt_mmaxtd()    +
                            "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMinTransOpe_OB") +    ": "+    parametrosPersonales.getVbt_mmaxtd()    +
                            "\n\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_PP_montoMaxTransOpe_OB")+   ": "+ parametrosPersonales.getVbt_mmaxtd()    +   "\n\n\n";

                  //  mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),result.getString(5), ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMsgEmailTransferenciaAprobador"), htmlText);
                 // Etiqueta ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMsgEmailTransferenciaAprobador") no encontrada exception

                        if (correos.equalsIgnoreCase("")){
                            correos=correos+result.getString(5);
                        }else{
                            correos=correos+","+result.getString(5);
                        }
                    mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),result.getString(5),"VBT Bank & Trust Online", htmlText);


                    }

                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (correos + " motivado a cambio de parametros personales ").replaceAll(",", ", ")+" del contrato Nº "+usuario.getNumContrato());
                }



            }else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public BufferedImage cargarImagenHome ()  throws Exception {
        final String origen = "DesingBankIo.cargarImagenHome";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        Blob resultado=null;
        BufferedImage image=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            String strFormat="dd/MM/yyyy";
            DateFormat myDateFormat = new SimpleDateFormat(strFormat);
            Date myDate = new Date();

            Calendar fecha = new GregorianCalendar();
            String fechaString=new String();

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_imagen_home(?,?,?); end;");

            if(fechaString==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,fechaString);


            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(2);


            String correos="";

            if (respuesta.equalsIgnoreCase("OK")){

                result = (ResultSet) statement.getObject (3);



                while (result.next()){

                    resultado = result.getBlob("imagen");

                    int blobLength = (int) result.getBlob("imagen").length();
                    byte[] blobAsBytes = result.getBlob("imagen").getBytes(1, blobLength);

                     image = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                }




                }else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (image);
    }

    public BufferedImage cargarImagenID (String id)  throws Exception {
        final String origen = "DesingBankIo.cargarImagenID";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        Blob resultado=null;
        BufferedImage image=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            String strFormat="dd/MM/yyyy";
            DateFormat myDateFormat = new SimpleDateFormat(strFormat);
            Date myDate = new Date();

            Calendar fecha = new GregorianCalendar();
            String fechaString=new String();

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_imagen_id(?,?,?); end;");

            if(id==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,id);


            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(2);


            String correos="";

            if (respuesta.equalsIgnoreCase("OK")){

                result = (ResultSet) statement.getObject (3);



                while (result.next()){

                    resultado = result.getBlob("imagen");

                    int blobLength = (int) result.getBlob("imagen").length();
                    byte[] blobAsBytes = result.getBlob("imagen").getBytes(1, blobLength);

                    image = ImageIO.read(new ByteArrayInputStream(blobAsBytes));

                }




            }else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (image);
    }

    public String GuardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception {
        final String origen = "DesingBankIo.GuardarLogFC";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;

        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin vbtonline.vbt_logActionFC_pr(?,?,?,?,?,?,?,?,?); end;");

            statementLog.setString(1, parametro1);
            statementLog.setString(2, parametro2);
            statementLog.setString(3, parametro3);
            statementLog.setString(4, parametro4);
            statementLog.setString(5, parametro5);
            statementLog.setString(6, parametro6);
            statementLog.setString(7, parametro7);
            statementLog.setString(8, parametro8);

            statementLog.registerOutParameter(9, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(9);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ DesingBankIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {

            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }
      /*
    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
        final String origen = "BackOfficeIo.guardarExcepcion";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;

        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin vbtonline.vbt_errores_pr(?,?,?,?,?,?,?); end;");

            statementLog.setString(1, parametro1);
            statementLog.setString(2, parametro2);
            statementLog.setString(3, parametro3);
            statementLog.setString(4, parametro4);
            statementLog.setString(5, parametro5);

            if(parametro6==null)
                statementLog.setString(6, "");
            else
                statementLog.setString(6, parametro6);

            statementLog.registerOutParameter(7, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(7);
//            if (!respuesta.equalsIgnoreCase("OK"))
//                throw (new Exception (respuesta,null));
//            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {

            logger.error(ex);
        }
        finally {
            try{
                closeJdbcObjects(connectionLog, statementLog, result);
            } catch (Exception ex) {

                logger.error(ex);
                }
        }

        return (respuesta);
    }

    */
    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }



}

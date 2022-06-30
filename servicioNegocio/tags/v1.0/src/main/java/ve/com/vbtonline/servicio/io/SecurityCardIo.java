package ve.com.vbtonline.servicio.io;

import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.ContentSelectOd;
import ve.com.vbtonline.servicio.od.SecurityCardOd;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 11/10/13
 * Time: 04:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityCardIo extends comundao implements Serializable {

    private static final Logger logger = Logger.getLogger(SecurityCardIo.class);

    /** Constructor de la clase
     */
    public SecurityCardIo() {
    }

    public SecurityCardOd generarTarjeta (SecurityCardOd tarjeta, String usuario, String ip) throws Exception {

        final String origen = "SecurityCardIo.generarTarjeta";

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0, time2 = 0, time3 = 0;

        String respuesta = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin TCO.VBT_PR_GENERACION(?,?,?,?,?,?,?); end;");
            statement.setString(1, "");             //IN_OUT SERIAL      IN VARCHAR2,
            statement.setString(2, usuario);        //LOGIN   IN VARCHAR2,
            statement.setString(3, "");             //CODCAR      IN VARCHAR2,
            statement.setString(4, "");  //VENCIMIENTO IN VARCHAR2,
            statement.setString(5, usuario);        //USUARIO     IN VARCHAR2,
            statement.setString(6, ip);             //IP          IN VARCHAR2,
            statement.setString(7, "");             //IN_OUT_RESULTADO
            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.execute ();

            String serial = statement.getString(1);
            String resultado = statement.getString(7);

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            statement  = connection.prepareCall ("begin TCO.VBT_PR_CONSULTAS(?,?,?,?,?,?,?); end;");

            statement.setString(1, serial);
            statement.setString(2, "");
            statement.setString(3, "CM");
            statement.setString(4, "");
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();


            respuesta = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("0"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen+" | 1 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);

            while (result.next()){

                tarjeta.setUsuarioInc(result.getString(1));
                tarjeta.setFechaInc(result.getString(2));
                tarjeta.setIpInc(result.getString(3));
                tarjeta.setVencimiento(result.getString(4));

            }

            statement  = connection.prepareCall ("begin TCO.VBT_PR_CONSULTAS(?,?,?,?,?,?,?); end;");
            tarjeta.setSerial(serial);
            statement.setString(1, serial);
            statement.setString(2, usuario);
            statement.setString(3, "CD");
            statement.setString(4, "");
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("0"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen+" | 2 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);

            while (result.next()){

                tarjeta.addFila(result.getString(1));
                tarjeta.addColumna(result.getString(2));
                tarjeta.addValor(result.getString(1), result.getString(2), result.getString(3));
            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityCardIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tarjeta);
    }

    public String activarTarjeta (SecurityCardOd tarjeta, String usuario, String ip) throws Exception {

        final String origen = "SecurityCardIo.activarTarjeta";

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0, time2 = 0, time3 = 0;

        String resultado ;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin TCO.VBT_PR_ACTIVACION(?,?,?,?,?,?); end;");
            statement.setString(1, tarjeta.getSerial());    //IN_OUT SERIAL      IN VARCHAR2,
            statement.setString(2, usuario);                //CODPERCLI   IN VARCHAR2,
            statement.setString(3, "");                     //CODCAR      IN VARCHAR2,
            statement.setString(4, usuario);                //USUARIO     IN VARCHAR2,
            statement.setString(5, ip);                     //IP          IN VARCHAR2,
            statement.setString(6, "");                     //IN_OUT_RESULTADO
            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.execute ();

            String serial = statement.getString(1);
            resultado = statement.getString(6);

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityCardIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (resultado);
    }

    public SecurityCardOd consultarTarjetaAsignada(String usuario) throws Exception {

        final String origen = "SecurityCardIo.consultarTarjetaAsignada";

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0, time2 = 0, time3 = 0;

        String respuesta = null;
        SecurityCardOd tarjeta = new SecurityCardOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin TCO.VBT_PR_CONSULTAS(?,?,?,?,?,?,?); end;");

            statement.setString(1, "");
            statement.setString(2, usuario);
            statement.setString(3, "C");
            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);

           /* if (!respuesta.equalsIgnoreCase("0"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
             */


                tarjeta.setSerialPantalla(statement.getString(4));
                tarjeta.setSerial(statement.getString(1));
                tarjeta.setStatus(statement.getString(5));

                tarjeta.setResultado(respuesta);
            logger.info( "serial >>"+statement.getString(1)+ " . respuesta: "+respuesta+ " . status: "+statement.getString(5));

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return tarjeta;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {

        final String origen = "SecurityCardIo.GuardarLog";
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
            if (!respuesta.equalsIgnoreCase("0"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityCardIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {

            //logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }

}
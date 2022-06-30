
package ve.com.vbtonline.servicio.io;

import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.ClientInstructionOd;
import ve.com.vbtonline.servicio.od.ContentSelectOd;
import ve.com.vbtonline.servicio.od.SelectOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 20/09/13
 * Time: 02:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientInstructionIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(ClientInstructionIo.class);
    private LoggerIo loggerIo;
    /** Constructor de la clase
     */
    public ClientInstructionIo() {
    }

    public List<ContentSelectOd> consultarListaClientes (String numContrato, VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionIo.consultarListaClientes";

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0, time2 = 0, time3 = 0;

        List<ContentSelectOd> clientes= new ArrayList<ContentSelectOd>();
        ContentSelectOd cliente = new ContentSelectOd();

        StringBuilder label = new StringBuilder();
        StringBuilder valor = new StringBuilder();
        String codpercli =  new String();
        String precirif = new String();
        String cirif = new String();
        String nombreUsu = new String();

        String respuesta = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_CONSULTAR_CLIENTES_PR(?,?,?); end;");

            if(numContrato==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, numContrato);
            }
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor.setLength(0);
                codpercli = result.getString(2);
                precirif = result.getString(3);
                cirif = result.getString(4);
                nombreUsu   = result.getString(5);

                valor.append( codpercli);
                valor.append( "#");
                valor.append( precirif);
                valor.append("-");
                valor.append( cirif);
                valor.append( "#");
                valor.append( nombreUsu);

                cliente.setValue(valor.toString());
                cliente.setLabel(nombreUsu);
                clientes.add(cliente);
                cliente=new ContentSelectOd();
            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (clientes);
    }

    public List<ContentSelectOd> consultarListaTipoTransf(VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionIo.consultarListaTipoTransf";

        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;

        long time = 0, time2 = 0, time3 = 0;

        List<ContentSelectOd> lista= new ArrayList<ContentSelectOd>();
        ContentSelectOd codigoInterno = new ContentSelectOd();

        String label = null;
        String valor = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_CODIGOS_INTERNOS_PR(?,?,?); end;");
            statement.setString(1, "TIPO_TRAN");
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = result.getString(1);
                label = result.getString(2);
                codigoInterno.setValue(valor);
                codigoInterno.setLabel(label);
                lista.add(codigoInterno);
                codigoInterno=new ContentSelectOd();
            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (lista);
    }

    public List<ContentSelectOd> consultarListaMaxCartas(VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionIo.consultarListaMaxCartas";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;

        long time = 0, time2 = 0, time3 = 0;

        List<ContentSelectOd> lista= new ArrayList<ContentSelectOd>();
        ContentSelectOd codigoInterno = new ContentSelectOd();

        String label;
        String valor;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_CODIGOS_INTERNOS_PR(?,?,?); end;");
            statement.setString(1, "MAX_CARTAS");
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            int max = 0;
            if (result.next()){

                max = Integer.parseInt(result.getString(2));
               for (int i =1 ; i<max; i++){

                codigoInterno.setValue(String.valueOf(i));
                codigoInterno.setLabel(String.valueOf(i));
                lista.add(codigoInterno);
                codigoInterno=new ContentSelectOd();
               }
            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (lista);
    }

    public List<ContentSelectOd> consultarListaVencimientos(VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionIo.consultarListaVencimientos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;

        long time = 0, time2 = 0, time3 = 0;

        List<ContentSelectOd> lista= new ArrayList<ContentSelectOd>();
        ContentSelectOd codigoInterno = new ContentSelectOd();

        String label;
        String valor;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_CODIGOS_INTERNOS_PR(?,?,?); end;");
            statement.setString(1, "CADUCIDAD");
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = result.getString(1);
                label = result.getString(1);
                codigoInterno.setValue(valor);
                codigoInterno.setLabel(label);
                lista.add(codigoInterno);
                codigoInterno=new ContentSelectOd();
            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (lista);
    }

    public List<String> insertarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, VBTUsersOd usuarioOd) throws Exception {

        final String origen = "ClientInstructionIo.insertarNumeroControl";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        //String cartera = Carteras.toString();
        long time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> clientes= new ArrayList<ContentSelectOd>();
        ContentSelectOd cliente = new ContentSelectOd();

        List<String> numeros = new ArrayList<String>();
        StringBuilder label;
        StringBuilder valor;
        String respuesta=null;
        Random random = new Random();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            //Creacion del numero de control (Pdor los momentos no se encuentra encriptao
            Calendar calendario = Calendar.getInstance();
            int dia, mes, ano, minutos, segundos, mili;
            dia = calendario.get(Calendar.DAY_OF_MONTH);
            mes = calendario.get(Calendar.MONTH);
            ano = calendario.get(Calendar.YEAR);
            minutos = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

            connection=getConnection();

            int numCartas = Integer.parseInt(carta.getMaxcartas());
            String numero = null;

            boolean buscarOtro = false;
            connection.setAutoCommit(false);

            for (int i = 0; i < numCartas; i++) {

               statement  = connection.prepareCall ("begin PR_NUMERO_CONTROL(?,?,?,?,?,?,?,?,?,?); end;");

               do{
                   numero = String.valueOf(random.nextInt(9)) + String.valueOf(random.nextInt(9))
                           + String.valueOf(ano)
                           + String.valueOf(random.nextInt(9)) + String.valueOf(random.nextInt(9))
                           + String.valueOf(mes)
                           + String.valueOf(random.nextInt(9)) + String.valueOf(random.nextInt(9))
                           + String.valueOf(dia) + String.valueOf(random.nextInt(9));

                    statement.setString(1, carta.getCodperclicarta());
                    statement.setString(2, "0");
                    statement.setString(3, numero);
                    statement.setString(4, sessionId);
                    statement.setString(5, carta.getVencimiento());
                    statement.setString(6, "O");
                    statement.setString(7, usuario);
                    statement.setString(8, ip);
                    statement.setString(9, "I");
                    statement.setString(10, "");

                    statement.registerOutParameter(10, OracleTypes.VARCHAR);

                    statement.execute ();
                    respuesta = statement.getString(10);

                    if(respuesta!=null && respuesta.equals("1")){

                        buscarOtro=true;

                    }  else{
                        buscarOtro=false;
                    }

                } while(buscarOtro);

                numeros.add(numero);
            }

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa ");

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }
        catch (Exception ex) {
            try {
                connection.rollback();
            } catch (SQLException h) {
            }
            logger.error(ex);
            loggerIo.guardarExcepcion(usuarioOd.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioOd.getLogin(), usuarioOd.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            connection.commit();
            connection.setAutoCommit(true);
            closeJdbcObjects (connection, statement, result);
        }

        return numeros;
    }

    public String evaluarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, String numeroControl, VBTUsersOd usuarioOd) throws Exception {

        final String origen = "ClientInstructionIo.evaluarNumeroControl";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        //String cartera = Carteras.toString();
        long time = 0, time2 = 0, time3 = 0;

        List<ContentSelectOd> clientes= new ArrayList<ContentSelectOd>();
        ContentSelectOd cliente = new ContentSelectOd();
        List<String> resp = new ArrayList<String>();
        List<String> numeros = new ArrayList<String>();
        StringBuilder label;
        StringBuilder valor;
        String respuesta=null;
        Random random = new Random();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            Calendar calendario = Calendar.getInstance();
            int dia, mes, ano, minutos, segundos, mili;
            dia      = calendario.get(Calendar.DAY_OF_MONTH);
            mes      = calendario.get(Calendar.MONTH);
            ano      = calendario.get(Calendar.YEAR);
            minutos  = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

            connection = getConnection();


            connection.setAutoCommit(false);

            statement = connection.prepareCall ("begin PR_NUMERO_CONTROL(?,?,?,?,?,?,?,?,?,?); end;");



            statement.setString(1, carta.getCodperclicarta());
            statement.setString(2, "0");
            statement.setString(3, numeroControl);
            statement.setString(4, sessionId);
            statement.setString(5, carta.getVencimiento());
            statement.setString(6, "O");
            statement.setString(7, usuario);
            statement.setString(8, ip);
            statement.setString(9, "E");
            statement.setString(10, "");

            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(10);

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa ");

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }

        catch (Exception ex) {

            try {

                connection.rollback();

            } catch (SQLException h) {
            }
            logger.error(ex);
            loggerIo.guardarExcepcion(usuarioOd.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioOd.getLogin(), usuarioOd.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }

        finally {
            connection.commit();
            connection.setAutoCommit(true);
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }

    public String anularNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, String numeroControl, String login, VBTUsersOd usuarioOd) throws Exception {

        final String origen = "ClientInstructionIo.anularNumeroControl";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        //String cartera = Carteras.toString();
        long time = 0, time2 = 0, time3 = 0;

        List<ContentSelectOd> clientes= new ArrayList<ContentSelectOd>();
        ContentSelectOd cliente = new ContentSelectOd();
        List<String> resp = new ArrayList<String>();
        List<String> numeros = new ArrayList<String>();
        StringBuilder label;
        StringBuilder valor;
        String respuesta=null;
        Random random = new Random();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            Calendar calendario = Calendar.getInstance();
            int dia, mes, ano, minutos, segundos, mili;
            dia      = calendario.get(Calendar.DAY_OF_MONTH);
            mes      = calendario.get(Calendar.MONTH);
            ano      = calendario.get(Calendar.YEAR);
            minutos  = calendario.get(Calendar.MINUTE);
            segundos = calendario.get(Calendar.SECOND);

            connection = getConnection();


            connection.setAutoCommit(false);

            statement = connection.prepareCall ("begin PR_NUMERO_CONTROL(?,?,?,?,?,?,?,?,?,?); end;");



            statement.setString(1, carta.getCodperclicarta());
            statement.setString(2, "0");
            statement.setString(3, numeroControl);
            statement.setString(4, sessionId);
            statement.setString(5, carta.getVencimiento());
            statement.setString(6, "O");
            statement.setString(7, usuario);
            statement.setString(8, ip);
            statement.setString(9, "AN");
            statement.setString(10, "");

            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(10);
            loggerIo.GuardarLog(login,"5","1","11","",ip,"El usuario: "+login+" anuló una Carta de Instrucción");

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa ");

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);
        }

        catch (Exception ex) {

            try {

                connection.rollback();

            } catch (SQLException h) {
            }
            logger.error(ex);
            loggerIo.guardarExcepcion(usuarioOd.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioOd.getLogin(), usuarioOd.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }

        finally {
            connection.commit();
            connection.setAutoCommit(true);
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }

    public SelectOd cargarMotivos (VBTUsersOd usuario,String Idioma) throws Exception {
        final String origen = "ClientInstructionIo.Motivos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> listmotivos= new ArrayList<ContentSelectOd>();
        ContentSelectOd motivo = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_motivos_pr(?,?,?); end;");

            statement.setString(1, Idioma);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            while (result.next()){
                motivo=new ContentSelectOd();
                motivo.setValue(result.getString(1));
                motivo.setLabel(result.getString(2));
                listmotivos.add(motivo);

            }

            select.setContenido(listmotivos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ ClientInstructionIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public boolean eliminarNumeroControl(List<String> numeros, VBTUsersOd usuario) throws Exception{
        return true;
    }
   /*
    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "ClientInstructionIo.GuardarLog";
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ ClientInstructionIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }

    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
        final String origen = "ClientInstructionIo.guardarExcepcion";
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
            }catch (Exception ex) {

                logger.error(ex);
            }
        }

        return (respuesta);
    } */

    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }
}

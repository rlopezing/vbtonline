package ve.com.vbtonline.servicio.io;


import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.SimpleCrypt;

import java.io.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 20/05/2010
 * Time: 02:59:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(LoginIo.class);

    /** Constructor de la clase
     */
    public LoginIo () {
    }

//    public static void main(String[] args) {
//        File f_es = new File( "C:\\Desarrollo\\proyectos\\proyectonew\\comun\\trunk\\src\\main\\resources\\vbtonline_ve_es.properties" );
//        File f_en = new File( "C:\\Desarrollo\\proyectos\\proyectonew\\comun\\trunk\\src\\main\\resources\\vbtonline_us_en.properties" );
//        BufferedReader entrada;
//        ArrayList<String> valor_es=new ArrayList<String>();
//        ArrayList<String> valor_en=new ArrayList<String>();
//        try {
//            entrada = new BufferedReader( new FileReader( f_es ) );
//            while(entrada.ready()){
//                valor_es.add(entrada.readLine().trim());
//            }
//
//            entrada = new BufferedReader( new FileReader( f_en ) );
//            while(entrada.ready()){
//                valor_en.add(entrada.readLine().trim());
//                System.out.println(entrada.readLine().trim());
//            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public VBTUsersOd validarUsuario (VBTUsersOd u) throws Exception {
        final String origen = "loginIo.validarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        String codigo = "";
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=null;
        Integer len=0;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            clave = new String();
            statement = connection.prepareCall("begin LOGIN.BAC_INGRE_P(?,?,?); end;");
            logger.info("usuario: "+ u.getLogin());

            if(u.getLogin()==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, u.getLogin());
            }

            if(u.getPassword() == null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                codigo = simpleCrypt.doCrypt(u.getPassword().toUpperCase());
                codigo = codigo.replaceAll("\r\n", ""); //elimina los ultimos caracteres especiales
                codigo = codigo.trim();
                statement.setString(2,codigo);
            };


            //statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                u.setPassword(codigo);
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

//            String pass = statement.getString(2);
//            if(codigo.equalsIgnoreCase(pass)){
//                u.setPassword(codigo);
//            }else{
////                statement = connection.prepareCall("begin LOGIN.BAC_INGRE_P(?,?,?); end;");
////                registrar intentos fallidos
//                u = new VBTUsersOd();
//            }
//




            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


        }
        catch (Exception ex) {
            if (!ex.getMessage().equals("CLAVE INVALIDA")){
                logger.error(ex);
                //Guarda excepcion
                guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
                guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
                guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
                guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());

            }

            //throw (new Exception (ex.getMessage() +" Login=" +u.getLogin(),null));
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (u);
    }

    public VBTUsersOd validarUsuario2 (String login,  VBTUsersOd usuarioOd) throws Exception {
        final String origen = "loginIo.validarUsuario2";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        String codigo = "";
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = new VBTUsersOd();
        String respuesta=null;
        Integer len=0;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            statement = connection.prepareCall("begin LOGIN.login2_pr(?,?,?); end;");

            if(login==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, login);
            }


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK")){
               throw (new Exception (respuesta,null));
            }
            else{
               logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (2);

            while(result.next()){
                usuario.setLogin(result.getString(1));
                usuario.setIntentos_login(Integer.parseInt(result.getString(4)));
                usuario.setEmail(result.getString(5));
                usuario.setNombres(result.getString(6));

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


        }
        catch (Exception ex) {
            if (!ex.getMessage().equals("CLAVE INVALIDA")){
                logger.error(ex);
            }
            guardarExcepcion(usuarioOd.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioOd.getLogin(), usuarioOd.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (usuario);
    }

    public VBTUsersOd cargarUsuario (VBTUsersOd u) throws Exception {
        final String origen = "loginIo.cargarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        String codigo = "";
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = new VBTUsersOd();
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            clave = new String();
            statement = connection.prepareCall("begin TransferenciaHandler.validateuser_handler_pr(?,?,?,?); end;");
//
            if(u.getLogin()==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, u.getLogin());
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
//                u.setPassword(codigo);
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (2);

            usuario=new VBTUsersOd();
//
            Calendar fecha;
            DateFormat formatter ;
            Date date ;
            while(result.next()){
                usuario.setTipo(result.getString(1));
                usuario.setStatus_cuenta(result.getString(2));
                usuario.setCodpercli(result.getString(6));
                usuario.setNumContrato(result.getString(7));
                usuario.setEmail(result.getString(8));
                //usuario.setCodpercli(result.getString(9));
                usuario.setLast_login(result.getString(3));
                usuario.setLast_login_hora(result.getString(4));
                usuario.setNombres(result.getString(5));
                usuario.setCambioPass(result.getString(9));
                usuario.setPassword_especiales(result.getString(10));
                usuario.setCirif(result.getString(11));
                usuario.setAmbito(result.getString(12));
                usuario.setTelefono_celular(result.getString(13));
                usuario.setGrupo(result.getString(14));
                usuario.setCodigoPais(result.getString(15));
                usuario.setTipoContrato(result.getString(16));
                usuario.setNombreGrupo(result.getString(17));
                usuario.setValidaSoporteContrato(result.getString(18));
                usuario.setFechaAnuncio(result.getString(19));
//
//                usu.tipo,
//                        UPPER(usu.status_cuenta),
//                        TO_CHAR(usu.last_login,'dd/mm/yyyy'),
//                        TO_CHAR(usu.last_login,'hh:mi:ss am'),
//                        INITCAP(usu.nombres),
//                        usu.codpercli,
//                        uc.num_contrato,
//                        usu.email email
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (usuario);
    }

    public String cargarCarteras (String contrato, VBTUsersOd usuarioOd) throws Exception {
        final String origen = "loginIo.cargarCarteras";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        String carteras = new String();
        String codigo = "";
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = new VBTUsersOd();
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            clave = new String();
            statement = connection.prepareCall("begin TransferenciaHandler.validateuser_cartera_pr(?,?,?); end;");
//
            if(contrato==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, contrato);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{

                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (2);


            carteras=new String();
            while(result.next()){
                
                if (!NullFormatter.isBlank(carteras))
                    carteras += ",";
                carteras += "'" + NullFormatter.formatBlank(result.getString(1)) + "'";

            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + LoginIo.class + " | " + origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuarioOd.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioOd.getLogin(), usuarioOd.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (carteras);
    }


    public List<String> cargarInfoHome () throws Exception {

        final String origen = "loginIo.cargarInfoHome";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        List<String> resultado = new ArrayList<String>();
        String codigo = "";
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = new VBTUsersOd();
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            clave = new String();
            statement = connection.prepareCall("begin VBTONLINE.vbt_info_home(?,?,?); end;");
//

            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK")){
               // throw (new Exception (respuesta,null));
            }
            else{
                resultado.add(NullFormatter.formatBlank(statement.getString(1)));
                resultado.add(NullFormatter.formatBlank(statement.getString(3)));
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + LoginIo.class + " | " + origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            //throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (resultado);
    }


    public List<String> cargarAccionesSistema (VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.cargarAccionesSistema";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<String> listaAcciones = new ArrayList<String>();
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            clave = new String();
            statement = connection.prepareCall("begin BACKOFFICE.accionessistema_pr(?,?); end;");
//

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{

                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (1);


            while(result.next()){
               listaAcciones.add(result.getString(1));

            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + LoginIo.class + " | " + origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaAcciones);
    }

    public List<CalendarioOd> cargarCalendario (Integer mes, Integer ano, Integer dia, VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.cargarCalendario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String mensaje=null, clave;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<CalendarioOd> listaFeriado = new ArrayList<CalendarioOd>();
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            clave = new String();
            statement = connection.prepareCall("begin vbtonline.vbt_calendario_pr(?,?,?,?,?); end;");
//            vbt_calendario_pr (p_mes IN NUMBER,
//                    p_año IN NUMBER,
//                    p_fin_mes IN NUMBER,
//                    p_vbt_calendario OUT vbt_calendario,
//                    p_resultado OUT VARCHAR2)
//

            statement.setInt(1,mes);
            statement.setInt(2,ano);
            statement.setInt(3,dia);
            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{

                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (4);

            listaFeriado = new ArrayList<CalendarioOd>();
            CalendarioOd calendario = new CalendarioOd();
            while(result.next()){
                calendario = new CalendarioOd();
                calendario.setDia(result.getString(1)+"-"+(mes)+"-"+ano);
                calendario.setDescripcion(result.getString(2));
                listaFeriado.add(calendario);
            }

//            listaFeriado = new ArrayList<CalendarioOd>();
//            CalendarioOd calendario = new CalendarioOd();
//            for(int i=1;i<5;i++){
//                calendario = new CalendarioOd();
//                calendario.setDia(i+"/12/2012");
//                calendario.setDescripcion("DIA BANCARIO");
//                listaFeriado.add(calendario);
//            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + LoginIo.class + " | " + origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaFeriado);
    }

    public VBTUsersOd loginOpeEsp (VBTUsersOd u) throws Exception {
        final String origen = "loginIo.loginOpeEsp";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null, clave;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.OPE_ESPECIALES_P(?,?,?); end;");

            if(u.getLogin()==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, u.getLogin());
            }

            if(u.getPassword_ope() == null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                codigo = simpleCrypt.doCrypt(u.getPassword_ope().toUpperCase());
                codigo = codigo.replaceAll("\r\n",""); //elimina los ultimos caracteres especiales
                statement.setString(2,codigo.trim());
            };

            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                usuario=null;
                throw (new Exception (respuesta,null));
            }
            else{

                usuario = u;
                usuario.setPassword_ope(codigo.trim());
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (usuario);
    }

    public String logout (String login) throws Exception {
        final String origen = "loginIo.logout";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.logout_pr(?,?); end;");

            if(login==null){
                statement.setNull(1, OracleTypes.NULL);
            }else
                statement.setString(1, login);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public List<ItemOd> home (String idioma, VBTUsersOd u) throws Exception {

        final String origen = "loginIo.home";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ItemOd> items = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.login_tagusuarios_pr(?,?,?,?,?); end;");
//
            if(u.getLogin() == null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,u.getLogin());
            };
            statement.setString(2,"VBT");

            statement.setString(3,"ONLINE");

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);


            statement.execute ();
            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta+" mensaje",null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            result = (ResultSet) statement.getObject (4);
            List<MenuOd> menu= new ArrayList<MenuOd>();
            List<MenuOd> padres= new ArrayList<MenuOd>();

            MenuOd opcionMenu;
            while (result.next()){
                if(Integer.valueOf(result.getString(3))==0){
                    opcionMenu = new MenuOd();
                    opcionMenu.setCodigo(result.getString(1));
                    opcionMenu.setDescripcion(result.getString(2));
                    opcionMenu.setCodPadre(result.getString(3));
                    opcionMenu.setNivel(result.getInt(4));
                    opcionMenu.setOrden(result.getInt(5));
                    opcionMenu.setAcciones(result.getString(6));
                    opcionMenu.setCodgrp(result.getInt(7));
                    opcionMenu.setMnBackoffice(result.getString(8));
                    opcionMenu.setMnInicio(result.getString(9));
                    opcionMenu.setMnSalir(result.getString(10));
                    opcionMenu.setOpcionAccion(result.getString(11));
                    padres.add(opcionMenu);
                } else{
                    opcionMenu = new MenuOd();
                    opcionMenu.setCodigo(result.getString(1));
                    opcionMenu.setDescripcion(result.getString(2));
                    opcionMenu.setCodPadre(result.getString(3));
                    opcionMenu.setNivel(result.getInt(4));
                    opcionMenu.setOrden(result.getInt(5));
                    opcionMenu.setAcciones(result.getString(6));
                    opcionMenu.setCodgrp(result.getInt(7));
                    opcionMenu.setMnBackoffice(result.getString(8));
                    opcionMenu.setMnInicio(result.getString(9));
                    opcionMenu.setMnSalir(result.getString(10));
                    opcionMenu.setOpcionAccion(result.getString(11));
                    menu.add(opcionMenu);
                }
            }

            ItemOd itemsOd;
            SubItemsOd subItemsOd;
            List<SubItemsOd> subitems=new ArrayList<SubItemsOd>();
            items=new ArrayList<ItemOd>();
            int miPadre = 1;
            itemsOd=new ItemOd();


            for(int i=0;i<padres.size();i++){
                subitems = new ArrayList<SubItemsOd>();
                itemsOd = new ItemOd();
                itemsOd.setHtml_span(ResourceBundle.getBundle("Menu"+idioma).getString(padres.get(i).getOpcionAccion()));
                itemsOd.setHtml_spanID(padres.get(i).getOpcionAccion());
                itemsOd.setEstilo("parent");
                itemsOd.setCodope(padres.get(i).getCodigo());
                itemsOd.setId(padres.get(i).getDescripcion().replace(" ","_"));

                for(int j=0;j<menu.size();j++){
                    if(Integer.valueOf(padres.get(i).getCodigo())==Integer.valueOf(menu.get(j).getCodPadre())){
                        subItemsOd = new SubItemsOd();
                        subItemsOd.setId(menu.get(j).getDescripcion().replace(" ","_"));
                        subItemsOd.setSpan_html(ResourceBundle.getBundle("Menu"+idioma).getString(menu.get(j).getOpcionAccion()));
                        subItemsOd.setSpan_htmlID(menu.get(j).getOpcionAccion());
                        subItemsOd.setEstilo("");
                        subItemsOd.setCodope(menu.get(j).getCodigo());
                        subitems.add(subItemsOd);
                    }


                }
                if (subitems.size()!=0){
                    itemsOd.setSub_opciones(subitems);
                }else{
                   itemsOd.setId(padres.get(i).getDescripcion().replace(" ", "_"));
                   itemsOd.setEstilo("");
                }

                items.add(itemsOd);
            }





//
//            for(int i=0;i<menu.size();i++){
//
//               if(Integer.valueOf(menu.get(i).getCodPadre())==0){
//                    subitems = new ArrayList<SubItemsOd>();
//                    itemsOd = new ItemOd();
//                    itemsOd.setHtml_span(ResourceBundle.getBundle("Menu"+idioma).getString(menu.get(i).getOpcionAccion()));
//                    itemsOd.setHtml_spanID(menu.get(i).getOpcionAccion());
//                    itemsOd.setEstilo("parent");
//                    itemsOd.setCodope(menu.get(i).getCodigo());
//                   itemsOd.setId(menu.get(i).getDescripcion().replace(" ","_"));
//                    miPadre = Integer.valueOf(menu.get(i).getCodigo());
//                }else if(Integer.valueOf(menu.get(i).getCodPadre())==miPadre){
//                   // CAIMANADA PARA FIRMAS CONJUNTAS QUITAR!!
//                     if (menu.get(i).getDescripcion().equalsIgnoreCase("DIRECTORIO")){
//                         if(!u.getTipo().equalsIgnoreCase("7") && !u.getTipo().equalsIgnoreCase("8")){
//                             // CAIMANADA PARA FIRMAS CONJUNTAS QUITAR!!
//                             subItemsOd = new SubItemsOd();
//                             subItemsOd.setId(menu.get(i).getDescripcion().replace(" ","_"));
//                             subItemsOd.setSpan_html(ResourceBundle.getBundle("Menu"+idioma).getString(menu.get(i).getOpcionAccion()));
//                             subItemsOd.setSpan_htmlID(menu.get(i).getOpcionAccion());
//                             subItemsOd.setEstilo("");
//                             subItemsOd.setCodope(menu.get(i).getCodigo());
//                             subitems.add(subItemsOd);
//                         }
//                     } else{
//                            subItemsOd = new SubItemsOd();
//                            subItemsOd.setId(menu.get(i).getDescripcion().replace(" ","_"));
//                            subItemsOd.setSpan_html(ResourceBundle.getBundle("Menu"+idioma).getString(menu.get(i).getOpcionAccion()));
//                            subItemsOd.setSpan_htmlID(menu.get(i).getOpcionAccion());
//                            subItemsOd.setEstilo("");
//                            subItemsOd.setCodope(menu.get(i).getCodigo());
//                            subitems.add(subItemsOd);
////                    miPadre = menu.get(i).getCodPadre();
//                   }
//                }
//                if((i+1)<menu.size()){
//                    if(Integer.valueOf(menu.get(i+1).getCodPadre())!=miPadre){
//                        if(subitems.size()!=0)
//                            itemsOd.setSub_opciones(subitems);
//                        else{
//                            itemsOd.setId(menu.get(i).getDescripcion().replace(" ", "_"));
//                            itemsOd.setEstilo("");
//                        }
//
//
//                        if (Integer.valueOf(menu.get(i+1).getCodPadre())!=0) {
//                          i++;
//                        }else{
//                            items.add(itemsOd);
//                        }
//                        miPadre = 0;
//                    }
//                }else{
//                    if(subitems.size()!=0){
//                        itemsOd.setSub_opciones(subitems);
//                    }else{
//                        itemsOd.setId(menu.get(i).getDescripcion().replace(" ", "_"));
//                        itemsOd.setEstilo("");
//                    }
//                    items.add(itemsOd);
//                }
//            }

            itemsOd=new ItemOd();
            if(idioma.equalsIgnoreCase("_ve_es")){
                itemsOd.setHtml_span("Salir");
                itemsOd.setHtml_spanID("menu_TAGTitleSalir");
            }
            else {
                itemsOd.setHtml_span("Log Out");
                itemsOd.setHtml_spanID("menu_TAGTitleSalir");
            }

            itemsOd.setEstilo("");
            itemsOd.setId("SALIR");


            items.add(itemsOd);
        time = System.currentTimeMillis () - time;

        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


    }
    catch (Exception ex) {
        logger.error(ex);
        guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
        throw (new Exception (ex.getMessage(),null));
    }
    finally {
            closeJdbcObjects (connection, statement, result);
    }

    return (items);
}

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "loginIo.GuardarLog";
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
    }

    public String GuardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception {
        final String origen = "loginIo.GuardarLogFC";
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }

    public List<PrivilegioOd> consultarGrupoOpcPermisos (VBTUsersOd usuario) throws Exception {
        final String origen = "LoginIO.consultarGrupoOpcPermisos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long time = 0;
        PrivilegioOd permisosAcciones=new PrivilegioOd();
        List<PrivilegioOd> listaOpcionesPermisos=new ArrayList<PrivilegioOd>();
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.permisosUsuarioOpcion_pr(?,?,?,?,?); end;");

            if(usuario.getLogin()==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, usuario.getLogin());
            }

            statement.setString(2, "VBT");
            statement.setString(3, "ONLINE");

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(5);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);
            List<String> priv;

            while (result.next()){
                permisosAcciones = new PrivilegioOd();
                permisosAcciones.setOpcion(result.getString(2));
                priv=new ArrayList<String>();
                for(int y=0;y<result.getString(1).length();y++){
                    priv.add(result.getString(1).substring(y, y + 1));
                }
                permisosAcciones.setPrivilegios(priv);
                listaOpcionesPermisos.add(permisosAcciones);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return listaOpcionesPermisos;
    }

    public boolean validarTerminosCondiciones (String contrato,VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.logout";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String pl_respuesta=new String();
        boolean respuesta = false;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.VALIDARTERMINOSCONDICIONES_PR(?,?,?); end;");
            statement.setString(1, contrato);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            pl_respuesta = statement.getString(3);
            String acepto = statement.getString(2);

            if (!pl_respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (pl_respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            if(acepto!=null && acepto.equalsIgnoreCase("S")){
               respuesta = true;
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    /**
     *      FUNCIÓN:        contarSolicitudesPendientes(String, String)
     *      AUTOR:          RRANGEL
     *      FECHA:          10/10/2014
     *      DESCRIPCIÓN:    Obtiene el contrato y el tipo de usuario. Cuando el usuario es Aprobador (9) asigna el valor
     *                      'C' a la variable estatus, cuando es Liberador (8) asigna 'A' a dicha variable.
     * */
    public String contarSolicitudesPendientes(VBTUsersOd usuario, String contrato, String estatus) throws Exception {
        final String origen = "loginIo.contarSolicitudesPendientes";
        Connection connection = null;
        ResultSet result = null;
        CallableStatement statement = null;
        long timeExecute = 0, timeResult = 0;
        String cantidadSolicitudesPendientes = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            timeExecute = System.currentTimeMillis ();

            connection = getConnection();
            statement = connection.prepareCall("begin vbtonline.vbt_cant_solpend_pr(?,?,?,?); end;");

            statement.setString(1, contrato);
            statement.setString(2, estatus);

            statement.registerOutParameter(3, OracleTypes.NUMBER);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute();


            timeExecute = System.currentTimeMillis () - timeExecute;

            timeResult = System.currentTimeMillis ();

            if (statement.getString(4).equalsIgnoreCase("OK")) {

                cantidadSolicitudesPendientes = statement.getString(3);

            }

            timeResult = System.currentTimeMillis () - timeResult;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);

        } catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }

        return cantidadSolicitudesPendientes;
    }
    /** FIN contarSolicitudesPendientes(String, String) */

    public String contarPagosTdcPendientes(String carteras, VBTUsersOd usuario, String estatus) throws Exception {
        final String origen = "loginIo.contarPagosTdcPendientes";
        Connection connection = null;
        ResultSet result = null;
        CallableStatement statement = null;
        long timeExecute = 0, timeResult = 0;
        String cantidadPagosTdcPendientes = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            timeExecute = System.currentTimeMillis ();

            connection = getConnection();
            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_PAGOS_TARJETA.PR_NUM_PAGOS_X_ESTATUS(?,?,?,?); end;");

            statement.setString(1, carteras);
            statement.setString(2, estatus);

            statement.registerOutParameter(3, OracleTypes.NUMBER);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute();

            timeExecute = System.currentTimeMillis () - timeExecute;

            timeResult = System.currentTimeMillis ();

            if (statement.getString(4).equalsIgnoreCase("OK")) {

                cantidadPagosTdcPendientes = statement.getString(3);

            }

            timeResult = System.currentTimeMillis () - timeResult;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);

        } catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }

        return cantidadPagosTdcPendientes;
    }

    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
        final String origen = "loginIo.guardarExcepcion";
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
    }

    public String consultarRolesAprobarLiberar(VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.consultarRolesAprobarLiberar";
        Connection connection = null;
        ResultSet result = null;
        CallableStatement statement = null;
        long timeExecute = 0, timeResult = 0;
        String cantidadSolicitudesPendientes = "";
        String respuesta="";
        String roles="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            timeExecute = System.currentTimeMillis ();

            connection = getConnection();
            statement = connection.prepareCall("begin vbtonline.vbt_aprobar_liberar_custom_pr(?,?,?); end;");

            if(usuario.getLogin()==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, usuario.getLogin());
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
             roles=roles+result.getString(1);
            }

            if (roles.equalsIgnoreCase("")){
                roles="N";
            }

            timeResult = System.currentTimeMillis () - timeResult;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);

        } catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }

        return roles;
    }

    public String registrarCodigoActualizacion (String codpercli, String login,String codigo,String numContrato) throws Exception {
        final String origen = "loginIo.registrarCodigoActualizacion";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;
        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin login.REGISTRAR_COD_ACT_PR(?,?,?,?,?); end;");

            statementLog.setString(1, login);
            statementLog.setString(2, codpercli );
            statementLog.setString(3, codigo);
            statementLog.setString(4, numContrato );


            statementLog.registerOutParameter(5, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(5);
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
    }

    public String validarCodigoActualizacion(VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.registrarCodigoActualizacion";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;
        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin login.VALIDA_ACTUALIZACION(?,?,?); end;");

            statementLog.setString(1, usuario.getLogin());
            statementLog.setString(2, usuario.getNumContrato() );


            statementLog.registerOutParameter(3, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(3);

            if (respuesta.equalsIgnoreCase("NO OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino" +
                        "")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }

    public String upateFechaAnuncioActualizacion(VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.registrarFechaAnuncioActualizacion";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;
        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin login.update_fechaAuncio(?,?,?); end;");

            statementLog.setString(1, usuario.getLogin());
            statementLog.setString(2, usuario.getCodpercli() );


            statementLog.registerOutParameter(3, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(3);

            if (respuesta.equalsIgnoreCase("NO OK"))
                throw (new Exception (respuesta,null));
            else{
                GuardarLog(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Fecha de visualizacion del anuncio de actualizacion: " + respuesta );
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino" +
                        "")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }

    public ParametrosPersonalesOd cargarParametrosContratos (String contrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "loginIo.cargarParametrosContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonales=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametros_contratos_pr(?,?,?); end;");
//            bt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
//                    P_NUM_CONTRATO  IN VARCHAR2,
//                    P_tipo_persona IN VARCHAR2,
//                    p_datosPorDefecto OUT vbt_datos_diseneBanco,
//                    p_resultado OUT VARCHAR2)


////
            if(contrato == null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,contrato);



            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            String monto = new String();
            while (result.next()){
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmaxtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))), 2, ","));
                parametrosPersonales.setVbt_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
                parametrosPersonales.setEx_nmtd(result.getInt(5));
                parametrosPersonales.setEx_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
                parametrosPersonales.setEx_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
                parametrosPersonales.setEx_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(8))),2,","));
                parametrosPersonales.setAct_plazo(result.getString(10));

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public String consultarMotivosAct(String contrato) throws Exception {
        final String origen = "loginIo.consultarMotivosAct";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;
        String motivosAct = "";

        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin login.CONSULTAR_MOTIVOS_ACTCLI(?,?,?); end;");


            statementLog.setString(1, contrato);
            statementLog.registerOutParameter(2, OracleTypes.VARCHAR);
            statementLog.registerOutParameter(3, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(2);

            motivosAct = statementLog.getString(3);

            if (respuesta.equalsIgnoreCase("NO OK"))
                throw (new Exception (respuesta,null));
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino" +
                        "")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (motivosAct);
    }

    public String validarUsuarioCorreo(String usuario, String correo) throws Exception {
        final String origen = "loginIo.validarUsuarioCorreo";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;
        String existe = "";
        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin login.VALIDAR_USUARIO_CORREO(?,?,?,?); end;");

            statementLog.setString(1, usuario);
            statementLog.setString(2, correo);
            statementLog.registerOutParameter(3, OracleTypes.VARCHAR);
            statementLog.registerOutParameter(4, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(3);

            existe = statementLog.getString(4);

            if (respuesta.equalsIgnoreCase("NO OK"))
                throw (new Exception (respuesta, null));
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino" +
                        "")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (existe);
    }

    public String validarExcluidoAct(VBTUsersOd usuario) throws Exception {
        final String origen = "loginIo.validarExcluidoAct";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;
        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin login.VALIDA_CONTRATO_EXCLUIDO(?,?,?); end;");

            statementLog.setString(1, usuario.getLogin());
            statementLog.setString(2, usuario.getNumContrato() );


            statementLog.registerOutParameter(3, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(3);

            if (respuesta.equalsIgnoreCase("NO OK"))
                throw (new Exception (respuesta,null));
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino" ) + LoginIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
            }
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }
}





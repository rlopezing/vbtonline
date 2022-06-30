package ve.com.vbtonline.servicio.io;

import com.venezolano.util.mail.MailManager;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import com.venezolano.webutil.ClientContext;
import comsms.vbt.servicio.ServicioVbt;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.SimpleCrypt;
import ve.com.vbtonline.servicio.util.TransformTable;

import java.io.Serializable;
import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(SecurityIo.class);

    /** Constructor de la clase
     */
    public SecurityIo() {
    }


    public AccountsOd Cargar (String transaccionId, AccountsOd aod) throws Exception {
        final String origen = "AccountsIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        AccountsOd accountsOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
//            accountsOd=new AccountsOd();
//            accountsOd.setId(aod.getId());





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (accountsOd);
    }

    public String crearClaveRamdom (VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "SecurityIo.crearClaveRamdom";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String[] select = new String[63];
        java.util.Date date=new java.util.Date();
        java.lang.StringBuffer nbuf = new java.lang.StringBuffer();
        java.util.Random rnum = new java.util.Random(date.getTime());
        ClientContext context = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            select[0]="A";
            select[1]="B";
            select[2]="C";
            select[3]="D";
            select[4]="E";
            select[5]="F";
            select[6]="G";
            select[7]="H";
            select[8]="J";
            select[9]="K";
            select[10]="M";
            select[11]="N";
            select[12]="P";
            select[13]="R";
            select[14]="S";
            select[15]="T";
            select[16]="U";
            select[17]="V";
            select[18]="W";
            select[19]="X";
            select[20]="Y";
            select[21]="Z";
            select[22]="2";
            select[23]="3";
            select[24]="4";
            select[25]="5";
            select[26]="6";
            select[27]="7";
            select[28]="8";
            select[29]="9";
            select[30]="a";
            select[31]="b";
            select[32]="c";
            select[33]="d";
            select[34]="e";
            select[35]="f";
            select[36]="g";
            select[37]="h";
            select[38]="j";
            select[39]="k";
            select[40]="m";
            select[41]="n";
            select[42]="p";
            select[43]="q";
            select[44]="r";
            select[45]="s";
            select[46]="t";
            select[47]="u";
            select[48]="v";
            select[49]="w";
            select[50]="x";
            select[51]="y";
            select[52]="z";

                int i=0, j=0;
                double d = 0;
                //System.out.println("Generating password...");
                do {
                    {
                        int rands = -1;
                        d = rnum.nextDouble();
                        rands = new Double(d*51).intValue();
                        j = rands+1;
                    }
                    nbuf.append(select[j]);
                    i++;
                } while (i<8);			//change this for size of password

            try{
                MailManager mailManager = new MailManager("vbtonline");
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
                SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));

                InetAddress direccion = InetAddress.getLocalHost();
                String nombreDelHost = direccion.getHostName();//nombre host
                String IP_local = direccion.getHostAddress();//ip como String

                String htmlText = "";
                htmlText = ResourceBundle.getBundle("Comun"+idioma).getString("TAGLinea1EmailClaveConfirmacion") +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGLinea2EmailClaveConfirmacion") + " " +  nbuf.toString().toLowerCase() +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("FIELDTxtLogin") + ": " + usuario.getLogin().toLowerCase()  +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": "+ formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGInfoEmailClaveConfirmacion").replaceAll("###","\n") +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgNoResponderEmail").replaceAll("###","\n") +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAgradecimiento") + "\n\n";

            mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail().toLowerCase(),ResourceBundle.getBundle("Comun"+idioma).getString("TAGTitEmailClaveConfirmacion"),htmlText);
//                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),"rafgoddark@gmail.com".toLowerCase(),ResourceBundle.getBundle("Comun"+idioma).getString("TAGTitEmailClaveConfirmacion"),htmlText);

            }catch (Exception ex) {
                logger.error(ex); throw (new Exception (ex.getMessage(),null));
            }
            finally {
                closeJdbcObjects (connection, statement, result);
            }





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (nbuf.toString());
    }



    public String crearClaveRamdomSMS (VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "SecurityIo.crearClaveRamdomSMS";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String[] select = new String[63];
        java.util.Date date=new java.util.Date();
        java.lang.StringBuffer nbuf = new java.lang.StringBuffer();
        java.util.Random rnum = new java.util.Random(date.getTime());
        ClientContext context = null;
        ServicioVbt vbt=new ServicioVbt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            select[0]="A";
            select[1]="B";
            select[2]="C";
            select[3]="D";
            select[4]="E";
            select[5]="F";
            select[6]="G";
            select[7]="H";
            select[8]="J";
            select[9]="K";
            select[10]="M";
            select[11]="N";
            select[12]="P";
            select[13]="R";
            select[14]="S";
            select[15]="T";
            select[16]="U";
            select[17]="V";
            select[18]="W";
            select[19]="X";
            select[20]="Y";
            select[21]="Z";
            select[22]="2";
            select[23]="3";
            select[24]="4";
            select[25]="5";
            select[26]="6";
            select[27]="7";
            select[28]="8";
            select[29]="9";
            select[30]="a";
            select[31]="b";
            select[32]="c";
            select[33]="d";
            select[34]="e";
            select[35]="f";
            select[36]="g";
            select[37]="h";
            select[38]="j";
            select[39]="k";
            select[40]="m";
            select[41]="n";
            select[42]="p";
            select[43]="q";
            select[44]="r";
            select[45]="s";
            select[46]="t";
            select[47]="u";
            select[48]="v";
            select[49]="w";
            select[50]="x";
            select[51]="y";
            select[52]="z";

            int i=0, j=0;
            double d = 0;
            //System.out.println("Generating password...");
            do {
                {
                    int rands = -1;
                    d = rnum.nextDouble();
                    rands = new Double(d*51).intValue();
                    j = rands+1;
                }
                nbuf.append(select[j]);
                i++;
            } while (i<8);			//change this for size of password

            try{

                String mensaje= ResourceBundle.getBundle("Comun"+idioma).getString("TAGLinea1SMSClaveConfirmacion") + " " +  nbuf.toString().toLowerCase() + " " +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGLinea2SMSClaveConfirmacion");

                String respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuario.getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                String numero = vbt.getTelefonoONL(usuario.getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS")) ;
                if (respuestaMsj == null){
                    this.GuardarLog(usuario.getLogin(), "18", "1", "21", "", usuario.getIP(), "Envio Codigo de confirmacion fallido al numero: " + numero);
                }   else{
                    this.GuardarLog(usuario.getLogin(), "18", "1", "21","", usuario.getIP(), "Envio Codigo de confirmacion exitoso al numero: " +numero);
                }



             vbt=null;
            }catch (Exception ex) {
                vbt=null;
                logger.error(ex); throw (new Exception (ex.getMessage(),null));
            }
            finally {
                vbt=null;
                closeJdbcObjects (connection, statement, result);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (nbuf.toString());
    }

    public String crearClaveRamdom2 () throws Exception {
        final String origen = "SecurityIo.crearClaveRamdom2";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String[] select = new String[63];
        java.util.Date date=new java.util.Date();
        java.lang.StringBuffer nbuf = new java.lang.StringBuffer();
        java.util.Random rnum = new java.util.Random(date.getTime());
        ClientContext context = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            select[0]="A";
            select[1]="B";
            select[2]="C";
            select[3]="D";
            select[4]="E";
            select[5]="F";
            select[6]="G";
            select[7]="H";
            select[8]="J";
            select[9]="K";
            select[10]="M";
            select[11]="N";
            select[12]="P";
            select[13]="R";
            select[14]="S";
            select[15]="T";
            select[16]="U";
            select[17]="V";
            select[18]="W";
            select[19]="X";
            select[20]="Y";
            select[21]="Z";
            select[22]="2";
            select[23]="3";
            select[24]="4";
            select[25]="5";
            select[26]="6";
            select[27]="7";
            select[28]="8";
            select[29]="9";
            select[30]="a";
            select[31]="b";
            select[32]="c";
            select[33]="d";
            select[34]="e";
            select[35]="f";
            select[36]="g";
            select[37]="h";
            select[38]="j";
            select[39]="k";
            select[40]="m";
            select[41]="n";
            select[42]="p";
            select[43]="q";
            select[44]="r";
            select[45]="s";
            select[46]="t";
            select[47]="u";
            select[48]="v";
            select[49]="w";
            select[50]="x";
            select[51]="y";
            select[52]="z";

                int i=0, j=0;
                double d = 0;
                //System.out.println("Generating password...");
                do {
                    {
                        int rands = -1;
                        d = rnum.nextDouble();
                        rands = new Double(d*51).intValue();
                        j = rands+1;
                    }
                    nbuf.append(select[j]);
                    i++;
                } while (i<8);			//change this for size of password

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (nbuf.toString());
    }

    public String cambiarClaveOpeEsp (String clave, String login) throws Exception {
        final String origen = "SecurityIo.cambiarClaveOpeEsp";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.login_asignarPassOpeEsp_pr(?,?,?); end;");


            if(clave == null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                codigo = simpleCrypt.doCrypt(clave.toUpperCase());
                codigo = codigo.replaceAll("\r\n",""); //elimina los ultimos caracteres especiales
                statement.setString(1,codigo.trim());
            };

            if(login==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, login);
            }


            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


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

    public String salvarNuevoPassword (String clave, String login) throws Exception {
        final String origen = "SecurityIo.salvarNuevoPassword";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin backoffice.bac_usuariosetpass_pr(?,?,?); end;");

            if(login==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, login);
            }

            if(clave == null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                codigo = simpleCrypt.doCrypt(clave.toUpperCase());
                codigo = codigo.replaceAll("\r\n",""); //elimina los ultimos caracteres especiales
                statement.setString(2,codigo.trim());
            };


            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


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

    public List<String> obtenerEmailContrato (String login) throws Exception {
        final String origen = "SecurityIo.obtenerEmailContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=new String();
        List<String> datos = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin backoffice.bac_usuariosconcon_pr(?,?,?); end;");

            if(login==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, login);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (2);

            while (result.next ()) {
               datos = new ArrayList<String>();
               datos.add(result.getString(1));
               datos.add(result.getString(2));
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (datos);
    }


    public SecurityCardOd cargarCoordenadas (String login, String ip) throws Exception {
        final String origen = "SecurityIo.cargarCoordenadas";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        ResultSet resultFilas = null;
        ResultSet resultColumnas = null;
        List filas = new ArrayList();
        List columnas = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=new String();
        String coordenadas="";
        int pos=0;
        SecurityCardOd tarjeta=new SecurityCardOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();
            statement  = connection.prepareCall ("begin tco.vbt_pr_seleccion(?,?,?,?,?,?,?,?); end;");

            if(login==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, login);
            }

            if(ip==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, ip);
            }

            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(7);

            if (respuesta.equalsIgnoreCase("0")){


                resultFilas = (ResultSet) statement.getObject (4);
                resultColumnas = (ResultSet) statement.getObject (5);
                result = (ResultSet) statement.getObject (6);

                while (resultFilas.next ()) {
                   filas.add(resultFilas.getString(1).toString());
                }

                while (resultColumnas.next ()) {
                   columnas.add(resultColumnas.getString(1).toString());
                }


                tarjeta.setTotalFilas(Integer.toString(filas.size()));
                tarjeta.setTotaColumnas(Integer.toString(columnas.size()));
                tarjeta.setResultado(respuesta);
                tarjeta.setSerial(statement.getString(3));

                while (result.next ()) {
                    pos=0;
                    do {
                        pos++;
                    } while (!(filas.get(pos-1).toString()).equalsIgnoreCase(result.getString(1)));

                    coordenadas=coordenadas+result.getString(1)+"-"+pos+"|";
                    pos=0;

                    do {
                        pos++;
                    } while (!(columnas.get(pos-1).toString()).equalsIgnoreCase(result.getString(2)));

                    pos=pos+1;
                    coordenadas=coordenadas+result.getString(2)+"-"+pos+"*";

                }

                coordenadas=coordenadas.substring(0, coordenadas.length()-1);
                tarjeta.setCoordenadas(coordenadas);
            }
            else{
                tarjeta.setResultado(respuesta);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tarjeta);
    }


    public String cargarTarjeta (String login) throws Exception {
        final String origen = "SecurityIo.cargarCoordenadas";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=new String();
        String tarjeta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();
            statement  = connection.prepareCall ("begin tco.vbt_pr_consultas(?,?,?,?,?,?,?); end;");



            statement.setString(1,"");

            if(login==null){
                statement.setString(2,"");
            }else{
                statement.setString(2, login);
            }

            statement.setString(3, "C");

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(7);

            if (respuesta.equalsIgnoreCase("3")){
                tarjeta=statement.getString(4);
            }
            else{
                if (respuesta.equalsIgnoreCase("6")){
                    tarjeta="BLOQUEDA";
                }else{
                    if (respuesta.equalsIgnoreCase("7")){
                        tarjeta="VENCIDA";
                    }else{
                        tarjeta="NO OK";
                    }
                }

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tarjeta);
    }




    public String validarCoordenadas (String login, String ip, String fila, String columna, String valor,String fila2, String columna2, String valor2) throws Exception {
        final String origen = "SecurityIo.cargarCoordenadas";
        Connection connection = null;
        CallableStatement statement = null;

        ResultSet result = null;
        List l = new ArrayList();
        long time = 0, time2 = 0, time3 = 0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            connection.setAutoCommit(false);
            statement  = connection.prepareCall ("begin tco.VBT_PR_VALIDACION(?,?,?,?,?,?,?); end;");

            if(login==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, login);
            }

            if(ip==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, ip);
            }

            if(fila==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, fila);
            }

            if(columna==null){
                statement.setNull(4, OracleTypes.NULL);
            }else{
                statement.setString(4, columna);
            }

            if(valor==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, valor);
            }

            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(6);

            if (respuesta.equalsIgnoreCase("0")){


                statement  = connection.prepareCall ("begin tco.VBT_PR_VALIDACION(?,?,?,?,?,?,?); end;");

                if(login==null){
                    statement.setNull(1, OracleTypes.NULL);
                }else{
                    statement.setString(1, login);
                }

                if(ip==null){
                    statement.setNull(2, OracleTypes.NULL);
                }else{
                    statement.setString(2, ip);
                }

                if(fila2==null){
                    statement.setNull(3, OracleTypes.NULL);
                }else{
                    statement.setString(3, fila2);
                }

                if(columna==null){
                    statement.setNull(4, OracleTypes.NULL);
                }else{
                    statement.setString(4, columna2);
                }

                if(valor2==null){
                    statement.setNull(5, OracleTypes.NULL);
                }else{
                    statement.setString(5, valor2);
                }

                statement.registerOutParameter(6, OracleTypes.VARCHAR);
                statement.registerOutParameter(7, OracleTypes.VARCHAR);

                statement.execute();

                respuesta = statement.getString(6);

                if (!respuesta.equalsIgnoreCase("0")){
                    connection.rollback();
                }else{
                    connection.commit();
                }

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            connection.rollback();
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String cambiarClaveOpeEspPersonal (String clave, String login) throws Exception {
        final String origen = "SecurityIo.cambiarClaveOpeEspPersonal";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.login_updateuser_pr(?,?,?); end;");


            if(clave == null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                codigo = simpleCrypt.doCrypt(clave.toUpperCase());
                codigo = codigo.replaceAll("\r\n",""); //elimina los ultimos caracteres especiales
                statement.setString(1,codigo.trim());
            };

            if(login==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, login);
            }


            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


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

    public String verificarSiClaveProvisonalOpeEsp (String login) throws Exception {
        final String origen = "SecurityIo.verificarSiClaveProvisonalOpeEsp";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        String cambiar = "N";
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.verificarPasswordOpeEsp_pr(?,?,?); end;");


            if(login == null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
               statement.setString(1,login);
            };

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                cambiar = result.getString(4);
                cambiar = cambiar + '|' +result.getString(5);

            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (cambiar);
    }

    public String bloquearUsuario (String login) throws Exception {
        final String origen = "SecurityIo.bloquearUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        String cambiar = "N";
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.bloquear_usuario_pr(?,?); end;");


            if(login == null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,login);
            };

            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


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


    public String verificarClaveOpeEsp (String login, String clave) throws Exception {
        final String origen = "SecurityIo.verificarClaveOpeEsp";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String  mensaje=null;
        String codigo = new String();
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuario = null;
        String respuesta=new String();
        String resultado = "NO OK";
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin LOGIN.verificarPasswordOpeEsp_pr(?,?,?); end;");


            if(login == null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,login);
            };

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);


            codigo = simpleCrypt.doCrypt(clave.toUpperCase());
            codigo = codigo.replaceAll("\r\n", ""); //elimina los ultimos caracteres especiales
            codigo = codigo.trim();


            if (!respuesta.equalsIgnoreCase("OK")){
                throw (new Exception (respuesta,null));
            }
            else{
                result = (ResultSet) statement.getObject (2);
                while (result.next()){

                    if (codigo.equalsIgnoreCase(result.getString(6)))
                        resultado="OK";

                }
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ SecurityIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ SecurityIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (resultado);
    }



    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "SecurityIo-.GuardarLog";
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
        final String origen = "SecurityIo-.GuardarLog";
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

    public String correoClientePrincipal (VBTUsersOd usuario,String idioma, String text) throws Exception {
        final String origen = "FirmasConjuntasIo.correoClientePrincipal";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

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


            if (respuesta.equalsIgnoreCase("OK")){
                result = (ResultSet) statement.getObject (2);

                String tipo=ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("tipo"+usuario.getTipo());
                String correos="";

                while (result.next()){

                    //mailManager.sendHtmlMail(rb.getString("adminmail"),strTxtEmailAgregar,"VBT Bank & Trust Online",htmlText);

                  correos=correos+result.getString(5)+",";


                }
                MailManager mailManager = new MailManager("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                String htmlTextUser = "";
                htmlTextUser = text;
                mailManager.sendPlainMail(rb.getString("adminmail"),correos, "VBT Bank & Trust Online", htmlTextUser);


            }else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public  List<String> carterasUsuario (String login) throws Exception {
        final String origen = "SecurityIo.carterasUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String carteras = new String();
        String carterasAux = new String();
        String contrato="";
        long time = 0;
        List<String> datos = new ArrayList<String>();

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_carterasUsuario_pr(?,?,?); end;");

            if(login==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, login);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            carterasAux="'";
            while(result.next()){
                if (!NullFormatter.isBlank(carteras)) {
                    carteras += ",";
                }
                carteras += NullFormatter.formatBlank(result.getString(1));
                contrato= NullFormatter.formatBlank(result.getString(2));
                carterasAux += NullFormatter.formatBlank(result.getString(1))+"','";
//                correos= result.getString(1);
            }

            carterasAux=carterasAux.substring(0,carterasAux.length()-2);
            if (carteras.substring(carteras.length()-1,carteras.length()).equalsIgnoreCase(",")){
                carteras=carteras.substring(0,carteras.length()-1);
            }

            datos = new ArrayList<String>();
            datos.add(contrato);
            datos.add(carteras);
            datos.add(carterasAux);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (datos);
    }

    public String emailEjecutivos (String cartera) throws Exception {
        final String origen = "SecurityIo.emailEjecutivos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String correos = new String();
        long time = 0;

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_mail_ejecutivos(?,?,?); end;");

            if(cartera==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, cartera);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }

            if (correos.substring(correos.length()-1,correos.length()).equalsIgnoreCase(",")){
                correos=correos.substring(0,correos.length()-1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }


}

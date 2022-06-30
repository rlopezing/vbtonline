package ve.com.vbtonline.servicio.io;

//import com.venezolano.util.mail.MailManager;
import com.venezolano.util.mail.MailManager;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import comsms.vbt.servicio.ServicioVbt;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.GeneratePassword;
import ve.com.vbtonline.servicio.util.SimpleCrypt;
import ve.com.vbtonline.servicio.util.TransformTable;

import java.io.Serializable;
import java.net.InetAddress;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirmasConjuntasIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(FirmasConjuntasIo.class);

    /** Constructor de la clase
     */
    public FirmasConjuntasIo() {
    }


    public String consultarCarterasContrato (String codcontrato) throws Exception {
        final String origen = "FirmasConjuntasIo.consultarCarterasContrato";
        Connection connection2 = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String carteras=new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection2=getConnection();

            statement  = connection2.prepareCall ("begin BACKOFFICE.bac_contraeditarcart_pr(?,?,?); end;");

//            bac_usuOpcionUsuarioInt_pr (p_strTxtUsuarioEditar in varchar2,  scaetano
//                    p_strTxtcodcia in varchar2,   VBT
//                    p_strTxtcodsis in varchar2,   ONLINE
//                    p_strTxtcodopc in varchar2,   000000002
//                    p_bac_usuOpcionUsuarioInt OUT bac_usuOpcionUsuarioInt,
//                    p_resultado OUT VARCHAR2)

            if(codcontrato==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, codcontrato);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            int con = 0;
            while (result.next()){
                if(con==0)
                  carteras += result.getString(1);
                else
                 carteras += "  |  "+result.getString(1);

                con++;
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection2, statement, result);
        }

        return (carteras);
    }

    public TableOd consultarUsuarios (ConsultUsersOd consulta, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "FirmasConjuntasIo.consultarUsuarios";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaUsuarios = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_usuarioFirmaConjunta_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            if(consulta.getStrConsulta()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,consulta.getStrConsulta());

            if(consulta.getStrCargador()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,consulta.getStrCargador());

            if(consulta.getStrAprobador()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,consulta.getStrAprobador());

            if(consulta.getStrLiberador()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,consulta.getStrLiberador());

            if(consulta.getStrAuditor()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,consulta.getStrAuditor());

            if(consulta.getStrActivo()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,consulta.getStrActivo());

            if(consulta.getStrInactiva()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,consulta.getStrInactiva());

            if(consulta.getStrCancelada()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,consulta.getStrCancelada());

            if(consulta.getStrBloqueado()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,consulta.getStrBloqueado());

            if(consulta.getStrDesconocido()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,consulta.getStrDesconocido());

            if(consulta.getStrTxtUsuario()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,consulta.getStrTxtUsuario());

            if(consulta.getStrTxtNombre()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,consulta.getStrTxtNombre().toUpperCase());

            if(consulta.getStrTxtCIDRIF()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,consulta.getStrTxtCIDRIF());

            if(consulta.getStrCmbTipoUsuario()==null)
                statement.setNull(14, OracleTypes.NULL);
            else
                statement.setString(14,consulta.getStrCmbTipoUsuario());

            if(consulta.getStrCmbEstatus()==null)
                statement.setNull(15, OracleTypes.NULL);
            else
                statement.setString(15,consulta.getStrCmbEstatus());

            if(consulta.getStrOrden()==null)
                statement.setNull(16, OracleTypes.NULL);
            else
                statement.setString(16,consulta.getStrOrden());

            if(consulta.getHdnAccion()==null)
                statement.setNull(17, OracleTypes.NULL);
            else
                statement.setString(17,consulta.getHdnAccion());

            if(usuario.getNumContrato()==null)
                statement.setNull(18, OracleTypes.NULL);
            else
                statement.setString(18, usuario.getNumContrato());

            if(usuario.getLogin()==null)
                statement.setNull(19, OracleTypes.NULL);
            else
                statement.setString(19, usuario.getLogin());


            statement.registerOutParameter(20, OracleTypes.CURSOR);
            statement.registerOutParameter(21, OracleTypes.VARCHAR);
            statement.registerOutParameter(22, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(21);
            sqlSelect = statement.getString(22);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));

            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (20);

            usuariosOd=new VBTUsersOd();
            listaUsuarios2 = new ArrayList<VBTUsersOd>();
            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            while (result.next ()) {
                body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(12))+"|"+ NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"' type='button' >");
                body.add("<a style='cursor:pointer' id='"+ NullFormatter.formatHtmlBlank(result.getString(1)) +"' onclick='seleccionarUsuariosFirmaConjuntaOpcion(this.id,this.value)' value='"+
                        NullFormatter.formatHtmlBlank(result.getString(6).toString())+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1).toString())+"<b></a>");
                body.add(NullFormatter.formatHtmlBlank(result.getString(3).toString()));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6).toString()));
                body.add(NullFormatter.formatHtmlBlank(result.getString(9).toString()));
                body.add(NullFormatter.formatHtmlBlank(result.getString(8)));
                bodys.add(body);
            }



            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("Usuario"));
            header.add(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("Nombre"));
            header.add(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("Grupo"));
            header.add(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("Estatus"));
            header.add(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("Ultimo_Acceso"));


            listaUsuarios=new TableOd();

            listaUsuarios= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public VBTUsersOd consultarUsuario (String id, String tipoGrupo) throws Exception {
        final String origen = "FirmasConjuntasIo.consultarUsuario";
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuarioconsul_pr(?,?,?); end;");

            if(id==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,id);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            usuariosOd=new VBTUsersOd();

            while (result.next ()) {
//
                usuariosOd.setLogin(id);
                usuariosOd.setNombres(result.getString(1));
                usuariosOd.setDireccion(result.getString(2));
                usuariosOd.setTelefono(result.getString(3));
                usuariosOd.setTelefono_celular(result.getString(4));
                usuariosOd.setEmail(result.getString(5));
                usuariosOd.setTipo(result.getString(6));
                usuariosOd.setStatus_cuenta(result.getString(7));
                usuariosOd.setTipo_cirif( result.getString(8).substring(0,1));
                usuariosOd.setCirif(result.getString(8).substring(2,result.getString(8).length()));

            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (usuariosOd);
    }

    public String editarUsuario (VBTUsersOd usuario,VBTUsersOd usuarioAnterior,String idioma) throws Exception {
        final String origen = "FirmasConjuntasIo.editarUsuario";
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuarioeditar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//

            if(usuario.getLogin()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin());

            if(usuario.getTelefono_celular()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,usuario.getTelefono_celular());

            if(usuario.getTelefono()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuario.getTelefono());

            if(usuario.getEmail()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,usuario.getEmail());

            if(usuario.getTipo_cirif()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,usuario.getTipo_cirif());

            if(usuario.getCirif()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,usuario.getCirif());

            if(usuario.getDireccion()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,usuario.getDireccion());

            if(usuario.getNombres()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,usuario.getNombres());

            if(usuario.getStatus_cuenta()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,usuario.getStatus_cuenta());

            if(usuario.getTipo()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,usuario.getTipo());

            if(usuario.getCambioStatus()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getCambioStatus());

            if(usuario.getTipo_grupo()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,usuario.getTipo_grupo());

            if(usuario.getCodpercli()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,usuario.getCodpercli());

                statement.setString(14,"A");



            statement.registerOutParameter(15, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(15);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else {
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                correoClientePrincipalModificar(usuario,usuarioAnterior,idioma);

            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String agregarUsuario (VBTUsersOd usuario, VBTUsersOd usuarioCreador, String idioma) throws Exception {
        final String origen = "FirmasConjuntasIo.agregarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        GeneratePassword passwordTemporal = new GeneratePassword();
        ServicioVbt vbt=new ServicioVbt();
//        MailManager mailManager = new MailManager("vbtonline");
//        ResourceBundle rb = ResourceBundle.getBundle("vbtonline");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_agregarUsuarioFC_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            String codigo = passwordTemporal.generateRandomPassword().toLowerCase();
            String clave = codigo;
            codigo = simpleCrypt.doCrypt(codigo.toUpperCase());
            codigo = codigo.replaceAll("\r\n","");
            usuario.setPassword(codigo.trim());


            if(usuario.getLogin()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin().toLowerCase());

            if(usuario.getPassword()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,usuario.getPassword());
           // String plogin = "scaetano";

            if(usuarioCreador.getLogin()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuarioCreador.getLogin().substring(0,6));

            if(usuario.getDireccion()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,usuario.getDireccion());

            if(usuario.getTelefono_celular()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,usuario.getTelefono_celular());

            if(usuario.getTelefono()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,usuario.getTelefono());

            if(usuario.getEmail()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,usuario.getEmail());

            if(usuario.getPassword()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,usuario.getPassword());

            if(usuario.getTipo()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,usuario.getTipo());

            if(usuario.getTipo_cirif()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,usuario.getTipo_cirif());

            if(usuario.getCirif()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getCirif());

            if(usuario.getNombres()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,usuario.getNombres().toLowerCase());

            if(usuario.getTipo_grupo()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,usuario.getTipo_grupo());


            statement.registerOutParameter(14, OracleTypes.VARCHAR);
            statement.registerOutParameter(15, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(14);
            String existe = statement.getString(15);
//            String existe = "existe";
//            respuesta = "OK";

            if (!respuesta.equalsIgnoreCase("OK")){
                if (!existe.equalsIgnoreCase("Usuario Registrado"))
                  throw (new Exception (respuesta,null));
                else
                    return (existe);
            }else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            }

//            statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarUsuarioGrupo_pr(?,?,?,?,?,?); end;");
//
//            if(usuario.getLogin()== null)
//                statement.setNull(1,OracleTypes.NULL);
//            else
//                statement.setString(1,usuario.getLogin());
//
//            if(usuario.getTipo_grupo()== null)
//                statement.setNull(2,OracleTypes.NULL);
//            else
//                statement.setString(2,usuario.getTipo_grupo());
//
//            statement.setString(3,"ONLINE");
//            statement.setString(4,"VBT");
//            statement.setString(5, usuarioCreador.getLogin());
//
//            statement.registerOutParameter(6, OracleTypes.VARCHAR);
//            statement.execute ();
//
//            respuesta = statement.getString(6);
//
//            if (!respuesta.equalsIgnoreCase("OK"))
//                throw (new Exception (respuesta,null));
//            else
//                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            //------- Agregamos relacion usuario con contrato
            statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarUsuarioContrato_pr(?,?,?); end;");
//                sql = "INSERT INTO vbt_users_contrato (login, num_contrato) VALUES (?, ?)";


            if(usuario.getLogin()== null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin().toLowerCase());

            if(usuarioCreador.getNumContrato()== null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,usuarioCreador.getNumContrato());

            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            //enviar correo

            connection.commit();
            MailManager mailManager = new MailManager("vbtonline");
            ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

            String htmlTextUser = "";
            String htmlTextPass = "";
            htmlTextUser =ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail001") +
                    "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("FIELDTxtLogin") + ": " + usuario.getLogin().toLowerCase()  +
                    "\n\n" + ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail002");

            htmlTextPass =ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail001") +
                    "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("FIELDPwdClave") + ": " + clave.toLowerCase()  +
                    "\n\n" + ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail002");

            //mailManager.sendHtmlMail(rb.getString("adminmail"),strTxtEmailAgregar,"VBT Bank & Trust Online",htmlText);
            //Envio de correo
//            mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), "VBT Bank & Trust Online", htmlTextUser);
//            mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), "VBT Bank & Trust Online", htmlTextPass);


            //Envio de SMS

            String mensaje = ResourceBundle.getBundle("ContratosEditar"+idioma).getString("TAGUserName") + ": " + usuario.getLogin().toLowerCase();

            String telefono="";
            String respuestaMsj="";
            telefono= vbt.getTelefonoONL(usuario.getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
            if (!telefono.equalsIgnoreCase("0")) {
                respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuario.getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                respuesta="OK";
            }else {
                respuesta="NO SMS";
            }


            mensaje = ResourceBundle.getBundle("ContratosEditar"+idioma).getString("TAGPassword") + ": " + clave.toLowerCase();

            if (!telefono.equalsIgnoreCase("0")){
                respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuario.getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                respuesta="OK";
            }else {
                respuesta="NO SMS";
            }





            vbt=null;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            vbt=null;
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            vbt=null;
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public String correoClientePrincipal (VBTUsersOd usuario,String idioma) throws Exception {
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


                while (result.next()){
                    MailManager mailManager = new MailManager("vbtonline");
                    ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                    String htmlTextUser = "";
                    htmlTextUser =ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail003") +  ": " + usuario.getLogin().toLowerCase()+
                            " " + ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail004") + ": " + tipo.toLowerCase()+"\n\n";

                    //mailManager.sendHtmlMail(rb.getString("adminmail"),strTxtEmailAgregar,"VBT Bank & Trust Online",htmlText);
                    mailManager.sendPlainMail(rb.getString("adminmail"),result.getString(5), "VBT Bank & Trust Online", htmlTextUser);



                }


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


    public String correoClientePrincipalModificar (VBTUsersOd usuario,VBTUsersOd usuarioAnterior,String idioma) throws Exception {
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
                String campos="";

                if(!usuario.getNombres().equalsIgnoreCase(usuarioAnterior.getNombres())){
                    campos=campos+ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("camporName")+": "+usuario.getNombres()+"\n";
                }

                if(!usuario.getStatus_cuenta().equalsIgnoreCase(usuarioAnterior.getStatus_cuenta())){
                    campos=campos+ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("campoStatus")+": "+ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("status"+usuario.getStatus_cuenta())+"\n";
                }
                if(!usuario.getCirif().equalsIgnoreCase(usuarioAnterior.getCirif())){
                    campos=campos+ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("campoID")+": "+usuario.getCirif()+"\n";
                }
                if(!usuario.getTipo().equalsIgnoreCase(usuarioAnterior.getTipo())){
                    campos=campos+ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("campoGroup")+": "+ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("tipo"+usuario.getTipo())+"\n";
                }

                if (!campos.equalsIgnoreCase("")){
                    while (result.next()){
                        MailManager mailManager = new MailManager("vbtonline");
                        ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                        String htmlTextUser = "";
                        htmlTextUser =ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail005") +  " " + usuario.getLogin().toLowerCase()+
                                " " + ResourceBundle.getBundle("UsuariosAgregar"+idioma).getString("TAGMsgEmail006") + " \n" + campos+"\n\n";

                        //mailManager.sendHtmlMail(rb.getString("adminmail"),strTxtEmailAgregar,"VBT Bank & Trust Online",htmlText);
                        mailManager.sendPlainMail(rb.getString("adminmail"),result.getString(5), "VBT Bank & Trust Online", htmlTextUser);



                    }
                }

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



    public SelectOd cargarElementosTipos (String tipo, String idioma) throws Exception {
        final String origen = "TransfersIo.cargarElementosTipos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_elementostipo_pr(?,?,?); end;");

            if(tipo!=null){
                statement.setString(1, tipo);
            }else{
                statement.setNull(1, OracleTypes.NULL);
            }


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            while (result.next()){
                //para no mostrar el tipo usuario administrador
                if(!result.getString(1).equalsIgnoreCase("10")){
                    elemento.setLabel(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString(result.getString(2)));
                    elemento.setValue(result.getString(1));
                    Elementos.add(elemento);
                    elemento=new ContentSelectOd();
                }

            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarGrupoClientes () throws Exception {
        final String origen = "TransfersIo.cargarGrupoClientes";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_grupocliente_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            while (result.next()){
                elemento.setLabel(result.getString(2));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTipoCiRif () throws Exception {
        final String origen = "TransfersIo.cargarTipoCiRif";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_elemencodtipo_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "FirmasConjuntasIo.GuardarLog";
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
        final String origen = "FirmasConjuntasIo.GuardarLogFC";
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


    public ParametrosPersonalesFCOd cargarParametrosPersonales (List<String> parametros) throws Exception {
        final String origen = "FirmasConjuntasIo.cargarParametrosPersonales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesFCOd parametrosPersonales=new ParametrosPersonalesFCOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametrosPersonales_FC(?,?,?); end;");

            if(parametros.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            String monto = new String();
            while (result.next()){
                parametrosPersonales.setNum_contrato(parametros.get(0));
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))), 2, ","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setCorreo(result.getString(4));

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public ParametrosPersonalesFCOd cargarParametrosPersonalesBase () throws Exception {
        final String origen = "FirmasConjuntasIo.cargarParametrosPersonalesBase";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesFCOd parametrosPersonales=new ParametrosPersonalesFCOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametrosGlobalesFC(?,?); end;");
//            bt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
//                    P_NUM_CONTRATO  IN VARCHAR2,
//                    P_tipo_persona IN VARCHAR2,
//                    p_datosPorDefecto OUT vbt_datos_diseneBanco,
//                    p_resultado OUT VARCHAR2)


////
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            String monto = new String();
            while (result.next()){
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))),2,","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setCorreo(result.getString(4));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public String guardarParametrosPersonales (ParametrosPersonalesFCOd parametrosPersonales) throws Exception {
        final String origen = "FirmasConjuntasIo.guardarParametrosPersonales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonalesOd=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_guardarParametrosP_FC_pr(?,?,?,?,?,?); end;");
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
            if(parametrosPersonales.getNum_contrato()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,parametrosPersonales.getNum_contrato());

            if(parametrosPersonales.getVbt_nmtd()==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setInt(2,parametrosPersonales.getVbt_nmtd());

            if(parametrosPersonales.getVbt_mmtd()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setDouble(3,Double.parseDouble(parametrosPersonales.getVbt_mmtd()));

            if(parametrosPersonales.getVbt_mmto()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setDouble(4,Double.parseDouble(parametrosPersonales.getVbt_mmto()));

            if(parametrosPersonales.getCorreo()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,parametrosPersonales.getCorreo());

            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

}


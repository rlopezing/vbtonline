package ve.com.vbtonline.vista.action.security;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.mail.MailManager;
import com.venezolano.util.text.NullFormatter;
import com.venezolano.webutil.ClientContext;
import comsms.vbt.servicio.ServicioVbt;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.desingBank.IDesingBankServicio;
import ve.com.vbtonline.servicio.negocio.firmasConjuntas.IFirmasConjuntasServicio;
import ve.com.vbtonline.servicio.negocio.security.ISecurityServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.io.LoginIo;
import ve.com.vbtonline.servicio.util.SimpleCrypt;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 21/05/2010
 * Time: 11:33:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(SecurityAction.class);
    private FabricaTo fabrica;
    private ISecurityServicio securityServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonOpeEsp;
    private Map session;
    private ClientContext context;
    private String cambiar;
    private String resultado;
    private String idioma;
    private String jsonParametros;
    private String usuarioCliente;
    private String respuesta;
    private String jsonTransfers;
    private String numReferencia;
    private String codigo;
    private String coordenadas;
    private String filas;
    private String columnas;
    private String serial;
    private String telefono;
    private IFirmasConjuntasServicio firmasConjuntasServicio;

    public IFirmasConjuntasServicio getFirmasConjuntasServicio() {
        return firmasConjuntasServicio;
    }

    public void setFirmasConjuntasServicio(IFirmasConjuntasServicio firmasConjuntasServicio) {
        this.firmasConjuntasServicio = firmasConjuntasServicio;
    }
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFilas() {
        return filas;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setFilas(String filas) {
        this.filas = filas;
    }

    public String getColumnas() {
        return columnas;
    }

    public void setColumnas(String columnas) {
        this.columnas = columnas;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;






    public SecurityAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "SecurityAction.execute";
        long time;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }

    public String crearClaveRamdom () throws Exception {
        final String origen = "SecurityAction.crearClaveRamdom";
        long time;
        AccountsOd account;
        String clave = new String();
        Integer intentos_clave = 0;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            String idioma = (String) session.get("idioma");
            clave=getSecurityServicio().crearClaveRamdom(usuario,idioma);
            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            if(data != null)
              numReferencia = data.getParametros().get(0);
            else
                numReferencia = "";
            session.put("clave_generada",clave.toLowerCase());
            session.put("intentos_clave",intentos_clave);
            session.put("numReferencia",numReferencia);
//            System.out.println("*****claveEmail:"+clave.toLowerCase());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String crearClaveRamdomSMS () throws Exception {
        final String origen = "SecurityAction.crearClaveRamdomSMS";
        long time;
        AccountsOd account;
        String clave = new String();
        Integer intentos_clave = 0;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            String idioma = (String) session.get("idioma");
            clave=getSecurityServicio().crearClaveRamdomSMS(usuario,idioma);
            respuesta=clave;
            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            if(data != null)
                numReferencia = data.getParametros().get(0);
            else
                numReferencia = "";
            session.put("clave_generada",clave.toLowerCase());
            session.put("intentos_clave",intentos_clave);
            session.put("numReferencia",numReferencia);
//            System.out.println("*****claveEmail:"+clave.toLowerCase());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String crearClaveRamdomOpeEsp() throws Exception {
        final String origen = "SecurityAction.crearClaveOpeEsp";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");

            u=(VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            clave=getSecurityServicio().crearClaveRamdom2();
            String respuesta = getSecurityServicio().cambiarClaveOpeEsp(clave, u.getLogin());
//            String respuesta = "OK";
            if(respuesta.equalsIgnoreCase("OK")){
                session.put("claveTemporalOpeEsp",clave);
                MailManager mailManager = new MailManager("vbtonline");
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
                SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));
                String IP_local = u.getIP();

                String htmlText = "";
                htmlText = ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsg1EmailPassOpeEsp") +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": "+ formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + IP_local +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgClaveTemporal") + ": " + clave.toLowerCase() +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGInfoCorreo_1").replaceAll("###","\n") +
                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAgradecimiento") + "\n\n";
//
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),u.getEmail().toLowerCase(),ResourceBundle.getBundle("Comun"+idioma).getString("TAGTitEmailPassOpeEsp"),htmlText);
//                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),"rafgoddark@gmail.com".toLowerCase(),ResourceBundle.getBundle("Comun"+idioma).getString("TAGTitEmailPassOpeEsp"),htmlText);
            }

            getSecurityServicio().guardarLog(u.getLogin(), "12", "1", "12", "", u.getIP(), "Creación/Reinicio clave Operaciones Especiales");
            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String enviarClave() throws Exception {
        final String origen = "SecurityAction.enviarClave";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        List<String> mailContrato = new ArrayList<>();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametros, DataJson.class);
            usuarioCliente = data.getParametros().get(0);

            clave=getSecurityServicio().crearClaveRamdom2();
            respuesta = "NO OK";
            respuesta = getSecurityServicio().salvarNuevoPassword(clave,usuarioCliente);
            if(respuesta.equalsIgnoreCase("OK")){
                mailContrato = getSecurityServicio().obtenerEmailContrato(usuarioCliente);
                MailManager mailManager = new MailManager("vbtonline");
                String htmlText = ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("TAGMsgEmail001") +
                        "\n\n" +  ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("FIELDPwdClave") + ": " + clave.toLowerCase()  +
                        "\n\n" +  ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("TAGMsgEmail002");

                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),mailContrato.get(0),"VBT Bank & Trust Online",htmlText);


            }
//
            usuario=(VBTUsersOd)session.get("user");
            getSecurityServicio().guardarLog(usuario.getLogin(), "14", "1", "1",usuarioCliente, usuario.getIP(), "Reinicio de clave, enviada al email: "+mailContrato.get(0));

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            respuesta = "NO OK";
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }



    public String enviarClaveSMS() throws Exception {
        final String origen = "SecurityAction.enviarClaveSMS";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        List<String> carteras = new ArrayList<>();
        ServicioVbt vbt=new ServicioVbt();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametros, DataJson.class);
            usuarioCliente = data.getParametros().get(0);

            MailManager mailManager = new MailManager("vbtonline");
            clave=getSecurityServicio().crearClaveRamdom2();
            respuesta = "NO OK";
            respuesta = getSecurityServicio().salvarNuevoPassword(clave,usuarioCliente);
            if(respuesta.equalsIgnoreCase("OK")){

                carteras= getSecurityServicio().carterasUsuario(usuarioCliente);
                String mensaje = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGPassword") + ": " + clave.toLowerCase();

                String emailsEjecuvitos=getSecurityServicio().emailEjecutivos(carteras.get(2));
                String telefono;
                String respuestaMsj="";
                telefono= vbt.getTelefonoONL(usuarioCliente,ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                if (!telefono.equalsIgnoreCase("0")){
                    respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuarioCliente.toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));

                    String     htmlText2= ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_clave_eje") +" " +  telefono +
                            "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje2") +" "+ carteras.get(0) +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje3") + ": " + carteras.get(1) +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje4") + ": " + usuarioCliente.toLowerCase()+"\n\n\n";


                    mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),emailsEjecuvitos,ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_clave_ejeTitulo"), htmlText2);

                } else{
                    respuesta="OK";
                }
//
            usuario=(VBTUsersOd)session.get("user");
            getSecurityServicio().guardarLog(usuario.getLogin(), "14", "1", "1",usuarioCliente, usuario.getIP(), "Reinicio de clave, enviada al telefono: "+telefono);
            }

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);


         vbt=null;
        }catch (Exception e){
            vbt=null;
            respuesta = "NO OK";
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }


    public String validarClaveSMS() throws Exception {
        final String origen = "securityAccion.validarClaveSMS";
        long time;
        Integer intentos_clave = 0;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + SecurityAction.class + " | " + origen);

            time = System.currentTimeMillis();
            Gson gson = new Gson();
            data = gson.fromJson(this.jsonTransfers, DataJson.class);
            idioma = (String) session.get("idioma");
            intentos_clave = (Integer) session.get("intentos_clave");
            String claveGenerada = (String) session.get("clave_generada");
            VBTUsersOd usuario=(VBTUsersOd)session.get("user");
            if(intentos_clave<3){
                if(data.getParametros().get(0).equalsIgnoreCase(claveGenerada)){
                    resultado ="OK";
                    mensaje="OK";
                    intentos_clave = 0;
                    session.put("intentos_clave",intentos_clave);
                    session.remove("clave_generada");
                }else{
                    if(intentos_clave==2){
                        resultado ="Cancel";
                        String bloqueo=getSecurityServicio().bloquearUsuario(usuario.getLogin());
                        if(idioma.equalsIgnoreCase("_us_en")) {
                            mensaje ="You have attempted three times to access with an incorrect confirmation code. Transaction will be cancelled and your user name blocked";
                        }else{
                            mensaje ="Ha ingresado por tercera vez un código de confirmación incorrecto. \n La operación será cancelada y su usuario bloqueado.";
                        }
                    }else{
                        resultado ="NO OK";
                        if(idioma.equalsIgnoreCase("_us_en")) {
                            mensaje ="Incorrect confirmation code. Transaction will be cancelled after three incorrect attempts";
                        }else{
                            mensaje ="Código de confirmación incorrecto. <br> Si se equivoca tres veces colocando el código de confirmación, la operación será cancelada  y su usuario bloqueado.";
                        }
                        intentos_clave++;
                        session.put("intentos_clave",intentos_clave);
                    }

                }
            }

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + SecurityAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String enviarClaveUsuarioBackoffice() throws Exception {
        final String origen = "SecurityAction.enviarClaveUsuarioBackoffice";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        List<String> mailContrato = new ArrayList<>();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametros, DataJson.class);
            usuarioCliente = data.getParametros().get(0);

            clave=getSecurityServicio().crearClaveRamdom2();
            respuesta = "NO OK";
            respuesta = getSecurityServicio().salvarNuevoPassword(clave,usuarioCliente);
            if(respuesta.equalsIgnoreCase("OK")){
                mailContrato = getSecurityServicio().obtenerEmailContrato(usuarioCliente);
                MailManager mailManager = new MailManager("vbtonline");
                String htmlText = "";
//
//                if ( !mailContrato.get(1).equals("") ) {
//                    htmlText = "N°: " + mailContrato.get(1) + "\n\n";
//                }

                htmlText += ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("TAGMsgEmail001") +

                        "\n\n" +  ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("FIELDPwdClave") + ": " + clave  +
                        "\n\n" +  ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("TAGMsgEmail002");

                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),mailContrato.get(0),"VBT Bank & Trust Online Registration",htmlText);


            }
//

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String enviarClaveUsuarioFirmaConjunta() throws Exception {
        final String origen = "SecurityAction.enviarClaveUsuarioFirmaConjunta";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        List<String> mailContrato = new ArrayList<>();
        ServicioVbt vbt=new ServicioVbt();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");
            idioma = (String) session.get("idioma");
            VBTUsersOd usu = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametros, DataJson.class);
            usuarioCliente = data.getParametros().get(0);

            clave=getSecurityServicio().crearClaveRamdom2();
            respuesta = "NO OK";
            respuesta = getSecurityServicio().salvarNuevoPassword(clave,usuarioCliente);
            if(respuesta.equalsIgnoreCase("OK")){

                String mensaje = ResourceBundle.getBundle("ContratosEditar"+idioma).getString("TAGresetPassword") + " " + ResourceBundle.getBundle("ContratosEditar"+idioma).getString("TAGPassword") + ": " + clave.toLowerCase();

                String telefono;
                String respuestaMsj="";
                telefono= vbt.getTelefonoONL(usuarioCliente,ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                if (!telefono.equalsIgnoreCase("0")){
                    respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuarioCliente.toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                    vbt=null;
                    respuesta="OK";

                } else{
                    respuesta="NO OK";
                }


                usuario=(VBTUsersOd)session.get("user");
                getSecurityServicio().guardarLogFC(usuario.getLogin(), "14", "1", "1",usuarioCliente, usuario.getIP(), "Reinicio de clave, enviada al telefono: "+telefono,usuario.getNumContrato());

                mailContrato = getSecurityServicio().obtenerEmailContrato(usuarioCliente);
                MailManager mailManager = new MailManager("vbtonline");
                String htmlText = "";

              /*  if ( !mailContrato.get(1).equals("") ) {
                    htmlText = "N°: " + mailContrato.get(1) + "\n\n";
                }

               htmlText += ResourceBundle.getBundle("UsuariosEditar"+idioma).getString("TAGMsgEmail008") +

                        "\n\n" +  ResourceBundle.getBundle("UsuariosEditar"+idioma).getString("FIELDPwdClave") + ": " + clave  +
                        "\n\n" +  ResourceBundle.getBundle("UsuariosEditar"+idioma).getString("TAGMsgEmail002");  */

                //mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),mailContrato.get(0),"VBT Bank & Trust Online Registration",htmlText);
                htmlText = ResourceBundle.getBundle("UsuariosEditar"+idioma).getString("TAGMsgEmail005") + " " + usuarioCliente  +
                        " " +  ResourceBundle.getBundle("UsuariosEditar"+idioma).getString("TAGMsgEmail006")+"\n";
                getSecurityServicio().correoClientePrincipal(usu, idioma, htmlText);

            }
//

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);


        vbt=null;
        }catch (Exception e){
            vbt=null;
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String enviarUsuario() throws Exception {
        final String origen = "SecurityAction.enviarUsuario";
        long time;
        String result=new String();
        result = SUCCESS;
        String clave = new String();
        List<String> mailContrato = new ArrayList<>();
        ServicioVbt vbt=new ServicioVbt();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");
            idioma = (String) session.get("idioma");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametros, DataJson.class);
            usuarioCliente = data.getParametros().get(0);

           mailContrato = getSecurityServicio().obtenerEmailContrato(usuarioCliente);
//            MailManager mailManager = new MailManager("vbtonline");
//            String htmlText = ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("TAGMsgEmail003") +
//                   // ResourceBundle.getBundle("Transferencias" + idioma).getString("TAGMsgInfoNombreBeneficiario");
//                    "\n\nContrato N°: " + NullFormatter.formatBlank(mailContrato.get(1)) +
//                    "\n\n" +  ResourceBundle.getBundle("Comun_ve_es").getString("FIELDTxtLogin") + ": " + usuarioCliente.toLowerCase()  +
//                    "\n\n" +  ResourceBundle.getBundle("UsuariosEditar_ve_es").getString("TAGMsgEmail002");
//
//            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),mailContrato.get(0),"VBT Bank & Trust Online",htmlText);

            String mensaje = ResourceBundle.getBundle("ContratosEditar"+idioma).getString("TAGUserName") + ": " + usuarioCliente.toLowerCase();

            String telefono="";
            String respuestaMsj="";
            telefono= vbt.getTelefonoONL(usuarioCliente,ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
            if (!telefono.equalsIgnoreCase("0")) {
                respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuarioCliente.toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                respuesta="OK";
                vbt=null;
            }else {
                respuesta="OK";
            }



             respuesta="OK";

//
            VBTUsersOd usuario=(VBTUsersOd)session.get("user");
            getSecurityServicio().guardarLogFC(usuario.getLogin(), "17", "1", "1", usuarioCliente, usuario.getIP(), "Se ha enviado el usuario :"+usuarioCliente.toLowerCase()+" del contrato: "+usuario.getNumContrato()+ " al telefono: "+telefono,usuario.getNumContrato());

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);


        vbt=null;
        }catch (Exception e){
            vbt=null;
            logger.error(e);
            respuesta="NO OK";
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String enviarUsuarioSMS() throws Exception {
        final String origen = "SecurityAction.enviarUsuarioSMS";
        long time;
        String result=new String();
        result = SUCCESS;
        String clave = new String();
        ServicioVbt vbt=new ServicioVbt();
        List<String> mailContrato = new ArrayList<>();
        List<String> carteras = new ArrayList<>();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            MailManager mailManager = new MailManager("vbtonline");
            String x=ResourceBundle.getBundle("package").getString("password");
            idioma = (String) session.get("idioma");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametros, DataJson.class);
            usuarioCliente = data.getParametros().get(0);

                carteras= getSecurityServicio().carterasUsuario(usuarioCliente);
                String mensaje = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGUserName") + ": " + usuarioCliente.toLowerCase();
                String emailsEjecuvitos=getSecurityServicio().emailEjecutivos(carteras.get(2));
                String telefono="";
                String respuestaMsj="";
                telefono= vbt.getTelefonoONL(usuarioCliente,ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                if (!telefono.equalsIgnoreCase("0")) {
                    respuestaMsj=vbt.enviarAlertaONL("",mensaje,usuarioCliente.toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                    respuesta="OK";

                    String     htmlText2= ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje") +" " +  telefono +
                            "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje2") +" "+ carteras.get(0) +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje3") + ": " + carteras.get(1) +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje4") + ": " + usuarioCliente.toLowerCase()+"\n\n\n";


                    mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),emailsEjecuvitos,ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_ejeTitulo"), htmlText2);


                }else {
                    throw (new Exception ("NO OK",null));
                }

                VBTUsersOd usuario=(VBTUsersOd)session.get("user");

                mailContrato = getSecurityServicio().obtenerEmailContrato(usuarioCliente);



            getSecurityServicio().guardarLog(usuario.getLogin(), "17", "1", "1", usuarioCliente, usuario.getIP(), "Se ha enviado el usuario :"+usuarioCliente.toLowerCase()+" del contrato: "+NullFormatter.formatBlank(mailContrato.get(1))+ "al telefono: "+telefono);

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);


        vbt=null;
        }catch (Exception e){
            vbt=null;
            logger.error(e);
            respuesta="NO OK";
            setMensaje(e.getMessage());
        }
        return result;
    }


    public String cambiarClaveOpeEspPersonal() throws Exception {
        final String origen = "SecurityAction.cambiarClaveOpeEspPersonal";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            u=(VBTUsersOd) session.get("user");
//            clave=getSecurityServicio().crearClaveRamdom2();
            Gson gson = new Gson();
            data = gson.fromJson(this.jsonOpeEsp, DataJson.class);
            idioma = (String) session.get("idioma");

            clave = data.getParametros().get(0); //Nueva Clave

            String claveCrypt = simpleCrypt.doCrypt(clave.toUpperCase());
            claveCrypt = claveCrypt.replaceAll("\r\n", ""); //elimina los ultimos caracteres especiales
            claveCrypt = claveCrypt.trim();

            if (!claveCrypt.equalsIgnoreCase(u.getPassword())){


                String claveTmp = data.getParametros().get(1);  //Clave temporal usuario
                //String claveTemporalOpeEsp=(String) session.get("claveTemporalOpeEsp"); //Clave temporal Original
                //String claveTemporalOpeEsp= getSecurityServicio().cambiarClaveOpeEspPersonal(clave, u.getLogin());
                String respuesta = "";
                respuesta =getSecurityServicio().verificarClaveOpeEsp(u.getLogin(),claveTmp);
                if(respuesta.equalsIgnoreCase("OK")){
                    respuesta = getSecurityServicio().cambiarClaveOpeEspPersonal(clave, u.getLogin());
                    if(respuesta.equalsIgnoreCase("OK")){
                        resultado = "OK";
                        MailManager mailManager = new MailManager("vbtonline");
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
                        SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));

                        InetAddress direccion = InetAddress.getLocalHost();
                        String IP_local = direccion.getHostAddress();//ip como String

                        String htmlText =  ResourceBundle.getBundle("CambiarPassword"+idioma).getString("TAGInfoEmailCambioPassOpeEsp") +
                                "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": "+ formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + u.getIP() +
                                "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("FIELDTxtLogin") + ": " + u.getLogin().toLowerCase()   +
                                "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAgradecimiento") + "\n\n";
        //
                        mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),u.getEmail().toLowerCase(),ResourceBundle.getBundle("CambiarPassword"+idioma).getString("TAGTitEmailCambioPassOpeEsp"),htmlText);
        //                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),"rafgoddark@gmail.com".toLowerCase(),ResourceBundle.getBundle("Comun"+idioma).getString("TAGTitEmailCambioPassOpeEsp"),htmlText);

                    }else{
                        resultado = "NO OK";
                    }
                }else{
                    resultado = "NO OK";
                }
            }else{
                resultado = "NO PASS";
            }



            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String verificarSiClaveProvisonalOpeEsp() throws Exception {
        final String origen = "SecurityAction.verificarSiClaveProvisonalOpeEsp";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u;
        String clave = new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            u=(VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
           cambiar = getSecurityServicio().verificarSiClaveProvisonalOpeEsp(u.getLogin());
           if((cambiar.substring(0, cambiar.indexOf("|")).trim().equalsIgnoreCase("S")) && (cambiar.substring(cambiar.indexOf("|")+1, cambiar.length()).equalsIgnoreCase("null") ))
            {
               cambiar = "N";
            }else if(cambiar.substring(0, cambiar.indexOf("|")).trim().equalsIgnoreCase("S")){
               cambiar = "S";
           }


            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String cargarCoordenadas() throws Exception {
        final String origen = "SecurityAction.cambiarClaveOpeEspPersonal";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario;
        String clave = new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario=(VBTUsersOd) session.get("user");

            idioma = (String) session.get("idioma");


            session.put("intentos_clave",0);

            SecurityCardOd tarjeta= getSecurityServicio().cargarCoordenadas(usuario.getLogin(),usuario.getIP());

            respuesta=tarjeta.getResultado();


            if (respuesta.equalsIgnoreCase("0")){
               coordenadas=tarjeta.getCoordenadas();
               filas=tarjeta.getTotalFilas();
               columnas=tarjeta.getTotaColumnas();
               serial=tarjeta.getSerial();
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String validarCoordenadas() throws Exception {
        final String origen = "SecurityAction.cambiarClaveOpeEspPersonal";
        long time;
        VBTUsersOd usuario;
        Gson gson = new Gson();
        Integer intentos_clave = 0;

        data = gson.fromJson(this.jsonTransfers, DataJson.class);
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario=(VBTUsersOd) session.get("user");

            idioma = (String) session.get("idioma");

            intentos_clave = (Integer) session.get("intentos_clave");

            if(intentos_clave<3){

                respuesta = getSecurityServicio().validarCoordenadas(usuario.getLogin(),usuario.getIP(), data.getParametros().get(1), data.getParametros().get(0), data.getParametros().get(2),data.getParametros().get(4), data.getParametros().get(3), data.getParametros().get(5));
                if(respuesta.equals("0")){
                    intentos_clave = 0;
                    session.put("intentos_clave",intentos_clave);
                    resultado="OK";
                }
                if(!respuesta.equals("0")){
                    if(intentos_clave==2){
                        resultado ="Cancel";
                        String bloqueo=getSecurityServicio().bloquearUsuario(usuario.getLogin());
                        if(idioma.equalsIgnoreCase("_us_en")) {
                            mensaje ="You have attempted three times to access with an incorrect code. Transaction will be cancelled and your user name blocked";
                        }else{
                            mensaje ="Ha ingresado por tercera vez unas coordenadas  erradas. Esta operación será cancelada y su usuario bloqueado";
                        }
                    }else{
                        resultado ="NO OK";
                        if(idioma.equalsIgnoreCase("_us_en")) {
                            mensaje ="Incorrect codes. Transaction will be cancelled after three incorrect attempts";
                        }else{
                            mensaje ="Coordenadas incorrectas. <br> Si se equivoca tres veces colocando sus coordenadas, la operaci&oacute;n ser&aacute; cancelada";
                        }
                        intentos_clave++;
                        session.put("intentos_clave",intentos_clave);
                    }
                }
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);




        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String cargarMetodos() throws Exception {
        final String origen = "SecurityAction.cambiarClaveOpeEspPersonal";
        long time;
        VBTUsersOd usuario;
        Gson gson = new Gson();
        Integer intentos_clave = 0;

        data = gson.fromJson(this.jsonTransfers, DataJson.class);
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario=(VBTUsersOd) session.get("user");

            idioma = (String) session.get("idioma");

           ServicioVbt vbt=new ServicioVbt();
           String resultado=vbt.getTelefonoONL(usuario.getLogin(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));

            if(!resultado.equalsIgnoreCase("0")){
              telefono="*******"+resultado.substring(resultado.length()-4,resultado.length());
              respuesta="OK";
            }else{
                respuesta=ResourceBundle.getBundle("Comun"+idioma).getString("TAGTelefonoActivo");;
            }


            resultado= getSecurityServicio().cargarTarjeta(usuario.getLogin());

            if ((!resultado.equalsIgnoreCase("NO OK"))&&(!resultado.equalsIgnoreCase("BLOQUEDA"))&&(!resultado.equalsIgnoreCase("VENCIDA"))){
                 serial=resultado;
                 mensaje="OK";
            }else{

                if (resultado.equalsIgnoreCase("BLOQUEDA"))
                    mensaje= ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG6");

                if (resultado.equalsIgnoreCase("VENCIDA"))
                    mensaje= ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG7");

                if (resultado.equalsIgnoreCase("NO OK"))
                    mensaje= ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSGNO");

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityAction.class+" | "+origen+" | "+time);




        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }




    //getters and Setters
    public ISecurityServicio getSecurityServicio() {
        return securityServicio;
    }

    public void setSecurityServicio(ISecurityServicio securityServicio) {
        this.securityServicio = securityServicio;
    }

    public FabricaTo getFabrica() {
        return fabrica;
    }

    public void setFabrica(FabricaTo fabrica) {
        this.fabrica = fabrica;
    }


    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSession(Map<String, Object> session) {
        //To change body of implemented methods use File | Settings | File Templates.
        this.session = session;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasTest() {
        return contenidoTabla_culumnasTest;
    }

    public void setContenidoTabla_culumnasTest(List<ContentsTableColumnsOd> contenidoTabla_culumnasTest) {
        this.contenidoTabla_culumnasTest = contenidoTabla_culumnasTest;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoTest() {
        return contenidoTabla_infoTest;
    }

    public void setContenidoTabla_infoTest(List<ContentTableInfoOd> contenidoTabla_infoTest) {
        this.contenidoTabla_infoTest = contenidoTabla_infoTest;
    }

    public ClientContext getContext() {
        return context;
    }

    public void setContext(ClientContext context) {
        this.context = context;
    }

    public String getCambiar() {
        return cambiar;
    }

    public void setCambiar(String cambiar) {
        this.cambiar = cambiar;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getJsonOpeEsp() {
        return jsonOpeEsp;
    }

    public void setJsonOpeEsp(String jsonOpeEsp) {
        this.jsonOpeEsp = jsonOpeEsp;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getJsonParametros() {
        return jsonParametros;
    }

    public void setJsonParametros(String jsonParametros) {
        this.jsonParametros = jsonParametros;
    }

    public String getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(String usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getJsonTransfers() {
        return jsonTransfers;
    }

    public void setJsonTransfers(String jsonTransfers) {
        this.jsonTransfers = jsonTransfers;
    }

    public String getNumReferencia() {
        return numReferencia;
    }

    public void setNumReferencia(String numReferencia) {
        this.numReferencia = numReferencia;
    }
}





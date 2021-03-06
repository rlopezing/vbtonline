package ve.com.vbtonline.vista.action.usuario;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import ve.com.vbtonline.servicio.util.MailManager365;
import com.venezolano.util.text.NullFormatter;
import com.venezolano.util.text.TextFormatter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.desingBank.IDesingBankServicio;
import ve.com.vbtonline.servicio.negocio.home.ILoginServicio;
import ve.com.vbtonline.servicio.negocio.security.ISecurityServicio;
import ve.com.vbtonline.servicio.od.*;

import org.apache.struts2.StrutsStatics;
import ve.com.vbtonline.vista.utils.CustomResourceBundleControl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 21/05/2010
 * Time: 11:33:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable,HttpSessionListener {
    private static final Logger logger = Logger.getLogger(LoginAction.class);
    private FabricaTo fabrica;
    private UsuarioOd usuario;
    private ILoginServicio loginServicio;
    private ISecurityServicio securityServicio;
    private IDesingBankServicio desingBankServicio;
    private String pwdClave;
    private String username;
    private String responseCaptcha;
    private String challengeCaptcha;
    private DataJson data=new DataJson();
    private Map session;
    private String mensaje;
    private String code;
    private List<ItemOd> menu;
    private String x;
    private String jsonLogin;
    private String jsonLoginOpeEsp;
    private String jsonOpcion;
    private String fechaLogueo;
    private String horalogueo;
    private String intentos;
    private String nombreUsuario;
    private String fechaHoy; private String horaHoy;
    private String codigoRandom;
    private String linkActualizacion;
    private String mostrarActualizacion;

    private String motivosAct;
    private Integer diasPlazo;
    private Integer diasRestantesPlazo;
    private Integer diasTranscurridos;


    private String fechaMesAnterior;
    private List<String> properties;
    private ArrayList<String> valor_es=new ArrayList<String>();
    //    private String prop_es="{";
    private ArrayList<String> valor_en=new ArrayList<String>();
    private ArrayList<String> valor_title_es=new ArrayList<String>();
    private ArrayList<String> valor_title_en=new ArrayList<String>();
    private Integer intentosOpeEsp;
    private String idioma;
    private List<CalendarioOd> listaFeriados;
    private String mes_parametro;
    private String ano_parametro;
    private String IpLocal;
    private boolean backoffice;
    private String cambioPass;
    private String tipo;
    private String usuarioAut;
    private String grupo;
    private String tipoContrato;
    private String cantidadSolicitudesPendientes;
    private String rolesCustom;
    private String cantidadSolicitudesPendientesLiberar;
    private String cantidadPagosTdcPendientes;
    private String cantidadPagosTdcPendientesLiberar;
    private List<String> infoHome;
    private String navegador;
    private Integer minutesTimeOut = 0;
    private String recoverUserExist;


    public String getNavegador() {
        return navegador;
    }

    public void setNavegador(String navegador) {
        this.navegador = navegador;
    }

    public List<String> getInfoHome() {
        return infoHome;
    }

    public void setInfoHome(List<String> infoHome) {
        this.infoHome = infoHome;
    }

    public String getCantidadSolicitudesPendientesLiberar() {
        return cantidadSolicitudesPendientesLiberar;
    }

    public void setCantidadSolicitudesPendientesLiberar(String cantidadSolicitudesPendientesLiberar) {
        this.cantidadSolicitudesPendientesLiberar = cantidadSolicitudesPendientesLiberar;
    }

    public String getRolesCustom() {
        return rolesCustom;
    }

    public void setRolesCustom(String rolesCustom) {
        this.rolesCustom = rolesCustom;
    }

    public String getIntentos() {
        return intentos;
    }

    public void setIntentos(String intentos) {
        this.intentos = intentos;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getUsuarioAut() {
        return usuarioAut;
    }

    public void setUsuarioAut(String usuarioAut) {
        this.usuarioAut = usuarioAut;
    }

    public String getResponseCaptcha() {
        return responseCaptcha;
    }

    public void setResponseCaptcha(String responseCaptcha) {
        this.responseCaptcha = responseCaptcha;
    }

    public String getChallengeCaptcha() {
        return challengeCaptcha;
    }

    public void setChallengeCaptcha(String challengeCaptcha) {
        this.challengeCaptcha = challengeCaptcha;
    }
    public String getCambioPass() {
        return cambioPass;
    }

    public void setCambioPass(String cambioPass) {
        this.cambioPass = cambioPass;
    }

    private List<PrivilegioOd> listaPrivilegios;

    public List<PrivilegioOd> getListaPrivilegios() {
        return listaPrivilegios;
    }

    public void setListaPrivilegios(List<PrivilegioOd> listaPrivilegios) {
        this.listaPrivilegios = listaPrivilegios;
    }

    public boolean isBackoffice() {
        return backoffice;
    }

    public void setBackoffice(boolean backoffice) {
        this.backoffice = backoffice;
    }

    public String getIpLocal() {
        return IpLocal;
    }

    public void setIpLocal(String ipLocal) {
        IpLocal = ipLocal;
    }

    public String getMes_parametro() {
        return mes_parametro;
    }

    public void setMes_parametro(String mes_parametro) {
        this.mes_parametro = mes_parametro;
    }

    public String getAno_parametro() {
        return ano_parametro;
    }

    public void setAno_parametro(String ano_parametro) {
        this.ano_parametro = ano_parametro;
    }

    public List<CalendarioOd> getListaFeriados() {
        return listaFeriados;
    }

    public void setListaFeriados(List<CalendarioOd> listaFeriados) {
        this.listaFeriados = listaFeriados;
    }

    public ArrayList<String> getValor_en() {
        return valor_en;
    }

    public void setValor_en(ArrayList<String> valor_en) {
        this.valor_en = valor_en;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Integer getMinutesTimeOut() {
        return minutesTimeOut;
    }

    public void setMinutesTimeOut(Integer minutesTimeOut) {
        this.minutesTimeOut = minutesTimeOut;
    }


    public String getCodigoRandom() {
        return codigoRandom;
    }

    public void setCodigoRandom(String codigoRandom) {
        this.codigoRandom = codigoRandom;
    }

    public LoginAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }

    public String execute() throws Exception {
        final String origen = "loginAction.execute";
        long time;
        VBTUsersOd usuario;
        String popup="popup";
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            if(session.get("idioma")==null)
                session.put("idioma","_us_en");

            if("EXISTE".equalsIgnoreCase(username)){
                logout();
                popup="json";
            }


            if("EXISTE".equalsIgnoreCase(pwdClave)){
                logout();
                popup="salir";
            }

            if(session.get("user")==null) {
                logout();
                popup="salir";
            }else{
               /* if(session.get("intentos")!=null) {
                    String validar=session.get("intentos").toString();
                  if(validar.equalsIgnoreCase("home")) {
                      session.put("intentos","salir");
                  }

                  if(validar.equalsIgnoreCase("salir")) {
                    logout();
                    popup="salir";
                  }
                }  */
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);


            mensaje="";
        }catch (Exception e){
            logger.error(e);
        }
        return popup;
    }

    public String mostrar() throws Exception {
        final String origen = "loginAction.execute";
        long time;
        VBTUsersOd usuario;
        String popup="popup";
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            if(session.get("idioma")==null)
                session.put("idioma","_us_en");

            if("EXISTE".equalsIgnoreCase(username)){
                logout();
                popup="json";
            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);


            mensaje="";
        }catch (Exception e){
            logger.error(e);
        }
        return popup;
    }

    public String abrir_vbtbank() throws Exception {
        final String origen = "loginAction.execute";
        long time;
        VBTUsersOd usuario;
        String popup="json";
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            logout();
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);


            mensaje="http://www.vbtbank.com/espanol/index.html";
        }catch (Exception e){
            logger.error(e);
        }
        return popup;
    }



    public String validarUsuario() throws Exception {
        final String origen = "loginAccion.validarUsuario";

        HttpServletResponse httpResponse =ServletActionContext.getResponse();
        httpResponse.addHeader("X-Frame-Options", "SAMEORIGIN");
        httpResponse.addHeader("Content-Security-Policy-Report-Only", "default-src 'self'; script-src 'self' 'unsafe-inline'; object-src 'none'; style-src 'self' 'unsafe-inline'; img-src 'self'; media-src 'none'; frame-src 'none'; font-src 'self'; connect-src 'self'; report-uri REDACTED");
        httpResponse.addHeader("X-Content-Security-Policy-Report-Only", "default-src 'self'; script-src 'self' 'unsafe-inline'; object-src 'none'; style-src 'self' 'unsafe-inline'; img-src 'self'; media-src 'none'; frame-src 'none'; font-src 'self'; connect-src 'self'; report-uri REDACTED");
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Origin", "http://localhost");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, *");
        httpResponse.setHeader("Access-Control-Max-Age", "86400");
        httpResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        System.out.println("---CORS Configuration Completed---");


        long time;
        String result = "json";


        VBTUsersOd usuario;
        VBTUsersOd usuario2;
        VBTUsersOd usuario3=new VBTUsersOd();
        String carteras;
        List<String> accionesSistema = new ArrayList<String>();
        backoffice=false;
        usuario = (VBTUsersOd) session.get("user");
        String errorTxt="";
        try {


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + LoginAction.class + " | " + origen);

            time = System.currentTimeMillis();

            String x = ResourceBundle.getBundle("package").getString("password");
            usuario = (VBTUsersOd) session.get("user");


            ServletContext sesionActual = ServletActionContext.getServletContext();

            Map<String,SessionActiva> loggedOnUsers = new HashMap<String, SessionActiva>();


            if(usuario!=null){
                session.remove("user");
                session.remove("carteras");
                session.remove("cuentasTOB");
                session.remove("intentosOpeEsp");

            }
            usuario = new VBTUsersOd();

            byte[] deencoding= Base64.decodeBase64(pwdClave.getBytes());


            usuario.setLogin(username.toLowerCase());
            usuario.setPassword(StringUtils.newStringUtf8(deencoding));
            usuario.setIP(IpLocal);
            // logger.info("iplocal: "+IpLocal);



            usuario3 = getLoginServicio().validarUsuario2(username, usuario);

            //validacion ambito

            //context.getRemoteAddr()
            intentos=String.valueOf(usuario3.getIntentos_login());
            if (usuario3.getIntentos_login() < 3) {
                usuario.setEmail(usuario3.getEmail());
                usuario = getLoginServicio().validarUsuario(usuario);
                if (usuario != null) {
                    usuario2 = getLoginServicio().cargarUsuario(usuario);
                    usuario2.setAmbito("A");
//                  usuario = usuario2;
                    if (("I".equals(usuario2.getAmbito()) &&
                            (!IpLocal.startsWith("172.23.") && !IpLocal.startsWith("172.30.")   &&
                                    !IpLocal.startsWith("10.1.2.") && !IpLocal.startsWith("192.168.223.")))
                            ||
                            ("E".equals(usuario3.getAmbito()) && (IpLocal.startsWith("172.23.")   ||
                                    IpLocal.startsWith("172.30.")  || IpLocal.startsWith("10.1.2.") || IpLocal.startsWith("192.168.223.")) )
                    ) { // El ambito es invalido
                        session.remove("user");
                        session.remove("carteras");
                        if(session.get("idioma")==null)
                            idioma = "_us_en";
                        else
                            idioma = (String) session.get("idioma");


                        if ("_us_en".equalsIgnoreCase(idioma)) {
                            //ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailIngreso")
                            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAmbito"));
                        } else    {
                            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAmbito"));
                        }
                        // context.putSessionObject("message","Cuenta de usuario invalida");
                        this.getLoginServicio().guardarLog(username,"15","1","1","",IpLocal,"El usuario est?? intentando entrar desde un ambito invalido");
                        //  logger.logAction(login,"7","1","1","",context.getRemoteAddr(),"El usuario est?? intentando entrar desde un ambito invalido");
                        /**********************************************************************/
                    }else{

                        //Valida que no este logueado en otro dispositivo
                        if (null!=sesionActual.getAttribute("usuarios")){
                            loggedOnUsers=(Map) sesionActual.getAttribute("usuarios");

                            if (loggedOnUsers.containsKey(username.toLowerCase())){

                                throw (new Exception ("SESSION DUPLICADA",null));

                            }else{
                                // String idSe= ActionContext.getContext().getSession().get;

                                SessionActiva elementoSession=new SessionActiva();
                                String formatoFechaLogin = (new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "us".toUpperCase()))).format(new Date());
                                String formatoHoraLogin = (new SimpleDateFormat("hh:mm:ss a", new Locale("en", "us".toUpperCase()))).format(new Date());

                                elementoSession.setTipoUsuario(usuario2.getNombreGrupo());

                                elementoSession.setLogin(username.toLowerCase());
                                elementoSession.setSessionId(ServletActionContext.getRequest().getSession().getId().toString());
                                elementoSession.setFechaLogin(formatoFechaLogin.toString());
                                elementoSession.setHoraLogin(formatoHoraLogin.toString());

                                elementoSession.setIp(IpLocal);

                                loggedOnUsers.put(username.toLowerCase(),elementoSession);
                                sesionActual.setAttribute("usuarios", loggedOnUsers);
                            }

                        } else{

                            if (loggedOnUsers.containsKey(username.toLowerCase())){
                                throw (new Exception ("SESSION DUPLICADA",null));
                            }else{


                                SessionActiva elementoSession=new SessionActiva();
                                String formatoFechaLogin = (new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "us".toUpperCase()))).format(new Date());
                                String formatoHoraLogin = (new SimpleDateFormat("hh:mm:ss a", new Locale("en", "us".toUpperCase()))).format(new Date());

                                elementoSession.setTipoUsuario(usuario2.getNombreGrupo());

                                elementoSession.setLogin(username.toLowerCase());
                                elementoSession.setSessionId(ServletActionContext.getRequest().getSession().getId().toString());
                                elementoSession.setFechaLogin(formatoFechaLogin.toString());
                                elementoSession.setHoraLogin(formatoHoraLogin.toString());

                                elementoSession.setIp(IpLocal);

                                loggedOnUsers.put(username.toLowerCase(),elementoSession);
                                sesionActual.setAttribute("usuarios",loggedOnUsers);
                            }
                        }





                        // hMap.containsKey("3")
                        usuario.setStatus_cuenta(usuario2.getStatus_cuenta());
                        usuario.setCodpercli(usuario2.getCodpercli());
                        usuario.setEmail(usuario2.getEmail());
                        usuario.setNumContrato(usuario2.getNumContrato());
                        usuario.setCodpercli(usuario2.getCodpercli());
                        usuario.setTipo(usuario2.getTipo());
                        usuario.setNombres(usuario2.getNombres());
                        usuario.setLast_login(usuario2.getLast_login());
                        usuario.setLast_login_hora(usuario2.getLast_login_hora());
                        usuario.setCambioPass(usuario2.getCambioPass());
                        usuario.setPassword_especiales(usuario2.getPassword_especiales());
                        usuario.setCirif(usuario2.getCirif());
                        usuario.setCambioPass(usuario2.getCambioPass());
                        usuario.setTelefono_celular(usuario2.getTelefono_celular());
                        usuario.setGrupo(usuario2.getGrupo());
                        usuario.setCodigoPais(usuario2.getCodigoPais());
                        usuario.setTipoContrato(usuario2.getTipoContrato());
                        usuario.setValidaSoporteContrato(usuario2.getValidaSoporteContrato());
                        usuario.setFechaAnuncio(usuario2.getFechaAnuncio());

                        cambioPass=usuario2.getCambioPass();

//                            if ((usuario.getTipo() != null) && ((usuario.getTipo().equalsIgnoreCase("2"))
//                                    || usuario.getTipo().equalsIgnoreCase("5")
//                                    || usuario.getTipo().equalsIgnoreCase("6")
//                                    || usuario.getTipo().equalsIgnoreCase("7")
//                                    || usuario.getTipo().equalsIgnoreCase("8"))) {
//                                usuario.setBackoffice(false);
//                            }else{
//                                usuario.setBackoffice(true);
//                            }


                        if ((usuario.getTipo() != null) && ((usuario.getTipo().equalsIgnoreCase("-1"))
                                || usuario.getTipo().equalsIgnoreCase("0")
                                || usuario.getTipo().equalsIgnoreCase("1"))) {
                            usuario.setBackoffice(true);
                        }else{
                            usuario.setBackoffice(false);
                        }


                        if (usuario.getStatus_cuenta().equalsIgnoreCase("A")) {
                            if ((usuario.getTipo() != null) && ((usuario.getTipo().equalsIgnoreCase("2"))
                                    || usuario.getTipo().equalsIgnoreCase("5")
                                    || usuario.getTipo().equalsIgnoreCase("6")
                                    || usuario.getTipo().equalsIgnoreCase("7")
                                    || usuario.getTipo().equalsIgnoreCase("8")
                                    || usuario.getTipo().equalsIgnoreCase("9")
                                    || usuario.getTipo().equalsIgnoreCase("11"))) {
                                carteras = getLoginServicio().cargarCarteras(usuario.getNumContrato(), usuario);
                                session.put("carteras", carteras);
                                tipo = usuario.getTipo();
                                tipoContrato=usuario.getTipoContrato();
                                idioma = new String();

                                if(session.get("idioma")!=null)
                                    idioma = (String) session.get("idioma");
                                else{
                                    idioma = "_us_en";
                                    session.put("idioma", idioma);
                                }
                                usuario.setTerminosCondiciones(getLoginServicio().validarTerminosCondiciones(usuario.getNumContrato(), usuario));
                            }else{
                                idioma = new String();
                                backoffice=true;
                                idioma = "_ve_es";
                                session.put("idioma", idioma);
                            }
                            Integer intentosOpeEsp =0;
                            session.put("intentosOpeEsp", intentosOpeEsp);
                            accionesSistema = getLoginServicio().cargarAccionesSistema(usuario);
                            session.put("accionesSistema", accionesSistema);
                            tipo = usuario.getTipo();
                            // TERMINOS Y CONDICIONES


                            session.put("user", usuario);

                            /*ServicioVbt vbt=new ServicioVbt();
                            String respuestaMsj=vbt.enviarAlertaONL("","texto","rafael.godoy","");*/

                            setMensaje("OK");
                            /*if ((usuario.getTipo().equalsIgnoreCase("2"))
                                    || usuario.getTipo().equalsIgnoreCase("5")
                                    || usuario.getTipo().equalsIgnoreCase("6")
                                    || usuario.getTipo().equalsIgnoreCase("7")
                                    || usuario.getTipo().equalsIgnoreCase("8")
                                    || usuario.getTipo().equalsIgnoreCase("9")
                                    || usuario.getTipo().equalsIgnoreCase("11")){*/
                            //this.getLoginServicio().guardarLogFC(getUsername(),"1","1",("2".equals(tipo) ? "2" : "1"),"",getIpLocal(),"Login Ok",usuario.getNumContrato());
                            this.getLoginServicio().guardarLogFC(getUsername(),"1","1", "1","",getIpLocal(),"Login Ok "+navegador,usuario.getNumContrato());
                            /*}else{
                                this.getLoginServicio().guardarLog(getUsername(),"1","1", "1","",getIpLocal(),"Login Ok "+navegador);
                            }*/

                            MailManager365 mailManager = new MailManager365("vbtonline");
                            ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "us".toUpperCase()));
                            SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a", new Locale("en", "us".toUpperCase()));


                            String htmlText = "";
                            htmlText = ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailIngreso") +
                                    "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                    "\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                    "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAgradecimiento");

                            //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar, pageConfigComun.getTagFieldValue("TAGMsgEmailIngreso"), htmlText);
                            mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailIngreso"), htmlText);


//                               this.getLoginServicio().guardarLogFC(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + usuario.getEmail()  , usuario.getNumContrato()) ;

//                            mailManager.sendPlainMail(rb.getString("adminmail"), "rafgoddark@gmail.com", ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailIngreso"), htmlText);
                        } else {
                            /*if ((usuario.getTipo().equalsIgnoreCase("2"))
                                    || usuario.getTipo().equalsIgnoreCase("5")
                                    || usuario.getTipo().equalsIgnoreCase("6")
                                    || usuario.getTipo().equalsIgnoreCase("7")
                                    || usuario.getTipo().equalsIgnoreCase("8")
                                    || usuario.getTipo().equalsIgnoreCase("9")){*/
                            this.getLoginServicio().guardarLogFC(getUsername(),"7","1","1","",getIpLocal(),"La cuenta no est?? activa o ha sido cancelada",usuario.getNumContrato());
                            /*}else{
                                // this.getLoginServicio().guardarLog(getUsername(),"7","1","1","",getIpLocal(),"La cuenta no est?? activa o ha sido cancelada");
                            }*/
                            session.remove("user");
                            session.remove("carteras");


                            if(session.get("idioma")==null){
                                idioma = "_us_en";
                            }
                            else{
                                idioma = (String) session.get("idioma");
                            }

                            if ("_us_en".equalsIgnoreCase(idioma))
                                setMensaje("Incorrect User name or Password. Try again. The third unsuccessful attempt, your login will be blocked");
                            else
                                setMensaje("Usuario o Clave Invalida. Intente nuevamente.  Al tercer intento fallido su usuario ser?? bloqueado");


                            MailManager365 mailManager = new MailManager365("vbtonline");

                            String strEmailEditar = usuario3.getEmail();

                            String htmlText = "";
                            htmlText = ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail003") + " " + usuario3.getNombres() +
                                    "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail001"), "\\n", "\n") +
                                    "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail002"), "\\n", "\n");


                            //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar,"VBT Bank & Trust Online Registration",htmlText);
                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), strEmailEditar, "VBT Bank & Trust Online Alert", htmlText);

//                                this.getLoginServicio().guardarLogFC(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + strEmailEditar , usuario.getNumContrato()) ;

                        }
                    }
                } else {
                    session.remove("user");
                    session.remove("carteras");
                    if(session.get("idioma")==null)
                        idioma = "_us_en";
                    else
                        idioma = (String) session.get("idioma");


                    if ("_us_en".equalsIgnoreCase(idioma))
                        setMensaje("Incorrect User name or Password. Try again.<br> The third unsuccessful attempt, your login will be blocked");
                    else
                        setMensaje("Usuario o Clave Invalida. Intente nuevamente.<br>  Al tercer intento fallido su usuario ser?? bloqueado");

                }

            } else {
                this.getLoginServicio().guardarLog(username,"15","1","1","",IpLocal,"3 intentos consecutivos fallidos" + navegador);
                session.remove("user");
                session.remove("carteras");
                if(session.get("idioma")==null)
                    idioma = "_us_en";
                else
                    idioma = (String) session.get("idioma");

                if ("_us_en".equalsIgnoreCase(idioma))
                    mensaje ="Incorrect User name or Password. Try again.<br> The third unsuccessful attempt, your login will be blocked";
                else
                    mensaje ="Usuario o Clave Invalida. Intente nuevamente.<br>  Al tercer intento fallido su usuario ser?? bloqueado";

                MailManager365 mailManager = new MailManager365("vbtonline");

                String strEmailEditar = usuario3.getEmail();

                String htmlText = "";
                htmlText = ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail003") + " " + usuario3.getNombres() +
                        "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail001"), "\\n", "\n") +
                        "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail002"), "\\n", "\n");


                //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar,"VBT Bank & Trust Online Registration",htmlText);
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), strEmailEditar, "VBT Bank & Trust Online Alert", htmlText);

//                   this.getLoginServicio().guardarLogFC(usuario3.getLogin(),"23","1","23", "",usuario3.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + strEmailEditar  , usuario3.getNumContrato()) ;
            }



            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + LoginAction.class + " | " + origen + " | " + time);



        } catch (Exception e) {

            if (!e.getMessage().equals("CLAVE INVALIDA")){
                logger.error(e);
            }

            if(session.get("idioma")==null)
                idioma = "_us_en";
            else
                idioma = (String) session.get("idioma");

            if ((usuario3.getIntentos_login()>=2)&&(e.getMessage().equals("CLAVE INVALIDA"))){
                errorTxt="CUENTA BLOQUEADA";
            }else{
                errorTxt=e.getMessage();
            }
            if(errorTxt.equals("SESSION DUPLICADA")){

                if ("_us_en".equalsIgnoreCase(idioma))
                    setMensaje("It seems that you already have an active session or your previous session ended improperly. \n Please wait 5 minutes before trying again.");
                else
                    setMensaje("Se ha detectado que ya posee una sesi??n activa o su sesi??n anterior finaliz?? de manera indebida. \n Por favor, espere 5 minutos antes de volver a intentar el acceso.");


            }else  if(errorTxt.equals("CUENTA BLOQUEADA")){
                this.getLoginServicio().guardarLog(getUsername(),"7","1","1","",getIpLocal(),"La cuenta no est?? activa o ha sido cancelada " + navegador);
                if ("_us_en".equalsIgnoreCase(idioma))
                    setMensaje("User is blocked");
                else
                    setMensaje("Su usuario se encuentra bloqueado");

            }else  if(errorTxt.equals("CUENTA INACTIVA")){
                this.getLoginServicio().guardarLog(getUsername(),"7","1","1","",getIpLocal(),"La cuenta no est?? activa o ha sido cancelada" + navegador);
                if ("_us_en".equalsIgnoreCase(idioma))
                    setMensaje("User inactive");
                else
                    setMensaje("Su usuario se encuentra inactivo");
            } else {
                this.getLoginServicio().guardarLog(getUsername(),"7","1","1","",getIpLocal(),"Usuario o Clave Invalida " + navegador);
                if ("_us_en".equalsIgnoreCase(idioma))
                    setMensaje("Incorrect User name or Password. Try again.<br> The third unsuccessful attempt, your login will be blocked");
                else
                    setMensaje("Usuario o Clave Invalida. Intente nuevamente<br>Al tercer intento fallido su usuario ser?? bloqueado");

            }
            session.remove("user");
            session.remove("carteras");
            if(session.get("idioma")==null)
                idioma = "_us_en";
            else
                idioma = (String) session.get("idioma");

            if(usuario3.getIntentos_login()==2){
                MailManager365 mailManager = new MailManager365("vbtonline");

                String strEmailEditar = usuario3.getEmail();

                String htmlText = "";
                htmlText = ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail003") + " " + usuario3.getNombres() +
                        "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail001"), "\\n", "\n") +
                        "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail002"), "\\n", "\n");


                //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar,"VBT Bank & Trust Online Registration",htmlText);
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), strEmailEditar, "VBT Bank & Trust Online Alert", htmlText);

//               this.getLoginServicio().guardarLogFC(usuario.getLogin(),"23","1","", "",usuario.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + strEmailEditar  , usuario.getNumContrato()) ;

            }

        }

        return result;
    }



    public String validarCaptcha() throws Exception {
        final String origen = "loginAccion.validarCaptcha";
        long time;
        String result = "json";
        VBTUsersOd usuario;
        VBTUsersOd usuario2;
        VBTUsersOd usuario3=new VBTUsersOd();
        String carteras;
        List<String> accionesSistema = new ArrayList<String>();
        backoffice=false;
        usuario = (VBTUsersOd) session.get("user");

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + LoginAction.class + " | " + origen);

            time = System.currentTimeMillis();



          /*  ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
            reCaptcha.setPrivateKey("6Lf0H-0SAAAAAPu5j9ODMgsn7zbvJe4rU8Ba2OQE");

            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(IpLocal, challengeCaptcha, responseCaptcha); */


            if ((responseCaptcha.toLowerCase()).equalsIgnoreCase(session.get("key").toString().toLowerCase())) {
                mensaje="OK";
            } else {
                mensaje="NO OK";
            }

        } catch (Exception e) {
            logger.error(e);
        }

        return SUCCESS;
    }





    public String logout(){
        final String origen = "LoginAction.logout";
        long time;
        UsuarioOd u;
        VBTUsersOd usuario = new VBTUsersOd();
        String retorno="salir";
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            usuario = (VBTUsersOd) session.get("user");
            if(usuario!=null){

                ServletContext sesionContexto = ServletActionContext.getServletContext();
                Map<String,SessionActiva> loggedOnUsers = new HashMap<String, SessionActiva>();
                loggedOnUsers=(Map) sesionContexto.getAttribute("usuarios");
                loggedOnUsers.remove(usuario.getLogin());
                sesionContexto.setAttribute("usuarios", loggedOnUsers);


                SessionMap sesionActual = (SessionMap) ActionContext.getContext().getSession();
                removerSoporte();
                session.remove("user");
                session.remove("carteras");
                session.remove("cuentasTOB");
                session.remove("intentosOpeEsp");
                session.remove("colocaciones");
                session.remove("intentos");
                session.clear();
                sesionActual.invalidate();
                idioma = (String) session.get("idioma");

                try{
                    String respuesta =  getLoginServicio().logout(usuario.getLogin());
                }catch (Exception e){
                    logger.error(e);
                }

                MailManager365 mailManager = new MailManager365("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("en","us".toUpperCase()));
                SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("en","us".toUpperCase()));


                if (null==idioma){
                    idioma = "_us_en";
                }else{
                    idioma = "_ve_es";
                }

                String IP_local = usuario.getIP();

                String htmlText = "";
                htmlText=  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailSalida") +
                        "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + IP_local+
                        "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAgradecimiento");

                //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar, pageConfigComun.getTagFieldValue("TAGMsgEmailIngreso"), htmlText);
                mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailSalida"), htmlText);

//                this.getLoginServicio().guardarLogFC(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Se ha enviado un correo  a los siguientes destinatarios: " + usuario.getEmail()  , usuario.getNumContrato()) ;

                //            mailManager.sendPlainMail(rb.getString("adminmail"),"rafgoddark@gmail.com", ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailSalida"), htmlText);

                time = System.currentTimeMillis () - time;
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);

                this.getLoginServicio().guardarLog(usuario.getLogin(),"1","1", "1","",usuario.getIP(),"Logout Ok");
                mensaje="";
            }
        }catch (Exception e){
            logger.error(e);
        }

        return retorno;
    }


    public String logout_session(String login){
        final String origen = "LoginAction.logout";
        long time;
        UsuarioOd u;
        VBTUsersOd usuario = new VBTUsersOd();
        String retorno="salir";
        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

        time = System.currentTimeMillis ();
        try{
            String respuesta =  getLoginServicio().logout(login);
        }catch (Exception e){
            logger.error(e);
        }


        return retorno;
    }


    public String logout_prueba(VBTUsersOd usuario){
        final String origen = "LoginAction.logout";
        long time;
        String retorno="salir";
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            idioma = (String) session.get("idioma");

            try{
                String respuesta =  getLoginServicio().logout(usuario.getLogin());
            }catch (Exception e){
                logger.error(e);
            }

            MailManager365 mailManager = new MailManager365("vbtonline");
            ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("en","us".toUpperCase()));
            SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("en","us".toUpperCase()));

            String IP_local = getUser();//ip como String

            String htmlText = "";
            htmlText=  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailSalida") +
                    "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + IP_local+
                    "\n\n" +  ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgAgradecimiento");

            //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar, pageConfigComun.getTagFieldValue("TAGMsgEmailIngreso"), htmlText);
            mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailSalida"), htmlText);
//            mailManager.sendPlainMail(rb.getString("adminmail"),"rafgoddark@gmail.com", ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgEmailSalida"), htmlText);



            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);


            mensaje="";

        }catch (Exception e){
            logger.error(e);
        }

        return retorno;
    }

    public String mostrarHome(){
        final String origen = "LoginAction.home";
        long time;
        String retorno="json";
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{
            time = System.currentTimeMillis ();
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            usuarioAut= NullFormatter.formatBlank(usuario.getLogin());

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return retorno;
    }


    public String home(){
        final String origen = "LoginAction.home";
        long time;
        VBTUsersOd u;
        String retorno="json";
        String estatus="";

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

//            x=ResourceBundle.getBundle("package").getString("password");
            u=new VBTUsersOd();
            u=(VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            session.put("intentos","home");
            menu=getLoginServicio().Home(idioma,u);
            grupo=u.getGrupo();
            cargarCalendario();
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            if(!NullFormatter.isBlank(usuario.getLast_login())){
                fechaLogueo= usuario.getLast_login().toString();
                horalogueo=usuario.getLast_login_hora();
            }else{
                fechaLogueo= "";
                horalogueo="";
            }

            nombreUsuario=usuario.getNombres();
            usuarioAut=usuario.getLogin();
            SimpleDateFormat formatoFecha;
            SimpleDateFormat formatoHora;
            if(idioma=="_us_en"){
                formatoFecha=new SimpleDateFormat("dd/MM/yyyy",new Locale("en","us".toUpperCase()));
                formatoHora=new SimpleDateFormat("hh:mm:ss a",new Locale("en","us".toUpperCase()));
            }else{
                formatoFecha=new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
                formatoHora=new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));
            }

            listaPrivilegios= getLoginServicio().consultarGrupoOpcPermisos(usuario);
            session.put("listaPrivilegiosLogin",listaPrivilegios);

            rolesCustom=getLoginServicio().consultarRolesAprobarLiberar(usuario);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -30);  // numero de d??as a a??adir, o restar en caso de d??as<0

            fechaHoy=formatoFecha.format(new Date()).toString();
            horaHoy=formatoHora.format(new Date()).toString();

            fechaMesAnterior=  formatoFecha.format(calendar.getTime()).toString();

            cambioPass=usuario.getCambioPass();
            tipo=usuario.getTipo();
            tipoContrato=usuario.getTipoContrato();
            backoffice=usuario.getBackoffice();

            infoHome=getLoginServicio().cargarInfoHome();

            if (u.getTipo().equalsIgnoreCase("7")) {
                estatus = "C";
                cantidadSolicitudesPendientes=this.contarSolicitudesPendientes(estatus);
                cantidadPagosTdcPendientes = this.contarPagosTdcPendientes(estatus);
            }else{
                if (u.getTipo().equalsIgnoreCase("8")) {
                    estatus = "A";
                    cantidadSolicitudesPendientes=this.contarSolicitudesPendientes(estatus);
                    cantidadPagosTdcPendientes = this.contarPagosTdcPendientes(estatus);
                }else{
                    if (u.getTipo().equalsIgnoreCase("11")) {
                        estatus = "C";
                        cantidadSolicitudesPendientes=this.contarSolicitudesPendientes(estatus);
                        cantidadPagosTdcPendientes = this.contarPagosTdcPendientes(estatus);
                        estatus = "A";
                        cantidadSolicitudesPendientesLiberar=this.contarSolicitudesPendientes(estatus);
                        cantidadPagosTdcPendientesLiberar = this.contarPagosTdcPendientes(estatus);

                    }
                }
            }

            String estatusAct = "";
            boolean actualizar = false;
            PrivilegioOd privilegio = null;
            for (int i =0 ; i< listaPrivilegios.size() ; i++   ){
                privilegio = (PrivilegioOd) listaPrivilegios.get(i);
                if(privilegio.getOpcion().equals("0000000023")) {
                    if (privilegio.getPrivilegios().get(2).equals("1")) {
                        actualizar = true;
                    }
                }
            }

            if(actualizar == true) {
                if((!NullFormatter.isBlank(u.getCodpercli()) )|| (u.getTipoContrato().equalsIgnoreCase("FC") && !(u.getGrupo().equalsIgnoreCase("0000000016")))){
                    estatusAct = loginServicio.validarAct(usuario);
                    linkActualizacion = (String) session.get("linkActualizacion");
                    mostrarActualizacion = ((String) session.get("mostrarActualizacion"));
                    motivosAct = ((String)  session.get("motivosAct"));

                    if(("CAR").equals(estatusAct)||("NO_ACT").equals(estatusAct)){
                        mostrarActualizacion = "2";
                        if(NullFormatter.isBlank(u.getFechaAnuncio())){
                            usuario.setFechaAnuncio(loginServicio.updateFechaAnuncio(usuario));
                            session.put("user", usuario);
                        }
                        if(("CAR").equals(estatusAct)){
                            motivosAct =(String) loginServicio.consultarMotvosAct(usuario.getNumContrato());
                        }else{
                            if(loginServicio.validarExcluidoAct(usuario).equals("EXC") )
                                mostrarActualizacion = "0";
                        }
                    }else if (("VER").equals(estatusAct)){
                        mostrarActualizacion = "0";
                    }else if (("ACT").equals(estatusAct)){
                        mostrarActualizacion ="0";
                    }

                    //   Calculo de dias
                    ParametrosPersonalesOd parametrosGlobales = new ParametrosPersonalesOd();
                    parametrosGlobales=loginServicio.cargarParametrosContratos(usuario.getNumContrato(), usuario);
                    diasPlazo = Integer.parseInt(parametrosGlobales.getAct_plazo());
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                    Date dateAnuncio = formato.parse(usuario.getFechaAnuncio());
                    Date dateActual = new Date();
                    diasTranscurridos = (int) (long) ((dateActual.getTime() - dateAnuncio.getTime()) / (60 * 60 * 1000 * 24));
                    diasRestantesPlazo = ((diasPlazo -  diasTranscurridos) < 0)? 0:(diasPlazo -  diasTranscurridos);
                    session.put("diasRestantesPlazo",diasRestantesPlazo);
                    //
                    if(!getMostrarActualizacion().equals("0")){
                        //if(NullFormatter.isBlank(linkActualizacion) ){

                        codigoRandom = Base64.encodeBase64(Long.toString(numbGen()).getBytes()).toString();
                        loginServicio.registrarCodigoAct(u,codigoRandom);
                        linkActualizacion = ResourceBundle.getBundle("vbtonline").getString("linkCustomers").toString() + "login?code=" + codigoRandom;
                        this.setLinkActualizacion(linkActualizacion);
                        session.put("linkActualizacion",linkActualizacion);
                        //}
                    }
                    this.setMostrarActualizacion(mostrarActualizacion);
                    session.put("motivosAct",motivosAct);
                    session.put("mostrarActualizacion", getMostrarActualizacion());
                }
            }else{
                if( (u.getTipoContrato().equalsIgnoreCase("FC") && !(u.getGrupo().equalsIgnoreCase("0000000016")))){
                    estatusAct = loginServicio.validarAct(usuario);

                    if(("CAR").equals(estatusAct)||("NO_ACT").equals(estatusAct)){
                        if(loginServicio.validarExcluidoAct(usuario).equals("EXC") )
                            mostrarActualizacion = "0";
                        else
                            mostrarActualizacion = "3";


                        if(NullFormatter.isBlank(u.getFechaAnuncio())){
                            usuario.setFechaAnuncio(loginServicio.updateFechaAnuncio(usuario));
                            session.put("user", usuario);
                        }
                    }

                    //   Calculo de dias
                    ParametrosPersonalesOd parametrosGlobales = new ParametrosPersonalesOd();
                    parametrosGlobales=loginServicio.cargarParametrosContratos(usuario.getNumContrato(), usuario);
                    diasPlazo = Integer.parseInt(parametrosGlobales.getAct_plazo());
                    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
                    Date dateAnuncio = formato.parse(usuario.getFechaAnuncio());
                    Date dateActual = new Date();
                    diasTranscurridos = (int) (long) ((dateActual.getTime() - dateAnuncio.getTime()) / (60 * 60 * 1000 * 24));
                    diasRestantesPlazo = ((diasPlazo -  diasTranscurridos) < 0)? 0:(diasPlazo -  diasTranscurridos);

                    //
                    session.put("diasRestantesPlazo",diasRestantesPlazo);
                    session.put("mostrarActualizacion", mostrarActualizacion);
                }
            }


            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);


        }catch (Exception e){
            logger.error(e);
        }

        return retorno;
    }

    public String mostrarPantalla() throws Exception {
        final String origen = "loginAccion.mostrarPantalla";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario;
        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);
            time = System.currentTimeMillis ();
            usuario=(VBTUsersOd)session.get("user");
            if(terminosCondiciones(jsonOpcion,usuario)){ // acepto terminos y condiciones
                if(usuario.getPassword_ope()==null){
                    if(transccionSegura(jsonOpcion) ){
                        setMensaje("VALIDAR");
                    }else{
                        setMensaje("OK");
                    }
                }else{
                    setMensaje("OK");
                }
            }else{
                setMensaje("TERMINOS_CONDICIONES");
            }

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String cargarProperties() throws Exception {
        final String origen = "loginAccion.cargarProperties";
        long time;
        String result=new String();
        result = "json";
        VBTUsersOd usuario=new VBTUsersOd();

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            /**/

//            Properties genericProperties = new Properties();
            ResourceBundle bundle = ResourceBundle.getBundle("vbtonline_ve_es", new CustomResourceBundleControl("UTF-8"));
            Enumeration claves = bundle.getKeys();
            while (claves.hasMoreElements()) {
                String clave = (String) claves.nextElement();
                String valor = bundle.getString(clave);
                valor_es.add(clave.trim()+"="+valor.trim());
//                genericProperties.put(clave, valor);
            }
            ResourceBundle bundle2 = ResourceBundle.getBundle("vbtonline_us_en", new CustomResourceBundleControl("UTF-8"));
            Enumeration claves2 = bundle2.getKeys();
            while (claves2.hasMoreElements()) {
                String clave2 = (String) claves2.nextElement();
                String valor2 = bundle2.getString(clave2);
                valor_en.add(clave2.trim()+"="+valor2.trim());
//                genericProperties.put(clave, valor);
                System.out.println(clave2.trim()+"="+valor2.trim());
            }

            ResourceBundle bundle3 = ResourceBundle.getBundle("title_us_en", new CustomResourceBundleControl("UTF-8"));
            Enumeration claves3 = bundle3.getKeys();
            while (claves3.hasMoreElements()) {
                String clave3 = (String) claves3.nextElement();
                String valor3 = bundle3.getString(clave3);
                valor_title_en.add(clave3.trim()+"="+valor3.trim());
//                genericProperties.put(clave, valor);
            }

            bundle3 = ResourceBundle.getBundle("title_ve_es", new CustomResourceBundleControl("UTF-8"));
            claves3 = bundle3.getKeys();
            while (claves3.hasMoreElements()) {
                String clave3 = (String) claves3.nextElement();
                String valor3 = bundle3.getString(clave3);
                valor_title_es.add(clave3.trim()+"="+valor3.trim());
//                genericProperties.put(clave, valor);
            }

            if(session.get("user")==null){
                idioma="_us_en";
            }else{
                idioma=session.get("idioma").toString();
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public String cambiarIdioma() throws Exception {
        final String origen = "loginAccion.cambiarIdioma";
        long time;
        idioma = new String();
        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonLogin, DataJson.class);
//            String valores = data.getParametros().get(1);
            if(data.getParametros().get(0).equalsIgnoreCase("es")){
                idioma = "_ve_es";
                session.put("idioma",idioma);
            }else {
                idioma = "_us_en";
                session.put("idioma",idioma);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public boolean transccionSegura(String jsonOpcion) throws Exception {
        boolean validar=false;
        if("TRANSFERENCIA_EXTERNA".equalsIgnoreCase(jsonOpcion)){
            validar=true;
        } else if("TRANSFERENCIA_EXTERNA_FC".equalsIgnoreCase(jsonOpcion)){
            validar=true;
        }else if("DIRECTORIO".equalsIgnoreCase(jsonOpcion)){
            validar=true;
        }else if("PARAMETROS_PERSONALES".equalsIgnoreCase(jsonOpcion)){
            validar=true;
        }

        // SE ELIMINA LA PANTALLA DE VALIDACIONES ESPECIALES
        return false;
    }

    public String loginOpeEsp() throws Exception {
        final String origen = "loginAccion.loginOpeEsp";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u = new VBTUsersOd();
        Integer intentos=0;
        idioma = new String();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String x=ResourceBundle.getBundle("package").getString("password");
            idioma = (String) session.get("idioma");
            u=(VBTUsersOd) session.get("user");
            intentosOpeEsp = (Integer) session.get("intentosOpeEsp");

            byte[] deencoding= Base64.decodeBase64(jsonLoginOpeEsp.getBytes());
            System.out.println("clave deencoding: "+ StringUtils.newStringUtf8(deencoding));


            u.setPassword_ope(StringUtils.newStringUtf8(deencoding));
            u = getLoginServicio().loginOpeEsp(u);
            if(u!=null){
                session.put("user",u);
                setCode("OK");
                getLoginServicio().guardarLog(u.getLogin(),"1","1","12","",u.getIP(), "Login OK");

            }else{
                setCode("ERROR");
                intentosOpeEsp++;
                getLoginServicio().guardarLog(u.getLogin(),"1","1","12","",u.getIP(), "Login Fallido, intento consecutivo: "+intentosOpeEsp);
                setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsg2OpeEsp"));
            }

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            intentosOpeEsp++;
            session.put("intentosOpeEsp",intentosOpeEsp);
            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsg2OpeEsp"));
            u.setPassword_ope(null);
            if(intentosOpeEsp==3){
                MailManager365 mailManager = new MailManager365("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");
                getLoginServicio().guardarLog(u.getLogin(),"1","1","12","",u.getIP(), "Clave bloqueada por 3 intentos fallidos");
                String htmlText = "";
                htmlText=ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail003") + " " + u.getNombres() +
                        "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmailOpeEsp001"),"\\n","\n") +
                        "\n\n" + TextFormatter.replaceString(ResourceBundle.getBundle("Login"+idioma).getString("TAGMsgEmail002"),"\\n","\n") + "\n\n";


                //mailManager.sendHtmlMail(rb.getString("adminmail"),strEmailEditar,"VBT Bank & Trust Online Registration",htmlText);
                mailManager.sendPlainMail(rb.getString("adminmail"),u.getEmail(), "VBT Bank & Trust Online Alert", htmlText);
//                this.getLoginServicio().guardarLogFC(u.getLogin(),"23","1","23", "",u.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + u.getEmail()  , u.getNumContrato()) ;
            }
        }
        return result;
    }

    public String cargarCalendario() throws Exception {
        final String origen = "loginAccion.cargarCalendario";
        long time;
        String result=new String();
        result = SUCCESS;
        VBTUsersOd usuario,u = new VBTUsersOd();
        Integer intentos=0;
        idioma = new String();
        listaFeriados = new ArrayList<CalendarioOd>();
        String dia = "";
        String language;
        String locale;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonLogin, DataJson.class);
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            /**/
            int mes2 = 0;
            int anio = 0;

            //Obtengo la fecha actual
            Calendar miCalendario = Calendar.getInstance();
            Calendar miCalendarioTemp = Calendar.getInstance();

            //Declaro los formatos de fecha a usar
            SimpleDateFormat formato_dia=new SimpleDateFormat("EEEE",new Locale("es","VE"));
            if("_us_en".equalsIgnoreCase(idioma)){
                language = "en";
                locale = "US";
            }else{
                language = "es";
                locale = "VE";
            }

            SimpleDateFormat formato_mes=new SimpleDateFormat("MMMM",new Locale(language,locale));
            SimpleDateFormat formato_a??o=new SimpleDateFormat("yyyy",new Locale("es","VE"));
            SimpleDateFormat formato_mes_numerico=new SimpleDateFormat("MM",new Locale("es","VE"));
            SimpleDateFormat formato_mes_actual=new SimpleDateFormat("MM",new Locale("es","VE"));

            int diaActual = miCalendario.get(Calendar.DAY_OF_MONTH);
            miCalendario.set(Calendar.DAY_OF_MONTH,1);

            //Seteo el mes y la fecha seleccionadas
            if (!NullFormatter.isBlank(ano_parametro)) {
                anio = Integer.parseInt(ano_parametro);
                miCalendario.set(Calendar.YEAR,anio);
                miCalendarioTemp.set(Calendar.YEAR,anio);
            }
            else {
                anio = Integer.parseInt(formato_a??o.format(miCalendario.getTime()));
            }

            if (!NullFormatter.isBlank(mes_parametro)) {
                mes2 = Integer.parseInt(mes_parametro);
                miCalendario.set(Calendar.MONTH,mes2);
                miCalendarioTemp.set(Calendar.MONTH,mes2);
            }
            else {
                mes2 = Integer.parseInt(formato_mes_numerico.format(miCalendario.getTime())) - 1;
            }

            //Obtengo el fin de mes
            int fin_mes = miCalendario.getActualMaximum(Calendar.DAY_OF_MONTH);

            if (diaActual > fin_mes) {
                miCalendario.set(Calendar.DAY_OF_MONTH,fin_mes);
            }
            else {
                miCalendario.set(Calendar.DAY_OF_MONTH,diaActual);
            }



            listaFeriados = getLoginServicio().cargarCalendario(mes2,anio,fin_mes, usuario);

            result = SUCCESS;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
//            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsg2OpeEsp"));

        }
        return result;
    }


    public String forgotPassword() throws Exception {
        final String origen = "loginAccion.forgotPassword";
        long time;
        String result = "";
        String existe = "";

        HttpServletRequest wrapper= ServletActionContext.getRequest();

        String username2 = wrapper.getParameter("username2");
        String correo = wrapper.getParameter("correo");

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + LoginAction.class + " | " + origen);

            time = System.currentTimeMillis();

            existe = getLoginServicio().validarUsuarioCorreo(username2, correo);

            this.setRecoverUserExist(existe);

            mensaje = "0";

            result = SUCCESS;

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + LoginAction.class + " | " + origen + " | " + time);



        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }


    public String latido() throws Exception {
        try{
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            if(usuario==null){
                this.mensaje="expiro la sessi??n";
            } else{
                this.mensaje="LATIDO";
            }
        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String minutesTimeOut() throws Exception {
        try{
            minutesTimeOut = Integer.parseInt(ResourceBundle.getBundle("vbtonline").getString("timeOut").toString());

        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }



    public ILoginServicio getLoginServicio() {
        return this.loginServicio;
    }

    public void setLoginServicio(ILoginServicio loginServicio) {
        this.loginServicio = loginServicio;
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

    public UsuarioOd getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioOd usuario) {
        this.usuario = usuario;
    }

    public String getPwdClave() {
        return pwdClave;
    }

    public void setPwdClave(String pwdClave) {
        this.pwdClave = pwdClave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public List<ItemOd> getMenu() {
        return menu;
    }

    public void setMenu(List<ItemOd> menu) {
        this.menu = menu;
    }


    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getJsonLogin() {
        return jsonLogin;
    }

    public void setJsonLogin(String jsonLogin) {
        this.jsonLogin = jsonLogin;
    }

    public String getJsonLoginOpeEsp() {
        return jsonLoginOpeEsp;
    }

    public void setJsonLoginOpeEsp(String jsonLoginOpeEsp) {
        this.jsonLoginOpeEsp = jsonLoginOpeEsp;
    }



    public String getJsonOpcion() {
        return jsonOpcion;
    }

    public void setJsonOpcion(String jsonOpcion) {
        this.jsonOpcion = jsonOpcion;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFechaLogueo() {
        return fechaLogueo;
    }

    public void setFechaLogueo(String fechaLogueo) {
        this.fechaLogueo = fechaLogueo;
    }

    public String getHoralogueo() {
        return horalogueo;
    }

    public void setHoralogueo(String horalogueo) {
        this.horalogueo = horalogueo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(String fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getHoraHoy() {
        return horaHoy;
    }

    public void setHoraHoy(String horaHoy) {
        this.horaHoy = horaHoy;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public ArrayList<String> getValor_es() {
        return valor_es;
    }

    public void setValor_es(ArrayList<String> valor_es) {
        this.valor_es = valor_es;
    }

    public ISecurityServicio getSecurityServicio() {
        return securityServicio;
    }

    public void setSecurityServicio(ISecurityServicio securityServicio) {
        this.securityServicio = securityServicio;
    }

    public Integer getIntentosOpeEsp() {
        return intentosOpeEsp;
    }

    public void setIntentosOpeEsp(Integer intentosOpeEsp) {
        this.intentosOpeEsp = intentosOpeEsp;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public IDesingBankServicio getDesingBankServicio() {
        return desingBankServicio;
    }

    public void setDesingBankServicio(IDesingBankServicio desingBankServicio) {
        this.desingBankServicio = desingBankServicio;
    }

    public ArrayList<String> getValor_title_es() {
        return valor_title_es;
    }

    public void setValor_title_es(ArrayList<String> valor_title_es) {
        this.valor_title_es = valor_title_es;
    }

    public ArrayList<String> getValor_title_en() {
        return valor_title_en;
    }

    public void setValor_title_en(ArrayList<String> valor_title_en) {
        this.valor_title_en = valor_title_en;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void sessionCreated(HttpSessionEvent arg0) {

        System.out.println("***** Session creada");
        ServletContext contexto = arg0.getSession().getServletContext();
        ServletContext sesionActual = ServletActionContext.getServletContext();
//        Map<String,String> loggedOnUsers = new HashMap<String, String>();
//        sesionActual.setAttribute("usuarios", loggedOnUsers);

    }

    public void sessionDestroyed(HttpSessionEvent arg0) {

        VBTUsersOd usuario;
        System.out.println("*** Session destruida");
        usuario=(VBTUsersOd)arg0.getSession().getAttribute("user");


        try{

            ServletContext sesionContexto =  arg0.getSession().getServletContext();
            Map<String,SessionActiva> loggedOnUsers = new HashMap<String, SessionActiva>();
            loggedOnUsers=(Map) sesionContexto.getAttribute("usuarios");


            Iterator it = loggedOnUsers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry)it.next();


                SessionActiva elementoSession = (SessionActiva) e.getValue();
                if ( elementoSession.getSessionId().equals(arg0.getSession().getId().toString())){
                    loggedOnUsers.remove(e.getKey());
                }


            }

            sesionContexto.setAttribute("usuarios", loggedOnUsers);
        } catch (Exception e) {

        }



        if(usuario!=null){
            System.out.println("se ha cerrado la session del usuario : "+usuario.getLogin());
            try {
                LoginAction log= new LoginAction();
                log.logout_session(usuario.getLogin());
                if(usuario!=null){

                    session.remove("user");
                    session.remove("carteras");
                    session.remove("cuentasTOB");
                    session.remove("intentosOpeEsp");
                    SessionMap sesionActual = (SessionMap) ActionContext.getContext().getSession();
                    sesionActual.invalidate();
                }
            } catch (Exception e) {
                //e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

    public String getUser(){
        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException ex) {
            return "Error al retornar el nombre de usuario: " + ex;
        }
    }


    public boolean terminosCondiciones(String jsonOpcion, VBTUsersOd usuario){
        boolean validar=true;
        if(("TRANSFERENCIA_EXTERNA".equalsIgnoreCase(jsonOpcion))||
                ("TRANSFERENCIA_EXTERNA_FC".equalsIgnoreCase(jsonOpcion))||
                ("TRANSFERENCIA_INTERNA_MIS_CTAS".equalsIgnoreCase(jsonOpcion))||
                ("DIRECTORIO".equalsIgnoreCase(jsonOpcion))||
                ("TRANSFERENCIA_EXTERNA_FC".equalsIgnoreCase(jsonOpcion))||
                ("TRANSFERENCIA_INTERNA_TERCEROS".equalsIgnoreCase(jsonOpcion))){
            if(!usuario.isTerminosCondiciones()){
                validar=false;
            }
        }
        return validar;
    }

    public String contarPagosTdcPendientes(String estatus) throws Exception {
        final String origen = "LoginAction.contarPagosTdcPendientes";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        String cantidad = "";
        String carteras = (String) session.get("carteras");
        carteras = carteras.replaceAll("\'", "");

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");

            cantidad = getLoginServicio().contarPagosTdcPendientes(carteras, usuario, estatus);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return cantidad;
    }


    /**
     *      FUNCI??N:        contarSolicitudesPendientes()
     *      AUTOR:          RRANGEL
     *      FECHA:          10/10/2014
     *      DESCRIPCI??N:    Obtiene el contrato y el tipo de usuario. Cuando el usuario es Aprobador (9) asigna el valor
     *                      'C' a la variable estatus, cuando es Liberador (8) asigna 'A' a dicha variable.
     *      CAMBIOS:        Se
     * */
    public String contarSolicitudesPendientes(String estatus) throws Exception {
        final String origen = "FirmasConjuntasAction.contarSolicitudesPendientes";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        String cantidad = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+LoginAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");

            cantidad = getLoginServicio().contarSolicitudesPendientes(usuario, usuario.getNumContrato(),estatus);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+LoginAction.class+" | "+origen+" | "+time);

        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return cantidad;
    }
    /** FIN contarSolicitudesPendientes() */


    public String getCantidadSolicitudesPendientes() {
        return cantidadSolicitudesPendientes;
    }

    public void setCantidadSolicitudesPendientes(String cantidadSolicitudesPendientes) {
        this.cantidadSolicitudesPendientes = cantidadSolicitudesPendientes;
    }
    public String getFechaMesAnterior() {
        return fechaMesAnterior;
    }

    public void setFechaMesAnterior(String fechaMesAnterior) {
        this.fechaMesAnterior = fechaMesAnterior;
    }
//    public String getProp_es() {
//        return prop_es;
//    }
//
//    public void setProp_es(String prop_es) {
//        this.prop_es = prop_es;
//    }


    public String getCantidadPagosTdcPendientes() {
        return cantidadPagosTdcPendientes;
    }

    public void setCantidadPagosTdcPendientes(String cantidadPagosTdcPendientes) {
        this.cantidadPagosTdcPendientes = cantidadPagosTdcPendientes;
    }

    public String getCantidadPagosTdcPendientesLiberar() {
        return cantidadPagosTdcPendientesLiberar;
    }

    public void setCantidadPagosTdcPendientesLiberar(String cantidadPagosTdcPendientesLiberar) {
        this.cantidadPagosTdcPendientesLiberar = cantidadPagosTdcPendientesLiberar;
    }

    public String removerSoporte() throws Exception {
        final String origen = "transfersAccion.borrarSoporte";
        long time;
        String result = "";
        String sessionIdFolder = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + LoginAction.class + " | " + origen);

            time = System.currentTimeMillis();

            if(session.get("sessionIdFolder") != null){
                sessionIdFolder = (String) session.get("sessionIdFolder");
                String filePath = ResourceBundle.getBundle("vbtonline").getString("carpetaTemporal") + sessionIdFolder;
                File file = new File(filePath);
                Files.exists(file.toPath());
                FileUtils.deleteDirectory(file);
                getLoginServicio().guardarLog(username,"15","1","1","",IpLocal,"Se ha eliminado el siguiente directorio: "+file.getPath() );
            }

            mensaje = "0";

            result = SUCCESS;

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + LoginAction.class + " | " + origen + " | " + time);

        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return result;
    }

    public static long numbGen() {
        while (true) {
            long numb = (long)(Math.random() * 100000000 * 1000000); // had to use this as int's are to small for a 13 digit number.
            if (String.valueOf(numb).length() == 13)
                return numb;
        }
    }


    public String getLinkActualizacion() {
        return linkActualizacion;
    }

    public void setLinkActualizacion(String linkActualizacion) {
        this.linkActualizacion = linkActualizacion;
    }

    public String getMostrarActualizacion() {
        return mostrarActualizacion;
    }

    public void setMostrarActualizacion(String mostrarActualizacion) {
        this.mostrarActualizacion = mostrarActualizacion;
    }

    public Integer getDiasPlazo() {
        return diasPlazo;
    }

    public void setDiasPlazo(Integer diasPlazo) {
        this.diasPlazo = diasPlazo;
    }

    public Integer getDiasRestantesPlazo() {
        return diasRestantesPlazo;
    }

    public void setDiasRestantesPlazo(Integer diasRestantesPlazo) {
        this.diasRestantesPlazo = diasRestantesPlazo;
    }

    public Integer getDiasTranscurridos() {
        return diasTranscurridos;
    }

    public void setDiasTranscurridos(Integer diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    public String getMotivosAct() {
        return motivosAct;
    }

    public void setMotivosAct(String motivosAct) {
        this.motivosAct = motivosAct;
    }

    public String getRecoverUserExist() {
        return recoverUserExist;
    }

    public void setRecoverUserExist(String recoverUserExist) {
        this.recoverUserExist = recoverUserExist;
    }
}






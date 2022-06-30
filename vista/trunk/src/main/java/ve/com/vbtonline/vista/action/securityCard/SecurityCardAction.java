package ve.com.vbtonline.vista.action.securityCard;

import com.itextpdf.text.pdf.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.securityCard.ISecurityCardServicio;
import ve.com.vbtonline.servicio.od.*;

import javax.servlet.ServletContext;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.apache.commons.codec.binary.Base64.decodeBase64;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 20/09/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityCardAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {

    private static final Logger logger = Logger.getLogger(SecurityCardAction.class);
    private FabricaTo fabrica;
    private ISecurityCardServicio securityCardServicio;

    private String mensaje;
    private Map session;
    private PdfWriter docWriter = null;
    private InputStream inputStream;
    private String idioma;
    private String codigo;   // 1 error 0 ok
    private String usuario;
    private String cirif;
    private String serial;
    private String status;

    private int  TAM_LETRA_CONTENIDO = 10;

    public SecurityCardAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }

    public String cargarValoresGeneracion() throws Exception {
        final String origen = "SecurityCardAction.cargarValoresGeneracion";
        long time;
        AccountsOd account;

        ContentSelectOd campo = new ContentSelectOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ SecurityCardAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            VBTUsersOd user = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            setUsuario(user.getNombres());
            setCirif(user.getCirif());

            setCodigo("0");

            time = System.currentTimeMillis () - time;
            this.GuardarLog(user.getLogin(),"3","1","19","",user.getIP(),"Ingresa al modulo de Generacion de Tarjeta de coordenadas");

            SecurityCardOd tarjeta =  getSecurityCardServicio().consultarTarjetaAsignada(user.getLogin(), user);
                serial = tarjeta.getSerialPantalla();
                setStatus(tarjeta.getStatus());

//            if(tarjeta.getStatus().equals("AC")){
            if("AC".equals(tarjeta.getStatus())){
                logger.info(   ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG20"));
                setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG20"));
            }

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            setCodigo("1");
        }
        return SUCCESS;
    }


    public String cargarValoresActivacion() throws Exception {

        final String origen = "SecurityCardAction.cargarValoresActivacion";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ SecurityCardAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            VBTUsersOd user = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            SecurityCardOd tarjeta =  getSecurityCardServicio().consultarTarjetaAsignada(user.getLogin(), user);

            setUsuario(user.getNombres());
            setCirif(user.getCirif());
            setSerial(tarjeta.getSerialPantalla());

            session.put("tarjeta",tarjeta);
            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG"+tarjeta.getResultado()));
            /*if(tarjeta.getSerial().length()==0){
                setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG2"));
            } else if(tarjeta.getStatus().equals("AC"))     {
                 setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG3"));
            } */
            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG"+tarjeta.getResultado()));

            if(tarjeta.getResultado().equals("5"))
            setCodigo("0");
            else
                setCodigo("1" +
                        "");

            this.GuardarLog(user.getLogin(),"3","1","19","",user.getIP(),"Ingresa al modulo de activacion de Tarjeta de coordenadas");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            setCodigo("1");
        }
        return SUCCESS;
    }

    public String activarTarjeta() throws Exception {
        final String origen = "SecurityCardAction.activarTarjeta";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+ SecurityCardAction.class+" | "+origen);
            time = System.currentTimeMillis ();

            VBTUsersOd user = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");

            SecurityCardOd tarjeta = (SecurityCardOd)session.get("tarjeta");

            String resultado = getSecurityCardServicio().activarTarjeta(tarjeta, user.getLogin(), user.getIP(), user);
            setMensaje(ResourceBundle.getBundle("Comun"+idioma).getString("SecurityCard_MSG"+resultado));

            setCodigo("0");

            this.GuardarLog(user.getLogin(),"9","1","19","",user.getIP(),"Activacion de TCO. Resultado=" +ResourceBundle.getBundle("Comun_ve_es").getString("SecurityCard_MSG"+resultado));
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            setCodigo("1");
        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "SecurityCardAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+SecurityCardAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getSecurityCardServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+SecurityCardAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
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

    public ISecurityCardServicio getSecurityCardServicio() {
        return securityCardServicio;
    }

    public void setSecurityCardServicio(ISecurityCardServicio securityCardServicio) {
        this.securityCardServicio = securityCardServicio;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCirif() {
        return cirif;
    }

    public void setCirif(String cirif) {
        this.cirif = cirif;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}






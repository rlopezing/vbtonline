package ve.com.vbtonline.vista.action.clientInstruction;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.clientInstruction.IClientInstructionServicio;
import ve.com.vbtonline.servicio.od.*;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 20/09/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientInstructionAction extends ActionSupport implements ServletContextAware, SessionAware, Serializable {
    private static final Logger logger = Logger.getLogger(ClientInstructionAction.class);
    private FabricaTo fabrica;
    // private CarterasOd client;
    private IClientInstructionServicio clientInstructionServicio;
    private String mensaje;
    private DataJson data = new DataJson();
    private String jsonAccounts;
    private Map session;
    private List<ContentSelectOd> clients;
    private List<ContentSelectOd> transferTypes;
    private List<ContentSelectOd> maxCartas;
    private List<ContentSelectOd> vencimientos;

    private String idioma;
    private String codigo;   // 1 error 0 ok


    public ClientInstructionAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }


    public String loadValues() throws Exception {
        final String origen = "ClientInstructionAction.loadValues";
        long time;
        AccountsOd account;
        String carteras = new String();

        ContentSelectOd campo = new ContentSelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + ClientInstructionAction.class + " | " + origen);

            time = System.currentTimeMillis();


//            Gson gson=new Gson();
//            data = gson.fromJson(this.jsonAccounts, DataJson.class);
            VBTUsersOd user = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");


            setClients(getClientInstructionServicio().cargarClientes(user.getNumContrato(), idioma));

            setMaxCartas(getClientInstructionServicio().cargarListaMaxCartas(idioma));

            setVencimientos(getClientInstructionServicio().cargarListaVencimientos(idioma));

            setTransferTypes(getClientInstructionServicio().cargarListaTipoTransf(idioma));

            // session.put("clients", getClients());

            /*String language = new String();
            String locale = new String();
            if(idioma.equalsIgnoreCase("_ve_es")){
                language = "es";
                locale = "ve";
            }else{
                language = "en";
                locale = "us";
            }  */

            codigo = "0";
            //
//
            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + ClientInstructionAction.class + " | " + origen + " | " + time);

        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String GuardarLog(String parametro1, String parametro2, String parametro3, String parametro4, String parametro5, String parametro6, String parametro7) throws Exception {
        final String origen = "ClientInstructionAction.GuardarLog";
        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + ClientInstructionAction.class + " | " + origen);

            time = System.currentTimeMillis();

            respuesta = getClientInstructionServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + ClientInstructionAction.class + " | " + origen + " | " + time);

        } catch (Exception e) {
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


    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public String getJsonAccounts() {
        return jsonAccounts;
    }

    public void setJsonAccounts(String jsonAccounts) {
        this.jsonAccounts = jsonAccounts;
    }




    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public IClientInstructionServicio getClientInstructionServicio() {
        return clientInstructionServicio;
    }

    public void setClientInstructionServicio(IClientInstructionServicio clientInstructionServicio) {
        this.clientInstructionServicio = clientInstructionServicio;
    }

    public List<ContentSelectOd> getClients() {
        return clients;
    }

    public void setClients(List<ContentSelectOd> clients) {
        this.clients = clients;
    }

    public List<ContentSelectOd> getTransferTypes() {
        return transferTypes;
    }

    public void setTransferTypes(List<ContentSelectOd> transferTypes) {
        this.transferTypes = transferTypes;
    }

    public List<ContentSelectOd> getMaxCartas() {
        return maxCartas;
    }

    public void setMaxCartas(List<ContentSelectOd> maxCartas) {
        this.maxCartas = maxCartas;
    }

    public List<ContentSelectOd> getVencimientos() {
        return vencimientos;
    }

    public void setVencimientos(List<ContentSelectOd> vencimientos) {
        this.vencimientos = vencimientos;
    }
}






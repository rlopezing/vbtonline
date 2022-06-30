package ve.com.vbtonline.vista.action.portafolio;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.portafolio.IPortafolioServicio;
import ve.com.vbtonline.servicio.od.DataJson;
import ve.com.vbtonline.servicio.od.PortafolioOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Rafael Godoy
 * Date: 13/07/2012
 * Time: 05:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class PortafolioAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(PortafolioAction.class);
    private FabricaTo fabrica;
    private PortafolioOd portafolio;
    private IPortafolioServicio portafolioServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonPortafolio;
    private Map session;




    public PortafolioAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "portafolioAction.execute";
        long time;
        PortafolioOd portafolio;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+PortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+PortafolioAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "portafolioAccion.cargar";
        long time;
        PortafolioOd portafolio;
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+PortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonPortafolio, DataJson.class);
            portafolio=getPortafolioServicio().cargar("", data.getPortafolios().get(0), usuario);
            setMensaje(portafolio.getId().toString());

            this.GuardarLog(usuario.getLogin(),"3","1","2", data.getPortafolios().get(0).toString(),usuario.getIP(),"Todo Mi Portafolio (Consolidado)");
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+PortafolioAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public PortafolioOd getPortafolio() {
        return portafolio;
    }

    public void setPortafolio(PortafolioOd portafolio) {
        this.portafolio = portafolio;
    }

    public IPortafolioServicio getPortafolioServicio() {
        return portafolioServicio;
    }

    public void setPortafolioServicio(IPortafolioServicio portafolioServicio) {
        this.portafolioServicio = portafolioServicio;
    }

    public FabricaTo getFabrica() {
        return fabrica;
    }

    public void setFabrica(FabricaTo fabrica) {
        this.fabrica = fabrica;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "portafolioAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+PortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getPortafolioServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+PortafolioAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
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


}





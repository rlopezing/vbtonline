package ve.com.vbtonline.vista.action.allMyPortafolio;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.accounts.IAccountsServicio;
import ve.com.vbtonline.servicio.negocio.allMyPortafolio.IAllMyPortafolioServicio;
import ve.com.vbtonline.servicio.od.*;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by IntelliJ IDEA.
 * User: Rafael Godoy
 * Date: 13/07/2012
 * Time: 05:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllMyPortafolioAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(AllMyPortafolioAction.class);
    private FabricaTo fabrica;
    private AllMyPortafolioOd allMyPortafolio;
    private IAllMyPortafolioServicio allMyPortafolioServicio;

    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonAllMyPortafolio;
    private Map session;
    private List<CarterasConsolidadasOd> portafolio;
    private CarterasConsolidadasOd carterasTablas;
    private TableOd tabla;
    private List<TablasOd> tablas;
    private TablasOd tablaNombre;
    private String idioma;


    public AllMyPortafolioAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }


    public String execute() throws Exception {
        final String origen = "allMyPortafolioAction.execute";
        long time;
        AllMyPortafolioOd allMyPortafolio;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AllMyPortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AllMyPortafolioAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "allMyPortafolioAccion.cargar";
        long time;
        AllMyPortafolioOd allMyPortafolio;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AllMyPortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonAllMyPortafolio, DataJson.class);
            allMyPortafolio=getAllMyPortafolioServicio().cargar("", data.getAllMyPortafolios().get(0), usuario);
            setMensaje(allMyPortafolio.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AllMyPortafolioAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarPortafolio() throws Exception {
        final String origen = "allMyPortafolioAccion.cargarPortafolio";
        long time;
        AllMyPortafolioOd allMyPortafolio;
        List<String> carteras = new ArrayList<String>();
        VBTUsersOd usuario = new VBTUsersOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AllMyPortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();


//            Gson gson=new Gson();
//            data = gson.fromJson(this.jsonAllMyPortafolio, DataJson.class);

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            carteras=getAllMyPortafolioServicio().cargarCarterasPortafolio(usuario);
//            setMensaje(allMyPortafolio.getId().toString());
            tablas = new ArrayList<>();
            portafolio = new ArrayList<>();
              for(int c=0;c<carteras.size();c++){
                  tablas = new ArrayList<>();
                  tabla = new TableOd();
                  tabla = getAllMyPortafolioServicio().cargarTablas(carteras.get(c),"verCuentas", usuario.getLogin(), idioma, usuario);
                  if(tabla.getContenidoTabla_info().size()>0){
                      tablaNombre = new TablasOd();
                      tablaNombre.setDatos(tabla);
                      tablaNombre.setNombre(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGCuentas").toUpperCase());
                      tablaNombre.setDiv("verCuentas");
                      tablas.add(tablaNombre);
                  }

                  tabla = new TableOd();
                  tabla = getAllMyPortafolioServicio().cargarTablas(carteras.get(c),"verColocaciones", usuario.getLogin(), idioma, usuario);
                  if(tabla.getContenidoTabla_info().size()>0){
                      tablaNombre = new TablasOd();
                      tablaNombre.setDatos(tabla);
                      tablaNombre.setNombre(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGColocaciones").toUpperCase());
                      tablaNombre.setDiv("verColocaciones");
                      tablas.add(tablaNombre);
                  }

                  tabla = new TableOd();
                  tabla = getAllMyPortafolioServicio().cargarTablas(carteras.get(c),"verFondos", usuario.getLogin(), idioma, usuario);
                  if(tabla.getContenidoTabla_info().size()>0){
                      tablaNombre = new TablasOd();
                      tablaNombre.setDatos(tabla);
                      tablaNombre.setNombre(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGFondos").toUpperCase());
                      tablaNombre.setDiv("verFondos");
                      tablas.add(tablaNombre);
                  }

                  tabla = new TableOd();
                  tabla = getAllMyPortafolioServicio().cargarTablas(carteras.get(c),"verOtrasInv", usuario.getLogin(), idioma, usuario);
                  if(tabla.getContenidoTabla_info().size()>0){
                      tablaNombre = new TablasOd();
                      tablaNombre.setDatos(tabla);
                      tablaNombre.setNombre(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGOtrasInversiones").toUpperCase());
                      tablaNombre.setDiv("verOtrasInv");
                      tablas.add(tablaNombre);
                  }

                  tabla = new TableOd();
                  tabla = getAllMyPortafolioServicio().cargarTablas(carteras.get(c),"verTarjetas",  usuario.getLogin(),idioma, usuario);
                  if(tabla.getContenidoTabla_info().size()>0){
                      tablaNombre = new TablasOd();
                      tablaNombre.setDatos(tabla);
                      tablaNombre.setNombre(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleTarjetasCredito").toUpperCase());
                      tablaNombre.setDiv("verTarjetas");
                      tablas.add(tablaNombre);
                  }

                  carterasTablas = new CarterasConsolidadasOd();
                  if(tablas.size()>0){
                      carterasTablas.setNumCartera(carteras.get(c));
                      carterasTablas.setTabla(tablas);
                  }

                  if(carterasTablas.getNumCartera()!=null){
                      portafolio.add(carterasTablas);
                  }
              }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AllMyPortafolioAction.class+" | "+origen+" | "+time);
            VBTUsersOd u=(VBTUsersOd) session.get("user");
            getAllMyPortafolioServicio().guardarLog(u.getLogin(),"3","1","2",u.getCodpercli(),u.getIP(), "Todo Mi Portafolio (Consolidado)");


        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String getJsonAllMyPortafolio() {
        return jsonAllMyPortafolio;
    }

    public void setJsonAllMyPortafolio(String jsonAllMyPortafolio) {
        this.jsonAllMyPortafolio = jsonAllMyPortafolio;
    }

    public AllMyPortafolioOd getAllMyPortafolio() {
        return allMyPortafolio;
    }

    public void setAllMyPortafolio(AllMyPortafolioOd allMyPortafolio) {
        this.allMyPortafolio = allMyPortafolio;
    }

    public IAllMyPortafolioServicio getAllMyPortafolioServicio() {
        return allMyPortafolioServicio;
    }

    public void setAllMyPortafolioServicio(IAllMyPortafolioServicio allMyPortafolioServicio) {
        this.allMyPortafolioServicio = allMyPortafolioServicio;
    }

    public Map getSession() {
        return session;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "allMyPortafolioAccion.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+AllMyPortafolioAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getAllMyPortafolioServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+AllMyPortafolioAction.class+" | "+origen+" | "+time);

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


    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public List<CarterasConsolidadasOd> getPortafolio() {
        return portafolio;
    }

    public void setPortafolio(List<CarterasConsolidadasOd> portafolio) {
        this.portafolio = portafolio;
    }

    public TableOd getTabla() {
        return tabla;
    }

    public void setTabla(TableOd tabla) {
        this.tabla = tabla;
    }

    public List<TablasOd> getTablas() {
        return tablas;
    }

    public void setTablas(List<TablasOd> tablas) {
        this.tablas = tablas;
    }

    public TablasOd getTablaNombre() {
        return tablaNombre;
    }

    public void setTablaNombre(TablasOd tablaNombre) {
        this.tablaNombre = tablaNombre;
    }

    public CarterasConsolidadasOd getCarterasTablas() {
        return carterasTablas;
    }

    public void setCarterasTablas(CarterasConsolidadasOd carterasTablas) {
        this.carterasTablas = carterasTablas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}





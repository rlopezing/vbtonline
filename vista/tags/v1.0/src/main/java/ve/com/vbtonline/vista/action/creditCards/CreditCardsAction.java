package ve.com.vbtonline.vista.action.creditCards;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.creditCards.ICreditCardsServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.validacionUtil;

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
public class CreditCardsAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(CreditCardsAction.class);
    private FabricaTo fabrica;
    private CreditCardsOd creditCard;
    private ICreditCardsServicio creditCardsServicio;
    private String mensaje;
    private DataJson data=new DataJson();
    private String jsonCreditCards;
    private Map session;
    private List<ContentSelectOd> tarjetas;
    private List<ContentSelectOd> tarjetasITT;
    private List<ContentSelectOd> fecha;
    private List<String> detalles;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
    private String idioma;
    private String codigo;




    public CreditCardsAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "creditCardsAction.execute";
        long time;
        CreditCardsOd creditCard;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "creditCardsAccion.cargar";
        long time;
        CreditCardsOd creditCard;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            creditCard=getCreditCardsServicio().cargar("", data.getCreditCardss().get(0));
            setMensaje(creditCard.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarTarjetasEstadoCuentaTDC() throws Exception {
        final String origen = "CreditCardsAction.cargarTarjetasEstadoCuentaTDC";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd tarjeta = new SelectOd();
        SelectOd meses = new SelectOd();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            tarjeta=getCreditCardsServicio().cargarTarjetasEstadoCuentaTDC(carteras, idioma);
            tarjetas=tarjeta.getContenido();

            meses = getCreditCardsServicio().cargarMesesEstadoCuentaTDC(null);
            fecha = meses.getContenido();
            session.put("tarjetas",tarjetas);

            if(tarjetas == null || tarjetas.size()==0)
                codigo = "1";
            else
                codigo = "0";
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarTarjetasITT() throws Exception {
        final String origen = "CreditCardsAction.cargarTarjetasITT";
        long time;
        List<String> parametros;
        String carteras= new String();
        SelectOd tarjeta = new SelectOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
            tarjeta=getCreditCardsServicio().cargarTarjetasTDCITT(carteras, idioma);
            tarjetasITT=tarjeta.getContenido();
            session.put("tarjetasITT",tarjetasITT);

            if(tarjetasITT == null || tarjetasITT.size()==0)
                codigo = "1";
            else
                codigo = "0";



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarDetalleYMovimientosEstadoCuentaTDC() throws Exception {
        final String origen = "CreditCardsAction.cargarDetalleYMovimientosEstadoCuentaTDC";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd tarjeta = new SelectOd();
        SelectOd meses = new SelectOd();
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
             parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));
            parametros.add("002");
            parametros.add(data.getParametros().get(1));


            List<ContentSelectOd> tarjetas = (List<ContentSelectOd> ) session.get("tarjetas");
            if(validar.validarTDC(tarjetas,data.getParametros().get(0)).equalsIgnoreCase("SI")){
                detalles=getCreditCardsServicio().consultarDetallesEstadoCuentaTDC(parametros);
                idioma = (String) session.get("idioma");
                TableOd tabla = getCreditCardsServicio().consultarSaldosEstadoCuentaTDC(parametros, idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "0";
               // strCmbCodInstrumento
                this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0),usuario.getIP(),"Edo. Cuenta Fecha:" + data.getParametros().get(1));
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecucion de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarMovimientosTDCITT() throws Exception {
        final String origen = "CreditCardsAction.cargarMovimientosTDCITT";
        long time;
        List<String> parametros;
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonCreditCards, DataJson.class);
            parametros = new ArrayList<>();
            parametros.add(data.getParametros().get(0));

            idioma = (String) session.get("idioma");
            List<ContentSelectOd> tarjetas = (List<ContentSelectOd> ) session.get("tarjetasITT");
            if(validar.validarTDC(tarjetas,data.getParametros().get(0)).equalsIgnoreCase("SI")){
                TableOd tabla = getCreditCardsServicio().cargarMovimientosTDCITT(parametros, idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                //strNumeroCuentaTdc
                this.GuardarLog(usuario.getLogin(),"3","1","9",data.getParametros().get(0),usuario.getIP(),"Transacciones x Facturar");
                codigo = "0";
            }else{
                idioma = (String) session.get("idioma");
                if(idioma.equalsIgnoreCase("_ve_es"))
                    setMensaje("El número de tarjeta de crédito no coincide con los asignados a su cuenta, por favor inserte un número de tarjeta de crédito válido ");
                else
                    setMensaje("The credit card  number does not match those assigned to your account, please insert a valid credit card number ");
                codigo = "1";
            }




            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            idioma = (String) session.get("idioma");
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde ";
            else
                mensaje = "An error occurred during the execution of your consultation, please try again later";

            codigo = "1";
        }
        return SUCCESS;
    }

    public CreditCardsOd getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardsOd creditCard) {
        this.creditCard = creditCard;
    }

    public ICreditCardsServicio getCreditCardsServicio() {
        return creditCardsServicio;
    }

    public void setCreditCardsServicio(ICreditCardsServicio creditCardsServicio) {
        this.creditCardsServicio = creditCardsServicio;
    }

    public Map getSession() {
        return session;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "CreditCardsAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+CreditCardsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getCreditCardsServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+CreditCardsAction.class+" | "+origen+" | "+time);

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

    public String getJsonCreditCards() {
        return jsonCreditCards;
    }

    public void setJsonCreditCards(String jsonCreditCards) {
        this.jsonCreditCards = jsonCreditCards;
    }

    public List<ContentSelectOd> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<ContentSelectOd> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<ContentSelectOd> getFecha() {
        return fecha;
    }

    public void setFecha(List<ContentSelectOd> fecha) {
        this.fecha = fecha;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
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

    public List<ContentSelectOd> getTarjetasITT() {
        return tarjetasITT;
    }

    public void setTarjetasITT(List<ContentSelectOd> tarjetasITT) {
        this.tarjetasITT = tarjetasITT;
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
}





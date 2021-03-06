package ve.com.vbtonline.vista.action.mutualFunds;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.accounts.IAccountsServicio;
import ve.com.vbtonline.servicio.negocio.mutualFunds.IMutualFundsServicio;
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
public class MutualFundsAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(MutualFundsAction.class);
    private FabricaTo fabrica;
    private MutualFundsOd mutualFunds;
    private IMutualFundsServicio mutualFundsServicio;
    private String mensaje;
    private String codigo;
    private DataJson data=new DataJson();
    private String jsonMutualFunds;
    private String jsonMutualFundsTabla;
    private Map session;
    private List<ContentSelectOd> fondos;
    private List<ContentSelectOd> tipoTransacciones;
    private List<ContentSelectOd> rango;
    private List<String> detalle;
    private String fechaCierre;

    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;

    private String idioma;
    private String numeroCuenta;




    public MutualFundsAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "mutualFundsAction.execute";
        long time;
        MutualFundsOd mutualFunds;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "mutualFundsAccion.cargar";
        long time;
        MutualFundsOd mutualFunds;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFunds, DataJson.class);
            mutualFunds=getMutualFundsServicio().cargar("", data.getMutualFundss().get(0));
            setMensaje(mutualFunds.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarFondosMutuales() throws Exception {
        final String origen = "mutualFundsAccion.cargarFondosMutuales";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd fondos2 = new SelectOd();
        CuentasOd detalles = new CuentasOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");
//            if(session.get("colocaciones")==null){
            detalles=getMutualFundsServicio().cargarFondosMutuales(carteras, idioma);

            fondos=detalles.getContenido();

            if(fondos.size()==0)
                codigo = "1";
            else
                codigo = "0";

            fechaCierre=detalles.getFecha();
            session.put("fondos",fondos);

            rango = new ArrayList<>();
            ContentSelectOd periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGUltimos5Dias"));
            periodo.setValue("5");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos15Dias"));
            periodo.setValue("15");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos30Dias"));
            periodo.setValue("30");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGUltimos90Dias"));
            periodo.setValue("90");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("CuentasSaldos"+idioma).getString("TAGRangoDeFechas"));
            periodo.setValue("-1");
            rango.add(periodo);

//            }else{
//                 colocaciones = (List<ContentSelectOd>) session.get("colocaciones");
//            }





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarFondosMutualesBloqueos() throws Exception {
        final String origen = "mutualFundsAccion.cargarFondosMutualesBloqueos";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd fondos2 = new SelectOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            this.cargarFondosMutuales();

            if(fondos.size()==0)
                codigo = "1";
            else
                codigo = "0";

            idioma = (String) session.get("idioma");
            rango = new ArrayList<>();
            ContentSelectOd periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGVigentes"));
            periodo.setValue("vigentes");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGVencidos"));
            periodo.setValue("vencidos");
            rango.add(periodo);
            periodo = new ContentSelectOd();
            periodo.setLabel(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGRangoFechas"));
            periodo.setValue("rangoFechas");
            rango.add(periodo);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String consultarDetalleFondosMutualesBT() throws Exception {
        final String origen = "mutualFundsAccion.consultarDetalleFondosMutualesBT";
        long time;
        List<String> parametros;
        String  strCodigoFondo = new String();
        String  strCodigoCartera = new String();
        SelectOd tipoTrans = new SelectOd();
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            fondos = (List<ContentSelectOd>) session.get("fondos");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFunds, DataJson.class);
            parametros = new ArrayList<String>();
            idioma = (String) session.get("idioma");
//
            strCodigoFondo  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());
            parametros.add(strCodigoCartera);
            parametros.add(strCodigoFondo);

            if(validar.validarColocaciones(carteras, fondos, data).equalsIgnoreCase("SI")){
                detalle = getMutualFundsServicio().consultarDetalleFondosMutualesBT(parametros);
                tipoTrans = getMutualFundsServicio().cargarTipoTransaccionBT(parametros,idioma);
                tipoTransacciones = tipoTrans.getContenido();
                numeroCuenta = data.getParametros().get(0);
                codigo = "0";
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El n??mero del fondo mutual ?? el n??mero de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El n??mero del fondo mutual ?? el n??mero de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }



//


                    time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecuci??n de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecuci??n de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String consultarSaldosTransFondosMutuales() throws Exception {
        final String origen = "mutualFundsAccion.consultarSaldosTransFondosMutuales";
        long time;
        List<String> parametros;
        String  strCodigoFondo = new String();
        String  strCodigoCartera = new String();
        SelectOd tipoTrans = new SelectOd();
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            fondos = (List<ContentSelectOd>) session.get("fondos");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFundsTabla, DataJson.class);
            parametros = new ArrayList<String>();
            idioma = (String) session.get("idioma");
//
            strCodigoFondo  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());

            parametros.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGEnTransito"));
            parametros.add(strCodigoFondo);
            parametros.add(strCodigoCartera);
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
//            p_TAGEnTransito  IN VARCHAR2,
//                    p_strCodigoFondo   IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//                    p_strCmbDiasConsulta IN VARCHAR2,
//                    p_strTxtFechaDesde  IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
            //***** falta el tipo de transaccion, arreglar el PL

            idioma = (String) session.get("idioma");

            if(validar.validarColocaciones(carteras, fondos, data).equalsIgnoreCase("SI")){
                TableOd tabla = getMutualFundsServicio().consultarSaldosTransFondosMutuales(parametros, idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "0";
                this.GuardarLog(usuario.getLogin(),"3","1","6",strCodigoFondo,usuario.getIP(),"Saldos y Trans. (N?? Cartera:" + strCodigoCartera + ")");
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El n??mero del fondo mutual ?? el n??mero de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El n??mero del fondo mutual ?? el n??mero de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }

//


                    time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecuci??n de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecuci??n de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String consultarBloqueosFondosMutuales() throws Exception {
        final String origen = "mutualFundsAccion.consultarBloqueosFondosMutuales";
        long time;
        List<String> parametros;
        String  strCodigoFondo = new String();
        String  strCodigoCartera = new String();
        SelectOd tipoTrans = new SelectOd();
        validacionUtil validar = new validacionUtil();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            String carteras = (String) session.get("carteras");
            fondos = (List<ContentSelectOd>) session.get("fondos");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonMutualFundsTabla, DataJson.class);
            parametros = new ArrayList<String>();

//
            strCodigoFondo  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());

            parametros.add(data.getParametros().get(1));
            parametros.add(strCodigoFondo);
            parametros.add(strCodigoCartera);
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));

            idioma = (String) session.get("idioma");

            if(validar.validarColocaciones(carteras, fondos, data).equalsIgnoreCase("SI")){
                TableOd tabla = getMutualFundsServicio().consultarBloqueosFondosMutuales(parametros, idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo = "0";
               this.GuardarLog(usuario.getLogin(),"3","1","6",strCodigoFondo,usuario.getIP(),"Bloqueos (N?? Cartera:" + strCodigoCartera + ")");
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El n??mero del fondo mutual ?? el n??mero de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El n??mero del fondo mutual ?? el n??mero de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }

//


                    time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecuci??n de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecuci??n de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "mutualFundsAccion.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+MutualFundsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getMutualFundsServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+MutualFundsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }

    public IMutualFundsServicio getMutualFundsServicio() {
        return mutualFundsServicio;
    }

    public void setMutualFundsServicio(IMutualFundsServicio mutualFundsServicio) {
        this.mutualFundsServicio = mutualFundsServicio;
    }

    public Map getSession() {
        return session;
    }

    public MutualFundsOd getMutualFunds() {
        return mutualFunds;
    }

    public void setMutualFunds(MutualFundsOd mutualFunds) {
        this.mutualFunds = mutualFunds;
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

    public String getJsonMutualFunds() {
        return jsonMutualFunds;
    }

    public void setJsonMutualFunds(String jsonMutualFunds) {
        this.jsonMutualFunds = jsonMutualFunds;
    }

    public List<ContentSelectOd> getFondos() {
        return fondos;
    }

    public void setFondos(List<ContentSelectOd> fondos) {
        this.fondos = fondos;
    }

    public List<String> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<String> detalle) {
        this.detalle = detalle;
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

    public List<ContentSelectOd> getTipoTransacciones() {
        return tipoTransacciones;
    }

    public void setTipoTransacciones(List<ContentSelectOd> tipoTransacciones) {
        this.tipoTransacciones = tipoTransacciones;
    }

    public List<ContentSelectOd> getRango() {
        return rango;
    }

    public void setRango(List<ContentSelectOd> rango) {
        this.rango = rango;
    }

    public String getJsonMutualFundsTabla() {
        return jsonMutualFundsTabla;
    }

    public void setJsonMutualFundsTabla(String jsonMutualFundsTabla) {
        this.jsonMutualFundsTabla = jsonMutualFundsTabla;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
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

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}





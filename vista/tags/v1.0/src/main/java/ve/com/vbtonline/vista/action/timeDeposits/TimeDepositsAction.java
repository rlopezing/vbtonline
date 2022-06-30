package ve.com.vbtonline.vista.action.timeDeposits;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.timeDeposits.ITimeDepositsServicio;
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
public class TimeDepositsAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(TimeDepositsAction.class);
    private FabricaTo fabrica;
    private TimeDepositsOd timeDeposits;
    private ITimeDepositsServicio timeDepositsServicio;
    private String mensaje;
    private String codigo;
    private DataJson data=new DataJson();
    private String jsonTimeDeposits;
    private String jsonColocaciones;
    private String jsonDetalleColocaciones;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
    private List<ContentTableInfoDetalleOd> contenidoTabla_infoDetalle;
    private Map session;
    private List<ContentSelectOd> search_ColCer;
    private List<ContentSelectOd> search_ColVen;
    private List<ContentSelectOd> search_ColBlo;
    private List<ContentSelectOd> colocaciones;
    private DetalleCuentaColocacionesOd detalle;
    private String fechaCierre;
    private String idioma;
    private String numeroCuenta;




    public TimeDepositsAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "timeDepositsAction.execute";
        long time;
        TimeDepositsOd timeDeposits;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TimeDepositsAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TimeDepositsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }


    public String cargar() throws Exception {
        final String origen = "timeDepositsAccion.cargar";
        long time;
        TimeDepositsOd timeDeposits;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TimeDepositsAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonTimeDeposits, DataJson.class);
            timeDeposits=getTimeDepositsServicio().cargar("", data.getTimeDepositss().get(0));
            setMensaje(timeDeposits.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TimeDepositsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public void ImprimirSaldosTrans() throws Exception {
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        Gson gson=new Gson();
        String  strNumeroColocacion = new String();
        String  strCodigoCartera = new String();
        data = gson.fromJson(this.jsonDetalleColocaciones, DataJson.class);
        strNumeroColocacion  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
        strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());
        this.GuardarLog(usuario.getLogin(),"3","1","5",strNumeroColocacion,usuario.getIP(),"Imprimir Saldos y Trans. (N° Cartera:" + strCodigoCartera + ")");
    }

    public String consultarColocacionesSaldos() throws Exception {
        final String origen = "timeDepositsAction.consultarColocacionesSaldos";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        String  strNumeroColocacion = new String();
        String  strCodigoCartera = new String();
        validacionUtil validar = new validacionUtil();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TimeDepositsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonDetalleColocaciones, DataJson.class);
            parametros = new ArrayList<String>();
            numeroCuenta = data.getParametros().get(0);
            strNumeroColocacion  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());
            parametros.add(strCodigoCartera);
            parametros.add(strNumeroColocacion);
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            carteras = (String) session.get("carteras");
            List<ContentSelectOd> colocaciones = (List<ContentSelectOd> ) session.get("colocaciones");

            if(validar.validarColocaciones(carteras, colocaciones, data).equalsIgnoreCase("SI")){
                detalle = getTimeDepositsServicio().consultarColocacionesDetalle(parametros);

                idioma = (String) session.get("idioma");
                TableDetalleOd tabla = getTimeDepositsServicio().consultarColocacionesSaldos(parametros,idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                this.GuardarLog(usuario.getLogin(),"3","1","5",strNumeroColocacion,usuario.getIP(),"Saldos y Trans. (N° Cartera:" + strCodigoCartera + ")");
                codigo ="0";
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El número de colocación ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El número de colocación ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }




            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TimeDepositsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            setMensaje("Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde");
//            if(idioma.equalsIgnoreCase("_ve_es"))
//                mensaje = "El número de colocación ó el número de la cartera no coinciden con los asignados a su usuario, <br> por favor intente nuevamente";
//            else
//                mensaje = "El número de colocación ó el número de la cartera no coinciden con los asignados a su usuario, <br> por favor intente nuevamente";
        }
        return SUCCESS;
    }

    public String cargarColocaciones() throws Exception {
        final String origen = "timeDepositsAction.cargarColocaciones";
        long time;
        List<String> parametros;
        String carteras = new String();
        ContentSelectOd campo = new ContentSelectOd();
        SelectOd colocaciones2 = new SelectOd();
        CuentasOd cuentas = new CuentasOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TimeDepositsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            carteras = (String) session.get("carteras");
            idioma = (String) session.get("idioma");

            cuentas = getTimeDepositsServicio().cargarColocaciones(carteras, idioma);
            colocaciones = cuentas.getContenido();
            fechaCierre = cuentas.getFecha();
            session.put("colocaciones", colocaciones);
            if(colocaciones == null || colocaciones.size()==0)
                codigo = "1";
            else
                codigo = "0";

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TimeDepositsAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String cargarColocacionesBloqueos() throws Exception {
        final String origen = "timeDepositsAction.cargarColocacionesBloqueos";
        long time;
        List<String> parametros;
        String result = new String();
        ContentSelectOd campo = new ContentSelectOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TimeDepositsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            result = cargarColocaciones();


            search_ColBlo = new ArrayList<ContentSelectOd>();
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesBloqueos" + idioma).getString("TAGVigentes"));
            campo.setValue("vigentes");
            search_ColBlo.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesBloqueos" + idioma).getString("TAGVencidos"));
            campo.setValue("vencidos");
            search_ColBlo.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesBloqueos" + idioma).getString("TAGRangoFechas"));
            campo.setValue("rangoFechas");
            search_ColBlo.add(campo);

            session.put("search_ColBlo", search_ColBlo);
//            codigo = "0";

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TimeDepositsAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
        }
        return SUCCESS;
    }

    public String consultarBloqueos() throws Exception {
        final String origen = "timeDepositsAction.consultarBloqueos";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        String  strNumeroColocacion = new String();
        String  strCodigoCartera = new String();
        validacionUtil validar = new validacionUtil();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TimeDepositsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonColocaciones, DataJson.class);
            parametros = new ArrayList<String>();
            strNumeroColocacion  = data.getParametros().get(0).substring(0, data.getParametros().get(0).indexOf("|"));
            strCodigoCartera = data.getParametros().get(0).substring((data.getParametros().get(0).indexOf("|") + 1), data.getParametros().get(0).length());
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            parametros.add(strNumeroColocacion);
            parametros.add(strCodigoCartera);
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));


            idioma= (String) session.get("idioma");

            carteras = (String) session.get("carteras");
            List<ContentSelectOd> colocaciones = (List<ContentSelectOd> ) session.get("colocaciones");

            if(validar.validarColocaciones(carteras, colocaciones, data).equalsIgnoreCase("SI")){
                TableOd tabla = getTimeDepositsServicio().consultarColocacionesBloqueos(parametros,idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();
                codigo ="0";
                this.GuardarLog(usuario.getLogin(),"3","1","5",strNumeroColocacion,usuario.getIP(),"Bloqueos (N° Cartera:" + strCodigoCartera + ")");
            }else{
                codigo = "1";
                if(idioma.equalsIgnoreCase("_ve_es"))
                    mensaje = "El número de colocación ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
                else
                    mensaje = "El número de colocación ó el número de la cartera no coinciden con los asignados a su usuario, por favor intente nuevamente";
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TimeDepositsAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";
            if(idioma.equalsIgnoreCase("_ve_es"))
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
            else
                mensaje = "Ha ocurrido un error durante la ejecución de la consulta, por favor intente mas tarde";
        }
        return SUCCESS;
    }

    public String consultarCertificados() throws Exception {
        final String origen = "timeDepositsAction.consultarCertificados";
        long time;
        List<String> parametros;
        String carteras = new String();
        ContentSelectOd campo = new ContentSelectOd();
        validacionUtil validar = new validacionUtil();
        String result = new String();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TimeDepositsAction.class + " | " + origen);

            time = System.currentTimeMillis();

            fechaCierre = getTimeDepositsServicio().fechaCierre((String) session.get("carteras"));

            Gson gson = new Gson();
            data = gson.fromJson(this.jsonColocaciones, DataJson.class);
            parametros = new ArrayList<String>();
            parametros.add(usuario.getLogin());
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));


            idioma = (String) session.get("idioma");
            result = cargarColocaciones();
            List<ContentSelectOd> colocaciones = (List<ContentSelectOd>) session.get("colocaciones");
            if (colocaciones == null || colocaciones.size()==0)
                codigo = "1";
            else
                codigo = "0";


            TableOd tabla = getTimeDepositsServicio().consultarCertificados(parametros, idioma);
            contenidoTabla_culumnasTest = tabla.getContenidoTabla_culumnas();
            contenidoTabla_infoTest = tabla.getContenidoTabla_info();
            this.GuardarLog(usuario.getLogin(),"3","1","5","",usuario.getIP(),"Certificados");
            search_ColCer = new ArrayList<>();
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesCertificados" + idioma).getString("TAGVigentes"));
            campo.setValue("VI");
            search_ColCer.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesCertificados" + idioma).getString("TAGVencidos"));
            campo.setValue("VE");
            search_ColCer.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesCertificados" + idioma).getString("TAGRangoFechasApertura"));
            campo.setValue("FA");
            search_ColCer.add(campo);
            campo = new ContentSelectOd();
            campo.setLabel(ResourceBundle.getBundle("ColocacionesCertificados" + idioma).getString("TAGRangoFechasVencimiento"));
            campo.setValue("FV");
            search_ColCer.add(campo);
            session.put("search_ColCer", search_ColCer);


            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act") + TimeDepositsAction.class + " | " + origen + " | " + time);


        } catch (Exception e) {
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";

        }
        return SUCCESS;
    }

    public String consultarVencimientos() throws Exception {
        final String origen = "timeDepositsAction.consultarVencimientos";
        long time;
        List<String> parametros;
        String carteras= new String();
        ContentSelectOd campo = new ContentSelectOd();
        validacionUtil validar = new validacionUtil();
        String result = new String();
        VBTUsersOd usuario = (VBTUsersOd) session.get("user");
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TimeDepositsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = (String) session.get("carteras");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonColocaciones, DataJson.class);
            parametros = new ArrayList<String>();
            parametros.add(carteras);
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
//            logger.info("carteras rafael  ::"+carteras);
            fechaCierre = getTimeDepositsServicio().fechaCierre(carteras);
            idioma= (String) session.get("idioma");

            result = cargarColocaciones();
            List<ContentSelectOd> colocaciones = (List<ContentSelectOd>) session.get("colocaciones");
            if (colocaciones == null || colocaciones.size()==0)
                codigo = "1";
            else
                codigo = "0";

//            if(validar.validarColocaciones(carteras, colocaciones, data).equalsIgnoreCase("SI")){
                TableOd tabla = getTimeDepositsServicio().consultarVencimientos(parametros,idioma);
                contenidoTabla_culumnasTest=tabla.getContenidoTabla_culumnas();
                contenidoTabla_infoTest=tabla.getContenidoTabla_info();

                this.GuardarLog(usuario.getLogin(),"3","1","5","",usuario.getIP(),"Vencimientos");

                search_ColVen = new ArrayList<>();
                campo = new ContentSelectOd();
                campo.setLabel(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGPorVencer"));
                campo.setValue("PV");
                search_ColVen.add(campo);
                campo = new ContentSelectOd();
                campo.setLabel(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGVencidos5Dias"));
                campo.setValue("5");
                search_ColVen.add(campo);
                campo = new ContentSelectOd();
                campo.setLabel(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGVencidos15Dias"));
                campo.setValue("15");
                search_ColVen.add(campo);
                campo = new ContentSelectOd();
                campo.setLabel(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGVencidos30Dias"));
                campo.setValue("30");
                search_ColVen.add(campo);
                campo = new ContentSelectOd();
                campo.setLabel(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGVencidos90Dias"));
                campo.setValue("90");
                search_ColVen.add(campo);
                campo = new ContentSelectOd();
                campo.setLabel(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGRangoFechas"));
                campo.setValue("FV");
                search_ColVen.add(campo);
//                codigo ="0";
//




            time = System.currentTimeMillis () - time;


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TimeDepositsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
            codigo = "1";

        }
        return SUCCESS;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TimeDepositsOd getTimeDeposits() {
        return timeDeposits;
    }

    public void setTimeDeposits(TimeDepositsOd timeDeposits) {
        this.timeDeposits = timeDeposits;
    }

    public ITimeDepositsServicio getTimeDepositsServicio() {
        return timeDepositsServicio;
    }

    public void setTimeDepositsServicio(ITimeDepositsServicio timeDepositsServicio) {
        this.timeDepositsServicio = timeDepositsServicio;
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

    public String getJsonTimeDeposits() {
        return jsonTimeDeposits;
    }

    public void setJsonTimeDeposits(String jsonTimeDeposits) {
        this.jsonTimeDeposits = jsonTimeDeposits;
    }

    public String getJsonColocaciones() {
        return jsonColocaciones;
    }

    public void setJsonColocaciones(String jsonColocaciones) {
        this.jsonColocaciones = jsonColocaciones;
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

    public List<ContentSelectOd> getSearch_ColCer() {
        return search_ColCer;
    }

    public void setSearch_ColCer(List<ContentSelectOd> search_ColCer) {
        this.search_ColCer = search_ColCer;
    }

    public List<ContentSelectOd> getSearch_ColVen() {
        return search_ColVen;
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "timeDepositsAction.GuardarLog";
        long time;
        String respuesta=new String();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+TimeDepositsAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getTimeDepositsServicio().guardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+TimeDepositsAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);

        }
        return respuesta;
    }
    public void setSearch_ColVen(List<ContentSelectOd> search_ColVen) {
        this.search_ColVen = search_ColVen;
    }

    public List<ContentSelectOd> getColocaciones() {
        return colocaciones;
    }

    public void setColocaciones(List<ContentSelectOd> colocaciones) {
        this.colocaciones = colocaciones;
    }

    public String getJsonDetalleColocaciones() {
        return jsonDetalleColocaciones;
    }

    public void setJsonDetalleColocaciones(String jsonDetalleColocaciones) {
        this.jsonDetalleColocaciones = jsonDetalleColocaciones;
    }

    public DetalleCuentaColocacionesOd getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleCuentaColocacionesOd detalle) {
        this.detalle = detalle;
    }

    public List<ContentSelectOd> getSearch_ColBlo() {
        return search_ColBlo;
    }

    public void setSearch_ColBlo(List<ContentSelectOd> search_ColBlo) {
        this.search_ColBlo = search_ColBlo;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public List<ContentTableInfoDetalleOd> getContenidoTabla_infoDetalle() {
        return contenidoTabla_infoDetalle;
    }

    public void setContenidoTabla_infoDetalle(List<ContentTableInfoDetalleOd> contenidoTabla_infoDetalle) {
        this.contenidoTabla_infoDetalle = contenidoTabla_infoDetalle;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }
}





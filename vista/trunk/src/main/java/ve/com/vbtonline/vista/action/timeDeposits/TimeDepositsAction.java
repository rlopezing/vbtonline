package ve.com.vbtonline.vista.action.timeDeposits;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.timeDeposits.ITimeDepositsServicio;
import ve.com.vbtonline.servicio.negocio.transfers.ITransfersServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.validacionUtil;
import ve.com.vbtonline.vista.action.transfers.TransfersAction;

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
    private List<ContentSelectOd> cuentasTOB;
    private List<ContentSelectOd> cuentasTD;
    private List<ContentSelectOd> tiposTD;
    private List<ContentSelectOd> plazosTD;
    private List<ContentSelectOd> plazosPrefTD;
    private List<ContentSelectOd> tasaTD;
    private DetalleCuentaColocacionesOd detalle;
    private String fechaCierre;
    private String idioma;
    private String numeroCuenta;
    private List<ContentSelectOd> cuentas;
    private ITransfersServicio transfersServicio;
    private String jsonCreditCards;
    private List<ContentsTableColumnsOd> tablaActivas_culumnas;
    private List<ContentTableInfoOd> contenidoTablaActivas_info;
    private List<ContentsTableColumnsOd> tablaHistorico_culumnas;
    private List<ContentTableInfoOd> contenidoTablaHistorico_info;
    private TableOd aperturaTD;
    private List<ContentsTableColumnsOd> contenidoTabla_culumt;
    private List<ContentTableInfoOd> contenidoTabla_info;
    private String jsonTD;
    private String respuesta;
    private String accountSelected;
    private String tasaTDFill;
    private String minBalance;
    private String resumenTD;

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
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonTimeDeposits, DataJson.class);
            timeDeposits=getTimeDepositsServicio().cargar("", data.getTimeDepositss().get(0), usuario);
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
                detalle = getTimeDepositsServicio().consultarColocacionesDetalle(parametros, usuario);

                idioma = (String) session.get("idioma");
                TableDetalleOd tabla = getTimeDepositsServicio().consultarColocacionesSaldos(parametros,idioma,usuario);
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
            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            cuentas = getTimeDepositsServicio().cargarColocaciones(carteras, idioma, usuario);
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
                TableOd tabla = getTimeDepositsServicio().consultarColocacionesBloqueos(parametros,idioma, usuario);
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

            fechaCierre = getTimeDepositsServicio().fechaCierre((String) session.get("carteras"), usuario);

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


            TableOd tabla = getTimeDepositsServicio().consultarCertificados(parametros, idioma, usuario);
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
            fechaCierre = getTimeDepositsServicio().fechaCierre(carteras, usuario);
            idioma= (String) session.get("idioma");

            result = cargarColocaciones();
            List<ContentSelectOd> colocaciones = (List<ContentSelectOd>) session.get("colocaciones");
            if (colocaciones == null || colocaciones.size()==0)
                codigo = "1";
            else
                codigo = "0";

//            if(validar.validarColocaciones(carteras, colocaciones, data).equalsIgnoreCase("SI")){
                TableOd tabla = getTimeDepositsServicio().consultarVencimientos(parametros,idioma, usuario);
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

            public List<ContentSelectOd> getCuentasTOB() {
        return cuentasTOB;
    }

            public void setCuentasTOB(List<ContentSelectOd> cuentasTOB) {
        this.cuentasTOB = cuentasTOB;
    }

    public String cargarTimeDeposits() throws Exception {
        final String origen = "timeDepositsAction.cargarTimeDeposits";
        long time;
        CuentasOd cuentasDescripcion = new CuentasOd();
        TimeDepositsOd tipoTDDescripcion = new TimeDepositsOd();
        SelectOd cuentas2 = new SelectOd();
        String carteras;
        String tiposTDs = null;
        String plazos = null;
        String plazosPref = null;
        String titulo = null;
        String tasa = null;
        List<String> parametros;
        VBTUsersOd usuario = new VBTUsersOd();
        parametros = new ArrayList<String>();
        Integer cantidad=0;
        String origenConsulta="TOB";//Parametros Personales
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);
            time = System.currentTimeMillis();
            usuario = (VBTUsersOd)session.get("user");
            idioma = (String) session.get("idioma");
            carteras = (String) session.get("carteras");
            cuentasTD = (List<ContentSelectOd> ) session.get("cuentasTD");
            tiposTD = (List<ContentSelectOd> ) session.get("tiposTD");
            plazosTD = (List<ContentSelectOd> ) session.get("plazosTD");
                cuentasDescripcion = getTimeDepositsServicio().cargarCuentasTD(carteras, idioma, usuario);
                cuentasTD = cuentasDescripcion.getContenido();
                setCuentasTD(cuentasTD);
                tiposTD = getTimeDepositsServicio().cargartiposTD(tiposTDs, idioma, usuario);
                setTiposTD(tiposTD);

                setPlazosTD(getTimeDepositsServicio().cargarPlazosTD(plazos, idioma, usuario));
                setPlazosTD(getPlazosTD());

                setPlazosPrefTD(getTimeDepositsServicio().cargarPlazosPrefTD(plazosPref, idioma, usuario));
                setPlazosPrefTD(getPlazosPrefTD());

                aperturaTD = getTimeDepositsServicio().cargarTablaSolicitudTD(parametros,idioma, usuario);
                setAperturaTD(getAperturaTD());

                setContenidoTabla_culumt(aperturaTD.getContenidoTabla_culumnas());
                setContenidoTabla_info(aperturaTD.getContenidoTabla_info());

                this.GuardarLog(usuario.getLogin(), "16", "1", "4", "", usuario.getIP(), "El usuario: " + usuario.getLogin() + " Ingreso a la opción Apertura Time Deposits");

        } catch (Exception ex) {
            logger.error(ex);
            setMensaje(ex.getMessage());
        }
        return SUCCESS;
    }

            public ITransfersServicio getTransfersServicio() {
        return transfersServicio;
    }

            public void setTransfersServicio(ITransfersServicio transfersServicio) {
        this.transfersServicio = transfersServicio;
    }

            public List<ContentSelectOd> getTiposTD() {
        return tiposTD;
    }

            public void setTiposTD(List<ContentSelectOd> tiposTD) {
        this.tiposTD = tiposTD;
    }

            public List<ContentSelectOd> getPlazosTD() {
        return plazosTD;
    }

            public void setPlazosTD(List<ContentSelectOd> plazosTD) {
        this.plazosTD = plazosTD;
    }

            public String getJsonCreditCards() {
        return jsonCreditCards;
    }

            public void setJsonCreditCards(String jsonCreditCards) {
        this.jsonCreditCards = jsonCreditCards;
    }

            public List<ContentsTableColumnsOd> getTablaActivas_culumnas() {
        return tablaActivas_culumnas;
    }

            public void setTablaActivas_culumnas(List<ContentsTableColumnsOd> tablaActivas_culumnas) {
        this.tablaActivas_culumnas = tablaActivas_culumnas;
    }

            public List<ContentTableInfoOd> getContenidoTablaActivas_info() {
        return contenidoTablaActivas_info;
    }

            public void setContenidoTablaActivas_info(List<ContentTableInfoOd> contenidoTablaActivas_info) {
        this.contenidoTablaActivas_info = contenidoTablaActivas_info;
    }

            public List<ContentsTableColumnsOd> getTablaHistorico_culumnas() {
        return tablaHistorico_culumnas;
    }

            public void setTablaHistorico_culumnas(List<ContentsTableColumnsOd> tablaHistorico_culumnas) {
        this.tablaHistorico_culumnas = tablaHistorico_culumnas;
    }

            public List<ContentTableInfoOd> getContenidoTablaHistorico_info() {
        return contenidoTablaHistorico_info;
    }

            public void setContenidoTablaHistorico_info(List<ContentTableInfoOd> contenidoTablaHistorico_info) {
        this.contenidoTablaHistorico_info = contenidoTablaHistorico_info;
    }

            public TableOd getAperturaTD() {
        return aperturaTD;
    }

            public void setAperturaTD(TableOd aperturaTD) {
        this.aperturaTD = aperturaTD;
    }

            public List<ContentsTableColumnsOd> getContenidoTabla_culumt() {
        return contenidoTabla_culumt;
    }

            public void setContenidoTabla_culumt(List<ContentsTableColumnsOd> contenidoTabla_culumt) {
        this.contenidoTabla_culumt = contenidoTabla_culumt;
    }

            public List<ContentTableInfoOd> getContenidoTabla_info() {
        return contenidoTabla_info;
    }

            public void setContenidoTabla_info(List<ContentTableInfoOd> contenidoTabla_info) {
        this.contenidoTabla_info = contenidoTabla_info;
    }

    public String llenarSolicitudTD() throws Exception {
        final String origen = "transfersAccion.llenarSolicitudTD";
        long time;
        CuentasOd cuentasDescrip = new CuentasOd();
        ParametrosPersonalesOd parametrosPersonales = new ParametrosPersonalesOd();
        List<String> resultadosParametros=null;
        String respuestaAux="OK";
        String origenConsulta="OTHER";//Parametros Personales
        VBTUsersOd usuario=new VBTUsersOd();
        TimeDepositsOd TD=new TimeDepositsOd();
        DataJson RTD=new DataJson();
       // String resumenTD=new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.getJsonTD(), DataJson.class);
            usuario = (VBTUsersOd) session.get("user");
            TD.setTipo(data.getParametros().get(0));
            TD.setCuenta(data.getParametros().get(1));
            TD.setPlazo(data.getParametros().get(2));
            TD.setMonto(data.getParametros().get(3).replace(",", "."));
            TD.setMoneda(data.getParametros().get(4));
            TD.setTasa(data.getParametros().get(5));
            TD.setCartera(data.getParametros().get(6));
            TD.setDescripcionTipoTD(data.getParametros().get(7));

            setResumenTD(getTimeDepositsServicio().insertarSolicitudesTD(TD, idioma, usuario));

            time = System.currentTimeMillis() - time;

            if (getResumenTD().equals("OK")){
                this.GuardarLog(usuario.getLogin(), "12", "1", "5", TD.getCuenta(), usuario.getIP(), "Solicitud de Apertura de Colocacion de la Cartera: " + TD.getCartera()  );

            }else {
                if (idioma.equalsIgnoreCase("_ve_es")) {
                    mensaje = "Apertura Fallida";
                } else {
                    mensaje = "Failed Opening";
                }
            }

        } catch (Exception ex) {
            logger.error(ex);
        }
       return SUCCESS;
    }

            public String getJsonTD() {
        return jsonTD;
    }

            public void setJsonTD(String jsonTD) {
        this.jsonTD = jsonTD;
    }

            public String getRespuesta() {
        return respuesta;
    }

            public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

            public String getAccountSelected() {
        return accountSelected;
    }

            public void setAccountSelected(String accountSelected) {
        this.accountSelected = accountSelected;
    }

            public List<ContentSelectOd> getCuentas() {
        return cuentas;
    }

            public void setCuentas(List<ContentSelectOd> cuentas) {
        this.cuentas = cuentas;
    }

            public List<ContentSelectOd> getTasaTD() {
        return tasaTD;
    }

            public void setTasaTD(List<ContentSelectOd> tasaTD) {
        this.tasaTD = tasaTD;
    }

    public String calcularTasaTD() throws Exception {
        final String origen = "transfersAccion.calcularTasaTD";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();
        TimeDepositsOd TD=new TimeDepositsOd();
        DataJson RTD=new DataJson();
        List<String> resumenTD=new ArrayList<String>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            Gson gson = new Gson();
            data = gson.fromJson(this.getJsonTD(), DataJson.class);
            usuario = (VBTUsersOd) session.get("user");
            TD.setTipo(data.getParametros().get(0));
            TD.setCuenta(data.getParametros().get(1));
            TD.setPlazo(data.getParametros().get(2));
            TD.setMonto(data.getParametros().get(3).replace(",","."));
            TD.setMoneda(data.getParametros().get(4));

            tasaTDFill = getTimeDepositsServicio().calcularTasaTD(TD, idioma, usuario);
            setTasaTDFill(getTasaTDFill());
            session.put("tasaTDFill", tasaTDFill);

            time = System.currentTimeMillis() - time;

        } catch (Exception e) {
            logger.error(e);
        }
        return SUCCESS;
    }

            public String getTasaTDFill() {
        return tasaTDFill;
    }

            public void setTasaTDFill(String tasaTDFill) {
        this.tasaTDFill = tasaTDFill;
    }

            public List<ContentSelectOd> getCuentasTD() {
        return cuentasTD;
    }

            public void setCuentasTD(List<ContentSelectOd> cuentasTD) {
        this.cuentasTD = cuentasTD;
    }

    public List<ContentSelectOd> getPlazosPrefTD() {
        return plazosPrefTD;
    }

    public void setPlazosPrefTD(List<ContentSelectOd> plazosPrefTD) {
        this.plazosPrefTD = plazosPrefTD;
    }

    public String validarParametrosPersonalesTD() throws Exception {
        final String origen = "transfersAccion.validarParametrosPersonalesTD";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();
        TimeDepositsOd TD=new TimeDepositsOd();
        DataJson RTD=new DataJson();
        List<String> resumenTD=new ArrayList<String>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act") + TransfersAction.class + " | " + origen);

            time = System.currentTimeMillis();

            //Gson gson = new Gson();
            //data = gson.fromJson(this.getJsonTD(), DataJson.class);
            usuario = (VBTUsersOd) session.get("user");
            //TD.setTipo(data.getParametros().get(0));
            //TD.setCuenta(data.getParametros().get(1));
            //TD.setMonto(data.getParametros().get(2).replace(",", "."));

            minBalance = getTimeDepositsServicio().validarParametrosPersonalesTD("0", usuario, "0", "0");
            setTasaTDFill(getTasaTDFill());
            session.put("minBalance", minBalance);

            time = System.currentTimeMillis() - time;

        } catch (Exception e) {
            logger.error(e);
        }
        return SUCCESS;
    }

    public String getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(String minBalance) {
        this.minBalance = minBalance;
    }

    public String getResumenTD() {
        return resumenTD;
    }

    public void setResumenTD(String resumenTD) {
        this.resumenTD = resumenTD;
    }
}
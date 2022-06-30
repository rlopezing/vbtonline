package ve.com.vbtonline.servicio.negocio.creditCards;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.CreditCardsIo;
import ve.com.vbtonline.servicio.io.LoggerIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.negocio.creditCards.ICreditCardsServicio;
import ve.com.vbtonline.servicio.od.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCardsServicio extends BasicService implements ICreditCardsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(CreditCardsServicio.class);

    /** El Data Access Object
     */
    private CreditCardsIo creditCardsIo;



    private LoggerIo loggerIo;




    /** Constructor de la clase
     */
    public CreditCardsServicio(){
    }



    public CreditCardsOd cargar (String transaccionId, CreditCardsOd ccod, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargar";

        long time;
        CreditCardsOd creditCardsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            creditCardsOd=new CreditCardsOd();
            creditCardsOd = getCreditCardsIo().Cargar("",ccod, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (creditCardsOd);
    }

    public TableOd consultarSaldosEstadoCuentaTDC (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.consultarSaldosEstadoCuentaTDC";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getCreditCardsIo().consultarSaldosEstadoCuentaTDC(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public TableOd cargarMovimientosTDCITT (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovimientosTDCITT";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getCreditCardsIo().cargarMovimientosTDCITT(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public Map<String,Object> cargarEstatusTDCCL (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovimientosTDCITT";
        Map<String,Object> estatus = null;
        long time;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            estatus = getCreditCardsIo().cargarEstatusTDCCL(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (estatus);
    }

    public List<String> cargarEstatusTDCCLE (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarEstatusTDCCLE";
        List<String> estatus = new ArrayList<String>();
        long time;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            estatus = getCreditCardsIo().cargarEstatusTDCCLE(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (estatus);
    }

    public List<String> consultarDetallesEstadoCuentaTDC (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.consultarDetallesEstadoCuentaTDC";

        long time;

        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getCreditCardsIo().consultarDetallesEstadoCuentaTDC(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }

    public List<String> consultarPagosTdc_detalle_fc (List<String> parametros, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "CreditCardsServicio.consultarPagosTdc_detalle_fc";

        long time;

        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getCreditCardsIo().consultarDetallesPagoTDC(parametros, usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }

    public SelectOd cargarMesesEstadoCuentaTDC (String nroCuenta, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarMesesEstadoCuentaTDC";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarMesesEstadoCuentaTDC(nroCuenta, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTarjetasEstadoCuentaTDC (String carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarTarjetasEstadoCuentaTDC";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTarjetasEstadoCuentaTDC(carteras, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarMovitosCLE (String estatusTarjeta, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovitosCLE";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarMotivosCLE(estatusTarjeta, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTarjetasTDCITT (String carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarTarjetasTDCITT";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTarjetasTDCITT(carteras, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTarjetasTDCCL (String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.cargarTarjetasTDCCL";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTarjetasTDCCL(carteras, idioma, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTdcPagos (String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.cargarTdcPagos";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTdcPagos(carteras, idioma, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public String cambiarEstatusPagosTDC (String Status, String numPago, VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "CreditCardsServicio.cambiarEstatusPagosTdc";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getCreditCardsIo().cambiarEstatusPagosTDC(Status, numPago, usuario, Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String crearReglaTDCCL (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.crearReglaTDCCL";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getCreditCardsIo().crearReglaTDCCL(parametros, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }

    public String eliminarReglaTDCCL (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.eliminarReglaTDCCL";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getCreditCardsIo().eliminarReglaTDCCL(parametros, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }

    public String editarRegla (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.editarRegla";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getCreditCardsIo().editarRegla(parametros, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }

    public String cargarProximoDiaHabil (VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.cargarProximoDiaHabil";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getCreditCardsIo().cargarProximoDiaHabil(usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }


  /*  public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "CreditCardsServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoggerIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }    */

    public String validarFechas (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.validarFechas";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getCreditCardsIo().validarFechas(parametros, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }

    public List<List<String>> cargarFeriados (String anio, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarFeriados";

        long time;

        List<List<String>> listaDetalles = new ArrayList();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getCreditCardsIo().cargarFeriados(anio, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }

    public TableOd consultarHistorioPagoTDC (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.consultarHistorioPagoTDC";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getCreditCardsIo().consultarHistorioPagoTDC(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public List<String> consultarDetallesEstadoCuentaCorteTDC (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.consultarDetallesEstadoCuentaCorteTDC";

        long time;

        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getCreditCardsIo().consultarDetallesEstadoCuentaCorteTDC(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }


    public  List<String> encabezadoMovimientos (List<String> parametros,VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.encabezadoMovimientos";
        List<String> listaDetalles = new ArrayList<String>();
        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getCreditCardsIo().encabezadoMovimientos(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }



    public String guardarPagoTDC (List<String> parametros, VBTUsersOd usuario, ParametrosPersonalesOd parametrospersonales, String idioma) throws Exception {
        final String origen = "CreditCardsServicio.guardarPagoTDC";

        long time;

        String respuesta="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getCreditCardsIo().guardarPagoTDC(parametros, usuario, parametrospersonales, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public TableOd consultarPagosTdc (String carteras, String Status, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.consultarPagosTdc";

        long time;

        TableOd listaPagos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaPagos = getCreditCardsIo().consultPaymentsTDC(carteras, Status, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaPagos);
    }

    public String validarLimiteSalvis (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.validarLimiteSalvis";
        long time;
        String respuesta="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getCreditCardsIo().validarLimiteSalvis(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public SelectOd cargarTarjetasTDCCLE (String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsServicio.cargarTarjetasTDCCLE";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTarjetasTDCCLE(carteras, idioma, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public String cambiarEstatusCLE (List<String> parametros, VBTUsersOd usuario, String idioma, boolean servicio) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovimientosTDCITT";
        String respuesta = "";
        long time;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getCreditCardsIo().cambiarEstatusCLE(parametros, usuario,idioma,servicio);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (respuesta);
    }

  /*  public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
        final String origen = "CreditServicio.guardarExcepcion";
        long time;
        Integer exitoso=0;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getLoggerIo().guardarExcepcion(parametro1, parametro2,parametro3,parametro4,parametro5,parametro6);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
        }

        return (respuesta);
    }   */




    public CreditCardsIo getCreditCardsIo() {
        return creditCardsIo;
    }

    public void setCreditCardsIo(CreditCardsIo creditCardsIo) {
        this.creditCardsIo = creditCardsIo;
    }

  /*  public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }  */
}

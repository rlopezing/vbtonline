package ve.com.vbtonline.servicio.negocio.timeDeposits;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.TimeDepositsIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.negocio.transfers.TransfersServicio;
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
public class TimeDepositsServicio extends BasicService implements ITimeDepositsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(TimeDepositsServicio.class);

    /** El Data Access Object **/

    private TimeDepositsIo timeDepositsIo;
    /** Constructor de la clase **/

    public TimeDepositsServicio(){
    }

    public TimeDepositsOd cargar (String transaccionId, TimeDepositsOd tdod,VBTUsersOd usuario) throws Exception {
        final String origen = "TimeDepositsServicio.cargar";

        long time;
        TimeDepositsOd timeDepositsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            timeDepositsOd=new TimeDepositsOd();
            timeDepositsOd = getTimeDepositsIo().Cargar("",tdod, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (timeDepositsOd);
    }

    public String fechaCierre (String carteras,VBTUsersOd usuario) throws Exception {
        final String origen = "TimeDepositsServicio.fechaCierre";

        long time;
        String fecha = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            fecha = getTimeDepositsIo().consultarFechaCierre(carteras, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (fecha);
    }

    public TableOd consultarCertificados (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarCertificados";

        long time;

        TableOd listaCertificado = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCertificado = getTimeDepositsIo().consultarColocacionesCertificados(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCertificado);
    }

    public TableDetalleOd consultarColocacionesSaldos (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarColocacionesSaldos";

        long time;

        TableDetalleOd listaSaldos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaSaldos = getTimeDepositsIo().consultarColocacionesSaldos(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaSaldos);
    }

    public DetalleCuentaColocacionesOd consultarColocacionesDetalle (List<String> parametros, VBTUsersOd usuario) throws Exception  {
        final String origen = "AccountsServicio.consultarColocacionesSaldos";

        long time;

        DetalleCuentaColocacionesOd detalle = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            detalle = getTimeDepositsIo().consultarColocacionesDetalle(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (detalle);
    }

    public TableOd consultarVencimientos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarVencimientos";

        long time;

        TableOd listaVencimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaVencimientos = getTimeDepositsIo().consultarColocacionesVencimientos(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaVencimientos);
    }

    public TableOd consultarColocacionesBloqueos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarColocacionesBloqueos";

        long time;

        TableOd listaBloqueos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaBloqueos = getTimeDepositsIo().consultarColocacionesBloqueos(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaBloqueos);
    }

    public CuentasOd cargarColocaciones (String carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.cargarColocaciones";

        long time;

        CuentasOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getTimeDepositsIo().cargarColocaciones(carteras, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public TimeDepositsIo getTimeDepositsIo() {
        return timeDepositsIo;
    }

    public void setTimeDepositsIo(TimeDepositsIo timeDepositsIo) {
        this.timeDepositsIo = timeDepositsIo;
    }

    public CuentasOd cargarCuentas (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
      final String origen = "TransfersServicio.cargarCuentas";

      long time;
      CuentasOd cuentas = new CuentasOd();

      try {

          logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

          time = System.currentTimeMillis ();


          cuentas= getTimeDepositsIo().cargarCuentas(Carteras,idioma, usuario);

          time = System.currentTimeMillis () - time;

          logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
      }
      catch (Exception ex) {
          logger.error(ex); throw (new Exception (ex.getMessage(),null));
      }

      return (cuentas);
  }

    public CuentasOd cargarCuentasTD (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCuentasTD";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cuentas= getTimeDepositsIo().cargarCuentasTD(Carteras,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public List<ContentSelectOd> cargartiposTD (String tipoTD, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarTiposTD";
        long time;
        List<ContentSelectOd> tiposTD = new ArrayList<ContentSelectOd>();
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);
            time = System.currentTimeMillis ();
            tiposTD= (List<ContentSelectOd>) getTimeDepositsIo().cargarTiposTD(tipoTD,idioma, usuario);
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (tiposTD);
    }

    public List<ContentSelectOd> cargarPlazosTD (String plazos, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarTiposTD";
        long time;
        List<ContentSelectOd> plazosTD = new ArrayList<ContentSelectOd>();
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);
            time = System.currentTimeMillis ();
            plazosTD= (List<ContentSelectOd>) getTimeDepositsIo().cargarPlazosTD(plazos,idioma, usuario);
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (plazosTD);
    }

    public List<ContentSelectOd> cargarPlazosPrefTD (String plazos, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarTiposTD";
        long time;
        List<ContentSelectOd> plazosTD = new ArrayList<ContentSelectOd>();
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);
            time = System.currentTimeMillis ();
            plazosTD= (List<ContentSelectOd>) getTimeDepositsIo().cargarPlazosPrefTD(plazos,idioma, usuario);
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (plazosTD);
    }


     public TableOd cargarTablaSolicitudTD (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarVencimientos";

        long time;

        TableOd listaAperturas = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaAperturas = getTimeDepositsIo().cargarTablaSolicitudTD(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaAperturas);
    }

    public String insertarSolicitudesTD (TimeDepositsOd TD, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCuentas";
        String tasaTD;
        long time;
        String resumenTD = new String();


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            resumenTD= getTimeDepositsIo().insertarSolicitudesTD(TD,idioma, usuario);

            //tasaTD= getTimeDepositsIo().obtenerTasaTD(TD,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (resumenTD);
    }

    public String calcularTasaTD (TimeDepositsOd TD, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.tasaTD";
        String tasaTDFill;
        long time;
        List<String> resumenTD = new ArrayList<String>();


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            tasaTDFill= getTimeDepositsIo().obtenerTasaTD(TD,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tasaTDFill);
    }

    public String validarParametrosPersonalesTD (String monto, VBTUsersOd usuario, String codcartera, String codcuenta) throws Exception{
        final String origen = "TransfersServicio.tasaTD";
        String montominimo;
        long time;
        List<String> resumenTD = new ArrayList<String>();


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            montominimo= getTimeDepositsIo().validarParametrosPersonalesTD(monto, usuario, codcartera, codcuenta);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (montominimo);
    }

}

package ve.com.vbtonline.servicio.negocio.timeDeposits;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.TimeDepositsIo;
import ve.com.vbtonline.servicio.od.*;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimeDepositsServicio implements ITimeDepositsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(TimeDepositsServicio.class);

    /** El Data Access Object
     */
    private TimeDepositsIo timeDepositsIo;




    /** Constructor de la clase
     */
    public TimeDepositsServicio(){
    }



    public TimeDepositsOd cargar (String transaccionId, TimeDepositsOd tdod) throws Exception {
        final String origen = "TimeDepositsServicio.cargar";

        long time;
        TimeDepositsOd timeDepositsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            timeDepositsOd=new TimeDepositsOd();
            timeDepositsOd = getTimeDepositsIo().Cargar("",tdod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (timeDepositsOd);
    }

    public String fechaCierre (String carteras) throws Exception {
        final String origen = "TimeDepositsServicio.fechaCierre";

        long time;
        String fecha = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            fecha = getTimeDepositsIo().consultarFechaCierre(carteras);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (fecha);
    }

    public TableOd consultarCertificados (List<String> parametros, String idioma) throws Exception {
        final String origen = "AccountsServicio.consultarCertificados";

        long time;

        TableOd listaCertificado = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCertificado = getTimeDepositsIo().consultarColocacionesCertificados(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCertificado);
    }

    public TableDetalleOd consultarColocacionesSaldos (List<String> parametros, String idioma) throws Exception {
        final String origen = "AccountsServicio.consultarColocacionesSaldos";

        long time;

        TableDetalleOd listaSaldos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaSaldos = getTimeDepositsIo().consultarColocacionesSaldos(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaSaldos);
    }

    public DetalleCuentaColocacionesOd consultarColocacionesDetalle (List<String> parametros) throws Exception  {
        final String origen = "AccountsServicio.consultarColocacionesSaldos";

        long time;

        DetalleCuentaColocacionesOd detalle = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            detalle = getTimeDepositsIo().consultarColocacionesDetalle(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (detalle);
    }

    public TableOd consultarVencimientos (List<String> parametros, String idioma) throws Exception {
        final String origen = "AccountsServicio.consultarVencimientos";

        long time;

        TableOd listaVencimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaVencimientos = getTimeDepositsIo().consultarColocacionesVencimientos(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaVencimientos);
    }

    public TableOd consultarColocacionesBloqueos (List<String> parametros, String idioma) throws Exception {
        final String origen = "AccountsServicio.consultarColocacionesBloqueos";

        long time;

        TableOd listaBloqueos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaBloqueos = getTimeDepositsIo().consultarColocacionesBloqueos(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaBloqueos);
    }

    public CuentasOd cargarColocaciones (String carteras, String idioma) throws Exception {
        final String origen = "AccountsServicio.cargarColocaciones";

        long time;

        CuentasOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getTimeDepositsIo().cargarColocaciones(carteras, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "TimeDepositsServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TimeDepositsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getTimeDepositsIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TimeDepositsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public TimeDepositsIo getTimeDepositsIo() {
        return timeDepositsIo;
    }

    public void setTimeDepositsIo(TimeDepositsIo timeDepositsIo) {
        this.timeDepositsIo = timeDepositsIo;
    }
}

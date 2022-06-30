package ve.com.vbtonline.servicio.negocio.reportsPdf;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.ReportsPdfIo;
import ve.com.vbtonline.servicio.negocio.portafolio.IPortafolioServicio;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReportsPdfServicio implements IReportsPdfServicio,Serializable {
    private static final Logger logger = Logger.getLogger(ReportsPdfServicio.class);

    /** El Data Access Object
     */
    private ReportsPdfIo reportsPdfIo;


    public ReportsPdfIo getReportsPdfIo() {
        return reportsPdfIo;
    }

    public void setReportsPdfIo(ReportsPdfIo reportsPdfIo) {
        this.reportsPdfIo = reportsPdfIo;
    }

    /** Constructor de la clase
     */
    public ReportsPdfServicio(){
    }

    /**
     * Retorno al informacion de cabecera de estado de cuenta
     * @param num_cta
     * @param mes
     * @param anio
     * @return
     * @throws Exception
     */
    public List<String> consultarCabeceraEdoCuenta (String num_cta, String mes , String anio) throws Exception {
        final String origen = "ReportsPdfServicio.consultarCabeceraEdoCuenta";

        long time;

        List<String> cabecera = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cabecera =  reportsPdfIo.consultarCabeceraEdoCuenta(num_cta, mes, anio);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cabecera);
    }

    public List<String> consultarCabeceraEdoCuentaTDC (String num_cta, String codproserv , String mes) throws Exception {
        final String origen = "ReportsPdfServicio.consultarCabeceraEdoCuentaTDC";

        long time;

        List<String> cabecera = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cabecera =  reportsPdfIo.consultarCabeceraEdoCuentaTDC(num_cta, codproserv, mes);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cabecera);
    }

    public List<List<String>> consultarTablaEdoCuenta (String num_cta, String mes , String anio) throws Exception {
        final String origen = "ReportsPdfServicio.consultarTablaEdoCuenta";

        long time;

        List<List<String>> tabla = new ArrayList<List<String>>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            tabla =  reportsPdfIo.consultarTablaEdoCuenta(num_cta, mes, anio);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tabla);
    }

    public List<List<String>> consultarTablaEdoCuentaTDC (String num_cta, String codproserv , String mes) throws Exception {
        final String origen = "ReportsPdfServicio.consultarTablaEdoCuentaTDC";

        long time;

        List<List<String>> tabla = new ArrayList<List<String>>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            tabla =  reportsPdfIo.consultarTablaEdoCuentaTDC(num_cta, codproserv, mes);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tabla);
    }

    public List<String> consultarDatosCliente (String cliente) throws Exception {
        final String origen = "ReportsPdfServicio.consultarDatosCliente";

        long time;

        List<String> datos = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            datos =  reportsPdfIo.consultarDatosCliente(cliente);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (datos);
    }

    public List<String> consultarCeritificadoApertura (String codIntrumento) throws Exception{
        final String origen = "ReportsPdfServicio.consultarCeritificadoApertura";

        long time;

        List<String> cabecera = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cabecera =  reportsPdfIo.consultarCeritificadoApertura(codIntrumento);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cabecera);
    }

    public List<List<String>> consultarmovimientosCertificado (String codIntrumento,String estatus) throws Exception {
        final String origen = "ReportsPdfServicio.consultarmovimientosApertura";

        long time;

        List<List<String>> tabla = new ArrayList<List<String>>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            tabla =  reportsPdfIo.consultarmovimientosCertificado(codIntrumento,estatus);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tabla);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "ReportsPdfServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ReportsPdfServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getReportsPdfIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ReportsPdfServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
}

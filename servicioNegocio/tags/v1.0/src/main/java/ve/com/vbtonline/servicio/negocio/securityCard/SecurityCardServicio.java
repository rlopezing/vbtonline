package ve.com.vbtonline.servicio.negocio.securityCard;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.SecurityCardIo;
import ve.com.vbtonline.servicio.od.SecurityCardOd;

import java.io.Serializable;
import java.util.ResourceBundle;


/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 23/09/13
 * Time: 04:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityCardServicio implements ISecurityCardServicio, Serializable {

    private static final Logger logger = Logger.getLogger(SecurityCardServicio.class);

    private SecurityCardIo securityCardIo;

    /**
     * Constructor de la clase
     */
    public SecurityCardServicio() {
    }

    public SecurityCardOd generarTarjeta (SecurityCardOd tarjeta, String usuario, String ip) throws Exception{


        final String origen = "SecurityCardServicio.generarTarjeta";

        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + SecurityCardServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            tarjeta=getSecurityCardIo().generarTarjeta(tarjeta, usuario, ip);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + SecurityCardServicio.class + " | " + origen);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return tarjeta;
    }

    public String activarTarjeta (SecurityCardOd tarjeta, String usuario, String ip) throws Exception{

        final String origen = "SecurityCardServicio.activarTarjeta";

        long time;
        String resultado;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + SecurityCardServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            resultado=getSecurityCardIo().activarTarjeta(tarjeta, usuario, ip);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + SecurityCardServicio.class + " | " + origen);

        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return resultado;
    }
    public SecurityCardOd consultarTarjetaAsignada (String usuario) throws Exception{

        SecurityCardOd tarjeta;
        final String origen = "SecurityCardServicio.consultarTarjetaAsignada";

        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + SecurityCardServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            tarjeta=getSecurityCardIo().consultarTarjetaAsignada(usuario);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + SecurityCardServicio.class + " | " + origen);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return tarjeta;
    }


    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "SecurityCardServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityCardServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getSecurityCardIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityCardServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }


    public SecurityCardIo getSecurityCardIo() {
        return securityCardIo;
    }

    public void setSecurityCardIo(SecurityCardIo securityCardIo) {
        this.securityCardIo = securityCardIo;
    }
}



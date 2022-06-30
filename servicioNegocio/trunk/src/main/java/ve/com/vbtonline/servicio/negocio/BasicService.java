package ve.com.vbtonline.servicio.negocio;

import ve.com.vbtonline.servicio.io.CreditCardsIo;
import ve.com.vbtonline.servicio.io.LoggerIo;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 15/07/16
 * Time: 09:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicService implements Serializable {

    private LoggerIo loggerIo;

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "BasicService.guardarLog";

        long time;

        String respuesta = new String();
        try {

           // logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoggerIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

          //  logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
           // logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
        final String origen = "BasicService.guardarExcepcion";
        long time;
        Integer exitoso=0;
        String respuesta = new String();
        try {

           // logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getLoggerIo().guardarExcepcion(parametro1, parametro2,parametro3,parametro4,parametro5,parametro6);

            time = System.currentTimeMillis () - time;

           // logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
           // logger.error(ex);
        }

        return (respuesta);
    }




    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }
}

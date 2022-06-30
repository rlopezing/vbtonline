package ve.com.vbtonline.servicio.negocio.portafolio;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.PortafolioIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.od.PortafolioOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

import java.io.Serializable;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class PortafolioServicio extends BasicService implements IPortafolioServicio,Serializable {
    private static final Logger logger = Logger.getLogger(PortafolioServicio.class);

    /** El Data Access Object
     */
    private PortafolioIo portafolioIo;




    /** Constructor de la clase
     */
    public PortafolioServicio(){
    }



    public PortafolioOd cargar (String transaccionId, PortafolioOd pod, VBTUsersOd usuario) throws Exception {
        final String origen = "PortafolioServicio.cargar";

        long time;
        PortafolioOd portafolioOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+PortafolioServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            portafolioOd=new PortafolioOd();
            portafolioOd = getPortafolioIo().Cargar("",pod, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+PortafolioServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (portafolioOd);
    }
   /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "PortafolioServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+PortafolioServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getPortafolioIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+PortafolioServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }  */

    public PortafolioIo getPortafolioIo() {
        return portafolioIo;
    }

    public void setPortafolioIo(PortafolioIo portafolioIo) {
        this.portafolioIo = portafolioIo;
    }
}

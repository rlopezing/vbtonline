package ve.com.vbtonline.servicio.negocio.allMyPortafolio;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.AllMyPortafolioIo;
import ve.com.vbtonline.servicio.negocio.allMyPortafolio.IAllMyPortafolioServicio;
import ve.com.vbtonline.servicio.od.AllMyPortafolioOd;
import ve.com.vbtonline.servicio.od.TableOd;

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
public class AllMyPortafolioServicio implements IAllMyPortafolioServicio,Serializable {
    private static final Logger logger = Logger.getLogger(AllMyPortafolioServicio.class);

    /** El Data Access Object
     */
    private AllMyPortafolioIo allMyPortafolioIo;




    /** Constructor de la clase
     */
    public AllMyPortafolioServicio(){
    }



    public AllMyPortafolioOd cargar (String transaccionId, AllMyPortafolioOd ampod) throws Exception {
        final String origen = "AllMyPortafolioServicio.cargar";

        long time;
        AllMyPortafolioOd allMyPortafolioOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AllMyPortafolioServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            allMyPortafolioOd=new AllMyPortafolioOd();
            allMyPortafolioOd = getAllMyPortafolioIo().Cargar("", ampod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AllMyPortafolioServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (allMyPortafolioOd);
    }

    public List<String> cargarCarterasPortafolio (String login) throws Exception {
        final String origen = "AllMyPortafolioServicio.cargarCarterasPortafolio";

        long time;
        List<String> carteras = new ArrayList<String>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AllMyPortafolioServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            carteras = getAllMyPortafolioIo().cargarCarterasPortafolio(login);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AllMyPortafolioServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (carteras);
    }

    public TableOd cargarTablas (String cartera, String accion, String login, String idioma) throws Exception {
        final String origen = "AllMyPortafolioServicio.cargarTablas";

        long time;
        TableOd tabla = new TableOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AllMyPortafolioServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            tabla = getAllMyPortafolioIo().cargarTablas(cartera,accion,login, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AllMyPortafolioServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tabla);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "AllMyPortafolioServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AllMyPortafolioServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta =getAllMyPortafolioIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AllMyPortafolioServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public AllMyPortafolioIo getAllMyPortafolioIo() {
        return allMyPortafolioIo;
    }

    public void setAllMyPortafolioIo(AllMyPortafolioIo allMyPortafolioIo) {
        this.allMyPortafolioIo = allMyPortafolioIo;
    }
}

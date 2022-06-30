package ve.com.vbtonline.servicio.negocio.mutualFunds;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.CreditCardsIo;
import ve.com.vbtonline.servicio.io.MutualFundsIo;
import ve.com.vbtonline.servicio.negocio.creditCards.ICreditCardsServicio;
import ve.com.vbtonline.servicio.od.*;

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
public class MutualFundsServicio implements IMutualFundsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(MutualFundsServicio.class);

    /** El Data Access Object
     */
    private MutualFundsIo mutualFundsIo;




    /** Constructor de la clase
     */
    public MutualFundsServicio(){
    }



    public MutualFundsOd cargar (String transaccionId, MutualFundsOd mfod) throws Exception {
        final String origen = "MutualFundsServicio.cargar";

        long time;
        MutualFundsOd mutualFundsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            mutualFundsOd=new MutualFundsOd();
            mutualFundsOd = getMutualFundsIo().Cargar("",mfod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (mutualFundsOd);
    }

    public CuentasOd cargarFondosMutuales (String carteras, String idioma) throws Exception {
        final String origen = "MutualFundsServicio.cargarFondosMutuales";

        long time;

        CuentasOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getMutualFundsIo().cargarFondosMutuales(carteras, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTipoTransaccionBT (List<String> parametros, String Idioma) throws Exception {
        final String origen = "MutualFundsServicio.cargarTipoTransaccionBT";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getMutualFundsIo().cargarTipoTransaccionBT(parametros, Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public TableOd consultarSaldosTransFondosMutuales (List<String> parametros, String idioma) throws Exception {
        final String origen = "MutualFundsServicio.consultarSaldosTransFondosMutuales";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getMutualFundsIo().consultarSaldosTransFondosMutuales(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public TableOd consultarBloqueosFondosMutuales (List<String> parametros, String idioma) throws Exception {
        final String origen = "MutualFundsServicio.consultarBloqueosFondosMutuales";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getMutualFundsIo().consultarBloqueosFondosMutuales(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public List<String> consultarDetalleFondosMutualesBT (List<String> parametros) throws Exception {
        final String origen = "MutualFundsServicio.consultarDetalleFondosMutualesBT";

        long time;

        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getMutualFundsIo().consultarDetalleFondosMutualesBT(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "MutualFundsServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MutualFundsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getMutualFundsIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MutualFundsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public MutualFundsIo getMutualFundsIo() {
        return mutualFundsIo;
    }

    public void setMutualFundsIo(MutualFundsIo mutualFundsIo) {
        this.mutualFundsIo = mutualFundsIo;
    }
}

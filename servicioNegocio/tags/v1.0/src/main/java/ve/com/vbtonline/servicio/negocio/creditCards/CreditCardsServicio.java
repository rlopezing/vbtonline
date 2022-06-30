package ve.com.vbtonline.servicio.negocio.creditCards;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.CreditCardsIo;
import ve.com.vbtonline.servicio.negocio.creditCards.ICreditCardsServicio;
import ve.com.vbtonline.servicio.od.CreditCardsOd;
import ve.com.vbtonline.servicio.od.SelectOd;
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
public class CreditCardsServicio implements ICreditCardsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(CreditCardsServicio.class);

    /** El Data Access Object
     */
    private CreditCardsIo creditCardsIo;




    /** Constructor de la clase
     */
    public CreditCardsServicio(){
    }



    public CreditCardsOd cargar (String transaccionId, CreditCardsOd ccod) throws Exception {
        final String origen = "CreditCardsServicio.cargar";

        long time;
        CreditCardsOd creditCardsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            creditCardsOd=new CreditCardsOd();
            creditCardsOd = getCreditCardsIo().Cargar("",ccod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (creditCardsOd);
    }

    public TableOd consultarSaldosEstadoCuentaTDC (List<String> parametros, String idioma) throws Exception {
        final String origen = "CreditCardsServicio.consultarSaldosEstadoCuentaTDC";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getCreditCardsIo().consultarSaldosEstadoCuentaTDC(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public TableOd cargarMovimientosTDCITT (List<String> parametros, String idioma) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovimientosTDCITT";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getCreditCardsIo().cargarMovimientosTDCITT(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public List<String> consultarDetallesEstadoCuentaTDC (List<String> parametros) throws Exception {
        final String origen = "CreditCardsServicio.consultarDetallesEstadoCuentaTDC";

        long time;

        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getCreditCardsIo().consultarDetallesEstadoCuentaTDC(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }

    public SelectOd cargarMesesEstadoCuentaTDC (String nroCuenta) throws Exception {
        final String origen = "CreditCardsServicio.cargarMesesEstadoCuentaTDC";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarMesesEstadoCuentaTDC(nroCuenta);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTarjetasEstadoCuentaTDC (String carteras, String idioma) throws Exception {
        final String origen = "CreditCardsServicio.cargarTarjetasEstadoCuentaTDC";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTarjetasEstadoCuentaTDC(carteras, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTarjetasTDCITT (String carteras, String idioma) throws Exception {
        final String origen = "CreditCardsServicio.cargarTarjetasTDCITT";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getCreditCardsIo().cargarTarjetasTDCITT(carteras, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "CreditCardsServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getCreditCardsIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }


    public CreditCardsIo getCreditCardsIo() {
        return creditCardsIo;
    }

    public void setCreditCardsIo(CreditCardsIo creditCardsIo) {
        this.creditCardsIo = creditCardsIo;
    }
}

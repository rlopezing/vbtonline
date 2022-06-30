package ve.com.vbtonline.servicio.negocio.otherInvestments;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.MutualFundsIo;
import ve.com.vbtonline.servicio.io.OtherInvestmentsIo;
import ve.com.vbtonline.servicio.negocio.otherInvestments.IOtherInvestmentsServicio;
import ve.com.vbtonline.servicio.od.CuentasOd;
import ve.com.vbtonline.servicio.od.MutualFundsOd;
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
public class OtherInvestmentsServicio implements IOtherInvestmentsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(OtherInvestmentsServicio.class);

    /** El Data Access Object
     */
    private OtherInvestmentsIo otherInvestmentsIo;




    /** Constructor de la clase
     */
    public OtherInvestmentsServicio(){
    }



    public MutualFundsOd cargar (String transaccionId, MutualFundsOd mfod) throws Exception {
        final String origen = "OtherInvestmentsServicio.cargar";

        long time;
        MutualFundsOd mutualFundsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            mutualFundsOd=new MutualFundsOd();
            mutualFundsOd = getOtherInvestmentsIo().Cargar("",mfod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (mutualFundsOd);
    }

    public CuentasOd cargarOtrasInversiones (String carteras, String idioma) throws Exception {
        final String origen = "OtherInvestmentsServicio.cargarOtrasInversiones";

        long time;

        CuentasOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getOtherInvestmentsIo().cargarOtrasInversiones(carteras, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarTipoTransaccionBT (List<String> parametros) throws Exception {
        final String origen = "OtherInvestmentsServicio.cargarTipoTransaccionBT";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getOtherInvestmentsIo().cargarTipoTransaccionBT(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public TableOd consultarSaldosTransOtrasInversiones (List<String> parametros, String idioma) throws Exception {
        final String origen = "OtherInvestmentsServicio.consultarSaldosTransOtrasInversiones";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getOtherInvestmentsIo().consultarSaldosTransOtrasInversiones(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public TableOd consultarBloqueosOtrasInversiones (List<String> parametros, String idioma) throws Exception {
        final String origen = "OtherInvestmentsServicio.consultarBloqueosOtrasInversiones";

        long time;

        TableOd listaMovimientos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMovimientos = getOtherInvestmentsIo().consultarBloqueosOtrasInversiones(parametros, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMovimientos);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "OtherInvestmentsServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getOtherInvestmentsIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<String> consultarDetalleOtrasInversionesBT (List<String> parametros) throws Exception {
        final String origen = "OtherInvestmentsServicio.consultarDetalleOtrasInversionesBT";

        long time;

        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+OtherInvestmentsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDetalles = getOtherInvestmentsIo().consultarDetalleOtrasInversionesBT(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+OtherInvestmentsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDetalles);
    }

    public OtherInvestmentsIo getOtherInvestmentsIo() {
        return otherInvestmentsIo;
    }

    public void setOtherInvestmentsIo(OtherInvestmentsIo otherInvestmentsIo) {
        this.otherInvestmentsIo = otherInvestmentsIo;
    }
}

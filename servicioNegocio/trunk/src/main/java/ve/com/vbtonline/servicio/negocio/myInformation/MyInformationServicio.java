package ve.com.vbtonline.servicio.negocio.myInformation;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.MyInformationIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.od.MyInformationOd;
import ve.com.vbtonline.servicio.od.TableOd;
import ve.com.vbtonline.servicio.od.TableOdDetallesOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

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
public class MyInformationServicio extends BasicService implements IMyInformationServicio,Serializable {
    private static final Logger logger = Logger.getLogger(MyInformationServicio.class);

    /** El Data Access Object
     */
    private MyInformationIo myInformationIo;




    /** Constructor de la clase
     */
    public MyInformationServicio(){
    }



    public MyInformationOd cargar (String transaccionId, MyInformationOd miod, VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.cargar";

        long time;
        MyInformationOd myInformationOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            myInformationOd=new MyInformationOd();
            myInformationOd = getMyInformationIo().Cargar("",miod,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (myInformationOd);
    }

    public TableOd consultarTelefonos (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.consultarTelefonos";

        long time;

        TableOd info = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            info = getMyInformationIo().consultarTelefonos(parametros, idioma,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (info);
    }

    public TableOd consultarDirecciones (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.consultarTelefonos";

        long time;

        TableOd info = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            info = getMyInformationIo().consultarDirecciones(parametros, idioma,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (info);
    }

    public TableOd consultarDocumentos (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.consultarDocumentos";

        long time;

        TableOd info = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            //info = getMyInformationIo().consultarDocumentos(parametros, idioma,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (info);
    }

    public TableOd consultarRepresentantes (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.consultarRepresentantes";

        long time;

        TableOd info = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            info = getMyInformationIo().consultarRepresentantes(parametros, idioma,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (info);
    }

    public TableOd consultarContactos (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.consultarContactos";

        long time;

        TableOd info = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            info = getMyInformationIo().consultarContactos(parametros, idioma,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (info);
    }

    public TableOdDetallesOd consultarCarteras (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "MyInformationServicio.consultarCarteras";

        long time;

//        TableOd info = null;
        TableOdDetallesOd info = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            info = getMyInformationIo().consultarCarteras(parametros, idioma,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (info);
    }
   /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "MyInformationServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+MyInformationServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getMyInformationIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+MyInformationServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }    */

    public MyInformationIo getMyInformationIo() {
        return myInformationIo;
    }

    public void setMyInformationIo(MyInformationIo myInformationIo) {
        this.myInformationIo = myInformationIo;
    }
}

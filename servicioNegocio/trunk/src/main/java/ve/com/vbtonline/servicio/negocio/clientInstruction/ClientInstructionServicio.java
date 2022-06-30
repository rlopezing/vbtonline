package ve.com.vbtonline.servicio.negocio.clientInstruction;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.ClientInstructionIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.od.*;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 23/09/13
 * Time: 04:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientInstructionServicio extends BasicService implements IClientInstructionServicio, Serializable {

    private static final Logger logger = Logger.getLogger(ClientInstructionServicio.class);

    private ClientInstructionIo clientInstructionIo;

    /**
     * Constructor de la clase
     */
    public ClientInstructionServicio() {
    }

    public List<ContentSelectOd> cargarClientes(String numContrato, String idioma, VBTUsersOd usuario) throws Exception {


        final String origen = "ClientInstructionServicio.cargarClientes";

        long time;
        List<ContentSelectOd> cuentas;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            cuentas = getClientInstructionIo().consultarListaClientes(numContrato, usuario);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + ClientInstructionServicio.class + " | " + origen);

        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return cuentas;
    }

    public List<ContentSelectOd> cargarListaVencimientos(String idioma, VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionServicio.cargarListaVencimientos";

        long time;
        List<ContentSelectOd> cuentas;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            cuentas = getClientInstructionIo().consultarListaVencimientos(usuario);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + ClientInstructionServicio.class + " | " + origen);

        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return cuentas;
    }

    public List<ContentSelectOd> cargarListaTipoTransf(String idioma, VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionServicio.cargarListaTipoTransf";

        long time;
        List<ContentSelectOd> cuentas;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            cuentas = getClientInstructionIo().consultarListaTipoTransf(usuario);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + ClientInstructionServicio.class + " | " + origen);

        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return cuentas;
    }

    public List<ContentSelectOd> cargarListaMaxCartas(String idioma, VBTUsersOd usuario) throws Exception {

        final String origen = "ClientInstructionServicio.cargarListaMaxCartas";

        long time;
        List<ContentSelectOd> cuentas;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            cuentas = getClientInstructionIo().consultarListaMaxCartas(usuario);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + ClientInstructionServicio.class + " | " + origen);

        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return cuentas;
    }

    public List<String> insertarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, VBTUsersOd usuarioOd) throws Exception{


        final String origen = "ClientInstructionServicio.insertarNumeroControl";

        long time;
        List<String> numeros;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            numeros = getClientInstructionIo().insertarNumeroControl(carta, sessionId, usuario, ip, usuarioOd);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + ClientInstructionServicio.class + " | " + origen+"numeros "+numeros.size());

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return numeros;
    }

    public boolean eliminarNumeroControl(List<String> numeros, VBTUsersOd usuario) throws Exception{


        final String origen = "ClientInstructionServicio.eliminarNumeroControl";

        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

             getClientInstructionIo().eliminarNumeroControl(numeros, usuario);

            time = System.currentTimeMillis() - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + ClientInstructionServicio.class + " | " + origen);

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return true;
    }

    public String evaluarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, String numeroControl, VBTUsersOd usuarioOd) throws Exception{


        final String origen = "ClientInstructionServicio.evaluarNumeroControl";

        long time;
        String respuesta = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            respuesta = getClientInstructionIo().evaluarNumeroControl(carta,sessionId,usuario,ip,numeroControl, usuarioOd);

            time = System.currentTimeMillis() - time;

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return (respuesta);
        }

    public String anularNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, String numeroControl, String login, VBTUsersOd usuarioOd) throws Exception{


        final String origen = "ClientInstructionServicio.anularNumeroControl";

        long time;
        String respuesta = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + ClientInstructionServicio.class + " | " + origen);
            time = System.currentTimeMillis();

            respuesta = getClientInstructionIo().anularNumeroControl(carta,sessionId,usuario,ip,numeroControl,login, usuarioOd);

            time = System.currentTimeMillis() - time;

        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return (respuesta);
    }

    public SelectOd cargarMotivos (VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "ClientInstructionServicio.cargarMotivos";

        long time;
        SelectOd motivos = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ClientInstructionServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            motivos= getClientInstructionIo().cargarMotivos(usuario,Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ClientInstructionServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (motivos);
    }
     /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "ClientInstructionServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ClientInstructionServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getClientInstructionIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+ClientInstructionServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    } */

    /**
     * El Data Access Object
     */
    public ClientInstructionIo getClientInstructionIo() {
        return clientInstructionIo;
    }

    public void setClientInstructionIo(ClientInstructionIo clientInstructionIo) {
        this.clientInstructionIo = clientInstructionIo;
    }
}


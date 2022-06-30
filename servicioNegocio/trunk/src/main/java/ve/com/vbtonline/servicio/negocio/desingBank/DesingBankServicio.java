package ve.com.vbtonline.servicio.negocio.desingBank;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.AccountsIo;
import ve.com.vbtonline.servicio.io.DesingBankIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.od.*;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;
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
public class DesingBankServicio extends BasicService implements IDesingBankServicio,Serializable {
    private static final Logger logger = Logger.getLogger(DesingBankServicio.class);

    /** El Data Access Object
     */
    private DesingBankIo desingBankIo;




    /** Constructor de la clase
     */
    public DesingBankServicio(){
    }



    public String cambiarClave (String clave, String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "DesingBankServicio.cambiarClave";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().cambiarClave(clave, login, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String verificarClave (String clave, String login) throws Exception {
        final String origen = "DesingBankServicio.verificarClave";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().verificarClave(clave, login);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public ParametrosPersonalesOd cargarParametrosPersonales (List<String> parametros) throws Exception {
        final String origen = "DesingBankServicio.cargarParametrosPersonales";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().cargarParametrosPersonales(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public ParametrosPersonalesOd cargarParametrosPersonalesBase (String tipo) throws Exception {
        final String origen = "DesingBankServicio.cargarParametrosPersonalesBase";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().cargarParametrosPersonalesBase(tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String guardarParametrosPersonales (ParametrosPersonalesOd parametrosPersonalesOd) throws Exception {
        final String origen = "DesingBankServicio.guardarParametrosPersonales";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().guardarParametrosPersonales(parametrosPersonalesOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
     /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "DesingBankServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    } */


    public String correoClientePrincipal(VBTUsersOd usuario, String idioma, ParametrosPersonalesOd paramPersonales) throws Exception {
        final String origen = "DesingBankServicio.correoClientePrincipal";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().correoClientePrincipal(usuario, idioma, paramPersonales);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

       public BufferedImage cargarImagenHome () throws Exception {
        final String origen = "DesingBankServicio.cargarImagenHome";
        BufferedImage respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().cargarImagenHome();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public BufferedImage cargarImagenID (String id) throws Exception {
        final String origen = "DesingBankServicio.cargarImagenID";
        BufferedImage respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().cargarImagenID(id);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }



    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7, String parametro8) throws Exception {
        final String origen = "DesingBankServicio.guardarLogFC";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+DesingBankServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getDesingBankIo().GuardarLogFC(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7,parametro8);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+DesingBankServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }




    public DesingBankIo getDesingBankIo() {
        return desingBankIo;
    }

    public void setDesingBankIo(DesingBankIo desingBankIo) {
        this.desingBankIo = desingBankIo;
    }





}

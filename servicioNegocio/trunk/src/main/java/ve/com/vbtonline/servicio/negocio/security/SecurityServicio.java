package ve.com.vbtonline.servicio.negocio.security;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.DesingBankIo;
import ve.com.vbtonline.servicio.io.SecurityIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.negocio.desingBank.IDesingBankServicio;
import ve.com.vbtonline.servicio.od.AccountsOd;
import ve.com.vbtonline.servicio.od.SecurityCardOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

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
public class SecurityServicio extends BasicService implements ISecurityServicio,Serializable {
    private static final Logger logger = Logger.getLogger(SecurityServicio.class);

    /** El Data Access Object
     */
    private SecurityIo securityIo;




    /** Constructor de la clase
     */
    public SecurityServicio(){
    }



    public AccountsOd cargar (String transaccionId, AccountsOd aod,VBTUsersOd usuario) throws Exception {
        final String origen = "DesingBankServicio.cargar";

        long time;
        AccountsOd accountsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            accountsOd=new AccountsOd();
            accountsOd = getSecurityIo().Cargar("",aod, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (accountsOd);
    }

    public String crearClaveRamdom (VBTUsersOd usuario, String idioma) throws Exception{
        final String origen = "SecurityServicio.crearClaveRamdom";

        long time;
        String clave = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


           clave = getSecurityIo().crearClaveRamdom(usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (clave);
    }

    public String crearClaveRamdomSMS (VBTUsersOd usuario, String idioma) throws Exception{
        final String origen = "SecurityServicio.crearClaveRamdomSMS";

        long time;
        String clave = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            clave = getSecurityIo().crearClaveRamdomSMS(usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (clave);
    }

    public String crearClaveRamdom2 (VBTUsersOd usuario) throws Exception{
        final String origen = "SecurityServicio.crearClaveRamdom2";

        long time;
        String clave = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


           clave = getSecurityIo().crearClaveRamdom2(usuario);

            //logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ "**** clave ramdon : "+ clave);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (clave);
    }

    public String cambiarClaveOpeEsp (String clave, String login, VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.cambiarClaveOpeEsp";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().cambiarClaveOpeEsp(clave, login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String salvarNuevoPassword (String clave, String login,VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.salvarNuevoPassword";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().salvarNuevoPassword(clave, login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String cargarTarjeta (String login, VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.cargarTarjeta";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().cargarTarjeta(login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<String> obtenerEmailContrato (String login, VBTUsersOd usuario) throws Exception{
        final String origen = "SecurityServicio.obtenerEmailContrato";

        long time;
        List<String> respuesta = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().obtenerEmailContrato(login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public SecurityCardOd cargarCoordenadas (String login, String ip, VBTUsersOd usuario) throws Exception{
        final String origen = "SecurityServicio.cargarCoordenadas";

        long time;
        SecurityCardOd respuesta = new SecurityCardOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().cargarCoordenadas(login,ip, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String validarCoordenadas (String login, String ip, String fila, String columna, String valor,String fila2, String columna2, String valor2, VBTUsersOd usuario) throws Exception{
        final String origen = "SecurityServicio.validarCoordenadas";

        long time;
        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().validarCoordenadas(login,ip, fila, columna, valor,fila2,columna2,valor2, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String cambiarClaveOpeEspPersonal (String clave, String login, VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.cambiarClaveOpeEspPersonal";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().cambiarClaveOpeEspPersonal(clave, login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String verificarSiClaveProvisonalOpeEsp (String login, VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.verificarSiClaveProvisonalOpeEsp";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().verificarSiClaveProvisonalOpeEsp(login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String bloquearUsuario (String login, VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.bloquearUsuario";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().bloquearUsuario(login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String verificarClaveOpeEsp (String login, String clave, VBTUsersOd usuario) throws Exception {
        final String origen = "SecurityServicio.verificarClaveOpeEsp";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().verificarClaveOpeEsp(login, clave, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
    /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "SecurityServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getSecurityIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    } */


    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7, String parametro8) throws Exception {
        final String origen = "SecurityServicio.guardarLogFC";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getSecurityIo().GuardarLogFC(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7, parametro8);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String correoClientePrincipal(String usuario,String idioma, String text, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "FirmasConjuntasServicio.correoClientePrincipal";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getSecurityIo().correoClientePrincipal(usuario, idioma, text, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<String> carterasUsuario (String login, VBTUsersOd usuario) throws Exception{
        final String origen = "carterasUsuario.obtenerEmailContrato";

        long time;
        List<String> respuesta = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().carterasUsuario(login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String emailEjecutivos (String carteras, VBTUsersOd usuario) throws Exception{
        final String origen = "securityIo.carterasUsuario";

        long time;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+SecurityServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getSecurityIo().emailEjecutivos(carteras, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+SecurityServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }


    public SecurityIo getSecurityIo() {
        return securityIo;
    }

    public void setSecurityIo(SecurityIo securityIo) {
        this.securityIo = securityIo;
    }
}

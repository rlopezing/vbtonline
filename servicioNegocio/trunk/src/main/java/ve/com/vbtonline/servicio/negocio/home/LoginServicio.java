package ve.com.vbtonline.servicio.negocio.home;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.LoginIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.od.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 20/05/2010
 * Time: 02:52:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServicio extends BasicService implements ILoginServicio,Serializable {
    private static final Logger logger = Logger.getLogger(LoginServicio.class);

    /** El Data Access Object
     */
    private LoginIo loginIo;




    /** Constructor de la clase
     */
    public LoginServicio (){
    }



    public VBTUsersOd validarUsuario (VBTUsersOd u) throws Exception {
        final String origen = "loginServicio.validarUsuario";

        long time;
        VBTUsersOd Usuario = null;
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Usuario = getLoginIo().validarUsuario(u);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            if (!ex.getMessage().equals("CLAVE INVALIDA")){
                logger.error(ex);
            }


            throw (new Exception (ex.getMessage(),null));
        }

        return (Usuario);
    }

    public String logout (String login) throws Exception {
        final String origen = "loginServicio.logout";

        long time;
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getLoginIo().logout(login);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public boolean validarTerminosCondiciones (String contrato, VBTUsersOd usuario) throws Exception {
        final String origen = "loginServicio.logout";

        long time;
        boolean respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta = getLoginIo().validarTerminosCondiciones(contrato, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public VBTUsersOd validarUsuario2 (String login, VBTUsersOd usuario) throws Exception {
        final String origen = "loginServicio.validarUsuario2";

        long time;
        VBTUsersOd Usuario = null;
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Usuario = getLoginIo().validarUsuario2(login, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            if (!ex.getMessage().equals("CLAVE INVALIDA")){
             logger.error(ex);
            }
             throw (new Exception (ex.getMessage(),null));

        }

        return (Usuario);
    }

    public VBTUsersOd cargarUsuario (VBTUsersOd u) throws Exception {
        final String origen = "loginServicio.cargarUsuario";

        long time;
        VBTUsersOd Usuario = null;
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Usuario = getLoginIo().cargarUsuario(u);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Usuario);
    }

    public String cargarCarteras (String numContrato,VBTUsersOd usuario) throws Exception {
        final String origen = "loginServicio.cargarCarteras";

        long time;
        String carteras = new String();
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            carteras = getLoginIo().cargarCarteras(numContrato, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (carteras);
    }

    public List<String> cargarInfoHome () throws Exception {
        final String origen = "loginServicio.cargarInfoHome";

        long time;
        List<String> texto = new ArrayList<String>();
        String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            texto = getLoginIo().cargarInfoHome();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (texto);
    }


    public List<String> cargarAccionesSistema (VBTUsersOd usuario) throws Exception {
        final String origen = "loginServicio.cargarAccionesSistema";

        long time;
        List<String> accionesSistema = new ArrayList<String>();
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            accionesSistema = getLoginIo().cargarAccionesSistema(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (accionesSistema);
    }

    public List<CalendarioOd> cargarCalendario (Integer mes, Integer ano, Integer dia, VBTUsersOd usuario) throws Exception {
        final String origen = "loginServicio.cargarCalendario";

        long time;
        List<CalendarioOd> listaFeriados = new ArrayList<CalendarioOd>();
         String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            listaFeriados = getLoginIo().cargarCalendario(mes,ano,dia, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaFeriados);
    }

    public VBTUsersOd loginOpeEsp (VBTUsersOd u) throws Exception {
        final String origen = "loginServicio.loginOpeEsp";

        long time;
        VBTUsersOd Usuario = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            Usuario=new VBTUsersOd();
            Usuario = getLoginIo().loginOpeEsp(u);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Usuario);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "loginServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoginIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception {
        final String origen = "loginServicio.guardarLogFC";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoginIo().GuardarLogFC(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7,parametro8);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<ItemOd> Home (String transaccionId, VBTUsersOd u) throws Exception {
        final String origen = "loginServicio.Home";

        long time;
        UsuarioOd Usuario = null;

        List<ItemOd> items;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + LoginServicio.class + " | " + origen);

            time = System.currentTimeMillis();

            items = getLoginIo().home(transaccionId, u);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + LoginServicio.class + " | " + origen);
        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return (items);
    }

    public LoginIo getLoginIo() {
        return loginIo;
    }

    public void setLoginIo(LoginIo loginIo) {
        this.loginIo = loginIo;
    }

    public List<PrivilegioOd> consultarGrupoOpcPermisos (VBTUsersOd u) throws Exception {
        final String origen = "loginServicio.consultarGrupoOpcPermisos";

        long time;

        List<PrivilegioOd> items;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser") + LoginServicio.class + " | " + origen);

            time = System.currentTimeMillis();

            items = getLoginIo().consultarGrupoOpcPermisos(u);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser") + LoginServicio.class + " | " + origen);
        } catch (Exception ex) {
            logger.error(ex);
            throw (new Exception(ex.getMessage(), null));
        }

        return (items);
    }

    /**
     *      FUNCIÓN:        contarSolicitudesPendientes(String, String)
     *      AUTOR:          RRANGEL
     *      FECHA:          10/10/2014
     *      DESCRIPCIÓN:    Implementación de la firma de función en IFirmasConjuntasServicio.class.
     * */
    public String contarSolicitudesPendientes(VBTUsersOd usuario, String contrato, String estatus) throws Exception {

        final String origen = "FirmasConjuntasServicio.consultarElementos";

        long time;

        String cantidadSolicitudesPendientes = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cantidadSolicitudesPendientes = getLoginIo().contarSolicitudesPendientes(usuario, contrato, estatus);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return cantidadSolicitudesPendientes;
    }
    /** FIN contarSolicitudesPendientes(String, String) */

    public String contarPagosTdcPendientes(String carteras, VBTUsersOd usuario, String estatus) throws Exception {

        final String origen = "LoginServicio.contarPagosTdcPendientes";

        long time;

        String cantidadPagosTdcPendientes = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cantidadPagosTdcPendientes = getLoginIo().contarPagosTdcPendientes(carteras, usuario, estatus);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return cantidadPagosTdcPendientes;
    }

    public String consultarRolesAprobarLiberar(VBTUsersOd usuario) throws Exception {

        final String origen = "FirmasConjuntasServicio.consultarRolesAprobarLiberar";

        long time;

        String roles = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            roles = getLoginIo().consultarRolesAprobarLiberar(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return roles;
    }

    public String registrarCodigoAct(VBTUsersOd usuario,String Codigo)  throws Exception {

        final String origen = "loginServicio.registrarCodigoAct";

        long time;

        String registrarCodigo = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            registrarCodigo = getLoginIo().registrarCodigoActualizacion(usuario.getCodpercli(),usuario.getLogin(),Codigo,usuario.getNumContrato());

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return registrarCodigo;
    }

    public String validarAct(VBTUsersOd usuario)  throws Exception {

        final String origen = "loginServicio.validarAct";

        long time;

        String validaCodigo = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            validaCodigo = getLoginIo().validarCodigoActualizacion(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return validaCodigo;
    }

    public String updateFechaAnuncio(VBTUsersOd usuario)  throws Exception {

        final String origen = "loginServicio.validarAct";

        long time;

        String fechaAnuncio = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            fechaAnuncio = getLoginIo().upateFechaAnuncioActualizacion(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return fechaAnuncio;
    }

    public ParametrosPersonalesOd cargarParametrosContratos (String contrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "loginServicio.cargarParametrosContratos";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoginIo().cargarParametrosContratos(contrato, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
    public String consultarMotvosAct (String contrato) throws Exception{

        final String origen = "loginServicio.consultarMotvosAct";

        long time;
        String respuesta;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoginIo().consultarMotivosAct(contrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String validarUsuarioCorreo(String usuario, String correo) throws Exception{

        final String origen = "loginServicio.validarUsuarioCorreo";

        long time;
        String respuesta;

        System.out.println(this.getClass() + "<<<< loginServicio Usuario -> " + usuario + " <===> Correo -> " + correo);
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getLoginIo().validarUsuarioCorreo(usuario, correo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String validarExcluidoAct(VBTUsersOd usuario)  throws Exception {

        final String origen = "loginServicio.validarExcluidoAct";

        long time;

        String excluido = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+LoginServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            excluido = getLoginIo().validarExcluidoAct(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+LoginServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return excluido;
    }
}

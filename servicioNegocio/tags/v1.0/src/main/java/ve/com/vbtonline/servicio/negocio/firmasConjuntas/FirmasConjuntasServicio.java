package ve.com.vbtonline.servicio.negocio.firmasConjuntas;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.FirmasConjuntasIo;
import ve.com.vbtonline.servicio.negocio.firmasConjuntas.IFirmasConjuntasServicio;
import ve.com.vbtonline.servicio.od.*;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class FirmasConjuntasServicio implements IFirmasConjuntasServicio,Serializable {
    private static final Logger logger = Logger.getLogger(FirmasConjuntasServicio.class);

    /** El Data Access Object
     */
    private FirmasConjuntasIo firmasConjuntasIo;

    /** Constructor de la clase
     */
    public FirmasConjuntasServicio(){
    }

    public TableOd consultarUsuarios (ConsultUsersOd consulta, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "FirmasConjuntasServicio.consultarUsuarios";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getFirmasConjuntasIo().consultarUsuarios(consulta, usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public VBTUsersOd consultarUsuario (String id, String tipoGrupo) throws Exception {
        final String origen = "FirmasConjuntasServicio.consultarUsuario";

        long time;

        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = getFirmasConjuntasIo().consultarUsuario(id, tipoGrupo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (usuario);
    }

    public String editarUsuario (VBTUsersOd usuario,VBTUsersOd usuarioAnterior,String idioma) throws Exception {
        final String origen = "FirmasConjuntasServicio.editarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().editarUsuario(usuario, usuarioAnterior, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String agregarUsuario (VBTUsersOd usuario, VBTUsersOd usuarioCreador, String idioma) throws Exception {
        final String origen = "FirmasConjuntasServicio.agregarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().agregarUsuario(usuario, usuarioCreador, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String correoClientePrincipal(VBTUsersOd usuario,String idioma) throws Exception {
        final String origen = "FirmasConjuntasServicio.correoClientePrincipal";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().correoClientePrincipal(usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
    public String consultarCarterasContrato (String codcontrato) throws Exception{
        final String origen = "FirmasConjuntasServicio.consultarCarterasContrato";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().consultarCarterasContrato(codcontrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public SelectOd consultarElementos (String tipo, String idioma) throws Exception {
        final String origen = "FirmasConjuntasServicio.consultarElementos";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getFirmasConjuntasIo().cargarElementosTipos(tipo, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd cargarGrupoClientes () throws Exception {
        final String origen = "FirmasConjuntasServicio.cargarGrupoClientes";

        long time;

        SelectOd listaGruposClientes = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaGruposClientes = getFirmasConjuntasIo().cargarGrupoClientes();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaGruposClientes);
    }

    public SelectOd cargarTipoCiRif () throws Exception {
        final String origen = "FirmasConjuntasServicio.cargarTipoCiRif";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getFirmasConjuntasIo().cargarTipoCiRif();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "FirmasConjuntasServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }


    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7, String parametro8) throws Exception {
        final String origen = "FirmasConjuntasServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().GuardarLogFC(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7,parametro8);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }


    public FirmasConjuntasIo getFirmasConjuntasIo() {
        return firmasConjuntasIo;
    }

    public void setFirmasConjuntasIo(FirmasConjuntasIo firmasConjuntasIo) {
        this.firmasConjuntasIo = firmasConjuntasIo;
    }

    public ParametrosPersonalesFCOd cargarParametrosPersonales (List<String> parametros) throws Exception {
        final String origen = "FirmasConjuntasServicio.cargarParametrosPersonales";

        long time;
        ParametrosPersonalesFCOd respuesta = new ParametrosPersonalesFCOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().cargarParametrosPersonales(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public ParametrosPersonalesFCOd cargarParametrosPersonalesBase () throws Exception {
        final String origen = "FirmasConjuntasServicio.cargarParametrosPersonalesBase";

        long time;
        ParametrosPersonalesFCOd respuesta = new ParametrosPersonalesFCOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().cargarParametrosPersonalesBase();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String guardarParametrosPersonales (ParametrosPersonalesFCOd parametrosPersonalesOd) throws Exception {
        final String origen = "FirmasConjuntasServicio.guardarParametrosPersonales";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+FirmasConjuntasServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getFirmasConjuntasIo().guardarParametrosPersonales(parametrosPersonalesOd);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+FirmasConjuntasServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
}

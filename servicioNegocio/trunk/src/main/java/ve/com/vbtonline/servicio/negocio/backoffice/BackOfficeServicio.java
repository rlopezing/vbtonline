package ve.com.vbtonline.servicio.negocio.backoffice;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.BackOfficeIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.negocio.creditCards.CreditCardsServicio;
import ve.com.vbtonline.servicio.od.*;


import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackOfficeServicio extends BasicService implements IBackOfficeServicio,Serializable {
    private static final Logger logger = Logger.getLogger(BackOfficeServicio.class);

    /** El Data Access Object
     */
    private BackOfficeIo backOfficeIo;




    /** Constructor de la clase
     */
    public BackOfficeServicio (){
    }



    public BackOfficeOd cargar (String transaccionId, BackOfficeOd bod) throws Exception {
        final String origen = "BackOfficeServicio.cargar";

        long time;
        BackOfficeOd backOfficeOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            backOfficeOd=new BackOfficeOd();
            backOfficeOd = getBackOfficeIo().Cargar("",bod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (backOfficeOd);
    }

    public CtaOpcOd opcionesGrupo (String transaccionId, CtaOpcOd ctod, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.opcionesGrupo";

        long time;
        CtaOpcOd ctaOpcOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            ctaOpcOd=new CtaOpcOd();
            ctaOpcOd = getBackOfficeIo().opcionesGrupo("",ctod, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (ctaOpcOd);
    }

    public TableOd consultarGrupos (VBTUsersOd usuarioSesion) throws Exception {       //VBTUsersOd usuarioSesion
        final String origen = "BackOfficeServicio.cargar";

        long time;

        TableOd listaGrupos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaGrupos = getBackOfficeIo().consultarGrupos(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaGrupos);
    }


    public TableOd consultarRolesFC (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarRolesFC";

        long time;

        TableOd listaRoles = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaRoles = getBackOfficeIo().consultarRolesFC(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaRoles);
    }

    public TableOd consultarGrupoOpcionesSistema (String categoria,VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarGrupoOpcionesSistema";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarGrupoOpcionesSistema(categoria, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }

    /**
     *      TAG:            consultarGrupoOpcionesSistemaEsquema
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/06
     *      DESCRIPCIÓN:
     *
     * */
    public TableOd consultarGrupoOpcionesSistemaEsquema (String categoria, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarGrupoOpcionesSistemaEsquema";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarGrupoOpcionesSistemaEsquema(categoria, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }



    public TableOd consultarRolOpcionesSistemaEsquema (String categoria, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarRolOpcionesSistemaEsquema";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarRolOpcionesSistemaEsquema(categoria, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }

    /** FIN consultarGrupoOpcionesSistemaEsquema */

    /**
     *      TAG:            consultarOpcionesEspeciales
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/06
     *      DESCRIPCIÓN:
     *
     * */
    public TableOd consultarOpcionesEspeciales (String login, VBTUsersOd usuarioSesion, String tipo) throws Exception {
        final String origen = "BackOfficeServicio.consultarOpcionesEspeciales";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarOpcionesEspeciales(login, usuarioSesion, tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        } catch (Exception ex) {
            logger.error(ex,ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }
    /** FIN consultarOpcionesEspeciales */

    /**
     *      TAG:            consultarAccesos
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/06
     *      DESCRIPCIÓN:
     *
     * */
    public TableOd consultarAccesos (String login, VBTUsersOd usuarioSesion, String tipo) throws Exception {
        final String origen = "BackOfficeServicio.consultarAccesos";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarAccesos(login, usuarioSesion, tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        } catch (Exception ex) {
            logger.error(ex,ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }
    /** FIN consultarAccesos */

    /**
     *      TAG:            actualizarGrupoOpcionesSistemaEsquema
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/10
     *      DESCRIPCIÓN:
     *
     * */
    public String actualizarGrupoOpcionesSistemaEsquema(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarGrupoOpcionesSistemaEsquema";
        String resultado = "";
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            resultado = getBackOfficeIo().actualizarGrupoOpcionesSistemaEsquema(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (resultado);
    }
    /** FIN actualizarGrupoOpcionesSistemaEsquema */
    public String actualizarRolOpcionesSistemaEsquema(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.actualizarRolOpcionesSistemaEsquema";
        String resultado = "";
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            resultado = getBackOfficeIo().actualizarRolOpcionesSistemaEsquema(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (resultado);
    }
    /**
     *      TAG:            actualizarGrupoOpcionesSistemaEsquema
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/10
     *      DESCRIPCIÓN:
     *
     * */
    public String actualizarOpcionesEspecialesSistema(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.actualizarOpcionesEspecialesSistema";
        String resultado = "";
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            resultado = getBackOfficeIo().actualizarOpcionesEspecialesSistema(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex,ex); throw (new Exception (ex.getMessage(),null));
        }

        return (resultado);
    }
    /** FIN actualizarGrupoOpcionesSistemaEsquema */

    public TableOd consultarOpcionesSistema (String strCmbTipoUsuarioEditar, VBTUsersOd usuarioSesion) throws Exception {

        final String origen = "BackOfficeServicio.consultarOpcionesSistema";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarOpcionesSistema(strCmbTipoUsuarioEditar, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }

    public TableOd consultarGrupoOpcPermisos (String codGrupo, String codOpcion, List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarGrupoOpcPermisos";

        long time;

        TableOd accionesLista = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            accionesLista = getBackOfficeIo().consultarGrupoOpcPermisos(codGrupo,codOpcion, acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (accionesLista);
    }

    public TableOd consultarOpcPermisos (String usuario, String codOpcion, List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarOpcPermisos";

        long time;

        TableOd accionesLista = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            accionesLista = getBackOfficeIo().consultarOpcPermisos(usuario,codOpcion, acciones,usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (accionesLista);
    }

    public TableOd consultarUsuarios (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.consultarUsuarios";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getBackOfficeIo().consultarUsuarios(consulta, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public TableOd consultarBitacoras (ConsultaBitacoraOd parametros,VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "BackOfficeServicio.consultarBitacoras";

        long time;

        TableOd listaBitacora = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaBitacora = getBackOfficeIo().consultarBitacoras(parametros,usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaBitacora);
    }

    public TableOd consultarSucesos (List<String> parametros, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarSucesos";

        long time;

        TableOd listaSucesos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaSucesos = getBackOfficeIo().consultarSucesos(parametros, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaSucesos);
    }

    public String salvarPermisosGrupos (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.salvarPermisosGrupos";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().salvarPermisosGrupos(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String salvarPermisosUsuarios (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.salvarPermisosUsuarios";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().salvarPermisosUsuarios(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String eliminarPermisosUsuarios (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.eliminarPermisosUsuarios";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().eliminarPermisosUsuarios(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public VBTUsersOd consultarUsuario (String id, String tipoGrupo) throws Exception {
        final String origen = "BackOfficeServicio.consultarUsuario";

        long time;

        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = getBackOfficeIo().consultarUsuario(id, tipoGrupo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (usuario);
    }

    public VBTUsersOd consultarUsuarioContrato (String id) throws Exception {
        final String origen = "BackOfficeServicio.consultarUsuarioContrato";

        long time;

        VBTUsersOd usuario = new VBTUsersOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = getBackOfficeIo().consultarUsuarioContrato(id);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (usuario);
    }

    public String editarUsuario (VBTUsersOd usuario, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.editarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarUsuario(usuario, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String editarUsuarioContrato (VBTUsersOd usuario, String plogin, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.editarUsuarioContrato";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarUsuarioContrato(usuario, plogin,usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String agregarUsuario (VBTUsersOd usuario, String plogin, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.agregarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().agregarUsuario(usuario, plogin,usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String consultarCarterasContrato (String codcontrato) throws Exception{
        final String origen = "BackOfficeServicio.consultarCarterasContrato";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().consultarCarterasContrato(codcontrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String guardarParametrosPersonalesBO (ParametrosPersonalesOd parametrosPersonalesOd,String tipo, String tipoParametro, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.guardarParametrosPersonalesBO";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().guardarParametrosPersonalesBO(parametrosPersonalesOd, tipo, tipoParametro, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public TableOd consultarUsuariosContratoDetalle (List<String> parametros,String status) throws Exception {
        final String origen = "BackOfficeServicio.consultarUsuariosContratoDetalle";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getBackOfficeIo().consultarUsuariosContratoDetalle(parametros, status);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public TableOd consultarDetallePagos (List<String> parametros,String status) throws Exception {
        final String origen = "BackOfficeServicio.consultarDetallePagos";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getBackOfficeIo().consultarDetallePagos(parametros, status);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }




    public TableOd consultarUsuariosContratos (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.consultarUsuariosContratos";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getBackOfficeIo().consultarUsuariosContratos(consulta, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public TableOd consultarContratos (ConsultContratsOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.consultarContratos";

        long time;

        TableOd listaContratos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaContratos = getBackOfficeIo().consultarContratos(consulta, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaContratos);
    }

    public TableOd cargarUsuariosContratos (String numContrato) throws Exception{
        final String origen = "BackOfficeServicio.cargarUsuariosContratos";

        long time;

        TableOd listaUsuariosContratos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuariosContratos = getBackOfficeIo().cargarUsuariosContratos(numContrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuariosContratos);
    }

    public TableOd cargarCarterasContratos (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numContrato) throws Exception{
        final String origen = "BackOfficeServicio.cargarCarterasContratos";

        long time;

        TableOd listaCarterasContratos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCarterasContratos = getBackOfficeIo().cargarCarterasContratos(p_TAGCompartida, p_TAGIndividual, p_TAGActiva, p_TAGInactiva, numContrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCarterasContratos);
    }

    public String cargarCartera(String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numCartera) throws Exception{
        final String origen = "BackOfficeServicio.cargarCarterasContratos";

        long time;

        String cartera = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cartera = getBackOfficeIo().cargarCartera(p_TAGCompartida, p_TAGIndividual, p_TAGActiva, p_TAGInactiva, numCartera);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cartera);
    }




    public SelectOd consultarElementos (String tipo) throws Exception {
        final String origen = "BackOfficeServicio.consultarElementos";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarElementosTipos(tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd consultarElementosExtra (String tipo) throws Exception {
        final String origen = "BackOfficeServicio.consultarElementosExtra";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarElementosTiposExtra(tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd cargarAccionFiltro (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarAccionFiltro";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarAccionFiltro(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }



    public SelectOd cargarAccionFiltroFC (String contrato) throws Exception {
        final String origen = "BackOfficeServicio.cargarAccionFiltroFC";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarAccionFiltroFC(contrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd cargarObjetosFiltro (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarObjetosFiltro";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarObjetosFiltro(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd cargarGrupoClientes (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarGrupoClientes";

        long time;

        SelectOd listaGruposClientes = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaGruposClientes = getBackOfficeIo().cargarGrupoClientes(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaGruposClientes);
    }


    public SelectOd cargarGrupoClientesCategoria (String categoria, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarGrupoClientesCategoria";

        long time;

        SelectOd listaGruposClientes = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaGruposClientes = getBackOfficeIo().cargarGrupoClientesCategoria(categoria, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaGruposClientes);
    }

    public SelectOd cargarTipoCiRif (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarTipoCiRif";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarTipoCiRif(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd cargarTipoAmbito() throws Exception {
        final String origen = "BackOfficeServicio.cargarTipoCiRif";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarTipoAmbito();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd consultarCorreos (String codcli,String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarCorreos";

        long time;

        SelectOd listaCorreos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCorreos = getBackOfficeIo().consultarCorreos(codcli,login,usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCorreos);
    }


    public SelectOd consultarTelefonos(String codcli,String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarTelefonos";

        long time;

        SelectOd listaCorreos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCorreos = getBackOfficeIo().consultarTelefonos(codcli,login,usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCorreos);
    }


    public SelectOd consultarTelefonosLocal(String codcli,String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarTelefonosLocal";

        long time;

        SelectOd listaCorreos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCorreos = getBackOfficeIo().consultarTelefonosLocal(codcli,login, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCorreos);
    }


    public SelectOd cargarUsuariosSelect () throws Exception {
        final String origen = "BackOfficeServicio.cargarUsuariosSelect";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarUsuariosSelect();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public TableOd busquedaUsuariosContrato (List<String> busqueda, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.busquedaUsuariosContrato";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getBackOfficeIo().busquedaUsuariosContrato(busqueda, usuarioSesion );

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public TableOd busquedaCarterasContrato (List<String> busqueda, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.busquedaCarterasContrato";

        long time;

        TableOd listaCarteras = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCarteras = getBackOfficeIo().busquedaCarterasContrato(busqueda, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCarteras);
    }

    public String agregarContrato (List<CarterasOd> carteras, List<VBTUsersOd> usuarios, VBTUsersOd usuarioCreador, String descripcion, String login, String ip, String tipoContrato, VBTUsersOd usuarioSesion,String requiereSoporte) throws Exception {
        final String origen = "BackOfficeServicio.agregarContrato";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().agregarContrato(carteras, usuarios, usuarioCreador, descripcion, login, ip, tipoContrato, usuarioSesion,requiereSoporte);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String editarContrato (List<CarterasOd> carterasViejas, List<VBTUsersOd> usuariosViejos, List<CarterasOd> carterasNuevas, List<VBTUsersOd> usuariosNuevos, String descripcion, String estatus, String numeroContrato, VBTUsersOd usuarioCreador, List<ContentSelectOd> motivosRechazo,String requiereSoporte) throws Exception {
        final String origen = "BackOfficeServicio.agregarContrato";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarContrato(carterasViejas, usuariosViejos, carterasNuevas, usuariosNuevos, descripcion, estatus, numeroContrato, usuarioCreador, motivosRechazo,requiereSoporte);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
    /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "BackOfficeServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
     */

    public SelectOd cargarMotivosRechazo (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarMotivosRechazos";

        long time;

        SelectOd listaMotivosRechazo = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMotivosRechazo = getBackOfficeIo().cargarMotivosRechazo(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMotivosRechazo);
    }

    public SelectOd cargarMotivosRechazoContrato (String contrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarMotivosRechazoContrato";

        long time;

        SelectOd listaMotivosRechazo = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMotivosRechazo = getBackOfficeIo().cargarMotivosRechazoContrato(contrato, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMotivosRechazo);
    }

    public ParametrosPersonalesOd cargarParametrosGlobales (String tipo,VBTUsersOd usuarioSesion ) throws Exception {
        final String origen = "BackOfficeServicio.cargarParametrosGlobales";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().cargarParametrosGlobales(tipo, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }



    public ParametrosPersonalesOd cargarParametrosContratos (String contrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.cargarParametrosContratos";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().cargarParametrosContratos(contrato, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String validarUsuario (String usuario) throws Exception {
        final String origen = "BackOfficeServicio.validarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().validarUsuario(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public  String validarCarteraContrato (String numCartera, String tipoContrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.validarCarteraContrato";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().validarCarteraContrato(numCartera, tipoContrato, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public TableOd consultarLogSms (ConsultLogSmsOd consulta, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarLogSms";

        long time;

        TableOd listalogsms = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listalogsms = getBackOfficeIo().consultarLogSms(consulta, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listalogsms);
    }

    public TableOd consultarPaisesNoPermitidos (String codPais,String nomPais,String paises,String revision) throws Exception {
        final String origen = "BackOfficeServicio.consultarPaisesNoPermitidos";

        long time;

        TableOd listapaisesnopermitidos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listapaisesnopermitidos = getBackOfficeIo().consultarPaisesNoPermitidos(codPais, nomPais, paises, revision);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listapaisesnopermitidos);
    }

    public BackOfficeIo getBackOfficeIo() {
        return backOfficeIo;
    }

    public void setBackOfficeIo(BackOfficeIo backOfficeIo) {
        this.backOfficeIo = backOfficeIo;
    }


    public String cambiaPaisesNoPermitidos (String codPais,String nomPais,String tipoOpcion) throws Exception {
        final String origen = "BackOfficeServicio.cambiaPaisesNoPermitidos";

        long time;

        String respuesta = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().cambiaPaisesNoPermitidos(codPais, nomPais, tipoOpcion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String cambiaPaisesRevision (String codPais,String nomPais,String tipoOpcion) throws Exception {
        final String origen = "BackOfficeServicio.cambiaPaisesRevision";
        long time;
        String respuesta = "";
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);
            time = System.currentTimeMillis ();
            respuesta = getBackOfficeIo().cambiaPaisesRevision(codPais, nomPais, tipoOpcion);
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (respuesta);
    }


    public TableOd consultarOpcionesFC (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.consultarOpcionesFC";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarOpcionesFC(usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }

    public String actualizarOpcionesSistemaFC(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeServicio.actualizarOpcionesSistemaFC";
        String resultado = "";
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            resultado = getBackOfficeIo().actualizarOpcionesSistemaFC(acciones, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex,ex); throw (new Exception (ex.getMessage(),null));
        }

        return (resultado);
    }

    public TableOd  buscarBanco (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.buscarBanco";

        long time;

        TableOd listaDirectorio = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDirectorio = getBackOfficeIo().buscarBanco(parametros,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDirectorio);
    }

    public String  inactivarBanco (String codigo, String tipo, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.inactivarBanco";

        long time;

        String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().inactivarBanco(codigo,tipo, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String  activarBanco (String codigo, String tipo, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.activarBanco";

        long time;

        String respuesta;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().activarBanco(codigo,tipo, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public TableOd buscarAvisos (List<String> parametros, VBTUsersOd usuario,String idioma) throws Exception {
        final String origen = "BackOfficeServicio.buscarAvisos";

        long time;

        TableOd listaDirectorio = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDirectorio = getBackOfficeIo().buscarAvisos(parametros,usuario, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDirectorio);
    }

    public SelectOd cargarTarjetasTDCCL (String carteras, String exacta, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeServicio.cargarTarjetasTDCCL";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getBackOfficeIo().cargarTarjetasTDCCL(carteras, exacta, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }


    public Map<String,Object> cargarEstatusTDCCL (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.cargarEstatusTDCCL";
        Map<String,Object> estatus = null;
        long time;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            estatus = getBackOfficeIo().cargarEstatusTDCCL(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (estatus);
    }

    public Map<String,Object> consultarPagosTDC (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.consultarPagosTDC";
        Map<String,Object> estatus = null;
        long time;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            estatus = getBackOfficeIo().consultarPagosTDC(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (estatus);
    }

    public String crearAviso (List<String> parametros, byte[] imagen, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.crearAviso";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().crearAviso(parametros, imagen, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String validarAvisos (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeServicio.validarFechas";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getBackOfficeIo().validarAvisos(parametros, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }

    public String editarAviso (List<String> parametros, byte[] imagen, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.editarAviso";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarAviso(parametros, imagen, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String eliminarReglaBO (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeServicio.eliminarReglaBO";
        long time;
        String response = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            response = getBackOfficeIo().eliminarReglaBO(parametros, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (response);
    }

    public TableOd consultarCuentasNoPermitidas(ConsultCtasNoPermitidasOd consulta, VBTUsersOd usersOd) throws Exception{

        final String origen = "BackOfficeServicio.consultarCuentasNoPermitidas";
        long time;
        TableOd listaUsuarios = null;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            listaUsuarios = getBackOfficeIo().consultarCuentasNoPermitidas(consulta, usersOd);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public String crearCuentaNoPermitida(ConsultCtasNoPermitidasOd parametros, VBTUsersOd usersOd) throws Exception{
        final String origen = "BackOfficeServicio.crearCuentaNoPermitida";
        long time;
        String respuesta = new String();

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            respuesta = getBackOfficeIo().crearCuentaNoPermitida(parametros, usersOd);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String editarEstatusCuentaNoPermitida(List<ConsultCtasNoPermitidasOd> parametros, VBTUsersOd usersOd) throws Exception{
        final String origen = "BackOfficeServicio.editarEstatusCuentaNoPermitida";
        long time;
        String respuesta = new String();

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            respuesta = getBackOfficeIo().editarEstatusCuentaNoPermitida(parametros, usersOd);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }


        return (respuesta);
    }


    public TableOd consultarTransaccionesEspeciales(VBTUsersOd usersOd) throws Exception{

        final String origen = "BackOfficeServicio.consultarTransaccionesEspeciales";
        long time;
        TableOd listaUsuarios = null;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            listaUsuarios = getBackOfficeIo().consultarTransaccionesEspeciales(usersOd);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public String crearTransaccionEspecial(ConsultaTransaccionesEspecialesOd parametros, VBTUsersOd usersOd) throws Exception{
        final String origen = "BackOfficeServicio.crearTransaccionEspecial";
        long time;
        String respuesta = new String();

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            respuesta = getBackOfficeIo().crearTransaccionEspecial(parametros, usersOd);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String eliminarTransaccionEspecial(List<ConsultaTransaccionesEspecialesOd> parametros, VBTUsersOd usersOd) throws Exception{
        final String origen = "BackOfficeServicio.eliminarTransaccionEspecial";
        long time;
        String respuesta = new String();

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            respuesta = getBackOfficeIo().eliminarTransaccionEspecial(parametros, usersOd);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }


        return (respuesta);
    }


    public SelectOd cargarTarjetasTDCCLE (String carteras, String exacta, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeServicio.cargarTarjetasTDCCLE";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getBackOfficeIo().cargarTarjetasTDCCL(carteras, exacta, usersOd);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public SelectOd cargarMovitosCLEBO (String estatusTarjeta, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovitosCLE";

        long time;

        SelectOd select = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            select = getBackOfficeIo().cargarMotivosCLE(estatusTarjeta, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (select);
    }

    public Map<String,Object> consultarTarjetasBloqueo(String carteras, List<String> parametros, VBTUsersOd usersOd,List<PrivilegioOd> listaAcciones) throws Exception{

        final String origen = "BackOfficeServicio.consultarTarjetasBloqueo";
        long time;
        Map<String,Object> listaTarjetas = null;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis();

            listaTarjetas = getBackOfficeIo().consultarTarjetasBloqueo(carteras, parametros,usersOd,listaAcciones);

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }

        return (listaTarjetas);
    }

    public String cambiarEstatusTdc (List<String> parametros, VBTUsersOd usuario, boolean servicio) throws Exception {
        final String origen = "CreditCardsServicio.cargarMovimientosTDCITT";
        String respuesta = "";
        long time;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+ CreditCardsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().cambiarEstatusTdc(parametros, usuario,servicio);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+CreditCardsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        return (respuesta);
    }
}
package ve.com.vbtonline.servicio.negocio.backoffice;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.BackOfficeIo;
import ve.com.vbtonline.servicio.od.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackOfficeServicio implements IBackOfficeServicio,Serializable {
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

    public CtaOpcOd opcionesGrupo (String transaccionId, CtaOpcOd ctod) throws Exception {
        final String origen = "BackOfficeServicio.opcionesGrupo";

        long time;
        CtaOpcOd ctaOpcOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            ctaOpcOd=new CtaOpcOd();
            ctaOpcOd = getBackOfficeIo().opcionesGrupo("",ctod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (ctaOpcOd);
    }

    public TableOd consultarGrupos () throws Exception {
        final String origen = "BackOfficeServicio.cargar";

        long time;

        TableOd listaGrupos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaGrupos = getBackOfficeIo().consultarGrupos();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaGrupos);
    }

    public TableOd consultarGrupoOpcionesSistema (String categoria) throws Exception {
        final String origen = "BackOfficeServicio.consultarGrupoOpcionesSistema";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarGrupoOpcionesSistema(categoria);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }

    public TableOd consultarOpcionesSistema (String strCmbTipoUsuarioEditar) throws Exception {
        final String origen = "BackOfficeServicio.consultarOpcionesSistema";

        long time;

        TableOd listaOpciones = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaOpciones = getBackOfficeIo().consultarOpcionesSistema(strCmbTipoUsuarioEditar);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaOpciones);
    }

    public TableOd consultarGrupoOpcPermisos (String codGrupo, String codOpcion, List<String> acciones) throws Exception {
        final String origen = "BackOfficeServicio.consultarGrupoOpcPermisos";

        long time;

        TableOd accionesLista = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            accionesLista = getBackOfficeIo().consultarGrupoOpcPermisos(codGrupo,codOpcion, acciones);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (accionesLista);
    }

    public TableOd consultarOpcPermisos (String usuario, String codOpcion, List<String> acciones) throws Exception {
        final String origen = "BackOfficeServicio.consultarOpcPermisos";

        long time;

        TableOd accionesLista = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            accionesLista = getBackOfficeIo().consultarOpcPermisos(usuario,codOpcion, acciones);

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

    public TableOd consultarSucesos (List<String> parametros) throws Exception {
        final String origen = "BackOfficeServicio.consultarSucesos";

        long time;

        TableOd listaSucesos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaSucesos = getBackOfficeIo().consultarSucesos(parametros);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaSucesos);
    }

    public String salvarPermisosGrupos (List<String> acciones) throws Exception {
        final String origen = "BackOfficeServicio.salvarPermisosGrupos";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().salvarPermisosGrupos(acciones);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String salvarPermisosUsuarios (List<String> acciones) throws Exception {
        final String origen = "BackOfficeServicio.salvarPermisosUsuarios";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().salvarPermisosUsuarios(acciones);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

public String eliminarPermisosUsuarios (List<String> acciones) throws Exception {
        final String origen = "BackOfficeServicio.eliminarPermisosUsuarios";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().eliminarPermisosUsuarios(acciones);

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

    public String editarUsuario (VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeServicio.editarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarUsuario(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String editarUsuarioContrato (VBTUsersOd usuario, String plogin) throws Exception {
        final String origen = "BackOfficeServicio.editarUsuarioContrato";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarUsuarioContrato(usuario, plogin);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String agregarUsuario (VBTUsersOd usuario, String plogin) throws Exception {
        final String origen = "BackOfficeServicio.agregarUsuario";
        String respuesta;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().agregarUsuario(usuario, plogin);

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

    public String guardarParametrosPersonalesBO (ParametrosPersonalesOd parametrosPersonalesOd,String tipo, String tipoParametro) throws Exception {
        final String origen = "BackOfficeServicio.guardarParametrosPersonalesBO";

        long time;
        String respuesta = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().guardarParametrosPersonalesBO(parametrosPersonalesOd, tipo, tipoParametro);

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

    public SelectOd cargarAccionFiltro () throws Exception {
        final String origen = "BackOfficeServicio.cargarAccionFiltro";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarAccionFiltro();

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

    public SelectOd cargarObjetosFiltro () throws Exception {
        final String origen = "BackOfficeServicio.cargarObjetosFiltro";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarObjetosFiltro();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public SelectOd cargarGrupoClientes () throws Exception {
        final String origen = "BackOfficeServicio.cargarGrupoClientes";

        long time;

        SelectOd listaGruposClientes = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaGruposClientes = getBackOfficeIo().cargarGrupoClientes();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaGruposClientes);
    }

     public SelectOd cargarTipoCiRif () throws Exception {
        final String origen = "BackOfficeServicio.cargarTipoCiRif";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getBackOfficeIo().cargarTipoCiRif();

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

     public SelectOd consultarCorreos (String codcli,String login) throws Exception {
        final String origen = "BackOfficeServicio.consultarCorreos";

        long time;

        SelectOd listaCorreos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCorreos = getBackOfficeIo().consultarCorreos(codcli,login);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCorreos);
    }


    public SelectOd consultarTelefonos(String codcli,String login) throws Exception {
        final String origen = "BackOfficeServicio.consultarTelefonos";

        long time;

        SelectOd listaCorreos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCorreos = getBackOfficeIo().consultarTelefonos(codcli,login);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCorreos);
    }


    public SelectOd consultarTelefonosLocal(String codcli,String login) throws Exception {
        final String origen = "BackOfficeServicio.consultarTelefonosLocal";

        long time;

        SelectOd listaCorreos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCorreos = getBackOfficeIo().consultarTelefonosLocal(codcli,login);

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

    public TableOd busquedaUsuariosContrato (List<String> busqueda) throws Exception {
        final String origen = "BackOfficeServicio.busquedaUsuariosContrato";

        long time;

        TableOd listaUsuarios = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaUsuarios = getBackOfficeIo().busquedaUsuariosContrato(busqueda);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaUsuarios);
    }

    public TableOd busquedaCarterasContrato (List<String> busqueda) throws Exception {
        final String origen = "BackOfficeServicio.busquedaCarterasContrato";

        long time;

        TableOd listaCarteras = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCarteras = getBackOfficeIo().busquedaCarterasContrato(busqueda);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCarteras);
    }

    public String agregarContrato (List<CarterasOd> carteras, List<VBTUsersOd> usuarios, VBTUsersOd usuarioCreador, String descripcion, String login, String ip) throws Exception {
        final String origen = "BackOfficeServicio.agregarContrato";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().agregarContrato(carteras, usuarios, usuarioCreador, descripcion, login, ip);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String editarContrato (List<CarterasOd> carterasViejas, List<VBTUsersOd> usuariosViejos, List<CarterasOd> carterasNuevas, List<VBTUsersOd> usuariosNuevos, String descripcion, String estatus, String numeroContrato, VBTUsersOd usuarioCreador, List<ContentSelectOd> motivosRechazo) throws Exception {
        final String origen = "BackOfficeServicio.agregarContrato";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().editarContrato(carterasViejas, usuariosViejos, carterasNuevas, usuariosNuevos, descripcion, estatus, numeroContrato, usuarioCreador, motivosRechazo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

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


    public SelectOd cargarMotivosRechazo () throws Exception {
        final String origen = "BackOfficeServicio.cargarMotivosRechazos";

        long time;

        SelectOd listaMotivosRechazo = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMotivosRechazo = getBackOfficeIo().cargarMotivosRechazo();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMotivosRechazo);
    }

    public SelectOd cargarMotivosRechazoContrato (String contrato) throws Exception {
        final String origen = "BackOfficeServicio.cargarMotivosRechazoContrato";

        long time;

        SelectOd listaMotivosRechazo = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaMotivosRechazo = getBackOfficeIo().cargarMotivosRechazoContrato(contrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaMotivosRechazo);
    }

    public ParametrosPersonalesOd cargarParametrosGlobales (String tipo) throws Exception {
        final String origen = "BackOfficeServicio.cargarParametrosGlobales";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().cargarParametrosGlobales(tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+BackOfficeServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }



    public ParametrosPersonalesOd cargarParametrosContratos (String contrato) throws Exception {
        final String origen = "BackOfficeServicio.cargarParametrosContratos";

        long time;
        ParametrosPersonalesOd respuesta = new ParametrosPersonalesOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+BackOfficeServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getBackOfficeIo().cargarParametrosContratos(contrato);

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

    public BackOfficeIo getBackOfficeIo() {
        return backOfficeIo;
    }

    public void setBackOfficeIo(BackOfficeIo backOfficeIo) {
        this.backOfficeIo = backOfficeIo;
    }
}

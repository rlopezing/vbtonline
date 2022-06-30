package ve.com.vbtonline.servicio.negocio.backoffice;

import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.od.SelectOd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IBackOfficeServicio {
    public BackOfficeOd cargar (String transaccionId, BackOfficeOd bod) throws Exception;
    public CtaOpcOd opcionesGrupo (String transaccionId, CtaOpcOd ctod) throws Exception;
    public TableOd consultarGrupos () throws Exception;
    public TableOd consultarGrupoOpcionesSistema (String categoria) throws Exception;
    public TableOd consultarOpcionesSistema (String strCmbTipoUsuarioEditar) throws Exception;
    public TableOd consultarGrupoOpcPermisos (String codGrupo, String codOpcion, List<String> acciones) throws Exception;
    public TableOd consultarOpcPermisos (String usuario, String codOpcion, List<String> acciones) throws Exception;
    public TableOd consultarUsuarios (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception;
    public TableOd consultarBitacoras (ConsultaBitacoraOd parametros,VBTUsersOd usuario, String idioma) throws Exception;
    public TableOd consultarSucesos (List<String> parametros) throws Exception;
    public String editarUsuario (VBTUsersOd usuario) throws Exception;
    public String editarUsuarioContrato (VBTUsersOd usuario, String plogin) throws Exception;
    public String agregarUsuario (VBTUsersOd usuario, String plogin) throws Exception;
    public VBTUsersOd consultarUsuario (String id, String tipoGrupo) throws Exception;
    public VBTUsersOd consultarUsuarioContrato (String id) throws Exception;
    public TableOd consultarUsuariosContratos (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception;
    public TableOd consultarContratos (ConsultContratsOd consulta, VBTUsersOd usuario) throws Exception;
    public TableOd cargarCarterasContratos (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numContrato) throws Exception;
    public String cargarCartera (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numCartera) throws Exception;
    public SelectOd cargarMotivosRechazo () throws Exception;
    public SelectOd cargarMotivosRechazoContrato (String contrato) throws Exception;
    public TableOd cargarUsuariosContratos (String numContrato) throws Exception;
    public SelectOd consultarElementos (String tipo) throws Exception;
    public SelectOd cargarGrupoClientes () throws Exception;
    public SelectOd cargarTipoCiRif () throws Exception;
    public SelectOd cargarTipoAmbito () throws Exception;
    public SelectOd consultarCorreos (String codcli, String login) throws Exception;
    public SelectOd consultarTelefonos (String codcli, String login) throws Exception;
    public SelectOd consultarTelefonosLocal (String codcli, String login) throws Exception;
    public SelectOd cargarUsuariosSelect () throws Exception;
    public TableOd busquedaUsuariosContrato (List<String> busqueda) throws Exception;
    public TableOd busquedaCarterasContrato (List<String> busqueda) throws Exception;
    public String salvarPermisosGrupos (List<String> acciones) throws Exception;
    public String salvarPermisosUsuarios (List<String> acciones) throws Exception;
    public String eliminarPermisosUsuarios (List<String> acciones) throws Exception;
    public SelectOd cargarObjetosFiltro () throws Exception;
    public SelectOd cargarAccionFiltro () throws Exception;
    public SelectOd cargarAccionFiltroFC (String contrato) throws Exception;
    public ParametrosPersonalesOd cargarParametrosGlobales (String tipo) throws Exception;
    public ParametrosPersonalesOd cargarParametrosContratos (String contrato) throws Exception;
    public String guardarParametrosPersonalesBO (ParametrosPersonalesOd parametrosPersonalesOd, String tipo, String tipoParametro) throws Exception;
    public String consultarCarterasContrato (String codcontrato) throws Exception;
    public TableOd consultarUsuariosContratoDetalle (List<String> parametros, String status) throws Exception;
    public String agregarContrato (List<CarterasOd> carteras, List<VBTUsersOd> usuarios, VBTUsersOd usuarioCreador, String descripcion, String login, String ip) throws Exception;
    public String validarUsuario (String usuario) throws Exception;
    public String editarContrato (List<CarterasOd> carterasViejas, List<VBTUsersOd> usuariosViejos, List<CarterasOd> carterasNuevas, List<VBTUsersOd> usuariosNuevos, String descripcion, String estatus, String numeroContrato, VBTUsersOd usuarioCreador,List<ContentSelectOd> motivosRechazo) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

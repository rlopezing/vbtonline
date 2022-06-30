package ve.com.vbtonline.servicio.negocio.backoffice;

import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.od.SelectOd;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IBackOfficeServicio {
    public BackOfficeOd cargar (String transaccionId, BackOfficeOd bod) throws Exception;
    public CtaOpcOd opcionesGrupo (String transaccionId, CtaOpcOd ctod, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarGrupos (VBTUsersOd usuarioSesion) throws Exception;          //VBTUsersOd usuarioSesion
    public TableOd consultarRolesFC (VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarGrupoOpcionesSistema (String categoria, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarGrupoOpcionesSistemaEsquema (String categoria, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarRolOpcionesSistemaEsquema (String categoria, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarOpcionesEspeciales (String login, VBTUsersOd u, String tipo) throws Exception;
    public TableOd consultarAccesos (String login, VBTUsersOd u, String tipo) throws Exception;
    public TableOd consultarOpcionesSistema (String strCmbTipoUsuarioEditar, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarGrupoOpcPermisos (String codGrupo, String codOpcion, List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarOpcPermisos (String usuario, String codOpcion, List<String> acciones, VBTUsersOd usuarioSesion ) throws Exception;
    public TableOd consultarUsuarios (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception;
    public TableOd consultarBitacoras (ConsultaBitacoraOd parametros,VBTUsersOd usuario, String idioma) throws Exception;
    public TableOd consultarSucesos (List<String> parametros, VBTUsersOd usuarioSesion ) throws Exception;
    public String editarUsuario (VBTUsersOd usuario, VBTUsersOd usuarioSesion) throws Exception;
    public String editarUsuarioContrato (VBTUsersOd usuario, String plogin, VBTUsersOd usuarioSesion) throws Exception;
    public String agregarUsuario (VBTUsersOd usuario, String plogin, VBTUsersOd usuarioSesion) throws Exception;
    public VBTUsersOd consultarUsuario (String id, String tipoGrupo) throws Exception;
    public VBTUsersOd consultarUsuarioContrato (String id) throws Exception;
    public TableOd consultarUsuariosContratos (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception;
    public TableOd consultarContratos (ConsultContratsOd consulta, VBTUsersOd usuario) throws Exception;
    public TableOd cargarCarterasContratos (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numContrato) throws Exception;
    public String cargarCartera (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numCartera) throws Exception;
    public SelectOd cargarMotivosRechazo (VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarMotivosRechazoContrato (String contrato, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd cargarUsuariosContratos (String numContrato) throws Exception;
    public SelectOd consultarElementos (String tipo) throws Exception;
    public SelectOd consultarElementosExtra (String tipo) throws Exception;
    public SelectOd cargarGrupoClientes (VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarGrupoClientesCategoria (String categoria, VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarTipoCiRif (VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarTipoAmbito () throws Exception;
    public SelectOd consultarCorreos (String codcli, String login, VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd consultarTelefonos (String codcli, String login, VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd consultarTelefonosLocal (String codcli, String login, VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarUsuariosSelect () throws Exception;
    public TableOd busquedaUsuariosContrato (List<String> busqueda, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd busquedaCarterasContrato (List<String> busqueda, VBTUsersOd usuarioSesion) throws Exception;
    public String salvarPermisosGrupos (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public String salvarPermisosUsuarios (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public String eliminarPermisosUsuarios (List<String> acciones,VBTUsersOd usuarioSesion ) throws Exception;
    public SelectOd cargarObjetosFiltro (VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarAccionFiltro (VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarAccionFiltroFC (String contrato) throws Exception;
    public ParametrosPersonalesOd cargarParametrosGlobales (String tipo, VBTUsersOd usuarioSesion) throws Exception;
    public ParametrosPersonalesOd cargarParametrosContratos (String contrato, VBTUsersOd usuarioSesion) throws Exception;
    public String guardarParametrosPersonalesBO (ParametrosPersonalesOd parametrosPersonalesOd, String tipo, String tipoParametro, VBTUsersOd usuarioSesion) throws Exception;
    public String consultarCarterasContrato (String codcontrato) throws Exception;
    public TableOd consultarUsuariosContratoDetalle (List<String> parametros, String status) throws Exception;
    public TableOd consultarDetallePagos (List<String> parametros, String status) throws Exception;
    public String agregarContrato (List<CarterasOd> carteras, List<VBTUsersOd> usuarios, VBTUsersOd usuarioCreador, String descripcion, String login, String ip, String tipoContrato, VBTUsersOd usuarioSesion,String requiereSoporte) throws Exception;
    public String validarUsuario (String usuario) throws Exception;
    public String editarContrato (List<CarterasOd> carterasViejas, List<VBTUsersOd> usuariosViejos, List<CarterasOd> carterasNuevas, List<VBTUsersOd> usuariosNuevos, String descripcion, String estatus, String numeroContrato, VBTUsersOd usuarioCreador,List<ContentSelectOd> motivosRechazo,String requiereSoporte ) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String validarCarteraContrato (String numCartera, String tipoContrato, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarLogSms (ConsultLogSmsOd consulta, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarPaisesNoPermitidos (String codPais,String nomPais,String paises,String revision) throws Exception;
    public String cambiaPaisesNoPermitidos (String codPais,String nomPais,String tipoOpcion) throws Exception;
    public String actualizarGrupoOpcionesSistemaEsquema (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public String actualizarRolOpcionesSistemaEsquema (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public String actualizarOpcionesEspecialesSistema (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd consultarOpcionesFC (VBTUsersOd usuarioSesion) throws Exception;
    public String actualizarOpcionesSistemaFC (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception;
    public TableOd  buscarBanco (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public String  inactivarBanco (String codigo, String tipo, VBTUsersOd usuario) throws Exception;
    public String  activarBanco (String codigo, String tipo, VBTUsersOd usuario) throws Exception;
    public TableOd buscarAvisos (List<String> parametros,VBTUsersOd usuario, String idioma) throws Exception;
    public SelectOd cargarTarjetasTDCCL (String carteras, String exacta, VBTUsersOd usersOd) throws Exception;
    public Map<String,Object> cargarEstatusTDCCL (List<String> parametros, VBTUsersOd usuario) throws Exception;
    public Map<String,Object> consultarPagosTDC (List<String> parametros, VBTUsersOd usuario) throws Exception;
    public String  crearAviso (List<String> parametros, byte[] imagen, VBTUsersOd usuario) throws Exception;
    public String validarAvisos (List<String> parametros, VBTUsersOd usersOd) throws Exception;
    public String  editarAviso (List<String> parametros, byte[] imagen, VBTUsersOd usuario) throws Exception;
    public String eliminarReglaBO (List<String> parametros, VBTUsersOd usersOd) throws Exception;
    public TableOd consultarCuentasNoPermitidas(ConsultCtasNoPermitidasOd consulta, VBTUsersOd usersOd) throws Exception;
    public String crearCuentaNoPermitida(ConsultCtasNoPermitidasOd parametros, VBTUsersOd usersOd) throws Exception;
    public String editarEstatusCuentaNoPermitida(List<ConsultCtasNoPermitidasOd> parametros, VBTUsersOd usersOd) throws Exception;
    public TableOd consultarTransaccionesEspeciales(VBTUsersOd usersOd) throws Exception;
    public String crearTransaccionEspecial(ConsultaTransaccionesEspecialesOd parametros, VBTUsersOd usersOd) throws Exception;
    public String eliminarTransaccionEspecial(List<ConsultaTransaccionesEspecialesOd> parametros, VBTUsersOd usersOd) throws Exception;
    public String cambiaPaisesRevision (String codPais,String nomPais,String tipoOpcion) throws Exception;
    /*public SelectOd cargarEstatusTDCCLEBO (String carteras, String exacta, VBTUsersOd usersOd) throws Exception;*/
    public SelectOd cargarTarjetasTDCCLE (String carteras, String exacta, VBTUsersOd usersOd) throws Exception;
    public SelectOd cargarMovitosCLEBO (String estatusTarjeta, VBTUsersOd usuario) throws Exception;
    public Map<String,Object> consultarTarjetasBloqueo(String carteras,List<String> parametros,VBTUsersOd usuario,List<PrivilegioOd> listaAcciones) throws Exception;
    public String cambiarEstatusTdc (List<String> parametros, VBTUsersOd usersOd, boolean servicio) throws Exception;
}

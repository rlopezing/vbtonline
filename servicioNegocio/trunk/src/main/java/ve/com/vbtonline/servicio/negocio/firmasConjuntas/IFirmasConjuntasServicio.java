package ve.com.vbtonline.servicio.negocio.firmasConjuntas;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:13 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IFirmasConjuntasServicio {

    public TableOd consultarUsuarios(ConsultUsersOd consulta, VBTUsersOd usuario, String idioma) throws Exception;
    public String editarUsuario(VBTUsersOd usuario,VBTUsersOd usuarioAnterior, String idioma, VBTUsersOd usuariosesion) throws Exception;
    public String agregarUsuario(VBTUsersOd usuario, VBTUsersOd usuarioCreador, String idioma, VBTUsersOd usuarioSesion) throws Exception;
    public String correoClientePrincipal(VBTUsersOd usuario, VBTUsersOd usuarioCreador,String idioma) throws Exception;
    public VBTUsersOd consultarUsuario(String id, String tipoGrupo, VBTUsersOd usuario) throws Exception;
    public SelectOd consultarElementos(String tipo, String idioma, VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarGrupoClientes(VBTUsersOd usuarioSesion) throws Exception;
    public SelectOd cargarTipoCiRif(VBTUsersOd usuarioSesion) throws Exception;
    public String consultarCarterasContrato(String codcontrato, VBTUsersOd usuarioSesion ) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception;
    public ParametrosPersonalesFCOd cargarParametrosPersonales (List<String> parametros, VBTUsersOd usuarioSesion) throws Exception;
    public ParametrosPersonalesFCOd cargarParametrosPersonalesBase (VBTUsersOd usuarioSesion) throws Exception;
    public String guardarParametrosPersonales (ParametrosPersonalesFCOd parametrosPersonalesOd, VBTUsersOd usuariosesion ) throws Exception;
    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6);
    public List<RolesCustomOd> cargarRolesCustom (String categoria, String idioma, VBTUsersOd usuario) throws Exception;
    public List<PrivilegioOd> cargarRolesCustomDetalle (String rol, String idioma, VBTUsersOd usuario) throws Exception;
    public List<RolesCustomOd> cargarRolesUsuario (VBTUsersOd usuario, String idioma) throws Exception;
}

package ve.com.vbtonline.servicio.negocio.home;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 21/05/2010
 * Time: 04:37:07 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILoginServicio {
    public VBTUsersOd validarUsuario (VBTUsersOd u) throws Exception;
    public VBTUsersOd validarUsuario2 (String login, VBTUsersOd usuario) throws Exception;
    public VBTUsersOd cargarUsuario (VBTUsersOd u) throws Exception;
    public String cargarCarteras (String numContrato, VBTUsersOd usuario) throws Exception;
    public List<String> cargarAccionesSistema (VBTUsersOd usuario) throws Exception;
    public VBTUsersOd loginOpeEsp (VBTUsersOd u) throws Exception;
    public List<ItemOd> Home (String transaccionId, VBTUsersOd u) throws Exception;
    public List<CalendarioOd> cargarCalendario (Integer mes, Integer ano, Integer dia, VBTUsersOd usuario) throws Exception;
    public String logout (String login) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception;
//    public String cambiarClaveOpeEsp (String clave, String login) throws Exception;
    public List<PrivilegioOd> consultarGrupoOpcPermisos (VBTUsersOd u) throws Exception;
    public boolean validarTerminosCondiciones (String contrato,VBTUsersOd usuario) throws Exception;
    public List<String> cargarInfoHome () throws Exception;

    public String validarUsuarioCorreo (String usuario, String correo) throws Exception;

    /**
     *      FUNCIÓN:        contarSolicitudesPendientes(String, String)
     *      AUTOR:          RRANGEL
     *      FECHA:          10/10/2014
     *      DESCRIPCIÓN:    Obtiene el contrato y el tipo de usuario. Cuando el usuario es Aprobador (9) asigna el valor
     *                      'C' a la variable estatus, cuando es Liberador (8) asigna 'A' a dicha variable.
     * */
    public String contarSolicitudesPendientes(VBTUsersOd usuario, String contrato, String estatus) throws Exception;
    /** FIN contarSolicitudesPendientes(String, String) */

    public String consultarRolesAprobarLiberar (VBTUsersOd usuario) throws Exception;
    public String contarPagosTdcPendientes(String carteras, VBTUsersOd usuario, String estatus) throws Exception;
    public String registrarCodigoAct(VBTUsersOd usuario,String Codigo) throws Exception;
    public String validarAct(VBTUsersOd usuario) throws Exception;
    public String updateFechaAnuncio(VBTUsersOd usuario) throws Exception;
    public ParametrosPersonalesOd cargarParametrosContratos (String contrato, VBTUsersOd usuarioSesion) throws Exception;
    public String consultarMotvosAct (String contrato) throws Exception;
    public String validarExcluidoAct(VBTUsersOd usuario) throws Exception;
}



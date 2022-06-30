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
    public VBTUsersOd validarUsuario2 (String login) throws Exception;
    public VBTUsersOd cargarUsuario (VBTUsersOd u) throws Exception;
    public String cargarCarteras (String numContrato) throws Exception;
    public List<String> cargarAccionesSistema () throws Exception;
    public VBTUsersOd loginOpeEsp (VBTUsersOd u) throws Exception;
    public List<ItemOd> Home (String transaccionId, VBTUsersOd u) throws Exception;
    public List<CalendarioOd> cargarCalendario (Integer mes, Integer ano, Integer dia) throws Exception;
    public String logout (String login) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception;
//    public String cambiarClaveOpeEsp (String clave, String login) throws Exception;
    public List<PrivilegioOd> consultarGrupoOpcPermisos (VBTUsersOd u) throws Exception;
    public boolean validarTerminosCondiciones (String contrato) throws Exception;

}



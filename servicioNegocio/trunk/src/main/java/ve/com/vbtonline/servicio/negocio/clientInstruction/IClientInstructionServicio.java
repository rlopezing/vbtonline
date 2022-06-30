package ve.com.vbtonline.servicio.negocio.clientInstruction;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 23/09/13
 * Time: 04:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IClientInstructionServicio {

    public List<ContentSelectOd> cargarClientes(String numContrato, String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargarListaVencimientos(String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargarListaTipoTransf(String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargarListaMaxCartas(String idioma, VBTUsersOd usuario) throws Exception;
    public List<String> insertarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, VBTUsersOd usuarioOd) throws Exception;
    public boolean eliminarNumeroControl(List<String> numeros, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String evaluarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, String numeroControl, VBTUsersOd usuarioOd) throws Exception;
    public String anularNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip, String numeroControl,String login, VBTUsersOd usuarioOd) throws Exception;
    public SelectOd cargarMotivos (VBTUsersOd usuario, String Idioma) throws Exception;
}

package ve.com.vbtonline.servicio.negocio.clientInstruction;

import ve.com.vbtonline.servicio.od.CarterasOd;
import ve.com.vbtonline.servicio.od.ClientInstructionOd;
import ve.com.vbtonline.servicio.od.ContentSelectOd;
import ve.com.vbtonline.servicio.od.CuentasOd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 23/09/13
 * Time: 04:41 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IClientInstructionServicio {

    public List<ContentSelectOd> cargarClientes(String numContrato, String idioma) throws Exception;
    public List<ContentSelectOd> cargarListaVencimientos(String idioma) throws Exception;
    public List<ContentSelectOd> cargarListaTipoTransf(String idioma) throws Exception;
    public List<ContentSelectOd> cargarListaMaxCartas(String idioma) throws Exception;
    public List<String> insertarNumeroControl(ClientInstructionOd carta, String sessionId, String usuario, String ip) throws Exception;
    public boolean eliminarNumeroControl(List<String> numeros) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

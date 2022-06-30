package ve.com.vbtonline.servicio.negocio.portafolio;

import ve.com.vbtonline.servicio.od.PortafolioOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IPortafolioServicio {
    public PortafolioOd cargar(String transaccionId, PortafolioOd pod, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

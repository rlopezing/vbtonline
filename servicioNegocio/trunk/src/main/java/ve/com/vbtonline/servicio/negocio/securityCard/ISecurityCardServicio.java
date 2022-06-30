package ve.com.vbtonline.servicio.negocio.securityCard;

import ve.com.vbtonline.servicio.od.SecurityCardOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 11/10/13
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ISecurityCardServicio {

    public SecurityCardOd generarTarjeta (SecurityCardOd tarjeta, String usuario, String ip,VBTUsersOd usuarioOd) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public SecurityCardOd consultarTarjetaAsignada (String usuario, VBTUsersOd usuarioOd) throws Exception;
    public String activarTarjeta (SecurityCardOd tarjeta, String usuario, String ip, VBTUsersOd usuarioOd) throws Exception;
}

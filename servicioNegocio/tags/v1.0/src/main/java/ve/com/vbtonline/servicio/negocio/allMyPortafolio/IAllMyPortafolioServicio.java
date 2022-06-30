package ve.com.vbtonline.servicio.negocio.allMyPortafolio;

import ve.com.vbtonline.servicio.od.AllMyPortafolioOd;
import ve.com.vbtonline.servicio.od.TableOd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:40 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAllMyPortafolioServicio {
    public AllMyPortafolioOd cargar(String transaccionId, AllMyPortafolioOd ampod) throws Exception;
    public List<String> cargarCarterasPortafolio (String login) throws Exception;
    public TableOd cargarTablas (String cartera, String accion,String login, String idioma) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

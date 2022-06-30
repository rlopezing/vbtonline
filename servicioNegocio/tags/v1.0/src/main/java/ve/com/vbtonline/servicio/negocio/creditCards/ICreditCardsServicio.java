package ve.com.vbtonline.servicio.negocio.creditCards;

import ve.com.vbtonline.servicio.od.CreditCardsOd;
import ve.com.vbtonline.servicio.od.SelectOd;
import ve.com.vbtonline.servicio.od.TableOd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ICreditCardsServicio {
    public CreditCardsOd cargar(String transaccionId, CreditCardsOd ccod) throws Exception;
    public TableOd consultarSaldosEstadoCuentaTDC (List<String> parametros, String idioma) throws Exception;
    public SelectOd cargarMesesEstadoCuentaTDC (String nroCuenta) throws Exception;
    public List<String> consultarDetallesEstadoCuentaTDC(List<String> parametros) throws Exception;
    public SelectOd cargarTarjetasEstadoCuentaTDC (String carteras, String idioma) throws Exception;
    public TableOd cargarMovimientosTDCITT (List<String> parametros, String idioma) throws Exception;
    public SelectOd cargarTarjetasTDCITT (String carteras, String idioma) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

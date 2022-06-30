package ve.com.vbtonline.servicio.negocio.otherInvestments;

import ve.com.vbtonline.servicio.od.CuentasOd;
import ve.com.vbtonline.servicio.od.MutualFundsOd;
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
public interface IOtherInvestmentsServicio {
    public MutualFundsOd cargar(String transaccionId, MutualFundsOd mfod) throws Exception;
    public CuentasOd cargarOtrasInversiones(String carteras, String idioma) throws Exception;
    public List<String> consultarDetalleOtrasInversionesBT(List<String> parametros) throws Exception;
    public SelectOd cargarTipoTransaccionBT(List<String> parametros) throws Exception;
    public TableOd consultarSaldosTransOtrasInversiones(List<String> parametros, String idioma) throws Exception;
    public TableOd consultarBloqueosOtrasInversiones(List<String> parametros, String idioma) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

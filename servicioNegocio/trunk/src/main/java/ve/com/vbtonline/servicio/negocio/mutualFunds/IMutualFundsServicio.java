package ve.com.vbtonline.servicio.negocio.mutualFunds;

import ve.com.vbtonline.servicio.od.*;

import java.io.InputStream;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IMutualFundsServicio {
    public MutualFundsOd cargar(String transaccionId, MutualFundsOd mfod, VBTUsersOd usuario) throws Exception;
    public CuentasOd cargarFondosMutuales (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public CuentasOd cargarFondosMutualesRazonMoneda (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public List<String> consultarDetalleFondosMutualesBT (List<String> parametros, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarTipoTransaccionBT (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd consultarSaldosTransFondosMutuales (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception;
    public TableOd consultarBloqueosFondosMutuales (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public EstadoCuentaFMGeneralOd consultarTablaEdoCuentaFondos (List<String> parametros, VBTUsersOd usuario) throws Exception;
//    public List<List<String>> consultarConfiguracionEDO(List<String> parametros, VBTUsersOd usuario) throws Exception;
    public List<ConfiguracionEstadoCuentaFMOd> consultarConfiguracionEDO(List<String> parametros, VBTUsersOd usuario) throws Exception;
    public InputStream generarPDFEdoCuenta(List<String> parametros, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

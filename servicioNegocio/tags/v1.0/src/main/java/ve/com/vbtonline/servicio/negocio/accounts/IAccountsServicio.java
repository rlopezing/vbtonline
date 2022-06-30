package ve.com.vbtonline.servicio.negocio.accounts;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 02:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IAccountsServicio {
    public AccountsOd cargar(String transaccionId, AccountsOd aod) throws Exception;
    public TableOd Consulta (String transaccionId, AccountsOd aod) throws Exception;
    public CuentasOd cargarCuentasEdoCuenta (String Carteras, String idioma) throws Exception;
    public DetalleCuentaEdoCtaOd consultarDetalleCuentasEdoCuenta (List<String> parametros) throws Exception;
    public DetalleCuentaEdoCtaOd consultarDetalleCuentasSaldoTrans (List<String> parametros) throws Exception;
    public List<ContentSelectOd> cargarTiposMovimientosBT (List<String> parametros) throws Exception;
    public TableOd consultarCuentasEdoCuenta (List<String> parametros, String saldo, String idioma) throws Exception;
    public TableOd consultarCuentasSaldoTransf (List<String> parametros, String idioma) throws Exception;
    public TableOd consultarCuentasBloqueos (List<String> parametros, String idioma) throws Exception;
    public List<String> consultarMesAnoMaximo () throws Exception;
    public String consultarFechaCierre(String cartera)throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

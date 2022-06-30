package ve.com.vbtonline.servicio.negocio.timeDeposits;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITimeDepositsServicio {
    public TimeDepositsOd cargar(String transaccionId, TimeDepositsOd tdod, VBTUsersOd usuario) throws Exception;
    public TableOd consultarCertificados (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd consultarVencimientos (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception;
    public TableDetalleOd consultarColocacionesSaldos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public CuentasOd cargarColocaciones (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public DetalleCuentaColocacionesOd consultarColocacionesDetalle (List<String> parametros, VBTUsersOd usuario) throws Exception;
    public TableOd consultarColocacionesBloqueos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public String fechaCierre (String carteras, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public CuentasOd cargarCuentas (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargartiposTD(String tipoTD, String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargarPlazosTD(String plazos, String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargarPlazosPrefTD(String plazos, String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd cargarTablaSolicitudTD (List<String> parametros, String idioma,VBTUsersOd usuario) throws Exception;
    public String insertarSolicitudesTD(TimeDepositsOd td, String idioma, VBTUsersOd usuario) throws Exception;
    public String calcularTasaTD (TimeDepositsOd td, String idioma, VBTUsersOd usuario) throws Exception;
    public CuentasOd cargarCuentasTD (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public String validarParametrosPersonalesTD (String monto, VBTUsersOd usuario, String codcartera, String codcuenta) throws Exception;
}

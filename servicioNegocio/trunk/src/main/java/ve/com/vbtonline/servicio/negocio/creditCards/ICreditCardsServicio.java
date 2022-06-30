package ve.com.vbtonline.servicio.negocio.creditCards;

import ve.com.vbtonline.servicio.io.LoggerIo;
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
public interface ICreditCardsServicio {
    public CreditCardsOd cargar(String transaccionId, CreditCardsOd ccod, VBTUsersOd usuario) throws Exception;
    public TableOd consultarSaldosEstadoCuentaTDC (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarMesesEstadoCuentaTDC (String nroCuenta, VBTUsersOd usuario) throws Exception;
    public List<String> consultarDetallesEstadoCuentaTDC(List<String> parametros, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarTarjetasEstadoCuentaTDC (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd cargarMovimientosTDCITT (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public Map<String,Object> cargarEstatusTDCCL (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarTarjetasTDCITT (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarTarjetasTDCCL (String carteras, String idioma, VBTUsersOd usersOd) throws Exception;
    public SelectOd cargarTdcPagos (String carteras, String idioma, VBTUsersOd usersOd) throws Exception;
    public String crearReglaTDCCL (List<String> parametros, VBTUsersOd usersOd) throws Exception;
    public String eliminarReglaTDCCL (List<String> parametros, VBTUsersOd usersOd) throws Exception;
    public String editarRegla (List<String> parametros, VBTUsersOd usersOd) throws Exception;
    public String validarFechas (List<String> parametros, VBTUsersOd usersOd) throws Exception;
    public String cargarProximoDiaHabil (VBTUsersOd usersOd) throws Exception;
    public List<List<String>> cargarFeriados(String anio, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;

    public TableOd consultarHistorioPagoTDC (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public List<String> consultarDetallesEstadoCuentaCorteTDC(List<String> parametros, VBTUsersOd usuario) throws Exception;
    public List<String> encabezadoMovimientos (List<String> parametros,VBTUsersOd usuario) throws Exception;
    public String guardarPagoTDC (List<String> parametros,VBTUsersOd usuario, ParametrosPersonalesOd parametrospersonales, String idioma) throws Exception;
    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6);

    public TableOd consultarPagosTdc (String carteras, String Status, String idioma, VBTUsersOd usuario) throws Exception;
    public List<String> consultarPagosTdc_detalle_fc (List<String> parametros, VBTUsersOd usuario, String idioma) throws Exception;
    public String cambiarEstatusPagosTDC (String Status, String numPago, VBTUsersOd usuario, String Idioma) throws Exception;

    public String validarLimiteSalvis (List<String> parametros,VBTUsersOd usuario) throws Exception;

    public SelectOd cargarTarjetasTDCCLE (String carteras, String idioma, VBTUsersOd usersOd) throws Exception;
    public List<String> cargarEstatusTDCCLE (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarMovitosCLE (String estatusTarjeta, String idioma, VBTUsersOd usuario) throws Exception;
    public String cambiarEstatusCLE (List<String> parametros, VBTUsersOd usersOd, String Idioma, boolean servicio) throws Exception;

}

package ve.com.vbtonline.servicio.negocio.reportsPdf;

import ve.com.vbtonline.servicio.od.PortafolioOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IReportsPdfServicio {
    public List<String> consultarCabeceraEdoCuenta (String num_cta, String mes , String anio, VBTUsersOd usuario) throws Exception;
    public List<String> consultarCabeceraEdoCuentaTDC (String num_cta, String codproserv , String mes, VBTUsersOd usuario) throws Exception ;
    public List<List<String>> consultarTablaEdoCuenta (String num_cta, String mes , String anio, VBTUsersOd usuario) throws Exception;
    public List<List<String>> consultarTablaEdoCuentaTDC (String num_cta, String codproserv , String mes,VBTUsersOd usuario) throws Exception;
    public List<String> consultarDatosCliente (String cliente, VBTUsersOd usuario) throws Exception;
    public List<String> consultarCeritificadoApertura (String codIntrumento, VBTUsersOd usuario) throws Exception;
    public List<List<String>> consultarmovimientosCertificado (String codIntrumento,String estatus, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public List<List<List<String>>> consultarTablaEdoCuentaFondos (String codemp, String codcar , String fechaEmision, String razon, String moneda, VBTUsersOd usuario) throws Exception;
    public List<List<String>> consultarTablaEdoCuentaFondosDividendos (String codemp, String fechaEmision, VBTUsersOd usuario) throws Exception;
}

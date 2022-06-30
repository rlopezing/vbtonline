package ve.com.vbtonline.servicio.negocio.timeDeposits;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITimeDepositsServicio {
    public TimeDepositsOd cargar(String transaccionId, TimeDepositsOd tdod) throws Exception;
    public TableOd consultarCertificados (List<String> parametros, String idioma) throws Exception;
    public TableOd consultarVencimientos (List<String> parametros, String idioma) throws Exception;
    public TableDetalleOd consultarColocacionesSaldos (List<String> parametros, String idioma) throws Exception;
    public CuentasOd cargarColocaciones (String carteras, String idioma) throws Exception;
    public DetalleCuentaColocacionesOd consultarColocacionesDetalle (List<String> parametros) throws Exception;
    public TableOd consultarColocacionesBloqueos (List<String> parametros, String idioma) throws Exception;
    public String fechaCierre (String carteras) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

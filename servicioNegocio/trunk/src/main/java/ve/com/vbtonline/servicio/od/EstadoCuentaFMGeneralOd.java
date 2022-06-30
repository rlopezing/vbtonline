package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: fescobar
 * Date: 28/10/15
 * Time: 04:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class EstadoCuentaFMGeneralOd {

    private EstadoCuentaFMOd encabezado;
    private List<EstadoCuentaFMOd> detalle;

    public EstadoCuentaFMOd getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(EstadoCuentaFMOd encabezado) {
        this.encabezado = encabezado;
    }

    public List<EstadoCuentaFMOd> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<EstadoCuentaFMOd> detalle) {
        this.detalle = detalle;
    }
}

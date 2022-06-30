package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class CarterasConsolidadasOd {
    private String numCartera;
    private List<TablasOd> tabla;

    public String getNumCartera() {
        return numCartera;
    }

    public void setNumCartera(String numCartera) {
        this.numCartera = numCartera;
    }

    public List<TablasOd> getTabla() {
        return tabla;
    }

    public void setTabla(List<TablasOd> tabla) {
        this.tabla = tabla;
    }
}

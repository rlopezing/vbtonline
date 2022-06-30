package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class TableOdDetallesOd implements Serializable {
  private TableOd tabla;
  private List<String> detalles;

    public TableOd getTabla() {
        return tabla;
    }

    public void setTabla(TableOd tabla) {
        this.tabla = tabla;
    }

    public List<String> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<String> detalles) {
        this.detalles = detalles;
    }
}

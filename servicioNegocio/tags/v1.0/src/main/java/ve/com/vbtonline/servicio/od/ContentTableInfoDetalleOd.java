package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 05:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContentTableInfoDetalleOd implements Serializable {
    private List<DataColumnDetalleOd> data_culumn;

    public List<DataColumnDetalleOd> getData_culumn() {
        return data_culumn;
    }

    public void setData_culumn(List<DataColumnDetalleOd> data_culumn) {
        this.data_culumn = data_culumn;
    }
}

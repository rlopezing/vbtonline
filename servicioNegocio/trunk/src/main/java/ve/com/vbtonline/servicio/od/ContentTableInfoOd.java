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
public class ContentTableInfoOd implements Serializable {
    private List<DataColumnOd> data_culumn;

    public List<DataColumnOd> getData_culumn() {
        return data_culumn;
    }

    public void setData_culumn(List<DataColumnOd> data_culumn) {
        this.data_culumn = data_culumn;
    }
}

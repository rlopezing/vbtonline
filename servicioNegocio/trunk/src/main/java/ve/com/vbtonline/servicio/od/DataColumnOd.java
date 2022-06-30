package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataColumnOd implements Serializable {
    private String data_columna;


    public String getData_columna() {
        return data_columna;
    }

    public void setData_columna(String data_columna) {
        this.data_columna = data_columna;
    }
}

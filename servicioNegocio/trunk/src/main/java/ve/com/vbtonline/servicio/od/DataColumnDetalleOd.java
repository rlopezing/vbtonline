package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataColumnDetalleOd implements Serializable {
    private String data_columna;
    private String nombre_columna;


    public String getData_columna() {
        return data_columna;
    }

    public void setData_columna(String data_columna) {
        this.data_columna = data_columna;
    }

    public String getNombre_columna() {
        return nombre_columna;
    }

    public void setNombre_columna(String nombre_columna) {
        this.nombre_columna = nombre_columna;
    }
}

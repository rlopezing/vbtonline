package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TablasOd {
    private String Nombre;
    private String div;
    private TableOd datos;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public TableOd getDatos() {
        return datos;
    }

    public void setDatos(TableOd datos) {
        this.datos = datos;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }
}

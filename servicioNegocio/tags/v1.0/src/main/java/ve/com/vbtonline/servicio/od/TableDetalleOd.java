package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TableDetalleOd {
    private List<ContentsTableColumnsOd> contenidoTabla_culumnas;
    private List<ContentTableInfoOd> contenidoTabla_info;


    public List<ContentTableInfoOd> getContenidoTabla_info() {
        return contenidoTabla_info;
    }

    public void setContenidoTabla_info(List<ContentTableInfoOd> contenidoTabla_info) {
        this.contenidoTabla_info = contenidoTabla_info;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnas() {
        return contenidoTabla_culumnas;
    }

    public void setContenidoTabla_culumnas(List<ContentsTableColumnsOd> contenidoTabla_culumnas) {
        this.contenidoTabla_culumnas = contenidoTabla_culumnas;
    }

}

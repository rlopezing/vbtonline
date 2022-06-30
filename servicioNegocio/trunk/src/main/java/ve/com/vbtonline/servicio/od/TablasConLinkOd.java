package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TablasConLinkOd {
    private List<String> link;
    private TableOd datos;

    public List<String> getLink() {
        return link;
    }

    public void setLink(List<String> link) {
        this.link = link;
    }

    public TableOd getDatos() {
        return datos;
    }

    public void setDatos(TableOd datos) {
        this.datos = datos;
    }
}

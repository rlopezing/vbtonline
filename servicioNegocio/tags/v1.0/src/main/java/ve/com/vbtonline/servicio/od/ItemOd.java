package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 16/07/12
 * Time: 01:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class ItemOd {
    private String id;
    private String html_span;
    private String html_spanID;
    private String estilo;
    private List<SubItemsOd> sub_opciones;
    private String codope;

    public String getCodope() {
        return codope;
    }

    public void setCodope(String codope) {
        this.codope = codope;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtml_span() {
        return html_span;
    }

    public void setHtml_span(String html_span) {
        this.html_span = html_span;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public List<SubItemsOd> getSub_opciones() {
        return sub_opciones;
    }

    public void setSub_opciones(List<SubItemsOd> sub_opciones) {
        this.sub_opciones = sub_opciones;
    }

    public String getHtml_spanID() {
        return html_spanID;
    }

    public void setHtml_spanID(String html_spanID) {
        this.html_spanID = html_spanID;
    }
}

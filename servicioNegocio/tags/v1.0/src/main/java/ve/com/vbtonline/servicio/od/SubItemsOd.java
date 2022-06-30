package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 16/07/12
 * Time: 01:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class SubItemsOd implements Serializable {
    private String id;
    private String span_html;
    private String span_htmlID;
    private String estilo;
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

    public String getSpan_html() {
        return span_html;
    }

    public void setSpan_html(String span_html) {
        this.span_html = span_html;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getSpan_htmlID() {
        return span_htmlID;
    }

    public void setSpan_htmlID(String span_htmlID) {
        this.span_htmlID = span_htmlID;
    }
}

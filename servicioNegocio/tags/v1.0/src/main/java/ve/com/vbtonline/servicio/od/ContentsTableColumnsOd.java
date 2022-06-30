package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 17/07/12
 * Time: 02:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContentsTableColumnsOd implements Serializable {
    private String span_html;


    public String getSpan_html() {
        return span_html;
    }

    public void setSpan_html(String span_html) {
        this.span_html = span_html;
    }
}

package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class CtaOpcOd implements Serializable {
    private String codopc;
    private String codsis;
    private String desopc;
    private String padcodopc;
    private String foraso;
    private String proceso;
    private String selectora;
    private String nivel;
    private String orden;
    private String usrid;
    private String menu;

    public String getCodopc() {
        return codopc;
    }

    public void setCodopc(String codopc) {
        this.codopc = codopc;
    }

    public String getCodsis() {
        return codsis;
    }

    public void setCodsis(String codsis) {
        this.codsis = codsis;
    }

    public String getDesopc() {
        return desopc;
    }

    public void setDesopc(String desopc) {
        this.desopc = desopc;
    }

    public String getPadcodopc() {
        return padcodopc;
    }

    public void setPadcodopc(String padcodopc) {
        this.padcodopc = padcodopc;
    }

    public String getForaso() {
        return foraso;
    }

    public void setForaso(String foraso) {
        this.foraso = foraso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getSelectora() {
        return selectora;
    }

    public void setSelectora(String selectora) {
        this.selectora = selectora;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}

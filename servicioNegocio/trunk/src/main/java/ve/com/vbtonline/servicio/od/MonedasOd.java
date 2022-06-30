package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class MonedasOd implements Serializable {
    private String codmon;
    private String nombmon;
    private String simbmon;
    private String usrid;
    private String codinterno;
    private String codmonnum;

    public String getCodmon() {
        return codmon;
    }

    public void setCodmon(String codmon) {
        this.codmon = codmon;
    }

    public String getNombmon() {
        return nombmon;
    }

    public void setNombmon(String nombmon) {
        this.nombmon = nombmon;
    }

    public String getSimbmon() {
        return simbmon;
    }

    public void setSimbmon(String simbmon) {
        this.simbmon = simbmon;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getCodinterno() {
        return codinterno;
    }

    public void setCodinterno(String codinterno) {
        this.codinterno = codinterno;
    }

    public String getCodmonnum() {
        return codmonnum;
    }

    public void setCodmonnum(String codmonnum) {
        this.codmonnum = codmonnum;
    }
}

package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class GrpCiaOpcOd implements Serializable {
    private String codgru;
    private String codsis;
    private String codcia;
    private String codopc;
    private String tipacc;
    private String usrid;
    private int nivsup;

    public String getCodgru() {

        return codgru;
    }

    public void setCodgru(String codgru) {
        this.codgru = codgru;
    }

    public String getCodsis() {
        return codsis;
    }

    public void setCodsis(String codsis) {
        this.codsis = codsis;
    }

    public String getCodcia() {
        return codcia;
    }

    public void setCodcia(String codcia) {
        this.codcia = codcia;
    }

    public String getCodopc() {
        return codopc;
    }

    public void setCodopc(String codopc) {
        this.codopc = codopc;
    }

    public String getTipacc() {
        return tipacc;
    }

    public void setTipacc(String tipacc) {
        this.tipacc = tipacc;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public int getNivsup() {
        return nivsup;
    }

    public void setNivsup(int nivsup) {
        this.nivsup = nivsup;
    }
}

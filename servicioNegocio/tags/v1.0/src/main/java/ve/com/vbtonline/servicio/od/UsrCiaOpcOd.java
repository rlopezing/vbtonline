package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class UsrCiaOpcOd implements Serializable {
    private String login;
    private String codcia;
    private String codsis;
    private String codopc;
    private String tipoacc;
    private String usrid;
    private int nivsup;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCodcia() {
        return codcia;
    }

    public void setCodcia(String codcia) {
        this.codcia = codcia;
    }

    public String getCodsis() {
        return codsis;
    }

    public void setCodsis(String codsis) {
        this.codsis = codsis;
    }

    public String getCodopc() {
        return codopc;
    }

    public void setCodopc(String codopc) {
        this.codopc = codopc;
    }

    public String getTipoacc() {
        return tipoacc;
    }

    public void setTipoacc(String tipoacc) {
        this.tipoacc = tipoacc;
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

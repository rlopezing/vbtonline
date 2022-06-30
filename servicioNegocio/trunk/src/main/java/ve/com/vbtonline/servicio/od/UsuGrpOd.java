package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class UsuGrpOd implements Serializable {
    private String login;
    private String codgru;
    private Calendar fechasig;
    private String codsis;
    private String codcia;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCodgru() {
        return codgru;
    }

    public void setCodgru(String codgru) {
        this.codgru = codgru;
    }

    public Calendar getFechasig() {
        return fechasig;
    }

    public void setFechasig(Calendar fechasig) {
        this.fechasig = fechasig;
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
}

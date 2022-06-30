package ve.com.vbtonline.servicio.od;

/**
 * Created with IntelliJ IDEA.
 * User: kalvarado
 * Date: 07/10/15
 * Time: 01:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class SessionActiva {
    private String login;
    private String sessionId;
    private String fechaLogin;
    private String horaLogin;
    private String tipoUsuario;
    private String ip;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getFechaLogin() {
        return fechaLogin;
    }

    public void setFechaLogin(String fechaLogin) {
        this.fechaLogin = fechaLogin;
    }

    public String getHoraLogin() {
        return horaLogin;
    }

    public void setHoraLogin(String horaLogin) {
        this.horaLogin = horaLogin;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

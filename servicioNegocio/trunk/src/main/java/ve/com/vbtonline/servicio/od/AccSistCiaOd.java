package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class AccSistCiaOd implements Serializable {
    private String codsis;
    private String codpercia;
    private String accion;
    private int posicion;

    public String getCodsis() {
        return codsis;
    }

    public void setCodsis(String codsis) {
        this.codsis = codsis;
    }

    public String getCodpercia() {
        return codpercia;
    }

    public void setCodpercia(String codpercia) {
        this.codpercia = codpercia;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }
}

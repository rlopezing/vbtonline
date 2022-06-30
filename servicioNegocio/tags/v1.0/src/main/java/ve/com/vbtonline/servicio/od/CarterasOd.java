package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: MI PC
 * Date: 28/05/2010
 * Time: 12:36:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class CarterasOd implements Serializable {
    private String codigoCartera;
    private String modalidad;
    private String estatus;
    private String clientePrincipal;
    private String asesor;
    private String ejecutivo;

    public String getCodigoCartera() {
        return codigoCartera;
    }

    public void setCodigoCartera(String codigoCartera) {
        this.codigoCartera = codigoCartera;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getClientePrincipal() {
        return clientePrincipal;
    }

    public void setClientePrincipal(String clientePrincipal) {
        this.clientePrincipal = clientePrincipal;
    }

    public String getAsesor() {
        return asesor;
    }

    public void setAsesor(String asesor) {
        this.asesor = asesor;
    }

    public String getEjecutivo() {
        return ejecutivo;
    }

    public void setEjecutivo(String ejecutivo) {
        this.ejecutivo = ejecutivo;
    }
}

package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MI PC
 * Date: 28/05/2010
 * Time: 12:36:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class ContratosOd implements Serializable {
    private List<CarterasOd> carteras;
    private List<VBTUsersOd> usuarios;
    private String numeroContrato;
    private String descripcion;
    private String estatus;
    private List<ContentSelectOd> motivosRechazo;
    private String tipoContrato;
    private String requiereSoporte;

    public List<ContentSelectOd> getMotivosRechazo() {
        return motivosRechazo;
    }

    public void setMotivosRechazo(List<ContentSelectOd> motivosRechazo) {
        this.motivosRechazo = motivosRechazo;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public List<CarterasOd> getCarteras() {
        return carteras;
    }

    public void setCarteras(List<CarterasOd> carteras) {
        this.carteras = carteras;
    }

    public List<VBTUsersOd> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<VBTUsersOd> usuarios) {
        this.usuarios = usuarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public String getRequiereSoporte() {
        return requiereSoporte;
    }

    public void setRequiereSoporte(String requiereSoporte) {
        this.requiereSoporte = requiereSoporte;
    }
}

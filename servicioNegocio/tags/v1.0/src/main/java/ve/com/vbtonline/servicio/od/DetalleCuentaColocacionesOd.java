package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class DetalleCuentaColocacionesOd implements Serializable {

    private String bloqueado;
    private String fechaCierre;
    private String fechaApertura;
    private String fechaVencimiento;
    private String montoApertura;
    private String montoVencimiento;
    private String tasa;
    private String clientePrincipal;
    private String moneda;

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(String fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(String fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getMontoApertura() {
        return montoApertura;
    }

    public void setMontoApertura(String montoApertura) {
        this.montoApertura = montoApertura;
    }

    public String getMontoVencimiento() {
        return montoVencimiento;
    }

    public void setMontoVencimiento(String montoVencimiento) {
        this.montoVencimiento = montoVencimiento;
    }

    public String getTasa() {
        return tasa;
    }

    public void setTasa(String tasa) {
        this.tasa = tasa;
    }

    public String getClientePrincipal() {
        return clientePrincipal;
    }

    public void setClientePrincipal(String clientePrincipal) {
        this.clientePrincipal = clientePrincipal;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
}

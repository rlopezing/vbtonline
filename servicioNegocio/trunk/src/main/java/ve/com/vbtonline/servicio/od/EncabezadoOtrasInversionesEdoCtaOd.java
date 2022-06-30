package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Fescobar
 * Date: 06/08/14
 * Time: 15:50 pm
 * To change this template use File | Settings | File Templates.
 */
public class EncabezadoOtrasInversionesEdoCtaOd implements Serializable {
    private String cliente;
    private String moneda;
    private String unidadesGarantia;
    private String unidadesDisponibles;
    private String unidadesTotales;
    private String VUI;
    private String montoTransito;
    private String totalMoneda;

    public String getCliente() {
        return cliente;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getUnidadesGarantia() {
        return unidadesGarantia;
    }

    public void setUnidadesGarantia(String unidadesGarantia) {
        this.unidadesGarantia = unidadesGarantia;
    }

    public String getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(String unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public String getUnidadesTotales() {
        return unidadesTotales;
    }

    public void setUnidadesTotales(String unidadesTotales) {
        this.unidadesTotales = unidadesTotales;
    }

    public String getVUI() {
        return VUI;
    }

    public void setVUI(String VUI) {
        this.VUI = VUI;
    }

    public String getMontoTransito() {
        return montoTransito;
    }

    public void setMontoTransito(String montoTransito) {
        this.montoTransito = montoTransito;
    }

    public String getTotalMoneda() {
        return totalMoneda;
    }

    public void setTotalMoneda(String totalMoneda) {
        this.totalMoneda = totalMoneda;
    }
}
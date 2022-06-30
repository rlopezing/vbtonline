package ve.com.vbtonline.servicio.od;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 16/07/12
 * Time: 01:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class CuentasOd {
    private String fecha;
    private String moneda;
    private String empresa;
    private List<ContentSelectOd> contenido;
    private List<String> monedas;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<ContentSelectOd> getContenido() {
        return contenido;
    }

    public void setContenido(List<ContentSelectOd> contenido) {
        this.contenido = contenido;
    }

    public List<String> getMonedas() {
        return monedas;
    }

    public void setMonedas(List<String> monedas) {
        this.monedas = monedas;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}

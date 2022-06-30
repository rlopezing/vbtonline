package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 02/10/13
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClientInstructionOd implements Serializable{

    private String cliente;
    private String rif;
    private String tipoTransf;
    private String maxcartas;
    private String vencimiento;
    private String codperclicarta;
    private String codpercli;
    private String descTipoTransf;
    private java.util.List<ContentSelectOd> motivos;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getTipoTransf() {
        return tipoTransf;
    }

    public void setTipoTransf(String tipoTransf) {
        this.tipoTransf = tipoTransf;
    }

    public String getMaxcartas() {
        return maxcartas;
    }

    public void setMaxcartas(String maxcartas) {
        this.maxcartas = maxcartas;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getCodperclicarta() {
        return codperclicarta;
    }

    public void setCodperclicarta(String codperclicarta) {
        this.codperclicarta = codperclicarta;
    }

    public String getCodpercli() {
        return codpercli;
    }

    public void setCodpercli(String codpercli) {
        this.codpercli = codpercli;
    }

    public String getDescTipoTransf() {
        return descTipoTransf;
    }

    public void setDescTipoTransf(String descTipoTransf) {
        this.descTipoTransf = descTipoTransf;
    }

    public List<ContentSelectOd> getMotivos() {
        return motivos;
    }

    public void setMotivos(List<ContentSelectOd> motivos) {
        this.motivos = motivos;
    }
}

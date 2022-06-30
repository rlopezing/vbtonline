package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class ParametrosPersonalesFCOd implements Serializable {
   private String codpercli;
   private String num_contrato;
   private Integer vbt_nmtd;
   private String vbt_mmtd;
   private String vbt_mmto;
   private String correo;

    public String getCodpercli() {
        return codpercli;
    }

    public void setCodpercli(String codpercli) {
        this.codpercli = codpercli;
    }

    public String getNum_contrato() {
        return num_contrato;
    }

    public void setNum_contrato(String num_contrato) {
        this.num_contrato = num_contrato;
    }

    public Integer getVbt_nmtd() {
        return vbt_nmtd;
    }

    public void setVbt_nmtd(Integer vbt_nmtd) {
        this.vbt_nmtd = vbt_nmtd;
    }

    public String getVbt_mmtd() {
        return vbt_mmtd;
    }

    public void setVbt_mmtd(String vbt_mmtd) {
        this.vbt_mmtd = vbt_mmtd;
    }

    public String getVbt_mmto() {
        return vbt_mmto;
    }

    public void setVbt_mmto(String vbt_mmto) {
        this.vbt_mmto = vbt_mmto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}

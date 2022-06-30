package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class ParametrosPersonalesOd implements Serializable {
   private String codpercli;
   private String num_contrato;
   private Integer vbt_nmtd;
   private String vbt_mmaxtd;
   private String vbt_mminto;
   private String vbt_mmto;
   private Integer ex_nmtd;
   private String ex_mmtd;
   private String ex_mminto;
   private String ex_mmto;
   private String account_num;
   private String act_plazo;

    private String minimun_balance;

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

    public String getVbt_mmaxtd() {
        return vbt_mmaxtd;
    }

    public void setVbt_mmaxtd(String vbt_mmaxtd) {
        this.vbt_mmaxtd = vbt_mmaxtd;
    }

    public String getVbt_mminto() {
        return vbt_mminto;
    }

    public void setVbt_mminto(String vbt_mminto) {
        this.vbt_mminto = vbt_mminto;
    }

    public String getVbt_mmto() {
        return vbt_mmto;
    }

    public void setVbt_mmto(String vbt_mmto) {
        this.vbt_mmto = vbt_mmto;
    }

    public Integer getEx_nmtd() {
        return ex_nmtd;
    }

    public void setEx_nmtd(Integer ex_nmtd) {
        this.ex_nmtd = ex_nmtd;
    }

    public String getEx_mmtd() {
        return ex_mmtd;
    }

    public void setEx_mmtd(String ex_mmtd) {
        this.ex_mmtd = ex_mmtd;
    }

    public String getEx_mminto() {
        return ex_mminto;
    }

    public void setEx_mminto(String ex_mminto) {
        this.ex_mminto = ex_mminto;
    }

    public String getEx_mmto() {
        return ex_mmto;
    }

    public void setEx_mmto(String ex_mmto) {
        this.ex_mmto = ex_mmto;
    }

    public String getAccount_num() {
        return account_num;
    }

    public void setAccount_num(String account_num) {
        this.account_num = account_num;
    }
    public String getMinimun_balance() {
        return minimun_balance;
    }

    public void setMinimun_balance(String minimun_balance) {
        this.minimun_balance = minimun_balance;
    }

    public String getAct_plazo() {
        return act_plazo;
    }

    public void setAct_plazo(String act_plazo) {
        this.act_plazo = act_plazo;
    }
}

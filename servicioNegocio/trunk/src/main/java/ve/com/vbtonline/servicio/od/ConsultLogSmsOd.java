package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: lgutierrez
 * Date: 03/09/14
 * Time: 02:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConsultLogSmsOd  implements Serializable {

    private String strContrato;
    private String strUsuario;
    private String strTelefono;
    private String strTipoStatus;
    private String strTxtDesde;
    private String strTxtHasta;
    private String strTipoMotivo;


    public String getStrContrato() {
        return strContrato;
    }

    public void setStrContrato(String strContrato) {
        this.strContrato = strContrato;
    }

    public String getStrUsuario() {
        return strUsuario;
    }

    public void setStrUsuario(String strUsuario) {
        this.strUsuario = strUsuario;
    }

    public String getStrTelefono() {
        return strTelefono;
    }

    public void setStrTelefono(String strTelefono) {
        this.strTelefono = strTelefono;
    }

    public String getStrTipoStatus() {
        return strTipoStatus;
    }

    public void setStrTipoStatus(String strTipoStatus) {
        this.strTipoStatus = strTipoStatus;
    }

    public String getStrTxtDesde() {
        return strTxtDesde;
    }

    public void setStrTxtDesde(String strTxtDesde) {
        this.strTxtDesde = strTxtDesde;
    }

    public String getStrTxtHasta() {
        return strTxtHasta;
    }

    public void setStrTxtHasta(String strTxtHasta) {
        this.strTxtHasta = strTxtHasta;
    }

    public String getStrTipoMotivo() {
        return strTipoMotivo;
    }

    public void setStrTipoMotivo(String strTipoMotivo) {
        this.strTipoMotivo = strTipoMotivo;
    }


}

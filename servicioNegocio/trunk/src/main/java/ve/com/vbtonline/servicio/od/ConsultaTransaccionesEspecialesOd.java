package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: bmundarain
 * Date: 25/08/16
 * Time: 11:00 am
 */
public class ConsultaTransaccionesEspecialesOd implements Serializable {
    private int codTipoMov;
    private String strTipoMov;
    private String strCodigoBanco;
    private String strSwiftBanco;
    private String strNumeroCuenta;
    private String strAddressBanco;
    private String strCityBanco;
    private String strCountryBanco;
    private String strPostalCodeBanco;
    private int showAccount;


    public String getStrNumeroCuenta() {
        return strNumeroCuenta;
    }

    public void setStrNumeroCuenta(String strNumeroCuenta) {
        this.strNumeroCuenta = strNumeroCuenta;
    }

    public int getCodTipoMov() {
        return codTipoMov;
    }

    public void setStrCodTipoMov(int codTipoMov) {
        this.codTipoMov = codTipoMov;
    }

    public String getStrTipoMov() {
        return strTipoMov;
    }

    public void setStrTipoMov(String strTipoMov) {
        this.strTipoMov = strTipoMov;
    }

    public String getStrCodigoBanco() {
        return strCodigoBanco;
    }

    public void setStrCodigoBanco(String strCodigoBanco) {
        this.strCodigoBanco = strCodigoBanco;
    }

    public String getStrSwiftBanco() {
        return strSwiftBanco;
    }

    public void setStrSwiftBanco(String strSwiftBanco) {
        this.strSwiftBanco = strSwiftBanco;
    }

    public String getStrAddressBanco() {
        return strAddressBanco;
    }

    public void setStrAddressBanco(String strAddressBanco) {
        this.strAddressBanco = strAddressBanco;
    }

    public String getStrCityBanco() {
        return strCityBanco;
    }

    public void setStrCityBanco(String strCityBanco) {
        this.strCityBanco = strCityBanco;
    }

    public String getStrCountryBanco() {
        return strCountryBanco;
    }

    public void setStrCountryBanco(String strCountryBanco) {
        this.strCountryBanco = strCountryBanco;
    }

    public String getStrPostalCodeBanco() {
        return strPostalCodeBanco;
    }

    public void setStrPostalCodeBanco(String strPostalCodeBanco) {
        this.strPostalCodeBanco = strPostalCodeBanco;
    }

    public int getShowAccount() {
        return showAccount;
    }

    public void setShowAccount(int showAccount) {
        this.showAccount = showAccount;
    }
}

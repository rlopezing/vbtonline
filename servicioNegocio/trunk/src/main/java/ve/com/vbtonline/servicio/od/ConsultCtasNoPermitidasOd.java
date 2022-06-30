package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class ConsultCtasNoPermitidasOd implements Serializable {
    private String strCodigoBanco;
    private String strNumeroCuenta;
    private String strCodigoTipoBanco;
    private String strNombreBanco;

    public String getStrCodigoBanco() {
        return strCodigoBanco;
    }

    public void setStrCodigoBanco(String strCodigoBanco) {
        this.strCodigoBanco = strCodigoBanco;
    }

    public String getStrNumeroCuenta() {
        return strNumeroCuenta;
    }

    public void setStrNumeroCuenta(String strNumeroCuenta) {
        this.strNumeroCuenta = strNumeroCuenta;
    }

    public String getStrCodigoTipoBanco() {
        return strCodigoTipoBanco;
    }

    public void setStrCodigoTipoBanco(String strCodigoTipoBanco) {
        this.strCodigoTipoBanco = strCodigoTipoBanco;
    }

    public String getStrNombreBanco() {
        return strNombreBanco;
    }

    public void setStrNombreBanco(String strNombreBanco) {
        this.strNombreBanco = strNombreBanco;
    }
}

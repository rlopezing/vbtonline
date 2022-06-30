package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class OpcionesOd implements Serializable {
    private String codigoPadre;
    private Integer opcionNivel;
    private Integer opcionOrden;
    private String opcionDescripcion;
    private String opcionCodigo;

    public String getCodigoPadre() {
        return codigoPadre;
    }

    public void setCodigoPadre(String codigoPadre) {
        this.codigoPadre = codigoPadre;
    }

    public Integer getOpcionNivel() {
        return opcionNivel;
    }

    public void setOpcionNivel(Integer opcionNivel) {
        this.opcionNivel = opcionNivel;
    }

    public Integer getOpcionOrden() {
        return opcionOrden;
    }

    public void setOpcionOrden(Integer opcionOrden) {
        this.opcionOrden = opcionOrden;
    }

    public String getOpcionDescripcion() {
        return opcionDescripcion;
    }

    public void setOpcionDescripcion(String opcionDescripcion) {
        this.opcionDescripcion = opcionDescripcion;
    }

    public String getOpcionCodigo() {
        return opcionCodigo;
    }

    public void setOpcionCodigo(String opcionCodigo) {
        this.opcionCodigo = opcionCodigo;
    }
}

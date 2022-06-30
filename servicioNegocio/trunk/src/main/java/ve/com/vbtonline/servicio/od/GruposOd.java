package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class GruposOd implements Serializable {
    private String grupo;
    private String idGrupo;
    private String grupoCategoria;
    private Integer numUsuGrp;
    private Integer OpcionAsociada;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        this.idGrupo = idGrupo;
    }

    public String getGrupoCategoria() {
        return grupoCategoria;
    }

    public void setGrupoCategoria(String grupoCategoria) {
        this.grupoCategoria = grupoCategoria;
    }

    public Integer getNumUsuGrp() {
        return numUsuGrp;
    }

    public void setNumUsuGrp(Integer numUsuGrp) {
        this.numUsuGrp = numUsuGrp;
    }

    public Integer getOpcionAsociada() {
        return OpcionAsociada;
    }

    public void setOpcionAsociada(Integer opcionAsociada) {
        OpcionAsociada = opcionAsociada;
    }
}

package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class PrivilegioOd implements Serializable {
  private String opcion;
  private List<String> privilegios;

    public List<String> getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(List<String> privilegios) {
        this.privilegios = privilegios;
    }

    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }
}

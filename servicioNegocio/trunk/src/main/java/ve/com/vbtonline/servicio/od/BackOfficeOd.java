package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackOfficeOd implements Serializable {
   private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

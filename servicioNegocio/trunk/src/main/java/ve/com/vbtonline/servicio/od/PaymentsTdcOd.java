package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Bell Mundarain
 * Date: 09/08/16
 * Time: 16:00
 *
 */
public class PaymentsTdcOd implements Serializable {
   private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
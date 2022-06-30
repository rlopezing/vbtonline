package ve.com.vbtonline.servicio.od;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class CtaGrpOd implements Serializable {
    private String codgru;
    private String nombgrp;
    private String usrid;
    private String categoria;


    public String getCodgru() {
        return codgru;
    }

    public void setCodgru(String codgru) {
        this.codgru = codgru;
    }

    public String getNombgrp() {
        return nombgrp;
    }

    public void setNombgrp(String nombgrp) {
        this.nombgrp = nombgrp;
    }

    public String getUsrid() {
        return usrid;
    }

    public void setUsrid(String usrid) {
        this.usrid = usrid;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

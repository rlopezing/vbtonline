package ve.com.vbtonline.servicio.od;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:00 am
 * To change this template use File | Settings | File Templates.
 */
public class MenuOd implements Serializable {
    private String codigo;
    private String descripcion;
    private String codPadre;
    private int nivel;
    private int orden;
    private String acciones;
    private int codgrp;
    private String mnBackoffice;
    private String mnInicio;
    private String mnSalir;
    private String opcionAccion;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodPadre() {
        return codPadre;
    }

    public void setCodPadre(String codPadre) {
        this.codPadre = codPadre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public int getCodgrp() {
        return codgrp;
    }

    public void setCodgrp(int codgrp) {
        this.codgrp = codgrp;
    }

    public String getMnBackoffice() {
        return mnBackoffice;
    }

    public void setMnBackoffice(String mnBackoffice) {
        this.mnBackoffice = mnBackoffice;
    }

    public String getMnInicio() {
        return mnInicio;
    }

    public void setMnInicio(String mnInicio) {
        this.mnInicio = mnInicio;
    }

    public String getMnSalir() {
        return mnSalir;
    }

    public void setMnSalir(String mnSalir) {
        this.mnSalir = mnSalir;
    }

    public String getOpcionAccion() {
        return opcionAccion;
    }

    public void setOpcionAccion(String opcionAccion) {
        this.opcionAccion = opcionAccion;
    }
}

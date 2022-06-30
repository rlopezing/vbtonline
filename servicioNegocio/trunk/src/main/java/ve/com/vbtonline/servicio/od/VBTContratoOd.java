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
public class VBTContratoOd implements Serializable {
    private String num_contrato;
    private String descripcion;
    private String creado_por;
    private Calendar fecha_creacion;
    private String estatus;
    private Calendar fecha_estatus;
    private char acepto_transferencias;
    private String usuario_contrato_transferencia;
    private String ip_contrato_transferencia;
    private Calendar fecha_contrato_transferencia;
    private String num_cartera;
    private String tipoContrato;

    public String getNum_cartera() {
        return num_cartera;
    }

    public void setNum_cartera(String num_cartera) {
        this.num_cartera = num_cartera;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getNum_contrato() {
        return num_contrato;
    }

    public void setNum_contrato(String num_contrato) {
        this.num_contrato = num_contrato;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreado_por() {
        return creado_por;
    }

    public void setCreado_por(String creado_por) {
        this.creado_por = creado_por;
    }

    public Calendar getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Calendar fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Calendar getFecha_estatus() {
        return fecha_estatus;
    }

    public void setFecha_estatus(Calendar fecha_estatus) {
        this.fecha_estatus = fecha_estatus;
    }

    public char getAcepto_transferencias() {
        return acepto_transferencias;
    }

    public void setAcepto_transferencias(char acepto_transferencias) {
        this.acepto_transferencias = acepto_transferencias;
    }

    public String getUsuario_contrato_transferencia() {
        return usuario_contrato_transferencia;
    }

    public void setUsuario_contrato_transferencia(String usuario_contrato_transferencia) {
        this.usuario_contrato_transferencia = usuario_contrato_transferencia;
    }

    public String getIp_contrato_transferencia() {
        return ip_contrato_transferencia;
    }

    public void setIp_contrato_transferencia(String ip_contrato_transferencia) {
        this.ip_contrato_transferencia = ip_contrato_transferencia;
    }

    public Calendar getFecha_contrato_transferencia() {
        return fecha_contrato_transferencia;
    }

    public void setFecha_contrato_transferencia(Calendar fecha_contrato_transferencia) {
        this.fecha_contrato_transferencia = fecha_contrato_transferencia;
    }
}

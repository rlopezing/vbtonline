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
public class VBTUsersOd implements Serializable {
    private String login;
    private String codpercli;
    private Calendar fecha_ingreso;
    private String direccion;
    private String telefono;
    private String telefono_celular;
    private String email;
    private String password;
    private String tipo;    //grupo
    private String nombres;
    private String tipo_grupo;
    private String last_login;
    private String last_login_hora;
    private String status_cuenta;
    private String cirif;
    private String tipo_cirif;
    private String ambito;
    private String nmAmbito;

    private int intentos_login;
    private String fecha_status;
    private String fecha_login;
    private String password_ope;
    private String password_especiales;
    private int intentos_ope;
    private char cambios_pass_ope;
    private String last_login_ope;
    private String last_login_ope_hora;
    private String cambioStatus;
    private String numContrato;
    private String IP;
    private String cambioPass;
    private boolean terminosCondiciones;
    private String statusContrato;
    private Boolean backoffice;
    private String relacion;
    private String grupo;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Boolean getBackoffice() {
        return backoffice;
    }

    public void setBackoffice(Boolean backoffice) {
        this.backoffice = backoffice;
    }

    public String getStatusContrato() {
        return statusContrato;
    }

    public void setStatusContrato(String statusContrato) {
        this.statusContrato = statusContrato;
    }

    public boolean isTerminosCondiciones() {
        return terminosCondiciones;
    }

    public void setTerminosCondiciones(boolean terminosCondiciones) {
        this.terminosCondiciones = terminosCondiciones;
    }

    public String getCambioPass() {
        return cambioPass;
    }

    public String getPassword_especiales() {
        return password_especiales;
    }

    public void setPassword_especiales(String password_especiales) {
        this.password_especiales = password_especiales;
    }

    public void setCambioPass(String cambioPass) {
        this.cambioPass = cambioPass;
    }

    public String getIP() {

        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCodpercli() {
        return codpercli;
    }

    public void setCodpercli(String codpercli) {
        this.codpercli = codpercli;
    }

    public Calendar getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Calendar fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono_celular() {
        return telefono_celular;
    }

    public void setTelefono_celular(String telefono_celular) {
        this.telefono_celular = telefono_celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }



    public String getStatus_cuenta() {
        return status_cuenta;
    }

    public void setStatus_cuenta(String status_cuenta) {
        this.status_cuenta = status_cuenta;
    }

    public String getCirif() {
        return cirif;
    }

    public void setCirif(String cirif) {
        this.cirif = cirif;
    }

    public String getTipo_cirif() {
        return tipo_cirif;
    }

    public void setTipo_cirif(String tipo_cirif) {
        this.tipo_cirif = tipo_cirif;
    }

    public int getIntentos_login() {
        return intentos_login;
    }

    public void setIntentos_login(int intentos_login) {
        this.intentos_login = intentos_login;
    }




    public String getPassword_ope() {
        return password_ope;
    }

    public void setPassword_ope(String password_ope) {
        this.password_ope = password_ope;
    }

    public int getIntentos_ope() {
        return intentos_ope;
    }

    public void setIntentos_ope(int intentos_ope) {
        this.intentos_ope = intentos_ope;
    }

    public char getCambios_pass_ope() {
        return cambios_pass_ope;
    }

    public void setCambios_pass_ope(char cambios_pass_ope) {
        this.cambios_pass_ope = cambios_pass_ope;
    }



    public String getTipo_grupo() {
        return tipo_grupo;
    }

    public void setTipo_grupo(String tipo_grupo) {
        this.tipo_grupo = tipo_grupo;
    }

    public String getCambioStatus() {
        return cambioStatus;
    }

    public void setCambioStatus(String cambioStatus) {
        this.cambioStatus = cambioStatus;
    }

    public String getNumContrato() {
        return numContrato;
    }

    public void setNumContrato(String numContrato) {
        this.numContrato = numContrato;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public String getFecha_status() {
        return fecha_status;
    }

    public void setFecha_status(String fecha_status) {
        this.fecha_status = fecha_status;
    }

    public String getFecha_login() {
        return fecha_login;
    }

    public void setFecha_login(String fecha_login) {
        this.fecha_login = fecha_login;
    }

    public String getLast_login_ope() {
        return last_login_ope;
    }

    public void setLast_login_ope(String last_login_ope) {
        this.last_login_ope = last_login_ope;
    }

    public String getLast_login_hora() {
        return last_login_hora;
    }

    public void setLast_login_hora(String last_login_hora) {
        this.last_login_hora = last_login_hora;
    }

    public String getLast_login_ope_hora() {
        return last_login_ope_hora;
    }

    public void setLast_login_ope_hora(String last_login_ope_hora) {
        this.last_login_ope_hora = last_login_ope_hora;
    }

    public String getAmbito() {
        return ambito;
    }

    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNmAmbito() {
        return nmAmbito;
    }

    public void setNmAmbito(String nmAmbito) {
        this.nmAmbito = nmAmbito;
    }
}

package ve.com.vbtonline.servicio.od;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Mbernot
 * Date: 14/10/13
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityCardOd {

    //MAESTRO
    private String serial;
    private String serialPantalla;
    private String usuarioInc;
    private String fechaInc;
    private String ipInc;
    private String vencimiento;
    private String usuarioIna;
    private String fechaIna;
    private String ipIna;
    private String status;

    //ASIGNACION
    private String login;
    private String codcar;
    private String usuarioAsi;
    private Date fechaAsi;
    private String ipAsi;

    //DETALLE
    private ArrayList filas;
    private ArrayList columnas;
    private HashMap valores;

    //OTROS
    private String resultado;
    private String coordenadas;
    private String totalFilas;
    private String totaColumnas;

    public String getTotalFilas() {
        return totalFilas;
    }

    public void setTotalFilas(String totalFilas) {
        this.totalFilas = totalFilas;
    }

    public String getTotaColumnas() {
        return totaColumnas;
    }

    public void setTotaColumnas(String totaColumnas) {
        this.totaColumnas = totaColumnas;
    }

    public String getCoordenadas() {

        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public SecurityCardOd(){
        //MAESTRO
        serial = "";
        serialPantalla = "";
        usuarioInc = "";
        fechaInc = null;
        ipInc = "";
        vencimiento = "";
        usuarioIna = null;
        fechaIna = null;
        ipIna = "";

        //ASIGNACION
        setLogin("");
        codcar = "";
        usuarioAsi = "";
        fechaAsi = null;
        ipAsi = "";

        //DETALLE
        filas = new ArrayList();
        columnas = new ArrayList();
        valores = new HashMap();

        //OTROS
        resultado = "";

    }
    public String getIpAsi() {
        return ipAsi;
    }

    public void setIpAsi(String ipAsi) {
        this.ipAsi = ipAsi;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUsuarioInc() {
        return usuarioInc;
    }

    public void setUsuarioInc(String usuarioInc) {
        this.usuarioInc = usuarioInc;
    }

    public String getFechaInc() {
        return fechaInc;
    }

    public void setFechaInc(String fechaInc) {
        this.fechaInc = fechaInc;
    }

    public String getIpInc() {
        return ipInc;
    }

    public void setIpInc(String ipInc) {
        this.ipInc = ipInc;
    }

    public String getVencimiento() {
        return vencimiento;
    }

    public void setVencimiento(String vencimiento) {
        this.vencimiento = vencimiento;
    }

    public String getUsuarioIna() {
        return usuarioIna;
    }

    public void setUsuarioIna(String usuarioIna) {
        this.usuarioIna = usuarioIna;
    }

    public String getFechaIna() {
        return fechaIna;
    }

    public void setFechaIna(String fechaIna) {
        this.fechaIna = fechaIna;
    }

    public String getIpIna() {
        return ipIna;
    }

    public void setIpIna(String ipIna) {
        this.ipIna = ipIna;
    }




    public String getCodcar() {
        return codcar;
    }

    public void setCodcar(String codcar) {
        this.codcar = codcar;
    }

    public String getUsuarioAsi() {
        return usuarioAsi;
    }

    public void setUsuarioAsi(String usuarioAsi) {
        this.usuarioAsi = usuarioAsi;
    }

    public Date getFechaAsi() {
        return fechaAsi;
    }

    public void setFechaAsi(Date fechaAsi) {
        this.fechaAsi = fechaAsi;
    }

    public ArrayList getFilas() {
        return filas;
    }

    public void setFilas(ArrayList filas) {
        this.filas = filas;
    }

    public ArrayList getColumnas() {
        return columnas;
    }

    public void setColumnas(ArrayList columnas) {
        this.columnas = columnas;
    }

    public HashMap getValores() {
        return valores;
    }

    public void setValores(HashMap valores) {
        this.valores = valores;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    /**
     * @param columna La columna a agregar
     */
    public void addColumna(String columna) {
        if (!this.columnas.contains((String)columna))
            this.columnas.add(columna);
    }

    /**
     * @param fila La fila a agregar
     */
    public void addFila(String fila) {
        if (!this.filas.contains((String)fila))
            this.filas.add(fila);
    }

    /**
     * @param fila La fila donde se va a agregar el valor
     * @param columna La columna donde se va a agregar el valor
     * @param valor El valor a agregar
     */
    public void addValor(String fila, String columna, String valor) {
        this.valores.put(fila + columna,valor);
    }

    /**
     * @param fila La fila a buscar
     * @param columna La columna a buscar
     * @return El valor de la posición fila X columna
     */
    public String getValor(String fila, String columna) {
        return (String)this.valores.get(fila + columna);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSerialPantalla() {
        return serialPantalla;
    }

    public void setSerialPantalla(String serialPantalla) {
        this.serialPantalla = serialPantalla;
    }
}

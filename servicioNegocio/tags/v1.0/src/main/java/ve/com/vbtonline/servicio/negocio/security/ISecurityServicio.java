package ve.com.vbtonline.servicio.negocio.security;

import ve.com.vbtonline.servicio.od.AccountsOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;
import ve.com.vbtonline.servicio.od.SecurityCardOd;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 02:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ISecurityServicio {
    public AccountsOd cargar(String transaccionId, AccountsOd aod) throws Exception;
    public String crearClaveRamdom (VBTUsersOd usuario, String idioma) throws Exception;
    public String crearClaveRamdomSMS (VBTUsersOd usuario, String idioma) throws Exception;
    public String crearClaveRamdom2 () throws Exception;
    public String cambiarClaveOpeEsp (String clave, String login) throws Exception;
    public String cambiarClaveOpeEspPersonal (String clave, String login) throws Exception;
    public String verificarSiClaveProvisonalOpeEsp (String login) throws Exception;
    public String bloquearUsuario (String login) throws Exception;
    public String verificarClaveOpeEsp (String login, String clave) throws Exception;
    public SecurityCardOd cargarCoordenadas (String login, String ip) throws Exception;
    public String validarCoordenadas (String login, String ip, String fila, String columna, String valor,String fila2, String columna2, String valor2) throws Exception;
    public String cargarTarjeta (String login) throws Exception;
    public String salvarNuevoPassword (String clave, String login) throws Exception;
    public List<String> obtenerEmailContrato (String login) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception;
    public String correoClientePrincipal(VBTUsersOd usuario, String idioma, String text) throws Exception;
    public List<String> carterasUsuario (String login) throws Exception;
    public String emailEjecutivos (String carteras) throws Exception;
}

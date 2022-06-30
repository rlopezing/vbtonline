package ve.com.vbtonline.servicio.negocio.desingBank;

import ve.com.vbtonline.servicio.od.*;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 02:00 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IDesingBankServicio {
    public String cambiarClave (String clave, String login, VBTUsersOd usuarioSesion) throws Exception;
    public String verificarClave (String clave, String login) throws Exception;
    public ParametrosPersonalesOd cargarParametrosPersonales (List<String> parametros) throws Exception;
    public ParametrosPersonalesOd cargarParametrosPersonalesBase (String tipo) throws Exception;
    public String guardarParametrosPersonales (ParametrosPersonalesOd parametrosPersonalesOd) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception;
    public String correoClientePrincipal(VBTUsersOd usuario,String idioma,  ParametrosPersonalesOd parametrosPersonales) throws Exception;
    public BufferedImage cargarImagenHome () throws Exception;
    public BufferedImage cargarImagenID (String id) throws Exception;
}

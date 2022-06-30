package ve.com.vbtonline.servicio.negocio.myInformation;

import ve.com.vbtonline.servicio.od.MyInformationOd;
import ve.com.vbtonline.servicio.od.TableOd;
import ve.com.vbtonline.servicio.od.TableOdDetallesOd;
import ve.com.vbtonline.servicio.od.VBTUsersOd;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IMyInformationServicio {
    public MyInformationOd cargar(String transaccionId, MyInformationOd miod, VBTUsersOd usuario) throws Exception;
    public TableOd consultarTelefonos (List<String> parametros , String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd consultarDirecciones (List<String> parametros , String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd consultarDocumentos (List<String> parametros , String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd consultarRepresentantes (List<String> parametros , String idioma, VBTUsersOd usuario) throws Exception;
    public TableOd consultarContactos (List<String> parametros , String idioma, VBTUsersOd usuario) throws Exception;
    public TableOdDetallesOd consultarCarteras (List<String> parametros , String idioma, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
}

package ve.com.vbtonline.servicio.negocio.transfers;

import ve.com.vbtonline.servicio.od.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 01:52 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITransfersServicio {
    public TransfersOd cargar(String transaccionId, TransfersOd tod) throws Exception;
    public TableOd consultarTransferencias (String carteras, String numContrato, String Status, String idioma) throws Exception;
    public List<String> consultTransfers_detalle_fc (String carteras, String numContrato, String numref) throws Exception;
    public TableOd consultarDirectorio (VBTUsersOd login, String idioma) throws Exception;
    public TableOd consultarDirectorioTransfer (VBTUsersOd login, String idioma) throws Exception;
    public String salvarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception;
    public String borrarDirectorio (String login, String codigo) throws Exception;
    public String buscarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception;
    public String buscarTemplateEditar (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, Integer codTemplate ) throws Exception;
    public String editarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String codTemplate) throws Exception;
    public SummaryTransfersToOtherBanksOd cargarDetalleDirectorio (String login, String codigoTemplate) throws Exception;
    public TableOd consultarTransferenciasGeneral (List<String> parametros, String idioma) throws Exception;
    public String cambiarEstatusTransferencia (String Status, String numReferencia, VBTUsersOd usuario, String Idioma) throws Exception;
    public String cambiarEstatusTemplate(String id, String usuario, String Idioma)throws Exception;
    public SelectOd cargarCuentasTransOtrosBan (String carteras) throws Exception;
    public SelectOd cargarPaises () throws Exception;
    public SelectOd cargarPaisesBeneficiario () throws Exception;
    public boolean aceptarCondicionesTransfe (String contrato, String usuario, String direccionip, String fecha, String acepto) throws Exception;
    public TemplateOd cargarListaTemplates(String login) throws Exception;
    public CuentasOd cargarCuentas (String carteras, String idioma) throws Exception;
    public SelectOd cargarCodBankBen () throws Exception;
    public Integer validarBancoBeneficiario_Intermediario (String codigoBancoB, String tipoBancoB, String codigoPaisB, String codigoBancoI, String tipoBancoI, String codigoPaisI) throws Exception;
    public String validarCuentaDestino_TOC (String cuenta) throws Exception;
    public String saveToOtherClient (SummaryTransfersToOtherBanksOd datos,  VBTUsersOd usuario, String idioma) throws Exception;
    public String saveBetweenMyAccounts (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception;
    public List<String> saveToOtherBank (SummaryTransfersToOtherBanksOd datos,  VBTUsersOd usuario, String idioma) throws Exception;
    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato) throws Exception;
    public List<String> obtenerMontosLiberadorFC (String contrato) throws Exception;
    public List<String> saveToOtherBank_JS (SummaryTransfersToOtherBanksOd datos,  VBTUsersOd usuario, String idioma) throws Exception;
    public String saveToOtherBankJS_BOFA (String numRef) throws Exception;
    public String saveToOtherBankBOFA_temporal (SummaryTransfersToOtherBanksOd datos, String numRef) throws Exception;
    public String EmailsInternos (String cuenta) throws Exception;
    public TableOd  buscarBanco (List<String> parametros, String idioma) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7, String parametro8) throws Exception;
    public String numeroReferencia ( String referencia) throws Exception;
}

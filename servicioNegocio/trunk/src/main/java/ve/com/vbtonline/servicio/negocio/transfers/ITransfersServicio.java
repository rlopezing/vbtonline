package ve.com.vbtonline.servicio.negocio.transfers;

import ve.com.vbtonline.servicio.od.*;

import java.util.ArrayList;
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
    public TableOd consultarTransferencias (String carteras, String numContrato, String Status, String idioma, VBTUsersOd usuario) throws Exception;
    public List<String> consultTransfers_detalle_fc (String carteras, String numContrato, String numref, VBTUsersOd usuario) throws Exception;
    public TableOd consultarDirectorio (VBTUsersOd login, String idioma) throws Exception;
    public TableOd consultarDirectorioTransfer (VBTUsersOd login, String idioma) throws Exception;
    public String salvarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception;
    public String borrarDirectorio (String login, String codigo, VBTUsersOd usuario) throws Exception;
    public String buscarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception;
    public String buscarTemplateEditar (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, Integer codTemplate ) throws Exception;
    public String editarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String codTemplate) throws Exception;
    public SummaryTransfersToOtherBanksOd cargarDetalleDirectorio (String login, String codigoTemplate, VBTUsersOd usuario) throws Exception;
    public TableOd consultarTransferenciasGeneral (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public String cambiarEstatusTransferencia (String Status, String numReferencia, VBTUsersOd usuario, String Idioma) throws Exception;
    public String cambiarEstatusTemplate(String id, String usuario, String Idioma, VBTUsersOd usuarioSesion)throws Exception;
    public SelectOd cargarCuentasTransOtrosBan (String carteras, VBTUsersOd usuario) throws Exception;
    public SelectOd cargarPaises (VBTUsersOd usuario) throws Exception;
    public SelectOd cargarPaisesBeneficiario (VBTUsersOd usuario) throws Exception;
    public SelectOd cargarMotivos (VBTUsersOd usuario, String Idioma) throws Exception;
    public boolean aceptarCondicionesTransfe (String contrato, String usuario, String direccionip, String fecha, String acepto, VBTUsersOd usuarioSesion) throws Exception;
    public TemplateOd cargarListaTemplates(VBTUsersOd login) throws Exception;
    public CuentasOd cargarCuentas (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public CuentasOd cargarCuentasDestino (String carteras, String idioma, VBTUsersOd usuario, String codempDest) throws Exception;
    public CuentasOd cargarCuentasTOB (String carteras, String idioma, VBTUsersOd usuario) throws Exception;
    public List<ContentSelectOd> cargarCuentasFM (String carteras, String idioma, VBTUsersOd usuario, String moneda, String codempDest) throws Exception;
    public SelectOd cargarCodBankBen (VBTUsersOd usuario) throws Exception;
    public Integer validarBancoBeneficiario_Intermediario (String codigoBancoB, String tipoBancoB, String codigoPaisB, String codigoBancoI, String tipoBancoI, String codigoPaisI, VBTUsersOd usuario) throws Exception;
    public String validarCuentaDestino_TOC (String cuenta, String cartera, VBTUsersOd usuario) throws Exception;
    public String validarFondoMutual (String setCodEmpTOC,String cartera, VBTUsersOd usuario, String codTipoEmp) throws Exception;
    public String saveToOtherClient (SummaryTransfersToOtherBanksOd datos,  VBTUsersOd usuario, String idioma, VBTUsersOd usuarioSesion) throws Exception;
    public String saveBetweenMyAccounts (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception;
    public List<String> saveToOtherBank (SummaryTransfersToOtherBanksOd datos,  VBTUsersOd usuario, String idioma) throws Exception;
    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato, VBTUsersOd usuario, String codcartera, String codcuenta, String conEmpresa) throws Exception;
    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato, VBTUsersOd usuario) throws Exception;
    public List<String> obtenerMontosLiberadorFC (String contrato, VBTUsersOd usuario) throws Exception;
    public List<String> saveToOtherBank_JS (SummaryTransfersToOtherBanksOd datos,  VBTUsersOd usuario, String idioma) throws Exception;
    public String saveToOtherBankJS_BOFA (String numRef, VBTUsersOd usuario) throws Exception;
    public String saveToOtherBankBOFA_temporal (SummaryTransfersToOtherBanksOd datos, String numRef, VBTUsersOd usuario) throws Exception;
    public String EmailsInternos (String cuenta, VBTUsersOd usuario) throws Exception;
    public TableOd  buscarBanco (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception;
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception;
    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7, String parametro8) throws Exception;
    public String numeroReferencia ( String referencia, VBTUsersOd usuario) throws Exception;
    public String buscarIdTemplate (VBTUsersOd usuario, String template) throws Exception;
    public String validacionBancoBeneficiario (List<String> parametros, VBTUsersOd usuario) throws Exception;
    public SelectOd consultarElementos (String tipo) throws Exception;
    public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6);
    public String validarDireccionOriginador (String carteraDebito, VBTUsersOd usuario) throws Exception;
    public CuentasOd cargarCuentasDebitoTdc (String nrocta, String idioma, VBTUsersOd usuario) throws Exception;
    public String validarCarteraDestino_TOC (String cuenta, String cartera, VBTUsersOd usuario, String tipoProductoCodigo) throws Exception;
    public SelectOd cargarNacionalidadBI (VBTUsersOd usuario, String Idioma) throws Exception;
    public SelectOd cargarPaisesBI (VBTUsersOd usuario, String Idioma) throws Exception;
    public boolean compararArchivos(VBTUsersOd usuario, ArrayList<String> cantidadArchivos, String archivo) throws Exception;
    public String newFileName(VBTUsersOd usuario, ArrayList<String> cantidadArchivos, String archivo) throws Exception;
    public String verfMismoDia(String idioma, VBTUsersOd usuario) throws Exception;
    public List<String> validarParametrosPersonalesFM (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato, VBTUsersOd usuario, String codcartera, String codEmp, String unidades, String esFM) throws Exception;
    public String comisionTransferenciasCancelacion (String codemp,String tipoMov, VBTUsersOd usuario, String monto, String cuenta, String bankType, String tiempoTransfe) throws Exception;


}

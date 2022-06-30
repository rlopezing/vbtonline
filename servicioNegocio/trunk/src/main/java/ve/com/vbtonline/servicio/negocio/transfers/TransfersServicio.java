package ve.com.vbtonline.servicio.negocio.transfers;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.TransfersIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.negocio.creditCards.CreditCardsServicio;
import ve.com.vbtonline.servicio.od.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransfersServicio extends BasicService implements ITransfersServicio,Serializable {
    private static final Logger logger = Logger.getLogger(TransfersServicio.class);

    /** El Data Access Object */
    private TransfersIo transfersIo;

    /** Constructor de la clase */
    public TransfersServicio(){
    }

    public TransfersOd cargar (String transaccionId,  TransfersOd tod) throws Exception {
        final String origen = "TransfersServicio.cargar";

        long time;
        TransfersOd transfersOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            transfersOd=new TransfersOd();
            transfersOd = getTransfersIo().Cargar("",tod);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (transfersOd);
    }

    public TableOd consultarTransferencias (String carteras, String numContrato, String Status, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.consultarTransferencias";

        long time;

        TableOd listaTransferencias = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaTransferencias = getTransfersIo().consultTransfers(carteras, numContrato, Status, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaTransferencias);
    }

    public  List<String> consultTransfers_detalle_fc (String carteras, String numContrato, String numref, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.consultarTransferencias";

        long time;

        List<String> listaTransferencias = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaTransferencias = getTransfersIo().consultTransfers_detalle_fc(carteras, numContrato, numref, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaTransferencias);
    }

    public TableOd consultarDirectorio (VBTUsersOd login, String idioma) throws Exception {
        final String origen = "TransfersServicio.consultarDirectorio";

        long time;

        TableOd listaDirectorio = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDirectorio = getTransfersIo().consultarDirectorio(login, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDirectorio);
    }

    public TableOd consultarDirectorioTransfer (VBTUsersOd login, String idioma) throws Exception {
        final String origen = "TransfersServicio.consultarDirectorioTransfer";

        long time;

        TableOd listaDirectorio = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDirectorio = getTransfersIo().consultarDirectorioTransfer(login, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDirectorio);
    }

    public SummaryTransfersToOtherBanksOd cargarDetalleDirectorio (String login, String codigoTemplate, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarDetalleDirectorio";

        long time;

        SummaryTransfersToOtherBanksOd datos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            datos = getTransfersIo().cargarDetalleDirectorio(login,codigoTemplate, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (datos);
    }

    public TableOd consultarTransferenciasGeneral (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.consultarTransferenciasGeneral";

        long time;

        TableOd listaTransferencias = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaTransferencias = getTransfersIo().consultarTransferenciasGeneral(parametros,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaTransferencias);
    }

    public String cambiarEstatusTransferencia (String Status, String numReferencia, VBTUsersOd usuario,String Idioma) throws Exception {
        final String origen = "TransfersServicio.cambiarEstatusTransferencia";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getTransfersIo().cambiarEstatusTransferencia(Status, numReferencia, usuario, Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String cambiarEstatusTemplate (String id, String usuario,String Idioma, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersServicio.cambiarEstatusTemplate";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getTransfersIo().cambiarEstatusTemplate(id, usuario, Idioma, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public SelectOd cargarCuentasTransOtrosBan (String carteras, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCuentasTransOtrosBan";

        long time;
        SelectOd cuentas = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cuentas= getTransfersIo().cargarCuentasTransOtrosBan(carteras, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public boolean aceptarCondicionesTransfe (String contrato, String usuario, String direccionip, String fecha, String acepto, VBTUsersOd usuarioSesion) throws Exception{
        final String origen = "TransfersServicio.cargarPaises";
        boolean result;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            result=getTransfersIo().aceptarCondicionesTransfe(contrato, usuario, direccionip, fecha, acepto, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (result);
    }

    public SelectOd cargarPaises (VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarPaises";

        long time;
        SelectOd Paises = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Paises= getTransfersIo().cargarPaises(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Paises);
    }

    public SelectOd cargarPaisesBeneficiario (VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarPaisesBeneficiario";

        long time;
        SelectOd Paises = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Paises= getTransfersIo().cargarPaisesBeneficiario(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Paises);
    }

    public SelectOd cargarMotivos (VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "TransfersServicio.cargarMotivos";

        long time;
        SelectOd Motivos = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Motivos= getTransfersIo().cargarMotivos(usuario,Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Motivos);
    }

    public TemplateOd cargarListaTemplates(VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarListaTemplates";

        long time;
        TemplateOd templates = new TemplateOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            templates= getTransfersIo().cargarListaTemplates(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (templates);
    }

    public CuentasOd cargarCuentas (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCuentas";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cuentas= getTransfersIo().cargarCuentas(Carteras,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public CuentasOd cargarCuentasDestino (String carteras, String idioma, VBTUsersOd usuario, String codempDest) throws Exception {
        final String origen = "TransfersServicio.cargarCuentas";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cuentas= getTransfersIo().cargarCuentasDestino(carteras, idioma, usuario, codempDest);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }


    public SelectOd cargarCodBankBen (VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCodBankBen";

        long time;
        SelectOd codBankBen = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            codBankBen= getTransfersIo().cargarCodBankBen(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (codBankBen);
    }

    public Integer validarBancoBeneficiario_Intermediario (String codigoBancoB, String tipoBancoB, String codigoPaisB, String tipoBancoI, String codigoBancoI,  String codigoPaisI, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.validarBancoBeneficiario_Intermediario";

        long time;
        Integer existe=0;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            existe= getTransfersIo().validarBancoBeneficiario_Intermediario(codigoBancoB, tipoBancoB, codigoPaisB, tipoBancoI, codigoBancoI , codigoPaisI, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (existe);
    }

    public String validarCuentaDestino_TOC (String cuenta, String cartera, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.validarCuentaDestino_TOC";

        long time;
        String moneda = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            moneda= getTransfersIo().validarCuentaDestino_TOC(cuenta, cartera, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (moneda);
    }

    public String validarFondoMutual (String setCodEmpTOC,String cartera, VBTUsersOd usuario, String codTipoEmp) throws Exception {
        final String origen = "TransfersServicio.validarFondoMutual";

        long time;
        String moneda = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            moneda= getTransfersIo().validarFondoMutual(setCodEmpTOC,cartera, usuario,codTipoEmp);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (moneda);
    }

    public List<String> saveToOtherBank (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersServicio.saveToOtherBank";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherBank(datos,usuario,idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String saveBetweenMyAccounts (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersServicio.saveBetweenMyAccounts";

        String esFM = new String();
        String tipoProducto = new String();
        String tipoCuenta = new String();
        String verfUnidad = new String();
        String verfDesdeCuenta = new String();

        esFM = datos.getCheckFM();                      /** Si es S va hacia un fondo mutual  **/
        tipoProducto = datos.getProductoTipo();         /** Trae el nombre del tipo de producto hacia donde se hace  **/
        tipoCuenta = datos.getCuentaTipo();             /** Trae el nombre del tipo de producto desde donde se hace  **/
        verfUnidad = datos.getVerfUnits();              /** si es S son unidades  **/
        verfDesdeCuenta = datos.getVerfDesdeCuenta();   /** Si es N es desde un Saving  **/

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            /** VERIFICA SI LA CUENTA VIENE DE UN SAVING O DE UN FONDO MUTUAL **/
            if (!verfDesdeCuenta.equalsIgnoreCase("S")){
                if (!esFM.equalsIgnoreCase("N")){
                    respuesta= getTransfersIo().saveBetweenMyAccountsToFM(datos,usuario,idioma);
                }else{
                    respuesta= getTransfersIo().saveBetweenMyAccountsToSA(datos,usuario,idioma);
                }
            }else{
                if (!esFM.equalsIgnoreCase("N")){
                    respuesta= getTransfersIo().saveBetweenMyAccountsFMtoFM(datos,usuario,idioma);
                }else{
                    respuesta= getTransfersIo().saveBetweenMyAccountsFMToSA(datos,usuario,idioma);
                }
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String buscarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.buscarTemplate";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().buscarTemplate(datos,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String borrarDirectorio (String login, String codigo, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.borrarDirectorio";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().borrarDirectorio(login,codigo, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String buscarTemplateEditar (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, Integer codTemplate) throws Exception {
        final String origen = "TransfersServicio.buscarTemplateEditar";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().buscarTemplateEditar(datos,usuario,codTemplate);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String salvarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.salvarTemplate";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().salvarTemplate(datos,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String editarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String codTemplate) throws Exception {
        final String origen = "TransfersServicio.editarTemplate";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().editarTemplate(datos,usuario, codTemplate);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String saveToOtherClient (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersServicio.saveToOtherClient";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherClient(datos,usuario,idioma, usuarioSesion);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<String> saveToOtherBank_JS (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersServicio.saveToOtherBank_JS";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherBank_JS(datos,usuario,idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String saveToOtherBankBOFA_temporal (SummaryTransfersToOtherBanksOd datos, String numRef, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.saveToOtherBankBOFA_temporal";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherBankJS_BOFA_Temporal(datos, numRef, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String saveToOtherBankJS_BOFA (String numRef, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.saveToOtherBankJS_BOFA";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherBankJS_BOFA(numRef, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String EmailsInternos (String cuenta, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.EmailsInternos";

        long time;
        String correos = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            correos= getTransfersIo().EmailsInternos(cuenta, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (correos);
    }

    public TableOd  buscarBanco (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.buscarBanco";

        long time;

        TableOd listaDirectorio = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDirectorio = getTransfersIo().buscarBanco(parametros,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDirectorio);
    }

    public String guardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception {
        final String origen = "TransfersServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getTransfersIo().GuardarLogFC(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7,parametro8);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public TransfersIo getTransfersIo() {
        return transfersIo;
    }

    public void setTransfersIo(TransfersIo transfersIo) {
        this.transfersIo = transfersIo;
    }

    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato,VBTUsersOd usuario, String codcartera, String codcuenta, String codEmpresa) throws Exception {
        final String origen = "TransfersServicio.validarParametrosPersonales";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().validarParametrosPersonales(codpercli,monto,tipoTransf, contrato, tipoContrato, usuario, codcartera, codcuenta, codEmpresa);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato, VBTUsersOd usuario) throws Exception {

        return validarParametrosPersonales(codpercli, monto, tipoTransf, contrato, tipoContrato, usuario, "NO", "NO", "NO");

    }

    public List<String> obtenerMontosLiberadorFC ( String contrato, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.obtenerMontosLiberadorFC";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().obtenerMontosLiberadorFC(contrato, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String numeroReferencia ( String referencia, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.obtenerMontosLiberadorFC";

        long time;
        Integer exitoso=0;
        String respuesta="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().numeroReferencia(referencia, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String buscarIdTemplate (VBTUsersOd usuario, String template) throws Exception{
        final String origen = "TransfersServicio.buscarIdTemplate";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().buscarIdTemplate(usuario,template);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String validacionBancoBeneficiario (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.validacionBancoBeneficiario";
        long time;
        Integer exitoso=0;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getTransfersIo().validacionBancoBeneficiario(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public SelectOd consultarElementos (String tipo) throws Exception {
        final String origen = "TransfersServicio.consultarElementos";

        long time;

        SelectOd listaElementos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaElementos = getTransfersIo().cargarElementosTipos(tipo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaElementos);
    }

    public  String validarDireccionOriginador (String carteraDebito, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.validarDireccionOriginador";
        long time;
        Integer exitoso=0;
        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta= getTransfersIo().validarDireccionOriginador(carteraDebito, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public CuentasOd cargarCuentasDebitoTdc (String nrocta, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCuentasDebitoTdc";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cuentas= getTransfersIo().cargarCuentasDebitoTdc(nrocta,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public String validarCarteraDestino_TOC (String cuenta, String cartera, VBTUsersOd usuario, String tipoProductoCodigo) throws Exception {
        final String origen = "TransfersServicio.validarCuentaDestino_TOC";

        long time;
        String cartera_ = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cartera_= getTransfersIo().validarCarteraDestino_TOC(cuenta, cartera, usuario, tipoProductoCodigo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cartera_);
    }

    public SelectOd cargarNacionalidadBI (VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "TransfersServicio.cargarNacionalidadBI";

        long time;
        SelectOd nacionalidadBI = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            nacionalidadBI = getTransfersIo().cargarNacionalidadBI(usuario,Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (nacionalidadBI);
    }

    public SelectOd cargarPaisesBI (VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "TransfersServicio.cargarPaisesBI";

        long time;
        SelectOd paisesBI = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            paisesBI = getTransfersIo().cargarPaisesBI(usuario,Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (paisesBI);
    }

    public boolean compararArchivos(VBTUsersOd usuario, ArrayList<String> cantidadArchivos, String archivo) throws Exception {
        final String origen = "TransfersServicio.compararArchivos";

        long time;
        boolean comparacion;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            comparacion = getTransfersIo().compararArchivos(usuario,cantidadArchivos,archivo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (comparacion);
    }

    public String newFileName(VBTUsersOd usuario, ArrayList<String> cantidadArchivos, String archivo) throws Exception {
        final String origen = "TransfersServicio.compararArchivos";

        long time;
        String newFileName;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            newFileName = getTransfersIo().newFileName(usuario,cantidadArchivos,archivo);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (newFileName);
    }

    public List<ContentSelectOd> cargarCuentasFM (String Carteras, String idioma, VBTUsersOd usuario, String moneda, String codempDest) throws Exception {
        final String origen = "TransfersServicio.cargarCuentasFM";

        long time;
        List<ContentSelectOd> cuentas = new ArrayList<ContentSelectOd>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            //cuentas= getTransfersIo().cargarCuentasFM(Carteras,idioma, usuario);
            cuentas= getTransfersIo().cargarProductosTransTOC(Carteras,idioma, usuario, moneda, codempDest);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public String verfMismoDia(String idioma, VBTUsersOd usuario) throws Exception{
        final String origen = "TransfersServicio.compararArchivos";

        long time;
        String mismoDia;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            mismoDia = getTransfersIo().mismoDiaVerf(idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (mismoDia);
    }

    public List<String> validarParametrosPersonalesFM (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato, VBTUsersOd usuario, String codcartera, String codEmp, String unidades, String esFM) throws Exception{
        final String origen = "TransfersServicio.validarParametrosPersonalesFM";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().validarParametrosPersonalesFM(codpercli,monto,tipoTransf, contrato, tipoContrato, usuario, codcartera, codEmp, unidades, esFM);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public CuentasOd cargarCuentasTOB (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersServicio.cargarCuentas";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            cuentas= getTransfersIo().cargarCuentasTOB(Carteras,idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public String comisionTransferenciasCancelacion (String codemp,String tipoMov, VBTUsersOd usuario, String monto, String cuenta, String bankType, String tiempoTransfe)  throws Exception {
        final String origen = "TransfersServicio.comisionTransferenciasCancelacion";
        String comisionCalculada;
        long time;
        List<String> resumenTD = new ArrayList<String>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            comisionCalculada= getTransfersIo().comisionTransferenciasCancelacion(codemp, tipoMov, usuario, monto, cuenta, bankType, tiempoTransfe);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (comisionCalculada);
    }
}

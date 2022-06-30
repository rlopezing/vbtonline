package ve.com.vbtonline.servicio.negocio.transfers;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.TransfersIo;
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
public class TransfersServicio implements ITransfersServicio,Serializable {
    private static final Logger logger = Logger.getLogger(TransfersServicio.class);

    /** El Data Access Object
     */
    private TransfersIo transfersIo;




    /** Constructor de la clase
     */
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

    public TableOd consultarTransferencias (String carteras, String numContrato, String Status, String idioma) throws Exception {
        final String origen = "TransfersServicio.consultarTransferencias";

        long time;

        TableOd listaTransferencias = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaTransferencias = getTransfersIo().consultTransfers(carteras, numContrato, Status, idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaTransferencias);
    }


    public  List<String> consultTransfers_detalle_fc (String carteras, String numContrato, String numref) throws Exception {
        final String origen = "TransfersServicio.consultarTransferencias";

        long time;

        List<String> listaTransferencias = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaTransferencias = getTransfersIo().consultTransfers_detalle_fc(carteras, numContrato, numref);

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



    public SummaryTransfersToOtherBanksOd cargarDetalleDirectorio (String login, String codigoTemplate) throws Exception {
        final String origen = "TransfersServicio.cargarDetalleDirectorio";

        long time;

        SummaryTransfersToOtherBanksOd datos = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            datos = getTransfersIo().cargarDetalleDirectorio(login,codigoTemplate);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (datos);
    }

    public TableOd consultarTransferenciasGeneral (List<String> parametros, String idioma) throws Exception {
        final String origen = "TransfersServicio.consultarTransferenciasGeneral";

        long time;

        TableOd listaTransferencias = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaTransferencias = getTransfersIo().consultarTransferenciasGeneral(parametros,idioma);

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

    public String cambiarEstatusTemplate (String id, String usuario,String Idioma) throws Exception {
        final String origen = "TransfersServicio.cambiarEstatusTemplate";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getTransfersIo().cambiarEstatusTemplate(id, usuario, Idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public SelectOd cargarCuentasTransOtrosBan (String carteras) throws Exception {
        final String origen = "TransfersServicio.cargarCuentasTransOtrosBan";

        long time;
        SelectOd cuentas = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cuentas= getTransfersIo().cargarCuentasTransOtrosBan(carteras);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public boolean aceptarCondicionesTransfe (String contrato, String usuario, String direccionip, String fecha, String acepto) throws Exception{
        final String origen = "TransfersServicio.cargarPaises";
        boolean result;
        long time;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            result=getTransfersIo().aceptarCondicionesTransfe(contrato, usuario, direccionip, fecha, acepto);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (result);
    }

    public SelectOd cargarPaises () throws Exception {
        final String origen = "TransfersServicio.cargarPaises";

        long time;
        SelectOd Paises = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Paises= getTransfersIo().cargarPaises();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Paises);
    }

    public SelectOd cargarPaisesBeneficiario () throws Exception {
        final String origen = "TransfersServicio.cargarPaisesBeneficiario";

        long time;
        SelectOd Paises = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            Paises= getTransfersIo().cargarPaisesBeneficiario();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (Paises);
    }


    public TemplateOd cargarListaTemplates(String login) throws Exception {
        final String origen = "TransfersServicio.cargarListaTemplates";

        long time;
        TemplateOd templates = new TemplateOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            templates= getTransfersIo().cargarListaTemplates(login);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (templates);
    }

    public CuentasOd cargarCuentas (String Carteras, String idioma) throws Exception {
        final String origen = "TransfersServicio.cargarCuentas";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cuentas= getTransfersIo().cargarCuentas(Carteras,idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public SelectOd cargarCodBankBen () throws Exception {
        final String origen = "TransfersServicio.cargarCodBankBen";

        long time;
        SelectOd codBankBen = new SelectOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            codBankBen= getTransfersIo().cargarCodBankBen();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (codBankBen);
    }

    public Integer validarBancoBeneficiario_Intermediario (String codigoBancoB, String tipoBancoB, String codigoPaisB, String tipoBancoI, String codigoBancoI,  String codigoPaisI) throws Exception {
        final String origen = "TransfersServicio.validarBancoBeneficiario_Intermediario";

        long time;
        Integer existe=0;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            existe= getTransfersIo().validarBancoBeneficiario_Intermediario(codigoBancoB, tipoBancoB, codigoPaisB, tipoBancoI, codigoBancoI , codigoPaisI);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (existe);
    }

    public String validarCuentaDestino_TOC (String cuenta) throws Exception {
        final String origen = "TransfersServicio.validarCuentaDestino_TOC";

        long time;
        String moneda = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            moneda= getTransfersIo().validarCuentaDestino_TOC(cuenta);

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

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveBetweenMyAccounts(datos,usuario,idioma);

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

    public String borrarDirectorio (String login, String codigo) throws Exception {
        final String origen = "TransfersServicio.borrarDirectorio";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().borrarDirectorio(login,codigo);

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

    public String saveToOtherClient (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersServicio.saveToOtherClient";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherClient(datos,usuario,idioma);

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

    public String saveToOtherBankBOFA_temporal (SummaryTransfersToOtherBanksOd datos, String numRef) throws Exception {
        final String origen = "TransfersServicio.saveToOtherBankBOFA_temporal";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherBankJS_BOFA_Temporal(datos, numRef);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String saveToOtherBankJS_BOFA (String numRef) throws Exception {
        final String origen = "TransfersServicio.saveToOtherBankJS_BOFA";

        long time;
        Integer exitoso=0;
        String respuesta=new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().saveToOtherBankJS_BOFA(numRef);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String EmailsInternos (String cuenta) throws Exception {
        final String origen = "TransfersServicio.EmailsInternos";

        long time;
        String correos = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            correos= getTransfersIo().EmailsInternos(cuenta);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (correos);
    }

    public TableOd  buscarBanco (List<String> parametros, String idioma) throws Exception {
        final String origen = "TransfersServicio.buscarBanco";

        long time;

        TableOd listaDirectorio = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaDirectorio = getTransfersIo().buscarBanco(parametros,idioma);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaDirectorio);
    }

    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "TransfersServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getTransfersIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
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

    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato) throws Exception {
        final String origen = "TransfersServicio.validarParametrosPersonales";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().validarParametrosPersonales(codpercli,monto,tipoTransf, contrato, tipoContrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public List<String> obtenerMontosLiberadorFC ( String contrato) throws Exception {
        final String origen = "TransfersServicio.obtenerMontosLiberadorFC";

        long time;
        Integer exitoso=0;
        List<String> respuesta=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().obtenerMontosLiberadorFC(contrato);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }

    public String numeroReferencia ( String referencia) throws Exception {
        final String origen = "TransfersServicio.obtenerMontosLiberadorFC";

        long time;
        Integer exitoso=0;
        String respuesta="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+TransfersServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            respuesta= getTransfersIo().numeroReferencia(referencia);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+TransfersServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }
}

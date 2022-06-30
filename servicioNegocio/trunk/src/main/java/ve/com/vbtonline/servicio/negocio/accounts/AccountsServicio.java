package ve.com.vbtonline.servicio.negocio.accounts;

import org.apache.log4j.Logger;
import ve.com.vbtonline.servicio.io.AccountsIo;
import ve.com.vbtonline.servicio.negocio.BasicService;
import ve.com.vbtonline.servicio.negocio.accounts.IAccountsServicio;
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
public class AccountsServicio extends BasicService implements IAccountsServicio,Serializable {
    private static final Logger logger = Logger.getLogger(AccountsServicio.class);

    /** El Data Access Object
     */
    private AccountsIo accountsIo;




    /** Constructor de la clase
     */
    public AccountsServicio(){
    }

    public AccountsOd cargar (String transaccionId, AccountsOd aod,VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.cargar";

        long time;
        AccountsOd accountsOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            accountsOd=new AccountsOd();
            accountsOd = getAccountsIo().Cargar("",aod, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (accountsOd);
    }

    public TableOd Consulta (String transaccionId, AccountsOd aod, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.Consulta";

        long time;
        TableOd tableOd = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            tableOd = getAccountsIo().Consulta("",aod, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tableOd);
    }

    public CuentasOd cargarCuentasEdoCuenta (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.cargarCuentasEdoCuenta";

        long time;
        CuentasOd cuentas = new CuentasOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            cuentas= getAccountsIo().cargarCuentasEdoCuenta(Carteras, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (cuentas);
    }

    public List<ContentSelectOd> cargarTiposMovimientosBT (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.cargarTiposMovimientosBT";

        long time;
        List<ContentSelectOd> tiposMovimientos = new ArrayList<ContentSelectOd>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            tiposMovimientos= getAccountsIo().cargarTiposMovimientosBT(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (tiposMovimientos);
    }

    public DetalleCuentaEdoCtaOd consultarDetalleCuentasEdoCuenta (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarDetalleCuentasEdoCuenta";

        long time;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            detalle= getAccountsIo().consultarDetalleCuentasEdoCuenta(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (detalle);
    }

    public DetalleCuentaEdoCtaOd consultarDetalleCuentasSaldoTrans (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarDetalleCuentasSaldoTrans";

        long time;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();


            detalle= getAccountsIo().consultarDetalleCuentasSaldosTrans(parametros, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (detalle);
    }

    public TableOd consultarCuentasEdoCuenta (List<String> parametros, String saldo, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarCuentasEdoCuenta";

        long time;

        TableOd listaEdoCuenta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaEdoCuenta = getAccountsIo().consultarCuentasEdoCuenta(parametros, saldo, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaEdoCuenta);
    }

    public TableOd consultarCuentasSaldoTransf (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarCuentasEdoCuenta";

        long time;

        TableOd listaSaldoTransCuenta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaSaldoTransCuenta = getAccountsIo().consultarCuentasSaldoTrans(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaSaldoTransCuenta);
    }

    public List<String> consultarMesAnoMaximo (VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarMesAnoMaximo";

        long time;

        List<String> mesano = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            mesano = getAccountsIo().consultarMesAnoMaximo(usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (mesano);
    }

    public TableOd consultarCuentasBloqueos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.consultarCuentasBloqueos";

        long time;

        TableOd listaCuentaBloqueo = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            listaCuentaBloqueo = getAccountsIo().consultarCuentasBloqueos(parametros, idioma, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (listaCuentaBloqueo);
    }
    /*
    public String guardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "AccountsServicio.guardarLog";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getAccountsIo().GuardarLog(parametro1, parametro2, parametro3, parametro4, parametro5, parametro6, parametro7);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);
    }  */

    public String consultarFechaCierre(String cartera, VBTUsersOd usuario) throws Exception{
        final String origen = "AccountsServicio.consultarFechaCierre";

        long time;

        String respuesta = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getAccountsIo().consultarFechaCierre(cartera, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return respuesta;
    }


    public String guardarComentarioTrans(List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsServicio.guardarComentarioTransf";

        long time;

        String respuesta = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ser")+AccountsServicio.class+" | "+origen);

            time = System.currentTimeMillis ();

            respuesta = getAccountsIo().guardarComentarioTrans(parametros,usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ser")+AccountsServicio.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return respuesta;
    }


    public AccountsIo getAccountsIo() {
        return accountsIo;
    }

    public void setAccountsIo(AccountsIo accountsIo) {
        this.accountsIo = accountsIo;
    }
}

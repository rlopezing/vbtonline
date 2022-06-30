package ve.com.vbtonline.servicio.io;

import ve.com.vbtonline.servicio.util.MailManager365;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.MailManager365;
import ve.com.vbtonline.servicio.util.TransformTable;
import ve.com.vbtonline.servicio.util.validacionUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class CreditCardsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(CreditCardsIo.class);


    private LoggerIo loggerIo;
    /** Constructor de la clase
     */
    public CreditCardsIo() {
    }


    public CreditCardsOd Cargar (String transaccionId, CreditCardsOd ccod,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        CreditCardsOd creditCardsOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            creditCardsOd=new CreditCardsOd();
            creditCardsOd.setId(ccod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (creditCardsOd);
    }

    public SelectOd cargarTarjetasEstadoCuentaTDC (String carteras, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasEstadoCuentaTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_tdcconsulta_tar_pr(?,?,?); end;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();


            while (result.next()){

                valor = new String();
                label  = new String();

                valor = NullFormatter.formatBlank(result.getString(3));
                label  = NullFormatter.formatBlank(result.getString(1)) + " (" + ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";

                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarMotivosCLE (String estatusTarjeta, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.estatusTarjeta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor,extra;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CONSULTAR_PROCESSING_LIST(?,?); END;");

            if(estatusTarjeta== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, "TIPO_BLOQUEO");

            statement.registerOutParameter(2, OracleTypes.CURSOR);

            statement.execute ();

            //respuesta = statement.getString(3);

            /*if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa"); */

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = new String();
                label  = new String();


                if(NullFormatter.formatBlank(result.getString(8)).equals("S")) {
                    valor = NullFormatter.formatBlank(result.getString(1)) + "|" + NullFormatter.formatBlank(result.getString(2));
                    label = (idioma.equalsIgnoreCase("_ve_es") ? NullFormatter.formatBlank(result.getString(5)) : NullFormatter.formatBlank(result.getString(4)));
                    extra = NullFormatter.formatBlank(result.getString(9));

                    cuenta.setLabel(label);
                    cuenta.setValue(valor);
                    cuenta.setExtra1(extra);
                    cuentas.add(cuenta);
                }
                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTarjetasTDCITT (String carteras, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasTDCITT";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_tdmovimi_ctas_pr(?,?,?); end;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();


            while (result.next()){

                valor = new String();
                label  = new String();

                valor = NullFormatter.formatBlank(result.getString(5))+"|"+NullFormatter.formatBlank(result.getString(6));;
                label  = NullFormatter.formatBlank(result.getString(3)) + " (" + ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";

                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTarjetasTDCCL (String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor, bloqueo;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CARGAR_TARJETAS(?,?,?,?,?); END;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.setString(4, "C");

            statement.setString(5, "NO");

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = new String();
                label  = new String();
                bloqueo = new String();

                valor = NullFormatter.formatBlank(result.getString(1));
                label  = NullFormatter.formatBlank(result.getString(3)) + " (" + ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";
                bloqueo  =  NullFormatter.formatBlank(result.getString(4));

                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuenta.setExtra(bloqueo);
                cuentas.add(cuenta);

                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTdcPagos (String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarTdcPagos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CARGAR_TARJETAS(?,?,?,?,?); END;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.setString(4, "P");

            statement.setString(5, "NO");

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = new String();
                label  = new String();

                valor = NullFormatter.formatBlank(result.getString(1));
                label  = NullFormatter.formatBlank(result.getString(3)) + " (" + ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";

                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }
    /*
    public SelectOd cargarTarjetasPrincipales(String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasPrincipales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CARGAR_TARJETAS(?,?,?,?,?); END;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.setString(4, "C");

            statement.setString(5, "NO");

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = new String();
                label  = new String();

                valor = NullFormatter.formatBlank(result.getString(1));
                label  = NullFormatter.formatBlank(result.getString(3)) + " (" + ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";

                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }   */


    public SelectOd cargarMesesEstadoCuentaTDC (String nroCuenta,   VBTUsersOd usuario) throws Exception {        final String origen = "CreditCardsIo.cargarMesesEstadoCuentaTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> meses= new ArrayList<ContentSelectOd>();
        ContentSelectOd mes = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_tdcconsulta_mes_pr(?,?,?); end;");


            if(nroCuenta== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, nroCuenta);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();


            while (result.next()){

                valor = new String();
                label  = new String();

                valor = NullFormatter.formatBlank(result.getString(1));
                label  = NullFormatter.formatBlank(result.getString(1));

                mes.setLabel(label);
                mes.setValue(valor);
                meses.add(mes);
                mes=new ContentSelectOd();

            }

            select.setContenido(meses);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public List<String> consultarDetallesEstadoCuentaTDC(List<String> parametros,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.consultarDetallesEstadoCuentaTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        List<String> detalle=new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_tdcconsulta_cab_pr(?,?,?,?,?); end;");
//           vbt_tdcconsulta_cab_pr (p_num_cta    IN VARCHAR2,
//            p_codproserv  IN VARCHAR2,
//                    p_mes         IN VARCHAR2,
//            p_vbt_tdcconsulta_cab OUT vbt_tdcconsulta_cab,
//                    p_resultado OUT VARCHAR2) AS

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.get(1));

            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(2));
            }

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);
            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            while (result.next()){
//                num_cta_plast_ppal = NullFormatter.formatBlank(rowEncab.getColumnAt(0));
                detalle.add(NullFormatter.formatBlank(result.getString(1)));
//                nombre_cliente = NullFormatter.formatBlank(rowEncab.getColumnAt(2));
                detalle.add(NullFormatter.formatBlank(result.getString(3)));
//                direccion_uno = NullFormatter.formatBlank(rowEncab.getColumnAt(3));
                detalle.add(NullFormatter.formatBlank(result.getString(4)));
//                direccion_dos = NullFormatter.formatBlank(rowEncab.getColumnAt(4));
                detalle.add(NullFormatter.formatBlank(result.getString(5)));
//                direccion_tres = NullFormatter.formatBlank(rowEncab.getColumnAt(5));
                detalle.add(NullFormatter.formatBlank(result.getString(6)));
//                zona_postal = NullFormatter.formatBlank(rowEncab.getColumnAt(6));
                detalle.add(NullFormatter.formatBlank(result.getString(7)));
//                lim_credito = (new Double(rowEncab.getColumnAt(7))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(7),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(8))!=null) ? formatoMonto.formatNumber(result.getString(8),2,","):"&nbsp;");
//                credito_disp = (new Double(rowEncab.getColumnAt(8))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(8),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(9))!=null)?formatoMonto.formatNumber(result.getString(9), 2, ","):"&nbsp;");
//                fecha_factura = rowEncab.getColumnAt(9);
                detalle.add(result.getString(10));
//                pag_total = (new Double(rowEncab.getColumnAt(10))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(10),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(11))!=null) ? formatoMonto.formatNumber(result.getString(11),2,","):"&nbsp;");
//                pag_min_mes = (new Double(rowEncab.getColumnAt(11))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(11),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(12))!=null) ? formatoMonto.formatNumber(result.getString(12),2,","):"&nbsp;");
//                fec_pag_antes = rowEncab.getColumnAt(12);
                detalle.add(result.getString(13));
//                sal_anterior = (new Double(rowEncab.getColumnAt(21))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(21),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(22))!=null) ? formatoMonto.formatNumber(result.getString(22),2,","):"&nbsp;");
//                cargos = (new Double(rowEncab.getColumnAt(22))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(22),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(23))!=null) ? formatoMonto.formatNumber(result.getString(23),2,","):"&nbsp;");
//                abonos = (new Double(rowEncab.getColumnAt(23))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(23),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(24))!=null) ? formatoMonto.formatNumber(result.getString(24),2,","):"&nbsp;");
//                saldo_actual = (new Double(rowEncab.getColumnAt(13))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(13),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(14))!=null) ? formatoMonto.formatNumber(result.getString(14),2,","):"&nbsp;");
//                cuo_ven = NullFormatter.formatBlank(rowEncab.getColumnAt(14));
                detalle.add(NullFormatter.formatBlank(result.getString(26)));
//                imp_ven = (new Double(rowEncab.getColumnAt(15))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(15),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(16))!=null) ? formatoMonto.formatNumber(result.getString(16),2,","):"&nbsp;");
//                cuo_mes = (new Double(rowEncab.getColumnAt(16))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(16),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(17))!=null) ? formatoMonto.formatNumber(result.getString(17),2,","):"&nbsp;");
//                int_bon = (new Double(rowEncab.getColumnAt(17))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(17),4,","):"&nbsp;";
                detalle.add((new Double(result.getString(18))!=null) ? formatoMonto.formatNumber(result.getString(18),4,","):"&nbsp;");
//                tas_int = (new Double(rowEncab.getColumnAt(18))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(18),4,","):"&nbsp;";
                detalle.add((new Double(result.getString(19))!=null) ? formatoMonto.formatNumber(result.getString(19),4,","):"&nbsp;");
//                tasa_mora = (new Double(rowEncab.getColumnAt(19))!=null)?formatoMonto.formatNumber(rowEncab.getColumnAt(19),2,","):"&nbsp;";
                detalle.add((new Double(result.getString(20))!=null)?formatoMonto.formatNumber(result.getString(20),2,","):"&nbsp;");
//                periodo_fact = NullFormatter.formatBlank(rowEncab.getColumnAt(20));
                detalle.add(NullFormatter.formatBlank(result.getString(21)));
//                tpo_tdc = NullFormatter.formatBlank(rowEncab.getColumnAt(24));
                detalle.add(NullFormatter.formatBlank(result.getString(25)));
//                observaciones = NullFormatter.formatBlank(rowEncab.getColumnAt(25));
                detalle.add(NullFormatter.formatBlank(result.getString(26)));

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }


    public List<String> consultarDetallesPagoTDC(List<String> parametros, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "CreditCardsIo.consultarDetallesPagoTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<String> detalle=new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_PAGOS_TARJETA.PR_DETALLE_UN_PAGO_TDC(?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            while (result.next()){

                detalle.add(NullFormatter.formatBlank(result.getString(3))); //NRO_DOC
                detalle.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoPagoTDC"+(result.getString(10)).trim())); //TIPO_PAGO
                detalle.add(NullFormatter.formatBlank(result.getString(11))); //CUENTA DEBITO
                detalle.add(CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(8)), 2, ",")); //MONTO
                detalle.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoStatusTDCPago"+(result.getString(9)).trim())); //ESTATUS
                detalle.add(NullFormatter.formatBlank(result.getString(12))); //USRID_CARGA
                detalle.add(NullFormatter.formatBlank(result.getString(13))); //FECHA_CARGA
                detalle.add(NullFormatter.formatBlank(result.getString(14))); //USRID_APRUEBA
                detalle.add(NullFormatter.formatBlank(result.getString(15))); //FECHA_APRUEBA
                detalle.add(NullFormatter.formatBlank(result.getString(16))); //USRID_LIBERA
                detalle.add(NullFormatter.formatBlank(result.getString(17))); //FECHA_LIBERA
                detalle.add(NullFormatter.formatBlank(result.getString(18))); //USRID_RECHAZA
                detalle.add(NullFormatter.formatBlank(result.getString(19))); //FECHA_RECHAZA
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public String cambiarEstatusPagosTDC (String Status, String numReferencia, VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "CreditCardsIo.cambiarEstatusPagoTdc";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<String> datos= new ArrayList<String>();
        String respuesta=null;
        MailManager365 mailManager = new MailManager365("vbtonline");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
        SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));

        String numeros_ref="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            //Carga los correos que afectaron la transferencia antes de cambiar su estatus
            String emailICargadores = emailsRechazo(numReferencia, usuario);

            connection=getConnection();
            connection.setAutoCommit(false);

            numeros_ref = numReferencia.replace("'","");
            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_PAGOS_TARJETA.PR_CAMBIAR_ESTATUS_PAGOS_FC(?,?,?,?); END;");

            if(Status == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,Status);

            if(numReferencia == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,numeros_ref);

            if (usuario== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuario.getLogin());

            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK")) {
                throw (new Exception (respuesta,null));
            }else{


                if(Status.equals("L")){

                    statement  = connection.prepareCall ("begin PAGOSTDCHANDLER.pagotdc_inserta_bofa_pr(?,?); end;");

                    if(numeros_ref==null)
                        statement.setNull(1,OracleTypes.NULL);
                    else
                        statement.setString(1,numeros_ref);

                    statement.registerOutParameter(2, OracleTypes.VARCHAR);

                    statement.execute ();

                    respuesta = statement.getString(2);

                    if (!respuesta.equalsIgnoreCase("OK")){
                        throw (new Exception (respuesta,null));
                    }
                }


                connection.commit();
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                try {

                    String referencias="";

                    int total=numeros_ref.split(",").length;
                    for (int i = 0; i < total; i++) {
                        String line="";
                        datos=buscarMontoPagosTdc(numeros_ref.split(",")[i], usuario);
                        line=ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGNumeroPagoTdc")+ ": "+numeros_ref.split(",")[i]+" " +
                                ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMontoPagoTDC")+ ": "+datos.get(2)+" "+CurrencyFormatter.formatNumber(datos.get(1),2,",")+".";
                        referencias=referencias+"\n\n"+line;

                    }

                    if(Status.equals("A")){
                        String htmlText = "";

                        htmlText=ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcAprobador")  +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgResumenAprobacion")+
                                "\n"+referencias+
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento1FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento3FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";

                        mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcAprobador"), htmlText);
                        String emailInternos = EmailsAprobadores(usuario.getNumContrato(), "8", usuario);

                        String listaCorreos="";

                        if (emailICargadores.length()>0) {
                            listaCorreos = emailInternos+","+emailICargadores;
                        }else{
                            listaCorreos = emailInternos;
                        }

                        if (!listaCorreos.equalsIgnoreCase(""))
                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), listaCorreos, ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailAlertaAprobacionPagoTdcTitulo"), htmlText);

                        this.GuardarLogFC(usuario.getLogin(),"20","1","14","",usuario.getIP(), "Se han aprobado los siguientes Pagos de Tdc: " + numReferencia,usuario.getNumContrato());

                        loggerIo.GuardarLog(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + ( listaCorreos +","+usuario.getEmail()+ " motivado a la aprobación de Pagos de Tdc Referencia Nro: " + numReferencia).replaceAll(",",", ")) ;

                    }else if(Status.equals("L")){

                        String htmlText = "";

                        htmlText=ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcLiberador")  +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgResumenLiberacion")+
                                "\n"+referencias+
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento3FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";

                        mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcLiberador"), htmlText);
                        String emailInternos = emailsClientesPrincipales(usuario.getNumContrato(), usuario);
                        String listaCorreos="";

                        if (emailICargadores.length()>0) {
                            listaCorreos= emailInternos+","+emailICargadores;
                        }else{
                            listaCorreos= emailInternos;
                        }

                        listaCorreos = listaCorreos + ", " + ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc");

                        if (!listaCorreos.equalsIgnoreCase(""))
                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), listaCorreos, ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcLiberadorAlertaTitulo"), htmlText);

                        this.GuardarLogFC(usuario.getLogin(),"21","1","14","",usuario.getIP(), "Se han liberado los siguientes Pagos de TDC: " + numReferencia,usuario.getNumContrato());
                        loggerIo.GuardarLog(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + ( listaCorreos +","+usuario.getEmail()+ " motivado a la liberación de Pagos de TDC Referencia Nro: " + numReferencia).replaceAll(",",", ")) ;
                    }else if(Status.equals("R")){
                        String htmlText = "";
                        htmlText=ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcRechazo")  +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgResumenRechazo")+
                                "\n"+referencias+
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento3FC") +
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n";

                        mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcRechazo"), htmlText);

                        String emailInternos = emailsRechazo(numReferencia, usuario);
                        htmlText=ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcRechazo")  +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgResumenRechazo")+
                                "\n"+referencias+
                                "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n";

                        if (!emailInternos.equalsIgnoreCase(""))
                            mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),emailInternos, ResourceBundle.getBundle("TarjetasCredito"+Idioma).getString("TAGMsgEmailPagoTdcRechazo"), htmlText);
                        this.GuardarLogFC(usuario.getLogin(),"22","1","14","",usuario.getIP(), "Se han rechazado los siguientes Pagos de TDC: " + numReferencia,usuario.getNumContrato());
                        loggerIo.GuardarLog(usuario.getLogin(),"23","1","23", "",usuario.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + ( emailInternos +","+usuario.getEmail()+ " motivado al rechazo de Pagos de TDC Referencia Nro: " + numReferencia).replaceAll(",",", ")) ;
                    }
                    time = System.currentTimeMillis () - time;

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);
                }
                catch (Exception ex) {
                    logger.error(ex);
                    loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                    //throw (new Exception (ex.getMessage(),null));
                    respuesta = "ERROR_EMAIL";
                    return (respuesta);
                }

            }

        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public List<String> buscarMontoPagosTdc (String codPago, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.buscarMontoPagosTdc";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<String> resultados= new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_TARJETA.PCK_PAGOS_TARJETA.PR_CARGAR_MONTOS_PAGOSTDC(?,?,?); end;");

            if (codPago== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,codPago);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                resultados.add(result.getString(1)) ;
                resultados.add(result.getString(2));
                resultados.add(result.getString(3));
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (resultados);
    }

    public String emailsClientesPrincipales (String contrato,VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.emailsClientesPrincipales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String correos="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_cliente_principal_pr(?,?,?); end;");



            if(contrato==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,contrato);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(5));
//                correos= result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }

    public String emailsRechazo (String referencia, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.emailsContactos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String correos="";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin VBT_TARJETA.PCK_PAGOS_TARJETA.PR_CORREOS_USUARIOS_FC(?,?,?); end;");



            if(referencia==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,referencia);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }


    public TableOd consultarSaldosEstadoCuentaTDC (List<String> parametros, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.consultarSaldosEstadoCuentaTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_tdcconsulta_mov_pr(?,?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, parametros.get(1));
            }


            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(2));
            }

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            String actual_formato;
            String fec_trn = new String();
            String num_ref;
            String descripcion;
            String tpo_trn;
            String mto_trn_ml;
            String mto_trn_mo;
            String tas_cam;
            String dsc_moneda;
            String actual;
            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            String actualFormatoAux = new String();
            while (result.next()){
                actual = new String();
                actual_formato = new String();
                fec_trn = new String();
                num_ref = new String();
                descripcion = new String();
                tpo_trn = new String();
                mto_trn_ml = new String();
                mto_trn_mo = new String();
                tas_cam = new String();
                dsc_moneda = new String();

                actual = NullFormatter.formatBlank(result.getString(1));
                actual_formato = NullFormatter.formatBlank(result.getString(11));
                if (!actual_formato.equalsIgnoreCase(actualFormatoAux)){
                    actualFormatoAux = NullFormatter.formatBlank(result.getString(11));
                    body = new ArrayList<String>();
                    body.add("");
                    body.add("<b>"+ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGNumeroTarjeta")+": "+actual_formato+"</b>");
                    body.add("");
                    body.add("");
                    bodys.add(body);
                }
                fec_trn = result.getString(2);
                num_ref = NullFormatter.formatBlank(result.getString(3));
                descripcion = NullFormatter.formatBlank(result.getString(4));
                tpo_trn = NullFormatter.formatBlank(result.getString(7));
                mto_trn_ml = (new Double(result.getString(6)) != null) ? formatoMonto.formatNumber(result.getString(6), 2, ",") : "&nbsp;";
                mto_trn_mo = (new Double(result.getString(8)) != null) ? formatoMonto.formatNumber(result.getString(8), 2, ",") : "&nbsp;";
                tas_cam = (new Double(result.getString(9)) != null) ? formatoMonto.formatNumber(result.getString(9), 4, ",") : "&nbsp;";
                dsc_moneda = NullFormatter.formatBlank(result.getString(10));

                body = new ArrayList<String>();
                body.add(fec_trn);
                body.add(num_ref);
                body.add(descripcion.toUpperCase() + ((tas_cam.equals("1,0000") || tas_cam.equals("0,0000")) ? " " : "(" + dsc_moneda.toUpperCase() + "  " + mto_trn_mo + "  " + tas_cam + ")"));
                body.add(tpo_trn.equals("CR") ? "-" + mto_trn_ml : mto_trn_ml);

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
//
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGFecTran"));
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGReferencia"));
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGMonto"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }


    public TableOd cargarMovimientosTDCITT (List<String> parametros, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.cargarMovimientosTDCITT";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_tdmovimi_trans_pr(?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            while (result.next()){

                body = new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add(CurrencyFormatter.formatNumber(result.getString(4),2,","));

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
//
            header.add(ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGFechaTransacción"));
            header.add(ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGFechaProceso"));
            header.add(ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGMonto_Usd"));



            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public Map<String,Object> cargarEstatusTDCCL (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.cargarEstatusTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        Map<String,Object> estatus = null;
        TableOd lista = null;
        String fecha = new String();
        String respuesta = "";
        List<List<String>> filas = null;
        List<String> celdas = null;
        List<String> columnas = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CONSULTAR_ESTATUS(?,?,?,?,?,?,?,?); END;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, parametros.get(1).split("_")[0]);
            }

            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(1).split("_")[2]);
            }

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.startsWith("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            estatus = new HashMap<String, Object>();

            respuesta = statement.getString(4);

            estatus.put("estatus",respuesta);

            result = (ResultSet) statement.getObject(5);

            filas = new ArrayList();

            while (result.next()){

                celdas = new ArrayList<String>();
                String id="";
                celdas.add("<table id='tabla1_"+result.getString(4)+"'><tr><td>"+result.getString(1)+"<table><tr><td>");
                celdas.add("<table id='tabla2_"+result.getString(4)+"'><tr><td>"+result.getString(2)+"<table><tr><td>");
//                celdas.add(result.getString(2));
                id=result.getString(4)+"|"+result.getString(5)+"|"+result.getString(6)+"|"+result.getString(7)+"|"+result.getString(1)+"|"+result.getString(2)+"|"+ ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_EST_" + result.getString(3))+"|"+result.getString(3);

//                celdas.add("<span id='TAG_EST_" + result.getString(3) + "'>" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_EST_" + result.getString(3)) + "</span>");
                celdas.add("<span>" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_EST_" + result.getString(3)) + "</span>");

                SimpleDateFormat formatoFecha;
                formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
                String fechaHoy=formatoFecha.format(new Date()).toString();

                if (result.getString(3).equalsIgnoreCase("P")) {

                    //Valida que no se pueda editar una regla que vence el midmo dia
                    /*if (fechaHoy.equalsIgnoreCase(result.getString(2))){
                        if (idioma.equalsIgnoreCase("_ve_es")) {
                            celdas.add("<table align='center'><tr><td><img id='del|"+id+"'  style='cursor:pointer'  src='resources/images/bin.png' title='Eliminar período de activación' onclick='eliminarReglaMain(this.id);' /></td></tr></table>");
                        } else {
                            celdas.add("<table align='center'><tr><td><img id='del|"+id+"'  style='cursor:pointer' src='resources/images/bin.png' title='Delete the activation period' onclick='eliminarReglaMain(this.id);' /></td></tr></table>");
                        }
                    }else{*/
                    if (idioma.equalsIgnoreCase("_ve_es")) {
                        celdas.add("<table align='center'><tr><td><img  id='"+id+"' style='cursor:pointer' src='resources/images/edit.png' title='Modificar período de activación' onclick='editarRegla(this.id)' /></td><td><img id='del|"+id+"' src='resources/images/bin.png' style='cursor:pointer' title='Eliminar período de activación' onclick='eliminarReglaMain(this.id);' /></td></tr></table>");
                    } else {
                        celdas.add("<table align='center'><tr><td><img  id='"+id+"' style='cursor:pointer' src='resources/images/edit.png' title='Modify the activation period' onclick='editarRegla(this.id)' /></td><td><img id='del|"+id+"' src='resources/images/bin.png' style='cursor:pointer' title='Delete the activation period' onclick='eliminarReglaMain(this.id);' /></td></tr></table>");
                    }
                    //}

                } else {

                    if (result.getString(3).equalsIgnoreCase("E")) {
                        if (fechaHoy.equalsIgnoreCase(result.getString(2))){
                            celdas.add("<table align='center'><tr><td></td></tr></table>");
                        }else{
                            if (idioma.equalsIgnoreCase("_ve_es")) {
                                celdas.add("<table align='center'><tr><td><img  id='"+id+"' style='cursor:pointer'  src='resources/images/edit.png' title='Modificar período de activación' onclick='editarRegla(this.id)' /></td></td></tr></table>");
                            } else {
                                celdas.add("<table align='center'><tr><td><img  id='"+id+"' style='cursor:pointer'  src='resources/images/edit.png' title='Modify the activation period' onclick='editarRegla(this.id)' /></td></tr></table>");
                            }
                        }

                    }else{
                        celdas.add("");

                    }
                }

                filas.add(celdas);
            }

            columnas = new ArrayList<String>();

            columnas.add("TAG_FEC_INICIAL#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_FEC_INICIAL"));
            columnas.add("TAG_FEC_FINAL#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_FEC_FINAL"));
            columnas.add("TAG_ESTATUS#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_ESTATUS"));
            columnas.add("TAG_ACCION#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_ACCION"));

            lista = new TableOd();

            lista = TransformTable.getTable(columnas, filas);

            estatus.put("lista_activas",lista);

            result = (ResultSet) statement.getObject(6);

            filas = new ArrayList();

            while (result.next()){

                celdas = new ArrayList<String>();

                celdas.add(result.getString(1));
                celdas.add(result.getString(2));
                celdas.add("<span>" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_EST_" + result.getString(3)) + "</span>");

                filas.add(celdas);
            }

            columnas = new ArrayList<String>();

            columnas.add("TAG_HIST_FEC_INICIAL#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_FEC_INICIAL"));
            columnas.add("TAG_HIST_FEC_FINAL#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_FEC_FINAL"));
            columnas.add("TAG_ESTATUS#" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_ESTATUS"));

            lista = new TableOd();

            lista = TransformTable.getTable(columnas, filas);

            estatus.put("lista_historico",lista);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {

            logger.error(ex,ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (estatus);
    }

    public String crearReglaTDCCL (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        String respuestaService = null;
        List<String> parametrosService = new ArrayList<String>();
        validacionUtil validar = new validacionUtil();
        boolean flagHoy = false;
        String estatus = "P";
        String codBloqueo = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            SimpleDateFormat formatoFecha;
            formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
            String fechaHoy=formatoFecha.format(new Date()).toString();


            if (validar.fechasDiferenciaEnDias(validar.aDate(fechaHoy),validar.aDate(parametros.get(1))) == 0){
                flagHoy = true;
                estatus = "E";
            }
            /*    */

            if(flagHoy || !flagHoy) {
                connection = getConnection();
                connection.setAutoCommit(false);


                statement = connection.prepareCall("BEGIN VBT_TARJETA.PCK_TARJETA.PR_REGISTRAR_DESBLOQUEO(?,?,?,?,?,?,?,?,?); END;");

                statement.setString(1, parametros.get(0).split("_")[1]);
                statement.setString(2, parametros.get(0).split("_")[2]);
                statement.setString(3, parametros.get(0).split("_")[0]);
                statement.setString(4, parametros.get(1));
                statement.setString(5, parametros.get(2));
                statement.setString(6, parametros.get(3));

                statement.registerOutParameter(7, OracleTypes.VARCHAR);

                statement.setString(8, estatus);

                statement.registerOutParameter(9, OracleTypes.VARCHAR);

                statement.execute();

                respuesta = statement.getString(7);

                codBloqueo = statement.getString(9);

                if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                    connection.rollback();
                    logger.error(respuesta, new Exception(respuesta));
                    throw (new Exception(respuesta, null));
                } else {
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + CreditCardsIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                    if(flagHoy){
                        parametrosService.add("DESBLOQUEO");
                        parametrosService.add(NullFormatter.formatBlank(parametros.get(0).split("_")[2]));
                        parametrosService.add(NullFormatter.formatBlank(parametros.get(0).split("_")[4]));
                        parametrosService.add(NullFormatter.formatBlank(parametros.get(0).split("_")[1]));
                        parametrosService.add(codBloqueo);
                        parametrosService.add("BP");
                        respuestaService = llamarServicioBtrans(parametrosService, usersOd);

                        //if(!respuestaService.equalsIgnoreCase("SUCCESS")) {
                        if(!respuestaService.equalsIgnoreCase("SUCCESS")) {
                            respuesta = respuestaService;

                            connection.rollback();
                            logger.error(respuesta, new Exception(respuesta));
                            //throw (new Exception(respuesta, null));
                        }
                    }

                }

            }
            connection.commit();
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }catch (Exception ex) {
            respuesta = ex.getMessage();
            connection.rollback();
            logger.error(ex);
            throw (new Exception (respuesta,null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String eliminarReglaTDCCL (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.eliminarReglaTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_ELIMINAR_REGLA(?,?); END;");

            statement.setString(1, parametros.get(0));

            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public String editarRegla (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.editarRegla";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_MODIFICAR_DESBLOQUEO(?,?,?,?,?,?,?,?); END;");

            statement.setString(1, parametros.get(0));
            statement.setString(2, parametros.get(1));
            statement.setString(3, parametros.get(2));
            statement.setString(4, parametros.get(3));
            statement.setString(5, parametros.get(4));
            statement.setString(6, parametros.get(5));
            statement.setString(7, usersOd.getLogin());

            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else{
                respuesta="OK";
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    /**
     *  public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7 ) throws Exception {
     final String origen = "CreditCardsIo.GuardarLog";
     String respuesta="";
     Connection connectionLog = null;
     ResultSet result = null;
     CallableStatement statementLog = null;

     try{

     connectionLog=getConnection();
     statementLog = connectionLog.prepareCall("begin vbtonline.vbt_logAction_pr(?,?,?,?,?,?,?,?); end;");

     statementLog.setString(1, parametro1);
     statementLog.setString(2, parametro2);
     statementLog.setString(3, parametro3);
     statementLog.setString(4, parametro4);
     statementLog.setString(5, parametro5);
     statementLog.setString(6, parametro6);
     statementLog.setString(7, parametro7);

     statementLog.registerOutParameter(8, OracleTypes.VARCHAR);
     statementLog.execute();

     respuesta = statementLog.getString(8);
     if (!respuesta.equalsIgnoreCase("OK"))
     throw (new Exception (respuesta,null));
     else
     logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
     }
     catch (Exception ex) {
     logger.error(ex); throw (new Exception (ex.getMessage(),null));
     }
     finally {
     closeJdbcObjects(connectionLog, statementLog, result);
     }

     return (respuesta);
     }

     public String guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
     final String origen = "CreditCardsIo.guardarExcepcion";
     String respuesta="";
     Connection connectionLog = null;
     ResultSet result = null;
     CallableStatement statementLog = null;

     try{

     connectionLog=getConnection();
     statementLog = connectionLog.prepareCall("begin vbtonline.vbt_errores_pr(?,?,?,?,?,?,?); end;");

     statementLog.setString(1, parametro1);
     statementLog.setString(2, parametro2);
     statementLog.setString(3, parametro3);
     statementLog.setString(4, parametro4);
     statementLog.setString(5, parametro5);

     if(parametro6==null)
     statementLog.setString(6, "");
     else
     statementLog.setString(6, parametro6);

     statementLog.registerOutParameter(7, OracleTypes.VARCHAR);
     statementLog.execute();

     respuesta = statementLog.getString(7);
     //            if (!respuesta.equalsIgnoreCase("OK"))
     //                throw (new Exception (respuesta,null));
     //            else
     logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
     }
     catch (Exception ex) {

     logger.error(ex);
     }
     finally {
     try{
     closeJdbcObjects(connectionLog, statementLog, result);
     }catch (Exception ex) {

     logger.error(ex);
     }
     }

     return (respuesta);
     }
     */

    public String validarFechas (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.validarFechas";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_VALIDAR_FECHA_REGLA(?,?,?,?,?,?,?,?,?,?); END;");

            statement.setString(1, parametros.get(2));
            statement.setString(2, parametros.get(4));
            statement.setString(3, parametros.get(3));
            statement.setString(4, parametros.get(0));
            statement.setString(5, parametros.get(1));
            statement.setString(6, usersOd.getLogin());
            statement.setString(7, parametros.get(5));
            statement.setString(8, parametros.get(6));

            statement.registerOutParameter(9, OracleTypes.VARCHAR);
            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(9);

            if (!respuesta.equalsIgnoreCase("OK")) {
                respuesta= statement.getString(10);
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public String cargarProximoDiaHabil (VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarProximoDiaHabil";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            //statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_PROXIMO_DIA_HABIL(?,?); END;");
            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_PROXIMO_DIA_ACTIVACION_TDC(?,?); END;");

            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK")) {
                connection.rollback();
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                respuesta = statement.getString(1);
            }
            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public List<List<String>> cargarFeriados(String anio,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.cargarFeriados";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        List<List<String>> detalle=new ArrayList();
        List<String> body=new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_TARJETA.PCK_TARJETA.PR_CARGAR_FERIADOS(?,?,?); end;");

            if(anio==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,anio);
            }


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                List<String> elemento=new ArrayList<String>();
                elemento.add(result.getString(1));
                elemento.add(result.getString(2));
                detalle.add(elemento);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public TableOd consultarHistorioPagoTDC (List<String> parametros, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.consultarHistorioPagoTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_PAGOS_TARJETA.PR_HISTORICO_PAGOS(?,?,?,?,?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, parametros.get(1));
            }

            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(2));
            }


            if(parametros.get(3)==null){
                statement.setNull(4, OracleTypes.NULL);
            }else{
                statement.setString(4, parametros.get(3));
            }


            if(parametros.get(4)==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, parametros.get(4));
            }

            if(parametros.get(5)==null){
                statement.setNull(6, OracleTypes.NULL);
            }else{
                statement.setString(6, parametros.get(5));
            }


            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (8);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            while (result.next()){

                body = new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(7)));
                body.add(NullFormatter.formatBlank(result.getString(6)));
                body.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoPagoTDC" + (result.getString(3)).trim()));
                // body.add(CurrencyFormatter.formatNumber(result.getString(4),2,","));
                body.add(result.getString(4));
                //body.add( CurrencyFormatter.formatNumber(result.getString(4).replace(",","."),2,","));


                body.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoStatusTDCPago"+(result.getString(5)).trim()));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add("<img class='detalle_resumen_pago_tdc' id='detalle_"+result.getString(8)+"' width='20' src='../vbtonline/resources/images/search.png' alt='' />");
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
//
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGFechaPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("misdatos_TAGCodigoCartera"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGCuentaPagoSelectTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGTipoPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGStatusTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGFechaProcesoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Detalle_detalle"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public TableOd consultarSaldosEstadoCuentaCorteTDC (List<String> parametros, String idioma,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.consultarSaldosEstadoCuentaCorteTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_detalle_pago_tdc(?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            String actual_formato;
            String fec_trn = new String();
            String num_ref;
            String descripcion;
            String tpo_trn;
            String mto_trn_ml;
            String mto_trn_mo;
            String tas_cam;
            String dsc_moneda;
            String actual;
            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            String actualFormatoAux = new String();
            while (result.next()){
                actual = new String();
                actual_formato = new String();
                fec_trn = new String();
                num_ref = new String();
                descripcion = new String();
                tpo_trn = new String();
                mto_trn_ml = new String();
                mto_trn_mo = new String();
                tas_cam = new String();
                dsc_moneda = new String();

                actual = NullFormatter.formatBlank(result.getString(1));
                actual_formato = NullFormatter.formatBlank(result.getString(11));
                if (!actual_formato.equalsIgnoreCase(actualFormatoAux)){
                    actualFormatoAux = NullFormatter.formatBlank(result.getString(11));
                    body = new ArrayList<String>();
                    body.add("");
                    body.add("<b>"+ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGNumeroTarjeta")+": "+actual_formato+"</b>");
                    body.add("");
                    body.add("");
                    bodys.add(body);
                }
                fec_trn = result.getString(2);
                num_ref = NullFormatter.formatBlank(result.getString(3));
                descripcion = NullFormatter.formatBlank(result.getString(4));
                tpo_trn = NullFormatter.formatBlank(result.getString(7));
                mto_trn_ml = (new Double(result.getString(6)) != null) ? formatoMonto.formatNumber(result.getString(6), 2, ",") : "&nbsp;";
                mto_trn_mo = (new Double(result.getString(8)) != null) ? formatoMonto.formatNumber(result.getString(8), 2, ",") : "&nbsp;";
                tas_cam = (new Double(result.getString(9)) != null) ? formatoMonto.formatNumber(result.getString(9), 4, ",") : "&nbsp;";
                dsc_moneda = NullFormatter.formatBlank(result.getString(10));

                body = new ArrayList<String>();
                body.add(fec_trn);
                body.add(num_ref);
                body.add(descripcion.toUpperCase() + ((tas_cam.equals("1,0000") || tas_cam.equals("0,0000")) ? " " : "(" + dsc_moneda.toUpperCase() + "  " + mto_trn_mo + "  " + tas_cam + ")"));
                body.add(tpo_trn.equals("CR") ? "-" + mto_trn_ml : mto_trn_ml);

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
//
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGFecTran"));
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGReferencia"));
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("TdcConsultaEdoCta"+idioma).getString("TAGMonto"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }


    public List<String> consultarDetallesEstadoCuentaCorteTDC(List<String> parametros,   VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.consultarDetallesEstadoCuentaCorteTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        List<String> detalle=new ArrayList<String>();
        String respuesta=null;
        CurrencyFormatter formatoMonto = new CurrencyFormatter();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            Socket cliente;
            DataOutputStream outStr;
            DataInputStream inStr;
            String datos;
            StringBuffer mensaje = new StringBuffer();

            cliente =null;
            outStr =null;
            inStr =null;

            try {

                cliente = new Socket(ResourceBundle.getBundle("vbtonline").getString("servidorBetransPagos"), Integer.parseInt(ResourceBundle.getBundle("vbtonline").getString("puertoBetransPagos")));
                outStr = new DataOutputStream(cliente.getOutputStream());
                inStr = new DataInputStream(cliente.getInputStream());

                mensaje.append("CONSULTA,"+parametros.get(2).substring(4)+"," + parametros.get(3));

                outStr.writeUTF(mensaje.toString());
                respuesta = inStr.readUTF();

                if (respuesta.split("\\|")[0].equalsIgnoreCase("OK")){

                    detalle.add((new Double(respuesta.split("\\|")[1])!=null) ? CurrencyFormatter.formatNumber(respuesta.split("\\|")[1],2,"."):"&nbsp;");
                    detalle.add((new Double(respuesta.split("\\|")[2])!=null) ? CurrencyFormatter.formatNumber(respuesta.split("\\|")[2],2,"."):"&nbsp;");
                    detalle.add((new Double(respuesta.split("\\|")[3])!=null) ? CurrencyFormatter.formatNumber(respuesta.split("\\|")[3],2,"."):"&nbsp;");
                    detalle.add((new Double(respuesta.split("\\|")[4])!=null) ? CurrencyFormatter.formatNumber(respuesta.split("\\|")[4],2,"."):"&nbsp;");

                    connection=getConnection();

                    /**
                     * Consulta del Saldo Actual para ser incluido en la página de pagos, a solicitud de Ysis Vivas el 25/11/2016
                     */
                    statement  = connection.prepareCall ("begin vbtonline.vbt_tdcconsulta_saldos_pr(?,?,?,?); end;");

                    if(parametros.get(1)==null){
                        statement.setNull(1, OracleTypes.NULL);
                    }else{
                        statement.setString(1, parametros.get(1));
                    }

                    statement.setString(2, "002");
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);

                    statement.execute ();

                    respuesta = statement.getString(4);

                    if (!respuesta.equalsIgnoreCase("OK"))
                        throw (new Exception (respuesta,null));
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                    result = (ResultSet) statement.getObject (3);

                    while (result.next()){
                        detalle.add((new Double(result.getString(1)) != null) ? CurrencyFormatter.formatNumber(result.getString(1),2,"."):"&nbsp;");
                        detalle.add(result.getString(3));
                    }

                }else{

                    detalle.add("");
                    detalle.add("");
                    detalle.add("");
                    detalle.add("");
                    detalle.add("");
                    detalle.add("");
                    loggerIo.guardarExcepcion(usuario.getIP(), "Servicio de pagos no disponible", origen,"Servicio de pagos no disponible", usuario.getLogin(), usuario.getNumContrato());
                }


            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                detalle.add("");
                detalle.add("");
                detalle.add("");
                detalle.add("");
                detalle.add("");
                detalle.add("");
                loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            }finally{
                inStr.close();
                outStr.close();
                cliente.close();
            }





//            connection=getConnection();
//
//            statement  = connection.prepareCall ("begin vbtonline.vbt_detalle_pago_tdc(?,?,?); end;");
////           vbt_tdcconsulta_cab_pr (p_num_cta    IN VARCHAR2,
////            p_codproserv  IN VARCHAR2,
////                    p_mes         IN VARCHAR2,
////            p_vbt_tdcconsulta_cab OUT vbt_tdcconsulta_cab,
////                    p_resultado OUT VARCHAR2) AS
//
//            if(parametros.get(0)==null){
//                statement.setNull(1, OracleTypes.NULL);
//            }else{
//                statement.setString(1, parametros.get(0));
//            }
//
//            statement.registerOutParameter(2, OracleTypes.CURSOR);
//            statement.registerOutParameter(3, OracleTypes.VARCHAR);
//
//            statement.execute ();
//
//            respuesta = statement.getString(3);
//
//            if (!respuesta.equalsIgnoreCase("OK"))
//                throw (new Exception (respuesta,null));
//            else
//                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
//
//            result = (ResultSet) statement.getObject (2);
//            CurrencyFormatter formatoMonto = new CurrencyFormatter();
//            while (result.next()){
////                num_cta_plast_ppal = NullFormatter.formatBlank(rowEncab.getColumnAt(0));
//                detalle.add(NullFormatter.formatBlank(result.getString(1)));
////                nombre_cliente = NullFormatter.formatBlank(rowEncab.getColumnAt(2));
//                detalle.add(NullFormatter.formatBlank(result.getString(3)));
////                direccion_uno = NullFormatter.formatBlank(rowEncab.getColumnAt(3));
//                detalle.add(NullFormatter.formatBlank(result.getString(4)));
////                direccion_dos = NullFormatter.formatBlank(rowEncab.getColumnAt(4));
//                detalle.add(NullFormatter.formatBlank(result.getString(5)));
////                direccion_tres = NullFormatter.formatBlank(rowEncab.getColumnAt(5));
//                detalle.add(NullFormatter.formatBlank(result.getString(6)));
////                zona_postal = NullFormatter.formatBlank(rowEncab.getColumnAt(6));
//                detalle.add(NullFormatter.formatBlank(result.getString(7)));
////                lim_credito = (new Double(rowEncab.getColumnAt(7))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(7),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(8))!=null) ? formatoMonto.formatNumber(result.getString(8),2,","):"&nbsp;");
////                credito_disp = (new Double(rowEncab.getColumnAt(8))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(8),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(9))!=null)?formatoMonto.formatNumber(result.getString(9), 2, ","):"&nbsp;");
////                fecha_factura = rowEncab.getColumnAt(9);
//                detalle.add(result.getString(10));
////                pag_total = (new Double(rowEncab.getColumnAt(10))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(10),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(11))!=null) ? formatoMonto.formatNumber(result.getString(11),2,","):"&nbsp;");
////                pag_min_mes = (new Double(rowEncab.getColumnAt(11))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(11),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(12))!=null) ? formatoMonto.formatNumber(result.getString(12),2,","):"&nbsp;");
////                fec_pag_antes = rowEncab.getColumnAt(12);
//                detalle.add(result.getString(13));
////                sal_anterior = (new Double(rowEncab.getColumnAt(21))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(21),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(22))!=null) ? formatoMonto.formatNumber(result.getString(22),2,","):"&nbsp;");
////                cargos = (new Double(rowEncab.getColumnAt(22))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(22),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(23))!=null) ? formatoMonto.formatNumber(result.getString(23),2,","):"&nbsp;");
////                abonos = (new Double(rowEncab.getColumnAt(23))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(23),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(24))!=null) ? formatoMonto.formatNumber(result.getString(24),2,","):"&nbsp;");
////                saldo_actual = (new Double(rowEncab.getColumnAt(13))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(13),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(14))!=null) ? formatoMonto.formatNumber(result.getString(14),2,","):"&nbsp;");
////                cuo_ven = NullFormatter.formatBlank(rowEncab.getColumnAt(14));
//                detalle.add(NullFormatter.formatBlank(result.getString(26)));
////                imp_ven = (new Double(rowEncab.getColumnAt(15))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(15),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(16))!=null) ? formatoMonto.formatNumber(result.getString(16),2,","):"&nbsp;");
////                cuo_mes = (new Double(rowEncab.getColumnAt(16))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(16),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(17))!=null) ? formatoMonto.formatNumber(result.getString(17),2,","):"&nbsp;");
////                int_bon = (new Double(rowEncab.getColumnAt(17))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(17),4,","):"&nbsp;";
//                detalle.add((new Double(result.getString(18))!=null) ? formatoMonto.formatNumber(result.getString(18),4,","):"&nbsp;");
////                tas_int = (new Double(rowEncab.getColumnAt(18))!=null) ? formatoMonto.formatNumber(rowEncab.getColumnAt(18),4,","):"&nbsp;";
//                detalle.add((new Double(result.getString(19))!=null) ? formatoMonto.formatNumber(result.getString(19),4,","):"&nbsp;");
////                tasa_mora = (new Double(rowEncab.getColumnAt(19))!=null)?formatoMonto.formatNumber(rowEncab.getColumnAt(19),2,","):"&nbsp;";
//                detalle.add((new Double(result.getString(20))!=null)?formatoMonto.formatNumber(result.getString(20),2,","):"&nbsp;");
////                periodo_fact = NullFormatter.formatBlank(rowEncab.getColumnAt(20));
//                detalle.add(NullFormatter.formatBlank(result.getString(21)));
////                tpo_tdc = NullFormatter.formatBlank(rowEncab.getColumnAt(24));
//                detalle.add(NullFormatter.formatBlank(result.getString(25)));
////                observaciones = NullFormatter.formatBlank(rowEncab.getColumnAt(25));
//                detalle.add(NullFormatter.formatBlank(result.getString(26)));
//
//            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            detalle.add("");
            detalle.add("");
            detalle.add("");
            detalle.add("");
            detalle.add("");
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            // throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public List<String> encabezadoMovimientos (List<String> parametros,VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.encabezadoMovimientos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        List<String> detalle=new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBT_TARJETA.PCK_TARJETA.PR_CARGAR_DETALLE_MOVIMIENTO(?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);
            CurrencyFormatter formatoMonto = new CurrencyFormatter();
            while (result.next()){

                if (null==statement.getString(2)){
                    detalle.add("0");
                }else{
                    detalle.add(CurrencyFormatter.formatNumber(statement.getString(2),2,","));
                }


                detalle.add(NullFormatter.formatBlank(result.getString(1)));

                detalle.add(NullFormatter.formatBlank(result.getString(2)));

                detalle.add(CurrencyFormatter.formatNumber(result.getString(3),2,","));

                detalle.add(NullFormatter.formatBlank(result.getString(4)));

                detalle.add(NullFormatter.formatBlank(result.getString(5)));

                detalle.add(NullFormatter.formatBlank(result.getString(6)));

                detalle.add(NullFormatter.formatBlank(result.getString(7)));

                detalle.add(NullFormatter.formatBlank(result.getString(8)));

                detalle.add(NullFormatter.formatBlank(result.getString(9)));

                detalle.add(CurrencyFormatter.formatNumber(result.getString(10),2,","));

                detalle.add(NullFormatter.formatBlank(result.getString(11)));

                detalle.add(CurrencyFormatter.formatNumber(result.getString(12),2,","));

                detalle.add(CurrencyFormatter.formatNumber(result.getString(13),2,","));

                detalle.add(NullFormatter.formatBlank(result.getString(14)));

            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }


    public String guardarPagoTDC(List<String> parametros,   VBTUsersOd usuario, ParametrosPersonalesOd parametrospersonales, String idioma) throws Exception {
        final String origen = "CreditCardsIo.guardarPagoTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        List<String> detalle=new ArrayList<String>();
        String respuesta=null;
        String tag=null;
        CurrencyFormatter formatoMonto = new CurrencyFormatter();
        String emails = new String();


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            String codigoBofa = obtenerNumeroReferencia(usuario);

            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_PAGOS_TARJETA.PR_GUARDAR_PAGO_TDC(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;");


            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));   //codcar
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.get(1));  //P_NRO_CTA

            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(2));  //P_NRO_DOC
            }

            if(parametros.get(3)==null){
                statement.setNull(4, OracleTypes.NULL);
            }else{
                statement.setString(4, parametros.get(3));  //P_TIPO_PAGO
            }

            if(parametros.get(4)==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, parametros.get(4).replace(",",""));  //P_MONTO
            }

            if(parametros.get(5)==null){
                statement.setNull(6, OracleTypes.NULL);
            }else{
                statement.setString(6, parametros.get(5));  //P_NRO_CTA_DEBITO
            }

            if(parametros.get(6)==null){
                statement.setNull(7, OracleTypes.NULL);
            }else{
                statement.setString(7, parametros.get(6).replace(",",""));    //P_BALANCE_TDC
            }
            if(codigoBofa==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8,codigoBofa);

            statement.registerOutParameter(9, OracleTypes.VARCHAR); // RESULTADO
            statement.registerOutParameter(10, OracleTypes.VARCHAR); // ETIQUETA DEL MENSAJE
            statement.registerOutParameter(14, OracleTypes.VARCHAR); // COD_PAGO

            if(usuario.getLogin()==null){
                statement.setNull(11, OracleTypes.NULL);
            }else{
                statement.setString(11, usuario.getLogin());
            }

            if(parametrospersonales.getMinimun_balance()==null){
                statement.setNull(12, OracleTypes.NULL);
            }else{
                statement.setString(12, parametrospersonales.getMinimun_balance());
            }

            if (usuario.getTipoContrato().equalsIgnoreCase("FC")){
                statement.setString(13,"C");
            }else
                statement.setString(13,"L");


            if(usuario.getNumContrato()==null){
                statement.setNull(15, OracleTypes.NULL);
            }else{
                statement.setString(15, usuario.getNumContrato());
            }

            statement.execute();

            respuesta = statement.getString(9);
            String codPago = new String();
            codPago = statement.getString(14);

            if (!respuesta.equalsIgnoreCase("OK")) {
                if (respuesta.equalsIgnoreCase("NO OK")) {
                    respuesta = statement.getString(10);
                    return respuesta;
                }
                else {
                    loggerIo.guardarExcepcion(usuario.getIP(), "Catch #32"+ "Error Procedimiento: VBT_TARJETA.PCK_PAGOS_TARJETA.PR_GUARDAR_PAGO_TDC", origen,"", usuario.getLogin(), usuario.getNumContrato());
                    throw (new Exception (respuesta,null));
                }
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                if (!usuario.getTipoContrato().equalsIgnoreCase("FC")){


                    /*REGISTRAR_PAGO_MOV_BOFA
                            (P_CODBOFA    IN VARCHAR2,
                                    P_CARTERA                   IN VARCHAR2,
                                    P_NUMERO_CUENTA_DEBITO      IN VARCHAR2,
                                    P_NUMCTA_TDC                    IN VARCHAR2,
                                    P_NUMDOC_TDC                   IN VARCHAR2,
                                    P_MONTO                     IN NUMBER,
                                    P_SALDO                     IN VARCHAR2,
                                    P_EMAIL_ORIGEN                     IN NUMBER,
                                    p_resultado                 OUT VARCHAR2) AS  */

                    statement  = connection.prepareCall ("begin PAGOSTDCHANDLER.REGISTRAR_PAGO_MOV_BOFA(?,?,?,?,?,?,?,?,?); end;");


                    if(codigoBofa==null)
                        statement.setNull(1,OracleTypes.NULL);
                    else
                        statement.setString(1,codigoBofa);

                    if(parametros.get(0)==null)
                        statement.setNull(2,OracleTypes.NULL);
                    else
                        statement.setString(2,parametros.get(0));

                    if(parametros.get(4)==null)
                        statement.setNull(3,OracleTypes.NULL);
                    else
                        statement.setString(3,parametros.get(5));
                    //                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));
                    if(parametros.get(1)==null)
                        statement.setNull(4,OracleTypes.NULL);
                    else
                        statement.setString(4,parametros.get(1));

                    if(parametros.get(2)==null)
                        statement.setNull(5,OracleTypes.NULL);
                    else
                        statement.setString(5,parametros.get(2));



                    if(parametros.get(4) ==null)
                        statement.setNull(6,OracleTypes.NULL);
                    else
                        statement.setString(6, parametros.get(4).replace(",",""));


                    if(parametros.get(6)==null){
                        statement.setNull(7, OracleTypes.NULL);
                    }else{
                        statement.setString(7, parametros.get(6).replace(",",""));    //P_BALANCE_TDC
                    }

                    if(usuario.getEmail()==null){
                        statement.setNull(8, OracleTypes.NULL);
                    }else{
                        statement.setString(8, usuario.getEmail()); //EMAIL_ORIGEN
                    }

                    statement.registerOutParameter(9, OracleTypes.VARCHAR);


                    statement.execute ();

                    respuesta = statement.getString(9);

                    if (!respuesta.equalsIgnoreCase("OK")) {
                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #33"+ "Error Procedimiento: PAGOSTDCHANDLER.REGISTRAR_PAGO_MOV_BOFA", origen,"", usuario.getLogin(), usuario.getNumContrato());
                        throw (new Exception (respuesta,null));
                    }
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                }
                else {

                    String emailPrincipales = emailsClientesPrincipales(usuario.getNumContrato(), usuario);

                      /*REGISTRAR_PAGO_MOV_BOFA_TMP
                           (P_CODBOFA    IN VARCHAR2,
                            P_CARTERA                   IN VARCHAR2,
                            P_NUMERO_CUENTA_DEBITO      IN VARCHAR2,
                            P_NUMCTA_TDC                    IN VARCHAR2,
                            P_NUMDOC_TDC                   IN VARCHAR2,
                            P_MONTO                     IN NUMBER,
                            P_SALDO                     IN VARCHAR2,
                            P_EMAIL_ORIGEN                     IN NUMBER,
                            p_resultado                 OUT VARCHAR2,
                            P_COD_PAGO                  IN VARCHAR2) AS  */

                    statement  = connection.prepareCall ("begin PAGOSTDCHANDLER.REGISTRAR_PAGO_MOV_BOFA_TMP(?,?,?,?,?,?,?,?,?,?); end;");

                    if(codigoBofa==null)
                        statement.setNull(1,OracleTypes.NULL);
                    else
                        statement.setString(1,codigoBofa);

                    if(parametros.get(0)==null)
                        statement.setNull(2,OracleTypes.NULL);
                    else
                        statement.setString(2,parametros.get(0));

                    if(parametros.get(4)==null)
                        statement.setNull(3,OracleTypes.NULL);
                    else
                        statement.setString(3,parametros.get(5));

                    if(parametros.get(1)==null)
                        statement.setNull(4,OracleTypes.NULL);
                    else
                        statement.setString(4,parametros.get(1));

                    if(parametros.get(2)==null)
                        statement.setNull(5,OracleTypes.NULL);
                    else
                        statement.setString(5,parametros.get(2));

                    if(parametros.get(4) ==null)
                        statement.setNull(6,OracleTypes.NULL);
                    else
                        statement.setString(6, parametros.get(4).replace(",",""));

                    if(parametros.get(6)==null){
                        statement.setNull(7, OracleTypes.NULL);
                    }else{
                        statement.setString(7, parametros.get(6).replace(",",""));    //P_BALANCE_TDC
                    }

                    if(emailPrincipales==null){
                        statement.setNull(8, OracleTypes.NULL);
                    }else{
                        statement.setString(8, emailPrincipales); //EMAIL_ORIGEN
                    }

                    if(codPago==null){
                        statement.setNull(10, OracleTypes.NULL);
                    }else{
                        statement.setString(10, codPago); //COD_PAGO
                    }

                    statement.registerOutParameter(9, OracleTypes.VARCHAR);

                    statement.execute ();

                    respuesta = statement.getString(9);

                    if (!respuesta.equalsIgnoreCase("OK")) {
                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #34"+ "Error Procedimiento: PAGOSTDCHANDLER.REGISTRAR_PAGO_MOV_BOFA_TMP", origen,"", usuario.getLogin(), usuario.getNumContrato());
                        throw (new Exception (respuesta,null));
                    }
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                }

            }

            connection.commit();

            MailManager365 mailManager = new MailManager365("vbtonline");

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
            SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));

            String etiqueta = new String();
            if(idioma.equalsIgnoreCase("_us_en")){
                // if(usuario.getTipo().equals("6"))
                if(usuario.getTipoContrato().equalsIgnoreCase("FC"))

                    etiqueta = "Input";
                else
                    etiqueta = "In Process";

            }else{
                // if(usuario.getTipo().equals("6"))
                if(usuario.getTipoContrato().equalsIgnoreCase("FC"))
                    etiqueta = "Cargada";
                else
                    etiqueta = "Liberada";
            }

            String pago = CurrencyFormatter.formatNumber(parametros.get(4),2,","); //Formatear monto

            String htmlText = "";
            String htmlText2 = "";
            String htmlText3 = "";

            //******** Envía correo de notificación de pago de tarjeta al cliente que monta la orden de pago
            try{
                if(usuario.getTipoContrato().equalsIgnoreCase("FC")){
                    //Usuario Cargador de Firmas Conjuntas
                    htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailPagoTdcFC")  +
                            "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                            "\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": " + pago +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento1FC") +
                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento2FC") +
                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento3FC") +
                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";


                    //Correo que se le envia a los Usuarios Aprobadores
                    htmlText3= ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBackOfficePagoTDCFC") +
                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": " + pago +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento1FC") +
                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";
                }else{
                    //Cliente Full Acceso
                    htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailPagoTdc")  +
                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": " + pago +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";
                }
                //******** Envía correo de notificación de la orden de pago de TDC al asesor, ejecutivo y asistentes
                htmlText2= ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgEmailBackOfficePagoTDC") +
                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
                        "\n" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAGMontoPagoTDC") + ": " + pago +
                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+"_ve_es").getString("TAGEstatusPagoTDC") + ": " + etiqueta+"\n\n\n";

            } catch (Exception ex) {
                logger.error(ex);
                loggerIo.guardarExcepcion(usuario.getIP(), "Catch #22" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                //throw (new Exception(ex.getMessage(), null));
                respuesta = "ERROR_EMAIL";
                return (respuesta);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

            /*Envio los correos */
            try {
//                if (usuario.getTipo().equals("2")) {
                //parametros.get(5) == 'numero de cuenta debito'
                //                emails = EmailsInternos(parametros.get(5), usuario);
                //                if (!emails.equalsIgnoreCase(""))
                //                  mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emails, ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailNotificacionPagoTdc"), htmlText2);
                //                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailNotificacionPagoTdc"), htmlText2);
//                }

                if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {

                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdcFC"), htmlText);
                    emails = EmailsAprobadores(usuario.getNumContrato(), "7", usuario);

                    if (!emails.equalsIgnoreCase(""))
                        mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emails, ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailNotificacionPagoTdc"), htmlText3);

                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo : " + (emails + ", " + usuario.getEmail() + " motivado a Pagos TDC  Referencia Nro: " + codigoBofa).replaceAll(",", ", "));

                } else {

                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdc"), htmlText);
                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc"), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdc"), htmlText2);
                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (usuario.getEmail() + ", " + ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc") + " motivado a pago de TDC Referencia Nro: " + codigoBofa).replaceAll(",", ", "));
                }

            } catch (Exception ex) {
                logger.error(ex);
                loggerIo.guardarExcepcion(usuario.getIP(), "Catch #30" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                //throw (new Exception(ex.getMessage(), null));
                respuesta = "ERROR_EMAIL";
                return (respuesta);
            }

        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            connection.rollback();
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String EmailsInternos (String cuenta, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.EmailsInternos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        String correos = new String();
        Integer existe=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.emails_internos_pr(?,?,?); end;");

            if(cuenta==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, cuenta);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);



            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }

    public String EmailsInternosCodcar (String codcar, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.EmailsInternosCodcar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        String correos = new String();
        Integer existe=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.emails_internos_codcar_pr(?,?,?); end;");

            if(codcar==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, codcar);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);



            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }

    public String EmailsAprobadores (String contrato, String tipo, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.EmailsAprobadores";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String correos = new String();
        long time = 0;

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.ConsultarAprobadoresContrato(?,?,?,?); end;");

            if(contrato==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, contrato);

            if(tipo==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2, tipo);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);



            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }

    public String obtenerNumeroReferencia (VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.obtenerNumeroReferencia";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String numReferencia = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.tra_numreferencia_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            numReferencia = statement.getString(1);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
        }

        return (numReferencia);
    }

    public TableOd consultPaymentsTDC(String carteras, String Status, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardIo.consultPaymentsTdc";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_PAGOS_TARJETA.PR_CONSULTA_PAGOS_X_ESTATUS(?,?,?,?); end;");

            if(carteras == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,carteras);

            if (Status== null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,Status);

            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject(4);

            List<List<String>> bodys=new ArrayList();

            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add("<div id='div_"+result.getString(1)+"'><input type='checkbox' class='datos verificarSelecccionPagosTDC' name='"+result.getInt(1) +
                        "|"+result.getString(3) +
                        "|"+result.getString(4)+
                        "|"+result.getString(5) +
                        "|"+result.getString(6) +
                        "|"+result.getString(7) +
                        "|"+CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(8)), 2, ",") +
                        "|"+ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoStatusTDCPago"+(result.getString(9)).trim()) +
                        "|"+ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoPagoTDC"+(result.getString(10)).trim()) +
                        "|"+result.getString(11)
                        +"' value='0' id='"+result.getString(1) +"' onclick='cambiarValorPayment(this.id)' /></div>");
                body.add(result.getString(5));
                body.add(result.getString(1));
                body.add(result.getString(3));
                body.add(result.getString(4));
                body.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoPagoTDC"+(result.getString(10)).trim()));
                body.add(CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(8)), 2, ","));
                body.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tipoStatusTDCPago"+(result.getString(9)).trim()));
                body.add(NullFormatter.formatBlank(result.getString(6)));
                body.add("<img class='detalle_resumen_pago_tdc' id='detalle_"+result.getString(1)+"' width='20' src='../vbtonline/resources/images/search.png' alt='' />");
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGFechaPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGCodigoPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tdcconsultaedocta_TAGNumeroTarjeta"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("misdatos_TAGCodigoCartera"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGTipoPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGStatusTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGFechaProcesoTDC"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Detalle_detalle"));

            //
            //header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGFechaProcesoTDC"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public String validarLimiteSalvis (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String mensaje="";
        String respuesta = "";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_LIMITE_CREDITO_SALVIS (?,?,?,?,?); END;");

            statement.setString(2, parametros.get(0));
            statement.setString(3, parametros.get(1));
            statement.setString(1, parametros.get(2));
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);
            mensaje = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK")) {

                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (mensaje);
    }

    public List<String> cargarEstatusTDCCLE (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "CreditCardsIo.cargarEstatusTDCCLE";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long  time = 0, time2 = 0, time3 = 0;
        String respuesta = "";
        String bloqueo = "";

        List<String> detalle=new ArrayList<String>();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_GET_ESTATUS_EMERGENCIA(?,?,?,?,?,?); END;");

            if(parametros.get(2)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(2));
            }

            if(parametros.get(1)==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, parametros.get(1).split("_")[0]);
            }

            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(1).split("_")[2]);
            }

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(6);

            if (!respuesta.startsWith("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            result =  (ResultSet) statement.getObject(4);
            bloqueo = NullFormatter.formatBlank(statement.getString(5));

            while (result.next()) {

                detalle.add(NullFormatter.formatBlank(result.getString(1)));
                detalle.add(NullFormatter.formatBlank(result.getString(2)));
                if(idioma.equalsIgnoreCase("_ve_es"))
                    detalle.add(NullFormatter.formatBlank(result.getString(3)));
                else
                    detalle.add(NullFormatter.formatBlank(result.getString(4)));
                detalle.add(NullFormatter.formatBlank(result.getString(5)));
            }

            if(bloqueo.equalsIgnoreCase("B")) {
                detalle.add("B");
                detalle.add("");
                detalle.add("");
                detalle.add("");
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

        }
        catch (Exception ex) {

            logger.error(ex,ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }


    public String GuardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception {
        final String origen = "CreditCardsIo.GuardarLogFC";
        String respuesta="";
        Connection connectionLog = null;
        ResultSet result = null;
        CallableStatement statementLog = null;

        try{

            connectionLog=getConnection();
            statementLog = connectionLog.prepareCall("begin vbtonline.vbt_logActionFC_pr(?,?,?,?,?,?,?,?,?); end;");

            statementLog.setString(1, parametro1);
            statementLog.setString(2, parametro2);
            statementLog.setString(3, parametro3);
            statementLog.setString(4, parametro4);
            statementLog.setString(5, parametro5);
            statementLog.setString(6, parametro6);
            statementLog.setString(7, parametro7);
            statementLog.setString(8, parametro8);
            statementLog.registerOutParameter(9, OracleTypes.VARCHAR);
            statementLog.execute();

            respuesta = statementLog.getString(9);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connectionLog, statementLog, result);
        }

        return (respuesta);
    }

    public String llamarServicioBtrans(List<String> parametros, VBTUsersOd usuario)throws Exception {
        final String origen = "CreditCardsIo.llamarServicioBtrans";
        String respuesta=null;
        try {

            Socket cliente;
            DataOutputStream outStr;
            DataInputStream inStr;
            String datos;
            StringBuffer mensaje = new StringBuffer();

            cliente = new Socket(ResourceBundle.getBundle("vbtonline").getString("servidorBetransPagos"), Integer.parseInt(ResourceBundle.getBundle("vbtonline").getString("puertoBetransPagos")));
            outStr = new DataOutputStream(cliente.getOutputStream());
            inStr = new DataInputStream(cliente.getInputStream());

            try {

                mensaje.append(parametros.get(0) + "," + parametros.get(1).substring(4) + "," + parametros.get(2)+ "," + parametros.get(3)+ "," + parametros.get(4)+ "," + parametros.get(5));


                outStr.writeUTF(mensaje.toString());
                respuesta = inStr.readUTF();

                if (respuesta.split("\\|")[0].equalsIgnoreCase("OK")) {
                    respuesta = "SUCCESS";
                } else {
                    respuesta = "NO OK";
                    loggerIo.guardarExcepcion(usuario.getIP(), "Servicio de bloqueo no disponible", origen, "Servicio de bloqueo no disponible", usuario.getLogin(), usuario.getNumContrato());
                }

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                respuesta ="NO OK";
                loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            }finally{
                inStr.close();
                outStr.close();
                cliente.close();
            }

        }catch (Exception ex) {
            respuesta ="NO OK";
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }

        return (respuesta);

    }

    /*public String cambiarEstatusCLE(List<String> parametros, VBTUsersOd usuario, String idioma, boolean servicio) throws Exception {
        final String origen = "CreditCardsIo.cambiarEstatusCLE";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        String emails = new String();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            Socket cliente;
            DataOutputStream outStr;
            DataInputStream inStr;
            String datos;
            StringBuffer mensaje = new StringBuffer();

            cliente =null;
            outStr =null;
            inStr =null;




                if(servicio) {
                    try {
                    cliente = new Socket(ResourceBundle.getBundle("vbtonline").getString("servidorBetransPagos"), Integer.parseInt(ResourceBundle.getBundle("vbtonline").getString("puertoBetransPagos")));
                    outStr = new DataOutputStream(cliente.getOutputStream());
                    inStr = new DataInputStream(cliente.getInputStream());

                    /*datos = NullFormatter.isBlank(parametros.get(4)) ? parametros.get(8): parametros.get(4);

                    /*mensaje.append(parametros.get(4) + "," + parametros.get(2).substring(4) + "," + parametros.get(5));

                    outStr.writeUTF(mensaje.toString());
                    respuesta = inStr.readUTF();

                if (respuesta.split("\\|")[0].equalsIgnoreCase("OK")) {

                        connection = getConnection();
                        /**
                         * SE LLAMA A BLOQUEO DE EMERGENCIA DE TDC el 04/08/2021
                         /*
                        statement = connection.prepareCall("begin VBT_TARJETA.PCK_TARJETA.PR_BLOQUEO_EMERGENCIA(?,?,?,?,?,?,?,?); end;");

                        /*
                        I_CODCAR        IN     VARCHAR2,
                        I_NRO_DOC       IN     VARCHAR2,
                        I_NRO_CTA       IN     VARCHAR2,
                        I_USER          IN     VARCHAR2,
                        I_TIPO_BLOQUEO  IN     VARCHAR2,
                        I_PROCESO       IN     VARCHAR2,
                        O_MENSAJE       OUT    VARCHAR2

                        /*
                        if (parametros.get(1) == null) {
                            statement.setNull(1, OracleTypes.NULL);
                        } else {
                            statement.setString(1, parametros.get(1));
                        }

                        if (parametros.get(2) == null) {
                            statement.setNull(2, OracleTypes.NULL);
                        } else {
                            statement.setString(2, parametros.get(2));
                        }

                        if (parametros.get(0) == null) {
                            statement.setNull(3, OracleTypes.NULL);
                        } else {
                            statement.setString(3, parametros.get(0));
                        }

                        if (usuario.getLogin() == null) {
                            statement.setNull(4, OracleTypes.NULL);
                        } else {
                            statement.setString(4, usuario.getLogin());
                        }

                        if (parametros.get(3) == null) {
                            statement.setNull(5, OracleTypes.NULL);
                        } else {
                            statement.setString(5, parametros.get(3));
                        }

                        statement.setString(6, "BLQ");

                        statement.registerOutParameter(7, OracleTypes.VARCHAR);

                        if (parametros.get(5) == null) {
                            statement.setNull(8, OracleTypes.NULL);
                        } else {
                            statement.setString(8, parametros.get(5));
                        }

                        statement.execute();

                        respuesta = statement.getString(7);

                        if (!respuesta.equalsIgnoreCase("OK"))
                            throw (new Exception(respuesta, null));
                        else {
                            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + CreditCardsIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                            MailManager365 mailManager = new MailManager365("vbtonline");

                            String htmlText = "";

                            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ve".toUpperCase()));
                            SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a", new Locale("es", "ve".toUpperCase()));

                            String etiqueta = new String();

                            //******** Envía correo de notificación de pago de tarjeta al cliente que monta la orden de pago
                            try {
                                if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {
                                    //Usuario Cargador de Firmas Conjuntas
                                    /*htmlText = "PRUEBA DE CORREO " + formatoFecha.format(new Date());
                                    /*
                                    htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + ": " + parametros.get(2) + " " +
                                            ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") + " " + parametros.get(7) + " " +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGNumeroTarjetaCredito") + ": " + parametros.get(2)  +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";


                                    //Correo que se le envia a los Usuarios Aprobadores
                                    /*htmlText3 = "PRUEBA DE CORREO " + formatoFecha.format(new Date());
                                    /*htmlText3= ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBackOfficePagoTDCFC") +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
                                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": " +
                                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
                                            "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
                                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento1FC") +
                                            "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";

                                } else {
                                    //Cliente Full Acceso


                                    htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + ": " + parametros.get(2) + " " +
                                            ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") + " " + parametros.get(7) + " " +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGNumeroTarjetaCredito") + ": " + parametros.get(2)  +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";

                                }
                                //******** Envía correo de notificación de la orden de pago de TDC al asesor, ejecutivo y asistentes
                                htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + ": " + parametros.get(2) + " " +
                                        ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") + " " + parametros.get(7) + " " +
                                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGNumeroTarjetaCredito") + ": " + parametros.get(2)  +
                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";

                            } catch (Exception ex) {
                                logger.error(ex);
                                loggerIo.guardarExcepcion(usuario.getIP(), "Catch #22" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                                //throw (new Exception(ex.getMessage(), null));
                                respuesta = "ERROR_EMAIL";
                            }

                            time = System.currentTimeMillis() - time;

                            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + CreditCardsIo.class + " | " + origen);

                            /*Envio los correos
                            try {

                                if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {

                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);
                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc"),  ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);

                                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo : " + (emails + ", " + usuario.getEmail() + " motivado a Pagos TDC  Referencia Nro: ").replaceAll(",", ", "));

                                } else {

                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(),  ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);
                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc"),  ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);
                                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (usuario.getEmail() + ", " + ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc") + " Prueba de Bloqueo CORREO ").replaceAll(",", ", "));
                                }

                            } catch (Exception ex) {
                                logger.error(ex);
                                loggerIo.guardarExcepcion(usuario.getIP(), "Catch #30" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                                respuesta = "ERROR_EMAIL";
                            }


                        }




                        } else {
                            respuesta = "NO OK";
                            loggerIo.guardarExcepcion(usuario.getIP(), "Servicio de bloqueo no disponible", origen, "Servicio de bloqueo no disponible", usuario.getLogin(), usuario.getNumContrato());
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        respuesta ="NO OK";
                        loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                    }finally{
                        inStr.close();
                        outStr.close();
                        cliente.close();
                    }
                }else{
                    connection = getConnection();
                    /**
                     * SE LLAMA A BLOQUEO DE EMERGENCIA DE TDC el 04/08/2021

                    statement = connection.prepareCall("begin VBT_TARJETA.PCK_TARJETA.PR_BLOQUEO_EMERGENCIA(?,?,?,?,?,?,?,?); end;");

                        /*
                        I_CODCAR        IN     VARCHAR2,
                        I_NRO_DOC       IN     VARCHAR2,
                        I_NRO_CTA       IN     VARCHAR2,
                        I_USER          IN     VARCHAR2,
                        I_TIPO_BLOQUEO  IN     VARCHAR2,
                        I_PROCESO       IN     VARCHAR2,
                        O_MENSAJE       OUT    VARCHAR2


                    if (parametros.get(1) == null) {
                        statement.setNull(1, OracleTypes.NULL);
                    } else {
                        statement.setString(1, parametros.get(1));
                    }

                    if (parametros.get(2) == null) {
                        statement.setNull(2, OracleTypes.NULL);
                    } else {
                        statement.setString(2, parametros.get(2));
                    }

                    if (parametros.get(0) == null) {
                        statement.setNull(3, OracleTypes.NULL);
                    } else {
                        statement.setString(3, parametros.get(0));
                    }

                    if (usuario.getLogin() == null) {
                        statement.setNull(4, OracleTypes.NULL);
                    } else {
                        statement.setString(4, usuario.getLogin());
                    }

                    if (parametros.get(3) == null) {
                        statement.setNull(5, OracleTypes.NULL);
                    } else {
                        statement.setString(5, parametros.get(3));
                    }

                    statement.setString(6, "ANL");

                    statement.registerOutParameter(7, OracleTypes.VARCHAR);

                    statement.setNull(8, OracleTypes.NULL);


                    statement.execute();

                    respuesta = statement.getString(7);

                    if (!respuesta.equalsIgnoreCase("OK"))
                        throw (new Exception(respuesta, null));
                    else {

                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + CreditCardsIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                        MailManager365 mailManager = new MailManager365("vbtonline");

                        String htmlText = "";

                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ve".toUpperCase()));
                        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a", new Locale("es", "ve".toUpperCase()));

                        String etiqueta = new String();

                        //******** Envía correo de notificación de pago de tarjeta al cliente que monta la orden de pago
                        try {
                                if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {
                                    //Usuario Cargador de Firmas Conjuntas
                                    /*htmlText = "PRUEBA DE CORREO " + formatoFecha.format(new Date());

                                    htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + ": " + parametros.get(2) + " " +
                                            ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") + " " + parametros.get(7) + " " +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGNumeroTarjetaCredito") + ": " + parametros.get(2)  +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";

                                } else {
                                    //Cliente Full Acceso
                                    /*htmlText = "PRUEBA DE CORREO " + formatoFecha.format(new Date());

                                    htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + ": " + parametros.get(2) + " " +
                                            ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") + " " + parametros.get(7) + " " +
                                            "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                            "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGNumeroTarjetaCredito") + ": " + parametros.get(2)  +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                                            "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";

                                }
                                //******** Envía correo de notificación de la orden de pago de TDC al asesor, ejecutivo y asistentes
                                htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + ": " + parametros.get(2) + " " +
                                        ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") + " " + parametros.get(7) + " " +
                                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGNumeroTarjetaCredito") + ": " + parametros.get(2)  +
                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";

                            } catch (Exception ex) {
                            logger.error(ex);
                            loggerIo.guardarExcepcion(usuario.getIP(), "Catch #22" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                            //throw (new Exception(ex.getMessage(), null));
                            respuesta = "ERROR_EMAIL";
                        }

                        time = System.currentTimeMillis() - time;

                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + CreditCardsIo.class + " | " + origen);

                        /*Envio los correos
                        try {

                            if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {

                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdcFC"), htmlText);
                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc"), " Prueba de Bloqueo CORREO ", htmlText);

                                loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo : " + (emails + ", " + usuario.getEmail() + " motivado a Pagos TDC  Referencia Nro: ").replaceAll(",", ", "));

                            } else {

                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), " Prueba de Bloqueo CORREO ", htmlText);
                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc"), " Prueba de Bloqueo CORREO ", htmlText);
                                loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (usuario.getEmail() + ", " + ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc") + " Prueba de Bloqueo CORREO ").replaceAll(",", ", "));
                            }

                        } catch (Exception ex) {
                            logger.error(ex);
                            loggerIo.guardarExcepcion(usuario.getIP(), "Catch #30" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                            respuesta = "ERROR_EMAIL";
                        }
                    }

                }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);
        }catch (Exception ex) {
            logger.error(ex);

            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
        }finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }*/

    public String cambiarEstatusCLE(List<String> parametros, VBTUsersOd usuario, String idioma, boolean servicio) throws Exception {
        final String origen = "CreditCardsIo.cambiarEstatusCLE";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        String emails = new String();
        String codigoBloqueo = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            Socket cliente;
            DataOutputStream outStr;
            DataInputStream inStr;
            String datos;
            StringBuffer mensaje = new StringBuffer();

            cliente =null;
            outStr =null;
            inStr =null;

            if(servicio) {

                connection = getConnection();
                connection.setAutoCommit(false);

                /**
                 * SE LLAMA A BLOQUEO DE EMERGENCIA DE TDC el 04/08/2021
                 */
                statement = connection.prepareCall("begin VBT_TARJETA.PCK_TARJETA.PR_BLOQUEO_EMERGENCIA(?,?,?,?,?,?,?,?,?); end;");

                /*
                I_CODCAR        IN     VARCHAR2,
                I_NRO_DOC       IN     VARCHAR2,
                I_NRO_CTA       IN     VARCHAR2,
                I_USER          IN     VARCHAR2,
                I_TIPO_BLOQUEO  IN     VARCHAR2,
                I_PROCESO       IN     VARCHAR2,
                O_MENSAJE       OUT    VARCHAR2,
                O_CODBLOQUEO    OUT    VARCHAR2
                */

                if (parametros.get(1) == null) {
                    statement.setNull(1, OracleTypes.NULL);
                } else {
                    statement.setString(1, parametros.get(1));
                }

                if (parametros.get(2) == null) {
                    statement.setNull(2, OracleTypes.NULL);
                } else {
                    statement.setString(2, parametros.get(2));
                }

                if (parametros.get(0) == null) {
                    statement.setNull(3, OracleTypes.NULL);
                } else {
                    statement.setString(3, parametros.get(0));
                }

                if (usuario.getLogin() == null) {
                    statement.setNull(4, OracleTypes.NULL);
                } else {
                    statement.setString(4, usuario.getLogin());
                }

                if (parametros.get(3) == null) {
                    statement.setNull(5, OracleTypes.NULL);
                } else {
                    statement.setString(5, parametros.get(3));
                }

                statement.setString(6, "BLQ");

                statement.registerOutParameter(7, OracleTypes.VARCHAR);

                if (parametros.get(5) == null) {
                    statement.setNull(8, OracleTypes.NULL);
                } else {
                    statement.setString(8, parametros.get(5));
                }

                statement.registerOutParameter(9, OracleTypes.VARCHAR);

                statement.execute();

                respuesta = statement.getString(7);

                codigoBloqueo = statement.getString(9);

                if (!respuesta.equalsIgnoreCase("OK")){
                    connection.rollback();
                    throw (new Exception(respuesta, null));
                }else {
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + CreditCardsIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                    MailManager365 mailManager = new MailManager365("vbtonline");

                    String htmlText = "";

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ve".toUpperCase()));
                    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a", new Locale("es", "ve".toUpperCase()));

                    String etiqueta = new String();

                    //******** Envía correo de notificación de pago de tarjeta al cliente que monta la orden de pago
                    try {
                        if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {
                            //Usuario Cargador de Firmas Conjuntas
                            /*htmlText = "PRUEBA DE CORREO " + formatoFecha.format(new Date());     */

                            htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                    "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + parametros.get(2).substring(parametros.get(2).length()-7) + " " +
                                    ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") +
                                    "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc3") + " " + parametros.get(7) + " " +
                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc4") + ": " + parametros.get(1) +
                                    "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgTDCLOCK1") +
                                    "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgTDCLOCK2")+"\n\n\n";
                        } else {

                            htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                    "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + parametros.get(2).substring(parametros.get(2).length()-7) + " " +
                                    ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") +
                                    "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc3") + " " + parametros.get(7) + " " +
                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc4") + ": " + parametros.get(1) +

                                    "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgTDCLOCK1") +
                                    "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgTDCLOCK2")+"\n\n\n";
                        }
                        //******** Envía correo de notificación de la orden de pago de TDC al asesor, ejecutivo y asistentes
                        htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBloqueoTdc")  +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc1") + parametros.get(2).substring(parametros.get(2).length()-7) + " " +
                                ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc2") +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc3") + " " + parametros.get(7) + " " +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgClienteBloqueoTdc4") + ": " + parametros.get(1)+
                                "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgTDCLOCK1") +
                                "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgTDCLOCK2")+"\n\n\n";

                    } catch (Exception ex) {
                        logger.error(ex);
                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #22" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                        respuesta = "ERROR_EMAIL";
                    }

                    time = System.currentTimeMillis() - time;

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + CreditCardsIo.class + " | " + origen);

                    try {
                        cliente = new Socket(ResourceBundle.getBundle("vbtonline").getString("servidorBetransPagos"), Integer.parseInt(ResourceBundle.getBundle("vbtonline").getString("puertoBetransPagos")));
                        outStr = new DataOutputStream(cliente.getOutputStream());
                        inStr = new DataInputStream(cliente.getInputStream());

                        /*datos = NullFormatter.isBlank(parametros.get(4)) ? parametros.get(8): parametros.get(4);   */

                        mensaje.append(parametros.get(4) + "," + parametros.get(2).substring(4) + "," + parametros.get(5)+ ","+ parametros.get(1) +"," + codigoBloqueo+ ",BE");

                        outStr.writeUTF(mensaje.toString());
                        respuesta = inStr.readUTF();

                        if (!respuesta.split("\\|")[0].equalsIgnoreCase("OK")) {
                            respuesta = "NO OK";
                            connection.rollback();
                            loggerIo.guardarExcepcion(usuario.getIP(), "Servicio de bloqueo no disponible", origen, "Servicio de bloqueo no disponible", usuario.getLogin(), usuario.getNumContrato());
                        }else{
                            /*Envio los correos */
                            try {

                                String emailInternos = new String();
                                emailInternos = "";
                                //emailInternos = EmailsInternos(parametros.get(1), usuario);

                                if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {

                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);
                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos+",altinads@vbtbank.com,fcolina@vbtbank.com,mbernot@vbtbank.com,rafaeln@vbtbank.com",  ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);

                                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (usuario.getEmail() + ", " +emailInternos+",altinads@vbtbank.com,fcolina@vbtbank.com,mbernot@vbtbank.com,rafaeln@vbtbank.com"+ ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc")).replaceAll(",", ", "));

                                } else {

                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(),  ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);
                                    mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos+",altinads@vbtbank.com,fcolina@vbtbank.com,mbernot@vbtbank.com,rafaeln@vbtbank.com",  ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc"), htmlText);
                                    loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (usuario.getEmail() + ", " + emailInternos+",altinads@vbtbank.com,fcolina@vbtbank.com,mbernot@vbtbank.com,rafaeln@vbtbank.com" + ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailBloqueoETdc")).replaceAll(",", ", "));
                                }

                            } catch (Exception ex) {
                                logger.error(ex);
                                loggerIo.guardarExcepcion(usuario.getIP(), "Catch #30" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                                respuesta = "ERROR_EMAIL";
                            }
                        }

                    }catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        respuesta ="NO OK";
                        connection.rollback();
                        loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                    }finally{
                        inStr.close();
                        outStr.close();
                        cliente.close();
                    }
                }
            }else{
                connection = getConnection();
                connection.setAutoCommit(false);
                /**
                 * SE LLAMA A BLOQUEO DE EMERGENCIA DE TDC el 04/08/2021
                 */
                statement = connection.prepareCall("begin VBT_TARJETA.PCK_TARJETA.PR_BLOQUEO_EMERGENCIA(?,?,?,?,?,?,?,?,?); end;");

                        /*
                        I_CODCAR        IN     VARCHAR2,
                        I_NRO_DOC       IN     VARCHAR2,
                        I_NRO_CTA       IN     VARCHAR2,
                        I_USER          IN     VARCHAR2,
                        I_TIPO_BLOQUEO  IN     VARCHAR2,
                        I_PROCESO       IN     VARCHAR2,
                        O_MENSAJE       OUT    VARCHAR2
                        */

                if (parametros.get(1) == null) {
                    statement.setNull(1, OracleTypes.NULL);
                } else {
                    statement.setString(1, parametros.get(1));
                }

                if (parametros.get(2) == null) {
                    statement.setNull(2, OracleTypes.NULL);
                } else {
                    statement.setString(2, parametros.get(2));
                }

                if (parametros.get(0) == null) {
                    statement.setNull(3, OracleTypes.NULL);
                } else {
                    statement.setString(3, parametros.get(0));
                }

                if (usuario.getLogin() == null) {
                    statement.setNull(4, OracleTypes.NULL);
                } else {
                    statement.setString(4, usuario.getLogin());
                }

                if (parametros.get(3) == null) {
                    statement.setNull(5, OracleTypes.NULL);
                } else {
                    statement.setString(5, parametros.get(3));
                }

                statement.setString(6, "ANL");

                statement.registerOutParameter(7, OracleTypes.VARCHAR);

                statement.setNull(8, OracleTypes.NULL);

                statement.registerOutParameter(9, OracleTypes.VARCHAR);

                statement.execute();

                respuesta = statement.getString(7);

                if (!respuesta.equalsIgnoreCase("OK")) {
                    connection.rollback();
                    throw (new Exception(respuesta, null));
                }else {

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + CreditCardsIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                    MailManager365 mailManager = new MailManager365("vbtonline");

                    String htmlText = "";

                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "ve".toUpperCase()));
                    SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a", new Locale("es", "ve".toUpperCase()));

                    String etiqueta = new String();

                    try {
                        //******** Envía correo de notificación de la orden de pago de TDC al asesor, ejecutivo y asistentes
                        htmlText=ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgEmailBloqueoTdcA")  +
                                "\n\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgClienteBloqueoTdc1") + parametros.get(2).substring(parametros.get(2).length()-7) + " " +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                                "\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgClienteBloqueoTdc4") + ": " + parametros.get(1) ;


                    } catch (Exception ex) {
                        logger.error(ex);
                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #22" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                        respuesta = "ERROR_EMAIL";
                    }

                    time = System.currentTimeMillis() - time;

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + CreditCardsIo.class + " | " + origen);

                    /*Envio los correos */
                    try {
                        String emailInternos = new String();
                        emailInternos = "";
                        emailInternos = EmailsInternos(parametros.get(1), usuario);

                        mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos+",altinads@vbtbank.com,fcolina@vbtbank.com,mbernot@vbtbank.com,rafaeln@vbtbank.com", ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgEmailBloqueoETdcA"), htmlText);
                        loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + emailInternos+",altinads@vbtbank.com,fcolina@vbtbank.com,mbernot@vbtbank.com,rafaeln@vbtbank.com" + " "+ ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgEmailBloqueoETdcA").replaceAll(",", ", "));

                    } catch (Exception ex) {
                        logger.error(ex);
                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #30" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                        respuesta = "ERROR_EMAIL";
                    }
                }

            }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);

            connection.commit();

        }catch (Exception ex) {
            logger.error(ex);
            connection.rollback();
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
        }finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public SelectOd cargarTarjetasTDCCLE (String carteras, String idioma, VBTUsersOd usersOd) throws Exception {
        final String origen = "CreditCardsIo.cargarTarjetasTDCCLE";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor, bloqueo;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CARGAR_TARJETAS(?,?,?,?,?); END;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.setString(4, "BE");

            statement.setString(5, "");

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = new String();
                label  = new String();
                bloqueo = new String();

                valor = NullFormatter.formatBlank(result.getString(1));
                label  = NullFormatter.formatBlank(result.getString(3)) + " (" + ResourceBundle.getBundle("TdcMovimientosxFacturar"+idioma).getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";
                bloqueo  =  NullFormatter.formatBlank(result.getString(4));

                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuenta.setExtra(bloqueo);
                cuentas.add(cuenta);

                cuenta=new ContentSelectOd();

            }

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

//    public String solitaAnulacionTdcCLE(List<String> parametros, VBTUsersOd usuario, String idioma) throws Exception {
//        final String origen = "CreditCardsIo.cambiarEstatusCLE";
//        Connection connection = null;
//        CallableStatement statement = null;
//        ResultSet result = null;
//        long fila = 0, time = 0, time2 = 0, time3 = 0;
//        String respuesta=null;
//        String emails = new String();
//
//        try {
//
//            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ CreditCardsIo.class+" | "+origen);
//
//            time = System.currentTimeMillis ();
//
//
//            try {
//
//                connection=getConnection();
//                /**
//                 * SE LLAMA A BLOQUEO DE EMERGENCIA DE TDC el 04/08/2021
//                 */
//                statement  = connection.prepareCall ("begin VBT_TARJETA.PCK_TARJETA.PR_BLOQUEO_EMERGENCIA(?,?,?,?,?,?,?); end;");
//
//                    /*
//                    I_CODCAR        IN     VARCHAR2,
//                    I_NRO_DOC       IN     VARCHAR2,
//                    I_NRO_CTA       IN     VARCHAR2,
//                    I_USER          IN     VARCHAR2,
//                    I_TIPO_BLOQUEO  IN     VARCHAR2,
//                    I_PROCESO       IN     VARCHAR2,
//                    O_MENSAJE       OUT    VARCHAR2
//                    */
//
//                if(parametros.get(1)==null){
//                    statement.setNull(1, OracleTypes.NULL);
//                }else{
//                    statement.setString(1, parametros.get(1));
//                }
//
//                if(parametros.get(2)==null){
//                    statement.setNull(2, OracleTypes.NULL);
//                }else{
//                    statement.setString(2, parametros.get(2));
//                }
//
//                if(parametros.get(0)==null){
//                    statement.setNull(3, OracleTypes.NULL);
//                }else{
//                    statement.setString(3, parametros.get(0));
//                }
//
//                if( usuario.getLogin() ==null){
//                    statement.setNull(4, OracleTypes.NULL);
//                }else{
//                    statement.setString(4, usuario.getLogin());
//                }
//
//                if(parametros.get(3)==null){
//                    statement.setNull(5, OracleTypes.NULL);
//                }else{
//                    statement.setString(5, parametros.get(3));
//                }
//
//                statement.setString(6, "ANL");
//
//                statement.registerOutParameter(7, OracleTypes.VARCHAR);
//
//                statement.execute ();
//
//                respuesta = statement.getString(7);
//
//                if (!respuesta.equalsIgnoreCase("OK"))
//                    throw (new Exception (respuesta,null));
//                else {
//                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + CreditCardsIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
//
//                    MailManager365 mailManager = new MailManager365("vbtonline");
//
//                    String htmlText = "";
//                    String htmlText2 = "";
//                    String htmlText3 = "";
//
//                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
//                    SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));
//
//                    String etiqueta = new String();
//
//                    //******** Envía correo de notificación de pago de tarjeta al cliente que monta la orden de pago
//                    try{
//                        if(usuario.getTipoContrato().equalsIgnoreCase("FC")){
//                            //Usuario Cargador de Firmas Conjuntas
//                            htmlText=" ";
//                                /*htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailPagoTdcFC")  +
//                                        "\n\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
//                                        "\n" + ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": " +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
//                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
//                                        "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento1FC") +
//                                        "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento2FC") +
//                                        "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento3FC") +
//                                        "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";     */
//
//
//                            //Correo que se le envia a los Usuarios Aprobadores
//                            htmlText3=" ";
//                                /*htmlText3= ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailBackOfficePagoTDCFC") +
//                                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
//                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": " +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
//                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
//                                        "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento1FC") +
//                                        "\n\n" + ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";      */
//                        }else{
//                            //Cliente Full Acceso
//                            htmlText="PRUEBA DE CORREO " + formatoFecha.format(new Date());
//                                /*htmlText=ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgEmailPagoTdc")  +
//                                        "\n\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
//                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAGMontoPagoTDC") + ": "  +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
//                                        "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
//                                        "\n" + ResourceBundle.getBundle("TarjetasCredito"+idioma).getString("TAGEstatusPagoTDC") + ": " + etiqueta +
//                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento1") +
//                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento2") +
//                                        "\n\n" + ResourceBundle.getBundle("Comun2"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";    */
//                        }
//                        //******** Envía correo de notificación de la orden de pago de TDC al asesor, ejecutivo y asistentes
//                        htmlText2=" ";
//                            /*htmlText2= ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgEmailBackOfficePagoTDC") +
//                                    "\n\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
//                                    "\n" + ResourceBundle.getBundle("TarjetasCredito_ve_es").getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
//                                    "\n" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAGMontoPagoTDC") + ": "  +
//                                    "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("FC_transferencias_TAGNumeroCuentaDebito") + ": " + parametros.get(5) +
//                                    "\n" + ResourceBundle.getBundle("vbtonline"+idioma).getString("cuentasedocuenta_TAGCartera") + ": " + parametros.get(0) +
//                                    "\n" + ResourceBundle.getBundle("TarjetasCredito"+"_ve_es").getString("TAGEstatusPagoTDC") + ": " + etiqueta+"\n\n\n";*/
//
//                    } catch (Exception ex) {
//                        logger.error(ex);
//                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #22" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
//                        //throw (new Exception(ex.getMessage(), null));
//                        respuesta = "ERROR_EMAIL";
//                        return (respuesta);
//                    }
//
//                    time = System.currentTimeMillis () - time;
//
//                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);
//
//                    /*Envio los correos */
//                    try {
//
//                        if (usuario.getTipoContrato().equalsIgnoreCase("FC")) {
//
//                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdcFC"), htmlText);
//                            emails = EmailsAprobadores(usuario.getNumContrato(), "7", usuario);
//
//                            if (!emails.equalsIgnoreCase(""))
//                                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emails, ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailNotificacionPagoTdc"), htmlText3);
//
//                            loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo : " + (emails + ", " + usuario.getEmail() + " motivado a Pagos TDC  Referencia Nro: " ).replaceAll(",", ", "));
//
//                        } else {
//
//                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdc"), htmlText);
//                            mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc"), ResourceBundle.getBundle("TarjetasCredito" + idioma).getString("TAGMsgEmailPagoTdc"), htmlText2);
//                            loggerIo.GuardarLog(usuario.getLogin(), "23", "1", "23", "", usuario.getIP(), "Se ha enviado un correo: " + (usuario.getEmail() + ", " + ResourceBundle.getBundle("vbtonline").getString("mail_pagostdc") + " motivado a pago de TDC Referencia Nro: " ).replaceAll(",", ", "));
//                        }
//
//                    } catch (Exception ex) {
//                        logger.error(ex);
//                        loggerIo.guardarExcepcion(usuario.getIP(), "Catch #30" + ex.getMessage(), origen, "Error: Preparando los correos " + "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
//                        respuesta = "ERROR_EMAIL";
//                    }
//                }
//
//
//            } catch (IOException ex) {
//                System.out.println(ex.getMessage());
//                loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
//            }
//            time = System.currentTimeMillis () - time;
//            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ CreditCardsIo.class+" | "+origen);
//        }
//        catch (Exception ex) {
//            logger.error(ex);
//            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
//        }
//        finally {
//            closeJdbcObjects (connection, statement, result);
//        }
//
//        return (respuesta);
//    }

    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }


}

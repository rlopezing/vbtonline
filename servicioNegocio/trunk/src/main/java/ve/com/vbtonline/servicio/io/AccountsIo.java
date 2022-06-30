package ve.com.vbtonline.servicio.io;

import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.TransformTable;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccountsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(AccountsIo.class);

    private LoggerIo loggerIo;
    /** Constructor de la clase
     */
    public AccountsIo() {
    }


    public AccountsOd Cargar (String transaccionId, AccountsOd aod, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        AccountsOd accountsOd = null;

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
//            accountsOd=new AccountsOd();
//            accountsOd.setId(aod.getId());





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (accountsOd);
    }

    public TableOd Consulta (String transaccionId, AccountsOd aod, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.Consulta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd tableOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
//            accountsOd=new AccountsOd();
//            accountsOd.setId(aod.getId());

            List<String> header=new ArrayList<String>();
            header.add("Nombre");
            header.add("Apellido");
            header.add("CI");
            header.add("Estado");
            header.add("Ciudad");

            List<String> body=new ArrayList<String>();

            List<List<String>> bodys=new ArrayList();

            body.add("Manuel");
            body.add("Serrano");
            body.add("11111111");
            body.add("tachira");
            body.add("tachira");
            bodys.add(body);


            body=new ArrayList<String>();

            body.add("rafael");
            body.add("godoy");
            body.add("22222222");
            body.add("caracas");
            body.add("tachira");
            bodys.add(body);


            tableOd=new TableOd();

            tableOd=TransformTable.getTable(header,bodys);

//            tableOd=new TableOd();
//            List<ContentsTableColumnsOd> contenidoTabla_culumnas=new ArrayList<ContentsTableColumnsOd>();
//            List<DataColumnOd> data_culumn=new ArrayList<DataColumnOd>();
//
//            ContentsTableColumnsOd ctco=new ContentsTableColumnsOd();
//            ctco.setSpan_html("columna1");
//            contenidoTabla_culumnas.add(ctco);
//
//            ctco=new ContentsTableColumnsOd();
//            ctco.setSpan_html("columna2");
//            contenidoTabla_culumnas.add(ctco);
//
//            ctco=new ContentsTableColumnsOd();
//            ctco.setSpan_html("columna3");
//            contenidoTabla_culumnas.add(ctco);
//
//            ctco=new ContentsTableColumnsOd();
//            ctco.setSpan_html("columna4");
//            contenidoTabla_culumnas.add(ctco);
//
//            ctco=new ContentsTableColumnsOd();
//            ctco.setSpan_html("columna5");
//            contenidoTabla_culumnas.add(ctco);
//
//            tableOd.setContenidoTabla_culumnas(contenidoTabla_culumnas);
//
//            DataColumnOd dco=new DataColumnOd();
//            dco.setData_columna("columna1.1");
//            data_culumn.add(dco);
//
//
//            dco=new DataColumnOd();
//            dco.setData_columna("columna1.2");
//            data_culumn.add(dco);
//
//            dco=new DataColumnOd();
//            dco.setData_columna("columna1.3");
//            data_culumn.add(dco);
//
//            dco=new DataColumnOd();
//            dco.setData_columna("columna1.4");
//            data_culumn.add(dco);
//
//
//            dco=new DataColumnOd();
//            dco.setData_columna("columna1.5");
//            data_culumn.add(dco);
//
//            List<ContentTableInfoOd>  contentTableInfoOd=new ArrayList<ContentTableInfoOd>();
//            ContentTableInfoOd ctid=new ContentTableInfoOd();
//            ctid.setData_culumn(data_culumn);
//            contentTableInfoOd.add(ctid);
//
//            tableOd.setContenidoTabla_info(contentTableInfoOd);





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (tableOd);
    }

    public CuentasOd cargarCuentasEdoCuenta (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.cargarCuentasEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        String cartera = Carteras.toString();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        CuentasOd select=new CuentasOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentas_edo_cuenta_pr(?,?,?,?,?,?); end;");
//
            String p_strCuentaCorriente = ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGMsgCuentaCorriente"); //TAGMsgCuentaCorriente
            String p_strCuentaAhorro = ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGMsgCuentaAhorro");     //TAGMsgCuentaAhorro
            String p_strDesconocido = ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgStatusDesconocido");     //TAGMsgStatusDesconocido
            if(Carteras==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, Carteras);
            }

            if(p_strCuentaCorriente==null)
                statement.setNull(2, OracleTypes.NULL);
            else
               statement.setString(2, p_strCuentaCorriente);

            if(p_strCuentaAhorro==null)
                statement.setNull(3, OracleTypes.NULL);
            else
               statement.setString(3, p_strCuentaAhorro);

            if(p_strDesconocido==null)
                statement.setNull(4, OracleTypes.NULL);
            else
               statement.setString(4, p_strDesconocido);

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);
            label = new String();
            valor = new String();
//
            String numeroCtaAux = new String();
            String codigoCarteraAux = new String();
//            String Disponible = new String();
            String cuentaValor = new String();
            String fechaCierre = new String();
            String descripcion = new String();
//            String moneda = new String();
            while (result.next()){
//
                numeroCtaAux = result.getString(1);
                codigoCarteraAux = result.getString(3);
//                Disponible = result.getString(5);
                fechaCierre = result.getString(4);
                cuentaValor = numeroCtaAux + " | "+ codigoCarteraAux;
                descripcion = numeroCtaAux + " (" +ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGCartera")+" : "+ codigoCarteraAux + ")";
                cuenta.setValue(cuentaValor);
                cuenta.setLabel(descripcion);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();
            }






            select.setContenido(cuentas);
            select.setFecha(fechaCierre);
//            select.setMoneda(moneda);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public List<ContentSelectOd> cargarTiposMovimientosBT (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.cargarTiposMovimientosBT";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> tiposMovimientos= new ArrayList<ContentSelectOd>();
        ContentSelectOd movimiento = new ContentSelectOd();
        CuentasOd select=new CuentasOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentasaldo_tipos_pr(?,?,?,?); end;");
//            vbt_cuentasaldo_tipos_pr (p_strNumeroCuenta  IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//                    p_vbt_cuentasaldo_tipos OUT vbt_cuentasaldo_tipos,
//                    p_resultado OUT VARCHAR2)
//


            if(parametros.get(0)==null)
                statement.setNull(1, OracleTypes.NULL);
            else
               statement.setString(1, parametros.get(0));

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.get(1));

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            label = new String();
            valor = new String();
//
            while (result.next()){
                movimiento.setValue(result.getString(1));
                movimiento.setLabel(result.getString(1));
                tiposMovimientos.add(movimiento);
                movimiento=new ContentSelectOd();
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tiposMovimientos);
    }

    public DetalleCuentaEdoCtaOd consultarDetalleCuentasEdoCuenta (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarDetalleCuentasEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentas_edo_cabecera_pr(?,?,?,?,?,?); end;");
//
//

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
               statement.setString(2, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
               statement.setString(3, parametros.get(2));

            if(parametros.get(3)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
               statement.setString(4, parametros.get(3));

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);

            detalle.setBloqueado(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(0.0)),2,","));
            detalle.setDiferido(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(0.0)),2,","));
            detalle.setSaldoDisp(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(0.0)),2,","));
            detalle.setSaldoActual(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(0.0)),2,","));

            while (result.next()){
              detalle.setCliente(result.getString(1));
              detalle.setMoneda(result.getString(2));
              detalle.setBloqueado(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
              detalle.setDiferido(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
              detalle.setSaldoDisp(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(5))),2,","));
              detalle.setSaldoActual(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
              detalle.setSaldoAnterior(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
            }
//

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public DetalleCuentaEdoCtaOd consultarDetalleCuentasSaldosTrans (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarDetalleCuentasSaldosTrans";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentasaldo_saldos_pr(?,?,?,?,?); end;");
//          vbt_cuentasaldo_saldos_pr (p_strCarteras    IN VARCHAR2,
//            p_strNumeroCuenta  IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//            p_vbt_cuentasaldo_saldos OUT vbt_cuentasaldo_saldos,
//                    p_resultado OUT VARCHAR2)
//

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
               statement.setString(2, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
               statement.setString(3, parametros.get(2));



            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);

            while (result.next()){
              detalle.setTipoCuenta(result.getString(2));
              detalle.setCliente(result.getString(9));
              detalle.setBloqueado(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
              detalle.setMoneda(result.getString(3));
              detalle.setDiferido(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(5))),2,","));
              detalle.setSaldoDisp(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
              detalle.setSaldoActual(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
              detalle.setFechaCierre(result.getString(8));
//
            }
//

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public String consultarFechaCierre (String carteras, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarFechaCierre";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        String respuesta=null;
        String fecha = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_colocacerti_cierre_pr(?,?,?); end;");
////         vbt_colocacerti_cierre_pr (p_strCarteras  IN VARCHAR2,
//            p_vbt_colocacerti_cierre OUT vbt_colocacerti_cierre,
//                    p_resultado OUT VARCHAR2) AS

            if(carteras==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, carteras);
            }


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);


            while (result.next()){
//
                fecha = result.getString(1);

            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (fecha);
    }

    public String guardarComentarioTrans (List<String> parametros,VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.guardarComentarioTrans";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);
            time = System.currentTimeMillis ();
            connection=getConnection();
            statement  = connection.prepareCall ("begin vbtonline.vbt_guardarComentario_trans_pr(?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.get(1));

            if(usuario.getLogin()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, usuario.getLogin());

            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);

        }catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }

    public TableOd consultarCuentasSaldoTrans (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarCuentasEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        CurrencyFormatter formatoMonto = new CurrencyFormatter();
        TableOd listaMovimientos;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentasaldo_trans_pr(?,?,?,?,?,?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
               statement.setString(2, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
               statement.setString(3, parametros.get(2));

            if(parametros.get(3)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
               statement.setString(4, parametros.get(3));

            if(parametros.get(4)==null)
                statement.setNull(5, OracleTypes.NULL);
            else
               statement.setString(5, parametros.get(4));

            if(parametros.get(5)==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6, parametros.get(5));

            statement.registerOutParameter(7, OracleTypes.CURSOR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);
            statement.registerOutParameter(9, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);
            String sql = statement.getString(9);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (7);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            String strTipoMov = new String();
            String aux1 = new String();
            String aux2 = new String();
            String aux3 = new String();
            String detalleRep = "";
            String tdetalle = "";
            String tag ="";
            while (result.next()){
                body=new ArrayList<String>();
                strTipoMov = new String();
                strTipoMov = result.getString(13);
                aux1 = new String();
                aux1 = (!NullFormatter.formatBlank(result.getString(12)).equals("")) ? "" + CurrencyFormatter.formatNumber(result.getString(12),4,",") : "";


                tdetalle = "<img onclick=' abrirDetalleTabla(this)' class='btn_descripcion_cuenta_BT' style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='";
                detalleRep = NullFormatter.formatHtmlBlank(result.getString("FECHA_OPERACION"))+"|"
                        +NullFormatter.formatHtmlBlank(result.getString("DESCRIPCION"))+"|";

                if (
                    (NullFormatter.formatHtmlBlank(result.getString("DEBITO")).equalsIgnoreCase("")) ||
                    (NullFormatter.formatHtmlBlank(result.getString("DEBITO")).equalsIgnoreCase("0"))
                ) {
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("CREDITO")) +"|";
                } else {
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("DEBITO")) +"|";
                }

                detalleRep += NullFormatter.formatHtmlBlank(result.getString("MONEDA"))+"|"
                        +NullFormatter.formatHtmlBlank(result.getString("FECHA_VALOR"))+"|"
                        +formatoMonto.formatNumber(NullFormatter.formatHtmlBlank(result.getString("TASA_CAMBIO")),4,",")+"|"
                        +NullFormatter.formatHtmlBlank(result.getString("REFERENCIA"))+"|";
                if (
                        (strTipoMov.equalsIgnoreCase("CHC")) //||
                        //(strTipoMov.equalsIgnoreCase("CHC"))
                        ) {
                    // Formato 1
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("BENEFICIARIO"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("REFERENCIA"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"));

                } else if (
                        (strTipoMov.equalsIgnoreCase("CIC")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 2
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("CODCAR_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCOL_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("CID")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 3
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("BENEFICIARIO"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCAR_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCOL_ORIG_DEST"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("DP")) ||
                        (strTipoMov.equalsIgnoreCase("DPE"))
                        ) {
                    // Formato 4
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("REFERENCIA"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("ORIGINATORS_BANK_DESCRIPTION"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("LOP")) ||
                        (strTipoMov.equalsIgnoreCase("LOR"))
                        ) {
                    // Formato 5
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("FECHA_ESPECIAL"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("PLAZOMOV"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("TASACAMB_REF"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("RCH")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 6
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("REFERENCIA"))+"|";
                } else if (
                        (strTipoMov.equalsIgnoreCase("STP")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 7
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("BENEFICIARIO"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("REFERENCIA"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("TEI")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 8
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("ORIGINATORS_BANK_DESCRIPTION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("RECEIVER_INFO"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("TEO")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 9
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_TYPE"))+" - "+NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_TYPE_NUMBER"))+" - "+NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_DESCRIPTION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_BANK_TYPE"))+" - "+NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_BANK_TYPE_NUMBER"))+" - "+NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_BANK_DESCRIPTION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("BENEFICIARY_INFO"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("TIC")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 10
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCAR_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCOL_ORIG_DEST"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("TID")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 11
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("BENEFICIARIO"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCAR_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCOL_ORIG_DEST"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("MFD")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 12
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("BENEFICIARIO"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODCAR_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODEMP_ORIG_DEST"));
                } else if (
                        (strTipoMov.equalsIgnoreCase("MFC")) //||
                    //(strTipoMov.equalsIgnoreCase(""))
                        ) {
                    // Formato 13
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("CODCAR_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("CODEMP_ORIG_DEST"))+"|"
                            +NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"));
                } else {
                    // Formato 0 - General
                    detalleRep += NullFormatter.formatHtmlBlank(result.getString("OBSERVACION"));
                   // throw new Exception("Prueba " + detalleRep);
                }

                tdetalle += detalleRep+ "' type='button' value='+'  title='"+strTipoMov+"' >";
                body.add(tdetalle);
                body.add(result.getString("FECHA_OPERACION"));
                body.add(result.getString("FECHA_VALOR"));
                body.add(result.getString("DESCRIPCION"));
                if(result.getString("COMENTARIO")!=null){
                    tag =  "TAGEditarComentario";
                }else{
                    tag =  "TAGAgregarComentario";
                }

                body.add(NullFormatter.formatHtmlBlank(result.getString("COMENTARIO"))+"<a style='cursor:pointer;font-weight:bold;color:#5D8D99' onclick=' abrirAgregarComentario(this)' id='"+NullFormatter.formatBlank(result.getString("ID"))+"|"+NullFormatter.formatBlank(result.getString("COMENTARIO"))+"'>"
                        +"&nbsp;"+ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString(tag)+"</a>");


                if(result.getString("REFERENCIA")!=null)
                  body.add(result.getString("REFERENCIA"));
                else
                  body.add("");

                body.add((!result.getString("DEBITO").equals("0") ? "" + CurrencyFormatter.formatNumber(result.getString("DEBITO"),2,",") : ""));
                body.add((!result.getString("CREDITO").equals("0") ? "" + CurrencyFormatter.formatNumber(result.getString("CREDITO"),2,",") : ""));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();

            header.add("");
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGComentario"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGReferencia"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGDebitos"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGCreditos"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public TableOd consultarCuentasBloqueos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarCuentasBloqueos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        TableOd listaMovimientos;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();


            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentas_bloqueo_vigen_pr(?,?,?,?,?,?); end;");
////

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
               statement.setString(2, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
               statement.setString(3, parametros.get(2));

            if(parametros.get(3)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
               statement.setString(4, parametros.get(3));



            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowBloqueos.getColumnAt(0))%></td>
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowBloqueos.getColumnAt(1))%></td>
//                <td align="left">&nbsp;<%=NullFormatter.formatHtmlBlank(rowBloqueos.getColumnAt(2))%></td>
//                <td align="center"><%=CurrencyFormatter.formatNumber(rowBloqueos.getColumnAt(3),2,",")%></td>
                body=new ArrayList<String>();
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
//                if(result.getString(2)!=null)
                    body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
//                else
//                    body.add("");

//                if(result.getString(3)!=null)
                  body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
//                else
//                  body.add("");
                body.add(CurrencyFormatter.formatNumber(result.getString(4),2,","));
                bodys.add(body);
            }

          List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGFechaVencimiento"));
            header.add(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("CuentasBloqueos"+idioma).getString("TAGMonto"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public List<String> consultarMesAnoMaximo (VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarMesAnoMaximo";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> fechames=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentas_edo_mesano_pr(?,?); end;");
////
            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);



            while (result.next()){
                fechames.add(result.getString(1));
                fechames.add(result.getString(2));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));


        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (fechames);
    }

    public TableOd consultarCuentasEdoCuenta (List<String> parametros, String saldo, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "AccountsIo.consultarCuentasEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        TableOd listaMovimientos;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_cuentas_edo_detalle_pr(?,?,?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
               statement.setString(2, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
               statement.setString(3, parametros.get(2));

            if(parametros.get(3)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
               statement.setString(4, parametros.get(3));

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);
            List<List<String>> bodys=new ArrayList();
            double dblMontoMovimiento = 0.0;
            double dblSaldo           = 0.0;
            double saldo2 = Double.parseDouble(saldo);
            String prueba;
            dblSaldo = dblSaldo + saldo2;


            List<String> body=new ArrayList<String>();
            body.add("");
            body.add("");
            body.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGMsgSaldoMesAnterior"));
            body.add("");
            body.add("");
            body.add("");
            body.add(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(saldo2)),2,","));
            bodys.add(body);
            int countDatos=0;
            while (result.next()){
                body=new ArrayList<String>();
                body.add(result.getString(1));
                body.add(result.getString(2));
                body.add(result.getString(3));
                if(result.getString(4)!=null)
                  body.add(result.getString(4));
                else
                  body.add("");
                dblMontoMovimiento = Double.parseDouble(NullFormatter.formatHtmlBlank(result.getString(5)));
                dblSaldo += dblMontoMovimiento;
                if(dblMontoMovimiento > 0)
                    body.add("");
                else
                    body.add(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(dblMontoMovimiento)),2,","));

                if(dblMontoMovimiento > 0)
                    body.add(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(dblMontoMovimiento)),2,","));
                else
                    body.add("");

                body.add(CurrencyFormatter.formatNumber(Double.toString(dblSaldo),2,","));
                bodys.add(body);
                countDatos++;

            }
            if(countDatos==0){
                List<String> bodyTable=new ArrayList<String>();
                bodyTable.add("");
                bodyTable.add("");
                bodyTable.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGMsgSaldoMesAnterior"));
                bodyTable.add("");
                bodyTable.add("");
                bodyTable.add("");
                bodyTable.add(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(Double.valueOf(saldo))),2,","));
                bodys.set(0,bodyTable);
            }


          List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGReferencia"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGDebitos"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGCreditos"));
            header.add(ResourceBundle.getBundle("CuentasEdoCuenta"+idioma).getString("TAGSaldo"));

//
//
            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }
}
    /*
    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "AccountsIo.GuardarLog";
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
        final String origen = "AccountsIo.guardarExcepcion";
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

}   */

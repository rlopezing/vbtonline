package ve.com.vbtonline.servicio.io;

import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRProperties;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.TransformTable;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class OtherInvestmentsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(OtherInvestmentsIo.class);
    private LoggerIo loggerIo;
    /** Constructor de la clase
     */
    public OtherInvestmentsIo() {
    }


    public MutualFundsOd Cargar (String transaccionId, MutualFundsOd mfod,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        MutualFundsOd mutualFundsOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            mutualFundsOd=new MutualFundsOd();
            mutualFundsOd.setId(mfod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ OtherInvestmentsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (mutualFundsOd);
    }

    public CuentasOd cargarOtrasInversiones (String carteras, String idioma,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.cargarOtrasInversiones";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        CuentasOd detalles = new CuentasOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_inverio_bloqueo_pr(?,?,?,?); end;");
//
            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            String strCodigoFondoAux = new String();
            String strCodigoCarteraAux = new String();
            String fecha = new String();
            while (result.next()){
                strCodigoFondoAux  = new String();
                strCodigoCarteraAux = new String();
                valor = new String();
                label  = new String();
                strCodigoFondoAux  = NullFormatter.formatBlank(result.getString(1));
                strCodigoCarteraAux = NullFormatter.formatBlank(result.getString(3));
                valor = strCodigoFondoAux + "|" + strCodigoCarteraAux;
                label  = NullFormatter.formatBlank(result.getString(2)) + " (" + ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGCartera")+ ": " + strCodigoCarteraAux + ")";

                fecha=NullFormatter.formatBlank(result.getString(4));
                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();


            }

//            select.setContenido(cuentas);

            detalles.setFecha(fecha);
            detalles.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalles);
    }

                       /*
    public CuentasOd cargarOtrasInversionesRazonMoneda (String carteras, String idioma,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.cargarOtrasInversionesRazonMoneda";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        CuentasOd detalles = new CuentasOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_sal_cartera_pr(?,?,?,?); end;");
//
            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            String sql = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            String strCodigoFondoAux = new String();
            String strCodigoCarteraAux = new String();
            String fecha = new String();
            while (result.next()){
                strCodigoFondoAux  = new String();
                strCodigoCarteraAux = new String();
                valor = new String();
                label  = new String();
                strCodigoFondoAux  = NullFormatter.formatBlank(result.getString(1));
                strCodigoCarteraAux = NullFormatter.formatBlank(result.getString(3));
                valor = strCodigoFondoAux + "|" + strCodigoCarteraAux+ "|" +NullFormatter.formatBlank(result.getString(2))+ "|" +NullFormatter.formatBlank(result.getString(5));
                label  = NullFormatter.formatBlank(result.getString(2)) + " (" + ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGCartera")+ ": " + strCodigoCarteraAux + ")";

                fecha=NullFormatter.formatBlank(result.getString(4));
                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();


            }

//            select.setContenido(cuentas);

            detalles.setFecha(fecha);
            detalles.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalles);
    }
                      */

    public CuentasOd cargarOtrasInversionesRazonMoneda (String carteras, String idioma,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.cargarOtrasInversionesRazonMoneda";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        CuentasOd detalles = new CuentasOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_inverio_bloqueo_pr(?,?,?,?); end;");
//
            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            String strCodigoFondoAux = new String();
            String strCodigoCarteraAux = new String();
            String fecha = new String();
            while (result.next()){
                strCodigoFondoAux  = new String();
                strCodigoCarteraAux = new String();
                valor = new String();
                label  = new String();
                strCodigoFondoAux  = NullFormatter.formatBlank(result.getString(1));
                strCodigoCarteraAux = NullFormatter.formatBlank(result.getString(3));
                valor = strCodigoFondoAux + "|" + strCodigoCarteraAux+"|" +  NullFormatter.formatBlank(result.getString(2))+"|" +  NullFormatter.formatBlank(result.getString(5))+"|" +  NullFormatter.formatBlank(result.getString(6));
                label  = NullFormatter.formatBlank(result.getString(2)) + " (" + ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGCartera")+ ": " + strCodigoCarteraAux + ")";

                fecha=NullFormatter.formatBlank(result.getString(4));
                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();


            }

//            select.setContenido(cuentas);

            detalles.setFecha(fecha);
            detalles.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalles);
    }

    public SelectOd cargarTipoTransaccionBT (List<String> parametros,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.cargarTipoTransaccionBT";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> tipoTransacciones= new ArrayList<ContentSelectOd>();
        ContentSelectOd tipoTrans = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_sal_movimi_pr(?,?,?,?); end;");

            if(parametros.get(0)== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, parametros.get(0));

            if(parametros.get(1)== null)
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);

            while (result.next()){

                tipoTrans.setLabel(result.getString(1));
                tipoTrans.setValue(result.getString(1));
                tipoTransacciones.add(tipoTrans);
                tipoTrans=new ContentSelectOd();

            }

            select.setContenido(tipoTransacciones);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


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

    public List<String> consultarDetalleOtrasInversionesBT (List<String> parametros,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.consultarDetalleOtrasInversionesBT";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        SelectOd select=new SelectOd();
        String respuesta=null;
        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_sal_saldos_pr(?,?,?,?); end;");
//          vbt_OtrasInversiones_sal_saldos_pr (p_strCodigoCartera IN VARCHAR2,
//            p_strCodigoFondo IN VARCHAR2,
//                    p_vbt_OtrasInversiones_sal_saldos OUT vbt_OtrasInversiones_sal_saldos,
//            p_resultado OUT VARCHAR2)



            if(parametros.get(0)== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, parametros.get(0));

            if(parametros.get(1)== null)
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);


            while (result.next()){

               listaDetalles.add(NullFormatter.formatBlank(result.getString(1))); //nombre fondo
               listaDetalles.add(NullFormatter.formatBlank(result.getString(2))); //moneda
                if(result.getString(3)!=null)
                    listaDetalles.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(3)),8,",")); //valor actual
                else
                    listaDetalles.add("0");
                if(result.getString(4)!=null)
                    listaDetalles.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(4)),8,",")); //bloqueado
                else
                    listaDetalles.add("0");
                if(result.getString(5)!=null)
                    listaDetalles.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(5)),8,",")); //disponible
                else
                    listaDetalles.add("0");
                if(result.getString(6)!=null)
                    listaDetalles.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(6)),6,",")); //VUI
                else
                    listaDetalles.add("0");
                if(result.getString(7)!=null)
                    listaDetalles.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(7)),2,",")); //total moneda
                else
                    listaDetalles.add("0");
                if(result.getString(8)!=null)
                    listaDetalles.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(8)),2,",")); //Saldo Transito
                else
                    listaDetalles.add("0");

               listaDetalles.add(NullFormatter.formatBlank(result.getString(9)));  //Fecha Cierre
               listaDetalles.add(NullFormatter.formatBlank(result.getString(10)));  //Cliente Principal

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaDetalles);
    }

    public TableOd consultarSaldosTransOtrasInversiones (List<String> parametros, String idioma,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.consultarSaldosTransOtrasInversiones";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_sal_trans_pr(?,?,?,?,?,?,?,?,?,?); end;");
//            vbt_OtrasInversiones_sal_trans_pr (p_TAGEnTransito  IN VARCHAR2,
//                    p_strCodigoFondo   IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//                    p_strCmbDiasConsulta IN VARCHAR2,
//                    p_strTxtFechaDesde  IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
//                    p_strTipoTransaccion IN VARCHAR2,
//                    p_vbt_OtrasInversiones_sal_trans OUT SYS_REFCURSOR,
//                    p_resultado OUT VARCHAR2)

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

            if(parametros.get(3)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, parametros.get(3));

            if(parametros.get(4)==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, parametros.get(4));
            }

            if(parametros.get(5)==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6, parametros.get(5));

            if(parametros.get(6)==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, parametros.get(6));

            statement.registerOutParameter(8, OracleTypes.CURSOR);
            statement.registerOutParameter(9, OracleTypes.VARCHAR);


            if(idioma==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10, idioma);

            statement.execute ();

            respuesta = statement.getString(9);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (8);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
//
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                if(!result.getString(4).equals("0"))
                  body.add(CurrencyFormatter.formatNumber(result.getString(4),2,","));
                else
                  body.add(result.getString(4));
                if(!result.getString(5).equals("0"))
                  body.add(CurrencyFormatter.formatNumber(result.getString(5),6,","));
                else
                  body.add(result.getString(5));
                if(!result.getString(6).equals("0"))
                  body.add(CurrencyFormatter.formatNumber(result.getString(6),2,","));
                else
                  body.add(result.getString(6));
                if(!result.getString(7).equals("0"))
                  body.add(CurrencyFormatter.formatNumber(result.getString(7),2,","));
                else
                  body.add(result.getString(7));

                body.add(NullFormatter.formatBlank(result.getString(8)));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
//
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGTotalMoneda"));
            if("0000062901".equals(parametros.get(6)))
              header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGValorNominal"));
            else
              header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGVUI"));
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGRescates"));
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGSuscripciones"));
            header.add(ResourceBundle.getBundle("OtrasInversionesSaldos"+idioma).getString("TAGStatus"));



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

    public TableOd consultarBloqueosOtrasInversiones (List<String> parametros, String idioma,  VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.consultarSaldosTransOtrasInversiones";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_blo_movimi_pr(?,?,?,?,?,?,?); end;");
//            PROCEDURE vbt_OtrasInversiones_blo_movimi_pr (p_strCmbBuscar  IN VARCHAR2,
//            p_strCodigoFondo   IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//            p_strTxtFechaDesde IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
//            p_vbt_OtrasInversiones_blo_movimi OUT SYS_REFCURSOR,
//                    p_resultado OUT VARCHAR2)

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

            if(parametros.get(3)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, parametros.get(3));

            if(parametros.get(4)==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, parametros.get(4));
            }



            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ OtherInvestmentsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
//              <td align="center"><%=NullFormatter.formatHtmlBlank(rowBloqueos.getColumnAt(0))%></td>
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowBloqueos.getColumnAt(1))%></td>
//                <td align="left">&nbsp;<%=NullFormatter.formatHtmlBlank(rowBloqueos.getColumnAt(2))%></td>
//                <td align="center"><%=CurrencyFormatter.formatNumber(rowBloqueos.getColumnAt(3),2,",")%></td>
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add(CurrencyFormatter.formatNumber(result.getString(4),2,","));

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
//
//            <td width="23%" height="20" align="center"><%=pageConfig.getTagFieldValue("TAGFechaOperacion")%></td>
//            <td width="24%" align="center"><%=pageConfig.getTagFieldValue("TAGFechaValor")%></td>
//            <td width="24%" align="center"><%=pageConfig.getTagFieldValue("TAGDescripcion")%></td>
//            <td width="24%" align="center"><%=pageConfig.getTagFieldValue("TAGMontoInversion")%></td>
            header.add(ResourceBundle.getBundle("OtrasInversionesBloqueos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("OtrasInversionesBloqueos"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("OtrasInversionesBloqueos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("OtrasInversionesBloqueos"+idioma).getString("TAGMontoInversion"));

            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);

        }catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }


    public EstadoCuentaFMGeneralOd consultarTablaEdoCuentaFondos (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "OtherInvestmentsIo.consultarTablaEdoCuentaFondos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> datos=new ArrayList<String>();
        List<List<String>> tabla=new ArrayList<List<String>>();
        List<List<String>> tablaEncabezado=new ArrayList<List<String>>();
        List<List<List<String>>> global=new ArrayList<List<List<String>>>();
        int contadorprovisional = 0;

        EstadoCuentaFMOd bean = null;
        EstadoCuentaFMOd encabezado =  null;
        List<EstadoCuentaFMOd> lista_detalle =  new ArrayList<EstadoCuentaFMOd>();
        EstadoCuentaFMGeneralOd beanGeneral = null;

        try{
            String codemp = "",codcar = "",fechaEmision = "";

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            statement  = connection.prepareCall ("begin vbtonline.vbt_estado_cuenta_fondos(?,?,?,?,?,?,?); end;");

            if (parametros.get(0)== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if (parametros.get(1)== null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.get(1));

            if (parametros.get(2)== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));

            if (parametros.get(3)== null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.get(3));

            if (parametros.get(4)== null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,parametros.get(4));


            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.registerOutParameter(7, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (7);

            contadorprovisional=0;

            while (result.next()){

                bean = new EstadoCuentaFMOd();
                bean.setConcirif(NullFormatter.formatHtmlBlank(result.getString(1)));
                bean.setInversionista(NullFormatter.formatHtmlBlank(result.getString(2)));
                bean.setDirenvio(NullFormatter.formatHtmlBlank(result.getString(3)));
                bean.setCodcar(NullFormatter.formatHtmlBlank(result.getString(4)));
                bean.setCodemp(NullFormatter.formatHtmlBlank(result.getString(5)));
                bean.setCodacc(NullFormatter.formatHtmlBlank(result.getString(6)));
                bean.setMoneda(NullFormatter.formatHtmlBlank(result.getString(7)));
                bean.setCodeje(result.getString(8));
                bean.setNombeje(result.getString(9));
                bean.setNumaccgar(NullFormatter.formatHtmlBlank(result.getString(10)));
                bean.setNumaccdisp(NullFormatter.formatHtmlBlank(result.getString(11)));
                bean.setSaldo_unit_ant(result.getString(12));
                bean.setPrecio_vui(NullFormatter.formatHtmlBlank(result.getString(13)));
                bean.setFechope(NullFormatter.formatHtmlBlank(result.getString(14)));
                bean.setFechval(NullFormatter.formatHtmlBlank(result.getString(15)));
                bean.setNombtipo(NullFormatter.formatHtmlBlank(result.getString(16)));
                bean.setNombtipoing(NullFormatter.formatHtmlBlank(result.getString(17)));
                bean.setMtoinv(CurrencyFormatter.formatNumber(result.getString(18), 2, ","));
                bean.setPrecuni(CurrencyFormatter.formatNumber(result.getString(19), 6, ","));
                bean.setNumuni(NullFormatter.formatHtmlBlank(result.getString(20)));
                bean.setTipope(NullFormatter.formatHtmlBlank(result.getString(21)));
                bean.setCodtipo(NullFormatter.formatHtmlBlank(result.getString(22)));
                bean.setSignotipo(NullFormatter.formatHtmlBlank(result.getString(23)));
                bean.setFecha_vui(NullFormatter.formatHtmlBlank(result.getString(24)));
                bean.setCodemp_a(NullFormatter.formatHtmlBlank(result.getString(25)));
                bean.setFlgreinv(NullFormatter.formatHtmlBlank(result.getString(26)));
                bean.setRazcomer(NullFormatter.formatHtmlBlank(result.getString(27)));
                bean.setNumacc(NullFormatter.formatHtmlBlank(result.getString(28)));
                bean.setPrecio_vui_ext(NullFormatter.formatHtmlBlank(result.getString(29)));
                bean.setCodope(NullFormatter.formatHtmlBlank(result.getString(30)));
                bean.setFlgextrt(NullFormatter.formatHtmlBlank(result.getString(31)));
                bean.setFecha_fin_mes(NullFormatter.formatHtmlBlank(result.getString(32)));
                bean.setAcc_secundario(NullFormatter.formatHtmlBlank(result.getString(33)));

                if (NullFormatter.formatHtmlBlank(result.getString(1)).equalsIgnoreCase("1")){
                    encabezado = new EstadoCuentaFMOd();
                    encabezado = bean;
                }else{
                    lista_detalle.add(bean);
                }
            }

            beanGeneral = new EstadoCuentaFMGeneralOd();

            if (encabezado != null ){
                beanGeneral.setEncabezado(encabezado);
            }

            if (lista_detalle.size() > 0){
                beanGeneral.setDetalle(lista_detalle);
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
        return beanGeneral;
    }

    public List<ConfiguracionEstadoCuentaFMOd> consultarConfiguracionEDO(List<String> parametros, VBTUsersOd usuario) throws Exception{

        final String origen = "OtherInvestmentsIo.consultarConfiguracionEDO";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta=null;
        List<ConfiguracionEstadoCuentaFMOd> resp = new ArrayList<ConfiguracionEstadoCuentaFMOd>();
        ConfiguracionEstadoCuentaFMOd bean;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            statement  = connection.prepareCall ("begin vbtonline.VBT_CARGAR_EDO_FONDOMUTUAL(?,?,?); end;");
            String nroEmp =  parametros.get(0);
            if(nroEmp == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, nroEmp);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.CURSOR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject(3);

            while (result.next()){
                bean = new ConfiguracionEstadoCuentaFMOd();
                bean.setCod_emp(NullFormatter.formatBlank(result.getString(1)));
                bean.setEtiqueta(NullFormatter.formatBlank(result.getString(2)));
                bean.setIdioma(NullFormatter.formatBlank(result.getString(3)));
                bean.setMes_condicion(NullFormatter.formatBlank(result.getString(4)));
                bean.setTexto_ing(NullFormatter.formatBlank(result.getString(5)));
                bean.setTexto_esp(NullFormatter.formatBlank(result.getString(6)));
                bean.setCondicion(NullFormatter.formatBlank(result.getString(7)));
                bean.setImagen(NullFormatter.formatBlank(result.getString(8)));
                resp.add(bean);
            }

            if (resp.size() == 0){
                statement = null;   result = null;
                nroEmp = "9999999999";
                resp = new ArrayList<ConfiguracionEstadoCuentaFMOd>();
                statement  = connection.prepareCall ("begin vbtonline.VBT_CARGAR_EDO_FONDOMUTUAL(?,?,?); end;");

                if(nroEmp == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, nroEmp);

                statement.registerOutParameter(2, OracleTypes.VARCHAR);
                statement.registerOutParameter(3, OracleTypes.CURSOR);

                statement.execute ();

                respuesta = statement.getString(2);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));

                result = (ResultSet) statement.getObject(3);

                while (result.next()){
                    bean = new ConfiguracionEstadoCuentaFMOd();
                    bean.setCod_emp(NullFormatter.formatBlank(result.getString(1)));
                    bean.setEtiqueta(NullFormatter.formatBlank(result.getString(2)));
                    bean.setIdioma(NullFormatter.formatBlank(result.getString(3)));
                    bean.setMes_condicion(NullFormatter.formatBlank(result.getString(4)));
                    bean.setTexto_ing(NullFormatter.formatBlank(result.getString(5)));
                    bean.setTexto_esp(NullFormatter.formatBlank(result.getString(6)));
                    bean.setCondicion(NullFormatter.formatBlank(result.getString(7)));
                    bean.setImagen(NullFormatter.formatBlank(result.getString(8)));
                    resp.add(bean);
                }
            }

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);

        }catch(Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(connection, statement, result);
        }

        return resp;
    }

    public String consultarDividendoEdoCuenta (String codigoEmp, String fechaEmision, VBTUsersOd usuario) throws Exception{

        final String origen = "OtherInvestmentsIo.consultarDividendoEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta=null,dividendo="";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBTONLINE.VBT_EDO_FONDOS_DIVIDENDO(?,?,?,?); end;");

            if (codigoEmp == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, codigoEmp);

            if (fechaEmision == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, fechaEmision);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject(3);

            while (result.next()){
                dividendo = result.getString(10)+" "+result.getString(4)+" X "+result.getString(5);
            }
        }catch(Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally {
            closeJdbcObjects(connection, statement, result);
        }

        return dividendo;
    }

    public InputStream generarPDFEdoCuenta(List<String> parametros, VBTUsersOd usuario) throws Exception{

        final String origen = "OtherInvestmentsIo.generarPDFEdoCuenta";
        Connection conexion = getConnection();

        List<ConfiguracionEstadoCuentaFMOd> respuesta = consultarConfiguracionEDO(parametros,usuario);
        InputStream inputStream = null;
        Map parameters = new HashMap();
        int nroDecimales = 0;

        try{
            parameters.put("COD_EMP",parametros.get(0));
            parameters.put("COD_CAR",parametros.get(1));
            parameters.put("RAZON",parametros.get(3));
            parameters.put("MONEDA",parametros.get(4));
            parameters.put("FECHA",parametros.get(2));

            if (parametros.get(5).equals("8")){
                nroDecimales = 8;
            }else{
                nroDecimales = 6;
            }

            String[] vfecha = parametros.get(2).split("/");
            int mes =  Integer.parseInt(vfecha[1]);

            EstadoCuentaFMGeneralOd detalle = consultarTablaEdoCuentaFondos(parametros,usuario);

            for (int a=0;a<respuesta.size();a++){
                if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGholder")){
                    parameters.put("ETIQ_HOLDER",respuesta.get(a).getTexto_ing()+":");
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGtotalBalance")){
                    parameters.put("ETIQ_TOTALBALANCE",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGguarantee")){
                    parameters.put("ETIQ_GARANTIA",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGavalaible")){
                    parameters.put("ETIQ_DISPONIBLE",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGNAV")){
                    parameters.put("ETIQ_NAV",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGcurrencybalance")){
                    parameters.put("ETIQ_BALANCE",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().toString().equals("ASIO_TAGtitle")){
                    if (respuesta.get(a).getTexto_ing() == null){
                        parameters.put("ETIQ_VENECREDIT","");
                    }else{
                        parameters.put("ETIQ_VENECREDIT",respuesta.get(a).getTexto_ing());
                    }
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGfooter")){
                    if (respuesta.get(a).getTexto_ing() == null){
                        parameters.put("ETIQ_PIEPAG","");
                    }else{
                        parameters.put("ETIQ_PIEPAG",respuesta.get(a).getTexto_ing());

                        if (respuesta.get(a).getCondicion()==null){
                            parameters.put("MES_CONDICION","NO");
                        }else if (respuesta.get(a).getCondicion().equals(">")){
                            if (mes > (Integer.parseInt(respuesta.get(a).getMes_condicion()))){
                                parameters.put("MES_CONDICION","SI");
                            }else{
                                parameters.put("MES_CONDICION","NO");
                            }
                        }else if (respuesta.get(a).getCondicion().equals("<")){
                            if (mes < (Integer.parseInt(respuesta.get(a).getMes_condicion()))){
                                parameters.put("MES_CONDICION","SI");
                            }else{
                                parameters.put("MES_CONDICION","NO");
                            }
                        }else if (respuesta.get(a).getCondicion().equals("=")){
                            if (mes == (Integer.parseInt(respuesta.get(a).getMes_condicion()))){
                                parameters.put("MES_CONDICION","SI");
                            }else{
                                parameters.put("MES_CONDICION","NO");
                            }
                        }
                    }
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGreinvestment")){
                    if (!respuesta.get(a).getTexto_ing().equals("")){
                        if (detalle.getEncabezado()!=null){
                            if (detalle.getEncabezado().getConcirif().equals("-1")){
                                parameters.put("ETIQ_REINVESTMENT","REINVESTMENT");
                            }else if (detalle.getEncabezado().getFlgreinv().equals("1") || detalle.getEncabezado().getFlgreinv().equals("0")){
                                parameters.put("ETIQ_REINVESTMENT","NON REINVESTMENT");
                            }
                        }
                    }else{
                        parameters.put("ETIQ_REINVESTMENT","");
                    }
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGdetailNumber1")){
                    parameters.put("ETIQ_NUMBERUNITS1",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGdetailNumber2")){
                    parameters.put("ETIQ_NUMBERUNITS2",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGdetailNumber3")){
                    parameters.put("ETIQ_NUMBERUNITS3",respuesta.get(a).getTexto_ing());
                }else if (respuesta.get(a).getEtiqueta().equals("ASIO_TAGimage")){
                    parameters.put("IMAGENFONDO",respuesta.get(a).getImagen());
                }
            }

            parameters.put("ETIQ_FUND",parametros.get(3).toString().toUpperCase());

            if (detalle != null){
                EstadoCuentaFMOd encab = detalle.getEncabezado();

                parameters.put("ENCAB_CODACC",encab.getCodacc());

                if (encab.getAcc_secundario().compareTo("&nbsp;")==0){
                    parameters.put("ENCAB_INVERSIONISTA",encab.getInversionista());
                }else{
//                    String[] listaSec = encab.getAcc_secundario().split("-");
//                    String secundario = "";
//                    if (listaSec.length>0){
//                        for (int a=0;a<listaSec.length;a++){
//                            secundario += "                              "+listaSec[a];
//                        }
//                    }else{
//                        secundario = encab.getAcc_secundario();
//                    }
                    parameters.put("ENCAB_INVERSIONISTA",encab.getInversionista()+"<br>"+encab.getAcc_secundario().replaceAll("-","<br>"));
                }

                parameters.put("ENCAB_DIRENVIO",encab.getDirenvio());

                Calendar cal = Calendar.getInstance();
                cal.set(Integer.parseInt(vfecha[2]),(Integer.parseInt(vfecha[1])-1),15);
                String fechaUlt = String.valueOf(cal.getActualMaximum(Calendar.DAY_OF_MONTH))+"/"+vfecha[1]+"/"+vfecha[2];

                parameters.put("ENCAB_FECHAOPE",fechaUlt);
                parameters.put("ENCAB_FECHAVAL",encab.getFechval());
                parameters.put("ENCAB_MONEDA",encab.getMoneda());
                parameters.put("ENCAB_CUENTAEJECUTIVO",encab.getCodeje());
                parameters.put("ENCAB_GARANTIA",CurrencyFormatter.formatNumber(encab.getNumaccgar(),nroDecimales,","));
                parameters.put("ENCAB_DISPONIBLE",CurrencyFormatter.formatNumber(encab.getNumaccdisp(),nroDecimales,","));
                parameters.put("ENCAB_NAV",CurrencyFormatter.formatNumber(encab.getPrecio_vui(),6,","));

                Float suma = Float.parseFloat(encab.getNumaccgar())+Float.parseFloat(encab.getNumaccdisp());
                Float mult = suma * Float.parseFloat(encab.getPrecio_vui());

                parameters.put("ENCAB_TOTALBALANCE",CurrencyFormatter.formatNumber(suma.toString(),nroDecimales, ","));
                parameters.put("ENCAB_BALANCE",CurrencyFormatter.formatNumber(mult.toString(),2, ","));
                parameters.put("SALDO", encab.getSaldo_unit_ant());

                if (detalle!=null){
                    String dividendo = consultarDividendoEdoCuenta(parametros.get(0),parametros.get(2),usuario);
                    if (dividendo.equals(""))
                        parameters.put("DIVIDENDO", "");
                    else
                        parameters.put("DIVIDENDO", dividendo);
                }else
                    parameters.put("DIVIDENDO", "");
            }
            parameters.put("CANT_DECIMALES", String.valueOf(nroDecimales));

            JasperReport jr = (JasperReport) JRLoader.loadObject(getClass().getResourceAsStream("/jasper/estadoCuenta.jasper"));
            jr.setProperty("net.sf.jasperreports.query.executer.factory.plsql" ,"com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
            JRProperties.setProperty(QueryExecuterFactory.QUERY_EXECUTER_FACTORY_PREFIX + "plsql", "com.jaspersoft.jrx.query.PlSqlQueryExecuterFactory");
            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, conexion);
            inputStream =  new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));

        }catch(Exception ex){
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects(conexion,null,null);
        }

        return inputStream;
    }
    /*
    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "OtherInvestmentsIo.GuardarLog";
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
        final String origen = "OtherInvestmentsIo.guardarExcepcion";
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
    }  */

    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }
}

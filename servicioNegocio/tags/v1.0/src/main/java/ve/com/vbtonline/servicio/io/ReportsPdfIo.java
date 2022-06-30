package ve.com.vbtonline.servicio.io;

import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.TransformTable;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class ReportsPdfIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(ReportsPdfIo.class);

    /** Constructor de la clase
     */
    public ReportsPdfIo() {
    }

    public List<String> consultarCabeceraEdoCuenta (String num_cta, String mes , String anio) throws Exception {
        final String origen = "ReportsPdfIo.consultarCabeceraEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> cabecera=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin pruebas_ana.vbt_PDFCuentasEdoCuenta_Enc_pr(?,?,?,?,?); end;");
////
            statement.setString(1, num_cta);
            statement.setString(2, mes);
            statement.setString(3, anio);

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
                cabecera.add(result.getString(1));    //Cliente
                cabecera.add(result.getString(2));    // currency
                cabecera.add(result.getString(3));
                cabecera.add(result.getString(4));
                cabecera.add(result.getString(5));
                cabecera.add(result.getString(6));
                cabecera.add(result.getString(7));
                cabecera.add(result.getString(8));
                cabecera.add(result.getString(9));
                cabecera.add(result.getString(10));
                cabecera.add(result.getString(11));
                cabecera.add(result.getString(12));
                cabecera.add(result.getString(13));
                cabecera.add(result.getString(14));
                cabecera.add(result.getString(15));

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (cabecera);
    }

    public List<String> consultarCabeceraEdoCuentaTDC (String num_cta, String codproserv , String mes) throws Exception {
        final String origen = "ReportsPdfIo.consultarCabeceraEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> cabecera=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin pruebas_ana.vbt_PDFTDCEdoCta_Enc_pr(?,?,?,?,?); end;");
////
            statement.setString(1, num_cta);
            statement.setString(2, codproserv);
            statement.setString(3, mes);

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
                cabecera.add(result.getString(1));    //num_cta_plast_ppal
                cabecera.add(result.getString(2));    // desprodserv
                cabecera.add(result.getString(3));    // nombre_cliente_cta
                cabecera.add(result.getString(4));   // direccion_uno
                cabecera.add(result.getString(5));    // direccion_dos
                cabecera.add(result.getString(6));    // direccion_tres
                cabecera.add(result.getString(7));    // zona_postal
                cabecera.add(result.getString(8));    // lim_credito
                cabecera.add(result.getString(9));    // credito_disp
                cabecera.add(result.getString(10));   // fecha_factura
                cabecera.add(result.getString(11));   // pag_total
                cabecera.add(result.getString(12));   // pag_min_mes
                cabecera.add(result.getString(13));   // fec_pag_antes
                cabecera.add(result.getString(14));   // saldo_actual
                cabecera.add(result.getString(15));   // cuo_ven
                cabecera.add(result.getString(16));   // imp_ven
                cabecera.add(result.getString(17));   // cuo_mes
                cabecera.add(result.getString(18));   // int_bon
                cabecera.add(result.getString(19));   // tas_int
                cabecera.add(result.getString(20));     // tasa_mora
                cabecera.add(result.getString(21));   // periodo_fact
                cabecera.add(result.getString(22));   //  sal_anterior
                cabecera.add(result.getString(23));   // cargos
                cabecera.add(result.getString(24));   // abonos
                cabecera.add(result.getString(25));   // tpo_tdc
                cabecera.add(result.getString(26));   // observaciones
//
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (cabecera);
    }

    public List<List<String>> consultarTablaEdoCuenta (String num_cta, String mes , String anio) throws Exception {
        final String origen = "ReportsPdfIo.consultarTablaEdoCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> datos=new ArrayList<String>();
        List<List<String>> tabla=new ArrayList<List<String>>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin pruebas_ana.vbt_PDFCuentasEdoCuenta_Det_pr(?,?,?,?,?); end;");
//            vbt_PDFCuentasEdoCuenta_Det_pr (p_strNumeroCuenta    		IN VARCHAR2,
//                    p_strCmbMes				IN VARCHAR2,
//                    p_strTxtA񯉉		IN VARCHAR2,
//                    p_vbt_PDFCtasEdoCtaDet 	OUT vbt_PDFCtasEdoCtaDet,
//                    p_resultado OUT VARCHAR2)
////
            statement.setString(1, num_cta);
            statement.setString(2, mes);
            statement.setString(3, anio);

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);

//            TO_CHAR(DET.MOVFECHOPE,'DD/MM/YYYY') FECHAOPERACION,
//                    TO_CHAR(DET.MOVFECHVAL,'DD/MM/YYYY') FECHAVALOR,
//                    DET.DESCMOV,
//                    DET.REFBANMOV REFERENCIA,
//                    DET.MTOMOV

            while (result.next()){
                datos = new ArrayList<String>();
                datos.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                datos.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                datos.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                datos.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                datos.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                tabla.add(datos);

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tabla);
    }



    public List<List<String>> consultarTablaEdoCuentaTDC (String num_cta, String codproserv , String mes) throws Exception {
        final String origen = "ReportsPdfIo.consultarTablaEdoCuentaTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> datos=new ArrayList<String>();
        List<List<String>> tabla=new ArrayList<List<String>>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin pruebas_ana.vbt_PDFTDCEdoCta_Det_pr(?,?,?,?,?); end;");
//            vbt_PDFCuentasEdoCuenta_Det_pr (p_strNumeroCuenta    		IN VARCHAR2,
//                    p_strCmbMes				IN VARCHAR2,
//                    p_strTxtA񯉉		IN VARCHAR2,
//                    p_vbt_PDFCtasEdoCtaDet 	OUT vbt_PDFCtasEdoCtaDet,
//                    p_resultado OUT VARCHAR2)
////
            statement.setString(1, num_cta);
            statement.setString(2, codproserv);
            statement.setString(3, mes);

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (4);

//

            while (result.next()){
                datos = new ArrayList<String>();
                datos.add(NullFormatter.formatHtmlBlank(result.getString(1))); //nro_plastico
                datos.add(NullFormatter.formatHtmlBlank(result.getString(2))); //fec_trn
                datos.add(NullFormatter.formatHtmlBlank(result.getString(3))); //num_ref
                datos.add(NullFormatter.formatHtmlBlank(result.getString(4))); //descripcion
                datos.add(NullFormatter.formatHtmlBlank(result.getString(5))); //cat_comercio
                datos.add(NullFormatter.formatHtmlBlank(result.getString(6))); //mto_trn_ml
                datos.add(NullFormatter.formatHtmlBlank(result.getString(7))); //tpo_trn
                datos.add(NullFormatter.formatHtmlBlank(result.getString(8))); //mto_trn_mo
                datos.add(NullFormatter.formatHtmlBlank(result.getString(9))); //tas_cam
                datos.add(NullFormatter.formatHtmlBlank(result.getString(10))); //dsc_moneda
                datos.add(NullFormatter.formatHtmlBlank(result.getString(11))); //nro_plastico_formato
                tabla.add(datos);

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tabla);
    }

    public List<String> consultarDatosCliente (String cliente) throws Exception {
        final String origen = "ReportsPdfIo.consultarDatosCliente";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> datos=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin pruebas_ana.vbt_PDFColOperacionDetalle_pr(?,?,?); end;");

            statement.setString(1, cliente);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                datos = new ArrayList<String>();
                datos.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                datos.add(NullFormatter.formatHtmlBlank(result.getString(2)));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (datos);
    }


    public List<String> consultarCeritificadoApertura (String codIntrumento) throws Exception {
        final String origen = "ReportsPdfIo.consultarCeritificadoApertura";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> datos=new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_PDFCertiApertura_certi_pr(?,?,?); end;");

//            vbt_PDFCertiApertura_certi_pr (p_strCodInstrumento    IN VARCHAR2,
//                    p_vbt_PDFCertiApertura_certi OUT vbt_PDFCertiApertura_certi,
//                    p_resultado OUT VARCHAR2)

            statement.setString(1, codIntrumento);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);


//            SELECT C.na nombre_cliente,
//            C.direnvio direccion,
//            TD.codmon codigo_moneda,
//            DECODE(TD.codmon,'USD','US$','VEB','Bs','EUR','EUR','GBP','£','CHF','CHF',TD.codmon) simbolo_moneda,
//                    to_char(TD.fechaper,'dd/mm/yyyy') fecha_apertura,
//                    to_char(TD.fechvenc,'dd/mm/yyyy') fecha_vencimiento,
//                    to_char(TD.mtoapert,'999G999G999G999G999G999G999G999G999G999G999G990D99') monto_apertura,
//                    to_char(TD.mtovcto,'999G999G999G999G999G999G999G999G999G999G999G990D99') monto_vencimiento,
//                    to_char(TD.intvcto,'999G999G999G999G999G999G999G999G999G999G999G990D99') intereses,
//                    to_char(TD.tasaorg,'999G999G999G999G999G999G999G999G999G999G999G990D99') tasa,
//                    to_char(TD.fechaper,'mm') mes_apertura,
//                    DECODE(NVL(TD.fechaper,''),'','',TRIM(TO_CHAR(TD.fechaper, 'Month','nls_date_language=english')) || ', ' || TO_CHAR(TD.fechaper, 'dd yyyy','nls_date_language=english')) fecha_apertura
            datos = new ArrayList<String>();
            String clienteSecundario="";
            while (result.next()){
                if (result.getString(14).equals("-1")){
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(6)));

                    datos.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(7)),2,","));
                    datos.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(8)),2,","));
                    datos.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(9)),2,","));
                    datos.add(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(10)),2,","));


                    datos.add(NullFormatter.formatHtmlBlank(result.getString(11)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(12)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(13)));
                }else{
                    clienteSecundario=result.getString(1);
                }
            }
            if ((clienteSecundario!=null)&&(!clienteSecundario.equalsIgnoreCase("")))
                datos.add(NullFormatter.formatHtmlBlank(clienteSecundario));
            else
                datos.add(clienteSecundario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (datos);
    }

    public List<List<String>> consultarmovimientosCertificado (String codIntrumento,String estatus) throws Exception {
        final String origen = "ReportsPdfIo.consultarmovimientosApertura";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> datos=new ArrayList<String>();
        List<List<String>> tabla=new ArrayList<List<String>>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            if(estatus.equalsIgnoreCase("vencida")){

                statement  = connection.prepareCall ("begin vbtonline.vbt_PDFCertivenci_certi_pr(?,?,?); end;");
                statement.setString(1, codIntrumento);

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject (2);

                while (result.next()){
                    datos = new ArrayList<String>();
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                    tabla.add(datos);

                }
            }   else {
                statement  = connection.prepareCall ("begin vbtonline.vbt_PDFCertiApertura_movi_pr(?,?,?); end;");
                statement.setString(1, codIntrumento);

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AccountsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject (2);

                while (result.next()){
                    datos = new ArrayList<String>();
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                    datos.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                    tabla.add(datos);

                }
            }





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (tabla);
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "ReportsPdfIo.GuardarLog";
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

}

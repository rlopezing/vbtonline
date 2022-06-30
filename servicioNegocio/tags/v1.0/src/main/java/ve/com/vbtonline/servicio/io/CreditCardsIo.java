package ve.com.vbtonline.servicio.io;

import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.ContentSelectOd;
import ve.com.vbtonline.servicio.od.CreditCardsOd;
import ve.com.vbtonline.servicio.od.SelectOd;
import ve.com.vbtonline.servicio.od.TableOd;
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
public class CreditCardsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(CreditCardsIo.class);

    /** Constructor de la clase
     */
    public CreditCardsIo() {
    }


    public CreditCardsOd Cargar (String transaccionId, CreditCardsOd ccod) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (creditCardsOd);
    }

    public SelectOd cargarTarjetasEstadoCuentaTDC (String carteras, String idioma) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTarjetasTDCITT (String carteras, String idioma) throws Exception {
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

                valor = NullFormatter.formatBlank(result.getString(5));
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

    public SelectOd cargarMesesEstadoCuentaTDC (String nroCuenta) throws Exception {
        final String origen = "CreditCardsIo.cargarMesesEstadoCuentaTDC";
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public List<String> consultarDetallesEstadoCuentaTDC(List<String> parametros) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public TableOd consultarSaldosEstadoCuentaTDC (List<String> parametros, String idioma) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public TableOd cargarMovimientosTDCITT (List<String> parametros, String idioma) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
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





}

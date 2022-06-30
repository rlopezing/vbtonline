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
public class MutualFundsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(MutualFundsIo.class);

    /** Constructor de la clase
     */
    public MutualFundsIo() {
    }


    public MutualFundsOd Cargar (String transaccionId, MutualFundsOd mfod) throws Exception {
        final String origen = "MutualFundsIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        MutualFundsOd mutualFundsOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            mutualFundsOd=new MutualFundsOd();
            mutualFundsOd.setId(mfod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MutualFundsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (mutualFundsOd);
    }

    public CuentasOd cargarFondosMutuales (String carteras, String idioma) throws Exception {
        final String origen = "MutualFundsIo.cargarFondosMutuales";
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

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

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
                valor = strCodigoFondoAux + "|" + strCodigoCarteraAux;
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalles);
    }

    public SelectOd cargarTipoTransaccionBT (List<String> parametros, String Idioma) throws Exception {
        final String origen = "MutualFundsIo.cargarTipoTransaccionBT";
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

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public List<String> consultarDetalleFondosMutualesBT (List<String> parametros) throws Exception {
        final String origen = "MutualFundsIo.consultarDetalleFondosMutualesBT";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        SelectOd select=new SelectOd();
        String respuesta=null;
        List<String> listaDetalles = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_sal_saldos_pr(?,?,?,?); end;");
//          vbt_fondos_sal_saldos_pr (p_strCodigoCartera IN VARCHAR2,
//            p_strCodigoFondo IN VARCHAR2,
//                    p_vbt_fondos_sal_saldos OUT vbt_fondos_sal_saldos,
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaDetalles);
    }

    public TableOd consultarSaldosTransFondosMutuales (List<String> parametros, String idioma) throws Exception {
        final String origen = "MutualFundsIo.consultarSaldosTransFondosMutuales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_sal_trans_pr(?,?,?,?,?,?,?,?,?); end;");
//            vbt_fondos_sal_trans_pr (p_TAGEnTransito  IN VARCHAR2,
//                    p_strCodigoFondo   IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//                    p_strCmbDiasConsulta IN VARCHAR2,
//                    p_strTxtFechaDesde  IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
//                    p_strTipoTransaccion IN VARCHAR2,
//                    p_vbt_fondos_sal_trans OUT SYS_REFCURSOR,
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

            statement.execute ();

            respuesta = statement.getString(9);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

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
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGTotalMoneda"));
            if("0000062901".equals(parametros.get(6)))
              header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGValorNominal"));
            else
              header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGVUI"));
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGRescates"));
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGSuscripciones"));
            header.add(ResourceBundle.getBundle("FondosSaldos"+idioma).getString("TAGStatus"));



            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public TableOd consultarBloqueosFondosMutuales (List<String> parametros, String idioma) throws Exception {
        final String origen = "MutualFundsIo.consultarSaldosTransFondosMutuales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_fondos_blo_movimi_pr(?,?,?,?,?,?,?); end;");
//            PROCEDURE vbt_fondos_blo_movimi_pr (p_strCmbBuscar  IN VARCHAR2,
//            p_strCodigoFondo   IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//            p_strTxtFechaDesde IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
//            p_vbt_fondos_blo_movimi OUT SYS_REFCURSOR,
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MutualFundsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

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
            header.add(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("FondosBloqueos"+idioma).getString("TAGMontoInversion"));




            listaMovimientos=new TableOd();

            listaMovimientos= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AccountsIo.class+" | "+origen);


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
        final String origen = "MutualFundsIo.GuardarLog";
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

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
public class TimeDepositsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(TimeDepositsIo.class);

    /** Constructor de la clase
     */
    public TimeDepositsIo() {
    }


    public TimeDepositsOd Cargar (String transaccionId, TimeDepositsOd tdod) throws Exception {
        final String origen = "TimeDepositsIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TimeDepositsOd timeDepositsOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            timeDepositsOd=new TimeDepositsOd();
            timeDepositsOd.setId(tdod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TimeDepositsIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (timeDepositsOd);
    }

    public TableOd consultarColocacionesCertificados (List<String> parametros, String idioma) throws Exception {
        final String origen = "TimeDepositsIo.consultarColocacionesCertificados";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            fecha = consultarFechaCierre(parametros.get(0));
            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_colocacerti_coloca_pr(?,?,?,?,?,?,?,?); end;");
////

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(fecha==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, fecha);

            if(parametros.get(1)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, parametros.get(2));

            if(parametros.get(3)==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5, parametros.get(3));



            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);
            String sql =  statement.getString(8);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            String strVencida = new String();
            String strVigente = new String();
            if(idioma.equalsIgnoreCase("_ve_es")){
                strVencida = "Vencida";
                strVigente = "Vigente";
            }else{
                strVencida = "Matured";
                strVigente = "Current";
            }
            while (result.next()){
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowColocaciones.getColumnAt(0))%></td>
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowColocaciones.getColumnAt(2))%></td>
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowColocaciones.getColumnAt(3))%></td>
//                <td align="center"><%=NullFormatter.formatHtmlBlank(rowColocaciones.getColumnAt(1))%></td>
//                <td align="right"><%=CurrencyFormatter.formatNumber(rowColocaciones.getColumnAt(4),2,",")%></td>
//                <td align="center"><%=(NullFormatter.formatHtmlBlank(rowColocaciones.getColumnAt(7)).equals("vencida")) ? "<font color='red'>" + strVencida + "</font>": strVigente %></td>
                body=new ArrayList<String>();
                String valor=NullFormatter.formatHtmlBlank(result.getString(9))+"|"+
                        CurrencyFormatter.formatNumber(result.getString(6),2,",")+"|"+
                        CurrencyFormatter.formatNumber(result.getString(7),4,",")+"|"+
                        NullFormatter.formatHtmlBlank(result.getString(1))+"|"+
                        NullFormatter.formatHtmlBlank(result.getString(3))+"|"+
                        NullFormatter.formatHtmlBlank(result.getString(4))+"|"+
                        NullFormatter.formatHtmlBlank(result.getString(2))+"|"+
                        CurrencyFormatter.formatNumber(result.getString(5), 2, ",")+"|"+
                        NullFormatter.formatHtmlBlank(result.getString(8));
                body.add("<img onclick='abrirDetalleTabla_certificados(this)' class='btn_descripcion_certificados'  src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+valor+"' type='button' >");
                body.add(result.getString(1));
                body.add(result.getString(3));
                body.add(result.getString(4));
                body.add(result.getString(2));

                body.add(CurrencyFormatter.formatNumber(result.getString(5), 2, ","));
//                body.add(result.getString(8));
                body.add((NullFormatter.formatHtmlBlank(result.getString(8)).equals("vencida")) ? "<font color='red'>" + strVencida + "</font>": strVigente);
                body.add("<img style='cursor:pointer' onclick='imprimir_detalle_certificados(\""+valor+"\")' src='../vbtonline/resources/images/comun/impresora.gif' border=0 width='18' height='15'>");

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGNumeroColocacion"));
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGFechaApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGFechaVencimiento"));
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGMoneda"));
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGMontoApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGEstatus"));
            header.add(ResourceBundle.getBundle("ColocacionesCertificados"+idioma).getString("TAGImprimirCertificado"));


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

    public CuentasOd cargarColocaciones (String carteras, String idioma) throws Exception {
        final String origen = "TimeDepositsIo.cargarColocaciones";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> cuentas= new ArrayList<ContentSelectOd>();
        ContentSelectOd cuenta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        CuentasOd datos = new CuentasOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

//
            statement  = connection.prepareCall ("begin vbtonline.vbt_colocasaldos_pr(?,?,?); end;");

            if(carteras== null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            String strNumeroColocacionAux = new String();
            String strCodigoCarteraAux = new String();
            String fecha = new String();
            while (result.next()){
                strNumeroColocacionAux  = new String();
                strCodigoCarteraAux = new String();
                valor = new String();
                label  = new String();
                strNumeroColocacionAux  = NullFormatter.formatBlank(result.getString(1));
                strCodigoCarteraAux = NullFormatter.formatBlank(result.getString(3));
                valor = strNumeroColocacionAux + "|" + strCodigoCarteraAux;
                label  = strNumeroColocacionAux + " (" + ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGCartera")+ ": " + strCodigoCarteraAux + ")";
                fecha = result.getString(4);
                cuenta.setLabel(label);
                cuenta.setValue(valor);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();

            }
            datos.setContenido(cuentas);
            datos.setFecha(fecha);

            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (datos);
    }

    public TableDetalleOd consultarColocacionesSaldos (List<String> parametros, String idioma) throws Exception {
        final String origen = "TimeDepositsIo.consultarColocacionesSaldos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        TableDetalleOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_colocaope_pr(?,?,?,?); end;");
//           CEDURE vbt_colocaope_pr (p_strCodigoCartera IN VARCHAR2,
//            p_strNumeroColocacion IN VARCHAR2,
//                    p_vbt_colocaope OUT vbt_colocaope,
//            p_resultado OUT VARCHAR2)



            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            List<List<String>> bodys=new ArrayList();



            List<String> body=new ArrayList<String>();

            while (result.next()){
                body=new ArrayList<String>();
                body.add("<img style='cursor:pointer'  onclick='abrirDetalleTabla_DetailsTimeDeposit(this)' class='mostrar_detalle_btn' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(12))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))
                         +"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(3))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+CurrencyFormatter.formatNumber(result.getString(5), 4, ",")
                         +"|"+CurrencyFormatter.formatNumber(result.getString(6), 2, ",")+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(10))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))+"' type='button'  >");
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(CurrencyFormatter.formatNumber(result.getString(5), 4, ","));
                body.add(CurrencyFormatter.formatNumber(result.getString(6), 2, ","));
                bodys.add(body);

            }

            List<String> header=new ArrayList<String>();
//
            header.add("");
            header.add(ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGReferencia"));
            header.add(ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGTasaDeCambio"));
            header.add(ResourceBundle.getBundle("ColocacionesSaldos"+idioma).getString("TAGMonto"));


            listaMovimientos=new TableDetalleOd();

            listaMovimientos= TransformTable.getTableDetalle(header, bodys);



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

    public DetalleCuentaColocacionesOd consultarColocacionesDetalle (List<String> parametros) throws Exception {
        final String origen = "TimeDepositsIo.DetalleCuentaColocacionesOd";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        DetalleCuentaColocacionesOd detalle = new DetalleCuentaColocacionesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_saldoscolo_pr(?,?,?,?); end;");
//           p_strCodigoCartera IN VARCHAR2,
//            p_strNumeroColocacion IN VARCHAR2,
//                    p_vbt_saldoscolo OUT vbt_saldoscolo,
//            p_resultado OUT VARCHAR2)

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
                detalle = new DetalleCuentaColocacionesOd();
                detalle.setClientePrincipal(NullFormatter.formatBlank(result.getString(10)));
                detalle.setMoneda(NullFormatter.formatBlank(result.getString(2)));
                detalle.setFechaApertura(NullFormatter.formatBlank(result.getString(3)));
                detalle.setFechaVencimiento(NullFormatter.formatBlank(result.getString(4)));

                if(!result.getString(5).equalsIgnoreCase(""))
                    detalle.setMontoApertura(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(5)),2,","));
                else
                    detalle.setMontoApertura("");

                if(!result.getString(6).equalsIgnoreCase(""))
                    detalle.setBloqueado(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(6)), 2, ","));
                else
                    detalle.setBloqueado("");

                if(!result.getString(7).equalsIgnoreCase(""))
                    detalle.setMontoVencimiento(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(7)), 2, ","));
                else
                    detalle.setMontoVencimiento("");

                if(!result.getString(8).equalsIgnoreCase(""))
                    detalle.setTasa(CurrencyFormatter.formatNumber(NullFormatter.formatBlank(result.getString(8)), 4, ","));
                else
                    detalle.setTasa("");

                detalle.setFechaCierre(NullFormatter.formatBlank(result.getString(9)));
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

        return (detalle);
    }

    public TableOd consultarColocacionesVencimientos (List<String> parametros, String idioma) throws Exception {
        final String origen = "TimeDepositsIo.consultarColocacionesVencimientos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        DetalleCuentaEdoCtaOd detalle = new DetalleCuentaEdoCtaOd();
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            fecha = consultarFechaCierre(parametros.get(0));
            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_colocavenci_coloca_pr(?,?,?,?,?,?,?,?); end;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(fecha==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, fecha);

            if(parametros.get(1)==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, parametros.get(1));

            if(parametros.get(2)==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, parametros.get(2));

            if(parametros.get(3)==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5, parametros.get(3));



            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);
            String sql = statement.getString(8);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            String strVencida = new String();
            String strVigente = new String();
            if(idioma.equalsIgnoreCase("_ve_es")){
                strVencida = "Vencida";
                strVigente = "Vigente";
            }else{
                strVencida = "Matured";
                strVigente = "Current";
            }
            while (result.next()){
                body=new ArrayList<String>();
                body.add("<img onclick='abrirDetalleTabla_ColocacionesVencimientos(this)' style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(9))+"' type='button' >");
                body.add(result.getString(1));
                body.add(result.getString(4));
                body.add(result.getString(2));
                body.add(CurrencyFormatter.formatNumber(result.getString(5), 2, ","));
                body.add(CurrencyFormatter.formatNumber(result.getString(7), 4, ","));
                body.add(CurrencyFormatter.formatNumber(result.getString(6), 2, ","));
                body.add((NullFormatter.formatHtmlBlank(result.getString(8)).equals("vencida")) ? "<font color='red'>" + strVencida + "</font>": strVigente);

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGNumeroColocacion"));
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGFechaVencimiento"));
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGMoneda"));
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGMontoApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGTasa"));
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGMontoVencimiento"));
            header.add(ResourceBundle.getBundle("ColocacionesVencimientos"+idioma).getString("TAGEstatus"));

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

    public TableOd consultarColocacionesBloqueos (List<String> parametros, String idioma) throws Exception {
        final String origen = "TimeDepositsIo.consultarColocacionesBloqueos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaMovimientos;
        String fecha = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_colobloquemovi_pr(?,?,?,?,?,?,?); end;");
//            vbt_colobloquemovi_pr (p_strNumeroColocacion IN VARCHAR2,
//                    p_strCodigoCartera IN VARCHAR2,
//                    p_strCmbBuscar        IN VARCHAR2,
//                    p_strTxtFechaDesde IN VARCHAR2,
//                    p_strTxtFechaHasta IN VARCHAR2,
//                    p_vbt_colobloquemovi OUT SYS_REFCURSOR,
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



            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
//
                body=new ArrayList<String>();
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(CurrencyFormatter.formatNumber(result.getString(4), 2, ","));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("ColocacionesBloqueos"+idioma).getString("TAGFechaOperacion"));
            header.add(ResourceBundle.getBundle("ColocacionesBloqueos"+idioma).getString("TAGFechaValor"));
            header.add(ResourceBundle.getBundle("ColocacionesBloqueos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("ColocacionesBloqueos"+idioma).getString("TAGMonto"));

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

    public String consultarFechaCierre (String carteras) throws Exception {
        final String origen = "TimeDepositsIo.consultarColocacionesCertificados";
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (fecha);
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "TimeDepositsIo.GuardarLog";
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

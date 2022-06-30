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
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Rgodoy
 * Date: 12/07/12
 * Time: 11:20 AM
 * To change this template use File | Settings | File Templates.
 */
public class TimeDepositsIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(TimeDepositsIo.class);
    private LoggerIo loggerIo;
    /** Constructor de la clase **/

    public TimeDepositsIo() {
    }

    public TimeDepositsOd Cargar (String transaccionId, TimeDepositsOd tdod, VBTUsersOd usuario) throws Exception {
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (timeDepositsOd);
    }

    public TableOd consultarColocacionesCertificados (List<String> parametros, String idioma,  VBTUsersOd usuario) throws Exception {
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

            fecha = consultarFechaCierre(parametros.get(0), usuario);
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public CuentasOd cargarColocaciones (String carteras, String idioma, VBTUsersOd usuario) throws Exception {
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (datos);
    }

    public TableDetalleOd consultarColocacionesSaldos (List<String> parametros, String idioma,  VBTUsersOd usuario) throws Exception {
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public DetalleCuentaColocacionesOd consultarColocacionesDetalle (List<String> parametros,  VBTUsersOd usuario) throws Exception {
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public TableOd consultarColocacionesVencimientos (List<String> parametros, String idioma,  VBTUsersOd usuario) throws Exception {
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

            fecha = consultarFechaCierre(parametros.get(0), usuario);
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public TableOd consultarColocacionesBloqueos (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaMovimientos);
    }

    public String consultarFechaCierre (String carteras,  VBTUsersOd usuario) throws Exception {
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
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (fecha);
    }

            public LoggerIo getLoggerIo() {
        return loggerIo;
    }

            public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }

    public CuentasOd cargarCuentas (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.cargarCuentas";
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

            statement  = connection.prepareCall ("begin TransferenciaHandler.cuentas_corri_pr(?,?,?,?); end;");

            if(Carteras==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,Carteras.toString());
            }
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            String sql = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
//
            String numeroCtaAux = new String();
            String codigoCarteraAux = new String();
            String Disponible = new String();
            String cuentaValor = new String();
            String fechaCierre = new String();
            String descripcion = new String();
            String moneda = new String();
            List<String> monedas = new ArrayList<String>();
            while (result.next()){
                numeroCtaAux = result.getString(1);
                codigoCarteraAux = result.getString(3);
                Disponible = result.getString(5);
                fechaCierre = result.getString(4);
                cuentaValor = numeroCtaAux + " | "+ codigoCarteraAux;
                descripcion = numeroCtaAux + " (" +ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGDisponible")+" : "+ CurrencyFormatter.formatNumber(Disponible, 2, ",")+
                        " " + NullFormatter.formatBlank(result.getString(6)) + ") - (" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCartera") + " : " + codigoCarteraAux + ")";
                moneda = result.getString(6);
                monedas.add(moneda);

                cuenta.setValue(cuentaValor);
                cuenta.setLabel(descripcion);
                cuenta.setExtra(Disponible);
                cuenta.setExtra1(moneda);
                cuenta.setExtra2(descripcion);
                cuenta.setValor(descripcion);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();
            }


            select.setContenido(cuentas);
            select.setFecha(fechaCierre);
            select.setMoneda(moneda);
            select.setMonedas(monedas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public CuentasOd cargarCuentasTD (String Carteras, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.cargarCuentasTD";
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

            statement  = connection.prepareCall ("begin TransferenciaHandler.cuentas_corri_TD(?,?,?,?); end;");

            if(Carteras==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,Carteras.toString());
            }
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            String sql = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();

            String numeroCtaAux = new String();
            String codigoCarteraAux = new String();
            String Disponible = new String();
            String cuentaValor = new String();
            String fechaCierre = new String();
            String descripcion = new String();
            String moneda = new String();
            String totalDisponible = new String();
            List<String> monedas = new ArrayList<String>();
            while (result.next()){
                numeroCtaAux = result.getString(1);
                codigoCarteraAux = result.getString(3);
                Disponible = result.getString(5);
                fechaCierre = result.getString(4);
                cuentaValor = numeroCtaAux + " | "+ codigoCarteraAux;
                descripcion = numeroCtaAux + " (" +ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGDisponible")+" : "+ CurrencyFormatter.formatNumber(Disponible, 2, ",")+
                        " " + NullFormatter.formatBlank(result.getString(6)) + ") - (" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCartera") + " : " + codigoCarteraAux + ")";
                moneda = result.getString(6);
                totalDisponible = result.getString(7);
                monedas.add(moneda);

                cuenta.setValue(cuentaValor);
                cuenta.setLabel(descripcion);
                cuenta.setExtra(Disponible);
                cuenta.setExtra1(moneda);
                cuenta.setExtra2(descripcion);
                cuenta.setExtra3(codigoCarteraAux);
                cuenta.setExtra4(totalDisponible);
                cuenta.setValor(descripcion);
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();
            }

            select.setContenido(cuentas);
            select.setFecha(fechaCierre);
            select.setMoneda(moneda);
            select.setMonedas(monedas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }
        return (select);
    }

    public List<ContentSelectOd> cargarTiposTD (String tipo, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.cargarTiposTD";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;

        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> tipoTDs= new ArrayList<ContentSelectOd>();
        List<ContentSelectOd> plazosTD= new ArrayList<ContentSelectOd>();
        ContentSelectOd tiposTD = new ContentSelectOd();
        TimeDepositsOd select=new TimeDepositsOd();
        String label, valor;
        String respuesta=null;
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);
            time = System.currentTimeMillis ();
            connection=getConnection();
            statement  = connection.prepareCall ("begin VBTONLINE_TRANS.VBTONLINE.VBT_CONSULTAR_TIPOSTD(?,?,?); end;");
            if(tipo==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,tipo);
            }
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject (2);

            String tipoTD = new String();
            String limiteMinimoTD = new String();
            String codTipoTD = new String();

            while (result.next()){
                codTipoTD = result.getString(1);
                tipoTD = result.getString(2);
                limiteMinimoTD = result.getString(3);

                tiposTD.setValue(codTipoTD);
                tiposTD.setLabel(tipoTD);
                tiposTD.setExtra2(limiteMinimoTD);
                tiposTD.setExtra(tipoTD);
                tipoTDs.add(tiposTD);
                tiposTD=new ContentSelectOd();
            }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }
        return (tipoTDs);
    }

    public List<ContentSelectOd> cargarPlazosTD (String tipo, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.cargarPlazosTD";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;

        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> plazosTD= new ArrayList<ContentSelectOd>();
        ContentSelectOd plazoTDs = new ContentSelectOd();
        TimeDepositsOd select=new TimeDepositsOd();
        String label, valor;
        String respuesta=null;
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);
            time = System.currentTimeMillis ();
            connection=getConnection();
            statement  = connection.prepareCall ("begin VBTONLINE_TRANS.VBTONLINE.VBT_CONSULTAR_PLAZOSTD(?,?,?); end;");
            if(tipo==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,tipo);
            }
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject (2);

            String codPlazoTD = new String();
            String plazoTD = new String();
            String plazoFinTD = new String();

            while (result.next()){
                codPlazoTD = result.getString(1);
                plazoTD = result.getString(2);
                plazoFinTD = result.getString(3);

                plazoTDs.setValue(codPlazoTD);
                plazoTDs.setLabel(plazoTD);
                plazoTDs.setExtra(plazoTD);
                plazoTDs.setExtra1(plazoFinTD);
                plazosTD.add(plazoTDs);
                plazoTDs=new ContentSelectOd();
            }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }
        return (plazosTD);
    }

    public List<ContentSelectOd> cargarPlazosPrefTD (String tipo, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.cargarPlazosTD";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;

        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> plazosTD= new ArrayList<ContentSelectOd>();
        ContentSelectOd plazoTDs = new ContentSelectOd();
        TimeDepositsOd select=new TimeDepositsOd();
        String label, valor;
        String respuesta=null;
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);
            time = System.currentTimeMillis ();
            connection=getConnection();
            statement  = connection.prepareCall ("begin VBTONLINE_TRANS.VBTONLINE.CONSUL_PLAZOS_PREF(?,?,?); end;");
            if(tipo==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1,tipo);
            }
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();
            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject (2);

            String codPlazoTD = new String();
            String plazoTD = new String();
            String plazoFinTD = new String();

            while (result.next()){
                codPlazoTD = result.getString(1);
                plazoTD = result.getString(2);
                plazoFinTD = result.getString(3);

                plazoTDs.setValue(codPlazoTD);
                plazoTDs.setLabel(plazoTD);
                plazoTDs.setExtra(plazoTD);
                plazoTDs.setExtra1(plazoFinTD);
                plazosTD.add(plazoTDs);
                plazoTDs=new ContentSelectOd();
            }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }
        return (plazosTD);
    }

    public TableOd cargarTablaSolicitudTD (List<String> parametros, String idioma,  VBTUsersOd usuario) throws Exception {
        final String origen = "TimeDepositsIo.cargarTablaSolicitudTD";
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

            statement  = connection.prepareCall ("begin vbtonline.VBT_CONSULTAR_SOLICITUDES_TD(?,?,?); end;");

            statement.setString(1, usuario.getNumContrato());

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TimeDepositsIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){

                body=new ArrayList<String>();
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(!NullFormatter.isBlank(result.getString(3)) ? result.getString(3).substring(0,1).toUpperCase() + result.getString(3).substring(1).toLowerCase():"");
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(7)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(8)));
                if(!NullFormatter.isBlank(result.getString(9)))
                    body.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("descripcionEstatus_"+result.getString(9)));

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGIDAperturaTD"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGFechaApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGTipoApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGCuentaApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGMonedaApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGMontoApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGPlazoApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGTasaApertura"));
            header.add(ResourceBundle.getBundle("ColocacionesTD"+idioma).getString("TAGEstatusApertura"));

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

    public String insertarSolicitudesTD (TimeDepositsOd TD, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TimeDepositsIo.cargarCuentas";
        Connection connection = null;
        CallableStatement statement = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String resum= new String();
        String respuesta=null;
        String montoTD = TD.getMonto();
        String plazoTD = TD.getPlazo();
        String monedaTD = TD.getMoneda();
        String cuentaTD = TD.getCuenta();
        String tipoTD = TD.getTipo();
        String cartera = TD.getCartera();
        String ID_solicitudTD = "";
        String tipoSolicitudTD = "";
        String fechaTD = "";
        String tasaTD = TD.getTasa();
        String estatusTD = "";
        String DescripcionTipoTD = TD.getDescripcionTipoTD();

        String strNumeroCuentaDebito = new String();
        String strCodigoCarteraDebito = new String();

        try {
            time = System.currentTimeMillis ();

            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin VBTONLINE.VBT_REGISTRAR_SOLICITUD_TD(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            statement.setString(1,ID_solicitudTD);

            statement.setString(2,tipoSolicitudTD);

            statement.setString(3,usuario.getLogin());

            statement.setDouble(4,Double.parseDouble(montoTD));

            statement.setString(5,fechaTD);

            statement.setString(6,String.valueOf(plazoTD));

            statement.setString(7,String.valueOf(tasaTD));

            statement.setString(8,String.valueOf(monedaTD));

            statement.setString(9,String.valueOf(cuentaTD));

            statement.setString(10,estatusTD);

            statement.setString(11,String.valueOf(tipoTD));

            statement.setString(12,usuario.getNumContrato());

            statement.setString(13,String.valueOf(cartera));

            statement.registerOutParameter(14, OracleTypes.VARCHAR );
            statement.registerOutParameter(1, OracleTypes.VARCHAR );

            statement.execute ();

            respuesta = statement.getString(14);
            resum = (respuesta);

            if (!respuesta.equalsIgnoreCase("OK")){
                connection.rollback();
                resum = (respuesta);
            }else{

                /** Si está OK se inserta en libro de ordenes de crm **/
                statement  = connection.prepareCall ("begin LIBRO_ORDENES.PR_INS_FLUJO_DOC_TD(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
                /*TRANSFERENCIA INTERNA ENTRE CUENTAS*/
                StringBuilder detalle = new StringBuilder();
                detalle.append("<BR>Cartera Origen : ").append(cartera).append(" - Cuenta: ").append(cuentaTD);

                statement.setString(1,ID_solicitudTD);
                statement.setString(2,"APER_TD");
                statement.setString(3,usuario.getLogin());
                statement.setString(4,String.valueOf(montoTD));
                statement.setString(5,fechaTD);
                statement.setString(6,String.valueOf(plazoTD));
                statement.setString(7,String.valueOf(tasaTD));
                statement.setString(8,String.valueOf(monedaTD));
                statement.setString(9,String.valueOf(cuentaTD));
                statement.setString(10,"P");
                statement.setString(11,String.valueOf(tipoTD));
                statement.setString(12,usuario.getNumContrato());
                statement.setString(13,String.valueOf(cartera));
                statement.setString(14,usuario.getIP());
                statement.registerOutParameter(15, OracleTypes.VARCHAR );
                statement.registerOutParameter(16, OracleTypes.VARCHAR );

                statement.execute ();

                respuesta = statement.getString(16);

                if (!respuesta.equalsIgnoreCase("OK"))  {
                    loggerIo.guardarExcepcion(usuario.getIP(), "Catch #26"+ "Error Procedimiento:  LIBRO_ORDENES.PR_INS_FLUJO_DOC_TD", origen,"", usuario.getLogin(), usuario.getNumContrato());
                    throw (new Exception (respuesta,null));
                }else{
                    connection.commit();
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                }
            }

        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
          }
        return (resum);
    }

    public String obtenerTasaTD (TimeDepositsOd TD, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "TimeDepositsIo.obtenerTasaTD";
        Connection connection = null;
        CallableStatement statement = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String resum = new String();
        String respuesta=null;
        String respTasa=null;
        String montoTD = TD.getMonto();
        String plazoTD = TD.getPlazo();
        String monedaTD = TD.getMoneda();
        String tipoTD = TD.getTipo();
        String tasaTD = "";

        try {
            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBTONLINE.VBT_CONSULTAR_TASATD(?,?,?,?,?,?); end;");

            statement.setString(1,String.valueOf(tipoTD));

            statement.setDouble(2,Double.parseDouble(montoTD));

            statement.setString(3,String.valueOf(plazoTD));

            statement.setString(4,String.valueOf(monedaTD));

            statement.registerOutParameter(5, OracleTypes.VARCHAR );

            statement.registerOutParameter(6, OracleTypes.VARCHAR );

            statement.execute ();

            respuesta = statement.getString(5);

            respTasa = statement.getString(6);

            resum = respTasa;
        }
        catch (Exception ex) {
            logger.error(ex);
        }
        return (resum);
    }

    public String validarParametrosPersonalesTD (String monto, VBTUsersOd usuario, String codcartera, String codcuenta) throws Exception {
        final String origen = "TimeDepositsIo.validarParametrosPersonalesTD";
        Connection connection = null;
        CallableStatement statement = null;

        List<String> resultados= new ArrayList<String>();
        long  time = 0;

        String label, valor, balanceminimo;

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.validar_pp_td(?,?,?,?,?,?,?,?,?); end;");

            if(usuario.getCodpercli()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getCodpercli());

            if(monto==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setDouble(2,Double.parseDouble(monto));

            if(usuario.getNumContrato()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, usuario.getNumContrato());

            if(usuario.getTipoContrato()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, usuario.getTipoContrato());

            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            if(codcartera==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, codcartera);

            if(codcuenta==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8, codcuenta);

            statement.registerOutParameter(9, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);
            resultados.add(respuesta);
            balanceminimo = (statement.getString(9));

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);

        }
        catch (Exception ex) {
            try{
                connection.rollback();
            }
            catch (Exception ext) {
            }
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }
        return (balanceminimo);
    }

}
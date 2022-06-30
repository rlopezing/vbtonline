package ve.com.vbtonline.servicio.io;

import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.AllMyPortafolioOd;
import ve.com.vbtonline.servicio.od.TablasConLinkOd;
import ve.com.vbtonline.servicio.od.TablasOd;
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
public class AllMyPortafolioIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(AllMyPortafolioIo.class);

    /** Constructor de la clase
     */
    public AllMyPortafolioIo() {
    }


    public AllMyPortafolioOd Cargar (String transaccionId, AllMyPortafolioOd ampod) throws Exception {
        final String origen = "AllMyPortafolioIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        AllMyPortafolioOd allMyPortafolioOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            allMyPortafolioOd=new AllMyPortafolioOd();
            allMyPortafolioOd.setId(ampod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AllMyPortafolioIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (allMyPortafolioOd);
    }

    public List<String> cargarCarterasPortafolio (String login) throws Exception {
        final String origen = "AllMyPortafolioIo.cargarCarterasPortafolio";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> carterasPortafolio = new ArrayList<String>();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen);

            time = System.currentTimeMillis ();

              connection=getConnection();

              statement  = connection.prepareCall ("BEGIN vbtonline.vbt_portafo_conso_user_pr(?,?,?); END;");

              if(login == null){
                  statement.setNull(1, OracleTypes.NULL);
              }else
                  statement.setString(1, login);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);


            while (result.next()){
                carterasPortafolio.add(result.getString(1));
            }





            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AllMyPortafolioIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (carterasPortafolio);
    }

    public String cargarSaldoFacturado (String p_num_cta, String p_codproserv ) throws Exception {
        final String origen = "AllMyPortafolioIo.cargarSaldoFacturado";
        Connection connection2 = null;
        CallableStatement statement2 = null;
        ResultSet result2 = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        String monto = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen);

            time = System.currentTimeMillis ();

              connection2=getConnection();

              statement2  = connection2.prepareCall ("BEGIN vbtonline.vbt_porta_con_saldo_pr(?,?,?,?); END;");


            if(p_num_cta == null){
                  statement2.setNull(1, OracleTypes.NULL);
              }else
                  statement2.setString(1, p_num_cta);

            if(p_codproserv == null){
                  statement2.setNull(2, OracleTypes.NULL);
              }else
                  statement2.setString(2, p_codproserv);

            statement2.registerOutParameter(3, OracleTypes.CURSOR);
            statement2.registerOutParameter(4, OracleTypes.VARCHAR);

            statement2.execute ();

            respuesta = statement2.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result2 = (ResultSet) statement2.getObject (3);


            while (result2.next()){
                monto = result2.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AllMyPortafolioIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection2, statement2, result2);
        }

        return (monto);
    }

    public String cargarSaldoNoFacturado (String p_num_cta ) throws Exception {
        final String origen = "AllMyPortafolioIo.cargarSaldoNoFacturado";
        Connection connection2 = null;
        CallableStatement statement2 = null;
        ResultSet result2 = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        String monto = new String();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen);

            time = System.currentTimeMillis ();

              connection2=getConnection();

              statement2  = connection2.prepareCall ("BEGIN vbtonline.vbt_porta_con_det_pr(?,?,?); END;");

            if(p_num_cta == null){
                  statement2.setNull(1, OracleTypes.NULL);
              }else
                  statement2.setString(1, p_num_cta);



            statement2.registerOutParameter(2, OracleTypes.CURSOR);
            statement2.registerOutParameter(3, OracleTypes.VARCHAR);

            statement2.execute ();

            respuesta = statement2.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result2 = (ResultSet) statement2.getObject (2);


            while (result2.next()){
                monto = result2.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AllMyPortafolioIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection2, statement2, result2);
        }

        return (monto);
    }

    public TableOd cargarTablas (String cartera, String accion, String login, String idioma) throws Exception {
        final String origen = "AllMyPortafolioIo.cargarCarterasPortafolio";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        List<String> carterasPortafolio = new ArrayList<String>();
        TableOd listaMovimientos;
        TablasConLinkOd tablasConLink= new TablasConLinkOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ AllMyPortafolioIo.class+" | "+origen);

            time = System.currentTimeMillis ();

              connection=getConnection();

              statement  = connection.prepareCall ("BEGIN vbtonline.vbt_porta_con_movi_pr(?,?,?,?,?); END;");

              if(cartera == null){
                  statement.setNull(1, OracleTypes.NULL);
              }else
                  statement.setString(1, cartera);

            if(accion == null){
                  statement.setNull(2, OracleTypes.NULL);
              }else
                  statement.setString(2, accion);

            if(login == null){
                statement.setNull(3, OracleTypes.NULL);
            }else
                statement.setString(3, login);

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);

            listaMovimientos=new TableOd();

            if (respuesta.equalsIgnoreCase("OK")){


            result = (ResultSet) statement.getObject (4);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            List<String> listaLink = new ArrayList<String>();
            String link= new String();
            Double saldoFacturado   = 0.00;
            Double saldoNoFacturado = 0.00;
            String saldoFacturado2 = new String();
            String saldoNoFacturado2 = new String();
            while (result.next()){
                if(accion.equalsIgnoreCase("verCuentas")){
                    body=new ArrayList<String>();
//                    link=  "<a  style='cursor:pointer' onclick='seleccionarVerCuentasOpcion(this.id)' title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesCuenta")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) + " | " + cartera+"' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
                    link=  "<a  style='cursor:pointer;font-weight: bold;'  title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesCuenta")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) + " | " + cartera+"' onclick='consultarSaldosDesdePortafolio(this.id)' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
                    body.add(link);
                    body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    body.add(((!NullFormatter.isBlank(result.getString(3))) ? CurrencyFormatter.formatNumber(result.getString(3), 2, ",") : "0.00"));
                    bodys.add(body);
                }else if(accion.equalsIgnoreCase("verColocaciones")){
                    body=new ArrayList<String>();
//                    link=  "<a style='cursor:pointer' onclick='seleccionarVerColocacionesOpcion(this.id)' title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesColocacion")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) + " | " + cartera+"' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
                    link=  "<a style='cursor:pointer;font-weight: bold;'  title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesColocacion")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) + "|" + cartera+"' onclick='BTColocacionesDesdePortafolio(this.id)' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
                    body.add(link);
                    body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    body.add(((!NullFormatter.isBlank(result.getString(3))) ? CurrencyFormatter.formatNumber(result.getString(3), 2, ",") : "0.00"));
                    bodys.add(body);
                }else if(accion.equalsIgnoreCase("verFondos")){
                    body=new ArrayList<String>();
                    link=  "<a style='cursor:pointer;font-weight: bold;'  title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesFondo")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(4)) + "|" + cartera+"' onclick='BTFMDesdePortafolio(this.id)' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
//                    link=  "<a style='cursor:pointer' onclick='seleccionarVerFondosOpcion(this.id)' title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesFondo")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(4)) + " | " + cartera+"' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
                    body.add(link);
                    body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    body.add(((!NullFormatter.isBlank(result.getString(3)))? CurrencyFormatter.formatNumber(result.getString(3),2,","):"0.00"));
                    bodys.add(body);

                }else if(accion.equalsIgnoreCase("verOtrasInv")){
                    body=new ArrayList<String>();
                    link=  "<a style='cursor:pointer;font-weight: bold;'  title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesFondo")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(4)) + "|" + cartera+"' onclick='BTOIDesdePortafolio(this.id)' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
//                    link=  "<a style='cursor:pointer' onclick='seleccionarVerOtrasInvOpcion(this.id)' title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleVerDetallesFondo")+"' id='"+NullFormatter.formatHtmlBlank(result.getString(4)) + " | " + cartera+"' >"+NullFormatter.formatHtmlBlank(result.getString(1)+"</a>");
                    body.add(link);
                    body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                    body.add(((!NullFormatter.isBlank(result.getString(3)))? CurrencyFormatter.formatNumber(result.getString(3),2,","):"0.00"));
                    bodys.add(body);

                }else if(accion.equalsIgnoreCase("verTarjetas")){
                    body=new ArrayList<String>();

                    saldoFacturado   = 0.00;
                    saldoNoFacturado = 0.00;
                    saldoFacturado2 = cargarSaldoFacturado(result.getString(3), "002");

                    if (!NullFormatter.isBlank(saldoFacturado2))
                        saldoFacturado = Double.parseDouble(saldoFacturado2);

                    saldoNoFacturado2 = cargarSaldoNoFacturado(result.getString(3));

                    if (!NullFormatter.isBlank(saldoNoFacturado2))
                         saldoNoFacturado = Double.parseDouble(saldoNoFacturado2);

                    link=  "<a style='cursor:pointer;font-weight: bold;'  title='"+ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTitleTarjetasCredito")+"'  id='"+NullFormatter.formatHtmlBlank(result.getString(3))+"' onclick='BTTDCDesdePortafolio(this.id)' >"+NullFormatter.formatHtmlBlank(result.getString(1))+"</a>";
                    body.add(link);
                    body.add("USD");
                    body.add(CurrencyFormatter.formatNumber(saldoFacturado + saldoNoFacturado,2,","));
                    bodys.add(body);

                }

            }

            List<String> header=new ArrayList<String>();
//
            if(accion.equalsIgnoreCase("verCuentas")){
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGNumeroCuenta"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGMoneda"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGSaldoActual"));
            }else if(accion.equalsIgnoreCase("verColocaciones")){
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGNumeroColocacion"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGMoneda"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGMontoApertura"));
            }else if(accion.equalsIgnoreCase("verFondos")){
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGNombreFondo"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGMoneda"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTotalEnMoneda"));
            }else if(accion.equalsIgnoreCase("verOtrasInv")){
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGNombreOtrasInversiones"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGMoneda"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGTotalEnMoneda"));
            }else if(accion.equalsIgnoreCase("verTarjetas")){
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGNumeroCuenta"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGMoneda"));
                header.add(ResourceBundle.getBundle("PortafolioConsolidado"+idioma).getString("TAGSaldoActual"));
            }

            listaMovimientos= TransformTable.getTable(header, bodys);
            } else{
                List<String> header=new ArrayList<String>();
                List<List<String>> bodys=new ArrayList();
                listaMovimientos= TransformTable.getTable(header, bodys);
            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ AllMyPortafolioIo.class+" | "+origen);

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



}

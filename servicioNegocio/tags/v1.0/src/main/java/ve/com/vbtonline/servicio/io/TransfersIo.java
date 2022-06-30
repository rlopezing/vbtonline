/**/package ve.com.vbtonline.servicio.io;

import com.venezolano.util.mail.MailManager;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import com.venezolano.webutil.ClientContext;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.TransformTable;

import java.io.Serializable;
import java.net.InetAddress;
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
public class TransfersIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(TransfersIo.class);
    private Map session;

    /** Constructor de la clase
     */
    public TransfersIo() {
    }


    public TransfersOd Cargar (String transaccionId, TransfersOd tod) throws Exception {
        final String origen = "TransfersIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TransfersOd transfersOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            transfersOd=new TransfersOd();
            transfersOd.setId(tod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (transfersOd);
    }

    public TableOd consultTransfers (String carteras, String numContrato, String Status, String idioma) throws Exception {
        final String origen = "TransfersIo.consultTransfers";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.transfe_consu_fc_pr(?,?,?,?,?,?); end;");

            if(carteras == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,carteras);

            if(numContrato == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,numContrato);

            if (Status== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,Status);

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

              statement.execute ();

            respuesta = statement.getString(5);
            String sql = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject (4);

            List<List<String>> bodys=new ArrayList();

            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add("<div id='div_"+result.getString(1)+"'><input type='checkbox' class='datos verificarSelecccionTrans' name='"+result.getInt(1) +
                        "|"+result.getString(3) +
                        "|"+result.getString(10)+"|"+result.getString(9) +"|"+result.getString(6) +"|"+result.getString(7) +"|"+CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(4)), 2, ",")
                        +"|"+result.getString(2) +"|"+result.getString(13) +"|"+result.getString(14) +"|"+result.getString(15) +"|"+result.getString(16) +"|"+result.getString(17) +"' value='0' id='"+result.getString(1) +"' onclick='cambiarValorTransfer(this.id)' /></div>");
                body.add(result.getString(7));
                body.add(result.getString(10));
                body.add(result.getString(9));
                body.add(result.getString(6));
                body.add(result.getString(3));
                if (result.getString(13).equalsIgnoreCase("TEO")){
                    body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_TEO"));
                }else{
                    body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_CID"));
                }
                body.add(CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(4)), 2, ","));
                //body.add(result.getString(2));
                if (result.getString(2).equalsIgnoreCase("Liberada"))
                    body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("Liberada2"));
                else
                    body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString(result.getString(2)));
                body.add("<img class='detalle_resumen_transferencia' id='detalle_"+result.getString(1)+"' width='20' src='../vbtonline/resources/images/search.png' alt='' />");
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Numero_de_Referencia"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Cuenta_Debito"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Cuenta_Credito"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Beneficiario"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Fecha"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_tipo"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Monto"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Estatus"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tag_FirmasConjuntas_Detalle"));

            listaTransferencias=new TableOd();

            listaTransferencias= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (listaTransferencias);
    }

    public List<String> consultTransfers_detalle_fc (String carteras, String numContrato, String numref) throws Exception {
        final String origen = "TransfersIo.consultTransfers_detalle_fc";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<String> DetalleTransferencia_fc=new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.transfe_consu_fc_detalle_pr(?,?,?,?,?,?); end;");

            if(carteras == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,carteras);

            if(numContrato == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,numContrato);

            if (numref== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,numref);

            statement.registerOutParameter(4, OracleTypes.CURSOR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(5);
            String sql = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject (4);



            if(result.next ()) {
                DetalleTransferencia_fc.add(result.getString(1));

                if (!result.getString(24).equalsIgnoreCase("")){
                    if ( result.getString(24).split("-")[0].trim().equalsIgnoreCase("FW")){
                        DetalleTransferencia_fc.add("ABA-"+result.getString(24).split("-")[1].trim());
                    }else if ( result.getString(24).split("-")[0].trim().equalsIgnoreCase("SA")){
                        DetalleTransferencia_fc.add("SWIFT-"+result.getString(24).split("-")[1].trim());
                    }else if ( result.getString(24).split("-")[0].trim().equalsIgnoreCase("CH")){
                        DetalleTransferencia_fc.add("CHIPS-"+result.getString(24).split("-")[1].trim());
                    }else if ( result.getString(24).split("-")[0].trim().equalsIgnoreCase("ACCOUNT")){
                        DetalleTransferencia_fc.add("ACCOUNT-"+result.getString(24).split("-")[1].trim());
                    }else{
                        DetalleTransferencia_fc.add(result.getString(24));
                    }
                }else{
                    if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("FW")){
                        DetalleTransferencia_fc.add("ABA-"+result.getString(2).split("-")[1].trim());
                    }else if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("SA")){
                        DetalleTransferencia_fc.add("SWIFT-"+result.getString(2).split("-")[1].trim());
                    }else if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("CH")){
                        DetalleTransferencia_fc.add("CHIPS-"+result.getString(2).split("-")[1].trim());
                    }else if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("ACCOUNT")){
                        DetalleTransferencia_fc.add("ACCOUNT-"+result.getString(2).split("-")[1].trim());
                    }else{
                        DetalleTransferencia_fc.add(result.getString(2));
                    }
                }

            /*    if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("FW")){
                    DetalleTransferencia_fc.add("ABA-"+result.getString(2).split("-")[1].trim());
                }else if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("SA")){
                    DetalleTransferencia_fc.add("SWIFT-"+result.getString(2).split("-")[1].trim());
                }else if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("CH")){
                    DetalleTransferencia_fc.add("CHIPS-"+result.getString(2).split("-")[1].trim());
                }else if ( result.getString(2).split("-")[0].trim().equalsIgnoreCase("ACCOUNT")){
                    DetalleTransferencia_fc.add("ACCOUNT-"+result.getString(2).split("-")[1].trim());
                }else{
                    DetalleTransferencia_fc.add(result.getString(2));
                }    */



                DetalleTransferencia_fc.add(result.getString(3));
                DetalleTransferencia_fc.add(result.getString(4));
                DetalleTransferencia_fc.add(result.getString(5));
                DetalleTransferencia_fc.add(result.getString(6));
                DetalleTransferencia_fc.add(result.getString(7));
                DetalleTransferencia_fc.add(result.getString(8));
                DetalleTransferencia_fc.add(result.getString(9));

                //DetalleTransferencia_fc.add(result.getString(10));


                if (!result.getString(25).equalsIgnoreCase("")){

                    if ( result.getString(25).split("-")[0].trim().equalsIgnoreCase("FW")){
                        DetalleTransferencia_fc.add("ABA-"+result.getString(25).split("-")[1].trim());
                    }else if ( result.getString(25).split("-")[0].trim().equalsIgnoreCase("SA")){
                        DetalleTransferencia_fc.add("SWIFT-"+result.getString(25).split("-")[1].trim());
                    }else if ( result.getString(25).split("-")[0].trim().equalsIgnoreCase("CH")){
                        DetalleTransferencia_fc.add("CHIPS-"+result.getString(25).split("-")[1].trim());
                    }else if ( result.getString(25).split("-")[0].trim().equalsIgnoreCase("ACCOUNT")){
                        DetalleTransferencia_fc.add("ACCOUNT-"+result.getString(25).split("-")[1].trim());
                    }else{
                        DetalleTransferencia_fc.add(result.getString(10));
                    }
                }else{
                    if ( result.getString(10).split("-")[0].trim().equalsIgnoreCase("FW")){
                        DetalleTransferencia_fc.add("ABA-"+result.getString(10).split("-")[1].trim());
                    }else if ( result.getString(10).split("-")[0].trim().equalsIgnoreCase("SA")){
                        DetalleTransferencia_fc.add("SWIFT-"+result.getString(10).split("-")[1].trim());
                    }else if ( result.getString(10).split("-")[0].trim().equalsIgnoreCase("CH")){
                        DetalleTransferencia_fc.add("CHIPS-"+result.getString(10).split("-")[1].trim());
                    }else if ( result.getString(10).split("-")[0].trim().equalsIgnoreCase("ACCOUNT")){
                        DetalleTransferencia_fc.add("ACCOUNT-"+result.getString(10).split("-")[1].trim());
                    }else{
                        DetalleTransferencia_fc.add(result.getString(10));
                    }
                }


                DetalleTransferencia_fc.add(result.getString(11));
                DetalleTransferencia_fc.add(result.getString(12));
                DetalleTransferencia_fc.add(result.getString(13));
                DetalleTransferencia_fc.add(result.getString(14));
                DetalleTransferencia_fc.add(result.getString(15));
                DetalleTransferencia_fc.add(CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(16)), 2, ","));
                DetalleTransferencia_fc.add(result.getString(17));
                DetalleTransferencia_fc.add(result.getString(18));
                DetalleTransferencia_fc.add(result.getString(20));
                DetalleTransferencia_fc.add(result.getString(21));
                DetalleTransferencia_fc.add(result.getString(22));
                DetalleTransferencia_fc.add(result.getString(23));
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

        return (DetalleTransferencia_fc);
    }

    public TableOd consultarDirectorioTransfer (VBTUsersOd login, String idioma) throws Exception {
        final String origen = "TransfersIo.consultarDirectorioTransfer";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaDirectorio = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_directorio_pr(?,?,?); end;");
            //            vbt_directorio_pr (p_strLogin  IN VARCHAR2,
//                    p_vbt_directorio OUT vbt_directorio,
//                    p_resultado OUT VARCHAR2)
            if(login.getLogin() == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,login.getLogin());

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

              statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            List<List<String>> bodys=new ArrayList();


            String strNombrePaisBancoBenef = new String();
            String strCodigoPaisBancoBenef = new String();
            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                String status="";
                //Es Usuariod e firmas Conjuntas no requiere aprobacion
                if (login.getTipo().equalsIgnoreCase("6")){
                    body.add("<b><a id='"+result.getString(2)+ "|"+result.getString(1)+"' onclick='cargarTemplatePoppup(this.id)' style='cursor: pointer'>"+result.getString(2)+"</a><b>");
                }else{
                    if (result.getString(28).equalsIgnoreCase("S"))
                        body.add("<b><a id='"+result.getString(2)+ "|"+result.getString(1)+"' onclick='cargarTemplatePoppup(this.id)' style='cursor: pointer'>"+result.getString(2)+"</a><b>");
                    else
                        body.add("<b><a id='"+result.getString(2)+ "|"+result.getString(1)+"'>"+result.getString(2)+"</a><b>");
                }

                body.add(result.getString(3));
                body.add(result.getString(4));
                body.add(result.getString(5));
                if (!NullFormatter.isBlank(result.getString(6))) {
                    strNombrePaisBancoBenef = result.getString(6).substring((result.getString(6).indexOf("|")+1),result.getString(6).length());
                    strCodigoPaisBancoBenef = result.getString(6).substring(0, result.getString(6).indexOf("|"));
                }
                body.add(strNombrePaisBancoBenef);
                if (result.getString(28).equalsIgnoreCase("S")){
                    status=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGApproved");
                    body.add(status);
                    body.add("");
                }else{
                    //Es Usuariod e firmas Conjuntas no requiere aprobacion
                    if (login.getTipo().equalsIgnoreCase("6")){
                        status=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGApproved");
                        body.add(status);
                        body.add("");
                    }else{
                        status=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNoApproved");
                        body.add(status);
                        body.add("<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+result.getString(1)+"' type='button' value='+'  onclick='validarAprobacion(this.id)' >");
                    }
                }

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("Directorio"+idioma).getString("TAGTitleAlias"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuenta"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGBancoBeneficiario"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGPais"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTemplate"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGAprobarTemplate"));

            listaDirectorio=new TableOd();

            listaDirectorio= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (listaDirectorio);
    }

    public TableOd consultarDirectorio (VBTUsersOd login, String idioma) throws Exception {
        final String origen = "TransfersIo.consultarDirectorio";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaDirectorio = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_directorio_pr(?,?,?); end;");
            //            vbt_directorio_pr (p_strLogin  IN VARCHAR2,
//                    p_vbt_directorio OUT vbt_directorio,
//                    p_resultado OUT VARCHAR2)
            if(login.getLogin() == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,login.getLogin());

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            List<List<String>> bodys=new ArrayList();


            String strNombrePaisBancoBenef = new String();
            String strCodigoPaisBancoBenef = new String();
            while (result.next ()) {
                String status="";
                List<String> body=new ArrayList<String>();

                body.add("<b><a id='"+result.getString(1)+"' onclick='seleccionarTemplateEditar(this.id)' style='cursor: pointer' value='"+result.getString(1)+"'>"+result.getString(2)+"</a><b>");
                body.add(result.getString(3));
                body.add(result.getString(4));
                body.add(result.getString(5));
                if (!NullFormatter.isBlank(result.getString(6))) {
                    strNombrePaisBancoBenef = result.getString(6).substring((result.getString(6).indexOf("|")+1),result.getString(6).length());
                    strCodigoPaisBancoBenef = result.getString(6).substring(0, result.getString(6).indexOf("|"));
                }
                body.add(strNombrePaisBancoBenef);
                if (login.getTipo().contentEquals("6")){
                    status=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGApproved");
                }else{
                    if (result.getString(28).equalsIgnoreCase("S"))
                        status=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGApproved");
                    else
                        status=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNoApproved");
                }
                body.add(status);

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("Directorio"+idioma).getString("TAGTitleAlias"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuenta"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGBancoBeneficiario"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGPais"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTemplate"));

            listaDirectorio=new TableOd();

            listaDirectorio= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaDirectorio);
    }

    public String buscarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.buscarTemplate";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String resultado= new String();

        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta="OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_buscar_template_pr(?,?,?,?,?,?,?,?,?,?,?,?); end;");
//            (p_login                    IN VARCHAR2,
//            p_TxtNombreTemplate    IN VARCHAR2,
//                    p_TipoIdCtaCredito    IN VARCHAR2,
//            p_TxtCuentaCredito        IN VARCHAR2,
//                    p_TipoCodBancoBeneficiario  IN VARCHAR2,
//            p_CodBancoBeneficiario        IN VARCHAR2,
//                    p_TipoCodBancoIntermediario    IN VARCHAR2,
//            p_CodBancoBancoIntermediario    IN VARCHAR2,
//                    p_CuentaFuturoCredito            IN VARCHAR2,
//            p_CuentaCredito                  IN VARCHAR2,
//                    p_vbt_template_handler OUT SYS_REFCURSOR,
//            p_resultado OUT VARCHAR2) AS

            if(usuario.getLogin()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin());

            if(datos.getNombreTemplate()==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,datos.getNombreTemplate());

            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,datos.getBeneficiaryAccountBank());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,datos.getBeneficiaryAccount());

            if(datos.getBeneficiaryBankCodeType()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5, datos.getBeneficiaryBankCodeType());

            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,datos.getBeneficiaryBankCodeNumber());


            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,datos.getIntermediaryBankCodeNumber());

            if(datos.getIntermediaryBankCodeType()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8, datos.getIntermediaryBankCodeType());


            if(datos.getFurtherCreditAccount()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9,datos.getFurtherCreditAccount());

            statement.registerOutParameter(10, OracleTypes.CURSOR);
            statement.registerOutParameter(11, OracleTypes.VARCHAR);
            statement.registerOutParameter(12, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(11);
            String sql = statement.getString(12);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (10);

            while(result.next()){
              resultado = result.getString(1);
            }

            if(resultado.equalsIgnoreCase("")){
                resultado = "1";   //  1 = resultado de la acción fue exitoso.
                //  0 = resultado de la acción no fue exitoso porque el alias ya existe.
                // -1 = resultado de la acción no fue exitoso porque ya existen los datos de la cta beneficiario.
                // -2 = resultado de la acción no fue exitoso porque ya existe el alias y los datos de la cta beneficiario.
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

        return (resultado);
    }

    public String borrarDirectorio (String login, String codigo) throws Exception {
        final String origen = "TransfersIo.borrarDirectorio";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String resultado= new String();

        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta="OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_borrar_directorio_pr(?,?,?); end;");
//            (p_login                    IN VARCHAR2,
//            p_TxtNombreTemplate    IN VARCHAR2,
//                    p_TipoIdCtaCredito    IN VARCHAR2,
//            p_TxtCuentaCredito        IN VARCHAR2,
//                    p_TipoCodBancoBeneficiario  IN VARCHAR2,
//            p_CodBancoBeneficiario        IN VARCHAR2,
//                    p_TipoCodBancoIntermediario    IN VARCHAR2,
//            p_CodBancoBancoIntermediario    IN VARCHAR2,
//                    p_CuentaFuturoCredito            IN VARCHAR2,
//            p_CuentaCredito                  IN VARCHAR2,
//                    p_vbt_template_handler OUT SYS_REFCURSOR,
//            p_resultado OUT VARCHAR2) AS

            if(login==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,login);

            if(codigo==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,codigo);

            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String buscarTemplateEditar (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, Integer codTemplate ) throws Exception {
        final String origen = "TransfersIo.buscarTemplateEditar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String resultado= new String();

        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta="OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_busca_directorio_pr(?,?,?,?,?,?,?,?,?,?,?); end;");
//           p_login                            IN VARCHAR2,
//            p_codigoTemplate              IN VARCHAR2,
//                    p_strCmbTipoIdCtaCredito     IN VARCHAR2,
//            p_strTxtCuentaCredito        IN VARCHAR2,
//                    p_CmbTipoCodBancoBene        IN VARCHAR2,
//            p_strTxtCodBancoBene         IN VARCHAR2,
//                    p_CmbTipoCodBancoInterme    IN VARCHAR2,
//            p_TxtCodBancoBancoInter        IN VARCHAR2,
//                    p_TxtCuentaFuturoCredito    IN VARCHAR2,
//            p_vbt_busca_directorio      OUT vbt_busca_directorio,
//                    p_resultado                 OUT VARCHAR2

            if(usuario.getLogin()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin());

            if(codTemplate==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setInt(2, codTemplate);

            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,datos.getBeneficiaryAccountBank());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,datos.getBeneficiaryAccount());

            if(datos.getBeneficiaryBankCodeType()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5, datos.getBeneficiaryBankCodeType());

            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,datos.getBeneficiaryBankCodeNumber());


            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,datos.getIntermediaryBankCodeNumber());

            if(datos.getIntermediaryBankCodeType()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8, datos.getIntermediaryBankCodeType());


            if(datos.getFurtherCreditAccount()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9,datos.getFurtherCreditAccount());

            statement.registerOutParameter(10, OracleTypes.CURSOR);
            statement.registerOutParameter(11, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(11);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            result = (ResultSet) statement.getObject (10);
            while(result.next()){
              resultado = result.getString(1);
            }

            if(resultado.equalsIgnoreCase("")){
               resultado = "1";   //  1 = resultado de la acción fue exitoso.
                //  0 = resultado de la acción no fue exitoso porque el alias ya existe.
                // -1 = resultado de la acción no fue exitoso porque ya existen los datos de la cta beneficiario.
                // -2 = resultado de la acción no fue exitoso porque ya existe el alias y los datos de la cta beneficiario.
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

        return (resultado);
    }

    public String salvarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario) throws Exception {
        final String origen = "TransfersIo.salvarTemplate";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String resultado= new String();

        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta="NO OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();



            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_insertar_directorio_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//            vbt_insertar_directorio_pr(
//                    p_login  IN VARCHAR2,
//                    numContrato IN VARCHAR2,
//                    p_NombreBeneficiario IN VARCHAR2,
//                    p_TipoIdCtaCredito IN VARCHAR2,
//                    p_CuentaCredito IN VARCHAR2,
//                    p_EmailBeneficiario IN VARCHAR2,
//                    p_Direccion1Beneficiario IN VARCHAR2,
//                    p_Direccion2Beneficiario IN VARCHAR2,
//                    p_Direccion3Beneficiario IN VARCHAR2,
//                    p_TelefonoBeneficiario IN VARCHAR2,
//                    p_PaisBeneficiario IN VARCHAR2,
//                    p_TipoCodBancoBene IN VARCHAR2,
//                    p_CodBancoBene IN VARCHAR2,
//                    p_NombreBancoBene IN VARCHAR2,
//                    p_Direccion1BancoBene IN VARCHAR2,
//                    p_Direccion2BancoBene IN VARCHAR2,
//                    p_Direccion3BancoBene IN VARCHAR2,
//                    p_PaisDestino IN VARCHAR2,
//                    p_TipoCodBancoInterme IN VARCHAR2,
//                    p_CodBancoBancoInterme IN VARCHAR2,
//                    p_NombreBancoInterme IN VARCHAR2,
//                    p_Direccion1BancoInterme IN VARCHAR2,
//                    p_Direccion2BancoInterme IN VARCHAR2,
//                    p_Direccion3BancoInterme IN VARCHAR2,
//                    p_PaisBancoInterme IN VARCHAR2,
//                    p_CuentaFuturoCredito IN VARCHAR2,
//                    p_NombreFuturoCredito IN VARCHAR2,
//                    p_NombreTemplate IN VARCHAR2,
//                    p_resultado OUT VARCHAR2) AS
            if(usuario.getLogin()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin().toLowerCase());

            if(usuario.getNumContrato()==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,usuario.getNumContrato());


            if(datos.getBeneficiaryName()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,datos.getBeneficiaryName());

            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,datos.getBeneficiaryAccountBank());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5, datos.getBeneficiaryAccount().toUpperCase());

            if(datos.getBeneficiaryEmail()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,datos.getBeneficiaryEmail());


            if(datos.getBeneficiaryAddress1()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,datos.getBeneficiaryAddress1());

            if(datos.getBeneficiaryAddress2()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8,datos.getBeneficiaryAddress2());

            if(datos.getBeneficiaryAddress3()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9,datos.getBeneficiaryAddress3());

            if(datos.getBeneficiaryTelephone()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10, datos.getBeneficiaryTelephone());

            if(datos.getBeneficiaryCountryCode()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11, datos.getBeneficiaryCountryCode()+"|"+datos.getBeneficiaryCountry());

            if(datos.getBeneficiaryBankCodeType()==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,datos.getBeneficiaryBankCodeType());

            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(13,OracleTypes.NULL);
            else
                statement.setString(13,datos.getBeneficiaryBankCodeNumber().toUpperCase());

            if(datos.getBeneficiaryBankName()==null)
                statement.setNull(14,OracleTypes.NULL);
            else
                statement.setString(14,datos.getBeneficiaryBankName());

            if(datos.getBeneficiaryBankAddress1()==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,datos.getBeneficiaryBankAddress1());

            if(datos.getBeneficiaryBankAddress2()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,datos.getBeneficiaryBankAddress2());

            if(datos.getBeneficiaryBankAddress3()==null)
                statement.setNull(17,OracleTypes.NULL);
            else
                statement.setString(17, datos.getBeneficiaryBankAddress3());

            if(datos.getBeneficiaryBankCountryCode()==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,datos.getBeneficiaryBankCountryCode()+"|"+datos.getBeneficiaryBankCountry());

            if(datos.getIntermediaryBankCodeType()==null)
                statement.setNull(19,OracleTypes.NULL);
            else{
                if(!datos.getIntermediaryBankCodeType().equalsIgnoreCase("-2"))
                    statement.setString(19,datos.getIntermediaryBankCodeType());
                else
                    statement.setString(19,"");
            }


            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(20,OracleTypes.NULL);
            else
                statement.setString(20,datos.getIntermediaryBankCodeNumber().toUpperCase());

            if(datos.getIntermediaryBankName()==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,datos.getIntermediaryBankName());

            if(datos.getIntermediaryBankAddress1()==null)
                statement.setNull(22,OracleTypes.NULL);
            else
                statement.setString(22,datos.getIntermediaryBankAddress1());

            if(datos.getIntermediaryBankAddress2()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getIntermediaryBankAddress2());

            if(datos.getIntermediaryBankAddress3()==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,datos.getIntermediaryBankAddress3());

            if(datos.getIntermediaryBankCountryCode()==null)
                statement.setNull(25,OracleTypes.NULL);
            else{
                if(!datos.getIntermediaryBankCountryCode().equalsIgnoreCase("-2"))
                  if (datos.getIntermediaryBankCountryCode().equalsIgnoreCase(""))
                    statement.setString(25,"");
                  else
                     statement.setString(25,datos.getIntermediaryBankCountryCode()+"|"+datos.getIntermediaryBankCountry());
                else
                    statement.setString(25,"");
            }


            if(datos.getFurtherCreditAccount()==null)
                statement.setNull(26,OracleTypes.NULL);
            else
                statement.setString(26,datos.getFurtherCreditAccount());

            if(datos.getFurtherCreditName()==null)
                statement.setNull(27,OracleTypes.NULL);
            else
                statement.setString(27,datos.getFurtherCreditName());

            if(datos.getNombreTemplate()==null)
                statement.setNull(28,OracleTypes.NULL);
            else
                statement.setString(28, datos.getNombreTemplate());

           /* if(datos.getBeneficiaryBankCodeTypeSwift()==null)
                statement.setNull(29,OracleTypes.NULL);
            else{
                statement.setString(12,datos.getBeneficiaryBankCodeTypeSwift());
                if(datos.getBeneficiaryBankCodeType()!=null)
                    statement.setString(29,datos.getBeneficiaryBankCodeType());
                else
                    statement.setNull(29,OracleTypes.NULL);
            }  */

            if(datos.getBeneficiaryBankCodeTypeSwift()==null)
                statement.setNull(29,OracleTypes.NULL);
            else{
                statement.setString(29,datos.getBeneficiaryBankCodeTypeSwift());
            }

           /* if(datos.getBeneficiaryBankCodeNumberSwift()==null)
                statement.setNull(30,OracleTypes.NULL);
            else{
                statement.setString(13,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
                if(datos.getBeneficiaryBankCodeNumber()==null)
                    statement.setNull(30,OracleTypes.NULL);
                else
                    statement.setString(30,datos.getBeneficiaryBankCodeNumber().toUpperCase());
            }    */

            if(datos.getBeneficiaryBankCodeNumberSwift()==null)
                statement.setNull(30,OracleTypes.NULL);
            else{
                statement.setString(30,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
            }


          /*  if(datos.getIntermediaryBankCodeTypeSwift()==null)
                statement.setNull(31,OracleTypes.NULL);
            else{
                if(!datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("-2"))
                    statement.setString(19,datos.getIntermediaryBankCodeTypeSwift());
                else
                    statement.setString(19,"");
                if(!datos.getIntermediaryBankCodeType().equalsIgnoreCase("-2"))
                    statement.setString(31,datos.getIntermediaryBankCodeType());
                else
                    statement.setString(31,"");
            } */

            if(datos.getIntermediaryBankCodeTypeSwift()==null)
                statement.setNull(31,OracleTypes.NULL);
            else{
                statement.setString(31,datos.getIntermediaryBankCodeTypeSwift());
            }


          /*  if(datos.getIntermediaryBankCodeNumberSwift()==null)
                statement.setNull(32,OracleTypes.NULL);
            else{
                statement.setString(20,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
                if(datos.getIntermediaryBankCodeNumber()==null)
                    statement.setNull(32,OracleTypes.NULL);
                else
                    statement.setString(32,datos.getIntermediaryBankCodeNumber().toUpperCase());

            }  */

            if(datos.getIntermediaryBankCodeNumberSwift()==null)
                statement.setNull(32,OracleTypes.NULL);
            else{
                    statement.setString(32,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
            }


            statement.registerOutParameter(33, OracleTypes.VARCHAR);
            statement.registerOutParameter(34, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(33);
            String sql = statement.getString(34);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String editarTemplate (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String codTemplate) throws Exception {
        final String origen = "TransfersIo.editarTemplate";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String resultado= new String();

        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta="NO OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_update_directo_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            if(datos.getBeneficiaryName()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,datos.getBeneficiaryName());

            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,datos.getBeneficiaryAccountBank());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3, datos.getBeneficiaryAccount().toUpperCase());

            if(datos.getBeneficiaryEmail()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,datos.getBeneficiaryEmail());

            if(datos.getBeneficiaryAddress1()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,datos.getBeneficiaryAddress1());

            if(datos.getBeneficiaryAddress2()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,datos.getBeneficiaryAddress2());

            if(datos.getBeneficiaryAddress3()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,datos.getBeneficiaryAddress3());

            if(datos.getBeneficiaryTelephone()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8, datos.getBeneficiaryTelephone());

            if(datos.getBeneficiaryCountryCode()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9, datos.getBeneficiaryCountryCode() +"|"+datos.getBeneficiaryCountry());

            if(datos.getBeneficiaryBankCodeType()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10,datos.getBeneficiaryBankCodeType());

            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11,datos.getBeneficiaryBankCodeNumber().toUpperCase());

            if(datos.getBeneficiaryBankName()==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,datos.getBeneficiaryBankName());

            if(datos.getBeneficiaryBankAddress1()==null)
                statement.setNull(13,OracleTypes.NULL);
            else
                statement.setString(13,datos.getBeneficiaryBankAddress1());

            if(datos.getBeneficiaryBankAddress2()==null)
                statement.setNull(14,OracleTypes.NULL);
            else
                statement.setString(14,datos.getBeneficiaryBankAddress2());

            if(datos.getBeneficiaryBankAddress3()==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,datos.getBeneficiaryBankAddress3());

            if(datos.getBeneficiaryBankCountryCode()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,datos.getBeneficiaryBankCountryCode() +"|"+datos.getBeneficiaryBankCountry());

            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(17,OracleTypes.NULL);
            else
                statement.setString(17,datos.getIntermediaryBankCodeNumber().toUpperCase());


            if(datos.getIntermediaryBankCodeType()==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,datos.getIntermediaryBankCodeType());

            if(datos.getIntermediaryBankName()==null)
                statement.setNull(19,OracleTypes.NULL);
            else
                statement.setString(19,datos.getIntermediaryBankName());

            if(datos.getIntermediaryBankAddress1()==null)
                statement.setNull(20,OracleTypes.NULL);
            else
                statement.setString(20,datos.getIntermediaryBankAddress1());

            if(datos.getIntermediaryBankAddress2()==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,datos.getIntermediaryBankAddress2());

            if(datos.getIntermediaryBankAddress3()==null)
                statement.setNull(22,OracleTypes.NULL);
            else
                statement.setString(22,datos.getIntermediaryBankAddress3());

            if(datos.getIntermediaryBankCountryCode()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getIntermediaryBankCountryCode()+"|"+datos.getIntermediaryBankCountry());

            if(datos.getFurtherCreditAccount()==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,datos.getFurtherCreditAccount());

            if(datos.getFurtherCreditName()==null)
                statement.setNull(25,OracleTypes.NULL);
            else
                statement.setString(25,datos.getFurtherCreditName());

            if(usuario.getLogin()==null)
                statement.setNull(26,OracleTypes.NULL);
            else
                statement.setString(26, usuario.getLogin().toLowerCase());

            if(codTemplate==null)
                statement.setNull(27,OracleTypes.NULL);
            else
                statement.setString(27, codTemplate);


         /*   if(datos.getBeneficiaryBankCodeTypeSwift()==null)
                statement.setNull(28,OracleTypes.NULL);
            else{
                statement.setString(10,datos.getBeneficiaryBankCodeTypeSwift());
                if(datos.getBeneficiaryBankCodeType()!=null)
                    statement.setString(28,datos.getBeneficiaryBankCodeType());
                else
                    statement.setNull(28,OracleTypes.NULL);
            }*/


            if(datos.getBeneficiaryBankCodeTypeSwift()==null)
                statement.setNull(28,OracleTypes.NULL);
            else{

                statement.setString(28,datos.getBeneficiaryBankCodeTypeSwift());

            }


           /* if(datos.getBeneficiaryBankCodeNumberSwift()==null)
                statement.setNull(29,OracleTypes.NULL);
            else{
                statement.setString(11,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
                if(datos.getBeneficiaryBankCodeNumber()==null)
                    statement.setNull(29,OracleTypes.NULL);
                else
                    statement.setString(29,datos.getBeneficiaryBankCodeNumber().toUpperCase());
            }  */

            if(datos.getBeneficiaryBankCodeNumberSwift()==null)
                statement.setNull(29,OracleTypes.NULL);
            else{
                statement.setString(29,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
            }


           /* if(datos.getIntermediaryBankCodeTypeSwift()==null)
                statement.setNull(30,OracleTypes.NULL);
            else{
                if(!datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("-2"))
                    statement.setString(18,datos.getIntermediaryBankCodeTypeSwift());
                else
                    statement.setString(18,"");
                if(!datos.getIntermediaryBankCodeType().equalsIgnoreCase("-2"))
                    statement.setString(30,datos.getIntermediaryBankCodeType());
                else
                    statement.setString(30,"");
            } */

            if(datos.getIntermediaryBankCodeTypeSwift()==null)
                statement.setNull(30,OracleTypes.NULL);
            else{
                if(!datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("-2"))
                    statement.setString(30,datos.getIntermediaryBankCodeTypeSwift());
                else
                    statement.setString(30,"");
            }


          /*  if(datos.getIntermediaryBankCodeNumberSwift()==null)
                statement.setNull(31,OracleTypes.NULL);
            else{
                statement.setString(17,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
                if(datos.getIntermediaryBankCodeNumber()==null)
                    statement.setNull(31,OracleTypes.NULL);
                else
                    statement.setString(31,datos.getIntermediaryBankCodeNumber().toUpperCase());

            }  */



            if(datos.getIntermediaryBankCodeNumberSwift()==null)
                statement.setNull(31,OracleTypes.NULL);
            else{
               statement.setString(31,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());

            }


            statement.registerOutParameter(32, OracleTypes.VARCHAR);



            statement.execute ();

            respuesta = statement.getString(32);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public SummaryTransfersToOtherBanksOd cargarDetalleDirectorio (String login, String codigoTemplate) throws Exception {
        final String origen = "TransfersIo.cargarDetalleDirectorio";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaDirectorio = null;
        SummaryTransfersToOtherBanksOd datos = new SummaryTransfersToOtherBanksOd();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_directorio_agregar_pr(?,?,?,?); end;");
            //            vbt_directorio_pr (p_strLogin  IN VARCHAR2,
//                    p_vbt_directorio OUT vbt_directorio,
//                    p_resultado OUT VARCHAR2)
            if(login == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,login);

            if(codigoTemplate == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,codigoTemplate);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

              statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);

            List<List<String>> bodys=new ArrayList();



            while (result.next ()) {
        //                strTxtNombreTemplate =  NullFormatter.formatBlank(row.getColumnAt(1));
                        datos.setNombreTemplate(result.getString(2));
        //
        //                strTxtNombreBeneficiario = NullFormatter.formatBlank(row.getColumnAt(2));
                        if ((result.getString(3)==null) || ((result.getString(3).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryName("");
                         else
                           datos.setBeneficiaryName(result.getString(3));

                        if ((result.getString(7)==null)|| ((result.getString(7).equalsIgnoreCase("null"))))
                            datos.setAccount("");
                        else
                            datos.setAccount(result.getString(4));

                        if ((result.getString(7)==null)|| ((result.getString(7).equalsIgnoreCase("null"))))
                            datos.setAccountCode("");
                        else
                            datos.setAccountCode(result.getString(7));

                        if ((result.getString(8)==null)|| ((result.getString(8).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryEmail("");
                        else
                            datos.setBeneficiaryEmail(result.getString(8));

                        if ((result.getString(9)==null)|| ((result.getString(9).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryAddress1("");
                        else
                            datos.setBeneficiaryAddress1(result.getString(9));


                        if ((result.getString(10)==null) || ((result.getString(10).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryAddress2("");
                        else
                            datos.setBeneficiaryAddress2(result.getString(10));

                        if ((result.getString(11)==null) || ((result.getString(11).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryAddress3("");
                        else
                            datos.setBeneficiaryAddress3(result.getString(11));

                       if ((result.getString(12)==null) || ((result.getString(12).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryTelephone("");
                       else
                           datos.setBeneficiaryTelephone(result.getString(12));

                        if(!result.getString(13).isEmpty()) {
                            datos.setBeneficiaryCountryCode(result.getString(13).substring(0, result.getString(13).indexOf("|")));
                            datos.setBeneficiaryCountry(result.getString(13).substring(result.getString(13).indexOf("|") + 1, result.getString(13).length()));
                        }else{
                            if ((result.getString(13)==null)  || ((result.getString(13).equalsIgnoreCase("null"))))
                                datos.setBeneficiaryCountryCode("");
                            else
                                datos.setBeneficiaryCountryCode(result.getString(13));
                        }

                        if ((result.getString(14)==null) || ((result.getString(14).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankCodeType("");
                        else
                            datos.setBeneficiaryBankCodeType(result.getString(14));

                        if ((result.getString(15)==null)|| ((result.getString(15).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankCodeNumber("");
                        else
                            datos.setBeneficiaryBankCodeNumber(result.getString(15));

                        if ((result.getString(5)==null) || ((result.getString(5).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankName("");
                        else
                            datos.setBeneficiaryBankName(result.getString(5));

                        if ((result.getString(16)==null)|| ((result.getString(16).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankAddress1("");
                        else
                            datos.setBeneficiaryBankAddress1(result.getString(16));

                        if ((result.getString(17)==null)|| ((result.getString(17).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankAddress2("");
                        else
                            datos.setBeneficiaryBankAddress2(result.getString(17));

                        if ((result.getString(18)==null)|| ((result.getString(18).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankAddress3("");
                        else
                            datos.setBeneficiaryBankAddress3(result.getString(18));

                        if(!result.getString(6).isEmpty()) {
                            datos.setBeneficiaryBankCountryCode(result.getString(6).substring(0, result.getString(6).indexOf("|")));
                            datos.setBeneficiaryBankCountry(result.getString(6).substring(result.getString(6).indexOf("|") + 1, result.getString(6).length()));
                        }else {
                            if ((result.getString(6)==null)|| ((result.getString(6).equalsIgnoreCase("null"))))
                                datos.setBeneficiaryBankCountryCode("");
                            else
                                datos.setBeneficiaryBankCountryCode(result.getString(6));
                        }

                        if ((result.getString(19)==null)|| ((result.getString(19).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankCodeType("");
                        else
                            datos.setIntermediaryBankCodeType(result.getString(19));

                        if ((result.getString(20)==null)|| ((result.getString(20).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankCodeNumber("");
                        else
                            datos.setIntermediaryBankCodeNumber(result.getString(20));

                        if ((result.getString(21)==null)|| ((result.getString(21).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankName("");
                        else
                            datos.setIntermediaryBankName(result.getString(21));

                        if ((result.getString(22)==null)|| ((result.getString(22).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankAddress1("");
                        else
                            datos.setIntermediaryBankAddress1(result.getString(22));

                        if ((result.getString(23)==null)|| ((result.getString(23).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankAddress2("");
                        else
                            datos.setIntermediaryBankAddress2(result.getString(23));

                        if ((result.getString(24)==null)|| ((result.getString(24).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankAddress3("");
                        else
                            datos.setIntermediaryBankAddress3(result.getString(24));


                        if((result.getString(25)!=null)) {
                            datos.setIntermediaryBankCountryCode(result.getString(25).substring(0, result.getString(25).indexOf("|")));
                            datos.setIntermediaryBankCountry(result.getString(25).substring(result.getString(25).indexOf("|") + 1, result.getString(25).length()));
                        }else{
                            if ((result.getString(25)==null)|| ((result.getString(25).equalsIgnoreCase("null"))))
                                datos.setIntermediaryBankCountryCode("");
                            else
                                datos.setIntermediaryBankCountryCode(result.getString(25));
                        }

                        if ((result.getString(26)==null)|| ((result.getString(26).equalsIgnoreCase("null"))))
                            datos.setFurtherCreditAccount("");
                        else
                            datos.setFurtherCreditAccount(result.getString(26));

                        if ((result.getString(27)==null) || ((result.getString(27).equalsIgnoreCase("null"))))
                            datos.setFurtherCreditName("");
                        else
                            datos.setFurtherCreditName(result.getString(27));



                        datos.setStatusAprobacion(result.getString(28));

                        if ((result.getString(29)==null) || ((result.getString(29).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankCodeTypeSwift("");
                        else
                            datos.setBeneficiaryBankCodeTypeSwift(result.getString(29));

                        if ((result.getString(30)==null)|| ((result.getString(30).equalsIgnoreCase("null"))))
                            datos.setBeneficiaryBankCodeNumberSwift("");
                        else
                            datos.setBeneficiaryBankCodeNumberSwift(result.getString(30));

                        if ((result.getString(31)==null)|| ((result.getString(31).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankCodeTypeSwift("");
                        else
                            datos.setIntermediaryBankCodeTypeSwift(result.getString(31));

                        if ((result.getString(32)==null)|| ((result.getString(32).equalsIgnoreCase("null"))))
                            datos.setIntermediaryBankCodeNumberSwift("");
                        else
                            datos.setIntermediaryBankCodeNumberSwift(result.getString(32));
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

        return (datos);
    }

    public TableOd  consultarTransferenciasGeneral (List<String> parametros, String idioma) throws Exception {
        final String origen = "TransfersIo.consultarTransferenciasGeneral";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.transfe_consulta_pr(?,?,?,?,?,?,?); end;");

            if(parametros.get(0) == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if(parametros.get(1) == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));

            if (parametros.get(2)== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));

            if (parametros.get(3)== null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.get(3));

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

              statement.execute ();

            respuesta = statement.getString(6);
            String sql = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);

            List<List<String>> bodys=new ArrayList();

            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(12))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))+"|"+NullFormatter.formatHtmlBlank(result.getString(13))+"|"+NullFormatter.formatHtmlBlank(result.getString(14))+"|"+NullFormatter.formatHtmlBlank(result.getString(15))+"|"+NullFormatter.formatHtmlBlank(result.getString(16))+"|"+NullFormatter.formatHtmlBlank(result.getString(17))+"|"+NullFormatter.formatHtmlBlank(result.getString(18))+"|"+NullFormatter.formatHtmlBlank(result.getString(19))+"|"+NullFormatter.formatHtmlBlank(result.getString(20))+"' type='button' >");

                body.add(result.getString(3));
                body.add(result.getString(10));
                body.add(result.getString(9));
                body.add(result.getString(6));
                body.add(result.getString(7));
                body.add(CurrencyFormatter.formatNumber(NullFormatter.formatHtmlBlank(result.getString(4)),2,",")+"&nbsp"+NullFormatter.formatHtmlBlank(result.getString(5)));

                if ((parametros.get(4).equalsIgnoreCase("6")||(parametros.get(4).equalsIgnoreCase("7"))||(parametros.get(4).equalsIgnoreCase("8"))||(parametros.get(4).equalsIgnoreCase("9")))){


                    if (result.getString(2).equalsIgnoreCase("Cargada")){

                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tagEstadoCargada"));

                    }else if (result.getString(2).equalsIgnoreCase("Aprobada")){

                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tagEstadoAprobada"));

                    }else if (result.getString(2).equalsIgnoreCase("Liberada")){

                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tagEstadoLiberada"));

                    }else if (result.getString(2).equalsIgnoreCase("Procesada")){

                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tagEstadoProcesada"));
                    }else if (result.getString(2).equalsIgnoreCase("Rechazada")){

                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("tagEstadoRechazada"));
                    }

                  /*  if (result.getString(2).equalsIgnoreCase("Liberada"))
                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("Liberada2"));
                    else
                        body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString(result.getString(2)));  */
                }
                else{
                    body.add(ResourceBundle.getBundle("Transferencias"+idioma).getString(result.getString(2)));
                }
                bodys.add(body);
//
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGFecha"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCuentaDebito"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCuentaCredito"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGBeneficiario"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto"));
            header.add(ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatus"));



                    listaTransferencias=new TableOd();

            listaTransferencias= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (listaTransferencias);
    }

    public String cambiarEstatusTransferencia (String Status, String numReferencia, VBTUsersOd usuario, String Idioma) throws Exception {
        final String origen = "TransfersIo.consultTransfers";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        List<String> datos= new ArrayList<String>();
        String respuesta=null;
        MailManager mailManager = new MailManager("vbtonline");
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
        SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));

        String numeros_ref="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            //Carga los correos que afectaron la transferencia antes de cambiar su estatus
            String emailICargadores = emailsRechazo(numReferencia);

            connection=getConnection();
            connection.setAutoCommit(false);

            numeros_ref = numReferencia.replace("'","");
            statement  = connection.prepareCall ("begin TransferenciaHandler.tran_cambiar_estatus_fc_pr(?,?,?,?,?); end;");

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

            if ( ResourceBundle.getBundle("vbtonline").getString("mail_transferencias")== null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"));

            statement.registerOutParameter(5, OracleTypes.VARCHAR);

              statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK")) {
                throw (new Exception (respuesta,null));
            }else{

                if(Status.equals("L")){

                    statement  = connection.prepareCall ("begin TransferenciaHandler.trans_inserta_bofa_pr(?,?); end;");

                    if(numeros_ref==null)
                        statement.setNull(1,OracleTypes.NULL);
                    else
                        statement.setString(1,numeros_ref);

                    statement.registerOutParameter(2, OracleTypes.VARCHAR);

                    statement.execute ();

                    respuesta = statement.getString(2);

                    if (!respuesta.equalsIgnoreCase("OK")){
                        throw (new Exception (respuesta,null));
                    } else {
                        //logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



                           /* Si está OK se inserta en libro de ordenes de crm
                             */
                        statement  = connection.prepareCall (
                                "begin LIBRO_ORDENES.PR_INS_FLUJO_DOC_FC(?,?,?,?); end;");



                        //detalle.append("<BR>el otro campo: ").append(clienteDebito.trim());
                        //    datailsOfPaymentName
                        if(numReferencia == null)
                            statement.setNull(1, OracleTypes.NULL);
                        else
                            statement.setString(1,numeros_ref);


                            statement.setString(2,usuario.getLogin());
                            statement.setString(3,usuario.getIP());

                        statement.registerOutParameter(4, OracleTypes.VARCHAR);

                        statement.execute ();

                        respuesta = statement.getString(4);


                        if (!respuesta.equalsIgnoreCase("OK"))  {
                            throw (new Exception (respuesta,null));
                        }else{
                              /* Si está OK se envian los correos
                             */
                            statement  = connection.prepareCall (
                                    "begin VBTONLINE.vbt_correos_liberar(?,?); end;");

                            //detalle.append("<BR>el otro campo: ").append(clienteDebito.trim());
                            //    datailsOfPaymentName
                            if(numReferencia == null)
                                statement.setNull(1, OracleTypes.NULL);
                            else
                                statement.setString(1,numeros_ref);

                            if ( ResourceBundle.getBundle("vbtonline").getString("mail_transferencias")== null)
                                statement.setNull(2, OracleTypes.NULL);
                            else
                                statement.setString(2, ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"));

                            statement.execute ();

//                          connection.commit();
//                          logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                        }

                    }
                }
                connection.commit();
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                //LLAMAR CORREO
            }
            String numeroReferencia = numerosReferencia(numReferencia);
            //AQUI SE OBTIENE EL USUARIO QUE CARGO LA TRANSFERENCIA

            String referencias="";

            int total=numeroReferencia.split(",").length;
            for (int i = 0; i < total; i++) {
                String line="";
                datos=buscarMontoRef(numeroReferencia.split(",")[i]);
                line=ResourceBundle.getBundle("Transferencias"+Idioma).getString("tag_FirmasConjuntas_Ref")+ " '"+numeroReferencia.split(",")[i]+"'" +
                ", "+ ResourceBundle.getBundle("Transferencias"+Idioma).getString("tag_FirmasConjuntas_tipo")+": ";

                if(datos.get(0).equals("CID")){
                    line=line + ResourceBundle.getBundle("Transferencias"+Idioma).getString("tag_FirmasConjuntas_CIDFC")+", ";
                }else{
                    line=line + ResourceBundle.getBundle("Transferencias"+Idioma).getString("tag_FirmasConjuntas_TEOFC")+", ";
                }

                line=line+ResourceBundle.getBundle("Transferencias"+Idioma).getString("tag_FirmasConjuntas_Monto")+ ": "+datos.get(2)+" "+datos.get(1)+".";
                referencias=referencias+"\n\n"+line;

            }

            if(Status.equals("A")){
                String htmlText = "";

                htmlText=ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaAprobador")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgResumenAprobacion")+
                        "\n"+referencias+
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento1FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento6FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";

                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaAprobador"), htmlText);
                String emailInternos = EmailsAprobadores(usuario.getNumContrato(), "8");

                String listaCorreos="";

                if (emailICargadores.length()>0) {
                   listaCorreos= emailInternos+","+emailICargadores;
                }else{
                   listaCorreos= emailInternos;
                }

                htmlText=ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaAprobador")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgResumenAprobacion")+
                        "\n"+referencias+
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento1FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento6FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento4FC");
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), listaCorreos, ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmmailAlertaAprobacionTitulo"), htmlText);

                //this.GuardarLog(usuario.getLogin(),"3","1","14","",usuario.getIP(), "Se han aprobado las siguientes Tranferencias :" + referencias);
                this.GuardarLogFC(usuario.getLogin(),"20","1","14","",usuario.getIP(), "Se han aprobado las siguientes Transferencias: " + numeroReferencia,usuario.getNumContrato());
            }else if(Status.equals("L")){


                String htmlText = "";

                htmlText=ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaLiberador")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgResumenLiberacion")+
                        "\n"+referencias+
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento6FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento4FC");


                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaLiberador"), htmlText);
                String emailInternos = emailsClientesPrincipales(usuario.getNumContrato());

                String listaCorreos="";

                if (emailICargadores.length()>0) {
                    listaCorreos= emailInternos+","+emailICargadores;
                }else{
                    listaCorreos= emailInternos;
                }
                htmlText=ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaLiberadorAlerta")  +
                        "\n\n"+referencias+
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento6FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento4FC");

                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), listaCorreos, ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaLiberadorAlertaTitulo"), htmlText);
                this.GuardarLogFC(usuario.getLogin(),"21","1","14","",usuario.getIP(), "Se han liberado las siguientes Transferencias: " + numeroReferencia,usuario.getNumContrato());
            }else if(Status.equals("D")){
                String htmlText = "";
                htmlText=ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaRechazo")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgResumenRechazo")+
                        "\n"+referencias+
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento6FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n";

                String emailInternos = emailsRechazo(numReferencia);
                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaRechazo"), htmlText);

                htmlText=ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaRechazo")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgResumenRechazo")+
                        "\n"+referencias+
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n";




                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),emailInternos, ResourceBundle.getBundle("Transferencias"+Idioma).getString("TAGMsgEmailTransferenciaRechazo"), htmlText);

                this.GuardarLogFC(usuario.getLogin(),"22","1","14","",usuario.getIP(), "Se han rechazado las siguientes Transferencias: " + numeroReferencia,usuario.getNumContrato());

            }
            time = System.currentTimeMillis () - time;

            //this.GuardarLog(usuario.getLogin(),"16","1","4",strNumeroCuentaDebito,usuario.getIP(), "N° ref: " + resultados.get(1) );

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            //connection.setAutoCommit(true);
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String cambiarEstatusTemplate (String id, String usuario, String Idioma) throws Exception {
        final String origen = "TransfersIo.cambiarEstatusTemplate";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.cambiar_estatus_template_pr(?,?,?); end;");

            if(id == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,id);

            if (usuario== null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,usuario);

            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public SelectOd cargarCuentasTransOtrosBan (String carteras) throws Exception {
        final String origen = "TransfersIo.cargarCuentasTransOtrosBan";
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

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_elementos_ext_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

              statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                cuenta.setLabel(result.getString(2));
                cuenta.setValue(result.getString(1));
                cuentas.add(cuenta);
                cuenta=new ContentSelectOd();
            }

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

        return (select);
    }


    public List<String> buscarMontoRef (String numeroReferencia) throws Exception {
        final String origen = "TransfersIo.buscarMontoRef";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<String> resultados= new ArrayList<String>();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.cargar_montos_fc(?,?,?); end;");

            if (numeroReferencia== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,numeroReferencia);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                resultados.add(result.getString(1)) ;
                resultados.add(result.getString(2));
                resultados.add(result.getString(3));
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

        return (resultados);
    }

    public SelectOd cargarPaises () throws Exception {
        final String origen = "TransfersIo.Paises";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Paises= new ArrayList<ContentSelectOd>();
        ContentSelectOd Pais = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_Paises_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                Pais.setValue(result.getString(1));
                Pais.setLabel(result.getString(2));
                Paises.add(Pais);
                Pais=new ContentSelectOd();
            }

            select.setContenido(Paises);

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

    public SelectOd cargarPaisesBeneficiario () throws Exception {
        final String origen = "TransfersIo.PaisesBeneficiario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Paises= new ArrayList<ContentSelectOd>();
        ContentSelectOd Pais = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_paises_beneficiario_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                Pais.setValue(result.getString(1));
                Pais.setLabel(result.getString(2));
                Paises.add(Pais);
                Pais=new ContentSelectOd();
            }

            select.setContenido(Paises);

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

    public TemplateOd cargarListaTemplates(String login) throws Exception {
        final String origen = "TransfersIo.cargarListaTemplates";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TemplateOd lista=new TemplateOd();
        List<ContentTemplateOd> listaTemplates = new ArrayList<ContentTemplateOd>();
        ContentTemplateOd template = new ContentTemplateOd();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_directorioAutocompletar_pr(?,?,?); end;");

            if(login != null)
                statement.setString(1,login);
            else
                statement.setNull(1, OracleTypes.NULL);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                template.setValue(result.getString(2));
                template.setLabel(result.getString(2));
                template.setKey(result.getString(1));
                listaTemplates.add(template);
                template=new ContentTemplateOd();
            }

            lista.setContenido(listaTemplates);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (lista);
    }

    public String direccionOriginadorTransf (String strCodigoCarteraDebito) throws Exception {
        final String origen = "TransfersIo.direccionOriginadorTransf";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String direccion = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.direc_orig_trans_pr(?,?,?); end;");
//            direc_orig_trans_pr (p_codcardeb in VARCHAR2, p_direccion OUT direccion_transf, p_resultado OUT VARCHAR2)

            if(strCodigoCarteraDebito==null)
                statement.setNull(1,OracleTypes.NULL);
            else
               statement.setString(1,strCodigoCarteraDebito);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                direccion = result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (direccion);
    }

    public String obtenerNumeroReferencia () throws Exception {
        final String origen = "TransfersIo.obtenerNumeroReferencia";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String numReferencia = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

             numReferencia = statement.getString(1);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (numReferencia);
    }

    public String actSecuenciaTrans () throws Exception {
        final String origen = "TransfersIo.actSecuenciaTrans";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String numReferencia = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.act_secuencia_trans_pr(?,?,?); end;");

            statement.setString(1,"0000009539");
            statement.setString(2,"04");
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String clienteDebito (String strCodigoCarteraDebito) throws Exception {
        final String origen = "TransfersIo.clienteDebito";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String cliente = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.na_clientes_pr(?,?,?); end;");
//             PROCEDURE na_clientes_pr (p_CodigoCarteraDebito in VARCHAR2, p_clientes OUT na_clientes, p_resultado OUT VARCHAR2) AS


            if(strCodigoCarteraDebito==null)
                statement.setNull(1,OracleTypes.NULL);
            else
               statement.setString(1,strCodigoCarteraDebito);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                cliente = result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (cliente);
    }

    public String carteraCredito (String strCodigoCuenta) throws Exception {
        final String origen = "TransfersIo.carteraCredito";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String cartera = new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_inter_codcar_pr(?,?,?); end;");
//             PROCEDURE na_clientes_pr (p_CodigoCarteraDebito in VARCHAR2, p_clientes OUT na_clientes, p_resultado OUT VARCHAR2) AS


            if(strCodigoCuenta==null)
                statement.setNull(1,OracleTypes.NULL);
            else
               statement.setString(1,strCodigoCuenta);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                cartera = result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (cartera);
    }

    public CuentasOd cargarCuentas (String Carteras, String idioma) throws Exception {
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
                statement.setString(1, Carteras.toString());
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public boolean aceptarCondicionesTransfe (String contrato, String usuario, String direccionip, String fecha, String acepto) throws Exception {
        final String origen = "TransfersIo.cargarCodBankBen";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> codigos= new ArrayList<ContentSelectOd>();
        ContentSelectOd codigoBank = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        boolean respuesta=false;
        String pl_resultado = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.ActualizarTerminosCondiciones(?,?,?,?,?,?); end;");

            statement.setString(1, contrato);
            statement.setString(2, usuario);
            statement.setString(3, direccionip);
            statement.setString(4, fecha);
            statement.setString(5, acepto);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            pl_resultado = statement.getString(6);

            if (!pl_resultado.equalsIgnoreCase("OK"))
                throw (new Exception (pl_resultado,null));
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                if (acepto.equalsIgnoreCase("S"))
                    respuesta = true;
                else
                    respuesta = false;
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

        return (respuesta);
    }


    public SelectOd cargarCodBankBen () throws Exception {
        final String origen = "TransfersIo.cargarCodBankBen";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> codigos= new ArrayList<ContentSelectOd>();
        ContentSelectOd codigoBank = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.bankCode_elementos_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                codigoBank.setValue(result.getString(1));
                codigoBank.setLabel(result.getString(2));
                codigos.add(codigoBank);
                codigoBank=new ContentSelectOd();
            }

            select.setContenido(codigos);

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

    public Integer validarBancoBeneficiario_Intermediario (String codigoBancoB, String tipoBancoB, String codigoPaisB, String tipoBancoI, String codigoBancoI, String codigoPaisI) throws Exception {
        final String origen = "TransfersIo.validarBancoBeneficiario_Intermediario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        Integer existe=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_ext_confir_pr(?,?,?,?,?,?,?,?,?); end;");

//            TipoCodBancoBeneficiario   IN VARCHAR2,
//                    CodBancoBeneficiario       IN VARCHAR2,
//            CodPaisBancoBeneficiario   IN VARCHAR2,
//                    TipoCodBancoIntermediario  IN VARCHAR2,
//            CodBancoBancoIntermediario IN VARCHAR2,
//                    CodPaisBancoIntermediario  IN VARCHAR2,
//            conta_inst                 OUT NUMBER,

            if(tipoBancoB == null)
               statement.setNull(1, OracleTypes.NULL);
            else
              statement.setString(1, tipoBancoB);
            if(codigoBancoB == null)
              statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, codigoBancoB);
            if(codigoPaisB == null)
              statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, codigoPaisB);
            if(tipoBancoI == null)
               statement.setNull(4, OracleTypes.NULL);
            else
              statement.setString(4, tipoBancoI);
            if(codigoBancoI == null)
              statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5, codigoBancoI);
            if(codigoPaisI == null)
              statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6, codigoPaisI);

            statement.registerOutParameter(7, OracleTypes.INTEGER);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);
            statement.registerOutParameter(9, OracleTypes.VARCHAR);



            statement.execute ();

            respuesta = statement.getString(8);
            String sql = statement.getString(9);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            existe= statement.getInt(7);


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (existe);
    }

    public String validarCuentaDestino_TOC (String cuenta) throws Exception {
        final String origen = "TransfersIo.validarCuentaDestino_TOC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        String moneda = new String();
        Integer existe=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_codmon_pr(?,?,?); end;");

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                moneda= result.getString(1);
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

        return (moneda);
    }

    public String saveBetweenMyAccounts (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersIo.saveBetweenMyAccounts";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        Integer exitoso=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String strNumeroCuentaDebito = new String();
        String strCodigoCarteraDebito = new String();

        String strNumeroCuentaCredito = new String();
        String strCodigoCarteraCredito = new String();
        String clienteDebito = new String();
        String clienteCredito = new String();
        String emailInternos = new String();


        String label, valor;
        String respuesta="OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            if (!NullFormatter.isBlank(datos.getBeneficiaryAccount())) {
                strNumeroCuentaCredito  = datos.getBeneficiaryAccount().substring(0,datos.getBeneficiaryAccount().indexOf("|"));
                strCodigoCarteraCredito = datos.getBeneficiaryAccount().substring((datos.getBeneficiaryAccount().indexOf("|")+1),datos.getBeneficiaryAccount().length()).trim();
            }

            String numReferenciaDebito = obtenerNumeroReferencia();
            String actualizo = actSecuenciaTrans();
            String numReferenciaCredito = obtenerNumeroReferencia();
            String actualizoCredito = actSecuenciaTrans();
//            strCodigoCarteraCredito = carteraCredito(strNumeroCuentaCredito);
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            clienteCredito = clienteDebito(strCodigoCarteraCredito);

            emailInternos = EmailsInternos(strNumeroCuentaDebito);



            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.ORDEN_TRANSFERENCIA_INTERNA(?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?); end;");
            if(usuario.getNumContrato()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getNumContrato());

            if(strCodigoCarteraDebito==null)
                statement.setNull(2,OracleTypes.NULL);
            else
               statement.setString(2,strCodigoCarteraDebito.trim());

            if(numReferenciaDebito==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,numReferenciaDebito.trim());

            if(strNumeroCuentaDebito==null)
                statement.setNull(4,OracleTypes.NULL);
            else
               statement.setString(4,strNumeroCuentaDebito.trim());

            if(datos.getAmount()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setDouble(5,Double.parseDouble(datos.getAmount()));
//                statement.setDouble(5,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

            if(strCodigoCarteraCredito==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,strCodigoCarteraCredito.trim());


            if(datos.getMoneda()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,datos.getMoneda());

            if(clienteCredito==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8, clienteCredito.trim());

            //concepto
            if(datos.getRecieverName()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9,datos.getRecieverName().trim());

            if(clienteDebito==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10,clienteDebito.trim());

            if(strNumeroCuentaCredito==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11,strNumeroCuentaCredito.trim());

            if(usuario.getLogin()==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,usuario.getLogin());

            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                String emailDestino=emailClientePrincipalCuenta(strNumeroCuentaCredito);
                if(emailDestino==null)
                    statement.setNull(13,OracleTypes.NULL);
                else
                    statement.setString(13,emailDestino);
            }else{
                if(usuario.getEmail()==null)
                    statement.setNull(13,OracleTypes.NULL);
                else
                    statement.setString(13,usuario.getEmail());
            }


            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                String emailOrigen=emailClientePrincipalCuenta(strNumeroCuentaDebito);
                if(emailOrigen==null)
                    statement.setNull(14,OracleTypes.NULL);
                else
                    statement.setString(14,emailOrigen);
            }else{
                if(usuario.getEmail()==null)
                    statement.setNull(14,OracleTypes.NULL);
                else
                    statement.setString(14,usuario.getEmail());
            }

            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                statement.setString(15,"C");
            }else
                statement.setNull(15,OracleTypes.VARCHAR);

            if(usuario.getTipo()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,usuario.getTipo());

            statement.registerOutParameter(17, OracleTypes.VARCHAR);
            statement.registerOutParameter(18, OracleTypes.VARCHAR);

            statement.execute ();

            String numRef =  statement.getString(17);
            respuesta = statement.getString(18);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            MailManager mailManager = new MailManager("vbtonline");

//            ClientContext context = new ClientContext();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
            SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));

            String etiqueta = new String();
            if(idioma.equalsIgnoreCase("_us_en")){
                if(usuario.getTipo().equals("6"))
                    etiqueta = "Input";
                else
                    etiqueta = "In Process";

            }else{
                if(usuario.getTipo().equals("6"))
                    etiqueta = "Cargada";
                else
                    etiqueta = "Liberada";
            }
            String htmlText = "";
            String htmlText2 = "";
            String htmlText3 = "";

            //******** Envía correo de notificación de transferencia al cliente que monta la orden de transferencia


            if(usuario.getTipo().equals("6")){
                //Usuario Cargador de Firmas Conjuntas
                htmlText=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaMisCuentasFC")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + strNumeroCuentaCredito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + clienteCredito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + datos.getRecieverName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + etiqueta +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento3FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";


                //Correo que se le envia a los Usuarios Aprobadores
                htmlText3= ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailBackOfficeTransferenciaInternaFC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                        " - " + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCarteraDebito") + ": *******" + strCodigoCarteraDebito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + strNumeroCuentaCredito.substring(7,10) +
                        " - " + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCarteraCredito") + ": *******" + strCodigoCarteraCredito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + clienteCredito.trim() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                        // "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + strTxtConcepto +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + etiqueta +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento4FC")+"\n\n\n";




            }else{
                //Cliente Full Acceso
                htmlText=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaMisCuentas")  +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + strNumeroCuentaCredito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + clienteCredito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + datos.getRecieverName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + etiqueta +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento2") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento3")+"\n\n\n";


            }



            //******** Envía correo de notificación de la orden de transferencia al asesor, ejecutivo y asistentes
            htmlText2= ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgEmailBackOfficeTransferenciaInterna") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCuentaDebito") + ": " + strNumeroCuentaDebito +
                    " - " + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCarteraDebito") + ": " + strCodigoCarteraDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCuentaCredito") + ": " + strNumeroCuentaCredito +
                    " - " + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCarteraCredito") + ": " + strCodigoCarteraCredito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNombreBeneficiario") + ": " + clienteCredito.trim() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    // "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + strTxtConcepto +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEstatusTransferencia") + ": " + etiqueta+"\n\n\n";



            //*BOFA debito*//
            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            if (!NullFormatter.isBlank(datos.getBeneficiaryAccount())) {
                strNumeroCuentaCredito  = datos.getBeneficiaryAccount().substring(0,datos.getBeneficiaryAccount().indexOf("|"));
                strCodigoCarteraCredito = datos.getBeneficiaryAccount().substring((datos.getBeneficiaryAccount().indexOf("|")+1),datos.getBeneficiaryAccount().length()).trim();
            }


            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            clienteCredito = clienteDebito(strCodigoCarteraCredito);
//            strCodigoCarteraCredito = carteraCredito(strNumeroCuentaCredito);


//            connection=getConnection();
            if(!usuario.getTipo().equals("6")){  //Usuario Full Acceso
                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_movimiento_bofa_it(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaDebito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaDebito);
                if(strNumeroCuentaDebito==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,strNumeroCuentaDebito);
                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
    //                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));
                if(strCodigoCarteraCredito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraCredito);

                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());
                if(clienteCredito==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, clienteCredito);
                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());
                if(clienteDebito==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,clienteDebito.trim());
                if(strNumeroCuentaCredito==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,strNumeroCuentaCredito.trim());

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef.trim());

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //*BOFA debito*//

                //*BOFA credito*//

                if (!NullFormatter.isBlank(datos.getAccountCode())) {
                    strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                    strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
                }

                if (!NullFormatter.isBlank(datos.getBeneficiaryAccount())) {
                    strNumeroCuentaCredito  = datos.getBeneficiaryAccount().substring(0,datos.getBeneficiaryAccount().indexOf("|"));
                    strCodigoCarteraCredito = datos.getBeneficiaryAccount().substring((datos.getBeneficiaryAccount().indexOf("|")+1),datos.getBeneficiaryAccount().length()).trim();
                }


                clienteDebito = clienteDebito(strCodigoCarteraDebito);
                clienteCredito = clienteDebito(strCodigoCarteraCredito);
    //            strCodigoCarteraCredito = carteraCredito(strNumeroCuentaCredito);


    //            connection=getConnection();

                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_movimiento_bofa_it_cre(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaCredito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaCredito);
    //            System.out.println("numref: "+numref);
                if(strNumeroCuentaCredito==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,strNumeroCuentaCredito.trim());
    //            System.out.println("strNumeroCuentaCredito: "+strNumeroCuentaCredito);
                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
    //                statement.setDouble(3,(Double.parseDouble(datos.getAmount().toString().replace(',','.'))*-1));
    //            System.out.println("strNumeroCuentaCredito: "+strNumeroCuentaCredito);
                if(strCodigoCarteraDebito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraDebito.trim());


                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());

                if(clienteDebito==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, clienteDebito.trim());

                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());

                if(clienteCredito==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,clienteCredito.trim());

                if(strNumeroCuentaDebito==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,strNumeroCuentaDebito.trim());

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK")) {
                    throw (new Exception (respuesta,null));
                }else{
                      /* Si está OK se inserta en libro de ordenes de crm
                     */
                    statement  = connection.prepareCall (
                            "begin LIBRO_ORDENES.PR_INS_FLUJO_DOC_FA(?,?,?,?,?,?,?,?,?,?); end;");

                    StringBuilder detalle = new StringBuilder();
                    detalle.append("<BR>Cartera Origen : ").append(strCodigoCarteraDebito).append(" - Cuenta: ").append(strNumeroCuentaDebito);
                    detalle.append("<BR>Cartera Destino: ").append(strCodigoCarteraCredito).append(" - Cuenta: ").append(strNumeroCuentaCredito);

                    statement.setString(1,strCodigoCarteraDebito);
                    statement.setString(2,"INTERNA");
                    statement.setString(3,"SAVING");
                    statement.setString(4,datos.getMoneda());
                    statement.setString(5, datos.getAmount());
                    statement.setString(6, detalle.toString());
                    statement.setString(7, numRef);
                    statement.setString(8,usuario.getLogin());
                    statement.setString(9,usuario.getIP());

                    statement.registerOutParameter(10, OracleTypes.VARCHAR);

                    statement.execute ();

                    respuesta = statement.getString(10);


                    if (!respuesta.equalsIgnoreCase("OK"))  {
                        throw (new Exception (respuesta,null));
                    }else{
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                    }

                }
                //*BOFA credito*//

            }else{

                //Usuarios de Firmas Conjuntas

                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_movimiento_bofa_tmp_it(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaDebito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaDebito);
                if(strNumeroCuentaDebito==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,strNumeroCuentaDebito);
                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
                //                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));
                if(strCodigoCarteraCredito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraCredito);

                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());
                if(clienteCredito==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, clienteCredito);
                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());
                if(clienteDebito==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,clienteDebito.trim());
                if(strNumeroCuentaCredito==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,strNumeroCuentaCredito.trim());

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //*BOFA debito*//

                //*BOFA credito*//

                if (!NullFormatter.isBlank(datos.getAccountCode())) {
                    strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                    strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
                }

                if (!NullFormatter.isBlank(datos.getBeneficiaryAccount())) {
                    strNumeroCuentaCredito  = datos.getBeneficiaryAccount().substring(0,datos.getBeneficiaryAccount().indexOf("|"));
                    strCodigoCarteraCredito = datos.getBeneficiaryAccount().substring((datos.getBeneficiaryAccount().indexOf("|")+1),datos.getBeneficiaryAccount().length()).trim();
                }


                clienteDebito = clienteDebito(strCodigoCarteraDebito);
                clienteCredito = clienteDebito(strCodigoCarteraCredito);
                //            strCodigoCarteraCredito = carteraCredito(strNumeroCuentaCredito);


                //            connection=getConnection();

                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_mov_bofa_tmp_it_cre(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaCredito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaCredito);
                //            System.out.println("numref: "+numref);
                if(strNumeroCuentaCredito==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,strNumeroCuentaCredito.trim());
                //            System.out.println("strNumeroCuentaCredito: "+strNumeroCuentaCredito);
                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
                //                statement.setDouble(3,(Double.parseDouble(datos.getAmount().toString().replace(',','.'))*-1));
                //            System.out.println("strNumeroCuentaCredito: "+strNumeroCuentaCredito);
                if(strCodigoCarteraDebito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraDebito.trim());


                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());

                if(clienteDebito==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, clienteDebito.trim());

                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());

                if(clienteCredito==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,clienteCredito.trim());

                if(strNumeroCuentaDebito==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,strNumeroCuentaDebito.trim());


                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK")) {
                    throw (new Exception (respuesta,null));
                } else{

                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



                }//*BOFA credito*//




            }
            respuesta = respuesta +"|"+numReferenciaDebito+"!"+numReferenciaCredito;
            connection.commit();

            /*Envio los correos*/
            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaMisCuentasFC"), htmlText);
            }else{
                mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaMisCuentas"), htmlText);
            }

             if(usuario.getTipo().equals("2")){
                 mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos, ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaInterna"), htmlText2);
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"),  ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaInterna"), htmlText2);

             }
            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                emailInternos = EmailsAprobadores(usuario.getNumContrato(), "7");
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos, ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaInterna"), htmlText3);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String saveToOtherClient (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersIo.saveToOtherClient";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        Integer exitoso=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String strNumeroCuentaDebito = new String();
        String strCodigoCarteraDebito = new String();

        String strNumeroCuentaCredito = new String();
        String strCodigoCarteraCredito = new String();
        String clienteDebito = new String();
        String emailInternos = new String();


        String label, valor;
        String respuesta="OK";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            String numReferenciaDebito = obtenerNumeroReferencia();
            String actualizo = actSecuenciaTrans();
            String numReferenciaCredito = obtenerNumeroReferencia();
            String actualizoCredito = actSecuenciaTrans();
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            strCodigoCarteraCredito = carteraCredito(datos.getBeneficiaryAccount());
            strNumeroCuentaCredito = datos.getBeneficiaryAccount();


            // Acomoda la codificación de los caracteres "Ã¤Ã¶Ã¼ÃÃÃÃÃ¡Ã©Ã­Ã³ÃºÃÃÃÃÃÃ Ã¨Ã¬Ã²Ã¹ÃÃÃÃÃÃ±Ã"
            String strConcepto = new String(datos.getRecieverName().trim().getBytes("ISO-8859-15"), "UTF-8");



            emailInternos = EmailsInternos(strNumeroCuentaDebito);

            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.ORDEN_TRANSFERENCIA_INTERNA(?,?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?); end;");


            if(usuario.getNumContrato()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getNumContrato());

            if(strCodigoCarteraDebito==null)
                statement.setNull(2,OracleTypes.NULL);
            else
               statement.setString(2,strCodigoCarteraDebito);

            if(numReferenciaDebito==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,numReferenciaDebito);


             if(strNumeroCuentaDebito==null)
                statement.setNull(4,OracleTypes.NULL);
             else
                statement.setString(4,strNumeroCuentaDebito);


            if(datos.getAmount()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setDouble(5, Double.parseDouble(datos.getAmount()));
//                statement.setDouble(5,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

            if(strCodigoCarteraCredito==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,strCodigoCarteraCredito);


            if(datos.getMoneda()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,datos.getMoneda());

            if(datos.getBeneficiaryName()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8, datos.getBeneficiaryName());

            //concepto
            if(datos.getRecieverName()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9,strConcepto);

            if(clienteDebito==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10,clienteDebito.trim());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11,datos.getBeneficiaryAccount());

            if(usuario.getLogin()==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,usuario.getLogin());

            if(datos.getBeneficiaryEmail()==null)
                statement.setNull(13,OracleTypes.NULL);
            else
                statement.setString(13,datos.getBeneficiaryEmail());


            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                String emailOrigen=emailClientePrincipalCuenta(strNumeroCuentaDebito);
                if(emailOrigen==null)
                    statement.setNull(14,OracleTypes.NULL);
                else
                    statement.setString(14,emailOrigen);
            }else{
                if(usuario.getEmail()==null)
                    statement.setNull(14,OracleTypes.NULL);
                else
                    statement.setString(14,usuario.getEmail());
            }


            if(usuario.getTipo().equals("6")||usuario.getTipo().equals("7")||usuario.getTipo().equals("8")){
                statement.setString(15,"C");
            }else
                statement.setNull(15,OracleTypes.NULL);


            if(usuario.getTipo()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,usuario.getTipo());


            statement.registerOutParameter(17, OracleTypes.VARCHAR);
            statement.registerOutParameter(18, OracleTypes.VARCHAR);

            statement.execute ();

            String numRef =  statement.getString(17);
            respuesta = statement.getString(18);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            MailManager mailManager = new MailManager("vbtonline");

//            ClientContext context = new ClientContext();

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
            SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));


            String etiqueta = new String();

            if(idioma.equalsIgnoreCase("_us_en"))
              etiqueta = "In Process";
            else
              etiqueta = "En Proceso";

            //******** Envía correo de notificación de transferencia al cliente que monta la orden de transferencia

            String htmlText = "";
            String htmlText3 = "";
            //******** Envía correo de notificación de transferencia al cliente que monta la orden de transferencia


            if(usuario.getTipo().equals("6")){
                htmlText=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaInternaTercerosFC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + strNumeroCuentaCredito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux()+ " " + datos.getMoneda() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + datos.getRecieverName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + etiqueta +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento2FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento3FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento4FC")+ "\n\n\n";

                //Correo que se le envia a los Usuarios Aprobadores
                htmlText3= ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailBackOfficeTransferenciaInternaFC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP()+
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": "  + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                        " - " + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCarteraDebito") + ": "  + ": *******" + strCodigoCarteraDebito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": "+ ": *******" + strNumeroCuentaCredito.substring(7,10) +
                        " - " + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCarteraCredito") + ": "  + ": *******" + strCodigoCarteraCredito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                        // "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + strTxtConcepto +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + etiqueta +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1FC") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento4FC")+ "\n\n\n";


            }else{
                htmlText=ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaInternaTerceros") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + strNumeroCuentaCredito.substring(7,10) +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailBeneficiario") + ": " + datos.getBeneficiaryEmail() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux()+ " " + datos.getMoneda() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + datos.getRecieverName() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + etiqueta +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento2") +
                        "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento3")+ "\n\n\n";
            }

            String htmlText2 = "";
            //******** Envía correo de notificación de la orden de transferencia al asesor, ejecutivo y asistentes
            htmlText2= ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgEmailBackOfficeTransferenciaInterna") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCuentaDebito") + ": " + strNumeroCuentaDebito +
                    " - " + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCarteraDebito") + ": " + strCodigoCarteraDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCuentaCredito") + ": " + strNumeroCuentaCredito +
                    " - " + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCarteraCredito") + ": " + strCodigoCarteraCredito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                    //"\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailBeneficiario") + ": " + strTxtEmailBeneficiario +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    // "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + strTxtConcepto +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEstatusTransferencia") + ": " + etiqueta+ "\n\n\n";



            //**BOFA debito*//
            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            strCodigoCarteraCredito = carteraCredito(datos.getBeneficiaryAccount());

            if(!usuario.getTipo().equals("6")){
                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_movimiento_bofa_it(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaDebito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaDebito);

                if(strNumeroCuentaDebito==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,strNumeroCuentaDebito);

                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
    //                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

                if(strCodigoCarteraCredito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraCredito);


                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());

                if(datos.getBeneficiaryName()==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, datos.getBeneficiaryName());

                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());

                if(clienteDebito==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,clienteDebito.trim());

                if(datos.getBeneficiaryAccount()==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,datos.getBeneficiaryAccount());

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //**BOFA debito*//


                //**BOFA credito*//
                if (!NullFormatter.isBlank(datos.getAccountCode())) {
                    strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                    strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
                }


                clienteDebito = clienteDebito(strCodigoCarteraDebito);


                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_movimiento_bofa_it_cre(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaCredito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaCredito);

                if(datos.getBeneficiaryAccount()==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,datos.getBeneficiaryAccount());

                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
    //                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

                if(strCodigoCarteraDebito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraDebito);


                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());

                if(clienteDebito==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, clienteDebito.trim());

                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());

                if(datos.getBeneficiaryName()==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,datos.getBeneficiaryName());

                if(strNumeroCuentaDebito==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,strNumeroCuentaDebito);

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);


                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))  {
                    throw (new Exception (respuesta,null));
                } else{
                    /* Si está OK se inserta en libro de ordenes de crm
                     */
                    statement  = connection.prepareCall (
                            "begin LIBRO_ORDENES.PR_INS_FLUJO_DOC_FA(?,?,?,?,?,?,?,?,?,?); end;");

                    StringBuilder detalle = new StringBuilder();
                    detalle.append("<BR>Cartera Origen : ").append(strCodigoCarteraDebito).append(" - Cuenta: ").append(strNumeroCuentaDebito);
                    detalle.append("<BR>Beneficiario: ").append(datos.getBeneficiaryName());
                    detalle.append("<BR>Cartera Destino: ").append(strCodigoCarteraCredito).append(" - Cuenta: ").append(strNumeroCuentaCredito);

                    statement.setString(1,strCodigoCarteraDebito);
                    statement.setString(2,"INTERNA");
                    statement.setString(3,"SAVING");
                    statement.setString(4,datos.getMoneda());
                    statement.setString(5, datos.getAmount());
                    statement.setString(6, detalle.toString());
                    statement.setString(7, numRef);
                    statement.setString(8,usuario.getLogin());
                    statement.setString(9,usuario.getIP());

                    statement.registerOutParameter(10, OracleTypes.VARCHAR);

                    statement.execute ();

                    respuesta = statement.getString(10);


                    if (!respuesta.equalsIgnoreCase("OK"))  {
                        throw (new Exception (respuesta,null));
                    }else{
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                    }



                }
                //**BOFA credito*//
            }else{
                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_movimiento_bofa_tmp_it(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaDebito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaDebito);

                if(strNumeroCuentaDebito==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,strNumeroCuentaDebito);

                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
//                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

                if(strCodigoCarteraCredito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraCredito);


                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());

                if(datos.getBeneficiaryName()==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, datos.getBeneficiaryName());

                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());

                if(clienteDebito==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,clienteDebito.trim());

                if(datos.getBeneficiaryAccount()==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,datos.getBeneficiaryAccount());

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //**BOFA debito*//


                //**BOFA credito*//
                if (!NullFormatter.isBlank(datos.getAccountCode())) {
                    strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                    strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
                }


                clienteDebito = clienteDebito(strCodigoCarteraDebito);


                statement  = connection.prepareCall ("begin TransferenciaHandler.trans_mov_bofa_tmp_it_cre(?,?,?,?,?,?,?,?,?,?,?); end;");


                if(numReferenciaCredito==null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,numReferenciaCredito);

                if(datos.getBeneficiaryAccount()==null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,datos.getBeneficiaryAccount());

                if(datos.getAmount()==null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setDouble(3,Double.parseDouble(datos.getAmount()));
//                statement.setDouble(3,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

                if(strCodigoCarteraDebito==null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strCodigoCarteraDebito);


                if(datos.getMoneda()==null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,datos.getMoneda());

                if(clienteDebito==null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6, clienteDebito.trim());

                //concepto
                if(datos.getRecieverName()==null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7,datos.getRecieverName().trim());

                if(datos.getBeneficiaryName()==null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,datos.getBeneficiaryName());

                if(strNumeroCuentaDebito==null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,strNumeroCuentaDebito);

                if(numRef==null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,numRef);

                statement.registerOutParameter(11, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //**BOFA credito*//
            }

            connection.commit();
            respuesta = respuesta +"|"+numReferenciaDebito+"!"+numReferenciaCredito;

            /*envia correos*/
            if(usuario.getTipo().equals("6")){
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaInternaTercerosFC"), htmlText);
            }else{
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"),usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaInternaTerceros"), htmlText);
            }

            if(usuario.getTipo().equals("2")){
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), ResourceBundle.getBundle("vbtonline").getString("mail_transferencias"), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaInternaTerceros"), htmlText2);
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos, ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaInterna"), htmlText2);
            }
            if(usuario.getTipo().equals("6")){
                emailInternos = EmailsAprobadores(usuario.getNumContrato(), "7");
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos, ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaInterna"), htmlText3);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public List<String> saveToOtherBank (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersIo.saveToOtherBank";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        Integer exitoso=0;
        List<String> resultados= new ArrayList<String>();
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String strNumeroCuentaDebito = new String();
        String strCodigoCarteraDebito = new String();

        String strNumeroCuentaCredito = new String();
        String strCodigoCarteraCredito = new String();
        String direccion = new String();
        String clienteDebito = new String();
        String emailInternos = new String();
        String numero_instruccion = new String();

        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

//            if (!NullFormatter.isBlank(datos.getBeneficiaryAccount())) {
//                strNumeroCuentaCredito  = datos.getBeneficiaryAccount().substring(0,datos.getBeneficiaryAccount().indexOf("|"));
//                strCodigoCarteraCredito = datos.getBeneficiaryAccount().substring((datos.getBeneficiaryAccount().indexOf("|")+1),datos.getBeneficiaryAccount().length()).trim();
//            }
            emailInternos = EmailsInternos(strNumeroCuentaDebito);
            direccion = direccionOriginadorTransf(strCodigoCarteraDebito);
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            String numReferenciaDebito = obtenerNumeroReferencia();
            String actualizo = actSecuenciaTrans();
//            String numReferenciaDebito2 = obtenerNumeroReferencia2();
            String strCmbTipoCodBancoBeneficiario=new String();
            String strTxtDireccion1BancoBeneficiario = strCmbTipoCodBancoBeneficiario + " " + datos.getBeneficiaryBankCodeType();
            strCmbTipoCodBancoBeneficiario = "";
            String strTxtCodBancoBeneficiario = "";
            String strTxtDireccion2BancoBeneficiario = "";
            String strTxtDireccion3BancoBeneficiario = "";

            if ("ABA".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strCmbTipoCodBancoBeneficiario = "FW";
            } else if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strCmbTipoCodBancoBeneficiario = "SA";
            } else if ("CHIPS".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strCmbTipoCodBancoBeneficiario = "CH";
            } else if ("ACCOUNT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strTxtDireccion1BancoBeneficiario = strCmbTipoCodBancoBeneficiario + " " + datos.getBeneficiaryBankCodeNumber();
                strCmbTipoCodBancoBeneficiario = "";
                strTxtCodBancoBeneficiario = "";
                strTxtDireccion2BancoBeneficiario = "";
                strTxtDireccion3BancoBeneficiario = "";
            }

            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.orden_transferencia_pr(?,?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?,?,?); end;");




            if(usuario.getNumContrato()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getNumContrato());

            if(strCodigoCarteraDebito==null)
                statement.setNull(2,OracleTypes.NULL);
            else
               statement.setString(2,strCodigoCarteraDebito);
            if(usuario.getTipo().equals('6'))
                statement.setString(3,"C");
            else
                statement.setString(3,"L");

            if(numReferenciaDebito==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,numReferenciaDebito);

            statement.setString(5,"0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(6,OracleTypes.NULL);
            else
               statement.setString(6,strNumeroCuentaDebito);

            statement.setString(7,"CAH");
            statement.setString(8,"TEO");

            if(datos.getAmount()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setDouble(9,Double.parseDouble(datos.getAmount()));
//                statement.setDouble(9,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

            String strRefBanMov = "CART" + Integer.parseInt(strCodigoCarteraDebito) + "CC" + Integer.parseInt(strNumeroCuentaDebito);
            if(strRefBanMov==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10, strRefBanMov);

            if(datos.getMoneda()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
               statement.setString(11,datos.getMoneda());

            if(datos.getBeneficiaryName()==null)
                statement.setNull(12,OracleTypes.NULL);
            else
               statement.setString(12, datos.getBeneficiaryName());

            if(datos.getRecieverName()==null)
                statement.setNull(13,OracleTypes.NULL);
            else
                statement.setString(13,datos.getRecieverName().trim());

            statement.setString(14,"0000009539");

            if(strCodigoCarteraDebito==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,strCodigoCarteraDebito);

            if(usuario.getLogin()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,usuario.getLogin());

            statement.setString(17,"ONL");

            if(datos.getBeneficiaryEmail()==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,datos.getBeneficiaryEmail());


            if(usuario.getEmail()==null)
                statement.setNull(19,OracleTypes.NULL);
            else
                statement.setString(19,usuario.getEmail());

            statement.setString(20,ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));

            if(strCodigoCarteraDebito==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,strCodigoCarteraDebito);

            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(22,OracleTypes.NULL);
            else
                statement.setString(22,datos.getBeneficiaryAccountBank());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getBeneficiaryAccount());

            String strBeneficiaryDescription = datos.getBeneficiaryCountryCode() + " " + datos.getBeneficiaryAddress1();
            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress2()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress3()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress3();


            if(strBeneficiaryDescription==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,strBeneficiaryDescription);

            if(datos.getBeneficiaryTelephone()==null)
                statement.setNull(25,OracleTypes.NULL);
            else
                statement.setString(25,datos.getBeneficiaryTelephone());

            if(strCmbTipoCodBancoBeneficiario==null)
                statement.setNull(26,OracleTypes.NULL);
            else
                statement.setString(26,strCmbTipoCodBancoBeneficiario);

            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(27,OracleTypes.NULL);
            else
                statement.setString(27,datos.getBeneficiaryBankCodeNumber().toUpperCase());

            String beneficiaryBankDescription = datos.getBeneficiaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress2()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress3()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress3();

            if(beneficiaryBankDescription==null)
                statement.setNull(28,OracleTypes.NULL);
            else
                statement.setString(28,beneficiaryBankDescription);

            String intermediaryBankDescription = new String();
            if ((NullFormatter.isBlank(datos.getIntermediaryBankCodeType())&&(NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift())))) {
                datos.setIntermediaryBankCodeType("");
                datos.setIntermediaryBankCountryCode("");
            } else {
                if ("ABA".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("FW");
                } else if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("SA");
                } else if ("CHIPS".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("CH");
                }else if ("ACCOUNT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("");
                }
                intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
            }

            if((datos.getIntermediaryBankCodeType()==null)||(datos.getIntermediaryBankCodeType().equalsIgnoreCase("")))
                statement.setNull(29,OracleTypes.NULL);
            else
                statement.setString(29,datos.getIntermediaryBankCodeType());

            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(30,OracleTypes.NULL);
            else
                statement.setString(30,datos.getIntermediaryBankCodeNumber().toUpperCase());

            if(intermediaryBankDescription==null)
                statement.setNull(31,OracleTypes.NULL);
            else
                statement.setString(31,intermediaryBankDescription);

            if(direccion==null)
                statement.setNull(32,OracleTypes.NULL);
            else
                statement.setString(32,direccion);

            statement.setString(33,"TEO");
            statement.setString(34,"0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(35,OracleTypes.NULL);
            else
                statement.setString(35,strNumeroCuentaDebito);

            statement.setString(36,"CAH");

            if(datos.getBeneficiaryName()==null)
                statement.setNull(37,OracleTypes.NULL);
            else
                statement.setString(37,datos.getBeneficiaryName());

            if(datos.getBeneficiaryBankName()==null)
                statement.setNull(38,OracleTypes.NULL);
            else
                statement.setString(38,datos.getBeneficiaryBankName());

            if(datos.getIntermediaryBankName()==null)
                statement.setNull(39,OracleTypes.NULL);
            else
                statement.setString(39,datos.getIntermediaryBankName());

            if(clienteDebito==null)
                statement.setNull(40,OracleTypes.NULL);
            else
                statement.setString(40,clienteDebito.trim());

            if(clienteDebito==null)
                statement.setNull(41,OracleTypes.NULL);
            else
                statement.setString(41,"REF."+clienteDebito.trim());

            String DetailsOfPaymentDescription = "";

            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName()))
                DetailsOfPaymentDescription = "/ACC/FFC." + datos.getFurtherCreditName() + " ACCOUNT " + datos.getFurtherCreditAccount() + " ";

            if (!NullFormatter.isBlank(datos.getRecieverName()))
                DetailsOfPaymentDescription = DetailsOfPaymentDescription + "REF." + datos.getRecieverName();

            String DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription;
            if (DetailsOfPaymentDescription.length() > 150) {
                DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription.substring(0,150);
            }

            if(DetailsOfPaymentDescriptionAux==null)
                statement.setNull(42,OracleTypes.NULL);
            else
                statement.setString(42,DetailsOfPaymentDescriptionAux);


            if(datos.getMoneda()==null)
                statement.setNull(43,OracleTypes.NULL);
            else
                statement.setString(43,datos.getMoneda());

            if(datos.getFurtherCreditAccount()==null)
                statement.setNull(44,OracleTypes.NULL);
            else
                statement.setString(44,datos.getFurtherCreditAccount());

            if(datos.getFurtherCreditName()==null)
                statement.setNull(45,OracleTypes.NULL);
            else
                statement.setString(45,datos.getFurtherCreditName());

            statement.registerOutParameter(46, OracleTypes.VARCHAR);
            statement.registerOutParameter(47, OracleTypes.VARCHAR);

            if(datos.getMotivo()==null)
                statement.setNull(48,OracleTypes.NULL);
            else
                statement.setString(48,datos.getMotivo());

            if (NullFormatter.isBlank(datos.getBeneficiaryBankCodeTypeSwift())) {
                datos.setBeneficiaryBankCodeNumberSwift("");
            } else {
                if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeTypeSwift())) {
                    datos.setBeneficiaryBankCodeTypeSwift("SA");
                }
            }

          /*  if((datos.getBeneficiaryBankCodeTypeSwift()==null)||(datos.getBeneficiaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(49,OracleTypes.NULL);
            else{
                statement.setString(26,datos.getBeneficiaryBankCodeTypeSwift());
                if(strCmbTipoCodBancoBeneficiario==null)
                    statement.setNull(49,OracleTypes.NULL);
                else
                    statement.setString(49,strCmbTipoCodBancoBeneficiario);
            }  */

            if((datos.getBeneficiaryBankCodeTypeSwift()==null)||(datos.getBeneficiaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(49,OracleTypes.NULL);
            else{
                    statement.setString(49,datos.getBeneficiaryBankCodeTypeSwift());
            }


          /*  if((datos.getBeneficiaryBankCodeNumberSwift()==null)||(datos.getBeneficiaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(50,OracleTypes.NULL);
            else{
                statement.setString(27,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
                if(datos.getBeneficiaryBankCodeNumber()==null)
                    statement.setNull(50,OracleTypes.NULL);
                else
                    statement.setString(50,datos.getBeneficiaryBankCodeNumber().toUpperCase());
            }  */

            if((datos.getBeneficiaryBankCodeNumberSwift()==null)||(datos.getBeneficiaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(50,OracleTypes.NULL);
            else{
                statement.setString(50,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
            }



             if (NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift())) {
                datos.setIntermediaryBankCodeNumberSwift("");
            } else {
                if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeTypeSwift())) {
                    datos.setIntermediaryBankCodeTypeSwift("SA");
                }
            }

          /*  if((datos.getIntermediaryBankCodeTypeSwift()==null)||(datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(51,OracleTypes.NULL);
            else{
                statement.setString(29,datos.getIntermediaryBankCodeTypeSwift());
                if(datos.getIntermediaryBankCodeType()==null)
                    statement.setNull(51,OracleTypes.NULL);
                else
                    statement.setString(51,datos.getIntermediaryBankCodeType());
            } */


            if((datos.getIntermediaryBankCodeTypeSwift()==null)||(datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(51,OracleTypes.NULL);
            else{
                statement.setString(51,datos.getIntermediaryBankCodeTypeSwift());
            }




           /* if((datos.getIntermediaryBankCodeNumberSwift()==null)||(datos.getIntermediaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(52,OracleTypes.NULL);
            else{
                statement.setString(30,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
                if((datos.getIntermediaryBankCodeNumber()==null)||(datos.getIntermediaryBankCodeNumber().equalsIgnoreCase("")))
                    statement.setNull(52,OracleTypes.NULL);
                else
                    statement.setString(52,datos.getIntermediaryBankCodeNumber().toUpperCase());
            }   */

            if((datos.getIntermediaryBankCodeNumberSwift()==null)||(datos.getIntermediaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(52,OracleTypes.NULL);
            else{
                statement.setString(52,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
            }



            if(usuario.getTipo()==null)
                statement.setNull(53,OracleTypes.NULL);
            else
                statement.setString(53,usuario.getTipo());
            statement.execute ();


            respuesta = statement.getString(47);
            resultados.add(respuesta);
            resultados.add(statement.getString(46));//num referencia

           numero_instruccion =  statement.getString(46);



            if (!respuesta.equalsIgnoreCase("OK"))  {
                connection.rollback();
                throw (new Exception (respuesta,null));
            } else {
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }

            /*envio de correo*/
            MailManager mailManager = new MailManager("vbtonline");

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
            SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));


            String htmlText = "";
//            String prueba = ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExterna").replaceAll("###","\n");
            //******** Envía correo de notificación de transferencia al cliente que monta la orden de transferencia
            htmlText= ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExternaFC1") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": " + datos.getBeneficiaryAccount() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailBeneficiario") + ": " + datos.getBeneficiaryEmail() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCodigoBancoBeneficiario") + ": " + datos.getBeneficiaryBankCodeNumber().toUpperCase() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreBancoBeneficiario") + ": " + datos.getBeneficiaryBankName();
            if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeNumber().toUpperCase())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCodigoBancoIntermediario") + ": " + datos.getIntermediaryBankCodeNumber().toUpperCase() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreBancoIntermediario") + ": " + datos.getIntermediaryBankName();
            }
            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCuentaFFC") + ": " + datos.getFurtherCreditAccount() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreFFC") + ": " + datos.getFurtherCreditName();
            }
            htmlText =  htmlText +                                                                                                             //.toString().replace(',','.')
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGConcepto") + ": " + datos.getRecieverName().trim() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": ";

                    if(usuario.getTipo().equals("6"))
                        htmlText =  htmlText + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCargada");
                    else
                        htmlText =  htmlText + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGLiberada");

            htmlText =  htmlText + "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgNoResponderEmail_1") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento2") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgNoResponderEmail_2") + "\n\n\n\n";



            resultados.add(htmlText);

            //******** Redacto correo de notificación de la orden de transferencia al asesor, ejecutivo y asistentes
            htmlText= ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgEmailBackOfficeTransferenciaExterna") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCuentaDebito") + ": " + strNumeroCuentaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCarteraDebito") + ": " + strCodigoCarteraDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNumeroCuentaCredito") + ": " + datos.getBeneficiaryAccount() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEmailCodigoBancoBeneficiario") + ": " + datos.getBeneficiaryBankCodeNumber().toUpperCase() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEmailNombreBancoBeneficiario") + ": " + datos.getBeneficiaryBankName();
            if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeNumber().toUpperCase())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEmailCodigoBancoIntermediario") + ": " +  datos.getIntermediaryBankCodeNumber().toUpperCase() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEmailNombreBancoIntermediario") + ": " +  datos.getIntermediaryBankName();
            }
            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEmailCuentaFFC") + ": " + datos.getFurtherCreditAccount() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEmailNombreFFC") + ": " + datos.getFurtherCreditName();
            }
            htmlText =  htmlText +                                                                                                           //.toString().replace(',','.')
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGEstatusTransferencia") + ": ";

            if(usuario.getTipo().equals("6"))
                htmlText =  htmlText + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGCargada");
            else
                htmlText =  htmlText + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("TAGLiberada");

            htmlText =  htmlText + "\n\n\n\n";

            resultados.add(htmlText);
            resultados.add(numReferenciaDebito);
//            resultados.add(numReferenciaDebito2);


            //*Salvar en BOFA*//
            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            direccion = direccionOriginadorTransf(strCodigoCarteraDebito);
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
//            String numReferenciaDebito = obtenerNumeroReferencia();
//            String numReferenciaDebito = numreferenciadebito;

            strBeneficiaryDescription = datos.getBeneficiaryCountryCode() + " " + datos.getBeneficiaryAddress1();
            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress2()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress3()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress3();

            beneficiaryBankDescription = datos.getBeneficiaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress2()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress3()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress3();

            intermediaryBankDescription = new String();

            if ("ABA".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("FW");
            } else if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("SA");
            } else if ("CHIPS".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("CH");
            }

            if (NullFormatter.isBlank(datos.getIntermediaryBankCodeType())) {
                datos.setIntermediaryBankCodeType("");
                datos.setIntermediaryBankCountryCode("");

                if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift())) {
                    intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                    if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                        intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                    if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                        intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
                }


            } else {
                if ("ABA".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("FW");
                } else if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("SA");
                } else if ("CHIPS".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("CH");
                }
                intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
            }





            DetailsOfPaymentDescription = "";

            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName()))
                DetailsOfPaymentDescription = "/ACC/FFC." + datos.getFurtherCreditName() + " ACCOUNT " + datos.getFurtherCreditAccount() + " ";

            if (!NullFormatter.isBlank(datos.getRecieverName()))
                DetailsOfPaymentDescription = DetailsOfPaymentDescription + "REF." + datos.getRecieverName();

            DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription;
            if (DetailsOfPaymentDescription.length() > 150) {
                DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            String[] arrayNombre = null;
            String nombreBOFA = datos.getBeneficiaryName();
            String strRestoNombre = "";
            boolean caracteresCompletos = false;

            if (datos.getBeneficiaryName().length() > 35) {
                nombreBOFA = "";
                arrayNombre = datos.getBeneficiaryName().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        //System.out.println("Palabra " + i + ": " + arrayNombre[i]);
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreBOFA = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreBOFA = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                nombreBOFA = nombreBOFA + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strTxtNombreBeneficiario.length() > 35)

            strRestoNombre = strRestoNombre.trim();
            strBeneficiaryDescription = strRestoNombre + " " + strBeneficiaryDescription;
            strBeneficiaryDescription = strBeneficiaryDescription.trim();

            if (strBeneficiaryDescription.length() > 150) {
                strBeneficiaryDescription = strBeneficiaryDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String nombreBancoBeneficiarioBOFA = datos.getBeneficiaryBankName();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (datos.getBeneficiaryBankName().length() > 35) {
                nombreBancoBeneficiarioBOFA = "";
                arrayNombre = datos.getBeneficiaryBankName().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        //System.out.println("Palabra " + i + ": " + arrayNombre[i]);
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreBancoBeneficiarioBOFA = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreBancoBeneficiarioBOFA = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreBancoBeneficiarioBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                nombreBancoBeneficiarioBOFA = nombreBancoBeneficiarioBOFA + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strTxtNombreBancoBeneficiario.length() > 35)

            strRestoNombre = strRestoNombre.trim();
            beneficiaryBankDescription = strRestoNombre + " " + beneficiaryBankDescription;
            beneficiaryBankDescription = beneficiaryBankDescription.trim();

            if (beneficiaryBankDescription.length() > 150) {
                beneficiaryBankDescription = beneficiaryBankDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            String nombreBancoIntermediarioBOFA = "";

            if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeType())||(!NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift()))) {
                arrayNombre = null;
                nombreBancoIntermediarioBOFA = datos.getIntermediaryBankName();
                strRestoNombre = "";
                caracteresCompletos = false;

                if (datos.getIntermediaryBankName().length() > 35) {
                    nombreBancoIntermediarioBOFA = "";
                    arrayNombre = datos.getIntermediaryBankName().split(" ");
                    for (int i = 0; i < arrayNombre.length; i++) {
                        if (!NullFormatter.isBlank(arrayNombre[i])) {
                            if (i == 0 ) {
                                if (arrayNombre[i].length() <= 35) {
                                    nombreBancoIntermediarioBOFA = arrayNombre[i];
                                } else {
                                    caracteresCompletos = true;
                                    nombreBancoIntermediarioBOFA = arrayNombre[i].substring(0,35);
                                    strRestoNombre = arrayNombre[i].substring(35);
                                }
                            } else {
                                if (!caracteresCompletos && (nombreBancoIntermediarioBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                    nombreBancoIntermediarioBOFA = nombreBancoIntermediarioBOFA + " " + arrayNombre[i];
                                } else {
                                    caracteresCompletos = true;
                                    strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                                }
                            }
                        } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                    }   // End For
                }     // End if (strTxtNombreBancoIntermediario.length() > 35)

                strRestoNombre = strRestoNombre.trim();
                intermediaryBankDescription = strRestoNombre + " " + intermediaryBankDescription;
                intermediaryBankDescription = intermediaryBankDescription.trim();

                if (intermediaryBankDescription.length() > 150) {
                    intermediaryBankDescription = intermediaryBankDescription.substring(0,150);
                }
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String nombreOriginador = clienteDebito.trim();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (clienteDebito.trim().length() > 35) {
                nombreOriginador = "";
                arrayNombre = clienteDebito.trim().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreOriginador = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreOriginador = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreOriginador + " " +  arrayNombre[i]).length() <= 35) {
                                nombreOriginador = nombreOriginador + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strClienteDebito.trim().length() > 35)

            strRestoNombre = strRestoNombre.trim();
            direccion = strRestoNombre + " " + direccion;
            direccion = direccion.trim();

            if (direccion.length() > 180) {
                direccion = direccion.substring(0,180);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String datailsOfPaymentName = "REF." + clienteDebito.trim();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (datailsOfPaymentName.length() > 35) {
                datailsOfPaymentName = "";
                arrayNombre = ("REF." + clienteDebito.trim()).split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                datailsOfPaymentName = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                datailsOfPaymentName = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (datailsOfPaymentName + " " +  arrayNombre[i]).length() <= 35) {
                                datailsOfPaymentName = datailsOfPaymentName + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strClienteDebito.trim().length() > 35)

            strRestoNombre = strRestoNombre.trim();
            DetailsOfPaymentDescription = strRestoNombre + " " + DetailsOfPaymentDescription;
            DetailsOfPaymentDescription = DetailsOfPaymentDescription.trim();

            if (DetailsOfPaymentDescription.length() > 105) {
                DetailsOfPaymentDescription = DetailsOfPaymentDescription.substring(0,105);
            }


//            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.orden_transfe_externa_pr(?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?); end;");

            if(numReferenciaDebito==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,numReferenciaDebito);
            // System.out.println("numReferenciaDebito: "+numReferenciaDebito);
            statement.setString(2,"0000009539");
            // System.out.println("2: 0000009539");
            if(strNumeroCuentaDebito==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,strNumeroCuentaDebito);
            // System.out.println("strNumeroCuentaDebito: "+strNumeroCuentaDebito);
            statement.setString(4,"CAH");
            // System.out.println("4: CAH");
            statement.setString(5,"TEO");
            // System.out.println("5: TEO");

            if(datos.getAmount()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setDouble(6,Double.parseDouble(datos.getAmount()));
            // System.out.println("getAmount: "+Double.parseDouble(datos.getAmount().toString().replace(',','.')));
            strRefBanMov = "CART" + Integer.parseInt(strCodigoCarteraDebito) + "CC" + Integer.parseInt(strNumeroCuentaDebito);
            if(strRefBanMov==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, strRefBanMov);
            // System.out.println("strRefBanMov: "+strRefBanMov);
            if(datos.getMoneda()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8,datos.getMoneda());
            // System.out.println("getMoneda: "+datos.getMoneda());
            if(datos.getBeneficiaryName()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9, datos.getBeneficiaryName());

            // System.out.println("datos.getBeneficiaryName(): "+datos.getBeneficiaryName());
            if(datos.getRecieverName()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10,datos.getRecieverName().trim());
            // System.out.println("datos.getRecieverName().trim(): "+datos.getRecieverName().trim());
            statement.setString(11,"0000009539");
            // System.out.println("11: 0000009539");
            if(strCodigoCarteraDebito==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,strCodigoCarteraDebito);
            // System.out.println("strCodigoCarteraDebito: "+strCodigoCarteraDebito);
            statement.setString(13,"ONL");
            // System.out.println("13: OL2");
            statement.setString(14,ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));
            // System.out.println("nro_Cta_bofa: "+ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));
            if(strCodigoCarteraDebito==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,strCodigoCarteraDebito);
            // System.out.println("strCodigoCarteraDebito: "+strCodigoCarteraDebito);
            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,datos.getBeneficiaryAccountBank());
            // System.out.println("getBeneficiaryAccountBank: "+datos.getBeneficiaryAccountBank());
            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(17,OracleTypes.NULL);
            else
                statement.setString(17,datos.getBeneficiaryAccount());
            // System.out.println("getBeneficiaryAccount: "+datos.getBeneficiaryAccount());
            if(strBeneficiaryDescription==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,strBeneficiaryDescription);
            // System.out.println("strBeneficiaryDescription: "+strBeneficiaryDescription);

           //Invierte el aba por el swift
           if (!datos.getBeneficiaryBankCodeTypeSwift().equals("")){
                statement.setString(19,"SA");
                datos.setBeneficiaryBankCodeNumber(datos.getBeneficiaryBankCodeNumberSwift());
            }else{
                if(datos.getBeneficiaryBankCodeType()==null)
                    statement.setNull(19,OracleTypes.NULL);
                else
                    statement.setString(19,datos.getBeneficiaryBankCodeType());
            }

            // Systemtem.out.println("getBeneficiaryBankCodeType: "+datos.getBeneficiaryBankCodeType());
            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(20,OracleTypes.NULL);
            else
                statement.setString(20,datos.getBeneficiaryBankCodeNumber().toUpperCase());
            // System.out.println("getBeneficiaryBankCodeNumber: "+datos.getBeneficiaryBankCodeNumber().toUpperCase());
            if(beneficiaryBankDescription==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,beneficiaryBankDescription);
            // Systemtem.out.println("beneficiaryBankDescription: "+beneficiaryBankDescription);



         /*   if(datos.getIntermediaryBankCodeType()==null)
                statement.setNull(22,OracleTypes.NULL);
            else
                statement.setString(22,datos.getIntermediaryBankCodeType());  */


            if (!datos.getIntermediaryBankCodeTypeSwift().equals("")){
                statement.setString(22,"SA");
                datos.setIntermediaryBankCodeNumber(datos.getIntermediaryBankCodeNumberSwift());
            }else{
                statement.setString(22,datos.getIntermediaryBankCodeType().toUpperCase());
            }


            // System.out.println("datos.getIntermediaryBankCodeType(): "+datos.getIntermediaryBankCodeType());
            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getIntermediaryBankCodeNumber().toUpperCase());

            // Systemtem.out.println("datos.getIntermediaryBankCodeNumber(): "+datos.getIntermediaryBankCodeNumber());
            if(intermediaryBankDescription==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,intermediaryBankDescription);
            // System.out.println("intermediaryBankDescription: "+intermediaryBankDescription);
            if(direccion==null)
                statement.setNull(25,OracleTypes.NULL);
            else
                statement.setString(25,direccion);
            // System.out.println("direccion: "+direccion);
            statement.setString(26,"TEO");
            // System.out.println("26: TEO ");
            statement.setString(27,"0000009539");
            // System.out.println("27: 0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(28,OracleTypes.NULL);
            else
                statement.setString(28,strNumeroCuentaDebito);
            // System.out.println("strNumeroCuentaDebito: "+strNumeroCuentaDebito);
            statement.setString(29,"CAH");
            // System.out.println("29: CAH");
            if(nombreBOFA==null)
                statement.setNull(30,OracleTypes.NULL);
            else
                statement.setString(30,nombreBOFA);
            // System.out.println("nombreBOFA: "+nombreBOFA);
            if(nombreBancoBeneficiarioBOFA==null)
                statement.setNull(31,OracleTypes.NULL);
            else
                statement.setString(31,nombreBancoBeneficiarioBOFA);
            // System.out.println("nombreBancoBeneficiarioBOFA: "+nombreBancoBeneficiarioBOFA);
            if(nombreBancoIntermediarioBOFA==null)
                statement.setNull(32,OracleTypes.NULL);
            else
                statement.setString(32,nombreBancoIntermediarioBOFA);
            // System.out.println("nombreBancoIntermediarioBOFA: "+nombreBancoIntermediarioBOFA);
            if(nombreOriginador==null)
                statement.setNull(33,OracleTypes.NULL);
            else
                statement.setString(33,nombreOriginador);
            // System.out.println("nombreOriginador: "+nombreOriginador);
            if(datailsOfPaymentName==null)
                statement.setNull(34,OracleTypes.NULL);
            else
                statement.setString(34,datailsOfPaymentName);
            // System.out.println("datailsOfPaymentName: "+datailsOfPaymentName);
            if(DetailsOfPaymentDescription==null)
                statement.setNull(35,OracleTypes.NULL);
            else
                statement.setString(35,DetailsOfPaymentDescription);
            // System.out.println("DetailsOfPaymentDescription: "+DetailsOfPaymentDescription);
            if(datos.getMoneda()==null)
                statement.setNull(36,OracleTypes.NULL);
            else
                statement.setString(36,datos.getMoneda());

            // System.out.println("getMoneda: "+datos.getMoneda());
            statement.setString(37,"16");
            // System.out.println("37: 16");
            statement.setString(38,"");
            // System.out.println("38: ");
            statement.setString(39,"S");
            // System.out.println("39: S");
            statement.setString(40,numero_instruccion);

            statement.registerOutParameter(41, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(41);

            if (!respuesta.equalsIgnoreCase("OK")){
                connection.rollback();
                resultados.set(0,respuesta);
                throw (new Exception (respuesta,null));
            }
            else {
                  /* Si está OK se inserta en libro de ordenes de crm
                     */

                statement  = connection.prepareCall (
                        "begin LIBRO_ORDENES.PR_INS_FLUJO_DOC_FA(?,?,?,?,?,?,?,?,?,?); end;");

                StringBuilder detalle = new StringBuilder();

                detalle.append("<BR>Cuenta Origen: ").append(strNumeroCuentaDebito.trim());

                if(datos.getBeneficiaryName()!=null)
                    detalle.append("<BR> Beneficiario: ").append(datos.getBeneficiaryName());
                detalle.append("<BR>Cuenta Destino: ").append(datos.getBeneficiaryAccount());

                if(datos.getBeneficiaryBankCodeNumberSwift()==null || datos.getBeneficiaryBankCodeNumberSwift().trim().length()==0){
                    detalle.append("<BR>Banco Beneficiario: ").append(datos.getBeneficiaryBankCodeNumber()).append("-").append(datos.getBeneficiaryBankName());
                }else{

                    detalle.append("<BR>Banco Beneficiario: ").append(datos.getBeneficiaryBankCodeNumberSwift()).append("-").append(datos.getBeneficiaryBankName());
                }


                if(datos.getFurtherCreditAccount()!=null && datos.getFurtherCreditAccount().trim().length()>0)
                    detalle.append("<BR>Para Crédito Final a: ").append(datos.getFurtherCreditAccount()).append(", a nombre de: ").append(datos.getFurtherCreditAccount());


                //detalle.append("<BR>el otro campo: ").append(clienteDebito.trim());
                //    datailsOfPaymentName
                statement.setString(1,strCodigoCarteraDebito);
                statement.setString(2,"EXTERNA");
                statement.setString(3,"SAVING");
                statement.setString(4,datos.getMoneda());
                statement.setString(5, datos.getAmount());
                statement.setString(6, detalle.toString());
                statement.setString(7, numero_instruccion);
                statement.setString(8,usuario.getLogin());
                statement.setString(9,usuario.getIP());

                statement.registerOutParameter(10, OracleTypes.VARCHAR);

                statement.execute ();

                respuesta = statement.getString(10);


                if (!respuesta.equalsIgnoreCase("OK"))  {
                    throw (new Exception (respuesta,null));
                }else{
                    connection.commit();
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                }

            }
            //*Salvar en BOFA*//
            resultados.set(0,respuesta);
            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (resultados);
    }

    public List<String> saveToOtherBank_JS (SummaryTransfersToOtherBanksOd datos, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "TransfersIo.saveToOtherBank_JS";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        Integer exitoso=0;
        List<String> resultados= new ArrayList<String>();
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String strNumeroCuentaDebito = new String();
        String strCodigoCarteraDebito = new String();

        String strNumeroCuentaCredito = new String();
        String strCodigoCarteraCredito = new String();
        String direccion = new String();
        String clienteDebito = new String();
        String emailInternos = new String();


        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

//            if (!NullFormatter.isBlank(datos.getBeneficiaryAccount())) {
//                strNumeroCuentaCredito  = datos.getBeneficiaryAccount().substring(0,datos.getBeneficiaryAccount().indexOf("|"));
//                strCodigoCarteraCredito = datos.getBeneficiaryAccount().substring((datos.getBeneficiaryAccount().indexOf("|")+1),datos.getBeneficiaryAccount().length()).trim();
//            }
            emailInternos = EmailsInternos(strNumeroCuentaDebito);
            direccion = direccionOriginadorTransf(strCodigoCarteraDebito);
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            String numReferenciaDebito = obtenerNumeroReferencia();
            String actualizo = actSecuenciaTrans();
//            String numReferenciaDebito2 = obtenerNumeroReferencia2();
            String strCmbTipoCodBancoBeneficiario=new String();
            String strTxtDireccion1BancoBeneficiario = strCmbTipoCodBancoBeneficiario + " " + datos.getBeneficiaryBankCodeType();
            strCmbTipoCodBancoBeneficiario = "";
            String strTxtCodBancoBeneficiario = "";
            String strTxtDireccion2BancoBeneficiario = "";
            String strTxtDireccion3BancoBeneficiario = "";

            if ("ABA".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strCmbTipoCodBancoBeneficiario = "FW";
            } else if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strCmbTipoCodBancoBeneficiario = "SA";
            } else if ("CHIPS".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strCmbTipoCodBancoBeneficiario = "CH";
            } else if ("ACCOUNT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                strTxtDireccion1BancoBeneficiario = strCmbTipoCodBancoBeneficiario + " " + datos.getBeneficiaryBankCodeNumber();
                strCmbTipoCodBancoBeneficiario = "";
                strTxtCodBancoBeneficiario = "";
                strTxtDireccion2BancoBeneficiario = "";
                strTxtDireccion3BancoBeneficiario = "";
            }

            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.orden_transferencia_pr(?,?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?,?); end;");



            if(usuario.getNumContrato()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario.getNumContrato());

            if(strCodigoCarteraDebito==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,strCodigoCarteraDebito);

            statement.setString(3,"C");

            if(numReferenciaDebito==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,numReferenciaDebito);

            statement.setString(5,"0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,strNumeroCuentaDebito);

            statement.setString(7,"CAH");
            statement.setString(8,"TEO");

            if(datos.getAmount()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setDouble(9,Double.parseDouble(datos.getAmount()));
//                statement.setDouble(9,Double.parseDouble(datos.getAmount().toString().replace(',','.')));

            String strRefBanMov = "CART" + Integer.parseInt(strCodigoCarteraDebito) + "CC" + Integer.parseInt(strNumeroCuentaDebito);
            if(strRefBanMov==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10, strRefBanMov);

            if(datos.getMoneda()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11,datos.getMoneda());

            if(datos.getBeneficiaryName()==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12, datos.getBeneficiaryName());

            if(datos.getRecieverName()==null)
                statement.setNull(13,OracleTypes.NULL);
            else
                statement.setString(13,datos.getRecieverName().trim());

            statement.setString(14,"0000009539");

            if(strCodigoCarteraDebito==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,strCodigoCarteraDebito);

            if(usuario.getLogin()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,usuario.getLogin());

            statement.setString(17,"ONL");

            if(datos.getBeneficiaryEmail()==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,datos.getBeneficiaryEmail());

            String emailOrigen=emailClientePrincipalCuenta(strNumeroCuentaDebito);
          /*  if(usuario.getEmail()==null)
                statement.setNull(19,OracleTypes.NULL);
            else
                statement.setString(19,usuario.getEmail());    */

            if(emailOrigen==null)
                statement.setNull(19,OracleTypes.NULL);
            else
                statement.setString(19,emailOrigen);

            statement.setString(20,ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));

            if(strCodigoCarteraDebito==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,strCodigoCarteraDebito);

            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(22,OracleTypes.NULL);
            else
                statement.setString(22,datos.getBeneficiaryAccountBank());

            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getBeneficiaryAccount());

            String strBeneficiaryDescription = datos.getBeneficiaryCountryCode() + " " + datos.getBeneficiaryAddress1();
            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress2()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress3()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress3();


            if(strBeneficiaryDescription==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,strBeneficiaryDescription);

            if(datos.getBeneficiaryTelephone()==null)
                statement.setNull(25,OracleTypes.NULL);
            else
                statement.setString(25,datos.getBeneficiaryTelephone());

            if(strCmbTipoCodBancoBeneficiario==null)
                statement.setNull(26,OracleTypes.NULL);
            else
                statement.setString(26,strCmbTipoCodBancoBeneficiario);

            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(27,OracleTypes.NULL);
            else
                statement.setString(27,datos.getBeneficiaryBankCodeNumber().toUpperCase());

            String beneficiaryBankDescription = datos.getBeneficiaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress2()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress3()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress3();

            if(beneficiaryBankDescription==null)
                statement.setNull(28,OracleTypes.NULL);
            else
                statement.setString(28,beneficiaryBankDescription);

            String intermediaryBankDescription = new String();
            if ((NullFormatter.isBlank(datos.getIntermediaryBankCodeType())&&(NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift())))) {
                datos.setIntermediaryBankCodeType("");
                datos.setIntermediaryBankCountryCode("");
            } else {
                if ("ABA".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("FW");
                } else if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("SA");
                } else if ("CHIPS".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("CH");
                }else if ("ACCOUNT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("");
                }
                intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
            }


            if((datos.getIntermediaryBankCodeType()==null)||(datos.getIntermediaryBankCodeType().equalsIgnoreCase("")))
                statement.setNull(29,OracleTypes.NULL);
            else
                statement.setString(29,datos.getIntermediaryBankCodeType());

            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(30,OracleTypes.NULL);
            else
                statement.setString(30,datos.getIntermediaryBankCodeNumber().toUpperCase());

            if(intermediaryBankDescription==null)
                statement.setNull(31,OracleTypes.NULL);
            else
                statement.setString(31,intermediaryBankDescription);

            if(direccion==null)
                statement.setNull(32,OracleTypes.NULL);
            else
                statement.setString(32,direccion);

            statement.setString(33,"TEO");
            statement.setString(34,"0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(35,OracleTypes.NULL);
            else
                statement.setString(35,strNumeroCuentaDebito);

            statement.setString(36,"CAH");

            if(datos.getBeneficiaryName()==null)
                statement.setNull(37,OracleTypes.NULL);
            else
                statement.setString(37,datos.getBeneficiaryName());

            if(datos.getBeneficiaryBankName()==null)
                statement.setNull(38,OracleTypes.NULL);
            else
                statement.setString(38,datos.getBeneficiaryBankName());

            if(datos.getIntermediaryBankName()==null)
                statement.setNull(39,OracleTypes.NULL);
            else
                statement.setString(39,datos.getIntermediaryBankName());

            if(clienteDebito==null)
                statement.setNull(40,OracleTypes.NULL);
            else
                statement.setString(40,clienteDebito.trim());

            if(clienteDebito==null)
                statement.setNull(41,OracleTypes.NULL);
            else
                statement.setString(41,"REF."+clienteDebito.trim());

            String DetailsOfPaymentDescription = "";

            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName()))
                DetailsOfPaymentDescription = "/ACC/FFC." + datos.getFurtherCreditName() + " ACCOUNT " + datos.getFurtherCreditAccount() + " ";

            if (!NullFormatter.isBlank(datos.getRecieverName()))
                DetailsOfPaymentDescription = DetailsOfPaymentDescription + "REF." + datos.getRecieverName();

            String DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription;
            if (DetailsOfPaymentDescription.length() > 150) {
                DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription.substring(0,150);
            }

            if(DetailsOfPaymentDescriptionAux==null)
                statement.setNull(42,OracleTypes.NULL);
            else
                statement.setString(42,DetailsOfPaymentDescriptionAux);


            if(datos.getMoneda()==null)
                statement.setNull(43,OracleTypes.NULL);
            else
                statement.setString(43,datos.getMoneda());

            if(datos.getFurtherCreditAccount()==null)
                statement.setNull(44,OracleTypes.NULL);
            else
                statement.setString(44,datos.getFurtherCreditAccount());

            if(datos.getFurtherCreditName()==null)
                statement.setNull(45,OracleTypes.NULL);
            else
                statement.setString(45,datos.getFurtherCreditName());

            statement.registerOutParameter(46, OracleTypes.VARCHAR);
            statement.registerOutParameter(47, OracleTypes.VARCHAR);

            if(datos.getMotivo()==null)
                statement.setNull(48,OracleTypes.NULL);
            else
                statement.setString(48,datos.getMotivo());

            if (NullFormatter.isBlank(datos.getBeneficiaryBankCodeTypeSwift())) {
                datos.setBeneficiaryBankCodeNumberSwift("");
            } else {
                if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeTypeSwift())) {
                    datos.setBeneficiaryBankCodeTypeSwift("SA");
                }
            }

          /*  if((datos.getBeneficiaryBankCodeTypeSwift()==null)||(datos.getBeneficiaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(49,OracleTypes.NULL);
            else{
                statement.setString(26,datos.getBeneficiaryBankCodeTypeSwift());
                if(strCmbTipoCodBancoBeneficiario==null)
                    statement.setNull(49,OracleTypes.NULL);
                else
                    statement.setString(49,strCmbTipoCodBancoBeneficiario);
            }  */


            if((datos.getBeneficiaryBankCodeTypeSwift()==null)||(datos.getBeneficiaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(49,OracleTypes.NULL);
            else{
              statement.setString(49,datos.getBeneficiaryBankCodeTypeSwift());
            }


          /*  if((datos.getBeneficiaryBankCodeNumberSwift()==null)||(datos.getBeneficiaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(50,OracleTypes.NULL);
            else{
                statement.setString(27,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
                if(datos.getBeneficiaryBankCodeNumber()==null)
                    statement.setNull(50,OracleTypes.NULL);
                else
                    statement.setString(50,datos.getBeneficiaryBankCodeNumber().toUpperCase());
            } */

            if((datos.getBeneficiaryBankCodeNumberSwift()==null)||(datos.getBeneficiaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(50,OracleTypes.NULL);
            else{
                statement.setString(50,datos.getBeneficiaryBankCodeNumberSwift().toUpperCase());
            }




            if (NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift())) {
                datos.setIntermediaryBankCodeNumberSwift("");
            } else {
                if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeTypeSwift())) {
                    datos.setIntermediaryBankCodeTypeSwift("SA");
                }
            }

         /*   if((datos.getIntermediaryBankCodeTypeSwift()==null)||(datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(51,OracleTypes.NULL);
            else{
                statement.setString(29,datos.getIntermediaryBankCodeTypeSwift());
                if(datos.getIntermediaryBankCodeType()==null)
                    statement.setNull(51,OracleTypes.NULL);
                else
                    statement.setString(51,datos.getIntermediaryBankCodeType());
            }  */


            if((datos.getIntermediaryBankCodeTypeSwift()==null)||(datos.getIntermediaryBankCodeTypeSwift().equalsIgnoreCase("")))
                statement.setNull(51,OracleTypes.NULL);
            else{
                    statement.setString(51,datos.getIntermediaryBankCodeTypeSwift());
            }


          /*  if((datos.getIntermediaryBankCodeNumberSwift()==null)||(datos.getIntermediaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(52,OracleTypes.NULL);
            else{
                statement.setString(30,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
                if(datos.getIntermediaryBankCodeNumber()==null)
                    statement.setNull(52,OracleTypes.NULL);
                else
                    statement.setString(52,datos.getIntermediaryBankCodeNumber().toUpperCase());
            } */

            if((datos.getIntermediaryBankCodeNumberSwift()==null)||(datos.getIntermediaryBankCodeNumberSwift().equalsIgnoreCase("")))
                statement.setNull(52,OracleTypes.NULL);
            else{
                    statement.setString(52,datos.getIntermediaryBankCodeNumberSwift().toUpperCase());
            }

            if(usuario.getTipo()==null)
                statement.setNull(53,OracleTypes.NULL);
            else
                statement.setString(53,usuario.getTipo());

            statement.execute ();


            respuesta = statement.getString(47);
            resultados.add(respuesta);
            resultados.add(statement.getString(46));//num referencia
            String numRef = new String();
            numRef = statement.getString(46);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            /*envio de correo*/
            MailManager mailManager = new MailManager("vbtonline");

            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
            SimpleDateFormat formatoHora  = new SimpleDateFormat("hh:mm:ss a",new Locale("es","ve".toUpperCase()));


            String htmlText = "";
            String htmlText2 = "";
//            String prueba = ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExterna").replaceAll("###","\n");
            //******** Envía correo de notificación de transferencia al cliente que monta la orden de transferencia
            htmlText= ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExternaFC1") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExternaFC2") +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + datos.getBeneficiaryAccount().substring(datos.getBeneficiaryAccount().length()-3,datos.getBeneficiaryAccount().length()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName();

            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCuentaFFC") + ": " + datos.getFurtherCreditAccount() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreFFC") + ": " + datos.getFurtherCreditName();
            }
            htmlText =  htmlText +                                                                                                             //.toString().replace(',','.')
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": ";

            if(usuario.getTipo().equals("6"))
                htmlText =  htmlText + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCargada");
            else
                htmlText =  htmlText + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGLiberada");

            htmlText =  htmlText +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1FC") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento5FC") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento2FC") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento6FC") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento4FC") + "\n\n\n\n";



            //Correo de los Aprobadores
            htmlText2= ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaExternaFC") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": *******" + strNumeroCuentaDebito.substring(7,10) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": *******" + datos.getBeneficiaryAccount().substring(datos.getBeneficiaryAccount().length()-3,datos.getBeneficiaryAccount().length()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName();

            htmlText2 =  htmlText2 +                                                                                                             //.toString().replace(',','.')
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": ";

            if(usuario.getTipo().equals("6"))
                htmlText2 =  htmlText2 + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCargada");
            else
                htmlText2 =  htmlText2 + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGLiberada");

            htmlText2 =  htmlText2 + "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgNoResponderEmail_1") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento1FC") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgAgradecimiento4FC") + "\n\n\n\n";






            //mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExterna"), htmlText);

            //mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), usuario.getEmail(), ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailTransferenciaExterna"), htmlText);

            resultados.add(htmlText);

            //******** Redacto correo de notificación de la orden de transferencia al asesor, ejecutivo y asistentes
            htmlText= ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailBackOfficeTransferenciaExterna") +
                    "\n\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayHora") + ": " + formatoFecha.format(new Date()) + " " + formatoHora.format(new Date()) +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgFechayDireccionIP") + ": " + usuario.getIP() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroRecibo") + ": " + numReferenciaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaDebito") + ": " + strNumeroCuentaDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCarteraDebito") + ": " + strCodigoCarteraDebito +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNumeroCuentaCredito") + ": " + datos.getBeneficiaryAccount() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGNombreBeneficiario") + ": " + datos.getBeneficiaryName() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCodigoBancoBeneficiario") + ": " + datos.getBeneficiaryBankCodeNumber().toUpperCase() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreBancoBeneficiario") + ": " + datos.getBeneficiaryBankName();
            if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeNumber().toUpperCase())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCodigoBancoIntermediario") + ": " +  datos.getIntermediaryBankCodeNumber().toUpperCase() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreBancoIntermediario") + ": " +  datos.getIntermediaryBankName();
            }
            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName())) {
                htmlText = htmlText +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailCuentaFFC") + ": " + datos.getFurtherCreditAccount() +
                        "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEmailNombreFFC") + ": " + datos.getFurtherCreditName();
            }
            htmlText =  htmlText +                                                                                                           //.toString().replace(',','.')
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMonto") + ": " + datos.getMonto_aux() + " " + datos.getMoneda() +
                    "\n" + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGEstatusTransferencia") + ": " + ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGCargada") + "\n\n\n\n";

            resultados.add(htmlText2);
            resultados.add(numReferenciaDebito);
//            resultados.add(numReferenciaDebito2);


            //*Salvar en BOFA*//
            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            direccion = direccionOriginadorTransf(strCodigoCarteraDebito);
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
//            String numReferenciaDebito = obtenerNumeroReferencia();
//            String numReferenciaDebito = numreferenciadebito;

            strBeneficiaryDescription = datos.getBeneficiaryCountryCode() + " " + datos.getBeneficiaryAddress1();
            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress2()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress3()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress3();

            beneficiaryBankDescription = datos.getBeneficiaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress2()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress3()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress3();

            intermediaryBankDescription = new String();

            if ("ABA".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("FW");
            } else if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("SA");
            } else if ("CHIPS".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("CH");
            }else if ("ACCOUNT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())){
                datos.setBeneficiaryBankCodeType("");
            }

            if (NullFormatter.isBlank(datos.getIntermediaryBankCodeType())) {


                if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift())) {
                    intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                    if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                        intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                    if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                        intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
                }
                datos.setIntermediaryBankCodeType("");
                datos.setIntermediaryBankCountryCode("");

            } else {
                if ("ABA".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("FW");
                } else if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("SA");
                } else if ("CHIPS".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("CH");
                }
                intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
            }



            DetailsOfPaymentDescription = "";

            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName()))
                DetailsOfPaymentDescription = "/ACC/FFC." + datos.getFurtherCreditName() + " ACCOUNT " + datos.getFurtherCreditAccount() + " ";

            if (!NullFormatter.isBlank(datos.getRecieverName()))
                DetailsOfPaymentDescription = DetailsOfPaymentDescription + "REF." + datos.getRecieverName();

            DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription;
            if (DetailsOfPaymentDescription.length() > 150) {
                DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            String[] arrayNombre = null;
            String nombreBOFA = datos.getBeneficiaryName();
            String strRestoNombre = "";
            boolean caracteresCompletos = false;

            if (datos.getBeneficiaryName().length() > 35) {
                nombreBOFA = "";
                arrayNombre = datos.getBeneficiaryName().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        //System.out.println("Palabra " + i + ": " + arrayNombre[i]);
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreBOFA = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreBOFA = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                nombreBOFA = nombreBOFA + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strTxtNombreBeneficiario.length() > 35)

            strRestoNombre = strRestoNombre.trim();
            strBeneficiaryDescription = strRestoNombre + " " + strBeneficiaryDescription;
            strBeneficiaryDescription = strBeneficiaryDescription.trim();

            if (strBeneficiaryDescription.length() > 150) {
                strBeneficiaryDescription = strBeneficiaryDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String nombreBancoBeneficiarioBOFA = datos.getBeneficiaryBankName();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (datos.getBeneficiaryBankName().length() > 35) {
                nombreBancoBeneficiarioBOFA = "";
                arrayNombre = datos.getBeneficiaryBankName().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        //System.out.println("Palabra " + i + ": " + arrayNombre[i]);
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreBancoBeneficiarioBOFA = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreBancoBeneficiarioBOFA = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreBancoBeneficiarioBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                nombreBancoBeneficiarioBOFA = nombreBancoBeneficiarioBOFA + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strTxtNombreBancoBeneficiario.length() > 35)

            strRestoNombre = strRestoNombre.trim();
            beneficiaryBankDescription = strRestoNombre + " " + beneficiaryBankDescription;
            beneficiaryBankDescription = beneficiaryBankDescription.trim();

            if (beneficiaryBankDescription.length() > 150) {
                beneficiaryBankDescription = beneficiaryBankDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            String nombreBancoIntermediarioBOFA = "";

            if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeType())||(!NullFormatter.isBlank(datos.getIntermediaryBankCodeTypeSwift()))) {
                arrayNombre = null;
                nombreBancoIntermediarioBOFA = datos.getIntermediaryBankName();
                strRestoNombre = "";
                caracteresCompletos = false;

                if (datos.getIntermediaryBankName().length() > 35) {
                    nombreBancoIntermediarioBOFA = "";
                    arrayNombre = datos.getIntermediaryBankName().split(" ");
                    for (int i = 0; i < arrayNombre.length; i++) {
                        if (!NullFormatter.isBlank(arrayNombre[i])) {
                            if (i == 0 ) {
                                if (arrayNombre[i].length() <= 35) {
                                    nombreBancoIntermediarioBOFA = arrayNombre[i];
                                } else {
                                    caracteresCompletos = true;
                                    nombreBancoIntermediarioBOFA = arrayNombre[i].substring(0,35);
                                    strRestoNombre = arrayNombre[i].substring(35);
                                }
                            } else {
                                if (!caracteresCompletos && (nombreBancoIntermediarioBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                    nombreBancoIntermediarioBOFA = nombreBancoIntermediarioBOFA + " " + arrayNombre[i];
                                } else {
                                    caracteresCompletos = true;
                                    strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                                }
                            }
                        } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                    }   // End For
                }     // End if (strTxtNombreBancoIntermediario.length() > 35)

                strRestoNombre = strRestoNombre.trim();
                intermediaryBankDescription = strRestoNombre + " " + intermediaryBankDescription;
                intermediaryBankDescription = intermediaryBankDescription.trim();

                if (intermediaryBankDescription.length() > 150) {
                    intermediaryBankDescription = intermediaryBankDescription.substring(0,150);
                }
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String nombreOriginador = clienteDebito.trim();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (clienteDebito.trim().length() > 35) {
                nombreOriginador = "";
                arrayNombre = clienteDebito.trim().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreOriginador = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreOriginador = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreOriginador + " " +  arrayNombre[i]).length() <= 35) {
                                nombreOriginador = nombreOriginador + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strClienteDebito.trim().length() > 35)

            strRestoNombre = strRestoNombre.trim();
            direccion = strRestoNombre + " " + direccion;
            direccion = direccion.trim();

            if (direccion.length() > 180) {
                direccion = direccion.substring(0,180);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String datailsOfPaymentName = "REF." + clienteDebito.trim();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (datailsOfPaymentName.length() > 35) {
                datailsOfPaymentName = "";
                arrayNombre = ("REF." + clienteDebito.trim()).split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                datailsOfPaymentName = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                datailsOfPaymentName = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (datailsOfPaymentName + " " +  arrayNombre[i]).length() <= 35) {
                                datailsOfPaymentName = datailsOfPaymentName + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strClienteDebito.trim().length() > 35)

            strRestoNombre = strRestoNombre.trim();
            DetailsOfPaymentDescription = strRestoNombre + " " + DetailsOfPaymentDescription;
            DetailsOfPaymentDescription = DetailsOfPaymentDescription.trim();

            if (DetailsOfPaymentDescription.length() > 105) {
                DetailsOfPaymentDescription = DetailsOfPaymentDescription.substring(0,105);
            }


            statement  = connection.prepareCall ("begin TransferenciaHandler.orden_externa_temp_pr(?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?," +
                    "?,?,?,?,?,?,?,?,?,?,?); end;");

            if(numReferenciaDebito==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,numReferenciaDebito);
            // System.out.println("numReferenciaDebito: "+numReferenciaDebito);
            statement.setString(2,"0000009539");
            // System.out.println("2: 0000009539");
            if(strNumeroCuentaDebito==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,strNumeroCuentaDebito);
            // System.out.println("strNumeroCuentaDebito: "+strNumeroCuentaDebito);
            statement.setString(4,"CAH");
            // System.out.println("4: CAH");
            statement.setString(5,"TEO");
            // System.out.println("5: TEO");

            if(datos.getAmount()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setDouble(6,Double.parseDouble(datos.getAmount()));
            // System.out.println("getAmount: "+Double.parseDouble(datos.getAmount().toString().replace(',','.')));
            strRefBanMov = "CART" + Integer.parseInt(strCodigoCarteraDebito) + "CC" + Integer.parseInt(strNumeroCuentaDebito);
            if(strRefBanMov==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, strRefBanMov);
            // System.out.println("strRefBanMov: "+strRefBanMov);
            if(datos.getMoneda()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8,datos.getMoneda());
            // System.out.println("getMoneda: "+datos.getMoneda());
            if(datos.getBeneficiaryName()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9, datos.getBeneficiaryName());
            // System.out.println("datos.getBeneficiaryName(): "+datos.getBeneficiaryName());
            if(datos.getRecieverName()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10,datos.getRecieverName().trim());
            // System.out.println("datos.getRecieverName().trim(): "+datos.getRecieverName().trim());
            statement.setString(11,"0000009539");
            // System.out.println("11: 0000009539");
            if(strCodigoCarteraDebito==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,strCodigoCarteraDebito);
            // System.out.println("strCodigoCarteraDebito: "+strCodigoCarteraDebito);
            statement.setString(13,"ONL");
            // System.out.println("13: OL2");
            statement.setString(14,ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));
            // System.out.println("nro_Cta_bofa: "+ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));
            if(strCodigoCarteraDebito==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,strCodigoCarteraDebito);
            // System.out.println("strCodigoCarteraDebito: "+strCodigoCarteraDebito);
            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,datos.getBeneficiaryAccountBank());
            // System.out.println("getBeneficiaryAccountBank: "+datos.getBeneficiaryAccountBank());
            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(17,OracleTypes.NULL);
            else
                statement.setString(17,datos.getBeneficiaryAccount());
            // System.out.println("getBeneficiaryAccount: "+datos.getBeneficiaryAccount());
            if(strBeneficiaryDescription==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,strBeneficiaryDescription);
            // System.out.println("strBeneficiaryDescription: "+strBeneficiaryDescription);

			/*
            if(datos.getBeneficiaryBankCodeType()==null)
                statement.setNull(19,OracleTypes.NULL);
            else
                statement.setString(19,datos.getBeneficiaryBankCodeType()); */

            //Invierte el aba por el swift
            if (!datos.getBeneficiaryBankCodeTypeSwift().equals("")){
                statement.setString(19,"SA");
                datos.setBeneficiaryBankCodeNumber(datos.getBeneficiaryBankCodeNumberSwift());
            }else{
                if(datos.getBeneficiaryBankCodeType()==null)
                    statement.setNull(19,OracleTypes.NULL);
                else
                    statement.setString(19,datos.getBeneficiaryBankCodeType());
            }


            // Systemtem.out.println("getBeneficiaryBankCodeType: "+datos.getBeneficiaryBankCodeType());
            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(20,OracleTypes.NULL);
            else
                statement.setString(20,datos.getBeneficiaryBankCodeNumber().toUpperCase());
            // System.out.println("getBeneficiaryBankCodeNumber: "+datos.getBeneficiaryBankCodeNumber().toUpperCase());
            if(beneficiaryBankDescription==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,beneficiaryBankDescription);
            // Systemtem.out.println("beneficiaryBankDescription: "+beneficiaryBankDescription);


//            if(datos.getIntermediaryBankCodeType()==null)
//                statement.setNull(22,OracleTypes.NULL);
//            else
//                statement.setString(22,datos.getIntermediaryBankCodeType());



            if (!datos.getIntermediaryBankCodeTypeSwift().equals("")){
                statement.setString(22,"SA");
                datos.setIntermediaryBankCodeNumber(datos.getIntermediaryBankCodeNumberSwift());
            }else{
                statement.setString(22,datos.getIntermediaryBankCodeType().toUpperCase());
            }


            // System.out.println("datos.getIntermediaryBankCodeType(): "+datos.getIntermediaryBankCodeType());
            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getIntermediaryBankCodeNumber().toUpperCase());
            // Systemtem.out.println("datos.getIntermediaryBankCodeNumber(): "+datos.getIntermediaryBankCodeNumber());
            if(intermediaryBankDescription==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,intermediaryBankDescription);
            // System.out.println("intermediaryBankDescription: "+intermediaryBankDescription);
            if(direccion==null)
                statement.setNull(25,OracleTypes.NULL);
            else
                statement.setString(25,direccion);
            // System.out.println("direccion: "+direccion);
            statement.setString(26,"TEO");
            // System.out.println("26: TEO ");
            statement.setString(27,"0000009539");
            // System.out.println("27: 0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(28,OracleTypes.NULL);
            else
                statement.setString(28,strNumeroCuentaDebito);
            // System.out.println("strNumeroCuentaDebito: "+strNumeroCuentaDebito);
            statement.setString(29,"CAH");
            // System.out.println("29: CAH");
            if(nombreBOFA==null)
                statement.setNull(30,OracleTypes.NULL);
            else
                statement.setString(30,nombreBOFA);
            // System.out.println("nombreBOFA: "+nombreBOFA);
            if(nombreBancoBeneficiarioBOFA==null)
                statement.setNull(31,OracleTypes.NULL);
            else
                statement.setString(31,nombreBancoBeneficiarioBOFA);
            // System.out.println("nombreBancoBeneficiarioBOFA: "+nombreBancoBeneficiarioBOFA);
            if(nombreBancoIntermediarioBOFA==null)
                statement.setNull(32,OracleTypes.NULL);
            else
                statement.setString(32,nombreBancoIntermediarioBOFA);
            // System.out.println("nombreBancoIntermediarioBOFA: "+nombreBancoIntermediarioBOFA);
            if(nombreOriginador==null)
                statement.setNull(33,OracleTypes.NULL);
            else
                statement.setString(33,nombreOriginador);
            // System.out.println("nombreOriginador: "+nombreOriginador);
            if(datailsOfPaymentName==null)
                statement.setNull(34,OracleTypes.NULL);
            else
                statement.setString(34,datailsOfPaymentName);
            // System.out.println("datailsOfPaymentName: "+datailsOfPaymentName);
            if(DetailsOfPaymentDescription==null)
                statement.setNull(35,OracleTypes.NULL);
            else
                statement.setString(35,DetailsOfPaymentDescription);
            // System.out.println("DetailsOfPaymentDescription: "+DetailsOfPaymentDescription);
            if(datos.getMoneda()==null)
                statement.setNull(36,OracleTypes.NULL);
            else
                statement.setString(36,datos.getMoneda());
            // System.out.println("getMoneda: "+datos.getMoneda());
            statement.setString(37,"16");
            // System.out.println("37: 16");
            statement.setString(38,"");
            // System.out.println("38: ");
            statement.setString(39,"S");

            if(numRef==null)
                statement.setNull(40,OracleTypes.NULL);
            else
                statement.setString(40,numRef);

            statement.registerOutParameter(41, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(41);

            if (!respuesta.equalsIgnoreCase("OK")){
                resultados.set(0,respuesta);
                throw (new Exception (respuesta,null));
            }
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            //*Salvar en BOFA*//
            resultados.set(0,respuesta);
            connection.commit();

            if(usuario.getTipo().equals("6")){
                emailInternos = EmailsAprobadores(usuario.getNumContrato(), "7");
                mailManager.sendPlainMail(ResourceBundle.getBundle("vbtonline").getString("adminmail"), emailInternos, ResourceBundle.getBundle("Transferencias"+idioma).getString("TAGMsgEmailNotificacionTransferenciaExterna"), htmlText2);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (resultados);
    }

    public List<String> validarParametrosPersonales (String codpercli,  String monto, String tipoTransf, String contrato, String tipoContrato) throws Exception {
        final String origen = "TransfersIo.validarParametrosPersonales";
        Connection connection = null;
        CallableStatement statement = null;

        List<String> resultados= new ArrayList<String>();
        long  time = 0;


        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();


           statement  = connection.prepareCall ("begin TransferenciaHandler.validar_pp_pr(?,?,?,?,?,?,?); end;");

            if(codpercli==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,codpercli);

            if(monto==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setDouble(2,Double.parseDouble(monto));

            if(contrato==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, contrato);



            if(tipoTransf==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, tipoTransf);


            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            if(tipoContrato==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, tipoContrato);


            statement.execute ();


            respuesta = statement.getString(6);
            resultados.add(respuesta);
            resultados.add(statement.getString(5));


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }

        return (resultados);
    }



    public List<String> obtenerMontosLiberadorFC (String contrato) throws Exception {
        final String origen = "TransfersIo.validarParametrosPersonales";
        Connection connection = null;
        CallableStatement statement = null;

        List<String> resultados= new ArrayList<String>();
        long  time = 0;


        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();


            statement  = connection.prepareCall ("begin TransferenciaHandler.montos_liberador_fc(?,?,?,?,?,?); end;");

            if(contrato==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,contrato);

            statement.registerOutParameter(2, OracleTypes.NUMBER);
            statement.registerOutParameter(3, OracleTypes.NUMBER);
            statement.registerOutParameter(4, OracleTypes.NUMBER);
            statement.registerOutParameter(5, OracleTypes.NUMBER);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);



            statement.execute ();


            respuesta = statement.getString(6);
            if(respuesta.equalsIgnoreCase("OK")){
                resultados.add(statement.getString(2));
                resultados.add(statement.getString(3));
                resultados.add(statement.getString(4));
                resultados.add(statement.getString(5));
            }else{
                resultados.add("0");
                resultados.add("0");
                resultados.add("0");
                resultados.add("0");
            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }

        return (resultados);
    }


    public String saveToOtherBankJS_BOFA_Temporal (SummaryTransfersToOtherBanksOd datos, String numRef) throws Exception {
        final String origen = "TransfersIo.saveToOtherBankJS_BOFA_Temporal";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        Integer exitoso=0;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String strNumeroCuentaDebito = new String();
        String strCodigoCarteraDebito = new String();

        String strNumeroCuentaCredito = new String();
        String strCodigoCarteraCredito = new String();
        String direccion = new String();
        String clienteDebito = new String();


        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            if (!NullFormatter.isBlank(datos.getAccountCode())) {
                strNumeroCuentaDebito  = datos.getAccountCode().substring(0,datos.getAccountCode().indexOf("|")).trim();
                strCodigoCarteraDebito = datos.getAccountCode().substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()).trim();
            }

            direccion = direccionOriginadorTransf(strCodigoCarteraDebito);
            clienteDebito = clienteDebito(strCodigoCarteraDebito);
            String numReferenciaDebito = obtenerNumeroReferencia();

            String strBeneficiaryDescription = datos.getBeneficiaryCountryCode() + " " + datos.getBeneficiaryAddress1();
            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress2()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryAddress3()))
                strBeneficiaryDescription = strBeneficiaryDescription + " " + datos.getBeneficiaryAddress3();

            String beneficiaryBankDescription = datos.getBeneficiaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress2()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress2();

            if (!NullFormatter.isBlank(datos.getBeneficiaryBankAddress3()))
                beneficiaryBankDescription = beneficiaryBankDescription + " " + datos.getBeneficiaryBankAddress3();

            if ("ABA".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("FW");
            } else if ("SWIFT".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("SA");
            } else if ("CHIPS".equalsIgnoreCase(datos.getBeneficiaryBankCodeType())) {
                datos.setBeneficiaryBankCodeType("CH");
            }

            String intermediaryBankDescription = new String();
            if (NullFormatter.isBlank(datos.getIntermediaryBankCodeType())) {
                datos.setIntermediaryBankCodeType("");
                datos.setIntermediaryBankCountryCode("");
            } else {
                if ("ABA".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("FW");
                } else if ("SWIFT".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("SA");
                } else if ("CHIPS".equalsIgnoreCase(datos.getIntermediaryBankCodeType())) {
                    datos.setIntermediaryBankCodeType("CH");
                }
                intermediaryBankDescription = datos.getIntermediaryBankCountryCode() + " " + datos.getBeneficiaryBankAddress1();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress2()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress2();

                if (!NullFormatter.isBlank(datos.getIntermediaryBankAddress3()))
                    intermediaryBankDescription = intermediaryBankDescription + " " + datos.getIntermediaryBankAddress3();
            }

            String DetailsOfPaymentDescription = "";

            if (!NullFormatter.isBlank(datos.getFurtherCreditAccount()) && !NullFormatter.isBlank(datos.getFurtherCreditName()))
                DetailsOfPaymentDescription = "/ACC/FFC." + datos.getFurtherCreditName() + " ACCOUNT " + datos.getFurtherCreditAccount() + " ";

            if (!NullFormatter.isBlank(datos.getRecieverName()))
                DetailsOfPaymentDescription = DetailsOfPaymentDescription + "REF." + datos.getRecieverName();

            String DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription;
            if (DetailsOfPaymentDescription.length() > 150) {
                DetailsOfPaymentDescriptionAux = DetailsOfPaymentDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            String[] arrayNombre = null;
            String nombreBOFA = datos.getBeneficiaryName();
            String strRestoNombre = "";
            boolean caracteresCompletos = false;

            if (datos.getBeneficiaryName().length() > 35) {
                nombreBOFA = "";
                arrayNombre = datos.getBeneficiaryName().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        //System.out.println("Palabra " + i + ": " + arrayNombre[i]);
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreBOFA = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreBOFA = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                nombreBOFA = nombreBOFA + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strTxtNombreBeneficiario.length() > 35)

            strRestoNombre = strRestoNombre.trim();
            strBeneficiaryDescription = strRestoNombre + " " + strBeneficiaryDescription;
            strBeneficiaryDescription = strBeneficiaryDescription.trim();

            if (strBeneficiaryDescription.length() > 150) {
                strBeneficiaryDescription = strBeneficiaryDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String nombreBancoBeneficiarioBOFA = datos.getBeneficiaryBankName();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (datos.getBeneficiaryBankName().length() > 35) {
                nombreBancoBeneficiarioBOFA = "";
                arrayNombre = datos.getBeneficiaryBankName().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        //System.out.println("Palabra " + i + ": " + arrayNombre[i]);
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreBancoBeneficiarioBOFA = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreBancoBeneficiarioBOFA = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreBancoBeneficiarioBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                nombreBancoBeneficiarioBOFA = nombreBancoBeneficiarioBOFA + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strTxtNombreBancoBeneficiario.length() > 35)

            strRestoNombre = strRestoNombre.trim();
            beneficiaryBankDescription = strRestoNombre + " " + beneficiaryBankDescription;
            beneficiaryBankDescription = beneficiaryBankDescription.trim();

            if (beneficiaryBankDescription.length() > 150) {
                beneficiaryBankDescription = beneficiaryBankDescription.substring(0,150);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            String nombreBancoIntermediarioBOFA = "";

            if (!NullFormatter.isBlank(datos.getIntermediaryBankCodeType())) {
                arrayNombre = null;
                nombreBancoIntermediarioBOFA = datos.getIntermediaryBankName();
                strRestoNombre = "";
                caracteresCompletos = false;

                if (datos.getIntermediaryBankName().length() > 35) {
                    nombreBancoIntermediarioBOFA = "";
                    arrayNombre = datos.getIntermediaryBankName().split(" ");
                    for (int i = 0; i < arrayNombre.length; i++) {
                        if (!NullFormatter.isBlank(arrayNombre[i])) {
                            if (i == 0 ) {
                                if (arrayNombre[i].length() <= 35) {
                                    nombreBancoIntermediarioBOFA = arrayNombre[i];
                                } else {
                                    caracteresCompletos = true;
                                    nombreBancoIntermediarioBOFA = arrayNombre[i].substring(0,35);
                                    strRestoNombre = arrayNombre[i].substring(35);
                                }
                            } else {
                                if (!caracteresCompletos && (nombreBancoIntermediarioBOFA + " " +  arrayNombre[i]).length() <= 35) {
                                    nombreBancoIntermediarioBOFA = nombreBancoIntermediarioBOFA + " " + arrayNombre[i];
                                } else {
                                    caracteresCompletos = true;
                                    strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                                }
                            }
                        } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                    }   // End For
                }     // End if (strTxtNombreBancoIntermediario.length() > 35)

                strRestoNombre = strRestoNombre.trim();
                intermediaryBankDescription = strRestoNombre + " " + intermediaryBankDescription;
                intermediaryBankDescription = intermediaryBankDescription.trim();

                if (intermediaryBankDescription.length() > 150) {
                    intermediaryBankDescription = intermediaryBankDescription.substring(0,150);
                }
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String nombreOriginador = clienteDebito.trim();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (clienteDebito.trim().length() > 35) {
                nombreOriginador = "";
                arrayNombre = clienteDebito.trim().split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                nombreOriginador = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                nombreOriginador = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (nombreOriginador + " " +  arrayNombre[i]).length() <= 35) {
                                nombreOriginador = nombreOriginador + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strClienteDebito.trim().length() > 35)

            strRestoNombre = strRestoNombre.trim();
            direccion = strRestoNombre + " " + direccion;
            direccion = direccion.trim();

            if (direccion.length() > 180) {
                direccion = direccion.substring(0,180);
            }

            /*************************************************************
             * Tratamiento Especial debido al tamaño de los campos de nombres en la tabla movimiento_bofa
             * ***************************************************************/
            arrayNombre = null;
            String datailsOfPaymentName = "REF." + clienteDebito.trim();
            strRestoNombre = "";
            caracteresCompletos = false;

            if (datailsOfPaymentName.length() > 35) {
                datailsOfPaymentName = "";
                arrayNombre = ("REF." + clienteDebito.trim()).split(" ");
                for (int i = 0; i < arrayNombre.length; i++) {
                    if (!NullFormatter.isBlank(arrayNombre[i])) {
                        if (i == 0 ) {
                            if (arrayNombre[i].length() <= 35) {
                                datailsOfPaymentName = arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                datailsOfPaymentName = arrayNombre[i].substring(0,35);
                                strRestoNombre = arrayNombre[i].substring(35);
                            }
                        } else {
                            if (!caracteresCompletos && (datailsOfPaymentName + " " +  arrayNombre[i]).length() <= 35) {
                                datailsOfPaymentName = datailsOfPaymentName + " " + arrayNombre[i];
                            } else {
                                caracteresCompletos = true;
                                strRestoNombre = strRestoNombre + " " + arrayNombre[i];
                            }
                        }
                    } // End if (!NullFormatter.isBlank(arrayNombre[i]))
                }   // End For
            }     // End if (strClienteDebito.trim().length() > 35)

            strRestoNombre = strRestoNombre.trim();
            DetailsOfPaymentDescription = strRestoNombre + " " + DetailsOfPaymentDescription;
            DetailsOfPaymentDescription = DetailsOfPaymentDescription.trim();

            if (DetailsOfPaymentDescription.length() > 105) {
                DetailsOfPaymentDescription = DetailsOfPaymentDescription.substring(0,105);
            }


            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.orden_externa_temp_pr(?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?," +
                                                                                                    "?,?,?,?,?,?,?,?,?,?,?); end;");

            if(numReferenciaDebito==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,numReferenciaDebito);
//             System.out.println("numReferenciaDebito: "+numReferenciaDebito);
            statement.setString(2,"0000009539");
//            System.out.println("2: 0000009539");
            if(strNumeroCuentaDebito==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,strNumeroCuentaDebito);
//            System.out.println("strNumeroCuentaDebito"+strNumeroCuentaDebito);
            statement.setString(4,"CAH");
//            System.out.println("4: CAH");
            statement.setString(5,"TEO");
//            System.out.println("5: TEO");

            if(datos.getAmount()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setDouble(6,Double.parseDouble(datos.getAmount()));
//            System.out.println("amount: "+Double.parseDouble(datos.getAmount().toString().replace(',','.')));

            String strRefBanMov = "CART" + Integer.parseInt(strCodigoCarteraDebito) + "CC" + Integer.parseInt(strNumeroCuentaDebito);
            if(strRefBanMov==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, strRefBanMov);
//            System.out.println("strRefBanMov: "+strRefBanMov);
            if(datos.getMoneda()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setString(8,datos.getMoneda());
//            System.out.println("getMoneda: "+datos.getMoneda());
            if(datos.getBeneficiaryName()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setString(9, datos.getBeneficiaryName());
//            System.out.println("getBeneficiaryName: "+datos.getBeneficiaryName());
            if(datos.getRecieverName()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setString(10,datos.getRecieverName().trim());
//            System.out.println("getRecieverName: "+datos.getRecieverName());
            statement.setString(11,"0000009539");
//            System.out.println("11: 0000009539 ");

            if(strCodigoCarteraDebito==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,strCodigoCarteraDebito);
//            System.out.println("strCodigoCarteraDebito: "+strCodigoCarteraDebito);

            statement.setString(13,"ONL");
//            System.out.println("13: OL2 ");
            statement.setString(14,ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));
//            System.out.println("14:"+ResourceBundle.getBundle("vbtonline").getString("nro_cta_bofa"));

            if(strCodigoCarteraDebito==null)
                statement.setNull(15,OracleTypes.NULL);
            else
                statement.setString(15,strCodigoCarteraDebito);
//            System.out.println("strCodigoCarteraDebito: "+strCodigoCarteraDebito);
            if(datos.getBeneficiaryAccountBank()==null)
                statement.setNull(16,OracleTypes.NULL);
            else
                statement.setString(16,datos.getBeneficiaryAccountBank());
//            System.out.println("getBeneficiaryAccountBank: "+datos.getBeneficiaryAccountBank());
            if(datos.getBeneficiaryAccount()==null)
                statement.setNull(17,OracleTypes.NULL);
            else
                statement.setString(17,datos.getBeneficiaryAccount());
//            System.out.println("getBeneficiaryAccount: "+datos.getBeneficiaryAccount());
            if(strBeneficiaryDescription==null)
                statement.setNull(18,OracleTypes.NULL);
            else
                statement.setString(18,strBeneficiaryDescription);
//            System.out.println("strBeneficiaryDescription: "+strBeneficiaryDescription);
            if(datos.getBeneficiaryBankCodeType()==null)
                statement.setNull(19,OracleTypes.NULL);
            else
                statement.setString(19,datos.getBeneficiaryBankCodeType());
//            System.out.println("getBeneficiaryBankCodeType: "+datos.getBeneficiaryBankCodeType());
            if(datos.getBeneficiaryBankCodeNumber()==null)
                statement.setNull(20,OracleTypes.NULL);
            else
                statement.setString(20,datos.getBeneficiaryBankCodeNumber().toUpperCase());
//            System.out.println("getBeneficiaryBankCodeNumber: "+datos.getBeneficiaryBankCodeNumber());
            if(beneficiaryBankDescription==null)
                statement.setNull(21,OracleTypes.NULL);
            else
                statement.setString(21,beneficiaryBankDescription);
//            System.out.println("beneficiaryBankDescription: "+beneficiaryBankDescription);
            if(datos.getIntermediaryBankCodeType()==null)
                statement.setNull(22,OracleTypes.NULL);
            else
                statement.setString(22,datos.getIntermediaryBankCodeType());
//            System.out.println("datos.getIntermediaryBankCodeType(): "+datos.getIntermediaryBankCodeType());
            if(datos.getIntermediaryBankCodeNumber()==null)
                statement.setNull(23,OracleTypes.NULL);
            else
                statement.setString(23,datos.getIntermediaryBankCodeNumber().toUpperCase());
//            System.out.println("getIntermediaryBankCodeNumber: "+datos.getIntermediaryBankCodeNumber());
            if(intermediaryBankDescription==null)
                statement.setNull(24,OracleTypes.NULL);
            else
                statement.setString(24,intermediaryBankDescription);
//            System.out.println("intermediaryBankDescription: "+intermediaryBankDescription);
            if(direccion==null)
                statement.setNull(25,OracleTypes.NULL);
            else
                statement.setString(25,direccion);
//            System.out.println("direccion: "+direccion);
            statement.setString(26,"TEO");
//            System.out.println("26: TEO");
            statement.setString(27,"0000009539");
//            System.out.println("26: 0000009539");

            if(strNumeroCuentaDebito==null)
                statement.setNull(28,OracleTypes.NULL);
            else
                statement.setString(28,strNumeroCuentaDebito);
//            System.out.println("strNumeroCuentaDebito"+strNumeroCuentaDebito);
            statement.setString(29,"CAH");
//            System.out.println("29: CAH");
            if(nombreBOFA==null)
                statement.setNull(30,OracleTypes.NULL);
            else
                statement.setString(30,nombreBOFA);
//            System.out.println("nombreBOFA: "+nombreBOFA);
            if(nombreBancoBeneficiarioBOFA==null)
                statement.setNull(31,OracleTypes.NULL);
            else
                statement.setString(31,nombreBancoBeneficiarioBOFA);
//            System.out.println("nombreBancoBeneficiarioBOFA: "+nombreBancoBeneficiarioBOFA);
            if(nombreBancoIntermediarioBOFA==null)
                statement.setNull(32,OracleTypes.NULL);
            else
                statement.setString(32,nombreBancoIntermediarioBOFA);
//            System.out.println("nombreBancoIntermediarioBOFA: "+nombreBancoIntermediarioBOFA);
            if(nombreOriginador==null)
                statement.setNull(33,OracleTypes.NULL);
            else
                statement.setString(33,nombreOriginador);
//            System.out.println("nombreOriginador: "+nombreOriginador);
            if(datailsOfPaymentName==null)
                statement.setNull(34,OracleTypes.NULL);
            else
                statement.setString(34,datailsOfPaymentName);
//            System.out.println("datailsOfPaymentName: "+datailsOfPaymentName);
            if(DetailsOfPaymentDescription==null)
                statement.setNull(35,OracleTypes.NULL);
            else
                statement.setString(35,DetailsOfPaymentDescription);
//            System.out.println("DetailsOfPaymentDescription: "+DetailsOfPaymentDescription);
            if(datos.getMoneda()==null)
                statement.setNull(36,OracleTypes.NULL);
            else
                statement.setString(36,datos.getMoneda());
//            System.out.println("getMoneda: "+datos.getMoneda());
            statement.setString(37,"16");
//            System.out.println("37: 16 ");
            statement.setString(38,"");
//            System.out.println("38:  ");
            statement.setString(39,"S");
//            System.out.println("39: S ");

            if(numRef==null)
                statement.setNull(40,OracleTypes.NULL);
            else
                statement.setString(40,numRef);
//            System.out.println("numRef: "+ numRef);
            statement.registerOutParameter(41, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(41);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String saveToOtherBankJS_BOFA (String numRef) throws Exception {
        final String origen = "TransfersIo.saveToOtherBankJS_BOFA";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin TransferenciaHandler.trans_inserta_bofa_pr(?,?); end;");

            if(numRef==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,numRef);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String EmailsInternos (String cuenta) throws Exception {
        final String origen = "TransfersIo.EmailsInternos";
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

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
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

        return (correos);
    }

    public String EmailsAprobadores (String contrato, String tipo) throws Exception {
        final String origen = "TransfersIo.EmailsAprobadores";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String correos = new String();
        long time = 0;

        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
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

        return (correos);
    }




    public String emailsClientesPrincipales (String contrato) throws Exception {
        final String origen = "TransfersIo.emailsClientesPrincipales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String correos="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(5));
//                correos= result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }


    public String emailsaprobador (String contrato) throws Exception {
        final String origen = "TransfersIo.emailsClientesPrincipales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String correos="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(5));
//                correos= result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }


    public String emailsRechazo (String referencia) throws Exception {
        final String origen = "TransfersIo.emailsRechazo";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String correos="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.correos_rechazo(?,?,?); end;");



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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(correos)) {
                    correos += ",";
                }
                correos += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }


    public String emailClientePrincipalCuenta (String cuenta) throws Exception {
        final String origen = "TransfersIo.emailClientePrincipalCuenta";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String correos="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_correo_cliente_cuenta(?,?,?); end;");



            if(cuenta==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,cuenta.trim());

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){

                   correos = correos +  NullFormatter.formatBlank(result.getString(1))+",";

                 /*  if (NullFormatter.isBlank(correos)) {
                       correos += NullFormatter.formatBlank(result.getString(1));
                   } */

//                correos= result.getString(1);
            }

            if (correos.trim().length()>0){
                correos=correos.substring(0, correos.length()-1);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (correos);
    }




    public String numerosReferencia (String referencia) throws Exception {
        final String origen = "TransfersIo.numerosReferencia";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String numeroReferencia="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.cargar_numero_referencia(?,?,?); end;");


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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){
                if (!NullFormatter.isBlank(numeroReferencia)) {
                    numeroReferencia += ",";
                }
                numeroReferencia += NullFormatter.formatBlank(result.getString(1));
//                correos= result.getString(1);
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (numeroReferencia);
    }

    public String numeroReferencia (String referencia) throws Exception {
        final String origen = "TransfersIo.numerosReferencia";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta="";
        String numeroReferencia="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ FirmasConjuntasIo.class+" | "+origen);

            time = System.currentTimeMillis ();
            connection=getConnection();
            connection.setAutoCommit(false);

            statement  = connection.prepareCall ("begin TransferenciaHandler.cargar_numero_referencia(?,?,?); end;");


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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            while(result.next()){

                numeroReferencia =result.getString(1);
//
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ FirmasConjuntasIo.class+" | "+origen);


        }
        catch (Exception ex) {
            connection.rollback();
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (numeroReferencia);
    }

    public TableOd  buscarBanco (List<String> parametros, String idioma) throws Exception {
        final String origen = "TransfersIo.buscarBanco";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_BankCodeBuscar_pr(?,?,?,?,?,?,?,?); end;");

//            vbt_BankCodeBuscar_pr (p_strCmbTipoCodBanco           IN VARCHAR2,
//                    p_strTxtCodBanco                 IN VARCHAR2,
//                    p_strTxtNombreBanco             IN VARCHAR2,
//                    p_strCmbPais                      IN VARCHAR2,
//                    p_strCodPais                      IN VARCHAR2,
//                    vbt_BankCodeBuscar  OUT SYS_REFCURSOR,
//                    p_resultado OUT VARCHAR2,
//                    p_salida OUT VARCHAR2)
            //p_strCmbTipoCodBanco
            if(parametros.get(0) == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));
            //p_strTxtCodBanco
            if(parametros.get(1) == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));
            //p_strTxtNombreBanco
            if (parametros.get(2)== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));
            //p_strCmbPais
            if (parametros.get(3)== null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.get(3));
            //p_strCodPaís
            if (parametros.get(4)== null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,parametros.get(4));

            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);
            String sql = statement.getString(8);

                if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);

            List<List<String>> bodys=new ArrayList();
//            I.CD_TITLE NOMBRE
//            ,I.STATE DIR_L3
//            ,I.CITY DIR_L1
//            ,I.COUNTRY Pais
//            ,I.COUNTRY_CODE COD_Pais ';
//
//            if p_strCmbTipoCodBanco ='ABA' then
//            SQLSTRING := SQLSTRING || ' ,I.ROUTNUM ';
//            elsif p_strCmbTipoCodBanco ='SWIFT' then
//            SQLSTRING := SQLSTRING || ' ,I.BIC ';
//            elsif p_strCmbTipoCodBanco ='CHIPS' then
//            SQLSTRING := SQLSTRING || ' ,I.CHIPS_UID ';
//            else
//            SQLSTRING := SQLSTRING || ' ,I.ROUTNUM  ';
//            end if;

            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add(NullFormatter.formatHtmlBlank("<b><a id='"+result.getString(6)+"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(3))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(5))+"' onclick='cargarBanco(this.id)' style='cursor: pointer'>"+result.getString(6)+"</a><b>"));
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add("");
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));

                bodys.add(body);
//
            }
            String propertie = new String();

            if(idioma.equalsIgnoreCase("_ve_es")){
                propertie = "Transferencias"+idioma;
            }else
                propertie = "Transferencias"+idioma;

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle(propertie).getString("TAGCodigoBanco"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGBankName"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGDireccion1"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGDireccion2"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGDireccion3"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGPais"));



            listaTransferencias=new TableOd();

            listaTransferencias= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaTransferencias);
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "TransfersIo.GuardarLog";
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

    public String GuardarLogFC (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7,String parametro8) throws Exception {
        final String origen = "TransfersIo.GuardarLogFC";
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

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }
}

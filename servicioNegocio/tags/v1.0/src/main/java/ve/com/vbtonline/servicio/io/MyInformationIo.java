package ve.com.vbtonline.servicio.io;

import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.DetalleCuentaEdoCtaOd;
import ve.com.vbtonline.servicio.od.MyInformationOd;
import ve.com.vbtonline.servicio.od.TableOd;
import ve.com.vbtonline.servicio.od.TableOdDetallesOd;
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
public class MyInformationIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(MyInformationIo.class);

    /** Constructor de la clase
     */
    public MyInformationIo() {
    }


    public MyInformationOd Cargar (String transaccionId, MyInformationOd miod) throws Exception {
        final String origen = "MyInformationIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        MyInformationOd myInformationOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            myInformationOd=new MyInformationOd();
            myInformationOd.setId(miod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (myInformationOd);
    }

    public TableOd consultarTelefonos (List<String> parametros, String idioma) throws Exception {
        final String origen = "MyInformationIo.consultarTelefonos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd info;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_consultarTelefono_pr(?,?,?,?,?,?,?,?); end;");

//          String idioma = parametros.get(1);

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }
            statement.setString(2,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGHabitacion"));
            statement.setString(3,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCelular"));
            statement.setString(4,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGOficina"));
            statement.setString(5,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGFax"));
            statement.setString(6,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGOtros"));



            statement.registerOutParameter(7, OracleTypes.CURSOR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (7);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGTipo"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCodigoArea"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGTelefono"));

            info=new TableOd();

            info= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (info);
    }

    public TableOd consultarDirecciones (List<String> parametros, String idioma) throws Exception {
        final String origen = "MyInformationIo.consultarDirecciones";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd info;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_consultarDirecciones_pr(?,?,?,?,?,?,?,?,?,?); end;");
//

//          String idioma = parametros.get(1);

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }
            statement.setString(2,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGApartadoPostal"));
            statement.setString(3, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGHabitacion"));
            statement.setString(4, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGOficina"));
            statement.setString(5, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGSi"));
            statement.setString(6,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGOtros"));
            statement.setString(7,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGNo"));
            statement.setString(8,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGEmail"));



            statement.registerOutParameter(9, OracleTypes.CURSOR);
            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(10);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (9);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add((!NullFormatter.isBlank(result.getString(2)) && (result.getString(2)).equals("Email"))?NullFormatter.formatBlank(result.getString(1).toLowerCase()): NullFormatter.formatBlank(result.getString(1)));

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGTipo"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGEsDeEnvio"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGDireccion"));

            info=new TableOd();

            info= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (info);
    }

    public TableOd consultarDocumentos (List<String> parametros, String idioma) throws Exception {
        final String origen = "MyInformationIo.consultarDocumentos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd info;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_consultarDocumentos_pr(?,?,?); end;");
//
//          idioma = parametros.get(1);
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add(NullFormatter.formatBlank(result.getString(4)));
                body.add(NullFormatter.formatBlank(result.getString(5)));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();


            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGNumero"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGDescripcion"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGVencimiento"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGUbicacion"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGAutoridad"));

            info=new TableOd();

            info= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (info);
    }

    public TableOd consultarRepresentantes (List<String> parametros, String idioma) throws Exception {
        final String origen = "MyInformationIo.consultarRepresentantes";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd info;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_consultarRepresentantes_pr(?,?,?,?,?,?,?,?); end;");
//            SELECT   INITCAP(NA) NOMBRE, " // 0
//                    + "          DECODE(CONCIRIF,'0',PRECIRIF || '-' || CIRIF, PRECIRIF || '-' || CIRIF || '-' || CONCIRIF) CIRIF, " // 1
//                    + "          DECODE(TIPOREP,'Apoderado','" + pageConfig.getTagFieldValue("TAGApoderado") +
// "','Representante','" + pageConfig.getTagFieldValue("TAGRepresentante") + "','" + pageConfig.getTagFieldValue("TAGOtros") + "')
// TIPO, " // 2
//                    + "          DECODE(STATREP,'Activo','" + pageConfig.getTagFieldValue("TAGActivo") + "','Inactivo','
// " + pageConfig.getTagFieldValue("TAGInactivo") + "','" + pageConfig.getTagFieldValue("TAGOtros") + "') STATUS, " // 3
//                    + "          TO_CHAR(FECHVEN,'dd/mm/yyyy') VENCIMIENTO " // 4
//                    + " FROM     PRODUCCION.REPRESENTANTES "
//                    + " WHERE    CODPERCLI='"+ strCliente +"' "
//                    + " AND      UPPER(STATREP) = 'ACTIVO'";
//

            //          idioma = parametros.get(1);

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }
            statement.setString(2,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGApoderado"));
            statement.setString(3, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGRepresentante"));
            statement.setString(4, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGOtros"));
            statement.setString(5, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGActivo"));
            statement.setString(6,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGInactivo"));



            statement.registerOutParameter(7, OracleTypes.CURSOR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (7);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add(NullFormatter.formatBlank(result.getString(4)));
                body.add(NullFormatter.formatBlank(result.getString(5)));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGNombre"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCIRIF"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGTipo"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGStatus"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGVencimiento"));

            info=new TableOd();

            info= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (info);
    }

    public TableOd consultarContactos (List<String> parametros, String idioma) throws Exception {
        final String origen = "MyInformationIo.consultarContactos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd info;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_consultarContactos_pr(?,?,?); end;");
//
//          idioma = parametros.get(1);
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();

            while (result.next()){
                body=new ArrayList<String>();
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add(NullFormatter.formatBlank(result.getString(4)));
                body.add(NullFormatter.formatBlank(result.getString(5)));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();


            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGNombre"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCIRIF"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGGerencia"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCargo"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGTelefono"));


            info=new TableOd();

            info= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (info);
    }

    public TableOdDetallesOd consultarCarteras (List<String> parametros, String idioma) throws Exception {
        final String origen = "MyInformationIo.consultarCarteras";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd info;
        TableOdDetallesOd infodetalle;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_consultarCarteras_pr(?,?,?,?,?,?,?,?,?); end;");
//
//            " SELECT DISTINCT CLI.CODCAR, "                         // 0
//                    + "        DECODE(CLI.MODCAR,0,'" + pageConfig.getTagFieldValue("TAGCompartida") + "',1,'" + pageConfig.getTagFieldValue("TAGIndividual") + "'), " // 1
//                    + "        DECODE(UPPER(CLI.STATCAR),'A','" + pageConfig.getTagFieldValue("TAGActiva") + "','I','" + pageConfig.getTagFieldValue("TAGInactiva") + "'), " // 2
//                    + "        DECODE(CLI.CONCIRIF,'0',CLI.PRECIRIF || '-' || CLI.CIRIF, CLI.PRECIRIF || '-' || CLI.CIRIF || '-' || CLI.CONCIRIF) CIRIF, " // 3
//                    + "        INITCAP(CLI.NA) NOMBRE, "             // 4
//                    + "        INITCAP(CLI.REFERIDO) ASESOR, "       // 5
//                    + "        INITCAP(CLI.RESPONSABLE) EJECUTIVO, " // 6
//                    + "        INITCAP(CLI.DIRENVIO) DIRECCION "     // 7
//                    // + "        LOWER(CLI.EMAIL_EJECUTIVO) EMAIL_EJECUTIVO "  // 8
//                    + "   FROM PRODUCCION.CLIENTES CLI"
//                    + "  WHERE CLI.CODPERCLI = '"+ strCliente +"' "
//                    + "    AND CLI.CODCAR IN (" + strCarteras + ")";
            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }
            statement.setString(2,ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCompartida"));
            statement.setString(3, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGIndividual"));
            statement.setString(4, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGInactiva"));
            statement.setString(5, ResourceBundle.getBundle("MisDatos" + idioma).getString("TAGActiva"));
//
//          idioma = parametros.get(1);

            if(parametros.get(1)==null){
                statement.setNull(6, OracleTypes.NULL);
            }else{
                statement.setString(6, parametros.get(1));
            }


            statement.registerOutParameter(7, OracleTypes.CURSOR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);
            statement.registerOutParameter(9, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);
           String sql = statement.getString(9);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ MyInformationIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (7);
            List<List<String>> bodys=new ArrayList();

            List<String> body=new ArrayList<String>();
            List<String> detalleCliente = new ArrayList<String>();
            while (result.next()){
                body=new ArrayList<String>();
                if(detalleCliente.size()==0){
                   detalleCliente.add(NullFormatter.formatBlank(result.getString(5)));
                   detalleCliente.add(NullFormatter.formatBlank(result.getString(4)));
                   detalleCliente.add(NullFormatter.formatBlank(result.getString(8)));
                }

                body.add(NullFormatter.formatBlank(result.getString(1)));
                body.add(NullFormatter.formatBlank(result.getString(2)));
                body.add(NullFormatter.formatBlank(result.getString(3)));
                body.add(NullFormatter.formatBlank(result.getString(5)));
                body.add(NullFormatter.formatBlank(result.getString(6)));
                body.add(NullFormatter.formatBlank(result.getString(7)));

                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();


            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGCodigoCartera"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGModalidad"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGStatus"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGClientePrincipal"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGAsesor"));
            header.add(ResourceBundle.getBundle("MisDatos"+idioma).getString("TAGEjecutivo"));


            info=new TableOd();
            infodetalle = new TableOdDetallesOd();

            info= TransformTable.getTable(header, bodys);

            infodetalle.setTabla(info);
            infodetalle.setDetalles(detalleCliente);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ MyInformationIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (infodetalle);
    }

    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "MyInformationIo.GuardarLog";
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

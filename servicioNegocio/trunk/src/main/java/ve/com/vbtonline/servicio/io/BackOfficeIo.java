package ve.com.vbtonline.servicio.io;

//import ve.com.vbtonline.servicio.util.MailManager365;
import ve.com.vbtonline.servicio.util.MailManager365;
import com.venezolano.util.text.CurrencyFormatter;
import com.venezolano.util.text.NullFormatter;
import comsms.vbt.servicio.ServicioVbt;
import oracle.jdbc.driver.OracleTypes;
import org.apache.log4j.Logger;
import ve.com.vbtonline.comun.conexiondao.comundao;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.GeneratePassword;
import ve.com.vbtonline.servicio.util.SimpleCrypt;
import ve.com.vbtonline.servicio.util.TransformTable;
import ve.com.vbtonline.servicio.util.validacionUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Mserrano
 * Date: 11/07/12
 * Time: 04:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class BackOfficeIo extends comundao implements Serializable {
    private static final Logger logger = Logger.getLogger(BackOfficeIo.class);
    private LoggerIo loggerIo;
    /** Constructor de la clase
     */
    public BackOfficeIo () {
    }


    public BackOfficeOd Cargar (String transaccionId, BackOfficeOd bod) throws Exception {
        final String origen = "BackOfficeIo.Cargar";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        BackOfficeOd backOfficeOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();
            backOfficeOd=new BackOfficeOd();
            backOfficeOd.setId(bod.getId());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {


            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (backOfficeOd);
    }

    public CtaOpcOd opcionesGrupo (String transaccionId, CtaOpcOd ctod, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.opcionesGrupo";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        CtaOpcOd ctaOpcOd = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            ctaOpcOd=new CtaOpcOd();
            ctaOpcOd.setDesopc("prueba");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (ctaOpcOd);
    }

    public TableOd consultarGrupos (VBTUsersOd usuarioSesion) throws Exception {       //VBTUsersOd usuarioSesion
        final String origen = "BackOfficeIo.consultarGrupos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        GruposOd gruposOd = null;
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaGrupos = null;
        List<GruposOd> listaGrupos2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_gruposopctot_pr(?,?,?); end;");


            statement.setNull(1, OracleTypes.NULL);
            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            listaGrupos2 = new ArrayList<GruposOd>();
            while (result.next()){

                gruposOd=new GruposOd();
                gruposOd.setIdGrupo(result.getString(1));
                gruposOd.setGrupo(result.getString(2));
                gruposOd.setGrupoCategoria(result.getString(3));
                gruposOd.setNumUsuGrp(result.getInt(4));
                gruposOd.setOpcionAsociada(result.getInt(5));
                listaGrupos2.add(gruposOd);
            }

            List<String> header=new ArrayList<String>();
            header.add("Nombre Grupo");
            header.add("N° Usuarios en el Grupo");
            header.add("N° Opciones Asociadas");

            List<List<String>> bodys=new ArrayList();
            String valor = new String();
            for(int i=0; i<listaGrupos2.size();i++){
                List<String> body=new ArrayList<String>();
                valor = new String();
                valor = listaGrupos2.get(i).getGrupo();
                body.add("<b><a style='cursor:pointer' id='"+listaGrupos2.get(i).getGrupoCategoria() +"|"+listaGrupos2.get(i).getIdGrupo()+"!"+listaGrupos2.get(i).getGrupo()+
                        "' onclick='seleccionarGrupoOpcion(this.id)' value='"+listaGrupos2.get(i).getIdGrupo()+"'>"+listaGrupos2.get(i).getGrupo()+"</a><b>");
//                         ResourceBundle.getBundle("Comun2").getString(listaGrupos2.get(i).getGrupo())+"<p></a>");
//                        ResourceBundle.getBundle("package").getString("username")+"<p></a>");
                body.add(listaGrupos2.get(i).getNumUsuGrp().toString());
                body.add(listaGrupos2.get(i).getOpcionAsociada().toString());
                bodys.add(body);
            }

            listaGrupos=new TableOd();

            listaGrupos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaGrupos);
    }


    public TableOd consultarRolesFC (VBTUsersOd usuarioSesion) throws Exception {       //VBTUsersOd usuarioSesion
        final String origen = "BackOfficeIo.consultarRolesFC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        GruposOd gruposOd = null;
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaRoles = null;
        List<GruposOd> listaRoles2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_roles_fc_pr(?,?); end;");


            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            listaRoles2 = new ArrayList<GruposOd>();
            List<List<String>> bodys=new ArrayList();
            while (result.next()){
                List<String> body=new ArrayList<String>();
                body.add("<b><a style='cursor:pointer' id='"+result.getString(1) +"|"+result.getString(2)+
                        "' onclick='seleccionarRolOpcion(this.id)' value='"+result.getString(1)+"'>"+result.getString(2)+"</a><b>");

                body.add(result.getString(6));
                body.add(result.getString(7));
                bodys.add(body);

            }

            List<String> header=new ArrayList<String>();
            header.add("Nombre Rol");
            header.add("N° Usuarios con el Rol");
            header.add("N° Opciones Asociadas");



            listaRoles=new TableOd();

            listaRoles= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaRoles);
    }





    /**
     *      TAG:            consultarGrupoOpcionesSistemaEsquema
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/10
     *      DESCRIPCIÓN:
     *
     * */
    public TableOd consultarGrupoOpcionesSistemaEsquema (String codigoGrupo, VBTUsersOd usuarioSesion) throws Exception {

        final String origen = "BackOfficeIo.consultarGrupoOpcionesSistemaEsquema";
        long time = 0;
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultCategory = null;
        ResultSet resultOption = null;
        TableOd listaOpciones = null;
        List<String> tableHeader = null;
        List<String> tableCells = null;
        ArrayList<List<String>> tableRows = null;
        String rowDetail = "";
        String rowSubdetail = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen);

            time = System.currentTimeMillis ();

            connection = getConnection();

            statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_LISTAR_CATOPC_PADRES(?,?,?,?); END;");

            if(codigoGrupo==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, codigoGrupo);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            if (!"SUCCESS".equalsIgnoreCase(statement.getString(4))) {
                logger.error(statement.getString(4),new Exception (statement.getString(3) + " - " + statement.getString(4)));
                throw (new Exception (statement.getString(3) + " - " + statement.getString(4)));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen + " | 0 respuesta de bd exitosa");

            resultCategory = (ResultSet) statement.getObject(2);

            listaOpciones = new TableOd();
            tableHeader = new ArrayList();
            tableHeader.add("");
            tableHeader.add("<b>NOMBRE OPCIONES</b>");
            tableHeader.add("<b>CONSULTAR</b>");
            tableHeader.add("<b>AGREGAR</b>");
            tableHeader.add("<b>EDITAR</b>");
            tableHeader.add("<b>ELIMINAR</b>");
            tableHeader.add("<b>EDITAR ESTATUS</b>");
            tableHeader.add("<b>REINICIAR CLAVE</b>");
            tableHeader.add("<b>APROBAR</b>");
            tableHeader.add("<b>LIBERAR</b>");
            tableRows = new ArrayList();

            while (resultCategory.next()) {

                tableCells = new ArrayList();

                statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_LISTAR_CATOPC(?,?,?,?,?); END;");

                statement.setString(1, codigoGrupo);
                statement.setString(2, resultCategory.getString("CODOPC"));
                statement.registerOutParameter(3, OracleTypes.CURSOR);
                statement.registerOutParameter(4, OracleTypes.VARCHAR);
                statement.registerOutParameter(5, OracleTypes.VARCHAR);

                statement.execute();

                resultOption = (ResultSet) statement.getObject(3);

                if (!resultOption.next()) {

//                    rowDetail = "<img id='img" + resultCategory.getString("CODOPC") + "' onclick='abrirDetalle(\"" +
//                    resultCategory.getString("CODOPC") + "\")' " +
//                    "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
//                    "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />";
//                    rowDetail += "<input type='hidden' id='" + resultCategory.getString("CODOPC") + "' value='' />";
                    tableCells.add("");

                    rowDetail = "<b>" +
                            ResourceBundle.getBundle("Comun2_ve_es").getString(resultCategory.getString("DESOPC")) +
                            "</b>";

                    tableCells.add(rowDetail);

                    // CONSULTAR
                    rowDetail = "<b><img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // AGREGAR
                    rowDetail = "<b><img id='2_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[2])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // EDITAR
                    rowDetail = "<b><img id='3_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[3])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // ELIMINAR
                    rowDetail = "<b><img id='4_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[4])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // EDITAR ESTATUS
                    rowDetail = "<b><img id='5_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[5])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // REINICIAR CLAVE
                    rowDetail = "<b><img id='6_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[6])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // APROBAR
                    rowDetail = "<b><img id='7_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[7])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // LIBERAR
                    rowDetail = "<b><img id='8_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[8])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);
                    tableRows.add(tableCells);
                    tableCells = new ArrayList();

                } else{

                    rowDetail = "<img id='img" + resultCategory.getString("CODOPC") + "' onclick='abrirDetalle(\"" +
                            resultCategory.getString("CODOPC") + "\")' " +
                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />";
                    rowDetail += "<input type='hidden' id='" + resultCategory.getString("CODOPC") + "' value='' />";
                    tableCells.add(rowDetail);

                    rowDetail = "<b>" +
                            ResourceBundle.getBundle("Comun2_ve_es").getString(resultCategory.getString("DESOPC")) +
                            "</b>";

                    tableCells.add(rowDetail);

                    // CONSULTAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "<img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoGrupo +
                            "' onclick='guardarOpcion(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " />";


                    tableCells.add(rowDetail);

                    // AGREGAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[2])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // EDITAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[3])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // ELIMINAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[4])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // EDITAR ESTATUS
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[5])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // REINICIAR CLAVE
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[6])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // APROBAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[7])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // LIBERAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[8])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);
                    tableRows.add(tableCells);
                    tableCells = new ArrayList<String>();

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen + " | 0 respuesta de bd exitosa");

                    do {

                        tableCells.add("<input type='hidden' id='" + resultOption.getString("CODOPC") + "' value='" + resultCategory.getString("CODOPC") + "' /><script type='text/javascript'>ocultarFila('" + resultOption.getString("CODOPC") +",');</script>");

                        rowSubdetail = ResourceBundle.getBundle("Comun2_ve_es").getString(resultOption.getString("DESOPC").replaceAll(" ","_"));

                        tableCells.add(rowSubdetail);

                        // CONSULTAR
                        rowSubdetail = "<img id='1_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // AGREGAR
                        rowSubdetail = "<img id='2_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[2])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // EDITAR
                        rowSubdetail = "<img id='3_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[3])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // ELIMINAR
                        rowSubdetail = "<img id='4_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[4])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // EDITAR ESTATUS
                        rowSubdetail = "<img id='5_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[5])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // REINICIAR CLAVE
                        rowSubdetail = "<img id='6_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[6])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // APROBAR
                        rowSubdetail = "<img id='7_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[7])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // LIBERAR
                        rowSubdetail = "<img id='8_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoGrupo +
                                "' onclick='guardarOpcion(this.id)' class='check' ";

                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[8])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);
                        tableRows.add(tableCells);
                        tableCells = new ArrayList<String>();

                    } while (resultOption.next());

                }

            }

            listaOpciones = new TableOd();
            listaOpciones = TransformTable.getTable(tableHeader, tableRows);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            closeJdbcObjects (null, null, resultOption);
            closeJdbcObjects (connection, statement, resultCategory);
        }

        return (listaOpciones);
    }
    /** FIN consultarGrupoOpcionesSistemaEsquema */


    public TableOd consultarRolOpcionesSistemaEsquema (String codigoRol, VBTUsersOd usuarioSesion) throws Exception {

        final String origen = "BackOfficeIo.consultarRolOpcionesSistemaEsquema";
        long time = 0;
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultCategory = null;
        ResultSet resultOption = null;
        TableOd listaOpciones = null;
        List<String> tableHeader = null;
        List<String> tableCells = null;
        ArrayList<List<String>> tableRows = null;
        String rowDetail = "";
        String rowSubdetail = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen);

            time = System.currentTimeMillis ();

            connection = getConnection();

            statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_CATOPC_PADRES_ROLES(?,?,?,?); END;");

            if(codigoRol==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, codigoRol);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            if (!"SUCCESS".equalsIgnoreCase(statement.getString(4))) {
                logger.error(statement.getString(4),new Exception (statement.getString(3) + " - " + statement.getString(4)));
                throw (new Exception (statement.getString(3) + " - " + statement.getString(4)));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen + " | 0 respuesta de bd exitosa");

            resultCategory = (ResultSet) statement.getObject(2);

            listaOpciones = new TableOd();
            tableHeader = new ArrayList();
            tableHeader.add("");
            tableHeader.add("<b>NOMBRE OPCIONES</b>");
            tableHeader.add("<b>ASIGNADO</b>");
            tableRows = new ArrayList();

            while (resultCategory.next()) {

                tableCells = new ArrayList();

                statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_LISTAR_CATOPC_ROLES(?,?,?,?,?); END;");

                statement.setString(1, codigoRol);
                statement.setString(2, resultCategory.getString("CODOPC"));
                statement.registerOutParameter(3, OracleTypes.CURSOR);
                statement.registerOutParameter(4, OracleTypes.VARCHAR);
                statement.registerOutParameter(5, OracleTypes.VARCHAR);

                statement.execute();

                resultOption = (ResultSet) statement.getObject(3);

                if (!resultOption.next()) {

                    tableCells.add("");

                    rowDetail = "<b>" +
                            ResourceBundle.getBundle("Comun2_ve_es").getString(resultCategory.getString("DESOPC")) +
                            "</b>";

                    tableCells.add(rowDetail);

                    // CONSULTAR
                    rowDetail = "<b><img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoRol +
                            "' onclick='guardarOpcionRol(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    rowDetail = "<img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoRol +
                            "' onclick='guardarOpcionRol(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " />";

                    tableRows.add(tableCells);
                    tableCells = new ArrayList();

                } else{

                    rowDetail = "<img id='img" + resultCategory.getString("CODOPC") + "' onclick='abrirDetalle(\"" +
                            resultCategory.getString("CODOPC") + "\")' " +
                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />";
                    rowDetail += "<input type='hidden' id='" + resultCategory.getString("CODOPC") + "' value='' />";
                    tableCells.add(rowDetail);

                    rowDetail = "<b>" +
                            ResourceBundle.getBundle("Comun2_ve_es").getString(resultCategory.getString("DESOPC")) +
                            "</b>";

                    tableCells.add(rowDetail);

                    rowDetail = "<img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + codigoRol +
                            "' onclick='guardarOpcionRol(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " />";
                    tableCells.add(rowDetail);

                    tableRows.add(tableCells);
                    tableCells = new ArrayList<String>();

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen + " | 0 respuesta de bd exitosa");

                    do {

                        tableCells.add("<input type='hidden' id='" + resultOption.getString("CODOPC") + "' value='" + resultCategory.getString("CODOPC") + "' /><script type='text/javascript'>ocultarFila('" + resultOption.getString("CODOPC") +",');</script>");

                        rowSubdetail = ResourceBundle.getBundle("Comun2_ve_es").getString(resultOption.getString("DESOPC").replaceAll(" ","_"));

                        tableCells.add(rowSubdetail);

                        // CONSULTAR
                        rowSubdetail = "<img id='1_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + codigoRol +
                                "' onclick='guardarOpcionRol(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        tableRows.add(tableCells);
                        tableCells = new ArrayList<String>();

                    } while (resultOption.next());

                }

            }

            listaOpciones = new TableOd();
            listaOpciones = TransformTable.getTable(tableHeader, tableRows);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            closeJdbcObjects (null, null, resultOption);
            closeJdbcObjects (connection, statement, resultCategory);
        }

        return (listaOpciones);
    }





    /**
     *      TAG:            actualizarGrupoOpcionesSistemaEsquema
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/11/10
     *      DESCRIPCIÓN:
     *
     * */
    public String actualizarGrupoOpcionesSistemaEsquema(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.actualizarGrupoOpcionesSistemaEsquema";
        Connection connection = null;
        CallableStatement statement = null;
        String result = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            connection=getConnection();

            statement  = connection.prepareCall("BEGIN BACKOFFICE.BAC_PRC_ACTUALIZAR_CATOPC(?,?,?,?,?,?); END;");

            statement.setString(1, acciones.get(1));
            statement.setString(2, acciones.get(2));
            statement.setString(3, acciones.get(3));

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.setString(6, usuarioSesion.getLogin());

            statement.execute ();

            result = statement.getString(5);

            if (!result.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                throw (new Exception (result,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            connection.commit();

        } catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        } finally {
            closeJdbcObjects(connection, statement, null);
        }

        return result;
    }
    /** FIN actualizarGrupoOpcionesSistemaEsquema */

    public String actualizarRolOpcionesSistemaEsquema(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.actualizaRolOpcionesSistemaEsquema";
        Connection connection = null;
        CallableStatement statement = null;
        String result = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            connection=getConnection();

            statement  = connection.prepareCall("BEGIN BACKOFFICE.BAC_PRC_ACTUALIZAR_CATOPC_ROL(?,?,?,?,?,?); END;");

            statement.setString(1, acciones.get(1));
            statement.setString(2, acciones.get(2));
            statement.setString(3, acciones.get(3));

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.setString(6, usuarioSesion.getLogin());

            statement.execute ();

            result = statement.getString(5);

            if (!result.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                throw (new Exception (result,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            connection.commit();

        } catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        } finally {
            closeJdbcObjects(connection, statement, null);
        }

        return result;
    }

    /**
     *      TAG:            actualizarOpcionesEspecialesSistema
     *      AUTOR:          RRANGEL
     *      FECHA:          2014/12/09
     *      DESCRIPCIÓN:
     *
     * */
    public String actualizarOpcionesEspecialesSistema(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.actualizarOpcionesEspecialesSistema";
        Connection connection = null;
        CallableStatement statement = null;
        String result = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            connection=getConnection();

            statement  = connection.prepareCall("BEGIN BACKOFFICE.BAC_PRC_ACTUALIZAR_OPC_ESP(?,?,?,?,?,?); END;");

            statement.setString(1, acciones.get(0));
            statement.setString(2, acciones.get(1));
            statement.setString(3, acciones.get(2));
            statement.setString(4, usuarioSesion.getLogin());

            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);

            statement.execute ();

            result = statement.getString(6);

            if (!result.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                throw (new Exception (result,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            connection.commit();

        } catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        } finally {
            closeJdbcObjects(connection, statement, null);
        }

        return result;
    }
    /** FIN actualizarOpcionesEspecialesSistema */

    public TableOd consultarGrupoOpcionesSistema (String categoria, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarGrupoOpcionesSistema";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo;
        String mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        OpcionesOd opcionesOd = null;
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaOpciones = null;
        List<OpcionesOd> listaOpciones2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall("begin BACKOFFICE.bac_gruposopcsis_pr(?,?,?,?); end;");

            statement.setString(1, "ONLINE");
            if(categoria==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(2, categoria);
            }

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);

            listaOpciones2 = new ArrayList<OpcionesOd>();
            while (result.next()){

                opcionesOd=new OpcionesOd();
                opcionesOd.setCodigoPadre(result.getString(1));
                opcionesOd.setOpcionNivel(result.getInt(2));
                opcionesOd.setOpcionOrden(result.getInt(3));
                opcionesOd.setOpcionDescripcion(result.getString(4));
                opcionesOd.setOpcionCodigo(result.getString(5));
                listaOpciones2.add(opcionesOd);
            }

            List<String> header=new ArrayList<String>();
            header.add("Nombre Opciones");


            List<List<String>> bodys=new ArrayList();
            String descripcion=new String();
            String miPadre= new String();
            String NombrePadre = new String();
            int marca = 0;
            for(int i=0; i<listaOpciones2.size();i++){
                List<String> body=new ArrayList<String>();
                descripcion = listaOpciones2.get(i).getOpcionDescripcion().replaceAll(" ","_");
                if(i+1<listaOpciones2.size())
                    if(listaOpciones2.get(i).getOpcionCodigo().equalsIgnoreCase(listaOpciones2.get(i+1).getCodigoPadre())){
                        NombrePadre = ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion);
                        marca = 1;
//                        body.add("<b><a id='"+listaOpciones2.get(i).getOpcionCodigo() +"' value='"+
//                                listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                    }else{
                        miPadre =  listaOpciones2.get(i).getCodigoPadre();
                        marca = 0;
                        if(!NombrePadre.equalsIgnoreCase("")) {
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarGrupoSistemaOpcion(this.id)' value='"+
                                    listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                        } else
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarGrupoSistemaOpcion(this.id)' value='"+
                                    listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                    }
                else{
                    //                    if(miPadre.equalsIgnoreCase(listaOpciones2.get(i).getCodigoPadre()))
                    marca = 0;
                    if(!NombrePadre.equalsIgnoreCase(""))
                        body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarGrupoSistemaOpcion(this.id)' value='"+
                                listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                    else
                        body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarGrupoSistemaOpcion(this.id)' value='"+
                                listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                }
                if(i+1<listaOpciones2.size())
                    if(!listaOpciones2.get(i).getCodigoPadre().equalsIgnoreCase(listaOpciones2.get(i+1).getCodigoPadre())){
                        NombrePadre = "";
                    }
                if(marca == 0)
                    bodys.add(body);
            }

            listaOpciones=new TableOd();

            listaOpciones= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public TableOd consultarOpcionesSistema (String strCmbTipoUsuarioEditar, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarOpcionesSistema";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        OpcionesOd opcionesOd = null;
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaOpciones = null;
        List<OpcionesOd> listaOpciones2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuopcionsis_pr(?,?,?,?); end;");

//            ROCEDURE bac_usuopcionsis_pr (p_strCmbTipoUsuarioEditar IN VARCHAR2,
//            p_strCmbcodsys            IN VARCHAR2,
//                    p_bac_usuopcionsis OUT bac_usuopcionsis,
//            p_resultado OUT VARCHAR2) AS

//            (!"2".equals(strCmbTipoUsuarioEditar) ? "B" : "C")
            String tipoUsuario = (!"2".equals(strCmbTipoUsuarioEditar) ? "B" : "C");

            statement.setString(1, tipoUsuario);
            statement.setString(2, "ONLINE");

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(4);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);

            listaOpciones2 = new ArrayList<OpcionesOd>();
            while (result.next()){
                opcionesOd=new OpcionesOd();
                opcionesOd.setCodigoPadre(result.getString(1));
                opcionesOd.setOpcionNivel(result.getInt(2));
                opcionesOd.setOpcionOrden(result.getInt(3));
                opcionesOd.setOpcionDescripcion(result.getString(4));
                opcionesOd.setOpcionCodigo(result.getString(5));
                listaOpciones2.add(opcionesOd);
            }

            List<String> header=new ArrayList<String>();
            header.add("Nombre Opciones");


            List<List<String>> bodys=new ArrayList();
            String descripcion=new String();
            String miPadre= new String();
            String NombrePadre = new String();
            int marca = 0;
            for(int i=0; i<listaOpciones2.size();i++){
                List<String> body=new ArrayList<String>();
                descripcion = listaOpciones2.get(i).getOpcionDescripcion().replaceAll(" ","_");
                if(i+1<listaOpciones2.size())
                    if(listaOpciones2.get(i).getOpcionCodigo().equalsIgnoreCase(listaOpciones2.get(i+1).getCodigoPadre())){
                        NombrePadre = ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion);
                        marca = 1;
//                        body.add("<b><a id='"+listaOpciones2.get(i).getOpcionCodigo() +"' value='"+
//                                listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                    }else{
                        miPadre =  listaOpciones2.get(i).getCodigoPadre();
                        marca = 0;
                        if(!NombrePadre.equalsIgnoreCase("")){
                            if(tipoUsuario.equalsIgnoreCase("B"))
                                body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioBSistemaOpcion(this.id)' value='"+
                                        listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                            else
                                body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioCSistemaOpcion(this.id)' value='"+
                                        listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                        }
                        else{
                            if(tipoUsuario.equalsIgnoreCase("B"))
                                body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioBSistemaOpcion(this.id)' value='"+
                                        listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                            else
                                body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioCSistemaOpcion(this.id)' value='"+
                                        listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");

                        }

                    }
                else{
                    //                    if(miPadre.equalsIgnoreCase(listaOpciones2.get(i).getCodigoPadre()))
                    marca = 0;
                    if(!NombrePadre.equalsIgnoreCase("")) {
                        if(tipoUsuario.equalsIgnoreCase("B"))
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioBSistemaOpcion(this.id)' value='"+
                                    listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                        else
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioCSistemaOpcion(this.id)' value='"+
                                    listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                    }

                    else{
                        if(tipoUsuario.equalsIgnoreCase("B"))
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioBSistemaOpcion(this.id)' value='"+
                                    listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                        else
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarUsuarioCSistemaOpcion(this.id)' value='"+
                                    listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                    }

                }

//                        listaOpciones2.get(i).getOpcionDescripcion()+"'>"+ResourceBundle.getBundle("Comun2_ve_es").getString(listaOpciones2.get(i).getOpcionDescripcion())+"</a></b>");
                if(marca == 0)
                    bodys.add(body);
            }

            listaOpciones=new TableOd();

            listaOpciones= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public TableOd consultarGrupoOpcPermisos (String codGrupo, String codOpcion, List<String> acciones,VBTUsersOd usuarioSesion ) throws Exception {
        final String origen = "BackOfficeIo.consultarGrupoOpcPermisos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String opcionesOd = null;
        String permisosAcciones=new String();
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaOpciones = null;
        List<String> listaOpciones2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_grpuopcper_pr(?,?,?,?,?,?,?,?,?); end;");

            statement.setNull(1, OracleTypes.NULL);

            statement.setString(1, "ONLINE");

            if(codGrupo==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, codGrupo);
            }
            statement.setString(3, "VBT");
            statement.setString(4, "ONLINE");

            if(codOpcion==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, codOpcion);
            }

            statement.setNull(6, OracleTypes.NULL);
            statement.setNull(7, OracleTypes.NULL);

            statement.registerOutParameter(8, OracleTypes.CURSOR);
            statement.registerOutParameter(9, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(9);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (8);
            while (result.next()){
                permisosAcciones = result.getString(1);
            }

            List<String> header=new ArrayList<String>();
            header.add("Opciones");

            List<List<String>> bodys=new ArrayList();

            Vector acc = new Vector();
            for(int y=0;y<permisosAcciones.length();y++){
                acc.addElement(permisosAcciones.substring(y,y+1));
            }
            //esto en caso de que las opciones no tengan acciones asociadas
            if(permisosAcciones.equalsIgnoreCase("")){
                for(int i=0; i<acciones.size();i++){
                    acc.addElement("0");
                }
            }
//            acc[1]= permisosAcciones.charAt(1);
            for(int i=0; i<acciones.size();i++){
                List<String> body=new ArrayList<String>();
                if(acc.elementAt(i).equals("0"))
                    body.add("<input class='datos verificarSelecccion' type='checkbox' id='" +acciones.get(i).replace(" ","_")+"' value='"+acc.elementAt(i).toString()+"' onclick='cambiarValor(this.id)'><label>"+acciones.get(i)+"</label>");
                else
                    body.add("<input class='datos verificarSelecccion' checked type='checkbox' id='" +acciones.get(i).replace(" ","_")+"' value='"+acc.elementAt(i).toString()+"' onclick='cambiarValor(this.id)' ><label>"+acciones.get(i)+"</label>");
                bodys.add(i,body);
            }

            listaOpciones=new TableOd();

            listaOpciones= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public TableOd consultarOpcPermisos (String usuario, String codOpcion, List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarOpcPermisos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String opcionesOd = null;
        String permisosAcciones=new String();
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaOpciones = null;
        List<String> listaOpciones2;
        String permisosGrupoUsuario = consultarOpcPermisosGrupoUsuario(usuario,codOpcion);

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuOpcionUsuarioInt_pr(?,?,?,?,?,?); end;");

//            bac_usuOpcionUsuarioInt_pr (p_strTxtUsuarioEditar in varchar2,  scaetano
//                    p_strTxtcodcia in varchar2,   VBT
//                    p_strTxtcodsis in varchar2,   ONLINE
//                    p_strTxtcodopc in varchar2,   000000002
//                    p_bac_usuOpcionUsuarioInt OUT bac_usuOpcionUsuarioInt,
//                    p_resultado OUT VARCHAR2)

            if(usuario==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, usuario);
            }

            statement.setString(2, "VBT");
            statement.setString(3, "ONLINE");

            if(codOpcion==null){
                statement.setNull(4, OracleTypes.NULL);
            }else{
                statement.setString(4, codOpcion);
            }

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(6);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);
            while (result.next()){
                permisosAcciones = result.getString(1);
            }

            List<String> header=new ArrayList<String>();
            header.add("Opciones");

            List<List<String>> bodys=new ArrayList();

            Vector acc = new Vector();
            for(int y=0;y<permisosAcciones.length();y++){
                acc.addElement(permisosAcciones.substring(y,y+1));
            }

            Vector accGrupo = new Vector();
            for(int y=0;y<permisosGrupoUsuario.length();y++){
                accGrupo.addElement(permisosGrupoUsuario.substring(y,y+1));
            }
            //esto en caso de que las opciones no tengan acciones asociadas
            if(permisosAcciones.equalsIgnoreCase("")){
//                for(int i=0; i<acciones.size();i++){
//                    acc.addElement("0");
//                }
                acc = accGrupo;
            }
//            acc[1]= permisosAcciones.charAt(1);
            for(int i=0; i<acciones.size();i++){
                List<String> body=new ArrayList<String>();
                if(acc.elementAt(i).equals("0"))
                    body.add("<input class='datos verificarSelecccion' type='checkbox' id='" +acciones.get(i).replace(" ","_")+"' value='"+acc.elementAt(i).toString()+"' onclick='cambiarValorEU(this.id)'><label>"+acciones.get(i)+"</label>");
                else
                    body.add("<input class='datos verificarSelecccion' checked type='checkbox' id='" +acciones.get(i).replace(" ","_")+"' value='"+acc.elementAt(i).toString()+"' onclick='cambiarValorEU(this.id)' ><label>"+acciones.get(i)+"</label>");
                bodys.add(i,body);
            }

            listaOpciones=new TableOd();

            listaOpciones= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public String consultarOpcPermisosGrupoUsuario (String usuario, String codOpcion) throws Exception {
        final String origen = "BackOfficeIo.consultarOpcPermisosGrupoUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String opcionesOd = null;
        String permisosAcciones=new String();
        CtaGrpOd ctaGrpOd = null;

        String respuesta=null;
        TableOd listaOpciones = null;
        List<String> listaOpciones2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuOpcionGrupoInt_pr(?,?,?,?,?,?); end;");

//            bac_usuOpcionUsuarioInt_pr (p_strTxtUsuarioEditar in varchar2,  scaetano
//                    p_strTxtcodcia in varchar2,   VBT
//                    p_strTxtcodsis in varchar2,   ONLINE
//                    p_strTxtcodopc in varchar2,   000000002
//                    p_bac_usuOpcionUsuarioInt OUT bac_usuOpcionUsuarioInt,
//                    p_resultado OUT VARCHAR2)

            if(usuario==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, usuario);
            }

            statement.setString(2, "VBT");
            statement.setString(3, "ONLINE");

            if(codOpcion==null){
                statement.setNull(4, OracleTypes.NULL);
            }else{
                statement.setString(4, codOpcion);
            }

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(6);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);
            while (result.next()){
                permisosAcciones = result.getString(1);
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (permisosAcciones);
    }

    public String consultarCarterasContrato (String codcontrato) throws Exception {
        final String origen = "BackOfficeIo.consultarCarterasContrato";
        Connection connection2 = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String carteras=new String();
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection2=getConnection();

            statement  = connection2.prepareCall ("begin BACKOFFICE.bac_contraeditarcart_pr(?,?,?); end;");

//            bac_usuOpcionUsuarioInt_pr (p_strTxtUsuarioEditar in varchar2,  scaetano
//                    p_strTxtcodcia in varchar2,   VBT
//                    p_strTxtcodsis in varchar2,   ONLINE
//                    p_strTxtcodopc in varchar2,   000000002
//                    p_bac_usuOpcionUsuarioInt OUT bac_usuOpcionUsuarioInt,
//                    p_resultado OUT VARCHAR2)

            if(codcontrato==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, codcontrato);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            int con = 0;
            while (result.next()){
                if(con==0)
                    carteras += result.getString(1);
                else
                    carteras += "  |  "+result.getString(1);

                con++;
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection2, statement, result);
        }

        return (carteras);
    }

    public TableOd consultarUsuariosContratoDetalle (List<String> parametros,String status) throws Exception {
        final String origen = "BackOfficeIo.consultarUsuariosContratoDetalle";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTContratoOd contratosOd = null;
        String respuesta=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();

        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaContratos = null;
        List<VBTContratoOd> listaContratos2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_contratousugrupo_pr(?,?,?,?,?,?,?,?); end;");



            if(parametros.get(0)==null || parametros.get(0)=="")
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if(parametros.get(1)==null || parametros.get(1)=="")
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));

            if(parametros.get(2)==null || parametros.get(2)=="")
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));

            if(parametros.get(3)==null || parametros.get(3)=="")
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.get(3));

            if(parametros.get(4)==null || parametros.get(4)=="")
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,parametros.get(4));

            if(parametros.get(5)==null || parametros.get(5)=="")
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,parametros.get(5));

            statement.registerOutParameter(7, OracleTypes.CURSOR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (7);

            List<List<String>> bodys=new ArrayList();

            while (result.next ()) {
                //Se debn filtar
//                if(!result.getString(12).equalsIgnoreCase("6") && !result.getString(12).equalsIgnoreCase("7") && !result.getString(12).equalsIgnoreCase("8")){


                if (result.getString(12).equalsIgnoreCase("2")){
                    //if (!result.getString(13).equalsIgnoreCase("FC"))){
                    List<String> body=new ArrayList<String>();

                    body.add("<img style='cursor:pointer'  onclick='abrirDetalleTablaUsuario(this)' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(9))+
                            "|"+NullFormatter.formatHtmlBlank(result.getString(2))+
                            "|"+NullFormatter.formatHtmlBlank(result.getString(10))+
                            "|"+NullFormatter.formatHtmlBlank(result.getString(4))+
                            "|+"+NullFormatter.formatHtmlBlank(result.getString(15))+NullFormatter.formatHtmlBlank(result.getString(14))+
                            "' class='detalleBusquedaContratoUsuario' type='button' value='+'   >");
                    //                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                    if (status.equalsIgnoreCase("Cancelled"))
                        body.add("<a id='"+NullFormatter.formatHtmlBlank(result.getString(1)) +"'  value='"+NullFormatter.formatHtmlBlank(result.getString(1))+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1))+"<b></a>");
                    else
                        body.add("<a style='cursor:pointer' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) +"' onclick='seleccionarUsuariosContratosOpcionDetalle(this.id)' value='"+NullFormatter.formatHtmlBlank(result.getString(1))+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1))+"<b></a>");
                    body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                    body.add(NullFormatter.formatHtmlBlank(result.getString(8)));
                    body.add(NullFormatter.formatHtmlBlank(result.getString(6)) +"<br>"+NullFormatter.formatHtmlBlank(result.getString(7)));
                    body.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                    bodys.add(body);
                    // }
                }
            }

            List<String> header=new ArrayList<String>();
            header.add(" ");
            header.add("Usuario");
            header.add("Nombre");
            header.add("Grupo");
            header.add("Estatus");
            header.add("Último acceso");

            listaContratos=new TableOd();

            listaContratos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaContratos);
    }

    public TableOd consultarDetallePagos (List<String> parametros,String status) throws Exception {
        final String origen = "BackOfficeIo.consultarDetallePagos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTContratoOd contratosOd = null;
        String respuesta=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();

        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaContratos = null;
        List<VBTContratoOd> listaContratos2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_DETALLE_PAGOS_TDC(?,?,?); END;");



            if(parametros.get(0)==null || parametros.get(0)=="")
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            List<List<String>> bodys=new ArrayList();

            while (result.next ()) {

                List<String> body=new ArrayList<String>();

                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));

                bodys.add(body);

            }

            List<String> header=new ArrayList<String>();
            header.add("Fecha de Operacion");
            header.add("Codigo de Autorizacion");
            header.add("Resultado");

            listaContratos=new TableOd();

            listaContratos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaContratos);
    }


    public TableOd consultarContratos (ConsultContratsOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.consultarContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTContratoOd contratosOd = null;
        String respuesta=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();

        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaContratos = null;
        List<VBTContratoOd> listaContratos2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin VBTONLINE.vbt_contratos_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            if(consulta.getStrNuevo()==null || consulta.getStrNuevo().equals(""))
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,consulta.getStrNuevo());

            if(consulta.getStrActivo()==null || consulta.getStrActivo().equals(""))
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,consulta.getStrActivo());

            if(consulta.getStrCancelado()==null || consulta.getStrCancelado().equals(""))
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,consulta.getStrCancelado());

            if(consulta.getStrInactivo()==null || consulta.getStrInactivo().equals(""))
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,consulta.getStrInactivo());

            if(consulta.getStrRechazado()==null || consulta.getStrRechazado().equals(""))
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,consulta.getStrRechazado());

            if(consulta.getStrDesconocido()==null || consulta.getStrDesconocido().equals(""))
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,consulta.getStrDesconocido());

            if(consulta.getHndAccion()==null || consulta.getHndAccion().equals(""))
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,consulta.getHndAccion());

            if(consulta.getTxtNumeroCartera()==null || consulta.getTxtNumeroCartera().equals(""))
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,consulta.getTxtNumeroCartera());

            if(consulta.getStrTipoUsuario()==null || consulta.getStrTipoUsuario().equals(""))
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,consulta.getStrTipoUsuario());

            if(consulta.getLogin()==null || consulta.getLogin().equals(""))
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,consulta.getLogin());

            if(consulta.getStrCmbCreadoPor()==null || consulta.getStrCmbCreadoPor().equals("Default"))
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,consulta.getStrCmbCreadoPor());

            if(consulta.getStrTxtNumeroContrato()==null || consulta.getStrTxtNumeroContrato().equals(""))
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,consulta.getStrTxtNumeroContrato());

            if(consulta.getStrTxtUsuario()==null || consulta.getStrTxtUsuario().equals(""))
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,consulta.getStrTxtUsuario());

            if(consulta.getStrTxtNombreCliente()==null || consulta.getStrTxtNombreCliente().equals(""))
                statement.setNull(14, OracleTypes.NULL);
            else
                statement.setString(14,consulta.getStrTxtNombreCliente());

            if(consulta.getStrTxtCIRIFCliente()==null || consulta.getStrTxtCIRIFCliente().equals(""))
                statement.setNull(15, OracleTypes.NULL);
            else
                statement.setString(15,consulta.getStrTxtCIRIFCliente());

            if(consulta.getStrTxtDesde()==null || consulta.getStrTxtDesde().equals(""))
                statement.setNull(16, OracleTypes.NULL);
            else
                statement.setString(16,consulta.getStrTxtDesde());

            if(consulta.getStrTxtHasta()==null || consulta.getStrTxtHasta().equals(""))
                statement.setNull(17, OracleTypes.NULL);
            else
                statement.setString(17,consulta.getStrTxtHasta());

            if(consulta.getStrOrden()==null || consulta.getStrOrden().equals(""))
                statement.setNull(18, OracleTypes.NULL);
            else
                statement.setString(18,consulta.getStrOrden());

            if(consulta.getStrTipoStatus()==null || consulta.getStrTipoStatus().equals("Default"))
                statement.setNull(19, OracleTypes.NULL);
            else
                statement.setString(19,consulta.getStrTipoStatus());

            if(consulta.getStrTipoContrato()==null || consulta.getStrTipoContrato().equals("Default"))
                statement.setNull(20, OracleTypes.NULL);
            else
                statement.setString(20,consulta.getStrTipoContrato());

            statement.registerOutParameter(21, OracleTypes.CURSOR);
            statement.registerOutParameter(22, OracleTypes.VARCHAR);
            statement.registerOutParameter(23, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(22);
            String sql = statement.getString(23);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (21);

            List<List<String>> bodys=new ArrayList();
            String carteras = new String();
            validacionUtil validar = new validacionUtil();

            SimpleDateFormat formatoFecha;
            formatoFecha=new SimpleDateFormat("dd/MM/yyyy",new Locale("es","ve".toUpperCase()));
            String fechaHoy=formatoFecha.format(new Date()).toString();
            int cont = 1;
            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                int enableButon=0;
//                carteras = new String();
//                carteras = consultarCarterasContrato(result.getString(1));

                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif'  onclick='abrirDetalleTablaContrato(this)' width='9' height='9' id='"+result.getString(1)+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+result.getString(5)+"' class='detalleBusquedaContrato' type='button' value='+'   >");

                //if (!consulta.getLogin().equalsIgnoreCase(result.getString(3))&&( validar.restarFechaDias(fechaHoy,result.getString(7))>=1)){
                if (!consulta.getLogin().equalsIgnoreCase(result.getString(3))){
                    enableButon=1;
                }

                if (result.getString(2)!=null){

                    if (result.getString(5).equalsIgnoreCase("Cancelado"))
                        body.add("<a id='"+result.getString(1) +"|"+result.getString(6)+"|"+result.getString(2)+"|"+enableButon+"|"+result.getString(9)+"|"+result.getString(10)+"'  value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");
                    else
                        body.add("<a style='cursor:pointer' id='"+result.getString(1) +"|"+result.getString(6)+"|"+result.getString(2)+"|"+enableButon+"|"+result.getString(9)+"|"+result.getString(10)+"' onclick='seleccionarContratoOpcion(this.id)' value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");
                }else{
                    if (result.getString(5).equalsIgnoreCase("Cancelado"))
                        body.add("<a id='"+result.getString(1) +"|"+result.getString(6)+"|"+"|"+enableButon+"|"+result.getString(9)+"|"+result.getString(10)+"'  value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");
                    else
                        body.add("<a style='cursor:pointer' id='"+result.getString(1) +"|"+result.getString(6)+"|"+"|"+enableButon+"|"+result.getString(9)+"|"+result.getString(10)+"' onclick='seleccionarContratoOpcion(this.id)' value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");

                }
//                myDate = myDateFormat.parse(result.getString(4));
//                fecha.setTime(myDate);
////                myDateFormat.parse(fecha.getTime().toString());
//                body.add(fecha.getTime().toString());
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(5))+"<br>"+NullFormatter.formatHtmlBlank(result.getString(7)));
                body.add(result.getString(3));
                bodys.add(body);
                if(cont<=20)
                    loggerIo.GuardarLog(usuario.getLogin(),"3","1","7",result.getString(1),usuario.getIP(),"Consulta General de Contratos");
                cont++;
            }

            List<String> header=new ArrayList<String>();
            header.add(" ");
            header.add("Contrato N°");
            header.add("Fecha Creaci&oacute;n");
            header.add("Estatus");
            header.add("Creado Por");

            listaContratos=new TableOd();

            listaContratos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaContratos);
    }

    public TableOd cargarUsuariosContratos (String numContrato) throws Exception {
        final String origen = "BackOfficeIo.cargarUsuariosContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;

        TableOd listaUsuariosContratos = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_coneditargrupos_pr(?,?,?); end;");

            if(numContrato==null )
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,numContrato);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            List<List<String>> bodys=new ArrayList();
            int con=0;
            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add("<input type='checkbox' checked='checked' value='"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"' disabled class='usuarioViejo' id='guardar_"+con+"'>");
                body.add("<span id='nombreUsuarioV_"+con+"' class='nombreValidar'> "+NullFormatter.formatHtmlBlank(result.getString(2))+"</span>");
                body.add("<span id='loginUsuarioV_"+con+"' class='loginUsuario'> "+NullFormatter.formatHtmlBlank(result.getString(1))+"</span>");
                // body.add("<span id='emailV_"+con+"' class='email'> "+NullFormatter.formatHtmlBlank(result.getString(5))+"</span>"+ "<input type='hidden' value='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"' id='codpercli_"+con+"'>");
                body.add("<select id='emailV_"+con+"' class='anchoSelect email inputFormulario ObligatorioECEmail' title='Email'></select>"+"<input type='hidden' id='emailSeleccionadoV_"+con+"' value='"+NullFormatter.formatHtmlBlank(result.getString(5))+"' class='' >"+ "<input type='hidden' value='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"' id='codpercli_"+con+"'>");
                body.add("<span  id='tipoClienteV_"+con+"' class='tipoCliente'> "+NullFormatter.formatHtmlBlank(result.getString(6))+"</span>"+"<input type='hidden' value='"+NullFormatter.formatHtmlBlank(result.getString(8))+"' id='tipoClienteCodigoV_"+con+"'>");
                //body.add("<span id='telefonoCelularV_"+con+"' class='tipoCliente'> "+NullFormatter.formatHtmlBlank(result.getString(9))+"</span>");
                body.add("<select id='telefonoCelularV_"+con+"' class='anchoSelectCell tipoCliente ObligatorioECTelefono' title='Telefono Celular'></select>"+"<input type='hidden' id='telefonoCelularSeleccionadoV_"+con+"' value='"+NullFormatter.formatHtmlBlank(result.getString(11))+"|"+NullFormatter.formatHtmlBlank(result.getString(9))+"' class='' >");
                body.add("<span > "+NullFormatter.formatHtmlBlank(result.getString(10))+"</span>"+"<input type='hidden' value='"+NullFormatter.formatHtmlBlank(result.getString(10))+"' id='estatusClienteV_"+con+"'>");

                bodys.add(body);
                con++;
            }
            /*'<input type="checkbox" checked="checked" class="usuarioAgregarEC nuevoUsuario" id="guardar_'+giCount2+'">',
        '<span id="nombreUsuario_'+giCount2+'" class="nombreValidar">'+valor.split("|")[0]+'</span>',
        '<input type="TEXT" id="loginUsuario_'+giCount2+'" class="loginUsuario" maxlength="16" style="width: 220px" >',
        '<span id="email_'+giCount2+'" class="email">'+valor.split("|")[1]+'</span><input type="hidden" value="'+valor.split("|")[2]+'|'+valor.split("|")[3]+'" id="codpercli_'+giCount2+'"> ',
        '<select  id="tipoCliente_'+giCount2+'" title="Tipo Cliente" class="tipoCliente" ></select>'] );*/

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Nombre");
            header.add("Usuario");
            header.add("E-mail");
            header.add("Grupo");
            header.add("Telefono Celular");
            header.add("Estatus");

            listaUsuariosContratos=new TableOd();

            listaUsuariosContratos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuariosContratos);
    }

    public TableOd cargarCarterasContratos (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numContrato) throws Exception {
        final String origen = "BackOfficeIo.cargarCarterasContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        ResultSet resultAux = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;

        String carteras="-2";
        String id="";
        boolean enc;

        TableOd listaCarterasContratos = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_contraeditarcarteras_pr(?,?,?,?,?,?,?); end;");

            if(p_TAGCompartida==null )
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,p_TAGCompartida);

            if(p_TAGIndividual==null )
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,p_TAGIndividual);

            if(p_TAGActiva==null )
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,p_TAGActiva);

            if(p_TAGInactiva==null )
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,p_TAGInactiva);

            if(numContrato==null )
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,numContrato);

            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);
            //resultAux =(ResultSet) statement.getObject (6);
            List<List<String>> bodys=new ArrayList();

            ArrayList<String[]> resultado=new ArrayList<String[]>();
            int numColumnas=10;
            String Fila[]=new String [numColumnas];
            enc=false;
            while (result.next())
            {
                Fila=new String[numColumnas];
                for (int x=0;x<numColumnas;++x){
                    Fila[x]=result.getObject(x+1).toString();
                }
                resultado.add(Fila);
            }
            int con=0;
            List<String> args =new ArrayList<String>();
            for(int i=0;i<resultado.size();i++){
                List<String> body=new ArrayList<String>();
                String cliSecundarios="";
                String detalle="";
                String cliPrincipal="";
                String asesor="";
                String ejecutivo="";
                String modalidad="";
                String status="";
                String cartera="";
                int productos=0;
                enc=false;
                for(int l=0;l<args.size();l++){
                    if (args.get(l).toString().equalsIgnoreCase(resultado.get(i)[0])){
                        enc=true;
                        break;
                    }
                }
                if ((!enc)||carteras.equalsIgnoreCase("-2")){
                    for(int j=0;j<resultado.size();j++){
                        if (resultado.get(i)[0].equalsIgnoreCase(resultado.get(j)[0])){
                            if(resultado.get(j)[6].equalsIgnoreCase("-1")){
                                cartera=resultado.get(j)[0];
                                modalidad=resultado.get(j)[1];
                                status=resultado.get(j)[2];
                                cliPrincipal=resultado.get(j)[3];
                                asesor=resultado.get(j)[4];
                                ejecutivo=resultado.get(j)[5];

                                if(!resultado.get(j)[7].equalsIgnoreCase("0")){
                                    productos=productos+Integer.parseInt(resultado.get(j)[7]);
                                }
                                if(!resultado.get(j)[8].equalsIgnoreCase("0")){
                                    productos=productos+Integer.parseInt(resultado.get(j)[8]);
                                }
                                if(!resultado.get(j)[9].equalsIgnoreCase("0")){
                                    productos=productos+Integer.parseInt(resultado.get(j)[9]);
                                }


                            } else{
                                cliSecundarios=cliSecundarios+resultado.get(j)[3]+", ";
                            }

                        }
                    }
                    args.add(resultado.get(i)[0]);
                    carteras=carteras+resultado.get(i)[0];

                    detalle=NullFormatter.formatHtmlBlank(asesor)+"||"+NullFormatter.formatHtmlBlank(ejecutivo)+"||"+NullFormatter.formatHtmlBlank(cliSecundarios)+"||"+productos;



                    body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' class='detalleBusquedaCartera' width='9' height='9' id='"+detalle+"' type='button' >");
                   /*  if (productos==0){
                         body.add("<input type='checkbox' disabled name='"+NullFormatter.formatHtmlBlank(cartera) +"' value='"+NullFormatter.formatHtmlBlank(cartera) +"'  id='guardarCartera_"+con+"' class='datos 0000000004_2' />");
                     }else{*/
                    body.add("<input type='checkbox' checked name='"+NullFormatter.formatHtmlBlank(cartera) +"' value='"+NullFormatter.formatHtmlBlank(cartera) +"' id='guardarCartera_"+con+"' class='datos 0000000004_2' />");

                    //}
                    body.add("<span id='codigoCarteraV_"+con+"' class='carteraVieja'>"+NullFormatter.formatHtmlBlank(cartera)+"</span>");
                    body.add("<span id='modalidadV_"+con+"' class='modalidadV'>"+NullFormatter.formatHtmlBlank(modalidad)+"</span>");
                    body.add("<span id='estatusV_"+con+"' class='estatusV'>"+NullFormatter.formatHtmlBlank(status)+"</span>");
                    body.add("<span id='clientePrincipalV_"+con+"' class='clientePrincipalV'>"+NullFormatter.formatHtmlBlank(cliPrincipal)+"</span>");
                    // body.add("<span id='asesorV_"+con+"' class='asesorV'>"+NullFormatter.formatHtmlBlank(result.getString(5))+"</span>");
                    // body.add("<span id='ejecutivoV_"+con+"' class='ejecutivoV'>"+NullFormatter.formatHtmlBlank(result.getString(6))+"</span>");

                    bodys.add(body);
                    con++;
                }

            }


            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Incluir");
            header.add("C&oacute;digo Cartera");
            header.add("Modalidad");
            header.add("Estatus");
            header.add("Cliente Principal");


            listaCarterasContratos=new TableOd();

            listaCarterasContratos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaCarterasContratos);
    }


    public String cargarCartera (String p_TAGCompartida, String p_TAGIndividual, String p_TAGActiva, String p_TAGInactiva,String numCartera) throws Exception {
        final String origen = "BackOfficeIo.cargarCarterasContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        ResultSet resultAux = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        String detalle="";

        String carteras="-2";
        String id="";
        boolean enc;

        TableOd listaCarterasContratos = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_cargarcarteraindividual_pr(?,?,?,?,?,?,?); end;");

            if(p_TAGCompartida==null )
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,p_TAGCompartida);

            if(p_TAGIndividual==null )
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,p_TAGIndividual);

            if(p_TAGActiva==null )
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,p_TAGActiva);

            if(p_TAGInactiva==null )
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,p_TAGInactiva);

            if(numCartera==null )
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,numCartera);

            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(7);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);
            //resultAux =(ResultSet) statement.getObject (6);
            List<List<String>> bodys=new ArrayList();

            ArrayList<String[]> resultado=new ArrayList<String[]>();
            int numColumnas=10;
            String Fila[]=new String [numColumnas];
            enc=false;
            while (result.next())
            {
                Fila=new String[numColumnas];
                for (int x=0;x<numColumnas;++x){
                    Fila[x]=result.getObject(x+1).toString();
                }
                resultado.add(Fila);
            }
            int con=0;
            List<String> args =new ArrayList<String>();
            for(int i=0;i<resultado.size();i++){
                List<String> body=new ArrayList<String>();
                String cliSecundarios="";
                String cliPrincipal="";
                String asesor="";
                String ejecutivo="";
                String modalidad="";
                String status="";
                String cartera="";
                int productos=0;
                enc=false;
                for(int l=0;l<args.size();l++){
                    if (args.get(l).toString().equalsIgnoreCase(resultado.get(i)[0])){
                        enc=true;
                        break;
                    }
                }
                if ((!enc)||carteras.equalsIgnoreCase("-2")){
                    for(int j=0;j<resultado.size();j++){
                        if (resultado.get(i)[0].equalsIgnoreCase(resultado.get(j)[0])){
                            if(resultado.get(j)[6].equalsIgnoreCase("-1")){
                                cartera=resultado.get(j)[0];
                                modalidad=resultado.get(j)[1];
                                status=resultado.get(j)[2];
                                cliPrincipal=resultado.get(j)[3];
                                asesor=resultado.get(j)[4];
                                ejecutivo=resultado.get(j)[5];

                                if(!resultado.get(j)[7].equalsIgnoreCase("0")){
                                    productos=productos+Integer.parseInt(resultado.get(j)[7]);
                                }
                                if(!resultado.get(j)[8].equalsIgnoreCase("0")){
                                    productos=productos+Integer.parseInt(resultado.get(j)[8]);
                                }
                                if(!resultado.get(j)[9].equalsIgnoreCase("0")){
                                    productos=productos+Integer.parseInt(resultado.get(j)[9]);
                                }

                                detalle=cartera+"||"+cliPrincipal+"||"+modalidad+"||"+status+"||"+productos+"--";
                            } else{
                                cliSecundarios=cliSecundarios+resultado.get(j)[3]+", ";
                            }

                        }
                    }
                    args.add(resultado.get(i)[0]);
                    carteras=carteras+resultado.get(i)[0];

                    detalle=detalle+NullFormatter.formatHtmlBlank(asesor)+"||"+NullFormatter.formatHtmlBlank(ejecutivo)+"||"+NullFormatter.formatHtmlBlank(cliSecundarios)+"||"+productos;

                    con++;
                }

            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (detalle);
    }

    public TableOd consultarUsuarios (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.consultarUsuarios";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaUsuarios = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuario_con_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            if(consulta.getStrRoot()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,consulta.getStrRoot());

            if(consulta.getStrLoader()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,consulta.getStrLoader());

            if(consulta.getStrSupervisor()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,consulta.getStrSupervisor());

            if(consulta.getStrCliente()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,consulta.getStrCliente());

            if(consulta.getStrActivo()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,consulta.getStrActivo());

            if(consulta.getStrInactiva()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,consulta.getStrInactiva());

            if(consulta.getStrCancelada()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,consulta.getStrCancelada());

            if(consulta.getStrBloqueado()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,consulta.getStrBloqueado());

            if(consulta.getStrDesconocido()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,consulta.getStrDesconocido());

            if(consulta.getStrTxtUsuario()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,consulta.getStrTxtUsuario());

            if(consulta.getStrTxtNombre()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,consulta.getStrTxtNombre().toUpperCase());

            if(consulta.getStrTxtCIDRIF()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,consulta.getStrTxtCIDRIF());

            if(consulta.getStrCmbTipoUsuario()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,consulta.getStrCmbTipoUsuario());

            if(consulta.getStrCmbEstatus()==null)
                statement.setNull(14, OracleTypes.NULL);
            else
                statement.setString(14,consulta.getStrCmbEstatus());

            if(consulta.getStrCmbEstatus()==null)
                statement.setNull(15, OracleTypes.NULL);
            else
                statement.setString(15,consulta.getStrCmbAmbito());

            if(consulta.getStrOrden()==null)
                statement.setNull(16, OracleTypes.NULL);
            else
                statement.setString(16,consulta.getStrOrden());

            if(consulta.getHdnAccion()==null)
                statement.setNull(17, OracleTypes.NULL);
            else
                statement.setString(17,consulta.getHdnAccion());


            statement.registerOutParameter(18, OracleTypes.CURSOR);
            statement.registerOutParameter(19, OracleTypes.VARCHAR);
            statement.registerOutParameter(20, OracleTypes.VARCHAR);

            if(consulta.getLogin()==null)
                statement.setNull(21, OracleTypes.NULL);
            else
                statement.setString(21,consulta.getLogin());

            if(consulta.getStrAsesor()==null)
                statement.setNull(22, OracleTypes.NULL);
            else
                statement.setString(22,consulta.getStrAsesor());

            statement.execute ();

            respuesta = statement.getString(19);
            sqlSelect = statement.getString(20);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));

            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (18);

            usuariosOd=new VBTUsersOd();
            listaUsuarios2 = new ArrayList<VBTUsersOd>();
            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            int filas = 1;
            while (result.next ()) {
//                usuariosOd.setLogin(result.getString(1));
//                usuariosOd.setNombres(result.getString(3));
//                usuariosOd.setTipo_grupo(result.getString(6));
//                usuariosOd.setStatus_cuenta(result.getString(9));
                body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(12))+"|"+ NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(1).toString())+"' type='button' >");
                if (!result.getString(9).toString().equalsIgnoreCase("Cancelado"))
                    body.add("<a style='cursor: pointer' id='"+ NullFormatter.formatHtmlBlank(result.getString(1)) +"' onclick='seleccionarUsuariosOpcion(this.id,this.value)' value='"+NullFormatter.formatHtmlBlank(result.getString(6).toString())+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1).toString())+"<b></a>");
                else
                    body.add("<a id='"+ NullFormatter.formatHtmlBlank(result.getString(1)) +"'  value='"+NullFormatter.formatHtmlBlank(result.getString(6).toString())+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1).toString())+"<b></a>");
                body.add(NullFormatter.formatHtmlBlank(result.getString(3).toString()));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6).toString()));
                body.add(NullFormatter.formatHtmlBlank(result.getString(9).toString())+"<br>"+NullFormatter.formatHtmlBlank(result.getString(13)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(15)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(8)));


                bodys.add(body);
                if(filas<=20)
                    loggerIo.GuardarLog(usuario.getLogin(),"3","1","1",result.getString(1).toString(),usuario.getIP(),"Consulta General de Usuarios");
                filas++;
//                myDate = myDateFormat.parse(result.getString(2));
//                fecha.setTime(myDate);
//
//                usuariosOd.setFecha_ingreso(fecha);
//                listaUsuarios2.add(usuariosOd);
//                usuariosOd=new VBTUsersOd();
            }

            //esto lo voy a llenar desde base de datos
//            listaUsuarios2 = new ArrayList<VBTUsersOd>();
//            usuariosOd=new VBTUsersOd();
//            usuariosOd.setLogin("rgodoy");
//            usuariosOd.setNombres("Rafael Godoy");
//
//            usuariosOd.setStatus_cuenta("activo");
//
//            myDate = myDateFormat.parse("17/08/2012");
//            fecha.setTime(myDate);
//
//            usuariosOd.setFecha_ingreso(fecha);
//            listaUsuarios2.add(usuariosOd);
//
//            usuariosOd=new VBTUsersOd();
//
//            usuariosOd.setLogin("rgodoy");
//            usuariosOd.setNombres("Rafael Godoy2");
//            usuariosOd.setStatus_cuenta("inactivo");
//            myDate = myDateFormat.parse("18/08/2012");
//            fecha.setTime(myDate);
//            usuariosOd.setFecha_ingreso(fecha);
//            listaUsuarios2.add(usuariosOd);
//

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Usuario");
            header.add("Nombre");
            header.add("Grupo");
            header.add("Estatus");
            header.add("Ambito");
            header.add("Ultimo Acceso");


//            List<List<String>> bodys=new ArrayList();
//
//            for(int i=0; i<listaUsuarios2.size();i++){
//                List<String> body=new ArrayList<String>();
////                body.add(listaUsuarios2.get(i).getLogin().toString());
//                body.add("<a id='"+listaUsuarios2.get(i).getLogin() +"' onclick='seleccionarUsuariosOpcion(this.id,this.value)' value='"+
//                        listaUsuarios2.get(i).getTipo_grupo().toString()+"'><b>"+listaUsuarios2.get(i).getLogin().toString()+"<b></a>");
//                body.add(listaUsuarios2.get(i).getNombres().toString());
//                body.add(listaUsuarios2.get(i).getTipo_grupo().toString());
//                body.add(listaUsuarios2.get(i).getStatus_cuenta().toString());
//                fechaString = listaUsuarios2.get(i).getFecha_ingreso().getTime().toString();
//
//                body.add(fechaString);
//                bodys.add(body);
//            }

            listaUsuarios=new TableOd();

            listaUsuarios= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public TableOd consultarBitacoras (ConsultaBitacoraOd parametros,VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "BackOfficeIo.consultarBitacoras";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaBitacora = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();


            statement  = connection.prepareCall ("begin vbtonline.vbt_bitacora_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//            vbt_bitacora_pr (p_strIdApp IN VARCHAR2,
//                    p_strTxtUsuario IN VARCHAR2,
//                    p_strTxtIdObjeto IN VARCHAR2,
//                    p_strCmbAccion IN VARCHAR2,
//                    p_strCmbObjetoAfectado IN VARCHAR2,
//                    p_strTxtDesde IN VARCHAR2,
//                    p_strTxtHasta IN VARCHAR2,
//                    p_strTxtUltimos IN VARCHAR2,
//                    p_strOrden       IN NUMBER,

//                    p_vbt_bitacora  OUT SYS_REFCURSOR,
//                    p_resultado OUT VARCHAR2)

            statement.setString(1,"1");

            if(parametros.getUsuario()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.getUsuario());

            if(parametros.getIdObjeto()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.getIdObjeto());

            if(parametros.getAccion()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.getAccion());

            if(parametros.getObjeto()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,parametros.getObjeto());

            if(parametros.getDesde()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,parametros.getDesde());

            if(parametros.getHasta()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,parametros.getHasta());

            if(parametros.getUltimos()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,parametros.getUltimos());

            statement.setString(9,"");

            if(parametros.getIp()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,parametros.getIp());


            if(!usuario.getTipo().equalsIgnoreCase("9"))
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getNumContrato());


            if(parametros.getComentario()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,parametros.getComentario());

            statement.registerOutParameter(13, OracleTypes.CURSOR);
            statement.registerOutParameter(14, OracleTypes.VARCHAR);
            statement.registerOutParameter(15, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(14);
            String sql = statement.getString(15);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (13);


            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            Date fecha2 = new Date();
            while (result.next ()) {

                body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(8))+"' type='button' >");
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(7)));
                bodys.add(body);
//
            }



            List<String> header=new ArrayList<String>();
            header.add("");
          /*  header.add("Usuario");
            header.add("Acción");
            header.add("Objeto Afectado");
            header.add("Id del Objeto");
            header.add("Fecha de acción");
            header.add("IP");
            header.add("Comentario");  */

            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Usuario_consolidado_det"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Acción_consolidado_det"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Objeto_consolidado_det"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Id_del_consolidado_det"));
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Fecha_de_consolidado_det"));
            header.add("IP");
            header.add(ResourceBundle.getBundle("vbtonline"+idioma).getString("tag_Comentario_consolidado_det"));


            listaBitacora=new TableOd();

            listaBitacora= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaBitacora);
    }

    public TableOd consultarSucesos (List<String> parametros, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarSucesos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;

        TableOd listaSucesos = null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();


            statement  = connection.prepareCall ("begin vbtonline.vbt_ConsultaExcepcion_pr(?,?,?,?,?); end;");
//            p_hdnAccion    		IN VARCHAR2,
//                    p_strTxtDesde 		IN VARCHAR2,
//            p_strTxtHasta			IN VARCHAR2,
//                    p_vbt_ConsultaExcepcion OUT SYS_REFCURSOR,
//            p_resultado OUT VARCHAR2

            if(parametros.get(0)==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if(parametros.get(1)==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);
            String sql = statement.getString(5);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);


            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            String aux ="";
            String aux2 ="";
            String aux3 ="";
            String aux4 ="";
            while (result.next ()) {
                aux ="";
                aux2 ="";
                aux3 ="";
                aux4 ="";
                aux =NullFormatter.formatHtmlBlank(result.getString(2));
                aux2 =NullFormatter.formatHtmlBlank(result.getString(4));
                aux3 =NullFormatter.formatHtmlBlank(result.getString(5));
                aux4 =NullFormatter.formatHtmlBlank(result.getString(6)).replaceAll("'","");
                body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+aux+"||"+aux2+"||"+aux3+"||"+aux4+"' type='button' >");
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                bodys.add(body);
//
            }



            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Fecha");
            header.add("Descripción de Excepción");

            listaSucesos=new TableOd();

            listaSucesos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaSucesos);
    }

    public VBTUsersOd consultarUsuario (String id, String tipoGrupo) throws Exception {
        final String origen = "BackOfficeIo.consultarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuarioconsul_pr(?,?,?); end;");

            if(id==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,id);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            usuariosOd=new VBTUsersOd();

            while (result.next ()) {
//
                usuariosOd.setLogin(id);
                usuariosOd.setNombres(result.getString(1));
                usuariosOd.setDireccion(result.getString(2));
                usuariosOd.setTelefono(result.getString(3));
                usuariosOd.setTelefono_celular(result.getString(4));
                usuariosOd.setEmail(result.getString(5));
                usuariosOd.setTipo(result.getString(6));
                usuariosOd.setStatus_cuenta(result.getString(7));
                usuariosOd.setTipo_cirif( result.getString(8).substring(0,1));
                usuariosOd.setCirif(result.getString(8).substring(2,result.getString(8).length()));
                usuariosOd.setAmbito(result.getString(9));


            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (usuariosOd);
    }

    public VBTUsersOd consultarUsuarioContrato (String id) throws Exception {
        final String origen = "BackOfficeIo.consultarUsuarioContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usueditar_con_pr(?,?,?); end;");

            if(id==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,id);


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            usuariosOd=new VBTUsersOd();

            while (result.next ()) {
//
                usuariosOd.setLogin(id);
                usuariosOd.setNombres(result.getString(1));
                usuariosOd.setDireccion(result.getString(2));
                usuariosOd.setTelefono(result.getString(3));
                usuariosOd.setTelefono_celular(result.getString(4));
                usuariosOd.setEmail(result.getString(5));
                usuariosOd.setTipo(result.getString(6));
                usuariosOd.setStatus_cuenta(result.getString(7));
                usuariosOd.setCirif(result.getString(8));
                usuariosOd.setCodpercli(result.getString(9));
                usuariosOd.setTipo_grupo(result.getString(10));
                usuariosOd.setAmbito(result.getString(11));
                usuariosOd.setStatusContrato(result.getString(12));
                usuariosOd.setCodigoPais(result.getString(13));
            }



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (usuariosOd);
    }

    public String editarUsuario (VBTUsersOd usuario, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.editarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuarioeditar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//

            if(usuario.getLogin()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin());

            if(usuario.getTelefono_celular()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,usuario.getTelefono_celular());

            if(usuario.getTelefono()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuario.getTelefono());

            if(usuario.getEmail()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,usuario.getEmail());

            if(usuario.getTipo_cirif()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,usuario.getTipo_cirif());

            if(usuario.getCirif()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,usuario.getCirif());

            if(usuario.getDireccion()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,usuario.getDireccion());

            if(usuario.getNombres()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,usuario.getNombres());

            if(usuario.getStatus_cuenta()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,usuario.getStatus_cuenta());

            if(usuario.getTipo()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,usuario.getTipo());

            if(usuario.getCambioStatus()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getCambioStatus());

            if(usuario.getTipo_grupo()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,usuario.getTipo_grupo());

            if(usuario.getCodpercli()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,usuario.getCodpercli());

            if(usuario.getAmbito()==null)
                statement.setNull(14, OracleTypes.NULL);
            else
                statement.setString(14,usuario.getAmbito());

            statement.setString(15,"58");

            statement.registerOutParameter(16, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(16);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            connection.commit();
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }
    //incompleto
    public String editarUsuarioContrato (VBTUsersOd usuario, String plogin, VBTUsersOd usuarioSesion ) throws Exception {
        final String origen = "BackOfficeIo.editarUsuarioContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuclienteeditar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//
            if(usuario.getEmail()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,usuario.getEmail());

            if(usuario.getCambioStatus()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,usuario.getCambioStatus());

            if(usuario.getStatus_cuenta()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuario.getStatus_cuenta());

            if(usuario.getLogin()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,usuario.getLogin());

            if(usuario.getTipo_grupo()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,usuario.getTipo_grupo());

            if(plogin==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,usuario.getAmbito());

            if(plogin==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,plogin);

            //lo tengo que obtener de la session
            //statement.setString(6,"scaetano");
            if(usuario.getTelefono()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,usuario.getTelefono());

            if(usuario.getTelefono_celular()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,usuario.getTelefono_celular());

            if(usuario.getCodigoPais()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,usuario.getCodigoPais());


            if(usuario.getNombres()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getNombres());

            if(usuario.getDireccion()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,usuario.getDireccion());

            if(usuario.getCirif()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,usuario.getCirif());

            statement.registerOutParameter(14, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(14);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                connection.commit();
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }
    //incompleto
    public String agregarUsuario (VBTUsersOd usuario, String plogin, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.agregarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        GeneratePassword passwordTemporal = new GeneratePassword();
//        MailManager365 mailManager = new MailManager365("vbtonline");
//        ResourceBundle rb = ResourceBundle.getBundle("vbtonline");
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuarioagregar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            String codigo = passwordTemporal.generateRandomPassword();
            String clave = codigo;
            codigo = simpleCrypt.doCrypt(codigo.toUpperCase());
            codigo = codigo.replaceAll("\r\n","");
            usuario.setPassword(codigo.trim());


            if(usuario.getLogin()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,usuario.getLogin().toLowerCase());

            if(usuario.getPassword()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,usuario.getPassword());
            // String plogin = "scaetano";

            if(plogin==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,plogin);

            if(usuario.getDireccion()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,usuario.getDireccion());

            if(usuario.getTelefono_celular()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,usuario.getTelefono_celular());

            if(usuario.getTelefono()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,usuario.getTelefono());

            if(usuario.getEmail()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,usuario.getEmail());

            if(usuario.getPassword()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,usuario.getPassword());

            if(usuario.getTipo()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,usuario.getTipo());

            if(usuario.getTipo_cirif()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,usuario.getTipo_cirif());

            if(usuario.getCirif()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getCirif());

            if(usuario.getNombres()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,usuario.getNombres().toLowerCase());

            if(usuario.getTipo_grupo()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,usuario.getTipo_grupo());

            if(usuario.getTipo_grupo()==null)
                statement.setNull(14, OracleTypes.NULL);
            else
                statement.setString(14,usuario.getAmbito());

            statement.registerOutParameter(15, OracleTypes.VARCHAR);
            statement.registerOutParameter(16, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(15);
            String existe = statement.getString(16);
//            String existe = "existe";
//            respuesta = "OK";

            if (!respuesta.equalsIgnoreCase("OK")){
                if (!existe.equalsIgnoreCase("Usuario Registrado"))
                    throw (new Exception (respuesta,null));
                else
                    return (existe);
            }else{
                connection.commit();
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //enviar correo

                MailManager365 mailManager = new MailManager365("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                String htmlText = "";
                htmlText =ResourceBundle.getBundle("UsuariosAgregar_ve_es").getString("TAGMsgEmail001") +
                        "\n\n" + ResourceBundle.getBundle("Comun_ve_es").getString("FIELDTxtLogin") + ": " + usuario.getLogin().toLowerCase()  +
                        "\n" + ResourceBundle.getBundle("Comun_ve_es").getString("FIELDPwdClave") + ": " + clave.toLowerCase()  +
                        "\n\n" + ResourceBundle.getBundle("UsuariosAgregar_ve_es").getString("TAGMsgEmail002");

                //mailManager.sendHtmlMail(rb.getString("adminmail"),strTxtEmailAgregar,"VBT Bank & Trust Online",htmlText);
                mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), "VBT Bank & Trust Online", htmlText);

                loggerIo.GuardarLog(usuarioSesion.getLogin(),"23","1","23", "",usuarioSesion.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + usuario.getEmail()+" motivado a la creación de un nuevo usuario: "+usuario.getLogin() ) ;

            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public TableOd consultarUsuariosContratos (ConsultUsersOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.consultarUsuariosContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaUsuarios = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuario_clientes_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

            if(consulta.getStrRoot()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,consulta.getStrRoot());

            if(consulta.getStrLoader()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,consulta.getStrLoader());

            if(consulta.getStrSupervisor()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,consulta.getStrSupervisor());

            if(consulta.getStrCliente()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,consulta.getStrCliente());

            if(consulta.getStrActivo()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,consulta.getStrActivo());

            if(consulta.getStrInactiva()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,consulta.getStrInactiva());

            if(consulta.getStrCancelada()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,consulta.getStrCancelada());

            if(consulta.getStrBloqueado()==null)
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8,consulta.getStrBloqueado());

            if(consulta.getStrDesconocido()==null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,consulta.getStrDesconocido());

            if(consulta.getStrTxtUsuario()==null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,consulta.getStrTxtUsuario().toLowerCase());

            if(consulta.getStrTxtNombre()==null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,consulta.getStrTxtNombre().toUpperCase());

            if(consulta.getStrTxtCIDRIF()==null)
                statement.setNull(12, OracleTypes.NULL);
            else
                statement.setString(12,consulta.getStrTxtCIDRIF());

            if(consulta.getStrCmbTipoUsuario()==null)
                statement.setNull(13, OracleTypes.NULL);
            else
                statement.setString(13,consulta.getStrCmbTipoUsuario());

            if(consulta.getStrCmbEstatus()==null)
                statement.setNull(14, OracleTypes.NULL);
            else
                statement.setString(14,consulta.getStrCmbEstatus());

            if(consulta.getStrCmbAmbito()==null)
                statement.setNull(15, OracleTypes.NULL);
            else
                statement.setString(15,consulta.getStrCmbAmbito());

            if(consulta.getStrOrden()==null)
                statement.setNull(16, OracleTypes.NULL);
            else
                statement.setString(16,consulta.getStrOrden());

            if(consulta.getHdnAccion()==null)
                statement.setNull(17, OracleTypes.NULL);
            else
                statement.setString(17,consulta.getHdnAccion());

            if(consulta.getStrCartera()==null)
                statement.setNull(18, OracleTypes.NULL);
            else
                statement.setString(18,consulta.getStrCartera());


            statement.registerOutParameter(19, OracleTypes.CURSOR);
            statement.registerOutParameter(20, OracleTypes.VARCHAR);
            statement.registerOutParameter(21, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(20);
            sqlSelect = statement.getString(21);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (19);

            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            int filas = 1;
            while (result.next ()) {
                body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(12))+"|"+ NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"' type='button' >");
                if (!result.getString(9).toString().equalsIgnoreCase("Cancelado"))
                    body.add("<a style='cursor:pointer' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) +"|"+NullFormatter.formatHtmlBlank(result.getString(14)) +"' onclick='seleccionarUsuariosContratosOpcion(this.id)' value='"+ NullFormatter.formatHtmlBlank(result.getString(1))+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1))+"<b></a>");
                else
                    body.add("<a style='cursor:default' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) +"|"+NullFormatter.formatHtmlBlank(result.getString(14)) +"' value='"+ NullFormatter.formatHtmlBlank(result.getString(1))+"'><b>"+NullFormatter.formatHtmlBlank(result.getString(1))+"<b></a>");
//                seleccionarUsuariosContratosOpcion
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(9))+"<br>"+NullFormatter.formatHtmlBlank(result.getString(13)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(16)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(8)));
                bodys.add(body);
                if(filas<=20)
                    loggerIo.GuardarLog(usuario.getLogin(),"3","1","1",result.getString(1),usuario.getIP(),"Consulta General Usuarios en Contrato");
                filas++;
            }


            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Usuario");
            header.add("Nombre");
            header.add("Grupo");
            header.add("Estatus");
            header.add("Ambito");
            header.add("Ultimo Acceso");


            listaUsuarios=new TableOd();

            listaUsuarios= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public SelectOd cargarElementosTipos (String tipo) throws Exception {
        final String origen = "BackOfficeIo.cargarElementosTipos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_elementostipo_pr(?,?,?); end;");

            if(tipo!=null){
                statement.setString(1, tipo);
            }else{
                statement.setNull(1, OracleTypes.NULL);
            }


            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(2));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

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

    public SelectOd cargarAccionFiltro (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.cargarAccionFiltro";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.accionFiltro_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }


    public SelectOd cargarAccionFiltroFC (String contrato) throws Exception {
        final String origen = "BackOfficeIo.cargarAccionFiltroFC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.accionFiltroFC_pr(?,?,?); end;");

            if(contrato== null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,contrato);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

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

    public SelectOd cargarObjetosFiltro (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.cargarObjetosFiltro";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.objetosFiltro_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarGrupoClientes (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersIo.cargarGrupoClientes";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_grupocliente_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            while (result.next()){
                elemento.setLabel(result.getString(2));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }



    public SelectOd cargarGrupoClientesCategoria (String categoria, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersIo.cargarGrupoClientesCategoria";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_grupocliente_categoria_pr(?,?,?); end;");

            if(categoria== null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,categoria);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                elemento.setLabel(result.getString(2));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTipoCiRif (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersIo.cargarTipoCiRif";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_elemencodtipo_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }
    public SelectOd cargarTipoAmbito() throws Exception {
        final String origen = "TransfersIo.cargarTipoAmbito";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_elemencodtipo_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

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

    public SelectOd consultarCorreos (String codcli,String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarCorreos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_correoenvio_login_pr(?,?,?,?); end;");
            if(codcli != null){
                statement.setString(1,codcli);
            }else
                statement.setNull(1, OracleTypes.NULL);

            if(login != null){
                statement.setString(2,login);
            }else
                statement.setNull(2, OracleTypes.NULL);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }


    public SelectOd consultarTelefonos (String codcli,String login,  VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarTelefonos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_telefonoenvio_login_pr(?,?,?,?); end;");
            if(codcli != null){
                statement.setString(1,codcli);
            }else
                statement.setNull(1, OracleTypes.NULL);

            if(login != null){
                statement.setString(2,login);
            }else
                statement.setNull(2, OracleTypes.NULL);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            label = new String();
            valor = new String();
            while (result.next()){

                if((result.getString(1)!=null)&&(result.getString(3)!=null)){
                    elemento.setLabel("(+"+NullFormatter.formatHtmlBlank(result.getString(3).trim())+") "+NullFormatter.formatHtmlBlank(result.getString(1)));
                    elemento.setValue(NullFormatter.formatHtmlBlank((result.getString(3).trim())+"|"+NullFormatter.formatHtmlBlank(result.getString(1)).trim()));
                }else if((result.getString(1)==null)&&(result.getString(3)!=null)){
                    elemento.setLabel("(+"+NullFormatter.formatHtmlBlank(result.getString(3).trim())+") ");
                    elemento.setValue(NullFormatter.formatHtmlBlank(result.getString(3).trim())+"|");
                }else if((result.getString(1)!=null)&&(result.getString(3)==null)){
                    elemento.setLabel("(+) "+NullFormatter.formatHtmlBlank(result.getString(1)));
                    elemento.setValue("|"+NullFormatter.formatHtmlBlank(result.getString(1)).trim());
                }else{
                    elemento.setLabel("() ");
                    elemento.setValue("|");
                }


                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }




    public SelectOd consultarTelefonosLocal (String codcli,String login, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.telefonosLocal";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_telefonolocal_login_pr(?,?,?,?); end;");
            if(codcli != null){
                statement.setString(1,codcli);
            }else
                statement.setNull(1, OracleTypes.NULL);

            if(login != null){
                statement.setString(2,login);
            }else
                statement.setNull(2, OracleTypes.NULL);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public String salvarPermisosGrupos (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.salvarPermisosGrupos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_grpudel_insr_pr(?,?,?,?,?,?,?,?); end;");
            if(acciones.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,acciones.get(0));
            if(acciones.get(1)==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,acciones.get(1));

            statement.setString(3,"VBT");
            statement.setString(4,"ONLINE");

            if(acciones.get(2)==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,acciones.get(2));
            if(acciones.get(3)==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,acciones.get(3));

            statement.setString(7,"1");//primero elimino
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.equalsIgnoreCase("OK")){
                connection.rollback();
                throw (new Exception (respuesta,null));
            }else

                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_grpudel_insr_pr(?,?,?,?,?,?,?,?); end;");
            if(acciones.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,acciones.get(0));
            if(acciones.get(1)==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,acciones.get(1));

            statement.setString(3,"VBT");
            statement.setString(4,"ONLINE");

            if(acciones.get(2)==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,acciones.get(2));
            if(acciones.get(3)==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,acciones.get(3));

            statement.setString(7,"2");//segundo agrego
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.equalsIgnoreCase("OK")) {
                connection.rollback();
                throw (new Exception (respuesta,null));
            }else{
                connection.commit();
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String salvarPermisosUsuarios (List<String> acciones, VBTUsersOd usuarioSesion ) throws Exception {
        final String origen = "BackOfficeIo.salvarPermisosUsuarios";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_permisosEditarUsuario_pr(?,?,?,?,?,?,?,?); end;");
            if(acciones.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,acciones.get(0));

            if(acciones.get(1)==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,acciones.get(1));

            statement.setString(3,"VBT");
            statement.setString(4,"ONLINE");

            if(acciones.get(2)==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,acciones.get(2));

            if(acciones.get(3)==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,acciones.get(3));

            statement.setString(7,"1");//primero elimino
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.equalsIgnoreCase("OK")) {
                connection.rollback();
                throw (new Exception (respuesta,null));
            }else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_permisosEditarUsuario_pr(?,?,?,?,?,?,?,?); end;");
            if(acciones.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,acciones.get(0));
            if(acciones.get(1)==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,acciones.get(1));

            statement.setString(3,"VBT");
            statement.setString(4,"ONLINE");

            if(acciones.get(2)==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,acciones.get(2));
            if(acciones.get(3)==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setString(6,acciones.get(3));

            statement.setString(7,"2");//segundo agrego
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.equalsIgnoreCase("OK")){
                connection.rollback();
                throw (new Exception (respuesta,null));
            }else{
                connection.commit();
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public SelectOd cargarUsuariosSelect () throws Exception {
        final String origen = "TransfersIo.cargarUsuariosSelect";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_contratousuarios_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            while (result.next()){
                elemento.setLabel(result.getString(1));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

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

    public TableOd busquedaUsuariosContrato (List<String> busqueda, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.busquedaUsuariosContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta=null;
        TableOd listaUsuarios = null;
        List<GruposOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_clientebuscar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGCompartida")!=null)
                statement.setString(1,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGCompartida"));
            else
                statement.setNull(1,OracleTypes.NULL);

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGIndividual")!=null)
                statement.setString(2,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGIndividual"));
            else
                statement.setNull(2,OracleTypes.NULL);

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGPrincipal")!=null)
                statement.setString(3,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGPrincipal"));
            else
                statement.setNull(3,OracleTypes.NULL);

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGSecundario")!=null)
                statement.setString(4,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGSecundario"));
            else
                statement.setNull(4,OracleTypes.NULL);
//            p_strTxtCarteraNumero
            if(!busqueda.get(0).equalsIgnoreCase(""))
                statement.setString(5,busqueda.get(0));
            else
                statement.setNull(5,OracleTypes.NULL);
            //                    p_strChkNumeroExactoCartera
            if(busqueda.get(1)!=null)
                statement.setString(6,busqueda.get(1));
            else
                statement.setNull(6,OracleTypes.NULL);
            //            p_strTxtCedula_Rif
            if(!busqueda.get(2).equalsIgnoreCase(""))
                statement.setString(7,busqueda.get(2));
            else
                statement.setNull(7,OracleTypes.NULL);
            //                    p_strTxtNombreCliente
            if(!busqueda.get(3).equalsIgnoreCase(""))
                statement.setString(8,"%"+busqueda.get(3)+"%");
            else
                statement.setNull(8,OracleTypes.NULL);
            //            p_strTxtAsesor
            if(!busqueda.get(4).equalsIgnoreCase(""))
                statement.setString(9,"%"+busqueda.get(4)+"%");
            else
                statement.setNull(9,OracleTypes.NULL);
            //                    p_strTxtEjecutivo
            if(!busqueda.get(5).equalsIgnoreCase(""))
                statement.setString(10,"%"+busqueda.get(5)+"%");
            else
                statement.setNull(10,OracleTypes.NULL);
            //            p_strTxtEjecutivo


            statement.registerOutParameter(11, OracleTypes.CURSOR);
            statement.registerOutParameter(12, OracleTypes.VARCHAR);
            statement.registerOutParameter(13, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(12);
            String sql = statement.getString(13);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (11);

//            'SELECT DISTINCT CLI.codcar cartera
//                    ,DECODE(CLI.modcar,0,'''||p_TAGCompartida||''',1,'''||p_TAGIndividual||''') modalidad
//                    ,DECODE(upper(CLI.statcar),''A'',''TAGActiva'',''I'',''TAGInactiva'') status
//                    ,DECODE(CLI.flgpri,-1,'''||p_TAGPrincipal||''','''||p_TAGSecundario||''') flag_primario
//                    ,INITCAP(CLI.referido) asesor
//                    ,INITCAP(CLI.responsable) ejecutivo
//                    ,DECODE(CLI.concirif,''0'',CLI.precirif || ''-'' || CLI.cirif, CLI.precirif || ''-'' || CLI.cirif || ''-'' || CLI.concirif)
//            ,INITCAP(CLI.na) nombre
//                    ,upper(CLI.statcar) status_cartera
//            LOWER(DIR.direcc1) email
            String img = "", img2 = "", anterior = "", ci = "", nombre1 = "", nombre2 = "";
            List<List<String>> bodys=new ArrayList();
            List<String> body = null;
            while (result.next()){
                body=new ArrayList<String>();
                if(anterior.equals(NullFormatter.formatHtmlBlank(result.getString(7))))
                    img = img + "*" +NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(NullFormatter.formatHtmlBlank(result.getString(3)))+"|"+NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(6));
                else{
                    if(img.equals("")){
                        img = "<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(NullFormatter.formatHtmlBlank(result.getString(3)))+"|"+NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(6));
                        if(result.getString(10)==null)
                            img2 = "";
                        else{
                            if(busqueda.get(6).equalsIgnoreCase("editar"))
                                img2 ="<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(10))+"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))+"' type='button' value='+' onclick='verificarSiExisteUsuario_EC(this.id)' >";
                            else
                                img2 ="<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(10))+"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))+"' type='button' value='+' onclick='verificarSiExisteUsuario_AC(this.id);' >";

                        }
                        ci = NullFormatter.formatHtmlBlank(result.getString(7));
                        nombre1 =  NullFormatter.formatHtmlBlank(result.getString(8));
                        nombre2 =  NullFormatter.formatHtmlBlank(result.getString(10));
                    }else{
                        body.add(img+"' class='detalleBusqueda' type='button' value='+'   >");
                        body.add(img2);
                        body.add(ci);
                        body.add(nombre1);
                        body.add(nombre2);
                        bodys.add(body);
                        img = "<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(NullFormatter.formatHtmlBlank(result.getString(3)))+"|"+NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(6));
                        if(result.getString(10)==null)
                            img2 = "";
                        else{
                            if(busqueda.get(6).equalsIgnoreCase("editar"))
                                img2 ="<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(10))+"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))+"' type='button' value='+' onclick='verificarSiExisteUsuario_EC(this.id)' >";
                            else
                                img2 ="<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(10))+"|"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"|"+NullFormatter.formatHtmlBlank(result.getString(11))+"' type='button' value='+' onclick='verificarSiExisteUsuario_AC(this.id);' >";
                        }
                        ci = NullFormatter.formatHtmlBlank(result.getString(7));
                        nombre1 =  NullFormatter.formatHtmlBlank(result.getString(8));
                        nombre2 =  NullFormatter.formatHtmlBlank(result.getString(10));
                    }
                }
                anterior = NullFormatter.formatHtmlBlank(result.getString(7));
            }
            if(body!=null){
                body=new ArrayList<String>();
                body.add(img+"' class='detalleBusqueda' type='button' value='+'   >");
                body.add(img2);
                body.add(ci);
                body.add(nombre1);
                body.add(nombre2);
                img = "";
                bodys.add(body);
            }
            List<String> header=new ArrayList<String>();
            header.add(" ");
            header.add(" ");
            header.add("CI/RIF");
            header.add("Cliente");
            header.add("Correo Electrónico");


            listaUsuarios=new TableOd();

            listaUsuarios= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public TableOd busquedaCarterasContrato (List<String> busqueda, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.busquedaCarterasContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;


        String respuesta=null;
        TableOd listaGrupos = null;
        List<GruposOd> listaGrupos2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_carterasbuscar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//           p_TAGCompartida IN VARCHAR2,
//            p_TAGIndividual IN VARCHAR2,
//                    p_TAGPrincipal    IN VARCHAR2,
//            p_TAGSecundario    IN VARCHAR2,
//                    p_hdnAccion     IN VARCHAR2,
//            p_strTxtCarteraNumero IN VARCHAR2,
//                    p_strChkNumeroExactoCartera IN VARCHAR2,
//            p_strTxtCedula_Rif  IN VARCHAR2,
//                    p_strTxtNombreCliente IN VARCHAR2,
//            p_strTxtAsesor IN VARCHAR2,
//                    p_strTxtEjecutivo  IN VARCHAR2,
//            p_bac_carterasbuscar OUT SYS_REFCURSOR,
//                    p_resultado OUT VARCHAR2

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGCompartida")!=null)
                statement.setString(1,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGCompartida"));
            else
                statement.setNull(1,OracleTypes.NULL);

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGIndividual")!=null)
                statement.setString(2,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGIndividual"));
            else
                statement.setNull(2,OracleTypes.NULL);

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGPrincipal")!=null)
                statement.setString(3,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGPrincipal"));
            else
                statement.setNull(3,OracleTypes.NULL);

            if(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGSecundario")!=null)
                statement.setString(4,ResourceBundle.getBundle("ClientesBuscar_ve_es").getString("TAGSecundario"));
            else
                statement.setNull(4,OracleTypes.NULL);
            statement.setString(5,"1");
//            p_strTxtCarteraNumero
            if(busqueda.get(0)!=null)
                statement.setString(6,busqueda.get(0));
            else
                statement.setNull(6,OracleTypes.NULL);
            //                    p_strChkNumeroExactoCartera
            if(busqueda.get(1)!=null)
                statement.setString(7,busqueda.get(1));
            else
                statement.setNull(7,OracleTypes.NULL);
            //            p_strTxtCedula_Rif
            if(busqueda.get(2)!=null)
                statement.setString(8,busqueda.get(2));
            else
                statement.setNull(8,OracleTypes.NULL);
            //                    p_strTxtNombreCliente
            if(busqueda.get(3)!=null)
                statement.setString(9,busqueda.get(3));
            else
                statement.setNull(9,OracleTypes.NULL);
            //            p_strTxtAsesor
            if(busqueda.get(4)!=null)
                statement.setString(10,busqueda.get(4));
            else
                statement.setNull(10,OracleTypes.NULL);
            //                    p_strTxtEjecutivo
            if(busqueda.get(5)!=null)
                statement.setString(11,busqueda.get(5));
            else
                statement.setNull(11,OracleTypes.NULL);

            statement.registerOutParameter(12, OracleTypes.CURSOR);
            statement.registerOutParameter(13, OracleTypes.VARCHAR);
            statement.registerOutParameter(14, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(13);
            String sql = statement.getString(14);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (12);

            List<List<String>> bodys=new ArrayList();
            while (result.next()){
                List<String> body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(6))+"' type='button' value='+' class='detalleBusqueda'  >");
                if(!NullFormatter.formatHtmlBlank(result.getString(9)).equalsIgnoreCase("I")){
                    //Valida que posea productos
                    int productos=Integer.parseInt(result.getString(11))+Integer.parseInt(result.getString(12))+Integer.parseInt(result.getString(13));
                    if(busqueda.get(6).equalsIgnoreCase("editar")){
                        if (ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3)).equalsIgnoreCase("Activa")){
                            body.add("<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+NullFormatter.formatHtmlBlank(result.getString(1))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(2))+
                                    "|"+ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(8))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(5))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(6))+
                                    "|"+productos+
                                    "' type='button' value='+'  onclick='fnClickAddRowCartera(this.id)' >");
                        }else{
                            body.add(" ");
                        }
                    } else {

                        if (ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3)).equalsIgnoreCase("Activa")){
                            body.add("<img style='cursor:pointer' src='resources/images/comun/approved.gif' id='"+NullFormatter.formatHtmlBlank(result.getString(1))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(2))+
                                    "|"+ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(8))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(5))+
                                    "|"+NullFormatter.formatHtmlBlank(result.getString(6))+
                                    "|"+productos+
                                    "' type='button' value='+'  onclick='verificarSiExisteCartera_AC(this.id)' >");
                        }else{
                            body.add(" ");
                        }
                    }
                }
                else
                    body.add("");
                //                body.add("<input type='checkbox' name='"+result.getString(1)+"' id='"+result.getString(1)+"' value='1' onclick='asignarATablaCarterasAC(this.id)'>");
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(8)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(7)));
                if (ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3)).equalsIgnoreCase("Inactiva"))
                    body.add("<font color='red'>"+ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3))+"</font>");
                else
                    body.add(ResourceBundle.getBundle("ClientesBuscar_ve_es").getString(result.getString(3)));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add(" ");
            header.add(" ");
            header.add("Cartera");
            header.add("Cliente");
            header.add("CI/RIF");
            header.add("Estatus");

            listaGrupos=new TableOd();

            listaGrupos= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaGrupos);
    }

    public String agregarContrato (List<CarterasOd> carteras, List<VBTUsersOd> usuarios, VBTUsersOd usuarioCreador, String descripcion, String login, String ip, String tipoContrato, VBTUsersOd usuarioSesion,String requiereSoporte) throws Exception {
        final String origen = "BackOfficeIo.agregarContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String numeroContrato = new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        GeneratePassword passwordTemporal = new GeneratePassword();
        String respuesta=null;


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            connection.setAutoCommit(false);
            /*Obtengo numero del nuevo contrato*/
            statement  = connection.prepareCall ("begin BACKOFFICE.bac_numeroNuevoContrato_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.VARCHAR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            numeroContrato = statement.getString(1);
//            numeroContrato = "1731";


            /*Creo el Contrato*/
            InetAddress direccion2 = InetAddress.getLocalHost();
            String IP_local = direccion2.getHostAddress();//ip como String
            statement  = connection.prepareCall ("begin BACKOFFICE.bac_crearContrato_pr(?,?,?,?,?,?,?); end;");
//            sql = "INSERT INTO vbt_contrato (num_contrato, descripcion, creado_por, fecha_creacion, status, fecha_status, ACEPTO_TRANSFERENCIAS, IP_CONTRATO_TRANSFERENCIA, USUARIO_CONTRATO_TRANSFERENCIA, FECHA_CONTRATO_TRANSFERENCIA) "
//                    +                  " VALUES ('" + numeroContrato + "'"
//                    +                           ",?"
//                    +                           ",'" + login.toLowerCase() + "'"
//                    +                           ",SYSDATE"
//                    +                           ",'0'"
//                    +                           ",SYSDATE"
//                    +                           ",'S'"
//                    +                           ",'" + context.getRemoteAddr() + "'"
//                    +                           ",'" + login.toLowerCase() + "'"
//                    +                           ",SYSDATE)";
            if(numeroContrato == null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,numeroContrato);

            if(descripcion == null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,descripcion);

            if(usuarioCreador.getLogin() == null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setString(3,usuarioCreador.getLogin().toLowerCase());

            if(IP_local == null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,IP_local);


            if(tipoContrato == null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setString(5,tipoContrato);

            if(requiereSoporte == null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setString(7,requiereSoporte);

            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(6);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            /*Creo usuarios asociados al contrato*/

            String strTelefono        = "";
            String strTelefonoCelular = "";
            String strTelefonoOficina = "";


            for(int i=0;i<usuarios.size();i++){
                /*Genero el password random*/
                String claveTemporal = passwordTemporal.generateRandomPassword();
                String clave = claveTemporal;
                clave = simpleCrypt.doCrypt(claveTemporal.toUpperCase());
                clave = clave.replaceAll("\r\n","");

                /*Busco la direccion*/
                statement  = connection.prepareCall ("begin BACKOFFICE.bac_buscarDireccion_pr(?,?,?); end;");
                if(usuarios.get(i).getCodpercli() == null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,usuarios.get(i).getCodpercli());

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute ();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject (2);

                String Direccion = new String();
                while (result.next()){
                    Direccion = result.getString(1);
                }

                //---- Busca los teléfonos del cliente
                statement  = connection.prepareCall ("begin BACKOFFICE.bac_buscarTelefonos_pr(?,?,?); end;");

                if(usuarios.get(i).getCodpercli() == null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,usuarios.get(i).getCodpercli());

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute ();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject (2);

                strTelefono        = "";
                strTelefonoCelular = "";
                strTelefonoOficina = "";
                String campo              = "";
                while (result.next()){
                    campo = NullFormatter.formatBlank(result.getString(2));
                    if (!NullFormatter.isBlank(campo)) {
                        switch (campo.charAt(0)) {
                            case 'H': { strTelefono        = NullFormatter.formatBlank(result.getString(1));
                                break; }
                            case 'O': { strTelefonoOficina = NullFormatter.formatBlank(result.getString(1));
                                break; }
                            case 'C': { strTelefonoCelular = NullFormatter.formatBlank(result.getString(1));
                                break; }
                            default: { }
                        }
                    }
                }

                if (NullFormatter.isBlank(strTelefono)) {
                    strTelefono = strTelefonoOficina;
                }

                /*Agrego el usuario*/


                if(usuarios.get(i).getRelacion().equalsIgnoreCase("NO OK")){

                    strTelefono=usuarios.get(i).getTelefono();
                    Direccion=usuarios.get(i).getDireccion();
                }

                statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarNuevoUsuario_pr(?,?,?,?,?,?,?,?,?,?,?); end;");

                if(usuarios.get(i).getLogin()== null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,usuarios.get(i).getLogin().toLowerCase());

                if(usuarios.get(i).getCodpercli()== null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,usuarios.get(i).getCodpercli());

                if(Direccion== null)
                    statement.setNull(3,OracleTypes.NULL);
                else
                    statement.setString(3,Direccion);

              /*  if(strTelefonoCelular== null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,strTelefonoCelular); */

                if(usuarios.get(i).getTelefono_celular()== null)
                    statement.setNull(4,OracleTypes.NULL);
                else
                    statement.setString(4,usuarios.get(i).getTelefono_celular());


                if(strTelefono == null)
                    statement.setNull(5,OracleTypes.NULL);
                else
                    statement.setString(5,strTelefono);

                if(usuarios.get(i).getEmail()== null)
                    statement.setNull(6,OracleTypes.NULL);
                else
                    statement.setString(6,usuarios.get(i).getEmail().toLowerCase());

                if(clave == null)
                    statement.setNull(7,OracleTypes.NULL);
                else
                    statement.setString(7, clave);

                if(usuarios.get(i).getNombres()== null)
                    statement.setNull(8,OracleTypes.NULL);
                else
                    statement.setString(8,usuarios.get(i).getNombres());

                if(usuarios.get(i).getCirif()== null)
                    statement.setNull(9,OracleTypes.NULL);
                else
                    statement.setString(9,usuarios.get(i).getTipo_cirif()+"-"+ usuarios.get(i).getCirif());


                if(usuarios.get(i).getCodigoPais()== null)
                    statement.setNull(10,OracleTypes.NULL);
                else
                    statement.setString(10,usuarios.get(i).getCodigoPais());


                statement.registerOutParameter(11, OracleTypes.VARCHAR);
                statement.execute ();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else{
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                    loggerIo.GuardarLog(login,"12","1","1",usuarios.get(i).getLogin(),ip,"Ha creado un usuario para el contrato Nº "+ numeroContrato );
                }


                //------- Agregamos el nuevo usuario en la tabla de grupos
                statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarUsuarioGrupo_pr(?,?,?,?,?,?); end;");

                if(usuarios.get(i).getLogin()== null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,usuarios.get(i).getLogin().toLowerCase());

                if(usuarios.get(i).getTipo()== null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,usuarios.get(i).getTipo());

                statement.setString(3,"ONLINE");
                statement.setString(4,"VBT");
                statement.setString(5, usuarioCreador.getLogin());

                statement.registerOutParameter(6, OracleTypes.VARCHAR);
                statement.execute ();

                respuesta = statement.getString(6);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                //------- Agregamos relacion usuario con contrato
                statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarUsuarioContrato_pr(?,?,?); end;");
//                sql = "INSERT INTO vbt_users_contrato (login, num_contrato) VALUES (?, ?)";


                if(usuarios.get(i).getLogin()== null)
                    statement.setNull(1,OracleTypes.NULL);
                else
                    statement.setString(1,usuarios.get(i).getLogin().toLowerCase());

                if(numeroContrato== null)
                    statement.setNull(2,OracleTypes.NULL);
                else
                    statement.setString(2,numeroContrato);

                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute ();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            }
            /*Agrego las carteras al contrato*/
            if (carteras != null) {

                for (int i=0; i < carteras.size(); i++) {


                    statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarCarterasContrato_pr(?,?,?); end;");

                    if(numeroContrato== null)
                        statement.setNull(1,OracleTypes.NULL);
                    else
                        statement.setString(1,numeroContrato);

                    if(carteras.get(i).getCodigoCartera()== null)
                        statement.setNull(2,OracleTypes.NULL);
                    else
                        statement.setString(2,carteras.get(i).getCodigoCartera());

                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute ();

                    respuesta = statement.getString(3);

                    if (!respuesta.equalsIgnoreCase("OK"))
                        throw (new Exception (respuesta,null));
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                    connection.commit();
//                    connection.rollback();
                    respuesta="OK";
                } // end for
            } else {
                //System.out.println("No se seleccionaron carteras asociadas");
                connection.commit();
//                connection.rollback();
                respuesta ="OK";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);
            loggerIo.GuardarLog(login,"12","1","7","",ip,"Ha creado un contrato Nº "+ numeroContrato );

        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            connection.rollback();
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String editarContrato(List<CarterasOd> carterasViejas, List<VBTUsersOd> usuariosViejos, List<CarterasOd> carterasNuevas, List<VBTUsersOd> usuariosNuevos, String descripcion, String estatus, String numeroContrato, VBTUsersOd usuarioEditor, List<ContentSelectOd> motivosRechazo, String requiereSoporte) throws Exception {
        final String origen = "BackOfficeIo.editarContrato";
        Connection connection = null;
        Connection connection2 = null;
        CallableStatement statement = null;
        CallableStatement statement2 = null;
        ResultSet result = null;
        String codigo, mensaje = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        boolean cambioStatus = false;
        boolean activacionContratoNuevo = false;
        String strStatusUsuario = "";

        SimpleCrypt simpleCrypt = new SimpleCrypt();
        GeneratePassword passwordTemporal = new GeneratePassword();
        String respuesta = null;
        InetAddress direccion2 = InetAddress.getLocalHost();
        String IP_local = direccion2.getHostAddress();//ip como String
        ServicioVbt vbt=new ServicioVbt();

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen);

            time = System.currentTimeMillis();

            connection = getConnection();
            connection2 = getConnection();
            connection.setAutoCommit(false);


            String carteras="";
            String carterasAux="";

            /*Verifico si se cambio el estatus del contrato*/
            statement = connection.prepareCall("begin BACKOFFICE.bac_verificarStatusContrato_pr(?,?,?,?); end;");

            if (numeroContrato == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, numeroContrato);

            if (estatus == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, estatus);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception(respuesta, null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);
            String estatusTabla = new String();
            while(result.next()){
                estatusTabla = result.getString(1);
            }


            if (!estatusTabla.isEmpty()) {
                cambioStatus = true;
                if (((estatusTabla).equals("0") || (estatusTabla).equals("4")) && estatus.equals("1"))
                    activacionContratoNuevo = true;

                /***********************  logger record **************************/
//                logger.logAction(login,"9","1","7",strNumeroContratoEditar,context.getRemoteAddr(), "");
                connection2.setAutoCommit(false);
                statement2 = connection2.prepareCall("begin vbtonline.vbt_logAction_pr(?,?,?,?,?,?,?,?); end;");

                statement2.setString(1, usuarioEditor.getLogin());
                statement2.setString(2, "9");
                statement2.setString(3, "1");
                statement2.setString(4, "7");
                statement2.setString(5, numeroContrato);
                statement2.setString(6, IP_local);
                statement2.setString(7, "");

                statement2.registerOutParameter(8, OracleTypes.VARCHAR);
                statement2.execute();

                respuesta = statement2.getString(8);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
                /**********************************************************************/
            }

//            if (acceso.verificarOpcionAccion(modulo,"EDITAR ESTATUS")) {

            /*Actualizo descripcion y estatus si fue cambiado*/
            connection.setAutoCommit(false);
            statement = connection.prepareCall("begin BACKOFFICE.bac_editarContrato_pr(?,?,?,?,?); end;");

            if (descripcion == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, descripcion);

            // Modifica el status del contrato
            if (!NullFormatter.isBlank(estatus) && cambioStatus) {

                statement.setString(2, estatus);
                /***********************  logger record **************************/
//                logger.logAction(login,"6","1","7",strNumeroContratoEditar,context.getRemoteAddr(), "Cod. estatus: " + strStatusContratoEditar);
                connection2.setAutoCommit(false);
                statement2 = connection2.prepareCall("begin vbtonline.vbt_logAction_pr(?,?,?,?,?,?,?,?); end;");

                statement2.setString(1, usuarioEditor.getLogin());
                statement2.setString(2, "6");
                statement2.setString(3, "1");
                statement2.setString(4, "7");
                statement2.setString(5, numeroContrato);
                statement2.setString(6, IP_local);
                statement2.setString(7, "Cod. estatus: " + estatus);

                statement2.registerOutParameter(8, OracleTypes.VARCHAR);
                statement2.execute();

                respuesta = statement2.getString(8);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
                /**********************************************************************/
            } else {
                statement.setNull(2, OracleTypes.NULL);
            }

            if (numeroContrato == null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, numeroContrato);

            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            if (requiereSoporte == null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5, requiereSoporte);

            statement.execute();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception(respuesta, null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

            // Modifica el status de usuarios asociados al contrato
            if (!NullFormatter.isBlank(estatus) && cambioStatus) {
                // Busca el status que le corresponde al usuario
                switch (estatus.charAt(0)) {
                    case '1': {
                        strStatusUsuario = "A"; // Estatus Activo
                        break;
                    }
                    case '2': {
                        strStatusUsuario = "C"; // Estatus Cancelado
                        break;
                    }
                    case '3': {
                        strStatusUsuario = "I"; // Estatus Inactivo
                        break;
                    }
                    case '4': {
                        strStatusUsuario = "I"; // Estatus Inactivo
                        break;
                    }
                    default: {
                        strStatusUsuario = "I"; // Estatus Inactivo
                        break;
                    }
                }

                if ((!estatus.equals("0") && !estatus.equals("1") && cambioStatus) || (estatus.equals("1") && cambioStatus && activacionContratoNuevo)) {
                    connection.setAutoCommit(false);
                    statement = connection.prepareCall("begin BACKOFFICE.bac_editarUsuario_pr(?,?,?); end;");



                    if (numeroContrato == null)
                        statement.setNull(1, OracleTypes.NULL);
                    else
                        statement.setString(1, numeroContrato);

                    if (strStatusUsuario == null)
                        statement.setNull(2, OracleTypes.NULL);
                    else
                        statement.setString(2, strStatusUsuario);
                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();

                    respuesta = statement.getString(3);

                    if (!respuesta.equalsIgnoreCase("OK"))
                        throw (new Exception(respuesta, null));
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                    connection.commit();
                }
            }
//            }

            int numUsuariosIngresaron = 0;
            /**llamar al PL bac_usuariosIngresaron_pr*/
            connection.setAutoCommit(false);
            statement = connection.prepareCall("begin BACKOFFICE.bac_usuariosIngresaron_pr(?,?,?); end;");

            if (numeroContrato == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, numeroContrato);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception(respuesta, null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject(2);
            while (result.next()) {
                numUsuariosIngresaron = numUsuariosIngresaron + 1;
            }


            /*datos del contrato a editar*/
            connection.setAutoCommit(false);
            statement = connection.prepareCall("begin BACKOFFICE.bac_contraeditarcon_pr(?,?,?); end;");

            if (numeroContrato == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, numeroContrato);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception(respuesta, null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject(2);

            String strTxtDescripcionEditar = new String();
            String strCmbStatusContratoEditar = new String();
            String strHdnCreadoPor = new String();
            String strActivable = new String();

            while (result.next()) {
                strTxtDescripcionEditar = result.getString(1);
                strCmbStatusContratoEditar = result.getString(2);
                strHdnCreadoPor = result.getString(3);
                strActivable = result.getString(4);
            }

            /*fin datos del contrato a editar*/
            // if (!(numUsuariosIngresaron == 0 && (!NullFormatter.isBlank(usuarioEditor.getLogin()) && usuarioEditor.getLogin().equals(strHdnCreadoPor)))) {
            // Elimina todas las asociaciones del contrato con los motivos de rechazo
            connection.setAutoCommit(false);
            statement = connection.prepareCall("begin BACKOFFICE.bac_eliminarAsocContrato_pr(?,?); end;");

            if (numeroContrato == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, numeroContrato);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception(respuesta, null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


            //  }

            // Verifica si el status es rechazado
            if (!NullFormatter.isBlank(estatus) && estatus.equals("4")) {
                // Asocio al contrato, los motivos de rechazo seleccionados
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_contraeditarrechazo_pr(?,?,?); end;");

                if (numeroContrato == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, numeroContrato);

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


                if (motivosRechazo != null) {

                    for (int i = 0; i < motivosRechazo.size(); i++) {
                        //Agrego los motivos de rechazo
                        connection.setAutoCommit(false);
                        statement = connection.prepareCall("begin BACKOFFICE.bac_agregarMotivoRechazo_pr(?,?,?); end;");

                        if (numeroContrato == null)
                            statement.setNull(1, OracleTypes.NULL);
                        else
                            statement.setString(1, numeroContrato);

                        if (motivosRechazo.get(i).getValue() == null)
                            statement.setNull(2, OracleTypes.NULL);
                        else
                            statement.setString(2, motivosRechazo.get(i).getValue());

                        statement.registerOutParameter(3, OracleTypes.VARCHAR);
                        statement.execute();

                        respuesta = statement.getString(3);

                        if (!respuesta.equalsIgnoreCase("OK"))
                            throw (new Exception(respuesta, null));
                        else
                            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                    } // end for
                }
            } // end if (!NullFormatter.isBlank(strStatusContratoEditar) && strStatusContratoEditar.equals("4"))

            if (!NullFormatter.isBlank(estatus)) {
                switch (estatus.charAt(0)) {
                    case '1': {
                        strStatusUsuario = "A"; // Estatus Activo
                        break;
                    }
                    case '2': {
                        strStatusUsuario = "C"; // Estatus Cancelado
                        break;
                    }
                    case '3': {
                        strStatusUsuario = "I"; // Estatus Inactivo
                        break;
                    }
                    case '4': {
                        strStatusUsuario = "I"; // Estatus Inactivo
                        break;
                    }
                    default: {
                        strStatusUsuario = "I"; // Estatus Inactivo
                        break;
                    }
                }
            }
            /*Creo usuarios asociados al contrato*/

            String strTelefono = "";
            String strTelefonoCelular = "";
            String strTelefonoOficina = "";


            for (int i = 0; i < usuariosViejos.size(); i++) {
                connection.setAutoCommit(false);
                statement = connection.prepareCall ("begin BACKOFFICE.bac_usuclienteeditar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

               /* p_strTxtEmailEditar          IN     VARCHAR2,
                        p_hdnCambioEstatus           IN     VARCHAR2,
                p_strCmbStatusCuentaEditar   IN     VARCHAR2,
                        p_strTxtUsuarioEditar        IN     VARCHAR2,
                p_strCmbTipoUsuarioEditar    IN     VARCHAR2,
                        p_strCmbAmbitoEditar         IN     VARCHAR2,
                p_login                      IN     VARCHAR2,
                        p_telefono_local             IN     VARCHAR2,
                p_telefono_celular            IN     VARCHAR2,
                        p_resultado                     OUT VARCHAR2)   */

               /* if (usuariosViejos.get(i).getEmail() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosViejos.get(i).getEmail());  */

                if (usuariosViejos.get(i).getEmail() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosViejos.get(i).getEmail().toLowerCase());

                statement.setNull(2,OracleTypes.NULL);
                statement.setNull(3,OracleTypes.NULL);

                if (usuariosViejos.get(i).getLogin() == null)
                    statement.setNull(4, OracleTypes.NULL);
                else
                    statement.setString(4, usuariosViejos.get(i).getLogin().toLowerCase());

                statement.setNull(5,OracleTypes.NULL);
                statement.setString(6, "Commit");
                statement.setNull(7, OracleTypes.NULL);
                statement.setNull(8, OracleTypes.NULL);

                if (usuariosViejos.get(i).getTelefono_celular() == null)
                    statement.setNull(9, OracleTypes.NULL);
                else
                    statement.setString(9, usuariosViejos.get(i).getTelefono_celular());

                if (usuariosViejos.get(i).getCodigoPais() == null)
                    statement.setNull(10, OracleTypes.NULL);
                else
                    statement.setString(10, usuariosViejos.get(i).getCodigoPais());



                if(usuariosViejos.get(i).getNombres()==null)
                    statement.setNull(11, OracleTypes.NULL);
                else
                    statement.setString(11,usuariosViejos.get(i).getNombres());

                if(usuariosViejos.get(i).getDireccion()==null)
                    statement.setNull(12, OracleTypes.NULL);
                else
                    statement.setString(12,usuariosViejos.get(i).getDireccion());

                if(usuariosViejos.get(i).getCirif()==null)
                    statement.setNull(13, OracleTypes.NULL);
                else
                    statement.setString(13,usuariosViejos.get(i).getCirif());


                statement.registerOutParameter(14, OracleTypes.VARCHAR);

                statement.execute();

                respuesta = statement.getString(14);
                connection.commit();
            }


            for (int i = 0; i < usuariosNuevos.size(); i++) {
                /*Genero el password random*/
                String claveTemporal = passwordTemporal.generateRandomPassword();
                String clave = claveTemporal;
                clave = simpleCrypt.doCrypt(claveTemporal.toUpperCase());
                clave = clave.replaceAll("\r\n", "");
                clave = clave.trim();

                /*Busco la direccion*/
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_buscarDireccion_pr(?,?,?); end;");
                if (usuariosNuevos.get(i).getCodpercli() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosNuevos.get(i).getCodpercli());

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject(2);

                String Direccion = new String();
                while (result.next()) {
                    Direccion = result.getString(1);
                }

                //---- Busca los teléfonos del cliente
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_buscarTelefonos_pr(?,?,?); end;");

                if (usuariosNuevos.get(i).getCodpercli() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosNuevos.get(i).getCodpercli());

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject(2);

                strTelefono = "";
                strTelefonoCelular = "";
                strTelefonoOficina = "";
                String campo = "";
                while (result.next()) {
                    campo = NullFormatter.formatBlank(result.getString(2));
                    if (!NullFormatter.isBlank(campo)) {
                        switch (campo.charAt(0)) {
                            case 'H': {
                                strTelefono = NullFormatter.formatBlank(result.getString(1));
                                break;
                            }
                            case 'O': {
                                strTelefonoOficina = NullFormatter.formatBlank(result.getString(1));
                                break;
                            }
                            case 'C': {
                                strTelefonoCelular = NullFormatter.formatBlank(result.getString(1));
                                break;
                            }
                            default: {
                            }
                        }
                    }
                }

                if (NullFormatter.isBlank(strTelefono)) {
                    strTelefono = strTelefonoOficina;
                }


                if(usuariosNuevos.get(i).getRelacion().equalsIgnoreCase("NO OK")){

                    strTelefono=usuariosNuevos.get(i).getTelefono();
                    Direccion=usuariosNuevos.get(i).getDireccion();
                }


                /*Agrego el usuario*/
                connection.setAutoCommit(false);

                statement = connection.prepareCall("begin BACKOFFICE.bac_agregarNuevoUsuario_pr(?,?,?,?,?,?,?,?,?,?,?); end;");

                if (usuariosNuevos.get(i).getLogin() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosNuevos.get(i).getLogin().toLowerCase());

                if (usuariosNuevos.get(i).getCodpercli() == null)
                    statement.setNull(2, OracleTypes.NULL);
                else
                    statement.setString(2, usuariosNuevos.get(i).getCodpercli());

                if (Direccion == null)
                    statement.setNull(3, OracleTypes.NULL);
                else
                    statement.setString(3, Direccion);

                if (usuariosNuevos.get(i).getTelefono_celular() == null)
                    statement.setNull(4, OracleTypes.NULL);
                else
                    statement.setString(4, usuariosNuevos.get(i).getTelefono_celular());

                if (strTelefono == null)
                    statement.setNull(5, OracleTypes.NULL);
                else
                    statement.setString(5, strTelefono);

                if (usuariosNuevos.get(i).getEmail() == null)
                    statement.setNull(6, OracleTypes.NULL);
                else
                    statement.setString(6, usuariosNuevos.get(i).getEmail().toLowerCase());

                if (clave == null)
                    statement.setNull(7, OracleTypes.NULL);
                else
                    statement.setString(7, clave);

                if (usuariosNuevos.get(i).getNombres() == null)
                    statement.setNull(8, OracleTypes.NULL);
                else
                    statement.setString(8, usuariosNuevos.get(i).getNombres());

                if (usuariosNuevos.get(i).getCirif() == null)
                    statement.setNull(9, OracleTypes.NULL);
                else
                    statement.setString(9, usuariosNuevos.get(i).getTipo_cirif() + "-" + usuariosNuevos.get(i).getCirif());

                if (usuariosNuevos.get(i).getCodigoPais() == null)
                    statement.setNull(10, OracleTypes.NULL);
                else
                    statement.setString(10, usuariosNuevos.get(i).getCodigoPais());


                statement.registerOutParameter(11, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(11);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else{
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
                    loggerIo.GuardarLog(usuarioEditor.getLogin(),"12","1","1",usuariosNuevos.get(i).getLogin(),usuarioEditor.getIP(),"Se ha creado un usuario para el contrato: "+numeroContrato);

                }

                //------- Agregamos el nuevo usuario en la tabla de grupos
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_agregarUsuarioGrupo_pr(?,?,?,?,?,?); end;");

                if (usuariosNuevos.get(i).getLogin() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosNuevos.get(i).getLogin().toLowerCase());

                if (usuariosNuevos.get(i).getTipo() == null)
                    statement.setNull(2, OracleTypes.NULL);
                else
                    statement.setString(2, usuariosNuevos.get(i).getTipo());

                statement.setString(3, "ONLINE");
                statement.setString(4, "VBT");
                statement.setString(5, usuarioEditor.getLogin());

                statement.registerOutParameter(6, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(6);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                //------- Agregamos relacion usuario con contrato
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_agregarUsuarioContrato_pr(?,?,?); end;");
//                sql = "INSERT INTO vbt_users_contrato (login, num_contrato) VALUES (?, ?)";


                if (usuariosNuevos.get(i).getLogin() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, usuariosNuevos.get(i).getLogin().toLowerCase());

                if (numeroContrato == null)
                    statement.setNull(2, OracleTypes.NULL);
                else
                    statement.setString(2, numeroContrato);

                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


            }

            if (!NullFormatter.isBlank(descripcion)) {
                // Modifica el contrato
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_editarContrato_pr(?,?,?,?,?); end;");

                if (descripcion == null)
                    statement.setString(1, "");
                else
                    statement.setString(1, descripcion);

                statement.setNull(2, OracleTypes.NULL);
                statement.setString(3, numeroContrato);

                statement.registerOutParameter(4, OracleTypes.VARCHAR);

                if (requiereSoporte == null)
                    statement.setNull(5, OracleTypes.NULL);
                else
                    statement.setString(5, requiereSoporte);

                statement.execute();

                respuesta = statement.getString(4);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
            }

            //bac_borraCartContrat_pr


            //*borro las carteras del contrato*//
            connection.setAutoCommit(false);
            statement = connection.prepareCall("begin BACKOFFICE.bac_borraCartContrat_pr(?,?); end;");

            if (numeroContrato == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, numeroContrato);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception(respuesta, null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


            /*Agrego las carteras al contrato*/
            for (int y = 0; y < carterasNuevas.size(); y++) {
                carterasViejas.add(carterasNuevas.get(y));
            }

            List<CarterasOd> carterasNuevasAux = new ArrayList<CarterasOd>();
            CarterasOd cartera = new CarterasOd();
            int longitud = 0;
            longitud =carterasViejas.size();
            for(int i=0; i < carterasViejas.size(); i++){
                if(carterasViejas.size() >= (i+1)){
                    for(int y=i+1; y < carterasViejas.size(); y++){
                        if(carterasViejas.get(y).getCodigoCartera().equalsIgnoreCase(carterasViejas.get(i).getCodigoCartera())){
                            carterasViejas.remove(y);
                        }
                    }
                }
            }

            if (carterasViejas != null) {
                carteras="'";
                carterasAux="";
                for (int i = 0; i < carterasViejas.size(); i++) {


                    carteras += NullFormatter.formatBlank(carterasViejas.get(i).getCodigoCartera())+"','";
                    carterasAux += NullFormatter.formatBlank(carterasViejas.get(i).getCodigoCartera())+",";

                    connection.setAutoCommit(false);
                    statement = connection.prepareCall("begin BACKOFFICE.bac_agregarCarterasContrato_pr(?,?,?); end;");

                    if (numeroContrato == null)
                        statement.setNull(1, OracleTypes.NULL);
                    else
                        statement.setString(1, numeroContrato);

                    if (carterasViejas.get(i).getCodigoCartera() == null)
                        statement.setNull(2, OracleTypes.NULL);
                    else
                        statement.setString(2, carterasViejas.get(i).getCodigoCartera());

                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();

                    respuesta = statement.getString(3);

                    if (!respuesta.equalsIgnoreCase("OK"))
                        throw (new Exception(respuesta, null));
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


                } // end for
                connection.commit();


                carteras=carteras.substring(0,carteras.length()-2);
                carterasAux=carterasAux.substring(0,carterasAux.length()-1);

            } else {
                //System.out.println("No se seleccionaron carteras asociadas");

                connection2.commit();
            }
            /*/////////////////////////////////////////////////////*/
            // Envia correos de activación para los nuevos usuarios (si los hay) de contratos activos
            if (!NullFormatter.isBlank(estatus) && activacionContratoNuevo && (usuariosNuevos != null) && (usuariosNuevos.size() > 0) && "1".equals(estatus)) {
                MailManager365 mailManager = new MailManager365("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");
                String htmlText = "";
                String htmlTextAux = "";
                //se coloca carteras viejas poruqe se unificaron las nuevas y las viejas en esta lista
                if (carterasViejas != null) {
                    htmlTextAux += "\n" +
                            ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGCarterasAsociadas") + ": " + NullFormatter.formatBlank(carterasViejas.get(0).getCodigoCartera());

                    for (int i = 1; i < carterasViejas.size(); i++) {
                        htmlTextAux += ", " + NullFormatter.formatBlank(carterasViejas.get(i).getCodigoCartera());
                    } //
                } else {
                    htmlTextAux += "\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgNoTieneCarteraAsociadas");
                }  // end if (strCodigosCarteras!=null)


                String claveTemporal = new String();
                String clave = new String();
                if (usuariosNuevos != null && usuariosNuevos.size() > 0) {
                    for (int i = 0; i < usuariosNuevos.size(); i++) {


                        //CREA EL CORREO CON LA INFORMACIÓN PARA ENVIAR A LOS USUARIOS DEL CONTRATO
                     /*   htmlText = ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail001") +
                                "\n\nContrato N°: " + numeroContrato +
                                "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGUserName") + ": " + usuariosNuevos.get(i).getLogin().toLowerCase();

                        htmlText += htmlTextAux;
                        htmlText += "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail002");
                        //pageConfig.getTagFieldValue("TAGMsgEmail002") + " <A HREF=http://" + (context.getRequest()).getServerName() + ":" + (context.getRequest()).getServerPort() + "/vbtonline/pages/index.jsp>VBT Online</A>"+

                        mailManager.sendPlainMail(rb.getString("adminmail"), usuariosNuevos.get(i).getEmail().toLowerCase(), "VBT Bank & Trust Online Registration", htmlText);
                            */


                        String mensaje2 = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGUserName") + ": " + usuariosNuevos.get(i).getLogin().toLowerCase();

                        String telefono="";
                        String respuestaMsj="";
                        telefono= vbt.getTelefonoONL(usuariosNuevos.get(i).getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                        if (!telefono.equalsIgnoreCase("0")) {
                            respuestaMsj=vbt.enviarAlertaONL("9101",mensaje2,usuariosNuevos.get(i).getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                            loggerIo.GuardarLog(usuarioEditor.getLogin(), "17", "1", "1", usuariosNuevos.get(i).getLogin().toLowerCase(), usuarioEditor.getIP(), "Se ha enviado el usuario :"+ usuariosNuevos.get(i).getLogin().toLowerCase()+" del contrato: "+numeroContrato+ " al telefono: "+telefono);

                        }




                        claveTemporal = new String();
                        clave = new String();
                        claveTemporal = passwordTemporal.generateRandomPassword();
                        clave = claveTemporal;
                        clave = simpleCrypt.doCrypt(claveTemporal.toUpperCase());
                        clave = clave.replaceAll("\r\n", "");
                        clave = clave.trim();

                        //Actualiza el password del usuario
                        connection.setAutoCommit(false);
                        statement = connection.prepareCall("begin BACKOFFICE.bac_actPassUser_pr(?,?,?); end;");

                        if (clave == null)
                            statement.setNull(1, OracleTypes.NULL);
                        else
                            statement.setString(1, clave);

                        if (usuariosNuevos.get(i).getLogin() == null)
                            statement.setNull(2, OracleTypes.NULL);
                        else
                            statement.setString(2, usuariosNuevos.get(i).getLogin());

                        statement.registerOutParameter(3, OracleTypes.VARCHAR);
                        statement.execute();

                        respuesta = statement.getString(3);

                        if (!respuesta.equalsIgnoreCase("OK"))
                            throw (new Exception(respuesta, null));
                        else
                            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


                     /*   htmlText = ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail001") +
                                "\n\nContrato N°: " + numeroContrato +
                                "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGPassword") + ": " + clave.toLowerCase() +
                                "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail002");
                        //pageConfig.getTagFieldValue("TAGMsgEmail002") + " <A HREF=http://" + (context.getRequest()).getServerName() + ":" + (context.getRequest()).getServerPort() + "/vbtonline/pages/index.jsp>VBT Online</A>"+

                        mailManager.sendPlainMail(rb.getString("adminmail"), usuariosNuevos.get(i).getEmail().toLowerCase(), "VBT Bank & Trust Online Registration", htmlText);

                            */

                        String mensaje3 = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGUserSMS")+": "+this.mascaraUsuario(usuariosNuevos.get(i).getLogin().toLowerCase())+ ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGPassword") + ": " + clave.toLowerCase();

                        respuestaMsj=vbt.enviarAlertaONL("9102",mensaje3,usuariosNuevos.get(i).getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                        loggerIo.GuardarLog(usuarioEditor.getLogin(), "17", "1", "1", usuariosNuevos.get(i).getLogin().toLowerCase(), usuarioEditor.getIP(), "Se ha enviado la contraseña :"+ usuariosNuevos.get(i).getLogin().toLowerCase()+" del contrato: "+numeroContrato+ " al telefono: "+telefono);

                    }  // End for (int i=0; i<usuariosNuevos.size() ; i++)
                }    // End if (usuariosNuevos != null && usuariosNuevos.size() > 0)
            }  // End if (!NullFormatter.isBlank(estatus) && !activacionContratoNuevo && (usuariosNuevos != null) && (usuariosNuevos.size() >0) && "1".equals(estatus) )
            /*/////////////////////////////////////////////////////*/

            /*///////////////////////////////////////////////////**/
            /***************************************************************************
             * Envia correos de activación
             * ************************************************************************/
            if (!NullFormatter.isBlank(estatus) && activacionContratoNuevo) {
                MailManager365 mailManager = new MailManager365("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");
                String htmlText = "";
                String htmlTextAux = "";

                // ************************************* Busca las carteras asociadas al contrato.
                //if (acceso.verificarOpcionAccion(modulo,"EDITAR")) {
                if (carterasViejas != null) {
                    htmlTextAux += "\n" +
                            ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGCarterasAsociadas") + ": " + NullFormatter.formatBlank(carterasViejas.get(0).getCodigoCartera());

                    for (int i = 1; i < carterasViejas.size(); i++) {
                        htmlTextAux += ", " + NullFormatter.formatBlank(carterasViejas.get(i).getCodigoCartera());
                    } //
                } else {
                    htmlTextAux += "\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgNoTieneCarteraAsociadas");
                }  // end if (strCodigosCarteras!=null)
//                } else {
//                    Table tableCarteras = new Table();
//                    Row rowCarteras     = new Row();
//                    int maxCarteras     = 0;
//                    String carterasSQL  = " SELECT codcar "
//                            + " FROM   vbt_contrato_carteras "
//                            + " WHERE  num_contrato = '" + strNumeroContratoEditar + "' ";
//
//                    tableCarteras.fillWithQuery(carterasSQL,"jdbc/vbtonlineDB");
//                    maxCarteras = tableCarteras.numberOfRows();
//
//                    if (maxCarteras > 0) {
//                        rowCarteras = tableCarteras.getRowAt(0);
//                        htmlTextAux += "\n" + pageConfig.getTagFieldValue("TAGCarterasAsociadas") + ": " +
//                                NullFormatter.formatBlank(rowCarteras.getColumnAt(0));
//
//                        for (int i=1; i<maxCarteras; i++) {
//                            rowCarteras = tableCarteras.getRowAt(i);
//                            htmlTextAux += ", " + NullFormatter.formatBlank(rowCarteras.getColumnAt(0));
//                        } // end for carteras
//                    } else {
//                        htmlTextAux += "\n" + pageConfig.getTagFieldValue("TAGMsgNoTieneCarteraAsociadas");
//                    } // end if (strCodigosCarteras!=null)
//                }
                // ******************************* end if (!acceso.verificarOpcionAccion(modulo,"EDITAR ESTATUS"))

                // ******************************* Busca los usuarios asociados al contrato y envia correos de activacion.
//                bac_buscarUsuarios_pr
                //Actualiza el password del usuario
                connection.setAutoCommit(false);
                statement = connection.prepareCall("begin BACKOFFICE.bac_buscarUsuarios_pr(?,?,?); end;");

                if (numeroContrato == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, numeroContrato);

                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                result = (ResultSet) statement.getObject(2);
                String claveTemporal = new String();
                String clave = new String();
                while (result.next()) {


                    //CREA EL CORREO CON LA INFORMACIÓN PARA ENVIAR A LOS USUARIOS DEL CONTRATO
                 /*   htmlText = ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail001") +
                            "\n\nContrato N°: " + numeroContrato +
                            "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGUserName") + ": " + result.getString(1).toLowerCase();

                    htmlText += htmlTextAux;
                    htmlText += "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail002");
                    //pageConfig.getTagFieldValue("TAGMsgEmail002") + " <A HREF=http://" + (context.getRequest()).getServerName() + ":" + (context.getRequest()).getServerPort() + "/vbtonline/pages/index.jsp>VBT Online</A>"+

                    String correo2 = result.getString(2).toLowerCase();
//                    String correo2 = "rafgoddark@gmail.com";
                    mailManager.sendPlainMail(rb.getString("adminmail"), correo2, "VBT Bank & Trust Online Registration", htmlText);
                            */





                    //Busca los ejecutivos asociados
                    String emailsEjecuvitos=this.emailEjecutivos(carteras);
                    String telefonoONL= vbt.getTelefonoONL(result.getString(1).toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));

                    String mensajeUsu = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGUserName") + ": " + result.getString(1).toLowerCase();
                    String respuestaMsj1=vbt.enviarAlertaONL("9101",mensajeUsu,result.getString(1).toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                    loggerIo.GuardarLog(usuarioEditor.getLogin(), "17", "1", "1", result.getString(1).toLowerCase(), usuarioEditor.getIP(), "Se ha enviado el usuario :"+result.getString(1).toLowerCase()+" del contrato: "+numeroContrato+ " al telefono: "+telefonoONL);


                    // envia el correo a los ejecutivos

                    String     htmlText2="";


                    claveTemporal = new String();
                    clave = new String();
                    claveTemporal = passwordTemporal.generateRandomPassword();
                    clave = claveTemporal;
                    clave = simpleCrypt.doCrypt(claveTemporal.toUpperCase());
                    clave = clave.replaceAll("\r\n", "");
                    clave = clave.trim();

                    //Actualiza el password del usuario
                    connection.setAutoCommit(false);
                    statement = connection.prepareCall("begin BACKOFFICE.bac_actPassUser_pr(?,?,?); end;");

                    if (clave == null)
                        statement.setNull(1, OracleTypes.NULL);
                    else
                        statement.setString(1, clave);

                    if (result.getString(1) == null)
                        statement.setNull(2, OracleTypes.NULL);
                    else
                        statement.setString(2, result.getString(1).toLowerCase());

                    statement.registerOutParameter(3, OracleTypes.VARCHAR);
                    statement.execute();

                    respuesta = statement.getString(3);

                    if (!respuesta.equalsIgnoreCase("OK"))
                        throw (new Exception(respuesta, null));
                    else
                        logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");


                 /*   htmlText = ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail001") +
                            "\n\nContrato N°: " + numeroContrato +
                            "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGPassword") + ": " + claveTemporal.toLowerCase() +
                            "\n\n" + ResourceBundle.getBundle("ContratosEditar_ve_es").getString("TAGMsgEmail002");
                    //pageConfig.getTagFieldValue("TAGMsgEmail002") + " <A HREF=http://" + (context.getRequest()).getServerName() + ":" + (context.getRequest()).getServerPort() + "/vbtonline/pages/index.jsp>VBT Online</A>"+

                    String correo = result.getString(2).toLowerCase();
//                    String correo = "rafgoddark@gmail.com";
                    mailManager.sendPlainMail(rb.getString("adminmail"), correo, "VBT Bank & Trust Online Registration", htmlText);

                             */
                    String mensajePass =ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGUserSMS")+": "+this.mascaraUsuario(result.getString(1).toLowerCase())+ ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGPassword") + ": " +  claveTemporal.toLowerCase();


                    respuestaMsj1=vbt.enviarAlertaONL("9102",mensajePass,result.getString(1).toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));
                    loggerIo.GuardarLog(usuarioEditor.getLogin(), "17", "1", "1", result.getString(1).toLowerCase(), usuarioEditor.getIP(), "Se ha enviado la contraseña :"+ result.getString(1).toLowerCase()+" del contrato: "+numeroContrato+ " al telefono: "+telefonoONL);

                    htmlText2 = ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_eje") +" " +  telefonoONL +
                            "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje2") + numeroContrato +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje3") + ": " + carterasAux +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje4") + ": " + result.getString(1).toLowerCase()+"\n\n\n";


                    mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),emailsEjecuvitos,ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_ejeTitulo"), htmlText2);

                    loggerIo.GuardarLog(usuarioEditor.getLogin(),"23","1","23", "",usuarioEditor.getIP(),"Se ha enviado un correo a los siguientes destinatarios: " + emailsEjecuvitos +" motivado a la activacion del contrato Nº "+ numeroContrato+ " Usuario: "+result.getString(1).toLowerCase()) ;

                } // End if (maxUsuarios > 0)
            } // End if (!NullFormatter.isBlank(strStatusContratoEditar) && activacionContratoNuevo)
            /*///////////////////////////////////////////////////**/
            vbt=null;
            connection.commit();

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + BackOfficeIo.class + " | " + origen);


        } catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioEditor.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioEditor.getLogin(), usuarioEditor.getNumContrato());
            connection.rollback();
            connection2.rollback();
            vbt=null;
            throw (new Exception(ex.getMessage(), null));
        } finally {
            vbt=null;
            closeJdbcObjects(connection, statement, result);
            closeJdbcObjects(connection2, statement2, result);
        }

        return (respuesta);
    }
    /*
    public String GuardarLog (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6, String parametro7) throws Exception {
        final String origen = "BackOfficeIo.GuardarLog";
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
    }   */


    public String eliminarPermisosUsuarios (List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.salvarPermisosUsuarios";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_eliminarAccionUser_pr(?,?,?); end;");
            if(acciones.get(0)==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,acciones.get(0));
            if(acciones.get(1)==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,acciones.get(1));

            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public SelectOd cargarMotivosRechazo (VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersIo.cargarMotivosRechazo";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_contraeditarrechat_pr(?,?); end;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            while (result.next()){
                elemento.setLabel(result.getString(2));
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarMotivosRechazoContrato (String contrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "TransfersIo.cargarMotivosRechazoContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_contraeditarrechazo_pr(?,?,?); end;");

            if(contrato==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,contrato);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){
                elemento.setValue(result.getString(1));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ TransfersIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public String validarUsuario ( String usuario) throws Exception {
        final String origen = "BackOfficeIo.validarUsuario";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;

        String numeroContrato = new String();
        SimpleCrypt simpleCrypt = new SimpleCrypt();
        GeneratePassword passwordTemporal = new GeneratePassword();
        String respuesta=null;


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();
            connection.setAutoCommit(false);



            /*Busco la direccion*/
            statement  = connection.prepareCall ("begin BACKOFFICE.bac_validarusuario_pr(?,?); end;");
            if(usuario == null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,usuario);

            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(2);


            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");




            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));

        }
        finally {
            connection.rollback();
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public ParametrosPersonalesOd cargarParametrosGlobales (String tipo, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.cargarParametrosGlobales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonales=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametros_globales_pr(?,?,?,?); end;");
//            bt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
//                    P_NUM_CONTRATO  IN VARCHAR2,
//                    P_tipo_persona IN VARCHAR2,
//                    p_datosPorDefecto OUT vbt_datos_diseneBanco,
//                    p_resultado OUT VARCHAR2)


////



            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            if(tipo==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setString(4,tipo);

            statement.execute ();

            respuesta = statement.getString(2);
            String sql = statement.getString(3);



            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (1);

            String monto = new String();
            while (result.next()){
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmaxtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))),2,","));
                parametrosPersonales.setVbt_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
                parametrosPersonales.setEx_nmtd(result.getInt(5));
                parametrosPersonales.setEx_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
                parametrosPersonales.setEx_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
                parametrosPersonales.setEx_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(8))),2,","));
                parametrosPersonales.setMinimun_balance(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(10))),2,","));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public ParametrosPersonalesOd cargarParametrosContratos (String contrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.cargarParametrosContratos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonales=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametros_contratos_pr(?,?,?); end;");
//            bt_movimi_codpercli_pr (p_codpercli   IN VARCHAR2,
//                    P_NUM_CONTRATO  IN VARCHAR2,
//                    P_tipo_persona IN VARCHAR2,
//                    p_datosPorDefecto OUT vbt_datos_diseneBanco,
//                    p_resultado OUT VARCHAR2)


////
            if(contrato == null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,contrato);



            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);


            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            String monto = new String();
            while (result.next()){
                parametrosPersonales.setVbt_nmtd(result.getInt(1));
                parametrosPersonales.setVbt_mmaxtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(2))),2,","));
                parametrosPersonales.setVbt_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(3))),2,","));
                parametrosPersonales.setVbt_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(4))),2,","));
                parametrosPersonales.setEx_nmtd(result.getInt(5));
                parametrosPersonales.setEx_mmtd(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(6))),2,","));
                parametrosPersonales.setEx_mminto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(7))),2,","));
                parametrosPersonales.setEx_mmto(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(8))),2,","));
                parametrosPersonales.setAct_plazo(CurrencyFormatter.formatNumber(Double.toString(StrictMath.abs(result.getDouble(10))),2,","));
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }








    public String guardarParametrosPersonalesBO (ParametrosPersonalesOd parametrosPersonales,String tipo, String tipoParametro,VBTUsersOd usuarioSesion ) throws Exception {
        final String origen = "BackOfficeIo.guardarParametrosPersonalesBO";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        String respuesta=null;
        ParametrosPersonalesOd parametrosPersonalesOd=new ParametrosPersonalesOd();
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametros_personales_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
//           vbt_movimi_ope_codper_pr (p_codpercli   IN VARCHAR2,
//            P_NUM_CONTRATO  IN VARCHAR2,
//                    P_VBT_NMTD       IN NUMBER,
//            P_VBT_MMTD       IN NUMBER,
//                    P_VBT_MMINTO     IN NUMBER,
//            P_VBT_MMTO       IN NUMBER,
//                    P_EX_NMTD        IN NUMBER,
//            P_EX_MMTD        IN NUMBER,
//                    P_EX_MMINTO      IN NUMBER,
//            P_EX_MMTO        IN NUMBER,
//                    p_resultado OUT VARCHAR2)
////
            if(parametrosPersonales.getCodpercli()==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,parametrosPersonales.getCodpercli());

            if(parametrosPersonales.getNum_contrato()==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,parametrosPersonales.getNum_contrato());

            if(parametrosPersonales.getVbt_nmtd()==null)
                statement.setNull(3,OracleTypes.NULL);
            else
                statement.setInt(3,parametrosPersonales.getVbt_nmtd());

            if(parametrosPersonales.getVbt_mmaxtd()==null)
                statement.setNull(4,OracleTypes.NULL);
            else
                statement.setDouble(4,Double.parseDouble(parametrosPersonales.getVbt_mmaxtd()));

            if(parametrosPersonales.getVbt_mminto()==null)
                statement.setNull(5,OracleTypes.NULL);
            else
                statement.setDouble(5,Double.parseDouble(parametrosPersonales.getVbt_mminto()));

            if(parametrosPersonales.getVbt_mmto()==null)
                statement.setNull(6,OracleTypes.NULL);
            else
                statement.setDouble(6,Double.parseDouble(parametrosPersonales.getVbt_mmto()));

            if(parametrosPersonales.getEx_nmtd()==null)
                statement.setNull(7,OracleTypes.NULL);
            else
                statement.setInt(7,parametrosPersonales.getEx_nmtd());

            if(parametrosPersonales.getEx_mmtd()==null)
                statement.setNull(8,OracleTypes.NULL);
            else
                statement.setDouble(8,Double.parseDouble(parametrosPersonales.getEx_mmtd()));

            if(parametrosPersonales.getEx_mminto()==null)
                statement.setNull(9,OracleTypes.NULL);
            else
                statement.setDouble(9,Double.parseDouble(parametrosPersonales.getEx_mminto()));

            if(parametrosPersonales.getEx_mmto()==null)
                statement.setNull(10,OracleTypes.NULL);
            else
                statement.setDouble(10,Double.parseDouble(parametrosPersonales.getEx_mmto()));

            if(parametrosPersonales.getAccount_num()==null)
                statement.setNull(11,OracleTypes.NULL);
            else
                statement.setString(11,parametrosPersonales.getAccount_num());

            if(tipo==null)
                statement.setNull(12,OracleTypes.NULL);
            else
                statement.setString(12,tipo);

            if(tipoParametro==null)
                statement.setNull(13,OracleTypes.NULL);
            else
                statement.setString(13,tipoParametro);

            if (parametrosPersonales.getMinimun_balance()==null)
                statement.setNull(14,OracleTypes.NULL);
            else
                statement.setString(14,parametrosPersonales.getMinimun_balance());

            statement.registerOutParameter(15, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(15);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String emailEjecutivos (String cartera) throws Exception {
        final String origen = "BackOfficeIo.emailEjecutivos";
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_mail_ejecutivos(?,?,?); end;");

            if(cartera==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1, cartera);


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

            if (correos.substring(correos.length()-1,correos.length()).equalsIgnoreCase(",")){
                correos=correos.substring(0,correos.length()-1);
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
    public String validarCarteraContrato (String numCartera, String tipoContrato, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.validarCarteraContrato";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        Integer cantidad=0;
        String respuesta=null;


        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_validarContratos_pr(?,?,?,?); end;");


            if(numCartera==null)
                statement.setNull(1,OracleTypes.NULL);
            else
                statement.setString(1,numCartera);

            if(tipoContrato==null)
                statement.setNull(2,OracleTypes.NULL);
            else
                statement.setString(2,tipoContrato);

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(4);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (3);

            mensaje="";
            while (result.next()){

                if (!NullFormatter.isBlank(mensaje)) {
                    mensaje += ", ";
                }
                tipoContrato=NullFormatter.formatBlank(result.getString(2));
                mensaje += NullFormatter.formatBlank(result.getString(1));
                cantidad++;
//                correos= result.getString(1);
            }


            if (cantidad>0){
                String tipo2="";

                if(tipoContrato.equalsIgnoreCase("FC"))
                    tipo2="Firmas Conjuntas";
                else
                    tipo2="Firmas Indistintas";

                if (cantidad>1){
                    mensaje="No se puede modificar el contrato, ya que la cartera Nº"+numCartera+" se encuentra asociada a \na los contratos Nª "+mensaje+" de "+tipo2;
                }else{
                    mensaje="No se puede modificar el contrato, ya que la cartera Nº"+numCartera+" se encuentra asociada \nal Contrato Nª "+mensaje+" de "+tipo2;
                }

            }else{
                mensaje="OK";
            }

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (mensaje);
    }

    public TableOd consultarLogSms (ConsultLogSmsOd consulta, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.consultarLogSms";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaUsuarios = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();       // hacer pl con firma y body en paquete

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_consultarlogsms_pr(?,?,?,?,?,?,?,?,?,?); end;");

            if(consulta.getStrContrato()==null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,consulta.getStrContrato());

            if(consulta.getStrUsuario()==null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,consulta.getStrUsuario());

            if(consulta.getStrTelefono()==null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,consulta.getStrTelefono());

            if(consulta.getStrTxtDesde()==null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,consulta.getStrTxtDesde());

            if(consulta.getStrTxtHasta()==null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,consulta.getStrTxtHasta());

            if(consulta.getStrTipoStatus()==null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,consulta.getStrTipoStatus());

            if(consulta.getStrTipoMotivo()==null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,consulta.getStrTipoMotivo());


            statement.registerOutParameter(8, OracleTypes.CURSOR);
            statement.registerOutParameter(9, OracleTypes.VARCHAR);
            statement.registerOutParameter(10, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(9);
            sqlSelect = statement.getString(10);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));

            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (8);

            usuariosOd=new VBTUsersOd();
            listaUsuarios2 = new ArrayList<VBTUsersOd>();

            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            int filas = 1;
            while (result.next ()) {

                body=new ArrayList<String>();

                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(7)));

                bodys.add(body);

            }

            List<String> header=new ArrayList<String>();

            header.add("Fecha Envio");
            header.add("Contrato");
            header.add("Usuario");
            header.add("Telefono");
            header.add("Proveedor");
            header.add("Estatus");
            header.add("Motivo");



            listaUsuarios=new TableOd();

            listaUsuarios= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {

            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }



    public TableOd consultarPaisesNoPermitidos (String codPais, String nomPais, String paises,String revision) throws Exception {
        final String origen = "BackOfficeIo.consultarPaisesNoPermitidos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaPaises = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();       // hacer pl con firma y body en paquete

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_paisesNoPermitidos_pr(?,?,?,?,?,?,?); end;");


            if(codPais==null )
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,codPais);

            if(nomPais==null )
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,nomPais.toUpperCase());

            if(paises==null )
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,paises);
            if(paises==null )
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,revision);

            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(6);
            sqlSelect = statement.getString(7);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));

            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);

            usuariosOd=new VBTUsersOd();
            listaUsuarios2 = new ArrayList<VBTUsersOd>();

            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            int filas = 1;
            while (result.next ()) {

                body=new ArrayList<String>();

                body.add(NullFormatter.formatHtmlBlank(result.getString(1).toString()));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2).toString()));
                if(result.getString(3).equalsIgnoreCase("S"))
                    body.add("Si <input id='"+result.getString(1)+"|"+result.getString(2)+ "' class='botonMin' value ='Cambiar' type='button'  onclick='cambiarListaPaises(this.id,0)'>");
                else
                    body.add("No <input id='"+result.getString(1)+"|"+result.getString(2)+ "' class='botonMin' value ='Cambiar' type='button'  onclick='cambiarListaPaises(this.id,1)'>");

                if(result.getString(4).equalsIgnoreCase("S"))
                    body.add("Si <input id='"+result.getString(1)+"|"+result.getString(2)+ "' class='botonMin' value ='Cambiar' type='button'  onclick='cambiarRevisionPais(this.id,0)'>");
                else
                    body.add("No <input id='"+result.getString(1)+"|"+result.getString(2)+ "' class='botonMin'  value ='Cambiar' type='button'  onclick='cambiarRevisionPais(this.id,1)'>");


                bodys.add(body);

            }

            List<String> header=new ArrayList<String>();

            header.add("Código País");
            header.add("Nombre País");
            header.add("Transferencias");
            header.add("¿Requiere Revisión?");

            listaPaises=new TableOd();

            listaPaises= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaPaises);
    }

    public String  cambiaPaisesNoPermitidos (String codPais, String nomPais, String tipoOpcion) throws Exception {
        final String origen = "BackOfficeIo.consultarPaisesNoPermitidosCambia";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;

        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();

        TableOd listaPaises = null;
        List<VBTUsersOd> listaUsuarios2;

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();       // hacer pl con firma y body en paquete

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_cambiaPaisNoPermitidos_pr(?,?,?,?); end;");


            if(codPais==null )
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,codPais);

            if(nomPais==null )
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,nomPais);

            if(tipoOpcion==null )
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,tipoOpcion);

            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK")){
                connection.rollback();
                throw (new Exception (respuesta,null));
            }else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                connection.commit();
            }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public String  cambiaPaisesRevision (String codPais, String nomPais, String tipoOpcion) throws Exception {
        final String origen = "BackOfficeIo.cambiaPaisesRevision";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;
        String strFormat="dd/MM/yyyy";
        DateFormat myDateFormat = new SimpleDateFormat(strFormat);
        Date myDate = new Date();
        Calendar fecha = new GregorianCalendar();
        String fechaString=new String();
        TableOd listaPaises = null;
        List<VBTUsersOd> listaUsuarios2;
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);
            time = System.currentTimeMillis ();
            connection=getConnection();       // hacer pl con firma y body en paquete
            statement  = connection.prepareCall ("begin BACKOFFICE.bac_cambiaPaisesRevision_pr(?,?,?,?); end;");
            if(codPais==null )
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,codPais);
            if(nomPais==null )
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,nomPais);

            if(tipoOpcion==null )
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,tipoOpcion);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.execute ();
            respuesta = statement.getString(4);
            if (!respuesta.equalsIgnoreCase("OK")){
                connection.rollback();
                throw (new Exception (respuesta,null));
            }
            else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

                connection.commit();
            }
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }
        return (respuesta);
    }

    public String mascaraUsuario (String usuario) throws Exception {
        final String origen = "BackOfficeIo.mascaraUsuario";

        long time = 0;
        String login="";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            if (usuario.length()>3){
                login="****"+usuario.substring(usuario.length()-4,usuario.length());
            }else{
                login="****"+usuario.substring(usuario.length()-1,usuario.length());
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {

        }

        return (login);
    }
    /*
    public String loggerIo.guardarExcepcion (String parametro1, String parametro2,String parametro3,String parametro4,String parametro5,String parametro6) {
        final String origen = "BackOfficeIo.guardarExcepcion";
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
    }    */

    public TableOd consultarOpcionesEspeciales (String login, VBTUsersOd u, String tipo) throws Exception {
        final String origen = "loginIo.home";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        ResultSet resultOpciones = null;
        TableOd listaOpciones = null;
        List<String> tableHeader = null;
        List<String> tableCells = null;
        ArrayList<List<String>> tableRows = null;
        long time = 0;
        String respuesta = "";
        String cell = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_CONSULTAR_OPC_ESP(?,?,?,?,?); END;");
//
            if (u.getLogin() == null) {

                statement.setNull(1, OracleTypes.NULL);

            } else {

                statement.setString(1, login);

            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            if (tipo == null) {

                statement.setNull(5, OracleTypes.NULL);

            } else {

                statement.setString(5, tipo);

            }

            statement.execute ();
            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {

                throw (new Exception (respuesta+" mensaje",null));

            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            listaOpciones = new TableOd();
            tableHeader = new ArrayList();

            tableHeader.add("");
            tableHeader.add("<b>NOMBRE OPCION</b>");
            tableHeader.add("<b>CONSULTAR</b>");
            tableHeader.add("<b>AGREGAR</b>");
            tableHeader.add("<b>EDITAR</b>");
            tableHeader.add("<b>ELIMINAR</b>");
            tableHeader.add("<b>EDITAR ESTATUS</b>");
            tableHeader.add("<b>REINICIAR CLAVE</b>");
            tableHeader.add("<b>APROBAR</b>");
            tableHeader.add("<b>LIBERAR</b>");

            tableRows = new ArrayList();
            tableCells = new ArrayList();

            while (result.next()) {


                // if ("0".equalsIgnoreCase(result.getString("CODIGO_PADRE"))) {
                if (("0".equalsIgnoreCase(result.getString("CODIGO_PADRE")))||("999999".equalsIgnoreCase(result.getString("CODIGO_PADRE")))) {
//                    cell = "<img id='img" + result.getString("CODIGO") +
//                            "' onclick='abrirDetalleOpcEsp(\"" +
//                            result.getString("CODIGO") + "\")' " +
//                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
//                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />" +
                    cell = "<input type='hidden' id='" + result.getString("CODIGO") + "' value='' class='padre elemento'/>";

                    tableCells.add(cell);

                } else {

                    cell = "<input type='hidden' id='" +
                            result.getString("CODIGO") +
                            "' value='" +
                            result.getString("CODIGO_PADRE") +
                            "' class='hijo elemento'/>";

                    tableCells.add(cell);

                }

                cell = "<b>" +
                        ResourceBundle.getBundle("Comun2_ve_es").getString(result.getString("DESCRIPCION").replaceAll(" ","_")) +
                        "</b>";

                tableCells.add(cell);


                //if ("0".equalsIgnoreCase(result.getString("CODIGO_PADRE"))) {
                if (("0".equalsIgnoreCase(result.getString("CODIGO_PADRE")))||("999999".equalsIgnoreCase(result.getString("CODIGO_PADRE")))) {
                    // CONSULTAR

                    statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_LISTAR_CATOPC(?,?,?,?,?); END;");

                    statement.setString(1, result.getString("CODGRP"));
                    statement.setString(2, result.getString("CODIGO"));
                    statement.registerOutParameter(3, OracleTypes.CURSOR);
                    statement.registerOutParameter(4, OracleTypes.VARCHAR);
                    statement.registerOutParameter(5, OracleTypes.VARCHAR);

                    statement.execute();

                    resultOpciones = (ResultSet) statement.getObject(3);

                    if (!resultOpciones.next()) {
                        // CONSULTAR
                        cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // AGREGAR
                        cell = "<img id='2_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[2])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // EDITAR
                        cell = "<img id='3_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[3])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // ELIMINAR
                        cell = "<img id='4_" +
                                result.getString("CODIGO") +
                                "_" +
                                result.getString("ACCIONES") +
                                "_" +
                                login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[4])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // EDITAR ESTATUS
                        cell = "<img id='5_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[5])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // REINICIAR CLAVE
                        cell = "<img id='6_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[6])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // APROBAR
                        cell = "<img id='7_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[7])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // LIBERAR
                        cell = "<img id='8_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[8])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                    }else{


                        cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                                result.getString("ACCIONES") + "_" + login +
                                "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                            cell += "src='resources/images/unchecked.gif' ";
                        } else {
                            cell += "src='resources/images/checked.gif' ";
                        }
                        cell += " />";

                        tableCells.add(cell);

                        // AGREGAR

                        cell = "";

                        tableCells.add(cell);

                        // EDITAR

                        cell = "";

                        tableCells.add(cell);

                        // ELIMINAR

                        cell = "";

                        tableCells.add(cell);

                        // EDITAR ESTATUS

                        cell = "";

                        tableCells.add(cell);

                        // REINICIAR CLAVE

                        cell = "";

                        tableCells.add(cell);

                        // APROBAR

                        cell = "";

                        tableCells.add(cell);

                        // LIBERAR

                        cell = "";

                        tableCells.add(cell);
                    }

                } else {

                    // CONSULTAR
                    cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // AGREGAR
                    cell = "<img id='2_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[2])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // EDITAR
                    cell = "<img id='3_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[3])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // ELIMINAR
                    cell = "<img id='4_" +
                            result.getString("CODIGO") +
                            "_" +
                            result.getString("ACCIONES") +
                            "_" +
                            login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[4])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // EDITAR ESTATUS
                    cell = "<img id='5_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[5])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // REINICIAR CLAVE
                    cell = "<img id='6_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[6])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // APROBAR
                    cell = "<img id='7_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[7])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // LIBERAR
                    cell = "<img id='8_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[8])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                }

                tableRows.add(tableCells);
                tableCells = new ArrayList<String>();

            }

            listaOpciones = new TableOd();
            listaOpciones = TransformTable.getTable(tableHeader, tableRows);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);

        } catch (Exception ex) {

            logger.error(ex, ex);
            loggerIo.guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        } finally {

            closeJdbcObjects (connection, statement, result);

        }

        return (listaOpciones);
    }

    public TableOd consultarOpcionesEspeciales_respaldo (String login, VBTUsersOd u, String tipo) throws Exception {
        final String origen = "loginIo.home";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        TableOd listaOpciones = null;
        List<String> tableHeader = null;
        List<String> tableCells = null;
        ArrayList<List<String>> tableRows = null;
        long time = 0;
        String respuesta = "";
        String cell = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_CONSULTAR_OPC_ESP(?,?,?,?,?); END;");
//
            if (u.getLogin() == null) {

                statement.setNull(1, OracleTypes.NULL);

            } else {

                statement.setString(1, login);

            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            if (tipo == null) {

                statement.setNull(5, OracleTypes.NULL);

            } else {

                statement.setString(5, tipo);

            }

            statement.execute ();
            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {

                throw (new Exception (respuesta+" mensaje",null));

            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            listaOpciones = new TableOd();
            tableHeader = new ArrayList();

            tableHeader.add("");
            tableHeader.add("<b>NOMBRE OPCION</b>");
            tableHeader.add("<b>CONSULTAR</b>");
            tableHeader.add("<b>AGREGAR</b>");
            tableHeader.add("<b>EDITAR</b>");
            tableHeader.add("<b>ELIMINAR</b>");
            tableHeader.add("<b>EDITAR ESTATUS</b>");
            tableHeader.add("<b>REINICIAR CLAVE</b>");
            tableHeader.add("<b>APROBAR</b>");
            tableHeader.add("<b>LIBERAR</b>");

            tableRows = new ArrayList();
            tableCells = new ArrayList<String>();

            while (result.next()) {

                // if ("0".equalsIgnoreCase(result.getString("CODIGO_PADRE"))) {
                if (("0".equalsIgnoreCase(result.getString("CODIGO_PADRE")))||("999999".equalsIgnoreCase(result.getString("CODIGO_PADRE")))) {
//                    cell = "<img id='img" + result.getString("CODIGO") +
//                            "' onclick='abrirDetalleOpcEsp(\"" +
//                            result.getString("CODIGO") + "\")' " +
//                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
//                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />" +
                    cell = "<input type='hidden' id='" + result.getString("CODIGO") + "' value='' class='padre elemento'/>";

                    tableCells.add(cell);

                } else {

                    cell = "<input type='hidden' id='" +
                            result.getString("CODIGO") +
                            "' value='" +
                            result.getString("CODIGO_PADRE") +
                            "' class='hijo elemento'/>";

                    tableCells.add(cell);

                }

                cell = "<b>" +
                        ResourceBundle.getBundle("Comun2_ve_es").getString(result.getString("DESCRIPCION").replaceAll(" ","_")) +
                        "</b>";

                tableCells.add(cell);


                //if ("0".equalsIgnoreCase(result.getString("CODIGO_PADRE"))) {
                if (("0".equalsIgnoreCase(result.getString("CODIGO_PADRE")))||("999999".equalsIgnoreCase(result.getString("CODIGO_PADRE")))) {
                    // CONSULTAR

                    cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // AGREGAR

                    cell = "";

                    tableCells.add(cell);

                    // EDITAR

                    cell = "";

                    tableCells.add(cell);

                    // ELIMINAR

                    cell = "";

                    tableCells.add(cell);

                    // EDITAR ESTATUS

                    cell = "";

                    tableCells.add(cell);

                    // REINICIAR CLAVE

                    cell = "";

                    tableCells.add(cell);

                    // APROBAR

                    cell = "";

                    tableCells.add(cell);

                    // LIBERAR

                    cell = "";

                    tableCells.add(cell);

                } else {

                    // CONSULTAR
                    cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // AGREGAR
                    cell = "<img id='2_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[2])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // EDITAR
                    cell = "<img id='3_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[3])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // ELIMINAR
                    cell = "<img id='4_" +
                            result.getString("CODIGO") +
                            "_" +
                            result.getString("ACCIONES") +
                            "_" +
                            login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[4])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // EDITAR ESTATUS
                    cell = "<img id='5_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[5])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // REINICIAR CLAVE
                    cell = "<img id='6_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[6])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // APROBAR
                    cell = "<img id='7_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[7])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // LIBERAR
                    cell = "<img id='8_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[8])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                }

                tableRows.add(tableCells);
                tableCells = new ArrayList<String>();

            }

            listaOpciones = new TableOd();
            listaOpciones = TransformTable.getTable(tableHeader, tableRows);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);

        } catch (Exception ex) {

            logger.error(ex, ex);
            loggerIo.guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        } finally {

            closeJdbcObjects (connection, statement, result);

        }

        return (listaOpciones);
    }

    public TableOd consultarAccesos (String login, VBTUsersOd u, String tipo) throws Exception {
        final String origen = "BackOfficeIo.consultarAccesos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        TableOd listaOpciones = null;
        List<String> tableHeader = null;
        List<String> tableCells = null;
        ArrayList<List<String>> tableRows = null;
        long time = 0;
        String respuesta = "";
        String cell = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_CONSULTAR_OPC_ESP(?,?,?,?,?); END;");
//
            if (u.getLogin() == null) {

                statement.setNull(1, OracleTypes.NULL);

            } else {

                statement.setString(1, login);

            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);


            if (tipo == null) {

                statement.setNull(5, OracleTypes.NULL);

            } else {

                statement.setString(5, tipo);

            }

            statement.execute ();
            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {

                throw (new Exception (respuesta+" mensaje",null));

            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ LoginIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            listaOpciones = new TableOd();
            tableHeader = new ArrayList();

            tableHeader.add("");
            tableHeader.add("<b>NOMBRE OPCION</b>");
            tableHeader.add("<b>CONSULTAR</b>");
            tableHeader.add("<b>AGREGAR</b>");
            tableHeader.add("<b>EDITAR</b>");
            tableHeader.add("<b>ELIMINAR</b>");
            tableHeader.add("<b>EDITAR ESTATUS</b>");
            tableHeader.add("<b>REINICIAR CLAVE</b>");
            tableHeader.add("<b>APROBAR</b>");
            tableHeader.add("<b>LIBERAR</b>");

            tableRows = new ArrayList();
            tableCells = new ArrayList<String>();

            while (result.next()) {

                if ("0".equalsIgnoreCase(result.getString("CODIGO_PADRE"))) {

//                    cell = "<img id='img" + result.getString("CODIGO") +
//                            "' onclick='abrirDetalleOpcEsp(\"" +
//                            result.getString("CODIGO") + "\")' " +
//                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
//                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />" +
                    cell = "<input type='hidden' id='" + result.getString("CODIGO") + "' value='' class='padre elemento'/>";

                    tableCells.add(cell);

                } else {

                    cell = "<input type='hidden' id='" +
                            result.getString("CODIGO") +
                            "' value='" +
                            result.getString("CODIGO_PADRE") +
                            "' class='hijo elemento'/>";

                    tableCells.add(cell);

                }

                cell = "<b>" +
                        ResourceBundle.getBundle("Comun2_ve_es").getString(result.getString("DESCRIPCION").replaceAll(" ","_")) +
                        "</b>";

                tableCells.add(cell);

                if ("0".equalsIgnoreCase(result.getString("CODIGO_PADRE"))) {

                    // CONSULTAR

                    cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // AGREGAR

                    cell = "";

                    tableCells.add(cell);

                    // EDITAR

                    cell = "";

                    tableCells.add(cell);

                    // ELIMINAR

                    cell = "";

                    tableCells.add(cell);

                    // EDITAR ESTATUS

                    cell = "";

                    tableCells.add(cell);

                    // REINICIAR CLAVE

                    cell = "";

                    tableCells.add(cell);

                    // APROBAR

                    cell = "";

                    tableCells.add(cell);

                    // LIBERAR

                    cell = "";

                    tableCells.add(cell);

                } else {

                    // CONSULTAR
                    cell = "<img id='1_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[1])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // AGREGAR
                    cell = "<img id='2_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[2])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // EDITAR
                    cell = "<img id='3_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[3])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // ELIMINAR
                    cell = "<img id='4_" +
                            result.getString("CODIGO") +
                            "_" +
                            result.getString("ACCIONES") +
                            "_" +
                            login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[4])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // EDITAR ESTATUS
                    cell = "<img id='5_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[5])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // REINICIAR CLAVE
                    cell = "<img id='6_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[6])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // APROBAR
                    cell = "<img id='7_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[7])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                    // LIBERAR
                    cell = "<img id='8_" + result.getString("CODIGO") + "_" +
                            result.getString("ACCIONES") + "_" + login +
                            "' class='check' ";
//                            "' onclick='guardarOpcionEspecial(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(result.getString("ACCIONES").split("")[8])) {
                        cell += "src='resources/images/unchecked.gif' ";
                    } else {
                        cell += "src='resources/images/checked.gif' ";
                    }
                    cell += " />";

                    tableCells.add(cell);

                }

                tableRows.add(tableCells);
                tableCells = new ArrayList<String>();

            }

            listaOpciones = new TableOd();
            listaOpciones = TransformTable.getTable(tableHeader, tableRows);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ LoginIo.class+" | "+origen);

        } catch (Exception ex) {

            logger.error(ex, ex);
            loggerIo.guardarExcepcion(u.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), u.getLogin(), u.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        } finally {

            closeJdbcObjects (connection, statement, result);

        }

        return (listaOpciones);
    }




    public TableOd consultarOpcionesFC (VBTUsersOd usuarioSesion) throws Exception {

        final String origen = "BackOfficeIo.consultarOpcionesFC";
        long time = 0;
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resultCategory = null;
        ResultSet resultOption = null;
        TableOd listaOpciones = null;
        List<String> tableHeader = null;
        List<String> tableCells = null;
        ArrayList<List<String>> tableRows = null;
        String rowDetail = "";
        String rowSubdetail = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen);

            time = System.currentTimeMillis ();

            connection = getConnection();

            statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_CATOPC_PADRES(?,?,?); END;");


            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            if (!"SUCCESS".equalsIgnoreCase(statement.getString(3))) {
                logger.error(statement.getString(3),new Exception (statement.getString(2) + " - " + statement.getString(3)));
                throw (new Exception (statement.getString(2) + " - " + statement.getString(3)));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen + " | 0 respuesta de bd exitosa");

            resultCategory = (ResultSet) statement.getObject(1);

            listaOpciones = new TableOd();
            tableHeader = new ArrayList();
            tableHeader.add("");
            tableHeader.add("<b>NOMBRE OPCIONES</b>");
            tableHeader.add("<b>CONSULTAR</b>");
            tableHeader.add("<b>AGREGAR</b>");
            tableHeader.add("<b>EDITAR</b>");
            tableHeader.add("<b>ELIMINAR</b>");
            tableHeader.add("<b>EDITAR ESTATUS</b>");
            tableHeader.add("<b>REINICIAR CLAVE</b>");
            tableHeader.add("<b>APROBAR</b>");
            tableHeader.add("<b>LIBERAR</b>");
            tableRows = new ArrayList();

            while (resultCategory.next()) {

                tableCells = new ArrayList();

                statement = connection.prepareCall ("BEGIN BACKOFFICE.BAC_PRC_CATOPC_HIJOS(?,?,?,?); END;");

                statement.setString(1, resultCategory.getString("CODOPC"));
                statement.registerOutParameter(2, OracleTypes.CURSOR);
                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.registerOutParameter(4, OracleTypes.VARCHAR);

                statement.execute();

                resultOption = (ResultSet) statement.getObject(2);

                if (!resultOption.next()) {

                    rowDetail = "<img id='img" + resultCategory.getString("CODOPC") + "' onclick='abrirDetalle(\"" +
                            resultCategory.getString("CODOPC") + "\")' " +
                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />";
                    rowDetail += "<input type='hidden' id='" + resultCategory.getString("CODOPC") + "' value='' />";
                    tableCells.add("");

                    rowDetail = "<b>" +
                            ResourceBundle.getBundle("Comun2_ve_es").getString(resultCategory.getString("DESOPC")) +
                            "</b>";

                    tableCells.add(rowDetail);

                    // CONSULTAR
                    rowDetail = "<b><img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // AGREGAR
                    rowDetail = "<b><img id='2_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[2])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // EDITAR
                    rowDetail = "<b><img id='3_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[3])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // ELIMINAR
                    rowDetail = "<b><img id='4_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[4])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // EDITAR ESTATUS
                    rowDetail = "<b><img id='5_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[5])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // REINICIAR CLAVE
                    rowDetail = "<b><img id='6_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[6])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // APROBAR
                    rowDetail = "<b><img id='7_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[7])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);

                    // LIBERAR
                    rowDetail = "<b><img id='8_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";

                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[8])) {
//                            rowDetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowDetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " /></b>";

                    tableCells.add(rowDetail);
                    tableRows.add(tableCells);
                    tableCells = new ArrayList();

                } else{

                    rowDetail = "<img id='img" + resultCategory.getString("CODOPC") + "' onclick='abrirDetalle(\"" +
                            resultCategory.getString("CODOPC") + "\")' " +
                            "class='btn_descripcion_cuenta_BT' style='cursor:pointer;cursor: hand;' " +
                            "src='resources/images/comun/nolines_plus.gif' width='9' height='9' />";
                    rowDetail += "<input type='hidden' id='" + resultCategory.getString("CODOPC") + "' value='' />";
                    tableCells.add(rowDetail);

                    rowDetail = "<b>" +
                            ResourceBundle.getBundle("Comun2_ve_es").getString(resultCategory.getString("DESOPC")) +
                            "</b>";

                    tableCells.add(rowDetail);

                    // CONSULTAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "<img id='1_" + resultCategory.getString("CODOPC") + "_" +
                            resultCategory.getString("TIPACC") + "_" + resultCategory.getString("CODOPC") +
                            "' onclick='guardarOpcionFC(this.id)' class='check' ";
                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                        rowDetail += "src='resources/images/unchecked.gif' ";
                    } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                        rowDetail += "src='resources/images/checked.gif' ";
                    }
                    rowDetail += " />";


                    tableCells.add(rowDetail);

                    // AGREGAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[2])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // EDITAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[3])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // ELIMINAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[4])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // EDITAR ESTATUS
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[5])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // REINICIAR CLAVE
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[6])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // APROBAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[7])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);

                    // LIBERAR
//                    if ("0".equalsIgnoreCase(resultCategory.getString("TIPACC").split("")[8])) {
//                        rowDetail = "<b><img src='resources/images/unchecked.gif' /></b>";
//                    } else {
//                        rowDetail = "<b><img src='resources/images/checked.gif' /></b>";
//                    }

                    rowDetail = "";

                    tableCells.add(rowDetail);
                    tableRows.add(tableCells);
                    tableCells = new ArrayList<String>();

                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + " | " + origen + " | 0 respuesta de bd exitosa");

                    do {

                        tableCells.add("<input type='hidden' id='" + resultOption.getString("CODOPC") + "' value='" + resultCategory.getString("CODOPC") + "' /><script type='text/javascript'>ocultarFila('" + resultOption.getString("CODOPC") +",');</script>");

                        rowSubdetail = ResourceBundle.getBundle("Comun2_ve_es").getString(resultOption.getString("DESOPC").replaceAll(" ","_"));

                        tableCells.add(rowSubdetail);

                        // CONSULTAR
                        rowSubdetail = "<img id='1_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[1])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // AGREGAR
                        rowSubdetail = "<img id='2_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[2])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // EDITAR
                        rowSubdetail = "<img id='3_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[3])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // ELIMINAR
                        rowSubdetail = "<img id='4_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[4])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // EDITAR ESTATUS
                        rowSubdetail = "<img id='5_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[5])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // REINICIAR CLAVE
                        rowSubdetail = "<img id='6_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_" + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[6])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // APROBAR
                        rowSubdetail = "<img id='7_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";
                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[7])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);

                        // LIBERAR
                        rowSubdetail = "<img id='8_" + resultOption.getString("CODOPC") + "_" +
                                resultOption.getString("TIPACC") + "_"  + resultCategory.getString("CODOPC") +
                                "' onclick='guardarOpcionFC(this.id)' class='check' ";

                        if ("0".equalsIgnoreCase(resultOption.getString("TIPACC").split("")[8])) {
//                            rowSubdetail += "value='0' ";
                            rowSubdetail += "src='resources/images/unchecked.gif' ";
                        } else {
//                            rowSubdetail += "value='1' checked='checked' ";
                            rowSubdetail += "src='resources/images/checked.gif' ";
                        }
                        rowSubdetail += " />";

                        tableCells.add(rowSubdetail);
                        tableRows.add(tableCells);
                        tableCells = new ArrayList<String>();

                    } while (resultOption.next());

                }

            }

            listaOpciones = new TableOd();
            listaOpciones = TransformTable.getTable(tableHeader, tableRows);

        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        } finally {
            closeJdbcObjects (null, null, resultOption);
            closeJdbcObjects (connection, statement, resultCategory);
        }

        return (listaOpciones);
    }


    public String actualizarOpcionesSistemaFC(List<String> acciones, VBTUsersOd usuarioSesion) throws Exception {
        final String origen = "BackOfficeIo.actualizarOpcionesSistemaFC";
        Connection connection = null;
        CallableStatement statement = null;
        String result = "";

        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            connection=getConnection();

            statement  = connection.prepareCall("BEGIN BACKOFFICE.BAC_PRC_ACTUALIZAR_OPCACC(?,?,?,?,?); END;");

            statement.setString(1, usuarioSesion.getLogin());
            statement.setString(2, acciones.get(1));
            statement.setString(3, acciones.get(2));

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);

            statement.execute ();

            result = statement.getString(5);

            if (!result.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                throw (new Exception (result,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            connection.commit();

        } catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuarioSesion.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuarioSesion.getLogin(), usuarioSesion.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        } finally {
            closeJdbcObjects(connection, statement, null);
        }

        return result;
    }

    public TableOd  buscarBanco (List<String> parametros, String idioma, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.buscarBanco";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBTONLINE.VBT_BankCodeBuscar_pr(?,?,?,?,?,?,?,?); END;");

            if(parametros.get(0).equalsIgnoreCase("-2"))
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

            if (parametros.get(4).equalsIgnoreCase("-2"))
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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (6);

            List<List<String>> bodys=new ArrayList();


            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add(NullFormatter.formatHtmlBlank("<b><a id='codigobanco_"+result.getString(4)+"' style='cursor: pointer'>"+result.getString(4)+"</a><b>"));
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add("<b><a style='cursor: pointer' onclick='cargraSucursales(\""+result.getString(4)+"\",\""+result.getString(6)+"\")'>"+NullFormatter.formatHtmlBlank(result.getString(5))+"</a></b>");
                if (parametros.get(4).equalsIgnoreCase("A")){
                    body.add("<input id='"+result.getString(4)+"' class='botonMinRojo' value ='Inactivar' type='button'  onclick='desactivarBanco(this.id,\""+parametros.get(0)+"\","+result.getString(5)+")'>");
                }else{
                    body.add("<input id='"+result.getString(4)+"' class='botonMin' value ='activar' type='button'  onclick='activarBanco(this.id,\""+parametros.get(0)+"\","+result.getString(5)+")'>");
                }


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
            header.add(ResourceBundle.getBundle(propertie).getString("TAGPais"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGCantidad"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGAccionBO"));


            listaTransferencias=new TableOd();

            listaTransferencias= TransformTable.getTable(header, bodys);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaTransferencias);
    }

    public String  inactivarBanco (String codigo, String tipo, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.inactivarBanco";
        Connection connection = null;
        CallableStatement statement = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBTONLINE.VBT_EXCLUIR_BANCO(?,?,?,?); END;");


            if(codigo == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,codigo);

            if (tipo== null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,tipo);

            if (usuario.getLogin()== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuario.getLogin());


            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }

        return (respuesta);
    }

    public String  activarBanco (String codigo, String tipo, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.activarBanco";
        Connection connection = null;
        CallableStatement statement = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBTONLINE.VBT_INCLUIR_BANCO(?,?,?,?); END;");


            if(codigo == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,codigo);

            if (tipo== null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,tipo);

            if (usuario.getLogin()== null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,usuario.getLogin());


            statement.registerOutParameter(4, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }

        return (respuesta);
    }

    public SelectOd cargarTarjetasTDCCL (String carteras,String exacta, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeIo.cargarTarjetasTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> tarjetas= new ArrayList<ContentSelectOd>();
        ContentSelectOd tarjeta = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CARGAR_TARJETAS(?,?,?,?,?); END;");

            if(carteras== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, carteras);

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.setString(4, "B");

            statement.setString(5, exacta);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);

            while (result.next()){

                valor = new String();
                label  = new String();

                valor = NullFormatter.formatBlank(result.getString(1));
                label  = NullFormatter.formatBlank(result.getString(3)) + " (" + ResourceBundle.getBundle("TdcMovimientosxFacturar_ve_es").getString("TAGCartera")+ ": " + NullFormatter.formatBlank(result.getString(2)) + ")";

                tarjeta.setLabel(label);
                tarjeta.setValue(valor);
                tarjetas.add(tarjeta);
                tarjeta=new ContentSelectOd();

            }

            select.setContenido(tarjetas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public TableOd  buscarAvisos (List<String> parametros, VBTUsersOd usuario, String idioma) throws Exception {
        final String origen = "BackOfficeIo.buscarAvisos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();


            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBTONLINE.VBT_AVISOS_PR(?,?,?,?,?,?,?,?); END;");

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

            if (parametros.get(4).equalsIgnoreCase(""))
                statement.setString(8,"1");
            else
                statement.setString(8,parametros.get(4));

            statement.execute ();

            respuesta = statement.getString(6);
            String sql = statement.getString(7);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (5);

            List<List<String>> bodys=new ArrayList();

            String propertie = new String();

            if(idioma.equalsIgnoreCase("_ve_es")){
                propertie = "Transferencias"+idioma;
            }else
                propertie = "Transferencias"+idioma;

            while (result.next ()) {
                List<String> body=new ArrayList<String>();
                body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif'  onclick='abrirDetalleTablaAvisos(this)' width='9' height='9' id='AVISO|"+NullFormatter.formatHtmlBlank(result.getString(3)).replace("'","=")+"|"+NullFormatter.formatHtmlBlank(result.getString(4)).replace("'","=")+"' class='detalleBusquedaAviso' type='button' value='+'   >");
                //body.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif'  onclick='abrirDetalleTablaAvisos(this)' width='9' height='9' id='AVISO|"+" "+"|"+" "+"' class='detalleBusquedaAviso' type='button' value='+'   >");

                body.add("<a style='cursor:pointer' id='"+NullFormatter.formatHtmlBlank(result.getString(1)) +"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(3)).replace("'","=")+"|"+NullFormatter.formatHtmlBlank(result.getString(4)).replace("'","=")+"|"+NullFormatter.formatHtmlBlank(result.getString(5))+"|"+NullFormatter.formatHtmlBlank(result.getString(6))+"|"+result.getString(7)+"|"+result.getString(8)+"' onclick='seleccionarAvisoOpcion(this.id)' value='"+ result.getString(2)+"'><b>"+result.getString(2)+"<b></a>");
//              body.add("<a style='cursor:pointer' id='"+result.getString(1) +"|"+result.getString(2)+"|"+result.getString(3)+"|"+result.getString(4)+"|"+result.getString(5)+"|"+result.getString(6)+"|"+result.getString(7)+"|"+result.getString(8)+"' onclick='seleccionarAvisoOpcion(this.id)' value='"+ result.getString(2)+"'><b>"+result.getString(2)+"<b></a>");
                body.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                body.add(ResourceBundle.getBundle(propertie).getString("TAGTipo"+NullFormatter.formatHtmlBlank(result.getString(7))));
                body.add(ResourceBundle.getBundle(propertie).getString("TAGEstatus"+NullFormatter.formatHtmlBlank(result.getString(8))));
                bodys.add(body);
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add(ResourceBundle.getBundle(propertie).getString("TAGTextoBO"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGFechaInicioBO"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGFechaFinBO"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGTipoBO"));
            header.add(ResourceBundle.getBundle(propertie).getString("TAGEstatusBO"));
            listaTransferencias=new TableOd();

            listaTransferencias= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaTransferencias);
    }



    public Map<String,Object> cargarEstatusTDCCL (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.cargarEstatusTDCCL";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        Map<String,Object> estatus = null;
        TableOd lista = null;
        String fecha = new String();
        String respuesta = "";
        List<List<String>> filas = null;
        List<String> celdas = null;
        List<String> columnas = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CONSULTAR_ESTATUS(?,?,?,?,?,?,?,?); END;");

            if(parametros.get(0)==null){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(0));
            }

            if(parametros.get(1)==null){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, parametros.get(1).split("_")[0]);
            }

            if(parametros.get(2)==null){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(1).split("_")[2]);
            }

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.CURSOR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);
            statement.registerOutParameter(8, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(8);

            if (!respuesta.startsWith("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            estatus = new HashMap<String, Object>();

            respuesta = statement.getString(4);

            estatus.put("estatus",respuesta);

            result = (ResultSet) statement.getObject(5);

            filas = new ArrayList();

            while (result.next()){

                celdas = new ArrayList<String>();
                String id="";
                celdas.add("<table id='tabla1_"+result.getString(4)+"'><tr><td>"+result.getString(1)+"<table><tr><td>");
                celdas.add("<table id='tabla2_"+result.getString(4)+"'><tr><td>"+result.getString(2)+"<table><tr><td>");
//                celdas.add(result.getString(2));
                id=result.getString(4)+"|"+result.getString(5)+"|"+result.getString(6)+"|"+result.getString(7)+"|"+result.getString(1)+"|"+result.getString(2)+"|"+ ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_EST_" + result.getString(3))+"|"+result.getString(3);

//                celdas.add("<span id='TAG_EST_" + result.getString(3) + "'>" + ResourceBundle.getBundle("vbtonline"+idioma).getString("TAG_EST_" + result.getString(3)) + "</span>");
                celdas.add("<span>" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_EST_" + result.getString(3)) + "</span>");

                SimpleDateFormat formatoFecha;
                formatoFecha=new SimpleDateFormat("dd/MM/yyyy");
                String fechaHoy=formatoFecha.format(new Date()).toString();


                celdas.add(result.getString(8)+ "<BR>"+result.getString(10));
                celdas.add(result.getString(9));

                //if (result.getString(11).trim().equalsIgnoreCase("B")){


                if (result.getString(3).equalsIgnoreCase("P")) {

                    //Valida que no se pueda editar una regla que vence el midmo dia
                    //if (fechaHoy.equalsIgnoreCase(result.getString(2))){
//                            celdas.add("<table align='center'><tr><td></td></tr></table>");
                    //celdas.add("<table align='center'><tr><td><img id='del|"+id+"'  style='cursor:pointer'  src='resources/images/bin.png' title='Eliminar período de activación' onclick='eliminarReglaMainBO(this.id);' /></td></tr></table>");
                    //}else{
//                            celdas.add("<table align='center'><tr><td></td></tr></table>");
                    celdas.add("<table align='center'><tr><td><img  id='"+id+"' style='cursor:pointer' src='resources/images/edit.png' title='Modificar período de activación' onclick='editarReglaBO(this.id)' /></td><td><img id='del|"+id+"' src='resources/images/bin.png' style='cursor:pointer' title='Eliminar período de activación' onclick='eliminarReglaMainBO(this.id);' /></td></tr></table>");
                    //}

                } else {

                    if (result.getString(3).equalsIgnoreCase("E")) {
                        if (fechaHoy.equalsIgnoreCase(result.getString(2))){
                            celdas.add("<table align='center'><tr><td></td></tr></table>");
                        }else{

//                                celdas.add("<table align='center'><tr><td></td></tr></table>");

                            celdas.add("<table align='center'><tr><td><img  id='"+id+"' style='cursor:pointer'  src='resources/images/edit.png' title='Modificar período de activación' onclick='editarReglaBO(this.id)' /></td></td></tr></table>");
                        }

                    }else{
                        celdas.add("");

                    }
                }
                //}else{
                //     celdas.add("");
                // }



                filas.add(celdas);
            }

            columnas = new ArrayList<String>();

            columnas.add("TAG_FEC_INICIAL#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_FEC_INICIAL"));
            columnas.add("TAG_FEC_FINAL#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_FEC_FINAL"));
            columnas.add("TAG_ESTATUS#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_ESTATUS"));
            columnas.add("TAG_USUARIO#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_USUARIO"));
            columnas.add("TAG_FECHA_CARGA#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_FECHA_CARGA"));
            columnas.add("TAG_ACCION#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_ACCION"));

            lista = new TableOd();

            lista = TransformTable.getTable(columnas, filas);

            estatus.put("lista_activas",lista);

            result = (ResultSet) statement.getObject(6);

            filas = new ArrayList();

            while (result.next()){

                celdas = new ArrayList<String>();

                celdas.add(result.getString(1));
                celdas.add(result.getString(2));
                celdas.add("<span>" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_EST_" + result.getString(3)) + "</span>");
                celdas.add(result.getString(4)+"<BR>"+result.getString(6));
                celdas.add(result.getString(5));

                filas.add(celdas);
            }

            columnas = new ArrayList<String>();

            columnas.add("TAG_HIST_FEC_INICIAL#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_FEC_INICIAL"));
            columnas.add("TAG_HIST_FEC_FINAL#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_FEC_FINAL"));
            columnas.add("TAG_ESTATUS#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_ESTATUS"));
            columnas.add("TAG_USUARIO#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_USUARIO"));
            columnas.add("TAG_FECHA_CARGA#" + ResourceBundle.getBundle("vbtonline_ve_es").getString("TAG_FECHA_CARGA"));

            lista = new TableOd();

            lista = TransformTable.getTable(columnas, filas);

            estatus.put("lista_historico",lista);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {

            logger.error(ex,ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (estatus);
    }


    public Map<String,Object> consultarPagosTDC (List<String> parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.consultarPagosTDC";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        Map<String,Object> estatus = null;
        TableOd lista = null;
        String fecha = new String();
        String respuesta = "";
        List<List<String>> filas = null;
        List<String> celdas = null;
        List<String> columnas = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CONSULTAR_PAGOS_TDC(?,?,?,?,?,?,?); END;");

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

            if(parametros.get(4)==null){
                statement.setNull(4, OracleTypes.NULL);
            }else{
                statement.setString(4, parametros.get(4));
            }

            if(parametros.get(5)==null){
                statement.setNull(5, OracleTypes.NULL);
            }else{
                statement.setString(5, parametros.get(5));
            }

            statement.registerOutParameter(6, OracleTypes.CURSOR);
            statement.registerOutParameter(7, OracleTypes.VARCHAR);


            statement.execute ();

            respuesta = statement.getString(7);

            if (!respuesta.startsWith("OK")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            estatus = new HashMap<String, Object>();

            lista = new TableOd();

            filas = new ArrayList();

            result = (ResultSet) statement.getObject(6);

            while (result.next()){

                celdas = new ArrayList<String>();
                String id=NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(4));
                celdas.add("<img style='cursor:pointer' src='resources/images/comun/nolines_plus.gif'  onclick='abrirDetalleTablaPagos(this)' width='9' height='9' id='"+id+"' class='detalleBusquedaAviso' type='button' value='+'   >");
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(7)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(9)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(8)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(10)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(11)));

                filas.add(celdas);
            }

            columnas = new ArrayList<String>();

            columnas.add("");
            columnas.add("Fecha Registro");
            columnas.add("Tipo Pago");
            columnas.add("Fecha Proceso");
            columnas.add("Monto");
            columnas.add("Tipo Moneda");
            columnas.add("Estatus");
            columnas.add("Cantidad de Intentos");




            lista = TransformTable.getTable(columnas, filas);

            estatus.put("lista_pagos",lista);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {

            logger.error(ex,ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (estatus);
    }





    public String crearAviso (List<String> parametros, byte[] imagen, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.crearAviso";
        Connection connection = null;
        CallableStatement statement = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_CREAR_AVISO(?,?,?,?,?,?,?,?,?,?); END;");

            if(parametros.get(0) == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if(parametros.get(1) == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));


            if(parametros.get(2) == null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));

            if(parametros.get(3) == null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.get(3));

            if(parametros.get(4) == null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,parametros.get(4));

            if(parametros.get(5) == null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,parametros.get(5));

            if(parametros.get(6) == null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,parametros.get(6));

            if(imagen == null) {
                statement.setNull(8, OracleTypes.BLOB);
            }else{
                statement.setBytes(8,imagen);
            }
            if(usuario.getLogin() == null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,usuario.getLogin());

            statement.registerOutParameter(10, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(10);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }

        return (respuesta);
    }


    public String validarAvisos (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeIo.validarAvisos";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN BACKOFFICE.PR_VALIDAR_FECHA_AVISOS(?,?,?,?,?,?,?); END;");

            statement.setString(1, parametros.get(0));
            statement.setString(2, parametros.get(1));
            statement.setString(3, parametros.get(2));
            statement.setString(4, parametros.get(3));
            statement.setString(5, parametros.get(4));

            if (parametros.get(4).equalsIgnoreCase("edit")){
                statement.setString(6, parametros.get(5));
            }else{
                statement.setString(6, "");
            }

            statement.registerOutParameter(7, OracleTypes.VARCHAR);

            statement.execute();

            respuesta = statement.getString(7);
            if (!respuesta.equalsIgnoreCase("OK")) {
                respuesta= statement.getString(7);
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public String editarAviso (List<String> parametros, byte[] imagen, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.editarAviso";
        Connection connection = null;
        CallableStatement statement = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        TableOd listaTransferencias = null;
        String respuesta=null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_EDITAR_AVISO(?,?,?,?,?,?,?,?,?,?,?,?); END;");

            if(parametros.get(0) == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1,parametros.get(0));

            if(parametros.get(1) == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2,parametros.get(1));


            if(parametros.get(2) == null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3,parametros.get(2));

            if(parametros.get(3) == null)
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4,parametros.get(3));

            if(parametros.get(4) == null)
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5,parametros.get(4));

            if(parametros.get(5) == null)
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6,parametros.get(5));

            if(parametros.get(6) == null)
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7,parametros.get(6));


            statement.setBytes(8,imagen);

            if(parametros.get(8) == null)
                statement.setNull(9, OracleTypes.NULL);
            else
                statement.setString(9,parametros.get(8));

            if(parametros.get(9) == null)
                statement.setNull(10, OracleTypes.NULL);
            else
                statement.setString(10,parametros.get(9));

            if(usuario.getLogin() == null)
                statement.setNull(11, OracleTypes.NULL);
            else
                statement.setString(11,usuario.getLogin());

            statement.registerOutParameter(12, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(12);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, null);
        }

        return (respuesta);
    }



    public String eliminarReglaBO (List<String> parametros, VBTUsersOd usersOd) throws Exception {
        final String origen = "BackOfficeIo.eliminarReglaBO";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta="WRONG";
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_ELIMINAR_REGLA(?,?); END;");

            statement.setString(1, parametros.get(0));

            statement.registerOutParameter(2, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(2);

            if (!respuesta.equalsIgnoreCase("SUCCESS")) {
                connection.rollback();
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else{
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }

    public TableOd consultarCuentasNoPermitidas (ConsultCtasNoPermitidasOd consulta, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.consultarCuentasNoPermitidas";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta=null;
        TableOd listaUsuarios = null;

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_CONSULTAR_CTA_NOPERMITIDA(?,?,?,?,?,?); END;");

            if(consulta.getStrCodigoBanco() == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, consulta.getStrCodigoBanco());

            if(consulta.getStrNumeroCuenta() == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, consulta.getStrNumeroCuenta());

            statement.registerOutParameter(3, OracleTypes.CURSOR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(4);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject(3);

            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            int con = 0;
            while (result.next()){
                body = new ArrayList<String>();
                body.add("<input type='checkbox' name='"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(3))+"' value='"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(3))+"' id='cuentaNoPermitida_"+con+"' class='datosCCNP 0000000094_2' />");
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                bodys.add(body);
                con++;
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Codigo del banco");
            header.add("Tipo Codigo");
            header.add("Nombre del banco");
            header.add("Numero de Cuenta");

            listaUsuarios=new TableOd();

            listaUsuarios= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);
        }catch (Exception ex){
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public String crearCuentaNoPermitida (ConsultCtasNoPermitidasOd parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.crearCuentaNoPermitida";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta = "";

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            connection.setAutoCommit(false);
            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_INGRESAR_CTA_NO_PERMITIDA(?,?,?,?); END;");

            if( parametros.getStrCodigoBanco() == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, parametros.getStrCodigoBanco());

            if(parametros.getStrNumeroCuenta() == null)
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.getStrNumeroCuenta());

            if(parametros.getStrCodigoTipoBanco() == null)
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, parametros.getStrCodigoTipoBanco());

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(4);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            time = System.currentTimeMillis () - time;

            connection.commit();
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }catch (Exception ex){
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen, "Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }

    public String editarEstatusCuentaNoPermitida (List<ConsultCtasNoPermitidasOd> lista, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.editarEstatusCuentaNoPermitida";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta = "";

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            connection.setAutoCommit(false);

            ConsultCtasNoPermitidasOd cuenta;

            for (int a = 0 ; a < lista.size() ; a++ ){
                cuenta = (ConsultCtasNoPermitidasOd) lista.get(a);

                statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_MODIFICAR_CTANOPERMITIDA(?,?,?); END;");

                if (cuenta.getStrCodigoBanco() == null)
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, cuenta.getStrCodigoBanco());

                if (cuenta.getStrNumeroCuenta() == null)
                    statement.setNull(2, OracleTypes.NULL);
                else
                    statement.setString(2, cuenta.getStrNumeroCuenta());

                statement.registerOutParameter(3, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(3);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }catch (Exception ex){
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen, "Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }


    public TableOd consultarTransaccionesEspeciales (VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.consultarTransaccionesEspeciales";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta=null;
        TableOd listaTransacciones = null;

        try{
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_CONSULTAR_TRANS_ESP(?,?,?,?); END;");

            statement.registerOutParameter(1, OracleTypes.CURSOR);
            statement.registerOutParameter(2, OracleTypes.VARCHAR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);
            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(2);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject(1);

            List<List<String>> bodys=new ArrayList();
            List<String> body=new ArrayList<String>();
            int con = 0;
            while (result.next()){
                body = new ArrayList<String>();
                body.add("<input type='checkbox' name='"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(3))+"' value='"+NullFormatter.formatHtmlBlank(result.getString(1))+"|"+NullFormatter.formatHtmlBlank(result.getString(2))+"|"+NullFormatter.formatHtmlBlank(result.getString(3))+"' id='transaccionEspecial_"+con+"' class='datosCTE 0000000097_2' />");
                body.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(9)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(5)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(6)));
                body.add(NullFormatter.formatHtmlBlank(result.getString(7)));
                bodys.add(body);
                con++;
            }

            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Tipo Movimiento");
            header.add("Código Banco");
            header.add("Swift Banco");
            header.add("Numero de Cuenta");
            header.add("Dirección");
            header.add("Ciudad");
            header.add("País");


            listaTransacciones=new TableOd();

            listaTransacciones= TransformTable.getTable(header, bodys);

            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);
        }catch (Exception ex){
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }

        return (listaTransacciones);
    }

    public String crearTransaccionEspecial (ConsultaTransaccionesEspecialesOd parametros, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.crearTransaccionEspecial";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta = "";

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            connection.setAutoCommit(false);
            statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_INGRESAR_TRANS_ESP(?,?,?,?,?,?,?,?,?,?); END;");

            if( parametros.getStrTipoMov() == null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, parametros.getStrTipoMov());

            if( parametros.getStrCodigoBanco().equals(""))
                statement.setNull(2, OracleTypes.NULL);
            else
                statement.setString(2, parametros.getStrCodigoBanco());

            if(parametros.getStrNumeroCuenta().equals(""))
                statement.setNull(3, OracleTypes.NULL);
            else
                statement.setString(3, parametros.getStrNumeroCuenta());

            if(parametros.getStrAddressBanco().equals(""))
                statement.setNull(4, OracleTypes.NULL);
            else
                statement.setString(4, parametros.getStrAddressBanco());

            if(parametros.getStrCityBanco().equals(""))
                statement.setNull(5, OracleTypes.NULL);
            else
                statement.setString(5, parametros.getStrCityBanco());

            if(parametros.getStrCountryBanco().equals(""))
                statement.setNull(6, OracleTypes.NULL);
            else
                statement.setString(6, parametros.getStrCountryBanco());

            if(parametros.getStrPostalCodeBanco().equals(""))
                statement.setNull(7, OracleTypes.NULL);
            else
                statement.setString(7, parametros.getStrPostalCodeBanco());

            if(parametros.getStrSwiftBanco().equals(""))
                statement.setNull(8, OracleTypes.NULL);
            else
                statement.setString(8, parametros.getStrSwiftBanco());

            statement.setInt(9, parametros.getShowAccount());

            statement.registerOutParameter(10, OracleTypes.VARCHAR);
            statement.execute();

            respuesta = statement.getString(10);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            time = System.currentTimeMillis () - time;

            connection.commit();
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }catch (Exception ex){
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen, "Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }

    public String eliminarTransaccionEspecial(List<ConsultaTransaccionesEspecialesOd> lista, VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.editarEstatusTransaccionEspecial";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long time = 0;
        String respuesta = "";

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis();

            connection=getConnection();
            connection.setAutoCommit(false);

            ConsultaTransaccionesEspecialesOd cuenta;

            for (int a = 0 ; a < lista.size() ; a++ ){
                cuenta = (ConsultaTransaccionesEspecialesOd) lista.get(a);

                statement  = connection.prepareCall ("BEGIN BACKOFFICE.BAC_ELIMINAR_TRANS_ESP(?,?); END;");

                statement.setInt(1, cuenta.getCodTipoMov());
                statement.registerOutParameter(2, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(2);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
            }
            connection.commit();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }catch (Exception ex){
            logger.error(ex);
            loggerIo.guardarExcepcion (usuario.getIP(), ex.getMessage(), origen, "Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }

        return respuesta;
    }

    public SelectOd cargarElementosTiposExtra (String tipo) throws Exception {
        final String origen = "BackOfficeIo.cargarElementosTiposExtra";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        String codigo, mensaje=null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        List<ContentSelectOd> Elementos= new ArrayList<ContentSelectOd>();
        ContentSelectOd elemento = new ContentSelectOd();
        SelectOd select=new SelectOd();
        String label, valor;
        String respuesta=null;
        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ TransfersIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_elementostipo_pr(?,?,?); end;");

            if(tipo!=null){
                statement.setString(1, tipo);
            }else{
                statement.setNull(1, OracleTypes.NULL);
            }

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.registerOutParameter(3, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(3);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();
            while (result.next()){
                elemento.setLabel(result.getString(2));
                elemento.setValue(result.getString(1));
                elemento.setExtra(result.getString(3));
                Elementos.add(elemento);
                elemento=new ContentSelectOd();
            }

            select.setContenido(Elementos);

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


    public SelectOd cargarMotivosCLE (String estatusTarjeta,   VBTUsersOd usuario) throws Exception {
        final String origen = "BackOfficeIo.estatusTarjeta";
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
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_CONSULTAR_PROCESSING_LIST(?,?); END;");

            if(estatusTarjeta== null)
                statement.setNull(1, OracleTypes.NULL);
            else
                statement.setString(1, "TIPO_BLOQUEO");

            statement.registerOutParameter(2, OracleTypes.CURSOR);
            statement.execute ();

            result = (ResultSet) statement.getObject (2);
            label = new String();
            valor = new String();

            while (result.next()){
                valor = new String();
                label  = new String();

                if(NullFormatter.formatBlank(result.getString(8)).equals("S")) {
                    valor = NullFormatter.formatBlank(result.getString(1)) + "|" + NullFormatter.formatBlank(result.getString(2));
                    label =  NullFormatter.formatBlank(result.getString(5));

                    cuenta.setLabel(label);
                    cuenta.setValue(valor);
                    cuentas.add(cuenta);
                }

                cuenta = new ContentSelectOd();

            }
            select.setContenido(cuentas);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);
        }
        catch (Exception ex) {
            logger.error(ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen, "Archivo: " + ex.getStackTrace()[0].getFileName() + "    Metodo: " + ex.getStackTrace()[0].getMethodName() + "   Linea: " + ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));

        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public Map<String,Object> consultarTarjetasBloqueo (String carteras, List<String> parametros,VBTUsersOd usuario,List<PrivilegioOd> listaAcciones) throws Exception {
        final String origen = "BackOfficeIo.consultarTarjetasBloqueo";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        Map<String,Object> estatus = null;
        TableOd lista = null;
        String fecha = new String();
        String respuesta = "";
        List<List<String>> filas = null;
        List<String> celdas = null;
        List<String> columnas = null;
        try {

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);

            time = System.currentTimeMillis ();

            connection=getConnection();


            /*


            PR_BLOQUEO_EMERGENCIA_BO (I_CODCAR       IN     VARCHAR2,
                                   I_NUM_CTA           IN     VARCHAR2,
                                   I_NUM_DOC           IN     VARCHAR2,
                                   O_ESTATUS           OUT    VARCHAR2,
                                   O_MENSAJE           OUT    VARCHAR2,
                                   O_LISTA_EMERGENCIA        OUT SYS_REFCURSOR


             */

            statement  = connection.prepareCall ("BEGIN VBT_TARJETA.PCK_TARJETA.PR_BLOQUEO_EMERGENCIA_BO(?,?,?,?,?,?); END;");

            if(NullFormatter.isBlank(parametros.get(3))){
                statement.setNull(1, OracleTypes.NULL);
            }else{
                statement.setString(1, parametros.get(3));
            }

            if(NullFormatter.isBlank(parametros.get(2))){
                statement.setNull(2, OracleTypes.NULL);
            }else{
                statement.setString(2, parametros.get(2));
            }

            if(NullFormatter.isBlank(parametros.get(4))){
                statement.setNull(3, OracleTypes.NULL);
            }else{
                statement.setString(3, parametros.get(4));
            }

            statement.registerOutParameter(4, OracleTypes.VARCHAR);
            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.registerOutParameter(6, OracleTypes.CURSOR);


            statement.execute ();

            respuesta = statement.getString(5);

            if (!respuesta.startsWith("SUCCESS")) {
                logger.error(respuesta, new Exception(respuesta));
                throw (new Exception (respuesta,null));
            } else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            estatus = new HashMap<String, Object>();

            respuesta = statement.getString(4);

            estatus.put("estatusTarjeta",respuesta);

            result = (ResultSet) statement.getObject(6);

            filas = new ArrayList();

            while (result.next()){

                celdas = new ArrayList<String>();
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(1)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(2)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(3)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(4)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(6)));    //estatus
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(7)) + "</br>" + NullFormatter.formatHtmlBlank(result.getString(8)));    //Usuario y fecha bloqueo
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(9)));
                celdas.add(NullFormatter.formatHtmlBlank(result.getString(10)) + "</br>" + NullFormatter.formatHtmlBlank(result.getString(11)));   //Usuario y fecha Anulacion

                PrivilegioOd privilegio = null;
                for (int i =0 ; i< listaAcciones.size() ; i++   ){
                    privilegio = (PrivilegioOd) listaAcciones.get(i);
                    if(privilegio.getOpcion().equals("0000000103")) {
                        if (NullFormatter.formatBlank(result.getString(5)).equalsIgnoreCase("P")) {
                            if (privilegio.getPrivilegios().get(4).equals("1") && NullFormatter.formatBlank(result.getString(15)).equalsIgnoreCase("S"))
                                celdas.add("<input type='button' id='" + NullFormatter.formatBlank(result.getString(1)) + "|" + NullFormatter.formatBlank(result.getString(5)) + "|" + NullFormatter.formatBlank(result.getString(13)) + "|" + NullFormatter.formatBlank(result.getString(4)) + "|" + NullFormatter.formatBlank(result.getString(14)) +"|"+NullFormatter.formatBlank(result.getString(2)) +"' value='" + NullFormatter.formatBlank(result.getString(12)) + "' class='0000000103_5 botonGrandeDerecha' onclick='btnBloqueoTabla(this)'>");      //bt
                            else
                                celdas.add(NullFormatter.formatHtmlBlank("") );
                        }
                        if (NullFormatter.formatBlank(result.getString(5)).equalsIgnoreCase("A")) {
                            if (privilegio.getPrivilegios().get(7).equals("1") && NullFormatter.formatBlank(result.getString(15)).equalsIgnoreCase("S"))
                                celdas.add("<input type='button' id='" + NullFormatter.formatBlank(result.getString(1)) + "|" + NullFormatter.formatBlank(result.getString(5)) + "|" + NullFormatter.formatBlank(result.getString(13)) + "|" + NullFormatter.formatBlank(result.getString(4)) + "|" + NullFormatter.formatBlank(result.getString(14)) +"|"+NullFormatter.formatBlank(result.getString(2)) + "' value='" + NullFormatter.formatBlank(result.getString(12)) + "' class='0000000103_6 botonGrandeDerecha' onclick='btnBloqueoTabla(this)'>");      //bt
                            else
                                celdas.add(NullFormatter.formatHtmlBlank("") );
                        }
                    }
                }

                filas.add(celdas);
            }

            columnas = new ArrayList<String>();

            columnas.add("# Bloqueo");
            columnas.add("Cartera");
            columnas.add("Nro. CTA");
            columnas.add("Nro. DOC");
            columnas.add("Estatus Bloqueo");
            columnas.add("Info Bloqueo");
            columnas.add("Tipo Bloqueo");
            columnas.add("Solicitud Reactivación");
            columnas.add("Acci&oacute;n");

            lista = new TableOd();
            lista = TransformTable.getTable(columnas, filas);
            estatus.put("lista_tarjetas",lista);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);

        }catch (Exception ex) {
            logger.error(ex,ex);
            loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            throw (new Exception (ex.getMessage(),null));
        }finally{
            closeJdbcObjects (connection, statement, result);
        }
        return (estatus);
    }


    public String  cambiarEstatusTdc (List<String> parametros, VBTUsersOd usuario,boolean servicio) throws Exception {
        final String origen = "BackOfficeIo.cambiarEstatusTdc";
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet result = null;
        List l = new ArrayList();
        long fila = 0, time = 0, time2 = 0, time3 = 0;
        VBTUsersOd usuariosOd = null;
        String respuesta=null;
        String sqlSelect=null;
        String emails = new String();
        String codigoBloqueo = "";

        try {
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen);
            time = System.currentTimeMillis ();

            Socket cliente;
            DataOutputStream outStr;
            DataInputStream inStr;
            String comandoMensaje;
            StringBuffer mensaje = new StringBuffer();

            cliente =null;
            outStr =null;
            inStr =null;

            String comando = NullFormatter.isBlank(parametros.get(4)) ? parametros.get(6): "B";

            if(servicio) {

                connection = getConnection();       // hacer pl con firma y body en paquete
                connection.setAutoCommit(false);

                statement = connection.prepareCall("begin VBT_TARJETA.PCK_TARJETA.PR_CAMBIAR_ESTATUS_TDC_BO(?,?,?,?,?,?,?,?,?,?); end;");

                if (NullFormatter.isBlank(parametros.get(1)))
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, parametros.get(1));

                if (NullFormatter.isBlank(parametros.get(2)))
                    statement.setNull(2, OracleTypes.NULL);
                else
                    statement.setString(2, parametros.get(2));

                if (NullFormatter.isBlank(parametros.get(0)))
                    statement.setNull(3, OracleTypes.NULL);
                else
                    statement.setString(3, parametros.get(0));

                if (NullFormatter.isBlank(usuario.getLogin()))
                    statement.setNull(4, OracleTypes.NULL);
                else
                    statement.setString(4, usuario.getLogin());

                if (NullFormatter.isBlank(parametros.get(3)))
                    statement.setNull(5, OracleTypes.NULL);
                else
                    statement.setString(5, parametros.get(3));

                if (NullFormatter.isBlank(comando))
                    statement.setNull(6, OracleTypes.NULL);
                else
                    statement.setString(6, comando);

                if (NullFormatter.isBlank(parametros.get(7)))
                    statement.setNull(7, OracleTypes.NULL);
                else
                    statement.setString(7, parametros.get(7));

                statement.registerOutParameter(8, OracleTypes.VARCHAR);

                if (NullFormatter.isBlank(parametros.get(5)))
                    statement.setNull(9, OracleTypes.NULL);
                else
                    statement.setString(9, parametros.get(5));

                statement.registerOutParameter(10, OracleTypes.VARCHAR);

                statement.execute();
                respuesta = statement.getString(8);

                codigoBloqueo = statement.getString(10);

                if (!respuesta.equalsIgnoreCase("OK")) {
                    connection.rollback();
                    throw (new Exception(respuesta, null));
                } else {
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");

                    try{
                        cliente = new Socket(ResourceBundle.getBundle("vbtonline").getString("servidorBetransPagos"), Integer.parseInt(ResourceBundle.getBundle("vbtonline").getString("puertoBetransPagos")));
                        outStr = new DataOutputStream(cliente.getOutputStream());
                        inStr = new DataInputStream(cliente.getInputStream());

                        comandoMensaje = NullFormatter.isBlank(parametros.get(4)) ? parametros.get(8): parametros.get(4);

                        mensaje.append(comandoMensaje + "," + parametros.get(2).substring(4) + "," + parametros.get(5)+ ","+ parametros.get(1) +","+codigoBloqueo +",BE");

                        outStr.writeUTF(mensaje.toString());
                        respuesta = inStr.readUTF();

                        if (!respuesta.split("\\|")[0].equalsIgnoreCase("OK")) {
                            respuesta = "NO OK";
                            connection.rollback();
                            loggerIo.guardarExcepcion(usuario.getIP(), "Servicio de bloqueo no disponible", origen, "Servicio de bloqueo no disponible", usuario.getLogin(), usuario.getNumContrato());
                        }

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                        respuesta ="NO OK";
                        connection.rollback();
                        loggerIo.guardarExcepcion(usuario.getIP(), ex.getMessage(), origen,"Archivo: "+ex.getStackTrace()[0].getFileName()+"    Metodo: "+ ex.getStackTrace()[0].getMethodName()+"   Linea: "+ex.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
                    }finally{
                        inStr.close();
                        outStr.close();
                        cliente.close();
                    }
                }

            }else{
                connection = getConnection();       // hacer pl con firma y body en paquete
                connection.setAutoCommit(false);

                statement = connection.prepareCall("begin VBT_TARJETA.PCK_TARJETA.PR_CAMBIAR_ESTATUS_TDC_BO(?,?,?,?,?,?,?,?,?,?); end;");

                if (NullFormatter.isBlank(parametros.get(1)))
                    statement.setNull(1, OracleTypes.NULL);
                else
                    statement.setString(1, parametros.get(1));

                if (NullFormatter.isBlank(parametros.get(2)))
                    statement.setNull(2, OracleTypes.NULL);
                else
                    statement.setString(2, parametros.get(2));

                if (NullFormatter.isBlank(parametros.get(0)))
                    statement.setNull(3, OracleTypes.NULL);
                else
                    statement.setString(3, parametros.get(0));

                if (NullFormatter.isBlank(usuario.getLogin()))
                    statement.setNull(4, OracleTypes.NULL);
                else
                    statement.setString(4, usuario.getLogin());

                if (NullFormatter.isBlank(parametros.get(3)))
                    statement.setNull(5, OracleTypes.NULL);
                else
                    statement.setString(5, parametros.get(3));

                if (NullFormatter.isBlank(comando))
                    statement.setNull(6, OracleTypes.NULL);
                else
                    statement.setString(6, comando);

                if (NullFormatter.isBlank(parametros.get(7)))
                    statement.setNull(7, OracleTypes.NULL);
                else
                    statement.setString(7, parametros.get(7));

                statement.registerOutParameter(8, OracleTypes.VARCHAR);

                statement.setNull(9, OracleTypes.NULL);

                statement.registerOutParameter(10, OracleTypes.VARCHAR);

                statement.execute();
                respuesta = statement.getString(8);

                if (!respuesta.equalsIgnoreCase("OK")) {
                    connection.rollback();
                    throw (new Exception(respuesta, null));
                } else {
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
                }
            }

            connection.commit();
            time = System.currentTimeMillis () - time;
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);
        }catch (Exception ex) {
            logger.error(ex);
            respuesta ="NO OK";
            throw (new Exception (ex.getMessage(),null));

        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }
        return (respuesta);
    }

    public LoggerIo getLoggerIo() {
        return loggerIo;
    }

    public void setLoggerIo(LoggerIo loggerIo) {
        this.loggerIo = loggerIo;
    }
}
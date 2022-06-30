package ve.com.vbtonline.servicio.io;

//import com.venezolano.util.mail.MailManager;
import com.venezolano.util.mail.MailManager;
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

import java.io.Serializable;
import java.net.InetAddress;
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

    public CtaOpcOd opcionesGrupo (String transaccionId, CtaOpcOd ctod) throws Exception {
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

//              connection=getConnection();

//              statement  = connection.prepareCall ("begin AFILIADOS_PK.existe_afiliado_pr(?,?,?,?); end;");


//              statement.execute ();

            /// aqui llamo al procedure

            ctaOpcOd=new CtaOpcOd();
            ctaOpcOd.setDesopc("prueba");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (ctaOpcOd);
    }

    public TableOd consultarGrupos () throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
             closeJdbcObjects (connection, statement, result);
        }

        return (listaGrupos);
    }

    public TableOd consultarGrupoOpcionesSistema (String categoria) throws Exception {
        final String origen = "BackOfficeIo.consultarGrupoOpcionesSistema";
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_gruposopcsis_pr(?,?,?,?); end;");


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
                        if(!NombrePadre.equalsIgnoreCase(""))
                            body.add("<b><a style='cursor:pointer' id='"+listaOpciones2.get(i).getOpcionCodigo() +"' onclick='seleccionarGrupoSistemaOpcion(this.id)' value='"+
                                listaOpciones2.get(i).getOpcionDescripcion()+"'>"+NombrePadre+": "+ResourceBundle.getBundle("Comun2_ve_es").getString(descripcion)+"</a></b>");
                        else
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

               if(marca == 0)
                bodys.add(body);
            }

            listaOpciones=new TableOd();

            listaOpciones= TransformTable.getTable(header, bodys);
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ BackOfficeIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public TableOd consultarOpcionesSistema (String strCmbTipoUsuarioEditar) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public TableOd consultarGrupoOpcPermisos (String codGrupo, String codOpcion, List<String> acciones) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaOpciones);
    }

    public TableOd consultarOpcPermisos (String usuario, String codOpcion, List<String> acciones) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
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
                if(!result.getString(12).equalsIgnoreCase("6") && !result.getString(12).equalsIgnoreCase("7") && !result.getString(12).equalsIgnoreCase("8")){
                    List<String> body=new ArrayList<String>();

                    body.add("<img style='cursor:pointer'  onclick='abrirDetalleTablaUsuario(this)' src='resources/images/comun/nolines_plus.gif' width='9' height='9' id='"+NullFormatter.formatHtmlBlank(result.getString(9))+
                            "|"+NullFormatter.formatHtmlBlank(result.getString(2))+
                            "|"+NullFormatter.formatHtmlBlank(result.getString(10))+
                            "|"+NullFormatter.formatHtmlBlank(result.getString(4))+
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

              statement  = connection.prepareCall ("begin VBTONLINE.vbt_contratos_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

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

            statement.registerOutParameter(20, OracleTypes.CURSOR);
            statement.registerOutParameter(21, OracleTypes.VARCHAR);
            statement.registerOutParameter(22, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(21);
            String sql = statement.getString(22);
            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

            result = (ResultSet) statement.getObject (20);

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

                    if (result.getString(5).equalsIgnoreCase("Cancelled"))
                        body.add("<a id='"+result.getString(1) +"|"+result.getString(6)+"|"+result.getString(2)+"|"+enableButon+"'  value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");
                    else
                        body.add("<a style='cursor:pointer' id='"+result.getString(1) +"|"+result.getString(6)+"|"+result.getString(2)+"|"+enableButon+"' onclick='seleccionarContratoOpcion(this.id)' value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");
                }else{
                    if (result.getString(5).equalsIgnoreCase("Cancelled"))
                        body.add("<a id='"+result.getString(1) +"|"+result.getString(6)+"|"+"|"+enableButon+"'  value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");
                    else
                        body.add("<a style='cursor:pointer' id='"+result.getString(1) +"|"+result.getString(6)+"|"+"|"+enableButon+"' onclick='seleccionarContratoOpcion(this.id)' value='"+ result.getString(1)+"'><b>"+result.getString(1)+"<b></a>");

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
                    GuardarLog(usuario.getLogin(),"3","1","7",result.getString(1),usuario.getIP(),"Consulta General de Contratos");
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
                body.add("<select id='emailV_"+con+"' class='email ObligatorioECTelefono' title='Email'></select>"+"<input type='hidden' id='emailSeleccionadoV_"+con+"' value='"+NullFormatter.formatHtmlBlank(result.getString(5))+"' class='' >"+ "<input type='hidden' value='"+NullFormatter.formatHtmlBlank(result.getString(8))+"|"+NullFormatter.formatHtmlBlank(result.getString(4))+"|"+NullFormatter.formatHtmlBlank(result.getString(7))+"' id='codpercli_"+con+"'>");
                body.add("<span id='tipoClienteV_"+con+"' class='tipoCliente'> "+NullFormatter.formatHtmlBlank(result.getString(6))+"</span>"+"<input type='hidden' value='"+NullFormatter.formatHtmlBlank(result.getString(8))+"' id='tipoClienteCodigoV_"+con+"'>");
                //body.add("<span id='telefonoCelularV_"+con+"' class='tipoCliente'> "+NullFormatter.formatHtmlBlank(result.getString(9))+"</span>");
                body.add("<select id='telefonoCelularV_"+con+"' class='tipoCliente ObligatorioECTelefono' title='Telefono Celular'></select>"+"<input type='hidden' id='telefonoCelularSeleccionadoV_"+con+"' checked='checked' value='"+NullFormatter.formatHtmlBlank(result.getString(9))+"' class='' >");
                body.add("<span  class='loginUsuario'> "+NullFormatter.formatHtmlBlank(result.getString(10))+"</span>");

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
                     if (productos==0){
                         body.add("<input type='checkbox' disabled name='"+NullFormatter.formatHtmlBlank(cartera) +"' value='"+NullFormatter.formatHtmlBlank(cartera) +"'  id='guardarCartera_"+con+"' class='datos 0000000004_2' />");
                     }else{
                         body.add("<input type='checkbox' checked name='"+NullFormatter.formatHtmlBlank(cartera) +"' value='"+NullFormatter.formatHtmlBlank(cartera) +"' id='guardarCartera_"+con+"' class='datos 0000000004_2' />");

                     }
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuario_con_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");

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
                    this.GuardarLog(usuario.getLogin(),"3","1","1",result.getString(1).toString(),usuario.getIP(),"Consulta General de Usuarios");
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
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


            statement  = connection.prepareCall ("begin vbtonline.vbt_bitacora_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaBitacora);
    }

    public TableOd consultarSucesos (List<String> parametros) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
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

    public String editarUsuario (VBTUsersOd usuario) throws Exception {
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuarioeditar_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }
    //incompleto
    public String editarUsuarioContrato (VBTUsersOd usuario, String plogin) throws Exception {
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

            statement  = connection.prepareCall ("begin BACKOFFICE.bac_usuclienteeditar_pr(?,?,?,?,?,?,?,?,?,?); end;");
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }
    //incompleto
    public String agregarUsuario (VBTUsersOd usuario, String plogin) throws Exception {
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
//        MailManager mailManager = new MailManager("vbtonline");
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
                statement.setString(1,usuario.getLogin());

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
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                //enviar correo

                MailManager mailManager = new MailManager("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");

                String htmlText = "";
                htmlText =ResourceBundle.getBundle("UsuariosAgregar_ve_es").getString("TAGMsgEmail001") +
                        "\n\n" + ResourceBundle.getBundle("Comun_ve_es").getString("FIELDTxtLogin") + ": " + usuario.getLogin().toLowerCase()  +
                        "\n" + ResourceBundle.getBundle("Comun_ve_es").getString("FIELDPwdClave") + ": " + clave.toLowerCase()  +
                        "\n\n" + ResourceBundle.getBundle("UsuariosAgregar_ve_es").getString("TAGMsgEmail002");

                //mailManager.sendHtmlMail(rb.getString("adminmail"),strTxtEmailAgregar,"VBT Bank & Trust Online",htmlText);
                mailManager.sendPlainMail(rb.getString("adminmail"),usuario.getEmail(), "VBT Bank & Trust Online", htmlText);

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
                    this.GuardarLog(usuario.getLogin(),"3","1","1",result.getString(1),usuario.getIP(),"Consulta General Usuarios en Contrato");
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public SelectOd cargarElementosTipos (String tipo) throws Exception {
        final String origen = "TransfersIo.cargarElementosTipos";
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

    public SelectOd cargarAccionFiltro () throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
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

    public SelectOd cargarObjetosFiltro () throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarGrupoClientes () throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarTipoCiRif () throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
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

    public SelectOd consultarCorreos (String codcli,String login) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }


    public SelectOd consultarTelefonos (String codcli,String login) throws Exception {
        final String origen = "BackOfficeIo.telefonos";
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




    public SelectOd consultarTelefonosLocal (String codcli,String login) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public String salvarPermisosGrupos (List<String> acciones) throws Exception {
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

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
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

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

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

    public String salvarPermisosUsuarios (List<String> acciones) throws Exception {
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

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
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

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");

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

    public TableOd busquedaUsuariosContrato (List<String> busqueda) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaUsuarios);
    }

    public TableOd busquedaCarterasContrato (List<String> busqueda) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (listaGrupos);
    }

    public String agregarContrato (List<CarterasOd> carteras, List<VBTUsersOd> usuarios, VBTUsersOd usuarioCreador, String descripcion, String login, String ip) throws Exception {
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
            statement  = connection.prepareCall ("begin BACKOFFICE.bac_crearContrato_pr(?,?,?,?,?); end;");
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

            statement.registerOutParameter(5, OracleTypes.VARCHAR);
            statement.execute ();

            respuesta = statement.getString(5);

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

                statement  = connection.prepareCall ("begin BACKOFFICE.bac_agregarNuevoUsuario_pr(?,?,?,?,?,?,?,?,?,?); end;");

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

                statement.registerOutParameter(10, OracleTypes.VARCHAR);
                statement.execute ();

                respuesta = statement.getString(10);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception (respuesta,null));
                else{
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");
                    this.GuardarLog(login,"12","1","1",usuarios.get(i).getLogin(),ip,"Ha creado un usuario para el contrato Nº "+ numeroContrato );
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
            this.GuardarLog(login,"12","1","7","",ip,"Ha creado un contrato Nº "+ numeroContrato );

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

    public String editarContrato(List<CarterasOd> carterasViejas, List<VBTUsersOd> usuariosViejos, List<CarterasOd> carterasNuevas, List<VBTUsersOd> usuariosNuevos, String descripcion, String estatus, String numeroContrato, VBTUsersOd usuarioEditor, List<ContentSelectOd> motivosRechazo) throws Exception {
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
            statement = connection.prepareCall("begin BACKOFFICE.bac_editarContrato_pr(?,?,?,?); end;");

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
                statement = connection.prepareCall ("begin BACKOFFICE.bac_usuclienteeditar_pr(?,?,?,?,?,?,?,?,?,?); end;");

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

                    statement.registerOutParameter(10, OracleTypes.VARCHAR);
                    statement.execute();

                respuesta = statement.getString(10);
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

                statement = connection.prepareCall("begin BACKOFFICE.bac_agregarNuevoUsuario_pr(?,?,?,?,?,?,?,?,?,?); end;");

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

                statement.registerOutParameter(10, OracleTypes.VARCHAR);
                statement.execute();

                respuesta = statement.getString(10);

                if (!respuesta.equalsIgnoreCase("OK"))
                    throw (new Exception(respuesta, null));
                else{
                    logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino") + BackOfficeIo.class + " | " + origen + " | 0 respuesta de bd exitosa");
                    this.GuardarLog(usuarioEditor.getLogin(),"12","1","1",usuariosNuevos.get(i).getLogin(),usuarioEditor.getIP(),"Se ha creado un usuario para el contrato: "+numeroContrato);

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
                statement = connection.prepareCall("begin BACKOFFICE.bac_editarContrato_pr(?,?,?,?); end;");

                if (descripcion == null)
                    statement.setString(1, "");
                else
                    statement.setString(1, descripcion);

                statement.setNull(2, OracleTypes.NULL);
                statement.setString(3, numeroContrato);

                statement.registerOutParameter(4, OracleTypes.VARCHAR);
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
                    carterasAux=carterasAux.substring(0,carterasAux.length()-2);

            } else {
                //System.out.println("No se seleccionaron carteras asociadas");

              connection2.commit();
            }
                /*/////////////////////////////////////////////////////*/
            // Envia correos de activación para los nuevos usuarios (si los hay) de contratos activos
            if (!NullFormatter.isBlank(estatus) && activacionContratoNuevo && (usuariosNuevos != null) && (usuariosNuevos.size() > 0) && "1".equals(estatus)) {
                MailManager mailManager = new MailManager("vbtonline");
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
                            respuestaMsj=vbt.enviarAlertaONL("",mensaje2,usuariosNuevos.get(i).getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));

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

                        String mensaje3 = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGPassword") + ": " + clave.toLowerCase();

                        respuestaMsj=vbt.enviarAlertaONL("",mensaje3,usuariosNuevos.get(i).getLogin().toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));


                    }  // End for (int i=0; i<usuariosNuevos.size() ; i++)
                }    // End if (usuariosNuevos != null && usuariosNuevos.size() > 0)
            }  // End if (!NullFormatter.isBlank(estatus) && !activacionContratoNuevo && (usuariosNuevos != null) && (usuariosNuevos.size() >0) && "1".equals(estatus) )
            /*/////////////////////////////////////////////////////*/

            /*///////////////////////////////////////////////////**/
            /***************************************************************************
             * Envia correos de activación
             * ************************************************************************/
            if (!NullFormatter.isBlank(estatus) && activacionContratoNuevo) {
                MailManager mailManager = new MailManager("vbtonline");
                ResourceBundle rb = ResourceBundle.getBundle("vbtonline");
                String htmlText = "";
                String htmlTextAux = "";

                // ************************************* Busca las carteras asociadas al contrato.
//                if (acceso.verificarOpcionAccion(modulo,"EDITAR")) {
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
                    String respuestaMsj1=vbt.enviarAlertaONL("",mensajeUsu,result.getString(1).toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));

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
                    String mensajePass = ResourceBundle.getBundle("ContratosEditar_us_en").getString("TAGPassword") + ": " +  claveTemporal.toLowerCase() ;


                    respuestaMsj1=vbt.enviarAlertaONL("",mensajePass,result.getString(1).toLowerCase(),ResourceBundle.getBundle("vbtonline").getString("adminSMS"));


                    htmlText2 = ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_eje") +" " +  telefonoONL +
                            "\n\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje2") + numeroContrato +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje3") + ": " + carterasAux +
                            "\n" + ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_usuarios_eje4") + ": " + result.getString(1).toLowerCase()+"\n\n\n";


                    mailManager.sendPlainMail( ResourceBundle.getBundle("vbtonline").getString("adminmail"),emailsEjecuvitos,ResourceBundle.getBundle("Transferencias"+"_ve_es").getString("tag_ejeTitulo"), htmlText2);

                } // End if (maxUsuarios > 0)
            } // End if (!NullFormatter.isBlank(strStatusContratoEditar) && activacionContratoNuevo)
            /*///////////////////////////////////////////////////**/
            vbt=null;
            connection.commit();

            time = System.currentTimeMillis() - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino") + BackOfficeIo.class + " | " + origen);


        } catch (Exception ex) {
            connection.rollback();
            connection2.rollback();
            logger.error(ex);
            vbt=null;
            throw (new Exception(ex.getMessage(), null));
        } finally {
            vbt=null;
            closeJdbcObjects(connection, statement, result);
            closeJdbcObjects(connection2, statement2, result);
        }

        return (respuesta);
    }

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
    }


    public String eliminarPermisosUsuarios (List<String> acciones) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (respuesta);
    }


    public SelectOd cargarMotivosRechazo () throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (select);
    }

    public SelectOd cargarMotivosRechazoContrato (String contrato) throws Exception {
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
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
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

    public ParametrosPersonalesOd cargarParametrosGlobales (String tipo) throws Exception {
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

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }

    public ParametrosPersonalesOd cargarParametrosContratos (String contrato) throws Exception {
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

            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.ino")+ DesingBankIo.class+" | "+origen);


        }
        catch (Exception ex) {
            logger.error(ex); throw (new Exception (ex.getMessage(),null));
        }
        finally {
            closeJdbcObjects (connection, statement, result);
        }

        return (parametrosPersonales);
    }








    public String guardarParametrosPersonalesBO (ParametrosPersonalesOd parametrosPersonales,String tipo, String tipoParametro) throws Exception {
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

            statement  = connection.prepareCall ("begin vbtonline.vbt_parametros_personales_pr(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
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


            statement.registerOutParameter(14, OracleTypes.VARCHAR);

            statement.execute ();

            respuesta = statement.getString(14);

            if (!respuesta.equalsIgnoreCase("OK"))
                throw (new Exception (respuesta,null));
            else
                logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.ino")+ BackOfficeIo.class+" | "+origen+" | 0 respuesta de bd exitosa");



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

}


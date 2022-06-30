package ve.com.vbtonline.vista.action.firmasConjuntas;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.backoffice.IBackOfficeServicio;
import ve.com.vbtonline.servicio.negocio.firmasConjuntas.IFirmasConjuntasServicio;
import ve.com.vbtonline.servicio.od.*;
import ve.com.vbtonline.servicio.util.TransformTable;
import ve.com.vbtonline.servicio.util.validacionUtil;

import javax.servlet.ServletContext;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by IntelliJ IDEA.
 * User: Manuel Serrano
 * Date: 21/05/2010
 * Time: 11:33:39 AM
 * To change this template use File | Settings | File Templates.
 */
public class FirmasConjuntasAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(FirmasConjuntasAction.class);
    private FabricaTo fabrica;
    private Map session;
    private BackOfficeOd backOfficeOd;
    private CtaOpcOd ctaOpcOd;
    private IFirmasConjuntasServicio firmasConjuntasServicio;
    private String mensaje;
    private String codigo;
    private DataJson data=new DataJson();
    private String jsonBackOffice;
    private String jsonConsultaUsuarios;
    private String jsonEditarUsuario;
    private String jsonAgregarUsuario;
    private String jsonParametrosString;
    private String jsonFirmasConjuntas;
    private String carterasContrato;

    private ConsultUsersOd datosConsultaUBO;
    private ConsultaBitacoraOd datosConsultBitacora;
    private ConsultContratsOd datosConsContBO;
    private GruposOd datosGrupoOS;
    private List<GruposOd> listaGrupos;
    private List<ItemOd> tituloColumnas;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTest;
    private List<ContentTableInfoOd> contenidoTabla_infoTest;
    private List<ContentsTableColumnsOd> contenidoTabla_culumnasTestaux;
    private List<ContentTableInfoOd> contenidoTabla_infoTestaux;
    private List<ContentSelectOd> tipoGrupo;
    private List<ContentSelectOd> tipoEstatus;
    private List<ContentSelectOd> correos;
    private List<ContentSelectOd> tipoEstatusContrato;
    private List<ContentSelectOd> usuarioCreador;
    private List<ContentSelectOd> tipoCiRif;
    private List<ContentSelectOd> acciones;
    private List<ContentSelectOd> objetos;
    private String primeraVez;
    private VBTUsersOd usuario;
    private VBTUsersOd usuarioContrato;
    private String jsonEditarContrato;
    private String mensajeBO;
    private String desde;
    private String hasta;
    private String codOpc;
    private String respuesta;
    private String idioma;
    private ParametrosPersonalesFCOd parametrosPersonalesFC;
    private ParametrosPersonalesFCOd parametrosPersonalesBaseFC;
    private List<RolesCustomOd> rolesCustom;
    private List<PrivilegioOd> permisos;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<PrivilegioOd> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PrivilegioOd> permisos) {
        this.permisos = permisos;
    }

    public List<RolesCustomOd> getRolesCustom() {
        return rolesCustom;
    }

    public void setRolesCustom(List<RolesCustomOd> rolesCustom) {
        this.rolesCustom = rolesCustom;
    }

    public FirmasConjuntasAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }

    public String execute() throws Exception {
        final String origen = "FirmasConjuntasAction.execute";
        long time;
        UsuarioOd usuario;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }

    public String consultarUsuarios() throws Exception {
        final String origen = "FirmasConjuntasAction.consultarUsuarios";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            idioma = (String) session.get("idioma");


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaUsuarios, DataJson.class);
            datosConsultaUBO = data.getConsultaUsuarioss().get(0);
            datosConsultaUBO.setStrConsulta(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioConsultaFC"));
            datosConsultaUBO.setStrCargador(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioCargadorFC"));
            datosConsultaUBO.setStrAprobador(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioAprobadorFC"));
            datosConsultaUBO.setStrLiberador(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioLiberadorFC"));
            datosConsultaUBO.setStrAuditor(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioAuditorFC"));
            datosConsultaUBO.setStrCliente(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgCliente"));
            datosConsultaUBO.setStrPersonalizado(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioPersonalizadoFC"));
            datosConsultaUBO.setStrActualizacion(ResourceBundle.getBundle("FirmasConjuntas"+idioma).getString("usuarioActFC"));
            datosConsultaUBO.setStrActivo(ResourceBundle.getBundle("Comun"+idioma).getString("Activo"));
            datosConsultaUBO.setStrInactiva(ResourceBundle.getBundle("Comun"+idioma).getString("Inactivo"));
            datosConsultaUBO.setStrCancelada(ResourceBundle.getBundle("Comun"+idioma).getString("Cancelado"));
            datosConsultaUBO.setStrBloqueado(ResourceBundle.getBundle("Comun"+idioma).getString("Bloqueado"));
            datosConsultaUBO.setStrDesconocido(ResourceBundle.getBundle("Comun"+idioma).getString("TAGMsgStatusDesconocido"));
            datosConsultaUBO.setStrOrden("Usuario");

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");
            idioma = (String) ((String) session.get("idioma"));

            TableOd tableOd=getFirmasConjuntasServicio().consultarUsuarios(datosConsultaUBO, usuario, idioma);

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

//            if (datosConsultaUBO.getHdnAccion()==null){

            /// cargar elementos de los selects para la consulta

            SelectOd grupos2 =getFirmasConjuntasServicio().consultarElementos("0000000007", idioma,usuario);
            tipoGrupo=grupos2.getContenido();
            session.put("tipoGrupoFC",tipoGrupo);



            SelectOd  estatus2 =getFirmasConjuntasServicio().consultarElementos("0000000001", idioma, usuario);
            tipoEstatus=estatus2.getContenido();
            session.put("tipoEstatusFC",tipoEstatus);



            SelectOd  cirif =getFirmasConjuntasServicio().cargarTipoCiRif(usuario);
            tipoCiRif=cirif.getContenido();
            session.put("tipoCIRIFFC",tipoCiRif);


            primeraVez = "SI";
//            }else{
//                primeraVez="NO";
//            }
            //////

            getFirmasConjuntasServicio().guardarLog(usuario.getLogin(),"3","1","1",null,usuario.getIP(),"Consulta de Usuarios de Firmas Conjuntas en Contratos");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen, "Archivo: " + e.getStackTrace()[0].getFileName() + "    Metodo: " + e.getStackTrace()[0].getMethodName() + "   Linea: " + e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());

        }
        return SUCCESS;
    }

    public String cargarGruposFC() throws Exception {
        final String origen = "FirmasConjuntasAction.cargarGruposFC";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            idioma = (String) session.get("idioma");

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");

            if ( session.get("tipoGrupoFC")==null){

                /// cargar elementos de los selects para la consulta

                SelectOd grupos2 =getFirmasConjuntasServicio().consultarElementos("0000000007", idioma, usuario);
                tipoGrupo=grupos2.getContenido();
                session.put("tipoGrupoFC",tipoGrupo);


                SelectOd  cirif =getFirmasConjuntasServicio().cargarTipoCiRif(usuario);
                tipoCiRif=cirif.getContenido();
                session.put("tipoCIRIFFC",tipoCiRif);


            }else{

                tipoGrupo =(List<ContentSelectOd>) session.get("tipoGrupoFC");
                tipoCiRif =(List<ContentSelectOd>) session.get("tipoCiRif");
            }
            //////

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());

        }
        return SUCCESS;
    }

    public String consultaUsuario() throws Exception {
        final String origen = "FirmasConjuntasAction.consultarUsuario";
        long time;
        BackOfficeOd backOfficeOd;
//        VBTUsersOd usuario = new VBTUsersOd();
        String loginUsuario = new String();
        String tipoGrupo = new String();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarUsuario, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            VBTUsersOd usuario2 = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            loginUsuario = usu.getLogin();
            tipoGrupo = usu.getTipo_grupo();
            String prueba = ResourceBundle.getBundle("Comun2_ve_es").getString("EXCEPCIONES");
            usuario= getFirmasConjuntasServicio().consultarUsuario(loginUsuario, tipoGrupo,usuario2);
            rolesCustom=getFirmasConjuntasServicio().cargarRolesUsuario(usuario, idioma);
            usuario.setRolesCustom(rolesCustom);


            getFirmasConjuntasServicio().guardarLogFC(usuario2.getLogin(),"3","1","1",loginUsuario,usuario2.getIP(),"Consulta Detalle de Usuarios Firmas Conjuntas",usuario2.getNumContrato());

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
        }
        return SUCCESS;
    }

    public String editarUsuario() throws Exception {
        final String origen = "FirmasConjuntasAction.editarUsuario";
        long time;
        String respuesta= new String();
        validacionUtil validar = new validacionUtil();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(validar.caracteresEspeciales(this.jsonEditarUsuario), DataJson.class);

            VBTUsersOd usu =  data.getLogins().get(0);
            VBTUsersOd usuAnt =  data.getLogins().get(1);
            usu.setCodpercli(usuario.getCodpercli());
            idioma = (String)session.get("idioma");
            mensaje = "El usuario seleccionado fue no pudo ser modificado";
            usu.setNumContrato(usuario.getNumContrato());
            respuesta=getFirmasConjuntasServicio().editarUsuario(usu,usuAnt,idioma, usuario);


            if (respuesta.equals("OK")){
                if(idioma == "_us_en")
                    mensaje = "The selected user was successfully modified";
                else
                    mensaje = "El usuario seleccionado fue modificado satisfactoriamente";

                getFirmasConjuntasServicio().guardarLogFC(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"Se ha editado el usuario: "+ usu.getLogin()+" con exito",usuario.getNumContrato());

            }else{
                if(idioma == "_us_en")
                    mensaje = "The selected user could not be modified";
                else
                    mensaje = "El usuario seleccionado no pudo ser modificado";

                getFirmasConjuntasServicio().guardarLogFC(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"El usuario: "+ usu.getLogin()+" no pudo ser modificado",usuario.getNumContrato());
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
        }
        return SUCCESS;
    }

    public String agregarUsuario() throws Exception {
        final String origen = "FirmasConjuntasAction.agregarUsuario";
        long time;
        String respuesta= new String();
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            validacionUtil validar = new validacionUtil();
            Gson gson=new Gson();
            data =gson.fromJson(validar.caracteresEspeciales(this.jsonAgregarUsuario), DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            mensaje = "El usuario no pudo ser agregado";
            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            respuesta=getFirmasConjuntasServicio().agregarUsuario(usu,usuario,idioma, usuario );
            usu.setNumContrato(usuario.getNumContrato());
            if (respuesta.equals("OK")){

                getFirmasConjuntasServicio().guardarLogFC(usuario.getLogin(),"12","1","1",usu.getLogin(),usuario.getIP(),"Se ha agregado el usuario satisfactoriamente: "+ usu.getLogin()+ " de Firmas Conjuntas",usuario.getNumContrato());

                getFirmasConjuntasServicio().correoClientePrincipal(usu,usuario,idioma);

                if(idioma == "_us_en"){
                    mensaje = "The new user was successfully added.\n" +
                            "The login and password have been sent to the registered mobile phone number";
                }else{
                    mensaje = "El nuevo usuario fue agregado satisfactoriamente.\n" +
                            "El login y la clave han sido enviados el telefono celular registrado.";
                }
                codigo="OK";


            }else if(respuesta.equals("Usuario Registrado")){
                getFirmasConjuntasServicio().guardarLogFC(usuario.getLogin(),"12","1","1",usu.getLogin(),usuario.getIP(),"Trato de agregar usuario que ya existe: "+ usu.getLogin()+ " de Firmas Conjuntas",usuario.getNumContrato());
                if(idioma == "_us_en"){
                    mensaje = "The new user you want to enter already exists.\n Please choose another user.\n " +
                            "Try again, but this time by adding a number to the user that is easy to remember.";
                }else{
                    mensaje = "El nuevo usuario que desea ingresar ya existe. \n Por favor elija otro usuario.\n Intente de nuevo, " +
                            "pero esta vez agregando un número al final del usuario que le sea fácil de recordar. ";
                }
                codigo="ERROR";
                getFirmasConjuntasServicio().guardarLogFC(usuario.getLogin(),"12","1","1",usu.getLogin(),usuario.getIP(),"El usuario no pudo ser agregado:  "+ usu.getLogin()+ " de Firmas Conjuntas",usuario.getNumContrato());
            }else if(respuesta.equals("NO SMS")){
                if(idioma == "_us_en")
                    mensaje = "the message could not be sent";
                else
                    mensaje = "El mensaje no pudo ser enviado";
                codigo="ERROR";
            }else{
                if(idioma == "_us_en")
                    mensaje = "The user could not be added";
                else
                    mensaje = "El usuario no pudo ser agregado";
                codigo="ERROR";
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            mensaje = "El usuario no pudo ser agregado";
            codigo="ERROR";
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(),e.getMessage(),origen,this.jsonAgregarUsuario,usuario.getLogin(),usuario.getNumContrato());
        }
        return SUCCESS;
    }

    public String guardarParametrosPersonales() throws Exception {
        final String origen = "FirmasConjuntasAction.guardarParametrosPersonales";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonFirmasConjuntas, DataJson.class);
            parametrosPersonalesFC = data.getParametrosPersonalesFC().get(0);
            parametrosPersonalesFC.setNum_contrato(usuario.getNumContrato());

            String monto = new String();
            monto = parametrosPersonalesFC.getVbt_mmtd().replace(".","");
            parametrosPersonalesFC.setVbt_mmtd(monto.replace(",","."));
            monto = new String();
            monto = parametrosPersonalesFC.getVbt_mmto().replace(".","");
            parametrosPersonalesFC.setVbt_mmto(monto.replace(",","."));


            idioma = (String) session.get("idioma");
            respuesta=getFirmasConjuntasServicio().guardarParametrosPersonales(parametrosPersonalesFC, usuario);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            respuesta ="NO OK";
            setMensaje(respuesta);
        }
        return SUCCESS;
    }

    public String cargarParametrosPersonales() throws Exception {
        final String origen = "FirmasConjuntasAction.cargarParametrosPersonales";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            List<String> parametros = new ArrayList<String>();
            parametros.add(usuario.getNumContrato());


            parametrosPersonalesFC = new ParametrosPersonalesFCOd();
            parametrosPersonalesFC=getFirmasConjuntasServicio().cargarParametrosPersonales(parametros, usuario);



            this.cargarParametrosPersonalesBase();

            if(parametrosPersonalesFC.getCorreo()==null){
                parametrosPersonalesFC = parametrosPersonalesBaseFC;
            }
            session.put("parametrosPersonalesFC", parametrosPersonalesFC);
            session.put("parametrosPersonalesBaseFC", parametrosPersonalesBaseFC);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }

    public String cargarParametrosPersonalesBase() throws Exception {
        final String origen = "FirmasConjuntasAction.cargarParametrosPersonalesBase";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");


            parametrosPersonalesBaseFC = new ParametrosPersonalesFCOd();
            parametrosPersonalesBaseFC=getFirmasConjuntasServicio().cargarParametrosPersonalesBase(usuario);

            session.put("parametrosPersonalesBaseFC", parametrosPersonalesBaseFC);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String cargarRolesCustom() throws Exception {
        final String origen = "FirmasConjuntasAction.cargarRolesCustom";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            List<String> parametros = new ArrayList<String>();
            parametros.add(usuario.getNumContrato());
            String usr="";
            usr=usuario.getLogin();
            if (login.equalsIgnoreCase("")){
                rolesCustom=getFirmasConjuntasServicio().cargarRolesCustom("FC", idioma, usuario);
            }else {
                usuario.setLogin(login);
                rolesCustom=getFirmasConjuntasServicio().cargarRolesUsuario(usuario, idioma);
            }
            usuario.setLogin(usr);

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }


    public String cargarRolesCustomDetalle() throws Exception {
        final String origen = "FirmasConjuntasAction.cargarRolesCustomDetalle";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+FirmasConjuntasAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            idioma = (String) session.get("idioma");
            List<String> parametros = new ArrayList<String>();
            parametros.add(usuario.getNumContrato());

            permisos=getFirmasConjuntasServicio().cargarRolesCustomDetalle(codigo, idioma, usuario);

            getFirmasConjuntasServicio().guardarLog(usuario.getLogin(),"3","1","1",null,usuario.getIP(),"Consulta de Roles de Usuario Personalizado de Firmas Conjuntas en Contratos");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+FirmasConjuntasAction.class+" | "+origen+" | "+time);

        }catch (Exception e){
            logger.error(e);
            getFirmasConjuntasServicio().guardarExcepcion(usuario.getIP(), e.getMessage(), origen,"Archivo: "+e.getStackTrace()[0].getFileName()+"    Metodo: "+ e.getStackTrace()[0].getMethodName()+"   Linea: "+e.getStackTrace()[0].getLineNumber(), usuario.getLogin(), usuario.getNumContrato());
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }



    //getter and setter

    public String getMensajeBO() {
        return mensajeBO;
    }

    public void setMensajeBO(String mensajeBO) {
        this.mensajeBO = mensajeBO;
    }

    public FabricaTo getFabrica() {
        return fabrica;
    }

    public void setFabrica(FabricaTo fabrica) {
        this.fabrica = fabrica;
    }

    public void setServletContext(ServletContext context) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public BackOfficeOd getBackOfficeOd() {
        return backOfficeOd;
    }

    public void setBackOfficeOd(BackOfficeOd backOfficeOd) {
        this.backOfficeOd = backOfficeOd;
    }

    public String getJsonBackOffice() {
        return jsonBackOffice;
    }

    public void setJsonBackOffice(String jsonBackOffice) {
        this.jsonBackOffice = jsonBackOffice;
    }

    public DataJson getData() {
        return data;
    }

    public void setData(DataJson data) {
        this.data = data;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public CtaOpcOd getCtaOpcOd() {
        return ctaOpcOd;
    }

    public void setCtaOpcOd(CtaOpcOd ctaOpcOd) {
        this.ctaOpcOd = ctaOpcOd;
    }

    public List<GruposOd> getListaGrupos() {
        return listaGrupos;
    }

    public void setListaGrupos(List<GruposOd> listaGrupos) {
        this.listaGrupos = listaGrupos;
    }

    public List<ItemOd> getTituloColumnas() {
        return tituloColumnas;
    }

    public void setTituloColumnas(List<ItemOd> tituloColumnas) {
        this.tituloColumnas = tituloColumnas;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasTest() {
        return contenidoTabla_culumnasTest;
    }

    public void setContenidoTabla_culumnasTest(List<ContentsTableColumnsOd> contenidoTabla_culumnasTest) {
        this.contenidoTabla_culumnasTest = contenidoTabla_culumnasTest;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoTest() {
        return contenidoTabla_infoTest;
    }

    public void setContenidoTabla_infoTest(List<ContentTableInfoOd> contenidoTabla_infoTest) {
        this.contenidoTabla_infoTest = contenidoTabla_infoTest;
    }

    public String getJsonConsultaUsuarios() {
        return jsonConsultaUsuarios;
    }

    public void setJsonConsultaUsuarios(String jsonConsultaUsuarios) {
        this.jsonConsultaUsuarios = jsonConsultaUsuarios;
    }

    public ConsultUsersOd getDatosConsultaUBO() {
        return datosConsultaUBO;
    }

    public void setDatosConsultaUBO(ConsultUsersOd datosConsultaUBO) {
        this.datosConsultaUBO = datosConsultaUBO;
    }

    public ConsultContratsOd getDatosConsContBO() {
        return datosConsContBO;
    }

    public void setDatosConsContBO(ConsultContratsOd datosConsContBO) {
        this.datosConsContBO = datosConsContBO;
    }

    public List<ContentSelectOd> getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(List<ContentSelectOd> tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public List<ContentSelectOd> getTipoEstatus() {
        return tipoEstatus;
    }

    public void setTipoEstatus(List<ContentSelectOd> tipoEstatus) {
        this.tipoEstatus = tipoEstatus;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public String getPrimeraVez() {
        return primeraVez;
    }

    public void setPrimeraVez(String primeraVez) {
        this.primeraVez = primeraVez;
    }


    public String getJsonEditarUsuario() {
        return jsonEditarUsuario;
    }

    public void setJsonEditarUsuario(String jsonEditarUsuario) {
        this.jsonEditarUsuario = jsonEditarUsuario;
    }

    public VBTUsersOd getUsuario() {
        return usuario;
    }

    public void setUsuario(VBTUsersOd usuario) {
        this.usuario = usuario;
    }

    public List<ContentSelectOd> getTipoCiRif() {
        return tipoCiRif;
    }

    public void setTipoCiRif(List<ContentSelectOd> tipoCiRif) {
        this.tipoCiRif = tipoCiRif;
    }


    public GruposOd getDatosGrupoOS() {
        return datosGrupoOS;
    }

    public void setDatosGrupoOS(GruposOd datosGrupoOS) {
        this.datosGrupoOS = datosGrupoOS;
    }

    public List<ContentSelectOd> getTipoEstatusContrato() {
        return tipoEstatusContrato;
    }

    public void setTipoEstatusContrato(List<ContentSelectOd> tipoEstatusContrato) {
        this.tipoEstatusContrato = tipoEstatusContrato;
    }

    public List<ContentSelectOd> getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(List<ContentSelectOd> usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public String getJsonEditarContrato() {
        return jsonEditarContrato;
    }

    public void setJsonEditarContrato(String jsonEditarContrato) {
        this.jsonEditarContrato = jsonEditarContrato;
    }

    public List<ContentsTableColumnsOd> getContenidoTabla_culumnasTestaux() {
        return contenidoTabla_culumnasTestaux;
    }

    public void setContenidoTabla_culumnasTestaux(List<ContentsTableColumnsOd> contenidoTabla_culumnasTestaux) {
        this.contenidoTabla_culumnasTestaux = contenidoTabla_culumnasTestaux;
    }

    public List<ContentTableInfoOd> getContenidoTabla_infoTestaux() {
        return contenidoTabla_infoTestaux;
    }

    public void setContenidoTabla_infoTestaux(List<ContentTableInfoOd> contenidoTabla_infoTestaux) {
        this.contenidoTabla_infoTestaux = contenidoTabla_infoTestaux;
    }

    public String getJsonAgregarUsuario() {
        return jsonAgregarUsuario;
    }

    public void setJsonAgregarUsuario(String jsonAgregarUsuario) {
        this.jsonAgregarUsuario = jsonAgregarUsuario;
    }


    public VBTUsersOd getUsuarioContrato() {
        return usuarioContrato;
    }

    public void setUsuarioContrato(VBTUsersOd usuarioContrato) {
        this.usuarioContrato = usuarioContrato;
    }

    public List<ContentSelectOd> getCorreos() {
        return correos;
    }

    public void setCorreos(List<ContentSelectOd> correos) {
        this.correos = correos;
    }

    public String getJsonParametrosString() {
        return jsonParametrosString;
    }

    public void setJsonParametrosString(String jsonParametrosString) {
        this.jsonParametrosString = jsonParametrosString;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<ContentSelectOd> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<ContentSelectOd> acciones) {
        this.acciones = acciones;
    }

    public List<ContentSelectOd> getObjetos() {
        return objetos;
    }

    public void setObjetos(List<ContentSelectOd> objetos) {
        this.objetos = objetos;
    }

    public ConsultaBitacoraOd getDatosConsultBitacora() {
        return datosConsultBitacora;
    }

    public void setDatosConsultBitacora(ConsultaBitacoraOd datosConsultBitacora) {
        this.datosConsultBitacora = datosConsultBitacora;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getCodOpc() {
        return codOpc;
    }

    public void setCodOpc(String codOpc) {
        this.codOpc = codOpc;
    }

    public String getCarterasContrato() {
        return carterasContrato;
    }

    public void setCarterasContrato(String carterasContrato) {
        this.carterasContrato = carterasContrato;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public IFirmasConjuntasServicio getFirmasConjuntasServicio() {
        return firmasConjuntasServicio;
    }

    public void setFirmasConjuntasServicio(IFirmasConjuntasServicio firmasConjuntasServicio) {
        this.firmasConjuntasServicio = firmasConjuntasServicio;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public ParametrosPersonalesFCOd getParametrosPersonalesFC() {
        return parametrosPersonalesFC;
    }

    public void setParametrosPersonalesFC(ParametrosPersonalesFCOd parametrosPersonalesFC) {
        this.parametrosPersonalesFC = parametrosPersonalesFC;
    }

    public ParametrosPersonalesFCOd getParametrosPersonalesBaseFC() {
        return parametrosPersonalesBaseFC;
    }

    public void setParametrosPersonalesBaseFC(ParametrosPersonalesFCOd parametrosPersonalesBaseFC) {
        this.parametrosPersonalesBaseFC = parametrosPersonalesBaseFC;
    }

    public String getJsonFirmasConjuntas() {
        return jsonFirmasConjuntas;
    }

    public void setJsonFirmasConjuntas(String jsonFirmasConjuntas) {
        this.jsonFirmasConjuntas = jsonFirmasConjuntas;
    }

}





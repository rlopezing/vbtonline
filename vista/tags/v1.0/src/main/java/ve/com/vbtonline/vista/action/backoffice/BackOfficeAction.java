package ve.com.vbtonline.vista.action.backoffice;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import com.venezolano.util.text.NullFormatter;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;
import ve.com.vbtonline.comun.fabrica.LanzarFabrica;
import ve.com.vbtonline.comun.od.FabricaTo;
import ve.com.vbtonline.servicio.negocio.backoffice.IBackOfficeServicio;
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
public class BackOfficeAction extends ActionSupport implements ServletContextAware, SessionAware,Serializable {
    private static final Logger logger = Logger.getLogger(BackOfficeAction.class);
    private FabricaTo fabrica;
    private Map session;
    private BackOfficeOd backOfficeOd;
    private CtaOpcOd ctaOpcOd;
    private IBackOfficeServicio backOfficeServicio;
    private String mensaje;
    private String codigo;
    private DataJson data=new DataJson();
    private String jsonBackOffice;
    private String jsonGrupo;
    private String jsonGOS;
    private String jsonConsultaUsuarios;
    private String jsonEditarUsuario;
    private String jsonEditarUsuarioContrato;
    private String jsonAgregarUsuario;
    private String jsonConsultaUsuariosContratos;
    private String jsonConsultaContratos;
    private String jsonParametrosString;
    private String jsonConsultaBitacora;
    private String jsonConsultaSucesos;
    private String jsonParametrosGlobales;
    private String jsonGuardarLog;
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
    private List<ContentSelectOd> telefonos;
    private List<ContentSelectOd> telefonoLocal;
    private List<ContentSelectOd> tipoEstatusContrato;
    private List<ContentSelectOd> usuarioCreador;
    private List<ContentSelectOd> tipoCiRif;
    private List<ContentSelectOd> acciones;
    private List<ContentSelectOd> objetos;
    private List<ContentSelectOd> motivosRechazo;
    private List<ContentSelectOd> motivosRechazoContrato;
    private List<ContentSelectOd> tipoAmbito;
    private List<ContentSelectOd> parametrosSelect;
    private String primeraVez;
    private VBTUsersOd usuario;
    private VBTUsersOd usuarioContrato;
    private String jsonEditarContrato;
    private String mensajeBO;
    private String desde;
    private String hasta;
    private String codOpc;
    private String respuesta;
    private String inicial;
    private String numCartera;
    private String fechaFiltro;
    private ParametrosPersonalesOd parametrosGlobales;
    private String parametros;
    private String tipoParametro;

    public String getNumCartera() {
        return numCartera;
    }

    public List<ContentSelectOd> getTelefonoLocal() {
        return telefonoLocal;
    }

    public List<ContentSelectOd> getParametrosSelect() {
        return parametrosSelect;
    }

    public void setParametrosSelect(List<ContentSelectOd> parametrosSelect) {
        this.parametrosSelect = parametrosSelect;
    }

    public void setTelefonoLocal(List<ContentSelectOd> telefonoLocal) {
        this.telefonoLocal = telefonoLocal;
    }

    public String getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public void setNumCartera(String numCartera) {
        this.numCartera = numCartera;
    }

    public List<ContentSelectOd> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<ContentSelectOd> telefonos) {
        this.telefonos = telefonos;
    }

    public BackOfficeAction() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        LanzarFabrica.getInstancia(this);
    }



    public String execute() throws Exception {
        final String origen = "BackOfficeAction.execute";
        long time;
        UsuarioOd usuario;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return INPUT;
    }

    public String cargar() throws Exception {
        final String origen = "BackOfficeAction.validarUsuario";
        long time;
        BackOfficeOd backOfficeOd;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonBackOffice, DataJson.class);
            backOfficeOd= getBackOfficeServicio().cargar("", data.getBackOffices().get(0));
            setMensaje(backOfficeOd.getId().toString());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String opcionesGrupo() throws Exception {
        final String origen = "BackOfficeAction.grupoOpciones";
        long time;
        CtaOpcOd ctaOpcOd;
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            Gson gson=new Gson();
            data = gson.fromJson(this.jsonGrupo, DataJson.class);
            ctaOpcOd= getBackOfficeServicio().opcionesGrupo("", data.getOpcionesGruposs().get(0));
            setMensaje(ctaOpcOd.getDesopc());


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarUsuarios() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuarios";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);
            usuario = (VBTUsersOd) session.get("user");
            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaUsuarios, DataJson.class);
            datosConsultaUBO = data.getConsultaUsuarioss().get(0);
            //Estos datos deberian venir de un Tag dependiendo del idioma cambia, debe cambiarse cuando se manejen los idiomas
            datosConsultaUBO.setStrRoot("BackOffice Administrador");
            datosConsultaUBO.setStrLoader("BackOffice Loader");
            datosConsultaUBO.setStrSupervisor("BackOffice Supervisor");
            datosConsultaUBO.setStrCliente("Client");
            datosConsultaUBO.setStrActivo("Activo");
            datosConsultaUBO.setStrInactiva("Inactivo");
            datosConsultaUBO.setStrCancelada("Cancelado");
            datosConsultaUBO.setStrBloqueado("Bloqueado");
            datosConsultaUBO.setStrDesconocido("desconocido");
            datosConsultaUBO.setStrOrden("Usuario");
            datosConsultaUBO.setLogin(usuario.getLogin());

            //cambiar
            usuario = (VBTUsersOd) session.get("user");

            TableOd tableOd=getBackOfficeServicio().consultarUsuarios(datosConsultaUBO, usuario);

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            if (datosConsultaUBO.getHdnAccion()==null){

            /// cargar elementos de los selects para la consulta
            if(session.get("tipoGrupo")==null){
              SelectOd grupos2 =getBackOfficeServicio().consultarElementos("0000000002");
              tipoGrupo=grupos2.getContenido();
                session.put("tipoGrupo",tipoGrupo);
           }else{
                tipoGrupo = (List<ContentSelectOd>) session.get("tipoGrupo");
            }


              SelectOd  estatus2 =getBackOfficeServicio().consultarElementos("0000000001");
              tipoEstatus=estatus2.getContenido();
              session.put("tipoEstatus",tipoEstatus);


            if(session.get("tipoCIRIF")==null){
              SelectOd  cirif =getBackOfficeServicio().cargarTipoCiRif();
              tipoCiRif=cirif.getContenido();
                session.put("tipoCIRIF",tipoCiRif);
            }else{
                tipoCiRif = (List<ContentSelectOd>) session.get("tipoCIRIF");
            }

                SelectOd  ambito =getBackOfficeServicio().consultarElementos("0000000008");
                tipoAmbito = ambito.getContenido();
                session.put("tipoAmbito",tipoAmbito);

              primeraVez = "SI";
            }else{
                primeraVez="NO";
            }

            //getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","1","",usuario.getIP(),"Consulta de usuarios con Exito");

            //////

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarBitacora() throws Exception {
        final String origen = "BackOfficeAction.consultarBitacora";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();
        try{
            usuario = (VBTUsersOd) session.get("user");
            String idioma = (String) session.get("idioma");
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaBitacora, DataJson.class);
            datosConsultBitacora = data.getConsultaBitacorass().get(0);

            if (datosConsultBitacora.getDesde().equalsIgnoreCase("") && datosConsultBitacora.getHasta().equalsIgnoreCase("")&&(datosConsultBitacora.getUltimos().equalsIgnoreCase(""))){
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","VE".toUpperCase()));
                String fechaHoy = formatoFecha.format(new Date());
                datosConsultBitacora.setDesde(fechaHoy);
                datosConsultBitacora.setHasta(fechaHoy);

            }else{
                datosConsultBitacora.setDesde(datosConsultBitacora.getDesde().replace("-","/"));
                datosConsultBitacora.setHasta(datosConsultBitacora.getHasta().replace("-","/"));
            }

            if(datosConsultBitacora.getUsuario().equalsIgnoreCase(""))
                datosConsultBitacora.setUsuario(null);
            if(datosConsultBitacora.getDesde().equalsIgnoreCase(""))
                datosConsultBitacora.setDesde(null);
            if(datosConsultBitacora.getHasta().equalsIgnoreCase(""))
                datosConsultBitacora.setHasta(null);
            if(datosConsultBitacora.getUltimos().equalsIgnoreCase(""))
                datosConsultBitacora.setUltimos(null);
            if(datosConsultBitacora.getIdObjeto().equalsIgnoreCase(""))
                datosConsultBitacora.setIdObjeto(null);
            if(datosConsultBitacora.getAccion()!=null)
            {
               if(datosConsultBitacora.getAccion().equalsIgnoreCase("-2"))
                   datosConsultBitacora.setAccion(null);
            }
            if(datosConsultBitacora.getObjeto()!=null)
            {
                if(datosConsultBitacora.getObjeto().equalsIgnoreCase("-2"))
                    datosConsultBitacora.setObjeto(null);
            }

//
//
            TableOd tableOd=getBackOfficeServicio().consultarBitacoras(datosConsultBitacora,usuario,idioma);

            fechaFiltro=datosConsultBitacora.getDesde();

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

//            if (datosConsultaUBO.getHdnAccion()==null){

            /// cargar elementos de los selects para la consulta
              if (!usuario.getTipo().equalsIgnoreCase("9")){
                  SelectOd accion =getBackOfficeServicio().cargarAccionFiltro();
                  acciones=accion.getContenido();
              }else{
                  SelectOd accion =getBackOfficeServicio().cargarAccionFiltroFC(usuario.getNumContrato());
                  acciones=accion.getContenido();
              }




              SelectOd  objeto =getBackOfficeServicio().cargarObjetosFiltro();
              objetos=objeto.getContenido();

            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","16","",usuario.getIP(),"Consulta de bitacora con exito");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarSucesos() throws Exception {
        final String origen = "BackOfficeAction.consultarSucesos";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaSucesos, DataJson.class);
            List<String> parametros = data.getParametros();

            if (parametros.get(0).equalsIgnoreCase("") && parametros.get(1).equalsIgnoreCase("")){
                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy",new Locale("es","VE".toUpperCase()));
                String fechaHoy = formatoFecha.format(new Date());
                parametros.set(0,fechaHoy);
                parametros.set(1, fechaHoy);
                desde = fechaHoy.replace("/", "-");
                hasta = fechaHoy.replace("/", "-");
            }else{
                desde = parametros.get(0);
                hasta = parametros.get(1);
                parametros.set(0, parametros.get(0).replace("-", "/"));
                parametros.set(1, parametros.get(1).replace("-", "/"));

            }


            TableOd tableOd=getBackOfficeServicio().consultarSucesos(parametros);

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            usuario = (VBTUsersOd) session.get("user");
            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","17","",usuario.getIP(),"Consulta de visor de sucesos con exito");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarUsuariosContratos() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuariosContratos";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaUsuariosContratos, DataJson.class);
            datosConsultaUBO = data.getConsultaUsuarioss().get(0);
            //Estos datos deberian venir de un Tag dependiendo del idioma cambia, debe cambiarse cuando se manejen los idiomas
            datosConsultaUBO.setStrRoot("BackOffice Administrador");
            datosConsultaUBO.setStrLoader("BackOffice Loader");
            datosConsultaUBO.setStrSupervisor("BackOffice Supervisor");
            datosConsultaUBO.setStrCliente("Client");
            datosConsultaUBO.setStrActivo("Activo");
            datosConsultaUBO.setStrInactiva("Inactivo");
            datosConsultaUBO.setStrCancelada("Cancelado");
            datosConsultaUBO.setStrBloqueado("Bloqueado");
            datosConsultaUBO.setStrDesconocido("desconocido");
            datosConsultaUBO.setStrOrden("Nombre");

            //cambiar
            usuario = (VBTUsersOd) session.get("user");
            TableOd tableOd=getBackOfficeServicio().consultarUsuariosContratos(datosConsultaUBO, usuario);

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            if (datosConsultaUBO.getHdnAccion()==null){

            /// cargar elementos de los selects para la consulta

              SelectOd grupos2 =getBackOfficeServicio().cargarGrupoClientes();
              tipoGrupo=grupos2.getContenido();
                session.put("tipoGrupoCliente",tipoGrupo);



              SelectOd  estatus2 =getBackOfficeServicio().consultarElementos("0000000001");
              tipoEstatus=estatus2.getContenido();
                session.put("tipoEstatus",tipoEstatus);

                SelectOd  ambito =getBackOfficeServicio().consultarElementos("0000000008");
                tipoAmbito=ambito.getContenido();
                session.put("tipoAmbito",tipoAmbito);

              primeraVez = "SI";
            }else{
                primeraVez="NO";
            }
            //////

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarContratos() throws Exception {
        final String origen = "BackOfficeAction.consultarContratos";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaContratos, DataJson.class);
            datosConsContBO = data.getConsultaContratoss().get(0);
            usuario = (VBTUsersOd) session.get("user");
            String  idioma = (String) session.get("idioma");
            //Estos datos deberian venir de un Tag dependiendo del idioma cambia, debe cambiarse cuando se manejen los idiomas
            datosConsContBO.setStrNuevo("New");
            datosConsContBO.setStrActivo("Active");
            datosConsContBO.setStrCancelado("Cancelled");
            datosConsContBO.setStrInactivo("Inactive");
            datosConsContBO.setStrRechazado("Rejected");
            datosConsContBO.setStrDesconocido("desconocido");
            datosConsContBO.setHndAccion(null);

//            datosConsContBO.setTxtNumeroCartera(null);
//            datosConsContBO.setStrTipoUsuario(null);
            datosConsContBO.setLogin(usuario.getLogin());
//            datosConsContBO.setStrCmbCreadoPor(null);
//            datosConsContBO.setStrTxtNumeroContrato(null);
//            datosConsContBO.setStrTxtUsuario(null);
//            datosConsContBO.setStrTxtNombreCliente(null);
//            datosConsContBO.setStrTxtCIRIFCliente(null);
//            datosConsContBO.setStrTxtDesde(null);
//            datosConsContBO.setStrTxtHasta(null);
            datosConsContBO.setStrOrden(null);
            usuario = (VBTUsersOd) session.get("user");
            //cambiar
            if(!"OK".equalsIgnoreCase (datosConsContBO.getStrInicial())){

                TableOd tableOd=getBackOfficeServicio().consultarContratos(datosConsContBO, usuario);
                 contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
                 contenidoTabla_infoTest=tableOd.getContenidoTabla_info();
            }


            if(session.get("tipoEstatusContrato")==null){
                SelectOd  estatus2 =getBackOfficeServicio().consultarElementos("0000000003");
                tipoEstatusContrato=estatus2.getContenido();
                session.put("tipoEstatusContrato",tipoEstatusContrato);
            }else{
                tipoEstatusContrato = (List<ContentSelectOd>) session.get("tipoEstatusContrato");
            }

            if(session.get("usuarioCreador")==null){
                SelectOd  usuarioCreador2 =getBackOfficeServicio().cargarUsuariosSelect();
                usuarioCreador=usuarioCreador2.getContenido();
                session.put("usuarioCreador",usuarioCreador);
            }else{
                usuarioCreador = (List<ContentSelectOd>) session.get("usuarioCreador");
            }


                /// cargar elementos de los selects para la consulta

                SelectOd grupos2 =getBackOfficeServicio().cargarGrupoClientes();
                tipoGrupo=grupos2.getContenido();
                session.put("tipoGrupoFC",tipoGrupo);


                SelectOd  cirif =getBackOfficeServicio().cargarTipoCiRif();
                tipoCiRif=cirif.getContenido();
                session.put("tipoCIRIFFC",tipoCiRif);



            //////




            //getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","7","",usuario.getIP(),"Consulta General de Contratos con exito");


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }



    public String consultarDetallesContratos() throws Exception {
        final String origen = "BackOfficeAction.consultarDetallesContratos";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaContratos, DataJson.class);
            String codContrato = data.getParametros().get(0);
            List<String> parametros = new ArrayList<>();

            //cambiar

            carterasContrato =getBackOfficeServicio().consultarCarterasContrato(codContrato);

            parametros.add(codContrato);
            parametros.add("Activa");
            parametros.add("Inactiva");
            parametros.add("Cancelada");
            parametros.add("Bloqueado");
            parametros.add("Desconocido");

//            p_strContrato     IN VARCHAR2,
//                    p_strActiva         IN VARCHAR2,
//            p_strInactiva     IN VARCHAR2,
//                    p_strCancelada     IN VARCHAR2,
//            p_strBloqueado     IN VARCHAR2,
//                    p_strDesconocido  IN VARCHAR2,
//            p_bac_contratousugrupo OUT bac_contratousugrupo,
//                    p_resultado OUT VARCHAR2) AS

            TableOd tableOd=getBackOfficeServicio().consultarUsuariosContratoDetalle(parametros,data.getParametros().get(1));

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            SelectOd grupos2 =getBackOfficeServicio().cargarGrupoClientes();
            tipoGrupo=grupos2.getContenido();
            session.put("tipoGrupoCliente",tipoGrupo);



            SelectOd  estatus2 =getBackOfficeServicio().consultarElementos("0000000001");
            tipoEstatus=estatus2.getContenido();
            session.put("tipoEstatus",tipoEstatus);



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);
            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","7",codContrato,usuario.getIP(),"Consulta del Contrato: "+codContrato);


        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String cargarEditarContratos() throws Exception {
        final String origen = "BackOfficeAction.cargarEditarContratos";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);
            VBTUsersOd usuario=new VBTUsersOd();
            usuario = (VBTUsersOd) session.get("user");
            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarContrato, DataJson.class);
            VBTContratoOd contrato = data.getConsultaEditarContratos().get(0);


            //carga de usuarios del contrato
            TableOd tableOd=getBackOfficeServicio().cargarUsuariosContratos(contrato.getNum_contrato());

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            //carga de las carteras del contrato
            TableOd tableOd2=getBackOfficeServicio().cargarCarterasContratos("Compartida","Individual","Activa","Inactiva",contrato.getNum_contrato());

            contenidoTabla_culumnasTestaux=tableOd2.getContenidoTabla_culumnas();
            contenidoTabla_infoTestaux=tableOd2.getContenidoTabla_info();

            SelectOd grupos2 =getBackOfficeServicio().cargarGrupoClientes();
            tipoGrupo=grupos2.getContenido();
            session.put("tipoGrupoCliente",tipoGrupo);
            //Carga los Motivos Para Generar los checks
            motivosRechazo=getBackOfficeServicio().cargarMotivosRechazo().getContenido();

            //Carga los Motivos Para un contrato en espefifico
            motivosRechazoContrato=getBackOfficeServicio().cargarMotivosRechazoContrato(contrato.getNum_contrato()).getContenido();

            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","7",contrato.getNum_contrato(),usuario.getIP(),"Consulta detalle de contrato");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }


    public String cargarCartera() throws Exception {
        final String origen = "BackOfficeAction.cargarCartera";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarContrato, DataJson.class);
            VBTContratoOd contrato = data.getConsultaEditarContratos().get(0);

            //carga de las carteras del contrato
            String cartera=getBackOfficeServicio().cargarCartera("Compartida","Individual","Activa","Inactiva",contrato.getNum_cartera());

            numCartera=cartera;

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }


    public String cargarAgregarContratos() throws Exception {
        final String origen = "BackOfficeAction.cargarAgregarContratos";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            //carga de usuarios del contrato
            List<List<String>> bodys=new ArrayList();


            List<String> header=new ArrayList<String>();
            header.add("");
            header.add("Nombre");
            header.add("Usuario");
            header.add("E-mail");
            header.add("Grupo");
            header.add("Telefono Celular");

            TableOd tableOd=new TableOd();

            tableOd= TransformTable.getTable(header, bodys);

            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            //carga de las carteras del contrato

            bodys=new ArrayList();


            header=new ArrayList<String>();
            header.add("");
            header.add("Incluir");
            header.add("C&oacute;digo Cartera");
            header.add("Modalidad");
            header.add("Estatus");
            header.add("Cliente Principal");

            TableOd tableOd2=new TableOd();

            tableOd2= TransformTable.getTable(header, bodys);

            contenidoTabla_culumnasTestaux=tableOd2.getContenidoTabla_culumnas();
            contenidoTabla_infoTestaux=tableOd2.getContenidoTabla_info();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String cargarDatos() throws Exception {
        final String origen = "BackOfficeAction.cargarDatos";
        long time;
        BackOfficeOd backOfficeOd;
        VBTUsersOd usuarios = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

//            usuarios.setCirif("11111111");



            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultaUsuario() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuario";
        long time;
        BackOfficeOd backOfficeOd;
//        VBTUsersOd usuario = new VBTUsersOd();
         String loginUsuario = new String();
         String tipoGrupo = new String();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);
            VBTUsersOd usuario2 = new VBTUsersOd();
            usuario2 = (VBTUsersOd) session.get("user");

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarUsuario, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            loginUsuario = usu.getLogin();
            tipoGrupo = usu.getTipo_grupo();
            String prueba = ResourceBundle.getBundle("Comun2_ve_es").getString("EXCEPCIONES");
            usuario= getBackOfficeServicio().consultarUsuario(loginUsuario, tipoGrupo);

            getBackOfficeServicio().guardarLog(usuario2.getLogin(),"3","1","1",usu.getLogin(),usuario2.getIP(),"Consulta detalle de usuario");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarUsuarioContrato() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuarioContrato";
        long time;
        BackOfficeOd backOfficeOd;
        String loginUsuario = new String();
        VBTUsersOd usuario=new VBTUsersOd();

        try{
            usuario = (VBTUsersOd) session.get("user");
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarUsuarioContrato, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            loginUsuario = usu.getLogin();
            usuarioContrato= getBackOfficeServicio().consultarUsuarioContrato(loginUsuario);

            SelectOd  correos2 =getBackOfficeServicio().consultarCorreos(usuarioContrato.getCodpercli(),loginUsuario);
            correos=correos2.getContenido();


            SelectOd  telefonos2 =getBackOfficeServicio().consultarTelefonos(usuarioContrato.getCodpercli(),loginUsuario);
            telefonos=telefonos2.getContenido();

            SelectOd  telefonos3 =getBackOfficeServicio().consultarTelefonosLocal(usuarioContrato.getCodpercli(),loginUsuario);
            telefonoLocal=telefonos3.getContenido();

            SelectOd  ambito =getBackOfficeServicio().consultarElementos("0000000008");
            tipoAmbito=ambito.getContenido();
            session.put("tipoAmbito",tipoAmbito);

            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","1",loginUsuario,usuario.getIP(),"Consulta Detalle de Usuarios en Contratos");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }



    public String consultarTelefonos() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuarioContrato";
        long time;
        BackOfficeOd backOfficeOd;
        String loginUsuario = new String();
        VBTUsersOd usuario=new VBTUsersOd();

        try{
            usuario = (VBTUsersOd) session.get("user");
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarUsuarioContrato, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            loginUsuario = usu.getLogin();

            SelectOd  telefonos2 =getBackOfficeServicio().consultarTelefonos(usu.getCodpercli(),usu.getLogin());
            telefonos=telefonos2.getContenido();

            SelectOd  correos2 =getBackOfficeServicio().consultarCorreos(usu.getCodpercli(),usu.getLogin());
            correos=correos2.getContenido();

            SelectOd grupos2 =getBackOfficeServicio().cargarGrupoClientes();
            tipoGrupo=grupos2.getContenido();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }




    public String consultarGrupos() throws Exception {
        final String origen = "BackOfficeAction.consultarGrupos";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();


            TableOd tableOd=getBackOfficeServicio().consultarGrupos();
            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarGruposOpcionesSistemas() throws Exception {
        final String origen = "BackOfficeAction.consultarGruposOpcionesSistemas";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonGOS, DataJson.class);
            GruposOd grupo =  data.getConsultaGrupoOS().get(0);
            //substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()
            String categoria = grupo.getGrupoCategoria().substring(0,grupo.getGrupoCategoria().indexOf("|")).trim();
//            String categoria = grupo.getGrupoCategoria().split("|");
//            grupo.setGrupoCategoria();
            TableOd tableOd=getBackOfficeServicio().consultarGrupoOpcionesSistema(categoria);
            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();
            datosGrupoOS = grupo;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarUsuarioOpcionesSistemas() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuarioOpcionesSistemas";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonBackOffice, DataJson.class);
            List<String> parametros=  data.getParametros();
            //substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()
//            String categoria = grupo.getGrupoCategoria().substring(0,grupo.getGrupoCategoria().indexOf("|")).trim();
//            grupo.setGrupoCategoria();

            TableOd tableOd=getBackOfficeServicio().consultarOpcionesSistema(parametros.get(0));
            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();
//            datosGrupoOS = grupo;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarGrupoOpcPermisos() throws Exception {
        final String origen = "BackOfficeAction.consultarGruposOpcionesPermisos";
        long time;
        String gruposs=new String();
        String opcion = new String();
        List<String> listaAcciones = new ArrayList<String>();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonGOS, DataJson.class);
            GruposOd grupo =  data.getConsultaGrupoOS().get(0);
            gruposs = grupo.getGrupo();
            gruposs = gruposs.substring((gruposs.indexOf("|")+1),gruposs.indexOf("!"));
//            gruposs = gruposs.split("|")[1];
//            substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()
            opcion = grupo.getGrupoCategoria();
            listaAcciones = (List<String>) session.get("accionesSistema");
            TableOd acciones=getBackOfficeServicio().consultarGrupoOpcPermisos(gruposs, opcion,listaAcciones);

            contenidoTabla_culumnasTest=acciones.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=acciones.getContenidoTabla_info();
//            datosGrupoOS = grupo;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String consultarUsuariosOpcPermisos() throws Exception {
        final String origen = "BackOfficeAction.consultarUsuariosOpcPermisos";
        long time;
        String gruposs=new String();
        String opcion = new String();
        List<String> listaAcciones = new ArrayList<String>();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonBackOffice, DataJson.class);
            List<String> parametros =  data.getParametros();
//            gruposs = grupo.getGrupo();
//            gruposs = gruposs.substring((gruposs.indexOf("|")+1),gruposs.length());
//            substring((datos.getAccountCode().indexOf("|")+1),datos.getAccountCode().length()
//            opcion = grupo.getGrupoCategoria();
            codOpc = parametros.get(0);
            listaAcciones = (List<String>) session.get("accionesSistema");
            //                                                            usuario          ,    opcion        , acciones
            TableOd acciones=getBackOfficeServicio().consultarOpcPermisos(parametros.get(1), parametros.get(0), listaAcciones);

            contenidoTabla_culumnasTest=acciones.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=acciones.getContenidoTabla_info();
//            datosGrupoOS = grupo;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String salvarPermisosGrupos() throws Exception {
        final String origen = "BackOfficeAction.salvarPermisosGrupos";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();
        String opcion = new String();
        List<String> listaParametros = new ArrayList<String>();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonGOS, DataJson.class);

            usuario = (VBTUsersOd) session.get("user");
//            p_strTxtUsuarioEditar
            listaParametros.add(usuario.getLogin());
//            p_strTxtcodgrp
            String codgru = data.getParametros().get(1).substring((data.getParametros().get(1).indexOf("|")+1), data.getParametros().get(1).indexOf("!"));
//            String codgru = data.getParametros().get(1).split("|")[1];
            listaParametros.add(codgru);
//            p_strTxtcodopc
            listaParametros.add(data.getParametros().get(2));
//            p_strTxttipacc
            listaParametros.add(data.getParametros().get(0));

            String respuesta = getBackOfficeServicio().salvarPermisosGrupos(listaParametros);
            mensaje = respuesta;


            getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","8",codgru,usuario.getIP(),"Editar permisos Grupo "+codgru+" con exito");

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String salvarPermisosUsuarios() throws Exception {
        final String origen = "BackOfficeAction.salvarPermisosUsuarios";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();
        String opcion = new String();
        List<String> listaParametros = new ArrayList<String>();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonGOS, DataJson.class);

            usuario = (VBTUsersOd) session.get("user");
//            p_strTxtUsuarioEditar
            listaParametros.add(data.getParametros().get(1));
//            p_strTxtcodgrp
            listaParametros.add(usuario.getLogin());
//            p_strTxtcodopc
            listaParametros.add(data.getParametros().get(2));
//            p_strTxttipacc
            listaParametros.add(data.getParametros().get(0));

            String respuesta = getBackOfficeServicio().salvarPermisosUsuarios(listaParametros);
            mensaje = respuesta;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);

            getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1", data.getParametros().get(1),usuario.getIP(),"Se ha modificado los accesos especiales, "+ data.getParametros().get(1) +" con exito");


        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String eliminarPermisosUsuarios() throws Exception {
        final String origen = "BackOfficeAction.eliminarPermisosUsuarios";
        long time;
        VBTUsersOd usuario=new VBTUsersOd();
        String opcion = new String();
        List<String> listaParametros = new ArrayList<String>();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonGOS, DataJson.class);

            usuario = (VBTUsersOd) session.get("user");
//            p_strTxtUsuarioEditar
            listaParametros.add(data.getParametros().get(0));
//            p_strTxtcodopc
            listaParametros.add(data.getParametros().get(1));

            String respuesta = getBackOfficeServicio().eliminarPermisosUsuarios(listaParametros);
            mensaje = respuesta;
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);

            getBackOfficeServicio().guardarLog(usuario.getLogin(),"5","1","1","",usuario.getIP(),"Se ha editado los permisos del usuario, "+ data.getParametros().get(1) +" con exito");


        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String editarUsuario() throws Exception {
        final String origen = "BackOfficeAction.editarUsuario";
        long time;
        String respuesta= new String();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarUsuario, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            usu.setCodpercli(usuario.getCodpercli());
            mensaje = "El usuario seleccionado no pudo ser modificado";

            validacionUtil validar = new validacionUtil();

            //En caso de que la codificacion falle evita que se guarde basura en la bd
            usu.setNombres(validar.caracteresEspeciales(usu.getNombres()));
            usu.setDireccion(validar.caracteresEspeciales(usu.getDireccion()));

            respuesta=getBackOfficeServicio().editarUsuario(usu);

            if (respuesta.equals("OK")){
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"Se ha editado el usuario: "+ usu.getLogin()+" con exito");

                mensaje = "El usuario seleccionado fue modificado satisfactoriamente";
                codigo = "1";
            }else{
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"El  usuario: "+ usu.getLogin()+" no pudo ser modificado");

                mensaje = "El usuario seleccionado no pudo ser modificado";
                codigo = "0";
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            codigo = "0";
            mensaje = "El usuario seleccionado no pudo ser modificado";
            logger.error(e);
        }
        return SUCCESS;
    }

    public String editarUsuarioContrato() throws Exception {
        final String origen = "BackOfficeAction.editarUsuarioContrato";
        long time;
        String respuesta= new String();
        VBTUsersOd usuario = new VBTUsersOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonEditarUsuarioContrato, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            mensaje = "El usuario seleccionado fue no pudo ser modificado";
            codigo ="1";
            usuario = (VBTUsersOd) session.get("user");
            respuesta=getBackOfficeServicio().editarUsuarioContrato(usu,usuario.getLogin());

            if (respuesta.equals("OK")){
                if (usu.getCambioStatus().equalsIgnoreCase("Si"))
                    getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"Editar usuario en contrato "+usu.getLogin()+", Ambito: "+usu.getAmbito()+", Correo: "+usu.getEmail()+", Grupo: "+usu.getTipo_grupo()+", Status: "+usu.getStatus_cuenta());
                else
                    getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"Editar usuario en contrato "+usu.getLogin()+", Ambito: "+usu.getAmbito()+", Correo: "+usu.getEmail()+", Grupo: "+usu.getTipo_grupo());
                mensaje = "El usuario seleccionado fue modificado satisfactoriamente";
                codigo ="0";
            }else{
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1",usu.getLogin(),usuario.getIP(),"Editar usuario en contrato fallido");

                mensaje = "El usuario seleccionado no pudo ser modificado";
                codigo ="1";
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            mensaje = "El usuario seleccionado fue no pudo ser modificado";
            codigo ="1";
            logger.error(e);
        }
        return SUCCESS;
    }

    public String agregarUsuario() throws Exception {
        final String origen = "BackOfficeAction.agregarUsuario";
        long time;
        String respuesta= new String();
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonAgregarUsuario, DataJson.class);
            VBTUsersOd usu =  data.getLogins().get(0);
            mensaje = "El usuario no pudo ser agregado";
            usuario = (VBTUsersOd) session.get("user");

            validacionUtil validar = new validacionUtil();

            //En caso de que la codificacion falle evita que se guarde basura en la bd
            usu.setNombres(validar.caracteresEspeciales(usu.getNombres()));
            usu.setDireccion(validar.caracteresEspeciales(usu.getDireccion()));
            respuesta=getBackOfficeServicio().agregarUsuario(usu,usuario.getLogin());

            if (respuesta.equals("OK")){
                mensaje = "El nuevo usuario fue agregado satisfactoriamente.\n" +
                        "El login y la clave han sido enviados a la dirección de correo registrada.";
                codigo="OK";
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"12","1","1",usu.getLogin(),usuario.getIP(),"Se ha agregado un usuario: "+ usu.getLogin());

            }else if(respuesta.equals("Usuario Registrado")){
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"12","1","1",usu.getLogin(),usuario.getIP(),"Trato de agregar un usuario que ya existe : "+ usu.getLogin()+" ");
                mensaje = "El nuevo usuario que desea ingresar ya existe. \n Por favor elija otro usuario.\n Intente de nuevo, " +
                        "pero esta vez agregando un número al final del usuario que le sea fácil de recordar. ";
                codigo="ERROR";
            }else{
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"12","1","1",usu.getLogin(),usuario.getIP(),"El usuario no pudo ser agregado: "+ usu.getLogin()+" ");
                mensaje = "El usuario no pudo ser agregado";
                codigo="ERROR";
            }


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            mensaje = "El usuario no pudo ser agregado";
            codigo="ERROR";
        }
        return SUCCESS;
    }

    public String busquedaUsuariosContrato() throws Exception {
        final String origen = "BackOfficeAction.busquedaUsuariosContrato";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaContratos, DataJson.class);
            List<String> parametros = new ArrayList<String>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2).toUpperCase());
            parametros.add(data.getParametros().get(3).toUpperCase());
            parametros.add(data.getParametros().get(4).toUpperCase());
            parametros.add(data.getParametros().get(5).toUpperCase());
            parametros.add(data.getParametros().get(6));
            TableOd tableOd=getBackOfficeServicio().busquedaUsuariosContrato(parametros);
            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();

            SelectOd grupos2 =getBackOfficeServicio().cargarGrupoClientes();
            tipoGrupo=grupos2.getContenido();

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String busquedaCarterasContrato() throws Exception {
        final String origen = "BackOfficeAction.busquedaCarterasContrato";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaContratos, DataJson.class);
            List<String> parametros = new ArrayList<String>();
            parametros.add(data.getParametros().get(0));
            parametros.add(data.getParametros().get(1));
            parametros.add(data.getParametros().get(2));
            parametros.add(data.getParametros().get(3));
            parametros.add(data.getParametros().get(4));
            parametros.add(data.getParametros().get(5));
            parametros.add(data.getParametros().get(6));
            TableOd tableOd=getBackOfficeServicio().busquedaCarterasContrato(parametros);
            contenidoTabla_culumnasTest=tableOd.getContenidoTabla_culumnas();
            contenidoTabla_infoTest=tableOd.getContenidoTabla_info();
//            contenidoTabla_infoTest=null;

            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String agregarContrato() throws Exception {
        final String origen = "BackOfficeAction.agregarContrato";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaContratos, DataJson.class);
            List<CarterasOd> carteras = data.getContratos().get(0).getCarteras();
            List<VBTUsersOd> usuarios = data.getContratos().get(0).getUsuarios();
            String descripcion = data.getContratos().get(0).getDescripcion();

            VBTUsersOd usuario = (VBTUsersOd) session.get("user");


            respuesta=getBackOfficeServicio().agregarContrato(carteras, usuarios, usuario, descripcion,usuario.getLogin(),usuario.getIP());

            time = System.currentTimeMillis () - time;
            //El Log se guarda en el io
            //getBackOfficeServicio().guardarLog(usuario.getLogin(),"12","1","7","",usuario.getIP(),"Ha creado un contrato");

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }

    public String editarContrato() throws Exception {
        final String origen = "BackOfficeAction.editarContrato";
        long time;
        validacionUtil validar = new validacionUtil();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonConsultaContratos, DataJson.class);
            //viejas
            List<CarterasOd> carterasViejas = data.getContratos().get(0).getCarteras();
            List<VBTUsersOd> usuariosViejos = data.getContratos().get(0).getUsuarios();
            //nuevas
            List<CarterasOd> carterasNuevas = data.getContratos().get(1).getCarteras();
            List<VBTUsersOd> usuariosNuevos = data.getContratos().get(1).getUsuarios();

            String descripcion =  validar.caracteresEspeciales( data.getContratos().get(0).getDescripcion());
            String estatus = data.getContratos().get(0).getEstatus();
            String numeroContrato = data.getContratos().get(0).getNumeroContrato();

            List<ContentSelectOd> motivosRechazo=data.getContratos().get(0).getMotivosRechazo();
            VBTUsersOd usuarioEditor = (VBTUsersOd) session.get("user");
            respuesta=getBackOfficeServicio().editarContrato(carterasViejas,
                                                                    usuariosViejos,
                                                                    carterasNuevas,
                                                                    usuariosNuevos,
                                                                    descripcion,
                                                                    estatus,
                                                                    numeroContrato,
                                                                    usuarioEditor,motivosRechazo);
            contenidoTabla_infoTest=null;

            time = System.currentTimeMillis () - time;

            getBackOfficeServicio().guardarLog(usuarioEditor.getLogin(),"4","1","7",numeroContrato,usuarioEditor.getIP(),"Ha editado un contrato: "+numeroContrato);
            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }


    public String validarUsuario() throws Exception {
        final String origen = "BackOfficeAction.validarUsuario";
        long time;

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametrosString, DataJson.class);
            String usuario = data.getParametros().get(0);

            respuesta=getBackOfficeServicio().validarUsuario(usuario);


            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
        }
        return SUCCESS;
    }


    public String cargarParametrosGlobales() throws Exception {
        final String origen = "BackOffice.cargarParametrosGlobales";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            List<String> parametros = new ArrayList<String>();
            parametros.add(usuario.getCodpercli());
            parametros.add(usuario.getNumContrato());
            parametros.add("2");

            parametrosGlobales = new ParametrosPersonalesOd();
            parametrosGlobales=getBackOfficeServicio().cargarParametrosGlobales(tipoParametro);
            SelectOd tipoParametros = getBackOfficeServicio().consultarElementos("0000000010");
            parametrosSelect=tipoParametros.getContenido();
            //cargarParametrosPersonalesBase();
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }



    public String cargarParametrosContratos() throws Exception {
        final String origen = "BackOffice.cargarParametrosContratos";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();

        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            List<String> parametros = new ArrayList<String>();


            parametrosGlobales = new ParametrosPersonalesOd();
            parametrosGlobales=getBackOfficeServicio().cargarParametrosContratos(carterasContrato);



            //cargarParametrosPersonalesBase();
            time = System.currentTimeMillis () - time;

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            setMensaje(e.getMessage());
        }
        return SUCCESS;
    }




    public String guardarParametrosPersonalesBO() throws Exception {
        final String origen = "BckOffice.guardarParametrosPersonalesBO";
        long time;
        VBTUsersOd usuario = new VBTUsersOd();
        try{

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.inicio.act")+BackOfficeAction.class+" | "+origen);

            time = System.currentTimeMillis ();

            usuario = (VBTUsersOd) session.get("user");
            Gson gson=new Gson();
            data = gson.fromJson(this.jsonParametrosGlobales, DataJson.class);
            parametrosGlobales = data.getParametrosGlobales().get(0);

            String monto = new String();
            monto = parametrosGlobales.getVbt_mmaxtd().replace(".","");
            parametrosGlobales.setVbt_mmaxtd(monto.replace(",","."));
            monto = new String();
            monto = parametrosGlobales.getVbt_mminto().replace(".","");
            parametrosGlobales.setVbt_mminto(monto.replace(",","."));
            monto = new String();
            monto = parametrosGlobales.getVbt_mmto().replace(".","");
            parametrosGlobales.setVbt_mmto(monto.replace(",","."));
            monto = new String();
            monto = parametrosGlobales.getEx_mmtd().replace(".","");
            parametrosGlobales.setEx_mmtd(monto.replace(",","."));
            monto = new String();
            monto = parametrosGlobales.getEx_mminto().replace(".","");
            parametrosGlobales.setEx_mminto(monto.replace(",","."));
            monto = new String();
            monto = parametrosGlobales.getEx_mmto().replace(".", "");
            parametrosGlobales.setEx_mmto(monto.replace(",", "."));

           respuesta=getBackOfficeServicio().guardarParametrosPersonalesBO(parametrosGlobales, codigo,tipoParametro);

            time = System.currentTimeMillis () - time;

            if("VBT".equalsIgnoreCase(codigo))
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","20","",usuario.getIP(),"Ha editado los parametros globales de la organización");
            else
                getBackOfficeServicio().guardarLog(usuario.getLogin(),"4","1","1","",usuario.getIP(),"Ha editado los parametros personales del contrato: "+parametrosGlobales.getNum_contrato());

            logger.info(ResourceBundle.getBundle("mensajeslogs").getString("mensaje.exitos.act")+BackOfficeAction.class+" | "+origen+" | "+time);



        }catch (Exception e){
            logger.error(e);
            respuesta ="NO OK";
            setMensaje(respuesta);
        }
        return SUCCESS;
    }

    public void guardarLog() throws Exception {
        final String origen = "BckOffice.guardarLog";
        VBTUsersOd usuario = new VBTUsersOd();
        usuario = (VBTUsersOd) session.get("user");
        Gson gson=new Gson();
        data = gson.fromJson(this.jsonEditarUsuario, DataJson.class);
        VBTUsersOd usu =  data.getLogins().get(0);
        if (codOpc.equalsIgnoreCase("GN"))
            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","1",usu.getLogin(),usuario.getIP(),"Consulta General de Usuarios");
        else
            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","1",usu.getLogin(),usuario.getIP(),"Consulta detalle de usuario");
    }


    public void guardarLogContratos() throws Exception {
        final String origen = "BckOffice.guardarLogContratos";
        VBTUsersOd usuario = new VBTUsersOd();
        usuario = (VBTUsersOd) session.get("user");
        Gson gson=new Gson();
        data = gson.fromJson(this.jsonEditarContrato, DataJson.class);
        VBTContratoOd con =  data.getConsultaEditarContratos().get(0);
        if (codOpc.equalsIgnoreCase("GN"))
            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","7",con.getNum_contrato(),usuario.getIP(),"Consulta General Contratos");
        else
            getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","7",con.getNum_contrato(),usuario.getIP(),"Consulta detalle de contrato");
    }

    public void guardarLogUsuariosContratos() throws Exception {
        final String origen = "BckOffice.guardarLogUsuariosContratos";
        String loginUsuario = new String();
        usuario = (VBTUsersOd) session.get("user");
        Gson gson=new Gson();
        data = gson.fromJson(this.jsonEditarUsuarioContrato, DataJson.class);
        VBTUsersOd usu =  data.getLogins().get(0);
        loginUsuario = usu.getLogin();

        getBackOfficeServicio().guardarLog(usuario.getLogin(),"3","1","1",loginUsuario,usuario.getIP(),"Consulta General Usuarios en Contratos");

    }

   //getter and setter


    public String getJsonParametrosGlobales() {
        return jsonParametrosGlobales;
    }

    public void setJsonParametrosGlobales(String jsonParametrosGlobales) {
        this.jsonParametrosGlobales = jsonParametrosGlobales;
    }

    public ParametrosPersonalesOd getParametrosGlobales() {
        return parametrosGlobales;
    }

    public void setParametrosGlobales(ParametrosPersonalesOd parametrosGlobales) {
        this.parametrosGlobales = parametrosGlobales;
    }

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

    public IBackOfficeServicio getBackOfficeServicio() {
        return backOfficeServicio;
    }

    public void setBackOfficeServicio(IBackOfficeServicio backOfficeServicio) {
        this.backOfficeServicio = backOfficeServicio;
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

    public String getJsonGrupo() {
        return jsonGrupo;
    }

    public void setJsonGrupo(String jsonGrupo) {
        this.jsonGrupo = jsonGrupo;
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

    public String getJsonConsultaContratos() {
        return jsonConsultaContratos;
    }

    public void setJsonConsultaContratos(String jsonConsultaContratos) {
        this.jsonConsultaContratos = jsonConsultaContratos;
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

    public String getJsonConsultaUsuariosContratos() {
        return jsonConsultaUsuariosContratos;
    }

    public void setJsonConsultaUsuariosContratos(String jsonConsultaUsuariosContratos) {
        this.jsonConsultaUsuariosContratos = jsonConsultaUsuariosContratos;
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

    public String getJsonGOS() {
        return jsonGOS;
    }

    public void setJsonGOS(String jsonGOS) {
        this.jsonGOS = jsonGOS;
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

    public String getJsonEditarUsuarioContrato() {
        return jsonEditarUsuarioContrato;
    }

    public void setJsonEditarUsuarioContrato(String jsonEditarUsuarioContrato) {
        this.jsonEditarUsuarioContrato = jsonEditarUsuarioContrato;
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

    public String getJsonConsultaBitacora() {
        return jsonConsultaBitacora;
    }

    public void setJsonConsultaBitacora(String jsonConsultaBitacora) {
        this.jsonConsultaBitacora = jsonConsultaBitacora;
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

    public String getJsonConsultaSucesos() {
        return jsonConsultaSucesos;
    }

    public void setJsonConsultaSucesos(String jsonConsultaSucesos) {
        this.jsonConsultaSucesos = jsonConsultaSucesos;
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
    public String getInicial() {
        return inicial;
    }

    public void setInicial(String inicial) {
        this.inicial = inicial;
    }

    public List<ContentSelectOd> getMotivosRechazo() {
        return motivosRechazo;
    }

    public void setMotivosRechazo(List<ContentSelectOd> motivosRechazo) {
        this.motivosRechazo = motivosRechazo;
    }

    public List<ContentSelectOd> getMotivosRechazoContrato() {
        return motivosRechazoContrato;
    }

    public void setMotivosRechazoContrato(List<ContentSelectOd> motivosRechazoContrato) {
        this.motivosRechazoContrato = motivosRechazoContrato;
    }

    public String getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(String fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

    public List<ContentSelectOd> getTipoAmbito() {
        return tipoAmbito;
    }

    public void setTipoAmbito(List<ContentSelectOd> tipoAmbito) {
        this.tipoAmbito = tipoAmbito;
    }
}




